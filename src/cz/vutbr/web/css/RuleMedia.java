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
public interface RuleMedia extends Rule<RuleSet>, PrettyOutput {

	/**
	 * Returns list of all medias associated with this rule
	 * @return List of media
	 */
	public List<String> getMedias();	
	
	/**
	 * Sets medias associated with rules
	 * @param medias Medias associated
	 * @return Modified instance
	 */
	public RuleMedia setMedias(List<String> medias);	

	
}
