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
public class AnimationTest {

    private static final TermFactory tf = CSSFactory.getTermFactory();
    private final List<TestData> _tests = new ArrayList<>();

    @Before
    public void prepare() {
        NameGenerator ng = new NameGenerator("animation");
        TermList list;
        TermList fnList;

        _tests.add(new TestData(ng.next(), "animation-duration", AnimationDuration.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "animation-timing-function", AnimationTimingFunction.EASE));
        _tests.add(new TestData(ng.curr(), "animation-delay", AnimationDelay.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "animation-iteration-count", AnimationIterationCount.number, tf.createInteger(1)));
        _tests.add(new TestData(ng.curr(), "animation-direction", AnimationDirection.NORMAL));
        _tests.add(new TestData(ng.curr(), "animation-fill-mode", AnimationFillMode.NONE));
        _tests.add(new TestData(ng.curr(), "animation-play-state", AnimationPlayState.RUNNING));
        _tests.add(new TestData(ng.curr(), "animation-name", AnimationName.NONE));

        _tests.add(new TestData(ng.next(), "animation-duration", AnimationDuration.time, tf.createTime(3f)));
        _tests.add(new TestData(ng.curr(), "animation-timing-function", AnimationTimingFunction.EASE));
        _tests.add(new TestData(ng.curr(), "animation-delay", AnimationDelay.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "animation-iteration-count", AnimationIterationCount.number, tf.createInteger(1)));
        _tests.add(new TestData(ng.curr(), "animation-direction", AnimationDirection.NORMAL));
        _tests.add(new TestData(ng.curr(), "animation-fill-mode", AnimationFillMode.NONE));
        _tests.add(new TestData(ng.curr(), "animation-play-state", AnimationPlayState.RUNNING));
        _tests.add(new TestData(ng.curr(), "animation-name", AnimationName.NONE));

        _tests.add(new TestData(ng.next(), "animation-duration", AnimationDuration.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "animation-timing-function", AnimationTimingFunction.EASE_IN_OUT));
        _tests.add(new TestData(ng.curr(), "animation-delay", AnimationDelay.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "animation-iteration-count", AnimationIterationCount.number, tf.createInteger(1)));
        _tests.add(new TestData(ng.curr(), "animation-direction", AnimationDirection.NORMAL));
        _tests.add(new TestData(ng.curr(), "animation-fill-mode", AnimationFillMode.NONE));
        _tests.add(new TestData(ng.curr(), "animation-play-state", AnimationPlayState.RUNNING));
        _tests.add(new TestData(ng.curr(), "animation-name", AnimationName.NONE));

        _tests.add(new TestData(ng.next(), "animation-duration", AnimationDuration.time, tf.createTime(3f)));
        _tests.add(new TestData(ng.curr(), "animation-timing-function", AnimationTimingFunction.EASE));
        _tests.add(new TestData(ng.curr(), "animation-delay", AnimationDelay.time, tf.createTime(1f)));
        _tests.add(new TestData(ng.curr(), "animation-iteration-count", AnimationIterationCount.number, tf.createInteger(1)));
        _tests.add(new TestData(ng.curr(), "animation-direction", AnimationDirection.NORMAL));
        _tests.add(new TestData(ng.curr(), "animation-fill-mode", AnimationFillMode.NONE));
        _tests.add(new TestData(ng.curr(), "animation-play-state", AnimationPlayState.RUNNING));
        _tests.add(new TestData(ng.curr(), "animation-name", AnimationName.NONE));

        _tests.add(new TestData(ng.next(), "animation-duration", AnimationDuration.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "animation-timing-function", AnimationTimingFunction.EASE));
        _tests.add(new TestData(ng.curr(), "animation-delay", AnimationDelay.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "animation-iteration-count", AnimationIterationCount.number, tf.createInteger(5)));
        _tests.add(new TestData(ng.curr(), "animation-direction", AnimationDirection.NORMAL));
        _tests.add(new TestData(ng.curr(), "animation-fill-mode", AnimationFillMode.NONE));
        _tests.add(new TestData(ng.curr(), "animation-play-state", AnimationPlayState.RUNNING));
        _tests.add(new TestData(ng.curr(), "animation-name", AnimationName.NONE));

        _tests.add(new TestData(ng.next(), "animation-duration", AnimationDuration.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "animation-timing-function", AnimationTimingFunction.EASE));
        _tests.add(new TestData(ng.curr(), "animation-delay", AnimationDelay.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "animation-iteration-count", AnimationIterationCount.number, tf.createInteger(1)));
        _tests.add(new TestData(ng.curr(), "animation-direction", AnimationDirection.ALTERNATE_REVERSE));
        _tests.add(new TestData(ng.curr(), "animation-fill-mode", AnimationFillMode.NONE));
        _tests.add(new TestData(ng.curr(), "animation-play-state", AnimationPlayState.RUNNING));
        _tests.add(new TestData(ng.curr(), "animation-name", AnimationName.NONE));

        _tests.add(new TestData(ng.next(), "animation-duration", AnimationDuration.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "animation-timing-function", AnimationTimingFunction.EASE));
        _tests.add(new TestData(ng.curr(), "animation-delay", AnimationDelay.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "animation-iteration-count", AnimationIterationCount.number, tf.createInteger(1)));
        _tests.add(new TestData(ng.curr(), "animation-direction", AnimationDirection.NORMAL));
        _tests.add(new TestData(ng.curr(), "animation-fill-mode", AnimationFillMode.BACKWARDS));
        _tests.add(new TestData(ng.curr(), "animation-play-state", AnimationPlayState.RUNNING));
        _tests.add(new TestData(ng.curr(), "animation-name", AnimationName.NONE));

        _tests.add(new TestData(ng.next(), "animation-duration", AnimationDuration.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "animation-timing-function", AnimationTimingFunction.EASE));
        _tests.add(new TestData(ng.curr(), "animation-delay", AnimationDelay.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "animation-iteration-count", AnimationIterationCount.number, tf.createInteger(1)));
        _tests.add(new TestData(ng.curr(), "animation-direction", AnimationDirection.NORMAL));
        _tests.add(new TestData(ng.curr(), "animation-fill-mode", AnimationFillMode.NONE));
        _tests.add(new TestData(ng.curr(), "animation-play-state", AnimationPlayState.PAUSED));
        _tests.add(new TestData(ng.curr(), "animation-name", AnimationName.NONE));

        _tests.add(new TestData(ng.next(), "animation-duration", AnimationDuration.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "animation-timing-function", AnimationTimingFunction.EASE));
        _tests.add(new TestData(ng.curr(), "animation-delay", AnimationDelay.time, tf.createTime(0f)));
        _tests.add(new TestData(ng.curr(), "animation-iteration-count", AnimationIterationCount.number, tf.createInteger(1)));
        _tests.add(new TestData(ng.curr(), "animation-direction", AnimationDirection.NORMAL));
        _tests.add(new TestData(ng.curr(), "animation-fill-mode", AnimationFillMode.NONE));
        _tests.add(new TestData(ng.curr(), "animation-play-state", AnimationPlayState.RUNNING));
        _tests.add(new TestData(ng.curr(), "animation-name", AnimationName.custom_ident, tf.createIdent("someName")));
        
        _tests.add(new TestData(ng.next(), "animation-duration", AnimationDuration.time, tf.createTime(3f)));
        _tests.add(new TestData(ng.curr(), "animation-timing-function", AnimationTimingFunction.LINEAR));
        _tests.add(new TestData(ng.curr(), "animation-delay", AnimationDelay.time, tf.createTime(1f)));
        _tests.add(new TestData(ng.curr(), "animation-iteration-count", AnimationIterationCount.number, tf.createInteger(1)));
        _tests.add(new TestData(ng.curr(), "animation-direction", AnimationDirection.NORMAL));
        _tests.add(new TestData(ng.curr(), "animation-fill-mode", AnimationFillMode.NONE));
        _tests.add(new TestData(ng.curr(), "animation-play-state", AnimationPlayState.RUNNING));
        _tests.add(new TestData(ng.curr(), "animation-name", AnimationName.custom_ident, tf.createIdent("someName")));

        _tests.add(new TestData(ng.next(), "animation-duration", AnimationDuration.time, tf.createTime(3f)));
        _tests.add(new TestData(ng.curr(), "animation-timing-function", AnimationTimingFunction.EASE_IN));
        _tests.add(new TestData(ng.curr(), "animation-delay", AnimationDelay.time, tf.createTime(1f)));
        _tests.add(new TestData(ng.curr(), "animation-iteration-count", AnimationIterationCount.number, tf.createInteger(5)));
        _tests.add(new TestData(ng.curr(), "animation-direction", AnimationDirection.REVERSE));
        _tests.add(new TestData(ng.curr(), "animation-fill-mode", AnimationFillMode.BOTH));
        _tests.add(new TestData(ng.curr(), "animation-play-state", AnimationPlayState.PAUSED));
        _tests.add(new TestData(ng.curr(), "animation-name", AnimationName.custom_ident, tf.createIdent("someName")));
        
        _tests.add(new TestData(ng.next(), "animation-duration", AnimationDuration.time, tf.createTime(3f)));
        _tests.add(new TestData(ng.curr(), "animation-timing-function", AnimationTimingFunction.EASE_IN));
        _tests.add(new TestData(ng.curr(), "animation-delay", AnimationDelay.time, tf.createTime(1f)));
        _tests.add(new TestData(ng.curr(), "animation-iteration-count", AnimationIterationCount.number, tf.createInteger(5)));
        _tests.add(new TestData(ng.curr(), "animation-direction", AnimationDirection.REVERSE));
        _tests.add(new TestData(ng.curr(), "animation-fill-mode", AnimationFillMode.BOTH));
        _tests.add(new TestData(ng.curr(), "animation-play-state", AnimationPlayState.PAUSED));
        _tests.add(new TestData(ng.curr(), "animation-name", AnimationName.custom_ident, tf.createIdent("someName")));
        
        list = tf.createList();
        list.add(tf.createTime(1f));
        list.add(tf.createTime(3f).setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.next(), "animation-duration", AnimationDuration.list_values, list));
        list = tf.createList();
        list.add(tf.createIdent("ease"));
        list.add(tf.createIdent("ease-in").setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.curr(), "animation-timing-function", AnimationTimingFunction.list_values, list));
        list = tf.createList();
        list.add(tf.createTime(2f));
        list.add(tf.createTime(4f).setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.curr(), "animation-delay", AnimationDelay.list_values, list));
        list = tf.createList();
        list.add(tf.createIdent("infinite"));
        list.add(tf.createInteger(5).setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.curr(), "animation-iteration-count", AnimationIterationCount.list_values, list));
        list = tf.createList();
        list.add(tf.createIdent("alternate"));
        list.add(tf.createIdent("reverse").setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.curr(), "animation-direction", AnimationDirection.list_values, list));
        list = tf.createList();
        list.add(tf.createIdent("both"));
        list.add(tf.createIdent("backwards").setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.curr(), "animation-fill-mode", AnimationFillMode.list_values, list));
        list = tf.createList();
        list.add(tf.createIdent("running"));
        list.add(tf.createIdent("paused").setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.curr(), "animation-play-state", AnimationPlayState.list_values, list));
        list = tf.createList();
        list.add(tf.createIdent("name1"));
        list.add(tf.createIdent("name2").setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.curr(), "animation-name", AnimationName.list_values, list));
        
        ng.setName("animation-delay");
        _tests.add(new TestData(ng.next(), "animation-delay", AnimationDelay.time, tf.createTime(1f)));

        _tests.add(new TestData(ng.next(), "animation-delay", AnimationDelay.time, tf.createTime(0f)));

        list = tf.createList();
        list.add(tf.createTime(1f));
        list.add(tf.createTime(2f).setOperator(Operator.COMMA));
        list.add(tf.createTime(3f).setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.next(), "animation-delay", AnimationDelay.list_values, list));

        _tests.add(new TestData(ng.next(), "animation-delay", AnimationDelay.time, tf.createTime(0f)));

        ng.setName("animation-direction");
        _tests.add(new TestData(ng.next(), "animation-direction", AnimationDirection.REVERSE));

        list = tf.createList();
        list.add(tf.createIdent("alternate"));
        list.add(tf.createIdent("alternate-reverse").setOperator(Operator.COMMA));
        list.add(tf.createIdent("normal").setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.next(), "animation-direction", AnimationDirection.list_values, list));

        _tests.add(new TestData(ng.next(), "animation-direction", AnimationDirection.NORMAL));

        _tests.add(new TestData(ng.next(), "animation-direction", AnimationDirection.NORMAL));

        ng.setName("animation-duration");
        _tests.add(new TestData(ng.next(), "animation-duration", AnimationDuration.time, tf.createTime(1f)));

        _tests.add(new TestData(ng.next(), "animation-duration", AnimationDuration.time, tf.createTime(0f)));

        list = tf.createList();
        list.add(tf.createTime(1f));
        list.add(tf.createTime(2f).setOperator(Operator.COMMA));
        list.add(tf.createTime(3f).setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.next(), "animation-duration", AnimationDuration.list_values, list));

        _tests.add(new TestData(ng.next(), "animation-duration", AnimationDuration.time, tf.createTime(0f)));

        ng.setName("animation-fill-mode");
        _tests.add(new TestData(ng.next(), "animation-fill-mode", AnimationFillMode.FORWARDS));

        list = tf.createList();
        list.add(tf.createIdent("backwards"));
        list.add(tf.createIdent("none").setOperator(Operator.COMMA));
        list.add(tf.createIdent("both").setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.next(), "animation-fill-mode", AnimationFillMode.list_values, list));

        _tests.add(new TestData(ng.next(), "animation-fill-mode", AnimationFillMode.NONE));

        _tests.add(new TestData(ng.next(), "animation-fill-mode", AnimationFillMode.NONE));

        ng.setName("animation-iteration-count");
        _tests.add(new TestData(ng.next(), "animation-iteration-count", AnimationIterationCount.INFINITE));

        list = tf.createList();
        list.add(tf.createInteger(0));
        list.add(tf.createNumber(1.5f).setOperator(Operator.COMMA));
        list.add(tf.createInteger(3).setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.next(), "animation-iteration-count", AnimationIterationCount.list_values, list));

        _tests.add(new TestData(ng.next(), "animation-iteration-count", AnimationIterationCount.number, tf.createInteger(1)));

        _tests.add(new TestData(ng.next(), "animation-iteration-count", AnimationIterationCount.number, tf.createInteger(1)));

        ng.setName("animation-name");
        _tests.add(new TestData(ng.next(), "animation-name", AnimationName.custom_ident, tf.createIdent("someName")));

        list = tf.createList();
        list.add(tf.createIdent("number_42"));
        list.add(tf.createIdent("-dash").setOperator(Operator.COMMA));
        list.add(tf.createIdent("_underscore").setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.next(), "animation-name", AnimationName.list_values, list));

        _tests.add(new TestData(ng.next(), "animation-name", AnimationName.NONE));

        _tests.add(new TestData(ng.next(), "animation-name", AnimationName.NONE));

        ng.setName("animation-play-state");
        _tests.add(new TestData(ng.next(), "animation-play-state", AnimationPlayState.PAUSED));

        list = tf.createList();
        list.add(tf.createIdent("running"));
        list.add(tf.createIdent("paused").setOperator(Operator.COMMA));
        list.add(tf.createIdent("paused").setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.next(), "animation-play-state", AnimationPlayState.list_values, list));

        _tests.add(new TestData(ng.next(), "animation-play-state", AnimationPlayState.RUNNING));

        _tests.add(new TestData(ng.next(), "animation-play-state", AnimationPlayState.RUNNING));

        ng.setName("animation-timing-function");
        _tests.add(new TestData(ng.next(), "animation-timing-function", AnimationTimingFunction.LINEAR));

        list = tf.createList();
        list.add(tf.createIdent("ease"));
        list.add(tf.createIdent("ease-in").setOperator(Operator.COMMA));
        list.add(tf.createIdent("ease-in-out").setOperator(Operator.COMMA));
        _tests.add(new TestData(ng.next(), "animation-timing-function", AnimationTimingFunction.list_values, list));

        _tests.add(new TestData(ng.next(), "animation-timing-function", AnimationTimingFunction.EASE));

        _tests.add(new TestData(ng.next(), "animation-timing-function", AnimationTimingFunction.EASE));

        fnList = tf.createList();
        fnList.add(tf.createNumber(0.1f));
        fnList.add(tf.createOperator(','));
        fnList.add(tf.createNumber(0.7f));
        fnList.add(tf.createOperator(','));
        fnList.add(tf.createNumber(1f));
        fnList.add(tf.createOperator(','));
        fnList.add(tf.createNumber(0.1f));
        _tests.add(new TestData(ng.next(), "animation-timing-function", AnimationTimingFunction.timing_function, tf.createFunction("cubic-bezier", fnList)));

        _tests.add(new TestData(ng.next(), "animation-timing-function", AnimationTimingFunction.EASE));

        _tests.add(new TestData(ng.next(), "animation-timing-function", AnimationTimingFunction.EASE));

        fnList = tf.createList();
        fnList.add(tf.createInteger(5));
        fnList.add(tf.createOperator(','));
        fnList.add(tf.createIdent("jump-both"));
        _tests.add(new TestData(ng.next(), "animation-timing-function", AnimationTimingFunction.timing_function, tf.createFunction("steps", fnList)));

        _tests.add(new TestData(ng.next(), "animation-timing-function", AnimationTimingFunction.EASE));

        fnList = tf.createList();
        fnList.add(tf.createInteger(10));
        _tests.add(new TestData(ng.next(), "animation-timing-function", AnimationTimingFunction.timing_function, tf.createFunction("frames", fnList)));

        _tests.add(new TestData(ng.next(), "animation-timing-function", AnimationTimingFunction.EASE));
    }

    @Test
    public void test() {
        TestUtils.runTests(_tests, getClass().getResource("/advanced/animation.html"));
    }

}
