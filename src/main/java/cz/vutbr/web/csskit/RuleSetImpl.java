package cz.vutbr.web.csskit;

import java.util.Collections;
import java.util.List;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.CombinedSelector;

/**
 * Basic holder of declarations with CSS selectors
 * 
 * @author kapy
 */
public class RuleSetImpl extends AbstractRuleBlock<Declaration> implements RuleSet {

	protected List<CombinedSelector> selectors;
	
	protected RuleSetImpl() {
		super();
		this.selectors = Collections.emptyList();
	}
	
	/**
	 * Shallow copy constructor
	 * @param rs RuleSet to share selectors and declarations with 
	 */
	protected RuleSetImpl(RuleSet rs) {
		super();
		this.selectors = rs.getSelectors();
		this.replaceAll(rs.asList());
	}
	
	
    /**
	 * @return the selectors
	 */
	public List<CombinedSelector> getSelectors() {
		return selectors;
	}

	/**
	 * @param selectors the selectors to set
	 * @return Modified instance
	 */
	public RuleSet setSelectors(List<CombinedSelector> selectors) {
		this.selectors = selectors;
		return this;
	}

	@Override
    public String toString() {
    	return this.toString(0);
    }
    
    
    public String toString(int depth) {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	// append selectors
    	sb = OutputUtil.appendTimes(sb, OutputUtil.DEPTH_DELIM, depth);
    	sb = OutputUtil.appendList(sb, selectors, OutputUtil.SELECTOR_DELIM);

    	// append rules (declarations)
    	sb.append(OutputUtil.RULE_OPENING);
    	sb = OutputUtil.appendList(sb, list, OutputUtil.EMPTY_DELIM, depth + 1); 
    	sb = OutputUtil.appendTimes(sb, OutputUtil.DEPTH_DELIM, depth);
        sb.append(OutputUtil.RULE_CLOSING);
        
        return sb.toString();
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((selectors == null) ? 0 : selectors.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof RuleSetImpl))
			return false;
		RuleSetImpl other = (RuleSetImpl) obj;
		if (selectors == null) {
			if (other.selectors != null)
				return false;
		} else if (!selectors.equals(other.selectors))
			return false;
		return true;
	}

	
    
    
    
}
