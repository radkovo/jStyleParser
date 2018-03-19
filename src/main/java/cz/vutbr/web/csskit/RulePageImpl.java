package cz.vutbr.web.csskit;

import java.util.ArrayList;
import java.util.List;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.PrettyOutput;
import cz.vutbr.web.css.Rule;
import cz.vutbr.web.css.RuleMargin;
import cz.vutbr.web.css.RulePage;
import cz.vutbr.web.css.Selector;

/**
 * Wrap of declarations bounded with a page rule 
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 * @author Bert Frees, 2012
 */
public class RulePageImpl extends AbstractRuleBlock<Rule<?>> implements RulePage {

	protected String name;
	protected Selector.PseudoPage pseudo;
	
	protected RulePageImpl() {
		super();
		this.name = null;
		this.pseudo = null;
		replaceAll(new ArrayList<Rule<?>>());
	}
	
	/**
	 * Gets name of the page
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name of the page
	 * @param name The name to set
	 * @return Modified instance
	 */
	public RulePage setName(String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * Gets pseudo-class of the page
	 */
	public Selector.PseudoPage getPseudo() {
		return pseudo;
	}

	/**
	 * Sets pseudo-class of the page
	 * @param pseudo The pseudo-class to set
	 * @return Modified instance
	 */
	public RulePage setPseudo(Selector.PseudoPage pseudo) {
		this.pseudo = pseudo;
		return this;
	}

	@Override
	public boolean add(Rule<?> element) {
		if (element instanceof Declaration || element instanceof RuleMargin)
			return super.add(element);
		else
			throw new IllegalArgumentException("Element must be either a Declaration or a RuleMargin");
	}

	@Override 
	public String toString() {
		return this.toString(0);
	}
	
    public String toString(int depth) {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append(OutputUtil.PAGE_KEYWORD);
    	if(name != null && !"".equals(name))
    		sb.append(OutputUtil.SPACE_DELIM).append(name);
    	if(pseudo != null)
    		sb.append(pseudo.toString());
    	
    	// append declarations and margin rules
    	sb.append(OutputUtil.RULE_OPENING);
    	@SuppressWarnings({ "unchecked", "rawtypes" })
        List<PrettyOutput> rules = (List)list;
    	sb = OutputUtil.appendList(sb, rules, OutputUtil.EMPTY_DELIM, depth + 1);
    	sb.append(OutputUtil.RULE_CLOSING).append(OutputUtil.PAGE_CLOSING);
    
    	return sb.toString();
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
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
		if (!(obj instanceof RulePageImpl))
			return false;
		RulePageImpl other = (RulePageImpl) obj;
		if (pseudo == null) {
			if (other.pseudo != null)
				return false;
		} else if (!pseudo.equals(other.pseudo))
			return false;
		return true;
	}
    
    
   
}
