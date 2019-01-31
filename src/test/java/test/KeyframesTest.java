package test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.RuleKeyframes;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.TermFactory;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.*;

public class KeyframesTest {
    private static final Logger log = LoggerFactory.getLogger(KeyframesTest.class);

    public static final TermFactory tf = CSSFactory.getTermFactory();

    public static final String TEST_STRING1 = FilesUtil.readResource("/simple/animations.css");
            
            
    @BeforeClass
    public static void init() {
        log.info("\n\n\n == FontFaceTest test at {} == \n\n\n", new Date());
    }


    @Test
    public void testSimpleKeyframes() throws IOException, CSSException {
        log.info("input:\n\n\n" + TEST_STRING1 + "\n\n\n");
        StyleSheet ss;

        ss = CSSFactory.parseString(TEST_STRING1, null);

        assertEquals("Two rules are present", 2, ss.size());

        RuleKeyframes rule = (RuleKeyframes) ss.get(0);
        assertEquals("Keyframes name is correct ", "test_anim", rule.getName());
        assertEquals("Rule contains 5 keyframes ", 5, rule.size());
        assertEquals("Selector is correct ", tf.createPercent(0.0f), rule.get(0).getPercentages().get(0));
        assertEquals("Selector is correct ", tf.createPercent(50.0f), rule.get(1).getPercentages().get(0));
        assertEquals("Selector is correct ", tf.createPercent(50.0f), rule.get(2).getPercentages().get(0));
        assertEquals("Selector is correct ", tf.createPercent(60.0f), rule.get(3).getPercentages().get(0));
        assertEquals("Selector is correct ", tf.createPercent(72.0f), rule.get(3).getPercentages().get(1));
        assertEquals("Selector is correct ", tf.createPercent(100.0f), rule.get(4).getPercentages().get(0));
        assertEquals("Second keyframe has two declarations ", 2, rule.get(1).size());
    }

}
