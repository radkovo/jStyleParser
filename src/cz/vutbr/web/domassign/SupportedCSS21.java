package cz.vutbr.web.domassign;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.SupportedCSS;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.NodeData.Azimuth;
import cz.vutbr.web.css.NodeData.Background;
import cz.vutbr.web.css.NodeData.BackgroundAttachment;
import cz.vutbr.web.css.NodeData.BackgroundColor;
import cz.vutbr.web.css.NodeData.BackgroundImage;
import cz.vutbr.web.css.NodeData.BackgroundPosition;
import cz.vutbr.web.css.NodeData.BackgroundRepeat;
import cz.vutbr.web.css.NodeData.Border;
import cz.vutbr.web.css.NodeData.BorderCollapse;
import cz.vutbr.web.css.NodeData.BorderColor;
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
import cz.vutbr.web.css.NodeData.Cue;
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
import cz.vutbr.web.css.NodeData.LineHeight;
import cz.vutbr.web.css.NodeData.ListStyle;
import cz.vutbr.web.css.NodeData.ListStyleImage;
import cz.vutbr.web.css.NodeData.ListStylePosition;
import cz.vutbr.web.css.NodeData.ListStyleType;
import cz.vutbr.web.css.NodeData.Margin;
import cz.vutbr.web.css.NodeData.MaxHeight;
import cz.vutbr.web.css.NodeData.MaxWidth;
import cz.vutbr.web.css.NodeData.MinHeight;
import cz.vutbr.web.css.NodeData.MinWidth;
import cz.vutbr.web.css.NodeData.Orphans;
import cz.vutbr.web.css.NodeData.Outline;
import cz.vutbr.web.css.NodeData.OutlineColor;
import cz.vutbr.web.css.NodeData.OutlineStyle;
import cz.vutbr.web.css.NodeData.OutlineWidth;
import cz.vutbr.web.css.NodeData.Overflow;
import cz.vutbr.web.css.NodeData.Padding;
import cz.vutbr.web.css.NodeData.PageBreak;
import cz.vutbr.web.css.NodeData.PageBreakInside;
import cz.vutbr.web.css.NodeData.Pause;
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
import cz.vutbr.web.css.NodeData.VoiceFamily;
import cz.vutbr.web.css.NodeData.Volume;
import cz.vutbr.web.css.NodeData.WhiteSpace;
import cz.vutbr.web.css.NodeData.Widows;
import cz.vutbr.web.css.NodeData.Width;
import cz.vutbr.web.css.NodeData.WordSpacing;
import cz.vutbr.web.css.NodeData.ZIndex;

/**
 * Contains default values for properties supported by parser (CSS 2.1)
 * 
 * @author kapy
 * 
 */
public class SupportedCSS21 implements SupportedCSS {

	private static final int TOTAL_SUPPORTED_DECLARATIONS = 116;

	private static final TermFactory tf = CSSFactory.getTermFactory();

	private static final CSSProperty DEFAULT_UA_FONT_FAMILY = FontFamily.SANS_SERIF;
	private static final CSSProperty DEFAULT_UA_TEXT_ALIGN = TextAlign.BY_DIRECTION;;
	private static final Term<?> DEFAULT_UA_COLOR = tf.createColor("#000000");;
	private static final Term<?> DEFAULT_UA_TEXT_IDENT = tf.createLength(0.0f);
	private static final Term<?> DEFAULT_UA_MARGIN = tf.createLength(0.0f);
	private static final Term<?> DEFAULT_UA_PADDING = tf.createLength(0.0f);
	private static final Term<?> DEFAULT_UA_MIN_WIDTH = tf.createLength(0.0f);
	private static final Term<?> DEFAULT_UA_MIN_HEIGHT = tf.createLength(0.0f);
	private static final TermList DEFAULT_UA_BACKGROUND_POSITION	= tf.createList(2);
	static {
		DEFAULT_UA_BACKGROUND_POSITION.add(tf.createPercent(0.0f));
		DEFAULT_UA_BACKGROUND_POSITION.add(tf.createPercent(0.0f));
	}
	private static final TermList DEFAULT_UA_BORDER_SPACING  = tf.createList(2);
	static {
		DEFAULT_UA_BORDER_SPACING.add(tf.createLength(0.0f));
		DEFAULT_UA_BORDER_SPACING.add(tf.createLength(0.0f));
	}
	private static final Term<?> DEFAULT_UA_WIDOWS = tf.createInteger(2);
	private static final Term<?> DEFAULT_UA_ORPHANS = tf.createInteger(2);
	private static final Term<?> DEFAULT_UA_PAUSE_BEFORE = tf.createTime(0.0f);
	private static final Term<?> DEFAULT_UA_PAUSE_AFTER = tf.createTime(0.0f);
	private static final Term<?> DEFAULT_UA_RICHNESS = tf.createNumber(50.0f);
	private static final Term<?> DEFAULT_UA_PITCH_RANGE = tf.createNumber(50.0f);
	private static final Term<?> DEFAULT_UA_STRESS = tf.createNumber(50.0f);

	private static final CSSProperty DEFAULT_UA_VOICE_FAMILY = VoiceFamily.MALE;;


	private final static SupportedCSS21 instance;
	static {
		instance = new SupportedCSS21();
		CSSFactory.registerSupportedCSS(instance);
	}

	/**
	 * Contains names of supported elements and default values according to <a
	 * href="http://www.culturedcode.com/css/reference.html">
	 * http://www.culturedcode.com/css/reference.html</a>
	 */
	private Map<String, CSSProperty> defaultCSSproperties;
	private Map<String, Term<?>> defaultCSSvalues;

	private Map<String, Integer> ordinals;
	private Map<Integer, String> ordinalsRev;

	/**
	 * Gets instance of SupportedCSS21
	 * 
	 * @return Singleton instance reference
	 */
	public final static SupportedCSS21 getInstance() {
		return instance;
	}

	private SupportedCSS21() {
		this.setSupportedCSS();
		this.setOridinals();
	}

	public final CSSProperty getDefaultProperty(String property) {
		return defaultCSSproperties.get(property);
	}

	public final Term<?> getDefaultValue(String property) {
		return defaultCSSvalues.get(property);
	}

	public final int getTotalProperties() {
		return defaultCSSproperties.size();
	}

	public final Set<String> getDefinedPropertyNames() {
		return defaultCSSproperties.keySet();
	}

	public String getRandomPropertyName() {
		final Random generator = new Random();
		int o = generator.nextInt(getTotalProperties());
		return getPropertyName(o);
	}
	
	public int getOrdinal(String propertyName) {		
		Integer i = ordinals.get(propertyName);
		return (i == null) ? -1 : i.intValue();
	}
	
	public String getPropertyName(int o) {
		return ordinalsRev.get(o);
	}
	
	/**
	 * Constructs maps of CSSProperties and its values (Term<?) according
	 * to CSS 2.1 definition. Called during construction of object.
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
		props.put("white-space", WhiteSpace.NORMAL);
		props.put("text-align", DEFAULT_UA_TEXT_ALIGN);
		props.put("text-indent", TextIndent.length);
		values.put("text-indent", DEFAULT_UA_TEXT_IDENT);
		props.put("line-height", LineHeight.NORMAL);
		props.put("word-spacing", WordSpacing.NORMAL);
		props.put("letter-spacing", LetterSpacing.NORMAL);
		props.put("vertical-align", VerticalAlign.BASELINE);
		props.put("direction", Direction.LTR);
		props.put("unicode-bidi", UnicodeBidi.NORMAL);

		// layout box
		props.put("margin", Margin.component_values);
		props.put("margin-top", Margin.lenght);
		values.put("margin-top", DEFAULT_UA_MARGIN);
		props.put("margin-right", Margin.lenght);
		values.put("margin-right", DEFAULT_UA_MARGIN);
		props.put("margin-bottom", Margin.lenght);
		values.put("margin-bottom", DEFAULT_UA_MARGIN);
		props.put("margin-left", Margin.lenght);
		values.put("margin-left", DEFAULT_UA_MARGIN);

		props.put("padding", Padding.component_values);
		props.put("padding-top", Padding.length);
		values.put("padding-top", DEFAULT_UA_PADDING);
		props.put("padding-right", Padding.length);
		values.put("padding-right", DEFAULT_UA_PADDING);
		props.put("padding-bottom", Padding.length);
		values.put("padding-bottom", DEFAULT_UA_PADDING);
		props.put("padding-left", Padding.length);
		values.put("padding-left", DEFAULT_UA_PADDING);

		props.put("border", Border.component_values);
		props.put("border-top", Border.component_values);
		props.put("border-right", Border.component_values);
		props.put("border-bottom", Border.component_values);
		props.put("border-left", Border.component_values);

		props.put("border-width", BorderWidth.component_values);
		props.put("border-top-width", BorderWidth.MEDIUM);
		props.put("border-right-width", BorderWidth.MEDIUM);
		props.put("border-bottom-width", BorderWidth.MEDIUM);
		props.put("border-left-width", BorderWidth.MEDIUM);
		props.put("border-style", BorderStyle.component_values);
		props.put("border-top-style", BorderStyle.NONE);
		props.put("border-right-style", BorderStyle.NONE);
		props.put("border-bottom-style", BorderStyle.NONE);
		props.put("border-left-style", BorderStyle.NONE);

		// default color is taken form color property
		props.put("border-color", BorderColor.component_values);
		props.put("border-top-color", BorderColor.taken);
		props.put("border-right-color", BorderColor.taken);
		props.put("border-bottom-color", BorderColor.taken);
		props.put("border-left-color", BorderColor.taken);

		props.put("width", Width.AUTO);
		props.put("min-width", MinWidth.lenght);
		values.put("min-width", DEFAULT_UA_MIN_WIDTH);
		props.put("max-width", MaxWidth.NONE);
		props.put("height", Width.AUTO);
		props.put("min-height", MinHeight.lenght);
		values.put("min-height", DEFAULT_UA_MIN_HEIGHT);
		props.put("max-height", MaxHeight.NONE);
		props.put("overflow", Overflow.VISIBLE);
		props.put("clip", Clip.AUTO);

		// positioning
		props.put("display", Display.INLINE);
		props.put("position", Position.STATIC);
		props.put("top", Top.AUTO);
		props.put("right", Right.AUTO);
		props.put("bottom", Bottom.AUTO);
		props.put("left", Left.AUTO);
		props.put("float", NodeData.Float.NONE);
		props.put("clear", Clear.NONE);
		props.put("z-index", ZIndex.AUTO);
		props.put("visibility", Visibility.VISIBLE);

		// background
		props.put("background", Background.component_values);
		props.put("background-attachement", BackgroundAttachment.SCROLL);
		props.put("background-color", BackgroundColor.TRANSPARENT);
		props.put("background-image", BackgroundImage.NONE);
		props.put("background-position", BackgroundPosition.list_values);
		values.put("background-position", DEFAULT_UA_BACKGROUND_POSITION);
		props.put("background-repeat", BackgroundRepeat.REPEAT);

		// elements
		props.put("list-style", ListStyle.component_values);
		props.put("list-style-type", ListStyleType.DISC);
		props.put("list-style-position", ListStylePosition.OUTSIDE);
		props.put("list-style-image", ListStyleImage.NONE);

		props.put("border-collapse", BorderCollapse.SEPARATE);
		props.put("border-spacing", BorderSpacing.list_values);
		values.put("border-spacing", DEFAULT_UA_BORDER_SPACING);
		props.put("empty-cells", EmptyCells.SHOW);
		props.put("table-layout", TableLayout.AUTO);
		props.put("caption-side", CaptionSide.TOP);

		// other supported by tables (width, vertical-align)
		// are already defined
		props.put("content", Content.NORMAL);
		props.put("quotes", Quotes.NONE);
		props.put("counter-increment", CounterIncrement.NONE);
		props.put("counter-reset", CounterReset.NONE);

		// miscellaneous

		props.put("cursor", Cursor.AUTO);
		props.put("outline", Outline.component_values);
		props.put("outline-width", OutlineWidth.MEDIUM);
		props.put("outline-style", OutlineStyle.NONE);
		props.put("outline-color", OutlineColor.INVERT);

		props.put("page-break", PageBreak.AUTO);
		props.put("page-break-before", PageBreak.AUTO);
		props.put("page-break-after", PageBreak.AUTO);
		props.put("page-break-inside", PageBreakInside.AUTO);

		props.put("widows", Widows.integer);
		values.put("widows", DEFAULT_UA_WIDOWS);
		props.put("orphans", Orphans.integer);
		values.put("orphans", DEFAULT_UA_ORPHANS);

		// other values according to
		// http://www.w3.org/TR/CSS21/propidx.html

		props.put("azimuth", Azimuth.CENTER);
		props.put("cue", Cue.component_values);
		props.put("cue-before", Cue.NONE);
		props.put("cue-after", Cue.NONE);
		props.put("elevation", Elevation.LEVEL);
		props.put("pause", Pause.component_values);
		props.put("pause-before", Pause.time);
		values.put("pause-before", DEFAULT_UA_PAUSE_BEFORE);
		props.put("pause-after", Pause.time);
		values.put("pause-after", DEFAULT_UA_PAUSE_AFTER);
		props.put("pitch-range", PitchRange.number);
		values.put("pitch-range", DEFAULT_UA_PITCH_RANGE);
		props.put("pitch", Pitch.MEDIUM);
		props.put("play-during", PlayDuring.AUTO);
		props.put("richness", Richness.number);
		values.put("richness", DEFAULT_UA_RICHNESS);
		props.put("speak-header", SpeakHeader.ONCE);
		props.put("speak-numeral", SpeakNumeral.CONTINUOUS);
		props.put("speak-punctuation", SpeakPunctuation.NONE);
		props.put("speak", Speak.NORMAL);
		props.put("speech-rate", SpeechRate.MEDIUM);
		props.put("stress", Stress.number);
		values.put("stress", DEFAULT_UA_STRESS);
		props.put("voice-family", DEFAULT_UA_VOICE_FAMILY);
		props.put("volume", Volume.MEDIUM);

		this.defaultCSSproperties = props;
		this.defaultCSSvalues = values;
	}

	private void setOridinals() {

		Map<String, Integer> ords = new HashMap<String, Integer>(
				getTotalProperties(), 1.0f);
		Map<Integer, String> ordsRev = new HashMap<Integer, String>(
				getTotalProperties(), 1.0f);

		int i = 0;
		for (String key : defaultCSSproperties.keySet()) {
			ords.put(key, i);
			ordsRev.put(i, key);
			i++;
		}

		this.ordinals = ords;
		this.ordinalsRev = ordsRev;

	}

}
