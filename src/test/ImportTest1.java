package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cz.vutbr.web.css.ImportURI;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StyleSheetNotValidException;
import cz.vutbr.web.csskit.ImportURIImpl;
import cz.vutbr.web.csskit.parser.CssParser;

public class ImportTest1 {

	public static final String SIMPLE_IMPORT = "@import 'bla.css';\n";

	public static final String URL_IMPORT = "@import url('bla.css');\n";

	public static final String QUOT_IMPORT = "@import \"bla.css\";\n";

	public static final String DOUBLE_IMPORT = "@import 'file1.css';\n"
			+ "@import 'file2.css';";

	public static final String MEDIA_IMPORT = "@import 'test.css' aural;"
			+ "@import 'test-print.css' print, screen\n;";

	@Test
	public void testSimpleImport() throws StyleSheetNotValidException {

		StyleSheet ss = (new CssParser(SIMPLE_IMPORT)).parse();

		List<ImportURI> imports = ss.getImports();

		assertEquals("There should be one import", 1, imports.size());

		assertEquals("Import should be 'bla.css'", "bla.css", imports.get(0)
				.getUri());
		assertEquals("Encapsulation into CSS element",
				"@import url('bla.css');\n", imports.get(0).toString());
	}

	@Test
	public void testQuotImport() throws StyleSheetNotValidException {

		StyleSheet ss = (new CssParser(QUOT_IMPORT)).parse();

		List<ImportURI> imports = ss.getImports();

		assertEquals("There should be one import", 1, imports.size());

		assertEquals("Import should be 'bla.css'", "bla.css", imports.get(0)
				.getUri());
		assertEquals("Encapsulation into CSS element",
				"@import url('bla.css');\n", imports.get(0).toString());
	}

	@Test
	public void testURLImport() throws StyleSheetNotValidException {

		StyleSheet ss = (new CssParser(URL_IMPORT)).parse();

		List<ImportURI> imports = ss.getImports();

		assertEquals("There should be one import", 1, imports.size());

		assertEquals("Import should be 'bla.css'", "bla.css", imports.get(0)
				.getUri());
		assertEquals("Encapsulation into CSS element",
				"@import url('bla.css');\n", imports.get(0).toString());

	}

	@Test
	public void testDoubleImport() throws StyleSheetNotValidException {

		StyleSheet ss = (new CssParser(DOUBLE_IMPORT)).parse();

		List<ImportURI> imports = ss.getImports();

		assertEquals("There should be two imports", 2, imports.size());

		assertEquals("First import should be 'file1.css'", "file1.css", imports
				.get(0).getUri());
		assertEquals("Second import should be 'file2.css'", "file2.css",
				imports.get(1).getUri());

	}

	@Test
	public void testMediaImport() throws StyleSheetNotValidException {

		final ImportURI rule1 = new ImportURIImpl();
		List<String> rule1medias = new ArrayList<String>(1);
		rule1medias.add("aural");
		rule1.setUri("test.css");
		rule1.setMedias(rule1medias);

		final ImportURI rule2 = new ImportURIImpl();
		List<String> rule2medias = new ArrayList<String>(2);
		rule2medias.add("print");
		rule2medias.add("screen");
		rule2.setUri("test-print.css");
		rule2.setMedias(rule2medias);

		StyleSheet ss = (new CssParser(MEDIA_IMPORT)).parse();

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
