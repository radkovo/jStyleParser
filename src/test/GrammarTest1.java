package test;

import org.apache.log4j.Logger;
import org.junit.Test;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.csskit.Engine;
import cz.vutbr.web.csskit.parser.ParseException;


public class GrammarTest1 {

	private static final Logger log = Logger.getLogger(GrammarTest1.class);
	
	public static final String TEST_STRING1 = 
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
	public void testString1() throws ParseException {
		
		log.debug("Entering testString1()");
		
		StyleSheet ss = Engine.parse(TEST_STRING1);
		
		log.debug(ss);
		
		log.debug("Leaving testString1()");
		
	}	
	
}
