package cz.vutbr.web.cssKit;

import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.cssKit.parser.SimpleNode;

/**
 * TermPercent
 * @author Jan Svercl, VUT Brno, 2008
 */
public class TermPercentImpl extends TermImpl implements TermPercent {

    private Float value;

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        if(value == null) {
            throw new NullPointerException();
        }    
        else {
            this.value = value;
        }
    }
    
    public TermPercentImpl(Float value) {
        setValue(value);
    }
    
    @Override
    public String toString() {
        return operator(value.toString() + "%");
    }
    
    protected static TermPercentImpl getPercentByNode(SimpleNode term) {
        if(term != null) {
            int unary_operator = 1;
            Float value = null;
            
            for(int i = 0; i < term.jjtGetNumChildren(); i++) {
                SimpleNode fTermChild = (SimpleNode)term.jjtGetChild(i);
                if(fTermChild.getType().equals("unary_operator") && fTermChild.jjtGetNumChildren() == 1) {
                    SimpleNode fTermChildOperator = (SimpleNode)fTermChild.jjtGetChild(0);
                    if(fTermChildOperator.getType().equals("minus")) {
                        unary_operator = -1;
                    }
                }
                else if(fTermChild.getType().equals("percentage")) {
                    value = Float.parseFloat(fTermChild.getImage().trim().toLowerCase().replaceAll("%$", ""));
                }
            }
            if(value != null) {
                value = value.floatValue() * unary_operator;
                TermPercentImpl s;
                s = new TermPercentImpl(value);
                return s;
            }
        }
        return null;
    }
}
