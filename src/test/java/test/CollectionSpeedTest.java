package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.SupportedCSS;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFactory;

/**
 * This test is to show performance difference between Set and List and
 * different methods of its clearing. Set is slightly slower, but its
 * functionality to avoid duplicates is a big plus. Set is backed by Map, so
 * Maps are also possible candidates. Maps are even used internally inside
 * QuadrupleMapNodeData.
 * 
 * For more then 50 000 declarations Maps becomes 10% slower than Set with
 * performs nearly as well as array.
 * 
 * For about 100 000 declarations is clearing as rapid as new initialization,
 * which is not the case of common CSS file. Declaring as final for reinit
 * decreases performance
 * 
 * 
 * @author kapy
 * 
 */
@Ignore
public class CollectionSpeedTest {

	private static Logger log = LoggerFactory.getLogger(CollectionSpeedTest.class);

	private static final SupportedCSS css = CSSFactory.getSupportedCSS();
	private static final TermFactory tf = CSSFactory.getTermFactory();

	private static final int MAX = 6;
	private static final int ITERATIONS = 100000;

	private Map<String, CSSProperty> mprop;
	private Map<String, Term<?>> mterm;

	@BeforeClass
	public static void init() {
		log.info("\n\n\n == AnalyzerTest test at {} == \n\n\n", new Date());
	}

	@Before
	public void initMasters() {
		this.mprop = new HashMap<String, CSSProperty>(css.getTotalProperties(),
				1.0f);
		this.mterm = new HashMap<String, Term<?>>(css.getTotalProperties(),
				1.0f);
	}

	@Test
	public void testLinkedHashSetWithClear() {

		long time = System.currentTimeMillis();

		Set<CSSProperty> properties = new LinkedHashSet<CSSProperty>(MAX);
		Set<Term<?>> terms = new LinkedHashSet<Term<?>>(MAX);

		for (int i = 0; i < ITERATIONS; i++) {
			insert(properties, terms);
			properties.clear();
			terms.clear();
		}

		time = System.currentTimeMillis() - time;

		log.debug("LinkedHashSet with clear took {}ms.", time);

	}

	@Test
	public void testLinkedHashSetWithReinit() {

		long time = System.currentTimeMillis();

		for (int i = 0; i < ITERATIONS; i++) {
			Set<CSSProperty> properties = new LinkedHashSet<CSSProperty>(MAX);
			Set<Term<?>> terms = new LinkedHashSet<Term<?>>(MAX);
			insert(properties, terms);
			properties.clear();
			terms.clear();
		}

		time = System.currentTimeMillis() - time;

		log.debug("LinkedHashSet with reinitalization took {}ms.", time);

	}

	@Test
	public void testArrayListWithClear() {

		long time = System.currentTimeMillis();

		List<CSSProperty> properties = new ArrayList<CSSProperty>(MAX);
		List<Term<?>> terms = new ArrayList<Term<?>>(MAX);

		for (int i = 0; i < ITERATIONS; i++) {

			insert(properties, terms);
			properties.clear();
			terms.clear();
		}

		time = System.currentTimeMillis() - time;

		log.debug("ArrayList with clear took {}ms.", time);

	}

	@Test
	public void testArrayListWithReinit() {

		long time = System.currentTimeMillis();

		for (int i = 0; i < ITERATIONS; i++) {

			List<CSSProperty> properties = new ArrayList<CSSProperty>(MAX);
			List<Term<?>> terms = new ArrayList<Term<?>>(MAX);
			insert(properties, terms);
		}

		time = System.currentTimeMillis() - time;

		log.debug("ArrayList with reinit took {}ms.", time);

	}

	@Test
	public void testHashMapWithClear() {

		long time = System.currentTimeMillis();

		Map<String, CSSProperty> properties = new HashMap<String, CSSProperty>(
				MAX);
		Map<String, Term<?>> terms = new HashMap<String, Term<?>>(MAX);

		for (int i = 0; i < ITERATIONS; i++) {
			insert(properties, terms);

			mprop.putAll(properties);
			mterm.putAll(terms);

			properties.clear();
			terms.clear();
		}

		time = System.currentTimeMillis() - time;

		log.debug("HashMap with clear took {}ms.", time);

	}

	@Test
	public void testHashMapWithReinit() {

		long time = System.currentTimeMillis();

		for (int i = 0; i < ITERATIONS; i++) {

			Map<String, CSSProperty> properties = new HashMap<String, CSSProperty>(
					MAX);
			Map<String, Term<?>> terms = new HashMap<String, Term<?>>(MAX);
			insert(properties, terms);

			mprop.putAll(properties);
			mterm.putAll(terms);

		}

		time = System.currentTimeMillis() - time;

		log.debug("HashMap with reinit took {}ms.", time);

	}

	@Test
	public void testLinkedHashMapWithReinit() {

		long time = System.currentTimeMillis();

		for (int i = 0; i < ITERATIONS; i++) {

			Map<String, CSSProperty> properties = new LinkedHashMap<String, CSSProperty>(
					MAX);
			Map<String, Term<?>> terms = new LinkedHashMap<String, Term<?>>(MAX);
			insert(properties, terms);

			mprop.putAll(properties);
			mterm.putAll(terms);

		}

		time = System.currentTimeMillis() - time;

		log.debug("LinkedHashMap with reinit took {}ms.", time);

	}

	private void insert(Collection<CSSProperty> properties,
			Collection<Term<?>> terms) {

		final Random generator = new Random();

		for (int i = 0; i < generator.nextInt(MAX); i++)
			properties.add(css.getDefaultProperty(css.getRandomPropertyName()));

		for (int i = 0; i < generator.nextInt(MAX); i++)
			terms.add(tf.createNumber(generator.nextFloat()));
	}

	private void insert(Map<String, CSSProperty> properties,
			Map<String, Term<?>> terms) {

		final Random generator = new Random();

		for (int i = 0; i < generator.nextInt(MAX); i++) {

			String name = css.getRandomPropertyName();
			CSSProperty p = css.getDefaultProperty(name);
			properties.put(name, p);
			terms.put(name, tf.createNumber(generator.nextFloat()));
		}
	}

}
