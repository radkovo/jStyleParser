// $ANTLR 3.1.2 /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g 2013-02-09 17:46:20

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
import cz.vutbr.web.css.RuleMargin;
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "STYLESHEET", "INLINESTYLE", "ATBLOCK", "CURLYBLOCK", "PARENBLOCK", "BRACEBLOCK", "RULE", "SELECTOR", "ELEMENT", "PSEUDO", "ADJACENT", "PRECEDING", "CHILD", "DESCENDANT", "ATTRIBUTE", "SET", "DECLARATION", "VALUE", "IMPORTANT", "IMPORT_END", "INVALID_STRING", "INVALID_SELECTOR", "INVALID_SELPART", "INVALID_DECLARATION", "INVALID_STATEMENT", "INVALID_IMPORT", "INVALID_DIRECTIVE", "S", "CDO", "CDC", "CHARSET", "IMPORT", "VIEWPORT", "LCURLY", "RCURLY", "FONTFACE", "MEDIA", "ATKEYWORD", "PAGE", "IDENT", "MARGIN_AREA", "COMMA", "SEMICOLON", "COLON", "EXCLAMATION", "MINUS", "EXPRESSION", "FUNCTION", "RPAREN", "CLASSKEYWORD", "NUMBER", "PERCENTAGE", "DIMENSION", "URI", "HASH", "UNIRANGE", "INCLUDES", "GREATER", "LESS", "QUESTION", "PERCENT", "EQUALS", "SLASH", "PLUS", "ASTERISK", "DASHMATCH", "LPAREN", "LBRACE", "RBRACE", "TILDE", "STARTSWITH", "ENDSWITH", "CONTAINS", "INDEX", "STRING", "STRING_CHAR", "INVALID_TOKEN", "IDENT_MACR", "STRING_MACR", "NAME_MACR", "INTEGER_MACR", "NUMBER_MACR", "W_MACR", "URI_MACR", "APOS", "QUOT", "W_CHAR", "COMMENT", "SL_COMMENT", "NAME_START", "NAME_CHAR", "NON_ASCII", "ESCAPE_CHAR", "URI_CHAR", "NL_CHAR", "'important'", "'#'", "'^'"
    };
    public static final int FUNCTION=51;
    public static final int VIEWPORT=36;
    public static final int APOS=88;
    public static final int CLASSKEYWORD=53;
    public static final int CONTAINS=76;
    public static final int INVALID_STATEMENT=28;
    public static final int INVALID_TOKEN=80;
    public static final int EQUALS=65;
    public static final int MEDIA=40;
    public static final int NL_CHAR=98;
    public static final int EOF=-1;
    public static final int NON_ASCII=95;
    public static final int STYLESHEET=4;
    public static final int INCLUDES=60;
    public static final int ENDSWITH=75;
    public static final int INVALID_DIRECTIVE=30;
    public static final int RPAREN=52;
    public static final int IMPORT=35;
    public static final int GREATER=61;
    public static final int EXCLAMATION=48;
    public static final int PRECEDING=15;
    public static final int INVALID_SELPART=26;
    public static final int LESS=62;
    public static final int INVALID_DECLARATION=27;
    public static final int ELEMENT=12;
    public static final int DIMENSION=56;
    public static final int COMMENT=91;
    public static final int T__99=99;
    public static final int CHILD=16;
    public static final int INVALID_STRING=24;
    public static final int RBRACE=72;
    public static final int RULE=10;
    public static final int PARENBLOCK=8;
    public static final int NUMBER=54;
    public static final int URI_CHAR=97;
    public static final int LCURLY=37;
    public static final int SEMICOLON=46;
    public static final int FONTFACE=39;
    public static final int S=31;
    public static final int VALUE=21;
    public static final int CDO=32;
    public static final int CDC=33;
    public static final int PERCENTAGE=55;
    public static final int INVALID_SELECTOR=25;
    public static final int URI=57;
    public static final int STRING_CHAR=79;
    public static final int DASHMATCH=69;
    public static final int IMPORT_END=23;
    public static final int INLINESTYLE=5;
    public static final int SL_COMMENT=92;
    public static final int MARGIN_AREA=44;
    public static final int INTEGER_MACR=84;
    public static final int IDENT_MACR=81;
    public static final int NAME_CHAR=94;
    public static final int PSEUDO=13;
    public static final int LBRACE=71;
    public static final int ATTRIBUTE=18;
    public static final int NAME_START=93;
    public static final int NUMBER_MACR=85;
    public static final int CHARSET=34;
    public static final int DECLARATION=20;
    public static final int ASTERISK=68;
    public static final int LPAREN=70;
    public static final int BRACEBLOCK=9;
    public static final int INDEX=77;
    public static final int SELECTOR=11;
    public static final int SLASH=66;
    public static final int ATBLOCK=6;
    public static final int COMMA=45;
    public static final int TILDE=73;
    public static final int IDENT=43;
    public static final int UNIRANGE=59;
    public static final int PLUS=67;
    public static final int EXPRESSION=50;
    public static final int CURLYBLOCK=7;
    public static final int ATKEYWORD=41;
    public static final int PERCENT=64;
    public static final int W_CHAR=90;
    public static final int STRING_MACR=82;
    public static final int QUOT=89;
    public static final int W_MACR=86;
    public static final int DESCENDANT=17;
    public static final int HASH=58;
    public static final int SET=19;
    public static final int NAME_MACR=83;
    public static final int T__101=101;
    public static final int T__100=100;
    public static final int MINUS=49;
    public static final int IMPORTANT=22;
    public static final int ESCAPE_CHAR=96;
    public static final int COLON=47;
    public static final int PAGE=42;
    public static final int QUESTION=63;
    public static final int ADJACENT=14;
    public static final int INVALID_IMPORT=29;
    public static final int STARTSWITH=74;
    public static final int RCURLY=38;
    public static final int STRING=78;
    public static final int URI_MACR=87;

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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:105:1: inlinestyle returns [StyleSheet sheet] : ( ^( INLINESTYLE decl= declarations ) | ^( INLINESTYLE (irs= inlineset )+ ) );
    public final StyleSheet inlinestyle() throws RecognitionException {
        StyleSheet sheet = null;

        List<Declaration> decl = null;

        RuleBlock<?> irs = null;



        	logEnter("inlinestyle");
        	sheet = this.stylesheet;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:116:2: ( ^( INLINESTYLE decl= declarations ) | ^( INLINESTYLE (irs= inlineset )+ ) )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:116:5: ^( INLINESTYLE decl= declarations )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:123:6: ^( INLINESTYLE (irs= inlineset )+ )
                    {
                    match(input,INLINESTYLE,FOLLOW_INLINESTYLE_in_inlinestyle78); 

                    match(input, Token.DOWN, null); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:124:5: (irs= inlineset )+
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
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:124:6: irs= inlineset
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:128:1: stylesheet returns [StyleSheet sheet] : ^( STYLESHEET (s= statement )* ) ;
    public final StyleSheet stylesheet() throws RecognitionException {
        StyleSheet sheet = null;

        RuleBlock<?> s = null;



        	logEnter("stylesheet");
        	sheet = this.stylesheet;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:142:2: ( ^( STYLESHEET (s= statement )* ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:142:4: ^( STYLESHEET (s= statement )* )
            {
            match(input,STYLESHEET,FOLLOW_STYLESHEET_in_stylesheet125); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:143:4: (s= statement )*
                loop3:
                do {
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==RULE||LA3_0==IMPORT_END||(LA3_0>=INVALID_STATEMENT && LA3_0<=INVALID_IMPORT)||(LA3_0>=CHARSET && LA3_0<=VIEWPORT)||(LA3_0>=FONTFACE && LA3_0<=MEDIA)||LA3_0==PAGE) ) {
                        alt3=1;
                    }


                    switch (alt3) {
                	case 1 :
                	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:143:5: s= statement
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:147:1: statement returns [RuleBlock<?> stm] : (rs= ruleset | ats= atstatement | INVALID_STATEMENT );
    public final RuleBlock<?> statement() throws RecognitionException {
        statement_stack.push(new statement_scope());
        RuleBlock<?> stm = null;

        RuleBlock<?> rs = null;

        RuleBlock<?> ats = null;



        	logEnter("statement");
        	((statement_scope)statement_stack.peek()).invalid = false;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:168:2: (rs= ruleset | ats= atstatement | INVALID_STATEMENT )
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
            case VIEWPORT:
            case FONTFACE:
            case MEDIA:
            case PAGE:
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:168:4: rs= ruleset
                    {
                    pushFollow(FOLLOW_ruleset_in_statement183);
                    rs=ruleset();

                    state._fsp--;

                    stm =(RuleBlock<?>) rs;

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:169:4: ats= atstatement
                    {
                    pushFollow(FOLLOW_atstatement_in_statement193);
                    ats=atstatement();

                    state._fsp--;

                    stm =(RuleBlock<?>) ats;

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:170:4: INVALID_STATEMENT
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:174:1: atstatement returns [RuleBlock<?> stmnt] : ( CHARSET | INVALID_IMPORT | i= IMPORT | IMPORT_END | ^( PAGE (i= IDENT )? ( ^( PSEUDO i= IDENT ) )? decl= declarations ^( SET (m= margin )* ) ) | ^( VIEWPORT decl= declarations ) | ^( FONTFACE decl= declarations ) | ^( MEDIA (mediaList= media )? (rs= ruleset | INVALID_STATEMENT )* ) );
    public final RuleBlock<?> atstatement() throws RecognitionException {
        atstatement_stack.push(new atstatement_scope());
        RuleBlock<?> stmnt = null;

        CommonTree i=null;
        List<Declaration> decl = null;

        RuleMargin m = null;

        List<String> mediaList = null;

        RuleBlock<?> rs = null;



            logEnter("atstatement");
        	((statement_scope)statement_stack.peek()).insideAtstatement =true;
        	((atstatement_scope)atstatement_stack.peek()).stm = stmnt = null;
        	List<RuleSet> rules = null;
        	List<RuleMargin> margins = null;
        	String name = null;
        	String pseudo = null;
        	Priority mark = preparator.markPriority();

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:191:2: ( CHARSET | INVALID_IMPORT | i= IMPORT | IMPORT_END | ^( PAGE (i= IDENT )? ( ^( PSEUDO i= IDENT ) )? decl= declarations ^( SET (m= margin )* ) ) | ^( VIEWPORT decl= declarations ) | ^( FONTFACE decl= declarations ) | ^( MEDIA (mediaList= media )? (rs= ruleset | INVALID_STATEMENT )* ) )
            int alt10=8;
            switch ( input.LA(1) ) {
            case CHARSET:
                {
                alt10=1;
                }
                break;
            case INVALID_IMPORT:
                {
                alt10=2;
                }
                break;
            case IMPORT:
                {
                alt10=3;
                }
                break;
            case IMPORT_END:
                {
                alt10=4;
                }
                break;
            case PAGE:
                {
                alt10=5;
                }
                break;
            case VIEWPORT:
                {
                alt10=6;
                }
                break;
            case FONTFACE:
                {
                alt10=7;
                }
                break;
            case MEDIA:
                {
                alt10=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:191:4: CHARSET
                    {
                    match(input,CHARSET,FOLLOW_CHARSET_in_atstatement233); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:192:4: INVALID_IMPORT
                    {
                    match(input,INVALID_IMPORT,FOLLOW_INVALID_IMPORT_in_atstatement239); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:193:4: i= IMPORT
                    {
                    i=(CommonTree)match(input,IMPORT,FOLLOW_IMPORT_in_atstatement247); 

                    	    String media = extractText(i);
                    		imports.push(new TreeParserState(media));
                    		
                    		log.info("From imported file: Rules will use these media: {}", 
                    			imports.peek());
                    	  

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:201:4: IMPORT_END
                    {
                    match(input,IMPORT_END,FOLLOW_IMPORT_END_in_atstatement258); 

                    	    imports.pop();
                    		log.info("Imported file was parsed, returing in nesting.");
                    	  

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:205:5: ^( PAGE (i= IDENT )? ( ^( PSEUDO i= IDENT ) )? decl= declarations ^( SET (m= margin )* ) )
                    {
                    match(input,PAGE,FOLLOW_PAGE_in_atstatement267); 

                    match(input, Token.DOWN, null); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:206:7: (i= IDENT )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==IDENT) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:206:8: i= IDENT
                            {
                            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_atstatement278); 
                             name = extractText(i); 

                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:209:7: ( ^( PSEUDO i= IDENT ) )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==PSEUDO) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:209:8: ^( PSEUDO i= IDENT )
                            {
                            match(input,PSEUDO,FOLLOW_PSEUDO_in_atstatement307); 

                            match(input, Token.DOWN, null); 
                            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_atstatement311); 

                            match(input, Token.UP, null); 
                             pseudo = extractText(i); 

                            }
                            break;

                    }

                    pushFollow(FOLLOW_declarations_in_atstatement341);
                    decl=declarations();

                    state._fsp--;

                    match(input,SET,FOLLOW_SET_in_atstatement350); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:213:13: (m= margin )*
                        loop7:
                        do {
                            int alt7=2;
                            int LA7_0 = input.LA(1);

                            if ( (LA7_0==MARGIN_AREA) ) {
                                alt7=1;
                            }


                            switch (alt7) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:213:14: m= margin
                        	    {
                        	    pushFollow(FOLLOW_margin_in_atstatement355);
                        	    m=margin();

                        	    state._fsp--;


                        	            if (m!=null) {
                        	              if (margins == null) margins = new ArrayList<RuleMargin>();
                        	              margins.add(m);
                        	              log.debug("Inserted margin rule #{} into @page", margins.size()+1);
                        	            }
                        	          

                        	    }
                        	    break;

                        	default :
                        	    break loop7;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }

                    match(input, Token.UP, null); 

                          stmnt = preparator.prepareRulePage(decl, margins, name, pseudo);
                        

                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:224:5: ^( VIEWPORT decl= declarations )
                    {
                    match(input,VIEWPORT,FOLLOW_VIEWPORT_in_atstatement379); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_declarations_in_atstatement383);
                    decl=declarations();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     stmnt = preparator.prepareRuleViewport(decl); 

                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:226:5: ^( FONTFACE decl= declarations )
                    {
                    match(input,FONTFACE,FOLLOW_FONTFACE_in_atstatement397); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_declarations_in_atstatement401);
                    decl=declarations();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     stmnt = preparator.prepareRuleFontFace(decl); 

                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:228:4: ^( MEDIA (mediaList= media )? (rs= ruleset | INVALID_STATEMENT )* )
                    {
                    match(input,MEDIA,FOLLOW_MEDIA_in_atstatement414); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:228:12: (mediaList= media )?
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==IDENT) ) {
                            alt8=1;
                        }
                        switch (alt8) {
                            case 1 :
                                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:228:13: mediaList= media
                                {
                                pushFollow(FOLLOW_media_in_atstatement419);
                                mediaList=media();

                                state._fsp--;


                                }
                                break;

                        }

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:229:4: (rs= ruleset | INVALID_STATEMENT )*
                        loop9:
                        do {
                            int alt9=3;
                            int LA9_0 = input.LA(1);

                            if ( (LA9_0==RULE) ) {
                                alt9=1;
                            }
                            else if ( (LA9_0==INVALID_STATEMENT) ) {
                                alt9=2;
                            }


                            switch (alt9) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:229:7: rs= ruleset
                        	    {
                        	    pushFollow(FOLLOW_ruleset_in_atstatement432);
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
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:238:8: INVALID_STATEMENT
                        	    {
                        	    match(input,INVALID_STATEMENT,FOLLOW_INVALID_STATEMENT_in_atstatement443); 
                        	     log.debug("Skiping invalid statement in media"); 

                        	    }
                        	    break;

                        	default :
                        	    break loop9;
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


    // $ANTLR start "margin"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:247:1: margin returns [RuleMargin m] : ^(area= MARGIN_AREA decl= declarations ) ;
    public final RuleMargin margin() throws RecognitionException {
        RuleMargin m = null;

        CommonTree area=null;
        List<Declaration> decl = null;



            logEnter("margin");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:254:2: ( ^(area= MARGIN_AREA decl= declarations ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:254:4: ^(area= MARGIN_AREA decl= declarations )
            {
            area=(CommonTree)match(input,MARGIN_AREA,FOLLOW_MARGIN_AREA_in_margin498); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_declarations_in_margin504);
            decl=declarations();

            state._fsp--;


            match(input, Token.UP, null); 
             m = preparator.prepareRuleMargin(extractText(area).substring(1), decl); 

            }


                logLeave("margin");

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return m;
    }
    // $ANTLR end "margin"


    // $ANTLR start "media"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:259:1: media returns [List<String> affected] : (i= IDENT )+ ;
    public final List<String> media() throws RecognitionException {
        List<String> affected = null;

        CommonTree i=null;


           logEnter("media");
           affected = new ArrayList<String>();

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:268:2: ( (i= IDENT )+ )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:268:4: (i= IDENT )+
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:268:4: (i= IDENT )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==IDENT) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:268:5: i= IDENT
            	    {
            	    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_media538); 

            	    				   String m = extractText(i);
            	    				   if(css.isSupportedMedia(m)) affected.add(m);
            	        

            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:274:1: inlineset returns [RuleBlock<?> is] : ^( RULE (p= pseudo )* decl= declarations ) ;
    public final RuleBlock<?> inlineset() throws RecognitionException {
        RuleBlock<?> is = null;

        Selector.PseudoPage p = null;

        List<Declaration> decl = null;



             logEnter("inlineset");
        	 List<Selector.PseudoPage> pplist = new ArrayList<Selector.PseudoPage>();

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:282:2: ( ^( RULE (p= pseudo )* decl= declarations ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:282:4: ^( RULE (p= pseudo )* decl= declarations )
            {
            match(input,RULE,FOLLOW_RULE_in_inlineset573); 

            match(input, Token.DOWN, null); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:282:11: (p= pseudo )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==PSEUDO) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:282:12: p= pseudo
            	    {
            	    pushFollow(FOLLOW_pseudo_in_inlineset578);
            	    p=pseudo();

            	    state._fsp--;

            	    pplist.add(p);

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            pushFollow(FOLLOW_declarations_in_inlineset586);
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:287:1: ruleset returns [RuleBlock<?> stmnt] : ^( RULE (cs= combined_selector )* decl= declarations ) ;
    public final RuleBlock<?> ruleset() throws RecognitionException {
        RuleBlock<?> stmnt = null;

        CombinedSelector cs = null;

        List<Declaration> decl = null;



            logEnter("ruleset"); 
            List<CombinedSelector> cslist = new ArrayList<CombinedSelector>();

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:308:5: ( ^( RULE (cs= combined_selector )* decl= declarations ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:308:7: ^( RULE (cs= combined_selector )* decl= declarations )
            {
            match(input,RULE,FOLLOW_RULE_in_ruleset639); 

            match(input, Token.DOWN, null); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:309:9: (cs= combined_selector )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==SELECTOR||LA13_0==INVALID_SELECTOR) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:309:10: cs= combined_selector
            	    {
            	    pushFollow(FOLLOW_combined_selector_in_ruleset653);
            	    cs=combined_selector();

            	    state._fsp--;

            	    if(cs!=null && !cs.isEmpty() && !((statement_scope)statement_stack.peek()).invalid) {
            	                cslist.add(cs);
            	                log.debug("Inserted combined selector ({}) into ruleset",  cslist.size());
            	             }   
            	            

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            pushFollow(FOLLOW_declarations_in_ruleset674);
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:319:1: declarations returns [List<Declaration> decl] : ^( SET (d= declaration )* ) ;
    public final List<Declaration> declarations() throws RecognitionException {
        List<Declaration> decl = null;

        Declaration d = null;



        		  logEnter("declarations");
        		  decl = new ArrayList<Declaration>();

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:330:2: ( ^( SET (d= declaration )* ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:330:4: ^( SET (d= declaration )* )
            {
            match(input,SET,FOLLOW_SET_in_declarations715); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:330:10: (d= declaration )*
                loop14:
                do {
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==DECLARATION||LA14_0==INVALID_DECLARATION) ) {
                        alt14=1;
                    }


                    switch (alt14) {
                	case 1 :
                	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:330:11: d= declaration
                	    {
                	    pushFollow(FOLLOW_declaration_in_declarations720);
                	    d=declaration();

                	    state._fsp--;


                	    	     if(d!=null) {
                	                decl.add(d);
                	                log.debug("Inserted declaration #{} ", decl.size()+1);
                	    		 }	
                	    	 

                	    }
                	    break;

                	default :
                	    break loop14;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:340:1: declaration returns [Declaration decl] : ( ^( DECLARATION ( important )? ( INVALID_DIRECTIVE )? property t= terms ) | INVALID_DECLARATION );
    public final Declaration declaration() throws RecognitionException {
        declaration_stack.push(new declaration_scope());
        Declaration decl = null;

        List<Term<?>> t = null;



            logEnter("declaration");
            ((declaration_scope)declaration_stack.peek()).d = decl = rf.createDeclaration();
            ((declaration_scope)declaration_stack.peek()).invalid = false;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:363:3: ( ^( DECLARATION ( important )? ( INVALID_DIRECTIVE )? property t= terms ) | INVALID_DECLARATION )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==DECLARATION) ) {
                alt17=1;
            }
            else if ( (LA17_0==INVALID_DECLARATION) ) {
                alt17=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:363:5: ^( DECLARATION ( important )? ( INVALID_DIRECTIVE )? property t= terms )
                    {
                    match(input,DECLARATION,FOLLOW_DECLARATION_in_declaration764); 

                    match(input, Token.DOWN, null); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:364:6: ( important )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==IMPORTANT) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:364:7: important
                            {
                            pushFollow(FOLLOW_important_in_declaration773);
                            important();

                            state._fsp--;

                             decl.setImportant(true); log.debug("IMPORTANT"); 

                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:365:7: ( INVALID_DIRECTIVE )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==INVALID_DIRECTIVE) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:365:8: INVALID_DIRECTIVE
                            {
                            match(input,INVALID_DIRECTIVE,FOLLOW_INVALID_DIRECTIVE_in_declaration786); 
                             ((declaration_scope)declaration_stack.peek()).invalid =true; 

                            }
                            break;

                    }

                    pushFollow(FOLLOW_property_in_declaration798);
                    property();

                    state._fsp--;

                    pushFollow(FOLLOW_terms_in_declaration809);
                    t=terms();

                    state._fsp--;

                    decl.replaceAll(t);

                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:369:4: INVALID_DECLARATION
                    {
                    match(input,INVALID_DECLARATION,FOLLOW_INVALID_DECLARATION_in_declaration829); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:372:1: important : IMPORTANT ;
    public final void important() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:373:5: ( IMPORTANT )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:373:7: IMPORTANT
            {
            match(input,IMPORTANT,FOLLOW_IMPORTANT_in_important846); 

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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:376:1: property : (i= IDENT | MINUS i= IDENT );
    public final void property() throws RecognitionException {
        CommonTree i=null;


            logEnter("property");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:387:3: (i= IDENT | MINUS i= IDENT )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==IDENT) ) {
                alt18=1;
            }
            else if ( (LA18_0==MINUS) ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:387:5: i= IDENT
                    {
                    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_property886); 
                     ((declaration_scope)declaration_stack.peek()).d.setProperty(extractText(i)); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:388:5: MINUS i= IDENT
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_property894); 
                    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_property900); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:391:1: terms returns [List<Term<?>> tlist] : ^( VALUE ( term )+ ) ;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:414:5: ( ^( VALUE ( term )+ ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:414:7: ^( VALUE ( term )+ )
            {
            match(input,VALUE,FOLLOW_VALUE_in_terms945); 

            match(input, Token.DOWN, null); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:414:15: ( term )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>=CURLYBLOCK && LA19_0<=BRACEBLOCK)||LA19_0==INVALID_STRING||LA19_0==ATKEYWORD||LA19_0==IDENT||LA19_0==COMMA||LA19_0==COLON||(LA19_0>=MINUS && LA19_0<=FUNCTION)||(LA19_0>=CLASSKEYWORD && LA19_0<=DASHMATCH)||LA19_0==STRING) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:414:15: term
            	    {
            	    pushFollow(FOLLOW_term_in_terms947);
            	    term();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt19 >= 1 ) break loop19;
                        EarlyExitException eee =
                            new EarlyExitException(19, input);
                        throw eee;
                }
                cnt19++;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:417:1: term : ( valuepart | CURLYBLOCK | ATKEYWORD );
    public final void term() throws RecognitionException {

          logEnter("term");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:421:5: ( valuepart | CURLYBLOCK | ATKEYWORD )
            int alt20=3;
            switch ( input.LA(1) ) {
            case PARENBLOCK:
            case BRACEBLOCK:
            case INVALID_STRING:
            case IDENT:
            case COMMA:
            case COLON:
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
                alt20=1;
                }
                break;
            case CURLYBLOCK:
                {
                alt20=2;
                }
                break;
            case ATKEYWORD:
                {
                alt20=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }

            switch (alt20) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:421:7: valuepart
                    {
                    pushFollow(FOLLOW_valuepart_in_term975);
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:433:7: CURLYBLOCK
                    {
                    match(input,CURLYBLOCK,FOLLOW_CURLYBLOCK_in_term992); 
                     ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:434:7: ATKEYWORD
                    {
                    match(input,ATKEYWORD,FOLLOW_ATKEYWORD_in_term1002); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:437:1: valuepart : ( ( MINUS )? i= IDENT | CLASSKEYWORD | ( MINUS )? n= NUMBER | ( MINUS )? p= PERCENTAGE | ( MINUS )? d= DIMENSION | s= string | u= URI | h= HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | LESS | QUESTION | PERCENT | EQUALS | SLASH | PLUS | ASTERISK | e= EXPRESSION | ( MINUS )? ^(f= FUNCTION (t= terms )? ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) );
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:459:5: ( ( MINUS )? i= IDENT | CLASSKEYWORD | ( MINUS )? n= NUMBER | ( MINUS )? p= PERCENTAGE | ( MINUS )? d= DIMENSION | s= string | u= URI | h= HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | LESS | QUESTION | PERCENT | EQUALS | SLASH | PLUS | ASTERISK | e= EXPRESSION | ( MINUS )? ^(f= FUNCTION (t= terms )? ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) )
            int alt29=25;
            alt29 = dfa29.predict(input);
            switch (alt29) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:459:7: ( MINUS )? i= IDENT
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:459:7: ( MINUS )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==MINUS) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:459:8: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart1029); 
                            ((terms_scope)terms_stack.peek()).dash =true;

                            }
                            break;

                    }

                    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_valuepart1037); 
                    ((terms_scope)terms_stack.peek()).term = tf.createIdent(extractText(i), ((terms_scope)terms_stack.peek()).dash);

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:460:7: CLASSKEYWORD
                    {
                    match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_valuepart1049); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:461:6: ( MINUS )? n= NUMBER
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:461:6: ( MINUS )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==MINUS) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:461:7: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart1059); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    n=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_valuepart1067); 
                    ((terms_scope)terms_stack.peek()).term = tf.createNumeric(extractText(n), ((terms_scope)terms_stack.peek()).unary);

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:462:7: ( MINUS )? p= PERCENTAGE
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:462:7: ( MINUS )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==MINUS) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:462:8: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart1081); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    p=(CommonTree)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_valuepart1089); 
                     ((terms_scope)terms_stack.peek()).term = tf.createPercent(extractText(p), ((terms_scope)terms_stack.peek()).unary);

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:463:7: ( MINUS )? d= DIMENSION
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:463:7: ( MINUS )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==MINUS) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:463:8: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart1101); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    d=(CommonTree)match(input,DIMENSION,FOLLOW_DIMENSION_in_valuepart1109); 
                    String dim = extractText(d);
                    				 ((terms_scope)terms_stack.peek()).term = tf.createDimension(dim, ((terms_scope)terms_stack.peek()).unary);
                    			     if(((terms_scope)terms_stack.peek()).term==null) {
                    					 log.info("Unable to create dimension from {}, unary {}", dim, ((terms_scope)terms_stack.peek()).unary);
                    			         ((declaration_scope)declaration_stack.peek()).invalid = true;
                    				 }
                    	    

                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:471:7: s= string
                    {
                    pushFollow(FOLLOW_string_in_valuepart1127);
                    s=string();

                    state._fsp--;

                     if(s!=null) ((terms_scope)terms_stack.peek()).term = tf.createString(s);
                    			  else ((declaration_scope)declaration_stack.peek()).invalid =true;
                    			

                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:475:7: u= URI
                    {
                    u=(CommonTree)match(input,URI,FOLLOW_URI_in_valuepart1146); 
                    ((terms_scope)terms_stack.peek()).term = tf.createURI(extractText(u), extractBase(u));

                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:476:7: h= HASH
                    {
                    h=(CommonTree)match(input,HASH,FOLLOW_HASH_in_valuepart1164); 
                    ((terms_scope)terms_stack.peek()).term = tf.createColor(extractText(h));
                    	     if(((terms_scope)terms_stack.peek()).term==null)
                    	         ((declaration_scope)declaration_stack.peek()).invalid = true;
                    	    

                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:481:7: UNIRANGE
                    {
                    match(input,UNIRANGE,FOLLOW_UNIRANGE_in_valuepart1183); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:482:7: INCLUDES
                    {
                    match(input,INCLUDES,FOLLOW_INCLUDES_in_valuepart1194); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:483:7: COLON
                    {
                    match(input,COLON,FOLLOW_COLON_in_valuepart1205); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:484:7: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_valuepart1219); 
                    ((terms_scope)terms_stack.peek()).op = Term.Operator.COMMA;

                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:485:7: GREATER
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_valuepart1237); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:486:7: LESS
                    {
                    match(input,LESS,FOLLOW_LESS_in_valuepart1249); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:487:7: QUESTION
                    {
                    match(input,QUESTION,FOLLOW_QUESTION_in_valuepart1264); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:488:7: PERCENT
                    {
                    match(input,PERCENT,FOLLOW_PERCENT_in_valuepart1275); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:489:7: EQUALS
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_valuepart1287); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 18 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:490:7: SLASH
                    {
                    match(input,SLASH,FOLLOW_SLASH_in_valuepart1300); 
                    ((terms_scope)terms_stack.peek()).op = Term.Operator.SLASH;

                    }
                    break;
                case 19 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:491:5: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_valuepart1312); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 20 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:492:5: ASTERISK
                    {
                    match(input,ASTERISK,FOLLOW_ASTERISK_in_valuepart1323); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 21 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:493:5: e= EXPRESSION
                    {
                    e=(CommonTree)match(input,EXPRESSION,FOLLOW_EXPRESSION_in_valuepart1334); 

                    		    String exprval = extractText(e);
                            TermExpression expr = tf.createExpression(exprval.substring(11,exprval.length()-1)); //strip the 'expression()'
                            ((terms_scope)terms_stack.peek()).term = expr;
                    		

                    }
                    break;
                case 22 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:498:7: ( MINUS )? ^(f= FUNCTION (t= terms )? )
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:498:7: ( MINUS )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==MINUS) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:498:8: MINUS
                            {
                            match(input,MINUS,FOLLOW_MINUS_in_valuepart1345); 
                            ((terms_scope)terms_stack.peek()).unary =-1;

                            }
                            break;

                    }

                    f=(CommonTree)match(input,FUNCTION,FOLLOW_FUNCTION_in_valuepart1354); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:498:50: (t= terms )?
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( (LA26_0==VALUE) ) {
                            alt26=1;
                        }
                        switch (alt26) {
                            case 1 :
                                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:498:50: t= terms
                                {
                                pushFollow(FOLLOW_terms_in_valuepart1358);
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:508:7: DASHMATCH
                    {
                    match(input,DASHMATCH,FOLLOW_DASHMATCH_in_valuepart1370); 
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 24 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:509:7: ^( PARENBLOCK ( any )* )
                    {
                    match(input,PARENBLOCK,FOLLOW_PARENBLOCK_in_valuepart1381); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:509:20: ( any )*
                        loop27:
                        do {
                            int alt27=2;
                            int LA27_0 = input.LA(1);

                            if ( ((LA27_0>=PARENBLOCK && LA27_0<=BRACEBLOCK)||LA27_0==INVALID_STRING||LA27_0==IDENT||LA27_0==COMMA||(LA27_0>=COLON && LA27_0<=EXCLAMATION)||LA27_0==FUNCTION||(LA27_0>=CLASSKEYWORD && LA27_0<=GREATER)||(LA27_0>=EQUALS && LA27_0<=SLASH)||LA27_0==DASHMATCH||LA27_0==STRING) ) {
                                alt27=1;
                            }


                            switch (alt27) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:509:20: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_valuepart1383);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop27;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }
                    ((declaration_scope)declaration_stack.peek()).invalid = true;

                    }
                    break;
                case 25 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:510:7: ^( BRACEBLOCK ( any )* )
                    {
                    match(input,BRACEBLOCK,FOLLOW_BRACEBLOCK_in_valuepart1396); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:510:20: ( any )*
                        loop28:
                        do {
                            int alt28=2;
                            int LA28_0 = input.LA(1);

                            if ( ((LA28_0>=PARENBLOCK && LA28_0<=BRACEBLOCK)||LA28_0==INVALID_STRING||LA28_0==IDENT||LA28_0==COMMA||(LA28_0>=COLON && LA28_0<=EXCLAMATION)||LA28_0==FUNCTION||(LA28_0>=CLASSKEYWORD && LA28_0<=GREATER)||(LA28_0>=EQUALS && LA28_0<=SLASH)||LA28_0==DASHMATCH||LA28_0==STRING) ) {
                                alt28=1;
                            }


                            switch (alt28) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:510:20: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_valuepart1398);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop28;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:513:1: combined_selector returns [CombinedSelector combinedSelector] : s= selector (c= combinator s= selector )* ;
    public final CombinedSelector combined_selector() throws RecognitionException {
        combined_selector_stack.push(new combined_selector_scope());
        CombinedSelector combinedSelector = null;

        Selector s = null;

        Selector.Combinator c = null;



        	logEnter("combined_selector");	  
        	combinedSelector = (CombinedSelector) rf.createCombinedSelector().unlock();

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:543:2: (s= selector (c= combinator s= selector )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:543:4: s= selector (c= combinator s= selector )*
            {
            pushFollow(FOLLOW_selector_in_combined_selector1446);
            s=selector();

            state._fsp--;


            	     combinedSelector.add(s);
            	  
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:546:3: (c= combinator s= selector )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( ((LA30_0>=ADJACENT && LA30_0<=DESCENDANT)) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:546:4: c= combinator s= selector
            	    {
            	    pushFollow(FOLLOW_combinator_in_combined_selector1455);
            	    c=combinator();

            	    state._fsp--;

            	    pushFollow(FOLLOW_selector_in_combined_selector1459);
            	    s=selector();

            	    state._fsp--;


            	    	     s.setCombinator(c);
            	    	     combinedSelector.add(s);	
            	    	  

            	    }
            	    break;

            	default :
            	    break loop30;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:553:1: combinator returns [Selector.Combinator combinator] : ( CHILD | ADJACENT | PRECEDING | DESCENDANT );
    public final Selector.Combinator combinator() throws RecognitionException {
        Selector.Combinator combinator = null;

         logEnter("combinator"); 
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:556:2: ( CHILD | ADJACENT | PRECEDING | DESCENDANT )
            int alt31=4;
            switch ( input.LA(1) ) {
            case CHILD:
                {
                alt31=1;
                }
                break;
            case ADJACENT:
                {
                alt31=2;
                }
                break;
            case PRECEDING:
                {
                alt31=3;
                }
                break;
            case DESCENDANT:
                {
                alt31=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }

            switch (alt31) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:556:4: CHILD
                    {
                    match(input,CHILD,FOLLOW_CHILD_in_combinator1489); 
                    combinator =Selector.Combinator.CHILD;

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:557:4: ADJACENT
                    {
                    match(input,ADJACENT,FOLLOW_ADJACENT_in_combinator1496); 
                    combinator =Selector.Combinator.ADJACENT;

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:558:5: PRECEDING
                    {
                    match(input,PRECEDING,FOLLOW_PRECEDING_in_combinator1504); 
                    combinator =Selector.Combinator.PRECEDING;

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:559:4: DESCENDANT
                    {
                    match(input,DESCENDANT,FOLLOW_DESCENDANT_in_combinator1511); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:563:1: selector returns [Selector sel] : ( ^( SELECTOR ^( ELEMENT (i= IDENT )? ) ( selpart )* ) | ^( SELECTOR ( selpart )+ ) | INVALID_SELECTOR );
    public final Selector selector() throws RecognitionException {
        selector_stack.push(new selector_scope());
        Selector sel = null;

        CommonTree i=null;


        	logEnter("selector");
        	((selector_scope)selector_stack.peek()).s =sel=(Selector)rf.createSelector().unlock();
        	Selector.ElementName en = rf.createElement(Selector.ElementName.WILDCARD);

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:575:5: ( ^( SELECTOR ^( ELEMENT (i= IDENT )? ) ( selpart )* ) | ^( SELECTOR ( selpart )+ ) | INVALID_SELECTOR )
            int alt35=3;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==SELECTOR) ) {
                int LA35_1 = input.LA(2);

                if ( (LA35_1==DOWN) ) {
                    int LA35_3 = input.LA(3);

                    if ( (LA35_3==ELEMENT) ) {
                        alt35=1;
                    }
                    else if ( (LA35_3==PSEUDO||LA35_3==ATTRIBUTE||LA35_3==INVALID_SELPART||LA35_3==CLASSKEYWORD||LA35_3==HASH) ) {
                        alt35=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 35, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 35, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA35_0==INVALID_SELECTOR) ) {
                alt35=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:575:7: ^( SELECTOR ^( ELEMENT (i= IDENT )? ) ( selpart )* )
                    {
                    match(input,SELECTOR,FOLLOW_SELECTOR_in_selector1547); 

                    match(input, Token.DOWN, null); 
                    match(input,ELEMENT,FOLLOW_ELEMENT_in_selector1559); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:577:11: (i= IDENT )?
                        int alt32=2;
                        int LA32_0 = input.LA(1);

                        if ( (LA32_0==IDENT) ) {
                            alt32=1;
                        }
                        switch (alt32) {
                            case 1 :
                                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:577:12: i= IDENT
                                {
                                i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_selector1575); 
                                 en.setName(extractText(i)); 

                                }
                                break;

                        }


                        match(input, Token.UP, null); 
                    }

                    		  log.debug("Adding element name: {}.", en.getName());
                    		  ((selector_scope)selector_stack.peek()).s.add(en);
                    		 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:583:10: ( selpart )*
                    loop33:
                    do {
                        int alt33=2;
                        int LA33_0 = input.LA(1);

                        if ( (LA33_0==PSEUDO||LA33_0==ATTRIBUTE||LA33_0==INVALID_SELPART||LA33_0==CLASSKEYWORD||LA33_0==HASH) ) {
                            alt33=1;
                        }


                        switch (alt33) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:583:10: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1622);
                    	    selpart();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop33;
                        }
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:585:7: ^( SELECTOR ( selpart )+ )
                    {
                    match(input,SELECTOR,FOLLOW_SELECTOR_in_selector1641); 

                    match(input, Token.DOWN, null); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:586:10: ( selpart )+
                    int cnt34=0;
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==PSEUDO||LA34_0==ATTRIBUTE||LA34_0==INVALID_SELPART||LA34_0==CLASSKEYWORD||LA34_0==HASH) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:586:10: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1653);
                    	    selpart();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt34 >= 1 ) break loop34;
                                EarlyExitException eee =
                                    new EarlyExitException(34, input);
                                throw eee;
                        }
                        cnt34++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:588:7: INVALID_SELECTOR
                    {
                    match(input,INVALID_SELECTOR,FOLLOW_INVALID_SELECTOR_in_selector1671); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:591:1: selpart : (h= HASH | c= CLASSKEYWORD | ^( ATTRIBUTE ea= attribute ) | p= pseudo | INVALID_SELPART );
    public final void selpart() throws RecognitionException {
        CommonTree h=null;
        CommonTree c=null;
        Selector.ElementAttribute ea = null;

        Selector.PseudoPage p = null;



        	logEnter("selpart");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:598:5: (h= HASH | c= CLASSKEYWORD | ^( ATTRIBUTE ea= attribute ) | p= pseudo | INVALID_SELPART )
            int alt36=5;
            switch ( input.LA(1) ) {
            case HASH:
                {
                alt36=1;
                }
                break;
            case CLASSKEYWORD:
                {
                alt36=2;
                }
                break;
            case ATTRIBUTE:
                {
                alt36=3;
                }
                break;
            case PSEUDO:
                {
                alt36=4;
                }
                break;
            case INVALID_SELPART:
                {
                alt36=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }

            switch (alt36) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:598:8: h= HASH
                    {
                    h=(CommonTree)match(input,HASH,FOLLOW_HASH_in_selpart1705); 
                     ((selector_scope)selector_stack.peek()).s.add(rf.createID(extractText(h))); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:599:7: c= CLASSKEYWORD
                    {
                    c=(CommonTree)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_selpart1717); 
                     ((selector_scope)selector_stack.peek()).s.add(rf.createClass(extractText(c))); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:600:4: ^( ATTRIBUTE ea= attribute )
                    {
                    match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_selpart1725); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_attribute_in_selpart1729);
                    ea=attribute();

                    state._fsp--;

                     ((selector_scope)selector_stack.peek()).s.add(ea);

                    match(input, Token.UP, null); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:601:7: p= pseudo
                    {
                    pushFollow(FOLLOW_pseudo_in_selpart1743);
                    p=pseudo();

                    state._fsp--;

                     ((selector_scope)selector_stack.peek()).s.add(p);

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:602:4: INVALID_SELPART
                    {
                    match(input,INVALID_SELPART,FOLLOW_INVALID_SELPART_in_selpart1750); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:605:1: attribute returns [Selector.ElementAttribute elemAttr] : i= IDENT ( ( EQUALS | INCLUDES | DASHMATCH | CONTAINS | STARTSWITH | ENDSWITH ) (i= IDENT | s= string ) )? ;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:623:2: (i= IDENT ( ( EQUALS | INCLUDES | DASHMATCH | CONTAINS | STARTSWITH | ENDSWITH ) (i= IDENT | s= string ) )? )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:623:4: i= IDENT ( ( EQUALS | INCLUDES | DASHMATCH | CONTAINS | STARTSWITH | ENDSWITH ) (i= IDENT | s= string ) )?
            {
            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_attribute1784); 
            attribute=extractText(i); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:624:4: ( ( EQUALS | INCLUDES | DASHMATCH | CONTAINS | STARTSWITH | ENDSWITH ) (i= IDENT | s= string ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==INCLUDES||LA39_0==EQUALS||LA39_0==DASHMATCH||(LA39_0>=STARTSWITH && LA39_0<=CONTAINS)) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:624:5: ( EQUALS | INCLUDES | DASHMATCH | CONTAINS | STARTSWITH | ENDSWITH ) (i= IDENT | s= string )
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:624:5: ( EQUALS | INCLUDES | DASHMATCH | CONTAINS | STARTSWITH | ENDSWITH )
                    int alt37=6;
                    switch ( input.LA(1) ) {
                    case EQUALS:
                        {
                        alt37=1;
                        }
                        break;
                    case INCLUDES:
                        {
                        alt37=2;
                        }
                        break;
                    case DASHMATCH:
                        {
                        alt37=3;
                        }
                        break;
                    case CONTAINS:
                        {
                        alt37=4;
                        }
                        break;
                    case STARTSWITH:
                        {
                        alt37=5;
                        }
                        break;
                    case ENDSWITH:
                        {
                        alt37=6;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 37, 0, input);

                        throw nvae;
                    }

                    switch (alt37) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:624:6: EQUALS
                            {
                            match(input,EQUALS,FOLLOW_EQUALS_in_attribute1793); 
                            op=Selector.Operator.EQUALS; 

                            }
                            break;
                        case 2 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:625:7: INCLUDES
                            {
                            match(input,INCLUDES,FOLLOW_INCLUDES_in_attribute1804); 
                            op=Selector.Operator.INCLUDES; 

                            }
                            break;
                        case 3 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:626:7: DASHMATCH
                            {
                            match(input,DASHMATCH,FOLLOW_DASHMATCH_in_attribute1815); 
                            op=Selector.Operator.DASHMATCH; 

                            }
                            break;
                        case 4 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:627:8: CONTAINS
                            {
                            match(input,CONTAINS,FOLLOW_CONTAINS_in_attribute1826); 
                            op=Selector.Operator.CONTAINS; 

                            }
                            break;
                        case 5 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:628:8: STARTSWITH
                            {
                            match(input,STARTSWITH,FOLLOW_STARTSWITH_in_attribute1837); 
                            op=Selector.Operator.STARTSWITH; 

                            }
                            break;
                        case 6 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:629:8: ENDSWITH
                            {
                            match(input,ENDSWITH,FOLLOW_ENDSWITH_in_attribute1848); 
                            op=Selector.Operator.ENDSWITH; 

                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:631:5: (i= IDENT | s= string )
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==IDENT) ) {
                        alt38=1;
                    }
                    else if ( (LA38_0==INVALID_STRING||LA38_0==STRING) ) {
                        alt38=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 38, 0, input);

                        throw nvae;
                    }
                    switch (alt38) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:631:6: i= IDENT
                            {
                            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_attribute1866); 

                            		value=extractText(i);
                            		isStringValue=false;
                            		

                            }
                            break;
                        case 2 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:635:7: s= string
                            {
                            pushFollow(FOLLOW_string_in_attribute1878);
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:647:1: pseudo returns [Selector.PseudoPage pseudoPage] : ( ^( PSEUDO i= IDENT ) | ^( PSEUDO f= FUNCTION i= IDENT ) | ^( PSEUDO f= FUNCTION n= NUMBER ) | ^( PSEUDO f= FUNCTION n= INDEX ) );
    public final Selector.PseudoPage pseudo() throws RecognitionException {
        Selector.PseudoPage pseudoPage = null;

        CommonTree i=null;
        CommonTree f=null;
        CommonTree n=null;


        		logEnter("pseudo");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:651:2: ( ^( PSEUDO i= IDENT ) | ^( PSEUDO f= FUNCTION i= IDENT ) | ^( PSEUDO f= FUNCTION n= NUMBER ) | ^( PSEUDO f= FUNCTION n= INDEX ) )
            int alt40=4;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==PSEUDO) ) {
                int LA40_1 = input.LA(2);

                if ( (LA40_1==DOWN) ) {
                    int LA40_2 = input.LA(3);

                    if ( (LA40_2==IDENT) ) {
                        alt40=1;
                    }
                    else if ( (LA40_2==FUNCTION) ) {
                        switch ( input.LA(4) ) {
                        case IDENT:
                            {
                            alt40=2;
                            }
                            break;
                        case NUMBER:
                            {
                            alt40=3;
                            }
                            break;
                        case INDEX:
                            {
                            alt40=4;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 40, 4, input);

                            throw nvae;
                        }

                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 40, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 40, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }
            switch (alt40) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:651:4: ^( PSEUDO i= IDENT )
                    {
                    match(input,PSEUDO,FOLLOW_PSEUDO_in_pseudo1911); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_pseudo1915); 

                    match(input, Token.UP, null); 

                    			pseudoPage = rf.createPseudoPage(extractText(i), null);
                    		

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:655:4: ^( PSEUDO f= FUNCTION i= IDENT )
                    {
                    match(input,PSEUDO,FOLLOW_PSEUDO_in_pseudo1926); 

                    match(input, Token.DOWN, null); 
                    f=(CommonTree)match(input,FUNCTION,FOLLOW_FUNCTION_in_pseudo1930); 
                    i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_pseudo1934); 

                    match(input, Token.UP, null); 

                    			pseudoPage = rf.createPseudoPage(extractText(i), extractText(f));
                    		

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:659:4: ^( PSEUDO f= FUNCTION n= NUMBER )
                    {
                    match(input,PSEUDO,FOLLOW_PSEUDO_in_pseudo1945); 

                    match(input, Token.DOWN, null); 
                    f=(CommonTree)match(input,FUNCTION,FOLLOW_FUNCTION_in_pseudo1949); 
                    n=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_pseudo1953); 

                    match(input, Token.UP, null); 

                    			pseudoPage = rf.createPseudoPage(extractText(n), extractText(f));
                    		

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:663:5: ^( PSEUDO f= FUNCTION n= INDEX )
                    {
                    match(input,PSEUDO,FOLLOW_PSEUDO_in_pseudo1965); 

                    match(input, Token.DOWN, null); 
                    f=(CommonTree)match(input,FUNCTION,FOLLOW_FUNCTION_in_pseudo1969); 
                    n=(CommonTree)match(input,INDEX,FOLLOW_INDEX_in_pseudo1973); 

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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:669:1: string returns [String s] : (st= STRING | INVALID_STRING );
    public final String string() throws RecognitionException {
        String s = null;

        CommonTree st=null;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:670:2: (st= STRING | INVALID_STRING )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==STRING) ) {
                alt41=1;
            }
            else if ( (LA41_0==INVALID_STRING) ) {
                alt41=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:670:4: st= STRING
                    {
                    st=(CommonTree)match(input,STRING,FOLLOW_STRING_in_string1997); 
                     s = extractText(st);

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:671:4: INVALID_STRING
                    {
                    match(input,INVALID_STRING,FOLLOW_INVALID_STRING_in_string2004); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:674:1: any : ( IDENT | CLASSKEYWORD | NUMBER | PERCENTAGE | DIMENSION | string | URI | HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | EXCLAMATION | ^( FUNCTION ( any )* ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) );
    public final void any() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:675:3: ( IDENT | CLASSKEYWORD | NUMBER | PERCENTAGE | DIMENSION | string | URI | HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | EQUALS | SLASH | EXCLAMATION | ^( FUNCTION ( any )* ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) )
            int alt45=20;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt45=1;
                }
                break;
            case CLASSKEYWORD:
                {
                alt45=2;
                }
                break;
            case NUMBER:
                {
                alt45=3;
                }
                break;
            case PERCENTAGE:
                {
                alt45=4;
                }
                break;
            case DIMENSION:
                {
                alt45=5;
                }
                break;
            case INVALID_STRING:
            case STRING:
                {
                alt45=6;
                }
                break;
            case URI:
                {
                alt45=7;
                }
                break;
            case HASH:
                {
                alt45=8;
                }
                break;
            case UNIRANGE:
                {
                alt45=9;
                }
                break;
            case INCLUDES:
                {
                alt45=10;
                }
                break;
            case COLON:
                {
                alt45=11;
                }
                break;
            case COMMA:
                {
                alt45=12;
                }
                break;
            case GREATER:
                {
                alt45=13;
                }
                break;
            case EQUALS:
                {
                alt45=14;
                }
                break;
            case SLASH:
                {
                alt45=15;
                }
                break;
            case EXCLAMATION:
                {
                alt45=16;
                }
                break;
            case FUNCTION:
                {
                alt45=17;
                }
                break;
            case DASHMATCH:
                {
                alt45=18;
                }
                break;
            case PARENBLOCK:
                {
                alt45=19;
                }
                break;
            case BRACEBLOCK:
                {
                alt45=20;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;
            }

            switch (alt45) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:675:5: IDENT
                    {
                    match(input,IDENT,FOLLOW_IDENT_in_any2020); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:676:5: CLASSKEYWORD
                    {
                    match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_any2026); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:677:5: NUMBER
                    {
                    match(input,NUMBER,FOLLOW_NUMBER_in_any2032); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:678:5: PERCENTAGE
                    {
                    match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_any2038); 

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:679:5: DIMENSION
                    {
                    match(input,DIMENSION,FOLLOW_DIMENSION_in_any2044); 

                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:680:5: string
                    {
                    pushFollow(FOLLOW_string_in_any2050);
                    string();

                    state._fsp--;


                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:681:5: URI
                    {
                    match(input,URI,FOLLOW_URI_in_any2056); 

                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:682:5: HASH
                    {
                    match(input,HASH,FOLLOW_HASH_in_any2062); 

                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:683:5: UNIRANGE
                    {
                    match(input,UNIRANGE,FOLLOW_UNIRANGE_in_any2068); 

                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:684:5: INCLUDES
                    {
                    match(input,INCLUDES,FOLLOW_INCLUDES_in_any2074); 

                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:685:5: COLON
                    {
                    match(input,COLON,FOLLOW_COLON_in_any2080); 

                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:686:5: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_any2086); 

                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:687:5: GREATER
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_any2092); 

                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:688:5: EQUALS
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_any2098); 

                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:689:5: SLASH
                    {
                    match(input,SLASH,FOLLOW_SLASH_in_any2104); 

                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:690:5: EXCLAMATION
                    {
                    match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_any2110); 

                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:691:5: ^( FUNCTION ( any )* )
                    {
                    match(input,FUNCTION,FOLLOW_FUNCTION_in_any2117); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:691:16: ( any )*
                        loop42:
                        do {
                            int alt42=2;
                            int LA42_0 = input.LA(1);

                            if ( ((LA42_0>=PARENBLOCK && LA42_0<=BRACEBLOCK)||LA42_0==INVALID_STRING||LA42_0==IDENT||LA42_0==COMMA||(LA42_0>=COLON && LA42_0<=EXCLAMATION)||LA42_0==FUNCTION||(LA42_0>=CLASSKEYWORD && LA42_0<=GREATER)||(LA42_0>=EQUALS && LA42_0<=SLASH)||LA42_0==DASHMATCH||LA42_0==STRING) ) {
                                alt42=1;
                            }


                            switch (alt42) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:691:16: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_any2119);
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
                case 18 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:692:5: DASHMATCH
                    {
                    match(input,DASHMATCH,FOLLOW_DASHMATCH_in_any2128); 

                    }
                    break;
                case 19 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:693:5: ^( PARENBLOCK ( any )* )
                    {
                    match(input,PARENBLOCK,FOLLOW_PARENBLOCK_in_any2135); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:693:18: ( any )*
                        loop43:
                        do {
                            int alt43=2;
                            int LA43_0 = input.LA(1);

                            if ( ((LA43_0>=PARENBLOCK && LA43_0<=BRACEBLOCK)||LA43_0==INVALID_STRING||LA43_0==IDENT||LA43_0==COMMA||(LA43_0>=COLON && LA43_0<=EXCLAMATION)||LA43_0==FUNCTION||(LA43_0>=CLASSKEYWORD && LA43_0<=GREATER)||(LA43_0>=EQUALS && LA43_0<=SLASH)||LA43_0==DASHMATCH||LA43_0==STRING) ) {
                                alt43=1;
                            }


                            switch (alt43) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:693:18: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_any2137);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop43;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }

                    }
                    break;
                case 20 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:694:5: ^( BRACEBLOCK ( any )* )
                    {
                    match(input,BRACEBLOCK,FOLLOW_BRACEBLOCK_in_any2146); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:694:18: ( any )*
                        loop44:
                        do {
                            int alt44=2;
                            int LA44_0 = input.LA(1);

                            if ( ((LA44_0>=PARENBLOCK && LA44_0<=BRACEBLOCK)||LA44_0==INVALID_STRING||LA44_0==IDENT||LA44_0==COMMA||(LA44_0>=COLON && LA44_0<=EXCLAMATION)||LA44_0==FUNCTION||(LA44_0>=CLASSKEYWORD && LA44_0<=GREATER)||(LA44_0>=EQUALS && LA44_0<=SLASH)||LA44_0==DASHMATCH||LA44_0==STRING) ) {
                                alt44=1;
                            }


                            switch (alt44) {
                        	case 1 :
                        	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSSTreeParser.g:694:18: any
                        	    {
                        	    pushFollow(FOLLOW_any_in_any2148);
                        	    any();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop44;
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


    protected DFA29 dfa29 = new DFA29(this);
    static final String DFA29_eotS =
        "\33\uffff";
    static final String DFA29_eofS =
        "\33\uffff";
    static final String DFA29_minS =
        "\1\10\1\53\31\uffff";
    static final String DFA29_maxS =
        "\1\116\1\70\31\uffff";
    static final String DFA29_acceptS =
        "\2\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31";
    static final String DFA29_specialS =
        "\33\uffff}>";
    static final String[] DFA29_transitionS = {
            "\1\31\1\32\16\uffff\1\7\22\uffff\1\2\1\uffff\1\15\1\uffff\1"+
            "\14\1\uffff\1\1\1\26\1\27\1\uffff\1\3\1\4\1\5\1\6\1\10\1\11"+
            "\1\12\1\13\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\30\10\uffff"+
            "\1\7",
            "\1\2\7\uffff\1\27\2\uffff\1\4\1\5\1\6",
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

    static final short[] DFA29_eot = DFA.unpackEncodedString(DFA29_eotS);
    static final short[] DFA29_eof = DFA.unpackEncodedString(DFA29_eofS);
    static final char[] DFA29_min = DFA.unpackEncodedStringToUnsignedChars(DFA29_minS);
    static final char[] DFA29_max = DFA.unpackEncodedStringToUnsignedChars(DFA29_maxS);
    static final short[] DFA29_accept = DFA.unpackEncodedString(DFA29_acceptS);
    static final short[] DFA29_special = DFA.unpackEncodedString(DFA29_specialS);
    static final short[][] DFA29_transition;

    static {
        int numStates = DFA29_transitionS.length;
        DFA29_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA29_transition[i] = DFA.unpackEncodedString(DFA29_transitionS[i]);
        }
    }

    class DFA29 extends DFA {

        public DFA29(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 29;
            this.eot = DFA29_eot;
            this.eof = DFA29_eof;
            this.min = DFA29_min;
            this.max = DFA29_max;
            this.accept = DFA29_accept;
            this.special = DFA29_special;
            this.transition = DFA29_transition;
        }
        public String getDescription() {
            return "437:1: valuepart : ( ( MINUS )? i= IDENT | CLASSKEYWORD | ( MINUS )? n= NUMBER | ( MINUS )? p= PERCENTAGE | ( MINUS )? d= DIMENSION | s= string | u= URI | h= HASH | UNIRANGE | INCLUDES | COLON | COMMA | GREATER | LESS | QUESTION | PERCENT | EQUALS | SLASH | PLUS | ASTERISK | e= EXPRESSION | ( MINUS )? ^(f= FUNCTION (t= terms )? ) | DASHMATCH | ^( PARENBLOCK ( any )* ) | ^( BRACEBLOCK ( any )* ) );";
        }
    }
 

    public static final BitSet FOLLOW_INLINESTYLE_in_inlinestyle59 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_declarations_in_inlinestyle63 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INLINESTYLE_in_inlinestyle78 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_inlineset_in_inlinestyle88 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_STYLESHEET_in_stylesheet125 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_stylesheet134 = new BitSet(new long[]{0x0000059C30800408L});
    public static final BitSet FOLLOW_ruleset_in_statement183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atstatement_in_statement193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_STATEMENT_in_statement200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARSET_in_atstatement233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_IMPORT_in_atstatement239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_atstatement247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_END_in_atstatement258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PAGE_in_atstatement267 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_atstatement278 = new BitSet(new long[]{0x0000000000082000L});
    public static final BitSet FOLLOW_PSEUDO_in_atstatement307 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_atstatement311 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_declarations_in_atstatement341 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_SET_in_atstatement350 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_margin_in_atstatement355 = new BitSet(new long[]{0x0000100000000008L});
    public static final BitSet FOLLOW_VIEWPORT_in_atstatement379 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_declarations_in_atstatement383 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FONTFACE_in_atstatement397 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_declarations_in_atstatement401 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MEDIA_in_atstatement414 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_media_in_atstatement419 = new BitSet(new long[]{0x0000000010000408L});
    public static final BitSet FOLLOW_ruleset_in_atstatement432 = new BitSet(new long[]{0x0000000010000408L});
    public static final BitSet FOLLOW_INVALID_STATEMENT_in_atstatement443 = new BitSet(new long[]{0x0000000010000408L});
    public static final BitSet FOLLOW_MARGIN_AREA_in_margin498 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_declarations_in_margin504 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IDENT_in_media538 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_RULE_in_inlineset573 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_pseudo_in_inlineset578 = new BitSet(new long[]{0x0000000000082000L});
    public static final BitSet FOLLOW_declarations_in_inlineset586 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RULE_in_ruleset639 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_combined_selector_in_ruleset653 = new BitSet(new long[]{0x0000000002082800L});
    public static final BitSet FOLLOW_declarations_in_ruleset674 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SET_in_declarations715 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_declaration_in_declarations720 = new BitSet(new long[]{0x0000000008100008L});
    public static final BitSet FOLLOW_DECLARATION_in_declaration764 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_important_in_declaration773 = new BitSet(new long[]{0x0002080040000000L});
    public static final BitSet FOLLOW_INVALID_DIRECTIVE_in_declaration786 = new BitSet(new long[]{0x0002080040000000L});
    public static final BitSet FOLLOW_property_in_declaration798 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_terms_in_declaration809 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INVALID_DECLARATION_in_declaration829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORTANT_in_important846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_property886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_property894 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_IDENT_in_property900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_terms945 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_term_in_terms947 = new BitSet(new long[]{0xFFEEAA0001000388L,0x000000000000403FL});
    public static final BitSet FOLLOW_valuepart_in_term975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CURLYBLOCK_in_term992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATKEYWORD_in_term1002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1029 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_IDENT_in_valuepart1037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_valuepart1049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1059 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_NUMBER_in_valuepart1067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1081 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_PERCENTAGE_in_valuepart1089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1101 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_DIMENSION_in_valuepart1109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_valuepart1127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_URI_in_valuepart1146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_valuepart1164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_valuepart1183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCLUDES_in_valuepart1194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_valuepart1205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_valuepart1219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_valuepart1237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_valuepart1249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_valuepart1264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENT_in_valuepart1275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_valuepart1287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_valuepart1300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_valuepart1312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASTERISK_in_valuepart1323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPRESSION_in_valuepart1334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1345 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_FUNCTION_in_valuepart1354 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_terms_in_valuepart1358 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DASHMATCH_in_valuepart1370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENBLOCK_in_valuepart1381 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_valuepart1383 = new BitSet(new long[]{0x3FE9A80001000308L,0x0000000000004026L});
    public static final BitSet FOLLOW_BRACEBLOCK_in_valuepart1396 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_valuepart1398 = new BitSet(new long[]{0x3FE9A80001000308L,0x0000000000004026L});
    public static final BitSet FOLLOW_selector_in_combined_selector1446 = new BitSet(new long[]{0x000000000003C002L});
    public static final BitSet FOLLOW_combinator_in_combined_selector1455 = new BitSet(new long[]{0x0000000002082800L});
    public static final BitSet FOLLOW_selector_in_combined_selector1459 = new BitSet(new long[]{0x000000000003C002L});
    public static final BitSet FOLLOW_CHILD_in_combinator1489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADJACENT_in_combinator1496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRECEDING_in_combinator1504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DESCENDANT_in_combinator1511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELECTOR_in_selector1547 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ELEMENT_in_selector1559 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_selector1575 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_selpart_in_selector1622 = new BitSet(new long[]{0x04200000040C2008L});
    public static final BitSet FOLLOW_SELECTOR_in_selector1641 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_selpart_in_selector1653 = new BitSet(new long[]{0x04200000040C2008L});
    public static final BitSet FOLLOW_INVALID_SELECTOR_in_selector1671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_selpart1705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_selpart1717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_selpart1725 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_attribute_in_selpart1729 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_pseudo_in_selpart1743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_SELPART_in_selpart1750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_attribute1784 = new BitSet(new long[]{0x1000000000000002L,0x0000000000001C22L});
    public static final BitSet FOLLOW_EQUALS_in_attribute1793 = new BitSet(new long[]{0x0000080001000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_INCLUDES_in_attribute1804 = new BitSet(new long[]{0x0000080001000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_DASHMATCH_in_attribute1815 = new BitSet(new long[]{0x0000080001000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_CONTAINS_in_attribute1826 = new BitSet(new long[]{0x0000080001000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_STARTSWITH_in_attribute1837 = new BitSet(new long[]{0x0000080001000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_ENDSWITH_in_attribute1848 = new BitSet(new long[]{0x0000080001000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_IDENT_in_attribute1866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_attribute1878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PSEUDO_in_pseudo1911 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_pseudo1915 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PSEUDO_in_pseudo1926 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_FUNCTION_in_pseudo1930 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_IDENT_in_pseudo1934 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PSEUDO_in_pseudo1945 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_FUNCTION_in_pseudo1949 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_NUMBER_in_pseudo1953 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PSEUDO_in_pseudo1965 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_FUNCTION_in_pseudo1969 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_INDEX_in_pseudo1973 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STRING_in_string1997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_STRING_in_string2004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_any2020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_any2026 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_any2032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENTAGE_in_any2038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_any2044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_any2050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_URI_in_any2056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_any2062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_any2068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCLUDES_in_any2074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_any2080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_any2086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_any2092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_any2098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_any2104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_any2110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_any2117 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_any2119 = new BitSet(new long[]{0x3FE9A80001000308L,0x0000000000004026L});
    public static final BitSet FOLLOW_DASHMATCH_in_any2128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENBLOCK_in_any2135 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_any2137 = new BitSet(new long[]{0x3FE9A80001000308L,0x0000000000004026L});
    public static final BitSet FOLLOW_BRACEBLOCK_in_any2146 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_in_any2148 = new BitSet(new long[]{0x3FE9A80001000308L,0x0000000000004026L});

}