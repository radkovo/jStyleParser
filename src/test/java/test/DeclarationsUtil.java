package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleFactory;
import cz.vutbr.web.css.Term;

/**
 * Creates declarations
 * @author kapy
 *
 */
public class DeclarationsUtil {

	private static final RuleFactory rf = CSSFactory.getRuleFactory();
	
	
	/**
	 * Simple declaration of rule. Other functions are wrappers
	 * @param property Property name
	 * @param terms Terms of declaration
	 * @param important !IMPORTANT flag
	 * @return Created declaration
	 */
	public static Declaration createDeclaration(String property, boolean important, List<Term<?>> terms) {
	
		Declaration dec = (Declaration) rf.createDeclaration().replaceAll(terms);
		
		dec.setProperty(property);
		dec.setImportant(important);
		
		return dec;
		
	}
	
	/**
	 * Adds declaration to list.
	 * @param declarations Appending list, will be created if <code>null</code>
	 * @param property Property name
	 * @param important !IMPORTANT flag
	 * @param terms Terms of declaration
	 * @return Modified/Created list
	 */
	public static List<Declaration> appendDeclaration(List<Declaration> declarations,
			String property, boolean important, List<Term<?>> terms) {
	
		if(declarations==null)
			declarations = new ArrayList<Declaration>();
		
		declarations.add(createDeclaration(property, important, terms));
		
		return declarations;
	}
	
	/**
	 * Add term to list. 
	 * @param terms Appending list, will be created if <code>null</code>
	 * @param op Operator to append term
	 * @param term Term
	 * @return Modified/created list
	 */
	public static List<Term<?>> appendTerm(List<Term<?>> terms, Term.Operator op, Term<?> term) {
		
		if(terms==null)
			terms = new ArrayList<Term<?>>();
		
		term.setOperator(op);
		
		terms.add(term);
		
		return terms;
	}
	
	
	
	public static Declaration createDeclaration(String property, List<Term<?>> terms) {
		return createDeclaration(property, false, terms);
	}
	
	
	public static Declaration createDeclaration(String property, Term<?>...terms) {
		return createDeclaration(property, Arrays.asList(terms));
	}
	
	public static Declaration createDeclaration(String property, boolean important, Term<?>...terms) {
		return createDeclaration(property, important, Arrays.asList(terms));
	}
	
	public static List<Declaration> appendDeclaration(List<Declaration> declarations,
			String property, List<Term<?>> terms) {
		return appendDeclaration(declarations, property, false, terms);
	}
	
	
	public static List<Declaration> appendDeclaration(List<Declaration> declarations,
			String property, boolean important, Term<?>...terms) {
		return appendDeclaration(declarations, property, false, Arrays.asList(terms));
	}
	
	public static List<Declaration> appendDeclaration(List<Declaration> declarations,
			String property, Term<?>...terms) {
		return appendDeclaration(declarations, property, Arrays.asList(terms));
	}
	
	public static List<Term<?>> appendSpaceTerm(List<Term<?>> terms, Term<?> term) {
		return appendTerm(terms, Term.Operator.SPACE, term);
	}
	
	public static List<Term<?>> appendCommaTerm(List<Term<?>> terms, Term<?> term) {
		return appendTerm(terms, Term.Operator.COMMA, term);
	}
	
	public static List<Term<?>> appendSlashTerm(List<Term<?>> terms, Term<?> term) {
		return appendTerm(terms, Term.Operator.SLASH, term);
	}
	
	
}
