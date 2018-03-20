package cz.vutbr.web.csskit;

import java.util.List;
import java.util.Objects;

import org.unbescape.css.CssEscape;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.ElementMatcher;
import cz.vutbr.web.css.MatchCondition;
import cz.vutbr.web.css.Rule;
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
	protected PseudoElementType pseudoElementType; //the pseudo element if defined (only a single pseudo element may be used in the selector)
    
	@Override
    public Rule<SelectorPart> replaceAll(List<SelectorPart> replacement)
    {
	    for (SelectorPart item : replacement)
	        checkPseudoElement(item);
        return super.replaceAll(replacement);
    }

    @Override
    public SelectorPart set(int index, SelectorPart element)
    {
        checkPseudoElement(element);
        return super.set(index, element);
    }

    @Override
    public void add(int index, SelectorPart element)
    {
        checkPseudoElement(element);
        super.add(index, element);
    }

    @Override
    public boolean add(SelectorPart o)
    {
        checkPseudoElement(o);
        return super.add(o);
    }

    /**
	 * @return the combinator
	 */
	public Combinator getCombinator() {
		return combinator;
	}

	/**
	 * Registers the pseudo element if the given selector part is a pseudo element.
	 */
	private void checkPseudoElement(SelectorPart item) {
        if(item instanceof PseudoElement) {
            pseudoElementType = ((PseudoElement) item).getType();
        }
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
    
    @Override
    public PseudoElementType getPseudoElementType() {
        return pseudoElementType;
    }
    
    @Override
    public boolean hasPseudoClass(final PseudoClassType pct) {
        for(SelectorPart item : list) {
            if(item instanceof PseudoClass && ((PseudoClass) item).getType() == pct) {
                return true;
            }
        }
        return false;
    }
    	
    public boolean matches(Element e) {
    	
		// check other items of simple selector
		for(SelectorPart item : list) {
			if(item == null || !item.matches(e, CSSFactory.getElementMatcher(), CSSFactory.getDefaultMatchCondition())) //null in case of syntax error (missing term)
				return false;
		}
		
		// we passed checking
		return true;
    }
    
    public boolean matches(Element e, ElementMatcher matcher, MatchCondition cond) {
        
        // check other items of simple selector
        for(SelectorPart item : list) {
            if(item == null || !item.matches(e, matcher, cond)) //null in case of syntax error (missing term)
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
		
		public boolean matches(Element e, ElementMatcher matcher, MatchCondition cond) {
			if(name!=null && WILDCARD.equals(name)) return true;
			return matcher.matchesName(e, name);
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
			return CssEscape.escapeCssIdentifier(name);
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
    	
    	public boolean matches(Element e, ElementMatcher matcher, MatchCondition cond) {
    		return matcher.matchesClass(e, className);
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
    		return "." + CssEscape.escapeCssIdentifier(className);
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
    
    public static class PseudoPageImpl implements PseudoPage {

        private final String name;
        private final PseudoPageType type;
        
        protected PseudoPageImpl(String name) {
            this.name = name;
            type = PseudoPageType.forName(name);
        }
        
        @Override
        public String getName() {
            return name;
        }

        @Override
        public PseudoPageType getType() {
            return type;
        }

        @Override
        public void computeSpecificity(Specificity spec) {
            spec.add(Level.C);
        }
        
        @Override
        public boolean matches(Element e, ElementMatcher matcher, MatchCondition cond) {
            // TODO: check this implementation
            if (type == null) {
                return false; // unknown or unimplemented pseudo
            }
            return cond.isSatisfied(e, this);
        }
        
        @Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(OutputUtil.PSEUDO_OPENING);
			
			if(name!=null) 
				sb.append(name);
			
			return sb.toString();
		}

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 23 * hash + Objects.hashCode(this.name);
            hash = 23 * hash + Objects.hashCode(this.type);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final PseudoPageImpl other = (PseudoPageImpl) obj;
            if (!Objects.equals(this.name, other.name)) {
                return false;
            }
            if (this.type != other.type) {
                return false;
            }
            return true;
        }
        
    }
    
    public static class PseudoClassImpl implements PseudoClass {

        private final String name;
        private final String functionValue;
        private final PseudoClassType type;
        private Selector nestedSelector; // for :not(sel)
    	private int[] elementIndex; // decoded element index for nth-XXXX properties -- values a and b in the an+b specification
        
        private PseudoClassImpl(String name, String functionValue, Selector nestedSelector) {
            this.name = name;
            type = PseudoClassType.forName(name);
            this.functionValue = functionValue;
            this.nestedSelector = nestedSelector;
            
            // Type-specific initialization
            if (type != null) {
                switch (type) {
                    case NOT:
                        if (nestedSelector == null && functionValue != null) {
                            nestedSelector = new SelectorImpl();
                            nestedSelector.add(new ElementNameImpl(functionValue));
                        }
                        break;
                    case NTH_CHILD:
                    case NTH_LAST_CHILD:
                    case NTH_OF_TYPE:
                    case NTH_LAST_OF_TYPE:
                        elementIndex = decodeIndex(functionValue);
                        break;
                }
            }
        }
        
        protected PseudoClassImpl(String name) {
            this(name, null, null);
        }
        
        protected PseudoClassImpl(String name, String functionValue) {
            this(name, functionValue, null);
        }
        
        protected PseudoClassImpl(String name, Selector nestedSelector) {
            this(name, null, nestedSelector);
        }
        
        @Override
        public String getName() {
            return name;
        }
        
        @Override
        public String getFunctionValue() {
            return functionValue;
        }

        @Override
        public PseudoClassType getType() {
            return type;
        }

        @Override
        public Selector getNestedSelector() {
            return nestedSelector;
        }

        @Override
        public void computeSpecificity(Specificity spec) {
            spec.add(Level.C);
        }
        
        @Override
        public boolean matches(Element e, ElementMatcher matcher, MatchCondition cond) {
            if (type == null) {
                return false; // unknown or unimplemented pseudo
            }
            
            switch (type) {
                case FIRST_CHILD:
                case LAST_CHILD:
                case ONLY_CHILD:
                    if (e.getParentNode().getNodeType() == Node.ELEMENT_NODE)
                    {
                        boolean first = false;
                        boolean last = false;
                        if (type != PseudoClassType.LAST_CHILD) {
                            Node prev = e;
                            do {
                                prev = prev.getPreviousSibling();
                                if (prev == null) {
                                    first = true;
                                    break;
                                }
                            } while(prev.getNodeType() != Node.ELEMENT_NODE);
                        }
                        if (type != PseudoClassType.FIRST_CHILD) {
                            Node next = e;
                            do {
                                next = next.getNextSibling();
                                if (next == null) {
                                    last = true;
                                    break; 
                                }
                            } while(next.getNodeType() != Node.ELEMENT_NODE);
                        }
                        switch (type) {
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
                        if (type != PseudoClassType.LAST_OF_TYPE) {
                            Node prev = e;
                            firstt = true;
                            do {
                                prev = prev.getPreviousSibling();
                                if (prev != null && prev.getNodeType() == Node.ELEMENT_NODE
                                        && isSameElementType(e, (Element) prev))
                                    firstt = false;
                            } while (prev != null && firstt);
                        }
                        if (type != PseudoClassType.FIRST_OF_TYPE) {
                            Node next = e;
                            lastt = true;
                            do {
                                next = next.getNextSibling();
                                if (next != null && next.getNodeType() == Node.ELEMENT_NODE
                                        && isSameElementType(e, (Element) next))
                                    lastt = false;
                            } while(next != null && lastt);
                        }
                        switch (type) {
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
                case NOT:
                    return nestedSelector != null && !nestedSelector.matches(e, matcher, cond);
                default:
                    // match all pseudo classes specified by an additional condition (usually used for using LINK pseudo class for links)
                    return cond.isSatisfied(e, this);
            }
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
		 * Checks whether the element position matches a <code>an+b</code> index specification.
		 * @param pos The element position according to some counting criteria.
		 * @param n The index specification <code>an+b</code> - <code>a</code> and <code>b</code> values in array int[2].
		 * @return <code>true</code> when the position matches the index.
		 */
		protected boolean positionMatches(int pos, int[] n)
		{
		    if (n != null)
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
		    else //no indices specified (syntax error or missing values)
		        return false;
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
		 * Decodes the element index in the <code>an+b</code> form.
		 * @param index the element index string
		 * @return an array of two integers <code>a</code> and <code>b</code>, or null if the index string is malformatted
		 */
		protected int[] decodeIndex(String index)
		{
            if (index == null) {
                return null;
            }
		    String s = index.toLowerCase().trim();
		    if (s.equals("odd")){
                return new int[] {2, 1};
		    }
		    if (s.equals("even")){
                return new int[] {2, 0};
            }
		    try {
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
            } catch (NumberFormatException nfe) {
                return null;
            }
		}
        
        @Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(OutputUtil.PSEUDO_OPENING);
			
			if(name!=null) 
				sb.append(name);
            
			if(nestedSelector != null)
			    sb.append(OutputUtil.FUNCTION_OPENING).append(nestedSelector.toString()).append(OutputUtil.FUNCTION_CLOSING);
			else if(functionValue != null)
			    sb.append(OutputUtil.FUNCTION_OPENING).append(CssEscape.escapeCssIdentifier(functionValue)).append(OutputUtil.FUNCTION_CLOSING);
			
			return sb.toString();
		}
        
        @Override
        public int hashCode() {
            int hash = 17;
            hash = 43 * hash + Objects.hashCode(this.name);
            hash = 43 * hash + Objects.hashCode(this.functionValue);
            hash = 43 * hash + Objects.hashCode(this.type);
            hash = 43 * hash + Objects.hashCode(this.nestedSelector);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final PseudoClassImpl other = (PseudoClassImpl) obj;
            if (this.type != other.type) {
                return false;
            }
            if (!Objects.equals(this.name, other.name)) {
                return false;
            }
            if (!Objects.equals(this.functionValue, other.functionValue)) {
                return false;
            }
            if (!Objects.equals(this.nestedSelector, other.nestedSelector)) {
                return false;
            }
            return true;
        }
    }
    
    public static class PseudoElementImpl implements PseudoElement {

        private final String name;
        private final String functionValue;
        private final PseudoElementType type;
        private Selector nestedSelector; // for ::cue(sel)
        
        private PseudoElementImpl(String name, String functionValue, Selector nestedSelector) {
            this.name = name;
            type = PseudoElementType.forName(name);
            this.functionValue = functionValue;
            this.nestedSelector = nestedSelector;
            
            // Type-specific initialization
            if (type != null) {
                switch (type) {
                    case CUE:
                        if (nestedSelector == null && functionValue != null) {
                            nestedSelector = new SelectorImpl();
                            nestedSelector.add(new ElementNameImpl(functionValue));
                        }
                        break;
                }
            }
        }
        
        protected PseudoElementImpl(String name) {
            this(name, null, null);
        }
        
        protected PseudoElementImpl(String name, String functionValue) {
            this(name, functionValue, null);
        }
        
        protected PseudoElementImpl(String name, Selector nestedSelector) {
            this(name, null, nestedSelector);
        }
        
        @Override
        public String getName() {
            return name;
        }
        
        @Override
        public String getFunctionValue() {
            return functionValue;
        }

        @Override
        public PseudoElementType getType() {
            return type;
        }

        @Override
        public Selector getNestedSelector() {
            return nestedSelector;
        }

        @Override
        public void computeSpecificity(Specificity spec) {
            spec.add(Level.D);
        }
        
        @Override
        public boolean matches(Element e, ElementMatcher matcher, MatchCondition cond) {
            // TODO: check
            if (type == null) {
                return false; // Unknown or unimplemented pseudo-element
            }
            return true; // All pseudo-elements match
        }
        
        @Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(OutputUtil.PSEUDO_OPENING).append(OutputUtil.PSEUDO_OPENING);
			
			if(name!=null) 
				sb.append(name);
            
			if(nestedSelector != null)
			    sb.append(OutputUtil.FUNCTION_OPENING).append(nestedSelector.toString()).append(OutputUtil.FUNCTION_CLOSING);
			else if(functionValue != null)
			    sb.append(OutputUtil.FUNCTION_OPENING).append(CssEscape.escapeCssIdentifier(functionValue)).append(OutputUtil.FUNCTION_CLOSING);
			
			return sb.toString();
		}

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 53 * hash + Objects.hashCode(this.name);
            hash = 53 * hash + Objects.hashCode(this.functionValue);
            hash = 53 * hash + Objects.hashCode(this.type);
            hash = 53 * hash + Objects.hashCode(this.nestedSelector);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final PseudoElementImpl other = (PseudoElementImpl) obj;
            if (this.type != other.type) {
                return false;
            }
            if (!Objects.equals(this.name, other.name)) {
                return false;
            }
            if (!Objects.equals(this.functionValue, other.functionValue)) {
                return false;
            }
            if (!Objects.equals(this.nestedSelector, other.nestedSelector)) {
                return false;
            }
            return true;
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
    	
    	public boolean matches(Element e, ElementMatcher matcher, MatchCondition cond) {
    		return matcher.matchesID(e, id);
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
    		return "#" + CssEscape.escapeCssIdentifier(id);
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
		
		public boolean matches(Element e, ElementMatcher matcher, MatchCondition cond) {
			return matcher.matchesAttribute(e, attribute, value, operator);
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

    		if (value != null) {
        		if (isStringValue) {
        			sb.append(OutputUtil.STRING_OPENING);
        			sb.append(CssEscape.escapeCssString(value));
        			sb.append(OutputUtil.STRING_CLOSING);
        		} else {
                    sb.append(CssEscape.escapeCssIdentifier(value));
        		}
    		}

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

		public boolean matches(Element e, ElementMatcher matcher, MatchCondition cond) {
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
