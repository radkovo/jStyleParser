package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermPercent;

/**
 * TermPercent
 * @author burgetr
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko
 * @version 1.0 * Construction moved to parser
 * 				 * Rewritten toString() method
 */
public class TermPercentImpl extends TermNumericImpl<Float> implements TermPercent {

    protected TermPercentImpl() {
    }
    
	@Override
    public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if(operator!=null) sb.append(operator.value());
		sb.append(value).append(OutputUtil.PERCENT_SIGN);

		return sb.toString();
	}

    public boolean isPercentage()
    {
        return true;
    }
	
}
