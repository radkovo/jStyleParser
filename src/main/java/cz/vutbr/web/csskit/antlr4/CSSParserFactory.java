package cz.vutbr.web.csskit.antlr4;

import cz.vutbr.web.css.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.fit.net.DataURLHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Handles construction of parser
 *
 * @author sedlakr
 */
public class CSSParserFactory {
    private static final Logger log = LoggerFactory.getLogger(CSSParserFactory.class);

    /**
     * Source types.
     * http://www.w3schools.com/css/css_howto.asp
     */
    public enum SourceType {
        INLINE, // example: <a style="color:red; margin:0 auto;">hyperlink</a>
        EMBEDDED, // example <style> a{ color:red;} i{font-style: italic} </style>
        URL // file example:<link rel="stylesheet" type="text/css" href="mystyle.css">
    }

    /**
     * singleton instance
     */
    private static CSSParserFactory instance;

    /**
     * dummy constructor for singleton
     */
    protected CSSParserFactory() {
    }

    /**
     * get instance singleton method
     *
     * @return CSS Parser Factory
     */
    public static CSSParserFactory getInstance() {
        if (instance == null)
            instance = new CSSParserFactory();
        return instance;
    }

    /**
     * Parses source of given type
     *
     * @param source         Source, interpretation depends on {@code type}
     * @param type           Type of source provided
     * @param inline         InlineElement
     * @param inlinePriority True when the rule should have an 'inline' (greater) priority
     * @return Created StyleSheet
     * @throws IOException  When problem with input stream occurs
     * @throws CSSException When unrecoverable exception during parsing occurs
     */
    public StyleSheet parse(Object source, NetworkProcessor network, String encoding, SourceType type,
                            Element inline, boolean inlinePriority, URL base) throws IOException, CSSException {

        StyleSheet sheet = (StyleSheet) CSSFactory.getRuleFactory()
                .createStyleSheet().unlock();

        Preparator preparator = new SimplePreparator(inline, inlinePriority);
        return parseAndImport(source, network, encoding, type, sheet, preparator, base, null);
    }

    /**
     * Parses source of given type. Uses no element.
     *
     * @param source Source, interpretation depends on {@code type}
     * @param type   Type of source provided
     * @param base   The base URL
     * @return Created StyleSheet
     * @throws IOException              When problem with input stream occurs
     * @throws CSSException             When unrecoverable exception during parsing occurs
     * @throws IllegalArgumentException When type of source is INLINE
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
     * @param source         Source, interpretation depends on {@code type}
     * @param type           Type of source provided
     * @param inline         Inline element
     * @param inlinePriority True when the rule should have an 'inline' (greater) priority
     * @param sheet          StyleSheet to be modified
     * @return Modified StyleSheet
     * @throws IOException  When problem with input stream occurs
     * @throws CSSException When unrecoverable exception during parsing occurs
     */

    public StyleSheet append(Object source, NetworkProcessor network, String encoding, SourceType type,
                             Element inline, boolean inlinePriority, StyleSheet sheet, URL base) throws IOException, CSSException {

        Preparator preparator = new SimplePreparator(inline, inlinePriority);
        return parseAndImport(source, network, encoding, type, sheet, preparator, base, null);
    }

    /**
     * Appends parsed source to passed style sheet. This style sheet must be
     * IMPERATIVELY parsed by this factory to guarantee proper appending. Uses
     * no inline element
     *
     * @param source Source, interpretation depends on {@code type}
     * @param type   Type of source provided
     * @param base   Base url
     * @param sheet  StyleSheet to be modified
     * @return Modified StyleSheet
     * @throws IOException              When problem with input stream occurs
     * @throws CSSException             When unrecoverable exception during parsing occurs
     * @throws IllegalArgumentException When type of source is INLINE
     */
    public StyleSheet append(Object source, NetworkProcessor network, String encoding, SourceType type,
                             StyleSheet sheet, URL base) throws IOException, CSSException {
        if (type == SourceType.INLINE)
            throw new IllegalArgumentException("Missing element for INLINE input");

        return append(source, network, encoding, type, null, false, sheet, base);
    }

    /**
     * Parses the source using the given infrastructure and returns the resulting style sheet.
     * The imports are handled recursively.
     */
    protected StyleSheet parseAndImport(Object source, NetworkProcessor network, String encoding, SourceType type,
                                        StyleSheet sheet, Preparator preparator, URL base, List<MediaQuery> media)
            throws CSSException, IOException {
        CSSParser parser = createParser(source, network, encoding, type, base);
        CSSParserListenerImpl extractor = parse(parser, type, preparator, media);
        
        if (extractor.getStyleSheetComment() != null) {
        	sheet.setComment(extractor.getStyleSheetComment());
        }
        
        sheet.setLocation(extractor.getStyleSheetLocation());
        
        String[] paths = source.toString().split("\\/");
        String name = paths[paths.length-1];
        
        sheet.setName(name);

        for (int i = 0; i < extractor.getImportPaths().size(); i++) {
            String path = extractor.getImportPaths().get(i);
            List<MediaQuery> imedia = extractor.getImportMedia().get(i);

            if (((imedia == null || imedia.isEmpty()) && CSSFactory.getAutoImportMedia().matchesEmpty()) //no media query specified
                    || CSSFactory.getAutoImportMedia().matchesOneOf(imedia)) //or some media query matches to the autoload media spec
            {
                URL url = DataURLHandler.createURL(base, path);
                try {
                    parseAndImport(url, network, encoding, SourceType.URL, sheet, preparator, url, imedia);
                } catch (IOException e) {
                    log.warn("Couldn't read imported style sheet: {}", e.getMessage());
                }
            } else
                log.trace("Skipping import {} (media not matching)", path);
        }

        return addRulesToStyleSheet(extractor.getRules(), sheet);
    }

    // creates the tree parser
    private static CSSParser createParser(Object source, NetworkProcessor network, String encoding, SourceType type,
                                          URL base) throws IOException, CSSException {

        CSSInputStream input = getInput(source, network, encoding, type);
        input.setBase(base);
        
        CSSLexer lexer = new CSSLexer(input);
        lexer.init();
//        ANTLRErrorListener lexErr = new CSSLexerErrorListener();
//        lexer.removeErrorListeners();
//        lexer.getErrorListeners();
        lexer.addErrorListener(CSSLexerErrorListener.INSTANCE);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CSSParser parser = new CSSParser(tokens);
        parser.addErrorListener(new CSSParserErrorListener());
        parser.setErrorHandler(new CSSErrorStrategy());
        return parser;

//        CommonTokenStream tokens = feedLexer(input);
//        CommonTree ast = feedParser(tokens, type);
//        return feedAST(tokens, ast, preparator, media);
    }

    private static CSSParserListenerImpl parse(CSSParser parser, SourceType type, Preparator preparator,
                                               List<MediaQuery> media) throws CSSException {
        ParserRuleContext tree;
        switch (type) {
            case INLINE:
                tree = parser.inlinestyle();
                break;
            case EMBEDDED:
//                try {
                tree = parser.stylesheet();
//                } catch (RuntimeException re) {
//                    throw encapsulateException(re,
//                            "Unable to parse embedded CSS style [AST]");
//                }
                break;
            case URL:
//                try {
                tree = parser.stylesheet();
//                } catch (RuntimeException re) {
//                    throw encapsulateException(re,
//                            "Unable to parse file CSS style [AST]");
//                }
                break;
            default:
                throw new RuntimeException("Coding error");
        }
        ParseTreeWalker walker = new ParseTreeWalker(); // create standard walker
        CSSParserListenerImpl extractor = new CSSParserListenerImpl(preparator, media);
        walker.walk(extractor, tree); // initiate walk of tree with listener
        return extractor;
    }


    /**
     * Parses a media query from a string (e.g. the 'media' HTML attribute).
     *
     * @param query The query string
     * @return List of media queries found.
     */
    public List<MediaQuery> parseMediaQuery(String query) {
        try {
            //input from string
            CSSInputStream input = CSSInputStream.stringStream(query);
            input.setBase(new URL("file://media/query/url")); //this URL should not be used, just for safety
            CSSLexer lexer = new CSSLexer(input);
            lexer.init();
            lexer.addErrorListener(CSSLexerErrorListener.INSTANCE);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            CSSParser parser = new CSSParser(tokens);
            parser.addErrorListener(new CSSParserErrorListener());
            parser.setErrorHandler(new CSSErrorStrategy());
            ParseTreeWalker walker = new ParseTreeWalker(); // create standard walker
            CSSParserListenerImpl extractor = new CSSParserListenerImpl();
            ParserRuleContext tree = parser.media();
            walker.walk(extractor, tree); // initiate walk of tree with listener
            return extractor.getMedia();
        } catch (IOException e) {
            log.error("I/O error during media query parsing: {}", e.getMessage());
            return null;
        }/* catch (CSSException e) {
            log.warn("Malformed media query {}", query);
            return null;
        } */catch (RecognitionException e) {
            log.warn("Malformed media query {}", query);
            return null;
        }
    }


    /**
     * Creates new CSSException which encapsulates cause
     *
     * @param t   Cause
     * @param msg Message
     * @return New CSSException
     */
    protected static CSSException encapsulateException(Throwable t, String msg) {
        log.error("THROWN:", t);
        return new CSSException(msg, t);
    }

    protected static StyleSheet addRulesToStyleSheet(RuleList rules, StyleSheet sheet) {
        if (rules != null) {
            for (RuleBlock<?> rule : rules) {
                sheet.add(rule);
            }
        }
        return sheet;
    }

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
}