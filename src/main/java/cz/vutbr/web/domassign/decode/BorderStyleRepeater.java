/**
 * 
 */
package cz.vutbr.web.domassign.decode;

import java.util.Map;

import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.CSSProperty.BorderStyle;

/**
 * Border style repeater
 * 
 * @author kapy
 * 
 */
public class BorderStyleRepeater extends Repeater {

    public BorderStyleRepeater() {
        super(4);
        this.type = BorderStyle.class;
        names.add("border-top-style");
        names.add("border-right-style");
        names.add("border-bottom-style");
        names.add("border-left-style");
    }

    @Override
    protected boolean operation(int i, Map<String, CSSProperty> properties,
            Map<String, Term<?>> values) {

        return genericTermIdent(BorderStyle.class, terms.get(i), ALLOW_INH, names.get(i), properties);
    }
}
