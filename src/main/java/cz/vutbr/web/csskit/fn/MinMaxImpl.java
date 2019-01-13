package cz.vutbr.web.csskit.fn;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.csskit.TermFunctionImpl;
import java.util.List;

/**
 *
 * @author Petr Mikulík
 */
public class MinMaxImpl extends TermFunctionImpl implements TermFunction.MinMax {

    private static final String MIN_CONTENT = "min-content";
    private static final String MAX_CONTENT = "max-content";
    private static final String AUTO = "auto";

    private Unit _min;
    private Unit _max;

    public MinMaxImpl() {
        setValid(false);
    }

    @Override
    public TermList setValue(List<Term<?>> value) {
        super.setValue(value);
        List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, true);
        if (args != null && args.size() == 2) {
            if (setArgument(true, args.get(0)) && setArgument(false, args.get(1))) {
                setValid(true);
            }
        }
        return this;
    }

    @Override
    public Unit getMin() {
        return _min;
    }

    @Override
    public Unit getMax() {
        return _max;
    }

    private boolean setArgument(boolean isMin, Term<?> argTerm) {
        if (argTerm instanceof TermLength) {
            if (isMin) {
                _min = Unit.createWithLenght((TermLength) argTerm);
            } else {
                _max = Unit.createWithLenght((TermLength) argTerm);
            }
        } else if (argTerm instanceof TermPercent) {
            if (isMin) {
                _min = Unit.createWithLenght((TermPercent) argTerm);
            } else {
                _max = Unit.createWithLenght((TermPercent) argTerm);
            }
        } else if (argTerm instanceof TermIdent) {
            String value = ((TermIdent) argTerm).getValue();
            if (value.equalsIgnoreCase(MIN_CONTENT)) {
                if (isMin) {
                    _min = Unit.createWithMinContent();
                } else {
                    _max = Unit.createWithMinContent();
                }
            } else if (value.equalsIgnoreCase(MAX_CONTENT)) {
                if (isMin) {
                    _min = Unit.createWithMaxContent();
                } else {
                    _max = Unit.createWithMaxContent();
                }
            } else if (value.equalsIgnoreCase(AUTO)) {
                if (isMin) {
                    _min = Unit.createWithAuto();
                } else {
                    _max = Unit.createWithAuto();
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

}
