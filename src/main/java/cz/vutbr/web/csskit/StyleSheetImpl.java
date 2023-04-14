package cz.vutbr.web.csskit;

import java.util.List;

import cz.vutbr.web.css.MediaSpec;
import cz.vutbr.web.css.RuleBlock;
import cz.vutbr.web.css.RuleMedia;
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

	@Override
	public StyleSheet filter(MediaSpec medium) {
		StyleSheet filtered = new StyleSheetImpl();
		filtered.unlock();
		filtered.setOrigin(origin);
		filter(this, medium, filtered);
		return filtered;
	}

	private static void filter(Iterable<RuleBlock<?>> unfiltered, MediaSpec medium, List<RuleBlock<?>> filtered) {
		for (RuleBlock<?> rule : unfiltered) {
			if (rule instanceof RuleMedia) {
				if (medium.matches(((RuleMedia)rule).getMediaQueries()))
					filter((RuleMedia)rule, medium, filtered);
			} else
				filtered.add(rule);
		}
	}
}
