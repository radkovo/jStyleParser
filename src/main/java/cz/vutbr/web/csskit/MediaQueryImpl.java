/**
 * MediaQueryImpl.java
 *
 * Created on 26. 6. 2014, 15:43:53 by burgetr
 */
package cz.vutbr.web.csskit;

import java.util.Locale;

import cz.vutbr.web.css.MediaExpression;
import cz.vutbr.web.css.MediaQuery;
import cz.vutbr.web.css.Term;

/**
 * 
 * @author burgetr
 */
public class MediaQueryImpl extends AbstractRule<MediaExpression> implements MediaQuery
{

    /**
     * Media query that doesn't match any medium.
     */
    public static final MediaQuery NOT_ALL = new MediaQueryImpl("all", true) {
            @Override
            public MediaQuery and(MediaQuery query) {
                return this;
            }
        };

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
    public MediaQuery and(MediaQuery query) {
        MediaQueryImpl combined;
        if (type == null) {
            combined = new MediaQueryImpl(query.getType(), negative);
        } else if (query.getType() == null) {
            combined = new MediaQueryImpl(type, negative);
        } else if ("all".equals(type)) {
            if (negative)
                return NOT_ALL;
            else
                combined = new MediaQueryImpl(query.getType(), negative);
        } else if ("all".equals(query.getType())) {
            if (query.isNegative())
                return NOT_ALL;
            else
                combined = new MediaQueryImpl(type, negative);
        } else if (type.equals(query.getType())) {
            if (negative == query.isNegative())
                combined = new MediaQueryImpl(type, negative);
            else
                return NOT_ALL;
        } else {
            if (negative == query.isNegative())
                return NOT_ALL;
            else if (negative)
                combined = new MediaQueryImpl(query.getType(), negative);
            else
                combined = new MediaQueryImpl(type, negative);
        }
        combined.addAll(this);
        if (negative == query.isNegative())
            combined.addAll(query);
        else
            for (MediaExpression feature : query) {
                MediaExpression negatedFeature = new MediaExpressionImpl();
                negatedFeature.unlock();
                negatedFeature.setNegative(true);
                for (Term<?> t : feature)
                    negatedFeature.add(t);
                combined.add(negatedFeature);
            }
        return combined;
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
