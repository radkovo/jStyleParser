package cz.vutbr.web.domassign;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.CSSProperty.BackgroundSize;
import cz.vutbr.web.css.CSSProperty.BorderRadius;
import cz.vutbr.web.css.CSSProperty.GenericCSSPropertyProxy;
import cz.vutbr.web.css.CSSProperty.Opacity;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.SupportedCSS;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermInteger;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermNumber;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.css.TermString;
import cz.vutbr.web.css.TermURI;
import cz.vutbr.web.css.CSSProperty.BackgroundAttachment;
import cz.vutbr.web.css.CSSProperty.BackgroundColor;
import cz.vutbr.web.css.CSSProperty.BackgroundImage;
import cz.vutbr.web.css.CSSProperty.BackgroundPosition;
import cz.vutbr.web.css.CSSProperty.BackgroundRepeat;
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
import cz.vutbr.web.css.CSSProperty.CounterSet;
import cz.vutbr.web.css.CSSProperty.Cursor;
import cz.vutbr.web.css.CSSProperty.Direction;
import cz.vutbr.web.css.CSSProperty.Display;
import cz.vutbr.web.css.CSSProperty.EmptyCells;
import cz.vutbr.web.css.CSSProperty.Font;
import cz.vutbr.web.css.CSSProperty.FontFamily;
import cz.vutbr.web.css.CSSProperty.FontSize;
import cz.vutbr.web.css.CSSProperty.FontStyle;
import cz.vutbr.web.css.CSSProperty.FontVariant;
import cz.vutbr.web.css.CSSProperty.FontWeight;
import cz.vutbr.web.css.CSSProperty.Height;
import cz.vutbr.web.css.CSSProperty.Left;
import cz.vutbr.web.css.CSSProperty.LetterSpacing;
import cz.vutbr.web.css.CSSProperty.LineHeight;
import cz.vutbr.web.css.CSSProperty.ListStyleImage;
import cz.vutbr.web.css.CSSProperty.ListStylePosition;
import cz.vutbr.web.css.CSSProperty.ListStyleType;
import cz.vutbr.web.css.CSSProperty.Margin;
import cz.vutbr.web.css.CSSProperty.MaxHeight;
import cz.vutbr.web.css.CSSProperty.MaxWidth;
import cz.vutbr.web.css.CSSProperty.MinHeight;
import cz.vutbr.web.css.CSSProperty.MinWidth;
import cz.vutbr.web.css.CSSProperty.Orphans;
import cz.vutbr.web.css.CSSProperty.OutlineColor;
import cz.vutbr.web.css.CSSProperty.OutlineStyle;
import cz.vutbr.web.css.CSSProperty.OutlineWidth;
import cz.vutbr.web.css.CSSProperty.Overflow;
import cz.vutbr.web.css.CSSProperty.Padding;
import cz.vutbr.web.css.CSSProperty.PageBreak;
import cz.vutbr.web.css.CSSProperty.PageBreakInside;
import cz.vutbr.web.css.CSSProperty.Position;
import cz.vutbr.web.css.CSSProperty.Quotes;
import cz.vutbr.web.css.CSSProperty.Right;
import cz.vutbr.web.css.CSSProperty.TableLayout;
import cz.vutbr.web.css.CSSProperty.TextAlign;
import cz.vutbr.web.css.CSSProperty.TextDecoration;
import cz.vutbr.web.css.CSSProperty.TextIndent;
import cz.vutbr.web.css.CSSProperty.TextTransform;
import cz.vutbr.web.css.CSSProperty.Top;
import cz.vutbr.web.css.CSSProperty.UnicodeBidi;
import cz.vutbr.web.css.CSSProperty.VerticalAlign;
import cz.vutbr.web.css.CSSProperty.Visibility;
import cz.vutbr.web.css.CSSProperty.WhiteSpace;
import cz.vutbr.web.css.CSSProperty.Widows;
import cz.vutbr.web.css.CSSProperty.Width;
import cz.vutbr.web.css.CSSProperty.WordSpacing;
import cz.vutbr.web.css.CSSProperty.ZIndex;
import cz.vutbr.web.css.Term.Operator;

/**
 * Contains methods to transform declaration into values applicable to NodeData.
 * Uses defaults defined by CSSFactory
 * 
 * @author kapy
 * 
 */
public class DeclarationTransformer {

	protected static Logger log = LoggerFactory
			.getLogger(DeclarationTransformer.class);

	/**
	 * Inherit acceptance flags
	 */
	protected static final boolean AVOID_INH = true;
	protected static final boolean ALLOW_INH = false;

	/**
	 * Cache of parsing methods
	 */
	protected Map<String, Method> methods;

	/**
	 * Singleton instance
	 */
	private static final DeclarationTransformer instance;

	protected static final TermFactory tf = CSSFactory.getTermFactory();
	protected final SupportedCSS css;

	static {
		instance = new DeclarationTransformer();
	}

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
	 * Core function. Parses CSS declaration into structure applicable to
	 * DataNodeImpl
	 * 
	 * @param d
	 *            Declaration
	 * @param properties
	 *            Wrap of parsed declaration's properties
	 * @param values
	 *            Wrap of parsed declaration's value
	 * @return <code>true</code> in case of success, <code>false</code>
	 *         otherwise
	 */
	public boolean parseDeclaration(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		String propertyName = d.getProperty().toLowerCase();

		// no such declaration is supported
		if (!css.isSupportedCSSProperty(propertyName))
			return false;

		try {
			Method m = methods.get(propertyName);
			if (m != null) {
				boolean result = (Boolean) m
						.invoke(this, d, properties, values);
				log.debug("Parsing /{}/ {}", result, d);
				return result;
			}
			else
			{
			    boolean result = processAdditionalCSSGenericProperty(d, properties, values);
			    log.debug("Parsing with proxy /{}/ {}", result, d);
			    return result; 
			}			
		} catch (IllegalArgumentException e) {
			log.warn("Illegal argument", e);
		} catch (IllegalAccessException e) {
			log.warn("Illegal access", e);
		} catch (InvocationTargetException e) {
			log.warn("Invocation target", e);
			log.warn("Invotation target cause", e.getCause());
		}

		return false;
	}

	public DeclarationTransformer() {
		this(CSSFactory.getSupportedCSS());
	}

	public DeclarationTransformer(SupportedCSS css) {
		this.css = css;
		this.methods = parsingMethods();
	}

	protected Map<String, Method> parsingMethods() {

		Map<String, Method> map = new HashMap<String, Method>(css
				.getTotalProperties(), 1.0f);

		for (String key : css.getDefinedPropertyNames()) {
			try {
				Method m = DeclarationTransformer.class.getDeclaredMethod(
						DeclarationTransformer.camelCase("process-" + key),
						Declaration.class, Map.class, Map.class);
				map.put(key, m);
			} catch (Exception e) {
				log.debug("Unable to find method for property {}.", key);
			}
		}
		log.debug("Totally found {} parsing methods", map.size());
		return map;
	}

	/****************************************************************
	 * GENERIC METHODS *
	 ****************************************************************/

	/**
	 * Converts TermIdent into CSSProperty using intersection set.
	 * CSSProperty.Translator is used.
	 * 
	 * @param <T>
	 *            Subclass of CSSProperty to be returned
	 * @param type
	 *            Class of property to be used to retrive value
	 * @param intersection
	 *            Intersection set or <code>null</code> if no intersection is
	 *            used
	 * @param term
	 *            TermIdent to be transfered to property
	 * @return CSSProperty of type <T> or <code>null</code>
	 */
	public <T extends CSSProperty> T genericPropertyRaw(Class<T> type,
			Set<T> intersection, TermIdent term) {

		try {
			String name = term.getValue().replace("-", "_").toUpperCase();
			T property = CSSProperty.Translator.valueOf(type, name);
			if (intersection != null && intersection.contains(property))
				return property;
			return property;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Converts TermIdent into value of enum of given class and stores it into
	 * properties map under key property
	 * 
	 * @param <T>
	 *            Enum & CSSProperty limitation
	 * @param type
	 *            Type of enum which instance is retrieved
	 * @param term
	 *            Term with value to be converted
	 * @param avoidInherit
	 *            If <code>true</code> inherit value is not considered valid
	 * @param properties
	 *            Properties map where to store value
	 * @param propertyName
	 *            Name under which property is stored in map
	 * @return <code>true</code> in case of success, <code>false</code>
	 *         otherwise
	 */
	protected <T extends CSSProperty> boolean genericProperty(Class<T> type,
			TermIdent term, boolean avoidInherit,
			Map<String, CSSProperty> properties, String propertyName) {

		T property = genericPropertyRaw(type, null, term);
		if (property == null || (avoidInherit && property.equalsInherit()))
			return false;

		properties.put(propertyName, property);
		return true;
	}

	/**
	 * Converts TermIdent into value of CSSProperty for given class
	 * 
	 */
	protected <T extends CSSProperty> boolean genericTermIdent(Class<T> type,
			Term<?> term, boolean avoidInherit, String propertyName,
			Map<String, CSSProperty> properties) {

		if (term instanceof TermIdent) {
			return genericProperty(type, (TermIdent) term, avoidInherit,
					properties, propertyName);
		}
		return false;
	}

	/**
	 * Converts term into Color and stored values and types in maps
	 * 
	 * @param <T>
	 *            CSSProperty
	 * @param term
	 *            Term to be parsed
	 * @param propertyName
	 *            How to store colorIdentificiton
	 * @param colorIdentification
	 *            What to store under propertyName
	 * @param properties
	 *            Map to store property types
	 * @param values
	 *            Map to store property values
	 * @return <code>true</code> in case of success, <code>false</code>
	 *         otherwise
	 */
	protected <T extends CSSProperty> boolean genericTermColor(Term<?> term,
			String propertyName, T colorIdentification,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (term instanceof TermColor) {
			properties.put(propertyName, colorIdentification);
			values.put(propertyName, term);
			return true;
		}

		return false;

	}

	/**
	 * Converts term into TermLength and stores values and types in maps
	 * 
	 * @param <T>
	 *            CSSProperty
	 * @param term
	 *            Term to be parsed
	 * @param propertyName
	 *            How to store colorIdentificiton
	 * @param lengthIdentification
	 *            What to store under propertyName
	 * @param properties
	 *            Map to store property types
	 * @param values
	 *            Map to store property values
	 * @return <code>true</code> in case of success, <code>false</code>
	 *         otherwise
	 */
	protected <T extends CSSProperty> boolean genericTermLength(Term<?> term,
			String propertyName, T lengthIdentification, boolean sanify,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

        if (term instanceof TermInteger  && ((TermInteger) term).getUnit().equals(TermNumber.Unit.none)) {
            if (CSSFactory.getImplyPixelLength() || ((TermInteger) term).getValue() == 0) { //0 is always allowed with no units
                // convert to length with units of px
                TermLength tl = tf.createLength(((TermInteger) term).getValue(), TermNumber.Unit.px);
                return genericTerm(TermLength.class, tl, propertyName, lengthIdentification, sanify, properties, values);
            } else {
                return false;
            }
        }
        else if (term instanceof TermLength) { 
            return genericTerm(TermLength.class, term, propertyName, lengthIdentification, sanify, properties, values); 
        }

        return false;

	}

	/**
	 * Check whether given declaration contains one term of given type. It is
	 * able to check even whether is above zero for numeric values
	 * 
	 * @param <T>
	 *            Class of CSSProperty to be used for result
	 * @param termType
	 *            Supposed type of term
	 * @param term
	 *            Term of which is supposed to be of type <code>termType</code>,
	 *            that is input data
	 * @param propertyName
	 *            Name under which property's value and type is stored in maps
	 * @param typeIdentification
	 *            How this type of term is described in type T
	 * @param sanify
	 *            Check if value is positive
	 * @param properties
	 *            Where to store property type
	 * @param values
	 *            Where to store property value
	 * @return <code>true</code> if succeeded in recognition, <code>false</code>
	 *         otherwise
	 */
	protected <T extends CSSProperty> boolean genericTerm(
			Class<? extends Term<?>> termType, Term<?> term,
			String propertyName, T typeIdentification, boolean sanify,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		// check type
		if (termType.isInstance(term)) {
			// sanity check
			if (sanify) {
				// check for integer
				if (term instanceof TermInteger) {
					final Integer zero = new Integer(0);
					if (zero.compareTo((Integer) ((TermInteger)term).getIntValue()) > 0) {
						// return false is also possibility
						// but we will change to zero
						((TermInteger) term).setValue(zero);
					}
				}
				// check for float
				else if (term instanceof TermNumber) {
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
	 *            Type of CSSProperty
	 * @param type
	 *            Class of CSSProperty to be stored
	 * @param d
	 *            Declaration to be parsed
	 * @param properties
	 *            Properties map where to store enum
	 * @return <code>true</code> in case of success, <code>false</code>
	 *         elsewhere
	 */
	protected <T extends CSSProperty> boolean genericOneIdent(Class<T> type,
			Declaration d, Map<String, CSSProperty> properties) {

		if (d.size() != 1)
			return false;

		return genericTermIdent(type, d.get(0), ALLOW_INH, d.getProperty(),
				properties);
	}

	/**
	 * Processes declaration which is supposed to contain one identification
	 * term or one TermColor
	 * 
	 * @param <T>
	 *            Type of CSSProperty
	 * @param type
	 *            Class of enum to be stored
	 * @param colorIdentification
	 *            Instance of CSSProperty stored into properties to indicate
	 *            that additional value of type TermColor is stored in values
	 * @param d
	 *            Declaration to be parsed
	 * @param properties
	 *            Properties map where to store enum
	 * @param values
	 * @return <code>true</code> in case of success, <code>false</code>
	 *         elsewhere
	 */
	protected <T extends CSSProperty> boolean genericOneIdentOrColor(
			Class<T> type, T colorIdentification, Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (d.size() != 1)
			return false;

		return genericTermIdent(type, d.get(0), ALLOW_INH, d.getProperty(),
				properties)
				|| genericTermColor(d.get(0), d.getProperty(),
						colorIdentification, properties, values);
	}

	protected <T extends CSSProperty> boolean genericOneIdentOrInteger(
			Class<T> type, T integerIdentification, boolean sanify,
			Declaration d, Map<String, CSSProperty> properties,
			Map<String, Term<?>> values) {

		if (d.size() != 1)
			return false;

		return genericTermIdent(type, d.get(0), ALLOW_INH, d.getProperty(),
				properties)
				|| genericTerm(TermInteger.class, d.get(0), d.getProperty(),
						integerIdentification, sanify, properties, values);
	}

    protected <T extends CSSProperty> boolean genericOneIdentOrIntegerOrNumber(
            Class<T> type, T integerIdentification, T numberIdentification, boolean sanify,
            Declaration d, Map<String, CSSProperty> properties,
            Map<String, Term<?>> values) {

        if (d.size() != 1)
            return false;

        return genericTermIdent(type, d.get(0), ALLOW_INH, d.getProperty(), properties)
                || genericTerm(TermInteger.class, d.get(0), d.getProperty(),
                        integerIdentification, sanify, properties, values)
                || genericTerm(TermNumber.class, d.get(0), d.getProperty(),
                        numberIdentification, sanify, properties, values);
    }
    
	protected <T extends CSSProperty> boolean genericOneIdentOrLength(
			Class<T> type, T lengthIdentification, boolean sanify,
			Declaration d, Map<String, CSSProperty> properties,
			Map<String, Term<?>> values) {

		if (d.size() != 1)
			return false;

		return genericTermIdent(type, d.get(0), ALLOW_INH, d.getProperty(),
				properties)
				|| genericTermLength(d.get(0), d.getProperty(),
						lengthIdentification, sanify, properties, values);
	}

	protected <T extends Enum<T> & CSSProperty> boolean genericOneIdentOrLengthOrPercent(
			Class<T> type, T lengthIdentification, T percentIdentification,
			boolean sanify, Declaration d, Map<String, CSSProperty> properties,
			Map<String, Term<?>> values) {

		if (d.size() != 1)
			return false;

		return genericTermIdent(type, d.get(0), ALLOW_INH, d.getProperty(),
				properties)
				|| genericTermLength(d.get(0), d.getProperty(),
						lengthIdentification, sanify, properties, values)
				|| genericTerm(TermPercent.class, d.get(0), d.getProperty(),
						percentIdentification, sanify, properties, values);
	}

    protected <T extends Enum<T> & CSSProperty> boolean genericTwoIdentsOrLengthsOrPercents(
            Class<T> type, T listIdentification,
            boolean sanify, Declaration d, Map<String, CSSProperty> properties,
            Map<String, Term<?>> values) {

        if (d.size() == 1) {
            Term<?> term = d.get(0);
            String propertyName = d.getProperty();
            // is it identifier or length ?
            if (genericTermIdent(type, term, ALLOW_INH, propertyName, properties)
                || genericTermLength(term, propertyName,
                        listIdentification, sanify, properties, values)
                || genericTerm(TermPercent.class, term, propertyName,
                        listIdentification, sanify, properties, values)) {
                // one term with length was inserted, double it
                if (properties.get(propertyName) == listIdentification) {
                    TermList terms = tf.createList(2);
                    terms.add(term);
                    terms.add(term);
                    values.put(propertyName, terms);
                }
                return true;
            }
            else
                return false;
        }
        // two numerical values
        else if (d.size() == 2) {
            Term<?> term1 = d.get(0);
            Term<?> term2 = d.get(1);
            String propertyName = d.getProperty();
            // two lengths ?
            if ((genericTermLength(term1, propertyName,
                            listIdentification, sanify, properties, values)
                    || genericTerm(TermPercent.class, term1, propertyName,
                            listIdentification, sanify, properties, values))
                 && (genericTermLength(term2, propertyName,
                            listIdentification, sanify, properties, values)
                    || genericTerm(TermPercent.class, term2, propertyName,
                            listIdentification, sanify, properties, values))) {
                TermList terms = tf.createList(2);
                terms.add(term1);
                terms.add(term2);
                values.put(propertyName, terms);
                return true;
            }
            else
                return false;
        }
        else
            return false;
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
	private boolean processBackground(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		Variator background = new BackgroundVariator();
		background.assignTermsFromDeclaration(d);
		background.assignDefaults(properties, values);
		return background.vary(properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBackgroundAttachment(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator background = new BackgroundVariator();
		return background.tryOneTermVariant(BackgroundVariator.ATTACHMENT, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBackgroundColor(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator background = new BackgroundVariator();
		return background.tryOneTermVariant(BackgroundVariator.COLOR, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBackgroundImage(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator background = new BackgroundVariator();
		return background.tryOneTermVariant(BackgroundVariator.IMAGE, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBackgroundRepeat(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator background = new BackgroundVariator();
		return background.tryOneTermVariant(BackgroundVariator.REPEAT, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBackgroundPosition(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator background = new BackgroundVariator();
		return background.tryMultiTermVariant(BackgroundVariator.POSITION,
				properties, values, d.toArray(new Term<?>[0]));
	}

    @SuppressWarnings("unused")
    private boolean processBackgroundSize(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        final Variator background = new BackgroundVariator();
        return background.tryMultiTermVariant(BackgroundVariator.SIZE,
                properties, values, d.toArray(new Term<?>[0]));
    }

	@SuppressWarnings("unused")
	private boolean processBorder(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		Variator border = new BorderVariator();
		border.assignTermsFromDeclaration(d);
		border.assignDefaults(properties, values);
		return border.vary(properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderCollapse(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdent(BorderCollapse.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processBorderTopColor(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator borderSide = new BorderSideVariator("top");
		return borderSide.tryOneTermVariant(BorderSideVariator.COLOR, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderRightColor(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator borderSide = new BorderSideVariator("right");
		return borderSide.tryOneTermVariant(BorderSideVariator.COLOR, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderBottomColor(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator borderSide = new BorderSideVariator("bottom");
		return borderSide.tryOneTermVariant(BorderSideVariator.COLOR, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderLeftColor(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator borderSide = new BorderSideVariator("left");
		return borderSide.tryOneTermVariant(BorderSideVariator.COLOR, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderTopStyle(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator borderSide = new BorderSideVariator("top");
		return borderSide.tryOneTermVariant(BorderSideVariator.STYLE, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderRightStyle(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator borderSide = new BorderSideVariator("right");
		return borderSide.tryOneTermVariant(BorderSideVariator.STYLE, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderBottomStyle(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator borderSide = new BorderSideVariator("bottom");
		return borderSide.tryOneTermVariant(BorderSideVariator.STYLE, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderLeftStyle(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator borderSide = new BorderSideVariator("left");
		return borderSide.tryOneTermVariant(BorderSideVariator.STYLE, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderSpacing(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (d.size() == 1) {
			Term<?> term = d.get(0);
			String propertyName = d.getProperty();
			// is it identifier or length ?
			if (genericTermIdent(BorderSpacing.class, term, ALLOW_INH,
					propertyName, properties)
					|| genericTermLength(term, propertyName,
							BorderSpacing.list_values, true, properties, values)) {
				// one term with length was inserted, double it
				if (properties.get(propertyName) == BorderSpacing.list_values) {
					TermList terms = tf.createList(2);
					terms.add(term);
					terms.add(term);
					values.put(propertyName, terms);
				}
				return true;
			}
		}
		// two numerical values
		else if (d.size() == 2) {
			Term<?> term1 = d.get(0);
			Term<?> term2 = d.get(1);
			String propertyName = d.getProperty();
			// two lengths ?
			if (genericTermLength(term1, propertyName,
					BorderSpacing.list_values, true, properties, values)
					&& genericTermLength(term2, propertyName,
							BorderSpacing.list_values, true, properties, values)) {
				TermList terms = tf.createList(2);
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
		Repeater borderColor = new BorderColorRepeater();
		return borderColor.repeatOverFourTermDeclaration(d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderStyle(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		Repeater borderStyle = new BorderStyleRepeater();
		return borderStyle.repeatOverFourTermDeclaration(d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderTopWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator borderSide = new BorderSideVariator("top");
		return borderSide.tryOneTermVariant(BorderSideVariator.WIDTH, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderRightWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator borderSide = new BorderSideVariator("right");
		return borderSide.tryOneTermVariant(BorderSideVariator.WIDTH, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderBottomWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator borderSide = new BorderSideVariator("bottom");
		return borderSide.tryOneTermVariant(BorderSideVariator.WIDTH, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderLeftWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator borderSide = new BorderSideVariator("left");
		return borderSide.tryOneTermVariant(BorderSideVariator.WIDTH, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		Repeater borderWidth = new BorderWidthRepeater();
		return borderWidth.repeatOverFourTermDeclaration(d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderTop(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		Variator borderSide = new BorderSideVariator("top");
		borderSide.assignTermsFromDeclaration(d);
		borderSide.assignDefaults(properties, values);
		return borderSide.vary(properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderRight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		Variator borderSide = new BorderSideVariator("right");
		borderSide.assignTermsFromDeclaration(d);
        borderSide.assignDefaults(properties, values);
		return borderSide.vary(properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderBottom(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		Variator borderSide = new BorderSideVariator("bottom");
		borderSide.assignTermsFromDeclaration(d);
        borderSide.assignDefaults(properties, values);
		return borderSide.vary(properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBorderLeft(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		Variator borderSide = new BorderSideVariator("left");
		borderSide.assignTermsFromDeclaration(d);
        borderSide.assignDefaults(properties, values);
		return borderSide.vary(properties, values);
	}

    @SuppressWarnings("unused")
    private boolean processBorderTopLeftRadius(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return genericTwoIdentsOrLengthsOrPercents(BorderRadius.class,
                BorderRadius.list_values, false, d, properties, values);
    }
	
    @SuppressWarnings("unused")
    private boolean processBorderTopRightRadius(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return genericTwoIdentsOrLengthsOrPercents(BorderRadius.class,
                BorderRadius.list_values, false, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processBorderBottomRightRadius(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return genericTwoIdentsOrLengthsOrPercents(BorderRadius.class,
                BorderRadius.list_values, false, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processBorderBottomLeftRadius(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return genericTwoIdentsOrLengthsOrPercents(BorderRadius.class,
                BorderRadius.list_values, false, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processBorderRadius(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        BorderRadiusRepeater radius = new BorderRadiusRepeater();
        return radius.repeatOverMultiTermDeclaration(d, properties, values);
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
	 * borderWidthBottomValue = null; borderWidthLeftValue = null; return true;
	 * } else { rollbackTransaction(trans); return false; } }
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
	 * width - ignorace celé deklarace rollbackTransaction(trans); return false;
	 * } return true; }
	 */

	@SuppressWarnings("unused")
	private boolean processFontFamily(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator font = new FontVariator();
		return font.tryMultiTermVariant(FontVariator.FAMILY, properties,
				values, d.toArray(new Term<?>[0]));
	}

	@SuppressWarnings("unused")
	private boolean processFontSize(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator font = new FontVariator();
		return font.tryOneTermVariant(FontVariator.SIZE, d, properties, values);

	}

	@SuppressWarnings("unused")
	private boolean processFontStyle(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator font = new FontVariator();
		return font
				.tryOneTermVariant(FontVariator.STYLE, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processFontVariant(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator font = new FontVariator();
		return font.tryOneTermVariant(FontVariator.VARIANT, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processFontWeight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator font = new FontVariator();
		return font.tryOneTermVariant(FontVariator.WEIGHT, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processFont(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		Variator font = new FontVariator();
		font.assignTermsFromDeclaration(d);
		font.assignDefaults(properties, values);
		return font.vary(properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processLineHeight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator font = new FontVariator();
		return font.tryOneTermVariant(FontVariator.LINE_HEIGHT, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processTop(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Top.class, Top.length,
				Top.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processRight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Right.class, Right.length,
				Right.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBottom(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Bottom.class, Bottom.length,
				Bottom.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processLeft(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Left.class, Left.length,
				Left.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Width.class, Width.length,
				Width.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processHeight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Height.class, Height.length,
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

	@SuppressWarnings("unused")
	private boolean processClip(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (d.size() != 1)
			return false;

		Term<?> term = d.get(0);
		if (term instanceof TermIdent) {
			final Set<Clip> allowedClips = EnumSet.allOf(Clip.class);
			Clip clip = genericPropertyRaw(Clip.class, allowedClips, (TermIdent) term);
			if (clip != null) {
				properties.put("clip", clip);
				return true;
			}
			return false;
		} else if (term instanceof TermFunction) {
		    return genericTerm(TermFunction.class, term, "clip", Clip.shape, false, properties, values);
		}
		return false;
	}

	@SuppressWarnings("unused")
	protected boolean processCounterIncrement(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (d.size() == 1
				&& genericOneIdent(CounterIncrement.class, d, properties)) {
			return true;
		}
		// counter with increments
		else {
			// counters are stored there
			Set<Term<?>> termList = new LinkedHashSet<Term<?>>();
			String counterName = null;
			for (Term<?> term : d.asList()) {
				// counter name
				if (term instanceof TermIdent) {
					counterName = ((TermIdent) term).getValue();
					termList.add(tf.createPair(counterName, new Integer(1)));
				}
				// counter reset value follows counter name
				else if (term instanceof TermInteger && counterName != null) {
					termList.add(tf.createPair(counterName,
							((TermInteger) term).getIntValue()));
					counterName = null;
				} else {
					return false;
				}
			}
			if (!termList.isEmpty()) {
				TermList list = tf.createList(termList.size());
				list.addAll(termList);
				properties.put("counter-increment",
						CounterIncrement.list_values);
				values.put("counter-increment", list);
				return true;
			}
			return false;
		}
	}

	@SuppressWarnings("unused")
	protected boolean processCounterReset(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (d.size() == 1 && genericOneIdent(CounterReset.class, d, properties)) {
			return true;
		}
		// counter with resets
		else {
			// counters are stored there
			Set<Term<?>> termList = new LinkedHashSet<Term<?>>();
			String counterName = null;
			for (Term<?> term : d.asList()) {
				// counter name
				if (term instanceof TermIdent) {
					counterName = ((TermIdent) term).getValue();
					termList.add(tf.createPair(counterName, new Integer(0)));
				}
				// counter reset value follows counter name
				else if (term instanceof TermInteger && counterName != null) {
					termList.add(tf.createPair(counterName,
							((TermInteger) term).getIntValue()));
					counterName = null;
				} else {
					return false;
				}
			}
			if (!termList.isEmpty()) {
				TermList list = tf.createList(termList.size());
				list.addAll(termList);
				properties.put("counter-reset", CounterReset.list_values);
				values.put("counter-reset", list);
				return true;
			}
			return false;
		}

	}
	
	@SuppressWarnings("unused")
	protected boolean processCounterSet(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (d.size() == 1 && genericOneIdent(CounterSet.class, d, properties)) {
			return true;
		}
		// counter with sets
		else {
			// counters are stored there
			Set<Term<?>> termList = new LinkedHashSet<Term<?>>();
			String counterName = null;
			for (Term<?> term : d.asList()) {
				// counter name
				if (term instanceof TermIdent) {
					counterName = ((TermIdent) term).getValue();
					termList.add(tf.createPair(counterName, new Integer(0)));
				}
				// counter set value follows counter name
				else if (term instanceof TermInteger && counterName != null) {
					termList.add(tf.createPair(counterName,
							((TermInteger) term).getIntValue()));
					counterName = null;
				} else {
					return false;
				}
			}
			if (!termList.isEmpty()) {
				TermList list = tf.createList(termList.size());
				list.addAll(termList);
				properties.put("counter-set", CounterSet.list_values);
				values.put("counter-set", list);
				return true;
			}
			return false;
		}

	}

	@SuppressWarnings("unused")
	private boolean processCursor(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (d.size() == 1 && genericOneIdent(Cursor.class, d, properties)) {
			return true;
		} else {

			final Set<Cursor> allowedCursors = EnumSet.complementOf(EnumSet
					.of(Cursor.INHERIT));

			TermList list = tf.createList();
			Cursor cur = null;
			for (Term<?> term : d.asList()) {
				if (term instanceof TermURI) {
					list.add(term);
				} else if (term instanceof TermIdent
						&& (cur = genericPropertyRaw(Cursor.class,
								allowedCursors, (TermIdent) term)) != null) {
					// this have to be the last cursor in sequence
					// and only one generic cursor is allowed
					if (d.indexOf(term) != d.size() - 1)
						return false;

					// passed as last cursor, insert into properties and values
					properties.put("cursor", cur);
					if (!list.isEmpty())
						values.put("cursor", list);
					return true;
				} else
					return false;
			}
			return false;
		}
	}

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
		return genericOneIdent(CSSProperty.Float.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processListStyleImage(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator listStyle = new ListStyleVariator();
		return listStyle.tryOneTermVariant(ListStyleVariator.IMAGE, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processListStylePosition(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator listStyle = new ListStyleVariator();
		return listStyle.tryOneTermVariant(ListStyleVariator.POSITION, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processListStyleType(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator listStyle = new ListStyleVariator();
		return listStyle.tryOneTermVariant(ListStyleVariator.TYPE, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processListStyle(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		Variator listStyle = new ListStyleVariator();
		listStyle.assignTermsFromDeclaration(d);
		listStyle.assignDefaults(properties, values);
		return listStyle.vary(properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processMarginTop(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Margin.class, Margin.length,
				Margin.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processMarginRight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Margin.class, Margin.length,
				Margin.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processMarginBottom(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Margin.class, Margin.length,
				Margin.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processMarginLeft(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(Margin.class, Margin.length,
				Margin.percentage, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processMargin(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		Repeater margin = new MarginRepeater();
		return margin.repeatOverFourTermDeclaration(d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processMaxHeight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(MaxHeight.class,
				MaxHeight.length, MaxHeight.percentage, true, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processMaxWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(MaxWidth.class,
				MaxWidth.length, MaxWidth.percentage, true, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processMinHeight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(MinHeight.class,
				MinHeight.length, MinHeight.percentage, true, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processMinWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLengthOrPercent(MinWidth.class,
				MinWidth.length, MinWidth.percentage, true, d, properties,
				values);
	}

    @SuppressWarnings("unused")
    private boolean processOpacity(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return genericOneIdentOrIntegerOrNumber(Opacity.class, Opacity.number, Opacity.number, false, d,
                properties, values);
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
		final Variator outline = new OutlineVariator();
		return outline.tryOneTermVariant(OutlineVariator.COLOR, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processOutlineStyle(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator outline = new OutlineVariator();
		return outline.tryOneTermVariant(OutlineVariator.STYLE, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processOutlineWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator outline = new OutlineVariator();
		return outline.tryOneTermVariant(OutlineVariator.WIDTH, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processOutline(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		Variator outline = new OutlineVariator();
		outline.assignTermsFromDeclaration(d);
		outline.assignDefaults(properties, values);
		return outline.vary(properties, values);
	}

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
		Repeater padding = new PaddingRepeater();
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

	@SuppressWarnings("unused")
	private boolean processQuotes(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (d.size() == 1
				&& genericTermIdent(Quotes.class, d.get(0), ALLOW_INH,
						"quotes", properties)) {
			return true;
		} else {
			TermList list = tf.createList();
			for (Term<?> term : d.asList()) {
				if (term instanceof TermString)
					list.add(term);
				else
					return false;
			}

			// there are pairs of quotes
			if (!list.isEmpty() && list.size() % 2 == 0) {
				properties.put("quotes", Quotes.list_values);
				values.put("quotes", list);
				return true;
			}
			return false;
		}
	}

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

	@SuppressWarnings("unused")
	private boolean processTextDecoration(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		final Set<TextDecoration> availableDecorations = EnumSet.of(
				TextDecoration.BLINK, TextDecoration.LINE_THROUGH,
				TextDecoration.OVERLINE, TextDecoration.UNDERLINE);

		// it one term
		if (d.size() == 1) {
			return genericOneIdent(TextDecoration.class, d, properties);
		}
		// there are more terms, we have to construct list
		else {
			TermList list = tf.createList();
			TextDecoration dec = null;
			for (Term<?> term : d.asList()) {
				if (term instanceof TermIdent
						&& (dec = genericPropertyRaw(TextDecoration.class,
								availableDecorations, (TermIdent) term)) != null) {
					// construct term with value of parsed decoration
					list.add(tf.createTerm(dec));
				} else
					return false;
			}
			if (!list.isEmpty()) {
				properties.put("text-decoration", TextDecoration.list_values);
				values.put("text-decoration", list);
				return true;
			}
			return false;
		}
	}

	@SuppressWarnings("unused")
	private boolean processTextIndent(Declaration d,
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
	private boolean processLetterSpacing(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrLength(LetterSpacing.class,
				LetterSpacing.length, false, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processZIndex(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return genericOneIdentOrInteger(ZIndex.class, ZIndex.integer, false, d,
				properties, values);
	}

    /**
     * Processes an unknown property and stores its value. Unknown properties containing
     * multiple values are ignored (the interpretation is not clear).
     * 
     * @param d the declaration.
     * @param properties the properties.
     * @param values the values.
     * 
     * @return <code>true</code>, if the property has been pared successfully
     */
    private boolean processAdditionalCSSGenericProperty(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values)
    {
    	if (d.size() == 1)
    	{
	        Term<?> term = d.get(0);
	
	        if (term instanceof TermIdent)
	            return genericProperty(GenericCSSPropertyProxy.class, (TermIdent) term, true, properties, d.getProperty());
	        else
	            return genericTerm(TermLength.class, term, d.getProperty(), null, false, properties, values)
	                || genericTerm(TermPercent.class, term, d.getProperty(), null, false, properties, values)
	                || genericTerm(TermInteger.class, term, d.getProperty(), null, false, properties, values)
	                || genericTermColor(term, d.getProperty(), null, properties, values);
    	}
    	else
    	{
    		log.warn("Ignoring unsupported property " + d.getProperty() + " with multiple values");
    		return false;
    	}
    }          
	
	@SuppressWarnings("unused")
	private boolean processContent(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		// content contains no explicit values
		if (d.size() == 1 && genericOneIdent(Content.class, d, properties)) {
			return true;
		} else {

			// valid term idents
			final Set<String> validTermIdents = new HashSet<String>(Arrays
					.asList("open-quote", "close-quote", "no-open-quote",
							"no-close-quote"));

			// valid term function names
			final Set<String> validFuncNames = new HashSet<String>(Arrays
					.asList("counter", "counters", "attr"));

			TermList list = tf.createList();

			for (Term<?> t : d.asList()) {
				// one of valid terms
				if (t instanceof TermIdent
						&& validTermIdents.contains(((TermIdent) t).getValue()
								.toLowerCase()))
					list.add(t);
				else if (t instanceof TermString)
					list.add(t);
				else if (t instanceof TermURI)
					list.add(t);
				else if (t instanceof TermFunction
						&& validFuncNames.contains(((TermFunction) t)
								.getFunctionName().toLowerCase()))
					list.add(t);
				else
					return false;
			}
			// there is nothing in list after parsing
			if (list.isEmpty())
				return false;

			properties.put("content", Content.list_values);
			values.put("content", list);
			return true;
		}
	}

	/**
	 * Variator for list style. Grammar:
	 * 
	 * <pre>
	 * [ 'list-style-type' || 'list-style-position' || 'list-style-image' ]
	 * | inherit
	 * 
	 * @author kapy
	 */
	private final class ListStyleVariator extends Variator {

		public static final int TYPE = 0;
		public static final int POSITION = 1;
        public static final int IMAGE = 2;

		/*
		 * protected String[] names = { "list-style-image", "list-style-type",
		 * "list-style-position" };
		 */
		public ListStyleVariator() {
			super(3, css);
			names.add("list-style-type");
			types.add(ListStyleType.class);
			names.add("list-style-position");
			types.add(ListStylePosition.class);
			names.add("list-style-image");
			types.add(ListStyleImage.class);
		}

		@Override
		protected boolean variant(int v, IntegerRef iteration,
				Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

			// we won't use multivalue functionallity
			int i = iteration.get();

			switch (v) {
			case TYPE:
				// list style type
				return genericTermIdent(ListStyleType.class, terms.get(i),
						AVOID_INH, names.get(TYPE), properties);
			case POSITION:
				// list style position
				return genericTermIdent(ListStylePosition.class, terms.get(i),
						AVOID_INH, names.get(POSITION), properties);
            case IMAGE:
                // list style image
                return genericTermIdent(types.get(IMAGE), terms.get(i),
                        AVOID_INH, names.get(IMAGE), properties)
                        || genericTerm(TermURI.class, terms.get(i), names
                                .get(IMAGE), ListStyleImage.uri, false,
                                properties, values);
			default:
				return false;
			}
		}
	}

/**
	 * Variator for border side.
	 * Grammar:
	 * <pre>
	 * [ <border-width> || <border-style> || <'border-top-color'> ] 
	 * | inherit
	 * </pre>
	 * 
	 * @author kapy
	 * 
	 */
	private final class BorderSideVariator extends Variator {

		public static final int COLOR = 0;
		public static final int STYLE = 1;
		public static final int WIDTH = 2;

		public BorderSideVariator(String side) {
			super(3, css);
			names.add("border-" + side + "-color");
			types.add(BorderColor.class);
			names.add("border-" + side + "-style");
			types.add(BorderStyle.class);
			names.add("border-" + side + "-width");
			types.add(BorderWidth.class);
		}

		@Override
		protected boolean variant(int v, IntegerRef iteration,
				Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

			// we won't use multivalue functionallity
			int i = iteration.get();

			switch (v) {
			case COLOR:
				// process color
				return genericTermIdent(types.get(COLOR), terms.get(i),
						AVOID_INH, names.get(COLOR), properties)
						|| genericTermColor(terms.get(i), names.get(COLOR),
								BorderColor.color, properties, values);
			case STYLE:
				// process style
				return genericTermIdent(types.get(STYLE), terms.get(i),
						AVOID_INH, names.get(STYLE), properties);
			case WIDTH:
				// process width
				return genericTermIdent(types.get(WIDTH), terms.get(i),
						AVOID_INH, names.get(WIDTH), properties)
						|| genericTermLength(terms.get(i), names
								.get(WIDTH), BorderWidth.length, true,
								properties, values);
			default:
				return false;
			}

		}
	}

	/**
	 * Outline variator Grammar:
	 * 
	 * <pre>
	 * [ 'outline-color' || 'outline-style' || 'outline-width' ] 
	 * | inherit
	 * </pre>
	 * 
	 * @author kapy
	 * 
	 */
	private final class OutlineVariator extends Variator {

		public static final int COLOR = 0;
		public static final int STYLE = 1;
		public static final int WIDTH = 2;

		public OutlineVariator() {
			super(3, css);
			names.add("outline-color");
			types.add(OutlineColor.class);
			names.add("outline-style");
			types.add(OutlineStyle.class);
			names.add("outline-width");
			types.add(OutlineWidth.class);
		}

		@Override
		protected boolean variant(int v, IntegerRef iteration,
				Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

			// we won't use multivalue functionallity
			int i = iteration.get();

			switch (v) {
			case COLOR:
				// process color
				return genericTermIdent(types.get(COLOR), terms.get(i),
						AVOID_INH, names.get(COLOR), properties)
						|| genericTermColor(terms.get(i), names.get(COLOR),
								OutlineColor.color, properties, values);
			case STYLE:
				// process style
				return genericTermIdent(types.get(STYLE), terms.get(i),
						AVOID_INH, names.get(STYLE), properties);
			case WIDTH:
				// process width
				return genericTermIdent(types.get(WIDTH), terms.get(i),
						AVOID_INH, names.get(WIDTH), properties)
						|| genericTermLength(terms.get(i), names
								.get(WIDTH), OutlineWidth.length, true,
								properties, values);
			default:
				return false;
			}
		}
	}

/**
	 * Font variator:
	 * Grammar:
	 * <pre>
	 * 	[ 
	 * 		[ <'font-style'> || <'font-variant'> || <'font-weight'> ]? 
	 * 		<'font-size'> 
	 * 		[ / <'line-height'> ]? 
	 * 		<'font-family'> 
	 * 	] 
	 * 	| caption | icon | menu | message-box 
	 * 	| small-caption | status-bar | inherit
	 * </pre>
	 * 
	 * @author kapy
	 *
	 */
	private final class FontVariator extends Variator {

		public static final int STYLE = 0;
		public static final int VARIANT = 1;
		public static final int WEIGHT = 2;
		public static final int SIZE = 3;
		public static final int LINE_HEIGHT = 4;
		public static final int FAMILY = 5;

		public FontVariator() {
			super(6, css);
			names.add("font-style");
			types.add(FontStyle.class);
			names.add("font-variant");
			types.add(FontVariant.class);
			names.add("font-weight");
			types.add(FontWeight.class);
			names.add("font-size");
			types.add(FontSize.class);
			names.add("line-height");
			types.add(LineHeight.class);
			names.add("font-family");
			types.add(FontFamily.class);
		}

		@Override
		protected boolean variant(int v, IntegerRef iteration,
				Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

			// we will use multi value functionality in
			// FAMILY branch
			int i = iteration.get();

			switch (v) {
			case STYLE:
				// process font style
				return genericTermIdent(types.get(STYLE), terms.get(i),
						AVOID_INH, names.get(STYLE), properties);
			case VARIANT:
				// process font variant
				return genericTermIdent(types.get(VARIANT), terms.get(i),
						AVOID_INH, names.get(VARIANT), properties);
			case WEIGHT:
				// process font weight
				// test against numeric values
				final Integer[] fontWeight = new Integer[] { 100, 200, 300,
						400, 500, 600, 700, 800, 900 };

				Term<?> term = terms.get(i);

				if (term instanceof TermIdent) {
					return genericProperty(types.get(WEIGHT), (TermIdent) term,
							AVOID_INH, properties, names.get(WEIGHT));
				} else if (term instanceof TermInteger) {

					Integer value = ((TermInteger) term).getIntValue();
					for (Integer test : fontWeight) {
						int result = value.compareTo(test);
						// not found if value is smaller than currently compared
						if (result < 0)
							break;

						// match
						// construct according CSSProperty name
						if (result == 0) {
							CSSProperty property = CSSProperty.Translator
									.valueOf(types.get(WEIGHT), "numeric_"
											+ value.intValue());
							if (property == null) {
								log
										.warn("Not found numeric values for FontWeight: "
												+ "numeric_ "
												+ value.intValue());
								return false;
							}
							properties.put(names.get(WEIGHT), property);
							return true;
						}
					}
				}
				return false;
			case SIZE:
				return genericTermIdent(types.get(SIZE), terms.get(i),
						AVOID_INH, names.get(SIZE), properties)
						|| genericTermLength(terms.get(i), names
								.get(SIZE), FontSize.length, true, properties,
								values)
						|| genericTerm(TermPercent.class, terms.get(i), names
								.get(SIZE), FontSize.percentage, true,
								properties, values);
			case LINE_HEIGHT:
				return genericTermIdent(types.get(LINE_HEIGHT), terms.get(i),
						AVOID_INH, names.get(LINE_HEIGHT), properties)
						|| genericTerm(TermNumber.class, terms.get(i), names
								.get(LINE_HEIGHT), LineHeight.number, true,
								properties, values)
                        || genericTerm(TermInteger.class, terms.get(i), names
                                .get(LINE_HEIGHT), LineHeight.number, true,
                                properties, values)
						|| genericTerm(TermPercent.class, terms.get(i), names
								.get(LINE_HEIGHT), LineHeight.percentage, true,
								properties, values)
						|| genericTerm(TermLength.class, terms.get(i), names
								.get(LINE_HEIGHT), LineHeight.length, true,
								properties, values);
			case FAMILY:
				// all values parsed
				TermList list = tf.createList();
				// current font name
				StringBuffer sb = new StringBuffer();
				// font name was composed from multiple parts
				boolean composed = false;
				for (Term<?> t : terms.subList(i, terms.size())) {
					// first item
					if (t instanceof TermIdent && sb.length() == 0) {
						sb.append(t.getValue());
						composed = false;
					}
					// next item
					else if (t instanceof TermIdent && sb.length() != 0
							&& t.getOperator() != Operator.COMMA) {
						sb.append(" ").append(t.getValue());
						composed = true;
					}
					// store current state
					else if (t instanceof TermString
							|| (t instanceof TermIdent && t.getOperator() == Operator.COMMA)) {
						storeFamilyName(list, sb.toString(), composed);
						sb = new StringBuffer();
						composed = false;
						// store next
						if (t instanceof TermString) {
							storeFamilyName(list, (String) t.getValue(), true);
						} else {
							sb.append(t.getValue());
						}
					}
					// invalid term
					else
						return false;
				}
				// add last item
				storeFamilyName(list, sb.toString(), composed);

				if (list.isEmpty())
					return false;

				// when only generic family is stored, there is no need to have
				// list with one value
				if (list.size() == 1
						&& (list.toArray(new Term<?>[0])[0] instanceof TermString) == false) {
					properties.put(names.get(FAMILY), (FontFamily) (list
							.toArray(new Term<?>[0])[0]).getValue());
					return true;
				}

				properties.put(names.get(FAMILY), FontFamily.list_values);
				values.put(names.get(FAMILY), list);
				// modify reference to the last element
				iteration.set(terms.size());
				return true;
			default:
				return false;
			}
		}

		@Override
		protected boolean variantCondition(int variant, IntegerRef iteration) {

			switch (variant) {
			case STYLE:
			case VARIANT:
			case WEIGHT:
				// must be within 3 first terms
				return iteration.get() < 3;
			case SIZE:
				// no condition
				return true;
			case LINE_HEIGHT:
				if (!variantPassed[SIZE])
					return false;
				return terms.get(iteration.get()).getOperator() == Operator.SLASH;
			case FAMILY:
				// requires passed size
				return variantPassed[SIZE];
			default:
				return false;
			}
		}

		@Override
		public boolean vary(Map<String, CSSProperty> properties,
				Map<String, Term<?>> values) {

			// special check for user interface values
			// such as "caption", "icon" or "menu"
			// this will break inheritance division into distint categories,
			// so it must be reconstructed later
			if (terms.size() == 1 && terms.get(0) instanceof TermIdent) {

				if (checkInherit(ALL_VARIANTS, terms.get(0), properties))
					return true;

				return genericTermIdent(Font.class, terms.get(0), AVOID_INH,
						"font", properties);
			}
			// follow basic control flow
			return super.vary(properties, values);
		}

		private void storeFamilyName(TermList storage, String name,
				boolean composed) {

			final Set<FontFamily> allowedFamilies = EnumSet
					.complementOf(EnumSet.of(FontFamily.INHERIT,
							FontFamily.list_values));

			if (name == null || "".equals(name) || name.length() == 0)
				return;

			// trim spaces
			name = name.trim();

			// if composed, store directly as family name
			if (composed) {
				Term<?> term = tf.createString(name);
				if (!storage.isEmpty())
					term.setOperator(Operator.COMMA);
				storage.add(term);
			}
			// try to find generic name
			else {
				FontFamily generic = genericPropertyRaw(FontFamily.class,
						allowedFamilies, tf.createIdent(name));
				// generic name found,
				// store in term which value is generic font name FontFamily
				// we have to append even operator
				if (generic != null) {
					Term<?> term = tf.createTerm(generic);
					if (!storage.isEmpty())
						term.setOperator(Operator.COMMA);
					storage.add(term);
				}
				// generic name not found, store as family name
				// we have to append even operator
				else {
					Term<?> term = tf.createString(name);
					if (!storage.isEmpty())
						term.setOperator(Operator.COMMA);
					storage.add(term);
				}
			}
		}

	}

/**
	 * Background variator.
	 * Grammar:
	 * <pre>
	 * [ <'background-color'> || <'background-image'> 
	 * 		|| <'background-repeat'> || <'background-attachment'> 
	 * 		|| <'background-position'> [ / <background-size> ]?
	 * ] 
	 * | inherit
	 * </pre>
	 * 
	 * @author kapy
	 */
	private final class BackgroundVariator extends Variator {

		public static final int COLOR = 0;
		public static final int IMAGE = 1;
		public static final int REPEAT = 2;
		public static final int ATTACHMENT = 3;
		public static final int POSITION = 4;
		public static final int SIZE = 5;

		public BackgroundVariator() {
			super(6, css);
			names.add("background-color");
			types.add(BackgroundColor.class);
			names.add("background-image");
			types.add(BackgroundImage.class);
			names.add("background-repeat");
			types.add(BackgroundRepeat.class);
			names.add("background-attachment");
			types.add(BackgroundAttachment.class);
			names.add("background-position");
			types.add(BackgroundPosition.class);
			names.add("background-size");
			types.add(BackgroundSize.class);
		}

		@Override
		protected boolean variant(int v, IntegerRef iteration,
				Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

			// we will use multi value functionality in
			// POSITION branch
			int i = iteration.get();

			switch (v) {
			case COLOR:
				return genericTermIdent(types.get(COLOR), terms.get(i),
						AVOID_INH, names.get(COLOR), properties)
						|| genericTermColor(terms.get(i), names.get(COLOR),
								BackgroundColor.color, properties, values);
			case IMAGE:
				return genericTermIdent(types.get(IMAGE), terms.get(i),
						AVOID_INH, names.get(IMAGE), properties)
						|| genericTerm(TermURI.class, terms.get(i), names
								.get(IMAGE), BackgroundImage.uri, false,
								properties, values);
			case REPEAT:
				return genericTermIdent(types.get(REPEAT), terms.get(i),
						AVOID_INH, names.get(REPEAT), properties);
			case ATTACHMENT:
				return genericTermIdent(types.get(ATTACHMENT), terms.get(i),
						AVOID_INH, names.get(ATTACHMENT), properties);
			case POSITION:

				final EnumSet<BackgroundPosition> allowedBackground = EnumSet
						.complementOf(EnumSet.of(
								BackgroundPosition.list_values,
								BackgroundPosition.INHERIT));

				// try this and next term, but consider terms size
				BackgroundPosition bp = null;
				Term<?>[] vv = {null, null}; //horizontal and vertical position
				for (; i < terms.size(); i++) {
					Term<?> term = terms.get(i);
					if (term.getOperator() != Operator.SLASH)
					{
    					if (term instanceof TermIdent) {
    						bp = genericPropertyRaw(BackgroundPosition.class,
    								allowedBackground, (TermIdent) term);
    						if (bp != null)
    							storeBackgroundPosition(vv, bp, term);
    					} else if (term instanceof TermPercent) {
    						storeBackgroundPosition(vv, null, term);
    					} else if (term instanceof TermLength) {
    						storeBackgroundPosition(vv, null, term);
    					}
    					else //not recognized value
    					    break;
					}
					else //slash found - this value belongs to size rather than position
					    break;
				}

				//create term list from the values, replace unspecified values by center
                int assigned = 0;
				TermList list = tf.createList(2);
                for (int j = 0; j < 2; j++)
                {
                    if (vv[j] == null)
                        list.add(tf.createPercent(50.0f));
                    else
                    {
                        list.add(vv[j]);
                        assigned++;
                    }
                }
                
                // no values could be used
				if (assigned == 0)
					return false;
				// if used two elements, inform master
				else if (assigned == 2)
				    iteration.inc();

				// store list
				properties.put(names.get(POSITION),
						BackgroundPosition.list_values);
				values.put(names.get(POSITION), list);
				return true;

            case SIZE:

                final EnumSet<BackgroundSize> allowedSize = EnumSet
                        .complementOf(EnumSet.of(
                                BackgroundSize.list_values,
                                BackgroundSize.INHERIT));

                // try this and next term, but consider terms size
                BackgroundSize bs = null;
                Term<?>[] sz = {null, null}; //horizontal and vertical size
                int vi = 0; //current value index
                for (; i < terms.size() && vi < 2; i++) {
                    Term<?> term = terms.get(i);
                    if (term instanceof TermIdent) {
                        bs = genericPropertyRaw(BackgroundSize.class,
                                allowedSize, (TermIdent) term);
                        if (bs != null) {
                            //contain and cover have only one occurence
                            properties.put(names.get(SIZE), bs);
                            values.remove(names.get(SIZE)); //only keyword, no value
                            return true;
                        } else if (term.getValue().equals("auto")) {
                            sz[vi++] = term;
                        }
                    } else if (term instanceof TermPercent || term instanceof TermLength) {
                        //TODO this allows integers with no unit as lengths
                        sz[vi++] = term;
                    }
                    else
                        break; //something that cannot be assigned
                }

                //check the number of values
                if (sz[0] == null)
                    return false; //no values set
                else if (sz[1] == null)
                    sz[1] = tf.createIdent("auto");
                else //if used two elements, inform master
                    iteration.inc();
                
                //create term list from the values, replace unspecified values by center
                TermList szlist = tf.createList(2);
                szlist.add(sz[0]);
                szlist.add(sz[1]);

                // store list
                properties.put(names.get(SIZE), BackgroundSize.list_values);
                values.put(names.get(SIZE), szlist);
                return true;
                
			default:
				return false;
			}
		}

		private void storeBackgroundPosition(Term<?>[] storage, BackgroundPosition bp, Term<?> term) 
		{
			if (bp == BackgroundPosition.LEFT)
				setPositionValue(storage, 0, tf.createPercent(0.0f));
            else if (bp == BackgroundPosition.RIGHT)
                setPositionValue(storage, 0, tf.createPercent(100.0f));
            else if (bp == BackgroundPosition.TOP)
                setPositionValue(storage, 1, tf.createPercent(0.0f));
            else if (bp == BackgroundPosition.BOTTOM)
                setPositionValue(storage, 1, tf.createPercent(100.0f));
			else if (bp == BackgroundPosition.CENTER)
                setPositionValue(storage, -1, tf.createPercent(50.0f));
			else
			    setPositionValue(storage, -1, term);
		}
		
		private void setPositionValue(Term<?>[] s, int index, Term<?> term)
		{
		    switch (index) {
		        case -1: if (s[0] == null) //any position - use the free position
		                     s[0] = term;
		                 else
		                     s[1] = term;
		                 break;
                case 0: if (s[0] != null) //if the position is occupied, move the old value
                            s[1] = s[0];
                        s[0] = term;
                        break;
		        case 1: if (s[1] != null)
		                    s[0] = s[1];
		                s[1] = term;
		                break;
		    }
		}

        @Override
        protected boolean variantCondition(int variant, IntegerRef iteration)
        {
            switch (variant)
            {
                case POSITION:
                    if (variantPassed[SIZE])
                        return false;
                    return terms.get(iteration.get()).getOperator() != Operator.SLASH;
                case SIZE:
                    if (!variantPassed[POSITION])
                        return false;
                    return terms.get(iteration.get()).getOperator() == Operator.SLASH;
                default:
                    return true;
            }
        }		
		
	}

	/**
	 * Border variator. Grammar: [ <border-width> || <border-style> ||
	 * <border-top-color> ] | inherit
	 * 
	 * @author kapy
	 * 
	 */
	private final class BorderVariator extends Variator {

		public static final int WIDTH = 0;
		public static final int STYLE = 1;
		public static final int COLOR = 2;

		private List<Repeater> repeaters;

		public BorderVariator() {
			super(3, css);
			types.add(BorderWidth.class);
			types.add(BorderStyle.class);
			types.add(BorderColor.class);
			repeaters = new ArrayList<Repeater>(variants);
			repeaters.add(new BorderWidthRepeater());
			repeaters.add(new BorderStyleRepeater());
			repeaters.add(new BorderColorRepeater());
		}

		@Override
		protected boolean variant(int variant, IntegerRef iteration,
				Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

			// iteration is not modified in this function
			int i = iteration.get();
			Term<?> term = terms.get(i);
			Repeater r;

			switch (variant) {
			case WIDTH:
				r = repeaters.get(WIDTH);
				r.assignTerms(term, term, term, term);
				return r.repeat(properties, values);
			case STYLE:
				r = repeaters.get(STYLE);
				r.assignTerms(term, term, term, term);
				return r.repeat(properties, values);
			case COLOR:
				r = repeaters.get(COLOR);
				r.assignTerms(term, term, term, term);
				return r.repeat(properties, values);
			default:
				return false;
			}
		}

		/**
		 * This method is overriden to use repeaters
		 */
		@Override
		protected boolean checkInherit(int variant, Term<?> term,
				Map<String, CSSProperty> properties) {

			// check whether term equals inherit
			if (!(term instanceof TermIdent)
					|| !CSSProperty.INHERIT_KEYWORD
							.equalsIgnoreCase(((TermIdent) term).getValue())) {
				return false;
			}

			if (variant == ALL_VARIANTS) {
				for (int i = 0; i < variants; i++) {
					Repeater r = repeaters.get(i);
					r.assignTerms(term, term, term, term);
					r.repeat(properties, null);
				}
				return true;
			}

			Repeater r = repeaters.get(variant);
			r.assignTerms(term, term, term, term);
			r.repeat(properties, null);
			return true;
		}

        @Override
        public void assignDefaults(Map<String, CSSProperty> properties, Map<String, Term<?>> values)
        {
            for (Repeater r : repeaters)
                r.assignDefaults(properties, values);
        }
		

	}

	/**
	 * Border style repeater
	 * 
	 * @author kapy
	 * 
	 */
	private final class BorderStyleRepeater extends Repeater {

		public BorderStyleRepeater() {
			super(4, css);
			this.type = BorderStyle.class;
			names.add("border-top-style");
			names.add("border-right-style");
			names.add("border-bottom-style");
			names.add("border-left-style");
		}

		@Override
		protected boolean operation(int i, Map<String, CSSProperty> properties,
				Map<String, Term<?>> values) {

			return genericTermIdent(BorderStyle.class, terms.get(i), ALLOW_INH,	names.get(i), properties);
		}
	}

	/**
	 * Border color repeater
	 * 
	 * @author kapy
	 * 
	 */
	private final class BorderColorRepeater extends Repeater {

		public BorderColorRepeater() {
			super(4, css);
			this.type = BorderColor.class;
			names.add("border-top-color");
			names.add("border-right-color");
			names.add("border-bottom-color");
			names.add("border-left-color");
		}

		@Override
		protected boolean operation(int i, Map<String, CSSProperty> properties,
				Map<String, Term<?>> values) {

			return genericTermIdent(type, terms.get(i), ALLOW_INH, names.get(i), properties)
					|| genericTerm(TermColor.class, terms.get(i), names.get(i),	BorderColor.color, false, properties, values);
		}
	}

	/**
	 * Border width repeater
	 * 
	 * @author kapy
	 * 
	 */
	private final class BorderWidthRepeater extends Repeater {

		public BorderWidthRepeater() {
			super(4, css);
			this.type = BorderWidth.class;
			names.add("border-top-width");
			names.add("border-right-width");
			names.add("border-bottom-width");
			names.add("border-left-width");
		}

		@Override
		protected boolean operation(int i, Map<String, CSSProperty> properties,
				Map<String, Term<?>> values) {

			return genericTermIdent(type, terms.get(i), ALLOW_INH, names.get(i), properties)
					|| genericTermLength(terms.get(i), names.get(i), BorderWidth.length, true, properties, values);
		}
	}

    /**
     * Border radius repeater
     * 
     * @author burgetr
     * 
     */
    private final class BorderRadiusRepeater extends Repeater {

        public BorderRadiusRepeater() {
            super(4, css);
            this.type = BorderRadius.class;
            names.add("border-top-left-radius");
            names.add("border-top-right-radius");
            names.add("border-bottom-right-radius");
            names.add("border-bottom-left-radius");
        }

        @Override
        protected boolean operation(int i, Map<String, CSSProperty> properties,
                Map<String, Term<?>> values) {

            Term<?> term = terms.get(i);
            String name = names.get(i);
            
            if (genericTermIdent(type, terms.get(i), AVOID_INH, names.get(i), properties))
            {
                return true;
            }       
            else if (term instanceof TermList)
            {
                properties.put(name, BorderRadius.list_values);
                values.put(name, term);
                return true;
            }
            else
                return false;
        }
        
        /** Decodes the complicated border-radius declaration into four term pairs */
        public boolean repeatOverMultiTermDeclaration(Declaration d,
                Map<String, CSSProperty> properties, Map<String, Term<?>> values)
                throws IllegalArgumentException {

            if (d.size() == 1) //one value - check for inherit
            {
                Term<?> term = d.get(0);
                if(term instanceof TermIdent && CSSProperty.INHERIT_KEYWORD.equalsIgnoreCase(((TermIdent) term).getValue())) {
                    CSSProperty property = CSSProperty.Translator.createInherit(type);
                    for(int i = 0; i < times; i++) {
                        properties.put(names.get(i), property);
                    }
                    return true;
                }
            }
            
            //find the slash (if any)
            int slash = -1;
            for (int i = 0; i < d.size(); i++)
            {
                Term<?> term = d.get(i);
                if (term.getOperator() == Operator.SLASH)
                {
                    slash = i;
                    break;
                }
            }
            if (slash == -1)
            {
                Term<?>[] sterms = createFourTerms(d, 0, d.size());
                for (int i = 0; i < 4; i++)
                {
                    TermList list = tf.createList(2);
                    list.add(sterms[i]);
                    list.add(sterms[i]);
                    terms.add(list);
                }
            }
            else
            {
                Term<?>[] sterms1 = createFourTerms(d, 0, slash);
                Term<?>[] sterms2 = createFourTerms(d, slash, d.size());
                for (int i = 0; i < 4; i++)
                {
                    TermList list = tf.createList(2);
                    list.add(sterms1[i]);
                    list.add(sterms2[i]);
                    terms.add(list);
                }
            }
            return repeat(properties, values);
        }
        
        private Term<?>[] createFourTerms(Declaration d, int fromIndex, int toIndex)
                throws IllegalArgumentException
        {
            int size = toIndex - fromIndex;
            Term<?>[] ret = new Term<?>[4];
            switch (size) {
            case 1:
                // one term for all value
                ret[0] = ret[1] = ret[2] = ret[3] = d.get(fromIndex); 
                break;
            case 2:
                ret[0] = ret[2] = d.get(fromIndex);
                ret[1] = ret[3] = d.get(fromIndex + 1);
                break;
            case 3:
                ret[0] = d.get(fromIndex);
                ret[1] = ret[3] = d.get(fromIndex+1);
                ret[2] = d.get(fromIndex+2);
                break;
            case 4:
                for (int i = 0; i < 4; i++)
                    ret[i] = d.get(fromIndex + i);
                break;
            default:
                throw new IllegalArgumentException(
                        "Invalid length of terms in Repeater.");
            }
            
            //when started by a slash, remove the slash from the terms
            if (fromIndex != 0)
            {
                for (int i = 0; i < 4; i++)
                    if (ret[i].getOperator() == Operator.SLASH)
                        ret[i] = stripSlash(ret[i]);
            }
            
            return ret;
        }
        
        private Term<?> stripSlash(Term<?> src)
        {
            if (src.getOperator() == Operator.SLASH)
            {
                if (src instanceof TermLength)
                    return tf.createLength((Float) src.getValue(), ((TermLength) src).getUnit());
                else if (src instanceof TermPercent)
                    return tf.createPercent((Float) src.getValue());
                else
                    return src;
            }
            else
                return src;
        }
        
    }
    
	/**
	 * Margin repeater
	 * 
	 * @author kapy
	 * 
	 */
	private final class MarginRepeater extends Repeater {

		public MarginRepeater() {
			super(4, css);
			this.type = Margin.class;
			names.add("margin-top");
			names.add("margin-right");
			names.add("margin-bottom");
			names.add("margin-left");

		}

		@Override
		protected boolean operation(int i, Map<String, CSSProperty> properties,
				Map<String, Term<?>> values) {

			return genericTermIdent(type, terms.get(i), AVOID_INH,
					names.get(i), properties)
					|| genericTermLength(terms.get(i),
							names.get(i), Margin.length, false, properties,
							values)
					|| genericTerm(TermPercent.class, terms.get(i), names
							.get(i), Margin.percentage, false, properties,
							values);
		}
	}

	/**
	 * Padding repeater
	 * 
	 * @author kapy
	 * 
	 */
	private final class PaddingRepeater extends Repeater {

		public PaddingRepeater() {
			super(4, css);
			names.add("padding-top");
			names.add("padding-right");
			names.add("padding-bottom");
			names.add("padding-left");
			this.type = Padding.class;
		}

		@Override
		protected boolean operation(int i, Map<String, CSSProperty> properties,
				Map<String, Term<?>> values) {

			return genericTermIdent(type, terms.get(i), AVOID_INH,
					names.get(i), properties)
					|| genericTermLength(terms.get(i),
							names.get(i), Padding.length, false, properties,
							values)
					|| genericTerm(TermPercent.class, terms.get(i), names
							.get(i), Padding.percentage, false, properties,
							values);
		}
	}

}
