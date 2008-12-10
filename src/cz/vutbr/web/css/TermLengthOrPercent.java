/**
 * TermLengthOrPercent.java
 *
 * Created on 13.11.2008, 15:58:28 by burgetr
 */
package cz.vutbr.web.css;

/**
 * Holds a float number which is either a CSS length or percentage.
 * 
 * @author burgetr
 */
public interface TermLengthOrPercent extends TermNumeric<Float>
{
    
    public boolean isPercentage();

}
