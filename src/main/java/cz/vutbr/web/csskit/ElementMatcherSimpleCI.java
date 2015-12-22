/**
 * ElementMatcherSimpleCI.java
 *
 * Created on 25. 11. 2015, 15:29:26 by burgetr
 */
package cz.vutbr.web.csskit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import cz.vutbr.web.css.ElementMatcher;
import cz.vutbr.web.css.Selector;

/**
 * A case-insensitive matcher that corresponds to the HTML quirks mode matching. 
 * 
 * This is a simplified implementation of the element matcher. This implementation requires
 * that the {@code Element.getAttribute()} method provided by the DOM implementation returns
 * an empty string (not {@code null}) when the attribute is not defined. 
 * 
 * @author burgetr
 */
public class ElementMatcherSimpleCI implements ElementMatcher
{
    public static final String CLASS_DELIM = " ";
    public static final String CLASS_ATTR = "class";
    public static final String ID_ATTR = "id";
    
    public String getAttribute(final Element e, final String name)
    {
        return e.getAttribute(name);
    }
    
    public Collection<String> elementClasses(final Element e) 
    {
        final String classNames = e.getAttribute(CLASS_ATTR);
        if (!classNames.isEmpty())
        {
            Collection<String> list = new ArrayList<String>();
            for (String cname : classNames.toLowerCase().split(CLASS_DELIM)) 
            {
                cname = cname.trim();
                if(cname.length() > 0)
                    list.add(cname);
            }
            return list;
        }
        else
            return Collections.emptyList();
    }
    
    public boolean matchesClass(final Element e, final String className)
    {
        final String classNames = e.getAttribute(CLASS_ATTR).toLowerCase();
        if (!classNames.isEmpty())
        {
            final String search = className.toLowerCase();
            final int len = className.length();
            int lastIndex = 0;
            
            while ((lastIndex = classNames.indexOf(search, lastIndex)) != -1) {
                if ((lastIndex == 0 || Character.isWhitespace(classNames.charAt(lastIndex - 1))) &&
                        (lastIndex + len == classNames.length() || Character.isWhitespace(classNames.charAt(lastIndex + len)))) {
                    return true;
                }
                lastIndex += len;
            }
            return false;
        }
        else
            return false;
    }

    
    public String elementID(final Element e) 
    {
        return e.getAttribute(ID_ATTR);
    }
    
    public boolean matchesID(final Element e, final String id) 
    {
        return id.equalsIgnoreCase(e.getAttribute(ID_ATTR));
    }
    
    public String elementName(final Element e) 
    {
        return e.getNodeName();
    }
    
    public boolean matchesName(final Element e, final String name)
    {
        return name.equalsIgnoreCase(e.getNodeName());
    }
    
    public boolean matchesAttribute(final Element e, final String name, final String value, final Selector.Operator o) 
    {
        final Node attributeNode = e.getAttributeNode(name);
        if (attributeNode != null && o != null)
        {
            String attributeValue = attributeNode.getNodeValue();
            
            switch(o) {
                case EQUALS:
                    return attributeValue.equals(value);
                case INCLUDES:
                    if (value.isEmpty() || containsWhitespace(value))
                        return false;
                    else
                    {
                        attributeValue = " " + attributeValue + " ";
                        return attributeValue.matches(".* " + value + " .*");
                    }
                case DASHMATCH:
                    return attributeValue.matches("^" + value + "(-.*|$)");
                case CONTAINS:
                    return !value.isEmpty() && attributeValue.matches(".*" + value + ".*");
                case STARTSWITH:
                    return !value.isEmpty() && attributeValue.matches("^" + value + ".*");
                case ENDSWITH:
                    return !value.isEmpty() && attributeValue.matches(".*" + value + "$");
                default:
                    return true;
            }
        }
        else
            return false;
    }
    
    private static boolean containsWhitespace(String s)
    {
        for (int i = 0; i < s.length(); i++)
        {
            if (Character.isWhitespace(s.charAt(i)))
                    return true;
        }
        return false;
    }

}
