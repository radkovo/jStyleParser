package cz.vutbr.web.csskit;

import java.util.Collections;
import java.util.List;

import cz.vutbr.web.css.ImportURI;
import cz.vutbr.web.css.Rule;
import cz.vutbr.web.css.StyleSheet;

/**
 * CSS stylesheet, entry point
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008,
 * 
 */
public class StyleSheetImpl extends AbstractRule<Rule<?>> implements StyleSheet {
	
    protected String charset;
    protected List<ImportURI> imports;

	protected StyleSheetImpl() {
    	this.charset = null;
    	this.imports = Collections.emptyList();
    }    
    
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
    	sb = OutputUtil.appendList(sb, list, OutputUtil.NEW_LINE)
    		.append(OutputUtil.NEW_LINE);		
    	    	
    	return sb.toString();
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((charset == null) ? 0 : charset.hashCode());
		result = prime * result + ((imports == null) ? 0 : imports.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof StyleSheetImpl))
			return false;
		StyleSheetImpl other = (StyleSheetImpl) obj;
		if (charset == null) {
			if (other.charset != null)
				return false;
		} else if (!charset.equals(other.charset))
			return false;
		if (imports == null) {
			if (other.imports != null)
				return false;
		} else if (!imports.equals(other.imports))
			return false;
		return true;
	}
    
    
    
}
