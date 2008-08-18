package cz.vutbr.web.domassign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermIdent;

/**
 * Selects appropriate variant when parsing content of CSS declaration.
 * Allows easy parsing of CSS declaration multi-values such as
 * <code>border: blue 1px</code>
 * @author kapy
 *
 */
public abstract class Variator {

	/**
	 * Total variants available
	 */
	protected int variants;

	/**
	 * Results of variants. Each variant is allowed to be passed only once
	 * in case of multi-value declaration, so this array is used to show
	 * that currently passed variant was already successfully passed in past
	 */
	protected boolean[] variantPassed;
	
	/**
	 * Property names according to each variant 
	 */
	protected String[] variantPropertyNames;
	
	/**
	 * Terms over which variants are tested
	 */
	protected List<Term<?>> terms;

	/**
	 * Creates variator which contains <code>variants</code> variants
	 * to be tested
	 * @param variants
	 */
	public Variator(int variants) {
		this.variants = variants;
		this.variantPassed = new boolean[variants];
		for (int i = 0; i < variants; i++)
			variantPassed[i] = false;
	}

	/**
	 * This function contains parsing block for variants
	 * @param variant Tested variant
	 * @param iteration Number of iteration, that is term to be tested
	 * @param properties Properties map where to store properties types
	 * @param values Values map where to store properties values
	 * @return <code>true</code> in case of success, <code>false</code> otherwise
	 */
	protected abstract boolean variant(int variant, int iteration,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values);

	/**
	 * Solves variant which leads to <code>inherit</code> CSS Property value.
	 * This overrides all other possible variants and no other informations are 
	 * allowed per CSS Declaration.
	 * 
	 * Example:
	 * <code>margin: inherit</code> is valid value and leads to setting of
	 * <ul>
	 * <li><code>margin-top: inherit</code></li>
	 * <li><code>margin-right: inherit</code></li>
	 * <li><code>margin-bottom: inherit</code></li>
	 * <li><code>margin-left: inherit</code></li>
	 * <ul>
	 * 
	 * <br/>
	 * <code>margin: 0px inherit</code> is invalid value.
	 * 
	 * @param properties Properties map where to store properties types
	 * @param values Values map where to store properties values
	 * @return <code>true</code> in case of success, <code>false</code> otherwise
	 */
	protected abstract boolean inheritance(
			Map<String, CSSProperty> properties, Map<String, Term<?>> values);

	/**
	 * Check if variant, which was passed is able to be located in place where it was 
	 * found.
	 * 
	 * Example:<br/>
	 * We have declaration:
	 * <code>font: 12px/14px sans-serif</code>
	 * Then according to grammar:<br/>
	 * <pre>
	 * 	[ 
	 * 		[ <'font-style'> || <'font-variant'> || <'font-weight'> ]? 
	 * 		<'font-size'> 
	 * 		[ / <'line-height'> ]? 
	 * 		<'font-family'> 
	 *  ] 
	 *  | caption | icon | menu | message-box | 
	 *  small-caption | status-bar | inherit
	 * </pre> 
	 * <ol>
	 * <li><code>12px</code> is assigned to </i>font-size</i></li>
	 * <li><code>14px</code> is checked to have SLASH operator before 
	 * and check to whether <i>font-size</i> was defined before it</li>
	 * <li><code>sans-serif</code> is tested to have at least 
	 * definition of <i>font-size</i> before itself</li>
	 * <li>declaration passes</li>
	 * </ol>
	 *   	  
	 * @param variant Identification of current variant which passed test 
	 * @param term Position in term list of terms which passed test
	 * @return <code>true</code> in case of success, <code>false</code> elsewhere
	 * @see Term.Operator
	 */
	protected boolean variantCondition(int variant, int term) {
		return true;
	}
	
	/**
	 * Test all terms
	 * @param properties
	 * @param values
	 * @return
	 */
	public boolean vary(Map<String, CSSProperty> properties,
			Map<String, Term<?>> values) {

		// inheritance
		if (terms.size()==1
				&& terms.get(0) instanceof TermIdent
				&& "inherit".equalsIgnoreCase(((TermIdent) terms.get(0))
						.getValue())) {
			return inheritance(properties, values);
		}

		// for all terms
		for (int i = 0; i < terms.size(); i++) {

			boolean passed = false;

			// check all variants
			for (int v = 0; v < variants; v++) {
				// check and if variant was already found
				// signalize error by discarding complete declaration
				passed = variant(v, i, properties, values);
				if (passed) {
					// failed on term, because this variant already passed in past
					if (variantPassed[v]) return false;
									
					// check variant conditions
					if (!variantCondition(v, i)) return false;
					
					// mark occurrence of variant
					variantPassed[v] = true;
					// we have found, skip evaluation
					break;
				}
			}
			// no variant could be assingned
			if (!passed)
				return false;
		}
		// all terms passed
		return true;
	}

	/**
	 * Uses variator functionality to test selected variant on term
	 * @param variant Which variant will be tested
	 * @param term Term on which variant will be tested
	 * @param properties Properties map where to store property type
	 * @param values Values map where to store property value
	 * @return <code>true</code> in case of success, <code>false</code> otherwise
	 */
	public boolean tryOneTermVariant(int variant, Declaration d, Map<String, CSSProperty> properties,
			Map<String, Term<?>> values) {
		
		// only one term is allowed
		if(d.size()!=1)
			return false;
		
		this.terms = new ArrayList<Term<?>>();
		this.terms.add(d.get(0));
		
		return variant(variant, 0, properties, values);
	}
	
	/**
	 * Uses variator functionality to test selected variant on more terms.
	 * This is used when variant is represented by more terms. Since usually
	 * only one term per variant is used, only one multiple variant is allowed
	 * per variator and should be placed as the last one 
	 * 
	 * @param variant Number of variant (last variant in variator)
	 * @param properties Properties map where to store property type
	 * @param values Values map where to store property value
	 * @param terms Array of terms used for variant
	 * @return <code>true</code> in case of success, <code>false</code> otherwise
	 */
	public boolean tryMultiTermVariant(int variant, Map<String, CSSProperty> properties,
			Map<String, Term<?>> values, Term<?>...terms) {
		
		this.terms = Arrays.asList(terms);
		
		return variant(variant, 0, properties, values);
	}
	
	/**
	 * Assigns property names for each variant
	 * @param variantPropertyNames Array of property names
	 */
	public void assignVariantPropertyNames(String... variantPropertyNames) {
		this.variantPropertyNames = variantPropertyNames;
	}

	/**
	 * Assigns terms to be checked by variator
	 * @param terms Terms to be assigned
	 */
	public void assignTerms(Term<?>... terms) {
		this.terms = Arrays.asList(terms);
	}

	/**
	 * Assigns terms from declaration
	 * @param d Declaration which contains terms
	 */
	public void assignTermsFromDeclaration(Declaration d) {
		this.terms = d.asList();
	}
}
