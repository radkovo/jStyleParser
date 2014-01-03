/**
 * MatchCondition.java
 *
 * Created on 1.7.2013, 11:03:32 by burgetr
 */
package cz.vutbr.web.css;

import cz.vutbr.web.css.Selector.SelectorPart;

import org.w3c.dom.Element;

/**
 * An additional condition for matching the selectors.
 * 
 * @author burgetr
 */
public interface MatchCondition
{
    
    /**
     * Checks whether the condition is satisfied for the given element and selector part. 
     * @param e The element to be tested.
     * @param selpart The selector part.
     * @return <code>true</code> when the condition is satisfied
     */
    public boolean isSatisfied(Element e, SelectorPart selpart);

}
