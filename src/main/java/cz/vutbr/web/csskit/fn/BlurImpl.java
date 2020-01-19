package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class BlurImpl extends TermFunctionImpl implements TermFunction.Blur {
    
    private TermLength radius;
    
    public BlurImpl() {
        setValid(false);
    }

    @Override
    public TermLength getRadius() {
        return radius;
    }

    @Override
    public TermList setValue(List<Term<?>> value)
    {
        super.setValue(value);
        List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
        if (args != null && args.size() == 1 && (radius = getLengthArg(args.get(0))) != null) {
            setValid(true);
        }
        return this;
    }

}
