/**
 * Media.java
 *
 * Created on 9. 7. 2014, 10:24:02 by burgetr
 */
package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.MediaQuery;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.StyleSheet;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author burgetr
 */
public class Media
{
    private static Logger log = LoggerFactory.getLogger(Media.class);

    public static final String TEST_SIMPLE_MEDIA = "p { color: red; } @media screen, print { p { color: blue; } } em { color: green; }";
    public static final String TEST_MEDIA_EXPR = "p { color: red; } @media screen AND (min-width: 100px) AND (max-width: 500px), print { p { color: blue; } } em { color: green; }";
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
        StyleSheet ss = CSSFactory.parse(TEST_SIMPLE_MEDIA);
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
        StyleSheet ss = CSSFactory.parse(TEST_MEDIA_EXPR);
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
    public void missingQuery() throws IOException, CSSException 
    {
        StyleSheet ss = CSSFactory.parse(TEST_NO_QUERY);
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
        
        ss = CSSFactory.parse(TEST_MALFORMED1);
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
        
        ss = CSSFactory.parse(TEST_MALFORMED2);
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
        
        ss = CSSFactory.parse(TEST_MALFORMED3);
        assertEquals("There are three rules", 3, ss.size());
        assertEquals("There is one rule in media", 1, ss.get(1).size());
        
        queries = ((RuleMedia) ss.get(1)).getMediaQueries();
        assertEquals("There is one media query", 1, queries.size());
        assertEquals("First of them is NOT all", true, queries.get(0).isNegative());
        assertEquals("First of them is NOT ALL", "all", queries.get(0).getType());
    }
    
    @Test
    @Ignore //should be fixed but not so important now
    public void malformedQueries4() throws IOException, CSSException 
    {
        StyleSheet ss = CSSFactory.parse(TEST_MALFORMED4);
        assertEquals("There are three rules", 3, ss.size());
    }
}
