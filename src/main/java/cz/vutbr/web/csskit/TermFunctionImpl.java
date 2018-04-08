package cz.vutbr.web.csskit;

import java.util.ArrayList;
import java.util.List;

import org.unbescape.css.CssEscape;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermAngle;
import cz.vutbr.web.css.TermFloatValue;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermInteger;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermNumber;
import cz.vutbr.web.css.TermOperator;

/**
 * TermFunction, holds function
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko
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
        
        private TermLength translateX;
        private TermLength translateY;
        
        public TranslateImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public TermLength getTranslateX() {
            return translateX;
        }

        @Override
        public TermLength getTranslateY() {
            return translateY;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 2 
                    && (translateX = getLengthArg(args.get(0))) != null
                    && (translateY = getLengthArg(args.get(1))) != null) {
                setValid(true);
            } else if (size() == 1 && (translateX = getLengthArg(args.get(0))) != null) {
                translateY = CSSFactory.getTermFactory().createLength(0.0f);
                setValid(true);
            }
            return this;
        }
    }
    
    public static class Translate3dImpl extends TermFunctionImpl implements TermFunction.Translate3d {
        
        private TermLength translateX;
        private TermLength translateY;
        private TermLength translateZ;
        
        public Translate3dImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public TermLength getTranslateX() {
            return translateX;
        }

        @Override
        public TermLength getTranslateY() {
            return translateY;
        }

        @Override
        public TermLength getTranslateZ() {
            return translateZ;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 3 
                    && (translateX = getLengthArg(args.get(0))) != null
                    && (translateY = getLengthArg(args.get(1))) != null
                    && (translateZ = getLengthArg(args.get(2))) != null) {
                setValid(true);
            }
            return this;
        }
    }

    public static class TranslateXImpl extends TermFunctionImpl implements TermFunction.TranslateX {
        
        private TermLength translate;
        
        public TranslateXImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public TermLength getTranslate() {
            return translate;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 1 && (translate = getLengthArg(args.get(0))) != null) {
                setValid(true);
            }
            return this;
        }
    }
    
    public static class TranslateYImpl extends TermFunctionImpl implements TermFunction.TranslateY {
        
        private TermLength translate;
        
        public TranslateYImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public TermLength getTranslate() {
            return translate;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 1 && (translate = getLengthArg(args.get(0))) != null) {
                setValid(true);
            }
            return this;
        }
    }
    
    public static class TranslateZImpl extends TermFunctionImpl implements TermFunction.TranslateZ {
        
        private TermLength translate;
        
        public TranslateZImpl() {
            setValid(false); //arguments are required
        }

        @Override
        public TermLength getTranslate() {
            return translate;
        }

        @Override
        public TermList setValue(List<Term<?>> value)
        {
            super.setValue(value);
            List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, false);
            if (args.size() == 1 && (translate = getLengthArg(args.get(0))) != null) {
                setValid(true);
            }
            return this;
        }
    }

}
