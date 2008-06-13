package cz.vutbr.web.cssKit;

import cz.vutbr.web.css.ImportURI;
import cz.vutbr.web.css.Rule;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StylesheetNotValidException;
import cz.vutbr.web.cssKit.parser.SimpleNode;
import java.util.ArrayList;

/**
 * StyleSheet
 * @author Jan Svercl, VUT Brno, 2008
 */
public class StyleSheetImpl implements StyleSheet {
  
    private String charset;
    private ArrayList<ImportURI> importList = new ArrayList<ImportURI>();
    private ArrayList<Rule> rulesList = new ArrayList<Rule>();

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public ArrayList<ImportURI> getImportList() {
        return importList;
    }

    public ArrayList<Rule> getRulesList() {
        return rulesList;
    }
    
    public StyleSheetImpl() {
    }
  
    protected StyleSheetImpl(SimpleNode n) {
        for(int i = 0; i < n.jjtGetNumChildren(); i++) {
            SimpleNode cNode = (SimpleNode)n.jjtGetChild(i);
            
            /* Charset node */
            if(cNode.getType().equals("charset")) {
                String s = ((SimpleNode)cNode.jjtGetChild(0)).getImage();
                s = s.replaceAll("^'", "");
                s = s.replaceAll("^\"", "");
                s = s.replaceAll("'$", "");
                s = s.replaceAll("\"$", "");
                setCharset(s);
            }
            
            /* @media rule */
            if(cNode.getType().equals("media")) {
                rulesList.add(new RuleMediaImpl(cNode));
            }
            
            /* Generic rule */
            if(cNode.getType().equals("ruleset")) {
                rulesList.add(new RuleSetImpl(cNode));
            }
            
            /* @page rule */
            if(cNode.getType().equals("page")) {
                rulesList.add(new RulePageImpl(cNode));
            }
            
            /* @import rule */
            if(cNode.getType().equals("import_a")) {
                importList.add(new ImportURIImpl(cNode));
            }
        }
    }
    
    @Override
    public String toString() {
        String out = "";
        if(charset != null) {
            out += "@charset'"+ charset +"'\n";
            out += "\n";
        }
        if(!importList.isEmpty()) {
            for(ImportURI importUri : importList) {
                out += importUri.toString();
            }
            out += "\n";
        }
        for(Rule rule : rulesList) {
            out += rule.toString(0);
            out += "\n";
        }
        return out;
    }
    
    public void check() throws StylesheetNotValidException {
        for(ImportURI importUri : importList) {
            importUri.check("Stylesheet");
        }
        for(Rule rule : rulesList) {
            rule.check("Stylesheet");
        }
    }
}
