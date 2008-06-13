package cz.vutbr.web.cssKit;

import cz.vutbr.web.css.SSItemAttrib;
import cz.vutbr.web.cssKit.parser.SimpleNode;

/**
 * SSItemAttrib
 * @author Jan Svercl, VUT Brno, 2008
 */
public class SSItemAttribImpl implements SSItemAttrib {

    private String attrib;
    private EnumOperator operator = null;
    private String value;
    private EnumValueType valueType;

    public String getAttrib() {
        return attrib;
    }

    public void setAttrib(String attrib) {
        if(attrib == null) {
            throw new NullPointerException();
        }
        else {
            this.attrib = attrib;
        }
    }

    public EnumOperator getOperator() {
        return operator;
    }

    public String getValue() {
        return value;
    }

    public EnumValueType getValueType() {
        return valueType;
    }
    
    public void setValue(EnumOperator operator, String value, EnumValueType valueType) {
        if(operator == null && value == null && valueType == null) {
            this.operator = null;
            this.value = null;
            this.valueType = null;
            return;
        }
        else if(operator == null) {
            throw new NullPointerException();
        }
        else if(value == null) {
            throw new NullPointerException();
        }
        else if(valueType == null) {
            throw new NullPointerException();
        }
        else {
            this.operator = operator;
            this.value = value;
            this.valueType = valueType;
        }
    }
    
    public SSItemAttribImpl(String attrib) {
        setAttrib(attrib);
    }
    
    protected SSItemAttribImpl(SimpleNode n) {
        SimpleNode cNode = (SimpleNode)n.jjtGetChild(0);
        if(cNode.getType().equals("ident")) {
            attrib = cNode.getImage();
        }
        if(n.jjtGetNumChildren() > 1) {
            if(((SimpleNode)n.jjtGetChild(1)).getType().equals("equal")) {
                operator = EnumOperator.equals;
            }
            else if(((SimpleNode)n.jjtGetChild(1)).getType().equals("includes")) {
                operator = EnumOperator.includes;
            }
            else if(((SimpleNode)n.jjtGetChild(1)).getType().equals("dashmatch")) {
                operator = EnumOperator.dashmatch;
            }
            
            if(((SimpleNode)n.jjtGetChild(2)).getType().equals("ident")) {
                value = ((SimpleNode)n.jjtGetChild(2)).getImage();
                valueType = EnumValueType.ident;
            }
            else {
                value = ((SimpleNode)n.jjtGetChild(2)).getImage();
                value = value.replaceAll("^'", "");
                value = value.replaceAll("^\"", "");
                value = value.replaceAll("'$", "");
                value = value.replaceAll("\"$", "");
                valueType = EnumValueType.string;
            }
        }
    }
    
    @Override
    public String toString() {
        String out = "";
        out += "[" + attrib;
        if(operator == EnumOperator.equals) {
            out += "=";
        }
        else if(operator == EnumOperator.includes) {
            out += "~=";
        }
        else if(operator == EnumOperator.dashmatch) {
            out += "|=";
        }
        if(valueType == EnumValueType.string) {
            out += "'";
        }
        if(value != null) {
            out += value;
        }
        if(valueType == EnumValueType.string) {
            out += "'";
        }
        out += "]";
        return out;
    }
}
