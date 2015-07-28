package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.Selector.ElementAttribute;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.Term.Operator;
import cz.vutbr.web.css.TermFactory;

public class EscapingTest {
	private static final Logger log = LoggerFactory.getLogger(EscapingTest.class);
	
	public static final TermFactory tf = CSSFactory.getTermFactory();
	
	public static final String TEST_STRING1 = "\\65 m { color: green; }";
	public static final String TEST_STRING2 = ".\\3A \\`\\( { color: green; }";
	public static final String TEST_STRING3 = ".\\31 a2b3c { color: green; }";
	public static final String TEST_STRING4 = "#\\#fake-id { color: green; }";
	public static final String TEST_STRING5 = "#-a-b-c- { color: green; }";
	public static final String TEST_STRING6 = "#© { color: green; }";
	public static final String TEST_STRING7 = ".\\61 atest { color: green; }";
	public static final String TEST_STRING8 = ".\\000061 atest { color: red; }";
	public static final String TEST_STRING9 = ".\\000062btest { color: green; }";
	public static final String TEST_STRING10 = ".\\62btest { color: red; }";
	public static final String TEST_STRING11 = "h1 { background: white \\75 rl('img1.jpg') top left no-repeat; }";
	public static final String TEST_STRING12 = "body { background: white url('\\69 mg1.jpg') bottom right no-repeat fixed; }";
	public static final String TEST_STRING13 = "script[type=text\\/plain] { display: block; }";
	
	@BeforeClass
	public static void init()  {
		log.info("\n\n\n == EscapingTest test at {} == \n\n\n", new Date());
	}
	
	
	@Test
	public void testElementNames() throws IOException, CSSException   {
		
		StyleSheet ss;
		
		ss = CSSFactory.parseString(TEST_STRING1, null);
		assertEquals("One rule is set", 1, ss.size());
		RuleSet rule = (RuleSet) ss.get(0);				
		assertArrayEquals("Rule contains one selector em ", 
				SelectorsUtil.createSelectors("em"), 
				rule.getSelectors());
	}
	
    @Test
    public void testClasses() throws IOException, CSSException   {
        assertSingleClassName(TEST_STRING2, ":`(", true);
        assertSingleClassName(TEST_STRING3, "1a2b3c", true);
    }
    
    @Test
    public void testIdentifiers() throws IOException, CSSException   {
        
        assertSingleId(TEST_STRING4, "#fake-id");
        assertSingleId(TEST_STRING5, "-a-b-c-");
        assertSingleId(TEST_STRING6, "©");
    }
    
    @Test
    public void testWhitespace() throws IOException, CSSException   {
        
        assertSingleClassName(TEST_STRING7, "aatest", true);
        assertSingleClassName(TEST_STRING8, "aatest", true);
        assertSingleClassName(TEST_STRING9, "bbtest", true);
        assertSingleClassName(TEST_STRING10, "bbtest", false);
    }
    
    @Test
    public void testUrls() throws IOException, CSSException   {
        
        StyleSheet ss;
        RuleSet rule;
        Declaration decl;
        
        Term<?> q = tf.createURI("img1.jpg");
        q.setOperator(Operator.SPACE);
        
        ss = CSSFactory.parseString(TEST_STRING11, null);
        assertEquals("One rule is set", 1, ss.size());
        rule = (RuleSet) ss.get(0);
        assertEquals("There is one declaration", 1, rule.size());
        decl = rule.get(0);
        assertEquals("There are five values assigned", 5, decl.size());
        assertEquals("The URL is correct", q, decl.get(1));
        
        ss = CSSFactory.parseString(TEST_STRING12, null);
        assertEquals("One rule is set", 1, ss.size());
        rule = (RuleSet) ss.get(0);
        assertEquals("There is one declaration", 1, rule.size());
        decl = rule.get(0);
        assertEquals("There are five values assigned", 6, decl.size());
        assertEquals("The URL is correct", q, decl.get(1));
        
    }
    
    @Test
    public void testAttributes() throws IOException, CSSException   {
        
        StyleSheet ss = CSSFactory.parseString(TEST_STRING13, null);
        assertEquals("One rule is set", 1, ss.size());
        RuleSet rule = (RuleSet) ss.get(0);
        assertEquals("One combined selector", rule.getSelectors().length, 1);
        CombinedSelector cs = rule.getSelectors()[0];
        assertEquals("One selector", cs.size(), 1);
        ElementAttribute attr = (ElementAttribute) cs.get(0).get(1);
        assertEquals("The attribute value", "text/plain", attr.getValue());
    
    }
    
    private void assertSingleClassName(String input, String className, boolean positive) throws IOException, CSSException
    {
        StyleSheet ss = CSSFactory.parseString(input, null);
        assertEquals("One rule is set", 1, ss.size());
        RuleSet rule = (RuleSet) ss.get(0);
        assertEquals("One combined selector", rule.getSelectors().length, 1);
        CombinedSelector cs = rule.getSelectors()[0];
        assertEquals("One selector", cs.size(), 1);
        if (positive)
            assertEquals("The class name is correct", className, cs.get(0).getClassName());
        else
            assertNotEquals("The class name is correct", className, cs.get(0).getClassName());
    }
    
    private void assertSingleId(String input, String className) throws IOException, CSSException
    {
        StyleSheet ss = CSSFactory.parseString(input, null);
        assertEquals("One rule is set", 1, ss.size());
        RuleSet rule = (RuleSet) ss.get(0);
        assertEquals("One combined selector", rule.getSelectors().length, 1);
        CombinedSelector cs = rule.getSelectors()[0];
        assertEquals("One selector", cs.size(), 1);
        assertEquals("The class name is correct", className, cs.get(0).getIDName());
    }
    
    
}
