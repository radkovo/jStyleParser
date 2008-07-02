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
		angle, LEFT_SIDE, FAR_LEFT, LEFT, CENTER_LEFT, CENTER,
		CENTER_RIGHT, RIGHT, FAR_RIGHT, RIGHT_SIDE, BEHIND,
		LEFTWARDS, RIGHTWARDS, INHERIT;
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
    	SCROLL, FIXED, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum BackgroundColor implements CSSProperty {
    	color, TRANSPARENT, INHERIT;
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
    	percentage, lenght, LEFT, CENTER, RIGHT, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum BackgroundPositionVer implements CSSProperty {
    	percentage, length, TOP, CENTER, BOTTOM, INHERIT;
    	public boolean inherited() { return false;}
    };
    public enum BackgroundRepeat implements CSSProperty {
    	REPEAT, REPEAT_X, REPEAT_Y, NO_REPEAT, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum BorderCollapse implements CSSProperty { 
    	COLLAPSE, SEPARATE, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum BorderColor implements CSSProperty {
    	color, TRANSPARENT, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum BorderSpacing implements CSSProperty {
    	length, INHERIT;
    	public boolean inherited() { return true;}
    }
    
    public enum BorderStyle implements CSSProperty { 
    	NONE, HIDDEN, DOTTED, DASHED, SOLID, DOUBLE, 
    	GROOVE, RIDGE, INSET, OUTSET, INHERIT;
    	public boolean inherited() { return false;}
    };
    public enum BorderWidth implements CSSProperty {
    	length, THIN, MEDIUM, THICK, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Elevation implements CSSProperty {
    	angle, BELOW, LEVEL, ABOVE, HIGHER, LOWER, INHERIT;
    	public boolean inherited() { return true;}
    }
    
    public enum FontFamily implements CSSProperty { 
    	font, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum FontSize implements CSSProperty { 
    	percentage, length, XX_SMALL, X_SMALL, SMALL, 
    	MEDIUM, LARGE, X_LARGE, XX_LARGE, LARGER, 
    	SMALLER, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum FontStyle implements CSSProperty { 
    	NORMAL, ITALIC, OBLIQUE, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum FontVariant implements CSSProperty { 
    	SMALL_CAPS, INHERIT, NORMAL;
    	public boolean inherited() { return true;}
    };
    
    public enum FontWeight implements CSSProperty { 
    	numeric_100, numeric_200, numeric_300, numeric_400, numeric_500, 
    	numeric_600, numeric_700, numeric_800, numeric_900, 
    	NORMAL, BOLD, BOLDER, LIGHTER, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum LineHeight implements CSSProperty {
    	number, length, percentage, NORMAL, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum CaptionSide implements CSSProperty {
    	TOP, BOTTOM, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum Content implements CSSProperty { 
    	list_values, NORMAL, NONE, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum CounterIncrement implements CSSProperty { 
    	list_values, NONE, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum CounterReset implements CSSProperty {
    	list_values, NONE, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Clear implements CSSProperty {
    	NONE, LEFT, RIGHT, BOTH, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Clip implements CSSProperty { 
    	shape, AUTO, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Cursor implements CSSProperty { 
    	AUTO, CROSSHAIR, DEFAULT, POINTER, MOVE, E_RESIZE, 
    	NE_RESIZE, NW_RESIZE, N_RESIZE, SE_RESIZE, 
    	SW_RESIZE, S_RESIZE, W_RESIZE, TEXT, WAIT, 
    	PROGRESS, HELP, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum Direction implements CSSProperty { 
    	LTR, RTL, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum Display implements CSSProperty { 
    	INLINE, BLOCK, LIST_ITEM, RUN_IN, INLINE_BLOCK, 
    	TABLE, INLINE_TABLE, TABLE_ROW_GROUP, 
    	TABLE_HEADER_GROUP, TABLE_FOOTER_GROUP, TABLE_ROW, 
    	TABLE_COLUMN_GROUP, TABLE_COLUMN, TABLE_CELL, 
    	TABLE_CAPTION, NONE, INHERIT;
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
    	SHOW, HIDE, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum Float implements CSSProperty { 
    	NONE, LEFT, RIGHT, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum ListStyleImage implements CSSProperty { 
    	uri, NONE, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum ListStylePosition implements CSSProperty { 
    	INSIDE, OUTSIDE, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum ListStyleType implements CSSProperty { 
    	DISC, CIRCLE, SQUARE, DECIMAL, DECIMAL_LEADING_ZERO, 
    	LOWER_ROMAN, UPPER_ROMAN, LOWER_GREEK, LOWER_LATIN, 
    	UPPER_LATN, ARMENIAN, GEORGIAN, LOWER_ALPHA, 
    	UPPER_ALPHA, NONE, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum Margin implements CSSProperty {
    	lenght, percentage, AUTO, INHERIT;
    	public boolean inherited() { return false;}
    }
    
    public enum Orphans implements CSSProperty { 
    	integer, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum OutlineWidth implements CSSProperty {
    	length, THIN, MEDIUM, THICK, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum OutlineStyle implements CSSProperty {
    	NONE, DOTTED, DASHED, SOLID, DOUBLE, 
    	GROOVE, RIDGE, INSET, OUTSET, HIDDEN, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum OutlineColor implements CSSProperty {
    	color, INVERT, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Overflow implements CSSProperty { 
    	VISIBLE, HIDDEN, SCROLL, AUTO, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Padding implements CSSProperty {
    	length, percentage, AUTO, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum PageBreak implements CSSProperty { 
    	AUTO, ALWAYS, AVOID, LEFT, RIGHT, INHERIT;
    	public boolean inherited() { return false;}
    };    
    
    public enum PageBreakInside implements CSSProperty {
    	AUTO, AVOID, INHERIT;
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
    	frequency, X_LOW, LOW, MEDIUM, HIGH, X_HIGH, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum PlayDuring implements CSSProperty {
    	uri, uri_mix, uri_repeat, AUTO, NONE, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Position implements CSSProperty { 
    	STATIC, RELATIVE, ABSOLUTE, FIXED, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum Richness implements CSSProperty {
    	number, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum SpeakHeader implements CSSProperty {
    	ONCE, ALWAYS, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum SpeakNumeral implements CSSProperty {
    	DIGITS, CONTINUOUS, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum SpeakPunctuation implements CSSProperty {
    	CODE, NONE, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum Speak implements CSSProperty {
    	NORMAL, NONE, SPELL_OUT, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum SpeechRate implements CSSProperty {
    	number, X_SLOW, SLOW, MEDIUM, FAST, X_FAST,
    	FASTER, SLOWER, INHERIT;
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
    	list_values, NONE, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum TableLayout implements CSSProperty { 
    	AUTO, FIXED, INHERIT;
    	public boolean inherited() { return false;}
    }
    
    public enum TextAlign implements CSSProperty { 
    	BY_DIRECTION, LEFT, RIGHT, CENTER, JUSTIFY, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum TextDecoration implements CSSProperty { 
    	UNDERLINE, OVERLINE, BLINK, LINE_THROUGH, NONE, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public enum TextIndent implements CSSProperty { 
    	length, percentage, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum TextTransform implements CSSProperty { 
    	CAPITALIZE, UPPERCASE, LOWERCASE, NONE, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum UnicodeBidi implements CSSProperty { 
    	NORMAL, EMDEB, BIDI_OVERRIDE, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum VerticalAlign implements CSSProperty { 
    	length, percentage, BASELINE, SUB, SUPER, TOP,
    	TEXT_TOP, MIDDLE, BOTTOM, TEXT_BOTTOM, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum Visibility implements CSSProperty { 
    	VISIBLE, HIDDEN, COLLAPSE, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum VoiceFamily implements CSSProperty {
    	list_values, specific_voice, generic_voice, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum Volume implements CSSProperty {
    	number, percentage, SILENT, X_SOFT, SOFT, 
    	MEDIUM, LOUD, X_LOUD, INHERIT;
    	public boolean inherited() { return true;}
    };
    
    public enum WhiteSpace implements CSSProperty { 
    	NORMAL, PRE, NOWRAP, PRE_WRAP, PRE_LINE, INHERIT;
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
    	integer, AUTO, INHERIT;
    	public boolean inherited() { return false;}
    };
    
    public <T extends CSSProperty> T getProperty(Class<T> clazz, String name);
    
    public <T extends Term<?>> T getValue(Class<T> clazz, String name);
    
    public <T extends Term<?>> List<T> getValues(Class<T> clazz, String name);
    
    public void push(Declaration d, int inheritanceLevel, boolean inherit);    
    
}
