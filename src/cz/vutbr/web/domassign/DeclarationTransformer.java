package cz.vutbr.web.domassign;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermInteger;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermNumber;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.css.TermURI;
import cz.vutbr.web.css.NodeData.Azimuth;
import cz.vutbr.web.css.NodeData.BackgroundAttachment;
import cz.vutbr.web.css.NodeData.BackgroundColor;
import cz.vutbr.web.css.NodeData.BackgroundImage;
import cz.vutbr.web.css.NodeData.BackgroundRepeat;
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
import cz.vutbr.web.css.NodeData.CueAfter;
import cz.vutbr.web.css.NodeData.CueBefore;
import cz.vutbr.web.css.NodeData.Cursor;
import cz.vutbr.web.css.NodeData.Direction;
import cz.vutbr.web.css.NodeData.Display;
import cz.vutbr.web.css.NodeData.Elevation;
import cz.vutbr.web.css.NodeData.EmptyCells;
import cz.vutbr.web.css.NodeData.FontSize;
import cz.vutbr.web.css.NodeData.FontStyle;
import cz.vutbr.web.css.NodeData.FontVariant;
import cz.vutbr.web.css.NodeData.FontWeight;
import cz.vutbr.web.css.NodeData.Height;
import cz.vutbr.web.css.NodeData.Left;
import cz.vutbr.web.css.NodeData.LetterSpacing;
import cz.vutbr.web.css.NodeData.LineHeight;
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
import cz.vutbr.web.csskit.TermListImpl;

/**
 * Contains methods to transform declaration into values applicable to
 * NodeDataImpl. Contains map of CSS properties as supported in CSS 2.1 and
 * their default values. Implements singleton pattern.
 * 
 * @author kapy
 * 
 */
public class DeclarationTransformer {

	private static Logger log = Logger.getLogger(DeclarationTransformer.class);

	public static final int TOTAL_SUPPORTED_DECLARATIONS = 117;

	private static final CSSProperty INHERITABLE_PROPERTY = new CSSProperty() {
		public boolean inherited() {
			return true;
		}
	};

	private static final CSSProperty NOT_INHERITABLE_PROPERTY = new CSSProperty() {
		public boolean inherited() {
			return false;
		}
	};

	private static final CSSProperty MULTIVALUE_PROPERTY = new CSSProperty() {
		public boolean inherited() {
			return false;
		}
	};

	/**
	 * Contains names of supported elements and default values according to <a
	 * href="http://www.culturedcode.com/css/reference.html">
	 * http://www.culturedcode.com/css/reference.html</a>
	 */
	private Map<String, CSSProperty> supportedCSS;

	/**
	 * Cache of parsing methods
	 */
	private Map<String, Method> methods;

	/**
	 * Singleton instance
	 */
	private static final DeclarationTransformer instance;

	static {
		instance = new DeclarationTransformer();
	}

	/**
	 * Encapsulates functionality for parsing border side CSS style.
	 * 
	 * TODO: this is not thread safe and may create unexpected result when
	 * threads which are using this class as singleton modify variant property
	 * names or term at the same moment
	 */
	private final Variator borderSide = new Variator(3) {

		@Override
		protected boolean variant(int v, int i,
				Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

			switch (v) {
			case 0:
				// process color
				return genericTermIdent(BorderColor.class, terms[i],
						variantPropertyNames[v], properties)
						|| genericTermColor(terms[i], variantPropertyNames[v],
								BorderColor.color, properties, values);
			case 1:
				// process style
				return genericTermIdent(BorderStyle.class, terms[i],
						variantPropertyNames[v], properties);
			case 2:
				// process width
				return genericTermIdent(BorderWidth.class, terms[i],
						variantPropertyNames[v], properties)
						|| genericTerm(TermLength.class, terms[i],
								variantPropertyNames[v], BorderWidth.length,
								true, properties, values);
			default:
				return false;
			}
		}

		@Override
		protected boolean inheritance(Map<String, CSSProperty> properties,
				Map<String, Term<?>> values) {
			properties.put(variantPropertyNames[0], BorderColor.INHERIT);
			properties.put(variantPropertyNames[1], BorderStyle.INHERIT);
			properties.put(variantPropertyNames[2], BorderWidth.INHERIT);
			return true;
		}
	};

	/**
	 * Returns instance
	 * 
	 * @return Singleton instance
	 */
	public static final DeclarationTransformer getInstance() {
		return instance;
	}

	/**
	 * Converts string divided by dash ('-') characters into camelCase such as
	 * convenient for Java method names
	 * 
	 * @param string
	 *            String to convert
	 * @return CamelCase version of string
	 */
	public static final String camelCase(String string) {

		StringBuilder sb = new StringBuilder();

		boolean upperFlag = false;

		for (int i = 0; i < string.length(); i++) {
			char ch = string.charAt(i);
			if (ch == '-')
				upperFlag = true;
			else if (upperFlag && Character.isLetter(ch)) {
				sb.append(Character.toUpperCase(ch));
				upperFlag = false;
			} else if (!upperFlag && Character.isLetter(ch))
				sb.append(ch);
			else if (ch == '_') // vendor extension
				sb.append(ch);
		}
		return sb.toString();
	}

	/**
	 * Test function. Returns random supported CSS property
	 * 
	 * @return Default value of randomly chosen supported CSS property
	 */
	public CSSProperty randomValue() {

		Random generator = new Random();

		int i = generator.nextInt(supportedCSS.size());
		for (CSSProperty prop : supportedCSS.values()) {
			if (i == 0)
				return prop;
			i--;
		}
		// should never reach this statement
		return null;
	}

	/**
	 * Core function. Parses CSS declaration into structure applicable to
	 * DataNodeImpl
	 * 
	 * @param d
	 *            Declaration
	 * @param properties
	 *            Holder of parsed declaration's properties
	 * @param values
	 *            Holder of parsed declaration's value
	 * @return <code>true</code> in case of success, <code>false</code>
	 *         otherwise
	 */
	public boolean parseDeclaration(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		String propertyName = d.getProperty().toLowerCase();

		CSSProperty defaultValue = supportedCSS.get(propertyName);
		// no such declaration is supported
		if (defaultValue == null)
			return false;

		try {
			Method m = methods.get(propertyName);
			if (m != null)
				return (Boolean) m.invoke(this, d, properties, values);
		} catch (IllegalArgumentException e) {
			log.warn("Illegal argument: " + e);
		} catch (IllegalAccessException e) {
			log.warn("Illegal access: " + e);
		} catch (InvocationTargetException e) {
			log.warn("Invocation target: " + e + e.getCause());
		}

		return false;
	}

	/**
	 * Sole constructor
	 */
	private DeclarationTransformer() {
		this.supportedCSS = supportedCSS();
		this.methods = parsingMethods();
	}

	/**
	 * Constructs map of supported properties as keys and their default values
	 * 
	 * @return Constructed map
	 */
	private Map<String, CSSProperty> supportedCSS() {

		Map<String, CSSProperty> map = new HashMap<String, CSSProperty>(
				TOTAL_SUPPORTED_DECLARATIONS, 1.0f);

		// text type
		map.put("color", INHERITABLE_PROPERTY);
		map.put("font", MULTIVALUE_PROPERTY);
		map.put("font-family", INHERITABLE_PROPERTY);
		map.put("font-size", FontSize.MEDIUM);
		map.put("font-style", FontStyle.NORMAL);
		map.put("font-variant", FontVariant.NORMAL);
		map.put("font-weight", FontWeight.NORMAL);
		map.put("text-decoration", TextDecoration.NONE);
		map.put("text-transform", TextTransform.NONE);

		// text spacing
		map.put("white-space", WhiteSpace.NORMAL);
		map.put("text-align", INHERITABLE_PROPERTY);
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
		map.put("border-spacing", BorderSpacing.hor_ver_list); // 0 0
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

		if (log.isInfoEnabled()) {
			log.info("Total supported properties: " + map.size());
		}

		return map;
	}

	private Map<String, Method> parsingMethods() {

		Map<String, Method> map = new HashMap<String, Method>(
				TOTAL_SUPPORTED_DECLARATIONS, 1.0f);

		for (String key : supportedCSS.keySet()) {
			try {
				Method m = DeclarationTransformer.class
						.getDeclaredMethod(DeclarationTransformer
								.camelCase("process-" + key), new Class[] {
								Declaration.class, Map.class, Map.class });
				map.put(key, m);
			} catch (Exception e) {
				log.warn("Unable to find method for property: " + key);
			}
		}
		if (log.isInfoEnabled()) {
			log.info("Total methods found: " + map.size());
		}
		return map;
	}

	// generic methods

	/**
	 * Converts TermIdent into value of enum of given class and stores it into
	 * properties map under key property
	 * 
	 * @param <T>
	 * @param enumType
	 * @param term
	 * @param properties
	 * @param property
	 * @return <code>true</code> in case of success, <code>false</code>
	 *         otherwise
	 */
	private <T extends Enum<T> & CSSProperty> boolean genericProperty(
			Class<T> enumType, TermIdent term,
			Map<String, CSSProperty> properties, String propertyName) {

		// try to find enum with given value and if so
		// insert it inside
		try {
			final String name = term.getValue().replace("-", "_").toUpperCase();
			properties.put(propertyName, Enum.valueOf(enumType, name));
			return true;
		} catch (IllegalArgumentException e) {
			// no such enum value
		} catch (NullPointerException e) {
			log.warn("TermIdent contained empty value!");
		}

		return false;

	}

	/**
	 * Converts TermIdent into value of enum for given class, check where there
	 * is only none term in Declaration
	 * 
	 * @param <T>
	 * @param enumType
	 * @param d
	 * @param properties
	 * @return
	 */
	private <T extends Enum<T> & CSSProperty> boolean genericTermIdent(
			Class<T> enumType, Term<?> term, String propertyName,
			Map<String, CSSProperty> properties) {

		if (term instanceof TermIdent) {
			return genericProperty(enumType, (TermIdent) term, properties,
					propertyName);
		}
		return false;

	}

	private <T extends Enum<T> & CSSProperty> boolean genericTermColor(
			Term<?> term, String propertyName, T colorIdentification,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (term instanceof TermColor) {
			properties.put(propertyName, colorIdentification);
			values.put(propertyName, term);
			return true;
		}

		return false;

	}

	/**
	 * Check whether given declaration contains one term of given type. It is
	 * able to check even whether is above zero for numeric values
	 * 
	 * @param <T>
	 *            Class of enum to be used for result
	 * @param termType
	 *            Type of term
	 * @param d
	 *            Declaration
	 * @param typeIdentification
	 *            How this type of term is described in enum T
	 * @param sanify
	 *            Check if value is positive
	 * @param properties
	 *            Where to store property type
	 * @param values
	 *            Where to store property value
	 * @return <code>true</code> if succeeded in recognition,
	 *         <code>false</code> otherwise
	 */
	private <T extends Enum<T> & CSSProperty> boolean genericTerm(
			Class<? extends Term<?>> termType, Term<?> term,
			String propertyName, T typeIdentification, boolean sanify,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		// check type
		if (termType.isInstance(term)) {
			// sanity check
			if (sanify) {
				// check for integer
				if (term.getValue() instanceof Integer) {
					final Integer zero = new Integer(0);
					if (zero.compareTo((Integer) term.getValue()) > 0) {
						// return false is also possibility
						// but we will change to zero
						((TermInteger) term).setValue(zero);
					}
				}
				// check for float
				else if (term.getValue() instanceof Float) {
					final Float zero = new Float(0.0f);
					if (zero.compareTo((Float) term.getValue()) > 0) {
						// return false is also possibility
						// but we will change to zero
						((TermNumber) term).setValue(zero);
					}
				}
			}
			// passed both type check and (optional) sanity check,
			// store
			properties.put(propertyName, typeIdentification);
			values.put(propertyName, term);
			return true;

		}
		return false;

	}

	/**
	 * Processes declaration which is supposed to contain one identification
	 * term
	 * 
	 * @param <T>
	 *            Type of enum
	 * @param enumType
	 *            Class of enum to be stored
	 * @param d
	 *            Declaration to be parsed
	 * @param properties
	 *            Properties map where to store enum
	 * @return <code>true</code> in case of success, <code>false</code>
	 *         elsewhere
	 */
	private <T extends Enum<T> & CSSProperty> boolean genericOneIdent(
			Class<T> enumType, Declaration d,
			Map<String, CSSProperty> properties) {

		if (d.getTerms().size() != 1)
			return false;

		return genericTermIdent(enumType, d.getTerms().get(0), d.getProperty(),
				properties);
	}

	/**
	 * Processes declaration which is supposed to contain one identification
	 * term or one TermColor
	 * 
	 * @param <T>
	 *            Type of enum
	 * @param enumType
	 *            Class of enum to be stored
	 * @param colorIdentification
	 *            Instance of enum stored into properties to indicate that
	 *            additional value of type TermColor is stored in values
	 * @param d
	 *            Declaration to be parsed
	 * @param properties
	 *            Properties map where to store enum
	 * @param values
	 * @return <code>true</code> in case of success, <code>false</code>
	 *         elsewhere
	 */
	private <T extends Enum<T> & CSSProperty> boolean genericOneIdentOrColor(
			Class<T> enumType, T colorIdentification, Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (d.getTerms().size() != 1)
			return false;

		return genericTermIdent(enumType, d.getTerms().get(0), d.getProperty(),
				properties)
				|| genericTermColor(d.getTerms().get(0), d.getProperty(),
						colorIdentification, properties, values);
	}

	private <T extends Enum<T> & CSSProperty> boolean genericOneIdentOrInteger(
			Class<T> enumType, T integerIdentification, boolean sanify,
			Declaration d, Map<String, CSSProperty> properties,
			Map<String, Term<?>> values) {

		if (d.getTerms().size() != 1)
			return false;

		return genericTermIdent(enumType, d.getTerms().get(0), d.getProperty(),
				properties)
				|| genericTerm(TermInteger.class, d.getTerms().get(0), d
						.getProperty(), integerIdentification, sanify,
						properties, values);
	}

	private <T extends Enum<T> & CSSProperty> boolean genericOneIdentOrLength(
			Class<T> enumType, T lengthIdentification, boolean sanify,
			Declaration d, Map<String, CSSProperty> properties,
			Map<String, Term<?>> values) {

		if (d.getTerms().size() != 1)
			return false;

		return genericTermIdent(enumType, d.getTerms().get(0), d.getProperty(),
				properties)
				|| genericTerm(TermLength.class, d.getTerms().get(0), d
						.getProperty(), lengthIdentification, sanify,
						properties, values);
	}

	private <T extends Enum<T> & CSSProperty> boolean genericOneIdentOrLengthOrPercent(
			Class<T> enumType, T lengthIdentification, T percentIdentification,
			boolean sanify, Declaration d, Map<String, CSSProperty> properties,
			Map<String, Term<?>> values) {

		if (d.getTerms().size() != 1)
			return false;

		return genericTermIdent(enumType, d.getTerms().get(0), d.getProperty(),
				properties)
				|| genericTerm(TermLength.class, d.getTerms().get(0), d
						.getProperty(), lengthIdentification, sanify,
						properties, values)
				|| genericTerm(TermPercent.class, d.getTerms().get(0), d
						.getProperty(), percentIdentification, sanify,
						properties, values);
	}

	// repeater object

	/**
	 * Repeats one operation on different objects. Helper object to avoid
	 * duplication of code To use, implement operation() method
	 */
	private abstract class Repeater {

		protected int times;
		@SuppressWarnings("unused")
		protected Term<?>[] terms;

		@SuppressWarnings("unused")
		protected String[] propertyNames;

		public Repeater(int times) {
			this.times = times;
		}

		protected abstract boolean operation(int iteration,
				Map<String, CSSProperty> properties, Map<String, Term<?>> values);

		public boolean repeat(Map<String, CSSProperty> properties,
				Map<String, Term<?>> values) {
			for (int i = 0; i < times; i++) {
				if (!operation(i, properties, values))
					return false;
			}
			return true;
		}

		public boolean repeatOverFourTermDeclaration(Declaration d,
				Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

			switch (d.getTerms().size()) {
			case 1:
				// one term for all value
				Term<?> term = d.getTerms().get(0);
				assignTerms(term, term, term, term);
				return repeat(properties, values);
			case 2:
				// one term for bottom-top and left-right
				Term<?> term1 = d.getTerms().get(0);
				Term<?> term2 = d.getTerms().get(1);
				assignTerms(term1, term2, term1, term2);
				return repeat(properties, values);
			case 3:
				// terms for bottom, top, left-right
				Term<?> term31 = d.getTerms().get(0);
				Term<?> term32 = d.getTerms().get(1);
				Term<?> term33 = d.getTerms().get(2);
				assignTerms(term31, term32, term33, term32);
				return repeat(properties, values);
			case 4:
				// four individual terms
				Term<?> term41 = d.getTerms().get(0);
				Term<?> term42 = d.getTerms().get(1);
				Term<?> term43 = d.getTerms().get(2);
				Term<?> term44 = d.getTerms().get(3);
				assignTerms(term41, term42, term43, term44);
				return repeat(properties, values);
			default:
				log
						.warn("Repetear casted over declaration not within 1-4 terms range"
								+ d);
				return false;
			}
		}

		public void assignPropertyNames(String... propertyNames)
				throws IllegalArgumentException {
			if (propertyNames.length != times)
				throw new IllegalArgumentException(
						"Invalid length of propertyNames in Repeater.");
			this.propertyNames = propertyNames;
		}

		public void assignTerms(Term<?>... terms)
				throws IllegalArgumentException {
			if (terms.length != times)
				throw new IllegalArgumentException(
						"Invalid length of terms in Repeater.");
			this.terms = terms;
		}
	}

	private abstract class Variator {

		protected int variants;

		protected boolean[] variantPassed;
		protected String[] variantPropertyNames;
		protected Term<?>[] terms;

		public Variator(int variants) {
			this.variants = variants;
			this.variantPassed = new boolean[variants];
			for (int i = 0; i < variants; i++)
				variantPassed[i] = false;
		}

		protected abstract boolean variant(int variant, int iteration,
				Map<String, CSSProperty> properties, Map<String, Term<?>> values);

		protected abstract boolean inheritance(
				Map<String, CSSProperty> properties, Map<String, Term<?>> values);

		public boolean vary(Map<String, CSSProperty> properties,
				Map<String, Term<?>> values) {

			// null passed
			for (int i = 0; i < variants; i++)
				variantPassed[i] = false;

			if (log.isDebugEnabled()) {
				log.debug("Varying with: " + variants + "variants and "
						+ terms.length + " terms");
			}

			// inheritance
			if (terms.length == 1
					&& terms[0] instanceof TermIdent
					&& "inherit".equalsIgnoreCase(((TermIdent) terms[0])
							.getValue())) {
				return inheritance(properties, values);
			}

			// for all terms
			for (int i = 0; i < terms.length; i++) {

				log.debug("checking " + terms[i]);
				// check inheritance once again
				// this should never happen in case of valid declaration
				/*
				 * if (terms[i] instanceof TermIdent &&
				 * "inherit".equalsIgnoreCase(((TermIdent) terms[i])
				 * .getValue())) return false;
				 */

				boolean passed = false;

				// check all variants
				for (int v = 0; v < variants; v++) {
					// check and if variant was already found
					// signalize error by discarding complete declaration
					passed = variant(v, i, properties, values);
					if (passed) {
						if (variantPassed[v])
							return false;
						variantPassed[v] = true;
						if (log.isDebugEnabled()) {
							log.debug("Passed variant " + v + " "
									+ variantPropertyNames[v] + " for term:("
									+ i + ")" + terms[i]);
						}
						// we have found, skip evaluation
						break;
					}
				}
				// failed on term
				if (!passed) {
					if (log.isDebugEnabled()) {
						log.debug("Failed on term " + terms[i]);
						return false;
					}
				}

			}
			return true;
		}

		public void assignVariantPropertyNames(String... variantPropertyNames) {
			this.variantPropertyNames = variantPropertyNames;
		}

		public void assignTerms(Term<?>... terms)
				throws IllegalArgumentException {
			this.terms = terms;
		}

		public void assignTermsFromDeclaration(Declaration d) {
			this.terms = new Term<?>[d.getTerms().size()];
			for (int i = 0; i < terms.length; i++)
				this.terms[i] = d.getTerms().get(i);
		}
	}

	// =============================================================
	// processing methods

	@SuppressWarnings("unused")
	private boolean processColor(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrColor(Color.class, Color.color, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processBackgroundAttachement(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(BackgroundAttachment.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processBackgroundColor(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrColor(BackgroundColor.class,
				BackgroundColor.color, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBackgroundImage(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (d.getTerms().size() != 1)
			return false;

		return genericOneIdent(BackgroundImage.class, d, properties)
				|| genericTerm(TermURI.class, d.getTerms().get(0), d
						.getProperty(), BackgroundImage.uri, false, properties,
						values);
	}

	@SuppressWarnings("unused")
	private boolean processBackgroundRepeat(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(BackgroundRepeat.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processBorderCollapse(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(BorderCollapse.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processBorderTopColor(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrColor(BorderColor.class, BorderColor.color, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderRightColor(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrColor(BorderColor.class, BorderColor.color, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderBottomColor(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrColor(BorderColor.class, BorderColor.color, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderLeftColor(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrColor(BorderColor.class, BorderColor.color, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderTopStyle(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(BorderStyle.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processBorderRightStyle(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(BorderStyle.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processBorderBottomStyle(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(BorderStyle.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processBorderLeftStyle(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(BorderStyle.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processBorderSpacing(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (d.getTerms().size() == 1) {
			Term<?> term = d.getTerms().get(0);
			String propertyName = d.getProperty();
			// is it identifier or length ?
			if (genericTermIdent(BorderSpacing.class, term, propertyName,
					properties)
					|| genericTerm(TermLength.class, term, propertyName,
							BorderSpacing.hor_ver_list, true, properties,
							values)) {
				// one term with length was inserted, double it
				if (properties.get(propertyName) == BorderSpacing.hor_ver_list) {
					TermList terms = new TermListImpl(2);
					terms.add(term);
					terms.add(term);
					values.put(propertyName, terms);
				}
				return true;
			}
		}
		// two numerical values
		else if (d.getTerms().size() == 2) {
			Term<?> term1 = d.getTerms().get(0);
			Term<?> term2 = d.getTerms().get(1);
			String propertyName = d.getProperty();
			// two lengths ?
			if (genericTerm(TermLength.class, term1, propertyName,
					BorderSpacing.hor_ver_list, true, properties, values)
					&& genericTerm(TermLength.class, term2, propertyName,
							BorderSpacing.hor_ver_list, true, properties,
							values)) {
				TermList terms = new TermListImpl(2);
				terms.add(term1);
				terms.add(term2);
				values.put(propertyName, terms);
				return true;
			}
			return false;
		}
		return false;
	}

	@SuppressWarnings("unused")
	private boolean processBorderColor(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		// encapsulated functionality
		final Repeater borderColor = new Repeater(4) {

			protected String[] propertyNames = new String[] {
					"border-top-color", "border-right-color",
					"border-bottom-color", "border-left-color" };

			@Override
			protected boolean operation(int i,
					Map<String, CSSProperty> properties,
					Map<String, Term<?>> values) {

				return genericTermIdent(BorderColor.class, terms[i],
						propertyNames[i], properties)
						|| genericTerm(TermColor.class, terms[i],
								propertyNames[i], BorderColor.color, false,
								properties, values);
			}
		};

		return borderColor.repeatOverFourTermDeclaration(d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderStyle(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		// encapsulated functionality
		final Repeater borderStyle = new Repeater(4) {

			protected String[] propertyNames = new String[] {
					"border-top-style", "border-right-style",
					"border-bottom-style", "border-left-style" };

			@Override
			protected boolean operation(int i,
					Map<String, CSSProperty> properties,
					Map<String, Term<?>> values) {

				return genericTermIdent(BorderStyle.class, terms[i],
						propertyNames[i], properties);
			}
		};

		return borderStyle.repeatOverFourTermDeclaration(d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderTopWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLength(BorderWidth.class, BorderWidth.length,
				true, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderRightWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLength(BorderWidth.class, BorderWidth.length,
				true, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderBottomWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLength(BorderWidth.class, BorderWidth.length,
				true, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderLeftWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLength(BorderWidth.class, BorderWidth.length,
				true, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		// encapsulated functionality
		final Repeater borderWidth = new Repeater(4) {

			protected String[] propertyNames = new String[] {
					"border-top-width", "border-right-width",
					"border-bottom-width", "border-left-width" };

			@Override
			protected boolean operation(int i,
					Map<String, CSSProperty> properties,
					Map<String, Term<?>> values) {

				return genericTermIdent(BorderWidth.class, terms[i],
						propertyNames[i], properties)
						|| genericTerm(TermLength.class, terms[i],
								propertyNames[i], BorderWidth.length, true,
								properties, values);
			}
		};

		return borderWidth.repeatOverFourTermDeclaration(d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderTop(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		borderSide.assignVariantPropertyNames("border-top-color",
				"border-top-style", "border-top-width");
		borderSide.assignTermsFromDeclaration(d);

		return borderSide.vary(properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderRight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		borderSide.assignVariantPropertyNames("border-right-color",
				"border-right-style", "border-right-width");
		borderSide.assignTermsFromDeclaration(d);

		return borderSide.vary(properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderBottom(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		borderSide.assignVariantPropertyNames("border-bottom-color",
				"border-bottom-style", "border-bottom-width");
		borderSide.assignTermsFromDeclaration(d);

		return borderSide.vary(properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderLeft(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		borderSide.assignVariantPropertyNames("border-left-color",
				"border-left-style", "border-left-width");
		borderSide.assignTermsFromDeclaration(d);

		return borderSide.vary(properties, values);
	}

	/*
	 * 
	 * private Boolean processBorder(Declaration d) { NodeData trans =
	 * beginTransaction(); //Nastavení na výchozí hodnoty borderColorTopType =
	 * null; borderColorRightType = null; borderColorBottomType = null;
	 * borderColorLeftType = null; borderColorTopValue = null;
	 * borderColorRightValue = null; borderColorBottomValue = null;
	 * borderColorLeftValue = null; borderStyleTopType = null;
	 * borderStyleRightType = null; borderStyleBottomType = null;
	 * borderStyleLeftType = null; borderWidthTopType = null;
	 * borderWidthRightType = null; borderWidthBottomType = null;
	 * borderWidthLeftType = null; borderWidthTopValue = null;
	 * borderWidthRightValue = null; borderWidthBottomValue = null;
	 * borderWidthLeftValue = null;
	 * 
	 * //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné
	 * deklaraci //objevila například 2x barva. K určení slouží následující
	 * proměnné boolean processedColor = false; boolean processedStyle = false;
	 * boolean processedWidth = false;
	 * 
	 * for(Term t : d.getTerms()) { //Pokud je první (a jediný) identifikátor
	 * inherit, pak se nastaví všecm hodnotám inherit //Pokud by se inherit
	 * objevilo až například jako třetí term, dojde k ignorování celé deklarace
	 * if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
	 * if(d.getTerms().size() == 1) { borderColorTopType =
	 * EnumColorTransparent.inherit; borderColorRightType =
	 * EnumColorTransparent.inherit; borderColorBottomType =
	 * EnumColorTransparent.inherit; borderColorLeftType =
	 * EnumColorTransparent.inherit; borderColorTopValue = null;
	 * borderColorRightValue = null; borderColorBottomValue = null;
	 * borderColorLeftValue = null; borderStyleTopType =
	 * EnumBorderStyle.inherit; borderStyleRightType = EnumBorderStyle.inherit;
	 * borderStyleBottomType = EnumBorderStyle.inherit; borderStyleLeftType =
	 * EnumBorderStyle.inherit; borderWidthTopType = EnumBorderWidth.inherit;
	 * borderWidthRightType = EnumBorderWidth.inherit; borderWidthBottomType =
	 * EnumBorderWidth.inherit; borderWidthLeftType = EnumBorderWidth.inherit;
	 * borderWidthTopValue = null; borderWidthRightValue = null;
	 * borderWidthBottomValue = null; borderWidthLeftValue = null; return true; }
	 * else { rollbackTransaction(trans); return false; } }
	 * 
	 * //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten
	 * aktuální) //a v jednotlivých blocích se pokouším tuto deklaraci
	 * zpracovat. Declaration tmpDeclaration = new DataDeclaration("border");
	 * tmpDeclaration.getTerms().add(t);
	 * 
	 * //Vyzkouším, jestli se jedná o barvu
	 * tmpDeclaration.setProperty("border-color");
	 * if(processBorderColor(tmpDeclaration)) { //Jedná se o barvu. Zjistím,
	 * jestli barva už nebyla jednou zadána if(processedColor) { //Barva už byla
	 * jednou zadáno, deklarace je chybná, rollback a konec
	 * rollbackTransaction(trans); return false; } else { //Barva ještě nebyla
	 * zadána, pokračujeme dalším termem processedColor = true; continue; } }
	 * tmpDeclaration.setProperty("border-style");
	 * if(processBorderStyle(tmpDeclaration)) { if(processedStyle) {
	 * rollbackTransaction(trans); return false; } else { processedStyle = true;
	 * continue; } } tmpDeclaration.setProperty("border-width");
	 * if(processBorderWidth(tmpDeclaration)) { if(processedWidth) {
	 * rollbackTransaction(trans); return false; } else { processedWidth = true;
	 * continue; } }
	 * 
	 * //Pokud program dojde sem, znamená to že term není ani color, style nebo
	 * width - ignorace celé deklarace rollbackTransaction(trans); return false; }
	 * return true; }
	 * 
	 * private Boolean processFontFamily(Declaration d) { NodeData trans =
	 * beginTransaction(); ArrayList<String>input = new ArrayList<String>();
	 * if(d.getTerms().size() == 1) { if(d.getTerms().get(0) instanceof
	 * TermIdent) { String ident = ((TermIdent)d.getTerms().get(0)).getValue();
	 * if(ident.equalsIgnoreCase("inherit")) { fontFamilyType =
	 * EnumFontFamily.inherit; fontFamilyValues.clear(); return true; } } }
	 * 
	 * for(Term t : d.getTerms()) { if(t instanceof TermIdent) { String ident =
	 * ((TermIdent)t).getValue(); fontFamilyType = EnumFontFamily.font;
	 * if(t.getOperator() == Term.Operator.SPACE && !input.isEmpty()) { String
	 * tmp = input.get(input.size()-1); tmp = tmp + " " + ident;
	 * input.remove(input.size()-1); input.add(tmp); } else { input.add(ident); } }
	 * else if(t instanceof TermString) { String ident =
	 * ((TermString)t).getValue(); fontFamilyType = EnumFontFamily.font;
	 * input.add(ident); } else { rollbackTransaction(trans); return false; } }
	 * fontFamilyValues.clear(); fontFamilyValues.addAll(input); return true; }
	 */

	@SuppressWarnings("unused")
	private boolean processFontSize(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(FontSize.class,
				FontSize.length, FontSize.percentage, true, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processFontStyle(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(FontStyle.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processFontVariant(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(FontVariant.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processFontWeight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		// test against numeric values
		final Integer[] test = new Integer[] { 100, 200, 300, 400, 500, 600,
				700, 800, 900 };

		if (d.getTerms().size() != 1)
			return false;

		final Term<?> term = d.getTerms().get(0);

		if (term instanceof TermIdent) {
			return genericProperty(FontWeight.class, (TermIdent) term,
					properties, d.getProperty());
		} else if (term instanceof TermInteger) {

			Integer value = ((TermInteger) term).getValue();
			for (int i = 0; i < test.length; i++) {
				int result = value.compareTo(test[i]);
				// not found if value is smaller than currently compared
				if (result < 0)
					break;

				// match
				// construct according enum name
				if (result == 0) {
					try {
						properties.put(d.getProperty(), FontWeight
								.valueOf("numeric_" + value.intValue()));
						return true;
					} catch (IllegalArgumentException e) {
						log.warn("Not found numeric values for FontWeight: "
								+ "numeric_ " + value.intValue());
						return false;
					}
				}
			}
		}
		return false;

	}

	/*
	 * private Boolean processFont(Declaration d) { NodeData trans =
	 * beginTransaction(); //Hodnoty jsou děděné (inherited) - Nastavení na
	 * výchozí hodnoty fontStyleType = EnumFontStyle.normal; fontFamilyType =
	 * EnumFontFamily.font; fontFamilyValues.clear(); fontSizeType =
	 * EnumFontSize.medium; fontSizePercentValue = null; fontSizeNumberValue =
	 * null; fontVariantType = EnumFontVariant.normal; fontWeightType =
	 * EnumFontWeight.normal; lineHeightType = EnumLineHeight.normal;
	 * lineHeightNumberValue = null; lineHeightPercentValue = null;
	 * 
	 * //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné
	 * deklaraci //objevila například 2x barva. K určení slouží následující
	 * proměnné boolean processedStyle = false; boolean processedVariant =
	 * false; boolean processedWeight = false; boolean processedFontSize =
	 * false; boolean processedLineHeight = false; int count = 0;
	 * 
	 * //Sem se budou kládat všechny hodnoty na konci (pravděpodobně se bude
	 * jednat o hodnoty font-family) Declaration fontFamilyDeclaration = new
	 * DataDeclaration("font-family");
	 * 
	 * for(Term t : d.getTerms()) { //Pokud je první (a jediný) identifikátor
	 * inherit, pak se nastaví všecm hodnotám inherit //Pokud by se inherit
	 * objevilo až například jako třetí term, dojde k ignorování celé deklarace
	 * if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
	 * if(d.getTerms().size() == 1) { fontStyleType = EnumFontStyle.inherit;
	 * fontFamilyType = EnumFontFamily.inherit; fontFamilyValues.clear();
	 * fontSizeType = EnumFontSize.inherit; fontSizePercentValue = null;
	 * fontSizeNumberValue = null; fontVariantType = EnumFontVariant.inherit;
	 * fontWeightType = EnumFontWeight.inherit; lineHeightType =
	 * EnumLineHeight.inherit; lineHeightNumberValue = null;
	 * lineHeightPercentValue = null; return true; } else {
	 * rollbackTransaction(trans); return false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("caption")) {
	 * if(d.getTerms().size() == 1) { //neimplementováno return true; } else {
	 * rollbackTransaction(trans); return false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("icon")) {
	 * if(d.getTerms().size() == 1) { //neimplementováno return true; } else {
	 * rollbackTransaction(trans); return false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("menu")) {
	 * if(d.getTerms().size() == 1) { //neimplementováno return true; } else {
	 * rollbackTransaction(trans); return false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("message-box")) {
	 * if(d.getTerms().size() == 1) { //neimplementováno return true; } else {
	 * rollbackTransaction(trans); return false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("small-caption")) {
	 * if(d.getTerms().size() == 1) { //neimplementováno return true; } else {
	 * rollbackTransaction(trans); return false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("status-bar")) {
	 * if(d.getTerms().size() == 1) { //neimplementováno return true; } else {
	 * rollbackTransaction(trans); return false; } }
	 * 
	 * //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten
	 * aktuální) //a v jednotlivých blocích se pokouším tuto deklaraci
	 * zpracovat. Declaration tmpDeclaration = new DataDeclaration("font");
	 * tmpDeclaration.getTerms().add(t);
	 * 
	 * if(!processedFontSize && count < 3) { if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("normal")) { //U normal není
	 * jasné o co se jedná. Ale je to výchozí hodnota, lze jí ignorovat count++;
	 * continue; }
	 * 
	 * //Vyzkouším, jestli se jedná o barvu
	 * tmpDeclaration.setProperty("font-style");
	 * if(processFontStyle(tmpDeclaration)) { //Jedná se o barvu. Zjistím,
	 * jestli barva už nebyla jednou zadána if(processedStyle) { //Barva už byla
	 * jednou zadáno, deklarace je chybná, rollback a konec
	 * rollbackTransaction(trans); return false; } else { //Barva ještě nebyla
	 * zadána, pokračujeme dalším termem processedStyle = true; count++;
	 * continue; } } tmpDeclaration.setProperty("font-variant");
	 * if(processFontVariant(tmpDeclaration)) { if(processedVariant) {
	 * rollbackTransaction(trans); return false; } else { processedVariant =
	 * true; count++; continue; } } tmpDeclaration.setProperty("font-weight");
	 * if(processFontWeight(tmpDeclaration)) { if(processedWeight) {
	 * rollbackTransaction(trans); return false; } else { processedWeight =
	 * true; count++; continue; } } }
	 * 
	 * //V tomto místě musí být deklarováno bezpodmínečně font-size
	 * if(!processedFontSize) { tmpDeclaration.setProperty("font-size");
	 * if(processFontSize(tmpDeclaration)) { processedFontSize = true; continue; }
	 * else { rollbackTransaction(trans); return false; } }
	 * 
	 * if(!processedLineHeight && t.getOperator() == Term.Operator.SLASH) {
	 * tmpDeclaration.setProperty("line-height");
	 * if(processLineHeight(tmpDeclaration)) { processedLineHeight = true;
	 * continue; } else { rollbackTransaction(trans); return false; } }
	 * 
	 * //Vše co neprojde předchozím fontFamilyDeclaration.getTerms().add(t); }
	 * 
	 * //Font family musí být zadáno
	 * if(fontFamilyDeclaration.getTerms().isEmpty()) {
	 * rollbackTransaction(trans); return false; }
	 * 
	 * if(processFontFamily(fontFamilyDeclaration)) { return true; } else {
	 * rollbackTransaction(trans); return false; } }
	 */

	@SuppressWarnings("unused")
	private boolean processLineHeight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (d.getTerms().size() != 1)
			return false;

		return genericOneIdent(LineHeight.class, d, properties)
				|| genericTerm(TermNumber.class, d.getTerms().get(0), d
						.getProperty(), LineHeight.number, true, properties,
						values)
				|| genericTerm(TermPercent.class, d.getTerms().get(0), d
						.getProperty(), LineHeight.percentage, true,
						properties, values)
				|| genericTerm(TermLength.class, d.getTerms().get(0), d
						.getProperty(), LineHeight.length, true, properties,
						values);
	}

	@SuppressWarnings("unused")
	private boolean processTop(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Top.class, Top.lenght,
				Top.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processRight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Right.class, Right.lenght,
				Right.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBottom(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Bottom.class, Bottom.lenght,
				Bottom.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processLeft(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Left.class, Left.lenght,
				Left.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Width.class, Width.lenght,
				Width.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processHeight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Height.class, Height.lenght,
				Height.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processCaptionSide(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(CaptionSide.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processClear(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(Clear.class, d, properties);
	}

	/*
	 * private Boolean processClip(Declaration d) { if(d.getTerms().size() == 1) {
	 * if(d.getTerms().get(0) instanceof TermIdent) { String ident =
	 * ((TermIdent)d.getTerms().get(0)).getValue();
	 * if(ident.equalsIgnoreCase("auto")) { clipTopType = EnumClip.auto;
	 * clipRightType = EnumClip.auto; clipBottomType = EnumClip.auto;
	 * clipLeftType = EnumClip.auto; clipTopNumberValue = null;
	 * clipRightNumberValue = null; clipBottomNumberValue = null;
	 * clipLeftNumberValue = null; return true; }
	 * if(ident.equalsIgnoreCase("inherit")) { clipTopType = EnumClip.inherit;
	 * clipRightType = EnumClip.inherit; clipBottomType = EnumClip.inherit;
	 * clipLeftType = EnumClip.inherit; clipTopNumberValue = null;
	 * clipRightNumberValue = null; clipBottomNumberValue = null;
	 * clipLeftNumberValue = null; return true; } } if(d.getTerms().get(0)
	 * instanceof TermFunction) { TermFunction f =
	 * (TermFunction)d.getTerms().get(0);
	 * if(f.getFunctionName().equalsIgnoreCase("rect") &&
	 * f.getTermsList().size() == 4) { NodeData trans = beginTransaction(); int
	 * index = 0; for(Term t : f.getTermsList()) { if(t instanceof TermIdent) {
	 * String ident = ((TermIdent)t).getValue();
	 * if(ident.equalsIgnoreCase("auto")) { switch (index) { case 0: clipTopType =
	 * EnumClip.auto; clipTopNumberValue = null; break; case 1: clipRightType =
	 * EnumClip.auto; clipRightNumberValue = null; break; case 2: clipBottomType =
	 * EnumClip.auto; clipBottomNumberValue = null; break; case 3: clipLeftType =
	 * EnumClip.auto; clipLeftNumberValue = null; break; } index++; continue; } }
	 * if(t instanceof TermNumber) { TermNumber num = (TermNumber)t;
	 * if(num.isLength()) { switch (index) { case 0: clipTopType =
	 * EnumClip.length; clipTopNumberValue = num; break; case 1: clipRightType =
	 * EnumClip.length; clipRightNumberValue = num; break; case 2:
	 * clipBottomType = EnumClip.length; clipBottomNumberValue = num; break;
	 * case 3: clipLeftType = EnumClip.length; clipLeftNumberValue = num; break; }
	 * index++; continue; } }
	 * 
	 * rollbackTransaction(trans); return false; } return true; } } } return
	 * false; }
	 * 
	 * private Boolean processCounterIncrement(Declaration d) { HashMap<String,
	 * Integer> out = new HashMap<String, Integer>(); String lastIdent = null;
	 * 
	 * for(Term t : d.getTerms()) { //Pokud je první (a jediný) identifikátor
	 * inherit, pak se nastaví všecm hodnotám inherit //Pokud by se inherit
	 * objevilo až například jako třetí term, dojde k ignorování celé deklarace
	 * if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
	 * if(d.getTerms().size() == 1) { counterIncrementType =
	 * EnumCounter.inherit; counterIncrementValues.clear(); return true; } else {
	 * return false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
	 * if(d.getTerms().size() == 1) { counterIncrementType = EnumCounter.none;
	 * counterIncrementValues.clear(); return true; } else { return false; } }
	 * 
	 * if((t instanceof TermIdent)) { out.put(((TermIdent)t).getValue(), new
	 * Integer(1)); lastIdent = ((TermIdent)t).getValue(); continue; } if((t
	 * instanceof TermNumber)) { TermNumber num = (TermNumber)t;
	 * if(num.isInteger() && lastIdent != null) { out.put(lastIdent,
	 * num.getValue().intValue()); lastIdent = null; continue; } } return false; }
	 * 
	 * if(!out.isEmpty()) { counterIncrementType = EnumCounter.table_values;
	 * counterIncrementValues.clear(); counterIncrementValues.putAll(out);
	 * return true; } else { return false; } }
	 * 
	 * private Boolean processCounterReset(Declaration d) { HashMap<String,
	 * Integer> out = new HashMap<String, Integer>(); String lastIdent = null;
	 * 
	 * for(Term t : d.getTerms()) { //Pokud je první (a jediný) identifikátor
	 * inherit, pak se nastaví všecm hodnotám inherit //Pokud by se inherit
	 * objevilo až například jako třetí term, dojde k ignorování celé deklarace
	 * if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
	 * if(d.getTerms().size() == 1) { counterResetType = EnumCounter.inherit;
	 * counterResetValues.clear(); return true; } else { return false; } } if((t
	 * instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
	 * if(d.getTerms().size() == 1) { counterResetType = EnumCounter.none;
	 * counterResetValues.clear(); return true; } else { return false; } }
	 * 
	 * if((t instanceof TermIdent)) { out.put(((TermIdent)t).getValue(), new
	 * Integer(1)); lastIdent = ((TermIdent)t).getValue(); continue; } if((t
	 * instanceof TermNumber)) { TermNumber num = (TermNumber)t;
	 * if(num.isInteger() && lastIdent != null) { out.put(lastIdent,
	 * num.getValue().intValue()); lastIdent = null; continue; } } return false; }
	 * 
	 * if(!out.isEmpty()) { counterResetType = EnumCounter.table_values;
	 * counterResetValues.clear(); counterResetValues.putAll(out); return true; }
	 * else { return false; } }
	 * 
	 * 
	 * 
	 * 
	 * private Boolean processCursor(Declaration d) { boolean processedGeneric =
	 * false; ArrayList<TermURI> out = new ArrayList<TermURI>();
	 * 
	 * int index = 0; for(Term t : d.getTerms()) { //Pokud je první (a jediný)
	 * identifikátor inherit, pak se nastaví všecm hodnotám inherit //Pokud by
	 * se inherit objevilo až například jako třetí term, dojde k ignorování celé
	 * deklarace if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
	 * if(d.getTerms().size() == 1) { cursorType = EnumCursor.inherit;
	 * cursorUri.clear(); return true; } else { return false; } } if((t
	 * instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("auto")) {
	 * if(d.getTerms().size()-1 == index) { //Poslední hodnota cursorType =
	 * EnumCursor.auto; processedGeneric = true; continue; } else { return
	 * false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("crosshair")) {
	 * if(d.getTerms().size()-1 == index) { //Poslední hodnota cursorType =
	 * EnumCursor.crosshair; processedGeneric = true; continue; } else { return
	 * false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("default")) {
	 * if(d.getTerms().size()-1 == index) { //Poslední hodnota cursorType =
	 * EnumCursor.prefix_default; processedGeneric = true; continue; } else {
	 * return false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("pointer")) {
	 * if(d.getTerms().size()-1 == index) { //Poslední hodnota cursorType =
	 * EnumCursor.pointer; processedGeneric = true; continue; } else { return
	 * false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("move")) {
	 * if(d.getTerms().size()-1 == index) { //Poslední hodnota cursorType =
	 * EnumCursor.move; processedGeneric = true; continue; } else { return
	 * false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("e-resize")) {
	 * if(d.getTerms().size()-1 == index) { //Poslední hodnota cursorType =
	 * EnumCursor.e_resize; processedGeneric = true; continue; } else { return
	 * false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("ne-resize")) {
	 * if(d.getTerms().size()-1 == index) { //Poslední hodnota cursorType =
	 * EnumCursor.ne_resize; processedGeneric = true; continue; } else { return
	 * false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("nw-resize")) {
	 * if(d.getTerms().size()-1 == index) { //Poslední hodnota cursorType =
	 * EnumCursor.nw_resize; processedGeneric = true; continue; } else { return
	 * false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("n-resize")) {
	 * if(d.getTerms().size()-1 == index) { //Poslední hodnota cursorType =
	 * EnumCursor.n_resize; processedGeneric = true; continue; } else { return
	 * false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("se-resize")) {
	 * if(d.getTerms().size()-1 == index) { //Poslední hodnota cursorType =
	 * EnumCursor.se_resize; processedGeneric = true; continue; } else { return
	 * false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("sw-resize")) {
	 * if(d.getTerms().size()-1 == index) { //Poslední hodnota cursorType =
	 * EnumCursor.sw_resize; processedGeneric = true; continue; } else { return
	 * false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("s-resize")) {
	 * if(d.getTerms().size()-1 == index) { //Poslední hodnota cursorType =
	 * EnumCursor.s_resize; processedGeneric = true; continue; } else { return
	 * false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("w-resize")) {
	 * if(d.getTerms().size()-1 == index) { //Poslední hodnota cursorType =
	 * EnumCursor.w_resize; processedGeneric = true; continue; } else { return
	 * false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("text")) {
	 * if(d.getTerms().size()-1 == index) { //Poslední hodnota cursorType =
	 * EnumCursor.text; processedGeneric = true; continue; } else { return
	 * false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("wait")) {
	 * if(d.getTerms().size()-1 == index) { //Poslední hodnota cursorType =
	 * EnumCursor.wait; processedGeneric = true; continue; } else { return
	 * false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("help")) {
	 * if(d.getTerms().size()-1 == index) { //Poslední hodnota cursorType =
	 * EnumCursor.help; processedGeneric = true; continue; } else { return
	 * false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("progress")) {
	 * if(d.getTerms().size()-1 == index) { //Poslední hodnota cursorType =
	 * EnumCursor.progress; processedGeneric = true; continue; } else { return
	 * false; } } if(t instanceof TermURI) { index++; out.add((TermURI)t);
	 * continue; }
	 * 
	 * return false; }
	 * 
	 * if(processedGeneric) { cursorUri.clear(); cursorUri.addAll(out); return
	 * true; } else { return false; } }
	 */

	@SuppressWarnings("unused")
	private boolean processDirection(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(Direction.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processDisplay(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(Display.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processEmptyCells(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(EmptyCells.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processFloat(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(EmptyCells.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processListStyleImage(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (d.getTerms().size() != 1)
			return false;

		return genericOneIdent(ListStyleImage.class, d, properties)
				|| genericTerm(TermURI.class, d.getTerms().get(0), d
						.getProperty(), ListStyleImage.uri, false, properties,
						values);
	}

	@SuppressWarnings("unused")
	private boolean processListStylePosition(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(ListStylePosition.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processListStyleType(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(ListStyleType.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processListStyle(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		final Variator listStyle = new Variator(3) {

			protected String[] variantPropertyNames = { "list-style-image",
					"list-style-type", "list-style-position" };

			@Override
			protected boolean variant(int v, int i,
					Map<String, CSSProperty> properties,
					Map<String, Term<?>> values) {

				switch (v) {
				case 0:
					// list style image
					return genericTermIdent(ListStyleImage.class, terms[i],
							variantPropertyNames[v], properties)
							|| genericTerm(TermURI.class, terms[i],
									variantPropertyNames[v],
									ListStyleImage.uri, false, properties,
									values);
				case 1:
					// list style type
					return genericTermIdent(ListStyleType.class, terms[i],
							variantPropertyNames[v], properties);
				case 2:
					// list style position
					return genericTermIdent(ListStylePosition.class, terms[i],
							variantPropertyNames[v], properties);
				default:
					return false;
				}
			}

			@Override
			protected boolean inheritance(Map<String, CSSProperty> properties,
					Map<String, Term<?>> values) {
				properties.put(variantPropertyNames[0], ListStyleImage.INHERIT);
				properties.put(variantPropertyNames[1], ListStyleType.INHERIT);
				properties.put(variantPropertyNames[2],
						ListStylePosition.INHERIT);
				return true;
			}

		};

		listStyle.assignTermsFromDeclaration(d);
		return listStyle.vary(properties, values);

	}

	@SuppressWarnings("unused")
	private boolean processMarginTop(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Margin.class, Margin.lenght,
				Margin.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processMarginRight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Margin.class, Margin.lenght,
				Margin.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processMarginBottom(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Margin.class, Margin.lenght,
				Margin.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processMarginLeft(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Margin.class, Margin.lenght,
				Margin.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processMargin(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		final Repeater margin = new Repeater(4) {

			protected String[] propertyNames = new String[] { "margin-top",
					"margin-right", "margin-bottom", "margin-left" };

			@Override
			protected boolean operation(int i,
					Map<String, CSSProperty> properties,
					Map<String, Term<?>> values) {

				return genericTermIdent(Margin.class, terms[i],
						propertyNames[i], properties)
						|| genericTerm(TermLength.class, terms[i],
								propertyNames[i], Margin.lenght, false,
								properties, values)
						|| genericTerm(TermPercent.class, terms[i],
								propertyNames[i], Margin.percentage, false,
								properties, values);
			}
		};

		return margin.repeatOverFourTermDeclaration(d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processMaxHeight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(MaxHeight.class,
				MaxHeight.lenght, MaxHeight.percentage, true, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processMaxWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(MaxWidth.class,
				MaxWidth.lenght, MaxWidth.percentage, true, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processMinHeight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(MinHeight.class,
				MinHeight.lenght, MinHeight.percentage, true, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processMinWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(MinWidth.class,
				MinWidth.lenght, MinWidth.percentage, true, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processOrphans(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrInteger(Orphans.class, Orphans.integer, true,
				d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processOutlineColor(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrColor(OutlineColor.class, OutlineColor.color,
				d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processOutlineStyle(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(OutlineStyle.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processOutlineWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLength(OutlineWidth.class, OutlineWidth.length,
				false, d, properties, values);
	}

	/*
	 * private Boolean processOutline(Declaration d) { NodeData trans =
	 * beginTransaction(); //Nastavení na výchozí hodnoty outlineColorType =
	 * null; outlineColorValue = null; outlineStyleType = null; outlineWidthType =
	 * null; outlineWidthValue = null;
	 * 
	 * //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné
	 * deklaraci //objevila například 2x barva. K určení slouží následující
	 * proměnné boolean processedColor = false; boolean processedStyle = false;
	 * boolean processedWidth = false;
	 * 
	 * for(Term t : d.getTerms()) { //Pokud je první (a jediný) identifikátor
	 * inherit, pak se nastaví všecm hodnotám inherit //Pokud by se inherit
	 * objevilo až například jako třetí term, dojde k ignorování celé deklarace
	 * if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
	 * if(d.getTerms().size() == 1) { outlineColorType =
	 * EnumColorInvert.inherit; outlineColorValue = null; outlineStyleType =
	 * EnumBorderStyle.inherit; outlineWidthType = EnumBorderWidth.inherit;
	 * outlineWidthValue = null; return true; } else {
	 * rollbackTransaction(trans); return false; } }
	 * 
	 * //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten
	 * aktuální) //a v jednotlivých blocích se pokouším tuto deklaraci
	 * zpracovat. Declaration tmpDeclaration = new DataDeclaration("outline");
	 * tmpDeclaration.getTerms().add(t);
	 * 
	 * //Vyzkouším, jestli se jedná o barvu
	 * tmpDeclaration.setProperty("outline-color");
	 * if(processOutlineColor(tmpDeclaration)) { //Jedná se o barvu. Zjistím,
	 * jestli barva už nebyla jednou zadána if(processedColor) { //Barva už byla
	 * jednou zadáno, deklarace je chybná, rollback a konec
	 * rollbackTransaction(trans); return false; } else { //Barva ještě nebyla
	 * zadána, pokračujeme dalším termem processedColor = true; continue; } }
	 * tmpDeclaration.setProperty("outline-style");
	 * if(processOutlineStyle(tmpDeclaration)) { if(processedStyle) {
	 * rollbackTransaction(trans); return false; } else { processedStyle = true;
	 * continue; } } tmpDeclaration.setProperty("outline-width");
	 * if(processOutlineWidth(tmpDeclaration)) { if(processedWidth) {
	 * rollbackTransaction(trans); return false; } else { processedWidth = true;
	 * continue; } }
	 * 
	 * //Pokud program dojde sem, znamená to že term není ani image, position
	 * nebo type - ignorace celé deklarace rollbackTransaction(trans); return
	 * false; } return true; }
	 */

	@SuppressWarnings("unused")
	private boolean processOverflow(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(Overflow.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processPaddingTop(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Padding.class, Padding.length,
				Padding.percentage, true, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processPaddingRight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Padding.class, Padding.length,
				Padding.percentage, true, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processPaddingBottom(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Padding.class, Padding.length,
				Padding.percentage, true, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processPaddingLeft(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Padding.class, Padding.length,
				Padding.percentage, true, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processPadding(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		final Repeater padding = new Repeater(4) {

			protected String[] propertyNames = new String[] { "padding-top",
					"padding-right", "padding-bottom", "padding-left" };

			@Override
			protected boolean operation(int i,
					Map<String, CSSProperty> properties,
					Map<String, Term<?>> values) {

				return genericTermIdent(Margin.class, terms[i],
						propertyNames[i], properties)
						|| genericTerm(TermLength.class, terms[i],
								propertyNames[i], Margin.lenght, false,
								properties, values)
						|| genericTerm(TermPercent.class, terms[i],
								propertyNames[i], Margin.percentage, false,
								properties, values);
			}
		};

		return padding.repeatOverFourTermDeclaration(d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processPageBreakAfter(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(PageBreak.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processPageBreakBefore(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(PageBreak.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processPageBreakInside(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(PageBreakInside.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processPosition(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(Position.class, d, properties);
	}

	/*
	 * private Boolean processQuotes(Declaration d) { ArrayList<String> out =
	 * new ArrayList<String>(); for(Term t : d.getTerms()) { //Pokud je první
	 * (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
	 * //Pokud by se inherit objevilo až například jako třetí term, dojde k
	 * ignorování celé deklarace if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
	 * if(d.getTerms().size() == 1) { quotesType = EnumQuotes.inherit;
	 * quotesValues.clear(); return true; } else { return false; } } if((t
	 * instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
	 * if(d.getTerms().size() == 1) { quotesType = EnumQuotes.none;
	 * quotesValues.clear(); return true; } else { return false; } } if((t
	 * instanceof TermString)) { out.add(((TermString)t).getValue()); continue; }
	 * return false; }
	 * 
	 * if(!out.isEmpty() && out.size() % 2 == 0) { quotesType =
	 * EnumQuotes.list_values; quotesValues.clear(); quotesValues.addAll(out);
	 * return true; } else { return false; } }
	 */
	@SuppressWarnings("unused")
	private boolean processTableLayout(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(TableLayout.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processTextAlign(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(TextAlign.class, d, properties);
	}

	/*
	 * private Boolean processTextDecoration(Declaration d) { NodeData trans =
	 * beginTransaction(); //Nastavení na výchozí hodnoty textDecorationType =
	 * null; textDecorationUnderline = null; textDecorationOverline = null;
	 * textDecorationLineThrough = null; textDecorationBlink = null;
	 * 
	 * for(Term t : d.getTerms()) { //Pokud je první (a jediný) identifikátor
	 * inherit, pak se nastaví všecm hodnotám inherit //Pokud by se inherit
	 * objevilo až například jako třetí term, dojde k ignorování celé deklarace
	 * if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
	 * if(d.getTerms().size() == 1) { textDecorationType =
	 * EnumTextDecoration.inherit; textDecorationUnderline = null;
	 * textDecorationOverline = null; textDecorationLineThrough = null;
	 * textDecorationBlink = null; return true; } else {
	 * rollbackTransaction(trans); return false; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
	 * if(d.getTerms().size() == 1) { textDecorationType =
	 * EnumTextDecoration.set; textDecorationUnderline = false;
	 * textDecorationOverline = false; textDecorationLineThrough = false;
	 * textDecorationBlink = false; return true; } else {
	 * rollbackTransaction(trans); return false; } }
	 * 
	 * if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("underline")) {
	 * textDecorationType = EnumTextDecoration.set; if(textDecorationBlink ==
	 * null) { textDecorationBlink = false; } if(textDecorationLineThrough ==
	 * null) { textDecorationLineThrough = false; } if(textDecorationOverline ==
	 * null) { textDecorationOverline = false; }
	 * 
	 * if(textDecorationUnderline != null && textDecorationUnderline) {
	 * rollbackTransaction(trans); return false; } else {
	 * textDecorationUnderline = true; continue; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("overline")) {
	 * textDecorationType = EnumTextDecoration.set; if(textDecorationBlink ==
	 * null) { textDecorationBlink = false; } if(textDecorationLineThrough ==
	 * null) { textDecorationLineThrough = false; } if(textDecorationUnderline ==
	 * null) { textDecorationUnderline = false; }
	 * 
	 * if(textDecorationOverline != null && textDecorationOverline) {
	 * rollbackTransaction(trans); return false; } else { textDecorationOverline =
	 * true; continue; } } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("line-through")) {
	 * textDecorationType = EnumTextDecoration.set; if(textDecorationBlink ==
	 * null) { textDecorationBlink = false; } if(textDecorationOverline == null) {
	 * textDecorationOverline = false; } if(textDecorationUnderline == null) {
	 * textDecorationUnderline = false; }
	 * 
	 * if(textDecorationLineThrough != null && textDecorationLineThrough) {
	 * rollbackTransaction(trans); return false; } else {
	 * textDecorationLineThrough = true; continue; } } if((t instanceof
	 * TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("blink")) {
	 * textDecorationType = EnumTextDecoration.set; if(textDecorationLineThrough ==
	 * null) { textDecorationLineThrough = false; } if(textDecorationOverline ==
	 * null) { textDecorationOverline = false; } if(textDecorationUnderline ==
	 * null) { textDecorationUnderline = false; }
	 * 
	 * if(textDecorationBlink != null && textDecorationBlink) {
	 * rollbackTransaction(trans); return false; } else { textDecorationBlink =
	 * true; continue; } }
	 * 
	 * //Pokud program dojde sem, znamená to že term není ani image, position
	 * nebo type - ignorace celé deklarace rollbackTransaction(trans); return
	 * false; } return true; }
	 */

	@SuppressWarnings("unused")
	private boolean processTextIdent(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(TextIndent.class,
				TextIndent.length, TextIndent.percentage, false, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processTextTransform(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(TextTransform.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processUnicodeBidi(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(UnicodeBidi.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processVerticalAlign(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(VerticalAlign.class,
				VerticalAlign.length, VerticalAlign.percentage, false, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processVisibility(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(Visibility.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processWhiteSpace(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(WhiteSpace.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processWidows(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrInteger(Widows.class, Widows.integer, true, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processWordSpacing(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLength(WordSpacing.class, WordSpacing.length,
				false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processZIndex(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrInteger(ZIndex.class, ZIndex.integer, false, d,
				properties, values);
	}

	/*
	 * private Boolean processContent(Declaration d) {
	 * 
	 * ArrayList<Term<?>> out = new ArrayList<Term<?>>();
	 * 
	 * for(Term t : d.getTerms()) { //Pokud je první (a jediný) identifikátor
	 * inherit, pak se nastaví všecm hodnotám inherit //Pokud by se inherit
	 * objevilo až například jako třetí term, dojde k ignorování celé deklarace
	 * if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
	 * if(d.getTerms().size() == 1) { contentType = EnumContent.inherit;
	 * contentValues.clear(); return true; } else { return false; } } if((t
	 * instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("normal")) {
	 * if(d.getTerms().size() == 1) { contentType = EnumContent.normal;
	 * contentValues.clear(); return true; } else { return false; } } if((t
	 * instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
	 * if(d.getTerms().size() == 1) { contentType = EnumContent.none;
	 * contentValues.clear(); return true; } else { return false; } }
	 * 
	 * if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("open-quote")) { out.add(t);
	 * continue; }
	 * 
	 * if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("close-quote")) { out.add(t);
	 * continue; } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("no-open-quote")) {
	 * out.add(t); continue; } if((t instanceof TermIdent) &&
	 * ((TermIdent)t).getValue().equalsIgnoreCase("no-close-quote")) {
	 * out.add(t); continue; } if(t instanceof TermString) { out.add(t);
	 * continue; } if(t instanceof TermURI) { out.add(t); continue; } if((t
	 * instanceof TermFunction) &&
	 * ((TermFunction)t).getFunctionName().equalsIgnoreCase("counter")) {
	 * out.add(t); continue; } if((t instanceof TermFunction) &&
	 * ((TermFunction)t).getFunctionName().equalsIgnoreCase("counters")) {
	 * out.add(t); continue; } if((t instanceof TermFunction) &&
	 * ((TermFunction)t).getFunctionName().equalsIgnoreCase("attr")) {
	 * out.add(t); continue; } return false; }
	 * 
	 * if(!out.isEmpty()) { contentType = EnumContent.list_values;
	 * contentValues.clear(); contentValues.addAll(out); return true; } else {
	 * return false; } }
	 */
}
