/*
 */
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
public class StepsImpl extends TermFunctionImpl implements TermFunction.Steps {

    private int _numberOfSteps;
    private Direction _direciton;
    
    public StepsImpl() {
        setValid(false);
    }

    @Override
    public TermList setValue(List<Term<?>> value) {
        super.setValue(value);
        List<List<Term<?>>> args = getSeparatedArgs(DEFAULT_ARG_SEP);
        if (args != null) {
            if (args.size() == 1) {
                if (setNumberOfSteps(args.get(0))) {
                    _direciton = Direction.END;
                    setValid(true);
                }
            } else if (args.size() == 2) {
                if (setNumberOfSteps(args.get(0)) && setDirection(args.get(1))) {
                    setValid(true);
                }
            }
        }
        return this;
    }

    @Override
    public int getNumberOfSteps() {
        return _numberOfSteps;
    }

    @Override
    public Direction getDirection() {
        return _direciton;
    }

    private boolean setNumberOfSteps(List<Term<?>> argTerms) {
        if (argTerms.size() == 1) {
            Term<?> t = argTerms.get(0);
            if (t instanceof TermInteger) {
                int value = ((TermInteger) t).getIntValue();
                if (value > 0) {
                    _numberOfSteps = value;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean setDirection(List<Term<?>> argTerms) {
        if (argTerms.size() == 1) {
            Term<?> t = argTerms.get(0);
            if (t instanceof TermIdent) {
                String value = ((TermIdent) t).getValue();
                for (Direction d : Direction.values()) {
                    if (d.toString().equals(value)) {
                        _direciton = d;
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
