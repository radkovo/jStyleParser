package cz.vutbr.web.css;

import java.util.List;

/**
 * RulePage
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface RulePage extends Rule {

    public String getPseudo();
    
    public void setPseudo(String pseudo);

    public List<Declaration> getDeclarationsList();
    
    public String toString(int depth);
    
    public void check(String path) throws StylesheetNotValidException;
}
