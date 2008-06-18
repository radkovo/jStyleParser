package cz.vutbr.web.csskit;

import cz.vutbr.web.css.SSType;
import cz.vutbr.web.csskit.parser.SimpleNode;

/**
 * SSType
 * @author Jan Svercl, VUT Brno, 2008
 */
public class SSTypeImpl implements SSType {

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
    
    public SSTypeImpl(String value) {
        setValue(value);
    }
    
    protected SSTypeImpl(SimpleNode n) {
        if(n.jjtGetNumChildren() == 0) {
            value = "*";
        }
        else {
            value = ((SimpleNode)n.jjtGetChild(0)).getImage();
        }
    }
    
    @Override
    public String toString() {
        return value;
    }
  
}
