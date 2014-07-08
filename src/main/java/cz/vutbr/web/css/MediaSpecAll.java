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
    public boolean matches(MediaQuery q)
    {
        return true;
    }

    @Override
    public boolean matches(MediaExpression e)
    {
        return true;
    }

    @Override
    public boolean matchesOneOf(List<MediaQuery> queries)
    {
        return !queries.isEmpty(); //we don't match an empty list (to be consistent)
    }

    @Override
    public String toString()
    {
        return "(all media)";
    }

}
