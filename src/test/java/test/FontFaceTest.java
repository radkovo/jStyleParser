package test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.RuleFontFace;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.TermFactory;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.*;

public class FontFaceTest {
    private static final Logger log = LoggerFactory.getLogger(FontFaceTest.class);

    public static final TermFactory tf = CSSFactory.getTermFactory();

    public static final String TEST_STRING1 =
            "@font-face {\n" +
                    "  font-family: 'MyWebFont';\n" +
                    "  src: url('myfont.woff2') format('woff2');\n" +
                    "}";
    public static final String TEST_STRING2 =
            "@font-face {\n" +
                    "  font-family: 'MyWebFont';\n" +
                    "  src: url('myfont.woff2') format('woff2'),\n" +
                    "       url('myfont.woff') format('woff'),\n" +
                    "       url('myfont.ttf') format('truetype');\n" +
                    "}";

    public static final String TEST_STRING3 =
            "@font-face {\n" +
                    "  font-family: 'MyWebFont';\n" +
                    "  src: url('webfont.eot'); /* IE9 Compat Modes */\n" +
                    "  src: url('webfont.eot?#iefix') format('embedded-opentype'), /* IE6-IE8 */\n" +
                    "       url('webfont.woff2') format('woff2'), /* Super Modern Browsers */\n" +
                    "       url('webfont.woff') format('woff'), /* Pretty Modern Browsers */\n" +
                    "       url('webfont.ttf')  format('truetype'), /* Safari, Android, iOS */\n" +
                    "       url('webfont.svg#svgFontName') format('svg'); /* Legacy iOS */\n" +
                    "}\n" +
                    "" +
                    "body {\n" +
                    "  font-family: 'MyWebFont', Fallback, sans-serif;\n" +
                    "}";

    @BeforeClass
    public static void init() {
        log.info("\n\n\n == FontFaceTest test at {} == \n\n\n", new Date());
    }


    @Test
    public void testSimpleFF() throws IOException, CSSException {
        log.info("input:\n\n\n" + TEST_STRING1 + "\n\n\n");
        StyleSheet ss;

        ss = CSSFactory.parseString(TEST_STRING1, null);

        assertEquals("One rule is set", 1, ss.size());

        RuleFontFace rule = (RuleFontFace) ss.get(0);
        assertEquals("Rule contains 2 declarations ", 2, rule.size());
        assertEquals("Rule contains font-family declaration", "font-family: 'MyWebFont';\n", rule.get(0).toString());
        assertEquals("Rule contains scr declaration", "src: url('myfont.woff2') format('woff2');\n", rule.get(1).toString());

    }

    @Test
    public void testFFMultiSrc() throws IOException, CSSException {
        log.info("input:\n\n\n" + TEST_STRING2 + "\n\n\n");
        StyleSheet ss;

        ss = CSSFactory.parseString(TEST_STRING2, null);

        assertEquals("One rule is set", 1, ss.size());

        RuleFontFace rule = (RuleFontFace) ss.get(0);
        assertEquals("Rule contains 2 declarations ", 2, rule.size());
        assertEquals("Rule contains font-family declaration", "font-family: 'MyWebFont';\n", rule.get(0).toString());
        assertEquals("Rule contains scr declaration",
                "src: url('myfont.woff2') format('woff2'), url('myfont.woff') format('woff'), url('myfont.ttf') format('truetype');\n",
                rule.get(1).toString());

    }

    @Test
    public void testFFMultiSrc2() throws IOException, CSSException {

        log.info("input:\n\n\n" + TEST_STRING3 + "\n\n\n");
        StyleSheet ss;

        ss = CSSFactory.parseString(TEST_STRING3, null);

        assertEquals("Two rules are set", 2, ss.size());

        RuleFontFace rule = (RuleFontFace) ss.get(0);
        assertEquals("Rule contains 3 declarations ", 3, rule.size());
        assertEquals("Rule contains font-family declaration", "font-family: 'MyWebFont';\n", rule.get(0).toString());
//        assertEquals("Rule contains scr declaration",
//                "src: url('myfont.woff2') format('woff2'), url('myfont.woff') format('woff'), url('myfont.ttf') format('truetype');\n",
//                rule.get(1).toString());

    }
}
