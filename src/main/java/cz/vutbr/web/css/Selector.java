package cz.vutbr.web.css;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.w3c.dom.Element;

/**
 * Acts as collection of parsed parts of Selector (Parts)
 * with extended functionality.
 * 
 * Items are defined within this interface.
 * 
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface Selector extends Rule<Selector.SelectorPart> {

	/**
	 * Combinator for simple selectors 
	 * @author kapy
	 *
	 */
    public enum Combinator {
    	DESCENDANT(" "),
        ADJACENT("+"),
    	PRECEDING("~"),
    	CHILD(">");
    
    	private String value;
    	
    	private Combinator(String value) {
    		this.value = value;
    	}
    	
    	public String value() {return value;}
    }
    
    /**
     * Operator for SelectorPart attributes 
     * @author kapy
     *
     */
    public enum Operator {
    	EQUALS("="),
    	INCLUDES("~="),
    	DASHMATCH("|="),
    	CONTAINS("*="),
    	STARTSWITH("^="),
    	ENDSWITH("$="),
    	NO_OPERATOR("");
    	
    	private String value;
    	
    	private Operator(String value) {
    		this.value = value;
    	}
    	
    	public String value() {return value;}
    }

    /**
     * A pseudo-class for @page rules
     */
    public enum PseudoPageType {
        BLANK("blank"),
        FIRST("first"),
        LEFT("left"),
        RIGHT("right"),
        vendor(null); // Vendor-prefixed
        
        private final String name;
        private static final Map<String, PseudoPageType> lookup = new ConcurrentHashMap<>();
        
        private PseudoPageType(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
        
        public static PseudoPageType forName(String name) {
            if (name == null) {
                return null;
            }
            if (name.startsWith("-") || name.startsWith("_")) {
                return vendor;
            }
            if (lookup.isEmpty()) {
                for (PseudoPageType type : values()) {
                    if (type.getName() != null) {
                        lookup.put(type.getName(), type);
                    }
                }
            }
            return lookup.get(name.toLowerCase());
        }
    }
    
    /**
     * A pseudo-class
     */
    public enum PseudoClassType {
        ACTIVE("active"),
        ANY("any"),
        ANY_LINK("any-link"),
        CHECKED("checked"),
        DEFAULT("default"),
        DEFINED("defined"),
        DIR("dir"),
        DISABLED("disabled"),
        EMPTY("empty"),
        ENABLED("enabled"),
        FIRST_CHILD("first-child"),
        FIRST_OF_TYPE("first-of-type"),
        FULLSCREEN("fullscreen"),
        FOCUS("focus"),
        FOCUS_WITHIN("focus-within"),
        HAS("has"),
        HOVER("hover"),
        INDETERMINATE("indeterminate"),
        IN_RANGE("in-range"),
        INVALID("invalid"),
        LANG("lang"),
        LAST_CHILD("last-child"),
        LAST_OF_TYPE("last-of-type"),
        LINK("link"),
        NOT("not"),
        NTH_CHILD("nth-child"),
        NTH_LAST_CHILD("nth-last-child"),
        NTH_LAST_OF_TYPE("nth-last-of-type"),
        NTH_OF_TYPE("nth-of-type"),
        ONLY_CHILD("only-child"),
        ONLY_OF_TYPE("only-of-type"),
        OPTIONAL("optional"),
        OUT_OF_RANGE("out-of-range"),
        PLACEHOLDER_SHOWN("placeholder-shown"),
        READ_ONLY("read-only"),
        READ_WRITE("read-write"),
        REQUIRED("required"),
        ROOT("root"),
        SCOPE("scope"),
        TARGET("target"),
        VALID("valid"),
        VISITED("visited"),
        vendor(null); // Vendor-prefixed
        
        private final String name;
        private static final Map<String, PseudoClassType> lookup = new ConcurrentHashMap<>();
        
        private PseudoClassType(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
        
        public static PseudoClassType forName(String name) {
            if (name == null) {
                return null;
            }
            if (name.startsWith("-") || name.startsWith("_")) {
                return vendor;
            }
            if (lookup.isEmpty()) {
                for (PseudoClassType type : values()) {
                    if (type.getName() != null) {
                        lookup.put(type.getName(), type);
                    }
                }
            }
            return lookup.get(name.toLowerCase());
        }
    }
    
    /**
     * A pseudo-element
     */
    public enum PseudoElementType {
        FIRST_LINE("first-line"),
        FIRST_LETTER("first-letter"),
        BEFORE("before"),
        AFTER("after"),
        BACKDROP("backdrop"),
        CUE("cue"),
        GRAMMAR_ERROR("grammar-error"),
        PLACEHOLDER("placeholder"),
        SELECTION("selection"),
        SPELLING_ERROR("spelling-error"),
        vendor(null); // Vendor-prefixed
        
        private final String name;
        private static final Map<String, PseudoElementType> lookup = new ConcurrentHashMap<>();
        
        private PseudoElementType(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
        
        public static PseudoElementType forName(String name) {
            if (name == null) {
                return null;
            }
            if (name.startsWith("-") || name.startsWith("_")) {
                return vendor;
            }
            if (lookup.isEmpty()) {
                for (PseudoElementType type : values()) {
                    if (type.getName() != null) {
                        lookup.put(type.getName(), type);
                    }
                }
            }
            return lookup.get(name.toLowerCase());
        }
    }
    
    /**
     * Returns combinator of this and other simple selector
     * @return Combinator
     */
    public Combinator getCombinator();
    
    /**
     * Sets combinator 
     * @param combinator Combinator between this and other selector
     * @return Modified instance
     */
    public Selector setCombinator(Combinator combinator);  
    
    /**
     * Name of CSS class which is affected by this selector  
     * @return Name of CSS class
     */
    public String getClassName();
    
    /**
     * ID of CSS item which is affected by this selector
     * @return ID of CSS item
     */
    public String getIDName();
    
    /**
     * Name of HTML element which is affected by this selector
     * @return Name of HTML element
     */
    public String getElementName();
    
    /**
     * Reads the pseudo-element of the selector 
     * @return the used pseudo-element or <code>null</code> if no pseudo-element is specified
     */
    public PseudoElementType getPseudoElementType();
    
    /**
     * Checks where the specified pseudo-class is in this selector
     * @param pct
     * @return <code>true</code> if the selector has the specified pseudo-class
     */
    public boolean hasPseudoClass(final PseudoClassType pct);

    /**
     * Modifies specificity according to CSS standard
     * @param spec Specificity to be modified
     */
    public void computeSpecificity(CombinedSelector.Specificity spec);
    
    /**
     * Matches simple selector against DOM element using the default element matcher
     * and the default match condition registered in CSSFactory.
     * @param e Element
     * @return <code>true</code> in case of match
     */
    public boolean matches(Element e);
    
    /**
     * Matches simple selector against DOM element with an additional condition
     * @param e Element
     * @param matcher Element matcher to be used
     * @param cond An additional condition to be applied
     * @return <code>true</code> in case of match
     */
    public boolean matches(Element e, ElementMatcher matcher, MatchCondition cond);
    
    /**
     * Interface for handling items
     * @author kapy
     *
     */
    public interface SelectorPart { 	
    	public boolean matches(Element e, ElementMatcher matcher, MatchCondition cond);
    	public void computeSpecificity(CombinedSelector.Specificity spec);
    }
    
    /**
     * Element name
     * @author kapy
     *
     */
    public interface ElementName extends SelectorPart {
    	public static final String WILDCARD = "*";    	
    	public String getName();
    	public ElementName setName(String name);
    }
    
    /**
     * Element attribute
     * @author kapy
     *
     */
    public interface ElementAttribute extends SelectorPart {
    	
    	public String getAttribute();
    	public ElementAttribute setAttribute(String attribute);
    	
    	public String getValue();
    	public ElementAttribute setValue(String value);
    	
    	public Operator getOperator();
    	public void setOperator(Operator operator);
    }
    
    /**
     * Element class
     * @author kapy
     *
     */
    public interface ElementClass extends SelectorPart {
    	public String getClassName();
    	public ElementClass setClassName(String name);
    }
    
    /**
     * Element id
     * @author kapy
     *
     */
    public interface ElementID extends SelectorPart {
    	public String getID();
    	public ElementID setID(String id);
    }
    
    public interface ElementDOM extends SelectorPart {
    	public Element getElement();
    	public ElementDOM setElement(Element e);
    }
    
    public interface PseudoPage extends SelectorPart {
    	public String getName();
        public PseudoPageType getType();
    }
    
    public interface PseudoClass extends SelectorPart {
        public String getName();
        public String getFunctionValue();
        public PseudoClassType getType();
        public Selector getNestedSelector();
    }
    
    public interface PseudoElement extends SelectorPart {
        public String getName();
        public String getFunctionValue();
        public PseudoElementType getType();
        public Selector getNestedSelector();
    }
}
