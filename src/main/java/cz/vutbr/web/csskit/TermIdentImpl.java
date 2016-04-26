package cz.vutbr.web.csskit;

import org.unbescape.css.CssEscape;

import cz.vutbr.web.css.CSSNodeVisitor;
import cz.vutbr.web.css.TermIdent;

/**
 * TermIdent
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 */
public class TermIdentImpl extends TermImpl<String> implements TermIdent {    
    
    protected TermIdentImpl() {
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
        if(operator != null) sb.append(operator.value());
        if(value!=null) sb.append(CssEscape.escapeCssIdentifier(value));

        return sb.toString();
    }
    
}
