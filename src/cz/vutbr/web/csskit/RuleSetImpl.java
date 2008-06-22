package cz.vutbr.web.csskit;

import java.util.Collections;
import java.util.List;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.StyleSheetNotValidException;

/**
 * RuleSet
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 * @version 1.0 * Changed according to changed interface
 * 				 * Rewritten toString() method	
 * 				 * Construction moved to parser
 */
public class RuleSetImpl implements RuleSet {

	protected List<Selector> selectors;
	protected List<Declaration> declarations;
	
	public RuleSetImpl() {
		this.selectors = Collections.emptyList();
		this.selectors = Collections.emptyList();
	}
	
	
	
	/*
    public RuleSetImpl(SimpleNode n) {
        for(int i = 0; i < n.jjtGetNumChildren(); i++) {
            SimpleNode cNode = (SimpleNode)n.jjtGetChild(i);
            
            if(cNode.getType().equals("selector")) {
                selectorsList.add(new SelectorImpl(cNode));
            }
            
            if(cNode.getType().equals("declaration") && cNode.jjtGetNumChildren() > 0) {
                declarationsList.add(new DeclarationImpl(cNode));
            }
        }
    }
    */
    
    /**
	 * @return the selectors
	 */
	public List<Selector> getSelectors() {
		return selectors;
	}



	/**
	 * @param selectors the selectors to set
	 */
	public void setSelectors(List<Selector> selectors) {
		this.selectors = selectors;
	}



	/**
	 * @return the declarations
	 */
	public List<Declaration> getDeclarations() {
		return declarations;
	}



	/**
	 * @param declarations the declarations to set
	 */
	public void setDeclarations(List<Declaration> declarations) {
		this.declarations = declarations;
	}



	@Override
    public String toString() {
    	return this.toString(0);
    }
    
    
    public String toString(int depth) {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	// append selectors
    	sb = OutputUtil.appendTimes(sb, OutputUtil.DEPTH_DELIM, depth);
    	sb = OutputUtil.appendList(sb, selectors, OutputUtil.SELECTOR_DELIM);

    	// append rules (declarations)
    	sb.append(OutputUtil.RULE_OPENING);
    	sb = OutputUtil.appendList(sb, declarations, OutputUtil.EMPTY_DELIM, depth + 1); 
    	sb = OutputUtil.appendTimes(sb, OutputUtil.DEPTH_DELIM, depth);
        sb.append(OutputUtil.RULE_CLOSING);
        
        return sb.toString();
    }
    
    public void check(String path) throws StyleSheetNotValidException {
        
    	if(selectors.isEmpty()) {
            throw new StyleSheetNotValidException("Selector is missing in rule", path);
        }
        for(Selector selector : selectors) {
            selector.check(path);
        }
        String pathNew = path + " -> rule(";
        boolean first = true;
        for(Selector selector : selectors) {
            if(!first) {
                pathNew += ", ";
            }
            else {
                first = false;
            }
            pathNew += selector.toString();
        }
        pathNew += ")";
        for(Declaration declaration : declarations) {
            declaration.check(pathNew);
        }
    }
}
