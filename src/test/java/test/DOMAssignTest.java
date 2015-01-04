package test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermNumeric.Unit;
import cz.vutbr.web.domassign.StyleMap;

public class DOMAssignTest {
	private static final Logger log = LoggerFactory.getLogger(DOMAssignTest.class);
	
	private static TermFactory tf = CSSFactory.getTermFactory();
	
	@BeforeClass
	public static void init() throws SAXException, IOException {
		log.info("\n\n\n == DOMAssign test at {} == \n\n\n", new Date());
	}
	
	@Test
	public void basic() throws SAXException, IOException {	
		
        DOMSource ds = new DOMSource(getClass().getResourceAsStream("/advanced/domassign.html"));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);
        
		StyleMap decl = CSSFactory.assignDOM(doc, null, getClass().getResource("/advanced/domassign.html"), "screen", true);
		
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
        
        DOMSource ds = new DOMSource(getClass().getResourceAsStream("/simple/selectors.html"));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);
        
        StyleMap decl = CSSFactory.assignDOM(doc, null, getClass().getResource("/simple/selectors.html"),"screen", true);
        
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
        
        DOMSource ds = new DOMSource(getClass().getResourceAsStream("/simple/selectors2.html"));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);
        
        StyleMap decl = CSSFactory.assignDOM(doc, null, getClass().getResource("/simple/selectors2.html"),"screen", true);
        
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
    
    @Test
    public void combinators() throws SAXException, IOException {  
        
        DOMSource ds = new DOMSource(getClass().getResourceAsStream("/simple/selectors3.html"));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);
        
        StyleMap decl = CSSFactory.assignDOM(doc, null, getClass().getResource("/simple/selectors3.html"),"screen", true);
        
        NodeData i1 = getStyleById(elements, decl, "i1");
        NodeData i2 = getStyleById(elements, decl, "i2");
        NodeData i3 = getStyleById(elements, decl, "i3");
        NodeData i4 = getStyleById(elements, decl, "i4");

        assertThat("Descendant combinator", i1.getValue(TermColor.class, "color"), is(tf.createColor(0,128,0)));
        assertThat("Child combinator", i2.getValue(TermColor.class, "color"), is(tf.createColor(0,128,0)));
        assertThat("Adjacent sibling combinator", i3.getValue(TermColor.class, "color"), is(tf.createColor(0,128,0)));
        assertThat("Generic sibling combinator", i4.getValue(TermColor.class, "color"), is(tf.createColor(0,128,0)));
    }
    
    @Test
    public void inherit() throws SAXException, IOException {  
        DOMSource ds = new DOMSource(getClass().getResourceAsStream("/advanced/inherit.html"));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);
        
        StyleMap decl = CSSFactory.assignDOM(doc, null,
        		getClass().getResource("/advanced/inherit.html"),"screen", true);
        
        NodeData data = decl.get(elements.getElementById("item1"));
        assertNotNull("Data for #item1 exist", data);
        assertThat(data.getValue(TermLength.class, "border-top-width"), is(tf.createLength(1.0f, Unit.px)));
        assertThat(data.getValue(TermColor.class, "border-top-color"), is(tf.createColor(0, 128, 0)));
        assertThat((CSSProperty.BorderStyle) data.getProperty("border-top-style"), is(CSSProperty.BorderStyle.SOLID));
        assertThat(data.getValue(TermLength.class, "margin-top"), is(tf.createLength(1.0f, Unit.em)));
        assertThat(data.getValue(TermLength.class, "margin-bottom"), is(tf.createLength(3.0f, Unit.em)));
        
        data = decl.get(elements.getElementById("item2"));
        assertNotNull("Data for #item2 exist", data);
        assertThat(data.getValue(TermLength.class, "border-top-width"), is(tf.createLength(5.0f, Unit.px)));
        assertThat(data.getValue(TermColor.class, "border-top-color"), is(tf.createColor(0, 128, 0)));
        assertThat((CSSProperty.BorderStyle) data.getProperty("border-top-style"), is(CSSProperty.BorderStyle.DOTTED));
        assertThat(data.getValue(TermLength.class, "margin-top"), is(tf.createLength(1.0f, Unit.em)));
        assertNull(data.getValue(TermLength.class, "margin-bottom"));
        
    }
    
    // Test for issue #11 on GitHub. Respect the specified order of rule-blocks even if the selectors don't match in the same order.
    @Test
    public void respectSpecifiedOrder() throws SAXException, IOException, CSSException {

        final String css =
    	    ".red {color: red}"+
    	    ".blue{color: blue}";
        final String html = "<html><head><style>"
                + css
                + "</style></head><body>"
                + "  <p id='p1' class='red blue'>Lorem Ipsum</p>"
                + "  <p id='p2' class='blue red'>Lorem Ipsum</p>"
                + "</div></body></html> ";

        final InputStream is = new ByteArrayInputStream(html.getBytes());
        final DOMSource ds = new DOMSource(is);
        final Document doc = ds.parse();
        final ElementMap elements = new ElementMap(doc);
        final StyleSheet style = CSSFactory.parseString(css, null);
        StyleMap decl = CSSFactory.assignDOM(doc, null, null, "screen", true);

        // Test p1
        {
          final NodeData nodeData = decl.get(elements.getElementById("p1"));
          assertThat("Color", nodeData.getValue(TermColor.class, "color"), is(tf.createColor(0,0,0xff)));
        }

        // Test p2
        {
          final NodeData nodeData = decl.get(elements.getElementById("p2"));
          assertThat("Color", nodeData.getValue(TermColor.class, "color"), is(tf.createColor(0,0,0xff)));
        }
    }

    private NodeData getStyleById(ElementMap elements, StyleMap decl, String id)
    {
        NodeData data = decl.get(elements.getElementById(id));
        assertNotNull("Data for #" + id + " exists", data);
        return data;
    }
    
}
