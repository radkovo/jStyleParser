package cz.vutbr.web.csskit;

import java.net.URL;

import cz.vutbr.web.css.TermURI;

/**
 * TermURIImpl
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 */
public class TermURIImpl extends TermImpl<String> implements TermURI {

    protected URL base;
    
	protected TermURIImpl() {
	}

    @Override
    public TermURI setValue(String uri) {
        if(uri == null) 
            throw new IllegalArgumentException("Invalid uri for TermURI(null)");
        
        /* this shlould be done by parser
        uri = uri.replaceAll("^url\\(", "")
        	  .replaceAll("\\)$", "")
        	  .replaceAll("^'", "")
        	  .replaceAll("^\"", "")
        	  .replaceAll("'$", "")
        	  .replaceAll("\"$", "");
        */
        
        this.value = uri;
        return this;
    }
    
    public TermURI setBase(URL base)
    {
        this.base = base;
        return this;
    }
    
    public URL getBase()
    {
        return base;
    }
    
    @Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	if(operator!=null) sb.append(operator.value());
    	sb.append(OutputUtil.URL_OPENING).append(value).append(OutputUtil.URL_CLOSING);
    	
    	return sb.toString();
    }
}
