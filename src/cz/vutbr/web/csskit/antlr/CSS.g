grammar CSS;

options {
	output = AST;
	k = 2;
}

tokens {
	STYLESHEET;
	ATRULE;
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
	DECLARATION;	
	VALUE;
	IMPORTANT;
	
	INVALID_CHARSET;
	INVALID_STRING;
	INVALID_DECLARATION;
	INVALID_ATRULE;
	INVALID_ATBLOCK;
}

@lexer::header {
package cz.vutbr.web.csskit.antlr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StyleSheetNotValidException;

}

@lexer::members {
    private static Logger log = LoggerFactory.getLogger(CSSLexer.class);
    
    public static class LexerState {
        public short curlyNest;
        public boolean quotOpen;
        public boolean aposOpen;
        
        public LexerState() {
        	this.curlyNest = 0;
        	this.quotOpen = false;
        	this.aposOpen = false;
        }
        
        public LexerState(LexerState clone) {
        	this();
            this.curlyNest = clone.curlyNest;
            this.quotOpen = clone.quotOpen;
            this.aposOpen = clone.aposOpen;
        }
        
        /**
         * Checks whether all pair characters (single and double quotatation marks,
         * curly braces are balaneced.
         */ 
        public boolean isBalanced() {
        	return aposOpen==false && quotOpen==false && curlyNest==0;
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
        	  .append(", '=").append(aposOpen ? "1" : "0")
        	  .append(", \"=").append(quotOpen ? "1" :"0");
        	 
        	return sb.toString();  
        }
    }
    
    private class LexerStream {
    
    	public CharStream input;
    	public int mark;
    	public LexerState ls;
    	
    	public LexerStream(CharStream input) {
    	    this.input = input;
    	    this.mark = input.mark();
    	    this.ls = new LexerState();
    	}
    }
    
    // lexer states for imported files
    private Stack<LexerStream> imports;
    
    // current lexer state
    private LexerState ls;
    
    // stylesheet instance
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
       if(token==Token.EOF_TOKEN && !imports.empty()){
        // We've got EOF and have non empty stack.
         LexerStream stream = imports.pop();
         setCharStream(stream.input);
         input.rewind(stream.mark);
         this.ls = stream.ls;
         
         token = nextTokenRecover();
       }       

      // Skip first token after switching on another input.
      // You need to use this rather than super as there may be nested include files
       if(((CommonToken)token).getStartIndex() < 0)
         token = this.nextToken();
        
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
    		case CHARSET:
    			final BitSet charsetFollow = BitSet.of((int) '}');
    			consumeUntilBalanced(charsetFollow);
    			//state.token = (Token) new CSSToken(INVALID_CHARSET, new LexerState(ls));
    			break;
    		case STRING:
    			// eat character which should be newline
    			if(log.isTraceEnabled())
    				log.trace("Lexer consumes '{}' while recovering in STRING", 
    					Character.toString((char)input.LA(1)));
    			input.consume();
    			// set back to uninitialized state
    			ls.quotOpen = false;
    			ls.aposOpen = false;
    			state.token = (Token) new CSSToken(INVALID_STRING, 
        					"INVALID_STRING", new LexerState(ls), 
        					state.tokenStartCharIndex, getCharIndex() -1);
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
    public void consumeUntilBalanced(BitSet follow) {

    	log.debug("Lexer entered consumeUntilBalanced with {} and follow {}", 
    		ls, follow);
    
    	int c;
		do {
    		c = input.LA(1);
    		// change apostrophe state
    		if(c=='\'' && ls.quotOpen==false) {
    			ls.aposOpen = !ls.aposOpen;
    		}
    		else if (c=='"' && ls.aposOpen==false) {
    			ls.quotOpen = !ls.quotOpen;
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
    			log.warn("Unexpected EOF during consumeUntilBalanced, EOF not consumed");
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
}

@parser::members {
    private static Logger log = LoggerFactory.getLogger(CSSParser.class);
    
    private StyleSheet stylesheet;
    
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
		  t= (CSSToken) input.LT(1);
		  log.trace("Skipped greedy: {}", t);
		  // consume token even if it will match
		  input.consume();
		}while(!(t.getLexerState().isBalanced() && follow.member(t.getType())));
		
	} 

}


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
	| PAGE S* COLON IDENT S* block -> ^(PAGE IDENT block)
	| MEDIA S* medias? block -> ^(MEDIA medias? block)	
	| ATKEYWORD S* atblock -> ^(ATKEYWORD atblock)
	;
	
atblock
	: (COLON IDENT S* | medias)? (block | SEMICOLON)
	  -> ATBLOCK
	;
	catch [RecognitionException re] {
      	final BitSet follow = BitSet.of(CSSLexer.RCURLY);								
	    retval.tree = invalidFallbackGreedy(CSSLexer.INVALID_ATBLOCK, 
	  		"INVALID_ATBLOCK", follow, re);							
	}
	
medias
	: IDENT S* (COMMA S* IDENT S*)* 
		-> IDENT+
	;		  	
	
block       
	: LCURLY S* 
		blockpart* 
	  RCURLY 
	  -> ^(CURLYBLOCK blockpart*)
	;

blockpart
    : ruleset S* -> ruleset
    | LCURLY S* declaration? (SEMICOLON S* declaration? )* RCURLY S* -> ^(CURLYBLOCK declaration*)
    | (ATKEYWORD S*) -> ATKEYWORD
    ;
  	
	
ruleset
@after {
}
	: combined_selector (COMMA S* combined_selector)* 
	  LCURLY S* 
	  	declaration? (SEMICOLON S* declaration? )* 
	  RCURLY
	  -> ^(RULE combined_selector+ declaration*)
	;

declaration
	: property COLON S* terms important? -> ^(DECLARATION important? property terms)
	;
	catch [RecognitionException re] {
	  retval.tree = invalidFallback(CSSLexer.INVALID_DECLARATION, "INVALID_DECLARATION", re);									
	}

important
    : EXCLAMATION S* 'important' S* -> IMPORTANT
    ;	
	
property    
	: IDENT S* -> IDENT
	;
	
terms	       
	: term+
	-> ^(VALUE term+)
	;
	
term
    : valuepart -> valuepart
    | LCURLY S* (any | SEMICOLON S*)* RCURLY -> CURLYBLOCK
    | ATKEYWORD S* -> ATKEYWORD
    ;	

valuepart
    : ( IDENT -> IDENT
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
      | EQUALS -> EQUALS
      | SLASH -> SLASH
	  | PLUS -> PLUS
	  | ASTERISK -> ASTERISK		 
      | FUNCTION S* terms RPAREN -> ^(FUNCTION terms) 
      | DASHMATCH -> DASHMATCH
      | LPAREN valuepart* RPAREN -> ^(PARENBLOCK valuepart*)
      | LBRACE valuepart* RBRACE -> ^(BRACEBLOCK valuepart*)
    ) !S*
  ;

combined_selector    
	: selector ((combinator) selector)*
	;

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

selpart	
    : COLON IDENT -> PSEUDO IDENT
    | HASH
    | CLASSKEYWORD
	| LBRACE S* attribute RBRACE -> ^(ATTRIBUTE attribute)
    | COLON FUNCTION S* IDENT RPAREN -> ^(FUNCTION IDENT)
    ;
	

attribute
	: IDENT S*
	  ((EQUALS | INCLUDES | DASHMATCH) S* (IDENT | string) S*)?
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
	    String enc = s.getText().substring(1, s.getText().length()-1);
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
            // set charset
            stylesheet.setCharset(enc);
        }
        catch(IllegalCharsetNameException icne) {
        	log.warn("Could not change to unsupported charset!", icne);
        	throw new RuntimeException(new StyleSheetNotValidException("Unsupported charset: " + enc));
        }
	  }
	;

IMPORT
	: '@import'
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

EQUALS
    : '='
    ;

SLASH
    : '/'
    ;

GREATER
    : '>'
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
	: '('
	;

RPAREN
	: ')'
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
	
/** Function beginning */	
FUNCTION
	: IDENT_MACR '('
	;

INCLUDES
	: '~='
	;

DASHMATCH
	: '|='
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
  	: ('a'..'z' | 'A'..'Z' | NON_ASCII | ESCAPE_CHAR)
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
  	: ('a'..'z' | 'A'..'Z' | '0'..'9' | '-' | NON_ASCII | ESCAPE_CHAR)
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
	:  (URI_CHAR | ' ' | ('\\' NL_CHAR))
	;
  	
fragment
URI_MACR
	: URI_CHAR*
	;  	
  	
fragment
URI_CHAR
	: ('\u0009' | '\u0021' | '\u0023'..'\u0026' | '\u0028'..'\u007E')
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
