package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermAngle;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class Rotate3dImpl extends TermFunctionImpl implements TermFunction.Rotate3d {

    private float x;
    private float y;
    private float z;
    private TermAngle angle;
    
    public Rotate3dImpl() {
        setValid(false); //arguments are required
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }


    @Override
    public float getZ() {
        return z;
    }

    @Override
    public TermAngle getAngle() {
        return angle;
    }

    @Override
    public TermList setValue(List<Term<?>> value)
    {
        super.setValue(value);
        List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
        if (args != null && args.size() == 4 
                && isNumberArg(args.get(0)) && isNumberArg(args.get(1)) && isNumberArg(args.get(2))
                && (angle = getAngleArg(args.get(3))) != null) {
            x = getNumberArg(args.get(0));
            y = getNumberArg(args.get(1));
            z = getNumberArg(args.get(2));
            setValid(true);
        } 
        return this;
    }
}