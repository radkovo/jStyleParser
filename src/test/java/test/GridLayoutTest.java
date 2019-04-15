package test;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty.Grid;
import cz.vutbr.web.css.CSSProperty.GridAutoFlow;
import cz.vutbr.web.css.CSSProperty.GridAutoRowsColumns;
import cz.vutbr.web.css.CSSProperty.GridGap;
import cz.vutbr.web.css.CSSProperty.GridStartEnd;
import cz.vutbr.web.css.CSSProperty.GridTemplateAreas;
import cz.vutbr.web.css.CSSProperty.GridTemplateRowsColumns;
import cz.vutbr.web.css.Term.Operator;
import cz.vutbr.web.css.TermBracketedIdents;
import cz.vutbr.web.css.TermFactory;
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
public class GridLayoutTest {

    private static final TermFactory tf = CSSFactory.getTermFactory();
    private final List<TestData> _tests = new ArrayList<>();

    @Before
    public void prepare() {
        NameGenerator ng = new NameGenerator("grid");
        TermList list;
        TermList fnList;
        TermBracketedIdents bi;

        _tests.add(new TestData(ng.next(), "grid-template-areas", GridTemplateAreas.NONE));
        _tests.add(new TestData(ng.curr(), "grid-template-rows", GridTemplateRowsColumns.NONE));
        _tests.add(new TestData(ng.curr(), "grid-template-columns", GridTemplateRowsColumns.NONE));
        _tests.add(new TestData(ng.curr(), "grid", Grid.component_values));

        list = tf.createList();
        list.add(tf.createLength(1f, Unit.fr).setOperator(Operator.SLASH));
        _tests.add(new TestData(ng.next(), "grid-template-columns", GridTemplateRowsColumns.list_values, list));
        _tests.add(new TestData(ng.curr(), "grid-auto-flow", GridAutoFlow.ROW));
        _tests.add(new TestData(ng.curr(), "grid", Grid.component_values));

        list = tf.createList();
        list.add(tf.createLength(1f, Unit.fr).setOperator(Operator.SLASH));
        _tests.add(new TestData(ng.next(), "grid-template-columns", GridTemplateRowsColumns.list_values, list));
        list = tf.createList();
        list.add(tf.createIdent("row"));
        list.add(tf.createIdent("dense").setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.curr(), "grid-auto-flow", GridAutoFlow.component_values, list));
        _tests.add(new TestData(ng.curr(), "grid", Grid.component_values));

        list = tf.createList();
        list.add(tf.createLength(1f, Unit.px));
        list.add(tf.createLength(1f, Unit.px).setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.next(), "grid-template-rows", GridTemplateRowsColumns.list_values, list));
        _tests.add(new TestData(ng.curr(), "grid-auto-flow", GridAutoFlow.COLUMN));
        _tests.add(new TestData(ng.curr(), "grid", Grid.component_values));

        list = tf.createList();
        list.add(tf.createLength(1f, Unit.px));
        list.add(tf.createLength(1f, Unit.px).setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.next(), "grid-template-rows", GridTemplateRowsColumns.list_values, list));
        list = tf.createList();
        list.add(tf.createIdent("dense").setOperator(Operator.SLASH));
        list.add(tf.createIdent("column"));
        _tests.add(new TestData(ng.curr(), "grid-auto-flow", GridAutoFlow.component_values, list));
        _tests.add(new TestData(ng.curr(), "grid", Grid.component_values));

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
        _tests.add(new TestData(ng.curr(), "grid", Grid.component_values));

        list = tf.createList();
        list.add(tf.createString("a a a").setOperator(Operator.SPACE));
        list.add(tf.createString("b c c").setOperator(Operator.SPACE));
        list.add(tf.createString("b c c").setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.next(), "grid-template-areas", GridTemplateAreas.list_values, list));
        list = tf.createList();
        bi = tf.createBracketedIdents();
        bi.add(tf.createIdent("name"));
        list.add(bi);
        list.add(tf.createLength(1f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(1f, Unit.px).setOperator(Operator.SPACE));
        bi = tf.createBracketedIdents();
        bi.add(tf.createIdent("name"));
        list.add(bi.setOperator(Operator.SPACE));
        list.add(tf.createLength(1f, Unit.px).setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.curr(), "grid-template-rows", GridTemplateRowsColumns.list_values, list));
        list = tf.createList();
        list.add(tf.createLength(2f, Unit.px).setOperator(Operator.SLASH));
        bi = tf.createBracketedIdents();
        bi.add(tf.createIdent("name"));
        list.add(bi.setOperator(Operator.SPACE));
        list.add(tf.createLength(2f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(2f, Unit.px).setOperator(Operator.SPACE));
        bi = tf.createBracketedIdents();
        bi.add(tf.createIdent("name"));
        list.add(bi.setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.curr(), "grid-template-columns", GridTemplateRowsColumns.list_values, list));
        _tests.add(new TestData(ng.curr(), "grid", Grid.component_values));

        _tests.add(new TestData(ng.next(), "grid-template-areas", GridTemplateAreas.NONE));
        _tests.add(new TestData(ng.curr(), "grid-template-rows", GridTemplateRowsColumns.NONE));
        _tests.add(new TestData(ng.curr(), "grid-template-columns", GridTemplateRowsColumns.NONE));
        _tests.add(new TestData(ng.curr(), "grid", Grid.component_values));

        _tests.add(new TestData(ng.next(), "grid-template-areas", GridTemplateAreas.NONE));
        _tests.add(new TestData(ng.curr(), "grid-template-rows", GridTemplateRowsColumns.NONE));
        _tests.add(new TestData(ng.curr(), "grid-template-columns", GridTemplateRowsColumns.NONE));
        _tests.add(new TestData(ng.curr(), "grid", Grid.component_values));

        ng.setName("grid-area");
        _tests.add(new TestData(ng.next(), "grid-row-start", GridStartEnd.identificator, tf.createIdent("a")));
        _tests.add(new TestData(ng.curr(), "grid-column-start", GridStartEnd.identificator, tf.createIdent("a")));
        _tests.add(new TestData(ng.curr(), "grid-row-end", GridStartEnd.identificator, tf.createIdent("a")));
        _tests.add(new TestData(ng.curr(), "grid-column-end", GridStartEnd.identificator, tf.createIdent("a")));
        
        _tests.add(new TestData(ng.next(), "grid-row-start", GridStartEnd.identificator, tf.createIdent("a")));
        _tests.add(new TestData(ng.curr(), "grid-column-start", GridStartEnd.identificator, tf.createIdent("b")));
        _tests.add(new TestData(ng.curr(), "grid-row-end", GridStartEnd.identificator, tf.createIdent("a")));
        _tests.add(new TestData(ng.curr(), "grid-column-end", GridStartEnd.identificator, tf.createIdent("b")));
        
        _tests.add(new TestData(ng.next(), "grid-row-start", GridStartEnd.identificator, tf.createIdent("a")));
        _tests.add(new TestData(ng.curr(), "grid-column-start", GridStartEnd.identificator, tf.createIdent("b")));
        _tests.add(new TestData(ng.curr(), "grid-row-end", GridStartEnd.identificator, tf.createIdent("c")));
        _tests.add(new TestData(ng.curr(), "grid-column-end", GridStartEnd.identificator, tf.createIdent("b")));

        _tests.add(new TestData(ng.next(), "grid-row-start", GridStartEnd.identificator, tf.createIdent("a")));
        _tests.add(new TestData(ng.curr(), "grid-column-start", GridStartEnd.identificator, tf.createIdent("b")));
        _tests.add(new TestData(ng.curr(), "grid-row-end", GridStartEnd.identificator, tf.createIdent("c")));
        _tests.add(new TestData(ng.curr(), "grid-column-end", GridStartEnd.identificator, tf.createIdent("d")));
        
        _tests.add(new TestData(ng.next(), "grid-row-start", GridStartEnd.number, tf.createInteger(1)));
        _tests.add(new TestData(ng.curr(), "grid-column-start", GridStartEnd.AUTO));
        _tests.add(new TestData(ng.curr(), "grid-row-end", GridStartEnd.AUTO));
        _tests.add(new TestData(ng.curr(), "grid-column-end", GridStartEnd.AUTO));
        
        _tests.add(new TestData(ng.next(), "grid-row-start", GridStartEnd.number, tf.createInteger(1)));
        list = tf.createList();
        list.add(tf.createIdent("span").setOperator(Operator.SLASH));
        list.add(tf.createInteger(3).setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.curr(), "grid-column-start", GridStartEnd.component_values, list));
        _tests.add(new TestData(ng.curr(), "grid-row-end", GridStartEnd.AUTO));
        _tests.add(new TestData(ng.curr(), "grid-column-end", GridStartEnd.AUTO));
        
        _tests.add(new TestData(ng.next(), "grid-row-start", GridStartEnd.number, tf.createInteger(1)));
        _tests.add(new TestData(ng.curr(), "grid-column-start", GridStartEnd.AUTO));
        list = tf.createList();
        list.add(tf.createIdent("span").setOperator(Operator.SLASH));
        list.add(tf.createInteger(3).setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.curr(), "grid-row-end", GridStartEnd.component_values, list));
        _tests.add(new TestData(ng.curr(), "grid-column-end", GridStartEnd.AUTO));

        _tests.add(new TestData(ng.next(), "grid-row-start", GridStartEnd.AUTO));
        _tests.add(new TestData(ng.curr(), "grid-column-start", GridStartEnd.number, tf.createInteger(1)));
        list = tf.createList();
        list.add(tf.createIdent("span").setOperator(Operator.SLASH));
        list.add(tf.createIdent("IDENT").setOperator(Operator.SPACE));
        list.add(tf.createInteger(3).setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.curr(), "grid-row-end", GridStartEnd.component_values, list));
        list = tf.createList();
        list.add(tf.createIdent("span").setOperator(Operator.SLASH));
        list.add(tf.createInteger(3).setOperator(Operator.SPACE));
        list.add(tf.createIdent("IDENT").setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.curr(), "grid-column-end", GridStartEnd.component_values, list));

        _tests.add(new TestData(ng.next(), "grid-row-start", GridStartEnd.AUTO));
        _tests.add(new TestData(ng.curr(), "grid-column-start", GridStartEnd.AUTO));
        _tests.add(new TestData(ng.curr(), "grid-row-end", GridStartEnd.AUTO));
        _tests.add(new TestData(ng.curr(), "grid-column-end", GridStartEnd.AUTO));

        ng.setName("grid-row");
        _tests.add(new TestData(ng.next(), "grid-row-start", GridStartEnd.identificator, tf.createIdent("IDENT")));
        _tests.add(new TestData(ng.curr(), "grid-row-end", GridStartEnd.identificator, tf.createIdent("IDENT")));

        list = tf.createList();
        list.add(tf.createIdent("span"));
        list.add(tf.createIdent("IDENT").setOperator(Operator.SPACE));
        list.add(tf.createInteger(2).setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.next(), "grid-row-start", GridStartEnd.component_values, list));
        _tests.add(new TestData(ng.curr(), "grid-row-end", GridStartEnd.number, tf.createInteger(3)));

        _tests.add(new TestData(ng.next(), "grid-row-start", GridStartEnd.AUTO));
        _tests.add(new TestData(ng.curr(), "grid-row-end", GridStartEnd.AUTO));
        
        _tests.add(new TestData(ng.next(), "grid-row-start", GridStartEnd.AUTO));
        _tests.add(new TestData(ng.curr(), "grid-row-end", GridStartEnd.AUTO));

        ng.setName("grid-column");
        _tests.add(new TestData(ng.next(), "grid-column-start", GridStartEnd.identificator, tf.createIdent("IDENT")));
        _tests.add(new TestData(ng.curr(), "grid-column-end", GridStartEnd.identificator, tf.createIdent("IDENT")));

        list = tf.createList();
        list.add(tf.createIdent("span"));
        list.add(tf.createInteger(2).setOperator(Operator.SPACE));
        list.add(tf.createIdent("IDENT").setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.next(), "grid-column-start", GridStartEnd.component_values, list));
        _tests.add(new TestData(ng.curr(), "grid-column-end", GridStartEnd.number, tf.createInteger(3)));

        _tests.add(new TestData(ng.next(), "grid-column-start", GridStartEnd.AUTO));
        _tests.add(new TestData(ng.curr(), "grid-column-end", GridStartEnd.AUTO));
        
        _tests.add(new TestData(ng.next(), "grid-column-start", GridStartEnd.AUTO));
        _tests.add(new TestData(ng.curr(), "grid-column-end", GridStartEnd.AUTO));

        _tests.add(new TestData("grid-row-start", "grid-row-start", GridStartEnd.identificator, tf.createIdent("IDENT")));

        _tests.add(new TestData("grid-row-end", "grid-row-end", GridStartEnd.AUTO));

        _tests.add(new TestData("grid-column-start", "grid-column-start", GridStartEnd.number, tf.createInteger(1)));

        list = tf.createList();
        list.add(tf.createIdent("span"));
        list.add(tf.createInteger(2).setOperator(Operator.SPACE));
        _tests.add(new TestData("grid-column-end", "grid-column-end", GridStartEnd.component_values, list));

        ng.setName("grid-gap");
        _tests.add(new TestData(ng.next(), "grid-row-gap", GridGap.NORMAL));
        _tests.add(new TestData(ng.curr(), "grid-column-gap", GridGap.NORMAL));

        _tests.add(new TestData(ng.next(), "grid-row-gap", GridGap.length, tf.createLength(1f, Unit.px)));
        _tests.add(new TestData(ng.curr(), "grid-column-gap", GridGap.length, tf.createLength(1f, Unit.px)));

        _tests.add(new TestData(ng.next(), "grid-row-gap", GridGap.length, tf.createLength(1f, Unit.px)));
        _tests.add(new TestData(ng.curr(), "grid-column-gap", GridGap.length, tf.createLength(1f, Unit.px)));

        _tests.add(new TestData(ng.next(), "grid-row-gap", GridGap.length, tf.createLength(1f, Unit.px)));
        _tests.add(new TestData(ng.curr(), "grid-column-gap", GridGap.length, tf.createLength(2f, Unit.px)));

        _tests.add(new TestData(ng.next(), "grid-row-gap", GridGap.NORMAL));
        _tests.add(new TestData(ng.curr(), "grid-column-gap", GridGap.NORMAL));

        ng.setName("grid-row-gap");
        _tests.add(new TestData(ng.next(), "grid-row-gap", GridGap.NORMAL));

        _tests.add(new TestData(ng.next(), "grid-row-gap", GridGap.length, tf.createLength(1f, Unit.px)));

        _tests.add(new TestData(ng.next(), "grid-row-gap", GridGap.NORMAL));

        ng.setName("grid-column-gap");
        _tests.add(new TestData(ng.next(), "grid-column-gap", GridGap.NORMAL));

        _tests.add(new TestData(ng.next(), "grid-column-gap", GridGap.length, tf.createLength(1f, Unit.px)));

        _tests.add(new TestData(ng.next(), "grid-column-gap", GridGap.NORMAL));

        ng.setName("grid-template");
        _tests.add(new TestData(ng.next(), "grid-template-areas", GridTemplateAreas.NONE));
        _tests.add(new TestData(ng.curr(), "grid-template-rows", GridTemplateRowsColumns.NONE));
        _tests.add(new TestData(ng.curr(), "grid-template-columns", GridTemplateRowsColumns.NONE));

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
        bi = tf.createBracketedIdents();
        bi.add(tf.createIdent("name"));
        list.add(bi);
        list.add(tf.createLength(1f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(1f, Unit.px).setOperator(Operator.SPACE));
        bi = tf.createBracketedIdents();
        bi.add(tf.createIdent("name"));
        list.add(bi.setOperator(Operator.SPACE));
        list.add(tf.createLength(1f, Unit.px).setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.curr(), "grid-template-rows", GridTemplateRowsColumns.list_values, list));
        list = tf.createList();
        list.add(tf.createLength(2f, Unit.px).setOperator(Operator.SLASH));
        bi = tf.createBracketedIdents();
        bi.add(tf.createIdent("name"));
        list.add(bi.setOperator(Operator.SPACE));
        list.add(tf.createLength(2f, Unit.px).setOperator(Operator.SPACE));
        list.add(tf.createLength(2f, Unit.px).setOperator(Operator.SPACE));
        bi = tf.createBracketedIdents();
        bi.add(tf.createIdent("name"));
        list.add(bi.setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.curr(), "grid-template-columns", GridTemplateRowsColumns.list_values, list));

        _tests.add(new TestData(ng.next(), "grid-template-areas", GridTemplateAreas.NONE));
        _tests.add(new TestData(ng.curr(), "grid-template-rows", GridTemplateRowsColumns.NONE));
        _tests.add(new TestData(ng.curr(), "grid-template-columns", GridTemplateRowsColumns.NONE));

        ng.setName("grid-template-areas");
        _tests.add(new TestData(ng.next(), "grid-template-areas", GridTemplateAreas.NONE));

        list = tf.createList();
        list.add(tf.createString("a a a"));
        list.add(tf.createString("b c c").setOperator(Operator.SPACE));
        list.add(tf.createString("b c c").setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.next(), "grid-template-areas", GridTemplateAreas.list_values, list));

        _tests.add(new TestData(ng.next(), "grid-template-areas", GridTemplateAreas.NONE));

        ng.setName("grid-template-rows");
        _tests.add(new TestData(ng.next(), "grid-template-rows", GridTemplateRowsColumns.AUTO));

        list = tf.createList();
        fnList = tf.createList();
        fnList.add(tf.createLength(1f, Unit.px));
        fnList.add(tf.createOperator(','));
        fnList.add(tf.createIdent("max-content"));
        list.add(tf.createFunction("minmax", fnList));
        _tests.add(new TestData(ng.next(), "grid-template-rows", GridTemplateRowsColumns.list_values, list));

        list = tf.createList();
        bi = tf.createBracketedIdents();
        bi.add(tf.createIdent("name"));
        list.add(bi);
        list.add(tf.createLength(1f, Unit.fr).setOperator(Operator.SPACE));
        list.add(tf.createLength(2f, Unit.fr).setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.next(), "grid-template-rows", GridTemplateRowsColumns.list_values, list));

        list = tf.createList();
        fnList = tf.createList();
        fnList.add(tf.createLength(100f, Unit.px));
        fnList.add(tf.createOperator(','));
        fnList.add(tf.createIdent("min-content"));
        list.add(tf.createFunction("minmax", fnList));
        fnList = tf.createList();
        fnList.add(tf.createIdent("auto-fill"));
        fnList.add(tf.createOperator(','));
        fnList.add(tf.createLength(200f, Unit.px));
        list.add(tf.createFunction("repeat", fnList).setOperator(Operator.SPACE));
        list.add(tf.createPercent(20f).setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.next(), "grid-template-rows", GridTemplateRowsColumns.list_values, list));

        ng.setName("grid-template-columns");
        _tests.add(new TestData(ng.next(), "grid-template-columns", GridTemplateRowsColumns.AUTO));

        list = tf.createList();
        fnList = tf.createList();
        fnList.add(tf.createLength(1f, Unit.px));
        list.add(tf.createFunction("fit-content", fnList));
        _tests.add(new TestData(ng.next(), "grid-template-columns", GridTemplateRowsColumns.list_values, list));

        list = tf.createList();
        bi = tf.createBracketedIdents();
        bi.add(tf.createIdent("name"));
        list.add(bi);
        list.add(tf.createLength(1f, Unit.fr).setOperator(Operator.SPACE));
        list.add(tf.createLength(2f, Unit.fr).setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.next(), "grid-template-columns", GridTemplateRowsColumns.list_values, list));

        ng.setName("grid-auto-flow");
        _tests.add(new TestData(ng.next(), "grid-auto-flow", GridAutoFlow.COLUMN));

        list = tf.createList();
        list.add(tf.createIdent("column"));
        list.add(tf.createIdent("dense").setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.next(), "grid-auto-flow", GridAutoFlow.component_values, list));

        list = tf.createList();
        list.add(tf.createIdent("dense"));
        list.add(tf.createIdent("row").setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.next(), "grid-auto-flow", GridAutoFlow.component_values, list));

        _tests.add(new TestData(ng.next(), "grid-auto-flow", GridAutoFlow.ROW));

        _tests.add(new TestData(ng.next(), "grid-auto-flow", GridAutoFlow.ROW));

        ng.setName("grid-auto-rows");
        _tests.add(new TestData(ng.next(), "grid-auto-rows", GridAutoRowsColumns.MIN_CONTENT));

        list = tf.createList();
        list.add(tf.createPercent(10f));
        list.add(tf.createIdent("max-content").setOperator(Operator.SPACE));
        list.add(tf.createLength(1f, Unit.fr).setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.next(), "grid-auto-rows", GridAutoRowsColumns.list_values, list));

        _tests.add(new TestData(ng.next(), "grid-auto-rows", GridAutoRowsColumns.AUTO));

        _tests.add(new TestData(ng.next(), "grid-auto-rows", GridAutoRowsColumns.AUTO));

        ng.setName("grid-auto-columns");
        _tests.add(new TestData(ng.next(), "grid-auto-columns", GridAutoRowsColumns.MAX_CONTENT));

        list = tf.createList();
        list.add(tf.createPercent(20f));
        list.add(tf.createIdent("min-content").setOperator(Operator.SPACE));
        list.add(tf.createLength(2f, Unit.fr).setOperator(Operator.SPACE));
        _tests.add(new TestData(ng.next(), "grid-auto-columns", GridAutoRowsColumns.list_values, list));

        _tests.add(new TestData(ng.next(), "grid-auto-columns", GridAutoRowsColumns.AUTO));

        _tests.add(new TestData(ng.next(), "grid-auto-columns", GridAutoRowsColumns.AUTO));
    }

    @Test
    public void test() {
        TestUtils.runTests(_tests, getClass().getResource("/simple/grid-layout.html"));
    }

}
