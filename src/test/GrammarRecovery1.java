package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.TermFactory;

public class GrammarRecovery1 {
	private static Logger log = Logger.getLogger(GrammarRecovery1.class);

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

	public static final String TEST_DECL1 = "p { color:red;   color; color:green }";

	public static final String TEST_DECL2 = "p { color:green; color: }";

	public static final String TEST_DECL3 = "p { color:red;   color:; color:green }";

	public static final String TEST_DECL4 = "p { color:green; color{;color:maroon} }";

	public static final String TEST_DECL5 = "p { color:red;   color{;color:maroon}; color:green }";

	@BeforeClass
	public static void init() {
		log.info("\n\n\n == GrammarRecovery1 test == " + new Date()
				+ " == \n\n\n");
	}

	@Test
	public void charsetCharsetWithoutSemicolon() {

		StyleSheet ss = CSSFactory.parse(new StringReader(
				TEST_CHARSET_WITHOUT_SEMICOLON1));
		assertEquals("Charset should not be set", null, ss.getCharset());

		assertEquals("No rules are defined", 0, ss.size());
	}

	@Test
	public void charsetWithoutSemicolonAndDefinitinAfter()
			throws FileNotFoundException {

		StyleSheet ss = CSSFactory.parse(new FileReader(
				"data/invalid/recovery2.css"));
		assertEquals("Charset should not be set", null, ss.getCharset());

		assertEquals("No rules are set", 0, ss.size());

	}

	@Test
	public void charsetWithoutSemicolonAndDoubleDAfter() {

		StyleSheet ss = CSSFactory.parse(new StringReader(
				TEST_CHARSET_WITHOUT_SEMICOLON3));
		assertEquals("Charset should not be set", null, ss.getCharset());

		RuleSet rule = (RuleSet) ss.get(0);

		assertEquals("Rule contains one selector BODY ", SelectorsUtil
				.createSelectors("BODY"), rule.getSelectors());

		assertEquals("Rule contains one declaration { color: red;}",
				DeclarationsUtil.appendDeclaration(null, "color", tf
						.createColor(255, 0, 0)), rule.asList());

	}

	@Test
	public void invalidAtKeyword() {
		StyleSheet ss = CSSFactory.parse(new StringReader(
				TEST_INVALID_ATKEYWORD));
		assertTrue("Ruleset is empty", ss.isEmpty());

	}

	@Test
	public void noTerms() {

		StyleSheet ss = CSSFactory.parse(new StringReader(TEST_DECL5));

		assertEquals("Contains one ruleset", 1, ss.size());

		RuleSet rule = (RuleSet) ss.get(0);

		assertEquals("Rule contains last declaration { color: green;}",
				DeclarationsUtil.createDeclaration("color", tf.createColor(0,
						0x80, 0)), rule.get(rule.size() - 1));
	}

}
