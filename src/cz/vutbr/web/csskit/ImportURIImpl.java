package cz.vutbr.web.csskit;

import java.util.Collections;
import java.util.List;

import cz.vutbr.web.css.ImportURI;
import cz.vutbr.web.css.StyleSheetNotValidException;

/**
 * Implementation of ImportURI
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko
 * @version 1.0 * Rewritten according to new interface
 * 				 * Construction moved to parser 
 * 				 * Added equals() and hashCode() methods
 */
public class ImportURIImpl implements ImportURI {
  
	/** URI of file to be imported */
    protected String uri;
    
    /** List of associated medias */
    protected List<String> medias;

    /** 
     * Creates empty ImportURI instance 
     * 
     */
    public ImportURIImpl() {
    	this.uri = "";
    	this.medias = Collections.emptyList();
    }
    
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {

    	// sanity check
    	if(uri == null) 
        	return; 

    	this.uri = uri.replaceAll("^url\\(", "")
    				  .replaceAll("\\)$", "")
    				  .replaceAll("^'", "")
    				  .replaceAll("^\"", "")
    				  .replaceAll("'$", "")
    				  .replaceAll("\"$", "");
    }
    
    public List<String> getMedias() {
		return medias;
	}

	public void setMedias(List<String> medias) {
		this.medias = medias;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((medias == null) ? 0 : medias.hashCode());
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
		if (obj == null)
			return false;
		if (!(obj instanceof ImportURIImpl))
			return false;
		ImportURIImpl other = (ImportURIImpl) obj;
		if (medias == null) {
			if (other.medias != null)
				return false;
		} else if (!medias.equals(other.medias))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}

	public String toString(int depth) {
		
		StringBuilder sb = new StringBuilder();
    	
    	sb.append(OutputUtil.IMPORT_KEYWORD).append(OutputUtil.URL_OPENING)
    			.append(uri).append(OutputUtil.URL_CLOSING);
    	
    	// append medias
    	if(medias.size()!=0)
    		sb.append(OutputUtil.SPACE_DELIM);
    	sb = OutputUtil.appendList(sb, medias, OutputUtil.MEDIA_DELIM); 
    	sb.append(OutputUtil.LINE_CLOSING);
    	
    	return sb.toString();
		
	}

    @Override
    public String toString() {
    	return toString(0);
    }
    
    public void check(String path) throws StyleSheetNotValidException {
        //Nothing
    }
}
