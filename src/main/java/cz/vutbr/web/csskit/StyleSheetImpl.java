package cz.vutbr.web.csskit;

import cz.vutbr.web.css.CSSNodeVisitor;
import cz.vutbr.web.css.RuleBlock;
import cz.vutbr.web.css.StyleSheet;

/**
 * CSS style sheet, entry point.
 * Allows 
 * 
 * @author kapy
 * 
 */
public class StyleSheetImpl extends AbstractRule<RuleBlock<?>> implements StyleSheet {
	
    private Origin origin;

	protected StyleSheetImpl() {
    	this.origin = StyleSheet.Origin.AUTHOR;
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
    
	@Override
	public void setOrigin(Origin o)
	{
		this.origin = o;
	}

	@Override
	public Origin getOrigin()
	{
		return origin;
	}

	@Override
	public void add(int index, RuleBlock<?> element)
	{
		element.setStyleSheet(this);
		super.add(index, element);
	}

	@Override
	public boolean add(RuleBlock<?> o)
	{
		o.setStyleSheet(this);
		return super.add(o);
	}
   
	
}
