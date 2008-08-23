package cz.vutbr.web.css;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.tidy.Tidy;

import cz.vutbr.web.csskit.parser.CSSParser;
import cz.vutbr.web.domassign.Analyzer;

/**
 * This class is abstract factory for other factories used during CSS parsing.
 * Use it, for example, to retrieve current(default) TermFactory,
 * current(default) SupportedCSS implementation and so on.
 * 
 * Factories need to be registered first. This can be done using Java static
 * block initialization together with Java classloader.
 * 
 * By default, factory searches automatically for implementations:
 * <code>cz.vutbr.web.csskit.TermFactoryImpl</code>
 * <code>cz.vutbr.web.domassign.SupportedCSS21</code>
 * <code>cz.vutbr.web.csskit.RuleFactoryImpl</code>
 * <code>cz.vutbr.web.domassign.SingleMapNodeData</code>
 * 
 * Example:
 * 
 * <pre>
 * public class TermFactoryImpl implemenent TermFactory {
 * 		static {
 * 			CSSFactory.registerTermFactory(new TermFactoryImpl());
 * 		}
 * 		...
 * }
 * 
 * That, default factory is set when this class is loaded by class loader.
 * 
 * <pre>
 * Class.forName(&quot;xx.package.TermFactoryImpl&quot;)
 * </pre>
 * 
 * </pre>
 * 
 * @author kapy
 * 
 */
public final class CSSFactory {
	private static Logger log = Logger.getLogger(CSSFactory.class);
	
	private static final String DEFAULT_TERM_FACTORY = "cz.vutbr.web.csskit.TermFactoryImpl";
	private static final String DEFAULT_SUPPORTED_CSS = "cz.vutbr.web.domassign.SupportedCSS21";
	private static final String DEFAULT_RULE_FACTORY = "cz.vutbr.web.csskit.RuleFactoryImpl";
	private static final String DEFAULT_NODE_DATA_IMPL = "cz.vutbr.web.domassign.SingleMapNodeData";

	/**
	 * Default instance of TermFactory implementation
	 */
	private static TermFactory tf;

	/**
	 * Default instance of SupportedCSS implementation
	 */
	private static SupportedCSS css;

	/**
	 * Default instance of RuleFactory implementation
	 */
	private static RuleFactory rf;

	private static Class<? extends NodeData> ndImpl;

	/**
	 * Registers new TermFactory instance
	 * 
	 * @param newFactory
	 *            New TermFactory
	 */
	public static final void registerTermFactory(TermFactory newFactory) {
		tf = newFactory;
	}

	/**
	 * Returns TermFactory registered in step above
	 * 
	 * @return TermFactory registered
	 */
	public static final TermFactory getTermFactory() {
		if (tf == null) {
			try {
				Class.forName(DEFAULT_TERM_FACTORY);
				if (tf != null)
					return tf;
			} catch (Exception e) {
			}

			throw new RuntimeException(
					"No TermFactory implementation registered!");
		}
		return tf;
	}

	public static final void registerSupportedCSS(SupportedCSS newCSS) {
		css = newCSS;
	}

	public static final SupportedCSS getSupportedCSS() {
		if (css == null) {
			try {
				Class.forName(DEFAULT_SUPPORTED_CSS);
				if (css != null)
					return css;
			} catch (Exception e) {
			}

			throw new RuntimeException(
					"No SupportedCSS implementation registered!");
		}
		return css;
	}

	public static final void registerRuleFactory(RuleFactory newRuleFactory) {
		rf = newRuleFactory;
	}

	public static final RuleFactory getRuleFactory() {
		if (rf == null) {
			try {
				Class.forName(DEFAULT_RULE_FACTORY);
				if (rf != null)
					return rf;
			} catch (Exception e) {
			}

			throw new RuntimeException(
					"No RuleFactory implementation registered!");
		}

		return rf;
	}

	public static final void registerNodeDataInstance(
			Class<? extends NodeData> clazz) {
		try {
			@SuppressWarnings("unused")
			NodeData test = clazz.newInstance();
			ndImpl = clazz;
		} catch (InstantiationException e) {
			throw new RuntimeException("NodeData implemenation ("
					+ clazz.getName() + ") doesn't provide sole constructor", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("NodeData implementation ("
					+ clazz.getName() + ") is not accesible", e);
		}

	}

	@SuppressWarnings("unchecked")
	public static final NodeData createNodeData() {
		if(ndImpl==null) {
			try {
				registerNodeDataInstance((Class<? extends NodeData>)Class.forName(DEFAULT_NODE_DATA_IMPL));
			} catch(Exception e) {
			}
		}	
		
		try {
			return ndImpl.newInstance();
		}
		catch(Exception e) {
			throw new RuntimeException("No NodeData implementation registered");
		}
	}
	
	public static final Map<Element, NodeData> parse(InputStream stylesheet, InputStream html, String media, boolean inherit) {
		
		try {
			StyleSheet style = (new CSSParser(stylesheet)).parse();
			Tidy parser = new Tidy();
			parser.setCharEncoding(org.w3c.tidy.Configuration.UTF8);

			Document doc = parser.parseDOM(html, null);
			
			Analyzer analyzer = new Analyzer(style);
			return analyzer.evaluateDOM(doc, media, inherit);
		}
		catch(Exception e) {
			log.error("Unable to parse document", e);
			return Collections.emptyMap();
		}
		
	}

}
