package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermNumeric;

public class TermNumericImpl<T> extends TermImpl<T> implements TermNumeric<T> {

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
	public TermNumeric<T> setUnit(Unit unit) {
		this.unit = unit;
		return this;
	}

	@Override
    public String toString() {
		StringBuilder sb = new StringBuilder();
		if(operator!=null) sb.append(operator.value());
		if(value!=null) sb.append(value);
		if(unit!=null) sb.append(unit.value());
		return sb.toString();
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
		if (!(obj instanceof TermNumericImpl<?>))
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
