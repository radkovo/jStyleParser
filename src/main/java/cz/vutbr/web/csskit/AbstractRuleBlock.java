package cz.vutbr.web.csskit;

import cz.vutbr.web.css.RuleBlock;
import cz.vutbr.web.css.StyleSheet;

public class AbstractRuleBlock<T> extends AbstractRule<T> implements RuleBlock<T> {
	
	protected Priority priority;
	protected StyleSheet stylesheet;
	
	protected AbstractRuleBlock(Priority priority) {
		this.priority = priority;
	}

	public RuleBlock<T> setPriority(Priority priority) {
		this.priority = priority;
		return this;
	}
	
	public Priority getPriority() {
		return priority;
	}

	public StyleSheet getStyleSheet()
	{
		return stylesheet;
	}

	public void setStyleSheet(StyleSheet stylesheet)
	{
		this.stylesheet = stylesheet;
	}

	public int compareTo(RuleBlock<?> o) throws ClassCastException {
		return this.getPriority().compareTo(o.getPriority());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((priority == null) ? 0 : priority.hashCode());
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
		if (!(obj instanceof AbstractRuleBlock<?>))
			return false;
		AbstractRuleBlock<?> other = (AbstractRuleBlock<?>) obj;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		return true;
	}

	
}
