package cz.vutbr.web.domassign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.ElementMatcher;
import cz.vutbr.web.css.MatchCondition;
import cz.vutbr.web.css.MediaQuery;
import cz.vutbr.web.css.MediaSpec;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.Rule;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.Selector.PseudoClassType;
import cz.vutbr.web.css.Selector.PseudoElement;
import cz.vutbr.web.css.Selector.PseudoElementType;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.domassign.Analyzer.Holder;
import cz.vutbr.web.domassign.Analyzer.HolderItem;
import cz.vutbr.web.domassign.Analyzer.HolderSelector;
import cz.vutbr.web.domassign.Analyzer.OrderedRule;

/**
 * A pure (state-less) Analyser.
 * 
 * Can be used by clients that need more control over what computation is cached.
 *
 */
public final class AnalyzerUtil {

    private static final Logger log = LoggerFactory.getLogger(AnalyzerUtil.class);

    /**
     * Returns all applicable rules for an element
     * 
     * @param sheets
     * @param element
     * @param mediaspec
     * @return
     */
    public static OrderedRule[] getApplicableRules(final List<StyleSheet> sheets, final Element element, final MediaSpec mediaspec) {
	    final Holder rules = getClassifiedRules(sheets, mediaspec);
	    return getApplicableRules(element, rules, null);
    }

    public static Holder getClassifiedRules(final List<StyleSheet> sheets, final MediaSpec mediaspec) {
	    final Holder rules = new Holder();
	    AnalyzerUtil.classifyAllSheets(sheets, rules, mediaspec);
	    return rules;
    }

    public static NodeData getElementStyle(Element el, PseudoElementType pseudo, final ElementMatcher matcher, MatchCondition matchCond, OrderedRule[] applicableRules)
    {
    	return makeNodeData(computeDeclarations(el, pseudo, applicableRules, matcher, matchCond));
    }

	public static OrderedRule[] getApplicableRules(final Element e, final Holder holder, final RuleSet[] elementRuleSets)
	{
        // create set of possible candidates applicable to given element
        // set is automatically filtered to not contain duplicates
        final Set<OrderedRule> candidates = new HashSet<OrderedRule>();

        // match element classes
        for (final String cname : CSSFactory.getElementMatcher().elementClasses(e)) {
            // holder contains rule with given class
            final List<OrderedRule> classRules = holder.get(HolderItem.CLASS, cname.toLowerCase());
            if (classRules != null)
                candidates.addAll(classRules);
        }
        // log.trace("After CLASSes {} total candidates.", candidates.size());

        // match IDs
        final String id = CSSFactory.getElementMatcher().elementID(e);
        if (id != null && id.length() != 0) {
            final List<OrderedRule> idRules = holder.get(HolderItem.ID, id.toLowerCase());
            if (idRules != null)
                candidates.addAll(idRules);
        }
        // log.trace("After IDs {} total candidates.", candidates.size());
        
        // match elements
        final String name = CSSFactory.getElementMatcher().elementName(e);
        if (name != null) {
            final List<OrderedRule> nameRules = holder.get(HolderItem.ELEMENT, name.toLowerCase());
            if (nameRules != null)
                candidates.addAll(nameRules);
        }
        // log.trace("After ELEMENTs {} total candidates.", candidates.size());

        // others
        candidates.addAll(holder.get(HolderItem.OTHER, null));

        final int totalCandidates = candidates.size();
        final int netCandidates = elementRuleSets == null ? totalCandidates : totalCandidates + elementRuleSets.length;

        // log.debug("Totally {} candidates.", totalCandidates);

        // transform to array to speed up traversal
        // and sort rules in order as they were found in CSS definition
        final OrderedRule[] clist = (OrderedRule[]) candidates.toArray(new OrderedRule[netCandidates]);
        Arrays.sort(clist, 0, totalCandidates);

        // Append the element rules
        if (elementRuleSets != null) {
          final int lastOrder = totalCandidates > 0 ? clist[totalCandidates-1].getOrder() : 0;
          for (int i = 0; i < elementRuleSets.length; i++) {
            clist[totalCandidates + i] = new OrderedRule(elementRuleSets[i], lastOrder + i);
          }
        }

        // NOTE: The following trace statement creates a lot of memory pressure
        // log.trace("With values: {}", Arrays.toString(clist));

        return clist;
	}

	static NodeData makeNodeData(final List<Declaration> decls)
	{
		final NodeData main = CSSFactory.createNodeData();
        for (final Declaration d : decls)
            main.push(d);
        
        return main;
	}
    
	/**
	 * Classifies the rules in all the style sheets.
	 * @param mediaspec The specification of the media for evaluating the media queries.
	 */
	static void classifyAllSheets(final List<StyleSheet> sheets, final Holder rules, final MediaSpec mediaspec)
	{
		final Counter orderCounter = new Counter();
	    for (final StyleSheet sheet : sheets)
	        classifyRules(sheet, mediaspec, rules, orderCounter);
	}
	
	static boolean elementSelectorMatches(final Selector s, final Element e, final ElementMatcher matcher, final MatchCondition matchCond) {
		return s.matches(e, matcher, matchCond);
	}

    private static boolean nodeSelectorMatches(final Selector s, final Node n, final ElementMatcher matcher, final MatchCondition matchCond) {
        if (n.getNodeType() == Node.ELEMENT_NODE) {
            return s.matches((Element) n, matcher, matchCond);
        } else {
            return false;
        }
    }
    
	static List<Declaration> computeDeclarations(final Element e, final PseudoElementType pseudo, final OrderedRule[] clist, final ElementMatcher matcher, final MatchCondition matchCond) {
		// resulting list of declaration for this element with no pseudo-selectors (main list)(local cache)
        final List<Declaration> eldecl = new ArrayList<Declaration>();
        
        // for all candidates
        for (final OrderedRule orule : clist) {
            
            final RuleSet rule = orule.getRule();
            final StyleSheet sheet = rule.getStyleSheet();
            final StyleSheet.Origin origin = (sheet == null) ? StyleSheet.Origin.AGENT : sheet.getOrigin();
            
            // for all selectors inside
            for (final CombinedSelector s : rule.getSelectors()) {
                
                if (!AnalyzerUtil.matchSelector(s, e, matcher, matchCond)) {
                    log.trace("CombinedSelector \"{}\" NOT matched!", s);
                    continue;
                }

                log.trace("CombinedSelector \"{}\" matched", s);
                
                final PseudoElementType ptype = s.getPseudoElementType();
                if (ptype == pseudo)
                {
                    // add to the resulting list
                    final CombinedSelector.Specificity spec = s.computeSpecificity();
                    for (final Declaration d : rule)
                        eldecl.add(new AssignedDeclaration(d, spec, origin));
                }
            }
        }

        // sort declarations
        Collections.sort(eldecl); //sort the main list
        log.debug("Sorted {} declarations.", eldecl.size());
        log.trace("With values: {}", eldecl);
        
        return eldecl;
	}
    
    public static boolean hasPseudoSelector(final OrderedRule[] rules, final Element e, final MatchCondition matchCond, PseudoClassType pd)
    {
		for (final OrderedRule rule : rules) {
			for (final CombinedSelector cs : rule.getRule().getSelectors()) {
				final Selector lastSelector = cs.get(cs.size() - 1);
				if (lastSelector.hasPseudoClass(pd)) {
					return true;
				}
			}
		}
		return false;
	}

    public static boolean hasPseudoSelectorForAncestor(final OrderedRule[] rules, final Element e, final Element targetAncestor, final ElementMatcher matcher, final MatchCondition matchCond, PseudoClassType pd)
    {
		for (final OrderedRule rule : rules) {
			for (final CombinedSelector cs : rule.getRule().getSelectors()) {
				if (hasPseudoSelectorForAncestor(cs, e, targetAncestor, matcher, matchCond, pd)) {
					return true;
				}
			}
		}
		return false;
    }

    private static boolean hasPseudoSelectorForAncestor(final CombinedSelector sel, final Element e, final Element targetAncestor, final ElementMatcher matcher, final MatchCondition matchCond, PseudoClassType pd)
    {
        boolean retval = false;
        Selector.Combinator combinator = null;
        Element current = e;
        // traverse simple selector backwards
        for (int i = sel.size() - 1; i >= 0; i--) {
            // last simple selector
            final Selector s = sel.get(i);

            // decide according to combinator anti-pattern
            if (combinator == null) {
                retval = elementSelectorMatches(s, current, matcher, matchCond);
            } else if (combinator == Selector.Combinator.ADJACENT) {
                Node adjacent = current;
                do {
                    adjacent = adjacent.getPreviousSibling();
                } while (adjacent != null && adjacent.getNodeType() != Node.ELEMENT_NODE);
                retval = false;
                if (adjacent != null && adjacent.getNodeType() == Node.ELEMENT_NODE)
                {
                    current = (Element) adjacent; 
                    retval = elementSelectorMatches(s, current, matcher, matchCond);
                }
            } else if (combinator == Selector.Combinator.PRECEDING) {
                Node preceding = current.getPreviousSibling();
                retval = false;
                do
                {
                    if (preceding != null)
                    {
                        if (nodeSelectorMatches(s, preceding, matcher, matchCond))
                        {
                            current = (Element) preceding;
                            retval = true;
                        }
                        else
                            preceding = preceding.getPreviousSibling();
                    }
                } while (!retval && preceding != null);
            } else if (combinator == Selector.Combinator.DESCENDANT) {
                Node ancestor = current.getParentNode();
                retval = false;
                do
                {
                    if (ancestor != null)
                    {
                        if (nodeSelectorMatches(s, ancestor, matcher, matchCond))
                        {
                            current = (Element) ancestor;
                            retval = true;
                        }
                        else
                            ancestor = ancestor.getParentNode();
                    }
                } while (!retval && ancestor != null);
            } else if (combinator == Selector.Combinator.CHILD) {
                final Node parent = current.getParentNode();
                retval = false;
                if (parent != null && parent.getNodeType() == Node.ELEMENT_NODE)
                {
                    current = (Element) parent;
                    retval = elementSelectorMatches(s, current, matcher, matchCond);
                }
            }

            // set combinator for next loop
            combinator = s.getCombinator();

            // leave loop if not matched
            if (!retval) {
                break;
            } else if (current == targetAncestor) {
                return s.hasPseudoClass(pd);
            }
        }
        return false;
    }

    protected static boolean matchSelector(final CombinedSelector sel, final Element e, final ElementMatcher matcher, final MatchCondition matchCond)
    {
        boolean retval = false;
        Selector.Combinator combinator = null;
        Element current = e;
        // traverse simple selector backwards
        for (int i = sel.size() - 1; i >= 0; i--) {
            // last simple selector
            final Selector s = sel.get(i);
            log.trace("Iterating loop with selector {}, combinator {}",
                    s, combinator);

            // decide according to combinator anti-pattern
            if (combinator == null) {
                retval = elementSelectorMatches(s, current, matcher, matchCond);
            } else if (combinator == Selector.Combinator.ADJACENT) {
                Node adjacent = current;
                do {
                    adjacent = adjacent.getPreviousSibling();
                } while (adjacent != null && adjacent.getNodeType() != Node.ELEMENT_NODE);
                retval = false;
                if (adjacent != null && adjacent.getNodeType() == Node.ELEMENT_NODE)
                {
                    current = (Element) adjacent; 
                    retval = elementSelectorMatches(s, current, matcher, matchCond);
                }
            } else if (combinator == Selector.Combinator.PRECEDING) {
                Node preceding = current.getPreviousSibling();
                retval = false;
                do
                {
                    if (preceding != null)
                    {
                        if (nodeSelectorMatches(s, preceding, matcher, matchCond))
                        {
                            current = (Element) preceding;
                            retval = true;
                        }
                        else
                            preceding = preceding.getPreviousSibling();
                    }
                } while (!retval && preceding != null);
            } else if (combinator == Selector.Combinator.DESCENDANT) {
                Node ancestor = current.getParentNode();
                retval = false;
                do
                {
                    if (ancestor != null)
                    {
                        if (nodeSelectorMatches(s, ancestor, matcher, matchCond))
                        {
                            current = (Element) ancestor;
                            retval = true;
                        }
                        else
                            ancestor = ancestor.getParentNode();
                    }
                } while (!retval && ancestor != null);
            } else if (combinator == Selector.Combinator.CHILD) {
                final Node parent = current.getParentNode();
                retval = false;
                if (parent != null && parent.getNodeType() == Node.ELEMENT_NODE)
                {
                    current = (Element) parent;
                    retval = elementSelectorMatches(s, current, matcher, matchCond);
                }
            }

            // set combinator for next loop
            combinator = s.getCombinator();

            // leave loop if not matched
            if (!retval)
                break;
        }
        return retval;
    }
    
	/**
	 * Classify CSS rule according its selector for to be of specified item(s)
	 * 
	 * @param selector
	 *            CombinedSelector of rules
	 * @return List of HolderSelectors to which selectors conforms
	 */
	private static List<HolderSelector> classifySelector(final CombinedSelector selector) {

		final List<HolderSelector> hs = new ArrayList<HolderSelector>();

		try {
			// last simple selector decided about all selector
			final Selector last = selector.getLastSelector();

			// is element or other (wildcard)
			final String element = last.getElementName();
			if (element != null) {
				// wildcard
				if (Selector.ElementName.WILDCARD.equals(element))
					hs.add(new HolderSelector(HolderItem.OTHER, null));
				// element
				else
					hs.add(new HolderSelector(HolderItem.ELEMENT, element
							.toLowerCase()));
			}

			// is class name
			final String className = last.getClassName();
			if (className != null)
				hs.add(new HolderSelector(HolderItem.CLASS, className
						.toLowerCase()));

			// is id
			final String id = last.getIDName();
			if (id != null)
				hs.add(new HolderSelector(HolderItem.ID, id.toLowerCase()));

			// is in others
			if (hs.size() == 0)
				hs.add(new HolderSelector(HolderItem.OTHER, null));

			return hs;

		} catch (final UnsupportedOperationException e) {
			log
					.error("CombinedSelector does not include any selector, this should not happen!");
			return Collections.emptyList();
		}
	}

	private static class Counter {
		private int count = 0;
		public int getAndIncrement() {
			return count++;
		}
	}

	private static void insertClassified(final Holder holder, final List<HolderSelector> hs, final RuleSet value, final Counter orderCounter) {
		for (final HolderSelector h : hs)
			holder.insert(h.item, h.key, new OrderedRule(value, orderCounter.getAndIncrement()));
	}

	/**
	 * Divides rules in sheet into different categories to be easily and more
	 * quickly parsed afterward
	 * 
	 * @param sheet The style sheet to be classified
     * @param mediaspec The specification of the media for evaluating the media queries.
	 * @param orderCounter 
	 */
	private static void classifyRules(final StyleSheet sheet, final MediaSpec mediaspec, final Holder rules, final Counter orderCounter) {

		for (final Rule<?> rule : sheet) {
			// this rule conforms to all media
			if (rule instanceof RuleSet) {
				final RuleSet ruleset = (RuleSet) rule;
				for (final CombinedSelector s : ruleset.getSelectors()) {
					insertClassified(rules, classifySelector(s), ruleset, orderCounter);
				}
			}
			// this rule conforms to different media
			else if (rule instanceof RuleMedia) {
				final RuleMedia rulemedia = (RuleMedia) rule;

				boolean mediaValid = false;
                if(rulemedia.getMediaQueries()==null || rulemedia.getMediaQueries().isEmpty()) {
                    //no media queries actually
                    mediaValid = mediaspec.matchesEmpty();
                } else {
                    //find a matching query
    				for (final MediaQuery media : rulemedia.getMediaQueries()) {
                        if (mediaspec.matches(media)) {
                            mediaValid = true;
                            break;
                        }
    				}
                }
				
                if (mediaValid)
                {
    				// for all rules in media set
    				for (final RuleSet ruleset : rulemedia) {
    					// for all selectors in there
    					for (final CombinedSelector s : ruleset.getSelectors()) {
   							insertClassified(rules, classifySelector(s), ruleset, orderCounter);
    					}
    				}
                }
			}
		}

		// logging
		if (log.isDebugEnabled()) {
			log.debug("For media \"{}\" we have {} rules", mediaspec, rules.contentCount());
			if(log.isTraceEnabled()) {
				log.trace("Detailed view: \n{}", rules);
			}
		}
	}

}
