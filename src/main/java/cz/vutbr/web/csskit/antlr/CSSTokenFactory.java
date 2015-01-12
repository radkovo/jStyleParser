package cz.vutbr.web.csskit.antlr;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognizerSharedState;

public class CSSTokenFactory {
	
	private final CSSInputStream input;
	private final RecognizerSharedState state;
	private final CSSLexerState ls;
	
	public CSSTokenFactory(CharStream input, RecognizerSharedState state, CSSLexerState ls) {
		this.input = (CSSInputStream)input;
		this.state = state;
		this.ls = ls;
	}
	
	public CSSToken make() {
		CSSToken t = new CSSToken(input, state.type, state.channel, state.tokenStartCharIndex, input.index()-1);
		t.setLine(state.tokenStartLine);
		t.setText(state.text);
		t.setCharPositionInLine(state.tokenStartCharPositionInLine);
		t.setBase(input.getBase());
		
		// clone lexer state
		t.setLexerState(new CSSLexerState(ls));
		return t;
	}
}
