package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermLengthOrPercent;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class Translate3dImpl extends TermFunctionImpl implements TermFunction.Translate3d {
    
    private TermLengthOrPercent translateX;
    private TermLengthOrPercent translateY;
    private TermLengthOrPercent translateZ;
    
    public Translate3dImpl() {
        setValid(false); //arguments are required
    }

    @Override
    public TermLengthOrPercent getTranslateX() {
        return translateX;
    }

    @Override
    public TermLengthOrPercent getTranslateY() {
        return translateY;
    }

    @Override
    public TermLengthOrPercent getTranslateZ() {
        return translateZ;
    }

    @Override
    public TermList setValue(List<Term<?>> value)
    {
        super.setValue(value);
        List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
        if (args != null && args.size() == 3 
                && (translateX = getLengthOrPercentArg(args.get(0))) != null
                && (translateY = getLengthOrPercentArg(args.get(1))) != null
                && (translateZ = getLengthOrPercentArg(args.get(2))) != null) {
            setValid(true);
        }
        return this;
    }
}