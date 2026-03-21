package test;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty.AlignContent;
import cz.vutbr.web.css.CSSProperty.AlignItems;
import cz.vutbr.web.css.CSSProperty.AlignSelf;
import cz.vutbr.web.css.CSSProperty.ColumnGap;
import cz.vutbr.web.css.CSSProperty.JustifyContent;
import cz.vutbr.web.css.CSSProperty.JustifyItems;
import cz.vutbr.web.css.CSSProperty.JustifySelf;
import cz.vutbr.web.css.CSSProperty.RowGap;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermNumeric.Unit;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import test.TestUtils.NameGenerator;
import test.TestUtils.TestData;

/**
 * Tests for CSS Box Alignment Module Level 3 properties:
 * extended align-content/items/self, justify-content/items/self,
 * gap/row-gap/column-gap, and place-content/items/self shorthands.
 */
public class BoxAlignmentTest {

    private static final TermFactory tf = CSSFactory.getTermFactory();
    private final List<TestData> _tests = new ArrayList<>();

    @Before
    public void prepare() {
        NameGenerator ng;

        // --- align-content: new values ---
        ng = new NameGenerator("ac");
        _tests.add(new TestData(ng.next(), "align-content", AlignContent.START));
        _tests.add(new TestData(ng.next(), "align-content", AlignContent.END));
        _tests.add(new TestData(ng.next(), "align-content", AlignContent.NORMAL));
        _tests.add(new TestData(ng.next(), "align-content", AlignContent.SPACE_EVENLY));
        _tests.add(new TestData(ng.next(), "align-content", AlignContent.FIRST_BASELINE));
        _tests.add(new TestData(ng.next(), "align-content", AlignContent.LAST_BASELINE));

        // --- align-items: new values ---
        ng = new NameGenerator("ai");
        _tests.add(new TestData(ng.next(), "align-items", AlignItems.START));
        _tests.add(new TestData(ng.next(), "align-items", AlignItems.END));
        _tests.add(new TestData(ng.next(), "align-items", AlignItems.SELF_START));
        _tests.add(new TestData(ng.next(), "align-items", AlignItems.SELF_END));
        _tests.add(new TestData(ng.next(), "align-items", AlignItems.NORMAL));
        _tests.add(new TestData(ng.next(), "align-items", AlignItems.FIRST_BASELINE));
        _tests.add(new TestData(ng.next(), "align-items", AlignItems.LAST_BASELINE));

        // --- align-self: new values ---
        ng = new NameGenerator("as");
        _tests.add(new TestData(ng.next(), "align-self", AlignSelf.START));
        _tests.add(new TestData(ng.next(), "align-self", AlignSelf.END));
        _tests.add(new TestData(ng.next(), "align-self", AlignSelf.SELF_START));
        _tests.add(new TestData(ng.next(), "align-self", AlignSelf.SELF_END));
        _tests.add(new TestData(ng.next(), "align-self", AlignSelf.NORMAL));
        _tests.add(new TestData(ng.next(), "align-self", AlignSelf.FIRST_BASELINE));
        _tests.add(new TestData(ng.next(), "align-self", AlignSelf.LAST_BASELINE));

        // --- justify-content: new values ---
        ng = new NameGenerator("jc");
        _tests.add(new TestData(ng.next(), "justify-content", JustifyContent.START));
        _tests.add(new TestData(ng.next(), "justify-content", JustifyContent.END));
        _tests.add(new TestData(ng.next(), "justify-content", JustifyContent.LEFT));
        _tests.add(new TestData(ng.next(), "justify-content", JustifyContent.RIGHT));
        _tests.add(new TestData(ng.next(), "justify-content", JustifyContent.NORMAL));
        _tests.add(new TestData(ng.next(), "justify-content", JustifyContent.STRETCH));
        _tests.add(new TestData(ng.next(), "justify-content", JustifyContent.SPACE_EVENLY));

        // --- justify-items ---
        ng = new NameGenerator("ji");
        _tests.add(new TestData(ng.next(), "justify-items", JustifyItems.CENTER));
        _tests.add(new TestData(ng.next(), "justify-items", JustifyItems.START));
        _tests.add(new TestData(ng.next(), "justify-items", JustifyItems.END));
        _tests.add(new TestData(ng.next(), "justify-items", JustifyItems.LEFT));
        _tests.add(new TestData(ng.next(), "justify-items", JustifyItems.RIGHT));
        _tests.add(new TestData(ng.next(), "justify-items", JustifyItems.FLEX_START));
        _tests.add(new TestData(ng.next(), "justify-items", JustifyItems.FLEX_END));
        _tests.add(new TestData(ng.next(), "justify-items", JustifyItems.SELF_START));
        _tests.add(new TestData(ng.next(), "justify-items", JustifyItems.SELF_END));
        _tests.add(new TestData(ng.next(), "justify-items", JustifyItems.BASELINE));
        _tests.add(new TestData(ng.next(), "justify-items", JustifyItems.FIRST_BASELINE));
        _tests.add(new TestData(ng.next(), "justify-items", JustifyItems.LAST_BASELINE));
        _tests.add(new TestData(ng.next(), "justify-items", JustifyItems.STRETCH));
        _tests.add(new TestData(ng.next(), "justify-items", JustifyItems.NORMAL));
        _tests.add(new TestData(ng.next(), "justify-items", JustifyItems.LEGACY));

        // --- justify-self ---
        ng = new NameGenerator("js");
        _tests.add(new TestData(ng.next(), "justify-self", JustifySelf.AUTO));
        _tests.add(new TestData(ng.next(), "justify-self", JustifySelf.CENTER));
        _tests.add(new TestData(ng.next(), "justify-self", JustifySelf.START));
        _tests.add(new TestData(ng.next(), "justify-self", JustifySelf.END));
        _tests.add(new TestData(ng.next(), "justify-self", JustifySelf.LEFT));
        _tests.add(new TestData(ng.next(), "justify-self", JustifySelf.RIGHT));
        _tests.add(new TestData(ng.next(), "justify-self", JustifySelf.FLEX_START));
        _tests.add(new TestData(ng.next(), "justify-self", JustifySelf.FLEX_END));
        _tests.add(new TestData(ng.next(), "justify-self", JustifySelf.SELF_START));
        _tests.add(new TestData(ng.next(), "justify-self", JustifySelf.SELF_END));
        _tests.add(new TestData(ng.next(), "justify-self", JustifySelf.BASELINE));
        _tests.add(new TestData(ng.next(), "justify-self", JustifySelf.FIRST_BASELINE));
        _tests.add(new TestData(ng.next(), "justify-self", JustifySelf.LAST_BASELINE));
        _tests.add(new TestData(ng.next(), "justify-self", JustifySelf.STRETCH));
        _tests.add(new TestData(ng.next(), "justify-self", JustifySelf.NORMAL));

        // --- gap shorthand: expands to row-gap + column-gap ---
        // gap0: gap: normal
        _tests.add(new TestData("gap0", "row-gap", RowGap.NORMAL));
        _tests.add(new TestData("gap0", "column-gap", ColumnGap.NORMAL));
        // gap1: gap: 10px  → both row-gap and column-gap = 10px
        _tests.add(new TestData("gap1", "row-gap", RowGap.length, tf.createLength(10f, Unit.px)));
        _tests.add(new TestData("gap1", "column-gap", ColumnGap.length, tf.createLength(10f, Unit.px)));
        // gap2: gap: 10px 20px → row-gap=10px, column-gap=20px
        _tests.add(new TestData("gap2", "row-gap", RowGap.length, tf.createLength(10f, Unit.px)));
        _tests.add(new TestData("gap2", "column-gap", ColumnGap.length, tf.createLength(20f, Unit.px)));

        // --- row-gap ---
        _tests.add(new TestData("rg0", "row-gap", RowGap.NORMAL));
        _tests.add(new TestData("rg1", "row-gap", RowGap.length, tf.createLength(10f, Unit.px)));

        // --- column-gap ---
        _tests.add(new TestData("cg0", "column-gap", ColumnGap.NORMAL));
        _tests.add(new TestData("cg1", "column-gap", ColumnGap.length, tf.createLength(10f, Unit.px)));

        // --- place-content shorthand: expands to align-content + justify-content ---
        // pc0: place-content: center → both center
        _tests.add(new TestData("pc0", "align-content", AlignContent.CENTER));
        _tests.add(new TestData("pc0", "justify-content", JustifyContent.CENTER));
        // pc1: place-content: start end → align=start, justify=end
        _tests.add(new TestData("pc1", "align-content", AlignContent.START));
        _tests.add(new TestData("pc1", "justify-content", JustifyContent.END));
        // pc2: place-content: first baseline space-evenly → align=first baseline, justify=space-evenly
        _tests.add(new TestData("pc2", "align-content", AlignContent.FIRST_BASELINE));
        _tests.add(new TestData("pc2", "justify-content", JustifyContent.SPACE_EVENLY));

        // --- place-items shorthand: expands to align-items + justify-items ---
        // pi0: place-items: stretch → both stretch
        _tests.add(new TestData("pi0", "align-items", AlignItems.STRETCH));
        _tests.add(new TestData("pi0", "justify-items", JustifyItems.STRETCH));
        // pi1: place-items: start end → align=start, justify=end
        _tests.add(new TestData("pi1", "align-items", AlignItems.START));
        _tests.add(new TestData("pi1", "justify-items", JustifyItems.END));

        // --- place-self shorthand: expands to align-self + justify-self ---
        // ps0: place-self: auto → both auto
        _tests.add(new TestData("ps0", "align-self", AlignSelf.AUTO));
        _tests.add(new TestData("ps0", "justify-self", JustifySelf.AUTO));
        // ps1: place-self: start end → align=start, justify=end
        _tests.add(new TestData("ps1", "align-self", AlignSelf.START));
        _tests.add(new TestData("ps1", "justify-self", JustifySelf.END));
    }

    @Test
    public void test() {
        TestUtils.runTests(_tests, getClass().getResource("/simple/box-alignment.html"));
    }

}
