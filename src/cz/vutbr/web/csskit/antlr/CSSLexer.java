// $ANTLR 3.1.2 /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g 2013-02-09 17:46:23

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

    // $ANTLR start "T__99"
    public final void mT__99() throws RecognitionException {
        try {
            int _type = T__99;
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
    // $ANTLR end "T__99"

    // $ANTLR start "T__100"
    public final void mT__100() throws RecognitionException {
        try {
            int _type = T__100;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:481:8: ( '#' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:481:10: '#'
            {
            match('#'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__100"

    // $ANTLR start "T__101"
    public final void mT__101() throws RecognitionException {
        try {
            int _type = T__101;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:482:8: ( '^' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:482:10: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__101"

    // $ANTLR start "IDENT"
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1077:2: ( IDENT_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1077:4: IDENT_MACR
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1088:2: ( '@charset' ( S )* s= STRING_MACR ( S )* SEMICOLON )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1088:4: '@charset' ( S )* s= STRING_MACR ( S )* SEMICOLON
            {
            match("@charset"); 

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1088:15: ( S )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\t' && LA1_0<='\n')||(LA1_0>='\f' && LA1_0<='\r')||LA1_0==' ') ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1088:15: S
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1088:32: ( S )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='\t' && LA2_0<='\n')||(LA2_0>='\f' && LA2_0<='\r')||LA2_0==' ') ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1088:32: S
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1122:2: ( '@import' ( S )* (s= STRING_MACR | s= URI ) ( S )* (m= IDENT_MACR ( S )* ( ',' ( S )* m= IDENT_MACR ( S )* )* )? SEMICOLON )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1122:4: '@import' ( S )* (s= STRING_MACR | s= URI ) ( S )* (m= IDENT_MACR ( S )* ( ',' ( S )* m= IDENT_MACR ( S )* )* )? SEMICOLON
            {
            match("@import"); 

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1122:14: ( S )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='\t' && LA3_0<='\n')||(LA3_0>='\f' && LA3_0<='\r')||LA3_0==' ') ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1122:14: S
            	    {
            	    mS(); 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1123:4: (s= STRING_MACR | s= URI )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1123:5: s= STRING_MACR
                    {
                    int sStart130 = getCharIndex();
                    mSTRING_MACR(); 
                    s = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, sStart130, getCharIndex()-1);
                     s.setType(STRING);

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1124:7: s= URI
                    {
                    int sStart143 = getCharIndex();
                    mURI(); 
                    s = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, sStart143, getCharIndex()-1);
                    s.setType(URI);

                    }
                    break;

            }

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1124:33: ( S )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\t' && LA5_0<='\n')||(LA5_0>='\f' && LA5_0<='\r')||LA5_0==' ') ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1124:33: S
            	    {
            	    mS(); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1125:6: (m= IDENT_MACR ( S )* ( ',' ( S )* m= IDENT_MACR ( S )* )* )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0>='A' && LA10_0<='Z')||LA10_0=='\\'||LA10_0=='_'||(LA10_0>='a' && LA10_0<='z')||(LA10_0>='\u0080' && LA10_0<='\uD7FF')||(LA10_0>='\uE000' && LA10_0<='\uFFFD')) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1125:7: m= IDENT_MACR ( S )* ( ',' ( S )* m= IDENT_MACR ( S )* )*
                    {
                    int mStart159 = getCharIndex();
                    mIDENT_MACR(); 
                    m = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, mStart159, getCharIndex()-1);
                     
                    	        mText = m.getText();
                    	    	if(css.isSupportedMedia(mText)) 
                    	    		media.append(mText); 
                    	    	else
                    	    	    log.debug("Invalid import media \"{}\"", mText);
                    	     
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1132:7: ( S )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='\t' && LA6_0<='\n')||(LA6_0>='\f' && LA6_0<='\r')||LA6_0==' ') ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1132:7: S
                    	    {
                    	    mS(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1133:9: ( ',' ( S )* m= IDENT_MACR ( S )* )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==',') ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1133:10: ',' ( S )* m= IDENT_MACR ( S )*
                    	    {
                    	    match(','); 
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1133:14: ( S )*
                    	    loop7:
                    	    do {
                    	        int alt7=2;
                    	        int LA7_0 = input.LA(1);

                    	        if ( ((LA7_0>='\t' && LA7_0<='\n')||(LA7_0>='\f' && LA7_0<='\r')||LA7_0==' ') ) {
                    	            alt7=1;
                    	        }


                    	        switch (alt7) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1133:14: S
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
                    	    	       	
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1140:9: ( S )*
                    	    loop8:
                    	    do {
                    	        int alt8=2;
                    	        int LA8_0 = input.LA(1);

                    	        if ( ((LA8_0>='\t' && LA8_0<='\n')||(LA8_0>='\f' && LA8_0<='\r')||LA8_0==' ') ) {
                    	            alt8=1;
                    	        }


                    	        switch (alt8) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1140:9: S
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1205:2: ( '@media' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1205:4: '@media'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1209:2: ( '@page' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1209:4: '@page'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1213:3: ( '@top-left-corner' | '@top-left' | '@top-center' | '@top-right' | '@top-right-corner' | '@bottom-left-corner' | '@bottom-left' | '@bottom-center' | '@bottom-right' | '@bottom-right-corner' | '@left-top' | '@left-middle' | '@left-bottom' | '@right-top' | '@right-middle' | '@right-bottom' )
            int alt11=16;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1213:5: '@top-left-corner'
                    {
                    match("@top-left-corner"); 


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1214:5: '@top-left'
                    {
                    match("@top-left"); 


                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1215:5: '@top-center'
                    {
                    match("@top-center"); 


                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1216:5: '@top-right'
                    {
                    match("@top-right"); 


                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1217:5: '@top-right-corner'
                    {
                    match("@top-right-corner"); 


                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1218:5: '@bottom-left-corner'
                    {
                    match("@bottom-left-corner"); 


                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1219:5: '@bottom-left'
                    {
                    match("@bottom-left"); 


                    }
                    break;
                case 8 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1220:5: '@bottom-center'
                    {
                    match("@bottom-center"); 


                    }
                    break;
                case 9 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1221:5: '@bottom-right'
                    {
                    match("@bottom-right"); 


                    }
                    break;
                case 10 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1222:5: '@bottom-right-corner'
                    {
                    match("@bottom-right-corner"); 


                    }
                    break;
                case 11 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1223:5: '@left-top'
                    {
                    match("@left-top"); 


                    }
                    break;
                case 12 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1224:5: '@left-middle'
                    {
                    match("@left-middle"); 


                    }
                    break;
                case 13 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1225:5: '@left-bottom'
                    {
                    match("@left-bottom"); 


                    }
                    break;
                case 14 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1226:5: '@right-top'
                    {
                    match("@right-top"); 


                    }
                    break;
                case 15 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1227:5: '@right-middle'
                    {
                    match("@right-middle"); 


                    }
                    break;
                case 16 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1228:5: '@right-bottom'
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

    // $ANTLR start "VIEWPORT"
    public final void mVIEWPORT() throws RecognitionException {
        try {
            int _type = VIEWPORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1232:3: ( '@viewport' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1232:5: '@viewport'
            {
            match("@viewport"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VIEWPORT"

    // $ANTLR start "FONTFACE"
    public final void mFONTFACE() throws RecognitionException {
        try {
            int _type = FONTFACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1236:3: ( '@font-face' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1236:5: '@font-face'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1241:2: ( '@' ( MINUS )? IDENT_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1241:4: '@' ( MINUS )? IDENT_MACR
            {
            match('@'); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1241:8: ( MINUS )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='-') ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1241:8: MINUS
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1245:5: ( '.' IDENT_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1245:7: '.' IDENT_MACR
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1256:2: ( STRING_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1256:4: STRING_MACR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1261:2: ( '#' NAME_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1261:4: '#' NAME_MACR
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

    // $ANTLR start "INDEX"
    public final void mINDEX() throws RecognitionException {
        try {
            int _type = INDEX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1266:3: ( ( '+' | '-' )? ( INTEGER_MACR )? ( 'N' | 'n' ) ( ( S )* ( '+' | '-' ) ( S )* INTEGER_MACR )? )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1266:5: ( '+' | '-' )? ( INTEGER_MACR )? ( 'N' | 'n' ) ( ( S )* ( '+' | '-' ) ( S )* INTEGER_MACR )?
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1266:5: ( '+' | '-' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='+'||LA13_0=='-') ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1266:18: ( INTEGER_MACR )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>='0' && LA14_0<='9')) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1266:18: INTEGER_MACR
                    {
                    mINTEGER_MACR(); 

                    }
                    break;

            }

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1266:44: ( ( S )* ( '+' | '-' ) ( S )* INTEGER_MACR )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( ((LA17_0>='\t' && LA17_0<='\n')||(LA17_0>='\f' && LA17_0<='\r')||LA17_0==' '||LA17_0=='+'||LA17_0=='-') ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1266:45: ( S )* ( '+' | '-' ) ( S )* INTEGER_MACR
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1266:45: ( S )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( ((LA15_0>='\t' && LA15_0<='\n')||(LA15_0>='\f' && LA15_0<='\r')||LA15_0==' ') ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1266:45: S
                    	    {
                    	    mS(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);

                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1266:60: ( S )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( ((LA16_0>='\t' && LA16_0<='\n')||(LA16_0>='\f' && LA16_0<='\r')||LA16_0==' ') ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1266:60: S
                    	    {
                    	    mS(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);

                    mINTEGER_MACR(); 

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
    // $ANTLR end "INDEX"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1271:2: ( NUMBER_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1271:4: NUMBER_MACR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1276:2: ( NUMBER_MACR '%' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1276:4: NUMBER_MACR '%'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1281:2: ( NUMBER_MACR IDENT_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1281:4: NUMBER_MACR IDENT_MACR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1286:2: ( 'url(' W_MACR ( STRING_MACR | URI_MACR ) W_MACR ')' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1286:4: 'url(' W_MACR ( STRING_MACR | URI_MACR ) W_MACR ')'
            {
            match("url("); 

            mW_MACR(); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1286:18: ( STRING_MACR | URI_MACR )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0=='\"'||LA18_0=='\'') ) {
                alt18=1;
            }
            else if ( ((LA18_0>='\t' && LA18_0<='\n')||(LA18_0>='\f' && LA18_0<='\r')||(LA18_0>=' ' && LA18_0<='!')||(LA18_0>='#' && LA18_0<='&')||(LA18_0>=')' && LA18_0<='~')||(LA18_0>='\u0080' && LA18_0<='\uD7FF')||(LA18_0>='\uE000' && LA18_0<='\uFFFD')) ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1286:19: STRING_MACR
                    {
                    mSTRING_MACR(); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1286:33: URI_MACR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1290:9: ( 'U+' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) )? ( '-' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )? )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1291:2: 'U+' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) )? ( '-' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )?
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1295:7: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( ((LA19_0>='0' && LA19_0<='9')||LA19_0=='?'||(LA19_0>='A' && LA19_0<='F')||(LA19_0>='a' && LA19_0<='f')) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1295:8: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' )
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1296:2: ( '-' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='-') ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1296:3: '-' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )?
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

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1301:14: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( ((LA20_0>='0' && LA20_0<='9')||(LA20_0>='A' && LA20_0<='F')||(LA20_0>='a' && LA20_0<='f')) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1301:15: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1307:2: ( '<!--' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1307:4: '<!--'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1312:2: ( '-->' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1312:4: '-->'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1316:2: ( ';' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1316:4: ';'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1320:2: ( ':' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1320:4: ':'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1324:5: ( ',' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1324:7: ','
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1328:2: ( '?' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1328:4: '?'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1332:2: ( '%' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1332:4: '%'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1336:5: ( '=' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1336:7: '='
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1340:5: ( '/' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1340:7: '/'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1344:5: ( '>' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1344:7: '>'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1348:5: ( '<' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1348:7: '<'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1352:2: ( '{' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1352:4: '{'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1356:2: ( '}' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1356:4: '}'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1360:2: ( '\\'' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1360:4: '\\''
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1364:2: ( '\"' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1364:4: '\"'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1368:2: ( '(' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1368:4: '('
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1372:2: ( ')' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1372:4: ')'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1376:2: ( '[' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1376:4: '['
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1380:2: ( ']' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1380:4: ']'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1384:5: ( '!' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1384:7: '!'
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

    // $ANTLR start "TILDE"
    public final void mTILDE() throws RecognitionException {
        try {
            int _type = TILDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1388:3: ( '~' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1388:5: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TILDE"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1392:2: ( '-' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1392:4: '-'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1396:2: ( '+' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1396:4: '+'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1400:2: ( '*' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1400:4: '*'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1405:2: ( ( W_CHAR )+ )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1405:4: ( W_CHAR )+
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1405:4: ( W_CHAR )+
            int cnt22=0;
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>='\t' && LA22_0<='\n')||(LA22_0>='\f' && LA22_0<='\r')||LA22_0==' ') ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1405:4: W_CHAR
            	    {
            	    mW_CHAR(); 

            	    }
            	    break;

            	default :
            	    if ( cnt22 >= 1 ) break loop22;
                        EarlyExitException eee =
                            new EarlyExitException(22, input);
                        throw eee;
                }
                cnt22++;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1409:2: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1409:4: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1409:9: ( options {greedy=false; } : . )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0=='*') ) {
                    int LA23_1 = input.LA(2);

                    if ( (LA23_1=='/') ) {
                        alt23=2;
                    }
                    else if ( ((LA23_1>='\u0000' && LA23_1<='.')||(LA23_1>='0' && LA23_1<='\uFFFF')) ) {
                        alt23=1;
                    }


                }
                else if ( ((LA23_0>='\u0000' && LA23_0<=')')||(LA23_0>='+' && LA23_0<='\uFFFF')) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1409:37: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop23;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1413:2: ( '//' ( options {greedy=false; } : . )* ( '\\n' | '\\r' ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1413:4: '//' ( options {greedy=false; } : . )* ( '\\n' | '\\r' )
            {
            match("//"); 

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1413:9: ( options {greedy=false; } : . )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0=='\n'||LA24_0=='\r') ) {
                    alt24=2;
                }
                else if ( ((LA24_0>='\u0000' && LA24_0<='\t')||(LA24_0>='\u000B' && LA24_0<='\f')||(LA24_0>='\u000E' && LA24_0<='\uFFFF')) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1413:37: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop24;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1418:3: ( 'expression(' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1418:5: 'expression('
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1423:2: ( IDENT_MACR '(' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1423:4: IDENT_MACR '('
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1427:2: ( '~=' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1427:4: '~='
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1431:2: ( '|=' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1431:4: '|='
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1435:3: ( '^=' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1435:5: '^='
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1439:3: ( '$=' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1439:5: '$='
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1443:3: ( '*=' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1443:5: '*='
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1447:2: ( . )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1447:4: .
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1456:4: ( NAME_START ( NAME_CHAR )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1456:6: NAME_START ( NAME_CHAR )*
            {
            mNAME_START(); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1456:17: ( NAME_CHAR )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0=='-'||(LA25_0>='0' && LA25_0<='9')||(LA25_0>='A' && LA25_0<='Z')||LA25_0=='\\'||LA25_0=='_'||(LA25_0>='a' && LA25_0<='z')||(LA25_0>='\u0080' && LA25_0<='\uD7FF')||(LA25_0>='\uE000' && LA25_0<='\uFFFD')) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1456:17: NAME_CHAR
            	    {
            	    mNAME_CHAR(); 

            	    }
            	    break;

            	default :
            	    break loop25;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1461:3: ( ( NAME_CHAR )+ )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1461:5: ( NAME_CHAR )+
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1461:5: ( NAME_CHAR )+
            int cnt26=0;
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0=='-'||(LA26_0>='0' && LA26_0<='9')||(LA26_0>='A' && LA26_0<='Z')||LA26_0=='\\'||LA26_0=='_'||(LA26_0>='a' && LA26_0<='z')||(LA26_0>='\u0080' && LA26_0<='\uD7FF')||(LA26_0>='\uE000' && LA26_0<='\uFFFD')) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1461:5: NAME_CHAR
            	    {
            	    mNAME_CHAR(); 

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

        }
        finally {
        }
    }
    // $ANTLR end "NAME_MACR"

    // $ANTLR start "NAME_START"
    public final void mNAME_START() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1466:4: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' | NON_ASCII | ESCAPE_CHAR ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1466:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | NON_ASCII | ESCAPE_CHAR )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1466:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | NON_ASCII | ESCAPE_CHAR )
            int alt27=5;
            int LA27_0 = input.LA(1);

            if ( ((LA27_0>='a' && LA27_0<='z')) ) {
                alt27=1;
            }
            else if ( ((LA27_0>='A' && LA27_0<='Z')) ) {
                alt27=2;
            }
            else if ( (LA27_0=='_') ) {
                alt27=3;
            }
            else if ( ((LA27_0>='\u0080' && LA27_0<='\uD7FF')||(LA27_0>='\uE000' && LA27_0<='\uFFFD')) ) {
                alt27=4;
            }
            else if ( (LA27_0=='\\') ) {
                alt27=5;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1466:7: 'a' .. 'z'
                    {
                    matchRange('a','z'); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1466:18: 'A' .. 'Z'
                    {
                    matchRange('A','Z'); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1466:29: '_'
                    {
                    match('_'); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1466:35: NON_ASCII
                    {
                    mNON_ASCII(); 

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1466:47: ESCAPE_CHAR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1471:4: ( ( '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1471:6: ( '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1476:3: ( ( '\\\\' ) ( ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? ) | ( '\\u0020' .. '\\u007E' | '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' ) ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1476:5: ( '\\\\' ) ( ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? ) | ( '\\u0020' .. '\\u007E' | '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' ) )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1476:5: ( '\\\\' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1476:6: '\\\\'
            {
            match('\\'); 

            }

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1477:5: ( ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? ) | ( '\\u0020' .. '\\u007E' | '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' ) )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( ((LA29_0>='0' && LA29_0<='9')||(LA29_0>='A' && LA29_0<='F')||(LA29_0>='a' && LA29_0<='f')) ) {
                int LA29_1 = input.LA(2);

                if ( ((LA29_1>='0' && LA29_1<='9')||(LA29_1>='A' && LA29_1<='F')||(LA29_1>='a' && LA29_1<='f')) ) {
                    alt29=1;
                }
                else {
                    alt29=2;}
            }
            else if ( ((LA29_0>=' ' && LA29_0<='/')||(LA29_0>=':' && LA29_0<='@')||(LA29_0>='G' && LA29_0<='`')||(LA29_0>='g' && LA29_0<='~')||(LA29_0>='\u0080' && LA29_0<='\uD7FF')||(LA29_0>='\uE000' && LA29_0<='\uFFFD')) ) {
                alt29=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1478:7: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1478:7: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1478:8: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )?
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

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1482:8: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( ((LA28_0>='0' && LA28_0<='9')||(LA28_0>='A' && LA28_0<='F')||(LA28_0>='a' && LA28_0<='f')) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1482:9: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1485:7: ( '\\u0020' .. '\\u007E' | '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1491:4: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | NON_ASCII | ESCAPE_CHAR ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1491:6: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | NON_ASCII | ESCAPE_CHAR )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1491:6: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | NON_ASCII | ESCAPE_CHAR )
            int alt30=7;
            int LA30_0 = input.LA(1);

            if ( ((LA30_0>='a' && LA30_0<='z')) ) {
                alt30=1;
            }
            else if ( ((LA30_0>='A' && LA30_0<='Z')) ) {
                alt30=2;
            }
            else if ( ((LA30_0>='0' && LA30_0<='9')) ) {
                alt30=3;
            }
            else if ( (LA30_0=='-') ) {
                alt30=4;
            }
            else if ( (LA30_0=='_') ) {
                alt30=5;
            }
            else if ( ((LA30_0>='\u0080' && LA30_0<='\uD7FF')||(LA30_0>='\uE000' && LA30_0<='\uFFFD')) ) {
                alt30=6;
            }
            else if ( (LA30_0=='\\') ) {
                alt30=7;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1491:7: 'a' .. 'z'
                    {
                    matchRange('a','z'); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1491:18: 'A' .. 'Z'
                    {
                    matchRange('A','Z'); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1491:29: '0' .. '9'
                    {
                    matchRange('0','9'); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1491:40: '-'
                    {
                    match('-'); 

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1491:46: '_'
                    {
                    match('_'); 

                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1491:52: NON_ASCII
                    {
                    mNON_ASCII(); 

                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1491:64: ESCAPE_CHAR
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

    // $ANTLR start "INTEGER_MACR"
    public final void mINTEGER_MACR() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1496:5: ( ( '0' .. '9' )+ )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1496:7: ( '0' .. '9' )+
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1496:7: ( '0' .. '9' )+
            int cnt31=0;
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( ((LA31_0>='0' && LA31_0<='9')) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1496:8: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt31 >= 1 ) break loop31;
                        EarlyExitException eee =
                            new EarlyExitException(31, input);
                        throw eee;
                }
                cnt31++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "INTEGER_MACR"

    // $ANTLR start "NUMBER_MACR"
    public final void mNUMBER_MACR() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1501:4: ( ( '0' .. '9' )+ | ( ( '0' .. '9' )* '.' ( '0' .. '9' )+ ) )
            int alt35=2;
            alt35 = dfa35.predict(input);
            switch (alt35) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1501:6: ( '0' .. '9' )+
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1501:6: ( '0' .. '9' )+
                    int cnt32=0;
                    loop32:
                    do {
                        int alt32=2;
                        int LA32_0 = input.LA(1);

                        if ( ((LA32_0>='0' && LA32_0<='9')) ) {
                            alt32=1;
                        }


                        switch (alt32) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1501:7: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

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


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1501:20: ( ( '0' .. '9' )* '.' ( '0' .. '9' )+ )
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1501:20: ( ( '0' .. '9' )* '.' ( '0' .. '9' )+ )
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1501:21: ( '0' .. '9' )* '.' ( '0' .. '9' )+
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1501:21: ( '0' .. '9' )*
                    loop33:
                    do {
                        int alt33=2;
                        int LA33_0 = input.LA(1);

                        if ( ((LA33_0>='0' && LA33_0<='9')) ) {
                            alt33=1;
                        }


                        switch (alt33) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1501:22: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop33;
                        }
                    } while (true);

                    match('.'); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1501:37: ( '0' .. '9' )+
                    int cnt34=0;
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( ((LA34_0>='0' && LA34_0<='9')) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1501:38: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1506:2: ( QUOT ( STRING_CHAR | APOS )* QUOT | APOS ( STRING_CHAR | QUOT )* APOS )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0=='\"') ) {
                alt38=1;
            }
            else if ( (LA38_0=='\'') ) {
                alt38=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }
            switch (alt38) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1506:4: QUOT ( STRING_CHAR | APOS )* QUOT
                    {
                    mQUOT(); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1506:9: ( STRING_CHAR | APOS )*
                    loop36:
                    do {
                        int alt36=3;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0=='\t'||(LA36_0>=' ' && LA36_0<='!')||(LA36_0>='#' && LA36_0<='&')||(LA36_0>='(' && LA36_0<='~')||(LA36_0>='\u0080' && LA36_0<='\uD7FF')||(LA36_0>='\uE000' && LA36_0<='\uFFFD')) ) {
                            alt36=1;
                        }
                        else if ( (LA36_0=='\'') ) {
                            alt36=2;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1506:10: STRING_CHAR
                    	    {
                    	    mSTRING_CHAR(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1506:24: APOS
                    	    {
                    	    mAPOS(); 
                    	    ls.aposOpen=false;

                    	    }
                    	    break;

                    	default :
                    	    break loop36;
                        }
                    } while (true);

                    mQUOT(); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1507:4: APOS ( STRING_CHAR | QUOT )* APOS
                    {
                    mAPOS(); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1507:9: ( STRING_CHAR | QUOT )*
                    loop37:
                    do {
                        int alt37=3;
                        int LA37_0 = input.LA(1);

                        if ( (LA37_0=='\t'||(LA37_0>=' ' && LA37_0<='!')||(LA37_0>='#' && LA37_0<='&')||(LA37_0>='(' && LA37_0<='~')||(LA37_0>='\u0080' && LA37_0<='\uD7FF')||(LA37_0>='\uE000' && LA37_0<='\uFFFD')) ) {
                            alt37=1;
                        }
                        else if ( (LA37_0=='\"') ) {
                            alt37=2;
                        }


                        switch (alt37) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1507:10: STRING_CHAR
                    	    {
                    	    mSTRING_CHAR(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1507:24: QUOT
                    	    {
                    	    mQUOT(); 
                    	    ls.quotOpen=false;

                    	    }
                    	    break;

                    	default :
                    	    break loop37;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1512:2: ( ( URI_CHAR | ' ' | '(' | ')' | ( '\\\\' NL_CHAR ) ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1512:5: ( URI_CHAR | ' ' | '(' | ')' | ( '\\\\' NL_CHAR ) )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1512:5: ( URI_CHAR | ' ' | '(' | ')' | ( '\\\\' NL_CHAR ) )
            int alt39=5;
            int LA39_0 = input.LA(1);

            if ( (LA39_0=='\\') ) {
                int LA39_1 = input.LA(2);

                if ( (LA39_1=='\n'||(LA39_1>='\f' && LA39_1<='\r')) ) {
                    alt39=5;
                }
                else {
                    alt39=1;}
            }
            else if ( (LA39_0=='\t'||LA39_0=='!'||(LA39_0>='#' && LA39_0<='&')||(LA39_0>='*' && LA39_0<='[')||(LA39_0>=']' && LA39_0<='~')||(LA39_0>='\u0080' && LA39_0<='\uD7FF')||(LA39_0>='\uE000' && LA39_0<='\uFFFD')) ) {
                alt39=1;
            }
            else if ( (LA39_0==' ') ) {
                alt39=2;
            }
            else if ( (LA39_0=='(') ) {
                alt39=3;
            }
            else if ( (LA39_0==')') ) {
                alt39=4;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }
            switch (alt39) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1512:6: URI_CHAR
                    {
                    mURI_CHAR(); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1512:17: ' '
                    {
                    match(' '); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1512:23: '('
                    {
                    match('('); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1512:29: ')'
                    {
                    match(')'); 

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1512:35: ( '\\\\' NL_CHAR )
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1512:35: ( '\\\\' NL_CHAR )
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1512:36: '\\\\' NL_CHAR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1517:2: ( ( URI_CHAR )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1517:4: ( URI_CHAR )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1517:4: ( URI_CHAR )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0=='\t'||LA40_0=='!'||(LA40_0>='#' && LA40_0<='&')||(LA40_0>='*' && LA40_0<='~')||(LA40_0>='\u0080' && LA40_0<='\uD7FF')||(LA40_0>='\uE000' && LA40_0<='\uFFFD')) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1517:4: URI_CHAR
            	    {
            	    mURI_CHAR(); 

            	    }
            	    break;

            	default :
            	    break loop40;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1522:2: ( ( '\\u0009' | '\\u0021' | '\\u0023' .. '\\u0026' | '\\u002A' .. '\\u007E' ) | NON_ASCII | ESCAPE_CHAR )
            int alt41=3;
            int LA41_0 = input.LA(1);

            if ( (LA41_0=='\\') ) {
                int LA41_1 = input.LA(2);

                if ( ((LA41_1>=' ' && LA41_1<='~')||(LA41_1>='\u0080' && LA41_1<='\uD7FF')||(LA41_1>='\uE000' && LA41_1<='\uFFFD')) ) {
                    alt41=3;
                }
                else {
                    alt41=1;}
            }
            else if ( ((LA41_0>='\u0080' && LA41_0<='\uD7FF')||(LA41_0>='\uE000' && LA41_0<='\uFFFD')) ) {
                alt41=2;
            }
            else if ( (LA41_0=='\t'||LA41_0=='!'||(LA41_0>='#' && LA41_0<='&')||(LA41_0>='*' && LA41_0<='[')||(LA41_0>=']' && LA41_0<='~')) ) {
                alt41=1;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1522:4: ( '\\u0009' | '\\u0021' | '\\u0023' .. '\\u0026' | '\\u002A' .. '\\u007E' )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1523:6: NON_ASCII
                    {
                    mNON_ASCII(); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1523:18: ESCAPE_CHAR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1528:4: ( '\\u000A' | '\\u000D' '\\u000A' | '\\u000D' | '\\u000C' )
            int alt42=4;
            switch ( input.LA(1) ) {
            case '\n':
                {
                alt42=1;
                }
                break;
            case '\r':
                {
                int LA42_2 = input.LA(2);

                if ( (LA42_2=='\n') ) {
                    alt42=2;
                }
                else {
                    alt42=3;}
                }
                break;
            case '\f':
                {
                alt42=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;
            }

            switch (alt42) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1528:6: '\\u000A'
                    {
                    match('\n'); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1528:17: '\\u000D' '\\u000A'
                    {
                    match('\r'); 
                    match('\n'); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1528:37: '\\u000D'
                    {
                    match('\r'); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1528:48: '\\u000C'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1533:2: ( ( W_CHAR )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1533:4: ( W_CHAR )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1533:4: ( W_CHAR )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( ((LA43_0>='\t' && LA43_0<='\n')||(LA43_0>='\f' && LA43_0<='\r')||LA43_0==' ') ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1533:4: W_CHAR
            	    {
            	    mW_CHAR(); 

            	    }
            	    break;

            	default :
            	    break loop43;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1538:4: ( '\\u0009' | '\\u000A' | '\\u000C' | '\\u000D' | '\\u0020' )
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
        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:8: ( T__99 | T__100 | T__101 | IDENT | CHARSET | IMPORT | MEDIA | PAGE | MARGIN_AREA | VIEWPORT | FONTFACE | ATKEYWORD | CLASSKEYWORD | STRING | HASH | INDEX | NUMBER | PERCENTAGE | DIMENSION | URI | UNIRANGE | CDO | CDC | SEMICOLON | COLON | COMMA | QUESTION | PERCENT | EQUALS | SLASH | GREATER | LESS | LCURLY | RCURLY | APOS | QUOT | LPAREN | RPAREN | LBRACE | RBRACE | EXCLAMATION | TILDE | MINUS | PLUS | ASTERISK | S | COMMENT | SL_COMMENT | EXPRESSION | FUNCTION | INCLUDES | DASHMATCH | STARTSWITH | ENDSWITH | CONTAINS | INVALID_TOKEN )
        int alt44=56;
        alt44 = dfa44.predict(input);
        switch (alt44) {
            case 1 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:10: T__99
                {
                mT__99(); 

                }
                break;
            case 2 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:16: T__100
                {
                mT__100(); 

                }
                break;
            case 3 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:23: T__101
                {
                mT__101(); 

                }
                break;
            case 4 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:30: IDENT
                {
                mIDENT(); 

                }
                break;
            case 5 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:36: CHARSET
                {
                mCHARSET(); 

                }
                break;
            case 6 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:44: IMPORT
                {
                mIMPORT(); 

                }
                break;
            case 7 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:51: MEDIA
                {
                mMEDIA(); 

                }
                break;
            case 8 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:57: PAGE
                {
                mPAGE(); 

                }
                break;
            case 9 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:62: MARGIN_AREA
                {
                mMARGIN_AREA(); 

                }
                break;
            case 10 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:74: VIEWPORT
                {
                mVIEWPORT(); 

                }
                break;
            case 11 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:83: FONTFACE
                {
                mFONTFACE(); 

                }
                break;
            case 12 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:92: ATKEYWORD
                {
                mATKEYWORD(); 

                }
                break;
            case 13 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:102: CLASSKEYWORD
                {
                mCLASSKEYWORD(); 

                }
                break;
            case 14 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:115: STRING
                {
                mSTRING(); 

                }
                break;
            case 15 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:122: HASH
                {
                mHASH(); 

                }
                break;
            case 16 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:127: INDEX
                {
                mINDEX(); 

                }
                break;
            case 17 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:133: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 18 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:140: PERCENTAGE
                {
                mPERCENTAGE(); 

                }
                break;
            case 19 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:151: DIMENSION
                {
                mDIMENSION(); 

                }
                break;
            case 20 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:161: URI
                {
                mURI(); 

                }
                break;
            case 21 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:165: UNIRANGE
                {
                mUNIRANGE(); 

                }
                break;
            case 22 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:174: CDO
                {
                mCDO(); 

                }
                break;
            case 23 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:178: CDC
                {
                mCDC(); 

                }
                break;
            case 24 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:182: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 25 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:192: COLON
                {
                mCOLON(); 

                }
                break;
            case 26 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:198: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 27 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:204: QUESTION
                {
                mQUESTION(); 

                }
                break;
            case 28 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:213: PERCENT
                {
                mPERCENT(); 

                }
                break;
            case 29 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:221: EQUALS
                {
                mEQUALS(); 

                }
                break;
            case 30 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:228: SLASH
                {
                mSLASH(); 

                }
                break;
            case 31 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:234: GREATER
                {
                mGREATER(); 

                }
                break;
            case 32 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:242: LESS
                {
                mLESS(); 

                }
                break;
            case 33 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:247: LCURLY
                {
                mLCURLY(); 

                }
                break;
            case 34 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:254: RCURLY
                {
                mRCURLY(); 

                }
                break;
            case 35 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:261: APOS
                {
                mAPOS(); 

                }
                break;
            case 36 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:266: QUOT
                {
                mQUOT(); 

                }
                break;
            case 37 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:271: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 38 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:278: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 39 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:285: LBRACE
                {
                mLBRACE(); 

                }
                break;
            case 40 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:292: RBRACE
                {
                mRBRACE(); 

                }
                break;
            case 41 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:299: EXCLAMATION
                {
                mEXCLAMATION(); 

                }
                break;
            case 42 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:311: TILDE
                {
                mTILDE(); 

                }
                break;
            case 43 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:317: MINUS
                {
                mMINUS(); 

                }
                break;
            case 44 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:323: PLUS
                {
                mPLUS(); 

                }
                break;
            case 45 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:328: ASTERISK
                {
                mASTERISK(); 

                }
                break;
            case 46 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:337: S
                {
                mS(); 

                }
                break;
            case 47 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:339: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 48 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:347: SL_COMMENT
                {
                mSL_COMMENT(); 

                }
                break;
            case 49 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:358: EXPRESSION
                {
                mEXPRESSION(); 

                }
                break;
            case 50 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:369: FUNCTION
                {
                mFUNCTION(); 

                }
                break;
            case 51 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:378: INCLUDES
                {
                mINCLUDES(); 

                }
                break;
            case 52 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:387: DASHMATCH
                {
                mDASHMATCH(); 

                }
                break;
            case 53 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:397: STARTSWITH
                {
                mSTARTSWITH(); 

                }
                break;
            case 54 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:408: ENDSWITH
                {
                mENDSWITH(); 

                }
                break;
            case 55 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:417: CONTAINS
                {
                mCONTAINS(); 

                }
                break;
            case 56 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:426: INVALID_TOKEN
                {
                mINVALID_TOKEN(); 

                }
                break;

        }

    }


    protected DFA11 dfa11 = new DFA11(this);
    protected DFA35 dfa35 = new DFA35(this);
    protected DFA44 dfa44 = new DFA44(this);
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
            return "1212:1: MARGIN_AREA : ( '@top-left-corner' | '@top-left' | '@top-center' | '@top-right' | '@top-right-corner' | '@bottom-left-corner' | '@bottom-left' | '@bottom-center' | '@bottom-right' | '@bottom-right-corner' | '@left-top' | '@left-middle' | '@left-bottom' | '@right-top' | '@right-middle' | '@right-bottom' );";
        }
    }
    static final String DFA35_eotS =
        "\1\uffff\1\3\2\uffff";
    static final String DFA35_eofS =
        "\4\uffff";
    static final String DFA35_minS =
        "\2\56\2\uffff";
    static final String DFA35_maxS =
        "\2\71\2\uffff";
    static final String DFA35_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA35_specialS =
        "\4\uffff}>";
    static final String[] DFA35_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\2\1\uffff\12\1",
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
            return "1499:1: fragment NUMBER_MACR : ( ( '0' .. '9' )+ | ( ( '0' .. '9' )* '.' ( '0' .. '9' )+ ) );";
        }
    }
    static final String DFA44_eotS =
        "\1\uffff\1\54\1\65\1\70\4\54\3\52\1\112\1\114\1\116\1\117\4\54\1"+
        "\132\1\133\6\uffff\1\144\10\uffff\1\156\1\160\1\uffff\1\54\2\52"+
        "\1\uffff\1\54\1\uffff\6\54\7\uffff\3\54\12\107\1\uffff\1\117\10"+
        "\uffff\1\117\1\uffff\2\71\1\uffff\1\54\1\uffff\1\54\33\uffff\7\54"+
        "\12\107\1\125\11\54\12\107\1\71\1\64\10\54\3\107\1\u00b9\6\107\1"+
        "\uffff\10\54\2\107\1\u00cc\1\uffff\10\107\10\54\2\107\1\uffff\12"+
        "\107\5\54\2\107\1\uffff\14\107\1\54\1\u00fe\1\uffff\1\107\1\u0101"+
        "\5\107\1\u0101\5\107\1\u010c\1\107\1\54\1\uffff\2\107\1\uffff\1"+
        "\107\1\u0101\5\107\1\u0101\2\107\1\uffff\1\u0119\1\54\1\107\1\u0101"+
        "\10\107\2\uffff\2\107\1\u0101\2\107\2\u0101\2\107\1\uffff\4\107"+
        "\3\u0101\3\107\1\u0101\5\107\1\u0101\3\107\1\u0101\4\107\1\u0101"+
        "\1\107\1\u0101";
    static final String DFA44_eofS =
        "\u0141\uffff";
    static final String DFA44_minS =
        "\1\0\1\50\1\55\1\75\2\11\2\50\1\40\1\55\1\60\2\11\1\55\1\45\4\50"+
        "\1\41\1\60\6\uffff\1\52\10\uffff\2\75\1\uffff\1\50\2\75\1\uffff"+
        "\1\50\1\uffff\6\50\1\40\6\uffff\1\11\2\50\1\150\1\155\1\145\1\141"+
        "\2\157\1\145\2\151\1\157\1\uffff\1\45\7\uffff\1\60\1\45\1\uffff"+
        "\2\55\1\uffff\1\50\1\uffff\1\50\33\uffff\7\50\1\141\1\160\1\144"+
        "\1\147\1\160\1\164\1\146\1\147\1\145\1\156\1\11\11\50\1\162\1\157"+
        "\1\151\1\145\1\55\2\164\1\150\1\167\1\164\1\55\1\11\10\50\1\163"+
        "\1\162\1\141\1\55\1\143\1\157\1\55\1\164\1\160\1\55\1\uffff\10\50"+
        "\1\145\1\164\1\55\1\uffff\2\145\1\151\1\155\1\142\1\55\1\157\1\146"+
        "\10\50\1\164\1\11\1\uffff\1\146\1\156\1\147\1\55\1\157\1\151\1\157"+
        "\1\142\1\162\1\141\5\50\1\11\1\162\1\uffff\2\164\1\150\1\143\1\160"+
        "\1\144\1\164\1\157\1\151\1\157\1\164\1\143\2\50\1\uffff\1\154\1"+
        "\55\1\145\1\164\2\145\1\151\1\55\1\144\1\164\1\160\1\144\1\164\1"+
        "\55\1\145\1\50\1\uffff\1\50\1\143\1\uffff\1\162\1\55\1\146\1\156"+
        "\1\147\1\154\1\157\1\55\1\144\1\164\1\uffff\1\55\1\50\1\157\1\55"+
        "\1\143\2\164\1\150\1\145\1\155\1\154\1\157\2\uffff\1\162\1\157\1"+
        "\55\1\145\1\164\2\55\1\145\1\155\1\uffff\1\156\1\162\1\143\1\162"+
        "\3\55\1\145\1\156\1\157\1\55\1\143\1\162\1\145\1\162\1\157\1\55"+
        "\1\162\1\156\1\162\1\55\1\145\1\156\1\162\1\145\1\55\1\162\1\55";
    static final String DFA44_maxS =
        "\1\uffff\2\ufffd\1\75\11\ufffd\1\156\5\ufffd\1\41\1\156\6\uffff"+
        "\1\57\10\uffff\2\75\1\uffff\1\ufffd\2\75\1\uffff\1\ufffd\1\uffff"+
        "\7\ufffd\6\uffff\3\ufffd\1\150\1\155\1\145\1\141\2\157\1\145\2\151"+
        "\1\157\1\uffff\1\ufffd\7\uffff\1\71\1\ufffd\1\uffff\2\ufffd\1\uffff"+
        "\1\ufffd\1\uffff\1\ufffd\33\uffff\7\ufffd\1\141\1\160\1\144\1\147"+
        "\1\160\1\164\1\146\1\147\1\145\1\156\1\71\11\ufffd\1\162\1\157\1"+
        "\151\1\145\1\55\2\164\1\150\1\167\1\164\12\ufffd\1\163\1\162\1\141"+
        "\1\ufffd\1\162\1\157\1\55\1\164\1\160\1\55\1\uffff\10\ufffd\1\145"+
        "\1\164\1\ufffd\1\uffff\2\145\1\151\1\155\1\164\1\55\1\157\1\146"+
        "\10\ufffd\1\164\1\165\1\uffff\1\146\1\156\1\147\1\55\1\157\1\151"+
        "\1\157\1\164\1\162\1\141\5\ufffd\1\47\1\162\1\uffff\2\164\1\150"+
        "\1\162\1\160\1\144\1\164\1\157\1\151\1\157\1\164\1\143\2\ufffd\1"+
        "\uffff\1\154\1\ufffd\1\145\1\164\2\145\1\151\1\ufffd\1\144\1\164"+
        "\1\160\1\144\1\164\1\ufffd\1\145\1\ufffd\1\uffff\1\50\1\143\1\uffff"+
        "\1\162\1\ufffd\1\146\1\156\1\147\1\154\1\157\1\ufffd\1\144\1\164"+
        "\1\uffff\2\ufffd\1\157\1\ufffd\1\143\2\164\1\150\1\145\1\155\1\154"+
        "\1\157\2\uffff\1\162\1\157\1\ufffd\1\145\1\164\2\ufffd\1\145\1\155"+
        "\1\uffff\1\156\1\162\1\143\1\162\3\ufffd\1\145\1\156\1\157\1\ufffd"+
        "\1\143\1\162\1\145\1\162\1\157\1\ufffd\1\162\1\156\1\162\1\ufffd"+
        "\1\145\1\156\1\162\1\145\1\ufffd\1\162\1\ufffd";
    static final String DFA44_acceptS =
        "\25\uffff\1\30\1\31\1\32\1\33\1\34\1\35\1\uffff\1\37\1\41\1\42\1"+
        "\45\1\46\1\47\1\50\1\51\2\uffff\1\56\3\uffff\1\70\1\uffff\1\4\7"+
        "\uffff\1\62\1\2\1\17\1\65\1\3\1\20\15\uffff\1\14\1\uffff\1\15\1"+
        "\44\1\16\1\43\1\27\1\53\1\21\2\uffff\1\22\2\uffff\1\23\1\uffff\1"+
        "\25\1\uffff\1\26\1\40\1\54\1\30\1\31\1\32\1\33\1\34\1\35\1\57\1"+
        "\60\1\36\1\37\1\41\1\42\1\45\1\46\1\47\1\50\1\51\1\63\1\52\1\67"+
        "\1\55\1\56\1\64\1\66\71\uffff\1\24\13\uffff\1\10\22\uffff\1\7\21"+
        "\uffff\1\6\16\uffff\1\5\20\uffff\1\1\2\uffff\1\11\12\uffff\1\12"+
        "\14\uffff\1\13\1\61\11\uffff\1\61\34\uffff";
    static final String DFA44_specialS =
        "\1\0\u0140\uffff}>";
    static final String[] DFA44_transitionS = {
            "\11\52\2\46\1\52\2\46\22\52\1\46\1\43\1\13\1\2\1\51\1\31\1\52"+
            "\1\14\1\37\1\40\1\45\1\24\1\27\1\15\1\12\1\33\12\16\1\26\1\25"+
            "\1\23\1\32\1\34\1\30\1\11\15\22\1\5\6\22\1\20\5\22\1\41\1\10"+
            "\1\42\1\3\1\6\1\52\4\47\1\21\3\47\1\1\4\47\1\4\6\47\1\17\5\47"+
            "\1\35\1\50\1\36\1\44\1\52\ud780\7\u0800\52\u1ffe\7\2\52",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\14\55\1\53\15\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "\1\66\2\uffff\12\66\7\uffff\32\66\1\uffff\1\66\2\uffff\1\66"+
            "\1\uffff\32\66\5\uffff\ud780\66\u0800\uffff\u1ffe\66",
            "\1\67",
            "\2\71\1\uffff\2\71\22\uffff\1\71\7\uffff\1\64\2\uffff\1\71"+
            "\1\uffff\1\72\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63\2\uffff"+
            "\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe\62",
            "\2\71\1\uffff\2\71\22\uffff\1\71\7\uffff\1\64\2\uffff\1\71"+
            "\1\uffff\1\72\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63\2\uffff"+
            "\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\20\74\12\73\7\74\6\73\32\74\6\73\30\74\1\uffff\ud780\74\u0800"+
            "\uffff\u1ffe\74",
            "\1\107\23\uffff\32\107\1\uffff\1\107\2\uffff\1\107\1\uffff"+
            "\1\107\1\102\1\75\2\107\1\106\2\107\1\76\2\107\1\103\1\77\2"+
            "\107\1\100\1\107\1\104\1\107\1\101\1\107\1\105\4\107\5\uffff"+
            "\ud780\107\u0800\uffff\u1ffe\107",
            "\12\110\7\uffff\32\111\1\uffff\1\111\2\uffff\1\111\1\uffff"+
            "\32\111\5\uffff\ud780\111\u0800\uffff\u1ffe\111",
            "\1\113\26\uffff\137\113\1\uffff\ud780\113\u0800\uffff\u1ffe"+
            "\113",
            "\1\113\26\uffff\137\113\1\uffff\ud780\113\u0800\uffff\u1ffe"+
            "\113",
            "\1\115\2\uffff\12\71\24\uffff\1\71\37\uffff\1\71",
            "\1\122\10\uffff\1\120\1\uffff\12\121\7\uffff\15\125\1\124\14"+
            "\125\1\uffff\1\125\2\uffff\1\125\1\uffff\15\125\1\123\14\125"+
            "\5\uffff\ud780\125\u0800\uffff\u1ffe\125",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\21\55\1\126\10\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "\1\64\2\uffff\1\127\1\uffff\1\60\2\uffff\12\57\7\uffff\32\56"+
            "\1\uffff\1\63\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\27\55\1\130\2\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\1\131",
            "\12\71\24\uffff\1\71\37\uffff\1\71",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\142\4\uffff\1\143",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\155",
            "\1\157",
            "",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\1\162",
            "\1\163",
            "",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\17\55\1\164\12\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\20\166\12\165\7\166\6\165\32\166\6\165\30\166\1\uffff\ud780"+
            "\166\u0800\uffff\u1ffe\166",
            "",
            "",
            "",
            "",
            "",
            "",
            "\2\71\1\uffff\2\71\22\uffff\1\71\7\uffff\1\64\4\uffff\1\60"+
            "\2\uffff\12\167\7\uffff\32\56\1\uffff\1\63\2\uffff\1\61\1\uffff"+
            "\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\172\7\uffff\6\171\24\56\1\uffff"+
            "\1\63\2\uffff\1\61\1\uffff\6\170\24\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "",
            "\1\122\12\uffff\12\110\7\uffff\32\125\1\uffff\1\125\2\uffff"+
            "\1\125\1\uffff\32\125\5\uffff\ud780\125\u0800\uffff\u1ffe\125",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\110",
            "\1\122\10\uffff\1\120\1\uffff\12\121\7\uffff\15\125\1\124\14"+
            "\125\1\uffff\1\125\2\uffff\1\125\1\uffff\15\125\1\123\14\125"+
            "\5\uffff\ud780\125\u0800\uffff\u1ffe\125",
            "",
            "\1\u0085\2\uffff\12\125\7\uffff\32\125\1\uffff\1\125\2\uffff"+
            "\1\125\1\uffff\32\125\5\uffff\ud780\125\u0800\uffff\u1ffe\125",
            "\1\u0085\2\uffff\12\125\7\uffff\32\125\1\uffff\1\125\2\uffff"+
            "\1\125\1\uffff\32\125\5\uffff\ud780\125\u0800\uffff\u1ffe\125",
            "",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\13\55\1\u0086\16\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\17\55\1\u0087\12\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\16\55\1\u0088\13\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u008b\7\uffff\6\u008a\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u0089\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\1\64\4\uffff\1\60\2\uffff\12\167\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u008e\7\uffff\6\u008d\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u008c\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u008e\7\uffff\6\u008d\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u008c\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u008e\7\uffff\6\u008d\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u008c\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\2\71\1\uffff\2\71\22\uffff\1\71\17\uffff\12\u0099",
            "\1\u009a\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1"+
            "\63\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff"+
            "\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\21\55\1\u009b\10\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\21\55\1\u009c\10\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u009f\7\uffff\6\u009e\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u009d\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u009f\7\uffff\6\u009e\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u009d\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u009f\7\uffff\6\u009e\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u009d\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u00a2\7\uffff\6\u00a1\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u00a0\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u00a2\7\uffff\6\u00a1\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u00a0\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u00a2\7\uffff\6\u00a1\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u00a0\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\u00a3",
            "\1\u00a4",
            "\1\u00a5",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\1\125\2\uffff\12\u0099\7\uffff\32\125\1\uffff\1\125\2\uffff"+
            "\1\125\1\uffff\32\125\5\uffff\ud780\125\u0800\uffff\u1ffe\125",
            "\2\u00ad\1\uffff\2\u00ad\22\uffff\10\u00ad\1\uffff\126\u00ad"+
            "\1\uffff\ud780\u00ad\u0800\uffff\u1ffe\u00ad",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\4\55\1\u00ae\25\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\23\55\1\u00af\6\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u00b2\7\uffff\6\u00b1\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u00b0\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u00b2\7\uffff\6\u00b1\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u00b0\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u00b2\7\uffff\6\u00b1\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u00b0\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u00b5\7\uffff\6\u00b4\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u00b3\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u00b5\7\uffff\6\u00b4\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u00b3\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u00b5\7\uffff\6\u00b4\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u00b3\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "\1\107\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "\1\u00bb\10\uffff\1\u00ba\5\uffff\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\22\55\1\u00c2\7\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\1\u00c3\31\55\5\uffff\ud780\62\u0800\uffff"+
            "\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u00c6\7\uffff\6\u00c5\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u00c4\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u00c6\7\uffff\6\u00c5\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u00c4\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u00c6\7\uffff\6\u00c5\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u00c4\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u00c9\7\uffff\6\u00c8\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u00c7\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u00c9\7\uffff\6\u00c8\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u00c7\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u00c9\7\uffff\6\u00c8\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u00c7\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\u00ca",
            "\1\u00cb",
            "\1\107\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "",
            "\1\u00cd",
            "\1\u00ce",
            "\1\u00cf",
            "\1\u00d0",
            "\1\u00d3\12\uffff\1\u00d2\6\uffff\1\u00d1",
            "\1\u00d4",
            "\1\u00d5",
            "\1\u00d6",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\22\55\1\u00d7\7\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\15\55\1\u00d8\14\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u00db\7\uffff\6\u00da\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u00d9\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u00db\7\uffff\6\u00da\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u00d9\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\u00db\7\uffff\6\u00da\24\56\1"+
            "\uffff\1\63\2\uffff\1\61\1\uffff\6\u00d9\24\55\5\uffff\ud780"+
            "\62\u0800\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\1\u00dc",
            "\2\u00de\1\uffff\2\u00de\22\uffff\1\u00de\1\uffff\1\u00de\4"+
            "\uffff\1\u00de\115\uffff\1\u00dd",
            "",
            "\1\u00df",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "\1\u00e5",
            "\1\u00e8\12\uffff\1\u00e7\6\uffff\1\u00e6",
            "\1\u00e9",
            "\1\u00ea",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\10\55\1\u00eb\21\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\23\55\1\u00ec\6\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "\2\u00ed\1\uffff\2\u00ed\22\uffff\1\u00ed\1\uffff\1\u00ed\4"+
            "\uffff\1\u00ed",
            "\1\u00ee",
            "",
            "\1\u00ef",
            "\1\u00f0",
            "\1\u00f1",
            "\1\u00f3\10\uffff\1\u00f2\5\uffff\1\u00f4",
            "\1\u00f5",
            "\1\u00f6",
            "\1\u00f7",
            "\1\u00f8",
            "\1\u00f9",
            "\1\u00fa",
            "\1\u00fb",
            "\1\u00fc",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\16\55\1\u00fd\13\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff\u1ffe"+
            "\62",
            "",
            "\1\u00ff",
            "\1\u0100\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "\1\u0102",
            "\1\u0103",
            "\1\u0104",
            "\1\u0105",
            "\1\u0106",
            "\1\107\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "\1\u0107",
            "\1\u0108",
            "\1\u0109",
            "\1\u010a",
            "\1\u010b",
            "\1\107\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "\1\u010d",
            "\1\64\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1\63"+
            "\2\uffff\1\61\1\uffff\15\55\1\u010e\14\55\5\uffff\ud780\62\u0800"+
            "\uffff\u1ffe\62",
            "",
            "\1\u00de",
            "\1\u010f",
            "",
            "\1\u0110",
            "\1\u0111\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "\1\u0112",
            "\1\u0113",
            "\1\u0114",
            "\1\u0115",
            "\1\u0116",
            "\1\107\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "\1\u0117",
            "\1\u0118",
            "",
            "\1\107\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "\1\u011a\4\uffff\1\60\2\uffff\12\57\7\uffff\32\56\1\uffff\1"+
            "\63\2\uffff\1\61\1\uffff\32\55\5\uffff\ud780\62\u0800\uffff"+
            "\u1ffe\62",
            "\1\u011b",
            "\1\107\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "\1\u011c",
            "\1\u011d",
            "\1\u011e",
            "\1\u011f",
            "\1\u0120",
            "\1\u0121",
            "\1\u0122",
            "\1\u0123",
            "",
            "",
            "\1\u0125",
            "\1\u0126",
            "\1\u0127\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "\1\u0128",
            "\1\u0129",
            "\1\107\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "\1\107\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "\1\u012a",
            "\1\u012b",
            "",
            "\1\u012c",
            "\1\u012d",
            "\1\u012e",
            "\1\u012f",
            "\1\u0130\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "\1\107\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "\1\107\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "\1\u0131",
            "\1\u0132",
            "\1\u0133",
            "\1\107\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "\1\u0134",
            "\1\u0135",
            "\1\u0136",
            "\1\u0137",
            "\1\u0138",
            "\1\107\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "\1\u0139",
            "\1\u013a",
            "\1\u013b",
            "\1\107\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "\1\u013c",
            "\1\u013d",
            "\1\u013e",
            "\1\u013f",
            "\1\107\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107",
            "\1\u0140",
            "\1\107\2\uffff\12\107\7\uffff\32\107\1\uffff\1\107\2\uffff"+
            "\1\107\1\uffff\32\107\5\uffff\ud780\107\u0800\uffff\u1ffe\107"
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
            return "1:1: Tokens : ( T__99 | T__100 | T__101 | IDENT | CHARSET | IMPORT | MEDIA | PAGE | MARGIN_AREA | VIEWPORT | FONTFACE | ATKEYWORD | CLASSKEYWORD | STRING | HASH | INDEX | NUMBER | PERCENTAGE | DIMENSION | URI | UNIRANGE | CDO | CDC | SEMICOLON | COLON | COMMA | QUESTION | PERCENT | EQUALS | SLASH | GREATER | LESS | LCURLY | RCURLY | APOS | QUOT | LPAREN | RPAREN | LBRACE | RBRACE | EXCLAMATION | TILDE | MINUS | PLUS | ASTERISK | S | COMMENT | SL_COMMENT | EXPRESSION | FUNCTION | INCLUDES | DASHMATCH | STARTSWITH | ENDSWITH | CONTAINS | INVALID_TOKEN );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA44_0 = input.LA(1);

                        s = -1;
                        if ( (LA44_0=='i') ) {s = 1;}

                        else if ( (LA44_0=='#') ) {s = 2;}

                        else if ( (LA44_0=='^') ) {s = 3;}

                        else if ( (LA44_0=='n') ) {s = 4;}

                        else if ( (LA44_0=='N') ) {s = 5;}

                        else if ( (LA44_0=='_') ) {s = 6;}

                        else if ( ((LA44_0>='\u0080' && LA44_0<='\uD7FF')||(LA44_0>='\uE000' && LA44_0<='\uFFFD')) ) {s = 7;}

                        else if ( (LA44_0=='\\') ) {s = 8;}

                        else if ( (LA44_0=='@') ) {s = 9;}

                        else if ( (LA44_0=='.') ) {s = 10;}

                        else if ( (LA44_0=='\"') ) {s = 11;}

                        else if ( (LA44_0=='\'') ) {s = 12;}

                        else if ( (LA44_0=='-') ) {s = 13;}

                        else if ( ((LA44_0>='0' && LA44_0<='9')) ) {s = 14;}

                        else if ( (LA44_0=='u') ) {s = 15;}

                        else if ( (LA44_0=='U') ) {s = 16;}

                        else if ( (LA44_0=='e') ) {s = 17;}

                        else if ( ((LA44_0>='A' && LA44_0<='M')||(LA44_0>='O' && LA44_0<='T')||(LA44_0>='V' && LA44_0<='Z')) ) {s = 18;}

                        else if ( (LA44_0=='<') ) {s = 19;}

                        else if ( (LA44_0=='+') ) {s = 20;}

                        else if ( (LA44_0==';') ) {s = 21;}

                        else if ( (LA44_0==':') ) {s = 22;}

                        else if ( (LA44_0==',') ) {s = 23;}

                        else if ( (LA44_0=='?') ) {s = 24;}

                        else if ( (LA44_0=='%') ) {s = 25;}

                        else if ( (LA44_0=='=') ) {s = 26;}

                        else if ( (LA44_0=='/') ) {s = 27;}

                        else if ( (LA44_0=='>') ) {s = 28;}

                        else if ( (LA44_0=='{') ) {s = 29;}

                        else if ( (LA44_0=='}') ) {s = 30;}

                        else if ( (LA44_0=='(') ) {s = 31;}

                        else if ( (LA44_0==')') ) {s = 32;}

                        else if ( (LA44_0=='[') ) {s = 33;}

                        else if ( (LA44_0==']') ) {s = 34;}

                        else if ( (LA44_0=='!') ) {s = 35;}

                        else if ( (LA44_0=='~') ) {s = 36;}

                        else if ( (LA44_0=='*') ) {s = 37;}

                        else if ( ((LA44_0>='\t' && LA44_0<='\n')||(LA44_0>='\f' && LA44_0<='\r')||LA44_0==' ') ) {s = 38;}

                        else if ( ((LA44_0>='a' && LA44_0<='d')||(LA44_0>='f' && LA44_0<='h')||(LA44_0>='j' && LA44_0<='m')||(LA44_0>='o' && LA44_0<='t')||(LA44_0>='v' && LA44_0<='z')) ) {s = 39;}

                        else if ( (LA44_0=='|') ) {s = 40;}

                        else if ( (LA44_0=='$') ) {s = 41;}

                        else if ( ((LA44_0>='\u0000' && LA44_0<='\b')||LA44_0=='\u000B'||(LA44_0>='\u000E' && LA44_0<='\u001F')||LA44_0=='&'||LA44_0=='`'||LA44_0=='\u007F'||(LA44_0>='\uD800' && LA44_0<='\uDFFF')||(LA44_0>='\uFFFE' && LA44_0<='\uFFFF')) ) {s = 42;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 44, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}