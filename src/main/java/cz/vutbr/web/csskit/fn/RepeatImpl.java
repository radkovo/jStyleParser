package cz.vutbr.web.csskit.fn;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermInteger;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;
import java.util.List;

/**
 *
 * @author Petr Mikulík
 */
public class RepeatImpl extends TermFunctionImpl implements TermFunction.Repeat {

    private static final String AUTO_FIT = "auto-fit";
    private static final String AUTO_FILL = "auto-fill";

    private Unit _numberOfRepetitions;
    private List<Term<?>> _repeatedTerms;
    
    public RepeatImpl() {
        setValid(false);
    }

    @Override
    public TermList setValue(List<Term<?>> value) {
        super.setValue(value);
        List<List<Term<?>>> args = getSeparatedArgs(DEFAULT_ARG_SEP);
        if (args != null && args.size() == 2) {
            if (setNumberOfRepetitions(args.get(0)) && setRepeatedTerms(args.get(1))) {
                setValid(true);
            }
        }
        return this;
    }

    @Override
    public Unit getNumberOfRepetitions() {
        return _numberOfRepetitions;
    }

    @Override
    public List<Term<?>> getRepeatedTerms() {
        return _repeatedTerms;
    }

    private boolean setNumberOfRepetitions(List<Term<?>> argTerms) {
        if (argTerms.size() == 1) {
            Term<?> t = argTerms.get(0);
            if (t instanceof TermInteger) {
                int value = ((TermInteger) t).getIntValue();
                if (value > 0) {
                    _numberOfRepetitions = Unit.createWithNRepetitions(value);
                    return true;
                }
            } else if (t instanceof TermIdent) {
                String value = ((TermIdent) t).getValue();
                if (value.equalsIgnoreCase(AUTO_FIT)) {
                    _numberOfRepetitions = Unit.createWithAutoFit();
                    return true;
                } else if (value.equalsIgnoreCase(AUTO_FILL)) {
                    _numberOfRepetitions = Unit.createWithAutoFill();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean setRepeatedTerms(List<Term<?>> argTerms) {
        _repeatedTerms = argTerms;
        return true;
    }

}
