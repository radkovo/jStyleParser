package cz.vutbr.web.csskit;

import cz.vutbr.web.css.ImportURI;

/**
 * URI with stylesheet to be imported
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 */
public class ImportURIImpl extends AbstractRule<String> implements ImportURI {
  
	/** URI of file to be imported */
    protected String uri;
    
    /** 
     * Creates empty ImportURI instance 
     * 
     */
    protected ImportURIImpl() {
    	this.uri = "";
    }
    
    public String getUri() {
        return uri;
    }

    public ImportURI setUri(String uri) {

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
		if (!(obj instanceof ImportURIImpl))
			return false;
		ImportURIImpl other = (ImportURIImpl) obj;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}    

    
    
}
