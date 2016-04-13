package cz.vutbr.web.csskit;

import cz.vutbr.web.css.CSSNodeVisitor;
import cz.vutbr.web.css.TermPercent;

/**
 * TermPercent
 * @author burgetr
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko
 * @version 1.0 * Construction moved to parser
 * 				 * Rewritten toString() method
 */
public class TermPercentImpl extends TermFloatValueImpl implements TermPercent {

    protected TermPercentImpl() {
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
    public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if(operator!=null) sb.append(operator.value());
		sb.append(value).append(OutputUtil.PERCENT_SIGN);

		return sb.toString();
	}

    public boolean isPercentage()
    {
        return true;
    }
	
}
