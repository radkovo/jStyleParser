package test;

import org.junit.Test;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StyleSheetNotValidException;
import cz.vutbr.web.csskit.parser.CssParser;
import cz.vutbr.web.domassign.Analyzer;

import static org.junit.Assert.*;

public class AnalyzerTest {

	@Test
	public void analyzeSimple() throws StyleSheetNotValidException {
		
		StyleSheet ss = (new CssParser(SimpleTest.TEST_STRING2)).parse();
		
		Analyzer a = new Analyzer(ss);
		
		
		
	}
	
}
