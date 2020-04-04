/**
 * 
 */
package cz.vutbr.web.domassign.decode;

import java.util.Map;

import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.CSSProperty.BorderColor;

/**
 * Border color repeater
 * 
 * @author kapy
 * 
 */
public class BorderColorRepeater extends Repeater {

    public BorderColorRepeater() {
        super(4);
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
                || genericTerm(TermColor.class, terms.get(i), names.get(i), BorderColor.color, ValueRange.ALLOW_ALL, properties, values);
    }
}
