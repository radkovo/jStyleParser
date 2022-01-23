package cz.vutbr.web.csskit.antlr;

import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import cz.vutbr.web.css.SourceLocator;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.Token;

/**
 * Token with encapsulation of LexerState during parse.
 * Models view at token text by removing syntactic sugar
 * from tokens with contains it,
 * e.g. STRING, URI, FUNCTION 
 * @author kapy
 *
 */
public class CSSToken extends CommonToken {

	/**
	 * Extended with EOF_TOKEN
	 */
	private static final long serialVersionUID = 3L;
	
	/**
	 * Current lexer state
	 */
	protected CSSLexerState ls;
	
	/** Source location */
	private final SourceLocator source;
	
	// token types
	public static final int FUNCTION = 1;
	public static final int URI = 2;
	public static final int STRING = 3;
	public static final int CLASSKEYWORD = 4;
	public static final int HASH = 5;
	
	private final TypeMapper typeMapper;
	
	/**
	 * Creates CSSToken, this is base {@code emit()} constructor
	 * @param input Input stream
	 * @param type Type of token
	 * @param channel Channel of token
	 * @param start Start position in stream
	 * @param stop End position in stream
	 */
	public CSSToken(CharStream input, int type, int channel, int start, int stop, Class<? extends Lexer> lexerClass, SourceLocator source) {
		super(input, type, channel, start, stop);
		typeMapper = new TypeMapper(CSSToken.class, lexerClass, "FUNCTION", "URI", "STRING", "CLASSKEYWORD", "HASH");
		this.source = source;
	}
	
	/**
	 * Creates CSSToken of given type with cloning lexer state
	 * automatically
	 * @param type Type of token
	 * @param state State of lexer, which will be copied
	 */
	public CSSToken(int type, CSSLexerState state, Class<? extends Lexer> lexerClass) {
		this(type, state, 0, 0, lexerClass);
	}
	
	/**
	 * Creates CSSToken of given type with cloning lexer state
	 * automatically, allows to set text boundaries in input stream
	 * @param type Type of token
	 * @param state State of lexer, which will be copied
	 * @param start Start position in stream
	 * @param stop End position in stream
	 */
	public CSSToken(int type, CSSLexerState state, int start, int stop, Class<? extends Lexer> lexerClass) {
		this(null, type, Token.DEFAULT_CHANNEL, start, stop, lexerClass, null);
		this.ls = new CSSLexerState(state);
	}

	/**
	 * Sets lexer state for current token
	 * @param state Current lexer state
	 * @return Modified CSSToken
	 */
	public CSSToken setLexerState(CSSLexerState state) {
		this.ls = state;
		return this;
	}

	/**
	 * Gets lexer state at creation of token
	 * @return the lexer state
	 */
	public CSSLexerState getLexerState() {
		return ls;	
	}

	/**
	 * Obtains the source location for resolving relative URLs and for use in messages
	 */
	public SourceLocator getSourceLocator() {
		return source;
	}

	/**
	 * Obtains the base URL for resolving relative URLs
	 */
	public URL getBase() {
		return source == null ? null : source.getURL();
	}

	/**
	 * Considers text as content of STRING token,
	 * and models view at this text as an common string,
	 * that is one character removed from the both beginning
	 * and the end.  
	 * @param string Content of STRING token 
	 * @return String with trimmed quotation marks
	 */
	public static String extractSTRING(String string) {
		return string.substring(1, string.length()-1);
	}

	/**
	 * Considers text as content of URI token,
	 * and models view at this text as an common string,
	 * that is removed {@code 'url('} from the beginning
	 * and {@code ')'} from the and. If result of this operation
	 * is STRING, remove even quotation marks
	 * @param uri Content of URI token
	 * @return String with trimmed URI syntax sugar and
	 * optionally quotation marks 
	 */
	public static String extractURI(String uri) {
		String ret = uri.substring(4, uri.length()-1);
		// trim string
		if(ret.length() > 0 && (ret.charAt(0)=='\'' || ret.charAt(0)=='"'))
			ret = ret.substring(1, ret.length()-1);
		
		return ret;
	}
	
	/**
	 * Considers text as content of FUNCTION token,
	 * and models view at this text as an common string,
	 * that is removed {@code '('} from the end of string
	 * @param function Content of FUNCTION token
	 * @return String with trimmed FUNCTION open parenthesis
	 */
	public static String extractFUNCTION(String function) {
		return function.substring(0, function.length()-1);
	}
	
	/**
	 * Considers text as content of HASH token,
	 * and models view at this text as an common string,
	 * that is removed {@code '#'} from the beginning of string
	 * @param hash Content of HASH token
	 * @return String with trimmed HASH # character
	 */
	public static String extractHASH(String hash) {
		return hash.substring(1,hash.length());
	}
	
	/**
	 * Considers text as content of CLASSKEYWORD token,
	 * and models view at this text as an common string,
	 * that is removed {@code '.'} from the beginning of string
	 * @param className Content of CLASSKEYWORD token
	 * @return String with trimmed CLASSKEYWORD dot
	 */
	public static String extractCLASSKEYWORD(String className) {
		return className.substring(1,className.length());
	}
	
	/**
	 * Returns common text stored in token. Content is not modified.
	 * @return Model view of text in token
	 */
	@Override
	public String getText() {
		
		// sets text from input if not text directly available
		text = super.getText();
		
		int t;
		try {
			t = typeMapper.inverse().get(type);
		} catch (NullPointerException e) {
			return text;
		}
		switch (t) {
			case FUNCTION:
				return text.substring(0, text.length()-1);
			case URI:
				return extractURI(text);
			case STRING:
				return extractSTRING(text);
			case CLASSKEYWORD:
				return extractCLASSKEYWORD(text);
			case HASH:
				return extractHASH(text);	
			default:
				return text;
		}
		
	}
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("/").append(ls).append("/")
    		.append(super.toString());
    	
    	return sb.toString();
    }
	
	/**
	 * Convert between type values defined in two classes.
	 */
	public static class TypeMapper {
		private final Map<Integer,Integer> map;
		private final TypeMapper inverse;
		private TypeMapper(Map<Integer,Integer> map, TypeMapper inverse) {
			this.map = map;
			this.inverse = inverse;
		}
		public TypeMapper(Class<?> classA, Class<?> classB, String... fieldNames) {
			map = new TreeMap<Integer,Integer>();
			Map<Integer,Integer> inverseMap = new TreeMap<Integer,Integer>();
			for (String f : fieldNames) {
				try {
					int a = classA.getField(f).getInt(null);
					int b = classB.getField(f).getInt(null);
					map.put(a, b);
					inverseMap.put(b, a);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (NoSuchFieldException e) {
					throw new RuntimeException(e);
				}
			}
			inverse = new TypeMapper(inverseMap, this);
		}
		public int get(int type) throws NullPointerException {
			return map.get(type);
		}
		public TypeMapper inverse() {
			return inverse;
		}
	}
}
