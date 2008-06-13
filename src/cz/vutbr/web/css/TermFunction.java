package cz.vutbr.web.css;

import java.util.List;

/**
 * TermFunction
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface TermFunction extends Term {

    public String getFunctionName();
    
    public void setFunctionName(String functionName);

    public List<Term> getTermsList();
    
}
