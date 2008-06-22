package cz.vutbr.web.css;

/**
 * Term
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 * @version 1.0 * Added text representation to enum EnumOperator
 * 				 * Removed String operator(String) method
 */
public abstract interface Term {

    public enum Operator {
    	    	
    	SPACE(" "),
    	SLASH("/"),
    	COMMA(", ");
    	
    	private final String value;
    	
    	private Operator(String value) {
    		this.value = value; 
    	}
    	
    	public String value() { return value;}
    }
    
    public Operator getOperator();

    public void setOperator(Operator operator);
    
}
