package cz.vutbr.web.csskit.antlr;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.fit.net.DataURLHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.MediaQuery;
import cz.vutbr.web.css.NetworkProcessor;
import cz.vutbr.web.css.RuleBlock;
import cz.vutbr.web.css.RuleList;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;

/**
 * Handles construction of parser
 * 
 * @author kapy
 * 
 */
public class CSSParserFactory {
	private static final Logger log = LoggerFactory
			.getLogger(CSSParserFactory.class);

	/**
	 * Source types.
	 */
	public static enum SourceType {
		INLINE,
		EMBEDDED,
		URL
	}
	
	/**
	 * Creates input for CSSLexer
	 * 
	 * @param source
	 *            Source, either raw data (String) or URL 
	 * @return Created stream
	 * @throws IOException
	 *             When file is not found or other IO exception occurs
	 */
	protected static CSSInputStream getInput(Object source, NetworkProcessor network, String encoding, SourceType type) throws IOException {
		switch (type) {
		case INLINE:
		case EMBEDDED:
			return CSSInputStream.stringStream((String) source);
		case URL:
			return CSSInputStream.urlStream((URL) source, network, encoding);
		default:
			throw new RuntimeException("Coding error");
		}
	}
	
	/**
	 * Creates AST tree for CSSTreeParser
	 * 
	 * @param parser
	 *            Source parser
	 * @return Created AST tree
	 * @throws CSSException
	 *             When unrecoverable exception during parse occurs.
	 *             RuntimeException are also encapsulated at this point
	 */
	private static CommonTree getAST(CSSParser parser, SourceType type) throws CSSException {
		switch (type) {
		case INLINE:
			try {
				CSSParser.inlinestyle_return retval = parser.inlinestyle();
				return (CommonTree) retval.getTree();
			} catch (RecognitionException re) {
				throw encapsulateException(re,
						"Unable to parse inline CSS style");
			} catch (RuntimeException re) {
				throw encapsulateException(re,
						"Unable to parse inline CSS style");
			}
		case EMBEDDED:
			try {
				CSSParser.stylesheet_return retval = parser.stylesheet();
				return (CommonTree) retval.getTree();
			} catch (RecognitionException re) {
				throw encapsulateException(re,
						"Unable to parse embedded CSS style");
			} catch (RuntimeException re) {
				throw encapsulateException(re,
						"Unable to parse embedded CSS style");
			}
		case URL:
			try {
				CSSParser.stylesheet_return retval = parser.stylesheet();
				return (CommonTree) retval.getTree();
			} catch (RecognitionException re) {
				throw encapsulateException(re,
						"Unable to parse URL CSS style");
			} catch (RuntimeException re) {
				throw encapsulateException(re,
						"Unable to parse URL CSS style");
			}
		default:
			throw new RuntimeException("Coding error");
		}
	}
	
	/**
	 * Creates StyleSheet from AST tree
	 * 
	 * @param parser
	 *            Parser
	 * @return Created StyleSheet
	 * @throws CSSException
	 *             When unrecoverable exception during parse occurs.
	 *             RuntimeException are also encapsulated at this point
	 */
	private static RuleList parse(CSSTreeParser parser, SourceType type) throws CSSException {
		switch (type) {
		case INLINE:
			try {
				return parser.inlinestyle();
			} catch (RecognitionException re) {
				throw encapsulateException(re,
						"Unable to parse inline CSS style [AST]");
			} catch (RuntimeException re) {
				throw encapsulateException(re,
						"Unable to parse inline CSS style [AST]");
			}
		case EMBEDDED:
			try {
				return parser.stylesheet();
			} catch (RecognitionException re) {
				throw encapsulateException(re,
						"Unable to parse embedded CSS style [AST]");
			} catch (RuntimeException re) {
				throw encapsulateException(re,
						"Unable to parse embedded CSS style [AST]");
			}
		case URL:
			try {
				return parser.stylesheet();
			} catch (RecognitionException re) {
				throw encapsulateException(re,
						"Unable to parse file CSS style [AST]");
			} catch (RuntimeException re) {
				throw encapsulateException(re,
						"Unable to parse file CSS style [AST]");
			}
		default:
			throw new RuntimeException("Coding error");
		}
	}
	
	/**
	 * Creates new CSSException which encapsulates cause
	 * 
	 * @param t
	 *            Cause
	 * @param msg
	 *            Message
	 * @return New CSSException
	 */
	protected static CSSException encapsulateException(Throwable t, String msg) {
		log.error("THROWN:", t);
		return new CSSException(msg, t);
	}

    //========================================================================================================================
	
	private static CSSParserFactory instance;
	
	protected CSSParserFactory() {}
	
	public static CSSParserFactory getInstance() {
		if(instance == null)
			instance = new CSSParserFactory();
		return instance;
	}

	/**
	 * Parses source of given type
	 * 
	 * @param source
	 *            Source, interpretation depends on {@code type}
	 * @param type
	 *            Type of source provided
	 * @param inline
	 *            InlineElement
     * @param inlinePriority
     *            True when the rule should have an 'inline' (greater) priority
	 * @return Created StyleSheet
	 * @throws IOException
	 *             When problem with input stream occurs
	 * @throws CSSException
	 *             When unrecoverable exception during parsing occurs
	 */
	public StyleSheet parse(Object source, NetworkProcessor network, String encoding, SourceType type,
			Element inline, boolean inlinePriority, URL base) throws IOException, CSSException {

		StyleSheet sheet = (StyleSheet) CSSFactory.getRuleFactory()
				.createStyleSheet().unlock();

		Preparator preparator = new SimplePreparator(inline, inlinePriority);
        StyleSheet ret = parseAndImport(source, network, encoding, type, sheet, preparator, base, null);
		return ret;
	}

	/**
	 * Parses source of given type. Uses no element.
	 * 
	 * @param source
	 *            Source, interpretation depends on {@code type}
	 * @param type
	 *            Type of source provided
	 * @param base
	 *            The base URL
	 * @return Created StyleSheet
	 * @throws IOException
	 *             When problem with input stream occurs
	 * @throws CSSException
	 *             When unrecoverable exception during parsing occurs
	 * @throws IllegalArgumentException
	 *             When type of source is INLINE
	 */
	public StyleSheet parse(Object source, NetworkProcessor network, String encoding, SourceType type, URL base)
			throws IOException, CSSException {
		if (type == SourceType.INLINE)
			throw new IllegalArgumentException(
					"Missing element for INLINE input");

		return parse(source, network, encoding, type, null, false, base);
	}

	/**
	 * Appends parsed source to passed style sheet. This style sheet must be
	 * IMPERATIVELY parsed by this factory to guarantee proper appending
	 * 
	 * @param source
	 *            Source, interpretation depends on {@code type}
	 * @param type
	 *            Type of source provided
	 * @param inline
	 *            Inline element
	 * @param inlinePriority
	 *            True when the rule should have an 'inline' (greater) priority
	 * @param sheet
	 *            StyleSheet to be modified
	 * @return Modified StyleSheet
	 * @throws IOException
	 *             When problem with input stream occurs
	 * @throws CSSException
	 *             When unrecoverable exception during parsing occurs
	 */
	public StyleSheet append(Object source, NetworkProcessor network, String encoding, SourceType type,
			Element inline, boolean inlinePriority, StyleSheet sheet, URL base) throws IOException, CSSException {

		Preparator preparator = new SimplePreparator(inline, inlinePriority);
		StyleSheet ret = parseAndImport(source, network, encoding, type, sheet, preparator, base, null);
		return ret;
	}

	/**
	 * Appends parsed source to passed style sheet. This style sheet must be
	 * IMPERATIVELY parsed by this factory to guarantee proper appending. Uses
	 * no inline element
	 * 
	 * @param source
	 *            Source, interpretation depends on {@code type}
	 * @param type
	 *            Type of source provided
	 * @param base
	 *            Base url
	 * @param sheet
	 *            StyleSheet to be modified
	 * @return Modified StyleSheet
	 * @throws IOException
	 *             When problem with input stream occurs
	 * @throws CSSException
	 *             When unrecoverable exception during parsing occurs
	 * @throws IllegalArgumentException
	 *             When type of source is INLINE
	 */
	public StyleSheet append(Object source, NetworkProcessor network, String encoding, SourceType type,
			StyleSheet sheet, URL base) throws IOException, CSSException {
		if (type == SourceType.INLINE)
			throw new IllegalArgumentException(
					"Missing element for INLINE input");

		return append(source, network, encoding, type, null, false, sheet, base);
	}
	
	/**
	 * Parses the source using the given infrastructure and returns the resulting style sheet.
	 * The imports are handled recursively.
	 */
	protected StyleSheet parseAndImport(Object source, NetworkProcessor network, String encoding, SourceType type,
	        StyleSheet sheet, Preparator preparator, URL base, List<MediaQuery> media)
	        throws CSSException, IOException
	{
        CSSTreeParser parser = createTreeParser(source, network, encoding, type, preparator, base, media);
        parse(parser, type);
        
        for (int i = 0; i < parser.getImportPaths().size(); i++)
        {
            String path = parser.getImportPaths().get(i);
            List<MediaQuery> imedia = parser.getImportMedia().get(i);
            
            if (((imedia == null || imedia.isEmpty()) && CSSFactory.getAutoImportMedia().matchesEmpty()) //no media query specified
                 || CSSFactory.getAutoImportMedia().matchesOneOf(imedia)) //or some media query matches to the autoload media spec
            {    
                URL url = DataURLHandler.createURL(base, path);
                try {
                    parseAndImport(url, network, encoding, SourceType.URL, sheet, preparator, url, imedia);
                } catch (IOException e) {
                    log.warn("Couldn't read imported style sheet: {}", e.getMessage());
                }
            }
            else
                log.trace("Skipping import {} (media not matching)", path);
        }

	    return addRulesToStyleSheet(parser.getRules(), sheet);
	}
	
	protected static StyleSheet addRulesToStyleSheet(RuleList rules, StyleSheet sheet) {
		if (rules != null)
		{
			for (RuleBlock<?> rule : rules)
			{
				sheet.add(rule);
			}
		}
		return sheet;
	}
	
	// creates the tree parser
	private static CSSTreeParser createTreeParser(Object source, NetworkProcessor network, String encoding, SourceType type,
			Preparator preparator, URL base, List<MediaQuery> media) throws IOException, CSSException {

		CSSInputStream input = getInput(source, network, encoding, type);
		input.setBase(base);
		CommonTokenStream tokens = feedLexer(input);
		CommonTree ast = feedParser(tokens, type);
		return feedAST(tokens, ast, preparator, media);
	}

	// initializer lexer
	private static CommonTokenStream feedLexer(CSSInputStream source)
	        throws CSSException 
	{
		// we have to unpack runtime exception
		// because of Java limitation
		// to change method contract with different type of exception
		try {
			CSSLexer lexer = new CSSLexer(source);
			lexer.init();
			return new CommonTokenStream(lexer);
		} catch (RuntimeException re) {
			if (re.getCause() instanceof CSSException) {
				throw (CSSException) re.getCause();
			}
			// this is some other exception
			else {
				log.error("LEXER THROWS:", re);
				throw re;
			}
		}
	}

	// Initializes parser
	private static CommonTree feedParser(CommonTokenStream source, SourceType type)
	        throws CSSException 
	{
		CSSParser parser = new CSSParser(source);
		parser.init();
		return getAST(parser, type);
	}

	// initializes tree parser
	private static CSSTreeParser feedAST(CommonTokenStream source, CommonTree ast, Preparator preparator, List<MediaQuery> media) 
	{
		if (log.isTraceEnabled()) {
			log.trace("Feeding tree parser with AST:\n{}", TreeUtil.toStringTree(ast));
		}
		// Walk resulting tree; create tree-node stream first
		CommonTreeNodeStream nodes = new CommonTreeNodeStream(ast);
		// AST nodes have payloads that point into token stream
		nodes.setTokenStream(source);
		CSSTreeParser parser = new CSSTreeParser(nodes);
		parser.init(preparator, media);
		return parser;
	}

    //========================================================================================================================
	
	/**
	 * Parses a media query from a string (e.g. the 'media' HTML attribute).
	 * @param query The query string
	 * @return List of media queries found.
	 */
	public List<MediaQuery> parseMediaQuery(String query)
	{
	    try
        {
	        //input from string
            CSSInputStream input = CSSInputStream.stringStream(query);
            input.setBase(new URL("file://media/query/url")); //this URL should not be used, just for safety
            //lexer
            CommonTokenStream tokens = feedLexer(input);
            //run parser - create AST
            CSSParser parser = new CSSParser(tokens);
            parser.init();
            CSSParser.media_return retval = parser.media();
            CommonTree ast = (CommonTree) retval.getTree();
            //tree parser
            CSSTreeParser tparser = feedAST(tokens, ast, null, null);
            return tparser.media();
        } catch (IOException e) {
            log.error("I/O error during media query parsing: {}", e.getMessage());
            return null;
        } catch (CSSException e) {
            log.warn("Malformed media query {}", query);
            return null;
        } catch (RecognitionException e) {
            log.warn("Malformed media query {}", query);
            return null;
        }
	}
	
}
