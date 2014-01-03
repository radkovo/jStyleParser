package cz.vutbr.web.csskit;

import cz.vutbr.web.css.RuleBlock;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.RuleBlock.Priority;

/**
 * CSS style sheet, entry point.
 * Allows 
 * 
 * @author kapy
 * 
 */
public class StyleSheetImpl extends AbstractRule<RuleBlock<?>> implements StyleSheet {
	
    private Priority mark;
    private Origin origin;

	protected StyleSheetImpl() {
    	this.mark = null;
    	this.origin = StyleSheet.Origin.AUTHOR;
    }    
    
	public void markLast(Priority mark) {
		this.mark = mark;
	}
	
	public Priority getLastMark() {
		return mark;
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
