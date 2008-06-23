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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof TermNumberImpl))
			return false;
		final TermNumberImpl other = (TermNumberImpl) obj;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
