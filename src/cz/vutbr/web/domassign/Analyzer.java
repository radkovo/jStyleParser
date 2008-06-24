package cz.vutbr.web.domassign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.OperationNotSupportedException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.Rule;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.SimpleSelector;
import cz.vutbr.web.css.StyleSheet;

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
	
	public Map<Element,List<AssignedDeclaration>> assingDeclarationsToDOM(Document doc, String media,
			boolean inherit) { 
		
		TreeWalker walker = new TidyTreeWalker(doc.getDocumentElement(),
				NodeFilter.SHOW_ELEMENT);
		Holder holder = rules.get(media); 
		
		// list traversal will be enough
		if(!inherit)
			return listTraversal(walker, holder);
		
		// we will do level traversal to economize blind returning 
		// in tree
		Map<Element, List<AssignedDeclaration>> declarations =
			new HashMap<Element, List<AssignedDeclaration>>();
		levelTraversal(declarations, walker, holder, 0);
		return declarations;
	}
	
	/**
	 * Uses walker to flat traversal of DOM tree
	 * @param walker Tree walker
	 * @param holder Holder of rules for given media
	 * @return Map, where each DOM element has CSS declarations assigned
	 */
	private Map<Element,List<AssignedDeclaration>> listTraversal(
			TreeWalker walker, Holder holder) {
		
		Map<Element, List<AssignedDeclaration>> declarations = new
			HashMap<Element, List<AssignedDeclaration>>();
		
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
	private void levelTraversal(Map<Element, List<AssignedDeclaration>> declarations, 
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
			Map<Element, List<AssignedDeclaration>> declarations, 
			TreeWalker walker, Element e, Holder holder, int level) {
		
		if(log.isDebugEnabled()) {
			log.debug("Traversal(level " + level+ ") " + e.getNodeName()+":"
                + e.getNodeValue());
		}

		// candidates
		Set<RuleSet> candidates = new HashSet<RuleSet>();
		
		// match element classes
		for(String cname: ElementUtil.elementClasses(e)) {
			// holder contains rule with given class
			final List<RuleSet> rules = holder.items.get(HolderItem.CLASS.type()).get(cname);
			if(rules!=null)
				candidates.addAll(rules);
		}
		
		if(log.isTraceEnabled()) {
			log.trace("After CLASSes total candidates: " + candidates.size());
		}
		
		// match ids
		final String id = ElementUtil.elementID(e);
		if(id!=null) {
			final List<RuleSet> rules = holder.items.get(HolderItem.ID.type()).get(id);
			if(rules!= null)
				candidates.addAll(rules);
		}
		
		if(log.isTraceEnabled()) {
			log.trace("After IDes total candidates: " + candidates.size());
		}
		
		// match elements
		final String name = ElementUtil.elementName(e);
		if(name!=null) {
			final List<RuleSet> rules = holder.items.get(HolderItem.ELEMENT.type()).get(name);
			if(rules!=null)
				candidates.addAll(rules);
		}
		
		if(log.isTraceEnabled()) {
			log.trace("After ELEMENTs total candidates: " + candidates.size());
		}
		
		// others
		candidates.addAll(holder.others);
		
		// transform to list to speed up traversal
		// and sort rules as they were found in CSS definition
		List<RuleSet> clist = new ArrayList<RuleSet>(candidates);
		Collections.sort(clist);
		
		if(log.isDebugEnabled()) {
			log.debug("Total canditates (including OTHERs: " + clist.size());
			if(log.isTraceEnabled()) {
				log.trace("Condidates: " + clist);
			}
		}
		
		// resulting list of declaration for this element
		List<AssignedDeclaration> eldecl = new ArrayList<AssignedDeclaration>();
		
		// for all canditates
		for(RuleSet rule: clist) {
			// for all selectors inside
			for(Selector s: rule.getSelectors()) {
				//if match, add to declarations				
				Selector.Specificity spec = s.computeSpecificity();				
				for(Declaration d: rule.getDeclarations())
					eldecl.add(new AssignedDeclaration(d, spec, level));
				
			}
		}
		
		// inheritance
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
	

	private void classifyRules(StyleSheet sheet) {

		// create holder for medium of type all
		Holder all = new Holder();
		rules.put("all", all);
		
		for(Rule rule: sheet.getRules()) {
			// this rule conforms to all media
			if(rule instanceof RuleSet) {
				final RuleSet ruleset = (RuleSet) rule;
				for(Selector s: ruleset.getSelectors()) {
					insertClassified(all, classifySelector(s), ruleset);
				}
			}
			// this rule conforms to different media
			if(rule instanceof RuleMedia) {
				final RuleMedia rulemedia = (RuleMedia) rule;
				
				// for all rules in media set   
				for(RuleSet ruleset : rulemedia.getRules()) {
					// for all selectors in there
					for(Selector s: ruleset.getSelectors()) {
						
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
	 * Classify rule for to be of specified item
	 */
	private List<HolderSelector> classifySelector(Selector selector) {

		List<HolderSelector> hs = new ArrayList<HolderSelector>();
		
		try {
			// last simple selector decided about all selector
			SimpleSelector last = selector.getLastSimpleSelector();

			// is element or other (wildcard)
			String element = last.getElementName();
			if(element!=null) {
				// wildcard
				if("*".equals(element)) 
					// TODO check this behaviour
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
				hs.add(new HolderSelector(HolderItem.CLASS, id.toLowerCase()));

			// is in others
			if(hs.size()==0)
				hs.add(new HolderSelector(HolderItem.OTHER, null));
			
			return hs;
			
		}
		catch(OperationNotSupportedException e) {
			log.warn("Selector does not include any simple selector, this should not happen!");
			return Collections.emptyList();
		}
	}	
	
	
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
		public List<Map<String, List<RuleSet>>> items;

		/** OTHER rules are stored there */
		public List<RuleSet> others;

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
	}
	
	/**
	 * Kontroluje jeden Selector proti danému elementu. Pokud Selector obsahuje
	 * více SimpleSelectorů, ohybuje se po stromové struktuře v závislosti na
	 * kombinátorech
	 */
	/*
	private static boolean matchElementBySelector(Element el, Selector s) {
		boolean match = true;
		Element actualPos = el;
		SimpleSelector.EnumCombinator combinator = null;
		// Procházím všechny SimpleSelectory v opačném pořadí
		for (int i = s.getSimpleSelectorsList().size() - 1; i >= 0; i--) {
			SimpleSelector ss = s.getSimpleSelectorsList().get(i);

			// Kontroluje se vždy v prvním kole na element který vstupuje do
			// funkce
			if (combinator == null) {
				if (!(matchElementBySimpleSelector(actualPos, ss))) {
					match = false;
					break;
				}
			}

			// kombinátor mezery, hledám výše v hierarchii výše odpovídající
			// SimpleSelecor
			else if (combinator == SimpleSelector.EnumCombinator.space) {
				match = false;
				if (actualPos != null) {
					actualPos = (actualPos.getParentNode() instanceof Element ? (Element) actualPos
							.getParentNode()
							: null);
					while (actualPos != null && !match) {
						match = matchElementBySimpleSelector(actualPos, ss);
						if (!match) {
							actualPos = (actualPos.getParentNode() instanceof Element ? (Element) actualPos
									.getParentNode()
									: null);
						}
					}
				}
			}

			// kombinátor "větší než" - stejné jako předchozí (space), jen musí
			// být hledaný element
			// v hierarchii právě o jeden stupeň výše
			else if (combinator == SimpleSelector.EnumCombinator.greater) {
				match = false;
				if (actualPos != null) {
					actualPos = (Element) actualPos.getParentNode();
					match = matchElementBySimpleSelector(actualPos, ss);
				}
			}

			// kombinátor plus - elementy musí ležet hned vedle sebe
			else if (combinator == SimpleSelector.EnumCombinator.plus) {
				match = false;
				Element parent = (actualPos.getParentNode() instanceof Element ? (Element) actualPos
						.getParentNode()
						: null);
				if (parent != null) {
					// Nejdříve najdu svého přímého předchůdce
					Element pre = null;
					for (int ii = 0; ii < parent.getChildNodes().getLength(); ii++) {
						Node n = parent.getChildNodes().item(ii);
						if (n == actualPos) {
							break;
						} else if (n instanceof Element) {
							pre = (Element) n;
						}
					}
					// Pokud byl předchůdce nalezen, ověřím ho (pokud ne, match
					// zůstává na false)
					if (pre != null) {
						match = matchElementBySimpleSelector(pre, ss);
						actualPos = pre;
					}
				}
			}
			combinator = ss.getCombinator();

			// Pokud Selector něčím neodpovídá, nemá cenu zkoušet další - vrátím
			// false
			if (!match) {
				return match;
			}
		}
		return match;
	}
*/
	/**
	 * Zkontroluje jeden SimpleSelector - porovnávání probíhá na jednom elementu
	 * (není třeba kontrolovat kontext)
	 */
	/*
	private static boolean matchElementBySimpleSelector(Element el,
			SimpleSelector s) {
		// Je-li zadán typ elementu, zkontroluje se
		if (s.getFirstItem() != null) {
			if (!(el.getNodeName()
					.equalsIgnoreCase(s.getFirstItem().getValue()) || s
					.getFirstItem().getValue().equals("*"))) {
				return false;
			}
		}

		// Kontrola ostatních položek SimpleSelectoru. Položky pseudo nelze
		// porovnávat, protože pseudoelementy vznikají až
		// při rasterizaci dokumentu (například "první řádek" nebo :hover - "nad
		// objektem je myš")
		for (SSItem item : s.getItemsList()) {

			if (item instanceof SSItemID) {
				String id = el.getAttribute("id");
				if (!(id.equalsIgnoreCase(((SSItemID) item).getValue()))) {
					return false;
				}
				continue;
			}

			if (item instanceof SSItemClass) {
				String classNames = el.getAttribute("class");
				boolean found = false;
				if (classNames != null) {
					for (String className : classNames.split(" ")) {
						if (className.trim().length() > 0) {
							if ((className
									.equalsIgnoreCase(((SSItemClass) item)
											.getValue()))) {
								found = true;
							}
						}
					}
				}
				if (!found) {
					return false;
				}
				continue;
			}

			if (item instanceof SSItemAttrib) {
				SSItemAttrib ssa = (SSItemAttrib) item;
				String attrib = el.getAttribute((ssa).getAttrib());
				if (ssa.getOperator() == null) {
					if (attrib.length() == 0) {
						return false;
					}
				} else if (ssa.getOperator() == SSItemAttrib.EnumOperator.equals) {
					if (!attrib.equalsIgnoreCase(ssa.getValue())) {
						return false;
					}
				} else if (ssa.getOperator() == SSItemAttrib.EnumOperator.includes) {
					attrib = " " + attrib + " ";
					if (!attrib.toLowerCase().matches(
							".* " + ssa.getValue() + " .*")) {
						return false;
					}
				} else if (ssa.getOperator() == SSItemAttrib.EnumOperator.dashmatch) {
					if (!attrib.toLowerCase().matches(
							"^" + ssa.getValue() + "(\\|.*)*")) {
						return false;
					}
				}
			}

			if (item instanceof SSItemPseudo) {
				// Ignorováno
				return false;
			}
		}
		return true;
	}
	*/
}
