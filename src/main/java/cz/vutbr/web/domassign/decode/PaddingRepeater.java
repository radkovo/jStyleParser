/**
 * 
 */
package cz.vutbr.web.domassign.decode;

import java.util.Map;

import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.css.CSSProperty.Padding;

/**
 * Padding repeater
 * 
 * @author kapy
 * 
 */
public class PaddingRepeater extends Repeater {

    public PaddingRepeater() {
        super(4);
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
                        names.get(i), Padding.length, ValueRange.DISALLOW_NEGATIVE, properties,
                        values)
                || genericTerm(TermPercent.class, terms.get(i), names
                        .get(i), Padding.percentage, ValueRange.DISALLOW_NEGATIVE, properties,
                        values);
    }
}
