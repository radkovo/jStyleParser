package cz.vutbr.web.css;

/**
 * TermIdent
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface TermNumber extends Term {

    public enum EnumUnit { px, em, ex, cm, mm, pt, pc, deg, rad, grad, ms, s, hz, khz }
    
    public Float getValue();

    public void setValue(Float value);
    
    public EnumUnit getUnit();

    public void setUnit(EnumUnit unit);
    
    public boolean isLength();
    
    public boolean isNumber();
    
    public boolean isInteger();
}
