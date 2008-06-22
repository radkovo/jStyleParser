package cz.vutbr.web.css;

/**
 * SSType
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 * @version 1.0 * Added wildcard
 */
public interface SSType {

	public static final String WILDCARD = "*";
	
    public String getValue();

    public void setValue(String value);
    
}
