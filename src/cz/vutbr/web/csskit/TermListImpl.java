package cz.vutbr.web.csskit;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermList;

public class TermListImpl extends AbstractCollection<Term<?>> implements TermList {

	protected List<Term<?>> value;
	protected Operator operator;
	
	public TermListImpl() {
		this.value = new ArrayList<Term<?>>();
	}
	
	public TermListImpl(int initialSize) {
		this.value = new ArrayList<Term<?>>(initialSize);
	}
	
	/**
	 * @return the value
	 */
	public List<Term<?>> getValue() {
		return value;
	}
	
	@Override
	public int size() {
		return value.size();
	}
	
	@Override
	public Iterator<Term<?>> iterator() {
		return value.iterator();
	}
	
	@Override
	public boolean add(Term<?> o) {
		return value.add(o);
	}
	
	/**
	 * @param value the value to set
	 */
	public void setValue(List<Term<?>> value) {
		this.value = value;
	}
	/**
	 * @return the operator
	 */
	public Operator getOperator() {
		return operator;
	}
	/**
	 * @param operator the operator to set
	 */
	public void setOperator(Operator operator) {
		this.operator = operator;
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
		if (!(obj instanceof TermListImpl))
			return false;
		TermListImpl other = (TermListImpl) obj;
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
