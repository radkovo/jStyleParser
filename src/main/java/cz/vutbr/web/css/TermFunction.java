package cz.vutbr.web.css;

import java.util.List;

/**
 * Holds name of CSS function and terms stored inside
 * @author Jan Svercl, VUT Brno, 2008
 * @author Karel Piwko, 2008
 */
public interface TermFunction extends TermList {

    /**
     * Gets the name of the function.
     * @return The function name.
     */
    public String getFunctionName();
    
    public TermFunction setFunctionName(String functionName);
    
    /**
     * Checks whether the arguments passed using {@code setValue()} are valid for this function.
     * @return {@code true} when the function has valid arguments
     */
    public boolean isValid();
    
    /**
     * Splits the list of arguments to several lists based on the given separator term.
     * @param separator The term used as a separator (typically TermOperator(',').
     * @return A list of term lists corresponding to the individual separated arguments.
     */
    public List<List<Term<?>>> getSeparatedArgs(Term<?> separator);
    
    /**
     * Splits the list of arguments using the given separator term and tries to convert the
     * arguments to typed values (lengths, times, numbers, etc...) 
     * @param separator The term used as a separator (typically TermOperator(',').
     * @return A list of typed numeric values or idents (if keywords allowed) or {@code null} in case that the arguments cannot be converted to values. 
     */
    public List<Term<?>> getSeparatedValues(Term<?> separator, boolean allowKeywords);

    /**
     * Tries to convert the terms to a list of typed values (lengths, times, numbers, etc...). 
     * @return A list of typed numeric values or idents (if keywords allowed) or {@code null} in case that the arguments cannot be converted to values. 
     */
    public List<Term<?>> getValues(boolean allowKeywords);

    
    //========================================================================
    
    public interface TransformFunction extends TermFunction {
        
    }
    
    public interface Matrix extends TransformFunction {
        public float[] getValues();
    }
    
    public interface Matrix3d extends TransformFunction {
        public float[] getValues();
    }
    
    public interface Perspective extends TransformFunction {
        public TermLength getDistance();
    }
    
    public interface Rotate extends TransformFunction {
        public TermAngle getAngle();
    }
    
    public interface Rotate3d extends TransformFunction {
        public float getX();
        public float getY();
        public float getZ();
        public TermAngle getAngle();
    }
    
    public interface RotateX extends TransformFunction {
        public TermAngle getAngle();
    }
    
    public interface RotateY extends TransformFunction {
        public TermAngle getAngle();
    }
    
    public interface RotateZ extends TransformFunction {
        public TermAngle getAngle();
    }
    
    public interface Scale extends TransformFunction {
        public float getScaleX();
        public float getScaleY();
    }
    
    public interface Scale3d extends TransformFunction {
        public float getScaleX();
        public float getScaleY();
        public float getScaleZ();
    }
    
    public interface ScaleX extends TransformFunction {
        public float getScale();
    }
    
    public interface ScaleY extends TransformFunction {
        public float getScale();
    }

    public interface ScaleZ extends TransformFunction {
        public float getScale();
    }

    public interface Skew extends TransformFunction {
        public TermAngle getSkewX();
        public TermAngle getSkewY();
    }
    
    public interface SkewX extends TransformFunction {
        public TermAngle getSkew();
    }
    
    public interface SkewY extends TransformFunction {
        public TermAngle getSkew();
    }
    
    public interface Translate extends TransformFunction {
        public TermLength getTranslateX();
        public TermLength getTranslateY();
    }
    
    public interface Translate3d extends TransformFunction {
        public TermLength getTranslateX();
        public TermLength getTranslateY();
        public TermLength getTranslateZ();
    }
    
    public interface TranslateX extends TransformFunction {
        public TermLength getTranslate();
    }
    
    public interface TranslateY extends TransformFunction {
        public TermLength getTranslate();
    }
    
    public interface TranslateZ extends TransformFunction {
        public TermLength getTranslate();
    }
    
}
