// $ANTLR 3.1.2 /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g 2013-02-02 18:07:36

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
    public static final int FUNCTION=49;
    public static final int APOS=83;
    public static final int CLASSKEYWORD=51;
    public static final int CONTAINS=73;
    public static final int INVALID_STATEMENT=27;
    public static final int INVALID_TOKEN=76;
    public static final int EQUALS=63;
    public static final int MEDIA=38;
    public static final int NL_CHAR=93;
    public static final int EOF=-1;
    public static final int NON_ASCII=90;
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
    public static final int LESS=60;
    public static final int INVALID_DECLARATION=26;
    public static final int ELEMENT=12;
    public static final int DIMENSION=54;
    public static final int COMMENT=86;
    public static final int T__96=96;
    public static final int T__95=95;
    public static final int CHILD=15;
    public static final int INVALID_STRING=23;
    public static final int RBRACE=70;
    public static final int RULE=10;
    public static final int PARENBLOCK=8;
    public static final int NUMBER=52;
    public static final int URI_CHAR=92;
    public static final int LCURLY=36;
    public static final int SEMICOLON=44;
    public static final int FONTFACE=35;
    public static final int S=30;
    public static final int VALUE=20;
    public static final int CDO=31;
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
    public static final int STARTSWITH=71;
    public static final int RCURLY=37;
    public static final int STRING=74;
    public static final int URI_MACR=82;

        

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

    // $ANTLR start "T__94"
    public final void mT__94() throws RecognitionException {
        try {
            int _type = T__94;
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
    // $ANTLR end "T__94"

    // $ANTLR start "T__95"
    public final void mT__95() throws RecognitionException {
        try {
            int _type = T__95;
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
    // $ANTLR end "T__95"

    // $ANTLR start "T__96"
    public final void mT__96() throws RecognitionException {
        try {
            int _type = T__96;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:482:7: ( '^' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:482:9: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__96"

    // $ANTLR start "IDENT"
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1072:2: ( IDENT_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1072:4: IDENT_MACR
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1083:2: ( '@charset' ( S )* s= STRING_MACR ( S )* SEMICOLON )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1083:4: '@charset' ( S )* s= STRING_MACR ( S )* SEMICOLON
            {
            match("@charset"); 

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1083:15: ( S )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\t' && LA1_0<='\n')||(LA1_0>='\f' && LA1_0<='\r')||LA1_0==' ') ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1083:15: S
            	    {
            	    mS(); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            int sStart85 = getCharIndex();
            mSTRING_MACR(); 
            s = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, sStart85, getCharIndex()-1);
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1083:32: ( S )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='\t' && LA2_0<='\n')||(LA2_0>='\f' && LA2_0<='\r')||LA2_0==' ') ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1083:32: S
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1117:2: ( '@import' ( S )* (s= STRING_MACR | s= URI ) ( S )* (m= IDENT_MACR ( S )* ( ',' ( S )* m= IDENT_MACR ( S )* )* )? SEMICOLON )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1117:4: '@import' ( S )* (s= STRING_MACR | s= URI ) ( S )* (m= IDENT_MACR ( S )* ( ',' ( S )* m= IDENT_MACR ( S )* )* )? SEMICOLON
            {
            match("@import"); 

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1117:14: ( S )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='\t' && LA3_0<='\n')||(LA3_0>='\f' && LA3_0<='\r')||LA3_0==' ') ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1117:14: S
            	    {
            	    mS(); 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1118:4: (s= STRING_MACR | s= URI )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1118:5: s= STRING_MACR
                    {
                    int sStart130 = getCharIndex();
                    mSTRING_MACR(); 
                    s = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, sStart130, getCharIndex()-1);
                     s.setType(STRING);

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1119:7: s= URI
                    {
                    int sStart143 = getCharIndex();
                    mURI(); 
                    s = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, sStart143, getCharIndex()-1);
                    s.setType(URI);

                    }
                    break;

            }

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1119:33: ( S )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\t' && LA5_0<='\n')||(LA5_0>='\f' && LA5_0<='\r')||LA5_0==' ') ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1119:33: S
            	    {
            	    mS(); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1120:6: (m= IDENT_MACR ( S )* ( ',' ( S )* m= IDENT_MACR ( S )* )* )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0>='A' && LA10_0<='Z')||LA10_0=='\\'||LA10_0=='_'||(LA10_0>='a' && LA10_0<='z')||(LA10_0>='\u0080' && LA10_0<='\uD7FF')||(LA10_0>='\uE000' && LA10_0<='\uFFFD')) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1120:7: m= IDENT_MACR ( S )* ( ',' ( S )* m= IDENT_MACR ( S )* )*
                    {
                    int mStart159 = getCharIndex();
                    mIDENT_MACR(); 
                    m = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, mStart159, getCharIndex()-1);
                     
                    	        mText = m.getText();
                    	    	if(css.isSupportedMedia(mText)) 
                    	    		media.append(mText); 
                    	    	else
                    	    	    log.debug("Invalid import media \"{}\"", mText);
                    	     
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1127:7: ( S )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='\t' && LA6_0<='\n')||(LA6_0>='\f' && LA6_0<='\r')||LA6_0==' ') ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1127:7: S
                    	    {
                    	    mS(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1128:9: ( ',' ( S )* m= IDENT_MACR ( S )* )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==',') ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1128:10: ',' ( S )* m= IDENT_MACR ( S )*
                    	    {
                    	    match(','); 
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1128:14: ( S )*
                    	    loop7:
                    	    do {
                    	        int alt7=2;
                    	        int LA7_0 = input.LA(1);

                    	        if ( ((LA7_0>='\t' && LA7_0<='\n')||(LA7_0>='\f' && LA7_0<='\r')||LA7_0==' ') ) {
                    	            alt7=1;
                    	        }


                    	        switch (alt7) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1128:14: S
                    	    	    {
                    	    	    mS(); 

                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop7;
                    	        }
                    	    } while (true);

                    	    int mStart190 = getCharIndex();
                    	    mIDENT_MACR(); 
                    	    m = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, mStart190, getCharIndex()-1);
                    	     
                    	    	         mText = m.getText();
                    	    	       	 if(css.isSupportedMedia(mText)) 
                    	    	       	 		media.append(",").append(mText);
                    	    	       	 else
                    	    	    	    log.debug("Invalid import media \"{}\"", mText);		
                    	    	       	
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1135:9: ( S )*
                    	    loop8:
                    	    do {
                    	        int alt8=2;
                    	        int LA8_0 = input.LA(1);

                    	        if ( ((LA8_0>='\t' && LA8_0<='\n')||(LA8_0>='\f' && LA8_0<='\r')||LA8_0==' ') ) {
                    	            alt8=1;
                    	        }


                    	        switch (alt8) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1135:9: S
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1200:2: ( '@media' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1200:4: '@media'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1204:2: ( '@page' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1204:4: '@page'
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

    // $ANTLR start "MARGIN_AREA"
    public final void mMARGIN_AREA() throws RecognitionException {
        try {
            int _type = MARGIN_AREA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1208:3: ( '@top-left-corner' | '@top-left' | '@top-center' | '@top-right' | '@top-right-corner' | '@bottom-left-corner' | '@bottom-left' | '@bottom-center' | '@bottom-right' | '@bottom-right-corner' | '@left-top' | '@left-middle' | '@left-bottom' | '@right-top' | '@right-middle' | '@right-bottom' )
            int alt11=16;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1208:5: '@top-left-corner'
                    {
                    match("@top-left-corner"); 


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1209:5: '@top-left'
                    {
                    match("@top-left"); 


                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1210:5: '@top-center'
                    {
                    match("@top-center"); 


                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1211:5: '@top-right'
                    {
                    match("@top-right"); 


                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1212:5: '@top-right-corner'
                    {
                    match("@top-right-corner"); 


                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1213:5: '@bottom-left-corner'
                    {
                    match("@bottom-left-corner"); 


                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1214:5: '@bottom-left'
                    {
                    match("@bottom-left"); 


                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1215:5: '@bottom-center'
                    {
                    match("@bottom-center"); 


                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1216:5: '@bottom-right'
                    {
                    match("@bottom-right"); 


                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1217:5: '@bottom-right-corner'
                    {
                    match("@bottom-right-corner"); 


                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1218:5: '@left-top'
                    {
                    match("@left-top"); 


                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1219:5: '@left-middle'
                    {
                    match("@left-middle"); 


                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1220:5: '@left-bottom'
                    {
                    match("@left-bottom"); 


                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1221:5: '@right-top'
                    {
                    match("@right-top"); 


                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1222:5: '@right-middle'
                    {
                    match("@right-middle"); 


                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1223:5: '@right-bottom'
                    {
                    match("@right-bottom"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MARGIN_AREA"

    // $ANTLR start "FONTFACE"
    public final void mFONTFACE() throws RecognitionException {
        try {
            int _type = FONTFACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1227:3: ( '@font-face' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1227:5: '@font-face'
            {
            match("@font-face"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FONTFACE"

    // $ANTLR start "ATKEYWORD"
    public final void mATKEYWORD() throws RecognitionException {
        try {
            int _type = ATKEYWORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1232:2: ( '@' ( MINUS )? IDENT_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1232:4: '@' ( MINUS )? IDENT_MACR
            {
            match('@'); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1232:8: ( MINUS )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='-') ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1232:8: MINUS
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1236:5: ( '.' IDENT_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1236:7: '.' IDENT_MACR
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1247:2: ( STRING_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1247:4: STRING_MACR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1252:2: ( '#' NAME_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1252:4: '#' NAME_MACR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1257:2: ( NUMBER_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1257:4: NUMBER_MACR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1262:2: ( NUMBER_MACR '%' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1262:4: NUMBER_MACR '%'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1267:2: ( NUMBER_MACR IDENT_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1267:4: NUMBER_MACR IDENT_MACR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1272:2: ( 'url(' W_MACR ( STRING_MACR | URI_MACR ) W_MACR ')' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1272:4: 'url(' W_MACR ( STRING_MACR | URI_MACR ) W_MACR ')'
            {
            match("url("); 

            mW_MACR(); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1272:18: ( STRING_MACR | URI_MACR )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='\"'||LA13_0=='\'') ) {
                alt13=1;
            }
            else if ( ((LA13_0>='\t' && LA13_0<='\n')||(LA13_0>='\f' && LA13_0<='\r')||(LA13_0>=' ' && LA13_0<='!')||(LA13_0>='#' && LA13_0<='&')||(LA13_0>=')' && LA13_0<='~')||(LA13_0>='\u0080' && LA13_0<='\uD7FF')||(LA13_0>='\uE000' && LA13_0<='\uFFFD')) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1272:19: STRING_MACR
                    {
                    mSTRING_MACR(); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1272:33: URI_MACR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1276:9: ( 'U+' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) )? ( '-' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )? )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1277:2: 'U+' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) )? ( '-' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )?
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1281:7: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>='0' && LA14_0<='9')||LA14_0=='?'||(LA14_0>='A' && LA14_0<='F')||(LA14_0>='a' && LA14_0<='f')) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1281:8: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' )
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1282:2: ( '-' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='-') ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1282:3: '-' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )?
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

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1287:14: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( ((LA15_0>='0' && LA15_0<='9')||(LA15_0>='A' && LA15_0<='F')||(LA15_0>='a' && LA15_0<='f')) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1287:15: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1293:2: ( '<!--' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1293:4: '<!--'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1298:2: ( '-->' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1298:4: '-->'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1302:2: ( ';' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1302:4: ';'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1306:2: ( ':' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1306:4: ':'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1310:5: ( ',' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1310:7: ','
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1314:2: ( '?' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1314:4: '?'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1318:2: ( '%' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1318:4: '%'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1322:5: ( '=' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1322:7: '='
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1326:5: ( '/' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1326:7: '/'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1330:5: ( '>' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1330:7: '>'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1334:5: ( '<' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1334:7: '<'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1338:2: ( '{' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1338:4: '{'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1342:2: ( '}' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1342:4: '}'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1346:2: ( '\\'' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1346:4: '\\''
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1350:2: ( '\"' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1350:4: '\"'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1354:2: ( '(' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1354:4: '('
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1358:2: ( ')' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1358:4: ')'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1362:2: ( '[' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1362:4: '['
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1366:2: ( ']' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1366:4: ']'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1370:5: ( '!' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1370:7: '!'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1374:2: ( '-' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1374:4: '-'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1378:2: ( '+' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1378:4: '+'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1382:2: ( '*' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1382:4: '*'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1387:2: ( ( W_CHAR )+ )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1387:4: ( W_CHAR )+
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1387:4: ( W_CHAR )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>='\t' && LA17_0<='\n')||(LA17_0>='\f' && LA17_0<='\r')||LA17_0==' ') ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1387:4: W_CHAR
            	    {
            	    mW_CHAR(); 

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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1391:2: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1391:4: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1391:9: ( options {greedy=false; } : . )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0=='*') ) {
                    int LA18_1 = input.LA(2);

                    if ( (LA18_1=='/') ) {
                        alt18=2;
                    }
                    else if ( ((LA18_1>='\u0000' && LA18_1<='.')||(LA18_1>='0' && LA18_1<='\uFFFF')) ) {
                        alt18=1;
                    }


                }
                else if ( ((LA18_0>='\u0000' && LA18_0<=')')||(LA18_0>='+' && LA18_0<='\uFFFF')) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1391:37: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop18;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1395:2: ( '//' ( options {greedy=false; } : . )* ( '\\n' | '\\r' ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1395:4: '//' ( options {greedy=false; } : . )* ( '\\n' | '\\r' )
            {
            match("//"); 

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1395:9: ( options {greedy=false; } : . )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0=='\n'||LA19_0=='\r') ) {
                    alt19=2;
                }
                else if ( ((LA19_0>='\u0000' && LA19_0<='\t')||(LA19_0>='\u000B' && LA19_0<='\f')||(LA19_0>='\u000E' && LA19_0<='\uFFFF')) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1395:37: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop19;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1400:3: ( 'expression(' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1400:5: 'expression('
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1405:2: ( IDENT_MACR '(' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1405:4: IDENT_MACR '('
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1409:2: ( '~=' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1409:4: '~='
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1413:2: ( '|=' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1413:4: '|='
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

    // $ANTLR start "STARTSWITH"
    public final void mSTARTSWITH() throws RecognitionException {
        try {
            int _type = STARTSWITH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1417:3: ( '^=' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1417:5: '^='
            {
            match("^="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STARTSWITH"

    // $ANTLR start "ENDSWITH"
    public final void mENDSWITH() throws RecognitionException {
        try {
            int _type = ENDSWITH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1421:3: ( '$=' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1421:5: '$='
            {
            match("$="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ENDSWITH"

    // $ANTLR start "CONTAINS"
    public final void mCONTAINS() throws RecognitionException {
        try {
            int _type = CONTAINS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1425:3: ( '*=' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1425:5: '*='
            {
            match("*="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONTAINS"

    // $ANTLR start "INVALID_TOKEN"
    public final void mINVALID_TOKEN() throws RecognitionException {
        try {
            int _type = INVALID_TOKEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1429:2: ( . )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1429:4: .
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1438:4: ( NAME_START ( NAME_CHAR )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1438:6: NAME_START ( NAME_CHAR )*
            {
            mNAME_START(); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1438:17: ( NAME_CHAR )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0=='-'||(LA20_0>='0' && LA20_0<='9')||(LA20_0>='A' && LA20_0<='Z')||LA20_0=='\\'||LA20_0=='_'||(LA20_0>='a' && LA20_0<='z')||(LA20_0>='\u0080' && LA20_0<='\uD7FF')||(LA20_0>='\uE000' && LA20_0<='\uFFFD')) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1438:17: NAME_CHAR
            	    {
            	    mNAME_CHAR(); 

            	    }
            	    break;

            	default :
            	    break loop20;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1443:3: ( ( NAME_CHAR )+ )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1443:5: ( NAME_CHAR )+
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1443:5: ( NAME_CHAR )+
            int cnt21=0;
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0=='-'||(LA21_0>='0' && LA21_0<='9')||(LA21_0>='A' && LA21_0<='Z')||LA21_0=='\\'||LA21_0=='_'||(LA21_0>='a' && LA21_0<='z')||(LA21_0>='\u0080' && LA21_0<='\uD7FF')||(LA21_0>='\uE000' && LA21_0<='\uFFFD')) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1443:5: NAME_CHAR
            	    {
            	    mNAME_CHAR(); 

            	    }
            	    break;

            	default :
            	    if ( cnt21 >= 1 ) break loop21;
                        EarlyExitException eee =
                            new EarlyExitException(21, input);
                        throw eee;
                }
                cnt21++;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1448:4: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' | NON_ASCII | ESCAPE_CHAR ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1448:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | NON_ASCII | ESCAPE_CHAR )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1448:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | NON_ASCII | ESCAPE_CHAR )
            int alt22=5;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0>='a' && LA22_0<='z')) ) {
                alt22=1;
            }
            else if ( ((LA22_0>='A' && LA22_0<='Z')) ) {
                alt22=2;
            }
            else if ( (LA22_0=='_') ) {
                alt22=3;
            }
            else if ( ((LA22_0>='\u0080' && LA22_0<='\uD7FF')||(LA22_0>='\uE000' && LA22_0<='\uFFFD')) ) {
                alt22=4;
            }
            else if ( (LA22_0=='\\') ) {
                alt22=5;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1448:7: 'a' .. 'z'
                    {
                    matchRange('a','z'); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1448:18: 'A' .. 'Z'
                    {
                    matchRange('A','Z'); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1448:29: '_'
                    {
                    match('_'); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1448:35: NON_ASCII
                    {
                    mNON_ASCII(); 

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1448:47: ESCAPE_CHAR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1453:4: ( ( '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1453:6: ( '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1458:3: ( ( '\\\\' ) ( ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? ) | ( '\\u0020' .. '\\u007E' | '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' ) ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1458:5: ( '\\\\' ) ( ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? ) | ( '\\u0020' .. '\\u007E' | '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' ) )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1458:5: ( '\\\\' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1458:6: '\\\\'
            {
            match('\\'); 

            }

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1459:5: ( ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? ) | ( '\\u0020' .. '\\u007E' | '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' ) )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( ((LA24_0>='0' && LA24_0<='9')||(LA24_0>='A' && LA24_0<='F')||(LA24_0>='a' && LA24_0<='f')) ) {
                int LA24_1 = input.LA(2);

                if ( ((LA24_1>='0' && LA24_1<='9')||(LA24_1>='A' && LA24_1<='F')||(LA24_1>='a' && LA24_1<='f')) ) {
                    alt24=1;
                }
                else {
                    alt24=2;}
            }
            else if ( ((LA24_0>=' ' && LA24_0<='/')||(LA24_0>=':' && LA24_0<='@')||(LA24_0>='G' && LA24_0<='`')||(LA24_0>='g' && LA24_0<='~')||(LA24_0>='\u0080' && LA24_0<='\uD7FF')||(LA24_0>='\uE000' && LA24_0<='\uFFFD')) ) {
                alt24=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1460:7: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1460:7: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1460:8: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )?
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

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1464:8: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( ((LA23_0>='0' && LA23_0<='9')||(LA23_0>='A' && LA23_0<='F')||(LA23_0>='a' && LA23_0<='f')) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1464:9: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1467:7: ( '\\u0020' .. '\\u007E' | '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1473:4: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | NON_ASCII | ESCAPE_CHAR ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1473:6: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | NON_ASCII | ESCAPE_CHAR )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1473:6: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | NON_ASCII | ESCAPE_CHAR )
            int alt25=7;
            int LA25_0 = input.LA(1);

            if ( ((LA25_0>='a' && LA25_0<='z')) ) {
                alt25=1;
            }
            else if ( ((LA25_0>='A' && LA25_0<='Z')) ) {
                alt25=2;
            }
            else if ( ((LA25_0>='0' && LA25_0<='9')) ) {
                alt25=3;
            }
            else if ( (LA25_0=='-') ) {
                alt25=4;
            }
            else if ( (LA25_0=='_') ) {
                alt25=5;
            }
            else if ( ((LA25_0>='\u0080' && LA25_0<='\uD7FF')||(LA25_0>='\uE000' && LA25_0<='\uFFFD')) ) {
                alt25=6;
            }
            else if ( (LA25_0=='\\') ) {
                alt25=7;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1473:7: 'a' .. 'z'
                    {
                    matchRange('a','z'); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1473:18: 'A' .. 'Z'
                    {
                    matchRange('A','Z'); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1473:29: '0' .. '9'
                    {
                    matchRange('0','9'); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1473:40: '-'
                    {
                    match('-'); 

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1473:46: '_'
                    {
                    match('_'); 

                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1473:52: NON_ASCII
                    {
                    mNON_ASCII(); 

                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1473:64: ESCAPE_CHAR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1478:4: ( ( '0' .. '9' )+ | ( ( '0' .. '9' )* '.' ( '0' .. '9' )+ ) )
            int alt29=2;
            alt29 = dfa29.predict(input);
            switch (alt29) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1478:6: ( '0' .. '9' )+
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1478:6: ( '0' .. '9' )+
                    int cnt26=0;
                    loop26:
                    do {
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( ((LA26_0>='0' && LA26_0<='9')) ) {
                            alt26=1;
                        }


                        switch (alt26) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1478:7: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt26 >= 1 ) break loop26;
                                EarlyExitException eee =
                                    new EarlyExitException(26, input);
                                throw eee;
                        }
                        cnt26++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1478:20: ( ( '0' .. '9' )* '.' ( '0' .. '9' )+ )
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1478:20: ( ( '0' .. '9' )* '.' ( '0' .. '9' )+ )
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1478:21: ( '0' .. '9' )* '.' ( '0' .. '9' )+
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1478:21: ( '0' .. '9' )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( ((LA27_0>='0' && LA27_0<='9')) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1478:22: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);

                    match('.'); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1478:37: ( '0' .. '9' )+
                    int cnt28=0;
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( ((LA28_0>='0' && LA28_0<='9')) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1478:38: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt28 >= 1 ) break loop28;
                                EarlyExitException eee =
                                    new EarlyExitException(28, input);
                                throw eee;
                        }
                        cnt28++;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1483:2: ( QUOT ( STRING_CHAR | APOS )* QUOT | APOS ( STRING_CHAR | QUOT )* APOS )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0=='\"') ) {
                alt32=1;
            }
            else if ( (LA32_0=='\'') ) {
                alt32=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1483:4: QUOT ( STRING_CHAR | APOS )* QUOT
                    {
                    mQUOT(); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1483:9: ( STRING_CHAR | APOS )*
                    loop30:
                    do {
                        int alt30=3;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0=='\t'||(LA30_0>=' ' && LA30_0<='!')||(LA30_0>='#' && LA30_0<='&')||(LA30_0>='(' && LA30_0<='~')||(LA30_0>='\u0080' && LA30_0<='\uD7FF')||(LA30_0>='\uE000' && LA30_0<='\uFFFD')) ) {
                            alt30=1;
                        }
                        else if ( (LA30_0=='\'') ) {
                            alt30=2;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1483:10: STRING_CHAR
                    	    {
                    	    mSTRING_CHAR(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1483:24: APOS
                    	    {
                    	    mAPOS(); 
                    	    ls.aposOpen=false;

                    	    }
                    	    break;

                    	default :
                    	    break loop30;
                        }
                    } while (true);

                    mQUOT(); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1484:4: APOS ( STRING_CHAR | QUOT )* APOS
                    {
                    mAPOS(); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1484:9: ( STRING_CHAR | QUOT )*
                    loop31:
                    do {
                        int alt31=3;
                        int LA31_0 = input.LA(1);

                        if ( (LA31_0=='\t'||(LA31_0>=' ' && LA31_0<='!')||(LA31_0>='#' && LA31_0<='&')||(LA31_0>='(' && LA31_0<='~')||(LA31_0>='\u0080' && LA31_0<='\uD7FF')||(LA31_0>='\uE000' && LA31_0<='\uFFFD')) ) {
                            alt31=1;
                        }
                        else if ( (LA31_0=='\"') ) {
                            alt31=2;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1484:10: STRING_CHAR
                    	    {
                    	    mSTRING_CHAR(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1484:24: QUOT
                    	    {
                    	    mQUOT(); 
                    	    ls.quotOpen=false;

                    	    }
                    	    break;

                    	default :
                    	    break loop31;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1489:2: ( ( URI_CHAR | ' ' | '(' | ')' | ( '\\\\' NL_CHAR ) ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1489:5: ( URI_CHAR | ' ' | '(' | ')' | ( '\\\\' NL_CHAR ) )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1489:5: ( URI_CHAR | ' ' | '(' | ')' | ( '\\\\' NL_CHAR ) )
            int alt33=5;
            int LA33_0 = input.LA(1);

            if ( (LA33_0=='\\') ) {
                int LA33_1 = input.LA(2);

                if ( (LA33_1=='\n'||(LA33_1>='\f' && LA33_1<='\r')) ) {
                    alt33=5;
                }
                else {
                    alt33=1;}
            }
            else if ( (LA33_0=='\t'||LA33_0=='!'||(LA33_0>='#' && LA33_0<='&')||(LA33_0>='*' && LA33_0<='[')||(LA33_0>=']' && LA33_0<='~')||(LA33_0>='\u0080' && LA33_0<='\uD7FF')||(LA33_0>='\uE000' && LA33_0<='\uFFFD')) ) {
                alt33=1;
            }
            else if ( (LA33_0==' ') ) {
                alt33=2;
            }
            else if ( (LA33_0=='(') ) {
                alt33=3;
            }
            else if ( (LA33_0==')') ) {
                alt33=4;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1489:6: URI_CHAR
                    {
                    mURI_CHAR(); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1489:17: ' '
                    {
                    match(' '); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1489:23: '('
                    {
                    match('('); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1489:29: ')'
                    {
                    match(')'); 

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1489:35: ( '\\\\' NL_CHAR )
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1489:35: ( '\\\\' NL_CHAR )
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1489:36: '\\\\' NL_CHAR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1494:2: ( ( URI_CHAR )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1494:4: ( URI_CHAR )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1494:4: ( URI_CHAR )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0=='\t'||LA34_0=='!'||(LA34_0>='#' && LA34_0<='&')||(LA34_0>='*' && LA34_0<='~')||(LA34_0>='\u0080' && LA34_0<='\uD7FF')||(LA34_0>='\uE000' && LA34_0<='\uFFFD')) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1494:4: URI_CHAR
            	    {
            	    mURI_CHAR(); 

            	    }
            	    break;

            	default :
            	    break loop34;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1499:2: ( ( '\\u0009' | '\\u0021' | '\\u0023' .. '\\u0026' | '\\u002A' .. '\\u007E' ) | NON_ASCII | ESCAPE_CHAR )
            int alt35=3;
            int LA35_0 = input.LA(1);

            if ( (LA35_0=='\\') ) {
                int LA35_1 = input.LA(2);

                if ( ((LA35_1>=' ' && LA35_1<='~')||(LA35_1>='\u0080' && LA35_1<='\uD7FF')||(LA35_1>='\uE000' && LA35_1<='\uFFFD')) ) {
                    alt35=3;
                }
                else {
                    alt35=1;}
            }
            else if ( ((LA35_0>='\u0080' && LA35_0<='\uD7FF')||(LA35_0>='\uE000' && LA35_0<='\uFFFD')) ) {
                alt35=2;
            }
            else if ( (LA35_0=='\t'||LA35_0=='!'||(LA35_0>='#' && LA35_0<='&')||(LA35_0>='*' && LA35_0<='[')||(LA35_0>=']' && LA35_0<='~')) ) {
                alt35=1;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1499:4: ( '\\u0009' | '\\u0021' | '\\u0023' .. '\\u0026' | '\\u002A' .. '\\u007E' )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1500:6: NON_ASCII
                    {
                    mNON_ASCII(); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1500:18: ESCAPE_CHAR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1505:4: ( '\\u000A' | '\\u000D' '\\u000A' | '\\u000D' | '\\u000C' )
            int alt36=4;
            switch ( input.LA(1) ) {
            case '\n':
                {
                alt36=1;
                }
                break;
            case '\r':
                {
                int LA36_2 = input.LA(2);

                if ( (LA36_2=='\n') ) {
                    alt36=2;
                }
                else {
                    alt36=3;}
                }
                break;
            case '\f':
                {
                alt36=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }

            switch (alt36) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1505:6: '\\u000A'
                    {
                    match('\n'); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1505:17: '\\u000D' '\\u000A'
                    {
                    match('\r'); 
                    match('\n'); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1505:37: '\\u000D'
                    {
                    match('\r'); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1505:48: '\\u000C'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1510:2: ( ( W_CHAR )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1510:4: ( W_CHAR )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1510:4: ( W_CHAR )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( ((LA37_0>='\t' && LA37_0<='\n')||(LA37_0>='\f' && LA37_0<='\r')||LA37_0==' ') ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1510:4: W_CHAR
            	    {
            	    mW_CHAR(); 

            	    }
            	    break;

            	default :
            	    break loop37;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1515:4: ( '\\u0009' | '\\u000A' | '\\u000C' | '\\u000D' | '\\u0020' )
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
        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:8: ( T__94 | T__95 | T__96 | IDENT | CHARSET | IMPORT | MEDIA | PAGE | MARGIN_AREA | FONTFACE | ATKEYWORD | CLASSKEYWORD | STRING | HASH | NUMBER | PERCENTAGE | DIMENSION | URI | UNIRANGE | CDO | CDC | SEMICOLON | COLON | COMMA | QUESTION | PERCENT | EQUALS | SLASH | GREATER | LESS | LCURLY | RCURLY | APOS | QUOT | LPAREN | RPAREN | LBRACE | RBRACE | EXCLAMATION | MINUS | PLUS | ASTERISK | S | COMMENT | SL_COMMENT | EXPRESSION | FUNCTION | INCLUDES | DASHMATCH | STARTSWITH | ENDSWITH | CONTAINS | INVALID_TOKEN )
        int alt38=53;
        alt38 = dfa38.predict(input);
        switch (alt38) {
            case 1 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:10: T__94
                {
                mT__94(); 

                }
                break;
            case 2 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:16: T__95
                {
                mT__95(); 

                }
                break;
            case 3 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:22: T__96
                {
                mT__96(); 

                }
                break;
            case 4 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:28: IDENT
                {
                mIDENT(); 

                }
                break;
            case 5 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:34: CHARSET
                {
                mCHARSET(); 

                }
                break;
            case 6 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:42: IMPORT
                {
                mIMPORT(); 

                }
                break;
            case 7 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:49: MEDIA
                {
                mMEDIA(); 

                }
                break;
            case 8 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:55: PAGE
                {
                mPAGE(); 

                }
                break;
            case 9 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:60: MARGIN_AREA
                {
                mMARGIN_AREA(); 

                }
                break;
            case 10 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:72: FONTFACE
                {
                mFONTFACE(); 

                }
                break;
            case 11 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:81: ATKEYWORD
                {
                mATKEYWORD(); 

                }
                break;
            case 12 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:91: CLASSKEYWORD
                {
                mCLASSKEYWORD(); 

                }
                break;
            case 13 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:104: STRING
                {
                mSTRING(); 

                }
                break;
            case 14 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:111: HASH
                {
                mHASH(); 

                }
                break;
            case 15 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:116: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 16 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:123: PERCENTAGE
                {
                mPERCENTAGE(); 

                }
                break;
            case 17 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:134: DIMENSION
                {
                mDIMENSION(); 

                }
                break;
            case 18 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:144: URI
                {
                mURI(); 

                }
                break;
            case 19 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:148: UNIRANGE
                {
                mUNIRANGE(); 

                }
                break;
            case 20 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:157: CDO
                {
                mCDO(); 

                }
                break;
            case 21 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:161: CDC
                {
                mCDC(); 

                }
                break;
            case 22 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:165: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 23 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:175: COLON
                {
                mCOLON(); 

                }
                break;
            case 24 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:181: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 25 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:187: QUESTION
                {
                mQUESTION(); 

                }
                break;
            case 26 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:196: PERCENT
                {
                mPERCENT(); 

                }
                break;
            case 27 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:204: EQUALS
                {
                mEQUALS(); 

                }
                break;
            case 28 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:211: SLASH
                {
                mSLASH(); 

                }
                break;
            case 29 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:217: GREATER
                {
                mGREATER(); 

                }
                break;
            case 30 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:225: LESS
                {
                mLESS(); 

                }
                break;
            case 31 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:230: LCURLY
                {
                mLCURLY(); 

                }
                break;
            case 32 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:237: RCURLY
                {
                mRCURLY(); 

                }
                break;
            case 33 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:244: APOS
                {
                mAPOS(); 

                }
                break;
            case 34 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:249: QUOT
                {
                mQUOT(); 

                }
                break;
            case 35 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:254: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 36 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:261: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 37 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:268: LBRACE
                {
                mLBRACE(); 

                }
                break;
            case 38 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:275: RBRACE
                {
                mRBRACE(); 

                }
                break;
            case 39 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:282: EXCLAMATION
                {
                mEXCLAMATION(); 

                }
                break;
            case 40 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:294: MINUS
                {
                mMINUS(); 

                }
                break;
            case 41 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:300: PLUS
                {
                mPLUS(); 

                }
                break;
            case 42 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:305: ASTERISK
                {
                mASTERISK(); 

                }
                break;
            case 43 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:314: S
                {
                mS(); 

                }
                break;
            case 44 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:316: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 45 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:324: SL_COMMENT
                {
                mSL_COMMENT(); 

                }
                break;
            case 46 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:335: EXPRESSION
                {
                mEXPRESSION(); 

                }
                break;
            case 47 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:346: FUNCTION
                {
                mFUNCTION(); 

                }
                break;
            case 48 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:355: INCLUDES
                {
                mINCLUDES(); 

                }
                break;
            case 49 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:364: DASHMATCH
                {
                mDASHMATCH(); 

                }
                break;
            case 50 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:374: STARTSWITH
                {
                mSTARTSWITH(); 

                }
                break;
            case 51 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:385: ENDSWITH
                {
                mENDSWITH(); 

                }
                break;
            case 52 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:394: CONTAINS
                {
                mCONTAINS(); 

                }
                break;
            case 53 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:403: INVALID_TOKEN
                {
                mINVALID_TOKEN(); 

                }
                break;

        }

    }


    protected DFA11 dfa11 = new DFA11(this);
    protected DFA29 dfa29 = new DFA29(this);
    protected DFA38 dfa38 = new DFA38(this);
    static final String DFA11_eotS =
        "\45\uffff\1\53\6\uffff\1\60\6\uffff\1\66\3\uffff\1\71\2\uffff";
    static final String DFA11_eofS =
        "\72\uffff";
    static final String DFA11_minS =
        "\1\100\1\142\2\157\1\145\1\151\1\160\1\164\1\146\1\147\1\55\2\164"+
        "\1\150\1\143\1\157\1\55\1\164\1\145\1\uffff\1\151\1\155\1\142\1"+
        "\55\1\146\1\147\1\55\3\uffff\1\142\1\164\1\150\1\143\3\uffff\1\55"+
        "\1\164\1\145\1\uffff\1\151\2\uffff\1\55\1\146\1\147\2\uffff\1\164"+
        "\1\150\1\55\1\164\2\uffff\1\55\2\uffff";
    static final String DFA11_maxS =
        "\1\100\1\164\2\157\1\145\1\151\1\160\1\164\1\146\1\147\1\55\2\164"+
        "\1\150\1\162\1\157\1\55\1\164\1\145\1\uffff\1\151\1\155\1\164\1"+
        "\55\1\146\1\147\1\55\3\uffff\2\164\1\150\1\162\3\uffff\1\55\1\164"+
        "\1\145\1\uffff\1\151\2\uffff\1\55\1\146\1\147\2\uffff\1\164\1\150"+
        "\1\55\1\164\2\uffff\1\55\2\uffff";
    static final String DFA11_acceptS =
        "\23\uffff\1\3\7\uffff\1\13\1\14\1\15\4\uffff\1\16\1\17\1\20\3\uffff"+
        "\1\10\1\uffff\1\1\1\2\3\uffff\1\5\1\4\4\uffff\1\6\1\7\1\uffff\1"+
        "\12\1\11";
    static final String DFA11_specialS =
        "\72\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\1",
            "\1\3\11\uffff\1\4\5\uffff\1\5\1\uffff\1\2",
            "\1\6",
            "\1\7",
            "\1\10",
            "\1\11",
            "\1\12",
            "\1\13",
            "\1\14",
            "\1\15",
            "\1\16",
            "\1\17",
            "\1\20",
            "\1\21",
            "\1\23\10\uffff\1\22\5\uffff\1\24",
            "\1\25",
            "\1\26",
            "\1\27",
            "\1\30",
            "",
            "\1\31",
            "\1\32",
            "\1\35\12\uffff\1\34\6\uffff\1\33",
            "\1\36",
            "\1\37",
            "\1\40",
            "\1\41",
            "",
            "",
            "",
            "\1\44\12\uffff\1\43\6\uffff\1\42",
            "\1\45",
            "\1\46",
            "\1\50\10\uffff\1\47\5\uffff\1\51",
            "",
            "",
            "",
            "\1\52",
            "\1\54",
            "\1\55",
            "",
            "\1\56",
            "",
            "",
            "\1\57",
            "\1\61",
            "\1\62",
            "",
            "",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\67",
            "",
            "",
            "\1\70",
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
            return "1207:1: MARGIN_AREA : ( '@top-left-corner' | '@top-left' | '@top-center' | '@top-right' | '@top-right-corner' | '@bottom-left-corner' | '@bottom-left' | '@bottom-center' | '@bottom-right' | '@bottom-right-corner' | '@left-top' | '@left-middle' | '@left-bottom' | '@right-top' | '@right-middle' | '@right-bottom' );";
        }
    }
    static final String DFA29_eotS =
        "\1\uffff\1\3\2\uffff";
    static final String DFA29_eofS =
        "\4\uffff";
    static final String DFA29_minS =
        "\2\56\2\uffff";
    static final String DFA29_maxS =
        "\2\71\2\uffff";
    static final String DFA29_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA29_specialS =
        "\4\uffff}>";
    static final String[] DFA29_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\2\1\uffff\12\1",
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
            return "1476:1: fragment NUMBER_MACR : ( ( '0' .. '9' )+ | ( ( '0' .. '9' )* '.' ( '0' .. '9' )+ ) );";
        }
    }
    static final String DFA38_eotS =
        "\1\uffff\1\52\1\63\1\66\4\52\3\50\1\107\1\111\1\112\2\52\1\121\1"+
        "\123\6\uffff\1\134\11\uffff\1\147\1\uffff\1\52\3\50\1\uffff\1\52"+
        "\1\uffff\6\52\6\uffff\1\52\1\uffff\2\52\11\104\1\uffff\1\112\6\uffff"+
        "\1\112\2\uffff\1\52\34\uffff\7\52\11\104\5\52\1\62\3\52\11\104\5"+
        "\52\1\uffff\3\52\3\104\1\u00ab\5\104\10\52\2\104\1\u00bd\1\uffff"+
        "\7\104\10\52\2\104\1\uffff\11\104\5\52\2\104\1\uffff\13\104\1\52"+
        "\1\u00ec\1\uffff\1\104\1\u00ef\5\104\1\u00ef\6\104\1\52\1\uffff"+
        "\2\104\1\uffff\1\104\1\u00ef\5\104\1\u00ef\2\104\1\u0106\1\52\1"+
        "\104\1\u00ef\10\104\2\uffff\2\104\1\u00ef\2\104\2\u00ef\2\104\1"+
        "\uffff\4\104\3\u00ef\3\104\1\u00ef\5\104\1\u00ef\3\104\1\u00ef\4"+
        "\104\1\u00ef\1\104\1\u00ef";
    static final String DFA38_eofS =
        "\u012e\uffff";
    static final String DFA38_minS =
        "\1\0\1\50\1\55\1\75\4\50\1\40\1\55\1\60\2\11\1\45\2\50\1\41\1\55"+
        "\6\uffff\1\52\11\uffff\1\75\1\uffff\1\50\3\75\1\uffff\1\50\1\uffff"+
        "\6\50\1\40\5\uffff\1\50\1\uffff\2\50\1\150\1\155\1\145\1\141\2\157"+
        "\1\145\1\151\1\157\1\uffff\1\45\5\uffff\1\60\1\45\2\uffff\1\50\34"+
        "\uffff\7\50\1\141\1\160\1\144\1\147\1\160\1\164\1\146\1\147\1\156"+
        "\5\50\1\11\3\50\1\162\1\157\1\151\1\145\1\55\2\164\1\150\1\164\5"+
        "\50\1\uffff\3\50\1\163\1\162\1\141\1\55\1\143\1\157\1\55\1\164\1"+
        "\55\10\50\1\145\1\164\1\55\1\uffff\2\145\1\151\1\155\1\142\1\55"+
        "\1\146\10\50\1\164\1\11\1\uffff\1\146\1\156\1\147\1\55\1\157\1\151"+
        "\1\157\1\142\1\141\5\50\1\11\1\162\1\uffff\2\164\1\150\1\143\1\160"+
        "\1\144\1\164\1\157\1\151\1\157\1\143\2\50\1\uffff\1\154\1\55\1\145"+
        "\1\164\2\145\1\151\1\55\1\144\1\164\1\160\1\144\1\164\1\145\1\50"+
        "\1\uffff\1\50\1\143\1\uffff\1\162\1\55\1\146\1\156\1\147\1\154\1"+
        "\157\1\55\1\144\1\164\1\55\1\50\1\157\1\55\1\143\2\164\1\150\1\145"+
        "\1\155\1\154\1\157\2\uffff\1\162\1\157\1\55\1\145\1\164\2\55\1\145"+
        "\1\155\1\uffff\1\156\1\162\1\143\1\162\3\55\1\145\1\156\1\157\1"+
        "\55\1\143\1\162\1\145\1\162\1\157\1\55\1\162\1\156\1\162\1\55\1"+
        "\145\1\156\1\162\1\145\1\55\1\162\1\55";
    static final String DFA38_maxS =
        "\1\uffff\2\ufffd\1\75\14\ufffd\1\41\1\55\6\uffff\1\57\11\uffff\1"+
        "\75\1\uffff\1\ufffd\3\75\1\uffff\1\ufffd\1\uffff\7\ufffd\5\uffff"+
        "\1\ufffd\1\uffff\2\ufffd\1\150\1\155\1\145\1\141\2\157\1\145\1\151"+
        "\1\157\1\uffff\1\ufffd\5\uffff\1\71\1\ufffd\2\uffff\1\ufffd\34\uffff"+
        "\7\ufffd\1\141\1\160\1\144\1\147\1\160\1\164\1\146\1\147\1\156\11"+
        "\ufffd\1\162\1\157\1\151\1\145\1\55\2\164\1\150\1\164\5\ufffd\1"+
        "\uffff\3\ufffd\1\163\1\162\1\141\1\ufffd\1\162\1\157\1\55\1\164"+
        "\1\55\10\ufffd\1\145\1\164\1\ufffd\1\uffff\2\145\1\151\1\155\1\164"+
        "\1\55\1\146\10\ufffd\1\164\1\165\1\uffff\1\146\1\156\1\147\1\55"+
        "\1\157\1\151\1\157\1\164\1\141\5\ufffd\1\47\1\162\1\uffff\2\164"+
        "\1\150\1\162\1\160\1\144\1\164\1\157\1\151\1\157\1\143\2\ufffd\1"+
        "\uffff\1\154\1\ufffd\1\145\1\164\2\145\1\151\1\ufffd\1\144\1\164"+
        "\1\160\1\144\1\164\1\145\1\ufffd\1\uffff\1\50\1\143\1\uffff\1\162"+
        "\1\ufffd\1\146\1\156\1\147\1\154\1\157\1\ufffd\1\144\1\164\2\ufffd"+
        "\1\157\1\ufffd\1\143\2\164\1\150\1\145\1\155\1\154\1\157\2\uffff"+
        "\1\162\1\157\1\ufffd\1\145\1\164\2\ufffd\1\145\1\155\1\uffff\1\156"+
        "\1\162\1\143\1\162\3\ufffd\1\145\1\156\1\157\1\ufffd\1\143\1\162"+
        "\1\145\1\162\1\157\1\ufffd\1\162\1\156\1\162\1\ufffd\1\145\1\156"+
        "\1\162\1\145\1\ufffd\1\162\1\ufffd";
    static final String DFA38_acceptS =
        "\22\uffff\1\26\1\27\1\30\1\31\1\32\1\33\1\uffff\1\35\1\37\1\40\1"+
        "\43\1\44\1\45\1\46\1\47\1\51\1\uffff\1\53\4\uffff\1\65\1\uffff\1"+
        "\4\7\uffff\1\57\1\2\1\16\1\62\1\3\1\uffff\1\23\13\uffff\1\13\1\uffff"+
        "\1\14\1\42\1\15\1\41\1\17\2\uffff\1\21\1\20\1\uffff\1\24\1\36\1"+
        "\25\1\50\1\26\1\27\1\30\1\31\1\32\1\33\1\54\1\55\1\34\1\35\1\37"+
        "\1\40\1\43\1\44\1\45\1\46\1\47\1\51\1\64\1\52\1\53\1\60\1\61\1\63"+
        "\47\uffff\1\22\27\uffff\1\10\21\uffff\1\7\20\uffff\1\6\15\uffff"+
        "\1\5\17\uffff\1\1\2\uffff\1\11\26\uffff\1\12\1\56\11\uffff\1\56"+
        "\34\uffff";
    static final String DFA38_specialS =
        "\1\0\u012d\uffff}>";
    static final String[] DFA38_transitionS = {
            "\11\50\2\43\1\50\2\43\22\50\1\43\1\40\1\13\1\2\1\47\1\26\1\50"+
            "\1\14\1\34\1\35\1\42\1\41\1\24\1\21\1\12\1\30\12\15\1\23\1\22"+
            "\1\20\1\27\1\31\1\25\1\11\24\17\1\5\5\17\1\36\1\10\1\37\1\3"+
            "\1\6\1\50\4\44\1\16\3\44\1\1\13\44\1\4\5\44\1\32\1\46\1\33\1"+
            "\45\1\50\ud780\7\u0800\50\u1ffe\7\2\50",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\14\53\1\51\15\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "\1\64\2\uffff\12\64\7\uffff\32\64\1\uffff\1\64\2\uffff\1\64"+
            "\1\uffff\32\64\5\uffff\ud780\64\u0800\uffff\u1ffe\64",
            "\1\65",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\21\53\1\67\10\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "\1\62\2\uffff\1\70\1\uffff\1\56\2\uffff\12\55\7\uffff\32\54"+
            "\1\uffff\1\61\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "\20\72\12\71\7\72\6\71\32\72\6\71\30\72\1\uffff\ud780\72\u0800"+
            "\uffff\u1ffe\72",
            "\1\104\23\uffff\32\104\1\uffff\1\104\2\uffff\1\104\1\uffff"+
            "\1\104\1\100\1\73\2\104\1\103\2\104\1\74\2\104\1\101\1\75\2"+
            "\104\1\76\1\104\1\102\1\104\1\77\6\104\5\uffff\ud780\104\u0800"+
            "\uffff\u1ffe\104",
            "\12\105\7\uffff\32\106\1\uffff\1\106\2\uffff\1\106\1\uffff"+
            "\32\106\5\uffff\ud780\106\u0800\uffff\u1ffe\106",
            "\1\110\26\uffff\137\110\1\uffff\ud780\110\u0800\uffff\u1ffe"+
            "\110",
            "\1\110\26\uffff\137\110\1\uffff\ud780\110\u0800\uffff\u1ffe"+
            "\110",
            "\1\116\10\uffff\1\113\1\uffff\12\114\7\uffff\32\115\1\uffff"+
            "\1\115\2\uffff\1\115\1\uffff\32\115\5\uffff\ud780\115\u0800"+
            "\uffff\u1ffe\115",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\27\53\1\117\2\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "\1\120",
            "\1\122",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\132\4\uffff\1\133",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\146",
            "",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "\1\151",
            "\1\152",
            "\1\153",
            "",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\17\53\1\154\12\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "\20\156\12\155\7\156\6\155\32\156\6\155\30\156\1\uffff\ud780"+
            "\156\u0800\uffff\u1ffe\156",
            "",
            "",
            "",
            "",
            "",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\13\53\1\157\16\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "",
            "\1\62\4\uffff\1\56\2\uffff\12\162\7\uffff\6\161\24\54\1\uffff"+
            "\1\61\2\uffff\1\57\1\uffff\6\160\24\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\170",
            "\1\171",
            "\1\172",
            "\1\173",
            "",
            "\1\116\12\uffff\12\105\7\uffff\32\115\1\uffff\1\115\2\uffff"+
            "\1\115\1\uffff\32\115\5\uffff\ud780\115\u0800\uffff\u1ffe\115",
            "",
            "",
            "",
            "",
            "",
            "\12\105",
            "\1\116\10\uffff\1\113\1\uffff\12\114\7\uffff\32\115\1\uffff"+
            "\1\115\2\uffff\1\115\1\uffff\32\115\5\uffff\ud780\115\u0800"+
            "\uffff\u1ffe\115",
            "",
            "",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\17\53\1\174\12\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\16\53\1\175\13\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u0080\7\uffff\6\177\24\54\1\uffff"+
            "\1\61\2\uffff\1\57\1\uffff\6\176\24\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "\1\u0081\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1"+
            "\61\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff"+
            "\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u0084\7\uffff\6\u0083\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u0082\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u0084\7\uffff\6\u0083\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u0082\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u0084\7\uffff\6\u0083\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u0082\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\21\53\1\u008e\10\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\21\53\1\u008f\10\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u0092\7\uffff\6\u0091\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u0090\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u0092\7\uffff\6\u0091\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u0090\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u0092\7\uffff\6\u0091\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u0090\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\2\u0093\1\uffff\2\u0093\22\uffff\10\u0093\1\uffff\126\u0093"+
            "\1\uffff\ud780\u0093\u0800\uffff\u1ffe\u0093",
            "\1\62\4\uffff\1\56\2\uffff\12\u0096\7\uffff\6\u0095\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u0094\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u0096\7\uffff\6\u0095\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u0094\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u0096\7\uffff\6\u0095\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u0094\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\1\u009b",
            "\1\u009c",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\4\53\1\u00a0\25\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\23\53\1\u00a1\6\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u00a4\7\uffff\6\u00a3\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u00a2\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u00a4\7\uffff\6\u00a3\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u00a2\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u00a4\7\uffff\6\u00a3\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u00a2\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "",
            "\1\62\4\uffff\1\56\2\uffff\12\u00a7\7\uffff\6\u00a6\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u00a5\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u00a7\7\uffff\6\u00a6\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u00a5\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u00a7\7\uffff\6\u00a6\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u00a5\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\1\104\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104",
            "\1\u00ad\10\uffff\1\u00ac\5\uffff\1\u00ae",
            "\1\u00af",
            "\1\u00b0",
            "\1\u00b1",
            "\1\u00b2",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\22\53\1\u00b3\7\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\1\u00b4\31\53\5\uffff\ud780\60\u0800\uffff"+
            "\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u00b7\7\uffff\6\u00b6\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u00b5\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u00b7\7\uffff\6\u00b6\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u00b5\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u00b7\7\uffff\6\u00b6\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u00b5\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u00ba\7\uffff\6\u00b9\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u00b8\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u00ba\7\uffff\6\u00b9\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u00b8\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u00ba\7\uffff\6\u00b9\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u00b8\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\u00bb",
            "\1\u00bc",
            "\1\104\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104",
            "",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c4\12\uffff\1\u00c3\6\uffff\1\u00c2",
            "\1\u00c5",
            "\1\u00c6",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\22\53\1\u00c7\7\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\15\53\1\u00c8\14\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u00cb\7\uffff\6\u00ca\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u00c9\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u00cb\7\uffff\6\u00ca\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u00c9\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\u00cb\7\uffff\6\u00ca\24\54\1"+
            "\uffff\1\61\2\uffff\1\57\1\uffff\6\u00c9\24\53\5\uffff\ud780"+
            "\60\u0800\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "\1\u00cc",
            "\2\u00ce\1\uffff\2\u00ce\22\uffff\1\u00ce\1\uffff\1\u00ce\4"+
            "\uffff\1\u00ce\115\uffff\1\u00cd",
            "",
            "\1\u00cf",
            "\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d3",
            "\1\u00d4",
            "\1\u00d5",
            "\1\u00d8\12\uffff\1\u00d7\6\uffff\1\u00d6",
            "\1\u00d9",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\10\53\1\u00da\21\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\23\53\1\u00db\6\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "\2\u00dc\1\uffff\2\u00dc\22\uffff\1\u00dc\1\uffff\1\u00dc\4"+
            "\uffff\1\u00dc",
            "\1\u00dd",
            "",
            "\1\u00de",
            "\1\u00df",
            "\1\u00e0",
            "\1\u00e2\10\uffff\1\u00e1\5\uffff\1\u00e3",
            "\1\u00e4",
            "\1\u00e5",
            "\1\u00e6",
            "\1\u00e7",
            "\1\u00e8",
            "\1\u00e9",
            "\1\u00ea",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\16\53\1\u00eb\13\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff\u1ffe"+
            "\60",
            "",
            "\1\u00ed",
            "\1\u00ee\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104",
            "\1\u00f0",
            "\1\u00f1",
            "\1\u00f2",
            "\1\u00f3",
            "\1\u00f4",
            "\1\104\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104",
            "\1\u00f5",
            "\1\u00f6",
            "\1\u00f7",
            "\1\u00f8",
            "\1\u00f9",
            "\1\u00fa",
            "\1\62\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1\61"+
            "\2\uffff\1\57\1\uffff\15\53\1\u00fb\14\53\5\uffff\ud780\60\u0800"+
            "\uffff\u1ffe\60",
            "",
            "\1\u00ce",
            "\1\u00fc",
            "",
            "\1\u00fd",
            "\1\u00fe\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104",
            "\1\u00ff",
            "\1\u0100",
            "\1\u0101",
            "\1\u0102",
            "\1\u0103",
            "\1\104\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104",
            "\1\u0104",
            "\1\u0105",
            "\1\104\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104",
            "\1\u0107\4\uffff\1\56\2\uffff\12\55\7\uffff\32\54\1\uffff\1"+
            "\61\2\uffff\1\57\1\uffff\32\53\5\uffff\ud780\60\u0800\uffff"+
            "\u1ffe\60",
            "\1\u0108",
            "\1\104\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104",
            "\1\u0109",
            "\1\u010a",
            "\1\u010b",
            "\1\u010c",
            "\1\u010d",
            "\1\u010e",
            "\1\u010f",
            "\1\u0110",
            "",
            "",
            "\1\u0112",
            "\1\u0113",
            "\1\u0114\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104",
            "\1\u0115",
            "\1\u0116",
            "\1\104\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104",
            "\1\104\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104",
            "\1\u0117",
            "\1\u0118",
            "",
            "\1\u0119",
            "\1\u011a",
            "\1\u011b",
            "\1\u011c",
            "\1\u011d\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104",
            "\1\104\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104",
            "\1\104\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104",
            "\1\u011e",
            "\1\u011f",
            "\1\u0120",
            "\1\104\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104",
            "\1\u0121",
            "\1\u0122",
            "\1\u0123",
            "\1\u0124",
            "\1\u0125",
            "\1\104\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104",
            "\1\u0126",
            "\1\u0127",
            "\1\u0128",
            "\1\104\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104",
            "\1\u0129",
            "\1\u012a",
            "\1\u012b",
            "\1\u012c",
            "\1\104\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104",
            "\1\u012d",
            "\1\104\2\uffff\12\104\7\uffff\32\104\1\uffff\1\104\2\uffff"+
            "\1\104\1\uffff\32\104\5\uffff\ud780\104\u0800\uffff\u1ffe\104"
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
            return "1:1: Tokens : ( T__94 | T__95 | T__96 | IDENT | CHARSET | IMPORT | MEDIA | PAGE | MARGIN_AREA | FONTFACE | ATKEYWORD | CLASSKEYWORD | STRING | HASH | NUMBER | PERCENTAGE | DIMENSION | URI | UNIRANGE | CDO | CDC | SEMICOLON | COLON | COMMA | QUESTION | PERCENT | EQUALS | SLASH | GREATER | LESS | LCURLY | RCURLY | APOS | QUOT | LPAREN | RPAREN | LBRACE | RBRACE | EXCLAMATION | MINUS | PLUS | ASTERISK | S | COMMENT | SL_COMMENT | EXPRESSION | FUNCTION | INCLUDES | DASHMATCH | STARTSWITH | ENDSWITH | CONTAINS | INVALID_TOKEN );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA38_0 = input.LA(1);

                        s = -1;
                        if ( (LA38_0=='i') ) {s = 1;}

                        else if ( (LA38_0=='#') ) {s = 2;}

                        else if ( (LA38_0=='^') ) {s = 3;}

                        else if ( (LA38_0=='u') ) {s = 4;}

                        else if ( (LA38_0=='U') ) {s = 5;}

                        else if ( (LA38_0=='_') ) {s = 6;}

                        else if ( ((LA38_0>='\u0080' && LA38_0<='\uD7FF')||(LA38_0>='\uE000' && LA38_0<='\uFFFD')) ) {s = 7;}

                        else if ( (LA38_0=='\\') ) {s = 8;}

                        else if ( (LA38_0=='@') ) {s = 9;}

                        else if ( (LA38_0=='.') ) {s = 10;}

                        else if ( (LA38_0=='\"') ) {s = 11;}

                        else if ( (LA38_0=='\'') ) {s = 12;}

                        else if ( ((LA38_0>='0' && LA38_0<='9')) ) {s = 13;}

                        else if ( (LA38_0=='e') ) {s = 14;}

                        else if ( ((LA38_0>='A' && LA38_0<='T')||(LA38_0>='V' && LA38_0<='Z')) ) {s = 15;}

                        else if ( (LA38_0=='<') ) {s = 16;}

                        else if ( (LA38_0=='-') ) {s = 17;}

                        else if ( (LA38_0==';') ) {s = 18;}

                        else if ( (LA38_0==':') ) {s = 19;}

                        else if ( (LA38_0==',') ) {s = 20;}

                        else if ( (LA38_0=='?') ) {s = 21;}

                        else if ( (LA38_0=='%') ) {s = 22;}

                        else if ( (LA38_0=='=') ) {s = 23;}

                        else if ( (LA38_0=='/') ) {s = 24;}

                        else if ( (LA38_0=='>') ) {s = 25;}

                        else if ( (LA38_0=='{') ) {s = 26;}

                        else if ( (LA38_0=='}') ) {s = 27;}

                        else if ( (LA38_0=='(') ) {s = 28;}

                        else if ( (LA38_0==')') ) {s = 29;}

                        else if ( (LA38_0=='[') ) {s = 30;}

                        else if ( (LA38_0==']') ) {s = 31;}

                        else if ( (LA38_0=='!') ) {s = 32;}

                        else if ( (LA38_0=='+') ) {s = 33;}

                        else if ( (LA38_0=='*') ) {s = 34;}

                        else if ( ((LA38_0>='\t' && LA38_0<='\n')||(LA38_0>='\f' && LA38_0<='\r')||LA38_0==' ') ) {s = 35;}

                        else if ( ((LA38_0>='a' && LA38_0<='d')||(LA38_0>='f' && LA38_0<='h')||(LA38_0>='j' && LA38_0<='t')||(LA38_0>='v' && LA38_0<='z')) ) {s = 36;}

                        else if ( (LA38_0=='~') ) {s = 37;}

                        else if ( (LA38_0=='|') ) {s = 38;}

                        else if ( (LA38_0=='$') ) {s = 39;}

                        else if ( ((LA38_0>='\u0000' && LA38_0<='\b')||LA38_0=='\u000B'||(LA38_0>='\u000E' && LA38_0<='\u001F')||LA38_0=='&'||LA38_0=='`'||LA38_0=='\u007F'||(LA38_0>='\uD800' && LA38_0<='\uDFFF')||(LA38_0>='\uFFFE' && LA38_0<='\uFFFF')) ) {s = 40;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 38, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}