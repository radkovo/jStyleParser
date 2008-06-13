package cz.vutbr.web.cssKit;

import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.SimpleSelector;
import cz.vutbr.web.css.StylesheetNotValidException;
import cz.vutbr.web.cssKit.parser.SimpleNode;
import java.util.ArrayList;

/**
 * Selector
 * @author Jan Svercl, VUT Brno, 2008
 */
public class SelectorImpl implements Selector {
  
    private ArrayList<SimpleSelector> simpleSelectorsList = new ArrayList<SimpleSelector>();

    public ArrayList<SimpleSelector> getSimpleSelectorsList() {
        return simpleSelectorsList;
    }
    
    /* input: SimpleSelector (Combinator SimpleSelector)* */
    protected SelectorImpl(SimpleNode n) {
        SimpleNode combinator = null;
        for(int i = 0; i < n.jjtGetNumChildren(); i++) {
            SimpleNode cNode = (SimpleNode)n.jjtGetChild(i);
            
            if(cNode.getType().equals("combinator")) {
                combinator = cNode;
            }
            
            if(cNode.getType().equals("simple_selector")) {
                simpleSelectorsList.add(new SimpleSelectorImpl(cNode, combinator));
            }
        }
    }
    
    @Override
    public String toString() {
        String out = "";
        for(SimpleSelector simpleSelector : simpleSelectorsList) {
            out += simpleSelector.toString();
        }
        return out;
    }
    
    public void check(String path) throws StylesheetNotValidException {
        if(simpleSelectorsList.isEmpty()) {
            throw new StylesheetNotValidException("Selector without SimpleSelector", path);
        }
        for(int i = 0; i < simpleSelectorsList.size(); i++) {
            SimpleSelector simpleSelector = simpleSelectorsList.get(i);
            if(i == 0 && simpleSelector.getCombinator() != null) {
                simpleSelector.setCombinator(null); //Fix error
            }
            if(i != 0 && simpleSelector.getCombinator() == null) {
                throw new StylesheetNotValidException("SimpleSelector without operator!", path + " -> simpleSelector("+simpleSelector.toString()+")");
            }
        }
    }
}
