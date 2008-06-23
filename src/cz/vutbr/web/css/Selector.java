package cz.vutbr.web.css;

import java.util.List;

import javax.naming.OperationNotSupportedException;

/**
 * Selector
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 * @version 1.0 * Extends Rule interface
 * 				 * Added setters
 * 			     * Added getLastSimpleSelector() method
 */
public interface Selector extends Rule {
  
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
	
    public List<SimpleSelector> getSimpleSelectors();
    
    public void setSimpleSelectors(List<SimpleSelector> selectors);

    /**
     * Gets last SimpleSelector stored in list,
     * so its values are easily read
     * @return Last SimpleSelector or null, 
     * @throws OperationNotSupportedException In case that there is no simple selector inside
     */
    public SimpleSelector getLastSimpleSelector() throws OperationNotSupportedException;
    
    /**
     * Computes specificity according to CSS rules
     * @return
     */
    public Specificity computeSpecificity();
    
}
