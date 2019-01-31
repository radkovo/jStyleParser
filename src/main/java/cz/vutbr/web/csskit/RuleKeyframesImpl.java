/**
 * RuleKeyframesImpl.java
 *
 * Created on 31. 1. 2019, 16:37:57 by burgetr
 */
package cz.vutbr.web.csskit;

import cz.vutbr.web.css.KeyframeBlock;
import cz.vutbr.web.css.RuleKeyframes;

/**
 * 
 * @author burgetr
 */
public class RuleKeyframesImpl extends AbstractRuleBlock<KeyframeBlock> implements RuleKeyframes {

    private String name;
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public RuleKeyframes setName(String name) {
        this.name = new String(name);
        return this;
    }

    @Override
    public String toString() {
        return this.toString(0);
    }
    
    public String toString(int depth) {
        
        StringBuilder sb = new StringBuilder();
        
        // append medias
        sb = OutputUtil.appendTimes(sb, OutputUtil.DEPTH_DELIM, depth);
        sb.append(OutputUtil.KEYFRAMES_KEYWORD);
        sb.append(name);
        
        // append rules
        sb = OutputUtil.appendTimes(sb, OutputUtil.DEPTH_DELIM, depth);
        sb.append(OutputUtil.RULE_OPENING);
        sb = OutputUtil.appendList(sb, list, OutputUtil.RULE_DELIM, depth + 1);
        sb.append(OutputUtil.RULE_CLOSING);
        
        return sb.toString();
    }
}
