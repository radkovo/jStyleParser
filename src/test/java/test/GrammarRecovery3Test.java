package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;

import cz.vutbr.web.css.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GrammarRecovery3Test {
    private static final Logger log = LoggerFactory.getLogger(GrammarRecovery3Test.class);

    public static final TermFactory tf = CSSFactory.getTermFactory();

    public static final String TEST_EXPR1 = "p { top:20px;top:expression(body.scrollTop + 50 + \"px\"); position:absolute;}";

    @BeforeClass
    public static void init() {
        log.info("\n\n\n == GrammarRecovery3 test at {} == \n\n\n", new Date());
    }

    @Test
    public void expressionRecovery() throws IOException, CSSException {
        StyleSheet ss = CSSFactory.parseString(TEST_EXPR1, null);
        assertEquals("There are only two declarations", 2, ss.get(0).size());
        assertEquals("First property is top", "top", ((Declaration) ss.get(0).get(0)).getProperty());
        assertEquals("Top value is 20px", tf.createLength((float) 20.0, TermNumeric.Unit.px), ((Declaration) ss.get(0).get(0)).get(0));
        assertEquals("Second property is position", "position", ((Declaration) ss.get(0).get(1)).getProperty());
        assertEquals("Position is absolute", "absolute", ((Declaration) ss.get(0).get(1)).get(0).getValue());
    }

}
