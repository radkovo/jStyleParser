package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermAngle;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class HueRotateImpl extends TermFunctionImpl implements TermFunction.HueRotate {
    
    private TermAngle angle;
    
    public HueRotateImpl() {
        setValid(false);
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
        if (args.size() == 1 && (angle = getAngleArg(args.get(0))) != null) {
            setValid(true);
        }
        return this;
    }

}
