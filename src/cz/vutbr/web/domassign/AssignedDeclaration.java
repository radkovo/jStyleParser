package cz.vutbr.web.domassign;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.csskit.DeclarationImpl;

/**
 * Adds specificity to declaration from its selector.
 * This class shares declaration with its parent class 
 * @author kapy
 * @since 1.0
 */
public class AssignedDeclaration extends DeclarationImpl implements Comparable<AssignedDeclaration>, Declaration {
	
	protected int level;
	
	protected Selector.Specificity spec;

	/**
	 * Creates assigned declaration from specificity and shallow copy of declaration
	 * @param d Declaration to be shallow-copied
	 * @param spec Specificity
	 * @param level Nesting level in DOM tree 
	 */
	public AssignedDeclaration(Declaration d, Selector.Specificity spec, int level) {
		super(d);
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
	
	public int compareTo(AssignedDeclaration o) {
		
		if(this.level > o.level) return 1;
		if(this.level < o.level) return -1;
		
		if(this.isImportant() && ! o.isImportant())
            return 1;
        else if(o.isImportant() && ! this.isImportant())
            return -1;
		
		return this.spec.compareTo(o.spec);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (important ? 1231 : 1237);
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
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof AssignedDeclaration))
			return false;
		final AssignedDeclaration other = (AssignedDeclaration) obj;
		if (important != other.important)
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
	
	
	
}
