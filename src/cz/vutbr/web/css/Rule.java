package cz.vutbr.web.css;

/**
 * Rule
 * @author Jan Svercl, VUT Brno, 2008
 */
public abstract interface Rule {
    
    public String toString(int depth);
    
    public void check(String path) throws StylesheetNotValidException;
}
