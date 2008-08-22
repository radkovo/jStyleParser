package cz.vutbr.web.csskit;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermList;

public class TermListImpl extends AbstractList<Term<?>> implements TermList {

	protected List<Term<?>> value;
	protected Operator operator;
	
	protected TermListImpl() {
		this.value = new ArrayList<Term<?>>();
	}
	
	protected TermListImpl(int initialSize) {
		this.value = new ArrayList<Term<?>>(initialSize);
	}
	
	/**
	 * @return the value
	 */
	public List<Term<?>> getValue() {
		return value;
	}
	
	/**
	 * @param value the value to set
	 */
	public TermList setValue(List<Term<?>> value) {
		this.value = value;
		return this;
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
	public TermList setOperator(Operator operator) {
		this.operator = operator;
		return this;
	}
	
	@Override
	public Term<?> get(int arg0) {
		return value.get(arg0);
	}
	
	@Override
	public void add(int index, Term<?> element) {
		value.add(index, element);
	}
	
	@Override
	public Term<?> remove(int index) {
		return value.remove(index);
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
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		// append operator
		if(operator!=null) sb.append(operator.value());
		OutputUtil.appendList(sb, value, OutputUtil.SPACE_DELIM);
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
