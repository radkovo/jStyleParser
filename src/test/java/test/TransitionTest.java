package test;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty.*;
import cz.vutbr.web.css.Term.Operator;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermList;
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
public class TransitionTest {

    private static final TermFactory tf = CSSFactory.getTermFactory();
    private final List<TestData> _tests = new ArrayList<>();

    @Before
    public void prepare() {
        NameGenerator ng = new NameGenerator("transition");
        TermList list;
        TermList fnList;

        _tests.add(new TestData(ng.next(), "transition-duration", TransitionDuration.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "transition-delay", TransitionDelay.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "transition-timing-function", TransitionTimingFunction.EASE));
        _tests.add(new TestData(ng.curr(), "transition-property", TransitionProperty.NONE));

        _tests.add(new TestData(ng.next(), "transition-duration", TransitionDuration.time, tf.createTime(3f)));
        _tests.add(new TestData(ng.curr(), "transition-delay", TransitionDelay.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "transition-timing-function", TransitionTimingFunction.EASE));
        _tests.add(new TestData(ng.curr(), "transition-property", TransitionProperty.ALL));

        _tests.add(new TestData(ng.next(), "transition-duration", TransitionDuration.time, tf.createTime(3f)));
        _tests.add(new TestData(ng.curr(), "transition-delay", TransitionDelay.time, tf.createTime(1f)));
        _tests.add(new TestData(ng.curr(), "transition-timing-function", TransitionTimingFunction.EASE));
        _tests.add(new TestData(ng.curr(), "transition-property", TransitionProperty.ALL));

        _tests.add(new TestData(ng.next(), "transition-duration", TransitionDuration.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "transition-delay", TransitionDelay.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "transition-timing-function", TransitionTimingFunction.EASE_IN_OUT));
        _tests.add(new TestData(ng.curr(), "transition-property", TransitionProperty.ALL));

        _tests.add(new TestData(ng.next(), "transition-duration", TransitionDuration.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "transition-delay", TransitionDelay.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "transition-timing-function", TransitionTimingFunction.EASE));
        _tests.add(new TestData(ng.curr(), "transition-property", TransitionProperty.custom_ident, tf.createIdent("propertyName")));

        _tests.add(new TestData(ng.next(), "transition-duration", TransitionDuration.time, tf.createTime(3f)));
        _tests.add(new TestData(ng.curr(), "transition-delay", TransitionDelay.time, tf.createTime(1f)));
        _tests.add(new TestData(ng.curr(), "transition-timing-function", TransitionTimingFunction.EASE));
        _tests.add(new TestData(ng.curr(), "transition-property", TransitionProperty.custom_ident, tf.createIdent("propertyName")));

        _tests.add(new TestData(ng.next(), "transition-duration", TransitionDuration.time, tf.createTime(3f)));
        _tests.add(new TestData(ng.curr(), "transition-delay", TransitionDelay.time, tf.createTime(1f)));
        _tests.add(new TestData(ng.curr(), "transition-timing-function", TransitionTimingFunction.EASE_IN));
        _tests.add(new TestData(ng.curr(), "transition-property", TransitionProperty.custom_ident, tf.createIdent("propertyName")));

        _tests.add(new TestData(ng.next(), "transition-duration", TransitionDuration.time, tf.createTime(3f)));
        _tests.add(new TestData(ng.curr(), "transition-delay", TransitionDelay.time, tf.createTime(1f)));
        _tests.add(new TestData(ng.curr(), "transition-timing-function", TransitionTimingFunction.EASE_IN));
        _tests.add(new TestData(ng.curr(), "transition-property", TransitionProperty.custom_ident, tf.createIdent("propertyName")));

        ng.setName("transition-delay");
        _tests.add(new TestData(ng.next(), "transition-delay", TransitionDelay.time, tf.createTime(1f)));

        _tests.add(new TestData(ng.next(), "transition-delay", TransitionDelay.time, tf.createTime(0f)));

        list = tf.createList();
        list.add(tf.createTime(1f));
        list.add(tf.createTime(2f).setOperator(Operator.COMMA));
        list.add(tf.createTime(3f).setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.next(), "transition-delay", TransitionDelay.list_values, list));

        _tests.add(new TestData(ng.next(), "transition-delay", TransitionDelay.time, tf.createTime(0f)));

        ng.setName("transition-duration");
        _tests.add(new TestData(ng.next(), "transition-duration", TransitionDuration.time, tf.createTime(1f)));

        _tests.add(new TestData(ng.next(), "transition-duration", TransitionDuration.time, tf.createTime(0f)));

        list = tf.createList();
        list.add(tf.createTime(1f));
        list.add(tf.createTime(2f).setOperator(Operator.COMMA));
        list.add(tf.createTime(3f).setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.next(), "transition-duration", TransitionDuration.list_values, list));

        _tests.add(new TestData(ng.next(), "transition-duration", TransitionDuration.time, tf.createTime(0f)));

        ng.setName("transition-property");
        _tests.add(new TestData(ng.next(), "transition-property", TransitionProperty.custom_ident, tf.createIdent("margin-top")));

        list = tf.createList();
        list.add(tf.createIdent("margin"));
        list.add(tf.createIdent("padding").setOperator(Operator.COMMA));
        list.add(tf.createIdent("box-shadow").setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.next(), "transition-property", TransitionProperty.list_values, list));

        _tests.add(new TestData(ng.next(), "transition-property", TransitionProperty.ALL));

        _tests.add(new TestData(ng.next(), "transition-property", TransitionProperty.ALL));

        ng.setName("transition-timing-function");
        _tests.add(new TestData(ng.next(), "transition-timing-function", TransitionTimingFunction.LINEAR));

        list = tf.createList();
        list.add(tf.createIdent("ease"));
        list.add(tf.createIdent("ease-in").setOperator(Operator.COMMA));
        list.add(tf.createIdent("ease-in-out").setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.next(), "transition-timing-function", TransitionTimingFunction.list_values, list));

        _tests.add(new TestData(ng.next(), "transition-timing-function", TransitionTimingFunction.EASE));

        _tests.add(new TestData(ng.next(), "transition-timing-function", TransitionTimingFunction.EASE));

        fnList = tf.createList();
        fnList.add(tf.createNumber(0.1f));
        fnList.add(tf.createOperator(','));
        fnList.add(tf.createNumber(0.7f));
        fnList.add(tf.createOperator(','));
        fnList.add(tf.createNumber(1f));
        fnList.add(tf.createOperator(','));
        fnList.add(tf.createNumber(0.1f));
        _tests.add(new TestData(ng.next(), "transition-timing-function", TransitionTimingFunction.timing_function, tf.createFunction("cubic-bezier", fnList)));

        _tests.add(new TestData(ng.next(), "transition-timing-function", TransitionTimingFunction.EASE));

        _tests.add(new TestData(ng.next(), "transition-timing-function", TransitionTimingFunction.EASE));

        fnList = tf.createList();
        fnList.add(tf.createInteger(5));
        fnList.add(tf.createOperator(','));
        fnList.add(tf.createIdent("jump-both"));
        _tests.add(new TestData(ng.next(), "transition-timing-function", TransitionTimingFunction.timing_function, tf.createFunction("steps", fnList)));

        _tests.add(new TestData(ng.next(), "transition-timing-function", TransitionTimingFunction.EASE));

        fnList = tf.createList();
        fnList.add(tf.createInteger(10));
        _tests.add(new TestData(ng.next(), "transition-timing-function", TransitionTimingFunction.timing_function, tf.createFunction("frames", fnList)));

        _tests.add(new TestData(ng.next(), "transition-timing-function", TransitionTimingFunction.EASE));
    }

    @Test
    public void test() {
        TestUtils.runTests(_tests, getClass().getResource("/advanced/transition.html"));
    }

}
