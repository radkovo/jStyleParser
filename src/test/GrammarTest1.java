package test;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Test;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StyleSheetNotValidException;
import cz.vutbr.web.csskit.parser.CssParser;

public class GrammarTest1 {

	private static final Logger log = Logger.getLogger(GrammarTest1.class);
	
	public static final String TEST_STRING1 = 
		"BODY { display: block;}";
	
	public static final String TEST_SELECTOR1 =
		"H1, DIV { display: block;}";
	
	public static final String TEST_SELECTOR2 =
		"H1 P { display: inline;}";
	
	public static final String TEST_SELECTOR3 =
		"DIV+P { color: blue;}"; 
	
	public static final String TEST_SELECTOR4 = 
		"DIV>P, SPAN, A, FORM+DIV { color: white;}";
	
	public static final String TEST_CHARSET_STRING1 = 
		"@charset \"UTF-8\";";
	
	public static final String TEST_CHARSET_STRING2 = 
		"@charset \"ISO-8859-1\";\n" +
		"\n" +
		"BODY { color: blue;}";
	
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
		
		ss = (new CssParser(TEST_CHARSET_STRING1)).parse();
		assertEquals("This should be UTF-8", "UTF-8", ss.getCharset());

		//log.debug(ss);
		
		ss = (new CssParser(TEST_CHARSET_STRING2)).parse();
		assertEquals("This should be ISO-8859-1", "ISO-8859-1", ss.getCharset());
		
		//log.debug(ss);
	}
	
	
	@Test 
	public void testString1() throws StyleSheetNotValidException {
		
		StyleSheet ss = (new CssParser(TEST_STRING1)).parse();
     	//log.debug(ss);
		
	}
	
	@Test
	public void testSelector1() throws StyleSheetNotValidException {
		StyleSheet ss = (new CssParser(TEST_SELECTOR1)).parse();
		//log.debug(ss);
	}
	
	@Test
	public void testSelector2() throws StyleSheetNotValidException {
		StyleSheet ss = (new CssParser(TEST_SELECTOR2)).parse();
		//log.debug(ss);
	}
	
	@Test
	public void testSelector3() throws StyleSheetNotValidException {
		StyleSheet ss = (new CssParser(TEST_SELECTOR3)).parse();
		//log.debug(ss);
	}
	
	@Test
	public void testSelector4() throws StyleSheetNotValidException {
		StyleSheet ss = (new CssParser(TEST_SELECTOR4)).parse();
		log.debug(ss);
	}
	
	//@Test
	public void testString2() throws StyleSheetNotValidException {
		StyleSheet ss = (new CssParser(TEST_STRING2)).parse();
		//log.debug(ss);
	}
	
}
