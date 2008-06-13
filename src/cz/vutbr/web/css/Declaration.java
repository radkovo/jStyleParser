package cz.vutbr.web.css;

import java.util.List;

/**
 * Declaration
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface Declaration {

    public boolean isImportant();
    
    public void setImportant(boolean important);
    
    public List<Term> getTermsList();

    public String getProperty();

    public void setProperty(String property);
    
    public String toString(int depth);
    
    public void check(String path) throws StylesheetNotValidException;
}
