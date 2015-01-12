package cz.vutbr.web.csskit.antlr;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.TreeAdaptor;

import org.slf4j.Logger;

public class CSSTreeNodeRecovery {
	
	private final Parser parser;
	private final TokenStream input;
	private final RecognizerSharedState state;
	private final TreeAdaptor adaptor;
	private final Logger log;
	
	public CSSTreeNodeRecovery(Parser parser,
	                           TokenStream input,
	                           RecognizerSharedState state,
	                           TreeAdaptor adaptor,
	                           Logger log) {
		this.parser = parser;
		this.input = input;
		this.state = state;
		this.adaptor = adaptor;
		this.log = log;
	}
	
	/**
	 * Recovers and logs error, prepares tree part replacement
	 */
	public Object invalidFallback(int ttype, String ttext, RecognitionException re) {
		parser.reportError(re);
		parser.recover(input, re);
		return invalidReplacement(ttype, ttext);
	}
	
	/**
	 * Recovers and logs error, using custom follow set,
	 * prepares tree part replacement
	 */
	public Object invalidFallbackGreedy(int ttype, String ttext, BitSet follow, RecognitionException re) {
		parser.reportError(re);
		if ( state.lastErrorIndex==input.index() ) {
			// uh oh, another error at same token index; must be a case
			// where LT(1) is in the recovery token set so nothing is
			// consumed; consume a single token so at least to prevent
			// an infinite loop; this is a failsafe.
			input.consume();
		}
		state.lastErrorIndex = input.index();
		parser.beginResync();
		consumeUntilGreedy(input, follow);
		parser.endResync();
		return invalidReplacement(ttype, ttext);
		
	}
	
	/**
	 * Recovers and logs error inside a function, using custom follow set,
	 * prepares tree part replacement
	 */
	public Object invalidFallback(int ttype, String ttext, BitSet follow, CSSLexerState.RecoveryMode mode, CSSLexerState ls, RecognitionException re) {
		parser.reportError(re);
		if ( state.lastErrorIndex==input.index() ) {
			// uh oh, another error at same token index; must be a case
			// where LT(1) is in the recovery token set so nothing is
			// consumed; consume a single token so at least to prevent
			// an infinite loop; this is a failsafe.
			input.consume();
		}
		state.lastErrorIndex = input.index();
		parser.beginResync();
		consumeUntil(input, follow, mode, ls);
		parser.endResync();
		return invalidReplacement(ttype, ttext);
		
	}

	/**
	 * Recovers and logs error inside a function, using custom follow set,
	 * prepares tree part replacement
	 */
	public Object invalidFallbackGreedy(int ttype, String ttext, BitSet follow, CSSLexerState.RecoveryMode mode, CSSLexerState ls, RecognitionException re) {
		parser.reportError(re);
		if ( state.lastErrorIndex==input.index() ) {
			// uh oh, another error at same token index; must be a case
			// where LT(1) is in the recovery token set so nothing is
			// consumed; consume a single token so at least to prevent
			// an infinite loop; this is a failsafe.
			input.consume();
		}
		state.lastErrorIndex = input.index();
		parser.beginResync();
		consumeUntilGreedy(input, follow, mode, ls);
		parser.endResync();
		return invalidReplacement(ttype, ttext);
		
	}
	
	private Object invalidReplacement(int ttype, String ttext) {
		
		Object root = (Object) adaptor.nil();
		Object node = (Object) adaptor.create(ttype, ttext);
		
		adaptor.addChild(root, node);
		
		if(log.isDebugEnabled()) {
			log.debug("Invalid fallback with: {}", TreeUtil.toStringTree((CommonTree) root));
		}
		
		return root;
	}
	
	/**
	 * Consumes token until lexer state is balanced and
	 * token from follow is matched. Matched token is also consumed
	 */
	private void consumeUntilGreedy(TokenStream input, BitSet follow) {
		CSSToken t = null;
		do{
		  Token next = input.LT(1);
		  if (next instanceof CSSToken)
		      t= (CSSToken) input.LT(1);
		  else
		      break; /* not a CSSToken, probably EOF */
		  log.trace("Skipped greedy: {} follow: {}", t, follow);
		  // consume token even if it will match
		  input.consume();
		}while(!(t.getLexerState().isBalanced() && follow.member(t.getType())));
	}
	
	/**
	 * Consumes token until lexer state is function-balanced and
	 * token from follow is matched. Matched token is also consumed
	 */
	private void consumeUntilGreedy(TokenStream input, BitSet follow, CSSLexerState.RecoveryMode mode, CSSLexerState ls) {
		CSSToken t = null;
		do{
			Token next = input.LT(1);
			if (next instanceof CSSToken)
				t= (CSSToken) input.LT(1);
			else
				break; /* not a CSSToken, probably EOF */
			log.trace("Skipped greedy: {}", t);
			// consume token even if it will match
			input.consume();
		}while(!(t.getLexerState().isBalanced(mode, ls, t) && follow.member(t.getType())));
	}
	
	/**
	 * Consumes token until lexer state is function-balanced and
	 * token from follow is matched.
	 */
	private void consumeUntil(TokenStream input, BitSet follow, CSSLexerState.RecoveryMode mode, CSSLexerState ls) {
		CSSToken t = null;
		boolean finish = false;
		do{
			Token next = input.LT(1);
			if (next instanceof CSSToken)
				t= (CSSToken) input.LT(1);
			else
				break; /* not a CSSToken, probably EOF */
			// consume token if does not match
			finish = (t.getLexerState().isBalanced(mode, ls, t) && follow.member(t.getType()));
			if (!finish)
			{
				log.trace("Skipped: {}", t);
				input.consume();
			}
		}while(!finish);
	}
}
