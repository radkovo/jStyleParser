package cz.vutbr.web.css;

import java.util.List;

/**
 * StyleSheet
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface StyleSheet {
  
    public String getCharset();

    public void setCharset(String charset);

    public List<ImportURI> getImportList();

    public List<Rule> getRulesList();
    
    public void check() throws StylesheetNotValidException;
}
