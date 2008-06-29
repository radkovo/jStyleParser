package cz.vutbr.web.css;

public interface PrettyOutput {

	/**
	 * Pretty output of CSS definition using indentation
	 * @param depth Number of times output is indented
	 * @return String with given rule
	 * @see OutputUtil
	 */
    public String toString(int depth);
    
}
