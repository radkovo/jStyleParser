/*
 */
package cz.vutbr.web.csskit.fn;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermNumber;
import cz.vutbr.web.csskit.TermFunctionImpl;
import java.util.List;

/**
 *
 * @author Petr Mikulík
 */
public class CubicBezierImpl extends TermFunctionImpl implements TermFunction.CubicBezier {

    private final float[] _values = new float[4];

    public CubicBezierImpl() {
        setValid(false);
    }

    @Override
    public TermList setValue(List<Term<?>> value) {
        super.setValue(value);
        List<List<Term<?>>> args = getSeparatedArgs(DEFAULT_ARG_SEP);
        if (args != null) {
            if (args.size() == 4) {
                if (setValues(args)) {
                    setValid(true);
                }
            }
        }
        return this;
    }

    @Override
    public float getX1() {
        return _values[0];
    }

    @Override
    public float getY1() {
        return _values[1];
    }

    @Override
    public float getX2() {
        return _values[2];
    }

    @Override
    public float getY2() {
        return _values[3];
    }

    private boolean setValues(List<List<Term<?>>> args) {
        for (int i = 0; i < args.size(); i++) {
            if (!setValueAt(i, args.get(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean setValueAt(int index, List<Term<?>> argTerms) {
        if (argTerms.size() == 1) {
            Term t = argTerms.get(0);
            if (t instanceof TermNumber) {
                float value = ((TermNumber) t).getValue();
                if (index == 1
                        || index == 3
                        || value >= 0 && value <= 1) {
                    _values[index] = value;
                    return true;
                }
            }
        }
        return false;
    }

}
