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

	protected StyleSheetImpl() {
    	this.mark = null;
    }    
    
	public void markLast(Priority mark) {
		this.mark = mark;
	}
	
	public Priority getLastMark() {
		return mark;
	}
   
	
}
