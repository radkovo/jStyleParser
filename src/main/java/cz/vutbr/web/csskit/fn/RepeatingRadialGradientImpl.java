package cz.vutbr.web.csskit.fn;

import cz.vutbr.web.css.TermFunction;

public class RepeatingRadialGradientImpl extends GenericRadialGradient implements TermFunction.RadialGradient {

    @Override
    public boolean isRepeating() {
        return true;
    }
    
    
}