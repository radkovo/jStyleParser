package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty.BackgroundSize;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermNumeric.Unit;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.domassign.StyleMap;

/**
 * Tests decoding various complex properties (e.g. background)
 * 
 * @author burgetr
 */
public class DecoderTest
{
    private static final Logger log = LoggerFactory.getLogger(DOMAssignTest.class);
    
    private static TermFactory tf = CSSFactory.getTermFactory();
    
    @BeforeClass
    public static void init() throws SAXException, IOException {
        log.info("\n\n\n == DOMAssign test at {} == \n\n\n", new Date());
    }
    
    @Test
    public void backgrounds() throws SAXException, IOException {    
        
        DOMSource ds = new DOMSource(getClass().getResourceAsStream("/advanced/background.html"));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);
        
        StyleMap decl = CSSFactory.assignDOM(doc, getClass().getResource("/advanced/background.html"), "screen", true);
        
        NodeData data = decl.get(elements.getElementById("bg1"));
        assertNotNull("Data for #bg1 exist", data);
        assertEquals(data.getValue(TermList.class, "background-position").size(), 2);
        assertEquals(stripOperator((TermLength) data.getValue(TermList.class, "background-position").get(0)), tf.createLength(25.0f, Unit.px));
        assertEquals(stripOperator((TermLength) data.getValue(TermList.class, "background-position").get(1)), tf.createLength(25.0f, Unit.px));
        assertEquals(data.getValue(TermList.class, "background-size").size(), 2);
        assertEquals(stripOperator((TermLength) data.getValue(TermList.class, "background-size").get(0)), tf.createLength(1.0f, Unit.px));
        assertEquals(stripOperator((TermPercent) data.getValue(TermList.class, "background-size").get(1)), tf.createPercent(10.0f));
        assertEquals(data.getValue(TermColor.class, "background-color"), tf.createColor(0, 0, 255));
        
        data = decl.get(elements.getElementById("bg2"));
        assertNotNull("Data for #bg2 exist", data);
        assertEquals(data.getValue(TermList.class, "background-position").size(), 2);
        assertEquals(stripOperator((TermPercent) data.getValue(TermList.class, "background-position").get(0)), tf.createPercent(100.0f));
        assertEquals(stripOperator((TermPercent) data.getValue(TermList.class, "background-position").get(1)), tf.createPercent(100.0f));
        assertEquals(data.getProperty("background-size"), BackgroundSize.COVER); 
        assertEquals(data.getValue(TermColor.class, "background-color"), tf.createColor(255, 255, 0));
        
        data = decl.get(elements.getElementById("bg3"));
        assertNotNull("Data for #bg3 exist", data);
        assertEquals(data.getValue(TermList.class, "background-position").size(), 2);
        assertEquals(stripOperator((TermLength) data.getValue(TermList.class, "background-position").get(0)), tf.createLength(10.0f, Unit.pt));
        assertEquals(stripOperator((TermPercent) data.getValue(TermList.class, "background-position").get(1)), tf.createPercent(50.0f));
        assertEquals(data.getValue(TermList.class, "background-size").size(), 2);
        assertEquals(stripOperator((TermLength) data.getValue(TermList.class, "background-size").get(0)), tf.createLength(20.0f, Unit.px));
        assertEquals(data.getValue(TermList.class, "background-size").get(1), tf.createIdent("auto"));
        assertEquals(data.getValue(TermColor.class, "background-color"), tf.createColor(0, 128, 0));
    }
    
    private TermLength stripOperator(TermLength src) {
        src.setOperator(null);
        return src;
    }
    
    private TermPercent stripOperator(TermPercent src) {
        src.setOperator(null);
        return src;
    }
    
}
