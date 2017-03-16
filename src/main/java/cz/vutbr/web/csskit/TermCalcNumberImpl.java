/**
 * 
 */
package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermCalc;

/**
 *
 * @author burgetr
 */
public class TermCalcNumberImpl extends TermNumberImpl implements TermCalc {
    
    private CalcArgs args;

    public TermCalcNumberImpl(CalcArgs args) {
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
        return "calc" + args.evaluate(CalcArgs.stringEvaluator);
    }
    
}
