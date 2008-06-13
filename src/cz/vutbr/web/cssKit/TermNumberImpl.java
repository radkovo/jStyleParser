package cz.vutbr.web.cssKit;

import cz.vutbr.web.css.TermNumber;
import cz.vutbr.web.cssKit.parser.SimpleNode;

/**
 * TermIdent
 * @author Jan Svercl, VUT Brno, 2008
 */
public class TermNumberImpl extends TermImpl implements TermNumber {

    private Float value;
    private EnumUnit unit = null;
    
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
    
    public EnumUnit getUnit() {
        return unit;
    }

    public void setUnit(EnumUnit unit) {
        this.unit = unit;
    }
    
    public TermNumberImpl(Float value) {
        setValue(value);
    }
    
    public TermNumberImpl(Float value, EnumUnit unit) {
        this(value);
        this.unit = unit;
    }
    
    @Override
    public String toString() {
        String out; 
        if(isInteger()) {
            out = new Integer(value.intValue()).toString();
        }
        else {
            out = value.toString();
        }
        if(unit == null) {
            return operator(out);
        }
        switch (unit) {
            case px: out = out + "px"; break;
            case em: out = out + "em"; break;
            case ex: out = out + "ex"; break;
            case cm: out = out + "cm"; break;
            case mm: out = out + "mm"; break;
            case pt: out = out + "pt"; break;
            case pc: out = out + "pc"; break;
            case deg: out = out + "deg"; break;
            case rad: out = out + "rad"; break;
            case grad: out = out + "grad"; break;
            case ms: out = out + "ms"; break;
            case s: out = out + "s"; break;
            case hz: out = out + "hz"; break;
            case khz: out = out + "khz"; break;
        }
        return operator(out);
    }
    
    protected static TermNumberImpl getNumberByNode(SimpleNode term) {
        if(term != null) {
            int unary_operator = 1;
            Float value = null;
            TermNumberImpl.EnumUnit unit = null;
            
            for(int i = 0; i < term.jjtGetNumChildren(); i++) {
                SimpleNode fTermChild = (SimpleNode)term.jjtGetChild(i);
                if(fTermChild.getType().equals("unary_operator") && fTermChild.jjtGetNumChildren() == 1) {
                    SimpleNode fTermChildOperator = (SimpleNode)fTermChild.jjtGetChild(0);
                    if(fTermChildOperator.getType().equals("minus")) {
                        unary_operator = -1;
                    }
                }
                else if(fTermChild.getType().equals("lengthpx")) {
                    value = Float.parseFloat(fTermChild.getImage().trim().toLowerCase().replaceAll("px$", ""));
                    unit = TermNumberImpl.EnumUnit.px;
                }
                else if(fTermChild.getType().equals("number")) {
                    value = Float.parseFloat(fTermChild.getImage().trim());
                    unit = null;
                }
                else if(fTermChild.getType().equals("ems")) {
                    value = Float.parseFloat(fTermChild.getImage().trim().toLowerCase().replaceAll("em$", ""));
                    unit = TermNumberImpl.EnumUnit.em;
                }
                else if(fTermChild.getType().equals("exs")) {
                    value = Float.parseFloat(fTermChild.getImage().trim().toLowerCase().replaceAll("ex$", ""));
                    unit = TermNumberImpl.EnumUnit.ex;
                }
                else if(fTermChild.getType().equals("lengthcm")) {
                    value = Float.parseFloat(fTermChild.getImage().trim().toLowerCase().replaceAll("cm$", ""));
                    unit = TermNumberImpl.EnumUnit.cm;
                }
                else if(fTermChild.getType().equals("lengthmm")) {
                    value = Float.parseFloat(fTermChild.getImage().trim().toLowerCase().replaceAll("mm$", ""));
                    unit = TermNumberImpl.EnumUnit.mm;
                }
                else if(fTermChild.getType().equals("lengthpt")) {
                    value = Float.parseFloat(fTermChild.getImage().trim().toLowerCase().replaceAll("pt$", ""));
                    unit = TermNumberImpl.EnumUnit.pt;
                }
                else if(fTermChild.getType().equals("lengthpc")) {
                    value = Float.parseFloat(fTermChild.getImage().trim().toLowerCase().replaceAll("pc$", ""));
                    unit = TermNumberImpl.EnumUnit.pc;
                }
                else if(fTermChild.getType().equals("angledeg")) {
                    value = Float.parseFloat(fTermChild.getImage().trim().toLowerCase().replaceAll("deg$", ""));
                    unit = TermNumberImpl.EnumUnit.deg;
                }
                else if(fTermChild.getType().equals("anglerad")) {
                    value = Float.parseFloat(fTermChild.getImage().trim().toLowerCase().replaceAll("rad$", ""));
                    unit = TermNumberImpl.EnumUnit.rad;
                }
                else if(fTermChild.getType().equals("anglegrad")) {
                    value = Float.parseFloat(fTermChild.getImage().trim().toLowerCase().replaceAll("grad$", ""));
                    unit = TermNumberImpl.EnumUnit.grad;
                }
                else if(fTermChild.getType().equals("timems")) {
                    value = Float.parseFloat(fTermChild.getImage().trim().toLowerCase().replaceAll("ms$", ""));
                    unit = TermNumberImpl.EnumUnit.ms;
                }
                else if(fTermChild.getType().equals("times")) {
                    value = Float.parseFloat(fTermChild.getImage().trim().toLowerCase().replaceAll("s$", ""));
                    unit = TermNumberImpl.EnumUnit.s;
                }
                else if(fTermChild.getType().equals("freqhz")) {
                    value = Float.parseFloat(fTermChild.getImage().trim().toLowerCase().replaceAll("hz$", ""));
                    unit = TermNumberImpl.EnumUnit.hz;
                }
                else if(fTermChild.getType().equals("freqkhz")) {
                    value = Float.parseFloat(fTermChild.getImage().trim().toLowerCase().replaceAll("khz$", ""));
                    unit = TermNumberImpl.EnumUnit.khz;
                }
            }
            if(value != null) {
                value = value.floatValue() * unary_operator;
                TermNumberImpl s;
                s = new TermNumberImpl(value, unit);
                return s;
            }
        }
        return null;
    }
    
    public boolean isLength() {
        if(value.floatValue() == 0 && unit == null) {
            return true;
        }
        if(unit == null) {
            return false;
        }
        switch (unit) {
            case px: return true;
            case em: return true;
            case ex: return true;
            case cm: return true;
            case mm: return true;
            case pt: return true;
            case pc: return true;
            case deg: return false;
            case rad: return false;
            case grad: return false;
            case ms: return false;
            case s: return false;
            case hz: return false;
            case khz: return false;
            default: return false;
        }
    }
    
    public boolean isNumber() {
        return (unit == null);
    }
    
    public boolean isInteger() {
        return (unit == null && value.floatValue() == value.intValue());
    }
}
