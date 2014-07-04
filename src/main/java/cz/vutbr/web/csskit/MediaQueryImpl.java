/**
 * MediaQueryImpl.java
 *
 * Created on 26. 6. 2014, 15:43:53 by burgetr
 */
package cz.vutbr.web.csskit;

import java.util.Locale;

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
            sb.append(getType());
            if (!isEmpty())
                sb.append(OutputUtil.QUERY_DELIM);
        }
        
        sb = OutputUtil.appendList(sb, list, OutputUtil.QUERY_DELIM);
        
        return sb.toString();
    }

}
