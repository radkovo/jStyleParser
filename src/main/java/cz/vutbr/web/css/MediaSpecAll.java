/**
 * MediaSpecAll.java
 *
 * Created on 8. 7. 2014, 10:46:22 by burgetr
 */
package cz.vutbr.web.css;

import java.util.List;

/**
 * A special case of media specification that matches all media queries.
 *  
 * @author burgetr
 */
public class MediaSpecAll extends MediaSpec
{

    /**
     * Creates the media specification that matches to all media queries and expressions.
     */
    public MediaSpecAll()
    {
        super("*");
    }

    @Override
    public boolean matches(MediaQuery q) {
        if ("all".equals(q.getType()) && q.isNegative())
            return false; // "NOT all" doesn't match anything
        return true;
    }

    @Override
    public boolean matches(MediaExpression e)
    {
        return true;
    }

    @Override
    public String toString()
    {
        return "(all media)";
    }

}
