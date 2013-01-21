// $ANTLR 3.1.2 /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g 2013-01-21 09:15:30
 
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "STYLESHEET", "INLINESTYLE", "ATBLOCK", "CURLYBLOCK", "PARENBLOCK", "BRACEBLOCK", "RULE", "SELECTOR", "ELEMENT", "PSEUDO", "ADJACENT", "CHILD", "DESCENDANT", "ATTRIBUTE", "SET", "DECLARATION", "VALUE", "IMPORTANT", "IMPORT_END", "INVALID_STRING", "INVALID_SELECTOR", "INVALID_SELPART", "INVALID_DECLARATION", "INVALID_STATEMENT", "INVALID_IMPORT", "INVALID_DIRECTIVE", "S", "CDO", "CDC", "CHARSET", "IMPORT", "PAGE", "COLON", "IDENT", "LCURLY", "RCURLY", "MEDIA", "ATKEYWORD", "COMMA", "SEMICOLON", "EXCLAMATION", "MINUS", "EXPRESSION", "FUNCTION", "RPAREN", "CLASSKEYWORD", "NUMBER", "PERCENTAGE", "DIMENSION", "URI", "HASH", "UNIRANGE", "INCLUDES", "GREATER", "LESS", "QUESTION", "PERCENT", "EQUALS", "SLASH", "PLUS", "ASTERISK", "DASHMATCH", "LPAREN", "LBRACE", "RBRACE", "STRING", "STRING_CHAR", "INVALID_TOKEN", "IDENT_MACR", "STRING_MACR", "NAME_MACR", "NUMBER_MACR", "W_MACR", "URI_MACR", "APOS", "QUOT", "W_CHAR", "COMMENT", "SL_COMMENT", "NAME_START", "NAME_CHAR", "NON_ASCII", "ESCAPE_CHAR", "URI_CHAR", "NL_CHAR", "'important'", "'#'"
    };
    public static final int FUNCTION=47;
    public static final int APOS=78;
    public static final int NAME_CHAR=84;
    public static final int CLASSKEYWORD=49;
    public static final int PSEUDO=13;
    public static final int LBRACE=67;
    public static final int INVALID_STATEMENT=27;
    public static final int ATTRIBUTE=17;
    public static final int INVALID_TOKEN=71;
    public static final int EQUALS=61;
    public static final int NAME_START=83;
    public static final int NUMBER_MACR=75;
    public static final int MEDIA=40;
    public static final int NL_CHAR=88;
    public static final int CHARSET=33;
    public static final int NON_ASCII=85;
    public static final int EOF=-1;
    public static final int DECLARATION=19;
    public static final int STYLESHEET=4;
    public static final int LPAREN=66;
    public static final int ASTERISK=64;
    public static final int BRACEBLOCK=9;
    public static final int INCLUDES=56;
    public static final int RPAREN=48;
    public static final int INVALID_DIRECTIVE=29;
    public static final int IMPORT=34;
    public static final int SLASH=62;
    public static final int GREATER=57;
    public static final int SELECTOR=11;
    public static final int T__90=90;
    public static final int EXCLAMATION=44;
    public static final int ATBLOCK=6;
    public static final int COMMA=42;
    public static final int INVALID_SELPART=25;
    public static final int LESS=58;
    public static final int INVALID_DECLARATION=26;
    public static final int ELEMENT=12;
    public static final int IDENT=37;
    public static final int PLUS=63;
    public static final int UNIRANGE=55;
    public static final int DIMENSION=52;
    public static final int COMMENT=81;
    public static final int EXPRESSION=46;
    public static final int CURLYBLOCK=7;
    public static final int CHILD=15;
    public static final int ATKEYWORD=41;
    public static final int INVALID_STRING=23;
    public static final int RBRACE=68;
    public static final int PERCENT=60;
    public static final int RULE=10;
    public static final int W_CHAR=80;
    public static final int PARENBLOCK=8;
    public static final int STRING_MACR=73;
    public static final int QUOT=79;
    public static final int W_MACR=76;
    public static final int URI_CHAR=87;
    public static final int NUMBER=50;
    public static final int DESCENDANT=16;
    public static final int HASH=54;
    public static final int SET=18;
    public static final int LCURLY=38;
    public static final int NAME_MACR=74;
    public static final int SEMICOLON=43;
    public static final int S=30;
    public static final int MINUS=45;
    public static final int VALUE=20;
    public static final int CDO=31;
    public static final int CDC=32;
    public static final int PERCENTAGE=51;
    public static final int T__89=89;
    public static final int IMPORTANT=21;
    public static final int INVALID_SELECTOR=24;
    public static final int ESCAPE_CHAR=86;
    public static final int URI=53;
    public static final int COLON=36;
    public static final int STRING_CHAR=70;
    public static final int PAGE=35;
    public static final int DASHMATCH=65;
    public static final int QUESTION=59;
    public static final int ADJACENT=14;
    public static final int IMPORT_END=22;
    public static final int INVALID_IMPORT=28;
    public static final int INLINESTYLE=5;
    public static final int SL_COMMENT=82;
    public static final int RCURLY=39;
    public static final int IDENT_MACR=72;
    public static final int STRING=69;
    public static final int URI_MACR=77;

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

                        if ( (LA2_0==COLON||LA2_0==LCURLY) ) {
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:767:1: atstatement : ( CHARSET | IMPORT | INVALID_IMPORT | IMPORT_END | PAGE ( S )* ( COLON IDENT ( S )* )? LCURLY ( S )* declarations RCURLY -> ^( PAGE ( IDENT )? declarations ) | MEDIA ( S )* ( media )? LCURLY ( S )* ( ruleset ( S )* )* RCURLY -> ^( MEDIA ( media )? ( ruleset )* ) | ATKEYWORD ( S )* LCURLY ( any )* RCURLY -> INVALID_STATEMENT );
    public final CSSParser.atstatement_return atstatement() throws RecognitionException {
        CSSParser.atstatement_return retval = new CSSParser.atstatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CHARSET11=null;
        Token IMPORT12=null;
        Token INVALID_IMPORT13=null;
        Token IMPORT_END14=null;
        Token PAGE15=null;
        Token S16=null;
        Token COLON17=null;
        Token IDENT18=null;
        Token S19=null;
        Token LCURLY20=null;
        Token S21=null;
        Token RCURLY23=null;
        Token MEDIA24=null;
        Token S25=null;
        Token LCURLY27=null;
        Token S28=null;
        Token S30=null;
        Token RCURLY31=null;
        Token ATKEYWORD32=null;
        Token S33=null;
        Token LCURLY34=null;
        Token RCURLY36=null;
        CSSParser.declarations_return declarations22 = null;

        CSSParser.media_return media26 = null;

        CSSParser.ruleset_return ruleset29 = null;

        CSSParser.any_return any35 = null;


        Object CHARSET11_tree=null;
        Object IMPORT12_tree=null;
        Object INVALID_IMPORT13_tree=null;
        Object IMPORT_END14_tree=null;
        Object PAGE15_tree=null;
        Object S16_tree=null;
        Object COLON17_tree=null;
        Object IDENT18_tree=null;
        Object S19_tree=null;
        Object LCURLY20_tree=null;
        Object S21_tree=null;
        Object RCURLY23_tree=null;
        Object MEDIA24_tree=null;
        Object S25_tree=null;
        Object LCURLY27_tree=null;
        Object S28_tree=null;
        Object S30_tree=null;
        Object RCURLY31_tree=null;
        Object ATKEYWORD32_tree=null;
        Object S33_tree=null;
        Object LCURLY34_tree=null;
        Object RCURLY36_tree=null;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:768:2: ( CHARSET | IMPORT | INVALID_IMPORT | IMPORT_END | PAGE ( S )* ( COLON IDENT ( S )* )? LCURLY ( S )* declarations RCURLY -> ^( PAGE ( IDENT )? declarations ) | MEDIA ( S )* ( media )? LCURLY ( S )* ( ruleset ( S )* )* RCURLY -> ^( MEDIA ( media )? ( ruleset )* ) | ATKEYWORD ( S )* LCURLY ( any )* RCURLY -> INVALID_STATEMENT )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:772:4: PAGE ( S )* ( COLON IDENT ( S )* )? LCURLY ( S )* declarations RCURLY
                    {
                    PAGE15=(Token)match(input,PAGE,FOLLOW_PAGE_in_atstatement335);  
                    stream_PAGE.add(PAGE15);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:772:9: ( S )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==S) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:772:9: S
                    	    {
                    	    S16=(Token)match(input,S,FOLLOW_S_in_atstatement337);  
                    	    stream_S.add(S16);


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:772:12: ( COLON IDENT ( S )* )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==COLON) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:772:13: COLON IDENT ( S )*
                            {
                            COLON17=(Token)match(input,COLON,FOLLOW_COLON_in_atstatement341);  
                            stream_COLON.add(COLON17);

                            IDENT18=(Token)match(input,IDENT,FOLLOW_IDENT_in_atstatement343);  
                            stream_IDENT.add(IDENT18);

                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:772:25: ( S )*
                            loop7:
                            do {
                                int alt7=2;
                                int LA7_0 = input.LA(1);

                                if ( (LA7_0==S) ) {
                                    alt7=1;
                                }


                                switch (alt7) {
                            	case 1 :
                            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:772:25: S
                            	    {
                            	    S19=(Token)match(input,S,FOLLOW_S_in_atstatement345);  
                            	    stream_S.add(S19);


                            	    }
                            	    break;

                            	default :
                            	    break loop7;
                                }
                            } while (true);


                            }
                            break;

                    }

                    LCURLY20=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atstatement353);  
                    stream_LCURLY.add(LCURLY20);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:773:10: ( S )*
                    loop9:
                    do {
                        int alt9=2;
                        alt9 = dfa9.predict(input);
                        switch (alt9) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:773:10: S
                    	    {
                    	    S21=(Token)match(input,S,FOLLOW_S_in_atstatement355);  
                    	    stream_S.add(S21);


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);

                    pushFollow(FOLLOW_declarations_in_atstatement358);
                    declarations22=declarations();

                    state._fsp--;

                    stream_declarations.add(declarations22.getTree());
                    RCURLY23=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atstatement363);  
                    stream_RCURLY.add(RCURLY23);



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
                    // 774:10: -> ^( PAGE ( IDENT )? declarations )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:774:13: ^( PAGE ( IDENT )? declarations )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_PAGE.nextNode(), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:774:20: ( IDENT )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:775:4: MEDIA ( S )* ( media )? LCURLY ( S )* ( ruleset ( S )* )* RCURLY
                    {
                    MEDIA24=(Token)match(input,MEDIA,FOLLOW_MEDIA_in_atstatement379);  
                    stream_MEDIA.add(MEDIA24);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:775:10: ( S )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==S) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:775:10: S
                    	    {
                    	    S25=(Token)match(input,S,FOLLOW_S_in_atstatement381);  
                    	    stream_S.add(S25);


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:775:13: ( media )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==IDENT) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:775:13: media
                            {
                            pushFollow(FOLLOW_media_in_atstatement384);
                            media26=media();

                            state._fsp--;

                            stream_media.add(media26.getTree());

                            }
                            break;

                    }

                    LCURLY27=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atstatement390);  
                    stream_LCURLY.add(LCURLY27);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:776:10: ( S )*
                    loop12:
                    do {
                        int alt12=2;
                        alt12 = dfa12.predict(input);
                        switch (alt12) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:776:10: S
                    	    {
                    	    S28=(Token)match(input,S,FOLLOW_S_in_atstatement392);  
                    	    stream_S.add(S28);


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:776:13: ( ruleset ( S )* )*
                    loop14:
                    do {
                        int alt14=2;
                        alt14 = dfa14.predict(input);
                        switch (alt14) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:776:14: ruleset ( S )*
                    	    {
                    	    pushFollow(FOLLOW_ruleset_in_atstatement396);
                    	    ruleset29=ruleset();

                    	    state._fsp--;

                    	    stream_ruleset.add(ruleset29.getTree());
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:776:22: ( S )*
                    	    loop13:
                    	    do {
                    	        int alt13=2;
                    	        alt13 = dfa13.predict(input);
                    	        switch (alt13) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:776:22: S
                    	    	    {
                    	    	    S30=(Token)match(input,S,FOLLOW_S_in_atstatement398);  
                    	    	    stream_S.add(S30);


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

                    RCURLY31=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atstatement403);  
                    stream_RCURLY.add(RCURLY31);



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
                    // 776:34: -> ^( MEDIA ( media )? ( ruleset )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:776:37: ^( MEDIA ( media )? ( ruleset )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_MEDIA.nextNode(), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:776:45: ( media )?
                        if ( stream_media.hasNext() ) {
                            adaptor.addChild(root_1, stream_media.nextTree());

                        }
                        stream_media.reset();
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:776:52: ( ruleset )*
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:4: ATKEYWORD ( S )* LCURLY ( any )* RCURLY
                    {
                    ATKEYWORD32=(Token)match(input,ATKEYWORD,FOLLOW_ATKEYWORD_in_atstatement421);  
                    stream_ATKEYWORD.add(ATKEYWORD32);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:14: ( S )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==S) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:14: S
                    	    {
                    	    S33=(Token)match(input,S,FOLLOW_S_in_atstatement423);  
                    	    stream_S.add(S33);


                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);

                    LCURLY34=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atstatement426);  
                    stream_LCURLY.add(LCURLY34);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:24: ( any )*
                    loop16:
                    do {
                        int alt16=2;
                        alt16 = dfa16.predict(input);
                        switch (alt16) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:24: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_atstatement428);
                    	    any35=any();

                    	    state._fsp--;

                    	    stream_any.add(any35.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);

                    RCURLY36=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atstatement431);  
                    stream_RCURLY.add(RCURLY36);



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
                    // 777:36: -> INVALID_STATEMENT
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:785:1: inlineset : ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )? LCURLY declarations RCURLY -> ^( RULE ( pseudo )* declarations ) ;
    public final CSSParser.inlineset_return inlineset() throws RecognitionException {
        CSSParser.inlineset_return retval = new CSSParser.inlineset_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token S38=null;
        Token COMMA39=null;
        Token S40=null;
        Token S42=null;
        Token LCURLY43=null;
        Token RCURLY45=null;
        CSSParser.pseudo_return pseudo37 = null;

        CSSParser.pseudo_return pseudo41 = null;

        CSSParser.declarations_return declarations44 = null;


        Object S38_tree=null;
        Object COMMA39_tree=null;
        Object S40_tree=null;
        Object S42_tree=null;
        Object LCURLY43_tree=null;
        Object RCURLY45_tree=null;
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_pseudo=new RewriteRuleSubtreeStream(adaptor,"rule pseudo");
        RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:2: ( ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )? LCURLY declarations RCURLY -> ^( RULE ( pseudo )* declarations ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:4: ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )? LCURLY declarations RCURLY
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:4: ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==COLON) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:5: pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )*
                    {
                    pushFollow(FOLLOW_pseudo_in_inlineset456);
                    pseudo37=pseudo();

                    state._fsp--;

                    stream_pseudo.add(pseudo37.getTree());
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:12: ( S )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==S) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:12: S
                    	    {
                    	    S38=(Token)match(input,S,FOLLOW_S_in_inlineset458);  
                    	    stream_S.add(S38);


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:15: ( COMMA ( S )* pseudo ( S )* )*
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==COMMA) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:16: COMMA ( S )* pseudo ( S )*
                    	    {
                    	    COMMA39=(Token)match(input,COMMA,FOLLOW_COMMA_in_inlineset462);  
                    	    stream_COMMA.add(COMMA39);

                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:22: ( S )*
                    	    loop19:
                    	    do {
                    	        int alt19=2;
                    	        int LA19_0 = input.LA(1);

                    	        if ( (LA19_0==S) ) {
                    	            alt19=1;
                    	        }


                    	        switch (alt19) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:22: S
                    	    	    {
                    	    	    S40=(Token)match(input,S,FOLLOW_S_in_inlineset464);  
                    	    	    stream_S.add(S40);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop19;
                    	        }
                    	    } while (true);

                    	    pushFollow(FOLLOW_pseudo_in_inlineset467);
                    	    pseudo41=pseudo();

                    	    state._fsp--;

                    	    stream_pseudo.add(pseudo41.getTree());
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:32: ( S )*
                    	    loop20:
                    	    do {
                    	        int alt20=2;
                    	        int LA20_0 = input.LA(1);

                    	        if ( (LA20_0==S) ) {
                    	            alt20=1;
                    	        }


                    	        switch (alt20) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:32: S
                    	    	    {
                    	    	    S42=(Token)match(input,S,FOLLOW_S_in_inlineset469);  
                    	    	    stream_S.add(S42);


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

            LCURLY43=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_inlineset482);  
            stream_LCURLY.add(LCURLY43);

            pushFollow(FOLLOW_declarations_in_inlineset488);
            declarations44=declarations();

            state._fsp--;

            stream_declarations.add(declarations44.getTree());
            RCURLY45=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_inlineset493);  
            stream_RCURLY.add(RCURLY45);



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
            // 792:4: -> ^( RULE ( pseudo )* declarations )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:792:7: ^( RULE ( pseudo )* declarations )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(RULE, "RULE"), root_1);

                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:792:14: ( pseudo )*
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:795:1: media : IDENT ( S )* ( COMMA ( S )* IDENT ( S )* )* -> ( IDENT )+ ;
    public final CSSParser.media_return media() throws RecognitionException {
        CSSParser.media_return retval = new CSSParser.media_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT46=null;
        Token S47=null;
        Token COMMA48=null;
        Token S49=null;
        Token IDENT50=null;
        Token S51=null;

        Object IDENT46_tree=null;
        Object S47_tree=null;
        Object COMMA48_tree=null;
        Object S49_tree=null;
        Object IDENT50_tree=null;
        Object S51_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:796:2: ( IDENT ( S )* ( COMMA ( S )* IDENT ( S )* )* -> ( IDENT )+ )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:796:4: IDENT ( S )* ( COMMA ( S )* IDENT ( S )* )*
            {
            IDENT46=(Token)match(input,IDENT,FOLLOW_IDENT_in_media520);  
            stream_IDENT.add(IDENT46);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:796:10: ( S )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==S) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:796:10: S
            	    {
            	    S47=(Token)match(input,S,FOLLOW_S_in_media522);  
            	    stream_S.add(S47);


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:796:13: ( COMMA ( S )* IDENT ( S )* )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==COMMA) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:796:14: COMMA ( S )* IDENT ( S )*
            	    {
            	    COMMA48=(Token)match(input,COMMA,FOLLOW_COMMA_in_media526);  
            	    stream_COMMA.add(COMMA48);

            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:796:20: ( S )*
            	    loop24:
            	    do {
            	        int alt24=2;
            	        int LA24_0 = input.LA(1);

            	        if ( (LA24_0==S) ) {
            	            alt24=1;
            	        }


            	        switch (alt24) {
            	    	case 1 :
            	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:796:20: S
            	    	    {
            	    	    S49=(Token)match(input,S,FOLLOW_S_in_media528);  
            	    	    stream_S.add(S49);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop24;
            	        }
            	    } while (true);

            	    IDENT50=(Token)match(input,IDENT,FOLLOW_IDENT_in_media531);  
            	    stream_IDENT.add(IDENT50);

            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:796:29: ( S )*
            	    loop25:
            	    do {
            	        int alt25=2;
            	        int LA25_0 = input.LA(1);

            	        if ( (LA25_0==S) ) {
            	            alt25=1;
            	        }


            	        switch (alt25) {
            	    	case 1 :
            	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:796:29: S
            	    	    {
            	    	    S51=(Token)match(input,S,FOLLOW_S_in_media533);  
            	    	    stream_S.add(S51);


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
            // 797:3: -> ( IDENT )+
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:800:1: ruleset : ( combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY -> ^( RULE ( combined_selector )+ declarations ) | norule -> INVALID_STATEMENT );
    public final CSSParser.ruleset_return ruleset() throws RecognitionException {
        CSSParser.ruleset_return retval = new CSSParser.ruleset_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA53=null;
        Token S54=null;
        Token LCURLY56=null;
        Token S57=null;
        Token RCURLY59=null;
        CSSParser.combined_selector_return combined_selector52 = null;

        CSSParser.combined_selector_return combined_selector55 = null;

        CSSParser.declarations_return declarations58 = null;

        CSSParser.norule_return norule60 = null;


        Object COMMA53_tree=null;
        Object S54_tree=null;
        Object LCURLY56_tree=null;
        Object S57_tree=null;
        Object RCURLY59_tree=null;
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_combined_selector=new RewriteRuleSubtreeStream(adaptor,"rule combined_selector");
        RewriteRuleSubtreeStream stream_norule=new RewriteRuleSubtreeStream(adaptor,"rule norule");
        RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:801:2: ( combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY -> ^( RULE ( combined_selector )+ declarations ) | norule -> INVALID_STATEMENT )
            int alt30=2;
            alt30 = dfa30.predict(input);
            switch (alt30) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:801:4: combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY
                    {
                    pushFollow(FOLLOW_combined_selector_in_ruleset558);
                    combined_selector52=combined_selector();

                    state._fsp--;

                    stream_combined_selector.add(combined_selector52.getTree());
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:801:22: ( COMMA ( S )* combined_selector )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==COMMA) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:801:23: COMMA ( S )* combined_selector
                    	    {
                    	    COMMA53=(Token)match(input,COMMA,FOLLOW_COMMA_in_ruleset561);  
                    	    stream_COMMA.add(COMMA53);

                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:801:29: ( S )*
                    	    loop27:
                    	    do {
                    	        int alt27=2;
                    	        int LA27_0 = input.LA(1);

                    	        if ( (LA27_0==S) ) {
                    	            alt27=1;
                    	        }


                    	        switch (alt27) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:801:29: S
                    	    	    {
                    	    	    S54=(Token)match(input,S,FOLLOW_S_in_ruleset563);  
                    	    	    stream_S.add(S54);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop27;
                    	        }
                    	    } while (true);

                    	    pushFollow(FOLLOW_combined_selector_in_ruleset566);
                    	    combined_selector55=combined_selector();

                    	    state._fsp--;

                    	    stream_combined_selector.add(combined_selector55.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop28;
                        }
                    } while (true);

                    LCURLY56=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_ruleset574);  
                    stream_LCURLY.add(LCURLY56);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:802:11: ( S )*
                    loop29:
                    do {
                        int alt29=2;
                        alt29 = dfa29.predict(input);
                        switch (alt29) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:802:11: S
                    	    {
                    	    S57=(Token)match(input,S,FOLLOW_S_in_ruleset576);  
                    	    stream_S.add(S57);


                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);

                    pushFollow(FOLLOW_declarations_in_ruleset584);
                    declarations58=declarations();

                    state._fsp--;

                    stream_declarations.add(declarations58.getTree());
                    RCURLY59=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_ruleset589);  
                    stream_RCURLY.add(RCURLY59);



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
                    // 805:4: -> ^( RULE ( combined_selector )+ declarations )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:805:7: ^( RULE ( combined_selector )+ declarations )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:806:4: norule
                    {
                    pushFollow(FOLLOW_norule_in_ruleset608);
                    norule60=norule();

                    state._fsp--;

                    stream_norule.add(norule60.getTree());


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
                    // 806:11: -> INVALID_STATEMENT
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:814:1: declarations : ( declaration )? ( SEMICOLON ( S )* ( declaration )? )* -> ^( SET ( declaration )* ) ;
    public final CSSParser.declarations_return declarations() throws RecognitionException {
        CSSParser.declarations_return retval = new CSSParser.declarations_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SEMICOLON62=null;
        Token S63=null;
        CSSParser.declaration_return declaration61 = null;

        CSSParser.declaration_return declaration64 = null;


        Object SEMICOLON62_tree=null;
        Object S63_tree=null;
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleSubtreeStream stream_declaration=new RewriteRuleSubtreeStream(adaptor,"rule declaration");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:815:2: ( ( declaration )? ( SEMICOLON ( S )* ( declaration )? )* -> ^( SET ( declaration )* ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:815:4: ( declaration )? ( SEMICOLON ( S )* ( declaration )? )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:815:4: ( declaration )?
            int alt31=2;
            alt31 = dfa31.predict(input);
            switch (alt31) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:815:4: declaration
                    {
                    pushFollow(FOLLOW_declaration_in_declarations630);
                    declaration61=declaration();

                    state._fsp--;

                    stream_declaration.add(declaration61.getTree());

                    }
                    break;

            }

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:815:17: ( SEMICOLON ( S )* ( declaration )? )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==SEMICOLON) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:815:18: SEMICOLON ( S )* ( declaration )?
            	    {
            	    SEMICOLON62=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_declarations634);  
            	    stream_SEMICOLON.add(SEMICOLON62);

            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:815:28: ( S )*
            	    loop32:
            	    do {
            	        int alt32=2;
            	        alt32 = dfa32.predict(input);
            	        switch (alt32) {
            	    	case 1 :
            	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:815:28: S
            	    	    {
            	    	    S63=(Token)match(input,S,FOLLOW_S_in_declarations636);  
            	    	    stream_S.add(S63);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop32;
            	        }
            	    } while (true);

            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:815:31: ( declaration )?
            	    int alt33=2;
            	    alt33 = dfa33.predict(input);
            	    switch (alt33) {
            	        case 1 :
            	            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:815:31: declaration
            	            {
            	            pushFollow(FOLLOW_declaration_in_declarations639);
            	            declaration64=declaration();

            	            state._fsp--;

            	            stream_declaration.add(declaration64.getTree());

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
            // 816:4: -> ^( SET ( declaration )* )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:816:7: ^( SET ( declaration )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SET, "SET"), root_1);

                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:816:13: ( declaration )*
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:819:1: declaration : ( property COLON ( S )* ( terms )? ( important )? -> ^( DECLARATION ( important )? property ( terms )? ) | noprop ( any )* -> INVALID_DECLARATION );
    public final CSSParser.declaration_return declaration() throws RecognitionException {
        CSSParser.declaration_return retval = new CSSParser.declaration_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON66=null;
        Token S67=null;
        CSSParser.property_return property65 = null;

        CSSParser.terms_return terms68 = null;

        CSSParser.important_return important69 = null;

        CSSParser.noprop_return noprop70 = null;

        CSSParser.any_return any71 = null;


        Object COLON66_tree=null;
        Object S67_tree=null;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:824:2: ( property COLON ( S )* ( terms )? ( important )? -> ^( DECLARATION ( important )? property ( terms )? ) | noprop ( any )* -> INVALID_DECLARATION )
            int alt39=2;
            alt39 = dfa39.predict(input);
            switch (alt39) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:824:4: property COLON ( S )* ( terms )? ( important )?
                    {
                    pushFollow(FOLLOW_property_in_declaration671);
                    property65=property();

                    state._fsp--;

                    stream_property.add(property65.getTree());
                    COLON66=(Token)match(input,COLON,FOLLOW_COLON_in_declaration673);  
                    stream_COLON.add(COLON66);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:824:19: ( S )*
                    loop35:
                    do {
                        int alt35=2;
                        alt35 = dfa35.predict(input);
                        switch (alt35) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:824:19: S
                    	    {
                    	    S67=(Token)match(input,S,FOLLOW_S_in_declaration675);  
                    	    stream_S.add(S67);


                    	    }
                    	    break;

                    	default :
                    	    break loop35;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:824:22: ( terms )?
                    int alt36=2;
                    alt36 = dfa36.predict(input);
                    switch (alt36) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:824:22: terms
                            {
                            pushFollow(FOLLOW_terms_in_declaration678);
                            terms68=terms();

                            state._fsp--;

                            stream_terms.add(terms68.getTree());

                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:824:29: ( important )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==EXCLAMATION) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:824:29: important
                            {
                            pushFollow(FOLLOW_important_in_declaration681);
                            important69=important();

                            state._fsp--;

                            stream_important.add(important69.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: terms, property, important
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 824:40: -> ^( DECLARATION ( important )? property ( terms )? )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:824:43: ^( DECLARATION ( important )? property ( terms )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DECLARATION, "DECLARATION"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:824:57: ( important )?
                        if ( stream_important.hasNext() ) {
                            adaptor.addChild(root_1, stream_important.nextTree());

                        }
                        stream_important.reset();
                        adaptor.addChild(root_1, stream_property.nextTree());
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:824:77: ( terms )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:825:4: noprop ( any )*
                    {
                    pushFollow(FOLLOW_noprop_in_declaration701);
                    noprop70=noprop();

                    state._fsp--;

                    stream_noprop.add(noprop70.getTree());
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:825:11: ( any )*
                    loop38:
                    do {
                        int alt38=2;
                        alt38 = dfa38.predict(input);
                        switch (alt38) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:825:11: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_declaration703);
                    	    any71=any();

                    	    state._fsp--;

                    	    stream_any.add(any71.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop38;
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
                    // 825:16: -> INVALID_DECLARATION
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:833:1: important : EXCLAMATION ( S )* 'important' ( S )* -> IMPORTANT ;
    public final CSSParser.important_return important() throws RecognitionException {
        CSSParser.important_return retval = new CSSParser.important_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EXCLAMATION72=null;
        Token S73=null;
        Token string_literal74=null;
        Token S75=null;

        Object EXCLAMATION72_tree=null;
        Object S73_tree=null;
        Object string_literal74_tree=null;
        Object S75_tree=null;
        RewriteRuleTokenStream stream_EXCLAMATION=new RewriteRuleTokenStream(adaptor,"token EXCLAMATION");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_89=new RewriteRuleTokenStream(adaptor,"token 89");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:834:3: ( EXCLAMATION ( S )* 'important' ( S )* -> IMPORTANT )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:834:5: EXCLAMATION ( S )* 'important' ( S )*
            {
            EXCLAMATION72=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_important729);  
            stream_EXCLAMATION.add(EXCLAMATION72);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:834:17: ( S )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==S) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:834:17: S
            	    {
            	    S73=(Token)match(input,S,FOLLOW_S_in_important731);  
            	    stream_S.add(S73);


            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);

            string_literal74=(Token)match(input,89,FOLLOW_89_in_important734);  
            stream_89.add(string_literal74);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:834:32: ( S )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==S) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:834:32: S
            	    {
            	    S75=(Token)match(input,S,FOLLOW_S_in_important736);  
            	    stream_S.add(S75);


            	    }
            	    break;

            	default :
            	    break loop41;
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
            // 834:35: -> IMPORTANT
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:841:1: property : ( MINUS )? IDENT ( S )* -> ( MINUS )? IDENT ;
    public final CSSParser.property_return property() throws RecognitionException {
        CSSParser.property_return retval = new CSSParser.property_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MINUS76=null;
        Token IDENT77=null;
        Token S78=null;

        Object MINUS76_tree=null;
        Object IDENT77_tree=null;
        Object S78_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:2: ( ( MINUS )? IDENT ( S )* -> ( MINUS )? IDENT )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:4: ( MINUS )? IDENT ( S )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:4: ( MINUS )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==MINUS) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:4: MINUS
                    {
                    MINUS76=(Token)match(input,MINUS,FOLLOW_MINUS_in_property765);  
                    stream_MINUS.add(MINUS76);


                    }
                    break;

            }

            IDENT77=(Token)match(input,IDENT,FOLLOW_IDENT_in_property768);  
            stream_IDENT.add(IDENT77);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:17: ( S )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==S) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:17: S
            	    {
            	    S78=(Token)match(input,S,FOLLOW_S_in_property770);  
            	    stream_S.add(S78);


            	    }
            	    break;

            	default :
            	    break loop43;
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
            // 842:20: -> ( MINUS )? IDENT
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:23: ( MINUS )?
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:845:1: terms : ( term )+ -> ^( VALUE ( term )+ ) ;
    public final CSSParser.terms_return terms() throws RecognitionException {
        CSSParser.terms_return retval = new CSSParser.terms_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CSSParser.term_return term79 = null;


        RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:846:2: ( ( term )+ -> ^( VALUE ( term )+ ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:846:4: ( term )+
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:846:4: ( term )+
            int cnt44=0;
            loop44:
            do {
                int alt44=2;
                alt44 = dfa44.predict(input);
                switch (alt44) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:846:4: term
            	    {
            	    pushFollow(FOLLOW_term_in_terms798);
            	    term79=term();

            	    state._fsp--;

            	    stream_term.add(term79.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt44 >= 1 ) break loop44;
                        EarlyExitException eee =
                            new EarlyExitException(44, input);
                        throw eee;
                }
                cnt44++;
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
            // 847:2: -> ^( VALUE ( term )+ )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:847:5: ^( VALUE ( term )+ )
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:863:1: term : ( valuepart -> valuepart | LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY -> CURLYBLOCK | ATKEYWORD ( S )* -> ATKEYWORD );
    public final CSSParser.term_return term() throws RecognitionException {
        CSSParser.term_return retval = new CSSParser.term_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LCURLY81=null;
        Token S82=null;
        Token SEMICOLON84=null;
        Token S85=null;
        Token RCURLY86=null;
        Token ATKEYWORD87=null;
        Token S88=null;
        CSSParser.valuepart_return valuepart80 = null;

        CSSParser.any_return any83 = null;


        Object LCURLY81_tree=null;
        Object S82_tree=null;
        Object SEMICOLON84_tree=null;
        Object S85_tree=null;
        Object RCURLY86_tree=null;
        Object ATKEYWORD87_tree=null;
        Object S88_tree=null;
        RewriteRuleTokenStream stream_ATKEYWORD=new RewriteRuleTokenStream(adaptor,"token ATKEYWORD");
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_valuepart=new RewriteRuleSubtreeStream(adaptor,"rule valuepart");
        RewriteRuleSubtreeStream stream_any=new RewriteRuleSubtreeStream(adaptor,"rule any");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:864:5: ( valuepart -> valuepart | LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY -> CURLYBLOCK | ATKEYWORD ( S )* -> ATKEYWORD )
            int alt49=3;
            alt49 = dfa49.predict(input);
            switch (alt49) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:864:7: valuepart
                    {
                    pushFollow(FOLLOW_valuepart_in_term831);
                    valuepart80=valuepart();

                    state._fsp--;

                    stream_valuepart.add(valuepart80.getTree());


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
                    // 864:17: -> valuepart
                    {
                        adaptor.addChild(root_0, stream_valuepart.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:865:7: LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY
                    {
                    LCURLY81=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_term843);  
                    stream_LCURLY.add(LCURLY81);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:865:14: ( S )*
                    loop45:
                    do {
                        int alt45=2;
                        alt45 = dfa45.predict(input);
                        switch (alt45) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:865:14: S
                    	    {
                    	    S82=(Token)match(input,S,FOLLOW_S_in_term845);  
                    	    stream_S.add(S82);


                    	    }
                    	    break;

                    	default :
                    	    break loop45;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:865:17: ( any | SEMICOLON ( S )* )*
                    loop47:
                    do {
                        int alt47=3;
                        alt47 = dfa47.predict(input);
                        switch (alt47) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:865:18: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_term849);
                    	    any83=any();

                    	    state._fsp--;

                    	    stream_any.add(any83.getTree());

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:865:24: SEMICOLON ( S )*
                    	    {
                    	    SEMICOLON84=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_term853);  
                    	    stream_SEMICOLON.add(SEMICOLON84);

                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:865:34: ( S )*
                    	    loop46:
                    	    do {
                    	        int alt46=2;
                    	        alt46 = dfa46.predict(input);
                    	        switch (alt46) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:865:34: S
                    	    	    {
                    	    	    S85=(Token)match(input,S,FOLLOW_S_in_term855);  
                    	    	    stream_S.add(S85);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop46;
                    	        }
                    	    } while (true);


                    	    }
                    	    break;

                    	default :
                    	    break loop47;
                        }
                    } while (true);

                    RCURLY86=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_term860);  
                    stream_RCURLY.add(RCURLY86);



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
                    // 865:46: -> CURLYBLOCK
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(CURLYBLOCK, "CURLYBLOCK"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:866:7: ATKEYWORD ( S )*
                    {
                    ATKEYWORD87=(Token)match(input,ATKEYWORD,FOLLOW_ATKEYWORD_in_term872);  
                    stream_ATKEYWORD.add(ATKEYWORD87);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:866:17: ( S )*
                    loop48:
                    do {
                        int alt48=2;
                        alt48 = dfa48.predict(input);
                        switch (alt48) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:866:17: S
                    	    {
                    	    S88=(Token)match(input,S,FOLLOW_S_in_term874);  
                    	    stream_S.add(S88);


                    	    }
                    	    break;

                    	default :
                    	    break loop48;
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
                    // 866:20: -> ATKEYWORD
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:869:1: funct : ( EXPRESSION -> EXPRESSION | FUNCTION ( S )* ( terms )? RPAREN -> ^( FUNCTION ( terms )? ) );
    public final CSSParser.funct_return funct() throws RecognitionException {
        CSSParser.funct_return retval = new CSSParser.funct_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EXPRESSION89=null;
        Token FUNCTION90=null;
        Token S91=null;
        Token RPAREN93=null;
        CSSParser.terms_return terms92 = null;


        Object EXPRESSION89_tree=null;
        Object FUNCTION90_tree=null;
        Object S91_tree=null;
        Object RPAREN93_tree=null;
        RewriteRuleTokenStream stream_FUNCTION=new RewriteRuleTokenStream(adaptor,"token FUNCTION");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_EXPRESSION=new RewriteRuleTokenStream(adaptor,"token EXPRESSION");
        RewriteRuleSubtreeStream stream_terms=new RewriteRuleSubtreeStream(adaptor,"rule terms");

        	functLevel++;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:877:3: ( EXPRESSION -> EXPRESSION | FUNCTION ( S )* ( terms )? RPAREN -> ^( FUNCTION ( terms )? ) )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==EXPRESSION) ) {
                alt52=1;
            }
            else if ( (LA52_0==FUNCTION) ) {
                alt52=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;
            }
            switch (alt52) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:877:5: EXPRESSION
                    {
                    EXPRESSION89=(Token)match(input,EXPRESSION,FOLLOW_EXPRESSION_in_funct907);  
                    stream_EXPRESSION.add(EXPRESSION89);



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
                    // 877:16: -> EXPRESSION
                    {
                        adaptor.addChild(root_0, stream_EXPRESSION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:878:4: FUNCTION ( S )* ( terms )? RPAREN
                    {
                    FUNCTION90=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_funct916);  
                    stream_FUNCTION.add(FUNCTION90);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:878:13: ( S )*
                    loop50:
                    do {
                        int alt50=2;
                        alt50 = dfa50.predict(input);
                        switch (alt50) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:878:13: S
                    	    {
                    	    S91=(Token)match(input,S,FOLLOW_S_in_funct918);  
                    	    stream_S.add(S91);


                    	    }
                    	    break;

                    	default :
                    	    break loop50;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:878:16: ( terms )?
                    int alt51=2;
                    alt51 = dfa51.predict(input);
                    switch (alt51) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:878:16: terms
                            {
                            pushFollow(FOLLOW_terms_in_funct921);
                            terms92=terms();

                            state._fsp--;

                            stream_terms.add(terms92.getTree());

                            }
                            break;

                    }

                    RPAREN93=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_funct924);  
                    stream_RPAREN.add(RPAREN93);



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
                    // 878:30: -> ^( FUNCTION ( terms )? )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:878:33: ^( FUNCTION ( terms )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_FUNCTION.nextNode(), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:878:44: ( terms )?
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:881:1: valuepart : ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | ( MINUS )? funct -> ( MINUS )? funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) ) ( S )* ;
    public final CSSParser.valuepart_return valuepart() throws RecognitionException {
        CSSParser.valuepart_return retval = new CSSParser.valuepart_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MINUS94=null;
        Token IDENT95=null;
        Token CLASSKEYWORD96=null;
        Token MINUS97=null;
        Token NUMBER98=null;
        Token MINUS99=null;
        Token PERCENTAGE100=null;
        Token MINUS101=null;
        Token DIMENSION102=null;
        Token URI104=null;
        Token HASH105=null;
        Token UNIRANGE106=null;
        Token INCLUDES107=null;
        Token COLON108=null;
        Token COMMA109=null;
        Token GREATER110=null;
        Token LESS111=null;
        Token QUESTION112=null;
        Token PERCENT113=null;
        Token EQUALS114=null;
        Token SLASH115=null;
        Token PLUS116=null;
        Token ASTERISK117=null;
        Token MINUS118=null;
        Token DASHMATCH120=null;
        Token LPAREN121=null;
        Token RPAREN123=null;
        Token LBRACE124=null;
        Token RBRACE126=null;
        Token S127=null;
        CSSParser.string_return string103 = null;

        CSSParser.funct_return funct119 = null;

        CSSParser.valuepart_return valuepart122 = null;

        CSSParser.valuepart_return valuepart125 = null;


        Object MINUS94_tree=null;
        Object IDENT95_tree=null;
        Object CLASSKEYWORD96_tree=null;
        Object MINUS97_tree=null;
        Object NUMBER98_tree=null;
        Object MINUS99_tree=null;
        Object PERCENTAGE100_tree=null;
        Object MINUS101_tree=null;
        Object DIMENSION102_tree=null;
        Object URI104_tree=null;
        Object HASH105_tree=null;
        Object UNIRANGE106_tree=null;
        Object INCLUDES107_tree=null;
        Object COLON108_tree=null;
        Object COMMA109_tree=null;
        Object GREATER110_tree=null;
        Object LESS111_tree=null;
        Object QUESTION112_tree=null;
        Object PERCENT113_tree=null;
        Object EQUALS114_tree=null;
        Object SLASH115_tree=null;
        Object PLUS116_tree=null;
        Object ASTERISK117_tree=null;
        Object MINUS118_tree=null;
        Object DASHMATCH120_tree=null;
        Object LPAREN121_tree=null;
        Object RPAREN123_tree=null;
        Object LBRACE124_tree=null;
        Object RBRACE126_tree=null;
        Object S127_tree=null;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:5: ( ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | ( MINUS )? funct -> ( MINUS )? funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) ) ( S )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:7: ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | ( MINUS )? funct -> ( MINUS )? funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) ) ( S )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:7: ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | ( MINUS )? funct -> ( MINUS )? funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) )
            int alt60=24;
            alt60 = dfa60.predict(input);
            switch (alt60) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:9: ( MINUS )? IDENT
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:9: ( MINUS )?
                    int alt53=2;
                    int LA53_0 = input.LA(1);

                    if ( (LA53_0==MINUS) ) {
                        alt53=1;
                    }
                    switch (alt53) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:9: MINUS
                            {
                            MINUS94=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart951);  
                            stream_MINUS.add(MINUS94);


                            }
                            break;

                    }

                    IDENT95=(Token)match(input,IDENT,FOLLOW_IDENT_in_valuepart954);  
                    stream_IDENT.add(IDENT95);



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
                    // 883:22: -> ( MINUS )? IDENT
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:25: ( MINUS )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:884:9: CLASSKEYWORD
                    {
                    CLASSKEYWORD96=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_valuepart971);  
                    stream_CLASSKEYWORD.add(CLASSKEYWORD96);



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
                    // 884:22: -> CLASSKEYWORD
                    {
                        adaptor.addChild(root_0, stream_CLASSKEYWORD.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:885:9: ( MINUS )? NUMBER
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:885:9: ( MINUS )?
                    int alt54=2;
                    int LA54_0 = input.LA(1);

                    if ( (LA54_0==MINUS) ) {
                        alt54=1;
                    }
                    switch (alt54) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:885:9: MINUS
                            {
                            MINUS97=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart985);  
                            stream_MINUS.add(MINUS97);


                            }
                            break;

                    }

                    NUMBER98=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_valuepart988);  
                    stream_NUMBER.add(NUMBER98);



                    // AST REWRITE
                    // elements: MINUS, NUMBER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 885:23: -> ( MINUS )? NUMBER
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:885:26: ( MINUS )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:886:9: ( MINUS )? PERCENTAGE
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:886:9: ( MINUS )?
                    int alt55=2;
                    int LA55_0 = input.LA(1);

                    if ( (LA55_0==MINUS) ) {
                        alt55=1;
                    }
                    switch (alt55) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:886:9: MINUS
                            {
                            MINUS99=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart1005);  
                            stream_MINUS.add(MINUS99);


                            }
                            break;

                    }

                    PERCENTAGE100=(Token)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_valuepart1008);  
                    stream_PERCENTAGE.add(PERCENTAGE100);



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
                    // 886:27: -> ( MINUS )? PERCENTAGE
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:886:30: ( MINUS )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:887:9: ( MINUS )? DIMENSION
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:887:9: ( MINUS )?
                    int alt56=2;
                    int LA56_0 = input.LA(1);

                    if ( (LA56_0==MINUS) ) {
                        alt56=1;
                    }
                    switch (alt56) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:887:9: MINUS
                            {
                            MINUS101=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart1025);  
                            stream_MINUS.add(MINUS101);


                            }
                            break;

                    }

                    DIMENSION102=(Token)match(input,DIMENSION,FOLLOW_DIMENSION_in_valuepart1028);  
                    stream_DIMENSION.add(DIMENSION102);



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
                    // 887:26: -> ( MINUS )? DIMENSION
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:887:29: ( MINUS )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:888:9: string
                    {
                    pushFollow(FOLLOW_string_in_valuepart1045);
                    string103=string();

                    state._fsp--;

                    stream_string.add(string103.getTree());


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
                    // 888:16: -> string
                    {
                        adaptor.addChild(root_0, stream_string.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:889:9: URI
                    {
                    URI104=(Token)match(input,URI,FOLLOW_URI_in_valuepart1059);  
                    stream_URI.add(URI104);



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
                    // 889:16: -> URI
                    {
                        adaptor.addChild(root_0, stream_URI.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:890:9: HASH
                    {
                    HASH105=(Token)match(input,HASH,FOLLOW_HASH_in_valuepart1076);  
                    stream_HASH.add(HASH105);



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
                    // 890:14: -> HASH
                    {
                        adaptor.addChild(root_0, stream_HASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:891:9: UNIRANGE
                    {
                    UNIRANGE106=(Token)match(input,UNIRANGE,FOLLOW_UNIRANGE_in_valuepart1090);  
                    stream_UNIRANGE.add(UNIRANGE106);



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
                    // 891:18: -> UNIRANGE
                    {
                        adaptor.addChild(root_0, stream_UNIRANGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:892:9: INCLUDES
                    {
                    INCLUDES107=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_valuepart1104);  
                    stream_INCLUDES.add(INCLUDES107);



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
                    // 892:18: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:893:9: COLON
                    {
                    COLON108=(Token)match(input,COLON,FOLLOW_COLON_in_valuepart1118);  
                    stream_COLON.add(COLON108);



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
                    // 893:15: -> COLON
                    {
                        adaptor.addChild(root_0, stream_COLON.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:894:9: COMMA
                    {
                    COMMA109=(Token)match(input,COMMA,FOLLOW_COMMA_in_valuepart1132);  
                    stream_COMMA.add(COMMA109);



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
                    // 894:15: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:895:9: GREATER
                    {
                    GREATER110=(Token)match(input,GREATER,FOLLOW_GREATER_in_valuepart1146);  
                    stream_GREATER.add(GREATER110);



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
                    // 895:17: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:896:9: LESS
                    {
                    LESS111=(Token)match(input,LESS,FOLLOW_LESS_in_valuepart1160);  
                    stream_LESS.add(LESS111);



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
                    // 896:14: -> LESS
                    {
                        adaptor.addChild(root_0, stream_LESS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:897:9: QUESTION
                    {
                    QUESTION112=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_valuepart1174);  
                    stream_QUESTION.add(QUESTION112);



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
                    // 897:18: -> QUESTION
                    {
                        adaptor.addChild(root_0, stream_QUESTION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:898:9: PERCENT
                    {
                    PERCENT113=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_valuepart1188);  
                    stream_PERCENT.add(PERCENT113);



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
                    // 898:17: -> PERCENT
                    {
                        adaptor.addChild(root_0, stream_PERCENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:899:9: EQUALS
                    {
                    EQUALS114=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_valuepart1202);  
                    stream_EQUALS.add(EQUALS114);



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
                    // 899:16: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 18 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:900:9: SLASH
                    {
                    SLASH115=(Token)match(input,SLASH,FOLLOW_SLASH_in_valuepart1216);  
                    stream_SLASH.add(SLASH115);



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
                    // 900:15: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 19 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:901:8: PLUS
                    {
                    PLUS116=(Token)match(input,PLUS,FOLLOW_PLUS_in_valuepart1229);  
                    stream_PLUS.add(PLUS116);



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
                    // 901:13: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 20 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:902:8: ASTERISK
                    {
                    ASTERISK117=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_valuepart1242);  
                    stream_ASTERISK.add(ASTERISK117);



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
                    // 902:17: -> ASTERISK
                    {
                        adaptor.addChild(root_0, stream_ASTERISK.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 21 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:903:9: ( MINUS )? funct
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:903:9: ( MINUS )?
                    int alt57=2;
                    int LA57_0 = input.LA(1);

                    if ( (LA57_0==MINUS) ) {
                        alt57=1;
                    }
                    switch (alt57) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:903:9: MINUS
                            {
                            MINUS118=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart1259);  
                            stream_MINUS.add(MINUS118);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_funct_in_valuepart1262);
                    funct119=funct();

                    state._fsp--;

                    stream_funct.add(funct119.getTree());


                    // AST REWRITE
                    // elements: MINUS, funct
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 903:22: -> ( MINUS )? funct
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:903:25: ( MINUS )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:904:9: DASHMATCH
                    {
                    DASHMATCH120=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_valuepart1280);  
                    stream_DASHMATCH.add(DASHMATCH120);



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
                    // 904:19: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 23 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:905:9: LPAREN ( valuepart )* RPAREN
                    {
                    LPAREN121=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_valuepart1294);  
                    stream_LPAREN.add(LPAREN121);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:905:16: ( valuepart )*
                    loop58:
                    do {
                        int alt58=2;
                        alt58 = dfa58.predict(input);
                        switch (alt58) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:905:16: valuepart
                    	    {
                    	    pushFollow(FOLLOW_valuepart_in_valuepart1296);
                    	    valuepart122=valuepart();

                    	    state._fsp--;

                    	    stream_valuepart.add(valuepart122.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop58;
                        }
                    } while (true);

                    RPAREN123=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_valuepart1299);  
                    stream_RPAREN.add(RPAREN123);



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
                    // 905:34: -> ^( PARENBLOCK ( valuepart )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:905:37: ^( PARENBLOCK ( valuepart )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PARENBLOCK, "PARENBLOCK"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:905:50: ( valuepart )*
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:906:9: LBRACE ( valuepart )* RBRACE
                    {
                    LBRACE124=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_valuepart1318);  
                    stream_LBRACE.add(LBRACE124);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:906:16: ( valuepart )*
                    loop59:
                    do {
                        int alt59=2;
                        alt59 = dfa59.predict(input);
                        switch (alt59) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:906:16: valuepart
                    	    {
                    	    pushFollow(FOLLOW_valuepart_in_valuepart1320);
                    	    valuepart125=valuepart();

                    	    state._fsp--;

                    	    stream_valuepart.add(valuepart125.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop59;
                        }
                    } while (true);

                    RBRACE126=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_valuepart1323);  
                    stream_RBRACE.add(RBRACE126);



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
                    // 906:34: -> ^( BRACEBLOCK ( valuepart )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:906:37: ^( BRACEBLOCK ( valuepart )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BRACEBLOCK, "BRACEBLOCK"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:906:50: ( valuepart )*
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:907:8: ( S )*
            loop61:
            do {
                int alt61=2;
                alt61 = dfa61.predict(input);
                switch (alt61) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:907:8: S
            	    {
            	    S127=(Token)match(input,S,FOLLOW_S_in_valuepart1341);  
            	    stream_S.add(S127);


            	    }
            	    break;

            	default :
            	    break loop61;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:910:1: combined_selector : selector ( ( combinator ) selector )* ;
    public final CSSParser.combined_selector_return combined_selector() throws RecognitionException {
        CSSParser.combined_selector_return retval = new CSSParser.combined_selector_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CSSParser.selector_return selector128 = null;

        CSSParser.combinator_return combinator129 = null;

        CSSParser.selector_return selector130 = null;



        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:911:2: ( selector ( ( combinator ) selector )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:911:4: selector ( ( combinator ) selector )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_selector_in_combined_selector1358);
            selector128=selector();

            state._fsp--;

            adaptor.addChild(root_0, selector128.getTree());
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:911:13: ( ( combinator ) selector )*
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0==S||LA62_0==GREATER||LA62_0==PLUS) ) {
                    alt62=1;
                }


                switch (alt62) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:911:14: ( combinator ) selector
            	    {
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:911:14: ( combinator )
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:911:15: combinator
            	    {
            	    pushFollow(FOLLOW_combinator_in_combined_selector1362);
            	    combinator129=combinator();

            	    state._fsp--;

            	    adaptor.addChild(root_0, combinator129.getTree());

            	    }

            	    pushFollow(FOLLOW_selector_in_combined_selector1365);
            	    selector130=selector();

            	    state._fsp--;

            	    adaptor.addChild(root_0, selector130.getTree());

            	    }
            	    break;

            	default :
            	    break loop62;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:919:1: combinator : ( GREATER ( S )* -> CHILD | PLUS ( S )* -> ADJACENT | S -> DESCENDANT );
    public final CSSParser.combinator_return combinator() throws RecognitionException {
        CSSParser.combinator_return retval = new CSSParser.combinator_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token GREATER131=null;
        Token S132=null;
        Token PLUS133=null;
        Token S134=null;
        Token S135=null;

        Object GREATER131_tree=null;
        Object S132_tree=null;
        Object PLUS133_tree=null;
        Object S134_tree=null;
        Object S135_tree=null;
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:920:2: ( GREATER ( S )* -> CHILD | PLUS ( S )* -> ADJACENT | S -> DESCENDANT )
            int alt65=3;
            switch ( input.LA(1) ) {
            case GREATER:
                {
                alt65=1;
                }
                break;
            case PLUS:
                {
                alt65=2;
                }
                break;
            case S:
                {
                alt65=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;
            }

            switch (alt65) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:920:4: GREATER ( S )*
                    {
                    GREATER131=(Token)match(input,GREATER,FOLLOW_GREATER_in_combinator1385);  
                    stream_GREATER.add(GREATER131);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:920:12: ( S )*
                    loop63:
                    do {
                        int alt63=2;
                        int LA63_0 = input.LA(1);

                        if ( (LA63_0==S) ) {
                            alt63=1;
                        }


                        switch (alt63) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:920:12: S
                    	    {
                    	    S132=(Token)match(input,S,FOLLOW_S_in_combinator1387);  
                    	    stream_S.add(S132);


                    	    }
                    	    break;

                    	default :
                    	    break loop63;
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
                    // 920:15: -> CHILD
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(CHILD, "CHILD"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:921:4: PLUS ( S )*
                    {
                    PLUS133=(Token)match(input,PLUS,FOLLOW_PLUS_in_combinator1397);  
                    stream_PLUS.add(PLUS133);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:921:9: ( S )*
                    loop64:
                    do {
                        int alt64=2;
                        int LA64_0 = input.LA(1);

                        if ( (LA64_0==S) ) {
                            alt64=1;
                        }


                        switch (alt64) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:921:9: S
                    	    {
                    	    S134=(Token)match(input,S,FOLLOW_S_in_combinator1399);  
                    	    stream_S.add(S134);


                    	    }
                    	    break;

                    	default :
                    	    break loop64;
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
                    // 921:12: -> ADJACENT
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(ADJACENT, "ADJACENT"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:922:4: S
                    {
                    S135=(Token)match(input,S,FOLLOW_S_in_combinator1409);  
                    stream_S.add(S135);



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
                    // 922:6: -> DESCENDANT
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:925:1: selector : ( ( IDENT | ASTERISK ) ( selpart )* ( S )* -> ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* ) | ( selpart )+ ( S )* -> ^( SELECTOR ( selpart )+ ) );
    public final CSSParser.selector_return selector() throws RecognitionException {
        CSSParser.selector_return retval = new CSSParser.selector_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT136=null;
        Token ASTERISK137=null;
        Token S139=null;
        Token S141=null;
        CSSParser.selpart_return selpart138 = null;

        CSSParser.selpart_return selpart140 = null;


        Object IDENT136_tree=null;
        Object ASTERISK137_tree=null;
        Object S139_tree=null;
        Object S141_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_ASTERISK=new RewriteRuleTokenStream(adaptor,"token ASTERISK");
        RewriteRuleSubtreeStream stream_selpart=new RewriteRuleSubtreeStream(adaptor,"rule selpart");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:926:5: ( ( IDENT | ASTERISK ) ( selpart )* ( S )* -> ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* ) | ( selpart )+ ( S )* -> ^( SELECTOR ( selpart )+ ) )
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==IDENT||LA71_0==ASTERISK) ) {
                alt71=1;
            }
            else if ( (LA71_0==INVALID_SELPART||LA71_0==COLON||LA71_0==CLASSKEYWORD||LA71_0==HASH||LA71_0==LBRACE) ) {
                alt71=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;
            }
            switch (alt71) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:926:7: ( IDENT | ASTERISK ) ( selpart )* ( S )*
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:926:7: ( IDENT | ASTERISK )
                    int alt66=2;
                    int LA66_0 = input.LA(1);

                    if ( (LA66_0==IDENT) ) {
                        alt66=1;
                    }
                    else if ( (LA66_0==ASTERISK) ) {
                        alt66=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 66, 0, input);

                        throw nvae;
                    }
                    switch (alt66) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:926:8: IDENT
                            {
                            IDENT136=(Token)match(input,IDENT,FOLLOW_IDENT_in_selector1428);  
                            stream_IDENT.add(IDENT136);


                            }
                            break;
                        case 2 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:926:16: ASTERISK
                            {
                            ASTERISK137=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_selector1432);  
                            stream_ASTERISK.add(ASTERISK137);


                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:926:27: ( selpart )*
                    loop67:
                    do {
                        int alt67=2;
                        alt67 = dfa67.predict(input);
                        switch (alt67) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:926:27: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1436);
                    	    selpart138=selpart();

                    	    state._fsp--;

                    	    stream_selpart.add(selpart138.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop67;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:926:36: ( S )*
                    loop68:
                    do {
                        int alt68=2;
                        alt68 = dfa68.predict(input);
                        switch (alt68) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:926:36: S
                    	    {
                    	    S139=(Token)match(input,S,FOLLOW_S_in_selector1439);  
                    	    stream_S.add(S139);


                    	    }
                    	    break;

                    	default :
                    	    break loop68;
                        }
                    } while (true);



                    // AST REWRITE
                    // elements: IDENT, selpart
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 927:6: -> ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:927:9: ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SELECTOR, "SELECTOR"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:927:20: ^( ELEMENT ( IDENT )? )
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ELEMENT, "ELEMENT"), root_2);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:927:30: ( IDENT )?
                        if ( stream_IDENT.hasNext() ) {
                            adaptor.addChild(root_2, stream_IDENT.nextNode());

                        }
                        stream_IDENT.reset();

                        adaptor.addChild(root_1, root_2);
                        }
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:927:38: ( selpart )*
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:928:7: ( selpart )+ ( S )*
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:928:7: ( selpart )+
                    int cnt69=0;
                    loop69:
                    do {
                        int alt69=2;
                        alt69 = dfa69.predict(input);
                        switch (alt69) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:928:7: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1469);
                    	    selpart140=selpart();

                    	    state._fsp--;

                    	    stream_selpart.add(selpart140.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt69 >= 1 ) break loop69;
                                EarlyExitException eee =
                                    new EarlyExitException(69, input);
                                throw eee;
                        }
                        cnt69++;
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:928:16: ( S )*
                    loop70:
                    do {
                        int alt70=2;
                        alt70 = dfa70.predict(input);
                        switch (alt70) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:928:16: S
                    	    {
                    	    S141=(Token)match(input,S,FOLLOW_S_in_selector1472);  
                    	    stream_S.add(S141);


                    	    }
                    	    break;

                    	default :
                    	    break loop70;
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
                    // 929:9: -> ^( SELECTOR ( selpart )+ )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:929:12: ^( SELECTOR ( selpart )+ )
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:935:1: selpart : ( HASH | CLASSKEYWORD | LBRACE ( S )* attribute RBRACE -> ^( ATTRIBUTE attribute ) | pseudo | INVALID_SELPART );
    public final CSSParser.selpart_return selpart() throws RecognitionException {
        CSSParser.selpart_return retval = new CSSParser.selpart_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token HASH142=null;
        Token CLASSKEYWORD143=null;
        Token LBRACE144=null;
        Token S145=null;
        Token RBRACE147=null;
        Token INVALID_SELPART149=null;
        CSSParser.attribute_return attribute146 = null;

        CSSParser.pseudo_return pseudo148 = null;


        Object HASH142_tree=null;
        Object CLASSKEYWORD143_tree=null;
        Object LBRACE144_tree=null;
        Object S145_tree=null;
        Object RBRACE147_tree=null;
        Object INVALID_SELPART149_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleSubtreeStream stream_attribute=new RewriteRuleSubtreeStream(adaptor,"rule attribute");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:936:5: ( HASH | CLASSKEYWORD | LBRACE ( S )* attribute RBRACE -> ^( ATTRIBUTE attribute ) | pseudo | INVALID_SELPART )
            int alt73=5;
            switch ( input.LA(1) ) {
            case HASH:
                {
                alt73=1;
                }
                break;
            case CLASSKEYWORD:
                {
                alt73=2;
                }
                break;
            case LBRACE:
                {
                alt73=3;
                }
                break;
            case COLON:
                {
                alt73=4;
                }
                break;
            case INVALID_SELPART:
                {
                alt73=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 73, 0, input);

                throw nvae;
            }

            switch (alt73) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:936:8: HASH
                    {
                    root_0 = (Object)adaptor.nil();

                    HASH142=(Token)match(input,HASH,FOLLOW_HASH_in_selpart1519); 
                    HASH142_tree = (Object)adaptor.create(HASH142);
                    adaptor.addChild(root_0, HASH142_tree);


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:937:7: CLASSKEYWORD
                    {
                    root_0 = (Object)adaptor.nil();

                    CLASSKEYWORD143=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_selpart1527); 
                    CLASSKEYWORD143_tree = (Object)adaptor.create(CLASSKEYWORD143);
                    adaptor.addChild(root_0, CLASSKEYWORD143_tree);


                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:938:6: LBRACE ( S )* attribute RBRACE
                    {
                    LBRACE144=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_selpart1534);  
                    stream_LBRACE.add(LBRACE144);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:938:13: ( S )*
                    loop72:
                    do {
                        int alt72=2;
                        int LA72_0 = input.LA(1);

                        if ( (LA72_0==S) ) {
                            alt72=1;
                        }


                        switch (alt72) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:938:13: S
                    	    {
                    	    S145=(Token)match(input,S,FOLLOW_S_in_selpart1536);  
                    	    stream_S.add(S145);


                    	    }
                    	    break;

                    	default :
                    	    break loop72;
                        }
                    } while (true);

                    pushFollow(FOLLOW_attribute_in_selpart1539);
                    attribute146=attribute();

                    state._fsp--;

                    stream_attribute.add(attribute146.getTree());
                    RBRACE147=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_selpart1541);  
                    stream_RBRACE.add(RBRACE147);



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
                    // 938:33: -> ^( ATTRIBUTE attribute )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:938:36: ^( ATTRIBUTE attribute )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:939:7: pseudo
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pseudo_in_selpart1557);
                    pseudo148=pseudo();

                    state._fsp--;

                    adaptor.addChild(root_0, pseudo148.getTree());

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:940:7: INVALID_SELPART
                    {
                    root_0 = (Object)adaptor.nil();

                    INVALID_SELPART149=(Token)match(input,INVALID_SELPART,FOLLOW_INVALID_SELPART_in_selpart1565); 
                    INVALID_SELPART149_tree = (Object)adaptor.create(INVALID_SELPART149);
                    adaptor.addChild(root_0, INVALID_SELPART149_tree);


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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:946:1: attribute : IDENT ( S )* ( ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )* )? ;
    public final CSSParser.attribute_return attribute() throws RecognitionException {
        CSSParser.attribute_return retval = new CSSParser.attribute_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT150=null;
        Token S151=null;
        Token set152=null;
        Token S153=null;
        Token IDENT154=null;
        Token S156=null;
        CSSParser.string_return string155 = null;


        Object IDENT150_tree=null;
        Object S151_tree=null;
        Object set152_tree=null;
        Object S153_tree=null;
        Object IDENT154_tree=null;
        Object S156_tree=null;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:947:2: ( IDENT ( S )* ( ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )* )? )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:947:4: IDENT ( S )* ( ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )* )?
            {
            root_0 = (Object)adaptor.nil();

            IDENT150=(Token)match(input,IDENT,FOLLOW_IDENT_in_attribute1589); 
            IDENT150_tree = (Object)adaptor.create(IDENT150);
            adaptor.addChild(root_0, IDENT150_tree);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:947:10: ( S )*
            loop74:
            do {
                int alt74=2;
                int LA74_0 = input.LA(1);

                if ( (LA74_0==S) ) {
                    alt74=1;
                }


                switch (alt74) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:947:10: S
            	    {
            	    S151=(Token)match(input,S,FOLLOW_S_in_attribute1591); 
            	    S151_tree = (Object)adaptor.create(S151);
            	    adaptor.addChild(root_0, S151_tree);


            	    }
            	    break;

            	default :
            	    break loop74;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:948:4: ( ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )* )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==INCLUDES||LA78_0==EQUALS||LA78_0==DASHMATCH) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:948:5: ( EQUALS | INCLUDES | DASHMATCH ) ( S )* ( IDENT | string ) ( S )*
                    {
                    set152=(Token)input.LT(1);
                    if ( input.LA(1)==INCLUDES||input.LA(1)==EQUALS||input.LA(1)==DASHMATCH ) {
                        input.consume();
                        adaptor.addChild(root_0, (Object)adaptor.create(set152));
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:948:37: ( S )*
                    loop75:
                    do {
                        int alt75=2;
                        int LA75_0 = input.LA(1);

                        if ( (LA75_0==S) ) {
                            alt75=1;
                        }


                        switch (alt75) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:948:37: S
                    	    {
                    	    S153=(Token)match(input,S,FOLLOW_S_in_attribute1610); 
                    	    S153_tree = (Object)adaptor.create(S153);
                    	    adaptor.addChild(root_0, S153_tree);


                    	    }
                    	    break;

                    	default :
                    	    break loop75;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:948:40: ( IDENT | string )
                    int alt76=2;
                    int LA76_0 = input.LA(1);

                    if ( (LA76_0==IDENT) ) {
                        alt76=1;
                    }
                    else if ( (LA76_0==INVALID_STRING||LA76_0==STRING) ) {
                        alt76=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 76, 0, input);

                        throw nvae;
                    }
                    switch (alt76) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:948:41: IDENT
                            {
                            IDENT154=(Token)match(input,IDENT,FOLLOW_IDENT_in_attribute1614); 
                            IDENT154_tree = (Object)adaptor.create(IDENT154);
                            adaptor.addChild(root_0, IDENT154_tree);


                            }
                            break;
                        case 2 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:948:49: string
                            {
                            pushFollow(FOLLOW_string_in_attribute1618);
                            string155=string();

                            state._fsp--;

                            adaptor.addChild(root_0, string155.getTree());

                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:948:57: ( S )*
                    loop77:
                    do {
                        int alt77=2;
                        int LA77_0 = input.LA(1);

                        if ( (LA77_0==S) ) {
                            alt77=1;
                        }


                        switch (alt77) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:948:57: S
                    	    {
                    	    S156=(Token)match(input,S,FOLLOW_S_in_attribute1621); 
                    	    S156_tree = (Object)adaptor.create(S156);
                    	    adaptor.addChild(root_0, S156_tree);


                    	    }
                    	    break;

                    	default :
                    	    break loop77;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:951:1: pseudo : pseudocolon ( IDENT | FUNCTION ( IDENT | NUMBER ) RPAREN ) ;
    public final CSSParser.pseudo_return pseudo() throws RecognitionException {
        CSSParser.pseudo_return retval = new CSSParser.pseudo_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT158=null;
        Token FUNCTION159=null;
        Token set160=null;
        Token RPAREN161=null;
        CSSParser.pseudocolon_return pseudocolon157 = null;


        Object IDENT158_tree=null;
        Object FUNCTION159_tree=null;
        Object set160_tree=null;
        Object RPAREN161_tree=null;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:952:2: ( pseudocolon ( IDENT | FUNCTION ( IDENT | NUMBER ) RPAREN ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:952:4: pseudocolon ( IDENT | FUNCTION ( IDENT | NUMBER ) RPAREN )
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_pseudocolon_in_pseudo1635);
            pseudocolon157=pseudocolon();

            state._fsp--;

            root_0 = (Object)adaptor.becomeRoot(pseudocolon157.getTree(), root_0);
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:952:17: ( IDENT | FUNCTION ( IDENT | NUMBER ) RPAREN )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:952:18: IDENT
                    {
                    IDENT158=(Token)match(input,IDENT,FOLLOW_IDENT_in_pseudo1639); 
                    IDENT158_tree = (Object)adaptor.create(IDENT158);
                    adaptor.addChild(root_0, IDENT158_tree);


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:952:26: FUNCTION ( IDENT | NUMBER ) RPAREN
                    {
                    FUNCTION159=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_pseudo1643); 
                    FUNCTION159_tree = (Object)adaptor.create(FUNCTION159);
                    adaptor.addChild(root_0, FUNCTION159_tree);

                    set160=(Token)input.LT(1);
                    if ( input.LA(1)==IDENT||input.LA(1)==NUMBER ) {
                        input.consume();
                        adaptor.addChild(root_0, (Object)adaptor.create(set160));
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    RPAREN161=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_pseudo1653); 

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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:958:1: pseudocolon : COLON ( COLON )? -> PSEUDO ;
    public final CSSParser.pseudocolon_return pseudocolon() throws RecognitionException {
        CSSParser.pseudocolon_return retval = new CSSParser.pseudocolon_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON162=null;
        Token COLON163=null;

        Object COLON162_tree=null;
        Object COLON163_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:959:2: ( COLON ( COLON )? -> PSEUDO )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:959:4: COLON ( COLON )?
            {
            COLON162=(Token)match(input,COLON,FOLLOW_COLON_in_pseudocolon1674);  
            stream_COLON.add(COLON162);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:959:10: ( COLON )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==COLON) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:959:10: COLON
                    {
                    COLON163=(Token)match(input,COLON,FOLLOW_COLON_in_pseudocolon1676);  
                    stream_COLON.add(COLON163);


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
            // 959:17: -> PSEUDO
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:962:1: string : ( STRING | INVALID_STRING );
    public final CSSParser.string_return string() throws RecognitionException {
        CSSParser.string_return retval = new CSSParser.string_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set164=null;

        Object set164_tree=null;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:963:2: ( STRING | INVALID_STRING )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:
            {
            root_0 = (Object)adaptor.nil();

            set164=(Token)input.LT(1);
            if ( input.LA(1)==INVALID_STRING||input.LA(1)==STRING ) {
                input.consume();
                adaptor.addChild(root_0, (Object)adaptor.create(set164));
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:968:1: any : ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) ) ( S )* ;
    public final CSSParser.any_return any() throws RecognitionException {
        CSSParser.any_return retval = new CSSParser.any_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT165=null;
        Token CLASSKEYWORD166=null;
        Token NUMBER167=null;
        Token PERCENTAGE168=null;
        Token DIMENSION169=null;
        Token URI171=null;
        Token HASH172=null;
        Token UNIRANGE173=null;
        Token INCLUDES174=null;
        Token COLON175=null;
        Token COMMA176=null;
        Token GREATER177=null;
        Token LESS178=null;
        Token QUESTION179=null;
        Token PERCENT180=null;
        Token EQUALS181=null;
        Token SLASH182=null;
        Token EXCLAMATION183=null;
        Token MINUS184=null;
        Token PLUS185=null;
        Token ASTERISK186=null;
        Token FUNCTION187=null;
        Token S188=null;
        Token RPAREN190=null;
        Token DASHMATCH191=null;
        Token LPAREN192=null;
        Token RPAREN194=null;
        Token LBRACE195=null;
        Token RBRACE197=null;
        Token S198=null;
        CSSParser.string_return string170 = null;

        CSSParser.any_return any189 = null;

        CSSParser.any_return any193 = null;

        CSSParser.any_return any196 = null;


        Object IDENT165_tree=null;
        Object CLASSKEYWORD166_tree=null;
        Object NUMBER167_tree=null;
        Object PERCENTAGE168_tree=null;
        Object DIMENSION169_tree=null;
        Object URI171_tree=null;
        Object HASH172_tree=null;
        Object UNIRANGE173_tree=null;
        Object INCLUDES174_tree=null;
        Object COLON175_tree=null;
        Object COMMA176_tree=null;
        Object GREATER177_tree=null;
        Object LESS178_tree=null;
        Object QUESTION179_tree=null;
        Object PERCENT180_tree=null;
        Object EQUALS181_tree=null;
        Object SLASH182_tree=null;
        Object EXCLAMATION183_tree=null;
        Object MINUS184_tree=null;
        Object PLUS185_tree=null;
        Object ASTERISK186_tree=null;
        Object FUNCTION187_tree=null;
        Object S188_tree=null;
        Object RPAREN190_tree=null;
        Object DASHMATCH191_tree=null;
        Object LPAREN192_tree=null;
        Object RPAREN194_tree=null;
        Object LBRACE195_tree=null;
        Object RBRACE197_tree=null;
        Object S198_tree=null;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:969:2: ( ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) ) ( S )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:969:4: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) ) ( S )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:969:4: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) )
            int alt85=26;
            alt85 = dfa85.predict(input);
            switch (alt85) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:969:6: IDENT
                    {
                    IDENT165=(Token)match(input,IDENT,FOLLOW_IDENT_in_any1713);  
                    stream_IDENT.add(IDENT165);



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
                    // 969:12: -> IDENT
                    {
                        adaptor.addChild(root_0, stream_IDENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:970:6: CLASSKEYWORD
                    {
                    CLASSKEYWORD166=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_any1724);  
                    stream_CLASSKEYWORD.add(CLASSKEYWORD166);



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
                    // 970:19: -> CLASSKEYWORD
                    {
                        adaptor.addChild(root_0, stream_CLASSKEYWORD.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:971:6: NUMBER
                    {
                    NUMBER167=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_any1735);  
                    stream_NUMBER.add(NUMBER167);



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
                    // 971:13: -> NUMBER
                    {
                        adaptor.addChild(root_0, stream_NUMBER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:972:6: PERCENTAGE
                    {
                    PERCENTAGE168=(Token)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_any1746);  
                    stream_PERCENTAGE.add(PERCENTAGE168);



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
                    // 972:17: -> PERCENTAGE
                    {
                        adaptor.addChild(root_0, stream_PERCENTAGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:973:6: DIMENSION
                    {
                    DIMENSION169=(Token)match(input,DIMENSION,FOLLOW_DIMENSION_in_any1756);  
                    stream_DIMENSION.add(DIMENSION169);



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
                    // 973:16: -> DIMENSION
                    {
                        adaptor.addChild(root_0, stream_DIMENSION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:974:6: string
                    {
                    pushFollow(FOLLOW_string_in_any1767);
                    string170=string();

                    state._fsp--;

                    stream_string.add(string170.getTree());


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
                    // 974:13: -> string
                    {
                        adaptor.addChild(root_0, stream_string.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:975:9: URI
                    {
                    URI171=(Token)match(input,URI,FOLLOW_URI_in_any1781);  
                    stream_URI.add(URI171);



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
                    // 975:16: -> URI
                    {
                        adaptor.addChild(root_0, stream_URI.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:976:9: HASH
                    {
                    HASH172=(Token)match(input,HASH,FOLLOW_HASH_in_any1798);  
                    stream_HASH.add(HASH172);



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
                    // 976:14: -> HASH
                    {
                        adaptor.addChild(root_0, stream_HASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:977:9: UNIRANGE
                    {
                    UNIRANGE173=(Token)match(input,UNIRANGE,FOLLOW_UNIRANGE_in_any1812);  
                    stream_UNIRANGE.add(UNIRANGE173);



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
                    // 977:18: -> UNIRANGE
                    {
                        adaptor.addChild(root_0, stream_UNIRANGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:978:9: INCLUDES
                    {
                    INCLUDES174=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_any1826);  
                    stream_INCLUDES.add(INCLUDES174);



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
                    // 978:18: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:979:9: COLON
                    {
                    COLON175=(Token)match(input,COLON,FOLLOW_COLON_in_any1840);  
                    stream_COLON.add(COLON175);



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
                    // 979:15: -> COLON
                    {
                        adaptor.addChild(root_0, stream_COLON.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:980:9: COMMA
                    {
                    COMMA176=(Token)match(input,COMMA,FOLLOW_COMMA_in_any1854);  
                    stream_COMMA.add(COMMA176);



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
                    // 980:15: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:981:9: GREATER
                    {
                    GREATER177=(Token)match(input,GREATER,FOLLOW_GREATER_in_any1868);  
                    stream_GREATER.add(GREATER177);



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
                    // 981:17: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:982:9: LESS
                    {
                    LESS178=(Token)match(input,LESS,FOLLOW_LESS_in_any1882);  
                    stream_LESS.add(LESS178);



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
                    // 982:14: -> LESS
                    {
                        adaptor.addChild(root_0, stream_LESS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:983:9: QUESTION
                    {
                    QUESTION179=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_any1896);  
                    stream_QUESTION.add(QUESTION179);



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
                    // 983:18: -> QUESTION
                    {
                        adaptor.addChild(root_0, stream_QUESTION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:984:9: PERCENT
                    {
                    PERCENT180=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_any1910);  
                    stream_PERCENT.add(PERCENT180);



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
                    // 984:17: -> PERCENT
                    {
                        adaptor.addChild(root_0, stream_PERCENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:985:9: EQUALS
                    {
                    EQUALS181=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_any1924);  
                    stream_EQUALS.add(EQUALS181);



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
                    // 985:16: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 18 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:986:9: SLASH
                    {
                    SLASH182=(Token)match(input,SLASH,FOLLOW_SLASH_in_any1938);  
                    stream_SLASH.add(SLASH182);



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
                    // 986:15: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 19 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:987:9: EXCLAMATION
                    {
                    EXCLAMATION183=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_any1952);  
                    stream_EXCLAMATION.add(EXCLAMATION183);



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
                    // 987:21: -> EXCLAMATION
                    {
                        adaptor.addChild(root_0, stream_EXCLAMATION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 20 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:988:6: MINUS
                    {
                    MINUS184=(Token)match(input,MINUS,FOLLOW_MINUS_in_any1963);  
                    stream_MINUS.add(MINUS184);



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
                    // 988:12: -> MINUS
                    {
                        adaptor.addChild(root_0, stream_MINUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 21 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:989:6: PLUS
                    {
                    PLUS185=(Token)match(input,PLUS,FOLLOW_PLUS_in_any1974);  
                    stream_PLUS.add(PLUS185);



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
                    // 989:11: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 22 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:990:6: ASTERISK
                    {
                    ASTERISK186=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_any1985);  
                    stream_ASTERISK.add(ASTERISK186);



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
                    // 990:15: -> ASTERISK
                    {
                        adaptor.addChild(root_0, stream_ASTERISK.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 23 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:991:9: FUNCTION ( S )* ( any )* RPAREN
                    {
                    FUNCTION187=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_any2002);  
                    stream_FUNCTION.add(FUNCTION187);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:991:18: ( S )*
                    loop81:
                    do {
                        int alt81=2;
                        alt81 = dfa81.predict(input);
                        switch (alt81) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:991:18: S
                    	    {
                    	    S188=(Token)match(input,S,FOLLOW_S_in_any2004);  
                    	    stream_S.add(S188);


                    	    }
                    	    break;

                    	default :
                    	    break loop81;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:991:21: ( any )*
                    loop82:
                    do {
                        int alt82=2;
                        alt82 = dfa82.predict(input);
                        switch (alt82) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:991:21: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_any2007);
                    	    any189=any();

                    	    state._fsp--;

                    	    stream_any.add(any189.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop82;
                        }
                    } while (true);

                    RPAREN190=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_any2010);  
                    stream_RPAREN.add(RPAREN190);



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
                    // 991:33: -> ^( FUNCTION ( any )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:991:36: ^( FUNCTION ( any )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_FUNCTION.nextNode(), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:991:47: ( any )*
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:992:9: DASHMATCH
                    {
                    DASHMATCH191=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_any2030);  
                    stream_DASHMATCH.add(DASHMATCH191);



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
                    // 992:19: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 25 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:993:9: LPAREN ( any )* RPAREN
                    {
                    LPAREN192=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_any2044);  
                    stream_LPAREN.add(LPAREN192);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:993:16: ( any )*
                    loop83:
                    do {
                        int alt83=2;
                        alt83 = dfa83.predict(input);
                        switch (alt83) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:993:16: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_any2046);
                    	    any193=any();

                    	    state._fsp--;

                    	    stream_any.add(any193.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop83;
                        }
                    } while (true);

                    RPAREN194=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_any2049);  
                    stream_RPAREN.add(RPAREN194);



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
                    // 993:28: -> ^( PARENBLOCK ( any )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:993:31: ^( PARENBLOCK ( any )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PARENBLOCK, "PARENBLOCK"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:993:44: ( any )*
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:994:9: LBRACE ( any )* RBRACE
                    {
                    LBRACE195=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_any2068);  
                    stream_LBRACE.add(LBRACE195);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:994:16: ( any )*
                    loop84:
                    do {
                        int alt84=2;
                        alt84 = dfa84.predict(input);
                        switch (alt84) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:994:16: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_any2070);
                    	    any196=any();

                    	    state._fsp--;

                    	    stream_any.add(any196.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop84;
                        }
                    } while (true);

                    RBRACE197=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_any2073);  
                    stream_RBRACE.add(RBRACE197);



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
                    // 994:28: -> ^( BRACEBLOCK ( any )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:994:31: ^( BRACEBLOCK ( any )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BRACEBLOCK, "BRACEBLOCK"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:994:44: ( any )*
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:995:8: ( S )*
            loop86:
            do {
                int alt86=2;
                alt86 = dfa86.predict(input);
                switch (alt86) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:995:8: S
            	    {
            	    S198=(Token)match(input,S,FOLLOW_S_in_any2091);  
            	    stream_S.add(S198);


            	    }
            	    break;

            	default :
            	    break loop86;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:997:1: nostatement : ( RCURLY -> RCURLY | SEMICOLON -> SEMICOLON ) ;
    public final CSSParser.nostatement_return nostatement() throws RecognitionException {
        CSSParser.nostatement_return retval = new CSSParser.nostatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token RCURLY199=null;
        Token SEMICOLON200=null;

        Object RCURLY199_tree=null;
        Object SEMICOLON200_tree=null;
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:999:3: ( ( RCURLY -> RCURLY | SEMICOLON -> SEMICOLON ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:999:5: ( RCURLY -> RCURLY | SEMICOLON -> SEMICOLON )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:999:5: ( RCURLY -> RCURLY | SEMICOLON -> SEMICOLON )
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==RCURLY) ) {
                alt87=1;
            }
            else if ( (LA87_0==SEMICOLON) ) {
                alt87=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 87, 0, input);

                throw nvae;
            }
            switch (alt87) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:999:7: RCURLY
                    {
                    RCURLY199=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_nostatement2106);  
                    stream_RCURLY.add(RCURLY199);



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
                    // 999:14: -> RCURLY
                    {
                        adaptor.addChild(root_0, stream_RCURLY.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1000:7: SEMICOLON
                    {
                    SEMICOLON200=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_nostatement2120);  
                    stream_SEMICOLON.add(SEMICOLON200);



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
                    // 1000:17: -> SEMICOLON
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1003:1: noprop : ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN ) ( S )* ;
    public final CSSParser.noprop_return noprop() throws RecognitionException {
        CSSParser.noprop_return retval = new CSSParser.noprop_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CLASSKEYWORD201=null;
        Token NUMBER202=null;
        Token COMMA203=null;
        Token GREATER204=null;
        Token LESS205=null;
        Token QUESTION206=null;
        Token PERCENT207=null;
        Token EQUALS208=null;
        Token SLASH209=null;
        Token EXCLAMATION210=null;
        Token PLUS211=null;
        Token ASTERISK212=null;
        Token DASHMATCH213=null;
        Token INCLUDES214=null;
        Token COLON215=null;
        Token STRING_CHAR216=null;
        Token INVALID_TOKEN217=null;
        Token S218=null;

        Object CLASSKEYWORD201_tree=null;
        Object NUMBER202_tree=null;
        Object COMMA203_tree=null;
        Object GREATER204_tree=null;
        Object LESS205_tree=null;
        Object QUESTION206_tree=null;
        Object PERCENT207_tree=null;
        Object EQUALS208_tree=null;
        Object SLASH209_tree=null;
        Object EXCLAMATION210_tree=null;
        Object PLUS211_tree=null;
        Object ASTERISK212_tree=null;
        Object DASHMATCH213_tree=null;
        Object INCLUDES214_tree=null;
        Object COLON215_tree=null;
        Object STRING_CHAR216_tree=null;
        Object INVALID_TOKEN217_tree=null;
        Object S218_tree=null;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1005:2: ( ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN ) ( S )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1005:4: ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN ) ( S )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1005:4: ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN )
            int alt88=17;
            alt88 = dfa88.predict(input);
            switch (alt88) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1005:6: CLASSKEYWORD
                    {
                    CLASSKEYWORD201=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_noprop2143);  
                    stream_CLASSKEYWORD.add(CLASSKEYWORD201);



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
                    // 1005:19: -> CLASSKEYWORD
                    {
                        adaptor.addChild(root_0, stream_CLASSKEYWORD.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1006:8: NUMBER
                    {
                    NUMBER202=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_noprop2156);  
                    stream_NUMBER.add(NUMBER202);



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
                    // 1006:15: -> NUMBER
                    {
                        adaptor.addChild(root_0, stream_NUMBER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1007:7: COMMA
                    {
                    COMMA203=(Token)match(input,COMMA,FOLLOW_COMMA_in_noprop2168);  
                    stream_COMMA.add(COMMA203);



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
                    // 1007:13: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1008:7: GREATER
                    {
                    GREATER204=(Token)match(input,GREATER,FOLLOW_GREATER_in_noprop2180);  
                    stream_GREATER.add(GREATER204);



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
                    // 1008:15: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1009:7: LESS
                    {
                    LESS205=(Token)match(input,LESS,FOLLOW_LESS_in_noprop2192);  
                    stream_LESS.add(LESS205);



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
                    // 1009:12: -> LESS
                    {
                        adaptor.addChild(root_0, stream_LESS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1010:7: QUESTION
                    {
                    QUESTION206=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_noprop2204);  
                    stream_QUESTION.add(QUESTION206);



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
                    // 1010:16: -> QUESTION
                    {
                        adaptor.addChild(root_0, stream_QUESTION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1011:7: PERCENT
                    {
                    PERCENT207=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_noprop2216);  
                    stream_PERCENT.add(PERCENT207);



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
                    // 1011:15: -> PERCENT
                    {
                        adaptor.addChild(root_0, stream_PERCENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1012:7: EQUALS
                    {
                    EQUALS208=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_noprop2228);  
                    stream_EQUALS.add(EQUALS208);



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
                    // 1012:14: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1013:7: SLASH
                    {
                    SLASH209=(Token)match(input,SLASH,FOLLOW_SLASH_in_noprop2240);  
                    stream_SLASH.add(SLASH209);



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
                    // 1013:13: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1014:7: EXCLAMATION
                    {
                    EXCLAMATION210=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_noprop2252);  
                    stream_EXCLAMATION.add(EXCLAMATION210);



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
                    // 1014:19: -> EXCLAMATION
                    {
                        adaptor.addChild(root_0, stream_EXCLAMATION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1015:7: PLUS
                    {
                    PLUS211=(Token)match(input,PLUS,FOLLOW_PLUS_in_noprop2264);  
                    stream_PLUS.add(PLUS211);



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
                    // 1015:12: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1016:7: ASTERISK
                    {
                    ASTERISK212=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_noprop2276);  
                    stream_ASTERISK.add(ASTERISK212);



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
                    // 1016:16: -> ASTERISK
                    {
                        adaptor.addChild(root_0, stream_ASTERISK.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1017:7: DASHMATCH
                    {
                    DASHMATCH213=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_noprop2291);  
                    stream_DASHMATCH.add(DASHMATCH213);



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
                    // 1017:17: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1018:7: INCLUDES
                    {
                    INCLUDES214=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_noprop2303);  
                    stream_INCLUDES.add(INCLUDES214);



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
                    // 1018:16: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1019:7: COLON
                    {
                    COLON215=(Token)match(input,COLON,FOLLOW_COLON_in_noprop2315);  
                    stream_COLON.add(COLON215);



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
                    // 1019:13: -> COLON
                    {
                        adaptor.addChild(root_0, stream_COLON.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1020:7: STRING_CHAR
                    {
                    STRING_CHAR216=(Token)match(input,STRING_CHAR,FOLLOW_STRING_CHAR_in_noprop2327);  
                    stream_STRING_CHAR.add(STRING_CHAR216);



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
                    // 1020:19: -> STRING_CHAR
                    {
                        adaptor.addChild(root_0, stream_STRING_CHAR.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1021:7: INVALID_TOKEN
                    {
                    INVALID_TOKEN217=(Token)match(input,INVALID_TOKEN,FOLLOW_INVALID_TOKEN_in_noprop2339);  
                    stream_INVALID_TOKEN.add(INVALID_TOKEN217);



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
                    // 1021:21: -> INVALID_TOKEN
                    {
                        adaptor.addChild(root_0, stream_INVALID_TOKEN.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;

            }

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1022:8: ( S )*
            loop89:
            do {
                int alt89=2;
                alt89 = dfa89.predict(input);
                switch (alt89) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1022:8: S
            	    {
            	    S218=(Token)match(input,S,FOLLOW_S_in_noprop2352);  
            	    stream_S.add(S218);


            	    }
            	    break;

            	default :
            	    break loop89;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1024:1: norule : ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH | RPAREN -> RPAREN | '#' ) ;
    public final CSSParser.norule_return norule() throws RecognitionException {
        CSSParser.norule_return retval = new CSSParser.norule_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NUMBER219=null;
        Token PERCENTAGE220=null;
        Token DIMENSION221=null;
        Token URI223=null;
        Token UNIRANGE224=null;
        Token INCLUDES225=null;
        Token COMMA226=null;
        Token GREATER227=null;
        Token LESS228=null;
        Token QUESTION229=null;
        Token PERCENT230=null;
        Token EQUALS231=null;
        Token SLASH232=null;
        Token EXCLAMATION233=null;
        Token MINUS234=null;
        Token PLUS235=null;
        Token DASHMATCH236=null;
        Token RPAREN237=null;
        Token char_literal238=null;
        CSSParser.string_return string222 = null;


        Object NUMBER219_tree=null;
        Object PERCENTAGE220_tree=null;
        Object DIMENSION221_tree=null;
        Object URI223_tree=null;
        Object UNIRANGE224_tree=null;
        Object INCLUDES225_tree=null;
        Object COMMA226_tree=null;
        Object GREATER227_tree=null;
        Object LESS228_tree=null;
        Object QUESTION229_tree=null;
        Object PERCENT230_tree=null;
        Object EQUALS231_tree=null;
        Object SLASH232_tree=null;
        Object EXCLAMATION233_tree=null;
        Object MINUS234_tree=null;
        Object PLUS235_tree=null;
        Object DASHMATCH236_tree=null;
        Object RPAREN237_tree=null;
        Object char_literal238_tree=null;
        RewriteRuleTokenStream stream_INCLUDES=new RewriteRuleTokenStream(adaptor,"token INCLUDES");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleTokenStream stream_SLASH=new RewriteRuleTokenStream(adaptor,"token SLASH");
        RewriteRuleTokenStream stream_DASHMATCH=new RewriteRuleTokenStream(adaptor,"token DASHMATCH");
        RewriteRuleTokenStream stream_EXCLAMATION=new RewriteRuleTokenStream(adaptor,"token EXCLAMATION");
        RewriteRuleTokenStream stream_QUESTION=new RewriteRuleTokenStream(adaptor,"token QUESTION");
        RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
        RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1026:3: ( ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH | RPAREN -> RPAREN | '#' ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1026:5: ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH | RPAREN -> RPAREN | '#' )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1026:5: ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH | RPAREN -> RPAREN | '#' )
            int alt90=20;
            alt90 = dfa90.predict(input);
            switch (alt90) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1026:7: NUMBER
                    {
                    NUMBER219=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_norule2367);  
                    stream_NUMBER.add(NUMBER219);



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
                    // 1026:14: -> NUMBER
                    {
                        adaptor.addChild(root_0, stream_NUMBER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1027:8: PERCENTAGE
                    {
                    PERCENTAGE220=(Token)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_norule2380);  
                    stream_PERCENTAGE.add(PERCENTAGE220);



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
                    // 1027:19: -> PERCENTAGE
                    {
                        adaptor.addChild(root_0, stream_PERCENTAGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1028:8: DIMENSION
                    {
                    DIMENSION221=(Token)match(input,DIMENSION,FOLLOW_DIMENSION_in_norule2392);  
                    stream_DIMENSION.add(DIMENSION221);



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
                    // 1028:18: -> DIMENSION
                    {
                        adaptor.addChild(root_0, stream_DIMENSION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1029:8: string
                    {
                    pushFollow(FOLLOW_string_in_norule2405);
                    string222=string();

                    state._fsp--;

                    stream_string.add(string222.getTree());


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
                    // 1029:15: -> string
                    {
                        adaptor.addChild(root_0, stream_string.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1030:9: URI
                    {
                    URI223=(Token)match(input,URI,FOLLOW_URI_in_norule2419);  
                    stream_URI.add(URI223);



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
                    // 1030:16: -> URI
                    {
                        adaptor.addChild(root_0, stream_URI.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1031:9: UNIRANGE
                    {
                    UNIRANGE224=(Token)match(input,UNIRANGE,FOLLOW_UNIRANGE_in_norule2436);  
                    stream_UNIRANGE.add(UNIRANGE224);



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
                    // 1031:18: -> UNIRANGE
                    {
                        adaptor.addChild(root_0, stream_UNIRANGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1032:9: INCLUDES
                    {
                    INCLUDES225=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_norule2450);  
                    stream_INCLUDES.add(INCLUDES225);



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
                    // 1032:18: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1033:9: COMMA
                    {
                    COMMA226=(Token)match(input,COMMA,FOLLOW_COMMA_in_norule2464);  
                    stream_COMMA.add(COMMA226);



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
                    // 1033:15: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1034:9: GREATER
                    {
                    GREATER227=(Token)match(input,GREATER,FOLLOW_GREATER_in_norule2478);  
                    stream_GREATER.add(GREATER227);



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
                    // 1034:17: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1035:9: LESS
                    {
                    LESS228=(Token)match(input,LESS,FOLLOW_LESS_in_norule2492);  
                    stream_LESS.add(LESS228);



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
                    // 1035:14: -> LESS
                    {
                        adaptor.addChild(root_0, stream_LESS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1036:9: QUESTION
                    {
                    QUESTION229=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_norule2506);  
                    stream_QUESTION.add(QUESTION229);



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
                    // 1036:18: -> QUESTION
                    {
                        adaptor.addChild(root_0, stream_QUESTION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1037:9: PERCENT
                    {
                    PERCENT230=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_norule2520);  
                    stream_PERCENT.add(PERCENT230);



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
                    // 1037:17: -> PERCENT
                    {
                        adaptor.addChild(root_0, stream_PERCENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1038:9: EQUALS
                    {
                    EQUALS231=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_norule2534);  
                    stream_EQUALS.add(EQUALS231);



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
                    // 1038:16: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1039:9: SLASH
                    {
                    SLASH232=(Token)match(input,SLASH,FOLLOW_SLASH_in_norule2548);  
                    stream_SLASH.add(SLASH232);



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
                    // 1039:15: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1040:9: EXCLAMATION
                    {
                    EXCLAMATION233=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_norule2562);  
                    stream_EXCLAMATION.add(EXCLAMATION233);



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
                    // 1040:21: -> EXCLAMATION
                    {
                        adaptor.addChild(root_0, stream_EXCLAMATION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1041:8: MINUS
                    {
                    MINUS234=(Token)match(input,MINUS,FOLLOW_MINUS_in_norule2575);  
                    stream_MINUS.add(MINUS234);



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
                    // 1041:14: -> MINUS
                    {
                        adaptor.addChild(root_0, stream_MINUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1042:8: PLUS
                    {
                    PLUS235=(Token)match(input,PLUS,FOLLOW_PLUS_in_norule2588);  
                    stream_PLUS.add(PLUS235);



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
                    // 1042:13: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 18 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1043:9: DASHMATCH
                    {
                    DASHMATCH236=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_norule2602);  
                    stream_DASHMATCH.add(DASHMATCH236);



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
                    // 1043:19: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 19 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1044:9: RPAREN
                    {
                    RPAREN237=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_norule2616);  
                    stream_RPAREN.add(RPAREN237);



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
                    // 1044:16: -> RPAREN
                    {
                        adaptor.addChild(root_0, stream_RPAREN.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 20 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1045:9: '#'
                    {
                    char_literal238=(Token)match(input,90,FOLLOW_90_in_norule2630);  
                    stream_90.add(char_literal238);


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
    protected DFA39 dfa39 = new DFA39(this);
    protected DFA35 dfa35 = new DFA35(this);
    protected DFA36 dfa36 = new DFA36(this);
    protected DFA38 dfa38 = new DFA38(this);
    protected DFA44 dfa44 = new DFA44(this);
    protected DFA49 dfa49 = new DFA49(this);
    protected DFA45 dfa45 = new DFA45(this);
    protected DFA47 dfa47 = new DFA47(this);
    protected DFA46 dfa46 = new DFA46(this);
    protected DFA48 dfa48 = new DFA48(this);
    protected DFA50 dfa50 = new DFA50(this);
    protected DFA51 dfa51 = new DFA51(this);
    protected DFA60 dfa60 = new DFA60(this);
    protected DFA58 dfa58 = new DFA58(this);
    protected DFA59 dfa59 = new DFA59(this);
    protected DFA61 dfa61 = new DFA61(this);
    protected DFA67 dfa67 = new DFA67(this);
    protected DFA68 dfa68 = new DFA68(this);
    protected DFA69 dfa69 = new DFA69(this);
    protected DFA70 dfa70 = new DFA70(this);
    protected DFA85 dfa85 = new DFA85(this);
    protected DFA81 dfa81 = new DFA81(this);
    protected DFA82 dfa82 = new DFA82(this);
    protected DFA83 dfa83 = new DFA83(this);
    protected DFA84 dfa84 = new DFA84(this);
    protected DFA86 dfa86 = new DFA86(this);
    protected DFA88 dfa88 = new DFA88(this);
    protected DFA89 dfa89 = new DFA89(this);
    protected DFA90 dfa90 = new DFA90(this);
    static final String DFA1_eotS =
        "\30\uffff";
    static final String DFA1_eofS =
        "\1\1\27\uffff";
    static final String DFA1_minS =
        "\1\36\27\uffff";
    static final String DFA1_maxS =
        "\1\107\27\uffff";
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
        "\1\107\20\uffff\1\105\42\uffff";
    static final String DFA3_acceptS =
        "\1\uffff\1\1\24\uffff\1\2\3\1\32\uffff";
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
            "\1\1\6\uffff\1\1\5\uffff\1\27\1\30\4\uffff\4\1\1\uffff\1\31"+
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
            return "753:8: ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) )";
        }
    }
    static final String DFA4_eotS =
        "\51\uffff";
    static final String DFA4_eofS =
        "\1\1\50\uffff";
    static final String DFA4_minS =
        "\1\26\50\uffff";
    static final String DFA4_maxS =
        "\1\132\50\uffff";
    static final String DFA4_acceptS =
        "\1\uffff\1\6\1\1\1\2\1\3\1\4\1\uffff\1\5\41\uffff";
    static final String DFA4_specialS =
        "\51\uffff}>";
    static final String[] DFA4_transitionS = {
            "\2\7\1\uffff\1\7\2\uffff\1\7\1\uffff\1\4\1\2\1\3\5\7\1\uffff"+
            "\1\5\3\7\1\5\2\7\2\uffff\22\7\1\uffff\1\7\1\uffff\1\7\24\uffff"+
            "\1\7",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
        "\43\uffff";
    static final String DFA5_eofS =
        "\43\uffff";
    static final String DFA5_minS =
        "\1\26\42\uffff";
    static final String DFA5_maxS =
        "\1\132\42\uffff";
    static final String DFA5_acceptS =
        "\1\uffff\1\1\32\uffff\1\2\6\uffff";
    static final String DFA5_specialS =
        "\43\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\34\1\1\1\uffff\1\1\2\uffff\1\34\4\uffff\3\34\2\1\2\uffff"+
            "\2\34\1\1\1\uffff\2\1\2\uffff\22\1\1\uffff\1\1\1\uffff\1\1\24"+
            "\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
    static final String DFA9_eotS =
        "\27\uffff";
    static final String DFA9_eofS =
        "\27\uffff";
    static final String DFA9_minS =
        "\1\36\26\uffff";
    static final String DFA9_maxS =
        "\1\107\26\uffff";
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
            return "()* loopback of 773:10: ( S )*";
        }
    }
    static final String DFA12_eotS =
        "\36\uffff";
    static final String DFA12_eofS =
        "\36\uffff";
    static final String DFA12_minS =
        "\1\27\35\uffff";
    static final String DFA12_maxS =
        "\1\132\35\uffff";
    static final String DFA12_acceptS =
        "\1\uffff\1\2\33\uffff\1\1";
    static final String DFA12_specialS =
        "\36\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\1\1\uffff\1\1\4\uffff\1\35\5\uffff\2\1\1\uffff\1\1\2\uffff"+
            "\1\1\1\uffff\2\1\2\uffff\22\1\1\uffff\1\1\1\uffff\1\1\24\uffff"+
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
            return "()* loopback of 776:10: ( S )*";
        }
    }
    static final String DFA14_eotS =
        "\35\uffff";
    static final String DFA14_eofS =
        "\35\uffff";
    static final String DFA14_minS =
        "\1\27\34\uffff";
    static final String DFA14_maxS =
        "\1\132\34\uffff";
    static final String DFA14_acceptS =
        "\1\uffff\1\2\1\1\32\uffff";
    static final String DFA14_specialS =
        "\35\uffff}>";
    static final String[] DFA14_transitionS = {
            "\1\2\1\uffff\1\2\12\uffff\2\2\1\uffff\1\1\2\uffff\1\2\1\uffff"+
            "\2\2\2\uffff\22\2\1\uffff\1\2\1\uffff\1\2\24\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 776:13: ( ruleset ( S )* )*";
        }
    }
    static final String DFA13_eotS =
        "\36\uffff";
    static final String DFA13_eofS =
        "\36\uffff";
    static final String DFA13_minS =
        "\1\27\35\uffff";
    static final String DFA13_maxS =
        "\1\132\35\uffff";
    static final String DFA13_acceptS =
        "\1\uffff\1\2\33\uffff\1\1";
    static final String DFA13_specialS =
        "\36\uffff}>";
    static final String[] DFA13_transitionS = {
            "\1\1\1\uffff\1\1\4\uffff\1\35\5\uffff\2\1\1\uffff\1\1\2\uffff"+
            "\1\1\1\uffff\2\1\2\uffff\22\1\1\uffff\1\1\1\uffff\1\1\24\uffff"+
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
            return "()* loopback of 776:22: ( S )*";
        }
    }
    static final String DFA16_eotS =
        "\34\uffff";
    static final String DFA16_eofS =
        "\34\uffff";
    static final String DFA16_minS =
        "\1\27\33\uffff";
    static final String DFA16_maxS =
        "\1\105\33\uffff";
    static final String DFA16_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA16_specialS =
        "\34\uffff}>";
    static final String[] DFA16_transitionS = {
            "\1\2\14\uffff\2\2\1\uffff\1\1\2\uffff\1\2\1\uffff\2\2\1\uffff"+
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
            return "()* loopback of 777:24: ( any )*";
        }
    }
    static final String DFA30_eotS =
        "\34\uffff";
    static final String DFA30_eofS =
        "\34\uffff";
    static final String DFA30_minS =
        "\1\27\33\uffff";
    static final String DFA30_maxS =
        "\1\132\33\uffff";
    static final String DFA30_acceptS =
        "\1\uffff\1\1\6\uffff\1\2\23\uffff";
    static final String DFA30_specialS =
        "\34\uffff}>";
    static final String[] DFA30_transitionS = {
            "\1\10\1\uffff\1\1\12\uffff\2\1\4\uffff\1\10\1\uffff\2\10\2\uffff"+
            "\1\10\1\1\4\10\1\1\11\10\1\1\1\10\1\uffff\1\1\1\uffff\1\10\24"+
            "\uffff\1\10",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "800:1: ruleset : ( combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY -> ^( RULE ( combined_selector )+ declarations ) | norule -> INVALID_STATEMENT );";
        }
    }
    static final String DFA29_eotS =
        "\27\uffff";
    static final String DFA29_eofS =
        "\27\uffff";
    static final String DFA29_minS =
        "\1\36\26\uffff";
    static final String DFA29_maxS =
        "\1\107\26\uffff";
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
            return "()* loopback of 802:11: ( S )*";
        }
    }
    static final String DFA31_eotS =
        "\27\uffff";
    static final String DFA31_eofS =
        "\1\24\26\uffff";
    static final String DFA31_minS =
        "\1\44\26\uffff";
    static final String DFA31_maxS =
        "\1\107\26\uffff";
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
            return "815:4: ( declaration )?";
        }
    }
    static final String DFA32_eotS =
        "\30\uffff";
    static final String DFA32_eofS =
        "\1\1\27\uffff";
    static final String DFA32_minS =
        "\1\36\27\uffff";
    static final String DFA32_maxS =
        "\1\107\27\uffff";
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
            return "()* loopback of 815:28: ( S )*";
        }
    }
    static final String DFA33_eotS =
        "\27\uffff";
    static final String DFA33_eofS =
        "\1\24\26\uffff";
    static final String DFA33_minS =
        "\1\44\26\uffff";
    static final String DFA33_maxS =
        "\1\107\26\uffff";
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
            return "815:31: ( declaration )?";
        }
    }
    static final String DFA39_eotS =
        "\24\uffff";
    static final String DFA39_eofS =
        "\24\uffff";
    static final String DFA39_minS =
        "\1\44\23\uffff";
    static final String DFA39_maxS =
        "\1\107\23\uffff";
    static final String DFA39_acceptS =
        "\1\uffff\1\1\1\uffff\1\2\20\uffff";
    static final String DFA39_specialS =
        "\24\uffff}>";
    static final String[] DFA39_transitionS = {
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
            return "819:1: declaration : ( property COLON ( S )* ( terms )? ( important )? -> ^( DECLARATION ( important )? property ( terms )? ) | noprop ( any )* -> INVALID_DECLARATION );";
        }
    }
    static final String DFA35_eotS =
        "\42\uffff";
    static final String DFA35_eofS =
        "\1\1\41\uffff";
    static final String DFA35_minS =
        "\1\27\41\uffff";
    static final String DFA35_maxS =
        "\1\105\41\uffff";
    static final String DFA35_acceptS =
        "\1\uffff\1\2\37\uffff\1\1";
    static final String DFA35_specialS =
        "\42\uffff}>";
    static final String[] DFA35_transitionS = {
            "\1\1\6\uffff\1\41\5\uffff\4\1\1\uffff\7\1\1\uffff\23\1\1\uffff"+
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
            return "()* loopback of 824:19: ( S )*";
        }
    }
    static final String DFA36_eotS =
        "\41\uffff";
    static final String DFA36_eofS =
        "\1\35\40\uffff";
    static final String DFA36_minS =
        "\1\27\40\uffff";
    static final String DFA36_maxS =
        "\1\105\40\uffff";
    static final String DFA36_acceptS =
        "\1\uffff\1\1\33\uffff\1\2\3\uffff";
    static final String DFA36_specialS =
        "\41\uffff}>";
    static final String[] DFA36_transitionS = {
            "\1\1\14\uffff\3\1\1\35\1\uffff\2\1\2\35\3\1\1\uffff\23\1\1\uffff"+
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
            return "824:22: ( terms )?";
        }
    }
    static final String DFA38_eotS =
        "\36\uffff";
    static final String DFA38_eofS =
        "\1\1\35\uffff";
    static final String DFA38_minS =
        "\1\27\35\uffff";
    static final String DFA38_maxS =
        "\1\105\35\uffff";
    static final String DFA38_acceptS =
        "\1\uffff\1\2\2\uffff\1\1\31\uffff";
    static final String DFA38_specialS =
        "\36\uffff}>";
    static final String[] DFA38_transitionS = {
            "\1\4\14\uffff\2\4\1\uffff\1\1\2\uffff\1\4\1\1\2\4\1\uffff\1"+
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
            return "()* loopback of 825:11: ( any )*";
        }
    }
    static final String DFA44_eotS =
        "\42\uffff";
    static final String DFA44_eofS =
        "\1\1\41\uffff";
    static final String DFA44_minS =
        "\1\27\41\uffff";
    static final String DFA44_maxS =
        "\1\105\41\uffff";
    static final String DFA44_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\33\uffff";
    static final String DFA44_specialS =
        "\42\uffff}>";
    static final String[] DFA44_transitionS = {
            "\1\6\14\uffff\3\6\1\1\1\uffff\2\6\2\1\3\6\1\1\23\6\1\uffff\1"+
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
            return "()+ loopback of 846:4: ( term )+";
        }
    }
    static final String DFA49_eotS =
        "\35\uffff";
    static final String DFA49_eofS =
        "\35\uffff";
    static final String DFA49_minS =
        "\1\27\34\uffff";
    static final String DFA49_maxS =
        "\1\105\34\uffff";
    static final String DFA49_acceptS =
        "\1\uffff\1\1\31\uffff\1\2\1\3";
    static final String DFA49_specialS =
        "\35\uffff}>";
    static final String[] DFA49_transitionS = {
            "\1\1\14\uffff\2\1\1\33\2\uffff\1\34\1\1\2\uffff\3\1\1\uffff"+
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
            return "863:1: term : ( valuepart -> valuepart | LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY -> CURLYBLOCK | ATKEYWORD ( S )* -> ATKEYWORD );";
        }
    }
    static final String DFA45_eotS =
        "\36\uffff";
    static final String DFA45_eofS =
        "\36\uffff";
    static final String DFA45_minS =
        "\1\27\35\uffff";
    static final String DFA45_maxS =
        "\1\105\35\uffff";
    static final String DFA45_acceptS =
        "\1\uffff\1\2\33\uffff\1\1";
    static final String DFA45_specialS =
        "\36\uffff}>";
    static final String[] DFA45_transitionS = {
            "\1\1\6\uffff\1\35\5\uffff\2\1\1\uffff\1\1\2\uffff\4\1\1\uffff"+
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
            return "()* loopback of 865:14: ( S )*";
        }
    }
    static final String DFA47_eotS =
        "\35\uffff";
    static final String DFA47_eofS =
        "\35\uffff";
    static final String DFA47_minS =
        "\1\27\34\uffff";
    static final String DFA47_maxS =
        "\1\105\34\uffff";
    static final String DFA47_acceptS =
        "\1\uffff\1\3\1\1\31\uffff\1\2";
    static final String DFA47_specialS =
        "\35\uffff}>";
    static final String[] DFA47_transitionS = {
            "\1\2\14\uffff\2\2\1\uffff\1\1\2\uffff\1\2\1\34\2\2\1\uffff\1"+
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
            return "()* loopback of 865:17: ( any | SEMICOLON ( S )* )*";
        }
    }
    static final String DFA46_eotS =
        "\36\uffff";
    static final String DFA46_eofS =
        "\36\uffff";
    static final String DFA46_minS =
        "\1\27\35\uffff";
    static final String DFA46_maxS =
        "\1\105\35\uffff";
    static final String DFA46_acceptS =
        "\1\uffff\1\2\33\uffff\1\1";
    static final String DFA46_specialS =
        "\36\uffff}>";
    static final String[] DFA46_transitionS = {
            "\1\1\6\uffff\1\35\5\uffff\2\1\1\uffff\1\1\2\uffff\4\1\1\uffff"+
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
            return "()* loopback of 865:34: ( S )*";
        }
    }
    static final String DFA48_eotS =
        "\43\uffff";
    static final String DFA48_eofS =
        "\1\1\42\uffff";
    static final String DFA48_minS =
        "\1\27\42\uffff";
    static final String DFA48_maxS =
        "\1\105\42\uffff";
    static final String DFA48_acceptS =
        "\1\uffff\1\2\40\uffff\1\1";
    static final String DFA48_specialS =
        "\43\uffff}>";
    static final String[] DFA48_transitionS = {
            "\1\1\6\uffff\1\42\5\uffff\4\1\1\uffff\33\1\1\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 866:17: ( S )*";
        }
    }
    static final String DFA50_eotS =
        "\37\uffff";
    static final String DFA50_eofS =
        "\37\uffff";
    static final String DFA50_minS =
        "\1\27\36\uffff";
    static final String DFA50_maxS =
        "\1\105\36\uffff";
    static final String DFA50_acceptS =
        "\1\uffff\1\2\34\uffff\1\1";
    static final String DFA50_specialS =
        "\37\uffff}>";
    static final String[] DFA50_transitionS = {
            "\1\1\6\uffff\1\36\5\uffff\3\1\2\uffff\2\1\2\uffff\27\1\1\uffff"+
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
            return "()* loopback of 878:13: ( S )*";
        }
    }
    static final String DFA51_eotS =
        "\36\uffff";
    static final String DFA51_eofS =
        "\36\uffff";
    static final String DFA51_minS =
        "\1\27\35\uffff";
    static final String DFA51_maxS =
        "\1\105\35\uffff";
    static final String DFA51_acceptS =
        "\1\uffff\1\1\33\uffff\1\2";
    static final String DFA51_specialS =
        "\36\uffff}>";
    static final String[] DFA51_transitionS = {
            "\1\1\14\uffff\3\1\2\uffff\2\1\2\uffff\3\1\1\35\23\1\1\uffff"+
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
            return "878:16: ( terms )?";
        }
    }
    static final String DFA60_eotS =
        "\41\uffff";
    static final String DFA60_eofS =
        "\41\uffff";
    static final String DFA60_minS =
        "\1\27\1\45\37\uffff";
    static final String DFA60_maxS =
        "\1\105\1\64\37\uffff";
    static final String DFA60_acceptS =
        "\2\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\uffff\1\26\1\27\1"+
        "\30\6\uffff";
    static final String DFA60_specialS =
        "\41\uffff}>";
    static final String[] DFA60_transitionS = {
            "\1\7\14\uffff\1\14\1\2\4\uffff\1\15\2\uffff\1\1\2\26\1\uffff"+
            "\1\3\1\4\1\5\1\6\1\10\1\11\1\12\1\13\1\16\1\17\1\20\1\21\1\22"+
            "\1\23\1\24\1\25\1\30\1\31\1\32\1\uffff\1\7",
            "\1\2\10\uffff\2\26\2\uffff\1\4\1\5\1\6",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "883:7: ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | ( MINUS )? funct -> ( MINUS )? funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) )";
        }
    }
    static final String DFA58_eotS =
        "\34\uffff";
    static final String DFA58_eofS =
        "\34\uffff";
    static final String DFA58_minS =
        "\1\27\33\uffff";
    static final String DFA58_maxS =
        "\1\105\33\uffff";
    static final String DFA58_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA58_specialS =
        "\34\uffff}>";
    static final String[] DFA58_transitionS = {
            "\1\2\14\uffff\2\2\4\uffff\1\2\2\uffff\3\2\1\1\23\2\1\uffff\1"+
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
            return "()* loopback of 905:16: ( valuepart )*";
        }
    }
    static final String DFA59_eotS =
        "\34\uffff";
    static final String DFA59_eofS =
        "\34\uffff";
    static final String DFA59_minS =
        "\1\27\33\uffff";
    static final String DFA59_maxS =
        "\1\105\33\uffff";
    static final String DFA59_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA59_specialS =
        "\34\uffff}>";
    static final String[] DFA59_transitionS = {
            "\1\2\14\uffff\2\2\4\uffff\1\2\2\uffff\3\2\1\uffff\23\2\1\1\1"+
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
            return "()* loopback of 906:16: ( valuepart )*";
        }
    }
    static final String DFA61_eotS =
        "\44\uffff";
    static final String DFA61_eofS =
        "\1\1\43\uffff";
    static final String DFA61_minS =
        "\1\27\43\uffff";
    static final String DFA61_maxS =
        "\1\105\43\uffff";
    static final String DFA61_acceptS =
        "\1\uffff\1\2\41\uffff\1\1";
    static final String DFA61_specialS =
        "\44\uffff}>";
    static final String[] DFA61_transitionS = {
            "\1\1\6\uffff\1\43\5\uffff\4\1\1\uffff\35\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 907:8: ( S )*";
        }
    }
    static final String DFA67_eotS =
        "\13\uffff";
    static final String DFA67_eofS =
        "\13\uffff";
    static final String DFA67_minS =
        "\1\31\12\uffff";
    static final String DFA67_maxS =
        "\1\103\12\uffff";
    static final String DFA67_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\4\uffff";
    static final String DFA67_specialS =
        "\13\uffff}>";
    static final String[] DFA67_transitionS = {
            "\1\6\4\uffff\1\1\5\uffff\1\6\1\uffff\1\1\3\uffff\1\1\6\uffff"+
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
            return "()* loopback of 926:27: ( selpart )*";
        }
    }
    static final String DFA68_eotS =
        "\22\uffff";
    static final String DFA68_eofS =
        "\22\uffff";
    static final String DFA68_minS =
        "\1\36\2\uffff\1\31\16\uffff";
    static final String DFA68_maxS =
        "\1\77\2\uffff\1\103\16\uffff";
    static final String DFA68_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\13\uffff";
    static final String DFA68_specialS =
        "\22\uffff}>";
    static final String[] DFA68_transitionS = {
            "\1\3\7\uffff\1\1\3\uffff\1\1\16\uffff\1\1\5\uffff\1\1",
            "",
            "",
            "\1\1\4\uffff\1\6\5\uffff\2\1\1\6\3\uffff\1\6\6\uffff\1\1\4"+
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
            return "()* loopback of 926:36: ( S )*";
        }
    }
    static final String DFA69_eotS =
        "\13\uffff";
    static final String DFA69_eofS =
        "\13\uffff";
    static final String DFA69_minS =
        "\1\31\12\uffff";
    static final String DFA69_maxS =
        "\1\103\12\uffff";
    static final String DFA69_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\4\uffff";
    static final String DFA69_specialS =
        "\13\uffff}>";
    static final String[] DFA69_transitionS = {
            "\1\6\4\uffff\1\1\5\uffff\1\6\1\uffff\1\1\3\uffff\1\1\6\uffff"+
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
            return "()+ loopback of 928:7: ( selpart )+";
        }
    }
    static final String DFA70_eotS =
        "\22\uffff";
    static final String DFA70_eofS =
        "\22\uffff";
    static final String DFA70_minS =
        "\1\36\2\uffff\1\31\16\uffff";
    static final String DFA70_maxS =
        "\1\77\2\uffff\1\103\16\uffff";
    static final String DFA70_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\13\uffff";
    static final String DFA70_specialS =
        "\22\uffff}>";
    static final String[] DFA70_transitionS = {
            "\1\3\7\uffff\1\1\3\uffff\1\1\16\uffff\1\1\5\uffff\1\1",
            "",
            "",
            "\1\1\4\uffff\1\6\5\uffff\2\1\1\6\3\uffff\1\6\6\uffff\1\1\4"+
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
            return "()* loopback of 928:16: ( S )*";
        }
    }
    static final String DFA85_eotS =
        "\33\uffff";
    static final String DFA85_eofS =
        "\33\uffff";
    static final String DFA85_minS =
        "\1\27\32\uffff";
    static final String DFA85_maxS =
        "\1\105\32\uffff";
    static final String DFA85_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
        "\1\32";
    static final String DFA85_specialS =
        "\33\uffff}>";
    static final String[] DFA85_transitionS = {
            "\1\6\14\uffff\1\13\1\1\4\uffff\1\14\1\uffff\1\23\1\24\1\uffff"+
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
            return "969:4: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) )";
        }
    }
    static final String DFA81_eotS =
        "\35\uffff";
    static final String DFA81_eofS =
        "\35\uffff";
    static final String DFA81_minS =
        "\1\27\34\uffff";
    static final String DFA81_maxS =
        "\1\105\34\uffff";
    static final String DFA81_acceptS =
        "\1\uffff\1\2\32\uffff\1\1";
    static final String DFA81_specialS =
        "\35\uffff}>";
    static final String[] DFA81_transitionS = {
            "\1\1\6\uffff\1\34\5\uffff\2\1\4\uffff\1\1\1\uffff\2\1\1\uffff"+
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
            return "()* loopback of 991:18: ( S )*";
        }
    }
    static final String DFA82_eotS =
        "\34\uffff";
    static final String DFA82_eofS =
        "\34\uffff";
    static final String DFA82_minS =
        "\1\27\33\uffff";
    static final String DFA82_maxS =
        "\1\105\33\uffff";
    static final String DFA82_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA82_specialS =
        "\34\uffff}>";
    static final String[] DFA82_transitionS = {
            "\1\2\14\uffff\2\2\4\uffff\1\2\1\uffff\2\2\1\uffff\1\2\1\1\23"+
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
            return "()* loopback of 991:21: ( any )*";
        }
    }
    static final String DFA83_eotS =
        "\34\uffff";
    static final String DFA83_eofS =
        "\34\uffff";
    static final String DFA83_minS =
        "\1\27\33\uffff";
    static final String DFA83_maxS =
        "\1\105\33\uffff";
    static final String DFA83_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA83_specialS =
        "\34\uffff}>";
    static final String[] DFA83_transitionS = {
            "\1\2\14\uffff\2\2\4\uffff\1\2\1\uffff\2\2\1\uffff\1\2\1\1\23"+
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
            return "()* loopback of 993:16: ( any )*";
        }
    }
    static final String DFA84_eotS =
        "\34\uffff";
    static final String DFA84_eofS =
        "\34\uffff";
    static final String DFA84_minS =
        "\1\27\33\uffff";
    static final String DFA84_maxS =
        "\1\105\33\uffff";
    static final String DFA84_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA84_specialS =
        "\34\uffff}>";
    static final String[] DFA84_transitionS = {
            "\1\2\14\uffff\2\2\4\uffff\1\2\1\uffff\2\2\1\uffff\1\2\1\uffff"+
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
            return "()* loopback of 994:16: ( any )*";
        }
    }
    static final String DFA86_eotS =
        "\41\uffff";
    static final String DFA86_eofS =
        "\1\1\40\uffff";
    static final String DFA86_minS =
        "\1\27\40\uffff";
    static final String DFA86_maxS =
        "\1\105\40\uffff";
    static final String DFA86_acceptS =
        "\1\uffff\1\2\36\uffff\1\1";
    static final String DFA86_specialS =
        "\41\uffff}>";
    static final String[] DFA86_transitionS = {
            "\1\1\6\uffff\1\40\5\uffff\2\1\1\uffff\1\1\2\uffff\4\1\1\uffff"+
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
            return "()* loopback of 995:8: ( S )*";
        }
    }
    static final String DFA88_eotS =
        "\22\uffff";
    static final String DFA88_eofS =
        "\22\uffff";
    static final String DFA88_minS =
        "\1\44\21\uffff";
    static final String DFA88_maxS =
        "\1\107\21\uffff";
    static final String DFA88_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21";
    static final String DFA88_specialS =
        "\22\uffff}>";
    static final String[] DFA88_transitionS = {
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
            return "1005:4: ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN )";
        }
    }
    static final String DFA89_eotS =
        "\37\uffff";
    static final String DFA89_eofS =
        "\1\1\36\uffff";
    static final String DFA89_minS =
        "\1\27\36\uffff";
    static final String DFA89_maxS =
        "\1\105\36\uffff";
    static final String DFA89_acceptS =
        "\1\uffff\1\2\34\uffff\1\1";
    static final String DFA89_specialS =
        "\37\uffff}>";
    static final String[] DFA89_transitionS = {
            "\1\1\6\uffff\1\36\5\uffff\2\1\1\uffff\1\1\2\uffff\4\1\1\uffff"+
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
            return "()* loopback of 1022:8: ( S )*";
        }
    }
    static final String DFA90_eotS =
        "\25\uffff";
    static final String DFA90_eofS =
        "\25\uffff";
    static final String DFA90_minS =
        "\1\27\24\uffff";
    static final String DFA90_maxS =
        "\1\132\24\uffff";
    static final String DFA90_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24";
    static final String DFA90_specialS =
        "\25\uffff}>";
    static final String[] DFA90_transitionS = {
            "\1\4\22\uffff\1\10\1\uffff\1\17\1\20\2\uffff\1\23\1\uffff\1"+
            "\1\1\2\1\3\1\5\1\uffff\1\6\1\7\1\11\1\12\1\13\1\14\1\15\1\16"+
            "\1\21\1\uffff\1\22\3\uffff\1\4\24\uffff\1\24",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "1026:5: ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH | RPAREN -> RPAREN | '#' )";
        }
    }
 

    public static final BitSet FOLLOW_S_in_inlinestyle201 = new BitSet(new long[]{0xFF063C7040000000L,0x00000000000000C3L});
    public static final BitSet FOLLOW_declarations_in_inlinestyle206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_inlineset_in_inlinestyle226 = new BitSet(new long[]{0xFF063C7000000002L,0x00000000000000C3L});
    public static final BitSet FOLLOW_CDO_in_stylesheet254 = new BitSet(new long[]{0xFFFF3FBFD2C00002L,0x000000000400002BL});
    public static final BitSet FOLLOW_CDC_in_stylesheet258 = new BitSet(new long[]{0xFFFF3FBFD2C00002L,0x000000000400002BL});
    public static final BitSet FOLLOW_S_in_stylesheet262 = new BitSet(new long[]{0xFFFF3FBFD2C00002L,0x000000000400002BL});
    public static final BitSet FOLLOW_nostatement_in_stylesheet266 = new BitSet(new long[]{0xFFFF3FBFD2C00002L,0x000000000400002BL});
    public static final BitSet FOLLOW_statement_in_stylesheet270 = new BitSet(new long[]{0xFFFF3FBFD2C00002L,0x000000000400002BL});
    public static final BitSet FOLLOW_ruleset_in_statement300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atstatement_in_statement304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARSET_in_atstatement315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_atstatement320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_IMPORT_in_atstatement325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_END_in_atstatement330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PAGE_in_atstatement335 = new BitSet(new long[]{0x0000005040000000L});
    public static final BitSet FOLLOW_S_in_atstatement337 = new BitSet(new long[]{0x0000005040000000L});
    public static final BitSet FOLLOW_COLON_in_atstatement341 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_IDENT_in_atstatement343 = new BitSet(new long[]{0x0000004040000000L});
    public static final BitSet FOLLOW_S_in_atstatement345 = new BitSet(new long[]{0x0000004040000000L});
    public static final BitSet FOLLOW_LCURLY_in_atstatement353 = new BitSet(new long[]{0xFF063CB040000000L,0x00000000000000C3L});
    public static final BitSet FOLLOW_S_in_atstatement355 = new BitSet(new long[]{0xFF063CB040000000L,0x00000000000000C3L});
    public static final BitSet FOLLOW_declarations_in_atstatement358 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_RCURLY_in_atstatement363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MEDIA_in_atstatement379 = new BitSet(new long[]{0x0000006040000000L});
    public static final BitSet FOLLOW_S_in_atstatement381 = new BitSet(new long[]{0x0000006040000000L});
    public static final BitSet FOLLOW_media_in_atstatement384 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LCURLY_in_atstatement390 = new BitSet(new long[]{0xFFFF34B042800000L,0x000000000400002BL});
    public static final BitSet FOLLOW_S_in_atstatement392 = new BitSet(new long[]{0xFFFF34B042800000L,0x000000000400002BL});
    public static final BitSet FOLLOW_ruleset_in_atstatement396 = new BitSet(new long[]{0xFFFF34B042800000L,0x000000000400002BL});
    public static final BitSet FOLLOW_S_in_atstatement398 = new BitSet(new long[]{0xFFFF34B042800000L,0x000000000400002BL});
    public static final BitSet FOLLOW_RCURLY_in_atstatement403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATKEYWORD_in_atstatement421 = new BitSet(new long[]{0x0000004040000000L});
    public static final BitSet FOLLOW_S_in_atstatement423 = new BitSet(new long[]{0x0000004040000000L});
    public static final BitSet FOLLOW_LCURLY_in_atstatement426 = new BitSet(new long[]{0xFFFEB4B000800000L,0x000000000000002FL});
    public static final BitSet FOLLOW_any_in_atstatement428 = new BitSet(new long[]{0xFFFEB4B000800000L,0x000000000000002FL});
    public static final BitSet FOLLOW_RCURLY_in_atstatement431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pseudo_in_inlineset456 = new BitSet(new long[]{0x0000044040000000L});
    public static final BitSet FOLLOW_S_in_inlineset458 = new BitSet(new long[]{0x0000044040000000L});
    public static final BitSet FOLLOW_COMMA_in_inlineset462 = new BitSet(new long[]{0x0000001040000000L});
    public static final BitSet FOLLOW_S_in_inlineset464 = new BitSet(new long[]{0x0000001040000000L});
    public static final BitSet FOLLOW_pseudo_in_inlineset467 = new BitSet(new long[]{0x0000044040000000L});
    public static final BitSet FOLLOW_S_in_inlineset469 = new BitSet(new long[]{0x0000044040000000L});
    public static final BitSet FOLLOW_LCURLY_in_inlineset482 = new BitSet(new long[]{0xFF063CB000000000L,0x00000000000000C3L});
    public static final BitSet FOLLOW_declarations_in_inlineset488 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_RCURLY_in_inlineset493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_media520 = new BitSet(new long[]{0x0000040040000002L});
    public static final BitSet FOLLOW_S_in_media522 = new BitSet(new long[]{0x0000040040000002L});
    public static final BitSet FOLLOW_COMMA_in_media526 = new BitSet(new long[]{0x0000002040000000L});
    public static final BitSet FOLLOW_S_in_media528 = new BitSet(new long[]{0x0000002040000000L});
    public static final BitSet FOLLOW_IDENT_in_media531 = new BitSet(new long[]{0x0000040040000002L});
    public static final BitSet FOLLOW_S_in_media533 = new BitSet(new long[]{0x0000040040000002L});
    public static final BitSet FOLLOW_combined_selector_in_ruleset558 = new BitSet(new long[]{0x0000044000000000L});
    public static final BitSet FOLLOW_COMMA_in_ruleset561 = new BitSet(new long[]{0x0042003042000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_S_in_ruleset563 = new BitSet(new long[]{0x0042003042000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_combined_selector_in_ruleset566 = new BitSet(new long[]{0x0000044000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleset574 = new BitSet(new long[]{0xFF063CB040000000L,0x00000000000000C3L});
    public static final BitSet FOLLOW_S_in_ruleset576 = new BitSet(new long[]{0xFF063CB040000000L,0x00000000000000C3L});
    public static final BitSet FOLLOW_declarations_in_ruleset584 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleset589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_norule_in_ruleset608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_declarations630 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_declarations634 = new BitSet(new long[]{0xFF063C3040000002L,0x00000000000000C3L});
    public static final BitSet FOLLOW_S_in_declarations636 = new BitSet(new long[]{0xFF063C3040000002L,0x00000000000000C3L});
    public static final BitSet FOLLOW_declaration_in_declarations639 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_property_in_declaration671 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_COLON_in_declaration673 = new BitSet(new long[]{0xFFFEF67040800002L,0x000000000000002FL});
    public static final BitSet FOLLOW_S_in_declaration675 = new BitSet(new long[]{0xFFFEF67040800002L,0x000000000000002FL});
    public static final BitSet FOLLOW_terms_in_declaration678 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_important_in_declaration681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_noprop_in_declaration701 = new BitSet(new long[]{0xFFFEB43000800002L,0x000000000000002FL});
    public static final BitSet FOLLOW_any_in_declaration703 = new BitSet(new long[]{0xFFFEB43000800002L,0x000000000000002FL});
    public static final BitSet FOLLOW_EXCLAMATION_in_important729 = new BitSet(new long[]{0x0000000040000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_S_in_important731 = new BitSet(new long[]{0x0000000040000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_89_in_important734 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_important736 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_MINUS_in_property765 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_IDENT_in_property768 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_property770 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_term_in_terms798 = new BitSet(new long[]{0xFFFEE67000800002L,0x000000000000002FL});
    public static final BitSet FOLLOW_valuepart_in_term831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_term843 = new BitSet(new long[]{0xFFFEBCB040800000L,0x000000000000002FL});
    public static final BitSet FOLLOW_S_in_term845 = new BitSet(new long[]{0xFFFEBCB040800000L,0x000000000000002FL});
    public static final BitSet FOLLOW_any_in_term849 = new BitSet(new long[]{0xFFFEBCB000800000L,0x000000000000002FL});
    public static final BitSet FOLLOW_SEMICOLON_in_term853 = new BitSet(new long[]{0xFFFEBCB040800000L,0x000000000000002FL});
    public static final BitSet FOLLOW_S_in_term855 = new BitSet(new long[]{0xFFFEBCB040800000L,0x000000000000002FL});
    public static final BitSet FOLLOW_RCURLY_in_term860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATKEYWORD_in_term872 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_term874 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_EXPRESSION_in_funct907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_funct916 = new BitSet(new long[]{0xFFFFE67040800000L,0x000000000000002FL});
    public static final BitSet FOLLOW_S_in_funct918 = new BitSet(new long[]{0xFFFFE67040800000L,0x000000000000002FL});
    public static final BitSet FOLLOW_terms_in_funct921 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_funct924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart951 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_IDENT_in_valuepart954 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_valuepart971 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart985 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_NUMBER_in_valuepart988 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1005 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_PERCENTAGE_in_valuepart1008 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1025 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_DIMENSION_in_valuepart1028 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_string_in_valuepart1045 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_URI_in_valuepart1059 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_HASH_in_valuepart1076 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_valuepart1090 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_INCLUDES_in_valuepart1104 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COLON_in_valuepart1118 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COMMA_in_valuepart1132 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_GREATER_in_valuepart1146 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_LESS_in_valuepart1160 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_QUESTION_in_valuepart1174 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_PERCENT_in_valuepart1188 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_EQUALS_in_valuepart1202 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_SLASH_in_valuepart1216 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_PLUS_in_valuepart1229 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_ASTERISK_in_valuepart1242 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1259 = new BitSet(new long[]{0x0000E00000000000L});
    public static final BitSet FOLLOW_funct_in_valuepart1262 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_valuepart1280 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_LPAREN_in_valuepart1294 = new BitSet(new long[]{0xFFFFE43000800000L,0x000000000000002FL});
    public static final BitSet FOLLOW_valuepart_in_valuepart1296 = new BitSet(new long[]{0xFFFFE43000800000L,0x000000000000002FL});
    public static final BitSet FOLLOW_RPAREN_in_valuepart1299 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_LBRACE_in_valuepart1318 = new BitSet(new long[]{0xFFFEE43000800000L,0x000000000000003FL});
    public static final BitSet FOLLOW_valuepart_in_valuepart1320 = new BitSet(new long[]{0xFFFEE43000800000L,0x000000000000003FL});
    public static final BitSet FOLLOW_RBRACE_in_valuepart1323 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_valuepart1341 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_selector_in_combined_selector1358 = new BitSet(new long[]{0x8200000040000002L});
    public static final BitSet FOLLOW_combinator_in_combined_selector1362 = new BitSet(new long[]{0x0042003002000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_selector_in_combined_selector1365 = new BitSet(new long[]{0x8200000040000002L});
    public static final BitSet FOLLOW_GREATER_in_combinator1385 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_combinator1387 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_PLUS_in_combinator1397 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_combinator1399 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_combinator1409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_selector1428 = new BitSet(new long[]{0x0042003042000002L,0x0000000000000009L});
    public static final BitSet FOLLOW_ASTERISK_in_selector1432 = new BitSet(new long[]{0x0042003042000002L,0x0000000000000009L});
    public static final BitSet FOLLOW_selpart_in_selector1436 = new BitSet(new long[]{0x0042003042000002L,0x0000000000000009L});
    public static final BitSet FOLLOW_S_in_selector1439 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_selpart_in_selector1469 = new BitSet(new long[]{0x0042003042000002L,0x0000000000000009L});
    public static final BitSet FOLLOW_S_in_selector1472 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_HASH_in_selpart1519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_selpart1527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_selpart1534 = new BitSet(new long[]{0x0000002040000000L});
    public static final BitSet FOLLOW_S_in_selpart1536 = new BitSet(new long[]{0x0000002040000000L});
    public static final BitSet FOLLOW_attribute_in_selpart1539 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_RBRACE_in_selpart1541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pseudo_in_selpart1557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_SELPART_in_selpart1565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_attribute1589 = new BitSet(new long[]{0x2100000040000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_S_in_attribute1591 = new BitSet(new long[]{0x2100000040000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_attribute1598 = new BitSet(new long[]{0x0000002040800000L,0x0000000000000020L});
    public static final BitSet FOLLOW_S_in_attribute1610 = new BitSet(new long[]{0x0000002040800000L,0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_attribute1614 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_string_in_attribute1618 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_attribute1621 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_pseudocolon_in_pseudo1635 = new BitSet(new long[]{0x0000802000000000L});
    public static final BitSet FOLLOW_IDENT_in_pseudo1639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_pseudo1643 = new BitSet(new long[]{0x0004002000000000L});
    public static final BitSet FOLLOW_set_in_pseudo1645 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_pseudo1653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_pseudocolon1674 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_COLON_in_pseudocolon1676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_string0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_any1713 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_any1724 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_NUMBER_in_any1735 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_PERCENTAGE_in_any1746 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_DIMENSION_in_any1756 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_string_in_any1767 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_URI_in_any1781 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_HASH_in_any1798 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_any1812 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_INCLUDES_in_any1826 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COLON_in_any1840 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COMMA_in_any1854 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_GREATER_in_any1868 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_LESS_in_any1882 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_QUESTION_in_any1896 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_PERCENT_in_any1910 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_EQUALS_in_any1924 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_SLASH_in_any1938 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_any1952 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_MINUS_in_any1963 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_PLUS_in_any1974 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_ASTERISK_in_any1985 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_FUNCTION_in_any2002 = new BitSet(new long[]{0xFFFFB43040800000L,0x000000000000002FL});
    public static final BitSet FOLLOW_S_in_any2004 = new BitSet(new long[]{0xFFFFB43040800000L,0x000000000000002FL});
    public static final BitSet FOLLOW_any_in_any2007 = new BitSet(new long[]{0xFFFFB43000800000L,0x000000000000002FL});
    public static final BitSet FOLLOW_RPAREN_in_any2010 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_any2030 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_LPAREN_in_any2044 = new BitSet(new long[]{0xFFFFB43000800000L,0x000000000000002FL});
    public static final BitSet FOLLOW_any_in_any2046 = new BitSet(new long[]{0xFFFFB43000800000L,0x000000000000002FL});
    public static final BitSet FOLLOW_RPAREN_in_any2049 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_LBRACE_in_any2068 = new BitSet(new long[]{0xFFFEB43000800000L,0x000000000000003FL});
    public static final BitSet FOLLOW_any_in_any2070 = new BitSet(new long[]{0xFFFEB43000800000L,0x000000000000003FL});
    public static final BitSet FOLLOW_RBRACE_in_any2073 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_any2091 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_RCURLY_in_nostatement2106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_nostatement2120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_noprop2143 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_NUMBER_in_noprop2156 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COMMA_in_noprop2168 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_GREATER_in_noprop2180 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_LESS_in_noprop2192 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_QUESTION_in_noprop2204 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_PERCENT_in_noprop2216 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_EQUALS_in_noprop2228 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_SLASH_in_noprop2240 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_noprop2252 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_PLUS_in_noprop2264 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_ASTERISK_in_noprop2276 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_noprop2291 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_INCLUDES_in_noprop2303 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COLON_in_noprop2315 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_STRING_CHAR_in_noprop2327 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_INVALID_TOKEN_in_noprop2339 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_S_in_noprop2352 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_NUMBER_in_norule2367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENTAGE_in_norule2380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_norule2392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_norule2405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_URI_in_norule2419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_norule2436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCLUDES_in_norule2450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_norule2464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_norule2478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_norule2492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_norule2506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENT_in_norule2520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_norule2534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_norule2548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_norule2562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_norule2575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_norule2588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_norule2602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_norule2616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_90_in_norule2630 = new BitSet(new long[]{0x0000000000000002L});

}