package cz.vutbr.web.csskit.antlr;

import java.net.URL;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;

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
	protected CSSLexer.LexerState ls;
	
	/** Base URL for URIs */
	protected URL base;
	
	/**
	 * Creates CSSToken, this is base {@code emit()} constructor
	 * @param input Input stream
	 * @param type Type of token
	 * @param channel Channel of token
	 * @param start Start position in stream
	 * @param stop End position in stream
	 */
	public CSSToken(CharStream input, int type, int channel, int start, int stop) {
		super(input, type, channel, start, stop);
	}
	
	/**
	 * Creates CSSToken of given type with cloning lexer state
	 * automatically
	 * @param type Type of token
	 * @param state State of lexer, which will be copied
	 */
	public CSSToken(int type, CSSLexer.LexerState state) {
		super(type);
		this.ls = new CSSLexer.LexerState(state);
	}
	
	/**
	 * Creates CSSToken of given type with cloning lexer state
	 * automatically, allows to set text boundaries in input stream
	 * @param type Type of token
	 * @param state State of lexer, which will be copied
	 * @param start Start position in stream
	 * @param stop End position in stream
	 */
	public CSSToken(int type, CSSLexer.LexerState state, int start, int stop) {
		super(type);
		this.ls = new CSSLexer.LexerState(state);
		this.start = start;
		this.stop = stop;
	}

	/**
	 * Sets lexer state for current token
	 * @param state Current lexer state
	 * @return Modified CSSToken
	 */
	public CSSToken setLexerState(CSSLexer.LexerState state) {
		this.ls = state;
		return this;
	}

	/**
	 * Gets lexer state at creation of token
	 * @return the lexer state
	 */
	public CSSLexer.LexerState getLexerState() {
		return ls;	
	}

	/**
	 * Obtains the base URL used for the relative URIs
	 * @return the URL or null if not set
	 */
	public URL getBase()
    {
        return base;
    }

	/**
	 * Sets the base URL used for the relative URIs
	 * @param base the base URL to set
	 */
    public void setBase(URL base)
    {
        this.base = base;
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
		
		switch(type) {
			case CSSLexer.FUNCTION:
				return text.substring(0, text.length()-1);
			case CSSLexer.URI:
				return extractURI(text);
			case CSSLexer.STRING:
				return extractSTRING(text);
			case CSSLexer.CLASSKEYWORD:
				return extractCLASSKEYWORD(text);
			case CSSLexer.HASH:
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
	
	

}
