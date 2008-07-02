package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermNumber;

/**
 * TermIdent
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko
 */
public class TermNumberImpl extends TermNumericImpl<Float> implements TermNumber {

    public TermNumberImpl(Float value) {
        setValue(value);
    }
    
    public TermNumberImpl(Float value, int unary) {
        this(unary * value);       
    }
    
    public TermNumberImpl(String value, int unary) {
    	this(TermNumericImpl.convertFloat(value, null, unary));
    }	
}
