package cz.vutbr.web.css;

public interface CSSComment {
	
	/**
     * Obtains the content of the comment.
     * @return The comment
     */
	public String getText();

	/**
     * Sets the comment's content.
     * @param The comment
     */
    public void setText(String text);
    
    /**
     * Obtains the location of the comment within the code.
     * @return The location
     */
    public CodeLocation getLocation(); 
    
    /**
	 * Accept method required by the visitor pattern for traversing the CSS Tree. 
	 * 
	 * @param visitor
	 * 	The visitor interface
	 * @return
	 * 	The current CSS Object
	 */
	public Object accept(CSSNodeVisitor visitor);
    
}
