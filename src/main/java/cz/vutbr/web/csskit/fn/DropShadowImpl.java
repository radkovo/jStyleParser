package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class DropShadowImpl extends TermFunctionImpl implements TermFunction.DropShadow {
    
    private TermLength offsetX;
    private TermLength offsetY;
    private TermLength blurRadius;
    private TermColor color;
    
    public DropShadowImpl() {
        setValid(false);
    }

    @Override
    public TermLength getOffsetX() {
        return offsetX;
    }
    
    @Override
    public TermLength getOffsetY() {
        return offsetY;
    }
    
    @Override
    public TermLength getBlurRadius() {
        return blurRadius;
    }
    
    @Override
    public TermColor getColor() {
        return color;
    }

    @Override
    public TermList setValue(List<Term<?>> value)
    {
        super.setValue(value);
        List<Term<?>> args = getValues(false);
        if (args.size() >= 2) {
            //find the color if used (the first or last value)
            if (args.get(0) instanceof TermColor) {
                color = (TermColor) args.get(0);
                args.remove(0);
            } else if (args.get(args.size() - 1) instanceof TermColor) {
                color = (TermColor) args.get(args.size() - 1);
                args.remove(args.size() - 1);
            }
            //interpret the remaining lengths
            if (args.size() >= 2) {
                if ((offsetX = getLengthArg(args.get(0))) != null && (offsetY = getLengthArg(args.get(1))) != null) {
                    setValid(true);
                }
                if (args.size() >= 3) {
                    if ((blurRadius = getLengthArg(args.get(2))) != null) {
                        setValid(true);
                    } else {
                        setValid(false);
                    }
                }
            }
        }
        return this;
    }

}
