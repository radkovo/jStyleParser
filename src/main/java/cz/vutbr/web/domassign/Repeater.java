package cz.vutbr.web.domassign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.SupportedCSS;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermIdent;

/**
 * Repeats one operation on different CSS declaration duplication of code. To
 * use, implement operation() method. Use for CSS declaration such as
 * <code>border-width: 2px</code>
 * 
 * @author kapy
 */
public abstract class Repeater {

	/**
	 * Number of times operation is repeated
	 */
	protected int times;

	/**
	 * Terms over which operation is repeated
	 */
	protected List<Term<?>> terms;

	/**
	 * Property names for each iteration of repeater object
	 */
	protected List<String> names;

	/**
	 * Which property is used to repeat
	 */
	protected Class<? extends CSSProperty> type;
	
	/**
	 * Constructor
	 * 
	 * @param times
	 *            Number of iterations
	 */
	public Repeater(int times) {
		this.times = times;
		this.terms = new ArrayList<Term<?>>(times);
		this.names = new ArrayList<String>(times);
	}

	/**
	 * Repeating operation
	 * 
	 * @param iteration
	 *            Currently passing iteration
	 * @param properties
	 *            Properties map where to store properties types
	 * @param values
	 *            Value map where to store properties values
	 * @return <code>true</code> in case of success, <code>false</code>
	 *         elsewhere
	 */
	protected abstract boolean operation(int iteration,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values);

	/**
	 * Repeats operations on terms
	 * 
	 * @param properties
	 *            Properties map where to store properties types
	 * @param values
	 *            Values map where to store properties values
	 * @return <code>true</code> in case of success, <code>false</code>
	 *         elsewhere
	 */
	public boolean repeat(Map<String, CSSProperty> properties,
			Map<String, Term<?>> values) {
		for (int i = 0; i < times; i++) {
			if (!operation(i, properties, values))
				return false;
		}
		return true;
	}

	/**
	 * Construct terms array to be used by repeated object from available terms
	 * (in size 1 to 4) according to CSS rules.
	 * 
	 * Example:
	 * <p>
	 * <code>margin: 2px 5px;</code> creates virtual terms array with terms
	 * <code>2px 5px 2px 5px</code> so top and bottom; left and right contains
	 * the same margin
	 * </p>
	 * 
	 * @param d
	 *            Declaration with terms
	 * @param properties
	 *            Properties map where to store properties types
	 * @param values
	 *            Value map where to store properties values
	 * @return <code>true</code> in case of success, <code>false</code>
	 *         elsewhere
	 * @throws IllegalArgumentException
	 *             In case when number of terms passed does not correspond to
	 *             iteration times
	 */
	public boolean repeatOverFourTermDeclaration(Declaration d,
			Map<String, CSSProperty> properties, Map<String, Term<?>> values)
			throws IllegalArgumentException {

		switch (d.size()) {
		case 1:
			// one term for all value
			Term<?> term = d.get(0);
			
			// check inherit
			if(term instanceof TermIdent && CSSProperty.INHERIT_KEYWORD.equalsIgnoreCase(((TermIdent) term).getValue())) {
				CSSProperty property = CSSProperty.Translator.createInherit(type);
				for(int i = 0; i < times; i++) {
					properties.put(names.get(i), property);
				}
				return true;
			}
			
			assignTerms(term, term, term, term);
			return repeat(properties, values);
		case 2:
			// one term for bottom-top and left-right
			Term<?> term1 = d.get(0);
			Term<?> term2 = d.get(1);
			assignTerms(term1, term2, term1, term2);
			return repeat(properties, values);
		case 3:
			// terms for bottom, top, left-right
			Term<?> term31 = d.get(0);
			Term<?> term32 = d.get(1);
			Term<?> term33 = d.get(2);
			assignTerms(term31, term32, term33, term32);
			return repeat(properties, values);
		case 4:
			// four individual terms (or more - omitted)
		    //if (d.size() > 4)
		    //    LoggerFactory.getLogger(Repeater.class).warn("Omitting additional terms in four-term declaration");
			Term<?> term41 = d.get(0);
			Term<?> term42 = d.get(1);
			Term<?> term43 = d.get(2);
			Term<?> term44 = d.get(3);
			assignTerms(term41, term42, term43, term44);
			return repeat(properties, values);
		default:
			throw new IllegalArgumentException(
					"Invalid length of terms in Repeater.");
		}
	}

	/**
	 * Assigns property names
	 * 
	 * @param propertyNames
	 *            Names of properties for each iteration
	 * @throws IllegalArgumentException
	 *             In case when number of properties names does not correspond
	 *             with number of iterations
	 */
	public void assignPropertyNames(String... propertyNames)
			throws IllegalArgumentException {
		if (propertyNames.length != times)
			throw new IllegalArgumentException(
					"Invalid length of propertyNames in Repeater.");
		this.names = Arrays.asList(propertyNames);
	}

	/**
	 * Assigns terms to repeater
	 * 
	 * @param terms
	 *            Terms to be assigned
	 * @throws IllegalArgumentException
	 *             In case when number of terms does not correspond with number
	 *             of iterations
	 */
	public void assignTerms(Term<?>... terms) throws IllegalArgumentException {
		if (terms.length != times)
			throw new IllegalArgumentException(
					"Invalid length of terms in Repeater.");
		this.terms = Arrays.asList(terms);
	}
	
    /**
     * Assigns the default values to all the properties.
     * @param properties
     * @param values
     */
    public void assignDefaults(Map<String, CSSProperty> properties, Map<String, Term<?>> values) {
        SupportedCSS css = CSSFactory.getSupportedCSS();
        for (String name : names) {
            CSSProperty dp = css.getDefaultProperty(name);
            if (dp != null)
                properties.put(name, dp);
            Term<?> dv = css.getDefaultValue(name);
            if (dv != null)
                values.put(name, dv);
        }
    }

}
