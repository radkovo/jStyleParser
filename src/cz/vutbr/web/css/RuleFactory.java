package cz.vutbr.web.css;

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
	 * @param position Position in CSS style sheet
	 * @return New CSS import rule
	 */
	RuleImport createImport(int position);

	/**
	 * Creates CSS rule set, that is collection of CSS declarations
	 * with collection of CSS combined selectors. 
	 * 
	 * In current implementation of parser they are used to pass 
	 * integer value by parser to preserve rule ordering according
	 * to their occurrence in CSS style sheet.
	 * 
	 * 
	 * @param position Position in CSS style sheet
	 * @return New CSS rule set
	 */
	RuleSet createSet(int position);

	/**
	 * Creates CSS media page
	 * @param position Position in CSS style sheet
	 * @return New CSS media page
	 */
	RuleMedia createMedia(int position);

	/**
	 * Creates CSS named page
	 * @param position Position in CSS style sheet
	 * @return New CSS page
	 */
	RulePage createPage(int position);

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
	 * Creates CSS style sheet
	 * @return New CSS style sheet
	 */
	StyleSheet createStyleSheet();

}
