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
     * First item, that is elements name
     * @return The first item
     */
    public SelectorPart getFirstItem();

    /**
     * Sets the first item
     * @param firstItem
     * @return Modified instance
     */
    public Selector setFirstItem(SelectorPart firstItem);    
    
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
    	
    	public static final String WILDCARD = "*";
    	
    	public String getValue();
    	public SelectorPart setValue(String value);
    	public int hashCode();
    	public boolean equals(Object obj);
    	
    	public boolean matches(Element e);
    	public void computeSpecificity(CombinedSelector.Specificity spec);
    }
    
    /**
     * Element name
     * @author kapy
     *
     */
    public interface ElementName extends SelectorPart {
    	
    }
    
    /**
     * Element attribute
     * @author kapy
     *
     */
    public interface ElementAttribute extends SelectorPart {
    	
    	public String getName();
    	public void setName(String name);
    	
    	public Operator getOperator();
    	public void setOperator(Operator operator);
    }
    
    /**
     * Element class
     * @author kapy
     *
     */
    public interface ElementClass extends SelectorPart {
    	
    }
    
    /**
     * Element id
     * @author kapy
     *
     */
    public interface ElementID extends SelectorPart {
    	
    }
    
    /**
     * Pseudo page
     * @author kapy
     *
     */
    public interface PseudoPage extends SelectorPart {
    	public String getFunctionName();
    	public PseudoPage setFunctionName(String functionName);
    }
       
   
}
