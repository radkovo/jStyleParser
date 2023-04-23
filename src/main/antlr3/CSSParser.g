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
parser grammar CSSParser;

options {
	output = AST;
	tokenVocab=CSSLexer;
	k = 2;
}

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////// P A R S E R /////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

@members {
    private org.slf4j.Logger log;
    
    private int functLevel = 0;
    
    protected cz.vutbr.web.csskit.antlr.CSSTreeNodeRecovery tnr;
    
    /**
     * This function must be called to initialize parser's state.
     * Because we can't change directly generated constructors.
     */
    public void init() {
        this.log = org.slf4j.LoggerFactory.getLogger(getClass());
        this.tnr = new cz.vutbr.web.csskit.antlr.CSSTreeNodeRecovery(this, input, state, adaptor, log);
    }
    
    private boolean emitErrorAsDebugMessage = false;

    @Override
    public void emitErrorMessage(String msg) {
    	if (emitErrorAsDebugMessage)
    	    log.debug("ANTLR: {}", msg);
    	else
    	    log.warn("ANTLR: {}", msg);
	}    

    @Override
    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
        cz.vutbr.web.csskit.antlr.CSSToken location = null; {
            if (e.token instanceof cz.vutbr.web.csskit.antlr.CSSToken)
                location = (cz.vutbr.web.csskit.antlr.CSSToken)e.token;
        }
        emitErrorMessage(
            (location != null ? ("at " + cz.vutbr.web.css.SourceLocator.toString(location.getSourceLocator()) + ": ") : "")
            + getErrorMessage(e, tokenNames));
    }

  /**
   * Obtains the current lexer state from current token
   */
  private cz.vutbr.web.csskit.antlr.CSSLexerState getCurrentLexerState(Token t)
  {
      if (t instanceof cz.vutbr.web.csskit.antlr.CSSToken)
          return ((cz.vutbr.web.csskit.antlr.CSSToken) t).getLexerState();
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
	| IMPORT S* import_uri S* media? SEMICOLON
	  -> ^(IMPORT media? import_uri)  
	| XSLT S* import_uri S*
	  ( SEMICOLON
	  | LCURLY S* declarations RCURLY
	  )
	  -> ^(XSLT import_uri declarations?)
	| namespace
	| page
  | VIEWPORT S*
    LCURLY S* declarations
    RCURLY -> ^(VIEWPORT declarations)
	| FONTFACE S*
	  LCURLY S* declarations
	  RCURLY -> ^(FONTFACE declarations)
	| MEDIA S* media? 
		LCURLY S* (media_rule S*)* RCURLY -> ^(MEDIA media? media_rule*)	
	| unknown_atrule
	;
	catch [RecognitionException re] {
       final BitSet follow = BitSet.of(RCURLY, SEMICOLON);
       try {
           emitErrorAsDebugMessage = true;
           retval.tree = tnr.invalidFallbackGreedy(INVALID_ATSTATEMENT, "INVALID_ATSTATEMENT", follow, re);
       } finally {
           emitErrorAsDebugMessage = false;
       }
	}

import_uri
  : (STRING | URI)
  ;

namespace
    : NAMESPACE S* (namespace_prefix S*)? namespace_uri S* SEMICOLON
        -> ^(NAMESPACE namespace_prefix? namespace_uri)
    ;

namespace_prefix
    : IDENT
    ;

namespace_uri
    : (STRING | URI)
    ;

page
	: PAGE S* (( IDENT | IDENT page_pseudo | page_pseudo) S*) ?
		LCURLY S*
		declarations margin_rule*
		RCURLY
		-> ^(PAGE IDENT? page_pseudo? declarations ^(SET margin_rule*))
	;

page_pseudo
	: pseudocolon^ IDENT
	;

margin_rule
	: MARGIN_AREA S* LCURLY S* declarations RCURLY S* -> ^(MARGIN_AREA declarations)
	;

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
 : media_query (COMMA S* media_query)*
    -> ^(MEDIA_QUERY media_query)+
 ;
 catch [RecognitionException re] {
     final BitSet follow = BitSet.of(COMMA, LCURLY, SEMICOLON);               
     try {
         emitErrorAsDebugMessage = true;
         retval.tree = tnr.invalidFallback(INVALID_STATEMENT, "INVALID_STATEMENT", follow, cz.vutbr.web.csskit.antlr.CSSLexerState.RecoveryMode.BALANCED, null, re);
     } finally {
         emitErrorAsDebugMessage = false;
     }
 }

media_query
 : (media_term S!*)+
 ;

media_term
 : (IDENT | media_expression)
 | nomediaquery -> INVALID_STATEMENT
 ;
 catch [RecognitionException re] {
     final BitSet follow = BitSet.of(COMMA, LCURLY, SEMICOLON);               
     try {
         emitErrorAsDebugMessage = true;
         retval.tree = tnr.invalidFallback(INVALID_STATEMENT, "INVALID_STATEMENT", follow, cz.vutbr.web.csskit.antlr.CSSLexerState.RecoveryMode.RULE, null, re);
     } finally {
         emitErrorAsDebugMessage = false;
     }
 }

media_expression
 : LPAREN S* IDENT S* (COLON S* terms)? RPAREN
    -> ^(DECLARATION IDENT terms)
 ;
 catch [RecognitionException re] {
		 final BitSet follow = BitSet.of(RPAREN, SEMICOLON);               
		 try {
		     emitErrorAsDebugMessage = true;
		     retval.tree = tnr.invalidFallbackGreedy(INVALID_STATEMENT, "INVALID_STATEMENT", follow, re);
		 } finally {
		     emitErrorAsDebugMessage = false;
		 }
 }

media_rule
 : ruleset
 | atstatement
 ;
	
unknown_atrule
 : ATKEYWORD S* any* LCURLY S* any* RCURLY -> INVALID_ATSTATEMENT
 ;
 catch [RecognitionException re] {
     final BitSet follow = BitSet.of(RCURLY);               
     try {
         emitErrorAsDebugMessage = true;
         retval.tree = tnr.invalidFallbackGreedy(INVALID_ATSTATEMENT, "INVALID_ATSTATEMENT", follow, cz.vutbr.web.csskit.antlr.CSSLexerState.RecoveryMode.BALANCED, null, re);
     } finally {
         emitErrorAsDebugMessage = false;
     }
 }
	
ruleset
	: combined_selector_list
	  LCURLY S* 
	  	declarations
	  RCURLY
	  -> ^(RULE combined_selector_list declarations)
	| norule -> INVALID_STATEMENT
	;
	catch [RecognitionException re] {
      final BitSet follow = BitSet.of(RCURLY);
      //we don't require {} to be balanced here because of possible parent 'media' sections that may remain open => RecoveryMode.RULE
	    retval.tree = tnr.invalidFallbackGreedy(INVALID_STATEMENT,	"INVALID_STATEMENT", follow, cz.vutbr.web.csskit.antlr.CSSLexerState.RecoveryMode.RULE, null, re);
	}

declarations
	: declaration? (SEMICOLON S* declaration? )*
	  -> ^(SET declaration*)
	;

declaration
@init {
  cz.vutbr.web.csskit.antlr.CSSLexerState begin = getCurrentLexerState(retval.start);
  log.trace("Decl begin: " + begin);
}
	: property COLON S* terms? important? -> ^(DECLARATION important? property terms?)
	| noprop any* -> INVALID_DECLARATION /* if first character in the declaration is invalid (various dirty hacks) */
	;
	catch [RecognitionException re] {
      final BitSet follow = BitSet.of(SEMICOLON, RCURLY); //recover on the declaration end or rule end
      try {
          emitErrorAsDebugMessage = true;
          //not greedy - the final ; or } must remain for properly finishing the declaration/rule
          retval.tree = tnr.invalidFallback(INVALID_DECLARATION, "INVALID_DECLARATION", follow, cz.vutbr.web.csskit.antlr.CSSLexerState.RecoveryMode.DECL, begin, re);
      } finally {
          emitErrorAsDebugMessage = false;
      }
	}

important
  : EXCLAMATION S* IMPORTANT S* -> IMPORTANT
  ;
  catch [RecognitionException re] {
      final BitSet follow = BitSet.of(RCURLY, SEMICOLON);               
      try {
          emitErrorAsDebugMessage = true;
          retval.tree = tnr.invalidFallback(INVALID_DIRECTIVE, "INVALID_DIRECTIVE", follow, cz.vutbr.web.csskit.antlr.CSSLexerState.RecoveryMode.RULE, null, re);
      } finally {
          emitErrorAsDebugMessage = false;
      }
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
	      final BitSet follow = BitSet.of(RCURLY, SEMICOLON);								
	      try {
	          emitErrorAsDebugMessage = true;
	          retval.tree = tnr.invalidFallbackGreedy(INVALID_STATEMENT, "INVALID_STATEMENT", follow, re);
	      } finally {
	          emitErrorAsDebugMessage = false;
	      }
		}
		else
		{
        final BitSet follow = BitSet.of(RPAREN, RCURLY, SEMICOLON);               
        try {
            emitErrorAsDebugMessage = true;
            retval.tree = tnr.invalidFallbackGreedy(INVALID_STATEMENT, "INVALID_STATEMENT", follow, cz.vutbr.web.csskit.antlr.CSSLexerState.RecoveryMode.FUNCTION, null, re);
        } finally {
            emitErrorAsDebugMessage = false;
        }
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
      | (PLUS | MINUS)? NUMBER -> MINUS? NUMBER
      | (PLUS | MINUS)? PERCENTAGE -> MINUS? PERCENTAGE
      | (PLUS | MINUS)? DIMENSION -> MINUS? DIMENSION
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
	    | ASTERISK -> ASTERISK		 
      | (PLUS | MINUS)? funct -> MINUS? funct 
      | DASHMATCH -> DASHMATCH
      | LPAREN valuepart* RPAREN -> ^(PARENBLOCK valuepart*)
      | LBRACE valuepart* RBRACE -> ^(BRACEBLOCK valuepart*)
    ) !S*
  ;

combined_selector_list
    : combined_selector (COMMA S* combined_selector)* -> combined_selector+
    ;

combined_selector    
	: selector ((combinator) selector)*
	;
	catch [RecognitionException re] {
	  log.debug("INVALID COMBINED SELECTOR");
	  try {
	      emitErrorAsDebugMessage = true;
	      reportError(re);
	  } finally {
	      emitErrorAsDebugMessage = false;
	  }
      recover(input,re);
	}

combinator
	: GREATER S* -> CHILD
	| PLUS S* -> ADJACENT
	| TILDE S* -> PRECEDING
	| S -> DESCENDANT
	;

element
    : prefix? (IDENT | ASTERISK)
        -> ^(ELEMENT prefix? IDENT? ASTERISK?)
    ;

prefix
    : (namespace_prefix | ASTERISK)? BAR
        -> ^(PREFIX namespace_prefix? ASTERISK?)
    ;

selector
    : element selpart* S*
        -> ^(SELECTOR element selpart*)
    | selpart+ S*
        -> ^(SELECTOR selpart+)
    ;
    catch [RecognitionException re] {
      try {
          emitErrorAsDebugMessage = true;
          retval.tree = tnr.invalidFallback(INVALID_SELECTOR, "INVALID_SELECTOR", re);
      } finally {
          emitErrorAsDebugMessage = false;
      }
	  }

selpart	
    :  HASH
    | CLASSKEYWORD
	  | LBRACE S* attribute RBRACE -> ^(ATTRIBUTE attribute)
    | pseudo
    | INVALID_SELPART
    ;
    catch [RecognitionException re] {
      try {
          emitErrorAsDebugMessage = true;
          retval.tree = tnr.invalidFallback(INVALID_SELPART, "INVALID_SELPART", re);
      } finally {
          emitErrorAsDebugMessage = false;
      }
	  }

attribute
	: prefix? IDENT S!*
	  ((EQUALS | INCLUDES | DASHMATCH | STARTSWITH | ENDSWITH | CONTAINS) S!* (IDENT | string) S!*)?
	;

pseudo
	: pseudocolon^ (IDENT | FUNCTION S!* (IDENT | MINUS? NUMBER | MINUS? INDEX) S!* RPAREN!)
	;
  catch [RecognitionException re] {
     try {
         emitErrorAsDebugMessage = true;
         retval.tree = tnr.invalidFallback(INVALID_SELPART, "INVALID_SELPART", re);
     } finally {
         emitErrorAsDebugMessage = false;
     }
  }

pseudocolon
	: COLON COLON -> PSEUDOELEM
	| COLON -> PSEUDOCLASS
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
  : ( RCURLY -> RCURLY
      | SEMICOLON -> SEMICOLON
      | QUOT -> QUOT
      | APOS -> APOS
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
     | CTRL -> CTRL
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
      | CTRL -> CTRL
      | POUND //that is not HASH (not an identifier)
      | HAT
      | AMPERSAND
    );

/** invalid start of a media query */    
nomediaquery
  : ( NUMBER -> NUMBER
      | PERCENTAGE ->PERCENTAGE
      | DIMENSION -> DIMENSION
      | string -> string
      | URI    -> URI
      | UNIRANGE -> UNIRANGE
      | INCLUDES -> INCLUDES
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
      | CTRL -> CTRL
      | COLON -> COLON
      | ASTERISK -> ASTERISK
      | FUNCTION -> FUNCTION
      | POUND //that is not HASH (not an identifier)
      | HAT
      | AMPERSAND
    );

