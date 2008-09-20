package test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.RuleFactory;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermNumeric;

public class SelectorTest extends TestCase {
	private static Logger log = LoggerFactory.getLogger(SelectorTest.class);

	private static final TermFactory tf = CSSFactory.getTermFactory();
	private static final RuleFactory rf = CSSFactory.getRuleFactory();

	public static final String TEST_MULTIPLE = "H1, DIV { display: block;}";

	public static final String TEST_DESCENDANT = "H1 P { display: inline;}";

	public static final String TEST_ADJACENT = "DIV+P { color: blue;}";

	public static final String TEST_CHILD = "DIV >P, SPAN, A, FORM+DIV { color: white;}";

	public static final String TEST_CLASS = ".fit { width: 80%;}";

	public static final String TEST_ID = "#krysa { font-size: 100px;}";

	public static final String TEST_ATTRIB = "A[href='fit.vutbr.cz'][id|=fit] { text-align: left;}";

	public static final String TEST_PSEUDO = ":hover { text-decoration: underline;}";

	public static final String TEST_PSEUDO_FUNC = ":lang(fr) > Q { quotes: '« ' ' »' }";

	public static final String TEST_PSPECIAL = "P.special:before {content: \"Special! \"}\n"
			+ "P.special:first-letter {color: #ffd800}";

	public static final String TEST_ASTERISK = "*.home { font-family: Verdana, monospace} ";

	public static final String TEST_IDATTRIB = "#krysa[id='krysa'] { text-align: right}";

	public static final String TEST_ATTRIB_PRESENCE = "*[href] { text-decoration: underline}";

	@BeforeClass
	public static void init() {
		log.info("\n\n\n == SelectorTest test at {} == \n\n\n", new Date());
	}

	@Test
	public void testMultiple() throws CSSException, IOException {
		StyleSheet ss = CSSFactory.parse(TEST_MULTIPLE);

		assertEquals("One rule is set", 1, ss.size());

		RuleSet rule = (RuleSet) ss.get(0);

		assertEquals("Rule contains two selectors H1, DIV  ", SelectorsUtil
				.createSelectors("H1", "DIV"), rule.getSelectors());

		assertEquals("Rule contains one declaration {display:block;}",
				DeclarationsUtil.appendDeclaration(null, "display", tf
						.createIdent("block")), rule.asList());
	}

	@Test
	public void testDescendant() throws CSSException, IOException {

		StyleSheet ss = CSSFactory.parse(TEST_DESCENDANT);

		assertEquals("One rule is set", 1, ss.size());

		RuleSet rule = (RuleSet) ss.get(0);

		List<CombinedSelector> cslist = SelectorsUtil.appendCS(null);
		SelectorsUtil.appendSimpleSelector(cslist, "H1", null);
		SelectorsUtil.appendDescendant(cslist, "P");

		assertEquals("Rule contains one combined selectors H1 P  ", cslist,
				rule.getSelectors());

		assertEquals("Rule contains one declaration {display:inline;}",
				DeclarationsUtil.appendDeclaration(null, "display", tf
						.createIdent("inline")), rule.asList());
	}

	@Test
	public void testAdjacent() throws CSSException, IOException {

		StyleSheet ss = CSSFactory.parse(TEST_ADJACENT);
		assertEquals("One rule is set", 1, ss.size());

		RuleSet rule = (RuleSet) ss.get(0);

		List<CombinedSelector> cslist = SelectorsUtil.appendCS(null);
		SelectorsUtil.appendSimpleSelector(cslist, "DIV", null);
		SelectorsUtil.appendAdjacent(cslist, "P");

		assertEquals("Rule contains one combined selectors DIV+P ", cslist,
				rule.getSelectors());

		assertEquals("Rule contains one declaration {color:blue;}",
				DeclarationsUtil.appendDeclaration(null, "color", tf
						.createColor(0, 0, 255)), rule.asList());

	}

	@Test
	public void testChild() throws CSSException, IOException {

		StyleSheet ss = CSSFactory.parse(TEST_CHILD);
		assertEquals("One rule is set", 1, ss.size());

		RuleSet rule = (RuleSet) ss.get(0);

		List<CombinedSelector> cslist = SelectorsUtil.appendCS(null);
		SelectorsUtil.appendSimpleSelector(cslist, "DIV", null);
		SelectorsUtil.appendChild(cslist, "P");
		SelectorsUtil.appendCS(cslist);
		SelectorsUtil.appendSimpleSelector(cslist, "SPAN", null);
		SelectorsUtil.appendCS(cslist);
		SelectorsUtil.appendSimpleSelector(cslist, "A", null);
		SelectorsUtil.appendCS(cslist);
		SelectorsUtil.appendSimpleSelector(cslist, "FORM", null);
		SelectorsUtil.appendAdjacent(cslist, "DIV");

		assertEquals(
				"Rule contains four combined selectors DIV>P, SPAN, A, FORM+DIV ",
				cslist, rule.getSelectors());

		assertEquals("Rule contains one declaration {color:white;}",
				DeclarationsUtil.appendDeclaration(null, "color", tf
						.createColor(255, 255, 255)), rule.asList());
	}

	@Test
	public void testClass() throws CSSException, IOException {

		StyleSheet ss = CSSFactory.parse(TEST_CLASS);
		assertEquals("One rule is set", 1, ss.size());

		RuleSet rule = (RuleSet) ss.get(0);

		List<CombinedSelector> cslist = SelectorsUtil.appendCS(null);
		SelectorsUtil.appendSimpleSelector(cslist, null, null, rf
				.createClass("fit"));

		assertEquals("Rule contains one class selector .fit", cslist, rule
				.getSelectors());

		assertEquals("Rule contains one declaration { width: 80%;}",
				DeclarationsUtil.appendDeclaration(null, "width", tf
						.createPercent(80.0f)), rule.asList());
	}

	@Test
	public void testID() throws CSSException, IOException {

		StyleSheet ss = CSSFactory.parse(TEST_ID);
		assertEquals("One rule is set", 1, ss.size());

		RuleSet rule = (RuleSet) ss.get(0);

		List<CombinedSelector> cslist = SelectorsUtil.appendCS(null);
		SelectorsUtil.appendSimpleSelector(cslist, null, null, rf
				.createID("krysa"));

		assertEquals("Rule contains one ID selector #krysa", cslist, rule
				.getSelectors());

		assertEquals("Rule contains one declaration { font-size: 100px;}",
				DeclarationsUtil.appendDeclaration(null, "font-size", tf
						.createLength(100.0f).setUnit(TermNumeric.Unit.px)),
				rule.asList());
	}

	@Test
	public void testIDAttrib() throws CSSException, IOException {

		StyleSheet ss = CSSFactory.parse(TEST_IDATTRIB);
		assertEquals("One rule is set", 1, ss.size());

		RuleSet rule = (RuleSet) ss.get(0);

		List<CombinedSelector> cslist = SelectorsUtil.appendCS(null);
		SelectorsUtil.appendSimpleSelector(cslist, null, null, rf
				.createID("krysa"), rf.createAttribute("krysa", true,
				Selector.Operator.EQUALS, "id"));

		assertEquals("Rule contains one ID selector #krysa[id='krysa']",
				cslist, rule.getSelectors());

		assertEquals("Rule contains one declaration { text-align: right }",
				DeclarationsUtil.appendDeclaration(null, "text-align", tf
						.createIdent("right")), rule.asList());
	}

	@Test
	public void testAttribute() throws CSSException, IOException {

		StyleSheet ss = CSSFactory.parse(TEST_ATTRIB);
		assertEquals("One rule is set", 1, ss.size());

		RuleSet rule = (RuleSet) ss.get(0);

		List<CombinedSelector> cslist = SelectorsUtil.appendCS(null);
		SelectorsUtil.appendSimpleSelector(cslist, "A", null, rf
				.createAttribute("fit.vutbr.cz", true,
						Selector.Operator.EQUALS, "href"), rf.createAttribute(
				"fit", false, Selector.Operator.DASHMATCH, "id"));

		assertEquals(
				"Rule contains one ID attributed selector A[href='fit.vutbr.cz'][id|=fit]",
				cslist, rule.getSelectors());

		assertEquals("Rule contains one declaration { text-align: left; }",
				DeclarationsUtil.appendDeclaration(null, "text-align", tf
						.createIdent("left")), rule.asList());
	}

	@Test
	public void testPseudo() throws CSSException, IOException {

		StyleSheet ss = CSSFactory.parse(TEST_PSEUDO);
		assertEquals("One rule is set", 1, ss.size());

		RuleSet rule = (RuleSet) ss.get(0);

		List<CombinedSelector> cslist = SelectorsUtil.appendCS(null);
		SelectorsUtil.appendSimpleSelector(cslist, null, null, rf
				.createPseudoPage("hover", null));

		assertEquals("Rule contains one pseudoselector :hover", cslist, rule
				.getSelectors());

		assertEquals(
				"Rule contains one declaration { text-decoration: underline; }",
				DeclarationsUtil.appendDeclaration(null, "text-decoration", tf
						.createIdent("underline")), rule.asList());
	}

	@Test
	public void testPseudoFunc() throws CSSException, IOException {

		StyleSheet ss = CSSFactory.parse(TEST_PSEUDO_FUNC);
		assertEquals("One rule is set", 1, ss.size());

		RuleSet rule = (RuleSet) ss.get(0);

		List<CombinedSelector> cslist = SelectorsUtil.appendCS(null);
		SelectorsUtil.appendSimpleSelector(cslist, null, null, rf
				.createPseudoPage("fr", "lang"));
		SelectorsUtil.appendChild(cslist, "Q");

		assertEquals("Rule contains one combined pseudoselector :lang(fr)>Q",
				cslist, rule.getSelectors());

		List<Term<?>> terms = DeclarationsUtil.appendTerm(null, null, tf
				.createString("« "));
		DeclarationsUtil.appendSpaceTerm(terms, tf.createString(" »"));

		assertEquals("Rule contains one declaration { quotes: '« ' ' »' }",
				DeclarationsUtil.appendDeclaration(null, "quotes", terms), rule
						.asList());
	}

	@Test
	public void testPSpecial() throws CSSException, IOException {

		StyleSheet ss = CSSFactory.parse(TEST_PSPECIAL);
		assertEquals("Two rules are set", 2, ss.size());

		// test first rule
		List<CombinedSelector> cslist = SelectorsUtil.appendCS(null);
		SelectorsUtil.appendSimpleSelector(cslist, "P", null, rf
				.createClass("special"), rf.createPseudoPage("before", null));

		assertEquals("Rule 1 contains one combined selector P.special:before",
				cslist, ((RuleSet) ss.get(0)).getSelectors());

		assertEquals(
				"Rule 2 contains one declaration { content: \"Special! \"}",
				DeclarationsUtil.appendDeclaration(null, "content", tf
						.createString("Special! ")), ((RuleSet) ss.get(0))
						.asList());

		// test second rule
		cslist = SelectorsUtil.appendCS(null);
		SelectorsUtil.appendSimpleSelector(cslist, "P", null, rf
				.createClass("special"), rf.createPseudoPage("first-letter",
				null));

		assertEquals(
				"Rule 2 contains one combined selector P.special:first-letter",
				cslist, ((RuleSet) ss.get(1)).getSelectors());

		assertEquals("Rule 2 contains one declaration { color: #ffd800}",
				DeclarationsUtil.appendDeclaration(null, "color", tf
						.createColor(255, 216, 0)), ((RuleSet) ss.get(1))
						.asList());
	}

	@Test
	public void testAsterisk() throws CSSException, IOException {

		StyleSheet ss = CSSFactory.parse(TEST_ASTERISK);
		assertEquals("One rule is set", 1, ss.size());

		List<CombinedSelector> cslist = SelectorsUtil.appendCS(null);
		SelectorsUtil.appendSimpleSelector(cslist, "*", null, rf
				.createClass("home"));

		assertEquals("Rule 1 contains one combined selector *.home", cslist,
				((RuleSet) ss.get(0)).getSelectors());

		List<Term<?>> terms = DeclarationsUtil.appendTerm(null, null, tf
				.createIdent("Verdana"));
		DeclarationsUtil.appendCommaTerm(terms, tf.createIdent("monospace"));

		assertEquals(
				"Rule contains one declaration { font-family: Verdana, monospace }",
				DeclarationsUtil.appendDeclaration(null, "font-family", terms),
				((RuleSet) ss.get(0)).asList());
	}

	@Test
	public void testElementName() throws CSSException, IOException {

		StyleSheet ss = CSSFactory.parse(TEST_MULTIPLE);
		assertEquals("One rule is set", 1, ss.size());

		RuleSet rule = (RuleSet) ss.get(0);

		List<CombinedSelector> cslist = rule.getSelectors();

		assertEquals("Rule rule contains two selectors", 2, cslist.size());

		// H1
		CombinedSelector s = cslist.get(0);

		assertEquals("CombinedSelector 1 contains one simple selector", 1, s
				.size());

		assertEquals("CombinedSelector contains element name", "H1", s.get(0)
				.getElementName());

		// DIV
		s = cslist.get(1);

		assertEquals("CombinedSelector 2 contains one simple selector", 1, s
				.size());

		assertEquals("CombinedSelector contains element name", "DIV", s.get(0)
				.getElementName());
	}

	@Test
	public void testAttributePresence() throws CSSException, IOException {

		StyleSheet ss = CSSFactory.parse(TEST_ATTRIB_PRESENCE);
		assertEquals("One rule is set", 1, ss.size());

		List<CombinedSelector> cslist = SelectorsUtil.appendCS(null);
		SelectorsUtil.appendSimpleSelector(cslist, "*", null, rf
				.createAttribute(null, false, Selector.Operator.NO_OPERATOR,
						"href"));

		assertEquals("Rule 1 contains one combined selector *[href]", cslist,
				((RuleSet) ss.get(0)).getSelectors());

		List<Term<?>> terms = DeclarationsUtil.appendTerm(null, null, tf
				.createIdent("Verdana"));
		DeclarationsUtil.appendCommaTerm(terms, tf.createIdent("monospace"));

		assertEquals(
				"Rule contains one declaration { text-decoration: underline }",
				DeclarationsUtil.appendDeclaration(null, "text-decoration", tf
						.createIdent("underline")), ((RuleSet) ss.get(0))
						.asList());

	}

}
