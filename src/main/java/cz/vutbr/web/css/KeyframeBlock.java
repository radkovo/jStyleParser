/**
 * KeyframeBlock.java
 *
 * Created on 31. 1. 2019, 16:20:34 by burgetr
 */
package cz.vutbr.web.css;

import java.util.List;

/**
 * Holds a set of keyframe declarations.
 * 
 * @author burgetr
 */
public interface KeyframeBlock extends RuleBlock<Declaration>, PrettyOutput {
    
    /**
     * Reads the percentages used as the keyframe selectors.
     * @return a list of selector percentages
     */
    public List<TermPercent> getPercentages();
    
    /**
     * Sets the percentages used as the keyframe selectors.
     * @return the modified instance
     */
    public KeyframeBlock setPercentages(List<TermPercent> percentages);

}
