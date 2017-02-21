/**
 * TermCalc.java
 *
 * Created on 17. 2. 2017, 15:52:35 by burgetr
 */
package cz.vutbr.web.css;

import cz.vutbr.web.csskit.CalcArgs;

/**
 * A value of the calc() expression. It may correspond to length, frequency, angle, time, number, or integer.
 * @author burgetr
 */
public interface TermCalc extends TermFloatValue
{

    /** 
     * Returns the calc() arguments (in postfix notation).
     * @return The list of arguments passed to the calc() function in postfix order.
     */
    public CalcArgs getArgs();
    
}
