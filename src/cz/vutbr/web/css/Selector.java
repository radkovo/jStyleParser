package cz.vutbr.web.css;

import java.util.List;

/**
 * Selector
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface Selector {
  
    public List<SimpleSelector> getSimpleSelectorsList();
    
    public void check(String path) throws StylesheetNotValidException;
}
