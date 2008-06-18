package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermString;
import cz.vutbr.web.csskit.parser.SimpleNode;

/**
 * TermString
 * @author Jan Svercl, VUT Brno, 2008
 */
public class TermStringImpl extends TermImpl implements TermString {
    
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
    
    public TermStringImpl(String value) {
        setValue(value);
    }
    
    @Override
    public String toString() {
        return operator("'" + value + "'");
    }
    
    protected static TermStringImpl getStringByNode(SimpleNode term) {
        if(term != null) {
            if((term.jjtGetNumChildren() == 1)) {
                if(((SimpleNode)term.jjtGetChild(0)).getType().equals("string")) {
                    String value = ((SimpleNode)term.jjtGetChild(0)).getImage();
                    if(value != null) {
                        value = value.replaceAll("^'", "");
                        value = value.replaceAll("^\"", "");
                        value = value.replaceAll("'$", "");
                        value = value.replaceAll("\"$", "");
                        TermStringImpl termString = new TermStringImpl(value);
                        return termString;
                    }
                }
            }
        }
        return null;
    }
}
