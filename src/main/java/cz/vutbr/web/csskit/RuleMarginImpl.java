package cz.vutbr.web.csskit;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleMargin;

/**
 * Implementation of RuleMargin
 * 
 * @author Bert Frees, 2012
 */
public class RuleMarginImpl extends AbstractRuleBlock<Declaration> implements RuleMargin {

	private MarginArea marginArea;

	protected RuleMarginImpl(String area, Priority priority) {
		super(priority);
		for (MarginArea a : MarginArea.values()) {
			if (a.value.equals(area)) {
				marginArea = a;
				break; }}
		if (marginArea == null)
			throw new IllegalArgumentException("Illegal value for margin area: " + area);
	}

	public MarginArea getMarginArea() {
		return marginArea;
	}

	@Override
	public String toString() {
		return this.toString(0);
	}
	
	public String toString(int depth) {
		
		StringBuilder sb = new StringBuilder();
		
		sb = OutputUtil.appendTimes(sb, OutputUtil.DEPTH_DELIM, depth);
		sb.append(OutputUtil.MARGIN_AREA_OPENING).append(marginArea.value);
		
		sb.append(OutputUtil.RULE_OPENING);
		sb = OutputUtil.appendList(sb, list, OutputUtil.RULE_DELIM, depth + 1);
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
		result = prime * result + ((marginArea == null) ? 0 : marginArea.hashCode());
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
		if (!(obj instanceof RuleMarginImpl))
			return false;
		RuleMarginImpl other = (RuleMarginImpl) obj;
		if (marginArea == null) {
			if (other.marginArea != null)
				return false;
		} else if (!marginArea.equals(other.marginArea))
			return false;
		return true;
	}
}
