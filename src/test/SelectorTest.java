package test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import cz.vutbr.web.css.Rule;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.SimpleSelector;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StyleSheetNotValidException;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermNumber;
import cz.vutbr.web.csskit.SimpleSelectorImpl;
import cz.vutbr.web.csskit.TermColorImpl;
import cz.vutbr.web.csskit.TermIdentImpl;
import cz.vutbr.web.csskit.TermNumberImpl;
import cz.vutbr.web.csskit.TermPercentImpl;
import cz.vutbr.web.csskit.TermStringImpl;
import cz.vutbr.web.csskit.parser.CSSParser;

public class SelectorTest {

	public static final String TEST_MULTIPLE =
		"H1, DIV { display: block;}";
	
	public static final String TEST_DESCENDANT =
		"H1 P { display: inline;}";
	
	public static final String TEST_ADJACENT =
		"DIV+P { color: blue;}"; 
	
	public static final String TEST_CHILD = 
		"DIV>P, SPAN, A, FORM+DIV { color: white;}";	
	
	public static final String TEST_CLASS =
		".fit { width: 80%;}";
		
	public static final String TEST_ID =
		"#krysa { font-size: 100px;}";
	
	public static final String TEST_ATTRIB =
		"A[href='fit.vutbr.cz'][id|=fit] { text-align: left;}";
	
	public static final String TEST_PSEUDO = 
		":hover { text-decoration: underline;}";
	
	public static final String TEST_PSEUDO_FUNC =
		":lang(fr) > Q { quotes: '« ' ' »' }";
	
	public static final String TEST_PSPECIAL =
		"P.special:before {content: \"Special! \"}\n" +
		"P.special:first-letter {color: #ffd800}";

	public static final String TEST_ASTERISK = 
		"*.home { font-family: Verdana, monospace} ";
	
	public static final String TEST_IDATTRIB =
		"#krysa[id='krysa'] { text-align: right}";
	
	public static final String TEST_ATTRIB_PRESENCE = 
		"*[href] { text-decoration: underline}";
	
	@Test
	public void testMultiple() throws StyleSheetNotValidException {
		StyleSheet ss = (new CSSParser(TEST_MULTIPLE)).parse();
		
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		final RuleSet rule = (RuleSet) rules.get(0);				
		
		assertEquals("Rule contains two selectors H1, DIV  ", 
				SelectorsUtil.createSelectors("H1", "DIV"), 
				rule.getSelectors());
		
		assertEquals("Rule contains one declaration {display:block;}",
				DeclarationsUtil.appendDeclaration(null, "display", 
						new TermIdentImpl("block")),
				rule.getDeclarations());
	}
	
	
	@Test
	public void testDescendant() throws StyleSheetNotValidException {
		
		StyleSheet ss = (new CSSParser(TEST_DESCENDANT)).parse();
		
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		final RuleSet rule = (RuleSet) rules.get(0);				
		
		List<Selector> sels = SelectorsUtil.appendSelector(null); 
		SelectorsUtil.appendSimpleSelector(sels, "H1", null);
		SelectorsUtil.appendDescendant(sels, "P");
		
		assertEquals("Rule contains one combined selectors H1 P  ",
				sels, rule.getSelectors());
		
		assertEquals("Rule contains one declaration {display:inline;}",
				DeclarationsUtil.appendDeclaration(null, "display", 
						new TermIdentImpl("inline")),
				rule.getDeclarations());
	}
	
	@Test
	public void testAdjacent() throws StyleSheetNotValidException {
		StyleSheet ss = (new CSSParser(TEST_ADJACENT)).parse();
		
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		final RuleSet rule = (RuleSet) rules.get(0);
		
		List<Selector> sels = SelectorsUtil.appendSelector(null); 
		SelectorsUtil.appendSimpleSelector(sels, "DIV", null);
		SelectorsUtil.appendAdjacent(sels, "P");
		
		assertEquals("Rule contains one combined selectors DIV+P ",
				sels, rule.getSelectors());
		
		assertEquals("Rule contains one declaration {color:blue;}",
				DeclarationsUtil.appendDeclaration(null, "color", 
						new TermColorImpl(0, 0, 255)),
				rule.getDeclarations());
		
	}
	
	@Test
	public void testChild() throws StyleSheetNotValidException {
		StyleSheet ss = (new CSSParser(TEST_CHILD)).parse();
		
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		final RuleSet rule = (RuleSet) rules.get(0);
		
		List<Selector> sels = SelectorsUtil.appendSelector(null); 
		SelectorsUtil.appendSimpleSelector(sels, "DIV", null);
		SelectorsUtil.appendChild(sels, "P");
		SelectorsUtil.appendSelector(sels);
		SelectorsUtil.appendSimpleSelector(sels, "SPAN", null);
		SelectorsUtil.appendSelector(sels);
		SelectorsUtil.appendSimpleSelector(sels, "A", null);
		SelectorsUtil.appendSelector(sels);
		SelectorsUtil.appendSimpleSelector(sels, "FORM", null);
		SelectorsUtil.appendAdjacent(sels, "DIV");
		
		assertEquals("Rule contains four combined selectors DIV>P, SPAN, A, FORM+DIV ",
				sels, rule.getSelectors());
		
		assertEquals("Rule contains one declaration {color:white;}",
				DeclarationsUtil.appendDeclaration(null, "color", 
						new TermColorImpl(255, 255, 255)),
				rule.getDeclarations());
	}
	
	@Test
	public void testClass() throws StyleSheetNotValidException {
		StyleSheet ss = (new CSSParser(TEST_CLASS)).parse();
		
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		final RuleSet rule = (RuleSet) rules.get(0);
		
		List<Selector> sels = SelectorsUtil.appendSelector(null); 
		SelectorsUtil.appendSimpleSelector(sels, null, null,
				new SimpleSelectorImpl.ItemClassImpl("fit"));
		
		assertEquals("Rule contains one class selector .fit",
				sels, rule.getSelectors());
		
		assertEquals("Rule contains one declaration { width: 80%;}",
				DeclarationsUtil.appendDeclaration(null, "width", 
						new TermPercentImpl(80.0f)),
				rule.getDeclarations());
	}
	
	@Test
	public void testID() throws StyleSheetNotValidException {
		StyleSheet ss = (new CSSParser(TEST_ID)).parse();
		
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		final RuleSet rule = (RuleSet) rules.get(0);
		
		List<Selector> sels = SelectorsUtil.appendSelector(null); 
		SelectorsUtil.appendSimpleSelector(sels, null, null,
				new SimpleSelectorImpl.ItemIDImpl("krysa"));
		
		assertEquals("Rule contains one ID selector #krysa",
				sels, rule.getSelectors());
		
		assertEquals("Rule contains one declaration { font-size: 100px;}",
				DeclarationsUtil.appendDeclaration(null, "font-size", 
						new TermNumberImpl(100f, TermNumber.Unit.px, 1)),
				rule.getDeclarations());
	}
	
	@Test
	public void testIDAttrib() throws StyleSheetNotValidException {
		StyleSheet ss = (new CSSParser(TEST_IDATTRIB)).parse();
		
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		final RuleSet rule = (RuleSet) rules.get(0);
		
		
		List<Selector> sels = SelectorsUtil.appendSelector(null); 
		SelectorsUtil.appendSimpleSelector(sels, null, null,
				new SimpleSelectorImpl.ItemIDImpl("krysa"),
				new SimpleSelectorImpl.ItemAttributeImpl("krysa", true, 
						SimpleSelector.Operator.EQUALS, "id"));
		
		assertEquals("Rule contains one ID selector #krysa[id='krysa']",
				sels, rule.getSelectors());
		
		assertEquals("Rule contains one declaration { text-align: right }",
				DeclarationsUtil.appendDeclaration(null, "text-align",
						new TermIdentImpl("right")),
				rule.getDeclarations());
	}
	
	
	@Test
	public void testAttribute() throws StyleSheetNotValidException {
		StyleSheet ss = (new CSSParser(TEST_ATTRIB)).parse();
		
		
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		final RuleSet rule = (RuleSet) rules.get(0);
		
		List<Selector> sels = SelectorsUtil.appendSelector(null); 
		SelectorsUtil.appendSimpleSelector(sels, "A", null,
				new SimpleSelectorImpl.ItemAttributeImpl("fit.vutbr.cz", true, SimpleSelector.Operator.EQUALS, "href"),
				new SimpleSelectorImpl.ItemAttributeImpl("fit", false, SimpleSelector.Operator.DASHMATCH, "id"));
		
		assertEquals("Rule contains one ID attributed selector A[href='fit.vutbr.cz'][id|=fit]",
				sels, rule.getSelectors());
		
		assertEquals("Rule contains one declaration { text-align: left; }",
				DeclarationsUtil.appendDeclaration(null, "text-align", 
						new TermIdentImpl("left")),
				rule.getDeclarations());
	}
	
	@Test
	public void testPseudo() throws StyleSheetNotValidException {
		StyleSheet ss = (new CSSParser(TEST_PSEUDO)).parse();
		
		
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		final RuleSet rule = (RuleSet) rules.get(0);
		
		List<Selector> sels = SelectorsUtil.appendSelector(null); 
		SelectorsUtil.appendSimpleSelector(sels, null, null,
				new SimpleSelectorImpl.ItemPseudoImpl("hover", null));
		
		assertEquals("Rule contains one pseudoselector :hover",
				sels, rule.getSelectors());
		
		assertEquals("Rule contains one declaration { text-decoration: underline; }",
				DeclarationsUtil.appendDeclaration(null, "text-decoration", 
						new TermIdentImpl("underline")),
				rule.getDeclarations());
	}
	
	@Test
	public void testPseudoFunc() throws StyleSheetNotValidException {
		StyleSheet ss = (new CSSParser(TEST_PSEUDO_FUNC)).parse();
		
		
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		final RuleSet rule = (RuleSet) rules.get(0);
		
		List<Selector> sels = SelectorsUtil.appendSelector(null); 
		SelectorsUtil.appendSimpleSelector(sels, null, null,
				new SimpleSelectorImpl.ItemPseudoImpl("fr", "lang"));
		SelectorsUtil.appendChild(sels, "Q");
		
		assertEquals("Rule contains one combined pseudoselector :lang(fr)>Q",
				sels, rule.getSelectors());
		
		List<Term> terms = DeclarationsUtil.appendTerm(null, null, 
				new TermStringImpl("« "));
		DeclarationsUtil.appendSpaceTerm(terms, 
				new TermStringImpl(" »"));
		
		assertEquals("Rule contains one declaration { quotes: '« ' ' »' }",
				DeclarationsUtil.appendDeclaration(null, "quotes", terms), 
				rule.getDeclarations());
	}
	
	@Test
	public void testPSpecial() throws StyleSheetNotValidException {
		StyleSheet ss = (new CSSParser(TEST_PSPECIAL)).parse();
		
		
		List<Rule> rules = ss.getRules();
		assertEquals("Two rules are set", 2, rules.size());
		
		// test first rule
		List<Selector> sels = SelectorsUtil.appendSelector(null);
		SelectorsUtil.appendSimpleSelector(sels, "P", null, 
				new SimpleSelectorImpl.ItemClassImpl("special"),
				new SimpleSelectorImpl.ItemPseudoImpl("before", null));
				
		
		assertEquals("Rule 1 contains one combined selector P.special:before",
				sels, ((RuleSet) rules.get(0)).getSelectors());
		
		assertEquals("Rule 2 contains one declaration { content: \"Special! \"}",
				DeclarationsUtil.appendDeclaration(null, "content", 
						new TermStringImpl("Special! ")), 
				((RuleSet)rules.get(0)).getDeclarations());
		
		// test second rule
		sels = SelectorsUtil.appendSelector(null);
		SelectorsUtil.appendSimpleSelector(sels, "P", null, 
				new SimpleSelectorImpl.ItemClassImpl("special"),
				new SimpleSelectorImpl.ItemPseudoImpl("first-letter", null));
		
		assertEquals("Rule 2 contains one combined selector P.special:first-letter",
				sels, ((RuleSet) rules.get(1)).getSelectors());
		
		assertEquals("Rule 2 contains one declaration { color: #ffd800}",
				DeclarationsUtil.appendDeclaration(null, "color", 
						new TermColorImpl(255,216,0)), 
				((RuleSet)rules.get(1)).getDeclarations());
	}
	
	@Test
	public void testAsterisk() throws StyleSheetNotValidException {
		StyleSheet ss = (new CSSParser(TEST_ASTERISK)).parse();
		
		
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		List<Selector> sels = SelectorsUtil.appendSelector(null);
		SelectorsUtil.appendSimpleSelector(sels, "*", null, 
				new SimpleSelectorImpl.ItemClassImpl("home"));				
		
		assertEquals("Rule 1 contains one combined selector *.home",
				sels, ((RuleSet) rules.get(0)).getSelectors());

		List<Term> terms = DeclarationsUtil.appendTerm(null, null, 
				new TermIdentImpl("Verdana"));
		DeclarationsUtil.appendCommaTerm(terms, 
				new TermIdentImpl("monospace"));
		
		
		assertEquals("Rule contains one declaration { font-family: Verdana, monospace }",
				DeclarationsUtil.appendDeclaration(null, "font-family", terms), 
				((RuleSet)rules.get(0)).getDeclarations());
	}
	
	@Test
	public void testElementName() throws StyleSheetNotValidException {
		
		StyleSheet ss = (new CSSParser(TEST_MULTIPLE)).parse();
		
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		final RuleSet rule = (RuleSet) rules.get(0);				
		
		List<Selector> selectors = rule.getSelectors();
		
		assertEquals("Rule rule contains two selectors", 2, selectors.size());
		
		// H1
		Selector s = selectors.get(0);
		List<SimpleSelector> list = s.getSimpleSelectors();
		
		assertEquals("Selector 1 contains one simple selector", 1, list.size());
		
		assertEquals("Selector contains element name", "H1", list.get(0).getElementName());
		
		
		// DIV
		s = selectors.get(1);
		list = s.getSimpleSelectors();
		
		assertEquals("Selector 2 contains one simple selector", 1, list.size());
		
		assertEquals("Selector contains element name", "DIV", list.get(0).getElementName());
	}
	
	@Test
	public void testAttributePresence() throws StyleSheetNotValidException {
		
		StyleSheet ss = (new CSSParser(TEST_ATTRIB_PRESENCE)).parse();
		
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		List<Selector> sels = SelectorsUtil.appendSelector(null);
		SelectorsUtil.appendSimpleSelector(sels, "*", null, 
				new SimpleSelectorImpl.ItemAttributeImpl(
						null, false, SimpleSelector.Operator.NO_OPERATOR, "href"));				
		
		assertEquals("Rule 1 contains one combined selector *[href]",
				sels, ((RuleSet) rules.get(0)).getSelectors());

		List<Term> terms = DeclarationsUtil.appendTerm(null, null, 
				new TermIdentImpl("Verdana"));
		DeclarationsUtil.appendCommaTerm(terms, 
				new TermIdentImpl("monospace"));
		
		assertEquals("Rule contains one declaration { text-decoration: underline }",
		DeclarationsUtil.appendDeclaration(null, "text-decoration", 
				new TermIdentImpl("underline")),
				((RuleSet) rules.get(0)).getDeclarations());		
		
	}
	
	
}
