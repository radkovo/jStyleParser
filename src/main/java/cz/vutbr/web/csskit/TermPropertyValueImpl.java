/**
 * 
 */
package cz.vutbr.web.csskit;

import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermPropertyValue;

/**
 *
 * @author burgetr
 */
public class TermPropertyValueImpl extends TermPairImpl<CSSProperty, Term<?>> implements TermPropertyValue {

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        if (operator != null)
            sb.append(operator.value());
        
        if (value!=null)
            sb.append(value);
        else
            sb.append(key);
        
        return sb.toString();
    }
    
    
}
