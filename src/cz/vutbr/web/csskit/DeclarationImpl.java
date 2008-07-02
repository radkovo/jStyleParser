package cz.vutbr.web.csskit;

import java.util.Collections;
import java.util.List;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.Term;

/**
 * Declaration
 * @author Jan Svercl, VUT Brno, 2008
 * 			modifed by Karel Piwko, 2008
 * @version 1.0 * Rewritten according to interface
 * 				 * Construction moved to parser
 */
public class DeclarationImpl implements Declaration {

	protected String property;
	protected List<Term<?>> terms;
	protected boolean important;

	public DeclarationImpl() {
		this.property = "";
		this.terms = Collections.emptyList();
		this.important = false;
	}
	
	/**
	 * Shallow copy constructor
	 * @param clone Declaration to share term values with
	 */
	public DeclarationImpl(Declaration clone) {
		this.property = clone.getProperty();
		this.terms = clone.getTerms();
		this.important = clone.isImportant();
	}

	/**
	 * This declaration type is never inherited
	 * @return <code>false</code>
	 */
	public boolean isInherited(int level) {
		return false;
	}
	
	public int getInheritanceLevel() {
		return 0;
	}
	
	/**
	 * This declaration type is not about to be compared
	 * using precise conditions
	 */
	public int compareTo(Declaration o) {
		
		if(this.isImportant() && ! o.isImportant())
            return 1;
        else if(o.isImportant() && ! this.isImportant())
            return -1;
		
		return 0;
	}
	
    /**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}



	/**
	 * @param property the property to set
	 */
	public void setProperty(String property) {
		this.property = property;
	}



	/**
	 * @return the terms
	 */
	public List<Term<?>> getTerms() {
		return terms;
	}



	/**
	 * @param terms the terms to set
	 */
	public void setTerms(List<Term<?>> terms) {
		this.terms = terms;
	}



	/**
	 * @return the important
	 */
	public boolean isImportant() {
		return important;
	}



	/**
	 * @param important the important to set
	 */
	public void setImportant(boolean important) {
		this.important = important;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (important ? 1231 : 1237);
		result = prime * result
				+ ((property == null) ? 0 : property.hashCode());
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
		if (obj == null)
			return false;
		if (!(obj instanceof DeclarationImpl))
			return false;
		final DeclarationImpl other = (DeclarationImpl) obj;
		if (important != other.important)
			return false;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		if (terms == null) {
			if (other.terms != null)
				return false;
		} else if (!terms.equals(other.terms))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.toString(0);
	}
	

	public String toString(int depth) {
		
		StringBuilder sb = new StringBuilder();
		
		// add property
		sb = OutputUtil.appendTimes(sb, OutputUtil.DEPTH_DELIM, depth);
		sb.append(property).append(OutputUtil.PROPERTY_OPENING);
		
		// add terms
		sb = OutputUtil.appendList(sb, terms, OutputUtil.EMPTY_DELIM)
				.append(OutputUtil.PROPERTY_CLOSING);
		
        return sb.toString();
    }
    
}
