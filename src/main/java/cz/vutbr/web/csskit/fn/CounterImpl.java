/**
 * 
 */
package cz.vutbr.web.csskit.fn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static Map<String, ListStyleType> allowedStyles;
    static {
        allowedStyles = new HashMap<>(ListStyleType.values().length - 4);
        for (ListStyleType item : ListStyleType.values()) {
            if (item != ListStyleType.INHERIT && item != ListStyleType.INITIAL
                    && item != ListStyleType.UNSET && item != ListStyleType.NONE) {
                allowedStyles.put(item.toString(), item);
            }
        }
    }
    
    private String name;
    private ListStyleType style;
    
    public CounterImpl() {
        setValid(false);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ListStyleType getStyle() {
        return style;
    }
    
    @Override
    public TermList setValue(List<Term<?>> value) {
        super.setValue(value);
        List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, true);
        if (args != null && (args.size() == 1 || args.size() == 2)) {
            //check for name
            if (args.get(0) instanceof TermIdent) {
                name = ((TermIdent) args.get(0)).getValue();
                setValid(true);
            }
            //an optional style
            if (args.size() == 2) {
                if (args.get(1) instanceof TermIdent) {
                    final String styleString = ((TermIdent) args.get(1)).getValue();
                    style = allowedStyles.get(styleString.toLowerCase());
                    if (style == null) {
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
