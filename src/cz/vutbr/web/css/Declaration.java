package cz.vutbr.web.css;

import java.util.List;

/**
 * Declaration
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 * @version 1.0 * Rewritten to allow inheritance
 */
public interface Declaration extends PrettyOutput, Comparable<Declaration> {

    public boolean isImportant();
    
    public void setImportant(boolean important);
    
    public List<Term<?>> getTerms();

    public void setTerms(List<Term<?>> terms);
    
    public String getProperty();

    public void setProperty(String property);
    
    public boolean isInherited(int level);
    
    public int getInheritanceLevel();
    
}
