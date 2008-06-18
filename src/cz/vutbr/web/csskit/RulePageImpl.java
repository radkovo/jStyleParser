package cz.vutbr.web.csskit;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RulePage;
import cz.vutbr.web.css.StylesheetNotValidException;
import cz.vutbr.web.csskit.parser.SimpleNode;

import java.util.ArrayList;

/**
 * RulePage
 * @author Jan Svercl, VUT Brno, 2008
 */
public class RulePageImpl implements RulePage {

    private String pseudo;
    private ArrayList<Declaration> declarationsList = new ArrayList<Declaration>();

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public ArrayList<Declaration> getDeclarationsList() {
        return declarationsList;
    }
    
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
    
    public String toString(int depth) {
        String out = "";
        out += "@page";
        if(pseudo != null) {
            out += ":" + pseudo;
        }
        out += " {\n";

        for(Declaration declaration : declarationsList) {
            out += declaration.toString(depth + 1);
        }
        
        out += "}\n";
        return out;
    }
    
    public void check(String path) throws StylesheetNotValidException {
        String pathNew = path + " -> page(";
        if(pseudo != null) {
            pathNew += pseudo;
        }
        pathNew += ")";
        for(Declaration declaration : declarationsList) {
            declaration.check(pathNew);
        }
    }
}
