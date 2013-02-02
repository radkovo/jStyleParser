// $ANTLR 3.1.2 /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g 2013-02-02 17:31:50

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


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CSSTreeParser extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "STYLESHEET", "INLINESTYLE", "ATBLOCK", "CURLYBLOCK", "PARENBLOCK", "BRACEBLOCK", "RULE", "SELECTOR", "ELEMENT", "PSEUDO", "ADJACENT", "CHILD", "DESCENDANT", "ATTRIBUTE", "SET", "DECLARATION", "VALUE", "IMPORTANT", "IMPORT_END", "INVALID_STRING", "INVALID_SELECTOR", "INVALID_SELPART", "INVALID_DECLARATION", "INVALID_STATEMENT", "INVALID_IMPORT", "INVALID_DIRECTIVE", "S", "CDO", "CDC", "CHARSET", "IMPORT", "PAGE", "COLON", "IDENT", "LCURLY", "RCURLY", "FONTFACE", "MEDIA", "ATKEYWORD", "COMMA", "SEMICOLON", "EXCLAMATION", "MINUS", "EXPRESSION", "FUNCTION", "RPAREN", "CLASSKEYWORD", "NUMBER", "PERCENTAGE", "DIMENSION", "URI", "HASH", "UNIRANGE", "INCLUDES", "GREATER", "LESS", "QUESTION", "PERCENT", "EQUALS", "SLASH", "PLUS", "ASTERISK", "DASHMATCH", "LPAREN", "LBRACE", "RBRACE", "STARTSWITH", "ENDSWITH", "CONTAINS", "STRING", "STRING_CHAR", "INVALID_TOKEN", "IDENT_MACR", "STRING_MACR", "NAME_MACR", "NUMBER_MACR", "W_MACR", "URI_MACR", "APOS", "QUOT", "W_CHAR", "COMMENT", "SL_COMMENT", "NAME_START", "NAME_CHAR", "NON_ASCII", "ESCAPE_CHAR", "URI_CHAR", "NL_CHAR", "'important'", "'#'", "'^'"
    };
    public static final int FUNCTION=48;
    public static final int APOS=82;
    public static final int CLASSKEYWORD=50;
    public static final int CONTAINS=72;
    public static final int INVALID_STATEMENT=27;
    public static final int INVALID_TOKEN=75;
    public static final int EQUALS=62;
    public static final int MEDIA=41;
    public static final int NL_CHAR=92;
    public static final int EOF=-1;
    public static final int NON_ASCII=89;
    public static final int STYLESHEET=4;
    public static final int T__93=93;
    public static final int INCLUDES=57;
    public static final int T__94=94;
    public static final int ENDSWITH=71;
    public static final int RPAREN=49;
    public static final int INVALID_DIRECTIVE=29;
    public static final int IMPORT=34;
    public static final int GREATER=58;
    public static final int EXCLAMATION=45;
    public static final int INVALID_SELPART=25;
    public static final int LESS=59;
    public static final int INVALID_DECLARATION=26;
    public static final int ELEMENT=12;
    public static final int DIMENSION=53;
    public static final int COMMENT=85;
    public static final int T__95=95;
    public static final int CHILD=15;
    public static final int INVALID_STRING=23;
    public static final int RBRACE=69;
    public static final int RULE=10;
    public static final int PARENBLOCK=8;
    public static final int NUMBER=51;
    public static final int URI_CHAR=91;
    public static final int LCURLY=38;
    public static final int SEMICOLON=44;
    public static final int FONTFACE=40;
    public static final int S=30;
    public static final int VALUE=20;
    public static final int CDO=31;
    public static final int CDC=32;
    public static final int PERCENTAGE=52;
    public static final int INVALID_SELECTOR=24;
    public static final int URI=54;
    public static final int STRING_CHAR=74;
    public static final int DASHMATCH=66;
    public static final int IMPORT_END=22;
    public static final int INLINESTYLE=5;
    public static final int SL_COMMENT=86;
    public static final int IDENT_MACR=76;
    public static final int NAME_CHAR=88;
    public static final int PSEUDO=13;
    public static final int LBRACE=68;
    public static final int ATTRIBUTE=17;
    public static final int NAME_START=87;
    public static final int NUMBER_MACR=79;
    public static final int CHARSET=33;
    public static final int DECLARATION=19;
    public static final int ASTERISK=65;
    public static final int LPAREN=67;
    public static final int BRACEBLOCK=9;
    public static final int SELECTOR=11;
    public static final int SLASH=63;
    public static final int ATBLOCK=6;
    public static final int COMMA=43;
    public static final int IDENT=37;
    public static final int UNIRANGE=56;
    public static final int PLUS=64;
    public static final int EXPRESSION=47;
    public static final int CURLYBLOCK=7;
    public static final int ATKEYWORD=42;
    public static final int PERCENT=61;
    public static final int W_CHAR=84;
    public static final int STRING_MACR=77;
    public static final int W_MACR=80;
    public static final int QUOT=83;
    public static final int DESCENDANT=16;
    public static final int HASH=55;
    public static final int SET=18;
    public static final int NAME_MACR=78;
    public static final int MINUS=46;
    public static final int IMPORTANT=21;
    public static final int ESCAPE_CHAR=90;
    public static final int COLON=36;
    public static final int PAGE=35;
    public static final int ADJACENT=14;
    public static final int QUESTION=60;
    public static final int INVALID_IMPORT=28;
    public static final int STARTSWITH=70;
    public static final int RCURLY=39;
    public static final int STRING=73;
    public static final int URI_MACR=81;

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



    // $ANTLR start "inlinestyle"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:104:1: inlinestyle returns [StyleSheet sheet] : ( ^( INLINESTYLE decl= declarations ) | ^( INLINESTYLE (irs= inlineset )+ ) );
    public final StyleSheet inlinestyle() throws RecognitionException {
        StyleSheet sheet = null;

        List<Declaration> decl = null;

        RuleBlock<?> irs = null;



        	logEnter("inlinestyle");
        	sheet = this.stylesheet;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:115:2: ( ^( INLINESTYLE decl= declarations ) | ^( INLINESTYLE (irs= inlineset )+ ) )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:115:5: ^( INLINESTYLE decl= declarations )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:122:6: ^( INLINESTYLE (irs= inlineset )+ )
                    {
                    match(input,INLINESTYLE,FOLLOW_INLINESTYLE_in_inlinestyle78); 

                    match(input, Token.DOWN, null); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:123:5: (irs= inlineset )+
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
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:123:6: irs= inlineset
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:127:1: stylesheet returns [StyleSheet sheet] : ^( STYLESHEET (s= statement )* ) ;
    public final StyleSheet stylesheet() throws RecognitionException {
        StyleSheet sheet = null;

        RuleBlock<?> s = null;



        	logEnter("stylesheet");
        	sheet = this.stylesheet;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:141:2: ( ^( STYLESHEET (s= statement )* ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:141:4: ^( STYLESHEET (s= statement )* )
            {
            match(input,STYLESHEET,FOLLOW_STYLESHEET_in_stylesheet125); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:142:4: (s= statement )*
                loop3:
                do {
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==RULE||LA3_0==IMPORT_END||(LA3_0>=INVALID_STATEMENT && LA3_0<=INVALID_IMPORT)||(LA3_0>=CHARSET && LA3_0<=PAGE)||(LA3_0>=FONTFACE && LA3_0<=MEDIA)) ) {
                        alt3=1;
                    }


                    switch (alt3) {
                	case 1 :
                	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:142:5: s= statement
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:146:1: statement returns [RuleBlock<?> stm] : (rs= ruleset | ats= atstatement | INVALID_STATEMENT );
    public final RuleBlock<?> statement() throws RecognitionException {
        statement_stack.push(new statement_scope());
        RuleBlock<?> stm = null;

        RuleBlock<?> rs = null;

        RuleBlock<?> ats = null;



        	logEnter("statement");
        	((statement_scope)statement_stack.peek()).invalid = false;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:167:2: (rs= ruleset | ats= atstatement | INVALID_STATEMENT )
            int alt4=3;
            switch ( input.LA(1) ) {
            case RULE:
                {
                alt4=1;
                }
                break;
            case IMPORT_END:
            case INVALID_IMPORT:
            case CHARSET:
            case IMPORT:
            case PAGE:
            case FONTFACE:
            case MEDIA:
                {
                alt4=2;
                }
                break;
            case INVALID_STATEMENT:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:167:4: rs= ruleset
                    {
                    pushFollow(FOLLOW_ruleset_in_statement183);
                    rs=ruleset();

                    state._fsp--;

                    stm =(RuleBlock<?>) rs;

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:168:4: ats= atstatement
                    {
                    pushFollow(FOLLOW_atstatement_in_statement193);
                    ats=atstatement();

                    state._fsp--;

                    stm =(RuleBlock<?>) ats;

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:169:4: INVALID_STATEMENT
                    {
                    match(input,INVALID_STATEMENT,FOLLOW_INVALID_STATEMENT_in_statement200); 
                     ((statement_scope)statement_stack.peek()).invalid = true; 

                    }
                    break;

            }

              if (((statement_scope)statement_stack.peek()).invalid)
                  log.debug("Statement is invalid");
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:173:1: atstatement returns [RuleBlock<?> stmnt] : ( CHARSET | INVALID_IMPORT | i= IMPORT | IMPORT_END | ^( PAGE (i= IDENT )? decl= declarations ) | ^( FONTFACE decl= declarations ) | ^( MEDIA (mediaList= media )? (rs= ruleset | INVALID_STATEMENT )* ) );
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:188:2: ( CHARSET | INVALID_IMPORT | i= IMPORT | IMPORT_END | ^( PAGE (i= IDENT )? decl= declarations ) | ^( FONTFACE decl= declarations ) | ^( MEDIA (mediaList= media )? (rs= ruleset | INVALID_STATEMENT )* ) )
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
            case FONTFACE:
                {
                alt8=6;
                }
                break;
            case MEDIA:
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:188:4: CHARSET
                    {
                    match(input,CHARSET,FOLLOW_CHARSET_in_atstatement233); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:189:4: INVALID_IMPORT
                    {
                    match(input,INVALID_IMPORT,FOLLOW_INVALID_IMPORT_in_atstatement239); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:190:4: i= IMPORT
                    {
                    i=(CommonTree)match(input,IMPORT,FOLLOW_IMPORT_in_atstatement247); 

                    	    String media = extractText(i);
                    		imports.push(new TreeParserState(media));
                    		
                    		log.info("From imported file: Rules will use these media: {}", 
                    			imports.peek());
                    	  

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:198:4: IMPORT_END
                    {
                    match(input,IMPORT_END,FOLLOW_IMPORT_END_in_atstatement258); 

                    	    imports.pop();
                    		log.info("Imported file was parsed, returing in nesting.");
                    	  

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:202:4: ^( PAGE (i= IDENT )? decl= declarations )
                    {
                    match(input,PAGE,FOLLOW_PAGE_in_atstatement266); 

                    match(input, Token.DOWN, null); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:202:11: (i= IDENT )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==IDENT) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:202:12: i= IDENT
                            {
                            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_atstatement271); 
                             pseudo=extractText(i);

                            }
                            break;

                    }

                    pushFollow(FOLLOW_declarations_in_atstatement278);
                    decl=declarations();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     stmnt = preparator.prepareRulePage(decl, pseudo); 

                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:204:5: ^( FONTFACE decl= declarations )
                    {
                    match(input,FONTFACE,FOLLOW_FONTFACE_in_atstatement290); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_declarations_in_atstatement294);
                    decl=declarations();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     stmnt = preparator.prepareRuleFontFace(decl); 

                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:206:4: ^( MEDIA (mediaList= media )? (rs= ruleset | INVALID_STATEMENT )* )
                    {
                    match(input,MEDIA,FOLLOW_MEDIA_in_atstatement307); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:206:12: (mediaList= media )?
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==IDENT) ) {
                            alt6=1;
                        }
                        switch (alt6) {
                            case 1 :
                                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:206:13: mediaList= media
                                {
                                pushFollow(FOLLOW_media_in_atstatement312);
                                mediaList=media();

                                state._fsp--;


                                }
                                break;

                        }

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:207:4: (rs= ruleset | INVALID_STATEMENT )*
                        loop7:
                        do {
                            int alt7=3;
                            int LA7_0 = input.LA(1);

                            if ( (LA7_0==RULE) ) {
                                alt7=1;
                            }
                            else if ( (LA7_0==INVALID_STATEMENT) ) {
                                alt7=2;
                            }


                            switch (alt7) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:207:7: rs= ruleset
                        	    {
                        	    pushFollow(FOLLOW_ruleset_in_atstatement325);
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
                        	case 2 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:216:8: INVALID_STATEMENT
                        	    {
                        	    match(input,INVALID_STATEMENT,FOLLOW_INVALID_STATEMENT_in_atstatement336); 
                        	     log.debug("Skiping invalid statement in media"); 

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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:225:1: media returns [List<String> affected] : (i= IDENT )+ ;
    public final List<String> media() throws RecognitionException {
        List<String> affected = null;

        CommonTree i=null;


           logEnter("media");
           affected = new ArrayList<String>();

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:234:2: ( (i= IDENT )+ )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:234:4: (i= IDENT )+
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:234:4: (i= IDENT )+
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
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:234:5: i= IDENT
            	    {
            	    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_media391); 

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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:240:1: inlineset returns [RuleBlock<?> is] : ^( RULE (p= pseudo )* decl= declarations ) ;
    public final RuleBlock<?> inlineset() throws RecognitionException {
        RuleBlock<?> is = null;

        Selector.PseudoPage p = null;

        List<Declaration> decl = null;



             logEnter("inlineset");
        	 List<Selector.PseudoPage> pplist = new ArrayList<Selector.PseudoPage>();

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:248:2: ( ^( RULE (p= pseudo )* decl= declarations ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:248:4: ^( RULE (p= pseudo )* decl= declarations )
            {
            match(input,RULE,FOLLOW_RULE_in_inlineset426); 

            match(input, Token.DOWN, null); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:248:11: (p= pseudo )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==PSEUDO) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:248:12: p= pseudo
            	    {
            	    pushFollow(FOLLOW_pseudo_in_inlineset431);
            	    p=pseudo();

            	    state._fsp--;

            	    pplist.add(p);

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            pushFollow(FOLLOW_declarations_in_inlineset439);
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:253:1: ruleset returns [RuleBlock<?> stmnt] : ^( RULE (cs= combined_selector )* decl= declarations ) ;
    public final RuleBlock<?> ruleset() throws RecognitionException {
        RuleBlock<?> stmnt = null;

        CombinedSelector cs = null;

        List<Declaration> decl = null;



            logEnter("ruleset"); 
            List<CombinedSelector> cslist = new ArrayList<CombinedSelector>();

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:274:5: ( ^( RULE (cs= combined_selector )* decl= declarations ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:274:7: ^( RULE (cs= combined_selector )* decl= declarations )
            {
            match(input,RULE,FOLLOW_RULE_in_ruleset492); 

            match(input, Token.DOWN, null); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:275:9: (cs= combined_selector )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==SELECTOR||LA11_0==INVALID_SELECTOR) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:275:10: cs= combined_selector
            	    {
            	    pushFollow(FOLLOW_combined_selector_in_ruleset506);
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

            pushFollow(FOLLOW_declarations_in_ruleset527);
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:285:1: declarations returns [List<Declaration> decl] : ^( SET (d= declaration )* ) ;
    public final List<Declaration> declarations() throws RecognitionException {
        List<Declaration> decl = null;

        Declaration d = null;



        		  logEnter("declarations");
        		  decl = new ArrayList<Declaration>();

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:296:2: ( ^( SET (d= declaration )* ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:296:4: ^( SET (d= declaration )* )
            {
            match(input,SET,FOLLOW_SET_in_declarations568); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:296:10: (d= declaration )*
                loop12:
                do {
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==DECLARATION||LA12_0==INVALID_DECLARATION) ) {
                        alt12=1;
                    }


                    switch (alt12) {
                	case 1 :
                	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:296:11: d= declaration
                	    {
                	    pushFollow(FOLLOW_declaration_in_declarations573);
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:306:1: declaration returns [Declaration decl] : ( ^( DECLARATION ( important )? ( INVALID_DIRECTIVE )? property t= terms ) | INVALID_DECLARATION );
    public final Declaration declaration() throws RecognitionException {
        declaration_stack.push(new declaration_scope());
        Declaration decl = null;

        List<Term<?>> t = null;



            logEnter("declaration");
            ((declaration_scope)declaration_stack.peek()).d = decl = rf.createDeclaration();
            ((declaration_scope)declaration_stack.peek()).invalid = false;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:329:3: ( ^( DECLARATION ( important )? ( INVALID_DIRECTIVE )? property t= terms ) | INVALID_DECLARATION )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==DECLARATION) ) {
                alt15=1;
            }
            else if ( (LA15_0==INVALID_DECLARATION) ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:329:5: ^( DECLARATION ( important )? ( INVALID_DIRECTIVE )? property t= terms )
                    {
                    match(input,DECLARATION,FOLLOW_DECLARATION_in_declaration617); 

                    match(input, Token.DOWN, null); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:330:6: ( important )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==IMPORTANT) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:330:7: important
                            {
                            pushFollow(FOLLOW_important_in_declaration626);
                            important();

                            state._fsp--;

                             decl.setImportant(true); log.debug("IMPORTANT"); 

                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:331:7: ( INVALID_DIRECTIVE )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==INVALID_DIRECTIVE) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:331:8: INVALID_DIRECTIVE
                            {
                            match(input,INVALID_DIRECTIVE,FOLLOW_INVALID_DIRECTIVE_in_declaration639); 
                             ((declaration_scope)declaration_stack.peek()).invalid =true; 

                            }
                            break;

                    }

                    pushFollow(FOLLOW_property_in_declaration651);
                    property();

                    state._fsp--;

                    pushFollow(FOLLOW_terms_in_declaration662);
                    t=terms();

                    state._fsp--;

                    decl.replaceAll(t);

                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:335:4: INVALID_DECLARATION
                    {
                    match(input,INVALID_DECLARATION,FOLLOW_INVALID_DECLARATION_in_declaration682); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:338:1: important : IMPORTANT ;
    public final void important() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:339:5: ( IMPORTANT )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:339:7: IMPORTANT
            {
            match(input,IMPORTANT,FOLLOW_IMPORTANT_in_important699); 

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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:342:1: property : (i= IDENT | MINUS i= IDENT );
    public final void property() throws RecognitionException {
        CommonTree i=null;


            logEnter("property");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:353:3: (i= IDENT | MINUS i= IDENT )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==IDENT) ) {
                alt16=1;
            }
            else if ( (LA16_0==MINUS) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:353:5: i= IDENT
                    {
                    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_property739); 
                     ((declaration_scope)declaration_stack.peek()).d.setProperty(extractText(i)); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:354:5: MINUS i= IDENT
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_property747); 
                    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_property753); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:357:1: terms returns [List<Term<?>> tlist] : ^( VALUE ( term )+ ) ;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:380:5: ( ^( VALUE ( term )+ ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:380:7: ^( VALUE ( term )+ )
            {
            match(input,VALUE,FOLLOW_VALUE_in_terms798); 

            match(input, Token.DOWN, null); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:380:15: ( term )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>=CURLYBLOCK && LA17_0<=BRACEBLOCK)||LA17_0==INVALID_STRING||(LA17_0>=COLON && LA17_0<=IDENT)||(LA17_0>=ATKEYWORD && LA17_0<=COMMA)||(LA17_0>=MINUS && LA17_0<=FUNCTION)||(LA17_0>=CLASSKEYWORD && LA17_0<=DASHMATCH)||LA17_0==STRING) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:380:15: term
            	    {
            	    pushFollow(FOLLOW_term_in_terms800);
            	    term();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:383:1: term : ( valuepart | CURLYBLOCK | ATKEYWORD );
    public final void term() throws RecognitionException {

          logEnter("term");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:387:5: ( valuepart | CURLYBLOCK | ATKEYWORD )
            int alt18=3;
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
                alt18=1;
                }
                break;
            case CURLYBLOCK:
                {
                alt18=2;
                }
                break;
            case ATKEYWORD:
                {
                alt18=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:387:7: valuepart
                    {
                    pushFollow(FOLLOW_valuepart_in_term828);
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:399:7: CURLYBLOCK
                    {
                    match(input,CURLYBLOCK,FOLLOW_CURLYBLOCK_in_term845); 
                     ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:400:7: ATKEYWORD
                    {
                    match(input,ATKEYWORD,FOLLOW_ATKEYWORD_in_term855); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:403:1: valuepart : ( ( MINUS )? i= IDENT | CLASSKEYWORD | ( MINUS )? n= NUMBER | ( MINUS )? p= PERCENTAGE | ( MINUS )? d= DIMENSION | s= string | u= URI | h= HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | LESS | QUESTION | PERCENT | EQUALS | SLASH | PLUS | ASTERISK | e= EXPRESSION | ( MINUS )? ^(f= FUNCTION (t= terms )? ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) );
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:425:5: ( ( MINUS )? i= IDENT | CLASSKEYWORD | ( MINUS )? n= NUMBER | ( MINUS )? p= PERCENTAGE | ( MINUS )? d= DIMENSION | s= string | u= URI | h= HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | LESS | QUESTION | PERCENT | EQUALS | SLASH | PLUS | ASTERISK | e= EXPRESSION | ( MINUS )? ^(f= FUNCTION (t= terms )? ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) )
            int alt27=25;
            alt27 = dfa27.predict(input);
            switch (alt27) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:425:7: ( MINUS )? i= IDENT
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:425:7: ( MINUS )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==MINUS) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:425:8: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart882); 
                            ((terms_scope)terms_stack.peek()).dash =true;

                            }
                            break;

                    }

                    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_valuepart890); 
                    ((terms_scope)terms_stack.peek()).term = tf.createIdent(extractText(i), ((terms_scope)terms_stack.peek()).dash);

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:426:7: CLASSKEYWORD
                    {
                    match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_valuepart902); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:427:6: ( MINUS )? n= NUMBER
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:427:6: ( MINUS )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==MINUS) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:427:7: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart912); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    n=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_valuepart920); 
                    ((terms_scope)terms_stack.peek()).term = tf.createNumeric(extractText(n), ((terms_scope)terms_stack.peek()).unary);

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:428:7: ( MINUS )? p= PERCENTAGE
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:428:7: ( MINUS )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==MINUS) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:428:8: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart934); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    p=(CommonTree)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_valuepart942); 
                     ((terms_scope)terms_stack.peek()).term = tf.createPercent(extractText(p), ((terms_scope)terms_stack.peek()).unary);

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:429:7: ( MINUS )? d= DIMENSION
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:429:7: ( MINUS )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==MINUS) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:429:8: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart954); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    d=(CommonTree)match(input,DIMENSION,FOLLOW_DIMENSION_in_valuepart962); 
                    String dim = extractText(d);
                    				 ((terms_scope)terms_stack.peek()).term = tf.createDimension(dim, ((terms_scope)terms_stack.peek()).unary);
                    			     if(((terms_scope)terms_stack.peek()).term==null) {
                    					 log.info("Unable to create dimension from {}, unary {}", dim, ((terms_scope)terms_stack.peek()).unary);
                    			         ((declaration_scope)declaration_stack.peek()).invalid = true;
                    				 }
                    	    

                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:437:7: s= string
                    {
                    pushFollow(FOLLOW_string_in_valuepart980);
                    s=string();

                    state._fsp--;

                     if(s!=null) ((terms_scope)terms_stack.peek()).term = tf.createString(s);
                    			  else ((declaration_scope)declaration_stack.peek()).invalid =true;
                    			

                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:441:7: u= URI
                    {
                    u=(CommonTree)match(input,URI,FOLLOW_URI_in_valuepart999); 
                    ((terms_scope)terms_stack.peek()).term = tf.createURI(extractText(u), extractBase(u));

                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:442:7: h= HASH
                    {
                    h=(CommonTree)match(input,HASH,FOLLOW_HASH_in_valuepart1017); 
                    ((terms_scope)terms_stack.peek()).term = tf.createColor(extractText(h));
                    	     if(((terms_scope)terms_stack.peek()).term==null)
                    	         ((declaration_scope)declaration_stack.peek()).invalid = true;
                    	    

                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:447:7: UNIRANGE
                    {
                    match(input,UNIRANGE,FOLLOW_UNIRANGE_in_valuepart1036); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:448:7: INCLUDES
                    {
                    match(input,INCLUDES,FOLLOW_INCLUDES_in_valuepart1047); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:449:7: COLON
                    {
                    match(input,COLON,FOLLOW_COLON_in_valuepart1058); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:450:7: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_valuepart1072); 
                    ((terms_scope)terms_stack.peek()).op = Term.Operator.COMMA;

                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:451:7: GREATER
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_valuepart1090); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:452:7: LESS
                    {
                    match(input,LESS,FOLLOW_LESS_in_valuepart1102); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:453:7: QUESTION
                    {
                    match(input,QUESTION,FOLLOW_QUESTION_in_valuepart1117); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:454:7: PERCENT
                    {
                    match(input,PERCENT,FOLLOW_PERCENT_in_valuepart1128); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:455:7: EQUALS
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_valuepart1140); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 18 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:456:7: SLASH
                    {
                    match(input,SLASH,FOLLOW_SLASH_in_valuepart1153); 
                    ((terms_scope)terms_stack.peek()).op = Term.Operator.SLASH;

                    }
                    break;
                case 19 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:457:5: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_valuepart1165); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 20 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:458:5: ASTERISK
                    {
                    match(input,ASTERISK,FOLLOW_ASTERISK_in_valuepart1176); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 21 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:459:5: e= EXPRESSION
                    {
                    e=(CommonTree)match(input,EXPRESSION,FOLLOW_EXPRESSION_in_valuepart1187); 

                    		    String exprval = extractText(e);
                            TermExpression expr = tf.createExpression(exprval.substring(11,exprval.length()-1)); //strip the 'expression()'
                            ((terms_scope)terms_stack.peek()).term = expr;
                    		

                    }
                    break;
                case 22 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:464:7: ( MINUS )? ^(f= FUNCTION (t= terms )? )
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:464:7: ( MINUS )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==MINUS) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:464:8: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart1198); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    f=(CommonTree)match(input,FUNCTION,FOLLOW_FUNCTION_in_valuepart1207); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:464:50: (t= terms )?
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==VALUE) ) {
                            alt24=1;
                        }
                        switch (alt24) {
                            case 1 :
                                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:464:50: t= terms
                                {
                                pushFollow(FOLLOW_terms_in_valuepart1211);
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
                            if (((terms_scope)terms_stack.peek()).unary == -1) //if started with minus, add the minus to the function name
                                function.setFunctionName('-' + function.getFunctionName());
                            if (t != null)
                            	function.setValue(t);
                            ((terms_scope)terms_stack.peek()).term = function;
                        

                    }
                    break;
                case 23 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:474:7: DASHMATCH
                    {
                    match(input,DASHMATCH,FOLLOW_DASHMATCH_in_valuepart1223); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 24 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:475:7: ^( PARENBLOCK ( any )* )
                    {
                    match(input,PARENBLOCK,FOLLOW_PARENBLOCK_in_valuepart1234); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:475:20: ( any )*
                        loop25:
                        do {
                            int alt25=2;
                            int LA25_0 = input.LA(1);

                            if ( ((LA25_0>=PARENBLOCK && LA25_0<=BRACEBLOCK)||LA25_0==INVALID_STRING||(LA25_0>=COLON && LA25_0<=IDENT)||LA25_0==COMMA||LA25_0==EXCLAMATION||LA25_0==FUNCTION||(LA25_0>=CLASSKEYWORD && LA25_0<=GREATER)||(LA25_0>=EQUALS && LA25_0<=SLASH)||LA25_0==DASHMATCH||LA25_0==STRING) ) {
                                alt25=1;
                            }


                            switch (alt25) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:475:20: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_valuepart1236);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop25;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 25 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:476:7: ^( BRACEBLOCK ( any )* )
                    {
                    match(input,BRACEBLOCK,FOLLOW_BRACEBLOCK_in_valuepart1249); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:476:20: ( any )*
                        loop26:
                        do {
                            int alt26=2;
                            int LA26_0 = input.LA(1);

                            if ( ((LA26_0>=PARENBLOCK && LA26_0<=BRACEBLOCK)||LA26_0==INVALID_STRING||(LA26_0>=COLON && LA26_0<=IDENT)||LA26_0==COMMA||LA26_0==EXCLAMATION||LA26_0==FUNCTION||(LA26_0>=CLASSKEYWORD && LA26_0<=GREATER)||(LA26_0>=EQUALS && LA26_0<=SLASH)||LA26_0==DASHMATCH||LA26_0==STRING) ) {
                                alt26=1;
                            }


                            switch (alt26) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:476:20: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_valuepart1251);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop26;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:479:1: combined_selector returns [CombinedSelector combinedSelector] : s= selector (c= combinator s= selector )* ;
    public final CombinedSelector combined_selector() throws RecognitionException {
        combined_selector_stack.push(new combined_selector_scope());
        CombinedSelector combinedSelector = null;

        Selector s = null;

        Selector.Combinator c = null;



        	logEnter("combined_selector");	  
        	combinedSelector = (CombinedSelector) rf.createCombinedSelector().unlock();

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:509:2: (s= selector (c= combinator s= selector )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:509:4: s= selector (c= combinator s= selector )*
            {
            pushFollow(FOLLOW_selector_in_combined_selector1299);
            s=selector();

            state._fsp--;


            	     combinedSelector.add(s);
            	  
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:512:3: (c= combinator s= selector )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( ((LA28_0>=ADJACENT && LA28_0<=DESCENDANT)) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:512:4: c= combinator s= selector
            	    {
            	    pushFollow(FOLLOW_combinator_in_combined_selector1308);
            	    c=combinator();

            	    state._fsp--;

            	    pushFollow(FOLLOW_selector_in_combined_selector1312);
            	    s=selector();

            	    state._fsp--;


            	    	     s.setCombinator(c);
            	    	     combinedSelector.add(s);	
            	    	  

            	    }
            	    break;

            	default :
            	    break loop28;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:519:1: combinator returns [Selector.Combinator combinator] : ( CHILD | ADJACENT | DESCENDANT );
    public final Selector.Combinator combinator() throws RecognitionException {
        Selector.Combinator combinator = null;

         logEnter("combinator"); 
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:522:2: ( CHILD | ADJACENT | DESCENDANT )
            int alt29=3;
            switch ( input.LA(1) ) {
            case CHILD:
                {
                alt29=1;
                }
                break;
            case ADJACENT:
                {
                alt29=2;
                }
                break;
            case DESCENDANT:
                {
                alt29=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }

            switch (alt29) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:522:4: CHILD
                    {
                    match(input,CHILD,FOLLOW_CHILD_in_combinator1342); 
                    combinator =Selector.Combinator.CHILD;

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:523:4: ADJACENT
                    {
                    match(input,ADJACENT,FOLLOW_ADJACENT_in_combinator1349); 
                    combinator =Selector.Combinator.ADJACENT;

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:524:4: DESCENDANT
                    {
                    match(input,DESCENDANT,FOLLOW_DESCENDANT_in_combinator1356); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:528:1: selector returns [Selector sel] : ( ^( SELECTOR ^( ELEMENT (i= IDENT )? ) ( selpart )* ) | ^( SELECTOR ( selpart )+ ) | INVALID_SELECTOR );
    public final Selector selector() throws RecognitionException {
        selector_stack.push(new selector_scope());
        Selector sel = null;

        CommonTree i=null;


        	logEnter("selector");
        	((selector_scope)selector_stack.peek()).s =sel=(Selector)rf.createSelector().unlock();
        	Selector.ElementName en = rf.createElement(Selector.ElementName.WILDCARD);

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:540:5: ( ^( SELECTOR ^( ELEMENT (i= IDENT )? ) ( selpart )* ) | ^( SELECTOR ( selpart )+ ) | INVALID_SELECTOR )
            int alt33=3;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==SELECTOR) ) {
                int LA33_1 = input.LA(2);

                if ( (LA33_1==DOWN) ) {
                    int LA33_3 = input.LA(3);

                    if ( (LA33_3==ELEMENT) ) {
                        alt33=1;
                    }
                    else if ( (LA33_3==PSEUDO||LA33_3==ATTRIBUTE||LA33_3==INVALID_SELPART||LA33_3==CLASSKEYWORD||LA33_3==HASH) ) {
                        alt33=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 33, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 33, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA33_0==INVALID_SELECTOR) ) {
                alt33=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:540:7: ^( SELECTOR ^( ELEMENT (i= IDENT )? ) ( selpart )* )
                    {
                    match(input,SELECTOR,FOLLOW_SELECTOR_in_selector1392); 

                    match(input, Token.DOWN, null); 
                    match(input,ELEMENT,FOLLOW_ELEMENT_in_selector1404); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:542:11: (i= IDENT )?
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==IDENT) ) {
                            alt30=1;
                        }
                        switch (alt30) {
                            case 1 :
                                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:542:12: i= IDENT
                                {
                                i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_selector1420); 
                                 en.setName(extractText(i)); 

                                }
                                break;

                        }


                        match(input, Token.UP, null); 
                    }

                    		  log.debug("Adding element name: {}.", en.getName());
                    		  ((selector_scope)selector_stack.peek()).s.add(en);
                    		 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:548:10: ( selpart )*
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( (LA31_0==PSEUDO||LA31_0==ATTRIBUTE||LA31_0==INVALID_SELPART||LA31_0==CLASSKEYWORD||LA31_0==HASH) ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:548:10: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1467);
                    	    selpart();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop31;
                        }
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:550:7: ^( SELECTOR ( selpart )+ )
                    {
                    match(input,SELECTOR,FOLLOW_SELECTOR_in_selector1486); 

                    match(input, Token.DOWN, null); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:551:10: ( selpart )+
                    int cnt32=0;
                    loop32:
                    do {
                        int alt32=2;
                        int LA32_0 = input.LA(1);

                        if ( (LA32_0==PSEUDO||LA32_0==ATTRIBUTE||LA32_0==INVALID_SELPART||LA32_0==CLASSKEYWORD||LA32_0==HASH) ) {
                            alt32=1;
                        }


                        switch (alt32) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:551:10: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1498);
                    	    selpart();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt32 >= 1 ) break loop32;
                                EarlyExitException eee =
                                    new EarlyExitException(32, input);
                                throw eee;
                        }
                        cnt32++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:553:7: INVALID_SELECTOR
                    {
                    match(input,INVALID_SELECTOR,FOLLOW_INVALID_SELECTOR_in_selector1516); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:556:1: selpart : (h= HASH | c= CLASSKEYWORD | ^( ATTRIBUTE ea= attribute ) | p= pseudo | INVALID_SELPART );
    public final void selpart() throws RecognitionException {
        CommonTree h=null;
        CommonTree c=null;
        Selector.ElementAttribute ea = null;

        Selector.PseudoPage p = null;



        	logEnter("selpart");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:563:5: (h= HASH | c= CLASSKEYWORD | ^( ATTRIBUTE ea= attribute ) | p= pseudo | INVALID_SELPART )
            int alt34=5;
            switch ( input.LA(1) ) {
            case HASH:
                {
                alt34=1;
                }
                break;
            case CLASSKEYWORD:
                {
                alt34=2;
                }
                break;
            case ATTRIBUTE:
                {
                alt34=3;
                }
                break;
            case PSEUDO:
                {
                alt34=4;
                }
                break;
            case INVALID_SELPART:
                {
                alt34=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:563:8: h= HASH
                    {
                    h=(CommonTree)match(input,HASH,FOLLOW_HASH_in_selpart1550); 
                     ((selector_scope)selector_stack.peek()).s.add(rf.createID(extractText(h))); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:564:7: c= CLASSKEYWORD
                    {
                    c=(CommonTree)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_selpart1562); 
                     ((selector_scope)selector_stack.peek()).s.add(rf.createClass(extractText(c))); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:565:4: ^( ATTRIBUTE ea= attribute )
                    {
                    match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_selpart1570); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_attribute_in_selpart1574);
                    ea=attribute();

                    state._fsp--;

                     ((selector_scope)selector_stack.peek()).s.add(ea);

                    match(input, Token.UP, null); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:566:7: p= pseudo
                    {
                    pushFollow(FOLLOW_pseudo_in_selpart1588);
                    p=pseudo();

                    state._fsp--;

                     ((selector_scope)selector_stack.peek()).s.add(p);

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:567:4: INVALID_SELPART
                    {
                    match(input,INVALID_SELPART,FOLLOW_INVALID_SELPART_in_selpart1595); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:570:1: attribute returns [Selector.ElementAttribute elemAttr] : i= IDENT ( ( EQUALS | INCLUDES | DASHMATCH | CONTAINS | STARTSWITH | ENDSWITH ) (i= IDENT | s= string ) )? ;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:588:2: (i= IDENT ( ( EQUALS | INCLUDES | DASHMATCH | CONTAINS | STARTSWITH | ENDSWITH ) (i= IDENT | s= string ) )? )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:588:4: i= IDENT ( ( EQUALS | INCLUDES | DASHMATCH | CONTAINS | STARTSWITH | ENDSWITH ) (i= IDENT | s= string ) )?
            {
            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_attribute1629); 
            attribute=extractText(i); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:589:4: ( ( EQUALS | INCLUDES | DASHMATCH | CONTAINS | STARTSWITH | ENDSWITH ) (i= IDENT | s= string ) )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==INCLUDES||LA37_0==EQUALS||LA37_0==DASHMATCH||(LA37_0>=STARTSWITH && LA37_0<=CONTAINS)) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:589:5: ( EQUALS | INCLUDES | DASHMATCH | CONTAINS | STARTSWITH | ENDSWITH ) (i= IDENT | s= string )
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:589:5: ( EQUALS | INCLUDES | DASHMATCH | CONTAINS | STARTSWITH | ENDSWITH )
                    int alt35=6;
                    switch ( input.LA(1) ) {
                    case EQUALS:
                        {
                        alt35=1;
                        }
                        break;
                    case INCLUDES:
                        {
                        alt35=2;
                        }
                        break;
                    case DASHMATCH:
                        {
                        alt35=3;
                        }
                        break;
                    case CONTAINS:
                        {
                        alt35=4;
                        }
                        break;
                    case STARTSWITH:
                        {
                        alt35=5;
                        }
                        break;
                    case ENDSWITH:
                        {
                        alt35=6;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 35, 0, input);

                        throw nvae;
                    }

                    switch (alt35) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:589:6: EQUALS
                            {
                            match(input,EQUALS,FOLLOW_EQUALS_in_attribute1638); 
                            op=Selector.Operator.EQUALS; 

                            }
                            break;
                        case 2 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:590:7: INCLUDES
                            {
                            match(input,INCLUDES,FOLLOW_INCLUDES_in_attribute1649); 
                            op=Selector.Operator.INCLUDES; 

                            }
                            break;
                        case 3 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:591:7: DASHMATCH
                            {
                            match(input,DASHMATCH,FOLLOW_DASHMATCH_in_attribute1660); 
                            op=Selector.Operator.DASHMATCH; 

                            }
                            break;
                        case 4 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:592:8: CONTAINS
                            {
                            match(input,CONTAINS,FOLLOW_CONTAINS_in_attribute1671); 
                            op=Selector.Operator.CONTAINS; 

                            }
                            break;
                        case 5 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:593:8: STARTSWITH
                            {
                            match(input,STARTSWITH,FOLLOW_STARTSWITH_in_attribute1682); 
                            op=Selector.Operator.STARTSWITH; 

                            }
                            break;
                        case 6 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:594:8: ENDSWITH
                            {
                            match(input,ENDSWITH,FOLLOW_ENDSWITH_in_attribute1693); 
                            op=Selector.Operator.ENDSWITH; 

                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:596:5: (i= IDENT | s= string )
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==IDENT) ) {
                        alt36=1;
                    }
                    else if ( (LA36_0==INVALID_STRING||LA36_0==STRING) ) {
                        alt36=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 36, 0, input);

                        throw nvae;
                    }
                    switch (alt36) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:596:6: i= IDENT
                            {
                            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_attribute1711); 

                            		value=extractText(i);
                            		isStringValue=false;
                            		

                            }
                            break;
                        case 2 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:600:7: s= string
                            {
                            pushFollow(FOLLOW_string_in_attribute1723);
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:612:1: pseudo returns [Selector.PseudoPage pseudoPage] : ( ^( PSEUDO i= IDENT ) | ^( PSEUDO f= FUNCTION i= IDENT ) | ^( PSEUDO f= FUNCTION n= NUMBER ) );
    public final Selector.PseudoPage pseudo() throws RecognitionException {
        Selector.PseudoPage pseudoPage = null;

        CommonTree i=null;
        CommonTree f=null;
        CommonTree n=null;


        		logEnter("pseudo");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:616:2: ( ^( PSEUDO i= IDENT ) | ^( PSEUDO f= FUNCTION i= IDENT ) | ^( PSEUDO f= FUNCTION n= NUMBER ) )
            int alt38=3;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==PSEUDO) ) {
                int LA38_1 = input.LA(2);

                if ( (LA38_1==DOWN) ) {
                    int LA38_2 = input.LA(3);

                    if ( (LA38_2==IDENT) ) {
                        alt38=1;
                    }
                    else if ( (LA38_2==FUNCTION) ) {
                        int LA38_4 = input.LA(4);

                        if ( (LA38_4==IDENT) ) {
                            alt38=2;
                        }
                        else if ( (LA38_4==NUMBER) ) {
                            alt38=3;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 38, 4, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 38, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 38, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }
            switch (alt38) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:616:4: ^( PSEUDO i= IDENT )
                    {
                    match(input,PSEUDO,FOLLOW_PSEUDO_in_pseudo1756); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_pseudo1760); 

                    match(input, Token.UP, null); 

                    			pseudoPage = rf.createPseudoPage(extractText(i), null);
                    		

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:620:4: ^( PSEUDO f= FUNCTION i= IDENT )
                    {
                    match(input,PSEUDO,FOLLOW_PSEUDO_in_pseudo1771); 

                    match(input, Token.DOWN, null); 
                    f=(CommonTree)match(input,FUNCTION,FOLLOW_FUNCTION_in_pseudo1775); 
                    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_pseudo1779); 

                    match(input, Token.UP, null); 

                    			pseudoPage = rf.createPseudoPage(extractText(i), extractText(f));
                    		

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:624:4: ^( PSEUDO f= FUNCTION n= NUMBER )
                    {
                    match(input,PSEUDO,FOLLOW_PSEUDO_in_pseudo1790); 

                    match(input, Token.DOWN, null); 
                    f=(CommonTree)match(input,FUNCTION,FOLLOW_FUNCTION_in_pseudo1794); 
                    n=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_pseudo1798); 

                    match(input, Token.UP, null); 

                    			pseudoPage = rf.createPseudoPage(extractText(n), extractText(f));
                    		

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
        return pseudoPage;
    }
    // $ANTLR end "pseudo"


    // $ANTLR start "string"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:630:1: string returns [String s] : (st= STRING | INVALID_STRING );
    public final String string() throws RecognitionException {
        String s = null;

        CommonTree st=null;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:631:2: (st= STRING | INVALID_STRING )
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==STRING) ) {
                alt39=1;
            }
            else if ( (LA39_0==INVALID_STRING) ) {
                alt39=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }
            switch (alt39) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:631:4: st= STRING
                    {
                    st=(CommonTree)match(input,STRING,FOLLOW_STRING_in_string1820); 
                     s = extractText(st);

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:632:4: INVALID_STRING
                    {
                    match(input,INVALID_STRING,FOLLOW_INVALID_STRING_in_string1827); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:635:1: any : ( IDENT | CLASSKEYWORD | NUMBER | PERCENTAGE | DIMENSION | string | URI | HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | EXCLAMATION | ^( FUNCTION ( any )* ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) );
    public final void any() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:636:3: ( IDENT | CLASSKEYWORD | NUMBER | PERCENTAGE | DIMENSION | string | URI | HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | EXCLAMATION | ^( FUNCTION ( any )* ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) )
            int alt43=20;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt43=1;
                }
                break;
            case CLASSKEYWORD:
                {
                alt43=2;
                }
                break;
            case NUMBER:
                {
                alt43=3;
                }
                break;
            case PERCENTAGE:
                {
                alt43=4;
                }
                break;
            case DIMENSION:
                {
                alt43=5;
                }
                break;
            case INVALID_STRING:
            case STRING:
                {
                alt43=6;
                }
                break;
            case URI:
                {
                alt43=7;
                }
                break;
            case HASH:
                {
                alt43=8;
                }
                break;
            case UNIRANGE:
                {
                alt43=9;
                }
                break;
            case INCLUDES:
                {
                alt43=10;
                }
                break;
            case COLON:
                {
                alt43=11;
                }
                break;
            case COMMA:
                {
                alt43=12;
                }
                break;
            case GREATER:
                {
                alt43=13;
                }
                break;
            case EQUALS:
                {
                alt43=14;
                }
                break;
            case SLASH:
                {
                alt43=15;
                }
                break;
            case EXCLAMATION:
                {
                alt43=16;
                }
                break;
            case FUNCTION:
                {
                alt43=17;
                }
                break;
            case DASHMATCH:
                {
                alt43=18;
                }
                break;
            case PARENBLOCK:
                {
                alt43=19;
                }
                break;
            case BRACEBLOCK:
                {
                alt43=20;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }

            switch (alt43) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:636:5: IDENT
                    {
                    match(input,IDENT,FOLLOW_IDENT_in_any1843); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:637:5: CLASSKEYWORD
                    {
                    match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_any1849); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:638:5: NUMBER
                    {
                    match(input,NUMBER,FOLLOW_NUMBER_in_any1855); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:639:5: PERCENTAGE
                    {
                    match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_any1861); 

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:640:5: DIMENSION
                    {
                    match(input,DIMENSION,FOLLOW_DIMENSION_in_any1867); 

                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:641:5: string
                    {
                    pushFollow(FOLLOW_string_in_any1873);
                    string();

                    state._fsp--;


                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:642:5: URI
                    {
                    match(input,URI,FOLLOW_URI_in_any1879); 

                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:643:5: HASH
                    {
                    match(input,HASH,FOLLOW_HASH_in_any1885); 

                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:644:5: UNIRANGE
                    {
                    match(input,UNIRANGE,FOLLOW_UNIRANGE_in_any1891); 

                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:645:5: INCLUDES
                    {
                    match(input,INCLUDES,FOLLOW_INCLUDES_in_any1897); 

                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:646:5: COLON
                    {
                    match(input,COLON,FOLLOW_COLON_in_any1903); 

                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:647:5: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_any1909); 

                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:648:5: GREATER
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_any1915); 

                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:649:5: EQUALS
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_any1921); 

                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:650:5: SLASH
                    {
                    match(input,SLASH,FOLLOW_SLASH_in_any1927); 

                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:651:5: EXCLAMATION
                    {
                    match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_any1933); 

                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:652:5: ^( FUNCTION ( any )* )
                    {
                    match(input,FUNCTION,FOLLOW_FUNCTION_in_any1940); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:652:16: ( any )*
                        loop40:
                        do {
                            int alt40=2;
                            int LA40_0 = input.LA(1);

                            if ( ((LA40_0>=PARENBLOCK && LA40_0<=BRACEBLOCK)||LA40_0==INVALID_STRING||(LA40_0>=COLON && LA40_0<=IDENT)||LA40_0==COMMA||LA40_0==EXCLAMATION||LA40_0==FUNCTION||(LA40_0>=CLASSKEYWORD && LA40_0<=GREATER)||(LA40_0>=EQUALS && LA40_0<=SLASH)||LA40_0==DASHMATCH||LA40_0==STRING) ) {
                                alt40=1;
                            }


                            switch (alt40) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:652:16: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_any1942);
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
                case 18 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:653:5: DASHMATCH
                    {
                    match(input,DASHMATCH,FOLLOW_DASHMATCH_in_any1951); 

                    }
                    break;
                case 19 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:654:5: ^( PARENBLOCK ( any )* )
                    {
                    match(input,PARENBLOCK,FOLLOW_PARENBLOCK_in_any1958); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:654:18: ( any )*
                        loop41:
                        do {
                            int alt41=2;
                            int LA41_0 = input.LA(1);

                            if ( ((LA41_0>=PARENBLOCK && LA41_0<=BRACEBLOCK)||LA41_0==INVALID_STRING||(LA41_0>=COLON && LA41_0<=IDENT)||LA41_0==COMMA||LA41_0==EXCLAMATION||LA41_0==FUNCTION||(LA41_0>=CLASSKEYWORD && LA41_0<=GREATER)||(LA41_0>=EQUALS && LA41_0<=SLASH)||LA41_0==DASHMATCH||LA41_0==STRING) ) {
                                alt41=1;
                            }


                            switch (alt41) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:654:18: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_any1960);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop41;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }

                    }
                    break;
                case 20 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:655:5: ^( BRACEBLOCK ( any )* )
                    {
                    match(input,BRACEBLOCK,FOLLOW_BRACEBLOCK_in_any1969); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:655:18: ( any )*
                        loop42:
                        do {
                            int alt42=2;
                            int LA42_0 = input.LA(1);

                            if ( ((LA42_0>=PARENBLOCK && LA42_0<=BRACEBLOCK)||LA42_0==INVALID_STRING||(LA42_0>=COLON && LA42_0<=IDENT)||LA42_0==COMMA||LA42_0==EXCLAMATION||LA42_0==FUNCTION||(LA42_0>=CLASSKEYWORD && LA42_0<=GREATER)||(LA42_0>=EQUALS && LA42_0<=SLASH)||LA42_0==DASHMATCH||LA42_0==STRING) ) {
                                alt42=1;
                            }


                            switch (alt42) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:655:18: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_any1971);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop42;
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


    protected DFA27 dfa27 = new DFA27(this);
    static final String DFA27_eotS =
        "\33\uffff";
    static final String DFA27_eofS =
        "\33\uffff";
    static final String DFA27_minS =
        "\1\10\1\45\31\uffff";
    static final String DFA27_maxS =
        "\1\111\1\65\31\uffff";
    static final String DFA27_acceptS =
        "\2\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31";
    static final String DFA27_specialS =
        "\33\uffff}>";
    static final String[] DFA27_transitionS = {
            "\1\31\1\32\15\uffff\1\7\14\uffff\1\14\1\2\5\uffff\1\15\2\uffff"+
            "\1\1\1\26\1\27\1\uffff\1\3\1\4\1\5\1\6\1\10\1\11\1\12\1\13\1"+
            "\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\30\6\uffff\1\7",
            "\1\2\12\uffff\1\27\2\uffff\1\4\1\5\1\6",
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

    static final short[] DFA27_eot = DFA.unpackEncodedString(DFA27_eotS);
    static final short[] DFA27_eof = DFA.unpackEncodedString(DFA27_eofS);
    static final char[] DFA27_min = DFA.unpackEncodedStringToUnsignedChars(DFA27_minS);
    static final char[] DFA27_max = DFA.unpackEncodedStringToUnsignedChars(DFA27_maxS);
    static final short[] DFA27_accept = DFA.unpackEncodedString(DFA27_acceptS);
    static final short[] DFA27_special = DFA.unpackEncodedString(DFA27_specialS);
    static final short[][] DFA27_transition;

    static {
        int numStates = DFA27_transitionS.length;
        DFA27_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA27_transition[i] = DFA.unpackEncodedString(DFA27_transitionS[i]);
        }
    }

    class DFA27 extends DFA {

        public DFA27(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 27;
            this.eot = DFA27_eot;
            this.eof = DFA27_eof;
            this.min = DFA27_min;
            this.max = DFA27_max;
            this.accept = DFA27_accept;
            this.special = DFA27_special;
            this.transition = DFA27_transition;
        }
        public String getDescription() {
            return "403:1: valuepart : ( ( MINUS )? i= IDENT | CLASSKEYWORD | ( MINUS )? n= NUMBER | ( MINUS )? p= PERCENTAGE | ( MINUS )? d= DIMENSION | s= string | u= URI | h= HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | LESS | QUESTION | PERCENT | EQUALS | SLASH | PLUS | ASTERISK | e= EXPRESSION | ( MINUS )? ^(f= FUNCTION (t= terms )? ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) );";
        }
    }
 

    public static final BitSet FOLLOW_INLINESTYLE_in_inlinestyle59 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_declarations_in_inlinestyle63 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INLINESTYLE_in_inlinestyle78 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_inlineset_in_inlinestyle88 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_STYLESHEET_in_stylesheet125 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_stylesheet134 = new BitSet(new long[]{0x0000030E18400408L});
    public static final BitSet FOLLOW_ruleset_in_statement183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atstatement_in_statement193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_STATEMENT_in_statement200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARSET_in_atstatement233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_IMPORT_in_atstatement239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_atstatement247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_END_in_atstatement258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PAGE_in_atstatement266 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_atstatement271 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_declarations_in_atstatement278 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FONTFACE_in_atstatement290 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_declarations_in_atstatement294 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MEDIA_in_atstatement307 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_media_in_atstatement312 = new BitSet(new long[]{0x0000000008000408L});
    public static final BitSet FOLLOW_ruleset_in_atstatement325 = new BitSet(new long[]{0x0000000008000408L});
    public static final BitSet FOLLOW_INVALID_STATEMENT_in_atstatement336 = new BitSet(new long[]{0x0000000008000408L});
    public static final BitSet FOLLOW_IDENT_in_media391 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_RULE_in_inlineset426 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_pseudo_in_inlineset431 = new BitSet(new long[]{0x0000000000042000L});
    public static final BitSet FOLLOW_declarations_in_inlineset439 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RULE_in_ruleset492 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_combined_selector_in_ruleset506 = new BitSet(new long[]{0x0000000001040800L});
    public static final BitSet FOLLOW_declarations_in_ruleset527 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SET_in_declarations568 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_declaration_in_declarations573 = new BitSet(new long[]{0x0000000004080008L});
    public static final BitSet FOLLOW_DECLARATION_in_declaration617 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_important_in_declaration626 = new BitSet(new long[]{0x0000402020000000L});
    public static final BitSet FOLLOW_INVALID_DIRECTIVE_in_declaration639 = new BitSet(new long[]{0x0000402020000000L});
    public static final BitSet FOLLOW_property_in_declaration651 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_terms_in_declaration662 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INVALID_DECLARATION_in_declaration682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORTANT_in_important699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_property739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_property747 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_IDENT_in_property753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_terms798 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_term_in_terms800 = new BitSet(new long[]{0xFFFDCC3000800388L,0x0000000000000207L});
    public static final BitSet FOLLOW_valuepart_in_term828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CURLYBLOCK_in_term845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATKEYWORD_in_term855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart882 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_IDENT_in_valuepart890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_valuepart902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart912 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_NUMBER_in_valuepart920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart934 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_PERCENTAGE_in_valuepart942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart954 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_DIMENSION_in_valuepart962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_valuepart980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_URI_in_valuepart999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_valuepart1017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_valuepart1036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCLUDES_in_valuepart1047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_valuepart1058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_valuepart1072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_valuepart1090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_valuepart1102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_valuepart1117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENT_in_valuepart1128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_valuepart1140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_valuepart1153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_valuepart1165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASTERISK_in_valuepart1176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPRESSION_in_valuepart1187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1198 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_FUNCTION_in_valuepart1207 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_terms_in_valuepart1211 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DASHMATCH_in_valuepart1223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENBLOCK_in_valuepart1234 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_valuepart1236 = new BitSet(new long[]{0xC7FD283000800308L,0x0000000000000204L});
    public static final BitSet FOLLOW_BRACEBLOCK_in_valuepart1249 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_valuepart1251 = new BitSet(new long[]{0xC7FD283000800308L,0x0000000000000204L});
    public static final BitSet FOLLOW_selector_in_combined_selector1299 = new BitSet(new long[]{0x000000000001C002L});
    public static final BitSet FOLLOW_combinator_in_combined_selector1308 = new BitSet(new long[]{0x0000000001040800L});
    public static final BitSet FOLLOW_selector_in_combined_selector1312 = new BitSet(new long[]{0x000000000001C002L});
    public static final BitSet FOLLOW_CHILD_in_combinator1342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADJACENT_in_combinator1349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DESCENDANT_in_combinator1356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELECTOR_in_selector1392 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ELEMENT_in_selector1404 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_selector1420 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_selpart_in_selector1467 = new BitSet(new long[]{0x0084000002062008L});
    public static final BitSet FOLLOW_SELECTOR_in_selector1486 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_selpart_in_selector1498 = new BitSet(new long[]{0x0084000002062008L});
    public static final BitSet FOLLOW_INVALID_SELECTOR_in_selector1516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_selpart1550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_selpart1562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_selpart1570 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_attribute_in_selpart1574 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_pseudo_in_selpart1588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_SELPART_in_selpart1595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_attribute1629 = new BitSet(new long[]{0x4200000000000002L,0x00000000000001C4L});
    public static final BitSet FOLLOW_EQUALS_in_attribute1638 = new BitSet(new long[]{0x0000002000800000L,0x0000000000000200L});
    public static final BitSet FOLLOW_INCLUDES_in_attribute1649 = new BitSet(new long[]{0x0000002000800000L,0x0000000000000200L});
    public static final BitSet FOLLOW_DASHMATCH_in_attribute1660 = new BitSet(new long[]{0x0000002000800000L,0x0000000000000200L});
    public static final BitSet FOLLOW_CONTAINS_in_attribute1671 = new BitSet(new long[]{0x0000002000800000L,0x0000000000000200L});
    public static final BitSet FOLLOW_STARTSWITH_in_attribute1682 = new BitSet(new long[]{0x0000002000800000L,0x0000000000000200L});
    public static final BitSet FOLLOW_ENDSWITH_in_attribute1693 = new BitSet(new long[]{0x0000002000800000L,0x0000000000000200L});
    public static final BitSet FOLLOW_IDENT_in_attribute1711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_attribute1723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PSEUDO_in_pseudo1756 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_pseudo1760 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PSEUDO_in_pseudo1771 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_FUNCTION_in_pseudo1775 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_IDENT_in_pseudo1779 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PSEUDO_in_pseudo1790 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_FUNCTION_in_pseudo1794 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_NUMBER_in_pseudo1798 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STRING_in_string1820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_STRING_in_string1827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_any1843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_any1849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_any1855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENTAGE_in_any1861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_any1867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_any1873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_URI_in_any1879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_any1885 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_any1891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCLUDES_in_any1897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_any1903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_any1909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_any1915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_any1921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_any1927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_any1933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_any1940 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_any1942 = new BitSet(new long[]{0xC7FD283000800308L,0x0000000000000204L});
    public static final BitSet FOLLOW_DASHMATCH_in_any1951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENBLOCK_in_any1958 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_any1960 = new BitSet(new long[]{0xC7FD283000800308L,0x0000000000000204L});
    public static final BitSet FOLLOW_BRACEBLOCK_in_any1969 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_any1971 = new BitSet(new long[]{0xC7FD283000800308L,0x0000000000000204L});

}