package cz.vutbr.web.css;

/**
 * Base class for elements of CSS definition
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 * @version 1.0 * Added comments
 */
public interface Rule  { 
	
	/**
	 * Pretty output of CSS definition using indentation
	 * @param depth Number of times output is indented
	 * @return String with given rule
	 * @see OutputUtil
	 */
    public String toString(int depth);
    
    /**
     * Checks whether rule is valid 
     * @param path
     * @throws StyleSheetNotValidException
     */
    public void check(String path) throws StyleSheetNotValidException;
    
    
}
