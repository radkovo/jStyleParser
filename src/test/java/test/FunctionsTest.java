package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
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
import cz.vutbr.web.css.TermString;
import cz.vutbr.web.csskit.CalcArgs;
import cz.vutbr.web.csskit.fn.AttrImpl;
import cz.vutbr.web.csskit.fn.BlurImpl;
import cz.vutbr.web.csskit.fn.BrightnessImpl;
import cz.vutbr.web.csskit.fn.ContrastImpl;
import cz.vutbr.web.csskit.fn.CounterImpl;
import cz.vutbr.web.csskit.fn.CountersImpl;
import cz.vutbr.web.csskit.fn.DropShadowImpl;
import cz.vutbr.web.csskit.fn.GrayscaleImpl;
import cz.vutbr.web.csskit.fn.HueRotateImpl;
import cz.vutbr.web.csskit.fn.InvertImpl;
import cz.vutbr.web.csskit.fn.LinearGradientImpl;
import cz.vutbr.web.csskit.fn.Matrix3dImpl;
import cz.vutbr.web.csskit.fn.MatrixImpl;
import cz.vutbr.web.csskit.fn.OpacityImpl;
import cz.vutbr.web.csskit.fn.PerspectiveImpl;
import cz.vutbr.web.csskit.fn.RadialGradientImpl;
import cz.vutbr.web.csskit.fn.RepeatingLinearGradientImpl;
import cz.vutbr.web.csskit.fn.Rotate3dImpl;
import cz.vutbr.web.csskit.fn.RotateImpl;
import cz.vutbr.web.csskit.fn.RotateXImpl;
import cz.vutbr.web.csskit.fn.RotateYImpl;
import cz.vutbr.web.csskit.fn.RotateZImpl;
import cz.vutbr.web.csskit.fn.SaturateImpl;
import cz.vutbr.web.csskit.fn.Scale3dImpl;
import cz.vutbr.web.csskit.fn.ScaleImpl;
import cz.vutbr.web.csskit.fn.ScaleXImpl;
import cz.vutbr.web.csskit.fn.ScaleYImpl;
import cz.vutbr.web.csskit.fn.ScaleZImpl;
import cz.vutbr.web.csskit.fn.SepiaImpl;
import cz.vutbr.web.csskit.fn.SkewImpl;
import cz.vutbr.web.csskit.fn.SkewXImpl;
import cz.vutbr.web.csskit.fn.SkewYImpl;
import cz.vutbr.web.csskit.fn.Translate3dImpl;
import cz.vutbr.web.csskit.fn.TranslateImpl;
import cz.vutbr.web.csskit.fn.TranslateXImpl;
import cz.vutbr.web.csskit.fn.TranslateYImpl;
import cz.vutbr.web.csskit.fn.TranslateZImpl;

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
    
    /* valid gradient functions */
    public static final String TEST_GRADIENT[] = new String[] {
        "p { background: linear-gradient(#e66465, #9198e5); }",
        "p { background: linear-gradient(0.25turn, red, #ebf8e1, #f69d3c); }",
        "p { background: linear-gradient(to left, #333, #333 50%, #eee 75%, #333 75%); }",
        "p { background: linear-gradient(to bottom right, #333, #333 50%, #eee); }",
        "p { background: repeating-linear-gradient(#e66465, #9198e5); }",
        "p { background: repeating-linear-gradient(0.25turn, red, #ebf8e1, #f69d3c); }",
        "p { background: repeating-linear-gradient(to left, #333, #333 50%, #eee 75%, #333 75%); }",
        "p { background: repeating-linear-gradient(to bottom right, #333, #333 50%, #eee); }",
        "p { background: radial-gradient(cyan 0%, transparent 20%, salmon 40%); }",
        "p { background: radial-gradient(farthest-corner at 30px 40px, #f35 0%, #43e 100%); }",
        "p { background: radial-gradient(#e66465, #9198e5); }",
        "p { background: radial-gradient(closest-side, #3f87a6, #ebf8e1, #f69d3c); }",
        "p { background: radial-gradient(circle at 100%, #333, #333 50%, #eee 75%, #333 75%); }",
        "p { background: radial-gradient(circle 10px at 100%, #333, #eee); }",
        "p { background: radial-gradient(ellipse 10px 55% at 10px 20px, #333, #eee); }",
        "p { background: radial-gradient(10px at center, #333, #eee); }",
        "p { background: radial-gradient(10% 12% at center, #333, #eee); }",
        "p { background: radial-gradient(ellipse at top, #e66465, transparent), radial-gradient(ellipse at bottom, #4d9f0c, transparent); }",
    };
    
    /* invalid gradient functions; only the float declaration should be accepted */
    public static final String TEST_GRADIENT_INVALID[] = new String[] {
        "p { float: left; background: linear-gradient(12pt, red); }",
        "p { float: left; background: linear-gradient(to top bottom, red, blue); }",
        "p { float: left; background: linear-gradient(top left, red, blue); }",
        "p { float: left; background: linear-gradient(to nowhere, red, blue); }",
        "p { float: left; background: radial-gradient(circle 10% at 100%, #333, #eee); }",
        "p { float: left; background: radial-gradient(circle 10px 20px at 100%, #333, #eee); }",
        "p { float: left; background: radial-gradient(ellipse 10px at 10px 20px, #333, #eee); }",
        "p { float: left; background: radial-gradient(ellipse at 10px 20px 30px, #333, #eee); }",
        "p { float: left; background: radial-gradient(ellipse unknown at top bottom, #333, #eee); }"
    };
    
    /* valid filter functions */
    public static final String TEST_FILTER[] = new String[] {
        "p { filter: blur(1.2em); }",
        "p { filter: brightness(1.75); }",
        "p { filter: contrast(200%); }",
        "p { filter: drop-shadow(30px 10px 4px #4444dd); }",
        "p { filter: drop-shadow(-30px -10px red); }",
        "p { filter: grayscale(1); }",
        "p { filter: hue-rotate(-3.142rad); }",
        "p { filter: invert(0.30); }",
        "p { filter: opacity(0); }",
        "p { filter: saturate(50%); }",
        "p { filter: sepia(1); }",
    };
    
    /* valid content functions */
    public static final String TEST_CONTENT[] = new String[] {
        "p:before { content: attr(title); }",
        "p:before { content: counter(chapter_counter); }",
        "p:before { content: counter(chapter_counter, lower-alpha); }",
        "p:before { content: counters(chapter_counter, '..'); }",
        "p:before { content: counters(chapter_counter, '::', lower-roman); }",
        "p:before { content: counter(c, none) \"z\"; }"
    };
    
    /* invalid content functions */
    public static final String TEST_CONTENT_INVALID[] = new String[] {
        "p:before { font-weight: bold; content: counter(chapter_counter, '.'); }",
        "p:before { font-weight: bold; content: counter(chapter_counter, weird); }",
        "p:before { font-weight: bold; content: counters(chapter_counter); }",
        "p:before { font-weight: bold; content: counters(chapter_counter, '::', unknown); }",
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
                    assertEquals(MatrixImpl.class, fn.getClass());
                    break;
                case 1:
                    assertEquals(Matrix3dImpl.class, fn.getClass());
                    break;
                case 2:
                    assertEquals(PerspectiveImpl.class, fn.getClass());
                    break;
                case 3:
                    assertEquals(PerspectiveImpl.class, fn.getClass());
                    break;
                case 4:
                    assertEquals(RotateImpl.class, fn.getClass());
                    assertEquals("Angle is correct", tf.createAngle("45", Unit.deg, 1), ((RotateImpl) fn).getAngle());
                    break;
                case 5:
                    assertEquals(RotateImpl.class, fn.getClass());
                    assertEquals("Angle is correct", tf.createAngle(0.0f), ((RotateImpl) fn).getAngle());
                    break;
                case 6:
                    assertEquals(Rotate3dImpl.class, fn.getClass());
                    break;
                case 7:
                    assertEquals(Rotate3dImpl.class, fn.getClass());
                    break;
                case 8:
                    assertEquals(RotateXImpl.class, fn.getClass());
                    break;
                case 9:
                    assertEquals(RotateXImpl.class, fn.getClass());
                    break;
                case 10:
                    assertEquals(RotateYImpl.class, fn.getClass());
                    break;
                case 11:
                    assertEquals(RotateYImpl.class, fn.getClass());
                    break;
                case 12:
                    assertEquals(RotateZImpl.class, fn.getClass());
                    break;
                case 13:
                    assertEquals(RotateZImpl.class, fn.getClass());
                    break;
                case 14:
                    assertEquals(ScaleImpl.class, fn.getClass());
                    assertEquals("ScaleX is correct", 1.5f, ((ScaleImpl) fn).getScaleX(), 0.001f);
                    assertEquals("ScaleY is correct", 1.2f, ((ScaleImpl) fn).getScaleY(), 0.001f);
                    break;
                case 15:
                    assertEquals(ScaleImpl.class, fn.getClass());
                    assertEquals("ScaleX is correct", 1.3f, ((ScaleImpl) fn).getScaleX(), 0.001f);
                    assertEquals("ScaleY is correct", 1.3f, ((ScaleImpl) fn).getScaleY(), 0.001f);
                    break;
                case 16:
                    assertEquals(Scale3dImpl.class, fn.getClass());
                    assertEquals("ScaleX is correct", -0.5f, ((Scale3dImpl) fn).getScaleX(), 0.001f);
                    assertEquals("ScaleY is correct", 1f, ((Scale3dImpl) fn).getScaleY(), 0.001f);
                    assertEquals("ScaleZ is correct", 1.7f, ((Scale3dImpl) fn).getScaleZ(), 0.001f);
                    break;
                case 17:
                    assertEquals(ScaleXImpl.class, fn.getClass());
                    assertEquals("Scale is correct", 1.5f, ((ScaleXImpl) fn).getScale(), 0.001f);
                    break;
                case 18:
                    assertEquals(ScaleYImpl.class, fn.getClass());
                    assertEquals("Scale is correct", -1.5f, ((ScaleYImpl) fn).getScale(), 0.001f);
                    break;
                case 19:
                    assertEquals(ScaleZImpl.class, fn.getClass());
                    assertEquals("Scale is correct", 1f, ((ScaleZImpl) fn).getScale(), 0.001f);
                    break;
                case 20:
                    assertEquals(SkewImpl.class, fn.getClass());
                    break;
                case 21:
                    assertEquals(SkewImpl.class, fn.getClass());
                    break;
                case 22:
                    assertEquals(SkewImpl.class, fn.getClass());
                    break;
                case 23:
                    assertEquals(SkewXImpl.class, fn.getClass());
                    break;
                case 24:
                    assertEquals(SkewYImpl.class, fn.getClass());
                    break;
                case 25:
                    assertEquals(TranslateImpl.class, fn.getClass());
                    assertEquals("Length X is correct", tf.createLength("10", Unit.px, 1), ((TranslateImpl) fn).getTranslateX());
                    assertEquals("Length Y is correct", tf.createLength(0.0f), ((TranslateImpl) fn).getTranslateY());
                    break;
                case 26:
                    assertEquals(TranslateImpl.class, fn.getClass());
                    assertEquals("Length X is correct", tf.createLength("10", Unit.px, 1), ((TranslateImpl) fn).getTranslateX());
                    assertEquals("Length Y is correct", tf.createPercent(25.0f), ((TranslateImpl) fn).getTranslateY());
                    break;
                case 27:
                    assertEquals(TranslateImpl.class, fn.getClass());
                    assertEquals("Length X is correct", tf.createInteger(0), ((TranslateImpl) fn).getTranslateX());
                    assertEquals("Length Y is correct", tf.createLength("12", Unit.pt, 1), ((TranslateImpl) fn).getTranslateY());
                    break;
                case 28:
                    assertEquals(Translate3dImpl.class, fn.getClass());
                    break;
                case 29:
                    assertEquals(TranslateXImpl.class, fn.getClass());
                    break;
                case 30:
                    assertEquals(TranslateYImpl.class, fn.getClass());
                    break;
                case 31:
                    assertEquals(TranslateZImpl.class, fn.getClass());
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
    
    @Test
    public void gradientValid() throws IOException, CSSException
    {
        for (int i = 0; i < TEST_GRADIENT.length; i++)
        {
            StyleSheet ss = CSSFactory.parseString(TEST_GRADIENT[i], null);
            //System.out.println(i + " ss: " + ss);
            assertEquals("One rule is parsed [" + i + "]", 1, ss.size());
            assertEquals("One property is set [" + i + "]", 1, ss.get(0).size());
            Declaration d = (Declaration) ss.get(0).get(0);
            TermFunction fn = (TermFunction) d.get(0);
            //System.out.println(i + ": " + d);
            switch (i)
            {
                case 0:
                    assertEquals(LinearGradientImpl.class, fn.getClass());
                    assertNull("Angle is not set", ((LinearGradientImpl) fn).getAngle());
                    assertEquals("Two stops are set", 2, ((LinearGradientImpl) fn).getColorStops().size());
                    assertEquals("First stop color", tf.createColor("#e66465"), ((LinearGradientImpl) fn).getColorStops().get(0).getColor());
                    assertNull("First stop length", ((LinearGradientImpl) fn).getColorStops().get(0).getLength());
                    assertEquals("Second stop color", tf.createColor("#9198e5"), ((LinearGradientImpl) fn).getColorStops().get(1).getColor());
                    assertNull("Second stop length", ((LinearGradientImpl) fn).getColorStops().get(1).getLength());
                    break;
                case 1:
                    assertEquals(LinearGradientImpl.class, fn.getClass());
                    assertEquals("Angle", tf.createAngle("0.25", Unit.turn, 1), ((LinearGradientImpl) fn).getAngle());
                    assertEquals("Three stops are set", 3, ((LinearGradientImpl) fn).getColorStops().size());
                    assertEquals("First stop color", tf.createColor("#ff0000"), ((LinearGradientImpl) fn).getColorStops().get(0).getColor());
                    assertNull("First stop length", ((LinearGradientImpl) fn).getColorStops().get(0).getLength());
                    assertEquals("Last stop color", tf.createColor("#f69d3c"), ((LinearGradientImpl) fn).getColorStops().get(2).getColor());
                    assertNull("Last stop length", ((LinearGradientImpl) fn).getColorStops().get(2).getLength());
                    break;
                case 2:
                    assertEquals(LinearGradientImpl.class, fn.getClass());
                    assertEquals("Angle", tf.createAngle("270", Unit.deg, 1), ((LinearGradientImpl) fn).getAngle());
                    assertEquals("Four stops are set", 4, ((LinearGradientImpl) fn).getColorStops().size());
                    assertEquals("First stop color", tf.createColor("#333333"), ((LinearGradientImpl) fn).getColorStops().get(0).getColor());
                    assertNull("First stop length", ((LinearGradientImpl) fn).getColorStops().get(0).getLength());
                    assertEquals("Last stop color", tf.createColor("#333333"), ((LinearGradientImpl) fn).getColorStops().get(3).getColor());
                    assertEquals("Last stop length", tf.createPercent(75.0f), ((LinearGradientImpl) fn).getColorStops().get(3).getLength());
                    break;
                case 3:
                    assertEquals(LinearGradientImpl.class, fn.getClass());
                    assertEquals("Angle", tf.createAngle("135", Unit.deg, 1), ((LinearGradientImpl) fn).getAngle());
                    assertEquals("Three stops are set", 3, ((LinearGradientImpl) fn).getColorStops().size());
                    assertEquals("First stop color", tf.createColor("#333333"), ((LinearGradientImpl) fn).getColorStops().get(0).getColor());
                    assertNull("First stop length", ((LinearGradientImpl) fn).getColorStops().get(0).getLength());
                    assertEquals("Second stop color", tf.createColor("#333333"), ((LinearGradientImpl) fn).getColorStops().get(1).getColor());
                    assertEquals("Second stop length", tf.createPercent(50.0f), ((LinearGradientImpl) fn).getColorStops().get(1).getLength());
                    break;
                case 4:
                    assertEquals(RepeatingLinearGradientImpl.class, fn.getClass());
                    assertNull("Angle is not set", ((RepeatingLinearGradientImpl) fn).getAngle());
                    assertEquals("Two stops are set", 2, ((RepeatingLinearGradientImpl) fn).getColorStops().size());
                    assertEquals("First stop color", tf.createColor("#e66465"), ((RepeatingLinearGradientImpl) fn).getColorStops().get(0).getColor());
                    assertNull("First stop length", ((RepeatingLinearGradientImpl) fn).getColorStops().get(0).getLength());
                    assertEquals("Second stop color", tf.createColor("#9198e5"), ((RepeatingLinearGradientImpl) fn).getColorStops().get(1).getColor());
                    assertNull("Second stop length", ((RepeatingLinearGradientImpl) fn).getColorStops().get(1).getLength());
                    break;
                case 5:
                    assertEquals(RepeatingLinearGradientImpl.class, fn.getClass());
                    assertEquals("Angle", tf.createAngle("0.25", Unit.turn, 1), ((RepeatingLinearGradientImpl) fn).getAngle());
                    assertEquals("Three stops are set", 3, ((RepeatingLinearGradientImpl) fn).getColorStops().size());
                    assertEquals("First stop color", tf.createColor("#ff0000"), ((RepeatingLinearGradientImpl) fn).getColorStops().get(0).getColor());
                    assertNull("First stop length", ((RepeatingLinearGradientImpl) fn).getColorStops().get(0).getLength());
                    assertEquals("Last stop color", tf.createColor("#f69d3c"), ((RepeatingLinearGradientImpl) fn).getColorStops().get(2).getColor());
                    assertNull("Last stop length", ((RepeatingLinearGradientImpl) fn).getColorStops().get(2).getLength());
                    break;
                case 6:
                    assertEquals(RepeatingLinearGradientImpl.class, fn.getClass());
                    assertEquals("Angle", tf.createAngle("270", Unit.deg, 1), ((RepeatingLinearGradientImpl) fn).getAngle());
                    assertEquals("Four stops are set", 4, ((RepeatingLinearGradientImpl) fn).getColorStops().size());
                    assertEquals("First stop color", tf.createColor("#333333"), ((RepeatingLinearGradientImpl) fn).getColorStops().get(0).getColor());
                    assertNull("First stop length", ((RepeatingLinearGradientImpl) fn).getColorStops().get(0).getLength());
                    assertEquals("Last stop color", tf.createColor("#333333"), ((RepeatingLinearGradientImpl) fn).getColorStops().get(3).getColor());
                    assertEquals("Last stop length", tf.createPercent(75.0f), ((RepeatingLinearGradientImpl) fn).getColorStops().get(3).getLength());
                    break;
                case 7:
                    assertEquals(RepeatingLinearGradientImpl.class, fn.getClass());
                    assertEquals("Angle", tf.createAngle("135", Unit.deg, 1), ((RepeatingLinearGradientImpl) fn).getAngle());
                    assertEquals("Three stops are set", 3, ((RepeatingLinearGradientImpl) fn).getColorStops().size());
                    assertEquals("First stop color", tf.createColor("#333333"), ((RepeatingLinearGradientImpl) fn).getColorStops().get(0).getColor());
                    assertNull("First stop length", ((RepeatingLinearGradientImpl) fn).getColorStops().get(0).getLength());
                    assertEquals("Second stop color", tf.createColor("#333333"), ((RepeatingLinearGradientImpl) fn).getColorStops().get(1).getColor());
                    assertEquals("Second stop length", tf.createPercent(50.0f), ((RepeatingLinearGradientImpl) fn).getColorStops().get(1).getLength());
                    break;
                case 8:
                    assertEquals(RadialGradientImpl.class, fn.getClass());
                    assertNull("No size", ((RadialGradientImpl) fn).getSize());
                    assertEquals("Size ident", tf.createIdent("farthest-corner"), ((RadialGradientImpl) fn).getSizeIdent());
                    assertEquals("Shape is ellipse", tf.createIdent("ellipse"), ((RadialGradientImpl) fn).getShape());
                    assertEquals("Default position", 2, ((RadialGradientImpl) fn).getPosition().length);
                    assertEquals("Three stops are set", 3, ((RadialGradientImpl) fn).getColorStops().size());
                    assertEquals("First stop color", tf.createColor("#00ffff"), ((RadialGradientImpl) fn).getColorStops().get(0).getColor());
                    assertEquals("First stop length", tf.createPercent(0f), ((RadialGradientImpl) fn).getColorStops().get(0).getLength());
                    assertEquals("Second stop color", tf.createColor(tf.createIdent("transparent")), ((RadialGradientImpl) fn).getColorStops().get(1).getColor());
                    assertEquals("Second stop length", tf.createPercent(20.0f), ((RadialGradientImpl) fn).getColorStops().get(1).getLength());
                    break;
                case 9:
                    assertEquals(RadialGradientImpl.class, fn.getClass());
                    assertNull("No size", ((RadialGradientImpl) fn).getSize());
                    assertEquals("Size ident", tf.createIdent("farthest-corner"), ((RadialGradientImpl) fn).getSizeIdent());
                    assertEquals("Shape is ellipse", tf.createIdent("ellipse"), ((RadialGradientImpl) fn).getShape());
                    assertEquals("Position is set", 2, ((RadialGradientImpl) fn).getPosition().length);
                    assertEquals("Position 1 is correct", tf.createLength(30.0f, Unit.px), ((RadialGradientImpl) fn).getPosition()[0]);
                    assertEquals("Position 2 is correct", tf.createLength(40.0f, Unit.px), ((RadialGradientImpl) fn).getPosition()[1]);
                    assertEquals("Two stops are set", 2, ((RadialGradientImpl) fn).getColorStops().size());
                    break;
                case 10:
                    assertEquals(RadialGradientImpl.class, fn.getClass());
                    assertNull("No size", ((RadialGradientImpl) fn).getSize());
                    assertEquals("Size ident", tf.createIdent("farthest-corner"), ((RadialGradientImpl) fn).getSizeIdent());
                    assertEquals("Shape is ellipse", tf.createIdent("ellipse"), ((RadialGradientImpl) fn).getShape());
                    assertEquals("Default position", 2, ((RadialGradientImpl) fn).getPosition().length);
                    assertEquals("Two stops are set", 2, ((RadialGradientImpl) fn).getColorStops().size());
                    assertEquals("First stop color", tf.createColor("#e66465"), ((RadialGradientImpl) fn).getColorStops().get(0).getColor());
                    assertNull("First stop length", ((RadialGradientImpl) fn).getColorStops().get(0).getLength());
                    assertEquals("Second stop color", tf.createColor("#9198e5"), ((RadialGradientImpl) fn).getColorStops().get(1).getColor());
                    assertNull("Second stop length", ((RadialGradientImpl) fn).getColorStops().get(1).getLength());
                    break;
                case 11:
                    assertEquals(RadialGradientImpl.class, fn.getClass());
                    assertNull("No size", ((RadialGradientImpl) fn).getSize());
                    assertEquals("Size ident", tf.createIdent("closest-side"), ((RadialGradientImpl) fn).getSizeIdent());
                    assertEquals("Shape is ellipse", tf.createIdent("ellipse"), ((RadialGradientImpl) fn).getShape());
                    assertEquals("Position is set", 2, ((RadialGradientImpl) fn).getPosition().length);
                    assertEquals("Position 1 is correct", tf.createPercent(50.0f), ((RadialGradientImpl) fn).getPosition()[0]);
                    assertEquals("Position 2 is correct", tf.createPercent(50.0f), ((RadialGradientImpl) fn).getPosition()[1]);
                    assertEquals("Three stops are set", 3, ((RadialGradientImpl) fn).getColorStops().size());
                    break;
                case 12:
                    assertEquals(RadialGradientImpl.class, fn.getClass());
                    assertNull("No size", ((RadialGradientImpl) fn).getSize());
                    assertEquals("Shape is circle", tf.createIdent("circle"), ((RadialGradientImpl) fn).getShape());
                    assertEquals("Position is set", 2, ((RadialGradientImpl) fn).getPosition().length);
                    assertEquals("Position 1 is correct", tf.createPercent(100.0f), ((RadialGradientImpl) fn).getPosition()[0]);
                    assertEquals("Position 2 is correct", tf.createPercent(50.0f), ((RadialGradientImpl) fn).getPosition()[1]);
                    assertEquals("Four stops are set", 4, ((RadialGradientImpl) fn).getColorStops().size());
                    break;
                case 13:
                    assertEquals(RadialGradientImpl.class, fn.getClass());
                    assertEquals("Single size", 1, ((RadialGradientImpl) fn).getSize().length);
                    assertEquals("Correct size", tf.createLength(10.0f, Unit.px), ((RadialGradientImpl) fn).getSize()[0]);
                    assertEquals("Shape is circle", tf.createIdent("circle"), ((RadialGradientImpl) fn).getShape());
                    assertEquals("Position is set", 2, ((RadialGradientImpl) fn).getPosition().length);
                    assertEquals("Position 1 is correct", tf.createPercent(100.0f), ((RadialGradientImpl) fn).getPosition()[0]);
                    assertEquals("Position 2 is correct", tf.createPercent(50.0f), ((RadialGradientImpl) fn).getPosition()[1]);
                    assertEquals("Two stops are set", 2, ((RadialGradientImpl) fn).getColorStops().size());
                    break;
                case 14:
                    assertEquals(RadialGradientImpl.class, fn.getClass());
                    assertEquals("Two sizes", 2, ((RadialGradientImpl) fn).getSize().length);
                    assertEquals("Correct size 1", tf.createLength(10.0f, Unit.px), ((RadialGradientImpl) fn).getSize()[0]);
                    assertEquals("Correct size 2", tf.createPercent(55.0f), ((RadialGradientImpl) fn).getSize()[1]);
                    assertEquals("Shape is ellipse", tf.createIdent("ellipse"), ((RadialGradientImpl) fn).getShape());
                    assertEquals("Position is set", 2, ((RadialGradientImpl) fn).getPosition().length);
                    assertEquals("Position 1 is correct", tf.createLength(10.0f, Unit.px), ((RadialGradientImpl) fn).getPosition()[0]);
                    assertEquals("Position 2 is correct", tf.createLength(20.0f, Unit.px), ((RadialGradientImpl) fn).getPosition()[1]);
                    assertEquals("Two stops are set", 2, ((RadialGradientImpl) fn).getColorStops().size());
                    break;
                case 15:
                    assertEquals(RadialGradientImpl.class, fn.getClass());
                    assertEquals("Single size", 1, ((RadialGradientImpl) fn).getSize().length);
                    assertEquals("Correct size", tf.createLength(10.0f, Unit.px), ((RadialGradientImpl) fn).getSize()[0]);
                    assertEquals("Shape is circle", tf.createIdent("circle"), ((RadialGradientImpl) fn).getShape());
                    assertEquals("Position is set", 2, ((RadialGradientImpl) fn).getPosition().length);
                    assertEquals("Position 1 is correct", tf.createPercent(50.0f), ((RadialGradientImpl) fn).getPosition()[0]);
                    assertEquals("Position 2 is correct", tf.createPercent(50.0f), ((RadialGradientImpl) fn).getPosition()[1]);
                    assertEquals("Two stops are set", 2, ((RadialGradientImpl) fn).getColorStops().size());
                    break;
                case 16:
                    assertEquals(RadialGradientImpl.class, fn.getClass());
                    assertEquals("Two sizes", 2, ((RadialGradientImpl) fn).getSize().length);
                    assertEquals("Correct size 1", tf.createPercent(10.0f), ((RadialGradientImpl) fn).getSize()[0]);
                    assertEquals("Correct size 2", tf.createPercent(12.0f), ((RadialGradientImpl) fn).getSize()[1]);
                    assertEquals("Shape is ellipse", tf.createIdent("ellipse"), ((RadialGradientImpl) fn).getShape());
                    assertEquals("Position is set", 2, ((RadialGradientImpl) fn).getPosition().length);
                    assertEquals("Position 1 is correct", tf.createPercent(50.0f), ((RadialGradientImpl) fn).getPosition()[0]);
                    assertEquals("Position 2 is correct", tf.createPercent(50.0f), ((RadialGradientImpl) fn).getPosition()[1]);
                    assertEquals("Two stops are set", 2, ((RadialGradientImpl) fn).getColorStops().size());
                    break;
                case 17:
                    assertEquals(RadialGradientImpl.class, fn.getClass());
                    TermFunction fn2 = (TermFunction) d.get(1);
                    assertEquals(RadialGradientImpl.class, fn2.getClass());
                    break;
            }
        }
    }
    
    @Test
    public void gradientInvalid() throws IOException, CSSException
    {
        for (int i = 0; i < TEST_GRADIENT_INVALID.length; i++)
        {
            StyleSheet ss = CSSFactory.parseString(TEST_GRADIENT_INVALID[i], null);
            assertEquals("One rule is parset [" + i + "]", 1, ss.size());
            assertEquals("One property is set [" + i + "]", 1, ss.get(0).size());
        }        
    }
    
    @Test
    public void filterValid() throws IOException, CSSException
    {
        for (int i = 0; i < TEST_FILTER.length; i++)
        {
            StyleSheet ss = CSSFactory.parseString(TEST_FILTER[i], null);
            //System.out.println(i + " ss: " + ss);
            assertEquals("One rule is parset [" + i + "]", 1, ss.size());
            assertEquals("One property is set [" + i + "]", 1, ss.get(0).size());
            Declaration d = (Declaration) ss.get(0).get(0);
            TermFunction fn = (TermFunction) d.get(0);
            //System.out.println(i + ": " + d);
            switch (i)
            {
                case 0:
                    assertEquals(BlurImpl.class, fn.getClass());
                    assertEquals("Radius is correct", tf.createLength("1.2", Unit.em, 1), ((BlurImpl) fn).getRadius());
                    break;
                case 1:
                    assertEquals(BrightnessImpl.class, fn.getClass());
                    assertEquals("Amount is correct", 1.75f, ((BrightnessImpl) fn).getAmount(), 0.001f);
                    break;
                case 2:
                    assertEquals(ContrastImpl.class, fn.getClass());
                    assertEquals("Amount is correct", 2.0f, ((ContrastImpl) fn).getAmount(), 0.001f);
                    break;
                case 3:
                    assertEquals(DropShadowImpl.class, fn.getClass());
                    assertEquals("Offset X is correct", tf.createLength("30", Unit.px, 1), ((DropShadowImpl) fn).getOffsetX());
                    assertEquals("Offset Y is correct", tf.createLength("10", Unit.px, 1), ((DropShadowImpl) fn).getOffsetY());
                    assertEquals("Blur radius is correct", tf.createLength("4", Unit.px, 1), ((DropShadowImpl) fn).getBlurRadius());
                    assertEquals("Color is correct", tf.createColor("#4444dd"), ((DropShadowImpl) fn).getColor());
                    break;
                case 4:
                    assertEquals(DropShadowImpl.class, fn.getClass());
                    assertEquals("Offset X is correct", tf.createLength("30", Unit.px, -1), ((DropShadowImpl) fn).getOffsetX());
                    assertEquals("Offset Y is correct", tf.createLength("10", Unit.px, -1), ((DropShadowImpl) fn).getOffsetY());
                    assertNull("Blur radius is enpty", ((DropShadowImpl) fn).getBlurRadius());
                    assertEquals("Color is correct", tf.createColor("#ff0000"), ((DropShadowImpl) fn).getColor());
                    break;
                case 5:
                    assertEquals(GrayscaleImpl.class, fn.getClass());
                    assertEquals("Amount is correct", 1.0f, ((GrayscaleImpl) fn).getAmount(), 0.001f);
                    break;
                case 6:
                    assertEquals(HueRotateImpl.class, fn.getClass());
                    assertEquals("Angle is correct", tf.createAngle("3.142", Unit.rad, -1), ((HueRotateImpl) fn).getAngle());
                    break;
                case 7:
                    assertEquals(InvertImpl.class, fn.getClass());
                    assertEquals("Amount is correct", 0.3f, ((InvertImpl) fn).getAmount(), 0.001f);
                    break;
                case 8:
                    assertEquals(OpacityImpl.class, fn.getClass());
                    assertEquals("Amount is correct", 0.0f, ((OpacityImpl) fn).getAmount(), 0.001f);
                    break;
                case 9:
                    assertEquals(SaturateImpl.class, fn.getClass());
                    assertEquals("Amount is correct", 0.5f, ((SaturateImpl) fn).getAmount(), 0.001f);
                    break;
                case 10:
                    assertEquals(SepiaImpl.class, fn.getClass());
                    assertEquals("Amount is correct", 1.0f, ((SepiaImpl) fn).getAmount(), 0.001f);
                    break;
            }
        }
    }
    
    @Test
    public void contentValid() throws IOException, CSSException
    {
        for (int i = 0; i < TEST_CONTENT.length; i++)
        {
            StyleSheet ss = CSSFactory.parseString(TEST_CONTENT[i], null);
            //System.out.println(i + " ss: " + ss);
            assertEquals("One rule is parset [" + i + "]", 1, ss.size());
            assertEquals("One property is set [" + i + "]", 1, ss.get(0).size());
            Declaration d = (Declaration) ss.get(0).get(0);
            TermFunction fn = (TermFunction) d.get(0);
            //System.out.println(i + ": " + d);
            switch (i)
            {
                case 0:
                    assertEquals(AttrImpl.class, fn.getClass());
                    assertEquals("Name is correct", "title", ((AttrImpl) fn).getName());
                    break;
                case 1:
                    assertEquals(CounterImpl.class, fn.getClass());
                    assertEquals("Name is correct", "chapter_counter", ((CounterImpl) fn).getName());
                    assertNull("Style is not set", ((CounterImpl) fn).getStyle());
                    break;
                case 2:
                    assertEquals(CounterImpl.class, fn.getClass());
                    assertEquals("Name is correct", "chapter_counter", ((CounterImpl) fn).getName());
                    assertEquals("Style is correct", CSSProperty.ListStyleType.LOWER_ALPHA, ((CounterImpl) fn).getStyle()); 
                    break;
                case 3:
                    assertEquals(CountersImpl.class, fn.getClass());
                    assertEquals("Name is correct", "chapter_counter", ((CountersImpl) fn).getName());
                    assertEquals("Separator is correct", "..", ((CountersImpl) fn).getSeparator());
                    assertNull("Style is not set", ((CountersImpl) fn).getStyle());
                    break;
                case 4:
                    assertEquals(CountersImpl.class, fn.getClass());
                    assertEquals("Name is correct", "chapter_counter", ((CountersImpl) fn).getName());
                    assertEquals("Separator is correct", "::", ((CountersImpl) fn).getSeparator());
                    assertEquals("Style is correct", CSSProperty.ListStyleType.LOWER_ROMAN, ((CountersImpl) fn).getStyle()); 
                    break;
                case 5:
                    assertEquals(CounterImpl.class, fn.getClass());
                    assertEquals("Name is correct", "c", ((CounterImpl) fn).getName());
                    assertEquals("Style is correct", CSSProperty.ListStyleType.NONE, ((CounterImpl) fn).getStyle());
                    TermString str = (TermString) d.get(1);
                    assertEquals("String is parsed", "z", str.getValue());
                    break;
            }
        }
    }
    
    @Test
    public void contentInvalid() throws IOException, CSSException
    {
        for (int i = 0; i < TEST_CONTENT_INVALID.length; i++)
        {
            StyleSheet ss = CSSFactory.parseString(TEST_CONTENT_INVALID[i], null);
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
