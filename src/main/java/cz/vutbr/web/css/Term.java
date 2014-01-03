package cz.vutbr.web.css;

/**
 * Part of value declaration of CSS property. Can be atomic or
 * contain other Terms inside in case of TermList or TermFuncion.
 * @param <T> Type of value stored in term, for atomic types
 * 			 usually of type String, Float or Integer
 * 
 * @author Karel Piwko, 2008
 * @author Jan Svercl, 2008
 * 
 */
public interface Term<T> {

	/**
	 * This operator is between terms in value part of CSS declaration.
	 * Typically, indistinguishable values of are shorthanded by SLASH, alternatives are 
	 * divides by COMMA and SPACE when multi-values are used 
	 * 
	 * <p>
	 * Currently, operators are only syntactic sugar, because they are not used during
	 * parsing values in current implementation
	 * </p>
	 * 
	 * @author kapy
	 *
	 */
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
    
    /**
     * Getter for value
     * @return the value of the term
     */
    public T getValue();
    
    /**
     * Setter for value
     * @param value
     * @return Modified object to allow chaining
     */
    public Term<T> setValue(T value);
    
    /**
     * Operator between two terms. The first term is having <code>null</code>
     * @return the operator
     */
    public Operator getOperator();

    /**
     * Sets operator
     * @param operator
     * @return Modified object to allow chaining
     */
    public Term<T> setOperator(Operator operator);
    
}
