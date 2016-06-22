package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;

public class CharsetTest
{
    private static final Logger log = LoggerFactory.getLogger(CharsetTest.class);
    
    @BeforeClass
    public static void init()  {
        log.info("\n\n\n == CharsetTest test at {} == \n\n\n", new Date());
    }
    
    
    @Test 
    public void testString1() throws IOException, CSSException   {
        
        URL url = getClass().getResource("/simple/charset1.css");
        StyleSheet ss = CSSFactory.parse(url, "utf-8");
        
        assertEquals("One rule is set", 1, ss.size());
        
        RuleSet rule = (RuleSet) ss.get(0);             
        
        assertArrayEquals("Rule contains one selector BODY ", 
                SelectorsUtil.createSelectors("tést"), 
                rule.getSelectors());
        
        assertEquals("Rule contains one declaration", rule.size(), 1);
                            
    }

}
