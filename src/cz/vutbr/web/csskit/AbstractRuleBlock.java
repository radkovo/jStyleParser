package cz.vutbr.web.csskit;

import cz.vutbr.web.css.RuleBlock;

public class AbstractRuleBlock<T> extends AbstractRule<T> implements RuleBlock<T> {
	
	protected int position;
	
	protected AbstractRuleBlock(int position) {
		this.position = position;
	}
	
	public int getFilePosition() {
		return position;
	}
	
	public RuleBlock<T> setFilePosition(int position) {
		this.position = position;
		return this;
	}

	public int compareTo(RuleBlock<?> o) throws ClassCastException {
		return this.getFilePosition() - o.getFilePosition();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + position;
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
		if (!(obj instanceof AbstractRuleBlock))
			return false;		
		AbstractRuleBlock<?> other = (AbstractRuleBlock<?>) obj;
		if (position != other.position)
			return false;
		return true;
	}
	
}
