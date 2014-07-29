/**
 * MediaSpecNone.java
 *
 * Created on 8. 7. 2014, 10:51:43 by burgetr
 */
package cz.vutbr.web.css;

import java.util.List;

/**
 * A specific case of media specification that does not match to any media quert and expression.
 * @author burgetr
 */
public class MediaSpecNone extends MediaSpec
{

    /**
     * Creates the media specification that does not match to any media quert and expression.
     */
    public MediaSpecNone()
    {
        super("!");
        setMatchEmpty(false); //do not match empty media queries
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
    public String toString()
    {
        return "(no media)";
    }

}
