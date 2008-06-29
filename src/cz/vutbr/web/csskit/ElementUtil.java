package cz.vutbr.web.csskit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.w3c.dom.Element;

import cz.vutbr.web.css.SimpleSelector;

public class ElementUtil {

	public static final String CLASS_DELIM = " ";
	public static final String CLASS_ATTR = "class";
	public static final String ID_ATTR = "id";
	
	public static Collection<String> elementClasses(Element e) {
		
		String classNames = e.getAttribute(CLASS_ATTR);
		
		if(classNames==null) Collections.emptyList();
		
		Collection<String> list = new ArrayList<String>();
		
		for(String cname: classNames.toLowerCase().split(CLASS_DELIM)) {
			cname = cname.trim();
			if(cname.length()>0)
				list.add(cname);
		}
		
		return list;
		
	}
	
	public static boolean matchesClass(Element e, String className) {
		return elementClasses(e).contains(className);
	}
	
	
	public static String elementID(Element e) {
		String id = e.getAttribute(ID_ATTR);
		
		if(id==null) return null;
		
		return id.toLowerCase();
	}
	
	public static boolean matchesID(Element e, String id) {
		return id.equals(elementID(e));
	}
	
	public static String elementName(Element e) {
		String name = e.getNodeName();
		
		if(name==null) return null;
		
		return name.toLowerCase();
	}
	
	public static boolean matchesName(Element e, String name) {
		if(name==null) return false;
		
		name = name.toLowerCase();
		
		return name.equals(elementName(e));
	}
	
	public static boolean matchesAttribute(Element e, String name, String value, SimpleSelector.Operator o) {
		
		String attributeValue = e.getAttribute(name);
		
		if(value==null && attributeValue==null) return true;
		else if(attributeValue==null) return false;
		else if(o==null) return false;
		
		// make both lowercase
		attributeValue = attributeValue.toLowerCase();
		value = value.toLowerCase();
		
		switch(o) {
		case EQUALS:
			return attributeValue.equals(value);
		case INCLUDES:
			attributeValue = " " + attributeValue + " ";
			return attributeValue.matches(".* " + value + " .*");
		case DASHMATCH:
			return attributeValue.matches("^" + value + "(\\|.*)*");
		default:
			return false;
		}
	}
	
}
