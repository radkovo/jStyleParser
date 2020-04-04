/**
 * 
 */
package cz.vutbr.web.domassign.decode;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermInteger;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermNumber;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.css.TermString;
import cz.vutbr.web.css.CSSProperty.Font;
import cz.vutbr.web.css.CSSProperty.FontFamily;
import cz.vutbr.web.css.CSSProperty.FontSize;
import cz.vutbr.web.css.CSSProperty.FontStyle;
import cz.vutbr.web.css.CSSProperty.FontVariant;
import cz.vutbr.web.css.CSSProperty.FontWeight;
import cz.vutbr.web.css.CSSProperty.LineHeight;
import cz.vutbr.web.css.Term.Operator;

/**
 * Font variator:
 * Grammar:
 * <pre>
 *  [ 
 *      [ <'font-style'> || <'font-variant'> || <'font-weight'> ]? 
 *      <'font-size'> 
 *      [ / <'line-height'> ]? 
 *      <'font-family'> 
 *  ] 
 *  | caption | icon | menu | message-box 
 *  | small-caption | status-bar | inherit
 * </pre>
 * 
 * @author kapy
 *
 */
public class FontVariator extends Variator {

    public static final int STYLE = 0;
    public static final int VARIANT = 1;
    public static final int WEIGHT = 2;
    public static final int SIZE = 3;
    public static final int LINE_HEIGHT = 4;
    public static final int FAMILY = 5;

    public FontVariator() {
        super(6);
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
                            //log.warn("Not found numeric values for FontWeight: " + "numeric_ " + value.intValue());
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
                    || genericTermLength(terms.get(i), names.get(SIZE),
                            FontSize.length, ValueRange.DISALLOW_NEGATIVE,
                            properties, values)
                    || genericTerm(TermPercent.class, terms.get(i), names.get(SIZE),
                            FontSize.percentage, ValueRange.DISALLOW_NEGATIVE,
                            properties, values);
        case LINE_HEIGHT:
            return genericTermIdent(types.get(LINE_HEIGHT), terms.get(i),
                    AVOID_INH, names.get(LINE_HEIGHT), properties)
                    || genericTerm(TermNumber.class, terms.get(i), names.get(LINE_HEIGHT),
                            LineHeight.number, ValueRange.DISALLOW_NEGATIVE,
                            properties, values)
                    || genericTerm(TermInteger.class, terms.get(i), names.get(LINE_HEIGHT),
                            LineHeight.number, ValueRange.DISALLOW_NEGATIVE,
                            properties, values)
                    || genericTerm(TermPercent.class, terms.get(i), names.get(LINE_HEIGHT),
                            LineHeight.percentage, ValueRange.DISALLOW_NEGATIVE,
                            properties, values)
                    || genericTerm(TermLength.class, terms.get(i), names.get(LINE_HEIGHT),
                            LineHeight.length, ValueRange.DISALLOW_NEGATIVE,
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
                        && t.getOperator() != Operator.COMMA
                        && t.getOperator() != Operator.SLASH) {
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
