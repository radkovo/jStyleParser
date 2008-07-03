package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermInteger;

public class TermIntegerImpl extends TermNumericImpl<Integer> implements TermInteger {

	public TermIntegerImpl(Integer value) {
		this.value = value;
	}
	
	public TermIntegerImpl(Integer value, int unary) {
		this(value*unary);
	}
}
