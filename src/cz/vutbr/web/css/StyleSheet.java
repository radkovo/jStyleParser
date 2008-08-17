package cz.vutbr.web.css;

import java.util.List;

/**
 * Acts as collection of Rules with added character sets.
 * This is entry point to parsed stylesheet.
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface StyleSheet extends Rule<Rule<?>>{
  
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

}
