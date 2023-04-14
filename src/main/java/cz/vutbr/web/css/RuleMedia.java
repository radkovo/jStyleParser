package cz.vutbr.web.css;

/**
 * Contains CSS rules associated with medias. 
 * Externally provides view of collection of defined RuleBlock
 * with additional media information.
 * 
 * @author burgetr
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008,
 * 
 */
public interface RuleMedia extends RuleBlock<RuleBlock<?>>, PrettyOutput {

	/**
	 * Returns list of all media associated with this rule
	 * @return List of media
	 */
	public MediaQueryList getMediaQueries();
	
	/**
	 * Sets media associated with rules
	 * @param media Media associated
	 * @return Modified instance
	 */
	public RuleMedia setMediaQueries(MediaQueryList media);

	
}
