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
		this.ls = state;
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

	@Override
	public String getText() {
		
		// sets text from input if not text directly available
		text = super.getText();
		
		switch(type) {
			case CSSLexer.FUNCTION:
				return text.substring(0, text.length()-1);
			case CSSLexer.URI:
				return text.substring(3, text.length()-1);
			case CSSLexer.STRING:
				return text.substring(1, text.length()-1);
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
