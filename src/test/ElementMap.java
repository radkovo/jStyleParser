package test;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

import cz.vutbr.web.domassign.TidyTreeWalker;

public class ElementMap {

	private Map<String, Element> elements;
	
	public ElementMap(Document doc) {
		elements = new HashMap<String, Element>();
		TreeWalker w = new TidyTreeWalker(doc, NodeFilter.SHOW_ELEMENT);
		Element current;
		while ((current = (Element) w.nextNode()) != null) {
			String id = current.getAttribute("id");
			if(id!=null)
				elements.put(id, current);
		}
	}
	
	
	public Element getElementById(String id) {
		return elements.get(id);
	}
}
