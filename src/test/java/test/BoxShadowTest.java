package test;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.domassign.StyleMap;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.w3c.dom.Document;

/**
 *
 * @author Petr Mikulík <dexterdd77@gmail.com>
 */
public class BoxShadowTest {

	@Test
	public void boxShadow() {

		try {
			DOMSource ds = new DOMSource(getClass().getResourceAsStream("/box-shadow.html"));
			Document doc = ds.parse();
			StyleMap sm = CSSFactory.assignDOM(doc, null, getClass().getResource("/box-shadow.html"), "screen", true);

			ElementMap elements = new ElementMap(doc);

			NodeData data = sm.get(elements.getElementById("test"));
			assertNotNull("Data for #test exist", data);

			String propertyName = "box-shadow";

			CSSProperty prop = data.getProperty(propertyName, true);
			assertNotNull("Property", prop);
			System.out.println("prop class = " + prop.getClass().getSimpleName());
			System.out.println("prop to string = " + prop);

			Term<?> term = data.getValue(propertyName, true);
			assertNotNull("Term", term);
			System.out.println("term class " + term.getClass().getSimpleName());
			System.out.println("term to string = " + term);
			System.out.println("");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
