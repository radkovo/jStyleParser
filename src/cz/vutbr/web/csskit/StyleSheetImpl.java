package cz.vutbr.web.csskit;

import java.util.Collections;
import java.util.List;

import cz.vutbr.web.css.ImportURI;
import cz.vutbr.web.css.Rule;
import cz.vutbr.web.css.StyleSheet;

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
	
    @Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	// append character set @charset
    	if(charset != null && !"".equals(charset))
    		sb.append(OutputUtil.CHARSET_KEYWORD)
    		  .append(OutputUtil.CHARSET_OPENING)
    		  .append(charset)
    		  .append(OutputUtil.CHARSET_CLOSING);
    	
    	// append @import rules
    	// and other rules
    	sb = OutputUtil.appendList(sb, imports, OutputUtil.EMPTY_DELIM)
    		.append(OutputUtil.NEW_LINE);
    	sb = OutputUtil.appendList(sb, rules, OutputUtil.NEW_LINE)
    		.append(OutputUtil.NEW_LINE);		
    	    	
    	return sb.toString();
    }   
    
}
