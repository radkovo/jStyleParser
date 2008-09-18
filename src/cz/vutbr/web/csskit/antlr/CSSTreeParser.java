// $ANTLR 3.1 CSSTreeParser.g 2008-09-14 19:23:57

package cz.vutbr.web.csskit.antlr;

import java.io.IOException;

import java.util.Arrays;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.RuleFactory;
import cz.vutbr.web.css.StyleSheetNotValidException;
import cz.vutbr.web.css.*;



import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CSSTreeParser extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "STYLESHEET", "ATBLOCK", "CURLYBLOCK", "PARENBLOCK", "BRACEBLOCK", "RULE", "SELECTOR", "ELEMENT", "PSEUDO", "ADJACENT", "CHILD", "DESCENDANT", "ATTRIBUTE", "DECLARATION", "VALUE", "IMPORTANT", "IMPORT_END", "INVALID_STRING", "INVALID_SELECTOR", "INVALID_SELPART", "INVALID_DECLARATION", "INVALID_STATEMENT", "INVALID_IMPORT", "CDO", "CDC", "S", "CHARSET", "IMPORT", "PAGE", "COLON", "IDENT", "LCURLY", "SEMICOLON", "RCURLY", "MEDIA", "ATKEYWORD", "COMMA", "EXCLAMATION", "CLASSKEYWORD", "MINUS", "NUMBER", "PERCENTAGE", "DIMENSION", "URI", "HASH", "UNIRANGE", "INCLUDES", "GREATER", "EQUALS", "SLASH", "PLUS", "ASTERISK", "FUNCTION", "RPAREN", "DASHMATCH", "LPAREN", "LBRACE", "RBRACE", "STRING", "IDENT_MACR", "STRING_MACR", "NAME_MACR", "NUMBER_MACR", "W_MACR", "URI_MACR", "APOS", "QUOT", "W_CHAR", "COMMENT", "SL_COMMENT", "INVALID_TOKEN", "NAME_START", "NAME_CHAR", "NON_ASCII", "ESCAPE_CHAR", "STRING_CHAR", "URI_CHAR", "NL_CHAR", "'important'"
    };
    public static final int COMMA=40;
    public static final int ELEMENT=11;
    public static final int INVALID_IMPORT=26;
    public static final int CHARSET=30;
    public static final int MINUS=43;
    public static final int BRACEBLOCK=8;
    public static final int PARENBLOCK=7;
    public static final int HASH=48;
    public static final int DASHMATCH=58;
    public static final int IMPORT_END=20;
    public static final int SELECTOR=10;
    public static final int NUMBER=44;
    public static final int PAGE=32;
    public static final int INVALID_TOKEN=74;
    public static final int URI_CHAR=80;
    public static final int NAME_MACR=65;
    public static final int RULE=9;
    public static final int CLASSKEYWORD=42;
    public static final int INCLUDES=50;
    public static final int MEDIA=38;
    public static final int VALUE=18;
    public static final int DESCENDANT=15;
    public static final int PSEUDO=12;
    public static final int STYLESHEET=4;
    public static final int URI=47;
    public static final int IMPORT=31;
    public static final int LBRACE=60;
    public static final int RBRACE=61;
    public static final int INVALID_SELECTOR=22;
    public static final int S=29;
    public static final int RCURLY=37;
    public static final int DECLARATION=17;
    public static final int LCURLY=35;
    public static final int ESCAPE_CHAR=78;
    public static final int FUNCTION=56;
    public static final int W_MACR=67;
    public static final int RPAREN=57;
    public static final int IDENT_MACR=63;
    public static final int NAME_CHAR=76;
    public static final int NAME_START=75;
    public static final int GREATER=51;
    public static final int LPAREN=59;
    public static final int W_CHAR=71;
    public static final int PLUS=54;
    public static final int NON_ASCII=77;
    public static final int SL_COMMENT=73;
    public static final int APOS=69;
    public static final int ATKEYWORD=39;
    public static final int STRING_CHAR=79;
    public static final int URI_MACR=68;
    public static final int IDENT=34;
    public static final int SLASH=53;
    public static final int UNIRANGE=49;
    public static final int IMPORTANT=19;
    public static final int EXCLAMATION=41;
    public static final int STRING=62;
    public static final int NL_CHAR=81;
    public static final int INVALID_STRING=21;
    public static final int COMMENT=72;
    public static final int CDC=28;
    public static final int ADJACENT=13;
    public static final int INVALID_DECLARATION=24;
    public static final int NUMBER_MACR=66;
    public static final int EQUALS=52;
    public static final int CURLYBLOCK=6;
    public static final int DIMENSION=46;
    public static final int INVALID_STATEMENT=25;
    public static final int SEMICOLON=36;
    public static final int STRING_MACR=64;
    public static final int EOF=-1;
    public static final int ASTERISK=55;
    public static final int COLON=33;
    public static final int T__82=82;
    public static final int CDO=27;
    public static final int ATTRIBUTE=16;
    public static final int CHILD=14;
    public static final int INVALID_SELPART=23;
    public static final int PERCENTAGE=45;
    public static final int QUOT=70;
    public static final int ATBLOCK=5;

    // delegates
    // delegators


        public CSSTreeParser(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public CSSTreeParser(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return CSSTreeParser.tokenNames; }
    public String getGrammarFileName() { return "CSSTreeParser.g"; }


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



    // $ANTLR start "stylesheet"
    // CSSTreeParser.g:117:1: stylesheet returns [StyleSheet sheet] : ^( STYLESHEET (s= statement )* ) ;
    public final StyleSheet stylesheet() throws RecognitionException {
        StyleSheet sheet = null;

        RuleBlock<?> s = null;



        	logEnter("stylesheet");
        	sheet = this.stylesheet;

        try {
            // CSSTreeParser.g:129:2: ( ^( STYLESHEET (s= statement )* ) )
            // CSSTreeParser.g:129:4: ^( STYLESHEET (s= statement )* )
            {
            match(input,STYLESHEET,FOLLOW_STYLESHEET_in_stylesheet60); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // CSSTreeParser.g:130:4: (s= statement )*
                loop1:
                do {
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==RULE||LA1_0==IMPORT_END||(LA1_0>=INVALID_STATEMENT && LA1_0<=INVALID_IMPORT)||(LA1_0>=CHARSET && LA1_0<=PAGE)||LA1_0==MEDIA) ) {
                        alt1=1;
                    }


                    switch (alt1) {
                	case 1 :
                	    // CSSTreeParser.g:130:5: s= statement
                	    {
                	    pushFollow(FOLLOW_statement_in_stylesheet69);
                	    s=statement();

                	    state._fsp--;

                	     if(s!=null) sheet.add(s);

                	    }
                	    break;

                	default :
                	    break loop1;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }


            	log.debug("\n***\n{}\n***\n", sheet);	   
            	logLeave("stylesheet");

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return sheet;
    }
    // $ANTLR end "stylesheet"

    protected static class statement_scope {
        boolean invalid;
        boolean insideAtstatement;
    }
    protected Stack statement_stack = new Stack();


    // $ANTLR start "statement"
    // CSSTreeParser.g:134:1: statement returns [RuleBlock<?> stm] : (rs= ruleset | ats= atstatement );
    public final RuleBlock<?> statement() throws RecognitionException {
        statement_stack.push(new statement_scope());
        RuleBlock<?> stm = null;

        RuleBlock<?> rs = null;

        RuleBlock<?> ats = null;



        	logEnter("statement");
        	((statement_scope)statement_stack.peek()).invalid = false;

        try {
            // CSSTreeParser.g:153:2: (rs= ruleset | ats= atstatement )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE) ) {
                alt2=1;
            }
            else if ( (LA2_0==IMPORT_END||(LA2_0>=INVALID_STATEMENT && LA2_0<=INVALID_IMPORT)||(LA2_0>=CHARSET && LA2_0<=PAGE)||LA2_0==MEDIA) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // CSSTreeParser.g:153:4: rs= ruleset
                    {
                    pushFollow(FOLLOW_ruleset_in_statement118);
                    rs=ruleset();

                    state._fsp--;

                    stm =(RuleBlock<?>) rs;

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:154:4: ats= atstatement
                    {
                    pushFollow(FOLLOW_atstatement_in_statement128);
                    ats=atstatement();

                    state._fsp--;

                    stm =(RuleBlock<?>) ats;

                    }
                    break;

            }

            	logLeave("statement");

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            statement_stack.pop();
        }
        return stm;
    }
    // $ANTLR end "statement"

    protected static class atstatement_scope {
        RuleBlock<?> stm;
    }
    protected Stack atstatement_stack = new Stack();


    // $ANTLR start "atstatement"
    // CSSTreeParser.g:158:1: atstatement returns [RuleBlock<?> stmnt] : ( CHARSET | INVALID_IMPORT | i= IMPORT | IMPORT_END | ^( PAGE (i= IDENT )? (d= declaration )* ) | ^( MEDIA (mediaList= media )? (rs= ruleset )* ) | INVALID_STATEMENT );
    public final RuleBlock<?> atstatement() throws RecognitionException {
        atstatement_stack.push(new atstatement_scope());
        RuleBlock<?> stmnt = null;

        CommonTree i=null;
        Declaration d = null;

        List<String> mediaList = null;

        RuleBlock<?> rs = null;



            logEnter("atstatement");
        	((statement_scope)statement_stack.peek()).insideAtstatement =true;
        	((atstatement_scope)atstatement_stack.peek()).stm = stmnt = null;
        	List<Declaration> declarations = null;
        	List<RuleSet> rules = null;
        	String pseudo = null;
        	int filePosition = ruleNum;

        try {
            // CSSTreeParser.g:174:2: ( CHARSET | INVALID_IMPORT | i= IMPORT | IMPORT_END | ^( PAGE (i= IDENT )? (d= declaration )* ) | ^( MEDIA (mediaList= media )? (rs= ruleset )* ) | INVALID_STATEMENT )
            int alt7=7;
            switch ( input.LA(1) ) {
            case CHARSET:
                {
                alt7=1;
                }
                break;
            case INVALID_IMPORT:
                {
                alt7=2;
                }
                break;
            case IMPORT:
                {
                alt7=3;
                }
                break;
            case IMPORT_END:
                {
                alt7=4;
                }
                break;
            case PAGE:
                {
                alt7=5;
                }
                break;
            case MEDIA:
                {
                alt7=6;
                }
                break;
            case INVALID_STATEMENT:
                {
                alt7=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // CSSTreeParser.g:174:4: CHARSET
                    {
                    match(input,CHARSET,FOLLOW_CHARSET_in_atstatement161); 

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:175:4: INVALID_IMPORT
                    {
                    match(input,INVALID_IMPORT,FOLLOW_INVALID_IMPORT_in_atstatement167); 

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:176:4: i= IMPORT
                    {
                    i=(CommonTree)match(input,IMPORT,FOLLOW_IMPORT_in_atstatement175); 

                    	    String media = extractText(i);
                    		imports.push(new TreeParserState(media));
                    		
                    		log.info("From imported file: Rules will use these media: {}", 
                    			imports.peek());
                    	  

                    }
                    break;
                case 4 :
                    // CSSTreeParser.g:184:4: IMPORT_END
                    {
                    match(input,IMPORT_END,FOLLOW_IMPORT_END_in_atstatement186); 

                    	    imports.pop();
                    		log.info("Imported file was parsed, returing in nesting.");
                    	  

                    }
                    break;
                case 5 :
                    // CSSTreeParser.g:188:4: ^( PAGE (i= IDENT )? (d= declaration )* )
                    {
                    match(input,PAGE,FOLLOW_PAGE_in_atstatement194); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:188:11: (i= IDENT )?
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==IDENT) ) {
                            alt3=1;
                        }
                        switch (alt3) {
                            case 1 :
                                // CSSTreeParser.g:188:12: i= IDENT
                                {
                                i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_atstatement199); 
                                 pseudo=extractText(i);

                                }
                                break;

                        }

                        // CSSTreeParser.g:188:47: (d= declaration )*
                        loop4:
                        do {
                            int alt4=2;
                            int LA4_0 = input.LA(1);

                            if ( (LA4_0==DECLARATION||LA4_0==INVALID_DECLARATION) ) {
                                alt4=1;
                            }


                            switch (alt4) {
                        	case 1 :
                        	    // CSSTreeParser.g:188:48: d= declaration
                        	    {
                        	    pushFollow(FOLLOW_declaration_in_atstatement207);
                        	    d=declaration();

                        	    state._fsp--;


                        	    				if(declarations==null)
                        	    				    declarations=new ArrayList<Declaration>();
                        	    				if(d!=null && !d.isEmpty()) {
                        	                		declarations.add(d);
                        	                	    log.debug("Inserted declaration ({}) into @page",
                        	    					    declarations.size());
                        	    				}
                        	    			

                        	    }
                        	    break;

                        	default :
                        	    break loop4;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }

                    		   	if(declarations!=null && !declarations.isEmpty()) {	
                                	RulePage rp = rf.createPage(ruleNum++);
                                    rp.replaceAll(declarations);
                                    rp.setPseudo(pseudo);
                                    stmnt = rp;
                                    log.info("Create @page as {}th with:\n{}", 
                                    	ruleNum, rp);
                                }
                            

                    }
                    break;
                case 6 :
                    // CSSTreeParser.g:209:4: ^( MEDIA (mediaList= media )? (rs= ruleset )* )
                    {
                    match(input,MEDIA,FOLLOW_MEDIA_in_atstatement233); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:209:12: (mediaList= media )?
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==IDENT) ) {
                            alt5=1;
                        }
                        switch (alt5) {
                            case 1 :
                                // CSSTreeParser.g:209:13: mediaList= media
                                {
                                pushFollow(FOLLOW_media_in_atstatement238);
                                mediaList=media();

                                state._fsp--;


                                }
                                break;

                        }

                        // CSSTreeParser.g:210:4: (rs= ruleset )*
                        loop6:
                        do {
                            int alt6=2;
                            int LA6_0 = input.LA(1);

                            if ( (LA6_0==RULE) ) {
                                alt6=1;
                            }


                            switch (alt6) {
                        	case 1 :
                        	    // CSSTreeParser.g:210:5: rs= ruleset
                        	    {
                        	    pushFollow(FOLLOW_ruleset_in_atstatement249);
                        	    rs=ruleset();

                        	    state._fsp--;


                        	    			   if(rules==null) rules = new ArrayList<RuleSet>();				
                        	    			   if(rs!=null) {
                        	    				   // increment rule number
                        	    				   rs.setFilePosition(rs.getFilePosition()+1);
                        	    				   
                        	    				   // this cast should be safe, because when 
                        	    				   // inside of @statetement, oridinal ruleset
                        	    				   // is returned
                        	    			       rules.add((RuleSet)rs);
                        	    				   log.debug("Inserted ruleset ({}) into @media",
                        	    				   		rules.size());
                        	    			   }
                        	    		
                        	    			

                        	    }
                        	    break;

                        	default :
                        	    break loop6;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }

                    		   if(rules!=null && !rules.isEmpty()) {
                    			  // create at begining, increment to match positions								   
                                  RuleMedia rm = rf.createMedia(filePosition);
                    			  ruleNum++;
                    			  
                    			  rm.replaceAll(rules);
                    			  if(mediaList!=null && !mediaList.isEmpty()) 
                    			  	  rm.setMedia(mediaList);
                    				
                    			  stmnt = rm;
                                  log.info("Create @media as {}th with:\n{}", 
                                    	rm.getFilePosition(), rm);
                    			  
                    		   }
                    	   

                    }
                    break;
                case 7 :
                    // CSSTreeParser.g:242:4: INVALID_STATEMENT
                    {
                    match(input,INVALID_STATEMENT,FOLLOW_INVALID_STATEMENT_in_atstatement271); 
                    ((statement_scope)statement_stack.peek()).invalid =true;

                    }
                    break;

            }

                logLeave("atstatement");

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            atstatement_stack.pop();
        }
        return stmnt;
    }
    // $ANTLR end "atstatement"


    // $ANTLR start "media"
    // CSSTreeParser.g:245:1: media returns [List<String> affected] : (i= IDENT )+ ;
    public final List<String> media() throws RecognitionException {
        List<String> affected = null;

        CommonTree i=null;


           logEnter("media");
           affected = new ArrayList<String>();

        try {
            // CSSTreeParser.g:254:2: ( (i= IDENT )+ )
            // CSSTreeParser.g:254:4: (i= IDENT )+
            {
            // CSSTreeParser.g:254:4: (i= IDENT )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==IDENT) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // CSSTreeParser.g:254:5: i= IDENT
            	    {
            	    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_media303); 

            	    				   String m = extractText(i);
            	    				   if(css.isSupportedMedia(m)) affected.add(m);
            	        

            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            }


               log.debug("Totally returned {} media.", affected.size());							  
               logLeave("media");		   

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return affected;
    }
    // $ANTLR end "media"


    // $ANTLR start "ruleset"
    // CSSTreeParser.g:260:1: ruleset returns [RuleBlock<?> stmnt] : ^( RULE (cs= combined_selector )* (d= declaration )* ) ;
    public final RuleBlock<?> ruleset() throws RecognitionException {
        RuleBlock<?> stmnt = null;

        CombinedSelector cs = null;

        Declaration d = null;



            logEnter("ruleset"); 
            List<CombinedSelector> cslist = new ArrayList<CombinedSelector>();
            List<Declaration> declarations = new ArrayList<Declaration>();

        try {
            // CSSTreeParser.g:307:5: ( ^( RULE (cs= combined_selector )* (d= declaration )* ) )
            // CSSTreeParser.g:307:7: ^( RULE (cs= combined_selector )* (d= declaration )* )
            {
            match(input,RULE,FOLLOW_RULE_in_ruleset349); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // CSSTreeParser.g:308:9: (cs= combined_selector )*
                loop9:
                do {
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==SELECTOR||LA9_0==INVALID_SELECTOR) ) {
                        alt9=1;
                    }


                    switch (alt9) {
                	case 1 :
                	    // CSSTreeParser.g:308:10: cs= combined_selector
                	    {
                	    pushFollow(FOLLOW_combined_selector_in_ruleset363);
                	    cs=combined_selector();

                	    state._fsp--;

                	    if(cs!=null && !cs.isEmpty() && !((statement_scope)statement_stack.peek()).invalid) {
                	                cslist.add(cs);
                	                log.debug("Inserted combined selector ({}) into ruleset", 
                	    				cslist.size());
                	             }   
                	            

                	    }
                	    break;

                	default :
                	    break loop9;
                    }
                } while (true);

                // CSSTreeParser.g:315:9: (d= declaration )*
                loop10:
                do {
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==DECLARATION||LA10_0==INVALID_DECLARATION) ) {
                        alt10=1;
                    }


                    switch (alt10) {
                	case 1 :
                	    // CSSTreeParser.g:315:10: d= declaration
                	    {
                	    pushFollow(FOLLOW_declaration_in_ruleset391);
                	    d=declaration();

                	    state._fsp--;

                	    if(d!=null && !d.isEmpty()) {
                	                declarations.add(d);
                	                log.debug("Inserted declaration ({}) into ruleset",
                	    				declarations.size());
                	             }
                	            

                	    }
                	    break;

                	default :
                	    break loop10;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }


                if(((statement_scope)statement_stack.peek()).invalid || cslist.isEmpty() || declarations.isEmpty()) {
                    stmnt = null;
                    log.debug("Ruleset not valid, so not created");
                }
                else {    
                    RuleSet rs = rf.createSet(ruleNum++);
                    rs.setSelectors(cslist);
                    rs.replaceAll(declarations);
            		log.info("Create ruleset as {}th with:\n{}", ruleNum, rs);
            		
            		// check statement
            		if(!((statement_scope)statement_stack.peek()).insideAtstatement && !imports.isEmpty() 
            			&& imports.peek().doWrap()) {
            			
            			// swap numbers, so RuleMedia is created before RuleSet
            			int filePosition = rs.getFilePosition();			
            			rs.setFilePosition(ruleNum++);
            			RuleMedia rm = rf.createMedia(filePosition);
            			List<String> media = imports.peek().media;
            			
            			log.debug("Wrapping ruleset {} into media: {}", rs, media);
            			
            			rm.unlock();
            			rm.add(rs);
            			rm.setMedia(media);
            			
            			stmnt = (RuleBlock<?>) rm;
            			
            		}
            		// create oridinal ruleset
            		else {
            			stmnt = (RuleBlock<?>) rs;
            		}
                }
                logLeave("ruleset");

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return stmnt;
    }
    // $ANTLR end "ruleset"

    protected static class declaration_scope {
        Declaration d;
        boolean invalid;
    }
    protected Stack declaration_stack = new Stack();


    // $ANTLR start "declaration"
    // CSSTreeParser.g:325:1: declaration returns [Declaration decl] : ( ^( DECLARATION ( important )? property t= terms ) | INVALID_DECLARATION );
    public final Declaration declaration() throws RecognitionException {
        declaration_stack.push(new declaration_scope());
        Declaration decl = null;

        List<Term<?>> t = null;



            logEnter("declaration");
            ((declaration_scope)declaration_stack.peek()).d = decl = rf.createDeclaration();
            ((declaration_scope)declaration_stack.peek()).invalid = false;

        try {
            // CSSTreeParser.g:348:3: ( ^( DECLARATION ( important )? property t= terms ) | INVALID_DECLARATION )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==DECLARATION) ) {
                alt12=1;
            }
            else if ( (LA12_0==INVALID_DECLARATION) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // CSSTreeParser.g:348:5: ^( DECLARATION ( important )? property t= terms )
                    {
                    match(input,DECLARATION,FOLLOW_DECLARATION_in_declaration458); 

                    match(input, Token.DOWN, null); 
                    // CSSTreeParser.g:349:4: ( important )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==IMPORTANT) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // CSSTreeParser.g:349:5: important
                            {
                            pushFollow(FOLLOW_important_in_declaration465);
                            important();

                            state._fsp--;

                             decl.setImportant(true);

                            }
                            break;

                    }

                    pushFollow(FOLLOW_property_in_declaration477);
                    property();

                    state._fsp--;

                    pushFollow(FOLLOW_terms_in_declaration488);
                    t=terms();

                    state._fsp--;

                    decl.replaceAll(t);

                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:353:5: INVALID_DECLARATION
                    {
                    match(input,INVALID_DECLARATION,FOLLOW_INVALID_DECLARATION_in_declaration509); 
                     ((declaration_scope)declaration_stack.peek()).invalid =true;

                    }
                    break;

            }

                if(((declaration_scope)declaration_stack.peek()).invalid) {
                    decl =null;
                    log.debug("Declaration was invalidated or already invalid");
                }
                else {
                    log.debug("Returning declaration: {}.", decl);
                }
                logLeave("declaration");    

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            declaration_stack.pop();
        }
        return decl;
    }
    // $ANTLR end "declaration"


    // $ANTLR start "important"
    // CSSTreeParser.g:356:1: important : IMPORTANT ;
    public final void important() throws RecognitionException {
        try {
            // CSSTreeParser.g:357:5: ( IMPORTANT )
            // CSSTreeParser.g:357:7: IMPORTANT
            {
            match(input,IMPORTANT,FOLLOW_IMPORTANT_in_important526); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "important"


    // $ANTLR start "property"
    // CSSTreeParser.g:360:1: property : i= IDENT ;
    public final void property() throws RecognitionException {
        CommonTree i=null;


            logEnter("property");

        try {
            // CSSTreeParser.g:371:3: (i= IDENT )
            // CSSTreeParser.g:371:5: i= IDENT
            {
            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_property566); 
             ((declaration_scope)declaration_stack.peek()).d.setProperty(extractText(i)); 

            }


            	log.debug("Setting property: {}", ((declaration_scope)declaration_stack.peek()).d.getProperty());	   
                logLeave("property");

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "property"

    protected static class terms_scope {
        List<Term<?>> list;
        Term<?> term;
        Term.Operator op;
        int unary;
    }
    protected Stack terms_stack = new Stack();


    // $ANTLR start "terms"
    // CSSTreeParser.g:374:1: terms returns [List<Term<?>> tlist] : ^( VALUE ( term )+ ) ;
    public final List<Term<?>> terms() throws RecognitionException {
        terms_stack.push(new terms_scope());
        List<Term<?>> tlist = null;


            logEnter("terms");
            ((terms_scope)terms_stack.peek()).list = tlist = new ArrayList<Term<?>>();
            ((terms_scope)terms_stack.peek()).term = null;
            ((terms_scope)terms_stack.peek()).op = null;
            ((terms_scope)terms_stack.peek()).unary = 1;

        try {
            // CSSTreeParser.g:395:5: ( ^( VALUE ( term )+ ) )
            // CSSTreeParser.g:395:7: ^( VALUE ( term )+ )
            {
            match(input,VALUE,FOLLOW_VALUE_in_terms611); 

            match(input, Token.DOWN, null); 
            // CSSTreeParser.g:395:15: ( term )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>=CURLYBLOCK && LA13_0<=BRACEBLOCK)||LA13_0==INVALID_STRING||(LA13_0>=COLON && LA13_0<=IDENT)||(LA13_0>=ATKEYWORD && LA13_0<=COMMA)||(LA13_0>=CLASSKEYWORD && LA13_0<=FUNCTION)||LA13_0==DASHMATCH||LA13_0==STRING) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // CSSTreeParser.g:395:15: term
            	    {
            	    pushFollow(FOLLOW_term_in_terms613);
            	    term();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);


            match(input, Token.UP, null); 

            }


            	log.debug("Totally added {} terms", tlist.size());	   
                logLeave("terms");

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            terms_stack.pop();
        }
        return tlist;
    }
    // $ANTLR end "terms"


    // $ANTLR start "term"
    // CSSTreeParser.g:398:1: term : ( valuepart | CURLYBLOCK | ATKEYWORD );
    public final void term() throws RecognitionException {
        try {
            // CSSTreeParser.g:399:5: ( valuepart | CURLYBLOCK | ATKEYWORD )
            int alt14=3;
            switch ( input.LA(1) ) {
            case PARENBLOCK:
            case BRACEBLOCK:
            case INVALID_STRING:
            case COLON:
            case IDENT:
            case COMMA:
            case CLASSKEYWORD:
            case MINUS:
            case NUMBER:
            case PERCENTAGE:
            case DIMENSION:
            case URI:
            case HASH:
            case UNIRANGE:
            case INCLUDES:
            case GREATER:
            case EQUALS:
            case SLASH:
            case PLUS:
            case ASTERISK:
            case FUNCTION:
            case DASHMATCH:
            case STRING:
                {
                alt14=1;
                }
                break;
            case CURLYBLOCK:
                {
                alt14=2;
                }
                break;
            case ATKEYWORD:
                {
                alt14=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // CSSTreeParser.g:399:7: valuepart
                    {
                    pushFollow(FOLLOW_valuepart_in_term636);
                    valuepart();

                    state._fsp--;

                    // set operator, store and create next 
                           if(!((declaration_scope)declaration_stack.peek()).invalid && ((terms_scope)terms_stack.peek()).term!=null) {     
                              ((terms_scope)terms_stack.peek()).term.setOperator(((terms_scope)terms_stack.peek()).op);
                              ((terms_scope)terms_stack.peek()).list.add(((terms_scope)terms_stack.peek()).term);
                              // reinitialization
                              ((terms_scope)terms_stack.peek()).op = Term.Operator.SPACE;
                              ((terms_scope)terms_stack.peek()).unary = 1;
                              ((terms_scope)terms_stack.peek()).term = null;
                           }    
                          

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:410:7: CURLYBLOCK
                    {
                    match(input,CURLYBLOCK,FOLLOW_CURLYBLOCK_in_term653); 
                     ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:411:7: ATKEYWORD
                    {
                    match(input,ATKEYWORD,FOLLOW_ATKEYWORD_in_term663); 
                     ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "term"


    // $ANTLR start "valuepart"
    // CSSTreeParser.g:414:1: valuepart : (i= IDENT | CLASSKEYWORD | ( MINUS )? n= NUMBER | ( MINUS )? p= PERCENTAGE | ( MINUS )? d= DIMENSION | s= string | u= URI | h= HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | PLUS | ASTERISK | ^(f= FUNCTION t= terms ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) );
    public final void valuepart() throws RecognitionException {
        CommonTree i=null;
        CommonTree n=null;
        CommonTree p=null;
        CommonTree d=null;
        CommonTree u=null;
        CommonTree h=null;
        CommonTree f=null;
        String s = null;

        List<Term<?>> t = null;


        try {
            // CSSTreeParser.g:436:5: (i= IDENT | CLASSKEYWORD | ( MINUS )? n= NUMBER | ( MINUS )? p= PERCENTAGE | ( MINUS )? d= DIMENSION | s= string | u= URI | h= HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | PLUS | ASTERISK | ^(f= FUNCTION t= terms ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) )
            int alt20=21;
            alt20 = dfa20.predict(input);
            switch (alt20) {
                case 1 :
                    // CSSTreeParser.g:436:7: i= IDENT
                    {
                    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_valuepart691); 
                    ((terms_scope)terms_stack.peek()).term = tf.createIdent(extractText(i));

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:437:7: CLASSKEYWORD
                    {
                    match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_valuepart703); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:438:4: ( MINUS )? n= NUMBER
                    {
                    // CSSTreeParser.g:438:4: ( MINUS )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==MINUS) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // CSSTreeParser.g:438:5: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart711); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    n=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_valuepart719); 
                    ((terms_scope)terms_stack.peek()).term = tf.createNumeric(extractText(n), ((terms_scope)terms_stack.peek()).unary);

                    }
                    break;
                case 4 :
                    // CSSTreeParser.g:439:7: ( MINUS )? p= PERCENTAGE
                    {
                    // CSSTreeParser.g:439:7: ( MINUS )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==MINUS) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // CSSTreeParser.g:439:8: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart733); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    p=(CommonTree)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_valuepart741); 
                     ((terms_scope)terms_stack.peek()).term = tf.createPercent(extractText(p), ((terms_scope)terms_stack.peek()).unary);

                    }
                    break;
                case 5 :
                    // CSSTreeParser.g:440:7: ( MINUS )? d= DIMENSION
                    {
                    // CSSTreeParser.g:440:7: ( MINUS )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==MINUS) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // CSSTreeParser.g:440:8: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart753); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    d=(CommonTree)match(input,DIMENSION,FOLLOW_DIMENSION_in_valuepart761); 
                    String dim = extractText(d);
                    	 ((terms_scope)terms_stack.peek()).term = tf.createDimension(dim, ((terms_scope)terms_stack.peek()).unary);
                         if(((terms_scope)terms_stack.peek()).term==null) {
                    		 log.info("Unable to create dimension from {}, unary {}", dim, ((terms_scope)terms_stack.peek()).unary);
                             ((declaration_scope)declaration_stack.peek()).invalid = true;
                    	 }
                        

                    }
                    break;
                case 6 :
                    // CSSTreeParser.g:448:7: s= string
                    {
                    pushFollow(FOLLOW_string_in_valuepart777);
                    s=string();

                    state._fsp--;

                     if(s!=null) ((terms_scope)terms_stack.peek()).term = tf.createString(s);
                    	  else ((declaration_scope)declaration_stack.peek()).invalid =true;
                    	

                    }
                    break;
                case 7 :
                    // CSSTreeParser.g:452:7: u= URI
                    {
                    u=(CommonTree)match(input,URI,FOLLOW_URI_in_valuepart794); 
                    ((terms_scope)terms_stack.peek()).term = tf.createURI(extractText(u));

                    }
                    break;
                case 8 :
                    // CSSTreeParser.g:453:7: h= HASH
                    {
                    h=(CommonTree)match(input,HASH,FOLLOW_HASH_in_valuepart812); 
                    ((terms_scope)terms_stack.peek()).term = tf.createColor(extractText(h));
                         if(((terms_scope)terms_stack.peek()).term==null)
                             ((declaration_scope)declaration_stack.peek()).invalid = true;
                        

                    }
                    break;
                case 9 :
                    // CSSTreeParser.g:458:7: UNIRANGE
                    {
                    match(input,UNIRANGE,FOLLOW_UNIRANGE_in_valuepart830); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 10 :
                    // CSSTreeParser.g:459:7: INCLUDES
                    {
                    match(input,INCLUDES,FOLLOW_INCLUDES_in_valuepart841); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 11 :
                    // CSSTreeParser.g:460:7: COLON
                    {
                    match(input,COLON,FOLLOW_COLON_in_valuepart852); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 12 :
                    // CSSTreeParser.g:461:7: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_valuepart866); 
                    ((terms_scope)terms_stack.peek()).op = Term.Operator.COMMA;

                    }
                    break;
                case 13 :
                    // CSSTreeParser.g:462:7: GREATER
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_valuepart884); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 14 :
                    // CSSTreeParser.g:463:7: EQUALS
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_valuepart896); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 15 :
                    // CSSTreeParser.g:464:7: SLASH
                    {
                    match(input,SLASH,FOLLOW_SLASH_in_valuepart909); 
                    ((terms_scope)terms_stack.peek()).op = Term.Operator.SLASH;

                    }
                    break;
                case 16 :
                    // CSSTreeParser.g:465:4: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_valuepart920); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 17 :
                    // CSSTreeParser.g:466:4: ASTERISK
                    {
                    match(input,ASTERISK,FOLLOW_ASTERISK_in_valuepart928); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 18 :
                    // CSSTreeParser.g:467:7: ^(f= FUNCTION t= terms )
                    {
                    f=(CommonTree)match(input,FUNCTION,FOLLOW_FUNCTION_in_valuepart942); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_terms_in_valuepart946);
                    t=terms();

                    state._fsp--;


                    match(input, Token.UP, null); 

                            // create function
                            TermFunction function = tf.createFunction();
                            function.setFunctionName(extractText(f));
                            function.setValue(t);
                            ((terms_scope)terms_stack.peek()).term = function;
                        

                    }
                    break;
                case 19 :
                    // CSSTreeParser.g:474:7: DASHMATCH
                    {
                    match(input,DASHMATCH,FOLLOW_DASHMATCH_in_valuepart957); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 20 :
                    // CSSTreeParser.g:475:7: ^( PARENBLOCK ( any )* )
                    {
                    match(input,PARENBLOCK,FOLLOW_PARENBLOCK_in_valuepart968); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:475:20: ( any )*
                        loop18:
                        do {
                            int alt18=2;
                            int LA18_0 = input.LA(1);

                            if ( ((LA18_0>=PARENBLOCK && LA18_0<=BRACEBLOCK)||LA18_0==INVALID_STRING||(LA18_0>=COLON && LA18_0<=IDENT)||(LA18_0>=COMMA && LA18_0<=CLASSKEYWORD)||(LA18_0>=NUMBER && LA18_0<=SLASH)||LA18_0==FUNCTION||LA18_0==DASHMATCH||LA18_0==STRING) ) {
                                alt18=1;
                            }


                            switch (alt18) {
                        	case 1 :
                        	    // CSSTreeParser.g:475:20: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_valuepart970);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop18;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 21 :
                    // CSSTreeParser.g:476:7: ^( BRACEBLOCK ( any )* )
                    {
                    match(input,BRACEBLOCK,FOLLOW_BRACEBLOCK_in_valuepart983); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:476:20: ( any )*
                        loop19:
                        do {
                            int alt19=2;
                            int LA19_0 = input.LA(1);

                            if ( ((LA19_0>=PARENBLOCK && LA19_0<=BRACEBLOCK)||LA19_0==INVALID_STRING||(LA19_0>=COLON && LA19_0<=IDENT)||(LA19_0>=COMMA && LA19_0<=CLASSKEYWORD)||(LA19_0>=NUMBER && LA19_0<=SLASH)||LA19_0==FUNCTION||LA19_0==DASHMATCH||LA19_0==STRING) ) {
                                alt19=1;
                            }


                            switch (alt19) {
                        	case 1 :
                        	    // CSSTreeParser.g:476:20: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_valuepart985);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop19;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;

            }

                // convert color
                Term<?> term = ((terms_scope)terms_stack.peek()).term;
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
                        ((terms_scope)terms_stack.peek()).term = colorTerm;
                    }                    
                }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "valuepart"

    protected static class combined_selector_scope {
        boolean invalid;
    }
    protected Stack combined_selector_stack = new Stack();


    // $ANTLR start "combined_selector"
    // CSSTreeParser.g:479:1: combined_selector returns [CombinedSelector combinedSelector] : s= selector (c= combinator s= selector )* ;
    public final CombinedSelector combined_selector() throws RecognitionException {
        combined_selector_stack.push(new combined_selector_scope());
        CombinedSelector combinedSelector = null;

        Selector s = null;

        Selector.Combinator c = null;



        	logEnter("combined_selector");	  
        	combinedSelector = (CombinedSelector) rf.createCombinedSelector().unlock();

        try {
            // CSSTreeParser.g:509:2: (s= selector (c= combinator s= selector )* )
            // CSSTreeParser.g:509:4: s= selector (c= combinator s= selector )*
            {
            pushFollow(FOLLOW_selector_in_combined_selector1033);
            s=selector();

            state._fsp--;


            	     combinedSelector.add(s);
            	  
            // CSSTreeParser.g:512:3: (c= combinator s= selector )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>=ADJACENT && LA21_0<=DESCENDANT)) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // CSSTreeParser.g:512:4: c= combinator s= selector
            	    {
            	    pushFollow(FOLLOW_combinator_in_combined_selector1042);
            	    c=combinator();

            	    state._fsp--;

            	    pushFollow(FOLLOW_selector_in_combined_selector1046);
            	    s=selector();

            	    state._fsp--;


            	    	     s.setCombinator(c);
            	    	     combinedSelector.add(s);	
            	    	  

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            }

              
                // entire ruleset is not valid when selector is not valid
                // there is no need to parse selector's when already marked as invalid
                if(((statement_scope)statement_stack.peek()).invalid || ((combined_selector_scope)combined_selector_stack.peek()).invalid) {        
                    combinedSelector = null;
                    if(((statement_scope)statement_stack.peek()).invalid) { 
            			log.debug("Ommiting combined selector, whole statement discarded");
            		}	
                    else { 
            			log.debug("Combined selector is invalid");               
                    }
            		// mark whole ruleset as invalid
                    ((statement_scope)statement_stack.peek()).invalid = true;
                }
                else {
                    log.debug("Returing combined selector: {}.", combinedSelector); 
                }
                logLeave("combined_selector"); 

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            combined_selector_stack.pop();
        }
        return combinedSelector;
    }
    // $ANTLR end "combined_selector"


    // $ANTLR start "combinator"
    // CSSTreeParser.g:519:1: combinator returns [Selector.Combinator combinator] : ( CHILD | ADJACENT | DESCENDANT );
    public final Selector.Combinator combinator() throws RecognitionException {
        Selector.Combinator combinator = null;

         logEnter("combinator"); 
        try {
            // CSSTreeParser.g:522:2: ( CHILD | ADJACENT | DESCENDANT )
            int alt22=3;
            switch ( input.LA(1) ) {
            case CHILD:
                {
                alt22=1;
                }
                break;
            case ADJACENT:
                {
                alt22=2;
                }
                break;
            case DESCENDANT:
                {
                alt22=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }

            switch (alt22) {
                case 1 :
                    // CSSTreeParser.g:522:4: CHILD
                    {
                    match(input,CHILD,FOLLOW_CHILD_in_combinator1076); 
                    combinator =Selector.Combinator.CHILD;

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:523:4: ADJACENT
                    {
                    match(input,ADJACENT,FOLLOW_ADJACENT_in_combinator1083); 
                    combinator =Selector.Combinator.ADJACENT;

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:524:4: DESCENDANT
                    {
                    match(input,DESCENDANT,FOLLOW_DESCENDANT_in_combinator1090); 
                    combinator =Selector.Combinator.DESCENDANT;

                    }
                    break;

            }
             logLeave("combinator"); 
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return combinator;
    }
    // $ANTLR end "combinator"

    protected static class selector_scope {
        Selector s;
    }
    protected Stack selector_stack = new Stack();


    // $ANTLR start "selector"
    // CSSTreeParser.g:528:1: selector returns [Selector sel] : ( ^( SELECTOR ^( ELEMENT (i= IDENT )? ) ( selpart )* ) | ^( SELECTOR ( selpart )+ ) | INVALID_SELECTOR );
    public final Selector selector() throws RecognitionException {
        selector_stack.push(new selector_scope());
        Selector sel = null;

        CommonTree i=null;


        	logEnter("selector");
        	((selector_scope)selector_stack.peek()).s =sel=(Selector)rf.createSelector().unlock();
        	Selector.ElementName en = rf.createElement(Selector.SelectorPart.WILDCARD);

        try {
            // CSSTreeParser.g:540:5: ( ^( SELECTOR ^( ELEMENT (i= IDENT )? ) ( selpart )* ) | ^( SELECTOR ( selpart )+ ) | INVALID_SELECTOR )
            int alt26=3;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==SELECTOR) ) {
                int LA26_1 = input.LA(2);

                if ( (LA26_1==DOWN) ) {
                    int LA26_3 = input.LA(3);

                    if ( (LA26_3==ELEMENT) ) {
                        alt26=1;
                    }
                    else if ( (LA26_3==PSEUDO||LA26_3==ATTRIBUTE||LA26_3==INVALID_SELPART||LA26_3==CLASSKEYWORD||LA26_3==HASH||LA26_3==FUNCTION) ) {
                        alt26=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 26, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 26, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA26_0==INVALID_SELECTOR) ) {
                alt26=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // CSSTreeParser.g:540:7: ^( SELECTOR ^( ELEMENT (i= IDENT )? ) ( selpart )* )
                    {
                    match(input,SELECTOR,FOLLOW_SELECTOR_in_selector1126); 

                    match(input, Token.DOWN, null); 
                    match(input,ELEMENT,FOLLOW_ELEMENT_in_selector1138); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:542:11: (i= IDENT )?
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( (LA23_0==IDENT) ) {
                            alt23=1;
                        }
                        switch (alt23) {
                            case 1 :
                                // CSSTreeParser.g:542:12: i= IDENT
                                {
                                i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_selector1154); 
                                 en.setValue(extractText(i)); 

                                }
                                break;

                        }


                        match(input, Token.UP, null); 
                    }

                    		  log.debug("Adding element name: {}.", en.getValue());
                    		  ((selector_scope)selector_stack.peek()).s.add(en);
                    		 
                    // CSSTreeParser.g:548:10: ( selpart )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==PSEUDO||LA24_0==ATTRIBUTE||LA24_0==INVALID_SELPART||LA24_0==CLASSKEYWORD||LA24_0==HASH||LA24_0==FUNCTION) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // CSSTreeParser.g:548:10: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1201);
                    	    selpart();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop24;
                        }
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:550:7: ^( SELECTOR ( selpart )+ )
                    {
                    match(input,SELECTOR,FOLLOW_SELECTOR_in_selector1220); 

                    match(input, Token.DOWN, null); 
                    // CSSTreeParser.g:551:10: ( selpart )+
                    int cnt25=0;
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==PSEUDO||LA25_0==ATTRIBUTE||LA25_0==INVALID_SELPART||LA25_0==CLASSKEYWORD||LA25_0==HASH||LA25_0==FUNCTION) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // CSSTreeParser.g:551:10: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1232);
                    	    selpart();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt25 >= 1 ) break loop25;
                                EarlyExitException eee =
                                    new EarlyExitException(25, input);
                                throw eee;
                        }
                        cnt25++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:553:7: INVALID_SELECTOR
                    {
                    match(input,INVALID_SELECTOR,FOLLOW_INVALID_SELECTOR_in_selector1250); 
                     ((statement_scope)statement_stack.peek()).invalid = true; 

                    }
                    break;

            }

            	logLeave("selector");

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            selector_stack.pop();
        }
        return sel;
    }
    // $ANTLR end "selector"


    // $ANTLR start "selpart"
    // CSSTreeParser.g:556:1: selpart : ( PSEUDO i= IDENT | h= HASH | c= CLASSKEYWORD | ^( ATTRIBUTE ea= attribute ) | ^(fname= FUNCTION farg= IDENT ) | INVALID_SELPART );
    public final void selpart() throws RecognitionException {
        CommonTree i=null;
        CommonTree h=null;
        CommonTree c=null;
        CommonTree fname=null;
        CommonTree farg=null;
        Selector.ElementAttribute ea = null;



        	logEnter("selpart");

        try {
            // CSSTreeParser.g:563:5: ( PSEUDO i= IDENT | h= HASH | c= CLASSKEYWORD | ^( ATTRIBUTE ea= attribute ) | ^(fname= FUNCTION farg= IDENT ) | INVALID_SELPART )
            int alt27=6;
            switch ( input.LA(1) ) {
            case PSEUDO:
                {
                alt27=1;
                }
                break;
            case HASH:
                {
                alt27=2;
                }
                break;
            case CLASSKEYWORD:
                {
                alt27=3;
                }
                break;
            case ATTRIBUTE:
                {
                alt27=4;
                }
                break;
            case FUNCTION:
                {
                alt27=5;
                }
                break;
            case INVALID_SELPART:
                {
                alt27=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }

            switch (alt27) {
                case 1 :
                    // CSSTreeParser.g:563:7: PSEUDO i= IDENT
                    {
                    match(input,PSEUDO,FOLLOW_PSEUDO_in_selpart1282); 
                    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_selpart1286); 
                     ((selector_scope)selector_stack.peek()).s.add(rf.createPseudoPage(extractText(i), null));

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:564:7: h= HASH
                    {
                    h=(CommonTree)match(input,HASH,FOLLOW_HASH_in_selpart1298); 
                     ((selector_scope)selector_stack.peek()).s.add(rf.createID(extractText(h))); 

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:565:7: c= CLASSKEYWORD
                    {
                    c=(CommonTree)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_selpart1310); 
                     ((selector_scope)selector_stack.peek()).s.add(rf.createClass(extractText(c))); 

                    }
                    break;
                case 4 :
                    // CSSTreeParser.g:566:4: ^( ATTRIBUTE ea= attribute )
                    {
                    match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_selpart1318); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_attribute_in_selpart1322);
                    ea=attribute();

                    state._fsp--;

                     ((selector_scope)selector_stack.peek()).s.add(ea);

                    match(input, Token.UP, null); 

                    }
                    break;
                case 5 :
                    // CSSTreeParser.g:567:7: ^(fname= FUNCTION farg= IDENT )
                    {
                    fname=(CommonTree)match(input,FUNCTION,FOLLOW_FUNCTION_in_selpart1337); 

                    match(input, Token.DOWN, null); 
                    farg=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_selpart1341); 

                           ((selector_scope)selector_stack.peek()).s.add(rf.createPseudoPage(extractText(farg), extractText(fname)));
                           

                    match(input, Token.UP, null); 

                    }
                    break;
                case 6 :
                    // CSSTreeParser.g:571:4: INVALID_SELPART
                    {
                    match(input,INVALID_SELPART,FOLLOW_INVALID_SELPART_in_selpart1356); 
                     ((combined_selector_scope)combined_selector_stack.peek()).invalid = true;

                    }
                    break;

            }

                logLeave("selpart");

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "selpart"


    // $ANTLR start "attribute"
    // CSSTreeParser.g:574:1: attribute returns [Selector.ElementAttribute elemAttr] : i= IDENT ( ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string ) )? ;
    public final Selector.ElementAttribute attribute() throws RecognitionException {
        Selector.ElementAttribute elemAttr = null;

        CommonTree i=null;
        String s = null;



            logEnter("attribute");
            String attribute = null;
        	String value = null;
        	Selector.Operator op = Selector.Operator.NO_OPERATOR;
        	boolean isStringValue = false;

        try {
            // CSSTreeParser.g:592:2: (i= IDENT ( ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string ) )? )
            // CSSTreeParser.g:592:4: i= IDENT ( ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string ) )?
            {
            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_attribute1390); 
            attribute=extractText(i); 
            // CSSTreeParser.g:593:4: ( ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==INCLUDES||LA30_0==EQUALS||LA30_0==DASHMATCH) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // CSSTreeParser.g:593:5: ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string )
                    {
                    // CSSTreeParser.g:593:5: ( EQUALS | INCLUDES | DASHMATCH )
                    int alt28=3;
                    switch ( input.LA(1) ) {
                    case EQUALS:
                        {
                        alt28=1;
                        }
                        break;
                    case INCLUDES:
                        {
                        alt28=2;
                        }
                        break;
                    case DASHMATCH:
                        {
                        alt28=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 28, 0, input);

                        throw nvae;
                    }

                    switch (alt28) {
                        case 1 :
                            // CSSTreeParser.g:593:6: EQUALS
                            {
                            match(input,EQUALS,FOLLOW_EQUALS_in_attribute1399); 
                            op=Selector.Operator.EQUALS; 

                            }
                            break;
                        case 2 :
                            // CSSTreeParser.g:594:7: INCLUDES
                            {
                            match(input,INCLUDES,FOLLOW_INCLUDES_in_attribute1410); 
                            op=Selector.Operator.INCLUDES; 

                            }
                            break;
                        case 3 :
                            // CSSTreeParser.g:595:7: DASHMATCH
                            {
                            match(input,DASHMATCH,FOLLOW_DASHMATCH_in_attribute1421); 
                            op=Selector.Operator.DASHMATCH; 

                            }
                            break;

                    }

                    // CSSTreeParser.g:597:5: (i= IDENT | s= string )
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0==IDENT) ) {
                        alt29=1;
                    }
                    else if ( (LA29_0==INVALID_STRING||LA29_0==STRING) ) {
                        alt29=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 29, 0, input);

                        throw nvae;
                    }
                    switch (alt29) {
                        case 1 :
                            // CSSTreeParser.g:597:6: i= IDENT
                            {
                            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_attribute1439); 

                            		value=extractText(i);
                            		isStringValue=false;
                            		

                            }
                            break;
                        case 2 :
                            // CSSTreeParser.g:601:7: s= string
                            {
                            pushFollow(FOLLOW_string_in_attribute1451);
                            s=string();

                            state._fsp--;


                            		 if(s!=null)  { 
                            			value=s;
                            			isStringValue=true;
                            		 }	
                            		 else {
                            			((combined_selector_scope)combined_selector_stack.peek()).invalid =true;
                            		 }
                            		

                            }
                            break;

                    }


                    }
                    break;

            }


            }


                if(attribute!=null) {
            		elemAttr = rf.createAttribute(value, isStringValue, op, attribute);
            	}
            	else {
            	    log.debug("Invalid attribute element in selector");
            	    ((combined_selector_scope)combined_selector_stack.peek()).invalid = true;
            	}
                logLeave("attribute");

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return elemAttr;
    }
    // $ANTLR end "attribute"


    // $ANTLR start "string"
    // CSSTreeParser.g:613:1: string returns [String s] : (st= STRING | INVALID_STRING );
    public final String string() throws RecognitionException {
        String s = null;

        CommonTree st=null;

        try {
            // CSSTreeParser.g:614:2: (st= STRING | INVALID_STRING )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==STRING) ) {
                alt31=1;
            }
            else if ( (LA31_0==INVALID_STRING) ) {
                alt31=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // CSSTreeParser.g:614:4: st= STRING
                    {
                    st=(CommonTree)match(input,STRING,FOLLOW_STRING_in_string1479); 
                     s = extractText(st);

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:615:4: INVALID_STRING
                    {
                    match(input,INVALID_STRING,FOLLOW_INVALID_STRING_in_string1486); 
                    s =null;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return s;
    }
    // $ANTLR end "string"


    // $ANTLR start "any"
    // CSSTreeParser.g:618:1: any : ( IDENT | CLASSKEYWORD | NUMBER | PERCENTAGE | DIMENSION | string | URI | HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | EXCLAMATION | ^( FUNCTION ( any )* ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) );
    public final void any() throws RecognitionException {
        try {
            // CSSTreeParser.g:619:3: ( IDENT | CLASSKEYWORD | NUMBER | PERCENTAGE | DIMENSION | string | URI | HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | EXCLAMATION | ^( FUNCTION ( any )* ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) )
            int alt35=20;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt35=1;
                }
                break;
            case CLASSKEYWORD:
                {
                alt35=2;
                }
                break;
            case NUMBER:
                {
                alt35=3;
                }
                break;
            case PERCENTAGE:
                {
                alt35=4;
                }
                break;
            case DIMENSION:
                {
                alt35=5;
                }
                break;
            case INVALID_STRING:
            case STRING:
                {
                alt35=6;
                }
                break;
            case URI:
                {
                alt35=7;
                }
                break;
            case HASH:
                {
                alt35=8;
                }
                break;
            case UNIRANGE:
                {
                alt35=9;
                }
                break;
            case INCLUDES:
                {
                alt35=10;
                }
                break;
            case COLON:
                {
                alt35=11;
                }
                break;
            case COMMA:
                {
                alt35=12;
                }
                break;
            case GREATER:
                {
                alt35=13;
                }
                break;
            case EQUALS:
                {
                alt35=14;
                }
                break;
            case SLASH:
                {
                alt35=15;
                }
                break;
            case EXCLAMATION:
                {
                alt35=16;
                }
                break;
            case FUNCTION:
                {
                alt35=17;
                }
                break;
            case DASHMATCH:
                {
                alt35=18;
                }
                break;
            case PARENBLOCK:
                {
                alt35=19;
                }
                break;
            case BRACEBLOCK:
                {
                alt35=20;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // CSSTreeParser.g:619:5: IDENT
                    {
                    match(input,IDENT,FOLLOW_IDENT_in_any1502); 

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:620:5: CLASSKEYWORD
                    {
                    match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_any1508); 

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:621:5: NUMBER
                    {
                    match(input,NUMBER,FOLLOW_NUMBER_in_any1514); 

                    }
                    break;
                case 4 :
                    // CSSTreeParser.g:622:5: PERCENTAGE
                    {
                    match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_any1520); 

                    }
                    break;
                case 5 :
                    // CSSTreeParser.g:623:5: DIMENSION
                    {
                    match(input,DIMENSION,FOLLOW_DIMENSION_in_any1526); 

                    }
                    break;
                case 6 :
                    // CSSTreeParser.g:624:5: string
                    {
                    pushFollow(FOLLOW_string_in_any1532);
                    string();

                    state._fsp--;


                    }
                    break;
                case 7 :
                    // CSSTreeParser.g:625:5: URI
                    {
                    match(input,URI,FOLLOW_URI_in_any1538); 

                    }
                    break;
                case 8 :
                    // CSSTreeParser.g:626:5: HASH
                    {
                    match(input,HASH,FOLLOW_HASH_in_any1544); 

                    }
                    break;
                case 9 :
                    // CSSTreeParser.g:627:5: UNIRANGE
                    {
                    match(input,UNIRANGE,FOLLOW_UNIRANGE_in_any1550); 

                    }
                    break;
                case 10 :
                    // CSSTreeParser.g:628:5: INCLUDES
                    {
                    match(input,INCLUDES,FOLLOW_INCLUDES_in_any1556); 

                    }
                    break;
                case 11 :
                    // CSSTreeParser.g:629:5: COLON
                    {
                    match(input,COLON,FOLLOW_COLON_in_any1562); 

                    }
                    break;
                case 12 :
                    // CSSTreeParser.g:630:5: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_any1568); 

                    }
                    break;
                case 13 :
                    // CSSTreeParser.g:631:5: GREATER
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_any1574); 

                    }
                    break;
                case 14 :
                    // CSSTreeParser.g:632:5: EQUALS
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_any1580); 

                    }
                    break;
                case 15 :
                    // CSSTreeParser.g:633:5: SLASH
                    {
                    match(input,SLASH,FOLLOW_SLASH_in_any1586); 

                    }
                    break;
                case 16 :
                    // CSSTreeParser.g:634:5: EXCLAMATION
                    {
                    match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_any1592); 

                    }
                    break;
                case 17 :
                    // CSSTreeParser.g:635:5: ^( FUNCTION ( any )* )
                    {
                    match(input,FUNCTION,FOLLOW_FUNCTION_in_any1599); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:635:16: ( any )*
                        loop32:
                        do {
                            int alt32=2;
                            int LA32_0 = input.LA(1);

                            if ( ((LA32_0>=PARENBLOCK && LA32_0<=BRACEBLOCK)||LA32_0==INVALID_STRING||(LA32_0>=COLON && LA32_0<=IDENT)||(LA32_0>=COMMA && LA32_0<=CLASSKEYWORD)||(LA32_0>=NUMBER && LA32_0<=SLASH)||LA32_0==FUNCTION||LA32_0==DASHMATCH||LA32_0==STRING) ) {
                                alt32=1;
                            }


                            switch (alt32) {
                        	case 1 :
                        	    // CSSTreeParser.g:635:16: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_any1601);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop32;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }

                    }
                    break;
                case 18 :
                    // CSSTreeParser.g:636:5: DASHMATCH
                    {
                    match(input,DASHMATCH,FOLLOW_DASHMATCH_in_any1610); 

                    }
                    break;
                case 19 :
                    // CSSTreeParser.g:637:5: ^( PARENBLOCK ( any )* )
                    {
                    match(input,PARENBLOCK,FOLLOW_PARENBLOCK_in_any1617); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:637:18: ( any )*
                        loop33:
                        do {
                            int alt33=2;
                            int LA33_0 = input.LA(1);

                            if ( ((LA33_0>=PARENBLOCK && LA33_0<=BRACEBLOCK)||LA33_0==INVALID_STRING||(LA33_0>=COLON && LA33_0<=IDENT)||(LA33_0>=COMMA && LA33_0<=CLASSKEYWORD)||(LA33_0>=NUMBER && LA33_0<=SLASH)||LA33_0==FUNCTION||LA33_0==DASHMATCH||LA33_0==STRING) ) {
                                alt33=1;
                            }


                            switch (alt33) {
                        	case 1 :
                        	    // CSSTreeParser.g:637:18: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_any1619);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop33;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }

                    }
                    break;
                case 20 :
                    // CSSTreeParser.g:638:5: ^( BRACEBLOCK ( any )* )
                    {
                    match(input,BRACEBLOCK,FOLLOW_BRACEBLOCK_in_any1628); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:638:18: ( any )*
                        loop34:
                        do {
                            int alt34=2;
                            int LA34_0 = input.LA(1);

                            if ( ((LA34_0>=PARENBLOCK && LA34_0<=BRACEBLOCK)||LA34_0==INVALID_STRING||(LA34_0>=COLON && LA34_0<=IDENT)||(LA34_0>=COMMA && LA34_0<=CLASSKEYWORD)||(LA34_0>=NUMBER && LA34_0<=SLASH)||LA34_0==FUNCTION||LA34_0==DASHMATCH||LA34_0==STRING) ) {
                                alt34=1;
                            }


                            switch (alt34) {
                        	case 1 :
                        	    // CSSTreeParser.g:638:18: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_any1630);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop34;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "any"

    // Delegated rules


    protected DFA20 dfa20 = new DFA20(this);
    static final String DFA20_eotS =
        "\27\uffff";
    static final String DFA20_eofS =
        "\27\uffff";
    static final String DFA20_minS =
        "\1\7\2\uffff\1\54\23\uffff";
    static final String DFA20_maxS =
        "\1\76\2\uffff\1\56\23\uffff";
    static final String DFA20_acceptS =
        "\1\uffff\1\1\1\2\1\uffff\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13"+
        "\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25";
    static final String DFA20_specialS =
        "\27\uffff}>";
    static final String[] DFA20_transitionS = {
            "\1\25\1\26\14\uffff\1\7\13\uffff\1\14\1\1\5\uffff\1\15\1\uffff"+
            "\1\2\1\3\1\4\1\5\1\6\1\10\1\11\1\12\1\13\1\16\1\17\1\20\1\21"+
            "\1\22\1\23\1\uffff\1\24\3\uffff\1\7",
            "",
            "",
            "\1\4\1\5\1\6",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA20_eot = DFA.unpackEncodedString(DFA20_eotS);
    static final short[] DFA20_eof = DFA.unpackEncodedString(DFA20_eofS);
    static final char[] DFA20_min = DFA.unpackEncodedStringToUnsignedChars(DFA20_minS);
    static final char[] DFA20_max = DFA.unpackEncodedStringToUnsignedChars(DFA20_maxS);
    static final short[] DFA20_accept = DFA.unpackEncodedString(DFA20_acceptS);
    static final short[] DFA20_special = DFA.unpackEncodedString(DFA20_specialS);
    static final short[][] DFA20_transition;

    static {
        int numStates = DFA20_transitionS.length;
        DFA20_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA20_transition[i] = DFA.unpackEncodedString(DFA20_transitionS[i]);
        }
    }

    class DFA20 extends DFA {

        public DFA20(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 20;
            this.eot = DFA20_eot;
            this.eof = DFA20_eof;
            this.min = DFA20_min;
            this.max = DFA20_max;
            this.accept = DFA20_accept;
            this.special = DFA20_special;
            this.transition = DFA20_transition;
        }
        public String getDescription() {
            return "414:1: valuepart : (i= IDENT | CLASSKEYWORD | ( MINUS )? n= NUMBER | ( MINUS )? p= PERCENTAGE | ( MINUS )? d= DIMENSION | s= string | u= URI | h= HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | PLUS | ASTERISK | ^(f= FUNCTION t= terms ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) );";
        }
    }
 

    public static final BitSet FOLLOW_STYLESHEET_in_stylesheet60 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_stylesheet69 = new BitSet(new long[]{0x00000041C6100208L});
    public static final BitSet FOLLOW_ruleset_in_statement118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atstatement_in_statement128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARSET_in_atstatement161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_IMPORT_in_atstatement167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_atstatement175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_END_in_atstatement186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PAGE_in_atstatement194 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_atstatement199 = new BitSet(new long[]{0x0000000001020008L});
    public static final BitSet FOLLOW_declaration_in_atstatement207 = new BitSet(new long[]{0x0000000001020008L});
    public static final BitSet FOLLOW_MEDIA_in_atstatement233 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_media_in_atstatement238 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_ruleset_in_atstatement249 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_INVALID_STATEMENT_in_atstatement271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_media303 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_RULE_in_ruleset349 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_combined_selector_in_ruleset363 = new BitSet(new long[]{0x0000000001420408L});
    public static final BitSet FOLLOW_declaration_in_ruleset391 = new BitSet(new long[]{0x0000000001020008L});
    public static final BitSet FOLLOW_DECLARATION_in_declaration458 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_important_in_declaration465 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_property_in_declaration477 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_terms_in_declaration488 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INVALID_DECLARATION_in_declaration509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORTANT_in_important526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_property566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_terms611 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_term_in_terms613 = new BitSet(new long[]{0x45FFFD86002001C8L});
    public static final BitSet FOLLOW_valuepart_in_term636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CURLYBLOCK_in_term653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATKEYWORD_in_term663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_valuepart691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_valuepart703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart711 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_NUMBER_in_valuepart719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart733 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_PERCENTAGE_in_valuepart741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart753 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_DIMENSION_in_valuepart761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_valuepart777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_URI_in_valuepart794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_valuepart812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_valuepart830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCLUDES_in_valuepart841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_valuepart852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_valuepart866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_valuepart884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_valuepart896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_valuepart909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_valuepart920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASTERISK_in_valuepart928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_valuepart942 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_terms_in_valuepart946 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DASHMATCH_in_valuepart957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENBLOCK_in_valuepart968 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_valuepart970 = new BitSet(new long[]{0x453FF70600200188L});
    public static final BitSet FOLLOW_BRACEBLOCK_in_valuepart983 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_valuepart985 = new BitSet(new long[]{0x453FF70600200188L});
    public static final BitSet FOLLOW_selector_in_combined_selector1033 = new BitSet(new long[]{0x000000000000E002L});
    public static final BitSet FOLLOW_combinator_in_combined_selector1042 = new BitSet(new long[]{0x0000000001420408L});
    public static final BitSet FOLLOW_selector_in_combined_selector1046 = new BitSet(new long[]{0x000000000000E002L});
    public static final BitSet FOLLOW_CHILD_in_combinator1076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADJACENT_in_combinator1083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DESCENDANT_in_combinator1090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELECTOR_in_selector1126 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ELEMENT_in_selector1138 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_selector1154 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_selpart_in_selector1201 = new BitSet(new long[]{0x0101040000811008L});
    public static final BitSet FOLLOW_SELECTOR_in_selector1220 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_selpart_in_selector1232 = new BitSet(new long[]{0x0101040000811008L});
    public static final BitSet FOLLOW_INVALID_SELECTOR_in_selector1250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PSEUDO_in_selpart1282 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_IDENT_in_selpart1286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_selpart1298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_selpart1310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_selpart1318 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_attribute_in_selpart1322 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNCTION_in_selpart1337 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_selpart1341 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INVALID_SELPART_in_selpart1356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_attribute1390 = new BitSet(new long[]{0x0414000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_attribute1399 = new BitSet(new long[]{0x4000000400200000L});
    public static final BitSet FOLLOW_INCLUDES_in_attribute1410 = new BitSet(new long[]{0x4000000400200000L});
    public static final BitSet FOLLOW_DASHMATCH_in_attribute1421 = new BitSet(new long[]{0x4000000400200000L});
    public static final BitSet FOLLOW_IDENT_in_attribute1439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_attribute1451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_string1479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_STRING_in_string1486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_any1502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_any1508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_any1514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENTAGE_in_any1520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_any1526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_any1532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_URI_in_any1538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_any1544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_any1550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCLUDES_in_any1556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_any1562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_any1568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_any1574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_any1580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_any1586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_any1592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_any1599 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_any1601 = new BitSet(new long[]{0x453FF70600200188L});
    public static final BitSet FOLLOW_DASHMATCH_in_any1610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENBLOCK_in_any1617 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_any1619 = new BitSet(new long[]{0x453FF70600200188L});
    public static final BitSet FOLLOW_BRACEBLOCK_in_any1628 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_any1630 = new BitSet(new long[]{0x453FF70600200188L});

}