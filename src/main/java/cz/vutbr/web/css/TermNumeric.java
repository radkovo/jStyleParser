package cz.vutbr.web.css;

/**
 * Holds value of numeric type. This could be integer or float
 * according to <T>.
 * @author kapy
 *
 * @param <T> Type of value stored in term
 */
public interface TermNumeric<T> extends Term<T> {
	
	/**
	 * These are available units in CSS
	 * @author kapy
	 *
	 */
	public enum Unit {
	    none(""),
    	em("em"),
    	ex("ex"),
    	ch("ch"),
    	rem("rem"),
    	vw("vw"),
    	vh("vh"),
    	vmin("vmin"),
    	vmax("vmax"),
    	cm("cm"),
    	mm("mm"),
        in("in"),
    	pt("pt"),
    	pc("pc"),
        px("px"),
    	deg("deg"),
    	rad("rad"),
    	grad("grad"),
        turn("turn"),
    	ms("ms"),
    	s("s"),
    	hz("hz"),
    	khz("khz"),
    	dpi("dpi"),
    	dpcm("dpcm"),
    	dppx("dppx");
    
    	private String value;
    	
    	private Unit(String value) { 
    		this.value = value;
    	}
    	public String value() { return value; }
    	
    	public boolean isAngle() {
    		return this==deg || this==rad || this==grad || this==turn;
    	}
    	
    	public boolean isLength() {
    		return this==px || this==ex || this==em || this==cm
    		|| this==mm || this==pt || this==pc || this==in
    		|| this==ch || this==rem || this==vw || this==vh
    		|| this==vmin || this==vmax;
    	}
    	
    	public boolean isTime() {
    		return this==s || this==ms;    
    	}
    	
    	public boolean isFrequency() {
    		return this==hz || this==khz;
    	}
    	
    	public boolean isResolution() {
    	    return this==dpi || this==dpcm || this==dppx;
    	}
    }
	
	/**
	 * Returns unit of type or <code>null</code> if not defined
	 * for numeric types that does not allow units
	 * @return Unit or <code>null</code>
	 */
	public Unit getUnit();
	
	/**
	 * Sets unit
	 * @param unit Unit value
	 * @return Modified object to allow chaining
	 */
    public TermNumeric<T> setUnit(Unit unit);

}
