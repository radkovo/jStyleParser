package cz.vutbr.web.domassign;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.Rule;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.SSItem;
import cz.vutbr.web.css.SSItemAttrib;
import cz.vutbr.web.css.SSItemClass;
import cz.vutbr.web.css.SSItemID;
import cz.vutbr.web.css.SSItemPseudo;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.SimpleSelector;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StyleSheetNotValidException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class Analyzer {
    
    protected static HashMap<Element, ArrayList<AssignedDeclaration>> analyze(Document doc, StyleSheet styleSheet, String media) throws StyleSheetNotValidException  {
        styleSheet.check();
        HashMap<Element, ArrayList<AssignedDeclaration>> assignedDeclarations = new HashMap<Element, ArrayList<AssignedDeclaration>>();
        
        ArrayList<OrderedRule> rules = new ArrayList<OrderedRule>();
        
        //Zpracování pravidel - odfiltrování pravidel určených pro jiný typ média, vložení všech do jednoho seznamu (ArrayList rules)
        //Pravidla získávají při vkládání do kolekce pořadové číslo, aby bylo možné zachovat jejich výchozí pořadí i při 
        //rozdělení do map
        int orderNum = 0;
        for(Rule rule : styleSheet.getRules()) {
            if(rule instanceof RuleSet) {
                rules.add(new OrderedRule((RuleSet)rule, orderNum++));
            }
            else if(rule instanceof RuleMedia) {
                boolean ruleAccepted = false;
                for(String ruleMedia : ((RuleMedia)rule).getMedias()) {
                    if(ruleMedia.equalsIgnoreCase(media) || ruleMedia.equalsIgnoreCase("all")) {
                        ruleAccepted = true;
                        break;
                    }
                }
                if(ruleAccepted) {
                    for(RuleSet mediaRule : ((RuleMedia)rule).getRules()) {
                        rules.add(new OrderedRule((RuleSet)mediaRule, orderNum++));
                    }
                }
            }
        }
        
        //Mapy pro indexování pravidel
        HashMap<String, ArrayList<OrderedRule>> mapElement = new HashMap<String, ArrayList<OrderedRule>>();
        HashMap<String, ArrayList<OrderedRule>> mapClass = new HashMap<String, ArrayList<OrderedRule>>();
        HashMap<String, ArrayList<OrderedRule>> mapID = new HashMap<String, ArrayList<OrderedRule>>();
        ArrayList<OrderedRule> others = new ArrayList<OrderedRule>();
        
        //Rozdělení pravidel do map pro rychlejší vyhledání pravidel.
        boolean addToOthers;
        String value;
        SimpleSelector lastSimpleSelector;
        ArrayList<OrderedRule> ar = new ArrayList<OrderedRule>();
        for(OrderedRule or : rules) {
            RuleSet r = or.getRuleSet();
            for(Selector s : r.getSelectors()) {
                addToOthers = true;
                lastSimpleSelector = s.getSimpleSelectorsList().get(s.getSimpleSelectorsList().size()-1);
                //Zpracování jména elementu
                value = lastSimpleSelector.getElementName();
                if(value != null) {
                    if(value.equals("*")) {
                        others.add(or);
                        continue;
                    }
                    else {
                        value = value.toLowerCase();
                        ar = mapElement.get(value);
                        if(ar == null) {
                            ar = new ArrayList<OrderedRule>();
                            mapElement.put(value, ar);
                        }
                        ar.add(or);
                        addToOthers = false;
                    }
                }
                //Zpracování jména(jmen) třídy
                value = lastSimpleSelector.getClassName();
                if(value != null) {
                    value = value.toLowerCase();
                    ar = mapClass.get(value);
                    if(ar == null) {
                        ar = new ArrayList<OrderedRule>();
                        mapClass.put(value, ar);
                    }
                    ar.add(or);
                    addToOthers = false;
                }
                //Zpracování ID
                value = lastSimpleSelector.getIDName();
                if(value != null) {
                    value = value.toLowerCase();
                    ar = mapID.get(value);
                    if(ar == null) {
                        ar = new ArrayList<OrderedRule>();
                        mapID.put(value, ar);
                    }
                    ar.add(or);
                    addToOthers = false;
                }
                
                //Pokud není v některém z předchozích, uložím do Others
                if(addToOthers) {
                    others.add(or);
                }
            }
        }
        
        Element root = doc.getDocumentElement();
        processElement(root, assignedDeclarations, mapElement, mapClass, mapID, others);
        return assignedDeclarations;
    }
    
    /**
     * Zpracuje jeden element a přiřadí k němu všechny deklarace. Deklarace se ukládají do mapy
     * assignedDeclarations, která na konci procesu obsahuje pro každý element seznam (ArrayList) s
     * příslušnými deklaracemi. U každé deklarace je vypočítána specificita, deklarace umožňují 
     * seřazení (implementují rozhraní comparable)
     */
    private static void processElement(Element el, HashMap<Element, ArrayList<AssignedDeclaration>> assignedDeclarations, HashMap<String, ArrayList<OrderedRule>> mapElement, HashMap<String, ArrayList<OrderedRule>> mapClass, HashMap<String, ArrayList<OrderedRule>> mapID, ArrayList<OrderedRule> others) {
        ArrayList<AssignedDeclaration> elementAssignedDeclarations = new ArrayList<AssignedDeclaration>();
        assignedDeclarations.put(el, elementAssignedDeclarations); //Přidám do mapy seznam s přiřazenými deklaracemi
        
        //Set pro ukládání kandidátních pravidel. Pravidla získaná z map nemusí svým selectorem odpovídat -> je potřeba 
        //následně selector ověřit!
        //Duplicity jsou řešeny implicitně díky kolekci typu Set
        HashSet<OrderedRule> candidates = new HashSet<OrderedRule>();
        //Název třídy
        String classNames = el.getAttribute("class").toLowerCase();
        if(classNames != null) {
            for(String className : classNames.split(" ")) {
                if(className.trim().length() > 0) {
                    if(mapClass.get(className) != null) {
                        candidates.addAll(mapClass.get(className));
                    }
                }
            }
        }
        //ID
        String idName = el.getAttribute("id").toLowerCase();
        if(idName != null) {
            if(mapID.get(idName) != null) {
                candidates.addAll(mapID.get(idName));
            }
        }
        //Název Elementu
        String elementName = el.getNodeName().toLowerCase();
        if(elementName != null) {
            if(mapElement.get(elementName) != null) {
                candidates.addAll(mapElement.get(elementName));
            }
        }
        //Ostatní
        candidates.addAll(others);
        ArrayList<OrderedRule> candidatesList = new ArrayList(candidates);
        Collections.sort(candidatesList);
        
        for(OrderedRule candidateRule : candidatesList) {
            for(Selector s : candidateRule.getRuleSet().getSelectors()) {
                if(matchElementBySelector(el, s)) {
                    //Daný selektor odpovídá elementu - přidám tedy do seznamu všechny deklarace
                    //pravidla. 
                    for(Declaration d : candidateRule.getRuleSet().getDeclarations()) {
                        elementAssignedDeclarations.add(new AssignedDeclaration(d, s));
                    }
                }
            }
        }
        //Seřazení deklarací podle pravidla !IMPORTANT a specificity (řadící metoda je stabilní)
        Collections.sort(elementAssignedDeclarations);
        
        //zpracování pod-elementů
        for(int i = 0; i < el.getChildNodes().getLength(); i++) {
            Node n = el.getChildNodes().item(i);
            if(n instanceof Element) {
                processElement((Element)n, assignedDeclarations, mapElement, mapClass, mapID, others);
            }
        }
    }
    
    /**
     * Kontroluje jeden Selector proti danému elementu.
     * Pokud Selector obsahuje více SimpleSelectorů, ohybuje se po stromové struktuře v závislosti
     * na kombinátorech
     */ 
    private static boolean matchElementBySelector(Element el, Selector s) {
        boolean match = true;
        Element actualPos = el;
        SimpleSelector.EnumCombinator combinator = null;
        //Procházím všechny SimpleSelectory v opačném pořadí
        for(int i = s.getSimpleSelectorsList().size()-1; i >= 0; i--) {
            SimpleSelector ss = s.getSimpleSelectorsList().get(i);
            
            //Kontroluje se vždy v prvním kole na element který vstupuje do funkce
            if(combinator == null) {
                if(!(matchElementBySimpleSelector(actualPos, ss))) {
                    match = false;
                    break;
                }
            }
            
            //kombinátor mezery, hledám výše v hierarchii výše odpovídající SimpleSelecor
            else if(combinator == SimpleSelector.EnumCombinator.space) {
                match = false;
                if(actualPos != null) {
                    actualPos = (actualPos.getParentNode() instanceof Element ? (Element)actualPos.getParentNode() : null);
                    while(actualPos != null && !match) {
                        match = matchElementBySimpleSelector(actualPos, ss);
                        if(!match) { 
                            actualPos = (actualPos.getParentNode() instanceof Element ? (Element)actualPos.getParentNode() : null);
                        }
                    }
                }
            }
            
            //kombinátor "větší než" - stejné jako předchozí (space), jen musí být hledaný element
            //v hierarchii právě o jeden stupeň výše
            else if(combinator == SimpleSelector.EnumCombinator.greater) {
                match = false;
                if(actualPos != null) {
                    actualPos = (Element)actualPos.getParentNode();
                    match = matchElementBySimpleSelector(actualPos, ss);
                }
            }
            
            //kombinátor plus - elementy musí ležet hned vedle sebe
            else if(combinator == SimpleSelector.EnumCombinator.plus) {
                match = false;
                Element parent = (actualPos.getParentNode() instanceof Element ? (Element)actualPos.getParentNode() : null);
                if(parent != null) {
                    //Nejdříve najdu svého přímého předchůdce
                    Element pre = null;
                    for(int ii = 0; ii < parent.getChildNodes().getLength(); ii++) {
                        Node n = parent.getChildNodes().item(ii);
                        if(n == actualPos) { 
                            break;
                        }
                        else if(n instanceof Element) {
                            pre = (Element)n;
                        }
                    }
                    //Pokud byl předchůdce nalezen, ověřím ho (pokud ne, match zůstává na false)
                    if(pre != null) {
                        match = matchElementBySimpleSelector(pre, ss);
                        actualPos = pre;
                    }
                }
            }
            combinator = ss.getCombinator();
            
            //Pokud Selector něčím neodpovídá, nemá cenu zkoušet další - vrátím false
            if(!match) { 
                return match;
            }
        }
        return match;
    }
    
    /**
     * Zkontroluje jeden SimpleSelector - porovnávání probíhá na jednom elementu (není třeba kontrolovat kontext)
     */
    private static boolean matchElementBySimpleSelector(Element el, SimpleSelector s) {
        //Je-li zadán typ elementu, zkontroluje se
        if(s.getFirstItem() != null) {
            if(!(el.getNodeName().equalsIgnoreCase(s.getFirstItem().getValue()) || s.getFirstItem().getValue().equals("*"))) {
                return false;
            }
        }
        
        //Kontrola ostatních položek SimpleSelectoru. Položky pseudo nelze porovnávat, protože pseudoelementy vznikají až
        //při rasterizaci dokumentu (například "první řádek" nebo :hover -  "nad objektem je myš")
        for(SSItem item : s.getItemsList()) {
            
            if(item instanceof SSItemID) {
                String id = el.getAttribute("id");
                if(!(id.equalsIgnoreCase(((SSItemID)item).getValue()))) {
                    return false;
                }
                continue;
            }
            
            if(item instanceof SSItemClass) {
                String classNames = el.getAttribute("class");
                boolean found = false;
                if(classNames != null) {
                    for(String className : classNames.split(" ")) {
                        if(className.trim().length() > 0) {
                            if((className.equalsIgnoreCase(((SSItemClass)item).getValue()))) {
                                found = true;
                            }
                        }
                    }
                }
                if(!found) {
                    return false;
                }
                continue;
            }
            
            if(item instanceof SSItemAttrib) {
                SSItemAttrib ssa = (SSItemAttrib)item;
                String attrib = el.getAttribute((ssa).getAttrib());
                if(ssa.getOperator() == null) {
                    if(attrib.length() == 0) {
                        return false;
                    }
                }
                else if(ssa.getOperator() == SSItemAttrib.EnumOperator.equals) {
                    if(!attrib.equalsIgnoreCase(ssa.getValue())) {
                        return false;
                    }
                }
                else if(ssa.getOperator() == SSItemAttrib.EnumOperator.includes) {
                    attrib = " " + attrib + " ";
                    if(!attrib.toLowerCase().matches(".* " + ssa.getValue() + " .*")) {
                        return false;
                    }
                }
                else if(ssa.getOperator() == SSItemAttrib.EnumOperator.dashmatch) {
                    if(!attrib.toLowerCase().matches("^" + ssa.getValue() + "(\\|.*)*")) {
                        return false;
                    }
                }
            }
            
            if(item instanceof SSItemPseudo) {
                //Ignorováno
                return false;
            }
        }
        return true;
    }
    
}
