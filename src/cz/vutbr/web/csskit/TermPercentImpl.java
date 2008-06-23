package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermPercent;

/**
 * TermPercent
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko
 * @version 1.0 * Construction moved to parser
 * 				 * Rewritten toString() method
 */
public class TermPercentImpl extends TermImpl implements TermPercent {

    protected Float value;
 
    
    public TermPercentImpl(Float value) {
    	setValue(value);
    }
    
    public TermPercentImpl(String value, int unary) {
    	if(value==null)
    		throw new IllegalArgumentException("Invalid initiazition string in TermPercent(null)");
    	
    	Float f;
    	try {
    		// trim & lowercase & replace sign
    		value = value.trim().toLowerCase().replaceAll("%$","");
    		
    		f = Float.parseFloat(value);
    		this.value = f * unary;    		  		
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
			throw new IllegalArgumentException("Invalid value in TermPercent(null)");
		this.value = value;
	}



	@Override
    public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if(operator!=null) sb.append(operator.value());
		sb.append(value).append(OutputUtil.PERCENT_SIGN);

		return sb.toString();
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
		if (!(obj instanceof TermPercentImpl))
			return false;
		final TermPercentImpl other = (TermPercentImpl) obj;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}    
    
}
