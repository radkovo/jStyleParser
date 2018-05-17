package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermAngle;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class RepeatingLinearGradientImpl extends TermFunctionImpl implements TermFunction.RepeatingLinearGradient {
    private TermAngle angle;
    private List<TermFunction.Gradient.ColorStop> colorStops;
    
    public RepeatingLinearGradientImpl() {
        setValid(false);
    }
    
    @Override
    public TermAngle getAngle() {
        return angle;
    }

    @Override
    public List<ColorStop> getColorStops() {
        return colorStops;
    }
    
    @Override
    public TermList setValue(List<Term<?>> value) {
        super.setValue(value);
        List<List<Term<?>>> args = getSeparatedArgs(DEFAULT_ARG_SEP);
        if (args.size() > 1) {
            int firstStop = 0;
            //check for an angle
            List<Term<?>> aarg = args.get(0);
            if (aarg.size() == 1 && (angle = getAngleArg(aarg.get(0))) != null) {
                firstStop = 1;
            } else if ((angle = convertSideOrCorner(aarg)) != null) {
                firstStop = 1;
            }
            //check for stops
            colorStops = loadColorStops(args, firstStop);
            if (colorStops != null)
                setValid(true);
        }
        return this;
    }
}