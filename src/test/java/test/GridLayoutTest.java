package test;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.CSSProperty.Grid;
import cz.vutbr.web.css.CSSProperty.GridAutoFlow;
import cz.vutbr.web.css.CSSProperty.GridGap;
import cz.vutbr.web.css.CSSProperty.GridStartEnd;
import cz.vutbr.web.css.CSSProperty.GridTemplateAreas;
import cz.vutbr.web.css.CSSProperty.GridTemplateRowsColumns;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.Term.Operator;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermNumeric.Unit;
import cz.vutbr.web.domassign.StyleMap;
import java.util.ArrayList;
import java.util.List;
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
	private final List<TestData> _tests = new ArrayList<>();

	@Before
	public void prepare() {
		NameGenerator ng = new NameGenerator("grid");
		TermList list = tf.createList();

		// grid
		_tests.add(new TestData(ng.next(), "grid", Grid.NONE));

		list.add(tf.createLength(1f, Unit.fr).setOperator(Operator.SLASH));
		_tests.add(new TestData(ng.next(), "grid-template-columns", GridTemplateRowsColumns.list_values, list));
		_tests.add(new TestData(ng.curr(), "grid-auto-flow", GridAutoFlow.ROW));

		_tests.add(new TestData(ng.next(), "grid-template-columns", GridTemplateRowsColumns.list_values, list));
		list = tf.createList();
		list.add(tf.createIdent("row"));
		list.add(tf.createIdent("dense").setOperator(Operator.SLASH));
		_tests.add(new TestData(ng.curr(), "grid-auto-flow", GridAutoFlow.component_values));

		list = tf.createList();
		list.add(tf.createLength(1f, Unit.px));
		list.add(tf.createLength(1f, Unit.px).setOperator(Operator.SPACE));
		_tests.add(new TestData(ng.next(), "grid-template-rows", GridTemplateRowsColumns.list_values, list));
		_tests.add(new TestData(ng.curr(), "grid-auto-flow", GridAutoFlow.COLUMN));

		list = tf.createList();
		list.add(tf.createLength(1f, Unit.px));
		list.add(tf.createLength(1f, Unit.px).setOperator(Operator.SPACE));
		_tests.add(new TestData(ng.next(), "grid-template-rows", GridTemplateRowsColumns.list_values, list));
		list = tf.createList();
		list.add(tf.createIdent("column").setOperator(Operator.SPACE));
		list.add(tf.createIdent("dense").setOperator(Operator.SPACE));
		_tests.add(new TestData(ng.curr(), "grid-auto-flow", GridAutoFlow.component_values));

		list = tf.createList();
		list.add(tf.createString("a a a"));
		list.add(tf.createString("b c c").setOperator(Operator.SPACE));
		list.add(tf.createString("b c c").setOperator(Operator.SPACE));
		_tests.add(new TestData(ng.next(), "grid-template-areas", GridTemplateAreas.list_values, list));
		list = tf.createList();
		list.add(tf.createLength(1f, Unit.px).setOperator(Operator.SPACE));
		list.add(tf.createLength(1f, Unit.px).setOperator(Operator.SPACE));
		list.add(tf.createLength(1f, Unit.px).setOperator(Operator.SPACE));
		_tests.add(new TestData(ng.curr(), "grid-template-rows", GridTemplateRowsColumns.list_values, list));
		list = tf.createList();
		list.add(tf.createLength(2f, Unit.px).setOperator(Operator.SLASH));
		list.add(tf.createLength(2f, Unit.px).setOperator(Operator.SPACE));
		list.add(tf.createLength(2f, Unit.px).setOperator(Operator.SPACE));
		_tests.add(new TestData(ng.curr(), "grid-template-columns", GridTemplateRowsColumns.list_values, list));
		
		list = tf.createList();
		list.add(tf.createString("a a a").setOperator(Operator.SPACE));
		list.add(tf.createString("b c c").setOperator(Operator.SPACE));
		list.add(tf.createString("b c c").setOperator(Operator.SPACE));
		_tests.add(new TestData(ng.next(), "grid-template-areas", GridTemplateAreas.list_values, list));
		list = tf.createList();
		list.add(tf.createBracketedIdent("name"));
		list.add(tf.createLength(1f, Unit.px).setOperator(Operator.SPACE));
		list.add(tf.createLength(1f, Unit.px).setOperator(Operator.SPACE));
		list.add(tf.createBracketedIdent("name").setOperator(Operator.SPACE));
		list.add(tf.createLength(1f, Unit.px).setOperator(Operator.SPACE));
		_tests.add(new TestData(ng.curr(), "grid-template-rows", GridTemplateRowsColumns.list_values, list));
		list = tf.createList();
		list.add(tf.createLength(2f, Unit.px).setOperator(Operator.SLASH));
		list.add(tf.createBracketedIdent("name").setOperator(Operator.SPACE));
		list.add(tf.createLength(2f, Unit.px).setOperator(Operator.SPACE));
		list.add(tf.createLength(2f, Unit.px).setOperator(Operator.SPACE));
		list.add(tf.createBracketedIdent("name").setOperator(Operator.SPACE));
		_tests.add(new TestData(ng.curr(), "grid-template-columns", GridTemplateRowsColumns.list_values, list));
		
		_tests.add(new TestData(ng.next(), "grid-template-areas", null));
		_tests.add(new TestData(ng.curr(), "grid-template-rows", null));
		_tests.add(new TestData(ng.curr(), "grid-template-columns", null));
		
		_tests.add(new TestData(ng.next(), "grid-template-areas", null));
		_tests.add(new TestData(ng.curr(), "grid-template-rows", null));
		_tests.add(new TestData(ng.curr(), "grid-template-columns", null));
		
		// grid-area
		ng.setName("grid-area");
		_tests.add(new TestData(ng.next(), "grid-row-start", GridStartEnd.AUTO));
		_tests.add(new TestData(ng.curr(), "grid-column-start", null));
		_tests.add(new TestData(ng.curr(), "grid-row-end", null));
		_tests.add(new TestData(ng.curr(), "grid-column-end", null));
		
		_tests.add(new TestData(ng.next(), "grid-row-start", GridStartEnd.AUTO));
		_tests.add(new TestData(ng.curr(), "grid-column-start", GridStartEnd.number, tf.createInteger(1)));
		_tests.add(new TestData(ng.curr(), "grid-row-end", null));
		_tests.add(new TestData(ng.curr(), "grid-column-end", null));
		
		_tests.add(new TestData(ng.next(), "grid-row-start", GridStartEnd.AUTO));
		_tests.add(new TestData(ng.curr(), "grid-column-start", GridStartEnd.number, tf.createInteger(1)));
		_tests.add(new TestData(ng.curr(), "grid-row-end", GridStartEnd.number, tf.createInteger(2)));
		_tests.add(new TestData(ng.curr(), "grid-column-end", null));
		
		_tests.add(new TestData(ng.next(), "grid-row-start", GridStartEnd.AUTO));
		_tests.add(new TestData(ng.curr(), "grid-column-start", GridStartEnd.number, tf.createInteger(1)));
		_tests.add(new TestData(ng.curr(), "grid-row-end", GridStartEnd.number, tf.createInteger(2)));
		_tests.add(new TestData(ng.curr(), "grid-column-end", GridStartEnd.number, tf.createInteger(3)));
		
		// grid-gap
		ng.setName("grid-gap");
		_tests.add(new TestData(ng.next(), "grid-row-gap", GridGap.NORMAL));
		_tests.add(new TestData(ng.curr(), "grid-column-gap", GridGap.NORMAL));
		
		_tests.add(new TestData(ng.next(), "grid-row-gap", GridGap.length, tf.createLength(1f, Unit.px)));
		_tests.add(new TestData(ng.curr(), "grid-column-gap", GridGap.length, tf.createLength(1f, Unit.px)));
		
		_tests.add(new TestData(ng.next(), "grid-row-gap", GridGap.length, tf.createLength(1f, Unit.px)));
		_tests.add(new TestData(ng.curr(), "grid-column-gap", GridGap.length, tf.createLength(1f, Unit.px)));
		
		_tests.add(new TestData(ng.next(), "grid-row-gap", GridGap.length, tf.createLength(1f, Unit.px)));
		_tests.add(new TestData(ng.curr(), "grid-column-gap", GridGap.length, tf.createLength(2f, Unit.px)));
		
		_tests.add(new TestData(ng.next(), "grid-row-gap", null));
		_tests.add(new TestData(ng.curr(), "grid-column-gap", null));
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

//			data = sm.get(elements.getElementById("test"));
//			checkProperty("grid", data);
//
//			checkProperty("grid-row-start", data);
//			checkProperty("grid-row-end", data);
//			checkProperty("grid-columns-start", data);
//			checkProperty("grid-column-end", data);
//
//			checkProperty("grid-template-areas", data);
//			checkProperty("grid-template-rows", data);
//			checkProperty("grid-template-columns", data);
//			checkProperty("grid-auto-flow", data);
//			checkProperty("grid-auto-rows", data);
//			checkProperty("grid-auto-columns", data);

			for (TestData test : _tests) {
				System.out.println("Testing " + test._id);
				String id = test._id;
				data = sm.get(elements.getElementById(id));
				prop = data.getProperty(test._propertyName);
				if (test._expextedProperty != prop) {
					System.out.println("expected = " + test._expextedProperty);
					System.out.println("actual = " + prop);

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
				Assert.assertEquals(test._expextedProperty, prop);
				if (test._expectedValue != null) {
					term = data.getValue(test._propertyName, true);
					if (!test._expectedValue.equals(term)) {
						System.out.println("expected = " + test._expectedValue);
						System.out.println("actual = " + term);

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
					Assert.assertEquals(test._expectedValue, term);
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

	private static class TestData {

		private final String _id;
		private final String _propertyName;
		private final CSSProperty _expextedProperty;
		private final Object _expectedValue;

		private TestData(String id, String propertyName, CSSProperty expextedProperty) {
			this(id, propertyName, expextedProperty, null);
		}

		private TestData(String id, String propertyName, CSSProperty expextedProperty, Object expectedValue) {
			_id = id;
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
