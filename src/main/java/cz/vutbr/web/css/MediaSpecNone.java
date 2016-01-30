/**
 * MediaSpecNone.java
 *
 * Created on 8. 7. 2014, 10:51:43 by burgetr
 */
package cz.vutbr.web.css;

import java.util.List;

/**
 * A specific case of media specification that does not match any media query or expression.
 * @author burgetr
 */
public class MediaSpecNone extends MediaSpec
{

    /**
     * Creates the media specification that does not match any media query or expression.
     */
    public MediaSpecNone()
    {
        super("!");
    }

    @Override
    public boolean matches(MediaQuery q)
    {
        return false;
    }

    @Override
    public boolean matches(MediaExpression e)
    {
        return false;
    }

    @Override
    public boolean matchesOneOf(List<MediaQuery> queries)
    {
        return false;
    }
    
    @Override
    public boolean matchesEmpty()
    {
        return false;
    }

    @Override
    public String toString()
    {
        return "(no media)";
    }

}
