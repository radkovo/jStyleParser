package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermNumeric;

public abstract class TermNumericImpl<T> extends TermImpl<T> implements TermNumeric<T> {

	protected Unit unit;
	
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
		if(value!=null) sb.append(value);
		if(unit!=null) sb.append(unit.value());
		return sb.toString();
    }
	
	public static TermNumeric<?> valueOf(String value, int unary) {
		
		try {
			value = value.trim().toLowerCase();
			Integer i = Integer.valueOf(value);
			return new TermIntegerImpl(i, unary);
		}
		catch(NullPointerException e) {
			throw new IllegalArgumentException("Invalid string null to be converted to numeric");
		}
		// this is not integer, but float
		catch(NumberFormatException e) {
			try {
				Float f = Float.valueOf(value);
				return new TermNumberImpl(f, unary);
			}
			catch(NumberFormatException ne) {
				throw new IllegalArgumentException("Not valid integer nor float value", ne);
			}
		}
	}
	
	
	// helpers
	protected static Float convertFloat(String value, String unit, int unary) throws IllegalArgumentException {
		
    	try {
    		// trim & lowercase
    		value = value.trim().toLowerCase();
    		// trim units from value
    		if(unit!=null)	value = value.replaceAll(unit+"$", "");
    		
    		return Float.parseFloat(value) * unary;
    	}
    	catch (NumberFormatException e) {
    		throw new IllegalArgumentException("Invalid number format " + value, e);
    	}
    	catch (NullPointerException e) {
    		throw new IllegalArgumentException("Invalid null format");
    	}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
		if (!(obj instanceof TermNumericImpl))
			return false;
		@SuppressWarnings("unchecked")
		TermNumericImpl<T> other = (TermNumericImpl<T>) obj;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}
	
	
	
}
