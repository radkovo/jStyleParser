package cz.vutbr.web.css;


/**
 * Acts as collection of Rules with added character sets.
 * This is entry point to parsed stylesheet.
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface StyleSheet extends Rule<RuleBlock<?>>{
  
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

}
