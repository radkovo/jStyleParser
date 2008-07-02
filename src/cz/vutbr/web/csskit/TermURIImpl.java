package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermURI;

/**
 * TermURI
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko
 */
public class TermURIImpl extends TermImpl<String> implements TermURI {

    
    public TermURIImpl(String uri) {
        setValue(uri);
    }
    

    @Override
    public void setValue(String uri) {
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
    }
    
    @Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	if(operator!=null) sb.append(operator.value());
    	sb.append(OutputUtil.URL_OPENING).append(value).append(OutputUtil.URL_CLOSING);
    	
    	return sb.toString();
    }
}
