package cz.vutbr.web.csskit;

import cz.vutbr.web.css.CSSNodeVisitor;
import cz.vutbr.web.css.TermInteger;

public class TermIntegerImpl extends TermLengthImpl implements TermInteger {

	protected TermIntegerImpl() {
	    setUnit(Unit.none);
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
        if (operator != null)
            return operator.value() + getIntValue();
        return String.valueOf(getIntValue());
    }

}
