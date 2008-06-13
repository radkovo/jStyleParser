package cz.vutbr.web.domAssign.data;

import cz.vutbr.web.css.TermIdent;

/**
 * TermIdent
 * @author Jan Svercl, VUT Brno, 2008
 */
public class DataTermIdent extends DataTerm implements TermIdent {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if(value == null) {
            throw new NullPointerException();
        }    
        else {
            this.value = value;
        }
    }
    
    public DataTermIdent(String value) {
        setValue(value);
    }
    
    @Override
    public String toString() {
        return operator(value);
    }
}
