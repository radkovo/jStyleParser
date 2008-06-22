package cz.vutbr.web.csskit;

import cz.vutbr.web.css.SSType;

/**
 * SSType
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 * @version 1.0 removed protected constructor
 */
public class SSTypeImpl implements SSType {

    protected String value;

    public SSTypeImpl(String value) {
    	setValue(value);
    }
    
    /**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		
		if(value==null)
			throw new IllegalArgumentException("Invalid value of sstype(null)");
		
		this.value = value;
	}

	@Override
    public String toString() {
        return value;
    }
  
}
