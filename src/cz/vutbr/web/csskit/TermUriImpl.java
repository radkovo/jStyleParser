package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermUri;

/**
 * TermUri
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko
 */
public class TermUriImpl extends TermImpl implements TermUri {

    protected String uri;

    
    public TermUriImpl(String uri) {
        setUri(uri);
    }
    
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        if(uri == null) 
            throw new IllegalArgumentException("Invalid uri for TermURI(null)");
        
        // sanity check
        uri = uri.replaceAll("^url\\(", "")
        	  .replaceAll("\\)$", "")
        	  .replaceAll("^'", "")
        	  .replaceAll("^\"", "")
        	  .replaceAll("'$", "")
        	  .replaceAll("\"$", "");
        
        this.uri = uri;
    }
    
    @Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	if(operator!=null) sb.append(operator.value());
    	sb.append(OutputUtil.URL_OPENING).append(uri).append(OutputUtil.URL_CLOSING);
    	
    	return sb.toString();
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
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
		if (!(obj instanceof TermUriImpl))
			return false;
		final TermUriImpl other = (TermUriImpl) obj;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	} 
}
