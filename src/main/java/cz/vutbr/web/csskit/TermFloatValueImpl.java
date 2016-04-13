package cz.vutbr.web.csskit;

import cz.vutbr.web.css.CSSNodeVisitor;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFloatValue;
import cz.vutbr.web.css.TermNumeric;

/**
 *
 * @author burgetr
 */
public class TermFloatValueImpl extends TermNumericImpl<Float> implements TermFloatValue
{
	
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
    public TermNumeric<Float> setZero()
    {
        super.setValue(0.0f);
        return this;
    }

    @Override
    public Term<Float> setValue(Float value)
    {
        if (value == -0.0f) //avoid negative zeroes in CSS
            return super.setValue(0.0f);
        else
            return super.setValue(value);
    }

}
