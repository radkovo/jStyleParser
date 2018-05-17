package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermLengthOrPercent;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class TranslateZImpl extends TermFunctionImpl implements TermFunction.TranslateZ {
    
    private TermLengthOrPercent translate;
    
    public TranslateZImpl() {
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
        if (args.size() == 1 && (translate = getLengthOrPercentArg(args.get(0))) != null) {
            setValid(true);
        }
        return this;
    }
}