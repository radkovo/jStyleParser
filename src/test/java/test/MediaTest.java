/**
 * Media.java
 *
 * Created on 9. 7. 2014, 10:24:02 by burgetr
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import cz.vutbr.web.css.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author burgetr
 */
public class MediaTest
{
    private static final Logger log = LoggerFactory.getLogger(MediaTest.class);

    public static final TermFactory tf = CSSFactory.getTermFactory();

    public static final String TEST_SIMPLE_MEDIA = "p { color: red; } @media screen, print { p { color: blue; } } em { color: green; }";
    public static final String TEST_MEDIA_EXPR = "p { color: red; } @media screen AND (min-width: 100px) AND (max-width: 500px), print { p { color: blue; } } em { color: green; }";
    public static final String TEST_MEDIA_EXPR_NO_VALUE = "@media (transform-3d) { p { color: blue; } } em { color: green; }";
    public static final String TEST_MEDIA_EXPR_NO_PROPERTY = "@media (395px) { p { color: blue; } } em { color: green; }";
    public static final String TEST_NO_QUERY = "p { color: red; } @media { p { color: blue; } } em { color: green; }";

    //Malformed media queries from http://www.w3.org/TR/css3-mediaqueries/#error-handling
    public static final String TEST_MALFORMED1 = "p { color: red; } @media (example, all,), speech { p { color: blue; } } em { color: green; }";
    public static final String TEST_MALFORMED2 = "p { color: red; } @media &test, screen { p { color: blue; } } em { color: green; }";
    public static final String TEST_MALFORMED3 = "p { color: red; } @media all and(color) { p { color: blue; } } em { color: green; }";
    public static final String TEST_MALFORMED4 = "p { color: red; } @media test;,all { p { color: blue; } } em { color: green; }";

    @BeforeClass
    public static void init() {
        log.info("\n\n\n == GrammarRecovery1 test at {} == \n\n\n", new Date());
    }

    @Test
    public void simpleMedia() throws IOException, CSSException
    {
        StyleSheet ss = CSSFactory.parseString(TEST_SIMPLE_MEDIA, null);
        assertEquals("There are three rules", 3, ss.size());
        assertEquals("There is one rule in media", 1, ss.get(1).size());

        List<MediaQuery> queries = ((RuleMedia) ss.get(1)).getMediaQueries();
        assertEquals("There are two media queries", 2, queries.size());
        assertEquals("The first media query is for screen", "screen", queries.get(0).getType());
        assertEquals("The second media query is for print", "print", queries.get(1).getType());
    }

    @Test
    public void mediaWithExpressions() throws IOException, CSSException
    {
        StyleSheet ss = CSSFactory.parseString(TEST_MEDIA_EXPR, null);
        assertEquals("There are three rules", 3, ss.size());
        assertEquals("There is one rule in media", 1, ss.get(1).size());

        List<MediaQuery> queries = ((RuleMedia) ss.get(1)).getMediaQueries();
        assertEquals("There are two media queries", 2, queries.size());
        assertEquals("The first media query is for screen", "screen", queries.get(0).getType());
        assertEquals("There are two expressions", 2, queries.get(0).size());
        assertEquals("The first expression is for min-width", "min-width", queries.get(0).get(0).getFeature());
        assertEquals("The first expression has one term", 1, queries.get(0).get(0).size());
    }

    @Test
    public void expressionWithNoValue() throws IOException, CSSException
    {
        StyleSheet ss = CSSFactory.parseString(TEST_MEDIA_EXPR_NO_VALUE, null);
        assertEquals("There are two rules", 2, ss.size());
        assertEquals("There is one rule in media", 1, ss.get(0).size());

        List<MediaQuery> queries = ((RuleMedia) ss.get(0)).getMediaQueries();
        assertEquals("There is one media query", 1, queries.size());
        assertEquals("There is one expression", 1, queries.get(0).size());
        assertEquals("The expression is for transform-3d", "transform-3d", queries.get(0).get(0).getFeature());
        assertEquals("The expression has no terms", 0, queries.get(0).get(0).size());
    }

    @Test
    public void expressionWithNoProperty() throws IOException, CSSException
    {
        StyleSheet ss = CSSFactory.parseString(TEST_MEDIA_EXPR_NO_PROPERTY, null);
        assertEquals("There are two rules", 2, ss.size());
        assertEquals("There is one rule in media", 1, ss.get(0).size());

        List<MediaQuery> queries = ((RuleMedia) ss.get(0)).getMediaQueries();
        assertEquals("There is one media query", 1, queries.size());
        assertEquals("There is no valid media expression", 0, queries.get(0).size());
    }

    @Test
    public void missingQuery() throws IOException, CSSException
    {
        StyleSheet ss = CSSFactory.parseString(TEST_NO_QUERY, null);
        assertEquals("There are three rules", 3, ss.size());
        assertEquals("There is one rule in media", 1, ss.get(1).size());

        List<MediaQuery> queries = ((RuleMedia) ss.get(1)).getMediaQueries();
        assertEquals("There are no media queries", 0, queries.size());
    }

    @Test
    public void malformedQueries1() throws IOException, CSSException
    {
        StyleSheet ss;
        List<MediaQuery> queries;

        ss = CSSFactory.parseString(TEST_MALFORMED1, null);
        assertEquals("There are three rules", 3, ss.size());
        assertEquals("There is one rule in media", 1, ss.get(1).size());

        queries = ((RuleMedia) ss.get(1)).getMediaQueries();
        assertEquals("There are two media queries", 2, queries.size());
        assertEquals("First of them is NOT all", true, queries.get(0).isNegative());
        assertEquals("First of them is NOT ALL", "all", queries.get(0).getType());
    }

    @Test
    public void malformedQueries2() throws IOException, CSSException
    {
        StyleSheet ss;
        List<MediaQuery> queries;

        ss = CSSFactory.parseString(TEST_MALFORMED2, null);
        assertEquals("There are three rules", 3, ss.size());
        assertEquals("There is one rule in media", 1, ss.get(1).size());

        queries = ((RuleMedia) ss.get(1)).getMediaQueries();
        assertEquals("There are two media queries", 2, queries.size());
        assertEquals("First of them is NOT all", true, queries.get(0).isNegative());
        assertEquals("First of them is NOT ALL", "all", queries.get(0).getType());
    }

    @Test
    public void malformedQueries3() throws IOException, CSSException
    {
        StyleSheet ss;
        List<MediaQuery> queries;

        ss = CSSFactory.parseString(TEST_MALFORMED3, null);
        assertEquals("There are three rules", 3, ss.size());
        assertEquals("There is one rule in media", 1, ss.get(1).size());

        queries = ((RuleMedia) ss.get(1)).getMediaQueries();
        assertEquals("There is one media query", 1, queries.size());
        assertEquals("First of them is NOT all", true, queries.get(0).isNegative());
        assertEquals("First of them is NOT ALL", "all", queries.get(0).getType());
    }

    @Test
    public void malformedQueries4() throws IOException, CSSException
    {
        StyleSheet ss = CSSFactory.parseString(TEST_MALFORMED4, null);
        assertEquals("There are two rules", 2, ss.size());
        RuleSet rule = (RuleSet) ss.get(0);
        assertEquals("First rule contains one declaration { color: red;}",
                DeclarationsUtil.appendDeclaration(null, "color", tf
                        .createColor(255, 0, 0)), rule.asList());
        rule = (RuleSet) ss.get(1);

        assertEquals("Second rule contains one declaration { color: red;}",
                DeclarationsUtil.appendDeclaration(null, "color", tf
                        .createColor(0,0x80, 0)), rule.asList());
    }

    @Test
    public void matchesEmpty()
    {
        assertTrue("MediaSpecAll matches the empty media query", (new MediaSpecAll()).matchesEmpty());
        assertFalse("MediaSpecNone does not match the empty media query", (new MediaSpecNone()).matchesEmpty());
    }
}
