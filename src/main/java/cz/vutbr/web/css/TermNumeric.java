package cz.vutbr.web.css;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds value of numeric type. This could be integer or float
 * according to &lt;T&gt;.
 * @author kapy
 * @author burgetr
 *
 * @param <T> Type of value stored in term
 */
public interface TermNumeric<T extends Number> extends Term<T> {
	
	/**
	 * These are available units in CSS
	 * @author kapy
	 * @author burgetr
	 */
	public enum Unit {
	    none("", Type.none),
    	em("em", Type.length),
    	ex("ex", Type.length),
    	ch("ch", Type.length),
    	rem("rem", Type.length),
    	vw("vw", Type.length),
    	vh("vh", Type.length),
    	vmin("vmin", Type.length),
    	vmax("vmax", Type.length),
    	cm("cm", Type.length),
    	mm("mm", Type.length),
    	q("q", Type.length),
        in("in", Type.length),
    	pt("pt", Type.length),
    	pc("pc", Type.length),
        px("px", Type.length),
        fr("fr", Type.length),
    	deg("deg", Type.angle),
    	rad("rad", Type.angle),
    	grad("grad", Type.angle),
        turn("turn", Type.angle),
    	ms("ms", Type.time),
    	s("s", Type.time),
    	hz("hz", Type.frequency),
    	khz("khz", Type.frequency),
    	dpi("dpi", Type.resolution),
    	dpcm("dpcm", Type.resolution),
    	dppx("dppx", Type.resolution);

	    private static final Map<String, Unit> map;
	    static {
	        map = new HashMap<>(Unit.values().length);
	        for (Unit u : Unit.values()) {
	            map.put(u.value, u);
	        }
	    }
	    
    	private String value;
    	private Type type;
    	
    	private Unit(String value, Type type) { 
    		this.value = value;
    		this.type = type;
    	}
    	
    	public String value() { 
    	    return value;
    	}
    	
        public Type getType() {
            return type;
        }
        
        public static Unit findByValue(String value) {
            return map.get(value);
        }
        
    	public boolean isAngle() {
    		return getType() == Type.angle;
    	}
    	
    	public boolean isLength() {
            return getType() == Type.length;
    	}
    	
    	public boolean isTime() {
            return getType() == Type.time;
    	}
    	
    	public boolean isFrequency() {
            return getType() == Type.frequency;
    	}
    	
    	public boolean isResolution() {
            return getType() == Type.resolution;
    	}

    	/** Unit types */
    	public enum Type {
    	    angle, length, time, frequency, resolution, none
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
