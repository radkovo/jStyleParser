package cz.vutbr.web.css;

import java.util.List;

/**
 * SimpleSelector
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko 2008
 * @version 1.0 * Rewritten enum Combinator,
 * 				 * moved SimpleSelector* interfaces inside
 */
public interface SimpleSelector {

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
     * Operator for Item attributes 
     * @author kapy
     *
     */
    public enum Operator {
    	EQUALS("="),
    	INCLUDES("~="),
    	DASHMATCH("|=");
    	
    	private String value;
    	
    	private Operator(String value) {
    		this.value = value;
    	}
    	
    	public String value() {return value;}
    }

    /**
     * Interface for handling items
     * @author kapy
     *
     */
    public interface Item {
    	public String getValue();
    	public void setValue(String value);
    	public int hashCode();
    	public boolean equals(Object obj);
    	
    	public boolean isAttribute();
    	public boolean isElement();
    	public boolean isClass();
    	public boolean isPseudoClass();
    	public boolean isPseudoElement();
    	public boolean isID();
    }
    
    /**
     * Item attribute
     * @author kapy
     *
     */
    public interface ItemAttribute extends Item {
    	public Operator getOperator();
    	public void setOperator(Operator operator);
    }
    
    /**
     * Item class
     * @author kapy
     *
     */
    public interface ItemClass extends Item {
    	
    }
    
    /**
     * Item class
     * @author kapy
     *
     */
    public interface ItemID extends Item {
    	
    }
    
    /**
     * Item pseudo page
     * @author kapy
     *
     */
    public interface ItemPseudo extends Item {
    	public String getFunctionName();
    	public void setFunctionName(String functionName);
    }
   
    /**
     * Returns items stored in simple selector
     * @return Items stored (excluding the first item)
     */
    public List<Item> getItems();
    
    /**
     * Sets items stored in simple selector
     * @param items Items to store (excluding the first item)
     */
    public void setItems(List<Item> items);
    
    /**
     * Returns combinator of this and other simple selector
     * @return Combinator
     */
    public Combinator getCombinator();
    
    /**
     * Sets combinator 
     * @param combinator Combinator between this and other simple selector
     */
    public void setCombinator(Combinator combinator);

    /**
     * First item, that is elements name
     * @return The first item
     */
    public Item getFirstItem();

    /**
     * Sets the first item
     * @param firstItem
     */
    public void setFirstItem(Item firstItem);    
    
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
}
