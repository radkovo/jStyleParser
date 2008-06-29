package cz.vutbr.web.css;

import java.util.List;

/**
 * Holds set of CSS selectors and CSS declaration rules.
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko
 * @version 1.0 * Added setter methods
 * 				 * Renamed methods to reach naming consistency
 * 				 * Implements Comparable
 */
public interface RuleSet extends Rule, Comparable<RuleSet>, PrettyOutput {

	/**
	 * Gets selectors of given declaration
	 * @return Selectors for this rule
	 */
    public List<Selector> getSelectors();
    
    /**
     * Gets declarations of this rule
     * @return Declarations included in this rule
     */
    public List<Declaration> getDeclarations();
    
    /**
     * Sets selectors of this rule
     * @param selectors Selectors to be set
     */
    public void setSelectors(List<Selector> selectors);
    
    /**
     * Set declarations of this rule
     * @param declarations Declarations to be set
     */
    public void setDeclarations(List<Declaration> declarations);
    
}
