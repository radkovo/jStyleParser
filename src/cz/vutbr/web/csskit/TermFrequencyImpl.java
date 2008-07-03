package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermFrequency;

public class TermFrequencyImpl extends TermNumericImpl<Float> implements TermFrequency {

	public TermFrequencyImpl(Float value) {
		setValue(value);
	}
	
	public TermFrequencyImpl(Float value, int unary) {
		this(value*unary);
	}
	
	public TermFrequencyImpl(String value, Unit unit, int unary) {
		this(TermNumericImpl.convertFloat(value, unit.value(), unary));
		this.unit = unit;
	}
	
	@Override
	public void setValue(Float value) {
		// value is negative
		if(value==null || (new Float(0.0f)).compareTo(value) > 0)
				throw new IllegalArgumentException("Null or negative value for CSS time");
		this.value = value;
	}
	
}
