package cz.vutbr.web.css;

/**
 * Term
 * @author Jan Svercl, VUT Brno, 2008
 */
public abstract interface Term {

    public enum EnumOperator { space, slash, comma }
    
    public EnumOperator getOperator();

    public void setOperator(EnumOperator operator);
    
    public String operator(String term);
}
