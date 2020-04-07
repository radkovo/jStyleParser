package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.CSSProperty.BackgroundSize;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermNumeric.Unit;
import cz.vutbr.web.csskit.fn.LinearGradientImpl;
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
        
        StyleMap decl = CSSFactory.assignDOM(doc, null, getClass().getResource("/advanced/background.html"), "screen", true);
        
        NodeData data = decl.get(elements.getElementById("bg1"));
        assertNotNull("Data for #bg1 exist", data);
        assertEquals(data.getValue(TermList.class, "background-position", 0).size(), 2);
        assertEquals(stripOperator((TermLength) data.getValue(TermList.class, "background-position", 0).get(0)), tf.createLength(25.0f, Unit.px));
        assertEquals(stripOperator((TermLength) data.getValue(TermList.class, "background-position", 0).get(1)), tf.createLength(25.0f, Unit.px));
        assertEquals(data.getValue(TermList.class, "background-size", 0).size(), 2);
        assertEquals(stripOperator((TermLength) data.getValue(TermList.class, "background-size", 0).get(0)), tf.createLength(1.0f, Unit.px));
        assertEquals(stripOperator((TermPercent) data.getValue(TermList.class, "background-size", 0).get(1)), tf.createPercent(10.0f));
        assertEquals(data.getValue(TermColor.class, "background-color", 0), tf.createColor(0, 0, 255));
        
        data = decl.get(elements.getElementById("bg2"));
        assertNotNull("Data for #bg2 exist", data);
        assertEquals(data.getValue(TermList.class, "background-position", 0).size(), 2);
        assertEquals(stripOperator((TermPercent) data.getValue(TermList.class, "background-position", 0).get(0)), tf.createPercent(100.0f));
        assertEquals(stripOperator((TermPercent) data.getValue(TermList.class, "background-position", 0).get(1)), tf.createPercent(100.0f));
        assertEquals(data.getProperty("background-size", 0), BackgroundSize.COVER); 
        assertEquals(data.getValue(TermColor.class, "background-color", 0), tf.createColor(255, 255, 0));
        
        data = decl.get(elements.getElementById("bg3"));
        assertNotNull("Data for #bg3 exist", data);
        assertEquals(data.getValue(TermList.class, "background-position", 0).size(), 2);
        assertEquals(stripOperator((TermLength) data.getValue(TermList.class, "background-position", 0).get(0)), tf.createLength(10.0f, Unit.pt));
        assertEquals(stripOperator((TermPercent) data.getValue(TermList.class, "background-position", 0).get(1)), tf.createPercent(50.0f));
        assertEquals(data.getValue(TermList.class, "background-size", 0).size(), 2);
        assertEquals(stripOperator((TermLength) data.getValue(TermList.class, "background-size", 0).get(0)), tf.createLength(20.0f, Unit.px));
        assertEquals(data.getValue(TermList.class, "background-size", 0).get(1), tf.createIdent("auto"));
        assertEquals(data.getValue(TermColor.class, "background-color", 0), tf.createColor(0, 128, 0));
        
        data = decl.get(elements.getElementById("bg4"));
        assertNotNull("Data for #bg4 exist", data);
        assertEquals("background-image is a gradient", CSSProperty.BackgroundImage.gradient, data.getProperty("background-image", 0));
        TermFunction.Gradient fn = data.getValue(TermFunction.Gradient.class, "background-image", 0);
        assertEquals(LinearGradientImpl.class, fn.getClass());
        assertEquals("Angle", tf.createAngle("0.25", Unit.turn, 1), ((LinearGradientImpl) fn).getAngle());
        
        data = decl.get(elements.getElementById("bg5"));
        assertNotNull("Data for #bg5 exist", data);
        assertEquals("background-image is a gradient", CSSProperty.BackgroundImage.gradient, data.getProperty("background-image", 0));
        fn = data.getValue(TermFunction.Gradient.class, "background-image", 0);
        assertEquals(LinearGradientImpl.class, fn.getClass());
        assertEquals("Angle", tf.createAngle("0.25", Unit.turn, 1), ((LinearGradientImpl) fn).getAngle());
        
        data = decl.get(elements.getElementById("bgmulti1"));
        assertNotNull("Data for #bgmulti1 exist", data);
        assertEquals(data.getListSize("background-color", true), 3);
        assertEquals(data.getValue(TermColor.class, "background-color", 0), tf.createColor(255, 0, 0));
        assertEquals(data.getValue(TermColor.class, "background-color", 1), tf.createColor(tf.createIdent("transparent")));
        assertEquals(stripOperator((TermPercent) data.getValue(TermList.class, "background-position", 0).get(0)), tf.createPercent(100.0f));
        assertEquals(stripOperator((TermPercent) data.getValue(TermList.class, "background-position", 0).get(1)), tf.createPercent(100.0f));
        assertEquals(stripOperator((TermPercent) data.getValue(TermList.class, "background-position", 1).get(0)), tf.createPercent(50.0f));
        assertEquals(stripOperator((TermPercent) data.getValue(TermList.class, "background-position", 1).get(1)), tf.createPercent(50.0f));
        assertEquals(stripOperator((TermLength) data.getValue(TermList.class, "background-position", 2).get(0)), tf.createLength(10.0f, Unit.pt));
        assertEquals(stripOperator((TermPercent) data.getValue(TermList.class, "background-position", 2).get(1)), tf.createPercent(50.0f));
    }

    @Test
    public void boxShadow() throws SAXException, IOException {

        DOMSource ds = new DOMSource(getClass().getResourceAsStream("/advanced/boxshadow.html"));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);

        StyleMap decl = CSSFactory.assignDOM(doc, null, getClass().getResource("/advanced/boxshadow.html"), "screen", true);

        Map<String, Integer> elementsToCheck = new LinkedHashMap<>();
        elementsToCheck.put("shadow1", 4);
        elementsToCheck.put("shadow2", 4);
        elementsToCheck.put("shadow3", 5);
        elementsToCheck.put("shadow4", 6);
        elementsToCheck.put("shadow5", 2);
        elementsToCheck.put("shadow_multi1", 8);
        elementsToCheck.put("shadow_multi2", 7);
        elementsToCheck.put("shadow_multi3", 12);

        for (Map.Entry<String, Integer> entry : elementsToCheck.entrySet()) {
            NodeData data = decl.get(elements.getElementById(entry.getKey()));
            assertNotNull("Data for #" + entry.getKey() + " exists", data);
            TermList value = data.getValue(TermList.class, "box-shadow");
            assertNotNull("TermList for #" + entry.getKey() + " exists", value);
            assertEquals("parsed all components of shadow for #" + entry.getKey(), (int)entry.getValue(), value.size());
        }
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
