package cz.vutbr.web.css;

import java.util.List;

/**
 * Holds set of CSS declarations for specified selectors.
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface RuleSet extends Rule<Declaration>, Comparable<RuleSet>,
		PrettyOutput {

	/**
	 * Gets selectors of given declaration
	 * 
	 * @return Selectors for this rule
	 */
	public List<CombinedSelector> getSelectors();

	/**
	 * Sets selectors for this CSS declarations
	 * 
	 * @param selectors
	 *            Selectors to be set
	 * @return Modified instance
	 */
	public RuleSet setSelectors(List<CombinedSelector> selectors);

	/**
	 * Compares with another instance of RuleSet
	 * @throws ClassCastException When compared instance if a different
	 * implementation
	 * @see Comparable
	 */
	public int compareTo(RuleSet o) throws ClassCastException;
	
}
