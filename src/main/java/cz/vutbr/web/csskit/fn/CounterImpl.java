/**
 * 
 */
package cz.vutbr.web.csskit.fn;

import java.util.ArrayList;
import java.util.List;

import cz.vutbr.web.css.CSSProperty.ListStyleType;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

/**
 * @author burgetr
 *
 */
public class CounterImpl extends TermFunctionImpl implements TermFunction.Counter {

    public static List<String> allowedStyles;
    static {
        allowedStyles = new ArrayList<>(ListStyleType.values().length - 4);
        for (ListStyleType item : ListStyleType.values()) {
            if (item != ListStyleType.INHERIT && item != ListStyleType.INITIAL
                    && item != ListStyleType.UNSET && item != ListStyleType.NONE) {
                allowedStyles.add(item.toString());
            }
        }
    }
    
    private TermIdent name;
    private TermIdent style;
    
    public CounterImpl() {
        setValid(false);
    }

    @Override
    public TermIdent getName() {
        return name;
    }

    @Override
    public TermIdent getStyle() {
        return style;
    }
    
    @Override
    public TermList setValue(List<Term<?>> value) {
        super.setValue(value);
        List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, true);
        if (args != null && (args.size() == 1 || args.size() == 2)) {
            //check for name
            if (args.get(0) instanceof TermIdent) {
                name = (TermIdent) args.get(0);
                setValid(true);
            }
            //an optional style
            if (args.size() == 2) {
                if (args.get(1) instanceof TermIdent) {
                    style = (TermIdent) args.get(1);
                    if (!allowedStyles.contains(style.getValue().toLowerCase())) {
                        setValid(false); //unknown style
                    }
                } else {
                    setValid(false);
                }
            }
        }
        return this;
    }
    
}
