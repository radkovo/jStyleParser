package cz.vutbr.web.csskit;

import cz.vutbr.web.css.CSSComment;
import cz.vutbr.web.css.RuleBlock;
import cz.vutbr.web.css.StyleSheet;

public class AbstractRuleBlock<T> extends AbstractRule<T> implements RuleBlock<T> {
	
	protected StyleSheet stylesheet;
	protected CSSComment comment;
	
	
	public StyleSheet getStyleSheet()
	{
		return stylesheet;
	}

	public void setStyleSheet(StyleSheet stylesheet)
	{
		this.stylesheet = stylesheet;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result;
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
		return true;
	}

	@Override
	public CSSComment getComment() {
		return this.comment;
	}

	@Override
	public void setComment(CSSComment comment) {
		this.comment = comment;
	}

	
}
