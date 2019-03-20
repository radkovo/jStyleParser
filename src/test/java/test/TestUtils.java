
package test;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.domassign.StyleMap;
import java.net.URL;
import java.util.List;
import org.junit.Assert;
import org.w3c.dom.Document;

/**
 *
 * @author Petr Mikulík
 */
public class TestUtils {
    
    public static void runTests(Iterable<TestData> tests, URL testedSource) {
        try {
            DOMSource ds = new DOMSource(testedSource.openStream());
            Document doc = ds.parse();
            StyleMap sm = CSSFactory.assignDOM(doc, null, testedSource, "screen", true);

            ElementMap elements = new ElementMap(doc);
            NodeData data;
            CSSProperty prop;
            Term<?> term;

            for (TestData test : tests) {
                System.out.print("Testing #" + test._id + " {" + test._propertyName + ": ");
                String id = test._id;
                data = sm.get(elements.getElementById(id));
                prop = data.getSpecifiedProperty(test._propertyName);
                
                Assert.assertEquals(test._expextedProperty, prop);
                if (test._expectedValue != null) {
                    System.out.print(test._expectedValue);
                    term = data.getSpecifiedValue(test._propertyName);
                    if (!test._expectedValue.equals(term)) {
                        if (test._expectedValue instanceof TermList && term instanceof TermList) {
                            compareLists((TermList) test._expectedValue, (TermList) term);
                        }
                    }
                    Assert.assertEquals(test._expectedValue, term);
                } else {
                    System.out.print(test._expextedProperty);
                }
                System.out.println("}");
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static void compareLists(List a, List b) {
        if (a.size() != b.size()) {
            System.err.println("Length");
        }
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i))) {
                System.err.println("\n" + i + ". item doesnt match");
                break;
            }
        }
    }

    public static class TestData {

        private final String _id;
        private final String _propertyName;
        private final CSSProperty _expextedProperty;
        private final Object _expectedValue;

        public TestData(String id, String propertyName, CSSProperty expextedProperty) {
            this(id, propertyName, expextedProperty, null);
        }

        public TestData(String id, String propertyName, CSSProperty expextedProperty, Object expectedValue) {
            _id = id;
            _propertyName = propertyName;
            _expextedProperty = expextedProperty;
            _expectedValue = expectedValue;
        }

    }

    public static class NameGenerator {

        private String _className;
        private int _iteration = -1;

        public NameGenerator(String className) {
            _className = className;
        }

        public void setName(String className) {
            _className = className;
            _iteration = -1;
        }

        public String curr() {
            return _className + Integer.toString(_iteration);
        }

        public String next() {
            _iteration++;
            return curr();
        }
    }
    
}
