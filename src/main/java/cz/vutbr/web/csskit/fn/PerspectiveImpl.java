package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class PerspectiveImpl extends TermFunctionImpl implements TermFunction.Perspective {
    
    private TermLength distance;
    
    public PerspectiveImpl() {
        setValid(false); //arguments are required
    }

    @Override
    public TermLength getDistance() {
        return distance;
    }

    @Override
    public TermList setValue(List<Term<?>> value)
    {
        super.setValue(value);
        List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
        if (args.size() == 1 && (distance = getLengthArg(args.get(0))) != null) {
            setValid(true);
        }
        return this;
    }
}