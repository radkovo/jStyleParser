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
        public TermLengthOrPercent getTranslateX();
        public TermLengthOrPercent getTranslateY();
    }
    
    public interface Translate3d extends TransformFunction {
        public TermLengthOrPercent getTranslateX();
        public TermLengthOrPercent getTranslateY();
        public TermLengthOrPercent getTranslateZ();
    }
    
    public interface TranslateX extends TransformFunction {
        public TermLengthOrPercent getTranslate();
    }
    
    public interface TranslateY extends TransformFunction {
        public TermLengthOrPercent getTranslate();
    }
    
    public interface TranslateZ extends TransformFunction {
        public TermLengthOrPercent getTranslate();
    }

    //========================================================================
    
    public interface Gradient extends TermFunction {

        public interface ColorStop {
            public TermColor getColor();
            public TermLengthOrPercent getLength();
        }
        
        /** Is this a repeating gradient? */
        public boolean isRepeating();
    }

    public interface LinearGradient extends Gradient {
        public TermAngle getAngle();
        public List<Gradient.ColorStop> getColorStops();
    }
    
    public interface RadialGradient extends Gradient {
        /** Obtains the shape (ELLIPSE or CIRCLE) */
        public TermIdent getShape();
        /** Obtains the circle/ellipse sizes (one value for circle, two for ellipse) */
        public TermLengthOrPercent[] getSize();
        /** Obtains the circle/ellipse size specified by an identifier (e.g. closest-side) */
        public TermIdent getSizeIdent();
        /** Obtains the 'at' position (always two values) */
        public TermLengthOrPercent[] getPosition();
        /** Obtains the color stops */
        public List<Gradient.ColorStop> getColorStops();
    }
    
    //========================================================================
    
    public interface FilterFunction extends TermFunction {
    }
    
    public interface Blur extends FilterFunction {
        public TermLength getRadius();
    }
    
    public interface Brightness extends FilterFunction {
        public float getAmount();
    }
    
    public interface Contrast extends FilterFunction {
        public float getAmount();
    }
    
    public interface DropShadow extends FilterFunction {
        public TermLength getOffsetX();
        public TermLength getOffsetY();
        public TermLength getBlurRadius();
        public TermColor getColor();
    }
    
    public interface Grayscale extends FilterFunction {
        public float getAmount();
    }
    
    public interface HueRotate extends FilterFunction {
        public TermAngle getAngle();
    }

    public interface Invert extends FilterFunction {
        public float getAmount();
    }
    
    public interface Opacity extends FilterFunction {
        public float getAmount();
    }
    
    public interface Saturate extends FilterFunction {
        public float getAmount();
    }
    
    public interface Sepia extends FilterFunction {
        public float getAmount();
    }
    
}
