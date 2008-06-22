package cz.vutbr.web.css;

import java.util.List;

/**
 * Contains imports associated with medias
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 * @version 1.0 * Now it is implementation of Rule
 * 				 * Added setters
 */
public interface ImportURI extends Rule {
  
	/**
	 * Gets URI of import rule file
	 * @return URI of file to be imported
	 */
    public String getUri();

    /**
     * Sets URI of import rule
     * @param uri URI of file to be imported
     */
    public void setUri(String uri);

    /**
     * Gets media associated with import
     * @return List of medias for which import is valid
     */
    public List<String> getMedias();
    
    /**
     * Sets media associated with import
     * @param medias List of medias for which import is valid
     */
    public void setMedias(List<String> medias);
    
}
