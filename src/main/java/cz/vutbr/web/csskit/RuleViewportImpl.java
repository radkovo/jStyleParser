/**
 * RuleViewportImpl.java
 *
 * Created on 8.2.2013, 15:44:19 by burgetr
 */
package cz.vutbr.web.csskit;

import cz.vutbr.web.css.CSSNodeVisitor;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleViewport;

/**
 * Set of declarations bound with the viewport.
 * 
 * @author burgetr
 */
public class RuleViewportImpl extends AbstractRuleBlock<Declaration> implements RuleViewport
{

    protected RuleViewportImpl() 
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
        
        sb.append(OutputUtil.VIEWPORT_KEYWORD).append(OutputUtil.SPACE_DELIM);
        
        // append declarations
        sb.append(OutputUtil.RULE_OPENING);
        sb = OutputUtil.appendList(sb, list, OutputUtil.EMPTY_DELIM, depth + 1);
        sb.append(OutputUtil.RULE_CLOSING);
    
        return sb.toString();
    }


    
}
