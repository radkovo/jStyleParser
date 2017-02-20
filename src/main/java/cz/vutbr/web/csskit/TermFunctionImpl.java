package cz.vutbr.web.csskit;

import java.util.ArrayList;
import java.util.List;

import org.unbescape.css.CssEscape;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFloatValue;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermOperator;

/**
 * TermFunction, holds function
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko
 */
public class TermFunctionImpl extends TermListImpl implements TermFunction {

	protected String functionName;
	
    protected TermFunctionImpl() {    	
    }
    
    /**
	 * @return the functionName
	 */
	public String getFunctionName() {
		return functionName;
	}

	/**
	 * @param functionName the functionName to set
	 */
	public TermFunction setFunctionName(String functionName) {
		if(functionName==null)
			throw new IllegalArgumentException("Invalid functionName in function (null)");
		this.functionName = functionName;
		return this;
	}

	@Override
    public List<List<Term<?>>> getSeparatedArgs(Term<?> separator) {
        List<List<Term<?>>> ret = new ArrayList<>();
        List<Term<?>> cur = new ArrayList<>();
        for (Term<?> t : this) {
            if (t.equals(separator)) {
                ret.add(cur);
                cur = new ArrayList<>();
            } else {
                cur.add(t);
            }
        }
        if (!cur.isEmpty())
            ret.add(cur);
        
        return ret;
    }

    @Override
    public List<TermFloatValue> getSeparatedValues(Term<?> separator) {
        List<TermFloatValue> ret = new ArrayList<>();
        TermOperator curOp = null; //an optional unary operator before the value
        TermFloatValue curVal = null;
        for (Term<?> t : this) {
            if (t.equals(separator)) {
                if (curVal != null) {
                    if (curOp != null) {
                        if (curOp.getValue() == '-') {
                            Float newVal = -curVal.getValue();
                            curVal = (TermFloatValue) curVal.shallowClone();
                            curVal.setValue(newVal);
                        } else if (curOp.getValue() != '+') {
                            return null; //invalid operator
                        }
                    }
                    ret.add(curVal);
                    curVal = null;
                    curOp = null;
                }
                else
                    return null; //value missing
            } else if (t instanceof TermOperator) {
                if (curOp == null && curVal == null)
                    curOp = (TermOperator) t;
                else
                    return null;
            } else if (t instanceof TermFloatValue) {
                if (curVal == null)
                    curVal = (TermFloatValue) t;
                else
                    return null;
            } else
                return null;
        }
        
        //the last value
        if (curVal != null) {
            if (curOp != null) {
                if (curOp.getValue() == '-') {
                    Float newVal = -curVal.getValue();
                    curVal = (TermFloatValue) curVal.shallowClone();
                    curVal.setValue(newVal);
                } else if (curOp.getValue() != '+') {
                    return null; //invalid operator
                }
            }
            ret.add(curVal);
        }
        else
            return null; //value missing
        
        return ret;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        // TODO Auto-generated method stub
        return super.clone();
    }


    @Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		// append operator
		if(operator!=null) sb.append(operator.value());
		
		sb.append(CssEscape.escapeCssIdentifier(functionName)).append(OutputUtil.FUNCTION_OPENING);
		sb = OutputUtil.appendList(sb, value, OutputUtil.EMPTY_DELIM)
			.append(OutputUtil.FUNCTION_CLOSING);
		
		return sb.toString();
    }


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((functionName == null) ? 0 : functionName.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof TermFunctionImpl))
			return false;
		TermFunctionImpl other = (TermFunctionImpl) obj;
		if (functionName == null) {
			if (other.functionName != null)
				return false;
		} else if (!functionName.equals(other.functionName))
			return false;
		return true;
	}
	
	
}
