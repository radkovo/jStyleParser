package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermAngle;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class SkewYImpl extends TermFunctionImpl implements TermFunction.SkewY {
    
    private TermAngle skew;
    
    public SkewYImpl() {
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
        if (args.size() == 1 && (skew = getAngleArg(args.get(0))) != null) {
            setValid(true);
        }
        return this;
    }
}