package cz.vutbr.web.csskit;

import java.net.URL;
import java.util.List;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermAngle;
import cz.vutbr.web.css.TermCalc;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermExpression;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermFrequency;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermInteger;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermNumber;
import cz.vutbr.web.css.TermNumeric;
import cz.vutbr.web.css.TermPair;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.css.TermRect;
import cz.vutbr.web.css.TermResolution;
import cz.vutbr.web.css.TermString;
import cz.vutbr.web.css.TermTime;
import cz.vutbr.web.css.TermURI;
import cz.vutbr.web.css.TermUnicodeRange;
import cz.vutbr.web.css.TermNumeric.Unit;
import cz.vutbr.web.css.TermOperator;

public class TermFactoryImpl implements TermFactory {

	private static final TermFactory instance;

	static {
		instance = new TermFactoryImpl();
	}

	public static final TermFactory getInstance() {
		return instance;
	}

	private TermFactoryImpl() {
	}

	public TermAngle createAngle(Float value) {
		return (TermAngle) (new TermAngleImpl()).setValue(value);
	}

	public TermAngle createAngle(String value, Unit unit, int unary) {
		return (TermAngle) (new TermAngleImpl()).setUnit(unit).setValue(
				convertFloat(value, unit.value(), unary));
	}

	@Override
    public TermCalc createCalc(List<Term<?>> args) {
        CalcArgs cargs = new CalcArgs(args);
        if (cargs.isValid())
        {
            switch (cargs.getType()) {
                case length:
                    return new TermCalcLengthImpl(cargs);
                case angle:
                    return new TermCalcAngleImpl(cargs);
                case frequency:
                    return new TermCalcFrequencyImpl(cargs);
                case resolution:
                    return new TermCalcResolutionImpl(cargs);
                case time:
                    return new TermCalcTimeImpl(cargs);
                case none:
                    if (cargs.isInt())
                        return new TermCalcIntegerImpl(cargs);
                    else
                        return new TermCalcNumberImpl(cargs);
            }
        }
        return null;
    }

    public TermColor createColor(TermIdent ident) {
		return TermColorImpl.getColorByIdent(ident);
	}

	public TermColor createColor(String hash) {
		return TermColorImpl.getColorByHash(hash);
	}

	public TermColor createColor(int r, int g, int b) {
		return new TermColorImpl(r, g, b);
	}

    public TermColor createColor(int r, int g, int b, int a) {
        return new TermColorImpl(r, g, b, a);
    }

	public TermColor createColor(TermFunction function) {
		return TermColorImpl.getColorByFunction(function);
	}

	public TermFrequency createFrequency(Float value) {
		return (TermFrequency) (new TermFrequencyImpl()).setValue(value);
	}

	public TermFrequency createFrequency(String value, Unit unit, int unary) {
		return (TermFrequency) (new TermFrequencyImpl()).setUnit(unit)
				.setValue(convertFloat(value, unit.value(), unary));
	}

    public TermExpression createExpression(String expr) {
        return (new TermExpressionImpl()).setValue(expr);
    }
    
	public TermFunction createFunction(String name) {
		return createFunctionByName(name, null);
	}

    public TermFunction createFunction(String name, List<Term<?>> args) {
        return createFunctionByName(name, args);
    }

    public TermIdent createIdent(String value) {
        return (TermIdent) (new TermIdentImpl()).setValue(value);
    }
    
	public TermIdent createIdent(String value, boolean dash) {
	    if (!dash)
	        return (TermIdent) (new TermIdentImpl()).setValue(value);
	    else
            return (TermIdent) (new TermIdentImpl()).setValue("-" + value);
	}

	public TermInteger createInteger(Integer value) {
		return (TermInteger) (new TermIntegerImpl()).setValue(value);
	}

	public TermInteger createInteger(String value, int unary) {
		return (TermInteger) (new TermIntegerImpl()).setValue(convertInteger(
				value, null, unary));
	}

	public TermLength createLength(Float value) {
		return (TermLength) (new TermLengthImpl()).setValue(value);
	}

    public TermLength createLength(Float value, Unit unit) {
        return (TermLength) (new TermLengthImpl()).setUnit(unit).setValue(value);
    }

	public TermLength createLength(String value, Unit unit, int unary) {
		return (TermLength) (new TermLengthImpl()).setUnit(unit).setValue(
				convertFloat(value, unit.value(), unary));
	}

	public TermList createList() {
		return new TermListImpl();
	}

	public TermList createList(int initialSize) {
		return new TermListImpl(initialSize);
	}

	public TermNumber createNumber(Float value) {
		return (TermNumber) (new TermNumberImpl()).setValue(value);
	}

	public TermNumber createNumber(String value, int unary) {
		return (TermNumber) (new TermNumberImpl()).setValue(convertFloat(value,
				null, unary));
	}

	public TermNumeric<?> createNumeric(String value, int unary) {

		try {
			return createInteger(value, unary);
		} catch (IllegalArgumentException e) {
			return createNumber(value, unary);
		}
	}

    public TermResolution createResolution(Float value) {
        return (TermResolution) (new TermResolutionImpl()).setValue(value);
    }

    public TermResolution createResolution(String value, Unit unit, int unary) {
        return (TermResolution) (new TermResolutionImpl()).setUnit(unit).setValue(
                convertFloat(value, unit.value(), unary));
    }

	public TermNumeric<Float> createDimension(String value, int unary) {
        //find the end of the numeric value
        int valend = value.length() - 1;
        while (valend >= 0 && !(value.charAt(valend) >= '0' && value.charAt(valend) <= '9'))
            valend--;
        //split the number and the unit
        if (valend >= 0 && valend < value.length() - 1) {
            final String upart = value.substring(valend + 1);
            final TermNumeric.Unit unit = TermNumeric.Unit.findByValue(upart.toLowerCase());
            if (unit != null) {
                final String vpart = value.substring(0, valend + 1);
                Float f;
                try {
                    f = Float.parseFloat(vpart) * unary;
                } catch (NumberFormatException e) {
                    return null; //not a float number
                }
                
                switch (unit.getType()) {
                    case angle:
                        return (TermNumeric<Float>) (new TermAngleImpl()).setUnit(unit).setValue(f);
                    case frequency:
                        return (TermNumeric<Float>) (new TermFrequencyImpl()).setUnit(unit).setValue(f);
                    case length:
                        return (TermNumeric<Float>) (new TermLengthImpl()).setUnit(unit).setValue(f);
                    case resolution:
                        return (TermNumeric<Float>) (new TermResolutionImpl()).setUnit(unit).setValue(f);
                    case time:
                        return (TermNumeric<Float>) (new TermTimeImpl()).setUnit(unit).setValue(f);
                    default:
                        return null;
                }
            }
            else
                return null; //unknown unit
        }
        else
            return null; //value or unit missing
	}

	@SuppressWarnings("unchecked")
	public <K, V> TermPair<K, V> createPair(K key, V value) {
		return (TermPair<K, V>) (new TermPairImpl<K, V>()).setKey(key)
				.setValue(value);
	}

	public TermPercent createPercent(Float value) {
		return (TermPercent) (new TermPercentImpl()).setValue(value);
	}

	public TermPercent createPercent(String value, int unary) {
		return (TermPercent) (new TermPercentImpl()).setValue(convertFloat(
				value, OutputUtil.PERCENT_SIGN, unary));
	}

	@Override
    public TermRect createRect(TermFunction function) {
        if (function.getFunctionName().equalsIgnoreCase("rect")) {
            List<Term<?>> args = function.getValues(true); //try the rect(0 0 0 0) syntax
            if (args == null || args.size() != 4)
                args = function.getSeparatedValues(CSSFactory.getTermFactory().createOperator(','), true); //try the rect(0, 0, 0, 0) syntax
            if (args.size() == 4) { //check the argument count and types
                for (int i = 0; i < 4; i++) {
                    Term<?> val = args.get(i);
                    if (val instanceof TermIdent) {
                        if (((TermIdent) val).getValue().equalsIgnoreCase("auto")) //replace 'auto' with null
                            args.set(i, null);
                    } else if (!(val instanceof TermLength)) {
                        return null;
                    }
                }
                return createRect((TermLength) args.get(0),
                        (TermLength) args.get(1),
                        (TermLength) args.get(2),
                        (TermLength) args.get(3));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public TermRect createRect(TermLength a, TermLength b,
            TermLength c, TermLength d) {
        return new TermRectImpl(a, b, c, d);
    }

    public TermString createString(String value) {
		return (new TermStringImpl()).setValue(value);
	}

	public <V> Term<V> createTerm(V value) {
		return (new TermImpl<V>()).setValue(value);
	}

	public TermTime createTime(Float value) {
		return (TermTime) (new TermTimeImpl()).setValue(value);
	}

	public TermTime createTime(String value, Unit unit, int unary) {
		return (TermTime) (new TermTimeImpl()).setUnit(unit).setValue(
				convertFloat(value, unit.value(), unary));
	}

    public TermUnicodeRange createUnicodeRange(String value) {
        return (new TermUnicodeRangeImpl()).setValue(value);
    }
    
	public TermURI createURI(String value) {
		return (new TermURIImpl()).setValue(value);
	}

    public TermURI createURI(String value, URL base) {
        return (new TermURIImpl()).setValue(value).setBase(base);
    }

	@Override
    public TermOperator createOperator(char value) {
	    return (TermOperator) (new TermOperatorImpl()).setValue(value);
    }

    /************************************************************************
	 * HELPERS *
	 ************************************************************************/

	protected Float convertFloat(String value, String unit, int unary)
			throws IllegalArgumentException {

		try {
            if (unit != null)
            {
    			// trim & lowercase
    			value = value.trim().toLowerCase();
    			// trim units from value
                if (value.endsWith(unit))
                    value = value.substring(0, value.length() - unit.length());
            }

			return Float.parseFloat(value) * unary;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(
					"Invalid number format " + value, e);
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("Invalid null format");
		}
	}

	protected Integer convertInteger(String value, String unit, int unary)
			throws IllegalArgumentException {

		try {
		    if (unit != null)
		    {
        		// trim & lowercase
        		value = value.trim().toLowerCase();
        		// trim units from value
        		if (value.endsWith(unit))
        		    value = value.substring(0, value.length() - unit.length());
		    }

			long lval = Long.valueOf(value) * unary;
			if (lval > Integer.MAX_VALUE)
			    return Integer.MAX_VALUE;
			else if (lval < Integer.MIN_VALUE)
			    return Integer.MIN_VALUE;
			else
			    return (int) lval;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(
					"Invalid number format " + value, e);
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("Invalid null format");
		}
	}
	
	protected TermFunction createFunctionByName(String name, List<Term<?>> args)
	{
	    TermFunction fn = null;
	    switch (name) {
	        case "matrix":
	            fn = new TermFunctionImpl.MatrixImpl();
	            break;
	        case "matrix3d":
	            fn = new TermFunctionImpl.Matrix3dImpl();
	            break;
	        case "perspective":
	            fn = new TermFunctionImpl.PerspectiveImpl();
	            break;
	        case "rotate":
	            fn = new TermFunctionImpl.RotateImpl();
	            break;
	        case "rotate3d":
	            fn = new TermFunctionImpl.Rotate3dImpl();
	            break;
	        case "rotatex":
	            fn = new TermFunctionImpl.RotateXImpl();
	            break;
	        case "rotatey":
	            fn = new TermFunctionImpl.RotateYImpl();
	            break;
	        case "rotatez":
	            fn = new TermFunctionImpl.RotateZImpl();
	            break;
	        case "scale":
	            fn = new TermFunctionImpl.ScaleImpl();
	            break;
	        case "scale3d":
	            fn = new TermFunctionImpl.Scale3dImpl();
	            break;
	        case "scalex":
	            fn = new TermFunctionImpl.ScaleXImpl();
	            break;
	        case "scaley":
	            fn = new TermFunctionImpl.ScaleYImpl();
	            break;
	        case "scalez":
	            fn = new TermFunctionImpl.ScaleZImpl();
	            break;
	        case "skew":
	            fn = new TermFunctionImpl.SkewImpl();
	            break;
	        case "skewx":
	            fn = new TermFunctionImpl.SkewXImpl();
	            break;
	        case "skewy":
	            fn = new TermFunctionImpl.SkewYImpl();
	            break;
	        case "translate":
	            fn = new TermFunctionImpl.TranslateImpl();
	            break;
	        case "translate3d":
	            fn = new TermFunctionImpl.Translate3dImpl();
	            break;
	        case "translatex":
	            fn = new TermFunctionImpl.TranslateXImpl();
	            break;
	        case "translatey":
	            fn = new TermFunctionImpl.TranslateYImpl();
	            break;
	        case "translatez":
	            fn = new TermFunctionImpl.TranslateZImpl();
	            break;
	        default:
	            fn = new TermFunctionImpl();
	            break;
	    }
	    if (fn != null) {
	        fn.setFunctionName(name);
	        fn.setValue(args);
	    }
	    if (!fn.isValid())
	        fn = null; //invalid arguments
	    return fn;
	}
	
}
