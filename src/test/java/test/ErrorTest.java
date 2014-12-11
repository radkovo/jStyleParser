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
import cz.vutbr.web.css.TermFactory;

public class ErrorTest {
    private static Logger log = LoggerFactory.getLogger(ErrorTest.class);
    
    public static final TermFactory tf = CSSFactory.getTermFactory();
    
    public static final String TEST_STRING1 = 
        "BODY { margin: 1em 2em 3em 4em 5em;}";
    
    @BeforeClass
    public static void init()  {
        log.info("\n\n\n == ErrorTest test at {} == \n\n\n", new Date());
    }
    
    
    @Test 
    public void testString1() throws IOException, CSSException   {
        
        StyleSheet ss = CSSFactory.parseString(TEST_STRING1, null);
        
        assertEquals("One rule is set", 1, ss.size());
        
        RuleSet rule = (RuleSet) ss.get(0);             
        
        assertEquals("Rule contains one selector BODY ", 
                SelectorsUtil.createSelectors("BODY"), 
                rule.getSelectors());
        
        assertEquals("Rule contains one declaration", rule.size(), 1);
                            
    }
    
    
}
