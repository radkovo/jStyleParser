package cz.vutbr.web.css;

import java.util.List;

/**
 * SimpleSelector
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko 2008
 * @version 1.0 Rewritten enum Combinator
 */
public interface SimpleSelector {

    public enum Combinator {
    	SPACE(" "),
    	PLUS("+"),
    	GREATER(">");
    
    	private String value;
    	
    	private Combinator(String value) {
    		this.value = value;
    	}
    	
    	public String value() {return value;}
    }

    
    public SSType getFirstItem();

    public void setFirstItem(SSType firstItem);
    
    public List<SSItem> getItems();
    
    public void setItems(List<SSItem> items);
    
    public Combinator getCombinator();
    
    public void setCombinator(Combinator combinator);
    
    
    public String getClassName();
    
    public String getIDName();
    
    public String getElementName();
}
