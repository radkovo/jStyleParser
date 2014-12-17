/**
 * DOMAssignMedia.java
 *
 * Created on 10. 7. 2014, 10:47:41 by burgetr
 */
package test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.Date;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.MediaSpec;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.domassign.StyleMap;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * 
 * @author burgetr
 */
public class DOMAssignMediaTest {
    private static final Logger log = LoggerFactory.getLogger(DOMAssignMediaTest.class);
    
    private static TermFactory tf = CSSFactory.getTermFactory();
    
    @BeforeClass
    public static void init() throws SAXException, IOException {
        log.info("\n\n\n == DOMAssign test at {} == \n\n\n", new Date());
    }
    
    @Test
    public void queryBasic() throws SAXException, IOException {  

        NodeData data;
        data = evaluateForMedia(new MediaSpec("screen"));
        assertNotNull("Data for #test exist", data);
        assertThat(data.getValue(TermColor.class, "color"), is(tf.createColor(0, 0, 255)));
        
    }
    
    @Test
    public void queryAlternatives() throws SAXException, IOException {  

        NodeData data;
        data = evaluateForMedia(new MediaSpec("projection"));
        assertNotNull("Data for #test exist", data);
        assertThat(data.getValue(TermColor.class, "color"), is(tf.createColor(0, 0, 255)));
        
    }
    
    @Test
    public void queryInvalidAndAlternative() throws SAXException, IOException {  

        NodeData data;
        data = evaluateForMedia(new MediaSpec("print"));
        assertNotNull("Data for #test exist", data);
        assertThat(data.getValue(TermColor.class, "color"), is(tf.createColor(0, 128, 0)));
        
    }
    
    @Test
    public void queryNoMatch() throws SAXException, IOException {  

        NodeData data;
        data = evaluateForMedia(new MediaSpec("speech"));
        assertNotNull("Data for #test exist", data);
        assertThat(data.getValue(TermColor.class, "color"), is(tf.createColor(255, 0, 0)));
        
    }
    
    //================================================================================================
    
    private NodeData evaluateForMedia(MediaSpec spec) throws SAXException, IOException
    {
        DOMSource ds = new DOMSource(getClass().getResourceAsStream("/media/media1.html"));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);
        
        StyleMap decl = CSSFactory.assignDOM(doc, null, getClass().getResource("/media/media1.html"), spec, true);
        NodeData data = decl.get(elements.getElementById("test"));
        
        return data;
    }
}
