package cz.vutbr.web.csskit;

import cz.vutbr.web.css.CSSComment;
import cz.vutbr.web.css.CSSNodeVisitor;
import cz.vutbr.web.css.CodeLocation;

public class CommentImpl implements CSSComment {
	
	private String text;
	private CodeLocation location;
	
	public CommentImpl(String text, CodeLocation location) {
		this.text = text;
		this.location = location;
	}

	/**
     * Obtains the content of the comment.
     * @return The comment
     */
	@Override
	public String getText() {
		return this.text;
	}

	/**
     * Sets the comment's content.
     * @param The comment
     */
	@Override
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public CodeLocation getLocation() {
		return this.location;
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

}