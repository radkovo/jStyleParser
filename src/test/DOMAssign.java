package test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.domassign.StyleMap;

public class DOMAssign {
	private static final Logger log = LoggerFactory.getLogger(DOMAssign.class);
	
	private static TermFactory tf = CSSFactory.getTermFactory();
	
	@BeforeClass
	public static void init() throws SAXException, IOException {
		log.info("\n\n\n == DOMAssign test at {} == \n\n\n", new Date());
	}
	
	@Test
	public void basic() throws SAXException, IOException {	
		
        DOMSource ds = new DOMSource(new FileInputStream("data/advanced/domassign.html"));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);
        
		StyleMap decl = CSSFactory.assignDOM(doc, null,
				createBaseFromFilename("data/advanced/domassign.html"),"screen", true);
		
		NodeData data = decl.get(elements.getElementById("bp"));
		assertNotNull("Data for #bp exist", data);
		
		assertThat(data.getValue(TermList.class, "background-position").size(), is(2));
		
		assertThat(data.getValue(TermColor.class, "color"), is(tf.createColor(255, 255, 255)));
		
		data = decl.get(elements.getElementById("battlecruiser"));
		
		assertThat(tf.createColor(255, 255, 0), is(data.getValue(TermColor.class, "color")));
		
		data = decl.get(elements.getElementById("border"));
		assertNotNull("Data for #border exist", data);
		assertThat(data.getValue(TermColor.class, "border-bottom-color"),
				is(tf.createColor(255,255,255)));
		
	}
	
    @Test
    public void structureSelectors() throws SAXException, IOException {  
        
        DOMSource ds = new DOMSource(new FileInputStream("data/simple/selectors.html"));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);
        
        StyleMap decl = CSSFactory.assignDOM(doc, null, createBaseFromFilename("data/simple/selectors.html"),"screen", true);
        
        NodeData i1 = getStyleById(elements, decl, "i1");
        NodeData i2 = getStyleById(elements, decl, "i2");
        NodeData i5 = getStyleById(elements, decl, "i5");
        NodeData i6 = getStyleById(elements, decl, "i6");
        NodeData i8 = getStyleById(elements, decl, "i8");
        NodeData empty = getStyleById(elements, decl, "empty");
        NodeData only = getStyleById(elements, decl, "only");

        NodeData html = decl.get(doc.getDocumentElement());
        assertNotNull("Data for <html> exists", html);
        
        assertThat(i1.getValue(TermColor.class, "color"), is(tf.createColor(255,0,0)));
        assertNull(i2.getValue(TermColor.class, "color"));
        assertThat(i5.getValue(TermColor.class, "background-color"), is(tf.createColor(255,0,0)));
        assertThat(i6.getValue(TermColor.class, "background-color"), is(tf.createColor(0,128,0)));
        assertThat(i8.getValue(TermColor.class, "border-top-color"), is(tf.createColor(0,0,255)));
        assertThat(only.getValue(TermColor.class, "border-top-color"), is(tf.createColor(0,0,255)));
        assertThat(only.getValue(TermColor.class, "background-color"), is(tf.createColor(0,255,255)));
        assertThat(empty.getValue(TermColor.class, "border-top-color"), is(tf.createColor(255,0,0)));
        assertThat(html.getValue(TermColor.class, "background-color"), is(tf.createColor(238,238,238)));
        
    }
    
    @Test
    public void indexSelectors() throws SAXException, IOException {  
        
        DOMSource ds = new DOMSource(new FileInputStream("data/simple/selectors2.html"));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);
        
        StyleMap decl = CSSFactory.assignDOM(doc, null, createBaseFromFilename("data/simple/selectors2.html"),"screen", true);
        
        NodeData i1 = getStyleById(elements, decl, "i1");
        NodeData i2 = getStyleById(elements, decl, "i2");
        NodeData i3 = getStyleById(elements, decl, "i3");
        NodeData i5 = getStyleById(elements, decl, "i5");
        NodeData i7 = getStyleById(elements, decl, "i7");
        NodeData i8 = getStyleById(elements, decl, "i8");
        NodeData i9 = getStyleById(elements, decl, "i9");

        NodeData html = decl.get(doc.getDocumentElement());
        assertNotNull("Data for <html> exists", html);
        
        assertNull(i1.getValue(TermColor.class, "color"));
        assertNull(i1.getValue(TermColor.class, "border-top-color"));
        assertNull(i1.getValue(TermColor.class, "background-color"));
        assertNull(i7.getValue(TermColor.class, "color"));
        assertNull(i7.getValue(TermColor.class, "border-top-color"));
        assertNull(i7.getValue(TermColor.class, "background-color"));
        
        assertThat(i2.getValue(TermColor.class, "border-top-color"), is(tf.createColor(0,128,0)));
        assertThat(i5.getValue(TermColor.class, "border-top-color"), is(tf.createColor(0,128,0)));
        assertThat(i8.getValue(TermColor.class, "border-top-color"), is(tf.createColor(0,128,0)));
        
        assertThat(i8.getValue(TermColor.class, "color"), is(tf.createColor(255,0,0)));
        
        assertThat(i3.getValue(TermColor.class, "background-color"), is(tf.createColor(238,238,238)));
        assertThat(i9.getValue(TermColor.class, "background-color"), is(tf.createColor(238,238,238)));
        assertThat(i5.getValue(TermColor.class, "background-color"), is(tf.createColor(170,170,255)));
        
    }
    
    private NodeData getStyleById(ElementMap elements, StyleMap decl, String id)
    {
        NodeData data = decl.get(elements.getElementById(id));
        assertNotNull("Data for #" + id + " exists", data);
        return data;
    }
    
	private static URL createBaseFromFilename(String filename) {
		try {
			File f = new File(filename);
			return f.toURI().toURL();
		} catch (MalformedURLException e) {
			return null;
		}
	}
}
