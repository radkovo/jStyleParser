package cz.vutbr.web.css;

import java.util.List;

/**
 * RulePage
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 * @version 1.0 * Added setter for declaration
 * 				 * Renamed methods to achieve consistency in naming
 */
public interface RulePage extends Rule {

    public String getPseudo();
    
    public void setPseudo(String pseudo);

    public List<Declaration> getDeclarations();   
    
    public void setDeclarations(List<Declaration> declarations);
}
