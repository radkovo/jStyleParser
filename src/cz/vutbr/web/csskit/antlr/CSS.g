grammar CSS;

options {
	output = AST;
	k = 4;
}

tokens {
	STYLESHEET;
    	
    	CHARSET;
	IMPORT;
	PAGE;
	MEDIA;
	BLOCK;
	RULE;
	
	ATTRIB;
	
	SOADJACENT;
	SOCHILD;
	SODESCENDANT;
	SOSTANDALONE;
	
	AOEQUALS;
    	AOINCLUDES;
    	AODASHMATCH;
	
	PSEUDO;
	PROPERTY;
	FUNCTION;
	IMPORTANT;
	URI;
	
	TAG;
	UNIVERSAL;
	ID;
	CLASS;
	
	TOCOMMA;
	TOSLASH;
	TOSPACE;
}



@parser::header { 
package cz.vutbr.web.csskit.antlr;
}

@lexer::header {
package cz.vutbr.web.csskit.antlr;
}


stylesheet 
	: (ruleset | atblock)*
	-> ^(STYLESHEET atblock* ruleset*)
	;

atblock	
	: '@' atkeyword BLANK? specifier? ( block | SEMICOLON ) 
	-> ^(atkeyword specifier? block?)
	;

atkeyword
	: 'charset' -> CHARSET
	| 'import' -> IMPORT
	| 'page' -> PAGE
	| 'media' -> MEDIA
	| ident -> BLOCK
	;

specifier
	: ident (COMMA ident) -> ident+	
	| string
	| uri
	;

block	
	: LCURLY properties RCURLY -> properties
	;	

ruleset
 	: selectors block -> ^( RULE selectors block)
	;
	
selectors
	: selector (selectorop selector )* -> (selector selectorop*)+
	;
	
selector
	: element ( elhash | elclass | attrib | pseudo)*
	;
	
selectorop
	: ADJACENT -> SOADJACENT
	| CHILD -> SOCHILD
	| BLANK -> SODESCENDANT
	| COMMA -> SOSTANDALONE
	;
	
element
	: ident -> ^( TAG ident )
	| '*' -> ^(UNIVERSAL)
	| -> ^(UNIVERSAL)
	;

elhash	
	: HASH -> ^(ID HASH )
	;		

elclass
	: '.' ident -> ^(CLASS ident)
	;	

pseudo
	: (':'|'::') ident -> ^( PSEUDO ident )
	| (':'|'::') function -> ^( PSEUDO function )
	;

attrib
	: '[' ident (attribRelate (string | ident))? ']' -> ^( ATTRIB ident (attribRelate string* ident*)? )
	;
	
attribRelate
	: '='  -> AOEQUALS
	| '~=' -> AOINCLUDES
	| '|=' -> AODASHMATCH
	;	

properties
	: declaration (SEMICOLON declaration? )* ->  declaration+
	;
  
declaration
	: ident COLON terms priority? -> ^( PROPERTY ident priority? terms )
	;

priority
	: '!' BLANK? 'important' BLANK? -> IMPORTANT
	;

terms
	: term (termop term)* 
	;

termop	
	: COMMA -> TOCOMMA
	| SLASH -> TOSLASH
	| BLANK -> TOSPACE
	;
	

term 
	: LENGTH
	| NUMBER
	| PERCENTAGE
	| ANGLE
	| TIME
	| FREQ
	| HASH
	| ident
	| uri
	| string
	| function
	;

ident
	: IDENT 
	;

string
	: STRING 
	;

uri
	: 'url' LPAREN 
		(u=URI_STRING | u=STRING ) 
		RPAREN -> ^(URI $u)
	;
	
function
	: ident LPAREN terms? RPAREN -> ^( FUNCTION ident terms? )
	;


/****************************************************************************
 * TOKENS *
 ****************************************************************************/

HASH
	: '#' IDENT_CHAR* 
	;


IDENT
	:	IDENT_START IDENT_CHAR*
	;	
	
COLON
	: SPACE* ':' SPACE*
	;	
	
COMMA
	: SPACE* ',' SPACE* 
	;	
	
SLASH
	: SPACE* '/' SPACE*
	;
		
LCURLY
	: SPACE* '{' SPACE*
	;

RCURLY
	: SPACE* '}' SPACE*
	;

LPAREN
	: SPACE* '(' SPACE*
	;

RPAREN
	: SPACE* ')' SPACE*
	;		
	
SEMICOLON
	: SPACE* ';' SPACE*
	;

ADJACENT
	: SPACE* '+' SPACE*
	;

CHILD
	: SPACE* '>' SPACE*
	;
	
	
URI_STRING
	: URI_CHAR*
	;

NUMBER
	: DIGIT+ 
	| DIGIT* '.' DIGIT+ 
	;

LENGTH
	:	NUMBER LENGTH_UNIT
	;
	
ANGLE
	: NUMBER ANGLE_UNIT
	;	

FREQ
	: NUMBER FREQ_UNIT
	;

TIME
	: NUMBER TIME_UNIT
	;
	
PERCENTAGE
	: NUMBER PERCENT
	;	
	
BLANK
	: SPACE+
	;

SL_COMMENT
	:	'//' ( options {greedy=false;} : .)* ('\n' | '\r' ) { $channel=HIDDEN; }
	;
	

COMMENT
	:	'/*' ( options {greedy=false;} : .)* '*/' { $channel = HIDDEN; }
	;

STRING
	: '"' STRING_V1_CHAR* '"' | '\'' STRING_V2_CHAR* '\''
	;	


/*********************************************************************
 * FRAGMENTS *
 *********************************************************************/

fragment LENGTH_UNIT
	: (PX | CM | MM | IN | PT | PC | EM | EX)
	;
	
fragment ANGLE_UNIT
	: (DEG | RAD | GRAD)
	;
	
fragment FREQ_UNIT
	: ( HZ | KHZ)
	;

fragment TIME_UNIT
	: ( MS | S)
	;

fragment DIGIT
	: ('0'..'9') 
	;

fragment HEXDIGIT
	: ('0'..'9'|'a'..'f'|'A'..'F')
	;
 
fragment PERCENT
	: '%'
	;

fragment PX
	: 'px'
	;	 

fragment CM
	: 'cm'
	; 

fragment MM
	: 'mm'
	;

fragment IN
	: 'in'
	;

fragment PT
	: 'pt'
	;

fragment PC
	: 'pc'
	;

fragment EM
	: 'em'
	;

fragment EX
	: 'ex'
	;	

fragment DEG
	: 'deg'
	;

fragment RAD
	: 'rad'
	;

fragment GRAD
	: 'grad'
	;

fragment MS
	: 'ms'
	;

fragment S
	: 's'
	;

fragment HZ
	: 'hz'
	;

fragment KHZ
	: 'khz'
	;

fragment SPACE
	: (' ' | '\t' | '\r' | '\n' | '\f')
	;


fragment ESC
	: '\\' (UNICODE_ESC |'b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
	;	

fragment UNICODE_ESC
	: 'u' HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT
	;

fragment NON_ASCII
	: ('\u0100'..'\ufffe')
	;
	
fragment IDENT_START
	: 	('_' | 'a'..'z'| 'A'..'Z' | NON_ASCII | ESC)
	;
	
fragment IDENT_CHAR
	:	( IDENT_START | '-' | '0'..'9')
	;
	
	
fragment URI_CHAR
	:   ('!'|'#'|'$'|'%'|'&'|'*'|'-'|'~'|'/'|':'| NON_ASCII | ESC)
	;
	
fragment STRING_V1_CHAR
	:	(~( '\r' | '\n' | '\f' | '"') | ESC)
	;
	
fragment STRING_V2_CHAR
	: 	(~( '\r' | '\n' | '\f' | '\'') | ESC)
	;	
	
