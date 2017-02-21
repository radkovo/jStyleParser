/**
 * 
 */
package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermCalc;

/**
 *
 * @author burgetr
 */
public class TermCalcLengthImpl extends TermLengthImpl implements TermCalc {
    
    private CalcArgs args;

    public TermCalcLengthImpl(CalcArgs args) {
        this.args = args;
    }
    
    @Override
    public CalcArgs getArgs() {
        return args;
    }

    @Override
    public Float getValue() {
        return 10f;
    }

    @Override
    public String toString() {
        return "calc" + args.evaluate(CalcArgs.stringEvaluator);
    }
    
}
