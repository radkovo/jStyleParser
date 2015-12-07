/**
 * ElementMatcherSimpleStd.java
 *
 * Created on 26. 11. 2015, 15:30:15 by burgetr
 */
package cz.vutbr.web.csskit;

import org.w3c.dom.Element;

/**
 * A matcher that corresponds to the HTML standard mode matching. The matching is case-sensitive
 * except of element names which are case-insensitive. 
 * 
 * This is a simplified implementation of the element matcher. This implementation requires
 * that the {@code Element.getAttribute()} method provided by the DOM implementation returns
 * an empty string (not {@code null}) when the attribute is not defined. 
 * 
 * @author burgetr
 */
public class ElementMatcherSimpleStd extends ElementMatcherSimpleCS
{

    @Override
    public boolean matchesName(Element e, String name)
    {
        return name.equalsIgnoreCase(elementName(e));
    }

}
