package cz.vutbr.web.csskit;

import cz.vutbr.web.css.SSItem;
import cz.vutbr.web.css.SSType;
import cz.vutbr.web.css.SimpleSelector;
import cz.vutbr.web.csskit.parser.SimpleNode;

import java.util.ArrayList;

/**
 * SimpleSelector
 * @author Jan Svercl, VUT Brno, 2008
 */
public class SimpleSelectorImpl implements SimpleSelector {

    private SSType firstItem = null;
    private ArrayList<SSItem> itemsList = new ArrayList<SSItem>();
    private EnumCombinator combinator = null;

    public SSType getFirstItem() {
        return firstItem;
    }

    public void setFirstItem(SSType firstItem) {
        this.firstItem = firstItem;
    }

    public ArrayList<SSItem> getItemsList() {
        return itemsList;
    }
    
    public EnumCombinator getCombinator() {
        return combinator;
    }
    
    public void setCombinator(EnumCombinator combinator) {
        this.combinator = combinator;
    }
    
    protected SimpleSelectorImpl(SimpleNode n, SimpleNode cmb) {
        /* combinator is optional, it's present only in 2nd and next SimpleSelectors */
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
    
    @Override
    public String toString() {
        String out = "";
        if(combinator == EnumCombinator.space) {
            out += " ";
        }
        else if(combinator == EnumCombinator.plus) {
            out += "+";
        }
        else if(combinator == EnumCombinator.greater) {
            out += ">";
        }
        if(firstItem != null) {
            out += firstItem.toString();
        }
        for(SSItem item : itemsList) {
            out += item.toString();
        }
        return out;
    }
    
    /**
     * Returns name of last class in SimpleSelector. If no class defined, NULL returned.
     */
    public String getClassName() {
        String className = null;
        for(SSItem ss : itemsList) {
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
        for(SSItem ss : itemsList) {
            if(ss instanceof SSItemIDImpl) {
                idName = ((SSItemIDImpl)ss).getValue();
            }
        }
        return idName;
    }
    
    /**
     * Returns name of element in SimpleSelector. If no element name defined, NULL returned.
     */
    public String getElementName() {
        if(firstItem == null) {
            return null;
        }
        else {
            return firstItem.getValue();
        }
    }
}
