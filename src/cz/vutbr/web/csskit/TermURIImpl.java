package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermURI;

/**
 * TermURIImpl
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 */
public class TermURIImpl extends TermImpl<String> implements TermURI {

	protected TermURIImpl() {
	}

    @Override
    public TermURI setValue(String uri) {
        if(uri == null) 
            throw new IllegalArgumentException("Invalid uri for TermURI(null)");
        
        // sanity check
        uri = uri.replaceAll("^url\\(", "")
        	  .replaceAll("\\)$", "")
        	  .replaceAll("^'", "")
        	  .replaceAll("^\"", "")
        	  .replaceAll("'$", "")
        	  .replaceAll("\"$", "");
        
        this.value = uri;
        return this;
    }
    
    @Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	if(operator!=null) sb.append(operator.value());
    	sb.append(OutputUtil.URL_OPENING).append(value).append(OutputUtil.URL_CLOSING);
    	
    	return sb.toString();
    }
}
