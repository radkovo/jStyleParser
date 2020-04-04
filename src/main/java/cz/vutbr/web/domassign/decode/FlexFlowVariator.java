/**
 * 
 */
package cz.vutbr.web.domassign.decode;

import java.util.Map;

import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.CSSProperty.FlexDirection;
import cz.vutbr.web.css.CSSProperty.FlexWrap;

/**
 * Variator for flex-flow. Grammar:
 * 
 * <pre>
 * <'flex-direction'> || <'flex-wrap'>
 * | inherit
 * 
 * @author burgetr
 */
public class FlexFlowVariator extends Variator {

    public static final int DIRECTION = 0;
    public static final int WRAP = 1;

    public FlexFlowVariator() {
        super(2);
        names.add("flex-direction");
        types.add(FlexDirection.class);
        names.add("flex-wrap");
        types.add(FlexWrap.class);
    }

    @Override
    protected boolean variant(int v, IntegerRef iteration,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

        int i = iteration.get();

        switch (v) {
        case DIRECTION:
            return genericTermIdent(FlexDirection.class, terms.get(i),
                    AVOID_INH, names.get(DIRECTION), properties);
        case WRAP:
            return genericTermIdent(FlexWrap.class, terms.get(i),
                    AVOID_INH, names.get(WRAP), properties);
        default:
            return false;
        }
    }
}
