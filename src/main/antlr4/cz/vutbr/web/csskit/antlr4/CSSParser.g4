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
    tokenVocab=CSSLexer;
}

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////// P A R S E R /////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

@members {
    // logger
    private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
    // function level e.g. function(function(function()))
    private int functLevel = 0;
    //protected cz.vutbr.web.csskit.antlr.CSSTreeNodeRecovery tnr;

    /**
     * Obtains the current lexer state from current token
     */
    private cz.vutbr.web.csskit.antlr4.CSSLexerState getCurrentLexerState(Token t){
        if (t instanceof cz.vutbr.web.csskit.antlr4.CSSToken){
            return ((cz.vutbr.web.csskit.antlr4.CSSToken) t).getLexerState();
        }
        return null;
    }
    private CSSErrorStrategy getCSSErrorHandler(){
        if(this._errHandler instanceof CSSErrorStrategy){
            return ((CSSErrorStrategy) this._errHandler);
        }
        log.error("ERROR STRATEGY IS NOT OF TYPE CSSErrorStrategy");
        return null;
    }

}
inlinestyle
	: S*  (
	        declarations
            | inlineset+
	      )
	;
    catch [RecognitionException re] {
        log.error("Recognition exception | inlinestyle | should be EMPTY");
    }

//http://www.w3.org/TR/css-syntax-3/
stylesheet
	: ( CDO | CDC  | S | nostatement | statement )*
    ;
    catch [RecognitionException re] {
        log.error("Recognition exception | stylesheet | should be EMPTY");
    }

statement
    : ruleset | atstatement
    ;
    catch [RecognitionException re] {
        log.error("Recognition exception | statement | should be EMPTY");
    }

atstatement
	: CHARSET
	| IMPORT S* import_uri S* media? SEMICOLON
	| page
    | VIEWPORT S* LCURLY S* declarations RCURLY
	| FONTFACE S* LCURLY S* declarations RCURLY
	| MEDIA S* media? LCURLY S* (media_rule S*)* RCURLY
	| unknown_atrule
	;
    catch [RecognitionException re] {
        log.error("Recognition exception | atstatement consume until RCURLY | SEMICOLON");
        IntervalSet intervalSet = new IntervalSet(RCURLY, SEMICOLON);
        getCSSErrorHandler().consumeUntilGreedy(this, intervalSet);
        _localctx.addErrorNode(this.getTokenFactory().create(INVALID_ATSTATEMENT,"INVALID_ATSTATEMENT"));
     }

import_uri
    : (STRING | URI)
    ;
    catch [RecognitionException re] {
        log.error("Recognition exception | import_uri | should be empty");
    }

page
	: PAGE S* IDENT? page_pseudo? S*
		LCURLY S*
		declarations margin_rule*
		RCURLY
	;
    catch [RecognitionException re] {
        log.error("Recognition exception | page | should be empty");
    }

page_pseudo
	: pseudocolon
	;
    catch [RecognitionException re] {
        log.error("Recognition exception | page_pseudo | should be empty");
    }

/** CSS3 margin-at-rule - see https://drafts.csswg.org/css-page-3/#margin-at-rules */
margin_rule
	: MARGIN_AREA S* LCURLY S* declarations RCURLY S*
	;
    catch [RecognitionException re] {
        log.error("Recognition exception | margin_rule | should be empty");
    }

/** A ruleset in the inline style according to
    http://www.w3.org/TR/css-style-attr */
inlineset
	: (pseudo S* (COMMA S* pseudo S*)*)?
      LCURLY
	  	declarations
	  RCURLY
	;
    catch [RecognitionException re] {
        log.error("Recognition exception | inlineset | should be empty");
    }


media
    : media_query (COMMA S* media_query)*
    ;
    catch [RecognitionException re] {
        log.error("PARSING MEDIA ERROR | consume until COMMA, LCURLY, SEMICOLON");
        IntervalSet intervalSet = new IntervalSet(COMMA, LCURLY,SEMICOLON);
        getCSSErrorHandler().consumeUntil(this,intervalSet,CSSLexerState.RecoveryMode.BALANCED, null);
        _localctx.addErrorNode(this.getTokenFactory().create(INVALID_STATEMENT,"INVALID_STATEMENT"));
    }

media_query
    : (media_term S*)+
    ;
    catch [RecognitionException re] {
        log.error("Recognition exception | media_query | should be empty");
    }

media_term
    : (IDENT | media_expression)
    | nomediaquery
    ;
    catch [RecognitionException re] {
        log.error("PARSING MEDIATERM ERROR | consume until COMMA, LCURLY, SEMICOLON");
        IntervalSet intervalSet = new IntervalSet(COMMA, LCURLY,SEMICOLON);
        getCSSErrorHandler().consumeUntil(this,intervalSet,CSSLexerState.RecoveryMode.RULE, null);
        _localctx.addErrorNode(this.getTokenFactory().create(INVALID_STATEMENT,"INVALID_STATEMENT"));
    }

media_expression
    : LPAREN S* IDENT S* (COLON S* terms)? RPAREN
    ;
    catch [RecognitionException re] {
        log.error("PARSING media_expression ERROR | consume until RPAREN, SEMICOLON");
        IntervalSet intervalSet = new IntervalSet(RPAREN, SEMICOLON);
        getCSSErrorHandler().consumeUntilGreedy(this, intervalSet);
        _localctx.addErrorNode(this.getTokenFactory().create(INVALID_STATEMENT, "INVALID_STATEMENT"));
    }

media_rule
    : ruleset
    | atstatement //invalid statement
    ;
    catch [RecognitionException re] {
        log.error("Recognition exception | media_rule | should be empty");
    }

unknown_atrule
    : ATKEYWORD S* any* LCURLY S* any* RCURLY //invalid atstatement
    ;
    catch [RecognitionException re] {
        log.error("PARSING unknown_atrule ERROR - consume until RCURLY");
        IntervalSet intervalSet = new IntervalSet(RCURLY);
        getCSSErrorHandler().consumeUntilGreedy(this,intervalSet,CSSLexerState.RecoveryMode.BALANCED);
        _localctx.addErrorNode(this.getTokenFactory().create(INVALID_ATSTATEMENT,"INVALID_ATSTATEMENT"));
    //     final BitSet follow = BitSet.of(RCURLY);
    //     retval.tree = tnr.invalidFallbackGreedy(INVALID_ATSTATEMENT,"INVALID_ATSTATEMENT", follow, cz.vutbr.web.csskit.antlr.CSSLexerState.RecoveryMode.BALANCED, null, re);
    }

ruleset
	: combined_selector (COMMA S* combined_selector)*
	  LCURLY S*
	  	declarations
	  RCURLY
	| norule // invalid statement
	;
    catch [RecognitionException re] {
	    log.debug("PARSING ruleset ERROR | consume until RCURLY and add INVALID_STATEMENT");
        IntervalSet intervalSet = new IntervalSet(RCURLY);
        getCSSErrorHandler().consumeUntilGreedy(this,intervalSet);
        _localctx.addErrorNode(this.getTokenFactory().create(INVALID_STATEMENT,"INVALID_STATEMENT"));
        //final BitSet follow = BitSet.of(RCURLY);
        //we don't require {} to be balanced here because of possible parent 'media' sections that may remain open => RecoveryMode.RULE
        //	    retval.tree = tnr.invalidFallbackGreedy(INVALID_STATEMENT,	"INVALID_STATEMENT", follow, cz.vutbr.web.csskit.antlr.CSSLexerState.RecoveryMode.RULE, null, re);
	}

declarations
	: declaration? (SEMICOLON S* declaration? )*
	;
    catch [RecognitionException re] {
        log.error("Recognition exception | declarations | should be empty");
    }


declaration
    @init {
      CSSLexerState begin = getCurrentLexerState(_localctx.getStart());
      log.debug("Decl begin: " + begin);
    }
	: property COLON S* terms? important?
	| noprop any* // invalid declaration /* if first character in the declaration is invalid (various dirty hacks) */
	;
	catch [RecognitionException re] {
        log.error("PARSING declaration ERROR | consume until SEMICOLON, RCURLY");
        IntervalSet follow = new IntervalSet(SEMICOLON,RCURLY); //recover on the declaration end or rule end
        //not greedy - the final ; or } must remain for properly finishing the declaration/rule
        this.getCSSErrorHandler().consumeUntil(this, follow, CSSLexerState.RecoveryMode.DECL, begin);
        _localctx.addErrorNode(this.getTokenFactory().create(INVALID_DECLARATION,"INVALID_DECLARATION"));
	}

important
    : EXCLAMATION S* IMPORTANT S*
    ;
    catch [RecognitionException re] {
        log.error("PARSING IMPORTANT error");
        IntervalSet intervalSet = new IntervalSet(RCURLY,SEMICOLON);
        this.getCSSErrorHandler().consumeUntil(this,intervalSet,CSSLexerState.RecoveryMode.RULE, null);
        _localctx.addErrorNode(this.getTokenFactory().create(INVALID_DIRECTIVE,"INVALID_DIRECTIVE"));
    }

property
	: MINUS? IDENT S*
	;
    catch [RecognitionException re]{
        log.error("PARSING property ERROR | should be empty");
    }

terms
	: term+
	;
    catch [RecognitionException re] {
        log.error("PARSING terms ERROR functLevel = {}", functLevel);
        if (functLevel == 0){
            IntervalSet intervalSet = new IntervalSet(RCURLY,SEMICOLON);
            this.getCSSErrorHandler().consumeUntilGreedy(this,intervalSet);
            _localctx.addErrorNode(this.getTokenFactory().create(INVALID_STATEMENT,"INVALID_STATEMENT"));
            /*
            final BitSet follow = BitSet.of(RCURLY, SEMICOLON);
            retval.tree = tnr.invalidFallbackGreedy(INVALID_STATEMENT,"INVALID_STATEMENT", follow, re);
            */
        }
        else{
            IntervalSet intervalSet = new IntervalSet(RPAREN,RCURLY,SEMICOLON);
            this.getCSSErrorHandler().consumeUntilGreedy(this,intervalSet);
            _localctx.addErrorNode(this.getTokenFactory().create(INVALID_STATEMENT,"INVALID_STATEMENT"));
            /*
            final BitSet follow = BitSet.of(RPAREN, RCURLY, SEMICOLON);
            retval.tree = tnr.invalidFallbackGreedy(INVALID_STATEMENT, "INVALID_STATEMENT", follow, cz.vutbr.web.csskit.antlr.CSSLexerState.RecoveryMode.FUNCTION, null, re);
            */
        }
    }

term
    : valuepart #termValuePart
    | LCURLY S* (any | SEMICOLON S*)* RCURLY #termInvalid // invalid term
    | ATKEYWORD S* #termInvalid // invalid term
    ;
    catch [RecognitionException re] {
      log.error("PARSING term ERROR | should be empty");
    }

/** other functions than expression */
funct
    @init {
        functLevel++;
    }
    @after {
        functLevel--;
    }
    : EXPRESSION S* (any | SEMICOLON S*)* RPAREN // invalid declaration - expression function is no longer supported
	| FUNCTION S* terms? RPAREN
	;
    catch [RecognitionException re] {
        log.error("Recognition exception | funct | should be empty");
    }

/** a part of a property value */
valuepart
    : ( MINUS? IDENT
        | (PLUS | MINUS)? NUMBER
        | (PLUS | MINUS)? PERCENTAGE
        | (PLUS | MINUS)? DIMENSION
        | string
        | URI
        | HASH
        | (PLUS | MINUS)? funct
        | COMMA
        | SLASH
        | CLASSKEYWORD //invalid
        | UNIRANGE //invalid
        | INCLUDES //invalid
        | COLON //invalid
        | GREATER //invalid
        | LESS //invalid
        | QUESTION //invalid
        | PERCENT //invalid
        | EQUALS //invalid
	    | ASTERISK //invalid
        | DASHMATCH //invalid
        | LPAREN valuepart* RPAREN //invalid
        | LBRACE valuepart* RBRACE //invalid
    ) S*
    ;
	catch [RecognitionException re] {
	   log.error("Recognition exception | valuepart | should be empty");
	}

combined_selector
	: selector ((combinator) selector)*
	;
	catch [RecognitionException re] {
	  log.error("Recognition exception | combined_selector | should be empty");
	}

//combinator of selectors
combinator
	: GREATER S* //child combinator
	| PLUS S* //adjacent combinator
	| TILDE S* //preceding combinator
	| S //descendant combinator
    ;
    catch [RecognitionException re] {
        log.error("Recognition exception | combinator| should be empty");
    }


selector
    : (IDENT | ASTERISK)  selpart* S*
    | selpart+ S*
    ;
    catch [RecognitionException re] {
        log.error("PARSING selector ERROR | inserting INVALID_SELECTOR");
        _localctx.addErrorNode(this.getTokenFactory().create(INVALID_SELECTOR,"INVALID_SELECTOR"));
    }

selpart
    : HASH
    | CLASSKEYWORD
	| LBRACE S* attribute RBRACE
    | pseudo
    | INVALID_SELPART // invalid selpart
    ;
    catch [RecognitionException re] {
        log.error("PARSING SELPART ERROR");
        _localctx.addErrorNode(this.getTokenFactory().create(INVALID_SELPART,"INVALID_SELPART"));
//      retval.tree = tnr.invalidFallback(INVALID_SELPART, "INVALID_SELPART", re);
	  }

attribute
	: IDENT S*
	  ((EQUALS | INCLUDES | DASHMATCH | STARTSWITH | ENDSWITH | CONTAINS) S* (IDENT | string) S*)?
	;
    catch [RecognitionException re] {
        log.error("Recognition exception | attribute | should be empty");
     }

pseudo
	: pseudocolon (IDENT | FUNCTION S*  (IDENT | MINUS? NUMBER | MINUS? INDEX) S* RPAREN)
	;
    catch [RecognitionException re] {
      log.error("PARSING pseudo ERROR | inserting INVALID_SELPART");
       _localctx.addErrorNode(this.getTokenFactory().create(INVALID_SELPART,"INVALID_SELPART"));
    //     retval.tree = tnr.invalidFallback(INVALID_SELPART, "INVALID_SELPART", re);
    }

pseudocolon
	: COLON COLON // pseudo element
	| COLON // pseudo class
	;
    catch [RecognitionException re] {
        log.error("PARSING pseudocolon ERROR | should be empty");
     }


string
	: STRING
	| UNCLOSED_STRING
	| INVALID_STRING
	;
    catch [RecognitionException re] {
        log.error("PARSING string ERROR | should be empty");
    }


any
	: ( IDENT
	  | CLASSKEYWORD
	  | NUMBER
	  | PERCENTAGE
	  | DIMENSION
	  | string
      | URI
      | HASH
      | UNIRANGE
      | INCLUDES
      | COLON
      | COMMA
      | GREATER
      | LESS
      |	QUESTION
      | PERCENT
      | EQUALS
      | SLASH
      | EXCLAMATION
	  | MINUS
	  | PLUS
	  | ASTERISK
      | FUNCTION S* any* RPAREN
      | DASHMATCH
      | LPAREN any* RPAREN
      | LBRACE any* RBRACE
    ) S*
    ;
    catch [RecognitionException re] {
        log.error("PARSING any ERROR | should be empty");
    }

/** What cannot be contained directly in the stylesheet (ignored) */
nostatement
  : ( RCURLY
    | SEMICOLON
    | QUOT
    | APOS
  );
  catch [RecognitionException re] {
    log.error("PARSING nostatement ERROR | should be empty");
  }

/** invalid start of a property */
noprop
	: ( CLASSKEYWORD
       | NUMBER
	   | COMMA
	   | GREATER
	   | LESS
	   | QUESTION
	   | PERCENT
	   | EQUALS
	   | SLASH
	   | EXCLAMATION
	   | PLUS
	   | ASTERISK
	   | DASHMATCH
	   | INCLUDES
	   | COLON
	   | STRING_CHAR
       | CTRL
	   | INVALID_TOKEN
    ) S*
    ;
    catch [RecognitionException re] {
        log.error("PARSING noprop ERROR | should be empty");
    }

/** invalid start of a rule */
norule
  : ( NUMBER
    | PERCENTAGE
    | DIMENSION
    | string
    | URI
    | UNIRANGE
    | INCLUDES
    | COMMA
    | GREATER
    | LESS
    | QUESTION
    | PERCENT
    | EQUALS
    | SLASH
    | EXCLAMATION
	| MINUS
    | PLUS
    | DASHMATCH
    | RPAREN
    | CTRL
    | POUND //that is not HASH (not an identifier)
    | HAT
    | AMPERSAND
    );
    catch [RecognitionException re] {
        log.error("PARSING norule ERROR | should be empty");
    }

/** invalid start of a media query */
nomediaquery
  : ( NUMBER
    | PERCENTAGE
    | DIMENSION
    | string
    | URI
    | UNIRANGE
    | INCLUDES
    | GREATER
    | LESS
    | QUESTION
    | PERCENT
    | EQUALS
    | SLASH
    | EXCLAMATION
    | MINUS
    | PLUS
    | DASHMATCH
    | RPAREN
    | CTRL
    | COLON
    | ASTERISK
    | FUNCTION
    | POUND //that is not HASH (not an identifier)
    | HAT
    | AMPERSAND
    );
    catch [RecognitionException re] {
        log.error("PARSING nomediaquery ERROR | should be empty");
    }


