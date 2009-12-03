package test;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

public class ElementMap {

	private Map<String, Element> elementIDs;
	private Map<String, Element> elementNames;
	
	public ElementMap(Document doc) {
		elementIDs = new HashMap<String, Element>();
		elementNames = new HashMap<String, Element>();
        DocumentTraversal traversal = (DocumentTraversal) doc;
        TreeWalker w = traversal.createTreeWalker(doc, NodeFilter.SHOW_ELEMENT, null, false);
		//TreeWalker w = new TidyTreeWalker(doc, NodeFilter.SHOW_ELEMENT);
		Element current;
		while ((current = (Element) w.nextNode()) != null) {
			elementNames.put(current.getNodeName().toLowerCase(), current);
			String id = current.getAttribute("id");
			if(id!=null)
				elementIDs.put(id, current);
		}
	}
	
	
	public Element getElementById(String id) {
		return elementIDs.get(id);
	}
	
	public Element getLastElementByName(String name) {
		return elementNames.get(name.toLowerCase());
	}

}
