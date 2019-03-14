/*
 */

package cz.vutbr.web.csskit.fn;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermInteger;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;
import java.util.List;

/**
 *
 * @author Petr Mikulík
 */
public class FramesImpl extends TermFunctionImpl implements TermFunction.Frames {

    private int _frames;
    
    public FramesImpl() {
        setValid(false);
    }
    
    @Override
    public TermList setValue(List<Term<?>> value) {
        super.setValue(value);
        List<List<Term<?>>> args = getSeparatedArgs(DEFAULT_ARG_SEP);
        if (args != null) {
            if (args.size() == 1) {
                if (setFrames(args.get(0))) {
                    setValid(true);
                }
            }
        }
        return this;
    }
    
    @Override
    public int getFrames() {
        return _frames;
    }

    private boolean setFrames(List<Term<?>> argTerms) {
        if (argTerms.size() == 1) {
            Term t = argTerms.get(0);
            if (t instanceof TermInteger) {
                int value = ((TermInteger) t).getIntValue();
                if (value > 0) {
                    _frames = value;
                    return true;
                }
            }
        }
        return false;
    }
    
}
