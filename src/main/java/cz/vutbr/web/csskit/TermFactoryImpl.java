package cz.vutbr.web.csskit;

import java.net.URL;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermAngle;
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
import cz.vutbr.web.css.TermResolution;
import cz.vutbr.web.css.TermString;
import cz.vutbr.web.css.TermTime;
import cz.vutbr.web.css.TermURI;
import cz.vutbr.web.css.TermNumeric.Unit;

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
    
	public TermFunction createFunction() {
		return new TermFunctionImpl();
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

	    try {
    		for (TermNumeric.Unit unit : TermNumeric.Unit.values()) {
    			// try to find valid unit identifier
    			if (value.matches("^[0-9]*\\.?[0-9]+" + unit.value() + "$")) {
    				Float f = convertFloat(value, unit.value(), unary);
    				if (unit.isAngle())
    					return (TermNumeric<Float>) (new TermAngleImpl()).setUnit(
    							unit).setValue(f);
    				else if (unit.isFrequency())
    					return (TermNumeric<Float>) (new TermFrequencyImpl())
    							.setUnit(unit).setValue(f);
    				else if (unit.isLength())
    					return (TermNumeric<Float>) (new TermLengthImpl()).setUnit(
    							unit).setValue(f);
                    else if (unit.isResolution())
                        return (TermNumeric<Float>) (new TermResolutionImpl()).setUnit(
                                unit).setValue(f);
    				else if (unit.isTime())
    					return (TermNumeric<Float>) (new TermTimeImpl()).setUnit(
    							unit).setValue(f);
    			}
    
    		}
	    } catch (IllegalArgumentException e) {
	        return null;
	    }

		return null;

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

	public TermString createString(String value) {
		return (new TermStringImpl()).setValue(value);
	}

	public <V> Term<V> createTerm(V value) {
		return (new TermImpl<V>()).setValue(value);
	}

	public TermTime createTime(Float value) {
		return (new TermTimeImpl()).setValue(value);
	}

	public TermTime createTime(String value, Unit unit, int unary) {
		return (TermTime) (new TermTimeImpl()).setUnit(unit).setValue(
				convertFloat(value, unit.value(), unary));
	}

	public TermURI createURI(String value) {
		return (new TermURIImpl()).setValue(value);
	}

    public TermURI createURI(String value, URL base) {
        return (new TermURIImpl()).setValue(value).setBase(base);
    }

	/************************************************************************
	 * HELPERS *
	 ************************************************************************/

	protected Float convertFloat(String value, String unit, int unary)
			throws IllegalArgumentException {

		try {
			// trim & lowercase
			value = value.trim().toLowerCase();
			// trim units from value
			if (unit != null)
				value = value.replaceAll(unit + "$", "");

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
			// trim & lowercase
			value = value.trim().toLowerCase();
			// trim units from value
			if (unit != null)
				value = value.replaceAll(unit + "$", "");

			return Integer.valueOf(value) * unary;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(
					"Invalid number format " + value, e);
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("Invalid null format");
		}
	}
}
