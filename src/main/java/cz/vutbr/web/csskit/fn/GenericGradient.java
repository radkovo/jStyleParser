/**
 * GenericGradient.java
 *
 * Created on 17. 5. 2018, 12:52:23 by burgetr
 */
package cz.vutbr.web.csskit.fn;

import java.util.ArrayList;
import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermLengthOrPercent;
import cz.vutbr.web.css.TermFunction.Gradient.ColorStop;
import cz.vutbr.web.csskit.ColorStopImpl;
import cz.vutbr.web.csskit.TermFunctionImpl;

/**
 * Implementation of common features of all the gradient functions.
 * 
 * @author burgetr
 */
public class GenericGradient extends TermFunctionImpl
{
    private List<TermFunction.Gradient.ColorStop> colorStops;
    
    public GenericGradient() {
        setValid(false);
    }
    
    public List<ColorStop> getColorStops() {
        return colorStops;
    }

    protected void loadColorStops(List<List<Term<?>>> args, int firstStop) {
        colorStops = decodeColorStops(args, firstStop);
    }
    
    /**
     * Loads the color stops from the gunction arguments.
     * @param args the comma-separated function arguments
     * @param firstStop the first argument to start with
     * @return the list of color stops or {@code null} when the arguments are invalid or missing
     */
    protected List<TermFunction.Gradient.ColorStop> decodeColorStops(List<List<Term<?>>> args, int firstStop) {
        boolean valid = true;
        List<TermFunction.Gradient.ColorStop> colorStops = null;
        if (args.size() > firstStop) {
            colorStops = new ArrayList<>();
            for (int i = firstStop; valid && i < args.size(); i++) {
                List<Term<?>> sarg = args.get(i);
                if (sarg.size() == 1 || sarg.size() == 2) {
                    Term<?> tclr = sarg.get(0);
                    Term<?> tlen = (sarg.size() == 2) ? sarg.get(1) : null;
                    if (tclr instanceof TermColor
                            && (tlen == null || tlen instanceof TermLengthOrPercent)) {
                        TermFunction.Gradient.ColorStop newStop = new ColorStopImpl((TermColor) tclr, (TermLengthOrPercent) tlen);
                        colorStops.add(newStop);
                    } else {
                        valid = false;
                    }
                } else {
                    valid = false;
                }
            }
        }
        if (valid && colorStops != null && !colorStops.isEmpty())
            return colorStops;
        else
            return null;
    }
    
}
