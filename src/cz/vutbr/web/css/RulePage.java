package cz.vutbr.web.css;


/**
 * Contains collection of CSS declarations specified for name of CSS pseudo-page.
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface RulePage extends RuleBlock<Declaration>, PrettyOutput {

	/**
	 * Gets name of CSS pseudo-page
	 * @return Name of pseudo-page
	 */
    public String getPseudo();
    
    /**
     * Sets name of CSS pseudo-page
     * @param pseudo New name of pseudo-page
     * @return Modified instance
     */
    public RulePage setPseudo(String pseudo);

}
