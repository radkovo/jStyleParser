/*
 * CSS.g 
 * Copyright (c) 2008 Karel Piwko
 * Copyright (c) 2008-2009 Radek Burget
 *
 * jStyleParser is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *  
 * jStyleParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *  
 * You should have received a copy of the GNU Lesser General Public License
 * along with jStyleParser. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
 
/**
 * A basic CSS grammar.
 */
grammar CSS;

options {
	output = AST;
	k = 2;
}

tokens {
	STYLESHEET;
	INLINESTYLE;
	ATBLOCK;
	CURLYBLOCK;
	PARENBLOCK;
	BRACEBLOCK;
	RULE;	
	SELECTOR;
	ELEMENT;
	PSEUDO;
	ADJACENT;
	CHILD;
	DESCENDANT;
	ATTRIBUTE;
	SET;
	DECLARATION;	
	VALUE;
	IMPORTANT;
	
	IMPORT_END;
	
	INVALID_STRING;
	INVALID_SELECTOR;
	INVALID_SELPART;
	INVALID_DECLARATION;
	INVALID_STATEMENT;
	INVALID_IMPORT;
	INVALID_DIRECTIVE;
}

@lexer::header {
package cz.vutbr.web.csskit.antlr;

import org.fit.net.DataURLHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.SupportedCSS; 

}

@lexer::members {
    

    private static Logger log = LoggerFactory.getLogger(CSSLexer.class);
    
    private static SupportedCSS css = CSSFactory.getSupportedCSS();
    
    public static class LexerState {
        
        public enum RecoveryMode {
            BALANCED,
            FUNCTION, 
            RULE,
            DECL
        } 
    
        public short curlyNest;
        public short parenNest;
        public boolean quotOpen;
        public boolean aposOpen;
        
        public LexerState() {
        	this.curlyNest = 0;
        	this.parenNest = 0;
        	this.quotOpen = false;
        	this.aposOpen = false;
        }
        
        public LexerState(LexerState clone) {
        	this();
            this.curlyNest = clone.curlyNest;
            this.parenNest = clone.parenNest;
            this.quotOpen = clone.quotOpen;
            this.aposOpen = clone.aposOpen;
        }
        
        @Override
        public boolean equals(Object o)
        {
            return (this.curlyNest == ((LexerState) o).curlyNest &&
                    this.parenNest == ((LexerState) o).parenNest &&
                    this.quotOpen == ((LexerState) o).quotOpen &&
                    this.aposOpen == ((LexerState) o).aposOpen);
        }
        
        /**
         * Checks whether all pair characters (single and double quotatation marks,
         * curly braces) are balanced
		     */ 
        public boolean isBalanced() {
        	return aposOpen==false && quotOpen==false && curlyNest==0 && parenNest==0;
        }
        
        /**
         * Checks whether some pair characters are balanced. Modes are:
         * <ul>
         * <li>BALANCED - everything must be balanced: single and double quotatation marks, curly braces, parentheses 
         * <li>FUNCTION - within the function arguments: parentheses must be balanced 
         * <li>RULE - within the CSS rule: all but curly braces
         * <li>DECL - within declaration: all, keep curly braces at desired state
         * </ul> 
         */ 
        public boolean isBalanced(RecoveryMode mode, LexerState state)
        {
            if (mode == RecoveryMode.BALANCED)
                return aposOpen==false && quotOpen==false && curlyNest==0 && parenNest==0;
            else if (mode == RecoveryMode.FUNCTION)
                return parenNest==0;
            else if (mode == RecoveryMode.RULE)
                return aposOpen==false && quotOpen==false && parenNest==0;
            else if (mode == RecoveryMode.DECL)
                return aposOpen==false && quotOpen==false && parenNest==0 && curlyNest==state.curlyNest;
            else
                return false;
        }
        
        /**
         * Recovers from unexpected EOF by preparing 
         * new token
         */ 
        public CSSToken generateEOFRecover() {
        	
        	CSSToken t = null;
        
        	if(aposOpen) {
        		this.aposOpen=false;
        		t = new CSSToken(CSSLexer.APOS, this);
        		t.setText("'");
        	}
        	else if(quotOpen) {
        		this.quotOpen=false;
        		t = new CSSToken(CSSLexer.QUOT, this);
        		t.setText("\"");
        	}
        	else if(parenNest!=0) {
        		this.parenNest--;
        		t = new CSSToken(CSSLexer.RPAREN, this);
        		t.setText(")");
        	}
        	else if(curlyNest!=0) {
        		this.curlyNest--;
        		t = new CSSToken(CSSLexer.RCURLY, this);
        		t.setText("}");
        	}
        	
        	log.debug("Recovering from EOF by {}", t);
        	return t;
        }
        
        @Override
        public String toString() {
        	StringBuilder sb = new StringBuilder();
        	sb.append("{=").append(curlyNest)
        	  .append(", (=").append(parenNest)
        	  .append(", '=").append(aposOpen ? "1" : "0")
        	  .append(", \"=").append(quotOpen ? "1" :"0");
        	 
        	return sb.toString();  
        }
    }
    
    private static class LexerStream {
    
    	public CharStream input;
    	public int mark;
    	public LexerState ls;
    	
    	public LexerStream(CharStream input, LexerState ls) {
    	    this.input = input;
    	    this.mark = input.mark();
    	    this.ls = new LexerState(ls);
    	}
    }
    
    // number of already processed tokens (for checking the beginning of the style sheet)
    private int tokencnt = 0;
    
    // lexer states for imported files
    private Stack<LexerStream> imports;
    
    // current lexer state
    private LexerState ls;
    
    // this is for orthogonality
    @SuppressWarnings("unused")
    private StyleSheet stylesheet;
    
    // token recovery
    private Stack<Integer> expectedToken;
    
    /**
     * This function must be called to initialize lexer's state.
     * Because we can't change directly generated constructors.
     * @param stylesheet CSS StyleSheet instance  
     */
    public CSSLexer init(StyleSheet stylesheet) {
	    this.imports = new Stack<LexerStream>();
	    this.expectedToken = new Stack<Integer>();
		this.ls = new LexerState();
		this.stylesheet = stylesheet;
		return this;
    }
    
    @Override
    public void reset() {
    	super.reset();
    	this.ls = new LexerState();
    }
    
    /**
     * Overrides next token to match includes and to 
     * recover from EOF
     */
	@Override 
    public Token nextToken(){
       Token token = nextTokenRecover();

       //count non-empty tokens for eventual checking of the style sheet start
       if (token.getType() == S) {
           tokencnt++;
       }

       // recover from unexpected EOF
       if(token==Token.EOF_TOKEN && !ls.isBalanced()) {
           CSSToken t = ls.generateEOFRecover(); 
           return (Token) t;
       }

       // push back import stream
       // We've got EOF and have non empty stack
       if(token==Token.EOF_TOKEN && !imports.empty()){

       	 // prepare end token 	
       	 CSSToken t = new CSSToken(IMPORT_END, ls);
       	 t.setText("IMPORT_END");
       
         // We've got EOF and have non empty stack.
         LexerStream stream = imports.pop();
         setCharStream(stream.input);
         input.rewind(stream.mark);
         this.ls = stream.ls;
         
         // send created token
         return (Token) t;
         //token = nextTokenRecover();
       }       

       // Skip first token after switching on another input.
       if(((CommonToken)token).getStartIndex() < 0)
         token = nextToken();
        
       return token;
    }

    /**
	 * Adds contextual information about n      {
	 esting into token.
	 */
    @Override
	public Token emit() {
		CSSToken t = new CSSToken(input, state.type, state.channel,
                        state.tokenStartCharIndex, getCharIndex()-1);
        t.setLine(state.tokenStartLine);
        t.setText(state.text);
        t.setCharPositionInLine(state.tokenStartCharPositionInLine);
        t.setBase(((CSSInputStream) input).getBase());
        
        // clone lexer state
        t.setLexerState(new LexerState(ls));
        emit(t);
        return t;
	}

	@Override
    public void emitErrorMessage(String msg) {
    	log.info("ANTLR: {}", msg);
    }
    
    /**
     * Does special token recovery for some cases
     */ 
    @Override
    public void recover(RecognitionException re) {
    	// there is no special recovery
    	if(expectedToken.isEmpty())
    		super.recover(re);
    	else {
    		switch(expectedToken.pop().intValue()) {
    		
    		case IMPORT:  // IMPORT share recovery rules with CHARSET
    		case CHARSET:
    			final BitSet charsetFollow = BitSet.of((int) '}', (int) ';');
    			consumeUntilBalanced(charsetFollow);
    			break;
    		case STRING:
    			// eat character which should be newline but not EOF
    			if(consumeAnyButEOF()) {
    				// set back to uninitialized state
    				ls.quotOpen = false;
    				ls.aposOpen = false;
    				// create invalid string token
    				state.token = (Token) new CSSToken(INVALID_STRING, ls);
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
    				state.token = (Token) new CSSToken(STRING, ls, 
    					state.tokenStartCharIndex, getCharIndex() -1);
        			state.token.setText(
        				input.substring(state.tokenStartCharIndex, getCharIndex() -1)
        				+ enclosing);	
    			}
    			break;
    		default:
    			super.recover(re);
    		}
    	}	
    }
    
    /**
     * Implements Lexer's next token with extra token passing from
     * recovery function 
     */
    private Token nextTokenRecover() {
    	while (true) {
			state.token = null;
			state.channel = Token.DEFAULT_CHANNEL;
			state.tokenStartCharIndex = input.index();
			state.tokenStartCharPositionInLine = input.getCharPositionInLine();
			state.tokenStartLine = input.getLine();
			state.text = null;
			if ( input.LA(1)==CharStream.EOF ) {
				return CSSToken.EOF_TOKEN;
			}
			try {
				mTokens();
				if ( state.token==null ) {
					emit();
				}
				else if ( state.token==Token.SKIP_TOKEN ) {
					continue;
				}
				return state.token;
			}
			catch (RecognitionException re) {
				reportError(re);
				if ( re instanceof NoViableAltException ) {
					recover(re); 
				}

				// there can be token returned from recovery
                if(state.token!=null) {
                    state.token.setChannel(Token.INVALID_TOKEN_TYPE);
                  	state.token.setInputStream(input);
                   	state.token.setLine(state.tokenStartLine);
            		state.token.setCharPositionInLine(state.tokenStartCharPositionInLine);
            		emit(state.token);
            		return state.token;
                }
			}
		}
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
    		else if(c==EOF) {
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
    
    /**
     * Reads all the contents of an expression. Parenthesis are matched but not in strings.
     */ 
    private String readExpressionContents() 
    {
      log.debug("readExpressionContents"); 
    
      StringBuffer ret = new StringBuffer(); 
      int parenN = 1; /* one already open */
      boolean inApos = false;
      boolean inQuot = false;
      boolean esc = false;
      boolean finished = false;
      int c;
	    while (!finished)
	    {
	        c = input.LA(1);
	        if(c=='\'' && !inQuot && !(inApos && esc)) {
	            inApos = !inApos;
	        }
	        else if (c=='"' && !inApos && !(inQuot && esc)) {
	            inQuot = !inQuot;
	        }
	        else if(c=='(' && !inApos && !inQuot) {
	            parenN++;
	        }
	        else if(c==')' && parenN>0  && !inApos && !inQuot) {
	            parenN--;
	            if (parenN == 0) finished = true;
	        }
	        // handle end of line in string
	        else if (c=='\n') {
	           inQuot = false;
	           inApos = false;
	        } 
	        else if(c==EOF) {
	          log.info("Unexpected EOF during consumeUntilBalanced, EOF not consumed");
	          return ret.toString();
	        }
	        
	        esc = (c == '\\') && !esc;
	        
	        if (!finished) ret.append((char) c);
	        
	        input.consume();
	          
	    }
	    
	    log.debug("Expr: " + ret.toString());
      return ret.toString();
    }
    
}

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////// P A R S E R /////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

@parser::header { 
package cz.vutbr.web.csskit.antlr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.SupportedCSS;
import cz.vutbr.web.csskit.antlr.CSSLexer.LexerState;
}

@parser::members {
    private static Logger log = LoggerFactory.getLogger(CSSParser.class);
    
    private static SupportedCSS css = CSSFactory.getSupportedCSS();
    
    private StyleSheet stylesheet;
    
    private int functLevel = 0;
    
    /**
     * This function must be called to initialize parser's state.
     * Because we can't change directly generated constructors.
     * @param stylesheet CSS StyleSheet instance  
     */
    public CSSParser init(StyleSheet stylesheet) {
    	this.stylesheet = stylesheet;
    	return this;
    }
    
    @Override
    public void emitErrorMessage(String msg) {
    	log.info("ANTLR: {}", msg);
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
	 * Recovers and logs error, prepares tree part replacement
	 */ 
	private Object invalidFallback(int ttype, String ttext, RecognitionException re) {
	    reportError(re);
		recover(input, re);
		return invalidReplacement(ttype, ttext);
	}
	
	/**
	 * Recovers and logs error, using custom follow set,
	 * prepares tree part replacement
	 */ 
	private Object invalidFallbackGreedy(int ttype, String ttext, BitSet follow, RecognitionException re) {
		reportError(re);
		if ( state.lastErrorIndex==input.index() ) {
			// uh oh, another error at same token index; must be a case
	 		// where LT(1) is in the recovery token set so nothing is
            // consumed; consume a single token so at least to prevent
            // an infinite loop; this is a failsafe.
            input.consume();
        }
    state.lastErrorIndex = input.index();
    beginResync();
		consumeUntilGreedy(input, follow);
    endResync();
		return invalidReplacement(ttype, ttext);
		
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
   * Recovers and logs error inside a function, using custom follow set,
   * prepares tree part replacement
   */ 
  private Object invalidFallbackGreedy(int ttype, String ttext, BitSet follow, LexerState.RecoveryMode mode, LexerState ls, RecognitionException re) {
    reportError(re);
    if ( state.lastErrorIndex==input.index() ) {
      // uh oh, another error at same token index; must be a case
      // where LT(1) is in the recovery token set so nothing is
            // consumed; consume a single token so at least to prevent
            // an infinite loop; this is a failsafe.
            input.consume();
        }
    state.lastErrorIndex = input.index();
    beginResync();
    consumeUntilGreedy(input, follow, mode, ls);
    endResync();
    return invalidReplacement(ttype, ttext);
    
    }
  
  /**
   * Consumes token until lexer state is function-balanced and
   * token from follow is matched. Matched token is also consumed
   */ 
  private void consumeUntilGreedy(TokenStream input, BitSet follow, LexerState.RecoveryMode mode, LexerState ls) {
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
    }while(!(t.getLexerState().isBalanced(mode, ls) && follow.member(t.getType())));
  }
  
  /**
   * Recovers and logs error inside a function, using custom follow set,
   * prepares tree part replacement
   */ 
  private Object invalidFallback(int ttype, String ttext, BitSet follow, LexerState.RecoveryMode mode, LexerState ls, RecognitionException re) {
    reportError(re);
    if ( state.lastErrorIndex==input.index() ) {
      // uh oh, another error at same token index; must be a case
      // where LT(1) is in the recovery token set so nothing is
            // consumed; consume a single token so at least to prevent
            // an infinite loop; this is a failsafe.
            input.consume();
        }
    state.lastErrorIndex = input.index();
    beginResync();
    consumeUntil(input, follow, mode, ls);
    endResync();
    return invalidReplacement(ttype, ttext);
    
    }
  
  /**
   * Consumes token until lexer state is function-balanced and
   * token from follow is matched.
   */ 
  private void consumeUntil(TokenStream input, BitSet follow, LexerState.RecoveryMode mode, LexerState ls) {
    CSSToken t = null;
    boolean finish = false;
    do{
      Token next = input.LT(1);
      if (next instanceof CSSToken)
          t= (CSSToken) input.LT(1);
      else
          break; /* not a CSSToken, probably EOF */
      // consume token if does not match
      finish = (t.getLexerState().isBalanced(mode, ls) && follow.member(t.getType()));
      if (!finish)
      { 
          log.trace("Skipped: {}", t);
          input.consume();
      }
    }while(!finish);
  }
    
  /**
   * Obtains the current lexer state from current token
   */
  private LexerState getCurrentLexerState(Token t)
  {
      if (t instanceof CSSToken)
          return ((CSSToken) t).getLexerState();
      else
          return null;
  }
     
  //this switches the single token insertion / deletion off because it interferes with our own error recovery
  protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow)
      throws RecognitionException
  {
      throw new MismatchedTokenException(ttype, input);
  }
   
}

/** Inline style contained in the 'style' attribute */
// since declarations can match empty string
// force at least one inlineset to exist
//TODO: The COLON in the possible pseudo class selector of inlineset (a CSS3 thing) conflicts with the
//colon recognized as the start of an invalid property (noprop) which is used in some nasty CSS hacks.
//For now, the CSS3 feature is broken in favor of the hack recovery 
inlinestyle
	: S*  (declarations -> ^(INLINESTYLE declarations) 
		     | inlineset+ -> ^(INLINESTYLE inlineset+)
			)
	;

stylesheet
	: ( CDO | CDC | S | nostatement | statement )* 
		-> ^(STYLESHEET statement*)
	;
	
statement   
	: ruleset | atstatement
	;

atstatement
	: CHARSET
	| IMPORT
	| INVALID_IMPORT
	| IMPORT_END
	| PAGE S* (COLON IDENT S*)? 
		LCURLY S* declarations 
		RCURLY -> ^(PAGE IDENT? declarations)
	| MEDIA S* media? 
		LCURLY S* (ruleset S*)* RCURLY -> ^(MEDIA media? ruleset*)	
	| ATKEYWORD S* LCURLY any* RCURLY -> INVALID_STATEMENT
	;
	catch [RecognitionException re] {
      	final BitSet follow = BitSet.of(CSSLexer.RCURLY, CSSLexer.SEMICOLON);								
	    retval.tree = invalidFallbackGreedy(CSSLexer.INVALID_STATEMENT, 
	  		"INVALID_STATEMENT", follow, re);							
	}

/** A ruleset in the inline style according to
    http://www.w3.org/TR/css-style-attr */
inlineset
	: (pseudo S* (COMMA S* pseudo S*)*)?
      LCURLY
	  	declarations
	  RCURLY	
	  -> ^(RULE pseudo* declarations)
	;
	
media
	: IDENT S* (COMMA S* IDENT S*)* 
		-> IDENT+
	;		
	
ruleset
	: combined_selector (COMMA S* combined_selector)* 
	  LCURLY S* 
	  	declarations
	  RCURLY
	  -> ^(RULE combined_selector+ declarations)
	| norule -> INVALID_STATEMENT
	;
	catch [RecognitionException re] {
      final BitSet follow = BitSet.of(CSSLexer.RCURLY);
      //we don't require {} to be balanced here because of possible parent 'media' sections that may remain open => RecoveryMode.RULE
	    retval.tree = invalidFallbackGreedy(CSSLexer.INVALID_STATEMENT,	"INVALID_STATEMENT", follow, LexerState.RecoveryMode.RULE, null, re);							
	}

declarations
	: declaration? (SEMICOLON S* declaration? )*
	  -> ^(SET declaration*)
	;

declaration
@init {
  LexerState begin = getCurrentLexerState(retval.start);
  log.trace("Decl begin: " + begin);
}
	: property COLON S* terms? important? -> ^(DECLARATION important? property terms?)
	| noprop any* -> INVALID_DECLARATION /* if first character in the declaration is invalid (various dirty hacks) */
	;
	catch [RecognitionException re] {
	  //retval.tree = invalidFallback(CSSLexer.INVALID_DECLARATION, "INVALID_DECLARATION", re);									
      final BitSet follow = BitSet.of(CSSLexer.SEMICOLON);               
      retval.tree = invalidFallback(CSSLexer.INVALID_DECLARATION, "INVALID_DECLARATION", follow, LexerState.RecoveryMode.DECL, begin, re);             
	}

important
  : EXCLAMATION S* 'important' S* -> IMPORTANT
  ;
  catch [RecognitionException re] {
      final BitSet follow = BitSet.of(CSSLexer.RCURLY, CSSLexer.SEMICOLON);               
      retval.tree = invalidFallback(CSSLexer.INVALID_DIRECTIVE, "INVALID_DIRECTIVE", follow, LexerState.RecoveryMode.RULE, null, re);
  }

property    
	: MINUS? IDENT S* -> MINUS? IDENT
	;
	
terms	       
	: term+
	-> ^(VALUE term+)
	;
	catch [RecognitionException re] {
		if (functLevel == 0)
		{
	      final BitSet follow = BitSet.of(CSSLexer.RCURLY, CSSLexer.SEMICOLON);								
		    retval.tree = invalidFallbackGreedy(CSSLexer.INVALID_STATEMENT, 
		  		"INVALID_STATEMENT", follow, re);
		}
		else
		{
        final BitSet follow = BitSet.of(CSSLexer.RPAREN, CSSLexer.RCURLY, CSSLexer.SEMICOLON);               
        retval.tree = invalidFallbackGreedy(CSSLexer.INVALID_STATEMENT, "INVALID_STATEMENT", follow, LexerState.RecoveryMode.FUNCTION, null, re);
		}
	}
	
term
    : valuepart -> valuepart
    | LCURLY S* (any | SEMICOLON S*)* RCURLY -> CURLYBLOCK
    | ATKEYWORD S* -> ATKEYWORD
    ;	

/** other functions than expression */
funct
@init {
	functLevel++;
}
@after {
	functLevel--;
}
  : EXPRESSION -> EXPRESSION
	| FUNCTION S* terms? RPAREN -> ^(FUNCTION terms?)
	;

/** a part of a property value */
valuepart
    : ( MINUS? IDENT -> MINUS? IDENT
      | CLASSKEYWORD -> CLASSKEYWORD
      | MINUS? NUMBER -> MINUS? NUMBER
      | MINUS? PERCENTAGE -> MINUS? PERCENTAGE
      | MINUS? DIMENSION -> MINUS? DIMENSION
      | string -> string
      | URI    -> URI
      | HASH -> HASH
      | UNIRANGE -> UNIRANGE
      | INCLUDES -> INCLUDES
      | COLON -> COLON
      | COMMA -> COMMA
      | GREATER -> GREATER
      | LESS -> LESS
      |	QUESTION -> QUESTION
      | PERCENT -> PERCENT
      | EQUALS -> EQUALS
      | SLASH -> SLASH
	    | PLUS -> PLUS
	    | ASTERISK -> ASTERISK		 
      | MINUS? funct -> MINUS? funct 
      | DASHMATCH -> DASHMATCH
      | LPAREN valuepart* RPAREN -> ^(PARENBLOCK valuepart*)
      | LBRACE valuepart* RBRACE -> ^(BRACEBLOCK valuepart*)
    ) !S*
  ;

combined_selector    
	: selector ((combinator) selector)*
	;
	catch [RecognitionException re] {
	  log.warn("INVALID COMBINED SELECTOR");
	  reportError(re);
      recover(input,re);
	}

combinator
	: GREATER S* -> CHILD
	| PLUS S* -> ADJACENT
	| S -> DESCENDANT
	;

selector
    : (IDENT | ASTERISK)  selpart* S*
    	-> ^(SELECTOR ^(ELEMENT IDENT?) selpart*)
    | selpart+ S*
        -> ^(SELECTOR selpart+)
    ;
    catch [RecognitionException re] {
      retval.tree = invalidFallback(CSSLexer.INVALID_SELECTOR, "INVALID_SELECTOR", re);
	  }

selpart	
    :  HASH
    | CLASSKEYWORD
	  | LBRACE S* attribute RBRACE -> ^(ATTRIBUTE attribute)
    | pseudo
    | INVALID_SELPART
    ;
    catch [RecognitionException re] {
      retval.tree = invalidFallback(CSSLexer.INVALID_SELPART, "INVALID_SELPART", re);
	  }

attribute
	: IDENT S*
	  ((EQUALS | INCLUDES | DASHMATCH) S* (IDENT | string) S*)?
	;

pseudo
	: pseudocolon^ (IDENT | FUNCTION (IDENT | NUMBER) RPAREN!)
	;
  catch [RecognitionException re] {
     retval.tree = invalidFallback(CSSLexer.INVALID_SELPART, "INVALID_SELPART", re);
  }

pseudocolon
	: COLON COLON? -> PSEUDO
	;

string
	: STRING
	| INVALID_STRING
	;
	
	
any
	: ( IDENT -> IDENT
	  | CLASSKEYWORD -> CLASSKEYWORD
	  | NUMBER -> NUMBER
	  | PERCENTAGE ->PERCENTAGE
	  | DIMENSION -> DIMENSION
	  | string -> string
      | URI    -> URI
      | HASH -> HASH
      | UNIRANGE -> UNIRANGE
      | INCLUDES -> INCLUDES
      | COLON -> COLON
      | COMMA -> COMMA
      | GREATER -> GREATER
      | LESS -> LESS
      |	QUESTION -> QUESTION
      | PERCENT -> PERCENT
      | EQUALS -> EQUALS
      | SLASH -> SLASH
      | EXCLAMATION -> EXCLAMATION
	  | MINUS -> MINUS
	  | PLUS -> PLUS
	  | ASTERISK -> ASTERISK		 
      | FUNCTION S* any* RPAREN -> ^(FUNCTION any*) 
      | DASHMATCH -> DASHMATCH
      | LPAREN any* RPAREN -> ^(PARENBLOCK any*)
      | LBRACE any* RBRACE -> ^(BRACEBLOCK any*)
    ) !S*;

/** What cannot be contained directly in the stylesheet (ignored) */
nostatement
  : ( RCURLY -> RCURLY |
      SEMICOLON -> SEMICOLON
    );

/** invalid start of a property */
noprop
	: ( CLASSKEYWORD -> CLASSKEYWORD
     | NUMBER -> NUMBER
	   | COMMA -> COMMA
	   | GREATER -> GREATER
	   | LESS -> LESS
	   | QUESTION -> QUESTION
	   | PERCENT -> PERCENT
	   | EQUALS -> EQUALS
	   | SLASH -> SLASH
	   | EXCLAMATION -> EXCLAMATION
	   | PLUS -> PLUS
	   | ASTERISK -> ASTERISK		 
	   | DASHMATCH -> DASHMATCH
	   | INCLUDES -> INCLUDES
	   | COLON -> COLON
	   | STRING_CHAR -> STRING_CHAR
	   | INVALID_TOKEN -> INVALID_TOKEN
    ) !S*;

/** invalid start of a rule */
norule
  : ( NUMBER -> NUMBER
	    | PERCENTAGE ->PERCENTAGE
	    | DIMENSION -> DIMENSION
	    | string -> string
      | URI    -> URI
      | UNIRANGE -> UNIRANGE
      | INCLUDES -> INCLUDES
      | COMMA -> COMMA
      | GREATER -> GREATER
      | LESS -> LESS
      | QUESTION -> QUESTION
      | PERCENT -> PERCENT
      | EQUALS -> EQUALS
      | SLASH -> SLASH
      | EXCLAMATION -> EXCLAMATION
	    | MINUS -> MINUS
	    | PLUS -> PLUS
      | DASHMATCH -> DASHMATCH
      | RPAREN -> RPAREN
      | '#' //that is not HASH (not an identifier)
    );
    
/////////////////////////////////////////////////////////////////////////////////
// TOKENS //
/////////////////////////////////////////////////////////////////////////////////

/** Identifier */
IDENT	
	: IDENT_MACR
	;	

CHARSET
@init {
	expectedToken.push(new Integer(CHARSET));
}
@after {
	expectedToken.pop();
}
	
	: '@charset' S* s=STRING_MACR S* SEMICOLON 
	  {
	    // we have to trim manually
	    String enc = CSSToken.extractSTRING($s.getText());
	    //System.err.println("CHARSET"+tokencnt);
	    if (tokencnt <= 1) //we are at the beginning of the style sheet
	    {
			    try {
			           log.warn("Changing charset to {}", enc);
			          ((CSSInputStream) input).setEncoding(enc);
			          //input = setCharStream(new ANTLFileStream(input.getSourceName(), enc));
			        }
			        catch(IllegalCharsetNameException icne) {
			        	log.warn("Could not change to unsupported charset!", icne);
			        	throw new RuntimeException(new CSSException("Unsupported charset: " + enc));
			        }
			        catch (IOException e) {
                log.warn("Could not change to unsupported charset!", e);
			        }
			 }
			 else
			      log.warn("Ignoring @charset rule not at the beginning of the style sheet");
	  }
	;

IMPORT
@init {
	expectedToken.push(new Integer(IMPORT));
	StringBuilder media = new StringBuilder();
	String mText = null;
}
@after {
	expectedToken.pop();
}
	: '@import' S* 
	  (s=STRING_MACR { $s.setType(STRING);} 
	  	| s=URI {$s.setType(URI);}) S*
	    (m=IDENT_MACR { 
	        mText = $m.getText();
	    	if(css.isSupportedMedia(mText)) 
	    		media.append(mText); 
	    	else
	    	    log.debug("Invalid import media \"{}\"", mText);
	     } 
	     S* 
	       (',' S* m=IDENT_MACR { 
	         mText = $m.getText();
	       	 if(css.isSupportedMedia(mText)) 
	       	 		media.append(",").append(mText);
	       	 else
	    	    log.debug("Invalid import media \"{}\"", mText);		
	       	} 
	       S* )*
	    )?
	  SEMICOLON 
	  {
		    // do some funny work with file name to be imported
        String fileName = s.getText();
        log.debug("FILE: " + fileName);
            	  	
        if(s.getType()==STRING) 
        	fileName = CSSToken.extractSTRING(fileName);
        else
        	fileName = CSSToken.extractURI(fileName);
            	  	
        log.info("Will import file \"{}\" with media: {}", 
          		fileName, media.toString());           	  	
            	  	
        // import file
        URL url = null;
        try {
        		    // construct URL
        		    log.debug("BASE: " + ((CSSInputStream) input).getBase());
        		    URL base = ((CSSInputStream) input).getBase();
        		    if (base != null)
              	    url = DataURLHandler.createURL(base, fileName);
              	else
              	{
              	    log.warn("Base URL is unknown");
                    url = DataURLHandler.createURL(base, fileName);
              	}
              	               			
              	log.debug("Actually, will try to import file \"{}\"", url.toString());	
              			
                // save current lexer's stream
                LexerStream stream = new LexerStream(input, ls);
                imports.push(stream);
                    	
                CSSToken t = new CSSToken(IMPORT, ls);
                t.setText(media.toString());
                    	
                // switch on new stream
                String enc = ((CSSInputStream) input).getEncoding();
                setCharStream(CSSInputStream.urlStream(url, enc));
                reset();
                    	
                log.info("File \"{}\" was imported.", url.toString());
                emit(t);
         }
         catch(MalformedURLException mue) {
         		log.warn("Unable to construct URL for fileName", fileName); 
              	// set type to invalid import
                _type = INVALID_IMPORT;
                setText("INVALID_IMPORT");
         }              		 
         catch(IOException fnf) {
         		log.warn("Cannot read \"{}\" to import: {}", fileName, fnf.getMessage());
                // restore state
                imports.pop();
                // set type to invalid import
                _type = INVALID_IMPORT;
                setText("INVALID_IMPORT");
          }
	}
	;

MEDIA
	: '@media'
	;

PAGE
	: '@page'
	;
	
/** Keyword beginning with '@' */
ATKEYWORD
	: '@' MINUS? IDENT_MACR
	;

CLASSKEYWORD
    : '.' IDENT_MACR
    ;

/** String including 'decorations' */
STRING
@init{
	expectedToken.push(new Integer(STRING));
}
@after {
	expectedToken.pop();
}
	: STRING_MACR
	;

/** Hash, either color or other */
HASH
	: '#' NAME_MACR	
	;

/** Number, decimal or integer */
NUMBER
	: NUMBER_MACR
	;

/** Number with percent sign */
PERCENTAGE
	: NUMBER_MACR '%'
	;

/** Number with other unit */
DIMENSION
	: NUMBER_MACR IDENT_MACR
	;

/** URI encapsulated in 'url(' ... ')' */
URI
	: 'url(' W_MACR (STRING_MACR | URI_MACR) W_MACR ')'
	;

/** Unicode range */	
UNIRANGE:	
	'U+' ('0'..'9' | 'a'..'f' | 'A'..'F' | '?')
	     ('0'..'9' | 'a'..'f' | 'A'..'F' | '?')
	     ('0'..'9' | 'a'..'f' | 'A'..'F' | '?')
	     ('0'..'9' | 'a'..'f' | 'A'..'F' | '?')
	     (('0'..'9' | 'a'..'f' | 'A'..'F' | '?') ('0'..'9' | 'a'..'f' | 'A'..'F' | '?'))?
	('-'
	     ('0'..'9' | 'a'..'f' | 'A'..'F')
	     ('0'..'9' | 'a'..'f' | 'A'..'F')
             ('0'..'9' | 'a'..'f' | 'A'..'F')
             ('0'..'9' | 'a'..'f' | 'A'..'F')
             (('0'..'9' | 'a'..'f' | 'A'..'F') ('0'..'9' | 'a'..'f' | 'A'..'F'))?
	)?
	;

/** Comment opening */
CDO
	: '<!--'
	;

/** Comment closing */
CDC
	: '-->'
	;	

SEMICOLON
	: ';'
	;

COLON
	: ':'
	;
	
COMMA
    : ','
    ;

QUESTION
	: '?'
	;
	
PERCENT
	: '%'
	;

EQUALS
    : '='
    ;

SLASH
    : '/'
    ;

GREATER
    : '>'
    ;    	

LESS
    : '<'
    ;    	

LCURLY
	: '{'  {ls.curlyNest++;}
	;

RCURLY	
	: '}'  { if(ls.curlyNest>0) ls.curlyNest--;}
	;

APOS
	: '\'' { ls.aposOpen=!ls.aposOpen; }
	;

QUOT
	: '"'  { ls.quotOpen=!ls.quotOpen; }
	;
	
LPAREN
	: '('  {ls.parenNest++; }
	;

RPAREN
	: ')'  { if(ls.parenNest>0) ls.parenNest--; }
	;		

LBRACE
	: '['
	;

RBRACE
	: ']'
	;
	
EXCLAMATION
    : '!'
    ;	

MINUS
	: '-'
	;

PLUS
	: '+'
	;

ASTERISK
	: '*'
	;

/** White character */		
S
	: W_CHAR+
	;

COMMENT	
	: '/*' ( options {greedy=false;} : .)* '*/' { $channel = HIDDEN; }
	;

SL_COMMENT
	: '//' ( options {greedy=false;} : .)* ('\n' | '\r' ) { $channel=HIDDEN; }
	;		

/** Expression function */
EXPRESSION
  : 'expression(' { readExpressionContents(); }
  ;
  
/** Other Function beginning */	
FUNCTION
	: IDENT_MACR '('
	;

INCLUDES
	: '~='
	;

DASHMATCH
	: '|='
	;

INVALID_TOKEN
	: .
	;
	
/*********************************************************************
 * FRAGMENTS *
 *********************************************************************/

fragment 
IDENT_MACR
  	: NAME_START NAME_CHAR*
  	;

fragment 
NAME_MACR
 	: NAME_CHAR+
  	;

fragment 
NAME_START
  	: ('a'..'z' | 'A'..'Z' | '_' | NON_ASCII | ESCAPE_CHAR)
  	;     

fragment 
NON_ASCII
  	: ('\u0080'..'\uD7FF' | '\uE000'..'\uFFFD')
  	;

fragment 
ESCAPE_CHAR
 	: ('\\') 
 	  (
 	    (('0'..'9' | 'a'..'f' | 'A'..'F')
 	     ('0'..'9' | 'a'..'f' | 'A'..'F')
 	     ('0'..'9' | 'a'..'f' | 'A'..'F')
 	     ('0'..'9' | 'a'..'f' | 'A'..'F')
 	     (('0'..'9' | 'a'..'f' | 'A'..'F') ('0'..'9' | 'a'..'f' | 'A'..'F'))?
 	    )
 	     
 	   |('\u0020'..'\u007E' | '\u0080'..'\uD7FF' | '\uE000'..'\uFFFD')
 	  )
  	;

fragment 
NAME_CHAR
  	: ('a'..'z' | 'A'..'Z' | '0'..'9' | '-' | '_' | NON_ASCII | ESCAPE_CHAR)
  	;

fragment 
NUMBER_MACR
  	: ('0'..'9')+ | (('0'..'9')* '.' ('0'..'9')+)
  	;

fragment 
STRING_MACR
	: QUOT (STRING_CHAR | APOS {ls.aposOpen=false;} )* QUOT 
	| APOS (STRING_CHAR | QUOT {ls.quotOpen=false;} )* APOS
  	;

fragment
STRING_CHAR
	:  (URI_CHAR | ' ' | '(' | ')' | ('\\' NL_CHAR))
	;
  	
fragment
URI_MACR
	: URI_CHAR*
	;  	
  	
fragment
URI_CHAR
	: ('\u0009' | '\u0021' | '\u0023'..'\u0026' | '\u002A'..'\u007E')
	  | NON_ASCII | ESCAPE_CHAR
	;	

fragment 
NL_CHAR
  	: '\u000A' | '\u000D' '\u000A' | '\u000D' | '\u000C'
  	; 

fragment
W_MACR
	: W_CHAR*
	;

fragment 
W_CHAR
  	: '\u0009' | '\u000A' | '\u000C' | '\u000D' | '\u0020'
  	;
