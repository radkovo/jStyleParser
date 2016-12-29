/**
 * BaseNodeDataImpl.java
 * Created on 29.12.2016 22:39 by Radek Burget
 */
package cz.vutbr.web.domassign;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.SupportedCSS;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermColor.Keyword;
import cz.vutbr.web.csskit.DeclarationTransformer;

/**
 * A common base for all the NodeData implementations. 
 * @author burgetr
 */
public abstract class BaseNodeDataImpl implements NodeData {
    
    protected static DeclarationTransformer transformer = CSSFactory.getDeclarationTransformer();
    protected static SupportedCSS css = CSSFactory.getSupportedCSS();
    
    @Override
    public Term<?> getSpecifiedValue(String name) {
        Term<?> ret = getValue(name, true);
        if (ret == null)
            ret = css.getDefaultValue(name);
        
        if (ret != null) {
            if (ret instanceof TermColor && ((TermColor) ret).getKeyword() == Keyword.CURRENT_COLOR) {
                TermColor cvalue = getValue(TermColor.class, "color", true);
                if (cvalue != null)
                    ((TermColor) ret).setValue(cvalue.getValue());
            }
        }
        
        return ret;
    }

    @Override
    public <T extends Term<?>> T getSpecifiedValue(Class<T> clazz, String name) {
        return clazz.cast(getSpecifiedValue(name));
    }

}
