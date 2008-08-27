grammar CSS;

options {
	output = AST;
	k = 4;
}

tokens {
	STYLESHEET;
	ATBLOCK;
	CURLYBLOCK;
	PARENBLOCK;
	BRACEBLOCK;
	RULE;	
	SELECTOR;
	DECLARATION;	
	VALUE;
}



@parser::header { 
package cz.vutbr.web.csskit.antlr;
}

@lexer::header {
package cz.vutbr.web.csskit.antlr;
}

@parser::members {

    public String toStringTree(CommonTree tree) {
        StringBuilder sb = new StringBuilder();
        rec(tree, sb, 0);
        return sb.toString();       
    }
    
    private void rec(CommonTree tree, StringBuilder sb, int nest) {
        if(tree.getChildCount()==0) {
            addTree(sb, tree, nest);
            return;
        }
            
        if(!tree.isNil()) {
            addTree(sb, tree, nest);
        }
       
        for(int i=0; i < tree.getChildCount(); i++) {
            CommonTree n = (CommonTree) tree.getChild(i);
            rec(n, sb, nest+1);
        }
        if(!tree.isNil()) {
            sb.append(")");
        }
    
    }
    
    private StringBuilder addTree(StringBuilder sb, CommonTree tree, int nest) {
        sb.append("\n");
        for(int i=0; i< nest; i++) {
            sb.append("  ");
        }
        
        if(!tree.isNil())
          sb.append("(");
        
        sb.append(tree.toString()).append(" |")
          .append(tree.getType()).append("| ");
        
        return sb;
    }

}


stylesheet  
	: ( CDO | CDC | S | statement )* 
	-> ^(STYLESHEET statement*)
	;
	
statement   
	: ruleset | atrule
	;
	
atrule     
	: ATKEYWORD S* 
	  any* 
	  ( block | SEMICOLON S* )
	  -> ^(ATBLOCK ATKEYWORD any* block?)
	;
	
block       
	: LCURLY S* 
		blockpart* 
	  RCURLY S*
	  -> ^(CURLYBLOCK blockpart*)
	;

blockpart
    : any -> any 
    | block -> block 
    | (ATKEYWORD S*) -> ATKEYWORD 
    | (SEMICOLON S*) -> SEMICOLON
    ;
  	
	
ruleset     
	: selector? 
	  LCURLY S* 
	  	declaration? (SEMICOLON S* declaration? )* 
	  RCURLY S*
	  -> ^(RULE selector* declaration*)
	;
	
selector    
	: selpart+ -> ^(SELECTOR selpart+)
	;

declaration 
	: property COLON S* terms -> ^(DECLARATION property terms)
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
    | block -> block
    | ATKEYWORD S* -> ATKEYWORD
    ;	

valuepart
    : ( IDENT -> IDENT
      | IDKEYWORD -> IDKEYWORD
      | NUMBER -> NUMBER
      | PERCENTAGE ->PERCENTAGE
      | DIMENSION -> DIMENSION
      | STRING -> STRING
      | URI    -> URI
      | HASH -> HASH
      | UNIRANGE -> UNIRANGE
      | INCLUDES -> INCLUDES
      | COLON -> COLON
      | COMMA -> COMMA
      | GREATER -> GREATER
      | EQUALS -> EQUALS
      | SLASH -> SLASH
      | FUNCTION S* terms RPAREN -> ^(FUNCTION terms) 
      | DASHMATCH -> DASHMATCH
      | LPAREN any* RPAREN -> ^(PARENBLOCK any*)
      | LBRACE any* RBRACE -> ^(BRACEBLOCK any*)
    ) !S*
  ;

selpart
    : ( IDENT -> IDENT
      | IDKEYWORD -> IDKEYWORD
      | NUMBER -> NUMBER
      | PERCENTAGE ->PERCENTAGE
      | DIMENSION -> DIMENSION
      | STRING -> STRING
      | URI    -> URI
      | HASH -> HASH
      | UNIRANGE -> UNIRANGE
      | INCLUDES -> INCLUDES
      | COLON -> COLON
      | COMMA -> COMMA
      | GREATER -> GREATER
      | EQUALS -> EQUALS
      | SLASH -> SLASH
      | FUNCTION S* any* RPAREN -> ^(FUNCTION any*) 
      | DASHMATCH -> DASHMATCH
      | LPAREN any* RPAREN -> ^(PARENBLOCK any*)
      | LBRACE any* RBRACE -> ^(BRACEBLOCK any*)
    ) !S*
  ;
	
any
	: ( IDENT -> IDENT
	  | IDKEYWORD -> IDKEYWORD
	  | NUMBER -> NUMBER
	  | PERCENTAGE ->PERCENTAGE
	  | DIMENSION -> DIMENSION
	  | STRING -> STRING
      | URI    -> URI
      | HASH -> HASH
      | UNIRANGE -> UNIRANGE
      | INCLUDES -> INCLUDES
      | COLON -> COLON
      | COMMA -> COMMA
      | GREATER -> GREATER
      | EQUALS -> EQUALS
      | SLASH -> SLASH
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

/** Keyword beginning with '@' */
ATKEYWORD
	: '@' IDENT_MACR
	;

IDKEYWORD
    : '.' IDENT_MACR
    ;

/** String including 'decorations' */
STRING
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
	: '{'
	;

RCURLY	
	: '}'
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
	: '"' (STRING_CHAR | '\'')* '"' 
	| '\'' (STRING_CHAR | '"')* '\''
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
