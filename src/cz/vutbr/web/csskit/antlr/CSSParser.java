// $ANTLR 3.1.2 /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g 2013-02-06 14:40:08
 
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "STYLESHEET", "INLINESTYLE", "ATBLOCK", "CURLYBLOCK", "PARENBLOCK", "BRACEBLOCK", "RULE", "SELECTOR", "ELEMENT", "PSEUDO", "ADJACENT", "PRECEDING", "CHILD", "DESCENDANT", "ATTRIBUTE", "SET", "DECLARATION", "VALUE", "IMPORTANT", "IMPORT_END", "INVALID_STRING", "INVALID_SELECTOR", "INVALID_SELPART", "INVALID_DECLARATION", "INVALID_STATEMENT", "INVALID_IMPORT", "INVALID_DIRECTIVE", "S", "CDO", "CDC", "CHARSET", "IMPORT", "FONTFACE", "LCURLY", "RCURLY", "MEDIA", "ATKEYWORD", "PAGE", "IDENT", "MARGIN_AREA", "COMMA", "SEMICOLON", "COLON", "EXCLAMATION", "MINUS", "EXPRESSION", "FUNCTION", "RPAREN", "CLASSKEYWORD", "NUMBER", "PERCENTAGE", "DIMENSION", "URI", "HASH", "UNIRANGE", "INCLUDES", "GREATER", "LESS", "QUESTION", "PERCENT", "EQUALS", "SLASH", "PLUS", "ASTERISK", "DASHMATCH", "LPAREN", "LBRACE", "RBRACE", "TILDE", "STARTSWITH", "ENDSWITH", "CONTAINS", "INDEX", "STRING", "STRING_CHAR", "INVALID_TOKEN", "IDENT_MACR", "STRING_MACR", "NAME_MACR", "INTEGER_MACR", "NUMBER_MACR", "W_MACR", "URI_MACR", "APOS", "QUOT", "W_CHAR", "COMMENT", "SL_COMMENT", "NAME_START", "NAME_CHAR", "NON_ASCII", "ESCAPE_CHAR", "URI_CHAR", "NL_CHAR", "'important'", "'#'", "'^'"
    };
    public static final int FUNCTION=50;
    public static final int APOS=87;
    public static final int CLASSKEYWORD=52;
    public static final int CONTAINS=75;
    public static final int INVALID_STATEMENT=28;
    public static final int INVALID_TOKEN=79;
    public static final int EQUALS=64;
    public static final int MEDIA=39;
    public static final int NL_CHAR=97;
    public static final int NON_ASCII=94;
    public static final int EOF=-1;
    public static final int STYLESHEET=4;
    public static final int INCLUDES=59;
    public static final int ENDSWITH=74;
    public static final int INVALID_DIRECTIVE=30;
    public static final int RPAREN=51;
    public static final int IMPORT=35;
    public static final int GREATER=60;
    public static final int EXCLAMATION=47;
    public static final int INVALID_SELPART=26;
    public static final int PRECEDING=15;
    public static final int INVALID_DECLARATION=27;
    public static final int LESS=61;
    public static final int ELEMENT=12;
    public static final int DIMENSION=55;
    public static final int COMMENT=90;
    public static final int T__99=99;
    public static final int T__98=98;
    public static final int CHILD=16;
    public static final int INVALID_STRING=24;
    public static final int RULE=10;
    public static final int RBRACE=71;
    public static final int PARENBLOCK=8;
    public static final int URI_CHAR=96;
    public static final int NUMBER=53;
    public static final int LCURLY=37;
    public static final int FONTFACE=36;
    public static final int SEMICOLON=45;
    public static final int S=31;
    public static final int CDO=32;
    public static final int VALUE=21;
    public static final int CDC=33;
    public static final int PERCENTAGE=54;
    public static final int INVALID_SELECTOR=25;
    public static final int URI=56;
    public static final int STRING_CHAR=78;
    public static final int DASHMATCH=68;
    public static final int IMPORT_END=23;
    public static final int INLINESTYLE=5;
    public static final int SL_COMMENT=91;
    public static final int MARGIN_AREA=43;
    public static final int INTEGER_MACR=83;
    public static final int IDENT_MACR=80;
    public static final int NAME_CHAR=93;
    public static final int PSEUDO=13;
    public static final int LBRACE=70;
    public static final int ATTRIBUTE=18;
    public static final int NAME_START=92;
    public static final int NUMBER_MACR=84;
    public static final int CHARSET=34;
    public static final int DECLARATION=20;
    public static final int ASTERISK=67;
    public static final int LPAREN=69;
    public static final int BRACEBLOCK=9;
    public static final int INDEX=76;
    public static final int SELECTOR=11;
    public static final int SLASH=65;
    public static final int ATBLOCK=6;
    public static final int COMMA=44;
    public static final int TILDE=72;
    public static final int IDENT=42;
    public static final int UNIRANGE=58;
    public static final int PLUS=66;
    public static final int EXPRESSION=49;
    public static final int CURLYBLOCK=7;
    public static final int ATKEYWORD=40;
    public static final int PERCENT=63;
    public static final int W_CHAR=89;
    public static final int STRING_MACR=81;
    public static final int W_MACR=85;
    public static final int QUOT=88;
    public static final int DESCENDANT=17;
    public static final int HASH=57;
    public static final int SET=19;
    public static final int NAME_MACR=82;
    public static final int T__100=100;
    public static final int MINUS=48;
    public static final int IMPORTANT=22;
    public static final int ESCAPE_CHAR=95;
    public static final int COLON=46;
    public static final int PAGE=41;
    public static final int ADJACENT=14;
    public static final int QUESTION=62;
    public static final int INVALID_IMPORT=29;
    public static final int RCURLY=38;
    public static final int STARTSWITH=73;
    public static final int STRING=77;
    public static final int URI_MACR=86;

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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:747:1: inlinestyle : ( S )* ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) ) ;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:754:2: ( ( S )* ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:754:4: ( S )* ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:754:4: ( S )*
            loop1:
            do {
                int alt1=2;
                alt1 = dfa1.predict(input);
                switch (alt1) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:754:4: S
            	    {
            	    S1=(Token)match(input,S,FOLLOW_S_in_inlinestyle205);  
            	    stream_S.add(S1);


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:754:8: ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) )
            int alt3=2;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:754:9: declarations
                    {
                    pushFollow(FOLLOW_declarations_in_inlinestyle210);
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
                    // 754:22: -> ^( INLINESTYLE declarations )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:754:25: ^( INLINESTYLE declarations )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:755:10: ( inlineset )+
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:755:10: ( inlineset )+
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
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:755:10: inlineset
                    	    {
                    	    pushFollow(FOLLOW_inlineset_in_inlinestyle230);
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
                    // 755:21: -> ^( INLINESTYLE ( inlineset )+ )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:755:24: ^( INLINESTYLE ( inlineset )+ )
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:759:1: stylesheet : ( CDO | CDC | S | nostatement | statement )* -> ^( STYLESHEET ( statement )* ) ;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:760:2: ( ( CDO | CDC | S | nostatement | statement )* -> ^( STYLESHEET ( statement )* ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:760:4: ( CDO | CDC | S | nostatement | statement )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:760:4: ( CDO | CDC | S | nostatement | statement )*
            loop4:
            do {
                int alt4=6;
                alt4 = dfa4.predict(input);
                switch (alt4) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:760:6: CDO
            	    {
            	    CDO4=(Token)match(input,CDO,FOLLOW_CDO_in_stylesheet258);  
            	    stream_CDO.add(CDO4);


            	    }
            	    break;
            	case 2 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:760:12: CDC
            	    {
            	    CDC5=(Token)match(input,CDC,FOLLOW_CDC_in_stylesheet262);  
            	    stream_CDC.add(CDC5);


            	    }
            	    break;
            	case 3 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:760:18: S
            	    {
            	    S6=(Token)match(input,S,FOLLOW_S_in_stylesheet266);  
            	    stream_S.add(S6);


            	    }
            	    break;
            	case 4 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:760:22: nostatement
            	    {
            	    pushFollow(FOLLOW_nostatement_in_stylesheet270);
            	    nostatement7=nostatement();

            	    state._fsp--;

            	    stream_nostatement.add(nostatement7.getTree());

            	    }
            	    break;
            	case 5 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:760:36: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_stylesheet274);
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
            // 761:3: -> ^( STYLESHEET ( statement )* )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:761:6: ^( STYLESHEET ( statement )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(STYLESHEET, "STYLESHEET"), root_1);

                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:761:19: ( statement )*
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:764:1: statement : ( ruleset | atstatement );
    public final CSSParser.statement_return statement() throws RecognitionException {
        CSSParser.statement_return retval = new CSSParser.statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CSSParser.ruleset_return ruleset9 = null;

        CSSParser.atstatement_return atstatement10 = null;



        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:765:2: ( ruleset | atstatement )
            int alt5=2;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:765:4: ruleset
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ruleset_in_statement304);
                    ruleset9=ruleset();

                    state._fsp--;

                    adaptor.addChild(root_0, ruleset9.getTree());

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:765:14: atstatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_atstatement_in_statement308);
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:768:1: atstatement : ( CHARSET | IMPORT | INVALID_IMPORT | IMPORT_END | page | FONTFACE ( S )* LCURLY ( S )* declarations RCURLY -> ^( FONTFACE declarations ) | MEDIA ( S )* ( media )? LCURLY ( S )* ( ruleset ( S )* )* RCURLY -> ^( MEDIA ( media )? ( ruleset )* ) | ATKEYWORD ( S )* LCURLY ( any )* RCURLY -> INVALID_STATEMENT );
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:769:2: ( CHARSET | IMPORT | INVALID_IMPORT | IMPORT_END | page | FONTFACE ( S )* LCURLY ( S )* declarations RCURLY -> ^( FONTFACE declarations ) | MEDIA ( S )* ( media )? LCURLY ( S )* ( ruleset ( S )* )* RCURLY -> ^( MEDIA ( media )? ( ruleset )* ) | ATKEYWORD ( S )* LCURLY ( any )* RCURLY -> INVALID_STATEMENT )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:769:4: CHARSET
                    {
                    root_0 = (Object)adaptor.nil();

                    CHARSET11=(Token)match(input,CHARSET,FOLLOW_CHARSET_in_atstatement319); 
                    CHARSET11_tree = (Object)adaptor.create(CHARSET11);
                    adaptor.addChild(root_0, CHARSET11_tree);


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:770:4: IMPORT
                    {
                    root_0 = (Object)adaptor.nil();

                    IMPORT12=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_atstatement324); 
                    IMPORT12_tree = (Object)adaptor.create(IMPORT12);
                    adaptor.addChild(root_0, IMPORT12_tree);


                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:771:4: INVALID_IMPORT
                    {
                    root_0 = (Object)adaptor.nil();

                    INVALID_IMPORT13=(Token)match(input,INVALID_IMPORT,FOLLOW_INVALID_IMPORT_in_atstatement329); 
                    INVALID_IMPORT13_tree = (Object)adaptor.create(INVALID_IMPORT13);
                    adaptor.addChild(root_0, INVALID_IMPORT13_tree);


                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:772:4: IMPORT_END
                    {
                    root_0 = (Object)adaptor.nil();

                    IMPORT_END14=(Token)match(input,IMPORT_END,FOLLOW_IMPORT_END_in_atstatement334); 
                    IMPORT_END14_tree = (Object)adaptor.create(IMPORT_END14);
                    adaptor.addChild(root_0, IMPORT_END14_tree);


                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:773:4: page
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_page_in_atstatement339);
                    page15=page();

                    state._fsp--;

                    adaptor.addChild(root_0, page15.getTree());

                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:774:4: FONTFACE ( S )* LCURLY ( S )* declarations RCURLY
                    {
                    FONTFACE16=(Token)match(input,FONTFACE,FOLLOW_FONTFACE_in_atstatement344);  
                    stream_FONTFACE.add(FONTFACE16);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:774:13: ( S )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==S) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:774:13: S
                    	    {
                    	    S17=(Token)match(input,S,FOLLOW_S_in_atstatement346);  
                    	    stream_S.add(S17);


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    LCURLY18=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atstatement352);  
                    stream_LCURLY.add(LCURLY18);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:775:11: ( S )*
                    loop7:
                    do {
                        int alt7=2;
                        alt7 = dfa7.predict(input);
                        switch (alt7) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:775:11: S
                    	    {
                    	    S19=(Token)match(input,S,FOLLOW_S_in_atstatement354);  
                    	    stream_S.add(S19);


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    pushFollow(FOLLOW_declarations_in_atstatement357);
                    declarations20=declarations();

                    state._fsp--;

                    stream_declarations.add(declarations20.getTree());
                    RCURLY21=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atstatement362);  
                    stream_RCURLY.add(RCURLY21);



                    // AST REWRITE
                    // elements: declarations, FONTFACE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 776:11: -> ^( FONTFACE declarations )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:776:14: ^( FONTFACE declarations )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:4: MEDIA ( S )* ( media )? LCURLY ( S )* ( ruleset ( S )* )* RCURLY
                    {
                    MEDIA22=(Token)match(input,MEDIA,FOLLOW_MEDIA_in_atstatement375);  
                    stream_MEDIA.add(MEDIA22);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:10: ( S )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==S) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:10: S
                    	    {
                    	    S23=(Token)match(input,S,FOLLOW_S_in_atstatement377);  
                    	    stream_S.add(S23);


                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:13: ( media )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==IDENT) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:777:13: media
                            {
                            pushFollow(FOLLOW_media_in_atstatement380);
                            media24=media();

                            state._fsp--;

                            stream_media.add(media24.getTree());

                            }
                            break;

                    }

                    LCURLY25=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atstatement386);  
                    stream_LCURLY.add(LCURLY25);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:778:10: ( S )*
                    loop10:
                    do {
                        int alt10=2;
                        alt10 = dfa10.predict(input);
                        switch (alt10) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:778:10: S
                    	    {
                    	    S26=(Token)match(input,S,FOLLOW_S_in_atstatement388);  
                    	    stream_S.add(S26);


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:778:13: ( ruleset ( S )* )*
                    loop12:
                    do {
                        int alt12=2;
                        alt12 = dfa12.predict(input);
                        switch (alt12) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:778:14: ruleset ( S )*
                    	    {
                    	    pushFollow(FOLLOW_ruleset_in_atstatement392);
                    	    ruleset27=ruleset();

                    	    state._fsp--;

                    	    stream_ruleset.add(ruleset27.getTree());
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:778:22: ( S )*
                    	    loop11:
                    	    do {
                    	        int alt11=2;
                    	        alt11 = dfa11.predict(input);
                    	        switch (alt11) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:778:22: S
                    	    	    {
                    	    	    S28=(Token)match(input,S,FOLLOW_S_in_atstatement394);  
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

                    RCURLY29=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atstatement399);  
                    stream_RCURLY.add(RCURLY29);



                    // AST REWRITE
                    // elements: media, ruleset, MEDIA
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 778:34: -> ^( MEDIA ( media )? ( ruleset )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:778:37: ^( MEDIA ( media )? ( ruleset )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_MEDIA.nextNode(), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:778:45: ( media )?
                        if ( stream_media.hasNext() ) {
                            adaptor.addChild(root_1, stream_media.nextTree());

                        }
                        stream_media.reset();
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:778:52: ( ruleset )*
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:779:4: ATKEYWORD ( S )* LCURLY ( any )* RCURLY
                    {
                    ATKEYWORD30=(Token)match(input,ATKEYWORD,FOLLOW_ATKEYWORD_in_atstatement417);  
                    stream_ATKEYWORD.add(ATKEYWORD30);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:779:14: ( S )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==S) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:779:14: S
                    	    {
                    	    S31=(Token)match(input,S,FOLLOW_S_in_atstatement419);  
                    	    stream_S.add(S31);


                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);

                    LCURLY32=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atstatement422);  
                    stream_LCURLY.add(LCURLY32);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:779:24: ( any )*
                    loop14:
                    do {
                        int alt14=2;
                        alt14 = dfa14.predict(input);
                        switch (alt14) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:779:24: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_atstatement424);
                    	    any33=any();

                    	    state._fsp--;

                    	    stream_any.add(any33.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);

                    RCURLY34=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atstatement427);  
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
                    // 779:36: -> INVALID_STATEMENT
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:787:1: page : PAGE ( S )* ( ( IDENT | IDENT page_pseudo | page_pseudo ) ( S )* )? LCURLY ( S )* declarations ( margin_rule )* RCURLY -> ^( PAGE ( IDENT )? ( page_pseudo )? declarations ^( SET ( margin_rule )* ) ) ;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:2: ( PAGE ( S )* ( ( IDENT | IDENT page_pseudo | page_pseudo ) ( S )* )? LCURLY ( S )* declarations ( margin_rule )* RCURLY -> ^( PAGE ( IDENT )? ( page_pseudo )? declarations ^( SET ( margin_rule )* ) ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:4: PAGE ( S )* ( ( IDENT | IDENT page_pseudo | page_pseudo ) ( S )* )? LCURLY ( S )* declarations ( margin_rule )* RCURLY
            {
            PAGE35=(Token)match(input,PAGE,FOLLOW_PAGE_in_page449);  
            stream_PAGE.add(PAGE35);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:9: ( S )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==S) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:9: S
            	    {
            	    S36=(Token)match(input,S,FOLLOW_S_in_page451);  
            	    stream_S.add(S36);


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:12: ( ( IDENT | IDENT page_pseudo | page_pseudo ) ( S )* )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==IDENT||LA19_0==COLON) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:13: ( IDENT | IDENT page_pseudo | page_pseudo ) ( S )*
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:13: ( IDENT | IDENT page_pseudo | page_pseudo )
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
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:15: IDENT
                            {
                            IDENT37=(Token)match(input,IDENT,FOLLOW_IDENT_in_page457);  
                            stream_IDENT.add(IDENT37);


                            }
                            break;
                        case 2 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:23: IDENT page_pseudo
                            {
                            IDENT38=(Token)match(input,IDENT,FOLLOW_IDENT_in_page461);  
                            stream_IDENT.add(IDENT38);

                            pushFollow(FOLLOW_page_pseudo_in_page463);
                            page_pseudo39=page_pseudo();

                            state._fsp--;

                            stream_page_pseudo.add(page_pseudo39.getTree());

                            }
                            break;
                        case 3 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:43: page_pseudo
                            {
                            pushFollow(FOLLOW_page_pseudo_in_page467);
                            page_pseudo40=page_pseudo();

                            state._fsp--;

                            stream_page_pseudo.add(page_pseudo40.getTree());

                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:56: ( S )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==S) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:788:56: S
                    	    {
                    	    S41=(Token)match(input,S,FOLLOW_S_in_page470);  
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

            LCURLY42=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_page478);  
            stream_LCURLY.add(LCURLY42);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:789:10: ( S )*
            loop20:
            do {
                int alt20=2;
                alt20 = dfa20.predict(input);
                switch (alt20) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:789:10: S
            	    {
            	    S43=(Token)match(input,S,FOLLOW_S_in_page480);  
            	    stream_S.add(S43);


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            pushFollow(FOLLOW_declarations_in_page485);
            declarations44=declarations();

            state._fsp--;

            stream_declarations.add(declarations44.getTree());
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:790:16: ( margin_rule )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==MARGIN_AREA) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:790:16: margin_rule
            	    {
            	    pushFollow(FOLLOW_margin_rule_in_page487);
            	    margin_rule45=margin_rule();

            	    state._fsp--;

            	    stream_margin_rule.add(margin_rule45.getTree());

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            RCURLY46=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_page492);  
            stream_RCURLY.add(RCURLY46);



            // AST REWRITE
            // elements: margin_rule, page_pseudo, IDENT, declarations, PAGE
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 792:3: -> ^( PAGE ( IDENT )? ( page_pseudo )? declarations ^( SET ( margin_rule )* ) )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:792:6: ^( PAGE ( IDENT )? ( page_pseudo )? declarations ^( SET ( margin_rule )* ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_PAGE.nextNode(), root_1);

                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:792:13: ( IDENT )?
                if ( stream_IDENT.hasNext() ) {
                    adaptor.addChild(root_1, stream_IDENT.nextNode());

                }
                stream_IDENT.reset();
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:792:20: ( page_pseudo )?
                if ( stream_page_pseudo.hasNext() ) {
                    adaptor.addChild(root_1, stream_page_pseudo.nextTree());

                }
                stream_page_pseudo.reset();
                adaptor.addChild(root_1, stream_declarations.nextTree());
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:792:46: ^( SET ( margin_rule )* )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(SET, "SET"), root_2);

                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:792:52: ( margin_rule )*
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:795:1: page_pseudo : pseudocolon IDENT ;
    public final CSSParser.page_pseudo_return page_pseudo() throws RecognitionException {
        CSSParser.page_pseudo_return retval = new CSSParser.page_pseudo_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT48=null;
        CSSParser.pseudocolon_return pseudocolon47 = null;


        Object IDENT48_tree=null;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:796:2: ( pseudocolon IDENT )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:796:4: pseudocolon IDENT
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_pseudocolon_in_page_pseudo526);
            pseudocolon47=pseudocolon();

            state._fsp--;

            root_0 = (Object)adaptor.becomeRoot(pseudocolon47.getTree(), root_0);
            IDENT48=(Token)match(input,IDENT,FOLLOW_IDENT_in_page_pseudo529); 
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:799:1: margin_rule : MARGIN_AREA ( S )* LCURLY ( S )* declarations RCURLY ( S )* -> ^( MARGIN_AREA declarations ) ;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:800:2: ( MARGIN_AREA ( S )* LCURLY ( S )* declarations RCURLY ( S )* -> ^( MARGIN_AREA declarations ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:800:4: MARGIN_AREA ( S )* LCURLY ( S )* declarations RCURLY ( S )*
            {
            MARGIN_AREA49=(Token)match(input,MARGIN_AREA,FOLLOW_MARGIN_AREA_in_margin_rule540);  
            stream_MARGIN_AREA.add(MARGIN_AREA49);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:800:16: ( S )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==S) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:800:16: S
            	    {
            	    S50=(Token)match(input,S,FOLLOW_S_in_margin_rule542);  
            	    stream_S.add(S50);


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            LCURLY51=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_margin_rule545);  
            stream_LCURLY.add(LCURLY51);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:800:26: ( S )*
            loop23:
            do {
                int alt23=2;
                alt23 = dfa23.predict(input);
                switch (alt23) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:800:26: S
            	    {
            	    S52=(Token)match(input,S,FOLLOW_S_in_margin_rule547);  
            	    stream_S.add(S52);


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            pushFollow(FOLLOW_declarations_in_margin_rule550);
            declarations53=declarations();

            state._fsp--;

            stream_declarations.add(declarations53.getTree());
            RCURLY54=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_margin_rule552);  
            stream_RCURLY.add(RCURLY54);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:800:49: ( S )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==S) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:800:49: S
            	    {
            	    S55=(Token)match(input,S,FOLLOW_S_in_margin_rule554);  
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
            // 800:52: -> ^( MARGIN_AREA declarations )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:800:55: ^( MARGIN_AREA declarations )
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:803:1: inlineset : ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )? LCURLY declarations RCURLY -> ^( RULE ( pseudo )* declarations ) ;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:806:2: ( ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )? LCURLY declarations RCURLY -> ^( RULE ( pseudo )* declarations ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:806:4: ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )? LCURLY declarations RCURLY
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:806:4: ( pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )* )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==COLON) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:806:5: pseudo ( S )* ( COMMA ( S )* pseudo ( S )* )*
                    {
                    pushFollow(FOLLOW_pseudo_in_inlineset577);
                    pseudo56=pseudo();

                    state._fsp--;

                    stream_pseudo.add(pseudo56.getTree());
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:806:12: ( S )*
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==S) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:806:12: S
                    	    {
                    	    S57=(Token)match(input,S,FOLLOW_S_in_inlineset579);  
                    	    stream_S.add(S57);


                    	    }
                    	    break;

                    	default :
                    	    break loop25;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:806:15: ( COMMA ( S )* pseudo ( S )* )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==COMMA) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:806:16: COMMA ( S )* pseudo ( S )*
                    	    {
                    	    COMMA58=(Token)match(input,COMMA,FOLLOW_COMMA_in_inlineset583);  
                    	    stream_COMMA.add(COMMA58);

                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:806:22: ( S )*
                    	    loop26:
                    	    do {
                    	        int alt26=2;
                    	        int LA26_0 = input.LA(1);

                    	        if ( (LA26_0==S) ) {
                    	            alt26=1;
                    	        }


                    	        switch (alt26) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:806:22: S
                    	    	    {
                    	    	    S59=(Token)match(input,S,FOLLOW_S_in_inlineset585);  
                    	    	    stream_S.add(S59);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop26;
                    	        }
                    	    } while (true);

                    	    pushFollow(FOLLOW_pseudo_in_inlineset588);
                    	    pseudo60=pseudo();

                    	    state._fsp--;

                    	    stream_pseudo.add(pseudo60.getTree());
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:806:32: ( S )*
                    	    loop27:
                    	    do {
                    	        int alt27=2;
                    	        int LA27_0 = input.LA(1);

                    	        if ( (LA27_0==S) ) {
                    	            alt27=1;
                    	        }


                    	        switch (alt27) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:806:32: S
                    	    	    {
                    	    	    S61=(Token)match(input,S,FOLLOW_S_in_inlineset590);  
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

            LCURLY62=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_inlineset603);  
            stream_LCURLY.add(LCURLY62);

            pushFollow(FOLLOW_declarations_in_inlineset609);
            declarations63=declarations();

            state._fsp--;

            stream_declarations.add(declarations63.getTree());
            RCURLY64=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_inlineset614);  
            stream_RCURLY.add(RCURLY64);



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
            // 810:4: -> ^( RULE ( pseudo )* declarations )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:810:7: ^( RULE ( pseudo )* declarations )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(RULE, "RULE"), root_1);

                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:810:14: ( pseudo )*
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:813:1: media : IDENT ( S )* ( COMMA ( S )* IDENT ( S )* )* -> ( IDENT )+ ;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:814:2: ( IDENT ( S )* ( COMMA ( S )* IDENT ( S )* )* -> ( IDENT )+ )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:814:4: IDENT ( S )* ( COMMA ( S )* IDENT ( S )* )*
            {
            IDENT65=(Token)match(input,IDENT,FOLLOW_IDENT_in_media641);  
            stream_IDENT.add(IDENT65);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:814:10: ( S )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==S) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:814:10: S
            	    {
            	    S66=(Token)match(input,S,FOLLOW_S_in_media643);  
            	    stream_S.add(S66);


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:814:13: ( COMMA ( S )* IDENT ( S )* )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==COMMA) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:814:14: COMMA ( S )* IDENT ( S )*
            	    {
            	    COMMA67=(Token)match(input,COMMA,FOLLOW_COMMA_in_media647);  
            	    stream_COMMA.add(COMMA67);

            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:814:20: ( S )*
            	    loop31:
            	    do {
            	        int alt31=2;
            	        int LA31_0 = input.LA(1);

            	        if ( (LA31_0==S) ) {
            	            alt31=1;
            	        }


            	        switch (alt31) {
            	    	case 1 :
            	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:814:20: S
            	    	    {
            	    	    S68=(Token)match(input,S,FOLLOW_S_in_media649);  
            	    	    stream_S.add(S68);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop31;
            	        }
            	    } while (true);

            	    IDENT69=(Token)match(input,IDENT,FOLLOW_IDENT_in_media652);  
            	    stream_IDENT.add(IDENT69);

            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:814:29: ( S )*
            	    loop32:
            	    do {
            	        int alt32=2;
            	        int LA32_0 = input.LA(1);

            	        if ( (LA32_0==S) ) {
            	            alt32=1;
            	        }


            	        switch (alt32) {
            	    	case 1 :
            	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:814:29: S
            	    	    {
            	    	    S70=(Token)match(input,S,FOLLOW_S_in_media654);  
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
            // 815:3: -> ( IDENT )+
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:818:1: ruleset : ( combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY -> ^( RULE ( combined_selector )+ declarations ) | norule -> INVALID_STATEMENT );
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:819:2: ( combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY -> ^( RULE ( combined_selector )+ declarations ) | norule -> INVALID_STATEMENT )
            int alt37=2;
            alt37 = dfa37.predict(input);
            switch (alt37) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:819:4: combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY
                    {
                    pushFollow(FOLLOW_combined_selector_in_ruleset679);
                    combined_selector71=combined_selector();

                    state._fsp--;

                    stream_combined_selector.add(combined_selector71.getTree());
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:819:22: ( COMMA ( S )* combined_selector )*
                    loop35:
                    do {
                        int alt35=2;
                        int LA35_0 = input.LA(1);

                        if ( (LA35_0==COMMA) ) {
                            alt35=1;
                        }


                        switch (alt35) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:819:23: COMMA ( S )* combined_selector
                    	    {
                    	    COMMA72=(Token)match(input,COMMA,FOLLOW_COMMA_in_ruleset682);  
                    	    stream_COMMA.add(COMMA72);

                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:819:29: ( S )*
                    	    loop34:
                    	    do {
                    	        int alt34=2;
                    	        int LA34_0 = input.LA(1);

                    	        if ( (LA34_0==S) ) {
                    	            alt34=1;
                    	        }


                    	        switch (alt34) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:819:29: S
                    	    	    {
                    	    	    S73=(Token)match(input,S,FOLLOW_S_in_ruleset684);  
                    	    	    stream_S.add(S73);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop34;
                    	        }
                    	    } while (true);

                    	    pushFollow(FOLLOW_combined_selector_in_ruleset687);
                    	    combined_selector74=combined_selector();

                    	    state._fsp--;

                    	    stream_combined_selector.add(combined_selector74.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop35;
                        }
                    } while (true);

                    LCURLY75=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_ruleset695);  
                    stream_LCURLY.add(LCURLY75);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:820:11: ( S )*
                    loop36:
                    do {
                        int alt36=2;
                        alt36 = dfa36.predict(input);
                        switch (alt36) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:820:11: S
                    	    {
                    	    S76=(Token)match(input,S,FOLLOW_S_in_ruleset697);  
                    	    stream_S.add(S76);


                    	    }
                    	    break;

                    	default :
                    	    break loop36;
                        }
                    } while (true);

                    pushFollow(FOLLOW_declarations_in_ruleset705);
                    declarations77=declarations();

                    state._fsp--;

                    stream_declarations.add(declarations77.getTree());
                    RCURLY78=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_ruleset710);  
                    stream_RCURLY.add(RCURLY78);



                    // AST REWRITE
                    // elements: declarations, combined_selector
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 823:4: -> ^( RULE ( combined_selector )+ declarations )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:823:7: ^( RULE ( combined_selector )+ declarations )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:824:4: norule
                    {
                    pushFollow(FOLLOW_norule_in_ruleset729);
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
                    // 824:11: -> INVALID_STATEMENT
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:832:1: declarations : ( declaration )? ( SEMICOLON ( S )* ( declaration )? )* -> ^( SET ( declaration )* ) ;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:833:2: ( ( declaration )? ( SEMICOLON ( S )* ( declaration )? )* -> ^( SET ( declaration )* ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:833:4: ( declaration )? ( SEMICOLON ( S )* ( declaration )? )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:833:4: ( declaration )?
            int alt38=2;
            alt38 = dfa38.predict(input);
            switch (alt38) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:833:4: declaration
                    {
                    pushFollow(FOLLOW_declaration_in_declarations751);
                    declaration80=declaration();

                    state._fsp--;

                    stream_declaration.add(declaration80.getTree());

                    }
                    break;

            }

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:833:17: ( SEMICOLON ( S )* ( declaration )? )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==SEMICOLON) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:833:18: SEMICOLON ( S )* ( declaration )?
            	    {
            	    SEMICOLON81=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_declarations755);  
            	    stream_SEMICOLON.add(SEMICOLON81);

            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:833:28: ( S )*
            	    loop39:
            	    do {
            	        int alt39=2;
            	        alt39 = dfa39.predict(input);
            	        switch (alt39) {
            	    	case 1 :
            	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:833:28: S
            	    	    {
            	    	    S82=(Token)match(input,S,FOLLOW_S_in_declarations757);  
            	    	    stream_S.add(S82);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop39;
            	        }
            	    } while (true);

            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:833:31: ( declaration )?
            	    int alt40=2;
            	    alt40 = dfa40.predict(input);
            	    switch (alt40) {
            	        case 1 :
            	            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:833:31: declaration
            	            {
            	            pushFollow(FOLLOW_declaration_in_declarations760);
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
            // 834:4: -> ^( SET ( declaration )* )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:834:7: ^( SET ( declaration )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SET, "SET"), root_1);

                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:834:13: ( declaration )*
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:837:1: declaration : ( property COLON ( S )* ( terms )? ( important )? -> ^( DECLARATION ( important )? property ( terms )? ) | noprop ( any )* -> INVALID_DECLARATION );
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:2: ( property COLON ( S )* ( terms )? ( important )? -> ^( DECLARATION ( important )? property ( terms )? ) | noprop ( any )* -> INVALID_DECLARATION )
            int alt46=2;
            alt46 = dfa46.predict(input);
            switch (alt46) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:4: property COLON ( S )* ( terms )? ( important )?
                    {
                    pushFollow(FOLLOW_property_in_declaration792);
                    property84=property();

                    state._fsp--;

                    stream_property.add(property84.getTree());
                    COLON85=(Token)match(input,COLON,FOLLOW_COLON_in_declaration794);  
                    stream_COLON.add(COLON85);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:19: ( S )*
                    loop42:
                    do {
                        int alt42=2;
                        alt42 = dfa42.predict(input);
                        switch (alt42) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:19: S
                    	    {
                    	    S86=(Token)match(input,S,FOLLOW_S_in_declaration796);  
                    	    stream_S.add(S86);


                    	    }
                    	    break;

                    	default :
                    	    break loop42;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:22: ( terms )?
                    int alt43=2;
                    alt43 = dfa43.predict(input);
                    switch (alt43) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:22: terms
                            {
                            pushFollow(FOLLOW_terms_in_declaration799);
                            terms87=terms();

                            state._fsp--;

                            stream_terms.add(terms87.getTree());

                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:29: ( important )?
                    int alt44=2;
                    int LA44_0 = input.LA(1);

                    if ( (LA44_0==EXCLAMATION) ) {
                        alt44=1;
                    }
                    switch (alt44) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:29: important
                            {
                            pushFollow(FOLLOW_important_in_declaration802);
                            important88=important();

                            state._fsp--;

                            stream_important.add(important88.getTree());

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
                    // 842:40: -> ^( DECLARATION ( important )? property ( terms )? )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:43: ^( DECLARATION ( important )? property ( terms )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DECLARATION, "DECLARATION"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:57: ( important )?
                        if ( stream_important.hasNext() ) {
                            adaptor.addChild(root_1, stream_important.nextTree());

                        }
                        stream_important.reset();
                        adaptor.addChild(root_1, stream_property.nextTree());
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:842:77: ( terms )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:843:4: noprop ( any )*
                    {
                    pushFollow(FOLLOW_noprop_in_declaration822);
                    noprop89=noprop();

                    state._fsp--;

                    stream_noprop.add(noprop89.getTree());
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:843:11: ( any )*
                    loop45:
                    do {
                        int alt45=2;
                        alt45 = dfa45.predict(input);
                        switch (alt45) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:843:11: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_declaration824);
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
                    // 843:16: -> INVALID_DECLARATION
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:851:1: important : EXCLAMATION ( S )* 'important' ( S )* -> IMPORTANT ;
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
        RewriteRuleTokenStream stream_98=new RewriteRuleTokenStream(adaptor,"token 98");
        RewriteRuleTokenStream stream_EXCLAMATION=new RewriteRuleTokenStream(adaptor,"token EXCLAMATION");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:852:3: ( EXCLAMATION ( S )* 'important' ( S )* -> IMPORTANT )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:852:5: EXCLAMATION ( S )* 'important' ( S )*
            {
            EXCLAMATION91=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_important850);  
            stream_EXCLAMATION.add(EXCLAMATION91);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:852:17: ( S )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==S) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:852:17: S
            	    {
            	    S92=(Token)match(input,S,FOLLOW_S_in_important852);  
            	    stream_S.add(S92);


            	    }
            	    break;

            	default :
            	    break loop47;
                }
            } while (true);

            string_literal93=(Token)match(input,98,FOLLOW_98_in_important855);  
            stream_98.add(string_literal93);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:852:32: ( S )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0==S) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:852:32: S
            	    {
            	    S94=(Token)match(input,S,FOLLOW_S_in_important857);  
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
            // 852:35: -> IMPORTANT
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:859:1: property : ( MINUS )? IDENT ( S )* -> ( MINUS )? IDENT ;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:860:2: ( ( MINUS )? IDENT ( S )* -> ( MINUS )? IDENT )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:860:4: ( MINUS )? IDENT ( S )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:860:4: ( MINUS )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==MINUS) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:860:4: MINUS
                    {
                    MINUS95=(Token)match(input,MINUS,FOLLOW_MINUS_in_property886);  
                    stream_MINUS.add(MINUS95);


                    }
                    break;

            }

            IDENT96=(Token)match(input,IDENT,FOLLOW_IDENT_in_property889);  
            stream_IDENT.add(IDENT96);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:860:17: ( S )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==S) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:860:17: S
            	    {
            	    S97=(Token)match(input,S,FOLLOW_S_in_property891);  
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
            // 860:20: -> ( MINUS )? IDENT
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:860:23: ( MINUS )?
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:863:1: terms : ( term )+ -> ^( VALUE ( term )+ ) ;
    public final CSSParser.terms_return terms() throws RecognitionException {
        CSSParser.terms_return retval = new CSSParser.terms_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CSSParser.term_return term98 = null;


        RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:864:2: ( ( term )+ -> ^( VALUE ( term )+ ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:864:4: ( term )+
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:864:4: ( term )+
            int cnt51=0;
            loop51:
            do {
                int alt51=2;
                alt51 = dfa51.predict(input);
                switch (alt51) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:864:4: term
            	    {
            	    pushFollow(FOLLOW_term_in_terms919);
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
            // 865:2: -> ^( VALUE ( term )+ )
            {
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:865:5: ^( VALUE ( term )+ )
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:881:1: term : ( valuepart -> valuepart | LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY -> CURLYBLOCK | ATKEYWORD ( S )* -> ATKEYWORD );
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:882:5: ( valuepart -> valuepart | LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY -> CURLYBLOCK | ATKEYWORD ( S )* -> ATKEYWORD )
            int alt56=3;
            alt56 = dfa56.predict(input);
            switch (alt56) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:882:7: valuepart
                    {
                    pushFollow(FOLLOW_valuepart_in_term952);
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
                    // 882:17: -> valuepart
                    {
                        adaptor.addChild(root_0, stream_valuepart.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:7: LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY
                    {
                    LCURLY100=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_term964);  
                    stream_LCURLY.add(LCURLY100);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:14: ( S )*
                    loop52:
                    do {
                        int alt52=2;
                        alt52 = dfa52.predict(input);
                        switch (alt52) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:14: S
                    	    {
                    	    S101=(Token)match(input,S,FOLLOW_S_in_term966);  
                    	    stream_S.add(S101);


                    	    }
                    	    break;

                    	default :
                    	    break loop52;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:17: ( any | SEMICOLON ( S )* )*
                    loop54:
                    do {
                        int alt54=3;
                        alt54 = dfa54.predict(input);
                        switch (alt54) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:18: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_term970);
                    	    any102=any();

                    	    state._fsp--;

                    	    stream_any.add(any102.getTree());

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:24: SEMICOLON ( S )*
                    	    {
                    	    SEMICOLON103=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_term974);  
                    	    stream_SEMICOLON.add(SEMICOLON103);

                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:34: ( S )*
                    	    loop53:
                    	    do {
                    	        int alt53=2;
                    	        alt53 = dfa53.predict(input);
                    	        switch (alt53) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:883:34: S
                    	    	    {
                    	    	    S104=(Token)match(input,S,FOLLOW_S_in_term976);  
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

                    RCURLY105=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_term981);  
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
                    // 883:46: -> CURLYBLOCK
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(CURLYBLOCK, "CURLYBLOCK"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:884:7: ATKEYWORD ( S )*
                    {
                    ATKEYWORD106=(Token)match(input,ATKEYWORD,FOLLOW_ATKEYWORD_in_term993);  
                    stream_ATKEYWORD.add(ATKEYWORD106);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:884:17: ( S )*
                    loop55:
                    do {
                        int alt55=2;
                        alt55 = dfa55.predict(input);
                        switch (alt55) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:884:17: S
                    	    {
                    	    S107=(Token)match(input,S,FOLLOW_S_in_term995);  
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
                    // 884:20: -> ATKEYWORD
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:887:1: funct : ( EXPRESSION -> EXPRESSION | FUNCTION ( S )* ( terms )? RPAREN -> ^( FUNCTION ( terms )? ) );
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:895:3: ( EXPRESSION -> EXPRESSION | FUNCTION ( S )* ( terms )? RPAREN -> ^( FUNCTION ( terms )? ) )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:895:5: EXPRESSION
                    {
                    EXPRESSION108=(Token)match(input,EXPRESSION,FOLLOW_EXPRESSION_in_funct1028);  
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
                    // 895:16: -> EXPRESSION
                    {
                        adaptor.addChild(root_0, stream_EXPRESSION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:896:4: FUNCTION ( S )* ( terms )? RPAREN
                    {
                    FUNCTION109=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_funct1037);  
                    stream_FUNCTION.add(FUNCTION109);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:896:13: ( S )*
                    loop57:
                    do {
                        int alt57=2;
                        alt57 = dfa57.predict(input);
                        switch (alt57) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:896:13: S
                    	    {
                    	    S110=(Token)match(input,S,FOLLOW_S_in_funct1039);  
                    	    stream_S.add(S110);


                    	    }
                    	    break;

                    	default :
                    	    break loop57;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:896:16: ( terms )?
                    int alt58=2;
                    alt58 = dfa58.predict(input);
                    switch (alt58) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:896:16: terms
                            {
                            pushFollow(FOLLOW_terms_in_funct1042);
                            terms111=terms();

                            state._fsp--;

                            stream_terms.add(terms111.getTree());

                            }
                            break;

                    }

                    RPAREN112=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_funct1045);  
                    stream_RPAREN.add(RPAREN112);



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
                    // 896:30: -> ^( FUNCTION ( terms )? )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:896:33: ^( FUNCTION ( terms )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_FUNCTION.nextNode(), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:896:44: ( terms )?
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:899:1: valuepart : ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | ( MINUS )? funct -> ( MINUS )? funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) ) ( S )* ;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:901:5: ( ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | ( MINUS )? funct -> ( MINUS )? funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) ) ( S )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:901:7: ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | ( MINUS )? funct -> ( MINUS )? funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) ) ( S )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:901:7: ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | ( MINUS )? funct -> ( MINUS )? funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) )
            int alt67=24;
            alt67 = dfa67.predict(input);
            switch (alt67) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:901:9: ( MINUS )? IDENT
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:901:9: ( MINUS )?
                    int alt60=2;
                    int LA60_0 = input.LA(1);

                    if ( (LA60_0==MINUS) ) {
                        alt60=1;
                    }
                    switch (alt60) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:901:9: MINUS
                            {
                            MINUS113=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart1072);  
                            stream_MINUS.add(MINUS113);


                            }
                            break;

                    }

                    IDENT114=(Token)match(input,IDENT,FOLLOW_IDENT_in_valuepart1075);  
                    stream_IDENT.add(IDENT114);



                    // AST REWRITE
                    // elements: MINUS, IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 901:22: -> ( MINUS )? IDENT
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:901:25: ( MINUS )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:902:9: CLASSKEYWORD
                    {
                    CLASSKEYWORD115=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_valuepart1092);  
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
                    // 902:22: -> CLASSKEYWORD
                    {
                        adaptor.addChild(root_0, stream_CLASSKEYWORD.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:903:9: ( MINUS )? NUMBER
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:903:9: ( MINUS )?
                    int alt61=2;
                    int LA61_0 = input.LA(1);

                    if ( (LA61_0==MINUS) ) {
                        alt61=1;
                    }
                    switch (alt61) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:903:9: MINUS
                            {
                            MINUS116=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart1106);  
                            stream_MINUS.add(MINUS116);


                            }
                            break;

                    }

                    NUMBER117=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_valuepart1109);  
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
                    // 903:23: -> ( MINUS )? NUMBER
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:903:26: ( MINUS )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:904:9: ( MINUS )? PERCENTAGE
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:904:9: ( MINUS )?
                    int alt62=2;
                    int LA62_0 = input.LA(1);

                    if ( (LA62_0==MINUS) ) {
                        alt62=1;
                    }
                    switch (alt62) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:904:9: MINUS
                            {
                            MINUS118=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart1126);  
                            stream_MINUS.add(MINUS118);


                            }
                            break;

                    }

                    PERCENTAGE119=(Token)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_valuepart1129);  
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
                    // 904:27: -> ( MINUS )? PERCENTAGE
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:904:30: ( MINUS )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:905:9: ( MINUS )? DIMENSION
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:905:9: ( MINUS )?
                    int alt63=2;
                    int LA63_0 = input.LA(1);

                    if ( (LA63_0==MINUS) ) {
                        alt63=1;
                    }
                    switch (alt63) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:905:9: MINUS
                            {
                            MINUS120=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart1146);  
                            stream_MINUS.add(MINUS120);


                            }
                            break;

                    }

                    DIMENSION121=(Token)match(input,DIMENSION,FOLLOW_DIMENSION_in_valuepart1149);  
                    stream_DIMENSION.add(DIMENSION121);



                    // AST REWRITE
                    // elements: MINUS, DIMENSION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 905:26: -> ( MINUS )? DIMENSION
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:905:29: ( MINUS )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:906:9: string
                    {
                    pushFollow(FOLLOW_string_in_valuepart1166);
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
                    // 906:16: -> string
                    {
                        adaptor.addChild(root_0, stream_string.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:907:9: URI
                    {
                    URI123=(Token)match(input,URI,FOLLOW_URI_in_valuepart1180);  
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
                    // 907:16: -> URI
                    {
                        adaptor.addChild(root_0, stream_URI.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:908:9: HASH
                    {
                    HASH124=(Token)match(input,HASH,FOLLOW_HASH_in_valuepart1197);  
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
                    // 908:14: -> HASH
                    {
                        adaptor.addChild(root_0, stream_HASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:909:9: UNIRANGE
                    {
                    UNIRANGE125=(Token)match(input,UNIRANGE,FOLLOW_UNIRANGE_in_valuepart1211);  
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
                    // 909:18: -> UNIRANGE
                    {
                        adaptor.addChild(root_0, stream_UNIRANGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:910:9: INCLUDES
                    {
                    INCLUDES126=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_valuepart1225);  
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
                    // 910:18: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:911:9: COLON
                    {
                    COLON127=(Token)match(input,COLON,FOLLOW_COLON_in_valuepart1239);  
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
                    // 911:15: -> COLON
                    {
                        adaptor.addChild(root_0, stream_COLON.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:912:9: COMMA
                    {
                    COMMA128=(Token)match(input,COMMA,FOLLOW_COMMA_in_valuepart1253);  
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
                    // 912:15: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:913:9: GREATER
                    {
                    GREATER129=(Token)match(input,GREATER,FOLLOW_GREATER_in_valuepart1267);  
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
                    // 913:17: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:914:9: LESS
                    {
                    LESS130=(Token)match(input,LESS,FOLLOW_LESS_in_valuepart1281);  
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
                    // 914:14: -> LESS
                    {
                        adaptor.addChild(root_0, stream_LESS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:915:9: QUESTION
                    {
                    QUESTION131=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_valuepart1295);  
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
                    // 915:18: -> QUESTION
                    {
                        adaptor.addChild(root_0, stream_QUESTION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:916:9: PERCENT
                    {
                    PERCENT132=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_valuepart1309);  
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
                    // 916:17: -> PERCENT
                    {
                        adaptor.addChild(root_0, stream_PERCENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:917:9: EQUALS
                    {
                    EQUALS133=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_valuepart1323);  
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
                    // 917:16: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 18 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:918:9: SLASH
                    {
                    SLASH134=(Token)match(input,SLASH,FOLLOW_SLASH_in_valuepart1337);  
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
                    // 918:15: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 19 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:919:8: PLUS
                    {
                    PLUS135=(Token)match(input,PLUS,FOLLOW_PLUS_in_valuepart1350);  
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
                    // 919:13: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 20 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:920:8: ASTERISK
                    {
                    ASTERISK136=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_valuepart1363);  
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
                    // 920:17: -> ASTERISK
                    {
                        adaptor.addChild(root_0, stream_ASTERISK.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 21 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:921:9: ( MINUS )? funct
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:921:9: ( MINUS )?
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==MINUS) ) {
                        alt64=1;
                    }
                    switch (alt64) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:921:9: MINUS
                            {
                            MINUS137=(Token)match(input,MINUS,FOLLOW_MINUS_in_valuepart1380);  
                            stream_MINUS.add(MINUS137);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_funct_in_valuepart1383);
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
                    // 921:22: -> ( MINUS )? funct
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:921:25: ( MINUS )?
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:922:9: DASHMATCH
                    {
                    DASHMATCH139=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_valuepart1401);  
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
                    // 922:19: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 23 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:923:9: LPAREN ( valuepart )* RPAREN
                    {
                    LPAREN140=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_valuepart1415);  
                    stream_LPAREN.add(LPAREN140);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:923:16: ( valuepart )*
                    loop65:
                    do {
                        int alt65=2;
                        alt65 = dfa65.predict(input);
                        switch (alt65) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:923:16: valuepart
                    	    {
                    	    pushFollow(FOLLOW_valuepart_in_valuepart1417);
                    	    valuepart141=valuepart();

                    	    state._fsp--;

                    	    stream_valuepart.add(valuepart141.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop65;
                        }
                    } while (true);

                    RPAREN142=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_valuepart1420);  
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
                    // 923:34: -> ^( PARENBLOCK ( valuepart )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:923:37: ^( PARENBLOCK ( valuepart )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PARENBLOCK, "PARENBLOCK"), root_1);

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
                case 24 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:924:9: LBRACE ( valuepart )* RBRACE
                    {
                    LBRACE143=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_valuepart1439);  
                    stream_LBRACE.add(LBRACE143);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:924:16: ( valuepart )*
                    loop66:
                    do {
                        int alt66=2;
                        alt66 = dfa66.predict(input);
                        switch (alt66) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:924:16: valuepart
                    	    {
                    	    pushFollow(FOLLOW_valuepart_in_valuepart1441);
                    	    valuepart144=valuepart();

                    	    state._fsp--;

                    	    stream_valuepart.add(valuepart144.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop66;
                        }
                    } while (true);

                    RBRACE145=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_valuepart1444);  
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
                    // 924:34: -> ^( BRACEBLOCK ( valuepart )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:924:37: ^( BRACEBLOCK ( valuepart )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BRACEBLOCK, "BRACEBLOCK"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:924:50: ( valuepart )*
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:925:8: ( S )*
            loop68:
            do {
                int alt68=2;
                alt68 = dfa68.predict(input);
                switch (alt68) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:925:8: S
            	    {
            	    S146=(Token)match(input,S,FOLLOW_S_in_valuepart1462);  
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:928:1: combined_selector : selector ( ( combinator ) selector )* ;
    public final CSSParser.combined_selector_return combined_selector() throws RecognitionException {
        CSSParser.combined_selector_return retval = new CSSParser.combined_selector_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CSSParser.selector_return selector147 = null;

        CSSParser.combinator_return combinator148 = null;

        CSSParser.selector_return selector149 = null;



        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:929:2: ( selector ( ( combinator ) selector )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:929:4: selector ( ( combinator ) selector )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_selector_in_combined_selector1479);
            selector147=selector();

            state._fsp--;

            adaptor.addChild(root_0, selector147.getTree());
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:929:13: ( ( combinator ) selector )*
            loop69:
            do {
                int alt69=2;
                int LA69_0 = input.LA(1);

                if ( (LA69_0==S||LA69_0==GREATER||LA69_0==PLUS||LA69_0==TILDE) ) {
                    alt69=1;
                }


                switch (alt69) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:929:14: ( combinator ) selector
            	    {
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:929:14: ( combinator )
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:929:15: combinator
            	    {
            	    pushFollow(FOLLOW_combinator_in_combined_selector1483);
            	    combinator148=combinator();

            	    state._fsp--;

            	    adaptor.addChild(root_0, combinator148.getTree());

            	    }

            	    pushFollow(FOLLOW_selector_in_combined_selector1486);
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:937:1: combinator : ( GREATER ( S )* -> CHILD | PLUS ( S )* -> ADJACENT | TILDE ( S )* -> PRECEDING | S -> DESCENDANT );
    public final CSSParser.combinator_return combinator() throws RecognitionException {
        CSSParser.combinator_return retval = new CSSParser.combinator_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token GREATER150=null;
        Token S151=null;
        Token PLUS152=null;
        Token S153=null;
        Token TILDE154=null;
        Token S155=null;
        Token S156=null;

        Object GREATER150_tree=null;
        Object S151_tree=null;
        Object PLUS152_tree=null;
        Object S153_tree=null;
        Object TILDE154_tree=null;
        Object S155_tree=null;
        Object S156_tree=null;
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_TILDE=new RewriteRuleTokenStream(adaptor,"token TILDE");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:938:2: ( GREATER ( S )* -> CHILD | PLUS ( S )* -> ADJACENT | TILDE ( S )* -> PRECEDING | S -> DESCENDANT )
            int alt73=4;
            switch ( input.LA(1) ) {
            case GREATER:
                {
                alt73=1;
                }
                break;
            case PLUS:
                {
                alt73=2;
                }
                break;
            case TILDE:
                {
                alt73=3;
                }
                break;
            case S:
                {
                alt73=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 73, 0, input);

                throw nvae;
            }

            switch (alt73) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:938:4: GREATER ( S )*
                    {
                    GREATER150=(Token)match(input,GREATER,FOLLOW_GREATER_in_combinator1506);  
                    stream_GREATER.add(GREATER150);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:938:12: ( S )*
                    loop70:
                    do {
                        int alt70=2;
                        int LA70_0 = input.LA(1);

                        if ( (LA70_0==S) ) {
                            alt70=1;
                        }


                        switch (alt70) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:938:12: S
                    	    {
                    	    S151=(Token)match(input,S,FOLLOW_S_in_combinator1508);  
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
                    // 938:15: -> CHILD
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(CHILD, "CHILD"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:939:4: PLUS ( S )*
                    {
                    PLUS152=(Token)match(input,PLUS,FOLLOW_PLUS_in_combinator1518);  
                    stream_PLUS.add(PLUS152);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:939:9: ( S )*
                    loop71:
                    do {
                        int alt71=2;
                        int LA71_0 = input.LA(1);

                        if ( (LA71_0==S) ) {
                            alt71=1;
                        }


                        switch (alt71) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:939:9: S
                    	    {
                    	    S153=(Token)match(input,S,FOLLOW_S_in_combinator1520);  
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
                    // 939:12: -> ADJACENT
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(ADJACENT, "ADJACENT"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:940:4: TILDE ( S )*
                    {
                    TILDE154=(Token)match(input,TILDE,FOLLOW_TILDE_in_combinator1530);  
                    stream_TILDE.add(TILDE154);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:940:10: ( S )*
                    loop72:
                    do {
                        int alt72=2;
                        int LA72_0 = input.LA(1);

                        if ( (LA72_0==S) ) {
                            alt72=1;
                        }


                        switch (alt72) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:940:10: S
                    	    {
                    	    S155=(Token)match(input,S,FOLLOW_S_in_combinator1532);  
                    	    stream_S.add(S155);


                    	    }
                    	    break;

                    	default :
                    	    break loop72;
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
                    // 940:13: -> PRECEDING
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(PRECEDING, "PRECEDING"));

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:941:4: S
                    {
                    S156=(Token)match(input,S,FOLLOW_S_in_combinator1542);  
                    stream_S.add(S156);



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
                    // 941:6: -> DESCENDANT
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:944:1: selector : ( ( IDENT | ASTERISK ) ( selpart )* ( S )* -> ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* ) | ( selpart )+ ( S )* -> ^( SELECTOR ( selpart )+ ) );
    public final CSSParser.selector_return selector() throws RecognitionException {
        CSSParser.selector_return retval = new CSSParser.selector_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT157=null;
        Token ASTERISK158=null;
        Token S160=null;
        Token S162=null;
        CSSParser.selpart_return selpart159 = null;

        CSSParser.selpart_return selpart161 = null;


        Object IDENT157_tree=null;
        Object ASTERISK158_tree=null;
        Object S160_tree=null;
        Object S162_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_ASTERISK=new RewriteRuleTokenStream(adaptor,"token ASTERISK");
        RewriteRuleSubtreeStream stream_selpart=new RewriteRuleSubtreeStream(adaptor,"rule selpart");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:945:5: ( ( IDENT | ASTERISK ) ( selpart )* ( S )* -> ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* ) | ( selpart )+ ( S )* -> ^( SELECTOR ( selpart )+ ) )
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==IDENT||LA79_0==ASTERISK) ) {
                alt79=1;
            }
            else if ( (LA79_0==INVALID_SELPART||LA79_0==COLON||LA79_0==CLASSKEYWORD||LA79_0==HASH||LA79_0==LBRACE) ) {
                alt79=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;
            }
            switch (alt79) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:945:7: ( IDENT | ASTERISK ) ( selpart )* ( S )*
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:945:7: ( IDENT | ASTERISK )
                    int alt74=2;
                    int LA74_0 = input.LA(1);

                    if ( (LA74_0==IDENT) ) {
                        alt74=1;
                    }
                    else if ( (LA74_0==ASTERISK) ) {
                        alt74=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 74, 0, input);

                        throw nvae;
                    }
                    switch (alt74) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:945:8: IDENT
                            {
                            IDENT157=(Token)match(input,IDENT,FOLLOW_IDENT_in_selector1561);  
                            stream_IDENT.add(IDENT157);


                            }
                            break;
                        case 2 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:945:16: ASTERISK
                            {
                            ASTERISK158=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_selector1565);  
                            stream_ASTERISK.add(ASTERISK158);


                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:945:27: ( selpart )*
                    loop75:
                    do {
                        int alt75=2;
                        alt75 = dfa75.predict(input);
                        switch (alt75) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:945:27: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1569);
                    	    selpart159=selpart();

                    	    state._fsp--;

                    	    stream_selpart.add(selpart159.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop75;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:945:36: ( S )*
                    loop76:
                    do {
                        int alt76=2;
                        alt76 = dfa76.predict(input);
                        switch (alt76) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:945:36: S
                    	    {
                    	    S160=(Token)match(input,S,FOLLOW_S_in_selector1572);  
                    	    stream_S.add(S160);


                    	    }
                    	    break;

                    	default :
                    	    break loop76;
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
                    // 946:6: -> ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:946:9: ^( SELECTOR ^( ELEMENT ( IDENT )? ) ( selpart )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SELECTOR, "SELECTOR"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:946:20: ^( ELEMENT ( IDENT )? )
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ELEMENT, "ELEMENT"), root_2);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:946:30: ( IDENT )?
                        if ( stream_IDENT.hasNext() ) {
                            adaptor.addChild(root_2, stream_IDENT.nextNode());

                        }
                        stream_IDENT.reset();

                        adaptor.addChild(root_1, root_2);
                        }
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:946:38: ( selpart )*
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:947:7: ( selpart )+ ( S )*
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:947:7: ( selpart )+
                    int cnt77=0;
                    loop77:
                    do {
                        int alt77=2;
                        alt77 = dfa77.predict(input);
                        switch (alt77) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:947:7: selpart
                    	    {
                    	    pushFollow(FOLLOW_selpart_in_selector1602);
                    	    selpart161=selpart();

                    	    state._fsp--;

                    	    stream_selpart.add(selpart161.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt77 >= 1 ) break loop77;
                                EarlyExitException eee =
                                    new EarlyExitException(77, input);
                                throw eee;
                        }
                        cnt77++;
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:947:16: ( S )*
                    loop78:
                    do {
                        int alt78=2;
                        alt78 = dfa78.predict(input);
                        switch (alt78) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:947:16: S
                    	    {
                    	    S162=(Token)match(input,S,FOLLOW_S_in_selector1605);  
                    	    stream_S.add(S162);


                    	    }
                    	    break;

                    	default :
                    	    break loop78;
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
                    // 948:9: -> ^( SELECTOR ( selpart )+ )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:948:12: ^( SELECTOR ( selpart )+ )
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:954:1: selpart : ( HASH | CLASSKEYWORD | LBRACE ( S )* attribute RBRACE -> ^( ATTRIBUTE attribute ) | pseudo | INVALID_SELPART );
    public final CSSParser.selpart_return selpart() throws RecognitionException {
        CSSParser.selpart_return retval = new CSSParser.selpart_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token HASH163=null;
        Token CLASSKEYWORD164=null;
        Token LBRACE165=null;
        Token S166=null;
        Token RBRACE168=null;
        Token INVALID_SELPART170=null;
        CSSParser.attribute_return attribute167 = null;

        CSSParser.pseudo_return pseudo169 = null;


        Object HASH163_tree=null;
        Object CLASSKEYWORD164_tree=null;
        Object LBRACE165_tree=null;
        Object S166_tree=null;
        Object RBRACE168_tree=null;
        Object INVALID_SELPART170_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleSubtreeStream stream_attribute=new RewriteRuleSubtreeStream(adaptor,"rule attribute");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:955:5: ( HASH | CLASSKEYWORD | LBRACE ( S )* attribute RBRACE -> ^( ATTRIBUTE attribute ) | pseudo | INVALID_SELPART )
            int alt81=5;
            switch ( input.LA(1) ) {
            case HASH:
                {
                alt81=1;
                }
                break;
            case CLASSKEYWORD:
                {
                alt81=2;
                }
                break;
            case LBRACE:
                {
                alt81=3;
                }
                break;
            case COLON:
                {
                alt81=4;
                }
                break;
            case INVALID_SELPART:
                {
                alt81=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;
            }

            switch (alt81) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:955:8: HASH
                    {
                    root_0 = (Object)adaptor.nil();

                    HASH163=(Token)match(input,HASH,FOLLOW_HASH_in_selpart1652); 
                    HASH163_tree = (Object)adaptor.create(HASH163);
                    adaptor.addChild(root_0, HASH163_tree);


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:956:7: CLASSKEYWORD
                    {
                    root_0 = (Object)adaptor.nil();

                    CLASSKEYWORD164=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_selpart1660); 
                    CLASSKEYWORD164_tree = (Object)adaptor.create(CLASSKEYWORD164);
                    adaptor.addChild(root_0, CLASSKEYWORD164_tree);


                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:957:6: LBRACE ( S )* attribute RBRACE
                    {
                    LBRACE165=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_selpart1667);  
                    stream_LBRACE.add(LBRACE165);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:957:13: ( S )*
                    loop80:
                    do {
                        int alt80=2;
                        int LA80_0 = input.LA(1);

                        if ( (LA80_0==S) ) {
                            alt80=1;
                        }


                        switch (alt80) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:957:13: S
                    	    {
                    	    S166=(Token)match(input,S,FOLLOW_S_in_selpart1669);  
                    	    stream_S.add(S166);


                    	    }
                    	    break;

                    	default :
                    	    break loop80;
                        }
                    } while (true);

                    pushFollow(FOLLOW_attribute_in_selpart1672);
                    attribute167=attribute();

                    state._fsp--;

                    stream_attribute.add(attribute167.getTree());
                    RBRACE168=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_selpart1674);  
                    stream_RBRACE.add(RBRACE168);



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
                    // 957:33: -> ^( ATTRIBUTE attribute )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:957:36: ^( ATTRIBUTE attribute )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:958:7: pseudo
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pseudo_in_selpart1690);
                    pseudo169=pseudo();

                    state._fsp--;

                    adaptor.addChild(root_0, pseudo169.getTree());

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:959:7: INVALID_SELPART
                    {
                    root_0 = (Object)adaptor.nil();

                    INVALID_SELPART170=(Token)match(input,INVALID_SELPART,FOLLOW_INVALID_SELPART_in_selpart1698); 
                    INVALID_SELPART170_tree = (Object)adaptor.create(INVALID_SELPART170);
                    adaptor.addChild(root_0, INVALID_SELPART170_tree);


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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:965:1: attribute : IDENT ( S )* ( ( EQUALS | INCLUDES | DASHMATCH | STARTSWITH | ENDSWITH | CONTAINS ) ( S )* ( IDENT | string ) ( S )* )? ;
    public final CSSParser.attribute_return attribute() throws RecognitionException {
        CSSParser.attribute_return retval = new CSSParser.attribute_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT171=null;
        Token S172=null;
        Token set173=null;
        Token S174=null;
        Token IDENT175=null;
        Token S177=null;
        CSSParser.string_return string176 = null;


        Object IDENT171_tree=null;
        Object S172_tree=null;
        Object set173_tree=null;
        Object S174_tree=null;
        Object IDENT175_tree=null;
        Object S177_tree=null;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:966:2: ( IDENT ( S )* ( ( EQUALS | INCLUDES | DASHMATCH | STARTSWITH | ENDSWITH | CONTAINS ) ( S )* ( IDENT | string ) ( S )* )? )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:966:4: IDENT ( S )* ( ( EQUALS | INCLUDES | DASHMATCH | STARTSWITH | ENDSWITH | CONTAINS ) ( S )* ( IDENT | string ) ( S )* )?
            {
            root_0 = (Object)adaptor.nil();

            IDENT171=(Token)match(input,IDENT,FOLLOW_IDENT_in_attribute1722); 
            IDENT171_tree = (Object)adaptor.create(IDENT171);
            adaptor.addChild(root_0, IDENT171_tree);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:966:10: ( S )*
            loop82:
            do {
                int alt82=2;
                int LA82_0 = input.LA(1);

                if ( (LA82_0==S) ) {
                    alt82=1;
                }


                switch (alt82) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:966:10: S
            	    {
            	    S172=(Token)match(input,S,FOLLOW_S_in_attribute1724); 
            	    S172_tree = (Object)adaptor.create(S172);
            	    adaptor.addChild(root_0, S172_tree);


            	    }
            	    break;

            	default :
            	    break loop82;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:967:4: ( ( EQUALS | INCLUDES | DASHMATCH | STARTSWITH | ENDSWITH | CONTAINS ) ( S )* ( IDENT | string ) ( S )* )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==INCLUDES||LA86_0==EQUALS||LA86_0==DASHMATCH||(LA86_0>=STARTSWITH && LA86_0<=CONTAINS)) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:967:5: ( EQUALS | INCLUDES | DASHMATCH | STARTSWITH | ENDSWITH | CONTAINS ) ( S )* ( IDENT | string ) ( S )*
                    {
                    set173=(Token)input.LT(1);
                    if ( input.LA(1)==INCLUDES||input.LA(1)==EQUALS||input.LA(1)==DASHMATCH||(input.LA(1)>=STARTSWITH && input.LA(1)<=CONTAINS) ) {
                        input.consume();
                        adaptor.addChild(root_0, (Object)adaptor.create(set173));
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:967:72: ( S )*
                    loop83:
                    do {
                        int alt83=2;
                        int LA83_0 = input.LA(1);

                        if ( (LA83_0==S) ) {
                            alt83=1;
                        }


                        switch (alt83) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:967:72: S
                    	    {
                    	    S174=(Token)match(input,S,FOLLOW_S_in_attribute1755); 
                    	    S174_tree = (Object)adaptor.create(S174);
                    	    adaptor.addChild(root_0, S174_tree);


                    	    }
                    	    break;

                    	default :
                    	    break loop83;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:967:75: ( IDENT | string )
                    int alt84=2;
                    int LA84_0 = input.LA(1);

                    if ( (LA84_0==IDENT) ) {
                        alt84=1;
                    }
                    else if ( (LA84_0==INVALID_STRING||LA84_0==STRING) ) {
                        alt84=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 84, 0, input);

                        throw nvae;
                    }
                    switch (alt84) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:967:76: IDENT
                            {
                            IDENT175=(Token)match(input,IDENT,FOLLOW_IDENT_in_attribute1759); 
                            IDENT175_tree = (Object)adaptor.create(IDENT175);
                            adaptor.addChild(root_0, IDENT175_tree);


                            }
                            break;
                        case 2 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:967:84: string
                            {
                            pushFollow(FOLLOW_string_in_attribute1763);
                            string176=string();

                            state._fsp--;

                            adaptor.addChild(root_0, string176.getTree());

                            }
                            break;

                    }

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:967:92: ( S )*
                    loop85:
                    do {
                        int alt85=2;
                        int LA85_0 = input.LA(1);

                        if ( (LA85_0==S) ) {
                            alt85=1;
                        }


                        switch (alt85) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:967:92: S
                    	    {
                    	    S177=(Token)match(input,S,FOLLOW_S_in_attribute1766); 
                    	    S177_tree = (Object)adaptor.create(S177);
                    	    adaptor.addChild(root_0, S177_tree);


                    	    }
                    	    break;

                    	default :
                    	    break loop85;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:970:1: pseudo : pseudocolon ( IDENT | FUNCTION ( IDENT | NUMBER | INDEX ) RPAREN ) ;
    public final CSSParser.pseudo_return pseudo() throws RecognitionException {
        CSSParser.pseudo_return retval = new CSSParser.pseudo_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT179=null;
        Token FUNCTION180=null;
        Token set181=null;
        Token RPAREN182=null;
        CSSParser.pseudocolon_return pseudocolon178 = null;


        Object IDENT179_tree=null;
        Object FUNCTION180_tree=null;
        Object set181_tree=null;
        Object RPAREN182_tree=null;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:971:2: ( pseudocolon ( IDENT | FUNCTION ( IDENT | NUMBER | INDEX ) RPAREN ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:971:4: pseudocolon ( IDENT | FUNCTION ( IDENT | NUMBER | INDEX ) RPAREN )
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_pseudocolon_in_pseudo1780);
            pseudocolon178=pseudocolon();

            state._fsp--;

            root_0 = (Object)adaptor.becomeRoot(pseudocolon178.getTree(), root_0);
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:971:17: ( IDENT | FUNCTION ( IDENT | NUMBER | INDEX ) RPAREN )
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==IDENT) ) {
                alt87=1;
            }
            else if ( (LA87_0==FUNCTION) ) {
                alt87=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 87, 0, input);

                throw nvae;
            }
            switch (alt87) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:971:18: IDENT
                    {
                    IDENT179=(Token)match(input,IDENT,FOLLOW_IDENT_in_pseudo1784); 
                    IDENT179_tree = (Object)adaptor.create(IDENT179);
                    adaptor.addChild(root_0, IDENT179_tree);


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:971:26: FUNCTION ( IDENT | NUMBER | INDEX ) RPAREN
                    {
                    FUNCTION180=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_pseudo1788); 
                    FUNCTION180_tree = (Object)adaptor.create(FUNCTION180);
                    adaptor.addChild(root_0, FUNCTION180_tree);

                    set181=(Token)input.LT(1);
                    if ( input.LA(1)==IDENT||input.LA(1)==NUMBER||input.LA(1)==INDEX ) {
                        input.consume();
                        adaptor.addChild(root_0, (Object)adaptor.create(set181));
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    RPAREN182=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_pseudo1802); 

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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:977:1: pseudocolon : COLON ( COLON )? -> PSEUDO ;
    public final CSSParser.pseudocolon_return pseudocolon() throws RecognitionException {
        CSSParser.pseudocolon_return retval = new CSSParser.pseudocolon_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON183=null;
        Token COLON184=null;

        Object COLON183_tree=null;
        Object COLON184_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:978:2: ( COLON ( COLON )? -> PSEUDO )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:978:4: COLON ( COLON )?
            {
            COLON183=(Token)match(input,COLON,FOLLOW_COLON_in_pseudocolon1823);  
            stream_COLON.add(COLON183);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:978:10: ( COLON )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==COLON) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:978:10: COLON
                    {
                    COLON184=(Token)match(input,COLON,FOLLOW_COLON_in_pseudocolon1825);  
                    stream_COLON.add(COLON184);


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
            // 978:17: -> PSEUDO
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:981:1: string : ( STRING | INVALID_STRING );
    public final CSSParser.string_return string() throws RecognitionException {
        CSSParser.string_return retval = new CSSParser.string_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set185=null;

        Object set185_tree=null;

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:982:2: ( STRING | INVALID_STRING )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:
            {
            root_0 = (Object)adaptor.nil();

            set185=(Token)input.LT(1);
            if ( input.LA(1)==INVALID_STRING||input.LA(1)==STRING ) {
                input.consume();
                adaptor.addChild(root_0, (Object)adaptor.create(set185));
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:987:1: any : ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) ) ( S )* ;
    public final CSSParser.any_return any() throws RecognitionException {
        CSSParser.any_return retval = new CSSParser.any_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT186=null;
        Token CLASSKEYWORD187=null;
        Token NUMBER188=null;
        Token PERCENTAGE189=null;
        Token DIMENSION190=null;
        Token URI192=null;
        Token HASH193=null;
        Token UNIRANGE194=null;
        Token INCLUDES195=null;
        Token COLON196=null;
        Token COMMA197=null;
        Token GREATER198=null;
        Token LESS199=null;
        Token QUESTION200=null;
        Token PERCENT201=null;
        Token EQUALS202=null;
        Token SLASH203=null;
        Token EXCLAMATION204=null;
        Token MINUS205=null;
        Token PLUS206=null;
        Token ASTERISK207=null;
        Token FUNCTION208=null;
        Token S209=null;
        Token RPAREN211=null;
        Token DASHMATCH212=null;
        Token LPAREN213=null;
        Token RPAREN215=null;
        Token LBRACE216=null;
        Token RBRACE218=null;
        Token S219=null;
        CSSParser.string_return string191 = null;

        CSSParser.any_return any210 = null;

        CSSParser.any_return any214 = null;

        CSSParser.any_return any217 = null;


        Object IDENT186_tree=null;
        Object CLASSKEYWORD187_tree=null;
        Object NUMBER188_tree=null;
        Object PERCENTAGE189_tree=null;
        Object DIMENSION190_tree=null;
        Object URI192_tree=null;
        Object HASH193_tree=null;
        Object UNIRANGE194_tree=null;
        Object INCLUDES195_tree=null;
        Object COLON196_tree=null;
        Object COMMA197_tree=null;
        Object GREATER198_tree=null;
        Object LESS199_tree=null;
        Object QUESTION200_tree=null;
        Object PERCENT201_tree=null;
        Object EQUALS202_tree=null;
        Object SLASH203_tree=null;
        Object EXCLAMATION204_tree=null;
        Object MINUS205_tree=null;
        Object PLUS206_tree=null;
        Object ASTERISK207_tree=null;
        Object FUNCTION208_tree=null;
        Object S209_tree=null;
        Object RPAREN211_tree=null;
        Object DASHMATCH212_tree=null;
        Object LPAREN213_tree=null;
        Object RPAREN215_tree=null;
        Object LBRACE216_tree=null;
        Object RBRACE218_tree=null;
        Object S219_tree=null;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:988:2: ( ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) ) ( S )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:988:4: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) ) ( S )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:988:4: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) )
            int alt93=26;
            alt93 = dfa93.predict(input);
            switch (alt93) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:988:6: IDENT
                    {
                    IDENT186=(Token)match(input,IDENT,FOLLOW_IDENT_in_any1862);  
                    stream_IDENT.add(IDENT186);



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
                    // 988:12: -> IDENT
                    {
                        adaptor.addChild(root_0, stream_IDENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:989:6: CLASSKEYWORD
                    {
                    CLASSKEYWORD187=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_any1873);  
                    stream_CLASSKEYWORD.add(CLASSKEYWORD187);



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
                    // 989:19: -> CLASSKEYWORD
                    {
                        adaptor.addChild(root_0, stream_CLASSKEYWORD.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:990:6: NUMBER
                    {
                    NUMBER188=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_any1884);  
                    stream_NUMBER.add(NUMBER188);



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
                    // 990:13: -> NUMBER
                    {
                        adaptor.addChild(root_0, stream_NUMBER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:991:6: PERCENTAGE
                    {
                    PERCENTAGE189=(Token)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_any1895);  
                    stream_PERCENTAGE.add(PERCENTAGE189);



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
                    // 991:17: -> PERCENTAGE
                    {
                        adaptor.addChild(root_0, stream_PERCENTAGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:992:6: DIMENSION
                    {
                    DIMENSION190=(Token)match(input,DIMENSION,FOLLOW_DIMENSION_in_any1905);  
                    stream_DIMENSION.add(DIMENSION190);



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
                    // 992:16: -> DIMENSION
                    {
                        adaptor.addChild(root_0, stream_DIMENSION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:993:6: string
                    {
                    pushFollow(FOLLOW_string_in_any1916);
                    string191=string();

                    state._fsp--;

                    stream_string.add(string191.getTree());


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
                    // 993:13: -> string
                    {
                        adaptor.addChild(root_0, stream_string.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:994:9: URI
                    {
                    URI192=(Token)match(input,URI,FOLLOW_URI_in_any1930);  
                    stream_URI.add(URI192);



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
                    // 994:16: -> URI
                    {
                        adaptor.addChild(root_0, stream_URI.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:995:9: HASH
                    {
                    HASH193=(Token)match(input,HASH,FOLLOW_HASH_in_any1947);  
                    stream_HASH.add(HASH193);



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
                    // 995:14: -> HASH
                    {
                        adaptor.addChild(root_0, stream_HASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:996:9: UNIRANGE
                    {
                    UNIRANGE194=(Token)match(input,UNIRANGE,FOLLOW_UNIRANGE_in_any1961);  
                    stream_UNIRANGE.add(UNIRANGE194);



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
                    // 996:18: -> UNIRANGE
                    {
                        adaptor.addChild(root_0, stream_UNIRANGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:997:9: INCLUDES
                    {
                    INCLUDES195=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_any1975);  
                    stream_INCLUDES.add(INCLUDES195);



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
                    // 997:18: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:998:9: COLON
                    {
                    COLON196=(Token)match(input,COLON,FOLLOW_COLON_in_any1989);  
                    stream_COLON.add(COLON196);



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
                    // 998:15: -> COLON
                    {
                        adaptor.addChild(root_0, stream_COLON.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:999:9: COMMA
                    {
                    COMMA197=(Token)match(input,COMMA,FOLLOW_COMMA_in_any2003);  
                    stream_COMMA.add(COMMA197);



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
                    // 999:15: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1000:9: GREATER
                    {
                    GREATER198=(Token)match(input,GREATER,FOLLOW_GREATER_in_any2017);  
                    stream_GREATER.add(GREATER198);



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
                    // 1000:17: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1001:9: LESS
                    {
                    LESS199=(Token)match(input,LESS,FOLLOW_LESS_in_any2031);  
                    stream_LESS.add(LESS199);



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
                    // 1001:14: -> LESS
                    {
                        adaptor.addChild(root_0, stream_LESS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1002:9: QUESTION
                    {
                    QUESTION200=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_any2045);  
                    stream_QUESTION.add(QUESTION200);



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
                    // 1002:18: -> QUESTION
                    {
                        adaptor.addChild(root_0, stream_QUESTION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1003:9: PERCENT
                    {
                    PERCENT201=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_any2059);  
                    stream_PERCENT.add(PERCENT201);



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
                    // 1003:17: -> PERCENT
                    {
                        adaptor.addChild(root_0, stream_PERCENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1004:9: EQUALS
                    {
                    EQUALS202=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_any2073);  
                    stream_EQUALS.add(EQUALS202);



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
                    // 1004:16: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 18 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1005:9: SLASH
                    {
                    SLASH203=(Token)match(input,SLASH,FOLLOW_SLASH_in_any2087);  
                    stream_SLASH.add(SLASH203);



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
                    // 1005:15: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 19 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1006:9: EXCLAMATION
                    {
                    EXCLAMATION204=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_any2101);  
                    stream_EXCLAMATION.add(EXCLAMATION204);



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
                    // 1006:21: -> EXCLAMATION
                    {
                        adaptor.addChild(root_0, stream_EXCLAMATION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 20 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1007:6: MINUS
                    {
                    MINUS205=(Token)match(input,MINUS,FOLLOW_MINUS_in_any2112);  
                    stream_MINUS.add(MINUS205);



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
                    // 1007:12: -> MINUS
                    {
                        adaptor.addChild(root_0, stream_MINUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 21 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1008:6: PLUS
                    {
                    PLUS206=(Token)match(input,PLUS,FOLLOW_PLUS_in_any2123);  
                    stream_PLUS.add(PLUS206);



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
                    // 1008:11: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 22 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1009:6: ASTERISK
                    {
                    ASTERISK207=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_any2134);  
                    stream_ASTERISK.add(ASTERISK207);



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
                    // 1009:15: -> ASTERISK
                    {
                        adaptor.addChild(root_0, stream_ASTERISK.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 23 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1010:9: FUNCTION ( S )* ( any )* RPAREN
                    {
                    FUNCTION208=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_any2151);  
                    stream_FUNCTION.add(FUNCTION208);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1010:18: ( S )*
                    loop89:
                    do {
                        int alt89=2;
                        alt89 = dfa89.predict(input);
                        switch (alt89) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1010:18: S
                    	    {
                    	    S209=(Token)match(input,S,FOLLOW_S_in_any2153);  
                    	    stream_S.add(S209);


                    	    }
                    	    break;

                    	default :
                    	    break loop89;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1010:21: ( any )*
                    loop90:
                    do {
                        int alt90=2;
                        alt90 = dfa90.predict(input);
                        switch (alt90) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1010:21: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_any2156);
                    	    any210=any();

                    	    state._fsp--;

                    	    stream_any.add(any210.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop90;
                        }
                    } while (true);

                    RPAREN211=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_any2159);  
                    stream_RPAREN.add(RPAREN211);



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
                    // 1010:33: -> ^( FUNCTION ( any )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1010:36: ^( FUNCTION ( any )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_FUNCTION.nextNode(), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1010:47: ( any )*
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1011:9: DASHMATCH
                    {
                    DASHMATCH212=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_any2179);  
                    stream_DASHMATCH.add(DASHMATCH212);



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
                    // 1011:19: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 25 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1012:9: LPAREN ( any )* RPAREN
                    {
                    LPAREN213=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_any2193);  
                    stream_LPAREN.add(LPAREN213);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1012:16: ( any )*
                    loop91:
                    do {
                        int alt91=2;
                        alt91 = dfa91.predict(input);
                        switch (alt91) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1012:16: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_any2195);
                    	    any214=any();

                    	    state._fsp--;

                    	    stream_any.add(any214.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop91;
                        }
                    } while (true);

                    RPAREN215=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_any2198);  
                    stream_RPAREN.add(RPAREN215);



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
                    // 1012:28: -> ^( PARENBLOCK ( any )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1012:31: ^( PARENBLOCK ( any )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PARENBLOCK, "PARENBLOCK"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1012:44: ( any )*
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1013:9: LBRACE ( any )* RBRACE
                    {
                    LBRACE216=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_any2217);  
                    stream_LBRACE.add(LBRACE216);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1013:16: ( any )*
                    loop92:
                    do {
                        int alt92=2;
                        alt92 = dfa92.predict(input);
                        switch (alt92) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1013:16: any
                    	    {
                    	    pushFollow(FOLLOW_any_in_any2219);
                    	    any217=any();

                    	    state._fsp--;

                    	    stream_any.add(any217.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop92;
                        }
                    } while (true);

                    RBRACE218=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_any2222);  
                    stream_RBRACE.add(RBRACE218);



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
                    // 1013:28: -> ^( BRACEBLOCK ( any )* )
                    {
                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1013:31: ^( BRACEBLOCK ( any )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BRACEBLOCK, "BRACEBLOCK"), root_1);

                        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1013:44: ( any )*
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1014:8: ( S )*
            loop94:
            do {
                int alt94=2;
                alt94 = dfa94.predict(input);
                switch (alt94) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1014:8: S
            	    {
            	    S219=(Token)match(input,S,FOLLOW_S_in_any2240);  
            	    stream_S.add(S219);


            	    }
            	    break;

            	default :
            	    break loop94;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1016:1: nostatement : ( RCURLY -> RCURLY | SEMICOLON -> SEMICOLON ) ;
    public final CSSParser.nostatement_return nostatement() throws RecognitionException {
        CSSParser.nostatement_return retval = new CSSParser.nostatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token RCURLY220=null;
        Token SEMICOLON221=null;

        Object RCURLY220_tree=null;
        Object SEMICOLON221_tree=null;
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");

        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1018:3: ( ( RCURLY -> RCURLY | SEMICOLON -> SEMICOLON ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1018:5: ( RCURLY -> RCURLY | SEMICOLON -> SEMICOLON )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1018:5: ( RCURLY -> RCURLY | SEMICOLON -> SEMICOLON )
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==RCURLY) ) {
                alt95=1;
            }
            else if ( (LA95_0==SEMICOLON) ) {
                alt95=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 95, 0, input);

                throw nvae;
            }
            switch (alt95) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1018:7: RCURLY
                    {
                    RCURLY220=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_nostatement2255);  
                    stream_RCURLY.add(RCURLY220);



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
                    // 1018:14: -> RCURLY
                    {
                        adaptor.addChild(root_0, stream_RCURLY.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1019:7: SEMICOLON
                    {
                    SEMICOLON221=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_nostatement2269);  
                    stream_SEMICOLON.add(SEMICOLON221);



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
                    // 1019:17: -> SEMICOLON
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1022:1: noprop : ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN ) ( S )* ;
    public final CSSParser.noprop_return noprop() throws RecognitionException {
        CSSParser.noprop_return retval = new CSSParser.noprop_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CLASSKEYWORD222=null;
        Token NUMBER223=null;
        Token COMMA224=null;
        Token GREATER225=null;
        Token LESS226=null;
        Token QUESTION227=null;
        Token PERCENT228=null;
        Token EQUALS229=null;
        Token SLASH230=null;
        Token EXCLAMATION231=null;
        Token PLUS232=null;
        Token ASTERISK233=null;
        Token DASHMATCH234=null;
        Token INCLUDES235=null;
        Token COLON236=null;
        Token STRING_CHAR237=null;
        Token INVALID_TOKEN238=null;
        Token S239=null;

        Object CLASSKEYWORD222_tree=null;
        Object NUMBER223_tree=null;
        Object COMMA224_tree=null;
        Object GREATER225_tree=null;
        Object LESS226_tree=null;
        Object QUESTION227_tree=null;
        Object PERCENT228_tree=null;
        Object EQUALS229_tree=null;
        Object SLASH230_tree=null;
        Object EXCLAMATION231_tree=null;
        Object PLUS232_tree=null;
        Object ASTERISK233_tree=null;
        Object DASHMATCH234_tree=null;
        Object INCLUDES235_tree=null;
        Object COLON236_tree=null;
        Object STRING_CHAR237_tree=null;
        Object INVALID_TOKEN238_tree=null;
        Object S239_tree=null;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1024:2: ( ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN ) ( S )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1024:4: ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN ) ( S )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1024:4: ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN )
            int alt96=17;
            alt96 = dfa96.predict(input);
            switch (alt96) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1024:6: CLASSKEYWORD
                    {
                    CLASSKEYWORD222=(Token)match(input,CLASSKEYWORD,FOLLOW_CLASSKEYWORD_in_noprop2292);  
                    stream_CLASSKEYWORD.add(CLASSKEYWORD222);



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
                    // 1024:19: -> CLASSKEYWORD
                    {
                        adaptor.addChild(root_0, stream_CLASSKEYWORD.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1025:8: NUMBER
                    {
                    NUMBER223=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_noprop2305);  
                    stream_NUMBER.add(NUMBER223);



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
                    // 1025:15: -> NUMBER
                    {
                        adaptor.addChild(root_0, stream_NUMBER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1026:7: COMMA
                    {
                    COMMA224=(Token)match(input,COMMA,FOLLOW_COMMA_in_noprop2317);  
                    stream_COMMA.add(COMMA224);



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
                    // 1026:13: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1027:7: GREATER
                    {
                    GREATER225=(Token)match(input,GREATER,FOLLOW_GREATER_in_noprop2329);  
                    stream_GREATER.add(GREATER225);



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
                    // 1027:15: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1028:7: LESS
                    {
                    LESS226=(Token)match(input,LESS,FOLLOW_LESS_in_noprop2341);  
                    stream_LESS.add(LESS226);



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
                    // 1028:12: -> LESS
                    {
                        adaptor.addChild(root_0, stream_LESS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1029:7: QUESTION
                    {
                    QUESTION227=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_noprop2353);  
                    stream_QUESTION.add(QUESTION227);



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
                    // 1029:16: -> QUESTION
                    {
                        adaptor.addChild(root_0, stream_QUESTION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1030:7: PERCENT
                    {
                    PERCENT228=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_noprop2365);  
                    stream_PERCENT.add(PERCENT228);



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
                    // 1030:15: -> PERCENT
                    {
                        adaptor.addChild(root_0, stream_PERCENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1031:7: EQUALS
                    {
                    EQUALS229=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_noprop2377);  
                    stream_EQUALS.add(EQUALS229);



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
                    // 1031:14: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1032:7: SLASH
                    {
                    SLASH230=(Token)match(input,SLASH,FOLLOW_SLASH_in_noprop2389);  
                    stream_SLASH.add(SLASH230);



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
                    // 1032:13: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1033:7: EXCLAMATION
                    {
                    EXCLAMATION231=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_noprop2401);  
                    stream_EXCLAMATION.add(EXCLAMATION231);



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
                    // 1033:19: -> EXCLAMATION
                    {
                        adaptor.addChild(root_0, stream_EXCLAMATION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1034:7: PLUS
                    {
                    PLUS232=(Token)match(input,PLUS,FOLLOW_PLUS_in_noprop2413);  
                    stream_PLUS.add(PLUS232);



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
                    // 1034:12: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1035:7: ASTERISK
                    {
                    ASTERISK233=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_noprop2425);  
                    stream_ASTERISK.add(ASTERISK233);



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
                    // 1035:16: -> ASTERISK
                    {
                        adaptor.addChild(root_0, stream_ASTERISK.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1036:7: DASHMATCH
                    {
                    DASHMATCH234=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_noprop2440);  
                    stream_DASHMATCH.add(DASHMATCH234);



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
                    // 1036:17: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1037:7: INCLUDES
                    {
                    INCLUDES235=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_noprop2452);  
                    stream_INCLUDES.add(INCLUDES235);



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
                    // 1037:16: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1038:7: COLON
                    {
                    COLON236=(Token)match(input,COLON,FOLLOW_COLON_in_noprop2464);  
                    stream_COLON.add(COLON236);



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
                    // 1038:13: -> COLON
                    {
                        adaptor.addChild(root_0, stream_COLON.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1039:7: STRING_CHAR
                    {
                    STRING_CHAR237=(Token)match(input,STRING_CHAR,FOLLOW_STRING_CHAR_in_noprop2476);  
                    stream_STRING_CHAR.add(STRING_CHAR237);



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
                    // 1039:19: -> STRING_CHAR
                    {
                        adaptor.addChild(root_0, stream_STRING_CHAR.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1040:7: INVALID_TOKEN
                    {
                    INVALID_TOKEN238=(Token)match(input,INVALID_TOKEN,FOLLOW_INVALID_TOKEN_in_noprop2488);  
                    stream_INVALID_TOKEN.add(INVALID_TOKEN238);



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
                    // 1040:21: -> INVALID_TOKEN
                    {
                        adaptor.addChild(root_0, stream_INVALID_TOKEN.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;

            }

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1041:8: ( S )*
            loop97:
            do {
                int alt97=2;
                alt97 = dfa97.predict(input);
                switch (alt97) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1041:8: S
            	    {
            	    S239=(Token)match(input,S,FOLLOW_S_in_noprop2501);  
            	    stream_S.add(S239);


            	    }
            	    break;

            	default :
            	    break loop97;
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
    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1043:1: norule : ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH | RPAREN -> RPAREN | '#' | '^' ) ;
    public final CSSParser.norule_return norule() throws RecognitionException {
        CSSParser.norule_return retval = new CSSParser.norule_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NUMBER240=null;
        Token PERCENTAGE241=null;
        Token DIMENSION242=null;
        Token URI244=null;
        Token UNIRANGE245=null;
        Token INCLUDES246=null;
        Token COMMA247=null;
        Token GREATER248=null;
        Token LESS249=null;
        Token QUESTION250=null;
        Token PERCENT251=null;
        Token EQUALS252=null;
        Token SLASH253=null;
        Token EXCLAMATION254=null;
        Token MINUS255=null;
        Token PLUS256=null;
        Token DASHMATCH257=null;
        Token RPAREN258=null;
        Token char_literal259=null;
        Token char_literal260=null;
        CSSParser.string_return string243 = null;


        Object NUMBER240_tree=null;
        Object PERCENTAGE241_tree=null;
        Object DIMENSION242_tree=null;
        Object URI244_tree=null;
        Object UNIRANGE245_tree=null;
        Object INCLUDES246_tree=null;
        Object COMMA247_tree=null;
        Object GREATER248_tree=null;
        Object LESS249_tree=null;
        Object QUESTION250_tree=null;
        Object PERCENT251_tree=null;
        Object EQUALS252_tree=null;
        Object SLASH253_tree=null;
        Object EXCLAMATION254_tree=null;
        Object MINUS255_tree=null;
        Object PLUS256_tree=null;
        Object DASHMATCH257_tree=null;
        Object RPAREN258_tree=null;
        Object char_literal259_tree=null;
        Object char_literal260_tree=null;
        RewriteRuleTokenStream stream_INCLUDES=new RewriteRuleTokenStream(adaptor,"token INCLUDES");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
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
        RewriteRuleTokenStream stream_99=new RewriteRuleTokenStream(adaptor,"token 99");
        RewriteRuleTokenStream stream_100=new RewriteRuleTokenStream(adaptor,"token 100");
        RewriteRuleTokenStream stream_URI=new RewriteRuleTokenStream(adaptor,"token URI");
        RewriteRuleSubtreeStream stream_string=new RewriteRuleSubtreeStream(adaptor,"rule string");
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1045:3: ( ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH | RPAREN -> RPAREN | '#' | '^' ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1045:5: ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH | RPAREN -> RPAREN | '#' | '^' )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1045:5: ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH | RPAREN -> RPAREN | '#' | '^' )
            int alt98=21;
            alt98 = dfa98.predict(input);
            switch (alt98) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1045:7: NUMBER
                    {
                    NUMBER240=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_norule2516);  
                    stream_NUMBER.add(NUMBER240);



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
                    // 1045:14: -> NUMBER
                    {
                        adaptor.addChild(root_0, stream_NUMBER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1046:8: PERCENTAGE
                    {
                    PERCENTAGE241=(Token)match(input,PERCENTAGE,FOLLOW_PERCENTAGE_in_norule2529);  
                    stream_PERCENTAGE.add(PERCENTAGE241);



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
                    // 1046:19: -> PERCENTAGE
                    {
                        adaptor.addChild(root_0, stream_PERCENTAGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1047:8: DIMENSION
                    {
                    DIMENSION242=(Token)match(input,DIMENSION,FOLLOW_DIMENSION_in_norule2541);  
                    stream_DIMENSION.add(DIMENSION242);



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
                    // 1047:18: -> DIMENSION
                    {
                        adaptor.addChild(root_0, stream_DIMENSION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1048:8: string
                    {
                    pushFollow(FOLLOW_string_in_norule2554);
                    string243=string();

                    state._fsp--;

                    stream_string.add(string243.getTree());


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
                    // 1048:15: -> string
                    {
                        adaptor.addChild(root_0, stream_string.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1049:9: URI
                    {
                    URI244=(Token)match(input,URI,FOLLOW_URI_in_norule2568);  
                    stream_URI.add(URI244);



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
                    // 1049:16: -> URI
                    {
                        adaptor.addChild(root_0, stream_URI.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1050:9: UNIRANGE
                    {
                    UNIRANGE245=(Token)match(input,UNIRANGE,FOLLOW_UNIRANGE_in_norule2585);  
                    stream_UNIRANGE.add(UNIRANGE245);



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
                    // 1050:18: -> UNIRANGE
                    {
                        adaptor.addChild(root_0, stream_UNIRANGE.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1051:9: INCLUDES
                    {
                    INCLUDES246=(Token)match(input,INCLUDES,FOLLOW_INCLUDES_in_norule2599);  
                    stream_INCLUDES.add(INCLUDES246);



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
                    // 1051:18: -> INCLUDES
                    {
                        adaptor.addChild(root_0, stream_INCLUDES.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1052:9: COMMA
                    {
                    COMMA247=(Token)match(input,COMMA,FOLLOW_COMMA_in_norule2613);  
                    stream_COMMA.add(COMMA247);



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
                    // 1052:15: -> COMMA
                    {
                        adaptor.addChild(root_0, stream_COMMA.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1053:9: GREATER
                    {
                    GREATER248=(Token)match(input,GREATER,FOLLOW_GREATER_in_norule2627);  
                    stream_GREATER.add(GREATER248);



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
                    // 1053:17: -> GREATER
                    {
                        adaptor.addChild(root_0, stream_GREATER.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1054:9: LESS
                    {
                    LESS249=(Token)match(input,LESS,FOLLOW_LESS_in_norule2641);  
                    stream_LESS.add(LESS249);



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
                    // 1054:14: -> LESS
                    {
                        adaptor.addChild(root_0, stream_LESS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1055:9: QUESTION
                    {
                    QUESTION250=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_norule2655);  
                    stream_QUESTION.add(QUESTION250);



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
                    // 1055:18: -> QUESTION
                    {
                        adaptor.addChild(root_0, stream_QUESTION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1056:9: PERCENT
                    {
                    PERCENT251=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_norule2669);  
                    stream_PERCENT.add(PERCENT251);



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
                    // 1056:17: -> PERCENT
                    {
                        adaptor.addChild(root_0, stream_PERCENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1057:9: EQUALS
                    {
                    EQUALS252=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_norule2683);  
                    stream_EQUALS.add(EQUALS252);



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
                    // 1057:16: -> EQUALS
                    {
                        adaptor.addChild(root_0, stream_EQUALS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1058:9: SLASH
                    {
                    SLASH253=(Token)match(input,SLASH,FOLLOW_SLASH_in_norule2697);  
                    stream_SLASH.add(SLASH253);



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
                    // 1058:15: -> SLASH
                    {
                        adaptor.addChild(root_0, stream_SLASH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1059:9: EXCLAMATION
                    {
                    EXCLAMATION254=(Token)match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_norule2711);  
                    stream_EXCLAMATION.add(EXCLAMATION254);



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
                    // 1059:21: -> EXCLAMATION
                    {
                        adaptor.addChild(root_0, stream_EXCLAMATION.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1060:8: MINUS
                    {
                    MINUS255=(Token)match(input,MINUS,FOLLOW_MINUS_in_norule2724);  
                    stream_MINUS.add(MINUS255);



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
                    // 1060:14: -> MINUS
                    {
                        adaptor.addChild(root_0, stream_MINUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 17 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1061:8: PLUS
                    {
                    PLUS256=(Token)match(input,PLUS,FOLLOW_PLUS_in_norule2737);  
                    stream_PLUS.add(PLUS256);



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
                    // 1061:13: -> PLUS
                    {
                        adaptor.addChild(root_0, stream_PLUS.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 18 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1062:9: DASHMATCH
                    {
                    DASHMATCH257=(Token)match(input,DASHMATCH,FOLLOW_DASHMATCH_in_norule2751);  
                    stream_DASHMATCH.add(DASHMATCH257);



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
                    // 1062:19: -> DASHMATCH
                    {
                        adaptor.addChild(root_0, stream_DASHMATCH.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 19 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1063:9: RPAREN
                    {
                    RPAREN258=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_norule2765);  
                    stream_RPAREN.add(RPAREN258);



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
                    // 1063:16: -> RPAREN
                    {
                        adaptor.addChild(root_0, stream_RPAREN.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 20 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1064:9: '#'
                    {
                    char_literal259=(Token)match(input,99,FOLLOW_99_in_norule2779);  
                    stream_99.add(char_literal259);


                    }
                    break;
                case 21 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1065:9: '^'
                    {
                    char_literal260=(Token)match(input,100,FOLLOW_100_in_norule2790);  
                    stream_100.add(char_literal260);


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
    protected DFA75 dfa75 = new DFA75(this);
    protected DFA76 dfa76 = new DFA76(this);
    protected DFA77 dfa77 = new DFA77(this);
    protected DFA78 dfa78 = new DFA78(this);
    protected DFA93 dfa93 = new DFA93(this);
    protected DFA89 dfa89 = new DFA89(this);
    protected DFA90 dfa90 = new DFA90(this);
    protected DFA91 dfa91 = new DFA91(this);
    protected DFA92 dfa92 = new DFA92(this);
    protected DFA94 dfa94 = new DFA94(this);
    protected DFA96 dfa96 = new DFA96(this);
    protected DFA97 dfa97 = new DFA97(this);
    protected DFA98 dfa98 = new DFA98(this);
    static final String DFA1_eotS =
        "\30\uffff";
    static final String DFA1_eofS =
        "\1\1\27\uffff";
    static final String DFA1_minS =
        "\1\37\27\uffff";
    static final String DFA1_maxS =
        "\1\117\27\uffff";
    static final String DFA1_acceptS =
        "\1\uffff\1\2\25\uffff\1\1";
    static final String DFA1_specialS =
        "\30\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\27\5\uffff\1\1\4\uffff\1\1\1\uffff\5\1\3\uffff\2\1\5\uffff"+
            "\12\1\11\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 754:4: ( S )*";
        }
    }
    static final String DFA3_eotS =
        "\64\uffff";
    static final String DFA3_eofS =
        "\1\1\20\uffff\1\1\42\uffff";
    static final String DFA3_minS =
        "\1\45\20\uffff\1\30\42\uffff";
    static final String DFA3_maxS =
        "\1\117\20\uffff\1\115\42\uffff";
    static final String DFA3_acceptS =
        "\1\uffff\1\1\24\uffff\1\2\3\1\32\uffff";
    static final String DFA3_specialS =
        "\64\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\26\4\uffff\1\1\1\uffff\2\1\1\21\2\1\3\uffff\2\1\5\uffff\12"+
            "\1\11\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\1\6\uffff\1\1\12\uffff\1\30\1\uffff\2\1\1\27\2\1\1\uffff"+
            "\1\31\1\uffff\23\1\6\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "754:8: ( declarations -> ^( INLINESTYLE declarations ) | ( inlineset )+ -> ^( INLINESTYLE ( inlineset )+ ) )";
        }
    }
    static final String DFA4_eotS =
        "\53\uffff";
    static final String DFA4_eofS =
        "\1\1\52\uffff";
    static final String DFA4_minS =
        "\1\27\52\uffff";
    static final String DFA4_maxS =
        "\1\144\52\uffff";
    static final String DFA4_acceptS =
        "\1\uffff\1\6\1\1\1\2\1\3\1\4\1\uffff\1\5\43\uffff";
    static final String DFA4_specialS =
        "\53\uffff}>";
    static final String[] DFA4_transitionS = {
            "\2\7\1\uffff\1\7\2\uffff\1\7\1\uffff\1\4\1\2\1\3\3\7\1\uffff"+
            "\1\5\4\7\1\uffff\1\7\1\5\3\7\2\uffff\22\7\1\uffff\1\7\6\uffff"+
            "\1\7\25\uffff\2\7",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 760:4: ( CDO | CDC | S | nostatement | statement )*";
        }
    }
    static final String DFA5_eotS =
        "\45\uffff";
    static final String DFA5_eofS =
        "\45\uffff";
    static final String DFA5_minS =
        "\1\27\44\uffff";
    static final String DFA5_maxS =
        "\1\144\44\uffff";
    static final String DFA5_acceptS =
        "\1\uffff\1\1\33\uffff\1\2\7\uffff";
    static final String DFA5_specialS =
        "\45\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\35\1\1\1\uffff\1\1\2\uffff\1\35\4\uffff\3\35\2\uffff\3\35"+
            "\1\1\1\uffff\1\1\1\uffff\3\1\2\uffff\22\1\1\uffff\1\1\6\uffff"+
            "\1\1\25\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "764:1: statement : ( ruleset | atstatement );";
        }
    }
    static final String DFA7_eotS =
        "\27\uffff";
    static final String DFA7_eofS =
        "\27\uffff";
    static final String DFA7_minS =
        "\1\37\26\uffff";
    static final String DFA7_maxS =
        "\1\117\26\uffff";
    static final String DFA7_acceptS =
        "\1\uffff\1\2\24\uffff\1\1";
    static final String DFA7_specialS =
        "\27\uffff}>";
    static final String[] DFA7_transitionS = {
            "\1\26\6\uffff\1\1\3\uffff\1\1\1\uffff\5\1\3\uffff\2\1\5\uffff"+
            "\12\1\11\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 775:11: ( S )*";
        }
    }
    static final String DFA10_eotS =
        "\37\uffff";
    static final String DFA10_eofS =
        "\37\uffff";
    static final String DFA10_minS =
        "\1\30\36\uffff";
    static final String DFA10_maxS =
        "\1\144\36\uffff";
    static final String DFA10_acceptS =
        "\1\uffff\1\2\34\uffff\1\1";
    static final String DFA10_specialS =
        "\37\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\1\1\uffff\1\1\4\uffff\1\36\6\uffff\1\1\3\uffff\1\1\1\uffff"+
            "\1\1\1\uffff\3\1\2\uffff\22\1\1\uffff\1\1\6\uffff\1\1\25\uffff"+
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
            return "()* loopback of 778:10: ( S )*";
        }
    }
    static final String DFA12_eotS =
        "\36\uffff";
    static final String DFA12_eofS =
        "\36\uffff";
    static final String DFA12_minS =
        "\1\30\35\uffff";
    static final String DFA12_maxS =
        "\1\144\35\uffff";
    static final String DFA12_acceptS =
        "\1\uffff\1\2\1\1\33\uffff";
    static final String DFA12_specialS =
        "\36\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\2\1\uffff\1\2\13\uffff\1\1\3\uffff\1\2\1\uffff\1\2\1\uffff"+
            "\3\2\2\uffff\22\2\1\uffff\1\2\6\uffff\1\2\25\uffff\2\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 778:13: ( ruleset ( S )* )*";
        }
    }
    static final String DFA11_eotS =
        "\37\uffff";
    static final String DFA11_eofS =
        "\37\uffff";
    static final String DFA11_minS =
        "\1\30\36\uffff";
    static final String DFA11_maxS =
        "\1\144\36\uffff";
    static final String DFA11_acceptS =
        "\1\uffff\1\2\34\uffff\1\1";
    static final String DFA11_specialS =
        "\37\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\1\1\uffff\1\1\4\uffff\1\36\6\uffff\1\1\3\uffff\1\1\1\uffff"+
            "\1\1\1\uffff\3\1\2\uffff\22\1\1\uffff\1\1\6\uffff\1\1\25\uffff"+
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
            return "()* loopback of 778:22: ( S )*";
        }
    }
    static final String DFA14_eotS =
        "\34\uffff";
    static final String DFA14_eofS =
        "\34\uffff";
    static final String DFA14_minS =
        "\1\30\33\uffff";
    static final String DFA14_maxS =
        "\1\115\33\uffff";
    static final String DFA14_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA14_specialS =
        "\34\uffff}>";
    static final String[] DFA14_transitionS = {
            "\1\2\15\uffff\1\1\3\uffff\1\2\1\uffff\1\2\1\uffff\3\2\1\uffff"+
            "\1\2\1\uffff\23\2\6\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 779:24: ( any )*";
        }
    }
    static final String DFA20_eotS =
        "\30\uffff";
    static final String DFA20_eofS =
        "\30\uffff";
    static final String DFA20_minS =
        "\1\37\27\uffff";
    static final String DFA20_maxS =
        "\1\117\27\uffff";
    static final String DFA20_acceptS =
        "\1\uffff\1\2\25\uffff\1\1";
    static final String DFA20_specialS =
        "\30\uffff}>";
    static final String[] DFA20_transitionS = {
            "\1\27\6\uffff\1\1\3\uffff\7\1\3\uffff\2\1\5\uffff\12\1\11\uffff"+
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
            return "()* loopback of 789:10: ( S )*";
        }
    }
    static final String DFA23_eotS =
        "\27\uffff";
    static final String DFA23_eofS =
        "\27\uffff";
    static final String DFA23_minS =
        "\1\37\26\uffff";
    static final String DFA23_maxS =
        "\1\117\26\uffff";
    static final String DFA23_acceptS =
        "\1\uffff\1\2\24\uffff\1\1";
    static final String DFA23_specialS =
        "\27\uffff}>";
    static final String[] DFA23_transitionS = {
            "\1\26\6\uffff\1\1\3\uffff\1\1\1\uffff\5\1\3\uffff\2\1\5\uffff"+
            "\12\1\11\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 800:26: ( S )*";
        }
    }
    static final String DFA37_eotS =
        "\35\uffff";
    static final String DFA37_eofS =
        "\35\uffff";
    static final String DFA37_minS =
        "\1\30\34\uffff";
    static final String DFA37_maxS =
        "\1\144\34\uffff";
    static final String DFA37_acceptS =
        "\1\uffff\1\1\6\uffff\1\2\24\uffff";
    static final String DFA37_specialS =
        "\35\uffff}>";
    static final String[] DFA37_transitionS = {
            "\1\10\1\uffff\1\1\17\uffff\1\1\1\uffff\1\10\1\uffff\1\1\2\10"+
            "\2\uffff\1\10\1\1\4\10\1\1\11\10\1\1\1\10\1\uffff\1\1\6\uffff"+
            "\1\10\25\uffff\2\10",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "818:1: ruleset : ( combined_selector ( COMMA ( S )* combined_selector )* LCURLY ( S )* declarations RCURLY -> ^( RULE ( combined_selector )+ declarations ) | norule -> INVALID_STATEMENT );";
        }
    }
    static final String DFA36_eotS =
        "\27\uffff";
    static final String DFA36_eofS =
        "\27\uffff";
    static final String DFA36_minS =
        "\1\37\26\uffff";
    static final String DFA36_maxS =
        "\1\117\26\uffff";
    static final String DFA36_acceptS =
        "\1\uffff\1\2\24\uffff\1\1";
    static final String DFA36_specialS =
        "\27\uffff}>";
    static final String[] DFA36_transitionS = {
            "\1\26\6\uffff\1\1\3\uffff\1\1\1\uffff\5\1\3\uffff\2\1\5\uffff"+
            "\12\1\11\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 820:11: ( S )*";
        }
    }
    static final String DFA38_eotS =
        "\30\uffff";
    static final String DFA38_eofS =
        "\1\24\27\uffff";
    static final String DFA38_minS =
        "\1\46\27\uffff";
    static final String DFA38_maxS =
        "\1\117\27\uffff";
    static final String DFA38_acceptS =
        "\1\uffff\1\1\22\uffff\1\2\3\uffff";
    static final String DFA38_specialS =
        "\30\uffff}>";
    static final String[] DFA38_transitionS = {
            "\1\24\3\uffff\1\1\1\24\1\1\1\24\3\1\3\uffff\2\1\5\uffff\12\1"+
            "\11\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "833:4: ( declaration )?";
        }
    }
    static final String DFA39_eotS =
        "\31\uffff";
    static final String DFA39_eofS =
        "\1\1\30\uffff";
    static final String DFA39_minS =
        "\1\37\30\uffff";
    static final String DFA39_maxS =
        "\1\117\30\uffff";
    static final String DFA39_acceptS =
        "\1\uffff\1\2\26\uffff\1\1";
    static final String DFA39_specialS =
        "\31\uffff}>";
    static final String[] DFA39_transitionS = {
            "\1\30\6\uffff\1\1\3\uffff\7\1\3\uffff\2\1\5\uffff\12\1\11\uffff"+
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
            return "()* loopback of 833:28: ( S )*";
        }
    }
    static final String DFA40_eotS =
        "\30\uffff";
    static final String DFA40_eofS =
        "\1\24\27\uffff";
    static final String DFA40_minS =
        "\1\46\27\uffff";
    static final String DFA40_maxS =
        "\1\117\27\uffff";
    static final String DFA40_acceptS =
        "\1\uffff\1\1\22\uffff\1\2\3\uffff";
    static final String DFA40_specialS =
        "\30\uffff}>";
    static final String[] DFA40_transitionS = {
            "\1\24\3\uffff\1\1\1\24\1\1\1\24\3\1\3\uffff\2\1\5\uffff\12\1"+
            "\11\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "833:31: ( declaration )?";
        }
    }
    static final String DFA46_eotS =
        "\24\uffff";
    static final String DFA46_eofS =
        "\24\uffff";
    static final String DFA46_minS =
        "\1\52\23\uffff";
    static final String DFA46_maxS =
        "\1\117\23\uffff";
    static final String DFA46_acceptS =
        "\1\uffff\1\1\1\uffff\1\2\20\uffff";
    static final String DFA46_specialS =
        "\24\uffff}>";
    static final String[] DFA46_transitionS = {
            "\1\1\1\uffff\1\3\1\uffff\2\3\1\1\3\uffff\2\3\5\uffff\12\3\11"+
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
            return "837:1: declaration : ( property COLON ( S )* ( terms )? ( important )? -> ^( DECLARATION ( important )? property ( terms )? ) | noprop ( any )* -> INVALID_DECLARATION );";
        }
    }
    static final String DFA42_eotS =
        "\43\uffff";
    static final String DFA42_eofS =
        "\1\1\42\uffff";
    static final String DFA42_minS =
        "\1\30\42\uffff";
    static final String DFA42_maxS =
        "\1\115\42\uffff";
    static final String DFA42_acceptS =
        "\1\uffff\1\2\40\uffff\1\1";
    static final String DFA42_specialS =
        "\43\uffff}>";
    static final String[] DFA42_transitionS = {
            "\1\1\6\uffff\1\42\5\uffff\2\1\1\uffff\1\1\1\uffff\11\1\1\uffff"+
            "\23\1\6\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 842:19: ( S )*";
        }
    }
    static final String DFA43_eotS =
        "\42\uffff";
    static final String DFA43_eofS =
        "\1\35\41\uffff";
    static final String DFA43_minS =
        "\1\30\41\uffff";
    static final String DFA43_maxS =
        "\1\115\41\uffff";
    static final String DFA43_acceptS =
        "\1\uffff\1\1\33\uffff\1\2\4\uffff";
    static final String DFA43_specialS =
        "\42\uffff}>";
    static final String[] DFA43_transitionS = {
            "\1\1\14\uffff\1\1\1\35\1\uffff\1\1\1\uffff\1\1\1\35\1\1\1\35"+
            "\1\1\1\35\3\1\1\uffff\23\1\6\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "842:22: ( terms )?";
        }
    }
    static final String DFA45_eotS =
        "\37\uffff";
    static final String DFA45_eofS =
        "\1\1\36\uffff";
    static final String DFA45_minS =
        "\1\30\36\uffff";
    static final String DFA45_maxS =
        "\1\115\36\uffff";
    static final String DFA45_acceptS =
        "\1\uffff\1\2\3\uffff\1\1\31\uffff";
    static final String DFA45_specialS =
        "\37\uffff}>";
    static final String[] DFA45_transitionS = {
            "\1\5\15\uffff\1\1\3\uffff\1\5\1\1\1\5\1\1\3\5\1\uffff\1\5\1"+
            "\uffff\23\5\6\uffff\1\5",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 843:11: ( any )*";
        }
    }
    static final String DFA51_eotS =
        "\43\uffff";
    static final String DFA51_eofS =
        "\1\1\42\uffff";
    static final String DFA51_minS =
        "\1\30\42\uffff";
    static final String DFA51_maxS =
        "\1\115\42\uffff";
    static final String DFA51_acceptS =
        "\1\uffff\1\2\5\uffff\1\1\33\uffff";
    static final String DFA51_specialS =
        "\43\uffff}>";
    static final String[] DFA51_transitionS = {
            "\1\7\14\uffff\1\7\1\1\1\uffff\1\7\1\uffff\1\7\1\1\1\7\1\1\1"+
            "\7\1\1\3\7\1\1\23\7\6\uffff\1\7",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()+ loopback of 864:4: ( term )+";
        }
    }
    static final String DFA56_eotS =
        "\35\uffff";
    static final String DFA56_eofS =
        "\35\uffff";
    static final String DFA56_minS =
        "\1\30\34\uffff";
    static final String DFA56_maxS =
        "\1\115\34\uffff";
    static final String DFA56_acceptS =
        "\1\uffff\1\1\31\uffff\1\2\1\3";
    static final String DFA56_specialS =
        "\35\uffff}>";
    static final String[] DFA56_transitionS = {
            "\1\1\14\uffff\1\33\2\uffff\1\34\1\uffff\1\1\1\uffff\1\1\1\uffff"+
            "\1\1\1\uffff\3\1\1\uffff\23\1\6\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "881:1: term : ( valuepart -> valuepart | LCURLY ( S )* ( any | SEMICOLON ( S )* )* RCURLY -> CURLYBLOCK | ATKEYWORD ( S )* -> ATKEYWORD );";
        }
    }
    static final String DFA52_eotS =
        "\36\uffff";
    static final String DFA52_eofS =
        "\36\uffff";
    static final String DFA52_minS =
        "\1\30\35\uffff";
    static final String DFA52_maxS =
        "\1\115\35\uffff";
    static final String DFA52_acceptS =
        "\1\uffff\1\2\33\uffff\1\1";
    static final String DFA52_specialS =
        "\36\uffff}>";
    static final String[] DFA52_transitionS = {
            "\1\1\6\uffff\1\35\6\uffff\1\1\3\uffff\1\1\1\uffff\5\1\1\uffff"+
            "\1\1\1\uffff\23\1\6\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 883:14: ( S )*";
        }
    }
    static final String DFA54_eotS =
        "\35\uffff";
    static final String DFA54_eofS =
        "\35\uffff";
    static final String DFA54_minS =
        "\1\30\34\uffff";
    static final String DFA54_maxS =
        "\1\115\34\uffff";
    static final String DFA54_acceptS =
        "\1\uffff\1\3\1\1\31\uffff\1\2";
    static final String DFA54_specialS =
        "\35\uffff}>";
    static final String[] DFA54_transitionS = {
            "\1\2\15\uffff\1\1\3\uffff\1\2\1\uffff\1\2\1\34\3\2\1\uffff\1"+
            "\2\1\uffff\23\2\6\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 883:17: ( any | SEMICOLON ( S )* )*";
        }
    }
    static final String DFA53_eotS =
        "\36\uffff";
    static final String DFA53_eofS =
        "\36\uffff";
    static final String DFA53_minS =
        "\1\30\35\uffff";
    static final String DFA53_maxS =
        "\1\115\35\uffff";
    static final String DFA53_acceptS =
        "\1\uffff\1\2\33\uffff\1\1";
    static final String DFA53_specialS =
        "\36\uffff}>";
    static final String[] DFA53_transitionS = {
            "\1\1\6\uffff\1\35\6\uffff\1\1\3\uffff\1\1\1\uffff\5\1\1\uffff"+
            "\1\1\1\uffff\23\1\6\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 883:34: ( S )*";
        }
    }
    static final String DFA55_eotS =
        "\44\uffff";
    static final String DFA55_eofS =
        "\1\1\43\uffff";
    static final String DFA55_minS =
        "\1\30\43\uffff";
    static final String DFA55_maxS =
        "\1\115\43\uffff";
    static final String DFA55_acceptS =
        "\1\uffff\1\2\41\uffff\1\1";
    static final String DFA55_specialS =
        "\44\uffff}>";
    static final String[] DFA55_transitionS = {
            "\1\1\6\uffff\1\43\5\uffff\2\1\1\uffff\1\1\1\uffff\35\1\6\uffff"+
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
            return "()* loopback of 884:17: ( S )*";
        }
    }
    static final String DFA57_eotS =
        "\37\uffff";
    static final String DFA57_eofS =
        "\37\uffff";
    static final String DFA57_minS =
        "\1\30\36\uffff";
    static final String DFA57_maxS =
        "\1\115\36\uffff";
    static final String DFA57_acceptS =
        "\1\uffff\1\2\34\uffff\1\1";
    static final String DFA57_specialS =
        "\37\uffff}>";
    static final String[] DFA57_transitionS = {
            "\1\1\6\uffff\1\36\5\uffff\1\1\2\uffff\1\1\1\uffff\1\1\1\uffff"+
            "\1\1\1\uffff\1\1\1\uffff\27\1\6\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 896:13: ( S )*";
        }
    }
    static final String DFA58_eotS =
        "\36\uffff";
    static final String DFA58_eofS =
        "\36\uffff";
    static final String DFA58_minS =
        "\1\30\35\uffff";
    static final String DFA58_maxS =
        "\1\115\35\uffff";
    static final String DFA58_acceptS =
        "\1\uffff\1\1\33\uffff\1\2";
    static final String DFA58_specialS =
        "\36\uffff}>";
    static final String[] DFA58_transitionS = {
            "\1\1\14\uffff\1\1\2\uffff\1\1\1\uffff\1\1\1\uffff\1\1\1\uffff"+
            "\1\1\1\uffff\3\1\1\35\23\1\6\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "896:16: ( terms )?";
        }
    }
    static final String DFA67_eotS =
        "\41\uffff";
    static final String DFA67_eofS =
        "\41\uffff";
    static final String DFA67_minS =
        "\1\30\1\52\37\uffff";
    static final String DFA67_maxS =
        "\1\115\1\67\37\uffff";
    static final String DFA67_acceptS =
        "\2\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\uffff\1\26\1\27\1"+
        "\30\6\uffff";
    static final String DFA67_specialS =
        "\41\uffff}>";
    static final String[] DFA67_transitionS = {
            "\1\7\21\uffff\1\2\1\uffff\1\15\1\uffff\1\14\1\uffff\1\1\2\26"+
            "\1\uffff\1\3\1\4\1\5\1\6\1\10\1\11\1\12\1\13\1\16\1\17\1\20"+
            "\1\21\1\22\1\23\1\24\1\25\1\30\1\31\1\32\6\uffff\1\7",
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
            return "901:7: ( ( MINUS )? IDENT -> ( MINUS )? IDENT | CLASSKEYWORD -> CLASSKEYWORD | ( MINUS )? NUMBER -> ( MINUS )? NUMBER | ( MINUS )? PERCENTAGE -> ( MINUS )? PERCENTAGE | ( MINUS )? DIMENSION -> ( MINUS )? DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | PLUS -> PLUS | ASTERISK -> ASTERISK | ( MINUS )? funct -> ( MINUS )? funct | DASHMATCH -> DASHMATCH | LPAREN ( valuepart )* RPAREN -> ^( PARENBLOCK ( valuepart )* ) | LBRACE ( valuepart )* RBRACE -> ^( BRACEBLOCK ( valuepart )* ) )";
        }
    }
    static final String DFA65_eotS =
        "\34\uffff";
    static final String DFA65_eofS =
        "\34\uffff";
    static final String DFA65_minS =
        "\1\30\33\uffff";
    static final String DFA65_maxS =
        "\1\115\33\uffff";
    static final String DFA65_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA65_specialS =
        "\34\uffff}>";
    static final String[] DFA65_transitionS = {
            "\1\2\21\uffff\1\2\1\uffff\1\2\1\uffff\1\2\1\uffff\3\2\1\1\23"+
            "\2\6\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 923:16: ( valuepart )*";
        }
    }
    static final String DFA66_eotS =
        "\34\uffff";
    static final String DFA66_eofS =
        "\34\uffff";
    static final String DFA66_minS =
        "\1\30\33\uffff";
    static final String DFA66_maxS =
        "\1\115\33\uffff";
    static final String DFA66_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA66_specialS =
        "\34\uffff}>";
    static final String[] DFA66_transitionS = {
            "\1\2\21\uffff\1\2\1\uffff\1\2\1\uffff\1\2\1\uffff\3\2\1\uffff"+
            "\23\2\1\1\5\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 924:16: ( valuepart )*";
        }
    }
    static final String DFA68_eotS =
        "\45\uffff";
    static final String DFA68_eofS =
        "\1\1\44\uffff";
    static final String DFA68_minS =
        "\1\30\44\uffff";
    static final String DFA68_maxS =
        "\1\115\44\uffff";
    static final String DFA68_acceptS =
        "\1\uffff\1\2\42\uffff\1\1";
    static final String DFA68_specialS =
        "\45\uffff}>";
    static final String[] DFA68_transitionS = {
            "\1\1\6\uffff\1\44\5\uffff\2\1\1\uffff\1\1\1\uffff\36\1\5\uffff"+
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
            return "()* loopback of 925:8: ( S )*";
        }
    }
    static final String DFA75_eotS =
        "\14\uffff";
    static final String DFA75_eofS =
        "\14\uffff";
    static final String DFA75_minS =
        "\1\32\13\uffff";
    static final String DFA75_maxS =
        "\1\110\13\uffff";
    static final String DFA75_acceptS =
        "\1\uffff\1\2\5\uffff\1\1\4\uffff";
    static final String DFA75_specialS =
        "\14\uffff}>";
    static final String[] DFA75_transitionS = {
            "\1\7\4\uffff\1\1\5\uffff\1\1\6\uffff\1\1\1\uffff\1\7\5\uffff"+
            "\1\7\4\uffff\1\7\2\uffff\1\1\5\uffff\1\1\3\uffff\1\7\1\uffff"+
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
            return "()* loopback of 945:27: ( selpart )*";
        }
    }
    static final String DFA76_eotS =
        "\24\uffff";
    static final String DFA76_eofS =
        "\24\uffff";
    static final String DFA76_minS =
        "\1\37\3\uffff\1\32\17\uffff";
    static final String DFA76_maxS =
        "\1\110\3\uffff\1\110\17\uffff";
    static final String DFA76_acceptS =
        "\1\uffff\1\2\5\uffff\1\1\14\uffff";
    static final String DFA76_specialS =
        "\24\uffff}>";
    static final String[] DFA76_transitionS = {
            "\1\4\5\uffff\1\1\6\uffff\1\1\17\uffff\1\1\5\uffff\1\1\5\uffff"+
            "\1\1",
            "",
            "",
            "",
            "\1\1\4\uffff\1\7\5\uffff\1\7\4\uffff\1\1\1\uffff\1\7\1\uffff"+
            "\1\1\5\uffff\1\1\4\uffff\1\1\2\uffff\1\7\5\uffff\1\7\1\1\2\uffff"+
            "\1\1\1\uffff\1\7",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 945:36: ( S )*";
        }
    }
    static final String DFA77_eotS =
        "\14\uffff";
    static final String DFA77_eofS =
        "\14\uffff";
    static final String DFA77_minS =
        "\1\32\13\uffff";
    static final String DFA77_maxS =
        "\1\110\13\uffff";
    static final String DFA77_acceptS =
        "\1\uffff\1\2\5\uffff\1\1\4\uffff";
    static final String DFA77_specialS =
        "\14\uffff}>";
    static final String[] DFA77_transitionS = {
            "\1\7\4\uffff\1\1\5\uffff\1\1\6\uffff\1\1\1\uffff\1\7\5\uffff"+
            "\1\7\4\uffff\1\7\2\uffff\1\1\5\uffff\1\1\3\uffff\1\7\1\uffff"+
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
            return "()+ loopback of 947:7: ( selpart )+";
        }
    }
    static final String DFA78_eotS =
        "\24\uffff";
    static final String DFA78_eofS =
        "\24\uffff";
    static final String DFA78_minS =
        "\1\37\3\uffff\1\32\17\uffff";
    static final String DFA78_maxS =
        "\1\110\3\uffff\1\110\17\uffff";
    static final String DFA78_acceptS =
        "\1\uffff\1\2\5\uffff\1\1\14\uffff";
    static final String DFA78_specialS =
        "\24\uffff}>";
    static final String[] DFA78_transitionS = {
            "\1\4\5\uffff\1\1\6\uffff\1\1\17\uffff\1\1\5\uffff\1\1\5\uffff"+
            "\1\1",
            "",
            "",
            "",
            "\1\1\4\uffff\1\7\5\uffff\1\7\4\uffff\1\1\1\uffff\1\7\1\uffff"+
            "\1\1\5\uffff\1\1\4\uffff\1\1\2\uffff\1\7\5\uffff\1\7\1\1\2\uffff"+
            "\1\1\1\uffff\1\7",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 947:16: ( S )*";
        }
    }
    static final String DFA93_eotS =
        "\33\uffff";
    static final String DFA93_eofS =
        "\33\uffff";
    static final String DFA93_minS =
        "\1\30\32\uffff";
    static final String DFA93_maxS =
        "\1\115\32\uffff";
    static final String DFA93_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
        "\1\32";
    static final String DFA93_specialS =
        "\33\uffff}>";
    static final String[] DFA93_transitionS = {
            "\1\6\21\uffff\1\1\1\uffff\1\14\1\uffff\1\13\1\23\1\24\1\uffff"+
            "\1\27\1\uffff\1\2\1\3\1\4\1\5\1\7\1\10\1\11\1\12\1\15\1\16\1"+
            "\17\1\20\1\21\1\22\1\25\1\26\1\30\1\31\1\32\6\uffff\1\6",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "988:4: ( IDENT -> IDENT | CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | HASH -> HASH | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COLON -> COLON | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | ASTERISK -> ASTERISK | FUNCTION ( S )* ( any )* RPAREN -> ^( FUNCTION ( any )* ) | DASHMATCH -> DASHMATCH | LPAREN ( any )* RPAREN -> ^( PARENBLOCK ( any )* ) | LBRACE ( any )* RBRACE -> ^( BRACEBLOCK ( any )* ) )";
        }
    }
    static final String DFA89_eotS =
        "\35\uffff";
    static final String DFA89_eofS =
        "\35\uffff";
    static final String DFA89_minS =
        "\1\30\34\uffff";
    static final String DFA89_maxS =
        "\1\115\34\uffff";
    static final String DFA89_acceptS =
        "\1\uffff\1\2\32\uffff\1\1";
    static final String DFA89_specialS =
        "\35\uffff}>";
    static final String[] DFA89_transitionS = {
            "\1\1\6\uffff\1\34\12\uffff\1\1\1\uffff\1\1\1\uffff\3\1\1\uffff"+
            "\25\1\6\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 1010:18: ( S )*";
        }
    }
    static final String DFA90_eotS =
        "\34\uffff";
    static final String DFA90_eofS =
        "\34\uffff";
    static final String DFA90_minS =
        "\1\30\33\uffff";
    static final String DFA90_maxS =
        "\1\115\33\uffff";
    static final String DFA90_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA90_specialS =
        "\34\uffff}>";
    static final String[] DFA90_transitionS = {
            "\1\2\21\uffff\1\2\1\uffff\1\2\1\uffff\3\2\1\uffff\1\2\1\1\23"+
            "\2\6\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 1010:21: ( any )*";
        }
    }
    static final String DFA91_eotS =
        "\34\uffff";
    static final String DFA91_eofS =
        "\34\uffff";
    static final String DFA91_minS =
        "\1\30\33\uffff";
    static final String DFA91_maxS =
        "\1\115\33\uffff";
    static final String DFA91_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA91_specialS =
        "\34\uffff}>";
    static final String[] DFA91_transitionS = {
            "\1\2\21\uffff\1\2\1\uffff\1\2\1\uffff\3\2\1\uffff\1\2\1\1\23"+
            "\2\6\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 1012:16: ( any )*";
        }
    }
    static final String DFA92_eotS =
        "\34\uffff";
    static final String DFA92_eofS =
        "\34\uffff";
    static final String DFA92_minS =
        "\1\30\33\uffff";
    static final String DFA92_maxS =
        "\1\115\33\uffff";
    static final String DFA92_acceptS =
        "\1\uffff\1\2\1\1\31\uffff";
    static final String DFA92_specialS =
        "\34\uffff}>";
    static final String[] DFA92_transitionS = {
            "\1\2\21\uffff\1\2\1\uffff\1\2\1\uffff\3\2\1\uffff\1\2\1\uffff"+
            "\23\2\1\1\5\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 1013:16: ( any )*";
        }
    }
    static final String DFA94_eotS =
        "\42\uffff";
    static final String DFA94_eofS =
        "\1\1\41\uffff";
    static final String DFA94_minS =
        "\1\30\41\uffff";
    static final String DFA94_maxS =
        "\1\115\41\uffff";
    static final String DFA94_acceptS =
        "\1\uffff\1\2\37\uffff\1\1";
    static final String DFA94_specialS =
        "\42\uffff}>";
    static final String[] DFA94_transitionS = {
            "\1\1\6\uffff\1\41\6\uffff\1\1\3\uffff\7\1\1\uffff\26\1\5\uffff"+
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

    static final short[] DFA94_eot = DFA.unpackEncodedString(DFA94_eotS);
    static final short[] DFA94_eof = DFA.unpackEncodedString(DFA94_eofS);
    static final char[] DFA94_min = DFA.unpackEncodedStringToUnsignedChars(DFA94_minS);
    static final char[] DFA94_max = DFA.unpackEncodedStringToUnsignedChars(DFA94_maxS);
    static final short[] DFA94_accept = DFA.unpackEncodedString(DFA94_acceptS);
    static final short[] DFA94_special = DFA.unpackEncodedString(DFA94_specialS);
    static final short[][] DFA94_transition;

    static {
        int numStates = DFA94_transitionS.length;
        DFA94_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA94_transition[i] = DFA.unpackEncodedString(DFA94_transitionS[i]);
        }
    }

    class DFA94 extends DFA {

        public DFA94(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 94;
            this.eot = DFA94_eot;
            this.eof = DFA94_eof;
            this.min = DFA94_min;
            this.max = DFA94_max;
            this.accept = DFA94_accept;
            this.special = DFA94_special;
            this.transition = DFA94_transition;
        }
        public String getDescription() {
            return "()* loopback of 1014:8: ( S )*";
        }
    }
    static final String DFA96_eotS =
        "\22\uffff";
    static final String DFA96_eofS =
        "\22\uffff";
    static final String DFA96_minS =
        "\1\54\21\uffff";
    static final String DFA96_maxS =
        "\1\117\21\uffff";
    static final String DFA96_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21";
    static final String DFA96_specialS =
        "\22\uffff}>";
    static final String[] DFA96_transitionS = {
            "\1\3\1\uffff\1\17\1\12\4\uffff\1\1\1\2\5\uffff\1\16\1\4\1\5"+
            "\1\6\1\7\1\10\1\11\1\13\1\14\1\15\11\uffff\1\20\1\21",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "1024:4: ( CLASSKEYWORD -> CLASSKEYWORD | NUMBER -> NUMBER | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | PLUS -> PLUS | ASTERISK -> ASTERISK | DASHMATCH -> DASHMATCH | INCLUDES -> INCLUDES | COLON -> COLON | STRING_CHAR -> STRING_CHAR | INVALID_TOKEN -> INVALID_TOKEN )";
        }
    }
    static final String DFA97_eotS =
        "\40\uffff";
    static final String DFA97_eofS =
        "\1\1\37\uffff";
    static final String DFA97_minS =
        "\1\30\37\uffff";
    static final String DFA97_maxS =
        "\1\115\37\uffff";
    static final String DFA97_acceptS =
        "\1\uffff\1\2\35\uffff\1\1";
    static final String DFA97_specialS =
        "\40\uffff}>";
    static final String[] DFA97_transitionS = {
            "\1\1\6\uffff\1\37\6\uffff\1\1\3\uffff\7\1\1\uffff\1\1\1\uffff"+
            "\23\1\6\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "()* loopback of 1041:8: ( S )*";
        }
    }
    static final String DFA98_eotS =
        "\26\uffff";
    static final String DFA98_eofS =
        "\26\uffff";
    static final String DFA98_minS =
        "\1\30\25\uffff";
    static final String DFA98_maxS =
        "\1\144\25\uffff";
    static final String DFA98_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25";
    static final String DFA98_specialS =
        "\26\uffff}>";
    static final String[] DFA98_transitionS = {
            "\1\4\23\uffff\1\10\2\uffff\1\17\1\20\2\uffff\1\23\1\uffff\1"+
            "\1\1\2\1\3\1\5\1\uffff\1\6\1\7\1\11\1\12\1\13\1\14\1\15\1\16"+
            "\1\21\1\uffff\1\22\10\uffff\1\4\25\uffff\1\24\1\25",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA98_eot = DFA.unpackEncodedString(DFA98_eotS);
    static final short[] DFA98_eof = DFA.unpackEncodedString(DFA98_eofS);
    static final char[] DFA98_min = DFA.unpackEncodedStringToUnsignedChars(DFA98_minS);
    static final char[] DFA98_max = DFA.unpackEncodedStringToUnsignedChars(DFA98_maxS);
    static final short[] DFA98_accept = DFA.unpackEncodedString(DFA98_acceptS);
    static final short[] DFA98_special = DFA.unpackEncodedString(DFA98_specialS);
    static final short[][] DFA98_transition;

    static {
        int numStates = DFA98_transitionS.length;
        DFA98_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA98_transition[i] = DFA.unpackEncodedString(DFA98_transitionS[i]);
        }
    }

    class DFA98 extends DFA {

        public DFA98(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 98;
            this.eot = DFA98_eot;
            this.eof = DFA98_eof;
            this.min = DFA98_min;
            this.max = DFA98_max;
            this.accept = DFA98_accept;
            this.special = DFA98_special;
            this.transition = DFA98_transition;
        }
        public String getDescription() {
            return "1045:5: ( NUMBER -> NUMBER | PERCENTAGE -> PERCENTAGE | DIMENSION -> DIMENSION | string -> string | URI -> URI | UNIRANGE -> UNIRANGE | INCLUDES -> INCLUDES | COMMA -> COMMA | GREATER -> GREATER | LESS -> LESS | QUESTION -> QUESTION | PERCENT -> PERCENT | EQUALS -> EQUALS | SLASH -> SLASH | EXCLAMATION -> EXCLAMATION | MINUS -> MINUS | PLUS -> PLUS | DASHMATCH -> DASHMATCH | RPAREN -> RPAREN | '#' | '^' )";
        }
    }
 

    public static final BitSet FOLLOW_S_in_inlinestyle205 = new BitSet(new long[]{0xF831F42080000000L,0x000000000000C01FL});
    public static final BitSet FOLLOW_declarations_in_inlinestyle210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_inlineset_in_inlinestyle230 = new BitSet(new long[]{0xF831F42000000002L,0x000000000000C01FL});
    public static final BitSet FOLLOW_CDO_in_stylesheet258 = new BitSet(new long[]{0xFFF9F7DFA5800002L,0x000000180000205FL});
    public static final BitSet FOLLOW_CDC_in_stylesheet262 = new BitSet(new long[]{0xFFF9F7DFA5800002L,0x000000180000205FL});
    public static final BitSet FOLLOW_S_in_stylesheet266 = new BitSet(new long[]{0xFFF9F7DFA5800002L,0x000000180000205FL});
    public static final BitSet FOLLOW_nostatement_in_stylesheet270 = new BitSet(new long[]{0xFFF9F7DFA5800002L,0x000000180000205FL});
    public static final BitSet FOLLOW_statement_in_stylesheet274 = new BitSet(new long[]{0xFFF9F7DFA5800002L,0x000000180000205FL});
    public static final BitSet FOLLOW_ruleset_in_statement304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atstatement_in_statement308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARSET_in_atstatement319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_atstatement324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_IMPORT_in_atstatement329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_END_in_atstatement334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_page_in_atstatement339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FONTFACE_in_atstatement344 = new BitSet(new long[]{0x0000002080000000L});
    public static final BitSet FOLLOW_S_in_atstatement346 = new BitSet(new long[]{0x0000002080000000L});
    public static final BitSet FOLLOW_LCURLY_in_atstatement352 = new BitSet(new long[]{0xF831F44080000000L,0x000000000000C01FL});
    public static final BitSet FOLLOW_S_in_atstatement354 = new BitSet(new long[]{0xF831F44080000000L,0x000000000000C01FL});
    public static final BitSet FOLLOW_declarations_in_atstatement357 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_RCURLY_in_atstatement362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MEDIA_in_atstatement375 = new BitSet(new long[]{0x0000042080000000L});
    public static final BitSet FOLLOW_S_in_atstatement377 = new BitSet(new long[]{0x0000042080000000L});
    public static final BitSet FOLLOW_media_in_atstatement380 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LCURLY_in_atstatement386 = new BitSet(new long[]{0xFFF9D44085000000L,0x000000180000205FL});
    public static final BitSet FOLLOW_S_in_atstatement388 = new BitSet(new long[]{0xFFF9D44085000000L,0x000000180000205FL});
    public static final BitSet FOLLOW_ruleset_in_atstatement392 = new BitSet(new long[]{0xFFF9D44085000000L,0x000000180000205FL});
    public static final BitSet FOLLOW_S_in_atstatement394 = new BitSet(new long[]{0xFFF9D44085000000L,0x000000180000205FL});
    public static final BitSet FOLLOW_RCURLY_in_atstatement399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATKEYWORD_in_atstatement417 = new BitSet(new long[]{0x0000002080000000L});
    public static final BitSet FOLLOW_S_in_atstatement419 = new BitSet(new long[]{0x0000002080000000L});
    public static final BitSet FOLLOW_LCURLY_in_atstatement422 = new BitSet(new long[]{0xFFF5D44001000000L,0x000000000000207FL});
    public static final BitSet FOLLOW_any_in_atstatement424 = new BitSet(new long[]{0xFFF5D44001000000L,0x000000000000207FL});
    public static final BitSet FOLLOW_RCURLY_in_atstatement427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PAGE_in_page449 = new BitSet(new long[]{0x0000442080000000L});
    public static final BitSet FOLLOW_S_in_page451 = new BitSet(new long[]{0x0000442080000000L});
    public static final BitSet FOLLOW_IDENT_in_page457 = new BitSet(new long[]{0x0000002080000000L});
    public static final BitSet FOLLOW_IDENT_in_page461 = new BitSet(new long[]{0x0000440000000000L});
    public static final BitSet FOLLOW_page_pseudo_in_page463 = new BitSet(new long[]{0x0000002080000000L});
    public static final BitSet FOLLOW_page_pseudo_in_page467 = new BitSet(new long[]{0x0000002080000000L});
    public static final BitSet FOLLOW_S_in_page470 = new BitSet(new long[]{0x0000002080000000L});
    public static final BitSet FOLLOW_LCURLY_in_page478 = new BitSet(new long[]{0xF831FC4080000000L,0x000000000000C01FL});
    public static final BitSet FOLLOW_S_in_page480 = new BitSet(new long[]{0xF831FC4080000000L,0x000000000000C01FL});
    public static final BitSet FOLLOW_declarations_in_page485 = new BitSet(new long[]{0x0000084000000000L});
    public static final BitSet FOLLOW_margin_rule_in_page487 = new BitSet(new long[]{0x0000084000000000L});
    public static final BitSet FOLLOW_RCURLY_in_page492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pseudocolon_in_page_pseudo526 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_IDENT_in_page_pseudo529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARGIN_AREA_in_margin_rule540 = new BitSet(new long[]{0x0000002080000000L});
    public static final BitSet FOLLOW_S_in_margin_rule542 = new BitSet(new long[]{0x0000002080000000L});
    public static final BitSet FOLLOW_LCURLY_in_margin_rule545 = new BitSet(new long[]{0xF831F44080000000L,0x000000000000C01FL});
    public static final BitSet FOLLOW_S_in_margin_rule547 = new BitSet(new long[]{0xF831F44080000000L,0x000000000000C01FL});
    public static final BitSet FOLLOW_declarations_in_margin_rule550 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_RCURLY_in_margin_rule552 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_S_in_margin_rule554 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_pseudo_in_inlineset577 = new BitSet(new long[]{0x0000102080000000L});
    public static final BitSet FOLLOW_S_in_inlineset579 = new BitSet(new long[]{0x0000102080000000L});
    public static final BitSet FOLLOW_COMMA_in_inlineset583 = new BitSet(new long[]{0x0000400080000000L});
    public static final BitSet FOLLOW_S_in_inlineset585 = new BitSet(new long[]{0x0000400080000000L});
    public static final BitSet FOLLOW_pseudo_in_inlineset588 = new BitSet(new long[]{0x0000102080000000L});
    public static final BitSet FOLLOW_S_in_inlineset590 = new BitSet(new long[]{0x0000102080000000L});
    public static final BitSet FOLLOW_LCURLY_in_inlineset603 = new BitSet(new long[]{0xF831F44000000000L,0x000000000000C01FL});
    public static final BitSet FOLLOW_declarations_in_inlineset609 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_RCURLY_in_inlineset614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_media641 = new BitSet(new long[]{0x0000100080000002L});
    public static final BitSet FOLLOW_S_in_media643 = new BitSet(new long[]{0x0000100080000002L});
    public static final BitSet FOLLOW_COMMA_in_media647 = new BitSet(new long[]{0x0000040080000000L});
    public static final BitSet FOLLOW_S_in_media649 = new BitSet(new long[]{0x0000040080000000L});
    public static final BitSet FOLLOW_IDENT_in_media652 = new BitSet(new long[]{0x0000100080000002L});
    public static final BitSet FOLLOW_S_in_media654 = new BitSet(new long[]{0x0000100080000002L});
    public static final BitSet FOLLOW_combined_selector_in_ruleset679 = new BitSet(new long[]{0x0000102000000000L});
    public static final BitSet FOLLOW_COMMA_in_ruleset682 = new BitSet(new long[]{0x0210440084000000L,0x0000000000000048L});
    public static final BitSet FOLLOW_S_in_ruleset684 = new BitSet(new long[]{0x0210440084000000L,0x0000000000000048L});
    public static final BitSet FOLLOW_combined_selector_in_ruleset687 = new BitSet(new long[]{0x0000102000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleset695 = new BitSet(new long[]{0xF831F44080000000L,0x000000000000C01FL});
    public static final BitSet FOLLOW_S_in_ruleset697 = new BitSet(new long[]{0xF831F44080000000L,0x000000000000C01FL});
    public static final BitSet FOLLOW_declarations_in_ruleset705 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleset710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_norule_in_ruleset729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_declarations751 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_declarations755 = new BitSet(new long[]{0xF831F40080000002L,0x000000000000C01FL});
    public static final BitSet FOLLOW_S_in_declarations757 = new BitSet(new long[]{0xF831F40080000002L,0x000000000000C01FL});
    public static final BitSet FOLLOW_declaration_in_declarations760 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_property_in_declaration792 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_COLON_in_declaration794 = new BitSet(new long[]{0xFFF7D52081000002L,0x000000000000207FL});
    public static final BitSet FOLLOW_S_in_declaration796 = new BitSet(new long[]{0xFFF7D52081000002L,0x000000000000207FL});
    public static final BitSet FOLLOW_terms_in_declaration799 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_important_in_declaration802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_noprop_in_declaration822 = new BitSet(new long[]{0xFFF5D40001000002L,0x000000000000207FL});
    public static final BitSet FOLLOW_any_in_declaration824 = new BitSet(new long[]{0xFFF5D40001000002L,0x000000000000207FL});
    public static final BitSet FOLLOW_EXCLAMATION_in_important850 = new BitSet(new long[]{0x0000000080000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_S_in_important852 = new BitSet(new long[]{0x0000000080000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_98_in_important855 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_S_in_important857 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_MINUS_in_property886 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_IDENT_in_property889 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_S_in_property891 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_term_in_terms919 = new BitSet(new long[]{0xFFF7552001000002L,0x000000000000207FL});
    public static final BitSet FOLLOW_valuepart_in_term952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_term964 = new BitSet(new long[]{0xFFF5F44081000000L,0x000000000000207FL});
    public static final BitSet FOLLOW_S_in_term966 = new BitSet(new long[]{0xFFF5F44081000000L,0x000000000000207FL});
    public static final BitSet FOLLOW_any_in_term970 = new BitSet(new long[]{0xFFF5F44001000000L,0x000000000000207FL});
    public static final BitSet FOLLOW_SEMICOLON_in_term974 = new BitSet(new long[]{0xFFF5F44081000000L,0x000000000000207FL});
    public static final BitSet FOLLOW_S_in_term976 = new BitSet(new long[]{0xFFF5F44081000000L,0x000000000000207FL});
    public static final BitSet FOLLOW_RCURLY_in_term981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATKEYWORD_in_term993 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_S_in_term995 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_EXPRESSION_in_funct1028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_funct1037 = new BitSet(new long[]{0xFFFF552081000000L,0x000000000000207FL});
    public static final BitSet FOLLOW_S_in_funct1039 = new BitSet(new long[]{0xFFFF552081000000L,0x000000000000207FL});
    public static final BitSet FOLLOW_terms_in_funct1042 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_funct1045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1072 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_IDENT_in_valuepart1075 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_valuepart1092 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1106 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_NUMBER_in_valuepart1109 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1126 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_PERCENTAGE_in_valuepart1129 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1146 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_DIMENSION_in_valuepart1149 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_string_in_valuepart1166 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_URI_in_valuepart1180 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_HASH_in_valuepart1197 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_valuepart1211 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_INCLUDES_in_valuepart1225 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_COLON_in_valuepart1239 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_COMMA_in_valuepart1253 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_GREATER_in_valuepart1267 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_LESS_in_valuepart1281 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_QUESTION_in_valuepart1295 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_PERCENT_in_valuepart1309 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_EQUALS_in_valuepart1323 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_SLASH_in_valuepart1337 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_PLUS_in_valuepart1350 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_ASTERISK_in_valuepart1363 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_MINUS_in_valuepart1380 = new BitSet(new long[]{0x0007000000000000L});
    public static final BitSet FOLLOW_funct_in_valuepart1383 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_valuepart1401 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_LPAREN_in_valuepart1415 = new BitSet(new long[]{0xFFFF540001000000L,0x000000000000207FL});
    public static final BitSet FOLLOW_valuepart_in_valuepart1417 = new BitSet(new long[]{0xFFFF540001000000L,0x000000000000207FL});
    public static final BitSet FOLLOW_RPAREN_in_valuepart1420 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_LBRACE_in_valuepart1439 = new BitSet(new long[]{0xFFF7540001000000L,0x00000000000020FFL});
    public static final BitSet FOLLOW_valuepart_in_valuepart1441 = new BitSet(new long[]{0xFFF7540001000000L,0x00000000000020FFL});
    public static final BitSet FOLLOW_RBRACE_in_valuepart1444 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_S_in_valuepart1462 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_selector_in_combined_selector1479 = new BitSet(new long[]{0x1000000080000002L,0x0000000000000104L});
    public static final BitSet FOLLOW_combinator_in_combined_selector1483 = new BitSet(new long[]{0x0210440004000000L,0x0000000000000048L});
    public static final BitSet FOLLOW_selector_in_combined_selector1486 = new BitSet(new long[]{0x1000000080000002L,0x0000000000000104L});
    public static final BitSet FOLLOW_GREATER_in_combinator1506 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_S_in_combinator1508 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_PLUS_in_combinator1518 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_S_in_combinator1520 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_TILDE_in_combinator1530 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_S_in_combinator1532 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_S_in_combinator1542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_selector1561 = new BitSet(new long[]{0x0210440084000002L,0x0000000000000048L});
    public static final BitSet FOLLOW_ASTERISK_in_selector1565 = new BitSet(new long[]{0x0210440084000002L,0x0000000000000048L});
    public static final BitSet FOLLOW_selpart_in_selector1569 = new BitSet(new long[]{0x0210440084000002L,0x0000000000000048L});
    public static final BitSet FOLLOW_S_in_selector1572 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_selpart_in_selector1602 = new BitSet(new long[]{0x0210440084000002L,0x0000000000000048L});
    public static final BitSet FOLLOW_S_in_selector1605 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_HASH_in_selpart1652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_selpart1660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_selpart1667 = new BitSet(new long[]{0x0000040080000000L});
    public static final BitSet FOLLOW_S_in_selpart1669 = new BitSet(new long[]{0x0000040080000000L});
    public static final BitSet FOLLOW_attribute_in_selpart1672 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_RBRACE_in_selpart1674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pseudo_in_selpart1690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVALID_SELPART_in_selpart1698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_attribute1722 = new BitSet(new long[]{0x0800000080000002L,0x0000000000000E11L});
    public static final BitSet FOLLOW_S_in_attribute1724 = new BitSet(new long[]{0x0800000080000002L,0x0000000000000E11L});
    public static final BitSet FOLLOW_set_in_attribute1731 = new BitSet(new long[]{0x0000040081000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_S_in_attribute1755 = new BitSet(new long[]{0x0000040081000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_IDENT_in_attribute1759 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_string_in_attribute1763 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_S_in_attribute1766 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_pseudocolon_in_pseudo1780 = new BitSet(new long[]{0x0004040000000000L});
    public static final BitSet FOLLOW_IDENT_in_pseudo1784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_pseudo1788 = new BitSet(new long[]{0x0020040000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_set_in_pseudo1790 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_pseudo1802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_pseudocolon1823 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_COLON_in_pseudocolon1825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_string0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_any1862 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_any1873 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_NUMBER_in_any1884 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_PERCENTAGE_in_any1895 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_DIMENSION_in_any1905 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_string_in_any1916 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_URI_in_any1930 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_HASH_in_any1947 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_any1961 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_INCLUDES_in_any1975 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_COLON_in_any1989 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_COMMA_in_any2003 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_GREATER_in_any2017 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_LESS_in_any2031 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_QUESTION_in_any2045 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_PERCENT_in_any2059 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_EQUALS_in_any2073 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_SLASH_in_any2087 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_any2101 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_MINUS_in_any2112 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_PLUS_in_any2123 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_ASTERISK_in_any2134 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_FUNCTION_in_any2151 = new BitSet(new long[]{0xFFFDD40081000000L,0x000000000000207FL});
    public static final BitSet FOLLOW_S_in_any2153 = new BitSet(new long[]{0xFFFDD40081000000L,0x000000000000207FL});
    public static final BitSet FOLLOW_any_in_any2156 = new BitSet(new long[]{0xFFFDD40001000000L,0x000000000000207FL});
    public static final BitSet FOLLOW_RPAREN_in_any2159 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_any2179 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_LPAREN_in_any2193 = new BitSet(new long[]{0xFFFDD40001000000L,0x000000000000207FL});
    public static final BitSet FOLLOW_any_in_any2195 = new BitSet(new long[]{0xFFFDD40001000000L,0x000000000000207FL});
    public static final BitSet FOLLOW_RPAREN_in_any2198 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_LBRACE_in_any2217 = new BitSet(new long[]{0xFFF5D40001000000L,0x00000000000020FFL});
    public static final BitSet FOLLOW_any_in_any2219 = new BitSet(new long[]{0xFFF5D40001000000L,0x00000000000020FFL});
    public static final BitSet FOLLOW_RBRACE_in_any2222 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_S_in_any2240 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_RCURLY_in_nostatement2255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_nostatement2269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASSKEYWORD_in_noprop2292 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_NUMBER_in_noprop2305 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_COMMA_in_noprop2317 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_GREATER_in_noprop2329 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_LESS_in_noprop2341 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_QUESTION_in_noprop2353 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_PERCENT_in_noprop2365 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_EQUALS_in_noprop2377 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_SLASH_in_noprop2389 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_noprop2401 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_PLUS_in_noprop2413 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_ASTERISK_in_noprop2425 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_noprop2440 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_INCLUDES_in_noprop2452 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_COLON_in_noprop2464 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_STRING_CHAR_in_noprop2476 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_INVALID_TOKEN_in_noprop2488 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_S_in_noprop2501 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_NUMBER_in_norule2516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENTAGE_in_norule2529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_norule2541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_norule2554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_URI_in_norule2568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNIRANGE_in_norule2585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCLUDES_in_norule2599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_norule2613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_norule2627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_norule2641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_norule2655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENT_in_norule2669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_norule2683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_norule2697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_norule2711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_norule2724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_norule2737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DASHMATCH_in_norule2751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_norule2765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_99_in_norule2779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_100_in_norule2790 = new BitSet(new long[]{0x0000000000000002L});

}