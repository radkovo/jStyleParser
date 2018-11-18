package test;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.CSSProperty.Grid;
import cz.vutbr.web.css.CSSProperty.GridAutoFlow;
import cz.vutbr.web.css.CSSProperty.GridTemplateRowsColumns;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermNumeric.Unit;
import cz.vutbr.web.domassign.StyleMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

/**
 *
 * @author Petr Mikulík <dexterdd77@gmail.com>
 */
public class GridLayoutTest {

	private static final TermFactory tf = CSSFactory.getTermFactory();
	private final Map<String, ResultInfo> _tests = new LinkedHashMap<>();

	@Before
	public void prepare() {
		NameGenerator ng = new NameGenerator("grid");
		TermList list = tf.createList();

		_tests.put(ng.next(), new ResultInfo("grid", Grid.NONE));

		list.add(tf.createLength(1f, Unit.fr));
		_tests.put(ng.next(), new ResultInfo("grid-template-columns", GridTemplateRowsColumns.list_values, list));
		_tests.put(ng.curr(), new ResultInfo("grid-auto-flow", GridAutoFlow.ROW));
		
		_tests.put(ng.next(), new ResultInfo("grid-template-columns", GridTemplateRowsColumns.list_values, list));
		list.clear();
		list.add(tf.createIdent("row"));
		list.add(tf.createIdent("dense"));
		_tests.put(ng.curr(), new ResultInfo("grid-auto-flow", GridAutoFlow.component_values));
		
		list.clear();
		list.add(tf.createLength(1f, Unit.px));
		list.add(tf.createLength(1f, Unit.px));
		_tests.put(ng.next(), new ResultInfo("grid-template-columns", GridTemplateRowsColumns.list_values, list));
		_tests.put(ng.curr(), new ResultInfo("grid-auto-flow", GridAutoFlow.COLUMN));
		
		list.clear();
		list.add(tf.createLength(1f, Unit.px));
		list.add(tf.createLength(1f, Unit.px));
		_tests.put(ng.next(), new ResultInfo("grid-template-columns", GridTemplateRowsColumns.list_values, list));
		list.clear();
		list.add(tf.createIdent("column"));
		list.add(tf.createIdent("dense"));
		_tests.put(ng.curr(), new ResultInfo("grid-auto-flow", GridAutoFlow.component_values));
	}

	@Test
	public void test() {
		try {
			DOMSource ds = new DOMSource(getClass().getResourceAsStream("/simple/grid-layout.html"));
			Document doc = ds.parse();
			StyleMap sm = CSSFactory.assignDOM(doc, null, getClass().getResource("/simple/grid-layout.html"), "screen", true);

			ElementMap elements = new ElementMap(doc);
			NodeData data;
			CSSProperty prop;
			Term<?> term;

			for (Map.Entry<String, ResultInfo> entry : _tests.entrySet()) {
				System.out.println("Testing " + entry.getKey());
				String name = entry.getKey();
				ResultInfo ri = entry.getValue();
				data = sm.get(elements.getElementById(name));
				prop = data.getProperty(ri._propertyName);
				if (!ri._expextedProperty.equals(prop)) {
					checkProperty("grid", data);

					checkProperty("grid-row-start", data);
					checkProperty("grid-row-end", data);
					checkProperty("grid-columns-start", data);
					checkProperty("grid-column-end", data);

					checkProperty("grid-template-areas", data);
					checkProperty("grid-template-rows", data);
					checkProperty("grid-template-columns", data);
					checkProperty("grid-auto-flow", data);
					checkProperty("grid-auto-rows", data);
					checkProperty("grid-auto-columns", data);
				}
				Assert.assertEquals(ri._expextedProperty, prop);
				if (ri._expectedValue != null) {
					term = data.getValue(name, true);
					Assert.assertEquals(ri._expectedValue, term);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkProperty(String property, NodeData data) {
		CSSProperty prop;
		Term<?> term;
		prop = data.getProperty(property);
		System.out.println("Property \"" + property + "\" exist " + (prop != null));
		if (prop != null) {
			System.out.println("prop class = " + prop.getClass().getSimpleName());
			System.out.println("prop = " + prop);
			term = data.getValue(property, true);
			System.out.println("Term for \"" + property + "\" exist " + (term != null));
			if (term != null) {
				System.out.println("term to string = " + term);
			}
		}
		System.out.println();
	}

	private static class ResultInfo {

		private final String _propertyName;
		private final CSSProperty _expextedProperty;
		private final Object _expectedValue;

		private ResultInfo(String propertyName, CSSProperty expextedProperty) {
			this(propertyName, expextedProperty, null);
		}

		private ResultInfo(String propertyName, CSSProperty expextedProperty, Object expectedValue) {
			_propertyName = propertyName;
			_expextedProperty = expextedProperty;
			_expectedValue = expectedValue;
		}

	}

	private static class NameGenerator {

		private String _className;
		private int _iteration = -1;

		private NameGenerator(String className) {
			_className = className;
		}

		private void setName(String className) {
			_className = className;
			_iteration = -1;
		}

		private String curr() {
			return _className + Integer.toString(_iteration);
		}

		private String next() {
			_iteration++;
			return curr();
		}
	}

}
