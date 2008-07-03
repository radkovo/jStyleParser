package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermAngle;

public class TermAngleImpl extends TermNumericImpl<Float> implements TermAngle {

	public TermAngleImpl(Float value) {
		setValue(value);
	}
	
	public TermAngleImpl(Float value, int unary) {
		this(value*unary);
	}
	
	public TermAngleImpl(String value, Unit unit, int unary) {
		this(TermNumericImpl.convertFloat(value, unit.value(), unary));
		this.unit = unit;
	}
}
