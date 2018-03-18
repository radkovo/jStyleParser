package test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.File;
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

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.RuleFactory;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.Selector.PseudoDeclaration;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.csskit.MatchConditionOnElements;
import cz.vutbr.web.domassign.DirectAnalyzer;
import cz.vutbr.web.domassign.StyleMap;
import java.util.List;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PseudoClassTest {
	private static final Logger log = LoggerFactory.getLogger(PseudoClassTest.class);
	
	private static TermFactory tf = CSSFactory.getTermFactory();
    private static final RuleFactory rf = CSSFactory.getRuleFactory();
	
	@BeforeClass
	public static void init() throws SAXException, IOException {
		log.info("\n\n\n == DOMAssign test at {} == \n\n\n", new Date());
	}
	
    @Test
    public void pseudoClassMap() throws SAXException, IOException {  
        
        DOMSource ds = new DOMSource(getClass().getResourceAsStream("/simple/pseudo.html"));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);
        
        MatchConditionOnElements cond = new MatchConditionOnElements("a", PseudoDeclaration.LINK);
        cond.addMatch(elements.getElementById("l2"), PseudoDeclaration.HOVER);
        cond.addMatch(elements.getElementById("l3"), PseudoDeclaration.VISITED);
        cond.addMatch(elements.getElementById("l3"), PseudoDeclaration.HOVER);
        cond.removeMatch(elements.getElementById("l3"), PseudoDeclaration.HOVER);
        CSSFactory.registerDefaultMatchCondition(cond);
        
        StyleMap decl = CSSFactory.assignDOM(doc, null, createBaseFromFilename("data/simple/pseudo.html"),"screen", true);
        
        NodeData l1 = getStyleById(elements, decl, "l1");
        NodeData l2 = getStyleById(elements, decl, "l2");
        NodeData l3 = getStyleById(elements, decl, "l3");
        
        assertThat(l1.getValue(TermColor.class, "color"), is(tf.createColor(0,255,0)));
        assertThat(l2.getValue(TermColor.class, "color"), is(tf.createColor(0,255,255)));
        assertThat(l3.getValue(TermColor.class, "color"), is(tf.createColor(0,0,170)));
    }
    
    @Test
    public void pseudoClassDirect() throws SAXException, IOException {  
        
        DOMSource ds = new DOMSource(getClass().getResourceAsStream("/simple/pseudo.html"));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);
        
        MatchConditionOnElements cond = new MatchConditionOnElements("a", PseudoDeclaration.LINK);
        cond.addMatch(elements.getElementById("l2"), PseudoDeclaration.HOVER);
        cond.addMatch(elements.getElementById("l3"), PseudoDeclaration.VISITED);
        CSSFactory.registerDefaultMatchCondition(cond);
        
        StyleSheet style = CSSFactory.getUsedStyles(doc, null, createBaseFromFilename("data/simple/selectors.html"),"screen");
        DirectAnalyzer da = new DirectAnalyzer(style);

        NodeData l1 = getStyleById(elements, da, "l1");
        NodeData l2 = getStyleById(elements, da, "l2");
        NodeData l3 = getStyleById(elements, da, "l3");
        
        assertThat(l1.getValue(TermColor.class, "color"), is(tf.createColor(0,255,0)));
        assertThat(l2.getValue(TermColor.class, "color"), is(tf.createColor(0,255,255)));
        assertThat(l3.getValue(TermColor.class, "color"), is(tf.createColor(0,0,170)));
    }
    
    @Test
    public void pseudoClassMapNonStatic() throws SAXException, IOException {

        DOMSource ds = new DOMSource(getClass().getResourceAsStream("/simple/pseudo.html"));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);

        MatchConditionOnElements cond = new MatchConditionOnElements("a", PseudoDeclaration.LINK);
        cond.addMatch(elements.getElementById("l2"), PseudoDeclaration.HOVER);
        cond.addMatch(elements.getElementById("l3"), PseudoDeclaration.VISITED);
        cond.addMatch(elements.getElementById("l3"), PseudoDeclaration.HOVER);
        cond.removeMatch(elements.getElementById("l3"), PseudoDeclaration.HOVER);

        StyleMap decl = CSSFactory.assignDOM(doc, null, createBaseFromFilename("data/simple/pseudo.html"),"screen", true, cond);

        NodeData l1 = getStyleById(elements, decl, "l1");
        NodeData l2 = getStyleById(elements, decl, "l2");
        NodeData l3 = getStyleById(elements, decl, "l3");

        assertThat(l1.getValue(TermColor.class, "color"), is(tf.createColor(0,255,0)));
        assertThat(l2.getValue(TermColor.class, "color"), is(tf.createColor(0,255,255)));
        assertThat(l3.getValue(TermColor.class, "color"), is(tf.createColor(0,0,170)));
    }

    @Test
    public void pseudoClassDirectNonStatic() throws SAXException, IOException {

        DOMSource ds = new DOMSource(getClass().getResourceAsStream("/simple/pseudo.html"));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);

        MatchConditionOnElements cond = new MatchConditionOnElements("a", PseudoDeclaration.LINK);
        cond.addMatch(elements.getElementById("l2"), PseudoDeclaration.HOVER);
        cond.addMatch(elements.getElementById("l3"), PseudoDeclaration.VISITED);

        StyleSheet style = CSSFactory.getUsedStyles(doc, null, createBaseFromFilename("data/simple/selectors.html"),"screen");
        DirectAnalyzer da = new DirectAnalyzer(style);
        da.registerMatchCondition(cond);

        NodeData l1 = getStyleById(elements, da, "l1");
        NodeData l2 = getStyleById(elements, da, "l2");
        NodeData l3 = getStyleById(elements, da, "l3");

        assertThat(l1.getValue(TermColor.class, "color"), is(tf.createColor(0,255,0)));
        assertThat(l2.getValue(TermColor.class, "color"), is(tf.createColor(0,255,255)));
        assertThat(l3.getValue(TermColor.class, "color"), is(tf.createColor(0,0,170)));
    }

    // Test for issue #52
    @Test
    public void pseudoClassVsPseudoElementSelector() throws SAXException, IOException {

        DOMSource ds = new DOMSource(getClass().getResourceAsStream("/simple/pseudo.html"));
        Document doc = ds.parse();
        ElementMap elements = new ElementMap(doc);

        StyleSheet style = CSSFactory.getUsedStyles(doc, null, createBaseFromFilename("data/simple/selectors.html"),"screen");
        DirectAnalyzer da = new DirectAnalyzer(style);

        NodeData nodeData = getStyleById(elements, da, "p1");
        assertThat(nodeData.getValue(TermColor.class, "color"), is(tf.createColor(0,128,0)));
    }

    // Test for issue #83
    private static final String TEST_RANGE = 
            "input[type=range]::-webkit-slider-runnable-track { width: 1em; }\n" +
            "input[type=range]::-webkit-slider-thumb { width: 2em; }\n" +
            "input[type=range]::-moz-range-track { width: 3em; }\n" +
            "input[type=range]::-moz-range-thumb { width: 4em; }\n" +
            "input[type=range]::-ms-track { width: 5em; }\n" +
            "input[type=range]::-ms-thumb { width: 6em; }";
    private static final String[] TEST_RANGE_ELEMENTS = new String[] {
            "-webkit-slider-runnable-track",
            "-webkit-slider-thumb",
            "-moz-range-track",
            "-moz-range-thumb",
            "-ms-track",
            "-ms-thumb"
            };
    
    @Test
    public void rangeInputPseudoElements() throws CSSException, IOException {
        
        StyleSheet style = CSSFactory.parseString(TEST_RANGE, null);
        assertEquals("There are 6 rules", 6, style.size());
        
        for (int i = 0; i < TEST_RANGE_ELEMENTS.length; i++) {
            List<CombinedSelector> cslist = SelectorsUtil.appendSimpleSelector(null, 
                "input", 
                null, 
                rf.createAttribute("range", false, Selector.Operator.EQUALS, "type"), 
                rf.createPseudoPage(TEST_RANGE_ELEMENTS[i], null, true));

            RuleSet rule = (RuleSet) style.get(i);
            assertArrayEquals("Rule contains one selector input[type=range]::" + TEST_RANGE_ELEMENTS[i], cslist.toArray(), rule.getSelectors());
            assertEquals("Rule contains one declaration", 1, rule.size());
        }
    }
    
    private NodeData getStyleById(ElementMap elements, StyleMap decl, String id)
    {
        NodeData data = decl.get(elements.getElementById(id));
        assertNotNull("Data for #" + id + " exists", data);
        return data;
    }
    
    private NodeData getStyleById(ElementMap elements, DirectAnalyzer da, String id)
    {
        NodeData data = da.getElementStyle(elements.getElementById(id), null, "screen");
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
