package test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import cz.vutbr.web.css.Rule;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StyleSheetNotValidException;
import cz.vutbr.web.csskit.TermColorImpl;
import cz.vutbr.web.csskit.TermIdentImpl;
import cz.vutbr.web.csskit.parser.CSSParser;

public class SimpleTest {

	
	public static final String TEST_STRING1 = 
		"BODY { display: block;}";
	
	public static final String TEST_CHARSET_STRING1 = 
		"@charset \"UTF-8\";";
	
	public static final String TEST_CHARSET_STRING2 = 
		"@charset \"ISO-8859-1\";\n" +
		"\n" +
		"BODY { color: blue;}";
	
	public static final String TEST_HASH_COLOR1 =
		"BODY { color: #00AA85;}";
	
	public static final String TEST_HASH_COLOR2 =
		"DIV, P { color: #CCC;}";
	
	public static final String TEST_RGBFUNCTION1 =
		"BODY { color: rgb(192,64,32);}";
	
	public static final String TEST_RGBFUNCTION2 =
		"BODY { color: rgb(50%,128,30%);} ";
	
	public static final String TEST_STRING2 = 
		"BODY {\n" +
		"	background-color: #EEE;\n" +
		"	color: red;\n" +
		"}\n" +
		"\n" +
		"H1.prvni+P {\n" +
		"	color: #00A;\n" +
		"}\n" +
		"\n" +
		"H1 {\n"+
		"	font-size: 20px;\n" +
		"	color: #000;\n" +
		"}\n" +
		"\n" +
		"H1.prvni {\n" +
		"	color: #00A;\n" +
		"}\n" +		
		"\n" +
		".AA {\n" +
		"font-weight: bold;\n" +
		"}\n" +
		"\n" +
		".BB {\n" +
		"	color: #0F0;}\n" +
		"\n";
	
	
	
	
	
	@Test
	public void testCharsets() throws StyleSheetNotValidException {
		
		StyleSheet ss;
		
		ss = (new CSSParser(TEST_CHARSET_STRING1)).parse();
		assertEquals("This should be UTF-8", "UTF-8", ss.getCharset());
		
		assertEquals("No rules are defined", 0, ss.getRules().size());

		ss = (new CSSParser(TEST_CHARSET_STRING2)).parse();
		assertEquals("This should be ISO-8859-1", "ISO-8859-1", ss.getCharset());
		
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		final RuleSet rule = (RuleSet) rules.get(0);				
		
		assertEquals("Rule contains one selector BODY ", 
				SelectorsUtil.createSelectors("BODY"), 
				rule.getSelectors());
		
		assertEquals("Rule contains one declaration { color: blue;}",
				DeclarationsUtil.appendDeclaration(null, "color", 
						new TermColorImpl(0,0,255)),
				rule.getDeclarations());	
	}
	
	
	@Test 
	public void testString1() throws StyleSheetNotValidException {
		
		StyleSheet ss = (new CSSParser(TEST_STRING1)).parse();
		
		assertEquals("No charset is set", null, ss.getCharset());
		assertEquals("No imports are set", 0, ss.getImports().size());
		
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		final RuleSet rule = (RuleSet) rules.get(0);				
		
		assertEquals("Rule contains one selector BODY ", 
				SelectorsUtil.createSelectors("BODY"), 
				rule.getSelectors());
		
		assertEquals("Rule contains one declaration {display:block;}",
				DeclarationsUtil.appendDeclaration(null, "display", 
						new TermIdentImpl("block")),
				rule.getDeclarations());
							
	}
	
	
	
	@Test 
	public void testRGBFunction1() throws StyleSheetNotValidException {
		
		StyleSheet ss = (new CSSParser(TEST_RGBFUNCTION1)).parse();
		
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		final RuleSet rule = (RuleSet) rules.get(0);				
		
		assertEquals("Rule contains one selector BODY ", 
				SelectorsUtil.createSelectors("BODY"), 
				rule.getSelectors());
		
		assertEquals("Rule contains one declaration {color: #00aa85;}",
				DeclarationsUtil.appendDeclaration(null, "color", 
						new TermColorImpl(192, 64, 32)),
				rule.getDeclarations());
		
	}
	
	@Test 
	public void testRGBFunction2() throws StyleSheetNotValidException {
		StyleSheet ss = (new CSSParser(TEST_RGBFUNCTION2)).parse();
		
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		final RuleSet rule = (RuleSet) rules.get(0);				
		
		assertEquals("Rule contains one selector BODY ", 
				SelectorsUtil.createSelectors("BODY"), 
				rule.getSelectors());
		
		assertEquals("Rule contains one declaration {color: rgb(50%,128,30%);}",
				DeclarationsUtil.appendDeclaration(null, "color", 
						new TermColorImpl(127, 128, 76)),
				rule.getDeclarations());
	}
	
	@Test
	public void testHashColor1() throws StyleSheetNotValidException {
		StyleSheet ss = (new CSSParser(TEST_HASH_COLOR1)).parse();
		
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		final RuleSet rule = (RuleSet) rules.get(0);				
		
		assertEquals("Rule contains one selector BODY ", 
				SelectorsUtil.createSelectors("BODY"), 
				rule.getSelectors());
		
		assertEquals("Rule contains one declaration {color: #00aa85;}",
				DeclarationsUtil.appendDeclaration(null, "color", 
						new TermColorImpl(0, 170, 133)),
				rule.getDeclarations());
		
	}
	
	@Test
	public void testHashColor2() throws StyleSheetNotValidException {
		
		StyleSheet ss = (new CSSParser(TEST_HASH_COLOR2)).parse();
		List<Rule> rules = ss.getRules();
		assertEquals("One rule is set", 1, rules.size());
		
		final RuleSet rule = (RuleSet) rules.get(0);				
		
		assertEquals("Rule contains two selectors DIV, P", 
				SelectorsUtil.createSelectors("DIV", "P"), 
				rule.getSelectors());
		
		assertEquals("Rule contains one declaration {color: #CCC;}",
				DeclarationsUtil.appendDeclaration(null, "color", 
						new TermColorImpl(204,204,204)),
				rule.getDeclarations());
	}
	
	@Test
	public void testString2() throws StyleSheetNotValidException {
		StyleSheet ss = (new CSSParser(TEST_STRING2)).parse();
		
		List<Rule> rules = ss.getRules();
		
		assertEquals("Six rules are set", 6, rules.size());
	}
	
}
