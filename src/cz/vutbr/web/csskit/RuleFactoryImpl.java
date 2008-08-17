/**
 * 
 */
package cz.vutbr.web.csskit;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.ImportURI;
import cz.vutbr.web.css.RuleFactory;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.RulePage;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.Selector.ElementAttribute;
import cz.vutbr.web.css.Selector.ElementClass;
import cz.vutbr.web.css.Selector.ElementID;
import cz.vutbr.web.css.Selector.ElementName;
import cz.vutbr.web.css.Selector.Operator;
import cz.vutbr.web.css.Selector.PseudoPage;

/**
 * @author kapy
 *
 */
public class RuleFactoryImpl implements RuleFactory {

	
	private static int ruleSetNum = 0;
	
	private static RuleFactory instance;
	
	static {
		instance = new RuleFactoryImpl();
		CSSFactory.registerRuleFactory(instance);
	}
	
	private RuleFactoryImpl() {
	}
	
	/* (non-Javadoc)
	 * @see cz.vutbr.web.css.RuleFactory#createDeclaration()
	 */
	public Declaration createDeclaration() {
		return new DeclarationImpl();
	}

	/* (non-Javadoc)
	 * @see cz.vutbr.web.css.RuleFactory#createDeclaration(cz.vutbr.web.css.Declaration)
	 */
	public Declaration createDeclaration(Declaration clone) {
		return new DeclarationImpl(clone);
	}

	/* (non-Javadoc)
	 * @see cz.vutbr.web.css.RuleFactory#createImport()
	 */
	public ImportURI createImport() {
		return new ImportURIImpl();
	}

	/* (non-Javadoc)
	 * @see cz.vutbr.web.css.RuleFactory#createMedia()
	 */
	public RuleMedia createMedia() {
		return new RuleMediaImpl();
	}

	/* (non-Javadoc)
	 * @see cz.vutbr.web.css.RuleFactory#createPage()
	 */
	public RulePage createPage() {
		return new RulePageImpl();
	}

	/* (non-Javadoc)
	 * @see cz.vutbr.web.css.RuleFactory#createCombinedSelector()
	 */
	public CombinedSelector createCombinedSelector() {
		return new CombinedSelectorImpl();
	}

	/* (non-Javadoc)
	 * @see cz.vutbr.web.css.RuleFactory#createSet()
	 */
	public RuleSet createSet() {
		return new RuleSetImpl(++ruleSetNum);
	}

	/* (non-Javadoc)
	 * @see cz.vutbr.web.css.RuleFactory#createSet(cz.vutbr.web.css.RuleSet)
	 */
	public RuleSet createSet(RuleSet clone) {
		return new RuleSetImpl(++ruleSetNum, clone);
	}

	/* (non-Javadoc)
	 * @see cz.vutbr.web.css.RuleFactory#createSelector()
	 */
	public Selector createSelector() {
		return new SelectorImpl();
	}
	
	public ElementAttribute createAttribute(String value,
			boolean isStringValue, Operator operator, String attribute) {
		return new SelectorImpl.ElementAttributeImpl(value, isStringValue, operator, attribute);
	}
	
	public ElementClass createClass(String className) {
		return new SelectorImpl.ElementClassImpl(className);
	}

	public ElementName createElement(String elementName) {
		return new SelectorImpl.ElementNameImpl(elementName);
	}
	
	public ElementID createID(String id) {
		return new SelectorImpl.ElementIDImpl(id);
	}
	
	public PseudoPage createPseudoPage(String pseudo, String functionName) {
		return new SelectorImpl.PseudoPageImpl(pseudo, functionName);
	}
	
	/* (non-Javadoc)
	 * @see cz.vutbr.web.css.RuleFactory#createStyleSheet()
	 */
	public StyleSheet createStyleSheet() {
		return new StyleSheetImpl();
	}

}
