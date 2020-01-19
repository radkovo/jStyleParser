package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class Matrix3dImpl extends TermFunctionImpl implements TermFunction.Matrix3d {
    
    private float[] values;
    
    public Matrix3dImpl() {
        setValid(false); //arguments are required
    }

    @Override
    public float[] getValues() {
        return values;
    }

    @Override
    public TermList setValue(List<Term<?>> value)
    {
        super.setValue(value);
        List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
        if (args != null && args.size() == 16) {
            values = new float[16];
            setValid(true);
            for (int i = 0; i < 16; i++) {
                if (isNumberArg(args.get(i)))
                    values[i] = getNumberArg(args.get(i));
                else
                    setValid(false);
            }
        }
        return this;
    }
}