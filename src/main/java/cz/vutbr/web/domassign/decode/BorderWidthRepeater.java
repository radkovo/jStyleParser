/**
 * 
 */
package cz.vutbr.web.domassign.decode;

import java.util.Map;

import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.CSSProperty.BorderWidth;

/**
 * Border width repeater
 * 
 * @author kapy
 * 
 */
public class BorderWidthRepeater extends Repeater {

    public BorderWidthRepeater() {
        super(4);
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
                || genericTermLength(terms.get(i), names.get(i), BorderWidth.length, ValueRange.DISALLOW_NEGATIVE, properties, values);
    }
}
