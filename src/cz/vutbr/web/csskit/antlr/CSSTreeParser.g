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

	private int ruleNum = 0;

	public static CSSTreeParser createParser(Reader reader) 
		throws StyleSheetNotValidException, IOException, RecognitionException {	
		
		CSSLexer lexer = new CSSLexer(new ANTLRReaderStream(reader));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CSSParser parser = new CSSParser(tokens);
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
	$stylesheet::style=$sheet = rf.createStyleSheet();
	$sheet.replaceAll(new ArrayList<RuleBlock<?>>());
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
    : ^(ATBLOCK ATKEYWORD any* block?)
    ;
  
block       
    : ^(CURLYBLOCK blockpart*)
    ;

blockpart
  : any 
  | block 
  | ATKEYWORD 
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
        (s=selector  
        {if(s!=null && !s.isEmpty() && !$statement::invalid) {
            cslist.add(s);
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
  
selector returns [CombinedSelector combinedSelector]
scope {
    CombinedSelector cs;
    Selector s;
    Selector.SelectorPart part;
    boolean invalid; 
}
@init {
    logEnter("selector");
    $selector::cs = $combinedSelector = rf.createCombinedSelector();
    $selector::cs.replaceAll(new ArrayList<Selector>());
    $selector::s = rf.createSelector();
    $selector::part = null;
    $selector::invalid = false;    
}
@after {

    // append last selector if appropriate
    if(!$selector::invalid && !$statement::invalid) {
        $selector::cs.add($selector::s);
    }   

    // entire ruleset is not valid when selector is not valid
    // there is no need to parse selector's when already marked as invalid
    if($statement::invalid || $selector::invalid) {        
        $combinedSelector = null;
        if(log.isDebugEnabled()) {
            if($statement::invalid)
                log.debug("Ommiting selector, whole statement discarded");
            else
                log.debug("Selector is invalid");               
        }
        // mark whole ruleset as invalid
        $statement::invalid = true;
    }
    else if(log.isDebugEnabled()) {
        log.debug("Returing combined selector: " + $selector::cs.toString()); 
    }
    logLeave("selector");
}   
  : ^(SELECTOR selpart+)
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
        log.debug("Declaration was invalidated");
    }
    else if(log.isDebugEnabled()) {
        log.debug("Returning declaration: " + $decl);
    }
    logLeave("declaration");    
}
  : ^(DECLARATION 
      property 
      t=terms {$decl.replaceAll(t);}
     )
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
          // initialization
          $terms::op = Term.Operator.SPACE;
          $terms::unary = 1;
          $terms::term = null;
       }    
      }
    | block { $declaration::invalid = true;}
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
    | IDKEYWORD {$declaration::invalid = true;}
    | n=NUMBER    {$terms::term = tf.createNumeric(extractText(n), $terms::unary);}
    | p=PERCENTAGE  { $terms::term = tf.createPercent(extractText(p), $terms::unary);}
    | d=DIMENSION   
    {$terms::term = tf.createDimension(extractText(d), $terms::unary);
     if($terms::term==null)
         $declaration::invalid = true;
    }
    | s=STRING    {$terms::term = tf.createString(extractText(s));}
    | u=URI       {$terms::term = tf.createURI(extractText(u));}
    | h=HASH    
    {$terms::term = tf.createColor(extractText(h));
     if($terms::term==null)
         $declaration::invalid = true;
    }
    | UNIRANGE  // just ignore
    | INCLUDES  {$declaration::invalid = true;}
    | COLON     {$declaration::invalid = true;}
    | COMMA     {$terms::op = Term.Operator.COMMA;}    
    | GREATER   {$declaration::invalid = true;}
    | EQUALS    {$declaration::invalid = true;}
    | SLASH     {$terms::op = Term.Operator.SLASH;}
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
selpart
@init {
    logEnter("selpart");
}
@after {
    logLeave("selpart");
}
  : i=IDENT {
    $selector::s.setFirstItem(rf.createElement(extractText(i)));
  }
  | IDKEYWORD
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
  | ^(FUNCTION any*) 
  | DASHMATCH
  | ^(PARENBLOCK any*)
  | ^(BRACEBLOCK any*)
  ;
  
any
  : IDENT
  | IDKEYWORD
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
  | ^(FUNCTION any*) 
  | DASHMATCH
  | ^(PARENBLOCK any*)
  | ^(BRACEBLOCK any*)
  ;
