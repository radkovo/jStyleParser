package cz.vutbr.web.csskit;

import java.util.List;

import cz.vutbr.web.css.CSSNodeVisitor;
import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleSet;

/**
 * Basic holder of declarations with CSS selectors
 * 
 * @author kapy
 */
public class RuleSetImpl extends AbstractRuleBlock<Declaration> implements RuleSet {

	protected CombinedSelector[] selectors;
	
	protected RuleSetImpl() {
		super();
		this.selectors = new CombinedSelector[0];
	}
	
	public RuleSetImpl(CombinedSelector[] selectors) {
		super();
		this.selectors = selectors;
	}
	
	
	/**
	 * Accept method required by the visitor pattern for traversing the CSS Tree. 
	 * 
	 * @param visitor
	 * 	The visitor interface
	 * @return
	 * 	The current CSS Object
	 */
	@Override
	public Object accept(CSSNodeVisitor visitor) {
		return visitor.visit(this);
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
	public CombinedSelector[] getSelectors() {
		return selectors;
	}

	/**
	 * @param selectors the selectors to set
	 * @return Modified instance
	 */
	public RuleSet setSelectors(List<CombinedSelector> selectors) {
		this.selectors = selectors.toArray(new CombinedSelector[selectors.size()]);
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
    	sb = OutputUtil.appendArray(sb, selectors, OutputUtil.SELECTOR_DELIM);

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
