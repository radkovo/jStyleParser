package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermString;

/**
 * TermString
 * 
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 */
public class TermStringImpl extends TermImpl implements TermString {

	protected String value;

	public TermStringImpl(String value) {
		setValue(value);
	}
	
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		if (value == null) {
			throw new IllegalArgumentException(
					"Invalid value for TermString(null)");
		}
		value = value.replaceAll("^'", "")
			.replaceAll("^\"", "")
			.replaceAll("'$", "")
			.replaceAll("\"$", "");
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(operator!=null) sb.append(operator.value());
		sb.append(OutputUtil.STRING_OPENING).append(value).append(OutputUtil.STRING_CLOSING);
		
		return sb.toString();
	}
	
}
