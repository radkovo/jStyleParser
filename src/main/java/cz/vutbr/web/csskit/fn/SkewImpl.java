package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermAngle;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class SkewImpl extends TermFunctionImpl implements TermFunction.Skew {
    
    private TermAngle skewX;
    private TermAngle skewY;
    
    public SkewImpl() {
        setValid(false); //arguments are required
    }

    @Override
    public TermAngle getSkewX() {
        return skewX;
    }

    @Override
    public TermAngle getSkewY() {
        return skewY;
    }

    @Override
    public TermList setValue(List<Term<?>> value)
    {
        super.setValue(value);
        List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
        if (args != null)
        {
            if (args.size() == 2 
                    && (skewX = getAngleArg(args.get(0))) != null
                    && (skewY = getAngleArg(args.get(1))) != null) {
                setValid(true);
            } else if (size() == 1 && (skewX = getAngleArg(args.get(0))) != null) {
                skewY = CSSFactory.getTermFactory().createAngle(0.0f);
                setValid(true);
            }
        }
        return this;
    }
}