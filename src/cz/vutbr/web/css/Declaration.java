package cz.vutbr.web.css;

/**
 * Basic CSS declaration consisting of list of terms.
 * Implements comparable to allow set declaration with bigger priority,
 * either by its important! flag or by its selector's specificity
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface Declaration extends Rule<Term<?>>, PrettyOutput, Comparable<Declaration> {

    public boolean isImportant();
    
    public void setImportant(boolean important);
    
    public String getProperty();

    public void setProperty(String property);
    
}
