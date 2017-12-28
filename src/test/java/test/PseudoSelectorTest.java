/**
 * 
 */
package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.TermFactory;

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


    @Test 
    public void testHas() throws IOException, CSSException   {
        
        StyleSheet ss = CSSFactory.parseString(TEST_HAS, null);
        
        assertEquals("One rule is set", 1, ss.size());
    }
    
    
}
