package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StyleSheetNotValidException;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.csskit.parser.CSSParser;

public class SimpleTest {

	public static final TermFactory tf = CSSFactory.getTermFactory();
	
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
		
		assertEquals("No rules are defined", 0, ss.size());

		ss = (new CSSParser(TEST_CHARSET_STRING2)).parse();
		assertEquals("This should be ISO-8859-1", "ISO-8859-1", ss.getCharset());
		
		assertEquals("One rule is set", 1, ss.size());
		
		RuleSet rule = (RuleSet) ss.get(0);				
		
		assertEquals("Rule contains one selector BODY ", 
				SelectorsUtil.createSelectors("BODY"), 
				rule.getSelectors());
		
		assertEquals("Rule contains one declaration { color: blue;}",
				DeclarationsUtil.appendDeclaration(null, "color", 
						tf.createColor(0,0,255)),
				rule.asList());	
	}
	
	
	@Test 
	public void testString1() throws StyleSheetNotValidException {
		
		StyleSheet ss = (new CSSParser(TEST_STRING1)).parse();
		
		assertEquals("No charset is set", null, ss.getCharset());
		assertEquals("No imports are set", 0, ss.getImports().size());
		assertEquals("One rule is set", 1, ss.size());
		
		RuleSet rule = (RuleSet) ss.get(0);				
		
		assertEquals("Rule contains one selector BODY ", 
				SelectorsUtil.createSelectors("BODY"), 
				rule.getSelectors());
		
		assertEquals("Rule contains one declaration {display:block;}",
				DeclarationsUtil.appendDeclaration(null, "display", 
						tf.createIdent("block")),
				rule.asList());
							
	}
	
	
	
	@Test 
	public void testRGBFunction1() throws StyleSheetNotValidException {
		
		StyleSheet ss = (new CSSParser(TEST_RGBFUNCTION1)).parse();
		assertEquals("One rule is set", 1, ss.size());
		
		RuleSet rule = (RuleSet) ss.get(0);				
		
		assertEquals("Rule contains one selector BODY ", 
				SelectorsUtil.createSelectors("BODY"), 
				rule.getSelectors());
		
		assertEquals("Rule contains one declaration {color: #00aa85;}",
				DeclarationsUtil.appendDeclaration(null, "color", 
						tf.createColor(192, 64, 32)),
				rule.asList());
		
	}
	
	@Test 
	public void testRGBFunction2() throws StyleSheetNotValidException {
		
		StyleSheet ss = (new CSSParser(TEST_RGBFUNCTION2)).parse();
		assertEquals("One rule is set", 1, ss.size());
		
		RuleSet rule = (RuleSet) ss.get(0);				
		
		assertEquals("Rule contains one selector BODY ", 
				SelectorsUtil.createSelectors("BODY"), 
				rule.getSelectors());
		
		assertEquals("Rule contains one declaration {color: rgb(50%,128,30%);}",
				DeclarationsUtil.appendDeclaration(null, "color", 
						tf.createColor(127, 128, 76)),
				rule.asList());
	}
	
	@Test
	public void testHashColor1() throws StyleSheetNotValidException {

		StyleSheet ss = (new CSSParser(TEST_HASH_COLOR1)).parse();
		assertEquals("One rule is set", 1, ss.size());
		
		RuleSet rule = (RuleSet) ss.get(0);				
		
		assertEquals("Rule contains one selector BODY ", 
				SelectorsUtil.createSelectors("BODY"), 
				rule.getSelectors());
		
		assertEquals("Rule contains one declaration {color: #00aa85;}",
				DeclarationsUtil.appendDeclaration(null, "color", 
						tf.createColor(0, 170, 133)),
				rule.asList());
		
	}
	
	@Test
	public void testHashColor2() throws StyleSheetNotValidException {
		
		StyleSheet ss = (new CSSParser(TEST_HASH_COLOR2)).parse();
		assertEquals("One rule is set", 1, ss.size());
		
		final RuleSet rule = (RuleSet) ss.get(0);				
		
		assertEquals("Rule contains two selectors DIV, P", 
				SelectorsUtil.createSelectors("DIV", "P"), 
				rule.getSelectors());
		
		assertEquals("Rule contains one declaration {color: #CCC;}",
				DeclarationsUtil.appendDeclaration(null, "color", 
						tf.createColor(204,204,204)),
				rule.asList());
	}
	
	@Test
	public void testString2() throws StyleSheetNotValidException {
		
		StyleSheet ss = (new CSSParser(TEST_STRING2)).parse();
		assertEquals("Six rules are set", 6, ss.size());
	}
	
}
