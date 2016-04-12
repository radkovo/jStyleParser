package test;

import com.sun.javafx.css.*;
import cz.vutbr.web.css.*;
import cz.vutbr.web.css.Declaration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class FontFaceTest {
    private static final Logger log = LoggerFactory.getLogger(FontFaceTest.class);

    public static final TermFactory tf = CSSFactory.getTermFactory();

    public static final String TEST_STRING1 =
            "@font-face {\n" +
                    "  font-family: 'MyWebFont';\n" +
                    "  src: url('myfont.woff2') format('woff2');\n" +
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

}
