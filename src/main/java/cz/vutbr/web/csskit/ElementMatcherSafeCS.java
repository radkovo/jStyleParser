/**
 * ElementMatcherSafeCS.java
 *
 * Created on 25. 11. 2015, 15:17:40 by burgetr
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
 * A case-sensitive matcher that corresponds to the XHTML mode matching. 
 * 
 * This is a safe implementation of the element matcher. It should be compatible with any
 * DOM implementation. On the other hand, its performance is slightly worse because of some
 * additional tests required due to the differences among the DOM implementations. 
 * 
 * @author burgetr
 */
public class ElementMatcherSafeCS implements ElementMatcher
{
    public static final String CLASS_DELIM = " ";
    public static final String CLASS_ATTR = "class";
    public static final String ID_ATTR = "id";
    
    public String getAttribute(Element e, String name)
    {
        final String ret = e.getAttribute(name);
        return (ret == null) ? "" : ret;
    }
    
    public Collection<String> elementClasses(Element e) 
    {
        if (e.hasAttribute(CLASS_ATTR))
        {
            String classNames = getAttribute(e, CLASS_ATTR);
            
            Collection<String> list = new ArrayList<String>();
            for (String cname : classNames.split(CLASS_DELIM)) 
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
    
    public boolean matchesClass(Element e, String className)
    {
        if (e.hasAttribute(CLASS_ATTR))
        {
            final String classNames = e.getAttribute(CLASS_ATTR);
            final int len = className.length();
            int lastIndex = 0;
            
            while ((lastIndex = classNames.indexOf(className, lastIndex)) != -1) {
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

    
    public String elementID(Element e) 
    {
        return getAttribute(e, ID_ATTR);
    }
    
    public boolean matchesID(Element e, String id) 
    {
        return id.equals(elementID(e));
    }
    
    public String elementName(Element e) 
    {
        return e.getNodeName();
    }
    
    public boolean matchesName(Element e, String name)
    {
        return name.equals(elementName(e));
    }
    
    public boolean matchesAttribute(Element e, String name, String value, Selector.Operator o) 
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
