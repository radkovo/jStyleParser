/**
 * 
 */
package cz.vutbr.web.css;

/**
 * Contains collection of CSS declarations specified for a keyframes rule.
 * 
 * @author burgetr
 */
public interface RuleKeyframes extends RuleBlock<KeyframeBlock>, PrettyOutput {
    
    /**
     * Gets name of the keyframe list
     * @return Name of the keyframe list
     */
    public String getName();
    
    /**
     * Sets name of the keyframe list
     * @param name New name of the keyframe list
     * @return Modified instance
     */
    public RuleKeyframes setName(String name);

}
