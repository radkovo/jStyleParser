// $ANTLR 3.1.2 /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g 2013-01-21 09:15:30

package cz.vutbr.web.csskit.antlr;

import org.fit.net.DataURLHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.SupportedCSS; 



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CSSLexer extends Lexer {
    public static final int FUNCTION=47;
    public static final int APOS=78;
    public static final int NAME_CHAR=84;
    public static final int CLASSKEYWORD=49;
    public static final int PSEUDO=13;
    public static final int INVALID_STATEMENT=27;
    public static final int LBRACE=67;
    public static final int ATTRIBUTE=17;
    public static final int INVALID_TOKEN=71;
    public static final int EQUALS=61;
    public static final int NAME_START=83;
    public static final int NUMBER_MACR=75;
    public static final int MEDIA=40;
    public static final int CHARSET=33;
    public static final int NL_CHAR=88;
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
    public static final int ATKEYWORD=41;
    public static final int CHILD=15;
    public static final int INVALID_STRING=23;
    public static final int RULE=10;
    public static final int PERCENT=60;
    public static final int RBRACE=68;
    public static final int W_CHAR=80;
    public static final int PARENBLOCK=8;
    public static final int STRING_MACR=73;
    public static final int W_MACR=76;
    public static final int QUOT=79;
    public static final int DESCENDANT=16;
    public static final int NUMBER=50;
    public static final int URI_CHAR=87;
    public static final int HASH=54;
    public static final int LCURLY=38;
    public static final int SET=18;
    public static final int SEMICOLON=43;
    public static final int NAME_MACR=74;
    public static final int S=30;
    public static final int CDO=31;
    public static final int VALUE=20;
    public static final int MINUS=45;
    public static final int CDC=32;
    public static final int T__89=89;
    public static final int PERCENTAGE=51;
    public static final int INVALID_SELECTOR=24;
    public static final int IMPORTANT=21;
    public static final int URI=53;
    public static final int ESCAPE_CHAR=86;
    public static final int COLON=36;
    public static final int PAGE=35;
    public static final int STRING_CHAR=70;
    public static final int DASHMATCH=65;
    public static final int ADJACENT=14;
    public static final int QUESTION=59;
    public static final int IMPORT_END=22;
    public static final int INVALID_IMPORT=28;
    public static final int INLINESTYLE=5;
    public static final int RCURLY=39;
    public static final int SL_COMMENT=82;
    public static final int IDENT_MACR=72;
    public static final int STRING=69;
    public static final int URI_MACR=77;

        

        private static Logger log = LoggerFactory.getLogger(CSSLexer.class);
        
        private static SupportedCSS css = CSSFactory.getSupportedCSS();
        
        public static class LexerState {
            
            public enum RecoveryMode {
                BALANCED,
                FUNCTION, 
                RULE,
                DECL
            } 
        
            public short curlyNest;
            public short parenNest;
            public boolean quotOpen;
            public boolean aposOpen;
            
            public LexerState() {
            	this.curlyNest = 0;
            	this.parenNest = 0;
            	this.quotOpen = false;
            	this.aposOpen = false;
            }
            
            public LexerState(LexerState clone) {
            	this();
                this.curlyNest = clone.curlyNest;
                this.parenNest = clone.parenNest;
                this.quotOpen = clone.quotOpen;
                this.aposOpen = clone.aposOpen;
            }
            
            @Override
            public boolean equals(Object o)
            {
                return (this.curlyNest == ((LexerState) o).curlyNest &&
                        this.parenNest == ((LexerState) o).parenNest &&
                        this.quotOpen == ((LexerState) o).quotOpen &&
                        this.aposOpen == ((LexerState) o).aposOpen);
            }
            
            /**
             * Checks whether all pair characters (single and double quotatation marks,
             * curly braces) are balanced
    		     */ 
            public boolean isBalanced() {
            	return aposOpen==false && quotOpen==false && curlyNest==0 && parenNest==0;
            }
            
            /**
             * Checks whether some pair characters are balanced. Modes are:
             * <ul>
             * <li>BALANCED - everything must be balanced: single and double quotatation marks, curly braces, parentheses 
             * <li>FUNCTION - within the function arguments: parentheses must be balanced 
             * <li>RULE - within the CSS rule: all but curly braces
             * <li>DECL - within declaration: all, keep curly braces at desired state
             * </ul> 
             */ 
            public boolean isBalanced(RecoveryMode mode, LexerState state)
            {
                if (mode == RecoveryMode.BALANCED)
                    return aposOpen==false && quotOpen==false && curlyNest==0 && parenNest==0;
                else if (mode == RecoveryMode.FUNCTION)
                    return parenNest==0;
                else if (mode == RecoveryMode.RULE)
                    return aposOpen==false && quotOpen==false && parenNest==0;
                else if (mode == RecoveryMode.DECL)
                    return aposOpen==false && quotOpen==false && parenNest==0 && curlyNest==state.curlyNest;
                else
                    return false;
            }
            
            /**
             * Recovers from unexpected EOF by preparing 
             * new token
             */ 
            public CSSToken generateEOFRecover() {
            	
            	CSSToken t = null;
            
            	if(aposOpen) {
            		this.aposOpen=false;
            		t = new CSSToken(CSSLexer.APOS, this);
            		t.setText("'");
            	}
            	else if(quotOpen) {
            		this.quotOpen=false;
            		t = new CSSToken(CSSLexer.QUOT, this);
            		t.setText("\"");
            	}
            	else if(parenNest!=0) {
            		this.parenNest--;
            		t = new CSSToken(CSSLexer.RPAREN, this);
            		t.setText(")");
            	}
            	else if(curlyNest!=0) {
            		this.curlyNest--;
            		t = new CSSToken(CSSLexer.RCURLY, this);
            		t.setText("}");
            	}
            	
            	log.debug("Recovering from EOF by {}", t);
            	return t;
            }
            
            @Override
            public String toString() {
            	StringBuilder sb = new StringBuilder();
            	sb.append("{=").append(curlyNest)
            	  .append(", (=").append(parenNest)
            	  .append(", '=").append(aposOpen ? "1" : "0")
            	  .append(", \"=").append(quotOpen ? "1" :"0");
            	 
            	return sb.toString();  
            }
        }
        
        private static class LexerStream {
        
        	public CharStream input;
        	public int mark;
        	public LexerState ls;
        	
        	public LexerStream(CharStream input, LexerState ls) {
        	    this.input = input;
        	    this.mark = input.mark();
        	    this.ls = new LexerState(ls);
        	}
        }
        
        // number of already processed tokens (for checking the beginning of the style sheet)
        private int tokencnt = 0;
        
        // lexer states for imported files
        private Stack<LexerStream> imports;
        
        // current lexer state
        private LexerState ls;
        
        // this is for orthogonality
        @SuppressWarnings("unused")
        private StyleSheet stylesheet;
        
        // token recovery
        private Stack<Integer> expectedToken;
        
        /**
         * This function must be called to initialize lexer's state.
         * Because we can't change directly generated constructors.
         * @param stylesheet CSS StyleSheet instance  
         */
        public CSSLexer init(StyleSheet stylesheet) {
    	    this.imports = new Stack<LexerStream>();
    	    this.expectedToken = new Stack<Integer>();
    		this.ls = new LexerState();
    		this.stylesheet = stylesheet;
    		return this;
        }
        
        @Override
        public void reset() {
        	super.reset();
        	this.ls = new LexerState();
        }
        
        /**
         * Overrides next token to match includes and to 
         * recover from EOF
         */
    	@Override 
        public Token nextToken(){
           Token token = nextTokenRecover();

           //count non-empty tokens for eventual checking of the style sheet start
           if (token.getType() == S) {
               tokencnt++;
           }

           // recover from unexpected EOF
           if(token==Token.EOF_TOKEN && !ls.isBalanced()) {
               CSSToken t = ls.generateEOFRecover(); 
               return (Token) t;
           }

           // push back import stream
           // We've got EOF and have non empty stack
           if(token==Token.EOF_TOKEN && !imports.empty()){

           	 // prepare end token 	
           	 CSSToken t = new CSSToken(IMPORT_END, ls);
           	 t.setText("IMPORT_END");
           
             // We've got EOF and have non empty stack.
             LexerStream stream = imports.pop();
             setCharStream(stream.input);
             input.rewind(stream.mark);
             this.ls = stream.ls;
             
             // send created token
             return (Token) t;
             //token = nextTokenRecover();
           }       

           // Skip first token after switching on another input.
           if(((CommonToken)token).getStartIndex() < 0)
             token = nextToken();
            
           return token;
        }

        /**
    	 * Adds contextual information about n      {
    	 esting into token.
    	 */
        @Override
    	public Token emit() {
    		CSSToken t = new CSSToken(input, state.type, state.channel,
                            state.tokenStartCharIndex, getCharIndex()-1);
            t.setLine(state.tokenStartLine);
            t.setText(state.text);
            t.setCharPositionInLine(state.tokenStartCharPositionInLine);
            t.setBase(((CSSInputStream) input).getBase());
            
            // clone lexer state
            t.setLexerState(new LexerState(ls));
            emit(t);
            return t;
    	}

    	@Override
        public void emitErrorMessage(String msg) {
        	log.info("ANTLR: {}", msg);
        }
        
        /**
         * Does special token recovery for some cases
         */ 
        @Override
        public void recover(RecognitionException re) {
        	// there is no special recovery
        	if(expectedToken.isEmpty())
        		super.recover(re);
        	else {
        		switch(expectedToken.pop().intValue()) {
        		
        		case IMPORT:  // IMPORT share recovery rules with CHARSET
        		case CHARSET:
        			final BitSet charsetFollow = BitSet.of((int) '}', (int) ';');
        			consumeUntilBalanced(charsetFollow);
        			break;
        		case STRING:
        			// eat character which should be newline but not EOF
        			if(consumeAnyButEOF()) {
        				// set back to uninitialized state
        				ls.quotOpen = false;
        				ls.aposOpen = false;
        				// create invalid string token
        				state.token = (Token) new CSSToken(INVALID_STRING, ls);
            			state.token.setText("INVALID_STRING");
        			}
        			// we can't just let parser generate missing 
        		    // single/double quot token
        			// because we have not emitted content of string yet!
        			// we will fake string token
        			else {
        				char enclosing = (ls.quotOpen) ? '"' : '\'';
        				ls.quotOpen = false;
        				ls.aposOpen = false;
        				state.token = (Token) new CSSToken(STRING, ls, 
        					state.tokenStartCharIndex, getCharIndex() -1);
            			state.token.setText(
            				input.substring(state.tokenStartCharIndex, getCharIndex() -1)
            				+ enclosing);	
        			}
        			break;
        		default:
        			super.recover(re);
        		}
        	}	
        }
        
        /**
         * Implements Lexer's next token with extra token passing from
         * recovery function 
         */
        private Token nextTokenRecover() {
        	while (true) {
    			state.token = null;
    			state.channel = Token.DEFAULT_CHANNEL;
    			state.tokenStartCharIndex = input.index();
    			state.tokenStartCharPositionInLine = input.getCharPositionInLine();
    			state.tokenStartLine = input.getLine();
    			state.text = null;
    			if ( input.LA(1)==CharStream.EOF ) {
    				return CSSToken.EOF_TOKEN;
    			}
    			try {
    				mTokens();
    				if ( state.token==null ) {
    					emit();
    				}
    				else if ( state.token==Token.SKIP_TOKEN ) {
    					continue;
    				}
    				return state.token;
    			}
    			catch (RecognitionException re) {
    				reportError(re);
    				if ( re instanceof NoViableAltException ) {
    					recover(re); 
    				}

    				// there can be token returned from recovery
                    if(state.token!=null) {
                        state.token.setChannel(Token.INVALID_TOKEN_TYPE);
                      	state.token.setInputStream(input);
                       	state.token.setLine(state.tokenStartLine);
                		state.token.setCharPositionInLine(state.tokenStartCharPositionInLine);
                		emit(state.token);
                		return state.token;
                    }
    			}
    		}
    	}
        
        /**
         * Eats characters until on from follow is found and Lexer state 
         * is balanced at the moment
         */ 
        private void consumeUntilBalanced(BitSet follow) {

        	log.debug("Lexer entered consumeUntilBalanced with {} and follow {}", 
        		ls, follow);
        
        	int c;
    		do {
        		c = input.LA(1);
        		// change apostrophe state
        		if(c=='\'' && ls.quotOpen==false) {
        			ls.aposOpen = !ls.aposOpen;
        		}
        		// change quot state
        		else if (c=='"' && ls.aposOpen==false) {
        			ls.quotOpen = !ls.quotOpen;
        		}
        		else if(c=='(') {
        			ls.parenNest++;
        		}
        		else if(c==')' && ls.parenNest>0) {
        			ls.parenNest--;
        		}
        		else if(c=='{') {
        			ls.curlyNest++;
        		}
        		else if(c=='}' && ls.curlyNest>0) {
        			ls.curlyNest--;
        		}
        		// handle end of line in string
        		else if(c=='\n') {
        			if(ls.quotOpen) ls.quotOpen=false;
        			else if(ls.aposOpen) ls.aposOpen=false;
        		} 
        		else if(c==EOF) {
        			log.info("Unexpected EOF during consumeUntilBalanced, EOF not consumed");
        			return;
        		}
        	
        		input.consume();
        		// log result
        		if(log.isTraceEnabled())
        			log.trace("Lexer consumes '{}'({}) until balanced ({}).", 
        				new Object[] {
        					Character.toString((char) c),
        					Integer.toString(c),
        					ls});
        			
        	}while(!(ls.isBalanced() && follow.member(c)));
        }
        
        /**
         * Consumes arbitrary character but EOF
         * @return <code>false</code> if EOF was matched,
         *         <code>true</code> otherwise and that character was consumed
         */
        private boolean consumeAnyButEOF() {
        
        	int c = input.LA(1);
        	
        	if(c==CharStream.EOF)
        		return false;
        		
        	if(log.isTraceEnabled())
        		log.trace("Lexer consumes '{}' while consumeButEOF", 
        					Character.toString((char)c));
        	
        	// consume character				
        	input.consume();
        	return true;
        }
        
        /**
         * Reads all the contents of an expression. Parenthesis are matched but not in strings.
         */ 
        private String readExpressionContents() 
        {
          log.debug("readExpressionContents"); 
        
          StringBuffer ret = new StringBuffer(); 
          int parenN = 1; /* one already open */
          boolean inApos = false;
          boolean inQuot = false;
          boolean esc = false;
          boolean finished = false;
          int c;
    	    while (!finished)
    	    {
    	        c = input.LA(1);
    	        if(c=='\'' && !inQuot && !(inApos && esc)) {
    	            inApos = !inApos;
    	        }
    	        else if (c=='"' && !inApos && !(inQuot && esc)) {
    	            inQuot = !inQuot;
    	        }
    	        else if(c=='(' && !inApos && !inQuot) {
    	            parenN++;
    	        }
    	        else if(c==')' && parenN>0  && !inApos && !inQuot) {
    	            parenN--;
    	            if (parenN == 0) finished = true;
    	        }
    	        // handle end of line in string
    	        else if (c=='\n') {
    	           inQuot = false;
    	           inApos = false;
    	        } 
    	        else if(c==EOF) {
    	          log.info("Unexpected EOF during consumeUntilBalanced, EOF not consumed");
    	          return ret.toString();
    	        }
    	        
    	        esc = (c == '\\') && !esc;
    	        
    	        if (!finished) ret.append((char) c);
    	        
    	        input.consume();
    	          
    	    }
    	    
    	    log.debug("Expr: " + ret.toString());
          return ret.toString();
        }
        


    // delegates
    // delegators

    public CSSLexer() {;} 
    public CSSLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public CSSLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g"; }

    // $ANTLR start "T__89"
    public final void mT__89() throws RecognitionException {
        try {
            int _type = T__89;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:480:7: ( 'important' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:480:9: 'important'
            {
            match("important"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__89"

    // $ANTLR start "T__90"
    public final void mT__90() throws RecognitionException {
        try {
            int _type = T__90;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:481:7: ( '#' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:481:9: '#'
            {
            match('#'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__90"

    // $ANTLR start "IDENT"
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1054:2: ( IDENT_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1054:4: IDENT_MACR
            {
            mIDENT_MACR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IDENT"

    // $ANTLR start "CHARSET"
    public final void mCHARSET() throws RecognitionException {
        try {
            int _type = CHARSET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            Token s=null;


            	expectedToken.push(new Integer(CHARSET));

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1065:2: ( '@charset' ( S )* s= STRING_MACR ( S )* SEMICOLON )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1065:4: '@charset' ( S )* s= STRING_MACR ( S )* SEMICOLON
            {
            match("@charset"); 

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1065:15: ( S )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\t' && LA1_0<='\n')||(LA1_0>='\f' && LA1_0<='\r')||LA1_0==' ') ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1065:15: S
            	    {
            	    mS(); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            int sStart77 = getCharIndex();
            mSTRING_MACR(); 
            s = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, sStart77, getCharIndex()-1);
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1065:32: ( S )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='\t' && LA2_0<='\n')||(LA2_0>='\f' && LA2_0<='\r')||LA2_0==' ') ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1065:32: S
            	    {
            	    mS(); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            mSEMICOLON(); 

            	    // we have to trim manually
            	    String enc = CSSToken.extractSTRING(s.getText());
            	    //System.err.println("CHARSET"+tokencnt);
            	    if (tokencnt <= 1) //we are at the beginning of the style sheet
            	    {
            			    try {
            			           log.warn("Changing charset to {}", enc);
            			          ((CSSInputStream) input).setEncoding(enc);
            			          //input = setCharStream(new ANTLFileStream(input.getSourceName(), enc));
            			        }
            			        catch(IllegalCharsetNameException icne) {
            			        	log.warn("Could not change to unsupported charset!", icne);
            			        	throw new RuntimeException(new CSSException("Unsupported charset: " + enc));
            			        }
            			        catch (IOException e) {
                            log.warn("Could not change to unsupported charset!", e);
            			        }
            			 }
            			 else
            			      log.warn("Ignoring @charset rule not at the beginning of the style sheet");
            	  

            }

            state.type = _type;
            state.channel = _channel;

            	expectedToken.pop();
        }
        finally {
        }
    }
    // $ANTLR end "CHARSET"

    // $ANTLR start "IMPORT"
    public final void mIMPORT() throws RecognitionException {
        try {
            int _type = IMPORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            Token s=null;
            Token m=null;


            	expectedToken.push(new Integer(IMPORT));
            	StringBuilder media = new StringBuilder();
            	String mText = null;

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1099:2: ( '@import' ( S )* (s= STRING_MACR | s= URI ) ( S )* (m= IDENT_MACR ( S )* ( ',' ( S )* m= IDENT_MACR ( S )* )* )? SEMICOLON )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1099:4: '@import' ( S )* (s= STRING_MACR | s= URI ) ( S )* (m= IDENT_MACR ( S )* ( ',' ( S )* m= IDENT_MACR ( S )* )* )? SEMICOLON
            {
            match("@import"); 

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1099:14: ( S )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='\t' && LA3_0<='\n')||(LA3_0>='\f' && LA3_0<='\r')||LA3_0==' ') ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1099:14: S
            	    {
            	    mS(); 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1100:4: (s= STRING_MACR | s= URI )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='\"'||LA4_0=='\'') ) {
                alt4=1;
            }
            else if ( (LA4_0=='u') ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1100:5: s= STRING_MACR
                    {
                    int sStart122 = getCharIndex();
                    mSTRING_MACR(); 
                    s = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, sStart122, getCharIndex()-1);
                     s.setType(STRING);

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1101:7: s= URI
                    {
                    int sStart135 = getCharIndex();
                    mURI(); 
                    s = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, sStart135, getCharIndex()-1);
                    s.setType(URI);

                    }
                    break;

            }

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1101:33: ( S )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\t' && LA5_0<='\n')||(LA5_0>='\f' && LA5_0<='\r')||LA5_0==' ') ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1101:33: S
            	    {
            	    mS(); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1102:6: (m= IDENT_MACR ( S )* ( ',' ( S )* m= IDENT_MACR ( S )* )* )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0>='A' && LA10_0<='Z')||LA10_0=='\\'||LA10_0=='_'||(LA10_0>='a' && LA10_0<='z')||(LA10_0>='\u0080' && LA10_0<='\uD7FF')||(LA10_0>='\uE000' && LA10_0<='\uFFFD')) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1102:7: m= IDENT_MACR ( S )* ( ',' ( S )* m= IDENT_MACR ( S )* )*
                    {
                    int mStart151 = getCharIndex();
                    mIDENT_MACR(); 
                    m = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, mStart151, getCharIndex()-1);
                     
                    	        mText = m.getText();
                    	    	if(css.isSupportedMedia(mText)) 
                    	    		media.append(mText); 
                    	    	else
                    	    	    log.debug("Invalid import media \"{}\"", mText);
                    	     
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1109:7: ( S )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='\t' && LA6_0<='\n')||(LA6_0>='\f' && LA6_0<='\r')||LA6_0==' ') ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1109:7: S
                    	    {
                    	    mS(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1110:9: ( ',' ( S )* m= IDENT_MACR ( S )* )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==',') ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1110:10: ',' ( S )* m= IDENT_MACR ( S )*
                    	    {
                    	    match(','); 
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1110:14: ( S )*
                    	    loop7:
                    	    do {
                    	        int alt7=2;
                    	        int LA7_0 = input.LA(1);

                    	        if ( ((LA7_0>='\t' && LA7_0<='\n')||(LA7_0>='\f' && LA7_0<='\r')||LA7_0==' ') ) {
                    	            alt7=1;
                    	        }


                    	        switch (alt7) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1110:14: S
                    	    	    {
                    	    	    mS(); 

                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop7;
                    	        }
                    	    } while (true);

                    	    int mStart182 = getCharIndex();
                    	    mIDENT_MACR(); 
                    	    m = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, mStart182, getCharIndex()-1);
                    	     
                    	    	         mText = m.getText();
                    	    	       	 if(css.isSupportedMedia(mText)) 
                    	    	       	 		media.append(",").append(mText);
                    	    	       	 else
                    	    	    	    log.debug("Invalid import media \"{}\"", mText);		
                    	    	       	
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1117:9: ( S )*
                    	    loop8:
                    	    do {
                    	        int alt8=2;
                    	        int LA8_0 = input.LA(1);

                    	        if ( ((LA8_0>='\t' && LA8_0<='\n')||(LA8_0>='\f' && LA8_0<='\r')||LA8_0==' ') ) {
                    	            alt8=1;
                    	        }


                    	        switch (alt8) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1117:9: S
                    	    	    {
                    	    	    mS(); 

                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop8;
                    	        }
                    	    } while (true);


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    }
                    break;

            }

            mSEMICOLON(); 

            		    // do some funny work with file name to be imported
                    String fileName = s.getText();
                    log.debug("FILE: " + fileName);
                        	  	
                    if(s.getType()==STRING) 
                    	fileName = CSSToken.extractSTRING(fileName);
                    else
                    	fileName = CSSToken.extractURI(fileName);
                        	  	
                    log.info("Will import file \"{}\" with media: {}", 
                      		fileName, media.toString());           	  	
                        	  	
                    // import file
                    URL url = null;
                    try {
                    		    // construct URL
                    		    log.debug("BASE: " + ((CSSInputStream) input).getBase());
                    		    URL base = ((CSSInputStream) input).getBase();
                    		    if (base != null)
                          	    url = DataURLHandler.createURL(base, fileName);
                          	else
                          	{
                          	    log.warn("Base URL is unknown");
                                url = DataURLHandler.createURL(base, fileName);
                          	}
                          	               			
                          	log.debug("Actually, will try to import file \"{}\"", url.toString());	
                          			
                            // save current lexer's stream
                            LexerStream stream = new LexerStream(input, ls);
                            imports.push(stream);
                                	
                            CSSToken t = new CSSToken(IMPORT, ls);
                            t.setText(media.toString());
                                	
                            // switch on new stream
                            String enc = ((CSSInputStream) input).getEncoding();
                            setCharStream(CSSInputStream.urlStream(url, enc));
                            reset();
                                	
                            log.info("File \"{}\" was imported.", url.toString());
                            emit(t);
                     }
                     catch(MalformedURLException mue) {
                     		log.warn("Unable to construct URL for fileName", fileName); 
                          	// set type to invalid import
                            _type = INVALID_IMPORT;
                            setText("INVALID_IMPORT");
                     }              		 
                     catch(IOException fnf) {
                     		log.warn("Cannot read \"{}\" to import: {}", fileName, fnf.getMessage());
                            // restore state
                            imports.pop();
                            // set type to invalid import
                            _type = INVALID_IMPORT;
                            setText("INVALID_IMPORT");
                      }
            	

            }

            state.type = _type;
            state.channel = _channel;

            	expectedToken.pop();
        }
        finally {
        }
    }
    // $ANTLR end "IMPORT"

    // $ANTLR start "MEDIA"
    public final void mMEDIA() throws RecognitionException {
        try {
            int _type = MEDIA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1182:2: ( '@media' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1182:4: '@media'
            {
            match("@media"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MEDIA"

    // $ANTLR start "PAGE"
    public final void mPAGE() throws RecognitionException {
        try {
            int _type = PAGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1186:2: ( '@page' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1186:4: '@page'
            {
            match("@page"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PAGE"

    // $ANTLR start "ATKEYWORD"
    public final void mATKEYWORD() throws RecognitionException {
        try {
            int _type = ATKEYWORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1191:2: ( '@' ( MINUS )? IDENT_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1191:4: '@' ( MINUS )? IDENT_MACR
            {
            match('@'); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1191:8: ( MINUS )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='-') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1191:8: MINUS
                    {
                    mMINUS(); 

                    }
                    break;

            }

            mIDENT_MACR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ATKEYWORD"

    // $ANTLR start "CLASSKEYWORD"
    public final void mCLASSKEYWORD() throws RecognitionException {
        try {
            int _type = CLASSKEYWORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1195:5: ( '.' IDENT_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1195:7: '.' IDENT_MACR
            {
            match('.'); 
            mIDENT_MACR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLASSKEYWORD"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            	expectedToken.push(new Integer(STRING));

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1206:2: ( STRING_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1206:4: STRING_MACR
            {
            mSTRING_MACR(); 

            }

            state.type = _type;
            state.channel = _channel;

            	expectedToken.pop();
        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "HASH"
    public final void mHASH() throws RecognitionException {
        try {
            int _type = HASH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1211:2: ( '#' NAME_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1211:4: '#' NAME_MACR
            {
            match('#'); 
            mNAME_MACR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "HASH"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1216:2: ( NUMBER_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1216:4: NUMBER_MACR
            {
            mNUMBER_MACR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "PERCENTAGE"
    public final void mPERCENTAGE() throws RecognitionException {
        try {
            int _type = PERCENTAGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1221:2: ( NUMBER_MACR '%' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1221:4: NUMBER_MACR '%'
            {
            mNUMBER_MACR(); 
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PERCENTAGE"

    // $ANTLR start "DIMENSION"
    public final void mDIMENSION() throws RecognitionException {
        try {
            int _type = DIMENSION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1226:2: ( NUMBER_MACR IDENT_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1226:4: NUMBER_MACR IDENT_MACR
            {
            mNUMBER_MACR(); 
            mIDENT_MACR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIMENSION"

    // $ANTLR start "URI"
    public final void mURI() throws RecognitionException {
        try {
            int _type = URI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1231:2: ( 'url(' W_MACR ( STRING_MACR | URI_MACR ) W_MACR ')' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1231:4: 'url(' W_MACR ( STRING_MACR | URI_MACR ) W_MACR ')'
            {
            match("url("); 

            mW_MACR(); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1231:18: ( STRING_MACR | URI_MACR )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='\"'||LA12_0=='\'') ) {
                alt12=1;
            }
            else if ( ((LA12_0>='\t' && LA12_0<='\n')||(LA12_0>='\f' && LA12_0<='\r')||(LA12_0>=' ' && LA12_0<='!')||(LA12_0>='#' && LA12_0<='&')||(LA12_0>=')' && LA12_0<='~')||(LA12_0>='\u0080' && LA12_0<='\uD7FF')||(LA12_0>='\uE000' && LA12_0<='\uFFFD')) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1231:19: STRING_MACR
                    {
                    mSTRING_MACR(); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1231:33: URI_MACR
                    {
                    mURI_MACR(); 

                    }
                    break;

            }

            mW_MACR(); 
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "URI"

    // $ANTLR start "UNIRANGE"
    public final void mUNIRANGE() throws RecognitionException {
        try {
            int _type = UNIRANGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1235:9: ( 'U+' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) )? ( '-' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )? )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1236:2: 'U+' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) )? ( '-' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )?
            {
            match("U+"); 

            if ( (input.LA(1)>='0' && input.LA(1)<='9')||input.LA(1)=='?'||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( (input.LA(1)>='0' && input.LA(1)<='9')||input.LA(1)=='?'||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( (input.LA(1)>='0' && input.LA(1)<='9')||input.LA(1)=='?'||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( (input.LA(1)>='0' && input.LA(1)<='9')||input.LA(1)=='?'||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1240:7: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>='0' && LA13_0<='9')||LA13_0=='?'||(LA13_0>='A' && LA13_0<='F')||(LA13_0>='a' && LA13_0<='f')) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1240:8: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' )
                    {
                    if ( (input.LA(1)>='0' && input.LA(1)<='9')||input.LA(1)=='?'||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    if ( (input.LA(1)>='0' && input.LA(1)<='9')||input.LA(1)=='?'||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1241:2: ( '-' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0=='-') ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1241:3: '-' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )?
                    {
                    match('-'); 
                    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1246:14: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( ((LA14_0>='0' && LA14_0<='9')||(LA14_0>='A' && LA14_0<='F')||(LA14_0>='a' && LA14_0<='f')) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1246:15: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
                            {
                            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}

                            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}


                            }
                            break;

                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNIRANGE"

    // $ANTLR start "CDO"
    public final void mCDO() throws RecognitionException {
        try {
            int _type = CDO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1252:2: ( '<!--' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1252:4: '<!--'
            {
            match("<!--"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CDO"

    // $ANTLR start "CDC"
    public final void mCDC() throws RecognitionException {
        try {
            int _type = CDC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1257:2: ( '-->' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1257:4: '-->'
            {
            match("-->"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CDC"

    // $ANTLR start "SEMICOLON"
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1261:2: ( ';' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1261:4: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEMICOLON"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1265:2: ( ':' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1265:4: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1269:5: ( ',' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1269:7: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "QUESTION"
    public final void mQUESTION() throws RecognitionException {
        try {
            int _type = QUESTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1273:2: ( '?' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1273:4: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUESTION"

    // $ANTLR start "PERCENT"
    public final void mPERCENT() throws RecognitionException {
        try {
            int _type = PERCENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1277:2: ( '%' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1277:4: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PERCENT"

    // $ANTLR start "EQUALS"
    public final void mEQUALS() throws RecognitionException {
        try {
            int _type = EQUALS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1281:5: ( '=' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1281:7: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUALS"

    // $ANTLR start "SLASH"
    public final void mSLASH() throws RecognitionException {
        try {
            int _type = SLASH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1285:5: ( '/' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1285:7: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SLASH"

    // $ANTLR start "GREATER"
    public final void mGREATER() throws RecognitionException {
        try {
            int _type = GREATER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1289:5: ( '>' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1289:7: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GREATER"

    // $ANTLR start "LESS"
    public final void mLESS() throws RecognitionException {
        try {
            int _type = LESS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1293:5: ( '<' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1293:7: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LESS"

    // $ANTLR start "LCURLY"
    public final void mLCURLY() throws RecognitionException {
        try {
            int _type = LCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1297:2: ( '{' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1297:4: '{'
            {
            match('{'); 
            ls.curlyNest++;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LCURLY"

    // $ANTLR start "RCURLY"
    public final void mRCURLY() throws RecognitionException {
        try {
            int _type = RCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1301:2: ( '}' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1301:4: '}'
            {
            match('}'); 
             if(ls.curlyNest>0) ls.curlyNest--;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RCURLY"

    // $ANTLR start "APOS"
    public final void mAPOS() throws RecognitionException {
        try {
            int _type = APOS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1305:2: ( '\\'' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1305:4: '\\''
            {
            match('\''); 
             ls.aposOpen=!ls.aposOpen; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "APOS"

    // $ANTLR start "QUOT"
    public final void mQUOT() throws RecognitionException {
        try {
            int _type = QUOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1309:2: ( '\"' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1309:4: '\"'
            {
            match('\"'); 
             ls.quotOpen=!ls.quotOpen; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUOT"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1313:2: ( '(' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1313:4: '('
            {
            match('('); 
            ls.parenNest++; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1317:2: ( ')' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1317:4: ')'
            {
            match(')'); 
             if(ls.parenNest>0) ls.parenNest--; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "LBRACE"
    public final void mLBRACE() throws RecognitionException {
        try {
            int _type = LBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1321:2: ( '[' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1321:4: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LBRACE"

    // $ANTLR start "RBRACE"
    public final void mRBRACE() throws RecognitionException {
        try {
            int _type = RBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1325:2: ( ']' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1325:4: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RBRACE"

    // $ANTLR start "EXCLAMATION"
    public final void mEXCLAMATION() throws RecognitionException {
        try {
            int _type = EXCLAMATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1329:5: ( '!' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1329:7: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EXCLAMATION"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1333:2: ( '-' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1333:4: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1337:2: ( '+' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1337:4: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "ASTERISK"
    public final void mASTERISK() throws RecognitionException {
        try {
            int _type = ASTERISK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1341:2: ( '*' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1341:4: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASTERISK"

    // $ANTLR start "S"
    public final void mS() throws RecognitionException {
        try {
            int _type = S;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1346:2: ( ( W_CHAR )+ )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1346:4: ( W_CHAR )+
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1346:4: ( W_CHAR )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>='\t' && LA16_0<='\n')||(LA16_0>='\f' && LA16_0<='\r')||LA16_0==' ') ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1346:4: W_CHAR
            	    {
            	    mW_CHAR(); 

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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "S"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1350:2: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1350:4: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1350:9: ( options {greedy=false; } : . )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0=='*') ) {
                    int LA17_1 = input.LA(2);

                    if ( (LA17_1=='/') ) {
                        alt17=2;
                    }
                    else if ( ((LA17_1>='\u0000' && LA17_1<='.')||(LA17_1>='0' && LA17_1<='\uFFFF')) ) {
                        alt17=1;
                    }


                }
                else if ( ((LA17_0>='\u0000' && LA17_0<=')')||(LA17_0>='+' && LA17_0<='\uFFFF')) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1350:37: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            match("*/"); 

             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "SL_COMMENT"
    public final void mSL_COMMENT() throws RecognitionException {
        try {
            int _type = SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1354:2: ( '//' ( options {greedy=false; } : . )* ( '\\n' | '\\r' ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1354:4: '//' ( options {greedy=false; } : . )* ( '\\n' | '\\r' )
            {
            match("//"); 

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1354:9: ( options {greedy=false; } : . )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0=='\n'||LA18_0=='\r') ) {
                    alt18=2;
                }
                else if ( ((LA18_0>='\u0000' && LA18_0<='\t')||(LA18_0>='\u000B' && LA18_0<='\f')||(LA18_0>='\u000E' && LA18_0<='\uFFFF')) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1354:37: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

            if ( input.LA(1)=='\n'||input.LA(1)=='\r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

             _channel=HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SL_COMMENT"

    // $ANTLR start "EXPRESSION"
    public final void mEXPRESSION() throws RecognitionException {
        try {
            int _type = EXPRESSION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1359:3: ( 'expression(' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1359:5: 'expression('
            {
            match("expression("); 

             readExpressionContents(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EXPRESSION"

    // $ANTLR start "FUNCTION"
    public final void mFUNCTION() throws RecognitionException {
        try {
            int _type = FUNCTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1364:2: ( IDENT_MACR '(' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1364:4: IDENT_MACR '('
            {
            mIDENT_MACR(); 
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FUNCTION"

    // $ANTLR start "INCLUDES"
    public final void mINCLUDES() throws RecognitionException {
        try {
            int _type = INCLUDES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1368:2: ( '~=' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1368:4: '~='
            {
            match("~="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INCLUDES"

    // $ANTLR start "DASHMATCH"
    public final void mDASHMATCH() throws RecognitionException {
        try {
            int _type = DASHMATCH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1372:2: ( '|=' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1372:4: '|='
            {
            match("|="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DASHMATCH"

    // $ANTLR start "INVALID_TOKEN"
    public final void mINVALID_TOKEN() throws RecognitionException {
        try {
            int _type = INVALID_TOKEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1376:2: ( . )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1376:4: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INVALID_TOKEN"

    // $ANTLR start "IDENT_MACR"
    public final void mIDENT_MACR() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1385:4: ( NAME_START ( NAME_CHAR )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1385:6: NAME_START ( NAME_CHAR )*
            {
            mNAME_START(); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1385:17: ( NAME_CHAR )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0=='-'||(LA19_0>='0' && LA19_0<='9')||(LA19_0>='A' && LA19_0<='Z')||LA19_0=='\\'||LA19_0=='_'||(LA19_0>='a' && LA19_0<='z')||(LA19_0>='\u0080' && LA19_0<='\uD7FF')||(LA19_0>='\uE000' && LA19_0<='\uFFFD')) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1385:17: NAME_CHAR
            	    {
            	    mNAME_CHAR(); 

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "IDENT_MACR"

    // $ANTLR start "NAME_MACR"
    public final void mNAME_MACR() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1390:3: ( ( NAME_CHAR )+ )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1390:5: ( NAME_CHAR )+
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1390:5: ( NAME_CHAR )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0=='-'||(LA20_0>='0' && LA20_0<='9')||(LA20_0>='A' && LA20_0<='Z')||LA20_0=='\\'||LA20_0=='_'||(LA20_0>='a' && LA20_0<='z')||(LA20_0>='\u0080' && LA20_0<='\uD7FF')||(LA20_0>='\uE000' && LA20_0<='\uFFFD')) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1390:5: NAME_CHAR
            	    {
            	    mNAME_CHAR(); 

            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "NAME_MACR"

    // $ANTLR start "NAME_START"
    public final void mNAME_START() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1395:4: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' | NON_ASCII | ESCAPE_CHAR ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1395:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | NON_ASCII | ESCAPE_CHAR )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1395:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | NON_ASCII | ESCAPE_CHAR )
            int alt21=5;
            int LA21_0 = input.LA(1);

            if ( ((LA21_0>='a' && LA21_0<='z')) ) {
                alt21=1;
            }
            else if ( ((LA21_0>='A' && LA21_0<='Z')) ) {
                alt21=2;
            }
            else if ( (LA21_0=='_') ) {
                alt21=3;
            }
            else if ( ((LA21_0>='\u0080' && LA21_0<='\uD7FF')||(LA21_0>='\uE000' && LA21_0<='\uFFFD')) ) {
                alt21=4;
            }
            else if ( (LA21_0=='\\') ) {
                alt21=5;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1395:7: 'a' .. 'z'
                    {
                    matchRange('a','z'); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1395:18: 'A' .. 'Z'
                    {
                    matchRange('A','Z'); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1395:29: '_'
                    {
                    match('_'); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1395:35: NON_ASCII
                    {
                    mNON_ASCII(); 

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1395:47: ESCAPE_CHAR
                    {
                    mESCAPE_CHAR(); 

                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "NAME_START"

    // $ANTLR start "NON_ASCII"
    public final void mNON_ASCII() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1400:4: ( ( '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1400:6: ( '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )
            {
            if ( (input.LA(1)>='\u0080' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uE000' && input.LA(1)<='\uFFFD') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "NON_ASCII"

    // $ANTLR start "ESCAPE_CHAR"
    public final void mESCAPE_CHAR() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1405:3: ( ( '\\\\' ) ( ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? ) | ( '\\u0020' .. '\\u007E' | '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' ) ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1405:5: ( '\\\\' ) ( ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? ) | ( '\\u0020' .. '\\u007E' | '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' ) )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1405:5: ( '\\\\' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1405:6: '\\\\'
            {
            match('\\'); 

            }

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1406:5: ( ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? ) | ( '\\u0020' .. '\\u007E' | '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' ) )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( ((LA23_0>='0' && LA23_0<='9')||(LA23_0>='A' && LA23_0<='F')||(LA23_0>='a' && LA23_0<='f')) ) {
                int LA23_1 = input.LA(2);

                if ( ((LA23_1>='0' && LA23_1<='9')||(LA23_1>='A' && LA23_1<='F')||(LA23_1>='a' && LA23_1<='f')) ) {
                    alt23=1;
                }
                else {
                    alt23=2;}
            }
            else if ( ((LA23_0>=' ' && LA23_0<='/')||(LA23_0>=':' && LA23_0<='@')||(LA23_0>='G' && LA23_0<='`')||(LA23_0>='g' && LA23_0<='~')||(LA23_0>='\u0080' && LA23_0<='\uD7FF')||(LA23_0>='\uE000' && LA23_0<='\uFFFD')) ) {
                alt23=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1407:7: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1407:7: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1407:8: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )?
                    {
                    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1411:8: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( ((LA22_0>='0' && LA22_0<='9')||(LA22_0>='A' && LA22_0<='F')||(LA22_0>='a' && LA22_0<='f')) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1411:9: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
                            {
                            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}

                            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1414:7: ( '\\u0020' .. '\\u007E' | '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )
                    {
                    if ( (input.LA(1)>=' ' && input.LA(1)<='~')||(input.LA(1)>='\u0080' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uE000' && input.LA(1)<='\uFFFD') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "ESCAPE_CHAR"

    // $ANTLR start "NAME_CHAR"
    public final void mNAME_CHAR() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1420:4: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | NON_ASCII | ESCAPE_CHAR ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1420:6: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | NON_ASCII | ESCAPE_CHAR )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1420:6: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | NON_ASCII | ESCAPE_CHAR )
            int alt24=7;
            int LA24_0 = input.LA(1);

            if ( ((LA24_0>='a' && LA24_0<='z')) ) {
                alt24=1;
            }
            else if ( ((LA24_0>='A' && LA24_0<='Z')) ) {
                alt24=2;
            }
            else if ( ((LA24_0>='0' && LA24_0<='9')) ) {
                alt24=3;
            }
            else if ( (LA24_0=='-') ) {
                alt24=4;
            }
            else if ( (LA24_0=='_') ) {
                alt24=5;
            }
            else if ( ((LA24_0>='\u0080' && LA24_0<='\uD7FF')||(LA24_0>='\uE000' && LA24_0<='\uFFFD')) ) {
                alt24=6;
            }
            else if ( (LA24_0=='\\') ) {
                alt24=7;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1420:7: 'a' .. 'z'
                    {
                    matchRange('a','z'); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1420:18: 'A' .. 'Z'
                    {
                    matchRange('A','Z'); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1420:29: '0' .. '9'
                    {
                    matchRange('0','9'); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1420:40: '-'
                    {
                    match('-'); 

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1420:46: '_'
                    {
                    match('_'); 

                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1420:52: NON_ASCII
                    {
                    mNON_ASCII(); 

                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1420:64: ESCAPE_CHAR
                    {
                    mESCAPE_CHAR(); 

                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "NAME_CHAR"

    // $ANTLR start "NUMBER_MACR"
    public final void mNUMBER_MACR() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1425:4: ( ( '0' .. '9' )+ | ( ( '0' .. '9' )* '.' ( '0' .. '9' )+ ) )
            int alt28=2;
            alt28 = dfa28.predict(input);
            switch (alt28) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1425:6: ( '0' .. '9' )+
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1425:6: ( '0' .. '9' )+
                    int cnt25=0;
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( ((LA25_0>='0' && LA25_0<='9')) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1425:7: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt25 >= 1 ) break loop25;
                                EarlyExitException eee =
                                    new EarlyExitException(25, input);
                                throw eee;
                        }
                        cnt25++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1425:20: ( ( '0' .. '9' )* '.' ( '0' .. '9' )+ )
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1425:20: ( ( '0' .. '9' )* '.' ( '0' .. '9' )+ )
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1425:21: ( '0' .. '9' )* '.' ( '0' .. '9' )+
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1425:21: ( '0' .. '9' )*
                    loop26:
                    do {
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( ((LA26_0>='0' && LA26_0<='9')) ) {
                            alt26=1;
                        }


                        switch (alt26) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1425:22: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop26;
                        }
                    } while (true);

                    match('.'); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1425:37: ( '0' .. '9' )+
                    int cnt27=0;
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( ((LA27_0>='0' && LA27_0<='9')) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1425:38: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

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


                    }


                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "NUMBER_MACR"

    // $ANTLR start "STRING_MACR"
    public final void mSTRING_MACR() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1430:2: ( QUOT ( STRING_CHAR | APOS )* QUOT | APOS ( STRING_CHAR | QUOT )* APOS )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0=='\"') ) {
                alt31=1;
            }
            else if ( (LA31_0=='\'') ) {
                alt31=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1430:4: QUOT ( STRING_CHAR | APOS )* QUOT
                    {
                    mQUOT(); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1430:9: ( STRING_CHAR | APOS )*
                    loop29:
                    do {
                        int alt29=3;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0=='\t'||(LA29_0>=' ' && LA29_0<='!')||(LA29_0>='#' && LA29_0<='&')||(LA29_0>='(' && LA29_0<='~')||(LA29_0>='\u0080' && LA29_0<='\uD7FF')||(LA29_0>='\uE000' && LA29_0<='\uFFFD')) ) {
                            alt29=1;
                        }
                        else if ( (LA29_0=='\'') ) {
                            alt29=2;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1430:10: STRING_CHAR
                    	    {
                    	    mSTRING_CHAR(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1430:24: APOS
                    	    {
                    	    mAPOS(); 
                    	    ls.aposOpen=false;

                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);

                    mQUOT(); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1431:4: APOS ( STRING_CHAR | QUOT )* APOS
                    {
                    mAPOS(); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1431:9: ( STRING_CHAR | QUOT )*
                    loop30:
                    do {
                        int alt30=3;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0=='\t'||(LA30_0>=' ' && LA30_0<='!')||(LA30_0>='#' && LA30_0<='&')||(LA30_0>='(' && LA30_0<='~')||(LA30_0>='\u0080' && LA30_0<='\uD7FF')||(LA30_0>='\uE000' && LA30_0<='\uFFFD')) ) {
                            alt30=1;
                        }
                        else if ( (LA30_0=='\"') ) {
                            alt30=2;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1431:10: STRING_CHAR
                    	    {
                    	    mSTRING_CHAR(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1431:24: QUOT
                    	    {
                    	    mQUOT(); 
                    	    ls.quotOpen=false;

                    	    }
                    	    break;

                    	default :
                    	    break loop30;
                        }
                    } while (true);

                    mAPOS(); 

                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "STRING_MACR"

    // $ANTLR start "STRING_CHAR"
    public final void mSTRING_CHAR() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1436:2: ( ( URI_CHAR | ' ' | '(' | ')' | ( '\\\\' NL_CHAR ) ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1436:5: ( URI_CHAR | ' ' | '(' | ')' | ( '\\\\' NL_CHAR ) )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1436:5: ( URI_CHAR | ' ' | '(' | ')' | ( '\\\\' NL_CHAR ) )
            int alt32=5;
            int LA32_0 = input.LA(1);

            if ( (LA32_0=='\\') ) {
                int LA32_1 = input.LA(2);

                if ( (LA32_1=='\n'||(LA32_1>='\f' && LA32_1<='\r')) ) {
                    alt32=5;
                }
                else {
                    alt32=1;}
            }
            else if ( (LA32_0=='\t'||LA32_0=='!'||(LA32_0>='#' && LA32_0<='&')||(LA32_0>='*' && LA32_0<='[')||(LA32_0>=']' && LA32_0<='~')||(LA32_0>='\u0080' && LA32_0<='\uD7FF')||(LA32_0>='\uE000' && LA32_0<='\uFFFD')) ) {
                alt32=1;
            }
            else if ( (LA32_0==' ') ) {
                alt32=2;
            }
            else if ( (LA32_0=='(') ) {
                alt32=3;
            }
            else if ( (LA32_0==')') ) {
                alt32=4;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1436:6: URI_CHAR
                    {
                    mURI_CHAR(); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1436:17: ' '
                    {
                    match(' '); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1436:23: '('
                    {
                    match('('); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1436:29: ')'
                    {
                    match(')'); 

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1436:35: ( '\\\\' NL_CHAR )
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1436:35: ( '\\\\' NL_CHAR )
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1436:36: '\\\\' NL_CHAR
                    {
                    match('\\'); 
                    mNL_CHAR(); 

                    }


                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "STRING_CHAR"

    // $ANTLR start "URI_MACR"
    public final void mURI_MACR() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1441:2: ( ( URI_CHAR )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1441:4: ( URI_CHAR )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1441:4: ( URI_CHAR )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0=='\t'||LA33_0=='!'||(LA33_0>='#' && LA33_0<='&')||(LA33_0>='*' && LA33_0<='~')||(LA33_0>='\u0080' && LA33_0<='\uD7FF')||(LA33_0>='\uE000' && LA33_0<='\uFFFD')) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1441:4: URI_CHAR
            	    {
            	    mURI_CHAR(); 

            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "URI_MACR"

    // $ANTLR start "URI_CHAR"
    public final void mURI_CHAR() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1446:2: ( ( '\\u0009' | '\\u0021' | '\\u0023' .. '\\u0026' | '\\u002A' .. '\\u007E' ) | NON_ASCII | ESCAPE_CHAR )
            int alt34=3;
            int LA34_0 = input.LA(1);

            if ( (LA34_0=='\\') ) {
                int LA34_1 = input.LA(2);

                if ( ((LA34_1>=' ' && LA34_1<='~')||(LA34_1>='\u0080' && LA34_1<='\uD7FF')||(LA34_1>='\uE000' && LA34_1<='\uFFFD')) ) {
                    alt34=3;
                }
                else {
                    alt34=1;}
            }
            else if ( ((LA34_0>='\u0080' && LA34_0<='\uD7FF')||(LA34_0>='\uE000' && LA34_0<='\uFFFD')) ) {
                alt34=2;
            }
            else if ( (LA34_0=='\t'||LA34_0=='!'||(LA34_0>='#' && LA34_0<='&')||(LA34_0>='*' && LA34_0<='[')||(LA34_0>=']' && LA34_0<='~')) ) {
                alt34=1;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1446:4: ( '\\u0009' | '\\u0021' | '\\u0023' .. '\\u0026' | '\\u002A' .. '\\u007E' )
                    {
                    if ( input.LA(1)=='\t'||input.LA(1)=='!'||(input.LA(1)>='#' && input.LA(1)<='&')||(input.LA(1)>='*' && input.LA(1)<='~') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1447:6: NON_ASCII
                    {
                    mNON_ASCII(); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1447:18: ESCAPE_CHAR
                    {
                    mESCAPE_CHAR(); 

                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "URI_CHAR"

    // $ANTLR start "NL_CHAR"
    public final void mNL_CHAR() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1452:4: ( '\\u000A' | '\\u000D' '\\u000A' | '\\u000D' | '\\u000C' )
            int alt35=4;
            switch ( input.LA(1) ) {
            case '\n':
                {
                alt35=1;
                }
                break;
            case '\r':
                {
                int LA35_2 = input.LA(2);

                if ( (LA35_2=='\n') ) {
                    alt35=2;
                }
                else {
                    alt35=3;}
                }
                break;
            case '\f':
                {
                alt35=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1452:6: '\\u000A'
                    {
                    match('\n'); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1452:17: '\\u000D' '\\u000A'
                    {
                    match('\r'); 
                    match('\n'); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1452:37: '\\u000D'
                    {
                    match('\r'); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1452:48: '\\u000C'
                    {
                    match('\f'); 

                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "NL_CHAR"

    // $ANTLR start "W_MACR"
    public final void mW_MACR() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1457:2: ( ( W_CHAR )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1457:4: ( W_CHAR )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1457:4: ( W_CHAR )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( ((LA36_0>='\t' && LA36_0<='\n')||(LA36_0>='\f' && LA36_0<='\r')||LA36_0==' ') ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1457:4: W_CHAR
            	    {
            	    mW_CHAR(); 

            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "W_MACR"

    // $ANTLR start "W_CHAR"
    public final void mW_CHAR() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1462:4: ( '\\u0009' | '\\u000A' | '\\u000C' | '\\u000D' | '\\u0020' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "W_CHAR"

    public void mTokens() throws RecognitionException {
        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:8: ( T__89 | T__90 | IDENT | CHARSET | IMPORT | MEDIA | PAGE | ATKEYWORD | CLASSKEYWORD | STRING | HASH | NUMBER | PERCENTAGE | DIMENSION | URI | UNIRANGE | CDO | CDC | SEMICOLON | COLON | COMMA | QUESTION | PERCENT | EQUALS | SLASH | GREATER | LESS | LCURLY | RCURLY | APOS | QUOT | LPAREN | RPAREN | LBRACE | RBRACE | EXCLAMATION | MINUS | PLUS | ASTERISK | S | COMMENT | SL_COMMENT | EXPRESSION | FUNCTION | INCLUDES | DASHMATCH | INVALID_TOKEN )
        int alt37=47;
        alt37 = dfa37.predict(input);
        switch (alt37) {
            case 1 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:10: T__89
                {
                mT__89(); 

                }
                break;
            case 2 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:16: T__90
                {
                mT__90(); 

                }
                break;
            case 3 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:22: IDENT
                {
                mIDENT(); 

                }
                break;
            case 4 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:28: CHARSET
                {
                mCHARSET(); 

                }
                break;
            case 5 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:36: IMPORT
                {
                mIMPORT(); 

                }
                break;
            case 6 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:43: MEDIA
                {
                mMEDIA(); 

                }
                break;
            case 7 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:49: PAGE
                {
                mPAGE(); 

                }
                break;
            case 8 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:54: ATKEYWORD
                {
                mATKEYWORD(); 

                }
                break;
            case 9 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:64: CLASSKEYWORD
                {
                mCLASSKEYWORD(); 

                }
                break;
            case 10 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:77: STRING
                {
                mSTRING(); 

                }
                break;
            case 11 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:84: HASH
                {
                mHASH(); 

                }
                break;
            case 12 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:89: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 13 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:96: PERCENTAGE
                {
                mPERCENTAGE(); 

                }
                break;
            case 14 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:107: DIMENSION
                {
                mDIMENSION(); 

                }
                break;
            case 15 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:117: URI
                {
                mURI(); 

                }
                break;
            case 16 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:121: UNIRANGE
                {
                mUNIRANGE(); 

                }
                break;
            case 17 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:130: CDO
                {
                mCDO(); 

                }
                break;
            case 18 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:134: CDC
                {
                mCDC(); 

                }
                break;
            case 19 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:138: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 20 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:148: COLON
                {
                mCOLON(); 

                }
                break;
            case 21 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:154: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 22 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:160: QUESTION
                {
                mQUESTION(); 

                }
                break;
            case 23 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:169: PERCENT
                {
                mPERCENT(); 

                }
                break;
            case 24 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:177: EQUALS
                {
                mEQUALS(); 

                }
                break;
            case 25 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:184: SLASH
                {
                mSLASH(); 

                }
                break;
            case 26 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:190: GREATER
                {
                mGREATER(); 

                }
                break;
            case 27 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:198: LESS
                {
                mLESS(); 

                }
                break;
            case 28 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:203: LCURLY
                {
                mLCURLY(); 

                }
                break;
            case 29 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:210: RCURLY
                {
                mRCURLY(); 

                }
                break;
            case 30 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:217: APOS
                {
                mAPOS(); 

                }
                break;
            case 31 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:222: QUOT
                {
                mQUOT(); 

                }
                break;
            case 32 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:227: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 33 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:234: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 34 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:241: LBRACE
                {
                mLBRACE(); 

                }
                break;
            case 35 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:248: RBRACE
                {
                mRBRACE(); 

                }
                break;
            case 36 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:255: EXCLAMATION
                {
                mEXCLAMATION(); 

                }
                break;
            case 37 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:267: MINUS
                {
                mMINUS(); 

                }
                break;
            case 38 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:273: PLUS
                {
                mPLUS(); 

                }
                break;
            case 39 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:278: ASTERISK
                {
                mASTERISK(); 

                }
                break;
            case 40 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:287: S
                {
                mS(); 

                }
                break;
            case 41 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:289: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 42 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:297: SL_COMMENT
                {
                mSL_COMMENT(); 

                }
                break;
            case 43 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:308: EXPRESSION
                {
                mEXPRESSION(); 

                }
                break;
            case 44 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:319: FUNCTION
                {
                mFUNCTION(); 

                }
                break;
            case 45 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:328: INCLUDES
                {
                mINCLUDES(); 

                }
                break;
            case 46 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:337: DASHMATCH
                {
                mDASHMATCH(); 

                }
                break;
            case 47 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:347: INVALID_TOKEN
                {
                mINVALID_TOKEN(); 

                }
                break;

        }

    }


    protected DFA28 dfa28 = new DFA28(this);
    protected DFA37 dfa37 = new DFA37(this);
    static final String DFA28_eotS =
        "\1\uffff\1\3\2\uffff";
    static final String DFA28_eofS =
        "\4\uffff";
    static final String DFA28_minS =
        "\2\56\2\uffff";
    static final String DFA28_maxS =
        "\2\71\2\uffff";
    static final String DFA28_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA28_specialS =
        "\4\uffff}>";
    static final String[] DFA28_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\2\1\uffff\12\1",
            "",
            ""
    };

    static final short[] DFA28_eot = DFA.unpackEncodedString(DFA28_eotS);
    static final short[] DFA28_eof = DFA.unpackEncodedString(DFA28_eofS);
    static final char[] DFA28_min = DFA.unpackEncodedStringToUnsignedChars(DFA28_minS);
    static final char[] DFA28_max = DFA.unpackEncodedStringToUnsignedChars(DFA28_maxS);
    static final short[] DFA28_accept = DFA.unpackEncodedString(DFA28_acceptS);
    static final short[] DFA28_special = DFA.unpackEncodedString(DFA28_specialS);
    static final short[][] DFA28_transition;

    static {
        int numStates = DFA28_transitionS.length;
        DFA28_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA28_transition[i] = DFA.unpackEncodedString(DFA28_transitionS[i]);
        }
    }

    class DFA28 extends DFA {

        public DFA28(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 28;
            this.eot = DFA28_eot;
            this.eof = DFA28_eof;
            this.min = DFA28_min;
            this.max = DFA28_max;
            this.accept = DFA28_accept;
            this.special = DFA28_special;
            this.transition = DFA28_transition;
        }
        public String getDescription() {
            return "1423:1: fragment NUMBER_MACR : ( ( '0' .. '9' )+ | ( ( '0' .. '9' )* '.' ( '0' .. '9' )+ ) );";
        }
    }
    static final String DFA37_eotS =
        "\1\uffff\1\50\1\61\4\50\3\46\1\76\1\100\1\103\2\50\1\110\1\112\6"+
        "\uffff\1\123\13\uffff\1\50\2\46\1\uffff\1\50\1\uffff\6\50\4\uffff"+
        "\1\50\1\uffff\2\50\4\73\1\uffff\1\103\5\uffff\1\103\3\uffff\1\50"+
        "\32\uffff\7\50\4\73\5\50\1\60\3\50\4\73\5\50\1\uffff\3\50\3\73\1"+
        "\u0091\10\50\2\73\1\u009c\1\uffff\10\50\2\73\1\uffff\5\50\2\73\1"+
        "\uffff\1\50\1\u00aa\1\uffff\1\73\1\50\1\uffff\1\73\1\50\2\uffff";
    static final String DFA37_eofS =
        "\u00af\uffff";
    static final String DFA37_minS =
        "\1\0\1\50\1\55\4\50\1\40\1\55\1\60\2\11\1\45\2\50\1\41\1\55\6\uffff"+
        "\1\52\13\uffff\1\50\2\75\1\uffff\1\50\1\uffff\6\50\1\40\3\uffff"+
        "\1\50\1\uffff\2\50\1\150\1\155\1\145\1\141\1\uffff\1\45\4\uffff"+
        "\1\60\1\45\3\uffff\1\50\32\uffff\7\50\1\141\1\160\1\144\1\147\5"+
        "\50\1\11\3\50\1\162\1\157\1\151\1\145\5\50\1\uffff\3\50\1\163\1"+
        "\162\1\141\1\55\10\50\1\145\1\164\1\55\1\uffff\10\50\1\164\1\11"+
        "\1\uffff\5\50\1\11\1\162\1\uffff\2\50\1\uffff\1\154\1\50\1\uffff"+
        "\2\50\2\uffff";
    static final String DFA37_maxS =
        "\1\uffff\16\ufffd\1\41\1\55\6\uffff\1\57\13\uffff\1\ufffd\2\75\1"+
        "\uffff\1\ufffd\1\uffff\7\ufffd\3\uffff\1\ufffd\1\uffff\2\ufffd\1"+
        "\150\1\155\1\145\1\141\1\uffff\1\ufffd\4\uffff\1\71\1\ufffd\3\uffff"+
        "\1\ufffd\32\uffff\7\ufffd\1\141\1\160\1\144\1\147\11\ufffd\1\162"+
        "\1\157\1\151\1\145\5\ufffd\1\uffff\3\ufffd\1\163\1\162\1\141\11"+
        "\ufffd\1\145\1\164\1\ufffd\1\uffff\10\ufffd\1\164\1\165\1\uffff"+
        "\5\ufffd\1\47\1\162\1\uffff\2\ufffd\1\uffff\1\154\1\ufffd\1\uffff"+
        "\1\50\1\ufffd\2\uffff";
    static final String DFA37_acceptS =
        "\21\uffff\1\23\1\24\1\25\1\26\1\27\1\30\1\uffff\1\32\1\34\1\35\1"+
        "\40\1\41\1\42\1\43\1\44\1\46\1\47\1\50\3\uffff\1\57\1\uffff\1\3"+
        "\7\uffff\1\54\1\2\1\13\1\uffff\1\20\6\uffff\1\10\1\uffff\1\11\1"+
        "\37\1\12\1\36\2\uffff\1\14\1\15\1\16\1\uffff\1\21\1\33\1\22\1\45"+
        "\1\23\1\24\1\25\1\26\1\27\1\30\1\51\1\52\1\31\1\32\1\34\1\35\1\40"+
        "\1\41\1\42\1\43\1\44\1\46\1\47\1\50\1\55\1\56\35\uffff\1\17\22\uffff"+
        "\1\7\12\uffff\1\6\7\uffff\1\5\2\uffff\1\4\2\uffff\1\1\2\uffff\2"+
        "\53";
    static final String DFA37_specialS =
        "\1\0\u00ae\uffff}>";
    static final String[] DFA37_transitionS = {
            "\11\46\2\42\1\46\2\42\22\46\1\42\1\37\1\12\1\2\1\46\1\25\1\46"+
            "\1\13\1\33\1\34\1\41\1\40\1\23\1\20\1\11\1\27\12\14\1\22\1\21"+
            "\1\17\1\26\1\30\1\24\1\10\24\16\1\4\5\16\1\35\1\7\1\36\1\46"+
            "\1\5\1\46\4\43\1\15\3\43\1\1\13\43\1\3\5\43\1\31\1\45\1\32\1"+
            "\44\1\46\ud780\6\u0800\46\u1ffe\6\2\46",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\14\51\1\47\15\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\62\2\uffff\12\62\7\uffff\32\62\1\uffff\1\62\2\uffff\1\62"+
            "\1\uffff\32\62\5\uffff\ud780\62\u0800\uffff\u1ffe\62",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\21\51\1\63\10\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\2\uffff\1\64\1\uffff\1\54\2\uffff\12\53\7\uffff\32\52"+
            "\1\uffff\1\57\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\20\66\12\65\7\66\6\65\32\66\6\65\30\66\1\uffff\ud780\66\u0800"+
            "\uffff\u1ffe\66",
            "\1\73\23\uffff\32\73\1\uffff\1\73\2\uffff\1\73\1\uffff\2\73"+
            "\1\67\5\73\1\70\3\73\1\71\2\73\1\72\12\73\5\uffff\ud780\73\u0800"+
            "\uffff\u1ffe\73",
            "\12\74\7\uffff\32\75\1\uffff\1\75\2\uffff\1\75\1\uffff\32\75"+
            "\5\uffff\ud780\75\u0800\uffff\u1ffe\75",
            "\1\77\26\uffff\137\77\1\uffff\ud780\77\u0800\uffff\u1ffe\77",
            "\1\77\26\uffff\137\77\1\uffff\ud780\77\u0800\uffff\u1ffe\77",
            "\1\104\10\uffff\1\101\1\uffff\12\102\7\uffff\32\105\1\uffff"+
            "\1\105\2\uffff\1\105\1\uffff\32\105\5\uffff\ud780\105\u0800"+
            "\uffff\u1ffe\105",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\27\51\1\106\2\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\107",
            "\1\111",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\121\4\uffff\1\122",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\137",
            "\1\140",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\17\51\1\141\12\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\20\143\12\142\7\143\6\142\32\143\6\142\30\143\1\uffff\ud780"+
            "\143\u0800\uffff\u1ffe\143",
            "",
            "",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\13\51\1\144\16\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\147\7\uffff\6\146\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\145\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "",
            "\1\104\12\uffff\12\74\7\uffff\32\105\1\uffff\1\105\2\uffff"+
            "\1\105\1\uffff\32\105\5\uffff\ud780\105\u0800\uffff\u1ffe\105",
            "",
            "",
            "",
            "",
            "\12\74",
            "\1\104\10\uffff\1\101\1\uffff\12\102\7\uffff\32\105\1\uffff"+
            "\1\105\2\uffff\1\105\1\uffff\32\105\5\uffff\ud780\105\u0800"+
            "\uffff\u1ffe\105",
            "",
            "",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\17\51\1\154\12\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\16\51\1\155\13\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\160\7\uffff\6\157\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\156\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\161\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\60\4\uffff\1\54\2\uffff\12\164\7\uffff\6\163\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\162\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\164\7\uffff\6\163\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\162\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\164\7\uffff\6\163\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\162\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\170",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\21\51\1\171\10\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\21\51\1\172\10\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\175\7\uffff\6\174\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\173\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\175\7\uffff\6\174\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\173\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\175\7\uffff\6\174\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\173\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\2\176\1\uffff\2\176\22\uffff\10\176\1\uffff\126\176\1\uffff"+
            "\ud780\176\u0800\uffff\u1ffe\176",
            "\1\60\4\uffff\1\54\2\uffff\12\u0081\7\uffff\6\u0080\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\177\24\51\5\uffff\ud780\56"+
            "\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0081\7\uffff\6\u0080\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\177\24\51\5\uffff\ud780\56"+
            "\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0081\7\uffff\6\u0080\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\177\24\51\5\uffff\ud780\56"+
            "\u0800\uffff\u1ffe\56",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\4\51\1\u0086\25\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\23\51\1\u0087\6\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u008a\7\uffff\6\u0089\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u0088\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u008a\7\uffff\6\u0089\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u0088\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u008a\7\uffff\6\u0089\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u0088\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\u008d\7\uffff\6\u008c\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u008b\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u008d\7\uffff\6\u008c\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u008b\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u008d\7\uffff\6\u008c\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u008b\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "\1\73\2\uffff\12\73\7\uffff\32\73\1\uffff\1\73\2\uffff\1\73"+
            "\1\uffff\32\73\5\uffff\ud780\73\u0800\uffff\u1ffe\73",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\22\51\1\u0092\7\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\1\u0093\31\51\5\uffff\ud780\56\u0800\uffff"+
            "\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0096\7\uffff\6\u0095\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u0094\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0096\7\uffff\6\u0095\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u0094\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0096\7\uffff\6\u0095\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u0094\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0099\7\uffff\6\u0098\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u0097\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0099\7\uffff\6\u0098\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u0097\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0099\7\uffff\6\u0098\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u0097\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\u009a",
            "\1\u009b",
            "\1\73\2\uffff\12\73\7\uffff\32\73\1\uffff\1\73\2\uffff\1\73"+
            "\1\uffff\32\73\5\uffff\ud780\73\u0800\uffff\u1ffe\73",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\22\51\1\u009d\7\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\15\51\1\u009e\14\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u00a1\7\uffff\6\u00a0\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u009f\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u00a1\7\uffff\6\u00a0\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u009f\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u00a1\7\uffff\6\u00a0\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u009f\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\u00a2",
            "\2\u00a4\1\uffff\2\u00a4\22\uffff\1\u00a4\1\uffff\1\u00a4\4"+
            "\uffff\1\u00a4\115\uffff\1\u00a3",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\10\51\1\u00a5\21\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\23\51\1\u00a6\6\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\2\u00a7\1\uffff\2\u00a7\22\uffff\1\u00a7\1\uffff\1\u00a7\4"+
            "\uffff\1\u00a7",
            "\1\u00a8",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\16\51\1\u00a9\13\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "",
            "\1\u00ab",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\15\51\1\u00ac\14\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "",
            "\1\u00a4",
            "\1\u00ad\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1"+
            "\57\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff"+
            "\u1ffe\56",
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
            return "1:1: Tokens : ( T__89 | T__90 | IDENT | CHARSET | IMPORT | MEDIA | PAGE | ATKEYWORD | CLASSKEYWORD | STRING | HASH | NUMBER | PERCENTAGE | DIMENSION | URI | UNIRANGE | CDO | CDC | SEMICOLON | COLON | COMMA | QUESTION | PERCENT | EQUALS | SLASH | GREATER | LESS | LCURLY | RCURLY | APOS | QUOT | LPAREN | RPAREN | LBRACE | RBRACE | EXCLAMATION | MINUS | PLUS | ASTERISK | S | COMMENT | SL_COMMENT | EXPRESSION | FUNCTION | INCLUDES | DASHMATCH | INVALID_TOKEN );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA37_0 = input.LA(1);

                        s = -1;
                        if ( (LA37_0=='i') ) {s = 1;}

                        else if ( (LA37_0=='#') ) {s = 2;}

                        else if ( (LA37_0=='u') ) {s = 3;}

                        else if ( (LA37_0=='U') ) {s = 4;}

                        else if ( (LA37_0=='_') ) {s = 5;}

                        else if ( ((LA37_0>='\u0080' && LA37_0<='\uD7FF')||(LA37_0>='\uE000' && LA37_0<='\uFFFD')) ) {s = 6;}

                        else if ( (LA37_0=='\\') ) {s = 7;}

                        else if ( (LA37_0=='@') ) {s = 8;}

                        else if ( (LA37_0=='.') ) {s = 9;}

                        else if ( (LA37_0=='\"') ) {s = 10;}

                        else if ( (LA37_0=='\'') ) {s = 11;}

                        else if ( ((LA37_0>='0' && LA37_0<='9')) ) {s = 12;}

                        else if ( (LA37_0=='e') ) {s = 13;}

                        else if ( ((LA37_0>='A' && LA37_0<='T')||(LA37_0>='V' && LA37_0<='Z')) ) {s = 14;}

                        else if ( (LA37_0=='<') ) {s = 15;}

                        else if ( (LA37_0=='-') ) {s = 16;}

                        else if ( (LA37_0==';') ) {s = 17;}

                        else if ( (LA37_0==':') ) {s = 18;}

                        else if ( (LA37_0==',') ) {s = 19;}

                        else if ( (LA37_0=='?') ) {s = 20;}

                        else if ( (LA37_0=='%') ) {s = 21;}

                        else if ( (LA37_0=='=') ) {s = 22;}

                        else if ( (LA37_0=='/') ) {s = 23;}

                        else if ( (LA37_0=='>') ) {s = 24;}

                        else if ( (LA37_0=='{') ) {s = 25;}

                        else if ( (LA37_0=='}') ) {s = 26;}

                        else if ( (LA37_0=='(') ) {s = 27;}

                        else if ( (LA37_0==')') ) {s = 28;}

                        else if ( (LA37_0=='[') ) {s = 29;}

                        else if ( (LA37_0==']') ) {s = 30;}

                        else if ( (LA37_0=='!') ) {s = 31;}

                        else if ( (LA37_0=='+') ) {s = 32;}

                        else if ( (LA37_0=='*') ) {s = 33;}

                        else if ( ((LA37_0>='\t' && LA37_0<='\n')||(LA37_0>='\f' && LA37_0<='\r')||LA37_0==' ') ) {s = 34;}

                        else if ( ((LA37_0>='a' && LA37_0<='d')||(LA37_0>='f' && LA37_0<='h')||(LA37_0>='j' && LA37_0<='t')||(LA37_0>='v' && LA37_0<='z')) ) {s = 35;}

                        else if ( (LA37_0=='~') ) {s = 36;}

                        else if ( (LA37_0=='|') ) {s = 37;}

                        else if ( ((LA37_0>='\u0000' && LA37_0<='\b')||LA37_0=='\u000B'||(LA37_0>='\u000E' && LA37_0<='\u001F')||LA37_0=='$'||LA37_0=='&'||LA37_0=='^'||LA37_0=='`'||LA37_0=='\u007F'||(LA37_0>='\uD800' && LA37_0<='\uDFFF')||(LA37_0>='\uFFFE' && LA37_0<='\uFFFF')) ) {s = 38;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 37, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}