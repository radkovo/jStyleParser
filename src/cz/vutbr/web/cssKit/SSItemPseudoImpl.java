package cz.vutbr.web.cssKit;

import cz.vutbr.web.css.SSItemPseudo;
import cz.vutbr.web.cssKit.parser.SimpleNode;

/**
 * SSItemPseudo
 * @author Jan Svercl, VUT Brno, 2008
 */
public class SSItemPseudoImpl implements SSItemPseudo {

    private String value = null;
    private String funcName = null;

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

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }
    
    public SSItemPseudoImpl(String value) {
        setValue(value);
    }
    
    public SSItemPseudoImpl(String value, String funcName) {
        this(value);
        setFuncName(funcName);
    }
    
    protected SSItemPseudoImpl(SimpleNode n) {
        SimpleNode cNode = (SimpleNode)n.jjtGetChild(0);
        if(cNode.getType().equals("ident")) {
            value = cNode.getImage();
        }
        else if(cNode.getType().equals("pfunction")) {
            funcName = ((SimpleNode)cNode.jjtGetChild(0)).getImage();
            funcName = funcName.replaceAll("\\($", "");
            if(cNode.jjtGetNumChildren() > 0) {
                value = funcName = ((SimpleNode)cNode.jjtGetChild(1)).getImage();
            }
        }
    }
    
    @Override
    public String toString() {
        String out = "";
        out += ":";
        if(funcName != null) {
            out += funcName + "(";
        }
        if(value != null) {
            out += value;
        }
        if(funcName != null) {
            out += ")";
        }
        return out;
    }
}
