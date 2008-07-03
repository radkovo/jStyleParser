package cz.vutbr.web.css;

import java.util.List;

/**
 * StyleSheet
 * @author Jan Svercl, VUT Brno, 2008
 * @author Karel Piwko, 2008
 */
public interface StyleSheet {
  
	/**
	 * Charset defined in stylesheet
	 * @return String identification of charset
	 */
    public String getCharset();

    /**
     * Sets charset
     * @param charset New charset
     */
    public void setCharset(String charset);

    /**
     * Getter for CSS Import Rules
     * @return List of css imports
     */
    public List<ImportURI> getImports();
    
    /**
     * Sets import rules
     * @param imports Imports to be set
     */
    public void setImports(List<ImportURI> imports);
    
    /**
     * Gets rules defined in stylesheet
     * @return List of rules defined in stylesheet
     */
    public List<Rule> getRules();
    
    /**
     * Sets rules of stylesheet
     * @param rules Rules to be set
     */
    public void setRules(List<Rule> rules);
    
   

}
