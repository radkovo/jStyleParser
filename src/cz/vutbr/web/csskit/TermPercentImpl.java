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
    
}
