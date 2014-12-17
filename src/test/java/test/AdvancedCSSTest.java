package test;

import java.io.IOException;
import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.CSSProperty.FontFamily;
import cz.vutbr.web.css.Term.Operator;
import cz.vutbr.web.domassign.Analyzer;
import cz.vutbr.web.domassign.StyleMap;

public class AdvancedCSSTest {

	private static Logger log = LoggerFactory.getLogger(AdvancedCSSTest.class);

	private static TermFactory tf = CSSFactory.getTermFactory();

	private static Document doc;
	private static StyleSheet sheet;
	private static Analyzer analyzer;
	private static ElementMap elements;
	private static StyleMap decl;

	@BeforeClass
	public static void init() throws IOException, CSSException, SAXException {

		log.info("\n\n\n == AdvancedTest test at {} == \n\n\n", new Date());

        DOMSource ds = new DOMSource(AdvancedCSSTest.class.getResourceAsStream("/advanced/style.html"));
        doc = ds.parse();

		sheet = CSSFactory.parse(AdvancedCSSTest.class.getResource("/advanced/style.css"), null);

		analyzer = new Analyzer(sheet);
		decl = analyzer.evaluateDOM(doc, "all", true);

		elements = new ElementMap(doc);
	}

	@Test
	public void testBP() {

		Element bp = elements.getElementById("bp");

		Assert.assertNotNull("Element bp exists", bp);

		NodeData data = decl.get(bp);

		Assert.assertNotNull("Data for #bp exist", data);

		log.debug("{}", data);

		Assert.assertEquals("Background position is list of two", 2, data
				.getValue(TermList.class, "background-position").size());

		Assert.assertEquals(tf.createPercent(100.0f), data.getValue(
				TermList.class, "background-position").get(0));
		Assert.assertEquals(tf.createPercent(50.0f), data.getValue(
				TermList.class, "background-position").get(1));
		Assert.assertEquals(tf.createColor(255, 255, 255), data.getValue(
				TermColor.class, "color"));

	}

	@Test
	public void testFF() {

		NodeData data = decl.get(elements.getElementById("ff"));

		log.debug("{}", data);

		Assert.assertEquals("Font family contains two fonts ", 2, data
				.getValue(TermList.class, "font-family").size());

		Assert.assertEquals("Which is serif", tf.createTerm(FontFamily.SERIF),
				data.getValue(TermList.class, "font-family").get(0));

		Assert.assertEquals("Which is 'Times New Roman'", tf.createString(
				"Times New Roman").setOperator(Operator.COMMA), data.getValue(
				TermList.class, "font-family").get(1));

	}

	@Test
	public void testBorder() {

		NodeData data = decl.get(elements.getElementById("border"));

		Assert.assertEquals("", tf.createColor(255,255,255),
				data.getValue(TermColor.class, "border-bottom-color"));
	}

}
