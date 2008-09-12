tree grammar CSSTreeParser;

options {
	tokenVocab=CSS;
	ASTLabelType=CommonTree;
}

@header {
package cz.vutbr.web.csskit.antlr;

import java.io.IOException;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.RuleFactory;
import cz.vutbr.web.css.StyleSheetNotValidException;
import cz.vutbr.web.css.*;

}

@members {
	private static Logger log = LoggerFactory.getLogger(CSSTreeParser.class);

	private static RuleFactory rf = CSSFactory.getRuleFactory();
	private static TermFactory tf = CSSFactory.getTermFactory();
	private static SupportedCSS css = CSSFactory.getSupportedCSS();


	private static class TreeParserState {
	    public List<String> medias;
		
		public TreeParserState(String medias) {
		    this.medias = Arrays.asList(medias.split(","));
		}
		
		@Override
		public String toString() {
		    return medias.toString();
		}
	}

	// current number of rule
	private int ruleNum = 0;
	
	private StyleSheet stylesheet;

	private Stack<TreeParserState> imports;

	public static CSSTreeParser createParser(CSSInputStream input) 
		throws StyleSheetNotValidException, IOException, RecognitionException {	
		
		StyleSheet stylesheet = (StyleSheet) rf.createStyleSheet().unlock();
		
		CSSLexer lexer = new CSSLexer(input);
		lexer.init(stylesheet);
		
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CSSParser parser = new CSSParser(tokens);
		parser.init(stylesheet);
		
        // Invoke the program rule in get return value
        CSSParser.stylesheet_return r = parser.stylesheet();
        CommonTree t = (CommonTree) r.getTree();

    	if(log.isDebugEnabled()) {
        	log.debug("* CSSLexer Tree was:\n{}", TreeUtil.toStringTree(t));
        }            	        	
            	
        // Walk resulting tree; create treenode stream first
        CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
        
		// AST nodes have payloads that point into token stream
        nodes.setTokenStream(tokens);
        
		// Create a tree Walker attached to the nodes stream
        return (new CSSTreeParser(nodes)).init(stylesheet);
    }
       
    public CSSTreeParser init(StyleSheet sheet) {
	    this.stylesheet = sheet;
		this.imports = new Stack<TreeParserState>();
		return this;
	}   
       
    @Override
	public void emitErrorMessage(String msg) {
	    log.info("ANTLR: {}", msg);
	}
		
	private String extractText(CommonTree token) {
        return token.getText();
    }
    	
		
    private void logEnter(String entry) {
        log.trace("Entering '{}'", entry);
    }
    	
    private void logLeave(String leaving) {
	    log.trace("Leaving '{}'", leaving);
    }
}

/**
 * Stylesheet, main rule
 */
stylesheet returns [StyleSheet sheet]
@init {
	logEnter("stylesheet");
	$sheet = this.stylesheet;
} 
@after {
	log.debug("\n***\n{}\n***\n", $sheet);	   
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
	| ats=atstatement {$stm=(RuleBlock<?>) ats;}
	;
	

atstatement returns [RuleBlock<?> stmnt]
scope {
	RuleBlock<?> stm;
}
@init {
    logEnter("atstatement");
	$atstatement::stm = $stmnt = null;
	List<Declaration> declarations = null;
	List<RuleSet> rules = null;
	String pseudo = null;
	int filePosition = ruleNum;
}
@after {
    logLeave("atstatement");
}
	: CHARSET	// charset already set
	| INVALID_IMPORT // already handled
	| i=IMPORT 
	  {
	    String medias = extractText(i);
		imports.push(new TreeParserState(medias));
		
		log.info("From imported file: Rules will use these medias: {}", 
			imports.peek());
	  }
	| IMPORT_END {
	    imports.pop();
		log.info("Imported file was parsed, returing in nesting.");
	  }
	| ^(PAGE (i=IDENT{ pseudo=extractText(i);})? (d=declaration
			{
				if(declarations==null)
				    declarations=new ArrayList<Declaration>();
				if(d!=null && !d.isEmpty()) {
            		declarations.add(d);
            	    log.debug("Inserted declaration ({}) into @page",
					    declarations.size());
				}
			}	   
		)*) 
		{
		   	if(declarations!=null && !declarations.isEmpty()) {	
            	RulePage rp = rf.createPage(ruleNum++);
                rp.replaceAll(declarations);
                rp.setPseudo(pseudo);
                $stmnt = rp;
                log.info("Create @page as {}th with:\n{}", 
                	ruleNum, rp);
            }
        }
	| ^(MEDIA (mediaList=medias)? 
			(rs=ruleset {
			   if(rules==null) rules = new ArrayList<RuleSet>();				
			   if(rs!=null) {
				   // increment rule number
				   rs.setFilePosition(rs.getFilePosition()+1);			
			       rules.add(rs);
				   log.debug("Inserted ruleset ({}) into @media",
				   		rules.size());
			   }
		
			})*
	   )	
	   {
		   if(rules!=null && !rules.isEmpty()) {
			  // create at begining, increment to match positions								   
              RuleMedia rm = rf.createMedia(filePosition);
			  ruleNum++;
			  
			  rm.replaceAll(rules);
			  if(mediaList!=null) rm.setMedias(mediaList);
			  $stmnt = rm;
              log.info("Create @media as {}th with:\n{}", 
                	rm.getFilePosition(), rm);
			  
		   }
	   }
	| INVALID_ATSTATEMENT {$statement::invalid=true;}
	;
	
medias returns [List<String> affected] 
@init {
   logEnter("medias");
   $affected = new ArrayList<String>();
}
@after {
   log.debug("Totally returned {} medias.", $affected.size());							  
   logLeave("medias");		   
}
	: (i=IDENT {$affected.add(extractText(i));} )+
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
		log.info("Create ruleset as {}th with:\n{}", ruleNum, $rs);
    }
    logLeave("ruleset");
}    
    : ^(RULE 
        (cs=combined_selector  
        {if(cs!=null && !cs.isEmpty() && !$statement::invalid) {
            cslist.add(cs);
            log.debug("Inserted combined selector ({}) into ruleset", 
				cslist.size());
         }   
        } )*
        (d=declaration 
        {if(d!=null && !d.isEmpty()) {
            declarations.add(d);
            log.debug("Inserted declaration ({}) into ruleset",
				declarations.size());
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
    else {
        log.debug("Returning declaration: {}.", $decl);
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
	log.debug("Setting property: {}", $declaration::d.getProperty());	   
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
	log.debug("Totally added {} terms", $tlist.size());	   
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
		 log.info("Unable to create dimension from {}, unary {}", dim, $terms::unary);
         $declaration::invalid = true;
	 }
    }
    | s=string    
	{ if(s!=null) $terms::term = tf.createString(s);
	  else $declaration::invalid=true;
	}
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
        if($statement::invalid) { 
			log.debug("Ommiting combined selector, whole statement discarded");
		}	
        else { 
			log.debug("Combined selector is invalid");               
        }
		// mark whole ruleset as invalid
        $statement::invalid = true;
    }
    else {
        log.debug("Returing combined selector: {}.", $combinedSelector); 
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
		  log.debug("Adding element name: {}.", en.getValue());
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
	   | s=string {
		 if(s!=null)  { 
			value=s;
			isStringValue=true;
		 }	
		 else {
			$combined_selector::invalid=true;
		 }
		}
	   ))?
	; 

string returns [String s]
	: st=STRING { $s= extractText(st);}
	| INVALID_STRING {$s=null;}
	;
  
any
  : IDENT
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
  | EQUALS
  | SLASH
  | EXCLAMATION
  | ^(FUNCTION any*) 
  | DASHMATCH
  | ^(PARENBLOCK any*)
  | ^(BRACEBLOCK any*)
  ;
