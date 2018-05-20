/**
 * 
 */
package cz.vutbr.web.csskit.fn;

import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;

/**
 * @author burgetr
 *
 */
public class AttrImpl extends TermFunctionImpl implements TermFunction.Attr {
    
    private TermIdent name;
    
    public AttrImpl() {
        setValid(false);
    }

    @Override
    public TermIdent getName() {
        return name;
    }
    
    @Override
    public TermList setValue(List<Term<?>> value) {
        super.setValue(value);
        List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, true);
        if (args != null && args.size() == 1) {
            if (args.get(0) instanceof TermIdent) {
                name = (TermIdent) args.get(0);
                setValid(true);
            }
        }
        return this;
    }

}
