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
	
	protected Selector.Specificity spec;
	
	/**
	 * Creates assigned declaration from specificity and shallow copy of declaration
	 * @param d Declaration to be shallow-copied
	 * @param spec Specificity 
	 */
	public AssignedDeclaration(Declaration d, Selector.Specificity spec) {
		super(d);
		this.spec = spec;
	}
	
	/**
	 * Creates assigned declaration from selector and shallow copy of declaration 
	 * @param d Declaration to be shallow-copied
	 * @param s Selector, which's specificity is computed inside
	 */
	public AssignedDeclaration(Declaration d, Selector s) {
		super(d);
		this.spec = s.computeSpecificity();
	}
	
	public int compareTo(AssignedDeclaration o) {
		
		if(this.isImportant() && ! o.isImportant())
            return 1;
        else if(o.isImportant() && ! this.isImportant())
            return -1;
		
		return this.spec.compareTo(o.spec);
	}
	
	
}
