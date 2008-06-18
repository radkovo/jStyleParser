package cz.vutbr.web.csskit;

import cz.vutbr.web.css.SSItemID;
import cz.vutbr.web.csskit.parser.SimpleNode;

/**
 * SSItemID
 * @author Jan Svercl, VUT Brno, 2008
 */
public class SSItemIDImpl implements SSItemID {
    
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
    
    public SSItemIDImpl(String value) {
        setValue(value);
    }
    
    protected SSItemIDImpl(SimpleNode n) {
        value = n.getImage();
        value = value.replaceAll("^#", "");
    }
    
    @Override
    public String toString() {
        return "#" + value;
    }
}
