package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermLengthOrPercent;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class TranslateXImpl extends TermFunctionImpl implements TermFunction.TranslateX {
    
    private TermLengthOrPercent translate;
    
    public TranslateXImpl() {
        setValid(false); //arguments are required
    }

    @Override
    public TermLengthOrPercent getTranslate() {
        return translate;
    }

    @Override
    public TermList setValue(List<Term<?>> value)
    {
        super.setValue(value);
        List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
        if (args != null && args.size() == 1 && (translate = getLengthOrPercentArg(args.get(0))) != null) {
            setValid(true);
        }
        return this;
    }
}