package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;
import org.w3c.tidy.Tidy;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StyleSheetNotValidException;
import cz.vutbr.web.css.SupportedCSS;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermNumeric;
import cz.vutbr.web.css.CSSProperty.BorderStyle;
import cz.vutbr.web.css.CSSProperty.FontFamily;
import cz.vutbr.web.css.CSSProperty.Margin;
import cz.vutbr.web.csskit.parser.CSSParser;
import cz.vutbr.web.domassign.Analyzer;
import cz.vutbr.web.domassign.TidyTreeWalker;
import cz.vutbr.web.domassign.TidyTreeWalker.Traversal;

public class AnalyzerTest {

	private static Logger log = Logger.getLogger(AnalyzerTest.class);

	private static final SupportedCSS css = CSSFactory.getSupportedCSS();

	private static Document doc;
	private static StyleSheet sheet;
	private static Analyzer analyzer;
	private static TreeWalker walker;
	private static ElementMap elements;

	@BeforeClass
	public static void init() throws FileNotFoundException,
			StyleSheetNotValidException {
		Tidy parser = new Tidy();
		parser.setCharEncoding(org.w3c.tidy.Configuration.UTF8);

		doc = parser.parseDOM(new FileInputStream("data/simple/data.html"),
				null);

		CSSParser cssparser = new CSSParser(new FileInputStream(
				"data/simple/data.css"));

		sheet = cssparser.parse();

		analyzer = new Analyzer(sheet);

		NodeList list = doc.getElementsByTagName("body");
		assertEquals("There is one <body> element", 1, list.getLength());

		walker = new TidyTreeWalker(list.item(0), NodeFilter.SHOW_ELEMENT);
		elements = new ElementMap(doc);
	}

	@Test
	public void evaluateSimple() {

		Map<Element, NodeData> decl = analyzer.evaluateDOM(doc, "all", false);

		Node current = walker.getCurrentNode();

		NodeData data = decl.get(current);

		assertEquals("<body> nodedata contains color", CSSProperty.Color.color,
				data.getProperty(CSSProperty.Color.class, "color"));
		assertEquals("color declaration contains red color",
				new java.awt.Color(255, 0, 0), data.getValue(TermColor.class,
						"color").getValue());

		assertEquals("<body> nodedata contains font-weight: 200",
				CSSProperty.FontWeight.numeric_200, data.getProperty(
						CSSProperty.FontWeight.class, "font-weight"));

		walker.setCurrentNode(current);
	}

	@Test
	public void testRepeaterOnMargin() {
		Map<Element, NodeData> decl = analyzer.evaluateDOM(doc, "all", false);

		Element marginator = elements.getElementById("marginator");

		assertNotNull("Element marginator exists", marginator);

		NodeData data = decl.get(marginator);

		assertEquals(
				"<div id=\"marginator\"> contains margin with for same values",
				Margin.length, data.getProperty(Margin.class, "margin-top"));
		assertEquals(
				"<div id=\"marginator\"> contains margin with for same values",
				Margin.length, data.getProperty(Margin.class, "margin-bottom"));
		assertEquals("Margin of 100px", new Float(100.0f), data.getValue(
				TermLength.class, "margin-top").getValue());
		assertEquals("Margin of 100px", TermNumeric.Unit.px, data.getValue(
				TermLength.class, "margin-top").getUnit());
		assertEquals("for all for both values", data.getValue(TermLength.class,
				"margin-bottom"), data
				.getValue(TermLength.class, "margin-left"));

	}

	@Test
	public void testVariatorOnBorderTop() {

		Map<Element, NodeData> decl = analyzer.evaluateDOM(doc, "all", false);

		Element marginator = elements.getElementById("marginator");
		assertNotNull("Element marginator exists", marginator);

		NodeData data = decl.get(marginator);

		assertEquals("border-top-style: dotted", BorderStyle.DOTTED, data
				.getProperty(BorderStyle.class, "border-top-style"));
	}

	@Test
	public void testFontFamily() {

		Map<Element, NodeData> decl = analyzer.evaluateDOM(doc, "all", false);

		Element fontoid = elements.getElementById("fontoid");
		assertNotNull("Element fontoid exist", fontoid);

		NodeData data = decl.get(fontoid);

		assertEquals("font-family: monospace", FontFamily.MONOSPACE, data
				.getProperty(FontFamily.class, "font-family"));
	}

	@Test
	public void inheritanceDiffers() {

		Map<Element, NodeData> declInh = analyzer.evaluateDOM(doc, "all", true);

		Map<Element, NodeData> decl = analyzer.evaluateDOM(doc, "all", false);

		Pair<Map<Element, NodeData>, Map<Element, NodeData>> pair = new Pair<Map<Element, NodeData>, Map<Element, NodeData>>(
				declInh, decl);

		Traversal<Boolean> traversal = new Traversal<Boolean>(doc,
				(Object) pair, NodeFilter.SHOW_ELEMENT) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processNode(Boolean result, Node current,
					Object source) {

				Pair<Map<Element, NodeData>, Map<Element, NodeData>> pair = (Pair<Map<Element, NodeData>, Map<Element, NodeData>>) source;

				Element e = (Element) current;

				NodeData ndInh = pair.getFirst().get(e);
				NodeData nd = pair.getSecond().get(e);

				String id = e.getAttribute("id");

				if (id != null)
					Assert.assertNotSame("Inherited differs for #" + id, ndInh,
							nd);
			}

		};

		traversal.levelTraversal(null);

	}

	@Test
	public void inheritanceNoInheritedValues() {

		Map<Element, NodeData> declInh = analyzer.evaluateDOM(doc, "all", true);

		Map<Element, NodeData> decl = analyzer.evaluateDOM(doc, "all", false);

		Pair<Map<Element, NodeData>, Map<Element, NodeData>> pair = new Pair<Map<Element, NodeData>, Map<Element, NodeData>>(
				declInh, decl);

		Traversal<Boolean> traversal = new Traversal<Boolean>(doc,
				(Object) pair, NodeFilter.SHOW_ELEMENT) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processNode(Boolean result, Node current,
					Object source) {

				Pair<Map<Element, NodeData>, Map<Element, NodeData>> pair = (Pair<Map<Element, NodeData>, Map<Element, NodeData>>) source;

				Element e = (Element) current;

				NodeData ndInh = pair.getFirst().get(e);
				NodeData nd = pair.getSecond().get(e);

				for (String property : css.getDefinedPropertyNames()) {
					CSSProperty inh = ndInh.getProperty(CSSProperty.class,
							property, false);
					CSSProperty noinh = nd.getProperty(CSSProperty.class,
							property, true);

					if ((inh != null && noinh == null)
							|| (inh == null && noinh != null))
						Assert
								.fail("No consitency between inheride and not inherited properties");

					// not existing property
					if (inh == null && noinh == null)
						continue;

					if(log.isDebugEnabled()) {
						log.debug(log(e, property, inh.toString(), noinh.toString()));
					}

					Term<?> tinh = ndInh.getValue(Term.class, property, false);
					Term<?> tnoinh = nd.getValue(Term.class, property, true);

					if ((tinh != null && tnoinh == null)
							|| (tinh == null && tnoinh != null))
						Assert
								.fail("No consitency between inheride and not inherited values");

					// not existing value
					if (tinh == null && tnoinh == null)
						continue;

					if(log.isDebugEnabled()) {
						log.debug(log(e, property, tinh.toString(), tnoinh.toString()));
					}

				}

			}
			
			private String log(Element element, String property, String inhVal, String noInhVal) {
				StringBuilder sb = new StringBuilder();
				
				sb.append(element.getNodeName())
					.append(" ").append(element.getAttribute("id")).append(" ")
					.append("(").append(property).append(")")
					.append(" INH: ").append(inhVal)
					.append(" NOINH: ").append(noInhVal);
					
				return sb.toString();
			}
			

		};

		traversal.levelTraversal(null);

	}

	static class Pair<T, V> {
		private T first;
		private V second;

		public Pair(T first, V second) {
			this.first = first;
			this.second = second;
		}

		public T getFirst() {
			return first;
		}

		public V getSecond() {
			return second;
		}

	}

}
