/**
 * 
 */
package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermCalc;

/**
 *
 * @author burgetr
 */
public class TermCalcResolutionImpl extends TermResolutionImpl implements TermCalc {
    
    private CalcArgs args;

    public TermCalcResolutionImpl(CalcArgs args) {
        this.args = args;
    }
    
    @Override
    public CalcArgs getArgs() {
        return args;
    }

    @Override
    public Float getValue() {
        return 0f;
    }

    @Override
    public String toString() {
        return OutputUtil.appendCalcArgs(new StringBuilder(OutputUtil.CALC_KEYWORD), args).toString();
    }
    
}
