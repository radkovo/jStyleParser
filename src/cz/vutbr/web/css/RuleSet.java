package cz.vutbr.web.css;

import java.util.List;

/**
 * RuleSet
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface RuleSet extends Rule {

    public List<Selector> getSelectorsList();

    public List<Declaration> getDeclarationsList();
    
    public String toString(int depth);
    
    public void check(String path) throws StylesheetNotValidException;
}
