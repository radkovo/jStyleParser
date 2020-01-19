/**
 * GenericFilterFunction.java
 *
 * Created on 18. 5. 2018, 12:37:05 by burgetr
 */
package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.csskit.TermFunctionImpl;

/**
 * 
 * @author burgetr
 */
public class GenericFilterFunction extends TermFunctionImpl {
    
    private float amount;
    
    public GenericFilterFunction() {
        setValid(false);
    }

    public float getAmount() {
        return amount;
    }

    @Override
    public TermList setValue(List<Term<?>> value)
    {
        super.setValue(value);
        List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
        if (args != null && args.size() == 1)
        {
            final Term<?> arg = args.get(0);
            if (isNumberArg(arg)) {
                amount = getNumberArg(args.get(0));
                setValid(true);
            } else if (arg instanceof TermPercent) {
                amount = ((TermPercent) arg).getValue() / 100.0f;
                setValid(true);
            }
        }
        return this;
    }
    
}
