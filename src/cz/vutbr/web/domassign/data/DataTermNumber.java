package cz.vutbr.web.domassign.data;

import cz.vutbr.web.css.TermNumber;

/**
 * TermIdent
 * @author Jan Svercl, VUT Brno, 2008
 */
public class DataTermNumber extends DataTerm implements TermNumber {

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
    
    public DataTermNumber(Float value) {
        setValue(value);
    }
    
    public DataTermNumber(Float value, EnumUnit unit) {
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
