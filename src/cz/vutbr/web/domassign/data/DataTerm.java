package cz.vutbr.web.domassign.data;

import cz.vutbr.web.css.Term;

/**
 * Term
 * @author Jan Svercl, VUT Brno, 2008
 */
public abstract class DataTerm implements Term {

    private EnumOperator operator = null;
    
    public EnumOperator getOperator() {
        return operator;
    }

    public void setOperator(EnumOperator operator) {
        this.operator = operator;
    }
    
    public String operator(String term) {
        String out = "";
        if(operator == EnumOperator.space) {
            out += " ";
        }
        else if(operator == EnumOperator.slash) {
            out += "/";
        }
        else if(operator == EnumOperator.comma) {
            out += ", ";
        }
        out += term;
        return out;
    }
}
