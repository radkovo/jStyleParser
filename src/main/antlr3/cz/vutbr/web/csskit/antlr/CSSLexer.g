/*
 * CSS.g 
 * Copyright (c) 2008 Karel Piwko
 * Copyright (c) 2008-2014 Radek Burget
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
lexer grammar CSSLexer;

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
	PSEUDOCLASS;
	PSEUDOELEM;
	ADJACENT;
	PRECEDING;
	CHILD;
	DESCENDANT;
	ATTRIBUTE;
	SET;
	DECLARATION;	
	VALUE;
	MEDIA_QUERY;
	
	INVALID_STRING;
	INVALID_SELECTOR;
	INVALID_SELPART;
	INVALID_DECLARATION;
	INVALID_STATEMENT;
	INVALID_IMPORT;
	INVALID_DIRECTIVE;
}

@header {
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

@members {
    

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
         * @param mode the desired recovery node
         * @param state the required lexer state (used for DECL mode)
         * @param t the token that is being processed (used for DECL mode)
         */ 
        public boolean isBalanced(RecoveryMode mode, LexerState state, CSSToken t)
        {
            if (mode == RecoveryMode.BALANCED)
                return aposOpen==false && quotOpen==false && curlyNest==0 && parenNest==0;
            else if (mode == RecoveryMode.FUNCTION)
                return parenNest==0;
            else if (mode == RecoveryMode.RULE)
                return aposOpen==false && quotOpen==false && parenNest==0;
            else if (mode == RecoveryMode.DECL)
            {
                if (t.getType() == RCURLY) //if '}' is processed the curlyNest has been already decreased 
                    return aposOpen==false && quotOpen==false && parenNest==0 && curlyNest==state.curlyNest-1;
                else
                    return aposOpen==false && quotOpen==false && parenNest==0 && curlyNest==state.curlyNest;
            }
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
    
    // token recovery
    private Stack<Integer> expectedToken;
    
    /**
     * This function must be called to initialize lexer's state.
     * Because we can't change directly generated constructors.
     */
    public CSSLexer init() {
	    this.imports = new Stack<LexerStream>();
	    this.expectedToken = new Stack<Integer>();
	    this.ls = new LexerState();
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
       if(token.getType()==Token.EOF && !ls.isBalanced()) {
           CSSToken t = ls.generateEOFRecover(); 
           return (Token) t;
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
				return getEOFToken();
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
    
/////////////////////////////////////////////////////////////////////////////////
// TOKENS //
/////////////////////////////////////////////////////////////////////////////////

IMPORTANT
    : 'important'
    ;

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
	: '@import' 
	;

MEDIA
	: '@media'
	;

PAGE
	: '@page'
	;

MARGIN_AREA
  : '@top-left-corner'
  | '@top-left'
  | '@top-center'
  | '@top-right'
  | '@top-right-corner'
  | '@bottom-left-corner'
  | '@bottom-left'
  | '@bottom-center'
  | '@bottom-right'
  | '@bottom-right-corner'
  | '@left-top'
  | '@left-middle'
  | '@left-bottom'
  | '@right-top'
  | '@right-middle'
  | '@right-bottom'
  ;

VIEWPORT
  : '@viewport'
	;

FONTFACE
  : '@font-face'
  ;

/** Keyword beginning with '@' */
ATKEYWORD
	: '@' MINUS? IDENT_MACR?
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
	: POUND NAME_MACR	
	;

/** An element index in the an+b form */
INDEX
  : INTEGER_MACR? ('N' | 'n') (S* (PLUS | MINUS) S* INTEGER_MACR)?
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

TILDE
  : '~'
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

POUND
	: '#'
	;

AMPERSAND
	: '&'
	;

HAT
	: '^'
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

STARTSWITH
  : '^='
  ;
  
ENDSWITH
  : '$='
  ;
  
CONTAINS
  : '*='
  ;

CTRL
  : CTRL_CHAR+
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
 	    (('0'..'9' | 'a'..'f' | 'A'..'F')+)
 	    |('\u0020'..'\u002F' | '\u003A'..'\u0040' | '\u0047'..'\u0060' | '\u0067'..'\u007E' | '\u0080'..'\uD7FF' | '\uE000'..'\uFFFD')
 	  )
  	;

fragment 
NAME_CHAR
  	: ('a'..'z' | 'A'..'Z' | '0'..'9' | '-' | '_' | NON_ASCII | ESCAPE_CHAR)
  	;

fragment 
INTEGER_MACR
    : ('0'..'9')+
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
  	: '\u0009' | '\u000A' | '\u000B' | '\u000C' | '\u000D' | '\u0020'
  	;

fragment
CTRL_CHAR
    : '\u0000'..'\u0008' | '\u000E'..'\u001F'
    ;
