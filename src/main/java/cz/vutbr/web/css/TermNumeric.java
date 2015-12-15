package cz.vutbr.web.css;

/**
 * Holds value of numeric type. This could be integer or float
 * according to &lt;T&gt;.
 * @author kapy
 *
 * @param <T> Type of value stored in term
 */
public interface TermNumeric<T extends Number> extends Term<T> {
	
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
    	q("q"),
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
            switch (this) {
                case pt:
                case in:
                case cm:
                case mm:
                case q:
                case pc:
                case px:
                case em:
                case ex:
                case ch:
                case rem:
                case vw:
                case vh:
                case vmin:
                case vmax:
                    return true;
                default:
                    return false;
            }
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

    /**
     * Sets the value to zero.
     * @return Modified object to allow chaining
     */
    public TermNumeric<T> setZero();
    
}
