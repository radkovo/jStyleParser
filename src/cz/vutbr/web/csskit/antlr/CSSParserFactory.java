package cz.vutbr.web.csskit.antlr;

import java.io.IOException;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.StyleSheet;

public class CSSParserFactory {
	private static final Logger log = LoggerFactory
			.getLogger(CSSParserFactory.class);

	public static enum SourceType {
		INLINE {
			@Override
			public CommonTree getAST(CSSParser parser) throws CSSException {
				try {
					CSSParser.inlinestyle_return retval = parser.inlinestyle();
					return (CommonTree) retval.getTree();
				} catch (RecognitionException re) {
					throw encapsulateException(re, "Unable to parse inline CSS style");
				} catch (RuntimeException re) {
					throw encapsulateException(re, "Unable to parse inline CSS style");
				}
			}

			@Override
			public StyleSheet parse(CSSTreeParser parser) throws CSSException {
				try {
					return parser.inlinestyle();
				} catch (RecognitionException re) {
					throw encapsulateException(re, "Unable to parse inline CSS style [AST]");
				} catch (RuntimeException re) {
					throw encapsulateException(re, "Unable to parse inline CSS style [AST]");
				}
			}

			@Override
			public CSSInputStream getInput(String source) throws IOException {
				return CSSInputStream.stringStream(source);
			}

		},
		EMBEDDED {
			@Override
			public CommonTree getAST(CSSParser parser) throws CSSException {
				try {
					CSSParser.stylesheet_return retval = parser.stylesheet();
					return (CommonTree) retval.getTree();
				} catch (RecognitionException re) {
					throw encapsulateException(re, "Unable to parse embedded CSS style");
				} catch (RuntimeException re) {
					throw encapsulateException(re, "Unable to parse embedded CSS style");
				}
			}

			@Override
			public StyleSheet parse(CSSTreeParser parser) throws CSSException {
				try {
					return parser.stylesheet();
				} catch (RecognitionException re) {
					throw encapsulateException(re, "Unable to parse embedded CSS style [AST]");
				} catch (RuntimeException re) {
					throw encapsulateException(re, "Unable to parse embedded CSS style [AST]");
				}
			}

			@Override
			public CSSInputStream getInput(String source) throws IOException {
				return CSSInputStream.stringStream(source);
			}
		},
		FILE {
			@Override
			public CommonTree getAST(CSSParser parser) throws CSSException {
				try {
					CSSParser.stylesheet_return retval = parser.stylesheet();
					return (CommonTree) retval.getTree();
				} catch (RecognitionException re) {
					throw encapsulateException(re, "Unable to parse file CSS style");
				} catch (RuntimeException re) {
					throw encapsulateException(re, "Unable to parse file CSS style");
				}
			}

			@Override
			public StyleSheet parse(CSSTreeParser parser) throws CSSException {
				try {
					return parser.stylesheet();
				} catch (RecognitionException re) {
					throw encapsulateException(re, "Unable to parse file CSS style [AST]");
				} catch (RuntimeException re) {
					throw encapsulateException(re, "Unable to parse file CSS style [AST]");
				}
			}

			@Override
			public CSSInputStream getInput(String source) throws IOException {
				return CSSInputStream.fileStream(source);
			}

		};

		public abstract CSSInputStream getInput(String source)
				throws IOException;

		public abstract CommonTree getAST(CSSParser parser) throws CSSException;

		public abstract StyleSheet parse(CSSTreeParser parser)
				throws CSSException;
		
		public static CSSException encapsulateException(Throwable t, String msg) throws CSSException {
			log.error("THROWN:", t);
			return new CSSException(msg, t);
		}
	}

	// disable instantiation
	private CSSParserFactory() {
		throw new AssertionError();
	}

	public static StyleSheet parse(String source, SourceType type)
			throws IOException, CSSException {

		StyleSheet sheet = (StyleSheet) CSSFactory.getRuleFactory()
				.createStyleSheet().unlock();
		CSSTreeParser parser = createParser(source, type, sheet);
		return type.parse(parser);	
	}
	
	/**
	 * FIXME rule block numbering
	 * @param source
	 * @param type
	 * @param sheet
	 * @return
	 * @throws CSSException 
	 * @throws IOException 
	 */
	public static StyleSheet append(String source, SourceType type, StyleSheet sheet) throws IOException, CSSException {
		CSSTreeParser parser = createParser(source, type, sheet);
		
		return type.parse(parser);
	}

	private static CSSTreeParser createParser(String source, SourceType type,
			StyleSheet stylesheet) throws IOException, CSSException {

		CSSInputStream input = type.getInput(source);
		CommonTokenStream tokens = feedLexer(input, stylesheet);
		CommonTree ast = feedParser(tokens, type, stylesheet);
		return feedAST(tokens, ast, stylesheet);
	}

	private static CommonTokenStream feedLexer(CSSInputStream source,
			StyleSheet stylesheet) throws CSSException {

		// we have to unpack runtime exception
		// because of Java limitation
		// to change method contract with different type of exception
		try {
			CSSLexer lexer = new CSSLexer(source);
			lexer.init(stylesheet);
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

	private static CommonTree feedParser(CommonTokenStream source,
			SourceType type, StyleSheet stylesheet) throws CSSException {

		CSSParser parser = new CSSParser(source);
		parser.init(stylesheet);
		return type.getAST(parser);
	}

	private static CSSTreeParser feedAST(CommonTokenStream source,
			CommonTree ast, StyleSheet stylesheet) {

		if (log.isTraceEnabled()) {
			log.trace("Feeding tree parser with AST:\n{}", TreeUtil
					.toStringTree(ast));
		}

		// Walk resulting tree; create tree-node stream first
		CommonTreeNodeStream nodes = new CommonTreeNodeStream(ast);

		// AST nodes have payloads that point into token stream
		nodes.setTokenStream(source);

		CSSTreeParser parser = new CSSTreeParser(nodes);

		return parser.init(stylesheet);
	}

}
