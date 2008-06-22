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
}
