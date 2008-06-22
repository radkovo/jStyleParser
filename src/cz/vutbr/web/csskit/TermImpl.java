package cz.vutbr.web.csskit;

import cz.vutbr.web.css.Term;

/**
 * Term
 * @author Jan Svercl, VUT Brno, 2008
 */
public abstract class TermImpl implements Term {

    protected Operator operator = null;
    
    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }    
}
