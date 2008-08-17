package cz.vutbr.web.css;

import java.util.List;

/**
 * Base class for elements of CSS definition.
 * All rules can be created as immutable object.
 * Rule is generally collection of other, finier grained object. 
 * Calling this function replaces this immutable object with another one,
 * which can be mutable.
 * 
 * @author kapy
 */
public interface Rule<T> extends List<T> {  
	   
	Rule<T> replaceAll(List<T> replacement);
	
	List<T> asList();
    
}
