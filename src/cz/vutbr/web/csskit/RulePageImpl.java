package cz.vutbr.web.csskit;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RulePage;

/**
 * Wrap of declarations bounded with pseudo-page 
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 */
public class RulePageImpl extends AbstractRuleBlock<Declaration> implements RulePage {

	protected String pseudo;
	
	protected RulePageImpl(Priority priority) {
		super(priority);
		this.pseudo = null;
	}
	
	/**
	 * Gets name of pseudopage
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @param pseudo the pseudo to set
	 * @return Modified instance
	 */
	public RulePage setPseudo(String pseudo) {
		this.pseudo = pseudo;
		return this;
	}

	@Override 
	public String toString() {
		return this.toString(0);
	}
	
    public String toString(int depth) {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append(OutputUtil.PAGE_KEYWORD).append(OutputUtil.SPACE_DELIM);
    	if(pseudo != null && !"".equals(pseudo))
    		sb.append(OutputUtil.PAGE_OPENING).append(pseudo);
    	
    	// append declarations
    	sb.append(OutputUtil.RULE_OPENING);
    	sb = OutputUtil.appendList(sb, list, OutputUtil.EMPTY_DELIM, depth + 1);
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
