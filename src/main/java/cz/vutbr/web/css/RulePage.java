package cz.vutbr.web.css;


/**
 * Contains collection of CSS declarations specified for a page rule.
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 * @author Bert Frees, 2012
 */
public interface RulePage extends RuleBlock<Rule<?>>, PrettyOutput {

	/**
	 * Gets name of the page
	 * @return Name of the page
	 */
    public String getName();
    
    /**
     * Sets name of the page
     * @param name New name of the page
     * @return Modified instance
     */
    public RulePage setName(String name);

	/**
	 * Gets pseudo-class of the page
	 * @return Pseudo-class of the page
	 */
    public Selector.PseudoPage getPseudo();
    
    /**
     * Sets pseudo-class of the page
     * @param pseudo New pseudo-class of the page
     * @return Modified instance
     */
    public RulePage setPseudo(Selector.PseudoPage pseudo);

}
