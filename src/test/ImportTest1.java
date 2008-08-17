package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.ImportURI;
import cz.vutbr.web.css.RuleFactory;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StyleSheetNotValidException;
import cz.vutbr.web.csskit.parser.CSSParser;

public class ImportTest1 {
	
	private static final RuleFactory rf = CSSFactory.getRuleFactory();

	public static final String SIMPLE_IMPORT = "@import 'bla.css';\n";

	public static final String URL_IMPORT = "@import url('bla.css');\n";

	public static final String QUOT_IMPORT = "@import \"bla.css\";\n";

	public static final String DOUBLE_IMPORT = "@import 'file1.css';\n"
			+ "@import 'file2.css';";

	public static final String MEDIA_IMPORT = "@import 'test.css' aural;"
			+ "@import 'test-print.css' print, screen\n;";

	@Test
	public void testSimpleImport() throws StyleSheetNotValidException {

		StyleSheet ss = (new CSSParser(SIMPLE_IMPORT)).parse();

		List<ImportURI> imports = ss.getImports();

		assertEquals("There should be one import", 1, imports.size());

		assertEquals("Import should be 'bla.css'", "bla.css", imports.get(0)
				.getUri());
		assertEquals("Encapsulation into CSS element",
				"@import url('bla.css');\n", imports.get(0).toString());
	}

	@Test
	public void testQuotImport() throws StyleSheetNotValidException {

		StyleSheet ss = (new CSSParser(QUOT_IMPORT)).parse();

		List<ImportURI> imports = ss.getImports();

		assertEquals("There should be one import", 1, imports.size());

		assertEquals("Import should be 'bla.css'", "bla.css", imports.get(0)
				.getUri());
		assertEquals("Encapsulation into CSS element",
				"@import url('bla.css');\n", imports.get(0).toString());
	}

	@Test
	public void testURLImport() throws StyleSheetNotValidException {

		StyleSheet ss = (new CSSParser(URL_IMPORT)).parse();

		List<ImportURI> imports = ss.getImports();

		assertEquals("There should be one import", 1, imports.size());

		assertEquals("Import should be 'bla.css'", "bla.css", imports.get(0)
				.getUri());
		assertEquals("Encapsulation into CSS element",
				"@import url('bla.css');\n", imports.get(0).toString());

	}

	@Test
	public void testDoubleImport() throws StyleSheetNotValidException {

		StyleSheet ss = (new CSSParser(DOUBLE_IMPORT)).parse();

		List<ImportURI> imports = ss.getImports();

		assertEquals("There should be two imports", 2, imports.size());

		assertEquals("First import should be 'file1.css'", "file1.css", imports
				.get(0).getUri());
		assertEquals("Second import should be 'file2.css'", "file2.css",
				imports.get(1).getUri());

	}

	@Test
	public void testMediaImport() throws StyleSheetNotValidException {

		ImportURI rule1 = (ImportURI) rf.createImport().replaceAll(new ArrayList<String>(1));
		rule1.add("aural");
		rule1.setUri("test.css");

		final ImportURI rule2 = (ImportURI) rf.createImport().replaceAll(new ArrayList<String>(2));
		rule2.add("print");
		rule2.add("screen");
		rule2.setUri("test-print.css");

		StyleSheet ss = (new CSSParser(MEDIA_IMPORT)).parse();
		List<ImportURI> imports = ss.getImports();
		assertEquals("There should be two imports", 2, imports.size());

		assertEquals(
				"Media rule 1 should be equal to @media 'test.css' aural;",
				rule1, imports.get(0));
		assertEquals(
				"Media rule 2 should be equal to @media 'test-print.css' print, screen;",
				rule2, imports.get(1));
		
	}

}
