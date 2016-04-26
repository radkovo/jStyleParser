/**
 * TermResolutionImpl.java
 *
 * Created on 2. 7. 2014, 14:57:29 by burgetr
 */
package cz.vutbr.web.csskit;

import cz.vutbr.web.css.CSSNodeVisitor;
import cz.vutbr.web.css.TermResolution;

/**
 * 
 * @author burgetr
 */
public class TermResolutionImpl extends TermFloatValueImpl implements TermResolution
{
    
    protected TermResolutionImpl() 
    {
    }
    
    /**
	 * Accept method required by the visitor pattern for traversing the CSS Tree. 
	 * 
	 * @param visitor
	 * 	The visitor interface
	 * @return
	 * 	The current CSS Object
	 */
	@Override
	public Object accept(CSSNodeVisitor visitor) {
		return visitor.visit(this);
	}
}
