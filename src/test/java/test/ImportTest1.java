package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.MediaSpec;
import cz.vutbr.web.css.MediaSpecAll;
import cz.vutbr.web.css.MediaSpecNone;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermNumeric;
import cz.vutbr.web.css.CSSProperty.Margin;
import cz.vutbr.web.domassign.Analyzer;
import cz.vutbr.web.domassign.StyleMap;

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
	
	public static final String EXTERN_IMPORT = 
		"@import url('http://www.abclinuxu.cz/styles.css');\n";

	@BeforeClass
	public static void init() throws SAXException, IOException {
		log.info("\n\n\n == ImportTest1 test at {} == \n\n\n", new Date());

        DOMSource ds = new DOMSource(ImportTest1.class.getResourceAsStream("/simple/data.html"));
        doc = ds.parse();
	}

	@Test
	public void importTokenization() throws CSSException, IOException {
		CSSFactory.parseString(SIMPLE_IMPORT, null);
		CSSFactory.parseString(QUOT_IMPORT, null);
		CSSFactory.parseString(URL_IMPORT, null);
		CSSFactory.parseString(DOUBLE_IMPORT, null);
		CSSFactory.parseString(MEDIA_IMPORT, null);
	}

	@Test
	public void testSimpleImport() throws CSSException, IOException {

		StyleSheet ss = CSSFactory.parse(getClass().getResource("/simple/imp.css"), null);

		Analyzer analyzer = new Analyzer(ss);

		StyleMap decl = analyzer.evaluateDOM(doc, "all", true);
		ElementMap elements = new ElementMap(doc);

		Element marginator = elements.getElementById("marginator");

		assertNotNull("Element marginator exists", marginator);

		NodeData data = decl.get(marginator);

		assertEquals(
				"<div id=\"marginator\"> contains margin with for same values",
				Margin.length, data.getProperty("margin-top"));
		assertEquals(
				"<div id=\"marginator\"> contains margin with for same values",
				Margin.length, data.getProperty("margin-bottom"));
		assertEquals("Margin of 100px", new Float(100.0f), data.getValue(
				TermLength.class, "margin-top").getValue());
		assertEquals("Margin of 100px", TermNumeric.Unit.px, data.getValue(
				TermLength.class, "margin-top").getUnit());
		assertEquals("for all for both values", data.getValue(TermLength.class,
				"margin-bottom"), data
				.getValue(TermLength.class, "margin-left"));

	}

	@Test
	public void testMediaImport() throws CSSException, IOException {
        CSSFactory.setAutoImportMedia(new MediaSpecAll()); //reset to default
		StyleSheet ss = CSSFactory.parse(getClass().getResource("/simple/impmedia.css"), null);
		assertEquals("All rules have been imported", 8, ss.size());
	}

    @Test
    public void testMediaImportLimit1() throws CSSException, IOException {
        CSSFactory.setAutoImportMedia(new MediaSpec("projection"));
        StyleSheet ss = CSSFactory.parse(getClass().getResource("/simple/impmedia.css"), null);
        assertEquals("All rules have been imported", 8, ss.size());
    }

    @Test
    public void testMediaImportLimit2() throws CSSException, IOException {
        CSSFactory.setAutoImportMedia(new MediaSpec("screen"));
        StyleSheet ss = CSSFactory.parse(getClass().getResource("/simple/impmedia.css"), null);
        assertEquals("No rules have been imported", 0, ss.size());
    }

    @Test
    public void testMediaImportLimit3() throws CSSException, IOException {
        CSSFactory.setAutoImportMedia(new MediaSpecNone());
        StyleSheet ss = CSSFactory.parse(getClass().getResource("/simple/impmedia.css"), null);
        assertEquals("No rules have been imported", 0, ss.size());
    }

	@Test
	public void testRealAndNested() throws CSSException, IOException {
		CSSFactory.parse(getClass().getResource("/abclinuxu/styles.css"), null);
	}

	@Test
	public void testExternAndNested() throws CSSException, IOException {
		CSSFactory.parse(new URL("http://www.abclinuxu.cz/styles.css"), null);
	}
	
	@Test
	public void testURLImport() throws CSSException, IOException {
		CSSFactory.parseString(EXTERN_IMPORT, null);
	}
	
}
