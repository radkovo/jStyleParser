package cz.vutbr.web.csskit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import cz.vutbr.web.css.Selector;

public class ElementUtil {

	public static final String CLASS_DELIM = " ";
	public static final String CLASS_ATTR = "class";
	public static final String ID_ATTR = "id";
	
	public static Collection<String> elementClasses(Element e) 
	{
	    if (e.hasAttribute(CLASS_ATTR))
	    {
    		String classNames = e.getAttribute(CLASS_ATTR);
    		
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
	
	public static boolean matchesClass(Element e, String className) 
	{
        if (e.hasAttribute(CLASS_ATTR))
        {
            String classNames = e.getAttribute(CLASS_ATTR).toLowerCase();
            int len = className.length();
    	    int start = classNames.indexOf(className.toLowerCase());
    	    if (start == -1)
    	        return false;
    	    else
    	        return ((start == 0 || classNames.charAt(start - 1) == ' ') &&
    	                (start + len == classNames.length() || classNames.charAt(start + len) == ' '));
        }
        else
            return false;
	}
	
	public static String elementID(Element e) 
	{
		String id = e.getAttribute(ID_ATTR);
		return id;
	}
	
	public static boolean matchesID(Element e, String id) 
	{
		return id.equalsIgnoreCase(elementID(e));
	}
	
	public static String elementName(Element e) 
	{
		String name = e.getNodeName();
		return name;
	}
	
	public static boolean matchesName(Element e, String name)
	{
		if (name == null)
		    return false;
		else
		    return name.equalsIgnoreCase(elementName(e));
	}
	
	public static boolean matchesAttribute(Element e, String name, String value, Selector.Operator o) 
	{
	    Node attributeNode = e.getAttributeNode(name);
	    if (attributeNode != null && o != null)
	    {
    	    String attributeValue = attributeNode.getNodeValue();
    		
    		switch(o) {
        		case EQUALS:
        			return attributeValue.equals(value);
        		case INCLUDES:
        			attributeValue = " " + attributeValue + " ";
        			return attributeValue.matches(".* " + value + " .*");
        		case DASHMATCH:
        			return attributeValue.matches("^" + value + "(\\|.*)*");
        		default:
        			return true;
    		}
	    }
	    else
	        return false;
	}
	
}
