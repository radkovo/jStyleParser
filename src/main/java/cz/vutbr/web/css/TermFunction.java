package cz.vutbr.web.css;

import java.util.List;

/**
 * Holds name of CSS function and terms stored inside
 * @author Jan Svercl, VUT Brno, 2008
 * @author Karel Piwko, 2008
 */
public interface TermFunction extends TermList {

    /**
     * Gets the name of the function.
     * @return The function name.
     */
    public String getFunctionName();
    
    public TermFunction setFunctionName(String functionName);
    
    /**
     * Splits the list of arguments to several lists based on the given separator term.
     * @param separator The term used as a separator (typically TermOperator(',').
     * @return A list of term lists corresponding to the individual separated arguments.
     */
    public List<List<Term<?>>> getSeparatedArgs(Term<?> separator);
    
    /**
     * Splits the list of arguments using the given separator term and tries to convert the
     * arguments to typed values (lengths, times, numbers, etc...) 
     * @param separator The term used as a separator (typically TermOperator(',').
     * @return A list of typed numeric values or {@code null} in case that the arguments cannot be converted to values. 
     */
    public List<TermFloatValue> getSeparatedValues(Term<?> separator);

    /**
     * Tries to convert the terms to a list of typed values (lengths, times, numbers, etc...). 
     * @return A list of typed numeric values or {@code null} in case that the arguments cannot be converted to values. 
     */
    public List<TermFloatValue> getValues();

}
