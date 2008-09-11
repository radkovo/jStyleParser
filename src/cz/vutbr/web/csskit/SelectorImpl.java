package cz.vutbr.web.csskit;

import org.w3c.dom.Element;

import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.CombinedSelector.Specificity;
import cz.vutbr.web.css.CombinedSelector.Specificity.Level;

/**
 * Encapsulates one selector for CSS declaration.
 * CombinedSelector can contain classes, attributes, ids, pseudo attributes,
 * and element name, together with combinator according to next placed selectors
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008   	    
 */
public class SelectorImpl extends AbstractRule<Selector.SelectorPart> implements Selector {

	protected Combinator combinator;
    
	/**
	 * @return the combinator
	 */
	public Combinator getCombinator() {
		return combinator;
	}

	/**
	 * @param combinator the combinator to set
	 */
	public Selector setCombinator(Combinator combinator) {
		this.combinator = combinator;
		return this;
	}

	@Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	if(combinator!=null) sb.append(combinator.value());    	
    	sb = OutputUtil.appendList(sb, list, OutputUtil.EMPTY_DELIM);
    	
    	return sb.toString();
    }

	
    public String getClassName() {
        String className = null;
        for(SelectorPart item : list) {
            if(item instanceof ElementClass) {
                className = item.getValue();
            }
        }
        return className;
    }
    
    
    public String getIDName() {
        String idName = null;
        for(SelectorPart item : list) {
            if(item instanceof ElementID)
            	idName = item.getValue();
        }
        return idName;
    }
    
    public String getElementName() {
    	String elementName = null;
    	for(SelectorPart item : list) {
    		if(item instanceof ElementName)
    			elementName = item.getValue();
    	}
    	return elementName;
    }
    
    public boolean matches(Element e) {
    	
    	// this is obsolete as Element name is always present
    	// at least in form of wildcard
		//String elementName = getElementName();
		//if(elementName!=null && !ElementUtil.matchesName(e, elementName))
		//	return false;
		
    	
		// check other items of simple selector
		for(SelectorPart item : list) {
			if(!item.matches(e))
				return false;
		}
		
		// we passed checking
		return true;
    }
    
    /**
     * Computes specificity of this selector
     */
    public void computeSpecificity(CombinedSelector.Specificity spec) {   	
		for(SelectorPart item: list) {
			item.computeSpecificity(spec);
		}
    }   
       
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((combinator == null) ? 0 : combinator.hashCode());
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
		if (!(obj instanceof SelectorImpl))
			return false;
		SelectorImpl other = (SelectorImpl) obj;
		if (combinator == null) {
			if (other.combinator != null)
				return false;
		} else if (!combinator.equals(other.combinator))
			return false;
		return true;
	}

	 
    // ============================================================
    // implementation of intern classes
	



	/**
	 * Abstract class with shared functionality
	 */
	public static abstract class AbstractSelectorPart implements SelectorPart {
		
		/** content */
		protected String value;
		
		protected AbstractSelectorPart(String value) {
			setValue(value);
		}
		
		/**
		 * @param value the value to set
		 */
		public abstract SelectorPart setValue(String value);
		
		public abstract boolean matches(Element e);
		
		public abstract void computeSpecificity(Specificity spec);
		
		public String getValue() {
			return value;
		}
		
		@Override
		public abstract String toString();
		
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
			if (!(obj instanceof AbstractSelectorPart))
				return false;
			AbstractSelectorPart other = (AbstractSelectorPart) obj;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}			
	}
	
	
	/**
     * Element name
     * @author kapy
     */
    public static class ElementNameImpl extends AbstractSelectorPart implements ElementName {    	 
		    	
    	protected ElementNameImpl(String value) {
    		super(value);
    	}
    	
    	@Override
		public void computeSpecificity(CombinedSelector.Specificity spec) {
			if(!WILDCARD.equals(value))
				spec.add(Level.D);
		}
		
    	@Override
		public boolean matches(Element e) {
			if(value!=null && WILDCARD.equals(value)) return true;
			return ElementUtil.matchesName(e, value);
		}	
		
		@Override
		public SelectorPart setValue(String value) {
			if(value == null)
				throw new IllegalArgumentException("Invalid SelectorPart value(null)");
				
			this.value = value;
			return this;
		}
		
		@Override
		public String toString() {
			return value;
		}
    	
    }
    
    /**
     * Element class
     * @author kapy
     *
     */
    public static class ElementClassImpl extends AbstractSelectorPart implements ElementClass {
    	
    	protected ElementClassImpl(String value) {
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
		public SelectorPart setValue(String value) {
			if(value == null)
				throw new IllegalArgumentException("Invalid SelectorPart value(null)");
			
			value = value.replaceAll("^\\.", "");			
			this.value = value;
			return this;
		}
    	
    	@Override
    	public String toString() {
    		return "." + value;
    	}
    }
    
    /**
     * Wrap of CSS pseudo class or pseudo class with function
     * @author kapy
     *
     */
    public static class PseudoPageImpl extends AbstractSelectorPart implements PseudoPage {
    	
    	protected static final String PSEUDO_CLASSES = 
    		"active|focus|hover|link|visited|first-child|lang";
    	
    	protected static final String PSEUDO_ELEMENTS =
    		"first-letter|first-line|before|after";
    	
    	protected String functionName;
    	
    	protected PseudoPageImpl(String value, String functionName) {
    		super(value);
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
		public PseudoPage setFunctionName(String functionName) {			
			this.functionName = functionName;
			return this;
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
			// pseudo-class
			if((value!=null && value.matches(PSEUDO_ELEMENTS)) ||
					(functionName!=null && functionName.matches(PSEUDO_ELEMENTS)))
				return true;
			
			return false;
		}
		
		/**
		 * Sets value of pseudo. Could be even <code>null</code>
		 * @param value New value
		 */
		@Override
		public PseudoPage setValue(String value) {
			this.value = value;
			return this;
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
			if (!(obj instanceof PseudoPageImpl))
				return false;
			PseudoPageImpl other = (PseudoPageImpl) obj;
			if (functionName == null) {
				if (other.functionName != null)
					return false;
			} else if (!functionName.equals(other.functionName))
				return false;
			return true;
		}

    }
    
    /**
     * Element ID
     * @author kapy
     *
     */
    public static class ElementIDImpl extends AbstractSelectorPart implements ElementID {
    	
    	protected ElementIDImpl(String value) {
    		super(value);
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
    	public ElementID setValue(String value) {
    		if(value==null)
    			throw new IllegalArgumentException("Invalid value for ElementID(null)");
    		
    		value = value.replaceAll("^#", "");
    		this.value = value;
    		return this;
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
    public static class ElementAttributeImpl extends AbstractSelectorPart implements ElementAttribute {
    	
    	/** Operator between attribute and value */
    	protected Operator operator;
    	
    	protected String attribute;
    	
    	private boolean isStringValue;
    	
    	protected ElementAttributeImpl(String value, boolean isStringValue, Operator operator, String attribute) {
    		super(value);
    		this.isStringValue = isStringValue;
    		this.operator = operator;
    		this.attribute = attribute;
    		setValue(value);
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
    	public ElementAttribute setValue(String value) {
    		
    		// sanity check
    		if(value == null) {
    			this.value = null;
    			return this;
    		}
    		
    		// create form string token
    		if(isStringValue)
    			value = value.replaceAll("^'", "")
    			.replaceAll("^\"", "")
    			.replaceAll("'$", "")
    			.replaceAll("\"$", "");
    		
    		this.value = value;
    		return this;
    	}
		
		@Override
    	public String toString() {
    		StringBuilder sb = new StringBuilder();
    		
    		sb.append(OutputUtil.ATTRIBUTE_OPENING).append(attribute);
    		sb.append(operator.value());

    		if(isStringValue)
    			sb.append(OutputUtil.STRING_OPENING);
    		
    		if(value != null) sb.append(value);
    		
    		if(isStringValue)
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
			result = prime * result + (isStringValue ? 1231 : 1237);
			result = prime * result
					+ ((operator == null) ? 0 : operator.hashCode());
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
			if (!(obj instanceof ElementAttributeImpl))
				return false;
			ElementAttributeImpl other = (ElementAttributeImpl) obj;
			if (attribute == null) {
				if (other.attribute != null)
					return false;
			} else if (!attribute.equals(other.attribute))
				return false;
			if (isStringValue != other.isStringValue)
				return false;
			if (operator == null) {
				if (other.operator != null)
					return false;
			} else if (!operator.equals(other.operator))
				return false;
			return true;
		}
				
		
    }	
}
