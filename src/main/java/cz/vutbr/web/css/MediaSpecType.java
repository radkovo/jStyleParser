/**
 * MediaSpecType.java
 *
 * Created on 8. 7. 2014, 11:01:23 by burgetr
 */
package cz.vutbr.web.css;

/**
 * A special case of media specification that matches all media queries with the given media type
 * regardless on the remaining media features. This specification <b>should be used with care</b> because
 * it matches even the disjunct queries. E.g. when used for {@code screen} media, it matches
 * both {@code screen AND (min-width: 1000px)} and {@code screen AND (max-width: 999px)}.
 * 
 * @author burgetr
 */
public class MediaSpecType extends MediaSpec
{

    /**
     * Creates a new media specification that matches all media queries with the given media type.
     * @param type
     */
    public MediaSpecType(String type)
    {
        super(type);
    }

    @Override
    public boolean matches(MediaExpression e)
    {
        return true; //we match all the expressions
    }

    @Override
    public String toString()
    {
        return type + "[*]";
    }
    
}
