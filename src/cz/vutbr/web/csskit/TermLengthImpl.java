package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermLength;

public class TermLengthImpl extends TermNumericImpl<Float> implements TermLength {

    public TermLengthImpl(Float value) {
        setValue(value);
    }
    
    public TermLengthImpl(Float value, Unit unit, int unary) {
        this(unary * value);
        this.unit = unit;
    }
    
    public TermLengthImpl(String value, Unit unit, int unary) {
    	this(TermNumericImpl.convertFloat(value, unit.value(), unary));
    	this.unit = unit;
    }    	
}
