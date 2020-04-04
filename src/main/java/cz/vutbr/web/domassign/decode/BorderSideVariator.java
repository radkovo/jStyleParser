/**
 * 
 */
package cz.vutbr.web.domassign.decode;

import java.util.Map;

import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.CSSProperty.BorderColor;
import cz.vutbr.web.css.CSSProperty.BorderStyle;
import cz.vutbr.web.css.CSSProperty.BorderWidth;

/**
 * Variator for border side.
 * Grammar:
 * <pre>
 * [ <border-width> || <border-style> || <'border-top-color'> ] 
 * | inherit
 * </pre>
 * 
 * @author kapy
 * 
 */
public class BorderSideVariator extends Variator {

    public static final int COLOR = 0;
    public static final int STYLE = 1;
    public static final int WIDTH = 2;

    public BorderSideVariator(String side) {
        super(3);
        names.add("border-" + side + "-color");
        types.add(BorderColor.class);
        names.add("border-" + side + "-style");
        types.add(BorderStyle.class);
        names.add("border-" + side + "-width");
        types.add(BorderWidth.class);
    }

    @Override
    protected boolean variant(int v, IntegerRef iteration,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

        // we won't use multivalue functionallity
        int i = iteration.get();

        switch (v) {
        case COLOR:
            // process color
            return genericTermIdent(types.get(COLOR), terms.get(i),
                    AVOID_INH, names.get(COLOR), properties)
                    || genericTermColor(terms.get(i), names.get(COLOR),
                            BorderColor.color, properties, values);
        case STYLE:
            // process style
            return genericTermIdent(types.get(STYLE), terms.get(i),
                    AVOID_INH, names.get(STYLE), properties);
        case WIDTH:
            // process width
            return genericTermIdent(types.get(WIDTH), terms.get(i),
                    AVOID_INH, names.get(WIDTH), properties)
                    || genericTermLength(terms.get(i), names.get(WIDTH),
                            BorderWidth.length, ValueRange.DISALLOW_NEGATIVE,
                            properties, values);
        default:
            return false;
        }

    }
}
