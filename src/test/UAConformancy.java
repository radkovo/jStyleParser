package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Element;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.NodeData;

public class UAConformancy {
	private static Logger log = Logger.getLogger(UAConformancy.class);

	private static Map<Element, NodeData> decl;
	
	@BeforeClass
	public static void init() throws FileNotFoundException {
		
		try {
		decl = CSSFactory.parse(new FileInputStream("data/invalid/style.css"), 
				new FileInputStream("data/invalid/style.html"), "screen", true);
		}
		catch(FileNotFoundException e) {
			log.error(e);
			throw e;
		}
	}
	
	@Test
	public void testNotEmptyness() {
		
		Assert.assertNotNull("Declarations parsed", decl);
		Assert.assertNotSame("There are some declarations", Collections.emptyMap(), decl);
		
		for(Element e: decl.keySet())
			log.debug(e.getNodeName() +  ": " + decl.get(e));
	}
	
}
