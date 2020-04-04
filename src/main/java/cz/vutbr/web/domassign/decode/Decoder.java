/**
 * 
 */
package cz.vutbr.web.domassign.decode;

import java.util.Map;
import java.util.Set;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermFloatValue;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermInteger;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermNumber;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.css.TermTime;

/**
 * A base class for repeaters and variators.
 * 
 * @author burgetr
 */
public class Decoder
{

    /**
     * A hint about the allowed value range when processing numeric values. 
     */
    public enum ValueRange {
        /** Allow all values */
        ALLOW_ALL,
        /** Treat negative values as invalid */
        DISALLOW_NEGATIVE,
        /** Truncate negative values to zero */
        TRUNCATE_NEGATIVE,
        /** Treat zero as invalid */
        DISALLOW_ZERO
    }
    
    /**
     * Inherit acceptance flags
     */
    public static final boolean AVOID_INH = true;
    public static final boolean ALLOW_INH = false;
    
    public static final TermFactory tf = CSSFactory.getTermFactory();

    
    /**
     * Converts TermIdent into CSSProperty using intersection set.
     * CSSProperty.Translator is used.
     * 
     * @param <T>
     *            Subclass of CSSProperty to be returned
     * @param type
     *            Class of property to be used to retrieve value
     * @param intersection
     *            Intersection set or <code>null</code> if no intersection is
     *            used
     * @param term
     *            TermIdent to be transferred to property
     * @return CSSProperty of type &lt;T&gt; or <code>null</code>
     */
    public static <T extends CSSProperty> T genericPropertyRaw(Class<T> type,
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
     *            Enum &amp; CSSProperty limitation
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
    public static <T extends CSSProperty> boolean genericProperty(Class<T> type,
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
    public static <T extends CSSProperty> boolean genericTermIdent(Class<T> type,
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
    public static <T extends CSSProperty> boolean genericTermColor(Term<?> term,
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
    public static <T extends CSSProperty> boolean genericTermLength(Term<?> term,
            String propertyName, T lengthIdentification, ValueRange range,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

        if (term instanceof TermInteger  && ((TermInteger) term).getUnit().equals(TermNumber.Unit.none)) {
            if (CSSFactory.getImplyPixelLength() || ((TermInteger) term).getValue() == 0) { //0 is always allowed with no units
                // convert to length with units of px
                TermLength tl = tf.createLength(((TermInteger) term).getValue(), TermNumber.Unit.px);
                return genericTerm(TermLength.class, tl, propertyName, lengthIdentification, range, properties, values);
            } else {
                return false;
            }
        }
        else if (term instanceof TermLength) { 
            return genericTerm(TermLength.class, term, propertyName, lengthIdentification, range, properties, values); 
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
    public static <T extends CSSProperty> boolean genericTerm(
            Class<? extends Term<?>> termType, Term<?> term,
            String propertyName, T typeIdentification, ValueRange range,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

        // check type
        if (termType.isInstance(term)) {
            // sanity check
            if (range != ValueRange.ALLOW_ALL) {
                // check for integer
                if (term.getValue() instanceof Integer) {
                    final Integer zero = 0;
                    int result = zero.compareTo((Integer) term.getValue());
                    if (result > 0) {
                        // return false is also possibility
                        // but we will change to zero
                        if (range == ValueRange.TRUNCATE_NEGATIVE)
                            ((TermInteger) term).setZero();
                        else
                            return false;
                    } else if(result == 0) {
                        if(range == ValueRange.DISALLOW_ZERO) {
                            return false;
                        }
                    }
                }
                // check for float
                else if (term.getValue() instanceof java.lang.Float) {
                    final java.lang.Float zero = 0f;
                    int result = zero.compareTo((java.lang.Float) term.getValue());
                    if (result > 0) {
                        // return false is also possibility
                        // but we will change to zero
                        if (range == ValueRange.TRUNCATE_NEGATIVE)
                            ((TermFloatValue) term).setZero();
                        else
                            return false;
                    } else if(result == 0) {
                        if(range == ValueRange.DISALLOW_ZERO) {
                            return false;
                        }
                    }
                }
            }
            // passed both type check and range check,
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
    public static <T extends CSSProperty> boolean genericOneIdent(Class<T> type,
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
    public static <T extends CSSProperty> boolean genericOneIdentOrColor(
            Class<T> type, T colorIdentification, Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

        if (d.size() != 1)
            return false;

        return genericTermIdent(type, d.get(0), ALLOW_INH, d.getProperty(),
                properties)
                || genericTermColor(d.get(0), d.getProperty(),
                        colorIdentification, properties, values);
    }

    public static <T extends CSSProperty> boolean genericOneIdentOrInteger(
            Class<T> type, T integerIdentification, ValueRange range,
            Declaration d, Map<String, CSSProperty> properties,
            Map<String, Term<?>> values) {

        if (d.size() != 1)
            return false;

        return genericTermIdent(type, d.get(0), ALLOW_INH, d.getProperty(),
                properties)
                || genericTerm(TermInteger.class, d.get(0), d.getProperty(),
                        integerIdentification, range, properties, values);
    }

    public static <T extends CSSProperty> boolean genericOneIdentOrIntegerOrNumber(
            Class<T> type, T integerIdentification, T numberIdentification, ValueRange range,
            Declaration d, Map<String, CSSProperty> properties,
            Map<String, Term<?>> values) {

        if (d.size() != 1)
            return false;

        return genericTermIdent(type, d.get(0), ALLOW_INH, d.getProperty(), properties)
                || genericTerm(TermInteger.class, d.get(0), d.getProperty(),
                        integerIdentification, range, properties, values)
                || genericTerm(TermNumber.class, d.get(0), d.getProperty(),
                        numberIdentification, range, properties, values);
    }
    
    public static <T extends CSSProperty> boolean genericOneIdentOrLength(
            Class<T> type, T lengthIdentification, ValueRange range,
            Declaration d, Map<String, CSSProperty> properties,
            Map<String, Term<?>> values) {

        if (d.size() != 1)
            return false;

        return genericTermIdent(type, d.get(0), ALLOW_INH, d.getProperty(),
                properties)
                || genericTermLength(d.get(0), d.getProperty(),
                        lengthIdentification, range, properties, values);
    }
    
    public static <T extends CSSProperty> boolean genericTime(
            Class<T> type, T integerIdentification, ValueRange range,
            Declaration d, Map<String, CSSProperty> properties,
            Map<String, Term<?>> values) {
        if (d.size() != 1)
            return false;
        
        Term<?> term = d.get(0);
        if (term instanceof TermIdent) {
            T property = genericPropertyRaw(type, null, (TermIdent) term);
            if (!property.equalsInherit())
                return false;
            else
            {
                properties.put(d.getProperty(), property);
                return true;
            }
        }
        return genericTerm(TermTime.class, term, d.getProperty(), integerIdentification, range, properties, values);
    }
    
    public static <T extends CSSProperty> boolean genericInteger(
            Class<T> type, T integerIdentification, ValueRange range,
            Declaration d, Map<String, CSSProperty> properties,
            Map<String, Term<?>> values) {

        if (d.size() != 1)
            return false;
        
        Term<?> term = d.get(0);
        if (term instanceof TermIdent)
        {
            T property = genericPropertyRaw(type, null, (TermIdent) term);
            if (!property.equalsInherit())
                return false;
            else
            {
                properties.put(d.getProperty(), property);
                return true;
            }
        }
        else
        {
            return genericTerm(TermInteger.class, term, d.getProperty(), integerIdentification, range, properties, values);
        }
    }
    
    public static <T extends CSSProperty> boolean genericIntegerOrLength(
            Class<T> type, T integerIdentification, T lengthIdentification, ValueRange range,
            Declaration d, Map<String, CSSProperty> properties,
            Map<String, Term<?>> values) {

        if (d.size() != 1)
            return false;
        
        Term<?> term = d.get(0);
        if (term instanceof TermIdent)
        {
            T property = genericPropertyRaw(type, null, (TermIdent) term);
            if (!property.equalsInherit())
                return false;
            else
            {
                properties.put(d.getProperty(), property);
                return true;
            }
        }
        else
        {
            return genericTerm(TermInteger.class, term, d.getProperty(),
                            integerIdentification, range, properties, values)
                    || genericTermLength(term, d.getProperty(), lengthIdentification, range, properties, values);
        }
    }
    
    public static <T extends Enum<T> & CSSProperty> boolean genericOneIdentOrLengthOrPercent(
            Class<T> type, T lengthIdentification, T percentIdentification,
            ValueRange range, Declaration d, Map<String, CSSProperty> properties,
            Map<String, Term<?>> values) {

        if (d.size() != 1)
            return false;

        return genericTermIdent(type, d.get(0), ALLOW_INH, d.getProperty(),
                properties)
                || genericTermLength(d.get(0), d.getProperty(),
                        lengthIdentification, range, properties, values)
                || genericTerm(TermPercent.class, d.get(0), d.getProperty(),
                        percentIdentification, range, properties, values);
    }

    public static <T extends Enum<T> & CSSProperty> boolean genericTwoIdentsOrLengthsOrPercents(
            Class<T> type, T listIdentification,
            ValueRange range, Declaration d, Map<String, CSSProperty> properties,
            Map<String, Term<?>> values) {

        if (d.size() == 1) {
            Term<?> term = d.get(0);
            String propertyName = d.getProperty();
            // is it identifier or length ?
            if (genericTermIdent(type, term, ALLOW_INH, propertyName, properties)
                || genericTermLength(term, propertyName,
                        listIdentification, range, properties, values)
                || genericTerm(TermPercent.class, term, propertyName,
                        listIdentification, range, properties, values)) {
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
                            listIdentification, range, properties, values)
                    || genericTerm(TermPercent.class, term1, propertyName,
                            listIdentification, range, properties, values))
                 && (genericTermLength(term2, propertyName,
                            listIdentification, range, properties, values)
                    || genericTerm(TermPercent.class, term2, propertyName,
                            listIdentification, range, properties, values))) {
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

    
}
