package cz.vutbr.web.csskit;

import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
                className = ((ElementClass)item).getClassName();
            }
        }
        return className;
    }
    
    
    public String getIDName() {
        String idName = null;
        for(SelectorPart item : list) {
            if(item instanceof ElementID)
            	idName = ((ElementID)item).getID();
        }
        return idName;
    }
    
    public String getElementName() {
    	String elementName = null;
    	for(SelectorPart item : list) {
    		if(item instanceof ElementName)
    			elementName = ((ElementName)item).getName();
    	}
    	return elementName;
    }
    
    public PseudoDeclaration getPseudoElement() {
        PseudoDeclaration ret = null;
        for(SelectorPart item : list) {
            if(item instanceof PseudoPage)
            {
                ret = ((PseudoPage)item).getDeclaration();
                if (ret.isPseudoElement())
                    break; //pseudo-elements may only be appended after the last simple selector of the selector
                else
                    ret = null; //not interested in pseudo-classes
            }
        }
        return ret;
    }
    
    public boolean matches(Element e) {
    	
		// check other items of simple selector
		for(SelectorPart item : list) {
			if(item == null || !item.matches(e)) //null in case of syntax error (missing term)
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
     * Element name
     * @author kapy
     */
    public static class ElementNameImpl implements ElementName {    	 
		
    	private String name; 
    	
    	protected ElementNameImpl(String name) {
    		setName(name);
    	}
    	
		public void computeSpecificity(CombinedSelector.Specificity spec) {
			if(!WILDCARD.equals(name))
				spec.add(Level.D);
		}
		
		public boolean matches(Element e) {
			if(name!=null && WILDCARD.equals(name)) return true;
			return ElementUtil.matchesName(e, name);
		}	
		
		public String getName() {
			return name;
		}
		
		public ElementName setName(String name) {
			if(name == null)
				throw new IllegalArgumentException("Invalid element name (null)");
				
			this.name = name;
			return this;
		}
		
		@Override
		public String toString() {
			return name;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
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
			if (!(obj instanceof ElementNameImpl))
				return false;
			ElementNameImpl other = (ElementNameImpl) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
    	
    }
    
    /**
     * Element class
     * @author kapy
     *
     */
    public static class ElementClassImpl implements ElementClass {

    	private String className;
    	
    	protected ElementClassImpl(String className) {
    		setClassName(className);
    	}
    	
    	public void computeSpecificity(Specificity spec) {
    		spec.add(Level.C);
    	}
    	
    	public boolean matches(Element e) {
    		return ElementUtil.	matchesClass(e, className);
    	}
    	
		public String getClassName() {
			return className;
		}
    	
		public ElementClass setClassName(String className) {
			if(className == null)
				throw new IllegalArgumentException("Invalid element class (null)");
			
			this.className = className;
			return this;
		}
    	
    	@Override
    	public String toString() {
    		return "." + className;
    	}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((className == null) ? 0 : className.hashCode());
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
			if (!(obj instanceof ElementClassImpl))
				return false;
			ElementClassImpl other = (ElementClassImpl) obj;
			if (className == null) {
				if (other.className != null)
					return false;
			} else if (!className.equals(other.className))
				return false;
			return true;
		}    	
    }
    
    /**
     * Wrap of CSS pseudo class or pseudo class with function
     * @author kapy
     *
     */
    public static class PseudoPageImpl implements PseudoPage {
    	
        private static HashMap<String, PseudoDeclaration> PSEUDO_DECLARATIONS;
        static {
            PSEUDO_DECLARATIONS = new HashMap<String, PseudoDeclaration>(22);
            PSEUDO_DECLARATIONS.put("active", PseudoDeclaration.ACTIVE);
            PSEUDO_DECLARATIONS.put("focus", PseudoDeclaration.FOCUS);
            PSEUDO_DECLARATIONS.put("hover", PseudoDeclaration.HOVER);
            PSEUDO_DECLARATIONS.put("link", PseudoDeclaration.LINK);
            PSEUDO_DECLARATIONS.put("visited", PseudoDeclaration.VISITED);
            PSEUDO_DECLARATIONS.put("first-child", PseudoDeclaration.FIRST_CHILD);
            PSEUDO_DECLARATIONS.put("last-child", PseudoDeclaration.LAST_CHILD);
            PSEUDO_DECLARATIONS.put("only-child", PseudoDeclaration.ONLY_CHILD);
            PSEUDO_DECLARATIONS.put("only-of-type", PseudoDeclaration.ONLY_OF_TYPE);
            PSEUDO_DECLARATIONS.put("nth-child", PseudoDeclaration.NTH_CHILD);
            PSEUDO_DECLARATIONS.put("nth-last-child", PseudoDeclaration.NTH_LAST_CHILD);
            PSEUDO_DECLARATIONS.put("nth-of-type", PseudoDeclaration.NTH_OF_TYPE);
            PSEUDO_DECLARATIONS.put("nth-last-of-type", PseudoDeclaration.NTH_LAST_OF_TYPE);
            PSEUDO_DECLARATIONS.put("first-of-type", PseudoDeclaration.FIRST_OF_TYPE);
            PSEUDO_DECLARATIONS.put("last-of-type", PseudoDeclaration.LAST_OF_TYPE);
            PSEUDO_DECLARATIONS.put("root", PseudoDeclaration.ROOT);
            PSEUDO_DECLARATIONS.put("empty", PseudoDeclaration.EMPTY);
            PSEUDO_DECLARATIONS.put("lang", PseudoDeclaration.LANG);
            PSEUDO_DECLARATIONS.put("first-letter", PseudoDeclaration.FIRST_LETTER);
            PSEUDO_DECLARATIONS.put("first-line", PseudoDeclaration.FIRST_LINE);
            PSEUDO_DECLARATIONS.put("before", PseudoDeclaration.BEFORE);
            PSEUDO_DECLARATIONS.put("after", PseudoDeclaration.AFTER);
        }
        
    	private String functionName;
    	private String value;
    	private PseudoDeclaration declaration;
    	//decoded element index for nth-XXX properties -- values a and b in the an+b specification
    	private int[] elementIndex;
    	
    	protected PseudoPageImpl(String value, String functionName) {
    		setValue(value);
    		setFunctionName(functionName);
    	}

    	
    	public PseudoDeclaration getDeclaration()
    	{
    	    return declaration;
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
            inferDeclaration();
            decodeValue();
			return this;
		}
		
		public void computeSpecificity(Specificity spec) {

		    if (declaration != null)
		    {
    			if(declaration.isPseudoElement())
    				spec.add(Level.D);
    			else
    				spec.add(Level.C);
		    }

		}		
		
		public boolean matches(Element e) {
			
			if(declaration != null) { //null declaration means some unknown or unimplemented pseudo
				switch (declaration) {
					case FIRST_CHILD:
					case LAST_CHILD:
					case ONLY_CHILD:
					    if (e.getParentNode().getNodeType() == Node.ELEMENT_NODE)
					    {
    						boolean first = false;
    						boolean last = false;
    						if (declaration != PseudoDeclaration.LAST_CHILD) {
    							Node prev = e;
    							do {
    								prev = prev.getPreviousSibling();
    								if (prev == null) {
    								    first = true;
    								    break;
    								}
    							} while(prev.getNodeType() != Node.ELEMENT_NODE);
    						}
    						if (declaration != PseudoDeclaration.FIRST_CHILD) {
    							Node next = e;
    							do {
    								next = next.getNextSibling();
    								if (next == null) {
    								    last = true;
    								    break; 
    								}
    							} while(next.getNodeType() != Node.ELEMENT_NODE);
    						}
    						switch (declaration) {
    							case FIRST_CHILD: return first;
    							case LAST_CHILD: return last;
    							default: return first && last; //ONLY_CHILD
    						}
					    }
					    else
					        return false;
                    case FIRST_OF_TYPE:
                    case LAST_OF_TYPE:
                    case ONLY_OF_TYPE:
                        if (e.getParentNode().getNodeType() == Node.ELEMENT_NODE)
                        {
                            boolean firstt = false;
                            boolean lastt = false;
                            if (declaration != PseudoDeclaration.LAST_OF_TYPE) {
                                Node prev = e;
                                firstt = true;
                                do {
                                    prev = prev.getPreviousSibling();
                                    if (prev != null && prev.getNodeType() == Node.ELEMENT_NODE
                                            && isSameElementType(e, (Element) prev))
                                        firstt = false;
                                } while (prev != null && firstt);
                            }
                            if (declaration != PseudoDeclaration.FIRST_OF_TYPE) {
                                Node next = e;
                                lastt = true;
                                do {
                                    next = next.getNextSibling();
                                    if (next != null && next.getNodeType() == Node.ELEMENT_NODE
                                            && isSameElementType(e, (Element) next))
                                        lastt = false;
                                } while(next != null && lastt);
                            }
                            switch (declaration) {
                                case FIRST_OF_TYPE: return firstt;
                                case LAST_OF_TYPE: return lastt;
                                default: return firstt && lastt; //ONLY_OF_TYPE
                            }
                        }
                        else
                            return false;
                    case NTH_CHILD:
                        return positionMatches(countSiblingsBefore(e, false) + 1, elementIndex);
                    case NTH_LAST_CHILD:
                        return positionMatches(countSiblingsAfter(e, false) + 1, elementIndex);
                    case NTH_OF_TYPE:
                        return positionMatches(countSiblingsBefore(e, true) + 1, elementIndex);
                    case NTH_LAST_OF_TYPE:
                        return positionMatches(countSiblingsAfter(e, true) + 1, elementIndex);
                    case ROOT:
                        return e.getParentNode().getNodeType() == Node.DOCUMENT_NODE;
                    case EMPTY:
                        NodeList elist = e.getChildNodes();
                        for (int i = 0; i < elist.getLength(); i++)
                        {
                            short t = elist.item(i).getNodeType();
                            if (t == Node.ELEMENT_NODE || t == Node.TEXT_NODE 
                                    ||t == Node.CDATA_SECTION_NODE || t == Node.ENTITY_REFERENCE_NODE)
                                return false;
                        }
                        return true;
					default:
						if (declaration.isPseudoElement() || //match all pseudo elements and the LINK pseudo class for links
								(e.getTagName().equalsIgnoreCase("a") && declaration == PseudoDeclaration.LINK))
							return true;
				}
			}
			return false;
		}
		
		/**
		 * Checks whether the element position matches a <code>an+b</code> index specification.
		 * @param pos The element position according to some counting criteria.
		 * @param n The index specifiaction <code>an+b</code> - <code>a</code> and <code>b</code> values in array int[2].
		 * @return <code>true</code> when the position matches the index.
		 */
		protected boolean positionMatches(int pos, int[] n)
		{
            try {
                int an = pos - n[1];
                if (n[0] == 0)
                    return an == 0;
                else
                    return an * n[0] >= 0 && an % n[0] == 0;
            } catch (NumberFormatException ex) {
                return false;
            }
		}
		
		/**
		 * Decodes the element index in the <code>an+b</code> form.
		 * @param index the element index string
		 * @return an array of two integers <code>a</code> and <code>b</code>
		 * @throws NumberFormatException
		 */
		protected int[] decodeIndex(String index) throws NumberFormatException
		{
		    String s = index.toLowerCase().trim();
		    if (s.equals("odd")){
                int[] ret = {2, 1};
                return ret;
		    }
		    else if (s.equals("even")){
                int[] ret = {2, 0};
                return ret;
            }
		    else {
                int[] ret = {0, 0};
    		    int n = s.indexOf('n');
    		    if (n != -1)
    		    {
    		        String sa = s.substring(0, n).trim();
                    if (sa.length() == 0)
                        ret[0] = 1;
                    else if (sa.equals("-"))
                        ret[0] = -1;
                    else
                        ret[0] = Integer.parseInt(sa);
                    
    		        n++;
    		        StringBuilder sb = new StringBuilder();
    		        while (n < s.length())
    		        {
    		            char ch = s.charAt(n);
    		            if (ch != '+' && !Character.isWhitespace(ch))
    		                sb.append(ch);
    		            n++;
    		        }
    		        if (sb.length() > 0)
    	                ret[1] = Integer.parseInt(sb.toString());
    		    }
    		    else
    		        ret[1] = Integer.parseInt(s);
    		    
    		    return ret;
		    }
		}
		
		/**
		 * Computes the count of element siblings before the given element in the DOM tree.
		 * @param e The element to be examined
		 * @param sameType when set to <code>true</code> only the element with the same type are considered.
		 *                 Otherwise, all elements are considered.
		 * @return the number of preceding siblings
		 */
		protected int countSiblingsBefore(Element e, boolean sameType)
		{
		    int cnt = 0;
		    Node prev = e;
		    do {
		        prev = prev.getPreviousSibling();
		        if (prev != null && prev.getNodeType() == Node.ELEMENT_NODE)
		        {
		            if (!sameType || isSameElementType(e, (Element) prev))
		                cnt++;
		        }
		    } while (prev != null);
		    
		    return cnt;
		}
		
        /**
         * Computes the count of element siblings after the given element in the DOM tree.
         * @param e The element to be examined
         * @param sameType when set to <code>true</code> only the element with the same type are considered.
         *                 Otherwise, all elements are considered.
         * @return the number of following siblings
         */
        protected int countSiblingsAfter(Element e, boolean sameType)
        {
            int cnt = 0;
            Node next = e;
            do {
                next = next.getNextSibling();
                if (next != null && next.getNodeType() == Node.ELEMENT_NODE)
                {
                    if (!sameType || isSameElementType(e, (Element) next))
                        cnt++;
                }
            } while (next != null);
            
            return cnt;
        }
        
		/**
		 * Checks whether two elements have the same name.
		 * @param e1 the first element
		 * @param e2 the second element
		 * @return <code>true</code> when the elements have the same names
		 */
		protected boolean isSameElementType(Element e1, Element e2)
		{
		    return e1.getNodeName().equalsIgnoreCase(e2.getNodeName());
		}
		
		/**
		 * Sets value of pseudo. Could be even <code>null</code>
		 * @param value New value
		 */
		public PseudoPage setValue(String value) {
			this.value = value;
			inferDeclaration();
			decodeValue();
			return this;
		}
		

		public String getValue() {
			return value;
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
			int result = 1;
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
			if (obj == null)
				return false;
			if (!(obj instanceof PseudoPageImpl))
				return false;
			PseudoPageImpl other = (PseudoPageImpl) obj;
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
		
		private void inferDeclaration()
		{
		    if (functionName != null)
		        declaration = PSEUDO_DECLARATIONS.get(functionName.toLowerCase()); //Pseudo-element and pseudo-class names are case-insensitive
		    else if (value != null)
		        declaration = PSEUDO_DECLARATIONS.get(value.toLowerCase());
		    else
		        declaration = null;
		}

        private void decodeValue()
        {
            //decode the element index for nth-X properties
            elementIndex = null;
            if (declaration == PseudoDeclaration.NTH_CHILD || declaration == PseudoDeclaration.NTH_LAST_CHILD
                    || declaration == PseudoDeclaration.NTH_OF_TYPE || declaration == PseudoDeclaration.NTH_LAST_OF_TYPE)
            {
                try {
                    elementIndex = decodeIndex(value);
                } catch (NumberFormatException e) {
                }
            }
        }

    }
    
    /**
     * Element ID
     * @author kapy
     *
     */
    public static class ElementIDImpl implements ElementID {
    	
    	private String id;
    	
    	protected ElementIDImpl(String value) {
    		setID(value);
    	}
    	
    	public void computeSpecificity(Specificity spec) {
    		spec.add(Level.B);
		}    	
    	
    	public boolean matches(Element e) {
    		return ElementUtil.matchesID(e, id);
    	}
    	
    	public ElementID setID(String id) {
    		if(id==null)
    			throw new IllegalArgumentException("Invalid element ID (null)");
    		
    		this.id = id;
    		return this;
    	}
    	
    	public String getID() {
    		return id;
    	}
    	    	
    	@Override
    	public String toString() {
    		return "#" + id;
    	}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
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
			if (!(obj instanceof ElementIDImpl))
				return false;
			ElementIDImpl other = (ElementIDImpl) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
    	
    	
    }
    
    /**
     * Attribute holder
     * @author kapy
     *
     */
    public static class ElementAttributeImpl implements ElementAttribute {
    	
    	/** Operator between attribute and value */
    	private Operator operator;
    	
    	private String attribute;
    	private String value;
    	private boolean isStringValue;
    	
    	protected ElementAttributeImpl(String value, boolean isStringValue, Operator operator, String attribute) {
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
		public String getAttribute() {
			return attribute;
		}



		/**
		 * @param name the attribute to set
		 */
		public ElementAttribute setAttribute(String name) {
			this.attribute = name;
			return this;
		}
		
		public void computeSpecificity(Specificity spec) {
			spec.add(Level.C);
		}
		
		public boolean matches(Element e) {
			return ElementUtil.matchesAttribute(e, attribute, value, operator);
		}
    	
		public String getValue() {
			return value;
		}
		
    	public ElementAttribute setValue(String value) {
    		this.value = value;
    		return this;
    	}
		
		@Override
    	public String toString() {
    		StringBuilder sb = new StringBuilder();
    		
    		sb.append(OutputUtil.ATTRIBUTE_OPENING).append(attribute);
    		sb.append(operator.value());

    		if(isStringValue && value!=null)
    			sb.append(OutputUtil.STRING_OPENING);
    		
    		if(value != null) sb.append(value);
    		
    		if(isStringValue && value!=null)
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
			int result = 1;
			result = prime * result
					+ ((attribute == null) ? 0 : attribute.hashCode());
			result = prime * result + (isStringValue ? 1231 : 1237);
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
			if (obj == null)
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
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}
		
    }
    
    public static class ElementDOMImpl implements ElementDOM {
 
        /** The element used as the selector */
    	private Element elem;
    	/** When set to true, the selector has a maximal specificity (inline). Otherwise, it has a minimal specificity. */
    	private boolean inlinePriority;
    	
    	protected ElementDOMImpl(Element e, boolean inlinePriority) {
    		this.elem = e;
    		this.inlinePriority = inlinePriority;
    	}

		public Element getElement() {
			return elem;
		}

		public ElementDOM setElement(Element e) {
			this.elem = e;
			return this;
		}

		public void computeSpecificity(Specificity spec) {
		    if (inlinePriority)
		        spec.add(Level.A);
		}

		public boolean matches(Element e) {
			return elem.equals(e);
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((elem == null) ? 0 : elem.hashCode());
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
			if (!(obj instanceof ElementDOMImpl))
				return false;
			ElementDOMImpl other = (ElementDOMImpl) obj;
			if (elem == null) {
				if (other.elem != null)
					return false;
			} else if (!elem.equals(other.elem))
				return false;
			return true;
		}
		
		
    	
    }
    
}
