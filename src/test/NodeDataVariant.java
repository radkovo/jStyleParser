package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.tidy.Tidy;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.StyleSheetNotValidException;
import cz.vutbr.web.css.SupportedCSS;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.csskit.parser.CSSParser;
import cz.vutbr.web.domassign.Analyzer;
import cz.vutbr.web.domassign.DeclarationTransformer;
import cz.vutbr.web.domassign.QuadrupleMapNodeData;
import cz.vutbr.web.domassign.TidyTreeWalker.Traversal;

public class NodeDataVariant {

	private static Logger log = Logger.getLogger(NodeDataVariant.class);

	private static SupportedCSS css = CSSFactory.getSupportedCSS();

	private static DeclarationTransformer dt = DeclarationTransformer
			.getInstance();

	private static Document doc;
	private static Analyzer analyzer;

	@BeforeClass
	public static void init() throws FileNotFoundException,
			StyleSheetNotValidException {

		Tidy parser = new Tidy();
		parser.setCharEncoding(org.w3c.tidy.Configuration.UTF8);

		doc = parser.parseDOM(new FileInputStream("data/simple/data.html"),
				null);

		CSSParser cssparser = new CSSParser(new FileInputStream(
				"data/simple/data.css"));

		analyzer = new Analyzer(cssparser.parse());
	}

	@Test
	@Ignore
	public void testMemoryAllocationEmpty() throws Exception {

		long singleMap, qm, enumArray;

		singleMap = memoryUsage(NodeDataSingleMap.class);
		qm = memoryUsage(QuadrupleMapNodeData.class);
		enumArray = memoryUsage(NodeDataEnumArray.class);

		log.debug("===\nMemory test:");
		log.debug("NodeDataSingleMap size: " + singleMap
				+ " QuadrupleMap size: " + qm + " NodeDataEnumArray size: "
				+ enumArray);

	}

	@Test
	public void testTimeUsage() throws Exception {

		long singleMap, qm, enumArray;

		singleMap = timeUsage(NodeDataSingleMap.class);
		qm = timeUsage(QuadrupleMapNodeData.class);
		enumArray = timeUsage(NodeDataEnumArray.class);

		log.debug("===\nTime test:");
		log.debug("QuadrupleMap time: " + qm + " NodeDataSingleMap time: "
				+ singleMap + " NodeDataEnumArray time: " + enumArray);

	}

	@Test
	public void testInheritance() {

		CSSFactory.registerNodeDataInstance(QuadrupleMapNodeData.class);
		
		Map<Element, NodeData> nodes = analyzer.evaluateDOM(doc, "all", true);

		Traversal<List<String>> traversal = new Traversal<List<String>>(doc, (Object) nodes, NodeFilter.SHOW_ELEMENT) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processNode(List<String> result, Node current,
					Object source) {

				NodeData nd = ((Map<Element, NodeData>) source).get(current);
				result.add("<" + current.getNodeName() +" style=\""+ nd.toString() + "\">");
			}

		};

		List<String> result = new ArrayList<String>();

		traversal.levelTraversal(result);

		log.debug(result);

	}

	private long timeUsage(Class<? extends NodeData> clazz) throws Exception {

		final int SAMPLE = 100000;

		CSSFactory.registerNodeDataInstance(clazz);

		long timeBefore = System.currentTimeMillis();
		for (int i = 0; i < SAMPLE; i++)
			CSSFactory.createNodeData();
		long timeAfter = System.currentTimeMillis();

		return timeAfter - timeBefore;
	}

	private long memoryUsage(Class<? extends NodeData> clazz) throws Exception {

		final int SAMPLE = 100;

		CSSFactory.registerNodeDataInstance(clazz);

		Object[] holder = new Object[SAMPLE];

		@SuppressWarnings("unused")
		Object throwAway = clazz.newInstance();

		long memoryUsageBefore, memoryUsageAfter;
		int i = 0;

		memoryUsageBefore = memoryUsage();
		for (i = 0; i < SAMPLE; i++)
			holder[i] = CSSFactory.createNodeData();
		memoryUsageAfter = memoryUsage();

		return Math.round((memoryUsageAfter - memoryUsageBefore)
				/ ((float) SAMPLE));

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

	public static class NodeDataEnumArray implements NodeData {

		@SuppressWarnings("unused")
		private NodeData[] properties;
		@SuppressWarnings("unused")
		private Term<?>[] values;
		@SuppressWarnings("unused")
		private BitSet inherited;

		public NodeDataEnumArray() {
			this.properties = new NodeData[css.getTotalProperties()];
			this.values = new Term<?>[css.getTotalProperties()];
			this.inherited = new BitSet(css.getTotalProperties());
		}

		public <T extends CSSProperty> T getProperty(Class<T> clazz, String name) {
			return null;
		}

		public <T extends CSSProperty> T getProperty(Class<T> clazz,
				String name, boolean includeInherited) {
			return null;
		}

		public <T extends Term<?>> T getValue(Class<T> clazz, String name) {
			return null;
		}

		public <T extends Term<?>> T getValue(Class<T> clazz, String name,
				boolean includeInherited) {
			return null;
		}

		public void push(Declaration d) {
		}

		public NodeData inheritFrom(NodeData parent) {
			return this;
		}

		public NodeData concretize() {
			return this;
		}

	}

	public static class NodeDataSingleMap implements NodeData {

		private Map<String, CSSProperty> properties;
		private Map<String, Term<?>> values;
		private BitSet inherited;

		public NodeDataSingleMap() {
			this.properties = new HashMap<String, CSSProperty>(css
					.getTotalProperties(), 1.0f);
			this.values = new HashMap<String, Term<?>>(
					css.getTotalProperties(), 1.0f);
			this.inherited = new BitSet(css.getTotalProperties());
		}

		public <T extends CSSProperty> T getProperty(Class<T> clazz, String name) {
			return getProperty(clazz, name, true);
		}

		public <T extends CSSProperty> T getProperty(Class<T> clazz,
				String name, boolean includeInherited) {

			T property = clazz.cast(properties.get(name));

			if (includeInherited)
				return property;
			if (inherited.get(css.getOrdinal(name)))
				return null;

			return property;
		}

		public <T extends Term<?>> T getValue(Class<T> clazz, String name) {
			return getValue(clazz, name, true);
		}

		public <T extends Term<?>> T getValue(Class<T> clazz, String name,
				boolean includeInherited) {

			T value = clazz.cast(values.get(name));

			if (includeInherited)
				return value;

			if (inherited.get(css.getOrdinal(name)))
				return null;

			return value;
		}

		public void push(Declaration d) {

			Map<String, CSSProperty> properties = new HashMap<String, CSSProperty>(
					7);
			Map<String, Term<?>> terms = new HashMap<String, Term<?>>(7);

			boolean result = dt.parseDeclaration(d, properties, terms);

			// in case of false do not insert anything
			if (!result)
				return;

			this.properties.putAll(properties);
			this.values.putAll(terms);

		}

		public NodeData inheritFrom(NodeData parent) {
			// TODO Auto-generated method stub
			return this;
		}

		public NodeData concretize() {
			// TODO Auto-generated method stub
			return this;
		}
	}

}
