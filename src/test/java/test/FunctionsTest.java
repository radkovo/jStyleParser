package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermFunction;

public class FunctionsTest {
	private static Logger log = LoggerFactory.getLogger(FunctionsTest.class);

	public static final TermFactory tf = CSSFactory.getTermFactory();

	/* parentheses in strings - pair matching test */
	public static final String TEST_DECL1A = " a:after { content: \"(\" attr(href) \")\"; border: 1px solid red; }";
    public static final String TEST_DECL1B = " a:after { content: \"{\" attr(href) \"}\"; border: 1px solid red; }";

    /* function name may start with minus */
    public static final String TEST_DECL2A = " a:after { background-image:-moz-linear-gradient(top,#fff,#ececec); border: 1px solid red; }";
    
	@BeforeClass
	public static void init() 
	{
		log.info("\n\n\n == Functions test at {} == \n\n\n", new Date());
	}

	@Test
	public void vendorSpecificUnderscore() throws IOException, CSSException 
	{
		StyleSheet ss = CSSFactory.parseString(TEST_DECL1A, null);
		assertEquals("Two properties are accepted", 2, ss.get(0).size());
		
		final RuleSet rule = (RuleSet) ss.get(0);
        assertEquals("The first property value has three terms", 3, rule.get(0).size());
	}
	
	@Test
	public void vendorSpecificFunctions() throws IOException, CSSException 
	{
		StyleSheet ss = CSSFactory.parseString(TEST_DECL2A, null);
		assertEquals("Two properties are accepted", 2, ss.get(0).size());
		Declaration d = (Declaration) ss.get(0).get(0);
		TermFunction f = (TermFunction) d.get(0);
		char first = f.getFunctionName().charAt(0);
		assertEquals("Function name starts with minus", '-', first);
	}
}
