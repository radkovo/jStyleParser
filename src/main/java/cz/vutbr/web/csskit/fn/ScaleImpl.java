package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class ScaleImpl extends TermFunctionImpl implements TermFunction.Scale {
    
    private float scaleX;
    private float scaleY;
    
    public ScaleImpl() {
        setValid(false); //arguments are required
    }

    @Override
    public float getScaleX() {
        return scaleX;
    }

    @Override
    public float getScaleY() {
        return scaleY;
    }

    @Override
    public TermList setValue(List<Term<?>> value)
    {
        super.setValue(value);
        List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
        if (args.size() == 2 && isNumberArg(args.get(0)) && isNumberArg(args.get(1))) {
            scaleX = getNumberArg(args.get(0));
            scaleY = getNumberArg(args.get(1));
            setValid(true);
        } else if (size() == 1 && isNumberArg(args.get(0))) {
            scaleX = scaleY = getNumberArg(args.get(0));
            setValid(true);
        }
        return this;
    }
}