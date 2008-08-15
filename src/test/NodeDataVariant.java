package test;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.SupportedCSS;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.domassign.DeclarationTransformer;


public class NodeDataVariant {

	private static Logger log = Logger.getLogger(NodeDataVariant.class); 
	
	private static SupportedCSS css = CSSFactory.getSupportedCSS();
	
	private static DeclarationTransformer dt = DeclarationTransformer.getInstance();
	
	@Test
	public void testMemoryAllocationEmpty() throws Exception {
		
		long singleMap, doubleMap, enumArray;
		
		singleMap = memoryUsage(NodeDataSingleMap.class);
		doubleMap = memoryUsage(NodeDataDoubleMap.class);
		enumArray = memoryUsage(NodeDataEnumArray.class);
		
		
		log.debug("===\nMemory test:");
		log.debug("NodeDataDoubleMap size: " + doubleMap +
				" NodeDataSingleMap size: " + singleMap +
				" NodeDataEnumArray size: " + enumArray);
	
	}
	
	
	private long memoryUsage(Class<? extends NodeData> clazz) throws Exception {
		
		final int SAMPLE = 100;
				
		Object[] holder = new Object[SAMPLE];
		
		@SuppressWarnings("unused")
		Object throwAway = clazz.newInstance();
		
		long memoryUsageBefore, memoryUsageAfter;
		int i = 0;
		
		memoryUsageBefore = memoryUsage();
		for(i=0;i<SAMPLE;i++) 
			holder[i] = clazz.newInstance();
		memoryUsageAfter = memoryUsage();
		
		return Math.round((memoryUsageAfter - memoryUsageBefore) / ((float) SAMPLE));

	}
		
		
	private long memoryUsage() throws Exception {
		
		final int SLEEP_TIME = 100;
		long memUsage;
		
		System.gc();
		Thread.sleep(SLEEP_TIME);
		System.runFinalization();
		Thread.sleep(SLEEP_TIME);
		System.gc();
		Thread.sleep(SLEEP_TIME);
		System.runFinalization();
		Thread.sleep(SLEEP_TIME);
		memUsage = Runtime.getRuntime().totalMemory();
		
		System.gc();
		Thread.sleep(SLEEP_TIME);
		System.runFinalization();
		Thread.sleep(SLEEP_TIME);
		System.gc();
		Thread.sleep(SLEEP_TIME);
		System.runFinalization();
		Thread.sleep(SLEEP_TIME);
		
		return memUsage - Runtime.getRuntime().freeMemory();
	}
	
	private static class NodeDataEnumArray implements NodeData {
		
		private NodeData[] properties;
		private Term<?>[] values;
		private BitSet inherited;
		
		public NodeDataEnumArray() {
			this.properties = new NodeData[css.getTotalProperties()];
			this.values = new Term<?>[css.getTotalProperties()];
			this.inherited = new BitSet(css.getTotalProperties());
		}
		
		public <T extends CSSProperty> T getProperty(Class<T> clazz, String name) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public <T extends CSSProperty> T getProperty(Class<T> clazz,
				String name, boolean includeInherited) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public <T extends Term<?>> T getValue(Class<T> clazz, String name) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public <T extends Term<?>> T getValue(Class<T> clazz, String name,
				boolean includeInherited) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public void push(Declaration d) {
			// TODO Auto-generated method stub
			
		}
		
		public NodeData inheritValues(NodeData parent) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	
	private static class NodeDataSingleMap implements NodeData {
		
		private Map<String,CSSProperty> properties;
		private Map<String,Term<?>> values;
		private BitSet inherited;
		
		public NodeDataSingleMap() {
			this.properties = new HashMap<String, CSSProperty>(css.getTotalProperties(), 1.0f);
			this.values = new HashMap<String, Term<?>>(css.getTotalProperties(), 1.0f);
			this.inherited = new BitSet(css.getTotalProperties());
		}
		
		public <T extends CSSProperty> T getProperty(Class<T> clazz, String name) {
			return getProperty(clazz, name, true);
		}
		
		public <T extends CSSProperty> T getProperty(Class<T> clazz,
				String name, boolean includeInherited) {
			
			T property = clazz.cast(properties.get(name));
			
			if(includeInherited) return property;
			if(inherited.get(css.getOrdinal(name))) return null;
			
			return property;
		}
		
		public <T extends Term<?>> T getValue(Class<T> clazz, String name) {
			return getValue(clazz, name, true);
		}
		
		public <T extends Term<?>> T getValue(Class<T> clazz, String name,
				boolean includeInherited) {
			
			T value = clazz.cast(values.get(name));
			
			if(includeInherited) return value;
			
			if(inherited.get(css.getOrdinal(name)))
				return null;
			
			return value;
		}		
		
		public void push(Declaration d) {
			
			Map<String,CSSProperty> properties = 
				new HashMap<String,CSSProperty>(7);
			Map<String,Term<?>> terms = 
				new HashMap<String, Term<?>>(7);
			
			boolean result = dt.parseDeclaration(d, properties, terms);
			
			// in case of false do not insert anything
			if(!result) return;
			
			this.properties.putAll(properties);
			this.values.putAll(terms);
			
		}
		
		public NodeData inheritValues(NodeData parent) {
			// TODO Auto-generated method stub
			return null;
		}
				
	}
	
	
	private static class NodeDataDoubleMap implements NodeData {
		
		private Map<String,CSSProperty> propertiesOwn;
		private Map<String,CSSProperty> propertiesInh;
		private Map<String,Term<?>> valuesOwn;
		private Map<String,Term<?>> valuesInh;
		
		public NodeDataDoubleMap() {
			this.propertiesOwn = new HashMap<String, CSSProperty>(css.getTotalProperties(), 1.0f);
			this.propertiesInh = new HashMap<String, CSSProperty>(css.getTotalProperties(), 1.0f);
			this.valuesOwn = new HashMap<String, Term<?>>(css.getTotalProperties(), 1.0f);
			this.valuesInh = new HashMap<String, Term<?>>(css.getTotalProperties(), 1.0f);
		}
		
		public <T extends CSSProperty> T getProperty(Class<T> clazz, String name, boolean includeInherited) {

			T inherited = null;
			
			if(includeInherited) 
				inherited = clazz.cast(propertiesInh.get(name));

			T own = clazz.cast(propertiesOwn.get(name));
			if(own==null) return inherited;
			return own;
		}
		
		public <T extends CSSProperty> T getProperty(Class<T> clazz, String name) {
			return getProperty(clazz, name, true);
		}
		
		public <T extends Term<?>> T getValue(Class<T> clazz, String name, boolean includeInherited) {
			
			T inherited = null;
			
			if(includeInherited) 
				inherited = clazz.cast(valuesInh.get(name));

			T own = clazz.cast(valuesOwn.get(name));
			if(own==null) return inherited;
			return own;
		}
		
		public <T extends Term<?>> T getValue(Class<T> clazz, String name) {
			return getValue(clazz, name, true);
		}
		
		public void push(Declaration d) {
			
			Map<String,CSSProperty> properties = 
				new HashMap<String,CSSProperty>(7);
			Map<String,Term<?>> terms = 
				new HashMap<String, Term<?>>(7);
			
			boolean result = dt.parseDeclaration(d, properties, terms);
			
			// in case of false do not insert anything
			if(!result) return;
			
			this.propertiesOwn.putAll(properties);
			this.valuesOwn.putAll(terms);
			
		}
		
		public NodeData inheritValues(NodeData parent) {
			if(!(parent instanceof NodeDataDoubleMap))
				throw new IllegalArgumentException(
						"Cant't inherit from NodeData different from "
								+ this.getClass().getName());
			
			NodeDataDoubleMap nd = (NodeDataDoubleMap) parent;
			
			// inherit values
			for(String key:nd.propertiesInh.keySet()) {
				CSSProperty value = nd.propertiesInh.get(key);
				if(value.inherited()) {
					this.propertiesInh.put(key, value);
					Term<?> term = nd.valuesInh.get(key);
					if(term!=null)
						this.valuesInh.put(key, term);
				}
			}
			
			for(String key:nd.propertiesOwn.keySet()) {
				CSSProperty value = nd.propertiesOwn.get(key);
				if(value.inherited()) {
					this.propertiesInh.put(key, value);
					Term<?> term = nd.valuesOwn.get(key);
					if(term!=null)
						this.valuesInh.put(key, term);
				}
			}
			
			// substitute "INHERIT" values
			for(String key:this.propertiesOwn.keySet()) {
				if(this.propertiesOwn.get(key).equalsInherit()) {
					CSSProperty value = null; // FIXME
				}
					
					propertiesOwn.put(key, null);
			}
			
			return this;
		}
		
		
	}
}
