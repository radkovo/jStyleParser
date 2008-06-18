package cz.vutbr.web.csskit;

import java.util.Collections;
import java.util.List;

import cz.vutbr.web.css.ImportURI;
import cz.vutbr.web.css.Rule;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StylesheetNotValidException;

/**
 * StyleSheet
 * @author Jan Svercl, VUT Brno, 2008,
 * 			modified by Karel Piwko
 * @version 1.0 * Changed constructor
 * 				 * Optimized and repaired toString() method
 * 				 * Implemented new operations added to interface
 * 				 * Changed visibility of attributes
 * 
 */
public class StyleSheetImpl implements StyleSheet {
	
    protected String charset;
    protected List<ImportURI> imports;
    protected List<Rule> rules;

    public String getCharset() {
        return charset;
    }

    /**
     * Sets character set of StyleSheet.
     * If it contains invalid characters, replaces them.
     * @param charset Text representation of character set 
     */
    public void setCharset(String charset) {
    	
    	// sanity check for charset
    	if(charset==null || "".equals(charset))
    		return;
    
    	charset = charset.replaceAll("^'", "")
    		   .replaceAll("^\"", "")
    		   .replaceAll("'$", "")
    		   .replaceAll("\"$", "");
    		   
        this.charset = charset;
    }    
    
    public List<ImportURI> getImports() {
		return imports;
	}

	public void setImports(List<ImportURI> imports) {
		this.imports = imports;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public StyleSheetImpl() {
    	this.charset = null;
    	this.imports = Collections.emptyList();
    	this.rules = Collections.emptyList();
    }
    /*
    public StyleSheetImpl(SimpleNode n) {   
    	
        for(int i = 0; i < n.jjtGetNumChildren(); i++) {
            SimpleNode cNode = (SimpleNode)n.jjtGetChild(i);
            
            //
            if(cNode.getType().equals("charset")) {
                String s = ((SimpleNode)cNode.jjtGetChild(0)).getImage();
                s = s.replaceAll("^'", "");
                s = s.replaceAll("^\"", "");
                s = s.replaceAll("'$", "");
                s = s.replaceAll("\"$", "");
                setCharset(s);
            }
            
            // @media rule 
            if(cNode.getType().equals("media")) {
                rulesList.add(new RuleMediaImpl(cNode));
            }
            
            // Generic rule 
            if(cNode.getType().equals("ruleset")) {
                rulesList.add(new RuleSetImpl(cNode));
            }
            
            // @page rule 
            if(cNode.getType().equals("page")) {
                rulesList.add(new RulePageImpl(cNode));
            }
            
            // @import rule 
            if(cNode.getType().equals("import_a")) {
                importList.add(new ImportURIImpl(cNode));
            }
        }
    	
    }
    */
	
    @Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	// append character set @charset
    	if(charset != null && !"".equals(charset))
    		sb.append("@charset \"").append(charset).append("\";\n\n");
    	
    	// append @import rules
    	for(ImportURI uri: imports)
    		sb.append(uri.toString()).append("\n");
    	
    	// append other rules
    	for(Rule rule: rules)
    		sb.append(rule.toString(0)).append("\n");
    	    	
    	return sb.toString();
    }
    
    public void check() throws StylesheetNotValidException {
    	
        for(ImportURI importUri : imports) {
            importUri.check("Stylesheet");
        }
        for(Rule rule : rules) {
            rule.check("Stylesheet");
        }
    }
}
