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
    
    //========================================================================
    
    public interface CounterFunction extends TermFunction {
    }
    
    public interface Counter extends CounterFunction {
        public String getName();
        public CSSProperty.ListStyleType getStyle();
    }
    
    public interface Counters extends CounterFunction {
        public String getName();
        public CSSProperty.ListStyleType getStyle();
        public String getSeparator();
    }
    
    public interface Attr extends TermFunction {
        public String getName();
    }
	
	//========================================================================
	
	public interface GridFunction extends TermFunction {
    }

    public interface FitContent extends GridFunction {

        public TermLengthOrPercent getMaximum();
    }

    public interface MinMax extends GridFunction {

        public static class Unit {

            private TermLengthOrPercent _lenght;
            private boolean _isMinContent;
            private boolean _isMaxContent;
            private boolean _isAuto;

            public static Unit createWithLenght(TermLengthOrPercent lenght) {
                return new Unit(lenght, false, false, false);
            }

            public static Unit createWithMinContent() {
                return new Unit(null, true, false, false);
            }

            public static Unit createWithMaxContent() {
                return new Unit(null, false, true, false);
            }

            public static Unit createWithAuto() {
                return new Unit(null, false, false, true);
            }

            private Unit(TermLengthOrPercent lenght, boolean isMinContent, boolean isMaxContent, boolean isAuto) {
                _lenght = lenght;
                _isMinContent = isMinContent;
                _isMaxContent = isMaxContent;
                _isAuto = isAuto;
            }

            public TermLengthOrPercent getLenght() {
                return _lenght;
            }

            public void setLenght(TermLengthOrPercent lenght) {
                _lenght = lenght;
                _isMinContent = false;
                _isMaxContent = false;
                _isAuto = false;
            }

            public boolean isIsMinContent() {
                return _isMinContent;
            }

            public void setIsMinContent(boolean isMinContent) {
                _lenght = null;
                _isMinContent = isMinContent;
                _isMaxContent = false;
                _isAuto = false;
            }

            public boolean isIsMaxContent() {
                return _isMaxContent;
            }

            public void setIsMaxContent(boolean isMaxContent) {
                _lenght = null;
                _isMinContent = false;
                _isMaxContent = isMaxContent;
                _isAuto = false;
            }

            public boolean isIsAuto() {
                return _isAuto;
            }

            public void setIsAuto(boolean isAuto) {
                _lenght = null;
                _isMinContent = false;
                _isMaxContent = false;
                _isAuto = isAuto;
            }
        }

        public Unit getMin();

        public Unit getMax();
    }

    public interface Repeat extends GridFunction {

        public static class Unit {

            private int _numberOfRepetition;
            private boolean _isAutoFit;
            private boolean _isAutoFill;

            public static Unit createWithNRepetitions(int n) {
                return new Unit(n, false, false);
            }

            public static Unit createWithAutoFit() {
                return new Unit(-1, true, false);
            }

            public static Unit createWithAutoFill() {
                return new Unit(-1, false, true);
            }

            private Unit(int numberOfRepetitions, boolean isAutoFit, boolean isAutoFill) {
                _numberOfRepetition = numberOfRepetitions;
                _isAutoFit = isAutoFit;
                _isAutoFill = isAutoFill;
            }

            public Unit setNumberOfRepetition(int n) {
                if (n <= 0) {
                    throw new IllegalArgumentException("Number of repetitions must be positive.");
                }
                _isAutoFit = false;
                _isAutoFill = false;
                _numberOfRepetition = n;
                return this;
            }

            public Unit setAutoFit() {
                _isAutoFit = true;
                _isAutoFill = false;
                _numberOfRepetition = -1;
                return this;
            }

            public Unit setAutoFill() {
                _isAutoFit = false;
                _isAutoFill = true;
                _numberOfRepetition = -1;
                return this;
            }

            public int getNumberOfRepetitions() {
                return _numberOfRepetition;
            }

            public boolean isAutoFit() {
                return _isAutoFit;
            }

            public boolean isAutoFill() {
                return _isAutoFill;
            }
        }

        public Unit getNumberOfRepetitions();

        public List<Term<?>> getRepeatedTerms();
    }
    
    //========================================================================
    
    public interface TimingFunction extends TermFunction {
    }
    
    public interface Steps extends TimingFunction {
        
        public static enum Direction {
            JUMP_START("jump-start"), JUMP_END("jump-end"), JUMP_BOTH("jump-both"), 
            JUMP_NONE("jump-none"), START("start"), END("end");
            
            private final String text;

            private Direction(String text) {
                this.text = text;
            }
            
            @Override
            public String toString() {
                return text;
            }
        } 
        
        public int getNumberOfSteps();
        public Direction getDirection();
    }
    
    public interface Frames extends TimingFunction {
        public int getFrames();
    }
    
    public interface CubicBezier extends TimingFunction {
        public float getX1();
        public float getY1();
        public float getX2();
        public float getY2();
    }
    
}
