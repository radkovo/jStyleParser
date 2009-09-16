// $ANTLR 3.1.2 /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g 2009-09-16 17:38:04

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
import cz.vutbr.web.css.TermExpression;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.RuleBlock.Priority;

// @SuppressWarnings("unchecked")


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CSSTreeParser extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "STYLESHEET", "INLINESTYLE", "ATBLOCK", "CURLYBLOCK", "PARENBLOCK", "BRACEBLOCK", "RULE", "SELECTOR", "ELEMENT", "PSEUDO", "ADJACENT", "CHILD", "DESCENDANT", "ATTRIBUTE", "SET", "DECLARATION", "VALUE", "IMPORTANT", "IMPORT_END", "INVALID_STRING", "INVALID_SELECTOR", "INVALID_SELPART", "INVALID_DECLARATION", "INVALID_STATEMENT", "INVALID_IMPORT", "S", "CDO", "CDC", "CHARSET", "IMPORT", "PAGE", "COLON", "IDENT", "LCURLY", "RCURLY", "MEDIA", "ATKEYWORD", "COMMA", "SEMICOLON", "EXCLAMATION", "MINUS", "EXPRESSION", "FUNCTION", "RPAREN", "CLASSKEYWORD", "NUMBER", "PERCENTAGE", "DIMENSION", "URI", "HASH", "UNIRANGE", "INCLUDES", "GREATER", "LESS", "QUESTION", "PERCENT", "EQUALS", "SLASH", "PLUS", "ASTERISK", "DASHMATCH", "LPAREN", "LBRACE", "RBRACE", "STRING", "STRING_CHAR", "INVALID_TOKEN", "IDENT_MACR", "STRING_MACR", "NAME_MACR", "NUMBER_MACR", "W_MACR", "URI_MACR", "APOS", "QUOT", "W_CHAR", "COMMENT", "SL_COMMENT", "NAME_START", "NAME_CHAR", "NON_ASCII", "ESCAPE_CHAR", "URI_CHAR", "NL_CHAR", "'important'"
    };
    public static final int FUNCTION=46;
    public static final int APOS=77;
    public static final int NAME_CHAR=83;
    public static final int CLASSKEYWORD=48;
    public static final int PSEUDO=13;
    public static final int INVALID_STATEMENT=27;
    public static final int LBRACE=66;
    public static final int ATTRIBUTE=17;
    public static final int INVALID_TOKEN=70;
    public static final int EQUALS=60;
    public static final int NAME_START=82;
    public static final int NUMBER_MACR=74;
    public static final int MEDIA=39;
    public static final int CHARSET=32;
    public static final int NL_CHAR=87;
    public static final int NON_ASCII=84;
    public static final int EOF=-1;
    public static final int DECLARATION=19;
    public static final int STYLESHEET=4;
    public static final int LPAREN=65;
    public static final int ASTERISK=63;
    public static final int BRACEBLOCK=9;
    public static final int INCLUDES=55;
    public static final int RPAREN=47;
    public static final int IMPORT=33;
    public static final int SLASH=61;
    public static final int GREATER=56;
    public static final int SELECTOR=11;
    public static final int EXCLAMATION=43;
    public static final int ATBLOCK=6;
    public static final int COMMA=41;
    public static final int INVALID_SELPART=25;
    public static final int LESS=57;
    public static final int INVALID_DECLARATION=26;
    public static final int ELEMENT=12;
    public static final int IDENT=36;
    public static final int PLUS=62;
    public static final int UNIRANGE=54;
    public static final int DIMENSION=51;
    public static final int COMMENT=80;
    public static final int EXPRESSION=45;
    public static final int CURLYBLOCK=7;
    public static final int ATKEYWORD=40;
    public static final int CHILD=15;
    public static final int INVALID_STRING=23;
    public static final int RULE=10;
    public static final int PERCENT=59;
    public static final int RBRACE=67;
    public static final int W_CHAR=79;
    public static final int PARENBLOCK=8;
    public static final int STRING_MACR=72;
    public static final int W_MACR=75;
    public static final int QUOT=78;
    public static final int DESCENDANT=16;
    public static final int NUMBER=49;
    public static final int URI_CHAR=86;
    public static final int HASH=53;
    public static final int LCURLY=37;
    public static final int SET=18;
    public static final int SEMICOLON=42;
    public static final int NAME_MACR=73;
    public static final int S=29;
    public static final int CDO=30;
    public static final int VALUE=20;
    public static final int MINUS=44;
    public static final int CDC=31;
    public static final int PERCENTAGE=50;
    public static final int T__88=88;
    public static final int INVALID_SELECTOR=24;
    public static final int IMPORTANT=21;
    public static final int URI=52;
    public static final int ESCAPE_CHAR=85;
    public static final int COLON=35;
    public static final int PAGE=34;
    public static final int STRING_CHAR=69;
    public static final int DASHMATCH=64;
    public static final int ADJACENT=14;
    public static final int QUESTION=58;
    public static final int IMPORT_END=22;
    public static final int INVALID_IMPORT=28;
    public static final int INLINESTYLE=5;
    public static final int RCURLY=38;
    public static final int SL_COMMENT=81;
    public static final int IDENT_MACR=71;
    public static final int STRING=68;
    public static final int URI_MACR=76;

    // delegates
    // delegators


        public CSSTreeParser(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public CSSTreeParser(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return CSSTreeParser.tokenNames; }
    public String getGrammarFileName() { return "/home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g"; }


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
        	
    		
        private void logEnter(String entry) {
            log.trace("Entering '{}'", entry);
        }
        	
        private void logLeave(String leaving) {
    	    log.trace("Leaving '{}'", leaving);
        }



    // $ANTLR start "inlinestyle"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:99:1: inlinestyle returns [StyleSheet sheet] : ( ^( INLINESTYLE decl= declarations ) | ^( INLINESTYLE (irs= inlineset )+ ) );
    public final StyleSheet inlinestyle() throws RecognitionException {
        StyleSheet sheet = null;

        List<Declaration> decl = null;

        RuleBlock<?> irs = null;



        	logEnter("inlinestyle");
        	sheet = this.stylesheet;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:110:2: ( ^( INLINESTYLE decl= declarations ) | ^( INLINESTYLE (irs= inlineset )+ ) )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:110:5: ^( INLINESTYLE decl= declarations )
                    {
                    match(input,INLINESTYLE,FOLLOW_INLINESTYLE_in_inlinestyle59); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_declarations_in_inlinestyle63);
                    decl=declarations();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			RuleBlock<?> rb = preparator.prepareInlineRuleSet(decl, null);
                    			if(rb!=null) {
                    			     sheet.add(rb);
                    			}
                    		

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:117:6: ^( INLINESTYLE (irs= inlineset )+ )
                    {
                    match(input,INLINESTYLE,FOLLOW_INLINESTYLE_in_inlinestyle78); 

                    match(input, Token.DOWN, null); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:118:5: (irs= inlineset )+
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
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:118:6: irs= inlineset
                    	    {
                    	    pushFollow(FOLLOW_inlineset_in_inlinestyle88);
                    	    irs=inlineset();

                    	    state._fsp--;

                    	    if(irs!=null) sheet.add(irs);

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
            	sheet.markLast(preparator.markPriority());
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:122:1: stylesheet returns [StyleSheet sheet] : ^( STYLESHEET (s= statement )* ) ;
    public final StyleSheet stylesheet() throws RecognitionException {
        StyleSheet sheet = null;

        RuleBlock<?> s = null;



        	logEnter("stylesheet");
        	sheet = this.stylesheet;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:136:2: ( ^( STYLESHEET (s= statement )* ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:136:4: ^( STYLESHEET (s= statement )* )
            {
            match(input,STYLESHEET,FOLLOW_STYLESHEET_in_stylesheet125); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:137:4: (s= statement )*
                loop3:
                do {
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==RULE||LA3_0==IMPORT_END||(LA3_0>=INVALID_STATEMENT && LA3_0<=INVALID_IMPORT)||(LA3_0>=CHARSET && LA3_0<=PAGE)||LA3_0==MEDIA) ) {
                        alt3=1;
                    }


                    switch (alt3) {
                	case 1 :
                	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:137:5: s= statement
                	    {
                	    pushFollow(FOLLOW_statement_in_stylesheet134);
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
            	sheet.markLast(preparator.markPriority());
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:141:1: statement returns [RuleBlock<?> stm] : (rs= ruleset | ats= atstatement );
    public final RuleBlock<?> statement() throws RecognitionException {
        statement_stack.push(new statement_scope());
        RuleBlock<?> stm = null;

        RuleBlock<?> rs = null;

        RuleBlock<?> ats = null;



        	logEnter("statement");
        	((statement_scope)statement_stack.peek()).invalid = false;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:160:2: (rs= ruleset | ats= atstatement )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:160:4: rs= ruleset
                    {
                    pushFollow(FOLLOW_ruleset_in_statement183);
                    rs=ruleset();

                    state._fsp--;

                    stm =(RuleBlock<?>) rs;

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:161:4: ats= atstatement
                    {
                    pushFollow(FOLLOW_atstatement_in_statement193);
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:165:1: atstatement returns [RuleBlock<?> stmnt] : ( CHARSET | INVALID_IMPORT | i= IMPORT | IMPORT_END | ^( PAGE (i= IDENT )? decl= declarations ) | ^( MEDIA (mediaList= media )? (rs= ruleset )* ) | INVALID_STATEMENT );
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
        	List<RuleSet> rules = null;
        	String pseudo = null;
        	Priority mark = preparator.markPriority();

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:180:2: ( CHARSET | INVALID_IMPORT | i= IMPORT | IMPORT_END | ^( PAGE (i= IDENT )? decl= declarations ) | ^( MEDIA (mediaList= media )? (rs= ruleset )* ) | INVALID_STATEMENT )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:180:4: CHARSET
                    {
                    match(input,CHARSET,FOLLOW_CHARSET_in_atstatement226); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:181:4: INVALID_IMPORT
                    {
                    match(input,INVALID_IMPORT,FOLLOW_INVALID_IMPORT_in_atstatement232); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:182:4: i= IMPORT
                    {
                    i=(CommonTree)match(input,IMPORT,FOLLOW_IMPORT_in_atstatement240); 

                    	    String media = extractText(i);
                    		imports.push(new TreeParserState(media));
                    		
                    		log.info("From imported file: Rules will use these media: {}", 
                    			imports.peek());
                    	  

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:190:4: IMPORT_END
                    {
                    match(input,IMPORT_END,FOLLOW_IMPORT_END_in_atstatement251); 

                    	    imports.pop();
                    		log.info("Imported file was parsed, returing in nesting.");
                    	  

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:194:4: ^( PAGE (i= IDENT )? decl= declarations )
                    {
                    match(input,PAGE,FOLLOW_PAGE_in_atstatement259); 

                    match(input, Token.DOWN, null); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:194:11: (i= IDENT )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==IDENT) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:194:12: i= IDENT
                            {
                            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_atstatement264); 
                             pseudo=extractText(i);

                            }
                            break;

                    }

                    pushFollow(FOLLOW_declarations_in_atstatement271);
                    decl=declarations();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     stmnt = preparator.prepareRulePage(decl, pseudo); 

                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:196:4: ^( MEDIA (mediaList= media )? (rs= ruleset )* )
                    {
                    match(input,MEDIA,FOLLOW_MEDIA_in_atstatement282); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:196:12: (mediaList= media )?
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==IDENT) ) {
                            alt6=1;
                        }
                        switch (alt6) {
                            case 1 :
                                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:196:13: mediaList= media
                                {
                                pushFollow(FOLLOW_media_in_atstatement287);
                                mediaList=media();

                                state._fsp--;


                                }
                                break;

                        }

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:197:4: (rs= ruleset )*
                        loop7:
                        do {
                            int alt7=2;
                            int LA7_0 = input.LA(1);

                            if ( (LA7_0==RULE) ) {
                                alt7=1;
                            }


                            switch (alt7) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:197:5: rs= ruleset
                        	    {
                        	    pushFollow(FOLLOW_ruleset_in_atstatement298);
                        	    rs=ruleset();

                        	    state._fsp--;


                        	    			   if(rules==null) rules = new ArrayList<RuleSet>();				
                        	    			   if(rs!=null) {
                        	    				   // this cast should be safe, because when inside of @statetement, oridinal ruleset
                        	    				   // is returned
                        	    			       rules.add((RuleSet)rs);
                        	    				   log.debug("Inserted ruleset ({}) into @media", rules.size());
                        	    			   }
                        	    		
                        	    			

                        	    }
                        	    break;

                        	default :
                        	    break loop7;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }

                    		   stmnt = preparator.prepareRuleMedia(mark, rules, mediaList);
                    	   

                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:211:4: INVALID_STATEMENT
                    {
                    match(input,INVALID_STATEMENT,FOLLOW_INVALID_STATEMENT_in_atstatement320); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:214:1: media returns [List<String> affected] : (i= IDENT )+ ;
    public final List<String> media() throws RecognitionException {
        List<String> affected = null;

        CommonTree i=null;


           logEnter("media");
           affected = new ArrayList<String>();

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:223:2: ( (i= IDENT )+ )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:223:4: (i= IDENT )+
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:223:4: (i= IDENT )+
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
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:223:5: i= IDENT
            	    {
            	    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_media352); 

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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:229:1: inlineset returns [RuleBlock<?> is] : ^( RULE (p= pseudo )* decl= declarations ) ;
    public final RuleBlock<?> inlineset() throws RecognitionException {
        RuleBlock<?> is = null;

        Selector.PseudoPage p = null;

        List<Declaration> decl = null;



             logEnter("inlineset");
        	 List<Selector.PseudoPage> pplist = new ArrayList<Selector.PseudoPage>();

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:237:2: ( ^( RULE (p= pseudo )* decl= declarations ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:237:4: ^( RULE (p= pseudo )* decl= declarations )
            {
            match(input,RULE,FOLLOW_RULE_in_inlineset387); 

            match(input, Token.DOWN, null); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:237:11: (p= pseudo )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==PSEUDO) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:237:12: p= pseudo
            	    {
            	    pushFollow(FOLLOW_pseudo_in_inlineset392);
            	    p=pseudo();

            	    state._fsp--;

            	    pplist.add(p);

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            pushFollow(FOLLOW_declarations_in_inlineset400);
            decl=declarations();

            state._fsp--;


            match(input, Token.UP, null); 
             is = preparator.prepareInlineRuleSet(decl, pplist); 

            }


                 logLeave("inlineset");   

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return is;
    }
    // $ANTLR end "inlineset"


    // $ANTLR start "ruleset"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:242:1: ruleset returns [RuleBlock<?> stmnt] : ^( RULE (cs= combined_selector )* decl= declarations ) ;
    public final RuleBlock<?> ruleset() throws RecognitionException {
        RuleBlock<?> stmnt = null;

        CombinedSelector cs = null;

        List<Declaration> decl = null;



            logEnter("ruleset"); 
            List<CombinedSelector> cslist = new ArrayList<CombinedSelector>();

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:263:5: ( ^( RULE (cs= combined_selector )* decl= declarations ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:263:7: ^( RULE (cs= combined_selector )* decl= declarations )
            {
            match(input,RULE,FOLLOW_RULE_in_ruleset453); 

            match(input, Token.DOWN, null); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:264:9: (cs= combined_selector )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==SELECTOR||LA11_0==INVALID_SELECTOR) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:264:10: cs= combined_selector
            	    {
            	    pushFollow(FOLLOW_combined_selector_in_ruleset467);
            	    cs=combined_selector();

            	    state._fsp--;

            	    if(cs!=null && !cs.isEmpty() && !((statement_scope)statement_stack.peek()).invalid) {
            	                cslist.add(cs);
            	                log.debug("Inserted combined selector ({}) into ruleset",  cslist.size());
            	             }   
            	            

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            pushFollow(FOLLOW_declarations_in_ruleset488);
            decl=declarations();

            state._fsp--;


            match(input, Token.UP, null); 

            }


                if(((statement_scope)statement_stack.peek()).invalid) {
                    stmnt = null;
                    log.debug("Ruleset not valid, so not created");
                }
                else {    
            		 stmnt = preparator.prepareRuleSet(cslist, decl, 
            		 	!((statement_scope)statement_stack.peek()).insideAtstatement && !imports.isEmpty() && imports.peek().doWrap(), 
            			imports.isEmpty() ? null : imports.peek().media);
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:274:1: declarations returns [List<Declaration> decl] : ^( SET (d= declaration )* ) ;
    public final List<Declaration> declarations() throws RecognitionException {
        List<Declaration> decl = null;

        Declaration d = null;



        		  logEnter("declarations");
        		  decl = new ArrayList<Declaration>();

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:285:2: ( ^( SET (d= declaration )* ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:285:4: ^( SET (d= declaration )* )
            {
            match(input,SET,FOLLOW_SET_in_declarations529); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:285:10: (d= declaration )*
                loop12:
                do {
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==DECLARATION||LA12_0==INVALID_DECLARATION) ) {
                        alt12=1;
                    }


                    switch (alt12) {
                	case 1 :
                	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:285:11: d= declaration
                	    {
                	    pushFollow(FOLLOW_declaration_in_declarations534);
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:295:1: declaration returns [Declaration decl] : ( ^( DECLARATION ( important )? property t= terms ) | INVALID_DECLARATION );
    public final Declaration declaration() throws RecognitionException {
        declaration_stack.push(new declaration_scope());
        Declaration decl = null;

        List<Term<?>> t = null;



            logEnter("declaration");
            ((declaration_scope)declaration_stack.peek()).d = decl = rf.createDeclaration();
            ((declaration_scope)declaration_stack.peek()).invalid = false;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:318:3: ( ^( DECLARATION ( important )? property t= terms ) | INVALID_DECLARATION )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:318:5: ^( DECLARATION ( important )? property t= terms )
                    {
                    match(input,DECLARATION,FOLLOW_DECLARATION_in_declaration578); 

                    match(input, Token.DOWN, null); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:319:4: ( important )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==IMPORTANT) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:319:5: important
                            {
                            pushFollow(FOLLOW_important_in_declaration585);
                            important();

                            state._fsp--;

                             decl.setImportant(true);

                            }
                            break;

                    }

                    pushFollow(FOLLOW_property_in_declaration597);
                    property();

                    state._fsp--;

                    pushFollow(FOLLOW_terms_in_declaration608);
                    t=terms();

                    state._fsp--;

                    decl.replaceAll(t);

                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:323:5: INVALID_DECLARATION
                    {
                    match(input,INVALID_DECLARATION,FOLLOW_INVALID_DECLARATION_in_declaration629); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:326:1: important : IMPORTANT ;
    public final void important() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:327:5: ( IMPORTANT )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:327:7: IMPORTANT
            {
            match(input,IMPORTANT,FOLLOW_IMPORTANT_in_important646); 

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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:330:1: property : (i= IDENT | MINUS i= IDENT );
    public final void property() throws RecognitionException {
        CommonTree i=null;


            logEnter("property");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:341:3: (i= IDENT | MINUS i= IDENT )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==IDENT) ) {
                alt15=1;
            }
            else if ( (LA15_0==MINUS) ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:341:5: i= IDENT
                    {
                    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_property686); 
                     ((declaration_scope)declaration_stack.peek()).d.setProperty(extractText(i)); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:342:5: MINUS i= IDENT
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_property694); 
                    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_property700); 
                     ((declaration_scope)declaration_stack.peek()).d.setProperty("-" + extractText(i)); 

                    }
                    break;

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
        boolean dash;
    }
    protected Stack terms_stack = new Stack();


    // $ANTLR start "terms"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:345:1: terms returns [List<Term<?>> tlist] : ^( VALUE ( term )+ ) ;
    public final List<Term<?>> terms() throws RecognitionException {
        terms_stack.push(new terms_scope());
        List<Term<?>> tlist = null;


            logEnter("terms");
            ((terms_scope)terms_stack.peek()).list = tlist = new ArrayList<Term<?>>();
            ((terms_scope)terms_stack.peek()).term = null;
            ((terms_scope)terms_stack.peek()).op = null;
            ((terms_scope)terms_stack.peek()).unary = 1;
            ((terms_scope)terms_stack.peek()).dash = false;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:368:5: ( ^( VALUE ( term )+ ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:368:7: ^( VALUE ( term )+ )
            {
            match(input,VALUE,FOLLOW_VALUE_in_terms745); 

            match(input, Token.DOWN, null); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:368:15: ( term )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>=CURLYBLOCK && LA16_0<=BRACEBLOCK)||LA16_0==INVALID_STRING||(LA16_0>=COLON && LA16_0<=IDENT)||(LA16_0>=ATKEYWORD && LA16_0<=COMMA)||(LA16_0>=MINUS && LA16_0<=FUNCTION)||(LA16_0>=CLASSKEYWORD && LA16_0<=DASHMATCH)||LA16_0==STRING) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:368:15: term
            	    {
            	    pushFollow(FOLLOW_term_in_terms747);
            	    term();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt16 >= 1 ) break loop16;
                        EarlyExitException eee =
                            new EarlyExitException(16, input);
                        throw eee;
                }
                cnt16++;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:371:1: term : ( valuepart | CURLYBLOCK | ATKEYWORD );
    public final void term() throws RecognitionException {

          logEnter("term");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:375:5: ( valuepart | CURLYBLOCK | ATKEYWORD )
            int alt17=3;
            switch ( input.LA(1) ) {
            case PARENBLOCK:
            case BRACEBLOCK:
            case INVALID_STRING:
            case COLON:
            case IDENT:
            case COMMA:
            case MINUS:
            case EXPRESSION:
            case FUNCTION:
            case CLASSKEYWORD:
            case NUMBER:
            case PERCENTAGE:
            case DIMENSION:
            case URI:
            case HASH:
            case UNIRANGE:
            case INCLUDES:
            case GREATER:
            case LESS:
            case QUESTION:
            case PERCENT:
            case EQUALS:
            case SLASH:
            case PLUS:
            case ASTERISK:
            case DASHMATCH:
            case STRING:
                {
                alt17=1;
                }
                break;
            case CURLYBLOCK:
                {
                alt17=2;
                }
                break;
            case ATKEYWORD:
                {
                alt17=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:375:7: valuepart
                    {
                    pushFollow(FOLLOW_valuepart_in_term775);
                    valuepart();

                    state._fsp--;

                    // set operator, store and create next 
                           if(!((declaration_scope)declaration_stack.peek()).invalid && ((terms_scope)terms_stack.peek()).term!=null) {
                              ((terms_scope)terms_stack.peek()).term.setOperator(((terms_scope)terms_stack.peek()).op);
                              ((terms_scope)terms_stack.peek()).list.add(((terms_scope)terms_stack.peek()).term);
                              // reinitialization
                              ((terms_scope)terms_stack.peek()).op = Term.Operator.SPACE;
                              ((terms_scope)terms_stack.peek()).unary = 1;
                              ((terms_scope)terms_stack.peek()).dash = false;
                              ((terms_scope)terms_stack.peek()).term = null;
                           }    
                          

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:387:7: CURLYBLOCK
                    {
                    match(input,CURLYBLOCK,FOLLOW_CURLYBLOCK_in_term792); 
                     ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:388:7: ATKEYWORD
                    {
                    match(input,ATKEYWORD,FOLLOW_ATKEYWORD_in_term802); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:391:1: valuepart : ( ( MINUS )? i= IDENT | CLASSKEYWORD | ( MINUS )? n= NUMBER | ( MINUS )? p= PERCENTAGE | ( MINUS )? d= DIMENSION | s= string | u= URI | h= HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | LESS | QUESTION | PERCENT | EQUALS | SLASH | PLUS | ASTERISK | e= EXPRESSION | ^(f= FUNCTION (t= terms )? ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) );
    public final void valuepart() throws RecognitionException {
        CommonTree i=null;
        CommonTree n=null;
        CommonTree p=null;
        CommonTree d=null;
        CommonTree u=null;
        CommonTree h=null;
        CommonTree e=null;
        CommonTree f=null;
        String s = null;

        List<Term<?>> t = null;


        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:413:5: ( ( MINUS )? i= IDENT | CLASSKEYWORD | ( MINUS )? n= NUMBER | ( MINUS )? p= PERCENTAGE | ( MINUS )? d= DIMENSION | s= string | u= URI | h= HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | LESS | QUESTION | PERCENT | EQUALS | SLASH | PLUS | ASTERISK | e= EXPRESSION | ^(f= FUNCTION (t= terms )? ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) )
            int alt25=25;
            alt25 = dfa25.predict(input);
            switch (alt25) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:413:7: ( MINUS )? i= IDENT
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:413:7: ( MINUS )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==MINUS) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:413:8: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart829); 
                            ((terms_scope)terms_stack.peek()).dash =true;

                            }
                            break;

                    }

                    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_valuepart837); 
                    ((terms_scope)terms_stack.peek()).term = tf.createIdent(extractText(i), ((terms_scope)terms_stack.peek()).dash);

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:414:7: CLASSKEYWORD
                    {
                    match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_valuepart849); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:415:6: ( MINUS )? n= NUMBER
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:415:6: ( MINUS )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==MINUS) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:415:7: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart859); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    n=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_valuepart867); 
                    ((terms_scope)terms_stack.peek()).term = tf.createNumeric(extractText(n), ((terms_scope)terms_stack.peek()).unary);

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:416:7: ( MINUS )? p= PERCENTAGE
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:416:7: ( MINUS )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==MINUS) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:416:8: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart881); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    p=(CommonTree)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_valuepart889); 
                     ((terms_scope)terms_stack.peek()).term = tf.createPercent(extractText(p), ((terms_scope)terms_stack.peek()).unary);

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:417:7: ( MINUS )? d= DIMENSION
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:417:7: ( MINUS )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==MINUS) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:417:8: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart901); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    d=(CommonTree)match(input,DIMENSION,FOLLOW_DIMENSION_in_valuepart909); 
                    String dim = extractText(d);
                    				 ((terms_scope)terms_stack.peek()).term = tf.createDimension(dim, ((terms_scope)terms_stack.peek()).unary);
                    			     if(((terms_scope)terms_stack.peek()).term==null) {
                    					 log.info("Unable to create dimension from {}, unary {}", dim, ((terms_scope)terms_stack.peek()).unary);
                    			         ((declaration_scope)declaration_stack.peek()).invalid = true;
                    				 }
                    	    

                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:425:7: s= string
                    {
                    pushFollow(FOLLOW_string_in_valuepart927);
                    s=string();

                    state._fsp--;

                     if(s!=null) ((terms_scope)terms_stack.peek()).term = tf.createString(s);
                    			  else ((declaration_scope)declaration_stack.peek()).invalid =true;
                    			

                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:429:7: u= URI
                    {
                    u=(CommonTree)match(input,URI,FOLLOW_URI_in_valuepart946); 
                    ((terms_scope)terms_stack.peek()).term = tf.createURI(extractText(u));

                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:430:7: h= HASH
                    {
                    h=(CommonTree)match(input,HASH,FOLLOW_HASH_in_valuepart964); 
                    ((terms_scope)terms_stack.peek()).term = tf.createColor(extractText(h));
                    	     if(((terms_scope)terms_stack.peek()).term==null)
                    	         ((declaration_scope)declaration_stack.peek()).invalid = true;
                    	    

                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:435:7: UNIRANGE
                    {
                    match(input,UNIRANGE,FOLLOW_UNIRANGE_in_valuepart983); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:436:7: INCLUDES
                    {
                    match(input,INCLUDES,FOLLOW_INCLUDES_in_valuepart994); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:437:7: COLON
                    {
                    match(input,COLON,FOLLOW_COLON_in_valuepart1005); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:438:7: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_valuepart1019); 
                    ((terms_scope)terms_stack.peek()).op = Term.Operator.COMMA;

                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:439:7: GREATER
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_valuepart1037); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:440:7: LESS
                    {
                    match(input,LESS,FOLLOW_LESS_in_valuepart1049); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:441:7: QUESTION
                    {
                    match(input,QUESTION,FOLLOW_QUESTION_in_valuepart1064); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:442:7: PERCENT
                    {
                    match(input,PERCENT,FOLLOW_PERCENT_in_valuepart1075); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:443:7: EQUALS
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_valuepart1087); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 18 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:444:7: SLASH
                    {
                    match(input,SLASH,FOLLOW_SLASH_in_valuepart1100); 
                    ((terms_scope)terms_stack.peek()).op = Term.Operator.SLASH;

                    }
                    break;
                case 19 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:445:5: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_valuepart1112); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 20 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:446:5: ASTERISK
                    {
                    match(input,ASTERISK,FOLLOW_ASTERISK_in_valuepart1123); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 21 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:447:5: e= EXPRESSION
                    {
                    e=(CommonTree)match(input,EXPRESSION,FOLLOW_EXPRESSION_in_valuepart1134); 

                    		    String exprval = extractText(e);
                            TermExpression expr = tf.createExpression(exprval.substring(11,exprval.length()-1)); //strip the 'expression()'
                            ((terms_scope)terms_stack.peek()).term = expr;
                    		

                    }
                    break;
                case 22 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:452:7: ^(f= FUNCTION (t= terms )? )
                    {
                    f=(CommonTree)match(input,FUNCTION,FOLLOW_FUNCTION_in_valuepart1147); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:452:21: (t= terms )?
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==VALUE) ) {
                            alt22=1;
                        }
                        switch (alt22) {
                            case 1 :
                                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:452:21: t= terms
                                {
                                pushFollow(FOLLOW_terms_in_valuepart1151);
                                t=terms();

                                state._fsp--;


                                }
                                break;

                        }


                        match(input, Token.UP, null); 
                    }

                            // create function
                            TermFunction function = tf.createFunction();
                            function.setFunctionName(extractText(f));
                            if (t != null)
                            	function.setValue(t);
                            ((terms_scope)terms_stack.peek()).term = function;
                        

                    }
                    break;
                case 23 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:460:7: DASHMATCH
                    {
                    match(input,DASHMATCH,FOLLOW_DASHMATCH_in_valuepart1163); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 24 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:461:7: ^( PARENBLOCK ( any )* )
                    {
                    match(input,PARENBLOCK,FOLLOW_PARENBLOCK_in_valuepart1174); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:461:20: ( any )*
                        loop23:
                        do {
                            int alt23=2;
                            int LA23_0 = input.LA(1);

                            if ( ((LA23_0>=PARENBLOCK && LA23_0<=BRACEBLOCK)||LA23_0==INVALID_STRING||(LA23_0>=COLON && LA23_0<=IDENT)||LA23_0==COMMA||LA23_0==EXCLAMATION||LA23_0==FUNCTION||(LA23_0>=CLASSKEYWORD && LA23_0<=GREATER)||(LA23_0>=EQUALS && LA23_0<=SLASH)||LA23_0==DASHMATCH||LA23_0==STRING) ) {
                                alt23=1;
                            }


                            switch (alt23) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:461:20: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_valuepart1176);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop23;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 25 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:462:7: ^( BRACEBLOCK ( any )* )
                    {
                    match(input,BRACEBLOCK,FOLLOW_BRACEBLOCK_in_valuepart1189); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:462:20: ( any )*
                        loop24:
                        do {
                            int alt24=2;
                            int LA24_0 = input.LA(1);

                            if ( ((LA24_0>=PARENBLOCK && LA24_0<=BRACEBLOCK)||LA24_0==INVALID_STRING||(LA24_0>=COLON && LA24_0<=IDENT)||LA24_0==COMMA||LA24_0==EXCLAMATION||LA24_0==FUNCTION||(LA24_0>=CLASSKEYWORD && LA24_0<=GREATER)||(LA24_0>=EQUALS && LA24_0<=SLASH)||LA24_0==DASHMATCH||LA24_0==STRING) ) {
                                alt24=1;
                            }


                            switch (alt24) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:462:20: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_valuepart1191);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop24;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:465:1: combined_selector returns [CombinedSelector combinedSelector] : s= selector (c= combinator s= selector )* ;
    public final CombinedSelector combined_selector() throws RecognitionException {
        combined_selector_stack.push(new combined_selector_scope());
        CombinedSelector combinedSelector = null;

        Selector s = null;

        Selector.Combinator c = null;



        	logEnter("combined_selector");	  
        	combinedSelector = (CombinedSelector) rf.createCombinedSelector().unlock();

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:495:2: (s= selector (c= combinator s= selector )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:495:4: s= selector (c= combinator s= selector )*
            {
            pushFollow(FOLLOW_selector_in_combined_selector1239);
            s=selector();

            state._fsp--;


            	     combinedSelector.add(s);
            	  
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:498:3: (c= combinator s= selector )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>=ADJACENT && LA26_0<=DESCENDANT)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:498:4: c= combinator s= selector
            	    {
            	    pushFollow(FOLLOW_combinator_in_combined_selector1248);
            	    c=combinator();

            	    state._fsp--;

            	    pushFollow(FOLLOW_selector_in_combined_selector1252);
            	    s=selector();

            	    state._fsp--;


            	    	     s.setCombinator(c);
            	    	     combinedSelector.add(s);	
            	    	  

            	    }
            	    break;

            	default :
            	    break loop26;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:505:1: combinator returns [Selector.Combinator combinator] : ( CHILD | ADJACENT | DESCENDANT );
    public final Selector.Combinator combinator() throws RecognitionException {
        Selector.Combinator combinator = null;

         logEnter("combinator"); 
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:508:2: ( CHILD | ADJACENT | DESCENDANT )
            int alt27=3;
            switch ( input.LA(1) ) {
            case CHILD:
                {
                alt27=1;
                }
                break;
            case ADJACENT:
                {
                alt27=2;
                }
                break;
            case DESCENDANT:
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:508:4: CHILD
                    {
                    match(input,CHILD,FOLLOW_CHILD_in_combinator1282); 
                    combinator =Selector.Combinator.CHILD;

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:509:4: ADJACENT
                    {
                    match(input,ADJACENT,FOLLOW_ADJACENT_in_combinator1289); 
                    combinator =Selector.Combinator.ADJACENT;

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:510:4: DESCENDANT
                    {
                    match(input,DESCENDANT,FOLLOW_DESCENDANT_in_combinator1296); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:514:1: selector returns [Selector sel] : ( ^( SELECTOR ^( ELEMENT (i= IDENT )? ) ( selpart )* ) | ^( SELECTOR ( selpart )+ ) | INVALID_SELECTOR );
    public final Selector selector() throws RecognitionException {
        selector_stack.push(new selector_scope());
        Selector sel = null;

        CommonTree i=null;


        	logEnter("selector");
        	((selector_scope)selector_stack.peek()).s =sel=(Selector)rf.createSelector().unlock();
        	Selector.ElementName en = rf.createElement(Selector.ElementName.WILDCARD);

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:526:5: ( ^( SELECTOR ^( ELEMENT (i= IDENT )? ) ( selpart )* ) | ^( SELECTOR ( selpart )+ ) | INVALID_SELECTOR )
            int alt31=3;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==SELECTOR) ) {
                int LA31_1 = input.LA(2);

                if ( (LA31_1==DOWN) ) {
                    int LA31_3 = input.LA(3);

                    if ( (LA31_3==ELEMENT) ) {
                        alt31=1;
                    }
                    else if ( (LA31_3==PSEUDO||LA31_3==ATTRIBUTE||LA31_3==INVALID_SELPART||LA31_3==CLASSKEYWORD||LA31_3==HASH) ) {
                        alt31=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 31, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 31, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA31_0==INVALID_SELECTOR) ) {
                alt31=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:526:7: ^( SELECTOR ^( ELEMENT (i= IDENT )? ) ( selpart )* )
                    {
                    match(input,SELECTOR,FOLLOW_SELECTOR_in_selector1332); 

                    match(input, Token.DOWN, null); 
                    match(input,ELEMENT,FOLLOW_ELEMENT_in_selector1344); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:528:11: (i= IDENT )?
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==IDENT) ) {
                            alt28=1;
                        }
                        switch (alt28) {
                            case 1 :
                                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:528:12: i= IDENT
                                {
                                i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_selector1360); 
                                 en.setName(extractText(i)); 

                                }
                                break;

                        }


                        match(input, Token.UP, null); 
                    }

                    		  log.debug("Adding element name: {}.", en.getName());
                    		  ((selector_scope)selector_stack.peek()).s.add(en);
                    		 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:534:10: ( selpart )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==PSEUDO||LA29_0==ATTRIBUTE||LA29_0==INVALID_SELPART||LA29_0==CLASSKEYWORD||LA29_0==HASH) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:534:10: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1407);
                    	    selpart();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:536:7: ^( SELECTOR ( selpart )+ )
                    {
                    match(input,SELECTOR,FOLLOW_SELECTOR_in_selector1426); 

                    match(input, Token.DOWN, null); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:537:10: ( selpart )+
                    int cnt30=0;
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==PSEUDO||LA30_0==ATTRIBUTE||LA30_0==INVALID_SELPART||LA30_0==CLASSKEYWORD||LA30_0==HASH) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:537:10: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1438);
                    	    selpart();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt30 >= 1 ) break loop30;
                                EarlyExitException eee =
                                    new EarlyExitException(30, input);
                                throw eee;
                        }
                        cnt30++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:539:7: INVALID_SELECTOR
                    {
                    match(input,INVALID_SELECTOR,FOLLOW_INVALID_SELECTOR_in_selector1456); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:542:1: selpart : (h= HASH | c= CLASSKEYWORD | ^( ATTRIBUTE ea= attribute ) | p= pseudo | INVALID_SELPART );
    public final void selpart() throws RecognitionException {
        CommonTree h=null;
        CommonTree c=null;
        Selector.ElementAttribute ea = null;

        Selector.PseudoPage p = null;



        	logEnter("selpart");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:549:5: (h= HASH | c= CLASSKEYWORD | ^( ATTRIBUTE ea= attribute ) | p= pseudo | INVALID_SELPART )
            int alt32=5;
            switch ( input.LA(1) ) {
            case HASH:
                {
                alt32=1;
                }
                break;
            case CLASSKEYWORD:
                {
                alt32=2;
                }
                break;
            case ATTRIBUTE:
                {
                alt32=3;
                }
                break;
            case PSEUDO:
                {
                alt32=4;
                }
                break;
            case INVALID_SELPART:
                {
                alt32=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }

            switch (alt32) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:549:8: h= HASH
                    {
                    h=(CommonTree)match(input,HASH,FOLLOW_HASH_in_selpart1490); 
                     ((selector_scope)selector_stack.peek()).s.add(rf.createID(extractText(h))); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:550:7: c= CLASSKEYWORD
                    {
                    c=(CommonTree)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_selpart1502); 
                     ((selector_scope)selector_stack.peek()).s.add(rf.createClass(extractText(c))); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:551:4: ^( ATTRIBUTE ea= attribute )
                    {
                    match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_selpart1510); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_attribute_in_selpart1514);
                    ea=attribute();

                    state._fsp--;

                     ((selector_scope)selector_stack.peek()).s.add(ea);

                    match(input, Token.UP, null); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:552:7: p= pseudo
                    {
                    pushFollow(FOLLOW_pseudo_in_selpart1528);
                    p=pseudo();

                    state._fsp--;

                     ((selector_scope)selector_stack.peek()).s.add(p);

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:553:4: INVALID_SELPART
                    {
                    match(input,INVALID_SELPART,FOLLOW_INVALID_SELPART_in_selpart1535); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:556:1: attribute returns [Selector.ElementAttribute elemAttr] : i= IDENT ( ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string ) )? ;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:574:2: (i= IDENT ( ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string ) )? )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:574:4: i= IDENT ( ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string ) )?
            {
            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_attribute1569); 
            attribute=extractText(i); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:575:4: ( ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string ) )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==INCLUDES||LA35_0==EQUALS||LA35_0==DASHMATCH) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:575:5: ( EQUALS | INCLUDES | DASHMATCH ) (i= IDENT | s= string )
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:575:5: ( EQUALS | INCLUDES | DASHMATCH )
                    int alt33=3;
                    switch ( input.LA(1) ) {
                    case EQUALS:
                        {
                        alt33=1;
                        }
                        break;
                    case INCLUDES:
                        {
                        alt33=2;
                        }
                        break;
                    case DASHMATCH:
                        {
                        alt33=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 33, 0, input);

                        throw nvae;
                    }

                    switch (alt33) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:575:6: EQUALS
                            {
                            match(input,EQUALS,FOLLOW_EQUALS_in_attribute1578); 
                            op=Selector.Operator.EQUALS; 

                            }
                            break;
                        case 2 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:576:7: INCLUDES
                            {
                            match(input,INCLUDES,FOLLOW_INCLUDES_in_attribute1589); 
                            op=Selector.Operator.INCLUDES; 

                            }
                            break;
                        case 3 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:577:7: DASHMATCH
                            {
                            match(input,DASHMATCH,FOLLOW_DASHMATCH_in_attribute1600); 
                            op=Selector.Operator.DASHMATCH; 

                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:579:5: (i= IDENT | s= string )
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==IDENT) ) {
                        alt34=1;
                    }
                    else if ( (LA34_0==INVALID_STRING||LA34_0==STRING) ) {
                        alt34=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 34, 0, input);

                        throw nvae;
                    }
                    switch (alt34) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:579:6: i= IDENT
                            {
                            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_attribute1618); 

                            		value=extractText(i);
                            		isStringValue=false;
                            		

                            }
                            break;
                        case 2 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:583:7: s= string
                            {
                            pushFollow(FOLLOW_string_in_attribute1630);
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:595:1: pseudo returns [Selector.PseudoPage pseudoPage] : ^( PSEUDO (f= FUNCTION )? i= IDENT ) ;
    public final Selector.PseudoPage pseudo() throws RecognitionException {
        Selector.PseudoPage pseudoPage = null;

        CommonTree f=null;
        CommonTree i=null;


        		  logEnter("pseudo");
        		  String fname =null;
        		  String value = null;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:601:2: ( ^( PSEUDO (f= FUNCTION )? i= IDENT ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:601:4: ^( PSEUDO (f= FUNCTION )? i= IDENT )
            {
            match(input,PSEUDO,FOLLOW_PSEUDO_in_pseudo1663); 

            match(input, Token.DOWN, null); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:602:8: (f= FUNCTION )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==FUNCTION) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:602:9: f= FUNCTION
                    {
                    f=(CommonTree)match(input,FUNCTION,FOLLOW_FUNCTION_in_pseudo1676); 
                    fname=extractText(f);

                    }
                    break;

            }

            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_pseudo1693); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:609:1: string returns [String s] : (st= STRING | INVALID_STRING );
    public final String string() throws RecognitionException {
        String s = null;

        CommonTree st=null;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:610:2: (st= STRING | INVALID_STRING )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==STRING) ) {
                alt37=1;
            }
            else if ( (LA37_0==INVALID_STRING) ) {
                alt37=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:610:4: st= STRING
                    {
                    st=(CommonTree)match(input,STRING,FOLLOW_STRING_in_string1718); 
                     s = extractText(st);

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:611:4: INVALID_STRING
                    {
                    match(input,INVALID_STRING,FOLLOW_INVALID_STRING_in_string1725); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:614:1: any : ( IDENT | CLASSKEYWORD | NUMBER | PERCENTAGE | DIMENSION | string | URI | HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | EXCLAMATION | ^( FUNCTION ( any )* ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) );
    public final void any() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:615:3: ( IDENT | CLASSKEYWORD | NUMBER | PERCENTAGE | DIMENSION | string | URI | HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | EXCLAMATION | ^( FUNCTION ( any )* ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) )
            int alt41=20;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt41=1;
                }
                break;
            case CLASSKEYWORD:
                {
                alt41=2;
                }
                break;
            case NUMBER:
                {
                alt41=3;
                }
                break;
            case PERCENTAGE:
                {
                alt41=4;
                }
                break;
            case DIMENSION:
                {
                alt41=5;
                }
                break;
            case INVALID_STRING:
            case STRING:
                {
                alt41=6;
                }
                break;
            case URI:
                {
                alt41=7;
                }
                break;
            case HASH:
                {
                alt41=8;
                }
                break;
            case UNIRANGE:
                {
                alt41=9;
                }
                break;
            case INCLUDES:
                {
                alt41=10;
                }
                break;
            case COLON:
                {
                alt41=11;
                }
                break;
            case COMMA:
                {
                alt41=12;
                }
                break;
            case GREATER:
                {
                alt41=13;
                }
                break;
            case EQUALS:
                {
                alt41=14;
                }
                break;
            case SLASH:
                {
                alt41=15;
                }
                break;
            case EXCLAMATION:
                {
                alt41=16;
                }
                break;
            case FUNCTION:
                {
                alt41=17;
                }
                break;
            case DASHMATCH:
                {
                alt41=18;
                }
                break;
            case PARENBLOCK:
                {
                alt41=19;
                }
                break;
            case BRACEBLOCK:
                {
                alt41=20;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }

            switch (alt41) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:615:5: IDENT
                    {
                    match(input,IDENT,FOLLOW_IDENT_in_any1741); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:616:5: CLASSKEYWORD
                    {
                    match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_any1747); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:617:5: NUMBER
                    {
                    match(input,NUMBER,FOLLOW_NUMBER_in_any1753); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:618:5: PERCENTAGE
                    {
                    match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_any1759); 

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:619:5: DIMENSION
                    {
                    match(input,DIMENSION,FOLLOW_DIMENSION_in_any1765); 

                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:620:5: string
                    {
                    pushFollow(FOLLOW_string_in_any1771);
                    string();

                    state._fsp--;


                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:621:5: URI
                    {
                    match(input,URI,FOLLOW_URI_in_any1777); 

                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:622:5: HASH
                    {
                    match(input,HASH,FOLLOW_HASH_in_any1783); 

                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:623:5: UNIRANGE
                    {
                    match(input,UNIRANGE,FOLLOW_UNIRANGE_in_any1789); 

                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:624:5: INCLUDES
                    {
                    match(input,INCLUDES,FOLLOW_INCLUDES_in_any1795); 

                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:625:5: COLON
                    {
                    match(input,COLON,FOLLOW_COLON_in_any1801); 

                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:626:5: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_any1807); 

                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:627:5: GREATER
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_any1813); 

                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:628:5: EQUALS
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_any1819); 

                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:629:5: SLASH
                    {
                    match(input,SLASH,FOLLOW_SLASH_in_any1825); 

                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:630:5: EXCLAMATION
                    {
                    match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_any1831); 

                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:631:5: ^( FUNCTION ( any )* )
                    {
                    match(input,FUNCTION,FOLLOW_FUNCTION_in_any1838); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:631:16: ( any )*
                        loop38:
                        do {
                            int alt38=2;
                            int LA38_0 = input.LA(1);

                            if ( ((LA38_0>=PARENBLOCK && LA38_0<=BRACEBLOCK)||LA38_0==INVALID_STRING||(LA38_0>=COLON && LA38_0<=IDENT)||LA38_0==COMMA||LA38_0==EXCLAMATION||LA38_0==FUNCTION||(LA38_0>=CLASSKEYWORD && LA38_0<=GREATER)||(LA38_0>=EQUALS && LA38_0<=SLASH)||LA38_0==DASHMATCH||LA38_0==STRING) ) {
                                alt38=1;
                            }


                            switch (alt38) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:631:16: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_any1840);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop38;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }

                    }
                    break;
                case 18 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:632:5: DASHMATCH
                    {
                    match(input,DASHMATCH,FOLLOW_DASHMATCH_in_any1849); 

                    }
                    break;
                case 19 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:633:5: ^( PARENBLOCK ( any )* )
                    {
                    match(input,PARENBLOCK,FOLLOW_PARENBLOCK_in_any1856); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:633:18: ( any )*
                        loop39:
                        do {
                            int alt39=2;
                            int LA39_0 = input.LA(1);

                            if ( ((LA39_0>=PARENBLOCK && LA39_0<=BRACEBLOCK)||LA39_0==INVALID_STRING||(LA39_0>=COLON && LA39_0<=IDENT)||LA39_0==COMMA||LA39_0==EXCLAMATION||LA39_0==FUNCTION||(LA39_0>=CLASSKEYWORD && LA39_0<=GREATER)||(LA39_0>=EQUALS && LA39_0<=SLASH)||LA39_0==DASHMATCH||LA39_0==STRING) ) {
                                alt39=1;
                            }


                            switch (alt39) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:633:18: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_any1858);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop39;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }

                    }
                    break;
                case 20 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:634:5: ^( BRACEBLOCK ( any )* )
                    {
                    match(input,BRACEBLOCK,FOLLOW_BRACEBLOCK_in_any1867); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:634:18: ( any )*
                        loop40:
                        do {
                            int alt40=2;
                            int LA40_0 = input.LA(1);

                            if ( ((LA40_0>=PARENBLOCK && LA40_0<=BRACEBLOCK)||LA40_0==INVALID_STRING||(LA40_0>=COLON && LA40_0<=IDENT)||LA40_0==COMMA||LA40_0==EXCLAMATION||LA40_0==FUNCTION||(LA40_0>=CLASSKEYWORD && LA40_0<=GREATER)||(LA40_0>=EQUALS && LA40_0<=SLASH)||LA40_0==DASHMATCH||LA40_0==STRING) ) {
                                alt40=1;
                            }


                            switch (alt40) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:634:18: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_any1869);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop40;
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


    protected DFA25 dfa25 = new DFA25(this);
    static final String DFA25_eotS =
        "\33\uffff";
    static final String DFA25_eofS =
        "\33\uffff";
    static final String DFA25_minS =
        "\1\10\1\44\31\uffff";
    static final String DFA25_maxS =
        "\1\104\1\63\31\uffff";
    static final String DFA25_acceptS =
        "\2\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31";
    static final String DFA25_specialS =
        "\33\uffff}>";
    static final String[] DFA25_transitionS = {
            "\1\31\1\32\15\uffff\1\7\13\uffff\1\14\1\2\4\uffff\1\15\2\uffff"+
            "\1\1\1\26\1\27\1\uffff\1\3\1\4\1\5\1\6\1\10\1\11\1\12\1\13\1"+
            "\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\30\3\uffff\1\7",
            "\1\2\14\uffff\1\4\1\5\1\6",
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
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA25_eot = DFA.unpackEncodedString(DFA25_eotS);
    static final short[] DFA25_eof = DFA.unpackEncodedString(DFA25_eofS);
    static final char[] DFA25_min = DFA.unpackEncodedStringToUnsignedChars(DFA25_minS);
    static final char[] DFA25_max = DFA.unpackEncodedStringToUnsignedChars(DFA25_maxS);
    static final short[] DFA25_accept = DFA.unpackEncodedString(DFA25_acceptS);
    static final short[] DFA25_special = DFA.unpackEncodedString(DFA25_specialS);
    static final short[][] DFA25_transition;

    static {
        int numStates = DFA25_transitionS.length;
        DFA25_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA25_transition[i] = DFA.unpackEncodedString(DFA25_transitionS[i]);
        }
    }

    class DFA25 extends DFA {

        public DFA25(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 25;
            this.eot = DFA25_eot;
            this.eof = DFA25_eof;
            this.min = DFA25_min;
            this.max = DFA25_max;
            this.accept = DFA25_accept;
            this.special = DFA25_special;
            this.transition = DFA25_transition;
        }
        public String getDescription() {
            return "391:1: valuepart : ( ( MINUS )? i= IDENT | CLASSKEYWORD | ( MINUS )? n= NUMBER | ( MINUS )? p= PERCENTAGE | ( MINUS )? d= DIMENSION | s= string | u= URI | h= HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | LESS | QUESTION | PERCENT | EQUALS | SLASH | PLUS | ASTERISK | e= EXPRESSION | ^(f= FUNCTION (t= terms )? ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) );";
        }
    }
 

    public static final BitSet FOLLOW_INLINESTYLE_in_inlinestyle59 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_declarations_in_inlinestyle63 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INLINESTYLE_in_inlinestyle78 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_inlineset_in_inlinestyle88 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_STYLESHEET_in_stylesheet125 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_stylesheet134 = new BitSet(new long[]{0x0000008718400408L});
    public static final BitSet FOLLOW_ruleset_in_statement183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atstatement_in_statement193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARSET_in_atstatement226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_IMPORT_in_atstatement232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_atstatement240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_END_in_atstatement251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PAGE_in_atstatement259 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_atstatement264 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_declarations_in_atstatement271 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MEDIA_in_atstatement282 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_media_in_atstatement287 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_ruleset_in_atstatement298 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_INVALID_STATEMENT_in_atstatement320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_media352 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_RULE_in_inlineset387 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_pseudo_in_inlineset392 = new BitSet(new long[]{0x0000000000042000L});
    public static final BitSet FOLLOW_declarations_in_inlineset400 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RULE_in_ruleset453 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_combined_selector_in_ruleset467 = new BitSet(new long[]{0x0000000001040800L});
    public static final BitSet FOLLOW_declarations_in_ruleset488 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SET_in_declarations529 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_declaration_in_declarations534 = new BitSet(new long[]{0x0000000004080008L});
    public static final BitSet FOLLOW_DECLARATION_in_declaration578 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_important_in_declaration585 = new BitSet(new long[]{0x0000101000000000L});
    public static final BitSet FOLLOW_property_in_declaration597 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_terms_in_declaration608 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INVALID_DECLARATION_in_declaration629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORTANT_in_important646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_property686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_property694 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_IDENT_in_property700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_terms745 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_term_in_terms747 = new BitSet(new long[]{0xFFFF731800800388L,0x0000000000000011L});
    public static final BitSet FOLLOW_valuepart_in_term775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CURLYBLOCK_in_term792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATKEYWORD_in_term802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart829 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_IDENT_in_valuepart837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_valuepart849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart859 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_NUMBER_in_valuepart867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart881 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_PERCENTAGE_in_valuepart889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart901 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_DIMENSION_in_valuepart909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_valuepart927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_URI_in_valuepart946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_valuepart964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_valuepart983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCLUDES_in_valuepart994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_valuepart1005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_valuepart1019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_valuepart1037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_valuepart1049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_valuepart1064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENT_in_valuepart1075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_valuepart1087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_valuepart1100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_valuepart1112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASTERISK_in_valuepart1123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPRESSION_in_valuepart1134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_valuepart1147 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_terms_in_valuepart1151 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DASHMATCH_in_valuepart1163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENBLOCK_in_valuepart1174 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_valuepart1176 = new BitSet(new long[]{0x31FF4A1800800308L,0x0000000000000011L});
    public static final BitSet FOLLOW_BRACEBLOCK_in_valuepart1189 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_valuepart1191 = new BitSet(new long[]{0x31FF4A1800800308L,0x0000000000000011L});
    public static final BitSet FOLLOW_selector_in_combined_selector1239 = new BitSet(new long[]{0x000000000001C002L});
    public static final BitSet FOLLOW_combinator_in_combined_selector1248 = new BitSet(new long[]{0x0000000001040800L});
    public static final BitSet FOLLOW_selector_in_combined_selector1252 = new BitSet(new long[]{0x000000000001C002L});
    public static final BitSet FOLLOW_CHILD_in_combinator1282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADJACENT_in_combinator1289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DESCENDANT_in_combinator1296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELECTOR_in_selector1332 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ELEMENT_in_selector1344 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_selector1360 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_selpart_in_selector1407 = new BitSet(new long[]{0x0021000002062008L});
    public static final BitSet FOLLOW_SELECTOR_in_selector1426 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_selpart_in_selector1438 = new BitSet(new long[]{0x0021000002062008L});
    public static final BitSet FOLLOW_INVALID_SELECTOR_in_selector1456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_selpart1490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_selpart1502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_selpart1510 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_attribute_in_selpart1514 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_pseudo_in_selpart1528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_SELPART_in_selpart1535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_attribute1569 = new BitSet(new long[]{0x1080000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_EQUALS_in_attribute1578 = new BitSet(new long[]{0x0000001000800000L,0x0000000000000010L});
    public static final BitSet FOLLOW_INCLUDES_in_attribute1589 = new BitSet(new long[]{0x0000001000800000L,0x0000000000000010L});
    public static final BitSet FOLLOW_DASHMATCH_in_attribute1600 = new BitSet(new long[]{0x0000001000800000L,0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_attribute1618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_attribute1630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PSEUDO_in_pseudo1663 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_FUNCTION_in_pseudo1676 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_IDENT_in_pseudo1693 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STRING_in_string1718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_STRING_in_string1725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_any1741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_any1747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_any1753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENTAGE_in_any1759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_any1765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_any1771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_URI_in_any1777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_any1783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_any1789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCLUDES_in_any1795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_any1801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_any1807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_any1813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_any1819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_any1825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_any1831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_any1838 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_any1840 = new BitSet(new long[]{0x31FF4A1800800308L,0x0000000000000011L});
    public static final BitSet FOLLOW_DASHMATCH_in_any1849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENBLOCK_in_any1856 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_any1858 = new BitSet(new long[]{0x31FF4A1800800308L,0x0000000000000011L});
    public static final BitSet FOLLOW_BRACEBLOCK_in_any1867 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_any1869 = new BitSet(new long[]{0x31FF4A1800800308L,0x0000000000000011L});

}