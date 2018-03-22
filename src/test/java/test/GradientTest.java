package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermNumeric;

public class GradientTest {

    private static final Logger log = LoggerFactory.getLogger(SimpleTest.class);
    public static final TermFactory tf = CSSFactory.getTermFactory();

    // Just colors
    private static final String TEST_LINEAR_GRADIENT_TWO_COLOR = "div { background: linear-gradient(red, blue); }";
    private static final String TEST_LINEAR_GRADIENT_TWO_COLOR_HASH = "div { background: linear-gradient(#FF0033, #3300FF); }";
    private static final String TEST_LINEAR_GRADIENT_TWO_COLOR_RGBA = "div { background: linear-gradient(rgba(255,0,0,0.5), rgba(0,128,255,0.7)); }";
    private static final String TEST_LINEAR_GRADIENT_MULTI_COLOR_3 = "div { background: linear-gradient(red, blue, green); }";
    private static final String TEST_LINEAR_GRADIENT_MULTI_COLOR_4 = "div { background: linear-gradient(red, blue, green, yellow); }";

    // Directions
    private static final String TEST_LINEAR_GRADIENT_LEFT = "div { background: linear-gradient(to left, red, blue); }";
    private static final String TEST_LINEAR_GRADIENT_RIGHT = "div { background: linear-gradient(to right, red, blue); }";
    private static final String TEST_LINEAR_GRADIENT_TOP = "div { background: linear-gradient(to top, red, blue); }";
    private static final String TEST_LINEAR_GRADIENT_BOTTOM = "div { background: linear-gradient(to bottom, red, blue); }";
    private static final String TEST_LINEAR_GRADIENT_TOP_LEFT = "div { background: linear-gradient(to top left, red, blue); }";
    private static final String TEST_LINEAR_GRADIENT_BOTTOM_LEFT = "div { background: linear-gradient(to left bottom, red, blue); }";
    private static final String TEST_LINEAR_GRADIENT_TOP_RIGHT = "div { background: linear-gradient(to right top, red, blue); }";
    private static final String TEST_LINEAR_GRADIENT_BOTTOM_RIGHT = "div { background: linear-gradient(to bottom right, red, blue); }";

    // Angles
    private static final String TEST_LINEAR_GRADIENT_ANGLE_DEG = "div { background: linear-gradient(10deg, red, blue); }";
    private static final String TEST_LINEAR_GRADIENT_ANGLE_RAD = "div { background: linear-gradient(1.3rad, red, blue); }";
    private static final String TEST_LINEAR_GRADIENT_ANGLE_GRAD = "div { background: linear-gradient(671grad, red, blue); }";
    private static final String TEST_LINEAR_GRADIENT_ANGLE_TURN = "div { background: linear-gradient(0.001turn, red, blue); }";
    private static final String TEST_LINEAR_GRADIENT_ANGLE_0 = "div { background: linear-gradient(0, red, blue); }";
    private static final String TEST_LINEAR_GRADIENT_ANGLE_NEGATIVE = "div { background: linear-gradient(-10deg, red, blue); }";

    // Color stops
    private static final String TEST_LINEAR_GRADIENT_STOPS_P1 = "div { background: linear-gradient(red 0%, blue 100%); }";
    private static final String TEST_LINEAR_GRADIENT_STOPS_P2 = "div { background: linear-gradient(red 20%, blue 73%, green 81%); }";
    private static final String TEST_LINEAR_GRADIENT_STOPS_L1 = "div { background: linear-gradient(red 0, blue 10em); }";
    private static final String TEST_LINEAR_GRADIENT_STOPS_L2 = "div { background: linear-gradient(red 10pt, blue 120pt); }";
    private static final String TEST_LINEAR_GRADIENT_STOPS_MIX = "div { background: linear-gradient(red, blue 120pt, yellow 84%); }";
    private static final String TEST_LINEAR_GRADIENT_STOPS_HASH = "div { background: linear-gradient(#00F 10%, #FF3 5em); }";
    private static final String TEST_LINEAR_GRADIENT_STOPS_RGB = "div { background: linear-gradient(rgb(0,0,255) 10%, rgb(255,255,0) 5em); }";

    // Multiple gradients
    private static final String TEST_LINEAR_GRADIENT_MULTIPLE = "div { background: linear-gradient(red, blue), linear-gradient(to right, yellow, green); }";

    // Real-life samples
    private static final String SAMPLE_LINEAR_GRADIENT_1 = "div { background: linear-gradient(to bottom, rgba(245,245,245,1) 0%, rgba(221,221,221,1) 100%); }";

    @Test
    public void testSimpleGradients() throws IOException, CSSException {
        StyleSheet ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_TWO_COLOR, null);
        List<List<Term<?>>> separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 2 comma-separated arguments ", 2, separatedArguments.size());
        assertEquals("The first argument is red ", Arrays.asList(tf.createColor(tf.createIdent("red"))), separatedArguments.get(0));
        assertEquals("The second argument is blue ", Arrays.asList(tf.createColor(tf.createIdent("blue"))), separatedArguments.get(1));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_TWO_COLOR_HASH, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 2 comma-separated arguments ", 2, separatedArguments.size());
        assertEquals("The first argument is #FF0033 ", Arrays.asList(tf.createColor("#FF0033")), separatedArguments.get(0));
        assertEquals("The second argument is #3300FF ", Arrays.asList(tf.createColor("#3300FF")), separatedArguments.get(1));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_TWO_COLOR_RGBA, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 2 comma-separated arguments ", 2, separatedArguments.size());
        assertEquals("The first argument is rgba(255,0,0,0.5) ", Arrays.asList(tf.createColor(255, 0, 0, 128)), separatedArguments.get(0));
        assertEquals("The second argument is rgba(0,128,255,0.7) ", Arrays.asList(tf.createColor(0, 128, 255, 179)), separatedArguments.get(1));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_MULTI_COLOR_3, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is red ", Arrays.asList(tf.createColor(tf.createIdent("red"))), separatedArguments.get(0));
        assertEquals("The second argument is blue ", Arrays.asList(tf.createColor(tf.createIdent("blue"))), separatedArguments.get(1));
        assertEquals("The third argument is green ", Arrays.asList(tf.createColor(tf.createIdent("green"))), separatedArguments.get(2));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_MULTI_COLOR_4, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 4 comma-separated arguments ", 4, separatedArguments.size());
        assertEquals("The first argument is red ", Arrays.asList(tf.createColor(tf.createIdent("red"))), separatedArguments.get(0));
        assertEquals("The second argument is blue ", Arrays.asList(tf.createColor(tf.createIdent("blue"))), separatedArguments.get(1));
        assertEquals("The third argument is green ", Arrays.asList(tf.createColor(tf.createIdent("green"))), separatedArguments.get(2));
        assertEquals("The fourth argument is yellow ", Arrays.asList(tf.createColor(tf.createIdent("yellow"))), separatedArguments.get(3));
    }

    @Test
    public void testGradientDirections() throws IOException, CSSException {
        StyleSheet ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_LEFT, null);
        List<List<Term<?>>> separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is to left ", Arrays.asList(tf.createIdent("to"), tf.createIdent("left")), separatedArguments.get(0));
        assertEquals("The first argument is printed as \"to left\" ", "linear-gradient(to left,", ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).toString().substring(0, "linear-gradient(to left,".length()));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_RIGHT, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is to right ", Arrays.asList(tf.createIdent("to"), tf.createIdent("right")), separatedArguments.get(0));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_TOP, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is to top ", Arrays.asList(tf.createIdent("to"), tf.createIdent("top")), separatedArguments.get(0));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_BOTTOM, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is to bottom ", Arrays.asList(tf.createIdent("to"), tf.createIdent("bottom")), separatedArguments.get(0));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_TOP_LEFT, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is to top left ", Arrays.asList(tf.createIdent("to"), tf.createIdent("top"), tf.createIdent("left")), separatedArguments.get(0));
        assertEquals("The first argument is printed as \"to top left\" ", "linear-gradient(to top left,", ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).toString().substring(0, "linear-gradient(to top left,".length()));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_BOTTOM_LEFT, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is to left bottom ", Arrays.asList(tf.createIdent("to"), tf.createIdent("left"), tf.createIdent("bottom")), separatedArguments.get(0));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_TOP_RIGHT, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is to right top ", Arrays.asList(tf.createIdent("to"), tf.createIdent("right"), tf.createIdent("top")), separatedArguments.get(0));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_BOTTOM_RIGHT, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is to bottom right ", Arrays.asList(tf.createIdent("to"), tf.createIdent("bottom"), tf.createIdent("right")), separatedArguments.get(0));
    }

    @Test
    public void testGradientAngles() throws IOException, CSSException {
        StyleSheet ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_ANGLE_DEG, null);
        List<List<Term<?>>> separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is 10deg ", Arrays.asList(tf.createAngle("10deg", TermNumeric.Unit.deg, 1)), separatedArguments.get(0));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_ANGLE_RAD, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is 1.3rad ", Arrays.asList(tf.createAngle("1.3rad", TermNumeric.Unit.rad, 1)), separatedArguments.get(0));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_ANGLE_GRAD, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is 671grad ", Arrays.asList(tf.createAngle("671grad", TermNumeric.Unit.grad, 1)), separatedArguments.get(0));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_ANGLE_TURN, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is 0.001turn ", Arrays.asList(tf.createAngle("0.001turn", TermNumeric.Unit.turn, 1)), separatedArguments.get(0));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_ANGLE_0, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is 0 ", Arrays.asList(tf.createInteger(0)), separatedArguments.get(0));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_ANGLE_NEGATIVE, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is -10deg ", Arrays.asList(tf.createOperator('-'), tf.createAngle("10deg", TermNumeric.Unit.deg, 1)), separatedArguments.get(0));
    }

    @Test
    public void testGradientColorStops() throws IOException, CSSException {
        StyleSheet ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_STOPS_P1, null);
        List<List<Term<?>>> separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 2 comma-separated arguments ", 2, separatedArguments.size());
        assertEquals("The first argument is red 0% ", Arrays.asList(tf.createColor(tf.createIdent("red")), tf.createPercent(0f)), separatedArguments.get(0));
        assertEquals("The second argument is blue 100% ", Arrays.asList(tf.createColor(tf.createIdent("blue")), tf.createPercent(100f)), separatedArguments.get(1));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_STOPS_P2, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is red 20% ", Arrays.asList(tf.createColor(tf.createIdent("red")), tf.createPercent(20f)), separatedArguments.get(0));
        assertEquals("The second argument is blue 73% ", Arrays.asList(tf.createColor(tf.createIdent("blue")), tf.createPercent(73f)), separatedArguments.get(1));
        assertEquals("The third argument is green 81% ", Arrays.asList(tf.createColor(tf.createIdent("green")), tf.createPercent(81f)), separatedArguments.get(2));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_STOPS_L1, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 2 comma-separated arguments ", 2, separatedArguments.size());
        assertEquals("The first argument is red 0 ", Arrays.asList(tf.createColor(tf.createIdent("red")), tf.createInteger(0)), separatedArguments.get(0));
        assertEquals("The second argument is blue 10em ", Arrays.asList(tf.createColor(tf.createIdent("blue")), tf.createLength(10f, TermNumeric.Unit.em)), separatedArguments.get(1));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_STOPS_L2, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 2 comma-separated arguments ", 2, separatedArguments.size());
        assertEquals("The first argument is red 10pt ", Arrays.asList(tf.createColor(tf.createIdent("red")), tf.createLength(10f, TermNumeric.Unit.pt)), separatedArguments.get(0));
        assertEquals("The second argument is blue 120pt ", Arrays.asList(tf.createColor(tf.createIdent("blue")), tf.createLength(120f, TermNumeric.Unit.pt)), separatedArguments.get(1));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_STOPS_MIX, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is red ", Arrays.asList(tf.createColor(tf.createIdent("red"))), separatedArguments.get(0));
        assertEquals("The second argument is blue 120pt ", Arrays.asList(tf.createColor(tf.createIdent("blue")), tf.createLength(120f, TermNumeric.Unit.pt)), separatedArguments.get(1));
        assertEquals("The third argument is yellow 84% ", Arrays.asList(tf.createColor(tf.createIdent("yellow")), tf.createPercent(84f)), separatedArguments.get(2));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_STOPS_HASH, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 2 comma-separated arguments ", 2, separatedArguments.size());
        assertEquals("The first argument is #00F 10% ", Arrays.asList(tf.createColor("#00F"), tf.createPercent(10f)), separatedArguments.get(0));
        assertEquals("The second argument is #FF3 5em ", Arrays.asList(tf.createColor("#FF3"), tf.createLength(5f, TermNumeric.Unit.em)), separatedArguments.get(1));

        ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_STOPS_RGB, null);
        separatedArguments = ((TermFunction) ((RuleSet) ss.get(0)).get(0).get(0)).getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 2 comma-separated arguments ", 2, separatedArguments.size());
        assertEquals("The first argument is rgb(0,0,255) 10% ", Arrays.asList(tf.createColor(0, 0, 255), tf.createPercent(10f)), separatedArguments.get(0));
        assertEquals("The second argument is rgb(255,255,0) 5em ", Arrays.asList(tf.createColor(255, 255, 0), tf.createLength(5f, TermNumeric.Unit.em)), separatedArguments.get(1));
    }

    @Test
    public void testMultipleGradients() throws IOException, CSSException {
        StyleSheet ss = CSSFactory.parseString(TEST_LINEAR_GRADIENT_MULTIPLE, null);
        assertEquals("One rule is set", 1, ss.size());

        RuleSet rule = (RuleSet) ss.get(0);
        assertArrayEquals("Rule contains one selector div ", SelectorsUtil.createSelectors("div"), rule.getSelectors());
        assertEquals("Rule contains one declaration for background ", "background", rule.get(0).getProperty());
        assertEquals("Rule contains two terms ", 2, rule.get(0).size());

        TermFunction func = (TermFunction) rule.get(0).get(0);
        List<List<Term<?>>> separatedArguments = func.getSeparatedArgs(tf.createOperator(','));
        assertEquals("First function name is linear-gradient ", "linear-gradient", func.getFunctionName());
        assertEquals("First function has 2 comma-separated arguments ", 2, separatedArguments.size());
        assertEquals("The first argument is red ", Arrays.asList(tf.createColor(tf.createIdent("red"))), separatedArguments.get(0));
        assertEquals("The second argument is blue ", Arrays.asList(tf.createColor(tf.createIdent("blue"))), separatedArguments.get(1));

        func = (TermFunction) rule.get(0).get(1);
        separatedArguments = func.getSeparatedArgs(tf.createOperator(','));
        assertEquals("Second function name is linear-gradient ", "linear-gradient", func.getFunctionName());
        assertEquals("Second function has 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is to right ", Arrays.asList(tf.createIdent("to"), tf.createIdent("right")), separatedArguments.get(0));
        assertEquals("The second argument is yellow ", Arrays.asList(tf.createColor(tf.createIdent("yellow"))), separatedArguments.get(1));
        assertEquals("The third argument is green ", Arrays.asList(tf.createColor(tf.createIdent("green"))), separatedArguments.get(2));
    }

    @Test
    public void testSampleGradients() throws IOException, CSSException {
        StyleSheet ss = CSSFactory.parseString(SAMPLE_LINEAR_GRADIENT_1, null);
        assertEquals("One rule is set", 1, ss.size());

        RuleSet rule1 = (RuleSet) ss.get(0);
        assertArrayEquals("Rule contains one selector div ", SelectorsUtil.createSelectors("div"), rule1.getSelectors());
        assertEquals("Rule contains one declaration for background ", "background", rule1.get(0).getProperty());
        assertEquals("Rule contains one term ", 1, rule1.get(0).size());
        assertTrue("Rule contains a TermFunction ", rule1.get(0).get(0) instanceof TermFunction);

        TermFunction func = (TermFunction) rule1.get(0).get(0);
        assertEquals("Function name is linear-gradient ", "linear-gradient", func.getFunctionName());

        List<List<Term<?>>> separatedArguments = func.getSeparatedArgs(tf.createOperator(','));
        assertEquals("There are 3 comma-separated arguments ", 3, separatedArguments.size());
        assertEquals("The first argument is to bottom ", Arrays.asList(tf.createIdent("to"), tf.createIdent("bottom")), separatedArguments.get(0));
        assertEquals("The first argument is pretty-printed as \"to bottom\" ", "linear-gradient(to bottom,", func.toString().substring(0, "linear-gradient(to bottom,".length()));
        assertEquals("The second argument is rgba(245,245,245,1) 0% ", Arrays.asList(tf.createColor(245, 245, 245, 255), tf.createPercent(0f)), separatedArguments.get(1));
        assertEquals("The second argument is rgba(221,221,221,1) 100% ", Arrays.asList(tf.createColor(221, 221, 221, 255), tf.createPercent(100f)), separatedArguments.get(2));
    }
}
