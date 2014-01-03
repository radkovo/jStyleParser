package cz.vutbr.web.domassign;

import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.csskit.DeclarationImpl;

/**
 * Adds specificity to declaration from its selector.
 * This class shares declaration with its parent class.
 *
 * @author burgetr
 * @author kapy
 */
public class AssignedDeclaration extends DeclarationImpl implements Declaration {
	
	protected CombinedSelector.Specificity spec;
	protected StyleSheet.Origin origin;

	/**
	 * Creates assigned declaration from specificity and shallow copy of declaration
	 * @param d Declaration to be shallow-copied
	 * @param spec Specificity
	 */
	public AssignedDeclaration(Declaration d, CombinedSelector.Specificity spec, StyleSheet.Origin origin) {
		super(d);
		this.spec = spec;
		this.origin = origin;
	}
	
	/**
	 * Creates assigned declaration from selector and shallow copy of declaration 
	 * @param d Declaration to be shallow-copied
	 * @param s CombinedSelector, which's specificity is computed inside
	 */
	public AssignedDeclaration(Declaration d, CombinedSelector s, StyleSheet.Origin origin) {
		this(d, s.computeSpecificity(), origin);
	}
	
	@Override
	public int compareTo(Declaration other) {
		
		if( !(other instanceof AssignedDeclaration))
			return super.compareTo(other);
		
		AssignedDeclaration o = (AssignedDeclaration) other;

		int res = getOriginOrder() - o.getOriginOrder();
		if (res == 0)
			return this.spec.compareTo(o.spec);
		else
			return res;
	}

	/**
	 * Computes the priority order of the declaration based on its origin and importance
	 * according to the CSS specification.
	 * @return The priority order (1..5).
	 * @see <a href="http://www.w3.org/TR/CSS21/cascade.html#cascading-order">http://www.w3.org/TR/CSS21/cascade.html#cascading-order</a>
	 */
	public int getOriginOrder()
	{
		if (important)
		{
			if (origin == StyleSheet.Origin.AUTHOR)
				return 4;
			else if (origin == StyleSheet.Origin.AGENT)
				return 1;
			else
				return 5;
		}
		else
		{
			if (origin == StyleSheet.Origin.AUTHOR)
				return 3;
			else if (origin == StyleSheet.Origin.AGENT)
				return 1;
			else
				return 2;
		}
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
