package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermAngle;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class SkewXImpl extends TermFunctionImpl implements TermFunction.SkewX {
    
    private TermAngle skew;
    
    public SkewXImpl() {
        setValid(false); //arguments are required
    }

    @Override
    public TermAngle getSkew() {
        return skew;
    }

    @Override
    public TermList setValue(List<Term<?>> value)
    {
        super.setValue(value);
        List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
        if (args != null && args.size() == 1 && (skew = getAngleArg(args.get(0))) != null) {
            setValid(true);
        }
        return this;
    }
}