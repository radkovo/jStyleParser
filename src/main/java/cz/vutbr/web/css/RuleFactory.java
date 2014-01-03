package cz.vutbr.web.css;

import org.w3c.dom.Element;

import cz.vutbr.web.css.RuleBlock.Priority;
import cz.vutbr.web.css.Selector.Operator;

/**
 * Creates rules, declarations and selectors,
 * that is the most of CSS grammar elements
 * @author kapy
 *
 */
public interface RuleFactory {

	/**
	 * Creates CSS declaration
	 * @return New CSS declaration
	 */
	Declaration createDeclaration();

	/**
	 * Creates CSS declaration by shallow cloning
	 * @param clone Source
	 * @return New CSS declaration
	 */
	Declaration createDeclaration(Declaration clone);

	/**
	 * Creates CSS import rule
	 * @param priority Priority mark
	 * @return New CSS import rule
	 */
	@Deprecated
	RuleImport createImport(Priority priority);

	/**
	 * Creates CSS rule set, that is collection of CSS declarations
	 * with collection of CSS combined selectors. 
	 * 
	 * In current implementation of parser they are used to pass 
	 * integer value by parser to preserve rule ordering according
	 * to their occurrence in CSS style sheet.
	 * 
	 * 
	 * @param priority Priority mark
	 * @return New CSS rule set
	 */
	RuleSet createSet(Priority priority);

	/**
	 * Creates CSS media page
	 * @param priority Priority mark
	 * @return New CSS media page
	 */
	RuleMedia createMedia(Priority priority);

	/**
	 * Creates CSS named page
	 * @param priority Priority mark
	 * @return New CSS page
	 */
	RulePage createPage(Priority priority);
	
	/**
	 * Creates CSS margin rule
	 * @param area Margin area
	 * @param priority Priority mark
	 * @return New CSS margin rule
	 */
	RuleMargin createMargin(String area, Priority priority);

    /**
     * Creates CSS viewport rule.
     * @param priority Priority mark
     * @return New CSS viewport rule
     */
    RuleViewport createViewport(Priority priority);

    /**
     * Creates CSS named font
     * @param priority Priority mark
     * @return New CSS font
     */
    RuleFontFace createFontFace(Priority priority);

	/**
	 * Creates CSS combined selector, collection of (simple) selectors
	 * @return New CSS combined selector
	 */
	CombinedSelector createCombinedSelector();

	/**
	 * Creates CSS selector
	 * @return New CSS selector
	 */
	Selector createSelector();

	/**
	 * Creates CSS selector part, element DOM node
	 * @param e Element node
     * @param inlinePriority true means that the selector has an inline priority
	 * @return New CSS element DOM selector part
	 */
	Selector.ElementDOM createElementDOM(Element e,  boolean inlinePriority);
	
	/**
	 * Creates CSS selector part, element name
	 * @param elementName Name of element
	 * @return New CSS element name selector part
	 */
	Selector.ElementName createElement(String elementName);

	/**
	 * Creates CSS selector part, element attribute
	 * @param value Value of attribute
	 * @param isStringValue Value given is string or identifier
	 * @param operator Operator between value and attribute
	 * @param attribute Name of attribute
	 * @return New CSS element attribute selector part
	 */
	Selector.ElementAttribute createAttribute(String value,
			boolean isStringValue, Operator operator, String attribute);

	/**
	 * Creates CSS selector part, element class
	 * @param className Name of class
	 * @return New CSS element class selector part
	 */
	Selector.ElementClass createClass(String className);

	/**
	 * Creates CSS selector part, element id
	 * @param id ID of element
	 * @return New CSS element ID selector part
	 */
	Selector.ElementID createID(String id);

	/**
	 * Creates CSS selector part, pseudo page
	 * @param pseudo Name of pseudo page
	 * @param functionName Name of additional function or <code>null</code>
	 * @return New CSS pseudo page selector page
	 */
	Selector.PseudoPage createPseudoPage(String pseudo, String functionName);

	/**
	 * Creates CSS author style sheet
	 * @return The new style sheet.
	 */
	StyleSheet createStyleSheet();

	/**
	 * Creates CSS author style sheet with the given origin.
	 * @param origin the origin of the style sheet.
	 * @return The new style sheet.
	 */
	StyleSheet createStyleSheet(StyleSheet.Origin origin);

}
