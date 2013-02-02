// $ANTLR 3.1.2 /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g 2013-02-02 18:07:35
 
package cz.vutbr.web.csskit.antlr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.SupportedCSS;
import cz.vutbr.web.csskit.antlr.CSSLexer.LexerState;


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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "STYLESHEET", "INLINESTYLE", "ATBLOCK", "CURLYBLOCK", "PARENBLOCK", "BRACEBLOCK", "RULE", "SELECTOR", "ELEMENT", "PSEUDO", "ADJACENT", "CHILD", "DESCENDANT", "ATTRIBUTE", "SET", "DECLARATION", "VALUE", "IMPORTANT", "IMPORT_END", "INVALID_STRING", "INVALID_SELECTOR", "INVALID_SELPART", "INVALID_DECLARATION", "INVALID_STATEMENT", "INVALID_IMPORT", "INVALID_DIRECTIVE", "S", "CDO", "CDC", "CHARSET", "IMPORT", "FONTFACE", "LCURLY", "RCURLY", "MEDIA", "ATKEYWORD", "PAGE", "IDENT", "MARGIN_AREA", "COMMA", "SEMICOLON", "COLON", "EXCLAMATION", "MINUS", "EXPRESSION", "FUNCTION", "RPAREN", "CLASSKEYWORD", "NUMBER", "PERCENTAGE", "DIMENSION", "URI", "HASH", "UNIRANGE", "INCLUDES", "GREATER", "LESS", "QUESTION", "PERCENT", "EQUALS", "SLASH", "PLUS", "ASTERISK", "DASHMATCH", "LPAREN", "LBRACE", "RBRACE", "STARTSWITH", "ENDSWITH", "CONTAINS", "STRING", "STRING_CHAR", "INVALID_TOKEN", "IDENT_MACR", "STRING_MACR", "NAME_MACR", "NUMBER_MACR", "W_MACR", "URI_MACR", "APOS", "QUOT", "W_CHAR", "COMMENT", "SL_COMMENT", "NAME_START", "NAME_CHAR", "NON_ASCII", "ESCAPE_CHAR", "URI_CHAR", "NL_CHAR", "'important'", "'#'", "'^'"
    };
    public static final int FUNCTION=49;
    public static final int APOS=83;
    public static final int CLASSKEYWORD=51;
    public static final int CONTAINS=73;
    public static final int INVALID_STATEMENT=27;
    public static final int INVALID_TOKEN=76;
    public static final int EQUALS=63;
    public static final int MEDIA=38;
    public static final int NL_CHAR=93;
    public static final int NON_ASCII=90;
    public static final int EOF=-1;
    public static final int STYLESHEET=4;
    public static final int INCLUDES=58;
    public static final int T__94=94;
    public static final int ENDSWITH=72;
    public static final int INVALID_DIRECTIVE=29;
    public static final int RPAREN=50;
    public static final int IMPORT=34;
    public static final int GREATER=59;
    public static final int EXCLAMATION=46;
    public static final int INVALID_SELPART=25;
    public static final int INVALID_DECLARATION=26;
    public static final int LESS=60;
    public static final int ELEMENT=12;
    public static final int DIMENSION=54;
    public static final int COMMENT=86;
    public static final int T__96=96;
    public static final int T__95=95;
    public static final int CHILD=15;
    public static final int INVALID_STRING=23;
    public static final int RULE=10;
    public static final int RBRACE=70;
    public static final int PARENBLOCK=8;
    public static final int URI_CHAR=92;
    public static final int NUMBER=52;
    public static final int LCURLY=36;
    public static final int FONTFACE=35;
    public static final int SEMICOLON=44;
    public static final int S=30;
    public static final int CDO=31;
    public static final int VALUE=20;
    public static final int CDC=32;
    public static final int PERCENTAGE=53;
    public static final int INVALID_SELECTOR=24;
    public static final int URI=55;
    public static final int STRING_CHAR=75;
    public static final int DASHMATCH=67;
    public static final int IMPORT_END=22;
    public static final int INLINESTYLE=5;
    public static final int SL_COMMENT=87;
    public static final int MARGIN_AREA=42;
    public static final int IDENT_MACR=77;
    public static final int NAME_CHAR=89;
    public static final int PSEUDO=13;
    public static final int LBRACE=69;
    public static final int ATTRIBUTE=17;
    public static final int NAME_START=88;
    public static final int NUMBER_MACR=80;
    public static final int CHARSET=33;
    public static final int DECLARATION=19;
    public static final int ASTERISK=66;
    public static final int LPAREN=68;
    public static final int BRACEBLOCK=9;
    public static final int SELECTOR=11;
    public static final int SLASH=64;
    public static final int ATBLOCK=6;
    public static final int COMMA=43;
    public static final int IDENT=41;
    public static final int UNIRANGE=57;
    public static final int PLUS=65;
    public static final int EXPRESSION=48;
    public static final int CURLYBLOCK=7;
    public static final int ATKEYWORD=39;
    public static final int PERCENT=62;
    public static final int W_CHAR=85;
    public static final int STRING_MACR=78;
    public static final int W_MACR=81;
    public static final int QUOT=84;
    public static final int DESCENDANT=16;
    public static final int HASH=56;
    public static final int SET=18;
    public static final int NAME_MACR=79;
    public static final int MINUS=47;
    public static final int IMPORTANT=21;
    public static final int ESCAPE_CHAR=91;
    public static final int COLON=45;
    public static final int PAGE=40;
    public static final int ADJACENT=14;
    public static final int QUESTION=61;
    public static final int INVALID_IMPORT=28;
    public static final int RCURLY=37;
    public static final int STARTSWITH=71;
    public static final int STRING=74;
    public static final int URI_MACR=82;

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
    		  log.trace("Skipped greedy: {} follow: {}", t, follow);
    		  // consume token even if it will match
    		  input.consume();
    		}while(!(t.getLexerState().isBalanced() && follow.member(t.getType())));
    	} 

      /**
       * Recovers and logs error inside a function, using custom follow set,
       * prepares tree part replacement
       */ 
      private Object invalidFallbackGreedy(int ttype, String ttext, BitSet follow, LexerState.RecoveryMode mode, LexerState ls, RecognitionException re) {
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
        consumeUntilGreedy(input, follow, mode, ls);
        endResync();
        return invalidReplacement(ttype, ttext);
        
        }
      
      /**
       * Consumes token until lexer state is function-balanced and
       * token from follow is matched. Matched token is also consumed
       */ 
      private void consumeUntilGreedy(TokenStream input, BitSet follow, LexerState.RecoveryMode mode, LexerState ls) {
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
        }while(!(t.getLexerState().isBalanced(mode, ls) && follow.member(t.getType())));
      }
      
      /**
       * Recovers and logs error inside a function, using custom follow set,
       * prepares tree part replacement
       */ 
      private Object invalidFallback(int ttype, String ttext, BitSet follow, LexerState.RecoveryMode mode, LexerState ls, RecognitionException re) {
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
        consumeUntil(input, follow, mode, ls);
        endResync();
        return invalidReplacement(ttype, ttext);
        
        }
      
      /**
       * Consumes token until lexer state is function-balanced and
       * token from follow is matched.
       */ 
      private void consumeUntil(TokenStream input, BitSet follow, LexerState.RecoveryMode mode, LexerState ls) {
        CSSToken t = null;
        boolean finish = false;
        do{
          Token next = input.LT(1);
          if (next instanceof CSSToken)
              t= (CSSToken) input.LT(1);
          else
              break; /* not a CSSToken, probably EOF */
          // consume token if does not match
          finish = (t.getLexerState().isBalanced(mode, ls) && follow.member(t.getType()));
          if (!finish)
          { 
              log.trace("Skipped: {}", t);
              input.consume();
          }
        }while(!finish);
      }
        
      /**
       * Obtains the current lexer state from current token
       */
      private LexerState getCurrentLexerState(Token t)
      {
          if (t instanceof CSSToken)
              return ((CSSToken) t).getLexerState();
          else
              return null;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:746:1: inlinestyle : ( S )* ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) ) ;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:753:2: ( ( S )* ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:753:4: ( S )* ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:753:4: ( S )*
            loop1:
            do {
                int alt1=2;
                alt1 = dfa1.predict(input);
                switch (alt1) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:753:4: S
            	    {
            	    S1=(Token)match(input,S,FOLLOW_S_in_inlinestyle201);  
            	    stream_S.add(S1);


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:753:8: ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) )
            int alt3=2;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:753:9: declarations
                    {
                    pushFollow(FOLLOW_declarations_in_inlinestyle206);
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
                    // 753:22: -> ^( INLINESTYLE declarations )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:753:25: ^( INLINESTYLE declarations )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:754:10: ( inlineset )+
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:754:10: ( inlineset )+
                    int cnt2=0;
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==LCURLY||LA2_0==COLON) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:754:10: inlineset
                    	    {
                    	    pushFollow(FOLLOW_inlineset_in_inlinestyle226);
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
                    // 754:21: -> ^( INLINESTYLE ( inlineset )+ )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:754:24: ^( INLINESTYLE ( inlineset )+ )
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:758:1: stylesheet : ( CDO | CDC | S | nostatement | statement )* -> ^( STYLESHEET ( statement )* ) ;
    public final CSSParser.stylesheet_return stylesheet() throws RecognitionException {
        CSSParser.stylesheet_return retval = new CSSParser.stylesheet_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CDO4=null;
        Token CDC5=null;
        Token S6=null;
        CSSParser.nostatement_return nostatement7 = null;

        CSSParser.statement_return statement8 = null;


        Object CDO4_tree=null;
        Object CDC5_tree=null;
        Object S6_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_CDO=new RewriteRuleTokenStream(adaptor,"token CDO");
        RewriteRuleTokenStream stream_CDC=new RewriteRuleTokenStream(adaptor,"token CDC");
        RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
        RewriteRuleSubtreeStream stream_nostatement=new RewriteRuleSubtreeStream(adaptor,"rule nostatement");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:759:2: ( ( CDO | CDC | S | nostatement | statement )* -> ^( STYLESHEET ( statement )* ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:759:4: ( CDO | CDC | S | nostatement | statement )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:759:4: ( CDO | CDC | S | nostatement | statement )*
            loop4:
            do {
                int alt4=6;
                alt4 = dfa4.predict(input);
                switch (alt4) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:759:6: CDO
            	    {
            	    CDO4=(Token)match(input,CDO,FOLLOW_CDO_in_stylesheet254);  
            	    stream_CDO.add(CDO4);


            	    }
            	    break;
            	case 2 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:759:12: CDC
            	    {
            	    CDC5=(Token)match(input,CDC,FOLLOW_CDC_in_stylesheet258);  
            	    stream_CDC.add(CDC5);


            	    }
            	    break;
            	case 3 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:759:18: S
            	    {
            	    S6=(Token)match(input,S,FOLLOW_S_in_stylesheet262);  
            	    stream_S.add(S6);


            	    }
            	    break;
            	case 4 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:759:22: nostatement
            	    {
            	    pushFollow(FOLLOW_nostatement_in_stylesheet266);
            	    nostatement7=nostatement();

            	    state._fsp--;

            	    stream_nostatement.add(nostatement7.getTree());

            	    }
            	    break;
            	case 5 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:759:36: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_stylesheet270);
            	    statement8=statement();

            	    state._fsp--;

            	    stream_statement.add(statement8.getTree());

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
            // 760:3: -> ^( STYLESHEET ( statement )* )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:760:6: ^( STYLESHEET ( statement )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(STYLESHEET, "STYLESHEET"), root_1);

                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:760:19: ( statement )*
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:763:1: statement : ( ruleset | atstatement );
    public final CSSParser.statement_return statement() throws RecognitionException {
        CSSParser.statement_return retval = new CSSParser.statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CSSParser.ruleset_return ruleset9 = null;

        CSSParser.atstatement_return atstatement10 = null;



        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:764:2: ( ruleset | atstatement )
            int alt5=2;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:764:4: ruleset
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ruleset_in_statement300);
                    ruleset9=ruleset();

                    state._fsp--;

                    adaptor.addChild(root_0, ruleset9.getTree());

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:764:14: atstatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_atstatement_in_statement304);
                    atstatement10=atstatement();

                    state._fsp--;

                    adaptor.addChild(root_0, atstatement10.getTree());

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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:767:1: atstatement : ( CHARSET | IMPORT | INVALID_IMPORT | IMPORT_END | page | FONTFACE ( S )* LCURLY ( S )* declarations RCURLY -> ^( FONTFACE declarations ) | MEDIA ( S )* ( media )? LCURLY ( S )* ( ruleset ( S )* )* RCURLY -> ^( MEDIA ( media )? ( ruleset )* ) | ATKEYWORD ( S )* LCURLY ( any )* RCURLY -> INVALID_STATEMENT );
    public final CSSParser.atstatement_return atstatement() throws RecognitionException {
        CSSParser.atstatement_return retval = new CSSParser.atstatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CHARSET11=null;
        Token IMPORT12=null;
        Token INVALID_IMPORT13=null;
        Token IMPORT_END14=null;
        Token FONTFACE16=null;
        Token S17=null;
        Token LCURLY18=null;
        Token S19=null;
        Token RCURLY21=null;
        Token MEDIA22=null;
        Token S23=null;
        Token LCURLY25=null;
        Token S26=null;
        Token S28=null;
        Token RCURLY29=null;
        Token ATKEYWORD30=null;
        Token S31=null;
        Token LCURLY32=null;
        Token RCURLY34=null;
        CSSParser.page_return page15 = null;

        CSSParser.declarations_return declarations20 = null;

        CSSParser.media_return media24 = null;

        CSSParser.ruleset_return ruleset27 = null;

        CSSParser.any_return any33 = null;


        Object CHARSET11_tree=null;
        Object IMPORT12_tree=null;
        Object INVALID_IMPORT13_tree=null;
        Object IMPORT_END14_tree=null;
        Object FONTFACE16_tree=null;
        Object S17_tree=null;
        Object LCURLY18_tree=null;
        Object S19_tree=null;
        Object RCURLY21_tree=null;
        Object MEDIA22_tree=null;
        Object S23_tree=null;
        Object LCURLY25_tree=null;
        Object S26_tree=null;
        Object S28_tree=null;
        Object RCURLY29_tree=null;
        Object ATKEYWORD30_tree=null;
        Object S31_tree=null;
        Object LCURLY32_tree=null;
        Object RCURLY34_tree=null;
        RewriteRuleTokenStream stream_ATKEYWORD=new RewriteRuleTokenStream(adaptor,"token ATKEYWORD");
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_FONTFACE=new RewriteRuleTokenStream(adaptor,"token FONTFACE");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_MEDIA=new RewriteRuleTokenStream(adaptor,"token MEDIA");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_ruleset=new RewriteRuleSubtreeStream(adaptor,"rule ruleset");
        RewriteRuleSubtreeStream stream_any=new RewriteRuleSubtreeStream(adaptor,"rule any");
        RewriteRuleSubtreeStream stream_media=new RewriteRuleSubtreeStream(adaptor,"rule media");
        RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:768:2: ( CHARSET | IMPORT | INVALID_IMPORT | IMPORT_END | page | FONTFACE ( S )* LCURLY ( S )* declarations RCURLY -> ^( FONTFACE declarations ) | MEDIA ( S )* ( media )? LCURLY ( S )* ( ruleset ( S )* )* RCURLY -> ^( MEDIA ( media )? ( ruleset )* ) | ATKEYWORD ( S )* LCURLY ( any )* RCURLY -> INVALID_STATEMENT )
            int alt15=8;
            switch ( input.LA(1) ) {
            case CHARSET:
                {
                alt15=1;
                }
                break;
            case IMPORT:
                {
                alt15=2;
                }
                break;
            case INVALID_IMPORT:
                {
                alt15=3;
                }
                break;
            case IMPORT_END:
                {
                alt15=4;
                }
                break;
            case PAGE:
                {
                alt15=5;
                }
                break;
            case FONTFACE:
                {
                alt15=6;
                }
                break;
            case MEDIA:
                {
                alt15=7;
                }
                break;
            case ATKEYWORD:
                {
                alt15=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:768:4: CHARSET
                    {
                    root_0 = (Object)adaptor.nil();

                    CHARSET11=(Token)match(input,CHARSET,FOLLOW_CHARSET_in_atstatement315); 
                    CHARSET11_tree = (Object)adaptor.create(CHARSET11);
                    adaptor.addChild(root_0, CHARSET11_tree);


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:769:4: IMPORT
                    {
                    root_0 = (Object)adaptor.nil();

                    IMPORT12=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_atstatement320); 
                    IMPORT12_tree = (Object)adaptor.create(IMPORT12);
                    adaptor.addChild(root_0, IMPORT12_tree);


                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:770:4: INVALID_IMPORT
                    {
                    root_0 = (Object)adaptor.nil();

                    INVALID_IMPORT13=(Token)match(input,INVALID_IMPORT,FOLLOW_INVALID_IMPORT_in_atstatement325); 
                    INVALID_IMPORT13_tree = (Object)adaptor.create(INVALID_IMPORT13);
                    adaptor.addChild(root_0, INVALID_IMPORT13_tree);


                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:771:4: IMPORT_END
                    {
                    root_0 = (Object)adaptor.nil();

                    IMPORT_END14=(Token)match(input,IMPORT_END,FOLLOW_IMPORT_END_in_atstatement330); 
                    IMPORT_END14_tree = (Object)adaptor.create(IMPORT_END14);
                    adaptor.addChild(root_0, IMPORT_END14_tree);


                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:772:4: page
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_page_in_atstatement335);
                    page15=page();

                    state._fsp--;

                    adaptor.addChild(root_0, page15.getTree());

                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:773:4: FONTFACE ( S )* LCURLY ( S )* declarations RCURLY
                    {
                    FONTFACE16=(Token)match(input,FONTFACE,FOLLOW_FONTFACE_in_atstatement340);  
                    stream_FONTFACE.add(FONTFACE16);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:773:13: ( S )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==S) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:773:13: S
                    	    {
                    	    S17=(Token)match(input,S,FOLLOW_S_in_atstatement342);  
                    	    stream_S.add(S17);


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    LCURLY18=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atstatement348);  
                    stream_LCURLY.add(LCURLY18);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:774:11: ( S )*
                    loop7:
                    do {
                        int alt7=2;
                        alt7 = dfa7.predict(input);
                        switch (alt7) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:774:11: S
                    	    {
                    	    S19=(Token)match(input,S,FOLLOW_S_in_atstatement350);  
                    	    stream_S.add(S19);


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    pushFollow(FOLLOW_declarations_in_atstatement353);
                    declarations20=declarations();

                    state._fsp--;

                    stream_declarations.add(declarations20.getTree());
                    RCURLY21=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atstatement358);  
                    stream_RCURLY.add(RCURLY21);



                    // AST REWRITE
                    // elements: FONTFACE, declarations
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 775:11: -> ^( FONTFACE declarations )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:775:14: ^( FONTFACE declarations )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_FONTFACE.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_declarations.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:776:4: MEDIA ( S )* ( media )? LCURLY ( S )* ( ruleset ( S )* )* RCURLY
                    {
                    MEDIA22=(Token)match(input,MEDIA,FOLLOW_MEDIA_in_atstatement371);  
                    stream_MEDIA.add(MEDIA22);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:776:10: ( S )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==S) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:776:10: S
                    	    {
                    	    S23=(Token)match(input,S,FOLLOW_S_in_atstatement373);  
                    	    stream_S.add(S23);


                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:776:13: ( media )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==IDENT) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:776:13: media
                            {
                            pushFollow(FOLLOW_media_in_atstatement376);
                            media24=media();

                            state._fsp--;

                            stream_media.add(media24.getTree());

                            }
                            break;

                    }

                    LCURLY25=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atstatement382);  
                    stream_LCURLY.add(LCURLY25);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:10: ( S )*
                    loop10:
                    do {
                        int alt10=2;
                        alt10 = dfa10.predict(input);
                        switch (alt10) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:10: S
                    	    {
                    	    S26=(Token)match(input,S,FOLLOW_S_in_atstatement384);  
                    	    stream_S.add(S26);


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:13: ( ruleset ( S )* )*
                    loop12:
                    do {
                        int alt12=2;
                        alt12 = dfa12.predict(input);
                        switch (alt12) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:14: ruleset ( S )*
                    	    {
                    	    pushFollow(FOLLOW_ruleset_in_atstatement388);
                    	    ruleset27=ruleset();

                    	    state._fsp--;

                    	    stream_ruleset.add(ruleset27.getTree());
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:22: ( S )*
                    	    loop11:
                    	    do {
                    	        int alt11=2;
                    	        alt11 = dfa11.predict(input);
                    	        switch (alt11) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:22: S
                    	    	    {
                    	    	    S28=(Token)match(input,S,FOLLOW_S_in_atstatement390);  
                    	    	    stream_S.add(S28);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop11;
                    	        }
                    	    } while (true);


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    RCURLY29=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atstatement395);  
                    stream_RCURLY.add(RCURLY29);



                    // AST REWRITE
                    // elements: MEDIA, media, ruleset
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 777:34: -> ^( MEDIA ( media )? ( ruleset )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:37: ^( MEDIA ( media )? ( ruleset )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_MEDIA.nextNode(), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:45: ( media )?
                        if ( stream_media.hasNext() ) {
                            adaptor.addChild(root_1, stream_media.nextTree());

                        }
                        stream_media.reset();
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:52: ( ruleset )*
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
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:778:4: ATKEYWORD ( S )* LCURLY ( any )* RCURLY
                    {
                    ATKEYWORD30=(Token)match(input,ATKEYWORD,FOLLOW_ATKEYWORD_in_atstatement413);  
                    stream_ATKEYWORD.add(ATKEYWORD30);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:778:14: ( S )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==S) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:778:14: S
                    	    {
                    	    S31=(Token)match(input,S,FOLLOW_S_in_atstatement415);  
                    	    stream_S.add(S31);


                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);

                    LCURLY32=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atstatement418);  
                    stream_LCURLY.add(LCURLY32);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:778:24: ( any )*
                    loop14:
                    do {
                        int alt14=2;
                        alt14 = dfa14.predict(input);
                        switch (alt14) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:778:24: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_atstatement420);
                    	    any33=any();

                    	    state._fsp--;

                    	    stream_any.add(any33.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);

                    RCURLY34=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atstatement423);  
                    stream_RCURLY.add(RCURLY34);



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
                    // 778:36: -> INVALID_STATEMENT
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

    public static class page_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "page"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:786:1: page : PAGE ( S )* ( ( IDENT | IDENT page_pseudo | page_pseudo ) ( S )* )? LCURLY ( S )* declarations ( margin_rule )* RCURLY -> ^( PAGE ( IDENT )? ( page_pseudo )? declarations ^( SET ( margin_rule )* ) ) ;
    public final CSSParser.page_return page() throws RecognitionException {
        CSSParser.page_return retval = new CSSParser.page_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PAGE35=null;
        Token S36=null;
        Token IDENT37=null;
        Token IDENT38=null;
        Token S41=null;
        Token LCURLY42=null;
        Token S43=null;
        Token RCURLY46=null;
        CSSParser.page_pseudo_return page_pseudo39 = null;

        CSSParser.page_pseudo_return page_pseudo40 = null;

        CSSParser.declarations_return declarations44 = null;

        CSSParser.margin_rule_return margin_rule45 = null;


        Object PAGE35_tree=null;
        Object S36_tree=null;
        Object IDENT37_tree=null;
        Object IDENT38_tree=null;
        Object S41_tree=null;
        Object LCURLY42_tree=null;
        Object S43_tree=null;
        Object RCURLY46_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_PAGE=new RewriteRuleTokenStream(adaptor,"token PAGE");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_page_pseudo=new RewriteRuleSubtreeStream(adaptor,"rule page_pseudo");
        RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");
        RewriteRuleSubtreeStream stream_margin_rule=new RewriteRuleSubtreeStream(adaptor,"rule margin_rule");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:787:2: ( PAGE ( S )* ( ( IDENT | IDENT page_pseudo | page_pseudo ) ( S )* )? LCURLY ( S )* declarations ( margin_rule )* RCURLY -> ^( PAGE ( IDENT )? ( page_pseudo )? declarations ^( SET ( margin_rule )* ) ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:787:4: PAGE ( S )* ( ( IDENT | IDENT page_pseudo | page_pseudo ) ( S )* )? LCURLY ( S )* declarations ( margin_rule )* RCURLY
            {
            PAGE35=(Token)match(input,PAGE,FOLLOW_PAGE_in_page445);  
            stream_PAGE.add(PAGE35);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:787:9: ( S )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==S) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:787:9: S
            	    {
            	    S36=(Token)match(input,S,FOLLOW_S_in_page447);  
            	    stream_S.add(S36);


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:787:12: ( ( IDENT | IDENT page_pseudo | page_pseudo ) ( S )* )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==IDENT||LA19_0==COLON) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:787:13: ( IDENT | IDENT page_pseudo | page_pseudo ) ( S )*
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:787:13: ( IDENT | IDENT page_pseudo | page_pseudo )
                    int alt17=3;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==IDENT) ) {
                        int LA17_1 = input.LA(2);

                        if ( (LA17_1==COLON) ) {
                            alt17=2;
                        }
                        else if ( (LA17_1==S||LA17_1==LCURLY) ) {
                            alt17=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 17, 1, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA17_0==COLON) ) {
                        alt17=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 17, 0, input);

                        throw nvae;
                    }
                    switch (alt17) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:787:15: IDENT
                            {
                            IDENT37=(Token)match(input,IDENT,FOLLOW_IDENT_in_page453);  
                            stream_IDENT.add(IDENT37);


                            }
                            break;
                        case 2 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:787:23: IDENT page_pseudo
                            {
                            IDENT38=(Token)match(input,IDENT,FOLLOW_IDENT_in_page457);  
                            stream_IDENT.add(IDENT38);

                            pushFollow(FOLLOW_page_pseudo_in_page459);
                            page_pseudo39=page_pseudo();

                            state._fsp--;

                            stream_page_pseudo.add(page_pseudo39.getTree());

                            }
                            break;
                        case 3 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:787:43: page_pseudo
                            {
                            pushFollow(FOLLOW_page_pseudo_in_page463);
                            page_pseudo40=page_pseudo();

                            state._fsp--;

                            stream_page_pseudo.add(page_pseudo40.getTree());

                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:787:56: ( S )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==S) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:787:56: S
                    	    {
                    	    S41=(Token)match(input,S,FOLLOW_S_in_page466);  
                    	    stream_S.add(S41);


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);


                    }
                    break;

            }

            LCURLY42=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_page474);  
            stream_LCURLY.add(LCURLY42);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:10: ( S )*
            loop20:
            do {
                int alt20=2;
                alt20 = dfa20.predict(input);
                switch (alt20) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:10: S
            	    {
            	    S43=(Token)match(input,S,FOLLOW_S_in_page476);  
            	    stream_S.add(S43);


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            pushFollow(FOLLOW_declarations_in_page481);
            declarations44=declarations();

            state._fsp--;

            stream_declarations.add(declarations44.getTree());
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:789:16: ( margin_rule )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==MARGIN_AREA) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:789:16: margin_rule
            	    {
            	    pushFollow(FOLLOW_margin_rule_in_page483);
            	    margin_rule45=margin_rule();

            	    state._fsp--;

            	    stream_margin_rule.add(margin_rule45.getTree());

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            RCURLY46=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_page488);  
            stream_RCURLY.add(RCURLY46);



            // AST REWRITE
            // elements: margin_rule, PAGE, page_pseudo, IDENT, declarations
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 791:3: -> ^( PAGE ( IDENT )? ( page_pseudo )? declarations ^( SET ( margin_rule )* ) )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:791:6: ^( PAGE ( IDENT )? ( page_pseudo )? declarations ^( SET ( margin_rule )* ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_PAGE.nextNode(), root_1);

                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:791:13: ( IDENT )?
                if ( stream_IDENT.hasNext() ) {
                    adaptor.addChild(root_1, stream_IDENT.nextNode());

                }
                stream_IDENT.reset();
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:791:20: ( page_pseudo )?
                if ( stream_page_pseudo.hasNext() ) {
                    adaptor.addChild(root_1, stream_page_pseudo.nextTree());

                }
                stream_page_pseudo.reset();
                adaptor.addChild(root_1, stream_declarations.nextTree());
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:791:46: ^( SET ( margin_rule )* )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(SET, "SET"), root_2);

                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:791:52: ( margin_rule )*
                while ( stream_margin_rule.hasNext() ) {
                    adaptor.addChild(root_2, stream_margin_rule.nextTree());

                }
                stream_margin_rule.reset();

                adaptor.addChild(root_1, root_2);
                }

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
    // $ANTLR end "page"

    public static class page_pseudo_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "page_pseudo"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:794:1: page_pseudo : pseudocolon IDENT ;
    public final CSSParser.page_pseudo_return page_pseudo() throws RecognitionException {
        CSSParser.page_pseudo_return retval = new CSSParser.page_pseudo_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT48=null;
        CSSParser.pseudocolon_return pseudocolon47 = null;


        Object IDENT48_tree=null;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:795:2: ( pseudocolon IDENT )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:795:4: pseudocolon IDENT
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_pseudocolon_in_page_pseudo522);
            pseudocolon47=pseudocolon();

            state._fsp--;

            root_0 = (Object)adaptor.becomeRoot(pseudocolon47.getTree(), root_0);
            IDENT48=(Token)match(input,IDENT,FOLLOW_IDENT_in_page_pseudo525); 
            IDENT48_tree = (Object)adaptor.create(IDENT48);
            adaptor.addChild(root_0, IDENT48_tree);


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
    // $ANTLR end "page_pseudo"

    public static class margin_rule_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "margin_rule"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:798:1: margin_rule : MARGIN_AREA ( S )* LCURLY ( S )* declarations RCURLY ( S )* -> ^( MARGIN_AREA declarations ) ;
    public final CSSParser.margin_rule_return margin_rule() throws RecognitionException {
        CSSParser.margin_rule_return retval = new CSSParser.margin_rule_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MARGIN_AREA49=null;
        Token S50=null;
        Token LCURLY51=null;
        Token S52=null;
        Token RCURLY54=null;
        Token S55=null;
        CSSParser.declarations_return declarations53 = null;


        Object MARGIN_AREA49_tree=null;
        Object S50_tree=null;
        Object LCURLY51_tree=null;
        Object S52_tree=null;
        Object RCURLY54_tree=null;
        Object S55_tree=null;
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleTokenStream stream_MARGIN_AREA=new RewriteRuleTokenStream(adaptor,"token MARGIN_AREA");
        RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:799:2: ( MARGIN_AREA ( S )* LCURLY ( S )* declarations RCURLY ( S )* -> ^( MARGIN_AREA declarations ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:799:4: MARGIN_AREA ( S )* LCURLY ( S )* declarations RCURLY ( S )*
            {
            MARGIN_AREA49=(Token)match(input,MARGIN_AREA,FOLLOW_MARGIN_AREA_in_margin_rule536);  
            stream_MARGIN_AREA.add(MARGIN_AREA49);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:799:16: ( S )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==S) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:799:16: S
            	    {
            	    S50=(Token)match(input,S,FOLLOW_S_in_margin_rule538);  
            	    stream_S.add(S50);


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            LCURLY51=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_margin_rule541);  
            stream_LCURLY.add(LCURLY51);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:799:26: ( S )*
            loop23:
            do {
                int alt23=2;
                alt23 = dfa23.predict(input);
                switch (alt23) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:799:26: S
            	    {
            	    S52=(Token)match(input,S,FOLLOW_S_in_margin_rule543);  
            	    stream_S.add(S52);


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            pushFollow(FOLLOW_declarations_in_margin_rule546);
            declarations53=declarations();

            state._fsp--;

            stream_declarations.add(declarations53.getTree());
            RCURLY54=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_margin_rule548);  
            stream_RCURLY.add(RCURLY54);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:799:49: ( S )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==S) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:799:49: S
            	    {
            	    S55=(Token)match(input,S,FOLLOW_S_in_margin_rule550);  
            	    stream_S.add(S55);


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);



            // AST REWRITE
            // elements: declarations, MARGIN_AREA
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 799:52: -> ^( MARGIN_AREA declarations )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:799:55: ^( MARGIN_AREA declarations )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_MARGIN_AREA.nextNode(), root_1);

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
    // $ANTLR end "margin_rule"

    public static class inlineset_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "inlineset"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:802:1: inlineset : ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )? LCURLY declarations RCURLY -> ^( RULE ( pseudo )* declarations ) ;
    public final CSSParser.inlineset_return inlineset() throws RecognitionException {
        CSSParser.inlineset_return retval = new CSSParser.inlineset_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token S57=null;
        Token COMMA58=null;
        Token S59=null;
        Token S61=null;
        Token LCURLY62=null;
        Token RCURLY64=null;
        CSSParser.pseudo_return pseudo56 = null;

        CSSParser.pseudo_return pseudo60 = null;

        CSSParser.declarations_return declarations63 = null;


        Object S57_tree=null;
        Object COMMA58_tree=null;
        Object S59_tree=null;
        Object S61_tree=null;
        Object LCURLY62_tree=null;
        Object RCURLY64_tree=null;
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_pseudo=new RewriteRuleSubtreeStream(adaptor,"rule pseudo");
        RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:805:2: ( ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )? LCURLY declarations RCURLY -> ^( RULE ( pseudo )* declarations ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:805:4: ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )? LCURLY declarations RCURLY
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:805:4: ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==COLON) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:805:5: pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )*
                    {
                    pushFollow(FOLLOW_pseudo_in_inlineset573);
                    pseudo56=pseudo();

                    state._fsp--;

                    stream_pseudo.add(pseudo56.getTree());
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:805:12: ( S )*
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==S) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:805:12: S
                    	    {
                    	    S57=(Token)match(input,S,FOLLOW_S_in_inlineset575);  
                    	    stream_S.add(S57);


                    	    }
                    	    break;

                    	default :
                    	    break loop25;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:805:15: ( COMMA ( S )* pseudo ( S )* )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==COMMA) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:805:16: COMMA ( S )* pseudo ( S )*
                    	    {
                    	    COMMA58=(Token)match(input,COMMA,FOLLOW_COMMA_in_inlineset579);  
                    	    stream_COMMA.add(COMMA58);

                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:805:22: ( S )*
                    	    loop26:
                    	    do {
                    	        int alt26=2;
                    	        int LA26_0 = input.LA(1);

                    	        if ( (LA26_0==S) ) {
                    	            alt26=1;
                    	        }


                    	        switch (alt26) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:805:22: S
                    	    	    {
                    	    	    S59=(Token)match(input,S,FOLLOW_S_in_inlineset581);  
                    	    	    stream_S.add(S59);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop26;
                    	        }
                    	    } while (true);

                    	    pushFollow(FOLLOW_pseudo_in_inlineset584);
                    	    pseudo60=pseudo();

                    	    state._fsp--;

                    	    stream_pseudo.add(pseudo60.getTree());
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:805:32: ( S )*
                    	    loop27:
                    	    do {
                    	        int alt27=2;
                    	        int LA27_0 = input.LA(1);

                    	        if ( (LA27_0==S) ) {
                    	            alt27=1;
                    	        }


                    	        switch (alt27) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:805:32: S
                    	    	    {
                    	    	    S61=(Token)match(input,S,FOLLOW_S_in_inlineset586);  
                    	    	    stream_S.add(S61);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop27;
                    	        }
                    	    } while (true);


                    	    }
                    	    break;

                    	default :
                    	    break loop28;
                        }
                    } while (true);


                    }
                    break;

            }

            LCURLY62=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_inlineset599);  
            stream_LCURLY.add(LCURLY62);

            pushFollow(FOLLOW_declarations_in_inlineset605);
            declarations63=declarations();

            state._fsp--;

            stream_declarations.add(declarations63.getTree());
            RCURLY64=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_inlineset610);  
            stream_RCURLY.add(RCURLY64);



            // AST REWRITE
            // elements: pseudo, declarations
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 809:4: -> ^( RULE ( pseudo )* declarations )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:809:7: ^( RULE ( pseudo )* declarations )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(RULE, "RULE"), root_1);

                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:809:14: ( pseudo )*
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:812:1: media : IDENT ( S )* ( COMMA ( S )* IDENT ( S )* )* -> ( IDENT )+ ;
    public final CSSParser.media_return media() throws RecognitionException {
        CSSParser.media_return retval = new CSSParser.media_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT65=null;
        Token S66=null;
        Token COMMA67=null;
        Token S68=null;
        Token IDENT69=null;
        Token S70=null;

        Object IDENT65_tree=null;
        Object S66_tree=null;
        Object COMMA67_tree=null;
        Object S68_tree=null;
        Object IDENT69_tree=null;
        Object S70_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:813:2: ( IDENT ( S )* ( COMMA ( S )* IDENT ( S )* )* -> ( IDENT )+ )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:813:4: IDENT ( S )* ( COMMA ( S )* IDENT ( S )* )*
            {
            IDENT65=(Token)match(input,IDENT,FOLLOW_IDENT_in_media637);  
            stream_IDENT.add(IDENT65);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:813:10: ( S )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==S) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:813:10: S
            	    {
            	    S66=(Token)match(input,S,FOLLOW_S_in_media639);  
            	    stream_S.add(S66);


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:813:13: ( COMMA ( S )* IDENT ( S )* )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==COMMA) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:813:14: COMMA ( S )* IDENT ( S )*
            	    {
            	    COMMA67=(Token)match(input,COMMA,FOLLOW_COMMA_in_media643);  
            	    stream_COMMA.add(COMMA67);

            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:813:20: ( S )*
            	    loop31:
            	    do {
            	        int alt31=2;
            	        int LA31_0 = input.LA(1);

            	        if ( (LA31_0==S) ) {
            	            alt31=1;
            	        }


            	        switch (alt31) {
            	    	case 1 :
            	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:813:20: S
            	    	    {
            	    	    S68=(Token)match(input,S,FOLLOW_S_in_media645);  
            	    	    stream_S.add(S68);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop31;
            	        }
            	    } while (true);

            	    IDENT69=(Token)match(input,IDENT,FOLLOW_IDENT_in_media648);  
            	    stream_IDENT.add(IDENT69);

            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:813:29: ( S )*
            	    loop32:
            	    do {
            	        int alt32=2;
            	        int LA32_0 = input.LA(1);

            	        if ( (LA32_0==S) ) {
            	            alt32=1;
            	        }


            	        switch (alt32) {
            	    	case 1 :
            	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:813:29: S
            	    	    {
            	    	    S70=(Token)match(input,S,FOLLOW_S_in_media650);  
            	    	    stream_S.add(S70);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop32;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop33;
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
            // 814:3: -> ( IDENT )+
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:817:1: ruleset : ( combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY -> ^( RULE ( combined_selector )+ declarations ) | norule -> INVALID_STATEMENT );
    public final CSSParser.ruleset_return ruleset() throws RecognitionException {
        CSSParser.ruleset_return retval = new CSSParser.ruleset_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA72=null;
        Token S73=null;
        Token LCURLY75=null;
        Token S76=null;
        Token RCURLY78=null;
        CSSParser.combined_selector_return combined_selector71 = null;

        CSSParser.combined_selector_return combined_selector74 = null;

        CSSParser.declarations_return declarations77 = null;

        CSSParser.norule_return norule79 = null;


        Object COMMA72_tree=null;
        Object S73_tree=null;
        Object LCURLY75_tree=null;
        Object S76_tree=null;
        Object RCURLY78_tree=null;
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_combined_selector=new RewriteRuleSubtreeStream(adaptor,"rule combined_selector");
        RewriteRuleSubtreeStream stream_norule=new RewriteRuleSubtreeStream(adaptor,"rule norule");
        RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:818:2: ( combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY -> ^( RULE ( combined_selector )+ declarations ) | norule -> INVALID_STATEMENT )
            int alt37=2;
            alt37 = dfa37.predict(input);
            switch (alt37) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:818:4: combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY
                    {
                    pushFollow(FOLLOW_combined_selector_in_ruleset675);
                    combined_selector71=combined_selector();

                    state._fsp--;

                    stream_combined_selector.add(combined_selector71.getTree());
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:818:22: ( COMMA ( S )* combined_selector )*
                    loop35:
                    do {
                        int alt35=2;
                        int LA35_0 = input.LA(1);

                        if ( (LA35_0==COMMA) ) {
                            alt35=1;
                        }


                        switch (alt35) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:818:23: COMMA ( S )* combined_selector
                    	    {
                    	    COMMA72=(Token)match(input,COMMA,FOLLOW_COMMA_in_ruleset678);  
                    	    stream_COMMA.add(COMMA72);

                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:818:29: ( S )*
                    	    loop34:
                    	    do {
                    	        int alt34=2;
                    	        int LA34_0 = input.LA(1);

                    	        if ( (LA34_0==S) ) {
                    	            alt34=1;
                    	        }


                    	        switch (alt34) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:818:29: S
                    	    	    {
                    	    	    S73=(Token)match(input,S,FOLLOW_S_in_ruleset680);  
                    	    	    stream_S.add(S73);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop34;
                    	        }
                    	    } while (true);

                    	    pushFollow(FOLLOW_combined_selector_in_ruleset683);
                    	    combined_selector74=combined_selector();

                    	    state._fsp--;

                    	    stream_combined_selector.add(combined_selector74.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop35;
                        }
                    } while (true);

                    LCURLY75=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_ruleset691);  
                    stream_LCURLY.add(LCURLY75);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:819:11: ( S )*
                    loop36:
                    do {
                        int alt36=2;
                        alt36 = dfa36.predict(input);
                        switch (alt36) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:819:11: S
                    	    {
                    	    S76=(Token)match(input,S,FOLLOW_S_in_ruleset693);  
                    	    stream_S.add(S76);


                    	    }
                    	    break;

                    	default :
                    	    break loop36;
                        }
                    } while (true);

                    pushFollow(FOLLOW_declarations_in_ruleset701);
                    declarations77=declarations();

                    state._fsp--;

                    stream_declarations.add(declarations77.getTree());
                    RCURLY78=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_ruleset706);  
                    stream_RCURLY.add(RCURLY78);



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
                    // 822:4: -> ^( RULE ( combined_selector )+ declarations )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:822:7: ^( RULE ( combined_selector )+ declarations )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:823:4: norule
                    {
                    pushFollow(FOLLOW_norule_in_ruleset725);
                    norule79=norule();

                    state._fsp--;

                    stream_norule.add(norule79.getTree());


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
                    // 823:11: -> INVALID_STATEMENT
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

                  final BitSet follow = BitSet.of(CSSLexer.RCURLY);
                  //we don't require {} to be balanced here because of possible parent 'media' sections that may remain open => RecoveryMode.RULE
            	    retval.tree = invalidFallbackGreedy(CSSLexer.INVALID_STATEMENT,	"INVALID_STATEMENT", follow, LexerState.RecoveryMode.RULE, null, re);							
            	
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:831:1: declarations : ( declaration )? ( SEMICOLON ( S )* ( declaration )? )* -> ^( SET ( declaration )* ) ;
    public final CSSParser.declarations_return declarations() throws RecognitionException {
        CSSParser.declarations_return retval = new CSSParser.declarations_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SEMICOLON81=null;
        Token S82=null;
        CSSParser.declaration_return declaration80 = null;

        CSSParser.declaration_return declaration83 = null;


        Object SEMICOLON81_tree=null;
        Object S82_tree=null;
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleSubtreeStream stream_declaration=new RewriteRuleSubtreeStream(adaptor,"rule declaration");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:832:2: ( ( declaration )? ( SEMICOLON ( S )* ( declaration )? )* -> ^( SET ( declaration )* ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:832:4: ( declaration )? ( SEMICOLON ( S )* ( declaration )? )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:832:4: ( declaration )?
            int alt38=2;
            alt38 = dfa38.predict(input);
            switch (alt38) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:832:4: declaration
                    {
                    pushFollow(FOLLOW_declaration_in_declarations747);
                    declaration80=declaration();

                    state._fsp--;

                    stream_declaration.add(declaration80.getTree());

                    }
                    break;

            }

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:832:17: ( SEMICOLON ( S )* ( declaration )? )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==SEMICOLON) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:832:18: SEMICOLON ( S )* ( declaration )?
            	    {
            	    SEMICOLON81=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_declarations751);  
            	    stream_SEMICOLON.add(SEMICOLON81);

            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:832:28: ( S )*
            	    loop39:
            	    do {
            	        int alt39=2;
            	        alt39 = dfa39.predict(input);
            	        switch (alt39) {
            	    	case 1 :
            	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:832:28: S
            	    	    {
            	    	    S82=(Token)match(input,S,FOLLOW_S_in_declarations753);  
            	    	    stream_S.add(S82);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop39;
            	        }
            	    } while (true);

            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:832:31: ( declaration )?
            	    int alt40=2;
            	    alt40 = dfa40.predict(input);
            	    switch (alt40) {
            	        case 1 :
            	            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:832:31: declaration
            	            {
            	            pushFollow(FOLLOW_declaration_in_declarations756);
            	            declaration83=declaration();

            	            state._fsp--;

            	            stream_declaration.add(declaration83.getTree());

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop41;
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
            // 833:4: -> ^( SET ( declaration )* )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:833:7: ^( SET ( declaration )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SET, "SET"), root_1);

                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:833:13: ( declaration )*
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:836:1: declaration : ( property COLON ( S )* ( terms )? ( important )? -> ^( DECLARATION ( important )? property ( terms )? ) | noprop ( any )* -> INVALID_DECLARATION );
    public final CSSParser.declaration_return declaration() throws RecognitionException {
        CSSParser.declaration_return retval = new CSSParser.declaration_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON85=null;
        Token S86=null;
        CSSParser.property_return property84 = null;

        CSSParser.terms_return terms87 = null;

        CSSParser.important_return important88 = null;

        CSSParser.noprop_return noprop89 = null;

        CSSParser.any_return any90 = null;


        Object COLON85_tree=null;
        Object S86_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleSubtreeStream stream_important=new RewriteRuleSubtreeStream(adaptor,"rule important");
        RewriteRuleSubtreeStream stream_any=new RewriteRuleSubtreeStream(adaptor,"rule any");
        RewriteRuleSubtreeStream stream_terms=new RewriteRuleSubtreeStream(adaptor,"rule terms");
        RewriteRuleSubtreeStream stream_property=new RewriteRuleSubtreeStream(adaptor,"rule property");
        RewriteRuleSubtreeStream stream_noprop=new RewriteRuleSubtreeStream(adaptor,"rule noprop");

          LexerState begin = getCurrentLexerState(retval.start);
          log.trace("Decl begin: " + begin);

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:841:2: ( property COLON ( S )* ( terms )? ( important )? -> ^( DECLARATION ( important )? property ( terms )? ) | noprop ( any )* -> INVALID_DECLARATION )
            int alt46=2;
            alt46 = dfa46.predict(input);
            switch (alt46) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:841:4: property COLON ( S )* ( terms )? ( important )?
                    {
                    pushFollow(FOLLOW_property_in_declaration788);
                    property84=property();

                    state._fsp--;

                    stream_property.add(property84.getTree());
                    COLON85=(Token)match(input,COLON,FOLLOW_COLON_in_declaration790);  
                    stream_COLON.add(COLON85);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:841:19: ( S )*
                    loop42:
                    do {
                        int alt42=2;
                        alt42 = dfa42.predict(input);
                        switch (alt42) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:841:19: S
                    	    {
                    	    S86=(Token)match(input,S,FOLLOW_S_in_declaration792);  
                    	    stream_S.add(S86);


                    	    }
                    	    break;

                    	default :
                    	    break loop42;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:841:22: ( terms )?
                    int alt43=2;
                    alt43 = dfa43.predict(input);
                    switch (alt43) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:841:22: terms
                            {
                            pushFollow(FOLLOW_terms_in_declaration795);
                            terms87=terms();

                            state._fsp--;

                            stream_terms.add(terms87.getTree());

                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:841:29: ( important )?
                    int alt44=2;
                    int LA44_0 = input.LA(1);

                    if ( (LA44_0==EXCLAMATION) ) {
                        alt44=1;
                    }
                    switch (alt44) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:841:29: important
                            {
                            pushFollow(FOLLOW_important_in_declaration798);
                            important88=important();

                            state._fsp--;

                            stream_important.add(important88.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: terms, important, property
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 841:40: -> ^( DECLARATION ( important )? property ( terms )? )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:841:43: ^( DECLARATION ( important )? property ( terms )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DECLARATION, "DECLARATION"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:841:57: ( important )?
                        if ( stream_important.hasNext() ) {
                            adaptor.addChild(root_1, stream_important.nextTree());

                        }
                        stream_important.reset();
                        adaptor.addChild(root_1, stream_property.nextTree());
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:841:77: ( terms )?
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
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:4: noprop ( any )*
                    {
                    pushFollow(FOLLOW_noprop_in_declaration818);
                    noprop89=noprop();

                    state._fsp--;

                    stream_noprop.add(noprop89.getTree());
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:11: ( any )*
                    loop45:
                    do {
                        int alt45=2;
                        alt45 = dfa45.predict(input);
                        switch (alt45) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:11: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_declaration820);
                    	    any90=any();

                    	    state._fsp--;

                    	    stream_any.add(any90.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop45;
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
                    // 842:16: -> INVALID_DECLARATION
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

            	  //retval.tree = invalidFallback(CSSLexer.INVALID_DECLARATION, "INVALID_DECLARATION", re);									
                  final BitSet follow = BitSet.of(CSSLexer.SEMICOLON);               
                  retval.tree = invalidFallback(CSSLexer.INVALID_DECLARATION, "INVALID_DECLARATION", follow, LexerState.RecoveryMode.DECL, begin, re);             
            	
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:850:1: important : EXCLAMATION ( S )* 'important' ( S )* -> IMPORTANT ;
    public final CSSParser.important_return important() throws RecognitionException {
        CSSParser.important_return retval = new CSSParser.important_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EXCLAMATION91=null;
        Token S92=null;
        Token string_literal93=null;
        Token S94=null;

        Object EXCLAMATION91_tree=null;
        Object S92_tree=null;
        Object string_literal93_tree=null;
        Object S94_tree=null;
        RewriteRuleTokenStream stream_94=new RewriteRuleTokenStream(adaptor,"token 94");
        RewriteRuleTokenStream stream_EXCLAMATION=new RewriteRuleTokenStream(adaptor,"token EXCLAMATION");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:851:3: ( EXCLAMATION ( S )* 'important' ( S )* -> IMPORTANT )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:851:5: EXCLAMATION ( S )* 'important' ( S )*
            {
            EXCLAMATION91=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_important846);  
            stream_EXCLAMATION.add(EXCLAMATION91);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:851:17: ( S )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==S) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:851:17: S
            	    {
            	    S92=(Token)match(input,S,FOLLOW_S_in_important848);  
            	    stream_S.add(S92);


            	    }
            	    break;

            	default :
            	    break loop47;
                }
            } while (true);

            string_literal93=(Token)match(input,94,FOLLOW_94_in_important851);  
            stream_94.add(string_literal93);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:851:32: ( S )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0==S) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:851:32: S
            	    {
            	    S94=(Token)match(input,S,FOLLOW_S_in_important853);  
            	    stream_S.add(S94);


            	    }
            	    break;

            	default :
            	    break loop48;
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
            // 851:35: -> IMPORTANT
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

                  final BitSet follow = BitSet.of(CSSLexer.RCURLY, CSSLexer.SEMICOLON);               
                  retval.tree = invalidFallback(CSSLexer.INVALID_DIRECTIVE, "INVALID_DIRECTIVE", follow, LexerState.RecoveryMode.RULE, null, re);
              
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:858:1: property : ( MINUS )? IDENT ( S )* -> ( MINUS )? IDENT ;
    public final CSSParser.property_return property() throws RecognitionException {
        CSSParser.property_return retval = new CSSParser.property_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MINUS95=null;
        Token IDENT96=null;
        Token S97=null;

        Object MINUS95_tree=null;
        Object IDENT96_tree=null;
        Object S97_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:859:2: ( ( MINUS )? IDENT ( S )* -> ( MINUS )? IDENT )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:859:4: ( MINUS )? IDENT ( S )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:859:4: ( MINUS )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==MINUS) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:859:4: MINUS
                    {
                    MINUS95=(Token)match(input,MINUS,FOLLOW_MINUS_in_property882);  
                    stream_MINUS.add(MINUS95);


                    }
                    break;

            }

            IDENT96=(Token)match(input,IDENT,FOLLOW_IDENT_in_property885);  
            stream_IDENT.add(IDENT96);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:859:17: ( S )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==S) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:859:17: S
            	    {
            	    S97=(Token)match(input,S,FOLLOW_S_in_property887);  
            	    stream_S.add(S97);


            	    }
            	    break;

            	default :
            	    break loop50;
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
            // 859:20: -> ( MINUS )? IDENT
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:859:23: ( MINUS )?
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:862:1: terms : ( term )+ -> ^( VALUE ( term )+ ) ;
    public final CSSParser.terms_return terms() throws RecognitionException {
        CSSParser.terms_return retval = new CSSParser.terms_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CSSParser.term_return term98 = null;


        RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:863:2: ( ( term )+ -> ^( VALUE ( term )+ ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:863:4: ( term )+
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:863:4: ( term )+
            int cnt51=0;
            loop51:
            do {
                int alt51=2;
                alt51 = dfa51.predict(input);
                switch (alt51) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:863:4: term
            	    {
            	    pushFollow(FOLLOW_term_in_terms915);
            	    term98=term();

            	    state._fsp--;

            	    stream_term.add(term98.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt51 >= 1 ) break loop51;
                        EarlyExitException eee =
                            new EarlyExitException(51, input);
                        throw eee;
                }
                cnt51++;
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
            // 864:2: -> ^( VALUE ( term )+ )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:864:5: ^( VALUE ( term )+ )
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
                    retval.tree = invalidFallbackGreedy(CSSLexer.INVALID_STATEMENT, "INVALID_STATEMENT", follow, LexerState.RecoveryMode.FUNCTION, null, re);
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:880:1: term : ( valuepart -> valuepart | LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY -> CURLYBLOCK | ATKEYWORD ( S )* -> ATKEYWORD );
    public final CSSParser.term_return term() throws RecognitionException {
        CSSParser.term_return retval = new CSSParser.term_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LCURLY100=null;
        Token S101=null;
        Token SEMICOLON103=null;
        Token S104=null;
        Token RCURLY105=null;
        Token ATKEYWORD106=null;
        Token S107=null;
        CSSParser.valuepart_return valuepart99 = null;

        CSSParser.any_return any102 = null;


        Object LCURLY100_tree=null;
        Object S101_tree=null;
        Object SEMICOLON103_tree=null;
        Object S104_tree=null;
        Object RCURLY105_tree=null;
        Object ATKEYWORD106_tree=null;
        Object S107_tree=null;
        RewriteRuleTokenStream stream_ATKEYWORD=new RewriteRuleTokenStream(adaptor,"token ATKEYWORD");
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_valuepart=new RewriteRuleSubtreeStream(adaptor,"rule valuepart");
        RewriteRuleSubtreeStream stream_any=new RewriteRuleSubtreeStream(adaptor,"rule any");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:881:5: ( valuepart -> valuepart | LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY -> CURLYBLOCK | ATKEYWORD ( S )* -> ATKEYWORD )
            int alt56=3;
            alt56 = dfa56.predict(input);
            switch (alt56) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:881:7: valuepart
                    {
                    pushFollow(FOLLOW_valuepart_in_term948);
                    valuepart99=valuepart();

                    state._fsp--;

                    stream_valuepart.add(valuepart99.getTree());


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
                    // 881:17: -> valuepart
                    {
                        adaptor.addChild(root_0, stream_valuepart.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:882:7: LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY
                    {
                    LCURLY100=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_term960);  
                    stream_LCURLY.add(LCURLY100);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:882:14: ( S )*
                    loop52:
                    do {
                        int alt52=2;
                        alt52 = dfa52.predict(input);
                        switch (alt52) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:882:14: S
                    	    {
                    	    S101=(Token)match(input,S,FOLLOW_S_in_term962);  
                    	    stream_S.add(S101);


                    	    }
                    	    break;

                    	default :
                    	    break loop52;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:882:17: ( any | SEMICOLON ( S )* )*
                    loop54:
                    do {
                        int alt54=3;
                        alt54 = dfa54.predict(input);
                        switch (alt54) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:882:18: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_term966);
                    	    any102=any();

                    	    state._fsp--;

                    	    stream_any.add(any102.getTree());

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:882:24: SEMICOLON ( S )*
                    	    {
                    	    SEMICOLON103=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_term970);  
                    	    stream_SEMICOLON.add(SEMICOLON103);

                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:882:34: ( S )*
                    	    loop53:
                    	    do {
                    	        int alt53=2;
                    	        alt53 = dfa53.predict(input);
                    	        switch (alt53) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:882:34: S
                    	    	    {
                    	    	    S104=(Token)match(input,S,FOLLOW_S_in_term972);  
                    	    	    stream_S.add(S104);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop53;
                    	        }
                    	    } while (true);


                    	    }
                    	    break;

                    	default :
                    	    break loop54;
                        }
                    } while (true);

                    RCURLY105=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_term977);  
                    stream_RCURLY.add(RCURLY105);



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
                    // 882:46: -> CURLYBLOCK
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(CURLYBLOCK, "CURLYBLOCK"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:7: ATKEYWORD ( S )*
                    {
                    ATKEYWORD106=(Token)match(input,ATKEYWORD,FOLLOW_ATKEYWORD_in_term989);  
                    stream_ATKEYWORD.add(ATKEYWORD106);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:17: ( S )*
                    loop55:
                    do {
                        int alt55=2;
                        alt55 = dfa55.predict(input);
                        switch (alt55) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:17: S
                    	    {
                    	    S107=(Token)match(input,S,FOLLOW_S_in_term991);  
                    	    stream_S.add(S107);


                    	    }
                    	    break;

                    	default :
                    	    break loop55;
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
                    // 883:20: -> ATKEYWORD
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:886:1: funct : ( EXPRESSION -> EXPRESSION | FUNCTION ( S )* ( terms )? RPAREN -> ^( FUNCTION ( terms )? ) );
    public final CSSParser.funct_return funct() throws RecognitionException {
        CSSParser.funct_return retval = new CSSParser.funct_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EXPRESSION108=null;
        Token FUNCTION109=null;
        Token S110=null;
        Token RPAREN112=null;
        CSSParser.terms_return terms111 = null;


        Object EXPRESSION108_tree=null;
        Object FUNCTION109_tree=null;
        Object S110_tree=null;
        Object RPAREN112_tree=null;
        RewriteRuleTokenStream stream_FUNCTION=new RewriteRuleTokenStream(adaptor,"token FUNCTION");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_EXPRESSION=new RewriteRuleTokenStream(adaptor,"token EXPRESSION");
        RewriteRuleSubtreeStream stream_terms=new RewriteRuleSubtreeStream(adaptor,"rule terms");

        	functLevel++;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:894:3: ( EXPRESSION -> EXPRESSION | FUNCTION ( S )* ( terms )? RPAREN -> ^( FUNCTION ( terms )? ) )
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==EXPRESSION) ) {
                alt59=1;
            }
            else if ( (LA59_0==FUNCTION) ) {
                alt59=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;
            }
            switch (alt59) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:894:5: EXPRESSION
                    {
                    EXPRESSION108=(Token)match(input,EXPRESSION,FOLLOW_EXPRESSION_in_funct1024);  
                    stream_EXPRESSION.add(EXPRESSION108);



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
                    // 894:16: -> EXPRESSION
                    {
                        adaptor.addChild(root_0, stream_EXPRESSION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:895:4: FUNCTION ( S )* ( terms )? RPAREN
                    {
                    FUNCTION109=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_funct1033);  
                    stream_FUNCTION.add(FUNCTION109);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:895:13: ( S )*
                    loop57:
                    do {
                        int alt57=2;
                        alt57 = dfa57.predict(input);
                        switch (alt57) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:895:13: S
                    	    {
                    	    S110=(Token)match(input,S,FOLLOW_S_in_funct1035);  
                    	    stream_S.add(S110);


                    	    }
                    	    break;

                    	default :
                    	    break loop57;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:895:16: ( terms )?
                    int alt58=2;
                    alt58 = dfa58.predict(input);
                    switch (alt58) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:895:16: terms
                            {
                            pushFollow(FOLLOW_terms_in_funct1038);
                            terms111=terms();

                            state._fsp--;

                            stream_terms.add(terms111.getTree());

                            }
                            break;

                    }

                    RPAREN112=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_funct1041);  
                    stream_RPAREN.add(RPAREN112);



                    // AST REWRITE
                    // elements: terms, FUNCTION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 895:30: -> ^( FUNCTION ( terms )? )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:895:33: ^( FUNCTION ( terms )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_FUNCTION.nextNode(), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:895:44: ( terms )?
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:898:1: valuepart : ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | ( MINUS )? funct -> ( MINUS )? funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) ) ( S )* ;
    public final CSSParser.valuepart_return valuepart() throws RecognitionException {
        CSSParser.valuepart_return retval = new CSSParser.valuepart_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MINUS113=null;
        Token IDENT114=null;
        Token CLASSKEYWORD115=null;
        Token MINUS116=null;
        Token NUMBER117=null;
        Token MINUS118=null;
        Token PERCENTAGE119=null;
        Token MINUS120=null;
        Token DIMENSION121=null;
        Token URI123=null;
        Token HASH124=null;
        Token UNIRANGE125=null;
        Token INCLUDES126=null;
        Token COLON127=null;
        Token COMMA128=null;
        Token GREATER129=null;
        Token LESS130=null;
        Token QUESTION131=null;
        Token PERCENT132=null;
        Token EQUALS133=null;
        Token SLASH134=null;
        Token PLUS135=null;
        Token ASTERISK136=null;
        Token MINUS137=null;
        Token DASHMATCH139=null;
        Token LPAREN140=null;
        Token RPAREN142=null;
        Token LBRACE143=null;
        Token RBRACE145=null;
        Token S146=null;
        CSSParser.string_return string122 = null;

        CSSParser.funct_return funct138 = null;

        CSSParser.valuepart_return valuepart141 = null;

        CSSParser.valuepart_return valuepart144 = null;


        Object MINUS113_tree=null;
        Object IDENT114_tree=null;
        Object CLASSKEYWORD115_tree=null;
        Object MINUS116_tree=null;
        Object NUMBER117_tree=null;
        Object MINUS118_tree=null;
        Object PERCENTAGE119_tree=null;
        Object MINUS120_tree=null;
        Object DIMENSION121_tree=null;
        Object URI123_tree=null;
        Object HASH124_tree=null;
        Object UNIRANGE125_tree=null;
        Object INCLUDES126_tree=null;
        Object COLON127_tree=null;
        Object COMMA128_tree=null;
        Object GREATER129_tree=null;
        Object LESS130_tree=null;
        Object QUESTION131_tree=null;
        Object PERCENT132_tree=null;
        Object EQUALS133_tree=null;
        Object SLASH134_tree=null;
        Object PLUS135_tree=null;
        Object ASTERISK136_tree=null;
        Object MINUS137_tree=null;
        Object DASHMATCH139_tree=null;
        Object LPAREN140_tree=null;
        Object RPAREN142_tree=null;
        Object LBRACE143_tree=null;
        Object RBRACE145_tree=null;
        Object S146_tree=null;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:900:5: ( ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | ( MINUS )? funct -> ( MINUS )? funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) ) ( S )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:900:7: ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | ( MINUS )? funct -> ( MINUS )? funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) ) ( S )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:900:7: ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | ( MINUS )? funct -> ( MINUS )? funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) )
            int alt67=24;
            alt67 = dfa67.predict(input);
            switch (alt67) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:900:9: ( MINUS )? IDENT
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:900:9: ( MINUS )?
                    int alt60=2;
                    int LA60_0 = input.LA(1);

                    if ( (LA60_0==MINUS) ) {
                        alt60=1;
                    }
                    switch (alt60) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:900:9: MINUS
                            {
                            MINUS113=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart1068);  
                            stream_MINUS.add(MINUS113);


                            }
                            break;

                    }

                    IDENT114=(Token)match(input,IDENT,FOLLOW_IDENT_in_valuepart1071);  
                    stream_IDENT.add(IDENT114);



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
                    // 900:22: -> ( MINUS )? IDENT
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:900:25: ( MINUS )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:901:9: CLASSKEYWORD
                    {
                    CLASSKEYWORD115=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_valuepart1088);  
                    stream_CLASSKEYWORD.add(CLASSKEYWORD115);



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
                    // 901:22: -> CLASSKEYWORD
                    {
                        adaptor.addChild(root_0, stream_CLASSKEYWORD.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:902:9: ( MINUS )? NUMBER
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:902:9: ( MINUS )?
                    int alt61=2;
                    int LA61_0 = input.LA(1);

                    if ( (LA61_0==MINUS) ) {
                        alt61=1;
                    }
                    switch (alt61) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:902:9: MINUS
                            {
                            MINUS116=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart1102);  
                            stream_MINUS.add(MINUS116);


                            }
                            break;

                    }

                    NUMBER117=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_valuepart1105);  
                    stream_NUMBER.add(NUMBER117);



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
                    // 902:23: -> ( MINUS )? NUMBER
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:902:26: ( MINUS )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:903:9: ( MINUS )? PERCENTAGE
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:903:9: ( MINUS )?
                    int alt62=2;
                    int LA62_0 = input.LA(1);

                    if ( (LA62_0==MINUS) ) {
                        alt62=1;
                    }
                    switch (alt62) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:903:9: MINUS
                            {
                            MINUS118=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart1122);  
                            stream_MINUS.add(MINUS118);


                            }
                            break;

                    }

                    PERCENTAGE119=(Token)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_valuepart1125);  
                    stream_PERCENTAGE.add(PERCENTAGE119);



                    // AST REWRITE
                    // elements: MINUS, PERCENTAGE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 903:27: -> ( MINUS )? PERCENTAGE
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:903:30: ( MINUS )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:904:9: ( MINUS )? DIMENSION
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:904:9: ( MINUS )?
                    int alt63=2;
                    int LA63_0 = input.LA(1);

                    if ( (LA63_0==MINUS) ) {
                        alt63=1;
                    }
                    switch (alt63) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:904:9: MINUS
                            {
                            MINUS120=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart1142);  
                            stream_MINUS.add(MINUS120);


                            }
                            break;

                    }

                    DIMENSION121=(Token)match(input,DIMENSION,FOLLOW_DIMENSION_in_valuepart1145);  
                    stream_DIMENSION.add(DIMENSION121);



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
                    // 904:26: -> ( MINUS )? DIMENSION
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:904:29: ( MINUS )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:905:9: string
                    {
                    pushFollow(FOLLOW_string_in_valuepart1162);
                    string122=string();

                    state._fsp--;

                    stream_string.add(string122.getTree());


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
                    // 905:16: -> string
                    {
                        adaptor.addChild(root_0, stream_string.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:906:9: URI
                    {
                    URI123=(Token)match(input,URI,FOLLOW_URI_in_valuepart1176);  
                    stream_URI.add(URI123);



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
                    // 906:16: -> URI
                    {
                        adaptor.addChild(root_0, stream_URI.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:907:9: HASH
                    {
                    HASH124=(Token)match(input,HASH,FOLLOW_HASH_in_valuepart1193);  
                    stream_HASH.add(HASH124);



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
                    // 907:14: -> HASH
                    {
                        adaptor.addChild(root_0, stream_HASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:908:9: UNIRANGE
                    {
                    UNIRANGE125=(Token)match(input,UNIRANGE,FOLLOW_UNIRANGE_in_valuepart1207);  
                    stream_UNIRANGE.add(UNIRANGE125);



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
                    // 908:18: -> UNIRANGE
                    {
                        adaptor.addChild(root_0, stream_UNIRANGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:909:9: INCLUDES
                    {
                    INCLUDES126=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_valuepart1221);  
                    stream_INCLUDES.add(INCLUDES126);



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
                    // 909:18: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:910:9: COLON
                    {
                    COLON127=(Token)match(input,COLON,FOLLOW_COLON_in_valuepart1235);  
                    stream_COLON.add(COLON127);



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
                    // 910:15: -> COLON
                    {
                        adaptor.addChild(root_0, stream_COLON.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:911:9: COMMA
                    {
                    COMMA128=(Token)match(input,COMMA,FOLLOW_COMMA_in_valuepart1249);  
                    stream_COMMA.add(COMMA128);



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
                    // 911:15: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:912:9: GREATER
                    {
                    GREATER129=(Token)match(input,GREATER,FOLLOW_GREATER_in_valuepart1263);  
                    stream_GREATER.add(GREATER129);



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
                    // 912:17: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:913:9: LESS
                    {
                    LESS130=(Token)match(input,LESS,FOLLOW_LESS_in_valuepart1277);  
                    stream_LESS.add(LESS130);



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
                    // 913:14: -> LESS
                    {
                        adaptor.addChild(root_0, stream_LESS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:914:9: QUESTION
                    {
                    QUESTION131=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_valuepart1291);  
                    stream_QUESTION.add(QUESTION131);



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
                    // 914:18: -> QUESTION
                    {
                        adaptor.addChild(root_0, stream_QUESTION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:915:9: PERCENT
                    {
                    PERCENT132=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_valuepart1305);  
                    stream_PERCENT.add(PERCENT132);



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
                    // 915:17: -> PERCENT
                    {
                        adaptor.addChild(root_0, stream_PERCENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:916:9: EQUALS
                    {
                    EQUALS133=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_valuepart1319);  
                    stream_EQUALS.add(EQUALS133);



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
                    // 916:16: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 18 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:917:9: SLASH
                    {
                    SLASH134=(Token)match(input,SLASH,FOLLOW_SLASH_in_valuepart1333);  
                    stream_SLASH.add(SLASH134);



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
                    // 917:15: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 19 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:918:8: PLUS
                    {
                    PLUS135=(Token)match(input,PLUS,FOLLOW_PLUS_in_valuepart1346);  
                    stream_PLUS.add(PLUS135);



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
                    // 918:13: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 20 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:919:8: ASTERISK
                    {
                    ASTERISK136=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_valuepart1359);  
                    stream_ASTERISK.add(ASTERISK136);



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
                    // 919:17: -> ASTERISK
                    {
                        adaptor.addChild(root_0, stream_ASTERISK.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 21 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:920:9: ( MINUS )? funct
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:920:9: ( MINUS )?
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==MINUS) ) {
                        alt64=1;
                    }
                    switch (alt64) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:920:9: MINUS
                            {
                            MINUS137=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart1376);  
                            stream_MINUS.add(MINUS137);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_funct_in_valuepart1379);
                    funct138=funct();

                    state._fsp--;

                    stream_funct.add(funct138.getTree());


                    // AST REWRITE
                    // elements: funct, MINUS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 920:22: -> ( MINUS )? funct
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:920:25: ( MINUS )?
                        if ( stream_MINUS.hasNext() ) {
                            adaptor.addChild(root_0, stream_MINUS.nextNode());

                        }
                        stream_MINUS.reset();
                        adaptor.addChild(root_0, stream_funct.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 22 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:921:9: DASHMATCH
                    {
                    DASHMATCH139=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_valuepart1397);  
                    stream_DASHMATCH.add(DASHMATCH139);



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
                    // 921:19: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 23 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:922:9: LPAREN ( valuepart )* RPAREN
                    {
                    LPAREN140=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_valuepart1411);  
                    stream_LPAREN.add(LPAREN140);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:922:16: ( valuepart )*
                    loop65:
                    do {
                        int alt65=2;
                        alt65 = dfa65.predict(input);
                        switch (alt65) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:922:16: valuepart
                    	    {
                    	    pushFollow(FOLLOW_valuepart_in_valuepart1413);
                    	    valuepart141=valuepart();

                    	    state._fsp--;

                    	    stream_valuepart.add(valuepart141.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop65;
                        }
                    } while (true);

                    RPAREN142=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_valuepart1416);  
                    stream_RPAREN.add(RPAREN142);



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
                    // 922:34: -> ^( PARENBLOCK ( valuepart )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:922:37: ^( PARENBLOCK ( valuepart )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PARENBLOCK, "PARENBLOCK"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:922:50: ( valuepart )*
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:923:9: LBRACE ( valuepart )* RBRACE
                    {
                    LBRACE143=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_valuepart1435);  
                    stream_LBRACE.add(LBRACE143);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:923:16: ( valuepart )*
                    loop66:
                    do {
                        int alt66=2;
                        alt66 = dfa66.predict(input);
                        switch (alt66) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:923:16: valuepart
                    	    {
                    	    pushFollow(FOLLOW_valuepart_in_valuepart1437);
                    	    valuepart144=valuepart();

                    	    state._fsp--;

                    	    stream_valuepart.add(valuepart144.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop66;
                        }
                    } while (true);

                    RBRACE145=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_valuepart1440);  
                    stream_RBRACE.add(RBRACE145);



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
                    // 923:34: -> ^( BRACEBLOCK ( valuepart )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:923:37: ^( BRACEBLOCK ( valuepart )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BRACEBLOCK, "BRACEBLOCK"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:923:50: ( valuepart )*
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:924:8: ( S )*
            loop68:
            do {
                int alt68=2;
                alt68 = dfa68.predict(input);
                switch (alt68) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:924:8: S
            	    {
            	    S146=(Token)match(input,S,FOLLOW_S_in_valuepart1458);  
            	    stream_S.add(S146);


            	    }
            	    break;

            	default :
            	    break loop68;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:927:1: combined_selector : selector ( ( combinator ) selector )* ;
    public final CSSParser.combined_selector_return combined_selector() throws RecognitionException {
        CSSParser.combined_selector_return retval = new CSSParser.combined_selector_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CSSParser.selector_return selector147 = null;

        CSSParser.combinator_return combinator148 = null;

        CSSParser.selector_return selector149 = null;



        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:928:2: ( selector ( ( combinator ) selector )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:928:4: selector ( ( combinator ) selector )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_selector_in_combined_selector1475);
            selector147=selector();

            state._fsp--;

            adaptor.addChild(root_0, selector147.getTree());
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:928:13: ( ( combinator ) selector )*
            loop69:
            do {
                int alt69=2;
                int LA69_0 = input.LA(1);

                if ( (LA69_0==S||LA69_0==GREATER||LA69_0==PLUS) ) {
                    alt69=1;
                }


                switch (alt69) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:928:14: ( combinator ) selector
            	    {
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:928:14: ( combinator )
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:928:15: combinator
            	    {
            	    pushFollow(FOLLOW_combinator_in_combined_selector1479);
            	    combinator148=combinator();

            	    state._fsp--;

            	    adaptor.addChild(root_0, combinator148.getTree());

            	    }

            	    pushFollow(FOLLOW_selector_in_combined_selector1482);
            	    selector149=selector();

            	    state._fsp--;

            	    adaptor.addChild(root_0, selector149.getTree());

            	    }
            	    break;

            	default :
            	    break loop69;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:936:1: combinator : ( GREATER ( S )* -> CHILD | PLUS ( S )* -> ADJACENT | S -> DESCENDANT );
    public final CSSParser.combinator_return combinator() throws RecognitionException {
        CSSParser.combinator_return retval = new CSSParser.combinator_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token GREATER150=null;
        Token S151=null;
        Token PLUS152=null;
        Token S153=null;
        Token S154=null;

        Object GREATER150_tree=null;
        Object S151_tree=null;
        Object PLUS152_tree=null;
        Object S153_tree=null;
        Object S154_tree=null;
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:937:2: ( GREATER ( S )* -> CHILD | PLUS ( S )* -> ADJACENT | S -> DESCENDANT )
            int alt72=3;
            switch ( input.LA(1) ) {
            case GREATER:
                {
                alt72=1;
                }
                break;
            case PLUS:
                {
                alt72=2;
                }
                break;
            case S:
                {
                alt72=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;
            }

            switch (alt72) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:937:4: GREATER ( S )*
                    {
                    GREATER150=(Token)match(input,GREATER,FOLLOW_GREATER_in_combinator1502);  
                    stream_GREATER.add(GREATER150);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:937:12: ( S )*
                    loop70:
                    do {
                        int alt70=2;
                        int LA70_0 = input.LA(1);

                        if ( (LA70_0==S) ) {
                            alt70=1;
                        }


                        switch (alt70) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:937:12: S
                    	    {
                    	    S151=(Token)match(input,S,FOLLOW_S_in_combinator1504);  
                    	    stream_S.add(S151);


                    	    }
                    	    break;

                    	default :
                    	    break loop70;
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
                    // 937:15: -> CHILD
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(CHILD, "CHILD"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:938:4: PLUS ( S )*
                    {
                    PLUS152=(Token)match(input,PLUS,FOLLOW_PLUS_in_combinator1514);  
                    stream_PLUS.add(PLUS152);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:938:9: ( S )*
                    loop71:
                    do {
                        int alt71=2;
                        int LA71_0 = input.LA(1);

                        if ( (LA71_0==S) ) {
                            alt71=1;
                        }


                        switch (alt71) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:938:9: S
                    	    {
                    	    S153=(Token)match(input,S,FOLLOW_S_in_combinator1516);  
                    	    stream_S.add(S153);


                    	    }
                    	    break;

                    	default :
                    	    break loop71;
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
                    // 938:12: -> ADJACENT
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(ADJACENT, "ADJACENT"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:939:4: S
                    {
                    S154=(Token)match(input,S,FOLLOW_S_in_combinator1526);  
                    stream_S.add(S154);



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
                    // 939:6: -> DESCENDANT
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:942:1: selector : ( ( IDENT | ASTERISK ) ( selpart )* ( S )* -> ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* ) | ( selpart )+ ( S )* -> ^( SELECTOR ( selpart )+ ) );
    public final CSSParser.selector_return selector() throws RecognitionException {
        CSSParser.selector_return retval = new CSSParser.selector_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT155=null;
        Token ASTERISK156=null;
        Token S158=null;
        Token S160=null;
        CSSParser.selpart_return selpart157 = null;

        CSSParser.selpart_return selpart159 = null;


        Object IDENT155_tree=null;
        Object ASTERISK156_tree=null;
        Object S158_tree=null;
        Object S160_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_ASTERISK=new RewriteRuleTokenStream(adaptor,"token ASTERISK");
        RewriteRuleSubtreeStream stream_selpart=new RewriteRuleSubtreeStream(adaptor,"rule selpart");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:943:5: ( ( IDENT | ASTERISK ) ( selpart )* ( S )* -> ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* ) | ( selpart )+ ( S )* -> ^( SELECTOR ( selpart )+ ) )
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==IDENT||LA78_0==ASTERISK) ) {
                alt78=1;
            }
            else if ( (LA78_0==INVALID_SELPART||LA78_0==COLON||LA78_0==CLASSKEYWORD||LA78_0==HASH||LA78_0==LBRACE) ) {
                alt78=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 78, 0, input);

                throw nvae;
            }
            switch (alt78) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:943:7: ( IDENT | ASTERISK ) ( selpart )* ( S )*
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:943:7: ( IDENT | ASTERISK )
                    int alt73=2;
                    int LA73_0 = input.LA(1);

                    if ( (LA73_0==IDENT) ) {
                        alt73=1;
                    }
                    else if ( (LA73_0==ASTERISK) ) {
                        alt73=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 73, 0, input);

                        throw nvae;
                    }
                    switch (alt73) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:943:8: IDENT
                            {
                            IDENT155=(Token)match(input,IDENT,FOLLOW_IDENT_in_selector1545);  
                            stream_IDENT.add(IDENT155);


                            }
                            break;
                        case 2 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:943:16: ASTERISK
                            {
                            ASTERISK156=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_selector1549);  
                            stream_ASTERISK.add(ASTERISK156);


                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:943:27: ( selpart )*
                    loop74:
                    do {
                        int alt74=2;
                        alt74 = dfa74.predict(input);
                        switch (alt74) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:943:27: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1553);
                    	    selpart157=selpart();

                    	    state._fsp--;

                    	    stream_selpart.add(selpart157.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop74;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:943:36: ( S )*
                    loop75:
                    do {
                        int alt75=2;
                        alt75 = dfa75.predict(input);
                        switch (alt75) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:943:36: S
                    	    {
                    	    S158=(Token)match(input,S,FOLLOW_S_in_selector1556);  
                    	    stream_S.add(S158);


                    	    }
                    	    break;

                    	default :
                    	    break loop75;
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
                    // 944:6: -> ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:944:9: ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SELECTOR, "SELECTOR"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:944:20: ^( ELEMENT ( IDENT )? )
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ELEMENT, "ELEMENT"), root_2);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:944:30: ( IDENT )?
                        if ( stream_IDENT.hasNext() ) {
                            adaptor.addChild(root_2, stream_IDENT.nextNode());

                        }
                        stream_IDENT.reset();

                        adaptor.addChild(root_1, root_2);
                        }
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:944:38: ( selpart )*
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:945:7: ( selpart )+ ( S )*
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:945:7: ( selpart )+
                    int cnt76=0;
                    loop76:
                    do {
                        int alt76=2;
                        alt76 = dfa76.predict(input);
                        switch (alt76) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:945:7: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1586);
                    	    selpart159=selpart();

                    	    state._fsp--;

                    	    stream_selpart.add(selpart159.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt76 >= 1 ) break loop76;
                                EarlyExitException eee =
                                    new EarlyExitException(76, input);
                                throw eee;
                        }
                        cnt76++;
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:945:16: ( S )*
                    loop77:
                    do {
                        int alt77=2;
                        alt77 = dfa77.predict(input);
                        switch (alt77) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:945:16: S
                    	    {
                    	    S160=(Token)match(input,S,FOLLOW_S_in_selector1589);  
                    	    stream_S.add(S160);


                    	    }
                    	    break;

                    	default :
                    	    break loop77;
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
                    // 946:9: -> ^( SELECTOR ( selpart )+ )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:946:12: ^( SELECTOR ( selpart )+ )
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:952:1: selpart : ( HASH | CLASSKEYWORD | LBRACE ( S )* attribute RBRACE -> ^( ATTRIBUTE attribute ) | pseudo | INVALID_SELPART );
    public final CSSParser.selpart_return selpart() throws RecognitionException {
        CSSParser.selpart_return retval = new CSSParser.selpart_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token HASH161=null;
        Token CLASSKEYWORD162=null;
        Token LBRACE163=null;
        Token S164=null;
        Token RBRACE166=null;
        Token INVALID_SELPART168=null;
        CSSParser.attribute_return attribute165 = null;

        CSSParser.pseudo_return pseudo167 = null;


        Object HASH161_tree=null;
        Object CLASSKEYWORD162_tree=null;
        Object LBRACE163_tree=null;
        Object S164_tree=null;
        Object RBRACE166_tree=null;
        Object INVALID_SELPART168_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleSubtreeStream stream_attribute=new RewriteRuleSubtreeStream(adaptor,"rule attribute");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:953:5: ( HASH | CLASSKEYWORD | LBRACE ( S )* attribute RBRACE -> ^( ATTRIBUTE attribute ) | pseudo | INVALID_SELPART )
            int alt80=5;
            switch ( input.LA(1) ) {
            case HASH:
                {
                alt80=1;
                }
                break;
            case CLASSKEYWORD:
                {
                alt80=2;
                }
                break;
            case LBRACE:
                {
                alt80=3;
                }
                break;
            case COLON:
                {
                alt80=4;
                }
                break;
            case INVALID_SELPART:
                {
                alt80=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 80, 0, input);

                throw nvae;
            }

            switch (alt80) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:953:8: HASH
                    {
                    root_0 = (Object)adaptor.nil();

                    HASH161=(Token)match(input,HASH,FOLLOW_HASH_in_selpart1636); 
                    HASH161_tree = (Object)adaptor.create(HASH161);
                    adaptor.addChild(root_0, HASH161_tree);


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:954:7: CLASSKEYWORD
                    {
                    root_0 = (Object)adaptor.nil();

                    CLASSKEYWORD162=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_selpart1644); 
                    CLASSKEYWORD162_tree = (Object)adaptor.create(CLASSKEYWORD162);
                    adaptor.addChild(root_0, CLASSKEYWORD162_tree);


                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:955:6: LBRACE ( S )* attribute RBRACE
                    {
                    LBRACE163=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_selpart1651);  
                    stream_LBRACE.add(LBRACE163);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:955:13: ( S )*
                    loop79:
                    do {
                        int alt79=2;
                        int LA79_0 = input.LA(1);

                        if ( (LA79_0==S) ) {
                            alt79=1;
                        }


                        switch (alt79) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:955:13: S
                    	    {
                    	    S164=(Token)match(input,S,FOLLOW_S_in_selpart1653);  
                    	    stream_S.add(S164);


                    	    }
                    	    break;

                    	default :
                    	    break loop79;
                        }
                    } while (true);

                    pushFollow(FOLLOW_attribute_in_selpart1656);
                    attribute165=attribute();

                    state._fsp--;

                    stream_attribute.add(attribute165.getTree());
                    RBRACE166=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_selpart1658);  
                    stream_RBRACE.add(RBRACE166);



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
                    // 955:33: -> ^( ATTRIBUTE attribute )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:955:36: ^( ATTRIBUTE attribute )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:956:7: pseudo
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pseudo_in_selpart1674);
                    pseudo167=pseudo();

                    state._fsp--;

                    adaptor.addChild(root_0, pseudo167.getTree());

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:957:7: INVALID_SELPART
                    {
                    root_0 = (Object)adaptor.nil();

                    INVALID_SELPART168=(Token)match(input,INVALID_SELPART,FOLLOW_INVALID_SELPART_in_selpart1682); 
                    INVALID_SELPART168_tree = (Object)adaptor.create(INVALID_SELPART168);
                    adaptor.addChild(root_0, INVALID_SELPART168_tree);


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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:963:1: attribute : IDENT ( S )* ( ( EQUALS | INCLUDES | DASHMATCH | STARTSWITH | ENDSWITH | CONTAINS ) ( S )* ( IDENT | string ) ( S )* )? ;
    public final CSSParser.attribute_return attribute() throws RecognitionException {
        CSSParser.attribute_return retval = new CSSParser.attribute_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT169=null;
        Token S170=null;
        Token set171=null;
        Token S172=null;
        Token IDENT173=null;
        Token S175=null;
        CSSParser.string_return string174 = null;


        Object IDENT169_tree=null;
        Object S170_tree=null;
        Object set171_tree=null;
        Object S172_tree=null;
        Object IDENT173_tree=null;
        Object S175_tree=null;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:964:2: ( IDENT ( S )* ( ( EQUALS | INCLUDES | DASHMATCH | STARTSWITH | ENDSWITH | CONTAINS ) ( S )* ( IDENT | string ) ( S )* )? )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:964:4: IDENT ( S )* ( ( EQUALS | INCLUDES | DASHMATCH | STARTSWITH | ENDSWITH | CONTAINS ) ( S )* ( IDENT | string ) ( S )* )?
            {
            root_0 = (Object)adaptor.nil();

            IDENT169=(Token)match(input,IDENT,FOLLOW_IDENT_in_attribute1706); 
            IDENT169_tree = (Object)adaptor.create(IDENT169);
            adaptor.addChild(root_0, IDENT169_tree);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:964:10: ( S )*
            loop81:
            do {
                int alt81=2;
                int LA81_0 = input.LA(1);

                if ( (LA81_0==S) ) {
                    alt81=1;
                }


                switch (alt81) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:964:10: S
            	    {
            	    S170=(Token)match(input,S,FOLLOW_S_in_attribute1708); 
            	    S170_tree = (Object)adaptor.create(S170);
            	    adaptor.addChild(root_0, S170_tree);


            	    }
            	    break;

            	default :
            	    break loop81;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:965:4: ( ( EQUALS | INCLUDES | DASHMATCH | STARTSWITH | ENDSWITH | CONTAINS ) ( S )* ( IDENT | string ) ( S )* )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==INCLUDES||LA85_0==EQUALS||LA85_0==DASHMATCH||(LA85_0>=STARTSWITH && LA85_0<=CONTAINS)) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:965:5: ( EQUALS | INCLUDES | DASHMATCH | STARTSWITH | ENDSWITH | CONTAINS ) ( S )* ( IDENT | string ) ( S )*
                    {
                    set171=(Token)input.LT(1);
                    if ( input.LA(1)==INCLUDES||input.LA(1)==EQUALS||input.LA(1)==DASHMATCH||(input.LA(1)>=STARTSWITH && input.LA(1)<=CONTAINS) ) {
                        input.consume();
                        adaptor.addChild(root_0, (Object)adaptor.create(set171));
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:965:72: ( S )*
                    loop82:
                    do {
                        int alt82=2;
                        int LA82_0 = input.LA(1);

                        if ( (LA82_0==S) ) {
                            alt82=1;
                        }


                        switch (alt82) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:965:72: S
                    	    {
                    	    S172=(Token)match(input,S,FOLLOW_S_in_attribute1739); 
                    	    S172_tree = (Object)adaptor.create(S172);
                    	    adaptor.addChild(root_0, S172_tree);


                    	    }
                    	    break;

                    	default :
                    	    break loop82;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:965:75: ( IDENT | string )
                    int alt83=2;
                    int LA83_0 = input.LA(1);

                    if ( (LA83_0==IDENT) ) {
                        alt83=1;
                    }
                    else if ( (LA83_0==INVALID_STRING||LA83_0==STRING) ) {
                        alt83=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 83, 0, input);

                        throw nvae;
                    }
                    switch (alt83) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:965:76: IDENT
                            {
                            IDENT173=(Token)match(input,IDENT,FOLLOW_IDENT_in_attribute1743); 
                            IDENT173_tree = (Object)adaptor.create(IDENT173);
                            adaptor.addChild(root_0, IDENT173_tree);


                            }
                            break;
                        case 2 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:965:84: string
                            {
                            pushFollow(FOLLOW_string_in_attribute1747);
                            string174=string();

                            state._fsp--;

                            adaptor.addChild(root_0, string174.getTree());

                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:965:92: ( S )*
                    loop84:
                    do {
                        int alt84=2;
                        int LA84_0 = input.LA(1);

                        if ( (LA84_0==S) ) {
                            alt84=1;
                        }


                        switch (alt84) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:965:92: S
                    	    {
                    	    S175=(Token)match(input,S,FOLLOW_S_in_attribute1750); 
                    	    S175_tree = (Object)adaptor.create(S175);
                    	    adaptor.addChild(root_0, S175_tree);


                    	    }
                    	    break;

                    	default :
                    	    break loop84;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:968:1: pseudo : pseudocolon ( IDENT | FUNCTION ( IDENT | NUMBER ) RPAREN ) ;
    public final CSSParser.pseudo_return pseudo() throws RecognitionException {
        CSSParser.pseudo_return retval = new CSSParser.pseudo_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT177=null;
        Token FUNCTION178=null;
        Token set179=null;
        Token RPAREN180=null;
        CSSParser.pseudocolon_return pseudocolon176 = null;


        Object IDENT177_tree=null;
        Object FUNCTION178_tree=null;
        Object set179_tree=null;
        Object RPAREN180_tree=null;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:969:2: ( pseudocolon ( IDENT | FUNCTION ( IDENT | NUMBER ) RPAREN ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:969:4: pseudocolon ( IDENT | FUNCTION ( IDENT | NUMBER ) RPAREN )
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_pseudocolon_in_pseudo1764);
            pseudocolon176=pseudocolon();

            state._fsp--;

            root_0 = (Object)adaptor.becomeRoot(pseudocolon176.getTree(), root_0);
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:969:17: ( IDENT | FUNCTION ( IDENT | NUMBER ) RPAREN )
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==IDENT) ) {
                alt86=1;
            }
            else if ( (LA86_0==FUNCTION) ) {
                alt86=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;
            }
            switch (alt86) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:969:18: IDENT
                    {
                    IDENT177=(Token)match(input,IDENT,FOLLOW_IDENT_in_pseudo1768); 
                    IDENT177_tree = (Object)adaptor.create(IDENT177);
                    adaptor.addChild(root_0, IDENT177_tree);


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:969:26: FUNCTION ( IDENT | NUMBER ) RPAREN
                    {
                    FUNCTION178=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_pseudo1772); 
                    FUNCTION178_tree = (Object)adaptor.create(FUNCTION178);
                    adaptor.addChild(root_0, FUNCTION178_tree);

                    set179=(Token)input.LT(1);
                    if ( input.LA(1)==IDENT||input.LA(1)==NUMBER ) {
                        input.consume();
                        adaptor.addChild(root_0, (Object)adaptor.create(set179));
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    RPAREN180=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_pseudo1782); 

                    }
                    break;

            }


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
    // $ANTLR end "pseudo"

    public static class pseudocolon_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pseudocolon"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:975:1: pseudocolon : COLON ( COLON )? -> PSEUDO ;
    public final CSSParser.pseudocolon_return pseudocolon() throws RecognitionException {
        CSSParser.pseudocolon_return retval = new CSSParser.pseudocolon_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON181=null;
        Token COLON182=null;

        Object COLON181_tree=null;
        Object COLON182_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:976:2: ( COLON ( COLON )? -> PSEUDO )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:976:4: COLON ( COLON )?
            {
            COLON181=(Token)match(input,COLON,FOLLOW_COLON_in_pseudocolon1803);  
            stream_COLON.add(COLON181);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:976:10: ( COLON )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==COLON) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:976:10: COLON
                    {
                    COLON182=(Token)match(input,COLON,FOLLOW_COLON_in_pseudocolon1805);  
                    stream_COLON.add(COLON182);


                    }
                    break;

            }



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
            // 976:17: -> PSEUDO
            {
                adaptor.addChild(root_0, (Object)adaptor.create(PSEUDO, "PSEUDO"));

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
    // $ANTLR end "pseudocolon"

    public static class string_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "string"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:979:1: string : ( STRING | INVALID_STRING );
    public final CSSParser.string_return string() throws RecognitionException {
        CSSParser.string_return retval = new CSSParser.string_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set183=null;

        Object set183_tree=null;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:980:2: ( STRING | INVALID_STRING )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:
            {
            root_0 = (Object)adaptor.nil();

            set183=(Token)input.LT(1);
            if ( input.LA(1)==INVALID_STRING||input.LA(1)==STRING ) {
                input.consume();
                adaptor.addChild(root_0, (Object)adaptor.create(set183));
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:985:1: any : ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) ) ( S )* ;
    public final CSSParser.any_return any() throws RecognitionException {
        CSSParser.any_return retval = new CSSParser.any_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT184=null;
        Token CLASSKEYWORD185=null;
        Token NUMBER186=null;
        Token PERCENTAGE187=null;
        Token DIMENSION188=null;
        Token URI190=null;
        Token HASH191=null;
        Token UNIRANGE192=null;
        Token INCLUDES193=null;
        Token COLON194=null;
        Token COMMA195=null;
        Token GREATER196=null;
        Token LESS197=null;
        Token QUESTION198=null;
        Token PERCENT199=null;
        Token EQUALS200=null;
        Token SLASH201=null;
        Token EXCLAMATION202=null;
        Token MINUS203=null;
        Token PLUS204=null;
        Token ASTERISK205=null;
        Token FUNCTION206=null;
        Token S207=null;
        Token RPAREN209=null;
        Token DASHMATCH210=null;
        Token LPAREN211=null;
        Token RPAREN213=null;
        Token LBRACE214=null;
        Token RBRACE216=null;
        Token S217=null;
        CSSParser.string_return string189 = null;

        CSSParser.any_return any208 = null;

        CSSParser.any_return any212 = null;

        CSSParser.any_return any215 = null;


        Object IDENT184_tree=null;
        Object CLASSKEYWORD185_tree=null;
        Object NUMBER186_tree=null;
        Object PERCENTAGE187_tree=null;
        Object DIMENSION188_tree=null;
        Object URI190_tree=null;
        Object HASH191_tree=null;
        Object UNIRANGE192_tree=null;
        Object INCLUDES193_tree=null;
        Object COLON194_tree=null;
        Object COMMA195_tree=null;
        Object GREATER196_tree=null;
        Object LESS197_tree=null;
        Object QUESTION198_tree=null;
        Object PERCENT199_tree=null;
        Object EQUALS200_tree=null;
        Object SLASH201_tree=null;
        Object EXCLAMATION202_tree=null;
        Object MINUS203_tree=null;
        Object PLUS204_tree=null;
        Object ASTERISK205_tree=null;
        Object FUNCTION206_tree=null;
        Object S207_tree=null;
        Object RPAREN209_tree=null;
        Object DASHMATCH210_tree=null;
        Object LPAREN211_tree=null;
        Object RPAREN213_tree=null;
        Object LBRACE214_tree=null;
        Object RBRACE216_tree=null;
        Object S217_tree=null;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:986:2: ( ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) ) ( S )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:986:4: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) ) ( S )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:986:4: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) )
            int alt92=26;
            alt92 = dfa92.predict(input);
            switch (alt92) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:986:6: IDENT
                    {
                    IDENT184=(Token)match(input,IDENT,FOLLOW_IDENT_in_any1842);  
                    stream_IDENT.add(IDENT184);



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
                    // 986:12: -> IDENT
                    {
                        adaptor.addChild(root_0, stream_IDENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:987:6: CLASSKEYWORD
                    {
                    CLASSKEYWORD185=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_any1853);  
                    stream_CLASSKEYWORD.add(CLASSKEYWORD185);



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
                    // 987:19: -> CLASSKEYWORD
                    {
                        adaptor.addChild(root_0, stream_CLASSKEYWORD.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:988:6: NUMBER
                    {
                    NUMBER186=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_any1864);  
                    stream_NUMBER.add(NUMBER186);



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
                    // 988:13: -> NUMBER
                    {
                        adaptor.addChild(root_0, stream_NUMBER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:989:6: PERCENTAGE
                    {
                    PERCENTAGE187=(Token)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_any1875);  
                    stream_PERCENTAGE.add(PERCENTAGE187);



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
                    // 989:17: -> PERCENTAGE
                    {
                        adaptor.addChild(root_0, stream_PERCENTAGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:990:6: DIMENSION
                    {
                    DIMENSION188=(Token)match(input,DIMENSION,FOLLOW_DIMENSION_in_any1885);  
                    stream_DIMENSION.add(DIMENSION188);



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
                    // 990:16: -> DIMENSION
                    {
                        adaptor.addChild(root_0, stream_DIMENSION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:991:6: string
                    {
                    pushFollow(FOLLOW_string_in_any1896);
                    string189=string();

                    state._fsp--;

                    stream_string.add(string189.getTree());


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
                    // 991:13: -> string
                    {
                        adaptor.addChild(root_0, stream_string.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:992:9: URI
                    {
                    URI190=(Token)match(input,URI,FOLLOW_URI_in_any1910);  
                    stream_URI.add(URI190);



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
                    // 992:16: -> URI
                    {
                        adaptor.addChild(root_0, stream_URI.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:993:9: HASH
                    {
                    HASH191=(Token)match(input,HASH,FOLLOW_HASH_in_any1927);  
                    stream_HASH.add(HASH191);



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
                    // 993:14: -> HASH
                    {
                        adaptor.addChild(root_0, stream_HASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:994:9: UNIRANGE
                    {
                    UNIRANGE192=(Token)match(input,UNIRANGE,FOLLOW_UNIRANGE_in_any1941);  
                    stream_UNIRANGE.add(UNIRANGE192);



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
                    // 994:18: -> UNIRANGE
                    {
                        adaptor.addChild(root_0, stream_UNIRANGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:995:9: INCLUDES
                    {
                    INCLUDES193=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_any1955);  
                    stream_INCLUDES.add(INCLUDES193);



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
                    // 995:18: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:996:9: COLON
                    {
                    COLON194=(Token)match(input,COLON,FOLLOW_COLON_in_any1969);  
                    stream_COLON.add(COLON194);



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
                    // 996:15: -> COLON
                    {
                        adaptor.addChild(root_0, stream_COLON.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:997:9: COMMA
                    {
                    COMMA195=(Token)match(input,COMMA,FOLLOW_COMMA_in_any1983);  
                    stream_COMMA.add(COMMA195);



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
                    // 997:15: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:998:9: GREATER
                    {
                    GREATER196=(Token)match(input,GREATER,FOLLOW_GREATER_in_any1997);  
                    stream_GREATER.add(GREATER196);



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
                    // 998:17: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:999:9: LESS
                    {
                    LESS197=(Token)match(input,LESS,FOLLOW_LESS_in_any2011);  
                    stream_LESS.add(LESS197);



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
                    // 999:14: -> LESS
                    {
                        adaptor.addChild(root_0, stream_LESS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1000:9: QUESTION
                    {
                    QUESTION198=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_any2025);  
                    stream_QUESTION.add(QUESTION198);



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
                    // 1000:18: -> QUESTION
                    {
                        adaptor.addChild(root_0, stream_QUESTION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1001:9: PERCENT
                    {
                    PERCENT199=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_any2039);  
                    stream_PERCENT.add(PERCENT199);



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
                    // 1001:17: -> PERCENT
                    {
                        adaptor.addChild(root_0, stream_PERCENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1002:9: EQUALS
                    {
                    EQUALS200=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_any2053);  
                    stream_EQUALS.add(EQUALS200);



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
                    // 1002:16: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 18 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1003:9: SLASH
                    {
                    SLASH201=(Token)match(input,SLASH,FOLLOW_SLASH_in_any2067);  
                    stream_SLASH.add(SLASH201);



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
                    // 1003:15: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 19 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1004:9: EXCLAMATION
                    {
                    EXCLAMATION202=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_any2081);  
                    stream_EXCLAMATION.add(EXCLAMATION202);



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
                    // 1004:21: -> EXCLAMATION
                    {
                        adaptor.addChild(root_0, stream_EXCLAMATION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 20 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1005:6: MINUS
                    {
                    MINUS203=(Token)match(input,MINUS,FOLLOW_MINUS_in_any2092);  
                    stream_MINUS.add(MINUS203);



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
                    // 1005:12: -> MINUS
                    {
                        adaptor.addChild(root_0, stream_MINUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 21 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1006:6: PLUS
                    {
                    PLUS204=(Token)match(input,PLUS,FOLLOW_PLUS_in_any2103);  
                    stream_PLUS.add(PLUS204);



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
                    // 1006:11: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 22 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1007:6: ASTERISK
                    {
                    ASTERISK205=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_any2114);  
                    stream_ASTERISK.add(ASTERISK205);



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
                    // 1007:15: -> ASTERISK
                    {
                        adaptor.addChild(root_0, stream_ASTERISK.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 23 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1008:9: FUNCTION ( S )* ( any )* RPAREN
                    {
                    FUNCTION206=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_any2131);  
                    stream_FUNCTION.add(FUNCTION206);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1008:18: ( S )*
                    loop88:
                    do {
                        int alt88=2;
                        alt88 = dfa88.predict(input);
                        switch (alt88) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1008:18: S
                    	    {
                    	    S207=(Token)match(input,S,FOLLOW_S_in_any2133);  
                    	    stream_S.add(S207);


                    	    }
                    	    break;

                    	default :
                    	    break loop88;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1008:21: ( any )*
                    loop89:
                    do {
                        int alt89=2;
                        alt89 = dfa89.predict(input);
                        switch (alt89) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1008:21: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_any2136);
                    	    any208=any();

                    	    state._fsp--;

                    	    stream_any.add(any208.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop89;
                        }
                    } while (true);

                    RPAREN209=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_any2139);  
                    stream_RPAREN.add(RPAREN209);



                    // AST REWRITE
                    // elements: any, FUNCTION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1008:33: -> ^( FUNCTION ( any )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1008:36: ^( FUNCTION ( any )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_FUNCTION.nextNode(), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1008:47: ( any )*
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1009:9: DASHMATCH
                    {
                    DASHMATCH210=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_any2159);  
                    stream_DASHMATCH.add(DASHMATCH210);



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
                    // 1009:19: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 25 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1010:9: LPAREN ( any )* RPAREN
                    {
                    LPAREN211=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_any2173);  
                    stream_LPAREN.add(LPAREN211);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1010:16: ( any )*
                    loop90:
                    do {
                        int alt90=2;
                        alt90 = dfa90.predict(input);
                        switch (alt90) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1010:16: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_any2175);
                    	    any212=any();

                    	    state._fsp--;

                    	    stream_any.add(any212.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop90;
                        }
                    } while (true);

                    RPAREN213=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_any2178);  
                    stream_RPAREN.add(RPAREN213);



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
                    // 1010:28: -> ^( PARENBLOCK ( any )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1010:31: ^( PARENBLOCK ( any )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PARENBLOCK, "PARENBLOCK"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1010:44: ( any )*
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1011:9: LBRACE ( any )* RBRACE
                    {
                    LBRACE214=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_any2197);  
                    stream_LBRACE.add(LBRACE214);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1011:16: ( any )*
                    loop91:
                    do {
                        int alt91=2;
                        alt91 = dfa91.predict(input);
                        switch (alt91) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1011:16: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_any2199);
                    	    any215=any();

                    	    state._fsp--;

                    	    stream_any.add(any215.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop91;
                        }
                    } while (true);

                    RBRACE216=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_any2202);  
                    stream_RBRACE.add(RBRACE216);



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
                    // 1011:28: -> ^( BRACEBLOCK ( any )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1011:31: ^( BRACEBLOCK ( any )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BRACEBLOCK, "BRACEBLOCK"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1011:44: ( any )*
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1012:8: ( S )*
            loop93:
            do {
                int alt93=2;
                alt93 = dfa93.predict(input);
                switch (alt93) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1012:8: S
            	    {
            	    S217=(Token)match(input,S,FOLLOW_S_in_any2220);  
            	    stream_S.add(S217);


            	    }
            	    break;

            	default :
            	    break loop93;
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

    public static class nostatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "nostatement"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1014:1: nostatement : ( RCURLY -> RCURLY | SEMICOLON -> SEMICOLON ) ;
    public final CSSParser.nostatement_return nostatement() throws RecognitionException {
        CSSParser.nostatement_return retval = new CSSParser.nostatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token RCURLY218=null;
        Token SEMICOLON219=null;

        Object RCURLY218_tree=null;
        Object SEMICOLON219_tree=null;
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1016:3: ( ( RCURLY -> RCURLY | SEMICOLON -> SEMICOLON ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1016:5: ( RCURLY -> RCURLY | SEMICOLON -> SEMICOLON )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1016:5: ( RCURLY -> RCURLY | SEMICOLON -> SEMICOLON )
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==RCURLY) ) {
                alt94=1;
            }
            else if ( (LA94_0==SEMICOLON) ) {
                alt94=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 94, 0, input);

                throw nvae;
            }
            switch (alt94) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1016:7: RCURLY
                    {
                    RCURLY218=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_nostatement2235);  
                    stream_RCURLY.add(RCURLY218);



                    // AST REWRITE
                    // elements: RCURLY
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1016:14: -> RCURLY
                    {
                        adaptor.addChild(root_0, stream_RCURLY.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1017:7: SEMICOLON
                    {
                    SEMICOLON219=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_nostatement2249);  
                    stream_SEMICOLON.add(SEMICOLON219);



                    // AST REWRITE
                    // elements: SEMICOLON
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1017:17: -> SEMICOLON
                    {
                        adaptor.addChild(root_0, stream_SEMICOLON.nextNode());

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
    // $ANTLR end "nostatement"

    public static class noprop_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "noprop"
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1020:1: noprop : ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN ) ( S )* ;
    public final CSSParser.noprop_return noprop() throws RecognitionException {
        CSSParser.noprop_return retval = new CSSParser.noprop_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CLASSKEYWORD220=null;
        Token NUMBER221=null;
        Token COMMA222=null;
        Token GREATER223=null;
        Token LESS224=null;
        Token QUESTION225=null;
        Token PERCENT226=null;
        Token EQUALS227=null;
        Token SLASH228=null;
        Token EXCLAMATION229=null;
        Token PLUS230=null;
        Token ASTERISK231=null;
        Token DASHMATCH232=null;
        Token INCLUDES233=null;
        Token COLON234=null;
        Token STRING_CHAR235=null;
        Token INVALID_TOKEN236=null;
        Token S237=null;

        Object CLASSKEYWORD220_tree=null;
        Object NUMBER221_tree=null;
        Object COMMA222_tree=null;
        Object GREATER223_tree=null;
        Object LESS224_tree=null;
        Object QUESTION225_tree=null;
        Object PERCENT226_tree=null;
        Object EQUALS227_tree=null;
        Object SLASH228_tree=null;
        Object EXCLAMATION229_tree=null;
        Object PLUS230_tree=null;
        Object ASTERISK231_tree=null;
        Object DASHMATCH232_tree=null;
        Object INCLUDES233_tree=null;
        Object COLON234_tree=null;
        Object STRING_CHAR235_tree=null;
        Object INVALID_TOKEN236_tree=null;
        Object S237_tree=null;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1022:2: ( ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN ) ( S )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1022:4: ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN ) ( S )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1022:4: ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN )
            int alt95=17;
            alt95 = dfa95.predict(input);
            switch (alt95) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1022:6: CLASSKEYWORD
                    {
                    CLASSKEYWORD220=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_noprop2272);  
                    stream_CLASSKEYWORD.add(CLASSKEYWORD220);



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
                    // 1022:19: -> CLASSKEYWORD
                    {
                        adaptor.addChild(root_0, stream_CLASSKEYWORD.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1023:8: NUMBER
                    {
                    NUMBER221=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_noprop2285);  
                    stream_NUMBER.add(NUMBER221);



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
                    // 1023:15: -> NUMBER
                    {
                        adaptor.addChild(root_0, stream_NUMBER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1024:7: COMMA
                    {
                    COMMA222=(Token)match(input,COMMA,FOLLOW_COMMA_in_noprop2297);  
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
                    // 1024:13: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1025:7: GREATER
                    {
                    GREATER223=(Token)match(input,GREATER,FOLLOW_GREATER_in_noprop2309);  
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
                    // 1025:15: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1026:7: LESS
                    {
                    LESS224=(Token)match(input,LESS,FOLLOW_LESS_in_noprop2321);  
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
                    // 1026:12: -> LESS
                    {
                        adaptor.addChild(root_0, stream_LESS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1027:7: QUESTION
                    {
                    QUESTION225=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_noprop2333);  
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
                    // 1027:16: -> QUESTION
                    {
                        adaptor.addChild(root_0, stream_QUESTION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1028:7: PERCENT
                    {
                    PERCENT226=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_noprop2345);  
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
                    // 1028:15: -> PERCENT
                    {
                        adaptor.addChild(root_0, stream_PERCENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1029:7: EQUALS
                    {
                    EQUALS227=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_noprop2357);  
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
                    // 1029:14: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1030:7: SLASH
                    {
                    SLASH228=(Token)match(input,SLASH,FOLLOW_SLASH_in_noprop2369);  
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
                    // 1030:13: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1031:7: EXCLAMATION
                    {
                    EXCLAMATION229=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_noprop2381);  
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
                    // 1031:19: -> EXCLAMATION
                    {
                        adaptor.addChild(root_0, stream_EXCLAMATION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1032:7: PLUS
                    {
                    PLUS230=(Token)match(input,PLUS,FOLLOW_PLUS_in_noprop2393);  
                    stream_PLUS.add(PLUS230);



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
                    // 1032:12: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1033:7: ASTERISK
                    {
                    ASTERISK231=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_noprop2405);  
                    stream_ASTERISK.add(ASTERISK231);



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
                    // 1033:16: -> ASTERISK
                    {
                        adaptor.addChild(root_0, stream_ASTERISK.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1034:7: DASHMATCH
                    {
                    DASHMATCH232=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_noprop2420);  
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
                    // 1034:17: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1035:7: INCLUDES
                    {
                    INCLUDES233=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_noprop2432);  
                    stream_INCLUDES.add(INCLUDES233);



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
                    // 1035:16: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1036:7: COLON
                    {
                    COLON234=(Token)match(input,COLON,FOLLOW_COLON_in_noprop2444);  
                    stream_COLON.add(COLON234);



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
                    // 1036:13: -> COLON
                    {
                        adaptor.addChild(root_0, stream_COLON.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1037:7: STRING_CHAR
                    {
                    STRING_CHAR235=(Token)match(input,STRING_CHAR,FOLLOW_STRING_CHAR_in_noprop2456);  
                    stream_STRING_CHAR.add(STRING_CHAR235);



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
                    // 1037:19: -> STRING_CHAR
                    {
                        adaptor.addChild(root_0, stream_STRING_CHAR.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1038:7: INVALID_TOKEN
                    {
                    INVALID_TOKEN236=(Token)match(input,INVALID_TOKEN,FOLLOW_INVALID_TOKEN_in_noprop2468);  
                    stream_INVALID_TOKEN.add(INVALID_TOKEN236);



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
                    // 1038:21: -> INVALID_TOKEN
                    {
                        adaptor.addChild(root_0, stream_INVALID_TOKEN.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;

            }

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1039:8: ( S )*
            loop96:
            do {
                int alt96=2;
                alt96 = dfa96.predict(input);
                switch (alt96) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1039:8: S
            	    {
            	    S237=(Token)match(input,S,FOLLOW_S_in_noprop2481);  
            	    stream_S.add(S237);


            	    }
            	    break;

            	default :
            	    break loop96;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1041:1: norule : ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH | RPAREN -> RPAREN | '#' | '^' ) ;
    public final CSSParser.norule_return norule() throws RecognitionException {
        CSSParser.norule_return retval = new CSSParser.norule_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NUMBER238=null;
        Token PERCENTAGE239=null;
        Token DIMENSION240=null;
        Token URI242=null;
        Token UNIRANGE243=null;
        Token INCLUDES244=null;
        Token COMMA245=null;
        Token GREATER246=null;
        Token LESS247=null;
        Token QUESTION248=null;
        Token PERCENT249=null;
        Token EQUALS250=null;
        Token SLASH251=null;
        Token EXCLAMATION252=null;
        Token MINUS253=null;
        Token PLUS254=null;
        Token DASHMATCH255=null;
        Token RPAREN256=null;
        Token char_literal257=null;
        Token char_literal258=null;
        CSSParser.string_return string241 = null;


        Object NUMBER238_tree=null;
        Object PERCENTAGE239_tree=null;
        Object DIMENSION240_tree=null;
        Object URI242_tree=null;
        Object UNIRANGE243_tree=null;
        Object INCLUDES244_tree=null;
        Object COMMA245_tree=null;
        Object GREATER246_tree=null;
        Object LESS247_tree=null;
        Object QUESTION248_tree=null;
        Object PERCENT249_tree=null;
        Object EQUALS250_tree=null;
        Object SLASH251_tree=null;
        Object EXCLAMATION252_tree=null;
        Object MINUS253_tree=null;
        Object PLUS254_tree=null;
        Object DASHMATCH255_tree=null;
        Object RPAREN256_tree=null;
        Object char_literal257_tree=null;
        Object char_literal258_tree=null;
        RewriteRuleTokenStream stream_INCLUDES=new RewriteRuleTokenStream(adaptor,"token INCLUDES");
        RewriteRuleTokenStream stream_96=new RewriteRuleTokenStream(adaptor,"token 96");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_95=new RewriteRuleTokenStream(adaptor,"token 95");
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleTokenStream stream_SLASH=new RewriteRuleTokenStream(adaptor,"token SLASH");
        RewriteRuleTokenStream stream_DASHMATCH=new RewriteRuleTokenStream(adaptor,"token DASHMATCH");
        RewriteRuleTokenStream stream_EXCLAMATION=new RewriteRuleTokenStream(adaptor,"token EXCLAMATION");
        RewriteRuleTokenStream stream_QUESTION=new RewriteRuleTokenStream(adaptor,"token QUESTION");
        RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_NUMBER=new RewriteRuleTokenStream(adaptor,"token NUMBER");
        RewriteRuleTokenStream stream_LESS=new RewriteRuleTokenStream(adaptor,"token LESS");
        RewriteRuleTokenStream stream_UNIRANGE=new RewriteRuleTokenStream(adaptor,"token UNIRANGE");
        RewriteRuleTokenStream stream_DIMENSION=new RewriteRuleTokenStream(adaptor,"token DIMENSION");
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
        RewriteRuleTokenStream stream_EQUALS=new RewriteRuleTokenStream(adaptor,"token EQUALS");
        RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");
        RewriteRuleTokenStream stream_PERCENTAGE=new RewriteRuleTokenStream(adaptor,"token PERCENTAGE");
        RewriteRuleTokenStream stream_URI=new RewriteRuleTokenStream(adaptor,"token URI");
        RewriteRuleSubtreeStream stream_string=new RewriteRuleSubtreeStream(adaptor,"rule string");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1043:3: ( ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH | RPAREN -> RPAREN | '#' | '^' ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1043:5: ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH | RPAREN -> RPAREN | '#' | '^' )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1043:5: ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH | RPAREN -> RPAREN | '#' | '^' )
            int alt97=21;
            alt97 = dfa97.predict(input);
            switch (alt97) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1043:7: NUMBER
                    {
                    NUMBER238=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_norule2496);  
                    stream_NUMBER.add(NUMBER238);



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
                    // 1043:14: -> NUMBER
                    {
                        adaptor.addChild(root_0, stream_NUMBER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1044:8: PERCENTAGE
                    {
                    PERCENTAGE239=(Token)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_norule2509);  
                    stream_PERCENTAGE.add(PERCENTAGE239);



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
                    // 1044:19: -> PERCENTAGE
                    {
                        adaptor.addChild(root_0, stream_PERCENTAGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1045:8: DIMENSION
                    {
                    DIMENSION240=(Token)match(input,DIMENSION,FOLLOW_DIMENSION_in_norule2521);  
                    stream_DIMENSION.add(DIMENSION240);



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
                    // 1045:18: -> DIMENSION
                    {
                        adaptor.addChild(root_0, stream_DIMENSION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1046:8: string
                    {
                    pushFollow(FOLLOW_string_in_norule2534);
                    string241=string();

                    state._fsp--;

                    stream_string.add(string241.getTree());


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
                    // 1046:15: -> string
                    {
                        adaptor.addChild(root_0, stream_string.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1047:9: URI
                    {
                    URI242=(Token)match(input,URI,FOLLOW_URI_in_norule2548);  
                    stream_URI.add(URI242);



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
                    // 1047:16: -> URI
                    {
                        adaptor.addChild(root_0, stream_URI.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1048:9: UNIRANGE
                    {
                    UNIRANGE243=(Token)match(input,UNIRANGE,FOLLOW_UNIRANGE_in_norule2565);  
                    stream_UNIRANGE.add(UNIRANGE243);



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
                    // 1048:18: -> UNIRANGE
                    {
                        adaptor.addChild(root_0, stream_UNIRANGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1049:9: INCLUDES
                    {
                    INCLUDES244=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_norule2579);  
                    stream_INCLUDES.add(INCLUDES244);



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
                    // 1049:18: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1050:9: COMMA
                    {
                    COMMA245=(Token)match(input,COMMA,FOLLOW_COMMA_in_norule2593);  
                    stream_COMMA.add(COMMA245);



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
                    // 1050:15: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1051:9: GREATER
                    {
                    GREATER246=(Token)match(input,GREATER,FOLLOW_GREATER_in_norule2607);  
                    stream_GREATER.add(GREATER246);



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
                    // 1051:17: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1052:9: LESS
                    {
                    LESS247=(Token)match(input,LESS,FOLLOW_LESS_in_norule2621);  
                    stream_LESS.add(LESS247);



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
                    // 1052:14: -> LESS
                    {
                        adaptor.addChild(root_0, stream_LESS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1053:9: QUESTION
                    {
                    QUESTION248=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_norule2635);  
                    stream_QUESTION.add(QUESTION248);



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
                    // 1053:18: -> QUESTION
                    {
                        adaptor.addChild(root_0, stream_QUESTION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1054:9: PERCENT
                    {
                    PERCENT249=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_norule2649);  
                    stream_PERCENT.add(PERCENT249);



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
                    // 1054:17: -> PERCENT
                    {
                        adaptor.addChild(root_0, stream_PERCENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1055:9: EQUALS
                    {
                    EQUALS250=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_norule2663);  
                    stream_EQUALS.add(EQUALS250);



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
                    // 1055:16: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1056:9: SLASH
                    {
                    SLASH251=(Token)match(input,SLASH,FOLLOW_SLASH_in_norule2677);  
                    stream_SLASH.add(SLASH251);



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
                    // 1056:15: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1057:9: EXCLAMATION
                    {
                    EXCLAMATION252=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_norule2691);  
                    stream_EXCLAMATION.add(EXCLAMATION252);



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
                    // 1057:21: -> EXCLAMATION
                    {
                        adaptor.addChild(root_0, stream_EXCLAMATION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1058:8: MINUS
                    {
                    MINUS253=(Token)match(input,MINUS,FOLLOW_MINUS_in_norule2704);  
                    stream_MINUS.add(MINUS253);



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
                    // 1058:14: -> MINUS
                    {
                        adaptor.addChild(root_0, stream_MINUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1059:8: PLUS
                    {
                    PLUS254=(Token)match(input,PLUS,FOLLOW_PLUS_in_norule2717);  
                    stream_PLUS.add(PLUS254);



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
                    // 1059:13: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 18 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1060:9: DASHMATCH
                    {
                    DASHMATCH255=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_norule2731);  
                    stream_DASHMATCH.add(DASHMATCH255);



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
                    // 1060:19: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 19 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1061:9: RPAREN
                    {
                    RPAREN256=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_norule2745);  
                    stream_RPAREN.add(RPAREN256);



                    // AST REWRITE
                    // elements: RPAREN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1061:16: -> RPAREN
                    {
                        adaptor.addChild(root_0, stream_RPAREN.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 20 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1062:9: '#'
                    {
                    char_literal257=(Token)match(input,95,FOLLOW_95_in_norule2759);  
                    stream_95.add(char_literal257);


                    }
                    break;
                case 21 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1063:9: '^'
                    {
                    char_literal258=(Token)match(input,96,FOLLOW_96_in_norule2770);  
                    stream_96.add(char_literal258);


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
    protected DFA7 dfa7 = new DFA7(this);
    protected DFA10 dfa10 = new DFA10(this);
    protected DFA12 dfa12 = new DFA12(this);
    protected DFA11 dfa11 = new DFA11(this);
    protected DFA14 dfa14 = new DFA14(this);
    protected DFA20 dfa20 = new DFA20(this);
    protected DFA23 dfa23 = new DFA23(this);
    protected DFA37 dfa37 = new DFA37(this);
    protected DFA36 dfa36 = new DFA36(this);
    protected DFA38 dfa38 = new DFA38(this);
    protected DFA39 dfa39 = new DFA39(this);
    protected DFA40 dfa40 = new DFA40(this);
    protected DFA46 dfa46 = new DFA46(this);
    protected DFA42 dfa42 = new DFA42(this);
    protected DFA43 dfa43 = new DFA43(this);
    protected DFA45 dfa45 = new DFA45(this);
    protected DFA51 dfa51 = new DFA51(this);
    protected DFA56 dfa56 = new DFA56(this);
    protected DFA52 dfa52 = new DFA52(this);
    protected DFA54 dfa54 = new DFA54(this);
    protected DFA53 dfa53 = new DFA53(this);
    protected DFA55 dfa55 = new DFA55(this);
    protected DFA57 dfa57 = new DFA57(this);
    protected DFA58 dfa58 = new DFA58(this);
    protected DFA67 dfa67 = new DFA67(this);
    protected DFA65 dfa65 = new DFA65(this);
    protected DFA66 dfa66 = new DFA66(this);
    protected DFA68 dfa68 = new DFA68(this);
    protected DFA74 dfa74 = new DFA74(this);
    protected DFA75 dfa75 = new DFA75(this);
    protected DFA76 dfa76 = new DFA76(this);
    protected DFA77 dfa77 = new DFA77(this);
    protected DFA92 dfa92 = new DFA92(this);
    protected DFA88 dfa88 = new DFA88(this);
    protected DFA89 dfa89 = new DFA89(this);
    protected DFA90 dfa90 = new DFA90(this);
    protected DFA91 dfa91 = new DFA91(this);
    protected DFA93 dfa93 = new DFA93(this);
    protected DFA95 dfa95 = new DFA95(this);
    protected DFA96 dfa96 = new DFA96(this);
    protected DFA97 dfa97 = new DFA97(this);
    static final String DFA1_eotS =
        "\30\uffff";
    static final String DFA1_eofS =
        "\1\1\27\uffff";
    static final String DFA1_minS =
        "\1\36\27\uffff";
    static final String DFA1_maxS =
        "\1\114\27\uffff";
    static final String DFA1_acceptS =
        "\1\uffff\1\2\25\uffff\1\1";
    static final String DFA1_specialS =
        "\30\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\27\5\uffff\1\1\4\uffff\1\1\1\uffff\5\1\3\uffff\2\1\5\uffff"+
            "\12\1\7\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 753:4: ( S )*";
        }
    }
    static final String DFA3_eotS =
        "\64\uffff";
    static final String DFA3_eofS =
        "\1\1\20\uffff\1\1\42\uffff";
    static final String DFA3_minS =
        "\1\44\20\uffff\1\27\42\uffff";
    static final String DFA3_maxS =
        "\1\114\20\uffff\1\112\42\uffff";
    static final String DFA3_acceptS =
        "\1\uffff\1\1\24\uffff\1\2\1\uffff\1\1\11\uffff\1\1\13\uffff\1\1"+
        "\5\uffff";
    static final String DFA3_specialS =
        "\64\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\26\4\uffff\1\1\1\uffff\2\1\1\21\2\1\3\uffff\2\1\5\uffff\12"+
            "\1\7\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\1\6\uffff\1\1\12\uffff\1\30\1\uffff\2\1\1\42\2\1\1\uffff"+
            "\1\56\1\uffff\23\1\4\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "753:8: ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) )";
        }
    }
    static final String DFA4_eotS =
        "\53\uffff";
    static final String DFA4_eofS =
        "\1\1\52\uffff";
    static final String DFA4_minS =
        "\1\26\52\uffff";
    static final String DFA4_maxS =
        "\1\140\52\uffff";
    static final String DFA4_acceptS =
        "\1\uffff\1\6\1\1\1\2\1\3\1\4\1\uffff\1\5\43\uffff";
    static final String DFA4_specialS =
        "\53\uffff}>";
    static final String[] DFA4_transitionS = {
            "\2\7\1\uffff\1\7\2\uffff\1\7\1\uffff\1\4\1\2\1\3\3\7\1\uffff"+
            "\1\5\4\7\1\uffff\1\7\1\5\3\7\2\uffff\22\7\1\uffff\1\7\4\uffff"+
            "\1\7\24\uffff\2\7",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 759:4: ( CDO | CDC | S | nostatement | statement )*";
        }
    }
    static final String DFA5_eotS =
        "\45\uffff";
    static final String DFA5_eofS =
        "\45\uffff";
    static final String DFA5_minS =
        "\1\26\44\uffff";
    static final String DFA5_maxS =
        "\1\140\44\uffff";
    static final String DFA5_acceptS =
        "\1\uffff\1\1\33\uffff\1\2\7\uffff";
    static final String DFA5_specialS =
        "\45\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\35\1\1\1\uffff\1\1\2\uffff\1\35\4\uffff\3\35\2\uffff\3\35"+
            "\1\1\1\uffff\1\1\1\uffff\3\1\2\uffff\22\1\1\uffff\1\1\4\uffff"+
            "\1\1\24\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "763:1: statement : ( ruleset | atstatement );";
        }
    }
    static final String DFA7_eotS =
        "\27\uffff";
    static final String DFA7_eofS =
        "\27\uffff";
    static final String DFA7_minS =
        "\1\36\26\uffff";
    static final String DFA7_maxS =
        "\1\114\26\uffff";
    static final String DFA7_acceptS =
        "\1\uffff\1\2\24\uffff\1\1";
    static final String DFA7_specialS =
        "\27\uffff}>";
    static final String[] DFA7_transitionS = {
            "\1\26\6\uffff\1\1\3\uffff\1\1\1\uffff\5\1\3\uffff\2\1\5\uffff"+
            "\12\1\7\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "()* loopback of 774:11: ( S )*";
        }
    }
    static final String DFA10_eotS =
        "\37\uffff";
    static final String DFA10_eofS =
        "\37\uffff";
    static final String DFA10_minS =
        "\1\27\36\uffff";
    static final String DFA10_maxS =
        "\1\140\36\uffff";
    static final String DFA10_acceptS =
        "\1\uffff\1\2\34\uffff\1\1";
    static final String DFA10_specialS =
        "\37\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\1\1\uffff\1\1\4\uffff\1\36\6\uffff\1\1\3\uffff\1\1\1\uffff"+
            "\1\1\1\uffff\3\1\2\uffff\22\1\1\uffff\1\1\4\uffff\1\1\24\uffff"+
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
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "()* loopback of 777:10: ( S )*";
        }
    }
    static final String DFA12_eotS =
        "\36\uffff";
    static final String DFA12_eofS =
        "\36\uffff";
    static final String DFA12_minS =
        "\1\27\35\uffff";
    static final String DFA12_maxS =
        "\1\140\35\uffff";
    static final String DFA12_acceptS =
        "\1\uffff\1\2\1\1\33\uffff";
    static final String DFA12_specialS =
        "\36\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\2\1\uffff\1\2\13\uffff\1\1\3\uffff\1\2\1\uffff\1\2\1\uffff"+
            "\3\2\2\uffff\22\2\1\uffff\1\2\4\uffff\1\2\24\uffff\2\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 777:13: ( ruleset ( S )* )*";
        }
    }
    static final String DFA11_eotS =
        "\37\uffff";
    static final String DFA11_eofS =
        "\37\uffff";
    static final String DFA11_minS =
        "\1\27\36\uffff";
    static final String DFA11_maxS =
        "\1\140\36\uffff";
    static final String DFA11_acceptS =
        "\1\uffff\1\2\34\uffff\1\1";
    static final String DFA11_specialS =
        "\37\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\1\1\uffff\1\1\4\uffff\1\36\6\uffff\1\1\3\uffff\1\1\1\uffff"+
            "\1\1\1\uffff\3\1\2\uffff\22\1\1\uffff\1\1\4\uffff\1\1\24\uffff"+
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
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "()* loopback of 777:22: ( S )*";
        }
    }
    static final String DFA14_eotS =
        "\34\uffff";
    static final String DFA14_eofS =
        "\34\uffff";
    static final String DFA14_minS =
        "\1\27\33\uffff";
    static final String DFA14_maxS =
        "\1\112\33\uffff";
    static final String DFA14_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA14_specialS =
        "\34\uffff}>";
    static final String[] DFA14_transitionS = {
            "\1\2\15\uffff\1\1\3\uffff\1\2\1\uffff\1\2\1\uffff\3\2\1\uffff"+
            "\1\2\1\uffff\23\2\4\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 778:24: ( any )*";
        }
    }
    static final String DFA20_eotS =
        "\30\uffff";
    static final String DFA20_eofS =
        "\30\uffff";
    static final String DFA20_minS =
        "\1\36\27\uffff";
    static final String DFA20_maxS =
        "\1\114\27\uffff";
    static final String DFA20_acceptS =
        "\1\uffff\1\2\25\uffff\1\1";
    static final String DFA20_specialS =
        "\30\uffff}>";
    static final String[] DFA20_transitionS = {
            "\1\27\6\uffff\1\1\3\uffff\7\1\3\uffff\2\1\5\uffff\12\1\7\uffff"+
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
            return "()* loopback of 788:10: ( S )*";
        }
    }
    static final String DFA23_eotS =
        "\27\uffff";
    static final String DFA23_eofS =
        "\27\uffff";
    static final String DFA23_minS =
        "\1\36\26\uffff";
    static final String DFA23_maxS =
        "\1\114\26\uffff";
    static final String DFA23_acceptS =
        "\1\uffff\1\2\24\uffff\1\1";
    static final String DFA23_specialS =
        "\27\uffff}>";
    static final String[] DFA23_transitionS = {
            "\1\26\6\uffff\1\1\3\uffff\1\1\1\uffff\5\1\3\uffff\2\1\5\uffff"+
            "\12\1\7\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA23_eot = DFA.unpackEncodedString(DFA23_eotS);
    static final short[] DFA23_eof = DFA.unpackEncodedString(DFA23_eofS);
    static final char[] DFA23_min = DFA.unpackEncodedStringToUnsignedChars(DFA23_minS);
    static final char[] DFA23_max = DFA.unpackEncodedStringToUnsignedChars(DFA23_maxS);
    static final short[] DFA23_accept = DFA.unpackEncodedString(DFA23_acceptS);
    static final short[] DFA23_special = DFA.unpackEncodedString(DFA23_specialS);
    static final short[][] DFA23_transition;

    static {
        int numStates = DFA23_transitionS.length;
        DFA23_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA23_transition[i] = DFA.unpackEncodedString(DFA23_transitionS[i]);
        }
    }

    class DFA23 extends DFA {

        public DFA23(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 23;
            this.eot = DFA23_eot;
            this.eof = DFA23_eof;
            this.min = DFA23_min;
            this.max = DFA23_max;
            this.accept = DFA23_accept;
            this.special = DFA23_special;
            this.transition = DFA23_transition;
        }
        public String getDescription() {
            return "()* loopback of 799:26: ( S )*";
        }
    }
    static final String DFA37_eotS =
        "\35\uffff";
    static final String DFA37_eofS =
        "\35\uffff";
    static final String DFA37_minS =
        "\1\27\34\uffff";
    static final String DFA37_maxS =
        "\1\140\34\uffff";
    static final String DFA37_acceptS =
        "\1\uffff\1\1\6\uffff\1\2\24\uffff";
    static final String DFA37_specialS =
        "\35\uffff}>";
    static final String[] DFA37_transitionS = {
            "\1\10\1\uffff\1\1\17\uffff\1\1\1\uffff\1\10\1\uffff\1\1\2\10"+
            "\2\uffff\1\10\1\1\4\10\1\1\11\10\1\1\1\10\1\uffff\1\1\4\uffff"+
            "\1\10\24\uffff\2\10",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "817:1: ruleset : ( combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY -> ^( RULE ( combined_selector )+ declarations ) | norule -> INVALID_STATEMENT );";
        }
    }
    static final String DFA36_eotS =
        "\27\uffff";
    static final String DFA36_eofS =
        "\27\uffff";
    static final String DFA36_minS =
        "\1\36\26\uffff";
    static final String DFA36_maxS =
        "\1\114\26\uffff";
    static final String DFA36_acceptS =
        "\1\uffff\1\2\24\uffff\1\1";
    static final String DFA36_specialS =
        "\27\uffff}>";
    static final String[] DFA36_transitionS = {
            "\1\26\6\uffff\1\1\3\uffff\1\1\1\uffff\5\1\3\uffff\2\1\5\uffff"+
            "\12\1\7\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 819:11: ( S )*";
        }
    }
    static final String DFA38_eotS =
        "\30\uffff";
    static final String DFA38_eofS =
        "\1\24\27\uffff";
    static final String DFA38_minS =
        "\1\45\27\uffff";
    static final String DFA38_maxS =
        "\1\114\27\uffff";
    static final String DFA38_acceptS =
        "\1\uffff\1\1\22\uffff\1\2\3\uffff";
    static final String DFA38_specialS =
        "\30\uffff}>";
    static final String[] DFA38_transitionS = {
            "\1\24\3\uffff\1\1\1\24\1\1\1\24\3\1\3\uffff\2\1\5\uffff\12\1"+
            "\7\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "832:4: ( declaration )?";
        }
    }
    static final String DFA39_eotS =
        "\31\uffff";
    static final String DFA39_eofS =
        "\1\1\30\uffff";
    static final String DFA39_minS =
        "\1\36\30\uffff";
    static final String DFA39_maxS =
        "\1\114\30\uffff";
    static final String DFA39_acceptS =
        "\1\uffff\1\2\26\uffff\1\1";
    static final String DFA39_specialS =
        "\31\uffff}>";
    static final String[] DFA39_transitionS = {
            "\1\30\6\uffff\1\1\3\uffff\7\1\3\uffff\2\1\5\uffff\12\1\7\uffff"+
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
            return "()* loopback of 832:28: ( S )*";
        }
    }
    static final String DFA40_eotS =
        "\30\uffff";
    static final String DFA40_eofS =
        "\1\24\27\uffff";
    static final String DFA40_minS =
        "\1\45\27\uffff";
    static final String DFA40_maxS =
        "\1\114\27\uffff";
    static final String DFA40_acceptS =
        "\1\uffff\1\1\22\uffff\1\2\3\uffff";
    static final String DFA40_specialS =
        "\30\uffff}>";
    static final String[] DFA40_transitionS = {
            "\1\24\3\uffff\1\1\1\24\1\1\1\24\3\1\3\uffff\2\1\5\uffff\12\1"+
            "\7\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "832:31: ( declaration )?";
        }
    }
    static final String DFA46_eotS =
        "\24\uffff";
    static final String DFA46_eofS =
        "\24\uffff";
    static final String DFA46_minS =
        "\1\51\23\uffff";
    static final String DFA46_maxS =
        "\1\114\23\uffff";
    static final String DFA46_acceptS =
        "\1\uffff\1\1\1\uffff\1\2\20\uffff";
    static final String DFA46_specialS =
        "\24\uffff}>";
    static final String[] DFA46_transitionS = {
            "\1\1\1\uffff\1\3\1\uffff\2\3\1\1\3\uffff\2\3\5\uffff\12\3\7"+
            "\uffff\2\3",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "836:1: declaration : ( property COLON ( S )* ( terms )? ( important )? -> ^( DECLARATION ( important )? property ( terms )? ) | noprop ( any )* -> INVALID_DECLARATION );";
        }
    }
    static final String DFA42_eotS =
        "\43\uffff";
    static final String DFA42_eofS =
        "\1\1\42\uffff";
    static final String DFA42_minS =
        "\1\27\42\uffff";
    static final String DFA42_maxS =
        "\1\112\42\uffff";
    static final String DFA42_acceptS =
        "\1\uffff\1\2\40\uffff\1\1";
    static final String DFA42_specialS =
        "\43\uffff}>";
    static final String[] DFA42_transitionS = {
            "\1\1\6\uffff\1\42\5\uffff\2\1\1\uffff\1\1\1\uffff\11\1\1\uffff"+
            "\23\1\4\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 841:19: ( S )*";
        }
    }
    static final String DFA43_eotS =
        "\42\uffff";
    static final String DFA43_eofS =
        "\1\35\41\uffff";
    static final String DFA43_minS =
        "\1\27\41\uffff";
    static final String DFA43_maxS =
        "\1\112\41\uffff";
    static final String DFA43_acceptS =
        "\1\uffff\1\1\33\uffff\1\2\4\uffff";
    static final String DFA43_specialS =
        "\42\uffff}>";
    static final String[] DFA43_transitionS = {
            "\1\1\14\uffff\1\1\1\35\1\uffff\1\1\1\uffff\1\1\1\35\1\1\1\35"+
            "\1\1\1\35\3\1\1\uffff\23\1\4\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "841:22: ( terms )?";
        }
    }
    static final String DFA45_eotS =
        "\37\uffff";
    static final String DFA45_eofS =
        "\1\1\36\uffff";
    static final String DFA45_minS =
        "\1\27\36\uffff";
    static final String DFA45_maxS =
        "\1\112\36\uffff";
    static final String DFA45_acceptS =
        "\1\uffff\1\2\3\uffff\1\1\31\uffff";
    static final String DFA45_specialS =
        "\37\uffff}>";
    static final String[] DFA45_transitionS = {
            "\1\5\15\uffff\1\1\3\uffff\1\5\1\1\1\5\1\1\3\5\1\uffff\1\5\1"+
            "\uffff\23\5\4\uffff\1\5",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 842:11: ( any )*";
        }
    }
    static final String DFA51_eotS =
        "\43\uffff";
    static final String DFA51_eofS =
        "\1\1\42\uffff";
    static final String DFA51_minS =
        "\1\27\42\uffff";
    static final String DFA51_maxS =
        "\1\112\42\uffff";
    static final String DFA51_acceptS =
        "\1\uffff\1\2\5\uffff\1\1\33\uffff";
    static final String DFA51_specialS =
        "\43\uffff}>";
    static final String[] DFA51_transitionS = {
            "\1\7\14\uffff\1\7\1\1\1\uffff\1\7\1\uffff\1\7\1\1\1\7\1\1\1"+
            "\7\1\1\3\7\1\1\23\7\4\uffff\1\7",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()+ loopback of 863:4: ( term )+";
        }
    }
    static final String DFA56_eotS =
        "\35\uffff";
    static final String DFA56_eofS =
        "\35\uffff";
    static final String DFA56_minS =
        "\1\27\34\uffff";
    static final String DFA56_maxS =
        "\1\112\34\uffff";
    static final String DFA56_acceptS =
        "\1\uffff\1\1\31\uffff\1\2\1\3";
    static final String DFA56_specialS =
        "\35\uffff}>";
    static final String[] DFA56_transitionS = {
            "\1\1\14\uffff\1\33\2\uffff\1\34\1\uffff\1\1\1\uffff\1\1\1\uffff"+
            "\1\1\1\uffff\3\1\1\uffff\23\1\4\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "880:1: term : ( valuepart -> valuepart | LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY -> CURLYBLOCK | ATKEYWORD ( S )* -> ATKEYWORD );";
        }
    }
    static final String DFA52_eotS =
        "\36\uffff";
    static final String DFA52_eofS =
        "\36\uffff";
    static final String DFA52_minS =
        "\1\27\35\uffff";
    static final String DFA52_maxS =
        "\1\112\35\uffff";
    static final String DFA52_acceptS =
        "\1\uffff\1\2\33\uffff\1\1";
    static final String DFA52_specialS =
        "\36\uffff}>";
    static final String[] DFA52_transitionS = {
            "\1\1\6\uffff\1\35\6\uffff\1\1\3\uffff\1\1\1\uffff\5\1\1\uffff"+
            "\1\1\1\uffff\23\1\4\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 882:14: ( S )*";
        }
    }
    static final String DFA54_eotS =
        "\35\uffff";
    static final String DFA54_eofS =
        "\35\uffff";
    static final String DFA54_minS =
        "\1\27\34\uffff";
    static final String DFA54_maxS =
        "\1\112\34\uffff";
    static final String DFA54_acceptS =
        "\1\uffff\1\3\1\1\31\uffff\1\2";
    static final String DFA54_specialS =
        "\35\uffff}>";
    static final String[] DFA54_transitionS = {
            "\1\2\15\uffff\1\1\3\uffff\1\2\1\uffff\1\2\1\34\3\2\1\uffff\1"+
            "\2\1\uffff\23\2\4\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 882:17: ( any | SEMICOLON ( S )* )*";
        }
    }
    static final String DFA53_eotS =
        "\36\uffff";
    static final String DFA53_eofS =
        "\36\uffff";
    static final String DFA53_minS =
        "\1\27\35\uffff";
    static final String DFA53_maxS =
        "\1\112\35\uffff";
    static final String DFA53_acceptS =
        "\1\uffff\1\2\33\uffff\1\1";
    static final String DFA53_specialS =
        "\36\uffff}>";
    static final String[] DFA53_transitionS = {
            "\1\1\6\uffff\1\35\6\uffff\1\1\3\uffff\1\1\1\uffff\5\1\1\uffff"+
            "\1\1\1\uffff\23\1\4\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA53_eot = DFA.unpackEncodedString(DFA53_eotS);
    static final short[] DFA53_eof = DFA.unpackEncodedString(DFA53_eofS);
    static final char[] DFA53_min = DFA.unpackEncodedStringToUnsignedChars(DFA53_minS);
    static final char[] DFA53_max = DFA.unpackEncodedStringToUnsignedChars(DFA53_maxS);
    static final short[] DFA53_accept = DFA.unpackEncodedString(DFA53_acceptS);
    static final short[] DFA53_special = DFA.unpackEncodedString(DFA53_specialS);
    static final short[][] DFA53_transition;

    static {
        int numStates = DFA53_transitionS.length;
        DFA53_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA53_transition[i] = DFA.unpackEncodedString(DFA53_transitionS[i]);
        }
    }

    class DFA53 extends DFA {

        public DFA53(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 53;
            this.eot = DFA53_eot;
            this.eof = DFA53_eof;
            this.min = DFA53_min;
            this.max = DFA53_max;
            this.accept = DFA53_accept;
            this.special = DFA53_special;
            this.transition = DFA53_transition;
        }
        public String getDescription() {
            return "()* loopback of 882:34: ( S )*";
        }
    }
    static final String DFA55_eotS =
        "\44\uffff";
    static final String DFA55_eofS =
        "\1\1\43\uffff";
    static final String DFA55_minS =
        "\1\27\43\uffff";
    static final String DFA55_maxS =
        "\1\112\43\uffff";
    static final String DFA55_acceptS =
        "\1\uffff\1\2\41\uffff\1\1";
    static final String DFA55_specialS =
        "\44\uffff}>";
    static final String[] DFA55_transitionS = {
            "\1\1\6\uffff\1\43\5\uffff\2\1\1\uffff\1\1\1\uffff\35\1\4\uffff"+
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
            return "()* loopback of 883:17: ( S )*";
        }
    }
    static final String DFA57_eotS =
        "\37\uffff";
    static final String DFA57_eofS =
        "\37\uffff";
    static final String DFA57_minS =
        "\1\27\36\uffff";
    static final String DFA57_maxS =
        "\1\112\36\uffff";
    static final String DFA57_acceptS =
        "\1\uffff\1\2\34\uffff\1\1";
    static final String DFA57_specialS =
        "\37\uffff}>";
    static final String[] DFA57_transitionS = {
            "\1\1\6\uffff\1\36\5\uffff\1\1\2\uffff\1\1\1\uffff\1\1\1\uffff"+
            "\1\1\1\uffff\1\1\1\uffff\27\1\4\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 895:13: ( S )*";
        }
    }
    static final String DFA58_eotS =
        "\36\uffff";
    static final String DFA58_eofS =
        "\36\uffff";
    static final String DFA58_minS =
        "\1\27\35\uffff";
    static final String DFA58_maxS =
        "\1\112\35\uffff";
    static final String DFA58_acceptS =
        "\1\uffff\1\1\33\uffff\1\2";
    static final String DFA58_specialS =
        "\36\uffff}>";
    static final String[] DFA58_transitionS = {
            "\1\1\14\uffff\1\1\2\uffff\1\1\1\uffff\1\1\1\uffff\1\1\1\uffff"+
            "\1\1\1\uffff\3\1\1\35\23\1\4\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "895:16: ( terms )?";
        }
    }
    static final String DFA67_eotS =
        "\41\uffff";
    static final String DFA67_eofS =
        "\41\uffff";
    static final String DFA67_minS =
        "\1\27\1\51\37\uffff";
    static final String DFA67_maxS =
        "\1\112\1\66\37\uffff";
    static final String DFA67_acceptS =
        "\2\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\uffff\1\26\1\27\1"+
        "\30\6\uffff";
    static final String DFA67_specialS =
        "\41\uffff}>";
    static final String[] DFA67_transitionS = {
            "\1\7\21\uffff\1\2\1\uffff\1\15\1\uffff\1\14\1\uffff\1\1\2\26"+
            "\1\uffff\1\3\1\4\1\5\1\6\1\10\1\11\1\12\1\13\1\16\1\17\1\20"+
            "\1\21\1\22\1\23\1\24\1\25\1\30\1\31\1\32\4\uffff\1\7",
            "\1\2\6\uffff\2\26\2\uffff\1\4\1\5\1\6",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "900:7: ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | ( MINUS )? funct -> ( MINUS )? funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) )";
        }
    }
    static final String DFA65_eotS =
        "\34\uffff";
    static final String DFA65_eofS =
        "\34\uffff";
    static final String DFA65_minS =
        "\1\27\33\uffff";
    static final String DFA65_maxS =
        "\1\112\33\uffff";
    static final String DFA65_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA65_specialS =
        "\34\uffff}>";
    static final String[] DFA65_transitionS = {
            "\1\2\21\uffff\1\2\1\uffff\1\2\1\uffff\1\2\1\uffff\3\2\1\1\23"+
            "\2\4\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 922:16: ( valuepart )*";
        }
    }
    static final String DFA66_eotS =
        "\34\uffff";
    static final String DFA66_eofS =
        "\34\uffff";
    static final String DFA66_minS =
        "\1\27\33\uffff";
    static final String DFA66_maxS =
        "\1\112\33\uffff";
    static final String DFA66_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA66_specialS =
        "\34\uffff}>";
    static final String[] DFA66_transitionS = {
            "\1\2\21\uffff\1\2\1\uffff\1\2\1\uffff\1\2\1\uffff\3\2\1\uffff"+
            "\23\2\1\1\3\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 923:16: ( valuepart )*";
        }
    }
    static final String DFA68_eotS =
        "\45\uffff";
    static final String DFA68_eofS =
        "\1\1\44\uffff";
    static final String DFA68_minS =
        "\1\27\44\uffff";
    static final String DFA68_maxS =
        "\1\112\44\uffff";
    static final String DFA68_acceptS =
        "\1\uffff\1\2\42\uffff\1\1";
    static final String DFA68_specialS =
        "\45\uffff}>";
    static final String[] DFA68_transitionS = {
            "\1\1\6\uffff\1\44\5\uffff\2\1\1\uffff\1\1\1\uffff\36\1\3\uffff"+
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
            return "()* loopback of 924:8: ( S )*";
        }
    }
    static final String DFA74_eotS =
        "\13\uffff";
    static final String DFA74_eofS =
        "\13\uffff";
    static final String DFA74_minS =
        "\1\31\12\uffff";
    static final String DFA74_maxS =
        "\1\105\12\uffff";
    static final String DFA74_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\4\uffff";
    static final String DFA74_specialS =
        "\13\uffff}>";
    static final String[] DFA74_transitionS = {
            "\1\6\4\uffff\1\1\5\uffff\1\1\6\uffff\1\1\1\uffff\1\6\5\uffff"+
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
            return "()* loopback of 943:27: ( selpart )*";
        }
    }
    static final String DFA75_eotS =
        "\22\uffff";
    static final String DFA75_eofS =
        "\22\uffff";
    static final String DFA75_minS =
        "\1\36\2\uffff\1\31\16\uffff";
    static final String DFA75_maxS =
        "\1\101\2\uffff\1\105\16\uffff";
    static final String DFA75_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\13\uffff";
    static final String DFA75_specialS =
        "\22\uffff}>";
    static final String[] DFA75_transitionS = {
            "\1\3\5\uffff\1\1\6\uffff\1\1\17\uffff\1\1\5\uffff\1\1",
            "",
            "",
            "\1\1\4\uffff\1\6\5\uffff\1\6\4\uffff\1\1\1\uffff\1\6\1\uffff"+
            "\1\1\5\uffff\1\1\4\uffff\1\1\2\uffff\1\6\5\uffff\1\6\1\1\2\uffff"+
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
            return "()* loopback of 943:36: ( S )*";
        }
    }
    static final String DFA76_eotS =
        "\13\uffff";
    static final String DFA76_eofS =
        "\13\uffff";
    static final String DFA76_minS =
        "\1\31\12\uffff";
    static final String DFA76_maxS =
        "\1\105\12\uffff";
    static final String DFA76_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\4\uffff";
    static final String DFA76_specialS =
        "\13\uffff}>";
    static final String[] DFA76_transitionS = {
            "\1\6\4\uffff\1\1\5\uffff\1\1\6\uffff\1\1\1\uffff\1\6\5\uffff"+
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
            return "()+ loopback of 945:7: ( selpart )+";
        }
    }
    static final String DFA77_eotS =
        "\22\uffff";
    static final String DFA77_eofS =
        "\22\uffff";
    static final String DFA77_minS =
        "\1\36\2\uffff\1\31\16\uffff";
    static final String DFA77_maxS =
        "\1\101\2\uffff\1\105\16\uffff";
    static final String DFA77_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\13\uffff";
    static final String DFA77_specialS =
        "\22\uffff}>";
    static final String[] DFA77_transitionS = {
            "\1\3\5\uffff\1\1\6\uffff\1\1\17\uffff\1\1\5\uffff\1\1",
            "",
            "",
            "\1\1\4\uffff\1\6\5\uffff\1\6\4\uffff\1\1\1\uffff\1\6\1\uffff"+
            "\1\1\5\uffff\1\1\4\uffff\1\1\2\uffff\1\6\5\uffff\1\6\1\1\2\uffff"+
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
            return "()* loopback of 945:16: ( S )*";
        }
    }
    static final String DFA92_eotS =
        "\33\uffff";
    static final String DFA92_eofS =
        "\33\uffff";
    static final String DFA92_minS =
        "\1\27\32\uffff";
    static final String DFA92_maxS =
        "\1\112\32\uffff";
    static final String DFA92_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
        "\1\32";
    static final String DFA92_specialS =
        "\33\uffff}>";
    static final String[] DFA92_transitionS = {
            "\1\6\21\uffff\1\1\1\uffff\1\14\1\uffff\1\13\1\23\1\24\1\uffff"+
            "\1\27\1\uffff\1\2\1\3\1\4\1\5\1\7\1\10\1\11\1\12\1\15\1\16\1"+
            "\17\1\20\1\21\1\22\1\25\1\26\1\30\1\31\1\32\4\uffff\1\6",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA92_eot = DFA.unpackEncodedString(DFA92_eotS);
    static final short[] DFA92_eof = DFA.unpackEncodedString(DFA92_eofS);
    static final char[] DFA92_min = DFA.unpackEncodedStringToUnsignedChars(DFA92_minS);
    static final char[] DFA92_max = DFA.unpackEncodedStringToUnsignedChars(DFA92_maxS);
    static final short[] DFA92_accept = DFA.unpackEncodedString(DFA92_acceptS);
    static final short[] DFA92_special = DFA.unpackEncodedString(DFA92_specialS);
    static final short[][] DFA92_transition;

    static {
        int numStates = DFA92_transitionS.length;
        DFA92_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA92_transition[i] = DFA.unpackEncodedString(DFA92_transitionS[i]);
        }
    }

    class DFA92 extends DFA {

        public DFA92(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 92;
            this.eot = DFA92_eot;
            this.eof = DFA92_eof;
            this.min = DFA92_min;
            this.max = DFA92_max;
            this.accept = DFA92_accept;
            this.special = DFA92_special;
            this.transition = DFA92_transition;
        }
        public String getDescription() {
            return "986:4: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) )";
        }
    }
    static final String DFA88_eotS =
        "\35\uffff";
    static final String DFA88_eofS =
        "\35\uffff";
    static final String DFA88_minS =
        "\1\27\34\uffff";
    static final String DFA88_maxS =
        "\1\112\34\uffff";
    static final String DFA88_acceptS =
        "\1\uffff\1\2\32\uffff\1\1";
    static final String DFA88_specialS =
        "\35\uffff}>";
    static final String[] DFA88_transitionS = {
            "\1\1\6\uffff\1\34\12\uffff\1\1\1\uffff\1\1\1\uffff\3\1\1\uffff"+
            "\25\1\4\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 1008:18: ( S )*";
        }
    }
    static final String DFA89_eotS =
        "\34\uffff";
    static final String DFA89_eofS =
        "\34\uffff";
    static final String DFA89_minS =
        "\1\27\33\uffff";
    static final String DFA89_maxS =
        "\1\112\33\uffff";
    static final String DFA89_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA89_specialS =
        "\34\uffff}>";
    static final String[] DFA89_transitionS = {
            "\1\2\21\uffff\1\2\1\uffff\1\2\1\uffff\3\2\1\uffff\1\2\1\1\23"+
            "\2\4\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA89_eot = DFA.unpackEncodedString(DFA89_eotS);
    static final short[] DFA89_eof = DFA.unpackEncodedString(DFA89_eofS);
    static final char[] DFA89_min = DFA.unpackEncodedStringToUnsignedChars(DFA89_minS);
    static final char[] DFA89_max = DFA.unpackEncodedStringToUnsignedChars(DFA89_maxS);
    static final short[] DFA89_accept = DFA.unpackEncodedString(DFA89_acceptS);
    static final short[] DFA89_special = DFA.unpackEncodedString(DFA89_specialS);
    static final short[][] DFA89_transition;

    static {
        int numStates = DFA89_transitionS.length;
        DFA89_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA89_transition[i] = DFA.unpackEncodedString(DFA89_transitionS[i]);
        }
    }

    class DFA89 extends DFA {

        public DFA89(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 89;
            this.eot = DFA89_eot;
            this.eof = DFA89_eof;
            this.min = DFA89_min;
            this.max = DFA89_max;
            this.accept = DFA89_accept;
            this.special = DFA89_special;
            this.transition = DFA89_transition;
        }
        public String getDescription() {
            return "()* loopback of 1008:21: ( any )*";
        }
    }
    static final String DFA90_eotS =
        "\34\uffff";
    static final String DFA90_eofS =
        "\34\uffff";
    static final String DFA90_minS =
        "\1\27\33\uffff";
    static final String DFA90_maxS =
        "\1\112\33\uffff";
    static final String DFA90_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA90_specialS =
        "\34\uffff}>";
    static final String[] DFA90_transitionS = {
            "\1\2\21\uffff\1\2\1\uffff\1\2\1\uffff\3\2\1\uffff\1\2\1\1\23"+
            "\2\4\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA90_eot = DFA.unpackEncodedString(DFA90_eotS);
    static final short[] DFA90_eof = DFA.unpackEncodedString(DFA90_eofS);
    static final char[] DFA90_min = DFA.unpackEncodedStringToUnsignedChars(DFA90_minS);
    static final char[] DFA90_max = DFA.unpackEncodedStringToUnsignedChars(DFA90_maxS);
    static final short[] DFA90_accept = DFA.unpackEncodedString(DFA90_acceptS);
    static final short[] DFA90_special = DFA.unpackEncodedString(DFA90_specialS);
    static final short[][] DFA90_transition;

    static {
        int numStates = DFA90_transitionS.length;
        DFA90_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA90_transition[i] = DFA.unpackEncodedString(DFA90_transitionS[i]);
        }
    }

    class DFA90 extends DFA {

        public DFA90(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 90;
            this.eot = DFA90_eot;
            this.eof = DFA90_eof;
            this.min = DFA90_min;
            this.max = DFA90_max;
            this.accept = DFA90_accept;
            this.special = DFA90_special;
            this.transition = DFA90_transition;
        }
        public String getDescription() {
            return "()* loopback of 1010:16: ( any )*";
        }
    }
    static final String DFA91_eotS =
        "\34\uffff";
    static final String DFA91_eofS =
        "\34\uffff";
    static final String DFA91_minS =
        "\1\27\33\uffff";
    static final String DFA91_maxS =
        "\1\112\33\uffff";
    static final String DFA91_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA91_specialS =
        "\34\uffff}>";
    static final String[] DFA91_transitionS = {
            "\1\2\21\uffff\1\2\1\uffff\1\2\1\uffff\3\2\1\uffff\1\2\1\uffff"+
            "\23\2\1\1\3\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA91_eot = DFA.unpackEncodedString(DFA91_eotS);
    static final short[] DFA91_eof = DFA.unpackEncodedString(DFA91_eofS);
    static final char[] DFA91_min = DFA.unpackEncodedStringToUnsignedChars(DFA91_minS);
    static final char[] DFA91_max = DFA.unpackEncodedStringToUnsignedChars(DFA91_maxS);
    static final short[] DFA91_accept = DFA.unpackEncodedString(DFA91_acceptS);
    static final short[] DFA91_special = DFA.unpackEncodedString(DFA91_specialS);
    static final short[][] DFA91_transition;

    static {
        int numStates = DFA91_transitionS.length;
        DFA91_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA91_transition[i] = DFA.unpackEncodedString(DFA91_transitionS[i]);
        }
    }

    class DFA91 extends DFA {

        public DFA91(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 91;
            this.eot = DFA91_eot;
            this.eof = DFA91_eof;
            this.min = DFA91_min;
            this.max = DFA91_max;
            this.accept = DFA91_accept;
            this.special = DFA91_special;
            this.transition = DFA91_transition;
        }
        public String getDescription() {
            return "()* loopback of 1011:16: ( any )*";
        }
    }
    static final String DFA93_eotS =
        "\42\uffff";
    static final String DFA93_eofS =
        "\1\1\41\uffff";
    static final String DFA93_minS =
        "\1\27\41\uffff";
    static final String DFA93_maxS =
        "\1\112\41\uffff";
    static final String DFA93_acceptS =
        "\1\uffff\1\2\37\uffff\1\1";
    static final String DFA93_specialS =
        "\42\uffff}>";
    static final String[] DFA93_transitionS = {
            "\1\1\6\uffff\1\41\6\uffff\1\1\3\uffff\7\1\1\uffff\26\1\3\uffff"+
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
            "",
            "",
            "",
            ""
    };

    static final short[] DFA93_eot = DFA.unpackEncodedString(DFA93_eotS);
    static final short[] DFA93_eof = DFA.unpackEncodedString(DFA93_eofS);
    static final char[] DFA93_min = DFA.unpackEncodedStringToUnsignedChars(DFA93_minS);
    static final char[] DFA93_max = DFA.unpackEncodedStringToUnsignedChars(DFA93_maxS);
    static final short[] DFA93_accept = DFA.unpackEncodedString(DFA93_acceptS);
    static final short[] DFA93_special = DFA.unpackEncodedString(DFA93_specialS);
    static final short[][] DFA93_transition;

    static {
        int numStates = DFA93_transitionS.length;
        DFA93_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA93_transition[i] = DFA.unpackEncodedString(DFA93_transitionS[i]);
        }
    }

    class DFA93 extends DFA {

        public DFA93(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 93;
            this.eot = DFA93_eot;
            this.eof = DFA93_eof;
            this.min = DFA93_min;
            this.max = DFA93_max;
            this.accept = DFA93_accept;
            this.special = DFA93_special;
            this.transition = DFA93_transition;
        }
        public String getDescription() {
            return "()* loopback of 1012:8: ( S )*";
        }
    }
    static final String DFA95_eotS =
        "\22\uffff";
    static final String DFA95_eofS =
        "\22\uffff";
    static final String DFA95_minS =
        "\1\53\21\uffff";
    static final String DFA95_maxS =
        "\1\114\21\uffff";
    static final String DFA95_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21";
    static final String DFA95_specialS =
        "\22\uffff}>";
    static final String[] DFA95_transitionS = {
            "\1\3\1\uffff\1\17\1\12\4\uffff\1\1\1\2\5\uffff\1\16\1\4\1\5"+
            "\1\6\1\7\1\10\1\11\1\13\1\14\1\15\7\uffff\1\20\1\21",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA95_eot = DFA.unpackEncodedString(DFA95_eotS);
    static final short[] DFA95_eof = DFA.unpackEncodedString(DFA95_eofS);
    static final char[] DFA95_min = DFA.unpackEncodedStringToUnsignedChars(DFA95_minS);
    static final char[] DFA95_max = DFA.unpackEncodedStringToUnsignedChars(DFA95_maxS);
    static final short[] DFA95_accept = DFA.unpackEncodedString(DFA95_acceptS);
    static final short[] DFA95_special = DFA.unpackEncodedString(DFA95_specialS);
    static final short[][] DFA95_transition;

    static {
        int numStates = DFA95_transitionS.length;
        DFA95_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA95_transition[i] = DFA.unpackEncodedString(DFA95_transitionS[i]);
        }
    }

    class DFA95 extends DFA {

        public DFA95(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 95;
            this.eot = DFA95_eot;
            this.eof = DFA95_eof;
            this.min = DFA95_min;
            this.max = DFA95_max;
            this.accept = DFA95_accept;
            this.special = DFA95_special;
            this.transition = DFA95_transition;
        }
        public String getDescription() {
            return "1022:4: ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN )";
        }
    }
    static final String DFA96_eotS =
        "\40\uffff";
    static final String DFA96_eofS =
        "\1\1\37\uffff";
    static final String DFA96_minS =
        "\1\27\37\uffff";
    static final String DFA96_maxS =
        "\1\112\37\uffff";
    static final String DFA96_acceptS =
        "\1\uffff\1\2\35\uffff\1\1";
    static final String DFA96_specialS =
        "\40\uffff}>";
    static final String[] DFA96_transitionS = {
            "\1\1\6\uffff\1\37\6\uffff\1\1\3\uffff\7\1\1\uffff\1\1\1\uffff"+
            "\23\1\4\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA96_eot = DFA.unpackEncodedString(DFA96_eotS);
    static final short[] DFA96_eof = DFA.unpackEncodedString(DFA96_eofS);
    static final char[] DFA96_min = DFA.unpackEncodedStringToUnsignedChars(DFA96_minS);
    static final char[] DFA96_max = DFA.unpackEncodedStringToUnsignedChars(DFA96_maxS);
    static final short[] DFA96_accept = DFA.unpackEncodedString(DFA96_acceptS);
    static final short[] DFA96_special = DFA.unpackEncodedString(DFA96_specialS);
    static final short[][] DFA96_transition;

    static {
        int numStates = DFA96_transitionS.length;
        DFA96_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA96_transition[i] = DFA.unpackEncodedString(DFA96_transitionS[i]);
        }
    }

    class DFA96 extends DFA {

        public DFA96(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 96;
            this.eot = DFA96_eot;
            this.eof = DFA96_eof;
            this.min = DFA96_min;
            this.max = DFA96_max;
            this.accept = DFA96_accept;
            this.special = DFA96_special;
            this.transition = DFA96_transition;
        }
        public String getDescription() {
            return "()* loopback of 1039:8: ( S )*";
        }
    }
    static final String DFA97_eotS =
        "\26\uffff";
    static final String DFA97_eofS =
        "\26\uffff";
    static final String DFA97_minS =
        "\1\27\25\uffff";
    static final String DFA97_maxS =
        "\1\140\25\uffff";
    static final String DFA97_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25";
    static final String DFA97_specialS =
        "\26\uffff}>";
    static final String[] DFA97_transitionS = {
            "\1\4\23\uffff\1\10\2\uffff\1\17\1\20\2\uffff\1\23\1\uffff\1"+
            "\1\1\2\1\3\1\5\1\uffff\1\6\1\7\1\11\1\12\1\13\1\14\1\15\1\16"+
            "\1\21\1\uffff\1\22\6\uffff\1\4\24\uffff\1\24\1\25",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA97_eot = DFA.unpackEncodedString(DFA97_eotS);
    static final short[] DFA97_eof = DFA.unpackEncodedString(DFA97_eofS);
    static final char[] DFA97_min = DFA.unpackEncodedStringToUnsignedChars(DFA97_minS);
    static final char[] DFA97_max = DFA.unpackEncodedStringToUnsignedChars(DFA97_maxS);
    static final short[] DFA97_accept = DFA.unpackEncodedString(DFA97_acceptS);
    static final short[] DFA97_special = DFA.unpackEncodedString(DFA97_specialS);
    static final short[][] DFA97_transition;

    static {
        int numStates = DFA97_transitionS.length;
        DFA97_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA97_transition[i] = DFA.unpackEncodedString(DFA97_transitionS[i]);
        }
    }

    class DFA97 extends DFA {

        public DFA97(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 97;
            this.eot = DFA97_eot;
            this.eof = DFA97_eof;
            this.min = DFA97_min;
            this.max = DFA97_max;
            this.accept = DFA97_accept;
            this.special = DFA97_special;
            this.transition = DFA97_transition;
        }
        public String getDescription() {
            return "1043:5: ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH | RPAREN -> RPAREN | '#' | '^' )";
        }
    }
 

    public static final BitSet FOLLOW_S_in_inlinestyle201 = new BitSet(new long[]{0xFC18FA1040000000L,0x000000000000180FL});
    public static final BitSet FOLLOW_declarations_in_inlinestyle206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_inlineset_in_inlinestyle226 = new BitSet(new long[]{0xFC18FA1000000002L,0x000000000000180FL});
    public static final BitSet FOLLOW_CDO_in_stylesheet254 = new BitSet(new long[]{0xFFFCFBEFD2C00002L,0x000000018000042FL});
    public static final BitSet FOLLOW_CDC_in_stylesheet258 = new BitSet(new long[]{0xFFFCFBEFD2C00002L,0x000000018000042FL});
    public static final BitSet FOLLOW_S_in_stylesheet262 = new BitSet(new long[]{0xFFFCFBEFD2C00002L,0x000000018000042FL});
    public static final BitSet FOLLOW_nostatement_in_stylesheet266 = new BitSet(new long[]{0xFFFCFBEFD2C00002L,0x000000018000042FL});
    public static final BitSet FOLLOW_statement_in_stylesheet270 = new BitSet(new long[]{0xFFFCFBEFD2C00002L,0x000000018000042FL});
    public static final BitSet FOLLOW_ruleset_in_statement300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atstatement_in_statement304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARSET_in_atstatement315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_atstatement320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_IMPORT_in_atstatement325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_END_in_atstatement330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_page_in_atstatement335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FONTFACE_in_atstatement340 = new BitSet(new long[]{0x0000001040000000L});
    public static final BitSet FOLLOW_S_in_atstatement342 = new BitSet(new long[]{0x0000001040000000L});
    public static final BitSet FOLLOW_LCURLY_in_atstatement348 = new BitSet(new long[]{0xFC18FA2040000000L,0x000000000000180FL});
    public static final BitSet FOLLOW_S_in_atstatement350 = new BitSet(new long[]{0xFC18FA2040000000L,0x000000000000180FL});
    public static final BitSet FOLLOW_declarations_in_atstatement353 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RCURLY_in_atstatement358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MEDIA_in_atstatement371 = new BitSet(new long[]{0x0000021040000000L});
    public static final BitSet FOLLOW_S_in_atstatement373 = new BitSet(new long[]{0x0000021040000000L});
    public static final BitSet FOLLOW_media_in_atstatement376 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_LCURLY_in_atstatement382 = new BitSet(new long[]{0xFFFCEA2042800000L,0x000000018000042FL});
    public static final BitSet FOLLOW_S_in_atstatement384 = new BitSet(new long[]{0xFFFCEA2042800000L,0x000000018000042FL});
    public static final BitSet FOLLOW_ruleset_in_atstatement388 = new BitSet(new long[]{0xFFFCEA2042800000L,0x000000018000042FL});
    public static final BitSet FOLLOW_S_in_atstatement390 = new BitSet(new long[]{0xFFFCEA2042800000L,0x000000018000042FL});
    public static final BitSet FOLLOW_RCURLY_in_atstatement395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATKEYWORD_in_atstatement413 = new BitSet(new long[]{0x0000001040000000L});
    public static final BitSet FOLLOW_S_in_atstatement415 = new BitSet(new long[]{0x0000001040000000L});
    public static final BitSet FOLLOW_LCURLY_in_atstatement418 = new BitSet(new long[]{0xFFFAEA2000800000L,0x000000000000043FL});
    public static final BitSet FOLLOW_any_in_atstatement420 = new BitSet(new long[]{0xFFFAEA2000800000L,0x000000000000043FL});
    public static final BitSet FOLLOW_RCURLY_in_atstatement423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PAGE_in_page445 = new BitSet(new long[]{0x0000221040000000L});
    public static final BitSet FOLLOW_S_in_page447 = new BitSet(new long[]{0x0000221040000000L});
    public static final BitSet FOLLOW_IDENT_in_page453 = new BitSet(new long[]{0x0000001040000000L});
    public static final BitSet FOLLOW_IDENT_in_page457 = new BitSet(new long[]{0x0000220000000000L});
    public static final BitSet FOLLOW_page_pseudo_in_page459 = new BitSet(new long[]{0x0000001040000000L});
    public static final BitSet FOLLOW_page_pseudo_in_page463 = new BitSet(new long[]{0x0000001040000000L});
    public static final BitSet FOLLOW_S_in_page466 = new BitSet(new long[]{0x0000001040000000L});
    public static final BitSet FOLLOW_LCURLY_in_page474 = new BitSet(new long[]{0xFC18FE2040000000L,0x000000000000180FL});
    public static final BitSet FOLLOW_S_in_page476 = new BitSet(new long[]{0xFC18FE2040000000L,0x000000000000180FL});
    public static final BitSet FOLLOW_declarations_in_page481 = new BitSet(new long[]{0x0000042000000000L});
    public static final BitSet FOLLOW_margin_rule_in_page483 = new BitSet(new long[]{0x0000042000000000L});
    public static final BitSet FOLLOW_RCURLY_in_page488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pseudocolon_in_page_pseudo522 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_IDENT_in_page_pseudo525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARGIN_AREA_in_margin_rule536 = new BitSet(new long[]{0x0000001040000000L});
    public static final BitSet FOLLOW_S_in_margin_rule538 = new BitSet(new long[]{0x0000001040000000L});
    public static final BitSet FOLLOW_LCURLY_in_margin_rule541 = new BitSet(new long[]{0xFC18FA2040000000L,0x000000000000180FL});
    public static final BitSet FOLLOW_S_in_margin_rule543 = new BitSet(new long[]{0xFC18FA2040000000L,0x000000000000180FL});
    public static final BitSet FOLLOW_declarations_in_margin_rule546 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RCURLY_in_margin_rule548 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_margin_rule550 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_pseudo_in_inlineset573 = new BitSet(new long[]{0x0000081040000000L});
    public static final BitSet FOLLOW_S_in_inlineset575 = new BitSet(new long[]{0x0000081040000000L});
    public static final BitSet FOLLOW_COMMA_in_inlineset579 = new BitSet(new long[]{0x0000200040000000L});
    public static final BitSet FOLLOW_S_in_inlineset581 = new BitSet(new long[]{0x0000200040000000L});
    public static final BitSet FOLLOW_pseudo_in_inlineset584 = new BitSet(new long[]{0x0000081040000000L});
    public static final BitSet FOLLOW_S_in_inlineset586 = new BitSet(new long[]{0x0000081040000000L});
    public static final BitSet FOLLOW_LCURLY_in_inlineset599 = new BitSet(new long[]{0xFC18FA2000000000L,0x000000000000180FL});
    public static final BitSet FOLLOW_declarations_in_inlineset605 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RCURLY_in_inlineset610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_media637 = new BitSet(new long[]{0x0000080040000002L});
    public static final BitSet FOLLOW_S_in_media639 = new BitSet(new long[]{0x0000080040000002L});
    public static final BitSet FOLLOW_COMMA_in_media643 = new BitSet(new long[]{0x0000020040000000L});
    public static final BitSet FOLLOW_S_in_media645 = new BitSet(new long[]{0x0000020040000000L});
    public static final BitSet FOLLOW_IDENT_in_media648 = new BitSet(new long[]{0x0000080040000002L});
    public static final BitSet FOLLOW_S_in_media650 = new BitSet(new long[]{0x0000080040000002L});
    public static final BitSet FOLLOW_combined_selector_in_ruleset675 = new BitSet(new long[]{0x0000081000000000L});
    public static final BitSet FOLLOW_COMMA_in_ruleset678 = new BitSet(new long[]{0x0108220042000000L,0x0000000000000024L});
    public static final BitSet FOLLOW_S_in_ruleset680 = new BitSet(new long[]{0x0108220042000000L,0x0000000000000024L});
    public static final BitSet FOLLOW_combined_selector_in_ruleset683 = new BitSet(new long[]{0x0000081000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleset691 = new BitSet(new long[]{0xFC18FA2040000000L,0x000000000000180FL});
    public static final BitSet FOLLOW_S_in_ruleset693 = new BitSet(new long[]{0xFC18FA2040000000L,0x000000000000180FL});
    public static final BitSet FOLLOW_declarations_in_ruleset701 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleset706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_norule_in_ruleset725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_declarations747 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_declarations751 = new BitSet(new long[]{0xFC18FA0040000002L,0x000000000000180FL});
    public static final BitSet FOLLOW_S_in_declarations753 = new BitSet(new long[]{0xFC18FA0040000002L,0x000000000000180FL});
    public static final BitSet FOLLOW_declaration_in_declarations756 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_property_in_declaration788 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_declaration790 = new BitSet(new long[]{0xFFFBEA9040800002L,0x000000000000043FL});
    public static final BitSet FOLLOW_S_in_declaration792 = new BitSet(new long[]{0xFFFBEA9040800002L,0x000000000000043FL});
    public static final BitSet FOLLOW_terms_in_declaration795 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_important_in_declaration798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_noprop_in_declaration818 = new BitSet(new long[]{0xFFFAEA0000800002L,0x000000000000043FL});
    public static final BitSet FOLLOW_any_in_declaration820 = new BitSet(new long[]{0xFFFAEA0000800002L,0x000000000000043FL});
    public static final BitSet FOLLOW_EXCLAMATION_in_important846 = new BitSet(new long[]{0x0000000040000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_S_in_important848 = new BitSet(new long[]{0x0000000040000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_94_in_important851 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_important853 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_MINUS_in_property882 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_IDENT_in_property885 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_property887 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_term_in_terms915 = new BitSet(new long[]{0xFFFBAA9000800002L,0x000000000000043FL});
    public static final BitSet FOLLOW_valuepart_in_term948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_term960 = new BitSet(new long[]{0xFFFAFA2040800000L,0x000000000000043FL});
    public static final BitSet FOLLOW_S_in_term962 = new BitSet(new long[]{0xFFFAFA2040800000L,0x000000000000043FL});
    public static final BitSet FOLLOW_any_in_term966 = new BitSet(new long[]{0xFFFAFA2000800000L,0x000000000000043FL});
    public static final BitSet FOLLOW_SEMICOLON_in_term970 = new BitSet(new long[]{0xFFFAFA2040800000L,0x000000000000043FL});
    public static final BitSet FOLLOW_S_in_term972 = new BitSet(new long[]{0xFFFAFA2040800000L,0x000000000000043FL});
    public static final BitSet FOLLOW_RCURLY_in_term977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATKEYWORD_in_term989 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_term991 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_EXPRESSION_in_funct1024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_funct1033 = new BitSet(new long[]{0xFFFFAA9040800000L,0x000000000000043FL});
    public static final BitSet FOLLOW_S_in_funct1035 = new BitSet(new long[]{0xFFFFAA9040800000L,0x000000000000043FL});
    public static final BitSet FOLLOW_terms_in_funct1038 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_funct1041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1068 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_IDENT_in_valuepart1071 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_valuepart1088 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1102 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_NUMBER_in_valuepart1105 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1122 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_PERCENTAGE_in_valuepart1125 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1142 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_DIMENSION_in_valuepart1145 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_string_in_valuepart1162 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_URI_in_valuepart1176 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_HASH_in_valuepart1193 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_valuepart1207 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_INCLUDES_in_valuepart1221 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COLON_in_valuepart1235 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COMMA_in_valuepart1249 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_GREATER_in_valuepart1263 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_LESS_in_valuepart1277 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_QUESTION_in_valuepart1291 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_PERCENT_in_valuepart1305 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_EQUALS_in_valuepart1319 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_SLASH_in_valuepart1333 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_PLUS_in_valuepart1346 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_ASTERISK_in_valuepart1359 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1376 = new BitSet(new long[]{0x0003800000000000L});
    public static final BitSet FOLLOW_funct_in_valuepart1379 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_valuepart1397 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_LPAREN_in_valuepart1411 = new BitSet(new long[]{0xFFFFAA0000800000L,0x000000000000043FL});
    public static final BitSet FOLLOW_valuepart_in_valuepart1413 = new BitSet(new long[]{0xFFFFAA0000800000L,0x000000000000043FL});
    public static final BitSet FOLLOW_RPAREN_in_valuepart1416 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_LBRACE_in_valuepart1435 = new BitSet(new long[]{0xFFFBAA0000800000L,0x000000000000047FL});
    public static final BitSet FOLLOW_valuepart_in_valuepart1437 = new BitSet(new long[]{0xFFFBAA0000800000L,0x000000000000047FL});
    public static final BitSet FOLLOW_RBRACE_in_valuepart1440 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_valuepart1458 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_selector_in_combined_selector1475 = new BitSet(new long[]{0x0800000040000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_combinator_in_combined_selector1479 = new BitSet(new long[]{0x0108220002000000L,0x0000000000000024L});
    public static final BitSet FOLLOW_selector_in_combined_selector1482 = new BitSet(new long[]{0x0800000040000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_combinator1502 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_combinator1504 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_PLUS_in_combinator1514 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_combinator1516 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_combinator1526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_selector1545 = new BitSet(new long[]{0x0108220042000002L,0x0000000000000024L});
    public static final BitSet FOLLOW_ASTERISK_in_selector1549 = new BitSet(new long[]{0x0108220042000002L,0x0000000000000024L});
    public static final BitSet FOLLOW_selpart_in_selector1553 = new BitSet(new long[]{0x0108220042000002L,0x0000000000000024L});
    public static final BitSet FOLLOW_S_in_selector1556 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_selpart_in_selector1586 = new BitSet(new long[]{0x0108220042000002L,0x0000000000000024L});
    public static final BitSet FOLLOW_S_in_selector1589 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_HASH_in_selpart1636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_selpart1644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_selpart1651 = new BitSet(new long[]{0x0000020040000000L});
    public static final BitSet FOLLOW_S_in_selpart1653 = new BitSet(new long[]{0x0000020040000000L});
    public static final BitSet FOLLOW_attribute_in_selpart1656 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_RBRACE_in_selpart1658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pseudo_in_selpart1674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_SELPART_in_selpart1682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_attribute1706 = new BitSet(new long[]{0x8400000040000002L,0x0000000000000388L});
    public static final BitSet FOLLOW_S_in_attribute1708 = new BitSet(new long[]{0x8400000040000002L,0x0000000000000388L});
    public static final BitSet FOLLOW_set_in_attribute1715 = new BitSet(new long[]{0x0000020040800000L,0x0000000000000400L});
    public static final BitSet FOLLOW_S_in_attribute1739 = new BitSet(new long[]{0x0000020040800000L,0x0000000000000400L});
    public static final BitSet FOLLOW_IDENT_in_attribute1743 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_string_in_attribute1747 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_attribute1750 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_pseudocolon_in_pseudo1764 = new BitSet(new long[]{0x0002020000000000L});
    public static final BitSet FOLLOW_IDENT_in_pseudo1768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_pseudo1772 = new BitSet(new long[]{0x0010020000000000L});
    public static final BitSet FOLLOW_set_in_pseudo1774 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_pseudo1782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_pseudocolon1803 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_COLON_in_pseudocolon1805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_string0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_any1842 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_any1853 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_NUMBER_in_any1864 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_PERCENTAGE_in_any1875 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_DIMENSION_in_any1885 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_string_in_any1896 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_URI_in_any1910 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_HASH_in_any1927 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_any1941 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_INCLUDES_in_any1955 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COLON_in_any1969 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COMMA_in_any1983 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_GREATER_in_any1997 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_LESS_in_any2011 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_QUESTION_in_any2025 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_PERCENT_in_any2039 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_EQUALS_in_any2053 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_SLASH_in_any2067 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_any2081 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_MINUS_in_any2092 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_PLUS_in_any2103 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_ASTERISK_in_any2114 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_FUNCTION_in_any2131 = new BitSet(new long[]{0xFFFEEA0040800000L,0x000000000000043FL});
    public static final BitSet FOLLOW_S_in_any2133 = new BitSet(new long[]{0xFFFEEA0040800000L,0x000000000000043FL});
    public static final BitSet FOLLOW_any_in_any2136 = new BitSet(new long[]{0xFFFEEA0000800000L,0x000000000000043FL});
    public static final BitSet FOLLOW_RPAREN_in_any2139 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_any2159 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_LPAREN_in_any2173 = new BitSet(new long[]{0xFFFEEA0000800000L,0x000000000000043FL});
    public static final BitSet FOLLOW_any_in_any2175 = new BitSet(new long[]{0xFFFEEA0000800000L,0x000000000000043FL});
    public static final BitSet FOLLOW_RPAREN_in_any2178 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_LBRACE_in_any2197 = new BitSet(new long[]{0xFFFAEA0000800000L,0x000000000000047FL});
    public static final BitSet FOLLOW_any_in_any2199 = new BitSet(new long[]{0xFFFAEA0000800000L,0x000000000000047FL});
    public static final BitSet FOLLOW_RBRACE_in_any2202 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_any2220 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_RCURLY_in_nostatement2235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_nostatement2249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_noprop2272 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_NUMBER_in_noprop2285 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COMMA_in_noprop2297 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_GREATER_in_noprop2309 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_LESS_in_noprop2321 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_QUESTION_in_noprop2333 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_PERCENT_in_noprop2345 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_EQUALS_in_noprop2357 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_SLASH_in_noprop2369 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_noprop2381 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_PLUS_in_noprop2393 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_ASTERISK_in_noprop2405 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_noprop2420 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_INCLUDES_in_noprop2432 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COLON_in_noprop2444 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_STRING_CHAR_in_noprop2456 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_INVALID_TOKEN_in_noprop2468 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_noprop2481 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_NUMBER_in_norule2496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENTAGE_in_norule2509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_norule2521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_norule2534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_URI_in_norule2548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_norule2565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCLUDES_in_norule2579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_norule2593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_norule2607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_norule2621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_norule2635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENT_in_norule2649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_norule2663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_norule2677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_norule2691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_norule2704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_norule2717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_norule2731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_norule2745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_95_in_norule2759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_norule2770 = new BitSet(new long[]{0x0000000000000002L});

}