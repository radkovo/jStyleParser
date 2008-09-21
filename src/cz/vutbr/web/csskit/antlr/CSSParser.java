// $ANTLR 3.1 CSS.g 2008-09-21 13:33:09
 
package cz.vutbr.web.csskit.antlr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.SupportedCSS;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

@SuppressWarnings("unused")
public class CSSParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "STYLESHEET", "INLINESTYLE", "ATBLOCK", "CURLYBLOCK", "PARENBLOCK", "BRACEBLOCK", "RULE", "SELECTOR", "ELEMENT", "PSEUDO", "ADJACENT", "CHILD", "DESCENDANT", "ATTRIBUTE", "SET", "DECLARATION", "VALUE", "IMPORTANT", "IMPORT_END", "INVALID_STRING", "INVALID_SELECTOR", "INVALID_SELPART", "INVALID_DECLARATION", "INVALID_STATEMENT", "INVALID_IMPORT", "S", "CDO", "CDC", "CHARSET", "IMPORT", "PAGE", "COLON", "IDENT", "LCURLY", "RCURLY", "MEDIA", "ATKEYWORD", "COMMA", "SEMICOLON", "EXCLAMATION", "CLASSKEYWORD", "MINUS", "NUMBER", "PERCENTAGE", "DIMENSION", "URI", "HASH", "UNIRANGE", "INCLUDES", "GREATER", "EQUALS", "SLASH", "PLUS", "ASTERISK", "FUNCTION", "RPAREN", "DASHMATCH", "LPAREN", "LBRACE", "RBRACE", "STRING", "IDENT_MACR", "STRING_MACR", "NAME_MACR", "NUMBER_MACR", "W_MACR", "URI_MACR", "APOS", "QUOT", "W_CHAR", "COMMENT", "SL_COMMENT", "INVALID_TOKEN", "NAME_START", "NAME_CHAR", "NON_ASCII", "ESCAPE_CHAR", "STRING_CHAR", "URI_CHAR", "NL_CHAR", "'important'"
    };
    public static final int COMMA=41;
    public static final int ELEMENT=12;
    public static final int INVALID_IMPORT=28;
    public static final int CHARSET=32;
    public static final int MINUS=45;
    public static final int BRACEBLOCK=9;
    public static final int HASH=50;
    public static final int PARENBLOCK=8;
    public static final int DASHMATCH=60;
    public static final int NUMBER=46;
    public static final int SELECTOR=11;
    public static final int IMPORT_END=22;
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
    public static final int LPAREN=61;
    public static final int GREATER=53;
    public static final int W_CHAR=73;
    public static final int NON_ASCII=79;
    public static final int PLUS=56;
    public static final int SL_COMMENT=75;
    public static final int APOS=71;
    public static final int ATKEYWORD=40;
    public static final int STRING_CHAR=81;
    public static final int URI_MACR=70;
    public static final int IDENT=36;
    public static final int SLASH=55;
    public static final int UNIRANGE=51;
    public static final int IMPORTANT=21;
    public static final int STRING=64;
    public static final int EXCLAMATION=43;
    public static final int NL_CHAR=83;
    public static final int INVALID_STRING=23;
    public static final int COMMENT=74;
    public static final int CDC=31;
    public static final int ADJACENT=14;
    public static final int NUMBER_MACR=68;
    public static final int INVALID_DECLARATION=26;
    public static final int EQUALS=54;
    public static final int T__84=84;
    public static final int CURLYBLOCK=7;
    public static final int DIMENSION=48;
    public static final int INLINESTYLE=5;
    public static final int INVALID_STATEMENT=27;
    public static final int STRING_MACR=66;
    public static final int SEMICOLON=42;
    public static final int EOF=-1;
    public static final int ASTERISK=57;
    public static final int COLON=35;
    public static final int CDO=30;
    public static final int SET=18;
    public static final int ATTRIBUTE=17;
    public static final int CHILD=15;
    public static final int PERCENTAGE=47;
    public static final int INVALID_SELPART=25;
    public static final int QUOT=72;
    public static final int ATBLOCK=6;

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



    public static class inlinestyle_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "inlinestyle"
    // CSS.g:493:1: inlinestyle : ( S )* ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) ) ;
    public final CSSParser.inlinestyle_return inlinestyle() throws RecognitionException {
        CSSParser.inlinestyle_return retval = new CSSParser.inlinestyle_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token S1=null;
        CSSParser.declarations_return declarations2 = null;

        CSSParser.inlineset_return inlineset3 = null;


        Object S1_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleSubtreeStream stream_inlineset=new RewriteRuleSubtreeStream(adaptor,"rule inlineset");
        RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");
        try {
            // CSS.g:494:2: ( ( S )* ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) ) )
            // CSS.g:494:4: ( S )* ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) )
            {
            // CSS.g:494:4: ( S )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==S) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // CSS.g:494:4: S
            	    {
            	    S1=(Token)match(input,S,FOLLOW_S_in_inlinestyle186);  
            	    stream_S.add(S1);


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // CSS.g:494:8: ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==EOF||LA3_0==IDENT||LA3_0==SEMICOLON) ) {
                alt3=1;
            }
            else if ( (LA3_0==COLON||LA3_0==LCURLY) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // CSS.g:494:9: declarations
                    {
                    pushFollow(FOLLOW_declarations_in_inlinestyle191);
                    declarations2=declarations();

                    state._fsp--;

                    stream_declarations.add(declarations2.getTree());


                    // AST REWRITE
                    // elements: declarations
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 494:22: -> ^( INLINESTYLE declarations )
                    {
                        // CSS.g:494:25: ^( INLINESTYLE declarations )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(INLINESTYLE, "INLINESTYLE"), root_1);

                        adaptor.addChild(root_1, stream_declarations.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // CSS.g:495:10: ( inlineset )+
                    {
                    // CSS.g:495:10: ( inlineset )+
                    int cnt2=0;
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==COLON||LA2_0==LCURLY) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // CSS.g:495:10: inlineset
                    	    {
                    	    pushFollow(FOLLOW_inlineset_in_inlinestyle211);
                    	    inlineset3=inlineset();

                    	    state._fsp--;

                    	    stream_inlineset.add(inlineset3.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt2 >= 1 ) break loop2;
                                EarlyExitException eee =
                                    new EarlyExitException(2, input);
                                throw eee;
                        }
                        cnt2++;
                    } while (true);



                    // AST REWRITE
                    // elements: inlineset
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 495:21: -> ^( INLINESTYLE ( inlineset )+ )
                    {
                        // CSS.g:495:24: ^( INLINESTYLE ( inlineset )+ )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(INLINESTYLE, "INLINESTYLE"), root_1);

                        if ( !(stream_inlineset.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_inlineset.hasNext() ) {
                            adaptor.addChild(root_1, stream_inlineset.nextTree());

                        }
                        stream_inlineset.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
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
    // $ANTLR end "inlinestyle"

    public static class stylesheet_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "stylesheet"
    // CSS.g:499:1: stylesheet : ( CDO | CDC | S | statement )* -> ^( STYLESHEET ( statement )* ) ;
    public final CSSParser.stylesheet_return stylesheet() throws RecognitionException {
        CSSParser.stylesheet_return retval = new CSSParser.stylesheet_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CDO4=null;
        Token CDC5=null;
        Token S6=null;
        CSSParser.statement_return statement7 = null;


        Object CDO4_tree=null;
        Object CDC5_tree=null;
        Object S6_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_CDO=new RewriteRuleTokenStream(adaptor,"token CDO");
        RewriteRuleTokenStream stream_CDC=new RewriteRuleTokenStream(adaptor,"token CDC");
        RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
        try {
            // CSS.g:500:2: ( ( CDO | CDC | S | statement )* -> ^( STYLESHEET ( statement )* ) )
            // CSS.g:500:4: ( CDO | CDC | S | statement )*
            {
            // CSS.g:500:4: ( CDO | CDC | S | statement )*
            loop4:
            do {
                int alt4=5;
                alt4 = dfa4.predict(input);
                switch (alt4) {
            	case 1 :
            	    // CSS.g:500:6: CDO
            	    {
            	    CDO4=(Token)match(input,CDO,FOLLOW_CDO_in_stylesheet239);  
            	    stream_CDO.add(CDO4);


            	    }
            	    break;
            	case 2 :
            	    // CSS.g:500:12: CDC
            	    {
            	    CDC5=(Token)match(input,CDC,FOLLOW_CDC_in_stylesheet243);  
            	    stream_CDC.add(CDC5);


            	    }
            	    break;
            	case 3 :
            	    // CSS.g:500:18: S
            	    {
            	    S6=(Token)match(input,S,FOLLOW_S_in_stylesheet247);  
            	    stream_S.add(S6);


            	    }
            	    break;
            	case 4 :
            	    // CSS.g:500:22: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_stylesheet251);
            	    statement7=statement();

            	    state._fsp--;

            	    stream_statement.add(statement7.getTree());

            	    }
            	    break;

            	default :
            	    break loop4;
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
            // 501:3: -> ^( STYLESHEET ( statement )* )
            {
                // CSS.g:501:6: ^( STYLESHEET ( statement )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(STYLESHEET, "STYLESHEET"), root_1);

                // CSS.g:501:19: ( statement )*
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
    // CSS.g:504:1: statement : ( ruleset | atstatement );
    public final CSSParser.statement_return statement() throws RecognitionException {
        CSSParser.statement_return retval = new CSSParser.statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CSSParser.ruleset_return ruleset8 = null;

        CSSParser.atstatement_return atstatement9 = null;



        try {
            // CSS.g:505:2: ( ruleset | atstatement )
            int alt5=2;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // CSS.g:505:4: ruleset
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ruleset_in_statement281);
                    ruleset8=ruleset();

                    state._fsp--;

                    adaptor.addChild(root_0, ruleset8.getTree());

                    }
                    break;
                case 2 :
                    // CSS.g:505:14: atstatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_atstatement_in_statement285);
                    atstatement9=atstatement();

                    state._fsp--;

                    adaptor.addChild(root_0, atstatement9.getTree());

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
    // CSS.g:508:1: atstatement : ( CHARSET | IMPORT | INVALID_IMPORT | IMPORT_END | PAGE ( S )* ( COLON IDENT ( S )* )? LCURLY ( S )* declarations RCURLY -> ^( PAGE ( IDENT )? declarations ) | MEDIA ( S )* ( media )? LCURLY ( S )* ( ruleset ( S )* )* RCURLY -> ^( MEDIA ( media )? ( ruleset )* ) | ATKEYWORD ( S )* LCURLY ( any )* RCURLY -> INVALID_STATEMENT );
    public final CSSParser.atstatement_return atstatement() throws RecognitionException {
        CSSParser.atstatement_return retval = new CSSParser.atstatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CHARSET10=null;
        Token IMPORT11=null;
        Token INVALID_IMPORT12=null;
        Token IMPORT_END13=null;
        Token PAGE14=null;
        Token S15=null;
        Token COLON16=null;
        Token IDENT17=null;
        Token S18=null;
        Token LCURLY19=null;
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
        CSSParser.declarations_return declarations21 = null;

        CSSParser.media_return media25 = null;

        CSSParser.ruleset_return ruleset28 = null;

        CSSParser.any_return any34 = null;


        Object CHARSET10_tree=null;
        Object IMPORT11_tree=null;
        Object INVALID_IMPORT12_tree=null;
        Object IMPORT_END13_tree=null;
        Object PAGE14_tree=null;
        Object S15_tree=null;
        Object COLON16_tree=null;
        Object IDENT17_tree=null;
        Object S18_tree=null;
        Object LCURLY19_tree=null;
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
        RewriteRuleTokenStream stream_PAGE=new RewriteRuleTokenStream(adaptor,"token PAGE");
        RewriteRuleSubtreeStream stream_ruleset=new RewriteRuleSubtreeStream(adaptor,"rule ruleset");
        RewriteRuleSubtreeStream stream_media=new RewriteRuleSubtreeStream(adaptor,"rule media");
        RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");
        RewriteRuleSubtreeStream stream_any=new RewriteRuleSubtreeStream(adaptor,"rule any");
        try {
            // CSS.g:509:2: ( CHARSET | IMPORT | INVALID_IMPORT | IMPORT_END | PAGE ( S )* ( COLON IDENT ( S )* )? LCURLY ( S )* declarations RCURLY -> ^( PAGE ( IDENT )? declarations ) | MEDIA ( S )* ( media )? LCURLY ( S )* ( ruleset ( S )* )* RCURLY -> ^( MEDIA ( media )? ( ruleset )* ) | ATKEYWORD ( S )* LCURLY ( any )* RCURLY -> INVALID_STATEMENT )
            int alt17=7;
            switch ( input.LA(1) ) {
            case CHARSET:
                {
                alt17=1;
                }
                break;
            case IMPORT:
                {
                alt17=2;
                }
                break;
            case INVALID_IMPORT:
                {
                alt17=3;
                }
                break;
            case IMPORT_END:
                {
                alt17=4;
                }
                break;
            case PAGE:
                {
                alt17=5;
                }
                break;
            case MEDIA:
                {
                alt17=6;
                }
                break;
            case ATKEYWORD:
                {
                alt17=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // CSS.g:509:4: CHARSET
                    {
                    root_0 = (Object)adaptor.nil();

                    CHARSET10=(Token)match(input,CHARSET,FOLLOW_CHARSET_in_atstatement296); 
                    CHARSET10_tree = (Object)adaptor.create(CHARSET10);
                    adaptor.addChild(root_0, CHARSET10_tree);


                    }
                    break;
                case 2 :
                    // CSS.g:510:4: IMPORT
                    {
                    root_0 = (Object)adaptor.nil();

                    IMPORT11=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_atstatement301); 
                    IMPORT11_tree = (Object)adaptor.create(IMPORT11);
                    adaptor.addChild(root_0, IMPORT11_tree);


                    }
                    break;
                case 3 :
                    // CSS.g:511:4: INVALID_IMPORT
                    {
                    root_0 = (Object)adaptor.nil();

                    INVALID_IMPORT12=(Token)match(input,INVALID_IMPORT,FOLLOW_INVALID_IMPORT_in_atstatement306); 
                    INVALID_IMPORT12_tree = (Object)adaptor.create(INVALID_IMPORT12);
                    adaptor.addChild(root_0, INVALID_IMPORT12_tree);


                    }
                    break;
                case 4 :
                    // CSS.g:512:4: IMPORT_END
                    {
                    root_0 = (Object)adaptor.nil();

                    IMPORT_END13=(Token)match(input,IMPORT_END,FOLLOW_IMPORT_END_in_atstatement311); 
                    IMPORT_END13_tree = (Object)adaptor.create(IMPORT_END13);
                    adaptor.addChild(root_0, IMPORT_END13_tree);


                    }
                    break;
                case 5 :
                    // CSS.g:513:4: PAGE ( S )* ( COLON IDENT ( S )* )? LCURLY ( S )* declarations RCURLY
                    {
                    PAGE14=(Token)match(input,PAGE,FOLLOW_PAGE_in_atstatement316);  
                    stream_PAGE.add(PAGE14);

                    // CSS.g:513:9: ( S )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==S) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // CSS.g:513:9: S
                    	    {
                    	    S15=(Token)match(input,S,FOLLOW_S_in_atstatement318);  
                    	    stream_S.add(S15);


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    // CSS.g:513:12: ( COLON IDENT ( S )* )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==COLON) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // CSS.g:513:13: COLON IDENT ( S )*
                            {
                            COLON16=(Token)match(input,COLON,FOLLOW_COLON_in_atstatement322);  
                            stream_COLON.add(COLON16);

                            IDENT17=(Token)match(input,IDENT,FOLLOW_IDENT_in_atstatement324);  
                            stream_IDENT.add(IDENT17);

                            // CSS.g:513:25: ( S )*
                            loop7:
                            do {
                                int alt7=2;
                                int LA7_0 = input.LA(1);

                                if ( (LA7_0==S) ) {
                                    alt7=1;
                                }


                                switch (alt7) {
                            	case 1 :
                            	    // CSS.g:513:25: S
                            	    {
                            	    S18=(Token)match(input,S,FOLLOW_S_in_atstatement326);  
                            	    stream_S.add(S18);


                            	    }
                            	    break;

                            	default :
                            	    break loop7;
                                }
                            } while (true);


                            }
                            break;

                    }

                    LCURLY19=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atstatement334);  
                    stream_LCURLY.add(LCURLY19);

                    // CSS.g:514:10: ( S )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==S) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // CSS.g:514:10: S
                    	    {
                    	    S20=(Token)match(input,S,FOLLOW_S_in_atstatement336);  
                    	    stream_S.add(S20);


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);

                    pushFollow(FOLLOW_declarations_in_atstatement339);
                    declarations21=declarations();

                    state._fsp--;

                    stream_declarations.add(declarations21.getTree());
                    RCURLY22=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atstatement344);  
                    stream_RCURLY.add(RCURLY22);



                    // AST REWRITE
                    // elements: PAGE, declarations, IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 515:10: -> ^( PAGE ( IDENT )? declarations )
                    {
                        // CSS.g:515:13: ^( PAGE ( IDENT )? declarations )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_PAGE.nextNode(), root_1);

                        // CSS.g:515:20: ( IDENT )?
                        if ( stream_IDENT.hasNext() ) {
                            adaptor.addChild(root_1, stream_IDENT.nextNode());

                        }
                        stream_IDENT.reset();
                        adaptor.addChild(root_1, stream_declarations.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // CSS.g:516:4: MEDIA ( S )* ( media )? LCURLY ( S )* ( ruleset ( S )* )* RCURLY
                    {
                    MEDIA23=(Token)match(input,MEDIA,FOLLOW_MEDIA_in_atstatement360);  
                    stream_MEDIA.add(MEDIA23);

                    // CSS.g:516:10: ( S )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==S) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // CSS.g:516:10: S
                    	    {
                    	    S24=(Token)match(input,S,FOLLOW_S_in_atstatement362);  
                    	    stream_S.add(S24);


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    // CSS.g:516:13: ( media )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==IDENT) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // CSS.g:516:13: media
                            {
                            pushFollow(FOLLOW_media_in_atstatement365);
                            media25=media();

                            state._fsp--;

                            stream_media.add(media25.getTree());

                            }
                            break;

                    }

                    LCURLY26=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atstatement371);  
                    stream_LCURLY.add(LCURLY26);

                    // CSS.g:517:10: ( S )*
                    loop12:
                    do {
                        int alt12=2;
                        alt12 = dfa12.predict(input);
                        switch (alt12) {
                    	case 1 :
                    	    // CSS.g:517:10: S
                    	    {
                    	    S27=(Token)match(input,S,FOLLOW_S_in_atstatement373);  
                    	    stream_S.add(S27);


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    // CSS.g:517:13: ( ruleset ( S )* )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==INVALID_SELPART||(LA14_0>=COLON && LA14_0<=IDENT)||LA14_0==CLASSKEYWORD||LA14_0==HASH||LA14_0==ASTERISK||LA14_0==LBRACE) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // CSS.g:517:14: ruleset ( S )*
                    	    {
                    	    pushFollow(FOLLOW_ruleset_in_atstatement377);
                    	    ruleset28=ruleset();

                    	    state._fsp--;

                    	    stream_ruleset.add(ruleset28.getTree());
                    	    // CSS.g:517:22: ( S )*
                    	    loop13:
                    	    do {
                    	        int alt13=2;
                    	        alt13 = dfa13.predict(input);
                    	        switch (alt13) {
                    	    	case 1 :
                    	    	    // CSS.g:517:22: S
                    	    	    {
                    	    	    S29=(Token)match(input,S,FOLLOW_S_in_atstatement379);  
                    	    	    stream_S.add(S29);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop13;
                    	        }
                    	    } while (true);


                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);

                    RCURLY30=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atstatement384);  
                    stream_RCURLY.add(RCURLY30);



                    // AST REWRITE
                    // elements: MEDIA, media, ruleset
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 517:34: -> ^( MEDIA ( media )? ( ruleset )* )
                    {
                        // CSS.g:517:37: ^( MEDIA ( media )? ( ruleset )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_MEDIA.nextNode(), root_1);

                        // CSS.g:517:45: ( media )?
                        if ( stream_media.hasNext() ) {
                            adaptor.addChild(root_1, stream_media.nextTree());

                        }
                        stream_media.reset();
                        // CSS.g:517:52: ( ruleset )*
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
                    // CSS.g:518:4: ATKEYWORD ( S )* LCURLY ( any )* RCURLY
                    {
                    ATKEYWORD31=(Token)match(input,ATKEYWORD,FOLLOW_ATKEYWORD_in_atstatement402);  
                    stream_ATKEYWORD.add(ATKEYWORD31);

                    // CSS.g:518:14: ( S )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==S) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // CSS.g:518:14: S
                    	    {
                    	    S32=(Token)match(input,S,FOLLOW_S_in_atstatement404);  
                    	    stream_S.add(S32);


                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);

                    LCURLY33=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atstatement407);  
                    stream_LCURLY.add(LCURLY33);

                    // CSS.g:518:24: ( any )*
                    loop16:
                    do {
                        int alt16=2;
                        alt16 = dfa16.predict(input);
                        switch (alt16) {
                    	case 1 :
                    	    // CSS.g:518:24: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_atstatement409);
                    	    any34=any();

                    	    state._fsp--;

                    	    stream_any.add(any34.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);

                    RCURLY35=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atstatement412);  
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
                    // 518:36: -> INVALID_STATEMENT
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

    public static class inlineset_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "inlineset"
    // CSS.g:526:1: inlineset : ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )? LCURLY declarations RCURLY -> ^( RULE ( pseudo )* declarations ) ;
    public final CSSParser.inlineset_return inlineset() throws RecognitionException {
        CSSParser.inlineset_return retval = new CSSParser.inlineset_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token S37=null;
        Token COMMA38=null;
        Token S39=null;
        Token S41=null;
        Token LCURLY42=null;
        Token RCURLY44=null;
        CSSParser.pseudo_return pseudo36 = null;

        CSSParser.pseudo_return pseudo40 = null;

        CSSParser.declarations_return declarations43 = null;


        Object S37_tree=null;
        Object COMMA38_tree=null;
        Object S39_tree=null;
        Object S41_tree=null;
        Object LCURLY42_tree=null;
        Object RCURLY44_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleSubtreeStream stream_pseudo=new RewriteRuleSubtreeStream(adaptor,"rule pseudo");
        RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");
        try {
            // CSS.g:527:2: ( ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )? LCURLY declarations RCURLY -> ^( RULE ( pseudo )* declarations ) )
            // CSS.g:527:4: ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )? LCURLY declarations RCURLY
            {
            // CSS.g:527:4: ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==COLON) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // CSS.g:527:5: pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )*
                    {
                    pushFollow(FOLLOW_pseudo_in_inlineset435);
                    pseudo36=pseudo();

                    state._fsp--;

                    stream_pseudo.add(pseudo36.getTree());
                    // CSS.g:527:12: ( S )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==S) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // CSS.g:527:12: S
                    	    {
                    	    S37=(Token)match(input,S,FOLLOW_S_in_inlineset437);  
                    	    stream_S.add(S37);


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);

                    // CSS.g:527:15: ( COMMA ( S )* pseudo ( S )* )*
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==COMMA) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // CSS.g:527:16: COMMA ( S )* pseudo ( S )*
                    	    {
                    	    COMMA38=(Token)match(input,COMMA,FOLLOW_COMMA_in_inlineset441);  
                    	    stream_COMMA.add(COMMA38);

                    	    // CSS.g:527:22: ( S )*
                    	    loop19:
                    	    do {
                    	        int alt19=2;
                    	        int LA19_0 = input.LA(1);

                    	        if ( (LA19_0==S) ) {
                    	            alt19=1;
                    	        }


                    	        switch (alt19) {
                    	    	case 1 :
                    	    	    // CSS.g:527:22: S
                    	    	    {
                    	    	    S39=(Token)match(input,S,FOLLOW_S_in_inlineset443);  
                    	    	    stream_S.add(S39);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop19;
                    	        }
                    	    } while (true);

                    	    pushFollow(FOLLOW_pseudo_in_inlineset446);
                    	    pseudo40=pseudo();

                    	    state._fsp--;

                    	    stream_pseudo.add(pseudo40.getTree());
                    	    // CSS.g:527:32: ( S )*
                    	    loop20:
                    	    do {
                    	        int alt20=2;
                    	        int LA20_0 = input.LA(1);

                    	        if ( (LA20_0==S) ) {
                    	            alt20=1;
                    	        }


                    	        switch (alt20) {
                    	    	case 1 :
                    	    	    // CSS.g:527:32: S
                    	    	    {
                    	    	    S41=(Token)match(input,S,FOLLOW_S_in_inlineset448);  
                    	    	    stream_S.add(S41);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop20;
                    	        }
                    	    } while (true);


                    	    }
                    	    break;

                    	default :
                    	    break loop21;
                        }
                    } while (true);


                    }
                    break;

            }

            LCURLY42=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_inlineset461);  
            stream_LCURLY.add(LCURLY42);

            pushFollow(FOLLOW_declarations_in_inlineset467);
            declarations43=declarations();

            state._fsp--;

            stream_declarations.add(declarations43.getTree());
            RCURLY44=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_inlineset472);  
            stream_RCURLY.add(RCURLY44);



            // AST REWRITE
            // elements: declarations, pseudo
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 531:4: -> ^( RULE ( pseudo )* declarations )
            {
                // CSS.g:531:7: ^( RULE ( pseudo )* declarations )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(RULE, "RULE"), root_1);

                // CSS.g:531:14: ( pseudo )*
                while ( stream_pseudo.hasNext() ) {
                    adaptor.addChild(root_1, stream_pseudo.nextTree());

                }
                stream_pseudo.reset();
                adaptor.addChild(root_1, stream_declarations.nextTree());

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
    // $ANTLR end "inlineset"

    public static class media_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "media"
    // CSS.g:534:1: media : IDENT ( S )* ( COMMA ( S )* IDENT ( S )* )* -> ( IDENT )+ ;
    public final CSSParser.media_return media() throws RecognitionException {
        CSSParser.media_return retval = new CSSParser.media_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT45=null;
        Token S46=null;
        Token COMMA47=null;
        Token S48=null;
        Token IDENT49=null;
        Token S50=null;

        Object IDENT45_tree=null;
        Object S46_tree=null;
        Object COMMA47_tree=null;
        Object S48_tree=null;
        Object IDENT49_tree=null;
        Object S50_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");

        try {
            // CSS.g:535:2: ( IDENT ( S )* ( COMMA ( S )* IDENT ( S )* )* -> ( IDENT )+ )
            // CSS.g:535:4: IDENT ( S )* ( COMMA ( S )* IDENT ( S )* )*
            {
            IDENT45=(Token)match(input,IDENT,FOLLOW_IDENT_in_media499);  
            stream_IDENT.add(IDENT45);

            // CSS.g:535:10: ( S )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==S) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // CSS.g:535:10: S
            	    {
            	    S46=(Token)match(input,S,FOLLOW_S_in_media501);  
            	    stream_S.add(S46);


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            // CSS.g:535:13: ( COMMA ( S )* IDENT ( S )* )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==COMMA) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // CSS.g:535:14: COMMA ( S )* IDENT ( S )*
            	    {
            	    COMMA47=(Token)match(input,COMMA,FOLLOW_COMMA_in_media505);  
            	    stream_COMMA.add(COMMA47);

            	    // CSS.g:535:20: ( S )*
            	    loop24:
            	    do {
            	        int alt24=2;
            	        int LA24_0 = input.LA(1);

            	        if ( (LA24_0==S) ) {
            	            alt24=1;
            	        }


            	        switch (alt24) {
            	    	case 1 :
            	    	    // CSS.g:535:20: S
            	    	    {
            	    	    S48=(Token)match(input,S,FOLLOW_S_in_media507);  
            	    	    stream_S.add(S48);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop24;
            	        }
            	    } while (true);

            	    IDENT49=(Token)match(input,IDENT,FOLLOW_IDENT_in_media510);  
            	    stream_IDENT.add(IDENT49);

            	    // CSS.g:535:29: ( S )*
            	    loop25:
            	    do {
            	        int alt25=2;
            	        int LA25_0 = input.LA(1);

            	        if ( (LA25_0==S) ) {
            	            alt25=1;
            	        }


            	        switch (alt25) {
            	    	case 1 :
            	    	    // CSS.g:535:29: S
            	    	    {
            	    	    S50=(Token)match(input,S,FOLLOW_S_in_media512);  
            	    	    stream_S.add(S50);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop25;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop26;
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
            // 536:3: -> ( IDENT )+
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
    // CSS.g:539:1: ruleset : combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY -> ^( RULE ( combined_selector )+ declarations ) ;
    public final CSSParser.ruleset_return ruleset() throws RecognitionException {
        CSSParser.ruleset_return retval = new CSSParser.ruleset_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA52=null;
        Token S53=null;
        Token LCURLY55=null;
        Token S56=null;
        Token RCURLY58=null;
        CSSParser.combined_selector_return combined_selector51 = null;

        CSSParser.combined_selector_return combined_selector54 = null;

        CSSParser.declarations_return declarations57 = null;


        Object COMMA52_tree=null;
        Object S53_tree=null;
        Object LCURLY55_tree=null;
        Object S56_tree=null;
        Object RCURLY58_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");
        RewriteRuleSubtreeStream stream_combined_selector=new RewriteRuleSubtreeStream(adaptor,"rule combined_selector");
        try {
            // CSS.g:542:2: ( combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY -> ^( RULE ( combined_selector )+ declarations ) )
            // CSS.g:542:4: combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY
            {
            pushFollow(FOLLOW_combined_selector_in_ruleset542);
            combined_selector51=combined_selector();

            state._fsp--;

            stream_combined_selector.add(combined_selector51.getTree());
            // CSS.g:542:22: ( COMMA ( S )* combined_selector )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==COMMA) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // CSS.g:542:23: COMMA ( S )* combined_selector
            	    {
            	    COMMA52=(Token)match(input,COMMA,FOLLOW_COMMA_in_ruleset545);  
            	    stream_COMMA.add(COMMA52);

            	    // CSS.g:542:29: ( S )*
            	    loop27:
            	    do {
            	        int alt27=2;
            	        int LA27_0 = input.LA(1);

            	        if ( (LA27_0==S) ) {
            	            alt27=1;
            	        }


            	        switch (alt27) {
            	    	case 1 :
            	    	    // CSS.g:542:29: S
            	    	    {
            	    	    S53=(Token)match(input,S,FOLLOW_S_in_ruleset547);  
            	    	    stream_S.add(S53);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop27;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_combined_selector_in_ruleset550);
            	    combined_selector54=combined_selector();

            	    state._fsp--;

            	    stream_combined_selector.add(combined_selector54.getTree());

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

            LCURLY55=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_ruleset558);  
            stream_LCURLY.add(LCURLY55);

            // CSS.g:543:11: ( S )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==S) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // CSS.g:543:11: S
            	    {
            	    S56=(Token)match(input,S,FOLLOW_S_in_ruleset560);  
            	    stream_S.add(S56);


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);

            pushFollow(FOLLOW_declarations_in_ruleset568);
            declarations57=declarations();

            state._fsp--;

            stream_declarations.add(declarations57.getTree());
            RCURLY58=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_ruleset573);  
            stream_RCURLY.add(RCURLY58);



            // AST REWRITE
            // elements: declarations, combined_selector
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 546:4: -> ^( RULE ( combined_selector )+ declarations )
            {
                // CSS.g:546:7: ^( RULE ( combined_selector )+ declarations )
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
                adaptor.addChild(root_1, stream_declarations.nextTree());

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

    public static class declarations_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "declarations"
    // CSS.g:549:1: declarations : ( declaration )? ( SEMICOLON ( S )* ( declaration )? )* -> ^( SET ( declaration )* ) ;
    public final CSSParser.declarations_return declarations() throws RecognitionException {
        CSSParser.declarations_return retval = new CSSParser.declarations_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SEMICOLON60=null;
        Token S61=null;
        CSSParser.declaration_return declaration59 = null;

        CSSParser.declaration_return declaration62 = null;


        Object SEMICOLON60_tree=null;
        Object S61_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleSubtreeStream stream_declaration=new RewriteRuleSubtreeStream(adaptor,"rule declaration");
        try {
            // CSS.g:550:2: ( ( declaration )? ( SEMICOLON ( S )* ( declaration )? )* -> ^( SET ( declaration )* ) )
            // CSS.g:550:4: ( declaration )? ( SEMICOLON ( S )* ( declaration )? )*
            {
            // CSS.g:550:4: ( declaration )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==IDENT) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // CSS.g:550:4: declaration
                    {
                    pushFollow(FOLLOW_declaration_in_declarations598);
                    declaration59=declaration();

                    state._fsp--;

                    stream_declaration.add(declaration59.getTree());

                    }
                    break;

            }

            // CSS.g:550:17: ( SEMICOLON ( S )* ( declaration )? )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==SEMICOLON) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // CSS.g:550:18: SEMICOLON ( S )* ( declaration )?
            	    {
            	    SEMICOLON60=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_declarations602);  
            	    stream_SEMICOLON.add(SEMICOLON60);

            	    // CSS.g:550:28: ( S )*
            	    loop31:
            	    do {
            	        int alt31=2;
            	        int LA31_0 = input.LA(1);

            	        if ( (LA31_0==S) ) {
            	            alt31=1;
            	        }


            	        switch (alt31) {
            	    	case 1 :
            	    	    // CSS.g:550:28: S
            	    	    {
            	    	    S61=(Token)match(input,S,FOLLOW_S_in_declarations604);  
            	    	    stream_S.add(S61);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop31;
            	        }
            	    } while (true);

            	    // CSS.g:550:31: ( declaration )?
            	    int alt32=2;
            	    int LA32_0 = input.LA(1);

            	    if ( (LA32_0==IDENT) ) {
            	        alt32=1;
            	    }
            	    switch (alt32) {
            	        case 1 :
            	            // CSS.g:550:31: declaration
            	            {
            	            pushFollow(FOLLOW_declaration_in_declarations607);
            	            declaration62=declaration();

            	            state._fsp--;

            	            stream_declaration.add(declaration62.getTree());

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);



            // AST REWRITE
            // elements: declaration
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 551:4: -> ^( SET ( declaration )* )
            {
                // CSS.g:551:7: ^( SET ( declaration )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SET, "SET"), root_1);

                // CSS.g:551:13: ( declaration )*
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
    // $ANTLR end "declarations"

    public static class declaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "declaration"
    // CSS.g:554:1: declaration : property COLON ( S )* terms ( important )? -> ^( DECLARATION ( important )? property terms ) ;
    public final CSSParser.declaration_return declaration() throws RecognitionException {
        CSSParser.declaration_return retval = new CSSParser.declaration_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON64=null;
        Token S65=null;
        CSSParser.property_return property63 = null;

        CSSParser.terms_return terms66 = null;

        CSSParser.important_return important67 = null;


        Object COLON64_tree=null;
        Object S65_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleSubtreeStream stream_important=new RewriteRuleSubtreeStream(adaptor,"rule important");
        RewriteRuleSubtreeStream stream_terms=new RewriteRuleSubtreeStream(adaptor,"rule terms");
        RewriteRuleSubtreeStream stream_property=new RewriteRuleSubtreeStream(adaptor,"rule property");
        try {
            // CSS.g:555:2: ( property COLON ( S )* terms ( important )? -> ^( DECLARATION ( important )? property terms ) )
            // CSS.g:555:4: property COLON ( S )* terms ( important )?
            {
            pushFollow(FOLLOW_property_in_declaration634);
            property63=property();

            state._fsp--;

            stream_property.add(property63.getTree());
            COLON64=(Token)match(input,COLON,FOLLOW_COLON_in_declaration636);  
            stream_COLON.add(COLON64);

            // CSS.g:555:19: ( S )*
            loop34:
            do {
                int alt34=2;
                alt34 = dfa34.predict(input);
                switch (alt34) {
            	case 1 :
            	    // CSS.g:555:19: S
            	    {
            	    S65=(Token)match(input,S,FOLLOW_S_in_declaration638);  
            	    stream_S.add(S65);


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);

            pushFollow(FOLLOW_terms_in_declaration641);
            terms66=terms();

            state._fsp--;

            stream_terms.add(terms66.getTree());
            // CSS.g:555:28: ( important )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==EXCLAMATION) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // CSS.g:555:28: important
                    {
                    pushFollow(FOLLOW_important_in_declaration643);
                    important67=important();

                    state._fsp--;

                    stream_important.add(important67.getTree());

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
            // 555:39: -> ^( DECLARATION ( important )? property terms )
            {
                // CSS.g:555:42: ^( DECLARATION ( important )? property terms )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DECLARATION, "DECLARATION"), root_1);

                // CSS.g:555:56: ( important )?
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
    // CSS.g:561:1: important : EXCLAMATION ( S )* 'important' ( S )* -> IMPORTANT ;
    public final CSSParser.important_return important() throws RecognitionException {
        CSSParser.important_return retval = new CSSParser.important_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EXCLAMATION68=null;
        Token S69=null;
        Token string_literal70=null;
        Token S71=null;

        Object EXCLAMATION68_tree=null;
        Object S69_tree=null;
        Object string_literal70_tree=null;
        Object S71_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleTokenStream stream_EXCLAMATION=new RewriteRuleTokenStream(adaptor,"token EXCLAMATION");

        try {
            // CSS.g:562:5: ( EXCLAMATION ( S )* 'important' ( S )* -> IMPORTANT )
            // CSS.g:562:7: EXCLAMATION ( S )* 'important' ( S )*
            {
            EXCLAMATION68=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_important678);  
            stream_EXCLAMATION.add(EXCLAMATION68);

            // CSS.g:562:19: ( S )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==S) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // CSS.g:562:19: S
            	    {
            	    S69=(Token)match(input,S,FOLLOW_S_in_important680);  
            	    stream_S.add(S69);


            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);

            string_literal70=(Token)match(input,84,FOLLOW_84_in_important683);  
            stream_84.add(string_literal70);

            // CSS.g:562:34: ( S )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==S) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // CSS.g:562:34: S
            	    {
            	    S71=(Token)match(input,S,FOLLOW_S_in_important685);  
            	    stream_S.add(S71);


            	    }
            	    break;

            	default :
            	    break loop37;
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
            // 562:37: -> IMPORTANT
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
    // CSS.g:565:1: property : IDENT ( S )* -> IDENT ;
    public final CSSParser.property_return property() throws RecognitionException {
        CSSParser.property_return retval = new CSSParser.property_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT72=null;
        Token S73=null;

        Object IDENT72_tree=null;
        Object S73_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");

        try {
            // CSS.g:566:2: ( IDENT ( S )* -> IDENT )
            // CSS.g:566:4: IDENT ( S )*
            {
            IDENT72=(Token)match(input,IDENT,FOLLOW_IDENT_in_property710);  
            stream_IDENT.add(IDENT72);

            // CSS.g:566:10: ( S )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==S) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // CSS.g:566:10: S
            	    {
            	    S73=(Token)match(input,S,FOLLOW_S_in_property712);  
            	    stream_S.add(S73);


            	    }
            	    break;

            	default :
            	    break loop38;
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
            // 566:13: -> IDENT
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
    // CSS.g:569:1: terms : ( term )+ -> ^( VALUE ( term )+ ) ;
    public final CSSParser.terms_return terms() throws RecognitionException {
        CSSParser.terms_return retval = new CSSParser.terms_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CSSParser.term_return term74 = null;


        RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");
        try {
            // CSS.g:570:2: ( ( term )+ -> ^( VALUE ( term )+ ) )
            // CSS.g:570:4: ( term )+
            {
            // CSS.g:570:4: ( term )+
            int cnt39=0;
            loop39:
            do {
                int alt39=2;
                alt39 = dfa39.predict(input);
                switch (alt39) {
            	case 1 :
            	    // CSS.g:570:4: term
            	    {
            	    pushFollow(FOLLOW_term_in_terms737);
            	    term74=term();

            	    state._fsp--;

            	    stream_term.add(term74.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt39 >= 1 ) break loop39;
                        EarlyExitException eee =
                            new EarlyExitException(39, input);
                        throw eee;
                }
                cnt39++;
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
            // 571:2: -> ^( VALUE ( term )+ )
            {
                // CSS.g:571:5: ^( VALUE ( term )+ )
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
    // CSS.g:574:1: term : ( valuepart -> valuepart | LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY -> CURLYBLOCK | ATKEYWORD ( S )* -> ATKEYWORD );
    public final CSSParser.term_return term() throws RecognitionException {
        CSSParser.term_return retval = new CSSParser.term_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LCURLY76=null;
        Token S77=null;
        Token SEMICOLON79=null;
        Token S80=null;
        Token RCURLY81=null;
        Token ATKEYWORD82=null;
        Token S83=null;
        CSSParser.valuepart_return valuepart75 = null;

        CSSParser.any_return any78 = null;


        Object LCURLY76_tree=null;
        Object S77_tree=null;
        Object SEMICOLON79_tree=null;
        Object S80_tree=null;
        Object RCURLY81_tree=null;
        Object ATKEYWORD82_tree=null;
        Object S83_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_ATKEYWORD=new RewriteRuleTokenStream(adaptor,"token ATKEYWORD");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleSubtreeStream stream_valuepart=new RewriteRuleSubtreeStream(adaptor,"rule valuepart");
        RewriteRuleSubtreeStream stream_any=new RewriteRuleSubtreeStream(adaptor,"rule any");
        try {
            // CSS.g:575:5: ( valuepart -> valuepart | LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY -> CURLYBLOCK | ATKEYWORD ( S )* -> ATKEYWORD )
            int alt44=3;
            alt44 = dfa44.predict(input);
            switch (alt44) {
                case 1 :
                    // CSS.g:575:7: valuepart
                    {
                    pushFollow(FOLLOW_valuepart_in_term763);
                    valuepart75=valuepart();

                    state._fsp--;

                    stream_valuepart.add(valuepart75.getTree());


                    // AST REWRITE
                    // elements: valuepart
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 575:17: -> valuepart
                    {
                        adaptor.addChild(root_0, stream_valuepart.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // CSS.g:576:7: LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY
                    {
                    LCURLY76=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_term775);  
                    stream_LCURLY.add(LCURLY76);

                    // CSS.g:576:14: ( S )*
                    loop40:
                    do {
                        int alt40=2;
                        alt40 = dfa40.predict(input);
                        switch (alt40) {
                    	case 1 :
                    	    // CSS.g:576:14: S
                    	    {
                    	    S77=(Token)match(input,S,FOLLOW_S_in_term777);  
                    	    stream_S.add(S77);


                    	    }
                    	    break;

                    	default :
                    	    break loop40;
                        }
                    } while (true);

                    // CSS.g:576:17: ( any | SEMICOLON ( S )* )*
                    loop42:
                    do {
                        int alt42=3;
                        alt42 = dfa42.predict(input);
                        switch (alt42) {
                    	case 1 :
                    	    // CSS.g:576:18: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_term781);
                    	    any78=any();

                    	    state._fsp--;

                    	    stream_any.add(any78.getTree());

                    	    }
                    	    break;
                    	case 2 :
                    	    // CSS.g:576:24: SEMICOLON ( S )*
                    	    {
                    	    SEMICOLON79=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_term785);  
                    	    stream_SEMICOLON.add(SEMICOLON79);

                    	    // CSS.g:576:34: ( S )*
                    	    loop41:
                    	    do {
                    	        int alt41=2;
                    	        alt41 = dfa41.predict(input);
                    	        switch (alt41) {
                    	    	case 1 :
                    	    	    // CSS.g:576:34: S
                    	    	    {
                    	    	    S80=(Token)match(input,S,FOLLOW_S_in_term787);  
                    	    	    stream_S.add(S80);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop41;
                    	        }
                    	    } while (true);


                    	    }
                    	    break;

                    	default :
                    	    break loop42;
                        }
                    } while (true);

                    RCURLY81=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_term792);  
                    stream_RCURLY.add(RCURLY81);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 576:46: -> CURLYBLOCK
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(CURLYBLOCK, "CURLYBLOCK"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // CSS.g:577:7: ATKEYWORD ( S )*
                    {
                    ATKEYWORD82=(Token)match(input,ATKEYWORD,FOLLOW_ATKEYWORD_in_term804);  
                    stream_ATKEYWORD.add(ATKEYWORD82);

                    // CSS.g:577:17: ( S )*
                    loop43:
                    do {
                        int alt43=2;
                        alt43 = dfa43.predict(input);
                        switch (alt43) {
                    	case 1 :
                    	    // CSS.g:577:17: S
                    	    {
                    	    S83=(Token)match(input,S,FOLLOW_S_in_term806);  
                    	    stream_S.add(S83);


                    	    }
                    	    break;

                    	default :
                    	    break loop43;
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
                    // 577:20: -> ATKEYWORD
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
    // CSS.g:580:1: valuepart : ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* terms RPAREN -> ^( FUNCTION terms ) | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) ) ( S )* ;
    public final CSSParser.valuepart_return valuepart() throws RecognitionException {
        CSSParser.valuepart_return retval = new CSSParser.valuepart_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT84=null;
        Token CLASSKEYWORD85=null;
        Token MINUS86=null;
        Token NUMBER87=null;
        Token MINUS88=null;
        Token PERCENTAGE89=null;
        Token MINUS90=null;
        Token DIMENSION91=null;
        Token URI93=null;
        Token HASH94=null;
        Token UNIRANGE95=null;
        Token INCLUDES96=null;
        Token COLON97=null;
        Token COMMA98=null;
        Token GREATER99=null;
        Token EQUALS100=null;
        Token SLASH101=null;
        Token PLUS102=null;
        Token ASTERISK103=null;
        Token FUNCTION104=null;
        Token S105=null;
        Token RPAREN107=null;
        Token DASHMATCH108=null;
        Token LPAREN109=null;
        Token RPAREN111=null;
        Token LBRACE112=null;
        Token RBRACE114=null;
        Token S115=null;
        CSSParser.string_return string92 = null;

        CSSParser.terms_return terms106 = null;

        CSSParser.valuepart_return valuepart110 = null;

        CSSParser.valuepart_return valuepart113 = null;


        Object IDENT84_tree=null;
        Object CLASSKEYWORD85_tree=null;
        Object MINUS86_tree=null;
        Object NUMBER87_tree=null;
        Object MINUS88_tree=null;
        Object PERCENTAGE89_tree=null;
        Object MINUS90_tree=null;
        Object DIMENSION91_tree=null;
        Object URI93_tree=null;
        Object HASH94_tree=null;
        Object UNIRANGE95_tree=null;
        Object INCLUDES96_tree=null;
        Object COLON97_tree=null;
        Object COMMA98_tree=null;
        Object GREATER99_tree=null;
        Object EQUALS100_tree=null;
        Object SLASH101_tree=null;
        Object PLUS102_tree=null;
        Object ASTERISK103_tree=null;
        Object FUNCTION104_tree=null;
        Object S105_tree=null;
        Object RPAREN107_tree=null;
        Object DASHMATCH108_tree=null;
        Object LPAREN109_tree=null;
        Object RPAREN111_tree=null;
        Object LBRACE112_tree=null;
        Object RBRACE114_tree=null;
        Object S115_tree=null;
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
            // CSS.g:581:5: ( ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* terms RPAREN -> ^( FUNCTION terms ) | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) ) ( S )* )
            // CSS.g:581:7: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* terms RPAREN -> ^( FUNCTION terms ) | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) ) ( S )*
            {
            // CSS.g:581:7: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* terms RPAREN -> ^( FUNCTION terms ) | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) )
            int alt51=21;
            alt51 = dfa51.predict(input);
            switch (alt51) {
                case 1 :
                    // CSS.g:581:9: IDENT
                    {
                    IDENT84=(Token)match(input,IDENT,FOLLOW_IDENT_in_valuepart831);  
                    stream_IDENT.add(IDENT84);



                    // AST REWRITE
                    // elements: IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 581:15: -> IDENT
                    {
                        adaptor.addChild(root_0, stream_IDENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // CSS.g:582:9: CLASSKEYWORD
                    {
                    CLASSKEYWORD85=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_valuepart845);  
                    stream_CLASSKEYWORD.add(CLASSKEYWORD85);



                    // AST REWRITE
                    // elements: CLASSKEYWORD
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 582:22: -> CLASSKEYWORD
                    {
                        adaptor.addChild(root_0, stream_CLASSKEYWORD.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // CSS.g:583:9: ( MINUS )? NUMBER
                    {
                    // CSS.g:583:9: ( MINUS )?
                    int alt45=2;
                    int LA45_0 = input.LA(1);

                    if ( (LA45_0==MINUS) ) {
                        alt45=1;
                    }
                    switch (alt45) {
                        case 1 :
                            // CSS.g:583:9: MINUS
                            {
                            MINUS86=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart859);  
                            stream_MINUS.add(MINUS86);


                            }
                            break;

                    }

                    NUMBER87=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_valuepart862);  
                    stream_NUMBER.add(NUMBER87);



                    // AST REWRITE
                    // elements: NUMBER, MINUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 583:23: -> ( MINUS )? NUMBER
                    {
                        // CSS.g:583:26: ( MINUS )?
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
                    // CSS.g:584:9: ( MINUS )? PERCENTAGE
                    {
                    // CSS.g:584:9: ( MINUS )?
                    int alt46=2;
                    int LA46_0 = input.LA(1);

                    if ( (LA46_0==MINUS) ) {
                        alt46=1;
                    }
                    switch (alt46) {
                        case 1 :
                            // CSS.g:584:9: MINUS
                            {
                            MINUS88=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart879);  
                            stream_MINUS.add(MINUS88);


                            }
                            break;

                    }

                    PERCENTAGE89=(Token)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_valuepart882);  
                    stream_PERCENTAGE.add(PERCENTAGE89);



                    // AST REWRITE
                    // elements: PERCENTAGE, MINUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 584:27: -> ( MINUS )? PERCENTAGE
                    {
                        // CSS.g:584:30: ( MINUS )?
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
                    // CSS.g:585:9: ( MINUS )? DIMENSION
                    {
                    // CSS.g:585:9: ( MINUS )?
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( (LA47_0==MINUS) ) {
                        alt47=1;
                    }
                    switch (alt47) {
                        case 1 :
                            // CSS.g:585:9: MINUS
                            {
                            MINUS90=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart899);  
                            stream_MINUS.add(MINUS90);


                            }
                            break;

                    }

                    DIMENSION91=(Token)match(input,DIMENSION,FOLLOW_DIMENSION_in_valuepart902);  
                    stream_DIMENSION.add(DIMENSION91);



                    // AST REWRITE
                    // elements: DIMENSION, MINUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 585:26: -> ( MINUS )? DIMENSION
                    {
                        // CSS.g:585:29: ( MINUS )?
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
                    // CSS.g:586:9: string
                    {
                    pushFollow(FOLLOW_string_in_valuepart919);
                    string92=string();

                    state._fsp--;

                    stream_string.add(string92.getTree());


                    // AST REWRITE
                    // elements: string
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 586:16: -> string
                    {
                        adaptor.addChild(root_0, stream_string.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // CSS.g:587:9: URI
                    {
                    URI93=(Token)match(input,URI,FOLLOW_URI_in_valuepart933);  
                    stream_URI.add(URI93);



                    // AST REWRITE
                    // elements: URI
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 587:16: -> URI
                    {
                        adaptor.addChild(root_0, stream_URI.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // CSS.g:588:9: HASH
                    {
                    HASH94=(Token)match(input,HASH,FOLLOW_HASH_in_valuepart950);  
                    stream_HASH.add(HASH94);



                    // AST REWRITE
                    // elements: HASH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 588:14: -> HASH
                    {
                        adaptor.addChild(root_0, stream_HASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // CSS.g:589:9: UNIRANGE
                    {
                    UNIRANGE95=(Token)match(input,UNIRANGE,FOLLOW_UNIRANGE_in_valuepart964);  
                    stream_UNIRANGE.add(UNIRANGE95);



                    // AST REWRITE
                    // elements: UNIRANGE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 589:18: -> UNIRANGE
                    {
                        adaptor.addChild(root_0, stream_UNIRANGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // CSS.g:590:9: INCLUDES
                    {
                    INCLUDES96=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_valuepart978);  
                    stream_INCLUDES.add(INCLUDES96);



                    // AST REWRITE
                    // elements: INCLUDES
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 590:18: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // CSS.g:591:9: COLON
                    {
                    COLON97=(Token)match(input,COLON,FOLLOW_COLON_in_valuepart992);  
                    stream_COLON.add(COLON97);



                    // AST REWRITE
                    // elements: COLON
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 591:15: -> COLON
                    {
                        adaptor.addChild(root_0, stream_COLON.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // CSS.g:592:9: COMMA
                    {
                    COMMA98=(Token)match(input,COMMA,FOLLOW_COMMA_in_valuepart1006);  
                    stream_COMMA.add(COMMA98);



                    // AST REWRITE
                    // elements: COMMA
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 592:15: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // CSS.g:593:9: GREATER
                    {
                    GREATER99=(Token)match(input,GREATER,FOLLOW_GREATER_in_valuepart1020);  
                    stream_GREATER.add(GREATER99);



                    // AST REWRITE
                    // elements: GREATER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 593:17: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // CSS.g:594:9: EQUALS
                    {
                    EQUALS100=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_valuepart1034);  
                    stream_EQUALS.add(EQUALS100);



                    // AST REWRITE
                    // elements: EQUALS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 594:16: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // CSS.g:595:9: SLASH
                    {
                    SLASH101=(Token)match(input,SLASH,FOLLOW_SLASH_in_valuepart1048);  
                    stream_SLASH.add(SLASH101);



                    // AST REWRITE
                    // elements: SLASH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 595:15: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // CSS.g:596:6: PLUS
                    {
                    PLUS102=(Token)match(input,PLUS,FOLLOW_PLUS_in_valuepart1059);  
                    stream_PLUS.add(PLUS102);



                    // AST REWRITE
                    // elements: PLUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 596:11: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // CSS.g:597:6: ASTERISK
                    {
                    ASTERISK103=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_valuepart1070);  
                    stream_ASTERISK.add(ASTERISK103);



                    // AST REWRITE
                    // elements: ASTERISK
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 597:15: -> ASTERISK
                    {
                        adaptor.addChild(root_0, stream_ASTERISK.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 18 :
                    // CSS.g:598:9: FUNCTION ( S )* terms RPAREN
                    {
                    FUNCTION104=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_valuepart1087);  
                    stream_FUNCTION.add(FUNCTION104);

                    // CSS.g:598:18: ( S )*
                    loop48:
                    do {
                        int alt48=2;
                        alt48 = dfa48.predict(input);
                        switch (alt48) {
                    	case 1 :
                    	    // CSS.g:598:18: S
                    	    {
                    	    S105=(Token)match(input,S,FOLLOW_S_in_valuepart1089);  
                    	    stream_S.add(S105);


                    	    }
                    	    break;

                    	default :
                    	    break loop48;
                        }
                    } while (true);

                    pushFollow(FOLLOW_terms_in_valuepart1092);
                    terms106=terms();

                    state._fsp--;

                    stream_terms.add(terms106.getTree());
                    RPAREN107=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_valuepart1094);  
                    stream_RPAREN.add(RPAREN107);



                    // AST REWRITE
                    // elements: terms, FUNCTION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 598:34: -> ^( FUNCTION terms )
                    {
                        // CSS.g:598:37: ^( FUNCTION terms )
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
                    // CSS.g:599:9: DASHMATCH
                    {
                    DASHMATCH108=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_valuepart1113);  
                    stream_DASHMATCH.add(DASHMATCH108);



                    // AST REWRITE
                    // elements: DASHMATCH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 599:19: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 20 :
                    // CSS.g:600:9: LPAREN ( valuepart )* RPAREN
                    {
                    LPAREN109=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_valuepart1127);  
                    stream_LPAREN.add(LPAREN109);

                    // CSS.g:600:16: ( valuepart )*
                    loop49:
                    do {
                        int alt49=2;
                        alt49 = dfa49.predict(input);
                        switch (alt49) {
                    	case 1 :
                    	    // CSS.g:600:16: valuepart
                    	    {
                    	    pushFollow(FOLLOW_valuepart_in_valuepart1129);
                    	    valuepart110=valuepart();

                    	    state._fsp--;

                    	    stream_valuepart.add(valuepart110.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop49;
                        }
                    } while (true);

                    RPAREN111=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_valuepart1132);  
                    stream_RPAREN.add(RPAREN111);



                    // AST REWRITE
                    // elements: valuepart
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 600:34: -> ^( PARENBLOCK ( valuepart )* )
                    {
                        // CSS.g:600:37: ^( PARENBLOCK ( valuepart )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PARENBLOCK, "PARENBLOCK"), root_1);

                        // CSS.g:600:50: ( valuepart )*
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
                    // CSS.g:601:9: LBRACE ( valuepart )* RBRACE
                    {
                    LBRACE112=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_valuepart1151);  
                    stream_LBRACE.add(LBRACE112);

                    // CSS.g:601:16: ( valuepart )*
                    loop50:
                    do {
                        int alt50=2;
                        alt50 = dfa50.predict(input);
                        switch (alt50) {
                    	case 1 :
                    	    // CSS.g:601:16: valuepart
                    	    {
                    	    pushFollow(FOLLOW_valuepart_in_valuepart1153);
                    	    valuepart113=valuepart();

                    	    state._fsp--;

                    	    stream_valuepart.add(valuepart113.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop50;
                        }
                    } while (true);

                    RBRACE114=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_valuepart1156);  
                    stream_RBRACE.add(RBRACE114);



                    // AST REWRITE
                    // elements: valuepart
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 601:34: -> ^( BRACEBLOCK ( valuepart )* )
                    {
                        // CSS.g:601:37: ^( BRACEBLOCK ( valuepart )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BRACEBLOCK, "BRACEBLOCK"), root_1);

                        // CSS.g:601:50: ( valuepart )*
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

            // CSS.g:602:8: ( S )*
            loop52:
            do {
                int alt52=2;
                alt52 = dfa52.predict(input);
                switch (alt52) {
            	case 1 :
            	    // CSS.g:602:8: S
            	    {
            	    S115=(Token)match(input,S,FOLLOW_S_in_valuepart1174);  
            	    stream_S.add(S115);


            	    }
            	    break;

            	default :
            	    break loop52;
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
    // CSS.g:605:1: combined_selector : selector ( ( combinator ) selector )* ;
    public final CSSParser.combined_selector_return combined_selector() throws RecognitionException {
        CSSParser.combined_selector_return retval = new CSSParser.combined_selector_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CSSParser.selector_return selector116 = null;

        CSSParser.combinator_return combinator117 = null;

        CSSParser.selector_return selector118 = null;



        try {
            // CSS.g:606:2: ( selector ( ( combinator ) selector )* )
            // CSS.g:606:4: selector ( ( combinator ) selector )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_selector_in_combined_selector1191);
            selector116=selector();

            state._fsp--;

            adaptor.addChild(root_0, selector116.getTree());
            // CSS.g:606:13: ( ( combinator ) selector )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( (LA53_0==S||LA53_0==GREATER||LA53_0==PLUS) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // CSS.g:606:14: ( combinator ) selector
            	    {
            	    // CSS.g:606:14: ( combinator )
            	    // CSS.g:606:15: combinator
            	    {
            	    pushFollow(FOLLOW_combinator_in_combined_selector1195);
            	    combinator117=combinator();

            	    state._fsp--;

            	    adaptor.addChild(root_0, combinator117.getTree());

            	    }

            	    pushFollow(FOLLOW_selector_in_combined_selector1198);
            	    selector118=selector();

            	    state._fsp--;

            	    adaptor.addChild(root_0, selector118.getTree());

            	    }
            	    break;

            	default :
            	    break loop53;
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
    // CSS.g:614:1: combinator : ( GREATER ( S )* -> CHILD | PLUS ( S )* -> ADJACENT | S -> DESCENDANT );
    public final CSSParser.combinator_return combinator() throws RecognitionException {
        CSSParser.combinator_return retval = new CSSParser.combinator_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token GREATER119=null;
        Token S120=null;
        Token PLUS121=null;
        Token S122=null;
        Token S123=null;

        Object GREATER119_tree=null;
        Object S120_tree=null;
        Object PLUS121_tree=null;
        Object S122_tree=null;
        Object S123_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");

        try {
            // CSS.g:615:2: ( GREATER ( S )* -> CHILD | PLUS ( S )* -> ADJACENT | S -> DESCENDANT )
            int alt56=3;
            switch ( input.LA(1) ) {
            case GREATER:
                {
                alt56=1;
                }
                break;
            case PLUS:
                {
                alt56=2;
                }
                break;
            case S:
                {
                alt56=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;
            }

            switch (alt56) {
                case 1 :
                    // CSS.g:615:4: GREATER ( S )*
                    {
                    GREATER119=(Token)match(input,GREATER,FOLLOW_GREATER_in_combinator1218);  
                    stream_GREATER.add(GREATER119);

                    // CSS.g:615:12: ( S )*
                    loop54:
                    do {
                        int alt54=2;
                        int LA54_0 = input.LA(1);

                        if ( (LA54_0==S) ) {
                            alt54=1;
                        }


                        switch (alt54) {
                    	case 1 :
                    	    // CSS.g:615:12: S
                    	    {
                    	    S120=(Token)match(input,S,FOLLOW_S_in_combinator1220);  
                    	    stream_S.add(S120);


                    	    }
                    	    break;

                    	default :
                    	    break loop54;
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
                    // 615:15: -> CHILD
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(CHILD, "CHILD"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // CSS.g:616:4: PLUS ( S )*
                    {
                    PLUS121=(Token)match(input,PLUS,FOLLOW_PLUS_in_combinator1230);  
                    stream_PLUS.add(PLUS121);

                    // CSS.g:616:9: ( S )*
                    loop55:
                    do {
                        int alt55=2;
                        int LA55_0 = input.LA(1);

                        if ( (LA55_0==S) ) {
                            alt55=1;
                        }


                        switch (alt55) {
                    	case 1 :
                    	    // CSS.g:616:9: S
                    	    {
                    	    S122=(Token)match(input,S,FOLLOW_S_in_combinator1232);  
                    	    stream_S.add(S122);


                    	    }
                    	    break;

                    	default :
                    	    break loop55;
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
                    // 616:12: -> ADJACENT
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(ADJACENT, "ADJACENT"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // CSS.g:617:4: S
                    {
                    S123=(Token)match(input,S,FOLLOW_S_in_combinator1242);  
                    stream_S.add(S123);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 617:6: -> DESCENDANT
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
    // CSS.g:620:1: selector : ( ( IDENT | ASTERISK ) ( selpart )* ( S )* -> ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* ) | ( selpart )+ ( S )* -> ^( SELECTOR ( selpart )+ ) );
    public final CSSParser.selector_return selector() throws RecognitionException {
        CSSParser.selector_return retval = new CSSParser.selector_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT124=null;
        Token ASTERISK125=null;
        Token S127=null;
        Token S129=null;
        CSSParser.selpart_return selpart126 = null;

        CSSParser.selpart_return selpart128 = null;


        Object IDENT124_tree=null;
        Object ASTERISK125_tree=null;
        Object S127_tree=null;
        Object S129_tree=null;
        RewriteRuleTokenStream stream_ASTERISK=new RewriteRuleTokenStream(adaptor,"token ASTERISK");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleSubtreeStream stream_selpart=new RewriteRuleSubtreeStream(adaptor,"rule selpart");
        try {
            // CSS.g:621:5: ( ( IDENT | ASTERISK ) ( selpart )* ( S )* -> ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* ) | ( selpart )+ ( S )* -> ^( SELECTOR ( selpart )+ ) )
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==IDENT||LA62_0==ASTERISK) ) {
                alt62=1;
            }
            else if ( (LA62_0==INVALID_SELPART||LA62_0==COLON||LA62_0==CLASSKEYWORD||LA62_0==HASH||LA62_0==LBRACE) ) {
                alt62=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;
            }
            switch (alt62) {
                case 1 :
                    // CSS.g:621:7: ( IDENT | ASTERISK ) ( selpart )* ( S )*
                    {
                    // CSS.g:621:7: ( IDENT | ASTERISK )
                    int alt57=2;
                    int LA57_0 = input.LA(1);

                    if ( (LA57_0==IDENT) ) {
                        alt57=1;
                    }
                    else if ( (LA57_0==ASTERISK) ) {
                        alt57=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 57, 0, input);

                        throw nvae;
                    }
                    switch (alt57) {
                        case 1 :
                            // CSS.g:621:8: IDENT
                            {
                            IDENT124=(Token)match(input,IDENT,FOLLOW_IDENT_in_selector1261);  
                            stream_IDENT.add(IDENT124);


                            }
                            break;
                        case 2 :
                            // CSS.g:621:16: ASTERISK
                            {
                            ASTERISK125=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_selector1265);  
                            stream_ASTERISK.add(ASTERISK125);


                            }
                            break;

                    }

                    // CSS.g:621:27: ( selpart )*
                    loop58:
                    do {
                        int alt58=2;
                        alt58 = dfa58.predict(input);
                        switch (alt58) {
                    	case 1 :
                    	    // CSS.g:621:27: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1269);
                    	    selpart126=selpart();

                    	    state._fsp--;

                    	    stream_selpart.add(selpart126.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop58;
                        }
                    } while (true);

                    // CSS.g:621:36: ( S )*
                    loop59:
                    do {
                        int alt59=2;
                        alt59 = dfa59.predict(input);
                        switch (alt59) {
                    	case 1 :
                    	    // CSS.g:621:36: S
                    	    {
                    	    S127=(Token)match(input,S,FOLLOW_S_in_selector1272);  
                    	    stream_S.add(S127);


                    	    }
                    	    break;

                    	default :
                    	    break loop59;
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
                    // 622:6: -> ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* )
                    {
                        // CSS.g:622:9: ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SELECTOR, "SELECTOR"), root_1);

                        // CSS.g:622:20: ^( ELEMENT ( IDENT )? )
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ELEMENT, "ELEMENT"), root_2);

                        // CSS.g:622:30: ( IDENT )?
                        if ( stream_IDENT.hasNext() ) {
                            adaptor.addChild(root_2, stream_IDENT.nextNode());

                        }
                        stream_IDENT.reset();

                        adaptor.addChild(root_1, root_2);
                        }
                        // CSS.g:622:38: ( selpart )*
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
                    // CSS.g:623:7: ( selpart )+ ( S )*
                    {
                    // CSS.g:623:7: ( selpart )+
                    int cnt60=0;
                    loop60:
                    do {
                        int alt60=2;
                        alt60 = dfa60.predict(input);
                        switch (alt60) {
                    	case 1 :
                    	    // CSS.g:623:7: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1302);
                    	    selpart128=selpart();

                    	    state._fsp--;

                    	    stream_selpart.add(selpart128.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt60 >= 1 ) break loop60;
                                EarlyExitException eee =
                                    new EarlyExitException(60, input);
                                throw eee;
                        }
                        cnt60++;
                    } while (true);

                    // CSS.g:623:16: ( S )*
                    loop61:
                    do {
                        int alt61=2;
                        alt61 = dfa61.predict(input);
                        switch (alt61) {
                    	case 1 :
                    	    // CSS.g:623:16: S
                    	    {
                    	    S129=(Token)match(input,S,FOLLOW_S_in_selector1305);  
                    	    stream_S.add(S129);


                    	    }
                    	    break;

                    	default :
                    	    break loop61;
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
                    // 624:9: -> ^( SELECTOR ( selpart )+ )
                    {
                        // CSS.g:624:12: ^( SELECTOR ( selpart )+ )
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
    // CSS.g:630:1: selpart : ( HASH | CLASSKEYWORD | LBRACE ( S )* attribute RBRACE -> ^( ATTRIBUTE attribute ) | pseudo | INVALID_SELPART );
    public final CSSParser.selpart_return selpart() throws RecognitionException {
        CSSParser.selpart_return retval = new CSSParser.selpart_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token HASH130=null;
        Token CLASSKEYWORD131=null;
        Token LBRACE132=null;
        Token S133=null;
        Token RBRACE135=null;
        Token INVALID_SELPART137=null;
        CSSParser.attribute_return attribute134 = null;

        CSSParser.pseudo_return pseudo136 = null;


        Object HASH130_tree=null;
        Object CLASSKEYWORD131_tree=null;
        Object LBRACE132_tree=null;
        Object S133_tree=null;
        Object RBRACE135_tree=null;
        Object INVALID_SELPART137_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleSubtreeStream stream_attribute=new RewriteRuleSubtreeStream(adaptor,"rule attribute");
        try {
            // CSS.g:631:5: ( HASH | CLASSKEYWORD | LBRACE ( S )* attribute RBRACE -> ^( ATTRIBUTE attribute ) | pseudo | INVALID_SELPART )
            int alt64=5;
            switch ( input.LA(1) ) {
            case HASH:
                {
                alt64=1;
                }
                break;
            case CLASSKEYWORD:
                {
                alt64=2;
                }
                break;
            case LBRACE:
                {
                alt64=3;
                }
                break;
            case COLON:
                {
                alt64=4;
                }
                break;
            case INVALID_SELPART:
                {
                alt64=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;
            }

            switch (alt64) {
                case 1 :
                    // CSS.g:631:8: HASH
                    {
                    root_0 = (Object)adaptor.nil();

                    HASH130=(Token)match(input,HASH,FOLLOW_HASH_in_selpart1352); 
                    HASH130_tree = (Object)adaptor.create(HASH130);
                    adaptor.addChild(root_0, HASH130_tree);


                    }
                    break;
                case 2 :
                    // CSS.g:632:7: CLASSKEYWORD
                    {
                    root_0 = (Object)adaptor.nil();

                    CLASSKEYWORD131=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_selpart1360); 
                    CLASSKEYWORD131_tree = (Object)adaptor.create(CLASSKEYWORD131);
                    adaptor.addChild(root_0, CLASSKEYWORD131_tree);


                    }
                    break;
                case 3 :
                    // CSS.g:633:4: LBRACE ( S )* attribute RBRACE
                    {
                    LBRACE132=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_selpart1365);  
                    stream_LBRACE.add(LBRACE132);

                    // CSS.g:633:11: ( S )*
                    loop63:
                    do {
                        int alt63=2;
                        int LA63_0 = input.LA(1);

                        if ( (LA63_0==S) ) {
                            alt63=1;
                        }


                        switch (alt63) {
                    	case 1 :
                    	    // CSS.g:633:11: S
                    	    {
                    	    S133=(Token)match(input,S,FOLLOW_S_in_selpart1367);  
                    	    stream_S.add(S133);


                    	    }
                    	    break;

                    	default :
                    	    break loop63;
                        }
                    } while (true);

                    pushFollow(FOLLOW_attribute_in_selpart1370);
                    attribute134=attribute();

                    state._fsp--;

                    stream_attribute.add(attribute134.getTree());
                    RBRACE135=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_selpart1372);  
                    stream_RBRACE.add(RBRACE135);



                    // AST REWRITE
                    // elements: attribute
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 633:31: -> ^( ATTRIBUTE attribute )
                    {
                        // CSS.g:633:34: ^( ATTRIBUTE attribute )
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
                case 4 :
                    // CSS.g:634:7: pseudo
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pseudo_in_selpart1388);
                    pseudo136=pseudo();

                    state._fsp--;

                    adaptor.addChild(root_0, pseudo136.getTree());

                    }
                    break;
                case 5 :
                    // CSS.g:635:7: INVALID_SELPART
                    {
                    root_0 = (Object)adaptor.nil();

                    INVALID_SELPART137=(Token)match(input,INVALID_SELPART,FOLLOW_INVALID_SELPART_in_selpart1396); 
                    INVALID_SELPART137_tree = (Object)adaptor.create(INVALID_SELPART137);
                    adaptor.addChild(root_0, INVALID_SELPART137_tree);


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
    // CSS.g:642:1: attribute : IDENT ( S )* ( ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )* )? ;
    public final CSSParser.attribute_return attribute() throws RecognitionException {
        CSSParser.attribute_return retval = new CSSParser.attribute_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT138=null;
        Token S139=null;
        Token set140=null;
        Token S141=null;
        Token IDENT142=null;
        Token S144=null;
        CSSParser.string_return string143 = null;


        Object IDENT138_tree=null;
        Object S139_tree=null;
        Object set140_tree=null;
        Object S141_tree=null;
        Object IDENT142_tree=null;
        Object S144_tree=null;

        try {
            // CSS.g:643:2: ( IDENT ( S )* ( ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )* )? )
            // CSS.g:643:4: IDENT ( S )* ( ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )* )?
            {
            root_0 = (Object)adaptor.nil();

            IDENT138=(Token)match(input,IDENT,FOLLOW_IDENT_in_attribute1422); 
            IDENT138_tree = (Object)adaptor.create(IDENT138);
            adaptor.addChild(root_0, IDENT138_tree);

            // CSS.g:643:10: ( S )*
            loop65:
            do {
                int alt65=2;
                int LA65_0 = input.LA(1);

                if ( (LA65_0==S) ) {
                    alt65=1;
                }


                switch (alt65) {
            	case 1 :
            	    // CSS.g:643:10: S
            	    {
            	    S139=(Token)match(input,S,FOLLOW_S_in_attribute1424); 
            	    S139_tree = (Object)adaptor.create(S139);
            	    adaptor.addChild(root_0, S139_tree);


            	    }
            	    break;

            	default :
            	    break loop65;
                }
            } while (true);

            // CSS.g:644:4: ( ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )* )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==INCLUDES||LA69_0==EQUALS||LA69_0==DASHMATCH) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // CSS.g:644:5: ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )*
                    {
                    set140=(Token)input.LT(1);
                    if ( input.LA(1)==INCLUDES||input.LA(1)==EQUALS||input.LA(1)==DASHMATCH ) {
                        input.consume();
                        adaptor.addChild(root_0, (Object)adaptor.create(set140));
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    // CSS.g:644:37: ( S )*
                    loop66:
                    do {
                        int alt66=2;
                        int LA66_0 = input.LA(1);

                        if ( (LA66_0==S) ) {
                            alt66=1;
                        }


                        switch (alt66) {
                    	case 1 :
                    	    // CSS.g:644:37: S
                    	    {
                    	    S141=(Token)match(input,S,FOLLOW_S_in_attribute1443); 
                    	    S141_tree = (Object)adaptor.create(S141);
                    	    adaptor.addChild(root_0, S141_tree);


                    	    }
                    	    break;

                    	default :
                    	    break loop66;
                        }
                    } while (true);

                    // CSS.g:644:40: ( IDENT | string )
                    int alt67=2;
                    int LA67_0 = input.LA(1);

                    if ( (LA67_0==IDENT) ) {
                        alt67=1;
                    }
                    else if ( (LA67_0==INVALID_STRING||LA67_0==STRING) ) {
                        alt67=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 67, 0, input);

                        throw nvae;
                    }
                    switch (alt67) {
                        case 1 :
                            // CSS.g:644:41: IDENT
                            {
                            IDENT142=(Token)match(input,IDENT,FOLLOW_IDENT_in_attribute1447); 
                            IDENT142_tree = (Object)adaptor.create(IDENT142);
                            adaptor.addChild(root_0, IDENT142_tree);


                            }
                            break;
                        case 2 :
                            // CSS.g:644:49: string
                            {
                            pushFollow(FOLLOW_string_in_attribute1451);
                            string143=string();

                            state._fsp--;

                            adaptor.addChild(root_0, string143.getTree());

                            }
                            break;

                    }

                    // CSS.g:644:57: ( S )*
                    loop68:
                    do {
                        int alt68=2;
                        int LA68_0 = input.LA(1);

                        if ( (LA68_0==S) ) {
                            alt68=1;
                        }


                        switch (alt68) {
                    	case 1 :
                    	    // CSS.g:644:57: S
                    	    {
                    	    S144=(Token)match(input,S,FOLLOW_S_in_attribute1454); 
                    	    S144_tree = (Object)adaptor.create(S144);
                    	    adaptor.addChild(root_0, S144_tree);


                    	    }
                    	    break;

                    	default :
                    	    break loop68;
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

    public static class pseudo_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pseudo"
    // CSS.g:647:1: pseudo : COLON ( IDENT | FUNCTION ( S )* IDENT ( S )* RPAREN ) -> ^( PSEUDO ( FUNCTION )? IDENT ) ;
    public final CSSParser.pseudo_return pseudo() throws RecognitionException {
        CSSParser.pseudo_return retval = new CSSParser.pseudo_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON145=null;
        Token IDENT146=null;
        Token FUNCTION147=null;
        Token S148=null;
        Token IDENT149=null;
        Token S150=null;
        Token RPAREN151=null;

        Object COLON145_tree=null;
        Object IDENT146_tree=null;
        Object FUNCTION147_tree=null;
        Object S148_tree=null;
        Object IDENT149_tree=null;
        Object S150_tree=null;
        Object RPAREN151_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_FUNCTION=new RewriteRuleTokenStream(adaptor,"token FUNCTION");

        try {
            // CSS.g:648:2: ( COLON ( IDENT | FUNCTION ( S )* IDENT ( S )* RPAREN ) -> ^( PSEUDO ( FUNCTION )? IDENT ) )
            // CSS.g:648:4: COLON ( IDENT | FUNCTION ( S )* IDENT ( S )* RPAREN )
            {
            COLON145=(Token)match(input,COLON,FOLLOW_COLON_in_pseudo1468);  
            stream_COLON.add(COLON145);

            // CSS.g:648:10: ( IDENT | FUNCTION ( S )* IDENT ( S )* RPAREN )
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==IDENT) ) {
                alt72=1;
            }
            else if ( (LA72_0==FUNCTION) ) {
                alt72=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;
            }
            switch (alt72) {
                case 1 :
                    // CSS.g:648:11: IDENT
                    {
                    IDENT146=(Token)match(input,IDENT,FOLLOW_IDENT_in_pseudo1471);  
                    stream_IDENT.add(IDENT146);


                    }
                    break;
                case 2 :
                    // CSS.g:648:19: FUNCTION ( S )* IDENT ( S )* RPAREN
                    {
                    FUNCTION147=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_pseudo1475);  
                    stream_FUNCTION.add(FUNCTION147);

                    // CSS.g:648:28: ( S )*
                    loop70:
                    do {
                        int alt70=2;
                        int LA70_0 = input.LA(1);

                        if ( (LA70_0==S) ) {
                            alt70=1;
                        }


                        switch (alt70) {
                    	case 1 :
                    	    // CSS.g:648:28: S
                    	    {
                    	    S148=(Token)match(input,S,FOLLOW_S_in_pseudo1477);  
                    	    stream_S.add(S148);


                    	    }
                    	    break;

                    	default :
                    	    break loop70;
                        }
                    } while (true);

                    IDENT149=(Token)match(input,IDENT,FOLLOW_IDENT_in_pseudo1481);  
                    stream_IDENT.add(IDENT149);

                    // CSS.g:648:38: ( S )*
                    loop71:
                    do {
                        int alt71=2;
                        int LA71_0 = input.LA(1);

                        if ( (LA71_0==S) ) {
                            alt71=1;
                        }


                        switch (alt71) {
                    	case 1 :
                    	    // CSS.g:648:38: S
                    	    {
                    	    S150=(Token)match(input,S,FOLLOW_S_in_pseudo1483);  
                    	    stream_S.add(S150);


                    	    }
                    	    break;

                    	default :
                    	    break loop71;
                        }
                    } while (true);

                    RPAREN151=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_pseudo1486);  
                    stream_RPAREN.add(RPAREN151);


                    }
                    break;

            }



            // AST REWRITE
            // elements: IDENT, FUNCTION
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 649:3: -> ^( PSEUDO ( FUNCTION )? IDENT )
            {
                // CSS.g:649:6: ^( PSEUDO ( FUNCTION )? IDENT )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PSEUDO, "PSEUDO"), root_1);

                // CSS.g:649:15: ( FUNCTION )?
                if ( stream_FUNCTION.hasNext() ) {
                    adaptor.addChild(root_1, stream_FUNCTION.nextNode());

                }
                stream_FUNCTION.reset();
                adaptor.addChild(root_1, stream_IDENT.nextNode());

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
    // $ANTLR end "pseudo"

    public static class string_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "string"
    // CSS.g:652:1: string : ( STRING | INVALID_STRING );
    public final CSSParser.string_return string() throws RecognitionException {
        CSSParser.string_return retval = new CSSParser.string_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set152=null;

        Object set152_tree=null;

        try {
            // CSS.g:653:2: ( STRING | INVALID_STRING )
            // CSS.g:
            {
            root_0 = (Object)adaptor.nil();

            set152=(Token)input.LT(1);
            if ( input.LA(1)==INVALID_STRING||input.LA(1)==STRING ) {
                input.consume();
                adaptor.addChild(root_0, (Object)adaptor.create(set152));
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
    // CSS.g:658:1: any : ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) ) ( S )* ;
    public final CSSParser.any_return any() throws RecognitionException {
        CSSParser.any_return retval = new CSSParser.any_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT153=null;
        Token CLASSKEYWORD154=null;
        Token NUMBER155=null;
        Token PERCENTAGE156=null;
        Token DIMENSION157=null;
        Token URI159=null;
        Token HASH160=null;
        Token UNIRANGE161=null;
        Token INCLUDES162=null;
        Token COLON163=null;
        Token COMMA164=null;
        Token GREATER165=null;
        Token EQUALS166=null;
        Token SLASH167=null;
        Token EXCLAMATION168=null;
        Token MINUS169=null;
        Token PLUS170=null;
        Token ASTERISK171=null;
        Token FUNCTION172=null;
        Token S173=null;
        Token RPAREN175=null;
        Token DASHMATCH176=null;
        Token LPAREN177=null;
        Token RPAREN179=null;
        Token LBRACE180=null;
        Token RBRACE182=null;
        Token S183=null;
        CSSParser.string_return string158 = null;

        CSSParser.any_return any174 = null;

        CSSParser.any_return any178 = null;

        CSSParser.any_return any181 = null;


        Object IDENT153_tree=null;
        Object CLASSKEYWORD154_tree=null;
        Object NUMBER155_tree=null;
        Object PERCENTAGE156_tree=null;
        Object DIMENSION157_tree=null;
        Object URI159_tree=null;
        Object HASH160_tree=null;
        Object UNIRANGE161_tree=null;
        Object INCLUDES162_tree=null;
        Object COLON163_tree=null;
        Object COMMA164_tree=null;
        Object GREATER165_tree=null;
        Object EQUALS166_tree=null;
        Object SLASH167_tree=null;
        Object EXCLAMATION168_tree=null;
        Object MINUS169_tree=null;
        Object PLUS170_tree=null;
        Object ASTERISK171_tree=null;
        Object FUNCTION172_tree=null;
        Object S173_tree=null;
        Object RPAREN175_tree=null;
        Object DASHMATCH176_tree=null;
        Object LPAREN177_tree=null;
        Object RPAREN179_tree=null;
        Object LBRACE180_tree=null;
        Object RBRACE182_tree=null;
        Object S183_tree=null;
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
            // CSS.g:659:2: ( ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) ) ( S )* )
            // CSS.g:659:4: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) ) ( S )*
            {
            // CSS.g:659:4: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) )
            int alt77=23;
            alt77 = dfa77.predict(input);
            switch (alt77) {
                case 1 :
                    // CSS.g:659:6: IDENT
                    {
                    IDENT153=(Token)match(input,IDENT,FOLLOW_IDENT_in_any1532);  
                    stream_IDENT.add(IDENT153);



                    // AST REWRITE
                    // elements: IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 659:12: -> IDENT
                    {
                        adaptor.addChild(root_0, stream_IDENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // CSS.g:660:6: CLASSKEYWORD
                    {
                    CLASSKEYWORD154=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_any1543);  
                    stream_CLASSKEYWORD.add(CLASSKEYWORD154);



                    // AST REWRITE
                    // elements: CLASSKEYWORD
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 660:19: -> CLASSKEYWORD
                    {
                        adaptor.addChild(root_0, stream_CLASSKEYWORD.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // CSS.g:661:6: NUMBER
                    {
                    NUMBER155=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_any1554);  
                    stream_NUMBER.add(NUMBER155);



                    // AST REWRITE
                    // elements: NUMBER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 661:13: -> NUMBER
                    {
                        adaptor.addChild(root_0, stream_NUMBER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // CSS.g:662:6: PERCENTAGE
                    {
                    PERCENTAGE156=(Token)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_any1565);  
                    stream_PERCENTAGE.add(PERCENTAGE156);



                    // AST REWRITE
                    // elements: PERCENTAGE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 662:17: -> PERCENTAGE
                    {
                        adaptor.addChild(root_0, stream_PERCENTAGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // CSS.g:663:6: DIMENSION
                    {
                    DIMENSION157=(Token)match(input,DIMENSION,FOLLOW_DIMENSION_in_any1575);  
                    stream_DIMENSION.add(DIMENSION157);



                    // AST REWRITE
                    // elements: DIMENSION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 663:16: -> DIMENSION
                    {
                        adaptor.addChild(root_0, stream_DIMENSION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // CSS.g:664:6: string
                    {
                    pushFollow(FOLLOW_string_in_any1586);
                    string158=string();

                    state._fsp--;

                    stream_string.add(string158.getTree());


                    // AST REWRITE
                    // elements: string
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 664:13: -> string
                    {
                        adaptor.addChild(root_0, stream_string.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // CSS.g:665:9: URI
                    {
                    URI159=(Token)match(input,URI,FOLLOW_URI_in_any1600);  
                    stream_URI.add(URI159);



                    // AST REWRITE
                    // elements: URI
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 665:16: -> URI
                    {
                        adaptor.addChild(root_0, stream_URI.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // CSS.g:666:9: HASH
                    {
                    HASH160=(Token)match(input,HASH,FOLLOW_HASH_in_any1617);  
                    stream_HASH.add(HASH160);



                    // AST REWRITE
                    // elements: HASH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 666:14: -> HASH
                    {
                        adaptor.addChild(root_0, stream_HASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // CSS.g:667:9: UNIRANGE
                    {
                    UNIRANGE161=(Token)match(input,UNIRANGE,FOLLOW_UNIRANGE_in_any1631);  
                    stream_UNIRANGE.add(UNIRANGE161);



                    // AST REWRITE
                    // elements: UNIRANGE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 667:18: -> UNIRANGE
                    {
                        adaptor.addChild(root_0, stream_UNIRANGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // CSS.g:668:9: INCLUDES
                    {
                    INCLUDES162=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_any1645);  
                    stream_INCLUDES.add(INCLUDES162);



                    // AST REWRITE
                    // elements: INCLUDES
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 668:18: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // CSS.g:669:9: COLON
                    {
                    COLON163=(Token)match(input,COLON,FOLLOW_COLON_in_any1659);  
                    stream_COLON.add(COLON163);



                    // AST REWRITE
                    // elements: COLON
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 669:15: -> COLON
                    {
                        adaptor.addChild(root_0, stream_COLON.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // CSS.g:670:9: COMMA
                    {
                    COMMA164=(Token)match(input,COMMA,FOLLOW_COMMA_in_any1673);  
                    stream_COMMA.add(COMMA164);



                    // AST REWRITE
                    // elements: COMMA
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 670:15: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // CSS.g:671:9: GREATER
                    {
                    GREATER165=(Token)match(input,GREATER,FOLLOW_GREATER_in_any1687);  
                    stream_GREATER.add(GREATER165);



                    // AST REWRITE
                    // elements: GREATER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 671:17: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // CSS.g:672:9: EQUALS
                    {
                    EQUALS166=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_any1701);  
                    stream_EQUALS.add(EQUALS166);



                    // AST REWRITE
                    // elements: EQUALS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 672:16: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // CSS.g:673:9: SLASH
                    {
                    SLASH167=(Token)match(input,SLASH,FOLLOW_SLASH_in_any1715);  
                    stream_SLASH.add(SLASH167);



                    // AST REWRITE
                    // elements: SLASH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 673:15: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // CSS.g:674:9: EXCLAMATION
                    {
                    EXCLAMATION168=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_any1729);  
                    stream_EXCLAMATION.add(EXCLAMATION168);



                    // AST REWRITE
                    // elements: EXCLAMATION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 674:21: -> EXCLAMATION
                    {
                        adaptor.addChild(root_0, stream_EXCLAMATION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // CSS.g:675:6: MINUS
                    {
                    MINUS169=(Token)match(input,MINUS,FOLLOW_MINUS_in_any1740);  
                    stream_MINUS.add(MINUS169);



                    // AST REWRITE
                    // elements: MINUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 675:12: -> MINUS
                    {
                        adaptor.addChild(root_0, stream_MINUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 18 :
                    // CSS.g:676:6: PLUS
                    {
                    PLUS170=(Token)match(input,PLUS,FOLLOW_PLUS_in_any1751);  
                    stream_PLUS.add(PLUS170);



                    // AST REWRITE
                    // elements: PLUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 676:11: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 19 :
                    // CSS.g:677:6: ASTERISK
                    {
                    ASTERISK171=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_any1762);  
                    stream_ASTERISK.add(ASTERISK171);



                    // AST REWRITE
                    // elements: ASTERISK
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 677:15: -> ASTERISK
                    {
                        adaptor.addChild(root_0, stream_ASTERISK.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 20 :
                    // CSS.g:678:9: FUNCTION ( S )* ( any )* RPAREN
                    {
                    FUNCTION172=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_any1779);  
                    stream_FUNCTION.add(FUNCTION172);

                    // CSS.g:678:18: ( S )*
                    loop73:
                    do {
                        int alt73=2;
                        alt73 = dfa73.predict(input);
                        switch (alt73) {
                    	case 1 :
                    	    // CSS.g:678:18: S
                    	    {
                    	    S173=(Token)match(input,S,FOLLOW_S_in_any1781);  
                    	    stream_S.add(S173);


                    	    }
                    	    break;

                    	default :
                    	    break loop73;
                        }
                    } while (true);

                    // CSS.g:678:21: ( any )*
                    loop74:
                    do {
                        int alt74=2;
                        alt74 = dfa74.predict(input);
                        switch (alt74) {
                    	case 1 :
                    	    // CSS.g:678:21: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_any1784);
                    	    any174=any();

                    	    state._fsp--;

                    	    stream_any.add(any174.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop74;
                        }
                    } while (true);

                    RPAREN175=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_any1787);  
                    stream_RPAREN.add(RPAREN175);



                    // AST REWRITE
                    // elements: any, FUNCTION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 678:33: -> ^( FUNCTION ( any )* )
                    {
                        // CSS.g:678:36: ^( FUNCTION ( any )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_FUNCTION.nextNode(), root_1);

                        // CSS.g:678:47: ( any )*
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
                    // CSS.g:679:9: DASHMATCH
                    {
                    DASHMATCH176=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_any1807);  
                    stream_DASHMATCH.add(DASHMATCH176);



                    // AST REWRITE
                    // elements: DASHMATCH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 679:19: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 22 :
                    // CSS.g:680:9: LPAREN ( any )* RPAREN
                    {
                    LPAREN177=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_any1821);  
                    stream_LPAREN.add(LPAREN177);

                    // CSS.g:680:16: ( any )*
                    loop75:
                    do {
                        int alt75=2;
                        alt75 = dfa75.predict(input);
                        switch (alt75) {
                    	case 1 :
                    	    // CSS.g:680:16: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_any1823);
                    	    any178=any();

                    	    state._fsp--;

                    	    stream_any.add(any178.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop75;
                        }
                    } while (true);

                    RPAREN179=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_any1826);  
                    stream_RPAREN.add(RPAREN179);



                    // AST REWRITE
                    // elements: any
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 680:28: -> ^( PARENBLOCK ( any )* )
                    {
                        // CSS.g:680:31: ^( PARENBLOCK ( any )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PARENBLOCK, "PARENBLOCK"), root_1);

                        // CSS.g:680:44: ( any )*
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
                    // CSS.g:681:9: LBRACE ( any )* RBRACE
                    {
                    LBRACE180=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_any1845);  
                    stream_LBRACE.add(LBRACE180);

                    // CSS.g:681:16: ( any )*
                    loop76:
                    do {
                        int alt76=2;
                        alt76 = dfa76.predict(input);
                        switch (alt76) {
                    	case 1 :
                    	    // CSS.g:681:16: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_any1847);
                    	    any181=any();

                    	    state._fsp--;

                    	    stream_any.add(any181.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop76;
                        }
                    } while (true);

                    RBRACE182=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_any1850);  
                    stream_RBRACE.add(RBRACE182);



                    // AST REWRITE
                    // elements: any
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 681:28: -> ^( BRACEBLOCK ( any )* )
                    {
                        // CSS.g:681:31: ^( BRACEBLOCK ( any )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BRACEBLOCK, "BRACEBLOCK"), root_1);

                        // CSS.g:681:44: ( any )*
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

            // CSS.g:682:8: ( S )*
            loop78:
            do {
                int alt78=2;
                alt78 = dfa78.predict(input);
                switch (alt78) {
            	case 1 :
            	    // CSS.g:682:8: S
            	    {
            	    S183=(Token)match(input,S,FOLLOW_S_in_any1868);  
            	    stream_S.add(S183);


            	    }
            	    break;

            	default :
            	    break loop78;
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


    protected DFA4 dfa4 = new DFA4(this);
    protected DFA5 dfa5 = new DFA5(this);
    protected DFA12 dfa12 = new DFA12(this);
    protected DFA13 dfa13 = new DFA13(this);
    protected DFA16 dfa16 = new DFA16(this);
    protected DFA34 dfa34 = new DFA34(this);
    protected DFA39 dfa39 = new DFA39(this);
    protected DFA44 dfa44 = new DFA44(this);
    protected DFA40 dfa40 = new DFA40(this);
    protected DFA42 dfa42 = new DFA42(this);
    protected DFA41 dfa41 = new DFA41(this);
    protected DFA43 dfa43 = new DFA43(this);
    protected DFA51 dfa51 = new DFA51(this);
    protected DFA48 dfa48 = new DFA48(this);
    protected DFA49 dfa49 = new DFA49(this);
    protected DFA50 dfa50 = new DFA50(this);
    protected DFA52 dfa52 = new DFA52(this);
    protected DFA58 dfa58 = new DFA58(this);
    protected DFA59 dfa59 = new DFA59(this);
    protected DFA60 dfa60 = new DFA60(this);
    protected DFA61 dfa61 = new DFA61(this);
    protected DFA77 dfa77 = new DFA77(this);
    protected DFA73 dfa73 = new DFA73(this);
    protected DFA74 dfa74 = new DFA74(this);
    protected DFA75 dfa75 = new DFA75(this);
    protected DFA76 dfa76 = new DFA76(this);
    protected DFA78 dfa78 = new DFA78(this);
    static final String DFA4_eotS =
        "\23\uffff";
    static final String DFA4_eofS =
        "\1\1\22\uffff";
    static final String DFA4_minS =
        "\1\26\22\uffff";
    static final String DFA4_maxS =
        "\1\76\22\uffff";
    static final String DFA4_acceptS =
        "\1\uffff\1\5\1\1\1\2\1\3\1\4\15\uffff";
    static final String DFA4_specialS =
        "\23\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\5\2\uffff\1\5\2\uffff\1\5\1\4\1\2\1\3\5\5\2\uffff\2\5\3\uffff"+
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

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "()* loopback of 500:4: ( CDO | CDC | S | statement )*";
        }
    }
    static final String DFA5_eotS =
        "\17\uffff";
    static final String DFA5_eofS =
        "\17\uffff";
    static final String DFA5_minS =
        "\1\26\16\uffff";
    static final String DFA5_maxS =
        "\1\76\16\uffff";
    static final String DFA5_acceptS =
        "\1\uffff\1\1\6\uffff\1\2\6\uffff";
    static final String DFA5_specialS =
        "\17\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\10\2\uffff\1\1\2\uffff\1\10\3\uffff\3\10\2\1\2\uffff\2\10"+
            "\3\uffff\1\1\5\uffff\1\1\6\uffff\1\1\4\uffff\1\1",
            "",
            "",
            "",
            "",
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

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "504:1: statement : ( ruleset | atstatement );";
        }
    }
    static final String DFA12_eotS =
        "\12\uffff";
    static final String DFA12_eofS =
        "\12\uffff";
    static final String DFA12_minS =
        "\1\31\11\uffff";
    static final String DFA12_maxS =
        "\1\76\11\uffff";
    static final String DFA12_acceptS =
        "\1\uffff\1\2\7\uffff\1\1";
    static final String DFA12_specialS =
        "\12\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\1\3\uffff\1\11\5\uffff\2\1\1\uffff\1\1\5\uffff\1\1\5\uffff"+
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

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "()* loopback of 517:10: ( S )*";
        }
    }
    static final String DFA13_eotS =
        "\12\uffff";
    static final String DFA13_eofS =
        "\12\uffff";
    static final String DFA13_minS =
        "\1\31\11\uffff";
    static final String DFA13_maxS =
        "\1\76\11\uffff";
    static final String DFA13_acceptS =
        "\1\uffff\1\2\7\uffff\1\1";
    static final String DFA13_specialS =
        "\12\uffff}>";
    static final String[] DFA13_transitionS = {
            "\1\1\3\uffff\1\11\5\uffff\2\1\1\uffff\1\1\5\uffff\1\1\5\uffff"+
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
            return "()* loopback of 517:22: ( S )*";
        }
    }
    static final String DFA16_eotS =
        "\31\uffff";
    static final String DFA16_eofS =
        "\31\uffff";
    static final String DFA16_minS =
        "\1\27\30\uffff";
    static final String DFA16_maxS =
        "\1\100\30\uffff";
    static final String DFA16_acceptS =
        "\1\uffff\1\2\1\1\26\uffff";
    static final String DFA16_specialS =
        "\31\uffff}>";
    static final String[] DFA16_transitionS = {
            "\1\2\13\uffff\2\2\1\uffff\1\1\2\uffff\1\2\1\uffff\20\2\1\uffff"+
            "\3\2\1\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA16_eot = DFA.unpackEncodedString(DFA16_eotS);
    static final short[] DFA16_eof = DFA.unpackEncodedString(DFA16_eofS);
    static final char[] DFA16_min = DFA.unpackEncodedStringToUnsignedChars(DFA16_minS);
    static final char[] DFA16_max = DFA.unpackEncodedStringToUnsignedChars(DFA16_maxS);
    static final short[] DFA16_accept = DFA.unpackEncodedString(DFA16_acceptS);
    static final short[] DFA16_special = DFA.unpackEncodedString(DFA16_specialS);
    static final short[][] DFA16_transition;

    static {
        int numStates = DFA16_transitionS.length;
        DFA16_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA16_transition[i] = DFA.unpackEncodedString(DFA16_transitionS[i]);
        }
    }

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = DFA16_eot;
            this.eof = DFA16_eof;
            this.min = DFA16_min;
            this.max = DFA16_max;
            this.accept = DFA16_accept;
            this.special = DFA16_special;
            this.transition = DFA16_transition;
        }
        public String getDescription() {
            return "()* loopback of 518:24: ( any )*";
        }
    }
    static final String DFA34_eotS =
        "\32\uffff";
    static final String DFA34_eofS =
        "\32\uffff";
    static final String DFA34_minS =
        "\1\27\31\uffff";
    static final String DFA34_maxS =
        "\1\100\31\uffff";
    static final String DFA34_acceptS =
        "\1\uffff\1\2\27\uffff\1\1";
    static final String DFA34_specialS =
        "\32\uffff}>";
    static final String[] DFA34_transitionS = {
            "\1\1\5\uffff\1\31\5\uffff\3\1\2\uffff\2\1\2\uffff\17\1\1\uffff"+
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

    static final short[] DFA34_eot = DFA.unpackEncodedString(DFA34_eotS);
    static final short[] DFA34_eof = DFA.unpackEncodedString(DFA34_eofS);
    static final char[] DFA34_min = DFA.unpackEncodedStringToUnsignedChars(DFA34_minS);
    static final char[] DFA34_max = DFA.unpackEncodedStringToUnsignedChars(DFA34_maxS);
    static final short[] DFA34_accept = DFA.unpackEncodedString(DFA34_acceptS);
    static final short[] DFA34_special = DFA.unpackEncodedString(DFA34_specialS);
    static final short[][] DFA34_transition;

    static {
        int numStates = DFA34_transitionS.length;
        DFA34_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA34_transition[i] = DFA.unpackEncodedString(DFA34_transitionS[i]);
        }
    }

    class DFA34 extends DFA {

        public DFA34(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 34;
            this.eot = DFA34_eot;
            this.eof = DFA34_eof;
            this.min = DFA34_min;
            this.max = DFA34_max;
            this.accept = DFA34_accept;
            this.special = DFA34_special;
            this.transition = DFA34_transition;
        }
        public String getDescription() {
            return "()* loopback of 555:19: ( S )*";
        }
    }
    static final String DFA39_eotS =
        "\36\uffff";
    static final String DFA39_eofS =
        "\1\1\35\uffff";
    static final String DFA39_minS =
        "\1\27\35\uffff";
    static final String DFA39_maxS =
        "\1\100\35\uffff";
    static final String DFA39_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\27\uffff";
    static final String DFA39_specialS =
        "\36\uffff}>";
    static final String[] DFA39_transitionS = {
            "\1\6\13\uffff\3\6\1\1\1\uffff\2\6\2\1\17\6\1\1\3\6\1\uffff\1"+
            "\6",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()+ loopback of 570:4: ( term )+";
        }
    }
    static final String DFA44_eotS =
        "\31\uffff";
    static final String DFA44_eofS =
        "\31\uffff";
    static final String DFA44_minS =
        "\1\27\30\uffff";
    static final String DFA44_maxS =
        "\1\100\30\uffff";
    static final String DFA44_acceptS =
        "\1\uffff\1\1\25\uffff\1\2\1\3";
    static final String DFA44_specialS =
        "\31\uffff}>";
    static final String[] DFA44_transitionS = {
            "\1\1\13\uffff\2\1\1\27\2\uffff\1\30\1\1\2\uffff\17\1\1\uffff"+
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
            return "574:1: term : ( valuepart -> valuepart | LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY -> CURLYBLOCK | ATKEYWORD ( S )* -> ATKEYWORD );";
        }
    }
    static final String DFA40_eotS =
        "\33\uffff";
    static final String DFA40_eofS =
        "\33\uffff";
    static final String DFA40_minS =
        "\1\27\32\uffff";
    static final String DFA40_maxS =
        "\1\100\32\uffff";
    static final String DFA40_acceptS =
        "\1\uffff\1\2\30\uffff\1\1";
    static final String DFA40_specialS =
        "\33\uffff}>";
    static final String[] DFA40_transitionS = {
            "\1\1\5\uffff\1\32\5\uffff\2\1\1\uffff\1\1\2\uffff\22\1\1\uffff"+
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
            return "()* loopback of 576:14: ( S )*";
        }
    }
    static final String DFA42_eotS =
        "\32\uffff";
    static final String DFA42_eofS =
        "\32\uffff";
    static final String DFA42_minS =
        "\1\27\31\uffff";
    static final String DFA42_maxS =
        "\1\100\31\uffff";
    static final String DFA42_acceptS =
        "\1\uffff\1\3\1\1\26\uffff\1\2";
    static final String DFA42_specialS =
        "\32\uffff}>";
    static final String[] DFA42_transitionS = {
            "\1\2\13\uffff\2\2\1\uffff\1\1\2\uffff\1\2\1\31\20\2\1\uffff"+
            "\3\2\1\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA42_eot = DFA.unpackEncodedString(DFA42_eotS);
    static final short[] DFA42_eof = DFA.unpackEncodedString(DFA42_eofS);
    static final char[] DFA42_min = DFA.unpackEncodedStringToUnsignedChars(DFA42_minS);
    static final char[] DFA42_max = DFA.unpackEncodedStringToUnsignedChars(DFA42_maxS);
    static final short[] DFA42_accept = DFA.unpackEncodedString(DFA42_acceptS);
    static final short[] DFA42_special = DFA.unpackEncodedString(DFA42_specialS);
    static final short[][] DFA42_transition;

    static {
        int numStates = DFA42_transitionS.length;
        DFA42_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA42_transition[i] = DFA.unpackEncodedString(DFA42_transitionS[i]);
        }
    }

    class DFA42 extends DFA {

        public DFA42(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 42;
            this.eot = DFA42_eot;
            this.eof = DFA42_eof;
            this.min = DFA42_min;
            this.max = DFA42_max;
            this.accept = DFA42_accept;
            this.special = DFA42_special;
            this.transition = DFA42_transition;
        }
        public String getDescription() {
            return "()* loopback of 576:17: ( any | SEMICOLON ( S )* )*";
        }
    }
    static final String DFA41_eotS =
        "\33\uffff";
    static final String DFA41_eofS =
        "\33\uffff";
    static final String DFA41_minS =
        "\1\27\32\uffff";
    static final String DFA41_maxS =
        "\1\100\32\uffff";
    static final String DFA41_acceptS =
        "\1\uffff\1\2\30\uffff\1\1";
    static final String DFA41_specialS =
        "\33\uffff}>";
    static final String[] DFA41_transitionS = {
            "\1\1\5\uffff\1\32\5\uffff\2\1\1\uffff\1\1\2\uffff\22\1\1\uffff"+
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

    static final short[] DFA41_eot = DFA.unpackEncodedString(DFA41_eotS);
    static final short[] DFA41_eof = DFA.unpackEncodedString(DFA41_eofS);
    static final char[] DFA41_min = DFA.unpackEncodedStringToUnsignedChars(DFA41_minS);
    static final char[] DFA41_max = DFA.unpackEncodedStringToUnsignedChars(DFA41_maxS);
    static final short[] DFA41_accept = DFA.unpackEncodedString(DFA41_acceptS);
    static final short[] DFA41_special = DFA.unpackEncodedString(DFA41_specialS);
    static final short[][] DFA41_transition;

    static {
        int numStates = DFA41_transitionS.length;
        DFA41_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA41_transition[i] = DFA.unpackEncodedString(DFA41_transitionS[i]);
        }
    }

    class DFA41 extends DFA {

        public DFA41(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 41;
            this.eot = DFA41_eot;
            this.eof = DFA41_eof;
            this.min = DFA41_min;
            this.max = DFA41_max;
            this.accept = DFA41_accept;
            this.special = DFA41_special;
            this.transition = DFA41_transition;
        }
        public String getDescription() {
            return "()* loopback of 576:34: ( S )*";
        }
    }
    static final String DFA43_eotS =
        "\37\uffff";
    static final String DFA43_eofS =
        "\1\1\36\uffff";
    static final String DFA43_minS =
        "\1\27\36\uffff";
    static final String DFA43_maxS =
        "\1\100\36\uffff";
    static final String DFA43_acceptS =
        "\1\uffff\1\2\34\uffff\1\1";
    static final String DFA43_specialS =
        "\37\uffff}>";
    static final String[] DFA43_transitionS = {
            "\1\1\5\uffff\1\36\5\uffff\4\1\1\uffff\27\1\1\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA43_eot = DFA.unpackEncodedString(DFA43_eotS);
    static final short[] DFA43_eof = DFA.unpackEncodedString(DFA43_eofS);
    static final char[] DFA43_min = DFA.unpackEncodedStringToUnsignedChars(DFA43_minS);
    static final char[] DFA43_max = DFA.unpackEncodedStringToUnsignedChars(DFA43_maxS);
    static final short[] DFA43_accept = DFA.unpackEncodedString(DFA43_acceptS);
    static final short[] DFA43_special = DFA.unpackEncodedString(DFA43_specialS);
    static final short[][] DFA43_transition;

    static {
        int numStates = DFA43_transitionS.length;
        DFA43_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA43_transition[i] = DFA.unpackEncodedString(DFA43_transitionS[i]);
        }
    }

    class DFA43 extends DFA {

        public DFA43(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 43;
            this.eot = DFA43_eot;
            this.eof = DFA43_eof;
            this.min = DFA43_min;
            this.max = DFA43_max;
            this.accept = DFA43_accept;
            this.special = DFA43_special;
            this.transition = DFA43_transition;
        }
        public String getDescription() {
            return "()* loopback of 577:17: ( S )*";
        }
    }
    static final String DFA51_eotS =
        "\32\uffff";
    static final String DFA51_eofS =
        "\32\uffff";
    static final String DFA51_minS =
        "\1\27\2\uffff\1\56\26\uffff";
    static final String DFA51_maxS =
        "\1\100\2\uffff\1\60\26\uffff";
    static final String DFA51_acceptS =
        "\1\uffff\1\1\1\2\1\uffff\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13"+
        "\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\3\uffff";
    static final String DFA51_specialS =
        "\32\uffff}>";
    static final String[] DFA51_transitionS = {
            "\1\7\13\uffff\1\14\1\1\4\uffff\1\15\2\uffff\1\2\1\3\1\4\1\5"+
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

    static final short[] DFA51_eot = DFA.unpackEncodedString(DFA51_eotS);
    static final short[] DFA51_eof = DFA.unpackEncodedString(DFA51_eofS);
    static final char[] DFA51_min = DFA.unpackEncodedStringToUnsignedChars(DFA51_minS);
    static final char[] DFA51_max = DFA.unpackEncodedStringToUnsignedChars(DFA51_maxS);
    static final short[] DFA51_accept = DFA.unpackEncodedString(DFA51_acceptS);
    static final short[] DFA51_special = DFA.unpackEncodedString(DFA51_specialS);
    static final short[][] DFA51_transition;

    static {
        int numStates = DFA51_transitionS.length;
        DFA51_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA51_transition[i] = DFA.unpackEncodedString(DFA51_transitionS[i]);
        }
    }

    class DFA51 extends DFA {

        public DFA51(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 51;
            this.eot = DFA51_eot;
            this.eof = DFA51_eof;
            this.min = DFA51_min;
            this.max = DFA51_max;
            this.accept = DFA51_accept;
            this.special = DFA51_special;
            this.transition = DFA51_transition;
        }
        public String getDescription() {
            return "581:7: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* terms RPAREN -> ^( FUNCTION terms ) | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) )";
        }
    }
    static final String DFA48_eotS =
        "\32\uffff";
    static final String DFA48_eofS =
        "\32\uffff";
    static final String DFA48_minS =
        "\1\27\31\uffff";
    static final String DFA48_maxS =
        "\1\100\31\uffff";
    static final String DFA48_acceptS =
        "\1\uffff\1\2\27\uffff\1\1";
    static final String DFA48_specialS =
        "\32\uffff}>";
    static final String[] DFA48_transitionS = {
            "\1\1\5\uffff\1\31\5\uffff\3\1\2\uffff\2\1\2\uffff\17\1\1\uffff"+
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
            return "()* loopback of 598:18: ( S )*";
        }
    }
    static final String DFA49_eotS =
        "\30\uffff";
    static final String DFA49_eofS =
        "\30\uffff";
    static final String DFA49_minS =
        "\1\27\27\uffff";
    static final String DFA49_maxS =
        "\1\100\27\uffff";
    static final String DFA49_acceptS =
        "\1\uffff\1\2\1\1\25\uffff";
    static final String DFA49_specialS =
        "\30\uffff}>";
    static final String[] DFA49_transitionS = {
            "\1\2\13\uffff\2\2\4\uffff\1\2\2\uffff\17\2\1\1\3\2\1\uffff\1"+
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

    static final short[] DFA49_eot = DFA.unpackEncodedString(DFA49_eotS);
    static final short[] DFA49_eof = DFA.unpackEncodedString(DFA49_eofS);
    static final char[] DFA49_min = DFA.unpackEncodedStringToUnsignedChars(DFA49_minS);
    static final char[] DFA49_max = DFA.unpackEncodedStringToUnsignedChars(DFA49_maxS);
    static final short[] DFA49_accept = DFA.unpackEncodedString(DFA49_acceptS);
    static final short[] DFA49_special = DFA.unpackEncodedString(DFA49_specialS);
    static final short[][] DFA49_transition;

    static {
        int numStates = DFA49_transitionS.length;
        DFA49_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA49_transition[i] = DFA.unpackEncodedString(DFA49_transitionS[i]);
        }
    }

    class DFA49 extends DFA {

        public DFA49(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 49;
            this.eot = DFA49_eot;
            this.eof = DFA49_eof;
            this.min = DFA49_min;
            this.max = DFA49_max;
            this.accept = DFA49_accept;
            this.special = DFA49_special;
            this.transition = DFA49_transition;
        }
        public String getDescription() {
            return "()* loopback of 600:16: ( valuepart )*";
        }
    }
    static final String DFA50_eotS =
        "\30\uffff";
    static final String DFA50_eofS =
        "\30\uffff";
    static final String DFA50_minS =
        "\1\27\27\uffff";
    static final String DFA50_maxS =
        "\1\100\27\uffff";
    static final String DFA50_acceptS =
        "\1\uffff\1\2\1\1\25\uffff";
    static final String DFA50_specialS =
        "\30\uffff}>";
    static final String[] DFA50_transitionS = {
            "\1\2\13\uffff\2\2\4\uffff\1\2\2\uffff\17\2\1\uffff\3\2\1\1\1"+
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

    static final short[] DFA50_eot = DFA.unpackEncodedString(DFA50_eotS);
    static final short[] DFA50_eof = DFA.unpackEncodedString(DFA50_eofS);
    static final char[] DFA50_min = DFA.unpackEncodedStringToUnsignedChars(DFA50_minS);
    static final char[] DFA50_max = DFA.unpackEncodedStringToUnsignedChars(DFA50_maxS);
    static final short[] DFA50_accept = DFA.unpackEncodedString(DFA50_acceptS);
    static final short[] DFA50_special = DFA.unpackEncodedString(DFA50_specialS);
    static final short[][] DFA50_transition;

    static {
        int numStates = DFA50_transitionS.length;
        DFA50_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA50_transition[i] = DFA.unpackEncodedString(DFA50_transitionS[i]);
        }
    }

    class DFA50 extends DFA {

        public DFA50(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 50;
            this.eot = DFA50_eot;
            this.eof = DFA50_eof;
            this.min = DFA50_min;
            this.max = DFA50_max;
            this.accept = DFA50_accept;
            this.special = DFA50_special;
            this.transition = DFA50_transition;
        }
        public String getDescription() {
            return "()* loopback of 601:16: ( valuepart )*";
        }
    }
    static final String DFA52_eotS =
        "\40\uffff";
    static final String DFA52_eofS =
        "\1\1\37\uffff";
    static final String DFA52_minS =
        "\1\27\37\uffff";
    static final String DFA52_maxS =
        "\1\100\37\uffff";
    static final String DFA52_acceptS =
        "\1\uffff\1\2\35\uffff\1\1";
    static final String DFA52_specialS =
        "\40\uffff}>";
    static final String[] DFA52_transitionS = {
            "\1\1\5\uffff\1\37\5\uffff\4\1\1\uffff\31\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA52_eot = DFA.unpackEncodedString(DFA52_eotS);
    static final short[] DFA52_eof = DFA.unpackEncodedString(DFA52_eofS);
    static final char[] DFA52_min = DFA.unpackEncodedStringToUnsignedChars(DFA52_minS);
    static final char[] DFA52_max = DFA.unpackEncodedStringToUnsignedChars(DFA52_maxS);
    static final short[] DFA52_accept = DFA.unpackEncodedString(DFA52_acceptS);
    static final short[] DFA52_special = DFA.unpackEncodedString(DFA52_specialS);
    static final short[][] DFA52_transition;

    static {
        int numStates = DFA52_transitionS.length;
        DFA52_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA52_transition[i] = DFA.unpackEncodedString(DFA52_transitionS[i]);
        }
    }

    class DFA52 extends DFA {

        public DFA52(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 52;
            this.eot = DFA52_eot;
            this.eof = DFA52_eof;
            this.min = DFA52_min;
            this.max = DFA52_max;
            this.accept = DFA52_accept;
            this.special = DFA52_special;
            this.transition = DFA52_transition;
        }
        public String getDescription() {
            return "()* loopback of 602:8: ( S )*";
        }
    }
    static final String DFA58_eotS =
        "\13\uffff";
    static final String DFA58_eofS =
        "\13\uffff";
    static final String DFA58_minS =
        "\1\31\12\uffff";
    static final String DFA58_maxS =
        "\1\76\12\uffff";
    static final String DFA58_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\4\uffff";
    static final String DFA58_specialS =
        "\13\uffff}>";
    static final String[] DFA58_transitionS = {
            "\1\6\3\uffff\1\1\5\uffff\1\6\1\uffff\1\1\3\uffff\1\1\2\uffff"+
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

    static final short[] DFA58_eot = DFA.unpackEncodedString(DFA58_eotS);
    static final short[] DFA58_eof = DFA.unpackEncodedString(DFA58_eofS);
    static final char[] DFA58_min = DFA.unpackEncodedStringToUnsignedChars(DFA58_minS);
    static final char[] DFA58_max = DFA.unpackEncodedStringToUnsignedChars(DFA58_maxS);
    static final short[] DFA58_accept = DFA.unpackEncodedString(DFA58_acceptS);
    static final short[] DFA58_special = DFA.unpackEncodedString(DFA58_specialS);
    static final short[][] DFA58_transition;

    static {
        int numStates = DFA58_transitionS.length;
        DFA58_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA58_transition[i] = DFA.unpackEncodedString(DFA58_transitionS[i]);
        }
    }

    class DFA58 extends DFA {

        public DFA58(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 58;
            this.eot = DFA58_eot;
            this.eof = DFA58_eof;
            this.min = DFA58_min;
            this.max = DFA58_max;
            this.accept = DFA58_accept;
            this.special = DFA58_special;
            this.transition = DFA58_transition;
        }
        public String getDescription() {
            return "()* loopback of 621:27: ( selpart )*";
        }
    }
    static final String DFA59_eotS =
        "\22\uffff";
    static final String DFA59_eofS =
        "\22\uffff";
    static final String DFA59_minS =
        "\1\35\2\uffff\1\31\16\uffff";
    static final String DFA59_maxS =
        "\1\70\2\uffff\1\76\16\uffff";
    static final String DFA59_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\13\uffff";
    static final String DFA59_specialS =
        "\22\uffff}>";
    static final String[] DFA59_transitionS = {
            "\1\3\7\uffff\1\1\3\uffff\1\1\13\uffff\1\1\2\uffff\1\1",
            "",
            "",
            "\1\1\3\uffff\1\6\5\uffff\2\1\1\6\3\uffff\1\6\2\uffff\1\1\5"+
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

    static final short[] DFA59_eot = DFA.unpackEncodedString(DFA59_eotS);
    static final short[] DFA59_eof = DFA.unpackEncodedString(DFA59_eofS);
    static final char[] DFA59_min = DFA.unpackEncodedStringToUnsignedChars(DFA59_minS);
    static final char[] DFA59_max = DFA.unpackEncodedStringToUnsignedChars(DFA59_maxS);
    static final short[] DFA59_accept = DFA.unpackEncodedString(DFA59_acceptS);
    static final short[] DFA59_special = DFA.unpackEncodedString(DFA59_specialS);
    static final short[][] DFA59_transition;

    static {
        int numStates = DFA59_transitionS.length;
        DFA59_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA59_transition[i] = DFA.unpackEncodedString(DFA59_transitionS[i]);
        }
    }

    class DFA59 extends DFA {

        public DFA59(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 59;
            this.eot = DFA59_eot;
            this.eof = DFA59_eof;
            this.min = DFA59_min;
            this.max = DFA59_max;
            this.accept = DFA59_accept;
            this.special = DFA59_special;
            this.transition = DFA59_transition;
        }
        public String getDescription() {
            return "()* loopback of 621:36: ( S )*";
        }
    }
    static final String DFA60_eotS =
        "\13\uffff";
    static final String DFA60_eofS =
        "\13\uffff";
    static final String DFA60_minS =
        "\1\31\12\uffff";
    static final String DFA60_maxS =
        "\1\76\12\uffff";
    static final String DFA60_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\4\uffff";
    static final String DFA60_specialS =
        "\13\uffff}>";
    static final String[] DFA60_transitionS = {
            "\1\6\3\uffff\1\1\5\uffff\1\6\1\uffff\1\1\3\uffff\1\1\2\uffff"+
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

    static final short[] DFA60_eot = DFA.unpackEncodedString(DFA60_eotS);
    static final short[] DFA60_eof = DFA.unpackEncodedString(DFA60_eofS);
    static final char[] DFA60_min = DFA.unpackEncodedStringToUnsignedChars(DFA60_minS);
    static final char[] DFA60_max = DFA.unpackEncodedStringToUnsignedChars(DFA60_maxS);
    static final short[] DFA60_accept = DFA.unpackEncodedString(DFA60_acceptS);
    static final short[] DFA60_special = DFA.unpackEncodedString(DFA60_specialS);
    static final short[][] DFA60_transition;

    static {
        int numStates = DFA60_transitionS.length;
        DFA60_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA60_transition[i] = DFA.unpackEncodedString(DFA60_transitionS[i]);
        }
    }

    class DFA60 extends DFA {

        public DFA60(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 60;
            this.eot = DFA60_eot;
            this.eof = DFA60_eof;
            this.min = DFA60_min;
            this.max = DFA60_max;
            this.accept = DFA60_accept;
            this.special = DFA60_special;
            this.transition = DFA60_transition;
        }
        public String getDescription() {
            return "()+ loopback of 623:7: ( selpart )+";
        }
    }
    static final String DFA61_eotS =
        "\22\uffff";
    static final String DFA61_eofS =
        "\22\uffff";
    static final String DFA61_minS =
        "\1\35\2\uffff\1\31\16\uffff";
    static final String DFA61_maxS =
        "\1\70\2\uffff\1\76\16\uffff";
    static final String DFA61_acceptS =
        "\1\uffff\1\2\13\uffff\1\1\4\uffff";
    static final String DFA61_specialS =
        "\22\uffff}>";
    static final String[] DFA61_transitionS = {
            "\1\3\7\uffff\1\1\3\uffff\1\1\13\uffff\1\1\2\uffff\1\1",
            "",
            "",
            "\1\1\3\uffff\1\15\5\uffff\2\1\1\15\3\uffff\1\15\2\uffff\1\1"+
            "\5\uffff\1\1\2\uffff\1\15\2\uffff\1\15\1\1\4\uffff\1\1",
            "",
            "",
            "",
            "",
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

    static final short[] DFA61_eot = DFA.unpackEncodedString(DFA61_eotS);
    static final short[] DFA61_eof = DFA.unpackEncodedString(DFA61_eofS);
    static final char[] DFA61_min = DFA.unpackEncodedStringToUnsignedChars(DFA61_minS);
    static final char[] DFA61_max = DFA.unpackEncodedStringToUnsignedChars(DFA61_maxS);
    static final short[] DFA61_accept = DFA.unpackEncodedString(DFA61_acceptS);
    static final short[] DFA61_special = DFA.unpackEncodedString(DFA61_specialS);
    static final short[][] DFA61_transition;

    static {
        int numStates = DFA61_transitionS.length;
        DFA61_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA61_transition[i] = DFA.unpackEncodedString(DFA61_transitionS[i]);
        }
    }

    class DFA61 extends DFA {

        public DFA61(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 61;
            this.eot = DFA61_eot;
            this.eof = DFA61_eof;
            this.min = DFA61_min;
            this.max = DFA61_max;
            this.accept = DFA61_accept;
            this.special = DFA61_special;
            this.transition = DFA61_transition;
        }
        public String getDescription() {
            return "()* loopback of 623:16: ( S )*";
        }
    }
    static final String DFA77_eotS =
        "\30\uffff";
    static final String DFA77_eofS =
        "\30\uffff";
    static final String DFA77_minS =
        "\1\27\27\uffff";
    static final String DFA77_maxS =
        "\1\100\27\uffff";
    static final String DFA77_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27";
    static final String DFA77_specialS =
        "\30\uffff}>";
    static final String[] DFA77_transitionS = {
            "\1\6\13\uffff\1\13\1\1\4\uffff\1\14\1\uffff\1\20\1\2\1\21\1"+
            "\3\1\4\1\5\1\7\1\10\1\11\1\12\1\15\1\16\1\17\1\22\1\23\1\24"+
            "\1\uffff\1\25\1\26\1\27\1\uffff\1\6",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA77_eot = DFA.unpackEncodedString(DFA77_eotS);
    static final short[] DFA77_eof = DFA.unpackEncodedString(DFA77_eofS);
    static final char[] DFA77_min = DFA.unpackEncodedStringToUnsignedChars(DFA77_minS);
    static final char[] DFA77_max = DFA.unpackEncodedStringToUnsignedChars(DFA77_maxS);
    static final short[] DFA77_accept = DFA.unpackEncodedString(DFA77_acceptS);
    static final short[] DFA77_special = DFA.unpackEncodedString(DFA77_specialS);
    static final short[][] DFA77_transition;

    static {
        int numStates = DFA77_transitionS.length;
        DFA77_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA77_transition[i] = DFA.unpackEncodedString(DFA77_transitionS[i]);
        }
    }

    class DFA77 extends DFA {

        public DFA77(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 77;
            this.eot = DFA77_eot;
            this.eof = DFA77_eof;
            this.min = DFA77_min;
            this.max = DFA77_max;
            this.accept = DFA77_accept;
            this.special = DFA77_special;
            this.transition = DFA77_transition;
        }
        public String getDescription() {
            return "659:4: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) )";
        }
    }
    static final String DFA73_eotS =
        "\32\uffff";
    static final String DFA73_eofS =
        "\32\uffff";
    static final String DFA73_minS =
        "\1\27\31\uffff";
    static final String DFA73_maxS =
        "\1\100\31\uffff";
    static final String DFA73_acceptS =
        "\1\uffff\1\2\27\uffff\1\1";
    static final String DFA73_specialS =
        "\32\uffff}>";
    static final String[] DFA73_transitionS = {
            "\1\1\5\uffff\1\31\5\uffff\2\1\4\uffff\1\1\1\uffff\24\1\1\uffff"+
            "\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA73_eot = DFA.unpackEncodedString(DFA73_eotS);
    static final short[] DFA73_eof = DFA.unpackEncodedString(DFA73_eofS);
    static final char[] DFA73_min = DFA.unpackEncodedStringToUnsignedChars(DFA73_minS);
    static final char[] DFA73_max = DFA.unpackEncodedStringToUnsignedChars(DFA73_maxS);
    static final short[] DFA73_accept = DFA.unpackEncodedString(DFA73_acceptS);
    static final short[] DFA73_special = DFA.unpackEncodedString(DFA73_specialS);
    static final short[][] DFA73_transition;

    static {
        int numStates = DFA73_transitionS.length;
        DFA73_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA73_transition[i] = DFA.unpackEncodedString(DFA73_transitionS[i]);
        }
    }

    class DFA73 extends DFA {

        public DFA73(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 73;
            this.eot = DFA73_eot;
            this.eof = DFA73_eof;
            this.min = DFA73_min;
            this.max = DFA73_max;
            this.accept = DFA73_accept;
            this.special = DFA73_special;
            this.transition = DFA73_transition;
        }
        public String getDescription() {
            return "()* loopback of 678:18: ( S )*";
        }
    }
    static final String DFA74_eotS =
        "\31\uffff";
    static final String DFA74_eofS =
        "\31\uffff";
    static final String DFA74_minS =
        "\1\27\30\uffff";
    static final String DFA74_maxS =
        "\1\100\30\uffff";
    static final String DFA74_acceptS =
        "\1\uffff\1\2\1\1\26\uffff";
    static final String DFA74_specialS =
        "\31\uffff}>";
    static final String[] DFA74_transitionS = {
            "\1\2\13\uffff\2\2\4\uffff\1\2\1\uffff\20\2\1\1\3\2\1\uffff\1"+
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
            "",
            ""
    };

    static final short[] DFA74_eot = DFA.unpackEncodedString(DFA74_eotS);
    static final short[] DFA74_eof = DFA.unpackEncodedString(DFA74_eofS);
    static final char[] DFA74_min = DFA.unpackEncodedStringToUnsignedChars(DFA74_minS);
    static final char[] DFA74_max = DFA.unpackEncodedStringToUnsignedChars(DFA74_maxS);
    static final short[] DFA74_accept = DFA.unpackEncodedString(DFA74_acceptS);
    static final short[] DFA74_special = DFA.unpackEncodedString(DFA74_specialS);
    static final short[][] DFA74_transition;

    static {
        int numStates = DFA74_transitionS.length;
        DFA74_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA74_transition[i] = DFA.unpackEncodedString(DFA74_transitionS[i]);
        }
    }

    class DFA74 extends DFA {

        public DFA74(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 74;
            this.eot = DFA74_eot;
            this.eof = DFA74_eof;
            this.min = DFA74_min;
            this.max = DFA74_max;
            this.accept = DFA74_accept;
            this.special = DFA74_special;
            this.transition = DFA74_transition;
        }
        public String getDescription() {
            return "()* loopback of 678:21: ( any )*";
        }
    }
    static final String DFA75_eotS =
        "\31\uffff";
    static final String DFA75_eofS =
        "\31\uffff";
    static final String DFA75_minS =
        "\1\27\30\uffff";
    static final String DFA75_maxS =
        "\1\100\30\uffff";
    static final String DFA75_acceptS =
        "\1\uffff\1\2\1\1\26\uffff";
    static final String DFA75_specialS =
        "\31\uffff}>";
    static final String[] DFA75_transitionS = {
            "\1\2\13\uffff\2\2\4\uffff\1\2\1\uffff\20\2\1\1\3\2\1\uffff\1"+
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
            "",
            ""
    };

    static final short[] DFA75_eot = DFA.unpackEncodedString(DFA75_eotS);
    static final short[] DFA75_eof = DFA.unpackEncodedString(DFA75_eofS);
    static final char[] DFA75_min = DFA.unpackEncodedStringToUnsignedChars(DFA75_minS);
    static final char[] DFA75_max = DFA.unpackEncodedStringToUnsignedChars(DFA75_maxS);
    static final short[] DFA75_accept = DFA.unpackEncodedString(DFA75_acceptS);
    static final short[] DFA75_special = DFA.unpackEncodedString(DFA75_specialS);
    static final short[][] DFA75_transition;

    static {
        int numStates = DFA75_transitionS.length;
        DFA75_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA75_transition[i] = DFA.unpackEncodedString(DFA75_transitionS[i]);
        }
    }

    class DFA75 extends DFA {

        public DFA75(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 75;
            this.eot = DFA75_eot;
            this.eof = DFA75_eof;
            this.min = DFA75_min;
            this.max = DFA75_max;
            this.accept = DFA75_accept;
            this.special = DFA75_special;
            this.transition = DFA75_transition;
        }
        public String getDescription() {
            return "()* loopback of 680:16: ( any )*";
        }
    }
    static final String DFA76_eotS =
        "\31\uffff";
    static final String DFA76_eofS =
        "\31\uffff";
    static final String DFA76_minS =
        "\1\27\30\uffff";
    static final String DFA76_maxS =
        "\1\100\30\uffff";
    static final String DFA76_acceptS =
        "\1\uffff\1\2\1\1\26\uffff";
    static final String DFA76_specialS =
        "\31\uffff}>";
    static final String[] DFA76_transitionS = {
            "\1\2\13\uffff\2\2\4\uffff\1\2\1\uffff\20\2\1\uffff\3\2\1\1\1"+
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
            "",
            ""
    };

    static final short[] DFA76_eot = DFA.unpackEncodedString(DFA76_eotS);
    static final short[] DFA76_eof = DFA.unpackEncodedString(DFA76_eofS);
    static final char[] DFA76_min = DFA.unpackEncodedStringToUnsignedChars(DFA76_minS);
    static final char[] DFA76_max = DFA.unpackEncodedStringToUnsignedChars(DFA76_maxS);
    static final short[] DFA76_accept = DFA.unpackEncodedString(DFA76_acceptS);
    static final short[] DFA76_special = DFA.unpackEncodedString(DFA76_specialS);
    static final short[][] DFA76_transition;

    static {
        int numStates = DFA76_transitionS.length;
        DFA76_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA76_transition[i] = DFA.unpackEncodedString(DFA76_transitionS[i]);
        }
    }

    class DFA76 extends DFA {

        public DFA76(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 76;
            this.eot = DFA76_eot;
            this.eof = DFA76_eof;
            this.min = DFA76_min;
            this.max = DFA76_max;
            this.accept = DFA76_accept;
            this.special = DFA76_special;
            this.transition = DFA76_transition;
        }
        public String getDescription() {
            return "()* loopback of 681:16: ( any )*";
        }
    }
    static final String DFA78_eotS =
        "\35\uffff";
    static final String DFA78_eofS =
        "\35\uffff";
    static final String DFA78_minS =
        "\1\27\34\uffff";
    static final String DFA78_maxS =
        "\1\100\34\uffff";
    static final String DFA78_acceptS =
        "\1\uffff\1\2\32\uffff\1\1";
    static final String DFA78_specialS =
        "\35\uffff}>";
    static final String[] DFA78_transitionS = {
            "\1\1\5\uffff\1\34\5\uffff\2\1\1\uffff\1\1\2\uffff\30\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA78_eot = DFA.unpackEncodedString(DFA78_eotS);
    static final short[] DFA78_eof = DFA.unpackEncodedString(DFA78_eofS);
    static final char[] DFA78_min = DFA.unpackEncodedStringToUnsignedChars(DFA78_minS);
    static final char[] DFA78_max = DFA.unpackEncodedStringToUnsignedChars(DFA78_maxS);
    static final short[] DFA78_accept = DFA.unpackEncodedString(DFA78_acceptS);
    static final short[] DFA78_special = DFA.unpackEncodedString(DFA78_specialS);
    static final short[][] DFA78_transition;

    static {
        int numStates = DFA78_transitionS.length;
        DFA78_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA78_transition[i] = DFA.unpackEncodedString(DFA78_transitionS[i]);
        }
    }

    class DFA78 extends DFA {

        public DFA78(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 78;
            this.eot = DFA78_eot;
            this.eof = DFA78_eof;
            this.min = DFA78_min;
            this.max = DFA78_max;
            this.accept = DFA78_accept;
            this.special = DFA78_special;
            this.transition = DFA78_transition;
        }
        public String getDescription() {
            return "()* loopback of 682:8: ( S )*";
        }
    }
 

    public static final BitSet FOLLOW_S_in_inlinestyle186 = new BitSet(new long[]{0x0000043820000000L});
    public static final BitSet FOLLOW_declarations_in_inlinestyle191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_inlineset_in_inlinestyle211 = new BitSet(new long[]{0x0000043800000002L});
    public static final BitSet FOLLOW_CDO_in_stylesheet239 = new BitSet(new long[]{0x4204119FF2400002L});
    public static final BitSet FOLLOW_CDC_in_stylesheet243 = new BitSet(new long[]{0x4204119FF2400002L});
    public static final BitSet FOLLOW_S_in_stylesheet247 = new BitSet(new long[]{0x4204119FF2400002L});
    public static final BitSet FOLLOW_statement_in_stylesheet251 = new BitSet(new long[]{0x4204119FF2400002L});
    public static final BitSet FOLLOW_ruleset_in_statement281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atstatement_in_statement285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARSET_in_atstatement296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_atstatement301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_IMPORT_in_atstatement306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_END_in_atstatement311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PAGE_in_atstatement316 = new BitSet(new long[]{0x0000002820000000L});
    public static final BitSet FOLLOW_S_in_atstatement318 = new BitSet(new long[]{0x0000002820000000L});
    public static final BitSet FOLLOW_COLON_in_atstatement322 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_IDENT_in_atstatement324 = new BitSet(new long[]{0x0000002020000000L});
    public static final BitSet FOLLOW_S_in_atstatement326 = new BitSet(new long[]{0x0000002020000000L});
    public static final BitSet FOLLOW_LCURLY_in_atstatement334 = new BitSet(new long[]{0x0000045020000000L});
    public static final BitSet FOLLOW_S_in_atstatement336 = new BitSet(new long[]{0x0000045020000000L});
    public static final BitSet FOLLOW_declarations_in_atstatement339 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_RCURLY_in_atstatement344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MEDIA_in_atstatement360 = new BitSet(new long[]{0x0000003020000000L});
    public static final BitSet FOLLOW_S_in_atstatement362 = new BitSet(new long[]{0x0000003020000000L});
    public static final BitSet FOLLOW_media_in_atstatement365 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LCURLY_in_atstatement371 = new BitSet(new long[]{0x4204105822000000L});
    public static final BitSet FOLLOW_S_in_atstatement373 = new BitSet(new long[]{0x4204105822000000L});
    public static final BitSet FOLLOW_ruleset_in_atstatement377 = new BitSet(new long[]{0x4204105822000000L});
    public static final BitSet FOLLOW_S_in_atstatement379 = new BitSet(new long[]{0x4204105822000000L});
    public static final BitSet FOLLOW_RCURLY_in_atstatement384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATKEYWORD_in_atstatement402 = new BitSet(new long[]{0x0000002020000000L});
    public static final BitSet FOLLOW_S_in_atstatement404 = new BitSet(new long[]{0x0000002020000000L});
    public static final BitSet FOLLOW_LCURLY_in_atstatement407 = new BitSet(new long[]{0x77FFFA5800800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_any_in_atstatement409 = new BitSet(new long[]{0x77FFFA5800800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_atstatement412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pseudo_in_inlineset435 = new BitSet(new long[]{0x0000022020000000L});
    public static final BitSet FOLLOW_S_in_inlineset437 = new BitSet(new long[]{0x0000022020000000L});
    public static final BitSet FOLLOW_COMMA_in_inlineset441 = new BitSet(new long[]{0x0000000820000000L});
    public static final BitSet FOLLOW_S_in_inlineset443 = new BitSet(new long[]{0x0000000820000000L});
    public static final BitSet FOLLOW_pseudo_in_inlineset446 = new BitSet(new long[]{0x0000022020000000L});
    public static final BitSet FOLLOW_S_in_inlineset448 = new BitSet(new long[]{0x0000022020000000L});
    public static final BitSet FOLLOW_LCURLY_in_inlineset461 = new BitSet(new long[]{0x0000045000000000L});
    public static final BitSet FOLLOW_declarations_in_inlineset467 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_RCURLY_in_inlineset472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_media499 = new BitSet(new long[]{0x0000020020000002L});
    public static final BitSet FOLLOW_S_in_media501 = new BitSet(new long[]{0x0000020020000002L});
    public static final BitSet FOLLOW_COMMA_in_media505 = new BitSet(new long[]{0x0000001020000000L});
    public static final BitSet FOLLOW_S_in_media507 = new BitSet(new long[]{0x0000001020000000L});
    public static final BitSet FOLLOW_IDENT_in_media510 = new BitSet(new long[]{0x0000020020000002L});
    public static final BitSet FOLLOW_S_in_media512 = new BitSet(new long[]{0x0000020020000002L});
    public static final BitSet FOLLOW_combined_selector_in_ruleset542 = new BitSet(new long[]{0x0000022000000000L});
    public static final BitSet FOLLOW_COMMA_in_ruleset545 = new BitSet(new long[]{0x4204105822000000L});
    public static final BitSet FOLLOW_S_in_ruleset547 = new BitSet(new long[]{0x4204105822000000L});
    public static final BitSet FOLLOW_combined_selector_in_ruleset550 = new BitSet(new long[]{0x0000022000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleset558 = new BitSet(new long[]{0x0000045020000000L});
    public static final BitSet FOLLOW_S_in_ruleset560 = new BitSet(new long[]{0x0000045020000000L});
    public static final BitSet FOLLOW_declarations_in_ruleset568 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleset573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_declarations598 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_declarations602 = new BitSet(new long[]{0x0000041020000002L});
    public static final BitSet FOLLOW_S_in_declarations604 = new BitSet(new long[]{0x0000041020000002L});
    public static final BitSet FOLLOW_declaration_in_declarations607 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_property_in_declaration634 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_COLON_in_declaration636 = new BitSet(new long[]{0x77FFF33820800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_S_in_declaration638 = new BitSet(new long[]{0x77FFF33820800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_terms_in_declaration641 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_important_in_declaration643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_important678 = new BitSet(new long[]{0x0000000020000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_S_in_important680 = new BitSet(new long[]{0x0000000020000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_important683 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_important685 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_IDENT_in_property710 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_property712 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_term_in_terms737 = new BitSet(new long[]{0x77FFF33820800002L,0x0000000000000001L});
    public static final BitSet FOLLOW_valuepart_in_term763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_term775 = new BitSet(new long[]{0x77FFFE5820800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_S_in_term777 = new BitSet(new long[]{0x77FFFE5820800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_any_in_term781 = new BitSet(new long[]{0x77FFFE5800800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMICOLON_in_term785 = new BitSet(new long[]{0x77FFFE5820800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_S_in_term787 = new BitSet(new long[]{0x77FFFE5820800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_term792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATKEYWORD_in_term804 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_term806 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_IDENT_in_valuepart831 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_valuepart845 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart859 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_NUMBER_in_valuepart862 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart879 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_PERCENTAGE_in_valuepart882 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart899 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_DIMENSION_in_valuepart902 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_string_in_valuepart919 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_URI_in_valuepart933 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_HASH_in_valuepart950 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_valuepart964 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_INCLUDES_in_valuepart978 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_COLON_in_valuepart992 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_COMMA_in_valuepart1006 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_GREATER_in_valuepart1020 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_EQUALS_in_valuepart1034 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_SLASH_in_valuepart1048 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_PLUS_in_valuepart1059 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_ASTERISK_in_valuepart1070 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_FUNCTION_in_valuepart1087 = new BitSet(new long[]{0x7FFFF33820800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_S_in_valuepart1089 = new BitSet(new long[]{0x7FFFF33820800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_terms_in_valuepart1092 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_valuepart1094 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_valuepart1113 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_LPAREN_in_valuepart1127 = new BitSet(new long[]{0x7FFFF21800800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_valuepart_in_valuepart1129 = new BitSet(new long[]{0x7FFFF21800800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_valuepart1132 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_LBRACE_in_valuepart1151 = new BitSet(new long[]{0xFFFFF21800800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_valuepart_in_valuepart1153 = new BitSet(new long[]{0xFFFFF21800800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RBRACE_in_valuepart1156 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_valuepart1174 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_selector_in_combined_selector1191 = new BitSet(new long[]{0x0120000020000002L});
    public static final BitSet FOLLOW_combinator_in_combined_selector1195 = new BitSet(new long[]{0x4204105822000000L});
    public static final BitSet FOLLOW_selector_in_combined_selector1198 = new BitSet(new long[]{0x0120000020000002L});
    public static final BitSet FOLLOW_GREATER_in_combinator1218 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_combinator1220 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_PLUS_in_combinator1230 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_combinator1232 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_combinator1242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_selector1261 = new BitSet(new long[]{0x4204105822000002L});
    public static final BitSet FOLLOW_ASTERISK_in_selector1265 = new BitSet(new long[]{0x4204105822000002L});
    public static final BitSet FOLLOW_selpart_in_selector1269 = new BitSet(new long[]{0x4204105822000002L});
    public static final BitSet FOLLOW_S_in_selector1272 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_selpart_in_selector1302 = new BitSet(new long[]{0x4204105822000002L});
    public static final BitSet FOLLOW_S_in_selector1305 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_HASH_in_selpart1352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_selpart1360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_selpart1365 = new BitSet(new long[]{0x0000001020000000L});
    public static final BitSet FOLLOW_S_in_selpart1367 = new BitSet(new long[]{0x0000001020000000L});
    public static final BitSet FOLLOW_attribute_in_selpart1370 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_RBRACE_in_selpart1372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pseudo_in_selpart1388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_SELPART_in_selpart1396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_attribute1422 = new BitSet(new long[]{0x1050000020000002L});
    public static final BitSet FOLLOW_S_in_attribute1424 = new BitSet(new long[]{0x1050000020000002L});
    public static final BitSet FOLLOW_set_in_attribute1431 = new BitSet(new long[]{0x0000001020800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_S_in_attribute1443 = new BitSet(new long[]{0x0000001020800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_IDENT_in_attribute1447 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_string_in_attribute1451 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_attribute1454 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_COLON_in_pseudo1468 = new BitSet(new long[]{0x0400001000000000L});
    public static final BitSet FOLLOW_IDENT_in_pseudo1471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_pseudo1475 = new BitSet(new long[]{0x0000001020000000L});
    public static final BitSet FOLLOW_S_in_pseudo1477 = new BitSet(new long[]{0x0000001020000000L});
    public static final BitSet FOLLOW_IDENT_in_pseudo1481 = new BitSet(new long[]{0x0800000020000000L});
    public static final BitSet FOLLOW_S_in_pseudo1483 = new BitSet(new long[]{0x0800000020000000L});
    public static final BitSet FOLLOW_RPAREN_in_pseudo1486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_string0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_any1532 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_any1543 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_NUMBER_in_any1554 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_PERCENTAGE_in_any1565 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_DIMENSION_in_any1575 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_string_in_any1586 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_URI_in_any1600 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_HASH_in_any1617 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_any1631 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_INCLUDES_in_any1645 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_COLON_in_any1659 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_COMMA_in_any1673 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_GREATER_in_any1687 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_EQUALS_in_any1701 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_SLASH_in_any1715 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_any1729 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_MINUS_in_any1740 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_PLUS_in_any1751 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_ASTERISK_in_any1762 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_FUNCTION_in_any1779 = new BitSet(new long[]{0x7FFFFA5820800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_S_in_any1781 = new BitSet(new long[]{0x7FFFFA5820800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_any_in_any1784 = new BitSet(new long[]{0x7FFFFA5800800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_any1787 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_any1807 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_LPAREN_in_any1821 = new BitSet(new long[]{0x7FFFFA5800800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_any_in_any1823 = new BitSet(new long[]{0x7FFFFA5800800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RPAREN_in_any1826 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_LBRACE_in_any1845 = new BitSet(new long[]{0xFFFFFA5800800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_any_in_any1847 = new BitSet(new long[]{0xFFFFFA5800800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RBRACE_in_any1850 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_any1868 = new BitSet(new long[]{0x0000000020000002L});

}