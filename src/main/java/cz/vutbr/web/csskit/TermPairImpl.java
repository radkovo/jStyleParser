package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermPair;

public class TermPairImpl<K,V> extends TermImpl<V> implements TermPair<K,V> {

	protected K key;
	
	protected TermPairImpl() {
	}
	
	public K getKey() {
		return key;
	}
	
	public TermPair<K,V> setKey(K key) {
		this.key = key;
		return this;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		if(operator != null) sb.append(operator.value());
		if(key!=null) sb.append(key);
		if(value!=null) { 
			sb.append(OutputUtil.SPACE_DELIM).append(value);
		}

		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		if (!(obj instanceof TermPairImpl<?, ?>))
			return false;
		@SuppressWarnings("unchecked")
		TermPairImpl<K,V> other = (TermPairImpl<K,V>) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
	
	
	
}
