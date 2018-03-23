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
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.TermAngle;
import cz.vutbr.web.css.TermCalc;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermFloatValue;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermLengthOrPercent;
import cz.vutbr.web.css.TermNumeric.Unit;
import cz.vutbr.web.css.TermRect;
import cz.vutbr.web.csskit.CalcArgs;

public class FunctionsTest {
	private static final Logger log = LoggerFactory.getLogger(FunctionsTest.class);

	public static final TermFactory tf = CSSFactory.getTermFactory();

	/* parentheses in strings - pair matching test */
	public static final String TEST_DECL1A = " a:after { content: \"(\" attr(href) \")\"; border: 1px solid red; }";
    public static final String TEST_DECL1B = " a:after { content: \"{\" attr(href) \"}\"; border: 1px solid red; }";

    /* function name may start with minus */
    public static final String TEST_DECL2A = " a:after { background-image:-moz-linear-gradient(top,#fff,#ececec); border: 1px solid red; }";
    
    /* function arguments may include minus */
    public static final String TEST_DECL3A = "img { translate: translateY(-.1em) }";
    
    /* different rect() syntaxes */
    public static final String TEST_RECT1 = "p { clip: rect(1px 10em 3rem 2ch); color: red; }";
    public static final String TEST_RECT2 = "p { clip: rect(1px, 10em, 3rem, 2ch); color: red; }";
    public static final String TEST_RECT3 = "p { clip: rect(1px, 10em, 3rem, auto); color: red; }";
    
    /* calc() length expressions (all should evaluate to 60.0) */
    public static final String TEST_CALC_L[];
    static {
        TEST_CALC_L = new String[5];
        TEST_CALC_L[0] = "p { width: calc(60px); color: red; }";
        TEST_CALC_L[1] = "p { width: calc(1em + 0.5em); color: red; }";
        TEST_CALC_L[2] = "p { width: calc(1em + (10% * 2)); color: red; }";
        TEST_CALC_L[3] = "p { width: calc(-3em + 4.5em); color: red; }";
        TEST_CALC_L[4] = "p { width: calc(3em + (-1.5em)); color: red; }";
    }
    
    /* calc() angle expressions (all should evaluate to 33) */
    public static final String TEST_CALC_A[];
    static {
        TEST_CALC_A = new String[1];
        TEST_CALC_A[0] = "p { azimuth: calc(30deg + 6rad); color: red; }";
    }
    
    /* invalid calc() expressions (all width declarations should be skipped) */
    public static final String TEST_CALC_I[];
    static {
        TEST_CALC_I = new String[1];
        TEST_CALC_I[0] = "p { width: calc(30deg + 3em * 2); color: red; }"; //mixed types in expression
    }

    
	@BeforeClass
	public static void init() 
	{
		log.info("\n\n\n == Functions test at {} == \n\n\n", new Date());
	}

	@Test
	public void vendorSpecificUnderscore() throws IOException, CSSException 
	{
		StyleSheet ss = CSSFactory.parseString(TEST_DECL1A, null);
		assertEquals("Two properties are accepted", 2, ss.get(0).size());
		
		final RuleSet rule = (RuleSet) ss.get(0);
        assertEquals("The first property value has three terms", 3, rule.get(0).size());
	}
	
	@Test
	public void vendorSpecificFunctions() throws IOException, CSSException 
	{
		StyleSheet ss = CSSFactory.parseString(TEST_DECL2A, null);
		assertEquals("Two properties are accepted", 2, ss.get(0).size());
		Declaration d = (Declaration) ss.get(0).get(0);
		TermFunction f = (TermFunction) d.get(0);
		char first = f.getFunctionName().charAt(0);
		assertEquals("Function name starts with minus", '-', first);
	}
    
    @Test
	public void negativeArgument() throws IOException, CSSException 
	{
		StyleSheet ss = CSSFactory.parseString(TEST_DECL3A, null);
		assertEquals("One properties is accepted", 1, ss.get(0).size());
		Declaration d = (Declaration) ss.get(0).get(0);
		TermFunction f = (TermFunction) d.get(0);
		assertEquals("The argument is -0.1em", tf.createLength(-0.1f, Unit.em), f.get(0));
	}
	
	@Test
	public void rectFunctions() throws IOException, CSSException
	{
        StyleSheet ss1 = CSSFactory.parseString(TEST_RECT1, null);
        assertEquals("Two properties are accepted", 2, ss1.get(0).size());
        Declaration d1 = (Declaration) ss1.get(0).get(0);
        TermRect r1 = (TermRect) d1.get(0);
        assertEquals("The last one is a correct length", tf.createLength(2f, Unit.ch), r1.getValue().get(3));
	    
        StyleSheet ss2 = CSSFactory.parseString(TEST_RECT2, null);
        assertEquals("Two properties are accepted", 2, ss2.get(0).size());
        Declaration d2 = (Declaration) ss2.get(0).get(0);
        TermRect r2 = (TermRect) d2.get(0);
        assertEquals("The last one is a correct length", tf.createLength(2f, Unit.ch), r2.getValue().get(3));
        
        StyleSheet ss3 = CSSFactory.parseString(TEST_RECT3, null);
        assertEquals("Two properties are accepted", 2, ss3.get(0).size());
        Declaration d3 = (Declaration) ss3.get(0).get(0);
        TermRect r3 = (TermRect) d3.get(0);
        assertEquals("The last one is a correct length", null, r3.getValue().get(3));
	}
	
	@Test
    public void calcLengths() throws IOException, CSSException
    {
	    PxEvaluator eval = new PxEvaluator();
	    for (int i = 0; i < TEST_CALC_L.length; i++)
	    {
	        StyleSheet ss = CSSFactory.parseString(TEST_CALC_L[i], null);
	        assertEquals("Two properties are accepted [" + i + "]", 2, ss.get(0).size());
	        Declaration d = (Declaration) ss.get(0).get(0);
	        TermCalc calc = (TermCalc) d.get(0);
	        Double result = calc.getArgs().evaluate(eval);
            assertEquals("Experssion result is correct [" + i + "]", 60.0, result.doubleValue(), 0.000001);
	    }
    }
	
    @Test
    public void calcAngles() throws IOException, CSSException
    {
        DegEvaluator eval = new DegEvaluator();
        for (int i = 0; i < TEST_CALC_A.length; i++)
        {
            StyleSheet ss = CSSFactory.parseString(TEST_CALC_A[i], null);
            assertEquals("Two properties are accepted [" + i + "]", 2, ss.get(0).size());
            Declaration d = (Declaration) ss.get(0).get(0);
            TermCalc calc = (TermCalc) d.get(0);
            Double result = calc.getArgs().evaluate(eval);
            assertEquals("Experssion result is correct [" + i + "]", 33.0, result.doubleValue(), 0.000001);
        }
    }
    
    @Test
    public void calcInvalid() throws IOException, CSSException
    {
        for (int i = 0; i < TEST_CALC_I.length; i++)
        {
            StyleSheet ss = CSSFactory.parseString(TEST_CALC_I[i], null);
            assertEquals("Only one property is accepted [" + i + "]", 1, ss.get(0).size());
        }
    }
    
	//==========================================================================================
	
    /**
     * A simple testing length evaluator with fixed values for 1em and 100%.
     */
	private class PxEvaluator extends CalcArgs.DoubleEvaluator
    {
        private static final double whole = 100f;
        private static final double em = 40f;

        @Override
        public double resolveValue(TermFloatValue val)
        {
            if (val instanceof TermLengthOrPercent)
            {
                if (((TermLengthOrPercent) val).isPercentage())
                    return val.getValue() * whole / 100.0;
                else
                    switch (val.getUnit())
                    {
                        case px:
                            return val.getValue();
                        case em:
                            return val.getValue() * em;
                        default:
                            return 0.0;
                    }
            }
            else
                return 0.0; //this should not happen
        }
    }

    /**
     * A simple testing angle evaluator with fixed values for 1em and 100%.
     */
    private class DegEvaluator extends CalcArgs.DoubleEvaluator
    {
        private static final double radx = 0.5; //just a testing value, nothing real

        @Override
        public double resolveValue(TermFloatValue val)
        {
            if (val instanceof TermAngle)
            {
                switch (val.getUnit())
                {
                    case deg:
                        return val.getValue();
                    case rad:
                        return val.getValue() * radx;
                    default:
                        return 0.0;
                }
            }
            else
                return 0.0; //this should not happen
        }
    }

}
