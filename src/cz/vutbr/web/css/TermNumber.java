package cz.vutbr.web.css;

/**
 * TermIdent
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 */
public interface TermNumber extends Term {

    public enum Unit {
    	px("px"),
    	em("em"),
    	ex("ex"),
    	cm("cm"),
    	mm("mm"),
    	pt("pt"),
    	pc("pc"),
    	deg("deg"),
    	rad("rad"),
    	grad("grad"),
    	ms("ms"),
    	s("s"),
    	hz("hz"),
    	khz("khz");
    
    	private String value;
    	
    	private Unit(String value) { 
    		this.value = value;
    	}
    	public String value() { return value; }
    }
    
    public Float getValue();

    public void setValue(Float value);
    
    public Unit getUnit();

    public void setUnit(Unit unit);
    
    public boolean isLength();
    
    public boolean isNumber();
    
    public boolean isInteger();
}
