package cz.vutbr.web.csskit;

import cz.vutbr.web.css.Term;

/**
 * Term
 * 
 * @author Karel Piwko, 2008
 */
public class TermImpl<T> implements Term<T> {

	protected T value;
	protected Operator operator = null;

	protected TermImpl() {
	}
	
	public Operator getOperator() {
		return operator;
	}

	public Term<T> setOperator(Operator operator) {
		this.operator = operator;
		return this;
	}

	/**
	 * @return the value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public Term<T> setValue(T value) {
		this.value = value;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(operator != null) sb.append(operator.value());
		if(value!=null) sb.append(value.toString());

		return sb.toString();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (obj == null)
			return false;
		if (!(obj instanceof TermImpl))
			return false;
		@SuppressWarnings("unchecked")
		TermImpl<T> other = (TermImpl<T>) obj;
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
