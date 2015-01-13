package cz.vutbr.web.csskit.antlr;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognizerSharedState;

public class CSSTokenFactory {
	
	private final CSSInputStream input;
	private final RecognizerSharedState state;
	private final CSSLexerState ls;
	private final Class<? extends Lexer> lexerClass;
	
	public CSSTokenFactory(CharStream input, RecognizerSharedState state, CSSLexerState ls, Class<? extends Lexer> lexerClass) {
		this.input = (CSSInputStream)input;
		this.state = state;
		this.ls = ls;
		this.lexerClass = lexerClass;
	}
	
	public CSSToken make() {
		CSSToken t = new CSSToken(input, state.type, state.channel, state.tokenStartCharIndex, input.index()-1, lexerClass);
		t.setLine(state.tokenStartLine);
		t.setText(state.text);
		t.setCharPositionInLine(state.tokenStartCharPositionInLine);
		t.setBase(input.getBase());
		
		// clone lexer state
		t.setLexerState(new CSSLexerState(ls));
		return t;
	}
}
