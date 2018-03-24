package cz.vutbr.web.csskit;

import java.util.ArrayList;
import java.util.List;

import org.unbescape.css.CssEscape;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFloatValue;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermList;
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
    public TermList setValue(List<Term<?>> value) {
        this.value = new ArrayList<>();
        
        // Treat '-' as modifying the next argument, instead of as an operator
        boolean prevMinus = false;
        
        for (Term<?> term : value) {
            if (term instanceof TermOperator && ((TermOperator) term).getValue() == '-') {
                prevMinus = true;
            } else if (prevMinus) {
                if (prependMinus(term)) {
                    this.value.remove(this.value.size() - 1); // Remove merged minus
                }

                prevMinus = false;
            }
            
            this.value.add(term);
        }
        
        return this;
    }
    
    protected boolean prependMinus(Term<?> term) {
        boolean merged = false;
        
        if (term instanceof TermFloatValue) { // includes TermAngle, TermLength, etc.
            TermFloatValue floatT = (TermFloatValue) term;
            floatT.setValue(-1 * floatT.getValue());
            merged = true;
        } else if (term instanceof TermIdent) {
            TermIdent ident = (TermIdent) term;
            ident.setValue("-" + ident.getValue());
            merged = true;
        } else if (term instanceof TermFunction) {
            TermFunction func = (TermFunction) term;
            func.setFunctionName("-" + func.getFunctionName());
            merged = true;
        }
        
        return merged;
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
    public List<Term<?>> getSeparatedValues(Term<?> separator, boolean allowKeywords) {
        List<Term<?>> ret = new ArrayList<>();
        TermOperator curOp = null; //an optional unary operator before the value
        Term<?> curVal = null;
        for (Term<?> t : this) {
            if (t.equals(separator)) {
                if (curVal != null) {
                    if (curOp != null) {
                        if (curVal instanceof TermFloatValue) {
                            if (curOp.getValue() == '-') {
                                Float newVal = -((TermFloatValue) curVal).getValue();
                                curVal = (TermFloatValue) curVal.shallowClone();
                                ((TermFloatValue) curVal).setValue(newVal);
                            } else if (curOp.getValue() != '+') {
                                return null; //invalid operator
                            }
                        }
                        else
                            return null; //operator combined with ident
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
                    curVal = t;
                else
                    return null;
            } else if (allowKeywords && t instanceof TermIdent) {
                if (curVal == null)
                    curVal = t;
                else
                    return null;
            } else
                return null;
        }
        
        //the last value
        if (curVal != null) {
            if (curOp != null) {
                if (curVal instanceof TermFloatValue) {
                    if (curOp.getValue() == '-') {
                        Float newVal = -((TermFloatValue) curVal).getValue();
                        curVal = (TermFloatValue) curVal.shallowClone();
                        ((TermFloatValue) curVal).setValue(newVal);
                    } else if (curOp.getValue() != '+') {
                        return null; //invalid operator
                    }
                }
                else
                    return null; //operator combined with ident
            }
            ret.add(curVal);
        }
        else
            return null; //value missing
        
        return ret;
    }

    @Override
    public List<Term<?>> getValues(boolean allowKeywords)
    {
        List<Term<?>> ret = new ArrayList<>();
        TermOperator curOp = null; //an optional unary operator before the value
        for (Term<?> t : this) {
            if (t instanceof TermOperator) {
                if (curOp == null)
                    curOp = (TermOperator) t;
                else
                    return null; //repeating operator
            } else if (t instanceof TermFloatValue) {
                TermFloatValue curVal = (TermFloatValue) t;
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
            } else if (t instanceof TermIdent) {
                if (curOp == null)
                    ret.add(t);
                else
                    return null; //operator combined with ident
            } else
                return null; //invalid term
        }
        
        if (curOp != null)
            return null; //an operator followed with no value
        
        return ret;
    }

    @Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		// append operator
		if(operator!=null) sb.append(operator.value());
		
		sb.append(CssEscape.escapeCssIdentifier(functionName)).append(OutputUtil.FUNCTION_OPENING);
		sb = OutputUtil.appendFunctionArgs(sb, value)
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
