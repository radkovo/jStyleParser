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
import cz.vutbr.web.domassign.DirectAnalyzer;
import cz.vutbr.web.domassign.StyleMap;

public class DOMAssignDirectTest {
	private static final Logger log = LoggerFactory.getLogger(DOMAssignDirectTest.class);
	
	private static TermFactory tf = CSSFactory.getTermFactory();
	
	@BeforeClass
	public static void init() throws SAXException, IOException {
		log.info("\n\n\n == DOMAssignDirect test at {} == \n\n\n", new Date());
	}
	
	@Test
	public void basic() throws SAXException, IOException {	
		
        DOMSource ds = new DOMSource(getClass().getResourceAsStream("/advanced/domassign.html"));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);
        
        StyleSheet style = CSSFactory.getUsedStyles(doc, getClass().getResource("/advanced/domassign.html"),"screen");
        DirectAnalyzer da = new DirectAnalyzer(style);
        
		NodeData data = da.getElementStyle(elements.getElementById("bp"), null, "screen");
		assertNotNull("Data for #bp exist", data);
		
		assertThat(data.getValue(TermList.class, "background-position").size(), is(2));
		
		assertThat(data.getValue(TermColor.class, "color"), is(tf.createColor(255, 255, 255)));
		
		data = da.getElementStyle(elements.getElementById("battlecruiser"), null, "screen");
		
		assertThat(tf.createColor(255, 255, 0), is(data.getValue(TermColor.class, "color")));
		
		data = da.getElementStyle(elements.getElementById("border"), null, "screen");
		assertNotNull("Data for #border exist", data);
		assertThat(data.getValue(TermColor.class, "border-bottom-color"),
				is(tf.createColor(255,255,255)));
		
	}
	
    @Test
    public void structureSelectors() throws SAXException, IOException {  
        
        DOMSource ds = new DOMSource(getClass().getResourceAsStream("/simple/selectors.html"));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);
        
        StyleSheet style = CSSFactory.getUsedStyles(doc, getClass().getResource("/simple/selectors.html"),"screen");
        DirectAnalyzer da = new DirectAnalyzer(style);
        
        NodeData i1 = getStyleById(elements, da, "i1");
        NodeData i2 = getStyleById(elements, da, "i2");
        NodeData i5 = getStyleById(elements, da, "i5");
        NodeData i6 = getStyleById(elements, da, "i6");
        NodeData i8 = getStyleById(elements, da, "i8");
        NodeData empty = getStyleById(elements, da, "empty");
        NodeData only = getStyleById(elements, da, "only");

        NodeData html = da.getElementStyle(doc.getDocumentElement(), null, "screen");
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
        
        StyleSheet style = CSSFactory.getUsedStyles(doc, getClass().getResource("/simple/selectors2.html"),"screen");
        DirectAnalyzer da = new DirectAnalyzer(style);
        
        NodeData i1 = getStyleById(elements, da, "i1");
        NodeData i2 = getStyleById(elements, da, "i2");
        NodeData i3 = getStyleById(elements, da, "i3");
        NodeData i5 = getStyleById(elements, da, "i5");
        NodeData i7 = getStyleById(elements, da, "i7");
        NodeData i8 = getStyleById(elements, da, "i8");
        NodeData i9 = getStyleById(elements, da, "i9");

        NodeData html = da.getElementStyle(doc.getDocumentElement(), null, "screen");
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
        
        StyleSheet style = CSSFactory.getUsedStyles(doc, getClass().getResource("/simple/selectors3.html"),"screen");
        DirectAnalyzer da = new DirectAnalyzer(style);
        
        NodeData i1 = getStyleById(elements, da, "i1");
        NodeData i2 = getStyleById(elements, da, "i2");
        NodeData i3 = getStyleById(elements, da, "i3");
        NodeData i4 = getStyleById(elements, da, "i4");

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
        
        StyleMap decl = CSSFactory.assignDOM(doc, getClass().getResource("/advanced/inherit.html"),"screen", true);
        
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
    
    // Test for issue #45
    @Test
    public void lineHeight() throws SAXException, IOException, CSSException {

        final DOMSource ds = new DOMSource(getClass().getResourceAsStream("/simple/line-height.html"));
        final Document doc = ds.parse();
        final ElementMap elements = new ElementMap(doc);
        final StyleSheet style = CSSFactory.getUsedStyles(doc, getClass().getResource("/simple/line-height.html"),"screen");

        final DirectAnalyzer da = new DirectAnalyzer(style);
        final NodeData firstNodeData = da.getElementStyle(elements.getElementById("p1"), null, "screen");
        assertThat(firstNodeData.getValue(TermLength.class, "line-height"), is(tf.createLength(1.0f, Unit.px)));

        final NodeData secondNodeData = da.getElementStyle(elements.getElementById("p1"), null, "screen");
        assertThat(secondNodeData.getValue(TermLength.class, "line-height"), is(tf.createLength(1.0f, Unit.px)));
    }

    // Test for issue #49
    @Test
    public void spaceSeparatedAttributeSelector() throws SAXException, IOException, CSSException {

        final String css = "p { color: green; }"
                         + "[title~='hello world'] { color: red; }";

        final String html = "<html><head><style>"
                + css
                + "</style></head><body>"
                + "<p title='hello world' id='p1'>This should be green color.</p>"
                + "</body></html> ";

        final InputStream is = new ByteArrayInputStream(html.getBytes());
        final DOMSource ds = new DOMSource(is);
        final Document doc = ds.parse();
        final ElementMap elements = new ElementMap(doc);
        final StyleSheet style = CSSFactory.parseString(css, null);

        final DirectAnalyzer da = new DirectAnalyzer(style);
        final NodeData nodeData1 = da.getElementStyle(elements.getElementById("p1"), null, "screen");

        assertThat("Color", nodeData1.getValue(TermColor.class, "color"), is(tf.createColor(0,128,0)));
    }

    // Test for issue #51
    @Test
    public void emptyStringAttributeSelector() throws SAXException, IOException, CSSException {

        final String css = "p { color: green; }"
                         + "p#p1[class$=''] { color: red; }"
                         + "p#p2[class^=''] { color: red; }"
                         + "p#p3[class*=''] { color: red; }";

        final String html = "<html><head><style>"
                + css
                + "</style></head><body>"
                + "<p class='' id='p1'>This should be green color.</p>"
                + "<p class='' id='p2'>This should be green color.</p>"
                + "<p class='' id='p3'>This should be green color.</p>"
                + "</body></html> ";

        final InputStream is = new ByteArrayInputStream(html.getBytes());
        final DOMSource ds = new DOMSource(is);
        final Document doc = ds.parse();
        final ElementMap elements = new ElementMap(doc);
        final StyleSheet style = CSSFactory.parseString(css, null);

        final DirectAnalyzer da = new DirectAnalyzer(style);
        final NodeData nodeData1 = da.getElementStyle(elements.getElementById("p1"), null, "screen");
        final NodeData nodeData2 = da.getElementStyle(elements.getElementById("p2"), null, "screen");
        final NodeData nodeData3 = da.getElementStyle(elements.getElementById("p3"), null, "screen");

        assertThat("Color", nodeData1.getValue(TermColor.class, "color"), is(tf.createColor(0,128,0)));
        assertThat("Color", nodeData2.getValue(TermColor.class, "color"), is(tf.createColor(0,128,0)));
        assertThat("Color", nodeData3.getValue(TermColor.class, "color"), is(tf.createColor(0,128,0)));
    }

    // Test for issue #57
    @Test
    public void initialValueSpecifiedValueConflict() throws SAXException, IOException, CSSException {

        final String css = "p { background: green; } p { background: 'red'; } p { color: white; }";
        final String html = "<html><head><style>"
                + css
                + "</style></head><body><p id='p1'>This should be green color.</p></body></html> ";

        final InputStream is = new ByteArrayInputStream(html.getBytes());
        final DOMSource ds = new DOMSource(is);
        final Document doc = ds.parse();
        final ElementMap elements = new ElementMap(doc);
        final StyleSheet style = CSSFactory.parseString(css, null);

        final DirectAnalyzer da = new DirectAnalyzer(style);
        final NodeData nodeData = da.getElementStyle(elements.getElementById("p1"), null, "screen");
        assertThat("Background color", nodeData.getValue(TermColor.class, "background-color"), is(tf.createColor(0,128,0)));
        assertThat("Color", nodeData.getValue(TermColor.class, "color"), is(tf.createColor(255,255,255)));
    }

    private NodeData getStyleById(ElementMap elements, DirectAnalyzer da, String id)
    {
        NodeData data = da.getElementStyle(elements.getElementById(id), null, "screen");
        assertNotNull("Data for #" + id + " exists", data);
        return data;
    }
    
    // Test for issue #62. The first rule's selector is incomplete, but it shouldn't throw an exception.
    @Test
    public void incompleteNthChild() throws SAXException, IOException, CSSException {

        final String css = "div:nth-child { background: red; }   div {background: green}";
        final String html = "<html><head><style>"
                + css
                + "</style></head><body><div id='d1'><p>Lorem Ipsum</p></div></body></html> ";

        final InputStream is = new ByteArrayInputStream(html.getBytes());
        final DOMSource ds = new DOMSource(is);
        final Document doc = ds.parse();
        final ElementMap elements = new ElementMap(doc);
        final StyleSheet style = CSSFactory.parseString(css, null);

        final DirectAnalyzer da = new DirectAnalyzer(style);
        final NodeData nodeData = da.getElementStyle(elements.getElementById("d1"), null, "screen");
        assertThat("Background color", nodeData.getValue(TermColor.class, "background-color"), is(tf.createColor(0,128,0)));
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

        final DirectAnalyzer da = new DirectAnalyzer(style);

        // Test p1
        {
          final NodeData nodeData = da.getElementStyle(elements.getElementById("p1"), null, "screen");
          assertThat("Color", nodeData.getValue(TermColor.class, "color"), is(tf.createColor(0,0,0xff)));
        }

        // Test p2
        {
          final NodeData nodeData = da.getElementStyle(elements.getElementById("p2"), null, "screen");
          assertThat("Color", nodeData.getValue(TermColor.class, "color"), is(tf.createColor(0,0,0xff)));
        }
    }

}
