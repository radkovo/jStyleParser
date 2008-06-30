package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.NodeData.CSSProperty;
import cz.vutbr.web.csskit.TermNumberImpl;
import cz.vutbr.web.domassign.DeclarationTransformer;

/**
 * This test is to show performance difference between
 * Set and List and different methods of its clearing.
 * Set is slightly slower, but its functionality to avoid duplicates
 * is a big plus. Set is backed by Map, so Maps are also 
 * possible candidates. Maps are even used internally inside
 * NodeDataImpl.
 * 
 * For more then 50 000 declarations Maps becomes 10% slower than
 * Set with performs nearly as well as array.
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
public class CollectionSpeed {

	private static Logger log = Logger.getLogger(CollectionSpeed.class);
	
	private final DeclarationTransformer transformer = 
		DeclarationTransformer.getInstance();
	
	private static final int MAX = 6;
	private static final int ITERATIONS = 10000;	
	
	private Map<String,CSSProperty> mprop;
	private Map<String,Term> mterm;
	
	@Before
	public void initMasters() {
		this.mprop = new HashMap<String, CSSProperty>
			(DeclarationTransformer.TOTAL_SUPPORTED_DECLARATIONS, 1.0f);
		this.mterm = new HashMap<String, Term>
			(DeclarationTransformer.TOTAL_SUPPORTED_DECLARATIONS, 1.0f);
	}
	
	@Test
	public void testLinkedHashSetWithClear() {
		
		long time = System.currentTimeMillis();
		
		Set<CSSProperty> properties = new LinkedHashSet<CSSProperty>(MAX);
		Set<Term> terms = new LinkedHashSet<Term>(MAX);
		
		for(int i = 0; i < ITERATIONS; i++) {
			insert(properties, terms);
			properties.clear();
			terms.clear();
		}
		
		time = System.currentTimeMillis() - time;
		
		log.debug("LinkedHashSet with clear took: " + time + "ms");
		
	}
	
	@Test
	public void testLinkedHashSetWithReinit() {
		
		long time = System.currentTimeMillis();
		
		for(int i = 0; i < ITERATIONS; i++) {
			Set<CSSProperty> properties = new LinkedHashSet<CSSProperty>(MAX);
			Set<Term> terms = new LinkedHashSet<Term>(MAX);
			insert(properties, terms);
			properties.clear();
			terms.clear();
		}
		
		time = System.currentTimeMillis() - time;
		
		log.debug("LinkedHashSet with reinitalization took: " + time + "ms");
		
	}
	
	
	
	@Test
	public void testArrayListWithClear() {
		
		long time = System.currentTimeMillis();
		
		List<CSSProperty> properties = new ArrayList<CSSProperty>(MAX);
		List<Term> terms = new ArrayList<Term>(MAX);
		
		for(int i = 0; i < ITERATIONS; i++) {
			
			insert(properties, terms);
			properties.clear();
			terms.clear();
		}
		
		time = System.currentTimeMillis() - time;
		
		log.debug("ArrayList with clear took: " + time + "ms");
		
	}
	
	@Test
	public void testArrayListWithReinit() {
		
		long time = System.currentTimeMillis();
		
		for(int i = 0; i < ITERATIONS; i++) {
			
			List<CSSProperty> properties = new ArrayList<CSSProperty>(MAX);
			List<Term> terms = new ArrayList<Term>(MAX);			
			insert(properties, terms);
		}
		
		time = System.currentTimeMillis() - time;
		
		log.debug("ArrayList with reinit took: " + time + "ms");
	
	}
	
	@Test
	public void testHashMapWithClear() {
		
		long time = System.currentTimeMillis();
		
		Map<String,CSSProperty> properties = new HashMap<String,CSSProperty>(MAX);
		Map<String,Term> terms = new HashMap<String,Term>(MAX);
		
		for(int i = 0; i < ITERATIONS; i++) {
			insert(properties, terms);
			
			mprop.putAll(properties);
			mterm.putAll(terms);
			
			properties.clear();
			terms.clear();
		}
		
		time = System.currentTimeMillis() - time;
		
		log.debug("HashMap with clear took: " + time + "ms");
		
	}
	
	
	
	@Test
	public void testHashMapWithReinit() {
		
		long time = System.currentTimeMillis();
		
		for(int i = 0; i < ITERATIONS; i++) {
			
			Map<String,CSSProperty> properties = new HashMap<String,CSSProperty>(MAX);
			Map<String,Term> terms = new HashMap<String,Term>(MAX);			
			insert(properties, terms);
			
			mprop.putAll(properties);
			mterm.putAll(terms);
			
		}
		
		time = System.currentTimeMillis() - time;
		
		log.debug("HashMap with reinit took: " + time + "ms");
		
	}
	
	@Test
	public void testLinkedHashMapWithReinit() {
		
		long time = System.currentTimeMillis();
		
		for(int i = 0; i < ITERATIONS; i++) {
			
			Map<String,CSSProperty> properties = new LinkedHashMap<String,CSSProperty>(MAX);
			Map<String,Term> terms = new LinkedHashMap<String,Term>(MAX);			
			insert(properties, terms);
			
			mprop.putAll(properties);
			mterm.putAll(terms);
			
		}
		
		time = System.currentTimeMillis() - time;
		
		log.debug("LinkedHashMap with reinit took: " + time + "ms");
		
	}
	
	
	private void insert(Collection<CSSProperty> properties, 
			Collection<Term> terms) {
		
		Random generator = new Random();
		
		for(int i = 0; i < generator.nextInt(MAX); i++) 
			properties.add(transformer.randomValue());
		
		for(int i = 0; i < generator.nextInt(MAX); i++)
			terms.add(new TermNumberImpl(generator.nextFloat()));
	}
	
	private void insert(Map<String, CSSProperty> properties,
			Map<String, Term> terms) {
		
		Random generator = new Random();
		
		for(int i = 0; i < generator.nextInt(MAX); i++) {
			final CSSProperty p = transformer.randomValue(); 
			properties.put(p.toString(), p);
			terms.put(p.toString(), new TermNumberImpl(generator.nextFloat()));
		}		
	}
	
}
