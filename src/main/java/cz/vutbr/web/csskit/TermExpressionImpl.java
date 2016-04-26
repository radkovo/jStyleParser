package cz.vutbr.web.csskit;

import cz.vutbr.web.css.CSSNodeVisitor;
import cz.vutbr.web.css.TermExpression;

/**
 * TermExpression
 * 
 * @author Radek Burget, 2009
 */
public class TermExpressionImpl extends TermImpl<String> implements TermExpression {

	protected TermExpressionImpl() {
	}

	@Override
	public TermExpression setValue(String value) {
		if (value == null) {
			throw new IllegalArgumentException(
					"Invalid value for TermExpression(null)");
		}
		this.value = value;
		return this;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(operator!=null) sb.append(operator.value());
		sb.append("expression(").append(value).append(")");
		return sb.toString();
	}
}
