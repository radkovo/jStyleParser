package cz.vutbr.web.css;

/**
 * Forces human readable variant of toString() method.
 * 
 * @author kapy
 *
 */
public interface PrettyOutput {

	/**
	 * Pretty output of CSS definition using indentation
	 * @param depth Number of times output is indented
	 * @return String with given rule
	 * @see cz.vutbr.web.csskit.OutputUtil
	 */
    public String toString(int depth);
    
}
