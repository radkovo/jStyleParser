/**
 * ElementMatcherSafeStd.java
 *
 * Created on 26. 11. 2015, 15:20:08 by burgetr
 */
package cz.vutbr.web.csskit;

import org.w3c.dom.Element;

/**
 * A matcher that corresponds to the HTML standard mode matching. The matching is case-sensitive
 * except of element names which are case-insensitive. 
 * 
 * This is a safe implementation of the element matcher. It should be compatible with any
 * DOM implementation. On the other hand, its performance is slightly worse because of some
 * additional tests required due to the differences among the DOM implementations. 
 * 
 * @author burgetr
 */
public class ElementMatcherSafeStd extends ElementMatcherSafeCS
{

    @Override
    public boolean matchesName(Element e, String name)
    {
        return name.equalsIgnoreCase(elementName(e));
    }

}
