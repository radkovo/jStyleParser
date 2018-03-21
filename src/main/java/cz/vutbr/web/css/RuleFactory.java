package cz.vutbr.web.css;

import org.w3c.dom.Element;

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
	 * @return New CSS import rule
	 */
	@Deprecated
	RuleImport createImport();

	/**
	 * Creates CSS rule set, that is collection of CSS declarations
	 * with collection of CSS combined selectors. 
	 * 
	 * In current implementation of parser they are used to pass 
	 * integer value by parser to preserve rule ordering according
	 * to their occurrence in CSS style sheet.
	 *
	 * @return New CSS rule set
	 */
	RuleSet createSet();

	/**
	 * Creates CSS media page
	 * @return New CSS media page
	 */
	RuleMedia createMedia();

	/**
	 * Creates a CSS media query
	 * @return New CSS media query
	 */
	MediaQuery createMediaQuery();
	
	/**
	 * Creates a new CSS media query expression.
	 * @return The new expression
	 */
	MediaExpression createMediaExpression();
	
	/**
	 * Creates CSS named page
	 * @return New CSS page
	 */
	RulePage createPage();
	
	/**
	 * Creates CSS margin rule
	 * @param area Margin area
	 * @return New CSS margin rule
	 */
	RuleMargin createMargin(String area);

    /**
     * Creates CSS viewport rule.
     * @return New CSS viewport rule
     */
    RuleViewport createViewport();

    /**
     * Creates CSS named font
     * @return New CSS font
     */
    RuleFontFace createFontFace();

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
	 * Creates CSS selector part, page at-rule pseudo-class
	 * @param name Name of pseudo-class
	 * @return New CSS page pseudo-class
	 */
	Selector.PseudoPage createPseudoPage(String name);
    
    /**
	 * Creates CSS selector part, pseudo-element
	 * @param name Name of pseudo-element
	 * @return New CSS pseudo-element
	 */
	Selector.PseudoElement createPseudoElement(String name);
    
    /**
	 * Creates CSS selector part, pseudo-element
	 * @param name Name of pseudo-element
     * @param functionValue Value of its function argument
	 * @return New CSS pseudo-element
	 */
	Selector.PseudoElement createPseudoElement(String name, String functionValue);
    
    /**
	 * Creates CSS selector part, pseudo-element
	 * @param name Name of pseudo-element
     * @param nestedSelector Selector in its function argument
	 * @return New CSS pseudo-element
	 */
	Selector.PseudoElement createPseudoElement(String name, Selector nestedSelector);
    
	/**
	 * Creates CSS selector part, pseudo-class
	 * @param name Name of pseudo-class
	 * @return New CSS pseudo-class
	 */
	Selector.PseudoClass createPseudoClass(String name);
    
    /**
	 * Creates CSS selector part, pseudo-class
	 * @param name Name of pseudo-class
     * @param functionValue Value of its function argument
	 * @return New CSS pseudo-class
	 */
	Selector.PseudoClass createPseudoClass(String name, String functionValue);
    
    /**
	 * Creates CSS selector part, pseudo-class
	 * @param name Name of pseudo-class
     * @param nestedSelector Selector in its function argument
	 * @return New CSS pseudo-class
	 */
	Selector.PseudoClass createPseudoClass(String name, Selector nestedSelector);

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
