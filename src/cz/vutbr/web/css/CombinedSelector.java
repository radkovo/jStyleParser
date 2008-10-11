package cz.vutbr.web.css;

/**
 * CombinedSelector of CSS declaration block.
 * Acts as collection of Selectors with ability to get directly 
 * the last one. 
 * 
 * Computes specificity of selector.
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface CombinedSelector extends Rule<Selector> {
  
    /**
     * Gets last Selector stored in list,
     * so its values are easily read
     * @return Last Selector or null, 
     * @throws OperationNotSupportedException In case that there is no simple selector inside
     */
    public Selector getLastSelector() throws UnsupportedOperationException;
    
    /**
     * Computes specificity according to CSS rules
     * @return
     */
    public Specificity computeSpecificity();
    
	/**
	 * Specificity of given selector
	 * @author kapy
	 *
	 */
	public interface Specificity extends Comparable<Specificity> {
		
		/**
		 * Specificity levels
		 * @author kapy
		 *
		 */
		public enum Level { A,	B, C, D	};
		
		/**
		 * Compare specificities
		 */
		public int compareTo(Specificity o);
		
		/**
		 * Gets specificity of level
		 * @param level Specificity level
		 * @return Numerical value of specificity
		 */
		public int get(Level level);
		
		/**
		 * Adds one to specificity level
		 * @param level
		 */
		public void add(Level level);
	}	
    
    
}
