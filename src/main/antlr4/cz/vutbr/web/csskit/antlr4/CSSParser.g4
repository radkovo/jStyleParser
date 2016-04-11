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

@members {
    // logger
    private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
    // function level e.g. function(function(function()))
    private int functLevel = 0;
    //protected cz.vutbr.web.csskit.antlr.CSSTreeNodeRecovery tnr;

    /**
     * This function must be called to initialize parser's state.
     * Because we can't change directly generated constructors.
     */
    public void init() {
        //this.tnr = new cz.vutbr.web.csskit.antlr4.CSSTreeNodeRecovery(this, input, state, adaptor, log);
    }
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
	        declarations //-> ^(INLINESTYLE declarations)
            | inlineset+ //-> ^(INLINESTYLE inlineset+)
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
        IntervalSet intervalSet = new IntervalSet(RCURLY,SEMICOLON);
        getCSSErrorHandler().consumeUntil(this,intervalSet);
        _localctx.addErrorNode(this.getTokenFactory().create(INVALID_ATSTATEMENT,"INVALID_ATSTATEMENT"));
     }

import_uri
    : (STRING | URI)
    ;
    catch [RecognitionException re] {
        log.error("Recognition exception | import_uri | should be empty");
    }

page
	: PAGE S* (( IDENT | IDENT page_pseudo | page_pseudo) S*) ?
		LCURLY S*
		declarations margin_rule*
		RCURLY
		//-> ^(PAGE IDENT? page_pseudo? declarations ^(SET margin_rule*))
	;
    catch [RecognitionException re] {
        log.error("Recognition exception | page | should be empty");
    }

page_pseudo
	: pseudocolon//^ IDENT
	;
    catch [RecognitionException re] {
        log.error("Recognition exception | page_pseudo | should be empty");
    }

margin_rule
	: MARGIN_AREA S* LCURLY S* declarations RCURLY S* //-> ^(MARGIN_AREA declarations)
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
	 // -> ^(RULE pseudo* declarations)
	;
    catch [RecognitionException re] {
        log.error("Recognition exception | inlineset | should be empty");
    }


media
    : media_query (COMMA S* media_query)*
    //-> ^(MEDIA_QUERY media_query)+
    ;
    catch [RecognitionException re] {
        log.error("PARSING MEDIA ERROR | consume until COMMA, LCURLY, SEMICOLON");
        IntervalSet intervalSet = new IntervalSet(COMMA, LCURLY,SEMICOLON);
        getCSSErrorHandler().consumeUntil(this,intervalSet);
        _localctx.addErrorNode(this.getTokenFactory().create(INVALID_STATEMENT,"INVALID_STATEMENT"));
        //     final BitSet follow = BitSet.of(COMMA, LCURLY, SEMICOLON);
        //     retval.tree = tnr.invalidFallback(INVALID_STATEMENT, "INVALID_STATEMENT", follow, cz.vutbr.web.csskit.antlr.CSSLexerState.RecoveryMode.BALANCED, null, re);
    }

media_query
    : (media_term S*)+
    ;
    catch [RecognitionException re] {
        log.error("Recognition exception | media_query | should be empty");
    }

media_term
    : (IDENT | media_expression)
    | nomediaquery //hotovo
    ;
    catch [RecognitionException re] {
        log.error("PARSING MEDIATERM ERROR | consume until COMMA, LCURLY, SEMICOLON");
        IntervalSet intervalSet = new IntervalSet(COMMA, LCURLY,SEMICOLON);
        getCSSErrorHandler().consumeUntil(this,intervalSet);
        _localctx.addErrorNode(this.getTokenFactory().create(INVALID_STATEMENT,"INVALID_STATEMENT"));
        //     final BitSet follow = BitSet.of(COMMA, LCURLY, SEMICOLON);
        //     retval.tree = tnr.invalidFallback(INVALID_STATEMENT, "INVALID_STATEMENT", follow, cz.vutbr.web.csskit.antlr.CSSLexerState.RecoveryMode.RULE, null, re);
    }

media_expression
    : LPAREN S* IDENT S* (COLON S* terms)? RPAREN
    //    -> ^(DECLARATION IDENT terms?)
    ;
    catch [RecognitionException re] {
        log.error("PARSING meida_expression ERROR | consume until RPAREN, SEMICOLON");
        IntervalSet intervalSet = new IntervalSet(RPAREN, SEMICOLON);
        getCSSErrorHandler().consumeUntilGreedy(this,intervalSet);
        _localctx.addErrorNode(this.getTokenFactory().create(INVALID_STATEMENT,"INVALID_STATEMENT"));
        //		 final BitSet follow = BitSet.of(RPAREN, SEMICOLON);
        //		 retval.tree = tnr.invalidFallbackGreedy(INVALID_STATEMENT, "INVALID_STATEMENT", follow, re);
    }

media_rule
    : ruleset
    | atstatement //-> INVALID_STATEMENT
    ;
    catch [RecognitionException re] {
        log.error("Recognition exception | media_rule | should be empty");
    }

unknown_atrule
    : ATKEYWORD S* any* LCURLY S* any* RCURLY //-> INVALID_ATSTATEMENT
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
	  //-> ^(RULE combined_selector+ declarations)
	| norule //-> INVALID_STATEMENT
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
	  //-> ^(SET declaration*)
	;
    catch [RecognitionException re] {
        log.error("Recognition exception | declarations | should be empty");
    }


declaration
    @init {
      CSSLexerState begin = getCurrentLexerState(_localctx.getStart());
      log.debug("Decl begin: " + begin);
    }
	: property COLON S* terms? important? //-> ^(DECLARATION important? property terms?)
	| noprop any* //-> INVALID_DECLARATION /* if first character in the declaration is invalid (various dirty hacks) */
	;
	catch [RecognitionException re] {
        log.error("PARSING declaration ERROR | consume until SEMICOLON, RCURLY");
        IntervalSet follow = new IntervalSet(SEMICOLON,RCURLY); //recover on the declaration end or rule end
        //not greedy - the final ; or } must remain for properly finishing the declaration/rule
        this.getCSSErrorHandler().consumeUntil(this,follow,CSSLexerState.RecoveryMode.DECL, begin, re);
        _localctx.addErrorNode(this.getTokenFactory().create(INVALID_DECLARATION,"INVALID_DECLARATION"));
//      final BitSet follow = BitSet.of(SEMICOLON, RCURLY); //recover on the declaration end or rule end
      //not greedy - the final ; or } must remain for properly finishing the declaration/rule
//      retval.tree = tnr.invalidFallback(INVALID_DECLARATION, "INVALID_DECLARATION", follow, cz.vutbr.web.csskit.antlr.CSSLexerState.RecoveryMode.DECL, begin, re);
	}

important
    : EXCLAMATION S* IMPORTANT S* //-> IMPORTANT
    ;
    catch [RecognitionException re] {
        log.error("PARSING IMPORTANT");
        IntervalSet intervalSet = new IntervalSet(RCURLY,SEMICOLON);
        this.getCSSErrorHandler().consumeUntil(this,intervalSet);
        _localctx.addErrorNode(this.getTokenFactory().create(INVALID_DIRECTIVE,"INVALID_DIRECTIVE"));
//      final BitSet follow = BitSet.of(RCURLY, SEMICOLON);
//      retval.tree = tnr.invalidFallback(INVALID_DIRECTIVE, "INVALID_DIRECTIVE", follow, cz.vutbr.web.csskit.antlr.CSSLexerState.RecoveryMode.RULE, null, re);
    }

//done
property
	: MINUS? IDENT S*
	;
    catch [RecognitionException re]{
        log.error("PARSING property ERROR | should be empty");
    }

terms
	: term+
	//-> ^(VALUE term+)
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
    : valuepart #termValuePart//-> valuepart
    | LCURLY S* (any | SEMICOLON S*)* RCURLY #termCurlyBlock//-> CURLYBLOCK
    | ATKEYWORD S* #termAtKeyword//-> ATKEYWORD
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
    : EXPRESSION //-> EXPRESSION
	| FUNCTION S* terms? RPAREN //-> ^(FUNCTION terms?)
	;
    catch [RecognitionException re] {
        log.error("Recognition exception | funct | should be empty");
    }

/** a part of a property value */
valuepart
    : ( MINUS? IDENT  //-> MINUS? IDENT
      | CLASSKEYWORD //-> CLASSKEYWORD
      | (PLUS | MINUS)? NUMBER //-> MINUS? NUMBER
      | (PLUS | MINUS)? PERCENTAGE //-> MINUS? PERCENTAGE
      | (PLUS | MINUS)? DIMENSION //-> MINUS? DIMENSION
      | string //-> string
      | URI    //-> URI
      | HASH //-> HASH
      | UNIRANGE //-> UNIRANGE
      | INCLUDES //-> INCLUDES
      | COLON //-> COLON
      | COMMA //-> COMMA
      | GREATER //-> GREATER
      | LESS //-> LESS
      |	QUESTION //-> QUESTION
      | PERCENT //-> PERCENT
      | EQUALS //-> EQUALS
      | SLASH //-> SLASH
	  | ASTERISK //-> ASTERISK
      | (PLUS | MINUS)? funct //-> MINUS? funct
      | DASHMATCH //-> DASHMATCH
      | LPAREN valuepart* RPAREN //-> ^(PARENBLOCK valuepart*)
      | LBRACE valuepart* RBRACE //-> ^(BRACEBLOCK valuepart*)
    ) S*//!S*
    ;
	catch [RecognitionException re] {
	  log.error("THROWING valuepart ERROR / TODO:");
//	  throw re;
//	  reportError(re);
//      recover(input,re);
	}

combined_selector
	: selector ((combinator) selector)*
	;
	catch [RecognitionException re] {
	  log.error("PARSING COMBINED_SELECTOR ERROR / TODO:");
//	  reportError(re);
//      recover(input,re);
	}

combinator
	: GREATER S* #combinatorChild
	| PLUS S* #combinatorAdjacent
	| TILDE S* #combinatorPreceding
	| S #combinatorDescendant
    ;
    catch [RecognitionException re] {
        log.error("Recognition exception | combinator| should be empty");
    }


selector
    : (IDENT | ASTERISK)  selpart* S* #selectorWithIdOrAsterisk
    	//-> ^(SELECTOR ^(ELEMENT IDENT?) selpart*)
    | selpart+ S* #selectorWithoutIdOrAsterisk
        //-> ^(SELECTOR selpart+)
    ;
    catch [RecognitionException re] {
        log.error("PARSING selector ERROR | inserting INVALID_SELECTOR");
        _localctx.addErrorNode(this.getTokenFactory().create(INVALID_SELECTOR,"INVALID_SELECTOR"));
//      retval.tree = tnr.invalidFallback(INVALID_SELECTOR, "INVALID_SELECTOR", re);
	  }

selpart
    :  HASH #selpartId
    | CLASSKEYWORD #selpartClass
	| LBRACE S* attribute RBRACE #selpartAttrib //-> ^(ATTRIBUTE attribute)
    | pseudo #selpartPseudo
    | INVALID_SELPART #selpartInvalid
    ;
    catch [RecognitionException re] {
        log.error("PARSING SELPART ERROR");
        _localctx.addErrorNode(this.getTokenFactory().create(INVALID_SELPART,"INVALID_SELPART"));
//      retval.tree = tnr.invalidFallback(INVALID_SELPART, "INVALID_SELPART", re);
	  }

attribute
	: IDENT S*//S!*
	  ((EQUALS | INCLUDES | DASHMATCH | STARTSWITH | ENDSWITH | CONTAINS) S*/*!**/ (IDENT | string) S*/*!**/)?
	;
    catch [RecognitionException re] {
        log.error("ATTRIBUTE ERROR");
    //    throw re;
     }

pseudo
	: pseudocolon (IDENT | FUNCTION S*  (IDENT | MINUS? NUMBER | MINUS? INDEX) S* RPAREN)
	;
    catch [RecognitionException re] {
      log.error("PARSING pseudo ERROR | inserting INVALID_SELPART");
       _localctx.addErrorNode(this.getTokenFactory().create(INVALID_SELPART,"INVALID_SELPART"));
    //     retval.tree = tnr.invalidFallback(INVALID_SELPART, "INVALID_SELPART", re);
    }

//pseudoElem| pseudoClass vyreseno zatim v metode enterPseudo ctx.getChild(0).getText().length() != 1
pseudocolon
	: COLON COLON //#pseudoElem //-> PSEUDOELEM
	| COLON //#pseudoClass//-> PSEUDOCLASS
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
	: ( IDENT //-> IDENT
	  | CLASSKEYWORD //-> CLASSKEYWORD
	  | NUMBER //-> NUMBER
	  | PERCENTAGE //->PERCENTAGE
	  | DIMENSION //-> DIMENSION
	  | string //-> string
      | URI    //-> URI
      | HASH //-> HASH
      | UNIRANGE //-> UNIRANGE
      | INCLUDES //-> INCLUDES
      | COLON //-> COLON
      | COMMA //-> COMMA
      | GREATER //-> GREATER
      | LESS //-> LESS
      |	QUESTION //-> QUESTION
      | PERCENT //-> PERCENT
      | EQUALS //-> EQUALS
      | SLASH //-> SLASH
      | EXCLAMATION //-> EXCLAMATION
	  | MINUS //-> MINUS
	  | PLUS //-> PLUS
	  | ASTERISK //-> ASTERISK
      | FUNCTION S* any* RPAREN //-> ^(FUNCTION any*)
      | DASHMATCH //-> DASHMATCH
      | LPAREN any* RPAREN //-> ^(PARENBLOCK any*)
      | LBRACE any* RBRACE //-> ^(BRACEBLOCK any*)
    ) S*//!S*
    ;
    catch [RecognitionException re] {
        log.error("PARSING any ERROR | should be empty");
    }

/** What cannot be contained directly in the stylesheet (ignored) */
nostatement
  : ( RCURLY //-> RCURLY
      | SEMICOLON //-> SEMICOLON
      | QUOT //-> QUOT
      | APOS //-> APOS
    );
    catch [RecognitionException re] {
        log.error("PARSING nostatement ERROR | should be empty");
    }

/** invalid start of a property */
noprop
	: ( CLASSKEYWORD //-> CLASSKEYWORD
       | NUMBER //-> NUMBER
	   | COMMA //-> COMMA
	   | GREATER //-> GREATER
	   | LESS //-> LESS
	   | QUESTION //-> QUESTION
	   | PERCENT //-> PERCENT
	   | EQUALS //-> EQUALS
	   | SLASH //-> SLASH
	   | EXCLAMATION //-> EXCLAMATION
	   | PLUS //-> PLUS
	   | ASTERISK //-> ASTERISK
	   | DASHMATCH //-> DASHMATCH
	   | INCLUDES //-> INCLUDES
	   | COLON //-> COLON
	   | STRING_CHAR //-> STRING_CHAR
       | CTRL //-> CTRL
	   | INVALID_TOKEN //-> INVALID_TOKEN
    ) S*//!S*
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
//sedlakr - hotovo
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


