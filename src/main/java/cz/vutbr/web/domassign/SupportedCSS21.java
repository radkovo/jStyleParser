package cz.vutbr.web.domassign;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.CSSProperty.Height;
import cz.vutbr.web.css.SupportedCSS;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.CSSProperty.Azimuth;
import cz.vutbr.web.css.CSSProperty.Background;
import cz.vutbr.web.css.CSSProperty.BackgroundAttachment;
import cz.vutbr.web.css.CSSProperty.BackgroundColor;
import cz.vutbr.web.css.CSSProperty.BackgroundImage;
import cz.vutbr.web.css.CSSProperty.BackgroundPosition;
import cz.vutbr.web.css.CSSProperty.BackgroundRepeat;
import cz.vutbr.web.css.CSSProperty.Border;
import cz.vutbr.web.css.CSSProperty.BorderCollapse;
import cz.vutbr.web.css.CSSProperty.BorderColor;
import cz.vutbr.web.css.CSSProperty.BorderSpacing;
import cz.vutbr.web.css.CSSProperty.BorderStyle;
import cz.vutbr.web.css.CSSProperty.BorderWidth;
import cz.vutbr.web.css.CSSProperty.Bottom;
import cz.vutbr.web.css.CSSProperty.CaptionSide;
import cz.vutbr.web.css.CSSProperty.Clear;
import cz.vutbr.web.css.CSSProperty.Clip;
import cz.vutbr.web.css.CSSProperty.Color;
import cz.vutbr.web.css.CSSProperty.Content;
import cz.vutbr.web.css.CSSProperty.CounterIncrement;
import cz.vutbr.web.css.CSSProperty.CounterReset;
import cz.vutbr.web.css.CSSProperty.Cue;
import cz.vutbr.web.css.CSSProperty.Cursor;
import cz.vutbr.web.css.CSSProperty.Direction;
import cz.vutbr.web.css.CSSProperty.Display;
import cz.vutbr.web.css.CSSProperty.Elevation;
import cz.vutbr.web.css.CSSProperty.EmptyCells;
import cz.vutbr.web.css.CSSProperty.Font;
import cz.vutbr.web.css.CSSProperty.FontFamily;
import cz.vutbr.web.css.CSSProperty.FontSize;
import cz.vutbr.web.css.CSSProperty.FontStyle;
import cz.vutbr.web.css.CSSProperty.FontVariant;
import cz.vutbr.web.css.CSSProperty.FontWeight;
import cz.vutbr.web.css.CSSProperty.Left;
import cz.vutbr.web.css.CSSProperty.LetterSpacing;
import cz.vutbr.web.css.CSSProperty.LineHeight;
import cz.vutbr.web.css.CSSProperty.ListStyle;
import cz.vutbr.web.css.CSSProperty.ListStyleImage;
import cz.vutbr.web.css.CSSProperty.ListStylePosition;
import cz.vutbr.web.css.CSSProperty.ListStyleType;
import cz.vutbr.web.css.CSSProperty.Margin;
import cz.vutbr.web.css.CSSProperty.MaxHeight;
import cz.vutbr.web.css.CSSProperty.MaxWidth;
import cz.vutbr.web.css.CSSProperty.MinHeight;
import cz.vutbr.web.css.CSSProperty.MinWidth;
import cz.vutbr.web.css.CSSProperty.Orphans;
import cz.vutbr.web.css.CSSProperty.Outline;
import cz.vutbr.web.css.CSSProperty.OutlineColor;
import cz.vutbr.web.css.CSSProperty.OutlineStyle;
import cz.vutbr.web.css.CSSProperty.OutlineWidth;
import cz.vutbr.web.css.CSSProperty.Overflow;
import cz.vutbr.web.css.CSSProperty.Padding;
import cz.vutbr.web.css.CSSProperty.PageBreak;
import cz.vutbr.web.css.CSSProperty.PageBreakInside;
import cz.vutbr.web.css.CSSProperty.Pause;
import cz.vutbr.web.css.CSSProperty.Pitch;
import cz.vutbr.web.css.CSSProperty.PitchRange;
import cz.vutbr.web.css.CSSProperty.PlayDuring;
import cz.vutbr.web.css.CSSProperty.Position;
import cz.vutbr.web.css.CSSProperty.Quotes;
import cz.vutbr.web.css.CSSProperty.Richness;
import cz.vutbr.web.css.CSSProperty.Right;
import cz.vutbr.web.css.CSSProperty.Speak;
import cz.vutbr.web.css.CSSProperty.SpeakHeader;
import cz.vutbr.web.css.CSSProperty.SpeakNumeral;
import cz.vutbr.web.css.CSSProperty.SpeakPunctuation;
import cz.vutbr.web.css.CSSProperty.SpeechRate;
import cz.vutbr.web.css.CSSProperty.Stress;
import cz.vutbr.web.css.CSSProperty.TableLayout;
import cz.vutbr.web.css.CSSProperty.TextAlign;
import cz.vutbr.web.css.CSSProperty.TextDecoration;
import cz.vutbr.web.css.CSSProperty.TextIndent;
import cz.vutbr.web.css.CSSProperty.TextTransform;
import cz.vutbr.web.css.CSSProperty.Top;
import cz.vutbr.web.css.CSSProperty.UnicodeBidi;
import cz.vutbr.web.css.CSSProperty.VerticalAlign;
import cz.vutbr.web.css.CSSProperty.Visibility;
import cz.vutbr.web.css.CSSProperty.VoiceFamily;
import cz.vutbr.web.css.CSSProperty.Volume;
import cz.vutbr.web.css.CSSProperty.WhiteSpace;
import cz.vutbr.web.css.CSSProperty.Widows;
import cz.vutbr.web.css.CSSProperty.Width;
import cz.vutbr.web.css.CSSProperty.WordSpacing;
import cz.vutbr.web.css.CSSProperty.ZIndex;

/**
 * Contains default values for properties supported by parser (CSS 2.1)
 * 
 * @author kapy
 * 
 */
public class SupportedCSS21 implements SupportedCSS {
	private static Logger log = LoggerFactory.getLogger(SupportedCSS21.class);

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
	private static final TermList DEFAULT_UA_BACKGROUND_POSITION = tf
			.createList(2);
	static {
		DEFAULT_UA_BACKGROUND_POSITION.add(tf.createPercent(0.0f));
		DEFAULT_UA_BACKGROUND_POSITION.add(tf.createPercent(0.0f));
	}
	private static final TermList DEFAULT_UA_BORDER_SPACING = tf.createList(2);
	static {
		DEFAULT_UA_BORDER_SPACING.add(tf.createLength(0.0f));
		DEFAULT_UA_BORDER_SPACING.add(tf.createLength(0.0f));
	}
	private static final Term<?> DEFAULT_UA_WIDOWS = tf.createInteger(2);
	private static final Term<?> DEFAULT_UA_ORPHANS = tf.createInteger(2);
	private static final Term<?> DEFAULT_UA_PAUSE_BEFORE = tf.createTime(0.0f);
	private static final Term<?> DEFAULT_UA_PAUSE_AFTER = tf.createTime(0.0f);
	private static final Term<?> DEFAULT_UA_RICHNESS = tf.createNumber(50.0f);
	private static final Term<?> DEFAULT_UA_PITCH_RANGE = tf
			.createNumber(50.0f);
	private static final Term<?> DEFAULT_UA_STRESS = tf.createNumber(50.0f);

	private static final CSSProperty DEFAULT_UA_VOICE_FAMILY = VoiceFamily.MALE;;

	private final static SupportedCSS21 instance;
	static {
		instance = new SupportedCSS21();
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

	private Set<String> supportedMedias;

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
		this.setSupportedAtKeywords();
	}

	public boolean isSupportedMedia(String media) {
		if (media == null)
			return false;

		return supportedMedias.contains(media.toLowerCase());
	}

	public final boolean isSupportedCSSProperty(String property) {
		return defaultCSSproperties.get(property) != null;
	}

	public final CSSProperty getDefaultProperty(String property) {
		CSSProperty value = defaultCSSproperties.get(property);
		log.debug("Asked for property {}'s default value: {}", property, value);
		return value;
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
	 * Constructs maps of CSSProperties and its values (Term<?) according to CSS
	 * 2.1 definition. Called during construction of object.
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
		props.put("margin-top", Margin.length);
		values.put("margin-top", DEFAULT_UA_MARGIN);
		props.put("margin-right", Margin.length);
		values.put("margin-right", DEFAULT_UA_MARGIN);
		props.put("margin-bottom", Margin.length);
		values.put("margin-bottom", DEFAULT_UA_MARGIN);
		props.put("margin-left", Margin.length);
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
		props.put("min-width", MinWidth.length);
		values.put("min-width", DEFAULT_UA_MIN_WIDTH);
		props.put("max-width", MaxWidth.NONE);
		props.put("height", Height.AUTO);
		props.put("min-height", MinHeight.length);
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
		props.put("float", CSSProperty.Float.NONE);
		props.put("clear", Clear.NONE);
		props.put("z-index", ZIndex.AUTO);
		props.put("visibility", Visibility.VISIBLE);

		// background
		props.put("background", Background.component_values);
		props.put("background-attachment", BackgroundAttachment.SCROLL);
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

	private void setSupportedAtKeywords() {
		
		Set<String> set = new HashSet<String>(Arrays.asList(
				"all",
				"braille",
				"embossed",
				"handheld",
				"print",
				"projection",
				"screen",
				"speech",
				"tty",
				"tv"));

		this.supportedMedias = set;
	}

}
