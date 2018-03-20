package cz.vutbr.web.domassign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.ElementMatcher;
import cz.vutbr.web.css.MatchCondition;
import cz.vutbr.web.css.MediaSpec;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.Selector.PseudoElement;
import cz.vutbr.web.css.Selector.PseudoElementType;
import cz.vutbr.web.css.StyleSheet;

/**
 * Analyzer allows to apply the given style to any document.
 * During the initialization, it divides rules of stylesheet into maps accoring to
 * medias and their type. Afterwards, it is able to return CSS declaration for any
 * DOM tree and media. It allows to use or not to use inheritance.
 * 
 * @author Karel Piwko 2008
 * @author Radek Burget 2008-2014
 * 
 */
public class Analyzer {

	private static final Logger log = LoggerFactory.getLogger(Analyzer.class);

	/** The style sheets to be processed. */
	protected List<StyleSheet> sheets;
	
	/** Rule order counter */
	protected int currentOrder;
	
	/**
	 * Holds maps of declared rules classified into groups of
	 * HolderItem (ID, CLASS, ELEMENT, OTHER).
	 */
	protected Holder rules;

	private MatchCondition matchCond;
	private ElementMatcher matcher;

	/**
	 * Creates the analyzer for a single style sheet.
	 * @param sheet The stylesheet that will be used as the source of rules.
	 */
	public Analyzer(StyleSheet sheet) {
	    sheets = new ArrayList<StyleSheet>(1);
	    sheets.add(sheet);
	    matchCond = CSSFactory.getDefaultMatchCondition();
	    matcher = CSSFactory.getElementMatcher();
	}

	/**
	 * Creates the analyzer for multiple style sheets.
	 * @param sheets A list of stylesheets that will be used as the source of rules.
	 */
	public Analyzer(List<StyleSheet> sheets) {
	    this.sheets = sheets;
        matchCond = CSSFactory.getDefaultMatchCondition();
        matcher = CSSFactory.getElementMatcher();
	}
	
	/**
	 * Registers a new match condition to be used for matching the elements and
	 * selector parts.
	 *
	 * @param matchCond
	 *            the new match condition
	 */
	public final void registerMatchCondition(MatchCondition matchCond) {
		this.matchCond = matchCond;
	}

	/**
	 * Obtains the match condition to be used for matching the elements and
	 * selector parts.
	 *
	 * @return the match condition used by the Analyzer.
	 */
	public final MatchCondition getMatchCondition() {
		return this.matchCond;
	}

    /**
     * Registers a new element matcher to be used for matching the elements and
     * selector parts.
     *
     * @param matcher
     *            the new element matcher
     */
    public final void registerElementMatcher(ElementMatcher matcher) {
        this.matcher = matcher;
    }

    /**
     * Obtains the matcher to be used for matching the elements.
     *
     * @return the matcher used by the Analyzer.
     */
    public final ElementMatcher getElementMatcher() {
        return matcher;
    }

	/**
	 * Evaluates CSS properties of DOM tree
	 * 
	 * @param doc
	 *            Document tree
	 * @param media
	 *            Media
	 * @param inherit
	 *            Use inheritance
	 * @return Map where each element contains its CSS properties
	 */
	public StyleMap evaluateDOM(Document doc, MediaSpec media, final boolean inherit) {

		DeclarationMap declarations = assingDeclarationsToDOM(doc, media, inherit);

		StyleMap nodes = new StyleMap(declarations.size());

		Traversal<StyleMap> traversal = new Traversal<StyleMap>(
				doc, (Object) declarations, NodeFilter.SHOW_ELEMENT) {
			
			@Override
			protected void processNode(StyleMap result, Node current, Object source) {

			    NodeData main = CSSFactory.createNodeData();
			    
				// for all declarations available in the main list (pseudo=null)
				List<Declaration> declarations = ((DeclarationMap) source).get((Element) current, null);
				if (declarations != null) 
				{
					for (Declaration d : declarations) {
						main.push(d);
					}
					if (inherit)
						main.inheritFrom(result.get((Element) walker.parentNode(), null));
				}
				// concretize values and store them
				result.put((Element) current, null, main.concretize());
				
				//repeat for the pseudo classes (if any)
				for (PseudoElementType pseudo : ((DeclarationMap) source).pseudoSet((Element) current))
				{
				    NodeData pdata = CSSFactory.createNodeData();
	                declarations = ((DeclarationMap) source).get((Element) current, pseudo);
	                if (declarations != null) 
	                {
	                    for (Declaration d : declarations) {
	                        pdata.push(d);
	                    }
                        pdata.inheritFrom(main); //always inherit from the main element style
	                }
	                // concretize values and store them
	                result.put((Element) current, pseudo, pdata.concretize());
				}
				
			}
		};

		traversal.levelTraversal(nodes);

		return nodes;
	}

   public StyleMap evaluateDOM(Document doc, String media, final boolean inherit) {
       return evaluateDOM(doc, new MediaSpec(media), inherit);
   }

	/**
	 * Creates map of declarations assigned to each element of a DOM tree
	 * 
	 * @param doc
	 *            DOM document
	 * @param media
	 *            Media type to be used for declarations
	 * @param inherit
	 *            Inheritance (cascade propagation of values)
	 * @return Map of elements as keys and their declarations
	 */
	protected DeclarationMap assingDeclarationsToDOM(Document doc, MediaSpec media, final boolean inherit) {

		// classify the rules
	    classifyAllSheets(media);
		
		// resulting map
		DeclarationMap declarations = new DeclarationMap();
		
        // if the holder is empty skip evaluation
        if(rules!=null && !rules.isEmpty()) {
            
    		Traversal<DeclarationMap> traversal = new Traversal<DeclarationMap>(
    				doc, (Object) rules, NodeFilter.SHOW_ELEMENT) {
    			protected void processNode(DeclarationMap result,
    					Node current, Object source) {
    				assignDeclarationsToElement(result, walker, (Element) current,
    						(Holder) source);
    			}
    		};
    
    		// list traversal will be enough
    		if (!inherit)
    			traversal.listTraversal(declarations);
    		// we will do level traversal to economize blind returning
    		// in tree
    		else
    			traversal.levelTraversal(declarations);
        }

		return declarations;
	}

	/**
	 * Assigns declarations to one element.
	 * 
	 * @param declarations
	 *            Declarations of all processed elements
	 * @param walker
	 *            Tree walker
	 * @param e
	 *            DOM Element
	 * @param holder
	 *            Wrap
	 */
	protected void assignDeclarationsToElement(
			DeclarationMap declarations, TreeWalker walker,
			Element e, Holder holder) {

		if(log.isDebugEnabled()) {
			log.debug("Traversal of {} {}.", e.getNodeName(), e.getNodeValue());
		}
		
		// create set of possible candidates applicable to given element
		// set is automatically filtered to not contain duplicates
		Set<OrderedRule> candidates = new HashSet<OrderedRule>();

		// match element classes
		for (String cname : matcher.elementClasses(e)) {
			// holder contains rule with given class
			List<OrderedRule> rules = holder.get(HolderItem.CLASS, cname.toLowerCase());
			if (rules != null)
				candidates.addAll(rules);
		}
		log.trace("After CLASSes {} total candidates.", candidates.size());

		// match IDs
		String id = matcher.elementID(e);
		if (id != null && id.length() != 0) {
			List<OrderedRule> rules = holder.get(HolderItem.ID, id.toLowerCase());
			if (rules != null)
				candidates.addAll(rules);
		}
		log.trace("After IDs {} total candidates.", candidates.size());
		
		// match elements
		String name = matcher.elementName(e);
		if (name != null) {
			List<OrderedRule> rules = holder.get(HolderItem.ELEMENT, name.toLowerCase());
			if (rules != null)
				candidates.addAll(rules);
		}
		log.trace("After ELEMENTs {} total candidates.", candidates.size());

		// others
		candidates.addAll(holder.get(HolderItem.OTHER, null));
		
	    // transform to list to speed up traversal
		// and sort rules in order as they were found in CSS definition
		List<OrderedRule> clist = new ArrayList<OrderedRule>(candidates);
		Collections.sort(clist);
		
		log.debug("Totally {} candidates.", candidates.size());
		log.trace("With values: {}", clist);

		// resulting list of declaration for this element with no pseudo-selectors (main list)(local cache)
		List<Declaration> eldecl = new ArrayList<Declaration>();
		
		// existing pseudo selectors found
		Set<PseudoElementType> pseudos = new HashSet<>();

		// for all candidates
		for (OrderedRule orule : clist) {
		    
			final RuleSet rule = orule.getRule();
			StyleSheet sheet = rule.getStyleSheet();
			if (sheet == null)
			    log.warn("No source style sheet set for rule: {}", rule.toString());
			StyleSheet.Origin origin = (sheet == null) ? StyleSheet.Origin.AGENT : sheet.getOrigin();
			
			// for all selectors inside
			for (CombinedSelector s : rule.getSelectors()) {
				// this method does automatic rewind of walker
				if (!matchSelector(s, e, walker)) {
					log.trace("CombinedSelector \"{}\" NOT matched!", s);
					continue;
				}

				log.trace("CombinedSelector \"{}\" matched", s);
				
				PseudoElementType pseudo = s.getPseudoElementType();
                CombinedSelector.Specificity spec = s.computeSpecificity();
				if (pseudo == null)
				{
    				// add to main list
    				for (Declaration d : rule)
    					eldecl.add(new AssignedDeclaration(d, spec, origin));
				}
				else
				{
				    // remember the pseudo element
				    pseudos.add(pseudo);
				    // add to pseudo lists
                    for (Declaration d : rule)
                        declarations.addDeclaration(e, pseudo, new AssignedDeclaration(d, spec, origin));
				}

			}
		}

		// sort declarations
		Collections.sort(eldecl); //sort the main list
		log.debug("Sorted {} declarations.", eldecl.size());
		log.trace("With values: {}", eldecl);
		for (PseudoElementType p : pseudos)
		    declarations.sortDeclarations(e, p); //sort pseudos
		
		// set the main list
		declarations.put(e, null, eldecl);
	}

	protected boolean elementSelectorMatches(final Selector s, final Element e) {
		return s.matches(e, matcher, matchCond);
	}

	protected boolean matchSelector(CombinedSelector sel, Element e, TreeWalker w) {

		// store current walker position
		Node current = w.getCurrentNode();

		boolean retval = false;
		Selector.Combinator combinator = null;
		// traverse simple selector backwards
		for (int i = sel.size() - 1; i >= 0; i--) {
			// last simple selector
			Selector s = sel.get(i);
			//log.trace("Iterating loop with selector {}, combinator {}",	s, combinator);

			// decide according to combinator anti-pattern
			if (combinator == null) {
				retval = this.elementSelectorMatches(s, e);
			} else if (combinator == Selector.Combinator.ADJACENT) {
				Element adjacent = (Element) w.previousSibling();
				retval = false;
				if (adjacent != null)
					retval = this.elementSelectorMatches(s, adjacent);
            } else if (combinator == Selector.Combinator.PRECEDING) {
                Element preceding;
                retval = false;
                while (!retval && (preceding = (Element) w.previousSibling()) != null) {
                    retval = this.elementSelectorMatches(s, preceding);
                }
			} else if (combinator == Selector.Combinator.DESCENDANT) {
                Element ancestor;
                retval = false;
                while (!retval && (ancestor = (Element) w.parentNode()) != null) {
                    retval = this.elementSelectorMatches(s, ancestor);
                }
			} else if (combinator == Selector.Combinator.CHILD) {
                Element parent = (Element) w.parentNode();
                retval = false;
                if (parent != null)
                    retval = this.elementSelectorMatches(s, parent);
			}

			// set combinator for next loop
			combinator = s.getCombinator();

			// leave loop if not matched
			if (!retval)
				break;
		}

		// restore walker position
		w.setCurrentNode(current);
		return retval;
	}

	/**
	 * Classifies the rules in all the style sheets.
	 * @param mediaspec The specification of the media for evaluating the media queries.
	 */
	protected void classifyAllSheets(MediaSpec mediaspec)
	{
	    rules = new Holder();

	    AnalyzerUtil.classifyAllSheets(sheets, rules, mediaspec);
	}
	
	/**
	 * Decides about holder item
	 * 
	 * @author kapy
	 */
	protected enum HolderItem {
		ELEMENT(0), ID(1), CLASS(2), OTHER(3);

		private int type;

		private HolderItem(int type) {
			this.type = type;
		}

		public int type() {
			return type;
		}
	}

	/**
	 * Holds holder item type and key value, that is two elements that are about
	 * to be used for storing in holder
	 * 
	 * @author kapy
	 * 
	 */
	protected static class HolderSelector {
		public HolderItem item;
		public String key;

		public HolderSelector(HolderItem item, String key) {
			this.item = item;
			this.key = key;
		}
	}

	/**
	 * Represents a ruleset and its order in the corresponding style sheet.
	 * 
	 * @author burgetr
	 */
	public static final class OrderedRule implements Comparable<OrderedRule> {
	    private final RuleSet rule;
        private final int order;
	    
        public OrderedRule(RuleSet rule, int order) {
            this.rule = rule;
            this.order = order;
        }

        public RuleSet getRule() {
            return rule;
        }

        public int getOrder() {
            return order;
        }

        public int compareTo(OrderedRule o) {
            return getOrder() - o.getOrder();
        }

        @Override
        public String toString() {
        	return "OR" + order + ", " + rule;
        }
        
	}
	
	/**
	 * Holds list of maps of list. This is used to classify rulesets into
	 * structure which is easily accessible by analyzator
	 * 
	 * @author kapy
	 * 
	 */
	public static class Holder {

		/** HolderItem.* except OTHER are stored there */
		private List<Map<String, List<OrderedRule>>> items;

		/** OTHER rules are stored there */
		private List<OrderedRule> others;

		public Holder() {
			// create list of items
			this.items = new ArrayList<Map<String, List<OrderedRule>>>(HolderItem.values().length - 1);

			// fill maps in list
			for (HolderItem hi : HolderItem.values()) {
				// this is special case, it is not map
				if (hi == HolderItem.OTHER)
					others = new ArrayList<OrderedRule>();
				else
					items.add(new HashMap<String, List<OrderedRule>>());
			}
		}

		public boolean isEmpty() {
			for(HolderItem hi: HolderItem.values()) {
				if(hi == HolderItem.OTHER) { 
					if(!others.isEmpty()) return false;
				}
				else if(!items.get(hi.type).isEmpty())
					return false;
			}			
			return true;
		}
		
		
		public static Holder union(Holder one, Holder two) {
			
			Holder union = new Holder();
			if(one==null) one = new Holder();
			if(two==null) two = new Holder();
			
			for(HolderItem hi: HolderItem.values()) {
				if(hi == HolderItem.OTHER) {
					union.others.addAll(one.others);
					union.others.addAll(two.others);
				}
				else {
					
					Map<String, List<OrderedRule>> oneMap, twoMap, unionMap;
					oneMap = one.items.get(hi.type);
					twoMap = two.items.get(hi.type);
					unionMap = union.items.get(hi.type);
					
					unionMap.putAll(oneMap);
					for(String key: twoMap.keySet()) {
						// map already contains this as key, append to list
						if(unionMap.containsKey(key)) {
							unionMap.get(key).addAll(twoMap.get(key));
						}
						// we could directly add elements
						else {
							unionMap.put(key, twoMap.get(key));
						}
					}
				}
				
			}
			return union;
		}
		
		/**
		 * Inserts Ruleset into group identified by HolderType, and optionally
		 * by key value
		 * 
		 * @param item
		 *            Identifier of holder's group
		 * @param key
		 *            Key, used in case other than OTHER
		 * @param value
		 *            Value to be store inside
		 */
		public void insert(HolderItem item, String key, OrderedRule value) {

			// check others and if so, insert item
			if (item == HolderItem.OTHER) {
				others.add(value);
				return;
			}

			// create list if empty
			Map<String, List<OrderedRule>> map = items.get(item.type);
			List<OrderedRule> list = map.get(key);
			if (list == null) {
				list = new ArrayList<OrderedRule>();
				map.put(key, list);
			}

			list.add(value);

		}

		/**
		 * Returns list of rules (ruleset) for given holder and key
		 * 
		 * @param item
		 *            Type of item to be returned
		 * @param key
		 *            Key or <code>null</code> in case of HolderItem.OTHER
		 * @return List of rules or <code>null</code> if not found under given
		 *         combination of key and item
		 */
		public List<OrderedRule> get(HolderItem item, String key) {

			// check others
			if (item == HolderItem.OTHER)
				return others;

			return items.get(item.type()).get(key);
		}
		
		
		public String contentCount(){
			StringBuilder sb = new StringBuilder();
			
			for(HolderItem hi: HolderItem.values()) {
				if(hi == HolderItem.OTHER) {
					sb.append(hi.name())
					  .append(": ")
					  .append(others.size())
					  .append(" ");
				}
				else {
					sb.append(hi.name())
					  .append(":")
					  .append(items.get(hi.type).size())
					  .append(" ");
				}
				
			}
			
			return sb.toString();
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			for(HolderItem hi: HolderItem.values()) {
				if(hi == HolderItem.OTHER) {
					sb.append(hi.name())
					  .append(" (")
					  .append(others.size())
					  .append("): ")
					  .append(others).append("\n");	
				}
				else {
					sb.append(hi.name())
					  .append(" (")
					  .append(items.get(hi.type).size())
					  .append("): ")
					  .append(items.get(hi.type)).append("\n");
				}
				
			}
			
			return sb.toString();
		}
	}
}
