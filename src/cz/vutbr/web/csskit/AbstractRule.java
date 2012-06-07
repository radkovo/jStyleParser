package cz.vutbr.web.csskit;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import cz.vutbr.web.css.Rule;

public class AbstractRule<T> extends AbstractList<T> implements Rule<T> {
	
	protected List<T> list = Collections.emptyList();
	protected int hash = 0;
	
	public List<T> asList() {
		return this.list;
	}
	
	public Rule<T> replaceAll(List<T> replacement) {
        hash = 0;
		this.list = replacement;
		return this;
	}
	
	public Rule<T> unlock() {
        hash = 0;
		this.list = new ArrayList<T>();
		return this;
	}
	
	@Override
	public int size() {
		return list.size();
	}
	
	@Override
	public T get(int index) {
		return list.get(index);
	}	
	
	@Override
	public T set(int index, T element) {
        hash = 0;
		return list.set(index, element);
	}
	
	@Override
	public void add(int index, T element) {
        hash = 0;
		list.add(index, element);
	}
	
	@Override
	public T remove(int index) {
        hash = 0;
		return list.remove(index);
	}
	
	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	@Override
	public boolean add(T o) {
	    hash = 0;
		return list.add(o);
	};
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
	    if (hash == 0)
	    {
    		final int prime = 31;
    		int result = super.hashCode();
    		result = prime * result + ((list == null) ? 0 : list.hashCode());
    		hash = result;
	    }
	    return hash;
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
		if (!(obj instanceof AbstractRule<?>))
			return false;
		AbstractRule<?> other = (AbstractRule<?>) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}

	
	
}
