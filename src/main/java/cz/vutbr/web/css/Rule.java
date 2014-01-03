package cz.vutbr.web.css;

import java.util.List;

/**
 * Base class for elements of CSS definition.
 * All rules can be created as immutable objects, but
 * this immutability can be changed by functions
 * {@code unlock()} or {@code replaceAll()} 
 * Rule is generally collection of other, finer grained object.
 * 
 * @author kapy
 */
public interface Rule<T> extends List<T> {  
	   
	/**
	 * Replaces all elements stored inside. Replaces changes
	 * whole collection, so it can be used to unlock immutable object.
	 * 	 
	 * @param replacement New list
	 * @return Modified collection
	 */
	Rule<T> replaceAll(List<T> replacement);
	
	/**
	 * Unlocks immutable object by changing collection from
	 * immutable to mutable
	 * @return Modified collection
	 */
	Rule<T> unlock();
	
	/**
	 * Returns underlying collection as list. This list is shared
	 * with Rule, so can be directly modified
	 * @return Underlying collection
	 */
	List<T> asList();
    
}
