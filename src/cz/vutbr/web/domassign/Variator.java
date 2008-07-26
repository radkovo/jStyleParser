package cz.vutbr.web.domassign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.NodeData.CSSProperty;

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
	 * Solves variant which leads to <code>inherit</code> CSS Property
	 * @param properties Properties map where to store properties types
	 * @param values Values map where to store properties values
	 * @return <code>true</code> in case of success, <code>false</code> otherwise
	 */
	protected abstract boolean inheritance(
			Map<String, CSSProperty> properties, Map<String, Term<?>> values);

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
					if (variantPassed[v])
						return false;
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
	public boolean tryVariant(int variant, Declaration d, Map<String, CSSProperty> properties,
			Map<String, Term<?>> values) {
		
		// only one term is allowed
		if(d.getTerms().size()!=1)
			return false;
		
		this.terms = new ArrayList<Term<?>>();
		this.terms.add(d.getTerms().get(0));
		
		return variant(variant, 0, properties, values);
	}
	
	/**
	 * Uses variator functionality to test selected variant on more term.
	 * This is used when variant is represented by more terms. Since usually
	 * is used only one term per variant, only one multiple variant is allowed
	 * per variator and should be placed as the last one 
	 * 
	 * @param variant Number of variant (last variant in variator
	 * @param properties Properties map where to store property type
	 * @param values Values map where to store property value
	 * @param terms Array of terms used for variant
	 * @return <code>true</code> in case of success, <code>false</code> otherwise
	 */
	public boolean tryVariant(int variant, Map<String, CSSProperty> properties,
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
		this.terms = d.getTerms();
	}
}
