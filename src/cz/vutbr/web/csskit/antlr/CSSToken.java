package cz.vutbr.web.csskit.antlr;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;

public class CSSToken extends CommonToken {

	/**
	 * Extended with EOF_TOKEN
	 */
	private static final long serialVersionUID = 3L;
	
	/**
	 * Current nesting level
	 */
	protected CSSLexer.LexerState ls;
	
	public CSSToken(CharStream input, int type, int channel, int start, int stop) {
		super(input, type, channel, start, stop);
	}
	
	public CSSToken(int type, CSSLexer.LexerState state) {
		super(type);
		this.ls = new CSSLexer.LexerState(state);
	}
	
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
	 * @return the lexer state
	 */
	public CSSLexer.LexerState getLexerState() {
		return ls;	
	}
	
	public static String extractSTRING(String string) {
		return string.substring(1, string.length()-1);
	}

	public static String extractURI(String uri) {
		String ret = uri.substring(4, uri.length()-1);
		// trim string
		if(ret.charAt(0)=='\'' || ret.charAt(0)=='"')
			ret = ret.substring(1, ret.length()-1);
		
		return ret;
	}
	
	public static String extractFUNCTION(String function) {
		return function.substring(0, function.length()-1);
	}
	
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
