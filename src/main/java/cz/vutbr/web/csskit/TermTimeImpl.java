package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermTime;

public class TermTimeImpl extends TermNumericImpl<Float> implements TermTime {
	
	protected TermTimeImpl() {
	}
	
	@Override
	public TermTime setValue(Float value) {
		// value is negative
		if(value==null || (new Float(0.0f)).compareTo(value) > 0)
				throw new IllegalArgumentException("Null or negative value for CSS time");
		this.value = value;
		return this;
	}

}
