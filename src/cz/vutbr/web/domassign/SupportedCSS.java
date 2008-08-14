package cz.vutbr.web.domassign;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.NodeData.Azimuth;
import cz.vutbr.web.css.NodeData.BackgroundAttachment;
import cz.vutbr.web.css.NodeData.BackgroundColor;
import cz.vutbr.web.css.NodeData.BackgroundImage;
import cz.vutbr.web.css.NodeData.BackgroundRepeat;
import cz.vutbr.web.css.NodeData.BorderCollapse;
import cz.vutbr.web.css.NodeData.BorderSpacing;
import cz.vutbr.web.css.NodeData.BorderStyle;
import cz.vutbr.web.css.NodeData.BorderWidth;
import cz.vutbr.web.css.NodeData.Bottom;
import cz.vutbr.web.css.NodeData.CSSProperty;
import cz.vutbr.web.css.NodeData.CaptionSide;
import cz.vutbr.web.css.NodeData.Clear;
import cz.vutbr.web.css.NodeData.Clip;
import cz.vutbr.web.css.NodeData.Color;
import cz.vutbr.web.css.NodeData.Content;
import cz.vutbr.web.css.NodeData.CounterIncrement;
import cz.vutbr.web.css.NodeData.CounterReset;
import cz.vutbr.web.css.NodeData.CueAfter;
import cz.vutbr.web.css.NodeData.CueBefore;
import cz.vutbr.web.css.NodeData.Cursor;
import cz.vutbr.web.css.NodeData.Direction;
import cz.vutbr.web.css.NodeData.Display;
import cz.vutbr.web.css.NodeData.Elevation;
import cz.vutbr.web.css.NodeData.EmptyCells;
import cz.vutbr.web.css.NodeData.Font;
import cz.vutbr.web.css.NodeData.FontFamily;
import cz.vutbr.web.css.NodeData.FontSize;
import cz.vutbr.web.css.NodeData.FontStyle;
import cz.vutbr.web.css.NodeData.FontVariant;
import cz.vutbr.web.css.NodeData.FontWeight;
import cz.vutbr.web.css.NodeData.Left;
import cz.vutbr.web.css.NodeData.LetterSpacing;
import cz.vutbr.web.css.NodeData.ListStyleImage;
import cz.vutbr.web.css.NodeData.ListStylePosition;
import cz.vutbr.web.css.NodeData.ListStyleType;
import cz.vutbr.web.css.NodeData.Margin;
import cz.vutbr.web.css.NodeData.MaxHeight;
import cz.vutbr.web.css.NodeData.MaxWidth;
import cz.vutbr.web.css.NodeData.MinHeight;
import cz.vutbr.web.css.NodeData.MinWidth;
import cz.vutbr.web.css.NodeData.Orphans;
import cz.vutbr.web.css.NodeData.OutlineColor;
import cz.vutbr.web.css.NodeData.OutlineStyle;
import cz.vutbr.web.css.NodeData.OutlineWidth;
import cz.vutbr.web.css.NodeData.Overflow;
import cz.vutbr.web.css.NodeData.Padding;
import cz.vutbr.web.css.NodeData.PageBreak;
import cz.vutbr.web.css.NodeData.PageBreakInside;
import cz.vutbr.web.css.NodeData.PauseBefore;
import cz.vutbr.web.css.NodeData.Pitch;
import cz.vutbr.web.css.NodeData.PitchRange;
import cz.vutbr.web.css.NodeData.PlayDuring;
import cz.vutbr.web.css.NodeData.Position;
import cz.vutbr.web.css.NodeData.Quotes;
import cz.vutbr.web.css.NodeData.Richness;
import cz.vutbr.web.css.NodeData.Right;
import cz.vutbr.web.css.NodeData.Speak;
import cz.vutbr.web.css.NodeData.SpeakHeader;
import cz.vutbr.web.css.NodeData.SpeakNumeral;
import cz.vutbr.web.css.NodeData.SpeakPunctuation;
import cz.vutbr.web.css.NodeData.SpeechRate;
import cz.vutbr.web.css.NodeData.Stress;
import cz.vutbr.web.css.NodeData.TableLayout;
import cz.vutbr.web.css.NodeData.TextAlign;
import cz.vutbr.web.css.NodeData.TextDecoration;
import cz.vutbr.web.css.NodeData.TextIndent;
import cz.vutbr.web.css.NodeData.TextTransform;
import cz.vutbr.web.css.NodeData.Top;
import cz.vutbr.web.css.NodeData.UnicodeBidi;
import cz.vutbr.web.css.NodeData.VerticalAlign;
import cz.vutbr.web.css.NodeData.Visibility;
import cz.vutbr.web.css.NodeData.Volume;
import cz.vutbr.web.css.NodeData.WhiteSpace;
import cz.vutbr.web.css.NodeData.Widows;
import cz.vutbr.web.css.NodeData.Width;
import cz.vutbr.web.css.NodeData.WordSpacing;
import cz.vutbr.web.css.NodeData.ZIndex;
import cz.vutbr.web.csskit.ColorCard;
import cz.vutbr.web.csskit.TermLengthImpl;

/**
 * Contains default values for properties supported by parser (CSS 2.1)
 * 
 * @author kapy
 * 
 */
public class SupportedCSS {

	public static final int TOTAL_SUPPORTED_DECLARATIONS = 117;

	public static Term<?> DEFAULT_UA_COLOR = ColorCard.getTermColor("black"); 
	public static CSSProperty DEFAULT_UA_FONT_FAMILY = FontFamily.SANS_SERIF;
	public static CSSProperty DEFAULT_UA_TEXT_ALIGN = TextAlign.BY_DIRECTION;
	public static Term<?> DEFAULT_UA_TEXT_IDENT = new TermLengthImpl(0.0f);
	
	/**
	 * Contains names of supported elements and default values according to <a
	 * href="http://www.culturedcode.com/css/reference.html">
	 * http://www.culturedcode.com/css/reference.html</a>
	 */
	private Map<String, CSSProperty> defaultCSSproperties;
	private Map<String, Term<?>> defaultCSSvalues;
	
	private Map<String, Integer> oridinal;

	private static final CSSProperty INHERITABLE_PROPERTY = new CSSProperty() {
		public boolean inherited() {
			return true;
		}
		public boolean equalsInherit() {
			return true;
		}
	};

	private static final CSSProperty NOT_INHERITABLE_PROPERTY = new CSSProperty() {
		public boolean inherited() {
			return false;
		}
		public boolean equalsInherit() {
			return false;
		}
	};

	private static final CSSProperty MULTIVALUE_PROPERTY = new CSSProperty() {
		public boolean inherited() {
			return false;
		}
		
		public boolean equalsInherit() {
			return false;
		}
	};

	private final static SupportedCSS instance;

	static {
		instance = new SupportedCSS();
	}

	public final static SupportedCSS getInstance() {
		return instance;
	}

	private SupportedCSS() {
		this.setSupportedCSS();
		this.oridinal = oridinal();
	}

	public final CSSProperty getDefaultCSSProperty(String property) {
		return defaultCSSproperties.get(property);
	}
	
	public final Term<?> getDefaultCSSValueTerm(String property) {
		return defaultCSSvalues.get(property);
	}
	
	public final int getOridinalValue(String property) {
		Integer i = oridinal.get(property);
		return (i==null) ? -1 : i.intValue();
	}

	public final int totalProperties() {
		return defaultCSSproperties.size();
	}

	public final Set<String> propertyNames() {
		return defaultCSSproperties.keySet();
	}

	/**
	 * Test function. Returns random supported CSS property
	 * 
	 * @return Default value of randomly chosen supported CSS property
	 */
	public final CSSProperty randomValue() {

		Random generator = new Random();

		int i = generator.nextInt(defaultCSSproperties.size());
		for (CSSProperty prop : defaultCSSproperties.values()) {
			if (i == 0)
				return prop;
			i--;
		}
		// should never reach this statement
		return null;
	}

	/**
	 * Constructs map of supported properties as keys and their default values
	 * 
	 * @return Constructed map
	 */
	private void setSupportedCSS() {

		Map<String, CSSProperty> props = new HashMap<String, CSSProperty>(
				TOTAL_SUPPORTED_DECLARATIONS, 1.0f);

		Map<String, Term<?>> values = new HashMap<String, Term<?>>(
				TOTAL_SUPPORTED_DECLARATIONS, 1.0f);
		
		// text type
		props.put("color", Color.color); 
		values.put("color", DEFAULT_UA_COLOR);
		props.put("font", Font.component_values);
		props.put("font-family", DEFAULT_UA_FONT_FAMILY);
		props.put("font-size", FontSize.MEDIUM);
		props.put("font-style", FontStyle.NORMAL);
		props.put("font-variant", FontVariant.NORMAL);
		props.put("font-weight", FontWeight.NORMAL);
		props.put("text-decoration", TextDecoration.NONE);
		props.put("text-transform", TextTransform.NONE);

		// text spacing
		map.put("white-space", WhiteSpace.NORMAL);
		map.put("text-align", DEFAULT_UA_TEXT_ALIGN);
		map.put("text-indent", TextIndent.length); // 0
		map.put("line-height", INHERITABLE_PROPERTY);
		map.put("word-spacing", WordSpacing.NORMAL);
		map.put("letter-spacing", LetterSpacing.NORMAL);
		map.put("vertical-align", VerticalAlign.BASELINE);
		map.put("direction", Direction.LTR);
		map.put("unicode-bidi", UnicodeBidi.NORMAL);

		// layout box
		map.put("margin", MULTIVALUE_PROPERTY);
		map.put("margin-top", Margin.lenght); // 0
		map.put("margin-right", Margin.lenght); // 0
		map.put("margin-bottom", Margin.lenght); // 0
		map.put("margin-left", Margin.lenght); // 0
		map.put("padding", MULTIVALUE_PROPERTY);
		map.put("padding-top", Padding.length); // 0
		map.put("padding-right", Padding.length); // 0
		map.put("padding-bottom", Padding.length);// 0
		map.put("padding-left", Padding.length); // 0
		map.put("border", MULTIVALUE_PROPERTY);
		map.put("border-width", BorderWidth.MEDIUM);
		map.put("border-top-width", BorderWidth.MEDIUM);
		map.put("border-right-width", BorderWidth.MEDIUM);
		map.put("border-bottom-width", BorderWidth.MEDIUM);
		map.put("border-left-width", BorderWidth.MEDIUM);
		map.put("border-style", BorderStyle.NONE);
		map.put("border-top-style", BorderStyle.NONE);
		map.put("border-right-style", BorderStyle.NONE);
		map.put("border-bottom-style", BorderStyle.NONE);
		map.put("border-left-style", BorderStyle.NONE);
		// default color is taken form color property
		map.put("border-color", NOT_INHERITABLE_PROPERTY);
		map.put("border-top-color", NOT_INHERITABLE_PROPERTY);
		map.put("border-right-color", NOT_INHERITABLE_PROPERTY);
		map.put("border-bottom-color", NOT_INHERITABLE_PROPERTY);
		map.put("border-left-color", NOT_INHERITABLE_PROPERTY);
		map.put("width", Width.AUTO);
		map.put("min-width", MinWidth.lenght); // 0
		map.put("max-width", MaxWidth.NONE);
		map.put("height", Width.AUTO);
		map.put("min-height", MinHeight.lenght); // 0
		map.put("max-height", MaxHeight.NONE);
		map.put("overflow", Overflow.VISIBLE);
		map.put("clip", Clip.AUTO);

		// positioning
		map.put("display", Display.INLINE);
		map.put("position", Position.STATIC);
		map.put("top", Top.AUTO);
		map.put("right", Right.AUTO);
		map.put("bottom", Bottom.AUTO);
		map.put("left", Left.AUTO);
		map.put("float", NodeData.Float.NONE);
		map.put("clear", Clear.NONE);
		map.put("z-index", ZIndex.AUTO);
		map.put("visibility", Visibility.VISIBLE);

		// background
		map.put("background", MULTIVALUE_PROPERTY);
		map.put("background-attachement", BackgroundAttachment.SCROLL);
		map.put("background-color", BackgroundColor.TRANSPARENT);
		map.put("background-image", BackgroundImage.NONE);
		map.put("background-position", MULTIVALUE_PROPERTY);
		map.put("background-repeat", BackgroundRepeat.REPEAT);

		// elements
		map.put("list-style", MULTIVALUE_PROPERTY);
		map.put("list-style-type", ListStyleType.DISC);
		map.put("list-style-position", ListStylePosition.OUTSIDE);
		map.put("list-style-image", ListStyleImage.NONE);

		map.put("border-collapse", BorderCollapse.SEPARATE);
		map.put("border-spacing", BorderSpacing.list_values); // 0 0
		map.put("empty-cells", EmptyCells.SHOW);
		map.put("table-layout", TableLayout.AUTO);
		map.put("caption-side", CaptionSide.TOP);
		// other supported by tables (width, vertical-align)
		// are already defined
		map.put("content", Content.NORMAL);
		map.put("quotes", Quotes.NONE);
		map.put("counter-increment", CounterIncrement.NONE);
		map.put("counter-reset", CounterReset.NONE);

		// miscellaneous

		map.put("cursor", Cursor.AUTO);
		map.put("outline", MULTIVALUE_PROPERTY);
		map.put("outline-width", OutlineWidth.MEDIUM);
		map.put("outline-style", OutlineStyle.NONE);
		map.put("outline-color", OutlineColor.INVERT);

		map.put("page-break", PageBreak.AUTO);
		map.put("page-break-before", PageBreak.AUTO);
		map.put("page-break-after", PageBreak.AUTO);
		map.put("page-break-inside", PageBreakInside.AUTO);

		map.put("widows", Widows.integer); // 2
		map.put("orphans", Orphans.integer); // 2

		// other values according to
		// http://www.w3.org/TR/CSS21/propidx.html

		map.put("azimuth", Azimuth.CENTER);
		map.put("border-top", MULTIVALUE_PROPERTY);
		map.put("border-right", MULTIVALUE_PROPERTY);
		map.put("border-bottom", MULTIVALUE_PROPERTY);
		map.put("border-left", MULTIVALUE_PROPERTY);

		map.put("cue", MULTIVALUE_PROPERTY);
		map.put("cue-before", CueBefore.NONE);
		map.put("cue-after", CueAfter.NONE);
		map.put("elevation", Elevation.LEVEL);
		map.put("pause", MULTIVALUE_PROPERTY);
		map.put("pause-before", PauseBefore.time); // 0
		map.put("pause-after", PauseBefore.time); // 0
		map.put("pitch-range", PitchRange.number); // 50
		map.put("pitch", Pitch.MEDIUM);
		map.put("play-during", PlayDuring.AUTO);
		map.put("richness", Richness.number); // 50
		map.put("speak-header", SpeakHeader.ONCE);
		map.put("speak-numeral", SpeakNumeral.CONTINUOUS);
		map.put("speak-punctuation", SpeakPunctuation.NONE);
		map.put("speak", Speak.NORMAL);
		map.put("speech-rate", SpeechRate.MEDIUM);
		map.put("stress", Stress.number); // 50
		map.put("voice-family", INHERITABLE_PROPERTY);
		map.put("volume", Volume.MEDIUM);

		return map;
	}
	
	private Map<String, Integer> oridinal() {
		Map<String, Integer> map = new HashMap<String, Integer>(totalProperties(), 1.0f);
		
		int i = 0;
		for(String key: defaultCSSproperties.keySet()) {
			map.put(key, i++);
		}
		
		return map;
	}
	
}
