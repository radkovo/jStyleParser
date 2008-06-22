package cz.vutbr.web.csskit;

import java.util.Collections;
import java.util.List;

import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.SimpleSelector;
import cz.vutbr.web.css.StyleSheetNotValidException;

/**
 * Selector
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 * @version 1.0 * Changed according to new interface
 * 				 * Construction moved to parser
 * 				 * Rewritten toString() method 
 */
public class SelectorImpl implements Selector {
  
	protected List<SimpleSelector> simpleSelectors;
	
	public SelectorImpl() {
		this.simpleSelectors = Collections.emptyList();
	}
	
	
	
    /* input: SimpleSelector (Combinator SimpleSelector)* */
    /*
    public SelectorImpl(SimpleNode n) {
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
    */
    
	/**
	 * @return the simpleSelectors
	 */
	public List<SimpleSelector> getSimpleSelectors() {
		return simpleSelectors;
	}



	/**
	 * @param simpleSelectors the simpleSelectors to set
	 */
	public void setSimpleSelectors(List<SimpleSelector> simpleSelectors) {
		this.simpleSelectors = simpleSelectors;
	}



	public String toString(int depth) {

		StringBuilder sb = new StringBuilder();
		sb = OutputUtil.appendList(sb, simpleSelectors, OutputUtil.EMPTY_DELIM);
		
		return sb.toString();
	}
	
    @Override
    public String toString() {
    	return this.toString(0);
    }

    
    public void check(String path) throws StyleSheetNotValidException {
        if(simpleSelectors.isEmpty()) {
            throw new StyleSheetNotValidException("Selector without SimpleSelector", path);
        }
        for(int i = 0; i < simpleSelectors.size(); i++) {
            SimpleSelector simpleSelector = simpleSelectors.get(i);
            if(i == 0 && simpleSelector.getCombinator() != null) {
                simpleSelector.setCombinator(null); //Fix error
            }
            if(i != 0 && simpleSelector.getCombinator() == null) {
                throw new StyleSheetNotValidException("SimpleSelector without operator!", path + " -> simpleSelector("+simpleSelector.toString()+")");
            }
        }
    }
}
