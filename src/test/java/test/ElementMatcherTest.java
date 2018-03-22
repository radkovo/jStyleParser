package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.ElementMatcher;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.csskit.ElementMatcherSafeCI;
import cz.vutbr.web.csskit.ElementMatcherSafeCS;
import cz.vutbr.web.csskit.ElementMatcherSafeStd;
import cz.vutbr.web.csskit.ElementMatcherSimpleCI;
import cz.vutbr.web.csskit.ElementMatcherSimpleCS;
import cz.vutbr.web.csskit.ElementMatcherSimpleStd;
import cz.vutbr.web.domassign.StyleMap;

public class ElementMatcherTest {
	private static final Logger log = LoggerFactory.getLogger(ElementMatcherTest.class);
	
	private static TermFactory tf = CSSFactory.getTermFactory();

	private static TermColor RR;
    private static TermColor GG;
	
	@BeforeClass
	public static void init() throws SAXException, IOException {
		log.info("\n\n\n == ElementMatcher test at {} == \n\n\n", new Date());
		RR = tf.createColor(255,0,0);
		GG = tf.createColor(0,128,0);
	}
	
    @Test
    public void xhtmlDocument() throws SAXException, IOException {  
        String file = "/case/test_xhtml.xht";
        TermColor[] exXHTML =      { GG, GG, GG, GG };
        TermColor[] exHTMLStd =    { RR, GG, GG, GG };
        TermColor[] exHTMLQuirks = { RR, RR, RR, GG };
        checkMatchersOnFile(file, exXHTML, exHTMLStd, exHTMLQuirks, "XHTML");
    }
    
    @Test
    public void htmlStandardDocument() throws SAXException, IOException {  
        String file = "/case/test_html_std.html";
        TermColor[] exXHTML =      { RR, GG, GG, GG };
        TermColor[] exHTMLStd =    { GG, GG, GG, GG };
        TermColor[] exHTMLQuirks = { GG, RR, RR, GG };
        checkMatchersOnFile(file, exXHTML, exHTMLStd, exHTMLQuirks, "HTMLstd");
    }
    
    @Test
    public void htmlQuirksDocument() throws SAXException, IOException {  
        String file = "/case/test_html_quirks.html";
        TermColor[] exXHTML =      { RR, RR, RR, GG };
        TermColor[] exHTMLStd =    { GG, RR, RR, GG };
        TermColor[] exHTMLQuirks = { GG, GG, GG, GG };
        checkMatchersOnFile(file, exXHTML, exHTMLStd, exHTMLQuirks, "HTMLquirks");
    }
    
    private void checkMatchersOnFile(String file, TermColor[] exXHTML, TermColor[] exHTMLStd, TermColor[] exHTMLQuirks, String msg) throws SAXException, IOException
    {
        checkDocument(file, new ElementMatcherSafeCS(), exXHTML, "XHTML matcher safe/" + msg);
        checkDocument(file, new ElementMatcherSimpleCS(), exXHTML, "XHTML matcher simple/" + msg);
        
        checkDocument(file, new ElementMatcherSafeStd(), exHTMLStd, "HTMLstd matcher safe/" + msg);
        checkDocument(file, new ElementMatcherSimpleStd(), exHTMLStd, "HTMLstd matcher simple/" + msg);
        
        checkDocument(file, new ElementMatcherSafeCI(), exHTMLQuirks, "HTMLquirks matcher safe/" + msg);
        checkDocument(file, new ElementMatcherSimpleCI(), exHTMLQuirks, "HTMLquirks matcher simple/" + msg);
    }
    
    private void checkDocument(String name, ElementMatcher matcher, TermColor[] expect, String msg) throws SAXException, IOException
    {
        DOMSource ds = new DOMSource(getClass().getResourceAsStream(name));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);
        
        CSSFactory.registerElementMatcher(matcher);
        StyleMap decl = CSSFactory.assignDOM(doc, null, getClass().getResource(name),"screen", true);
        
        Element list = elements.getElementById("list");
        NodeList items = list.getElementsByTagName("li");
        for (int i = 0; i < items.getLength(); i++)
        {
            NodeData style = decl.get((Element) items.item(i));
            assertEquals(msg + " : line " + (i+1), expect[i], style.getValue(TermColor.class, "color"));
        }
    }
    
}
