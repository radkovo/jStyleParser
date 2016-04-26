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
	STYLESHEET,
	INLINESTYLE,
	ATBLOCK,
	CURLYBLOCK,
	PARENBLOCK,
	BRACEBLOCK,
	RULE,
	SELECTOR,
	ELEMENT,
	PSEUDOCLASS,
	PSEUDOELEM,
	ADJACENT,
	PRECEDING,
	CHILD,
	DESCENDANT,
	ATTRIBUTE,
	SET,
	DECLARATION,
	VALUE,
	MEDIA_QUERY,

	INVALID_STRING,
	INVALID_SELECTOR,
	INVALID_SELPART,
	INVALID_DECLARATION,
	INVALID_STATEMENT,
	INVALID_ATSTATEMENT,
	INVALID_IMPORT,
	INVALID_DIRECTIVE
}
@header{
    import cz.vutbr.web.csskit.antlr4.*;
}
@members{
    // CSSLexer.g4 members start
    int count = 0;
    private org.slf4j.Logger log;

    // number of already processed tokens (for checking the beginning of the style sheet)
    protected int tokencnt = 0;
    // current lexer state
    protected cz.vutbr.web.csskit.antlr4.CSSLexerState ls;

    /**
      * token facctory for generating custom tokens (CSSToken)
      */
    protected CSSTokenFactory tf;
    protected cz.vutbr.web.csskit.antlr4.CSSTokenRecovery tr;
    protected cz.vutbr.web.csskit.antlr4.CSSExpressionReader er;

    /**
     * This function must be called to initialize lexer's state.
     * Because we can't change directly generated constructors.
     */
    public void init() {
        this.log = org.slf4j.LoggerFactory.getLogger(getClass());
//        this.log.info("init called");
        this.ls = new cz.vutbr.web.csskit.antlr4.CSSLexerState();
        //initialize CSSTokenFactory
        this.tf = new CSSTokenFactory(_tokenFactorySourcePair, this, ls, getClass());
        this.tr = new cz.vutbr.web.csskit.antlr4.CSSTokenRecovery(this, _input, ls, log);
        this.er = new cz.vutbr.web.csskit.antlr4.CSSExpressionReader(_input, log);
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException();
    }

    /**
      * Overrides inputStream to avoid setting input
      * stream after construction
      */
    @Override
    public void setInputStream(IntStream input) {
        throw new UnsupportedOperationException();
    }
    //   anltrv3 old method
    //    @Override
    //    public void setCharStream(CharStream input) {
    //        throw new UnsupportedOperationException();
    //    }

    /**
     * Overrides next token to match includes and to
     * recover from EOF
     */
	@Override
    public Token nextToken(){
       Token token = tr.nextToken();
       //count non-empty tokens for eventual checking of the style sheet start
       if (token.getType() == S) {
           tokencnt++;
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
        Token t = tf.make();
        emit(t);
        return t;
	}

//	@Override
    public void emitErrorMessage(String msg) {
    	log.info("ANTLR: {}", msg);
    }

    /**
     * Does special token recovery for some cases
     */
//    @Override
    public void recover(RecognitionException re) {
        log.info("ANTLR recover");
//        if (!tr.recover())
//            super.recover(re);
    }
    // CSSLexer.g4 members end
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
//@init {
//	System.out.println("eee");
//  tr.expecting(CHARSET);
//}
//@after {
//	tr.end();
//}

	: '@charset' S* STRING_MACR S* SEMICOLON
	  {
	    // we have to trim manually
	    String enc = cz.vutbr.web.csskit.antlr4.CSSToken.extractSTRING(getText());
	    if (tokencnt <= 1) //we are at the beginning of the style sheet
	    {
            try {
                log.warn("Changing charset to {}", enc);
                ((cz.vutbr.web.csskit.antlr4.CSSInputStream) _input).setEncoding(enc);
                //input = setCharStream(new ANTLFileStream(input.getSourceName(), enc));
            }
            catch(java.nio.charset.IllegalCharsetNameException icne) {
                log.warn("Could not change to unsupported charset!", icne);
                throw new RuntimeException(new cz.vutbr.web.css.CSSException("Unsupported charset: " + enc));
            }
            catch (java.io.IOException e) {
                log.warn("Could not change to unsupported charset!", e);
            }
        }
        else{
            log.warn("Ignoring @charset rule not at the beginning of the style sheet");
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
//@init{
//	tr.expecting(STRING);
//}
//@after {
//	tr.end();
//}
	: STRING_MACR
	;
UNCLOSED_STRING
	: UNCLOSED_STRING_MACR
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
	: '{'
	{ls.curlyNest++;}
	;

RCURLY	
	: '}'
	{ if(ls.curlyNest>0) ls.curlyNest--;}
	;

APOS
	: '\''
	{ ls.aposOpen=!ls.aposOpen; }
	;

QUOT
	: '"'
	{ ls.quotOpen=!ls.quotOpen; }
	;
	
LPAREN
	: '('
	{ls.parenNest++; }
	;

RPAREN
	: ')'
	{ if(ls.parenNest>0) ls.parenNest--; }
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
    : '/*' .*? '*/'
	;

SL_COMMENT
	: '//' .*? ('\n' | '\r' ) -> channel(HIDDEN)
	;

/** Expression function */
EXPRESSION
  : 'expression('
//  { er.read(); }
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
 	    (('0'..'9' | 'a'..'f' | 'A'..'F')+W_CHAR?)
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

//fragment - commented due ANTLR4 error  | fragment rule STRING_MACR contains an action or command which can never be executed
STRING_MACR
    : QUOT (STRING_CHAR | APOS {ls.aposOpen=false;} )* QUOT
    | APOS (STRING_CHAR | QUOT {ls.quotOpen=false;} )* APOS
    ;
UNCLOSED_STRING_MACR
    : QUOT (STRING_CHAR | APOS {ls.aposOpen=false;} )*
    | APOS (STRING_CHAR | QUOT {ls.quotOpen=false;} )*
    ;

//fragment
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
