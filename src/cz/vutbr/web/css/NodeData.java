package cz.vutbr.web.css;

/**
 * Holder of CSS properties defined for element. Enumeration values follows this
 * syntax: * UPPERCASE terminal symbols, direct values present in stylesheet,
 * such as background-color: transparent; * lowercase non-terminal symbols, just
 * information that concrete value is stored somewhere else
 * 
 * @author kapy
 */
public interface NodeData {

	/**
	 * Interface for definition of CSS properties. This interface simplifies
	 * storing of values in maps, and provides basic inheritance support.
	 * 
	 * @author kapy
	 * 
	 */
	public interface CSSProperty {

		/**
		 * Allows declarations of properties to inherit or to be inherited
		 * 
		 * @return <code>true</code> in case that this property could be
		 *         inherited from parent, <code>false</code> elsewhere
		 */
		public boolean inherited();
		
		/**
		 * Allows to check whether property equals <code>inherit</code> value
		 * 
		 * @return <code>true</code>if value is <code>INHERIT</code>, <code>false</code>
		 * otherwise
		 */
		public boolean equalsInherit();
	};

	public <T extends CSSProperty> T getProperty(Class<T> clazz, String name);

	public <T extends CSSProperty> T getProperty(Class<T> clazz, String name,
			boolean includeInherited);

	public <T extends Term<?>> T getValue(Class<T> clazz, String name);

	public <T extends Term<?>> T getValue(Class<T> clazz, String name,
			boolean includeInherited);

	public NodeData inheritValues(NodeData parent);

	public void push(Declaration d);

	/************************************************************************
	 * CSS PROPERTIES *
	 ************************************************************************/

	public enum Azimuth implements CSSProperty {
		angle, LEFT_SIDE, FAR_LEFT, LEFT, CENTER_LEFT, CENTER, CENTER_RIGHT, RIGHT, FAR_RIGHT, RIGHT_SIDE, BEHIND, LEFTWARDS, RIGHTWARDS, INHERIT;
		public boolean inherited() {
			return true;
		}
		
		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Color implements CSSProperty {
		color, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Cue implements CSSProperty {
		component_values, uri, NONE, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Background implements CSSProperty {
		component_values, INHERIT;
		public boolean inherited() {
			return false;
		}
		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum BackgroundAttachment implements CSSProperty {
		SCROLL, FIXED, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum BackgroundColor implements CSSProperty {
		color, TRANSPARENT, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum BackgroundImage implements CSSProperty {
		uri, NONE, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum BackgroundPosition implements CSSProperty {
		list_values, LEFT, CENTER, RIGHT, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum BackgroundRepeat implements CSSProperty {
		REPEAT, REPEAT_X, REPEAT_Y, NO_REPEAT, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}
	
	public enum Border implements CSSProperty {
		component_values, INHERIT;
		public boolean inherited() {
			return false;
		}
		
		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum BorderCollapse implements CSSProperty {
		COLLAPSE, SEPARATE, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}
	
	public enum BorderColor implements CSSProperty {
		color, taken, component_values, TRANSPARENT, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum BorderSpacing implements CSSProperty {
		list_values, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum BorderStyle implements CSSProperty {
		component_values, NONE, HIDDEN, DOTTED, DASHED, SOLID, DOUBLE, GROOVE, RIDGE, INSET, OUTSET, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum BorderWidth implements CSSProperty {
		component_values, length, THIN, MEDIUM, THICK, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Elevation implements CSSProperty {
		angle, BELOW, LEVEL, ABOVE, HIGHER, LOWER, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Font implements CSSProperty {
		component_values, CAPTION, ICON, MENU, MESSAGE_BOX, SMALL_CAPTION, STATUS_BAR, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum FontFamily implements CSSProperty {
		list_values, SERIF, SANS_SERIF, CURSIVE, FANTASY, MONOSPACE, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum FontSize implements CSSProperty {
		percentage, length, XX_SMALL, X_SMALL, SMALL, MEDIUM, LARGE, X_LARGE, XX_LARGE, LARGER, SMALLER, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum FontStyle implements CSSProperty {
		NORMAL, ITALIC, OBLIQUE, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum FontVariant implements CSSProperty {
		SMALL_CAPS, INHERIT, NORMAL;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum FontWeight implements CSSProperty {
		numeric_100, numeric_200, numeric_300, numeric_400, numeric_500, numeric_600, numeric_700, numeric_800, numeric_900, NORMAL, BOLD, BOLDER, LIGHTER, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum LineHeight implements CSSProperty {
		number, length, percentage, NORMAL, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum CaptionSide implements CSSProperty {
		TOP, BOTTOM, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Content implements CSSProperty {
		list_values, NORMAL, NONE, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum CounterIncrement implements CSSProperty {
		list_values, NONE, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum CounterReset implements CSSProperty {
		list_values, NONE, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Clear implements CSSProperty {
		NONE, LEFT, RIGHT, BOTH, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Clip implements CSSProperty {
		rect, AUTO, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Cursor implements CSSProperty {
		AUTO, CROSSHAIR, DEFAULT, POINTER, MOVE, E_RESIZE, NE_RESIZE, NW_RESIZE, N_RESIZE, SE_RESIZE, SW_RESIZE, S_RESIZE, W_RESIZE, TEXT, WAIT, PROGRESS, HELP, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Direction implements CSSProperty {
		LTR, RTL, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Display implements CSSProperty {
		INLINE, BLOCK, LIST_ITEM, RUN_IN, INLINE_BLOCK, TABLE, INLINE_TABLE, TABLE_ROW_GROUP, TABLE_HEADER_GROUP, TABLE_FOOTER_GROUP, TABLE_ROW, TABLE_COLUMN_GROUP, TABLE_COLUMN, TABLE_CELL, TABLE_CAPTION, NONE, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Width implements CSSProperty {
		lenght, percentage, AUTO, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum MinWidth implements CSSProperty {
		lenght, percentage, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum MaxWidth implements CSSProperty {
		lenght, percentage, NONE, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Height implements CSSProperty {
		lenght, percentage, AUTO, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum MinHeight implements CSSProperty {
		lenght, percentage, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum MaxHeight implements CSSProperty {
		lenght, percentage, NONE, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum EmptyCells implements CSSProperty {
		SHOW, HIDE, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Float implements CSSProperty {
		NONE, LEFT, RIGHT, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}
	
	public enum ListStyle implements CSSProperty {
		component_values, INHERIT;
		public boolean inherited() {
			return true;
		}
		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum ListStyleImage implements CSSProperty {
		uri, NONE, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum ListStylePosition implements CSSProperty {
		INSIDE, OUTSIDE, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum ListStyleType implements CSSProperty {
		DISC, CIRCLE, SQUARE, DECIMAL, DECIMAL_LEADING_ZERO, LOWER_ROMAN, UPPER_ROMAN, LOWER_GREEK, LOWER_LATIN, UPPER_LATN, ARMENIAN, GEORGIAN, LOWER_ALPHA, UPPER_ALPHA, NONE, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Margin implements CSSProperty {
		lenght, percentage, component_values, AUTO, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Orphans implements CSSProperty {
		integer, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Outline implements CSSProperty {
		component_values, INHERIT;
		public boolean inherited() {
			return false;
		}
		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}
	
	public enum OutlineWidth implements CSSProperty {
		length, THIN, MEDIUM, THICK, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum OutlineStyle implements CSSProperty {
		NONE, DOTTED, DASHED, SOLID, DOUBLE, GROOVE, RIDGE, INSET, OUTSET, HIDDEN, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum OutlineColor implements CSSProperty {
		color, INVERT, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Overflow implements CSSProperty {
		VISIBLE, HIDDEN, SCROLL, AUTO, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Padding implements CSSProperty {
		length, percentage, component_values, AUTO, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum PageBreak implements CSSProperty {
		AUTO, ALWAYS, AVOID, LEFT, RIGHT, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum PageBreakInside implements CSSProperty {
		AUTO, AVOID, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Pause implements CSSProperty {
		component_values, time, percentage, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum PitchRange implements CSSProperty {
		number, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Pitch implements CSSProperty {
		frequency, X_LOW, LOW, MEDIUM, HIGH, X_HIGH, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum PlayDuring implements CSSProperty {
		uri, uri_mix, uri_repeat, AUTO, NONE, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Position implements CSSProperty {
		STATIC, RELATIVE, ABSOLUTE, FIXED, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Richness implements CSSProperty {
		number, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum SpeakHeader implements CSSProperty {
		ONCE, ALWAYS, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum SpeakNumeral implements CSSProperty {
		DIGITS, CONTINUOUS, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum SpeakPunctuation implements CSSProperty {
		CODE, NONE, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Speak implements CSSProperty {
		NORMAL, NONE, SPELL_OUT, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum SpeechRate implements CSSProperty {
		number, X_SLOW, SLOW, MEDIUM, FAST, X_FAST, FASTER, SLOWER, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Stress implements CSSProperty {
		number, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Top implements CSSProperty {
		lenght, percentage, AUTO, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Right implements CSSProperty {
		lenght, percentage, AUTO, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Bottom implements CSSProperty {
		lenght, percentage, AUTO, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Left implements CSSProperty {
		lenght, percentage, AUTO, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Quotes implements CSSProperty {
		list_values, NONE, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum TableLayout implements CSSProperty {
		AUTO, FIXED, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum TextAlign implements CSSProperty {
		BY_DIRECTION, LEFT, RIGHT, CENTER, JUSTIFY, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum TextDecoration implements CSSProperty {
		list_values, UNDERLINE, OVERLINE, BLINK, LINE_THROUGH, NONE, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum TextIndent implements CSSProperty {
		length, percentage, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum TextTransform implements CSSProperty {
		CAPITALIZE, UPPERCASE, LOWERCASE, NONE, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum UnicodeBidi implements CSSProperty {
		NORMAL, EMDEB, BIDI_OVERRIDE, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum VerticalAlign implements CSSProperty {
		length, percentage, BASELINE, SUB, SUPER, TOP, TEXT_TOP, MIDDLE, BOTTOM, TEXT_BOTTOM, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Visibility implements CSSProperty {
		VISIBLE, HIDDEN, COLLAPSE, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum VoiceFamily implements CSSProperty {
		list_values, MALE, FEMALE, CHILD, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Volume implements CSSProperty {
		number, percentage, SILENT, X_SOFT, SOFT, MEDIUM, LOUD, X_LOUD, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum WhiteSpace implements CSSProperty {
		NORMAL, PRE, NOWRAP, PRE_WRAP, PRE_LINE, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum Widows implements CSSProperty {
		integer, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum WordSpacing implements CSSProperty {
		length, NORMAL, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum LetterSpacing implements CSSProperty {
		length, NORMAL, INHERIT;
		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}

	public enum ZIndex implements CSSProperty {
		integer, AUTO, INHERIT;
		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this==INHERIT;
		}
	}
}
