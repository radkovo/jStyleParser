/**
 * MediaExpresionImpl.java
 *
 * Created on 26. 6. 2014, 15:55:01 by burgetr
 */
package cz.vutbr.web.csskit;

import java.util.Locale;

import cz.vutbr.web.css.MediaExpression;
import cz.vutbr.web.css.Term;

/**
 * 
 * @author burgetr
 */
public class MediaExpressionImpl extends AbstractRule<Term<?>> implements MediaExpression
{
    protected String feature;
    protected boolean negative;

    @Override
    public String getFeature()
    {
        return feature;
    }

    @Override
    public void setFeature(String feature)
    {
        this.feature = feature.trim().toLowerCase(Locale.ENGLISH);
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
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append(OutputUtil.MEDIA_EXPR_OPENING);
        sb.append(getFeature()).append(OutputUtil.MEDIA_FEATURE_DELIM);
        sb = OutputUtil.appendList(sb, list, OutputUtil.SPACE_DELIM);
        sb.append(OutputUtil.MEDIA_EXPR_CLOSING);
        
        if (isNegative()) {
            sb.insert(0, "(NOT ");
            sb.append(")");
        }
        
        return sb.toString();
    }

}
