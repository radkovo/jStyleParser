package cz.vutbr.web.css;

import java.util.List;

/**
 * Holder of CSS properties defined for element
 */
public interface NodeData {
	
	public interface CSSProperty {
		public boolean inherited();
	};    
    
    public enum Color implements CSSProperty { 
    	color, inherit;
    	public boolean inherited() { return true;}
    };   
    
    public enum BackgroundAttachment implements CSSProperty {
    	scroll, fixed;
    	public boolean inherited() { return false;}
    };
    
    public enum BackgroundColor implements CSSProperty {
    	color, transparent;
    	public boolean inherited() { return false;}
    }
    
    public enum BackgroundImage implements CSSProperty { 
    	uri, none;
    	public boolean inherited() { return false;}
    };
    
    public enum BackgroundPosition implements CSSProperty { 
    	value;
    	public boolean inherited() { return false;}
    };
    
    public enum BackgroundPositionHor implements CSSProperty {
    	percentage, length, left, center, right;
    	public boolean inherited() { return false;}
    };
    
    public enum BackgroundPositionVer implements CSSProperty {
    	percentage, length, top, center, bottom;
    	public boolean inherited() { return false;}
    };
    public enum BackgroundRepeat implements CSSProperty {
    	repeat, repeat_x, repeat_y, no_repeat, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum BorderCollapse implements CSSProperty { 
    	collapse, separate, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum BorderSpacing implements CSSProperty {
    	length, inherit;
    	public boolean inherited() { return true;}
    }
    
    public enum BorderStyle implements CSSProperty { 
    	none, hidden, dotted, dashed, solid, prefix_double, groove, ridge, inset, outset;
    	public boolean inherited() { return false;}
    };
    public enum BorderWidth implements CSSProperty {
    	length, thin, medium, thick;
    	public boolean inherited() { return false;}
    };
    
    public enum FontFamily implements CSSProperty { 
    	font, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum FontSize implements CSSProperty { 
    	percentage, length, xx_small, x_small, small, medium, large, x_large, xx_large, larger, smaller, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum FontStyle implements CSSProperty { 
    	normal, italic, oblique, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum FontVariant implements CSSProperty { 
    	normal, small_caps, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum FontWeight implements CSSProperty { 
    	normal, bold, bolder, lighter, prefix_100, prefix_200, prefix_300, prefix_400, prefix_500, prefix_600, prefix_700, prefix_800, prefix_900, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum LineHeight implements CSSProperty {
    	normal, number, length, percentage, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum CaptionSide implements CSSProperty {
    	top, bottom, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum Content implements CSSProperty { 
    	normal, none, list_values, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum Counter implements CSSProperty { 
    	none, table_values, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum Clear implements CSSProperty {
    	none, left, right, both;
    	public boolean inherited() { return false;}
    };
    
    public enum Clip implements CSSProperty { 
    	shape, auto;
    	public boolean inherited() { return false;}
    };
    
    public enum Cursor implements CSSProperty { 
    	auto, crosshair, prefix_default, pointer, move, e_resize, ne_resize, nw_resize, n_resize, se_resize, sw_resize, s_resize, w_resize, text, wait, progress, help, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum Direction implements CSSProperty { 
    	ltr, rtl, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum Display implements CSSProperty { 
    	inline, block, list_item, run_in, inline_block, table, inline_table, table_row_group, table_header_group, table_footer_group, table_row, table_column_group, table_column, table_cell, table_caption, none;
    	public boolean inherited() { return false;}
    };
    
    public enum Width implements CSSProperty {
    	lenght, percentage, auto;
    	public boolean inherited() { return false;}
    }
    
    public enum MinWidth implements CSSProperty {
    	lenght, percentage;
    	public boolean inherited() { return false;}
    }
    
    public enum MaxWidth implements CSSProperty {
    	lenght, percentage, none;
    	public boolean inherited() { return false;}
    }
    
    public enum Height implements CSSProperty {
    	lenght, auto;
    	public boolean inherited() { return false;}
    }
    
    public enum MinHeight implements CSSProperty {
    	lenght, percentage;
    	public boolean inherited() { return false;}
    }
    
    public enum MaxHeight implements CSSProperty {
    	lenght, percentage, none;
    	public boolean inherited() { return false;}
    }
    
    public enum EmptyCells implements CSSProperty { 
    	show, hide, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum Float implements CSSProperty { 
    	none, left, right;
    	public boolean inherited() { return false;}
    };
    
    public enum ListStyleImage implements CSSProperty { 
    	uri, none, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum ListStylePosition implements CSSProperty { 
    	inside, outside, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum ListStyleType implements CSSProperty { 
    	disc, circle, square, decimal, decimal_leading_zero, lower_roman, upper_roman, lower_greek, lower_latin, upper_latin, armenian, georgian, lower_alpha, upper_alpha, none, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum MarginWidth implements CSSProperty {
    	lenght, percentage, auto;
    	public boolean inherited() { return false;}
    }
    
    public enum Orphans implements CSSProperty { 
    	integer, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum Overflow implements CSSProperty { 
    	visible, hidden, scroll, auto;
    	public boolean inherited() { return false;}
    };
    
    public enum PaddingWidth implements CSSProperty {
    	length, percentage, auto;
    	public boolean inherited() { return false;}
    };
    
    public enum PageBreak implements CSSProperty { 
    	auto, always, avoid, left, right, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum PageBreakInside implements CSSProperty {
    	auto, avoid, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum Position implements CSSProperty { 
    	prefix_static, relative, absolute, fixed;
    	public boolean inherited() { return false;}
    };
    
    public enum Top implements CSSProperty {
    	lenght, percentage, auto;
    	public boolean inherited() { return false;}
    };
    
    public enum Right implements CSSProperty {
    	lenght, percentage, auto;
    	public boolean inherited() { return false;}
    };
    
    public enum Bottom implements CSSProperty {
    	lenght, percentage, auto;
    	public boolean inherited() { return false;}
    };
    
    public enum Left implements CSSProperty {
    	lenght, percentage, auto;
    	public boolean inherited() { return false;}
    };
    
    public enum Quotes implements CSSProperty { 
    	none, list_values, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum TableLayout implements CSSProperty { 
    	auto, fixed, inherit;
    	public boolean inherited() { return true;}
    }
    
    public enum TextAlign implements CSSProperty { 
    	by_direction, left, right, center, justify, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum TextDecoration implements CSSProperty { 
    	underline, overline, line_through, blink, none;
    	public boolean inherited() { return false;}
    };
    
    public enum TextIndent implements CSSProperty { 
    	length, percentage, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum TextTransform implements CSSProperty { 
    	capitalize, uppercase, lowercase, none, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum UnicodeBidi implements CSSProperty { 
    	normal, embed, bidi_override, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum VerticalAlign implements CSSProperty { 
    	baseline, sub, prefix_super, top, text_top, middle, bottom, text_bottom, length, percentage, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum Visibility implements CSSProperty { 
    	visible, hidden, collapse, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum WhiteSpace implements CSSProperty { 
    	normal, pre, nowrap, pre_wrap, pre_line, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum Widows implements CSSProperty { 
    	integer, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum WordSpacing implements CSSProperty { 
    	length, normal, inherit;
    	public boolean inherited() { return true;}
    };
    
    public enum LetterSpacing implements CSSProperty {
    	length, normal, inherit;
    	public boolean inherited() { return true;}
    }
    
    public enum ZIndex implements CSSProperty { 
    	auto, integer;
    	public boolean inherited() { return false;}
    };
    
    public <T extends CSSProperty> T getProperty(Class<T> clazz, String name);
    
    public <T extends Term> T getValue(Class<T> clazz, String name);
    
    public <T extends Term> List<T> getValues(Class<T> clazz, String name);
    
    public void push(Declaration d, int inheritanceLevel, boolean inherit);    
    
}
