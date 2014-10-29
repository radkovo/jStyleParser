package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermInteger;

public class TermIntegerImpl extends TermLengthImpl implements TermInteger {

	protected TermIntegerImpl() {
	    setUnit(Unit.none);
	}

    public int getIntValue() {
        return getValue().intValue();
    }

    public TermInteger setValue(int value) {
        setValue(Float.valueOf(value));
        return this;
    }

    @Override
    public String toString()
    {
        return String.valueOf(getIntValue());
    }

}
