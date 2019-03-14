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
import cz.vutbr.web.css.SupportedCSS;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.CSSProperty.*;

/**
 * Contains default values for properties supported by parser (CSS 3)
 * 
 * @author kapy
 * @author burgetr
 */
public class SupportedCSS3 implements SupportedCSS {
	private static final Logger log = LoggerFactory.getLogger(SupportedCSS3.class);

	private static final int TOTAL_SUPPORTED_DECLARATIONS = 169;

	private static final TermFactory tf = CSSFactory.getTermFactory();

	private static final CSSProperty DEFAULT_UA_FONT_FAMILY = FontFamily.SANS_SERIF;
	private static final CSSProperty DEFAULT_UA_TEXT_ALIGN = TextAlign.BY_DIRECTION;
    private static final Term<?> DEFAULT_UA_COLOR = tf.createColor("#000000");
    private static final Term<?> DEFAULT_UA_OPACITY = tf.createNumber(1.0f);
	private static final Term<?> DEFAULT_UA_TEXT_IDENT = tf.createLength(0.0f);
	private static final Term<?> DEFAULT_UA_TAB_SIZE = tf.createInteger(8);
	private static final Term<?> DEFAULT_UA_MARGIN = tf.createLength(0.0f);
	private static final Term<?> DEFAULT_UA_PADDING = tf.createLength(0.0f);
	private static final Term<?> DEFAULT_UA_MIN_WIDTH = tf.createLength(0.0f);
	private static final Term<?> DEFAULT_UA_MIN_HEIGHT = tf.createLength(0.0f);
    private static final Term<?> DEFAULT_BORDER_COLOR = tf.createColor(tf.createIdent("currentColor"));
	private static final Term<?> DEFAULT_BACKGROUND_COLOR = tf.createColor(tf.createIdent("transparent"));
	private static final TermList DEFAULT_UA_BACKGROUND_POSITION = tf.createList(2);

    private static final Term<?> DEFAULT_FLEX_SHRINK = tf.createNumber(1.0f);
    private static final Term<?> DEFAULT_FLEX_GROW = tf.createNumber(0.0f);
    private static final Term<?> DEFAULT_ORDER = tf.createInteger(0);
    private static final Term<?> DEFAULT_TIME = tf.createTime(0f);
    private static final Term<?> DEFAULT_ITERATION_COUNT = tf.createInteger(1);

	static {
		DEFAULT_UA_BACKGROUND_POSITION.add(tf.createPercent(0.0f));
		DEFAULT_UA_BACKGROUND_POSITION.add(tf.createPercent(0.0f));
	}
    private static final TermList DEFAULT_UA_BACKGROUND_SIZE = tf.createList(2);
    static {
        DEFAULT_UA_BACKGROUND_SIZE.add(tf.createIdent("auto"));
        DEFAULT_UA_BACKGROUND_SIZE.add(tf.createIdent("auto"));
    }
	private static final TermList DEFAULT_UA_BORDER_RADIUS = tf.createList(2);
    static {
        DEFAULT_UA_BORDER_RADIUS.add(tf.createLength(0.0f));
        DEFAULT_UA_BORDER_RADIUS.add(tf.createLength(0.0f));
    }
	private static final TermList DEFAULT_UA_BORDER_SPACING = tf.createList(2);
	static {
		DEFAULT_UA_BORDER_SPACING.add(tf.createLength(0.0f));
		DEFAULT_UA_BORDER_SPACING.add(tf.createLength(0.0f));
	}
    private static final TermList DEFAULT_UA_TRANSFORM_ORIGIN = tf.createList(3);
    static {
        DEFAULT_UA_TRANSFORM_ORIGIN.add(tf.createPercent(50.0f));
        DEFAULT_UA_TRANSFORM_ORIGIN.add(tf.createPercent(50.0f));
        DEFAULT_UA_TRANSFORM_ORIGIN.add(tf.createLength(0.0f));
    }
	private static final Term<?> DEFAULT_UA_WIDOWS = tf.createInteger(2);
	private static final Term<?> DEFAULT_UA_ORPHANS = tf.createInteger(2);
	private static final Term<?> DEFAULT_UA_PAUSE_BEFORE = tf.createTime(0.0f);
	private static final Term<?> DEFAULT_UA_PAUSE_AFTER = tf.createTime(0.0f);
	private static final Term<?> DEFAULT_UA_RICHNESS = tf.createNumber(50.0f);
	private static final Term<?> DEFAULT_UA_PITCH_RANGE = tf
			.createNumber(50.0f);
	private static final Term<?> DEFAULT_UA_STRESS = tf.createNumber(50.0f);

	private static final CSSProperty DEFAULT_UA_VOICE_FAMILY = VoiceFamily.MALE;

    private final static SupportedCSS3 instance;
	static {
		instance = new SupportedCSS3();
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

	private Set<String> supportedMedia;

	/**
	 * Gets instance of SupportedCSS21
	 * 
	 * @return Singleton instance reference
	 */
	public final static SupportedCSS3 getInstance() {
		return instance;
	}

	private SupportedCSS3() {
		this.setSupportedCSS();
		this.setOridinals();
		this.setSupportedAtKeywords();
	}

	public boolean isSupportedMedia(String media) {
		if (media == null)
			return false;

		return supportedMedia.contains(media.toLowerCase());
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
		props.put("opacity", Opacity.number);
		values.put("opacity", DEFAULT_UA_OPACITY);
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
		props.put("tab-size", TabSize.integer);
		values.put("tab-size", DEFAULT_UA_TAB_SIZE);

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

		props.put("border-color", BorderColor.component_values);
		props.put("border-top-color", BorderColor.color);
        values.put("border-top-color", DEFAULT_BORDER_COLOR);
		props.put("border-right-color", BorderColor.color);
		values.put("border-right-color", DEFAULT_BORDER_COLOR);
		props.put("border-bottom-color", BorderColor.color);
		values.put("border-bottom-color", DEFAULT_BORDER_COLOR);
		props.put("border-left-color", BorderColor.color);
		values.put("border-left-color", DEFAULT_BORDER_COLOR);

        props.put("border-radius", BorderRadius.component_values);
		props.put("border-top-left-radius", BorderRadius.list_values);
		values.put("border-top-left-radius", DEFAULT_UA_BORDER_RADIUS);
        props.put("border-top-right-radius", BorderRadius.list_values);
        values.put("border-top-right-radius", DEFAULT_UA_BORDER_RADIUS);
        props.put("border-bottom-right-radius", BorderRadius.list_values);
        values.put("border-bottom-right-radius", DEFAULT_UA_BORDER_RADIUS);
        props.put("border-bottom-left-radius", BorderRadius.list_values);
        values.put("border-bottom-left-radius", DEFAULT_UA_BORDER_RADIUS);
		
		props.put("width", Width.AUTO);
		props.put("min-width", MinWidth.length);
		values.put("min-width", DEFAULT_UA_MIN_WIDTH);
		props.put("max-width", MaxWidth.NONE);
		props.put("height", Height.AUTO);
		props.put("min-height", MinHeight.length);
		values.put("min-height", DEFAULT_UA_MIN_HEIGHT);
		props.put("max-height", MaxHeight.NONE);
		props.put("overflow", Overflow.component_values);
        props.put("overflow-x", Overflow.VISIBLE);
        props.put("overflow-y", Overflow.VISIBLE);
		props.put("clip", Clip.AUTO);
		props.put("box-sizing", BoxSizing.CONTENT_BOX);
        props.put("box-shadow", BoxShadow.NONE);

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
		props.put("transform", Transform.NONE);
		props.put("transform-origin", TransformOrigin.list_values);
		values.put("transform-origin", DEFAULT_UA_TRANSFORM_ORIGIN);

		// background
		props.put("background", Background.component_values);
		props.put("background-attachment", BackgroundAttachment.SCROLL);
		props.put("background-color", BackgroundColor.color);
		values.put("background-color", DEFAULT_BACKGROUND_COLOR);
		props.put("background-image", BackgroundImage.NONE);
		props.put("background-position", BackgroundPosition.list_values);
		values.put("background-position", DEFAULT_UA_BACKGROUND_POSITION);
		props.put("background-size", BackgroundSize.list_values);
        values.put("background-size", DEFAULT_UA_BACKGROUND_SIZE);
		props.put("background-repeat", BackgroundRepeat.REPEAT);
		
		// box shadow
		props.put("box-shadow", BoxShadow.NONE);

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

		// filter
		props.put("filter", Filter.NONE);
        props.put("backdrop-filter", BackdropFilter.NONE);
		
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

		// Flexbox
		props.put("flex-direction", FlexDirection.ROW);
		props.put("flex-wrap", FlexWrap.NOWRAP);
		props.put("flex-basis", FlexBasis.AUTO);
		props.put("flex-grow", FlexGrow.number);
		values.put("flex-grow", DEFAULT_FLEX_GROW);
		props.put("flex-shrink", FlexShrink.number);
		values.put("flex-shrink", DEFAULT_FLEX_SHRINK);
		props.put("order", Order.integer);
		values.put("order", DEFAULT_ORDER);
		props.put("justify-content", JustifyContent.FlexStart);
		props.put("align-content", AlignContent.Stretch);
		props.put("align-items", AlignItems.Stretch);
		props.put("align-self", AlignSelf.Auto);

        // grid layout
        props.put("grid", Grid.component_values);
        props.put("grid-gap", GridGap.component_values);
        props.put("grid-row-gap", GridGap.NORMAL);
        props.put("grid-column-gap", GridGap.NORMAL);
        props.put("grid-area", Grid.component_values);
        props.put("grid-row", Grid.component_values);
        props.put("grid-column", Grid.component_values);
        props.put("grid-row-start", GridStartEnd.AUTO);
        props.put("grid-column-start", GridStartEnd.AUTO);
        props.put("grid-row-end", GridStartEnd.AUTO);
        props.put("grid-column-end", GridStartEnd.AUTO);
        props.put("grid-template", Grid.component_values);
        props.put("grid-template-areas", GridTemplateAreas.NONE);
        props.put("grid-template-rows", GridTemplateRowsColumns.NONE);
        props.put("grid-template-columns", GridTemplateRowsColumns.NONE);
        props.put("grid-auto-flow", GridAutoFlow.ROW);
        props.put("grid-auto-rows", GridAutoRowsColumns.AUTO);
        props.put("grid-auto-columns", GridAutoRowsColumns.AUTO);
        
        // animation
        props.put("animation", Animation.component_values);
        props.put("animation-delay", AnimationDelay.time);
        values.put("animation-delay", DEFAULT_TIME);
        props.put("animation-direction", AnimationDirection.NORMAL);
        props.put("animation-duration", AnimationDuration.time);
        values.put("animation-duration", DEFAULT_TIME);
        props.put("animation-fill-mode", AnimationFillMode.NONE);
        props.put("animation-iteration-count", AnimationIterationCount.number);
        values.put("animation-iteration-count", DEFAULT_ITERATION_COUNT);
        props.put("animation-name", AnimationName.NONE);
        props.put("animation-play-state", AnimationPlayState.RUNNING);
        props.put("animation-timing-function", AnimationTimingFunction.EASE);

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

		this.supportedMedia = set;
	}

}
