package cz.vutbr.web.csskit;

import java.util.Collections;
import java.util.List;

import cz.vutbr.web.css.SSItem;
import cz.vutbr.web.css.SSType;
import cz.vutbr.web.css.SimpleSelector;

/**
 * SimpleSelector
 * @author Jan Svercl, VUT Brno, 2008
 */
public class SimpleSelectorImpl implements SimpleSelector {

	protected SSType firstItem;
	protected List<SSItem> items;
	
    protected Combinator combinator;

    
    public SimpleSelectorImpl() {
    	this.firstItem = null;
    	this.combinator = null;
    	this.items = Collections.emptyList();
    }
    
    
    
    /*
    protected SimpleSelectorImpl(SimpleNode n, SimpleNode cmb) {
        // combinator is optional, it's present only in 2nd and next SimpleSelectors 
        if(cmb != null) {
            if(cmb.jjtGetNumChildren() == 0) {
                combinator = EnumCombinator.space;
            }
            else if(((SimpleNode)cmb.jjtGetChild(0)).getType().equals("plus")) {
                combinator = EnumCombinator.plus;
            }
            else if(((SimpleNode)cmb.jjtGetChild(0)).getType().equals("greater")) {
                combinator = EnumCombinator.greater;
            }
        }
        
        int i = 0;
        if(((SimpleNode)n.jjtGetChild(0)).getType().equals("element_name")) {
            firstItem = new SSTypeImpl((SimpleNode)n.jjtGetChild(0));
            i = 1;
        }
        
        for(; i < n.jjtGetNumChildren(); i++) {
            SimpleNode cNode = (SimpleNode)n.jjtGetChild(i);
            if((cNode).getType().equals("class_a")) {
                itemsList.add(new SSItemClassImpl(cNode));
            }
            else if((cNode).getType().equals("hash")) {
                itemsList.add(new SSItemIDImpl(cNode));
            }
            else if((cNode).getType().equals("pseudo")) {
                itemsList.add(new SSItemPseudoImpl(cNode));
            }
            else if((cNode).getType().equals("attrib")) {
                itemsList.add(new SSItemAttribImpl(cNode));
            }
        }
    }
	*/

    /**
	 * @return the firstItem
	 */
	public SSType getFirstItem() {
		return firstItem;
	}



	/**
	 * @param firstItem the firstItem to set
	 */
	public void setFirstItem(SSType firstItem) {
		this.firstItem = firstItem;
	}



	/**
	 * @return the items
	 */
	public List<SSItem> getItems() {
		return items;
	}



	/**
	 * @param items the items to set
	 */
	public void setItems(List<SSItem> items) {
		this.items = items;
	}



	/**
	 * @return the combinator
	 */
	public Combinator getCombinator() {
		return combinator;
	}



	/**
	 * @param combinator the combinator to set
	 */
	public void setCombinator(Combinator combinator) {
		this.combinator = combinator;
	}



	@Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	if(combinator!=null) sb.append(combinator.value());
    	if(firstItem!=null) sb.append(firstItem);
    	sb = OutputUtil.appendList(sb, items, OutputUtil.EMPTY_DELIM);
    	
    	return sb.toString();
    }
    
    /**
     * Returns name of last class in SimpleSelector. If no class defined, NULL returned.
     */
    public String getClassName() {
        String className = null;
        for(SSItem ss : items) {
            if(ss instanceof SSItemClassImpl) {
                className = ((SSItemClassImpl)ss).getValue();
            }
        }
        return className;
    }
    
    /**
     * Returns name of last ID in SimpleSelector. If no ID defined, NULL returned.
     */
    public String getIDName() {
        String idName = null;
        for(SSItem ss : items) {
            if(ss instanceof SSItemIDImpl)
                idName = ((SSItemIDImpl)ss).getValue();
        }
        return idName;
    }
    
    /**
     * Returns name of element in SimpleSelector. If no element name defined, NULL returned.
     */
    public String getElementName() {
        if(firstItem == null) 
            return null;
        else 
            return firstItem.getValue();
    }
}
