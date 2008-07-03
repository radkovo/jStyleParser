package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermTime;

public class TermTimeImpl extends TermNumericImpl<Float> implements TermTime {
	
	public TermTimeImpl(Float value) {
		setValue(value);
	}
	
	public TermTimeImpl(Float value, int unary) {
		this(value*unary);
	}
	
	public TermTimeImpl(String value, Unit unit, int unary) {
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
