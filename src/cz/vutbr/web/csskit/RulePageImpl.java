package cz.vutbr.web.csskit;

import java.util.Collections;
import java.util.List;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RulePage;
import cz.vutbr.web.css.StyleSheetNotValidException;

/**
 * RulePage
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 * @version 1.0 * Changed according to new interface
 * 				 * Construction moved to parser
 * 				 * Rewritten toString() method 
 */
public class RulePageImpl implements RulePage {

	protected String pseudo;
	protected List<Declaration> declarations;
	
	public RulePageImpl() {
		this.pseudo = "";
		this.declarations = Collections.emptyList();
	}
	
	/*
    public RulePageImpl(SimpleNode n) {
        for(int i = 0; i < n.jjtGetNumChildren(); i++) {
            SimpleNode cNode = (SimpleNode)n.jjtGetChild(i);
            
            if(cNode.getType().equals("declaration") && cNode.jjtGetNumChildren() > 0) {
                declarationsList.add(new DeclarationImpl(cNode));
            }
            
            if(cNode.getType().equals("pseudo_page")) {
                pseudo = ((SimpleNode)cNode.jjtGetChild(0)).getImage();
            }
        }
    }
    */
    
	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
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
    	
    	sb.append(OutputUtil.PAGE_KEYWORD);
    	if(pseudo != null || !"".equals(pseudo))
    		sb.append(OutputUtil.PAGE_OPENING).append(pseudo);
    	
    	// append declarations
    	sb.append(OutputUtil.RULE_OPENING);
    	sb = OutputUtil.appendList(sb, declarations, OutputUtil.EMPTY_DELIM, depth + 1);
    	sb.append(OutputUtil.RULE_CLOSING).append(OutputUtil.PAGE_CLOSING);
    
    	return sb.toString();
    }
    
    public void check(String path) throws StyleSheetNotValidException {
        String pathNew = path + " -> page(";
        if(pseudo != null) {
            pathNew += pseudo;
        }
        pathNew += ")";
        for(Declaration declaration : declarations) {
            declaration.check(pathNew);
        }
    }
}
