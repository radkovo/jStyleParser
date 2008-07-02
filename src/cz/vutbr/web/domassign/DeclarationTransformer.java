package cz.vutbr.web.domassign;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

/**
 * Contains methods to transform declaration into 
 * values applicable to NodeDataImpl.
 * Contains map of CSS properties as supported in CSS 2.1 and
 * their default values.
 * Implements singleton pattern.
 * @author kapy
 *
 */
public class DeclarationTransformer {

	private static Logger log = Logger.getLogger(DeclarationTransformer.class);
	
	public static final int TOTAL_SUPPORTED_DECLARATIONS = 117; 
	
	private static final CSSProperty INHERITABLE_PROPERTY = 
		new CSSProperty() {
			public boolean inherited() {return true;}
		};
		
	private static final CSSProperty NOT_INHERITABLE_PROPERTY = 
		new CSSProperty() {
			public boolean inherited() {return false;}
		};	
	
	private static final CSSProperty MULTIVALUE_PROPERTY = 
		new CSSProperty() {
			public boolean inherited() {return false;}
		};
	
	/**
	 * Contains names of supported elements and default values
	 * according to
	 * <a href="http://www.culturedcode.com/css/reference.html">
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
	private static DeclarationTransformer instance;
	
	
	/**
	 * Returns instance if exists, or creates new one
	 * @return Singleton instance
	 */
	public static DeclarationTransformer getInstance() {
		if(instance==null)
			instance = new DeclarationTransformer();
		
		return instance;
	}
	
	/**
	 * Converts string divided by dash ('-') characters into
	 * camelCase such as convenient for Java method names
	 * @param string String to convert
	 * @return CamelCase version of string
	 */
	public static final String camelCase(String string) { 
		
		StringBuilder sb = new StringBuilder();
		
		boolean upperFlag = false; 
		
		for(int i=0; i< string.length(); i++) {
			char ch = string.charAt(i);
			if(ch=='-') 
				upperFlag = true;
			else if(upperFlag && Character.isLetter(ch)) {
				sb.append(Character.toUpperCase(ch));
				upperFlag = false;
			}
			else if(!upperFlag && Character.isLetter(ch))
				sb.append(ch);
			else if(ch=='_') // vendor extension
				sb.append(ch);
		}
		return sb.toString();
	}
	

	/**
	 * Test function. Returns random supported CSS property
	 * @return Default value of randomly chosen supported CSS property
	 */
	public CSSProperty randomValue() {
		
		Random generator = new Random();
		
		int i = generator.nextInt(supportedCSS.size());
		for(CSSProperty prop: supportedCSS.values()) {
			if(i==0) return prop;
			i--;
		}
		// should never reach this statement
		return null;
	}
	
	/**
	 * Core function. Parses CSS declaration into structure applicable
	 * to DataNodeImpl
	 * @param d Declaration
	 * @param properties Holder of parsed declaration's properties
	 * @param values Holder of parsed declaration's value
	 * @return <code>true</code> in case of success, <code>false</code> otherwise
	 */
	public boolean parseDeclaration(Declaration d, 
			Map<String,CSSProperty> properties, Map<String,Term<?>> values,
			Map<String, List<Term<?>>> listValues) {
		
		String propertyName = d.getProperty().toLowerCase();
		
		CSSProperty defaultValue = supportedCSS.get(propertyName);
		// no such declaration is supported
		if(defaultValue==null)
			return false;
		
		try {
			Method m = methods.get(propertyName);
			if(m!=null) 
				return (Boolean) m.invoke(this, d, properties, values, listValues);
		}
		catch(IllegalArgumentException e) {
			log.warn("Illegal argument: " + e);
		}
		catch(IllegalAccessException e) {
			log.warn("Illegal access: " + e);
		}
		catch(InvocationTargetException e) {
			log.warn("Invocation target: " + e);
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
	 * @return Constructed map
	 */
	private Map<String, CSSProperty> supportedCSS() {
		
		Map<String, CSSProperty> map = 
			new HashMap<String, CSSProperty>(TOTAL_SUPPORTED_DECLARATIONS, 1.0f);
		
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
		map.put("margin-top", Margin.lenght);		// 0
		map.put("margin-right", Margin.lenght);	// 0
		map.put("margin-bottom", Margin.lenght);	// 0
		map.put("margin-left", Margin.lenght);	// 0	
		map.put("padding", MULTIVALUE_PROPERTY);		
		map.put("padding-top", Padding.length);	// 0
		map.put("padding-right", Padding.length);	// 0
		map.put("padding-bottom", Padding.length);// 0
		map.put("padding-left", Padding.length);	// 0
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
		map.put("min-width", MinWidth.lenght);		// 0
		map.put("max-width", MaxWidth.NONE);
		map.put("height", Width.AUTO);
		map.put("min-height", MinHeight.lenght);	// 0
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
		map.put("border-spacing", BorderSpacing.length);	// 0
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

		map.put("widows", Widows.integer);		// 2
		map.put("orphans", Orphans.integer);	// 2

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
		map.put("pause-after", PauseBefore.time);  // 0
		map.put("pitch-range", PitchRange.number); // 50
		map.put("pitch", Pitch.MEDIUM);
		map.put("play-during", PlayDuring.AUTO);
		map.put("richness", Richness.number);		// 50
		map.put("speak-header", SpeakHeader.ONCE);
		map.put("speak-numeral", SpeakNumeral.CONTINUOUS);
		map.put("speak-punctuation", SpeakPunctuation.NONE);
		map.put("speak", Speak.NORMAL);
		map.put("speech-rate", SpeechRate.MEDIUM);
		map.put("stress", Stress.number);			// 50
		map.put("voice-family", INHERITABLE_PROPERTY);
		map.put("volume", Volume.MEDIUM);

		if(log.isInfoEnabled()) {
			log.info("Total supported properties: " + map.size());
		}

		return map;
	}
	
	private Map<String, Method> parsingMethods() {
		
		Map<String, Method> map = 
			new HashMap<String, Method>(TOTAL_SUPPORTED_DECLARATIONS, 1.0f);
		
		for(String key: supportedCSS.keySet()) {
			try {
				Method m = DeclarationTransformer.class.getDeclaredMethod(
						DeclarationTransformer.camelCase("process-" + key), 
						new Class[] {	Declaration.class, 
							Map.class, Map.class, Map.class});
				map.put(key, m);
			}
			catch(Exception e) {
				log.warn("Unable to find method for property: " + key);
			}
		}
		if(log.isInfoEnabled()) {
			log.info("Total methods found: " + map.size());
		}
		return map;
	}
	
	
	
	/**
	 * Converts TermIdent into value of enum of given class and stores
	 * it into properties map under key property
	 * @param <T> 
	 * @param enumType
	 * @param term
	 * @param properties
	 * @param property
	 * @return <code>true</code> in case of success, <code>false</code> otherwise
	 */
	private <T extends Enum<T> & CSSProperty> boolean genericProperty(
				Class<T> enumType, 
				TermIdent term, 
				Map<String, CSSProperty> properties,
				String property) {
		
		// try to find enum with given value and if so
		// insert it inside
		try {
			final String name = term.getValue().replace("-", "_").toUpperCase();
			properties.put(property, Enum.valueOf(enumType, name));
			return true;
		}
		catch (IllegalArgumentException e) {
			// no such enum value
		}
		catch (NullPointerException e) {
			log.warn("TermIdent contained empty value!");
		}
		
		return false;
		
	}
	
	/**
	 * Converts TermIdent into value of enum for given class, check where 
	 * there is only none term in Declaration
	 * @param <T>
	 * @param enumType
	 * @param d
	 * @param properties
	 * @return
	 */
	private <T extends Enum<T> & CSSProperty> boolean genericTermIdent(
			Class<T> enumType, 
			Declaration d, 
			Map<String, CSSProperty> properties) {
		
		if(d.getTerms().size()!=1) return false;
    	final Term<?> term = d.getTerms().get(0);
    	
    	if(term instanceof TermIdent) {
    		return genericProperty(enumType, (TermIdent) term, 
					properties, d.getProperty());
    	}
    	return false; 
		
	}
	
	private <T extends Enum<T> & CSSProperty> boolean genericTermColor(
			Declaration d,
			T colorIdentification,
			Map<String, CSSProperty> properties,
			Map<String, Term<?>> values) {
		
		if(d.getTerms().size()!=1) return false;
    	final Term<?> term = d.getTerms().get(0);
		
    	if(term instanceof TermColor) {
			properties.put(d.getProperty(), colorIdentification);
			values.put(d.getProperty(), term);
			return true;
		}
		
		return false;
    	
	}
	
	private <T extends Enum<T> & CSSProperty> boolean genericTermNumberLength(
			Declaration d,
			T lengthIdentification,
			boolean sanify,
			Map<String, CSSProperty> properties,
			Map<String, Term<?>> values
			) {
		
		if(d.getTerms().size()!=1) return false;
    	final Term<?> term = d.getTerms().get(0);
		
    	if(term instanceof TermLength) {
    		
    		// check if below zero, if so set value to zero
    		if(sanify) {
    			Float zero = new Float(0.0f);
    			if(zero.compareTo(((TermLength) term).getValue())>0) {
    				((TermLength) term).setValue(zero);
    			}
    		}
    		
			properties.put(d.getProperty(), lengthIdentification);
			values.put(d.getProperty(), term);
			return true;
		}
		
		return false;
		
	}
	
	private <T extends Enum<T> & CSSProperty> boolean genericTermNumberInteger(
			Declaration d,
			T integerIdentification,
			boolean sanify,
			Map<String, CSSProperty> properties,
			Map<String, Term<?>> values
			) {
		
		if(d.getTerms().size()!=1) return false;
    	final Term<?> term = d.getTerms().get(0);
		
    	if(term instanceof TermInteger) {
    		
    		// check if below zero, if so set value to zero
    		if(sanify) {
    			Integer zero = new Integer(0);
    			if(zero.compareTo(((TermInteger) term).getValue())>0) {
    				((TermInteger) term).setValue(zero);
    			}
    		}
    		
			properties.put(d.getProperty(), integerIdentification);
			values.put(d.getProperty(), term);
			return true;
		}
		
		return false;
		
	}
	
	
	private <T extends Enum<T> & CSSProperty> boolean genericTermPercent(
			Declaration d,
			T percentIdentification,
			boolean sanify,
			Map<String, CSSProperty> properties,
			Map<String, Term<?>> values
			) {
		
		if(d.getTerms().size()!=1) return false;
    	final Term<?> term = d.getTerms().get(0);
		
    	if(term instanceof TermPercent) {
    		
    		// check if below zero, if so set value to zero
    		if(sanify) {
    			Float zero = new Float(0.0f);
    			if(zero.compareTo(((TermPercent) term).getValue())>0) {
    				((TermPercent) term).setValue(zero);
    			}
    		}
    		
			properties.put(d.getProperty(), percentIdentification);
			values.put(d.getProperty(), term);
			return true;
		}
		
		return false;
		
	}
	
	
	private <T extends Enum<T> & CSSProperty> boolean genericTermIdentOrColor(
			Class<T> enumType,
			T colorIdentification,
			Declaration d,
			Map<String, CSSProperty> properties,
			Map<String, Term<?>> values) {
	
		return genericTermIdent(enumType, d, properties)
			|| genericTermColor(d, colorIdentification, properties, values);
	}
		
	private <T extends Enum<T> & CSSProperty> boolean genericTermIdentOrInteger(
			Class<T> enumType,
			T integerIdentification,
			boolean sanify,
			Declaration d,
			Map<String, CSSProperty> properties,
			Map<String, Term<?>> values) {
		
		return genericTermIdent(enumType, d, properties)
			|| genericTermNumberInteger(d, integerIdentification, sanify, properties, values);
	}	
	
	private <T extends Enum<T> & CSSProperty> boolean genericTermIdentOrLength(
			Class<T> enumType,
			T lengthIdentification,
			boolean sanify,
			Declaration d,
			Map<String, CSSProperty> properties,
			Map<String, Term<?>> values) {
		
		return genericTermIdent(enumType, d, properties)
			|| genericTermNumberLength(d, lengthIdentification, sanify, properties, values);
	}
	
	
	private <T extends Enum<T> & CSSProperty> boolean genericTermIdentOrLengthOrPercent(
			Class<T> enumType,
			T lengthIdentification,
			T percentIdentification,
			boolean sanify,
			Declaration d,
			Map<String, CSSProperty> properties,
			Map<String, Term<?>> values) {
		
		return genericTermIdent(enumType, d, properties)
			|| genericTermNumberLength(d, lengthIdentification, sanify, properties, values)
			|| genericTermPercent(d, percentIdentification, sanify, properties, values);
	}
	
	// =============================================================
	// processing methods
	
	@SuppressWarnings("unused")
	private boolean processColor(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
		return genericTermIdentOrColor(Color.class, Color.color, d, properties, values);
    }
	
    @SuppressWarnings("unused")	
    private boolean processBackgroundAttachment(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(BackgroundAttachment.class, d, properties);
    }
    
    @SuppressWarnings("unused")
    private Boolean processBackgroundColor(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrColor(BackgroundColor.class, BackgroundColor.color, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private Boolean processBackgroundImage(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	
    	if(d.getTerms().size()!=1) return false;
    	
    	final Term<?> term = d.getTerms().get(0);
		
		if(term instanceof TermIdent) {
			return genericProperty(BackgroundImage.class, (TermIdent) term, 
					properties, d.getProperty());
		}
		else if(term instanceof TermURI) {
			properties.put(d.getProperty(), BackgroundImage.uri);
			values.put(d.getProperty(), term);
			return true;
		}		
		return false; 
    }
    
    @SuppressWarnings("unused")
    private Boolean processBackgroundRepeat(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(BackgroundRepeat.class, d, properties);
    }
        
    @SuppressWarnings("unused")
    private boolean processBorderCollapse(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(BorderCollapse.class, d, properties);
    }
    
     
    @SuppressWarnings("unused")
    private boolean processBorderTopColor(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrColor(BorderColor.class, BorderColor.color, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processBorderRightColor(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrColor(BorderColor.class, BorderColor.color, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processBorderBottomColor(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrColor(BorderColor.class, BorderColor.color, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processBorderLeftColor(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrColor(BorderColor.class, BorderColor.color, d, properties, values);
    }

    @SuppressWarnings("unused")
    private boolean processBorderTopStyle(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(BorderStyle.class, d, properties);
    }
    
    @SuppressWarnings("unused")
    private boolean processBorderRightStyle(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(BorderStyle.class, d, properties);
    }
    
    @SuppressWarnings("unused")
    private boolean processBorderBottomStyle(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(BorderStyle.class, d, properties);
    }
    
    @SuppressWarnings("unused")
    private boolean processBorderLeftStyle(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(BorderStyle.class, d, properties);
    }    
    
    @SuppressWarnings("unused")
    private boolean processBorderSpacing(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	
    	if(d.getTerms().size() == 1) {
    		final Term<?> term = d.getTerms().get(0);
    		if(term instanceof TermIdent) {
    			return genericProperty(BorderSpacing.class, 
    					(TermIdent)term, properties, d.getProperty());
    		}
    		// FIXME
    		// numerical values will be doubled
    		else if(term instanceof TermLength) {
    			final TermNumber nterm = (TermNumber) term;  
    			// sanity check
    			if(nterm.getValue() < 0) nterm.setValue(0.0f); 
    				
    			properties.put(d.getProperty(), BorderSpacing.length);
    			// construct list of terms
    			List<Term<?>> terms = new ArrayList<Term<?>>(2);
    			terms.add(nterm);terms.add(nterm);
    			listValues.put(d.getProperty(), terms);
    			return true;
    		}
    		
    	}
    	// FIXME
    	// two numerical values
    	else if(d.getTerms().size()==2) {
    		final Term<?> term1 = d.getTerms().get(0);
    		final Term<?> term2 = d.getTerms().get(1);
    		if(term1 instanceof TermLength && term2 instanceof TermLength) {
    			final TermNumber nterm1 = (TermNumber) term1;
        		final TermNumber nterm2 = (TermNumber) term2;
        		// sanity checks
        		if(nterm1.getValue() < 0) nterm1.setValue(0.0f); 
        		if(nterm2.getValue() < 0) nterm2.setValue(0.0f);
        		
        		properties.put(d.getProperty(), BorderSpacing.length);
    			// construct list of terms
    			List<Term<?>> terms = new ArrayList<Term<?>>(2);
    			terms.add(nterm1);terms.add(nterm2);
    			listValues.put(d.getProperty(), terms);
    			return true;
    		}
    	}
    	return false;
    }
    
    /*
    private Boolean processBorderColor(Declaration d) {
        NodeData trans = beginTransaction();
        if(d.getTerms().size() == 1) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-color");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-color");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-color");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-color");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(0));
            
            if(processBorderTopColor(tmpDeclarationTop) && processBorderRightColor(tmpDeclarationRight) &&
               processBorderBottomColor(tmpDeclarationBottom) && processBorderLeftColor(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 2) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-color");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-color");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-color");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-color");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processBorderTopColor(tmpDeclarationTop) && processBorderRightColor(tmpDeclarationRight) &&
               processBorderBottomColor(tmpDeclarationBottom) && processBorderLeftColor(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 3) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-color");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-color");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-color");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-color");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processBorderTopColor(tmpDeclarationTop) && processBorderRightColor(tmpDeclarationRight) &&
               processBorderBottomColor(tmpDeclarationBottom) && processBorderLeftColor(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 4) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-color");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-color");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-color");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-color");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(3));
            
            if(processBorderTopColor(tmpDeclarationTop) && processBorderRightColor(tmpDeclarationRight) &&
               processBorderBottomColor(tmpDeclarationBottom) && processBorderLeftColor(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderStyle(Declaration d) {
        NodeData trans = beginTransaction();
        if(d.getTerms().size() == 1) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-style");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-style");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-style");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-style");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(0));
            
            if(processBorderTopStyle(tmpDeclarationTop) && processBorderRightStyle(tmpDeclarationRight) &&
               processBorderBottomStyle(tmpDeclarationBottom) && processBorderLeftStyle(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 2) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-style");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-style");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-style");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-style");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processBorderTopStyle(tmpDeclarationTop) && processBorderRightStyle(tmpDeclarationRight) &&
               processBorderBottomStyle(tmpDeclarationBottom) && processBorderLeftStyle(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 3) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-style");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-style");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-style");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-style");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processBorderTopStyle(tmpDeclarationTop) && processBorderRightStyle(tmpDeclarationRight) &&
               processBorderBottomStyle(tmpDeclarationBottom) && processBorderLeftStyle(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 4) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-style");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-style");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-style");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-style");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(3));
            
            if(processBorderTopStyle(tmpDeclarationTop) && processBorderRightStyle(tmpDeclarationRight) &&
               processBorderBottomStyle(tmpDeclarationBottom) && processBorderLeftStyle(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        return new Boolean(false);
    }
    */
    @SuppressWarnings("unused")
    private boolean processBorderTopWidth(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrLength(BorderWidth.class, BorderWidth.length, true, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processBorderRightWidth(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrLength(BorderWidth.class, BorderWidth.length, true, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processBorderBottomWidth(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrLength(BorderWidth.class, BorderWidth.length, true, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processBorderLeftWidth(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrLength(BorderWidth.class, BorderWidth.length, true, d, properties, values);
    }
       
    /*
    private Boolean processBorderWidth(Declaration d) {
        NodeData trans = beginTransaction();
        if(d.getTerms().size() == 1) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-width");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-width");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-width");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-width");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(0));
            
            if(processBorderTopWidth(tmpDeclarationTop) && processBorderRightWidth(tmpDeclarationRight) &&
               processBorderBottomWidth(tmpDeclarationBottom) && processBorderLeftWidth(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 2) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-width");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-width");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-width");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-width");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processBorderTopWidth(tmpDeclarationTop) && processBorderRightWidth(tmpDeclarationRight) &&
               processBorderBottomWidth(tmpDeclarationBottom) && processBorderLeftWidth(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 3) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-width");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-width");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-width");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-width");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processBorderTopWidth(tmpDeclarationTop) && processBorderRightWidth(tmpDeclarationRight) &&
               processBorderBottomWidth(tmpDeclarationBottom) && processBorderLeftWidth(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 4) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-width");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-width");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-width");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-width");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(3));
            
            if(processBorderTopWidth(tmpDeclarationTop) && processBorderRightWidth(tmpDeclarationRight) &&
               processBorderBottomWidth(tmpDeclarationBottom) && processBorderLeftWidth(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderTop(Declaration d) {
        NodeData trans = beginTransaction();
        //Nastavení na výchozí hodnoty
        borderColorTopType = null;
        borderColorTopValue = null;
        borderStyleTopType = null;
        borderWidthTopType = null;
        borderWidthTopValue = null;
        
        //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné deklaraci
        //objevila například 2x barva. K určení slouží následující proměnné
        boolean processedColor = false;
        boolean processedStyle = false;
        boolean processedWidth = false;
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    borderColorTopType = EnumColorTransparent.inherit;
                    borderColorTopValue = null;
                    borderStyleTopType = EnumBorderStyle.inherit;
                    borderWidthTopType = EnumBorderWidth.inherit;
                    borderWidthTopValue = null;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten aktuální)
            //a v jednotlivých blocích se pokouším tuto deklaraci zpracovat. 
            Declaration tmpDeclaration = new DataDeclaration("border-top");
            tmpDeclaration.getTerms().add(t);
            
            //Vyzkouším, jestli se jedná o barvu
            tmpDeclaration.setProperty("border-top-color");
            if(processBorderTopColor(tmpDeclaration)) {
                //Jedná se o barvu. Zjistím, jestli barva už nebyla jednou zadána
                if(processedColor) {
                    //Barva už byla jednou zadáno, deklarace je chybná, rollback a konec
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    //Barva ještě nebyla zadána, pokračujeme dalším termem
                    processedColor = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-top-style");
            if(processBorderTopStyle(tmpDeclaration)) {
                if(processedStyle) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedStyle = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-top-width");
            if(processBorderTopWidth(tmpDeclaration)) {
                if(processedWidth) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedWidth = true;
                    continue;
                }
            }
            
            //Pokud program dojde sem, znamená to že term není ani color, style nebo width - ignorace celé deklarace
            rollbackTransaction(trans);
            return false;
        }
        return true;
    }
    
    private Boolean processBorderRight(Declaration d) {
        NodeData trans = beginTransaction();
        //Nastavení na výchozí hodnoty
        borderColorRightType = null;
        borderColorRightValue = null;
        borderStyleRightType = null;
        borderWidthRightType = null;
        borderWidthRightValue = null;
        
        //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné deklaraci
        //objevila například 2x barva. K určení slouží následující proměnné
        boolean processedColor = false;
        boolean processedStyle = false;
        boolean processedWidth = false;
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    borderColorRightType = EnumColorTransparent.inherit;
                    borderColorRightValue = null;
                    borderStyleRightType = EnumBorderStyle.inherit;
                    borderWidthRightType = EnumBorderWidth.inherit;
                    borderWidthRightValue = null;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten aktuální)
            //a v jednotlivých blocích se pokouším tuto deklaraci zpracovat. 
            Declaration tmpDeclaration = new DataDeclaration("border-right");
            tmpDeclaration.getTerms().add(t);
            
            //Vyzkouším, jestli se jedná o barvu
            tmpDeclaration.setProperty("border-right-color");
            if(processBorderRightColor(tmpDeclaration)) {
                //Jedná se o barvu. Zjistím, jestli barva už nebyla jednou zadána
                if(processedColor) {
                    //Barva už byla jednou zadáno, deklarace je chybná, rollback a konec
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    //Barva ještě nebyla zadána, pokračujeme dalším termem
                    processedColor = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-right-style");
            if(processBorderRightStyle(tmpDeclaration)) {
                if(processedStyle) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedStyle = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-right-width");
            if(processBorderRightWidth(tmpDeclaration)) {
                if(processedWidth) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedWidth = true;
                    continue;
                }
            }
            
            //Pokud program dojde sem, znamená to že term není ani color, style nebo width - ignorace celé deklarace
            rollbackTransaction(trans);
            return false;
        }
        return true;
    }
    
    private Boolean processBorderBottom(Declaration d) {
        NodeData trans = beginTransaction();
        //Nastavení na výchozí hodnoty
        borderColorBottomType = null;
        borderColorBottomValue = null;
        borderStyleBottomType = null;
        borderWidthBottomType = null;
        borderWidthBottomValue = null;
        
        //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné deklaraci
        //objevila například 2x barva. K určení slouží následující proměnné
        boolean processedColor = false;
        boolean processedStyle = false;
        boolean processedWidth = false;
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    borderColorBottomType = EnumColorTransparent.inherit;
                    borderColorBottomValue = null;
                    borderStyleBottomType = EnumBorderStyle.inherit;
                    borderWidthBottomType = EnumBorderWidth.inherit;
                    borderWidthBottomValue = null;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten aktuální)
            //a v jednotlivých blocích se pokouším tuto deklaraci zpracovat. 
            Declaration tmpDeclaration = new DataDeclaration("border-bottom");
            tmpDeclaration.getTerms().add(t);
            
            //Vyzkouším, jestli se jedná o barvu
            tmpDeclaration.setProperty("border-bottom-color");
            if(processBorderBottomColor(tmpDeclaration)) {
                //Jedná se o barvu. Zjistím, jestli barva už nebyla jednou zadána
                if(processedColor) {
                    //Barva už byla jednou zadáno, deklarace je chybná, rollback a konec
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    //Barva ještě nebyla zadána, pokračujeme dalším termem
                    processedColor = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-bottom-style");
            if(processBorderBottomStyle(tmpDeclaration)) {
                if(processedStyle) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedStyle = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-bottom-width");
            if(processBorderBottomWidth(tmpDeclaration)) {
                if(processedWidth) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedWidth = true;
                    continue;
                }
            }
            
            //Pokud program dojde sem, znamená to že term není ani color, style nebo width - ignorace celé deklarace
            rollbackTransaction(trans);
            return false;
        }
        return true;
    }
    
    private Boolean processBorderLeft(Declaration d) {
        NodeData trans = beginTransaction();
        //Nastavení na výchozí hodnoty
        borderColorLeftType = null;
        borderColorLeftValue = null;
        borderStyleLeftType = null;
        borderWidthLeftType = null;
        borderWidthLeftValue = null;
        
        //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné deklaraci
        //objevila například 2x barva. K určení slouží následující proměnné
        boolean processedColor = false;
        boolean processedStyle = false;
        boolean processedWidth = false;
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    borderColorLeftType = EnumColorTransparent.inherit;
                    borderColorLeftValue = null;
                    borderStyleLeftType = EnumBorderStyle.inherit;
                    borderWidthLeftType = EnumBorderWidth.inherit;
                    borderWidthLeftValue = null;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten aktuální)
            //a v jednotlivých blocích se pokouším tuto deklaraci zpracovat. 
            Declaration tmpDeclaration = new DataDeclaration("border-left");
            tmpDeclaration.getTerms().add(t);
            
            //Vyzkouším, jestli se jedná o barvu
            tmpDeclaration.setProperty("border-left-color");
            if(processBorderLeftColor(tmpDeclaration)) {
                //Jedná se o barvu. Zjistím, jestli barva už nebyla jednou zadána
                if(processedColor) {
                    //Barva už byla jednou zadáno, deklarace je chybná, rollback a konec
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    //Barva ještě nebyla zadána, pokračujeme dalším termem
                    processedColor = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-left-style");
            if(processBorderLeftStyle(tmpDeclaration)) {
                if(processedStyle) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedStyle = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-left-width");
            if(processBorderLeftWidth(tmpDeclaration)) {
                if(processedWidth) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedWidth = true;
                    continue;
                }
            }
            
            //Pokud program dojde sem, znamená to že term není ani color, style nebo width - ignorace celé deklarace
            rollbackTransaction(trans);
            return false;
        }
        return true;
    }
    
    private Boolean processBorder(Declaration d) {
        NodeData trans = beginTransaction();
        //Nastavení na výchozí hodnoty
        borderColorTopType = null;
        borderColorRightType = null;
        borderColorBottomType = null;
        borderColorLeftType = null;
        borderColorTopValue = null;
        borderColorRightValue = null;
        borderColorBottomValue = null;
        borderColorLeftValue = null;
        borderStyleTopType = null;
        borderStyleRightType = null;
        borderStyleBottomType = null;
        borderStyleLeftType = null;
        borderWidthTopType = null;
        borderWidthRightType = null;
        borderWidthBottomType = null;
        borderWidthLeftType = null;
        borderWidthTopValue = null;
        borderWidthRightValue = null;
        borderWidthBottomValue = null;
        borderWidthLeftValue = null; 
        
        //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné deklaraci
        //objevila například 2x barva. K určení slouží následující proměnné
        boolean processedColor = false;
        boolean processedStyle = false;
        boolean processedWidth = false;
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    borderColorTopType = EnumColorTransparent.inherit;
                    borderColorRightType = EnumColorTransparent.inherit;
                    borderColorBottomType = EnumColorTransparent.inherit;
                    borderColorLeftType = EnumColorTransparent.inherit;
                    borderColorTopValue = null;
                    borderColorRightValue = null;
                    borderColorBottomValue = null;
                    borderColorLeftValue = null;
                    borderStyleTopType = EnumBorderStyle.inherit;
                    borderStyleRightType = EnumBorderStyle.inherit;
                    borderStyleBottomType = EnumBorderStyle.inherit;
                    borderStyleLeftType = EnumBorderStyle.inherit;
                    borderWidthTopType = EnumBorderWidth.inherit;
                    borderWidthRightType = EnumBorderWidth.inherit;
                    borderWidthBottomType = EnumBorderWidth.inherit;
                    borderWidthLeftType = EnumBorderWidth.inherit;
                    borderWidthTopValue = null;
                    borderWidthRightValue = null;
                    borderWidthBottomValue = null;
                    borderWidthLeftValue = null; 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten aktuální)
            //a v jednotlivých blocích se pokouším tuto deklaraci zpracovat. 
            Declaration tmpDeclaration = new DataDeclaration("border");
            tmpDeclaration.getTerms().add(t);
            
            //Vyzkouším, jestli se jedná o barvu
            tmpDeclaration.setProperty("border-color");
            if(processBorderColor(tmpDeclaration)) {
                //Jedná se o barvu. Zjistím, jestli barva už nebyla jednou zadána
                if(processedColor) {
                    //Barva už byla jednou zadáno, deklarace je chybná, rollback a konec
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    //Barva ještě nebyla zadána, pokračujeme dalším termem
                    processedColor = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-style");
            if(processBorderStyle(tmpDeclaration)) {
                if(processedStyle) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedStyle = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-width");
            if(processBorderWidth(tmpDeclaration)) {
                if(processedWidth) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedWidth = true;
                    continue;
                }
            }
            
            //Pokud program dojde sem, znamená to že term není ani color, style nebo width - ignorace celé deklarace
            rollbackTransaction(trans);
            return false;
        }
        return true;
    }
    
    private Boolean processFontFamily(Declaration d) {
        NodeData trans = beginTransaction();
        ArrayList<String>input = new ArrayList<String>();
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    fontFamilyType = EnumFontFamily.inherit;
                    fontFamilyValues.clear();
                    return true;
                }
            }
        }
        
        for(Term t : d.getTerms()) {
            if(t instanceof TermIdent) {
                String ident = ((TermIdent)t).getValue();
                fontFamilyType = EnumFontFamily.font;
                if(t.getOperator() == Term.Operator.SPACE && !input.isEmpty()) {
                    String tmp = input.get(input.size()-1);
                    tmp = tmp + " " + ident;
                    input.remove(input.size()-1);
                    input.add(tmp);
                }
                else {
                    input.add(ident);
                }
            }
            else if(t instanceof TermString) {
                String ident = ((TermString)t).getValue();
                fontFamilyType = EnumFontFamily.font;
                input.add(ident);
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        fontFamilyValues.clear();
        fontFamilyValues.addAll(input);
        return true;
    }
    
    private Boolean processFontSize(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("xx-small")) {
                    fontSizeType = EnumFontSize.xx_small;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("x-small")) {
                    fontSizeType = EnumFontSize.x_small;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("small")) {
                    fontSizeType = EnumFontSize.small;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("medium")) {
                    fontSizeType = EnumFontSize.medium;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("large")) {
                    fontSizeType = EnumFontSize.large;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("x-large")) {
                    fontSizeType = EnumFontSize.x_large;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("xx-large")) {
                    fontSizeType = EnumFontSize.xx_large;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("larger")) {
                    fontSizeType = EnumFontSize.larger;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("smaller")) {
                    fontSizeType = EnumFontSize.smaller;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    fontSizeType = EnumFontSize.inherit;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    if(num.getValue().floatValue() < 0) {
                        num.setValue(new Float(0));
                    }
                    fontSizeType = EnumFontSize.length;
                    fontSizeNumberValue = num;
                    fontSizePercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                if(percentage.getValue().floatValue() < 0) {
                    percentage.setValue(new Float(0));
                }
                fontSizeType = EnumFontSize.percentage;
                fontSizeNumberValue = null;
                fontSizePercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    */
    
    @SuppressWarnings("unused")
    private boolean processFontStyle(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(FontStyle.class, d, properties);
    }
    
    @SuppressWarnings("unused")
    private boolean processFontVariant(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(FontVariant.class, d, properties);
    }
    
    @SuppressWarnings("unused")
    private boolean processFontWeight(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	
    	// test against numeric values
    	final Integer[] test = new Integer[] {
    		100, 200, 300, 400, 500, 
    		600, 700, 800, 900};
    	
    	if(d.getTerms().size() != 1)
    		return false;
    	
    	final Term<?> term = d.getTerms().get(0);
    	
    	if(term instanceof TermIdent) {
    		return genericProperty(FontWeight.class, 
    				(TermIdent)term, properties, d.getProperty());
    	}
    	else if(term instanceof TermInteger) { 
    		
    		Integer value = ((TermInteger) term).getValue();
    		for(int i=0; i < test.length; i++ ) {
    			int result = value.compareTo(test[i]);
    			// not found if value is smaller than currently compared
    			if(result<0) break;
    			
    			// match
    			// construct according enum name
    			if(result==0) {
    				try {
    					properties.put(d.getProperty(), 
    						FontWeight.valueOf("numeric_" + value.intValue()));
    					return true;
    				}
    				catch(IllegalArgumentException e) {
    					log.warn("Not found numeric values for FontWeight: " + 
    							"numeric_ " + value.intValue());
    					return false;
    				}
    			}
    		}
    	}
    	return false;
    		
    }
    
    /*
    private Boolean processFont(Declaration d) {
        NodeData trans = beginTransaction();
        //Hodnoty jsou děděné (inherited) - Nastavení na výchozí hodnoty
        fontStyleType = EnumFontStyle.normal;
        fontFamilyType = EnumFontFamily.font;
        fontFamilyValues.clear();
        fontSizeType = EnumFontSize.medium;
        fontSizePercentValue = null;
        fontSizeNumberValue = null;
        fontVariantType = EnumFontVariant.normal;
        fontWeightType = EnumFontWeight.normal;
        lineHeightType = EnumLineHeight.normal;
        lineHeightNumberValue = null;
        lineHeightPercentValue = null;
        
        //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné deklaraci
        //objevila například 2x barva. K určení slouží následující proměnné
        boolean processedStyle = false;
        boolean processedVariant = false;
        boolean processedWeight = false;
        boolean processedFontSize = false;
        boolean processedLineHeight = false;
        int count = 0;
        
        //Sem se budou kládat všechny hodnoty na konci (pravděpodobně se bude jednat o hodnoty font-family)
        Declaration fontFamilyDeclaration = new DataDeclaration("font-family");
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    fontStyleType = EnumFontStyle.inherit;
                    fontFamilyType = EnumFontFamily.inherit;
                    fontFamilyValues.clear();
                    fontSizeType = EnumFontSize.inherit;
                    fontSizePercentValue = null;
                    fontSizeNumberValue = null;
                    fontVariantType = EnumFontVariant.inherit;
                    fontWeightType = EnumFontWeight.inherit; 
                    lineHeightType = EnumLineHeight.inherit;
                    lineHeightNumberValue = null;
                    lineHeightPercentValue = null;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("caption")) {
                if(d.getTerms().size() == 1) {
                    //neimplementováno 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("icon")) {
                if(d.getTerms().size() == 1) {
                    //neimplementováno 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("menu")) {
                if(d.getTerms().size() == 1) {
                    //neimplementováno 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("message-box")) {
                if(d.getTerms().size() == 1) {
                    //neimplementováno 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("small-caption")) {
                if(d.getTerms().size() == 1) {
                    //neimplementováno 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("status-bar")) {
                if(d.getTerms().size() == 1) {
                    //neimplementováno 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten aktuální)
            //a v jednotlivých blocích se pokouším tuto deklaraci zpracovat. 
            Declaration tmpDeclaration = new DataDeclaration("font");
            tmpDeclaration.getTerms().add(t);
            
            if(!processedFontSize && count < 3) {
                if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("normal")) {
                    //U normal není jasné o co se jedná. Ale je to výchozí hodnota, lze jí ignorovat
                    count++;
                    continue;
                }

                //Vyzkouším, jestli se jedná o barvu
                tmpDeclaration.setProperty("font-style");
                if(processFontStyle(tmpDeclaration)) {
                    //Jedná se o barvu. Zjistím, jestli barva už nebyla jednou zadána
                    if(processedStyle) {
                        //Barva už byla jednou zadáno, deklarace je chybná, rollback a konec
                        rollbackTransaction(trans);
                        return false;
                    }
                    else {
                        //Barva ještě nebyla zadána, pokračujeme dalším termem
                        processedStyle = true;
                        count++;
                        continue;
                    }
                }
                tmpDeclaration.setProperty("font-variant");
                if(processFontVariant(tmpDeclaration)) {
                    if(processedVariant) {
                        rollbackTransaction(trans);
                        return false;
                    }
                    else {
                        processedVariant = true;
                        count++;
                        continue;
                    }
                }
                tmpDeclaration.setProperty("font-weight");
                if(processFontWeight(tmpDeclaration)) {
                    if(processedWeight) {
                        rollbackTransaction(trans);
                        return false;
                    }
                    else {
                        processedWeight = true;
                        count++;
                        continue;
                    }
                }
            }
            
            //V tomto místě musí být deklarováno bezpodmínečně font-size
            if(!processedFontSize) {
                tmpDeclaration.setProperty("font-size");
                if(processFontSize(tmpDeclaration)) {
                    processedFontSize = true;
                    continue;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            if(!processedLineHeight && t.getOperator() == Term.Operator.SLASH) {
                tmpDeclaration.setProperty("line-height");
                if(processLineHeight(tmpDeclaration)) {
                    processedLineHeight = true;
                    continue;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vše co neprojde předchozím
            fontFamilyDeclaration.getTerms().add(t);
        }
        
        //Font family musí být zadáno
        if(fontFamilyDeclaration.getTerms().isEmpty()) {
            rollbackTransaction(trans);
            return false;
        }
        
        if(processFontFamily(fontFamilyDeclaration)) {
            return true;
        }
        else {
            rollbackTransaction(trans);
            return false;
        }
    }
    */
    
    /*
    private Boolean processLineHeight(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("normal")) {
                    lineHeightType = EnumLineHeight.normal;
                    lineHeightNumberValue = null;
                    lineHeightPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    lineHeightType = EnumLineHeight.inherit;
                    lineHeightNumberValue = null;
                    lineHeightPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    if(num.getValue().floatValue() < 0) {
                        num.setValue(new Float(0));
                    }
                    lineHeightType = EnumLineHeight.length;
                    lineHeightNumberValue = num;
                    lineHeightPercentValue = null;
                    return true;
                }
                if(num.isNumber()) {
                    if(num.getValue().floatValue() < 0) {
                        num.setValue(new Float(0));
                    }
                    lineHeightType = EnumLineHeight.number;
                    lineHeightNumberValue = num;
                    lineHeightPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                if(percentage.getValue().floatValue() < 0) {
                    percentage.setValue(new Float(0));
                }
                lineHeightType = EnumLineHeight.percentage;
                lineHeightNumberValue = null;
                lineHeightPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processTop(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    topType = EnumSize.auto;
                    topNumberValue = null;
                    topPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    topType = EnumSize.inherit;
                    topNumberValue = null;
                    topPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    topType = EnumSize.length;
                    topNumberValue = num;
                    topPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                topType = EnumSize.percentage;
                topNumberValue = null;
                topPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processRight(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    rightType = EnumSize.auto;
                    rightNumberValue = null;
                    rightPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    rightType = EnumSize.inherit;
                    rightNumberValue = null;
                    rightPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    rightType = EnumSize.length;
                    rightNumberValue = num;
                    rightPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                rightType = EnumSize.percentage;
                rightNumberValue = null;
                rightPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processBottom(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    bottomType = EnumSize.auto;
                    bottomNumberValue = null;
                    bottomPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    bottomType = EnumSize.inherit;
                    bottomNumberValue = null;
                    bottomPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    bottomType = EnumSize.length;
                    bottomNumberValue = num;
                    bottomPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                bottomType = EnumSize.percentage;
                bottomNumberValue = null;
                bottomPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processLeft(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    leftType = EnumSize.auto;
                    leftNumberValue = null;
                    leftPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    leftType = EnumSize.inherit;
                    leftNumberValue = null;
                    leftPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    leftType = EnumSize.length;
                    leftNumberValue = num;
                    leftPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                leftType = EnumSize.percentage;
                leftNumberValue = null;
                leftPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processWidth(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    widthType = EnumSize.auto;
                    widthNumberValue = null;
                    widthPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    widthType = EnumSize.inherit;
                    widthNumberValue = null;
                    widthPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    widthType = EnumSize.length;
                    widthNumberValue = num;
                    widthPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                widthType = EnumSize.percentage;
                widthNumberValue = null;
                widthPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processHeight(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    heightType = EnumSize.auto;
                    heightNumberValue = null;
                    heightPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    heightType = EnumSize.inherit;
                    heightNumberValue = null;
                    heightPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    heightType = EnumSize.length;
                    heightNumberValue = num;
                    heightPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                heightType = EnumSize.percentage;
                heightNumberValue = null;
                heightPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processCaptionSide(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("top")) {
                    captionSideType = EnumCaptionSide.top;
                    return true;
                }
                if(ident.equalsIgnoreCase("bottom")) {
                    captionSideType = EnumCaptionSide.bottom;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    captionSideType = EnumCaptionSide.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    */
    
    @SuppressWarnings("unused")
    private boolean processClear(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(Clear.class, d, properties);
    }

    /*
    private Boolean processClip(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    clipTopType = EnumClip.auto;
                    clipRightType = EnumClip.auto;
                    clipBottomType = EnumClip.auto;
                    clipLeftType = EnumClip.auto;
                    clipTopNumberValue = null;
                    clipRightNumberValue = null;
                    clipBottomNumberValue = null;
                    clipLeftNumberValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    clipTopType = EnumClip.inherit;
                    clipRightType = EnumClip.inherit;
                    clipBottomType = EnumClip.inherit;
                    clipLeftType = EnumClip.inherit;
                    clipTopNumberValue = null;
                    clipRightNumberValue = null;
                    clipBottomNumberValue = null;
                    clipLeftNumberValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermFunction) {
                TermFunction f = (TermFunction)d.getTerms().get(0);
                if(f.getFunctionName().equalsIgnoreCase("rect") && f.getTermsList().size() == 4) {
                    NodeData trans = beginTransaction();
                    int index = 0;
                    for(Term t : f.getTermsList()) {
                        if(t instanceof TermIdent) {
                            String ident = ((TermIdent)t).getValue();
                            if(ident.equalsIgnoreCase("auto")) {
                                switch (index) {
                                    case 0: 
                                        clipTopType = EnumClip.auto;
                                        clipTopNumberValue = null;
                                        break;
                                    case 1: 
                                        clipRightType = EnumClip.auto;
                                        clipRightNumberValue = null;
                                        break;
                                    case 2: 
                                        clipBottomType = EnumClip.auto;
                                        clipBottomNumberValue = null;
                                        break;
                                    case 3: 
                                        clipLeftType = EnumClip.auto;
                                        clipLeftNumberValue = null;
                                        break;    
                                }
                                index++;
                                continue;
                            }
                        }
                        if(t instanceof TermNumber) {
                            TermNumber num = (TermNumber)t;
                            if(num.isLength()) {
                                switch (index) {
                                    case 0: 
                                        clipTopType = EnumClip.length;
                                        clipTopNumberValue = num;
                                        break;
                                    case 1: 
                                        clipRightType = EnumClip.length;
                                        clipRightNumberValue = num;
                                        break;
                                    case 2: 
                                        clipBottomType = EnumClip.length;
                                        clipBottomNumberValue = num;
                                        break;
                                    case 3: 
                                        clipLeftType = EnumClip.length;
                                        clipLeftNumberValue = num;
                                        break;    
                                }
                                index++;
                                continue;
                            }
                        }
                        
                        rollbackTransaction(trans);
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }
        
    private Boolean processCounterIncrement(Declaration d) {
        HashMap<String, Integer> out = new HashMap<String, Integer>();
        String lastIdent = null;
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    counterIncrementType = EnumCounter.inherit;
                    counterIncrementValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
                if(d.getTerms().size() == 1) {
                    counterIncrementType = EnumCounter.none;
                    counterIncrementValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            
            if((t instanceof TermIdent)) {
                out.put(((TermIdent)t).getValue(), new Integer(1));
                lastIdent = ((TermIdent)t).getValue();
                continue;
            }
            if((t instanceof TermNumber)) {
                TermNumber num = (TermNumber)t;
                if(num.isInteger() && lastIdent != null) {
                    out.put(lastIdent, num.getValue().intValue());
                    lastIdent = null;
                    continue;
                }
            }
            return false;
        }
        
        if(!out.isEmpty()) {
            counterIncrementType = EnumCounter.table_values;
            counterIncrementValues.clear();
            counterIncrementValues.putAll(out);
            return true;
        }
        else {
            return false;
        }
    }
    
    private Boolean processCounterReset(Declaration d) {
        HashMap<String, Integer> out = new HashMap<String, Integer>();
        String lastIdent = null;
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    counterResetType = EnumCounter.inherit;
                    counterResetValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
                if(d.getTerms().size() == 1) {
                    counterResetType = EnumCounter.none;
                    counterResetValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            
            if((t instanceof TermIdent)) {
                out.put(((TermIdent)t).getValue(), new Integer(1));
                lastIdent = ((TermIdent)t).getValue();
                continue;
            }
            if((t instanceof TermNumber)) {
                TermNumber num = (TermNumber)t;
                if(num.isInteger() && lastIdent != null) {
                    out.put(lastIdent, num.getValue().intValue());
                    lastIdent = null;
                    continue;
                }
            }
            return false;
        }
        
        if(!out.isEmpty()) {
            counterResetType = EnumCounter.table_values;
            counterResetValues.clear();
            counterResetValues.putAll(out);
            return true;
        }
        else {
            return false;
        }
    }
            
    private Boolean processCursor(Declaration d) {
        boolean processedGeneric = false;
        ArrayList<TermURI> out = new ArrayList<TermURI>();
        
        int index = 0;
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    cursorType = EnumCursor.inherit;
                    cursorUri.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("auto")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.auto;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("crosshair")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.crosshair;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("default")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.prefix_default;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("pointer")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.pointer;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("move")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.move;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("e-resize")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.e_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("ne-resize")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.ne_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("nw-resize")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.nw_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("n-resize")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.n_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("se-resize")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.se_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("sw-resize")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.sw_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("s-resize")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.s_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("w-resize")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.w_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("text")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.text;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("wait")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.wait;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("help")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.help;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("progress")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.progress;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if(t instanceof TermURI) {
                index++;
                out.add((TermURI)t);
                continue;
            }
            
            return false;
        }
        
        if(processedGeneric) {
            cursorUri.clear();
            cursorUri.addAll(out);
            return true;
        }
        else {
            return false;
        }
    }
    */
    
    @SuppressWarnings("unused")
    private boolean processDirection(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(Direction.class, d, properties);
    }
    
    @SuppressWarnings("unused")
    private boolean processDisplay(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(Display.class, d, properties);
    }

    @SuppressWarnings("unused")
    private boolean processEmptyCells(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(EmptyCells.class, d, properties);
    }
    
    @SuppressWarnings("unused")
    private boolean processFloat(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(EmptyCells.class, d, properties);
    }
    
    /*
    private Boolean processListStyleImage(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("none")) {
                    listStyleImageType = EnumListStyleImage.none;
                    listStyleImageValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    listStyleImageType = EnumListStyleImage.inherit;
                    listStyleImageValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermURI) {
                TermURI uri = (TermURI)d.getTerms().get(0);
                listStyleImageType = EnumListStyleImage.uri;
                listStyleImageValue = uri;
                return true;
            }
        }
        return false;
    }
    */
    
    @SuppressWarnings("unused")
    private boolean processListStylePosition(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(ListStylePosition.class, d, properties);
    }

    @SuppressWarnings("unused")
    private boolean processListStyleType(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(ListStyleType.class, d, properties);
    }

    /*
    private Boolean processListStyle(Declaration d) {
        NodeData trans = beginTransaction();
        //Nastavení na výchozí hodnoty
        listStyleImageType = EnumListStyleImage.none;
        listStyleImageValue = null;  
        listStylePositionType = EnumListStylePosition.outside;
        listStyleTypeType = EnumListStyleType.disc;
        
        //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné deklaraci
        //objevila například 2x barva. K určení slouží následující proměnné
        boolean processedImage = false;
        boolean processedPosition = false;
        boolean processedType = false;
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    listStyleImageType = EnumListStyleImage.inherit;
                    listStyleImageValue = null;  
                    listStylePositionType = EnumListStylePosition.inherit;
                    listStyleTypeType = EnumListStyleType.inherit;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten aktuální)
            //a v jednotlivých blocích se pokouším tuto deklaraci zpracovat. 
            Declaration tmpDeclaration = new DataDeclaration("list-style");
            tmpDeclaration.getTerms().add(t);
            
            //Vyzkouším, jestli se jedná o barvu
            tmpDeclaration.setProperty("list-style-image");
            if(processListStyleImage(tmpDeclaration)) {
                //Jedná se o barvu. Zjistím, jestli barva už nebyla jednou zadána
                if(processedImage) {
                    //Barva už byla jednou zadáno, deklarace je chybná, rollback a konec
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    //Barva ještě nebyla zadána, pokračujeme dalším termem
                    processedImage = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("list-style-position");
            if(processListStylePosition(tmpDeclaration)) {
                if(processedPosition) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedPosition = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("list-style-type");
            if(processListStyleType(tmpDeclaration)) {
                if(processedType) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedType = true;
                    continue;
                }
            }
            
            //Pokud program dojde sem, znamená to že term není ani image, position nebo type - ignorace celé deklarace
            rollbackTransaction(trans);
            return false;
        }
        return true;
    }
    */
    
    @SuppressWarnings("unused")
    private boolean processMarginTop(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrLengthOrPercent(Margin.class, 
    			Margin.lenght, Margin.percentage, 
    			false, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processMarginRight(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrLengthOrPercent(Margin.class, 
    			Margin.lenght, Margin.percentage, 
    			false, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processMarginBottom(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrLengthOrPercent(Margin.class, 
    			Margin.lenght, Margin.percentage, 
    			false, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processMarginLeft(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrLengthOrPercent(Margin.class, 
    			Margin.lenght, Margin.percentage, 
    			false, d, properties, values);
    }
    
    /*
    private Boolean processMargin(Declaration d) {
        NodeData trans = beginTransaction();
        if(d.getTerms().size() == 1) {
            Declaration tmpDeclarationTop = new DataDeclaration("margin-top");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("margin-right");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationBottom = new DataDeclaration("margin-bottom");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("margin-left");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(0));
            
            if(processMarginTop(tmpDeclarationTop) && processMarginRight(tmpDeclarationRight) &&
               processMarginBottom(tmpDeclarationBottom) && processMarginLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 2) {
            Declaration tmpDeclarationTop = new DataDeclaration("margin-top");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("margin-right");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("margin-bottom");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("margin-left");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processMarginTop(tmpDeclarationTop) && processMarginRight(tmpDeclarationRight) &&
               processMarginBottom(tmpDeclarationBottom) && processMarginLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 3) {
            Declaration tmpDeclarationTop = new DataDeclaration("margin-top");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("margin-right");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("margin-bottom");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("margin-left");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processMarginTop(tmpDeclarationTop) && processMarginRight(tmpDeclarationRight) &&
               processMarginBottom(tmpDeclarationBottom) && processMarginLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 4) {
            Declaration tmpDeclarationTop = new DataDeclaration("margin-top");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("margin-right");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("margin-bottom");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("margin-left");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(3));
            
            if(processMarginTop(tmpDeclarationTop) && processMarginRight(tmpDeclarationRight) &&
               processMarginBottom(tmpDeclarationBottom) && processMarginLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        return new Boolean(false);
    }
    */
    @SuppressWarnings("unused")
    private boolean processMaxHeight(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrLengthOrPercent(MaxHeight.class, 
    			MaxHeight.lenght, MaxHeight.percentage, true, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processMaxWidth(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrLengthOrPercent(MaxWidth.class, 
    			MaxWidth.lenght, MaxWidth.percentage, true, d, properties, values);
    }

    @SuppressWarnings("unused")
    private boolean processMinHeight(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrLengthOrPercent(MinHeight.class, 
    			MinHeight.lenght, MinHeight.percentage, true, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processMinWidth(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrLengthOrPercent(MinWidth.class, 
    			MinWidth.lenght, MinWidth.percentage, true, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processOrphans(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrInteger(Orphans.class, 
    			Orphans.integer, true, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processOutlineColor(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrColor(OutlineColor.class, OutlineColor.color, d, properties, values);
    }

    @SuppressWarnings("unused")
    private boolean processOutlineStyle(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(OutlineStyle.class, d, properties);
    }   
    
    @SuppressWarnings("unused")
    private boolean processOutlineWidth(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrLength(OutlineWidth.class, 
    			OutlineWidth.length, false, d, properties, values);
    }
    
    /*
    private Boolean processOutline(Declaration d) {
        NodeData trans = beginTransaction();
        //Nastavení na výchozí hodnoty
        outlineColorType = null;
        outlineColorValue = null;
        outlineStyleType = null;
        outlineWidthType = null;
        outlineWidthValue = null;
        
        //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné deklaraci
        //objevila například 2x barva. K určení slouží následující proměnné
        boolean processedColor = false;
        boolean processedStyle = false;
        boolean processedWidth = false;
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    outlineColorType = EnumColorInvert.inherit;
                    outlineColorValue = null;
                    outlineStyleType = EnumBorderStyle.inherit;
                    outlineWidthType = EnumBorderWidth.inherit;
                    outlineWidthValue = null;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten aktuální)
            //a v jednotlivých blocích se pokouším tuto deklaraci zpracovat. 
            Declaration tmpDeclaration = new DataDeclaration("outline");
            tmpDeclaration.getTerms().add(t);
            
            //Vyzkouším, jestli se jedná o barvu
            tmpDeclaration.setProperty("outline-color");
            if(processOutlineColor(tmpDeclaration)) {
                //Jedná se o barvu. Zjistím, jestli barva už nebyla jednou zadána
                if(processedColor) {
                    //Barva už byla jednou zadáno, deklarace je chybná, rollback a konec
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    //Barva ještě nebyla zadána, pokračujeme dalším termem
                    processedColor = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("outline-style");
            if(processOutlineStyle(tmpDeclaration)) {
                if(processedStyle) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedStyle = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("outline-width");
            if(processOutlineWidth(tmpDeclaration)) {
                if(processedWidth) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedWidth = true;
                    continue;
                }
            }
            
            //Pokud program dojde sem, znamená to že term není ani image, position nebo type - ignorace celé deklarace
            rollbackTransaction(trans);
            return false;
        }
        return true;
    }
    */
    
    @SuppressWarnings("unused")
    private boolean processOverflow(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(Overflow.class, d, properties);
    }
    
    @SuppressWarnings("unused")
    private boolean processPaddingTop(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrLengthOrPercent(Padding.class,
    			Padding.length, Padding.percentage,
    			true, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processPaddingRight(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrLengthOrPercent(Padding.class,
    			Padding.length, Padding.percentage,
    			true, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processPaddingBottom(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrLengthOrPercent(Padding.class,
    			Padding.length, Padding.percentage,
    			true, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processPaddingLeft(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdentOrLengthOrPercent(Padding.class,
    			Padding.length, Padding.percentage,
    			true, d, properties, values);
    }

    /*
    private Boolean processPadding(Declaration d) {
        NodeData trans = beginTransaction();
        if(d.getTerms().size() == 1) {
            Declaration tmpDeclarationTop = new DataDeclaration("padding-top");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("padding-right");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationBottom = new DataDeclaration("padding-bottom");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("padding-left");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(0));
            
            if(processPaddingTop(tmpDeclarationTop) && processPaddingRight(tmpDeclarationRight) &&
               processPaddingBottom(tmpDeclarationBottom) && processPaddingLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 2) {
            Declaration tmpDeclarationTop = new DataDeclaration("padding-top");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("padding-right");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("padding-bottom");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("padding-left");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processPaddingTop(tmpDeclarationTop) && processPaddingRight(tmpDeclarationRight) &&
               processPaddingBottom(tmpDeclarationBottom) && processPaddingLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 3) {
            Declaration tmpDeclarationTop = new DataDeclaration("padding-top");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("padding-right");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("padding-bottom");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("padding-left");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processPaddingTop(tmpDeclarationTop) && processPaddingRight(tmpDeclarationRight) &&
               processPaddingBottom(tmpDeclarationBottom) && processPaddingLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 4) {
            Declaration tmpDeclarationTop = new DataDeclaration("padding-top");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("padding-right");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("padding-bottom");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("padding-left");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(3));
            
            if(processPaddingTop(tmpDeclarationTop) && processPaddingRight(tmpDeclarationRight) &&
               processPaddingBottom(tmpDeclarationBottom) && processPaddingLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        return new Boolean(false);
    }
    */
    
    @SuppressWarnings("unused")
    private boolean processPageBreakAfter(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(PageBreak.class, d, properties);
    }
        
    @SuppressWarnings("unused")
    private boolean processPageBreakBefore(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(PageBreak.class, d, properties);
    }
    
    @SuppressWarnings("unused")
    private boolean processPageBreakInside(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(PageBreakInside.class, d, properties);
    }
    
    @SuppressWarnings("unused")
    private boolean processPosition(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(Position.class, d, properties);
    }

    /*
    private Boolean processQuotes(Declaration d) {
        ArrayList<String> out = new ArrayList<String>();
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    quotesType = EnumQuotes.inherit;
                    quotesValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
                if(d.getTerms().size() == 1) {
                    quotesType = EnumQuotes.none;
                    quotesValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermString)) {
                out.add(((TermString)t).getValue());
                continue;
            }
            return false;
        }
        
        if(!out.isEmpty() && out.size() % 2 == 0) {
            quotesType = EnumQuotes.list_values;
            quotesValues.clear();
            quotesValues.addAll(out);
            return true;
        }
        else {
            return false;
        }
    }
    */
    @SuppressWarnings("unused")
    private boolean processTableLayout(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(TableLayout.class, d, properties);
    }
    
    @SuppressWarnings("unused")
    private boolean processTextAlign(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(TextAlign.class, d, properties);
    }

    /*
    private Boolean processTextDecoration(Declaration d) {
        NodeData trans = beginTransaction();
        //Nastavení na výchozí hodnoty
        textDecorationType = null;
        textDecorationUnderline = null;
        textDecorationOverline = null;
        textDecorationLineThrough = null;
        textDecorationBlink = null;
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    textDecorationType = EnumTextDecoration.inherit;
                    textDecorationUnderline = null;
                    textDecorationOverline = null;
                    textDecorationLineThrough = null;
                    textDecorationBlink = null;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
                if(d.getTerms().size() == 1) {
                    textDecorationType = EnumTextDecoration.set;
                    textDecorationUnderline = false;
                    textDecorationOverline = false;
                    textDecorationLineThrough = false;
                    textDecorationBlink = false;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }

            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("underline")) {
                textDecorationType = EnumTextDecoration.set;
                if(textDecorationBlink == null) { textDecorationBlink = false; }
                if(textDecorationLineThrough == null) { textDecorationLineThrough = false; }
                if(textDecorationOverline == null) { textDecorationOverline = false; }
               
                if(textDecorationUnderline != null && textDecorationUnderline) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    textDecorationUnderline = true;
                    continue;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("overline")) {
                textDecorationType = EnumTextDecoration.set;
                if(textDecorationBlink == null) { textDecorationBlink = false; }
                if(textDecorationLineThrough == null) { textDecorationLineThrough = false; }
                if(textDecorationUnderline == null) { textDecorationUnderline = false; }

                if(textDecorationOverline != null && textDecorationOverline) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    textDecorationOverline = true;
                    continue;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("line-through")) {
                textDecorationType = EnumTextDecoration.set;
                if(textDecorationBlink == null) { textDecorationBlink = false; }
                if(textDecorationOverline == null) { textDecorationOverline = false; }
                if(textDecorationUnderline == null) { textDecorationUnderline = false; }

                if(textDecorationLineThrough != null && textDecorationLineThrough) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    textDecorationLineThrough = true;
                    continue;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("blink")) {
                textDecorationType = EnumTextDecoration.set;
                if(textDecorationLineThrough == null) { textDecorationLineThrough = false; }
                if(textDecorationOverline == null) { textDecorationOverline = false; }
                if(textDecorationUnderline == null) { textDecorationUnderline = false; }

                if(textDecorationBlink != null && textDecorationBlink) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    textDecorationBlink = true;
                    continue;
                }
            }
            
            //Pokud program dojde sem, znamená to že term není ani image, position nebo type - ignorace celé deklarace
            rollbackTransaction(trans);
            return false;
        }
        return true;
    }
    */
    
    @SuppressWarnings("unused")
    private boolean processTextIdent(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
		return genericTermIdentOrLengthOrPercent(TextIndent.class,
				TextIndent.length, TextIndent.percentage, 
				false, d, properties, values);		
    }
    
    @SuppressWarnings("unused")
    private boolean processTextTransform(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(TextTransform.class, d, properties);
    }
    
    @SuppressWarnings("unused")
    private boolean processUnicodeBidi(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
		return genericTermIdent(UnicodeBidi.class, d, properties);
	}
    
    @SuppressWarnings("unused")
    private boolean processVerticalAlign(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
		return genericTermIdentOrLengthOrPercent(VerticalAlign.class,
				VerticalAlign.length, VerticalAlign.percentage, 
				false, d, properties, values);		
    }
    
    
    @SuppressWarnings("unused")
    private boolean processVisibility(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
		return genericTermIdent(Visibility.class, d, properties);
	}
    
    @SuppressWarnings("unused")
    private boolean processWhiteSpace(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
    	return genericTermIdent(WhiteSpace.class, d, properties);
    }
    
    @SuppressWarnings("unused")
    private boolean processWidows(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
		return genericTermIdentOrInteger(Widows.class, Widows.integer, true, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processWordSpacing(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
		return genericTermIdentOrLength(WordSpacing.class, WordSpacing.length, false, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processZIndex(Declaration d, Map<String,CSSProperty> properties, 
			Map<String,Term<?>> values, Map<String, List<Term<?>>> listValues) {
		return genericTermIdentOrInteger(ZIndex.class, ZIndex.integer, false, d, properties, values);
    }
    
    /*
    private Boolean processContent(Declaration d) {

        ArrayList<Term<?>> out = new ArrayList<Term<?>>();
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    contentType = EnumContent.inherit;
                    contentValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("normal")) {
                if(d.getTerms().size() == 1) {
                    contentType = EnumContent.normal;
                    contentValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
                if(d.getTerms().size() == 1) {
                    contentType = EnumContent.none;
                    contentValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("open-quote")) {
                out.add(t);
                continue;
            }
            
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("close-quote")) {
                out.add(t);
                continue;
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("no-open-quote")) {
                out.add(t);
                continue;
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("no-close-quote")) {
                out.add(t);
                continue;
            }
            if(t instanceof TermString) {
                out.add(t);
                continue;
            }
            if(t instanceof TermURI) {
                out.add(t);
                continue;
            }
            if((t instanceof TermFunction) && ((TermFunction)t).getFunctionName().equalsIgnoreCase("counter")) {
                out.add(t);
                continue;
            }
            if((t instanceof TermFunction) && ((TermFunction)t).getFunctionName().equalsIgnoreCase("counters")) {
                out.add(t);
                continue;
            }
            if((t instanceof TermFunction) && ((TermFunction)t).getFunctionName().equalsIgnoreCase("attr")) {
                out.add(t);
                continue;
            }
            return false;
        }
        
        if(!out.isEmpty()) {
            contentType = EnumContent.list_values;
            contentValues.clear();
            contentValues.addAll(out);
            return true;
        }
        else {
            return false;
        }
    }
	*/
}
