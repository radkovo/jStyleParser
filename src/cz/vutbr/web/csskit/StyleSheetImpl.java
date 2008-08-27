package cz.vutbr.web.csskit;

import cz.vutbr.web.css.RuleBlock;
import cz.vutbr.web.css.StyleSheet;

/**
 * CSS stylesheet, entry point
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008,
 * 
 */
public class StyleSheetImpl extends AbstractRule<RuleBlock<?>> implements StyleSheet {
	
    protected String charset;

	protected StyleSheetImpl() {
    	this.charset = null;
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
    
    @Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	// append character set @charset
    	if(charset != null && !"".equals(charset))
    		sb.append(OutputUtil.CHARSET_KEYWORD)
    		  .append(OutputUtil.CHARSET_OPENING)
    		  .append(charset)
    		  .append(OutputUtil.CHARSET_CLOSING);
    	
    	// append rules
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
		return true;
	}

	
}
