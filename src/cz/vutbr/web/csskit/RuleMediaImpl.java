package cz.vutbr.web.csskit;

import cz.vutbr.web.css.Rule;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StylesheetNotValidException;
import cz.vutbr.web.csskit.parser.SimpleNode;

import java.util.ArrayList;

/**
 * RuleMedia
 * @author Jan Svercl, VUT Brno, 2008
 */
public class RuleMediaImpl implements RuleMedia {
  
    private ArrayList<String> mediaList = new ArrayList<String>();
    private ArrayList<RuleSet> rulesList = new ArrayList<RuleSet>();

    public ArrayList<String> getMediaList() {
        return mediaList;
    }

    public ArrayList<RuleSet> getRulesList() {
        return rulesList;
    }
    
    public RuleMediaImpl(SimpleNode n) {
        for(int ii = 0; ii < n.jjtGetNumChildren(); ii++) {
            SimpleNode cNode = (SimpleNode)n.jjtGetChild(ii);
            if(cNode.getType().equals("medium")) {
                String mediumName = ((SimpleNode)cNode.jjtGetChild(0)).getImage();
                mediaList.add(mediumName);
            }
            else if(cNode.getType().equals("ruleset")) {
                rulesList.add(new RuleSetImpl(cNode));
            }
        }
    }
    
    public String toString(int depth) {
        String out = "";
        for(int i = 0; i < depth; i++) {
            out += "\t";
        }
        out += "@media ";
        boolean first = true;
        for(String mName : mediaList) {
            if(!first) {
                out += ", ";
            }
            else {
                first = false;
            }
            out += mName;
        }
        for(int i = 0; i < depth; i++) {
            out += "\t";
        }
        out += " {\n";
        
        first = true;
        for(RuleSet rule : rulesList) {
            if(!first) {
                out += "\n";
            }
            else {
                first = false;
            }
            out += rule.toString(1);
        }
        out += "}\n";
        return out;
    }
    
    public void check(String path) throws StylesheetNotValidException {
        if(mediaList.isEmpty()) {
            throw new StylesheetNotValidException("Media type is missing", path);
        }
        for(String string: mediaList) {
            if(!string.matches("^(all|aural|braille|embossed|handheld|print|projection|screen|tty|tv)$")) {
                throw new StylesheetNotValidException("Unknown Media type: " + string, path);
            }
        }
        
        String pathNew = path + " -> Media(";
        boolean first = true;
        for(String mName : mediaList) {
            if(!first) {
                pathNew += ", ";
            }
            else {
                first = false;
            }
            pathNew += mName;
        }
        pathNew += ")";
        for(Rule rule: rulesList) {
            rule.check(pathNew);
        }
    }

}
