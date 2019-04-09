package test;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty.AlignContent;
import cz.vutbr.web.css.CSSProperty.AlignItems;
import cz.vutbr.web.css.CSSProperty.AlignSelf;
import cz.vutbr.web.css.CSSProperty.FlexBasis;
import cz.vutbr.web.css.CSSProperty.FlexDirection;
import cz.vutbr.web.css.CSSProperty.FlexGrow;
import cz.vutbr.web.css.CSSProperty.FlexShrink;
import cz.vutbr.web.css.CSSProperty.FlexWrap;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermNumeric.Unit;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import test.TestUtils.NameGenerator;
import test.TestUtils.TestData;

/**
 *
 * @author burgetr
 */
public class FlextLayoutTest {

    private static final TermFactory tf = CSSFactory.getTermFactory();
    private final List<TestData> _tests = new ArrayList<>();

    @Before
    public void prepare() {
        NameGenerator ng = new NameGenerator("flex");

        _tests.add(new TestData(ng.next(), "flex-basis", FlexBasis.AUTO));
        _tests.add(new TestData(ng.curr(), "flex-grow", FlexGrow.number, tf.createNumber(0.0f)));
        _tests.add(new TestData(ng.curr(), "flex-shrink", FlexShrink.number, tf.createNumber(0.0f)));
        
        _tests.add(new TestData(ng.next(), "flex-basis", FlexBasis.AUTO));
        _tests.add(new TestData(ng.curr(), "flex-grow", FlexGrow.number, tf.createNumber(0.0f)));
        _tests.add(new TestData(ng.curr(), "flex-shrink", FlexShrink.number, tf.createNumber(1.0f)));
        
        _tests.add(new TestData(ng.next(), "flex-basis", FlexBasis.AUTO));
        _tests.add(new TestData(ng.curr(), "flex-grow", FlexGrow.number, tf.createNumber(1.0f)));
        _tests.add(new TestData(ng.curr(), "flex-shrink", FlexShrink.number, tf.createNumber(1.0f)));
        
        _tests.add(new TestData(ng.next(), "flex-basis", FlexBasis.length, tf.createLength(0.0f)));
        _tests.add(new TestData(ng.curr(), "flex-grow", FlexGrow.number, tf.createNumber(5.0f)));
        _tests.add(new TestData(ng.curr(), "flex-shrink", FlexShrink.number, tf.createNumber(1.0f)));
        
        _tests.add(new TestData(ng.next(), "flex-basis", FlexBasis.length, tf.createLength(12.0f, Unit.px)));
        //_tests.add(new TestData(ng.curr(), "flex-grow", FlexGrow.number, tf.createNumber(1.0f))); //TODO specification?
        _tests.add(new TestData(ng.curr(), "flex-shrink", FlexShrink.number, tf.createNumber(1.0f)));
        
        _tests.add(new TestData(ng.next(), "flex-basis", FlexBasis.length, tf.createLength(0.0f)));
        _tests.add(new TestData(ng.curr(), "flex-grow", FlexGrow.number, tf.createNumber(5.0f)));
        _tests.add(new TestData(ng.curr(), "flex-shrink", FlexShrink.number, tf.createNumber(6.0f)));
        
        _tests.add(new TestData(ng.next(), "flex-basis", FlexBasis.length, tf.createLength(12.0f, Unit.px)));
        _tests.add(new TestData(ng.curr(), "flex-grow", FlexGrow.number, tf.createNumber(5.0f)));
        _tests.add(new TestData(ng.curr(), "flex-shrink", FlexShrink.number, tf.createNumber(1.0f)));
        
        _tests.add(new TestData(ng.next(), "flex-basis", FlexBasis.AUTO));
        _tests.add(new TestData(ng.curr(), "flex-grow", FlexGrow.number, tf.createNumber(5.0f)));
        _tests.add(new TestData(ng.curr(), "flex-shrink", FlexShrink.number, tf.createNumber(1.0f)));
        
        _tests.add(new TestData(ng.next(), "flex-basis", FlexBasis.length, tf.createLength(12.0f, Unit.px)));
        _tests.add(new TestData(ng.curr(), "flex-grow", FlexGrow.number, tf.createNumber(5.0f)));
        _tests.add(new TestData(ng.curr(), "flex-shrink", FlexShrink.number, tf.createNumber(6.0f)));
        
        _tests.add(new TestData(ng.next(), "flex-basis", FlexBasis.AUTO));
        _tests.add(new TestData(ng.curr(), "flex-grow", FlexGrow.number, tf.createNumber(5.0f)));
        _tests.add(new TestData(ng.curr(), "flex-shrink", FlexShrink.number, tf.createNumber(6.0f)));
        
        _tests.add(new TestData(ng.next(), "flex-basis", FlexBasis.length, tf.createLength(12.0f, Unit.px)));
        _tests.add(new TestData(ng.curr(), "flex-grow", FlexGrow.number, tf.createNumber(5.0f)));
        _tests.add(new TestData(ng.curr(), "flex-shrink", FlexShrink.number, tf.createNumber(6.0f)));
        
        
        ng = new NameGenerator("flex-flow");
        _tests.add(new TestData(ng.next(), "flex-direction", FlexDirection.COLUMN_REVERSE));
        _tests.add(new TestData(ng.curr(), "flex-wrap", FlexWrap.WRAP));

        _tests.add(new TestData(ng.next(), "flex-direction", FlexDirection.COLUMN));
        _tests.add(new TestData(ng.curr(), "flex-wrap", FlexWrap.NOWRAP));
        
        _tests.add(new TestData(ng.next(), "flex-direction", FlexDirection.ROW));
        _tests.add(new TestData(ng.curr(), "flex-wrap", FlexWrap.WRAP_REVERSE));

        ng = new NameGenerator("align");
        _tests.add(new TestData(ng.next(), "align-content", AlignContent.CENTER));
        _tests.add(new TestData(ng.next(), "align-items", AlignItems.STRETCH));
        _tests.add(new TestData(ng.next(), "align-self", AlignSelf.BASELINE));
        
    }

    @Test
    public void test() {
        TestUtils.runTests(_tests, getClass().getResource("/simple/flex-layout.html"));
    }

}
