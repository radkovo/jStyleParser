package cz.vutbr.web.css;

import cz.vutbr.web.css.Selector.Operator;

public interface RuleFactory {

	Declaration createDeclaration();

	Declaration createDeclaration(Declaration clone);

	ImportURI createImport();

	RuleSet createSet();

	RuleSet createSet(RuleSet clone);

	RuleMedia createMedia();

	RulePage createPage();

	CombinedSelector createCombinedSelector();

	Selector createSelector();

	Selector.ElementName createElement(String elementName);

	Selector.ElementAttribute createAttribute(String value,
			boolean isStringValue, Operator operator, String attribute);

	Selector.ElementClass createClass(String className);

	Selector.ElementID createID(String id);

	Selector.PseudoPage createPseudoPage(String pseudo, String functionName);

	StyleSheet createStyleSheet();

}
