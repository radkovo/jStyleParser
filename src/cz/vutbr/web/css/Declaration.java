package cz.vutbr.web.css;

/**
 * Basic CSS declaration consisting of list of terms
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface Declaration extends Rule<Term<?>>, PrettyOutput, Comparable<Declaration> {

    public boolean isImportant();
    
    public void setImportant(boolean important);
    
    public String getProperty();

    public void setProperty(String property);
    
    public boolean isInherited(int level);
    
    public int getInheritanceLevel();
    
}
