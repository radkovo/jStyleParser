// $ANTLR 3.1 CSSTreeParser.g 2008-09-21 13:33:13

package cz.vutbr.web.csskit.antlr;

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
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.RuleBlock.Priority;
import cz.vutbr.web.csskit.PriorityStrategy;



import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class CSSTreeParser extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "STYLESHEET", "INLINESTYLE", "ATBLOCK", "CURLYBLOCK", "PARENBLOCK", "BRACEBLOCK", "RULE", "SELECTOR", "ELEMENT", "PSEUDO", "ADJACENT", "CHILD", "DESCENDANT", "ATTRIBUTE", "SET", "DECLARATION", "VALUE", "IMPORTANT", "IMPORT_END", "INVALID_STRING", "INVALID_SELECTOR", "INVALID_SELPART", "INVALID_DECLARATION", "INVALID_STATEMENT", "INVALID_IMPORT", "S", "CDO", "CDC", "CHARSET", "IMPORT", "PAGE", "COLON", "IDENT", "LCURLY", "RCURLY", "MEDIA", "ATKEYWORD", "COMMA", "SEMICOLON", "EXCLAMATION", "CLASSKEYWORD", "MINUS", "NUMBER", "PERCENTAGE", "DIMENSION", "URI", "HASH", "UNIRANGE", "INCLUDES", "GREATER", "EQUALS", "SLASH", "PLUS", "ASTERISK", "FUNCTION", "RPAREN", "DASHMATCH", "LPAREN", "LBRACE", "RBRACE", "STRING", "IDENT_MACR", "STRING_MACR", "NAME_MACR", "NUMBER_MACR", "W_MACR", "URI_MACR", "APOS", "QUOT", "W_CHAR", "COMMENT", "SL_COMMENT", "INVALID_TOKEN", "NAME_START", "NAME_CHAR", "NON_ASCII", "ESCAPE_CHAR", "STRING_CHAR", "URI_CHAR", "NL_CHAR", "'important'"
    };
    public static final int COMMA=41;
    public static final int ELEMENT=12;
    public static final int INVALID_IMPORT=28;
    public static final int CHARSET=32;
    public static final int MINUS=45;
    public static final int BRACEBLOCK=9;
    public static final int PARENBLOCK=8;
    public static final int HASH=50;
    public static final int DASHMATCH=60;
    public static final int IMPORT_END=22;
    public static final int SELECTOR=11;
    public static final int NUMBER=46;
    public static final int PAGE=34;
    public static final int INVALID_TOKEN=76;
    public static final int URI_CHAR=82;
    public static final int NAME_MACR=67;
    public static final int RULE=10;
    public static final int CLASSKEYWORD=44;
    public static final int INCLUDES=52;
    public static final int MEDIA=39;
    public static final int DESCENDANT=16;
    public static final int VALUE=20;
    public static final int PSEUDO=13;
    public static final int STYLESHEET=4;
    public static final int URI=49;
    public static final int IMPORT=33;
    public static final int LBRACE=62;
    public static final int RBRACE=63;
    public static final int INVALID_SELECTOR=24;
    public static final int S=29;
    public static final int RCURLY=38;
    public static final int DECLARATION=19;
    public static final int LCURLY=37;
    public static final int ESCAPE_CHAR=80;
    public static final int FUNCTION=58;
    public static final int W_MACR=69;
    public static final int RPAREN=59;
    public static final int IDENT_MACR=65;
    public static final int NAME_CHAR=78;
    public static final int NAME_START=77;
    public static final int GREATER=53;
    public static final int LPAREN=61;
    public static final int W_CHAR=73;
    public static final int PLUS=56;
    public static final int NON_ASCII=79;
    public static final int SL_COMMENT=75;
    public static final int APOS=71;
    public static final int ATKEYWORD=40;
    public static final int STRING_CHAR=81;
    public static final int URI_MACR=70;
    public static final int IDENT=36;
    public static final int SLASH=55;
    public static final int UNIRANGE=51;
    public static final int IMPORTANT=21;
    public static final int EXCLAMATION=43;
    public static final int STRING=64;
    public static final int NL_CHAR=83;
    public static final int INVALID_STRING=23;
    public static final int COMMENT=74;
    public static final int CDC=31;
    public static final int ADJACENT=14;
    public static final int INVALID_DECLARATION=26;
    public static final int NUMBER_MACR=68;
    public static final int T__84=84;
    public static final int EQUALS=54;
    public static final int CURLYBLOCK=7;
    public static final int DIMENSION=48;
    public static final int INLINESTYLE=5;
    public static final int INVALID_STATEMENT=27;
    public static final int SEMICOLON=42;
    public static final int STRING_MACR=66;
    public static final int EOF=-1;
    public static final int ASTERISK=57;
    public static final int COLON=35;
    public static final int CDO=30;
    public static final int SET=18;
    public static final int ATTRIBUTE=17;
    public static final int CHILD=15;
    public static final int INVALID_SELPART=25;
    public static final int PERCENTAGE=47;
    public static final int QUOT=72;
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
    	private PriorityStrategy ps;
    	
    	private StyleSheet stylesheet;

    	private Stack<TreeParserState> imports;	
           
        public CSSTreeParser init(StyleSheet sheet, PriorityStrategy ps) {
    	    this.stylesheet = sheet;
    		this.ps = ps;
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



    // $ANTLR start "inlinestyle"
    // CSSTreeParser.g:98:1: inlinestyle returns [StyleSheet sheet] : ( ^( INLINESTYLE declarations ) | ^( INLINESTYLE ( inlineset )+ ) );
    public final StyleSheet inlinestyle() throws RecognitionException {
        StyleSheet sheet = null;


        	logEnter("inlinestyle");
        	sheet = this.stylesheet;

        try {
            // CSSTreeParser.g:109:2: ( ^( INLINESTYLE declarations ) | ^( INLINESTYLE ( inlineset )+ ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==INLINESTYLE) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==DOWN) ) {
                    int LA2_2 = input.LA(3);

                    if ( (LA2_2==SET) ) {
                        alt2=1;
                    }
                    else if ( (LA2_2==RULE) ) {
                        alt2=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // CSSTreeParser.g:109:5: ^( INLINESTYLE declarations )
                    {
                    match(input,INLINESTYLE,FOLLOW_INLINESTYLE_in_inlinestyle59); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_declarations_in_inlinestyle61);
                    declarations();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:110:6: ^( INLINESTYLE ( inlineset )+ )
                    {
                    match(input,INLINESTYLE,FOLLOW_INLINESTYLE_in_inlinestyle71); 

                    match(input, Token.DOWN, null); 
                    // CSSTreeParser.g:110:20: ( inlineset )+
                    int cnt1=0;
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==RULE) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // CSSTreeParser.g:110:20: inlineset
                    	    {
                    	    pushFollow(FOLLOW_inlineset_in_inlinestyle73);
                    	    inlineset();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt1 >= 1 ) break loop1;
                                EarlyExitException eee =
                                    new EarlyExitException(1, input);
                                throw eee;
                        }
                        cnt1++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;

            }

            	log.debug("\n***\n{}\n***\n", sheet);	   
            	// mark last usage
            	sheet.markLast(ps.getAndIncrement());
            	logLeave("inlinestyle");

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return sheet;
    }
    // $ANTLR end "inlinestyle"


    // $ANTLR start "stylesheet"
    // CSSTreeParser.g:114:1: stylesheet returns [StyleSheet sheet] : ^( STYLESHEET (s= statement )* ) ;
    public final StyleSheet stylesheet() throws RecognitionException {
        StyleSheet sheet = null;

        RuleBlock<?> s = null;



        	logEnter("stylesheet");
        	sheet = this.stylesheet;

        try {
            // CSSTreeParser.g:128:2: ( ^( STYLESHEET (s= statement )* ) )
            // CSSTreeParser.g:128:4: ^( STYLESHEET (s= statement )* )
            {
            match(input,STYLESHEET,FOLLOW_STYLESHEET_in_stylesheet105); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // CSSTreeParser.g:129:4: (s= statement )*
                loop3:
                do {
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==RULE||LA3_0==IMPORT_END||(LA3_0>=INVALID_STATEMENT && LA3_0<=INVALID_IMPORT)||(LA3_0>=CHARSET && LA3_0<=PAGE)||LA3_0==MEDIA) ) {
                        alt3=1;
                    }


                    switch (alt3) {
                	case 1 :
                	    // CSSTreeParser.g:129:5: s= statement
                	    {
                	    pushFollow(FOLLOW_statement_in_stylesheet114);
                	    s=statement();

                	    state._fsp--;

                	     if(s!=null) sheet.add(s);

                	    }
                	    break;

                	default :
                	    break loop3;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }


            	log.debug("\n***\n{}\n***\n", sheet);
            	// mark last usage
            	sheet.markLast(ps.getAndIncrement());
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
    // CSSTreeParser.g:133:1: statement returns [RuleBlock<?> stm] : (rs= ruleset | ats= atstatement );
    public final RuleBlock<?> statement() throws RecognitionException {
        statement_stack.push(new statement_scope());
        RuleBlock<?> stm = null;

        RuleBlock<?> rs = null;

        RuleBlock<?> ats = null;



        	logEnter("statement");
        	((statement_scope)statement_stack.peek()).invalid = false;

        try {
            // CSSTreeParser.g:152:2: (rs= ruleset | ats= atstatement )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE) ) {
                alt4=1;
            }
            else if ( (LA4_0==IMPORT_END||(LA4_0>=INVALID_STATEMENT && LA4_0<=INVALID_IMPORT)||(LA4_0>=CHARSET && LA4_0<=PAGE)||LA4_0==MEDIA) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // CSSTreeParser.g:152:4: rs= ruleset
                    {
                    pushFollow(FOLLOW_ruleset_in_statement163);
                    rs=ruleset();

                    state._fsp--;

                    stm =(RuleBlock<?>) rs;

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:153:4: ats= atstatement
                    {
                    pushFollow(FOLLOW_atstatement_in_statement173);
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
    // CSSTreeParser.g:157:1: atstatement returns [RuleBlock<?> stmnt] : ( CHARSET | INVALID_IMPORT | i= IMPORT | IMPORT_END | ^( PAGE (i= IDENT )? decl= declarations ) | ^( MEDIA (mediaList= media )? (rs= ruleset )* ) | INVALID_STATEMENT );
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
        	Priority mark = ps.markAndIncrement();

        try {
            // CSSTreeParser.g:173:2: ( CHARSET | INVALID_IMPORT | i= IMPORT | IMPORT_END | ^( PAGE (i= IDENT )? decl= declarations ) | ^( MEDIA (mediaList= media )? (rs= ruleset )* ) | INVALID_STATEMENT )
            int alt8=7;
            switch ( input.LA(1) ) {
            case CHARSET:
                {
                alt8=1;
                }
                break;
            case INVALID_IMPORT:
                {
                alt8=2;
                }
                break;
            case IMPORT:
                {
                alt8=3;
                }
                break;
            case IMPORT_END:
                {
                alt8=4;
                }
                break;
            case PAGE:
                {
                alt8=5;
                }
                break;
            case MEDIA:
                {
                alt8=6;
                }
                break;
            case INVALID_STATEMENT:
                {
                alt8=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // CSSTreeParser.g:173:4: CHARSET
                    {
                    match(input,CHARSET,FOLLOW_CHARSET_in_atstatement206); 

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:174:4: INVALID_IMPORT
                    {
                    match(input,INVALID_IMPORT,FOLLOW_INVALID_IMPORT_in_atstatement212); 

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:175:4: i= IMPORT
                    {
                    i=(CommonTree)match(input,IMPORT,FOLLOW_IMPORT_in_atstatement220); 

                    	    String media = extractText(i);
                    		imports.push(new TreeParserState(media));
                    		
                    		log.info("From imported file: Rules will use these media: {}", 
                    			imports.peek());
                    	  

                    }
                    break;
                case 4 :
                    // CSSTreeParser.g:183:4: IMPORT_END
                    {
                    match(input,IMPORT_END,FOLLOW_IMPORT_END_in_atstatement231); 

                    	    imports.pop();
                    		log.info("Imported file was parsed, returing in nesting.");
                    	  

                    }
                    break;
                case 5 :
                    // CSSTreeParser.g:187:4: ^( PAGE (i= IDENT )? decl= declarations )
                    {
                    match(input,PAGE,FOLLOW_PAGE_in_atstatement239); 

                    match(input, Token.DOWN, null); 
                    // CSSTreeParser.g:187:11: (i= IDENT )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==IDENT) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // CSSTreeParser.g:187:12: i= IDENT
                            {
                            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_atstatement244); 
                             pseudo=extractText(i);

                            }
                            break;

                    }

                    pushFollow(FOLLOW_declarations_in_atstatement251);
                    decl=declarations();

                    state._fsp--;


                    		   	if(decl!=null && !decl.isEmpty()) {
                    				Priority prio = ps.getAndIncrement();							  
                                	RulePage rp = rf.createPage(prio);
                                    rp.replaceAll(declarations);
                                    rp.setPseudo(pseudo);
                                    stmnt = rp;
                                    log.info("Create @page as {}th with:\n{}",  prio, rp);
                                }
                    		

                    match(input, Token.UP, null); 

                    }
                    break;
                case 6 :
                    // CSSTreeParser.g:198:4: ^( MEDIA (mediaList= media )? (rs= ruleset )* )
                    {
                    match(input,MEDIA,FOLLOW_MEDIA_in_atstatement262); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:198:12: (mediaList= media )?
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==IDENT) ) {
                            alt6=1;
                        }
                        switch (alt6) {
                            case 1 :
                                // CSSTreeParser.g:198:13: mediaList= media
                                {
                                pushFollow(FOLLOW_media_in_atstatement267);
                                mediaList=media();

                                state._fsp--;


                                }
                                break;

                        }

                        // CSSTreeParser.g:199:4: (rs= ruleset )*
                        loop7:
                        do {
                            int alt7=2;
                            int LA7_0 = input.LA(1);

                            if ( (LA7_0==RULE) ) {
                                alt7=1;
                            }


                            switch (alt7) {
                        	case 1 :
                        	    // CSSTreeParser.g:199:5: rs= ruleset
                        	    {
                        	    pushFollow(FOLLOW_ruleset_in_atstatement278);
                        	    rs=ruleset();

                        	    state._fsp--;


                        	    			   if(rules==null) rules = new ArrayList<RuleSet>();				
                        	    			   if(rs!=null) {
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
                        	    break loop7;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }

                    		   if(rules!=null && !rules.isEmpty()) {
                    			  // create at the beginning, increment to match positions								   
                                  RuleMedia rm = rf.createMedia(mark);
                    			  
                    			  rm.replaceAll(rules);
                    			  if(mediaList!=null && !mediaList.isEmpty()) 
                    			  	  rm.setMedia(mediaList);
                    				
                    			  stmnt = rm;
                                  log.info("Create @media as {}th with:\n{}", 
                                    	mark, rm);
                    			  
                    		   }
                    	   

                    }
                    break;
                case 7 :
                    // CSSTreeParser.g:227:4: INVALID_STATEMENT
                    {
                    match(input,INVALID_STATEMENT,FOLLOW_INVALID_STATEMENT_in_atstatement300); 
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
    // CSSTreeParser.g:230:1: media returns [List<String> affected] : (i= IDENT )+ ;
    public final List<String> media() throws RecognitionException {
        List<String> affected = null;

        CommonTree i=null;


           logEnter("media");
           affected = new ArrayList<String>();

        try {
            // CSSTreeParser.g:239:2: ( (i= IDENT )+ )
            // CSSTreeParser.g:239:4: (i= IDENT )+
            {
            // CSSTreeParser.g:239:4: (i= IDENT )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==IDENT) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // CSSTreeParser.g:239:5: i= IDENT
            	    {
            	    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_media332); 

            	    				   String m = extractText(i);
            	    				   if(css.isSupportedMedia(m)) affected.add(m);
            	        

            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
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


    // $ANTLR start "inlineset"
    // CSSTreeParser.g:245:1: inlineset : ^( RULE ( pseudo )* declarations ) ;
    public final void inlineset() throws RecognitionException {
        try {
            // CSSTreeParser.g:246:2: ( ^( RULE ( pseudo )* declarations ) )
            // CSSTreeParser.g:246:4: ^( RULE ( pseudo )* declarations )
            {
            match(input,RULE,FOLLOW_RULE_in_inlineset353); 

            match(input, Token.DOWN, null); 
            // CSSTreeParser.g:246:11: ( pseudo )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==PSEUDO) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // CSSTreeParser.g:246:11: pseudo
            	    {
            	    pushFollow(FOLLOW_pseudo_in_inlineset355);
            	    pseudo();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            pushFollow(FOLLOW_declarations_in_inlineset358);
            declarations();

            state._fsp--;


            match(input, Token.UP, null); 

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
    // $ANTLR end "inlineset"


    // $ANTLR start "ruleset"
    // CSSTreeParser.g:250:1: ruleset returns [RuleBlock<?> stmnt] : ^( RULE (cs= combined_selector )* decl= declarations ) ;
    public final RuleBlock<?> ruleset() throws RecognitionException {
        RuleBlock<?> stmnt = null;

        CombinedSelector cs = null;

        List<Declaration> decl = null;



            logEnter("ruleset"); 
            List<CombinedSelector> cslist = new ArrayList<CombinedSelector>();

        try {
            // CSSTreeParser.g:297:5: ( ^( RULE (cs= combined_selector )* decl= declarations ) )
            // CSSTreeParser.g:297:7: ^( RULE (cs= combined_selector )* decl= declarations )
            {
            match(input,RULE,FOLLOW_RULE_in_ruleset405); 

            match(input, Token.DOWN, null); 
            // CSSTreeParser.g:298:9: (cs= combined_selector )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==SELECTOR||LA11_0==INVALID_SELECTOR) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // CSSTreeParser.g:298:10: cs= combined_selector
            	    {
            	    pushFollow(FOLLOW_combined_selector_in_ruleset419);
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
            	    break loop11;
                }
            } while (true);

            pushFollow(FOLLOW_declarations_in_ruleset440);
            decl=declarations();

            state._fsp--;


            match(input, Token.UP, null); 

            }


                if(((statement_scope)statement_stack.peek()).invalid || cslist.isEmpty() || decl.isEmpty()) {
                    stmnt = null;
                    log.debug("Ruleset not valid, so not created");
                }
                else {    
            		Priority prio = ps.getAndIncrement(); 
                    RuleSet rs = rf.createSet(prio);
                    rs.setSelectors(cslist);
                    rs.replaceAll(decl);
            		log.info("Create ruleset as {}th with:\n{}", prio, rs);
            		
            		// check statement
            		if(!((statement_scope)statement_stack.peek()).insideAtstatement && !imports.isEmpty() 
            			&& imports.peek().doWrap()) {
            			
            			// swap numbers, so RuleMedia is created before RuleSet
            			prio = rs.getPriority();			
            			rs.setPriority(ps.getAndIncrement());
            			RuleMedia rm = rf.createMedia(prio);
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
    // CSSTreeParser.g:309:1: declarations returns [List<Declaration> decl] : ^( SET (d= declaration )* ) ;
    public final List<Declaration> declarations() throws RecognitionException {
        List<Declaration> decl = null;

        Declaration d = null;



        		  logEnter("declarations");
        		  decl = new ArrayList<Declaration>();

        try {
            // CSSTreeParser.g:320:2: ( ^( SET (d= declaration )* ) )
            // CSSTreeParser.g:320:4: ^( SET (d= declaration )* )
            {
            match(input,SET,FOLLOW_SET_in_declarations481); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // CSSTreeParser.g:320:10: (d= declaration )*
                loop12:
                do {
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==DECLARATION||LA12_0==INVALID_DECLARATION) ) {
                        alt12=1;
                    }


                    switch (alt12) {
                	case 1 :
                	    // CSSTreeParser.g:320:11: d= declaration
                	    {
                	    pushFollow(FOLLOW_declaration_in_declarations486);
                	    d=declaration();

                	    state._fsp--;


                	    	     if(d!=null) {
                	                decl.add(d);
                	                log.debug("Inserted declaration #{} ", decl.size()+1);
                	    		 }	
                	    	 

                	    }
                	    break;

                	default :
                	    break loop12;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

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
    // CSSTreeParser.g:330:1: declaration returns [Declaration decl] : ( ^( DECLARATION ( important )? property t= terms ) | INVALID_DECLARATION );
    public final Declaration declaration() throws RecognitionException {
        declaration_stack.push(new declaration_scope());
        Declaration decl = null;

        List<Term<?>> t = null;



            logEnter("declaration");
            ((declaration_scope)declaration_stack.peek()).d = decl = rf.createDeclaration();
            ((declaration_scope)declaration_stack.peek()).invalid = false;

        try {
            // CSSTreeParser.g:353:3: ( ^( DECLARATION ( important )? property t= terms ) | INVALID_DECLARATION )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==DECLARATION) ) {
                alt14=1;
            }
            else if ( (LA14_0==INVALID_DECLARATION) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // CSSTreeParser.g:353:5: ^( DECLARATION ( important )? property t= terms )
                    {
                    match(input,DECLARATION,FOLLOW_DECLARATION_in_declaration530); 

                    match(input, Token.DOWN, null); 
                    // CSSTreeParser.g:354:4: ( important )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==IMPORTANT) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // CSSTreeParser.g:354:5: important
                            {
                            pushFollow(FOLLOW_important_in_declaration537);
                            important();

                            state._fsp--;

                             decl.setImportant(true);

                            }
                            break;

                    }

                    pushFollow(FOLLOW_property_in_declaration549);
                    property();

                    state._fsp--;

                    pushFollow(FOLLOW_terms_in_declaration560);
                    t=terms();

                    state._fsp--;

                    decl.replaceAll(t);

                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:358:5: INVALID_DECLARATION
                    {
                    match(input,INVALID_DECLARATION,FOLLOW_INVALID_DECLARATION_in_declaration581); 
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
    // CSSTreeParser.g:361:1: important : IMPORTANT ;
    public final void important() throws RecognitionException {
        try {
            // CSSTreeParser.g:362:5: ( IMPORTANT )
            // CSSTreeParser.g:362:7: IMPORTANT
            {
            match(input,IMPORTANT,FOLLOW_IMPORTANT_in_important598); 

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
    // CSSTreeParser.g:365:1: property : i= IDENT ;
    public final void property() throws RecognitionException {
        CommonTree i=null;


            logEnter("property");

        try {
            // CSSTreeParser.g:376:3: (i= IDENT )
            // CSSTreeParser.g:376:5: i= IDENT
            {
            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_property638); 
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
    // CSSTreeParser.g:379:1: terms returns [List<Term<?>> tlist] : ^( VALUE ( term )+ ) ;
    public final List<Term<?>> terms() throws RecognitionException {
        terms_stack.push(new terms_scope());
        List<Term<?>> tlist = null;


            logEnter("terms");
            ((terms_scope)terms_stack.peek()).list = tlist = new ArrayList<Term<?>>();
            ((terms_scope)terms_stack.peek()).term = null;
            ((terms_scope)terms_stack.peek()).op = null;
            ((terms_scope)terms_stack.peek()).unary = 1;

        try {
            // CSSTreeParser.g:400:5: ( ^( VALUE ( term )+ ) )
            // CSSTreeParser.g:400:7: ^( VALUE ( term )+ )
            {
            match(input,VALUE,FOLLOW_VALUE_in_terms683); 

            match(input, Token.DOWN, null); 
            // CSSTreeParser.g:400:15: ( term )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>=CURLYBLOCK && LA15_0<=BRACEBLOCK)||LA15_0==INVALID_STRING||(LA15_0>=COLON && LA15_0<=IDENT)||(LA15_0>=ATKEYWORD && LA15_0<=COMMA)||(LA15_0>=CLASSKEYWORD && LA15_0<=FUNCTION)||LA15_0==DASHMATCH||LA15_0==STRING) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // CSSTreeParser.g:400:15: term
            	    {
            	    pushFollow(FOLLOW_term_in_terms685);
            	    term();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
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
    // CSSTreeParser.g:403:1: term : ( valuepart | CURLYBLOCK | ATKEYWORD );
    public final void term() throws RecognitionException {
        try {
            // CSSTreeParser.g:404:5: ( valuepart | CURLYBLOCK | ATKEYWORD )
            int alt16=3;
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
                alt16=1;
                }
                break;
            case CURLYBLOCK:
                {
                alt16=2;
                }
                break;
            case ATKEYWORD:
                {
                alt16=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // CSSTreeParser.g:404:7: valuepart
                    {
                    pushFollow(FOLLOW_valuepart_in_term708);
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
                    // CSSTreeParser.g:415:7: CURLYBLOCK
                    {
                    match(input,CURLYBLOCK,FOLLOW_CURLYBLOCK_in_term725); 
                     ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:416:7: ATKEYWORD
                    {
                    match(input,ATKEYWORD,FOLLOW_ATKEYWORD_in_term735); 
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
    // CSSTreeParser.g:419:1: valuepart : (i= IDENT | CLASSKEYWORD | ( MINUS )? n= NUMBER | ( MINUS )? p= PERCENTAGE | ( MINUS )? d= DIMENSION | s= string | u= URI | h= HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | PLUS | ASTERISK | ^(f= FUNCTION t= terms ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) );
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
            // CSSTreeParser.g:441:5: (i= IDENT | CLASSKEYWORD | ( MINUS )? n= NUMBER | ( MINUS )? p= PERCENTAGE | ( MINUS )? d= DIMENSION | s= string | u= URI | h= HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | PLUS | ASTERISK | ^(f= FUNCTION t= terms ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) )
            int alt22=21;
            alt22 = dfa22.predict(input);
            switch (alt22) {
                case 1 :
                    // CSSTreeParser.g:441:7: i= IDENT
                    {
                    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_valuepart763); 
                    ((terms_scope)terms_stack.peek()).term = tf.createIdent(extractText(i));

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:442:7: CLASSKEYWORD
                    {
                    match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_valuepart775); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:443:4: ( MINUS )? n= NUMBER
                    {
                    // CSSTreeParser.g:443:4: ( MINUS )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==MINUS) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // CSSTreeParser.g:443:5: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart783); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    n=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_valuepart791); 
                    ((terms_scope)terms_stack.peek()).term = tf.createNumeric(extractText(n), ((terms_scope)terms_stack.peek()).unary);

                    }
                    break;
                case 4 :
                    // CSSTreeParser.g:444:7: ( MINUS )? p= PERCENTAGE
                    {
                    // CSSTreeParser.g:444:7: ( MINUS )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==MINUS) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // CSSTreeParser.g:444:8: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart805); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    p=(CommonTree)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_valuepart813); 
                     ((terms_scope)terms_stack.peek()).term = tf.createPercent(extractText(p), ((terms_scope)terms_stack.peek()).unary);

                    }
                    break;
                case 5 :
                    // CSSTreeParser.g:445:7: ( MINUS )? d= DIMENSION
                    {
                    // CSSTreeParser.g:445:7: ( MINUS )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==MINUS) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // CSSTreeParser.g:445:8: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart825); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    d=(CommonTree)match(input,DIMENSION,FOLLOW_DIMENSION_in_valuepart833); 
                    String dim = extractText(d);
                    	 ((terms_scope)terms_stack.peek()).term = tf.createDimension(dim, ((terms_scope)terms_stack.peek()).unary);
                         if(((terms_scope)terms_stack.peek()).term==null) {
                    		 log.info("Unable to create dimension from {}, unary {}", dim, ((terms_scope)terms_stack.peek()).unary);
                             ((declaration_scope)declaration_stack.peek()).invalid = true;
                    	 }
                        

                    }
                    break;
                case 6 :
                    // CSSTreeParser.g:453:7: s= string
                    {
                    pushFollow(FOLLOW_string_in_valuepart849);
                    s=string();

                    state._fsp--;

                     if(s!=null) ((terms_scope)terms_stack.peek()).term = tf.createString(s);
                    	  else ((declaration_scope)declaration_stack.peek()).invalid =true;
                    	

                    }
                    break;
                case 7 :
                    // CSSTreeParser.g:457:7: u= URI
                    {
                    u=(CommonTree)match(input,URI,FOLLOW_URI_in_valuepart866); 
                    ((terms_scope)terms_stack.peek()).term = tf.createURI(extractText(u));

                    }
                    break;
                case 8 :
                    // CSSTreeParser.g:458:7: h= HASH
                    {
                    h=(CommonTree)match(input,HASH,FOLLOW_HASH_in_valuepart884); 
                    ((terms_scope)terms_stack.peek()).term = tf.createColor(extractText(h));
                         if(((terms_scope)terms_stack.peek()).term==null)
                             ((declaration_scope)declaration_stack.peek()).invalid = true;
                        

                    }
                    break;
                case 9 :
                    // CSSTreeParser.g:463:7: UNIRANGE
                    {
                    match(input,UNIRANGE,FOLLOW_UNIRANGE_in_valuepart902); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 10 :
                    // CSSTreeParser.g:464:7: INCLUDES
                    {
                    match(input,INCLUDES,FOLLOW_INCLUDES_in_valuepart913); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 11 :
                    // CSSTreeParser.g:465:7: COLON
                    {
                    match(input,COLON,FOLLOW_COLON_in_valuepart924); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 12 :
                    // CSSTreeParser.g:466:7: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_valuepart938); 
                    ((terms_scope)terms_stack.peek()).op = Term.Operator.COMMA;

                    }
                    break;
                case 13 :
                    // CSSTreeParser.g:467:7: GREATER
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_valuepart956); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 14 :
                    // CSSTreeParser.g:468:7: EQUALS
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_valuepart968); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 15 :
                    // CSSTreeParser.g:469:7: SLASH
                    {
                    match(input,SLASH,FOLLOW_SLASH_in_valuepart981); 
                    ((terms_scope)terms_stack.peek()).op = Term.Operator.SLASH;

                    }
                    break;
                case 16 :
                    // CSSTreeParser.g:470:4: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_valuepart992); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 17 :
                    // CSSTreeParser.g:471:4: ASTERISK
                    {
                    match(input,ASTERISK,FOLLOW_ASTERISK_in_valuepart1000); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 18 :
                    // CSSTreeParser.g:472:7: ^(f= FUNCTION t= terms )
                    {
                    f=(CommonTree)match(input,FUNCTION,FOLLOW_FUNCTION_in_valuepart1014); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_terms_in_valuepart1018);
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
                    // CSSTreeParser.g:479:7: DASHMATCH
                    {
                    match(input,DASHMATCH,FOLLOW_DASHMATCH_in_valuepart1029); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 20 :
                    // CSSTreeParser.g:480:7: ^( PARENBLOCK ( any )* )
                    {
                    match(input,PARENBLOCK,FOLLOW_PARENBLOCK_in_valuepart1040); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:480:20: ( any )*
                        loop20:
                        do {
                            int alt20=2;
                            int LA20_0 = input.LA(1);

                            if ( ((LA20_0>=PARENBLOCK && LA20_0<=BRACEBLOCK)||LA20_0==INVALID_STRING||(LA20_0>=COLON && LA20_0<=IDENT)||LA20_0==COMMA||(LA20_0>=EXCLAMATION && LA20_0<=CLASSKEYWORD)||(LA20_0>=NUMBER && LA20_0<=SLASH)||LA20_0==FUNCTION||LA20_0==DASHMATCH||LA20_0==STRING) ) {
                                alt20=1;
                            }


                            switch (alt20) {
                        	case 1 :
                        	    // CSSTreeParser.g:480:20: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_valuepart1042);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop20;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 21 :
                    // CSSTreeParser.g:481:7: ^( BRACEBLOCK ( any )* )
                    {
                    match(input,BRACEBLOCK,FOLLOW_BRACEBLOCK_in_valuepart1055); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:481:20: ( any )*
                        loop21:
                        do {
                            int alt21=2;
                            int LA21_0 = input.LA(1);

                            if ( ((LA21_0>=PARENBLOCK && LA21_0<=BRACEBLOCK)||LA21_0==INVALID_STRING||(LA21_0>=COLON && LA21_0<=IDENT)||LA21_0==COMMA||(LA21_0>=EXCLAMATION && LA21_0<=CLASSKEYWORD)||(LA21_0>=NUMBER && LA21_0<=SLASH)||LA21_0==FUNCTION||LA21_0==DASHMATCH||LA21_0==STRING) ) {
                                alt21=1;
                            }


                            switch (alt21) {
                        	case 1 :
                        	    // CSSTreeParser.g:481:20: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_valuepart1057);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop21;
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
    // CSSTreeParser.g:484:1: combined_selector returns [CombinedSelector combinedSelector] : s= selector (c= combinator s= selector )* ;
    public final CombinedSelector combined_selector() throws RecognitionException {
        combined_selector_stack.push(new combined_selector_scope());
        CombinedSelector combinedSelector = null;

        Selector s = null;

        Selector.Combinator c = null;



        	logEnter("combined_selector");	  
        	combinedSelector = (CombinedSelector) rf.createCombinedSelector().unlock();

        try {
            // CSSTreeParser.g:514:2: (s= selector (c= combinator s= selector )* )
            // CSSTreeParser.g:514:4: s= selector (c= combinator s= selector )*
            {
            pushFollow(FOLLOW_selector_in_combined_selector1105);
            s=selector();

            state._fsp--;


            	     combinedSelector.add(s);
            	  
            // CSSTreeParser.g:517:3: (c= combinator s= selector )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( ((LA23_0>=ADJACENT && LA23_0<=DESCENDANT)) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // CSSTreeParser.g:517:4: c= combinator s= selector
            	    {
            	    pushFollow(FOLLOW_combinator_in_combined_selector1114);
            	    c=combinator();

            	    state._fsp--;

            	    pushFollow(FOLLOW_selector_in_combined_selector1118);
            	    s=selector();

            	    state._fsp--;


            	    	     s.setCombinator(c);
            	    	     combinedSelector.add(s);	
            	    	  

            	    }
            	    break;

            	default :
            	    break loop23;
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
    // CSSTreeParser.g:524:1: combinator returns [Selector.Combinator combinator] : ( CHILD | ADJACENT | DESCENDANT );
    public final Selector.Combinator combinator() throws RecognitionException {
        Selector.Combinator combinator = null;

         logEnter("combinator"); 
        try {
            // CSSTreeParser.g:527:2: ( CHILD | ADJACENT | DESCENDANT )
            int alt24=3;
            switch ( input.LA(1) ) {
            case CHILD:
                {
                alt24=1;
                }
                break;
            case ADJACENT:
                {
                alt24=2;
                }
                break;
            case DESCENDANT:
                {
                alt24=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // CSSTreeParser.g:527:4: CHILD
                    {
                    match(input,CHILD,FOLLOW_CHILD_in_combinator1148); 
                    combinator =Selector.Combinator.CHILD;

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:528:4: ADJACENT
                    {
                    match(input,ADJACENT,FOLLOW_ADJACENT_in_combinator1155); 
                    combinator =Selector.Combinator.ADJACENT;

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:529:4: DESCENDANT
                    {
                    match(input,DESCENDANT,FOLLOW_DESCENDANT_in_combinator1162); 
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
    // CSSTreeParser.g:533:1: selector returns [Selector sel] : ( ^( SELECTOR ^( ELEMENT (i= IDENT )? ) ( selpart )* ) | ^( SELECTOR ( selpart )+ ) | INVALID_SELECTOR );
    public final Selector selector() throws RecognitionException {
        selector_stack.push(new selector_scope());
        Selector sel = null;

        CommonTree i=null;


        	logEnter("selector");
        	((selector_scope)selector_stack.peek()).s =sel=(Selector)rf.createSelector().unlock();
        	Selector.ElementName en = rf.createElement(Selector.SelectorPart.WILDCARD);

        try {
            // CSSTreeParser.g:545:5: ( ^( SELECTOR ^( ELEMENT (i= IDENT )? ) ( selpart )* ) | ^( SELECTOR ( selpart )+ ) | INVALID_SELECTOR )
            int alt28=3;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==SELECTOR) ) {
                int LA28_1 = input.LA(2);

                if ( (LA28_1==DOWN) ) {
                    int LA28_3 = input.LA(3);

                    if ( (LA28_3==ELEMENT) ) {
                        alt28=1;
                    }
                    else if ( (LA28_3==PSEUDO||LA28_3==ATTRIBUTE||LA28_3==INVALID_SELPART||LA28_3==CLASSKEYWORD||LA28_3==HASH) ) {
                        alt28=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 28, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 28, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA28_0==INVALID_SELECTOR) ) {
                alt28=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // CSSTreeParser.g:545:7: ^( SELECTOR ^( ELEMENT (i= IDENT )? ) ( selpart )* )
                    {
                    match(input,SELECTOR,FOLLOW_SELECTOR_in_selector1198); 

                    match(input, Token.DOWN, null); 
                    match(input,ELEMENT,FOLLOW_ELEMENT_in_selector1210); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:547:11: (i= IDENT )?
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==IDENT) ) {
                            alt25=1;
                        }
                        switch (alt25) {
                            case 1 :
                                // CSSTreeParser.g:547:12: i= IDENT
                                {
                                i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_selector1226); 
                                 en.setValue(extractText(i)); 

                                }
                                break;

                        }


                        match(input, Token.UP, null); 
                    }

                    		  log.debug("Adding element name: {}.", en.getValue());
                    		  ((selector_scope)selector_stack.peek()).s.add(en);
                    		 
                    // CSSTreeParser.g:553:10: ( selpart )*
                    loop26:
                    do {
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( (LA26_0==PSEUDO||LA26_0==ATTRIBUTE||LA26_0==INVALID_SELPART||LA26_0==CLASSKEYWORD||LA26_0==HASH) ) {
                            alt26=1;
                        }


                        switch (alt26) {
                    	case 1 :
                    	    // CSSTreeParser.g:553:10: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1273);
                    	    selpart();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop26;
                        }
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:555:7: ^( SELECTOR ( selpart )+ )
                    {
                    match(input,SELECTOR,FOLLOW_SELECTOR_in_selector1292); 

                    match(input, Token.DOWN, null); 
                    // CSSTreeParser.g:556:10: ( selpart )+
                    int cnt27=0;
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==PSEUDO||LA27_0==ATTRIBUTE||LA27_0==INVALID_SELPART||LA27_0==CLASSKEYWORD||LA27_0==HASH) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // CSSTreeParser.g:556:10: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1304);
                    	    selpart();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt27 >= 1 ) break loop27;
                                EarlyExitException eee =
                                    new EarlyExitException(27, input);
                                throw eee;
                        }
                        cnt27++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:558:7: INVALID_SELECTOR
                    {
                    match(input,INVALID_SELECTOR,FOLLOW_INVALID_SELECTOR_in_selector1322); 
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
    // CSSTreeParser.g:561:1: selpart : (h= HASH | c= CLASSKEYWORD | ^( ATTRIBUTE ea= attribute ) | p= pseudo | INVALID_SELPART );
    public final void selpart() throws RecognitionException {
        CommonTree h=null;
        CommonTree c=null;
        Selector.ElementAttribute ea = null;

        Selector.PseudoPage p = null;



        	logEnter("selpart");

        try {
            // CSSTreeParser.g:568:5: (h= HASH | c= CLASSKEYWORD | ^( ATTRIBUTE ea= attribute ) | p= pseudo | INVALID_SELPART )
            int alt29=5;
            switch ( input.LA(1) ) {
            case HASH:
                {
                alt29=1;
                }
                break;
            case CLASSKEYWORD:
                {
                alt29=2;
                }
                break;
            case ATTRIBUTE:
                {
                alt29=3;
                }
                break;
            case PSEUDO:
                {
                alt29=4;
                }
                break;
            case INVALID_SELPART:
                {
                alt29=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }

            switch (alt29) {
                case 1 :
                    // CSSTreeParser.g:568:8: h= HASH
                    {
                    h=(CommonTree)match(input,HASH,FOLLOW_HASH_in_selpart1356); 
                     ((selector_scope)selector_stack.peek()).s.add(rf.createID(extractText(h))); 

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:569:7: c= CLASSKEYWORD
                    {
                    c=(CommonTree)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_selpart1368); 
                     ((selector_scope)selector_stack.peek()).s.add(rf.createClass(extractText(c))); 

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:570:4: ^( ATTRIBUTE ea= attribute )
                    {
                    match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_selpart1376); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_attribute_in_selpart1380);
                    ea=attribute();

                    state._fsp--;

                     ((selector_scope)selector_stack.peek()).s.add(ea);

                    match(input, Token.UP, null); 

                    }
                    break;
                case 4 :
                    // CSSTreeParser.g:571:7: p= pseudo
                    {
                    pushFollow(FOLLOW_pseudo_in_selpart1394);
                    p=pseudo();

                    state._fsp--;

                     ((selector_scope)selector_stack.peek()).s.add(p);

                    }
                    break;
                case 5 :
                    // CSSTreeParser.g:572:4: INVALID_SELPART
                    {
                    match(input,INVALID_SELPART,FOLLOW_INVALID_SELPART_in_selpart1401); 
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
    // CSSTreeParser.g:575:1: attribute returns [Selector.ElementAttribute elemAttr] : i= IDENT ( ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string ) )? ;
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
            // CSSTreeParser.g:593:2: (i= IDENT ( ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string ) )? )
            // CSSTreeParser.g:593:4: i= IDENT ( ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string ) )?
            {
            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_attribute1435); 
            attribute=extractText(i); 
            // CSSTreeParser.g:594:4: ( ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==INCLUDES||LA32_0==EQUALS||LA32_0==DASHMATCH) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // CSSTreeParser.g:594:5: ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string )
                    {
                    // CSSTreeParser.g:594:5: ( EQUALS | INCLUDES | DASHMATCH )
                    int alt30=3;
                    switch ( input.LA(1) ) {
                    case EQUALS:
                        {
                        alt30=1;
                        }
                        break;
                    case INCLUDES:
                        {
                        alt30=2;
                        }
                        break;
                    case DASHMATCH:
                        {
                        alt30=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 30, 0, input);

                        throw nvae;
                    }

                    switch (alt30) {
                        case 1 :
                            // CSSTreeParser.g:594:6: EQUALS
                            {
                            match(input,EQUALS,FOLLOW_EQUALS_in_attribute1444); 
                            op=Selector.Operator.EQUALS; 

                            }
                            break;
                        case 2 :
                            // CSSTreeParser.g:595:7: INCLUDES
                            {
                            match(input,INCLUDES,FOLLOW_INCLUDES_in_attribute1455); 
                            op=Selector.Operator.INCLUDES; 

                            }
                            break;
                        case 3 :
                            // CSSTreeParser.g:596:7: DASHMATCH
                            {
                            match(input,DASHMATCH,FOLLOW_DASHMATCH_in_attribute1466); 
                            op=Selector.Operator.DASHMATCH; 

                            }
                            break;

                    }

                    // CSSTreeParser.g:598:5: (i= IDENT | s= string )
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==IDENT) ) {
                        alt31=1;
                    }
                    else if ( (LA31_0==INVALID_STRING||LA31_0==STRING) ) {
                        alt31=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 31, 0, input);

                        throw nvae;
                    }
                    switch (alt31) {
                        case 1 :
                            // CSSTreeParser.g:598:6: i= IDENT
                            {
                            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_attribute1484); 

                            		value=extractText(i);
                            		isStringValue=false;
                            		

                            }
                            break;
                        case 2 :
                            // CSSTreeParser.g:602:7: s= string
                            {
                            pushFollow(FOLLOW_string_in_attribute1496);
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
    // CSSTreeParser.g:614:1: pseudo returns [Selector.PseudoPage pseudoPage] : ^( PSEUDO (f= FUNCTION )? i= IDENT ) ;
    public final Selector.PseudoPage pseudo() throws RecognitionException {
        Selector.PseudoPage pseudoPage = null;

        CommonTree f=null;
        CommonTree i=null;


        		  logEnter("pseudo");
        		  String fname =null;
        		  String value = null;

        try {
            // CSSTreeParser.g:620:2: ( ^( PSEUDO (f= FUNCTION )? i= IDENT ) )
            // CSSTreeParser.g:620:4: ^( PSEUDO (f= FUNCTION )? i= IDENT )
            {
            match(input,PSEUDO,FOLLOW_PSEUDO_in_pseudo1529); 

            match(input, Token.DOWN, null); 
            // CSSTreeParser.g:621:8: (f= FUNCTION )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==FUNCTION) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // CSSTreeParser.g:621:9: f= FUNCTION
                    {
                    f=(CommonTree)match(input,FUNCTION,FOLLOW_FUNCTION_in_pseudo1542); 
                    fname=extractText(f);

                    }
                    break;

            }

            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_pseudo1559); 
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
    // CSSTreeParser.g:628:1: string returns [String s] : (st= STRING | INVALID_STRING );
    public final String string() throws RecognitionException {
        String s = null;

        CommonTree st=null;

        try {
            // CSSTreeParser.g:629:2: (st= STRING | INVALID_STRING )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==STRING) ) {
                alt34=1;
            }
            else if ( (LA34_0==INVALID_STRING) ) {
                alt34=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // CSSTreeParser.g:629:4: st= STRING
                    {
                    st=(CommonTree)match(input,STRING,FOLLOW_STRING_in_string1584); 
                     s = extractText(st);

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:630:4: INVALID_STRING
                    {
                    match(input,INVALID_STRING,FOLLOW_INVALID_STRING_in_string1591); 
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
    // CSSTreeParser.g:633:1: any : ( IDENT | CLASSKEYWORD | NUMBER | PERCENTAGE | DIMENSION | string | URI | HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | EXCLAMATION | ^( FUNCTION ( any )* ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) );
    public final void any() throws RecognitionException {
        try {
            // CSSTreeParser.g:634:3: ( IDENT | CLASSKEYWORD | NUMBER | PERCENTAGE | DIMENSION | string | URI | HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | EXCLAMATION | ^( FUNCTION ( any )* ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) )
            int alt38=20;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt38=1;
                }
                break;
            case CLASSKEYWORD:
                {
                alt38=2;
                }
                break;
            case NUMBER:
                {
                alt38=3;
                }
                break;
            case PERCENTAGE:
                {
                alt38=4;
                }
                break;
            case DIMENSION:
                {
                alt38=5;
                }
                break;
            case INVALID_STRING:
            case STRING:
                {
                alt38=6;
                }
                break;
            case URI:
                {
                alt38=7;
                }
                break;
            case HASH:
                {
                alt38=8;
                }
                break;
            case UNIRANGE:
                {
                alt38=9;
                }
                break;
            case INCLUDES:
                {
                alt38=10;
                }
                break;
            case COLON:
                {
                alt38=11;
                }
                break;
            case COMMA:
                {
                alt38=12;
                }
                break;
            case GREATER:
                {
                alt38=13;
                }
                break;
            case EQUALS:
                {
                alt38=14;
                }
                break;
            case SLASH:
                {
                alt38=15;
                }
                break;
            case EXCLAMATION:
                {
                alt38=16;
                }
                break;
            case FUNCTION:
                {
                alt38=17;
                }
                break;
            case DASHMATCH:
                {
                alt38=18;
                }
                break;
            case PARENBLOCK:
                {
                alt38=19;
                }
                break;
            case BRACEBLOCK:
                {
                alt38=20;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }

            switch (alt38) {
                case 1 :
                    // CSSTreeParser.g:634:5: IDENT
                    {
                    match(input,IDENT,FOLLOW_IDENT_in_any1607); 

                    }
                    break;
                case 2 :
                    // CSSTreeParser.g:635:5: CLASSKEYWORD
                    {
                    match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_any1613); 

                    }
                    break;
                case 3 :
                    // CSSTreeParser.g:636:5: NUMBER
                    {
                    match(input,NUMBER,FOLLOW_NUMBER_in_any1619); 

                    }
                    break;
                case 4 :
                    // CSSTreeParser.g:637:5: PERCENTAGE
                    {
                    match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_any1625); 

                    }
                    break;
                case 5 :
                    // CSSTreeParser.g:638:5: DIMENSION
                    {
                    match(input,DIMENSION,FOLLOW_DIMENSION_in_any1631); 

                    }
                    break;
                case 6 :
                    // CSSTreeParser.g:639:5: string
                    {
                    pushFollow(FOLLOW_string_in_any1637);
                    string();

                    state._fsp--;


                    }
                    break;
                case 7 :
                    // CSSTreeParser.g:640:5: URI
                    {
                    match(input,URI,FOLLOW_URI_in_any1643); 

                    }
                    break;
                case 8 :
                    // CSSTreeParser.g:641:5: HASH
                    {
                    match(input,HASH,FOLLOW_HASH_in_any1649); 

                    }
                    break;
                case 9 :
                    // CSSTreeParser.g:642:5: UNIRANGE
                    {
                    match(input,UNIRANGE,FOLLOW_UNIRANGE_in_any1655); 

                    }
                    break;
                case 10 :
                    // CSSTreeParser.g:643:5: INCLUDES
                    {
                    match(input,INCLUDES,FOLLOW_INCLUDES_in_any1661); 

                    }
                    break;
                case 11 :
                    // CSSTreeParser.g:644:5: COLON
                    {
                    match(input,COLON,FOLLOW_COLON_in_any1667); 

                    }
                    break;
                case 12 :
                    // CSSTreeParser.g:645:5: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_any1673); 

                    }
                    break;
                case 13 :
                    // CSSTreeParser.g:646:5: GREATER
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_any1679); 

                    }
                    break;
                case 14 :
                    // CSSTreeParser.g:647:5: EQUALS
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_any1685); 

                    }
                    break;
                case 15 :
                    // CSSTreeParser.g:648:5: SLASH
                    {
                    match(input,SLASH,FOLLOW_SLASH_in_any1691); 

                    }
                    break;
                case 16 :
                    // CSSTreeParser.g:649:5: EXCLAMATION
                    {
                    match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_any1697); 

                    }
                    break;
                case 17 :
                    // CSSTreeParser.g:650:5: ^( FUNCTION ( any )* )
                    {
                    match(input,FUNCTION,FOLLOW_FUNCTION_in_any1704); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:650:16: ( any )*
                        loop35:
                        do {
                            int alt35=2;
                            int LA35_0 = input.LA(1);

                            if ( ((LA35_0>=PARENBLOCK && LA35_0<=BRACEBLOCK)||LA35_0==INVALID_STRING||(LA35_0>=COLON && LA35_0<=IDENT)||LA35_0==COMMA||(LA35_0>=EXCLAMATION && LA35_0<=CLASSKEYWORD)||(LA35_0>=NUMBER && LA35_0<=SLASH)||LA35_0==FUNCTION||LA35_0==DASHMATCH||LA35_0==STRING) ) {
                                alt35=1;
                            }


                            switch (alt35) {
                        	case 1 :
                        	    // CSSTreeParser.g:650:16: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_any1706);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop35;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }

                    }
                    break;
                case 18 :
                    // CSSTreeParser.g:651:5: DASHMATCH
                    {
                    match(input,DASHMATCH,FOLLOW_DASHMATCH_in_any1715); 

                    }
                    break;
                case 19 :
                    // CSSTreeParser.g:652:5: ^( PARENBLOCK ( any )* )
                    {
                    match(input,PARENBLOCK,FOLLOW_PARENBLOCK_in_any1722); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:652:18: ( any )*
                        loop36:
                        do {
                            int alt36=2;
                            int LA36_0 = input.LA(1);

                            if ( ((LA36_0>=PARENBLOCK && LA36_0<=BRACEBLOCK)||LA36_0==INVALID_STRING||(LA36_0>=COLON && LA36_0<=IDENT)||LA36_0==COMMA||(LA36_0>=EXCLAMATION && LA36_0<=CLASSKEYWORD)||(LA36_0>=NUMBER && LA36_0<=SLASH)||LA36_0==FUNCTION||LA36_0==DASHMATCH||LA36_0==STRING) ) {
                                alt36=1;
                            }


                            switch (alt36) {
                        	case 1 :
                        	    // CSSTreeParser.g:652:18: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_any1724);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop36;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }

                    }
                    break;
                case 20 :
                    // CSSTreeParser.g:653:5: ^( BRACEBLOCK ( any )* )
                    {
                    match(input,BRACEBLOCK,FOLLOW_BRACEBLOCK_in_any1733); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CSSTreeParser.g:653:18: ( any )*
                        loop37:
                        do {
                            int alt37=2;
                            int LA37_0 = input.LA(1);

                            if ( ((LA37_0>=PARENBLOCK && LA37_0<=BRACEBLOCK)||LA37_0==INVALID_STRING||(LA37_0>=COLON && LA37_0<=IDENT)||LA37_0==COMMA||(LA37_0>=EXCLAMATION && LA37_0<=CLASSKEYWORD)||(LA37_0>=NUMBER && LA37_0<=SLASH)||LA37_0==FUNCTION||LA37_0==DASHMATCH||LA37_0==STRING) ) {
                                alt37=1;
                            }


                            switch (alt37) {
                        	case 1 :
                        	    // CSSTreeParser.g:653:18: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_any1735);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop37;
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


    protected DFA22 dfa22 = new DFA22(this);
    static final String DFA22_eotS =
        "\27\uffff";
    static final String DFA22_eofS =
        "\27\uffff";
    static final String DFA22_minS =
        "\1\10\2\uffff\1\56\23\uffff";
    static final String DFA22_maxS =
        "\1\100\2\uffff\1\60\23\uffff";
    static final String DFA22_acceptS =
        "\1\uffff\1\1\1\2\1\uffff\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13"+
        "\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25";
    static final String DFA22_specialS =
        "\27\uffff}>";
    static final String[] DFA22_transitionS = {
            "\1\25\1\26\15\uffff\1\7\13\uffff\1\14\1\1\4\uffff\1\15\2\uffff"+
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

    static final short[] DFA22_eot = DFA.unpackEncodedString(DFA22_eotS);
    static final short[] DFA22_eof = DFA.unpackEncodedString(DFA22_eofS);
    static final char[] DFA22_min = DFA.unpackEncodedStringToUnsignedChars(DFA22_minS);
    static final char[] DFA22_max = DFA.unpackEncodedStringToUnsignedChars(DFA22_maxS);
    static final short[] DFA22_accept = DFA.unpackEncodedString(DFA22_acceptS);
    static final short[] DFA22_special = DFA.unpackEncodedString(DFA22_specialS);
    static final short[][] DFA22_transition;

    static {
        int numStates = DFA22_transitionS.length;
        DFA22_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA22_transition[i] = DFA.unpackEncodedString(DFA22_transitionS[i]);
        }
    }

    class DFA22 extends DFA {

        public DFA22(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 22;
            this.eot = DFA22_eot;
            this.eof = DFA22_eof;
            this.min = DFA22_min;
            this.max = DFA22_max;
            this.accept = DFA22_accept;
            this.special = DFA22_special;
            this.transition = DFA22_transition;
        }
        public String getDescription() {
            return "419:1: valuepart : (i= IDENT | CLASSKEYWORD | ( MINUS )? n= NUMBER | ( MINUS )? p= PERCENTAGE | ( MINUS )? d= DIMENSION | s= string | u= URI | h= HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | PLUS | ASTERISK | ^(f= FUNCTION t= terms ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) );";
        }
    }
 

    public static final BitSet FOLLOW_INLINESTYLE_in_inlinestyle59 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_declarations_in_inlinestyle61 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INLINESTYLE_in_inlinestyle71 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_inlineset_in_inlinestyle73 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_STYLESHEET_in_stylesheet105 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_stylesheet114 = new BitSet(new long[]{0x0000008718400408L});
    public static final BitSet FOLLOW_ruleset_in_statement163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atstatement_in_statement173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARSET_in_atstatement206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_IMPORT_in_atstatement212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_atstatement220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_END_in_atstatement231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PAGE_in_atstatement239 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_atstatement244 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_declarations_in_atstatement251 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MEDIA_in_atstatement262 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_media_in_atstatement267 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_ruleset_in_atstatement278 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_INVALID_STATEMENT_in_atstatement300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_media332 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_RULE_in_inlineset353 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_pseudo_in_inlineset355 = new BitSet(new long[]{0x0000000000042000L});
    public static final BitSet FOLLOW_declarations_in_inlineset358 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RULE_in_ruleset405 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_combined_selector_in_ruleset419 = new BitSet(new long[]{0x0000000001040800L});
    public static final BitSet FOLLOW_declarations_in_ruleset440 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SET_in_declarations481 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_declaration_in_declarations486 = new BitSet(new long[]{0x0000000004080008L});
    public static final BitSet FOLLOW_DECLARATION_in_declaration530 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_important_in_declaration537 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_property_in_declaration549 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_terms_in_declaration560 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INVALID_DECLARATION_in_declaration581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORTANT_in_important598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_property638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_terms683 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_term_in_terms685 = new BitSet(new long[]{0x17FFF31800800388L,0x0000000000000001L});
    public static final BitSet FOLLOW_valuepart_in_term708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CURLYBLOCK_in_term725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATKEYWORD_in_term735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_valuepart763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_valuepart775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart783 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_NUMBER_in_valuepart791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart805 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_PERCENTAGE_in_valuepart813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart825 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_DIMENSION_in_valuepart833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_valuepart849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_URI_in_valuepart866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_valuepart884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_valuepart902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCLUDES_in_valuepart913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_valuepart924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_valuepart938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_valuepart956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_valuepart968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_valuepart981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_valuepart992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASTERISK_in_valuepart1000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_valuepart1014 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_terms_in_valuepart1018 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DASHMATCH_in_valuepart1029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENBLOCK_in_valuepart1040 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_valuepart1042 = new BitSet(new long[]{0x14FFDA1800800308L,0x0000000000000001L});
    public static final BitSet FOLLOW_BRACEBLOCK_in_valuepart1055 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_valuepart1057 = new BitSet(new long[]{0x14FFDA1800800308L,0x0000000000000001L});
    public static final BitSet FOLLOW_selector_in_combined_selector1105 = new BitSet(new long[]{0x000000000001C002L});
    public static final BitSet FOLLOW_combinator_in_combined_selector1114 = new BitSet(new long[]{0x0000000001040800L});
    public static final BitSet FOLLOW_selector_in_combined_selector1118 = new BitSet(new long[]{0x000000000001C002L});
    public static final BitSet FOLLOW_CHILD_in_combinator1148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADJACENT_in_combinator1155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DESCENDANT_in_combinator1162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELECTOR_in_selector1198 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ELEMENT_in_selector1210 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_selector1226 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_selpart_in_selector1273 = new BitSet(new long[]{0x0004100002062008L});
    public static final BitSet FOLLOW_SELECTOR_in_selector1292 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_selpart_in_selector1304 = new BitSet(new long[]{0x0004100002062008L});
    public static final BitSet FOLLOW_INVALID_SELECTOR_in_selector1322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_selpart1356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_selpart1368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_selpart1376 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_attribute_in_selpart1380 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_pseudo_in_selpart1394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_SELPART_in_selpart1401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_attribute1435 = new BitSet(new long[]{0x1050000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_attribute1444 = new BitSet(new long[]{0x0000001000800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_INCLUDES_in_attribute1455 = new BitSet(new long[]{0x0000001000800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_DASHMATCH_in_attribute1466 = new BitSet(new long[]{0x0000001000800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_IDENT_in_attribute1484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_attribute1496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PSEUDO_in_pseudo1529 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_FUNCTION_in_pseudo1542 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_IDENT_in_pseudo1559 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STRING_in_string1584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_STRING_in_string1591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_any1607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_any1613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_any1619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENTAGE_in_any1625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_any1631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_any1637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_URI_in_any1643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_any1649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_any1655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCLUDES_in_any1661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_any1667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_any1673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_any1679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_any1685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_any1691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_any1697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_any1704 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_any1706 = new BitSet(new long[]{0x14FFDA1800800308L,0x0000000000000001L});
    public static final BitSet FOLLOW_DASHMATCH_in_any1715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENBLOCK_in_any1722 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_any1724 = new BitSet(new long[]{0x14FFDA1800800308L,0x0000000000000001L});
    public static final BitSet FOLLOW_BRACEBLOCK_in_any1733 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_any1735 = new BitSet(new long[]{0x14FFDA1800800308L,0x0000000000000001L});

}