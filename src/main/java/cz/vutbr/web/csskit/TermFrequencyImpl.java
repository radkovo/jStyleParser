package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermFrequency;

public class TermFrequencyImpl extends TermNumericImpl<Float> implements TermFrequency {

	protected TermFrequencyImpl() {
	}
	
	@Override
	public TermFrequency setValue(Float value) {
		// value is negative
		if(value==null || (new Float(0.0f)).compareTo(value) > 0)
				throw new IllegalArgumentException("Null or negative value for CSS time");
		this.value = value;
		return this;
	}
	
}
