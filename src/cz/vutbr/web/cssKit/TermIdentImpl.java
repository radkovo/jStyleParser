package cz.vutbr.web.cssKit;

import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.cssKit.parser.SimpleNode;

/**
 * TermIdent
 * @author Jan Svercl, VUT Brno, 2008
 */
public class TermIdentImpl extends TermImpl implements TermIdent {

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
    
    public TermIdentImpl(String value) {
        setValue(value);
    }
    
    @Override
    public String toString() {
        return operator(value);
    }
    
    protected static TermIdentImpl getIdentByNode(SimpleNode term) {
        if(term != null) {
            if((term.jjtGetNumChildren() == 1)) {
                if(((SimpleNode)term.jjtGetChild(0)).getType().equals("ident")) {
                    String ident = ((SimpleNode)term.jjtGetChild(0)).getImage();
                    if(ident != null) {
                        TermIdentImpl termIdent = new TermIdentImpl(ident);
                        return termIdent;
                    }
                }
            }
        }
        return null;
    }
}
