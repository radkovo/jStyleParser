/**
 * SimpleAnalyzer.java
 *
 * Created on 6.6.2012, 13:57:00 by burgetr
 */
package cz.vutbr.web.domassign;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import cz.vutbr.web.css.MediaSpec;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.Selector.PseudoElementType;
import cz.vutbr.web.css.StyleSheet;

/**
 * A simple ananalyzer that computes a style for the individual DOM nodes with no mapping and caching.
 * This analyzer is suitable for obtaining the style of individual elements without computing the style
 * for the whole DOM tree. However, in larger scale, the performance of the individual computation
 * is significantly worse.  
 * 
 * @author burgetr
 */
public class DirectAnalyzer extends Analyzer
{
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(DirectAnalyzer.class);

    /**
     * Creates the analyzer for a single style sheet.
     * @param sheet The stylesheet that will be used as the source of rules.
     */
    public DirectAnalyzer(StyleSheet sheet)
    {
        super(sheet);
    }

    /**
     * Creates the analyzer for multiple style sheets.
     * @param sheets A list of stylesheets that will be used as the source of rules.
     */
    public DirectAnalyzer(List<StyleSheet> sheets)
    {
        super(sheets);
    }

    /**
     * Computes the style of an element with an eventual pseudo element for the given media.
     * @param el The DOM element.
     * @param pseudo A pseudo element that should be used for style computation or <code>null</code> if no pseudo element should be used (e.g. :after).
     * @param media Used media specification.
     * @return The relevant declarations from the registered style sheets.
     */
    public NodeData getElementStyle(Element el, PseudoElementType pseudo, MediaSpec media)
    {
        final OrderedRule[] applicableRules = AnalyzerUtil.getApplicableRules(sheets, el, media);
        return AnalyzerUtil.getElementStyle(el, pseudo, getElementMatcher(), getMatchCondition(), applicableRules);
    }

    /**
     * Computes the style of an element with an eventual pseudo element for the given media.
     * @param el The DOM element.
     * @param pseudo A pseudo element that should be used for style computation or <code>null</code> if no pseudo element should be used (e.g. :after).
     * @param media Used media name (e.g. "screen" or "all")
     * @return The relevant declarations from the registered style sheets.
     */
    public NodeData getElementStyle(Element el, PseudoElementType pseudo, String media)
    {
        return getElementStyle(el, pseudo, new MediaSpec(media));
    }
    
    //==========================================================================================

}
