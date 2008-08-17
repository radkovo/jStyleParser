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
 * @author Jan Svercl, VUT Brno, 2008
 */
public class RuleSetImpl extends AbstractRule<Declaration> implements RuleSet {

	private int order;
	
	protected List<CombinedSelector> selectors;
	
	protected RuleSetImpl(int order) {
		this.selectors = Collections.emptyList();
		this.order = order;
	}
	
	/**
	 * Shallow copy constructor
	 * @param rs RuleSet to share selectors and declarations with 
	 */
	protected RuleSetImpl(int order, RuleSet rs) {
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

	public int compareTo(RuleSet o) {
		
		if(o.getClass()!=RuleSetImpl.class)
			throw new IllegalArgumentException("Allowed to compare RuleSetImpl only");
		
		RuleSetImpl other = (RuleSetImpl) o;
		
		if (order < other.order)	return -1;
		else if (order > other.order)	return 1;
		
		return 0;
		
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
		result = prime * result + order;
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
		if (order != other.order)
			return false;
		if (selectors == null) {
			if (other.selectors != null)
				return false;
		} else if (!selectors.equals(other.selectors))
			return false;
		return true;
	} 
    
    
    
    
    
}
