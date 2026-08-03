package test;

import cz.vutbr.web.css.*;
import cz.vutbr.web.csskit.fn.VarImpl;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CSSVariableTest {

    public static final TermFactory tf = CSSFactory.getTermFactory();

    private static final String TEST_SIMPLE_CSS_VARIABLE_DECLARATION = "div { --custom-variable: red; }";

    private static final String TEST_SIMPLE_CSS_VARIABLE_USAGE = "div { background: var(--custom-variable); }";
    private static final String TEST_SIMPLE_CSS_VARIABLE_USAGE_WITH_DEFAULT = "div { background: var(--custom-variable, green); }";
    private static final String TEST_SIMPLE_CSS_VARIABLE_USAGE_WITH_EMPTY_DEFAULT = "div { background: var(--custom-variable,); }";

    @Test
    public void testSimpleDeclaration() throws IOException, CSSException {
        StyleSheet ss = CSSFactory.parseString(TEST_SIMPLE_CSS_VARIABLE_DECLARATION, null);
        Declaration declaration = ((RuleSet) ss.get(0)).get(0);
        String property = declaration.getProperty();
        Term<?> value = declaration.get(0);
        assertEquals("The property is --custom-variable", "--custom-variable", property);
        assertEquals("The value is red ", tf.createColor(tf.createIdent("red")), value);
    }

    @Test
    public void testSimpleVariableUsage() throws IOException, CSSException {
        StyleSheet ss = CSSFactory.parseString(TEST_SIMPLE_CSS_VARIABLE_USAGE, null);
        Declaration declaration = ((RuleSet) ss.get(0)).get(0);
        Term<?> value = declaration.get(0);
        assertEquals("The value is a var term ", VarImpl.class, value.getClass());
        assertEquals("The var property is --custom-variable ", "--custom-variable", ((TermFunction.Var)value).getProperty());
    }


    @Test
    public void testSimpleVariableUsageWithDefault() throws IOException, CSSException {
        StyleSheet ss = CSSFactory.parseString(TEST_SIMPLE_CSS_VARIABLE_USAGE_WITH_DEFAULT, null);
        Declaration declaration = ((RuleSet) ss.get(0)).get(0);
        Term<?> value = declaration.get(0);
        assertEquals("The value is a var term ", VarImpl.class, value.getClass());
        assertEquals("The var property is --custom-variable ", "--custom-variable", ((TermFunction.Var)value).getProperty());
        assertEquals("The default value is green ", tf.createColor(tf.createIdent("green")), ((TermFunction.Var)value).getDefaultTerm());
    }

    @Test
    public void testSimpleVariableUsageWithEmptyDefault() throws IOException, CSSException {
        StyleSheet ss = CSSFactory.parseString(TEST_SIMPLE_CSS_VARIABLE_USAGE_WITH_EMPTY_DEFAULT, null);
        Declaration declaration = ((RuleSet) ss.get(0)).get(0);
        Term<?> value = declaration.get(0);
        assertEquals("The value is a var term ", VarImpl.class, value.getClass());
        assertEquals("The var property is --custom-variable ", "--custom-variable", ((TermFunction.Var)value).getProperty());
        assertTrue("The var property has an empty fallback ", ((TermFunction.Var) value).hasEmptyValueFallback());
    }

}
