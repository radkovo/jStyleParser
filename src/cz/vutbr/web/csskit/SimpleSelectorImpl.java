package cz.vutbr.web.csskit;

import java.util.Collections;
import java.util.List;

import org.w3c.dom.Element;

import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.SimpleSelector;
import cz.vutbr.web.css.Selector.Specificity;
import cz.vutbr.web.css.Selector.Specificity.Level;

/**
 * Encapsulates one selector for CSS declaration.
 * Selector can contain classes, attributes, ids, pseudoatrributes,
 * and element name, together with combinator according to next placed selectors
 * @author Jan Svercl, VUT Brno, 2008   	    
 * @author Karel Piwko, 2008
 * @version 1.0	* Completely rewritten according to new interface
 * 					* Inner classes 
 */
public class SimpleSelectorImpl implements SimpleSelector {

	protected Item firstItem;
	protected List<Item> items;
	
    protected Combinator combinator;
    
    public SimpleSelectorImpl() {
    	this.firstItem = null;
    	this.combinator = null;
    	this.items = Collections.emptyList();
    }  
    
	/**
	 * @return the firstItem
	 */
	public Item getFirstItem() {
		return firstItem;
	}

	/**
	 * @param firstItem the firstItem to set
	 */
	public void setFirstItem(Item firstItem) {
		this.firstItem = firstItem;
	}



	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}



	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}



	/**
	 * @return the combinator
	 */
	public Combinator getCombinator() {
		return combinator;
	}



	/**
	 * @param combinator the combinator to set
	 */
	public void setCombinator(Combinator combinator) {
		this.combinator = combinator;
	}



	@Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	if(combinator!=null) sb.append(combinator.value());
    	if(firstItem!=null) sb.append(firstItem.getValue());
    	sb = OutputUtil.appendList(sb, items, OutputUtil.EMPTY_DELIM);
    	
    	return sb.toString();
    }

	
    public String getClassName() {
        String className = null;
        for(Item item : items) {
            if(item instanceof ItemClass) {
                className = item.getValue();
            }
        }
        return className;
    }
    
    
    public String getIDName() {
        String idName = null;
        for(Item item : items) {
            if(item instanceof ItemID)
            	idName = item.getValue();
        }
        return idName;
    }
    
    public String getElementName() {
        if(firstItem == null) 
            return null;
        return firstItem.getValue();
    }
    
    public boolean matches(Element e) {
    	
		final String elementName = getElementName();
		if(elementName!=null && ! ElementUtil.matchesName(e, elementName))
			return false;
		
		// check other items of simple selector
		for(SimpleSelector.Item item : getItems()) {
			if(!item.matches(e))
				return false;
		}
		
		// we passed checking
		return true;
    }
    
    /**
     * Computes specificity of this selector
     */
    public void computeSpecificity(Selector.Specificity spec) {
    	
    	if(getFirstItem()!=null)
    		getFirstItem().computeSpecificity(spec);
			spec.add(Level.D);
		
		for(SimpleSelector.Item item: items) {
			item.computeSpecificity(spec);
		}
    }
    
    
    // ============================================================
    // implementation of intern classes
    
    /**
     * Item of CSS selector
     */
    public static class ItemImpl implements Item {    	 
		    	
    	/** Value */
    	protected String value;

    	protected ItemImpl() {
    		
    	}
    	
    	public ItemImpl(String value) {
    		setValue(value);
    	}
    	
		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * @param value the value to set
		 */
		public void setValue(String value) {
			if(value == null)
				throw new IllegalArgumentException("Invalid Item value(null)");
			this.value = value;
		}

		public void computeSpecificity(Selector.Specificity spec) {
			if(!WILDCARD.equals(value))
				spec.add(Level.D);
		}
		
		public boolean matches(Element e) {
			
			if(value!=null && WILDCARD.equals(value)) return true;
			
			return ElementUtil.matchesName(e, value);
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof ItemImpl))
				return false;
			final ItemImpl other = (ItemImpl) obj;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}
    	
    }
    
    /**
     * Holder of CSS class for selector 
     * @author kapy
     *
     */
    public static class ItemClassImpl extends ItemImpl implements ItemClass {
    	
    	public ItemClassImpl(String value) {
    		super(value);
    	}
    	
    	@Override
    	public void computeSpecificity(Specificity spec) {
    		spec.add(Level.C);
    	}
    	
    	@Override
    	public boolean matches(Element e) {
    		return ElementUtil.	matchesClass(e, value);
    	}
    	
    	@Override
    	public String toString() {
    		return "." + value;
    	}
    }
    
    /**
     * Holder of CSS pseudo class or pseudo class with function
     * @author kapy
     *
     */
    public static class ItemPseudoImpl extends ItemImpl implements ItemPseudo {
    	
    	protected static final String PSEUDO_CLASSES = 
    		"active|focus|hover|link|visited|first-child|lang";
    	
    	protected static final String PSEUDO_ELEMENTS =
    		"first-letter|first-line|before|after";
    	
    	protected String functionName;
    	
    	public ItemPseudoImpl(String value, String functionName) {
    		setValue(value);
    		setFunctionName(functionName);
    	}

		/**
		 * @return the functionName
		 */
		public String getFunctionName() {
			return functionName;
		}

		/**
		 * @param functionName the functionName to set
		 */
		public void setFunctionName(String functionName) {
			
			// sanity check
			if(functionName!=null)
				functionName = functionName.replaceAll("\\($", "");
			
			this.functionName = functionName;
		}
		
		/**
		 * Sets value of pseudo. Could be even <code>null</code>
		 * @param value New value
		 */
		@Override
		public void setValue(String value) {
			this.value = value;
		}
		
		@Override
		public void computeSpecificity(Specificity spec) {

			// pseudo-class
			if((value!=null && value.matches(PSEUDO_CLASSES)) ||
					(functionName!=null && functionName.matches(PSEUDO_CLASSES)))
				spec.add(Level.C);
			
			// pseudo element
			else if((value!=null && value.matches(PSEUDO_ELEMENTS)) ||
				(functionName!=null && functionName.matches(PSEUDO_ELEMENTS)))
				spec.add(Level.D);

		}		
		
		@Override
		public boolean matches(Element e) {
			return false;
		}
		
    	
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			 
			sb.append(OutputUtil.PAGE_OPENING);
			if(functionName!=null) 
				sb.append(functionName).append(OutputUtil.FUNCTION_OPENING);
			if(value!=null)		sb.append(value);
			if(functionName!=null)
				sb.append(OutputUtil.FUNCTION_CLOSING);
			
			sb.append(OutputUtil.PAGE_CLOSING);
			
			return sb.toString();
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result
					+ ((functionName == null) ? 0 : functionName.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (!(obj instanceof ItemPseudoImpl))
				return false;
			final ItemPseudoImpl other = (ItemPseudoImpl) obj;
			if (functionName == null) {
				if (other.functionName != null)
					return false;
			} else if (!functionName.equals(other.functionName))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}
		
    }
    
    /**
     * Holder of CSS #ID element
     * @author kapy
     *
     */
    public static class ItemIDImpl extends ItemImpl implements ItemID {
    	
    	public ItemIDImpl(String value) {
    		setValue(value);
    	}
    	
    	@Override
    	public void setValue(String value) {
    		if(value==null)
    			throw new IllegalArgumentException("Invalid value for ItemID(null)");
    		
    		value = value.replaceAll("^#", "");
    		this.value = value;
    	}
    	
    	@Override
    	public void computeSpecificity(Specificity spec) {
    		spec.add(Level.B);
		}    	
    	
    	@Override
    	public boolean matches(Element e) {
    		return ElementUtil.matchesID(e, value);
    	}
    	    	
    	@Override
    	public String toString() {
    		return "#" + value;
    	}
    }
    
    /**
     * Attribute holder
     * @author kapy
     *
     */
    public static class ItemAttributeImpl extends ItemImpl implements ItemAttribute {
    	
    	/** Operator between attribute and value */
    	protected Operator operator;
    	
    	protected String attribute;
    	
    	private boolean isValueIdent;
    	
    	public ItemAttributeImpl(String value, boolean isValueIdent, Operator operator, String attribute) {
    		
    		this.isValueIdent = isValueIdent;
    		setValue(value);    		
    		this.operator = operator;
    		this.attribute = attribute;
    	}
    	
    	@Override
    	public void setValue(String value) {
    		
    		// sanity check
    		if(value == null)
    			throw new IllegalArgumentException("Invalid value ItemAttribute (null)");
    		
    		// create form string token
    		if(!isValueIdent)
    			value = value.replaceAll("^'", "")
    			.replaceAll("^\"", "")
    			.replaceAll("'$", "")
    			.replaceAll("\"$", "");
    		
    		this.value = value;
    	}
    	
    	/**
		 * @return the operator
		 */
		public Operator getOperator() {
			return operator;
		}



		/**
		 * @param operator the operator to set
		 */
		public void setOperator(Operator operator) {
			this.operator = operator;
		}



		/**
		 * @return the attribute
		 */
		public String getName() {
			return attribute;
		}



		/**
		 * @param attribute the attribute to set
		 */
		public void setName(String name) {
			this.attribute = name;
		}
		
		@Override
		public void computeSpecificity(Specificity spec) {
			spec.add(Level.C);
		}
		
		@Override
		public boolean matches(Element e) {
			return ElementUtil.matchesAttribute(e, attribute, value, operator);
		}
		
		@Override
    	public String toString() {
    		StringBuilder sb = new StringBuilder();
    		
    		sb.append(OutputUtil.ATTRIBUTE_OPENING).append(attribute);
    		sb.append(operator.value());

    		if(!isValueIdent)
    			sb.append(OutputUtil.STRING_OPENING);
    		
    		if(value != null) sb.append(value);
    		
    		if(!isValueIdent)
    			sb.append(OutputUtil.STRING_CLOSING);

    		sb.append(OutputUtil.ATTRIBUTE_CLOSING);
    		
    		return sb.toString();
    	}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result
					+ ((attribute == null) ? 0 : attribute.hashCode());
			result = prime * result + (isValueIdent ? 1231 : 1237);
			result = prime * result
					+ ((operator == null) ? 0 : operator.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (!(obj instanceof ItemAttributeImpl))
				return false;
			final ItemAttributeImpl other = (ItemAttributeImpl) obj;
			if (attribute == null) {
				if (other.attribute != null)
					return false;
			} else if (!attribute.equals(other.attribute))
				return false;
			if (isValueIdent != other.isValueIdent)
				return false;
			if (operator == null) {
				if (other.operator != null)
					return false;
			} else if (!operator.equals(other.operator))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}
		
		
		
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((combinator == null) ? 0 : combinator.hashCode());
		result = prime * result
				+ ((firstItem == null) ? 0 : firstItem.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SimpleSelectorImpl))
			return false;
		final SimpleSelectorImpl other = (SimpleSelectorImpl) obj;
		if (combinator == null) {
			if (other.combinator != null)
				return false;
		} else if (!combinator.equals(other.combinator))
			return false;
		if (firstItem == null) {
			if (other.firstItem != null)
				return false;
		} else if (!firstItem.equals(other.firstItem))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}
    
    
}
