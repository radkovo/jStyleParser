/**
 * OutputTest.java
 *
 * Created on 30. 9. 2020, 12:34:37 by burgetr
 */
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
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;

/**
 * 
 * @author burgetr
 */
public class OutputTest
{
    private static final Logger log = LoggerFactory.getLogger(OutputTest.class);

    public static final String TEST_STR_FUNCARG1 = ".ui-autocomplete li div.blocco-arrivo span:nth-child(1) { font-size: 12px; color: #888 }";
    public static final String TEST_STR_ASTERISK = "* { color: black; }";

    @BeforeClass
    public static void init() {
        log.info("\n\n\n == FontFaceTest test at {} == \n\n\n", new Date());
    }

    @Test
    public void testNumericFuncArg() throws IOException, CSSException {
        log.info("input:\n\n\n" + TEST_STR_FUNCARG1 + "\n\n\n");
        
        StyleSheet ss = CSSFactory.parseString(TEST_STR_FUNCARG1, null);
        RuleSet rule = (RuleSet) ss.get(0);
        String ruleString = rule.toString();
        int backSlashIndex = ruleString.indexOf('\\');
        assertEquals("There is no backshlash in the function argument ", -1, backSlashIndex);
    }
    
    @Test
    public void testEscapeAsterisk() throws IOException, CSSException {
        log.info("input:\n\n\n" + TEST_STR_ASTERISK + "\n\n\n");
        
        StyleSheet ss = CSSFactory.parseString(TEST_STR_ASTERISK, null);
        RuleSet rule = (RuleSet) ss.get(0);
        String ruleString = rule.toString();
        assertEquals("Asterisk selector is not escaped ", '*', ruleString.charAt(0));
    }

}
