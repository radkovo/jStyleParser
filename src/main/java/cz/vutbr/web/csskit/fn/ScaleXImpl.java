package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class ScaleXImpl extends TermFunctionImpl implements TermFunction.ScaleX {
    
    private float scale;
    
    public ScaleXImpl() {
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
        if (args != null && args.size() == 1 && isNumberArg(args.get(0))) {
            scale = getNumberArg(args.get(0));
            setValid(true);
        }
        return this;
    }
}