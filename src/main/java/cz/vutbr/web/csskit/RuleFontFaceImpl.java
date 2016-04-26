/**
 * RuleFontFaceImpl.java
 *
 * Created on 1.2.2013, 14:28:51 by burgetr
 */
package cz.vutbr.web.csskit;

import cz.vutbr.web.css.CSSNodeVisitor;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleFontFace;

/**
 * Wrap of declarations bound with a font specification
 *  
 * @author burgetr
 */
public class RuleFontFaceImpl extends AbstractRuleBlock<Declaration> implements RuleFontFace 
{
    
    protected RuleFontFaceImpl() 
    {
        super();
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
    
    @Override 
    public String toString() 
    {
        return this.toString(0);
    }
    
    public String toString(int depth) 
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append(OutputUtil.FONT_FACE_KEYWORD).append(OutputUtil.SPACE_DELIM);
        
        // append declarations
        sb.append(OutputUtil.RULE_OPENING);
        sb = OutputUtil.appendList(sb, list, OutputUtil.EMPTY_DELIM, depth + 1);
        sb.append(OutputUtil.RULE_CLOSING);
    
        return sb.toString();
    }

}
