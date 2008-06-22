package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermNumber;

/**
 * TermIdent
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko
 */
public class TermNumberImpl extends TermImpl implements TermNumber {

    protected Float value;
    protected Unit unit = null;    
        
    public TermNumberImpl(Float value) {
        setValue(value);
    }
    
    public TermNumberImpl(Float value, Unit unit, int unary) {
        this(unary * value);
        this.unit = unit;
    }
    
    public TermNumberImpl(String value, Unit unit, int unary) {
    	
    	if(value==null)
    		throw new IllegalArgumentException("Invalid initiazition string in TermNumber(null)");
    	
    	Float f;
    	try {
    		// trim & lowercase
    		value = value.trim().toLowerCase();
    		// trim units from value
    		if(unit!=null)
    			value = value.replaceAll(unit.value()+"$", "");
    		
    		f = Float.parseFloat(value);
    		this.value = f * unary;
    		this.unit = unit;    		
    	}
    	catch (NumberFormatException e) {
    		throw new IllegalArgumentException("Invalid number format " + value, e);
    	}
    	
    	
    }
    
    /**
	 * @return the value
	 */
	public Float getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Float value) {
		if(value==null)
			throw new IllegalArgumentException("Invalid float value(null) in TermNumber");
		this.value = value;
	}

	/**
	 * @return the unit
	 */
	public Unit getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();

		if(operator!=null) sb.append(operator.value());
		
		// conversion of float to int
		if(isInteger())
			sb.append(value.intValue());
		else
			sb.append(value);

		if(unit!=null) sb.append(unit.value());
		
		return sb.toString();
    }
	
    public boolean isLength() {
        if(value.floatValue() == 0 && unit == null) 
            return true;
        if(unit == null) 
            return false;
        
        switch (unit) {
            case px: return true;
            case em: return true;
            case ex: return true;
            case cm: return true;
            case mm: return true;
            case pt: return true;
            case pc: return true;
            case deg: return false;
            case rad: return false;
            case grad: return false;
            case ms: return false;
            case s: return false;
            case hz: return false;
            case khz: return false;
            default: return false;
        }
    }
    
    public boolean isNumber() {
        return (unit == null);
    }
    
    public boolean isInteger() {
        return (unit == null && value.floatValue() == value.intValue());
    }
}
