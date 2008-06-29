package test;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;
import org.w3c.tidy.Tidy;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StyleSheetNotValidException;
import cz.vutbr.web.csskit.parser.CssParser;
import cz.vutbr.web.domassign.Analyzer;
import cz.vutbr.web.domassign.TidyTreeWalker;

public class AnalyzerTest {

	private static Document doc;
	private static StyleSheet sheet;
	private static Analyzer analyzer;
	private static TreeWalker walker;
	
	@BeforeClass
	public static void init() throws FileNotFoundException, StyleSheetNotValidException {
		Tidy parser = new Tidy();
		parser.setCharEncoding(org.w3c.tidy.Configuration.UTF8);
		doc = parser.parseDOM(
				new FileInputStream("data/simple/data.html"), null);
		
		CssParser cssparser = new CssParser(
				new FileInputStream("data/simple/data.css"));
		
		sheet = cssparser.parse();
		
		analyzer = new Analyzer(sheet);
		
		NodeList list = doc.getElementsByTagName("body");
		assertEquals("There is one <body> element", 1, list.getLength());
		
		walker = new TidyTreeWalker(list.item(0), NodeFilter.SHOW_ELEMENT);
		
	}
	
	
	@Test
	public void analyzeSimple() {
		
		
		Map<Element, List<Declaration>> decl = 
			analyzer.assingDeclarationsToDOM(doc, "all", true);
		
		Node current = walker.getCurrentNode();
		
		assertEquals("<body> contains two assigned declaration", 2, decl.get(current).size());
				
		walker.setCurrentNode(current);
	}	
	
	@Test 
	public void evaluateSimple() {
		
		Map<Element, NodeData> decl =
			analyzer.evaluateDOM(doc, "all", true);
		
		Node current = walker.getCurrentNode();
		
		NodeData data = decl.get(current);
		
		assertEquals("<body> nodedata contains color", NodeData.Color.color, data.getProperty(NodeData.Color.class, "color"));
		
		walker.setCurrentNode(current);
	}
	
}
