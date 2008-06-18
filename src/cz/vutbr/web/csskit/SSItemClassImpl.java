package cz.vutbr.web.csskit;

import cz.vutbr.web.css.SSItemClass;
import cz.vutbr.web.csskit.parser.SimpleNode;

/**
 * SSItemClass
 * @author Jan Svercl, VUT Brno, 2008
 */
public class SSItemClassImpl implements SSItemClass {
    
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if(value == null) {
            throw new NullPointerException();
        }    
        else {
            this.value = value;
        }
    }
    
    public SSItemClassImpl(String value) {
        setValue(value);
    }
    
    protected SSItemClassImpl(SimpleNode n) {
        value = ((SimpleNode)n.jjtGetChild(0)).getImage();
    }
    
    @Override
    public String toString() {
        return "." + value;
    }
}
