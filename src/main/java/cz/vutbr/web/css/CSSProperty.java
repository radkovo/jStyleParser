package cz.vutbr.web.css;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Interface for definition of CSS properties. This interface simplifies storing
 * of values in maps, and provides basic inheritance support.
 * 
 * All implementations of this interface should provide static value with
 * signature:
 * 
 * <pre><b>public static</b> CSSProperty valueOf(String value);</pre>
 * 
 * to retrieve instance of property by string value. Since enum classes
 * provides this value automatically, it is encouraged to use them.
 * 
 * For make use of enums easier, this contract should be followed:
 * 
 * All values directly represented in CSS style sheet such as:
 * <code>float: <b>left</b>;</code> or <code>background-repeat: 
 * <b>repeat-x</b>;</code> should to converted to upper case and 
 * all not alphanumeric characters should be converted into underscores
 * (<code>_</code>), for example <code>REPEAT_X</code>
 * 
 * All other values, with essentially requires additional data, should 
 * broke enum standard and use lower case letters only. This way it is
 * guaranteed that this value won't never be considered as a keyword. 
 * 
 * @author kapy
 * 
 */
public interface CSSProperty {

	/**
	 * CSS "inherit" keyword for retrieving instance by Translator object
	 */
	public static final String INHERIT_KEYWORD = "INHERIT";
	public static final String INITIAL_KEYWORD = "INITIAL";

    public static final String FONT_SERIF = java.awt.Font.SERIF;
    public static final String FONT_SANS_SERIF = java.awt.Font.SANS_SERIF;
    public static final String FONT_MONOSPACED = java.awt.Font.MONOSPACED;
    public static final String FONT_CURSIVE = "Zapf-Chancery";
    public static final String FONT_FANTASY = "Western";
    
    
	/**
	 * Allows declarations of properties to inherit or to be inherited
	 * 
	 * @return <code>true</code> in case that this property could be inherited
	 *         from parent, <code>false</code> elsewhere
	 */
	public boolean inherited();

	/**
	 * Allows to check whether property equals <code>inherit</code> value
	 * 
	 * @return <code>true</code>if value is <code>INHERIT</code>,
	 *         <code>false</code> otherwise
	 */
	public boolean equalsInherit();

	/**
	 * Allows to check whether property equals <code>initial</code> value
	 * 
	 * @return <code>true</code>if value is <code>INITIAL</code>,
	 *         <code>false</code> otherwise
	 */
	public boolean equalsInitial();

	/**
	 * Textual representation of CSS property
	 * 
	 * @return String
	 */
	public String toString();
	
	/***************************************************************
	 * TRANSLATOR *
	 ****************************************************************/

	/**
	 * Retrieves value of property of given class and text value
	 * 
	 * @author kapy
	 * 
	 */
	public static class Translator {

		/**
		 * Methods cache
		 */
		private static Map<Class<? extends CSSProperty>, Method> translators = new HashMap<Class<? extends CSSProperty>, Method>();

		/**
		 * Retrieves CSSProperty by its name and class
		 * 
		 * @param type
		 *            Class of CSSProperty
		 * @param value
		 *            Text value
		 * @return CSSProperty if found, <code>null</code> elsewhere
		 */
		@SuppressWarnings("unchecked")
		public static final <T extends CSSProperty> T valueOf(Class<T> type,
				String value) {
			try {
				Method m = translators.get(type);
				if (m == null) {
					m = type.getMethod("valueOf", String.class);
				}
				return (T) m.invoke(null, value);
			} catch (Exception e) {
				return null;
				/*
				throw new IllegalArgumentException("Unable to get: " + value
						+ " for: " + type.getName(), e);
				*/		
			}
		}
		
		/**
		 * Creates "inherit" instance
		 * @param type Type of CSS property
		 * @return Should always return CSS instance. If <code>null</code> is returned, something
		 * is flawed.
		 */
		public static final <T extends CSSProperty> T createInherit(Class<T> type) {
			return valueOf(type, INHERIT_KEYWORD);
		}
	}

	/************************************************************************
	 * CSS PROPERTIES *
	 ************************************************************************/

	public enum Azimuth implements CSSProperty {
		angle(""), LEFT_SIDE("left-side"), FAR_LEFT("far-left"), LEFT("left"), CENTER_LEFT(
				"center-left"), CENTER("center"), CENTER_RIGHT("center-right"), RIGHT(
				"right"), FAR_RIGHT("far-right"), RIGHT_SIDE("right-side"), BEHIND(
				"behind"), LEFTWARDS("leftwards"), RIGHTWARDS("rightwards"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private Azimuth(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}

	}

	public enum Color implements CSSProperty {
		color(""), TRANSPARENT("transparent"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Color(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Cue implements CSSProperty {
		component_values(""), uri(""), NONE("none"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Cue(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Background implements CSSProperty {
		component_values(""), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Background(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum BackgroundAttachment implements CSSProperty {
		SCROLL("scroll"), FIXED("fixed"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private BackgroundAttachment(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum BackgroundColor implements CSSProperty {
		color(""), TRANSPARENT("transparent"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private BackgroundColor(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum BackgroundImage implements CSSProperty {
		uri(""), NONE("none"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private BackgroundImage(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum BackgroundPosition implements CSSProperty {
		list_values(""), LEFT("left"), CENTER("center"), RIGHT("right"), TOP("top"), BOTTOM("bottom"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private BackgroundPosition(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum BackgroundRepeat implements CSSProperty {
		REPEAT("repeat"), REPEAT_X("repeat-x"), REPEAT_Y("repeat-y"), NO_REPEAT(
				"no-repeat"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private BackgroundRepeat(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}
		
		@Override
		public String toString() {
			return text;
		}
	}

    public enum BackgroundSize implements CSSProperty {
        list_values(""), CONTAIN("contain"), COVER("cover"), INHERIT("inherit"), INITIAL("initial");

        private String text;

        private BackgroundSize(String text) {
            this.text = text;
        }

        public boolean inherited() {
            return false;
        }

        public boolean equalsInherit() {
            return this == INHERIT;
        }

        public boolean equalsInitial() {
            return this == INITIAL;
        }

        @Override
        public String toString() {
            return text;
        }
    }
    
	public enum Border implements CSSProperty {
		component_values(""), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Border(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum BorderCollapse implements CSSProperty {
		COLLAPSE("collapse"), SEPARATE("separate"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private BorderCollapse(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}		

		@Override
		public String toString() {
			return text;
		}
	}

	public enum BorderColor implements CSSProperty {
		color(""), taken(""), component_values(""), TRANSPARENT("transparent"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private BorderColor(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

    public enum BorderRadius implements CSSProperty {
        component_values(""), list_values(""), INHERIT("inherit"), INITIAL("initial");

        private String text;

        private BorderRadius(String text) {
            this.text = text;
        }

        public boolean inherited() {
            return false;
        }

        public boolean equalsInherit() {
            return this == INHERIT;
        }

        public boolean equalsInitial() {
            return this == INITIAL;
        }

        @Override
        public String toString() {
            return text;
        }
    }
	
	public enum BorderSpacing implements CSSProperty {
		list_values(""), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private BorderSpacing(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum BorderStyle implements CSSProperty {
		component_values(""), NONE("none"), HIDDEN("hidden"), DOTTED("dotted"), DASHED(
				"dashed"), SOLID("solid"), DOUBLE("double"), GROOVE("groove"), RIDGE(
				"ridge"), INSET("inset"), OUTSET("outset"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private BorderStyle(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum BorderWidth implements CSSProperty {
		component_values(""), length(""), THIN("thin"), MEDIUM("medium"), THICK(
				"thick"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private BorderWidth(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Elevation implements CSSProperty {
		angle(""), BELOW("below"), LEVEL("level"), ABOVE("above"), HIGHER(
				"higher"), LOWER("lower"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Elevation(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}
	
		@Override
		public String toString() {
			return text;
		}
	}

	public enum Font implements CSSProperty {
		component_values(""), CAPTION("caption"), ICON("icon"), MENU("menu"), MESSAGE_BOX(
				"message-box"), SMALL_CAPTION("small-caption"), STATUS_BAR(
				"status-bar"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Font(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum FontFamily implements CSSProperty {
		list_values("", ""), SERIF("serif", FONT_SERIF), SANS_SERIF("sans-serif", FONT_SANS_SERIF),
		CURSIVE("cursive", FONT_CURSIVE), FANTASY("fantasy", FONT_FANTASY), MONOSPACE("monospace", FONT_MONOSPACED), 
		INHERIT("inherit", ""), INITIAL("initial", "");
		
		private String text;
		private String awtval;
		
		private FontFamily(String text, String awtval) {
			this.text = text;
			this.awtval = awtval;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
		
		public String getAWTValue() {
		    return awtval;
		}
	}

	public enum FontSize implements CSSProperty {
		percentage(""), length(""), XX_SMALL("xx-small"), X_SMALL("x-small"), SMALL(
				"small"), MEDIUM("medium"), LARGE("large"), X_LARGE("x-large"), XX_LARGE(
				"xx-large"), LARGER("larger"), SMALLER("smaller"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private FontSize(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum FontStyle implements CSSProperty {
		NORMAL("normal"), ITALIC("italic"), OBLIQUE("oblique"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private FontStyle(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum FontVariant implements CSSProperty {
		SMALL_CAPS("small-caps"), NORMAL("normal"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private FontVariant(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum FontWeight implements CSSProperty {
		numeric_100("100"), numeric_200("200"), numeric_300("300"), numeric_400(
				"400"), numeric_500("500"), numeric_600("600"), numeric_700(
				"700"), numeric_800("800"), numeric_900("900"), NORMAL("normal"), BOLD(
				"bold"), BOLDER("bolder"), LIGHTER("lighter"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private FontWeight(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum LineHeight implements CSSProperty {
		number(""), length(""), percentage(""), NORMAL("normal"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private LineHeight(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum CaptionSide implements CSSProperty {
		TOP("top"), BOTTOM("bottom"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private CaptionSide(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Content implements CSSProperty {
		list_values(""), NORMAL("normal"), NONE("none"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Content(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}
	
		@Override
		public String toString() {
			return text;
		}
	}

	public enum CounterIncrement implements CSSProperty {
		list_values(""), NONE("none"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private CounterIncrement(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum CounterReset implements CSSProperty {
		list_values(""), NONE("none"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private CounterReset(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}
	
	public enum CounterSet implements CSSProperty {
		list_values(""), NONE("none"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private CounterSet(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}
	
	public enum Clear implements CSSProperty {
		NONE("none"), LEFT("left"), RIGHT("right"), BOTH("both"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private Clear(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Clip implements CSSProperty {
		shape(""), AUTO("auto"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Clip(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Cursor implements CSSProperty {
		AUTO("auto"), CROSSHAIR("crosshair"), DEFAULT("default"), POINTER(
				"pointer"), MOVE("move"), E_RESIZE("e-resize"), NE_RESIZE(
				"ne-resize"), NW_RESIZE("nw-resize"), N_RESIZE("n-resize"), SE_RESIZE(
				"se-resize"), SW_RESIZE("sw-resize"), S_RESIZE("s-resize"), W_RESIZE(
				"w-resize"), TEXT("text"), WAIT("wait"), PROGRESS("progress"), HELP(
				"help"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Cursor(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Direction implements CSSProperty {
		LTR("ltr"), RTL("rtl"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Direction(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Display implements CSSProperty {
		INLINE("inline"), BLOCK("block"), LIST_ITEM("list-item"), RUN_IN(
				"run-in"), INLINE_BLOCK("inline-block"), TABLE("table"), INLINE_TABLE(
				"inline-table"), TABLE_ROW_GROUP("table-row-group"), TABLE_HEADER_GROUP(
				"table-header-group"), TABLE_FOOTER_GROUP("table-footer-group"), TABLE_ROW(
				"table-row"), TABLE_COLUMN_GROUP("table-column-group"), TABLE_COLUMN(
				"table-column"), TABLE_CELL("table-cell"), TABLE_CAPTION(
				"table-caption"), NONE("none"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Display(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Width implements CSSProperty {
		length(""), percentage(""), AUTO("auto"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Width(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum MinWidth implements CSSProperty {
		length(""), percentage(""), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private MinWidth(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum MaxWidth implements CSSProperty {
		length(""), percentage(""), NONE("none"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private MaxWidth(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}
		
		@Override
		public String toString() {
			return text;
		}
	}

	public enum Height implements CSSProperty {
		length(""), percentage(""), AUTO("auto"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Height(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}
		
		@Override
		public String toString() {
			return text;
		}
	}

	public enum MinHeight implements CSSProperty {
		length(""), percentage(""), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private MinHeight(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum MaxHeight implements CSSProperty {
		length(""), percentage(""), NONE("none"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private MaxHeight(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum EmptyCells implements CSSProperty {
		SHOW("show"), HIDE("hide"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private EmptyCells(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Float implements CSSProperty {
		NONE("none"), LEFT("left"), RIGHT("right"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Float(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum ListStyle implements CSSProperty {
		component_values(""), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private ListStyle(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum ListStyleImage implements CSSProperty {
		uri(""), NONE("none"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private ListStyleImage(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum ListStylePosition implements CSSProperty {
		INSIDE("inside"), OUTSIDE("outside"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private ListStylePosition(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum ListStyleType implements CSSProperty {
		DISC("disc"), CIRCLE("circle"), SQUARE("square"), DECIMAL("decimal"), DECIMAL_LEADING_ZERO(
				"decimal-leading-zero"), LOWER_ROMAN("lower-roman"), UPPER_ROMAN(
				"upper-roman"), LOWER_GREEK("lower-greek"), LOWER_LATIN(
				"lower-latin"), UPPER_LATN("upper-latin"), ARMENIAN("armenian"), GEORGIAN(
				"georgian"), LOWER_ALPHA("lower-alpha"), UPPER_ALPHA(
				"upper-alpha"), NONE("none"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private ListStyleType(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Margin implements CSSProperty {
		length(""), percentage(""), component_values(""), AUTO("auto"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private Margin(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

    public enum Opacity implements CSSProperty {
        number(""), INHERIT("inherit"), INITIAL("initial");

        private String text;

        private Opacity(String text) {
            this.text = text;
        }

        public boolean inherited() {
            return false;
        }

        public boolean equalsInherit() {
            return this == INHERIT;
        }

        public boolean equalsInitial() {
            return this == INITIAL;
        }

        @Override
        public String toString() {
            return text;
        }
    }
    
	public enum Orphans implements CSSProperty {
		integer(""), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Orphans(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Outline implements CSSProperty {
		component_values(""), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Outline(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum OutlineWidth implements CSSProperty {
		length(""), THIN("thin"), MEDIUM("medium"), THICK("thick"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private OutlineWidth(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum OutlineStyle implements CSSProperty {
		NONE("none"), DOTTED("dotted"), DASHED("dashed"), SOLID("solid"), DOUBLE(
				"double"), GROOVE("groove"), RIDGE("ridge"), INSET("inset"), OUTSET(
				"outset"), HIDDEN("hidden"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private OutlineStyle(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum OutlineColor implements CSSProperty {
		color(""), INVERT("invert"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private OutlineColor(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}
		@Override
		public String toString() {
			return text;
		}
	}

	public enum Overflow implements CSSProperty {
		VISIBLE("visible"), HIDDEN("hidden"), SCROLL("scroll"), AUTO("auto"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private Overflow(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Padding implements CSSProperty {
		length(""), percentage(""), component_values(""), AUTO("auto"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private Padding(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum PageBreak implements CSSProperty {
		AUTO("auto"), ALWAYS("always"), AVOID("avoid"), LEFT("left"), RIGHT(
				"right"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private PageBreak(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum PageBreakInside implements CSSProperty {
		AUTO("auto"), AVOID("avoid"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private PageBreakInside(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Pause implements CSSProperty {
		component_values(""), time(""), percentage(""), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Pause(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum PitchRange implements CSSProperty {
		number(""), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private PitchRange(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Pitch implements CSSProperty {
		frequency(""), X_LOW("x-low"), LOW("low"), MEDIUM("medium"), HIGH(
				"high"), X_HIGH("x-high"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Pitch(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum PlayDuring implements CSSProperty {
		uri(""), uri_mix(""), uri_repeat(""), AUTO("auto"), NONE("none"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private PlayDuring(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Position implements CSSProperty {
		STATIC("static"), RELATIVE("relative"), ABSOLUTE("absolute"), FIXED(
				"fixed"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Position(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}
		
		@Override
		public String toString() {
			return text;
		}
	}

	public enum Richness implements CSSProperty {
		number("number"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Richness(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum SpeakHeader implements CSSProperty {
		ONCE("once"), ALWAYS("always"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private SpeakHeader(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum SpeakNumeral implements CSSProperty {
		DIGITS("digits"), CONTINUOUS("continuous"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private SpeakNumeral(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum SpeakPunctuation implements CSSProperty {
		CODE("code"), NONE("none"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private SpeakPunctuation(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}
		
		@Override
		public String toString() {
			return text;
		}
	}

	public enum Speak implements CSSProperty {
		NORMAL("normal"), NONE("none"), SPELL_OUT("spell-out"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private Speak(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}
		
		@Override
		public String toString() {
			return text;
		}
	}

	public enum SpeechRate implements CSSProperty {
		number(""), X_SLOW("x-slow"), SLOW("slow"), MEDIUM("medium"), FAST(
				"fast"), X_FAST("x-fast"), FASTER("faster"), SLOWER("slower"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private SpeechRate(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Stress implements CSSProperty {
		number(""), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Stress(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Top implements CSSProperty {
		length(""), percentage(""), AUTO("auto"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Top(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Right implements CSSProperty {
		length(""), percentage(""), AUTO("auto"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Right(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Bottom implements CSSProperty {
		length(""), percentage(""), AUTO("auto"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Bottom(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Left implements CSSProperty {
		length(""), percentage(""), AUTO("auto"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Left(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Quotes implements CSSProperty {
		list_values(""), NONE("none"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Quotes(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum TableLayout implements CSSProperty {
		AUTO("auto"), FIXED("fixed"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private TableLayout(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum TextAlign implements CSSProperty {
		BY_DIRECTION("by-direction"), LEFT("left"), RIGHT("right"), CENTER(
				"center"), JUSTIFY("justify"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private TextAlign(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum TextDecoration implements CSSProperty {
		list_values(""), UNDERLINE("underline"), OVERLINE("overline"), BLINK(
				"blink"), LINE_THROUGH("line-through"), NONE("none"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private TextDecoration(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum TextIndent implements CSSProperty {
		length(""), percentage(""), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private TextIndent(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum TextTransform implements CSSProperty {
		CAPITALIZE("capitalize"), UPPERCASE("uppercase"), LOWERCASE("lowercase"), NONE(
				"none"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private TextTransform(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum UnicodeBidi implements CSSProperty {
		NORMAL("normal"), EMDEB("embed"), BIDI_OVERRIDE("bidi-override"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private UnicodeBidi(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}		

		@Override
		public String toString() {
			return text;
		}
	}

	public enum VerticalAlign implements CSSProperty {
		length(""), percentage(""), BASELINE("baseline"), SUB("sub"), SUPER(
				"super"), TOP("top"), TEXT_TOP("text-top"), MIDDLE("middle"), BOTTOM(
				"bottom"), TEXT_BOTTOM("text-bottom"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private VerticalAlign(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Visibility implements CSSProperty {
		VISIBLE("visible"), HIDDEN("hidden"), COLLAPSE("collapse"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private Visibility(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum VoiceFamily implements CSSProperty {
		list_values(""), MALE("male"), FEMALE("female"), CHILD("child"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private VoiceFamily(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Volume implements CSSProperty {
		number(""), percentage(""), SILENT("silent"), X_SOFT("x-soft"), SOFT(
				"soft"), MEDIUM("medium"), LOUD("loud"), X_LOUD("x-loud"), INHERIT(
				"inherit"), INITIAL("initial");

		private String text;

		private Volume(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum WhiteSpace implements CSSProperty {
		NORMAL("normal"), PRE("pre"), NOWRAP("nowrap"), PRE_WRAP("pre-wrap"), PRE_LINE(
				"pre-line"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private WhiteSpace(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum Widows implements CSSProperty {
		integer(""), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private Widows(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum WordSpacing implements CSSProperty {
		length(""), NORMAL("normal"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private WordSpacing(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum LetterSpacing implements CSSProperty {
		length(""), NORMAL("normal"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private LetterSpacing(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return true;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public enum ZIndex implements CSSProperty {
		integer(""), AUTO("auto"), INHERIT("inherit"), INITIAL("initial");

		private String text;

		private ZIndex(String text) {
			this.text = text;
		}

		public boolean inherited() {
			return false;
		}

		public boolean equalsInherit() {
			return this == INHERIT;
		}

		public boolean equalsInitial() {
			return this == INITIAL;
		}
		
		@Override
		public String toString() {
			return text;
		}
	}

    /**
     * A generic property used for all the properties not supported by another implementation.
     */
    public class GenericCSSPropertyProxy implements CSSProperty
    {
        private String text;

        private GenericCSSPropertyProxy(final String thePropertyValue)
        {
            this.text = thePropertyValue;
        }

        @Override
        public boolean inherited()
        {
            return false;
        }

        @Override
        public boolean equalsInherit()
        {
            return false;
        }

        @Override
        public boolean equalsInitial()
        {
            return false;
        }

        @Override
        public String toString()
        {
            return text;
        }

        /**
         * Creates a new instance of the GenericCSSPropertyProxy. This method
         * simulates the method valueOf(String) of the other CSS attributes that
         * are implmented as enums.
         * 
         * @param value the property value.
         * 
         * @return a new insance that contains the property value.
         */
        public static GenericCSSPropertyProxy valueOf(final String value)
        {
            return new GenericCSSPropertyProxy(value == null ? "" : value.toLowerCase());
        }
	}	
	
}
