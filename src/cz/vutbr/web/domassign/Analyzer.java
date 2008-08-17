package cz.vutbr.web.domassign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.Rule;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.csskit.ElementUtil;

/**
 * During initialization divides rules of stylesheet into maps accoring
 * to medias and their type. Afterwards it is able to return CSS declaration
 * for DOM tree and media. Allow to use or not to use inheritance.
 * @author Karel Piwko 2008,
 *
 */
public class Analyzer {

	private static final Logger log = Logger.getLogger(Analyzer.class);
	
	/**
	 * For all medias holds maps of declared rules classified into groups
	 * of HolderItem (ID, CLASS, ELEMENT, OTHER). Media's type is key
	 */
	protected Map<String, Holder> rules;

	public Analyzer(StyleSheet sheet) {
		this.rules = Collections
				.synchronizedMap(new HashMap<String, Holder>());
		classifyRules(sheet);
	}
	
	/**
	 * Creates map of declarations assigned to each element of a DOM tree
	 * @param doc DOM document
	 * @param media Media type to be used for declarations
	 * @param inherit Inheritance (cascade propagation of values)
	 * @return Map of elements as keys and their declarations
	 */
	public Map<Element,List<Declaration>> assingDeclarationsToDOM(Document doc, String media,
			boolean inherit) { 
		
		TreeWalker walker = new TidyTreeWalker(doc.getDocumentElement(),
				NodeFilter.SHOW_ELEMENT);
		Holder holder = rules.get(media); 
		
		// list traversal will be enough
		if(!inherit)
			return listTraversal(walker, holder);
		
		// we will do level traversal to economize blind returning 
		// in tree
		Map<Element, List<Declaration>> declarations =
			new HashMap<Element, List<Declaration>>();
		levelTraversal(declarations, walker, holder, 0);
		return declarations;
	}
	
	/**
	 * Evaluates CSS properties of DOM tree
	 * @param doc Document tree
	 * @param media Media
	 * @param inherit Use inheritance
	 * @return Map where each element contains its CSS properties
	 */
	public Map<Element, NodeData> evaluateDOM(Document doc, String media, boolean inherit) {
		
		Map<Element, List<Declaration>> declarations =
			assingDeclarationsToDOM(doc, media, inherit);
		
		Map<Element, NodeData> nodes = new HashMap<Element, NodeData>(declarations.size(), 1.0f);
		
		for(Element e: declarations.keySet()) {
			NodeData data = new NodeDataImpl();
						
			// get inheritance level
			// this should never rise NullPointerException
			List<Declaration> decl = declarations.get(e);
			int level = (decl.size()>=1) ?
					decl.get(decl.size()-1).getInheritanceLevel() : 0;
			// TODO inheritance
			for(Declaration d: decl)
				data.push(d);		
			
			nodes.put(e, data);
		}
		return nodes;	
	}
	
	/**
	 * Uses walker to flat traversal of DOM tree
	 * @param walker Tree walker
	 * @param holder Holder of rules for given media
	 * @return Map, where each DOM element has CSS declarations assigned
	 */
	private Map<Element,List<Declaration>> listTraversal(
			TreeWalker walker, Holder holder) {
		
		Map<Element, List<Declaration>> declarations = new
			HashMap<Element, List<Declaration>>();
		
		// tree traversal as nodes are found in
		Node current, checkpoint = null;
	    current = walker.nextNode();
	    while (current != null) {
	    
	    	// this method can change position in walker	
	    	checkpoint = walker.getCurrentNode();    	
	    	assignDeclarationsToElement(declarations, walker, (Element) current, holder, 0);
	    	walker.setCurrentNode(checkpoint);
	       
	    	current = walker.nextNode();
	    }
	    return declarations;
	}
	
	/**
	 * Uses walker to traverse DOM tree in level order. This means, that
	 * declarations of each predecessors are defined before Element itself
	 * is processed.
	 * @param declarations Map where to store declarations
	 * @param walker Tree walker
	 * @param holder Holder of rules for given media
	 * @param level Current nesting level
	 */
	private void levelTraversal(Map<Element, List<Declaration>> declarations, 
			TreeWalker walker, Holder holder, int level) {

		// this method can change position in walker
	    Node current, checkpoint = null;
	    current = checkpoint = walker.getCurrentNode();
	    assignDeclarationsToElement(declarations, walker, (Element) current, holder, level);
	    walker.setCurrentNode(checkpoint);
	    
	    // traverse children:
	    for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
	      levelTraversal(declarations, walker, holder, level + 1);
	    }

	    // return position to the current (level up):
	    walker.setCurrentNode(checkpoint);
		
	}
	
	/**
	 * Assigns declarations to one element
	 * @param declarations Declarations of all processed elements
	 * @param walker Tree walker
	 * @param e DOM Element
	 * @param holder Holder
	 * @param level Current level of walker, if 0, no inheritance is allowed
	 */
	private void assignDeclarationsToElement(
			Map<Element, List<Declaration>> declarations, 
			TreeWalker walker, Element e, Holder holder, int level) {
		
		if(log.isDebugEnabled()) {
			log.debug("Traversal(level " + level+ ") " + e.getNodeName()+":"
                + e.getNodeValue());
		}

		// create set of possible candidates applicable to given element
		// set is automatically filtered to not contain duplicates
		Set<RuleSet> candidates = new HashSet<RuleSet>();
		
		// match element classes
		for(String cname: ElementUtil.elementClasses(e)) {
			// holder contains rule with given class
			List<RuleSet> rules = holder.get(HolderItem.CLASS,cname);
			if(rules!=null)
				candidates.addAll(rules);
		}
		
		if(log.isTraceEnabled()) {
			log.trace("After CLASSes total candidates: " + candidates.size());
		}
		
		// match IDs
		String id = ElementUtil.elementID(e);
		if(id!=null) {
			List<RuleSet> rules = holder.get(HolderItem.ID,id);
			if(rules!= null)
				candidates.addAll(rules);
		}
		
		if(log.isTraceEnabled()) {
			log.trace("After IDs total candidates: " + candidates.size());
		}
		
		// match elements
		String name = ElementUtil.elementName(e);
		if(name!=null) {
			List<RuleSet> rules = holder.get(HolderItem.ELEMENT, name);
			if(rules!=null)
				candidates.addAll(rules);
		}
		
		if(log.isTraceEnabled()) {
			log.trace("After ELEMENTs total candidates: " + candidates.size());
		}
		
		// others
		candidates.addAll(holder.get(HolderItem.OTHER, null));
		
		// transform to list to speed up traversal
		// and sort rules in order as they were found in CSS definition
		List<RuleSet> clist = new ArrayList<RuleSet>(candidates);
		Collections.sort(clist);
		
		if(log.isDebugEnabled()) {
			log.debug("Total canditates (including OTHERs): " + clist.size());
			if(log.isTraceEnabled()) {
				log.trace("Condidates: " + clist);
			}
		}
		
		// resulting list of declaration for this element
		List<Declaration> eldecl = new ArrayList<Declaration>();
		
		// for all candidates
		for(RuleSet rule: clist) {
			// for all selectors inside
			for(CombinedSelector s: rule.getSelectors()) {
				// this method does automatic rewind of walker 
				if(!matchSelector(s, e, walker))
					continue;
				
				log.trace("CombinedSelector matched, adding declarations");
				
				//if match, add to declarations				
				CombinedSelector.Specificity spec = s.computeSpecificity();				
				for(Declaration d: rule)
					eldecl.add(new AssignedDeclaration(d, spec, level));
				
			}
		}
		
		// inheritance, level is always 0 when not in inheritance mode
		// TODO: parent node could be searched only once per child, because
		// it should be known by level traversal
		if(level > 0) {
			Node checkpoint = walker.getCurrentNode();
			for(int i = level; i>level-1; i--) {
				eldecl.addAll(
						declarations.get((Element)walker.parentNode()));
			}
			walker.setCurrentNode(checkpoint);
		}
	
		// sort declarations
		Collections.sort(eldecl);
		
		if(log.isDebugEnabled()) {
			log.debug("Assorted " + eldecl.size() + "declarations");
			if(log.isTraceEnabled()) {
				log.trace(eldecl);
			}
		}
		
		declarations.put(e, eldecl);	
	}
	
	private boolean matchSelector(CombinedSelector sel, Element e, TreeWalker w) {
		
		// store current walker position
		Node current = w.getCurrentNode();
		
		boolean retval = false;
		Selector.Combinator combinator = null;
		// traverse simple selector backwards
		for(int i= sel.size()-1; i>=0; i--) {
			// last simple selector
			Selector s = sel.get(i);
			
			if(log.isTraceEnabled()) {
				log.trace("Iterating loop with sel: " + s +
						" combinator " + combinator );
			}
			
			// decide according to combinator anti-pattern
			if(combinator==null) {
				retval = s.matches(e);
			}
			else if(combinator==Selector.Combinator.ADJACENT) {
				Element adjacent = (Element) w.previousSibling();
				retval = false;
				if(adjacent!=null) retval = s.matches(adjacent);
			}
			else if(combinator==Selector.Combinator.DESCENDANT) {
				Element parent = (Element) w.parentNode();
				retval = false;
				if(parent!=null) retval = s.matches(parent);
			}
			else if(combinator==Selector.Combinator.CHILD) {
				Element ancestor;
				retval = false;
				while((ancestor=(Element)w.parentNode())!=null && retval==false) {
					retval = s.matches(ancestor);
				}
			}
			
			// set combinator for next loop
			combinator = s.getCombinator();
			
			// set walker for next loop
			w.setCurrentNode(current);	
			
			// leave loop if not matched
			if(retval==false)
				break;
		}
		
		// restore walker position
		w.setCurrentNode(current);
		return retval;
	}	
	
	/**
	 * Divides rules in sheet into different categories
	 * to be easily and more quickly parsed afterward
	 * @param sheet
	 */
	private void classifyRules(StyleSheet sheet) {

		// create holder for medium of type all
		Holder all = new Holder();
		rules.put("all", all);
		
		for(Rule<?> rule: sheet) {
			// this rule conforms to all media
			if(rule instanceof RuleSet) {
				RuleSet ruleset = (RuleSet) rule;
				for(CombinedSelector s: ruleset.getSelectors()) {
					insertClassified(all, classifySelector(s), ruleset);
				}
			}
			// this rule conforms to different media
			if(rule instanceof RuleMedia) {
				RuleMedia rulemedia = (RuleMedia) rule;
				
				// for all rules in media set   
				for(RuleSet ruleset : rulemedia) {
					// for all selectors in there
					for(CombinedSelector s: ruleset.getSelectors()) {
						
						List<HolderSelector> hs = classifySelector(s);
						// insert into all medias
						for(String media: rulemedia.getMedias()) {
							Holder h = rules.get(media);
							if(h==null) {
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
		if(log.isDebugEnabled()) {
			log.debug("Rules contains rules for " + rules.size() + " different medias:");
			for(String media: rules.keySet()) {
				log.debug("For media: " + media);
				
				log.debug("CLASS: " + rules.get(media).items.get(HolderItem.CLASS.type()).size() +
						" ID: " + rules.get(media).items.get(HolderItem.ID.type()).size() +
						" ELEMENT: " + rules.get(media).items.get(HolderItem.ELEMENT.type()).size() +
						" OTHER: " + rules.get(media).others.size());
				
				if(log.isTraceEnabled()) {
					log.trace("CLASS: " + rules.get(media).items.get(HolderItem.CLASS.type()));
					log.trace("ID: " + rules.get(media).items.get(HolderItem.ID.type()));
					log.trace("ELEMENT: " + rules.get(media).items.get(HolderItem.ELEMENT.type()));
					log.trace("OTHER:" + rules.get(media).others);
				}
				
			}
		}
		
	}

	/**
	 * Classify CSS rule according its selector for to be of specified item(s)
	 * @param CombinedSelector CombinedSelector of rules
	 * @return List of HolderSelectors to which selectors conforms
	 */
	private List<HolderSelector> classifySelector(CombinedSelector selector) {

		List<HolderSelector> hs = new ArrayList<HolderSelector>();
		
		try {
			// last simple selector decided about all selector
			Selector last = selector.getLastSelector();

			// is element or other (wildcard)
			String element = last.getElementName();
			if(element!=null) {
				// wildcard
				if(Selector.SelectorPart.WILDCARD.equals(element)) 
					hs.add(new HolderSelector(HolderItem.OTHER, null));
				// element
				else
					hs.add(new HolderSelector(HolderItem.ELEMENT,element.toLowerCase()));
			}

			// is class name
			String className = last.getClassName();
			if(className!=null)
				hs.add(new HolderSelector(HolderItem.CLASS, className.toLowerCase()));

			// is id
			String id = last.getIDName();
			if(id!=null)
				hs.add(new HolderSelector(HolderItem.ID, id.toLowerCase()));

			// is in others
			if(hs.size()==0)
				hs.add(new HolderSelector(HolderItem.OTHER, null));
			
			return hs;
			
		}
		catch(UnsupportedOperationException e) {
			log.error("CombinedSelector does not include any simple selector, this should not happen!");
			return Collections.emptyList();
		}
	}	
	
	/**
	 * Inserts rules into holder
	 * @param holder Holder to be inserted
	 * @param hs Holder's selector and key
	 * @param value Value to be inserted
	 */
	private void insertClassified(Holder holder, List<HolderSelector> hs, RuleSet value) {
		for(HolderSelector h: hs)
			holder.insert(h.item, h.key, value);
	}
	
	/**
	 * Decides about holder item
	 * 
	 * @author kapy
	 */
	private enum HolderItem {
		ELEMENT(0), ID(1), CLASS(2), 
		OTHER(3);

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
	private class HolderSelector {
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
	private class Holder {

		/** HolderItem.* except OTHER are stored there */
		private List<Map<String, List<RuleSet>>> items;

		/** OTHER rules are stored there */
		private List<RuleSet> others;

		public Holder() {
			// create list of items
			this.items = new ArrayList<Map<String, List<RuleSet>>>(
					HolderItem.values().length - 1);

			// fill maps in list
			for (HolderItem hi : HolderItem.values()) {
				// this is special case, it is not map
				if (hi == HolderItem.OTHER)
					others = new ArrayList<RuleSet>();
				else
					items.add(new HashMap<String, List<RuleSet>>());
			}
		}

		/**
		 * Inserts Ruleset into group identified by HolderType,
		 * and optionally by key value
		 * @param item Identifier of holder's group
		 * @param key Key, used in case other than OTHER 
		 * @param value Value to be store inside
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
		 * @param item Type of item to be returned
		 * @param key Key or <code>null</code> in case of HolderItem.OTHER
		 * @return List of rules or <code>null</code> if not found under given 
		 * combination of key and item
		 */
		public List<RuleSet> get(HolderItem item, String key) {
			
			// check others
			if(item == HolderItem.OTHER)
				return others;
			
			return items.get(item.type()).get(key);
		}
	}
}
