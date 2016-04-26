package cz.vutbr.web.csskit;

import cz.vutbr.web.css.CSSComment;
import cz.vutbr.web.css.CSSNodeVisitor;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.Term;

/**
 * CSS Declaration
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 */
public class DeclarationImpl extends AbstractRule<Term<?>> implements Declaration {

	protected String property;
	protected boolean important;
	protected Source source;
	protected CSSComment comment;

	protected DeclarationImpl() {
		this.property = "";
		this.important = false;
		this.source = null;
	}
	
	/**
	 * Shallow copy constructor
	 * @param clone Declaration to share term values with
	 */
	protected DeclarationImpl(Declaration clone) {
		this.property = clone.getProperty();
		this.important = clone.isImportant();
		this.source = new Source(clone.getSource());
		this.replaceAll(clone.asList());
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

	@Override
    public Source getSource()
    {
        return source;
    }

    @Override
    public void setSource(Source src)
    {
        this.source = src;
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
		sb = OutputUtil.appendList(sb, list, OutputUtil.EMPTY_DELIM);
		
		// importance flag
		if(important) sb.append(OutputUtil.SPACE_DELIM).append(OutputUtil.IMPORTANT_KEYWORD);
		
		sb.append(OutputUtil.PROPERTY_CLOSING);
		
        return sb.toString();
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (important ? 1231 : 1237);
		result = prime * result
				+ ((property == null) ? 0 : property.hashCode());
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
		if (!(obj instanceof DeclarationImpl))
			return false;
		DeclarationImpl other = (DeclarationImpl) obj;
		if (important != other.important)
			return false;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		return true;
	}

	@Override
	public CSSComment getComment() {
		return this.comment;
	}

	@Override
	public void setComment(CSSComment comment) {
		this.comment = comment;
	}
    
}
