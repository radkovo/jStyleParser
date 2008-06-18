package cz.vutbr.web.css;

import java.util.List;

/**
 * StyleSheet
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko
 * @version 1.0 Added setters for rules and imports lists
 */
public interface StyleSheet {
  
    public String getCharset();

    public void setCharset(String charset);

    public List<ImportURI> getImports();

    public List<Rule> getRules();
    
    public void setRules(List<Rule> rules);
    
    public void setImports(List<ImportURI> imports);
    
    public void check() throws StylesheetNotValidException;
}
