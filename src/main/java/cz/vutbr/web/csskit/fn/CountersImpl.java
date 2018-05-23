/**
 * 
 */
package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.CSSProperty.ListStyleType;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermString;
import cz.vutbr.web.csskit.TermFunctionImpl;

/**
 * @author burgetr
 *
 */
public class CountersImpl extends TermFunctionImpl implements TermFunction.Counters {
    
    private String name;
    private ListStyleType style;
    private String separator;
    
    public CountersImpl() {
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
    public String getSeparator() {
        return separator;
    }

    @Override
    public TermList setValue(List<Term<?>> value) {
        super.setValue(value);
        List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, true);
        if (args != null && (args.size() == 2 || args.size() == 3)) {
            //check for name and separator
            if (args.get(0) instanceof TermIdent && args.get(1) instanceof TermString) {
                name = ((TermIdent) args.get(0)).getValue();
                separator = ((TermString) args.get(1)).getValue();
                setValid(true);
            }
            //an optional style
            if (args.size() == 3) {
                if (args.get(2) instanceof TermIdent) {
                    final String styleString = ((TermIdent) args.get(2)).getValue();
                    style = CounterImpl.allowedStyles.get(styleString.toLowerCase());
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
