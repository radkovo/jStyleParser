package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermIdent;

/**
 * TermIdent
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 */
public class TermIdentImpl extends TermImpl implements TermIdent {

    private String value;
   
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if(value == null) 
            throw new IllegalArgumentException("Invalid value for TermIndent(null)");
        
        this.value = value;
    }
    
    public TermIdentImpl(String value) {
        setValue(value);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(operator!=null) sb.append(operator.value());
        sb.append(value);
        
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
				+ ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		if (!(obj instanceof TermIdentImpl))
			return false;
		final TermIdentImpl other = (TermIdentImpl) obj;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}    
    
    
}
