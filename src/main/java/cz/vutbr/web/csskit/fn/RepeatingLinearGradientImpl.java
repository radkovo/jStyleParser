package cz.vutbr.web.csskit.fn;

import cz.vutbr.web.css.TermFunction;

public class RepeatingLinearGradientImpl extends GenericLinearGradient implements TermFunction.LinearGradient {

    @Override
    public boolean isRepeating() {
        return true;
    }
    
}
