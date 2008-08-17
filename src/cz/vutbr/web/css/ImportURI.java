package cz.vutbr.web.css;


/**
 * Contains imports associated with medias. Acts as collection
 * of associated medias
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface ImportURI extends Rule<String> {
  
	/**
	 * Gets URI of import rule file
	 * @return URI of file to be imported
	 */
    public String getUri();

    /**
     * Sets URI of import rule
     * @param uri URI of file to be imported
     */
    public ImportURI setUri(String uri);

}
