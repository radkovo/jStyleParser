package cz.vutbr.web.domassign;

import java.util.List;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.csskit.OutputUtil;

/**
 * Adds specificity to declaration from its selector.
 * This class shares declaration with its parent class 
 * @author kapy
 * @since 1.0
 */
public class AssignedDeclaration implements Declaration {
	
	protected String property;
	protected List<Term> terms;
	protected boolean important;
	protected int level;
	
	protected Selector.Specificity spec;

	/**
	 * Creates assigned declaration from specificity and shallow copy of declaration
	 * @param d Declaration to be shallow-copied
	 * @param spec Specificity
	 * @param level Nesting level in DOM tree 
	 */
	public AssignedDeclaration(Declaration d, Selector.Specificity spec, int level) {
		this.important = d.isImportant();
		this.property = d.getProperty();
		this.terms = d.getTerms();
		this.spec = spec;
		this.level = level;
	}
	
	/**
	 * Creates assigned declaration from selector and shallow copy of declaration 
	 * @param d Declaration to be shallow-copied
	 * @param s Selector, which's specificity is computed inside
	 * @param level Nesting level in DOM tree
	 */
	public AssignedDeclaration(Declaration d, Selector s, int level) {
		this(d, s.computeSpecificity(), level);
	}
	
	public int compareTo(Declaration other) {
		
		if( !(other instanceof AssignedDeclaration))
			return 0;
		
		final AssignedDeclaration o = (AssignedDeclaration) other;
		
		if(this.level > o.level) return 1;
		if(this.level < o.level) return -1;
		
		if(this.isImportant() && ! o.isImportant())
            return 1;
        else if(o.isImportant() && ! this.isImportant())
            return -1;
		
		return this.spec.compareTo(o.spec);
	}
	
	public boolean isInherited(int level) {
		return level > this.level;
	}
		
	public int getInheritanceLevel() {
		return level;
	}
	
	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @param property the property to set
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * @return the terms
	 */
	public List<Term> getTerms() {
		return terms;
	}

	/**
	 * @param terms the terms to set
	 */
	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}

	/**
	 * @return the important
	 */
	public boolean isImportant() {
		return important;
	}

	/**
	 * @param important the important to set
	 */
	public void setImportant(boolean important) {
		this.important = important;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (important ? 1231 : 1237);
		result = prime * result + level;
		result = prime * result
				+ ((property == null) ? 0 : property.hashCode());
		result = prime * result + ((spec == null) ? 0 : spec.hashCode());
		result = prime * result + ((terms == null) ? 0 : terms.hashCode());
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
		if (!(obj instanceof AssignedDeclaration))
			return false;
		final AssignedDeclaration other = (AssignedDeclaration) obj;
		if (important != other.important)
			return false;
		if (level != other.level)
			return false;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		if (spec == null) {
			if (other.spec != null)
				return false;
		} else if (!spec.equals(other.spec))
			return false;
		if (terms == null) {
			if (other.terms != null)
				return false;
		} else if (!terms.equals(other.terms))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return toString(0);
	}
	
	public String toString(int depth) {
		
		StringBuilder sb = new StringBuilder();
		
		// add property
		sb = OutputUtil.appendTimes(sb, OutputUtil.DEPTH_DELIM, depth);
		sb.append(property).append(OutputUtil.PROPERTY_OPENING);
		
		// add terms
		sb = OutputUtil.appendList(sb, terms, OutputUtil.EMPTY_DELIM)
				.append(OutputUtil.PROPERTY_CLOSING);
		
        return sb.toString();
	}
}
