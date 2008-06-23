package cz.vutbr.web.domassign;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.Rule;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.SimpleSelector;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StyleSheetNotValidException;

public class Analyzer {

	private static final Logger log = Logger.getLogger(Analyzer.class);
	
	protected Map<Element, List<AssignedDeclaration>> declarations;

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

	private void classifyRules(StyleSheet sheet) {

		// create holder for media of type all
		Holder all = new Holder();
		rules.put("all", all);
		
		for(Rule rule: sheet.getRules()) {
			// this rule conforms to all media
			if(rule instanceof RuleSet) {
				final RuleSet ruleset = (RuleSet) rule;
				for(Selector s: ruleset.getSelectors()) {
					HolderSelector hs = classifySelector(s);
					all.insert(hs.item, hs.key, ruleset);
				}
			}
			// this rule conforms to different medias
			if(rule instanceof RuleMedia) {
				final RuleMedia rulemedia = (RuleMedia) rule;
				
				// for all rules in media set   
				for(RuleSet ruleset : rulemedia.getRules()) {
					// for all selectors in there
					for(Selector s: ruleset.getSelectors()) {
						
						HolderSelector hs = classifySelector(s);
						// insert into all medias
						for(String media: rulemedia.getMedias()) {
							Holder h = rules.get(media);
							if(h==null) {
								h = new Holder();
								rules.put(media, h);
							}
							h.insert(hs.item, hs.key, ruleset);
						}
					}
				}
			}
		}
		
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
	private HolderSelector classifySelector(Selector selector) {
		
		try {
			// last simple selector decided about all selector
			SimpleSelector last = selector.getLastSimpleSelector();

			// is element or other (wildcard)
			String element = last.getElementName();
			if(element != null) {
				// wildcard
				if("*".equals(element))
					return new HolderSelector(HolderItem.OTHER, null);
				// element
				else
					return new HolderSelector(HolderItem.ELEMENT,element.toLowerCase());
			}

			// is class name
			String className = last.getClassName();
			if(className!=null)
				return new HolderSelector(HolderItem.CLASS, className.toLowerCase());

			// is id
			String id = last.getIDName();
			if(id!=null)
				return new HolderSelector(HolderItem.CLASS, id.toLowerCase());

			// is in others
			return new HolderSelector(HolderItem.OTHER, null);
		}
		catch(OperationNotSupportedException e) {
			log.warn("Selector does not include any simple selector, this should not happen!");
			return new HolderSelector(HolderItem.OTHER, null);
		}
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

		public List<Map<String, List<RuleSet>>> items;

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

	
	/*
	protected static HashMap<Element, ArrayList<AssignedDeclaration>> analyze(
			Document doc, StyleSheet styleSheet, String media)
			throws StyleSheetNotValidException {
		styleSheet.check();
		HashMap<Element, ArrayList<AssignedDeclaration>> assignedDeclarations = new HashMap<Element, ArrayList<AssignedDeclaration>>();

		ArrayList<OrderedRule> rules = new ArrayList<OrderedRule>();

		// Zpracování pravidel - odfiltrování pravidel určených pro jiný typ
		// média, vložení všech do jednoho seznamu (ArrayList rules)
		// Pravidla získávají při vkládání do kolekce pořadové číslo, aby bylo
		// možné zachovat jejich výchozí pořadí i při
		// rozdělení do map
		int orderNum = 0;
		for (Rule rule : styleSheet.getRules()) {
			if (rule instanceof RuleSet) {
				rules.add(new OrderedRule((RuleSet) rule, orderNum++));
			} else if (rule instanceof RuleMedia) {
				boolean ruleAccepted = false;
				for (String ruleMedia : ((RuleMedia) rule).getMedias()) {
					if (ruleMedia.equalsIgnoreCase(media)
							|| ruleMedia.equalsIgnoreCase("all")) {
						ruleAccepted = true;
						break;
					}
				}
				if (ruleAccepted) {
					for (RuleSet mediaRule : ((RuleMedia) rule).getRules()) {
						rules.add(new OrderedRule((RuleSet) mediaRule,
								orderNum++));
					}
				}
			}
		}

		// Mapy pro indexování pravidel
		HashMap<String, ArrayList<OrderedRule>> mapElement = new HashMap<String, ArrayList<OrderedRule>>();
		HashMap<String, ArrayList<OrderedRule>> mapClass = new HashMap<String, ArrayList<OrderedRule>>();
		HashMap<String, ArrayList<OrderedRule>> mapID = new HashMap<String, ArrayList<OrderedRule>>();
		ArrayList<OrderedRule> others = new ArrayList<OrderedRule>();

		// Rozdělení pravidel do map pro rychlejší vyhledání pravidel.
		boolean addToOthers;
		String value;
		SimpleSelector lastSimpleSelector;
		ArrayList<OrderedRule> ar = new ArrayList<OrderedRule>();
		for (OrderedRule or : rules) {
			RuleSet r = or.getRuleSet();
			for (Selector s : r.getSelectors()) {
				addToOthers = true;
				lastSimpleSelector = s.getSimpleSelectorsList().get(
						s.getSimpleSelectorsList().size() - 1);
				// Zpracování jména elementu
				value = lastSimpleSelector.getElementName();
				if (value != null) {
					if (value.equals("*")) {
						others.add(or);
						continue;
					} else {
						value = value.toLowerCase();
						ar = mapElement.get(value);
						if (ar == null) {
							ar = new ArrayList<OrderedRule>();
							mapElement.put(value, ar);
						}
						ar.add(or);
						addToOthers = false;
					}
				}
				// Zpracování jména(jmen) třídy
				value = lastSimpleSelector.getClassName();
				if (value != null) {
					value = value.toLowerCase();
					ar = mapClass.get(value);
					if (ar == null) {
						ar = new ArrayList<OrderedRule>();
						mapClass.put(value, ar);
					}
					ar.add(or);
					addToOthers = false;
				}
				// Zpracování ID
				value = lastSimpleSelector.getIDName();
				if (value != null) {
					value = value.toLowerCase();
					ar = mapID.get(value);
					if (ar == null) {
						ar = new ArrayList<OrderedRule>();
						mapID.put(value, ar);
					}
					ar.add(or);
					addToOthers = false;
				}

				// Pokud není v některém z předchozích, uložím do Others
				if (addToOthers) {
					others.add(or);
				}
			}
		}
		
		
		
		Element root = doc.getDocumentElement();
		processElement(root, assignedDeclarations, mapElement, mapClass, mapID,
				others);
		return assignedDeclarations;
	}
	*/
	/**
	 * Zpracuje jeden element a přiřadí k němu všechny deklarace. Deklarace se
	 * ukládají do mapy assignedDeclarations, která na konci procesu obsahuje
	 * pro každý element seznam (ArrayList) s příslušnými deklaracemi. U každé
	 * deklarace je vypočítána specificita, deklarace umožňují seřazení
	 * (implementují rozhraní comparable)
	 */
	/*
	private static void processElement(
			Element el,
			HashMap<Element, ArrayList<AssignedDeclaration>> assignedDeclarations,
			HashMap<String, ArrayList<OrderedRule>> mapElement,
			HashMap<String, ArrayList<OrderedRule>> mapClass,
			HashMap<String, ArrayList<OrderedRule>> mapID,
			ArrayList<OrderedRule> others) {
		ArrayList<AssignedDeclaration> elementAssignedDeclarations = new ArrayList<AssignedDeclaration>();
		assignedDeclarations.put(el, elementAssignedDeclarations); // Přidám do
																	// mapy
																	// seznam s
																	// přiřazenými
																	// deklaracemi

		// Set pro ukládání kandidátních pravidel. Pravidla získaná z map nemusí
		// svým selectorem odpovídat -> je potřeba
		// následně selector ověřit!
		// Duplicity jsou řešeny implicitně díky kolekci typu Set
		HashSet<OrderedRule> candidates = new HashSet<OrderedRule>();
		// Název třídy
		String classNames = el.getAttribute("class").toLowerCase();
		if (classNames != null) {
			for (String className : classNames.split(" ")) {
				if (className.trim().length() > 0) {
					if (mapClass.get(className) != null) {
						candidates.addAll(mapClass.get(className));
					}
				}
			}
		}
		// ID
		String idName = el.getAttribute("id").toLowerCase();
		if (idName != null) {
			if (mapID.get(idName) != null) {
				candidates.addAll(mapID.get(idName));
			}
		}
		// Název Elementu
		String elementName = el.getNodeName().toLowerCase();
		if (elementName != null) {
			if (mapElement.get(elementName) != null) {
				candidates.addAll(mapElement.get(elementName));
			}
		}
		// Ostatní
		candidates.addAll(others);
		ArrayList<OrderedRule> candidatesList = new ArrayList(candidates);
		Collections.sort(candidatesList);

		for (OrderedRule candidateRule : candidatesList) {
			for (Selector s : candidateRule.getRuleSet().getSelectors()) {
				if (matchElementBySelector(el, s)) {
					// Daný selektor odpovídá elementu - přidám tedy do seznamu
					// všechny deklarace
					// pravidla.
					for (Declaration d : candidateRule.getRuleSet()
							.getDeclarations()) {
						elementAssignedDeclarations
								.add(new AssignedDeclaration(d, s));
					}
				}
			}
		}
		// Seřazení deklarací podle pravidla !IMPORTANT a specificity (řadící
		// metoda je stabilní)
		Collections.sort(elementAssignedDeclarations);

		// zpracování pod-elementů
		for (int i = 0; i < el.getChildNodes().getLength(); i++) {
			Node n = el.getChildNodes().item(i);
			if (n instanceof Element) {
				processElement((Element) n, assignedDeclarations, mapElement,
						mapClass, mapID, others);
			}
		}
	}
*/
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
