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
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.Rule;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.Selector.PseudoDeclaration;
import cz.vutbr.web.csskit.ElementUtil;

/**
 * Analyzer allows to apply the given style to any document.
 * During the initialization, it divides rules of stylesheet into maps accoring to
 * medias and their type. Afterwards, it is able to return CSS declaration for any
 * DOM tree and media. It allows to use or not to use inheritance.
 * 
 * @author Karel Piwko 2008,
 * 
 */
public class Analyzer {

	private static final Logger log = LoggerFactory.getLogger(Analyzer.class);

	protected static final String UNIVERSAL_HOLDER = "all";
	
	/**
	 * For all medias holds maps of declared rules classified into groups of
	 * HolderItem (ID, CLASS, ELEMENT, OTHER). Media's type is key
	 */
	protected Map<String, Holder> rules;

	/**
	 * Creates the analyzer for a single style sheet.
	 * @param sheet The stylesheet that will be used as the source of rules.
	 */
	public Analyzer(StyleSheet sheet) {
		this.rules = Collections.synchronizedMap(new HashMap<String, Holder>());
		classifyRules(sheet);
	}

	/**
	 * Creates the analyzer for multiple style sheets.
	 * @param sheets A list of stylesheets that will be used as the source of rules.
	 */
	public Analyzer(List<StyleSheet> sheets) {
		this.rules = Collections.synchronizedMap(new HashMap<String, Holder>());
		for (StyleSheet sheet : sheets)
			classifyRules(sheet);
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
	public StyleMap evaluateDOM(Document doc, String media,
			final boolean inherit) {

		DeclarationMap declarations = assingDeclarationsToDOM(doc, media, inherit);

		StyleMap nodes = new StyleMap(declarations.size());

		Traversal<StyleMap> traversal = new Traversal<StyleMap>(
				doc, (Object) declarations, NodeFilter.SHOW_ELEMENT) {
			
			@Override
			protected void processNode(StyleMap result, Node current, Object source) {

			    NodeData main = CSSFactory.createNodeData();
			    
			    if (current instanceof Element && ((Element) current).getAttribute("id").equals("mojo"))
			        System.out.println("jo!");
			    
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
				for (PseudoDeclaration pseudo : ((DeclarationMap) source).pseudoSet((Element) current))
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
	protected DeclarationMap assingDeclarationsToDOM(Document doc, String media, final boolean inherit) {

		// get holder
		Holder holder;
		if(UNIVERSAL_HOLDER.equals(media)) 
			holder = rules.get(UNIVERSAL_HOLDER);
		else 
			holder = Holder.union(rules.get(UNIVERSAL_HOLDER), rules.get(media));
		
		// log holder content
		if(log.isTraceEnabled()) {
			log.trace("For media \"{}\" constructed holder:\n {}", media, holder);
		}
		
		// resulting map
		DeclarationMap declarations = new DeclarationMap();
		
        // if holder is empty skip evaluation
        if(holder!=null && !holder.isEmpty()) {
            
    		Traversal<DeclarationMap> traversal = new Traversal<DeclarationMap>(
    				doc, (Object) holder, NodeFilter.SHOW_ELEMENT) {
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
		Set<RuleSet> candidates = new HashSet<RuleSet>();

		// match element classes
		for (String cname : ElementUtil.elementClasses(e)) {
			// holder contains rule with given class
			List<RuleSet> rules = holder.get(HolderItem.CLASS, cname.toLowerCase());
			if (rules != null)
				candidates.addAll(rules);
		}
		log.trace("After CLASSes {} total candidates.", candidates.size());

		// match IDs
		String id = ElementUtil.elementID(e);
		if (id != null && id.length() != 0) {
			List<RuleSet> rules = holder.get(HolderItem.ID, id.toLowerCase());
			if (rules != null)
				candidates.addAll(rules);
		}
		log.trace("After IDs {} total candidates.", candidates.size());
		
		// match elements
		String name = ElementUtil.elementName(e);
		if (name != null) {
			List<RuleSet> rules = holder.get(HolderItem.ELEMENT, name.toLowerCase());
			if (rules != null)
				candidates.addAll(rules);
		}
		log.trace("After ELEMENTs {} total candidates.", candidates.size());

		// others
		candidates.addAll(holder.get(HolderItem.OTHER, null));

		// transform to list to speed up traversal
		// and sort rules in order as they were found in CSS definition
		List<RuleSet> clist = new ArrayList<RuleSet>(candidates);
		Collections.sort(clist);

		log.debug("Totally {} candidates.", candidates.size());
		log.trace("With values: {}", clist);

		// resulting list of declaration for this element with no pseudo-selectors (main list)(local cache)
		List<Declaration> eldecl = new ArrayList<Declaration>();
		
		// existing pseudo selectors found
		Set<PseudoDeclaration> pseudos = new HashSet<PseudoDeclaration>();

		// for all candidates
		for (RuleSet rule : clist) {
			
			StyleSheet sheet = rule.getStyleSheet();
			StyleSheet.Origin origin = (sheet == null) ? StyleSheet.Origin.AGENT : sheet.getOrigin();
			
			// for all selectors inside
			for (CombinedSelector s : rule.getSelectors()) {
				// this method does automatic rewind of walker
				if (!matchSelector(s, e, walker)) {
					log.trace("CombinedSelector \"{}\" NOT matched!", s);
					continue;
				}

				log.trace("CombinedSelector \"{}\" matched", s);
				
				PseudoDeclaration pseudo = s.getPseudoElement();
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
		for (PseudoDeclaration p : pseudos)
		    declarations.sortDeclarations(e, p); //sort pseudos
		
		// set the main list
		declarations.put(e, null, eldecl);
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
				retval = s.matches(e);
			} else if (combinator == Selector.Combinator.ADJACENT) {
				Element adjacent = (Element) w.previousSibling();
				retval = false;
				if (adjacent != null)
					retval = s.matches(adjacent);
			} else if (combinator == Selector.Combinator.DESCENDANT) {
                Element ancestor;
                retval = false;
                while (!retval && (ancestor = (Element) w.parentNode()) != null) {
                    retval = s.matches(ancestor);
                }
			} else if (combinator == Selector.Combinator.CHILD) {
                Element parent = (Element) w.parentNode();
                retval = false;
                if (parent != null)
                    retval = s.matches(parent);
			}

			// set combinator for next loop
			combinator = s.getCombinator();

			// leave loop if not matched
			if (retval == false)
				break;
		}

		// restore walker position
		w.setCurrentNode(current);
		return retval;
	}

	/**
	 * Divides rules in sheet into different categories to be easily and more
	 * quickly parsed afterward
	 * 
	 * @param sheet
	 */
	private void classifyRules(StyleSheet sheet) {

		// create holder for medium of type all if it does not exist
		Holder all = rules.get(UNIVERSAL_HOLDER);
		if (all == null) {
			all = new Holder();
			rules.put(UNIVERSAL_HOLDER, all);
		}

		for (Rule<?> rule : sheet) {
			// this rule conforms to all media
			if (rule instanceof RuleSet) {
				RuleSet ruleset = (RuleSet) rule;
				for (CombinedSelector s : ruleset.getSelectors()) {
					insertClassified(all, classifySelector(s), ruleset);
				}
			}
			// this rule conforms to different media
			if (rule instanceof RuleMedia) {
				RuleMedia rulemedia = (RuleMedia) rule;

				// for all rules in media set
				for (RuleSet ruleset : rulemedia) {
					// for all selectors in there
					for (CombinedSelector s : ruleset.getSelectors()) {

						List<HolderSelector> hs = classifySelector(s);
						// insert into all medias
						
						// if there are no medias, it equals all
						if(rulemedia.getMedia()==null ||
								rulemedia.getMedia().isEmpty()) {
							
							insertClassified(rules.get(UNIVERSAL_HOLDER), hs, ruleset);
							continue;
						}
						
						for (String media : rulemedia.getMedia()) {
							Holder h = rules.get(media);
							if (h == null) {
								h = new Holder();
								rules.put(media, h);
							}
							insertClassified(h, hs, ruleset);
						}
					}
				}
			}
		}

		// logging
		if (log.isDebugEnabled()) {
			log.debug("Contains rules for {} medias.", rules.size());
			for (String media : rules.keySet()) {
				log.debug("For media \"{}\" it is {}", media, rules.get(media).contentCount());
				if(log.isTraceEnabled()) {
					log.trace("Detailed view: \n{}", rules.get(media));
				}
			}
		}

	}

	/**
	 * Classify CSS rule according its selector for to be of specified item(s)
	 * 
	 * @param selector
	 *            CombinedSelector of rules
	 * @return List of HolderSelectors to which selectors conforms
	 */
	private List<HolderSelector> classifySelector(CombinedSelector selector) {

		List<HolderSelector> hs = new ArrayList<HolderSelector>();

		try {
			// last simple selector decided about all selector
			Selector last = selector.getLastSelector();

			// is element or other (wildcard)
			String element = last.getElementName();
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
			String className = last.getClassName();
			if (className != null)
				hs.add(new HolderSelector(HolderItem.CLASS, className
						.toLowerCase()));

			// is id
			String id = last.getIDName();
			if (id != null)
				hs.add(new HolderSelector(HolderItem.ID, id.toLowerCase()));

			// is in others
			if (hs.size() == 0)
				hs.add(new HolderSelector(HolderItem.OTHER, null));

			return hs;

		} catch (UnsupportedOperationException e) {
			log
					.error("CombinedSelector does not include any selector, this should not happen!");
			return Collections.emptyList();
		}
	}

	/**
	 * Inserts rules into holder
	 * 
	 * @param holder
	 *            Wrap to be inserted
	 * @param hs
	 *            Wrap's selector and key
	 * @param value
	 *            Value to be inserted
	 */
	private void insertClassified(Holder holder, List<HolderSelector> hs,
			RuleSet value) {
		for (HolderSelector h : hs)
			holder.insert(h.item, h.key, value);
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
	protected class HolderSelector {
		public HolderItem item;
		public String key;

		public HolderSelector(HolderItem item, String key) {
			this.item = item;
			this.key = key;
		}
	}

	/**
	 * Holds list of maps of list. This is used to classify rulesets into
	 * structure which is easily accessible by analyzator
	 * 
	 * @author kapy
	 * 
	 */
	protected static class Holder {

		/** HolderItem.* except OTHER are stored there */
		private List<Map<String, List<RuleSet>>> items;

		/** OTHER rules are stored there */
		private List<RuleSet> others;

		public Holder() {
			// create list of items
			this.items = new ArrayList<Map<String, List<RuleSet>>>(HolderItem
					.values().length - 1);

			// fill maps in list
			for (HolderItem hi : HolderItem.values()) {
				// this is special case, it is not map
				if (hi == HolderItem.OTHER)
					others = new ArrayList<RuleSet>();
				else
					items.add(new HashMap<String, List<RuleSet>>());
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
					
					Map<String, List<RuleSet>> oneMap, twoMap, unionMap;
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
		public void insert(HolderItem item, String key, RuleSet value) {

			// check others and if so, insert item
			if (item == HolderItem.OTHER) {
				others.add(value);
				return;
			}

			// create list if empty
			Map<String, List<RuleSet>> map = items.get(item.type);
			List<RuleSet> list = map.get(key);
			if (list == null) {
				list = new ArrayList<RuleSet>();
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
		public List<RuleSet> get(HolderItem item, String key) {

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
