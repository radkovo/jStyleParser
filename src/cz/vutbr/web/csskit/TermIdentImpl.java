package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermIdent;

/**
 * TermIdent
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 */
public class TermIdentImpl extends TermImpl implements TermIdent {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if(value == null) 
            throw new IllegalArgumentException("Invalid value for TermIndent(null)");
        
        this.value = value;
    }
    
    public TermIdentImpl(String value) {
        setValue(value);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(operator!=null) sb.append(operator.value());
        sb.append(value);
        
        return sb.toString();
    }    
    
}
