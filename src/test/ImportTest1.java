package test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.RuleFactory;
import cz.vutbr.web.css.RuleImport;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StyleSheetNotValidException;

public class ImportTest1 {
	private static Logger log = LoggerFactory.getLogger(ImportTest1.class);
	
	private static final RuleFactory rf = CSSFactory.getRuleFactory();

	public static final String SIMPLE_IMPORT = "@import 'bla.css';\n";

	public static final String URL_IMPORT = "@import url('bla.css');\n";

	public static final String QUOT_IMPORT = "@import \"bla.css\";\n";

	public static final String DOUBLE_IMPORT = "@import 'file1.css';\n"
			+ "@import 'file2.css';";

	public static final String MEDIA_IMPORT = "@import 'test.css' aural;"
			+ "@import 'test-print.css' print, screen\n;";

	@BeforeClass
	public static void init()  {
		log.info("\n\n\n == ImportTest1 test at {} == \n\n\n", new Date());
	}
	
	@Test
	public void testSimpleImport() throws StyleSheetNotValidException {

		StyleSheet ss = CSSFactory.parse("data/simple/imp.css", null);
		
	}
	
	/*
	@Test
	public void testQuotImport() throws StyleSheetNotValidException {

		StyleSheet ss = CSSFactory.parse(QUOT_IMPORT);

		assertEquals("There should be one import", 1, ss.size());
		
		RuleImport i = (RuleImport) ss.get(0);

		assertEquals("Import should be 'bla.css'", "bla.css", i.getURI());
		assertEquals("Encapsulation into CSS element",
				"@import url('bla.css');\n", i.toString());
	}

	@Test
	public void testURLImport() throws StyleSheetNotValidException {

		StyleSheet ss = CSSFactory.parse(URL_IMPORT);

		assertEquals("There should be one import", 1, ss.size());
		
		RuleImport i = (RuleImport) ss.get(0);

		assertEquals("Import should be 'bla.css'", "bla.css", i.getURI());
		assertEquals("Encapsulation into CSS element",
				"@import url('bla.css');\n", i.toString());

	}

	@Test
	public void testDoubleImport() throws StyleSheetNotValidException {

		StyleSheet ss = CSSFactory.parse(DOUBLE_IMPORT);	

		assertEquals("There should be two imports", 2, ss.size());
		
		RuleImport i1 = (RuleImport) ss.get(0);
		RuleImport i2 = (RuleImport) ss.get(1);

		assertEquals("First import should be 'file1.css'", "file1.css", i1.getURI());
		assertEquals("Second import should be 'file2.css'", "file2.css", i2.getURI());

	}

	@Test
	public void testMediaImport() throws StyleSheetNotValidException {

		final RuleImport rule1 = (RuleImport) rf.createImport(0).unlock();
		rule1.add("aural");
		rule1.setURI("test.css");

		final RuleImport rule2 = (RuleImport) rf.createImport(1).unlock();
		rule2.add("print");
		rule2.add("screen");
		rule2.setURI("test-print.css");

		StyleSheet ss = CSSFactory.parse(MEDIA_IMPORT);
		
		assertEquals("There should be two imports", 2, ss.size());

		assertEquals(
				"Media rule 1 should be equal to @media 'test.css' aural;",
				rule1, ss.get(0));
		assertEquals(
				"Media rule 2 should be equal to @media 'test-print.css' print, screen;",
				rule2, ss.get(1));
		
	}
	*/
}
