package cz.vutbr.web.domassign;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.w3c.dom.Element;

public class ElementUtil {

	public static final String WILDCARD = "*";
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
		return name!=null && (name.equals(elementName(e)) || WILDCARD.equals(name));
	}
}
