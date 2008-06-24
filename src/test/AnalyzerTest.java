package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.naming.OperationNotSupportedException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StyleSheetNotValidException;
import cz.vutbr.web.csskit.parser.CssParser;
import cz.vutbr.web.domassign.Analyzer;

public class AnalyzerTest {

	@Test
	public void analyzeSimple() throws StyleSheetNotValidException, FileNotFoundException, OperationNotSupportedException {
		
		Tidy tidy = new Tidy();
		tidy.setCharEncoding(org.w3c.tidy.Configuration.UTF8);
		Document doc = tidy.parseDOM(new FileInputStream("data/simple/data.html"), null);
	
		StyleSheet ss = (new CssParser(SimpleTest.TEST_STRING2)).parse();

		Analyzer a = new Analyzer(ss);
		
		a.assingDeclarationsToDOM(doc, "all", true);
		
	}
	
}
