/**
 * KeyframeBlockImpl.java
 *
 * Created on 31. 1. 2019, 16:25:42 by burgetr
 */
package cz.vutbr.web.csskit;

import java.util.ArrayList;
import java.util.List;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.KeyframeBlock;
import cz.vutbr.web.css.TermPercent;

/**
 * 
 * @author burgetr
 */
public class KeyframeBlockImpl extends AbstractRuleBlock<Declaration> implements KeyframeBlock {

    private List<TermPercent> percentages;
    
    public KeyframeBlockImpl() {
        super();
        percentages = new ArrayList<>();
    }
    
    public KeyframeBlockImpl(List<TermPercent> percentages) {
        super();
        percentages = new ArrayList<>(percentages);
    }
    
    @Override
    public List<TermPercent> getPercentages() {
        return percentages;
    }

    @Override
    public KeyframeBlock setPercentages(List<TermPercent> percentages) {
        this.percentages = new ArrayList<>(percentages);
        return this;
    }

    @Override
    public String toString(int depth) {
        StringBuilder sb = new StringBuilder();
        
        // append selectors
        sb = OutputUtil.appendTimes(sb, OutputUtil.DEPTH_DELIM, depth);
        sb = OutputUtil.appendList(sb, percentages, OutputUtil.SELECTOR_DELIM);

        // append rules (declarations)
        sb.append(OutputUtil.RULE_OPENING);
        sb = OutputUtil.appendList(sb, list, OutputUtil.EMPTY_DELIM, depth + 1); 
        sb = OutputUtil.appendTimes(sb, OutputUtil.DEPTH_DELIM, depth);
        sb.append(OutputUtil.RULE_CLOSING);
        
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((percentages == null) ? 0 : percentages.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        KeyframeBlockImpl other = (KeyframeBlockImpl) obj;
        if (percentages == null)
        {
            if (other.percentages != null) return false;
        }
        else if (!percentages.equals(other.percentages)) return false;
        return true;
    }
    
}
