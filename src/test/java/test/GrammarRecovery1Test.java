package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.CSSProperty.FontFamily;
import cz.vutbr.web.domassign.Analyzer;
import cz.vutbr.web.domassign.StyleMap;

public class GrammarRecovery1Test {
	private static Logger log = LoggerFactory.getLogger(GrammarRecovery1Test.class);

	public static final TermFactory tf = CSSFactory.getTermFactory();

	public static final String TEST_CHARSET_WITHOUT_SEMICOLON1 = "@charset \"UTF-8\"";

	public static final String TEST_CHARSET_WITHOUT_SEMICOLON2 = "@charset \"UTF-8\"\n"
			+ "BODY { color: blue;}";

	public static final String TEST_CHARSET_WITHOUT_SEMICOLON3 = "@charset \"UTF-8\"\n"
			+ "BODY { color: blue;}\n" + "BODY { color: red; }";

	public static final String TEST_INVALID_ATKEYWORD = "@three-dee {\n"
			+ "	  @background-lighting {\n" + "    azimuth: 30deg;\n"
			+ "    elevation: 190deg;\n" + "	  }\n" + "  h2 { color: green }\n"
			+ "	}";

	public static final String TEST_NOT_CLOSED_STRING = "@charset \"UTF-8\n"
			+ "BODY { color: blue;}\n" + "p {color: white; quotes: '^' '^\n"
			+ "color: red;" + "color: green}";

	public static final String TEST_INVALID_ATTRIBUTE_OPERATOR = ".st a[href%=\"/slovnik/\"]:after {\n"
			+ "content: url('/images/site2/slovnik.png');\n"
			+ "margin: 0 0.1em 0 0.2em;\n";

	public static final String TEST_DECL1 = "p { color:red;   color; color:green }";

	public static final String TEST_DECL2 = "p { color:green; color: }";

	public static final String TEST_DECL3 = "p { color:red;   color:; color:green }";

	public static final String TEST_DECL4 = "p { color:green; color{;color:maroon} }";

	public static final String TEST_DECL5 = "p { color:red;   color{;color:maroon}; color:green }";

	public static final String TEST_UNEXP_EOF = "@media screen {\n"
			+ "p:before { content: 'Hello";

	public static final String TEST_INVALID_SELECTOR = " h1, h2 & h3 {color: green;}\n "
			+ " h1 {font-family: Times New Roman}";

	//invalid closing parenthesis between the rules
	public static final String TEST_INVALID_PAREN =	" h1 { color: red; } } h3 { color: blue; }";
	
	//invalid semicolon between the rules
	public static final String TEST_INVALID_SEMICOLON =	" h1 { color: red; } ; h3 { color: blue; }";
	
	//declaration with no value
	public static final String TEST_NO_VALUE = "#menu { background-color: #afa; null; color: red; }";
	
	
	@BeforeClass
	public static void init() {
		log.info("\n\n\n == GrammarRecovery1 test at {} == \n\n\n", new Date());
	}

	@Test
	public void charsetCharsetWithoutSemicolon() throws IOException, CSSException {

		StyleSheet ss = CSSFactory.parseString(TEST_CHARSET_WITHOUT_SEMICOLON1, null);
		assertEquals("No rules are defined", 0, ss.size());
	}

	@Ignore
	//@Test //TODO failing but it's a marginal problem
	public void charsetWithoutSemicolonAndDefinitinAfter() throws CSSException, IOException {

		StyleSheet ss = CSSFactory.parse(getClass().getResource("/invalid/recovery2.css"), null);
		assertEquals("No rules are set", 0, ss.size());

	}

	@Test
	public void charsetWithoutSemicolonAndDoubleDAfter() throws IOException, CSSException {

		StyleSheet ss = CSSFactory.parseString(TEST_CHARSET_WITHOUT_SEMICOLON3, null);

		RuleSet rule = (RuleSet) ss.get(0);

		assertEquals("Rule contains one selector BODY ", SelectorsUtil
				.createSelectors("BODY"), rule.getSelectors());

		assertEquals("Rule contains one declaration { color: red;}",
				DeclarationsUtil.appendDeclaration(null, "color", tf
						.createColor(255, 0, 0)), rule.asList());

	}

	@Test
	public void unclosedString() throws IOException, CSSException {
		StyleSheet ss = CSSFactory.parseString(TEST_NOT_CLOSED_STRING, null);

		assertEquals("Contains one ruleset", 1, ss.size());

		RuleSet rule = (RuleSet) ss.get(0);

		assertEquals("Rule contains last declaration { color: green;}",
				DeclarationsUtil.createDeclaration("color", tf.createColor(0,
						0x80, 0)), rule.get(rule.size() - 1));

	}

	@Test
	public void invalidAtKeyword() throws IOException, CSSException {
		StyleSheet ss = CSSFactory.parseString(TEST_INVALID_ATKEYWORD, null);
		assertTrue("Ruleset is empty", ss.isEmpty());

	}

	@Test
	public void noTerms() throws IOException, CSSException {

		StyleSheet ss = CSSFactory.parseString(TEST_DECL5, null);

		assertEquals("Contains one ruleset", 1, ss.size());

		RuleSet rule = (RuleSet) ss.get(0);

		assertEquals("Rule contains last declaration { color: green;}",
				DeclarationsUtil.createDeclaration("color", tf.createColor(0,
						0x80, 0)), rule.get(rule.size() - 1));
	}

	@Test
	public void unexpectedEOF() throws IOException, CSSException {
		StyleSheet ss = CSSFactory.parseString(TEST_UNEXP_EOF, null);

		assertEquals("Contains one @media", 1, ss.size());

		RuleMedia rm = (RuleMedia) ss.get(0);

		assertEquals("Media is set for screen", "screen", rm.getMediaQueries().get(0).getType());
	}

	@Test
	public void invalidAttributeOperator() throws IOException, CSSException {
		StyleSheet ss = CSSFactory.parseString(TEST_INVALID_ATTRIBUTE_OPERATOR, null);

		Assert.assertEquals("Stylesheet is empty", 0, ss.size());

	}

	@Test
	public void invalidSelector() throws IOException, CSSException, SAXException {
		StyleSheet sheet = CSSFactory.parseString(TEST_INVALID_SELECTOR, null);

        DOMSource ds = new DOMSource(getClass().getResourceAsStream("/simple/h1.html"));
        Document doc = ds.parse();

		Analyzer analyzer = new Analyzer(sheet);
		StyleMap decl = analyzer.evaluateDOM(doc, "all", true);

		ElementMap elements = new ElementMap(doc);

		NodeData nd = decl.get(elements.getLastElementByName("h1"));

		Assert.assertNull("There is no color", nd.getProperty("color"));

		Assert.assertEquals("There is font-family", FontFamily.list_values, nd
				.getProperty("font-family"));
		Assert.assertEquals("Font is 'Times New Roman'", tf
				.createString("Times New Roman"), nd.getValue(TermList.class,
				"font-family").get(0));

	}

	@Test
	public void invalidParen() throws IOException, CSSException {
		StyleSheet ss = CSSFactory.parseString(TEST_INVALID_PAREN, null);

		Assert.assertEquals("Stylesheet contains two rules", 2, ss.size());

	}
	
	@Test
	public void invalidSemicolon() throws IOException, CSSException {
		StyleSheet ss = CSSFactory.parseString(TEST_INVALID_SEMICOLON, null);

		Assert.assertEquals("Stylesheet contains two rules", 2, ss.size());

	}
	
    @Test
    public void declarationNoValue() throws IOException, CSSException {
        StyleSheet ss = CSSFactory.parseString(TEST_NO_VALUE, null);

        Assert.assertEquals("Stylesheet contains one rule", 1, ss.size());
        Assert.assertEquals("There are two declarations in the rule", 2, ss.get(0).size());

    }
}
