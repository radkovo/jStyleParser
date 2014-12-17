package cz.vutbr.web.css;

/**
 * Special case of rule, where rule is meant to be comparable
 * with other rules to determine priority of CSS declarations
 * @author kapy
 *
 * @param <T> Internal content of rule
 */
public interface RuleBlock<T> extends Rule<T>{

	/**
	 * Sets the owner style sheet for this rule.
	 * @param sheet The stylesheet where this rule is contained.
	 */
	public void setStyleSheet(StyleSheet sheet);
	
	/**
	 * Returns the stylesheet where the rule is contained.
	 * @return The stylesheet.
	 */
	public StyleSheet getStyleSheet();
	
}
