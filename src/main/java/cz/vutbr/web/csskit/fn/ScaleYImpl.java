package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class ScaleYImpl extends TermFunctionImpl implements TermFunction.ScaleY {
    
    private float scale;
    
    public ScaleYImpl() {
        setValid(false); //arguments are required
    }

    @Override
    public float getScale() {
        return scale;
    }

    @Override
    public TermList setValue(List<Term<?>> value)
    {
        super.setValue(value);
        List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
        if (args.size() == 1 && isNumberArg(args.get(0))) {
            scale = getNumberArg(args.get(0));
            setValid(true);
        }
        return this;
    }
}