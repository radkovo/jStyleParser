package cz.vutbr.web.csskit;

import java.net.URL;

import org.unbescape.css.CssEscape;

import cz.vutbr.web.css.CSSNodeVisitor;
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
	
	/**
	 * Accept method required by the visitor pattern for traversing the CSS Tree. 
	 * 
	 * @param visitor
	 * 	The visitor interface
	 * @return
	 * 	The current CSS Object
	 */
	@Override
	public Object accept(CSSNodeVisitor visitor) {
		return visitor.visit(this);
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
    	sb.append(OutputUtil.URL_OPENING)
    	    .append(CssEscape.escapeCssString(value))
    	    .append(OutputUtil.URL_CLOSING);
    	
    	return sb.toString();
    }
}
