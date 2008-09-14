package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.tidy.Tidy;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StyleSheetNotValidException;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermNumeric;
import cz.vutbr.web.css.CSSProperty.Margin;
import cz.vutbr.web.domassign.Analyzer;

public class ImportTest1 {
	private static Logger log = LoggerFactory.getLogger(ImportTest1.class);
	
	private static Document doc;
	
	public static final String SIMPLE_IMPORT = "@import 'bla.css';\n";

	public static final String URL_IMPORT = "@import url('bla.css');\n";

	public static final String QUOT_IMPORT = "@import \"bla.css\";\n";

	public static final String DOUBLE_IMPORT = "@import 'file1.css';\n"
			+ "@import 'file2.css';";

	public static final String MEDIA_IMPORT = "@import 'test.css' aural;"
			+ "@import 'test-print.css' print, screen\n;";

	@BeforeClass
	public static void init() throws FileNotFoundException  {
		log.info("\n\n\n == ImportTest1 test at {} == \n\n\n", new Date());
		
		Tidy parser = new Tidy();
		parser.setCharEncoding(org.w3c.tidy.Configuration.UTF8);

		doc = parser.parseDOM(new FileInputStream("data/simple/data.html"),
				null);
	}
	
	@Test
	public void importTokenization() throws StyleSheetNotValidException {
		CSSFactory.parse(SIMPLE_IMPORT);
		CSSFactory.parse(QUOT_IMPORT);
		CSSFactory.parse(URL_IMPORT);
		CSSFactory.parse(DOUBLE_IMPORT);
		CSSFactory.parse(MEDIA_IMPORT);
	}	

	@Test
	public void testSimpleImport() throws StyleSheetNotValidException {

		StyleSheet ss = CSSFactory.parse("data/simple/imp.css", null);
		
		Analyzer analyzer = new Analyzer(ss);
		
		Map<Element, NodeData> decl = analyzer.evaluateDOM(doc, "all", true);
		ElementMap elements = new ElementMap(doc);
		
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
	public void testMediaImport() throws StyleSheetNotValidException {
		CSSFactory.parse("data/simple/impmedia.css", null);
	}
	 
	@Test
	public void testRealAndNested() throws StyleSheetNotValidException {
		CSSFactory.parse("data/abclinuxu/styles.css", null);
	}
	
}
