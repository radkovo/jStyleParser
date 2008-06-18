package cz.vutbr.web.csskit;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.StylesheetNotValidException;
import cz.vutbr.web.csskit.parser.SimpleNode;

import java.util.ArrayList;

/**
 * RuleSet
 * @author Jan Svercl, VUT Brno, 2008
 */
public class RuleSetImpl implements RuleSet {

    private ArrayList<Selector> selectorsList = new ArrayList<Selector>();
    private ArrayList<Declaration> declarationsList = new ArrayList<Declaration>();

    public ArrayList<Selector> getSelectorsList() {
        return selectorsList;
    }

    public ArrayList<Declaration> getDeclarationsList() {
        return declarationsList;
    }
    
    protected RuleSetImpl(SimpleNode n) {
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
    
    public String toString(int depth) {
        String out = "";
        for(int i = 0; i < depth; i++) {
            out += "\t";
        }
        
        boolean first = true;
        for(Selector selector : selectorsList) {
            if(!first) {
                out += ", ";
            }
            else {
                first = false;
            }
            out += selector.toString();
        }
        out += " {\n";
        
        for(Declaration declaration : declarationsList) {
            out += declaration.toString(depth + 1);
        }
        
        for(int i = 0; i < depth; i++) {
            out += "\t";
        }
        out += "}\n";
        return out;
    }
    
    public void check(String path) throws StylesheetNotValidException {
        if(selectorsList.isEmpty()) {
            throw new StylesheetNotValidException("Selector is missing in rule", path);
        }
        for(Selector selector : selectorsList) {
            selector.check(path);
        }
        String pathNew = path + " -> rule(";
        boolean first = true;
        for(Selector selector : selectorsList) {
            if(!first) {
                pathNew += ", ";
            }
            else {
                first = false;
            }
            pathNew += selector.toString();
        }
        pathNew += ")";
        for(Declaration declaration : declarationsList) {
            declaration.check(pathNew);
        }
    }
}
