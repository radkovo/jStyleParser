package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermLength;

public class TermLengthImpl extends TermNumericImpl<Float> implements TermLength {

	protected TermLengthImpl() {
	}

    public boolean isPercentage()
    {
        return false;
    }
	
}
