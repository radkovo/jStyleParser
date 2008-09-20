// $ANTLR 3.1 CSSTreeParser.g 2008-09-20 22:01:07

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

@SuppressWarnings("unchecked")
public class CSSTreeParser extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "STYLESHEET", "INLINESTYLE", "ATBLOCK", "CURLYBLOCK", "PARENBLOCK", "BRACEBLOCK", "RULE", "SELECTOR", "ELEMENT", "PSEUDO", "ADJACENT", "CHILD", "DESCENDANT", "ATTRIBUTE", "DECLARATION", "VALUE", "IMPORTANT", "IMPORT_END", "INVALID_STRING", "INVALID_SELECTOR", "INVALID_SELPART", "INVALID_DECLARATION", "INVALID_STATEMENT", "INVALID_IMPORT", "S", "CDO", "CDC", "CHARSET", "IMPORT", "PAGE", "COLON", "IDENT", "LCURLY", "RCURLY", "MEDIA", "ATKEYWORD", "COMMA", "SEMICOLON", "EXCLAMATION", "CLASSKEYWORD", "MINUS", "NUMBER", "PERCENTAGE", "DIMENSION", "URI", "HASH", "UNIRANGE", "INCLUDES", "GREATER", "EQUALS", "SLASH", "PLUS", "ASTERISK", "FUNCTION", "RPAREN", "DASHMATCH", "LPAREN", "LBRACE", "RBRACE", "STRING", "IDENT_MACR", "STRING_MACR", "NAME_MACR", "NUMBER_MACR", "W_MACR", "URI_MACR", "APOS", "QUOT", "W_CHAR", "COMMENT", "SL_COMMENT", "INVALID_TOKEN", "NAME_START", "NAME_CHAR", "NON_ASCII", "ESCAPE_CHAR", "STRING_CHAR", "URI_CHAR", "NL_CHAR", "'important'"
    };
    public static final int COMMA=40;
    public static final int ELEMENT=12;
    public static final int INVALID_IMPORT=27;
    public static final int CHARSET=31;
    public static final int MINUS=44;
    public static final int BRACEBLOCK=9;
    public static final int PARENBLOCK=8;
    public static final int HASH=49;
    public static final int DASHMATCH=59;
    public static final int IMPORT_END=21;
    public static final int SELECTOR=11;
    public static final int NUMBER=45;
    public static final int PAGE=33;
    public static final int INVALID_TOKEN=75;
    public static final int URI_CHAR=81;
    public static final int NAME_MACR=66;
    public static final int RULE=10;
    public static final int CLASSKEYWORD=43;
    public static final int INCLUDES=51;
    public static final int MEDIA=38;
    public static final int DESCENDANT=16;
    public static final int VALUE=19;
    public static final int PSEUDO=13;
    public static final int STYLESHEET=4;
    public static final int URI=48;
    public static final int IMPORT=32;
    public static final int LBRACE=61;
    public static final int RBRACE=62;
    public static final int INVALID_SELECTOR=23;
    public static final int S=28;
    public static final int RCURLY=37;
    public static final int DECLARATION=18;
    public static final int LCURLY=36;
    public static final int ESCAPE_CHAR=79;
    public static final int FUNCTION=57;
    public static final int W_MACR=68;
    public static final int RPAREN=58;
    public static final int IDENT_MACR=64;
    public static final int NAME_CHAR=77;
    public static final int NAME_START=76;
    public static final int GREATER=52;
    public static final int LPAREN=60;
    public static final int W_CHAR=72;
    public static final int PLUS=55;
    public static final int NON_ASCII=78;
    public static final int SL_COMMENT=74;
    public static final int APOS=70;
    public static final int ATKEYWORD=39;
    public static final int STRING_CHAR=80;
    public static final int URI_MACR=69;
    public static final int IDENT=35;
    public static final int SLASH=54;
    public static final int UNIRANGE=50;
    public static final int IMPORTANT=20;
    public static final int EXCLAMATION=42;
    public static final int STRING=63;
    public static final int NL_CHAR=82;
    public static final int INVALID_STRING=22;
    public static final int COMMENT=73;
    public static final int CDC=30;
    public static final int ADJACENT=14;
    public static final int INVALID_DECLARATION=25;
    public static final int NUMBER_MACR=67;
    public static final int EQUALS=53;
    public static final int CURLYBLOCK=7;
    public static final int DIMENSION=47;
    public static final int INLINESTYLE=5;
    public static final int INVALID_STATEMENT=26;
    public static final int SEMICOLON=41;
    public static final int STRING_MACR=65;
    public static final int EOF=-1;
    public static final int ASTERISK=56;
    public static final int COLON=34;
    public static final int CDO=29;
    public static final int ATTRIBUTE=17;
    public static final int T__83=83;
    public static final int CHILD=15;
    public static final int INVALID_SELPART=24;
    public static final int PERCENTAGE=46;
    public static final int QUOT=71;
    public static final int ATBLOCK=6;

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
    // CSSTreeParser.g:158:1: atstatement returns [RuleBlock<?> stmnt] : ( CHARSET | INVALID_IMPORT | i= IMPORT | IMPORT_END | ^( PAGE (i= IDENT )? decl= declarations ) | ^( MEDIA (mediaList= media )? (rs= ruleset )* ) | INVALID_STATEMENT );
    public final RuleBlock<?> atstatement() throws RecognitionException {
        atstatement_stack.push(new atstatement_scope());
        RuleBlock<?> stmnt = null;

        CommonTree i=null;
        List<Declaration> decl = null;

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
            // CSSTreeParser.g:174:2: ( CHARSET | INVALID_IMPORT | i= IMPORT | IMPORT_END | ^( PAGE (i= IDENT )? decl= declarations ) | ^( MEDIA (mediaList= media )? (rs= ruleset )* ) | INVALID_STATEMENT )
            int alt6=7;
            switch ( input.LA(1) ) {
            case CHARSET:
                {
                alt6=1;
                }
                break;
            case INVALID_IMPORT:
                {
                alt6=2;
                }
                break;
            case IMPORT:
                {
                alt6=3;
                }
                break;
            case IMPORT_END:
                {
                alt6=4;
                }
                break;
            case PAGE:
                {
                alt6=5;
                }
                break;
            case MEDIA:
                {
                alt6=6;
                }
                break;
            case INVALID_STATEMENT:
                {
                alt6=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
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
                    // CSSTreeParser.g:188:4: ^( PAGE (i= IDENT )? decl= declarations )
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

                        pushFollow(FOLLOW_declarations_in_atstatement206);
                        decl=declarations();

                        state._fsp--;


                        		   	if(decl!=null && !decl.isEmpty()) {	
                                    	RulePage rp = rf.createPage(ruleNum++);
                                        rp.replaceAll(declarations);
                                        rp.setPseudo(pseudo);
                                        stmnt = rp;
                                        log.info("Create @page as {}th with:\n{}",  ruleNum, rp);
                                    }
                        		

                        match(input, Token.UP, null); 
                    }

                    }
                    break;
                case 6 :
                    // CSSTreeParser.g:198:4: ^( MEDIA (mediaList= media )? (rs= ruleset )* )
                    {
                    match(input,MEDIA,FOLLOW_MEDIA_in_atstatement217); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:198:12: (mediaList= media )?
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==IDENT) ) {
                            alt4=1;
                        }
                        switch (alt4) {
                            case 1 :
                                // CSSTreeParser.g:198:13: mediaList= media
                                {
                                pushFollow(FOLLOW_media_in_atstatement222);
                                mediaList=media();

                                state._fsp--;


                                }
                                break;

                        }

                        // CSSTreeParser.g:199:4: (rs= ruleset )*
                        loop5:
                        do {
                            int alt5=2;
                            int LA5_0 = input.LA(1);

                            if ( (LA5_0==RULE) ) {
                                alt5=1;
                            }


                            switch (alt5) {
                        	case 1 :
                        	    // CSSTreeParser.g:199:5: rs= ruleset
                        	    {
                        	    pushFollow(FOLLOW_ruleset_in_atstatement233);
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
                        	    break loop5;
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
                    // CSSTreeParser.g:231:4: INVALID_STATEMENT
                    {
                    match(input,INVALID_STATEMENT,FOLLOW_INVALID_STATEMENT_in_atstatement255); 
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
    // CSSTreeParser.g:234:1: media returns [List<String> affected] : (i= IDENT )+ ;
    public final List<String> media() throws RecognitionException {
        List<String> affected = null;

        CommonTree i=null;


           logEnter("media");
           affected = new ArrayList<String>();

        try {
            // CSSTreeParser.g:243:2: ( (i= IDENT )+ )
            // CSSTreeParser.g:243:4: (i= IDENT )+
            {
            // CSSTreeParser.g:243:4: (i= IDENT )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==IDENT) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // CSSTreeParser.g:243:5: i= IDENT
            	    {
            	    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_media287); 

            	    				   String m = extractText(i);
            	    				   if(css.isSupportedMedia(m)) affected.add(m);
            	        

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
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
    // CSSTreeParser.g:249:1: ruleset returns [RuleBlock<?> stmnt] : ^( RULE (cs= combined_selector )* decl= declarations ) ;
    public final RuleBlock<?> ruleset() throws RecognitionException {
        RuleBlock<?> stmnt = null;

        CombinedSelector cs = null;

        List<Declaration> decl = null;



            logEnter("ruleset"); 
            List<CombinedSelector> cslist = new ArrayList<CombinedSelector>();

        try {
            // CSSTreeParser.g:295:5: ( ^( RULE (cs= combined_selector )* decl= declarations ) )
            // CSSTreeParser.g:295:7: ^( RULE (cs= combined_selector )* decl= declarations )
            {
            match(input,RULE,FOLLOW_RULE_in_ruleset333); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // CSSTreeParser.g:296:9: (cs= combined_selector )*
                loop8:
                do {
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==SELECTOR||LA8_0==INVALID_SELECTOR) ) {
                        alt8=1;
                    }


                    switch (alt8) {
                	case 1 :
                	    // CSSTreeParser.g:296:10: cs= combined_selector
                	    {
                	    pushFollow(FOLLOW_combined_selector_in_ruleset347);
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
                	    break loop8;
                    }
                } while (true);

                pushFollow(FOLLOW_declarations_in_ruleset368);
                decl=declarations();

                state._fsp--;


                match(input, Token.UP, null); 
            }

            }


                if(((statement_scope)statement_stack.peek()).invalid || cslist.isEmpty() || decl.isEmpty()) {
                    stmnt = null;
                    log.debug("Ruleset not valid, so not created");
                }
                else {    
                    RuleSet rs = rf.createSet(ruleNum++);
                    rs.setSelectors(cslist);
                    rs.replaceAll(decl);
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


    // $ANTLR start "declarations"
    // CSSTreeParser.g:307:1: declarations returns [List<Declaration> decl] : (d= declaration )* ;
    public final List<Declaration> declarations() throws RecognitionException {
        List<Declaration> decl = null;

        Declaration d = null;



        		  logEnter("declarations");
        		  decl = new ArrayList<Declaration>();

        try {
            // CSSTreeParser.g:318:2: ( (d= declaration )* )
            // CSSTreeParser.g:318:4: (d= declaration )*
            {
            // CSSTreeParser.g:318:4: (d= declaration )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==DECLARATION||LA9_0==INVALID_DECLARATION) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // CSSTreeParser.g:318:5: d= declaration
            	    {
            	    pushFollow(FOLLOW_declaration_in_declarations411);
            	    d=declaration();

            	    state._fsp--;


            	    	     if(d!=null) {
            	                decl.add(d);
            	                log.debug("Inserted declaration #{} ", decl.size()+1);
            	    		 }	
            	    	 

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }


            		   logLeave("declarations");

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return decl;
    }
    // $ANTLR end "declarations"

    protected static class declaration_scope {
        Declaration d;
        boolean invalid;
    }
    protected Stack declaration_stack = new Stack();


    // $ANTLR start "declaration"
    // CSSTreeParser.g:328:1: declaration returns [Declaration decl] : ( ^( DECLARATION ( important )? property t= terms ) | INVALID_DECLARATION );
    public final Declaration declaration() throws RecognitionException {
        declaration_stack.push(new declaration_scope());
        Declaration decl = null;

        List<Term<?>> t = null;



            logEnter("declaration");
            ((declaration_scope)declaration_stack.peek()).d = decl = rf.createDeclaration();
            ((declaration_scope)declaration_stack.peek()).invalid = false;

        try {
            // CSSTreeParser.g:351:3: ( ^( DECLARATION ( important )? property t= terms ) | INVALID_DECLARATION )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==DECLARATION) ) {
                alt11=1;
            }
            else if ( (LA11_0==INVALID_DECLARATION) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // CSSTreeParser.g:351:5: ^( DECLARATION ( important )? property t= terms )
                    {
                    match(input,DECLARATION,FOLLOW_DECLARATION_in_declaration455); 

                    match(input, Token.DOWN, null); 
                    // CSSTreeParser.g:352:4: ( important )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==IMPORTANT) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // CSSTreeParser.g:352:5: important
                            {
                            pushFollow(FOLLOW_important_in_declaration462);
                            important();

                            state._fsp--;

                             decl.setImportant(true);

                            }
                            break;

                    }

                    pushFollow(FOLLOW_property_in_declaration474);
                    property();

                    state._fsp--;

                    pushFollow(FOLLOW_terms_in_declaration485);
                    t=terms();

                    state._fsp--;

                    decl.replaceAll(t);

                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:356:5: INVALID_DECLARATION
                    {
                    match(input,INVALID_DECLARATION,FOLLOW_INVALID_DECLARATION_in_declaration506); 
                     ((declaration_scope)declaration_stack.peek()).invalid =true;

                    }
                    break;

            }

                if(((declaration_scope)declaration_stack.peek()).invalid || declaration_stack.isEmpty()) {
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
    // CSSTreeParser.g:359:1: important : IMPORTANT ;
    public final void important() throws RecognitionException {
        try {
            // CSSTreeParser.g:360:5: ( IMPORTANT )
            // CSSTreeParser.g:360:7: IMPORTANT
            {
            match(input,IMPORTANT,FOLLOW_IMPORTANT_in_important523); 

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
    // CSSTreeParser.g:363:1: property : i= IDENT ;
    public final void property() throws RecognitionException {
        CommonTree i=null;


            logEnter("property");

        try {
            // CSSTreeParser.g:374:3: (i= IDENT )
            // CSSTreeParser.g:374:5: i= IDENT
            {
            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_property563); 
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
    // CSSTreeParser.g:377:1: terms returns [List<Term<?>> tlist] : ^( VALUE ( term )+ ) ;
    public final List<Term<?>> terms() throws RecognitionException {
        terms_stack.push(new terms_scope());
        List<Term<?>> tlist = null;


            logEnter("terms");
            ((terms_scope)terms_stack.peek()).list = tlist = new ArrayList<Term<?>>();
            ((terms_scope)terms_stack.peek()).term = null;
            ((terms_scope)terms_stack.peek()).op = null;
            ((terms_scope)terms_stack.peek()).unary = 1;

        try {
            // CSSTreeParser.g:398:5: ( ^( VALUE ( term )+ ) )
            // CSSTreeParser.g:398:7: ^( VALUE ( term )+ )
            {
            match(input,VALUE,FOLLOW_VALUE_in_terms608); 

            match(input, Token.DOWN, null); 
            // CSSTreeParser.g:398:15: ( term )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>=CURLYBLOCK && LA12_0<=BRACEBLOCK)||LA12_0==INVALID_STRING||(LA12_0>=COLON && LA12_0<=IDENT)||(LA12_0>=ATKEYWORD && LA12_0<=COMMA)||(LA12_0>=CLASSKEYWORD && LA12_0<=FUNCTION)||LA12_0==DASHMATCH||LA12_0==STRING) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // CSSTreeParser.g:398:15: term
            	    {
            	    pushFollow(FOLLOW_term_in_terms610);
            	    term();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
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
    // CSSTreeParser.g:401:1: term : ( valuepart | CURLYBLOCK | ATKEYWORD );
    public final void term() throws RecognitionException {
        try {
            // CSSTreeParser.g:402:5: ( valuepart | CURLYBLOCK | ATKEYWORD )
            int alt13=3;
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
                alt13=1;
                }
                break;
            case CURLYBLOCK:
                {
                alt13=2;
                }
                break;
            case ATKEYWORD:
                {
                alt13=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // CSSTreeParser.g:402:7: valuepart
                    {
                    pushFollow(FOLLOW_valuepart_in_term633);
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
                    // CSSTreeParser.g:413:7: CURLYBLOCK
                    {
                    match(input,CURLYBLOCK,FOLLOW_CURLYBLOCK_in_term650); 
                     ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:414:7: ATKEYWORD
                    {
                    match(input,ATKEYWORD,FOLLOW_ATKEYWORD_in_term660); 
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
    // CSSTreeParser.g:417:1: valuepart : (i= IDENT | CLASSKEYWORD | ( MINUS )? n= NUMBER | ( MINUS )? p= PERCENTAGE | ( MINUS )? d= DIMENSION | s= string | u= URI | h= HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | PLUS | ASTERISK | ^(f= FUNCTION t= terms ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) );
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
            // CSSTreeParser.g:439:5: (i= IDENT | CLASSKEYWORD | ( MINUS )? n= NUMBER | ( MINUS )? p= PERCENTAGE | ( MINUS )? d= DIMENSION | s= string | u= URI | h= HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | PLUS | ASTERISK | ^(f= FUNCTION t= terms ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) )
            int alt19=21;
            alt19 = dfa19.predict(input);
            switch (alt19) {
                case 1 :
                    // CSSTreeParser.g:439:7: i= IDENT
                    {
                    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_valuepart688); 
                    ((terms_scope)terms_stack.peek()).term = tf.createIdent(extractText(i));

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:440:7: CLASSKEYWORD
                    {
                    match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_valuepart700); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:441:4: ( MINUS )? n= NUMBER
                    {
                    // CSSTreeParser.g:441:4: ( MINUS )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==MINUS) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // CSSTreeParser.g:441:5: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart708); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    n=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_valuepart716); 
                    ((terms_scope)terms_stack.peek()).term = tf.createNumeric(extractText(n), ((terms_scope)terms_stack.peek()).unary);

                    }
                    break;
                case 4 :
                    // CSSTreeParser.g:442:7: ( MINUS )? p= PERCENTAGE
                    {
                    // CSSTreeParser.g:442:7: ( MINUS )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==MINUS) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // CSSTreeParser.g:442:8: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart730); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    p=(CommonTree)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_valuepart738); 
                     ((terms_scope)terms_stack.peek()).term = tf.createPercent(extractText(p), ((terms_scope)terms_stack.peek()).unary);

                    }
                    break;
                case 5 :
                    // CSSTreeParser.g:443:7: ( MINUS )? d= DIMENSION
                    {
                    // CSSTreeParser.g:443:7: ( MINUS )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==MINUS) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // CSSTreeParser.g:443:8: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart750); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    d=(CommonTree)match(input,DIMENSION,FOLLOW_DIMENSION_in_valuepart758); 
                    String dim = extractText(d);
                    	 ((terms_scope)terms_stack.peek()).term = tf.createDimension(dim, ((terms_scope)terms_stack.peek()).unary);
                         if(((terms_scope)terms_stack.peek()).term==null) {
                    		 log.info("Unable to create dimension from {}, unary {}", dim, ((terms_scope)terms_stack.peek()).unary);
                             ((declaration_scope)declaration_stack.peek()).invalid = true;
                    	 }
                        

                    }
                    break;
                case 6 :
                    // CSSTreeParser.g:451:7: s= string
                    {
                    pushFollow(FOLLOW_string_in_valuepart774);
                    s=string();

                    state._fsp--;

                     if(s!=null) ((terms_scope)terms_stack.peek()).term = tf.createString(s);
                    	  else ((declaration_scope)declaration_stack.peek()).invalid =true;
                    	

                    }
                    break;
                case 7 :
                    // CSSTreeParser.g:455:7: u= URI
                    {
                    u=(CommonTree)match(input,URI,FOLLOW_URI_in_valuepart791); 
                    ((terms_scope)terms_stack.peek()).term = tf.createURI(extractText(u));

                    }
                    break;
                case 8 :
                    // CSSTreeParser.g:456:7: h= HASH
                    {
                    h=(CommonTree)match(input,HASH,FOLLOW_HASH_in_valuepart809); 
                    ((terms_scope)terms_stack.peek()).term = tf.createColor(extractText(h));
                         if(((terms_scope)terms_stack.peek()).term==null)
                             ((declaration_scope)declaration_stack.peek()).invalid = true;
                        

                    }
                    break;
                case 9 :
                    // CSSTreeParser.g:461:7: UNIRANGE
                    {
                    match(input,UNIRANGE,FOLLOW_UNIRANGE_in_valuepart827); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 10 :
                    // CSSTreeParser.g:462:7: INCLUDES
                    {
                    match(input,INCLUDES,FOLLOW_INCLUDES_in_valuepart838); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 11 :
                    // CSSTreeParser.g:463:7: COLON
                    {
                    match(input,COLON,FOLLOW_COLON_in_valuepart849); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 12 :
                    // CSSTreeParser.g:464:7: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_valuepart863); 
                    ((terms_scope)terms_stack.peek()).op = Term.Operator.COMMA;

                    }
                    break;
                case 13 :
                    // CSSTreeParser.g:465:7: GREATER
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_valuepart881); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 14 :
                    // CSSTreeParser.g:466:7: EQUALS
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_valuepart893); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 15 :
                    // CSSTreeParser.g:467:7: SLASH
                    {
                    match(input,SLASH,FOLLOW_SLASH_in_valuepart906); 
                    ((terms_scope)terms_stack.peek()).op = Term.Operator.SLASH;

                    }
                    break;
                case 16 :
                    // CSSTreeParser.g:468:4: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_valuepart917); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 17 :
                    // CSSTreeParser.g:469:4: ASTERISK
                    {
                    match(input,ASTERISK,FOLLOW_ASTERISK_in_valuepart925); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 18 :
                    // CSSTreeParser.g:470:7: ^(f= FUNCTION t= terms )
                    {
                    f=(CommonTree)match(input,FUNCTION,FOLLOW_FUNCTION_in_valuepart939); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_terms_in_valuepart943);
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
                    // CSSTreeParser.g:477:7: DASHMATCH
                    {
                    match(input,DASHMATCH,FOLLOW_DASHMATCH_in_valuepart954); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 20 :
                    // CSSTreeParser.g:478:7: ^( PARENBLOCK ( any )* )
                    {
                    match(input,PARENBLOCK,FOLLOW_PARENBLOCK_in_valuepart965); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:478:20: ( any )*
                        loop17:
                        do {
                            int alt17=2;
                            int LA17_0 = input.LA(1);

                            if ( ((LA17_0>=PARENBLOCK && LA17_0<=BRACEBLOCK)||LA17_0==INVALID_STRING||(LA17_0>=COLON && LA17_0<=IDENT)||LA17_0==COMMA||(LA17_0>=EXCLAMATION && LA17_0<=CLASSKEYWORD)||(LA17_0>=NUMBER && LA17_0<=SLASH)||LA17_0==FUNCTION||LA17_0==DASHMATCH||LA17_0==STRING) ) {
                                alt17=1;
                            }


                            switch (alt17) {
                        	case 1 :
                        	    // CSSTreeParser.g:478:20: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_valuepart967);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop17;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 21 :
                    // CSSTreeParser.g:479:7: ^( BRACEBLOCK ( any )* )
                    {
                    match(input,BRACEBLOCK,FOLLOW_BRACEBLOCK_in_valuepart980); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:479:20: ( any )*
                        loop18:
                        do {
                            int alt18=2;
                            int LA18_0 = input.LA(1);

                            if ( ((LA18_0>=PARENBLOCK && LA18_0<=BRACEBLOCK)||LA18_0==INVALID_STRING||(LA18_0>=COLON && LA18_0<=IDENT)||LA18_0==COMMA||(LA18_0>=EXCLAMATION && LA18_0<=CLASSKEYWORD)||(LA18_0>=NUMBER && LA18_0<=SLASH)||LA18_0==FUNCTION||LA18_0==DASHMATCH||LA18_0==STRING) ) {
                                alt18=1;
                            }


                            switch (alt18) {
                        	case 1 :
                        	    // CSSTreeParser.g:479:20: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_valuepart982);
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
    // CSSTreeParser.g:482:1: combined_selector returns [CombinedSelector combinedSelector] : s= selector (c= combinator s= selector )* ;
    public final CombinedSelector combined_selector() throws RecognitionException {
        combined_selector_stack.push(new combined_selector_scope());
        CombinedSelector combinedSelector = null;

        Selector s = null;

        Selector.Combinator c = null;



        	logEnter("combined_selector");	  
        	combinedSelector = (CombinedSelector) rf.createCombinedSelector().unlock();

        try {
            // CSSTreeParser.g:512:2: (s= selector (c= combinator s= selector )* )
            // CSSTreeParser.g:512:4: s= selector (c= combinator s= selector )*
            {
            pushFollow(FOLLOW_selector_in_combined_selector1030);
            s=selector();

            state._fsp--;


            	     combinedSelector.add(s);
            	  
            // CSSTreeParser.g:515:3: (c= combinator s= selector )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( ((LA20_0>=ADJACENT && LA20_0<=DESCENDANT)) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // CSSTreeParser.g:515:4: c= combinator s= selector
            	    {
            	    pushFollow(FOLLOW_combinator_in_combined_selector1039);
            	    c=combinator();

            	    state._fsp--;

            	    pushFollow(FOLLOW_selector_in_combined_selector1043);
            	    s=selector();

            	    state._fsp--;


            	    	     s.setCombinator(c);
            	    	     combinedSelector.add(s);	
            	    	  

            	    }
            	    break;

            	default :
            	    break loop20;
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
    // CSSTreeParser.g:522:1: combinator returns [Selector.Combinator combinator] : ( CHILD | ADJACENT | DESCENDANT );
    public final Selector.Combinator combinator() throws RecognitionException {
        Selector.Combinator combinator = null;

         logEnter("combinator"); 
        try {
            // CSSTreeParser.g:525:2: ( CHILD | ADJACENT | DESCENDANT )
            int alt21=3;
            switch ( input.LA(1) ) {
            case CHILD:
                {
                alt21=1;
                }
                break;
            case ADJACENT:
                {
                alt21=2;
                }
                break;
            case DESCENDANT:
                {
                alt21=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // CSSTreeParser.g:525:4: CHILD
                    {
                    match(input,CHILD,FOLLOW_CHILD_in_combinator1073); 
                    combinator =Selector.Combinator.CHILD;

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:526:4: ADJACENT
                    {
                    match(input,ADJACENT,FOLLOW_ADJACENT_in_combinator1080); 
                    combinator =Selector.Combinator.ADJACENT;

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:527:4: DESCENDANT
                    {
                    match(input,DESCENDANT,FOLLOW_DESCENDANT_in_combinator1087); 
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
    // CSSTreeParser.g:531:1: selector returns [Selector sel] : ( ^( SELECTOR ^( ELEMENT (i= IDENT )? ) ( selpart )* ) | ^( SELECTOR ( selpart )+ ) | INVALID_SELECTOR );
    public final Selector selector() throws RecognitionException {
        selector_stack.push(new selector_scope());
        Selector sel = null;

        CommonTree i=null;


        	logEnter("selector");
        	((selector_scope)selector_stack.peek()).s =sel=(Selector)rf.createSelector().unlock();
        	Selector.ElementName en = rf.createElement(Selector.SelectorPart.WILDCARD);

        try {
            // CSSTreeParser.g:543:5: ( ^( SELECTOR ^( ELEMENT (i= IDENT )? ) ( selpart )* ) | ^( SELECTOR ( selpart )+ ) | INVALID_SELECTOR )
            int alt25=3;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==SELECTOR) ) {
                int LA25_1 = input.LA(2);

                if ( (LA25_1==DOWN) ) {
                    int LA25_3 = input.LA(3);

                    if ( (LA25_3==ELEMENT) ) {
                        alt25=1;
                    }
                    else if ( (LA25_3==PSEUDO||LA25_3==ATTRIBUTE||LA25_3==INVALID_SELPART||LA25_3==CLASSKEYWORD||LA25_3==HASH) ) {
                        alt25=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 25, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 25, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA25_0==INVALID_SELECTOR) ) {
                alt25=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // CSSTreeParser.g:543:7: ^( SELECTOR ^( ELEMENT (i= IDENT )? ) ( selpart )* )
                    {
                    match(input,SELECTOR,FOLLOW_SELECTOR_in_selector1123); 

                    match(input, Token.DOWN, null); 
                    match(input,ELEMENT,FOLLOW_ELEMENT_in_selector1135); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:545:11: (i= IDENT )?
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==IDENT) ) {
                            alt22=1;
                        }
                        switch (alt22) {
                            case 1 :
                                // CSSTreeParser.g:545:12: i= IDENT
                                {
                                i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_selector1151); 
                                 en.setValue(extractText(i)); 

                                }
                                break;

                        }


                        match(input, Token.UP, null); 
                    }

                    		  log.debug("Adding element name: {}.", en.getValue());
                    		  ((selector_scope)selector_stack.peek()).s.add(en);
                    		 
                    // CSSTreeParser.g:551:10: ( selpart )*
                    loop23:
                    do {
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( (LA23_0==PSEUDO||LA23_0==ATTRIBUTE||LA23_0==INVALID_SELPART||LA23_0==CLASSKEYWORD||LA23_0==HASH) ) {
                            alt23=1;
                        }


                        switch (alt23) {
                    	case 1 :
                    	    // CSSTreeParser.g:551:10: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1198);
                    	    selpart();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop23;
                        }
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:553:7: ^( SELECTOR ( selpart )+ )
                    {
                    match(input,SELECTOR,FOLLOW_SELECTOR_in_selector1217); 

                    match(input, Token.DOWN, null); 
                    // CSSTreeParser.g:554:10: ( selpart )+
                    int cnt24=0;
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==PSEUDO||LA24_0==ATTRIBUTE||LA24_0==INVALID_SELPART||LA24_0==CLASSKEYWORD||LA24_0==HASH) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // CSSTreeParser.g:554:10: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1229);
                    	    selpart();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt24 >= 1 ) break loop24;
                                EarlyExitException eee =
                                    new EarlyExitException(24, input);
                                throw eee;
                        }
                        cnt24++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:556:7: INVALID_SELECTOR
                    {
                    match(input,INVALID_SELECTOR,FOLLOW_INVALID_SELECTOR_in_selector1247); 
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
    // CSSTreeParser.g:559:1: selpart : (h= HASH | c= CLASSKEYWORD | ^( ATTRIBUTE ea= attribute ) | p= pseudo | INVALID_SELPART );
    public final void selpart() throws RecognitionException {
        CommonTree h=null;
        CommonTree c=null;
        Selector.ElementAttribute ea = null;

        Selector.PseudoPage p = null;



        	logEnter("selpart");

        try {
            // CSSTreeParser.g:566:5: (h= HASH | c= CLASSKEYWORD | ^( ATTRIBUTE ea= attribute ) | p= pseudo | INVALID_SELPART )
            int alt26=5;
            switch ( input.LA(1) ) {
            case HASH:
                {
                alt26=1;
                }
                break;
            case CLASSKEYWORD:
                {
                alt26=2;
                }
                break;
            case ATTRIBUTE:
                {
                alt26=3;
                }
                break;
            case PSEUDO:
                {
                alt26=4;
                }
                break;
            case INVALID_SELPART:
                {
                alt26=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // CSSTreeParser.g:566:8: h= HASH
                    {
                    h=(CommonTree)match(input,HASH,FOLLOW_HASH_in_selpart1281); 
                     ((selector_scope)selector_stack.peek()).s.add(rf.createID(extractText(h))); 

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:567:7: c= CLASSKEYWORD
                    {
                    c=(CommonTree)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_selpart1293); 
                     ((selector_scope)selector_stack.peek()).s.add(rf.createClass(extractText(c))); 

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:568:4: ^( ATTRIBUTE ea= attribute )
                    {
                    match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_selpart1301); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_attribute_in_selpart1305);
                    ea=attribute();

                    state._fsp--;

                     ((selector_scope)selector_stack.peek()).s.add(ea);

                    match(input, Token.UP, null); 

                    }
                    break;
                case 4 :
                    // CSSTreeParser.g:569:7: p= pseudo
                    {
                    pushFollow(FOLLOW_pseudo_in_selpart1319);
                    p=pseudo();

                    state._fsp--;

                     ((selector_scope)selector_stack.peek()).s.add(p);

                    }
                    break;
                case 5 :
                    // CSSTreeParser.g:570:4: INVALID_SELPART
                    {
                    match(input,INVALID_SELPART,FOLLOW_INVALID_SELPART_in_selpart1326); 
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
    // CSSTreeParser.g:573:1: attribute returns [Selector.ElementAttribute elemAttr] : i= IDENT ( ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string ) )? ;
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
            // CSSTreeParser.g:591:2: (i= IDENT ( ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string ) )? )
            // CSSTreeParser.g:591:4: i= IDENT ( ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string ) )?
            {
            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_attribute1360); 
            attribute=extractText(i); 
            // CSSTreeParser.g:592:4: ( ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==INCLUDES||LA29_0==EQUALS||LA29_0==DASHMATCH) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // CSSTreeParser.g:592:5: ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string )
                    {
                    // CSSTreeParser.g:592:5: ( EQUALS | INCLUDES | DASHMATCH )
                    int alt27=3;
                    switch ( input.LA(1) ) {
                    case EQUALS:
                        {
                        alt27=1;
                        }
                        break;
                    case INCLUDES:
                        {
                        alt27=2;
                        }
                        break;
                    case DASHMATCH:
                        {
                        alt27=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 27, 0, input);

                        throw nvae;
                    }

                    switch (alt27) {
                        case 1 :
                            // CSSTreeParser.g:592:6: EQUALS
                            {
                            match(input,EQUALS,FOLLOW_EQUALS_in_attribute1369); 
                            op=Selector.Operator.EQUALS; 

                            }
                            break;
                        case 2 :
                            // CSSTreeParser.g:593:7: INCLUDES
                            {
                            match(input,INCLUDES,FOLLOW_INCLUDES_in_attribute1380); 
                            op=Selector.Operator.INCLUDES; 

                            }
                            break;
                        case 3 :
                            // CSSTreeParser.g:594:7: DASHMATCH
                            {
                            match(input,DASHMATCH,FOLLOW_DASHMATCH_in_attribute1391); 
                            op=Selector.Operator.DASHMATCH; 

                            }
                            break;

                    }

                    // CSSTreeParser.g:596:5: (i= IDENT | s= string )
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==IDENT) ) {
                        alt28=1;
                    }
                    else if ( (LA28_0==INVALID_STRING||LA28_0==STRING) ) {
                        alt28=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 28, 0, input);

                        throw nvae;
                    }
                    switch (alt28) {
                        case 1 :
                            // CSSTreeParser.g:596:6: i= IDENT
                            {
                            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_attribute1409); 

                            		value=extractText(i);
                            		isStringValue=false;
                            		

                            }
                            break;
                        case 2 :
                            // CSSTreeParser.g:600:7: s= string
                            {
                            pushFollow(FOLLOW_string_in_attribute1421);
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


    // $ANTLR start "pseudo"
    // CSSTreeParser.g:612:1: pseudo returns [Selector.PseudoPage pseudoPage] : ^( PSEUDO (f= FUNCTION )? i= IDENT ) ;
    public final Selector.PseudoPage pseudo() throws RecognitionException {
        Selector.PseudoPage pseudoPage = null;

        CommonTree f=null;
        CommonTree i=null;


        		  logEnter("pseudo");
        		  String fname =null;
        		  String value = null;

        try {
            // CSSTreeParser.g:618:2: ( ^( PSEUDO (f= FUNCTION )? i= IDENT ) )
            // CSSTreeParser.g:618:4: ^( PSEUDO (f= FUNCTION )? i= IDENT )
            {
            match(input,PSEUDO,FOLLOW_PSEUDO_in_pseudo1454); 

            match(input, Token.DOWN, null); 
            // CSSTreeParser.g:619:8: (f= FUNCTION )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==FUNCTION) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // CSSTreeParser.g:619:9: f= FUNCTION
                    {
                    f=(CommonTree)match(input,FUNCTION,FOLLOW_FUNCTION_in_pseudo1467); 
                    fname=extractText(f);

                    }
                    break;

            }

            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_pseudo1484); 
            value=extractText(i);

            match(input, Token.UP, null); 

            			pseudoPage = rf.createPseudoPage(value, fname);
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return pseudoPage;
    }
    // $ANTLR end "pseudo"


    // $ANTLR start "string"
    // CSSTreeParser.g:626:1: string returns [String s] : (st= STRING | INVALID_STRING );
    public final String string() throws RecognitionException {
        String s = null;

        CommonTree st=null;

        try {
            // CSSTreeParser.g:627:2: (st= STRING | INVALID_STRING )
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
                    // CSSTreeParser.g:627:4: st= STRING
                    {
                    st=(CommonTree)match(input,STRING,FOLLOW_STRING_in_string1509); 
                     s = extractText(st);

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:628:4: INVALID_STRING
                    {
                    match(input,INVALID_STRING,FOLLOW_INVALID_STRING_in_string1516); 
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
    // CSSTreeParser.g:631:1: any : ( IDENT | CLASSKEYWORD | NUMBER | PERCENTAGE | DIMENSION | string | URI | HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | EXCLAMATION | ^( FUNCTION ( any )* ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) );
    public final void any() throws RecognitionException {
        try {
            // CSSTreeParser.g:632:3: ( IDENT | CLASSKEYWORD | NUMBER | PERCENTAGE | DIMENSION | string | URI | HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | EXCLAMATION | ^( FUNCTION ( any )* ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) )
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
                    // CSSTreeParser.g:632:5: IDENT
                    {
                    match(input,IDENT,FOLLOW_IDENT_in_any1532); 

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:633:5: CLASSKEYWORD
                    {
                    match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_any1538); 

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:634:5: NUMBER
                    {
                    match(input,NUMBER,FOLLOW_NUMBER_in_any1544); 

                    }
                    break;
                case 4 :
                    // CSSTreeParser.g:635:5: PERCENTAGE
                    {
                    match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_any1550); 

                    }
                    break;
                case 5 :
                    // CSSTreeParser.g:636:5: DIMENSION
                    {
                    match(input,DIMENSION,FOLLOW_DIMENSION_in_any1556); 

                    }
                    break;
                case 6 :
                    // CSSTreeParser.g:637:5: string
                    {
                    pushFollow(FOLLOW_string_in_any1562);
                    string();

                    state._fsp--;


                    }
                    break;
                case 7 :
                    // CSSTreeParser.g:638:5: URI
                    {
                    match(input,URI,FOLLOW_URI_in_any1568); 

                    }
                    break;
                case 8 :
                    // CSSTreeParser.g:639:5: HASH
                    {
                    match(input,HASH,FOLLOW_HASH_in_any1574); 

                    }
                    break;
                case 9 :
                    // CSSTreeParser.g:640:5: UNIRANGE
                    {
                    match(input,UNIRANGE,FOLLOW_UNIRANGE_in_any1580); 

                    }
                    break;
                case 10 :
                    // CSSTreeParser.g:641:5: INCLUDES
                    {
                    match(input,INCLUDES,FOLLOW_INCLUDES_in_any1586); 

                    }
                    break;
                case 11 :
                    // CSSTreeParser.g:642:5: COLON
                    {
                    match(input,COLON,FOLLOW_COLON_in_any1592); 

                    }
                    break;
                case 12 :
                    // CSSTreeParser.g:643:5: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_any1598); 

                    }
                    break;
                case 13 :
                    // CSSTreeParser.g:644:5: GREATER
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_any1604); 

                    }
                    break;
                case 14 :
                    // CSSTreeParser.g:645:5: EQUALS
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_any1610); 

                    }
                    break;
                case 15 :
                    // CSSTreeParser.g:646:5: SLASH
                    {
                    match(input,SLASH,FOLLOW_SLASH_in_any1616); 

                    }
                    break;
                case 16 :
                    // CSSTreeParser.g:647:5: EXCLAMATION
                    {
                    match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_any1622); 

                    }
                    break;
                case 17 :
                    // CSSTreeParser.g:648:5: ^( FUNCTION ( any )* )
                    {
                    match(input,FUNCTION,FOLLOW_FUNCTION_in_any1629); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:648:16: ( any )*
                        loop32:
                        do {
                            int alt32=2;
                            int LA32_0 = input.LA(1);

                            if ( ((LA32_0>=PARENBLOCK && LA32_0<=BRACEBLOCK)||LA32_0==INVALID_STRING||(LA32_0>=COLON && LA32_0<=IDENT)||LA32_0==COMMA||(LA32_0>=EXCLAMATION && LA32_0<=CLASSKEYWORD)||(LA32_0>=NUMBER && LA32_0<=SLASH)||LA32_0==FUNCTION||LA32_0==DASHMATCH||LA32_0==STRING) ) {
                                alt32=1;
                            }


                            switch (alt32) {
                        	case 1 :
                        	    // CSSTreeParser.g:648:16: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_any1631);
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
                    // CSSTreeParser.g:649:5: DASHMATCH
                    {
                    match(input,DASHMATCH,FOLLOW_DASHMATCH_in_any1640); 

                    }
                    break;
                case 19 :
                    // CSSTreeParser.g:650:5: ^( PARENBLOCK ( any )* )
                    {
                    match(input,PARENBLOCK,FOLLOW_PARENBLOCK_in_any1647); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:650:18: ( any )*
                        loop33:
                        do {
                            int alt33=2;
                            int LA33_0 = input.LA(1);

                            if ( ((LA33_0>=PARENBLOCK && LA33_0<=BRACEBLOCK)||LA33_0==INVALID_STRING||(LA33_0>=COLON && LA33_0<=IDENT)||LA33_0==COMMA||(LA33_0>=EXCLAMATION && LA33_0<=CLASSKEYWORD)||(LA33_0>=NUMBER && LA33_0<=SLASH)||LA33_0==FUNCTION||LA33_0==DASHMATCH||LA33_0==STRING) ) {
                                alt33=1;
                            }


                            switch (alt33) {
                        	case 1 :
                        	    // CSSTreeParser.g:650:18: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_any1649);
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
                    // CSSTreeParser.g:651:5: ^( BRACEBLOCK ( any )* )
                    {
                    match(input,BRACEBLOCK,FOLLOW_BRACEBLOCK_in_any1658); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:651:18: ( any )*
                        loop34:
                        do {
                            int alt34=2;
                            int LA34_0 = input.LA(1);

                            if ( ((LA34_0>=PARENBLOCK && LA34_0<=BRACEBLOCK)||LA34_0==INVALID_STRING||(LA34_0>=COLON && LA34_0<=IDENT)||LA34_0==COMMA||(LA34_0>=EXCLAMATION && LA34_0<=CLASSKEYWORD)||(LA34_0>=NUMBER && LA34_0<=SLASH)||LA34_0==FUNCTION||LA34_0==DASHMATCH||LA34_0==STRING) ) {
                                alt34=1;
                            }


                            switch (alt34) {
                        	case 1 :
                        	    // CSSTreeParser.g:651:18: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_any1660);
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


    protected DFA19 dfa19 = new DFA19(this);
    static final String DFA19_eotS =
        "\27\uffff";
    static final String DFA19_eofS =
        "\27\uffff";
    static final String DFA19_minS =
        "\1\10\2\uffff\1\55\23\uffff";
    static final String DFA19_maxS =
        "\1\77\2\uffff\1\57\23\uffff";
    static final String DFA19_acceptS =
        "\1\uffff\1\1\1\2\1\uffff\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13"+
        "\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25";
    static final String DFA19_specialS =
        "\27\uffff}>";
    static final String[] DFA19_transitionS = {
            "\1\25\1\26\14\uffff\1\7\13\uffff\1\14\1\1\4\uffff\1\15\2\uffff"+
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

    static final short[] DFA19_eot = DFA.unpackEncodedString(DFA19_eotS);
    static final short[] DFA19_eof = DFA.unpackEncodedString(DFA19_eofS);
    static final char[] DFA19_min = DFA.unpackEncodedStringToUnsignedChars(DFA19_minS);
    static final char[] DFA19_max = DFA.unpackEncodedStringToUnsignedChars(DFA19_maxS);
    static final short[] DFA19_accept = DFA.unpackEncodedString(DFA19_acceptS);
    static final short[] DFA19_special = DFA.unpackEncodedString(DFA19_specialS);
    static final short[][] DFA19_transition;

    static {
        int numStates = DFA19_transitionS.length;
        DFA19_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA19_transition[i] = DFA.unpackEncodedString(DFA19_transitionS[i]);
        }
    }

    class DFA19 extends DFA {

        public DFA19(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 19;
            this.eot = DFA19_eot;
            this.eof = DFA19_eof;
            this.min = DFA19_min;
            this.max = DFA19_max;
            this.accept = DFA19_accept;
            this.special = DFA19_special;
            this.transition = DFA19_transition;
        }
        public String getDescription() {
            return "417:1: valuepart : (i= IDENT | CLASSKEYWORD | ( MINUS )? n= NUMBER | ( MINUS )? p= PERCENTAGE | ( MINUS )? d= DIMENSION | s= string | u= URI | h= HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | PLUS | ASTERISK | ^(f= FUNCTION t= terms ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) );";
        }
    }
 

    public static final BitSet FOLLOW_STYLESHEET_in_stylesheet60 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_stylesheet69 = new BitSet(new long[]{0x000000438C200408L});
    public static final BitSet FOLLOW_ruleset_in_statement118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atstatement_in_statement128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARSET_in_atstatement161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_IMPORT_in_atstatement167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_atstatement175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_END_in_atstatement186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PAGE_in_atstatement194 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_atstatement199 = new BitSet(new long[]{0x0000000002040008L});
    public static final BitSet FOLLOW_declarations_in_atstatement206 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MEDIA_in_atstatement217 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_media_in_atstatement222 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_ruleset_in_atstatement233 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_INVALID_STATEMENT_in_atstatement255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_media287 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_RULE_in_ruleset333 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_combined_selector_in_ruleset347 = new BitSet(new long[]{0x0000000002840808L});
    public static final BitSet FOLLOW_declarations_in_ruleset368 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_declaration_in_declarations411 = new BitSet(new long[]{0x0000000002040002L});
    public static final BitSet FOLLOW_DECLARATION_in_declaration455 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_important_in_declaration462 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_property_in_declaration474 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_terms_in_declaration485 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INVALID_DECLARATION_in_declaration506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORTANT_in_important523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_property563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_terms608 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_term_in_terms610 = new BitSet(new long[]{0x8BFFF98C00400388L});
    public static final BitSet FOLLOW_valuepart_in_term633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CURLYBLOCK_in_term650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATKEYWORD_in_term660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_valuepart688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_valuepart700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart708 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_NUMBER_in_valuepart716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart730 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_PERCENTAGE_in_valuepart738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart750 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DIMENSION_in_valuepart758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_valuepart774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_URI_in_valuepart791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_valuepart809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_valuepart827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCLUDES_in_valuepart838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_valuepart849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_valuepart863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_valuepart881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_valuepart893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_valuepart906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_valuepart917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASTERISK_in_valuepart925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_valuepart939 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_terms_in_valuepart943 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DASHMATCH_in_valuepart954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENBLOCK_in_valuepart965 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_valuepart967 = new BitSet(new long[]{0x8A7FED0C00400308L});
    public static final BitSet FOLLOW_BRACEBLOCK_in_valuepart980 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_valuepart982 = new BitSet(new long[]{0x8A7FED0C00400308L});
    public static final BitSet FOLLOW_selector_in_combined_selector1030 = new BitSet(new long[]{0x000000000001C002L});
    public static final BitSet FOLLOW_combinator_in_combined_selector1039 = new BitSet(new long[]{0x0000000002840808L});
    public static final BitSet FOLLOW_selector_in_combined_selector1043 = new BitSet(new long[]{0x000000000001C002L});
    public static final BitSet FOLLOW_CHILD_in_combinator1073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADJACENT_in_combinator1080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DESCENDANT_in_combinator1087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELECTOR_in_selector1123 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ELEMENT_in_selector1135 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_selector1151 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_selpart_in_selector1198 = new BitSet(new long[]{0x0002080001022008L});
    public static final BitSet FOLLOW_SELECTOR_in_selector1217 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_selpart_in_selector1229 = new BitSet(new long[]{0x0002080001022008L});
    public static final BitSet FOLLOW_INVALID_SELECTOR_in_selector1247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_selpart1281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_selpart1293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_selpart1301 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_attribute_in_selpart1305 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_pseudo_in_selpart1319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_SELPART_in_selpart1326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_attribute1360 = new BitSet(new long[]{0x0828000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_attribute1369 = new BitSet(new long[]{0x8000000800400000L});
    public static final BitSet FOLLOW_INCLUDES_in_attribute1380 = new BitSet(new long[]{0x8000000800400000L});
    public static final BitSet FOLLOW_DASHMATCH_in_attribute1391 = new BitSet(new long[]{0x8000000800400000L});
    public static final BitSet FOLLOW_IDENT_in_attribute1409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_attribute1421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PSEUDO_in_pseudo1454 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_FUNCTION_in_pseudo1467 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_IDENT_in_pseudo1484 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STRING_in_string1509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_STRING_in_string1516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_any1532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_any1538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_any1544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENTAGE_in_any1550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_any1556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_any1562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_URI_in_any1568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_any1574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_any1580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCLUDES_in_any1586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_any1592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_any1598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_any1604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_any1610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_any1616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_any1622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_any1629 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_any1631 = new BitSet(new long[]{0x8A7FED0C00400308L});
    public static final BitSet FOLLOW_DASHMATCH_in_any1640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENBLOCK_in_any1647 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_any1649 = new BitSet(new long[]{0x8A7FED0C00400308L});
    public static final BitSet FOLLOW_BRACEBLOCK_in_any1658 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_any1660 = new BitSet(new long[]{0x8A7FED0C00400308L});

}