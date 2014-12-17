package cz.vutbr.web.csskit;

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
