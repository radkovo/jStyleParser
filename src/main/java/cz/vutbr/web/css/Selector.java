package cz.vutbr.web.css;

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
     * A pseudo class or element specification 
     * @author burgetr
     */
    public enum PseudoDeclaration
    {
        // Pseudo-classes
        ACTIVE("active", false),
        ANY("any", false),
        ANY_LINK("any-link", false),
        CHECKED("checked", false),
        DEFAULT("default", false),
        DEFINED("defined", false),
        DIR("dir", false),
        DISABLED("disabled", false),
        EMPTY("empty", false),
        ENABLED("enabled", false),
        FIRST("first", false),
        FIRST_CHILD("first-child", false),
        FIRST_OF_TYPE("first-of-type", false),
        FULLSCREEN("fullscreen", false),
        FOCUS("focus", false),
        FOCUS_WITHIN("focus-within", false),
        HOVER("hover", false),
        INDETERMINATE("indeterminate", false),
        IN_RANGE("in-range", false),
        INVALID("invalid", false),
        LANG("lang", false),
        LAST_CHILD("last-child", false),
        LAST_OF_TYPE("last-of-type", false),
        LEFT("left", false),
        LINK("link", false),
        NOT("not", false),
        NTH_CHILD("nth-child", false),
        NTH_LAST_CHILD("nth-last-child", false),
        NTH_LAST_OF_TYPE("nth-last-of-type", false),
        NTH_OF_TYPE("nth-of-type", false),
        ONLY_CHILD("only-child", false),
        ONLY_OF_TYPE("only-of-type", false),
        OPTIONAL("optional", false),
        OUT_OF_RANGE("out-of-range", false),
        PLACEHOLDER_SHOWN("placeholder-shown", false),
        READ_ONLY("read-only", false),
        READ_WRITE("read-write", false),
        REQUIRED("required", false),
        RIGHT("right", false),
        ROOT("root", false),
        SCOPE("scope", false),
        TARGET("target", false),
        VALID("valid", false),
        VISITED("visited", false),
        
        // Pseudo-elements
        FIRST_LINE("first-line", true),
        FIRST_LETTER("first-letter", true),
        BEFORE("before", true),
        AFTER("after", true),
        BACKDROP("backdrop", true),
        CUE("cue", true),
        GRAMMAR_ERROR("grammar-error", true),
        PLACEHOLDER("placeholder", true),
        SELECTION("selection", true),
        SPELLING_ERROR("spelling-error", true),
        
        // Placeholders for vendor-specific pseudo-classes or elements
        vendor_class(null, false),
        vendor_element(null, true);

        private String value;
        private boolean element;
        
        private PseudoDeclaration(String value, boolean isElement) {
            this.value = value;
            this.element = isElement;
        }
        
        public String value() {return value;}
        
        public boolean isPseudoElement() {return element;}
        
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
     * Reads the pseudoelement of the selector 
     * @return the used pseudo-element or <code>null</code> if no pseudo-element is specified
     */
    public PseudoDeclaration getPseudoElement();
    
    /**
     * Checks where the specified pseudo declaration is in this selector
     * @return <code>true</code> if the selector has the specified pseudo declaration
     */
    public boolean hasPseudoDeclaration(final PseudoDeclaration pd);

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
    
    /**
     * Pseudo page
     * @author kapy
     *
     */
    public interface PseudoPage extends SelectorPart {
    	public String getFunctionName();
    	public String getValue();
        public PseudoDeclaration getDeclaration();
    }
       
   
}
