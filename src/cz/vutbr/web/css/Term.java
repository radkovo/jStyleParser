package cz.vutbr.web.css;

/**
 * Term
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 * @version 1.0 * Rewritten and changed EnumOperator to Operator 
 * 				 * Removed String operator(String) method
 * 				 * Added equals() and hashCode()
 */
public interface Term<T> {

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
    
    public T getValue();
    
    public void setValue(T value);
    
    /**
     * Operator between two terms. The first term is having <code>null</code>
     * @return
     */
    public Operator getOperator();

    /**
     * Sets operator
     * @param operator
     */
    public void setOperator(Operator operator);
    
    public int hashCode();
    public boolean equals(Object obj);

    
}
