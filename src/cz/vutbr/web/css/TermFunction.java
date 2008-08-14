package cz.vutbr.web.css;


/**
 * Holds name of CSS function and terms stored inside
 * @author Jan Svercl, VUT Brno, 2008
 * @author Karel Piwko, 2008
 */
public interface TermFunction extends TermList {

    public String getFunctionName();
    
    public TermFunction setFunctionName(String functionName);

}
