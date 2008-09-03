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
	private static TermFactory tf = CSSFactory.getTermFactory();
	private static SupportedCSS css = CSSFactory.getSupportedCSS();

	private int ruleNum = 0;

	public static CSSTreeParser createParser(Reader reader) 
		throws StyleSheetNotValidException, IOException, RecognitionException {	
		
		CSSLexer lexer = new CSSLexer(new ANTLRReaderStream(reader));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CSSParser parser = new CSSParser(tokens);
		
		// set tree adaptor
		parser.setTreeAdaptor(new CSSAdaptor());
		
        // Invoke the program rule in get return value
        CSSParser.stylesheet_return r = parser.stylesheet();
        CommonTree t = (CommonTree) r.getTree();

    	if(log.isTraceEnabled()) {
        	log.trace("Tree: \n" + parser.toStringTree(t));
        }            	        	
            	
        // Walk resulting tree; create treenode stream first
        CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
        // AST nodes have payloads that point into token stream
        nodes.setTokenStream(tokens);
        // Create a tree Walker attached to the nodes stream
        return new CSSTreeParser(nodes);
    }
       
    @Override
	public void emitErrorMessage(String msg) {
		if(log.isInfoEnabled()) {
		    log.info("ANTLR: " + msg);
		}
	}
		
	private String extractText(CommonTree token) {
        return token.getText();
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

/**
 * Stylesheet, main rule
 */
stylesheet returns [StyleSheet sheet]
scope {
	StyleSheet style; 
}
@init {
	logEnter("stylesheet");
	$stylesheet::style=$sheet = (StyleSheet) rf.createStyleSheet().unlock();
} 
@after {
    if(log.isTraceEnabled()) {
        log.trace("\n\n***\n" + $sheet.toString() + "\n***\n\n");
    }
	logLeave("stylesheet");
}
	: ^(STYLESHEET 
	    (s=statement { if(s!=null) $sheet.add(s);})*  
	   )
	;

/**
 * Statement, main contents unit
 */	
statement returns [RuleBlock<?> stm]
scope {
    boolean invalid;
}
@init {
	logEnter("statement");
	$statement::invalid = false;
}   
@after {
	logLeave("statement");
}
	: rs=ruleset {$stm=(RuleBlock<?>) rs;} 
	| atr=atrule {$stm=(RuleBlock<?>) atr;}
	;	

/**
 * Rule which begins with '@' character
 */
atrule returns [RuleBlock<?> atblock]     
@init {
	logEnter("atrule");
}
@after{
	logLeave("atrule");
}
    : ^(ATBLOCK 
        atk=ATKEYWORD {
            String keyword = extractText(atk);
            // invalidate if keyword not supported
            if(!css.isSupportedAtKeyword(keyword)) {
                $statement::invalid = true;
            }
            else {
                log.debug("Atkeyword passed: " + keyword);
            }
        } 
        any* 
        block?
       )
    ;
  
block       
    : ^(CURLYBLOCK blockpart*)
    ;

blockpart
@init{
	logEnter("blockpart");
}
@after {
	logLeave("blockpart");
}
    : any 
    | ^(CURLYBLOCK declaration*)
    | ATKEYWORD { $statement::invalid=true; }
    | SEMICOLON 
    ; 
    
/**
 * The most common block in CSS file,
 * set of declarations with selector
 */  
ruleset returns [RuleSet rs]
@init {
    logEnter("ruleset"); 
    List<CombinedSelector> cslist = new ArrayList<CombinedSelector>();
    List<Declaration> declarations = new ArrayList<Declaration>();
}
@after {
    if($statement::invalid || cslist.isEmpty() || declarations.isEmpty()) {
        $rs = null;
        log.debug("Ruleset not valid, so not created");
    }
    else {    
        $rs = rf.createSet(ruleNum++);
        $rs.setSelectors(cslist);
        $rs.replaceAll(declarations);
        if(log.isInfoEnabled()) {
            log.info("Created ruleset at: " + ruleNum + " of: \n" + $rs); 
        }
    }
    logLeave("ruleset");
}    
    : ^(RULE 
        (cs=combined_selector  
        {if(cs!=null && !cs.isEmpty() && !$statement::invalid) {
            cslist.add(cs);
            log.debug("Inserted combined selector("+cslist.size()+") into ruleset");
         }   
        } )*
        (d=declaration 
        {if(d!=null && !d.isEmpty()) {
            declarations.add(d);
            log.debug("Inserted declaration("+ declarations.size() +") into ruleset");
         }
        })*        
    )
    ;  

/**
 * CSS declaration
 */
declaration returns [Declaration decl]
scope {
    Declaration d;
    boolean invalid;
} 
@init {
    logEnter("declaration");
    $declaration::d = $decl = rf.createDeclaration();
    $declaration::invalid = false;
} 
@after {
    if($declaration::invalid) {
        $decl=null;
        log.debug("Declaration was invalidated or already invalid");
    }
    else if(log.isDebugEnabled()) {
        log.debug("Returning declaration: " + $decl);
    }
    logLeave("declaration");    
}
  : ^(DECLARATION 
	  (important { $decl.setImportant(true);})?
      property 
      t=terms {$decl.replaceAll(t);}      
     )
	 | INVALID_DECLARATION { $declaration::invalid=true;}
  ;

important
    : IMPORTANT
    ;   

/**
 * Setting property of declaration
 */  
property
@init {
    logEnter("property");
}
@after {
    if(log.isDebugEnabled()) {
        log.debug("Set property: " + $declaration::d.getProperty());
    }
    logLeave("property");
}    
  : i = IDENT { $declaration::d.setProperty(extractText(i)); }
  ;

/**
 * Term of CSSDeclaration
 */
terms returns [List<Term<?>> tlist]
scope {
    List<Term<?>> list;
    Term<?> term;
    Term.Operator op;
    int unary;
}   
@init {
    logEnter("terms");
    $terms::list = $tlist = new ArrayList<Term<?>>();
    $terms::term = null;
    $terms::op = null;
    $terms::unary = 1;
}    
@after {
    if(log.isDebugEnabled()) {
        log.debug("Totally added "  + $tlist.size() + " terms");
    }
    logLeave("terms");
}
    : ^(VALUE term+)
    ;
    
term
    : valuepart 
      {// set operator, store and create next 
       if(!$declaration::invalid && $terms::term!=null) {     
          $terms::term.setOperator($terms::op);
          $terms::list.add($terms::term);
          // reinitialization
          $terms::op = Term.Operator.SPACE;
          $terms::unary = 1;
          $terms::term = null;
       }    
      }
    | CURLYBLOCK { $declaration::invalid = true;}
    | ATKEYWORD { $declaration::invalid = true;}
    ;   

valuepart
@after{
    // convert color
    Term<?> term = $terms::term;
    if(term!=null) {
        TermColor colorTerm = null;
        if(term instanceof TermIdent) {
            colorTerm = tf.createColor((TermIdent)term);
            if (colorTerm != null)
                term = colorTerm;
        }
        else if(term instanceof TermFunction) {
            colorTerm = tf.createColor((TermFunction)term);
            if(colorTerm != null)
                term = colorTerm;
        }
        // replace with color
        if(colorTerm!=null) {
            $terms::term = colorTerm;
        }                    
    }
}
    : i=IDENT   {$terms::term = tf.createIdent(extractText(i));}
    | CLASSKEYWORD {$declaration::invalid = true;}
	| (MINUS {$terms::unary=-1;})? n=NUMBER    {$terms::term = tf.createNumeric(extractText(n), $terms::unary);}
    | (MINUS {$terms::unary=-1;})? p=PERCENTAGE  { $terms::term = tf.createPercent(extractText(p), $terms::unary);}
    | (MINUS {$terms::unary=-1;})? d=DIMENSION   
	{String dim = extractText(d);
	 $terms::term = tf.createDimension(dim, $terms::unary);
     if($terms::term==null) {
		 if(log.isDebugEnabled()) {
		 	log.debug("Unable to create dimension from dim: " + dim + " unary: " +
			$terms::unary);
		 }
         $declaration::invalid = true;
	 }
    }
    | s=STRING    {$terms::term = tf.createString(extractText(s));}
    | u=URI       {$terms::term = tf.createURI(extractText(u));}
    | h=HASH    
    {$terms::term = tf.createColor(extractText(h));
     if($terms::term==null)
         $declaration::invalid = true;
    }
    | UNIRANGE  {$declaration::invalid = true;}
    | INCLUDES  {$declaration::invalid = true;}
    | COLON     {$declaration::invalid = true;}
    | COMMA     {$terms::op = Term.Operator.COMMA;}    
    | GREATER   {$declaration::invalid = true;}
    | EQUALS    {$declaration::invalid = true;}
    | SLASH     {$terms::op = Term.Operator.SLASH;}
	| PLUS		{$declaration::invalid = true;}
	| ASTERISK  {$declaration::invalid = true;}
    | ^(f=FUNCTION t=terms) {
        // create function
        TermFunction function = tf.createFunction();
        function.setFunctionName(extractText(f));
        function.setValue(t);
        $terms::term = function;
    }
    | DASHMATCH {$declaration::invalid = true;}
    | ^(PARENBLOCK any*) {$declaration::invalid = true;}
    | ^(BRACEBLOCK any*) {$declaration::invalid = true;}    
  ;
  
/**
 * Construction of selector
 */
combined_selector returns [CombinedSelector combinedSelector]
scope {
    boolean invalid;
}
@init {
	logEnter("combined_selector");	  
	$combinedSelector = (CombinedSelector) rf.createCombinedSelector().unlock();
}
@after {  
    // entire ruleset is not valid when selector is not valid
    // there is no need to parse selector's when already marked as invalid
    if($statement::invalid || $combined_selector::invalid) {        
        $combinedSelector = null;
        if(log.isDebugEnabled()) {
            if($statement::invalid)
                log.debug("Ommiting combined selector, whole statement discarded");
            else
                log.debug("Combined selector is invalid");               
        }
        // mark whole ruleset as invalid
        $statement::invalid = true;
    }
    else if(log.isDebugEnabled()) {
        log.debug("Returing combined selector: " + $combinedSelector.toString()); 
    }
    logLeave("combined_selector"); 
}    
	: s=selector {
	     $combinedSelector.add(s);
	  }
	 (c=combinator s=selector {
	     s.setCombinator(c);
	     $combinedSelector.add(s);	
	  }
	 )*
	;

combinator returns [Selector.Combinator combinator]
@init{ logEnter("combinator"); }
@after{ logLeave("combinator"); }
	: CHILD {$combinator=Selector.Combinator.CHILD;}
	| ADJACENT {$combinator=Selector.Combinator.ADJACENT;}
	| DESCENDANT {$combinator=Selector.Combinator.DESCENDANT;}
	;


selector returns [Selector sel]
scope {
	Selector s;
}
@init {
	logEnter("selector");
	$selector::s=$sel=(Selector)rf.createSelector().unlock();
	Selector.ElementName en = rf.createElement(Selector.SelectorPart.WILDCARD);
}
@after {
	logLeave("selector");
}
    : ^(SELECTOR 
        ^(ELEMENT 
          (i=IDENT { en.setValue(extractText(i)); }
          )?         
         ){
		  if(log.isDebugEnabled()) {
		      log.debug("Adding element name: " + en.getValue());
		  }	  
		  $selector::s.add(en);
		 }
         selpart*
       )
    | ^(SELECTOR 
         selpart+
       )
  ;

selpart
@init {
	logEnter("selpart");
}
@after {
    logLeave("selpart");
}	
    : PSEUDO i=IDENT { $selector::s.add(rf.createPseudoPage(extractText(i), null));}
    | h=HASH { $selector::s.add(rf.createID(extractText(h))); }
    | c=CLASSKEYWORD { $selector::s.add(rf.createClass(extractText(c))); }
	| ^(ATTRIBUTE ea=attribute { $selector::s.add(ea);} )
    | ^(fname=FUNCTION farg=IDENT {
       $selector::s.add(rf.createPseudoPage(extractText(farg), extractText(fname)));
       }
      )
    ;
 
attribute returns [Selector.ElementAttribute elemAttr]
@init {
    logEnter("attribute");
    String attribute = null;
	String value = null;
	Selector.Operator op = Selector.Operator.NO_OPERATOR;
	boolean isStringValue = false;
}
@after{
    if(attribute!=null) {
		$elemAttr = rf.createAttribute(value, isStringValue, op, attribute);
	}
	else {
	    log.debug("Invalid attribute element in selector");
	    $combined_selector::invalid = true;
	}
    logLeave("attribute");
}
	: i=IDENT {attribute=extractText(i); }
	  ((EQUALS {op=Selector.Operator.EQUALS; } 
	   | INCLUDES {op=Selector.Operator.INCLUDES; } 
	   | DASHMATCH {op=Selector.Operator.DASHMATCH; }
	   ) 
	   (i=IDENT {
		value=extractText(i);
		isStringValue=false;
		}
	   | s=STRING {
		 value=extractText(s);
		 isStringValue=true;
		}
	   ))?
	; 
  
any
  : IDENT
  | CLASSKEYWORD
  | NUMBER
  | PERCENTAGE
  | DIMENSION
  | STRING
  | URI
  | HASH
  | UNIRANGE
  | INCLUDES
  | COLON
  | COMMA
  | GREATER
  | EQUALS
  | SLASH
  | EXCLAMATION
  | ^(FUNCTION any*) 
  | DASHMATCH
  | ^(PARENBLOCK any*)
  | ^(BRACEBLOCK any*)
  ;
