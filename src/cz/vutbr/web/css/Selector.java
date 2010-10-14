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
        ACTIVE("active", false),
        FOCUS("focus", false),
        HOVER("hover", false),
        LINK("link", false),
        VISITED("visited", false),
        FIRST_CHILD("first-child", false),
        LANG("lang", false),
        
        FIRST_LINE("first-line", true),
        FIRST_LETTER("first-letter", true),
        BEFORE("before", true),
        AFTER("after", true);
        
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
     * Modifies specificity according to CSS standard
     * @param spec Specificity to be modified
     */
    public void computeSpecificity(CombinedSelector.Specificity spec);
    
    /**
     * Matches simple selector against DOM element
     * @param e Element
     * @return <code>true</true> in case of match
     */
    public boolean matches(Element e);
    
    
    
    
    /**
     * Interface for handling items
     * @author kapy
     *
     */
    public interface SelectorPart { 	
    	public boolean matches(Element e);
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
    	public PseudoPage setFunctionName(String functionName);
    	
    	public String getValue();
    	public PseudoPage setValue(String value);
    	
        public PseudoDeclaration getDeclaration();
        
    }
       
   
}
