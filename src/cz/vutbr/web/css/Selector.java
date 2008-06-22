package cz.vutbr.web.css;

import java.util.List;

/**
 * Selector
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 * @version 1.0 * Extends Rule interface
 * 				 * Added setters
 */
public interface Selector extends Rule {
  
    public List<SimpleSelector> getSimpleSelectors();
    
    public void setSimpleSelectors(List<SimpleSelector> selectors);
    
}
