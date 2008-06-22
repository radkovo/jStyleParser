package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StyleSheetNotValidException;
import cz.vutbr.web.csskit.parser.CssParser;

public class GrammarRecovery1 {

	public static final String TEST_CHARSET1 =
		"@charset \"UTF-8\"";
		
	public static final String TEST_CHARSET2 = 
		"@charset \"UTF-8\"\n" +
		"BODY { color: blue;}";
	
	@Test	
	public void charsetRecovery() throws StyleSheetNotValidException {
		
		StyleSheet ss = (new CssParser(TEST_CHARSET1)).parse();
		assertEquals("Charset should not be set", null, ss.getCharset());
	}
	
	@Test
	public void avoidOutOfMemory() throws StyleSheetNotValidException {
		
		// this will skip to token which is not found,
		// so either NL or EOF must be checked
		StyleSheet ss = (new CssParser(TEST_CHARSET2)).parse();
		assertEquals("Charset should not be set", null, ss.getCharset());
		
	}
	
}
