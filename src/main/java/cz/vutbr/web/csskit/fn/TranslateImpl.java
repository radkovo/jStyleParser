package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermLengthOrPercent;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class TranslateImpl extends TermFunctionImpl implements TermFunction.Translate {
    
    private TermLengthOrPercent translateX;
    private TermLengthOrPercent translateY;
    
    public TranslateImpl() {
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
    public TermList setValue(List<Term<?>> value)
    {
        super.setValue(value);
        List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
        if (args.size() == 2 
                && (translateX = getLengthOrPercentArg(args.get(0))) != null
                && (translateY = getLengthOrPercentArg(args.get(1))) != null) {
            setValid(true);
        } else if (size() == 1 && (translateX = getLengthOrPercentArg(args.get(0))) != null) {
            translateY = CSSFactory.getTermFactory().createLength(0.0f);
            setValid(true);
        }
        return this;
    }
}