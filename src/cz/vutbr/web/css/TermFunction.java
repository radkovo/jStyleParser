package cz.vutbr.web.css;

import java.util.List;

/**
 * TermFunction
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko 2008
 */
public interface TermFunction extends Term<List<Term<?>>> {

    public String getFunctionName();
    
    public void setFunctionName(String functionName);

}
