package test;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty.BoxShadow;
import cz.vutbr.web.css.Term.Operator;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermNumeric.Unit;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import test.TestUtils.NameGenerator;
import test.TestUtils.TestData;

/**
 *
 * @author Petr Mikulík
 */
public class BoxShadowTest {

    private static final String PROPERTY_NAME = "box-shadow";

    private static final TermFactory tf = CSSFactory.getTermFactory();
    private final List<TestData> _tests = new ArrayList<>();

    @Before
    public void prepare() {
        NameGenerator ng;
        TermList list;
        TermIdent redTerm = tf.createIdent("red");
        TermIdent blueTerm = tf.createIdent("blue");

        _tests.add(new TestData("none", PROPERTY_NAME, BoxShadow.NONE));
        _tests.add(new TestData("inherit", PROPERTY_NAME, BoxShadow.NONE));
        _tests.add(new TestData("initial", PROPERTY_NAME, BoxShadow.NONE));
        _tests.add(new TestData("unset", PROPERTY_NAME, BoxShadow.NONE));

        list = tf.createList();
        list.add(tf.createLength(5f, Unit.px));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        _tests.add(new TestData("minimal", PROPERTY_NAME, BoxShadow.component_values, list));

        list = tf.createList();
        list.add(tf.createLength(5f, Unit.px));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        _tests.add(new TestData("blur", PROPERTY_NAME, BoxShadow.component_values, list));

        list = tf.createList();
        list.add(tf.createLength(5f, Unit.px));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        _tests.add(new TestData("spread", PROPERTY_NAME, BoxShadow.component_values, list));

        list = tf.createList();
        list.add(tf.createLength(5f, Unit.px));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createColor(redTerm).setOperator(Operator.SPACE));
        _tests.add(new TestData("color", PROPERTY_NAME, BoxShadow.component_values, list));

        list = tf.createList();
        list.add(tf.createLength(5f, Unit.px));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createColor(redTerm).setOperator(Operator.SPACE));
        _tests.add(new TestData("blur_color", PROPERTY_NAME, BoxShadow.component_values, list));

        list = tf.createList();
        list.add(tf.createLength(5f, Unit.px));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createColor(redTerm).setOperator(Operator.SPACE));
        _tests.add(new TestData("spread_color", PROPERTY_NAME, BoxShadow.component_values, list));

        list = tf.createList();
        list.add(tf.createIdent("inset"));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        _tests.add(new TestData("inset", PROPERTY_NAME, BoxShadow.component_values, list));

        list = tf.createList();
        list.add(tf.createLength(5f, Unit.px));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createIdent("inset").setOperator(Operator.SPACE));
        _tests.add(new TestData("inset_blur", PROPERTY_NAME, BoxShadow.component_values, list));

        list = tf.createList();
        list.add(tf.createIdent("inset"));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        _tests.add(new TestData("inset_spread", PROPERTY_NAME, BoxShadow.component_values, list));

        list = tf.createList();
        list.add(tf.createIdent("inset"));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createColor(redTerm).setOperator(Operator.SPACE));
        _tests.add(new TestData("inset_color", PROPERTY_NAME, BoxShadow.component_values, list));

        list = tf.createList();
        list.add(tf.createColor(redTerm));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createIdent("inset").setOperator(Operator.SPACE));
        _tests.add(new TestData("inset_blur_color", PROPERTY_NAME, BoxShadow.component_values, list));

        list = tf.createList();
        list.add(tf.createIdent("inset"));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createColor(redTerm).setOperator(Operator.SPACE));
        _tests.add(new TestData("inset_spread_color", PROPERTY_NAME, BoxShadow.component_values, list));

        ng = new NameGenerator("multiple_");
        list = tf.createList();
        list.add(tf.createLength(5f, Unit.px));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.COMMA));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createColor(redTerm).setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.next(), PROPERTY_NAME, BoxShadow.component_values, list));

        list = tf.createList();
        list.add(tf.createIdent("inset"));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.COMMA));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createColor(redTerm).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.COMMA));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.next(), PROPERTY_NAME, BoxShadow.component_values, list));

        list = tf.createList();
        list.add(tf.createColor(redTerm));
        list.add(tf.createIdent("inset").setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.COMMA));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.COMMA));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(5f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createIdent("inset").setOperator(Operator.SPACE));
        list.add(tf.createColor(blueTerm).setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.next(), PROPERTY_NAME, BoxShadow.component_values, list));

        ng.setName("invalid_");
        _tests.add(new TestData(ng.next(), PROPERTY_NAME, BoxShadow.NONE));
        _tests.add(new TestData(ng.next(), PROPERTY_NAME, BoxShadow.NONE));
        _tests.add(new TestData(ng.next(), PROPERTY_NAME, BoxShadow.NONE));
        _tests.add(new TestData(ng.next(), PROPERTY_NAME, BoxShadow.NONE));
    }

    @Test
    public void test() {
        TestUtils.runTests(_tests, getClass().getResource("/simple/box-shadow.html"));
    }

}
