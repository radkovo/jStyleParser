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
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.domassign.Analyzer;
import cz.vutbr.web.domassign.StyleMap;

public class UAConformancyTest {
	private static Logger log = LoggerFactory.getLogger(UAConformancyTest.class);

	private static TermFactory tf = CSSFactory.getTermFactory();
	private static StyleMap decl;

	private static ElementMap em;

	@BeforeClass
	public static void init() throws CSSException, IOException, SAXException {
		log.info("\n\n\n == UAConformancy test at {} == \n\n\n", new Date());

        DOMSource ds = new DOMSource(UAConformancyTest.class.getResourceAsStream("/invalid/style.html"));
        Document doc = ds.parse();

		em = new ElementMap(doc);

		StyleSheet sheet = CSSFactory.parse(UAConformancyTest.class.getResource("/invalid/style.css"), null);

		Analyzer analyzer = new Analyzer(sheet);
		decl = analyzer.evaluateDOM(doc, "screen", true);

	}

	@Test
	public void testNotEmptyness() {

		Assert.assertNotNull("Declarations parsed", decl);
		Assert.assertNotSame("There are some declarations", 0, decl.size());

		for (Element e : decl.keySet())
			log.debug("{} : {} ", e.getNodeName(), decl.get(e));
	}

	@Test
	public void unknownProperties() {

		NodeData nd = decl.get(em.getLastElementByName("h1"));

		Assert.assertEquals("Color is red", tf.createColor("#ff0000"), nd
				.getValue(TermColor.class, "color"));

		Assert.assertNull("There is no rotatation", nd.getProperty("rotation"));
	}

	@Test
	public void illegalValues() {
		NodeData nd = decl.get(em.getLastElementByName("img"));

		Assert.assertEquals("Float is left", CSSProperty.Float.LEFT, nd
				.getProperty("float"));
	}

	@Test
	public void malformedDeclaration() {
		NodeData nd = decl.get(em.getLastElementByName("p"));

		Assert.assertEquals("Color is green", tf.createColor("#008000"), nd
				.getValue(TermColor.class, "color"));
	}

	@Test
	public void ignoreUnknownAtRule() {
		NodeData nd = decl.get(em.getLastElementByName("h2"));

		Assert.assertEquals("Color is blue", tf.createColor(0, 0, 0xff), nd
				.getValue(TermColor.class, "color"));

		Assert.assertNull("Text-decoration is not set", nd
				.getProperty("text-decoration"));

	}

	@Test
	public void unclosedString() {
		NodeData nd = decl.get(em.getLastElementByName("div"));

		Assert.assertEquals("Color is green", tf.createColor(0, 0x80, 0), nd
				.getValue(TermColor.class, "color"));

		Assert.assertEquals("Background-color is white", tf
				.createColor("#ffffff"), nd.getValue(TermColor.class,
				"background-color"));
	}

}
