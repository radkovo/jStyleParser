package cz.vutbr.web.css;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.traversal.NodeFilter;

import cz.vutbr.web.csskit.antlr.CSSParserFactory;
import cz.vutbr.web.csskit.antlr.CSSParserFactory.SourceType;
import cz.vutbr.web.domassign.Analyzer;
import cz.vutbr.web.domassign.StyleMap;
import cz.vutbr.web.domassign.Traversal;

/**
 * This class is abstract factory for other factories used during CSS parsing.
 * Use it, for example, to retrieve current(default) TermFactory,
 * current(default) SupportedCSS implementation and so on.
 * 
 * Factories need to be registered first. By default, CSSFactory uses
 * automatically for implementations:
 * <code>cz.vutbr.web.csskit.TermFactoryImpl</code>
 * <code>cz.vutbr.web.domassign.SupportedCSS21</code>
 * <code>cz.vutbr.web.csskit.RuleFactoryImpl</code>
 * <code>cz.vutbr.web.domassign.SingleMapNodeData</code>
 * 
 * Other usage of this factory is to parse either string or file into
 * StyleSheet.
 * 
 * Whole conversion between DOM tree and mapping of CSS to DOM tree elements
 * could be done by invoking method {@code assignDOM()}
 * 
 * @author kapy
 * 
 */
public final class CSSFactory {
	private static Logger log = LoggerFactory.getLogger(CSSFactory.class);

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
				@SuppressWarnings("unchecked")
				Class<? extends TermFactory> clazz = (Class<? extends TermFactory>) Class
						.forName(DEFAULT_TERM_FACTORY);
				Method m = clazz.getMethod("getInstance");
				registerTermFactory((TermFactory) m.invoke(null));
				log.debug("Retrived {} as default TermFactory implementation.",
						DEFAULT_TERM_FACTORY);
			} catch (Exception e) {
				log.error("Unable to get TermFactory from default", e);
				throw new RuntimeException(
						"No TermFactory implementation registered!");
			}
		}
		return tf;
	}

	/**
	 * Registers new SupportedCSS instance
	 * 
	 * @param newCSS
	 *            new SupportedCSS
	 */
	public static final void registerSupportedCSS(SupportedCSS newCSS) {
		css = newCSS;
	}

	/**
	 * Returns registered SupportedCSS
	 * 
	 * @return SupportedCSS instance
	 */
	public static final SupportedCSS getSupportedCSS() {
		if (css == null) {
			try {
				@SuppressWarnings("unchecked")
				Class<? extends SupportedCSS> clazz = (Class<? extends SupportedCSS>) Class
						.forName(DEFAULT_SUPPORTED_CSS);
				Method m = clazz.getMethod("getInstance");
				registerSupportedCSS((SupportedCSS) m.invoke(null));
				log.debug(
						"Retrived {} as default SupportedCSS implementation.",
						DEFAULT_SUPPORTED_CSS);
			} catch (Exception e) {
				log.error("Unable to get SupportedCSS from default", e);
				throw new RuntimeException(
						"No SupportedCSS implementation registered!");
			}
		}
		return css;
	}

	/**
	 * Registers new RuleFactory
	 * 
	 * @param newRuleFactory
	 *            New RuleFactory instance
	 */
	public static final void registerRuleFactory(RuleFactory newRuleFactory) {
		rf = newRuleFactory;
	}

	/**
	 * Returns registered RuleFactory
	 * 
	 * @return RuleFactory instance
	 */
	public static final RuleFactory getRuleFactory() {
		if (rf == null) {
			try {
				@SuppressWarnings("unchecked")
				Class<? extends RuleFactory> clazz = (Class<? extends RuleFactory>) Class
						.forName(DEFAULT_RULE_FACTORY);
				Method m = clazz.getMethod("getInstance");
				registerRuleFactory((RuleFactory) m.invoke(null));
				log.debug("Retrived {} as default RuleFactory implementation.",
						DEFAULT_RULE_FACTORY);
			} catch (Exception e) {
				log.error("Unable to get RuleFactory from default", e);
				throw new RuntimeException(
						"No RuleFactory implementation registered!");
			}
		}

		return rf;
	}

	/**
	 * Registers node data instance. Instance must provide no-argument
	 * Constructor
	 * 
	 * @param clazz
	 *            Instance class
	 */
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

	/**
	 * Creates instance of NodeData
	 * 
	 * @return Instance of NodeData
	 */
	public static final NodeData createNodeData() {
		if (ndImpl == null) {
			try {
				@SuppressWarnings("unchecked")
				Class<? extends NodeData> clazz = (Class<? extends NodeData>) Class
						.forName(DEFAULT_NODE_DATA_IMPL);
				registerNodeDataInstance(clazz);
				log.debug("Registered {} as default NodeData instance.",
						DEFAULT_NODE_DATA_IMPL);
			} catch (Exception e) {
			}
		}

		try {
			return ndImpl.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("No NodeData implementation registered");
		}
	}

	/**
	 * Parses URL into StyleSheet
	 * 
	 * @param url
	 *            URL of file to be parsed
	 * @param encoding
	 *            Encoding of file
	 * @return Parsed StyleSheet
	 * @throws CSSException
	 *             When exception during parse occurs
	 * @throws IOException
	 *             When file not found
	 */
	public static final StyleSheet parse(URL url, String encoding)
			throws CSSException, IOException {
		return CSSParserFactory.parse((Object) url, encoding, SourceType.URL, url);
	}

	/**
	 * Parses file into StyleSheet. Internally transforms file to URL
	 * @param fileName Name of file
	 * @param encoding Encoding used to parse input
	 * @return Parsed style sheet
	 * @throws CSSException In case that parsing error occurs 
	 * @throws IOException If file is not found or not readable
	 */
	public static final StyleSheet parse(String fileName, String encoding)
			throws CSSException, IOException {

		try {
			File f = new File(fileName);
			URL url = f.toURI().toURL();
			return parse(url, encoding);
		} catch (MalformedURLException e) {
			String message = "Unable to construct URL from fileName: "
					+ fileName;
			log.error(message);
			throw new FileNotFoundException(message);
		}
	}

	/**
	 * Parses text into StyleSheet
	 * 
	 * @param css
	 *            Text with CSS declarations
	 * @return Parsed StyleSheet
	 * @throws IOException
	 *             When exception during read occurs
	 * @throws CSSException
	 *             When exception during parse occurs
	 */
	public static final StyleSheet parse(String css) throws IOException,
			CSSException {
		return CSSParserFactory.parse(css, null, SourceType.EMBEDDED, null);
	}

    /**
     * Loads all the style sheets used from the specified DOM tree.
     * The following style specifications are evaluated:
     * <ul>
     * <li>The style sheets included using the <code>link</code> and <code>style</code> tags.
     * <li>Inline styles specified using the <code>style</code> element attribute.
     * <li><strong>Proprietary extension:</strong> Default styles defined using the <code>XDefaultStyle</code>
     *     element attribute. These styles behave the same way as the inline styles but they have the lowest priority
     *     (the values are used only when not redefined by any other way)
     *  </ul>
     *  <b>This method does not allow specifying the default style sheet character encoding. The current system
     *  encoding is used by default. It is recommended to use the {@link #getUsedStyles(Document, String, URL, String)}
     *  method in order to specify the encoding correctly.</b>
     * 
     * @param doc
     *            DOM tree
     * @param base
     *            Base URL against which all files are searched
     * @param media
     *            Selected media for style sheet
     * @return the rules of all the style sheets used in the document including the inline styles
     */
    public static final StyleSheet getUsedStyles(Document doc, URL base, String media)
    {
        return getUsedStyles(doc, null, base, media);
    }
    
    /**
     * Loads all the style sheets used from the specified DOM tree.
     * The following style specifications are evaluated:
     * <ul>
     * <li>The style sheets included using the <code>link</code> and <code>style</code> tags.
     * <li>Inline styles specified using the <code>style</code> element attribute.
     * <li><strong>Proprietary extension:</strong> Default styles defined using the <code>XDefaultStyle</code>
     *     element attribute. These styles behave the same way as the inline styles but they have the lowest priority
     *     (the values are used only when not redefined by any other way)
     *  </ul>   
     * 
     * @param doc
     *            DOM tree
     * @param encoding
     *            The default encoding used for the referenced style sheets
     * @param base
     *            Base URL against which all files are searched
     * @param media
     *            Selected media for style sheet
     * @return the rules of all the style sheets used in the document including the inline styles
     */
    public static final StyleSheet getUsedStyles(Document doc, String encoding, URL base, String media)
    {
        Pair pair = new Pair(base, media);

        Traversal<StyleSheet> traversal = new CSSAssignTraversal(doc, encoding,
                (Object) pair, NodeFilter.SHOW_ELEMENT);

        StyleSheet style = (StyleSheet) getRuleFactory().createStyleSheet().unlock();
        traversal.listTraversal(style);
        return style;
    }
    
    /**
     * Goes through a DOM tree and assigns the CSS declarations to the DOM elements.
     * The following style specifications are evaluated:
     * <ul>
     * <li>The style sheets included using the <code>link</code> and <code>style</code> tags.
     * <li>Inline styles specified using the <code>style</code> element attribute.
     * <li><strong>Proprietary extension:</strong> Default styles defined using the <code>XDefaultStyle</code>
     *     element attribute. These styles behave the same way as the inline styles but they have the lowest priority
     *     (the values are used only when not redefined by any other way)
     *  </ul>   
     *  <b>This method does not allow specifying the default style sheet character encoding. The current system
     *  encoding is used by default. It is recommended to use the {@link #assignDOM(Document, String, URL, String, boolean)}
     *  method in order to specify the encoding correctly.</b>
     * 
     * @param doc
     *            DOM tree
     * @param base
     *            Base URL against which all files are searched
     * @param media
     *            Selected media for style sheet
     * @param useInheritance
     *            Whether inheritance will be used to determine values
     * @return Map between DOM element nodes and data structure containing CSS
     *         information
     */
    public static final StyleMap assignDOM(Document doc,
            URL base, String media, boolean useInheritance) {
        return assignDOM(doc, null, base, media, useInheritance);
    }
    
	/**
	 * Goes through a DOM tree and assigns the CSS declarations to the DOM elements.
	 * The following style specifications are evaluated:
	 * <ul>
	 * <li>The style sheets included using the <code>link</code> and <code>style</code> tags.
	 * <li>Inline styles specified using the <code>style</code> element attribute.
	 * <li><strong>Proprietary extension:</strong> Default styles defined using the <code>XDefaultStyle</code>
	 *     element attribute. These styles behave the same way as the inline styles but they have the lowest priority
	 *     (the values are used only when not redefined by any other way)
	 *  </ul>   
	 * 
	 * @param doc
	 *            DOM tree
     * @param encoding
     *            The default encoding used for the referenced style sheets
	 * @param base
	 *            Base URL against which all files are searched
	 * @param media
	 *            Selected media for style sheet
	 * @param useInheritance
	 *            Whether inheritance will be used to determine values
	 * @return Map between DOM element nodes and data structure containing CSS
	 *         information
	 */
	public static final StyleMap assignDOM(Document doc, String encoding,
			URL base, String media, boolean useInheritance) {

		Pair pair = new Pair(base, media);

		Traversal<StyleSheet> traversal = new CSSAssignTraversal(doc, encoding,
				(Object) pair, NodeFilter.SHOW_ELEMENT);

		StyleSheet style = (StyleSheet) getRuleFactory().createStyleSheet()
				.unlock();
		traversal.listTraversal(style);

		Analyzer analyzer = new Analyzer(style);
		return analyzer.evaluateDOM(doc, media, useInheritance);
	}

	// ========================================================================

	/**
	 * Walks (X)HTML document and collects style information
	 * 
	 * @author kapy
	 * 
	 */
	private static final class CSSAssignTraversal extends Traversal<StyleSheet> {

	    private String encoding;
	    
		public CSSAssignTraversal(Document doc, String encoding, Object source, int whatToShow) {
			super(doc, source, whatToShow);
			this.encoding = encoding;
		}

		@Override
		protected void processNode(StyleSheet result, Node current,
				Object source) {

			// base uri
			URL base = ((Pair) source).base;
			// allowed media
			String media = ((Pair) source).media;
			Element elem = (Element) current;

			try {
				// embedded style-sheet
				if (isEmbeddedStyleSheet(elem, media)) {
					result = CSSParserFactory.append(extractElementText(elem), null,
							SourceType.EMBEDDED, result, base);
					log.debug("Matched embedded CSS style");
				}
				// linked style-sheet
				else if (isLinkedStyleSheet(elem, media)) {
					URL uri = new URL(base, elem.getAttribute("href"));
					result = CSSParserFactory.append(uri, encoding, SourceType.URL,
							result, uri);
					log.debug("Matched linked CSS style");
				}
				// in-line style and default style
				else {
    				    if (elem.getAttribute("style") != null && elem.getAttribute("style").length() > 0) {
        					result = CSSParserFactory.append(
        							elem.getAttribute("style"), null, SourceType.INLINE,
        							elem, true, result, base);
        					log.debug("Matched inline CSS style");
    				    }
                        if (elem.getAttribute("XDefaultStyle") != null && elem.getAttribute("XDefaultStyle").length() > 0) {
                            result = CSSParserFactory.append(
                                    elem.getAttribute("XDefaultStyle"), null, SourceType.INLINE,
                                    elem, false, result, base);
                            log.debug("Matched default CSS style");
                        }
				}
			} catch (CSSException ce) {
				log.error("THROWN:", ce);
			} catch (IOException ioe) {
				log.error("THROWN:", ioe);
			}

		}

		private static boolean isEmbeddedStyleSheet(Element e, String media) {
			return "style".equalsIgnoreCase(e.getNodeName())
					&& isAllowedMedia(e, media);
		}

		private static boolean isLinkedStyleSheet(Element e, String media) {
			return e.getNodeName().equals("link")
					&& "stylesheet".equalsIgnoreCase(e.getAttribute("rel"))
					&& (e.getAttribute("type").isEmpty() || "text/css".equalsIgnoreCase(e.getAttribute("type")))
					&& isAllowedMedia(e, media);
		}

		/**
		 * Extracts element's text, if any
		 * 
		 * @param e
		 *            Element
		 * @return Element's text or {@code null}
		 */
		private static String extractElementText(Element e) {
			Node text = e.getFirstChild();
			if (text != null && text.getNodeType() == Node.TEXT_NODE)
				return ((Text) text).getData();
			return null;
		}

		/**
		 * Checks allowed media by searching for {@code media} attribute on
		 * element and its content
		 * 
		 * @param e
		 *            (STYLE) Element
		 * @param media
		 *            Media used for parsing
		 * @return {@code true} if allowed, {@code false} otherwise
		 */
		private static boolean isAllowedMedia(Element e, String media) {
			String mediaList = e.getAttribute("media");
			if (mediaList == null || mediaList.length() == 0
				|| mediaList.contains(media) || mediaList.contains("all"))
				return true;

			return false;
		}
	}

	// holds pair with URL base and allowed media
	private static final class Pair {
		public URL base;
		public String media;

		public Pair(URL base, String media) {
			this.base = base;
			this.media = media;
		}
	}

}
