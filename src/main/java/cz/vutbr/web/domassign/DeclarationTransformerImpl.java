package cz.vutbr.web.domassign;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.CSSProperty.*;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleFactory;
import cz.vutbr.web.css.SupportedCSS;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.Term.Operator;
import cz.vutbr.web.css.TermBracketedIdents;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermFloatValue;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermInteger;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermLengthOrPercent;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.css.TermRect;
import cz.vutbr.web.css.TermString;
import cz.vutbr.web.css.TermTime;
import cz.vutbr.web.css.TermURI;
import cz.vutbr.web.css.TermUnicodeRange;
import cz.vutbr.web.csskit.DeclarationTransformer;
import cz.vutbr.web.domassign.decode.*;
import cz.vutbr.web.domassign.decode.Decoder.ValueRange;

/**
 * Contains methods to transform declaration into values applicable to NodeData.
 * Uses defaults defined by CSSFactory
 * 
 * @author kapy
 * 
 */
public class DeclarationTransformerImpl implements DeclarationTransformer {

	private static final Logger log = LoggerFactory
			.getLogger(DeclarationTransformerImpl.class);

	/**
	 * Cache of parsing methods
	 */
	private Map<String, Method> methods;

	/**
	 * Singleton instance
	 */
	private static final DeclarationTransformerImpl instance;

	private static final RuleFactory rf = CSSFactory.getRuleFactory();
	private static final TermFactory tf = CSSFactory.getTermFactory();
	private static final SupportedCSS css = CSSFactory.getSupportedCSS();

	static {
		instance = new DeclarationTransformerImpl();
	}

	/**
	 * Returns instance
	 * 
	 * @return Singleton instance
	 */
	public static final DeclarationTransformerImpl getInstance() {
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
	@Override
	public boolean parseDeclaration(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		final String propertyName = d.getProperty();

		// no such declaration is supported or declaration is empty
		if (!css.isSupportedCSSProperty(propertyName) || d.isEmpty())
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

	/**
	 * Sole constructor
	 */
	private DeclarationTransformerImpl() {
		this.methods = parsingMethods();
	}

	protected Map<String, Method> parsingMethods() {

		Map<String, Method> map = new HashMap<String, Method>(css
				.getTotalProperties(), 1.0f);

		for (String key : css.getDefinedPropertyNames()) {
			try {
				Method m = DeclarationTransformerImpl.class.getDeclaredMethod(
						DeclarationTransformerImpl.camelCase("process-" + key),
						Declaration.class, Map.class, Map.class);
				map.put(key, m);
			} catch (Exception e) {
				log.warn("Unable to find method for property {}.", key);
			}
		}
		log.info("Totally found {} parsing methods", map.size());
		return map;
	}

	// =============================================================
	// processing methods

	@SuppressWarnings("unused")
	private boolean processColor(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrColor(Color.class, Color.color, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processBackground(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		Variator background = new BackgroundVariator();
		return background.varyList(d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBackgroundAttachment(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator background = new BackgroundVariator();
		return background.tryListOfOneTermVariant(BackgroundVariator.ATTACHMENT, d,
				properties, values, BackgroundAttachment.nested_list);
	}

	@SuppressWarnings("unused")
	private boolean processBackgroundColor(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator background = new BackgroundVariator();
		return background.tryListOfOneTermVariant(BackgroundVariator.COLOR, d,
				properties, values, BackgroundColor.nested_list);
	}

	@SuppressWarnings("unused")
	private boolean processBackgroundImage(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator background = new BackgroundVariator();
		return background.tryListOfOneTermVariant(BackgroundVariator.IMAGE, d,
				properties, values, BackgroundImage.nested_list);
	}

	@SuppressWarnings("unused")
	private boolean processBackgroundRepeat(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator background = new BackgroundVariator();
		return background.tryListOfOneTermVariant(BackgroundVariator.REPEAT, d,
				properties, values, BackgroundRepeat.nested_list);
	}

	@SuppressWarnings("unused")
	private boolean processBackgroundPosition(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		final Variator background = new BackgroundVariator();
		return background.tryListOfMultiTermVariant(BackgroundVariator.POSITION, d,
				properties, values, BackgroundPosition.nested_list);
	}

    @SuppressWarnings("unused")
    private boolean processBackgroundSize(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        final Variator background = new BackgroundVariator();
        return background.tryListOfMultiTermVariant(BackgroundVariator.SIZE, d,
                properties, values, BackgroundSize.nested_list);
    }

    @SuppressWarnings("unused")
    private boolean processBackgroundOrigin(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        final Variator background = new BackgroundVariator();
        return background.tryListOfOneTermVariant(BackgroundVariator.ORIGIN, d,
                properties, values, BackgroundOrigin.nested_list);
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
		return Decoder.genericOneIdent(BorderCollapse.class, d, properties);
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
			if (Decoder.genericTermIdent(BorderSpacing.class, term, Decoder.ALLOW_INH,
					propertyName, properties)
					|| Decoder.genericTermLength(term, propertyName,
							BorderSpacing.list_values, ValueRange.DISALLOW_NEGATIVE, properties, values)) {
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
			if (Decoder.genericTermLength(term1, propertyName,
					BorderSpacing.list_values, ValueRange.DISALLOW_NEGATIVE, properties, values)
					&& Decoder.genericTermLength(term2, propertyName,
							BorderSpacing.list_values, ValueRange.DISALLOW_NEGATIVE, properties, values)) {
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
        return Decoder.genericTwoIdentsOrLengthsOrPercents(BorderRadius.class,
                BorderRadius.list_values, ValueRange.DISALLOW_NEGATIVE, d, properties, values);
    }
	
    @SuppressWarnings("unused")
    private boolean processBorderTopRightRadius(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return Decoder.genericTwoIdentsOrLengthsOrPercents(BorderRadius.class,
                BorderRadius.list_values, ValueRange.DISALLOW_NEGATIVE, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processBorderBottomRightRadius(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return Decoder.genericTwoIdentsOrLengthsOrPercents(BorderRadius.class,
                BorderRadius.list_values, ValueRange.DISALLOW_NEGATIVE, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processBorderBottomLeftRadius(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return Decoder.genericTwoIdentsOrLengthsOrPercents(BorderRadius.class,
                BorderRadius.list_values, ValueRange.DISALLOW_NEGATIVE, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processBorderRadius(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        BorderRadiusRepeater radius = new BorderRadiusRepeater();
        return radius.repeatOverMultiTermDeclaration(d, properties, values);
    }
	
	@SuppressWarnings("unused")
	private boolean processBoxShadow(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		if (d.size() == 1 && Decoder.genericOneIdent(BoxShadow.class, d, properties)) {
			return true;
		}
		// inset? && <length>{2,4} && <color>?
		TermList list = tf.createList();

		int lengthCount = 0;
		int lastLengthIndex = -1;
		int insetIndex = -1;
		int colorIndex = -1;

		for (int i = 0; i < d.size(); i++) {
			Term<?> t = d.get(i);

			if (t.getOperator() == Operator.COMMA) {
				if (lengthCount < 2) {
					return false;
				}
				lengthCount = 0;
				lastLengthIndex = -1;
				insetIndex = -1;
				colorIndex = -1;
			}

			if (t instanceof TermColor && colorIndex < 0) {
				colorIndex = i;
			} else if (t instanceof TermIdent
					&& ((TermIdent) t).getValue().equalsIgnoreCase("inset")
					&& insetIndex < 0) {
				insetIndex = i;
			} else if (t instanceof TermLength
					&& lastLengthIndex < 0
					|| (lastLengthIndex > insetIndex && lastLengthIndex > colorIndex)) {
				if (lengthCount >= 4) {
					return false;
				}
				lastLengthIndex = i;
				lengthCount++;
			} else {
				return false;
			}
			list.add(t);
		}

		if (lengthCount < 2) {
			return false;
		}
		properties.put(d.getProperty(), BoxShadow.component_values);
		values.put(d.getProperty(), list);
		return true;
	}

	@SuppressWarnings("unused")
	private boolean processBoxSizing(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return Decoder.genericOneIdent(BoxSizing.class, d, properties);
    }
    
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
    private boolean processTabSize(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return Decoder.genericIntegerOrLength(TabSize.class, TabSize.integer,
                TabSize.length, ValueRange.DISALLOW_NEGATIVE, d, properties, values);
    }

	@SuppressWarnings("unused")
	private boolean processTop(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLengthOrPercent(Top.class, Top.length,
				Top.percentage, ValueRange.ALLOW_ALL, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processRight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLengthOrPercent(Right.class, Right.length,
				Right.percentage, ValueRange.ALLOW_ALL, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processBottom(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLengthOrPercent(Bottom.class, Bottom.length,
				Bottom.percentage, ValueRange.ALLOW_ALL, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processLeft(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLengthOrPercent(Left.class, Left.length,
				Left.percentage, ValueRange.ALLOW_ALL, d, properties, values);
	}

    @SuppressWarnings("unused")
    private boolean processTransform(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

        // just a simple value (e.g. "none")
        if (d.size() == 1 && Decoder.genericOneIdent(Transform.class, d, properties)) {
            return true;
        } else {

            TermList list = tf.createList();

            for (Term<?> t : d.asList()) {
                if (t instanceof TermFunction.TransformFunction)
                    list.add(t);
                else
                    return false;
            }
            // there is nothing in list after parsing
            if (list.isEmpty())
                return false;

            properties.put("transform", Transform.list_values);
            values.put("transform", list);
            return true;
        }
    }
    
    @SuppressWarnings("unused")
    private boolean processTransformOrigin(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        
        if (d.size() == 1
            && Decoder.genericTermIdent(BorderSpacing.class, d.get(0), Decoder.ALLOW_INH, d.getProperty(), properties))
        {
            return true; //must be 'inherit'
        }
        else if (d.size() >= 1 && d.size() <= 3)
        {
            TermLengthOrPercent hpos = null;
            TermLengthOrPercent vpos = null;
            TermLength zpos = null;
            //generic check and assign recognizable keywords
            for (int i = 0; i < d.size(); i++)
            {
                Term<?> term = d.get(i);
                if (term instanceof TermIdent)
                {
                    String value = ((TermIdent) term).getValue();
                    if ("top".equals(value))
                    {
                        if (vpos == null)
                            vpos = tf.createPercent(0.0f);
                        else
                            return false;
                    }
                    else if ("bottom".equals(value))
                    {
                        if (vpos == null)
                            vpos = tf.createPercent(100.0f);
                        else
                            return false;
                    }
                    else if ("left".equals(value))
                    {
                        if (hpos == null)
                            hpos = tf.createPercent(0.0f);
                        else
                            return false;
                    }
                    else if ("right".equals(value))
                    {
                        if (hpos == null)
                            hpos = tf.createPercent(100.0f);
                        else
                            return false;
                    }
                    else if ("center".equals(value))
                    {
                        //skip for this iteration
                    }
                    else
                        return false; //unknown keyword
                }
                else if (term instanceof TermLengthOrPercent)
                {
                    if (i > 1 && ((TermLengthOrPercent) term).isPercentage())
                        return false; //percentages are only allowed for arguments 1 and 2
                }
                else
                    return false; //invalid value (not keyword nor length nor percentage)
            }
            //assign 'center' or numeric values
            for (int i = 0; i < d.size(); i++)
            {
                TermLengthOrPercent value = null;
                Term<?> term = d.get(i);
                if (i < 2) //first two arguments
                {
                    if (term instanceof TermIdent)
                    {
                        if ("center".equals(((TermIdent) term).getValue()))
                                value = tf.createPercent(50.0f);
                    }
                    else
                        value = (TermLengthOrPercent) term;
                    
                    if (value != null)
                    {
                        if (hpos == null)
                            hpos = value;
                        else if (vpos == null)
                            vpos = value;
                        else
                            return false;
                    }
                }
                else //last argument, must be length
                {
                    zpos = (TermLength) term;
                }
            }
            //replace null values by defaults
            if (hpos == null)
                hpos = tf.createPercent(50.0f);
            if (vpos == null)
                vpos = tf.createPercent(50.0f);
            if (zpos == null)
                zpos = tf.createLength(0.0f);
            //publish the values
            TermList list = tf.createList();
            list.add(hpos);
            list.add(vpos);
            list.add(zpos);
            properties.put("transform-origin", TransformOrigin.list_values);
            values.put("transform-origin", list);
            return true;
        }
        else
            return false; //invalid number of arguments
    }
    
	@SuppressWarnings("unused")
	private boolean processWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLengthOrPercent(Width.class, Width.length,
				Width.percentage, ValueRange.DISALLOW_NEGATIVE, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processHeight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLengthOrPercent(Height.class, Height.length,
				Height.percentage, ValueRange.DISALLOW_NEGATIVE, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processCaptionSide(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdent(CaptionSide.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processClear(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdent(Clear.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processClip(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (d.size() != 1)
			return false;

		Term<?> term = d.get(0);
		if (term instanceof TermIdent) {
			final Set<Clip> allowedClips = EnumSet.allOf(Clip.class);
			Clip clip = Decoder.genericPropertyRaw(Clip.class, allowedClips, (TermIdent) term);
			if (clip != null) {
				properties.put("clip", clip);
				return true;
			}
			return false;
		} else if (term instanceof TermRect) {
		    return Decoder.genericTerm(TermRect.class, term, "clip", Clip.shape, ValueRange.ALLOW_ALL, properties, values);
		}
		return false;
	}

	@SuppressWarnings("unused")
	private boolean processCounterIncrement(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (d.size() == 1
				&& Decoder.genericOneIdent(CounterIncrement.class, d, properties)) {
			return true;
		}
		// counter with increments
		else {
			List<Term<?>> termList = decodeCounterList(d.asList(), 1);
			if (termList != null && !termList.isEmpty()) {
				TermList list = tf.createList(termList.size());
				list.addAll(termList);
				properties.put("counter-increment",	CounterIncrement.list_values);
				values.put("counter-increment", list);
				return true;
			}
			return false;
		}
	}

	@SuppressWarnings("unused")
	private boolean processCounterReset(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (d.size() == 1 && Decoder.genericOneIdent(CounterReset.class, d, properties)) {
			return true;
		}
		// counter with resets
		else {
			// counters are stored there
			List<Term<?>> termList = decodeCounterList(d.asList(), 0);
			if (termList != null && !termList.isEmpty()) {
				TermList list = tf.createList(termList.size());
				list.addAll(termList);
				properties.put("counter-reset", CounterReset.list_values);
				values.put("counter-reset", list);
				return true;
			}
			return false;
		}
	}

    private List<Term<?>> decodeCounterList(List<Term<?>> terms, int defaultValue)
    {
        List<Term<?>> ret = new ArrayList<>();
        int i = 0;
        while (i < terms.size()) {
            final Term<?> term = terms.get(i);
            if (term instanceof TermIdent) {
                final String counterName = ((TermIdent) term).getValue();
                if (i + 1 < terms.size() && terms.get(i + 1) instanceof TermInteger)
                {
                    //integer value specified after the counter name
                    int counterValue = ((TermInteger) terms.get(i + 1)).getIntValue();
                    ret.add(tf.createPair(counterName, counterValue));
                    i += 2;
                }
                else
                {
                    //only the counter name, use the default value
                    ret.add(tf.createPair(counterName, defaultValue));
                    i++;
                }
            } else {
                return null;
            }
        }
        return ret;
    }

	@SuppressWarnings("unused")
	private boolean processCursor(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (d.size() == 1 && Decoder.genericOneIdent(Cursor.class, d, properties)) {
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
						&& (cur = Decoder.genericPropertyRaw(Cursor.class,
								allowedCursors, (TermIdent) term)) != null) {
					// this have to be the last cursor in sequence
					// and only one Decoder.generic cursor is allowed
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
		return Decoder.genericOneIdent(Direction.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processDisplay(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdent(Display.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processEmptyCells(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdent(EmptyCells.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processFloat(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdent(CSSProperty.Float.class, d, properties);
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
		return Decoder.genericOneIdentOrLengthOrPercent(Margin.class, Margin.length,
				Margin.percentage, ValueRange.ALLOW_ALL, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processMarginRight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLengthOrPercent(Margin.class, Margin.length,
				Margin.percentage, ValueRange.ALLOW_ALL, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processMarginBottom(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLengthOrPercent(Margin.class, Margin.length,
				Margin.percentage, ValueRange.ALLOW_ALL, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processMarginLeft(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLengthOrPercent(Margin.class, Margin.length,
				Margin.percentage, ValueRange.ALLOW_ALL, d, properties, values);
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
		return Decoder.genericOneIdentOrLengthOrPercent(MaxHeight.class,
				MaxHeight.length, MaxHeight.percentage, ValueRange.DISALLOW_NEGATIVE, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processMaxWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLengthOrPercent(MaxWidth.class,
				MaxWidth.length, MaxWidth.percentage, ValueRange.DISALLOW_NEGATIVE, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processMinHeight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLengthOrPercent(MinHeight.class,
				MinHeight.length, MinHeight.percentage, ValueRange.DISALLOW_NEGATIVE, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processMinWidth(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLengthOrPercent(MinWidth.class,
				MinWidth.length, MinWidth.percentage, ValueRange.DISALLOW_NEGATIVE, d, properties,
				values);
	}

    @SuppressWarnings("unused")
    private boolean processOpacity(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return Decoder.genericOneIdentOrIntegerOrNumber(Opacity.class, Opacity.number, Opacity.number, ValueRange.TRUNCATE_NEGATIVE, d,
                properties, values);
    }

	@SuppressWarnings("unused")
	private boolean processOrphans(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrInteger(Orphans.class, Orphans.integer, ValueRange.DISALLOW_NEGATIVE,
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
	    
	    if (d.size() == 1) {
	        Term<?> term = d.get(0);
	        if (term instanceof TermIdent) {
	            return Decoder.genericProperty(Overflow.class, (TermIdent) term, Decoder.ALLOW_INH, properties, "overflow-x")
	                    && Decoder.genericProperty(Overflow.class, (TermIdent) term, Decoder.ALLOW_INH, properties, "overflow-y");
	        }
	        else
	            return false;
	    }
	    else
	        return false;
	}

    @SuppressWarnings("unused")
    private boolean processOverflowX(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return Decoder.genericOneIdent(Overflow.class, d, properties);
    }

    @SuppressWarnings("unused")
    private boolean processOverflowY(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return Decoder.genericOneIdent(Overflow.class, d, properties);
    }

	@SuppressWarnings("unused")
	private boolean processPaddingTop(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLengthOrPercent(Padding.class, Padding.length,
				Padding.percentage, ValueRange.DISALLOW_NEGATIVE, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processPaddingRight(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLengthOrPercent(Padding.class, Padding.length,
				Padding.percentage, ValueRange.DISALLOW_NEGATIVE, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processPaddingBottom(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLengthOrPercent(Padding.class, Padding.length,
				Padding.percentage, ValueRange.DISALLOW_NEGATIVE, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processPaddingLeft(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLengthOrPercent(Padding.class, Padding.length,
				Padding.percentage, ValueRange.DISALLOW_NEGATIVE, d, properties, values);
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
		return Decoder.genericOneIdent(PageBreak.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processPageBreakBefore(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdent(PageBreak.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processPageBreakInside(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdent(PageBreakInside.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processPosition(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdent(Position.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processQuotes(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		if (d.size() == 1
				&& Decoder.genericTermIdent(Quotes.class, d.get(0), Decoder.ALLOW_INH,
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
		return Decoder.genericOneIdent(TableLayout.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processTextAlign(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdent(TextAlign.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processTextDecoration(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		final Set<TextDecoration> availableDecorations = EnumSet.of(
				TextDecoration.BLINK, TextDecoration.LINE_THROUGH,
				TextDecoration.OVERLINE, TextDecoration.UNDERLINE);

		// it one term
		if (d.size() == 1) {
			return Decoder.genericOneIdent(TextDecoration.class, d, properties);
		}
		// there are more terms, we have to construct list
		else {
			TermList list = tf.createList();
			TextDecoration dec = null;
			for (Term<?> term : d.asList()) {
				if (term instanceof TermIdent
						&& (dec = Decoder.genericPropertyRaw(TextDecoration.class,
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
		return Decoder.genericOneIdentOrLengthOrPercent(TextIndent.class,
				TextIndent.length, TextIndent.percentage, ValueRange.ALLOW_ALL, d, properties,
				values);
	}

	@SuppressWarnings("unused")
	private boolean processTextTransform(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdent(TextTransform.class, d, properties);
	}

    @SuppressWarnings("unused")
    private boolean processUnicodeBidi(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return Decoder.genericOneIdent(UnicodeBidi.class, d, properties);
    }
    
	@SuppressWarnings("unused")
	private boolean processUnicodeRange(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
	    
		if (d.size() > 0) {
		    TermList list = tf.createList();
		    for (int i = 0; i < d.size(); i++) {
		        Term<?> term = d.get(i);
		        if (term instanceof TermUnicodeRange
		                && ((i == 0 && term.getOperator() == null) || (i != 0 && term.getOperator() == Operator.COMMA))) {
		            list.add(term);
		        } else {
		            return false;
		        }
		    }
		    properties.put("unicode-range", UnicodeRange.list_values);
		    values.put("unicode-range", list);
		    return true;
		}
		else
		    return false;
	}

	@SuppressWarnings("unused")
	private boolean processVerticalAlign(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLengthOrPercent(VerticalAlign.class,
				VerticalAlign.length, VerticalAlign.percentage, ValueRange.ALLOW_ALL, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processVisibility(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdent(Visibility.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processWhiteSpace(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdent(WhiteSpace.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processWidows(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrInteger(Widows.class, Widows.integer, ValueRange.DISALLOW_NEGATIVE, d,
				properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processWordSpacing(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLength(WordSpacing.class, WordSpacing.length,
				ValueRange.ALLOW_ALL, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processLetterSpacing(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLength(LetterSpacing.class,
				LetterSpacing.length, ValueRange.ALLOW_ALL, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processZIndex(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrInteger(ZIndex.class, ZIndex.integer, ValueRange.ALLOW_ALL, d,
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
	            return Decoder.genericProperty(GenericCSSPropertyProxy.class, (TermIdent) term, true, properties, d.getProperty());
	        else
	            return Decoder.genericTerm(TermLength.class, term, d.getProperty(), null, ValueRange.ALLOW_ALL, properties, values)
	                || Decoder.genericTerm(TermPercent.class, term, d.getProperty(), null, ValueRange.ALLOW_ALL, properties, values)
	                || Decoder.genericTerm(TermInteger.class, term, d.getProperty(), null, ValueRange.ALLOW_ALL, properties, values)
	                || Decoder.genericTermColor(term, d.getProperty(), null, properties, values);
    	}
    	else
    	{
    		log.warn("Ignoring unsupported property " + d.getProperty() + " with multiple values");
    		return false;
    	}
    }          
	
    @SuppressWarnings("unused")
    private boolean processFlex(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        Variator variator = new FlexVariator();
        variator.assignTermsFromDeclaration(d);
        variator.assignDefaults(properties, values);
        return variator.vary(properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processFlexFlow(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        Variator variator = new FlexFlowVariator();
        variator.assignTermsFromDeclaration(d);
        variator.assignDefaults(properties, values);
        return variator.vary(properties, values);
    }
    
	@SuppressWarnings("unused")
	private boolean processFlexBasis(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdentOrLengthOrPercent(FlexBasis.class, FlexBasis.length, FlexBasis.percentage, ValueRange.DISALLOW_NEGATIVE, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processFlexDirection(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdent(FlexDirection.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processFlexWrap(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdent(FlexWrap.class, d, properties);
	}

	@SuppressWarnings("unused")
	private boolean processFlexGrow(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return Decoder.genericOneIdentOrIntegerOrNumber(FlexGrow.class, FlexGrow.number, FlexGrow.number, ValueRange.DISALLOW_NEGATIVE, d, properties, values);
	}
	
	@SuppressWarnings("unused")
	private boolean processFlexShrink(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return Decoder.genericOneIdentOrIntegerOrNumber(FlexShrink.class, FlexShrink.number, FlexShrink.number, ValueRange.DISALLOW_NEGATIVE, d, properties, values);
	}
	
	@SuppressWarnings("unused")
	private boolean processJustifyContent(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
		return Decoder.genericOneIdent(JustifyContent.class, d, properties);
	}

    @SuppressWarnings("unused")
    private boolean processAlignContent(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return Decoder.genericOneIdent(AlignContent.class, d, properties);
    }

    @SuppressWarnings("unused")
    private boolean processAlignItems(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return Decoder.genericOneIdent(AlignItems.class, d, properties);
    }

    @SuppressWarnings("unused")
    private boolean processAlignSelf(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return Decoder.genericOneIdent(AlignSelf.class, d, properties);
    }

	@SuppressWarnings("unused")
	private boolean processOrder(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return Decoder.genericInteger(Order.class, Order.integer, ValueRange.DISALLOW_NEGATIVE, d, properties, values);
	}

	@SuppressWarnings("unused")
	private boolean processContent(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

		// content contains no explicit values
		if (d.size() == 1 && Decoder.genericOneIdent(Content.class, d, properties)) {
			return true;
		} else {

			// valid term idents
			final Set<String> validTermIdents = new HashSet<String>(Arrays
					.asList("open-quote", "close-quote", "no-open-quote",
							"no-close-quote"));

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
				else if (t instanceof TermFunction.CounterFunction || t instanceof TermFunction.Attr)
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

    @SuppressWarnings("unused")
    private boolean processFilter(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

        // single ident: none, or global ones
        if (d.size() == 1 && Decoder.genericOneIdent(Filter.class, d, properties)) {
            return true;
        } else {
            //list of uri() or <filter-function> expected
            TermList list = tf.createList();

            for (Term<?> t : d.asList()) {
                if (t instanceof TermFunction.FilterFunction)
                    list.add(t);
                else if (t instanceof TermURI)
                    list.add(t);
                else
                    return false;
            }
            // there is nothing in list after parsing
            if (list.isEmpty())
                return false;

            properties.put("filter", Filter.list_values);
            values.put("filter", list);
            return true;
        }
    }
    
    @SuppressWarnings("unused")
    private boolean processBackdropFilter(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

        // single ident: none, or global ones
        if (d.size() == 1 && Decoder.genericOneIdent(BackdropFilter.class, d, properties)) {
            return true;
        } else {
            //list of uri() or <filter-function> expected
            TermList list = tf.createList();

            for (Term<?> t : d.asList()) {
                if (t instanceof TermFunction.FilterFunction)
                    list.add(t);
                else if (t instanceof TermURI)
                    list.add(t);
                else
                    return false;
            }
            // there is nothing in list after parsing
            if (list.isEmpty())
                return false;

            properties.put("backdrop-filter", BackdropFilter.list_values);
            values.put("backdrop-filter", list);
            return true;
        }
    }
    
    @SuppressWarnings("unused")
    private boolean processGrid(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        // <'grid-template'> 
        // | <'grid-template-rows'> / [ auto-flow && dense? ] <'grid-auto-columns'>?
        // | [ auto-flow && dense? ] <'grid-auto-rows'>? / <'grid-template-columns'>
        Declaration templateDecl = rf.createDeclaration(d);
        templateDecl.setProperty("grid-template");
        if (processGridTemplate(templateDecl, properties, values)) {
            return true;
        }

        boolean beforeSlash = true;
        boolean autoFlowBeforeSlash = false;
        Declaration autoFlowDecl = (Declaration) rf.createDeclaration().unlock();
        autoFlowDecl.setProperty("grid-auto-flow");
        Declaration templateRowsDecl = (Declaration) rf.createDeclaration().unlock();
        templateRowsDecl.setProperty("grid-template-rows");
        Declaration autoRowsDecl = (Declaration) rf.createDeclaration().unlock();
        autoRowsDecl.setProperty("grid-auto-rows");
        Declaration templateColumnsDecl = (Declaration) rf.createDeclaration().unlock();
        templateColumnsDecl.setProperty("grid-template-columns");
        Declaration autoColumnsDecl = (Declaration) rf.createDeclaration().unlock();
        autoColumnsDecl.setProperty("grid-auto-columns");

        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if (t.getOperator() == Term.Operator.SLASH) {
                beforeSlash = false;
            }

            if (t instanceof TermIdent) {
                CSSProperty property = Decoder.genericPropertyRaw(Grid.class, null, (TermIdent) t);
                if (Grid.AUTO_FLOW.equals(property)) {
                    if (beforeSlash) {
                        autoFlowDecl.add(tf.createIdent("row"));
                    } else {
                        autoFlowDecl.add(tf.createIdent("column"));
                    }
                    autoFlowBeforeSlash = beforeSlash;
                    continue;
                } else {
                    property = Decoder.genericPropertyRaw(GridAutoFlow.class, null, (TermIdent) t);
                    if (GridAutoFlow.DENSE.equals(property)) {
                        autoFlowDecl.add(t);
                        continue;
                    }
                }
            }

            if (autoFlowDecl.isEmpty()) {
                if (beforeSlash) {
                    templateRowsDecl.add(t);
                }
            } else {
                if (beforeSlash) {
                    autoRowsDecl.add(t);
                } else if (autoFlowBeforeSlash) {
                    templateColumnsDecl.add(t);
                } else {
                    autoColumnsDecl.add(t);
                }
            }
        }
        processGridAutoRows(autoRowsDecl, properties, values);
        processGridAutoColumns(autoColumnsDecl, properties, values);

        return processGridAutoFlow(autoFlowDecl, properties, values)
                && (processGridTemplateRows(templateRowsDecl, properties, values)
                || processGridTemplateColumns(templateColumnsDecl, properties, values));
    }

    @SuppressWarnings("unused")
    private boolean processGridGap(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        Term<?> rowGapTerm, columnGapTerm;
        switch (d.size()) {
            case 1:
                rowGapTerm = columnGapTerm = d.get(0);
                break;
            case 2:
                rowGapTerm = d.get(0);
                columnGapTerm = d.get(1);
                break;
            default:
                return false;
        }
        return (Decoder.genericTermIdent(GridGap.class, rowGapTerm, Decoder.ALLOW_INH, "grid-row-gap", properties)
                || Decoder.genericTermLength(rowGapTerm, "grid-row-gap", GridGap.length, ValueRange.DISALLOW_NEGATIVE, properties, values)
                || Decoder.genericTerm(TermPercent.class, rowGapTerm, "grid-row-gap", GridGap.length, ValueRange.DISALLOW_NEGATIVE, properties, values))
                && (Decoder.genericTermIdent(GridGap.class, columnGapTerm, Decoder.ALLOW_INH, "grid-column-gap", properties)
                || Decoder.genericTermLength(columnGapTerm, "grid-column-gap", GridGap.length, ValueRange.DISALLOW_NEGATIVE, properties, values)
                || Decoder.genericTerm(TermPercent.class, columnGapTerm, "grid-column-gap", GridGap.length, ValueRange.DISALLOW_NEGATIVE, properties, values));
    }

    @SuppressWarnings("unused")
    private boolean processGridRowGap(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return Decoder.genericOneIdentOrLengthOrPercent(GridGap.class, GridGap.length, GridGap.length, ValueRange.DISALLOW_NEGATIVE, d, properties, values);
    }

    @SuppressWarnings("unused")
    private boolean processGridColumnGap(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return Decoder.genericOneIdentOrLengthOrPercent(GridGap.class, GridGap.length, GridGap.length, ValueRange.DISALLOW_NEGATIVE, d, properties, values);
    }

    @SuppressWarnings("unused")
    private boolean processGridArea(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return processNStartEnds(4, new String[]{"grid-row-start", "grid-column-start", "grid-row-end", "grid-column-end"}, d, properties, values);
    }

    @SuppressWarnings("unused")
    private boolean processGridRow(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return processNStartEnds(2, new String[]{"grid-row-start", "grid-row-end"}, d, properties, values);
    }

    @SuppressWarnings("unused")
    private boolean processGridColumn(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return processNStartEnds(2, new String[]{"grid-column-start", "grid-column-end"}, d, properties, values);
    }

    private boolean processNStartEnds(int n, String[] propertyNames,
            Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        if (n != propertyNames.length) {
            return false;
        }
        TermList[] lists = new TermList[n];
        for (int i = 0; i < n; i++) {
            lists[i] = tf.createList();
        }
        Map<String, TermList> identOnly = new HashMap<>();
        int listIndex = 0;
        // auto | <custom-ident> | [ <integer> && <custom-ident>? ] | [ span && [ <integer> || <custom-ident> ] ]
        int valueValue = 0;
        int valueIndex = -1;
        int spanIndex = -1;
        int identIndex = -1;
        boolean autoSet = false;
        
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if (t.getOperator() == Term.Operator.SLASH) {
                if(!autoSet && spanIndex < 0 && valueIndex < 0) {
                    identOnly.put(propertyNames[listIndex], lists[listIndex]);
                }
                listIndex++;
                valueIndex = -1;
                spanIndex = -1;
                identIndex = -1;
                autoSet = false;
                if (listIndex >= n) {
                    return false;
                }
            }
            if (t instanceof TermIdent) {
                CSSProperty property = Decoder.genericPropertyRaw(GridStartEnd.class, null, (TermIdent) t);
                if (GridStartEnd.AUTO.equals(property) && lists[listIndex].isEmpty()) {
                    autoSet = true;
                } else if (GridStartEnd.SPAN.equals(property) && spanIndex < 0 && !autoSet
                         &&(valueIndex < 0 || valueValue > 0)) {
                    spanIndex = i;
                } else if (property == null && identIndex < 0 
                        && (spanIndex < 0 || valueIndex < 0 || spanIndex < valueIndex) && !autoSet) {
                    identIndex = i;
                } else {
                    return false;
                }
            } else if (t instanceof TermInteger && ((TermInteger) t).getIntValue() != 0
                    && (spanIndex < 0 || ((TermInteger) t).getIntValue() > 0)
                    && valueIndex < 0 && (identIndex < 0 || identIndex > spanIndex) && !autoSet) {
                valueValue = ((TermInteger) t).getIntValue();
                valueIndex = i;
            } else {
                return false;
            }
            lists[listIndex].add(t);
        }
        if(!autoSet && spanIndex < 0 && valueIndex < 0) {
            identOnly.put(propertyNames[listIndex], lists[listIndex]);
        }
        
        for (int i = 1; i < n; i++) {
            if(i <= listIndex) { // Property set explicitly
                setStartEndProperties(propertyNames[i], lists[i], properties, values);
            } else {
                switch(propertyNames[i]) { // Inherit indentifier from other property from declaration
                    case "grid-column-start":
                        if(identOnly.containsKey("grid-row-start")) {
                            setStartEndProperties(propertyNames[i], identOnly.get("grid-row-start"), properties, values);
                        }
                        break;
                    case "grid-row-end":
                        if(identOnly.containsKey("grid-row-start")) {
                            setStartEndProperties(propertyNames[i], identOnly.get("grid-row-start"), properties, values);
                        }
                        break;
                    case "grid-column-end":
                        if(identOnly.containsKey("grid-column-start")) {
                            setStartEndProperties(propertyNames[i], identOnly.get("grid-column-start"), properties, values);
                        } else if(identOnly.containsKey("grid-row-start")) {
                            setStartEndProperties(propertyNames[i], identOnly.get("grid-row-start"), properties, values);
                        }
                        break;
                }
            }
        }
        return setStartEndProperties(propertyNames[0], lists[0], properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processGridRowStart(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return processGridStartEnd(d, properties, values);
    }

    @SuppressWarnings("unused")
    private boolean processGridRowEnd(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return processGridStartEnd(d, properties, values);
    }

    @SuppressWarnings("unused")
    private boolean processGridColumnStart(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return processGridStartEnd(d, properties, values);
    }

    @SuppressWarnings("unused")
    private boolean processGridColumnEnd(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return processGridStartEnd(d, properties, values);
    }

    private boolean processGridStartEnd(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        if (d.isEmpty()) {
            return false;
        }
        if (Decoder.genericOneIdentOrInteger(GridStartEnd.class, GridStartEnd.number, ValueRange.DISALLOW_ZERO, d, properties, values)) {
            return !GridStartEnd.SPAN.equals(properties.get(d.getProperty()));
        }
        // auto | <custom-ident> | [ <integer> && <custom-ident>? ] | [ span && [ <integer> || <custom-ident> ] ]
        int valueValue = 0;
        int valueIndex = -1;
        int spanIndex = -1;
        int identIndex = -1;
        TermList list = tf.createList();
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if (t instanceof TermIdent) {
                CSSProperty property = Decoder.genericPropertyRaw(GridStartEnd.class, null, (TermIdent) t);
                if (GridStartEnd.SPAN.equals(property) && spanIndex < 0 && (valueIndex < 0 || valueValue > 0)) {
                    spanIndex = i;
                } else if (property == null && identIndex < 0 
                        && (spanIndex < 0 || valueIndex < 0 || spanIndex < valueIndex)) {
                    identIndex = i;
                } else {
                    return false;
                }
            } else if (t instanceof TermInteger && ((TermInteger) t).getIntValue() != 0
                    && (spanIndex < 0 || ((TermInteger) t).getIntValue() > 0)
                    && valueIndex < 0 && (identIndex < 0 || identIndex > spanIndex)) {
                valueValue = ((TermInteger) t).getIntValue();
                valueIndex = i;
            } else {
                return false;
            }
            list.add(t);
        }
        return setStartEndProperties(d.getProperty(), list, properties, values);
    }

    private boolean setStartEndProperties(String propertyName, TermList list, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        switch (list.size()) {
            case 0:
                return false;
            case 1:
                Term<?> single = list.get(0);
                CSSProperty property;
                if (single instanceof TermIdent) {
                    CSSProperty identProperty = Decoder.genericPropertyRaw(GridStartEnd.class, null, (TermIdent) single);
                    if (GridStartEnd.SPAN.equals(identProperty)) {
                        return false;
                    } else if (identProperty == GridStartEnd.AUTO) {
                        property = identProperty;
                    } else {
                        property = GridStartEnd.identificator;
                    }
                } else if (single instanceof TermInteger) {
                    property = GridStartEnd.number;
                } else {
                    return false;
                }
                properties.put(propertyName, property);
                values.put(propertyName, single);
                break;
            default:
                properties.put(propertyName, GridStartEnd.component_values);
                values.put(propertyName, list);
        }
        return true;
    }

    private boolean processGridTemplate(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        d.setProperty("grid-template-areas");
        if (Decoder.genericOneIdent(GridTemplateAreas.class, d, properties)) {
            return true;
        }
        // none 
        // | grid-template-rows / grid-template-columns
        // | (<areaString> <rowLenght>?)+ (/ <colLenght>+)?
        Declaration rowsDecl = (Declaration) rf.createDeclaration().unlock();
        rowsDecl.setProperty("grid-template-rows");
        Declaration columnsDecl = (Declaration) rf.createDeclaration().unlock();
        columnsDecl.setProperty("grid-template-columns");
        boolean beforeSlash = true;
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if (t.getOperator() == Term.Operator.SLASH) {
                beforeSlash = false;
            }
            if (beforeSlash) {
                rowsDecl.add(t);
            } else {
                columnsDecl.add(t);
            }
        }
        if (processGridTemplateRows(rowsDecl, properties, values)
                && processGridTemplateColumns(columnsDecl, properties, values)) {
            return true;
        }

        TermList areasTerms = tf.createList();
        TermList rowsTerms = tf.createList();
        TermList columnsTerms = tf.createList();
        beforeSlash = true;
        boolean bracketedIdentUsed = false;
        boolean rowLengthSet = false;
        int areasInRow = 0;
        List<String[]> map = new ArrayList<>();
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if (t.getOperator() == Term.Operator.SLASH) {
                bracketedIdentUsed = false;
                beforeSlash = false;
            }
            if (t instanceof TermString) {
                String[] rowAreas = ValidationUtils.getAreas(((TermString) t).getValue());
                if (rowAreas.length == 0 || (!map.isEmpty() && rowAreas.length != areasInRow) || !beforeSlash) {
                    return false;
                }
                areasInRow = rowAreas.length;
                map.add(rowAreas);
                rowLengthSet = false;
                areasTerms.add(t);
            } else if (t instanceof TermBracketedIdents) {
                if (bracketedIdentUsed) {
                    return false;
                } else {
                    bracketedIdentUsed = true;
                    if (beforeSlash) {
                        rowsTerms.add(t);
                    } else {
                        columnsTerms.add(t);
                    }
                }
            } else if (isTermTrackBreadth(t)) {
                bracketedIdentUsed = false;
                if (beforeSlash) {
                    if (rowLengthSet) {
                        return false;
                    } else {
                        rowLengthSet = true;
                        rowsTerms.add(t);
                    }
                } else {
                    columnsTerms.add(t);
                }
            } else {
                return false;
            }
        }
        if (!ValidationUtils.containsRectangles(map.toArray(new String[0][]))) {
            return false;
        }
        properties.put("grid-template-areas", GridTemplateAreas.list_values);
        values.put("grid-template-areas", areasTerms);
        if (!rowsTerms.isEmpty()) {
            properties.put("grid-template-rows", GridTemplateRowsColumns.list_values);
            values.put("grid-template-rows", rowsTerms);
        }
        if (!columnsTerms.isEmpty()) {
            properties.put("grid-template-columns", GridTemplateRowsColumns.list_values);
            values.put("grid-template-columns", columnsTerms);
        }
        return true;
    }

    /**
     * <track-breadth> = <length> | <percentage> | <flex> | min-content |
     * max-content | auto
     */
    private boolean isTermTrackBreadth(Term<?> t) {
        if (t instanceof TermLengthOrPercent) {
            return true;
        } else if (t instanceof TermIdent) {
            CSSProperty property = Decoder.genericPropertyRaw(GridTemplateRowsColumns.class, null, (TermIdent) t);
            return property == GridTemplateRowsColumns.AUTO
                    || property == GridTemplateRowsColumns.MIN_CONTENT
                    || property == GridTemplateRowsColumns.MAX_CONTENT;
        }
        return false;
    }

    @SuppressWarnings("unused")
    private boolean processGridTemplateAreas(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        if (Decoder.genericOneIdent(GridTemplateAreas.class, d, properties)) {
            return true;
        }
        TermList list = tf.createList();
        int areasInRow = 0;
        String[][] map = new String[d.size()][];
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if (t instanceof TermString) {
                map[i] = ValidationUtils.getAreas(((TermString) t).getValue());
                if (map[i].length == 0 || (i > 0 && map[i].length != areasInRow)) {
                    return false;
                }
                areasInRow = map[i].length;
            } else {
                return false;
            }
            list.add(t);
        }
        if (!ValidationUtils.containsRectangles(map)) {
            return false;
        }
        properties.put(d.getProperty(), GridTemplateAreas.list_values);
        values.put(d.getProperty(), list);
        return true;
    }

    private boolean processGridTemplateRows(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return processGridTemplateRowsColumns(d, properties, values);
    }

    private boolean processGridTemplateColumns(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return processGridTemplateRowsColumns(d, properties, values);
    }

    private boolean processGridTemplateRowsColumns(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        if (d.isEmpty()) {
            return false;
        }
        if (Decoder.genericOneIdent(GridTemplateRowsColumns.class, d, properties)) {
            return true;
        }
        TermList list = tf.createList();
        boolean bracketedIdentUsed = false;
        boolean repeatUsed = false;
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if (t instanceof TermIdent) {
                CSSProperty property = Decoder.genericPropertyRaw(GridTemplateRowsColumns.class, null, (TermIdent) t);
                if (property == null || property == GridTemplateRowsColumns.NONE) {
                    return false;
                }
            } else if (t instanceof TermBracketedIdents) {
                if (bracketedIdentUsed) {
                    return false;
                } else {
                    bracketedIdentUsed = true;
                    list.add(t);
                    continue;
                }
            } else if (t instanceof TermFunction.Repeat && !repeatUsed) {
                repeatUsed = true;
            } else if (!(t instanceof TermLengthOrPercent)
                    && !(t instanceof TermFunction.MinMax)
                    && !(t instanceof TermFunction.FitContent)) {
                return false;
            }
            list.add(t);
            bracketedIdentUsed = false;
        }
        properties.put(d.getProperty(), GridTemplateRowsColumns.list_values);
        values.put(d.getProperty(), list);
        return true;
    }

    private boolean processGridAutoFlow(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        if (Decoder.genericOneIdent(GridAutoFlow.class, d, properties)) {
            return !GridAutoFlow.DENSE.equals(properties.get(d.getProperty()));
        }
        boolean autoFlowSet = false;
        boolean denseSet = false;
        TermList list = tf.createList();
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if (t instanceof TermIdent) {
                CSSProperty property = Decoder.genericPropertyRaw(GridAutoFlow.class, null, (TermIdent) t);
                if ((GridAutoFlow.ROW.equals(property) || GridAutoFlow.COLUMN.equals(property)) && !autoFlowSet) {
                    autoFlowSet = true;
                } else if (GridAutoFlow.DENSE.equals(property) && !denseSet) {
                    denseSet = true;
                } else {
                    return false;
                }
                list.add(t);
            } else {
                return false;
            }
        }
        properties.put(d.getProperty(), GridAutoFlow.component_values);
        values.put(d.getProperty(), list);
        return true;
    }

    private boolean processGridAutoRows(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return processGridAutoRowsOrColumns(d, properties, values);
    }

    private boolean processGridAutoColumns(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return processGridAutoRowsOrColumns(d, properties, values);
    }

    private boolean processGridAutoRowsOrColumns(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        if (d.isEmpty()) {
            return false;
        }
        if (Decoder.genericOneIdentOrLengthOrPercent(GridAutoRowsColumns.class, GridAutoRowsColumns.length, GridAutoRowsColumns.length,
                ValueRange.DISALLOW_NEGATIVE, d, properties, values)) {
            return true;
        }
        TermList list = tf.createList();
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if (t instanceof TermIdent) {
                CSSProperty property = Decoder.genericPropertyRaw(GridAutoRowsColumns.class, null, (TermIdent) t);
                if (property == null) {
                    return false;
                }
            } else if (t instanceof TermLengthOrPercent) {
                if (!isPositive(t)) {
                    return false;
                }
            } else if (t instanceof TermFunction.MinMax) {
                TermFunction.MinMax f = (TermFunction.MinMax) t;
                if (f.getMin().getLenght() != null) {
                    if (!isPositive(f.getMin().getLenght())) {
                        return false;
                    }
                }
                if (f.getMax().getLenght() != null) {
                    if (!isPositive(f.getMax().getLenght())) {
                        return false;
                    }
                }
            } else if (t instanceof TermFunction.FitContent) {
                TermFunction.FitContent f = (TermFunction.FitContent) t;
                if (!isPositive(f.getMaximum())) {
                    return false;
                }
            } else {
                return false;
            }
            list.add(t);
        }
        properties.put(d.getProperty(), GridAutoRowsColumns.list_values);
        values.put(d.getProperty(), list);
        return true;
    }

    private static boolean isPositive(Term<?> t) {
        if (t instanceof TermLengthOrPercent) {
            if (((TermLengthOrPercent) t).getValue() < 0) {
                return false;
            }
        } else if (t instanceof TermFloatValue) {
            if (((TermFloatValue) t).getValue() < 0) {
                return false;
            }
        } else if (t instanceof TermTime) {
            if (((TermTime) t).getValue() < 0) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unused")
    private boolean processAnimation(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return processPropertiesInList(new String[]{
            "animation-duration",
            "animation-timing-function",
            "animation-delay",
            "animation-iteration-count",
            "animation-direction",
            "animation-fill-mode",
            "animation-play-state",
            "animation-name"
        }, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processAnimationDelay(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        if (Decoder.genericTime(AnimationDelay.class, AnimationDelay.time, ValueRange.DISALLOW_NEGATIVE, d, properties, values)) {
            return true;
        }
        TermList list = tf.createList();
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if ((i == 0 || t.getOperator() == Operator.COMMA) && t instanceof TermTime) {
                if (!isPositive(t)) {
                    return false;
                }
            } else {
                return false;
            }
            list.add(t);
        }
        properties.put(d.getProperty(), AnimationDelay.list_values);
        values.put(d.getProperty(), list);
        return true;
    }
    
    @SuppressWarnings("unused")
    private boolean processAnimationDirection(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        if(Decoder.genericOneIdent(AnimationDirection.class, d, properties)) {
            return true;
        }
        TermList list = tf.createList();
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if ((i == 0 || t.getOperator() == Operator.COMMA) && t instanceof TermIdent) {
                CSSProperty property = Decoder.genericPropertyRaw(AnimationDirection.class, null, (TermIdent) t);
                if (property == null) {
                    return false;
                }
            } else {
                return false;
            }
            list.add(t);
        }
        properties.put(d.getProperty(), AnimationDirection.list_values);
        values.put(d.getProperty(), list);
        return true;
    }
    
    @SuppressWarnings("unused")
    private boolean processAnimationDuration(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        if (Decoder.genericTime(AnimationDuration.class, AnimationDuration.time, ValueRange.DISALLOW_NEGATIVE, d, properties, values)) {
            return true;
        }
        TermList list = tf.createList();
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if ((i == 0 || t.getOperator() == Operator.COMMA) && t instanceof TermTime) {
                if(!isPositive(t)) {
                    return false;
                }
            } else {
                return false;
            }
            list.add(t);
        }
        properties.put(d.getProperty(), AnimationDuration.list_values);
        values.put(d.getProperty(), list);
        return true;
    }
    
    @SuppressWarnings("unused")
    private boolean processAnimationFillMode(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        if(Decoder.genericOneIdent(AnimationFillMode.class, d, properties)) {
            return true;
        }
        TermList list = tf.createList();
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if ((i == 0 || t.getOperator() == Operator.COMMA) && t instanceof TermIdent) {
                CSSProperty property = Decoder.genericPropertyRaw(AnimationFillMode.class, null, (TermIdent) t);
                if (property == null) {
                    return false;
                }
            } else {
                return false;
            }
            list.add(t);
        }
        properties.put(d.getProperty(), AnimationFillMode.list_values);
        values.put(d.getProperty(), list);
        return true;
    }
    
    @SuppressWarnings("unused")
    private boolean processAnimationIterationCount(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        if(Decoder.genericOneIdentOrInteger(AnimationIterationCount.class, AnimationIterationCount.number, ValueRange.DISALLOW_NEGATIVE, d, properties, values)) {
            return true;
        }
        TermList list = tf.createList();
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if(i > 0 && t.getOperator() != Operator.COMMA) {
                return false;
            }
            if (t instanceof TermIdent) {
                CSSProperty property = Decoder.genericPropertyRaw(AnimationIterationCount.class, null, (TermIdent) t);
                if (property == null) {
                    return false;
                }
            } else if(t instanceof TermFloatValue) {
                if(!isPositive(t)) {
                    return false;
                }
            } else {
                return false;
            }
            list.add(t);
        }
        properties.put(d.getProperty(), AnimationIterationCount.list_values);
        values.put(d.getProperty(), list);
        return true;
    }
    
    @SuppressWarnings("unused")
    private boolean processAnimationName(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        if(Decoder.genericOneIdent(AnimationName.class, d, properties)) {
            return true;
        }
        TermList list = tf.createList();
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if (i > 0 && t.getOperator() != Operator.COMMA || !(t instanceof TermIdent)) {
                return false;
            }
            list.add(t);
        }
        if(list.size() == 1) {
            properties.put(d.getProperty(), AnimationName.custom_ident);
            values.put(d.getProperty(), list.get(0));
        } else {
            properties.put(d.getProperty(), AnimationName.list_values);
            values.put(d.getProperty(), list);
        }
        return true;
    }
    
    @SuppressWarnings("unused")
    private boolean processAnimationPlayState(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        if(Decoder.genericOneIdent(AnimationPlayState.class, d, properties)) {
            return true;
        }
        TermList list = tf.createList();
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if ((i == 0 || t.getOperator() == Operator.COMMA) && t instanceof TermIdent) {
                CSSProperty property = Decoder.genericPropertyRaw(AnimationPlayState.class, null, (TermIdent) t);
                if (property == null) {
                    return false;
                }
            } else {
                return false;
            }
            list.add(t);
        }
        properties.put(d.getProperty(), AnimationPlayState.list_values);
        values.put(d.getProperty(), list);
        return true;
    }

    @SuppressWarnings("unused")
    private boolean processAnimationTimingFunction(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        if(Decoder.genericOneIdent(AnimationTimingFunction.class, d, properties)) {
            return true;
        }
        TermList list = tf.createList();
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if(i > 0 && t.getOperator() != Operator.COMMA) {
                return false;
            }
            if (t instanceof TermIdent) {
                CSSProperty property = Decoder.genericPropertyRaw(AnimationTimingFunction.class, null, (TermIdent) t);
                if (property == null) {
                    return false;
                }
            } else if (!(t instanceof TermFunction.TimingFunction)) {
                return false;
            }
            list.add(t);
        }
        if(list.size() == 1) {
            properties.put(d.getProperty(), AnimationTimingFunction.timing_function);
            values.put(d.getProperty(), list.get(0));
        } else {
            properties.put(d.getProperty(), AnimationTimingFunction.list_values);
            values.put(d.getProperty(), list);
        }
        return true;
    }
    
    private boolean processPropertiesInList(String[] propertyList, Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        Declaration subDeclaration = (Declaration) rf.createDeclaration().unlock();
        TermList[] termLists = new TermList[propertyList.length];
        for (int i = 0; i < termLists.length; i++) termLists[i] = tf.createList();
        boolean[] propertySet = new boolean[propertyList.length];
        Arrays.fill(propertySet, false);

        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            subDeclaration.add(t);
            if (t.getOperator() == Operator.COMMA) {
                Arrays.fill(propertySet, false);
            }
            for (int propertyIndex = 0; propertyIndex <= propertyList.length; propertyIndex++) {
                if (propertyIndex == propertyList.length) {
                    return false;
                }
                if (propertySet[propertyIndex]) {
                    continue;
                }
                subDeclaration.setProperty(propertyList[propertyIndex]);
                if (parseDeclaration(subDeclaration, properties, values)) {
                    propertySet[propertyIndex] = true;
                    t.setOperator(termLists[propertyIndex].isEmpty() ? null : Operator.COMMA);
                    termLists[propertyIndex].add(t);
                    break;
                }
            }
            subDeclaration.clear();
        }

        for (int propertyIndex = 0; propertyIndex < propertyList.length; propertyIndex++) {
            subDeclaration.setProperty(propertyList[propertyIndex]);
            subDeclaration.addAll(termLists[propertyIndex]);
            if (!subDeclaration.isEmpty() && !parseDeclaration(subDeclaration, properties, values)) {
                return false;
            }
            subDeclaration.clear();
        }
        return true;
    }
    
    @SuppressWarnings("unused")
    private boolean processTransition(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        return processPropertiesInList(new String[]{
            "transition-duration",
            "transition-delay",
            "transition-timing-function",
            "transition-property"
        }, d, properties, values);
    }
    
    @SuppressWarnings("unused")
    private boolean processTransitionDelay(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        if (Decoder.genericTime(TransitionDelay.class, TransitionDelay.time, ValueRange.DISALLOW_NEGATIVE, d, properties, values)) {
            return true;
        }
        TermList list = tf.createList();
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if ((i == 0 || t.getOperator() == Operator.COMMA) && t instanceof TermTime) {
                if (!isPositive(t)) {
                    return false;
                }
            } else {
                return false;
            }
            list.add(t);
        }
        properties.put(d.getProperty(), TransitionDelay.list_values);
        values.put(d.getProperty(), list);
        return true;
    }
    
    @SuppressWarnings("unused")
    private boolean processTransitionDuration(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        if (Decoder.genericTime(TransitionDuration.class, TransitionDuration.time, ValueRange.DISALLOW_NEGATIVE, d, properties, values)) {
            return true;
        }
        TermList list = tf.createList();
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if ((i == 0 || t.getOperator() == Operator.COMMA) && t instanceof TermTime) {
                if(!isPositive(t)) {
                    return false;
                }
            } else {
                return false;
            }
            list.add(t);
        }
        properties.put(d.getProperty(), TransitionDuration.list_values);
        values.put(d.getProperty(), list);
        return true;
    }
    
    @SuppressWarnings("unused")
    private boolean processTransitionProperty(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        if(Decoder.genericOneIdent(TransitionProperty.class, d, properties)) {
            return true;
        }
        TermList list = tf.createList();
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if ((i == 0 || t.getOperator() == Operator.COMMA) && t instanceof TermIdent) {
                CSSProperty property = Decoder.genericPropertyRaw(TransitionProperty.class, null, (TermIdent) t);
                if (property == TransitionProperty.NONE) {
                    return false;
                }
            } else {
                return false;
            }
            list.add(t);
        }
        if(list.size() == 1) {
            properties.put(d.getProperty(), TransitionProperty.custom_ident);
            values.put(d.getProperty(), list.get(0));
        } else {
            properties.put(d.getProperty(), TransitionProperty.list_values);
            values.put(d.getProperty(), list);
        }
        return true;
    }
    
    @SuppressWarnings("unused")
    private boolean processTransitionTimingFunction(Declaration d, Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        if(Decoder.genericOneIdent(TransitionTimingFunction.class, d, properties)) {
            return true;
        }
        TermList list = tf.createList();
        for (int i = 0; i < d.size(); i++) {
            Term<?> t = d.get(i);
            if(i > 0 && t.getOperator() != Operator.COMMA) {
                return false;
            }
            if (t instanceof TermIdent) {
                CSSProperty property = Decoder.genericPropertyRaw(TransitionTimingFunction.class, null, (TermIdent) t);
                if (property == null) {
                    return false;
                }
            } else if (!(t instanceof TermFunction.TimingFunction)) {
                return false;
            }
            list.add(t);
        }
        if(list.size() == 1) {
            properties.put(d.getProperty(), TransitionTimingFunction.timing_function);
            values.put(d.getProperty(), list.get(0));
        } else {
            properties.put(d.getProperty(), TransitionTimingFunction.list_values);
            values.put(d.getProperty(), list);
        }
        return true;
    }

}
