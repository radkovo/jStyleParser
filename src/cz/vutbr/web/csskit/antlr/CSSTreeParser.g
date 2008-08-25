tree grammar CSSTreeParser;

options {
	tokenVocab=CSS;
	ASTLabelType=CommonTree;
}

@header {
package cz.vutbr.web.csskit.antlr;

import java.io.IOException;
import java.io.Reader;

import org.apache.log4j.Logger;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.RuleFactory;
import cz.vutbr.web.css.StyleSheetNotValidException;
import cz.vutbr.web.css.*;

}

@members {
	private static Logger log = Logger.getLogger(CSSTreeParser.class);

	private static RuleFactory rf = CSSFactory.getRuleFactory();

	private int rulesetNum = 0;

	public static CSSTreeParser createParser(Reader reader) 
		throws StyleSheetNotValidException, IOException, RecognitionException {	
		
		CSSLexer lexer = new CSSLexer(new ANTLRReaderStream(reader));
            	CommonTokenStream tokens = new CommonTokenStream(lexer);
            	CSSParser parser = new CSSParser(tokens);
            	// Invoke the program rule in get return value
            	CSSParser.stylesheet_return r = parser.stylesheet();
            	CommonTree t = (CommonTree) r.getTree();

    		if(log.isTraceEnabled()) {
            		log.trace("Tree: \n" + t.toStringTree());
            	}
            	        	
            	
            	// Walk resulting tree; create treenode stream first
            	CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
            	// AST nodes have payloads that point into token stream
            	nodes.setTokenStream(tokens);
            	// Create a tree Walker attached to the nodes stream
            	return new CSSTreeParser(nodes);
    	}
    	
    	private void logEnter(String entry) {
    		if(log.isTraceEnabled()) {
    			log.debug("Entering '" + entry + "'");
    		}
    	}
    	
    	private void logLeave(String leaving) {
    		if(log.isTraceEnabled()) {
    			log.debug("Leaving '" + leaving + "'");
    		}
    	}
	
}

stylesheet returns [StyleSheet style]
scope { 	
	StyleSheet sheet;
}
@init { 
	logEnter("ROOT: stylesheet");
	$style = $stylesheet::sheet = rf.createStyleSheet();
	List<Rule<?>> rulesets = new ArrayList<Rule<?>>();	
}
@after {
	$style.replaceAll(rulesets);
}
	: ^(STYLESHEET 
		atblock* 
		rset=ruleset* { rulesets.add(rset); } )
	;


atblock	
@init { logEnter("at-block"); }
@after { logEnter("at-block"); }
	: ^(atkeyword specifier? block?) { $stylesheet::sheet.setCharset("auu"); }
	;

atkeyword
	: CHARSET
	| IMPORT
	| PAGE
	| MEDIA
	| BLOCK
	;

specifier
	: ident+
	| string
	| uri
	;

block returns [List<Declaration> declarations]
scope { List<Declaration> decls; }	
@init { 
	logEnter("block"); 
	$declarations = $block::decls = new ArrayList<Declaration>();
}
@after { logLeave("block"); }
	: properties
	;	


ruleset returns [RuleSet rule]
@init { logEnter("ruleset"); }
@after { 
	$rule = rf.createSet(new Integer(rulesetNum++));
	$rule.setSelectors($cslist.cslist);
	$rule.replaceAll($decls.declarations);
	logLeave("ruleset"); 
}
 	: ^( RULE cslist=selectors decls=block)
	;


	
selectors returns [List<CombinedSelector> cslist]
@init { logEnter("selectors");
	$cslist = new ArrayList<CombinedSelector>(); 
}
@after { logLeave("selectors");}
	: (selector selectorop*)+
	;


selector returns [Selector selector]
@init { logEnter("selector");
	$selector = rf.createSelector();
	List<Selector.SelectorPart> parts = new ArrayList<Selector.SelectorPart>();
}
@after { logLeave("selector");}
	: e=element { $selector.setFirstItem($e.element); } 
	( elhash | elclass | attrib | pseudo)*
	;
	
selectorop
@init { logEnter("selector-op");}
@after { logLeave("selector-op");}
	: SOADJACENT
	| SOCHILD
	| SODESCENDANT
	| SOSTANDALONE;
	
element returns [Selector.SelectorPart element]
	: ^( TAG i=ident ) { $element = rf.createElement($i.ident);}
	| UNIVERSAL { $element = null; }
	;

elhash	
	: ^(ID HASH )
	;		

elclass
	: ^(CLASS ident)
	;	

pseudo
	: ^( PSEUDO ident )
	| ^( PSEUDO function )
	;

attrib
	: ^( ATTRIB ident (attribRelate string* ident*)? )
	;
	
attribRelate
	: AOEQUALS
	| AOINCLUDES
	| AODASHMATCH
	;
	

properties
	: declaration+
	;
  
declaration
@init { logEnter("declaration");}
@after { logLeave("declaration");}
	: ^( PROPERTY ident priority? terms )
	;

priority
	: IMPORTANT
	;

terms
@init { logEnter("terms");}
@after { logLeave("terms");}
	: term (termop term)*
	;	

termop	
@after { logLeave("term-op"); }
	: TOCOMMA
	| TOSLASH
	| TOSPACE
	;
	

term returns [Term<?> term]
@init { logEnter("term");}
@after { logLeave("term");}
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

ident returns [String ident]
@after{	log.trace("ident was: " + $ident); }
	: i=IDENT { $ident = $ident.text; }
	;

/**
 * String literal
 * @returns String encapsuled in ' or " characters
 */
string returns [String string]
@after{	log.trace("string was: " + $string); }
	: s=STRING { $string = $s.text; }
	;

/**
 * URI literal.
 * @returns URI encapsuled or not in ' or " characters 
 */
uri returns [String uri]
@after{	log.trace("uri was: " + $uri); }
	: ^(URI (u=URI_STRING | u=STRING)) { $uri = $u.text; }
	;
	
function
	: ^( FUNCTION ident terms? )
	;
