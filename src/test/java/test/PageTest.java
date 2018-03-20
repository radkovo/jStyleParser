package test;

import cz.vutbr.web.css.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PageTest {
    private static final Logger log = LoggerFactory.getLogger(PageTest.class);

    public static final TermFactory tf = CSSFactory.getTermFactory();

    public static final String TEST_STRING1 =
            "@page {\n" +
                    "@top-left-corner { content: \" \"; border: solid green; }" +
                    "}";

    public static final String TEST_STRING2 = "@page :left { margin: 1cm; }";
    public static final String TEST_STRING3 = "@page :right { margin: 2cm; }";
    public static final String TEST_STRING4 = "@page :first { margin: 3cm; }";
    public static final String TEST_STRING5 = "@page :hover { margin: 4cm; }"; // Invalid
    
    @BeforeClass
    public static void init() {
        log.info("\n\n\n == PageTest test at {} == \n\n\n", new Date());
    }

    @Test
    public void testMarginRule() throws IOException, CSSException {
        log.info("input:\n\n\n" + TEST_STRING1 + "\n\n\n");
        StyleSheet ss;
        ss = CSSFactory.parseString(TEST_STRING1, null);
        assertEquals("One rule is set", 1, ss.size());
        RulePage rule = (RulePage) ss.get(0);
        assertEquals("Rule contains 1 declaration ", 1, rule.size());
        RuleMargin margin = (RuleMargin) rule.get(0);
        assertEquals("Margin rule is @top-left-corner", RuleMargin.MarginArea.TOPLEFTCORNER, margin.getMarginArea());
        assertEquals("Margin rule contains 2 declarations", 2, margin.size());
    }

    @Test
    public void testLeftPseudo() throws IOException, CSSException {
        StyleSheet ss = CSSFactory.parseString(TEST_STRING2, null);
        assertEquals("One rule is set", 1, ss.size());
        RulePage rule = (RulePage) ss.get(0);
        assertNotNull("Rule has a pseudo-class ", rule.getPseudo());
        assertEquals("Rule has :left pseudo-class ", Selector.PseudoPageType.LEFT, rule.getPseudo().getType());
        assertEquals("Rule contains 1 declaration ", 1, rule.size());
    }
    
    @Test
    public void testRightPseudo() throws IOException, CSSException {
        StyleSheet ss = CSSFactory.parseString(TEST_STRING3, null);
        assertEquals("One rule is set", 1, ss.size());
        RulePage rule = (RulePage) ss.get(0);
        assertNotNull("Rule has a pseudo-class ", rule.getPseudo());
        assertEquals("Rule has :right pseudo-class ", Selector.PseudoPageType.RIGHT, rule.getPseudo().getType());
        assertEquals("Rule contains 1 declaration ", 1, rule.size());
    }
    
    @Test
    public void testFirstPseudo() throws IOException, CSSException {
        StyleSheet ss = CSSFactory.parseString(TEST_STRING4, null);
        assertEquals("One rule is set", 1, ss.size());
        RulePage rule = (RulePage) ss.get(0);
        assertNotNull("Rule has a pseudo-class ", rule.getPseudo());
        assertEquals("Rule has :first pseudo-class ", Selector.PseudoPageType.FIRST, rule.getPseudo().getType());
        assertEquals("Rule contains 1 declaration ", 1, rule.size());
    }
    
    @Test
    public void testInvalidPseudo() throws IOException, CSSException {
        StyleSheet ss = CSSFactory.parseString(TEST_STRING5, null);
        assertEquals("No rules are set", 0, ss.size());
    }
}
