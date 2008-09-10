package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.TermFactory;

public class GrammarRecovery1 {
	private static Logger log = LoggerFactory.getLogger(GrammarRecovery1.class);

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

	public static final String TEST_NOT_CLOSED_STRING = 
			"@charset \"UTF-8\n" +
		    "BODY { color: blue;}\n" +
		    "p {color: white; quotes: '^' '^\n" +
		    "color: red;" +
		    "color: green}";
	
	public static final String TEST_DECL1 = "p { color:red;   color; color:green }";

	public static final String TEST_DECL2 = "p { color:green; color: }";

	public static final String TEST_DECL3 = "p { color:red;   color:; color:green }";

	public static final String TEST_DECL4 = "p { color:green; color{;color:maroon} }";

	public static final String TEST_DECL5 = "p { color:red;   color{;color:maroon}; color:green }";

	public static final String TEST_UNEXP_EOF =
		"@media screen {\n" +
	    "p:before { content: 'Hello";
	
	
	@BeforeClass
	public static void init() {
		log.info("\n\n\n == GrammarRecovery1 test at {} == \n\n\n", new Date());
	}

	@Test
	public void charsetCharsetWithoutSemicolon() {

		StyleSheet ss = CSSFactory.parse(TEST_CHARSET_WITHOUT_SEMICOLON1);
		assertEquals("Charset should not be set", null, ss.getCharset());

		assertEquals("No rules are defined", 0, ss.size());
	}

	@Test
	public void charsetWithoutSemicolonAndDefinitinAfter() {

		StyleSheet ss = CSSFactory.parse("data/invalid/recovery2.css", null);
		assertEquals("Charset should not be set", null, ss.getCharset());

		assertEquals("No rules are set", 0, ss.size());

	}

	@Test
	public void charsetWithoutSemicolonAndDoubleDAfter() {

		StyleSheet ss = CSSFactory.parse(TEST_CHARSET_WITHOUT_SEMICOLON3);
		assertEquals("Charset should not be set", null, ss.getCharset());

		RuleSet rule = (RuleSet) ss.get(0);

		assertEquals("Rule contains one selector BODY ", SelectorsUtil
				.createSelectors("BODY"), rule.getSelectors());

		assertEquals("Rule contains one declaration { color: red;}",
				DeclarationsUtil.appendDeclaration(null, "color", tf
						.createColor(255, 0, 0)), rule.asList());

	}

	@Test
	public void unclosedString() {
		StyleSheet ss = CSSFactory.parse(TEST_NOT_CLOSED_STRING);
		
		assertEquals("Contains one ruleset", 1, ss.size());

		RuleSet rule = (RuleSet) ss.get(0);

		assertEquals("Rule contains last declaration { color: green;}",
				DeclarationsUtil.createDeclaration("color", tf.createColor(0,
						0x80, 0)), rule.get(rule.size() - 1));
		
	}
	
	@Test
	public void invalidAtKeyword() {
		StyleSheet ss = CSSFactory.parse(TEST_INVALID_ATKEYWORD);
		assertTrue("Ruleset is empty", ss.isEmpty());

	}

	@Test
	public void noTerms() {

		StyleSheet ss = CSSFactory.parse(TEST_DECL5);

		assertEquals("Contains one ruleset", 1, ss.size());

		RuleSet rule = (RuleSet) ss.get(0);

		assertEquals("Rule contains last declaration { color: green;}",
				DeclarationsUtil.createDeclaration("color", tf.createColor(0,
						0x80, 0)), rule.get(rule.size() - 1));
	}

	@Test
	public void unexpectedEOF() {
		StyleSheet ss = CSSFactory.parse(TEST_UNEXP_EOF);
		
		log.debug("Unexpected EOF stylesheet: {}", ss);
	}
	
}
