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
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append(OutputUtil.MEDIA_EXPR_OPENING);
        sb.append(getFeature()).append(OutputUtil.MEDIA_FEATURE_DELIM);
        sb = OutputUtil.appendList(sb, list, OutputUtil.SPACE_DELIM);
        sb.append(OutputUtil.MEDIA_EXPR_CLOSING);
        
        return sb.toString();
    }

}
