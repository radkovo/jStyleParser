tree grammar CSSTreeParser;

options {
	tokenVocab=CSS;
	ASTLabelType=CommonTree;
}

@header {
package cz.vutbr.web.csskit.antlr;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleBlock;
import cz.vutbr.web.css.RuleFactory;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.RulePage;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.SupportedCSS;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermExpression;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.RuleBlock.Priority;

// @SuppressWarnings("unchecked")
}

@members {
	private static Logger log = LoggerFactory.getLogger(CSSTreeParser.class);

	private static RuleFactory rf = CSSFactory.getRuleFactory();
	private static TermFactory tf = CSSFactory.getTermFactory();
	private static SupportedCSS css = CSSFactory.getSupportedCSS();

	private static class TreeParserState {
	    public List<String> media;
		
		public TreeParserState(String media) {
			if(media==null || media.length()==0)
			    this.media = Collections.emptyList();
			else	
		    	this.media = Arrays.asList(media.split(","));
		}
		
		public boolean doWrap() {
		   return !media.isEmpty();
		}
		
		@Override
		public String toString() {
		    return media.toString();
		}
	}

    // block preparator
	private Preparator preparator;
	
	private StyleSheet stylesheet;

	private Stack<TreeParserState> imports;	
       
    public CSSTreeParser init(StyleSheet sheet, Preparator preparator) {
	    this.stylesheet = sheet;
		this.preparator = preparator;
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
   
  private URL extractBase(CommonTree token) {
      CSSToken ct = (CSSToken) token.getToken();
      return ct.getBase();
  }  	
		
    private void logEnter(String entry) {
        log.trace("Entering '{}'", entry);
    }
    	
    private void logLeave(String leaving) {
	    log.trace("Leaving '{}'", leaving);
    }
}

inlinestyle returns [StyleSheet sheet]
@init {
	logEnter("inlinestyle");
	$sheet = this.stylesheet;
} 
@after {
	log.debug("\n***\n{}\n***\n", $sheet);	   
	// mark last usage
	$sheet.markLast(preparator.markPriority());
	logLeave("inlinestyle");
}
	: 	^(INLINESTYLE decl=declarations) 
		{
			RuleBlock<?> rb = preparator.prepareInlineRuleSet(decl, null);
			if(rb!=null) {
			     $sheet.add(rb);
			}
		} 
	|   ^(INLINESTYLE 
		 	(irs=inlineset {if(irs!=null) $sheet.add(irs);} )+ )
	;


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
	// mark last usage
	$sheet.markLast(preparator.markPriority());
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
	
	// this flag allows us to encapsulate rulesets
	// into media when media import is used
	boolean insideAtstatement;	 
	
}
@init {
	logEnter("statement");
	$statement::invalid = false;
}   
@after {
  if ($statement::invalid)
      log.debug("Statement is invalid");
	logLeave("statement");
}
	: rs=ruleset {$stm=(RuleBlock<?>) rs;} 
	| ats=atstatement {$stm=(RuleBlock<?>) ats;}
	| INVALID_STATEMENT { $statement::invalid = true; }
	;
	

atstatement returns [RuleBlock<?> stmnt]
scope {
	RuleBlock<?> stm;
}
@init {
    logEnter("atstatement");
	$statement::insideAtstatement=true;
	$atstatement::stm = $stmnt = null;
	List<RuleSet> rules = null;
	String pseudo = null;
	Priority mark = preparator.markPriority();
}
@after {
    logLeave("atstatement");
}
	: CHARSET	// charset already set
	| INVALID_IMPORT // already handled
	| i=IMPORT 
	  {
	    String media = extractText(i);
		imports.push(new TreeParserState(media));
		
		log.info("From imported file: Rules will use these media: {}", 
			imports.peek());
	  }
	| IMPORT_END {
	    imports.pop();
		log.info("Imported file was parsed, returing in nesting.");
	  }
	| ^(PAGE (i=IDENT{ pseudo=extractText(i);})? decl=declarations)
		{ $stmnt = preparator.prepareRulePage(decl, pseudo); }
	| ^(MEDIA (mediaList=media)? 
			(  rs=ruleset {
					   if(rules==null) rules = new ArrayList<RuleSet>();				
					   if(rs!=null) {
						   // this cast should be safe, because when inside of @statetement, oridinal ruleset
						   // is returned
					       rules.add((RuleSet)rs);
						   log.debug("Inserted ruleset ({}) into @media", rules.size());
					   }
					}
			  | INVALID_STATEMENT { log.debug("Skiping invalid statement in media"); }
			
			)*
	   )	
	   {
		   $stmnt = preparator.prepareRuleMedia(mark, rules, mediaList);
	   }
	;
	
media returns [List<String> affected] 
@init {
   logEnter("media");
   $affected = new ArrayList<String>();
}
@after {
   log.debug("Totally returned {} media.", $affected.size());							  
   logLeave("media");		   
}
	: (i=IDENT {
				   String m = extractText(i);
				   if(css.isSupportedMedia(m)) $affected.add(m);
    } )+
	;
    
inlineset returns [RuleBlock<?> is]
@init {
     logEnter("inlineset");
	 List<Selector.PseudoPage> pplist = new ArrayList<Selector.PseudoPage>();
}
@after {
     logLeave("inlineset");   
}
	: ^(RULE (p=pseudo {pplist.add(p);})* decl=declarations)
	  	{ $is = preparator.prepareInlineRuleSet(decl, pplist); }
	;
    
    
/**
 * The most common block in CSS file,
 * set of declarations with selector
 */  
ruleset returns [RuleBlock<?> stmnt]
@init {
    logEnter("ruleset"); 
    List<CombinedSelector> cslist = new ArrayList<CombinedSelector>();
}
@after {
    if($statement::invalid) {
        $stmnt = null;
        log.debug("Ruleset not valid, so not created");
    }
    else {    
		 $stmnt = preparator.prepareRuleSet(cslist, decl, 
		 	!$statement::insideAtstatement && !imports.isEmpty() && imports.peek().doWrap(), 
			imports.isEmpty() ? null : imports.peek().media);
        }		
    logLeave("ruleset");
}    
    : ^(RULE 
        (cs=combined_selector  
        {if(cs!=null && !cs.isEmpty() && !$statement::invalid) {
            cslist.add(cs);
            log.debug("Inserted combined selector ({}) into ruleset",  cslist.size());
         }   
        } )*
		decl=declarations 
    )
    ;  

/**
 * Multiple CSS declarations
 */ 
declarations returns [List<Declaration> decl]
@init {
		  logEnter("declarations");
		  $decl = new ArrayList<Declaration>();
}
@after {
		   logLeave("declarations");
}
	: ^(SET (d=declaration {
	     if(d!=null) {
            $decl.add(d);
            log.debug("Inserted declaration #{} ", $decl.size()+1);
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
    if($declaration::invalid || $declaration.isEmpty()) {
        $decl=null;
        log.debug("Declaration was invalidated or already invalid");
    }
    else {
        log.debug("Returning declaration: {}.", $decl);
    }
    logLeave("declaration");    
}
  : ^(DECLARATION 
	    (important { $decl.setImportant(true); log.debug("IMPORTANT"); })?
      (INVALID_DIRECTIVE { $declaration::invalid=true; })?
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
  | MINUS i = IDENT { $declaration::d.setProperty("-" + extractText(i)); }
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
    boolean dash;
}   
@init {
    logEnter("terms");
    $terms::list = $tlist = new ArrayList<Term<?>>();
    $terms::term = null;
    $terms::op = null;
    $terms::unary = 1;
    $terms::dash = false;
}    
@after {
	log.debug("Totally added {} terms", $tlist.size());	   
    logLeave("terms");
}
    : ^(VALUE term+)
    ;
    
term
@init {
  logEnter("term");
}
    : valuepart 
      {// set operator, store and create next 
       if(!$declaration::invalid && $terms::term!=null) {
          $terms::term.setOperator($terms::op);
          $terms::list.add($terms::term);
          // reinitialization
          $terms::op = Term.Operator.SPACE;
          $terms::unary = 1;
          $terms::dash = false;
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
    : (MINUS {$terms::dash=true;})? i=IDENT   {$terms::term = tf.createIdent(extractText(i), $terms::dash);}
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
    | u=URI       {$terms::term = tf.createURI(extractText(u), extractBase(u));}
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
    | LESS      {$declaration::invalid = true;}
    | QUESTION  {$declaration::invalid = true;}
    | PERCENT   {$declaration::invalid = true;}
    | EQUALS    {$declaration::invalid = true;}
    | SLASH     {$terms::op = Term.Operator.SLASH;}
		| PLUS		  {$declaration::invalid = true;}
		| ASTERISK  {$declaration::invalid = true;}
		| e=EXPRESSION {
		    String exprval = extractText(e);
        TermExpression expr = tf.createExpression(exprval.substring(11,exprval.length()-1)); //strip the 'expression()'
        $terms::term = expr;
		}
    | (MINUS {$terms::unary=-1;})? ^(f=FUNCTION t=terms?) {
        // create function
        TermFunction function = tf.createFunction();
        function.setFunctionName(extractText(f));
        if ($terms::unary == -1) //if started with minus, add the minus to the function name
            function.setFunctionName('-' + function.getFunctionName());
        if (t != null)
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
	Selector.ElementName en = rf.createElement(Selector.ElementName.WILDCARD);
}
@after {
	logLeave("selector");
}
    : ^(SELECTOR 
        ^(ELEMENT 
          (i=IDENT { en.setName(extractText(i)); }
          )?         
         ){
		  log.debug("Adding element name: {}.", en.getName());
		  $selector::s.add(en);
		 }
         selpart*
       )
    | ^(SELECTOR 
         selpart+
       )
    | INVALID_SELECTOR { $statement::invalid = true; }	   
  ;

selpart
@init {
	logEnter("selpart");
}
@after {
    logLeave("selpart");
}
    :  h=HASH { $selector::s.add(rf.createID(extractText(h))); }
    | c=CLASSKEYWORD { $selector::s.add(rf.createClass(extractText(c))); }
	| ^(ATTRIBUTE ea=attribute { $selector::s.add(ea);} )
    | p=pseudo { $selector::s.add(p);}
	| INVALID_SELPART { $combined_selector::invalid = true;}  
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
	
pseudo returns [Selector.PseudoPage pseudoPage]
@init {
		logEnter("pseudo");
}
	: ^(PSEUDO i=IDENT)
		{
			$pseudoPage = rf.createPseudoPage(extractText(i), null);
		}
	| ^(PSEUDO f=FUNCTION i=IDENT)
		{
			$pseudoPage = rf.createPseudoPage(extractText(i), extractText(f));
		}
	| ^(PSEUDO f=FUNCTION n=NUMBER)
		{
			$pseudoPage = rf.createPseudoPage(extractText(n), extractText(f));
		}
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
