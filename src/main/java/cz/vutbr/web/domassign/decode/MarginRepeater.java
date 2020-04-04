/**
 * 
 */
package cz.vutbr.web.domassign.decode;

import java.util.Map;

import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.css.CSSProperty.Margin;

/**
 * Margin repeater
 * 
 * @author kapy
 * 
 */
public class MarginRepeater extends Repeater {

    public MarginRepeater() {
        super(4);
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
                        names.get(i), Margin.length, ValueRange.ALLOW_ALL, properties,
                        values)
                || genericTerm(TermPercent.class, terms.get(i), names
                        .get(i), Margin.percentage, ValueRange.ALLOW_ALL, properties,
                        values);
    }
}
