package cz.vutbr.web.csskit;

import cz.vutbr.web.css.RuleImport;

/**
 * URI with style sheet to be imported.
 * Since this is never used in new parser where imports 
 * are directly included, this is obsolete
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 */
@Deprecated
public class RuleImportImpl extends AbstractRuleBlock<String> implements RuleImport {
  
	/** URI of file to be imported */
    protected String uri;
    
    /** 
     * Creates empty RuleImport instance 
     * @param priority Priority mark
     */
    protected RuleImportImpl() {
    	super();
    	this.uri = "";
    }
    
    public String getURI() {
        return uri;
    }

    public RuleImport setURI(String uri) {

    	// sanity check
    	if(uri == null) 
        	return this; 

    	this.uri = uri.replaceAll("^url\\(", "")
    				  .replaceAll("\\)$", "")
    				  .replaceAll("^'", "")
    				  .replaceAll("^\"", "")
    				  .replaceAll("'$", "")
    				  .replaceAll("\"$", "");
    	return this;
    }
    	
	public String toString(int depth) {
		
		StringBuilder sb = new StringBuilder();
    	
    	sb.append(OutputUtil.IMPORT_KEYWORD).append(OutputUtil.URL_OPENING)
    			.append(uri).append(OutputUtil.URL_CLOSING);
    	
    	// append medias
    	if(list.size()!=0) sb.append(OutputUtil.SPACE_DELIM);
    	sb = OutputUtil.appendList(sb, list, OutputUtil.MEDIA_DELIM); 
    	sb.append(OutputUtil.LINE_CLOSING);
    	
    	return sb.toString();
		
	}
	
    @Override
    public String toString() {
    	return toString(0);
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
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
		if (!(obj instanceof RuleImportImpl))
			return false;
		RuleImportImpl other = (RuleImportImpl) obj;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}    

}
