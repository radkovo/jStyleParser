package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermFunction;

/**
 * TermFunction, holds function
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko
 * @version 1.0 * Construction moved to parser
 * 				 * Implementation changed according to new interface
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
		
		// this should be done by lexer/parser
		// functionName = functionName.replaceAll("\\($", "");
		
		this.functionName = functionName;
		return this;
	}

	@Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		// append operator
		if(operator!=null) sb.append(operator.value());
		
		sb.append(functionName).append(OutputUtil.FUNCTION_OPENING);
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
