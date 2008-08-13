package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermNumeric;
import cz.vutbr.web.css.NodeData.BorderStyle;
import cz.vutbr.web.css.NodeData.FontFamily;
import cz.vutbr.web.css.NodeData.Margin;
import cz.vutbr.web.csskit.parser.CSSParser;
import cz.vutbr.web.domassign.Analyzer;
import cz.vutbr.web.domassign.TidyTreeWalker;

public class AnalyzerTest {

	private static Document doc;
	private static StyleSheet sheet;
	private static Analyzer analyzer;
	private static TreeWalker walker;
	private static ElementMap elements;
	
	@BeforeClass
	public static void init() throws FileNotFoundException, StyleSheetNotValidException {
		Tidy parser = new Tidy();
		parser.setCharEncoding(org.w3c.tidy.Configuration.UTF8);

		doc = parser.parseDOM(
				new FileInputStream("data/simple/data.html"), null);
		
		CSSParser cssparser = new CSSParser(
				new FileInputStream("data/simple/data.css"));
		
		sheet = cssparser.parse();
		
		analyzer = new Analyzer(sheet);
		
		NodeList list = doc.getElementsByTagName("body");
		assertEquals("There is one <body> element", 1, list.getLength());
		
		walker = new TidyTreeWalker(list.item(0), NodeFilter.SHOW_ELEMENT);
		elements = new ElementMap(doc);
	}
	
	
	@Test
	public void analyzeSimple() {
		
		
		Map<Element, List<Declaration>> decl = 
			analyzer.assingDeclarationsToDOM(doc, "all", true);
		
		Node current = walker.getCurrentNode();
		
		assertEquals("<body> contains three assigned declaration", 3, decl.get(current).size());
				
		walker.setCurrentNode(current);
	}	
	
	@Test
	public void evaluateSimple() {
		
		Map<Element, NodeData> decl =
			analyzer.evaluateDOM(doc, "all", true);
		
		Node current = walker.getCurrentNode();
		
		NodeData data = decl.get(current);
		
		assertEquals("<body> nodedata contains color", NodeData.Color.color, data.getProperty(NodeData.Color.class, "color"));
		assertEquals("color declaration contains red color", 
					new java.awt.Color(255,0,0), 
					data.getValue(TermColor.class, "color").getValue());
		
		assertEquals("<body> nodedata contains font-weight: 200", 
				NodeData.FontWeight.numeric_200, data.getProperty(NodeData.FontWeight.class, "font-weight"));
		
		walker.setCurrentNode(current);
	}
	
	@Test 
	public void testRepeaterOnMargin() {
		Map<Element, NodeData> decl =
			analyzer.evaluateDOM(doc, "all", false);
		
		Element marginator = elements.getElementById("marginator"); 
		
		assertNotNull("Element marginator exists", marginator);
		
		NodeData data = decl.get(marginator);
		
		assertEquals("<div id=\"marginator\"> contains margin with for same values",
				Margin.lenght, data.getProperty(Margin.class, "margin-top"));
		assertEquals("<div id=\"marginator\"> contains margin with for same values",
				Margin.lenght, data.getProperty(Margin.class, "margin-bottom"));
		assertEquals("Margin of 100px", new Float(100.0f), 
				data.getValue(TermLength.class, "margin-top").getValue());
		assertEquals("Margin of 100px", TermNumeric.Unit.px, 
				data.getValue(TermLength.class, "margin-top").getUnit());
		assertEquals("for all for both values", 
				data.getValue(TermLength.class, "margin-bottom"),
				data.getValue(TermLength.class, "margin-left"));
		
	}
	
	@Test
	public void testVariatorOnBorderTop() {
	
		Map<Element, NodeData> decl =
			analyzer.evaluateDOM(doc, "all", false);
		
		Element marginator = elements.getElementById("marginator");
		assertNotNull("Element marginator exists", marginator);
		
		NodeData data = decl.get(marginator);
		
		assertEquals("border-top-style: dotted",
				BorderStyle.DOTTED, data.getProperty(BorderStyle.class, "border-top-style"));
	}
	
	@Test
	public void testFontFamily() {
		
		Map<Element, NodeData> decl =
			analyzer.evaluateDOM(doc, "all", false);
		
		Element fontoid = elements.getElementById("fontoid");
		assertNotNull("Element fontoid exist", fontoid);
		
		NodeData data = decl.get(fontoid);
		
		assertEquals("font-family: monospace",
				FontFamily.MONOSPACE, data.getProperty(FontFamily.class, "font-family"));
	}
	
}
