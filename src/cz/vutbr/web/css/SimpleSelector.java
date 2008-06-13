package cz.vutbr.web.css;

import java.util.List;

/**
 * SimpleSelector
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface SimpleSelector {

    public enum EnumCombinator { space, plus, greater }
    
    public SSType getFirstItem();

    public void setFirstItem(SSType firstItem);
    
    public List<SSItem> getItemsList();
    
    public EnumCombinator getCombinator();
    
    public void setCombinator(EnumCombinator combinator);
    
    public String getClassName();
    
    public String getIDName();
    
    public String getElementName();
}
