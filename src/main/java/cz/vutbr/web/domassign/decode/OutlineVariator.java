/**
 * 
 */
package cz.vutbr.web.domassign.decode;

import java.util.Map;

import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.CSSProperty.OutlineColor;
import cz.vutbr.web.css.CSSProperty.OutlineStyle;
import cz.vutbr.web.css.CSSProperty.OutlineWidth;

/**
 * Outline variator Grammar:
 * 
 * <pre>
 * [ 'outline-color' || 'outline-style' || 'outline-width' ] 
 * | inherit
 * </pre>
 * 
 * @author kapy
 * 
 */
public class OutlineVariator extends Variator {

    public static final int COLOR = 0;
    public static final int STYLE = 1;
    public static final int WIDTH = 2;

    public OutlineVariator() {
        super(3);
        names.add("outline-color");
        types.add(OutlineColor.class);
        names.add("outline-style");
        types.add(OutlineStyle.class);
        names.add("outline-width");
        types.add(OutlineWidth.class);
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
                            OutlineColor.color, properties, values);
        case STYLE:
            // process style
            return genericTermIdent(types.get(STYLE), terms.get(i),
                    AVOID_INH, names.get(STYLE), properties);
        case WIDTH:
            // process width
            return genericTermIdent(types.get(WIDTH), terms.get(i),
                    AVOID_INH, names.get(WIDTH), properties)
                    || genericTermLength(terms.get(i), names.get(WIDTH),
                            OutlineWidth.length, ValueRange.DISALLOW_NEGATIVE,
                            properties, values);
        default:
            return false;
        }
    }
}
