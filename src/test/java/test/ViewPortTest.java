package test;

import cz.vutbr.web.css.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ViewPortTest {
    private static final Logger log = LoggerFactory.getLogger(ViewPortTest.class);

    public static final TermFactory tf = CSSFactory.getTermFactory();

    public static final String TEST_STRING1 =
            "@viewport {\n" +
                    "  min-width: 640px;\n" +
                    "  max-width: 800px;\n" +
                    "}";

    @BeforeClass
    public static void init() {
        log.info("\n\n\n == ViewPortTest test at {} == \n\n\n", new Date());
    }


    @Test
    public void testSimpleVP() throws IOException, CSSException {
        log.info("input:\n\n\n" + TEST_STRING1 + "\n\n\n");
        StyleSheet ss;

        ss = CSSFactory.parseString(TEST_STRING1, null);

        assertEquals("One rule is set", 1, ss.size());

        RuleViewport rule = (RuleViewport) ss.get(0);
        assertEquals("Rule contains 2 declarations ", 2, rule.size());
        assertEquals("Rule contains min-width declaration", "min-width: 640px;\n", rule.get(0).toString());
        assertEquals("Rule contains max-width declaration", "max-width: 800px;\n", rule.get(1).toString());

    }


}
