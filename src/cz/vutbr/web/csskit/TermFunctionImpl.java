package cz.vutbr.web.csskit;

import java.util.Collections;
import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;

/**
 * TermFunction, holds function
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko
 * @version 1.0 * Construction moved to parser
 * 				 * Implementation changed according to new interface
 */
public class TermFunctionImpl extends TermImpl<List<Term<?>>> implements TermFunction {

	protected String functionName;
	protected List<Term<?>> terms;

    public TermFunctionImpl() {
    	this.terms = Collections.emptyList();
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
	public void setFunctionName(String functionName) {
		if(functionName==null)
			throw new IllegalArgumentException("Invalid functionName in function (null)");
		
		functionName = functionName.replaceAll("\\($", "");
		this.functionName = functionName;
	}



	/**
	 * @return the terms
	 */
	public List<Term<?>> getValue() {
		return terms;
	}



	/**
	 * @param terms the terms to set
	 */
	public void setValue(List<Term<?>> terms) {
		this.terms = terms;
	}



	@Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		// append operator
		if(operator!=null) sb.append(operator.value());
		
		sb.append(functionName).append(OutputUtil.FUNCTION_OPENING);
		sb = OutputUtil.appendList(sb, terms, OutputUtil.EMPTY_DELIM)
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
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((terms == null) ? 0 : terms.hashCode());
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
		final TermFunctionImpl other = (TermFunctionImpl) obj;
		if (functionName == null) {
			if (other.functionName != null)
				return false;
		} else if (!functionName.equals(other.functionName))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (terms == null) {
			if (other.terms != null)
				return false;
		} else if (!terms.equals(other.terms))
			return false;
		return true;
	}	
}
