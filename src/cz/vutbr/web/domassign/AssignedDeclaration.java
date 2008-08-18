package cz.vutbr.web.domassign;

import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.csskit.DeclarationImpl;

/**
 * Adds specificity to declaration from its selector.
 * This class shares declaration with its parent class.
 *  
 * @author kapy
 */
public class AssignedDeclaration extends DeclarationImpl implements Declaration {
	
	protected CombinedSelector.Specificity spec;

	/**
	 * Creates assigned declaration from specificity and shallow copy of declaration
	 * @param d Declaration to be shallow-copied
	 * @param spec Specificity
	 */
	public AssignedDeclaration(Declaration d, CombinedSelector.Specificity spec) {
		super(d);
		this.spec = spec;
	}
	
	/**
	 * Creates assigned declaration from selector and shallow copy of declaration 
	 * @param d Declaration to be shallow-copied
	 * @param s CombinedSelector, which's specificity is computed inside
	 */
	public AssignedDeclaration(Declaration d, CombinedSelector s) {
		this(d, s.computeSpecificity());
	}
	
	@Override
	public int compareTo(Declaration other) {
		
		if( !(other instanceof AssignedDeclaration))
			return super.compareTo(other);
		
		AssignedDeclaration o = (AssignedDeclaration) other;

		int result = super.compareTo(o);
		if(result!=0) return result;
		
		return this.spec.compareTo(o.spec);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((spec == null) ? 0 : spec.hashCode());
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
		AssignedDeclaration other = (AssignedDeclaration) obj;
		if (spec == null) {
			if (other.spec != null)
				return false;
		} else if (!spec.equals(other.spec))
			return false;
		return true;
	}
	
	
	
}
