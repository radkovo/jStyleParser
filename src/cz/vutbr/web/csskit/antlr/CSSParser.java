// $ANTLR 3.1.2 /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g 2009-09-16 17:38:11
 
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

/**
 * A basic CSS grammar.
 */
public class CSSParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "STYLESHEET", "INLINESTYLE", "ATBLOCK", "CURLYBLOCK", "PARENBLOCK", "BRACEBLOCK", "RULE", "SELECTOR", "ELEMENT", "PSEUDO", "ADJACENT", "CHILD", "DESCENDANT", "ATTRIBUTE", "SET", "DECLARATION", "VALUE", "IMPORTANT", "IMPORT_END", "INVALID_STRING", "INVALID_SELECTOR", "INVALID_SELPART", "INVALID_DECLARATION", "INVALID_STATEMENT", "INVALID_IMPORT", "S", "CDO", "CDC", "CHARSET", "IMPORT", "PAGE", "COLON", "IDENT", "LCURLY", "RCURLY", "MEDIA", "ATKEYWORD", "COMMA", "SEMICOLON", "EXCLAMATION", "MINUS", "EXPRESSION", "FUNCTION", "RPAREN", "CLASSKEYWORD", "NUMBER", "PERCENTAGE", "DIMENSION", "URI", "HASH", "UNIRANGE", "INCLUDES", "GREATER", "LESS", "QUESTION", "PERCENT", "EQUALS", "SLASH", "PLUS", "ASTERISK", "DASHMATCH", "LPAREN", "LBRACE", "RBRACE", "STRING", "STRING_CHAR", "INVALID_TOKEN", "IDENT_MACR", "STRING_MACR", "NAME_MACR", "NUMBER_MACR", "W_MACR", "URI_MACR", "APOS", "QUOT", "W_CHAR", "COMMENT", "SL_COMMENT", "NAME_START", "NAME_CHAR", "NON_ASCII", "ESCAPE_CHAR", "URI_CHAR", "NL_CHAR", "'important'"
    };
    public static final int FUNCTION=46;
    public static final int APOS=77;
    public static final int NAME_CHAR=83;
    public static final int CLASSKEYWORD=48;
    public static final int PSEUDO=13;
    public static final int LBRACE=66;
    public static final int INVALID_STATEMENT=27;
    public static final int ATTRIBUTE=17;
    public static final int INVALID_TOKEN=70;
    public static final int EQUALS=60;
    public static final int NAME_START=82;
    public static final int NUMBER_MACR=74;
    public static final int MEDIA=39;
    public static final int NL_CHAR=87;
    public static final int CHARSET=32;
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
    public static final int CHILD=15;
    public static final int ATKEYWORD=40;
    public static final int INVALID_STRING=23;
    public static final int RBRACE=67;
    public static final int PERCENT=59;
    public static final int RULE=10;
    public static final int W_CHAR=79;
    public static final int PARENBLOCK=8;
    public static final int STRING_MACR=72;
    public static final int QUOT=78;
    public static final int W_MACR=75;
    public static final int URI_CHAR=86;
    public static final int NUMBER=49;
    public static final int DESCENDANT=16;
    public static final int HASH=53;
    public static final int SET=18;
    public static final int LCURLY=37;
    public static final int NAME_MACR=73;
    public static final int SEMICOLON=42;
    public static final int S=29;
    public static final int MINUS=44;
    public static final int VALUE=20;
    public static final int CDO=30;
    public static final int CDC=31;
    public static final int PERCENTAGE=50;
    public static final int T__88=88;
    public static final int IMPORTANT=21;
    public static final int INVALID_SELECTOR=24;
    public static final int ESCAPE_CHAR=85;
    public static final int URI=52;
    public static final int COLON=35;
    public static final int STRING_CHAR=69;
    public static final int PAGE=34;
    public static final int DASHMATCH=64;
    public static final int QUESTION=58;
    public static final int ADJACENT=14;
    public static final int IMPORT_END=22;
    public static final int INVALID_IMPORT=28;
    public static final int INLINESTYLE=5;
    public static final int SL_COMMENT=81;
    public static final int RCURLY=38;
    public static final int IDENT_MACR=71;
    public static final int STRING=68;
    public static final int URI_MACR=76;

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
    public String getGrammarFileName() { return "/home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g"; }


        private static Logger log = LoggerFactory.getLogger(CSSParser.class);
        
        private static SupportedCSS css = CSSFactory.getSupportedCSS();
        
        private StyleSheet stylesheet;
        
        private int functLevel = 0;
        
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
    		  Token next = input.LT(1);
    		  if (next instanceof CSSToken)
    		      t= (CSSToken) input.LT(1);
    		  else
    		      break; /* not a CSSToken, probably EOF */
    		  log.trace("Skipped greedy: {}", t);
    		  // consume token even if it will match
    		  input.consume();
    		}while(!(t.getLexerState().isBalanced() && follow.member(t.getType())));
    	} 

      /**
       * Recovers and logs error inside a function, using custom follow set,
       * prepares tree part replacement
       */ 
      private Object invalidFallbackGreedyFunction(int ttype, String ttext, BitSet follow, RecognitionException re) {
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
        consumeUntilGreedyFunction(input, follow);
        endResync();
        return invalidReplacement(ttype, ttext);
        
        }
      
      /**
       * Consumes token until lexer state is function-balanced and
       * token from follow is matched. Matched token is also consumed
       */ 
      private void consumeUntilGreedyFunction(TokenStream input, BitSet follow) {
        CSSToken t = null;
        do{
          Token next = input.LT(1);
          if (next instanceof CSSToken)
              t= (CSSToken) input.LT(1);
          else
              break; /* not a CSSToken, probably EOF */
          log.trace("Skipped greedy: {}", t);
          // consume token even if it will match
          input.consume();
        }while(!(t.getLexerState().isFunctionBalanced() && follow.member(t.getType())));
      }
      
      //this switches the single token insertion / deletion off because it interferes with our own error recovery
      protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow)
          throws RecognitionException
      {
          throw new MismatchedTokenException(ttype, input);
      }   
       


    public static class inlinestyle_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "inlinestyle"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:641:1: inlinestyle : ( S )* ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) ) ;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:648:2: ( ( S )* ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:648:4: ( S )* ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:648:4: ( S )*
            loop1:
            do {
                int alt1=2;
                alt1 = dfa1.predict(input);
                switch (alt1) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:648:4: S
            	    {
            	    S1=(Token)match(input,S,FOLLOW_S_in_inlinestyle197);  
            	    stream_S.add(S1);


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:648:8: ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) )
            int alt3=2;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:648:9: declarations
                    {
                    pushFollow(FOLLOW_declarations_in_inlinestyle202);
                    declarations2=declarations();

                    state._fsp--;

                    stream_declarations.add(declarations2.getTree());


                    // AST REWRITE
                    // elements: declarations
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 648:22: -> ^( INLINESTYLE declarations )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:648:25: ^( INLINESTYLE declarations )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:649:10: ( inlineset )+
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:649:10: ( inlineset )+
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
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:649:10: inlineset
                    	    {
                    	    pushFollow(FOLLOW_inlineset_in_inlinestyle222);
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
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 649:21: -> ^( INLINESTYLE ( inlineset )+ )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:649:24: ^( INLINESTYLE ( inlineset )+ )
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:653:1: stylesheet : ( CDO | CDC | S | statement )* -> ^( STYLESHEET ( statement )* ) ;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:654:2: ( ( CDO | CDC | S | statement )* -> ^( STYLESHEET ( statement )* ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:654:4: ( CDO | CDC | S | statement )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:654:4: ( CDO | CDC | S | statement )*
            loop4:
            do {
                int alt4=5;
                alt4 = dfa4.predict(input);
                switch (alt4) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:654:6: CDO
            	    {
            	    CDO4=(Token)match(input,CDO,FOLLOW_CDO_in_stylesheet250);  
            	    stream_CDO.add(CDO4);


            	    }
            	    break;
            	case 2 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:654:12: CDC
            	    {
            	    CDC5=(Token)match(input,CDC,FOLLOW_CDC_in_stylesheet254);  
            	    stream_CDC.add(CDC5);


            	    }
            	    break;
            	case 3 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:654:18: S
            	    {
            	    S6=(Token)match(input,S,FOLLOW_S_in_stylesheet258);  
            	    stream_S.add(S6);


            	    }
            	    break;
            	case 4 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:654:22: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_stylesheet262);
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
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 655:3: -> ^( STYLESHEET ( statement )* )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:655:6: ^( STYLESHEET ( statement )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(STYLESHEET, "STYLESHEET"), root_1);

                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:655:19: ( statement )*
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:658:1: statement : ( ruleset | atstatement );
    public final CSSParser.statement_return statement() throws RecognitionException {
        CSSParser.statement_return retval = new CSSParser.statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CSSParser.ruleset_return ruleset8 = null;

        CSSParser.atstatement_return atstatement9 = null;



        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:659:2: ( ruleset | atstatement )
            int alt5=2;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:659:4: ruleset
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ruleset_in_statement292);
                    ruleset8=ruleset();

                    state._fsp--;

                    adaptor.addChild(root_0, ruleset8.getTree());

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:659:14: atstatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_atstatement_in_statement296);
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:662:1: atstatement : ( CHARSET | IMPORT | INVALID_IMPORT | IMPORT_END | PAGE ( S )* ( COLON IDENT ( S )* )? LCURLY ( S )* declarations RCURLY -> ^( PAGE ( IDENT )? declarations ) | MEDIA ( S )* ( media )? LCURLY ( S )* ( ruleset ( S )* )* RCURLY -> ^( MEDIA ( media )? ( ruleset )* ) | ATKEYWORD ( S )* LCURLY ( any )* RCURLY -> INVALID_STATEMENT );
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
        RewriteRuleTokenStream stream_ATKEYWORD=new RewriteRuleTokenStream(adaptor,"token ATKEYWORD");
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_PAGE=new RewriteRuleTokenStream(adaptor,"token PAGE");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_MEDIA=new RewriteRuleTokenStream(adaptor,"token MEDIA");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_ruleset=new RewriteRuleSubtreeStream(adaptor,"rule ruleset");
        RewriteRuleSubtreeStream stream_any=new RewriteRuleSubtreeStream(adaptor,"rule any");
        RewriteRuleSubtreeStream stream_media=new RewriteRuleSubtreeStream(adaptor,"rule media");
        RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:663:2: ( CHARSET | IMPORT | INVALID_IMPORT | IMPORT_END | PAGE ( S )* ( COLON IDENT ( S )* )? LCURLY ( S )* declarations RCURLY -> ^( PAGE ( IDENT )? declarations ) | MEDIA ( S )* ( media )? LCURLY ( S )* ( ruleset ( S )* )* RCURLY -> ^( MEDIA ( media )? ( ruleset )* ) | ATKEYWORD ( S )* LCURLY ( any )* RCURLY -> INVALID_STATEMENT )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:663:4: CHARSET
                    {
                    root_0 = (Object)adaptor.nil();

                    CHARSET10=(Token)match(input,CHARSET,FOLLOW_CHARSET_in_atstatement307); 
                    CHARSET10_tree = (Object)adaptor.create(CHARSET10);
                    adaptor.addChild(root_0, CHARSET10_tree);


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:664:4: IMPORT
                    {
                    root_0 = (Object)adaptor.nil();

                    IMPORT11=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_atstatement312); 
                    IMPORT11_tree = (Object)adaptor.create(IMPORT11);
                    adaptor.addChild(root_0, IMPORT11_tree);


                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:665:4: INVALID_IMPORT
                    {
                    root_0 = (Object)adaptor.nil();

                    INVALID_IMPORT12=(Token)match(input,INVALID_IMPORT,FOLLOW_INVALID_IMPORT_in_atstatement317); 
                    INVALID_IMPORT12_tree = (Object)adaptor.create(INVALID_IMPORT12);
                    adaptor.addChild(root_0, INVALID_IMPORT12_tree);


                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:666:4: IMPORT_END
                    {
                    root_0 = (Object)adaptor.nil();

                    IMPORT_END13=(Token)match(input,IMPORT_END,FOLLOW_IMPORT_END_in_atstatement322); 
                    IMPORT_END13_tree = (Object)adaptor.create(IMPORT_END13);
                    adaptor.addChild(root_0, IMPORT_END13_tree);


                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:667:4: PAGE ( S )* ( COLON IDENT ( S )* )? LCURLY ( S )* declarations RCURLY
                    {
                    PAGE14=(Token)match(input,PAGE,FOLLOW_PAGE_in_atstatement327);  
                    stream_PAGE.add(PAGE14);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:667:9: ( S )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==S) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:667:9: S
                    	    {
                    	    S15=(Token)match(input,S,FOLLOW_S_in_atstatement329);  
                    	    stream_S.add(S15);


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:667:12: ( COLON IDENT ( S )* )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==COLON) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:667:13: COLON IDENT ( S )*
                            {
                            COLON16=(Token)match(input,COLON,FOLLOW_COLON_in_atstatement333);  
                            stream_COLON.add(COLON16);

                            IDENT17=(Token)match(input,IDENT,FOLLOW_IDENT_in_atstatement335);  
                            stream_IDENT.add(IDENT17);

                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:667:25: ( S )*
                            loop7:
                            do {
                                int alt7=2;
                                int LA7_0 = input.LA(1);

                                if ( (LA7_0==S) ) {
                                    alt7=1;
                                }


                                switch (alt7) {
                            	case 1 :
                            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:667:25: S
                            	    {
                            	    S18=(Token)match(input,S,FOLLOW_S_in_atstatement337);  
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

                    LCURLY19=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atstatement345);  
                    stream_LCURLY.add(LCURLY19);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:668:10: ( S )*
                    loop9:
                    do {
                        int alt9=2;
                        alt9 = dfa9.predict(input);
                        switch (alt9) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:668:10: S
                    	    {
                    	    S20=(Token)match(input,S,FOLLOW_S_in_atstatement347);  
                    	    stream_S.add(S20);


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);

                    pushFollow(FOLLOW_declarations_in_atstatement350);
                    declarations21=declarations();

                    state._fsp--;

                    stream_declarations.add(declarations21.getTree());
                    RCURLY22=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atstatement355);  
                    stream_RCURLY.add(RCURLY22);



                    // AST REWRITE
                    // elements: PAGE, declarations, IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 669:10: -> ^( PAGE ( IDENT )? declarations )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:669:13: ^( PAGE ( IDENT )? declarations )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_PAGE.nextNode(), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:669:20: ( IDENT )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:670:4: MEDIA ( S )* ( media )? LCURLY ( S )* ( ruleset ( S )* )* RCURLY
                    {
                    MEDIA23=(Token)match(input,MEDIA,FOLLOW_MEDIA_in_atstatement371);  
                    stream_MEDIA.add(MEDIA23);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:670:10: ( S )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==S) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:670:10: S
                    	    {
                    	    S24=(Token)match(input,S,FOLLOW_S_in_atstatement373);  
                    	    stream_S.add(S24);


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:670:13: ( media )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==IDENT) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:670:13: media
                            {
                            pushFollow(FOLLOW_media_in_atstatement376);
                            media25=media();

                            state._fsp--;

                            stream_media.add(media25.getTree());

                            }
                            break;

                    }

                    LCURLY26=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atstatement382);  
                    stream_LCURLY.add(LCURLY26);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:671:10: ( S )*
                    loop12:
                    do {
                        int alt12=2;
                        alt12 = dfa12.predict(input);
                        switch (alt12) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:671:10: S
                    	    {
                    	    S27=(Token)match(input,S,FOLLOW_S_in_atstatement384);  
                    	    stream_S.add(S27);


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:671:13: ( ruleset ( S )* )*
                    loop14:
                    do {
                        int alt14=2;
                        alt14 = dfa14.predict(input);
                        switch (alt14) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:671:14: ruleset ( S )*
                    	    {
                    	    pushFollow(FOLLOW_ruleset_in_atstatement388);
                    	    ruleset28=ruleset();

                    	    state._fsp--;

                    	    stream_ruleset.add(ruleset28.getTree());
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:671:22: ( S )*
                    	    loop13:
                    	    do {
                    	        int alt13=2;
                    	        alt13 = dfa13.predict(input);
                    	        switch (alt13) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:671:22: S
                    	    	    {
                    	    	    S29=(Token)match(input,S,FOLLOW_S_in_atstatement390);  
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

                    RCURLY30=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atstatement395);  
                    stream_RCURLY.add(RCURLY30);



                    // AST REWRITE
                    // elements: ruleset, media, MEDIA
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 671:34: -> ^( MEDIA ( media )? ( ruleset )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:671:37: ^( MEDIA ( media )? ( ruleset )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_MEDIA.nextNode(), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:671:45: ( media )?
                        if ( stream_media.hasNext() ) {
                            adaptor.addChild(root_1, stream_media.nextTree());

                        }
                        stream_media.reset();
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:671:52: ( ruleset )*
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:672:4: ATKEYWORD ( S )* LCURLY ( any )* RCURLY
                    {
                    ATKEYWORD31=(Token)match(input,ATKEYWORD,FOLLOW_ATKEYWORD_in_atstatement413);  
                    stream_ATKEYWORD.add(ATKEYWORD31);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:672:14: ( S )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==S) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:672:14: S
                    	    {
                    	    S32=(Token)match(input,S,FOLLOW_S_in_atstatement415);  
                    	    stream_S.add(S32);


                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);

                    LCURLY33=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atstatement418);  
                    stream_LCURLY.add(LCURLY33);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:672:24: ( any )*
                    loop16:
                    do {
                        int alt16=2;
                        alt16 = dfa16.predict(input);
                        switch (alt16) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:672:24: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_atstatement420);
                    	    any34=any();

                    	    state._fsp--;

                    	    stream_any.add(any34.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);

                    RCURLY35=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atstatement423);  
                    stream_RCURLY.add(RCURLY35);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 672:36: -> INVALID_STATEMENT
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:680:1: inlineset : ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )? LCURLY declarations RCURLY -> ^( RULE ( pseudo )* declarations ) ;
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
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_pseudo=new RewriteRuleSubtreeStream(adaptor,"rule pseudo");
        RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:683:2: ( ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )? LCURLY declarations RCURLY -> ^( RULE ( pseudo )* declarations ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:683:4: ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )? LCURLY declarations RCURLY
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:683:4: ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==COLON) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:683:5: pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )*
                    {
                    pushFollow(FOLLOW_pseudo_in_inlineset448);
                    pseudo36=pseudo();

                    state._fsp--;

                    stream_pseudo.add(pseudo36.getTree());
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:683:12: ( S )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==S) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:683:12: S
                    	    {
                    	    S37=(Token)match(input,S,FOLLOW_S_in_inlineset450);  
                    	    stream_S.add(S37);


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:683:15: ( COMMA ( S )* pseudo ( S )* )*
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==COMMA) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:683:16: COMMA ( S )* pseudo ( S )*
                    	    {
                    	    COMMA38=(Token)match(input,COMMA,FOLLOW_COMMA_in_inlineset454);  
                    	    stream_COMMA.add(COMMA38);

                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:683:22: ( S )*
                    	    loop19:
                    	    do {
                    	        int alt19=2;
                    	        int LA19_0 = input.LA(1);

                    	        if ( (LA19_0==S) ) {
                    	            alt19=1;
                    	        }


                    	        switch (alt19) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:683:22: S
                    	    	    {
                    	    	    S39=(Token)match(input,S,FOLLOW_S_in_inlineset456);  
                    	    	    stream_S.add(S39);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop19;
                    	        }
                    	    } while (true);

                    	    pushFollow(FOLLOW_pseudo_in_inlineset459);
                    	    pseudo40=pseudo();

                    	    state._fsp--;

                    	    stream_pseudo.add(pseudo40.getTree());
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:683:32: ( S )*
                    	    loop20:
                    	    do {
                    	        int alt20=2;
                    	        int LA20_0 = input.LA(1);

                    	        if ( (LA20_0==S) ) {
                    	            alt20=1;
                    	        }


                    	        switch (alt20) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:683:32: S
                    	    	    {
                    	    	    S41=(Token)match(input,S,FOLLOW_S_in_inlineset461);  
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

            LCURLY42=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_inlineset474);  
            stream_LCURLY.add(LCURLY42);

            pushFollow(FOLLOW_declarations_in_inlineset480);
            declarations43=declarations();

            state._fsp--;

            stream_declarations.add(declarations43.getTree());
            RCURLY44=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_inlineset485);  
            stream_RCURLY.add(RCURLY44);



            // AST REWRITE
            // elements: declarations, pseudo
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 687:4: -> ^( RULE ( pseudo )* declarations )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:687:7: ^( RULE ( pseudo )* declarations )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(RULE, "RULE"), root_1);

                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:687:14: ( pseudo )*
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:690:1: media : IDENT ( S )* ( COMMA ( S )* IDENT ( S )* )* -> ( IDENT )+ ;
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
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:691:2: ( IDENT ( S )* ( COMMA ( S )* IDENT ( S )* )* -> ( IDENT )+ )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:691:4: IDENT ( S )* ( COMMA ( S )* IDENT ( S )* )*
            {
            IDENT45=(Token)match(input,IDENT,FOLLOW_IDENT_in_media512);  
            stream_IDENT.add(IDENT45);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:691:10: ( S )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==S) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:691:10: S
            	    {
            	    S46=(Token)match(input,S,FOLLOW_S_in_media514);  
            	    stream_S.add(S46);


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:691:13: ( COMMA ( S )* IDENT ( S )* )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==COMMA) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:691:14: COMMA ( S )* IDENT ( S )*
            	    {
            	    COMMA47=(Token)match(input,COMMA,FOLLOW_COMMA_in_media518);  
            	    stream_COMMA.add(COMMA47);

            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:691:20: ( S )*
            	    loop24:
            	    do {
            	        int alt24=2;
            	        int LA24_0 = input.LA(1);

            	        if ( (LA24_0==S) ) {
            	            alt24=1;
            	        }


            	        switch (alt24) {
            	    	case 1 :
            	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:691:20: S
            	    	    {
            	    	    S48=(Token)match(input,S,FOLLOW_S_in_media520);  
            	    	    stream_S.add(S48);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop24;
            	        }
            	    } while (true);

            	    IDENT49=(Token)match(input,IDENT,FOLLOW_IDENT_in_media523);  
            	    stream_IDENT.add(IDENT49);

            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:691:29: ( S )*
            	    loop25:
            	    do {
            	        int alt25=2;
            	        int LA25_0 = input.LA(1);

            	        if ( (LA25_0==S) ) {
            	            alt25=1;
            	        }


            	        switch (alt25) {
            	    	case 1 :
            	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:691:29: S
            	    	    {
            	    	    S50=(Token)match(input,S,FOLLOW_S_in_media525);  
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
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 692:3: -> ( IDENT )+
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:695:1: ruleset : ( combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY -> ^( RULE ( combined_selector )+ declarations ) | norule -> INVALID_STATEMENT );
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

        CSSParser.norule_return norule59 = null;


        Object COMMA52_tree=null;
        Object S53_tree=null;
        Object LCURLY55_tree=null;
        Object S56_tree=null;
        Object RCURLY58_tree=null;
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_combined_selector=new RewriteRuleSubtreeStream(adaptor,"rule combined_selector");
        RewriteRuleSubtreeStream stream_norule=new RewriteRuleSubtreeStream(adaptor,"rule norule");
        RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:696:2: ( combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY -> ^( RULE ( combined_selector )+ declarations ) | norule -> INVALID_STATEMENT )
            int alt30=2;
            alt30 = dfa30.predict(input);
            switch (alt30) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:696:4: combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY
                    {
                    pushFollow(FOLLOW_combined_selector_in_ruleset550);
                    combined_selector51=combined_selector();

                    state._fsp--;

                    stream_combined_selector.add(combined_selector51.getTree());
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:696:22: ( COMMA ( S )* combined_selector )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==COMMA) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:696:23: COMMA ( S )* combined_selector
                    	    {
                    	    COMMA52=(Token)match(input,COMMA,FOLLOW_COMMA_in_ruleset553);  
                    	    stream_COMMA.add(COMMA52);

                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:696:29: ( S )*
                    	    loop27:
                    	    do {
                    	        int alt27=2;
                    	        int LA27_0 = input.LA(1);

                    	        if ( (LA27_0==S) ) {
                    	            alt27=1;
                    	        }


                    	        switch (alt27) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:696:29: S
                    	    	    {
                    	    	    S53=(Token)match(input,S,FOLLOW_S_in_ruleset555);  
                    	    	    stream_S.add(S53);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop27;
                    	        }
                    	    } while (true);

                    	    pushFollow(FOLLOW_combined_selector_in_ruleset558);
                    	    combined_selector54=combined_selector();

                    	    state._fsp--;

                    	    stream_combined_selector.add(combined_selector54.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop28;
                        }
                    } while (true);

                    LCURLY55=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_ruleset566);  
                    stream_LCURLY.add(LCURLY55);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:697:11: ( S )*
                    loop29:
                    do {
                        int alt29=2;
                        alt29 = dfa29.predict(input);
                        switch (alt29) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:697:11: S
                    	    {
                    	    S56=(Token)match(input,S,FOLLOW_S_in_ruleset568);  
                    	    stream_S.add(S56);


                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);

                    pushFollow(FOLLOW_declarations_in_ruleset576);
                    declarations57=declarations();

                    state._fsp--;

                    stream_declarations.add(declarations57.getTree());
                    RCURLY58=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_ruleset581);  
                    stream_RCURLY.add(RCURLY58);



                    // AST REWRITE
                    // elements: combined_selector, declarations
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 700:4: -> ^( RULE ( combined_selector )+ declarations )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:700:7: ^( RULE ( combined_selector )+ declarations )
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
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:701:4: norule
                    {
                    pushFollow(FOLLOW_norule_in_ruleset600);
                    norule59=norule();

                    state._fsp--;

                    stream_norule.add(norule59.getTree());


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 701:11: -> INVALID_STATEMENT
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
    // $ANTLR end "ruleset"

    public static class declarations_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "declarations"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:709:1: declarations : ( declaration )? ( SEMICOLON ( S )* ( declaration )? )* -> ^( SET ( declaration )* ) ;
    public final CSSParser.declarations_return declarations() throws RecognitionException {
        CSSParser.declarations_return retval = new CSSParser.declarations_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SEMICOLON61=null;
        Token S62=null;
        CSSParser.declaration_return declaration60 = null;

        CSSParser.declaration_return declaration63 = null;


        Object SEMICOLON61_tree=null;
        Object S62_tree=null;
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleSubtreeStream stream_declaration=new RewriteRuleSubtreeStream(adaptor,"rule declaration");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:710:2: ( ( declaration )? ( SEMICOLON ( S )* ( declaration )? )* -> ^( SET ( declaration )* ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:710:4: ( declaration )? ( SEMICOLON ( S )* ( declaration )? )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:710:4: ( declaration )?
            int alt31=2;
            alt31 = dfa31.predict(input);
            switch (alt31) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:710:4: declaration
                    {
                    pushFollow(FOLLOW_declaration_in_declarations622);
                    declaration60=declaration();

                    state._fsp--;

                    stream_declaration.add(declaration60.getTree());

                    }
                    break;

            }

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:710:17: ( SEMICOLON ( S )* ( declaration )? )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==SEMICOLON) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:710:18: SEMICOLON ( S )* ( declaration )?
            	    {
            	    SEMICOLON61=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_declarations626);  
            	    stream_SEMICOLON.add(SEMICOLON61);

            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:710:28: ( S )*
            	    loop32:
            	    do {
            	        int alt32=2;
            	        alt32 = dfa32.predict(input);
            	        switch (alt32) {
            	    	case 1 :
            	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:710:28: S
            	    	    {
            	    	    S62=(Token)match(input,S,FOLLOW_S_in_declarations628);  
            	    	    stream_S.add(S62);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop32;
            	        }
            	    } while (true);

            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:710:31: ( declaration )?
            	    int alt33=2;
            	    alt33 = dfa33.predict(input);
            	    switch (alt33) {
            	        case 1 :
            	            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:710:31: declaration
            	            {
            	            pushFollow(FOLLOW_declaration_in_declarations631);
            	            declaration63=declaration();

            	            state._fsp--;

            	            stream_declaration.add(declaration63.getTree());

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);



            // AST REWRITE
            // elements: declaration
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 711:4: -> ^( SET ( declaration )* )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:711:7: ^( SET ( declaration )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SET, "SET"), root_1);

                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:711:13: ( declaration )*
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:714:1: declaration : ( property COLON ( S )* terms ( important )? -> ^( DECLARATION ( important )? property terms ) | noprop ( any )* -> INVALID_DECLARATION );
    public final CSSParser.declaration_return declaration() throws RecognitionException {
        CSSParser.declaration_return retval = new CSSParser.declaration_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON65=null;
        Token S66=null;
        CSSParser.property_return property64 = null;

        CSSParser.terms_return terms67 = null;

        CSSParser.important_return important68 = null;

        CSSParser.noprop_return noprop69 = null;

        CSSParser.any_return any70 = null;


        Object COLON65_tree=null;
        Object S66_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleSubtreeStream stream_important=new RewriteRuleSubtreeStream(adaptor,"rule important");
        RewriteRuleSubtreeStream stream_any=new RewriteRuleSubtreeStream(adaptor,"rule any");
        RewriteRuleSubtreeStream stream_terms=new RewriteRuleSubtreeStream(adaptor,"rule terms");
        RewriteRuleSubtreeStream stream_property=new RewriteRuleSubtreeStream(adaptor,"rule property");
        RewriteRuleSubtreeStream stream_noprop=new RewriteRuleSubtreeStream(adaptor,"rule noprop");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:715:2: ( property COLON ( S )* terms ( important )? -> ^( DECLARATION ( important )? property terms ) | noprop ( any )* -> INVALID_DECLARATION )
            int alt38=2;
            alt38 = dfa38.predict(input);
            switch (alt38) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:715:4: property COLON ( S )* terms ( important )?
                    {
                    pushFollow(FOLLOW_property_in_declaration658);
                    property64=property();

                    state._fsp--;

                    stream_property.add(property64.getTree());
                    COLON65=(Token)match(input,COLON,FOLLOW_COLON_in_declaration660);  
                    stream_COLON.add(COLON65);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:715:19: ( S )*
                    loop35:
                    do {
                        int alt35=2;
                        alt35 = dfa35.predict(input);
                        switch (alt35) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:715:19: S
                    	    {
                    	    S66=(Token)match(input,S,FOLLOW_S_in_declaration662);  
                    	    stream_S.add(S66);


                    	    }
                    	    break;

                    	default :
                    	    break loop35;
                        }
                    } while (true);

                    pushFollow(FOLLOW_terms_in_declaration665);
                    terms67=terms();

                    state._fsp--;

                    stream_terms.add(terms67.getTree());
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:715:28: ( important )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==EXCLAMATION) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:715:28: important
                            {
                            pushFollow(FOLLOW_important_in_declaration667);
                            important68=important();

                            state._fsp--;

                            stream_important.add(important68.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: property, important, terms
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 715:39: -> ^( DECLARATION ( important )? property terms )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:715:42: ^( DECLARATION ( important )? property terms )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DECLARATION, "DECLARATION"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:715:56: ( important )?
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
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:716:4: noprop ( any )*
                    {
                    pushFollow(FOLLOW_noprop_in_declaration686);
                    noprop69=noprop();

                    state._fsp--;

                    stream_noprop.add(noprop69.getTree());
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:716:11: ( any )*
                    loop37:
                    do {
                        int alt37=2;
                        alt37 = dfa37.predict(input);
                        switch (alt37) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:716:11: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_declaration688);
                    	    any70=any();

                    	    state._fsp--;

                    	    stream_any.add(any70.getTree());

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
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 716:16: -> INVALID_DECLARATION
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(INVALID_DECLARATION, "INVALID_DECLARATION"));

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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:722:1: important : EXCLAMATION ( S )* 'important' ( S )* -> IMPORTANT ;
    public final CSSParser.important_return important() throws RecognitionException {
        CSSParser.important_return retval = new CSSParser.important_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EXCLAMATION71=null;
        Token S72=null;
        Token string_literal73=null;
        Token S74=null;

        Object EXCLAMATION71_tree=null;
        Object S72_tree=null;
        Object string_literal73_tree=null;
        Object S74_tree=null;
        RewriteRuleTokenStream stream_EXCLAMATION=new RewriteRuleTokenStream(adaptor,"token EXCLAMATION");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_88=new RewriteRuleTokenStream(adaptor,"token 88");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:723:5: ( EXCLAMATION ( S )* 'important' ( S )* -> IMPORTANT )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:723:7: EXCLAMATION ( S )* 'important' ( S )*
            {
            EXCLAMATION71=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_important716);  
            stream_EXCLAMATION.add(EXCLAMATION71);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:723:19: ( S )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==S) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:723:19: S
            	    {
            	    S72=(Token)match(input,S,FOLLOW_S_in_important718);  
            	    stream_S.add(S72);


            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);

            string_literal73=(Token)match(input,88,FOLLOW_88_in_important721);  
            stream_88.add(string_literal73);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:723:34: ( S )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==S) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:723:34: S
            	    {
            	    S74=(Token)match(input,S,FOLLOW_S_in_important723);  
            	    stream_S.add(S74);


            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);



            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 723:37: -> IMPORTANT
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:726:1: property : ( MINUS )? IDENT ( S )* -> ( MINUS )? IDENT ;
    public final CSSParser.property_return property() throws RecognitionException {
        CSSParser.property_return retval = new CSSParser.property_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MINUS75=null;
        Token IDENT76=null;
        Token S77=null;

        Object MINUS75_tree=null;
        Object IDENT76_tree=null;
        Object S77_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:727:2: ( ( MINUS )? IDENT ( S )* -> ( MINUS )? IDENT )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:727:4: ( MINUS )? IDENT ( S )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:727:4: ( MINUS )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==MINUS) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:727:4: MINUS
                    {
                    MINUS75=(Token)match(input,MINUS,FOLLOW_MINUS_in_property748);  
                    stream_MINUS.add(MINUS75);


                    }
                    break;

            }

            IDENT76=(Token)match(input,IDENT,FOLLOW_IDENT_in_property751);  
            stream_IDENT.add(IDENT76);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:727:17: ( S )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==S) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:727:17: S
            	    {
            	    S77=(Token)match(input,S,FOLLOW_S_in_property753);  
            	    stream_S.add(S77);


            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);



            // AST REWRITE
            // elements: IDENT, MINUS
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 727:20: -> ( MINUS )? IDENT
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:727:23: ( MINUS )?
                if ( stream_MINUS.hasNext() ) {
                    adaptor.addChild(root_0, stream_MINUS.nextNode());

                }
                stream_MINUS.reset();
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:730:1: terms : ( term )+ -> ^( VALUE ( term )+ ) ;
    public final CSSParser.terms_return terms() throws RecognitionException {
        CSSParser.terms_return retval = new CSSParser.terms_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CSSParser.term_return term78 = null;


        RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:731:2: ( ( term )+ -> ^( VALUE ( term )+ ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:731:4: ( term )+
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:731:4: ( term )+
            int cnt43=0;
            loop43:
            do {
                int alt43=2;
                alt43 = dfa43.predict(input);
                switch (alt43) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:731:4: term
            	    {
            	    pushFollow(FOLLOW_term_in_terms781);
            	    term78=term();

            	    state._fsp--;

            	    stream_term.add(term78.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt43 >= 1 ) break loop43;
                        EarlyExitException eee =
                            new EarlyExitException(43, input);
                        throw eee;
                }
                cnt43++;
            } while (true);



            // AST REWRITE
            // elements: term
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 732:2: -> ^( VALUE ( term )+ )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:732:5: ^( VALUE ( term )+ )
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

            		if (functLevel == 0)
            		{
            	      final BitSet follow = BitSet.of(CSSLexer.RCURLY, CSSLexer.SEMICOLON);								
            		    retval.tree = invalidFallbackGreedy(CSSLexer.INVALID_STATEMENT, 
            		  		"INVALID_STATEMENT", follow, re);
            		}
            		else
            		{
                    final BitSet follow = BitSet.of(CSSLexer.RPAREN, CSSLexer.RCURLY, CSSLexer.SEMICOLON);               
                    retval.tree = invalidFallbackGreedyFunction(CSSLexer.INVALID_STATEMENT, 
                      "INVALID_STATEMENT", follow, re);
            		}
            	
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:749:1: term : ( valuepart -> valuepart | LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY -> CURLYBLOCK | ATKEYWORD ( S )* -> ATKEYWORD );
    public final CSSParser.term_return term() throws RecognitionException {
        CSSParser.term_return retval = new CSSParser.term_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LCURLY80=null;
        Token S81=null;
        Token SEMICOLON83=null;
        Token S84=null;
        Token RCURLY85=null;
        Token ATKEYWORD86=null;
        Token S87=null;
        CSSParser.valuepart_return valuepart79 = null;

        CSSParser.any_return any82 = null;


        Object LCURLY80_tree=null;
        Object S81_tree=null;
        Object SEMICOLON83_tree=null;
        Object S84_tree=null;
        Object RCURLY85_tree=null;
        Object ATKEYWORD86_tree=null;
        Object S87_tree=null;
        RewriteRuleTokenStream stream_ATKEYWORD=new RewriteRuleTokenStream(adaptor,"token ATKEYWORD");
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_valuepart=new RewriteRuleSubtreeStream(adaptor,"rule valuepart");
        RewriteRuleSubtreeStream stream_any=new RewriteRuleSubtreeStream(adaptor,"rule any");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:750:5: ( valuepart -> valuepart | LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY -> CURLYBLOCK | ATKEYWORD ( S )* -> ATKEYWORD )
            int alt48=3;
            alt48 = dfa48.predict(input);
            switch (alt48) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:750:7: valuepart
                    {
                    pushFollow(FOLLOW_valuepart_in_term814);
                    valuepart79=valuepart();

                    state._fsp--;

                    stream_valuepart.add(valuepart79.getTree());


                    // AST REWRITE
                    // elements: valuepart
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 750:17: -> valuepart
                    {
                        adaptor.addChild(root_0, stream_valuepart.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:751:7: LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY
                    {
                    LCURLY80=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_term826);  
                    stream_LCURLY.add(LCURLY80);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:751:14: ( S )*
                    loop44:
                    do {
                        int alt44=2;
                        alt44 = dfa44.predict(input);
                        switch (alt44) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:751:14: S
                    	    {
                    	    S81=(Token)match(input,S,FOLLOW_S_in_term828);  
                    	    stream_S.add(S81);


                    	    }
                    	    break;

                    	default :
                    	    break loop44;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:751:17: ( any | SEMICOLON ( S )* )*
                    loop46:
                    do {
                        int alt46=3;
                        alt46 = dfa46.predict(input);
                        switch (alt46) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:751:18: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_term832);
                    	    any82=any();

                    	    state._fsp--;

                    	    stream_any.add(any82.getTree());

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:751:24: SEMICOLON ( S )*
                    	    {
                    	    SEMICOLON83=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_term836);  
                    	    stream_SEMICOLON.add(SEMICOLON83);

                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:751:34: ( S )*
                    	    loop45:
                    	    do {
                    	        int alt45=2;
                    	        alt45 = dfa45.predict(input);
                    	        switch (alt45) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:751:34: S
                    	    	    {
                    	    	    S84=(Token)match(input,S,FOLLOW_S_in_term838);  
                    	    	    stream_S.add(S84);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop45;
                    	        }
                    	    } while (true);


                    	    }
                    	    break;

                    	default :
                    	    break loop46;
                        }
                    } while (true);

                    RCURLY85=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_term843);  
                    stream_RCURLY.add(RCURLY85);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 751:46: -> CURLYBLOCK
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(CURLYBLOCK, "CURLYBLOCK"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:752:7: ATKEYWORD ( S )*
                    {
                    ATKEYWORD86=(Token)match(input,ATKEYWORD,FOLLOW_ATKEYWORD_in_term855);  
                    stream_ATKEYWORD.add(ATKEYWORD86);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:752:17: ( S )*
                    loop47:
                    do {
                        int alt47=2;
                        alt47 = dfa47.predict(input);
                        switch (alt47) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:752:17: S
                    	    {
                    	    S87=(Token)match(input,S,FOLLOW_S_in_term857);  
                    	    stream_S.add(S87);


                    	    }
                    	    break;

                    	default :
                    	    break loop47;
                        }
                    } while (true);



                    // AST REWRITE
                    // elements: ATKEYWORD
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 752:20: -> ATKEYWORD
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

    public static class funct_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "funct"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:755:1: funct : ( EXPRESSION -> EXPRESSION | FUNCTION ( S )* ( terms )? RPAREN -> ^( FUNCTION ( terms )? ) );
    public final CSSParser.funct_return funct() throws RecognitionException {
        CSSParser.funct_return retval = new CSSParser.funct_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EXPRESSION88=null;
        Token FUNCTION89=null;
        Token S90=null;
        Token RPAREN92=null;
        CSSParser.terms_return terms91 = null;


        Object EXPRESSION88_tree=null;
        Object FUNCTION89_tree=null;
        Object S90_tree=null;
        Object RPAREN92_tree=null;
        RewriteRuleTokenStream stream_FUNCTION=new RewriteRuleTokenStream(adaptor,"token FUNCTION");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_EXPRESSION=new RewriteRuleTokenStream(adaptor,"token EXPRESSION");
        RewriteRuleSubtreeStream stream_terms=new RewriteRuleSubtreeStream(adaptor,"rule terms");

        	functLevel++;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:763:3: ( EXPRESSION -> EXPRESSION | FUNCTION ( S )* ( terms )? RPAREN -> ^( FUNCTION ( terms )? ) )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==EXPRESSION) ) {
                alt51=1;
            }
            else if ( (LA51_0==FUNCTION) ) {
                alt51=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }
            switch (alt51) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:763:5: EXPRESSION
                    {
                    EXPRESSION88=(Token)match(input,EXPRESSION,FOLLOW_EXPRESSION_in_funct890);  
                    stream_EXPRESSION.add(EXPRESSION88);



                    // AST REWRITE
                    // elements: EXPRESSION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 763:16: -> EXPRESSION
                    {
                        adaptor.addChild(root_0, stream_EXPRESSION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:764:4: FUNCTION ( S )* ( terms )? RPAREN
                    {
                    FUNCTION89=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_funct899);  
                    stream_FUNCTION.add(FUNCTION89);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:764:13: ( S )*
                    loop49:
                    do {
                        int alt49=2;
                        alt49 = dfa49.predict(input);
                        switch (alt49) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:764:13: S
                    	    {
                    	    S90=(Token)match(input,S,FOLLOW_S_in_funct901);  
                    	    stream_S.add(S90);


                    	    }
                    	    break;

                    	default :
                    	    break loop49;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:764:16: ( terms )?
                    int alt50=2;
                    alt50 = dfa50.predict(input);
                    switch (alt50) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:764:16: terms
                            {
                            pushFollow(FOLLOW_terms_in_funct904);
                            terms91=terms();

                            state._fsp--;

                            stream_terms.add(terms91.getTree());

                            }
                            break;

                    }

                    RPAREN92=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_funct907);  
                    stream_RPAREN.add(RPAREN92);



                    // AST REWRITE
                    // elements: FUNCTION, terms
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 764:30: -> ^( FUNCTION ( terms )? )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:764:33: ^( FUNCTION ( terms )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_FUNCTION.nextNode(), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:764:44: ( terms )?
                        if ( stream_terms.hasNext() ) {
                            adaptor.addChild(root_1, stream_terms.nextTree());

                        }
                        stream_terms.reset();

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


            	functLevel--;

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
    // $ANTLR end "funct"

    public static class valuepart_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "valuepart"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:767:1: valuepart : ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | funct -> funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) ) ( S )* ;
    public final CSSParser.valuepart_return valuepart() throws RecognitionException {
        CSSParser.valuepart_return retval = new CSSParser.valuepart_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MINUS93=null;
        Token IDENT94=null;
        Token CLASSKEYWORD95=null;
        Token MINUS96=null;
        Token NUMBER97=null;
        Token MINUS98=null;
        Token PERCENTAGE99=null;
        Token MINUS100=null;
        Token DIMENSION101=null;
        Token URI103=null;
        Token HASH104=null;
        Token UNIRANGE105=null;
        Token INCLUDES106=null;
        Token COLON107=null;
        Token COMMA108=null;
        Token GREATER109=null;
        Token LESS110=null;
        Token QUESTION111=null;
        Token PERCENT112=null;
        Token EQUALS113=null;
        Token SLASH114=null;
        Token PLUS115=null;
        Token ASTERISK116=null;
        Token DASHMATCH118=null;
        Token LPAREN119=null;
        Token RPAREN121=null;
        Token LBRACE122=null;
        Token RBRACE124=null;
        Token S125=null;
        CSSParser.string_return string102 = null;

        CSSParser.funct_return funct117 = null;

        CSSParser.valuepart_return valuepart120 = null;

        CSSParser.valuepart_return valuepart123 = null;


        Object MINUS93_tree=null;
        Object IDENT94_tree=null;
        Object CLASSKEYWORD95_tree=null;
        Object MINUS96_tree=null;
        Object NUMBER97_tree=null;
        Object MINUS98_tree=null;
        Object PERCENTAGE99_tree=null;
        Object MINUS100_tree=null;
        Object DIMENSION101_tree=null;
        Object URI103_tree=null;
        Object HASH104_tree=null;
        Object UNIRANGE105_tree=null;
        Object INCLUDES106_tree=null;
        Object COLON107_tree=null;
        Object COMMA108_tree=null;
        Object GREATER109_tree=null;
        Object LESS110_tree=null;
        Object QUESTION111_tree=null;
        Object PERCENT112_tree=null;
        Object EQUALS113_tree=null;
        Object SLASH114_tree=null;
        Object PLUS115_tree=null;
        Object ASTERISK116_tree=null;
        Object DASHMATCH118_tree=null;
        Object LPAREN119_tree=null;
        Object RPAREN121_tree=null;
        Object LBRACE122_tree=null;
        Object RBRACE124_tree=null;
        Object S125_tree=null;
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
        RewriteRuleTokenStream stream_CLASSKEYWORD=new RewriteRuleTokenStream(adaptor,"token CLASSKEYWORD");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleTokenStream stream_NUMBER=new RewriteRuleTokenStream(adaptor,"token NUMBER");
        RewriteRuleTokenStream stream_HASH=new RewriteRuleTokenStream(adaptor,"token HASH");
        RewriteRuleTokenStream stream_EQUALS=new RewriteRuleTokenStream(adaptor,"token EQUALS");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");
        RewriteRuleTokenStream stream_PERCENTAGE=new RewriteRuleTokenStream(adaptor,"token PERCENTAGE");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleTokenStream stream_ASTERISK=new RewriteRuleTokenStream(adaptor,"token ASTERISK");
        RewriteRuleTokenStream stream_URI=new RewriteRuleTokenStream(adaptor,"token URI");
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_INCLUDES=new RewriteRuleTokenStream(adaptor,"token INCLUDES");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleTokenStream stream_SLASH=new RewriteRuleTokenStream(adaptor,"token SLASH");
        RewriteRuleTokenStream stream_DASHMATCH=new RewriteRuleTokenStream(adaptor,"token DASHMATCH");
        RewriteRuleTokenStream stream_QUESTION=new RewriteRuleTokenStream(adaptor,"token QUESTION");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_LESS=new RewriteRuleTokenStream(adaptor,"token LESS");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
        RewriteRuleTokenStream stream_UNIRANGE=new RewriteRuleTokenStream(adaptor,"token UNIRANGE");
        RewriteRuleTokenStream stream_DIMENSION=new RewriteRuleTokenStream(adaptor,"token DIMENSION");
        RewriteRuleSubtreeStream stream_valuepart=new RewriteRuleSubtreeStream(adaptor,"rule valuepart");
        RewriteRuleSubtreeStream stream_string=new RewriteRuleSubtreeStream(adaptor,"rule string");
        RewriteRuleSubtreeStream stream_funct=new RewriteRuleSubtreeStream(adaptor,"rule funct");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:769:5: ( ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | funct -> funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) ) ( S )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:769:7: ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | funct -> funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) ) ( S )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:769:7: ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | funct -> funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) )
            int alt58=24;
            alt58 = dfa58.predict(input);
            switch (alt58) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:769:9: ( MINUS )? IDENT
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:769:9: ( MINUS )?
                    int alt52=2;
                    int LA52_0 = input.LA(1);

                    if ( (LA52_0==MINUS) ) {
                        alt52=1;
                    }
                    switch (alt52) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:769:9: MINUS
                            {
                            MINUS93=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart934);  
                            stream_MINUS.add(MINUS93);


                            }
                            break;

                    }

                    IDENT94=(Token)match(input,IDENT,FOLLOW_IDENT_in_valuepart937);  
                    stream_IDENT.add(IDENT94);



                    // AST REWRITE
                    // elements: IDENT, MINUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 769:22: -> ( MINUS )? IDENT
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:769:25: ( MINUS )?
                        if ( stream_MINUS.hasNext() ) {
                            adaptor.addChild(root_0, stream_MINUS.nextNode());

                        }
                        stream_MINUS.reset();
                        adaptor.addChild(root_0, stream_IDENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:770:9: CLASSKEYWORD
                    {
                    CLASSKEYWORD95=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_valuepart954);  
                    stream_CLASSKEYWORD.add(CLASSKEYWORD95);



                    // AST REWRITE
                    // elements: CLASSKEYWORD
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 770:22: -> CLASSKEYWORD
                    {
                        adaptor.addChild(root_0, stream_CLASSKEYWORD.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:771:9: ( MINUS )? NUMBER
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:771:9: ( MINUS )?
                    int alt53=2;
                    int LA53_0 = input.LA(1);

                    if ( (LA53_0==MINUS) ) {
                        alt53=1;
                    }
                    switch (alt53) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:771:9: MINUS
                            {
                            MINUS96=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart968);  
                            stream_MINUS.add(MINUS96);


                            }
                            break;

                    }

                    NUMBER97=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_valuepart971);  
                    stream_NUMBER.add(NUMBER97);



                    // AST REWRITE
                    // elements: NUMBER, MINUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 771:23: -> ( MINUS )? NUMBER
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:771:26: ( MINUS )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:772:9: ( MINUS )? PERCENTAGE
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:772:9: ( MINUS )?
                    int alt54=2;
                    int LA54_0 = input.LA(1);

                    if ( (LA54_0==MINUS) ) {
                        alt54=1;
                    }
                    switch (alt54) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:772:9: MINUS
                            {
                            MINUS98=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart988);  
                            stream_MINUS.add(MINUS98);


                            }
                            break;

                    }

                    PERCENTAGE99=(Token)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_valuepart991);  
                    stream_PERCENTAGE.add(PERCENTAGE99);



                    // AST REWRITE
                    // elements: PERCENTAGE, MINUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 772:27: -> ( MINUS )? PERCENTAGE
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:772:30: ( MINUS )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:773:9: ( MINUS )? DIMENSION
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:773:9: ( MINUS )?
                    int alt55=2;
                    int LA55_0 = input.LA(1);

                    if ( (LA55_0==MINUS) ) {
                        alt55=1;
                    }
                    switch (alt55) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:773:9: MINUS
                            {
                            MINUS100=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart1008);  
                            stream_MINUS.add(MINUS100);


                            }
                            break;

                    }

                    DIMENSION101=(Token)match(input,DIMENSION,FOLLOW_DIMENSION_in_valuepart1011);  
                    stream_DIMENSION.add(DIMENSION101);



                    // AST REWRITE
                    // elements: DIMENSION, MINUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 773:26: -> ( MINUS )? DIMENSION
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:773:29: ( MINUS )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:774:9: string
                    {
                    pushFollow(FOLLOW_string_in_valuepart1028);
                    string102=string();

                    state._fsp--;

                    stream_string.add(string102.getTree());


                    // AST REWRITE
                    // elements: string
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 774:16: -> string
                    {
                        adaptor.addChild(root_0, stream_string.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:775:9: URI
                    {
                    URI103=(Token)match(input,URI,FOLLOW_URI_in_valuepart1042);  
                    stream_URI.add(URI103);



                    // AST REWRITE
                    // elements: URI
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 775:16: -> URI
                    {
                        adaptor.addChild(root_0, stream_URI.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:776:9: HASH
                    {
                    HASH104=(Token)match(input,HASH,FOLLOW_HASH_in_valuepart1059);  
                    stream_HASH.add(HASH104);



                    // AST REWRITE
                    // elements: HASH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 776:14: -> HASH
                    {
                        adaptor.addChild(root_0, stream_HASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:9: UNIRANGE
                    {
                    UNIRANGE105=(Token)match(input,UNIRANGE,FOLLOW_UNIRANGE_in_valuepart1073);  
                    stream_UNIRANGE.add(UNIRANGE105);



                    // AST REWRITE
                    // elements: UNIRANGE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 777:18: -> UNIRANGE
                    {
                        adaptor.addChild(root_0, stream_UNIRANGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:778:9: INCLUDES
                    {
                    INCLUDES106=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_valuepart1087);  
                    stream_INCLUDES.add(INCLUDES106);



                    // AST REWRITE
                    // elements: INCLUDES
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 778:18: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:779:9: COLON
                    {
                    COLON107=(Token)match(input,COLON,FOLLOW_COLON_in_valuepart1101);  
                    stream_COLON.add(COLON107);



                    // AST REWRITE
                    // elements: COLON
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 779:15: -> COLON
                    {
                        adaptor.addChild(root_0, stream_COLON.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:780:9: COMMA
                    {
                    COMMA108=(Token)match(input,COMMA,FOLLOW_COMMA_in_valuepart1115);  
                    stream_COMMA.add(COMMA108);



                    // AST REWRITE
                    // elements: COMMA
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 780:15: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:781:9: GREATER
                    {
                    GREATER109=(Token)match(input,GREATER,FOLLOW_GREATER_in_valuepart1129);  
                    stream_GREATER.add(GREATER109);



                    // AST REWRITE
                    // elements: GREATER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 781:17: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:782:9: LESS
                    {
                    LESS110=(Token)match(input,LESS,FOLLOW_LESS_in_valuepart1143);  
                    stream_LESS.add(LESS110);



                    // AST REWRITE
                    // elements: LESS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 782:14: -> LESS
                    {
                        adaptor.addChild(root_0, stream_LESS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:783:9: QUESTION
                    {
                    QUESTION111=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_valuepart1157);  
                    stream_QUESTION.add(QUESTION111);



                    // AST REWRITE
                    // elements: QUESTION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 783:18: -> QUESTION
                    {
                        adaptor.addChild(root_0, stream_QUESTION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:784:9: PERCENT
                    {
                    PERCENT112=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_valuepart1171);  
                    stream_PERCENT.add(PERCENT112);



                    // AST REWRITE
                    // elements: PERCENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 784:17: -> PERCENT
                    {
                        adaptor.addChild(root_0, stream_PERCENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:785:9: EQUALS
                    {
                    EQUALS113=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_valuepart1185);  
                    stream_EQUALS.add(EQUALS113);



                    // AST REWRITE
                    // elements: EQUALS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 785:16: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 18 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:786:9: SLASH
                    {
                    SLASH114=(Token)match(input,SLASH,FOLLOW_SLASH_in_valuepart1199);  
                    stream_SLASH.add(SLASH114);



                    // AST REWRITE
                    // elements: SLASH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 786:15: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 19 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:787:8: PLUS
                    {
                    PLUS115=(Token)match(input,PLUS,FOLLOW_PLUS_in_valuepart1212);  
                    stream_PLUS.add(PLUS115);



                    // AST REWRITE
                    // elements: PLUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 787:13: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 20 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:8: ASTERISK
                    {
                    ASTERISK116=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_valuepart1225);  
                    stream_ASTERISK.add(ASTERISK116);



                    // AST REWRITE
                    // elements: ASTERISK
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 788:17: -> ASTERISK
                    {
                        adaptor.addChild(root_0, stream_ASTERISK.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 21 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:789:9: funct
                    {
                    pushFollow(FOLLOW_funct_in_valuepart1242);
                    funct117=funct();

                    state._fsp--;

                    stream_funct.add(funct117.getTree());


                    // AST REWRITE
                    // elements: funct
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 789:15: -> funct
                    {
                        adaptor.addChild(root_0, stream_funct.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 22 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:790:9: DASHMATCH
                    {
                    DASHMATCH118=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_valuepart1257);  
                    stream_DASHMATCH.add(DASHMATCH118);



                    // AST REWRITE
                    // elements: DASHMATCH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 790:19: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 23 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:791:9: LPAREN ( valuepart )* RPAREN
                    {
                    LPAREN119=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_valuepart1271);  
                    stream_LPAREN.add(LPAREN119);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:791:16: ( valuepart )*
                    loop56:
                    do {
                        int alt56=2;
                        alt56 = dfa56.predict(input);
                        switch (alt56) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:791:16: valuepart
                    	    {
                    	    pushFollow(FOLLOW_valuepart_in_valuepart1273);
                    	    valuepart120=valuepart();

                    	    state._fsp--;

                    	    stream_valuepart.add(valuepart120.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop56;
                        }
                    } while (true);

                    RPAREN121=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_valuepart1276);  
                    stream_RPAREN.add(RPAREN121);



                    // AST REWRITE
                    // elements: valuepart
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 791:34: -> ^( PARENBLOCK ( valuepart )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:791:37: ^( PARENBLOCK ( valuepart )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PARENBLOCK, "PARENBLOCK"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:791:50: ( valuepart )*
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
                case 24 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:792:9: LBRACE ( valuepart )* RBRACE
                    {
                    LBRACE122=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_valuepart1295);  
                    stream_LBRACE.add(LBRACE122);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:792:16: ( valuepart )*
                    loop57:
                    do {
                        int alt57=2;
                        alt57 = dfa57.predict(input);
                        switch (alt57) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:792:16: valuepart
                    	    {
                    	    pushFollow(FOLLOW_valuepart_in_valuepart1297);
                    	    valuepart123=valuepart();

                    	    state._fsp--;

                    	    stream_valuepart.add(valuepart123.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop57;
                        }
                    } while (true);

                    RBRACE124=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_valuepart1300);  
                    stream_RBRACE.add(RBRACE124);



                    // AST REWRITE
                    // elements: valuepart
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 792:34: -> ^( BRACEBLOCK ( valuepart )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:792:37: ^( BRACEBLOCK ( valuepart )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BRACEBLOCK, "BRACEBLOCK"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:792:50: ( valuepart )*
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:793:8: ( S )*
            loop59:
            do {
                int alt59=2;
                alt59 = dfa59.predict(input);
                switch (alt59) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:793:8: S
            	    {
            	    S125=(Token)match(input,S,FOLLOW_S_in_valuepart1318);  
            	    stream_S.add(S125);


            	    }
            	    break;

            	default :
            	    break loop59;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:796:1: combined_selector : selector ( ( combinator ) selector )* ;
    public final CSSParser.combined_selector_return combined_selector() throws RecognitionException {
        CSSParser.combined_selector_return retval = new CSSParser.combined_selector_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CSSParser.selector_return selector126 = null;

        CSSParser.combinator_return combinator127 = null;

        CSSParser.selector_return selector128 = null;



        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:797:2: ( selector ( ( combinator ) selector )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:797:4: selector ( ( combinator ) selector )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_selector_in_combined_selector1335);
            selector126=selector();

            state._fsp--;

            adaptor.addChild(root_0, selector126.getTree());
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:797:13: ( ( combinator ) selector )*
            loop60:
            do {
                int alt60=2;
                int LA60_0 = input.LA(1);

                if ( (LA60_0==S||LA60_0==GREATER||LA60_0==PLUS) ) {
                    alt60=1;
                }


                switch (alt60) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:797:14: ( combinator ) selector
            	    {
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:797:14: ( combinator )
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:797:15: combinator
            	    {
            	    pushFollow(FOLLOW_combinator_in_combined_selector1339);
            	    combinator127=combinator();

            	    state._fsp--;

            	    adaptor.addChild(root_0, combinator127.getTree());

            	    }

            	    pushFollow(FOLLOW_selector_in_combined_selector1342);
            	    selector128=selector();

            	    state._fsp--;

            	    adaptor.addChild(root_0, selector128.getTree());

            	    }
            	    break;

            	default :
            	    break loop60;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:805:1: combinator : ( GREATER ( S )* -> CHILD | PLUS ( S )* -> ADJACENT | S -> DESCENDANT );
    public final CSSParser.combinator_return combinator() throws RecognitionException {
        CSSParser.combinator_return retval = new CSSParser.combinator_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token GREATER129=null;
        Token S130=null;
        Token PLUS131=null;
        Token S132=null;
        Token S133=null;

        Object GREATER129_tree=null;
        Object S130_tree=null;
        Object PLUS131_tree=null;
        Object S132_tree=null;
        Object S133_tree=null;
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:806:2: ( GREATER ( S )* -> CHILD | PLUS ( S )* -> ADJACENT | S -> DESCENDANT )
            int alt63=3;
            switch ( input.LA(1) ) {
            case GREATER:
                {
                alt63=1;
                }
                break;
            case PLUS:
                {
                alt63=2;
                }
                break;
            case S:
                {
                alt63=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }

            switch (alt63) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:806:4: GREATER ( S )*
                    {
                    GREATER129=(Token)match(input,GREATER,FOLLOW_GREATER_in_combinator1362);  
                    stream_GREATER.add(GREATER129);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:806:12: ( S )*
                    loop61:
                    do {
                        int alt61=2;
                        int LA61_0 = input.LA(1);

                        if ( (LA61_0==S) ) {
                            alt61=1;
                        }


                        switch (alt61) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:806:12: S
                    	    {
                    	    S130=(Token)match(input,S,FOLLOW_S_in_combinator1364);  
                    	    stream_S.add(S130);


                    	    }
                    	    break;

                    	default :
                    	    break loop61;
                        }
                    } while (true);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 806:15: -> CHILD
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(CHILD, "CHILD"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:807:4: PLUS ( S )*
                    {
                    PLUS131=(Token)match(input,PLUS,FOLLOW_PLUS_in_combinator1374);  
                    stream_PLUS.add(PLUS131);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:807:9: ( S )*
                    loop62:
                    do {
                        int alt62=2;
                        int LA62_0 = input.LA(1);

                        if ( (LA62_0==S) ) {
                            alt62=1;
                        }


                        switch (alt62) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:807:9: S
                    	    {
                    	    S132=(Token)match(input,S,FOLLOW_S_in_combinator1376);  
                    	    stream_S.add(S132);


                    	    }
                    	    break;

                    	default :
                    	    break loop62;
                        }
                    } while (true);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 807:12: -> ADJACENT
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(ADJACENT, "ADJACENT"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:808:4: S
                    {
                    S133=(Token)match(input,S,FOLLOW_S_in_combinator1386);  
                    stream_S.add(S133);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 808:6: -> DESCENDANT
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:811:1: selector : ( ( IDENT | ASTERISK ) ( selpart )* ( S )* -> ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* ) | ( selpart )+ ( S )* -> ^( SELECTOR ( selpart )+ ) );
    public final CSSParser.selector_return selector() throws RecognitionException {
        CSSParser.selector_return retval = new CSSParser.selector_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT134=null;
        Token ASTERISK135=null;
        Token S137=null;
        Token S139=null;
        CSSParser.selpart_return selpart136 = null;

        CSSParser.selpart_return selpart138 = null;


        Object IDENT134_tree=null;
        Object ASTERISK135_tree=null;
        Object S137_tree=null;
        Object S139_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_ASTERISK=new RewriteRuleTokenStream(adaptor,"token ASTERISK");
        RewriteRuleSubtreeStream stream_selpart=new RewriteRuleSubtreeStream(adaptor,"rule selpart");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:812:5: ( ( IDENT | ASTERISK ) ( selpart )* ( S )* -> ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* ) | ( selpart )+ ( S )* -> ^( SELECTOR ( selpart )+ ) )
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==IDENT||LA69_0==ASTERISK) ) {
                alt69=1;
            }
            else if ( (LA69_0==INVALID_SELPART||LA69_0==COLON||LA69_0==CLASSKEYWORD||LA69_0==HASH||LA69_0==LBRACE) ) {
                alt69=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 69, 0, input);

                throw nvae;
            }
            switch (alt69) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:812:7: ( IDENT | ASTERISK ) ( selpart )* ( S )*
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:812:7: ( IDENT | ASTERISK )
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==IDENT) ) {
                        alt64=1;
                    }
                    else if ( (LA64_0==ASTERISK) ) {
                        alt64=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 64, 0, input);

                        throw nvae;
                    }
                    switch (alt64) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:812:8: IDENT
                            {
                            IDENT134=(Token)match(input,IDENT,FOLLOW_IDENT_in_selector1405);  
                            stream_IDENT.add(IDENT134);


                            }
                            break;
                        case 2 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:812:16: ASTERISK
                            {
                            ASTERISK135=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_selector1409);  
                            stream_ASTERISK.add(ASTERISK135);


                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:812:27: ( selpart )*
                    loop65:
                    do {
                        int alt65=2;
                        alt65 = dfa65.predict(input);
                        switch (alt65) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:812:27: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1413);
                    	    selpart136=selpart();

                    	    state._fsp--;

                    	    stream_selpart.add(selpart136.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop65;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:812:36: ( S )*
                    loop66:
                    do {
                        int alt66=2;
                        alt66 = dfa66.predict(input);
                        switch (alt66) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:812:36: S
                    	    {
                    	    S137=(Token)match(input,S,FOLLOW_S_in_selector1416);  
                    	    stream_S.add(S137);


                    	    }
                    	    break;

                    	default :
                    	    break loop66;
                        }
                    } while (true);



                    // AST REWRITE
                    // elements: selpart, IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 813:6: -> ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:813:9: ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SELECTOR, "SELECTOR"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:813:20: ^( ELEMENT ( IDENT )? )
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ELEMENT, "ELEMENT"), root_2);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:813:30: ( IDENT )?
                        if ( stream_IDENT.hasNext() ) {
                            adaptor.addChild(root_2, stream_IDENT.nextNode());

                        }
                        stream_IDENT.reset();

                        adaptor.addChild(root_1, root_2);
                        }
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:813:38: ( selpart )*
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:814:7: ( selpart )+ ( S )*
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:814:7: ( selpart )+
                    int cnt67=0;
                    loop67:
                    do {
                        int alt67=2;
                        alt67 = dfa67.predict(input);
                        switch (alt67) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:814:7: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1446);
                    	    selpart138=selpart();

                    	    state._fsp--;

                    	    stream_selpart.add(selpart138.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt67 >= 1 ) break loop67;
                                EarlyExitException eee =
                                    new EarlyExitException(67, input);
                                throw eee;
                        }
                        cnt67++;
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:814:16: ( S )*
                    loop68:
                    do {
                        int alt68=2;
                        alt68 = dfa68.predict(input);
                        switch (alt68) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:814:16: S
                    	    {
                    	    S139=(Token)match(input,S,FOLLOW_S_in_selector1449);  
                    	    stream_S.add(S139);


                    	    }
                    	    break;

                    	default :
                    	    break loop68;
                        }
                    } while (true);



                    // AST REWRITE
                    // elements: selpart
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 815:9: -> ^( SELECTOR ( selpart )+ )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:815:12: ^( SELECTOR ( selpart )+ )
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:821:1: selpart : ( HASH | CLASSKEYWORD | LBRACE ( S )* attribute RBRACE -> ^( ATTRIBUTE attribute ) | pseudo | INVALID_SELPART );
    public final CSSParser.selpart_return selpart() throws RecognitionException {
        CSSParser.selpart_return retval = new CSSParser.selpart_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token HASH140=null;
        Token CLASSKEYWORD141=null;
        Token LBRACE142=null;
        Token S143=null;
        Token RBRACE145=null;
        Token INVALID_SELPART147=null;
        CSSParser.attribute_return attribute144 = null;

        CSSParser.pseudo_return pseudo146 = null;


        Object HASH140_tree=null;
        Object CLASSKEYWORD141_tree=null;
        Object LBRACE142_tree=null;
        Object S143_tree=null;
        Object RBRACE145_tree=null;
        Object INVALID_SELPART147_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleSubtreeStream stream_attribute=new RewriteRuleSubtreeStream(adaptor,"rule attribute");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:822:5: ( HASH | CLASSKEYWORD | LBRACE ( S )* attribute RBRACE -> ^( ATTRIBUTE attribute ) | pseudo | INVALID_SELPART )
            int alt71=5;
            switch ( input.LA(1) ) {
            case HASH:
                {
                alt71=1;
                }
                break;
            case CLASSKEYWORD:
                {
                alt71=2;
                }
                break;
            case LBRACE:
                {
                alt71=3;
                }
                break;
            case COLON:
                {
                alt71=4;
                }
                break;
            case INVALID_SELPART:
                {
                alt71=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;
            }

            switch (alt71) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:822:8: HASH
                    {
                    root_0 = (Object)adaptor.nil();

                    HASH140=(Token)match(input,HASH,FOLLOW_HASH_in_selpart1496); 
                    HASH140_tree = (Object)adaptor.create(HASH140);
                    adaptor.addChild(root_0, HASH140_tree);


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:823:7: CLASSKEYWORD
                    {
                    root_0 = (Object)adaptor.nil();

                    CLASSKEYWORD141=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_selpart1504); 
                    CLASSKEYWORD141_tree = (Object)adaptor.create(CLASSKEYWORD141);
                    adaptor.addChild(root_0, CLASSKEYWORD141_tree);


                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:824:6: LBRACE ( S )* attribute RBRACE
                    {
                    LBRACE142=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_selpart1511);  
                    stream_LBRACE.add(LBRACE142);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:824:13: ( S )*
                    loop70:
                    do {
                        int alt70=2;
                        int LA70_0 = input.LA(1);

                        if ( (LA70_0==S) ) {
                            alt70=1;
                        }


                        switch (alt70) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:824:13: S
                    	    {
                    	    S143=(Token)match(input,S,FOLLOW_S_in_selpart1513);  
                    	    stream_S.add(S143);


                    	    }
                    	    break;

                    	default :
                    	    break loop70;
                        }
                    } while (true);

                    pushFollow(FOLLOW_attribute_in_selpart1516);
                    attribute144=attribute();

                    state._fsp--;

                    stream_attribute.add(attribute144.getTree());
                    RBRACE145=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_selpart1518);  
                    stream_RBRACE.add(RBRACE145);



                    // AST REWRITE
                    // elements: attribute
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 824:33: -> ^( ATTRIBUTE attribute )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:824:36: ^( ATTRIBUTE attribute )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:825:7: pseudo
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pseudo_in_selpart1534);
                    pseudo146=pseudo();

                    state._fsp--;

                    adaptor.addChild(root_0, pseudo146.getTree());

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:826:7: INVALID_SELPART
                    {
                    root_0 = (Object)adaptor.nil();

                    INVALID_SELPART147=(Token)match(input,INVALID_SELPART,FOLLOW_INVALID_SELPART_in_selpart1542); 
                    INVALID_SELPART147_tree = (Object)adaptor.create(INVALID_SELPART147);
                    adaptor.addChild(root_0, INVALID_SELPART147_tree);


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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:832:1: attribute : IDENT ( S )* ( ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )* )? ;
    public final CSSParser.attribute_return attribute() throws RecognitionException {
        CSSParser.attribute_return retval = new CSSParser.attribute_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT148=null;
        Token S149=null;
        Token set150=null;
        Token S151=null;
        Token IDENT152=null;
        Token S154=null;
        CSSParser.string_return string153 = null;


        Object IDENT148_tree=null;
        Object S149_tree=null;
        Object set150_tree=null;
        Object S151_tree=null;
        Object IDENT152_tree=null;
        Object S154_tree=null;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:833:2: ( IDENT ( S )* ( ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )* )? )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:833:4: IDENT ( S )* ( ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )* )?
            {
            root_0 = (Object)adaptor.nil();

            IDENT148=(Token)match(input,IDENT,FOLLOW_IDENT_in_attribute1566); 
            IDENT148_tree = (Object)adaptor.create(IDENT148);
            adaptor.addChild(root_0, IDENT148_tree);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:833:10: ( S )*
            loop72:
            do {
                int alt72=2;
                int LA72_0 = input.LA(1);

                if ( (LA72_0==S) ) {
                    alt72=1;
                }


                switch (alt72) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:833:10: S
            	    {
            	    S149=(Token)match(input,S,FOLLOW_S_in_attribute1568); 
            	    S149_tree = (Object)adaptor.create(S149);
            	    adaptor.addChild(root_0, S149_tree);


            	    }
            	    break;

            	default :
            	    break loop72;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:834:4: ( ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )* )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==INCLUDES||LA76_0==EQUALS||LA76_0==DASHMATCH) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:834:5: ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )*
                    {
                    set150=(Token)input.LT(1);
                    if ( input.LA(1)==INCLUDES||input.LA(1)==EQUALS||input.LA(1)==DASHMATCH ) {
                        input.consume();
                        adaptor.addChild(root_0, (Object)adaptor.create(set150));
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:834:37: ( S )*
                    loop73:
                    do {
                        int alt73=2;
                        int LA73_0 = input.LA(1);

                        if ( (LA73_0==S) ) {
                            alt73=1;
                        }


                        switch (alt73) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:834:37: S
                    	    {
                    	    S151=(Token)match(input,S,FOLLOW_S_in_attribute1587); 
                    	    S151_tree = (Object)adaptor.create(S151);
                    	    adaptor.addChild(root_0, S151_tree);


                    	    }
                    	    break;

                    	default :
                    	    break loop73;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:834:40: ( IDENT | string )
                    int alt74=2;
                    int LA74_0 = input.LA(1);

                    if ( (LA74_0==IDENT) ) {
                        alt74=1;
                    }
                    else if ( (LA74_0==INVALID_STRING||LA74_0==STRING) ) {
                        alt74=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 74, 0, input);

                        throw nvae;
                    }
                    switch (alt74) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:834:41: IDENT
                            {
                            IDENT152=(Token)match(input,IDENT,FOLLOW_IDENT_in_attribute1591); 
                            IDENT152_tree = (Object)adaptor.create(IDENT152);
                            adaptor.addChild(root_0, IDENT152_tree);


                            }
                            break;
                        case 2 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:834:49: string
                            {
                            pushFollow(FOLLOW_string_in_attribute1595);
                            string153=string();

                            state._fsp--;

                            adaptor.addChild(root_0, string153.getTree());

                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:834:57: ( S )*
                    loop75:
                    do {
                        int alt75=2;
                        int LA75_0 = input.LA(1);

                        if ( (LA75_0==S) ) {
                            alt75=1;
                        }


                        switch (alt75) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:834:57: S
                    	    {
                    	    S154=(Token)match(input,S,FOLLOW_S_in_attribute1598); 
                    	    S154_tree = (Object)adaptor.create(S154);
                    	    adaptor.addChild(root_0, S154_tree);


                    	    }
                    	    break;

                    	default :
                    	    break loop75;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:837:1: pseudo : COLON ( IDENT | FUNCTION ( S )* IDENT ( S )* RPAREN ) -> ^( PSEUDO ( FUNCTION )? IDENT ) ;
    public final CSSParser.pseudo_return pseudo() throws RecognitionException {
        CSSParser.pseudo_return retval = new CSSParser.pseudo_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON155=null;
        Token IDENT156=null;
        Token FUNCTION157=null;
        Token S158=null;
        Token IDENT159=null;
        Token S160=null;
        Token RPAREN161=null;

        Object COLON155_tree=null;
        Object IDENT156_tree=null;
        Object FUNCTION157_tree=null;
        Object S158_tree=null;
        Object IDENT159_tree=null;
        Object S160_tree=null;
        Object RPAREN161_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_FUNCTION=new RewriteRuleTokenStream(adaptor,"token FUNCTION");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:838:2: ( COLON ( IDENT | FUNCTION ( S )* IDENT ( S )* RPAREN ) -> ^( PSEUDO ( FUNCTION )? IDENT ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:838:4: COLON ( IDENT | FUNCTION ( S )* IDENT ( S )* RPAREN )
            {
            COLON155=(Token)match(input,COLON,FOLLOW_COLON_in_pseudo1612);  
            stream_COLON.add(COLON155);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:838:10: ( IDENT | FUNCTION ( S )* IDENT ( S )* RPAREN )
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==IDENT) ) {
                alt79=1;
            }
            else if ( (LA79_0==FUNCTION) ) {
                alt79=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;
            }
            switch (alt79) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:838:11: IDENT
                    {
                    IDENT156=(Token)match(input,IDENT,FOLLOW_IDENT_in_pseudo1615);  
                    stream_IDENT.add(IDENT156);


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:838:19: FUNCTION ( S )* IDENT ( S )* RPAREN
                    {
                    FUNCTION157=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_pseudo1619);  
                    stream_FUNCTION.add(FUNCTION157);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:838:28: ( S )*
                    loop77:
                    do {
                        int alt77=2;
                        int LA77_0 = input.LA(1);

                        if ( (LA77_0==S) ) {
                            alt77=1;
                        }


                        switch (alt77) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:838:28: S
                    	    {
                    	    S158=(Token)match(input,S,FOLLOW_S_in_pseudo1621);  
                    	    stream_S.add(S158);


                    	    }
                    	    break;

                    	default :
                    	    break loop77;
                        }
                    } while (true);

                    IDENT159=(Token)match(input,IDENT,FOLLOW_IDENT_in_pseudo1625);  
                    stream_IDENT.add(IDENT159);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:838:38: ( S )*
                    loop78:
                    do {
                        int alt78=2;
                        int LA78_0 = input.LA(1);

                        if ( (LA78_0==S) ) {
                            alt78=1;
                        }


                        switch (alt78) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:838:38: S
                    	    {
                    	    S160=(Token)match(input,S,FOLLOW_S_in_pseudo1627);  
                    	    stream_S.add(S160);


                    	    }
                    	    break;

                    	default :
                    	    break loop78;
                        }
                    } while (true);

                    RPAREN161=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_pseudo1630);  
                    stream_RPAREN.add(RPAREN161);


                    }
                    break;

            }



            // AST REWRITE
            // elements: FUNCTION, IDENT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 839:3: -> ^( PSEUDO ( FUNCTION )? IDENT )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:839:6: ^( PSEUDO ( FUNCTION )? IDENT )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PSEUDO, "PSEUDO"), root_1);

                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:839:15: ( FUNCTION )?
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:1: string : ( STRING | INVALID_STRING );
    public final CSSParser.string_return string() throws RecognitionException {
        CSSParser.string_return retval = new CSSParser.string_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set162=null;

        Object set162_tree=null;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:843:2: ( STRING | INVALID_STRING )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:
            {
            root_0 = (Object)adaptor.nil();

            set162=(Token)input.LT(1);
            if ( input.LA(1)==INVALID_STRING||input.LA(1)==STRING ) {
                input.consume();
                adaptor.addChild(root_0, (Object)adaptor.create(set162));
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:848:1: any : ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) ) ( S )* ;
    public final CSSParser.any_return any() throws RecognitionException {
        CSSParser.any_return retval = new CSSParser.any_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT163=null;
        Token CLASSKEYWORD164=null;
        Token NUMBER165=null;
        Token PERCENTAGE166=null;
        Token DIMENSION167=null;
        Token URI169=null;
        Token HASH170=null;
        Token UNIRANGE171=null;
        Token INCLUDES172=null;
        Token COLON173=null;
        Token COMMA174=null;
        Token GREATER175=null;
        Token LESS176=null;
        Token QUESTION177=null;
        Token PERCENT178=null;
        Token EQUALS179=null;
        Token SLASH180=null;
        Token EXCLAMATION181=null;
        Token MINUS182=null;
        Token PLUS183=null;
        Token ASTERISK184=null;
        Token FUNCTION185=null;
        Token S186=null;
        Token RPAREN188=null;
        Token DASHMATCH189=null;
        Token LPAREN190=null;
        Token RPAREN192=null;
        Token LBRACE193=null;
        Token RBRACE195=null;
        Token S196=null;
        CSSParser.string_return string168 = null;

        CSSParser.any_return any187 = null;

        CSSParser.any_return any191 = null;

        CSSParser.any_return any194 = null;


        Object IDENT163_tree=null;
        Object CLASSKEYWORD164_tree=null;
        Object NUMBER165_tree=null;
        Object PERCENTAGE166_tree=null;
        Object DIMENSION167_tree=null;
        Object URI169_tree=null;
        Object HASH170_tree=null;
        Object UNIRANGE171_tree=null;
        Object INCLUDES172_tree=null;
        Object COLON173_tree=null;
        Object COMMA174_tree=null;
        Object GREATER175_tree=null;
        Object LESS176_tree=null;
        Object QUESTION177_tree=null;
        Object PERCENT178_tree=null;
        Object EQUALS179_tree=null;
        Object SLASH180_tree=null;
        Object EXCLAMATION181_tree=null;
        Object MINUS182_tree=null;
        Object PLUS183_tree=null;
        Object ASTERISK184_tree=null;
        Object FUNCTION185_tree=null;
        Object S186_tree=null;
        Object RPAREN188_tree=null;
        Object DASHMATCH189_tree=null;
        Object LPAREN190_tree=null;
        Object RPAREN192_tree=null;
        Object LBRACE193_tree=null;
        Object RBRACE195_tree=null;
        Object S196_tree=null;
        RewriteRuleTokenStream stream_FUNCTION=new RewriteRuleTokenStream(adaptor,"token FUNCTION");
        RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_CLASSKEYWORD=new RewriteRuleTokenStream(adaptor,"token CLASSKEYWORD");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleTokenStream stream_NUMBER=new RewriteRuleTokenStream(adaptor,"token NUMBER");
        RewriteRuleTokenStream stream_HASH=new RewriteRuleTokenStream(adaptor,"token HASH");
        RewriteRuleTokenStream stream_EQUALS=new RewriteRuleTokenStream(adaptor,"token EQUALS");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");
        RewriteRuleTokenStream stream_PERCENTAGE=new RewriteRuleTokenStream(adaptor,"token PERCENTAGE");
        RewriteRuleTokenStream stream_ASTERISK=new RewriteRuleTokenStream(adaptor,"token ASTERISK");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleTokenStream stream_URI=new RewriteRuleTokenStream(adaptor,"token URI");
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_INCLUDES=new RewriteRuleTokenStream(adaptor,"token INCLUDES");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleTokenStream stream_SLASH=new RewriteRuleTokenStream(adaptor,"token SLASH");
        RewriteRuleTokenStream stream_DASHMATCH=new RewriteRuleTokenStream(adaptor,"token DASHMATCH");
        RewriteRuleTokenStream stream_QUESTION=new RewriteRuleTokenStream(adaptor,"token QUESTION");
        RewriteRuleTokenStream stream_EXCLAMATION=new RewriteRuleTokenStream(adaptor,"token EXCLAMATION");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_LESS=new RewriteRuleTokenStream(adaptor,"token LESS");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
        RewriteRuleTokenStream stream_DIMENSION=new RewriteRuleTokenStream(adaptor,"token DIMENSION");
        RewriteRuleTokenStream stream_UNIRANGE=new RewriteRuleTokenStream(adaptor,"token UNIRANGE");
        RewriteRuleSubtreeStream stream_string=new RewriteRuleSubtreeStream(adaptor,"rule string");
        RewriteRuleSubtreeStream stream_any=new RewriteRuleSubtreeStream(adaptor,"rule any");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:849:2: ( ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) ) ( S )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:849:4: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) ) ( S )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:849:4: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) )
            int alt84=26;
            alt84 = dfa84.predict(input);
            switch (alt84) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:849:6: IDENT
                    {
                    IDENT163=(Token)match(input,IDENT,FOLLOW_IDENT_in_any1676);  
                    stream_IDENT.add(IDENT163);



                    // AST REWRITE
                    // elements: IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 849:12: -> IDENT
                    {
                        adaptor.addChild(root_0, stream_IDENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:850:6: CLASSKEYWORD
                    {
                    CLASSKEYWORD164=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_any1687);  
                    stream_CLASSKEYWORD.add(CLASSKEYWORD164);



                    // AST REWRITE
                    // elements: CLASSKEYWORD
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 850:19: -> CLASSKEYWORD
                    {
                        adaptor.addChild(root_0, stream_CLASSKEYWORD.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:851:6: NUMBER
                    {
                    NUMBER165=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_any1698);  
                    stream_NUMBER.add(NUMBER165);



                    // AST REWRITE
                    // elements: NUMBER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 851:13: -> NUMBER
                    {
                        adaptor.addChild(root_0, stream_NUMBER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:852:6: PERCENTAGE
                    {
                    PERCENTAGE166=(Token)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_any1709);  
                    stream_PERCENTAGE.add(PERCENTAGE166);



                    // AST REWRITE
                    // elements: PERCENTAGE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 852:17: -> PERCENTAGE
                    {
                        adaptor.addChild(root_0, stream_PERCENTAGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:853:6: DIMENSION
                    {
                    DIMENSION167=(Token)match(input,DIMENSION,FOLLOW_DIMENSION_in_any1719);  
                    stream_DIMENSION.add(DIMENSION167);



                    // AST REWRITE
                    // elements: DIMENSION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 853:16: -> DIMENSION
                    {
                        adaptor.addChild(root_0, stream_DIMENSION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:854:6: string
                    {
                    pushFollow(FOLLOW_string_in_any1730);
                    string168=string();

                    state._fsp--;

                    stream_string.add(string168.getTree());


                    // AST REWRITE
                    // elements: string
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 854:13: -> string
                    {
                        adaptor.addChild(root_0, stream_string.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:855:9: URI
                    {
                    URI169=(Token)match(input,URI,FOLLOW_URI_in_any1744);  
                    stream_URI.add(URI169);



                    // AST REWRITE
                    // elements: URI
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 855:16: -> URI
                    {
                        adaptor.addChild(root_0, stream_URI.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:856:9: HASH
                    {
                    HASH170=(Token)match(input,HASH,FOLLOW_HASH_in_any1761);  
                    stream_HASH.add(HASH170);



                    // AST REWRITE
                    // elements: HASH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 856:14: -> HASH
                    {
                        adaptor.addChild(root_0, stream_HASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:857:9: UNIRANGE
                    {
                    UNIRANGE171=(Token)match(input,UNIRANGE,FOLLOW_UNIRANGE_in_any1775);  
                    stream_UNIRANGE.add(UNIRANGE171);



                    // AST REWRITE
                    // elements: UNIRANGE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 857:18: -> UNIRANGE
                    {
                        adaptor.addChild(root_0, stream_UNIRANGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:858:9: INCLUDES
                    {
                    INCLUDES172=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_any1789);  
                    stream_INCLUDES.add(INCLUDES172);



                    // AST REWRITE
                    // elements: INCLUDES
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 858:18: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:859:9: COLON
                    {
                    COLON173=(Token)match(input,COLON,FOLLOW_COLON_in_any1803);  
                    stream_COLON.add(COLON173);



                    // AST REWRITE
                    // elements: COLON
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 859:15: -> COLON
                    {
                        adaptor.addChild(root_0, stream_COLON.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:860:9: COMMA
                    {
                    COMMA174=(Token)match(input,COMMA,FOLLOW_COMMA_in_any1817);  
                    stream_COMMA.add(COMMA174);



                    // AST REWRITE
                    // elements: COMMA
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 860:15: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:861:9: GREATER
                    {
                    GREATER175=(Token)match(input,GREATER,FOLLOW_GREATER_in_any1831);  
                    stream_GREATER.add(GREATER175);



                    // AST REWRITE
                    // elements: GREATER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 861:17: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:862:9: LESS
                    {
                    LESS176=(Token)match(input,LESS,FOLLOW_LESS_in_any1845);  
                    stream_LESS.add(LESS176);



                    // AST REWRITE
                    // elements: LESS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 862:14: -> LESS
                    {
                        adaptor.addChild(root_0, stream_LESS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:863:9: QUESTION
                    {
                    QUESTION177=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_any1859);  
                    stream_QUESTION.add(QUESTION177);



                    // AST REWRITE
                    // elements: QUESTION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 863:18: -> QUESTION
                    {
                        adaptor.addChild(root_0, stream_QUESTION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:864:9: PERCENT
                    {
                    PERCENT178=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_any1873);  
                    stream_PERCENT.add(PERCENT178);



                    // AST REWRITE
                    // elements: PERCENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 864:17: -> PERCENT
                    {
                        adaptor.addChild(root_0, stream_PERCENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:865:9: EQUALS
                    {
                    EQUALS179=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_any1887);  
                    stream_EQUALS.add(EQUALS179);



                    // AST REWRITE
                    // elements: EQUALS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 865:16: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 18 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:866:9: SLASH
                    {
                    SLASH180=(Token)match(input,SLASH,FOLLOW_SLASH_in_any1901);  
                    stream_SLASH.add(SLASH180);



                    // AST REWRITE
                    // elements: SLASH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 866:15: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 19 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:867:9: EXCLAMATION
                    {
                    EXCLAMATION181=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_any1915);  
                    stream_EXCLAMATION.add(EXCLAMATION181);



                    // AST REWRITE
                    // elements: EXCLAMATION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 867:21: -> EXCLAMATION
                    {
                        adaptor.addChild(root_0, stream_EXCLAMATION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 20 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:868:6: MINUS
                    {
                    MINUS182=(Token)match(input,MINUS,FOLLOW_MINUS_in_any1926);  
                    stream_MINUS.add(MINUS182);



                    // AST REWRITE
                    // elements: MINUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 868:12: -> MINUS
                    {
                        adaptor.addChild(root_0, stream_MINUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 21 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:869:6: PLUS
                    {
                    PLUS183=(Token)match(input,PLUS,FOLLOW_PLUS_in_any1937);  
                    stream_PLUS.add(PLUS183);



                    // AST REWRITE
                    // elements: PLUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 869:11: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 22 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:870:6: ASTERISK
                    {
                    ASTERISK184=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_any1948);  
                    stream_ASTERISK.add(ASTERISK184);



                    // AST REWRITE
                    // elements: ASTERISK
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 870:15: -> ASTERISK
                    {
                        adaptor.addChild(root_0, stream_ASTERISK.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 23 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:871:9: FUNCTION ( S )* ( any )* RPAREN
                    {
                    FUNCTION185=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_any1965);  
                    stream_FUNCTION.add(FUNCTION185);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:871:18: ( S )*
                    loop80:
                    do {
                        int alt80=2;
                        alt80 = dfa80.predict(input);
                        switch (alt80) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:871:18: S
                    	    {
                    	    S186=(Token)match(input,S,FOLLOW_S_in_any1967);  
                    	    stream_S.add(S186);


                    	    }
                    	    break;

                    	default :
                    	    break loop80;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:871:21: ( any )*
                    loop81:
                    do {
                        int alt81=2;
                        alt81 = dfa81.predict(input);
                        switch (alt81) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:871:21: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_any1970);
                    	    any187=any();

                    	    state._fsp--;

                    	    stream_any.add(any187.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop81;
                        }
                    } while (true);

                    RPAREN188=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_any1973);  
                    stream_RPAREN.add(RPAREN188);



                    // AST REWRITE
                    // elements: FUNCTION, any
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 871:33: -> ^( FUNCTION ( any )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:871:36: ^( FUNCTION ( any )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_FUNCTION.nextNode(), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:871:47: ( any )*
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
                case 24 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:872:9: DASHMATCH
                    {
                    DASHMATCH189=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_any1993);  
                    stream_DASHMATCH.add(DASHMATCH189);



                    // AST REWRITE
                    // elements: DASHMATCH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 872:19: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 25 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:873:9: LPAREN ( any )* RPAREN
                    {
                    LPAREN190=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_any2007);  
                    stream_LPAREN.add(LPAREN190);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:873:16: ( any )*
                    loop82:
                    do {
                        int alt82=2;
                        alt82 = dfa82.predict(input);
                        switch (alt82) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:873:16: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_any2009);
                    	    any191=any();

                    	    state._fsp--;

                    	    stream_any.add(any191.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop82;
                        }
                    } while (true);

                    RPAREN192=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_any2012);  
                    stream_RPAREN.add(RPAREN192);



                    // AST REWRITE
                    // elements: any
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 873:28: -> ^( PARENBLOCK ( any )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:873:31: ^( PARENBLOCK ( any )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PARENBLOCK, "PARENBLOCK"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:873:44: ( any )*
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
                case 26 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:874:9: LBRACE ( any )* RBRACE
                    {
                    LBRACE193=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_any2031);  
                    stream_LBRACE.add(LBRACE193);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:874:16: ( any )*
                    loop83:
                    do {
                        int alt83=2;
                        alt83 = dfa83.predict(input);
                        switch (alt83) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:874:16: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_any2033);
                    	    any194=any();

                    	    state._fsp--;

                    	    stream_any.add(any194.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop83;
                        }
                    } while (true);

                    RBRACE195=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_any2036);  
                    stream_RBRACE.add(RBRACE195);



                    // AST REWRITE
                    // elements: any
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 874:28: -> ^( BRACEBLOCK ( any )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:874:31: ^( BRACEBLOCK ( any )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BRACEBLOCK, "BRACEBLOCK"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:874:44: ( any )*
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:875:8: ( S )*
            loop85:
            do {
                int alt85=2;
                alt85 = dfa85.predict(input);
                switch (alt85) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:875:8: S
            	    {
            	    S196=(Token)match(input,S,FOLLOW_S_in_any2054);  
            	    stream_S.add(S196);


            	    }
            	    break;

            	default :
            	    break loop85;
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

    public static class noprop_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "noprop"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:877:1: noprop : ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN ) ( S )* ;
    public final CSSParser.noprop_return noprop() throws RecognitionException {
        CSSParser.noprop_return retval = new CSSParser.noprop_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CLASSKEYWORD197=null;
        Token NUMBER198=null;
        Token COMMA199=null;
        Token GREATER200=null;
        Token LESS201=null;
        Token QUESTION202=null;
        Token PERCENT203=null;
        Token EQUALS204=null;
        Token SLASH205=null;
        Token EXCLAMATION206=null;
        Token PLUS207=null;
        Token ASTERISK208=null;
        Token DASHMATCH209=null;
        Token INCLUDES210=null;
        Token COLON211=null;
        Token STRING_CHAR212=null;
        Token INVALID_TOKEN213=null;
        Token S214=null;

        Object CLASSKEYWORD197_tree=null;
        Object NUMBER198_tree=null;
        Object COMMA199_tree=null;
        Object GREATER200_tree=null;
        Object LESS201_tree=null;
        Object QUESTION202_tree=null;
        Object PERCENT203_tree=null;
        Object EQUALS204_tree=null;
        Object SLASH205_tree=null;
        Object EXCLAMATION206_tree=null;
        Object PLUS207_tree=null;
        Object ASTERISK208_tree=null;
        Object DASHMATCH209_tree=null;
        Object INCLUDES210_tree=null;
        Object COLON211_tree=null;
        Object STRING_CHAR212_tree=null;
        Object INVALID_TOKEN213_tree=null;
        Object S214_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_INCLUDES=new RewriteRuleTokenStream(adaptor,"token INCLUDES");
        RewriteRuleTokenStream stream_STRING_CHAR=new RewriteRuleTokenStream(adaptor,"token STRING_CHAR");
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleTokenStream stream_SLASH=new RewriteRuleTokenStream(adaptor,"token SLASH");
        RewriteRuleTokenStream stream_DASHMATCH=new RewriteRuleTokenStream(adaptor,"token DASHMATCH");
        RewriteRuleTokenStream stream_QUESTION=new RewriteRuleTokenStream(adaptor,"token QUESTION");
        RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
        RewriteRuleTokenStream stream_EXCLAMATION=new RewriteRuleTokenStream(adaptor,"token EXCLAMATION");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_CLASSKEYWORD=new RewriteRuleTokenStream(adaptor,"token CLASSKEYWORD");
        RewriteRuleTokenStream stream_NUMBER=new RewriteRuleTokenStream(adaptor,"token NUMBER");
        RewriteRuleTokenStream stream_LESS=new RewriteRuleTokenStream(adaptor,"token LESS");
        RewriteRuleTokenStream stream_INVALID_TOKEN=new RewriteRuleTokenStream(adaptor,"token INVALID_TOKEN");
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
        RewriteRuleTokenStream stream_EQUALS=new RewriteRuleTokenStream(adaptor,"token EQUALS");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_ASTERISK=new RewriteRuleTokenStream(adaptor,"token ASTERISK");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:879:2: ( ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN ) ( S )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:879:4: ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN ) ( S )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:879:4: ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN )
            int alt86=17;
            alt86 = dfa86.predict(input);
            switch (alt86) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:879:6: CLASSKEYWORD
                    {
                    CLASSKEYWORD197=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_noprop2068);  
                    stream_CLASSKEYWORD.add(CLASSKEYWORD197);



                    // AST REWRITE
                    // elements: CLASSKEYWORD
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 879:19: -> CLASSKEYWORD
                    {
                        adaptor.addChild(root_0, stream_CLASSKEYWORD.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:880:8: NUMBER
                    {
                    NUMBER198=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_noprop2081);  
                    stream_NUMBER.add(NUMBER198);



                    // AST REWRITE
                    // elements: NUMBER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 880:15: -> NUMBER
                    {
                        adaptor.addChild(root_0, stream_NUMBER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:881:7: COMMA
                    {
                    COMMA199=(Token)match(input,COMMA,FOLLOW_COMMA_in_noprop2093);  
                    stream_COMMA.add(COMMA199);



                    // AST REWRITE
                    // elements: COMMA
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 881:13: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:882:7: GREATER
                    {
                    GREATER200=(Token)match(input,GREATER,FOLLOW_GREATER_in_noprop2105);  
                    stream_GREATER.add(GREATER200);



                    // AST REWRITE
                    // elements: GREATER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 882:15: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:7: LESS
                    {
                    LESS201=(Token)match(input,LESS,FOLLOW_LESS_in_noprop2117);  
                    stream_LESS.add(LESS201);



                    // AST REWRITE
                    // elements: LESS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 883:12: -> LESS
                    {
                        adaptor.addChild(root_0, stream_LESS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:884:7: QUESTION
                    {
                    QUESTION202=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_noprop2129);  
                    stream_QUESTION.add(QUESTION202);



                    // AST REWRITE
                    // elements: QUESTION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 884:16: -> QUESTION
                    {
                        adaptor.addChild(root_0, stream_QUESTION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:885:7: PERCENT
                    {
                    PERCENT203=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_noprop2141);  
                    stream_PERCENT.add(PERCENT203);



                    // AST REWRITE
                    // elements: PERCENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 885:15: -> PERCENT
                    {
                        adaptor.addChild(root_0, stream_PERCENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:886:7: EQUALS
                    {
                    EQUALS204=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_noprop2153);  
                    stream_EQUALS.add(EQUALS204);



                    // AST REWRITE
                    // elements: EQUALS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 886:14: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:887:7: SLASH
                    {
                    SLASH205=(Token)match(input,SLASH,FOLLOW_SLASH_in_noprop2165);  
                    stream_SLASH.add(SLASH205);



                    // AST REWRITE
                    // elements: SLASH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 887:13: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:888:7: EXCLAMATION
                    {
                    EXCLAMATION206=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_noprop2177);  
                    stream_EXCLAMATION.add(EXCLAMATION206);



                    // AST REWRITE
                    // elements: EXCLAMATION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 888:19: -> EXCLAMATION
                    {
                        adaptor.addChild(root_0, stream_EXCLAMATION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:889:7: PLUS
                    {
                    PLUS207=(Token)match(input,PLUS,FOLLOW_PLUS_in_noprop2189);  
                    stream_PLUS.add(PLUS207);



                    // AST REWRITE
                    // elements: PLUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 889:12: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:890:7: ASTERISK
                    {
                    ASTERISK208=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_noprop2201);  
                    stream_ASTERISK.add(ASTERISK208);



                    // AST REWRITE
                    // elements: ASTERISK
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 890:16: -> ASTERISK
                    {
                        adaptor.addChild(root_0, stream_ASTERISK.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:891:7: DASHMATCH
                    {
                    DASHMATCH209=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_noprop2216);  
                    stream_DASHMATCH.add(DASHMATCH209);



                    // AST REWRITE
                    // elements: DASHMATCH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 891:17: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:892:7: INCLUDES
                    {
                    INCLUDES210=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_noprop2228);  
                    stream_INCLUDES.add(INCLUDES210);



                    // AST REWRITE
                    // elements: INCLUDES
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 892:16: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:893:7: COLON
                    {
                    COLON211=(Token)match(input,COLON,FOLLOW_COLON_in_noprop2240);  
                    stream_COLON.add(COLON211);



                    // AST REWRITE
                    // elements: COLON
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 893:13: -> COLON
                    {
                        adaptor.addChild(root_0, stream_COLON.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:894:7: STRING_CHAR
                    {
                    STRING_CHAR212=(Token)match(input,STRING_CHAR,FOLLOW_STRING_CHAR_in_noprop2252);  
                    stream_STRING_CHAR.add(STRING_CHAR212);



                    // AST REWRITE
                    // elements: STRING_CHAR
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 894:19: -> STRING_CHAR
                    {
                        adaptor.addChild(root_0, stream_STRING_CHAR.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:895:7: INVALID_TOKEN
                    {
                    INVALID_TOKEN213=(Token)match(input,INVALID_TOKEN,FOLLOW_INVALID_TOKEN_in_noprop2264);  
                    stream_INVALID_TOKEN.add(INVALID_TOKEN213);



                    // AST REWRITE
                    // elements: INVALID_TOKEN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 895:21: -> INVALID_TOKEN
                    {
                        adaptor.addChild(root_0, stream_INVALID_TOKEN.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;

            }

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:896:8: ( S )*
            loop87:
            do {
                int alt87=2;
                alt87 = dfa87.predict(input);
                switch (alt87) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:896:8: S
            	    {
            	    S214=(Token)match(input,S,FOLLOW_S_in_noprop2277);  
            	    stream_S.add(S214);


            	    }
            	    break;

            	default :
            	    break loop87;
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
    // $ANTLR end "noprop"

    public static class norule_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "norule"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:898:1: norule : ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH ) ;
    public final CSSParser.norule_return norule() throws RecognitionException {
        CSSParser.norule_return retval = new CSSParser.norule_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NUMBER215=null;
        Token PERCENTAGE216=null;
        Token DIMENSION217=null;
        Token URI219=null;
        Token UNIRANGE220=null;
        Token INCLUDES221=null;
        Token COMMA222=null;
        Token GREATER223=null;
        Token LESS224=null;
        Token QUESTION225=null;
        Token PERCENT226=null;
        Token EQUALS227=null;
        Token SLASH228=null;
        Token EXCLAMATION229=null;
        Token MINUS230=null;
        Token PLUS231=null;
        Token DASHMATCH232=null;
        CSSParser.string_return string218 = null;


        Object NUMBER215_tree=null;
        Object PERCENTAGE216_tree=null;
        Object DIMENSION217_tree=null;
        Object URI219_tree=null;
        Object UNIRANGE220_tree=null;
        Object INCLUDES221_tree=null;
        Object COMMA222_tree=null;
        Object GREATER223_tree=null;
        Object LESS224_tree=null;
        Object QUESTION225_tree=null;
        Object PERCENT226_tree=null;
        Object EQUALS227_tree=null;
        Object SLASH228_tree=null;
        Object EXCLAMATION229_tree=null;
        Object MINUS230_tree=null;
        Object PLUS231_tree=null;
        Object DASHMATCH232_tree=null;
        RewriteRuleTokenStream stream_INCLUDES=new RewriteRuleTokenStream(adaptor,"token INCLUDES");
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleTokenStream stream_SLASH=new RewriteRuleTokenStream(adaptor,"token SLASH");
        RewriteRuleTokenStream stream_DASHMATCH=new RewriteRuleTokenStream(adaptor,"token DASHMATCH");
        RewriteRuleTokenStream stream_EXCLAMATION=new RewriteRuleTokenStream(adaptor,"token EXCLAMATION");
        RewriteRuleTokenStream stream_QUESTION=new RewriteRuleTokenStream(adaptor,"token QUESTION");
        RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_NUMBER=new RewriteRuleTokenStream(adaptor,"token NUMBER");
        RewriteRuleTokenStream stream_LESS=new RewriteRuleTokenStream(adaptor,"token LESS");
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
        RewriteRuleTokenStream stream_DIMENSION=new RewriteRuleTokenStream(adaptor,"token DIMENSION");
        RewriteRuleTokenStream stream_UNIRANGE=new RewriteRuleTokenStream(adaptor,"token UNIRANGE");
        RewriteRuleTokenStream stream_EQUALS=new RewriteRuleTokenStream(adaptor,"token EQUALS");
        RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");
        RewriteRuleTokenStream stream_PERCENTAGE=new RewriteRuleTokenStream(adaptor,"token PERCENTAGE");
        RewriteRuleTokenStream stream_URI=new RewriteRuleTokenStream(adaptor,"token URI");
        RewriteRuleSubtreeStream stream_string=new RewriteRuleSubtreeStream(adaptor,"rule string");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:900:3: ( ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:900:5: ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:900:5: ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH )
            int alt88=18;
            alt88 = dfa88.predict(input);
            switch (alt88) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:900:7: NUMBER
                    {
                    NUMBER215=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_norule2292);  
                    stream_NUMBER.add(NUMBER215);



                    // AST REWRITE
                    // elements: NUMBER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 900:14: -> NUMBER
                    {
                        adaptor.addChild(root_0, stream_NUMBER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:901:8: PERCENTAGE
                    {
                    PERCENTAGE216=(Token)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_norule2305);  
                    stream_PERCENTAGE.add(PERCENTAGE216);



                    // AST REWRITE
                    // elements: PERCENTAGE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 901:19: -> PERCENTAGE
                    {
                        adaptor.addChild(root_0, stream_PERCENTAGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:902:8: DIMENSION
                    {
                    DIMENSION217=(Token)match(input,DIMENSION,FOLLOW_DIMENSION_in_norule2317);  
                    stream_DIMENSION.add(DIMENSION217);



                    // AST REWRITE
                    // elements: DIMENSION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 902:18: -> DIMENSION
                    {
                        adaptor.addChild(root_0, stream_DIMENSION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:903:8: string
                    {
                    pushFollow(FOLLOW_string_in_norule2330);
                    string218=string();

                    state._fsp--;

                    stream_string.add(string218.getTree());


                    // AST REWRITE
                    // elements: string
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 903:15: -> string
                    {
                        adaptor.addChild(root_0, stream_string.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:904:9: URI
                    {
                    URI219=(Token)match(input,URI,FOLLOW_URI_in_norule2344);  
                    stream_URI.add(URI219);



                    // AST REWRITE
                    // elements: URI
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 904:16: -> URI
                    {
                        adaptor.addChild(root_0, stream_URI.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:905:9: UNIRANGE
                    {
                    UNIRANGE220=(Token)match(input,UNIRANGE,FOLLOW_UNIRANGE_in_norule2361);  
                    stream_UNIRANGE.add(UNIRANGE220);



                    // AST REWRITE
                    // elements: UNIRANGE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 905:18: -> UNIRANGE
                    {
                        adaptor.addChild(root_0, stream_UNIRANGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:906:9: INCLUDES
                    {
                    INCLUDES221=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_norule2375);  
                    stream_INCLUDES.add(INCLUDES221);



                    // AST REWRITE
                    // elements: INCLUDES
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 906:18: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:907:9: COMMA
                    {
                    COMMA222=(Token)match(input,COMMA,FOLLOW_COMMA_in_norule2389);  
                    stream_COMMA.add(COMMA222);



                    // AST REWRITE
                    // elements: COMMA
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 907:15: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:908:9: GREATER
                    {
                    GREATER223=(Token)match(input,GREATER,FOLLOW_GREATER_in_norule2403);  
                    stream_GREATER.add(GREATER223);



                    // AST REWRITE
                    // elements: GREATER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 908:17: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:909:9: LESS
                    {
                    LESS224=(Token)match(input,LESS,FOLLOW_LESS_in_norule2417);  
                    stream_LESS.add(LESS224);



                    // AST REWRITE
                    // elements: LESS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 909:14: -> LESS
                    {
                        adaptor.addChild(root_0, stream_LESS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:910:9: QUESTION
                    {
                    QUESTION225=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_norule2431);  
                    stream_QUESTION.add(QUESTION225);



                    // AST REWRITE
                    // elements: QUESTION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 910:18: -> QUESTION
                    {
                        adaptor.addChild(root_0, stream_QUESTION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:911:9: PERCENT
                    {
                    PERCENT226=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_norule2445);  
                    stream_PERCENT.add(PERCENT226);



                    // AST REWRITE
                    // elements: PERCENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 911:17: -> PERCENT
                    {
                        adaptor.addChild(root_0, stream_PERCENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:912:9: EQUALS
                    {
                    EQUALS227=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_norule2459);  
                    stream_EQUALS.add(EQUALS227);



                    // AST REWRITE
                    // elements: EQUALS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 912:16: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:913:9: SLASH
                    {
                    SLASH228=(Token)match(input,SLASH,FOLLOW_SLASH_in_norule2473);  
                    stream_SLASH.add(SLASH228);



                    // AST REWRITE
                    // elements: SLASH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 913:15: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:914:9: EXCLAMATION
                    {
                    EXCLAMATION229=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_norule2487);  
                    stream_EXCLAMATION.add(EXCLAMATION229);



                    // AST REWRITE
                    // elements: EXCLAMATION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 914:21: -> EXCLAMATION
                    {
                        adaptor.addChild(root_0, stream_EXCLAMATION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:915:8: MINUS
                    {
                    MINUS230=(Token)match(input,MINUS,FOLLOW_MINUS_in_norule2500);  
                    stream_MINUS.add(MINUS230);



                    // AST REWRITE
                    // elements: MINUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 915:14: -> MINUS
                    {
                        adaptor.addChild(root_0, stream_MINUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:916:8: PLUS
                    {
                    PLUS231=(Token)match(input,PLUS,FOLLOW_PLUS_in_norule2513);  
                    stream_PLUS.add(PLUS231);



                    // AST REWRITE
                    // elements: PLUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 916:13: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 18 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:917:9: DASHMATCH
                    {
                    DASHMATCH232=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_norule2527);  
                    stream_DASHMATCH.add(DASHMATCH232);



                    // AST REWRITE
                    // elements: DASHMATCH
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 917:19: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

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
    // $ANTLR end "norule"

    // Delegated rules


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA3 dfa3 = new DFA3(this);
    protected DFA4 dfa4 = new DFA4(this);
    protected DFA5 dfa5 = new DFA5(this);
    protected DFA9 dfa9 = new DFA9(this);
    protected DFA12 dfa12 = new DFA12(this);
    protected DFA14 dfa14 = new DFA14(this);
    protected DFA13 dfa13 = new DFA13(this);
    protected DFA16 dfa16 = new DFA16(this);
    protected DFA30 dfa30 = new DFA30(this);
    protected DFA29 dfa29 = new DFA29(this);
    protected DFA31 dfa31 = new DFA31(this);
    protected DFA32 dfa32 = new DFA32(this);
    protected DFA33 dfa33 = new DFA33(this);
    protected DFA38 dfa38 = new DFA38(this);
    protected DFA35 dfa35 = new DFA35(this);
    protected DFA37 dfa37 = new DFA37(this);
    protected DFA43 dfa43 = new DFA43(this);
    protected DFA48 dfa48 = new DFA48(this);
    protected DFA44 dfa44 = new DFA44(this);
    protected DFA46 dfa46 = new DFA46(this);
    protected DFA45 dfa45 = new DFA45(this);
    protected DFA47 dfa47 = new DFA47(this);
    protected DFA49 dfa49 = new DFA49(this);
    protected DFA50 dfa50 = new DFA50(this);
    protected DFA58 dfa58 = new DFA58(this);
    protected DFA56 dfa56 = new DFA56(this);
    protected DFA57 dfa57 = new DFA57(this);
    protected DFA59 dfa59 = new DFA59(this);
    protected DFA65 dfa65 = new DFA65(this);
    protected DFA66 dfa66 = new DFA66(this);
    protected DFA67 dfa67 = new DFA67(this);
    protected DFA68 dfa68 = new DFA68(this);
    protected DFA84 dfa84 = new DFA84(this);
    protected DFA80 dfa80 = new DFA80(this);
    protected DFA81 dfa81 = new DFA81(this);
    protected DFA82 dfa82 = new DFA82(this);
    protected DFA83 dfa83 = new DFA83(this);
    protected DFA85 dfa85 = new DFA85(this);
    protected DFA86 dfa86 = new DFA86(this);
    protected DFA87 dfa87 = new DFA87(this);
    protected DFA88 dfa88 = new DFA88(this);
    static final String DFA1_eotS =
        "\30\uffff";
    static final String DFA1_eofS =
        "\1\1\27\uffff";
    static final String DFA1_minS =
        "\1\35\27\uffff";
    static final String DFA1_maxS =
        "\1\106\27\uffff";
    static final String DFA1_acceptS =
        "\1\uffff\1\2\25\uffff\1\1";
    static final String DFA1_specialS =
        "\30\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\27\5\uffff\3\1\3\uffff\4\1\3\uffff\2\1\5\uffff\12\1\4\uffff"+
            "\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 648:4: ( S )*";
        }
    }
    static final String DFA3_eotS =
        "\64\uffff";
    static final String DFA3_eofS =
        "\1\1\20\uffff\1\1\42\uffff";
    static final String DFA3_minS =
        "\1\43\20\uffff\1\27\42\uffff";
    static final String DFA3_maxS =
        "\1\106\20\uffff\1\104\42\uffff";
    static final String DFA3_acceptS =
        "\1\uffff\1\1\24\uffff\1\2\2\1\33\uffff";
    static final String DFA3_specialS =
        "\64\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\21\1\1\1\26\3\uffff\4\1\3\uffff\2\1\5\uffff\12\1\4\uffff"+
            "\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\1\5\uffff\1\1\5\uffff\1\1\1\27\4\uffff\4\1\1\uffff\1\30"+
            "\1\uffff\23\1\1\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "648:8: ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) )";
        }
    }
    static final String DFA4_eotS =
        "\45\uffff";
    static final String DFA4_eofS =
        "\1\1\44\uffff";
    static final String DFA4_minS =
        "\1\26\44\uffff";
    static final String DFA4_maxS =
        "\1\104\44\uffff";
    static final String DFA4_acceptS =
        "\1\uffff\1\5\1\1\1\2\1\3\1\4\37\uffff";
    static final String DFA4_specialS =
        "\45\uffff}>";
    static final String[] DFA4_transitionS = {
            "\2\5\1\uffff\1\5\2\uffff\1\5\1\4\1\2\1\3\5\5\2\uffff\3\5\1\uffff"+
            "\2\5\3\uffff\21\5\1\uffff\1\5\1\uffff\1\5",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 654:4: ( CDO | CDC | S | statement )*";
        }
    }
    static final String DFA5_eotS =
        "\41\uffff";
    static final String DFA5_eofS =
        "\41\uffff";
    static final String DFA5_minS =
        "\1\26\40\uffff";
    static final String DFA5_maxS =
        "\1\104\40\uffff";
    static final String DFA5_acceptS =
        "\1\uffff\1\1\30\uffff\1\2\6\uffff";
    static final String DFA5_specialS =
        "\41\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\32\1\1\1\uffff\1\1\2\uffff\1\32\3\uffff\3\32\2\1\2\uffff"+
            "\2\32\1\1\1\uffff\2\1\3\uffff\21\1\1\uffff\1\1\1\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "658:1: statement : ( ruleset | atstatement );";
        }
    }
    static final String DFA9_eotS =
        "\27\uffff";
    static final String DFA9_eofS =
        "\27\uffff";
    static final String DFA9_minS =
        "\1\35\26\uffff";
    static final String DFA9_maxS =
        "\1\106\26\uffff";
    static final String DFA9_acceptS =
        "\1\uffff\1\2\24\uffff\1\1";
    static final String DFA9_specialS =
        "\27\uffff}>";
    static final String[] DFA9_transitionS = {
            "\1\26\5\uffff\2\1\1\uffff\1\1\2\uffff\4\1\3\uffff\2\1\5\uffff"+
            "\12\1\4\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "()* loopback of 668:10: ( S )*";
        }
    }
    static final String DFA12_eotS =
        "\34\uffff";
    static final String DFA12_eofS =
        "\34\uffff";
    static final String DFA12_minS =
        "\1\27\33\uffff";
    static final String DFA12_maxS =
        "\1\104\33\uffff";
    static final String DFA12_acceptS =
        "\1\uffff\1\2\31\uffff\1\1";
    static final String DFA12_specialS =
        "\34\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\1\1\uffff\1\1\3\uffff\1\33\5\uffff\2\1\1\uffff\1\1\2\uffff"+
            "\1\1\1\uffff\2\1\3\uffff\21\1\1\uffff\1\1\1\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 671:10: ( S )*";
        }
    }
    static final String DFA14_eotS =
        "\33\uffff";
    static final String DFA14_eofS =
        "\33\uffff";
    static final String DFA14_minS =
        "\1\27\32\uffff";
    static final String DFA14_maxS =
        "\1\104\32\uffff";
    static final String DFA14_acceptS =
        "\1\uffff\1\2\1\1\30\uffff";
    static final String DFA14_specialS =
        "\33\uffff}>";
    static final String[] DFA14_transitionS = {
            "\1\2\1\uffff\1\2\11\uffff\2\2\1\uffff\1\1\2\uffff\1\2\1\uffff"+
            "\2\2\3\uffff\21\2\1\uffff\1\2\1\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 671:13: ( ruleset ( S )* )*";
        }
    }
    static final String DFA13_eotS =
        "\34\uffff";
    static final String DFA13_eofS =
        "\34\uffff";
    static final String DFA13_minS =
        "\1\27\33\uffff";
    static final String DFA13_maxS =
        "\1\104\33\uffff";
    static final String DFA13_acceptS =
        "\1\uffff\1\2\31\uffff\1\1";
    static final String DFA13_specialS =
        "\34\uffff}>";
    static final String[] DFA13_transitionS = {
            "\1\1\1\uffff\1\1\3\uffff\1\33\5\uffff\2\1\1\uffff\1\1\2\uffff"+
            "\1\1\1\uffff\2\1\3\uffff\21\1\1\uffff\1\1\1\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 671:22: ( S )*";
        }
    }
    static final String DFA16_eotS =
        "\34\uffff";
    static final String DFA16_eofS =
        "\34\uffff";
    static final String DFA16_minS =
        "\1\27\33\uffff";
    static final String DFA16_maxS =
        "\1\104\33\uffff";
    static final String DFA16_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA16_specialS =
        "\34\uffff}>";
    static final String[] DFA16_transitionS = {
            "\1\2\13\uffff\2\2\1\uffff\1\1\2\uffff\1\2\1\uffff\2\2\1\uffff"+
            "\1\2\1\uffff\23\2\1\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 672:24: ( any )*";
        }
    }
    static final String DFA30_eotS =
        "\32\uffff";
    static final String DFA30_eofS =
        "\32\uffff";
    static final String DFA30_minS =
        "\1\27\31\uffff";
    static final String DFA30_maxS =
        "\1\104\31\uffff";
    static final String DFA30_acceptS =
        "\1\uffff\1\1\6\uffff\1\2\21\uffff";
    static final String DFA30_specialS =
        "\32\uffff}>";
    static final String[] DFA30_transitionS = {
            "\1\10\1\uffff\1\1\11\uffff\2\1\4\uffff\1\10\1\uffff\2\10\3\uffff"+
            "\1\1\4\10\1\1\11\10\1\1\1\10\1\uffff\1\1\1\uffff\1\10",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "695:1: ruleset : ( combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY -> ^( RULE ( combined_selector )+ declarations ) | norule -> INVALID_STATEMENT );";
        }
    }
    static final String DFA29_eotS =
        "\27\uffff";
    static final String DFA29_eofS =
        "\27\uffff";
    static final String DFA29_minS =
        "\1\35\26\uffff";
    static final String DFA29_maxS =
        "\1\106\26\uffff";
    static final String DFA29_acceptS =
        "\1\uffff\1\2\24\uffff\1\1";
    static final String DFA29_specialS =
        "\27\uffff}>";
    static final String[] DFA29_transitionS = {
            "\1\26\5\uffff\2\1\1\uffff\1\1\2\uffff\4\1\3\uffff\2\1\5\uffff"+
            "\12\1\4\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 697:11: ( S )*";
        }
    }
    static final String DFA31_eotS =
        "\27\uffff";
    static final String DFA31_eofS =
        "\1\24\26\uffff";
    static final String DFA31_minS =
        "\1\43\26\uffff";
    static final String DFA31_maxS =
        "\1\106\26\uffff";
    static final String DFA31_acceptS =
        "\1\uffff\1\1\22\uffff\1\2\2\uffff";
    static final String DFA31_specialS =
        "\27\uffff}>";
    static final String[] DFA31_transitionS = {
            "\2\1\1\uffff\1\24\2\uffff\1\1\1\24\2\1\3\uffff\2\1\5\uffff\12"+
            "\1\4\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA31_eot = DFA.unpackEncodedString(DFA31_eotS);
    static final short[] DFA31_eof = DFA.unpackEncodedString(DFA31_eofS);
    static final char[] DFA31_min = DFA.unpackEncodedStringToUnsignedChars(DFA31_minS);
    static final char[] DFA31_max = DFA.unpackEncodedStringToUnsignedChars(DFA31_maxS);
    static final short[] DFA31_accept = DFA.unpackEncodedString(DFA31_acceptS);
    static final short[] DFA31_special = DFA.unpackEncodedString(DFA31_specialS);
    static final short[][] DFA31_transition;

    static {
        int numStates = DFA31_transitionS.length;
        DFA31_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA31_transition[i] = DFA.unpackEncodedString(DFA31_transitionS[i]);
        }
    }

    class DFA31 extends DFA {

        public DFA31(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 31;
            this.eot = DFA31_eot;
            this.eof = DFA31_eof;
            this.min = DFA31_min;
            this.max = DFA31_max;
            this.accept = DFA31_accept;
            this.special = DFA31_special;
            this.transition = DFA31_transition;
        }
        public String getDescription() {
            return "710:4: ( declaration )?";
        }
    }
    static final String DFA32_eotS =
        "\30\uffff";
    static final String DFA32_eofS =
        "\1\1\27\uffff";
    static final String DFA32_minS =
        "\1\35\27\uffff";
    static final String DFA32_maxS =
        "\1\106\27\uffff";
    static final String DFA32_acceptS =
        "\1\uffff\1\2\25\uffff\1\1";
    static final String DFA32_specialS =
        "\30\uffff}>";
    static final String[] DFA32_transitionS = {
            "\1\27\5\uffff\2\1\1\uffff\1\1\2\uffff\4\1\3\uffff\2\1\5\uffff"+
            "\12\1\4\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA32_eot = DFA.unpackEncodedString(DFA32_eotS);
    static final short[] DFA32_eof = DFA.unpackEncodedString(DFA32_eofS);
    static final char[] DFA32_min = DFA.unpackEncodedStringToUnsignedChars(DFA32_minS);
    static final char[] DFA32_max = DFA.unpackEncodedStringToUnsignedChars(DFA32_maxS);
    static final short[] DFA32_accept = DFA.unpackEncodedString(DFA32_acceptS);
    static final short[] DFA32_special = DFA.unpackEncodedString(DFA32_specialS);
    static final short[][] DFA32_transition;

    static {
        int numStates = DFA32_transitionS.length;
        DFA32_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA32_transition[i] = DFA.unpackEncodedString(DFA32_transitionS[i]);
        }
    }

    class DFA32 extends DFA {

        public DFA32(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 32;
            this.eot = DFA32_eot;
            this.eof = DFA32_eof;
            this.min = DFA32_min;
            this.max = DFA32_max;
            this.accept = DFA32_accept;
            this.special = DFA32_special;
            this.transition = DFA32_transition;
        }
        public String getDescription() {
            return "()* loopback of 710:28: ( S )*";
        }
    }
    static final String DFA33_eotS =
        "\27\uffff";
    static final String DFA33_eofS =
        "\1\24\26\uffff";
    static final String DFA33_minS =
        "\1\43\26\uffff";
    static final String DFA33_maxS =
        "\1\106\26\uffff";
    static final String DFA33_acceptS =
        "\1\uffff\1\1\22\uffff\1\2\2\uffff";
    static final String DFA33_specialS =
        "\27\uffff}>";
    static final String[] DFA33_transitionS = {
            "\2\1\1\uffff\1\24\2\uffff\1\1\1\24\2\1\3\uffff\2\1\5\uffff\12"+
            "\1\4\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA33_eot = DFA.unpackEncodedString(DFA33_eotS);
    static final short[] DFA33_eof = DFA.unpackEncodedString(DFA33_eofS);
    static final char[] DFA33_min = DFA.unpackEncodedStringToUnsignedChars(DFA33_minS);
    static final char[] DFA33_max = DFA.unpackEncodedStringToUnsignedChars(DFA33_maxS);
    static final short[] DFA33_accept = DFA.unpackEncodedString(DFA33_acceptS);
    static final short[] DFA33_special = DFA.unpackEncodedString(DFA33_specialS);
    static final short[][] DFA33_transition;

    static {
        int numStates = DFA33_transitionS.length;
        DFA33_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA33_transition[i] = DFA.unpackEncodedString(DFA33_transitionS[i]);
        }
    }

    class DFA33 extends DFA {

        public DFA33(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 33;
            this.eot = DFA33_eot;
            this.eof = DFA33_eof;
            this.min = DFA33_min;
            this.max = DFA33_max;
            this.accept = DFA33_accept;
            this.special = DFA33_special;
            this.transition = DFA33_transition;
        }
        public String getDescription() {
            return "710:31: ( declaration )?";
        }
    }
    static final String DFA38_eotS =
        "\24\uffff";
    static final String DFA38_eofS =
        "\24\uffff";
    static final String DFA38_minS =
        "\1\43\23\uffff";
    static final String DFA38_maxS =
        "\1\106\23\uffff";
    static final String DFA38_acceptS =
        "\1\uffff\1\1\1\uffff\1\2\20\uffff";
    static final String DFA38_specialS =
        "\24\uffff}>";
    static final String[] DFA38_transitionS = {
            "\1\3\1\1\4\uffff\1\3\1\uffff\1\3\1\1\3\uffff\2\3\5\uffff\12"+
            "\3\4\uffff\2\3",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "714:1: declaration : ( property COLON ( S )* terms ( important )? -> ^( DECLARATION ( important )? property terms ) | noprop ( any )* -> INVALID_DECLARATION );";
        }
    }
    static final String DFA35_eotS =
        "\36\uffff";
    static final String DFA35_eofS =
        "\36\uffff";
    static final String DFA35_minS =
        "\1\27\35\uffff";
    static final String DFA35_maxS =
        "\1\104\35\uffff";
    static final String DFA35_acceptS =
        "\1\uffff\1\2\33\uffff\1\1";
    static final String DFA35_specialS =
        "\36\uffff}>";
    static final String[] DFA35_transitionS = {
            "\1\1\5\uffff\1\35\5\uffff\3\1\2\uffff\2\1\2\uffff\3\1\1\uffff"+
            "\23\1\1\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 715:19: ( S )*";
        }
    }
    static final String DFA37_eotS =
        "\36\uffff";
    static final String DFA37_eofS =
        "\1\1\35\uffff";
    static final String DFA37_minS =
        "\1\27\35\uffff";
    static final String DFA37_maxS =
        "\1\104\35\uffff";
    static final String DFA37_acceptS =
        "\1\uffff\1\2\2\uffff\1\1\31\uffff";
    static final String DFA37_specialS =
        "\36\uffff}>";
    static final String[] DFA37_transitionS = {
            "\1\4\13\uffff\2\4\1\uffff\1\1\2\uffff\1\4\1\1\2\4\1\uffff\1"+
            "\4\1\uffff\23\4\1\uffff\1\4",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 716:11: ( any )*";
        }
    }
    static final String DFA43_eotS =
        "\42\uffff";
    static final String DFA43_eofS =
        "\1\1\41\uffff";
    static final String DFA43_minS =
        "\1\27\41\uffff";
    static final String DFA43_maxS =
        "\1\104\41\uffff";
    static final String DFA43_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\33\uffff";
    static final String DFA43_specialS =
        "\42\uffff}>";
    static final String[] DFA43_transitionS = {
            "\1\6\13\uffff\3\6\1\1\1\uffff\2\6\2\1\3\6\1\1\23\6\1\uffff\1"+
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
            return "()+ loopback of 731:4: ( term )+";
        }
    }
    static final String DFA48_eotS =
        "\35\uffff";
    static final String DFA48_eofS =
        "\35\uffff";
    static final String DFA48_minS =
        "\1\27\34\uffff";
    static final String DFA48_maxS =
        "\1\104\34\uffff";
    static final String DFA48_acceptS =
        "\1\uffff\1\1\31\uffff\1\2\1\3";
    static final String DFA48_specialS =
        "\35\uffff}>";
    static final String[] DFA48_transitionS = {
            "\1\1\13\uffff\2\1\1\33\2\uffff\1\34\1\1\2\uffff\3\1\1\uffff"+
            "\23\1\1\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "749:1: term : ( valuepart -> valuepart | LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY -> CURLYBLOCK | ATKEYWORD ( S )* -> ATKEYWORD );";
        }
    }
    static final String DFA44_eotS =
        "\36\uffff";
    static final String DFA44_eofS =
        "\36\uffff";
    static final String DFA44_minS =
        "\1\27\35\uffff";
    static final String DFA44_maxS =
        "\1\104\35\uffff";
    static final String DFA44_acceptS =
        "\1\uffff\1\2\33\uffff\1\1";
    static final String DFA44_specialS =
        "\36\uffff}>";
    static final String[] DFA44_transitionS = {
            "\1\1\5\uffff\1\35\5\uffff\2\1\1\uffff\1\1\2\uffff\4\1\1\uffff"+
            "\1\1\1\uffff\23\1\1\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 751:14: ( S )*";
        }
    }
    static final String DFA46_eotS =
        "\35\uffff";
    static final String DFA46_eofS =
        "\35\uffff";
    static final String DFA46_minS =
        "\1\27\34\uffff";
    static final String DFA46_maxS =
        "\1\104\34\uffff";
    static final String DFA46_acceptS =
        "\1\uffff\1\3\1\1\31\uffff\1\2";
    static final String DFA46_specialS =
        "\35\uffff}>";
    static final String[] DFA46_transitionS = {
            "\1\2\13\uffff\2\2\1\uffff\1\1\2\uffff\1\2\1\34\2\2\1\uffff\1"+
            "\2\1\uffff\23\2\1\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 751:17: ( any | SEMICOLON ( S )* )*";
        }
    }
    static final String DFA45_eotS =
        "\36\uffff";
    static final String DFA45_eofS =
        "\36\uffff";
    static final String DFA45_minS =
        "\1\27\35\uffff";
    static final String DFA45_maxS =
        "\1\104\35\uffff";
    static final String DFA45_acceptS =
        "\1\uffff\1\2\33\uffff\1\1";
    static final String DFA45_specialS =
        "\36\uffff}>";
    static final String[] DFA45_transitionS = {
            "\1\1\5\uffff\1\35\5\uffff\2\1\1\uffff\1\1\2\uffff\4\1\1\uffff"+
            "\1\1\1\uffff\23\1\1\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 751:34: ( S )*";
        }
    }
    static final String DFA47_eotS =
        "\43\uffff";
    static final String DFA47_eofS =
        "\1\1\42\uffff";
    static final String DFA47_minS =
        "\1\27\42\uffff";
    static final String DFA47_maxS =
        "\1\104\42\uffff";
    static final String DFA47_acceptS =
        "\1\uffff\1\2\40\uffff\1\1";
    static final String DFA47_specialS =
        "\43\uffff}>";
    static final String[] DFA47_transitionS = {
            "\1\1\5\uffff\1\42\5\uffff\4\1\1\uffff\33\1\1\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 752:17: ( S )*";
        }
    }
    static final String DFA49_eotS =
        "\37\uffff";
    static final String DFA49_eofS =
        "\37\uffff";
    static final String DFA49_minS =
        "\1\27\36\uffff";
    static final String DFA49_maxS =
        "\1\104\36\uffff";
    static final String DFA49_acceptS =
        "\1\uffff\1\2\34\uffff\1\1";
    static final String DFA49_specialS =
        "\37\uffff}>";
    static final String[] DFA49_transitionS = {
            "\1\1\5\uffff\1\36\5\uffff\3\1\2\uffff\2\1\2\uffff\27\1\1\uffff"+
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
            return "()* loopback of 764:13: ( S )*";
        }
    }
    static final String DFA50_eotS =
        "\36\uffff";
    static final String DFA50_eofS =
        "\36\uffff";
    static final String DFA50_minS =
        "\1\27\35\uffff";
    static final String DFA50_maxS =
        "\1\104\35\uffff";
    static final String DFA50_acceptS =
        "\1\uffff\1\1\33\uffff\1\2";
    static final String DFA50_specialS =
        "\36\uffff}>";
    static final String[] DFA50_transitionS = {
            "\1\1\13\uffff\3\1\2\uffff\2\1\2\uffff\3\1\1\35\23\1\1\uffff"+
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
            return "764:16: ( terms )?";
        }
    }
    static final String DFA58_eotS =
        "\37\uffff";
    static final String DFA58_eofS =
        "\37\uffff";
    static final String DFA58_minS =
        "\1\27\1\44\35\uffff";
    static final String DFA58_maxS =
        "\1\104\1\63\35\uffff";
    static final String DFA58_acceptS =
        "\2\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\uffff\1\26\1\27\1"+
        "\30\4\uffff";
    static final String DFA58_specialS =
        "\37\uffff}>";
    static final String[] DFA58_transitionS = {
            "\1\7\13\uffff\1\14\1\2\4\uffff\1\15\2\uffff\1\1\2\26\1\uffff"+
            "\1\3\1\4\1\5\1\6\1\10\1\11\1\12\1\13\1\16\1\17\1\20\1\21\1\22"+
            "\1\23\1\24\1\25\1\30\1\31\1\32\1\uffff\1\7",
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
            return "769:7: ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | funct -> funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) )";
        }
    }
    static final String DFA56_eotS =
        "\34\uffff";
    static final String DFA56_eofS =
        "\34\uffff";
    static final String DFA56_minS =
        "\1\27\33\uffff";
    static final String DFA56_maxS =
        "\1\104\33\uffff";
    static final String DFA56_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA56_specialS =
        "\34\uffff}>";
    static final String[] DFA56_transitionS = {
            "\1\2\13\uffff\2\2\4\uffff\1\2\2\uffff\3\2\1\1\23\2\1\uffff\1"+
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
            return "()* loopback of 791:16: ( valuepart )*";
        }
    }
    static final String DFA57_eotS =
        "\34\uffff";
    static final String DFA57_eofS =
        "\34\uffff";
    static final String DFA57_minS =
        "\1\27\33\uffff";
    static final String DFA57_maxS =
        "\1\104\33\uffff";
    static final String DFA57_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA57_specialS =
        "\34\uffff}>";
    static final String[] DFA57_transitionS = {
            "\1\2\13\uffff\2\2\4\uffff\1\2\2\uffff\3\2\1\uffff\23\2\1\1\1"+
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
            return "()* loopback of 792:16: ( valuepart )*";
        }
    }
    static final String DFA59_eotS =
        "\44\uffff";
    static final String DFA59_eofS =
        "\1\1\43\uffff";
    static final String DFA59_minS =
        "\1\27\43\uffff";
    static final String DFA59_maxS =
        "\1\104\43\uffff";
    static final String DFA59_acceptS =
        "\1\uffff\1\2\41\uffff\1\1";
    static final String DFA59_specialS =
        "\44\uffff}>";
    static final String[] DFA59_transitionS = {
            "\1\1\5\uffff\1\43\5\uffff\4\1\1\uffff\35\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 793:8: ( S )*";
        }
    }
    static final String DFA65_eotS =
        "\13\uffff";
    static final String DFA65_eofS =
        "\13\uffff";
    static final String DFA65_minS =
        "\1\31\12\uffff";
    static final String DFA65_maxS =
        "\1\102\12\uffff";
    static final String DFA65_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\4\uffff";
    static final String DFA65_specialS =
        "\13\uffff}>";
    static final String[] DFA65_transitionS = {
            "\1\6\3\uffff\1\1\5\uffff\1\6\1\uffff\1\1\3\uffff\1\1\6\uffff"+
            "\1\6\4\uffff\1\6\2\uffff\1\1\5\uffff\1\1\3\uffff\1\6",
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

    static final short[] DFA65_eot = DFA.unpackEncodedString(DFA65_eotS);
    static final short[] DFA65_eof = DFA.unpackEncodedString(DFA65_eofS);
    static final char[] DFA65_min = DFA.unpackEncodedStringToUnsignedChars(DFA65_minS);
    static final char[] DFA65_max = DFA.unpackEncodedStringToUnsignedChars(DFA65_maxS);
    static final short[] DFA65_accept = DFA.unpackEncodedString(DFA65_acceptS);
    static final short[] DFA65_special = DFA.unpackEncodedString(DFA65_specialS);
    static final short[][] DFA65_transition;

    static {
        int numStates = DFA65_transitionS.length;
        DFA65_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA65_transition[i] = DFA.unpackEncodedString(DFA65_transitionS[i]);
        }
    }

    class DFA65 extends DFA {

        public DFA65(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 65;
            this.eot = DFA65_eot;
            this.eof = DFA65_eof;
            this.min = DFA65_min;
            this.max = DFA65_max;
            this.accept = DFA65_accept;
            this.special = DFA65_special;
            this.transition = DFA65_transition;
        }
        public String getDescription() {
            return "()* loopback of 812:27: ( selpart )*";
        }
    }
    static final String DFA66_eotS =
        "\22\uffff";
    static final String DFA66_eofS =
        "\22\uffff";
    static final String DFA66_minS =
        "\1\35\2\uffff\1\31\16\uffff";
    static final String DFA66_maxS =
        "\1\76\2\uffff\1\102\16\uffff";
    static final String DFA66_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\13\uffff";
    static final String DFA66_specialS =
        "\22\uffff}>";
    static final String[] DFA66_transitionS = {
            "\1\3\7\uffff\1\1\3\uffff\1\1\16\uffff\1\1\5\uffff\1\1",
            "",
            "",
            "\1\1\3\uffff\1\6\5\uffff\2\1\1\6\3\uffff\1\6\6\uffff\1\1\4"+
            "\uffff\1\1\2\uffff\1\6\5\uffff\1\6\1\1\2\uffff\1\1",
            "",
            "",
            "",
            "",
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

    static final short[] DFA66_eot = DFA.unpackEncodedString(DFA66_eotS);
    static final short[] DFA66_eof = DFA.unpackEncodedString(DFA66_eofS);
    static final char[] DFA66_min = DFA.unpackEncodedStringToUnsignedChars(DFA66_minS);
    static final char[] DFA66_max = DFA.unpackEncodedStringToUnsignedChars(DFA66_maxS);
    static final short[] DFA66_accept = DFA.unpackEncodedString(DFA66_acceptS);
    static final short[] DFA66_special = DFA.unpackEncodedString(DFA66_specialS);
    static final short[][] DFA66_transition;

    static {
        int numStates = DFA66_transitionS.length;
        DFA66_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA66_transition[i] = DFA.unpackEncodedString(DFA66_transitionS[i]);
        }
    }

    class DFA66 extends DFA {

        public DFA66(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 66;
            this.eot = DFA66_eot;
            this.eof = DFA66_eof;
            this.min = DFA66_min;
            this.max = DFA66_max;
            this.accept = DFA66_accept;
            this.special = DFA66_special;
            this.transition = DFA66_transition;
        }
        public String getDescription() {
            return "()* loopback of 812:36: ( S )*";
        }
    }
    static final String DFA67_eotS =
        "\13\uffff";
    static final String DFA67_eofS =
        "\13\uffff";
    static final String DFA67_minS =
        "\1\31\12\uffff";
    static final String DFA67_maxS =
        "\1\102\12\uffff";
    static final String DFA67_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\4\uffff";
    static final String DFA67_specialS =
        "\13\uffff}>";
    static final String[] DFA67_transitionS = {
            "\1\6\3\uffff\1\1\5\uffff\1\6\1\uffff\1\1\3\uffff\1\1\6\uffff"+
            "\1\6\4\uffff\1\6\2\uffff\1\1\5\uffff\1\1\3\uffff\1\6",
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
            return "()+ loopback of 814:7: ( selpart )+";
        }
    }
    static final String DFA68_eotS =
        "\22\uffff";
    static final String DFA68_eofS =
        "\22\uffff";
    static final String DFA68_minS =
        "\1\35\2\uffff\1\31\16\uffff";
    static final String DFA68_maxS =
        "\1\76\2\uffff\1\102\16\uffff";
    static final String DFA68_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\13\uffff";
    static final String DFA68_specialS =
        "\22\uffff}>";
    static final String[] DFA68_transitionS = {
            "\1\3\7\uffff\1\1\3\uffff\1\1\16\uffff\1\1\5\uffff\1\1",
            "",
            "",
            "\1\1\3\uffff\1\6\5\uffff\2\1\1\6\3\uffff\1\6\6\uffff\1\1\4"+
            "\uffff\1\1\2\uffff\1\6\5\uffff\1\6\1\1\2\uffff\1\1",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 814:16: ( S )*";
        }
    }
    static final String DFA84_eotS =
        "\33\uffff";
    static final String DFA84_eofS =
        "\33\uffff";
    static final String DFA84_minS =
        "\1\27\32\uffff";
    static final String DFA84_maxS =
        "\1\104\32\uffff";
    static final String DFA84_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
        "\1\32";
    static final String DFA84_specialS =
        "\33\uffff}>";
    static final String[] DFA84_transitionS = {
            "\1\6\13\uffff\1\13\1\1\4\uffff\1\14\1\uffff\1\23\1\24\1\uffff"+
            "\1\27\1\uffff\1\2\1\3\1\4\1\5\1\7\1\10\1\11\1\12\1\15\1\16\1"+
            "\17\1\20\1\21\1\22\1\25\1\26\1\30\1\31\1\32\1\uffff\1\6",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA84_eot = DFA.unpackEncodedString(DFA84_eotS);
    static final short[] DFA84_eof = DFA.unpackEncodedString(DFA84_eofS);
    static final char[] DFA84_min = DFA.unpackEncodedStringToUnsignedChars(DFA84_minS);
    static final char[] DFA84_max = DFA.unpackEncodedStringToUnsignedChars(DFA84_maxS);
    static final short[] DFA84_accept = DFA.unpackEncodedString(DFA84_acceptS);
    static final short[] DFA84_special = DFA.unpackEncodedString(DFA84_specialS);
    static final short[][] DFA84_transition;

    static {
        int numStates = DFA84_transitionS.length;
        DFA84_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA84_transition[i] = DFA.unpackEncodedString(DFA84_transitionS[i]);
        }
    }

    class DFA84 extends DFA {

        public DFA84(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 84;
            this.eot = DFA84_eot;
            this.eof = DFA84_eof;
            this.min = DFA84_min;
            this.max = DFA84_max;
            this.accept = DFA84_accept;
            this.special = DFA84_special;
            this.transition = DFA84_transition;
        }
        public String getDescription() {
            return "849:4: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) )";
        }
    }
    static final String DFA80_eotS =
        "\35\uffff";
    static final String DFA80_eofS =
        "\35\uffff";
    static final String DFA80_minS =
        "\1\27\34\uffff";
    static final String DFA80_maxS =
        "\1\104\34\uffff";
    static final String DFA80_acceptS =
        "\1\uffff\1\2\32\uffff\1\1";
    static final String DFA80_specialS =
        "\35\uffff}>";
    static final String[] DFA80_transitionS = {
            "\1\1\5\uffff\1\34\5\uffff\2\1\4\uffff\1\1\1\uffff\2\1\1\uffff"+
            "\25\1\1\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA80_eot = DFA.unpackEncodedString(DFA80_eotS);
    static final short[] DFA80_eof = DFA.unpackEncodedString(DFA80_eofS);
    static final char[] DFA80_min = DFA.unpackEncodedStringToUnsignedChars(DFA80_minS);
    static final char[] DFA80_max = DFA.unpackEncodedStringToUnsignedChars(DFA80_maxS);
    static final short[] DFA80_accept = DFA.unpackEncodedString(DFA80_acceptS);
    static final short[] DFA80_special = DFA.unpackEncodedString(DFA80_specialS);
    static final short[][] DFA80_transition;

    static {
        int numStates = DFA80_transitionS.length;
        DFA80_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA80_transition[i] = DFA.unpackEncodedString(DFA80_transitionS[i]);
        }
    }

    class DFA80 extends DFA {

        public DFA80(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 80;
            this.eot = DFA80_eot;
            this.eof = DFA80_eof;
            this.min = DFA80_min;
            this.max = DFA80_max;
            this.accept = DFA80_accept;
            this.special = DFA80_special;
            this.transition = DFA80_transition;
        }
        public String getDescription() {
            return "()* loopback of 871:18: ( S )*";
        }
    }
    static final String DFA81_eotS =
        "\34\uffff";
    static final String DFA81_eofS =
        "\34\uffff";
    static final String DFA81_minS =
        "\1\27\33\uffff";
    static final String DFA81_maxS =
        "\1\104\33\uffff";
    static final String DFA81_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA81_specialS =
        "\34\uffff}>";
    static final String[] DFA81_transitionS = {
            "\1\2\13\uffff\2\2\4\uffff\1\2\1\uffff\2\2\1\uffff\1\2\1\1\23"+
            "\2\1\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA81_eot = DFA.unpackEncodedString(DFA81_eotS);
    static final short[] DFA81_eof = DFA.unpackEncodedString(DFA81_eofS);
    static final char[] DFA81_min = DFA.unpackEncodedStringToUnsignedChars(DFA81_minS);
    static final char[] DFA81_max = DFA.unpackEncodedStringToUnsignedChars(DFA81_maxS);
    static final short[] DFA81_accept = DFA.unpackEncodedString(DFA81_acceptS);
    static final short[] DFA81_special = DFA.unpackEncodedString(DFA81_specialS);
    static final short[][] DFA81_transition;

    static {
        int numStates = DFA81_transitionS.length;
        DFA81_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA81_transition[i] = DFA.unpackEncodedString(DFA81_transitionS[i]);
        }
    }

    class DFA81 extends DFA {

        public DFA81(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 81;
            this.eot = DFA81_eot;
            this.eof = DFA81_eof;
            this.min = DFA81_min;
            this.max = DFA81_max;
            this.accept = DFA81_accept;
            this.special = DFA81_special;
            this.transition = DFA81_transition;
        }
        public String getDescription() {
            return "()* loopback of 871:21: ( any )*";
        }
    }
    static final String DFA82_eotS =
        "\34\uffff";
    static final String DFA82_eofS =
        "\34\uffff";
    static final String DFA82_minS =
        "\1\27\33\uffff";
    static final String DFA82_maxS =
        "\1\104\33\uffff";
    static final String DFA82_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA82_specialS =
        "\34\uffff}>";
    static final String[] DFA82_transitionS = {
            "\1\2\13\uffff\2\2\4\uffff\1\2\1\uffff\2\2\1\uffff\1\2\1\1\23"+
            "\2\1\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA82_eot = DFA.unpackEncodedString(DFA82_eotS);
    static final short[] DFA82_eof = DFA.unpackEncodedString(DFA82_eofS);
    static final char[] DFA82_min = DFA.unpackEncodedStringToUnsignedChars(DFA82_minS);
    static final char[] DFA82_max = DFA.unpackEncodedStringToUnsignedChars(DFA82_maxS);
    static final short[] DFA82_accept = DFA.unpackEncodedString(DFA82_acceptS);
    static final short[] DFA82_special = DFA.unpackEncodedString(DFA82_specialS);
    static final short[][] DFA82_transition;

    static {
        int numStates = DFA82_transitionS.length;
        DFA82_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA82_transition[i] = DFA.unpackEncodedString(DFA82_transitionS[i]);
        }
    }

    class DFA82 extends DFA {

        public DFA82(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 82;
            this.eot = DFA82_eot;
            this.eof = DFA82_eof;
            this.min = DFA82_min;
            this.max = DFA82_max;
            this.accept = DFA82_accept;
            this.special = DFA82_special;
            this.transition = DFA82_transition;
        }
        public String getDescription() {
            return "()* loopback of 873:16: ( any )*";
        }
    }
    static final String DFA83_eotS =
        "\34\uffff";
    static final String DFA83_eofS =
        "\34\uffff";
    static final String DFA83_minS =
        "\1\27\33\uffff";
    static final String DFA83_maxS =
        "\1\104\33\uffff";
    static final String DFA83_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA83_specialS =
        "\34\uffff}>";
    static final String[] DFA83_transitionS = {
            "\1\2\13\uffff\2\2\4\uffff\1\2\1\uffff\2\2\1\uffff\1\2\1\uffff"+
            "\23\2\1\1\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA83_eot = DFA.unpackEncodedString(DFA83_eotS);
    static final short[] DFA83_eof = DFA.unpackEncodedString(DFA83_eofS);
    static final char[] DFA83_min = DFA.unpackEncodedStringToUnsignedChars(DFA83_minS);
    static final char[] DFA83_max = DFA.unpackEncodedStringToUnsignedChars(DFA83_maxS);
    static final short[] DFA83_accept = DFA.unpackEncodedString(DFA83_acceptS);
    static final short[] DFA83_special = DFA.unpackEncodedString(DFA83_specialS);
    static final short[][] DFA83_transition;

    static {
        int numStates = DFA83_transitionS.length;
        DFA83_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA83_transition[i] = DFA.unpackEncodedString(DFA83_transitionS[i]);
        }
    }

    class DFA83 extends DFA {

        public DFA83(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 83;
            this.eot = DFA83_eot;
            this.eof = DFA83_eof;
            this.min = DFA83_min;
            this.max = DFA83_max;
            this.accept = DFA83_accept;
            this.special = DFA83_special;
            this.transition = DFA83_transition;
        }
        public String getDescription() {
            return "()* loopback of 874:16: ( any )*";
        }
    }
    static final String DFA85_eotS =
        "\41\uffff";
    static final String DFA85_eofS =
        "\1\1\40\uffff";
    static final String DFA85_minS =
        "\1\27\40\uffff";
    static final String DFA85_maxS =
        "\1\104\40\uffff";
    static final String DFA85_acceptS =
        "\1\uffff\1\2\36\uffff\1\1";
    static final String DFA85_specialS =
        "\41\uffff}>";
    static final String[] DFA85_transitionS = {
            "\1\1\5\uffff\1\40\5\uffff\2\1\1\uffff\1\1\2\uffff\4\1\1\uffff"+
            "\27\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA85_eot = DFA.unpackEncodedString(DFA85_eotS);
    static final short[] DFA85_eof = DFA.unpackEncodedString(DFA85_eofS);
    static final char[] DFA85_min = DFA.unpackEncodedStringToUnsignedChars(DFA85_minS);
    static final char[] DFA85_max = DFA.unpackEncodedStringToUnsignedChars(DFA85_maxS);
    static final short[] DFA85_accept = DFA.unpackEncodedString(DFA85_acceptS);
    static final short[] DFA85_special = DFA.unpackEncodedString(DFA85_specialS);
    static final short[][] DFA85_transition;

    static {
        int numStates = DFA85_transitionS.length;
        DFA85_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA85_transition[i] = DFA.unpackEncodedString(DFA85_transitionS[i]);
        }
    }

    class DFA85 extends DFA {

        public DFA85(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 85;
            this.eot = DFA85_eot;
            this.eof = DFA85_eof;
            this.min = DFA85_min;
            this.max = DFA85_max;
            this.accept = DFA85_accept;
            this.special = DFA85_special;
            this.transition = DFA85_transition;
        }
        public String getDescription() {
            return "()* loopback of 875:8: ( S )*";
        }
    }
    static final String DFA86_eotS =
        "\22\uffff";
    static final String DFA86_eofS =
        "\22\uffff";
    static final String DFA86_minS =
        "\1\43\21\uffff";
    static final String DFA86_maxS =
        "\1\106\21\uffff";
    static final String DFA86_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21";
    static final String DFA86_specialS =
        "\22\uffff}>";
    static final String[] DFA86_transitionS = {
            "\1\17\5\uffff\1\3\1\uffff\1\12\4\uffff\1\1\1\2\5\uffff\1\16"+
            "\1\4\1\5\1\6\1\7\1\10\1\11\1\13\1\14\1\15\4\uffff\1\20\1\21",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA86_eot = DFA.unpackEncodedString(DFA86_eotS);
    static final short[] DFA86_eof = DFA.unpackEncodedString(DFA86_eofS);
    static final char[] DFA86_min = DFA.unpackEncodedStringToUnsignedChars(DFA86_minS);
    static final char[] DFA86_max = DFA.unpackEncodedStringToUnsignedChars(DFA86_maxS);
    static final short[] DFA86_accept = DFA.unpackEncodedString(DFA86_acceptS);
    static final short[] DFA86_special = DFA.unpackEncodedString(DFA86_specialS);
    static final short[][] DFA86_transition;

    static {
        int numStates = DFA86_transitionS.length;
        DFA86_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA86_transition[i] = DFA.unpackEncodedString(DFA86_transitionS[i]);
        }
    }

    class DFA86 extends DFA {

        public DFA86(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 86;
            this.eot = DFA86_eot;
            this.eof = DFA86_eof;
            this.min = DFA86_min;
            this.max = DFA86_max;
            this.accept = DFA86_accept;
            this.special = DFA86_special;
            this.transition = DFA86_transition;
        }
        public String getDescription() {
            return "879:4: ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN )";
        }
    }
    static final String DFA87_eotS =
        "\37\uffff";
    static final String DFA87_eofS =
        "\1\1\36\uffff";
    static final String DFA87_minS =
        "\1\27\36\uffff";
    static final String DFA87_maxS =
        "\1\104\36\uffff";
    static final String DFA87_acceptS =
        "\1\uffff\1\2\34\uffff\1\1";
    static final String DFA87_specialS =
        "\37\uffff}>";
    static final String[] DFA87_transitionS = {
            "\1\1\5\uffff\1\36\5\uffff\2\1\1\uffff\1\1\2\uffff\4\1\1\uffff"+
            "\1\1\1\uffff\23\1\1\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA87_eot = DFA.unpackEncodedString(DFA87_eotS);
    static final short[] DFA87_eof = DFA.unpackEncodedString(DFA87_eofS);
    static final char[] DFA87_min = DFA.unpackEncodedStringToUnsignedChars(DFA87_minS);
    static final char[] DFA87_max = DFA.unpackEncodedStringToUnsignedChars(DFA87_maxS);
    static final short[] DFA87_accept = DFA.unpackEncodedString(DFA87_acceptS);
    static final short[] DFA87_special = DFA.unpackEncodedString(DFA87_specialS);
    static final short[][] DFA87_transition;

    static {
        int numStates = DFA87_transitionS.length;
        DFA87_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA87_transition[i] = DFA.unpackEncodedString(DFA87_transitionS[i]);
        }
    }

    class DFA87 extends DFA {

        public DFA87(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 87;
            this.eot = DFA87_eot;
            this.eof = DFA87_eof;
            this.min = DFA87_min;
            this.max = DFA87_max;
            this.accept = DFA87_accept;
            this.special = DFA87_special;
            this.transition = DFA87_transition;
        }
        public String getDescription() {
            return "()* loopback of 896:8: ( S )*";
        }
    }
    static final String DFA88_eotS =
        "\23\uffff";
    static final String DFA88_eofS =
        "\23\uffff";
    static final String DFA88_minS =
        "\1\27\22\uffff";
    static final String DFA88_maxS =
        "\1\104\22\uffff";
    static final String DFA88_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22";
    static final String DFA88_specialS =
        "\23\uffff}>";
    static final String[] DFA88_transitionS = {
            "\1\4\21\uffff\1\10\1\uffff\1\17\1\20\4\uffff\1\1\1\2\1\3\1\5"+
            "\1\uffff\1\6\1\7\1\11\1\12\1\13\1\14\1\15\1\16\1\21\1\uffff"+
            "\1\22\3\uffff\1\4",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA88_eot = DFA.unpackEncodedString(DFA88_eotS);
    static final short[] DFA88_eof = DFA.unpackEncodedString(DFA88_eofS);
    static final char[] DFA88_min = DFA.unpackEncodedStringToUnsignedChars(DFA88_minS);
    static final char[] DFA88_max = DFA.unpackEncodedStringToUnsignedChars(DFA88_maxS);
    static final short[] DFA88_accept = DFA.unpackEncodedString(DFA88_acceptS);
    static final short[] DFA88_special = DFA.unpackEncodedString(DFA88_specialS);
    static final short[][] DFA88_transition;

    static {
        int numStates = DFA88_transitionS.length;
        DFA88_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA88_transition[i] = DFA.unpackEncodedString(DFA88_transitionS[i]);
        }
    }

    class DFA88 extends DFA {

        public DFA88(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 88;
            this.eot = DFA88_eot;
            this.eof = DFA88_eof;
            this.min = DFA88_min;
            this.max = DFA88_max;
            this.accept = DFA88_accept;
            this.special = DFA88_special;
            this.transition = DFA88_transition;
        }
        public String getDescription() {
            return "900:5: ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH )";
        }
    }
 

    public static final BitSet FOLLOW_S_in_inlinestyle197 = new BitSet(new long[]{0xFF831E3820000000L,0x0000000000000061L});
    public static final BitSet FOLLOW_declarations_in_inlinestyle202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_inlineset_in_inlinestyle222 = new BitSet(new long[]{0xFF831E3800000002L,0x0000000000000061L});
    public static final BitSet FOLLOW_CDO_in_stylesheet250 = new BitSet(new long[]{0xFFFF1B9FF2C00002L,0x0000000000000015L});
    public static final BitSet FOLLOW_CDC_in_stylesheet254 = new BitSet(new long[]{0xFFFF1B9FF2C00002L,0x0000000000000015L});
    public static final BitSet FOLLOW_S_in_stylesheet258 = new BitSet(new long[]{0xFFFF1B9FF2C00002L,0x0000000000000015L});
    public static final BitSet FOLLOW_statement_in_stylesheet262 = new BitSet(new long[]{0xFFFF1B9FF2C00002L,0x0000000000000015L});
    public static final BitSet FOLLOW_ruleset_in_statement292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atstatement_in_statement296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARSET_in_atstatement307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_atstatement312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_IMPORT_in_atstatement317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_END_in_atstatement322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PAGE_in_atstatement327 = new BitSet(new long[]{0x0000002820000000L});
    public static final BitSet FOLLOW_S_in_atstatement329 = new BitSet(new long[]{0x0000002820000000L});
    public static final BitSet FOLLOW_COLON_in_atstatement333 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_IDENT_in_atstatement335 = new BitSet(new long[]{0x0000002020000000L});
    public static final BitSet FOLLOW_S_in_atstatement337 = new BitSet(new long[]{0x0000002020000000L});
    public static final BitSet FOLLOW_LCURLY_in_atstatement345 = new BitSet(new long[]{0xFF831E5820000000L,0x0000000000000061L});
    public static final BitSet FOLLOW_S_in_atstatement347 = new BitSet(new long[]{0xFF831E5820000000L,0x0000000000000061L});
    public static final BitSet FOLLOW_declarations_in_atstatement350 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_RCURLY_in_atstatement355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MEDIA_in_atstatement371 = new BitSet(new long[]{0x0000003020000000L});
    public static final BitSet FOLLOW_S_in_atstatement373 = new BitSet(new long[]{0x0000003020000000L});
    public static final BitSet FOLLOW_media_in_atstatement376 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LCURLY_in_atstatement382 = new BitSet(new long[]{0xFFFF1A5822800000L,0x0000000000000015L});
    public static final BitSet FOLLOW_S_in_atstatement384 = new BitSet(new long[]{0xFFFF1A5822800000L,0x0000000000000015L});
    public static final BitSet FOLLOW_ruleset_in_atstatement388 = new BitSet(new long[]{0xFFFF1A5822800000L,0x0000000000000015L});
    public static final BitSet FOLLOW_S_in_atstatement390 = new BitSet(new long[]{0xFFFF1A5822800000L,0x0000000000000015L});
    public static final BitSet FOLLOW_RCURLY_in_atstatement395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATKEYWORD_in_atstatement413 = new BitSet(new long[]{0x0000002020000000L});
    public static final BitSet FOLLOW_S_in_atstatement415 = new BitSet(new long[]{0x0000002020000000L});
    public static final BitSet FOLLOW_LCURLY_in_atstatement418 = new BitSet(new long[]{0xFFFF5A5800800000L,0x0000000000000017L});
    public static final BitSet FOLLOW_any_in_atstatement420 = new BitSet(new long[]{0xFFFF5A5800800000L,0x0000000000000017L});
    public static final BitSet FOLLOW_RCURLY_in_atstatement423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pseudo_in_inlineset448 = new BitSet(new long[]{0x0000022020000000L});
    public static final BitSet FOLLOW_S_in_inlineset450 = new BitSet(new long[]{0x0000022020000000L});
    public static final BitSet FOLLOW_COMMA_in_inlineset454 = new BitSet(new long[]{0x0000000820000000L});
    public static final BitSet FOLLOW_S_in_inlineset456 = new BitSet(new long[]{0x0000000820000000L});
    public static final BitSet FOLLOW_pseudo_in_inlineset459 = new BitSet(new long[]{0x0000022020000000L});
    public static final BitSet FOLLOW_S_in_inlineset461 = new BitSet(new long[]{0x0000022020000000L});
    public static final BitSet FOLLOW_LCURLY_in_inlineset474 = new BitSet(new long[]{0xFF831E5800000000L,0x0000000000000061L});
    public static final BitSet FOLLOW_declarations_in_inlineset480 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_RCURLY_in_inlineset485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_media512 = new BitSet(new long[]{0x0000020020000002L});
    public static final BitSet FOLLOW_S_in_media514 = new BitSet(new long[]{0x0000020020000002L});
    public static final BitSet FOLLOW_COMMA_in_media518 = new BitSet(new long[]{0x0000001020000000L});
    public static final BitSet FOLLOW_S_in_media520 = new BitSet(new long[]{0x0000001020000000L});
    public static final BitSet FOLLOW_IDENT_in_media523 = new BitSet(new long[]{0x0000020020000002L});
    public static final BitSet FOLLOW_S_in_media525 = new BitSet(new long[]{0x0000020020000002L});
    public static final BitSet FOLLOW_combined_selector_in_ruleset550 = new BitSet(new long[]{0x0000022000000000L});
    public static final BitSet FOLLOW_COMMA_in_ruleset553 = new BitSet(new long[]{0x8021001822000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_S_in_ruleset555 = new BitSet(new long[]{0x8021001822000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_combined_selector_in_ruleset558 = new BitSet(new long[]{0x0000022000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleset566 = new BitSet(new long[]{0xFF831E5820000000L,0x0000000000000061L});
    public static final BitSet FOLLOW_S_in_ruleset568 = new BitSet(new long[]{0xFF831E5820000000L,0x0000000000000061L});
    public static final BitSet FOLLOW_declarations_in_ruleset576 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleset581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_norule_in_ruleset600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_declarations622 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_declarations626 = new BitSet(new long[]{0xFF831E1820000002L,0x0000000000000061L});
    public static final BitSet FOLLOW_S_in_declarations628 = new BitSet(new long[]{0xFF831E1820000002L,0x0000000000000061L});
    public static final BitSet FOLLOW_declaration_in_declarations631 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_property_in_declaration658 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_COLON_in_declaration660 = new BitSet(new long[]{0xFFFF733820800000L,0x0000000000000017L});
    public static final BitSet FOLLOW_S_in_declaration662 = new BitSet(new long[]{0xFFFF733820800000L,0x0000000000000017L});
    public static final BitSet FOLLOW_terms_in_declaration665 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_important_in_declaration667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_noprop_in_declaration686 = new BitSet(new long[]{0xFFFF5A1800800002L,0x0000000000000017L});
    public static final BitSet FOLLOW_any_in_declaration688 = new BitSet(new long[]{0xFFFF5A1800800002L,0x0000000000000017L});
    public static final BitSet FOLLOW_EXCLAMATION_in_important716 = new BitSet(new long[]{0x0000000020000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_S_in_important718 = new BitSet(new long[]{0x0000000020000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_88_in_important721 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_important723 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_MINUS_in_property748 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_IDENT_in_property751 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_property753 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_term_in_terms781 = new BitSet(new long[]{0xFFFF733820800002L,0x0000000000000017L});
    public static final BitSet FOLLOW_valuepart_in_term814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_term826 = new BitSet(new long[]{0xFFFF5E5820800000L,0x0000000000000017L});
    public static final BitSet FOLLOW_S_in_term828 = new BitSet(new long[]{0xFFFF5E5820800000L,0x0000000000000017L});
    public static final BitSet FOLLOW_any_in_term832 = new BitSet(new long[]{0xFFFF5E5800800000L,0x0000000000000017L});
    public static final BitSet FOLLOW_SEMICOLON_in_term836 = new BitSet(new long[]{0xFFFF5E5820800000L,0x0000000000000017L});
    public static final BitSet FOLLOW_S_in_term838 = new BitSet(new long[]{0xFFFF5E5820800000L,0x0000000000000017L});
    public static final BitSet FOLLOW_RCURLY_in_term843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATKEYWORD_in_term855 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_term857 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_EXPRESSION_in_funct890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_funct899 = new BitSet(new long[]{0xFFFFF33820800000L,0x0000000000000017L});
    public static final BitSet FOLLOW_S_in_funct901 = new BitSet(new long[]{0xFFFFF33820800000L,0x0000000000000017L});
    public static final BitSet FOLLOW_terms_in_funct904 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_RPAREN_in_funct907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart934 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_IDENT_in_valuepart937 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_valuepart954 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart968 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_NUMBER_in_valuepart971 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart988 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_PERCENTAGE_in_valuepart991 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1008 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_DIMENSION_in_valuepart1011 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_string_in_valuepart1028 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_URI_in_valuepart1042 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_HASH_in_valuepart1059 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_valuepart1073 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_INCLUDES_in_valuepart1087 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_COLON_in_valuepart1101 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_COMMA_in_valuepart1115 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_GREATER_in_valuepart1129 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_LESS_in_valuepart1143 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_QUESTION_in_valuepart1157 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_PERCENT_in_valuepart1171 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_EQUALS_in_valuepart1185 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_SLASH_in_valuepart1199 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_PLUS_in_valuepart1212 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_ASTERISK_in_valuepart1225 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_funct_in_valuepart1242 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_valuepart1257 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_LPAREN_in_valuepart1271 = new BitSet(new long[]{0xFFFFF21800800000L,0x0000000000000017L});
    public static final BitSet FOLLOW_valuepart_in_valuepart1273 = new BitSet(new long[]{0xFFFFF21800800000L,0x0000000000000017L});
    public static final BitSet FOLLOW_RPAREN_in_valuepart1276 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_LBRACE_in_valuepart1295 = new BitSet(new long[]{0xFFFF721800800000L,0x000000000000001FL});
    public static final BitSet FOLLOW_valuepart_in_valuepart1297 = new BitSet(new long[]{0xFFFF721800800000L,0x000000000000001FL});
    public static final BitSet FOLLOW_RBRACE_in_valuepart1300 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_valuepart1318 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_selector_in_combined_selector1335 = new BitSet(new long[]{0x4100000020000002L});
    public static final BitSet FOLLOW_combinator_in_combined_selector1339 = new BitSet(new long[]{0x8021001802000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_selector_in_combined_selector1342 = new BitSet(new long[]{0x4100000020000002L});
    public static final BitSet FOLLOW_GREATER_in_combinator1362 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_combinator1364 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_PLUS_in_combinator1374 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_combinator1376 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_combinator1386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_selector1405 = new BitSet(new long[]{0x8021001822000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_ASTERISK_in_selector1409 = new BitSet(new long[]{0x8021001822000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_selpart_in_selector1413 = new BitSet(new long[]{0x8021001822000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_S_in_selector1416 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_selpart_in_selector1446 = new BitSet(new long[]{0x8021001822000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_S_in_selector1449 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_HASH_in_selpart1496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_selpart1504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_selpart1511 = new BitSet(new long[]{0x0000001020000000L});
    public static final BitSet FOLLOW_S_in_selpart1513 = new BitSet(new long[]{0x0000001020000000L});
    public static final BitSet FOLLOW_attribute_in_selpart1516 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_RBRACE_in_selpart1518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pseudo_in_selpart1534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_SELPART_in_selpart1542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_attribute1566 = new BitSet(new long[]{0x1080000020000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_S_in_attribute1568 = new BitSet(new long[]{0x1080000020000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_set_in_attribute1575 = new BitSet(new long[]{0x0000001020800000L,0x0000000000000010L});
    public static final BitSet FOLLOW_S_in_attribute1587 = new BitSet(new long[]{0x0000001020800000L,0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_attribute1591 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_string_in_attribute1595 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_attribute1598 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_COLON_in_pseudo1612 = new BitSet(new long[]{0x0000401000000000L});
    public static final BitSet FOLLOW_IDENT_in_pseudo1615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_pseudo1619 = new BitSet(new long[]{0x0000001020000000L});
    public static final BitSet FOLLOW_S_in_pseudo1621 = new BitSet(new long[]{0x0000001020000000L});
    public static final BitSet FOLLOW_IDENT_in_pseudo1625 = new BitSet(new long[]{0x0000800020000000L});
    public static final BitSet FOLLOW_S_in_pseudo1627 = new BitSet(new long[]{0x0000800020000000L});
    public static final BitSet FOLLOW_RPAREN_in_pseudo1630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_string0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_any1676 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_any1687 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_NUMBER_in_any1698 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_PERCENTAGE_in_any1709 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_DIMENSION_in_any1719 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_string_in_any1730 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_URI_in_any1744 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_HASH_in_any1761 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_any1775 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_INCLUDES_in_any1789 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_COLON_in_any1803 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_COMMA_in_any1817 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_GREATER_in_any1831 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_LESS_in_any1845 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_QUESTION_in_any1859 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_PERCENT_in_any1873 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_EQUALS_in_any1887 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_SLASH_in_any1901 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_any1915 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_MINUS_in_any1926 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_PLUS_in_any1937 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_ASTERISK_in_any1948 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_FUNCTION_in_any1965 = new BitSet(new long[]{0xFFFFDA1820800000L,0x0000000000000017L});
    public static final BitSet FOLLOW_S_in_any1967 = new BitSet(new long[]{0xFFFFDA1820800000L,0x0000000000000017L});
    public static final BitSet FOLLOW_any_in_any1970 = new BitSet(new long[]{0xFFFFDA1800800000L,0x0000000000000017L});
    public static final BitSet FOLLOW_RPAREN_in_any1973 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_any1993 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_LPAREN_in_any2007 = new BitSet(new long[]{0xFFFFDA1800800000L,0x0000000000000017L});
    public static final BitSet FOLLOW_any_in_any2009 = new BitSet(new long[]{0xFFFFDA1800800000L,0x0000000000000017L});
    public static final BitSet FOLLOW_RPAREN_in_any2012 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_LBRACE_in_any2031 = new BitSet(new long[]{0xFFFF5A1800800000L,0x000000000000001FL});
    public static final BitSet FOLLOW_any_in_any2033 = new BitSet(new long[]{0xFFFF5A1800800000L,0x000000000000001FL});
    public static final BitSet FOLLOW_RBRACE_in_any2036 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_any2054 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_noprop2068 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_NUMBER_in_noprop2081 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_COMMA_in_noprop2093 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_GREATER_in_noprop2105 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_LESS_in_noprop2117 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_QUESTION_in_noprop2129 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_PERCENT_in_noprop2141 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_EQUALS_in_noprop2153 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_SLASH_in_noprop2165 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_noprop2177 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_PLUS_in_noprop2189 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_ASTERISK_in_noprop2201 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_noprop2216 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_INCLUDES_in_noprop2228 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_COLON_in_noprop2240 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_STRING_CHAR_in_noprop2252 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_INVALID_TOKEN_in_noprop2264 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_S_in_noprop2277 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_NUMBER_in_norule2292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENTAGE_in_norule2305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_norule2317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_norule2330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_URI_in_norule2344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_norule2361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCLUDES_in_norule2375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_norule2389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_norule2403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_norule2417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_norule2431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENT_in_norule2445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_norule2459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_norule2473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_norule2487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_norule2500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_norule2513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_norule2527 = new BitSet(new long[]{0x0000000000000002L});

}