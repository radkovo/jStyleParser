/**
 * ElementMatcher.java
 *
 * Created on 25. 11. 2015, 13:32:58 by burgetr
 */
package cz.vutbr.web.css;

import java.util.Collection;

import org.w3c.dom.Element;

/**
 * Element matcher provides an abstraction of the DOM access and the element matching
 * based on their names, classes and IDs. The implementation may be adjusted according
 * to the used DOM implementation. Different implementations may be case sensitive
 * or insensitive depending on the DOM source (e.g. HTML vs. XML).
 * 
 * @author burgetr
 */
public interface ElementMatcher
{
    /**
     * Obtains the value of an element attribute.
     * @param e The DOM element.
     * @param name Attribute name.
     * @return The value of the given attribute or an empty string when the attribute
     * is not present.
     */
    public String getAttribute(Element e, String name);
    
    /**
     * Obtains a collection of class names assigned to the given element
     * using its {@code class} attribute.
     * @param e The DOM element
     * @return the list of class names (possibly empty).
     */
    public Collection<String> elementClasses(Element e); 
    
    /**
     * Checks whether the given element has the given class assigned.
     * @param e The DOM element
     * @param className the class name to check
     * @return {@code true} when any of the element classes matches the given class.
     */
    public boolean matchesClass(Element e, String className);
    
    /**
     * Obtains the ID of the given element when specified.
     * @param e The DOM element
     * @return The element ID or an empty string when not specified.
     */
    public String elementID(Element e);
    
    /**
     * Checks whether the given element has the given ID assigned.
     * @param e The DOM element
     * @param id the class name to check
     * @return {@code true} when the element has the given ID.
     */
    public boolean matchesID(Element e, String id); 
    
    /**
     * Obtains the name of the given element.
     * @param e The DOM element
     * @return The element name.
     */
    public String elementName(Element e);
    
    /**
     * Checks whether the given element has the given name.
     * @param e The DOM element
     * @param name the element name to check
     * @return {@code true} when the element has the given name.
     */
    public boolean matchesName(Element e, String name);
    
    /**
     * Checks whether the given element attribute has the given value.
     * @param e The DOM element
     * @param name the attribute name
     * @param value the attribute value
     * @return {@code true} when the given attribute of the element has the given value.
     */
    public boolean matchesAttribute(Element e, String name, String value, Selector.Operator o); 

}
