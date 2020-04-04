/**
 * 
 */
package cz.vutbr.web.domassign.decode;

import java.util.Map;

import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermInteger;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermNumber;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.css.CSSProperty.FlexBasis;
import cz.vutbr.web.css.CSSProperty.FlexGrow;
import cz.vutbr.web.css.CSSProperty.FlexShrink;

/**
 * Variator for flex. Grammar:
 * 
 * <pre>
 * [ <'flex-grow'> <'flex-shrink'>? || <'flex-basis'> ]
 * | none
 * | inherit
 * 
 * @author burgetr
 */
public class FlexVariator extends Variator {

    public static final int GROW = 0;
    public static final int SHRINK = 1;
    public static final int BASIS = 2;

    public FlexVariator() {
        super(3);
        names.add("flex-grow");
        types.add(FlexGrow.class);
        names.add("flex-shrink");
        types.add(FlexShrink.class);
        names.add("flex-basis");
        types.add(FlexBasis.class);
    }

    @Override
    protected boolean variant(int v, IntegerRef iteration,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

        int i = iteration.get();

        switch (v) {
        case GROW:
            return genericTerm(TermNumber.class, terms.get(i), names.get(GROW),
                            FlexGrow.number, ValueRange.DISALLOW_NEGATIVE,
                            properties, values)
                    || genericTerm(TermInteger.class, terms.get(i), names.get(GROW),
                            FlexGrow.number, ValueRange.DISALLOW_NEGATIVE,
                            properties, values);
        case SHRINK:
            return genericTerm(TermNumber.class, terms.get(i), names.get(SHRINK),
                            FlexShrink.number, ValueRange.DISALLOW_NEGATIVE,
                            properties, values)
                    || genericTerm(TermInteger.class, terms.get(i), names.get(SHRINK),
                            FlexShrink.number, ValueRange.DISALLOW_NEGATIVE,
                            properties, values);
        case BASIS:
            return genericTermIdent(types.get(BASIS), terms.get(i),
                    AVOID_INH, names.get(BASIS), properties)
                    || genericTerm(TermPercent.class, terms.get(i), names.get(BASIS),
                            FlexBasis.percentage, ValueRange.DISALLOW_NEGATIVE,
                            properties, values)
                    || genericTerm(TermLength.class, terms.get(i), names.get(BASIS),
                            FlexBasis.length, ValueRange.DISALLOW_NEGATIVE,
                            properties, values);
        default:
            return false;
        }
    }
    
    @Override
    protected boolean variantCondition(int variant, IntegerRef iteration)
    {
        switch (variant)
        {
            case SHRINK:
                return variantPassed[GROW];
            default:
                return true;
        }
    }       
    
    @Override
    public boolean vary(Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

        if (terms.size() == 1 && terms.get(0) instanceof TermIdent) {
            //check for flex: none
            if (checkInherit(ALL_VARIANTS, terms.get(0), properties))
                return true;
            if (terms.get(0).equals(tf.createIdent("none"))) {
                // none should compute to: 0 0 auto
                values.put(names.get(SHRINK), tf.createNumber(0.0f)); //override the default for shrink to 0
                return true;
            }
        }
        boolean ret = super.vary(properties, values);
        
        //change the default value for flex-shrink to 1 when flex: <basis> is used
        if (variantPassed[BASIS] && !variantPassed[GROW] && properties.get(names.get(BASIS)) == FlexBasis.AUTO) {
            values.put(names.get(GROW), tf.createNumber(1.0f));
        }
        //change the default value for flex-basis to 0 when flex: <positive_number> is used
        if (variantPassed[GROW] && !variantPassed[BASIS]) {
            properties.put(names.get(BASIS), FlexBasis.length);
            values.put(names.get(BASIS), tf.createLength(0.0f));
        }
        
        return ret;
    }
}
