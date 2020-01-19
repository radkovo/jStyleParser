package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class Scale3dImpl extends TermFunctionImpl implements TermFunction.Scale3d {
    
    private float scaleX;
    private float scaleY;
    private float scaleZ;
    
    public Scale3dImpl() {
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
    public float getScaleZ() {
        return scaleZ;
    }

    @Override
    public TermList setValue(List<Term<?>> value)
    {
        super.setValue(value);
        List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
        if (args != null && args.size() == 3 
                && isNumberArg(args.get(0)) && isNumberArg(args.get(1)) && isNumberArg(args.get(2))) {
            scaleX = getNumberArg(args.get(0));
            scaleY = getNumberArg(args.get(1));
            scaleZ = getNumberArg(args.get(2));
            setValid(true);
        }
        return this;
    }
}