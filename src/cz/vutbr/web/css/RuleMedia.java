package cz.vutbr.web.css;

import java.util.List;

/**
 * Contains CSS rules associated with medias
 * @author Jan Svercl, VUT Brno, 2008,
 * 			modified by Karel Piwko, 2008
 * @version 1.0 * Added setter methods
 * 				 * Renamed methods to reach naming consistency
 */
public interface RuleMedia extends Rule, PrettyOutput {

	/**
	 * Returns list of all medias associated with this rule
	 * @return List of media
	 */
	public List<String> getMedias();
	
	/**
	 * Returns list of all rules internally stored in this rule
	 * @return List of rules
	 */
	public List<RuleSet> getRules();
	
	/**
	 * Sets medias associated with rules
	 * @param medias Medias associated
	 */
	public void setMedias(List<String> medias);
	
	/**
	 * Sets rules associated with CSS definition element
	 * @param rules Rules associated
	 */
	public void setRules(List<RuleSet> rules);
	
}
