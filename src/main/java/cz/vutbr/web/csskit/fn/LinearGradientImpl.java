package cz.vutbr.web.csskit.fn;

import cz.vutbr.web.css.TermFunction;

public class LinearGradientImpl extends GenericLinearGradient implements TermFunction.LinearGradient {

    @Override
    public boolean isRepeating() {
        return false;
    }
    
}
