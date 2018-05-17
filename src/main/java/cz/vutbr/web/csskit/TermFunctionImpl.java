package cz.vutbr.web.csskit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.unbescape.css.CssEscape;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermAngle;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermFloatValue;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermInteger;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermLengthOrPercent;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermNumber;
import cz.vutbr.web.css.TermNumeric.Unit;
import cz.vutbr.web.css.TermOperator;
import cz.vutbr.web.css.TermPercent;

/**
 * TermFunction, holds function
 * @author Jan Svercl, VUT Brno, 2008
 * @author Karel Piwko
 * @author Radek Burget
 */
public class TermFunctionImpl extends TermListImpl implements TermFunction {

    protected static final TermOperator DEFAULT_ARG_SEP = CSSFactory.getTermFactory().createOperator(',');

    protected String functionName;
	protected boolean valid;
	
	
    protected TermFunctionImpl() {
        valid = true;
    }
    
    /**
	 * @return the functionName
	 */
    @Override
	public String getFunctionName() {
		return functionName;
	}

	/**
	 * @param functionName the functionName to set
	 */
    @Override
	public TermFunction setFunctionName(String functionName) {
		if(functionName==null)
			throw new IllegalArgumentException("Invalid functionName in function (null)");
		this.functionName = functionName;
		return this;
	}

	@Override
	public boolean isValid() {
	    return valid;
	}
	
	public void setValid(boolean valid) {
	    this.valid = valid;
	}
	
    @Override
    public TermList setValue(List<Term<?>> value) { //TODO the minus operation is duplicate to getSeparatedValues()?
        this.value = new ArrayList<>();
        
        // Treat '-' as modifying the next argument, instead of as an operator
        boolean prevMinus = false;
        
        for (Term<?> term : value) {
            if (term instanceof TermOperator && ((TermOperator) term).getValue() == '-') {
                prevMinus = true;
            } else if (prevMinus) {
                if (prependMinus(term)) {
                    this.value.remove(this.value.size() - 1); // Remove merged minus
                }

                prevMinus = false;
            }
            
            this.value.add(term);
        }
        
        return this;
    }
    
    protected boolean prependMinus(Term<?> term) {
        boolean merged = false;
        
        if (term instanceof TermFloatValue) { // includes TermAngle, TermLength, etc.
            TermFloatValue floatT = (TermFloatValue) term;
            floatT.setValue(-1 * floatT.getValue());
            merged = true;
        } else if (term instanceof TermIdent) {
            TermIdent ident = (TermIdent) term;
            ident.setValue("-" + ident.getValue());
            merged = true;
        } else if (term instanceof TermFunction) {
            TermFunction func = (TermFunction) term;
            func.setFunctionName("-" + func.getFunctionName());
            merged = true;
        }
        
        return merged;
    }

	@Override
    public List<List<Term<?>>> getSeparatedArgs(Term<?> separator) {
        List<List<Term<?>>> ret = new ArrayList<>();
        List<Term<?>> cur = new ArrayList<>();
        for (Term<?> t : this) {
            if (t.equals(separator)) {
                ret.add(cur);
                cur = new ArrayList<>();
            } else {
                cur.add(t);
            }
        }
        if (!cur.isEmpty())
            ret.add(cur);
        
        return ret;
    }

    @Override
    public List<Term<?>> getSeparatedValues(Term<?> separator, boolean allowKeywords) {
        List<Term<?>> ret = new ArrayList<>();
        TermOperator curOp = null; //an optional unary operator before the value
        Term<?> curVal = null;
        for (Term<?> t : this) {
            if (t.equals(separator)) {
                if (curVal != null) {
                    if (curOp != null) {
                        if (curVal instanceof TermFloatValue) {
                            if (curOp.getValue() == '-') {
                                Float newVal = -((TermFloatValue) curVal).getValue();
                                curVal = (TermFloatValue) curVal.shallowClone();
                                ((TermFloatValue) curVal).setValue(newVal);
                            } else if (curOp.getValue() != '+') {
                                return null; //invalid operator
                            }
                        }
                        else
                            return null; //operator combined with ident
                    }
                    ret.add(curVal);
                    curVal = null;
                    curOp = null;
                }
                else
                    return null; //value missing
            } else if (t instanceof TermOperator) {
                if (curOp == null && curVal == null)
                    curOp = (TermOperator) t;
                else
                    return null;
            } else if (t instanceof TermFloatValue) {
                if (curVal == null)
                    curVal = t;
                else
                    return null;
            } else if (allowKeywords && t instanceof TermIdent) {
                if (curVal == null)
                    curVal = t;
                else
                    return null;
            } else
                return null;
        }
        
        //the last value
        if (curVal != null) {
            if (curOp != null) {
                if (curVal instanceof TermFloatValue) {
                    if (curOp.getValue() == '-') {
                        Float newVal = -((TermFloatValue) curVal).getValue();
                        curVal = (TermFloatValue) curVal.shallowClone();
                        ((TermFloatValue) curVal).setValue(newVal);
                    } else if (curOp.getValue() != '+') {
                        return null; //invalid operator
                    }
                }
                else
                    return null; //operator combined with ident
            }
            ret.add(curVal);
        }
        else
            return null; //value missing
        
        return ret;
    }

    @Override
    public List<Term<?>> getValues(boolean allowKeywords)
    {
        List<Term<?>> ret = new ArrayList<>();
        TermOperator curOp = null; //an optional unary operator before the value
        for (Term<?> t : this) {
            if (t instanceof TermOperator) {
                if (curOp == null)
                    curOp = (TermOperator) t;
                else
                    return null; //repeating operator
            } else if (t instanceof TermFloatValue) {
                TermFloatValue curVal = (TermFloatValue) t;
                if (curOp != null) {
                    if (curOp.getValue() == '-') {
                        Float newVal = -curVal.getValue();
                        curVal = (TermFloatValue) curVal.shallowClone();
                        curVal.setValue(newVal);
                    } else if (curOp.getValue() != '+') {
                        return null; //invalid operator
                    }
                }
                ret.add(curVal);
                curVal = null;
                curOp = null;
            } else if (t instanceof TermIdent) {
                if (curOp == null)
                    ret.add(t);
                else
                    return null; //operator combined with ident
            } else
                return null; //invalid term
        }
        
        if (curOp != null)
            return null; //an operator followed with no value
        
        return ret;
    }

    @Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		// append operator
		if(operator!=null) sb.append(operator.value());
		
		sb.append(CssEscape.escapeCssIdentifier(functionName)).append(OutputUtil.FUNCTION_OPENING);
		sb = OutputUtil.appendFunctionArgs(sb, value)
			.append(OutputUtil.FUNCTION_CLOSING);
		
		return sb.toString();
    }


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((functionName == null) ? 0 : functionName.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof TermFunctionImpl))
			return false;
		TermFunctionImpl other = (TermFunctionImpl) obj;
		if (functionName == null) {
			if (other.functionName != null)
				return false;
		} else if (!functionName.equals(other.functionName))
			return false;
		return true;
	}
	
	//========================================================================
	
    protected boolean isNumberArg(Term<?> term) {
        return term instanceof TermNumber || term instanceof TermInteger;
    }

    protected float getNumberArg(Term<?> term) {
        if (term instanceof TermNumber)
            return ((TermNumber) term).getValue();
        else
            return ((TermInteger) term).getValue();
    }
	
    protected TermAngle getAngleArg(Term<?> term) {
        if (term instanceof TermAngle)
            return (TermAngle) term;
        else if (isNumberArg(term) && getNumberArg(term) == 0)
            return CSSFactory.getTermFactory().createAngle(0.0f);
        else
            return null;
    }
    
    protected TermLength getLengthArg(Term<?> term) {
        if (term instanceof TermLength)
            return (TermLength) term;
        else if (isNumberArg(term) && getNumberArg(term) == 0)
            return CSSFactory.getTermFactory().createLength(0.0f);
        else
            return null;
    }
    
    protected TermLengthOrPercent getLengthOrPercentArg(Term<?> term) {
        if (term instanceof TermLengthOrPercent)
            return (TermLengthOrPercent) term;
        else if (isNumberArg(term) && getNumberArg(term) == 0)
            return CSSFactory.getTermFactory().createLength(0.0f);
        else
            return null;
    }
    
    protected TermAngle convertSideOrCorner(List<Term<?>> aarg) {
        if (aarg.size() > 1 && aarg.size() <= 3) {
            TermAngle angle = null;
            Term<?> toTerm = aarg.get(0);
            Term<?> dir1 = aarg.get(1);
            Term<?> dir2 = (aarg.size() == 3) ? aarg.get(2) : null;
            if (toTerm instanceof TermIdent && toTerm.toString().equals("to")
                    && dir1 instanceof TermIdent
                    && (dir2 == null || dir2 instanceof TermIdent)) {
                
                final TermFactory tf = CSSFactory.getTermFactory();
                
                switch (dir1.toString()) {
                    case "top":
                        if (dir2 == null)
                            angle = tf.createAngle("0", Unit.deg, 1);
                        else if (dir2.toString().equals("left"))
                            angle = tf.createAngle("315", Unit.deg, 1);
                        else if (dir2.toString().equals("right"))
                            angle = tf.createAngle("45", Unit.deg, 1);
                        break;
                    case "right":
                        if (dir2 == null)
                            angle = tf.createAngle("90", Unit.deg, 1);
                        else if (dir2.toString().equals("top"))
                            angle = tf.createAngle("45", Unit.deg, 1);
                        else if (dir2.toString().equals("bottom"))
                            angle = tf.createAngle("135", Unit.deg, 1);
                        break;
                    case "bottom":
                        if (dir2 == null)
                            angle = tf.createAngle("180", Unit.deg, 1);
                        else if (dir2.toString().equals("left"))
                            angle = tf.createAngle("225", Unit.deg, 1);
                        else if (dir2.toString().equals("right"))
                            angle = tf.createAngle("135", Unit.deg, 1);
                        break;
                    case "left":
                        if (dir2 == null)
                            angle = tf.createAngle("270", Unit.deg, 1);
                        else if (dir2.toString().equals("top"))
                            angle = tf.createAngle("315", Unit.deg, 1);
                        else if (dir2.toString().equals("bottom"))
                            angle = tf.createAngle("225", Unit.deg, 1);
                        break;
                }
            }
            return angle;
        }
        else
            return null;
    }

    /**
     * Loads the color stops from the gunction arguments.
     * @param args the comma-separated function arguments
     * @param firstStop the first argument to start with
     * @return the list of color stops or {@code null} when the arguments are invalid or missing
     */
    protected List<TermFunction.Gradient.ColorStop> loadColorStops(List<List<Term<?>>> args, int firstStop)
    {
        boolean valid = true;
        List<TermFunction.Gradient.ColorStop> colorStops = null;
        if (args.size() > firstStop) {
            colorStops = new ArrayList<>();
            for (int i = firstStop; valid && i < args.size(); i++) {
                List<Term<?>> sarg = args.get(i);
                if (sarg.size() == 1 || sarg.size() == 2) {
                    Term<?> tclr = sarg.get(0);
                    Term<?> tlen = (sarg.size() == 2) ? sarg.get(1) : null;
                    if (tclr instanceof TermColor
                            && (tlen == null || tlen instanceof TermLengthOrPercent)) {
                        TermFunction.Gradient.ColorStop newStop = new ColorStopImpl((TermColor) tclr, (TermLengthOrPercent) tlen);
                        colorStops.add(newStop);
                    } else {
                        valid = false;
                    }
                } else {
                    valid = false;
                }
            }
        }
        if (valid && colorStops != null && !colorStops.isEmpty())
            return colorStops;
        else
            return null;
    }
    
    
    //========================================================================
    
    public static class MatrixImpl extends TermFunctionImpl implements TermFunction.Matrix {
        
        private float[] values;
        
        public MatrixImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public float[] getValues() {
            return values;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 6) {
                values = new float[6];
                setValid(true);
                for (int i = 0; i < 6; i++) {
                    if (isNumberArg(args.get(i)))
                        values[i] = getNumberArg(args.get(i));
                    else
                        setValid(false);
                }
            }
            return this;
        }
    }
    
    public static class Matrix3dImpl extends TermFunctionImpl implements TermFunction.Matrix3d {
        
        private float[] values;
        
        public Matrix3dImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public float[] getValues() {
            return values;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 16) {
                values = new float[16];
                setValid(true);
                for (int i = 0; i < 16; i++) {
                    if (isNumberArg(args.get(i)))
                        values[i] = getNumberArg(args.get(i));
                    else
                        setValid(false);
                }
            }
            return this;
        }
    }
    
    public static class PerspectiveImpl extends TermFunctionImpl implements TermFunction.Perspective {
        
        private TermLength distance;
        
        public PerspectiveImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public TermLength getDistance() {
            return distance;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 1 && (distance = getLengthArg(args.get(0))) != null) {
                setValid(true);
            }
            return this;
        }
    }
    
    public static class RotateImpl extends TermFunctionImpl implements TermFunction.Rotate {
        
        private TermAngle angle;
        
        public RotateImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public TermAngle getAngle() {
            return angle;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 1 && (angle = getAngleArg(args.get(0))) != null) {
                setValid(true);
            }
            return this;
        }
    }
    
    public static class Rotate3dImpl extends TermFunctionImpl implements TermFunction.Rotate3d {

        private float x;
        private float y;
        private float z;
        private TermAngle angle;
        
        public Rotate3dImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public float getX() {
            return x;
        }

        @Override
        public float getY() {
            return y;
        }


        @Override
        public float getZ() {
            return z;
        }

        @Override
        public TermAngle getAngle() {
            return angle;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 4 
                    && isNumberArg(args.get(0)) && isNumberArg(args.get(1)) && isNumberArg(args.get(2))
                    && (angle = getAngleArg(args.get(3))) != null) {
                x = getNumberArg(args.get(0));
                y = getNumberArg(args.get(1));
                z = getNumberArg(args.get(2));
                setValid(true);
            } 
            return this;
        }
    }
    
    public static class RotateXImpl extends TermFunctionImpl implements TermFunction.RotateX {
        
        private TermAngle angle;
        
        public RotateXImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public TermAngle getAngle() {
            return angle;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 1 && (angle = getAngleArg(args.get(0))) != null) {
                setValid(true);
            }
            return this;
        }
    }
    
    public static class RotateYImpl extends TermFunctionImpl implements TermFunction.RotateY {
        
        private TermAngle angle;
        
        public RotateYImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public TermAngle getAngle() {
            return angle;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 1 && (angle = getAngleArg(args.get(0))) != null) {
                setValid(true);
            }
            return this;
        }
    }
    
    public static class RotateZImpl extends TermFunctionImpl implements TermFunction.RotateZ {
        
        private TermAngle angle;
        
        public RotateZImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public TermAngle getAngle() {
            return angle;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 1 && (angle = getAngleArg(args.get(0))) != null) {
                setValid(true);
            }
            return this;
        }
    }
    
	public static class ScaleImpl extends TermFunctionImpl implements TermFunction.Scale {
	    
	    private float scaleX;
	    private float scaleY;
	    
	    public ScaleImpl() {
	        setValid(false); //arguments are required
	    }

        @Override
	    public float getScaleX() {
            return scaleX;
        }

        @Override
        public float getScaleY() {
            return scaleY;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 2 && isNumberArg(args.get(0)) && isNumberArg(args.get(1))) {
                scaleX = getNumberArg(args.get(0));
                scaleY = getNumberArg(args.get(1));
                setValid(true);
            } else if (size() == 1 && isNumberArg(args.get(0))) {
                scaleX = scaleY = getNumberArg(args.get(0));
                setValid(true);
            }
            return this;
        }
	}
	
    public static class Scale3dImpl extends TermFunctionImpl implements TermFunction.Scale3d {
        
        private float scaleX;
        private float scaleY;
        private float scaleZ;
        
        public Scale3dImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public float getScaleX() {
            return scaleX;
        }

        @Override
        public float getScaleY() {
            return scaleY;
        }

        @Override
        public float getScaleZ() {
            return scaleZ;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 3 
                    && isNumberArg(args.get(0)) && isNumberArg(args.get(1)) && isNumberArg(args.get(2))) {
                scaleX = getNumberArg(args.get(0));
                scaleY = getNumberArg(args.get(1));
                scaleZ = getNumberArg(args.get(2));
                setValid(true);
            }
            return this;
        }
    }
    
    public static class ScaleXImpl extends TermFunctionImpl implements TermFunction.ScaleX {
        
        private float scale;
        
        public ScaleXImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public float getScale() {
            return scale;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 1 && isNumberArg(args.get(0))) {
                scale = getNumberArg(args.get(0));
                setValid(true);
            }
            return this;
        }
    }
	
    public static class ScaleYImpl extends TermFunctionImpl implements TermFunction.ScaleY {
        
        private float scale;
        
        public ScaleYImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public float getScale() {
            return scale;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 1 && isNumberArg(args.get(0))) {
                scale = getNumberArg(args.get(0));
                setValid(true);
            }
            return this;
        }
    }
    
    public static class ScaleZImpl extends TermFunctionImpl implements TermFunction.ScaleZ {
        
        private float scale;
        
        public ScaleZImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public float getScale() {
            return scale;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 1 && isNumberArg(args.get(0))) {
                scale = getNumberArg(args.get(0));
                setValid(true);
            }
            return this;
        }
    }
    
    public static class SkewImpl extends TermFunctionImpl implements TermFunction.Skew {
        
        private TermAngle skewX;
        private TermAngle skewY;
        
        public SkewImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public TermAngle getSkewX() {
            return skewX;
        }

        @Override
        public TermAngle getSkewY() {
            return skewY;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 2 
                    && (skewX = getAngleArg(args.get(0))) != null
                    && (skewY = getAngleArg(args.get(1))) != null) {
                setValid(true);
            } else if (size() == 1 && (skewX = getAngleArg(args.get(0))) != null) {
                skewY = CSSFactory.getTermFactory().createAngle(0.0f);
                setValid(true);
            }
            return this;
        }
    }
    
    public static class SkewXImpl extends TermFunctionImpl implements TermFunction.SkewX {
        
        private TermAngle skew;
        
        public SkewXImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public TermAngle getSkew() {
            return skew;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 1 && (skew = getAngleArg(args.get(0))) != null) {
                setValid(true);
            }
            return this;
        }
    }
    
    public static class SkewYImpl extends TermFunctionImpl implements TermFunction.SkewY {
        
        private TermAngle skew;
        
        public SkewYImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public TermAngle getSkew() {
            return skew;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 1 && (skew = getAngleArg(args.get(0))) != null) {
                setValid(true);
            }
            return this;
        }
    }
    
    public static class TranslateImpl extends TermFunctionImpl implements TermFunction.Translate {
        
        private TermLengthOrPercent translateX;
        private TermLengthOrPercent translateY;
        
        public TranslateImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public TermLengthOrPercent getTranslateX() {
            return translateX;
        }

        @Override
        public TermLengthOrPercent getTranslateY() {
            return translateY;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 2 
                    && (translateX = getLengthOrPercentArg(args.get(0))) != null
                    && (translateY = getLengthOrPercentArg(args.get(1))) != null) {
                setValid(true);
            } else if (size() == 1 && (translateX = getLengthOrPercentArg(args.get(0))) != null) {
                translateY = CSSFactory.getTermFactory().createLength(0.0f);
                setValid(true);
            }
            return this;
        }
    }
    
    public static class Translate3dImpl extends TermFunctionImpl implements TermFunction.Translate3d {
        
        private TermLengthOrPercent translateX;
        private TermLengthOrPercent translateY;
        private TermLengthOrPercent translateZ;
        
        public Translate3dImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public TermLengthOrPercent getTranslateX() {
            return translateX;
        }

        @Override
        public TermLengthOrPercent getTranslateY() {
            return translateY;
        }

        @Override
        public TermLengthOrPercent getTranslateZ() {
            return translateZ;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 3 
                    && (translateX = getLengthOrPercentArg(args.get(0))) != null
                    && (translateY = getLengthOrPercentArg(args.get(1))) != null
                    && (translateZ = getLengthOrPercentArg(args.get(2))) != null) {
                setValid(true);
            }
            return this;
        }
    }

    public static class TranslateXImpl extends TermFunctionImpl implements TermFunction.TranslateX {
        
        private TermLengthOrPercent translate;
        
        public TranslateXImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public TermLengthOrPercent getTranslate() {
            return translate;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 1 && (translate = getLengthOrPercentArg(args.get(0))) != null) {
                setValid(true);
            }
            return this;
        }
    }
    
    public static class TranslateYImpl extends TermFunctionImpl implements TermFunction.TranslateY {
        
        private TermLengthOrPercent translate;
        
        public TranslateYImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public TermLengthOrPercent getTranslate() {
            return translate;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 1 && (translate = getLengthOrPercentArg(args.get(0))) != null) {
                setValid(true);
            }
            return this;
        }
    }
    
    public static class TranslateZImpl extends TermFunctionImpl implements TermFunction.TranslateZ {
        
        private TermLengthOrPercent translate;
        
        public TranslateZImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public TermLengthOrPercent getTranslate() {
            return translate;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 1 && (translate = getLengthOrPercentArg(args.get(0))) != null) {
                setValid(true);
            }
            return this;
        }
    }
    
    //========================================================================

    public static class ColorStopImpl implements TermFunction.Gradient.ColorStop {
        private TermColor color;
        private TermLengthOrPercent length;
        
        protected ColorStopImpl(TermColor color, TermLengthOrPercent length) {
            this.color = color;
            this.length = length;
        }

        @Override
        public TermColor getColor()
        {
            return color;
        }

        @Override
        public TermLengthOrPercent getLength()
        {
            return length;
        }
    }

    public static class LinearGradientImpl extends TermFunctionImpl implements TermFunction.LinearGradient {
        private TermAngle angle;
        private List<TermFunction.Gradient.ColorStop> colorStops;
        
        public LinearGradientImpl() {
            setValid(false);
        }
        
        @Override
        public TermAngle getAngle() {
            return angle;
        }

        @Override
        public List<ColorStop> getColorStops() {
            return colorStops;
        }
        
        @Override
        public TermList setValue(List<Term<?>> value) {
            super.setValue(value);
            List<List<Term<?>>> args = getSeparatedArgs(DEFAULT_ARG_SEP);
            if (args.size() > 1) {
                int firstStop = 0;
                //check for an angle
                List<Term<?>> aarg = args.get(0);
                if (aarg.size() == 1 && (angle = getAngleArg(aarg.get(0))) != null) {
                    firstStop = 1;
                } else if ((angle = convertSideOrCorner(aarg)) != null) {
                    firstStop = 1;
                }
                //check for stops
                colorStops = loadColorStops(args, firstStop);
                if (colorStops != null)
                    setValid(true);
            }
            return this;
        }
    }
    
    public static class RepeatingLinearGradientImpl extends TermFunctionImpl implements TermFunction.RepeatingLinearGradient {
        private TermAngle angle;
        private List<TermFunction.Gradient.ColorStop> colorStops;
        
        public RepeatingLinearGradientImpl() {
            setValid(false);
        }
        
        @Override
        public TermAngle getAngle() {
            return angle;
        }

        @Override
        public List<ColorStop> getColorStops() {
            return colorStops;
        }
        
        @Override
        public TermList setValue(List<Term<?>> value) {
            super.setValue(value);
            List<List<Term<?>>> args = getSeparatedArgs(DEFAULT_ARG_SEP);
            if (args.size() > 1) {
                int firstStop = 0;
                //check for an angle
                List<Term<?>> aarg = args.get(0);
                if (aarg.size() == 1 && (angle = getAngleArg(aarg.get(0))) != null) {
                    firstStop = 1;
                } else if ((angle = convertSideOrCorner(aarg)) != null) {
                    firstStop = 1;
                }
                //check for stops
                colorStops = loadColorStops(args, firstStop);
                if (colorStops != null)
                    setValid(true);
            }
            return this;
        }
    }

    public static class RadialGradientImpl extends TermFunctionImpl implements TermFunction.RadialGradient {
        
        //default property values
        private static final TermPercent DEFAULT_POSITION = CSSFactory.getTermFactory().createPercent(50.0f);
        private static final TermIdent DEFAULT_SHAPE = CSSFactory.getTermFactory().createIdent("ellipse");
        private static final TermIdent CIRCLE_SHAPE = CSSFactory.getTermFactory().createIdent("circle");
        private static final TermIdent DEFAULT_SIZE = CSSFactory.getTermFactory().createIdent("farthest-corner");
        
        private TermIdent shape;
        private TermLengthOrPercent[] size;
        private TermIdent sizeIdent;
        private TermLengthOrPercent[] position;
        private List<TermFunction.Gradient.ColorStop> colorStops;
        
        public RadialGradientImpl() {
            setValid(false);
        }
        
        @Override
        public TermIdent getShape() {
            return shape;
        }
        
        @Override
        public TermLengthOrPercent[] getSize() {
            return size;
        }

        @Override
        public TermIdent getSizeIdent() {
            return sizeIdent;
        }

        @Override
        public TermLengthOrPercent[] getPosition() {
            return position;
        }

        @Override
        public List<ColorStop> getColorStops() {
            return colorStops;
        }
        
        @Override
        public TermList setValue(List<Term<?>> value) {
            super.setValue(value);
            List<List<Term<?>>> args = getSeparatedArgs(DEFAULT_ARG_SEP);
            if (args.size() > 1) {
                int firstStop = 0;
                //check for shape and size
                if (decodeShape(args.get(0))) {
                    firstStop = 1;
                } else { //no shape info provided, use defaults
                    sizeIdent = DEFAULT_SIZE;
                    shape = DEFAULT_SHAPE;
                    position = new TermLengthOrPercent[2];
                    position[0] = position[1] = DEFAULT_POSITION;
                }
                //check for stops
                colorStops = loadColorStops(args, firstStop);
                if (colorStops != null)
                    setValid(true);
            }
            return this;
        }

        private boolean decodeShape(List<Term<?>> arglist) {
            List<Term<?>> args = new ArrayList<>(arglist);
            //determine the 'at' position
            int atpos = -1;
            for (int i = 0; i < args.size(); i++) {
                Term<?> arg = args.get(i);
                if (arg instanceof TermIdent && ((TermIdent) arg).getValue().equalsIgnoreCase("at")) {
                    atpos = i;
                    break;
                }
            }
            if (atpos != -1) {
                List<Term<?>> posList = args.subList(atpos + 1, args.size());
                if (!decodePosition(posList))
                    return false;
                args = args.subList(0, atpos);
            } else { //no position, use the default (center)
                position = new TermLengthOrPercent[2];
                position[0] = position[1] = DEFAULT_POSITION;
            }
            //determine the shape
            boolean isCircle = false;
            for (Iterator<Term<?>> it = args.iterator(); it.hasNext();)
            {
                Term<?> arg = it.next();
                String idval = (arg instanceof TermIdent) ? ((TermIdent) arg).getValue() : null;
                if (idval != null && (idval.equalsIgnoreCase("circle") || idval.equalsIgnoreCase("ellipse"))) {
                    shape = (TermIdent) arg;
                    isCircle = idval.equalsIgnoreCase("circle");
                    it.remove();
                    break;
                }
            }
            //determine the size
            if (shape == null) { //shape not given
                if (args.size() == 0) {
                    sizeIdent = DEFAULT_SIZE;
                    shape = DEFAULT_SHAPE;
                } else if (args.size() == 1) {
                    Term<?> arg = args.get(0);
                    if (isExtentKeyword(arg)) {
                        sizeIdent = (TermIdent) arg;
                        shape = DEFAULT_SHAPE; //see https://drafts.csswg.org/css-images-3/#radial-gradients
                    } else if (arg instanceof TermLength) {
                        size = new TermLengthOrPercent[1];
                        size[0] = (TermLength) arg;
                        shape = CIRCLE_SHAPE;
                    } else {
                        return false;
                    }
                } else if (args.size() == 2) {
                    size = new TermLengthOrPercent[2];
                    int i = 0;
                    for (Term<?> arg : args) {
                        if (arg instanceof TermLengthOrPercent) {
                            size[i++] = (TermLengthOrPercent) arg;
                        } else {
                            size = null;
                            return false;
                        }
                    }
                    shape = DEFAULT_SHAPE;
                } else {
                    return false;
                }
            } else if (!isCircle) { //ellipse
                if (args.size() == 0) {
                    sizeIdent = DEFAULT_SIZE;
                } else if (args.size() == 1) {
                    Term<?> arg = args.get(0);
                    if (isExtentKeyword(arg)) {
                        sizeIdent = (TermIdent) arg;
                    } else {
                        return false;
                    }
                } else if (args.size() == 2) {
                    size = new TermLengthOrPercent[2];
                    int i = 0;
                    for (Term<?> arg : args) {
                        if (arg instanceof TermLengthOrPercent) {
                            size[i++] = (TermLengthOrPercent) arg;
                        } else {
                            size = null;
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            } else { //circle
                if (args.size() == 0) {
                    sizeIdent = DEFAULT_SIZE;
                } else if (args.size() == 1) {
                    Term<?> arg = args.get(0);
                    if (isExtentKeyword(arg)) {
                        sizeIdent = (TermIdent) arg;
                    } else if (arg instanceof TermLength) {
                        size = new TermLengthOrPercent[1];
                        size[0] = (TermLength) arg;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            //parsed ok
            return true;
        }
        
        private boolean decodePosition(List<Term<?>> arglist) {
            if (arglist.size() == 1 || arglist.size() == 2) {
                
                position = new TermLengthOrPercent[2];
                //distribute terms to position
                for (Term<?> term : arglist) {
                    if (term instanceof TermIdent || term instanceof TermLengthOrPercent) {
                        storeBackgroundPosition(position, term);
                    } else {
                        return false;
                    }
                }
                //check validity
                int assigned = 0;
                int valid = 0;
                for (int i = 0; i < 2; i++) {
                    if (position[i] == null) {
                        position[i] = DEFAULT_POSITION;
                        valid++;
                    } else if (position[i] instanceof TermLengthOrPercent) {
                        assigned++;
                        valid++;
                    }
                }
                return (assigned > 0 && valid == 2);
                
            } else {
                return false;
            }
        }
        
        private void storeBackgroundPosition(Term<?>[] storage, Term<?> term) {
            
            if (term instanceof TermIdent) {
                String idval = ((TermIdent) term).getValue();
                TermFactory tf = CSSFactory.getTermFactory();
                if (idval.equalsIgnoreCase("left"))
                    setPositionValue(storage, 0, tf.createPercent(0.0f));
                else if (idval.equalsIgnoreCase("right"))
                    setPositionValue(storage, 0, tf.createPercent(100.0f));
                else if (idval.equalsIgnoreCase("top"))
                    setPositionValue(storage, 1, tf.createPercent(0.0f));
                else if (idval.equalsIgnoreCase("bottom"))
                    setPositionValue(storage, 1, tf.createPercent(100.0f));
                else if (idval.equalsIgnoreCase("center"))
                    setPositionValue(storage, -1, tf.createPercent(50.0f));
            }
            else
                setPositionValue(storage, -1, term);
        }
        
        private void setPositionValue(Term<?>[] s, int index, Term<?> term) {
            switch (index) {
                case -1: if (s[0] == null) //any position - use the free position
                             s[0] = term;
                         else
                             s[1] = term;
                         break;
                case 0: if (s[0] != null) //if the position is occupied, move the old value
                            s[1] = s[0];
                        s[0] = term;
                        break;
                case 1: if (s[1] != null)
                            s[0] = s[1];
                        s[1] = term;
                        break;
            }
        }
        
        private boolean isExtentKeyword(Term<?> term) {
            if (term instanceof TermIdent) {
                switch (((TermIdent) term).getValue()) {
                    case "closest-corner":
                    case "closest-side":
                    case "farthest-corner":
                    case "farthest-side":
                        return true;
                    default:
                        return false;
                }
            } else {
                return false;
            }
        }
        
    }
    
}
