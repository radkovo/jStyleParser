/**
 * BaseNodeDataImpl.java
 * Created on 29.12.2016 22:39 by Radek Burget
 */
package cz.vutbr.web.domassign;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.SupportedCSS;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermColor.Keyword;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermPropertyValue;
import cz.vutbr.web.csskit.DeclarationTransformer;

/**
 * A common base for all the NodeData implementations. 
 * @author burgetr
 */
public abstract class BaseNodeDataImpl implements NodeData {
    
    protected static DeclarationTransformer transformer = CSSFactory.getDeclarationTransformer();
    protected static SupportedCSS css = CSSFactory.getSupportedCSS();
    
    @Override
    public <T extends CSSProperty> T getSpecifiedProperty(String name) {
        T prop = getProperty(name, true);
        if (prop == null) {
            @SuppressWarnings("unchecked")
            T def = (T) css.getDefaultProperty(name);
            return def;
        } else {
            return prop;
        }
    }
    
    @Override
    public Term<?> getSpecifiedValue(String name) {
        Term<?> ret = getValue(name, true);
        if (ret == null)
            ret = css.getDefaultValue(name);
        
        if (ret != null) {
            if (ret instanceof TermColor && ((TermColor) ret).getKeyword() == Keyword.CURRENT_COLOR) {
                //clone the value fot setting the current color
                final TermFactory tf = CSSFactory.getTermFactory();
                ret = tf.createColor(tf.createIdent("currentColor"));
                //set the current color value
                TermColor cvalue = getValue(TermColor.class, "color", true);
                if (cvalue == null)
                    cvalue = (TermColor) css.getDefaultValue("color");
                ((TermColor) ret).setValue(cvalue.getValue());
            }
        }
        
        return ret;
    }

    @Override
    public <T extends Term<?>> T getSpecifiedValue(Class<T> clazz, String name) {
        return clazz.cast(getSpecifiedValue(name));
    }

    @Override
    public <T extends CSSProperty> T getProperty(String name, int index) {
        return getProperty(name, index, true);
    }

    @Override
    public <T extends CSSProperty> T getProperty(String name, int index, boolean includeInherited) {
        final TermList list = getValue(TermList.class, name, includeInherited);
        if (list != null && index < list.size()) {
            final TermPropertyValue pair = (TermPropertyValue) list.get(index);
            @SuppressWarnings("unchecked")
            T ret = (T) pair.getKey();
            return ret;
        } else {
            return null;
        }
    }
    
    @Override
    public Term<?> getValue(String name, int index, boolean includeInherited) {
        final TermList list = getValue(TermList.class, name, includeInherited);
        if (list != null && index < list.size()) {
            final TermPropertyValue pair = (TermPropertyValue) list.get(index);
            return pair.getValue();
        } else {
            return null;
        }
    }
    
    @Override
    public <T extends Term<?>> T getValue(Class<T> clazz, String name, int index) {
        return getValue(clazz, name, index, true);
    }

    @Override
    public <T extends Term<?>> T getValue(Class<T> clazz, String name, int index,
            boolean includeInherited) {
        final TermList list = getValue(TermList.class, name, includeInherited);
        if (list != null && index < list.size()) {
            final TermPropertyValue pair = (TermPropertyValue) list.get(index);
            return clazz.cast(pair.getValue());
        } else {
            return null;
        }
    }
    
    @Override
    public int getListSize(String name, boolean includeInherited) {
        final TermList list = getValue(TermList.class, name, includeInherited);
        if (list != null)
            return list.size();
        else
            return 0;
    }
    
}
