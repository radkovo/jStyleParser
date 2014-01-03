package cz.vutbr.web.css;


/**
 * Contains imports associated with medias. Acts as collection
 * of associated medias
 * 
 * @author kapy
 */
@Deprecated
public interface RuleImport extends RuleBlock<String>, PrettyOutput {
  
	/**
	 * Gets URI of import rule file
	 * @return URI of file to be imported
	 */
    public String getURI();

    /**
     * Sets URI of import rule
     * @param uri URI of file to be imported
     */
    public RuleImport setURI(String uri);

}
