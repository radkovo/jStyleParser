// $ANTLR 3.1 CSS.g 2008-09-14 19:23:53
 
package cz.vutbr.web.csskit.antlr;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.RewriteEarlyExitException;
import org.antlr.runtime.tree.RewriteRuleSubtreeStream;
import org.antlr.runtime.tree.RewriteRuleTokenStream;
import org.antlr.runtime.tree.TreeAdaptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.SupportedCSS;

@SuppressWarnings("unused")
public class CSSParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "STYLESHEET", "ATBLOCK", "CURLYBLOCK", "PARENBLOCK", "BRACEBLOCK", "RULE", "SELECTOR", "ELEMENT", "PSEUDO", "ADJACENT", "CHILD", "DESCENDANT", "ATTRIBUTE", "DECLARATION", "VALUE", "IMPORTANT", "IMPORT_END", "INVALID_STRING", "INVALID_SELECTOR", "INVALID_SELPART", "INVALID_DECLARATION", "INVALID_STATEMENT", "INVALID_IMPORT", "CDO", "CDC", "S", "CHARSET", "IMPORT", "PAGE", "COLON", "IDENT", "LCURLY", "SEMICOLON", "RCURLY", "MEDIA", "ATKEYWORD", "COMMA", "EXCLAMATION", "CLASSKEYWORD", "MINUS", "NUMBER", "PERCENTAGE", "DIMENSION", "URI", "HASH", "UNIRANGE", "INCLUDES", "GREATER", "EQUALS", "SLASH", "PLUS", "ASTERISK", "FUNCTION", "RPAREN", "DASHMATCH", "LPAREN", "LBRACE", "RBRACE", "STRING", "IDENT_MACR", "STRING_MACR", "NAME_MACR", "NUMBER_MACR", "W_MACR", "URI_MACR", "APOS", "QUOT", "W_CHAR", "COMMENT", "SL_COMMENT", "INVALID_TOKEN", "NAME_START", "NAME_CHAR", "NON_ASCII", "ESCAPE_CHAR", "STRING_CHAR", "URI_CHAR", "NL_CHAR", "'important'"
    };
    public static final int COMMA=40;
    public static final int ELEMENT=11;
    public static final int INVALID_IMPORT=26;
    public static final int CHARSET=30;
    public static final int MINUS=43;
    public static final int BRACEBLOCK=8;
    public static final int HASH=48;
    public static final int PARENBLOCK=7;
    public static final int DASHMATCH=58;
    public static final int NUMBER=44;
    public static final int SELECTOR=10;
    public static final int IMPORT_END=20;
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
    public static final int LPAREN=59;
    public static final int GREATER=51;
    public static final int W_CHAR=71;
    public static final int NON_ASCII=77;
    public static final int PLUS=54;
    public static final int SL_COMMENT=73;
    public static final int APOS=69;
    public static final int ATKEYWORD=39;
    public static final int STRING_CHAR=79;
    public static final int URI_MACR=68;
    public static final int IDENT=34;
    public static final int SLASH=53;
    public static final int UNIRANGE=49;
    public static final int IMPORTANT=19;
    public static final int STRING=62;
    public static final int EXCLAMATION=41;
    public static final int NL_CHAR=81;
    public static final int INVALID_STRING=21;
    public static final int COMMENT=72;
    public static final int CDC=28;
    public static final int ADJACENT=13;
    public static final int NUMBER_MACR=66;
    public static final int INVALID_DECLARATION=24;
    public static final int EQUALS=52;
    public static final int CURLYBLOCK=6;
    public static final int DIMENSION=46;
    public static final int INVALID_STATEMENT=25;
    public static final int STRING_MACR=64;
    public static final int SEMICOLON=36;
    public static final int EOF=-1;
    public static final int ASTERISK=55;
    public static final int T__82=82;
    public static final int COLON=33;
    public static final int CDO=27;
    public static final int ATTRIBUTE=16;
    public static final int CHILD=14;
    public static final int PERCENTAGE=45;
    public static final int INVALID_SELPART=23;
    public static final int QUOT=70;
    public static final int ATBLOCK=5;

    // delegates
    // delegators


        public CSSParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public CSSParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return CSSParser.tokenNames; }
    public String getGrammarFileName() { return "CSS.g"; }


        private static Logger log = LoggerFactory.getLogger(CSSParser.class);
        
        private static SupportedCSS css = CSSFactory.getSupportedCSS();
        
        private StyleSheet stylesheet;
        
        /**
         * This function must be called to initialize parser's state.
         * Because we can't change directly generated constructors.
         * @param stylesheet CSS StyleSheet instance  
         */
        public CSSParser init(StyleSheet stylesheet) {
        	this.stylesheet = stylesheet;
        	return this;
        }
        
        @Override
        public void emitErrorMessage(String msg) {
        	log.info("ANTLR: {}", msg);
    	}    

    	private Object invalidReplacement(int ttype, String ttext) {
    		
    		Object root = (Object) adaptor.nil();
    		Object node = (Object) adaptor.create(ttype, ttext);
    		
    		adaptor.addChild(root, node);	
    		
    		if(log.isDebugEnabled()) {
    			log.debug("Invalid fallback with: {}", TreeUtil.toStringTree((CommonTree) root));
    		}
    		
    		return root;	
    	}

    	/**
    	 * Recovers and logs error, prepares tree part replacement
    	 */ 
    	private Object invalidFallback(int ttype, String ttext, RecognitionException re) {
    	    reportError(re);
    		recover(input, re);
    		return invalidReplacement(ttype, ttext);
    	}
    	
    	/**
    	 * Recovers and logs error, using custom follow set,
    	 * prepares tree part replacement
    	 */ 
    	private Object invalidFallbackGreedy(int ttype, String ttext, BitSet follow, RecognitionException re) {
    		reportError(re);
    		if ( state.lastErrorIndex==input.index() ) {
    			// uh oh, another error at same token index; must be a case
    	 		// where LT(1) is in the recovery token set so nothing is
                // consumed; consume a single token so at least to prevent
                // an infinite loop; this is a failsafe.
                input.consume();
            }
            state.lastErrorIndex = input.index();
            beginResync();
    		consumeUntilGreedy(input, follow);
            endResync();
    		return invalidReplacement(ttype, ttext);
    		
        }
    	
    	/**
    	 * Consumes token until lexer state is balanced and
    	 * token from follow is matched. Matched token is also consumed
    	 */ 
    	private void consumeUntilGreedy(TokenStream input, BitSet follow) {
    		CSSToken t = null;
    		do{
    		  t= (CSSToken) input.LT(1);
    		  log.trace("Skipped greedy: {}", t);
    		  // consume token even if it will match
    		  input.consume();
    		}while(!(t.getLexerState().isBalanced() && follow.member(t.getType())));
    		
    	} 



    public static class stylesheet_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "stylesheet"
    // CSS.g:490:1: stylesheet : ( CDO | CDC | S | statement )* -> ^( STYLESHEET ( statement )* ) ;
    public final CSSParser.stylesheet_return stylesheet() throws RecognitionException {
        CSSParser.stylesheet_return retval = new CSSParser.stylesheet_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CDO1=null;
        Token CDC2=null;
        Token S3=null;
        CSSParser.statement_return statement4 = null;


        Object CDO1_tree=null;
        Object CDC2_tree=null;
        Object S3_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_CDO=new RewriteRuleTokenStream(adaptor,"token CDO");
        RewriteRuleTokenStream stream_CDC=new RewriteRuleTokenStream(adaptor,"token CDC");
        RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
        try {
            // CSS.g:491:2: ( ( CDO | CDC | S | statement )* -> ^( STYLESHEET ( statement )* ) )
            // CSS.g:491:4: ( CDO | CDC | S | statement )*
            {
            // CSS.g:491:4: ( CDO | CDC | S | statement )*
            loop1:
            do {
                int alt1=5;
                alt1 = dfa1.predict(input);
                switch (alt1) {
            	case 1 :
            	    // CSS.g:491:6: CDO
            	    {
            	    CDO1=(Token)match(input,CDO,FOLLOW_CDO_in_stylesheet179);  
            	    stream_CDO.add(CDO1);


            	    }
            	    break;
            	case 2 :
            	    // CSS.g:491:12: CDC
            	    {
            	    CDC2=(Token)match(input,CDC,FOLLOW_CDC_in_stylesheet183);  
            	    stream_CDC.add(CDC2);


            	    }
            	    break;
            	case 3 :
            	    // CSS.g:491:18: S
            	    {
            	    S3=(Token)match(input,S,FOLLOW_S_in_stylesheet187);  
            	    stream_S.add(S3);


            	    }
            	    break;
            	case 4 :
            	    // CSS.g:491:22: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_stylesheet191);
            	    statement4=statement();

            	    state._fsp--;

            	    stream_statement.add(statement4.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);



            // AST REWRITE
            // elements: statement
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 492:3: -> ^( STYLESHEET ( statement )* )
            {
                // CSS.g:492:6: ^( STYLESHEET ( statement )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(STYLESHEET, "STYLESHEET"), root_1);

                // CSS.g:492:19: ( statement )*
                while ( stream_statement.hasNext() ) {
                    adaptor.addChild(root_1, stream_statement.nextTree());

                }
                stream_statement.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "stylesheet"

    public static class statement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "statement"
    // CSS.g:495:1: statement : ( ruleset | atstatement );
    public final CSSParser.statement_return statement() throws RecognitionException {
        CSSParser.statement_return retval = new CSSParser.statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CSSParser.ruleset_return ruleset5 = null;

        CSSParser.atstatement_return atstatement6 = null;



        try {
            // CSS.g:496:2: ( ruleset | atstatement )
            int alt2=2;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // CSS.g:496:4: ruleset
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ruleset_in_statement221);
                    ruleset5=ruleset();

                    state._fsp--;

                    adaptor.addChild(root_0, ruleset5.getTree());

                    }
                    break;
                case 2 :
                    // CSS.g:496:14: atstatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_atstatement_in_statement225);
                    atstatement6=atstatement();

                    state._fsp--;

                    adaptor.addChild(root_0, atstatement6.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "statement"

    public static class atstatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "atstatement"
    // CSS.g:499:1: atstatement : ( CHARSET | IMPORT | INVALID_IMPORT | IMPORT_END | PAGE ( S )* ( COLON IDENT ( S )* )? LCURLY ( S )* ( declaration )? ( SEMICOLON ( S )* ( declaration )? )* RCURLY -> ^( PAGE ( IDENT )? ( declaration )* ) | MEDIA ( S )* ( media )? LCURLY ( S )* ( ruleset ( S )* )* RCURLY -> ^( MEDIA ( media )? ( ruleset )* ) | ATKEYWORD ( S )* LCURLY ( any )* RCURLY -> INVALID_STATEMENT );
    public final CSSParser.atstatement_return atstatement() throws RecognitionException {
        CSSParser.atstatement_return retval = new CSSParser.atstatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CHARSET7=null;
        Token IMPORT8=null;
        Token INVALID_IMPORT9=null;
        Token IMPORT_END10=null;
        Token PAGE11=null;
        Token S12=null;
        Token COLON13=null;
        Token IDENT14=null;
        Token S15=null;
        Token LCURLY16=null;
        Token S17=null;
        Token SEMICOLON19=null;
        Token S20=null;
        Token RCURLY22=null;
        Token MEDIA23=null;
        Token S24=null;
        Token LCURLY26=null;
        Token S27=null;
        Token S29=null;
        Token RCURLY30=null;
        Token ATKEYWORD31=null;
        Token S32=null;
        Token LCURLY33=null;
        Token RCURLY35=null;
        CSSParser.declaration_return declaration18 = null;

        CSSParser.declaration_return declaration21 = null;

        CSSParser.media_return media25 = null;

        CSSParser.ruleset_return ruleset28 = null;

        CSSParser.any_return any34 = null;


        Object CHARSET7_tree=null;
        Object IMPORT8_tree=null;
        Object INVALID_IMPORT9_tree=null;
        Object IMPORT_END10_tree=null;
        Object PAGE11_tree=null;
        Object S12_tree=null;
        Object COLON13_tree=null;
        Object IDENT14_tree=null;
        Object S15_tree=null;
        Object LCURLY16_tree=null;
        Object S17_tree=null;
        Object SEMICOLON19_tree=null;
        Object S20_tree=null;
        Object RCURLY22_tree=null;
        Object MEDIA23_tree=null;
        Object S24_tree=null;
        Object LCURLY26_tree=null;
        Object S27_tree=null;
        Object S29_tree=null;
        Object RCURLY30_tree=null;
        Object ATKEYWORD31_tree=null;
        Object S32_tree=null;
        Object LCURLY33_tree=null;
        Object RCURLY35_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_ATKEYWORD=new RewriteRuleTokenStream(adaptor,"token ATKEYWORD");
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_MEDIA=new RewriteRuleTokenStream(adaptor,"token MEDIA");
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleTokenStream stream_PAGE=new RewriteRuleTokenStream(adaptor,"token PAGE");
        RewriteRuleSubtreeStream stream_ruleset=new RewriteRuleSubtreeStream(adaptor,"rule ruleset");
        RewriteRuleSubtreeStream stream_media=new RewriteRuleSubtreeStream(adaptor,"rule media");
        RewriteRuleSubtreeStream stream_declaration=new RewriteRuleSubtreeStream(adaptor,"rule declaration");
        RewriteRuleSubtreeStream stream_any=new RewriteRuleSubtreeStream(adaptor,"rule any");
        try {
            // CSS.g:500:2: ( CHARSET | IMPORT | INVALID_IMPORT | IMPORT_END | PAGE ( S )* ( COLON IDENT ( S )* )? LCURLY ( S )* ( declaration )? ( SEMICOLON ( S )* ( declaration )? )* RCURLY -> ^( PAGE ( IDENT )? ( declaration )* ) | MEDIA ( S )* ( media )? LCURLY ( S )* ( ruleset ( S )* )* RCURLY -> ^( MEDIA ( media )? ( ruleset )* ) | ATKEYWORD ( S )* LCURLY ( any )* RCURLY -> INVALID_STATEMENT )
            int alt18=7;
            switch ( input.LA(1) ) {
            case CHARSET:
                {
                alt18=1;
                }
                break;
            case IMPORT:
                {
                alt18=2;
                }
                break;
            case INVALID_IMPORT:
                {
                alt18=3;
                }
                break;
            case IMPORT_END:
                {
                alt18=4;
                }
                break;
            case PAGE:
                {
                alt18=5;
                }
                break;
            case MEDIA:
                {
                alt18=6;
                }
                break;
            case ATKEYWORD:
                {
                alt18=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // CSS.g:500:4: CHARSET
                    {
                    root_0 = (Object)adaptor.nil();

                    CHARSET7=(Token)match(input,CHARSET,FOLLOW_CHARSET_in_atstatement236); 
                    CHARSET7_tree = (Object)adaptor.create(CHARSET7);
                    adaptor.addChild(root_0, CHARSET7_tree);


                    }
                    break;
                case 2 :
                    // CSS.g:501:4: IMPORT
                    {
                    root_0 = (Object)adaptor.nil();

                    IMPORT8=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_atstatement241); 
                    IMPORT8_tree = (Object)adaptor.create(IMPORT8);
                    adaptor.addChild(root_0, IMPORT8_tree);


                    }
                    break;
                case 3 :
                    // CSS.g:502:4: INVALID_IMPORT
                    {
                    root_0 = (Object)adaptor.nil();

                    INVALID_IMPORT9=(Token)match(input,INVALID_IMPORT,FOLLOW_INVALID_IMPORT_in_atstatement246); 
                    INVALID_IMPORT9_tree = (Object)adaptor.create(INVALID_IMPORT9);
                    adaptor.addChild(root_0, INVALID_IMPORT9_tree);


                    }
                    break;
                case 4 :
                    // CSS.g:503:4: IMPORT_END
                    {
                    root_0 = (Object)adaptor.nil();

                    IMPORT_END10=(Token)match(input,IMPORT_END,FOLLOW_IMPORT_END_in_atstatement251); 
                    IMPORT_END10_tree = (Object)adaptor.create(IMPORT_END10);
                    adaptor.addChild(root_0, IMPORT_END10_tree);


                    }
                    break;
                case 5 :
                    // CSS.g:504:4: PAGE ( S )* ( COLON IDENT ( S )* )? LCURLY ( S )* ( declaration )? ( SEMICOLON ( S )* ( declaration )? )* RCURLY
                    {
                    PAGE11=(Token)match(input,PAGE,FOLLOW_PAGE_in_atstatement256);  
                    stream_PAGE.add(PAGE11);

                    // CSS.g:504:9: ( S )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==S) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // CSS.g:504:9: S
                    	    {
                    	    S12=(Token)match(input,S,FOLLOW_S_in_atstatement258);  
                    	    stream_S.add(S12);


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);

                    // CSS.g:504:12: ( COLON IDENT ( S )* )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==COLON) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // CSS.g:504:13: COLON IDENT ( S )*
                            {
                            COLON13=(Token)match(input,COLON,FOLLOW_COLON_in_atstatement262);  
                            stream_COLON.add(COLON13);

                            IDENT14=(Token)match(input,IDENT,FOLLOW_IDENT_in_atstatement264);  
                            stream_IDENT.add(IDENT14);

                            // CSS.g:504:25: ( S )*
                            loop4:
                            do {
                                int alt4=2;
                                int LA4_0 = input.LA(1);

                                if ( (LA4_0==S) ) {
                                    alt4=1;
                                }


                                switch (alt4) {
                            	case 1 :
                            	    // CSS.g:504:25: S
                            	    {
                            	    S15=(Token)match(input,S,FOLLOW_S_in_atstatement266);  
                            	    stream_S.add(S15);


                            	    }
                            	    break;

                            	default :
                            	    break loop4;
                                }
                            } while (true);


                            }
                            break;

                    }

                    LCURLY16=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atstatement274);  
                    stream_LCURLY.add(LCURLY16);

                    // CSS.g:505:10: ( S )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==S) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // CSS.g:505:10: S
                    	    {
                    	    S17=(Token)match(input,S,FOLLOW_S_in_atstatement276);  
                    	    stream_S.add(S17);


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    // CSS.g:505:13: ( declaration )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==IDENT) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // CSS.g:505:13: declaration
                            {
                            pushFollow(FOLLOW_declaration_in_atstatement279);
                            declaration18=declaration();

                            state._fsp--;

                            stream_declaration.add(declaration18.getTree());

                            }
                            break;

                    }

                    // CSS.g:505:26: ( SEMICOLON ( S )* ( declaration )? )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==SEMICOLON) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // CSS.g:505:27: SEMICOLON ( S )* ( declaration )?
                    	    {
                    	    SEMICOLON19=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_atstatement283);  
                    	    stream_SEMICOLON.add(SEMICOLON19);

                    	    // CSS.g:505:37: ( S )*
                    	    loop8:
                    	    do {
                    	        int alt8=2;
                    	        int LA8_0 = input.LA(1);

                    	        if ( (LA8_0==S) ) {
                    	            alt8=1;
                    	        }


                    	        switch (alt8) {
                    	    	case 1 :
                    	    	    // CSS.g:505:37: S
                    	    	    {
                    	    	    S20=(Token)match(input,S,FOLLOW_S_in_atstatement285);  
                    	    	    stream_S.add(S20);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop8;
                    	        }
                    	    } while (true);

                    	    // CSS.g:505:40: ( declaration )?
                    	    int alt9=2;
                    	    int LA9_0 = input.LA(1);

                    	    if ( (LA9_0==IDENT) ) {
                    	        alt9=1;
                    	    }
                    	    switch (alt9) {
                    	        case 1 :
                    	            // CSS.g:505:40: declaration
                    	            {
                    	            pushFollow(FOLLOW_declaration_in_atstatement288);
                    	            declaration21=declaration();

                    	            state._fsp--;

                    	            stream_declaration.add(declaration21.getTree());

                    	            }
                    	            break;

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    RCURLY22=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atstatement297);  
                    stream_RCURLY.add(RCURLY22);



                    // AST REWRITE
                    // elements: declaration, PAGE, IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 506:10: -> ^( PAGE ( IDENT )? ( declaration )* )
                    {
                        // CSS.g:506:13: ^( PAGE ( IDENT )? ( declaration )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_PAGE.nextNode(), root_1);

                        // CSS.g:506:20: ( IDENT )?
                        if ( stream_IDENT.hasNext() ) {
                            adaptor.addChild(root_1, stream_IDENT.nextNode());

                        }
                        stream_IDENT.reset();
                        // CSS.g:506:27: ( declaration )*
                        while ( stream_declaration.hasNext() ) {
                            adaptor.addChild(root_1, stream_declaration.nextTree());

                        }
                        stream_declaration.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // CSS.g:507:4: MEDIA ( S )* ( media )? LCURLY ( S )* ( ruleset ( S )* )* RCURLY
                    {
                    MEDIA23=(Token)match(input,MEDIA,FOLLOW_MEDIA_in_atstatement314);  
                    stream_MEDIA.add(MEDIA23);

                    // CSS.g:507:10: ( S )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==S) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // CSS.g:507:10: S
                    	    {
                    	    S24=(Token)match(input,S,FOLLOW_S_in_atstatement316);  
                    	    stream_S.add(S24);


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);

                    // CSS.g:507:13: ( media )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==IDENT) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // CSS.g:507:13: media
                            {
                            pushFollow(FOLLOW_media_in_atstatement319);
                            media25=media();

                            state._fsp--;

                            stream_media.add(media25.getTree());

                            }
                            break;

                    }

                    LCURLY26=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atstatement325);  
                    stream_LCURLY.add(LCURLY26);

                    // CSS.g:508:10: ( S )*
                    loop13:
                    do {
                        int alt13=2;
                        alt13 = dfa13.predict(input);
                        switch (alt13) {
                    	case 1 :
                    	    // CSS.g:508:10: S
                    	    {
                    	    S27=(Token)match(input,S,FOLLOW_S_in_atstatement327);  
                    	    stream_S.add(S27);


                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);

                    // CSS.g:508:13: ( ruleset ( S )* )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==INVALID_SELPART||(LA15_0>=COLON && LA15_0<=IDENT)||LA15_0==CLASSKEYWORD||LA15_0==HASH||LA15_0==ASTERISK||LA15_0==LBRACE) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // CSS.g:508:14: ruleset ( S )*
                    	    {
                    	    pushFollow(FOLLOW_ruleset_in_atstatement331);
                    	    ruleset28=ruleset();

                    	    state._fsp--;

                    	    stream_ruleset.add(ruleset28.getTree());
                    	    // CSS.g:508:22: ( S )*
                    	    loop14:
                    	    do {
                    	        int alt14=2;
                    	        alt14 = dfa14.predict(input);
                    	        switch (alt14) {
                    	    	case 1 :
                    	    	    // CSS.g:508:22: S
                    	    	    {
                    	    	    S29=(Token)match(input,S,FOLLOW_S_in_atstatement333);  
                    	    	    stream_S.add(S29);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop14;
                    	        }
                    	    } while (true);


                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);

                    RCURLY30=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atstatement338);  
                    stream_RCURLY.add(RCURLY30);



                    // AST REWRITE
                    // elements: media, ruleset, MEDIA
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 508:34: -> ^( MEDIA ( media )? ( ruleset )* )
                    {
                        // CSS.g:508:37: ^( MEDIA ( media )? ( ruleset )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_MEDIA.nextNode(), root_1);

                        // CSS.g:508:45: ( media )?
                        if ( stream_media.hasNext() ) {
                            adaptor.addChild(root_1, stream_media.nextTree());

                        }
                        stream_media.reset();
                        // CSS.g:508:52: ( ruleset )*
                        while ( stream_ruleset.hasNext() ) {
                            adaptor.addChild(root_1, stream_ruleset.nextTree());

                        }
                        stream_ruleset.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // CSS.g:509:4: ATKEYWORD ( S )* LCURLY ( any )* RCURLY
                    {
                    ATKEYWORD31=(Token)match(input,ATKEYWORD,FOLLOW_ATKEYWORD_in_atstatement356);  
                    stream_ATKEYWORD.add(ATKEYWORD31);

                    // CSS.g:509:14: ( S )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==S) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // CSS.g:509:14: S
                    	    {
                    	    S32=(Token)match(input,S,FOLLOW_S_in_atstatement358);  
                    	    stream_S.add(S32);


                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);

                    LCURLY33=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atstatement361);  
                    stream_LCURLY.add(LCURLY33);

                    // CSS.g:509:24: ( any )*
                    loop17:
                    do {
                        int alt17=2;
                        alt17 = dfa17.predict(input);
                        switch (alt17) {
                    	case 1 :
                    	    // CSS.g:509:24: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_atstatement363);
                    	    any34=any();

                    	    state._fsp--;

                    	    stream_any.add(any34.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);

                    RCURLY35=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atstatement366);  
                    stream_RCURLY.add(RCURLY35);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 509:36: -> INVALID_STATEMENT
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(INVALID_STATEMENT, "INVALID_STATEMENT"));

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {

                  	final BitSet follow = BitSet.of(CSSLexer.RCURLY, CSSLexer.SEMICOLON);								
            	    retval.tree = invalidFallbackGreedy(CSSLexer.INVALID_STATEMENT, 
            	  		"INVALID_STATEMENT", follow, re);							
            	
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "atstatement"

    public static class media_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "media"
    // CSS.g:517:1: media : IDENT ( S )* ( COMMA ( S )* IDENT ( S )* )* -> ( IDENT )+ ;
    public final CSSParser.media_return media() throws RecognitionException {
        CSSParser.media_return retval = new CSSParser.media_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT36=null;
        Token S37=null;
        Token COMMA38=null;
        Token S39=null;
        Token IDENT40=null;
        Token S41=null;

        Object IDENT36_tree=null;
        Object S37_tree=null;
        Object COMMA38_tree=null;
        Object S39_tree=null;
        Object IDENT40_tree=null;
        Object S41_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");

        try {
            // CSS.g:518:2: ( IDENT ( S )* ( COMMA ( S )* IDENT ( S )* )* -> ( IDENT )+ )
            // CSS.g:518:4: IDENT ( S )* ( COMMA ( S )* IDENT ( S )* )*
            {
            IDENT36=(Token)match(input,IDENT,FOLLOW_IDENT_in_media389);  
            stream_IDENT.add(IDENT36);

            // CSS.g:518:10: ( S )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==S) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // CSS.g:518:10: S
            	    {
            	    S37=(Token)match(input,S,FOLLOW_S_in_media391);  
            	    stream_S.add(S37);


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            // CSS.g:518:13: ( COMMA ( S )* IDENT ( S )* )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==COMMA) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // CSS.g:518:14: COMMA ( S )* IDENT ( S )*
            	    {
            	    COMMA38=(Token)match(input,COMMA,FOLLOW_COMMA_in_media395);  
            	    stream_COMMA.add(COMMA38);

            	    // CSS.g:518:20: ( S )*
            	    loop20:
            	    do {
            	        int alt20=2;
            	        int LA20_0 = input.LA(1);

            	        if ( (LA20_0==S) ) {
            	            alt20=1;
            	        }


            	        switch (alt20) {
            	    	case 1 :
            	    	    // CSS.g:518:20: S
            	    	    {
            	    	    S39=(Token)match(input,S,FOLLOW_S_in_media397);  
            	    	    stream_S.add(S39);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop20;
            	        }
            	    } while (true);

            	    IDENT40=(Token)match(input,IDENT,FOLLOW_IDENT_in_media400);  
            	    stream_IDENT.add(IDENT40);

            	    // CSS.g:518:29: ( S )*
            	    loop21:
            	    do {
            	        int alt21=2;
            	        int LA21_0 = input.LA(1);

            	        if ( (LA21_0==S) ) {
            	            alt21=1;
            	        }


            	        switch (alt21) {
            	    	case 1 :
            	    	    // CSS.g:518:29: S
            	    	    {
            	    	    S41=(Token)match(input,S,FOLLOW_S_in_media402);  
            	    	    stream_S.add(S41);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop21;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);



            // AST REWRITE
            // elements: IDENT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 519:3: -> ( IDENT )+
            {
                if ( !(stream_IDENT.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_IDENT.hasNext() ) {
                    adaptor.addChild(root_0, stream_IDENT.nextNode());

                }
                stream_IDENT.reset();

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "media"

    public static class ruleset_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ruleset"
    // CSS.g:522:1: ruleset : combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* ( declaration )? ( SEMICOLON ( S )* ( declaration )? )* RCURLY -> ^( RULE ( combined_selector )+ ( declaration )* ) ;
    public final CSSParser.ruleset_return ruleset() throws RecognitionException {
        CSSParser.ruleset_return retval = new CSSParser.ruleset_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA43=null;
        Token S44=null;
        Token LCURLY46=null;
        Token S47=null;
        Token SEMICOLON49=null;
        Token S50=null;
        Token RCURLY52=null;
        CSSParser.combined_selector_return combined_selector42 = null;

        CSSParser.combined_selector_return combined_selector45 = null;

        CSSParser.declaration_return declaration48 = null;

        CSSParser.declaration_return declaration51 = null;


        Object COMMA43_tree=null;
        Object S44_tree=null;
        Object LCURLY46_tree=null;
        Object S47_tree=null;
        Object SEMICOLON49_tree=null;
        Object S50_tree=null;
        Object RCURLY52_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleSubtreeStream stream_combined_selector=new RewriteRuleSubtreeStream(adaptor,"rule combined_selector");
        RewriteRuleSubtreeStream stream_declaration=new RewriteRuleSubtreeStream(adaptor,"rule declaration");
        try {
            // CSS.g:525:2: ( combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* ( declaration )? ( SEMICOLON ( S )* ( declaration )? )* RCURLY -> ^( RULE ( combined_selector )+ ( declaration )* ) )
            // CSS.g:525:4: combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* ( declaration )? ( SEMICOLON ( S )* ( declaration )? )* RCURLY
            {
            pushFollow(FOLLOW_combined_selector_in_ruleset432);
            combined_selector42=combined_selector();

            state._fsp--;

            stream_combined_selector.add(combined_selector42.getTree());
            // CSS.g:525:22: ( COMMA ( S )* combined_selector )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==COMMA) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // CSS.g:525:23: COMMA ( S )* combined_selector
            	    {
            	    COMMA43=(Token)match(input,COMMA,FOLLOW_COMMA_in_ruleset435);  
            	    stream_COMMA.add(COMMA43);

            	    // CSS.g:525:29: ( S )*
            	    loop23:
            	    do {
            	        int alt23=2;
            	        int LA23_0 = input.LA(1);

            	        if ( (LA23_0==S) ) {
            	            alt23=1;
            	        }


            	        switch (alt23) {
            	    	case 1 :
            	    	    // CSS.g:525:29: S
            	    	    {
            	    	    S44=(Token)match(input,S,FOLLOW_S_in_ruleset437);  
            	    	    stream_S.add(S44);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop23;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_combined_selector_in_ruleset440);
            	    combined_selector45=combined_selector();

            	    state._fsp--;

            	    stream_combined_selector.add(combined_selector45.getTree());

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            LCURLY46=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_ruleset448);  
            stream_LCURLY.add(LCURLY46);

            // CSS.g:526:11: ( S )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==S) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // CSS.g:526:11: S
            	    {
            	    S47=(Token)match(input,S,FOLLOW_S_in_ruleset450);  
            	    stream_S.add(S47);


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

            // CSS.g:527:5: ( declaration )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==IDENT) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // CSS.g:527:5: declaration
                    {
                    pushFollow(FOLLOW_declaration_in_ruleset458);
                    declaration48=declaration();

                    state._fsp--;

                    stream_declaration.add(declaration48.getTree());

                    }
                    break;

            }

            // CSS.g:527:18: ( SEMICOLON ( S )* ( declaration )? )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==SEMICOLON) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // CSS.g:527:19: SEMICOLON ( S )* ( declaration )?
            	    {
            	    SEMICOLON49=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_ruleset462);  
            	    stream_SEMICOLON.add(SEMICOLON49);

            	    // CSS.g:527:29: ( S )*
            	    loop27:
            	    do {
            	        int alt27=2;
            	        int LA27_0 = input.LA(1);

            	        if ( (LA27_0==S) ) {
            	            alt27=1;
            	        }


            	        switch (alt27) {
            	    	case 1 :
            	    	    // CSS.g:527:29: S
            	    	    {
            	    	    S50=(Token)match(input,S,FOLLOW_S_in_ruleset464);  
            	    	    stream_S.add(S50);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop27;
            	        }
            	    } while (true);

            	    // CSS.g:527:32: ( declaration )?
            	    int alt28=2;
            	    int LA28_0 = input.LA(1);

            	    if ( (LA28_0==IDENT) ) {
            	        alt28=1;
            	    }
            	    switch (alt28) {
            	        case 1 :
            	            // CSS.g:527:32: declaration
            	            {
            	            pushFollow(FOLLOW_declaration_in_ruleset467);
            	            declaration51=declaration();

            	            state._fsp--;

            	            stream_declaration.add(declaration51.getTree());

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);

            RCURLY52=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_ruleset477);  
            stream_RCURLY.add(RCURLY52);



            // AST REWRITE
            // elements: declaration, combined_selector
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 529:4: -> ^( RULE ( combined_selector )+ ( declaration )* )
            {
                // CSS.g:529:7: ^( RULE ( combined_selector )+ ( declaration )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(RULE, "RULE"), root_1);

                if ( !(stream_combined_selector.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_combined_selector.hasNext() ) {
                    adaptor.addChild(root_1, stream_combined_selector.nextTree());

                }
                stream_combined_selector.reset();
                // CSS.g:529:33: ( declaration )*
                while ( stream_declaration.hasNext() ) {
                    adaptor.addChild(root_1, stream_declaration.nextTree());

                }
                stream_declaration.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);



        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ruleset"

    public static class declaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "declaration"
    // CSS.g:532:1: declaration : property COLON ( S )* terms ( important )? -> ^( DECLARATION ( important )? property terms ) ;
    public final CSSParser.declaration_return declaration() throws RecognitionException {
        CSSParser.declaration_return retval = new CSSParser.declaration_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON54=null;
        Token S55=null;
        CSSParser.property_return property53 = null;

        CSSParser.terms_return terms56 = null;

        CSSParser.important_return important57 = null;


        Object COLON54_tree=null;
        Object S55_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleSubtreeStream stream_important=new RewriteRuleSubtreeStream(adaptor,"rule important");
        RewriteRuleSubtreeStream stream_terms=new RewriteRuleSubtreeStream(adaptor,"rule terms");
        RewriteRuleSubtreeStream stream_property=new RewriteRuleSubtreeStream(adaptor,"rule property");
        try {
            // CSS.g:533:2: ( property COLON ( S )* terms ( important )? -> ^( DECLARATION ( important )? property terms ) )
            // CSS.g:533:4: property COLON ( S )* terms ( important )?
            {
            pushFollow(FOLLOW_property_in_declaration503);
            property53=property();

            state._fsp--;

            stream_property.add(property53.getTree());
            COLON54=(Token)match(input,COLON,FOLLOW_COLON_in_declaration505);  
            stream_COLON.add(COLON54);

            // CSS.g:533:19: ( S )*
            loop30:
            do {
                int alt30=2;
                alt30 = dfa30.predict(input);
                switch (alt30) {
            	case 1 :
            	    // CSS.g:533:19: S
            	    {
            	    S55=(Token)match(input,S,FOLLOW_S_in_declaration507);  
            	    stream_S.add(S55);


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

            pushFollow(FOLLOW_terms_in_declaration510);
            terms56=terms();

            state._fsp--;

            stream_terms.add(terms56.getTree());
            // CSS.g:533:28: ( important )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==EXCLAMATION) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // CSS.g:533:28: important
                    {
                    pushFollow(FOLLOW_important_in_declaration512);
                    important57=important();

                    state._fsp--;

                    stream_important.add(important57.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: property, important, terms
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 533:39: -> ^( DECLARATION ( important )? property terms )
            {
                // CSS.g:533:42: ^( DECLARATION ( important )? property terms )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DECLARATION, "DECLARATION"), root_1);

                // CSS.g:533:56: ( important )?
                if ( stream_important.hasNext() ) {
                    adaptor.addChild(root_1, stream_important.nextTree());

                }
                stream_important.reset();
                adaptor.addChild(root_1, stream_property.nextTree());
                adaptor.addChild(root_1, stream_terms.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {

            	  retval.tree = invalidFallback(CSSLexer.INVALID_DECLARATION, "INVALID_DECLARATION", re);									
            	
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "declaration"

    public static class important_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "important"
    // CSS.g:539:1: important : EXCLAMATION ( S )* 'important' ( S )* -> IMPORTANT ;
    public final CSSParser.important_return important() throws RecognitionException {
        CSSParser.important_return retval = new CSSParser.important_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EXCLAMATION58=null;
        Token S59=null;
        Token string_literal60=null;
        Token S61=null;

        Object EXCLAMATION58_tree=null;
        Object S59_tree=null;
        Object string_literal60_tree=null;
        Object S61_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
        RewriteRuleTokenStream stream_EXCLAMATION=new RewriteRuleTokenStream(adaptor,"token EXCLAMATION");

        try {
            // CSS.g:540:5: ( EXCLAMATION ( S )* 'important' ( S )* -> IMPORTANT )
            // CSS.g:540:7: EXCLAMATION ( S )* 'important' ( S )*
            {
            EXCLAMATION58=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_important547);  
            stream_EXCLAMATION.add(EXCLAMATION58);

            // CSS.g:540:19: ( S )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==S) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // CSS.g:540:19: S
            	    {
            	    S59=(Token)match(input,S,FOLLOW_S_in_important549);  
            	    stream_S.add(S59);


            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

            string_literal60=(Token)match(input,82,FOLLOW_82_in_important552);  
            stream_82.add(string_literal60);

            // CSS.g:540:34: ( S )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==S) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // CSS.g:540:34: S
            	    {
            	    S61=(Token)match(input,S,FOLLOW_S_in_important554);  
            	    stream_S.add(S61);


            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);



            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 540:37: -> IMPORTANT
            {
                adaptor.addChild(root_0, (Object)adaptor.create(IMPORTANT, "IMPORTANT"));

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "important"

    public static class property_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "property"
    // CSS.g:543:1: property : IDENT ( S )* -> IDENT ;
    public final CSSParser.property_return property() throws RecognitionException {
        CSSParser.property_return retval = new CSSParser.property_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT62=null;
        Token S63=null;

        Object IDENT62_tree=null;
        Object S63_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");

        try {
            // CSS.g:544:2: ( IDENT ( S )* -> IDENT )
            // CSS.g:544:4: IDENT ( S )*
            {
            IDENT62=(Token)match(input,IDENT,FOLLOW_IDENT_in_property579);  
            stream_IDENT.add(IDENT62);

            // CSS.g:544:10: ( S )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==S) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // CSS.g:544:10: S
            	    {
            	    S63=(Token)match(input,S,FOLLOW_S_in_property581);  
            	    stream_S.add(S63);


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);



            // AST REWRITE
            // elements: IDENT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 544:13: -> IDENT
            {
                adaptor.addChild(root_0, stream_IDENT.nextNode());

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "property"

    public static class terms_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "terms"
    // CSS.g:547:1: terms : ( term )+ -> ^( VALUE ( term )+ ) ;
    public final CSSParser.terms_return terms() throws RecognitionException {
        CSSParser.terms_return retval = new CSSParser.terms_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CSSParser.term_return term64 = null;


        RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");
        try {
            // CSS.g:548:2: ( ( term )+ -> ^( VALUE ( term )+ ) )
            // CSS.g:548:4: ( term )+
            {
            // CSS.g:548:4: ( term )+
            int cnt35=0;
            loop35:
            do {
                int alt35=2;
                alt35 = dfa35.predict(input);
                switch (alt35) {
            	case 1 :
            	    // CSS.g:548:4: term
            	    {
            	    pushFollow(FOLLOW_term_in_terms606);
            	    term64=term();

            	    state._fsp--;

            	    stream_term.add(term64.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt35 >= 1 ) break loop35;
                        EarlyExitException eee =
                            new EarlyExitException(35, input);
                        throw eee;
                }
                cnt35++;
            } while (true);



            // AST REWRITE
            // elements: term
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 549:2: -> ^( VALUE ( term )+ )
            {
                // CSS.g:549:5: ^( VALUE ( term )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VALUE, "VALUE"), root_1);

                if ( !(stream_term.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_term.hasNext() ) {
                    adaptor.addChild(root_1, stream_term.nextTree());

                }
                stream_term.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "terms"

    public static class term_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "term"
    // CSS.g:552:1: term : ( valuepart -> valuepart | LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY -> CURLYBLOCK | ATKEYWORD ( S )* -> ATKEYWORD );
    public final CSSParser.term_return term() throws RecognitionException {
        CSSParser.term_return retval = new CSSParser.term_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LCURLY66=null;
        Token S67=null;
        Token SEMICOLON69=null;
        Token S70=null;
        Token RCURLY71=null;
        Token ATKEYWORD72=null;
        Token S73=null;
        CSSParser.valuepart_return valuepart65 = null;

        CSSParser.any_return any68 = null;


        Object LCURLY66_tree=null;
        Object S67_tree=null;
        Object SEMICOLON69_tree=null;
        Object S70_tree=null;
        Object RCURLY71_tree=null;
        Object ATKEYWORD72_tree=null;
        Object S73_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_ATKEYWORD=new RewriteRuleTokenStream(adaptor,"token ATKEYWORD");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleSubtreeStream stream_valuepart=new RewriteRuleSubtreeStream(adaptor,"rule valuepart");
        RewriteRuleSubtreeStream stream_any=new RewriteRuleSubtreeStream(adaptor,"rule any");
        try {
            // CSS.g:553:5: ( valuepart -> valuepart | LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY -> CURLYBLOCK | ATKEYWORD ( S )* -> ATKEYWORD )
            int alt40=3;
            alt40 = dfa40.predict(input);
            switch (alt40) {
                case 1 :
                    // CSS.g:553:7: valuepart
                    {
                    pushFollow(FOLLOW_valuepart_in_term632);
                    valuepart65=valuepart();

                    state._fsp--;

                    stream_valuepart.add(valuepart65.getTree());


                    // AST REWRITE
                    // elements: valuepart
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 553:17: -> valuepart
                    {
                        adaptor.addChild(root_0, stream_valuepart.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // CSS.g:554:7: LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY
                    {
                    LCURLY66=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_term644);  
                    stream_LCURLY.add(LCURLY66);

                    // CSS.g:554:14: ( S )*
                    loop36:
                    do {
                        int alt36=2;
                        alt36 = dfa36.predict(input);
                        switch (alt36) {
                    	case 1 :
                    	    // CSS.g:554:14: S
                    	    {
                    	    S67=(Token)match(input,S,FOLLOW_S_in_term646);  
                    	    stream_S.add(S67);


                    	    }
                    	    break;

                    	default :
                    	    break loop36;
                        }
                    } while (true);

                    // CSS.g:554:17: ( any | SEMICOLON ( S )* )*
                    loop38:
                    do {
                        int alt38=3;
                        alt38 = dfa38.predict(input);
                        switch (alt38) {
                    	case 1 :
                    	    // CSS.g:554:18: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_term650);
                    	    any68=any();

                    	    state._fsp--;

                    	    stream_any.add(any68.getTree());

                    	    }
                    	    break;
                    	case 2 :
                    	    // CSS.g:554:24: SEMICOLON ( S )*
                    	    {
                    	    SEMICOLON69=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_term654);  
                    	    stream_SEMICOLON.add(SEMICOLON69);

                    	    // CSS.g:554:34: ( S )*
                    	    loop37:
                    	    do {
                    	        int alt37=2;
                    	        alt37 = dfa37.predict(input);
                    	        switch (alt37) {
                    	    	case 1 :
                    	    	    // CSS.g:554:34: S
                    	    	    {
                    	    	    S70=(Token)match(input,S,FOLLOW_S_in_term656);  
                    	    	    stream_S.add(S70);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop37;
                    	        }
                    	    } while (true);


                    	    }
                    	    break;

                    	default :
                    	    break loop38;
                        }
                    } while (true);

                    RCURLY71=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_term661);  
                    stream_RCURLY.add(RCURLY71);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 554:46: -> CURLYBLOCK
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(CURLYBLOCK, "CURLYBLOCK"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // CSS.g:555:7: ATKEYWORD ( S )*
                    {
                    ATKEYWORD72=(Token)match(input,ATKEYWORD,FOLLOW_ATKEYWORD_in_term673);  
                    stream_ATKEYWORD.add(ATKEYWORD72);

                    // CSS.g:555:17: ( S )*
                    loop39:
                    do {
                        int alt39=2;
                        alt39 = dfa39.predict(input);
                        switch (alt39) {
                    	case 1 :
                    	    // CSS.g:555:17: S
                    	    {
                    	    S73=(Token)match(input,S,FOLLOW_S_in_term675);  
                    	    stream_S.add(S73);


                    	    }
                    	    break;

                    	default :
                    	    break loop39;
                        }
                    } while (true);



                    // AST REWRITE
                    // elements: ATKEYWORD
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 555:20: -> ATKEYWORD
                    {
                        adaptor.addChild(root_0, stream_ATKEYWORD.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "term"

    public static class valuepart_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "valuepart"
    // CSS.g:558:1: valuepart : ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* terms RPAREN -> ^( FUNCTION terms ) | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) ) ( S )* ;
    public final CSSParser.valuepart_return valuepart() throws RecognitionException {
        CSSParser.valuepart_return retval = new CSSParser.valuepart_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT74=null;
        Token CLASSKEYWORD75=null;
        Token MINUS76=null;
        Token NUMBER77=null;
        Token MINUS78=null;
        Token PERCENTAGE79=null;
        Token MINUS80=null;
        Token DIMENSION81=null;
        Token URI83=null;
        Token HASH84=null;
        Token UNIRANGE85=null;
        Token INCLUDES86=null;
        Token COLON87=null;
        Token COMMA88=null;
        Token GREATER89=null;
        Token EQUALS90=null;
        Token SLASH91=null;
        Token PLUS92=null;
        Token ASTERISK93=null;
        Token FUNCTION94=null;
        Token S95=null;
        Token RPAREN97=null;
        Token DASHMATCH98=null;
        Token LPAREN99=null;
        Token RPAREN101=null;
        Token LBRACE102=null;
        Token RBRACE104=null;
        Token S105=null;
        CSSParser.string_return string82 = null;

        CSSParser.terms_return terms96 = null;

        CSSParser.valuepart_return valuepart100 = null;

        CSSParser.valuepart_return valuepart103 = null;


        Object IDENT74_tree=null;
        Object CLASSKEYWORD75_tree=null;
        Object MINUS76_tree=null;
        Object NUMBER77_tree=null;
        Object MINUS78_tree=null;
        Object PERCENTAGE79_tree=null;
        Object MINUS80_tree=null;
        Object DIMENSION81_tree=null;
        Object URI83_tree=null;
        Object HASH84_tree=null;
        Object UNIRANGE85_tree=null;
        Object INCLUDES86_tree=null;
        Object COLON87_tree=null;
        Object COMMA88_tree=null;
        Object GREATER89_tree=null;
        Object EQUALS90_tree=null;
        Object SLASH91_tree=null;
        Object PLUS92_tree=null;
        Object ASTERISK93_tree=null;
        Object FUNCTION94_tree=null;
        Object S95_tree=null;
        Object RPAREN97_tree=null;
        Object DASHMATCH98_tree=null;
        Object LPAREN99_tree=null;
        Object RPAREN101_tree=null;
        Object LBRACE102_tree=null;
        Object RBRACE104_tree=null;
        Object S105_tree=null;
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_EQUALS=new RewriteRuleTokenStream(adaptor,"token EQUALS");
        RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");
        RewriteRuleTokenStream stream_DIMENSION=new RewriteRuleTokenStream(adaptor,"token DIMENSION");
        RewriteRuleTokenStream stream_FUNCTION=new RewriteRuleTokenStream(adaptor,"token FUNCTION");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_HASH=new RewriteRuleTokenStream(adaptor,"token HASH");
        RewriteRuleTokenStream stream_SLASH=new RewriteRuleTokenStream(adaptor,"token SLASH");
        RewriteRuleTokenStream stream_DASHMATCH=new RewriteRuleTokenStream(adaptor,"token DASHMATCH");
        RewriteRuleTokenStream stream_UNIRANGE=new RewriteRuleTokenStream(adaptor,"token UNIRANGE");
        RewriteRuleTokenStream stream_NUMBER=new RewriteRuleTokenStream(adaptor,"token NUMBER");
        RewriteRuleTokenStream stream_ASTERISK=new RewriteRuleTokenStream(adaptor,"token ASTERISK");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleTokenStream stream_CLASSKEYWORD=new RewriteRuleTokenStream(adaptor,"token CLASSKEYWORD");
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
        RewriteRuleTokenStream stream_INCLUDES=new RewriteRuleTokenStream(adaptor,"token INCLUDES");
        RewriteRuleTokenStream stream_PERCENTAGE=new RewriteRuleTokenStream(adaptor,"token PERCENTAGE");
        RewriteRuleTokenStream stream_URI=new RewriteRuleTokenStream(adaptor,"token URI");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleSubtreeStream stream_string=new RewriteRuleSubtreeStream(adaptor,"rule string");
        RewriteRuleSubtreeStream stream_terms=new RewriteRuleSubtreeStream(adaptor,"rule terms");
        RewriteRuleSubtreeStream stream_valuepart=new RewriteRuleSubtreeStream(adaptor,"rule valuepart");
        try {
            // CSS.g:559:5: ( ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* terms RPAREN -> ^( FUNCTION terms ) | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) ) ( S )* )
            // CSS.g:559:7: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* terms RPAREN -> ^( FUNCTION terms ) | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) ) ( S )*
            {
            // CSS.g:559:7: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* terms RPAREN -> ^( FUNCTION terms ) | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) )
            int alt47=21;
            alt47 = dfa47.predict(input);
            switch (alt47) {
                case 1 :
                    // CSS.g:559:9: IDENT
                    {
                    IDENT74=(Token)match(input,IDENT,FOLLOW_IDENT_in_valuepart700);  
                    stream_IDENT.add(IDENT74);



                    // AST REWRITE
                    // elements: IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 559:15: -> IDENT
                    {
                        adaptor.addChild(root_0, stream_IDENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // CSS.g:560:9: CLASSKEYWORD
                    {
                    CLASSKEYWORD75=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_valuepart714);  
                    stream_CLASSKEYWORD.add(CLASSKEYWORD75);



                    // AST REWRITE
                    // elements: CLASSKEYWORD
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 560:22: -> CLASSKEYWORD
                    {
                        adaptor.addChild(root_0, stream_CLASSKEYWORD.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // CSS.g:561:9: ( MINUS )? NUMBER
                    {
                    // CSS.g:561:9: ( MINUS )?
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==MINUS) ) {
                        alt41=1;
                    }
                    switch (alt41) {
                        case 1 :
                            // CSS.g:561:9: MINUS
                            {
                            MINUS76=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart728);  
                            stream_MINUS.add(MINUS76);


                            }
                            break;

                    }

                    NUMBER77=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_valuepart731);  
                    stream_NUMBER.add(NUMBER77);



                    // AST REWRITE
                    // elements: MINUS, NUMBER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 561:23: -> ( MINUS )? NUMBER
                    {
                        // CSS.g:561:26: ( MINUS )?
                        if ( stream_MINUS.hasNext() ) {
                            adaptor.addChild(root_0, stream_MINUS.nextNode());

                        }
                        stream_MINUS.reset();
                        adaptor.addChild(root_0, stream_NUMBER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // CSS.g:562:9: ( MINUS )? PERCENTAGE
                    {
                    // CSS.g:562:9: ( MINUS )?
                    int alt42=2;
                    int LA42_0 = input.LA(1);

                    if ( (LA42_0==MINUS) ) {
                        alt42=1;
                    }
                    switch (alt42) {
                        case 1 :
                            // CSS.g:562:9: MINUS
                            {
                            MINUS78=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart748);  
                            stream_MINUS.add(MINUS78);


                            }
                            break;

                    }

                    PERCENTAGE79=(Token)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_valuepart751);  
                    stream_PERCENTAGE.add(PERCENTAGE79);



                    // AST REWRITE
                    // elements: PERCENTAGE, MINUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 562:27: -> ( MINUS )? PERCENTAGE
                    {
                        // CSS.g:562:30: ( MINUS )?
                        if ( stream_MINUS.hasNext() ) {
                            adaptor.addChild(root_0, stream_MINUS.nextNode());

                        }
                        stream_MINUS.reset();
                        adaptor.addChild(root_0, stream_PERCENTAGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // CSS.g:563:9: ( MINUS )? DIMENSION
                    {
                    // CSS.g:563:9: ( MINUS )?
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( (LA43_0==MINUS) ) {
                        alt43=1;
                    }
                    switch (alt43) {
                        case 1 :
                            // CSS.g:563:9: MINUS
                            {
                            MINUS80=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart768);  
                            stream_MINUS.add(MINUS80);


                            }
                            break;

                    }

                    DIMENSION81=(Token)match(input,DIMENSION,FOLLOW_DIMENSION_in_valuepart771);  
                    stream_DIMENSION.add(DIMENSION81);



                    // AST REWRITE
                    // elements: MINUS, DIMENSION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 563:26: -> ( MINUS )? DIMENSION
                    {
                        // CSS.g:563:29: ( MINUS )?
                        if ( stream_MINUS.hasNext() ) {
                            adaptor.addChild(root_0, stream_MINUS.nextNode());

                        }
                        stream_MINUS.reset();
                        adaptor.addChild(root_0, stream_DIMENSION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // CSS.g:564:9: string
                    {
                    pushFollow(FOLLOW_string_in_valuepart788);
                    string82=string();

                    state._fsp--;

                    stream_string.add(string82.getTree());


                    // AST REWRITE
                    // elements: string
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 564:16: -> string
                    {
                        adaptor.addChild(root_0, stream_string.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // CSS.g:565:9: URI
                    {
                    URI83=(Token)match(input,URI,FOLLOW_URI_in_valuepart802);  
                    stream_URI.add(URI83);



                    // AST REWRITE
                    // elements: URI
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 565:16: -> URI
                    {
                        adaptor.addChild(root_0, stream_URI.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // CSS.g:566:9: HASH
                    {
                    HASH84=(Token)match(input,HASH,FOLLOW_HASH_in_valuepart819);  
                    stream_HASH.add(HASH84);



                    // AST REWRITE
                    // elements: HASH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 566:14: -> HASH
                    {
                        adaptor.addChild(root_0, stream_HASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // CSS.g:567:9: UNIRANGE
                    {
                    UNIRANGE85=(Token)match(input,UNIRANGE,FOLLOW_UNIRANGE_in_valuepart833);  
                    stream_UNIRANGE.add(UNIRANGE85);



                    // AST REWRITE
                    // elements: UNIRANGE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 567:18: -> UNIRANGE
                    {
                        adaptor.addChild(root_0, stream_UNIRANGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // CSS.g:568:9: INCLUDES
                    {
                    INCLUDES86=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_valuepart847);  
                    stream_INCLUDES.add(INCLUDES86);



                    // AST REWRITE
                    // elements: INCLUDES
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 568:18: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // CSS.g:569:9: COLON
                    {
                    COLON87=(Token)match(input,COLON,FOLLOW_COLON_in_valuepart861);  
                    stream_COLON.add(COLON87);



                    // AST REWRITE
                    // elements: COLON
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 569:15: -> COLON
                    {
                        adaptor.addChild(root_0, stream_COLON.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // CSS.g:570:9: COMMA
                    {
                    COMMA88=(Token)match(input,COMMA,FOLLOW_COMMA_in_valuepart875);  
                    stream_COMMA.add(COMMA88);



                    // AST REWRITE
                    // elements: COMMA
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 570:15: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // CSS.g:571:9: GREATER
                    {
                    GREATER89=(Token)match(input,GREATER,FOLLOW_GREATER_in_valuepart889);  
                    stream_GREATER.add(GREATER89);



                    // AST REWRITE
                    // elements: GREATER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 571:17: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // CSS.g:572:9: EQUALS
                    {
                    EQUALS90=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_valuepart903);  
                    stream_EQUALS.add(EQUALS90);



                    // AST REWRITE
                    // elements: EQUALS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 572:16: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // CSS.g:573:9: SLASH
                    {
                    SLASH91=(Token)match(input,SLASH,FOLLOW_SLASH_in_valuepart917);  
                    stream_SLASH.add(SLASH91);



                    // AST REWRITE
                    // elements: SLASH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 573:15: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // CSS.g:574:6: PLUS
                    {
                    PLUS92=(Token)match(input,PLUS,FOLLOW_PLUS_in_valuepart928);  
                    stream_PLUS.add(PLUS92);



                    // AST REWRITE
                    // elements: PLUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 574:11: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // CSS.g:575:6: ASTERISK
                    {
                    ASTERISK93=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_valuepart939);  
                    stream_ASTERISK.add(ASTERISK93);



                    // AST REWRITE
                    // elements: ASTERISK
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 575:15: -> ASTERISK
                    {
                        adaptor.addChild(root_0, stream_ASTERISK.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 18 :
                    // CSS.g:576:9: FUNCTION ( S )* terms RPAREN
                    {
                    FUNCTION94=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_valuepart956);  
                    stream_FUNCTION.add(FUNCTION94);

                    // CSS.g:576:18: ( S )*
                    loop44:
                    do {
                        int alt44=2;
                        alt44 = dfa44.predict(input);
                        switch (alt44) {
                    	case 1 :
                    	    // CSS.g:576:18: S
                    	    {
                    	    S95=(Token)match(input,S,FOLLOW_S_in_valuepart958);  
                    	    stream_S.add(S95);


                    	    }
                    	    break;

                    	default :
                    	    break loop44;
                        }
                    } while (true);

                    pushFollow(FOLLOW_terms_in_valuepart961);
                    terms96=terms();

                    state._fsp--;

                    stream_terms.add(terms96.getTree());
                    RPAREN97=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_valuepart963);  
                    stream_RPAREN.add(RPAREN97);



                    // AST REWRITE
                    // elements: terms, FUNCTION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 576:34: -> ^( FUNCTION terms )
                    {
                        // CSS.g:576:37: ^( FUNCTION terms )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_FUNCTION.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_terms.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 19 :
                    // CSS.g:577:9: DASHMATCH
                    {
                    DASHMATCH98=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_valuepart982);  
                    stream_DASHMATCH.add(DASHMATCH98);



                    // AST REWRITE
                    // elements: DASHMATCH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 577:19: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 20 :
                    // CSS.g:578:9: LPAREN ( valuepart )* RPAREN
                    {
                    LPAREN99=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_valuepart996);  
                    stream_LPAREN.add(LPAREN99);

                    // CSS.g:578:16: ( valuepart )*
                    loop45:
                    do {
                        int alt45=2;
                        alt45 = dfa45.predict(input);
                        switch (alt45) {
                    	case 1 :
                    	    // CSS.g:578:16: valuepart
                    	    {
                    	    pushFollow(FOLLOW_valuepart_in_valuepart998);
                    	    valuepart100=valuepart();

                    	    state._fsp--;

                    	    stream_valuepart.add(valuepart100.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop45;
                        }
                    } while (true);

                    RPAREN101=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_valuepart1001);  
                    stream_RPAREN.add(RPAREN101);



                    // AST REWRITE
                    // elements: valuepart
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 578:34: -> ^( PARENBLOCK ( valuepart )* )
                    {
                        // CSS.g:578:37: ^( PARENBLOCK ( valuepart )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PARENBLOCK, "PARENBLOCK"), root_1);

                        // CSS.g:578:50: ( valuepart )*
                        while ( stream_valuepart.hasNext() ) {
                            adaptor.addChild(root_1, stream_valuepart.nextTree());

                        }
                        stream_valuepart.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 21 :
                    // CSS.g:579:9: LBRACE ( valuepart )* RBRACE
                    {
                    LBRACE102=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_valuepart1020);  
                    stream_LBRACE.add(LBRACE102);

                    // CSS.g:579:16: ( valuepart )*
                    loop46:
                    do {
                        int alt46=2;
                        alt46 = dfa46.predict(input);
                        switch (alt46) {
                    	case 1 :
                    	    // CSS.g:579:16: valuepart
                    	    {
                    	    pushFollow(FOLLOW_valuepart_in_valuepart1022);
                    	    valuepart103=valuepart();

                    	    state._fsp--;

                    	    stream_valuepart.add(valuepart103.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop46;
                        }
                    } while (true);

                    RBRACE104=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_valuepart1025);  
                    stream_RBRACE.add(RBRACE104);



                    // AST REWRITE
                    // elements: valuepart
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 579:34: -> ^( BRACEBLOCK ( valuepart )* )
                    {
                        // CSS.g:579:37: ^( BRACEBLOCK ( valuepart )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BRACEBLOCK, "BRACEBLOCK"), root_1);

                        // CSS.g:579:50: ( valuepart )*
                        while ( stream_valuepart.hasNext() ) {
                            adaptor.addChild(root_1, stream_valuepart.nextTree());

                        }
                        stream_valuepart.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }

            // CSS.g:580:8: ( S )*
            loop48:
            do {
                int alt48=2;
                alt48 = dfa48.predict(input);
                switch (alt48) {
            	case 1 :
            	    // CSS.g:580:8: S
            	    {
            	    S105=(Token)match(input,S,FOLLOW_S_in_valuepart1043);  
            	    stream_S.add(S105);


            	    }
            	    break;

            	default :
            	    break loop48;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "valuepart"

    public static class combined_selector_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "combined_selector"
    // CSS.g:583:1: combined_selector : selector ( ( combinator ) selector )* ;
    public final CSSParser.combined_selector_return combined_selector() throws RecognitionException {
        CSSParser.combined_selector_return retval = new CSSParser.combined_selector_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CSSParser.selector_return selector106 = null;

        CSSParser.combinator_return combinator107 = null;

        CSSParser.selector_return selector108 = null;



        try {
            // CSS.g:584:2: ( selector ( ( combinator ) selector )* )
            // CSS.g:584:4: selector ( ( combinator ) selector )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_selector_in_combined_selector1060);
            selector106=selector();

            state._fsp--;

            adaptor.addChild(root_0, selector106.getTree());
            // CSS.g:584:13: ( ( combinator ) selector )*
            loop49:
            do {
                int alt49=2;
                int LA49_0 = input.LA(1);

                if ( (LA49_0==S||LA49_0==GREATER||LA49_0==PLUS) ) {
                    alt49=1;
                }


                switch (alt49) {
            	case 1 :
            	    // CSS.g:584:14: ( combinator ) selector
            	    {
            	    // CSS.g:584:14: ( combinator )
            	    // CSS.g:584:15: combinator
            	    {
            	    pushFollow(FOLLOW_combinator_in_combined_selector1064);
            	    combinator107=combinator();

            	    state._fsp--;

            	    adaptor.addChild(root_0, combinator107.getTree());

            	    }

            	    pushFollow(FOLLOW_selector_in_combined_selector1067);
            	    selector108=selector();

            	    state._fsp--;

            	    adaptor.addChild(root_0, selector108.getTree());

            	    }
            	    break;

            	default :
            	    break loop49;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {

            	  log.warn("INVALID COMBINED SELECTOR");
            	  reportError(re);
                  recover(input,re);
            	
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "combined_selector"

    public static class combinator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "combinator"
    // CSS.g:592:1: combinator : ( GREATER ( S )* -> CHILD | PLUS ( S )* -> ADJACENT | S -> DESCENDANT );
    public final CSSParser.combinator_return combinator() throws RecognitionException {
        CSSParser.combinator_return retval = new CSSParser.combinator_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token GREATER109=null;
        Token S110=null;
        Token PLUS111=null;
        Token S112=null;
        Token S113=null;

        Object GREATER109_tree=null;
        Object S110_tree=null;
        Object PLUS111_tree=null;
        Object S112_tree=null;
        Object S113_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");

        try {
            // CSS.g:593:2: ( GREATER ( S )* -> CHILD | PLUS ( S )* -> ADJACENT | S -> DESCENDANT )
            int alt52=3;
            switch ( input.LA(1) ) {
            case GREATER:
                {
                alt52=1;
                }
                break;
            case PLUS:
                {
                alt52=2;
                }
                break;
            case S:
                {
                alt52=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;
            }

            switch (alt52) {
                case 1 :
                    // CSS.g:593:4: GREATER ( S )*
                    {
                    GREATER109=(Token)match(input,GREATER,FOLLOW_GREATER_in_combinator1087);  
                    stream_GREATER.add(GREATER109);

                    // CSS.g:593:12: ( S )*
                    loop50:
                    do {
                        int alt50=2;
                        int LA50_0 = input.LA(1);

                        if ( (LA50_0==S) ) {
                            alt50=1;
                        }


                        switch (alt50) {
                    	case 1 :
                    	    // CSS.g:593:12: S
                    	    {
                    	    S110=(Token)match(input,S,FOLLOW_S_in_combinator1089);  
                    	    stream_S.add(S110);


                    	    }
                    	    break;

                    	default :
                    	    break loop50;
                        }
                    } while (true);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 593:15: -> CHILD
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(CHILD, "CHILD"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // CSS.g:594:4: PLUS ( S )*
                    {
                    PLUS111=(Token)match(input,PLUS,FOLLOW_PLUS_in_combinator1099);  
                    stream_PLUS.add(PLUS111);

                    // CSS.g:594:9: ( S )*
                    loop51:
                    do {
                        int alt51=2;
                        int LA51_0 = input.LA(1);

                        if ( (LA51_0==S) ) {
                            alt51=1;
                        }


                        switch (alt51) {
                    	case 1 :
                    	    // CSS.g:594:9: S
                    	    {
                    	    S112=(Token)match(input,S,FOLLOW_S_in_combinator1101);  
                    	    stream_S.add(S112);


                    	    }
                    	    break;

                    	default :
                    	    break loop51;
                        }
                    } while (true);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 594:12: -> ADJACENT
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(ADJACENT, "ADJACENT"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // CSS.g:595:4: S
                    {
                    S113=(Token)match(input,S,FOLLOW_S_in_combinator1111);  
                    stream_S.add(S113);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 595:6: -> DESCENDANT
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(DESCENDANT, "DESCENDANT"));

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "combinator"

    public static class selector_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "selector"
    // CSS.g:598:1: selector : ( ( IDENT | ASTERISK ) ( selpart )* ( S )* -> ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* ) | ( selpart )+ ( S )* -> ^( SELECTOR ( selpart )+ ) );
    public final CSSParser.selector_return selector() throws RecognitionException {
        CSSParser.selector_return retval = new CSSParser.selector_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT114=null;
        Token ASTERISK115=null;
        Token S117=null;
        Token S119=null;
        CSSParser.selpart_return selpart116 = null;

        CSSParser.selpart_return selpart118 = null;


        Object IDENT114_tree=null;
        Object ASTERISK115_tree=null;
        Object S117_tree=null;
        Object S119_tree=null;
        RewriteRuleTokenStream stream_ASTERISK=new RewriteRuleTokenStream(adaptor,"token ASTERISK");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleSubtreeStream stream_selpart=new RewriteRuleSubtreeStream(adaptor,"rule selpart");
        try {
            // CSS.g:599:5: ( ( IDENT | ASTERISK ) ( selpart )* ( S )* -> ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* ) | ( selpart )+ ( S )* -> ^( SELECTOR ( selpart )+ ) )
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==IDENT||LA58_0==ASTERISK) ) {
                alt58=1;
            }
            else if ( (LA58_0==INVALID_SELPART||LA58_0==COLON||LA58_0==CLASSKEYWORD||LA58_0==HASH||LA58_0==LBRACE) ) {
                alt58=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }
            switch (alt58) {
                case 1 :
                    // CSS.g:599:7: ( IDENT | ASTERISK ) ( selpart )* ( S )*
                    {
                    // CSS.g:599:7: ( IDENT | ASTERISK )
                    int alt53=2;
                    int LA53_0 = input.LA(1);

                    if ( (LA53_0==IDENT) ) {
                        alt53=1;
                    }
                    else if ( (LA53_0==ASTERISK) ) {
                        alt53=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 0, input);

                        throw nvae;
                    }
                    switch (alt53) {
                        case 1 :
                            // CSS.g:599:8: IDENT
                            {
                            IDENT114=(Token)match(input,IDENT,FOLLOW_IDENT_in_selector1130);  
                            stream_IDENT.add(IDENT114);


                            }
                            break;
                        case 2 :
                            // CSS.g:599:16: ASTERISK
                            {
                            ASTERISK115=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_selector1134);  
                            stream_ASTERISK.add(ASTERISK115);


                            }
                            break;

                    }

                    // CSS.g:599:27: ( selpart )*
                    loop54:
                    do {
                        int alt54=2;
                        alt54 = dfa54.predict(input);
                        switch (alt54) {
                    	case 1 :
                    	    // CSS.g:599:27: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1138);
                    	    selpart116=selpart();

                    	    state._fsp--;

                    	    stream_selpart.add(selpart116.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop54;
                        }
                    } while (true);

                    // CSS.g:599:36: ( S )*
                    loop55:
                    do {
                        int alt55=2;
                        alt55 = dfa55.predict(input);
                        switch (alt55) {
                    	case 1 :
                    	    // CSS.g:599:36: S
                    	    {
                    	    S117=(Token)match(input,S,FOLLOW_S_in_selector1141);  
                    	    stream_S.add(S117);


                    	    }
                    	    break;

                    	default :
                    	    break loop55;
                        }
                    } while (true);



                    // AST REWRITE
                    // elements: selpart, IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 600:6: -> ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* )
                    {
                        // CSS.g:600:9: ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SELECTOR, "SELECTOR"), root_1);

                        // CSS.g:600:20: ^( ELEMENT ( IDENT )? )
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ELEMENT, "ELEMENT"), root_2);

                        // CSS.g:600:30: ( IDENT )?
                        if ( stream_IDENT.hasNext() ) {
                            adaptor.addChild(root_2, stream_IDENT.nextNode());

                        }
                        stream_IDENT.reset();

                        adaptor.addChild(root_1, root_2);
                        }
                        // CSS.g:600:38: ( selpart )*
                        while ( stream_selpart.hasNext() ) {
                            adaptor.addChild(root_1, stream_selpart.nextTree());

                        }
                        stream_selpart.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // CSS.g:601:7: ( selpart )+ ( S )*
                    {
                    // CSS.g:601:7: ( selpart )+
                    int cnt56=0;
                    loop56:
                    do {
                        int alt56=2;
                        alt56 = dfa56.predict(input);
                        switch (alt56) {
                    	case 1 :
                    	    // CSS.g:601:7: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1171);
                    	    selpart118=selpart();

                    	    state._fsp--;

                    	    stream_selpart.add(selpart118.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt56 >= 1 ) break loop56;
                                EarlyExitException eee =
                                    new EarlyExitException(56, input);
                                throw eee;
                        }
                        cnt56++;
                    } while (true);

                    // CSS.g:601:16: ( S )*
                    loop57:
                    do {
                        int alt57=2;
                        alt57 = dfa57.predict(input);
                        switch (alt57) {
                    	case 1 :
                    	    // CSS.g:601:16: S
                    	    {
                    	    S119=(Token)match(input,S,FOLLOW_S_in_selector1174);  
                    	    stream_S.add(S119);


                    	    }
                    	    break;

                    	default :
                    	    break loop57;
                        }
                    } while (true);



                    // AST REWRITE
                    // elements: selpart
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 602:9: -> ^( SELECTOR ( selpart )+ )
                    {
                        // CSS.g:602:12: ^( SELECTOR ( selpart )+ )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SELECTOR, "SELECTOR"), root_1);

                        if ( !(stream_selpart.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_selpart.hasNext() ) {
                            adaptor.addChild(root_1, stream_selpart.nextTree());

                        }
                        stream_selpart.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {

                  retval.tree = invalidFallback(CSSLexer.INVALID_SELECTOR, "INVALID_SELECTOR", re);
            	
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "selector"

    public static class selpart_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "selpart"
    // CSS.g:608:1: selpart : ( COLON IDENT -> PSEUDO IDENT | HASH | CLASSKEYWORD | LBRACE ( S )* attribute RBRACE -> ^( ATTRIBUTE attribute ) | COLON FUNCTION ( S )* IDENT RPAREN -> ^( FUNCTION IDENT ) | INVALID_SELPART );
    public final CSSParser.selpart_return selpart() throws RecognitionException {
        CSSParser.selpart_return retval = new CSSParser.selpart_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON120=null;
        Token IDENT121=null;
        Token HASH122=null;
        Token CLASSKEYWORD123=null;
        Token LBRACE124=null;
        Token S125=null;
        Token RBRACE127=null;
        Token COLON128=null;
        Token FUNCTION129=null;
        Token S130=null;
        Token IDENT131=null;
        Token RPAREN132=null;
        Token INVALID_SELPART133=null;
        CSSParser.attribute_return attribute126 = null;


        Object COLON120_tree=null;
        Object IDENT121_tree=null;
        Object HASH122_tree=null;
        Object CLASSKEYWORD123_tree=null;
        Object LBRACE124_tree=null;
        Object S125_tree=null;
        Object RBRACE127_tree=null;
        Object COLON128_tree=null;
        Object FUNCTION129_tree=null;
        Object S130_tree=null;
        Object IDENT131_tree=null;
        Object RPAREN132_tree=null;
        Object INVALID_SELPART133_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_FUNCTION=new RewriteRuleTokenStream(adaptor,"token FUNCTION");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleSubtreeStream stream_attribute=new RewriteRuleSubtreeStream(adaptor,"rule attribute");
        try {
            // CSS.g:609:5: ( COLON IDENT -> PSEUDO IDENT | HASH | CLASSKEYWORD | LBRACE ( S )* attribute RBRACE -> ^( ATTRIBUTE attribute ) | COLON FUNCTION ( S )* IDENT RPAREN -> ^( FUNCTION IDENT ) | INVALID_SELPART )
            int alt61=6;
            switch ( input.LA(1) ) {
            case COLON:
                {
                int LA61_1 = input.LA(2);

                if ( (LA61_1==IDENT) ) {
                    alt61=1;
                }
                else if ( (LA61_1==FUNCTION) ) {
                    alt61=5;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 61, 1, input);

                    throw nvae;
                }
                }
                break;
            case HASH:
                {
                alt61=2;
                }
                break;
            case CLASSKEYWORD:
                {
                alt61=3;
                }
                break;
            case LBRACE:
                {
                alt61=4;
                }
                break;
            case INVALID_SELPART:
                {
                alt61=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }

            switch (alt61) {
                case 1 :
                    // CSS.g:609:7: COLON IDENT
                    {
                    COLON120=(Token)match(input,COLON,FOLLOW_COLON_in_selpart1220);  
                    stream_COLON.add(COLON120);

                    IDENT121=(Token)match(input,IDENT,FOLLOW_IDENT_in_selpart1222);  
                    stream_IDENT.add(IDENT121);



                    // AST REWRITE
                    // elements: IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 609:19: -> PSEUDO IDENT
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(PSEUDO, "PSEUDO"));
                        adaptor.addChild(root_0, stream_IDENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // CSS.g:610:7: HASH
                    {
                    root_0 = (Object)adaptor.nil();

                    HASH122=(Token)match(input,HASH,FOLLOW_HASH_in_selpart1236); 
                    HASH122_tree = (Object)adaptor.create(HASH122);
                    adaptor.addChild(root_0, HASH122_tree);


                    }
                    break;
                case 3 :
                    // CSS.g:611:7: CLASSKEYWORD
                    {
                    root_0 = (Object)adaptor.nil();

                    CLASSKEYWORD123=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_selpart1244); 
                    CLASSKEYWORD123_tree = (Object)adaptor.create(CLASSKEYWORD123);
                    adaptor.addChild(root_0, CLASSKEYWORD123_tree);


                    }
                    break;
                case 4 :
                    // CSS.g:612:4: LBRACE ( S )* attribute RBRACE
                    {
                    LBRACE124=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_selpart1249);  
                    stream_LBRACE.add(LBRACE124);

                    // CSS.g:612:11: ( S )*
                    loop59:
                    do {
                        int alt59=2;
                        int LA59_0 = input.LA(1);

                        if ( (LA59_0==S) ) {
                            alt59=1;
                        }


                        switch (alt59) {
                    	case 1 :
                    	    // CSS.g:612:11: S
                    	    {
                    	    S125=(Token)match(input,S,FOLLOW_S_in_selpart1251);  
                    	    stream_S.add(S125);


                    	    }
                    	    break;

                    	default :
                    	    break loop59;
                        }
                    } while (true);

                    pushFollow(FOLLOW_attribute_in_selpart1254);
                    attribute126=attribute();

                    state._fsp--;

                    stream_attribute.add(attribute126.getTree());
                    RBRACE127=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_selpart1256);  
                    stream_RBRACE.add(RBRACE127);



                    // AST REWRITE
                    // elements: attribute
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 612:31: -> ^( ATTRIBUTE attribute )
                    {
                        // CSS.g:612:34: ^( ATTRIBUTE attribute )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ATTRIBUTE, "ATTRIBUTE"), root_1);

                        adaptor.addChild(root_1, stream_attribute.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // CSS.g:613:7: COLON FUNCTION ( S )* IDENT RPAREN
                    {
                    COLON128=(Token)match(input,COLON,FOLLOW_COLON_in_selpart1272);  
                    stream_COLON.add(COLON128);

                    FUNCTION129=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_selpart1274);  
                    stream_FUNCTION.add(FUNCTION129);

                    // CSS.g:613:22: ( S )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==S) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // CSS.g:613:22: S
                    	    {
                    	    S130=(Token)match(input,S,FOLLOW_S_in_selpart1276);  
                    	    stream_S.add(S130);


                    	    }
                    	    break;

                    	default :
                    	    break loop60;
                        }
                    } while (true);

                    IDENT131=(Token)match(input,IDENT,FOLLOW_IDENT_in_selpart1279);  
                    stream_IDENT.add(IDENT131);

                    RPAREN132=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_selpart1281);  
                    stream_RPAREN.add(RPAREN132);



                    // AST REWRITE
                    // elements: FUNCTION, IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 613:38: -> ^( FUNCTION IDENT )
                    {
                        // CSS.g:613:41: ^( FUNCTION IDENT )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_FUNCTION.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_IDENT.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // CSS.g:614:7: INVALID_SELPART
                    {
                    root_0 = (Object)adaptor.nil();

                    INVALID_SELPART133=(Token)match(input,INVALID_SELPART,FOLLOW_INVALID_SELPART_in_selpart1297); 
                    INVALID_SELPART133_tree = (Object)adaptor.create(INVALID_SELPART133);
                    adaptor.addChild(root_0, INVALID_SELPART133_tree);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {

                  retval.tree = invalidFallback(CSSLexer.INVALID_SELPART, "INVALID_SELPART", re);
            	
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "selpart"

    public static class attribute_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "attribute"
    // CSS.g:621:1: attribute : IDENT ( S )* ( ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )* )? ;
    public final CSSParser.attribute_return attribute() throws RecognitionException {
        CSSParser.attribute_return retval = new CSSParser.attribute_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT134=null;
        Token S135=null;
        Token set136=null;
        Token S137=null;
        Token IDENT138=null;
        Token S140=null;
        CSSParser.string_return string139 = null;


        Object IDENT134_tree=null;
        Object S135_tree=null;
        Object set136_tree=null;
        Object S137_tree=null;
        Object IDENT138_tree=null;
        Object S140_tree=null;

        try {
            // CSS.g:622:2: ( IDENT ( S )* ( ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )* )? )
            // CSS.g:622:4: IDENT ( S )* ( ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )* )?
            {
            root_0 = (Object)adaptor.nil();

            IDENT134=(Token)match(input,IDENT,FOLLOW_IDENT_in_attribute1323); 
            IDENT134_tree = (Object)adaptor.create(IDENT134);
            adaptor.addChild(root_0, IDENT134_tree);

            // CSS.g:622:10: ( S )*
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0==S) ) {
                    alt62=1;
                }


                switch (alt62) {
            	case 1 :
            	    // CSS.g:622:10: S
            	    {
            	    S135=(Token)match(input,S,FOLLOW_S_in_attribute1325); 
            	    S135_tree = (Object)adaptor.create(S135);
            	    adaptor.addChild(root_0, S135_tree);


            	    }
            	    break;

            	default :
            	    break loop62;
                }
            } while (true);

            // CSS.g:623:4: ( ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )* )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==INCLUDES||LA66_0==EQUALS||LA66_0==DASHMATCH) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // CSS.g:623:5: ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )*
                    {
                    set136=(Token)input.LT(1);
                    if ( input.LA(1)==INCLUDES||input.LA(1)==EQUALS||input.LA(1)==DASHMATCH ) {
                        input.consume();
                        adaptor.addChild(root_0, (Object)adaptor.create(set136));
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    // CSS.g:623:37: ( S )*
                    loop63:
                    do {
                        int alt63=2;
                        int LA63_0 = input.LA(1);

                        if ( (LA63_0==S) ) {
                            alt63=1;
                        }


                        switch (alt63) {
                    	case 1 :
                    	    // CSS.g:623:37: S
                    	    {
                    	    S137=(Token)match(input,S,FOLLOW_S_in_attribute1344); 
                    	    S137_tree = (Object)adaptor.create(S137);
                    	    adaptor.addChild(root_0, S137_tree);


                    	    }
                    	    break;

                    	default :
                    	    break loop63;
                        }
                    } while (true);

                    // CSS.g:623:40: ( IDENT | string )
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==IDENT) ) {
                        alt64=1;
                    }
                    else if ( (LA64_0==INVALID_STRING||LA64_0==STRING) ) {
                        alt64=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 64, 0, input);

                        throw nvae;
                    }
                    switch (alt64) {
                        case 1 :
                            // CSS.g:623:41: IDENT
                            {
                            IDENT138=(Token)match(input,IDENT,FOLLOW_IDENT_in_attribute1348); 
                            IDENT138_tree = (Object)adaptor.create(IDENT138);
                            adaptor.addChild(root_0, IDENT138_tree);


                            }
                            break;
                        case 2 :
                            // CSS.g:623:49: string
                            {
                            pushFollow(FOLLOW_string_in_attribute1352);
                            string139=string();

                            state._fsp--;

                            adaptor.addChild(root_0, string139.getTree());

                            }
                            break;

                    }

                    // CSS.g:623:57: ( S )*
                    loop65:
                    do {
                        int alt65=2;
                        int LA65_0 = input.LA(1);

                        if ( (LA65_0==S) ) {
                            alt65=1;
                        }


                        switch (alt65) {
                    	case 1 :
                    	    // CSS.g:623:57: S
                    	    {
                    	    S140=(Token)match(input,S,FOLLOW_S_in_attribute1355); 
                    	    S140_tree = (Object)adaptor.create(S140);
                    	    adaptor.addChild(root_0, S140_tree);


                    	    }
                    	    break;

                    	default :
                    	    break loop65;
                        }
                    } while (true);


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "attribute"

    public static class string_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "string"
    // CSS.g:626:1: string : ( STRING | INVALID_STRING );
    public final CSSParser.string_return string() throws RecognitionException {
        CSSParser.string_return retval = new CSSParser.string_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set141=null;

        Object set141_tree=null;

        try {
            // CSS.g:627:2: ( STRING | INVALID_STRING )
            // CSS.g:
            {
            root_0 = (Object)adaptor.nil();

            set141=(Token)input.LT(1);
            if ( input.LA(1)==INVALID_STRING||input.LA(1)==STRING ) {
                input.consume();
                adaptor.addChild(root_0, (Object)adaptor.create(set141));
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "string"

    public static class any_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "any"
    // CSS.g:632:1: any : ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) ) ( S )* ;
    public final CSSParser.any_return any() throws RecognitionException {
        CSSParser.any_return retval = new CSSParser.any_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT142=null;
        Token CLASSKEYWORD143=null;
        Token NUMBER144=null;
        Token PERCENTAGE145=null;
        Token DIMENSION146=null;
        Token URI148=null;
        Token HASH149=null;
        Token UNIRANGE150=null;
        Token INCLUDES151=null;
        Token COLON152=null;
        Token COMMA153=null;
        Token GREATER154=null;
        Token EQUALS155=null;
        Token SLASH156=null;
        Token EXCLAMATION157=null;
        Token MINUS158=null;
        Token PLUS159=null;
        Token ASTERISK160=null;
        Token FUNCTION161=null;
        Token S162=null;
        Token RPAREN164=null;
        Token DASHMATCH165=null;
        Token LPAREN166=null;
        Token RPAREN168=null;
        Token LBRACE169=null;
        Token RBRACE171=null;
        Token S172=null;
        CSSParser.string_return string147 = null;

        CSSParser.any_return any163 = null;

        CSSParser.any_return any167 = null;

        CSSParser.any_return any170 = null;


        Object IDENT142_tree=null;
        Object CLASSKEYWORD143_tree=null;
        Object NUMBER144_tree=null;
        Object PERCENTAGE145_tree=null;
        Object DIMENSION146_tree=null;
        Object URI148_tree=null;
        Object HASH149_tree=null;
        Object UNIRANGE150_tree=null;
        Object INCLUDES151_tree=null;
        Object COLON152_tree=null;
        Object COMMA153_tree=null;
        Object GREATER154_tree=null;
        Object EQUALS155_tree=null;
        Object SLASH156_tree=null;
        Object EXCLAMATION157_tree=null;
        Object MINUS158_tree=null;
        Object PLUS159_tree=null;
        Object ASTERISK160_tree=null;
        Object FUNCTION161_tree=null;
        Object S162_tree=null;
        Object RPAREN164_tree=null;
        Object DASHMATCH165_tree=null;
        Object LPAREN166_tree=null;
        Object RPAREN168_tree=null;
        Object LBRACE169_tree=null;
        Object RBRACE171_tree=null;
        Object S172_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_HASH=new RewriteRuleTokenStream(adaptor,"token HASH");
        RewriteRuleTokenStream stream_SLASH=new RewriteRuleTokenStream(adaptor,"token SLASH");
        RewriteRuleTokenStream stream_DASHMATCH=new RewriteRuleTokenStream(adaptor,"token DASHMATCH");
        RewriteRuleTokenStream stream_UNIRANGE=new RewriteRuleTokenStream(adaptor,"token UNIRANGE");
        RewriteRuleTokenStream stream_NUMBER=new RewriteRuleTokenStream(adaptor,"token NUMBER");
        RewriteRuleTokenStream stream_EXCLAMATION=new RewriteRuleTokenStream(adaptor,"token EXCLAMATION");
        RewriteRuleTokenStream stream_CLASSKEYWORD=new RewriteRuleTokenStream(adaptor,"token CLASSKEYWORD");
        RewriteRuleTokenStream stream_INCLUDES=new RewriteRuleTokenStream(adaptor,"token INCLUDES");
        RewriteRuleTokenStream stream_URI=new RewriteRuleTokenStream(adaptor,"token URI");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_EQUALS=new RewriteRuleTokenStream(adaptor,"token EQUALS");
        RewriteRuleTokenStream stream_DIMENSION=new RewriteRuleTokenStream(adaptor,"token DIMENSION");
        RewriteRuleTokenStream stream_FUNCTION=new RewriteRuleTokenStream(adaptor,"token FUNCTION");
        RewriteRuleTokenStream stream_ASTERISK=new RewriteRuleTokenStream(adaptor,"token ASTERISK");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
        RewriteRuleTokenStream stream_PERCENTAGE=new RewriteRuleTokenStream(adaptor,"token PERCENTAGE");
        RewriteRuleSubtreeStream stream_string=new RewriteRuleSubtreeStream(adaptor,"rule string");
        RewriteRuleSubtreeStream stream_any=new RewriteRuleSubtreeStream(adaptor,"rule any");
        try {
            // CSS.g:633:2: ( ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) ) ( S )* )
            // CSS.g:633:4: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) ) ( S )*
            {
            // CSS.g:633:4: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) )
            int alt71=23;
            alt71 = dfa71.predict(input);
            switch (alt71) {
                case 1 :
                    // CSS.g:633:6: IDENT
                    {
                    IDENT142=(Token)match(input,IDENT,FOLLOW_IDENT_in_any1390);  
                    stream_IDENT.add(IDENT142);



                    // AST REWRITE
                    // elements: IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 633:12: -> IDENT
                    {
                        adaptor.addChild(root_0, stream_IDENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // CSS.g:634:6: CLASSKEYWORD
                    {
                    CLASSKEYWORD143=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_any1401);  
                    stream_CLASSKEYWORD.add(CLASSKEYWORD143);



                    // AST REWRITE
                    // elements: CLASSKEYWORD
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 634:19: -> CLASSKEYWORD
                    {
                        adaptor.addChild(root_0, stream_CLASSKEYWORD.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // CSS.g:635:6: NUMBER
                    {
                    NUMBER144=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_any1412);  
                    stream_NUMBER.add(NUMBER144);



                    // AST REWRITE
                    // elements: NUMBER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 635:13: -> NUMBER
                    {
                        adaptor.addChild(root_0, stream_NUMBER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // CSS.g:636:6: PERCENTAGE
                    {
                    PERCENTAGE145=(Token)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_any1423);  
                    stream_PERCENTAGE.add(PERCENTAGE145);



                    // AST REWRITE
                    // elements: PERCENTAGE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 636:17: -> PERCENTAGE
                    {
                        adaptor.addChild(root_0, stream_PERCENTAGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // CSS.g:637:6: DIMENSION
                    {
                    DIMENSION146=(Token)match(input,DIMENSION,FOLLOW_DIMENSION_in_any1433);  
                    stream_DIMENSION.add(DIMENSION146);



                    // AST REWRITE
                    // elements: DIMENSION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 637:16: -> DIMENSION
                    {
                        adaptor.addChild(root_0, stream_DIMENSION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // CSS.g:638:6: string
                    {
                    pushFollow(FOLLOW_string_in_any1444);
                    string147=string();

                    state._fsp--;

                    stream_string.add(string147.getTree());


                    // AST REWRITE
                    // elements: string
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 638:13: -> string
                    {
                        adaptor.addChild(root_0, stream_string.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // CSS.g:639:9: URI
                    {
                    URI148=(Token)match(input,URI,FOLLOW_URI_in_any1458);  
                    stream_URI.add(URI148);



                    // AST REWRITE
                    // elements: URI
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 639:16: -> URI
                    {
                        adaptor.addChild(root_0, stream_URI.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // CSS.g:640:9: HASH
                    {
                    HASH149=(Token)match(input,HASH,FOLLOW_HASH_in_any1475);  
                    stream_HASH.add(HASH149);



                    // AST REWRITE
                    // elements: HASH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 640:14: -> HASH
                    {
                        adaptor.addChild(root_0, stream_HASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // CSS.g:641:9: UNIRANGE
                    {
                    UNIRANGE150=(Token)match(input,UNIRANGE,FOLLOW_UNIRANGE_in_any1489);  
                    stream_UNIRANGE.add(UNIRANGE150);



                    // AST REWRITE
                    // elements: UNIRANGE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 641:18: -> UNIRANGE
                    {
                        adaptor.addChild(root_0, stream_UNIRANGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // CSS.g:642:9: INCLUDES
                    {
                    INCLUDES151=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_any1503);  
                    stream_INCLUDES.add(INCLUDES151);



                    // AST REWRITE
                    // elements: INCLUDES
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 642:18: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // CSS.g:643:9: COLON
                    {
                    COLON152=(Token)match(input,COLON,FOLLOW_COLON_in_any1517);  
                    stream_COLON.add(COLON152);



                    // AST REWRITE
                    // elements: COLON
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 643:15: -> COLON
                    {
                        adaptor.addChild(root_0, stream_COLON.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // CSS.g:644:9: COMMA
                    {
                    COMMA153=(Token)match(input,COMMA,FOLLOW_COMMA_in_any1531);  
                    stream_COMMA.add(COMMA153);



                    // AST REWRITE
                    // elements: COMMA
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 644:15: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // CSS.g:645:9: GREATER
                    {
                    GREATER154=(Token)match(input,GREATER,FOLLOW_GREATER_in_any1545);  
                    stream_GREATER.add(GREATER154);



                    // AST REWRITE
                    // elements: GREATER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 645:17: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // CSS.g:646:9: EQUALS
                    {
                    EQUALS155=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_any1559);  
                    stream_EQUALS.add(EQUALS155);



                    // AST REWRITE
                    // elements: EQUALS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 646:16: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // CSS.g:647:9: SLASH
                    {
                    SLASH156=(Token)match(input,SLASH,FOLLOW_SLASH_in_any1573);  
                    stream_SLASH.add(SLASH156);



                    // AST REWRITE
                    // elements: SLASH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 647:15: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // CSS.g:648:9: EXCLAMATION
                    {
                    EXCLAMATION157=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_any1587);  
                    stream_EXCLAMATION.add(EXCLAMATION157);



                    // AST REWRITE
                    // elements: EXCLAMATION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 648:21: -> EXCLAMATION
                    {
                        adaptor.addChild(root_0, stream_EXCLAMATION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // CSS.g:649:6: MINUS
                    {
                    MINUS158=(Token)match(input,MINUS,FOLLOW_MINUS_in_any1598);  
                    stream_MINUS.add(MINUS158);



                    // AST REWRITE
                    // elements: MINUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 649:12: -> MINUS
                    {
                        adaptor.addChild(root_0, stream_MINUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 18 :
                    // CSS.g:650:6: PLUS
                    {
                    PLUS159=(Token)match(input,PLUS,FOLLOW_PLUS_in_any1609);  
                    stream_PLUS.add(PLUS159);



                    // AST REWRITE
                    // elements: PLUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 650:11: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 19 :
                    // CSS.g:651:6: ASTERISK
                    {
                    ASTERISK160=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_any1620);  
                    stream_ASTERISK.add(ASTERISK160);



                    // AST REWRITE
                    // elements: ASTERISK
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 651:15: -> ASTERISK
                    {
                        adaptor.addChild(root_0, stream_ASTERISK.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 20 :
                    // CSS.g:652:9: FUNCTION ( S )* ( any )* RPAREN
                    {
                    FUNCTION161=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_any1637);  
                    stream_FUNCTION.add(FUNCTION161);

                    // CSS.g:652:18: ( S )*
                    loop67:
                    do {
                        int alt67=2;
                        alt67 = dfa67.predict(input);
                        switch (alt67) {
                    	case 1 :
                    	    // CSS.g:652:18: S
                    	    {
                    	    S162=(Token)match(input,S,FOLLOW_S_in_any1639);  
                    	    stream_S.add(S162);


                    	    }
                    	    break;

                    	default :
                    	    break loop67;
                        }
                    } while (true);

                    // CSS.g:652:21: ( any )*
                    loop68:
                    do {
                        int alt68=2;
                        alt68 = dfa68.predict(input);
                        switch (alt68) {
                    	case 1 :
                    	    // CSS.g:652:21: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_any1642);
                    	    any163=any();

                    	    state._fsp--;

                    	    stream_any.add(any163.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop68;
                        }
                    } while (true);

                    RPAREN164=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_any1645);  
                    stream_RPAREN.add(RPAREN164);



                    // AST REWRITE
                    // elements: FUNCTION, any
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 652:33: -> ^( FUNCTION ( any )* )
                    {
                        // CSS.g:652:36: ^( FUNCTION ( any )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_FUNCTION.nextNode(), root_1);

                        // CSS.g:652:47: ( any )*
                        while ( stream_any.hasNext() ) {
                            adaptor.addChild(root_1, stream_any.nextTree());

                        }
                        stream_any.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 21 :
                    // CSS.g:653:9: DASHMATCH
                    {
                    DASHMATCH165=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_any1665);  
                    stream_DASHMATCH.add(DASHMATCH165);



                    // AST REWRITE
                    // elements: DASHMATCH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 653:19: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 22 :
                    // CSS.g:654:9: LPAREN ( any )* RPAREN
                    {
                    LPAREN166=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_any1679);  
                    stream_LPAREN.add(LPAREN166);

                    // CSS.g:654:16: ( any )*
                    loop69:
                    do {
                        int alt69=2;
                        alt69 = dfa69.predict(input);
                        switch (alt69) {
                    	case 1 :
                    	    // CSS.g:654:16: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_any1681);
                    	    any167=any();

                    	    state._fsp--;

                    	    stream_any.add(any167.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop69;
                        }
                    } while (true);

                    RPAREN168=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_any1684);  
                    stream_RPAREN.add(RPAREN168);



                    // AST REWRITE
                    // elements: any
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 654:28: -> ^( PARENBLOCK ( any )* )
                    {
                        // CSS.g:654:31: ^( PARENBLOCK ( any )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PARENBLOCK, "PARENBLOCK"), root_1);

                        // CSS.g:654:44: ( any )*
                        while ( stream_any.hasNext() ) {
                            adaptor.addChild(root_1, stream_any.nextTree());

                        }
                        stream_any.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 23 :
                    // CSS.g:655:9: LBRACE ( any )* RBRACE
                    {
                    LBRACE169=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_any1703);  
                    stream_LBRACE.add(LBRACE169);

                    // CSS.g:655:16: ( any )*
                    loop70:
                    do {
                        int alt70=2;
                        alt70 = dfa70.predict(input);
                        switch (alt70) {
                    	case 1 :
                    	    // CSS.g:655:16: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_any1705);
                    	    any170=any();

                    	    state._fsp--;

                    	    stream_any.add(any170.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop70;
                        }
                    } while (true);

                    RBRACE171=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_any1708);  
                    stream_RBRACE.add(RBRACE171);



                    // AST REWRITE
                    // elements: any
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 655:28: -> ^( BRACEBLOCK ( any )* )
                    {
                        // CSS.g:655:31: ^( BRACEBLOCK ( any )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BRACEBLOCK, "BRACEBLOCK"), root_1);

                        // CSS.g:655:44: ( any )*
                        while ( stream_any.hasNext() ) {
                            adaptor.addChild(root_1, stream_any.nextTree());

                        }
                        stream_any.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }

            // CSS.g:656:8: ( S )*
            loop72:
            do {
                int alt72=2;
                alt72 = dfa72.predict(input);
                switch (alt72) {
            	case 1 :
            	    // CSS.g:656:8: S
            	    {
            	    S172=(Token)match(input,S,FOLLOW_S_in_any1726);  
            	    stream_S.add(S172);


            	    }
            	    break;

            	default :
            	    break loop72;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "any"

    // Delegated rules


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA2 dfa2 = new DFA2(this);
    protected DFA13 dfa13 = new DFA13(this);
    protected DFA14 dfa14 = new DFA14(this);
    protected DFA17 dfa17 = new DFA17(this);
    protected DFA30 dfa30 = new DFA30(this);
    protected DFA35 dfa35 = new DFA35(this);
    protected DFA40 dfa40 = new DFA40(this);
    protected DFA36 dfa36 = new DFA36(this);
    protected DFA38 dfa38 = new DFA38(this);
    protected DFA37 dfa37 = new DFA37(this);
    protected DFA39 dfa39 = new DFA39(this);
    protected DFA47 dfa47 = new DFA47(this);
    protected DFA44 dfa44 = new DFA44(this);
    protected DFA45 dfa45 = new DFA45(this);
    protected DFA46 dfa46 = new DFA46(this);
    protected DFA48 dfa48 = new DFA48(this);
    protected DFA54 dfa54 = new DFA54(this);
    protected DFA55 dfa55 = new DFA55(this);
    protected DFA56 dfa56 = new DFA56(this);
    protected DFA57 dfa57 = new DFA57(this);
    protected DFA71 dfa71 = new DFA71(this);
    protected DFA67 dfa67 = new DFA67(this);
    protected DFA68 dfa68 = new DFA68(this);
    protected DFA69 dfa69 = new DFA69(this);
    protected DFA70 dfa70 = new DFA70(this);
    protected DFA72 dfa72 = new DFA72(this);
    static final String DFA1_eotS =
        "\23\uffff";
    static final String DFA1_eofS =
        "\1\1\22\uffff";
    static final String DFA1_minS =
        "\1\24\22\uffff";
    static final String DFA1_maxS =
        "\1\74\22\uffff";
    static final String DFA1_acceptS =
        "\1\uffff\1\5\1\1\1\2\1\3\1\4\15\uffff";
    static final String DFA1_specialS =
        "\23\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\5\2\uffff\1\5\2\uffff\1\5\1\2\1\3\1\4\5\5\3\uffff\2\5\2\uffff"+
            "\1\5\5\uffff\1\5\6\uffff\1\5\4\uffff\1\5",
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

    static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
    static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
    static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
    static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
    static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
    static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
    static final short[][] DFA1_transition;

    static {
        int numStates = DFA1_transitionS.length;
        DFA1_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
        }
    }

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = DFA1_eot;
            this.eof = DFA1_eof;
            this.min = DFA1_min;
            this.max = DFA1_max;
            this.accept = DFA1_accept;
            this.special = DFA1_special;
            this.transition = DFA1_transition;
        }
        public String getDescription() {
            return "()* loopback of 491:4: ( CDO | CDC | S | statement )*";
        }
    }
    static final String DFA2_eotS =
        "\17\uffff";
    static final String DFA2_eofS =
        "\17\uffff";
    static final String DFA2_minS =
        "\1\24\16\uffff";
    static final String DFA2_maxS =
        "\1\74\16\uffff";
    static final String DFA2_acceptS =
        "\1\uffff\1\1\6\uffff\1\2\6\uffff";
    static final String DFA2_specialS =
        "\17\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\10\2\uffff\1\1\2\uffff\1\10\3\uffff\3\10\2\1\3\uffff\2\10"+
            "\2\uffff\1\1\5\uffff\1\1\6\uffff\1\1\4\uffff\1\1",
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

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "495:1: statement : ( ruleset | atstatement );";
        }
    }
    static final String DFA13_eotS =
        "\12\uffff";
    static final String DFA13_eofS =
        "\12\uffff";
    static final String DFA13_minS =
        "\1\27\11\uffff";
    static final String DFA13_maxS =
        "\1\74\11\uffff";
    static final String DFA13_acceptS =
        "\1\uffff\1\2\7\uffff\1\1";
    static final String DFA13_specialS =
        "\12\uffff}>";
    static final String[] DFA13_transitionS = {
            "\1\1\5\uffff\1\11\3\uffff\2\1\2\uffff\1\1\4\uffff\1\1\5\uffff"+
            "\1\1\6\uffff\1\1\4\uffff\1\1",
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

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "()* loopback of 508:10: ( S )*";
        }
    }
    static final String DFA14_eotS =
        "\12\uffff";
    static final String DFA14_eofS =
        "\12\uffff";
    static final String DFA14_minS =
        "\1\27\11\uffff";
    static final String DFA14_maxS =
        "\1\74\11\uffff";
    static final String DFA14_acceptS =
        "\1\uffff\1\2\7\uffff\1\1";
    static final String DFA14_specialS =
        "\12\uffff}>";
    static final String[] DFA14_transitionS = {
            "\1\1\5\uffff\1\11\3\uffff\2\1\2\uffff\1\1\4\uffff\1\1\5\uffff"+
            "\1\1\6\uffff\1\1\4\uffff\1\1",
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

    static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
    static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
    static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
    static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
    static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
    static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
    static final short[][] DFA14_transition;

    static {
        int numStates = DFA14_transitionS.length;
        DFA14_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
        }
    }

    class DFA14 extends DFA {

        public DFA14(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = DFA14_eot;
            this.eof = DFA14_eof;
            this.min = DFA14_min;
            this.max = DFA14_max;
            this.accept = DFA14_accept;
            this.special = DFA14_special;
            this.transition = DFA14_transition;
        }
        public String getDescription() {
            return "()* loopback of 508:22: ( S )*";
        }
    }
    static final String DFA17_eotS =
        "\31\uffff";
    static final String DFA17_eofS =
        "\31\uffff";
    static final String DFA17_minS =
        "\1\25\30\uffff";
    static final String DFA17_maxS =
        "\1\76\30\uffff";
    static final String DFA17_acceptS =
        "\1\uffff\1\2\1\1\26\uffff";
    static final String DFA17_specialS =
        "\31\uffff}>";
    static final String[] DFA17_transitionS = {
            "\1\2\13\uffff\2\2\2\uffff\1\1\2\uffff\21\2\1\uffff\3\2\1\uffff"+
            "\1\2",
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

    static final short[] DFA17_eot = DFA.unpackEncodedString(DFA17_eotS);
    static final short[] DFA17_eof = DFA.unpackEncodedString(DFA17_eofS);
    static final char[] DFA17_min = DFA.unpackEncodedStringToUnsignedChars(DFA17_minS);
    static final char[] DFA17_max = DFA.unpackEncodedStringToUnsignedChars(DFA17_maxS);
    static final short[] DFA17_accept = DFA.unpackEncodedString(DFA17_acceptS);
    static final short[] DFA17_special = DFA.unpackEncodedString(DFA17_specialS);
    static final short[][] DFA17_transition;

    static {
        int numStates = DFA17_transitionS.length;
        DFA17_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA17_transition[i] = DFA.unpackEncodedString(DFA17_transitionS[i]);
        }
    }

    class DFA17 extends DFA {

        public DFA17(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 17;
            this.eot = DFA17_eot;
            this.eof = DFA17_eof;
            this.min = DFA17_min;
            this.max = DFA17_max;
            this.accept = DFA17_accept;
            this.special = DFA17_special;
            this.transition = DFA17_transition;
        }
        public String getDescription() {
            return "()* loopback of 509:24: ( any )*";
        }
    }
    static final String DFA30_eotS =
        "\32\uffff";
    static final String DFA30_eofS =
        "\32\uffff";
    static final String DFA30_minS =
        "\1\25\31\uffff";
    static final String DFA30_maxS =
        "\1\76\31\uffff";
    static final String DFA30_acceptS =
        "\1\uffff\1\2\27\uffff\1\1";
    static final String DFA30_specialS =
        "\32\uffff}>";
    static final String[] DFA30_transitionS = {
            "\1\1\7\uffff\1\31\3\uffff\3\1\3\uffff\2\1\1\uffff\17\1\1\uffff"+
            "\3\1\1\uffff\1\1",
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

    static final short[] DFA30_eot = DFA.unpackEncodedString(DFA30_eotS);
    static final short[] DFA30_eof = DFA.unpackEncodedString(DFA30_eofS);
    static final char[] DFA30_min = DFA.unpackEncodedStringToUnsignedChars(DFA30_minS);
    static final char[] DFA30_max = DFA.unpackEncodedStringToUnsignedChars(DFA30_maxS);
    static final short[] DFA30_accept = DFA.unpackEncodedString(DFA30_acceptS);
    static final short[] DFA30_special = DFA.unpackEncodedString(DFA30_specialS);
    static final short[][] DFA30_transition;

    static {
        int numStates = DFA30_transitionS.length;
        DFA30_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA30_transition[i] = DFA.unpackEncodedString(DFA30_transitionS[i]);
        }
    }

    class DFA30 extends DFA {

        public DFA30(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 30;
            this.eot = DFA30_eot;
            this.eof = DFA30_eof;
            this.min = DFA30_min;
            this.max = DFA30_max;
            this.accept = DFA30_accept;
            this.special = DFA30_special;
            this.transition = DFA30_transition;
        }
        public String getDescription() {
            return "()* loopback of 533:19: ( S )*";
        }
    }
    static final String DFA35_eotS =
        "\35\uffff";
    static final String DFA35_eofS =
        "\35\uffff";
    static final String DFA35_minS =
        "\1\25\34\uffff";
    static final String DFA35_maxS =
        "\1\76\34\uffff";
    static final String DFA35_acceptS =
        "\1\uffff\1\2\3\uffff\1\1\27\uffff";
    static final String DFA35_specialS =
        "\35\uffff}>";
    static final String[] DFA35_transitionS = {
            "\1\5\13\uffff\3\5\2\1\1\uffff\2\5\1\1\17\5\1\1\3\5\1\uffff\1"+
            "\5",
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
            "",
            "",
            "",
            ""
    };

    static final short[] DFA35_eot = DFA.unpackEncodedString(DFA35_eotS);
    static final short[] DFA35_eof = DFA.unpackEncodedString(DFA35_eofS);
    static final char[] DFA35_min = DFA.unpackEncodedStringToUnsignedChars(DFA35_minS);
    static final char[] DFA35_max = DFA.unpackEncodedStringToUnsignedChars(DFA35_maxS);
    static final short[] DFA35_accept = DFA.unpackEncodedString(DFA35_acceptS);
    static final short[] DFA35_special = DFA.unpackEncodedString(DFA35_specialS);
    static final short[][] DFA35_transition;

    static {
        int numStates = DFA35_transitionS.length;
        DFA35_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA35_transition[i] = DFA.unpackEncodedString(DFA35_transitionS[i]);
        }
    }

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = DFA35_eot;
            this.eof = DFA35_eof;
            this.min = DFA35_min;
            this.max = DFA35_max;
            this.accept = DFA35_accept;
            this.special = DFA35_special;
            this.transition = DFA35_transition;
        }
        public String getDescription() {
            return "()+ loopback of 548:4: ( term )+";
        }
    }
    static final String DFA40_eotS =
        "\31\uffff";
    static final String DFA40_eofS =
        "\31\uffff";
    static final String DFA40_minS =
        "\1\25\30\uffff";
    static final String DFA40_maxS =
        "\1\76\30\uffff";
    static final String DFA40_acceptS =
        "\1\uffff\1\1\25\uffff\1\2\1\3";
    static final String DFA40_specialS =
        "\31\uffff}>";
    static final String[] DFA40_transitionS = {
            "\1\1\13\uffff\2\1\1\27\3\uffff\1\30\1\1\1\uffff\17\1\1\uffff"+
            "\3\1\1\uffff\1\1",
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

    static final short[] DFA40_eot = DFA.unpackEncodedString(DFA40_eotS);
    static final short[] DFA40_eof = DFA.unpackEncodedString(DFA40_eofS);
    static final char[] DFA40_min = DFA.unpackEncodedStringToUnsignedChars(DFA40_minS);
    static final char[] DFA40_max = DFA.unpackEncodedStringToUnsignedChars(DFA40_maxS);
    static final short[] DFA40_accept = DFA.unpackEncodedString(DFA40_acceptS);
    static final short[] DFA40_special = DFA.unpackEncodedString(DFA40_specialS);
    static final short[][] DFA40_transition;

    static {
        int numStates = DFA40_transitionS.length;
        DFA40_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA40_transition[i] = DFA.unpackEncodedString(DFA40_transitionS[i]);
        }
    }

    class DFA40 extends DFA {

        public DFA40(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 40;
            this.eot = DFA40_eot;
            this.eof = DFA40_eof;
            this.min = DFA40_min;
            this.max = DFA40_max;
            this.accept = DFA40_accept;
            this.special = DFA40_special;
            this.transition = DFA40_transition;
        }
        public String getDescription() {
            return "552:1: term : ( valuepart -> valuepart | LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY -> CURLYBLOCK | ATKEYWORD ( S )* -> ATKEYWORD );";
        }
    }
    static final String DFA36_eotS =
        "\33\uffff";
    static final String DFA36_eofS =
        "\33\uffff";
    static final String DFA36_minS =
        "\1\25\32\uffff";
    static final String DFA36_maxS =
        "\1\76\32\uffff";
    static final String DFA36_acceptS =
        "\1\uffff\1\2\30\uffff\1\1";
    static final String DFA36_specialS =
        "\33\uffff}>";
    static final String[] DFA36_transitionS = {
            "\1\1\7\uffff\1\32\3\uffff\2\1\1\uffff\2\1\2\uffff\21\1\1\uffff"+
            "\3\1\1\uffff\1\1",
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
            "",
            ""
    };

    static final short[] DFA36_eot = DFA.unpackEncodedString(DFA36_eotS);
    static final short[] DFA36_eof = DFA.unpackEncodedString(DFA36_eofS);
    static final char[] DFA36_min = DFA.unpackEncodedStringToUnsignedChars(DFA36_minS);
    static final char[] DFA36_max = DFA.unpackEncodedStringToUnsignedChars(DFA36_maxS);
    static final short[] DFA36_accept = DFA.unpackEncodedString(DFA36_acceptS);
    static final short[] DFA36_special = DFA.unpackEncodedString(DFA36_specialS);
    static final short[][] DFA36_transition;

    static {
        int numStates = DFA36_transitionS.length;
        DFA36_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA36_transition[i] = DFA.unpackEncodedString(DFA36_transitionS[i]);
        }
    }

    class DFA36 extends DFA {

        public DFA36(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 36;
            this.eot = DFA36_eot;
            this.eof = DFA36_eof;
            this.min = DFA36_min;
            this.max = DFA36_max;
            this.accept = DFA36_accept;
            this.special = DFA36_special;
            this.transition = DFA36_transition;
        }
        public String getDescription() {
            return "()* loopback of 554:14: ( S )*";
        }
    }
    static final String DFA38_eotS =
        "\32\uffff";
    static final String DFA38_eofS =
        "\32\uffff";
    static final String DFA38_minS =
        "\1\25\31\uffff";
    static final String DFA38_maxS =
        "\1\76\31\uffff";
    static final String DFA38_acceptS =
        "\1\uffff\1\3\1\1\26\uffff\1\2";
    static final String DFA38_specialS =
        "\32\uffff}>";
    static final String[] DFA38_transitionS = {
            "\1\2\13\uffff\2\2\1\uffff\1\31\1\1\2\uffff\21\2\1\uffff\3\2"+
            "\1\uffff\1\2",
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

    static final short[] DFA38_eot = DFA.unpackEncodedString(DFA38_eotS);
    static final short[] DFA38_eof = DFA.unpackEncodedString(DFA38_eofS);
    static final char[] DFA38_min = DFA.unpackEncodedStringToUnsignedChars(DFA38_minS);
    static final char[] DFA38_max = DFA.unpackEncodedStringToUnsignedChars(DFA38_maxS);
    static final short[] DFA38_accept = DFA.unpackEncodedString(DFA38_acceptS);
    static final short[] DFA38_special = DFA.unpackEncodedString(DFA38_specialS);
    static final short[][] DFA38_transition;

    static {
        int numStates = DFA38_transitionS.length;
        DFA38_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA38_transition[i] = DFA.unpackEncodedString(DFA38_transitionS[i]);
        }
    }

    class DFA38 extends DFA {

        public DFA38(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 38;
            this.eot = DFA38_eot;
            this.eof = DFA38_eof;
            this.min = DFA38_min;
            this.max = DFA38_max;
            this.accept = DFA38_accept;
            this.special = DFA38_special;
            this.transition = DFA38_transition;
        }
        public String getDescription() {
            return "()* loopback of 554:17: ( any | SEMICOLON ( S )* )*";
        }
    }
    static final String DFA37_eotS =
        "\33\uffff";
    static final String DFA37_eofS =
        "\33\uffff";
    static final String DFA37_minS =
        "\1\25\32\uffff";
    static final String DFA37_maxS =
        "\1\76\32\uffff";
    static final String DFA37_acceptS =
        "\1\uffff\1\2\30\uffff\1\1";
    static final String DFA37_specialS =
        "\33\uffff}>";
    static final String[] DFA37_transitionS = {
            "\1\1\7\uffff\1\32\3\uffff\2\1\1\uffff\2\1\2\uffff\21\1\1\uffff"+
            "\3\1\1\uffff\1\1",
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
            "",
            ""
    };

    static final short[] DFA37_eot = DFA.unpackEncodedString(DFA37_eotS);
    static final short[] DFA37_eof = DFA.unpackEncodedString(DFA37_eofS);
    static final char[] DFA37_min = DFA.unpackEncodedStringToUnsignedChars(DFA37_minS);
    static final char[] DFA37_max = DFA.unpackEncodedStringToUnsignedChars(DFA37_maxS);
    static final short[] DFA37_accept = DFA.unpackEncodedString(DFA37_acceptS);
    static final short[] DFA37_special = DFA.unpackEncodedString(DFA37_specialS);
    static final short[][] DFA37_transition;

    static {
        int numStates = DFA37_transitionS.length;
        DFA37_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA37_transition[i] = DFA.unpackEncodedString(DFA37_transitionS[i]);
        }
    }

    class DFA37 extends DFA {

        public DFA37(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 37;
            this.eot = DFA37_eot;
            this.eof = DFA37_eof;
            this.min = DFA37_min;
            this.max = DFA37_max;
            this.accept = DFA37_accept;
            this.special = DFA37_special;
            this.transition = DFA37_transition;
        }
        public String getDescription() {
            return "()* loopback of 554:34: ( S )*";
        }
    }
    static final String DFA39_eotS =
        "\36\uffff";
    static final String DFA39_eofS =
        "\36\uffff";
    static final String DFA39_minS =
        "\1\25\35\uffff";
    static final String DFA39_maxS =
        "\1\76\35\uffff";
    static final String DFA39_acceptS =
        "\1\uffff\1\2\33\uffff\1\1";
    static final String DFA39_specialS =
        "\36\uffff}>";
    static final String[] DFA39_transitionS = {
            "\1\1\7\uffff\1\35\3\uffff\5\1\1\uffff\26\1\1\uffff\1\1",
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
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA39_eot = DFA.unpackEncodedString(DFA39_eotS);
    static final short[] DFA39_eof = DFA.unpackEncodedString(DFA39_eofS);
    static final char[] DFA39_min = DFA.unpackEncodedStringToUnsignedChars(DFA39_minS);
    static final char[] DFA39_max = DFA.unpackEncodedStringToUnsignedChars(DFA39_maxS);
    static final short[] DFA39_accept = DFA.unpackEncodedString(DFA39_acceptS);
    static final short[] DFA39_special = DFA.unpackEncodedString(DFA39_specialS);
    static final short[][] DFA39_transition;

    static {
        int numStates = DFA39_transitionS.length;
        DFA39_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA39_transition[i] = DFA.unpackEncodedString(DFA39_transitionS[i]);
        }
    }

    class DFA39 extends DFA {

        public DFA39(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 39;
            this.eot = DFA39_eot;
            this.eof = DFA39_eof;
            this.min = DFA39_min;
            this.max = DFA39_max;
            this.accept = DFA39_accept;
            this.special = DFA39_special;
            this.transition = DFA39_transition;
        }
        public String getDescription() {
            return "()* loopback of 555:17: ( S )*";
        }
    }
    static final String DFA47_eotS =
        "\32\uffff";
    static final String DFA47_eofS =
        "\32\uffff";
    static final String DFA47_minS =
        "\1\25\2\uffff\1\54\26\uffff";
    static final String DFA47_maxS =
        "\1\76\2\uffff\1\56\26\uffff";
    static final String DFA47_acceptS =
        "\1\uffff\1\1\1\2\1\uffff\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13"+
        "\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\3\uffff";
    static final String DFA47_specialS =
        "\32\uffff}>";
    static final String[] DFA47_transitionS = {
            "\1\7\13\uffff\1\14\1\1\5\uffff\1\15\1\uffff\1\2\1\3\1\4\1\5"+
            "\1\6\1\10\1\11\1\12\1\13\1\16\1\17\1\20\1\21\1\22\1\23\1\uffff"+
            "\1\24\1\25\1\26\1\uffff\1\7",
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
            "",
            "",
            "",
            ""
    };

    static final short[] DFA47_eot = DFA.unpackEncodedString(DFA47_eotS);
    static final short[] DFA47_eof = DFA.unpackEncodedString(DFA47_eofS);
    static final char[] DFA47_min = DFA.unpackEncodedStringToUnsignedChars(DFA47_minS);
    static final char[] DFA47_max = DFA.unpackEncodedStringToUnsignedChars(DFA47_maxS);
    static final short[] DFA47_accept = DFA.unpackEncodedString(DFA47_acceptS);
    static final short[] DFA47_special = DFA.unpackEncodedString(DFA47_specialS);
    static final short[][] DFA47_transition;

    static {
        int numStates = DFA47_transitionS.length;
        DFA47_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA47_transition[i] = DFA.unpackEncodedString(DFA47_transitionS[i]);
        }
    }

    class DFA47 extends DFA {

        public DFA47(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 47;
            this.eot = DFA47_eot;
            this.eof = DFA47_eof;
            this.min = DFA47_min;
            this.max = DFA47_max;
            this.accept = DFA47_accept;
            this.special = DFA47_special;
            this.transition = DFA47_transition;
        }
        public String getDescription() {
            return "559:7: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* terms RPAREN -> ^( FUNCTION terms ) | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) )";
        }
    }
    static final String DFA44_eotS =
        "\32\uffff";
    static final String DFA44_eofS =
        "\32\uffff";
    static final String DFA44_minS =
        "\1\25\31\uffff";
    static final String DFA44_maxS =
        "\1\76\31\uffff";
    static final String DFA44_acceptS =
        "\1\uffff\1\2\27\uffff\1\1";
    static final String DFA44_specialS =
        "\32\uffff}>";
    static final String[] DFA44_transitionS = {
            "\1\1\7\uffff\1\31\3\uffff\3\1\3\uffff\2\1\1\uffff\17\1\1\uffff"+
            "\3\1\1\uffff\1\1",
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

    static final short[] DFA44_eot = DFA.unpackEncodedString(DFA44_eotS);
    static final short[] DFA44_eof = DFA.unpackEncodedString(DFA44_eofS);
    static final char[] DFA44_min = DFA.unpackEncodedStringToUnsignedChars(DFA44_minS);
    static final char[] DFA44_max = DFA.unpackEncodedStringToUnsignedChars(DFA44_maxS);
    static final short[] DFA44_accept = DFA.unpackEncodedString(DFA44_acceptS);
    static final short[] DFA44_special = DFA.unpackEncodedString(DFA44_specialS);
    static final short[][] DFA44_transition;

    static {
        int numStates = DFA44_transitionS.length;
        DFA44_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA44_transition[i] = DFA.unpackEncodedString(DFA44_transitionS[i]);
        }
    }

    class DFA44 extends DFA {

        public DFA44(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 44;
            this.eot = DFA44_eot;
            this.eof = DFA44_eof;
            this.min = DFA44_min;
            this.max = DFA44_max;
            this.accept = DFA44_accept;
            this.special = DFA44_special;
            this.transition = DFA44_transition;
        }
        public String getDescription() {
            return "()* loopback of 576:18: ( S )*";
        }
    }
    static final String DFA45_eotS =
        "\30\uffff";
    static final String DFA45_eofS =
        "\30\uffff";
    static final String DFA45_minS =
        "\1\25\27\uffff";
    static final String DFA45_maxS =
        "\1\76\27\uffff";
    static final String DFA45_acceptS =
        "\1\uffff\1\2\1\1\25\uffff";
    static final String DFA45_specialS =
        "\30\uffff}>";
    static final String[] DFA45_transitionS = {
            "\1\2\13\uffff\2\2\5\uffff\1\2\1\uffff\17\2\1\1\3\2\1\uffff\1"+
            "\2",
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

    static final short[] DFA45_eot = DFA.unpackEncodedString(DFA45_eotS);
    static final short[] DFA45_eof = DFA.unpackEncodedString(DFA45_eofS);
    static final char[] DFA45_min = DFA.unpackEncodedStringToUnsignedChars(DFA45_minS);
    static final char[] DFA45_max = DFA.unpackEncodedStringToUnsignedChars(DFA45_maxS);
    static final short[] DFA45_accept = DFA.unpackEncodedString(DFA45_acceptS);
    static final short[] DFA45_special = DFA.unpackEncodedString(DFA45_specialS);
    static final short[][] DFA45_transition;

    static {
        int numStates = DFA45_transitionS.length;
        DFA45_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA45_transition[i] = DFA.unpackEncodedString(DFA45_transitionS[i]);
        }
    }

    class DFA45 extends DFA {

        public DFA45(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 45;
            this.eot = DFA45_eot;
            this.eof = DFA45_eof;
            this.min = DFA45_min;
            this.max = DFA45_max;
            this.accept = DFA45_accept;
            this.special = DFA45_special;
            this.transition = DFA45_transition;
        }
        public String getDescription() {
            return "()* loopback of 578:16: ( valuepart )*";
        }
    }
    static final String DFA46_eotS =
        "\30\uffff";
    static final String DFA46_eofS =
        "\30\uffff";
    static final String DFA46_minS =
        "\1\25\27\uffff";
    static final String DFA46_maxS =
        "\1\76\27\uffff";
    static final String DFA46_acceptS =
        "\1\uffff\1\2\1\1\25\uffff";
    static final String DFA46_specialS =
        "\30\uffff}>";
    static final String[] DFA46_transitionS = {
            "\1\2\13\uffff\2\2\5\uffff\1\2\1\uffff\17\2\1\uffff\3\2\1\1\1"+
            "\2",
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

    static final short[] DFA46_eot = DFA.unpackEncodedString(DFA46_eotS);
    static final short[] DFA46_eof = DFA.unpackEncodedString(DFA46_eofS);
    static final char[] DFA46_min = DFA.unpackEncodedStringToUnsignedChars(DFA46_minS);
    static final char[] DFA46_max = DFA.unpackEncodedStringToUnsignedChars(DFA46_maxS);
    static final short[] DFA46_accept = DFA.unpackEncodedString(DFA46_acceptS);
    static final short[] DFA46_special = DFA.unpackEncodedString(DFA46_specialS);
    static final short[][] DFA46_transition;

    static {
        int numStates = DFA46_transitionS.length;
        DFA46_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA46_transition[i] = DFA.unpackEncodedString(DFA46_transitionS[i]);
        }
    }

    class DFA46 extends DFA {

        public DFA46(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 46;
            this.eot = DFA46_eot;
            this.eof = DFA46_eof;
            this.min = DFA46_min;
            this.max = DFA46_max;
            this.accept = DFA46_accept;
            this.special = DFA46_special;
            this.transition = DFA46_transition;
        }
        public String getDescription() {
            return "()* loopback of 579:16: ( valuepart )*";
        }
    }
    static final String DFA48_eotS =
        "\37\uffff";
    static final String DFA48_eofS =
        "\37\uffff";
    static final String DFA48_minS =
        "\1\25\36\uffff";
    static final String DFA48_maxS =
        "\1\76\36\uffff";
    static final String DFA48_acceptS =
        "\1\uffff\1\2\34\uffff\1\1";
    static final String DFA48_specialS =
        "\37\uffff}>";
    static final String[] DFA48_transitionS = {
            "\1\1\7\uffff\1\36\3\uffff\5\1\1\uffff\30\1",
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
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA48_eot = DFA.unpackEncodedString(DFA48_eotS);
    static final short[] DFA48_eof = DFA.unpackEncodedString(DFA48_eofS);
    static final char[] DFA48_min = DFA.unpackEncodedStringToUnsignedChars(DFA48_minS);
    static final char[] DFA48_max = DFA.unpackEncodedStringToUnsignedChars(DFA48_maxS);
    static final short[] DFA48_accept = DFA.unpackEncodedString(DFA48_acceptS);
    static final short[] DFA48_special = DFA.unpackEncodedString(DFA48_specialS);
    static final short[][] DFA48_transition;

    static {
        int numStates = DFA48_transitionS.length;
        DFA48_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA48_transition[i] = DFA.unpackEncodedString(DFA48_transitionS[i]);
        }
    }

    class DFA48 extends DFA {

        public DFA48(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 48;
            this.eot = DFA48_eot;
            this.eof = DFA48_eof;
            this.min = DFA48_min;
            this.max = DFA48_max;
            this.accept = DFA48_accept;
            this.special = DFA48_special;
            this.transition = DFA48_transition;
        }
        public String getDescription() {
            return "()* loopback of 580:8: ( S )*";
        }
    }
    static final String DFA54_eotS =
        "\13\uffff";
    static final String DFA54_eofS =
        "\13\uffff";
    static final String DFA54_minS =
        "\1\27\12\uffff";
    static final String DFA54_maxS =
        "\1\74\12\uffff";
    static final String DFA54_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\4\uffff";
    static final String DFA54_specialS =
        "\13\uffff}>";
    static final String[] DFA54_transitionS = {
            "\1\6\5\uffff\1\1\3\uffff\1\6\1\uffff\1\1\4\uffff\1\1\1\uffff"+
            "\1\6\5\uffff\1\6\2\uffff\1\1\2\uffff\1\1\5\uffff\1\6",
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

    static final short[] DFA54_eot = DFA.unpackEncodedString(DFA54_eotS);
    static final short[] DFA54_eof = DFA.unpackEncodedString(DFA54_eofS);
    static final char[] DFA54_min = DFA.unpackEncodedStringToUnsignedChars(DFA54_minS);
    static final char[] DFA54_max = DFA.unpackEncodedStringToUnsignedChars(DFA54_maxS);
    static final short[] DFA54_accept = DFA.unpackEncodedString(DFA54_acceptS);
    static final short[] DFA54_special = DFA.unpackEncodedString(DFA54_specialS);
    static final short[][] DFA54_transition;

    static {
        int numStates = DFA54_transitionS.length;
        DFA54_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA54_transition[i] = DFA.unpackEncodedString(DFA54_transitionS[i]);
        }
    }

    class DFA54 extends DFA {

        public DFA54(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 54;
            this.eot = DFA54_eot;
            this.eof = DFA54_eof;
            this.min = DFA54_min;
            this.max = DFA54_max;
            this.accept = DFA54_accept;
            this.special = DFA54_special;
            this.transition = DFA54_transition;
        }
        public String getDescription() {
            return "()* loopback of 599:27: ( selpart )*";
        }
    }
    static final String DFA55_eotS =
        "\22\uffff";
    static final String DFA55_eofS =
        "\22\uffff";
    static final String DFA55_minS =
        "\1\35\2\uffff\1\27\16\uffff";
    static final String DFA55_maxS =
        "\1\66\2\uffff\1\74\16\uffff";
    static final String DFA55_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\13\uffff";
    static final String DFA55_specialS =
        "\22\uffff}>";
    static final String[] DFA55_transitionS = {
            "\1\3\5\uffff\1\1\4\uffff\1\1\12\uffff\1\1\2\uffff\1\1",
            "",
            "",
            "\1\1\5\uffff\1\6\3\uffff\2\1\1\6\4\uffff\1\6\1\uffff\1\1\5"+
            "\uffff\1\1\2\uffff\1\6\2\uffff\1\6\1\1\4\uffff\1\1",
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

    static final short[] DFA55_eot = DFA.unpackEncodedString(DFA55_eotS);
    static final short[] DFA55_eof = DFA.unpackEncodedString(DFA55_eofS);
    static final char[] DFA55_min = DFA.unpackEncodedStringToUnsignedChars(DFA55_minS);
    static final char[] DFA55_max = DFA.unpackEncodedStringToUnsignedChars(DFA55_maxS);
    static final short[] DFA55_accept = DFA.unpackEncodedString(DFA55_acceptS);
    static final short[] DFA55_special = DFA.unpackEncodedString(DFA55_specialS);
    static final short[][] DFA55_transition;

    static {
        int numStates = DFA55_transitionS.length;
        DFA55_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA55_transition[i] = DFA.unpackEncodedString(DFA55_transitionS[i]);
        }
    }

    class DFA55 extends DFA {

        public DFA55(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 55;
            this.eot = DFA55_eot;
            this.eof = DFA55_eof;
            this.min = DFA55_min;
            this.max = DFA55_max;
            this.accept = DFA55_accept;
            this.special = DFA55_special;
            this.transition = DFA55_transition;
        }
        public String getDescription() {
            return "()* loopback of 599:36: ( S )*";
        }
    }
    static final String DFA56_eotS =
        "\13\uffff";
    static final String DFA56_eofS =
        "\13\uffff";
    static final String DFA56_minS =
        "\1\27\12\uffff";
    static final String DFA56_maxS =
        "\1\74\12\uffff";
    static final String DFA56_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\4\uffff";
    static final String DFA56_specialS =
        "\13\uffff}>";
    static final String[] DFA56_transitionS = {
            "\1\6\5\uffff\1\1\3\uffff\1\6\1\uffff\1\1\4\uffff\1\1\1\uffff"+
            "\1\6\5\uffff\1\6\2\uffff\1\1\2\uffff\1\1\5\uffff\1\6",
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

    static final short[] DFA56_eot = DFA.unpackEncodedString(DFA56_eotS);
    static final short[] DFA56_eof = DFA.unpackEncodedString(DFA56_eofS);
    static final char[] DFA56_min = DFA.unpackEncodedStringToUnsignedChars(DFA56_minS);
    static final char[] DFA56_max = DFA.unpackEncodedStringToUnsignedChars(DFA56_maxS);
    static final short[] DFA56_accept = DFA.unpackEncodedString(DFA56_acceptS);
    static final short[] DFA56_special = DFA.unpackEncodedString(DFA56_specialS);
    static final short[][] DFA56_transition;

    static {
        int numStates = DFA56_transitionS.length;
        DFA56_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA56_transition[i] = DFA.unpackEncodedString(DFA56_transitionS[i]);
        }
    }

    class DFA56 extends DFA {

        public DFA56(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 56;
            this.eot = DFA56_eot;
            this.eof = DFA56_eof;
            this.min = DFA56_min;
            this.max = DFA56_max;
            this.accept = DFA56_accept;
            this.special = DFA56_special;
            this.transition = DFA56_transition;
        }
        public String getDescription() {
            return "()+ loopback of 601:7: ( selpart )+";
        }
    }
    static final String DFA57_eotS =
        "\22\uffff";
    static final String DFA57_eofS =
        "\22\uffff";
    static final String DFA57_minS =
        "\1\35\2\uffff\1\27\16\uffff";
    static final String DFA57_maxS =
        "\1\66\2\uffff\1\74\16\uffff";
    static final String DFA57_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\13\uffff";
    static final String DFA57_specialS =
        "\22\uffff}>";
    static final String[] DFA57_transitionS = {
            "\1\3\5\uffff\1\1\4\uffff\1\1\12\uffff\1\1\2\uffff\1\1",
            "",
            "",
            "\1\1\5\uffff\1\6\3\uffff\2\1\1\6\4\uffff\1\6\1\uffff\1\1\5"+
            "\uffff\1\1\2\uffff\1\6\2\uffff\1\6\1\1\4\uffff\1\1",
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

    static final short[] DFA57_eot = DFA.unpackEncodedString(DFA57_eotS);
    static final short[] DFA57_eof = DFA.unpackEncodedString(DFA57_eofS);
    static final char[] DFA57_min = DFA.unpackEncodedStringToUnsignedChars(DFA57_minS);
    static final char[] DFA57_max = DFA.unpackEncodedStringToUnsignedChars(DFA57_maxS);
    static final short[] DFA57_accept = DFA.unpackEncodedString(DFA57_acceptS);
    static final short[] DFA57_special = DFA.unpackEncodedString(DFA57_specialS);
    static final short[][] DFA57_transition;

    static {
        int numStates = DFA57_transitionS.length;
        DFA57_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA57_transition[i] = DFA.unpackEncodedString(DFA57_transitionS[i]);
        }
    }

    class DFA57 extends DFA {

        public DFA57(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 57;
            this.eot = DFA57_eot;
            this.eof = DFA57_eof;
            this.min = DFA57_min;
            this.max = DFA57_max;
            this.accept = DFA57_accept;
            this.special = DFA57_special;
            this.transition = DFA57_transition;
        }
        public String getDescription() {
            return "()* loopback of 601:16: ( S )*";
        }
    }
    static final String DFA71_eotS =
        "\30\uffff";
    static final String DFA71_eofS =
        "\30\uffff";
    static final String DFA71_minS =
        "\1\25\27\uffff";
    static final String DFA71_maxS =
        "\1\76\27\uffff";
    static final String DFA71_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27";
    static final String DFA71_specialS =
        "\30\uffff}>";
    static final String[] DFA71_transitionS = {
            "\1\6\13\uffff\1\13\1\1\5\uffff\1\14\1\20\1\2\1\21\1\3\1\4\1"+
            "\5\1\7\1\10\1\11\1\12\1\15\1\16\1\17\1\22\1\23\1\24\1\uffff"+
            "\1\25\1\26\1\27\1\uffff\1\6",
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

    static final short[] DFA71_eot = DFA.unpackEncodedString(DFA71_eotS);
    static final short[] DFA71_eof = DFA.unpackEncodedString(DFA71_eofS);
    static final char[] DFA71_min = DFA.unpackEncodedStringToUnsignedChars(DFA71_minS);
    static final char[] DFA71_max = DFA.unpackEncodedStringToUnsignedChars(DFA71_maxS);
    static final short[] DFA71_accept = DFA.unpackEncodedString(DFA71_acceptS);
    static final short[] DFA71_special = DFA.unpackEncodedString(DFA71_specialS);
    static final short[][] DFA71_transition;

    static {
        int numStates = DFA71_transitionS.length;
        DFA71_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA71_transition[i] = DFA.unpackEncodedString(DFA71_transitionS[i]);
        }
    }

    class DFA71 extends DFA {

        public DFA71(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 71;
            this.eot = DFA71_eot;
            this.eof = DFA71_eof;
            this.min = DFA71_min;
            this.max = DFA71_max;
            this.accept = DFA71_accept;
            this.special = DFA71_special;
            this.transition = DFA71_transition;
        }
        public String getDescription() {
            return "633:4: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) )";
        }
    }
    static final String DFA67_eotS =
        "\32\uffff";
    static final String DFA67_eofS =
        "\32\uffff";
    static final String DFA67_minS =
        "\1\25\31\uffff";
    static final String DFA67_maxS =
        "\1\76\31\uffff";
    static final String DFA67_acceptS =
        "\1\uffff\1\2\27\uffff\1\1";
    static final String DFA67_specialS =
        "\32\uffff}>";
    static final String[] DFA67_transitionS = {
            "\1\1\7\uffff\1\31\3\uffff\2\1\5\uffff\25\1\1\uffff\1\1",
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

    static final short[] DFA67_eot = DFA.unpackEncodedString(DFA67_eotS);
    static final short[] DFA67_eof = DFA.unpackEncodedString(DFA67_eofS);
    static final char[] DFA67_min = DFA.unpackEncodedStringToUnsignedChars(DFA67_minS);
    static final char[] DFA67_max = DFA.unpackEncodedStringToUnsignedChars(DFA67_maxS);
    static final short[] DFA67_accept = DFA.unpackEncodedString(DFA67_acceptS);
    static final short[] DFA67_special = DFA.unpackEncodedString(DFA67_specialS);
    static final short[][] DFA67_transition;

    static {
        int numStates = DFA67_transitionS.length;
        DFA67_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA67_transition[i] = DFA.unpackEncodedString(DFA67_transitionS[i]);
        }
    }

    class DFA67 extends DFA {

        public DFA67(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 67;
            this.eot = DFA67_eot;
            this.eof = DFA67_eof;
            this.min = DFA67_min;
            this.max = DFA67_max;
            this.accept = DFA67_accept;
            this.special = DFA67_special;
            this.transition = DFA67_transition;
        }
        public String getDescription() {
            return "()* loopback of 652:18: ( S )*";
        }
    }
    static final String DFA68_eotS =
        "\31\uffff";
    static final String DFA68_eofS =
        "\31\uffff";
    static final String DFA68_minS =
        "\1\25\30\uffff";
    static final String DFA68_maxS =
        "\1\76\30\uffff";
    static final String DFA68_acceptS =
        "\1\uffff\1\2\1\1\26\uffff";
    static final String DFA68_specialS =
        "\31\uffff}>";
    static final String[] DFA68_transitionS = {
            "\1\2\13\uffff\2\2\5\uffff\21\2\1\1\3\2\1\uffff\1\2",
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

    static final short[] DFA68_eot = DFA.unpackEncodedString(DFA68_eotS);
    static final short[] DFA68_eof = DFA.unpackEncodedString(DFA68_eofS);
    static final char[] DFA68_min = DFA.unpackEncodedStringToUnsignedChars(DFA68_minS);
    static final char[] DFA68_max = DFA.unpackEncodedStringToUnsignedChars(DFA68_maxS);
    static final short[] DFA68_accept = DFA.unpackEncodedString(DFA68_acceptS);
    static final short[] DFA68_special = DFA.unpackEncodedString(DFA68_specialS);
    static final short[][] DFA68_transition;

    static {
        int numStates = DFA68_transitionS.length;
        DFA68_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA68_transition[i] = DFA.unpackEncodedString(DFA68_transitionS[i]);
        }
    }

    class DFA68 extends DFA {

        public DFA68(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 68;
            this.eot = DFA68_eot;
            this.eof = DFA68_eof;
            this.min = DFA68_min;
            this.max = DFA68_max;
            this.accept = DFA68_accept;
            this.special = DFA68_special;
            this.transition = DFA68_transition;
        }
        public String getDescription() {
            return "()* loopback of 652:21: ( any )*";
        }
    }
    static final String DFA69_eotS =
        "\31\uffff";
    static final String DFA69_eofS =
        "\31\uffff";
    static final String DFA69_minS =
        "\1\25\30\uffff";
    static final String DFA69_maxS =
        "\1\76\30\uffff";
    static final String DFA69_acceptS =
        "\1\uffff\1\2\1\1\26\uffff";
    static final String DFA69_specialS =
        "\31\uffff}>";
    static final String[] DFA69_transitionS = {
            "\1\2\13\uffff\2\2\5\uffff\21\2\1\1\3\2\1\uffff\1\2",
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

    static final short[] DFA69_eot = DFA.unpackEncodedString(DFA69_eotS);
    static final short[] DFA69_eof = DFA.unpackEncodedString(DFA69_eofS);
    static final char[] DFA69_min = DFA.unpackEncodedStringToUnsignedChars(DFA69_minS);
    static final char[] DFA69_max = DFA.unpackEncodedStringToUnsignedChars(DFA69_maxS);
    static final short[] DFA69_accept = DFA.unpackEncodedString(DFA69_acceptS);
    static final short[] DFA69_special = DFA.unpackEncodedString(DFA69_specialS);
    static final short[][] DFA69_transition;

    static {
        int numStates = DFA69_transitionS.length;
        DFA69_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA69_transition[i] = DFA.unpackEncodedString(DFA69_transitionS[i]);
        }
    }

    class DFA69 extends DFA {

        public DFA69(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 69;
            this.eot = DFA69_eot;
            this.eof = DFA69_eof;
            this.min = DFA69_min;
            this.max = DFA69_max;
            this.accept = DFA69_accept;
            this.special = DFA69_special;
            this.transition = DFA69_transition;
        }
        public String getDescription() {
            return "()* loopback of 654:16: ( any )*";
        }
    }
    static final String DFA70_eotS =
        "\31\uffff";
    static final String DFA70_eofS =
        "\31\uffff";
    static final String DFA70_minS =
        "\1\25\30\uffff";
    static final String DFA70_maxS =
        "\1\76\30\uffff";
    static final String DFA70_acceptS =
        "\1\uffff\1\2\1\1\26\uffff";
    static final String DFA70_specialS =
        "\31\uffff}>";
    static final String[] DFA70_transitionS = {
            "\1\2\13\uffff\2\2\5\uffff\21\2\1\uffff\3\2\1\1\1\2",
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

    static final short[] DFA70_eot = DFA.unpackEncodedString(DFA70_eotS);
    static final short[] DFA70_eof = DFA.unpackEncodedString(DFA70_eofS);
    static final char[] DFA70_min = DFA.unpackEncodedStringToUnsignedChars(DFA70_minS);
    static final char[] DFA70_max = DFA.unpackEncodedStringToUnsignedChars(DFA70_maxS);
    static final short[] DFA70_accept = DFA.unpackEncodedString(DFA70_acceptS);
    static final short[] DFA70_special = DFA.unpackEncodedString(DFA70_specialS);
    static final short[][] DFA70_transition;

    static {
        int numStates = DFA70_transitionS.length;
        DFA70_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA70_transition[i] = DFA.unpackEncodedString(DFA70_transitionS[i]);
        }
    }

    class DFA70 extends DFA {

        public DFA70(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 70;
            this.eot = DFA70_eot;
            this.eof = DFA70_eof;
            this.min = DFA70_min;
            this.max = DFA70_max;
            this.accept = DFA70_accept;
            this.special = DFA70_special;
            this.transition = DFA70_transition;
        }
        public String getDescription() {
            return "()* loopback of 655:16: ( any )*";
        }
    }
    static final String DFA72_eotS =
        "\35\uffff";
    static final String DFA72_eofS =
        "\35\uffff";
    static final String DFA72_minS =
        "\1\25\34\uffff";
    static final String DFA72_maxS =
        "\1\76\34\uffff";
    static final String DFA72_acceptS =
        "\1\uffff\1\2\32\uffff\1\1";
    static final String DFA72_specialS =
        "\35\uffff}>";
    static final String[] DFA72_transitionS = {
            "\1\1\7\uffff\1\34\3\uffff\2\1\1\uffff\2\1\2\uffff\27\1",
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
            "",
            "",
            "",
            ""
    };

    static final short[] DFA72_eot = DFA.unpackEncodedString(DFA72_eotS);
    static final short[] DFA72_eof = DFA.unpackEncodedString(DFA72_eofS);
    static final char[] DFA72_min = DFA.unpackEncodedStringToUnsignedChars(DFA72_minS);
    static final char[] DFA72_max = DFA.unpackEncodedStringToUnsignedChars(DFA72_maxS);
    static final short[] DFA72_accept = DFA.unpackEncodedString(DFA72_acceptS);
    static final short[] DFA72_special = DFA.unpackEncodedString(DFA72_specialS);
    static final short[][] DFA72_transition;

    static {
        int numStates = DFA72_transitionS.length;
        DFA72_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA72_transition[i] = DFA.unpackEncodedString(DFA72_transitionS[i]);
        }
    }

    class DFA72 extends DFA {

        public DFA72(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 72;
            this.eot = DFA72_eot;
            this.eof = DFA72_eof;
            this.min = DFA72_min;
            this.max = DFA72_max;
            this.accept = DFA72_accept;
            this.special = DFA72_special;
            this.transition = DFA72_transition;
        }
        public String getDescription() {
            return "()* loopback of 656:8: ( S )*";
        }
    }
 

    public static final BitSet FOLLOW_CDO_in_stylesheet179 = new BitSet(new long[]{0x108104C7FC900002L});
    public static final BitSet FOLLOW_CDC_in_stylesheet183 = new BitSet(new long[]{0x108104C7FC900002L});
    public static final BitSet FOLLOW_S_in_stylesheet187 = new BitSet(new long[]{0x108104C7FC900002L});
    public static final BitSet FOLLOW_statement_in_stylesheet191 = new BitSet(new long[]{0x108104C7FC900002L});
    public static final BitSet FOLLOW_ruleset_in_statement221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atstatement_in_statement225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARSET_in_atstatement236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_atstatement241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_IMPORT_in_atstatement246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_END_in_atstatement251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PAGE_in_atstatement256 = new BitSet(new long[]{0x0000000A20000000L});
    public static final BitSet FOLLOW_S_in_atstatement258 = new BitSet(new long[]{0x0000000A20000000L});
    public static final BitSet FOLLOW_COLON_in_atstatement262 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_IDENT_in_atstatement264 = new BitSet(new long[]{0x0000000820000000L});
    public static final BitSet FOLLOW_S_in_atstatement266 = new BitSet(new long[]{0x0000000820000000L});
    public static final BitSet FOLLOW_LCURLY_in_atstatement274 = new BitSet(new long[]{0x0000003420000000L});
    public static final BitSet FOLLOW_S_in_atstatement276 = new BitSet(new long[]{0x0000003420000000L});
    public static final BitSet FOLLOW_declaration_in_atstatement279 = new BitSet(new long[]{0x0000003000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_atstatement283 = new BitSet(new long[]{0x0000003420000000L});
    public static final BitSet FOLLOW_S_in_atstatement285 = new BitSet(new long[]{0x0000003420000000L});
    public static final BitSet FOLLOW_declaration_in_atstatement288 = new BitSet(new long[]{0x0000003000000000L});
    public static final BitSet FOLLOW_RCURLY_in_atstatement297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MEDIA_in_atstatement314 = new BitSet(new long[]{0x0000000C20000000L});
    public static final BitSet FOLLOW_S_in_atstatement316 = new BitSet(new long[]{0x0000000C20000000L});
    public static final BitSet FOLLOW_media_in_atstatement319 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LCURLY_in_atstatement325 = new BitSet(new long[]{0x1081042620800000L});
    public static final BitSet FOLLOW_S_in_atstatement327 = new BitSet(new long[]{0x1081042620800000L});
    public static final BitSet FOLLOW_ruleset_in_atstatement331 = new BitSet(new long[]{0x1081042620800000L});
    public static final BitSet FOLLOW_S_in_atstatement333 = new BitSet(new long[]{0x1081042620800000L});
    public static final BitSet FOLLOW_RCURLY_in_atstatement338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATKEYWORD_in_atstatement356 = new BitSet(new long[]{0x0000000820000000L});
    public static final BitSet FOLLOW_S_in_atstatement358 = new BitSet(new long[]{0x0000000820000000L});
    public static final BitSet FOLLOW_LCURLY_in_atstatement361 = new BitSet(new long[]{0x5DFFFF2600200000L});
    public static final BitSet FOLLOW_any_in_atstatement363 = new BitSet(new long[]{0x5DFFFF2600200000L});
    public static final BitSet FOLLOW_RCURLY_in_atstatement366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_media389 = new BitSet(new long[]{0x0000010020000002L});
    public static final BitSet FOLLOW_S_in_media391 = new BitSet(new long[]{0x0000010020000002L});
    public static final BitSet FOLLOW_COMMA_in_media395 = new BitSet(new long[]{0x0000000420000000L});
    public static final BitSet FOLLOW_S_in_media397 = new BitSet(new long[]{0x0000000420000000L});
    public static final BitSet FOLLOW_IDENT_in_media400 = new BitSet(new long[]{0x0000010020000002L});
    public static final BitSet FOLLOW_S_in_media402 = new BitSet(new long[]{0x0000010020000002L});
    public static final BitSet FOLLOW_combined_selector_in_ruleset432 = new BitSet(new long[]{0x0000010800000000L});
    public static final BitSet FOLLOW_COMMA_in_ruleset435 = new BitSet(new long[]{0x1081042620800000L});
    public static final BitSet FOLLOW_S_in_ruleset437 = new BitSet(new long[]{0x1081042620800000L});
    public static final BitSet FOLLOW_combined_selector_in_ruleset440 = new BitSet(new long[]{0x0000010800000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleset448 = new BitSet(new long[]{0x0000003420000000L});
    public static final BitSet FOLLOW_S_in_ruleset450 = new BitSet(new long[]{0x0000003420000000L});
    public static final BitSet FOLLOW_declaration_in_ruleset458 = new BitSet(new long[]{0x0000003000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_ruleset462 = new BitSet(new long[]{0x0000003420000000L});
    public static final BitSet FOLLOW_S_in_ruleset464 = new BitSet(new long[]{0x0000003420000000L});
    public static final BitSet FOLLOW_declaration_in_ruleset467 = new BitSet(new long[]{0x0000003000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleset477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_property_in_declaration503 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_COLON_in_declaration505 = new BitSet(new long[]{0x5DFFFD8E20200000L});
    public static final BitSet FOLLOW_S_in_declaration507 = new BitSet(new long[]{0x5DFFFD8E20200000L});
    public static final BitSet FOLLOW_terms_in_declaration510 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_important_in_declaration512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_important547 = new BitSet(new long[]{0x0000000020000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_S_in_important549 = new BitSet(new long[]{0x0000000020000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_82_in_important552 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_important554 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_IDENT_in_property579 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_property581 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_term_in_terms606 = new BitSet(new long[]{0x5DFFFD8E20200002L});
    public static final BitSet FOLLOW_valuepart_in_term632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_term644 = new BitSet(new long[]{0x5DFFFF3620200000L});
    public static final BitSet FOLLOW_S_in_term646 = new BitSet(new long[]{0x5DFFFF3620200000L});
    public static final BitSet FOLLOW_any_in_term650 = new BitSet(new long[]{0x5DFFFF3600200000L});
    public static final BitSet FOLLOW_SEMICOLON_in_term654 = new BitSet(new long[]{0x5DFFFF3620200000L});
    public static final BitSet FOLLOW_S_in_term656 = new BitSet(new long[]{0x5DFFFF3620200000L});
    public static final BitSet FOLLOW_RCURLY_in_term661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATKEYWORD_in_term673 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_term675 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_IDENT_in_valuepart700 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_valuepart714 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart728 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_NUMBER_in_valuepart731 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart748 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_PERCENTAGE_in_valuepart751 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart768 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_DIMENSION_in_valuepart771 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_string_in_valuepart788 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_URI_in_valuepart802 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_HASH_in_valuepart819 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_valuepart833 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_INCLUDES_in_valuepart847 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_COLON_in_valuepart861 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_COMMA_in_valuepart875 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_GREATER_in_valuepart889 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_EQUALS_in_valuepart903 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_SLASH_in_valuepart917 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_PLUS_in_valuepart928 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_ASTERISK_in_valuepart939 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_FUNCTION_in_valuepart956 = new BitSet(new long[]{0x5FFFFD8E20200000L});
    public static final BitSet FOLLOW_S_in_valuepart958 = new BitSet(new long[]{0x5FFFFD8E20200000L});
    public static final BitSet FOLLOW_terms_in_valuepart961 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_valuepart963 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_valuepart982 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_LPAREN_in_valuepart996 = new BitSet(new long[]{0x5FFFFD0600200000L});
    public static final BitSet FOLLOW_valuepart_in_valuepart998 = new BitSet(new long[]{0x5FFFFD0600200000L});
    public static final BitSet FOLLOW_RPAREN_in_valuepart1001 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_LBRACE_in_valuepart1020 = new BitSet(new long[]{0x7FFFFD0600200000L});
    public static final BitSet FOLLOW_valuepart_in_valuepart1022 = new BitSet(new long[]{0x7FFFFD0600200000L});
    public static final BitSet FOLLOW_RBRACE_in_valuepart1025 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_valuepart1043 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_selector_in_combined_selector1060 = new BitSet(new long[]{0x0048000020000002L});
    public static final BitSet FOLLOW_combinator_in_combined_selector1064 = new BitSet(new long[]{0x1081042620800000L});
    public static final BitSet FOLLOW_selector_in_combined_selector1067 = new BitSet(new long[]{0x0048000020000002L});
    public static final BitSet FOLLOW_GREATER_in_combinator1087 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_combinator1089 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_PLUS_in_combinator1099 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_combinator1101 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_combinator1111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_selector1130 = new BitSet(new long[]{0x1081042620800002L});
    public static final BitSet FOLLOW_ASTERISK_in_selector1134 = new BitSet(new long[]{0x1081042620800002L});
    public static final BitSet FOLLOW_selpart_in_selector1138 = new BitSet(new long[]{0x1081042620800002L});
    public static final BitSet FOLLOW_S_in_selector1141 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_selpart_in_selector1171 = new BitSet(new long[]{0x1081042620800002L});
    public static final BitSet FOLLOW_S_in_selector1174 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_COLON_in_selpart1220 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_IDENT_in_selpart1222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASH_in_selpart1236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_selpart1244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_selpart1249 = new BitSet(new long[]{0x0000000420000000L});
    public static final BitSet FOLLOW_S_in_selpart1251 = new BitSet(new long[]{0x0000000420000000L});
    public static final BitSet FOLLOW_attribute_in_selpart1254 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_RBRACE_in_selpart1256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_selpart1272 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_FUNCTION_in_selpart1274 = new BitSet(new long[]{0x0000000420000000L});
    public static final BitSet FOLLOW_S_in_selpart1276 = new BitSet(new long[]{0x0000000420000000L});
    public static final BitSet FOLLOW_IDENT_in_selpart1279 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_selpart1281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_SELPART_in_selpart1297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_attribute1323 = new BitSet(new long[]{0x0414000020000002L});
    public static final BitSet FOLLOW_S_in_attribute1325 = new BitSet(new long[]{0x0414000020000002L});
    public static final BitSet FOLLOW_set_in_attribute1332 = new BitSet(new long[]{0x4000000420200000L});
    public static final BitSet FOLLOW_S_in_attribute1344 = new BitSet(new long[]{0x4000000420200000L});
    public static final BitSet FOLLOW_IDENT_in_attribute1348 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_string_in_attribute1352 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_attribute1355 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_set_in_string0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_any1390 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_any1401 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_NUMBER_in_any1412 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_PERCENTAGE_in_any1423 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_DIMENSION_in_any1433 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_string_in_any1444 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_URI_in_any1458 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_HASH_in_any1475 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_any1489 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_INCLUDES_in_any1503 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_COLON_in_any1517 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_COMMA_in_any1531 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_GREATER_in_any1545 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_EQUALS_in_any1559 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_SLASH_in_any1573 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_any1587 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_MINUS_in_any1598 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_PLUS_in_any1609 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_ASTERISK_in_any1620 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_FUNCTION_in_any1637 = new BitSet(new long[]{0x5FFFFF2620200000L});
    public static final BitSet FOLLOW_S_in_any1639 = new BitSet(new long[]{0x5FFFFF2620200000L});
    public static final BitSet FOLLOW_any_in_any1642 = new BitSet(new long[]{0x5FFFFF2600200000L});
    public static final BitSet FOLLOW_RPAREN_in_any1645 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_any1665 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_LPAREN_in_any1679 = new BitSet(new long[]{0x5FFFFF2600200000L});
    public static final BitSet FOLLOW_any_in_any1681 = new BitSet(new long[]{0x5FFFFF2600200000L});
    public static final BitSet FOLLOW_RPAREN_in_any1684 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_LBRACE_in_any1703 = new BitSet(new long[]{0x7FFFFF2600200000L});
    public static final BitSet FOLLOW_any_in_any1705 = new BitSet(new long[]{0x7FFFFF2600200000L});
    public static final BitSet FOLLOW_RBRACE_in_any1708 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_any1726 = new BitSet(new long[]{0x0000000020000002L});

}