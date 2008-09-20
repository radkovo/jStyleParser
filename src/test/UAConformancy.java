package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.tidy.Tidy;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.domassign.Analyzer;

public class UAConformancy {
	private static Logger log = LoggerFactory.getLogger(UAConformancy.class);

	private static TermFactory tf = CSSFactory.getTermFactory();
	private static Map<Element, NodeData> decl;

	private static ElementMap em;

	@BeforeClass
	public static void init() throws FileNotFoundException {
		log.info("\n\n\n == UAConformancy test at {} == \n\n\n", new Date());

		Tidy parser = new Tidy();
		parser.setCharEncoding(org.w3c.tidy.Configuration.UTF8);

		Document doc = parser.parseDOM(new FileInputStream(
				"data/invalid/style.html"), null);

		em = new ElementMap(doc);

		StyleSheet sheet = CSSFactory.parse("data/invalid/style.css", null);

		Analyzer analyzer = new Analyzer(sheet);
		decl = analyzer.evaluateDOM(doc, "screen", true);

	}

	@Test
	public void testNotEmptyness() {

		Assert.assertNotNull("Declarations parsed", decl);
		Assert.assertNotSame("There are some declarations", Collections
				.emptyMap(), decl);

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
