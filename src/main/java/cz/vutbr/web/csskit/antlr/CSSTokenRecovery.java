package cz.vutbr.web.csskit.antlr;

import java.util.Stack;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;

import org.slf4j.Logger;

public class CSSTokenRecovery {
	
	private final CSSLexer lexer;
	private final CharStream input;
	private final RecognizerSharedState state;
	private final CSSLexerState ls;
	private final Logger log;
	private final Stack<Integer> expectedToken;
	
	public CSSTokenRecovery(CSSLexer lexer,
	                        CharStream input,
	                        RecognizerSharedState state,
	                        CSSLexerState ls,
	                        Logger log) {
		this.lexer = lexer;
		this.input = input;
		this.state = state;
		this.ls = ls;
		this.log = log;
		this.expectedToken = new Stack<Integer>();
	}
	
	public void expecting(int token) {
		expectedToken.push(token);
	}
	
	public void end() {
		expectedToken.pop();
	}
	
	public boolean recover() {
		// there is no special recovery
		if(expectedToken.isEmpty())
			return false;
		switch(expectedToken.pop().intValue()) {
		case CSSLexer.IMPORT:  // IMPORT share recovery rules with CHARSET
		case CSSLexer.CHARSET:
			final BitSet charsetFollow = BitSet.of((int) '}', (int) ';');
			consumeUntilBalanced(charsetFollow);
			break;
		case CSSLexer.STRING:
			// eat character which should be newline but not EOF
			if(consumeAnyButEOF()) {
				// set back to uninitialized state
				ls.quotOpen = false;
				ls.aposOpen = false;
				// create invalid string token
				state.token = (Token) new CSSToken(CSSLexer.INVALID_STRING, ls);
				state.token.setText("INVALID_STRING");
			}
			// we can't just let parser generate missing
			// single/double quot token
			// because we have not emitted content of string yet!
			// we will fake string token
			else {
				char enclosing = (ls.quotOpen) ? '"' : '\'';
				ls.quotOpen = false;
				ls.aposOpen = false;
				state.token = (Token) new CSSToken(CSSLexer.STRING, ls,
				                                   state.tokenStartCharIndex, input.index() - 1);
				state.token.setText(
					input.substring(state.tokenStartCharIndex, input.index() - 1)
					+ enclosing);
			}
			break;
		default:
			return false;
		}
		return true;
	}
	
	/**
	 * Implements Lexer's next token with extra token passing from
	 * recovery function
	 */
	public Token nextToken() {
		while (true) {
			state.token = null;
			state.channel = Token.DEFAULT_CHANNEL;
			state.tokenStartCharIndex = input.index();
			state.tokenStartCharPositionInLine = input.getCharPositionInLine();
			state.tokenStartLine = input.getLine();
			state.text = null;
			if ( input.LA(1)==CharStream.EOF ) {
				// recover from unexpected EOF
				if (!ls.isBalanced())
					return generateEOFRecover();
				return lexer.getEOFToken();
			}
			try {
				lexer.mTokens();
				if ( state.token==null ) {
					lexer.emit();
				}
				else if ( state.token==Token.SKIP_TOKEN ) {
					continue;
				}
				return state.token;
			}
			catch (RecognitionException re) {
				lexer.reportError(re);
				if ( re instanceof NoViableAltException ) {
					lexer.recover(re);
				}

				// there can be token returned from recovery
				if(state.token!=null) {
					state.token.setChannel(Token.INVALID_TOKEN_TYPE);
					state.token.setInputStream(input);
					state.token.setLine(state.tokenStartLine);
					state.token.setCharPositionInLine(state.tokenStartCharPositionInLine);
					lexer.emit(state.token);
					return state.token;
				}
			}
		}
	}
	
	/**
	 * Recovers from unexpected EOF by preparing
	 * new token
	 */
	public CSSToken generateEOFRecover() {
		
		CSSToken t = null;
		
		if(ls.aposOpen) {
			ls.aposOpen=false;
			t = new CSSToken(CSSLexer.APOS, ls);
			t.setText("'");
		}
		else if(ls.quotOpen) {
			ls.quotOpen=false;
			t = new CSSToken(CSSLexer.QUOT, ls);
			t.setText("\"");
		}
		else if(ls.parenNest!=0) {
			ls.parenNest--;
			t = new CSSToken(CSSLexer.RPAREN, ls);
			t.setText(")");
		}
		else if(ls.curlyNest!=0) {
			ls.curlyNest--;
			t = new CSSToken(CSSLexer.RCURLY, ls);
			t.setText("}");
		}
		
		log.debug("Recovering from EOF by {}", t);
		return t;
	}
	
	/**
	 * Eats characters until on from follow is found and Lexer state
	 * is balanced at the moment
	 */
	private void consumeUntilBalanced(BitSet follow) {
		
		log.debug("Lexer entered consumeUntilBalanced with {} and follow {}",
			ls, follow);
	
		int c;
		do {
			c = input.LA(1);
			// change apostrophe state
			if(c=='\'' && ls.quotOpen==false) {
				ls.aposOpen = !ls.aposOpen;
			}
			// change quot state
			else if (c=='"' && ls.aposOpen==false) {
				ls.quotOpen = !ls.quotOpen;
			}
			else if(c=='(') {
				ls.parenNest++;
			}
			else if(c==')' && ls.parenNest>0) {
				ls.parenNest--;
			}
			else if(c=='{') {
				ls.curlyNest++;
			}
			else if(c=='}' && ls.curlyNest>0) {
				ls.curlyNest--;
			}
			// handle end of line in string
			else if(c=='\n') {
				if(ls.quotOpen) ls.quotOpen=false;
				else if(ls.aposOpen) ls.aposOpen=false;
			}
			else if(c==CSSLexer.EOF) {
				log.info("Unexpected EOF during consumeUntilBalanced, EOF not consumed");
				return;
			}
		
			input.consume();
			// log result
			if(log.isTraceEnabled())
				log.trace("Lexer consumes '{}'({}) until balanced ({}).",
					new Object[] {
						Character.toString((char) c),
						Integer.toString(c),
						ls});
				
		}while(!(ls.isBalanced() && follow.member(c)));
	}
	
	/**
	 * Consumes arbitrary character but EOF
	 * @return <code>false</code> if EOF was matched,
	 *         <code>true</code> otherwise and that character was consumed
	 */
	private boolean consumeAnyButEOF() {
	
		int c = input.LA(1);
		
		if(c==CharStream.EOF)
			return false;
			
		if(log.isTraceEnabled())
			log.trace("Lexer consumes '{}' while consumeButEOF",
						Character.toString((char)c));
		
		// consume character
		input.consume();
		return true;
	}
}
