/**
 * MediaQueryImpl.java
 *
 * Created on 26. 6. 2014, 15:43:53 by burgetr
 */
package cz.vutbr.web.csskit;

import java.util.Locale;

import org.unbescape.css.CssEscape;

import cz.vutbr.web.css.CSSNodeVisitor;
import cz.vutbr.web.css.MediaExpression;
import cz.vutbr.web.css.MediaQuery;

/**
 * 
 * @author burgetr
 */
public class MediaQueryImpl extends AbstractRule<MediaExpression> implements MediaQuery
{
    protected boolean negative;
    protected String type;
    
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

    public MediaQueryImpl()
    {
        negative = false;
        type = null;
    }
    
    public MediaQueryImpl(String type, boolean negative)
    {
        this.negative = negative;
        this.type = type.trim().toLowerCase(Locale.ENGLISH);
    }
    
    @Override
    public boolean isNegative()
    {
        return negative;
    }
    
    @Override
    public void setNegative(boolean negative)
    {
        this.negative = negative;
    }

    @Override
    public String getType()
    {
        return type;
    }

    @Override
    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        if (isNegative())
            sb.append("NOT ");
        
        if (getType() != null)
        {
            sb.append(CssEscape.escapeCssIdentifier(getType()));
            if (!isEmpty())
                sb.append(OutputUtil.QUERY_DELIM);
        }
        
        sb = OutputUtil.appendList(sb, list, OutputUtil.QUERY_DELIM);
        
        return sb.toString();
    }

}
