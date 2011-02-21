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
}

@lexer::header {
package cz.vutbr.web.csskit.antlr;

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
        
        /**
         * Checks whether all pair characters (single and double quotatation marks,
         * curly braces are balanced
		*/ 
        public boolean isBalanced() {
        	return aposOpen==false && quotOpen==false && curlyNest==0 && parenNest==0;
        }
        
        public boolean isFunctionBalanced() {
        	return parenNest==0;
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
	 * Adds contextual information about nesting into token.
	 */
    @Override
	public Token emit() {
		CSSToken t = new CSSToken(input, state.type, state.channel,
                        state.tokenStartCharIndex, getCharIndex()-1);
        t.setLine(state.tokenStartLine);
        t.setText(state.text);
        t.setCharPositionInLine(state.tokenStartCharPositionInLine);
        
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
  private Object invalidFallbackGreedyFunction(int ttype, String ttext, BitSet follow, RecognitionException re) {
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
    consumeUntilGreedyFunction(input, follow);
    endResync();
    return invalidReplacement(ttype, ttext);
    
    }
  
  /**
   * Consumes token until lexer state is function-balanced and
   * token from follow is matched. Matched token is also consumed
   */ 
  private void consumeUntilGreedyFunction(TokenStream input, BitSet follow) {
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
    }while(!(t.getLexerState().isFunctionBalanced() && follow.member(t.getType())));
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
	: ( CDO | CDC | S | statement )* 
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
      log.trace("GOTCHA2");
      	final BitSet follow = BitSet.of(CSSLexer.RCURLY, CSSLexer.SEMICOLON);								
	    retval.tree = invalidFallbackGreedy(CSSLexer.INVALID_STATEMENT, 
	  		"INVALID_STATEMENT", follow, re);							
	}

declarations
	: declaration? (SEMICOLON S* declaration? )*
	  -> ^(SET declaration*)
	;
  catch [RecognitionException re] {
        final BitSet follow = BitSet.of(CSSLexer.RCURLY, CSSLexer.SEMICOLON);               
      retval.tree = invalidFallbackGreedy(CSSLexer.INVALID_DECLARATION, "INVALID_DECLARATION", follow, re);             
  }

declaration
	: property COLON S* terms important? -> ^(DECLARATION important? property terms)
	| noprop any* -> INVALID_DECLARATION /* if first character in the declaration is invalid (various dirty hacks) */
	;
	catch [RecognitionException re] {
	  retval.tree = invalidFallback(CSSLexer.INVALID_DECLARATION, "INVALID_DECLARATION", re);									
	}

important
    : EXCLAMATION S* 'important' S* -> IMPORTANT
    ;
  catch [RecognitionException re] {
      log.trace("GOTCHA1");
        final BitSet follow = BitSet.of(CSSLexer.RCURLY, CSSLexer.SEMICOLON);               
      retval.tree = invalidFallbackGreedy(CSSLexer.INVALID_DECLARATION, "INVALID_DECLARATION", follow, re);
      //TODO: tady asi nema byt Greedy - preskakuje nam to strednik?             
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
        retval.tree = invalidFallbackGreedyFunction(CSSLexer.INVALID_STATEMENT, 
          "INVALID_STATEMENT", follow, re);
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
      | funct -> funct 
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
	: COLON (IDENT | FUNCTION S*  IDENT S* RPAREN)
	 -> ^(PSEUDO FUNCTION? IDENT)
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
	    try {
        	String defaultEnc = Charset.defaultCharset().name();
            if(!enc.equalsIgnoreCase(defaultEnc) && Charset.isSupported(enc)) {
            	log.warn("Should change encoding to \"{}\"", enc);
              			
              	// FIXME how to solve string and not file inputs?
              	// we can't just easily create new stream
              	// how to avoid infinite loop on changing stream
            	//input = setCharStream(new ANTLFileStream(input.getSourceName(), enc));
            }
            // charset already set
            else {
            	log.info("Already using correct charset (\"{}\") for stylesheet", enc);
            }
        }
        catch(IllegalCharsetNameException icne) {
        	log.warn("Could not change to unsupported charset!", icne);
        	throw new RuntimeException(new CSSException("Unsupported charset: " + enc));
        }
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
              	    url = new URL(base, fileName);
              	else
              	{
              	    log.warn("Base URL is unknown");
              	    url = new URL(fileName);
              	}
              	               			
              	log.debug("Actually, will try to import file \"{}\"", url.toString());	
              			
                // save current lexer's stream
                LexerStream stream = new LexerStream(input, ls);
                imports.push(stream);
                    	
                CSSToken t = new CSSToken(IMPORT, ls);
                t.setText(media.toString());
                    	
                // switch on new stream
                setCharStream(CSSInputStream.urlStream(url));
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
	: '@' IDENT_MACR
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
