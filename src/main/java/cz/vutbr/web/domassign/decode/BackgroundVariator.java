/**
 * 
 */
package cz.vutbr.web.domassign.decode;

import java.util.EnumSet;
import java.util.Map;

import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.css.TermURI;
import cz.vutbr.web.css.CSSProperty.BackgroundAttachment;
import cz.vutbr.web.css.CSSProperty.BackgroundColor;
import cz.vutbr.web.css.CSSProperty.BackgroundImage;
import cz.vutbr.web.css.CSSProperty.BackgroundOrigin;
import cz.vutbr.web.css.CSSProperty.BackgroundPosition;
import cz.vutbr.web.css.CSSProperty.BackgroundRepeat;
import cz.vutbr.web.css.CSSProperty.BackgroundSize;
import cz.vutbr.web.css.Term.Operator;

/**
 * Background variator.
 * Grammar:
 * <pre>
 * [ <'background-color'> || <'background-image'> 
 *      || <'background-repeat'> || <'background-attachment'> 
 *      || <'background-position'> [ / <background-size> ]?
 * ] 
 * | inherit
 * </pre>
 * 
 * @author kapy
 */
public class BackgroundVariator extends Variator {

    public static final int COLOR = 0;
    public static final int IMAGE = 1;
    public static final int REPEAT = 2;
    public static final int ATTACHMENT = 3;
    public static final int POSITION = 4;
    public static final int SIZE = 5;
    public static final int ORIGIN = 6;

    public BackgroundVariator() {
        super(7);
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
        names.add("background-origin");
        types.add(BackgroundOrigin.class);
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
                    || genericTerm(TermURI.class, terms.get(i), names.get(IMAGE),
                            BackgroundImage.uri, ValueRange.ALLOW_ALL,
                            properties, values)
                    || genericTerm(TermFunction.Gradient.class, terms.get(i), names.get(IMAGE),
                            BackgroundImage.gradient, ValueRange.ALLOW_ALL,
                            properties, values);
        case REPEAT:
            return genericTermIdent(types.get(REPEAT), terms.get(i),
                    AVOID_INH, names.get(REPEAT), properties);
        case ORIGIN:
            return genericTermIdent(types.get(ORIGIN), terms.get(i),
                    AVOID_INH, names.get(ORIGIN), properties);
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
