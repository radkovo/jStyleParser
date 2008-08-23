package test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;

import org.junit.Test;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.TermFactory;

public class GrammarRecovery1 {

	public static final TermFactory tf = CSSFactory.getTermFactory();
	
	
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
	public void charsetCharsetWithoutSemicolon() {
		
		StyleSheet ss = CSSFactory.parse(new StringReader(TEST_CHARSET_WITHOUT_SEMICOLON1));
		assertEquals("Charset should not be set", null, ss.getCharset());
		
		assertEquals("No rules are defined", 0, ss.size());
	}
	
	@Test
	public void charsetWithoutSemicolonAndDefinitinAfter() throws FileNotFoundException {
		
		StyleSheet ss = CSSFactory.parse(new FileReader("data/invalid/recovery2.css"));
		assertEquals("Charset should not be set", null, ss.getCharset());
		
		assertEquals("No rules are set", 0, ss.size());
		
	}
	
	@Test
	public void charsetWithoutSemicolonAndDoubleDAfter() {
		
		StyleSheet ss = CSSFactory.parse(new StringReader(TEST_CHARSET_WITHOUT_SEMICOLON3));
		assertEquals("Charset should not be set", null, ss.getCharset());
		
		RuleSet rule = (RuleSet) ss.get(0);				
		
		assertEquals("Rule contains one selector BODY ", 
				SelectorsUtil.createSelectors("BODY"), 
				rule.getSelectors());
		
		assertEquals("Rule contains one declaration { color: red;}",
				DeclarationsUtil.appendDeclaration(null, "color", 
						tf.createColor(255,0,0)),
				rule.asList());
		
	}
	
}
