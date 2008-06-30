package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StyleSheetNotValidException;
import cz.vutbr.web.csskit.TermColorImpl;
import cz.vutbr.web.csskit.parser.CSSParser;

public class GrammarRecovery1 {

	public static final String TEST_CHARSET_WITHOUT_SEMICOLON1 =
		"@charset \"UTF-8\"";
		
	public static final String TEST_CHARSET_WITHOUT_SEMICOLON2 = 
		"@charset \"UTF-8\"\n" +
		"BODY { color: blue;}";
	
	public static final String TEST_CHARSET_WITHOUT_SEMICOLON3 = 
		"@charset \"UTF-8\"\n" +
		"BODY { color: blue;}\n" +
		"BODY { color: red; }";
	
	@Test	
	public void charsetCharsetWithoutSemicolon() throws StyleSheetNotValidException {
		
		StyleSheet ss = (new CSSParser(TEST_CHARSET_WITHOUT_SEMICOLON1)).parse();
		assertEquals("Charset should not be set", null, ss.getCharset());
		
		assertEquals("No rules are defined", 0, ss.getRules().size());
	}
	
	@Test
	public void charsetWithoutSemicolonAndDefinitinAfter() throws StyleSheetNotValidException {
		
		StyleSheet ss = (new CSSParser(TEST_CHARSET_WITHOUT_SEMICOLON2)).parse();
		assertEquals("Charset should not be set", null, ss.getCharset());
		
		assertEquals("No rules are set", 0, ss.getRules().size());
		
	}
	
	@Test
	public void charsetWithoutSemicolonAndDoubleDAfter() throws StyleSheetNotValidException {
		
		StyleSheet ss = (new CSSParser(TEST_CHARSET_WITHOUT_SEMICOLON3)).parse();
		assertEquals("Charset should not be set", null, ss.getCharset());
		
		final RuleSet rule = (RuleSet) ss.getRules().get(0);				
		
		assertEquals("Rule contains one selector BODY ", 
				SelectorsUtil.createSelectors("BODY"), 
				rule.getSelectors());
		
		assertEquals("Rule contains one declaration { color: red;}",
				DeclarationsUtil.appendDeclaration(null, "color", 
						new TermColorImpl(255,0,0)),
				rule.getDeclarations());
		
	}
	
}
