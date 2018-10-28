package test;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.domassign.StyleMap;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.w3c.dom.Document;

/**
 *
 * @author Petr Mikulík <dexterdd77@gmail.com>
 */
public class BoxShadowTest {

	private static final String PROPERTY_NAME = "box-shadow";

	private static final String[] NON_VALUE = new String[]{
		"none",
		"inherit",
		"initial",
		"unset"
	};
	
	private static final String[] VALID = new String[]{
		"minimal",
		"blur",
		"spread",
		"color",
		"blur_color",
		"spread_color",
		"inset",
		"inset_blur",
		"inset_spread",
		"inset_color",
		"inset_blur_color",
		"inset_spread_color"
	};

	private static final String[] INVALID = new String[]{
		"invalid_0",
		"invalid_1",
		"invalid_2",
		"invalid_3"
	};

	@Test
	public void boxShadowTest() {

		try {
			DOMSource ds = new DOMSource(getClass().getResourceAsStream("/simple/box-shadow.html"));
			Document doc = ds.parse();
			StyleMap sm = CSSFactory.assignDOM(doc, null, getClass().getResource("/simple/box-shadow.html"), "screen", true);

			ElementMap elements = new ElementMap(doc);
			NodeData data;
			CSSProperty prop;
			Term<?> term;

			for (String nonValue : NON_VALUE) {
				data = sm.get(elements.getElementById(nonValue));
				assertNotNull("Data for #" + nonValue + " should exist", data);

				prop = data.getProperty(PROPERTY_NAME, true);
				assertNotNull("Property for #" + nonValue + " should exist", prop);
				System.out.println("prop class = " + prop.getClass().getSimpleName());
			}
			
			for (String valid : VALID) {
				data = sm.get(elements.getElementById(valid));
				assertNotNull("Data for #" + valid + " should exist", data);

				prop = data.getProperty(PROPERTY_NAME, true);
				assertNotNull("Property for #" + valid + " should exist", prop);
				System.out.println("prop class = " + prop.getClass().getSimpleName());

				term = data.getValue(PROPERTY_NAME, true);
				assertNotNull("Term for #" + valid + " should exist", term);
				System.out.println("term to string = " + term);
			}
			
			for (String valid : INVALID) {
				data = sm.get(elements.getElementById(valid));
				assertNotNull("Data for #" + valid + " should exist", data);

				prop = data.getProperty(PROPERTY_NAME, true);
				assertNull("Property for #" + valid + " should not exist", prop);

				term = data.getValue(PROPERTY_NAME, true);
				assertNull("Term for #" + valid + " should not exist", term);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
