package cz.vutbr.web.css;

public interface TermPair<K,V> extends Term<V> {

	/**
	 * Gets key
	 * @return Key stored in pair
	 */
	public K getKey();
	
	/**
	 * Sets key
	 * @param key Key to be stored in pair
	 */
	public void setKey(K key);
}
