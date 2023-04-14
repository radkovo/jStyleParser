package cz.vutbr.web.css;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.function.Function;

import org.fit.net.DataURLHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.traversal.NodeFilter;

import cz.vutbr.web.csskit.ElementUtil;
import cz.vutbr.web.csskit.MatchConditionImpl;
import cz.vutbr.web.csskit.antlr.CSSParserFactory;
import cz.vutbr.web.csskit.antlr.CSSSource;
import cz.vutbr.web.csskit.antlr.CSSSourceReader;
import cz.vutbr.web.csskit.antlr.DefaultCSSSourceReader;
import cz.vutbr.web.domassign.Analyzer;
import cz.vutbr.web.domassign.DeclarationTransformer;
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
 * <code>cz.vutbr.web.domassign.SupportedCSS3</code>
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
	private static final String DEFAULT_SUPPORTED_CSS = "cz.vutbr.web.domassign.SupportedCSS3";
	private static final String DEFAULT_RULE_FACTORY = "cz.vutbr.web.csskit.RuleFactoryImpl";
    private static final String DEFAULT_DECLARATION_TRANSFORMER = "cz.vutbr.web.domassign.DeclarationTransformer";
	private static final String DEFAULT_NODE_DATA_IMPL = "cz.vutbr.web.domassign.SingleMapNodeData";

	/**
	 * Default instance of CSSParcerFactory
	 */
	private static CSSParserFactory pf;
	
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

	/**
	 * Default instance of DeclarationTransformer
	 */
	private static DeclarationTransformer dt;
	
	/**
	 * Used NodeData class
	 */
	private static Class<? extends NodeData> ndImpl;

	/**
	 * Default match condition
	 */
	private static MatchCondition dcond;
	
	/**
	 * Whether to allow lengths with no units and interpret them as pixels.
	 */
	private static boolean implyPixelLengths = false;
	
	
	/**
	 * Media specification used for automatically importing style sheets. 
	 */
	private static MediaSpec autoImportMedia = null;
	
	/**
	 * Sets whether to allow lengths with no units and interpret them as pixels. The default value is {@code false}.
	 * @param b {@code true} when the lengths with no units should be allowed.
	 */
	public static final void setImplyPixelLength(boolean b) {
	    implyPixelLengths = b;
	}
	
    /**
     * Says whether to allow lengths with no units and interpret them as pixels.
     * @return {@code true} if the lengths with no units are allowed
     */
    public static final boolean getImplyPixelLength() {
        return implyPixelLengths;
    }

    /**
     * Obtains the media specification used for automatical style import. The parser will
     * automatically download the style sheets imported using the {@code @import} rules
     * when they corespond to the given media specification.
     * @return The media specification.
     */
    public static MediaSpec getAutoImportMedia()
    {
        if (autoImportMedia == null)
            autoImportMedia = new MediaSpecAll();
        return autoImportMedia;
    }

    /**
     * Sets the media specification used for automatical style import. The parser will
     * automatically download the style sheets imported using the {@code @import} rules
     * when they corespond to the given media specification. The default is "all" i.e.
     * all the imported style sheets are automatically parsed.
     * 
     * @param autoImportMedia The media specification for checking the imports.
     */
    public static void setAutoImportMedia(MediaSpec autoImportMedia)
    {
        CSSFactory.autoImportMedia = autoImportMedia;
    }
	
	/**
	 * Registers new CSSParserFactory instance
	 *
	 * @param factory
	 *            New CSSParserFactory
	 */
	public static final void registerCSSParserFactory(CSSParserFactory factory) {
		pf = factory;
	}

	/**
	 * Returns CSSParserFactory registered in step above
	 *
	 * @return CSSParserFactory registered
	 */
	private static final CSSParserFactory getCSSParserFactory() {
		if (pf == null)
			pf = CSSParserFactory.getInstance();
		return pf;
	}
	
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
     * Registers new DeclarationTransformer
     * 
     * @param newDeclarationTransformer
     *            New DeclarationTransformer instance
     */
    public static final void registerDeclarationTransformer(DeclarationTransformer newDeclarationTransformer) {
        dt = newDeclarationTransformer;
    }

    /**
     * Returns the registered DeclarationTransformer
     * 
     * @return DeclarationTransformer instance
     */
    public static final DeclarationTransformer getDeclarationTransformer() {
        if (dt == null) {
            try {
                @SuppressWarnings("unchecked")
                Class<? extends DeclarationTransformer> clazz = (Class<? extends DeclarationTransformer>) Class
                        .forName(DEFAULT_DECLARATION_TRANSFORMER);
                Method m = clazz.getMethod("getInstance");
                registerDeclarationTransformer((DeclarationTransformer) m.invoke(null));
                log.debug("Retrived {} as default DeclarationTransformer implementation.",
                        DEFAULT_DECLARATION_TRANSFORMER);
            } catch (Exception e) {
                log.error("Unable to get DeclarationTransformer from default", e);
                throw new RuntimeException(
                        "No DeclarationTransformer implementation registered!");
            }
        }

        return dt;
    }
    
    /**
     * Registers a new default match condition to be used for matching the elements and selector parts.
     * @param newMatchCondition the new match condition
     */
    public static final void registerDefaultMatchCondition(MatchCondition newMatchCondition)
    {
        dcond = newMatchCondition;
    }
    
    /**
     * Obtains the default match condition to be used for matching the elements and selector parts.
     * @return the default match condition used by this factory.
     */
    public static final MatchCondition getDefaultMatchCondition() {
        if (dcond == null)
            dcond = new MatchConditionImpl(); //use the default match condition when nothing is registered
        return dcond;
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
		return createNodeData(getDeclarationTransformer(), getSupportedCSS());
	}
	
	public static final NodeData createNodeData(DeclarationTransformer transformer, SupportedCSS css) {
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
			return ndImpl.getDeclaredConstructor(DeclarationTransformer.class, SupportedCSS.class)
			             .newInstance(transformer, css);
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
	public static final StyleSheet parse(URL url, Charset encoding)
			throws CSSException, IOException {
		return getCSSParserFactory().parse(new CSSSource(url, encoding, (String)null), new DefaultCSSSourceReader());
	}

    /**
     * Parses URL into StyleSheet
     * 
     * @param url
     *            URL of file to be parsed
     * @param cssReader
     * @param encoding
     *            Encoding of file
     * @return Parsed StyleSheet
     * @throws CSSException
     *             When exception during parse occurs
     * @throws IOException
     *             When file not found
     */
    public static final StyleSheet parse(URL url, CSSSourceReader cssReader, Charset encoding)
            throws CSSException, IOException {
        return getCSSParserFactory().parse(new CSSSource(url, encoding, (String)null), cssReader);
    }

	/**
	 * Parses file into StyleSheet. Internally transforms file to URL
	 * @param fileName Name of file
	 * @param encoding Encoding used to parse input
	 * @return Parsed style sheet
	 * @throws CSSException In case that parsing error occurs 
	 * @throws IOException If file is not found or not readable
	 */
	public static final StyleSheet parse(String fileName, Charset encoding)
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
	 * @deprecated
	 *         This function does not specify the base URL. {@link #parseString(String, URL)} 
	 *         should be used instead. 
	 */
	@Deprecated
	public static final StyleSheet parse(String css) throws IOException,
			CSSException {
		return parseString(css, null);
	}

    /**
     * Parses text into a StyleSheet
     * 
     * @param css
     *            Text with CSS declarations
     * @param base
     *            The URL to be used as a base for loading external resources. Base URL may
     *            be {@code null} if there are no external resources in the CSS string
     *            referenced by relative URLs. 
     * @return Parsed StyleSheet
     * @throws IOException
     *             When exception during read occurs
     * @throws CSSException
     *             When exception during parse occurs
     */
	public static final StyleSheet parseString(String css, URL base) throws IOException,
            CSSException {
	    return parseString(css, base, new DefaultCSSSourceReader());
    }
    
    /**
     * Parses text into a StyleSheet
     * 
     * @param css
     *            Text with CSS declarations
     * @param base
     *            The URL to be used as a base for loading external resources. Base URL may
     *            be {@code null} if there are no external resources in the CSS string
     *            referenced by relative URLs.
     * @param cssReader
     * @return Parsed StyleSheet
     * @throws IOException
     *             When exception during read occurs
     * @throws CSSException
     *             When exception during parse occurs
     */
    public static final StyleSheet parseString(String css, URL base, CSSSourceReader cssReader) throws IOException,
            CSSException {
        if (base == null)
            base = new URL("file:///base/url/is/not/specified"); //prevent errors if there are still some relative URLs used
        return getCSSParserFactory().parse(new CSSSource(css, (String)null, base, 0, 0), cssReader);
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
     * @param style
     *            Style sheet to be modified
     * @return the rules of all the style sheets used in the document including the inline styles
     */
    public static final StyleSheet getUsedStyles(Document doc, Charset encoding, URL base, MediaSpec media, CSSSourceReader cssReader, StyleSheet style)
    {
        return getUsedStyles(
            doc,
            encoding,
            node -> new SourceLocator() {
                    public URL getURL() {
                        return base;
                    }
                    public int getLineNumber() {
                        return 0;
                    }
                    public int getColumnNumber() {
                        return 0;
                    }
                },
            media,
            cssReader,
            style);
    }

    /**
     * @param nodeLocator
     *            Function to get a node's location (base URL, line number and column number)
     */
    public static final StyleSheet getUsedStyles(Document doc, Charset encoding, Function<Node,SourceLocator> nodeLocator,
                                                 MediaSpec media, CSSSourceReader cssReader, StyleSheet style)
    {
        URL base = nodeLocator.apply(doc).getURL();
        SourceData pair = new SourceData(base, cssReader, media);

        Traversal<StyleSheet> traversal = new CSSAssignTraversal(doc, encoding,
                (Object) pair, NodeFilter.SHOW_ELEMENT, nodeLocator);

        traversal.listTraversal(style);
        style = style.filter(media);
        return style;
    }
    
    /**
     * This is the same as {@link CSSFactory#getUsedStyles(Document, String, URL, MediaSpec, CSSSourceReader, Stylesheet)}
     * but a new StyleSheet is created instead of appending to one.
     * 
     * @param doc
     *            DOM tree
     * @param encoding
     *            The default encoding used for the referenced style sheets
     * @param base
     *            Base URL against which all files are searched
     * @param media
     *            Selected media for style sheet
     * @param cssReader
     * @return the rules of all the style sheets used in the document including the inline styles
     */
	public static final StyleSheet getUsedStyles(Document doc, Charset encoding, URL base, MediaSpec media, CSSSourceReader cssReader)
    {
        StyleSheet style = (StyleSheet) getRuleFactory().createStyleSheet().unlock();
        return getUsedStyles(doc, encoding, base, media, cssReader, style);
    }
    
    /**
     * This is the same as {@link CSSFactory#getUsedStyles(Document, String, URL, MediaSpec)} without
     * the possibility of specifying a custom CSSSourceReader.
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
    public static final StyleSheet getUsedStyles(Document doc, Charset encoding, URL base, MediaSpec media)
    {
        return getUsedStyles(doc, encoding, base, media, new DefaultCSSSourceReader());
    }
    
    /**
     * This is the same as {@link CSSFactory#getUsedStyles(Document, String, URL, MediaSpec)} but only the
     * media type is provided instead of the complete media specification.
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
    public static final StyleSheet getUsedStyles(Document doc, Charset encoding, URL base, String media)
    {
        return getUsedStyles(doc, encoding, base, new MediaSpec(media), new DefaultCSSSourceReader());
    }
    
    @Deprecated
    public static final StyleSheet getUsedStyles(Document doc, URL base, String media)
    {
        return getUsedStyles(doc, null, base, media);
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
	 *            Current media specification used for evaluating the media queries 
	 * @param useInheritance
	 *            Whether inheritance will be used to determine values
	 * @return Map between DOM element nodes and data structure containing CSS
	 *         information
	 */
	public static final StyleMap assignDOM(Document doc, Charset encoding,
			URL base, MediaSpec media, boolean useInheritance) {
		return assignDOM(doc, encoding, base, media, useInheritance, null);
	}

    /**
     * This is the same as {@link CSSFactory#assignDOM(Document, String, URL, MediaSpec, boolean)} but only the
     * media type is provided instead of the complete media specification.
     *
     * @param doc
     *            DOM tree
     * @param encoding
     *            The default encoding used for the referenced style sheets
     * @param base
     *            Base URL against which all files are searched
     * @param media
     *            Selected media type for style sheet
     * @param useInheritance
     *            Whether inheritance will be used to determine values
     * @return Map between DOM element nodes and data structure containing CSS
     *         information
     */
    public static final StyleMap assignDOM(Document doc, Charset encoding,
            URL base, String media, boolean useInheritance) {
        return assignDOM(doc, encoding, base, new MediaSpec(media), useInheritance);
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
     *            Current media specification used for evaluating the media queries
     * @param useInheritance
     *            Whether inheritance will be used to determine values
     * @param matchCond
     *            The match condition to match the against.
     * @return Map between DOM element nodes and data structure containing CSS
     *         information
     */
	public static final StyleMap assignDOM(Document doc, Charset encoding,
			URL base, MediaSpec media, boolean useInheritance, final MatchCondition matchCond) {
	    
	    return assignDOM(doc, encoding, new DefaultCSSSourceReader(),
	            base, media, useInheritance, matchCond);
	}

    /**
     * This is the same as {@link CSSFactory#assignDOM(Document, String, URL, MediaSpec, boolean)} 
     * with the possibility of specifying a custom CSSSourceReader.
     * 
     * @param doc
     *            DOM tree
     * @param encoding
     *            The default encoding used for the referenced style sheets
     * @param cssReader
     * @param base
     *            Base URL against which all files are searched
     * @param media
     *            Current media specification used for evaluating the media queries
     * @param useInheritance
     *            Whether inheritance will be used to determine values
     * @param matchCond
     *            The match condition to match the against.
     * @return Map between DOM element nodes and data structure containing CSS
     *         information
     */ 
    public static final StyleMap assignDOM(Document doc, Charset encoding, CSSSourceReader cssReader,
            URL base, MediaSpec media, boolean useInheritance, final MatchCondition matchCond) {

        StyleSheet style = (StyleSheet) getRuleFactory().createStyleSheet()
                .unlock();
        style = getUsedStyles(doc, encoding, base, media, cssReader, style);

        Analyzer analyzer = new Analyzer(style);
        if (matchCond != null) {
            analyzer.registerMatchCondition(matchCond);
        }
        return analyzer.evaluateDOM(doc, media, useInheritance);
    }
    
    /**
     * This is the same as {@link CSSFactory#assignDOM(Document, String, URL, MediaSpec, boolean)} but only the
     * media type is provided instead of the complete media specification.
     * 
     * @param doc
     *            DOM tree
     * @param encoding
     *            The default encoding used for the referenced style sheets
     * @param base
     *            Base URL against which all files are searched
     * @param media
     *            Selected media type for style sheet
     * @param useInheritance
     *            Whether inheritance will be used to determine values
     * @param matchCond
     *            The match condition to match the against.
     * @return Map between DOM element nodes and data structure containing CSS
     *         information
     */
    public static final StyleMap assignDOM(Document doc, Charset encoding,
            URL base, String media, boolean useInheritance, final MatchCondition matchCond) {
        
        return assignDOM(doc, encoding, base, new MediaSpec(media), useInheritance, matchCond);
    }

    @Deprecated
    public static final StyleMap assignDOM(Document doc,
            URL base, String media, boolean useInheritance) {
        return assignDOM(doc, null, base, media, useInheritance);
    }
    
	// ========================================================================

	/**
	 * Walks (X)HTML document and collects style information
	 * 
	 * @author kapy
	 * 
	 */
	private static final class CSSAssignTraversal extends Traversal<StyleSheet> {

		private CSSParserFactory pf = getCSSParserFactory();
	    private Charset encoding;
		private final Function<Node,SourceLocator> nodeLocator;
	    
		public CSSAssignTraversal(Document doc, Charset encoding, Object source, int whatToShow, Function<Node,SourceLocator> nodeLocator) {
			super(doc, source, whatToShow);
			this.encoding = encoding;
			this.nodeLocator = nodeLocator;
		}

		@Override
		protected void processNode(StyleSheet result, Node current,	Object source) 
		{
			// base uri
			URL base = ((SourceData) source).base;
			// allowed media
			MediaSpec media = ((SourceData) source).media;
			CSSSourceReader cssReader = ((SourceData) source).cssReader;
			Element elem = (Element) current;

			try {
				// embedded style-sheet
				if (isEmbeddedStyleSheet(elem, media)) {
					SourceLocator loc = nodeLocator.apply(elem);
					String mediaType = getMediaType(elem);
					if (cssReader.supportsMediaType(mediaType, null)) {
						result = pf.append(
							new CSSSource(extractElementText(elem), mediaType, base, loc.getLineNumber(), loc.getColumnNumber()),
							cssReader,
							result);
						log.debug("Matched embedded CSS style");
					}
				}
				// linked style-sheet
				else if (isLinkedStyleSheet(elem, media)) {
				    URL uri = DataURLHandler.createURL(base, ElementUtil.getAttribute(elem, "href"));
					String mediaType = getMediaType(elem);
					if (cssReader.supportsMediaType(mediaType, uri)) {
						result = pf.append(
							new CSSSource(uri, encoding, getMediaType(elem)),
							cssReader,
							result);
						log.debug("Matched linked CSS style");
					}
				}
				// in-line style and default style
				else {
					if (cssReader.supportsMediaType(null, null)) {
    				    if (elem.getAttribute("style") != null && elem.getAttribute("style").length() > 0) {
        					result = pf.append(
        						new CSSSource(elem.getAttribute("style"), elem, base),
        						cssReader,
        						true,
        						result);
        					log.debug("Matched inline CSS style");
    				    }
					}
                        if (elem.getAttribute("XDefaultStyle") != null && elem.getAttribute("XDefaultStyle").length() > 0) {
                            result = pf.append(
                            		new CSSSource(elem.getAttribute("XDefaultStyle"), elem, base),
                                 cssReader,
                                 false,
                                 result);
                            log.debug("Matched default CSS style");
                        }
				}
			} catch (CSSException ce) {
				log.error(ce.getMessage(), ce);
			} catch (IOException ioe) {
				log.error(ioe.getMessage(), ioe);
			}

		}

		private boolean isEmbeddedStyleSheet(Element e, MediaSpec media) {
			return "style".equalsIgnoreCase(e.getNodeName())
					&& isAllowedMedia(e, media);
		}

		private boolean isLinkedStyleSheet(Element e, MediaSpec media) {
			return e.getNodeName().equalsIgnoreCase("link")
			        && (ElementUtil.getAttribute(e, "rel").toLowerCase().contains("stylesheet"))
					&& isAllowedMedia(e, media);
		}

		/*
		 * @param e style or link element
		 */
		private String getMediaType(Element e) {
			String type = ElementUtil.getAttribute(e, "type");
			if (type != null) {
				if (type.isEmpty())
					return null;
				return type.toLowerCase();
			}
			return null;
		}

		/**
		 * Extracts element's text, if any
		 * 
		 * @param e
		 *            Element
		 * @return Element's text
		 */
		private static String extractElementText(Element e) {
			StringBuilder s = new StringBuilder();
			NodeList children = e.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				Node text = children.item(i);
				if (text != null) {
					if (text.getNodeType() == Node.TEXT_NODE
					    || text.getNodeType() == Node.CDATA_SECTION_NODE)
						s.append(((CharacterData)text).getData());
					else if (text.getNodeType() == Node.COMMENT_NODE)
						s.append("<!--").append(((CharacterData)text).getData()).append("-->");
				}
			}
			return s.toString();
		}

		/**
		 * Checks allowed media by searching for {@code media} attribute on
		 * element and its content
		 * 
		 * @param e
		 *            (STYLE) Element
		 * @param media
		 *            Current media specification used for parsing
		 * @return {@code true} if allowed, {@code false} otherwise
		 */
		private boolean isAllowedMedia(Element e, MediaSpec media) {
		    String attr = ElementUtil.getAttribute(e, "media");
		    if (attr != null && attr.length() > 0)
		    {
		        attr = attr.trim();
		        if (attr.length() > 0)
		        {
		            List<MediaQuery> ql = pf.parseMediaQuery(attr);
		            if (ql != null)
		                return media.matches(ql);
		            else
		                return false; //no usable media queries (malformed string?)
		        }
		        else
		            return media.matchesEmpty(); //empty query string
		    }
		    else
		        return media.matchesEmpty(); //no media queries
		}
	}

	// holds source description containing the URL base, CSSSourceReader and the required media
	private static final class SourceData {
		public URL base;
		public CSSSourceReader cssReader;
		public MediaSpec media;

		public SourceData(URL base, CSSSourceReader cssReader, MediaSpec media) {
			this.base = base;
			this.cssReader = cssReader;
			this.media = media;
		}
	}

}
