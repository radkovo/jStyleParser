/**
 * 
 */
package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import cz.vutbr.web.css.*;
import org.junit.Test;

/**
 * @author burgetr
 *
 */
public class PseudoSelectorTest
{
    //private static final Logger log = LoggerFactory.getLogger(PseudoSelectorTest.class);
    
    public static final TermFactory tf = CSSFactory.getTermFactory();
    
    //test case for #81 -- :has is not interpreted as a correct pseudo class because it is not
    //intended for use in style sheets. However, it should be parsed without a NPE.
    public static final String TEST_HAS = 
        "td:has(.servNoPad) { padding:0px !important; }";

    public static final String TEST_IS =
            "td:is(.servNoPad) { padding:0px !important; }";

    public static final String TEST_IS_MULTI =
            "td:is(.servNoPad, a) { padding:0px !important; }";


    @Test 
    public void testHas() throws IOException, CSSException   {
        
        StyleSheet ss = CSSFactory.parseString(TEST_HAS, null);
        
        assertEquals("One rule is set", 1, ss.size());
    }

    @Test
    public void testIs() throws IOException, CSSException   {

        StyleSheet ss = CSSFactory.parseString(TEST_IS, null);

        assertEquals("One rule is set", 1, ss.size());
    }

    @Test
    public void testIsMulti() throws IOException, CSSException   {

        StyleSheet ss = CSSFactory.parseString(TEST_IS_MULTI, null);

        assertEquals("One rule is set", 1, ss.size());
        assertEquals("parsed rule", "td:is(.servNoPad, a)", ((RuleSet)ss.get(0)).getSelectors()[0].toString());
    }
    
    
}
