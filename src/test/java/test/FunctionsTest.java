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
import cz.vutbr.web.csskit.TermFunctionImpl;

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
    public static final String TEST_CALC_L[] = new String[] {
        "p { width: calc(60px); color: red; }",
        "p { width: calc(1em + 0.5em); color: red; }",
        "p { width: calc(1em + (10% * 2)); color: red; }",
        "p { width: calc(-3em + 4.5em); color: red; }",
        "p { width: calc(3em + (-1.5em)); color: red; }",
        "p { width: calc(3em - 1.5em); color: red; }"
    };
    
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

    /* valid transform functions */
    public static final String TEST_TRANSFORM[] = new String[] {
        "p { transform: matrix(1, 2, -1, 1.2, 80, 80); }", 
        "p { transform: matrix3d(1.1, 2.2, 0, 4.4, 5.5, -6.6, 7.7, 8.8, 9, 10, -11, 12, 13.0, 14, 15, 16); }", 
        "p { transform: perspective(800px); }", 
        "p { transform: perspective(0); }", 
        "p { transform: rotate(45deg); }", 
        "p { transform: rotate(0); }", 
        "p { transform: rotate3d(1, 1, 1, 45deg); }", 
        "p { transform: rotate3d(2, -1, -1, -0.2turn); }", 
        "p { transform: rotateX(-0.2turn); }", 
        "p { transform: rotateX(0); }", 
        "p { transform: rotateY(-0.2turn); }", 
        "p { transform: rotateY(0); }", 
        "p { transform: rotateZ(-0.2turn); }", 
        "p { transform: rotateZ(0); }", 
        "p { transform: scale(1.5, 1.2); }", 
        "p { transform: scale(1.3); }", 
        "p { transform: scale3d(-0.5, 1, 1.7); }", 
        "p { transform: scaleX(1.5); }", 
        "p { transform: scaleY(-1.5); }", 
        "p { transform: scaleZ(1); }", 
        "p { transform: skew(0); }", 
        "p { transform: skew(15deg, 15deg); }", 
        "p { transform: skew(.312rad); }", 
        "p { transform: skewX(-35deg); }", 
        "p { transform: skewY(0); }", 
        "p { transform: translate(10px); }", 
        "p { transform: translate(10px, 25%); }", 
        "p { transform: translate(0, 12pt); }", 
        "p { transform: translate3d(5ch, -0.4in, 5em); }", 
        "p { transform: translateX(10px); }", 
        "p { transform: translateY(-1.5em); }", 
        "p { transform: translateZ(0); }" 
    };
    
    /* invalid transform functions; only the float declaration should be accepted */
    public static final String TEST_TRANSFORM_INVALID[] = new String[] {
        "p { float: left; transform: scale(1.5, 1.2, 1.1); }",
        "p { float: left; transform: scale(); }"
    };
    
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
    
    @Test
    public void transformValid() throws IOException, CSSException
    {
        for (int i = 0; i < TEST_TRANSFORM.length; i++)
        {
            StyleSheet ss = CSSFactory.parseString(TEST_TRANSFORM[i], null);
            //System.out.println(i + " ss: " + ss);
            assertEquals("One rule is parset [" + i + "]", 1, ss.size());
            assertEquals("One property is set [" + i + "]", 1, ss.get(0).size());
            Declaration d = (Declaration) ss.get(0).get(0);
            TermFunction fn = (TermFunction) d.get(0);
            //System.out.println(i + ": " + d);
            switch (i)
            {
                case 0:
                    assertEquals(TermFunctionImpl.MatrixImpl.class, fn.getClass());
                    break;
                case 1:
                    assertEquals(TermFunctionImpl.Matrix3dImpl.class, fn.getClass());
                    break;
                case 2:
                    assertEquals(TermFunctionImpl.PerspectiveImpl.class, fn.getClass());
                    break;
                case 3:
                    assertEquals(TermFunctionImpl.PerspectiveImpl.class, fn.getClass());
                    break;
                case 4:
                    assertEquals(TermFunctionImpl.RotateImpl.class, fn.getClass());
                    assertEquals("Angle is correct", tf.createAngle("45", Unit.deg, 1), ((TermFunctionImpl.RotateImpl) fn).getAngle());
                    break;
                case 5:
                    assertEquals(TermFunctionImpl.RotateImpl.class, fn.getClass());
                    assertEquals("Angle is correct", tf.createAngle(0.0f), ((TermFunctionImpl.RotateImpl) fn).getAngle());
                    break;
                case 6:
                    assertEquals(TermFunctionImpl.Rotate3dImpl.class, fn.getClass());
                    break;
                case 7:
                    assertEquals(TermFunctionImpl.Rotate3dImpl.class, fn.getClass());
                    break;
                case 8:
                    assertEquals(TermFunctionImpl.RotateXImpl.class, fn.getClass());
                    break;
                case 9:
                    assertEquals(TermFunctionImpl.RotateXImpl.class, fn.getClass());
                    break;
                case 10:
                    assertEquals(TermFunctionImpl.RotateYImpl.class, fn.getClass());
                    break;
                case 11:
                    assertEquals(TermFunctionImpl.RotateYImpl.class, fn.getClass());
                    break;
                case 12:
                    assertEquals(TermFunctionImpl.RotateZImpl.class, fn.getClass());
                    break;
                case 13:
                    assertEquals(TermFunctionImpl.RotateZImpl.class, fn.getClass());
                    break;
                case 14:
                    assertEquals(TermFunctionImpl.ScaleImpl.class, fn.getClass());
                    assertEquals("ScaleX is correct", 1.5f, ((TermFunctionImpl.ScaleImpl) fn).getScaleX(), 0.001f);
                    assertEquals("ScaleY is correct", 1.2f, ((TermFunctionImpl.ScaleImpl) fn).getScaleY(), 0.001f);
                    break;
                case 15:
                    assertEquals(TermFunctionImpl.ScaleImpl.class, fn.getClass());
                    assertEquals("ScaleX is correct", 1.3f, ((TermFunctionImpl.ScaleImpl) fn).getScaleX(), 0.001f);
                    assertEquals("ScaleY is correct", 1.3f, ((TermFunctionImpl.ScaleImpl) fn).getScaleY(), 0.001f);
                    break;
                case 16:
                    assertEquals(TermFunctionImpl.Scale3dImpl.class, fn.getClass());
                    assertEquals("ScaleX is correct", -0.5f, ((TermFunctionImpl.Scale3dImpl) fn).getScaleX(), 0.001f);
                    assertEquals("ScaleY is correct", 1f, ((TermFunctionImpl.Scale3dImpl) fn).getScaleY(), 0.001f);
                    assertEquals("ScaleZ is correct", 1.7f, ((TermFunctionImpl.Scale3dImpl) fn).getScaleZ(), 0.001f);
                    break;
                case 17:
                    assertEquals(TermFunctionImpl.ScaleXImpl.class, fn.getClass());
                    assertEquals("Scale is correct", 1.5f, ((TermFunctionImpl.ScaleXImpl) fn).getScale(), 0.001f);
                    break;
                case 18:
                    assertEquals(TermFunctionImpl.ScaleYImpl.class, fn.getClass());
                    assertEquals("Scale is correct", -1.5f, ((TermFunctionImpl.ScaleYImpl) fn).getScale(), 0.001f);
                    break;
                case 19:
                    assertEquals(TermFunctionImpl.ScaleZImpl.class, fn.getClass());
                    assertEquals("Scale is correct", 1f, ((TermFunctionImpl.ScaleZImpl) fn).getScale(), 0.001f);
                    break;
                case 20:
                    assertEquals(TermFunctionImpl.SkewImpl.class, fn.getClass());
                    break;
                case 21:
                    assertEquals(TermFunctionImpl.SkewImpl.class, fn.getClass());
                    break;
                case 22:
                    assertEquals(TermFunctionImpl.SkewImpl.class, fn.getClass());
                    break;
                case 23:
                    assertEquals(TermFunctionImpl.SkewXImpl.class, fn.getClass());
                    break;
                case 24:
                    assertEquals(TermFunctionImpl.SkewYImpl.class, fn.getClass());
                    break;
                case 25:
                    assertEquals(TermFunctionImpl.TranslateImpl.class, fn.getClass());
                    assertEquals("Length X is correct", tf.createLength("10", Unit.px, 1), ((TermFunctionImpl.TranslateImpl) fn).getTranslateX());
                    assertEquals("Length Y is correct", tf.createLength(0.0f), ((TermFunctionImpl.TranslateImpl) fn).getTranslateY());
                    break;
                case 26:
                    assertEquals(TermFunctionImpl.TranslateImpl.class, fn.getClass());
                    assertEquals("Length X is correct", tf.createLength("10", Unit.px, 1), ((TermFunctionImpl.TranslateImpl) fn).getTranslateX());
                    assertEquals("Length Y is correct", tf.createPercent(25.0f), ((TermFunctionImpl.TranslateImpl) fn).getTranslateY());
                    break;
                case 27:
                    assertEquals(TermFunctionImpl.TranslateImpl.class, fn.getClass());
                    assertEquals("Length X is correct", tf.createInteger(0), ((TermFunctionImpl.TranslateImpl) fn).getTranslateX());
                    assertEquals("Length Y is correct", tf.createLength("12", Unit.pt, 1), ((TermFunctionImpl.TranslateImpl) fn).getTranslateY());
                    break;
                case 28:
                    assertEquals(TermFunctionImpl.Translate3dImpl.class, fn.getClass());
                    break;
                case 29:
                    assertEquals(TermFunctionImpl.TranslateXImpl.class, fn.getClass());
                    break;
                case 30:
                    assertEquals(TermFunctionImpl.TranslateYImpl.class, fn.getClass());
                    break;
                case 31:
                    assertEquals(TermFunctionImpl.TranslateZImpl.class, fn.getClass());
                    break;
            }
        }
    }
    
    @Test
    public void transformInvalid() throws IOException, CSSException
    {
        for (int i = 0; i < TEST_TRANSFORM_INVALID.length; i++)
        {
            StyleSheet ss = CSSFactory.parseString(TEST_TRANSFORM_INVALID[i], null);
            assertEquals("One rule is parset [" + i + "]", 1, ss.size());
            assertEquals("One property is set [" + i + "]", 1, ss.get(0).size());
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
