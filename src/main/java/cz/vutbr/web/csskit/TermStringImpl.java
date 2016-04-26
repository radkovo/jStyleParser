package cz.vutbr.web.csskit;

import org.unbescape.css.CssEscape;

import cz.vutbr.web.css.CSSNodeVisitor;
import cz.vutbr.web.css.TermString;

/**
 * TermString
 * 
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 */
public class TermStringImpl extends TermImpl<String> implements TermString {

	protected TermStringImpl() {
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
	public TermString setValue(String value) {
		if (value == null) {
			throw new IllegalArgumentException(
					"Invalid value for TermString(null)");
		}
		/* This should be done by parser
		value = value.replaceAll("^'", "")
			.replaceAll("^\"", "")
			.replaceAll("'$", "")
			.replaceAll("\"$", "");
		*/	
		this.value = value;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(operator!=null) sb.append(operator.value());
		sb.append(OutputUtil.STRING_OPENING)
		    .append(CssEscape.escapeCssString(value))
		    .append(OutputUtil.STRING_CLOSING);
		
		return sb.toString();
	}
}
