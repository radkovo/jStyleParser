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
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.TermFactory;

public class GrammarRecovery2Test {
	private static final Logger log = LoggerFactory.getLogger(GrammarRecovery2Test.class);

	public static final TermFactory tf = CSSFactory.getTermFactory();

	public static final String TEST_DECL1A = "p { color: red; _width: 10em; }"; /* vendor-specific extension */
    public static final String TEST_DECL1B = "p { _width: 10em; color: red; }";

    public static final String TEST_DECL2A = "p { color: red; -width: 10em; }"; /* vendor-specific extension */
    public static final String TEST_DECL2B = "p { -width: 10em; color: red; }";
    
    public static final String TEST_DECL3A = "p { color: red; :width: 10em; }"; /* invalid character */
    public static final String TEST_DECL3B = "p { :width: 10em; color: red; }";
    
    public static final String TEST_DECL4A = "p { color: red; *width: 10em; }"; /* invalid character */
    public static final String TEST_DECL4B = "p { *width: 10em; color: red; }";
    
    public static final String TEST_DECL5A = "p { color: red; width: 10em !ie; }"; /* invalid !directive */
    public static final String TEST_DECL5B = "p { width: 10em !ie; color: red; }";
    
    /* invalid selector - the whole rule should be skipped */
    public static final String TEST_DECL6A = "#menu,x:-moz-any-link { color: green !ie; } #menu { border: 1px solid blue; }";
    public static final String TEST_DECL6B = "#menu { border: 1px solid blue; } #menu,x:-moz-any-link { color: green !ie; }";
    public static final String TEST_DECL6C = "p:invalidPseudoClass, p.test1 { color: yellow; background: red; }";
    
    /* invalid selector - the whole rule should be skipped but the other rules in media should be preserved (coorect {} matching test) */
    public static final String TEST_DECL7A = "@media { #menu,x:-moz-any-link { color: green !ie; } #menu { border: 1px solid blue; } }";
    public static final String TEST_DECL7B = "@media { #menu { border: 1px solid blue; } #menu,x:-moz-any-link { color: green !ie; } }";
    
    public static final String TEST_DECL8A = ".button{border-color:#c5c5c5 #7d7d7d #7b7b7b #a1a1a1}@-moz-document url-prefix(){.email-feed .g-cnt .button{padding:2px 0 3px 0}}.email{color:red}";
    
    /* invalid tokens among the declarations -- should be skipped */
    public static final String TEST_DECL9A = "p{background: white;border-color: #FCFCFC; #FCFCFC; #FDFDFD #FCFCFC; 123; url('nazdar'); 145%; 12em;color: red;}";
    
    /* invalid character in the value -- the declaration should be ignored */
    public static final String TEST_DECL10A = "div{color:green;background: url(\"support/red_box.png\"&);}";
    
    /* a rule with no selector should be skipped */
    public static final String TEST_DECL11A = "p{color:blue}{color:red}p{color:green}";
    
	@BeforeClass
	public static void init() 
	{
		log.info("\n\n\n == GrammarRecovery2 test at {} == \n\n\n", new Date());
	}

	@Test
	public void vendorSpecificUnderscore() throws IOException, CSSException 
	{
		StyleSheet ss = CSSFactory.parseString(TEST_DECL1A, null);
		assertEquals("Both properties are accepted (second one is extension)", 2, ss.get(0).size());
        ss = CSSFactory.parseString(TEST_DECL1B, null);
        assertEquals("Both properties are accepted (first one is extension)", 2, ss.get(0).size());
	}

    @Test
    public void vendorSpecificDash() throws IOException, CSSException 
    {
        StyleSheet ss = CSSFactory.parseString(TEST_DECL2A, null);
        assertEquals("Both properties are accepted (second one is extension)", 2, ss.get(0).size());
        ss = CSSFactory.parseString(TEST_DECL2B, null);
        assertEquals("Both properties are accepted (first one is extension)", 2, ss.get(0).size());
    }
    
    @Test
    public void invalidCharColon() throws IOException, CSSException 
    {
        StyleSheet ss = CSSFactory.parseString(TEST_DECL3A, null);
        assertEquals("One property is accepted (second one is invalid)", 1, ss.get(0).size());
        ss = CSSFactory.parseString(TEST_DECL3B, null);
        assertEquals("One property is accepted (first one is invalid)", 1, ss.get(0).size());
    }
    
    @Test
    public void invalidCharAsterisk() throws IOException, CSSException 
    {
        StyleSheet ss = CSSFactory.parseString(TEST_DECL4A, null);
        assertEquals("One property is accepted (second one is invalid)", 1, ss.get(0).size());
        ss = CSSFactory.parseString(TEST_DECL4B, null);
        assertEquals("One property is accepted (first one is invalid)", 1, ss.get(0).size());
    }

    @Test
    public void invalidDirective() throws IOException, CSSException 
    {
        StyleSheet ss = CSSFactory.parseString(TEST_DECL5A, null);
        assertEquals("One property is accepted (second one is invalid)", 1, ss.get(0).size());
        ss = CSSFactory.parseString(TEST_DECL5B, null);
        assertEquals("One property is accepted (first one is invalid)", 1, ss.get(0).size());
    }
    
    @Test
    public void invalidSelector() throws IOException, CSSException 
    {
        StyleSheet ss = CSSFactory.parseString(TEST_DECL6A, null);
        assertEquals("One declaration is accepted (second one is invalid)", 1, ss.size());
        ss = CSSFactory.parseString(TEST_DECL6B, null);
        assertEquals("One declaration is accepted (first one is invalid)", 1, ss.size());
        ss = CSSFactory.parseString(TEST_DECL6C, null);
        assertEquals("The whole rule is discarded (invalid pseudo class)", 0, ss.size());
    }
    
    @Test
    public void invalidSelectorMedia() throws IOException, CSSException 
    {
        StyleSheet ss = CSSFactory.parseString(TEST_DECL7A, null);
        assertEquals("Style sheet contains one media rule", 1, ss.size());
        assertEquals("One declaration is accepted (second one is invalid)", 1, ss.get(0).size());
        ss = CSSFactory.parseString(TEST_DECL7B, null);
        assertEquals("Style sheet contains one media rule", 1, ss.size());
        assertEquals("One declaration is accepted (first one is invalid)", 1, ss.get(0).size());
    }
    
    @Test
    public void invalidSelectorSyntax() throws IOException, CSSException 
    {
        StyleSheet ss = CSSFactory.parseString(TEST_DECL8A, null);
        assertEquals("Style sheet contains two rules (the middle one is invalid)", 2, ss.size());
    }
    
    @Test
    public void invalidTokensAmongDeclarations() throws IOException, CSSException 
    {
        StyleSheet ss = CSSFactory.parseString(TEST_DECL9A, null);
        assertEquals("Style sheet contains one rule", 1, ss.size());
        assertEquals("Three declarations are accepted", 3, ss.get(0).size());
        Declaration d = (Declaration) ss.get(0).get(2);
        assertEquals("The last declaration defines color", "color", d.getProperty());
    }
    
    @Test
    public void invalidCharacterInValue() throws IOException, CSSException 
    {
        StyleSheet ss = CSSFactory.parseString(TEST_DECL10A, null);
        assertEquals("Style sheet contains one rule", 1, ss.size());
        assertEquals("Only the first declaration is accepted", 1, ss.get(0).size());
    }
    
    @Test
    public void ruleWithNoSelector() throws IOException, CSSException 
    {
        StyleSheet ss = CSSFactory.parseString(TEST_DECL11A, null);
        assertEquals("Style sheet contains two rules", 2, ss.size());
    }
    
}
