package cz.vutbr.web.css;

import java.util.List;

/**
 * Holder of CSS properties defined for element.
 * Enumeration values follows this syntax:
 * 		* UPPERCASE terminal symbols, direct values present in stylesheet,
 * 			such as background-color: transparent;
 * 		* lowercase non-terminal symbols, just information that concrete value
 * 			is stored somewhere else
 */
public interface NodeData {
	
	public interface CSSProperty {
		public boolean inherited();
	};    
	
	public enum Azimuth implements CSSProperty {
		angle, left_side, far_left, left, center_left, center,
		center_right, right, far_right, right_side, behind,
		leftwards, rightwards, INHERIT;
		public boolean inherited() { return true;}
	}
    
    public enum Color implements CSSProperty { 
    	color, INHERIT;
    	public boolean inherited() { return true;}
    };   
    
    public enum CueBefore implements CSSProperty {
    	uri, NONE, INHERIT;
    	public boolean inherited() { return false;}
    }
    
    public enum CueAfter implements CSSProperty {
    	uri, NONE, INHERIT;
    	public boolean inherited() { return false;}
    }
    
    public enum BackgroundAttachment implements CSSProperty {
    	scroll, fixed, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum BackgroundColor implements CSSProperty {
    	color, transparent, INHERIT;
    	public boolean inherited() { return false;}
    }
    
    public enum BackgroundImage implements CSSProperty { 
    	uri, NONE, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum BackgroundPosition implements CSSProperty { 
    	VALUE, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum BackgroundPositionHor implements CSSProperty {
    	percentage, lenght, left, center, right, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum BackgroundPositionVer implements CSSProperty {
    	percentage, length, top, center, bottom, INHERIT;
    	public boolean inherited() { return false;}
    };
    public enum BackgroundRepeat implements CSSProperty {
    	repeat, repeat_x, repeat_y, no_repeat, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum BorderCollapse implements CSSProperty { 
    	collapse, separate, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum BorderSpacing implements CSSProperty {
    	length, INHERIT;
    	public boolean inherited() { return true;}
    }
    
    public enum BorderStyle implements CSSProperty { 
    	NONE, hidden, dotted, dashed, solid, DOUBLE, 
    	groove, ridge, inset, outset, INHERIT;
    	public boolean inherited() { return false;}
    };
    public enum BorderWidth implements CSSProperty {
    	length, thin, MEDIUM, thick, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Elevation implements CSSProperty {
    	angle, below, level, above, higher, lower, INHERIT;
    	public boolean inherited() { return true;}
    }
    
    public enum FontFamily implements CSSProperty { 
    	font, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum FontSize implements CSSProperty { 
    	percentage, length, xx_small, x_small, small, 
    	MEDIUM, large, x_large, xx_large, larger, 
    	smaller, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum FontStyle implements CSSProperty { 
    	NORMAL, italic, oblique, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum FontVariant implements CSSProperty { 
    	small_caps, INHERIT, NORMAL;
    	public boolean inherited() { return true;}
    };
    
    public enum FontWeight implements CSSProperty { 
    	NORMAL, bold, bolder, lighter, prefix_100, 
    	prefix_200, prefix_300, prefix_400, prefix_500, 
    	prefix_600, prefix_700, prefix_800, prefix_900, 
    	INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum LineHeight implements CSSProperty {
    	NORMAL, number, length, percentage, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum CaptionSide implements CSSProperty {
    	top, bottom, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum Content implements CSSProperty { 
    	NORMAL, NONE, list_values, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum CounterIncrement implements CSSProperty { 
    	NONE, list_values, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum CounterReset implements CSSProperty {
    	NONE, list_values, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Clear implements CSSProperty {
    	NONE, left, right, both, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Clip implements CSSProperty { 
    	shape, AUTO, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Cursor implements CSSProperty { 
    	AUTO, crosshair, DEFAULT, pointer, 
    	move, e_resize, ne_resize, nw_resize, n_resize, se_resize, 
    	sw_resize, s_resize, w_resize, text, wait, 
    	progress, help, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum Direction implements CSSProperty { 
    	ltr, rtl, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum Display implements CSSProperty { 
    	inline, block, list_item, run_in, inline_block, 
    	table, inline_table, table_row_group, 
    	table_header_group, table_footer_group, table_row, 
    	table_column_group, table_column, table_cell, 
    	table_caption, NONE, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Width implements CSSProperty {
    	lenght, percentage, AUTO, INHERIT;
    	public boolean inherited() { return false;}
    }
    
    public enum MinWidth implements CSSProperty {
    	lenght, percentage, INHERIT;
    	public boolean inherited() { return false;}
    }
    
    public enum MaxWidth implements CSSProperty {
    	lenght, percentage, NONE, INHERIT;
    	public boolean inherited() { return false;}
    }
    
    public enum Height implements CSSProperty {
    	lenght, AUTO, INHERIT;
    	public boolean inherited() { return false;}
    }
    
    public enum MinHeight implements CSSProperty {
    	lenght, percentage, INHERIT;
    	public boolean inherited() { return false;}
    }
    
    public enum MaxHeight implements CSSProperty {
    	lenght, percentage, NONE, INHERIT;
    	public boolean inherited() { return false;}
    }
    
    public enum EmptyCells implements CSSProperty { 
    	show, hide, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum Float implements CSSProperty { 
    	NONE, left, right, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum ListStyleImage implements CSSProperty { 
    	uri, NONE, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum ListStylePosition implements CSSProperty { 
    	inside, outside, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum ListStyleType implements CSSProperty { 
    	disc, circle, square, decimal, decimal_leading_zero, 
    	lower_roman, upper_roman, lower_greek, lower_latin, 
    	upper_latin, armenian, georgian, lower_alpha, 
    	upper_alpha, NONE, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum MarginWidth implements CSSProperty {
    	lenght, percentage, AUTO, INHERIT;
    	public boolean inherited() { return false;}
    }
    
    public enum Orphans implements CSSProperty { 
    	integer, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum OutlineWidth implements CSSProperty {
    	thin, MEDIUM, thick, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum OutlineStyle implements CSSProperty {
    	NONE, dotted, dashed, solid, DOUBLE, 
    	groove, ridge, inset, outset, hidden, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum OutlineColor implements CSSProperty {
    	invert, color, inerit;
    	public boolean inherited() { return false;}
    };
    
    public enum Overflow implements CSSProperty { 
    	visible, hidden, scroll, AUTO, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum PaddingWidth implements CSSProperty {
    	length, percentage, AUTO, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum PageBreak implements CSSProperty { 
    	AUTO, always, avoid, left, right, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum PageBreakBefore implements CSSProperty {
    	AUTO, always, avoid, left, right, INHERIT;
    	public boolean inherited() { return false;} 
    };
    
    public enum PageBreakAfter implements CSSProperty {
    	AUTO, always, avoid, left, right, INHERIT;
    	public boolean inherited() { return false;} 
    };
    
    public enum PageBreakInside implements CSSProperty {
    	AUTO, avoid, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum PauseBefore implements CSSProperty {
    	time, percentage, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum PauseAfter implements CSSProperty {
    	time, percentage, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum PitchRange implements CSSProperty {
    	number, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum Pitch implements CSSProperty {
    	frequency, x_low, low, MEDIUM, high, x_high, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum PlayDuring implements CSSProperty {
    	uri, uri_mix, uri_repeat, AUTO, NONE, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Position implements CSSProperty { 
    	STATIC, relative, absolute, fixed, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Richness implements CSSProperty {
    	number, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum SpeakHeader implements CSSProperty {
    	once, always, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum SpeakNumeral implements CSSProperty {
    	digits, continuous, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum SpeakPunctuation implements CSSProperty {
    	code, NONE, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum Speak implements CSSProperty {
    	NORMAL, NONE, spell_out, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum SpeechRate implements CSSProperty {
    	number, x_slow, slow, MEDIUM, fast, x_fast,
    	faster, slower, INHERIT;
    	public boolean inherited() { return true;}
    }
    
    public enum Stress implements CSSProperty {
    	number, INHERIT;
    	public boolean inherited() { return true;}
    }    
    
    public enum Top implements CSSProperty {
    	lenght, percentage, AUTO, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Right implements CSSProperty {
    	lenght, percentage, AUTO, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Bottom implements CSSProperty {
    	lenght, percentage, AUTO, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Left implements CSSProperty {
    	lenght, percentage, AUTO, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Quotes implements CSSProperty { 
    	NONE, list_values, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum TableLayout implements CSSProperty { 
    	AUTO, fixed, INHERIT;
    	public boolean inherited() { return false;}
    }
    
    public enum TextAlign implements CSSProperty { 
    	by_direction, left, right, center, justify, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum TextDecoration implements CSSProperty { 
    	underline, overline, line_through, blink, NONE, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum TextIndent implements CSSProperty { 
    	length, percentage, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum TextTransform implements CSSProperty { 
    	capitalize, uppercase, lowercase, NONE, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum UnicodeBidi implements CSSProperty { 
    	NORMAL, embed, bidi_override, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum VerticalAlign implements CSSProperty { 
    	baseline, sub, SUPER, top, text_top, 
    	middle, bottom, text_bottom, length, percentage, 
    	INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum Visibility implements CSSProperty { 
    	visible, hidden, collapse, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum VoiceFamily implements CSSProperty {
    	list_values, specific_voice, generic_voice, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum Volume implements CSSProperty {
    	number, percentage, silent, x_soft, soft, 
    	MEDIUM, loud, x_loud, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum WhiteSpace implements CSSProperty { 
    	NORMAL, pre, nowrap, pre_wrap, pre_line, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum Widows implements CSSProperty { 
    	integer, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum WordSpacing implements CSSProperty { 
    	length, NORMAL, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum LetterSpacing implements CSSProperty {
    	length, NORMAL, INHERIT;
    	public boolean inherited() { return true;}
    }
    
    public enum ZIndex implements CSSProperty { 
    	AUTO, integer, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public <T extends CSSProperty> T getProperty(Class<T> clazz, String name);
    
    public <T extends Term> T getValue(Class<T> clazz, String name);
    
    public <T extends Term> List<T> getValues(Class<T> clazz, String name);
    
    public void push(Declaration d, int inheritanceLevel, boolean inherit);    
    
}
