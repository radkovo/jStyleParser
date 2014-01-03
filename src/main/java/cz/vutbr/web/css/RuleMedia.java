package cz.vutbr.web.css;

import java.util.List;

/**
 * Contains CSS rules associated with medias. 
 * Externally provides view of collection of defined RuleSet 
 * with additional media information.
 * 
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008,
 * 
 */
public interface RuleMedia extends RuleBlock<RuleSet>, PrettyOutput {

	/**
	 * Returns list of all media associated with this rule
	 * @return List of media
	 */
	public List<String> getMedia();	
	
	/**
	 * Sets media associated with rules
	 * @param media Media associated
	 * @return Modified instance
	 */
	public RuleMedia setMedia(List<String> media);	

	
}
