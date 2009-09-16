// $ANTLR 3.1.2 /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g 2009-09-16 17:38:12

package cz.vutbr.web.csskit.antlr;

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
    public static final int FUNCTION=46;
    public static final int APOS=77;
    public static final int NAME_CHAR=83;
    public static final int CLASSKEYWORD=48;
    public static final int PSEUDO=13;
    public static final int INVALID_STATEMENT=27;
    public static final int LBRACE=66;
    public static final int ATTRIBUTE=17;
    public static final int INVALID_TOKEN=70;
    public static final int EQUALS=60;
    public static final int NAME_START=82;
    public static final int NUMBER_MACR=74;
    public static final int MEDIA=39;
    public static final int CHARSET=32;
    public static final int NL_CHAR=87;
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
    public static final int ATKEYWORD=40;
    public static final int CHILD=15;
    public static final int INVALID_STRING=23;
    public static final int RULE=10;
    public static final int PERCENT=59;
    public static final int RBRACE=67;
    public static final int W_CHAR=79;
    public static final int PARENBLOCK=8;
    public static final int STRING_MACR=72;
    public static final int W_MACR=75;
    public static final int QUOT=78;
    public static final int DESCENDANT=16;
    public static final int NUMBER=49;
    public static final int URI_CHAR=86;
    public static final int HASH=53;
    public static final int LCURLY=37;
    public static final int SET=18;
    public static final int SEMICOLON=42;
    public static final int NAME_MACR=73;
    public static final int S=29;
    public static final int CDO=30;
    public static final int VALUE=20;
    public static final int MINUS=44;
    public static final int CDC=31;
    public static final int PERCENTAGE=50;
    public static final int T__88=88;
    public static final int INVALID_SELECTOR=24;
    public static final int IMPORTANT=21;
    public static final int URI=52;
    public static final int ESCAPE_CHAR=85;
    public static final int COLON=35;
    public static final int PAGE=34;
    public static final int STRING_CHAR=69;
    public static final int DASHMATCH=64;
    public static final int ADJACENT=14;
    public static final int QUESTION=58;
    public static final int IMPORT_END=22;
    public static final int INVALID_IMPORT=28;
    public static final int INLINESTYLE=5;
    public static final int RCURLY=38;
    public static final int SL_COMMENT=81;
    public static final int IDENT_MACR=71;
    public static final int STRING=68;
    public static final int URI_MACR=76;

        private static Logger log = LoggerFactory.getLogger(CSSLexer.class);
        
        private static SupportedCSS css = CSSFactory.getSupportedCSS();
        
        public static class LexerState {
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
            
            /**
             * Checks whether all pair characters (single and double quotatation marks,
             * curly braces are balanced
    		*/ 
            public boolean isBalanced() {
            	return aposOpen==false && quotOpen==false && curlyNest==0 && parenNest==0;
            }
            
            public boolean isFunctionBalanced() {
            	return parenNest==0;
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
    	 * Adds contextual information about nesting into token.
    	 */
        @Override
    	public Token emit() {
    		CSSToken t = new CSSToken(input, state.type, state.channel,
                            state.tokenStartCharIndex, getCharIndex()-1);
            t.setLine(state.tokenStartLine);
            t.setText(state.text);
            t.setCharPositionInLine(state.tokenStartCharPositionInLine);
            
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

    // $ANTLR start "T__88"
    public final void mT__88() throws RecognitionException {
        try {
            int _type = T__88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:431:7: ( 'important' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:431:9: 'important'
            {
            match("important"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__88"

    // $ANTLR start "IDENT"
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:926:2: ( IDENT_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:926:4: IDENT_MACR
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:937:2: ( '@charset' ( S )* s= STRING_MACR ( S )* SEMICOLON )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:937:4: '@charset' ( S )* s= STRING_MACR ( S )* SEMICOLON
            {
            match("@charset"); 

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:937:15: ( S )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\t' && LA1_0<='\n')||(LA1_0>='\f' && LA1_0<='\r')||LA1_0==' ') ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:937:15: S
            	    {
            	    mS(); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            int sStart69 = getCharIndex();
            mSTRING_MACR(); 
            s = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, sStart69, getCharIndex()-1);
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:937:32: ( S )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='\t' && LA2_0<='\n')||(LA2_0>='\f' && LA2_0<='\r')||LA2_0==' ') ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:937:32: S
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
            	    try {
                    	String defaultEnc = Charset.defaultCharset().name();
                        if(!enc.equalsIgnoreCase(defaultEnc) && Charset.isSupported(enc)) {
                        	log.warn("Should change encoding to \"{}\"", enc);
                          			
                          	// FIXME how to solve string and not file inputs?
                          	// we can't just easily create new stream
                          	// how to avoid infinite loop on changing stream
                        	//input = setCharStream(new ANTLFileStream(input.getSourceName(), enc));
                        }
                        // charset already set
                        else {
                        	log.info("Already using correct charset (\"{}\") for stylesheet", enc);
                        }
                    }
                    catch(IllegalCharsetNameException icne) {
                    	log.warn("Could not change to unsupported charset!", icne);
                    	throw new RuntimeException(new CSSException("Unsupported charset: " + enc));
                    }
            	  

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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:972:2: ( '@import' ( S )* (s= STRING_MACR | s= URI ) ( S )* (m= IDENT_MACR ( S )* ( ',' ( S )* m= IDENT_MACR ( S )* )* )? SEMICOLON )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:972:4: '@import' ( S )* (s= STRING_MACR | s= URI ) ( S )* (m= IDENT_MACR ( S )* ( ',' ( S )* m= IDENT_MACR ( S )* )* )? SEMICOLON
            {
            match("@import"); 

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:972:14: ( S )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='\t' && LA3_0<='\n')||(LA3_0>='\f' && LA3_0<='\r')||LA3_0==' ') ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:972:14: S
            	    {
            	    mS(); 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:973:4: (s= STRING_MACR | s= URI )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:973:5: s= STRING_MACR
                    {
                    int sStart114 = getCharIndex();
                    mSTRING_MACR(); 
                    s = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, sStart114, getCharIndex()-1);
                     s.setType(STRING);

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:974:7: s= URI
                    {
                    int sStart127 = getCharIndex();
                    mURI(); 
                    s = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, sStart127, getCharIndex()-1);
                    s.setType(URI);

                    }
                    break;

            }

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:974:33: ( S )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\t' && LA5_0<='\n')||(LA5_0>='\f' && LA5_0<='\r')||LA5_0==' ') ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:974:33: S
            	    {
            	    mS(); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:975:6: (m= IDENT_MACR ( S )* ( ',' ( S )* m= IDENT_MACR ( S )* )* )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0>='A' && LA10_0<='Z')||LA10_0=='\\'||LA10_0=='_'||(LA10_0>='a' && LA10_0<='z')||(LA10_0>='\u0080' && LA10_0<='\uD7FF')||(LA10_0>='\uE000' && LA10_0<='\uFFFD')) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:975:7: m= IDENT_MACR ( S )* ( ',' ( S )* m= IDENT_MACR ( S )* )*
                    {
                    int mStart143 = getCharIndex();
                    mIDENT_MACR(); 
                    m = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, mStart143, getCharIndex()-1);
                     
                    	        mText = m.getText();
                    	    	if(css.isSupportedMedia(mText)) 
                    	    		media.append(mText); 
                    	    	else
                    	    	    log.debug("Invalid import media \"{}\"", mText);
                    	     
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:982:7: ( S )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='\t' && LA6_0<='\n')||(LA6_0>='\f' && LA6_0<='\r')||LA6_0==' ') ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:982:7: S
                    	    {
                    	    mS(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:983:9: ( ',' ( S )* m= IDENT_MACR ( S )* )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==',') ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:983:10: ',' ( S )* m= IDENT_MACR ( S )*
                    	    {
                    	    match(','); 
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:983:14: ( S )*
                    	    loop7:
                    	    do {
                    	        int alt7=2;
                    	        int LA7_0 = input.LA(1);

                    	        if ( ((LA7_0>='\t' && LA7_0<='\n')||(LA7_0>='\f' && LA7_0<='\r')||LA7_0==' ') ) {
                    	            alt7=1;
                    	        }


                    	        switch (alt7) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:983:14: S
                    	    	    {
                    	    	    mS(); 

                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop7;
                    	        }
                    	    } while (true);

                    	    int mStart174 = getCharIndex();
                    	    mIDENT_MACR(); 
                    	    m = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, mStart174, getCharIndex()-1);
                    	     
                    	    	         mText = m.getText();
                    	    	       	 if(css.isSupportedMedia(mText)) 
                    	    	       	 		media.append(",").append(mText);
                    	    	       	 else
                    	    	    	    log.debug("Invalid import media \"{}\"", mText);		
                    	    	       	
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:990:9: ( S )*
                    	    loop8:
                    	    do {
                    	        int alt8=2;
                    	        int LA8_0 = input.LA(1);

                    	        if ( ((LA8_0>='\t' && LA8_0<='\n')||(LA8_0>='\f' && LA8_0<='\r')||LA8_0==' ') ) {
                    	            alt8=1;
                    	        }


                    	        switch (alt8) {
                    	    	case 1 :
                    	    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:990:9: S
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
                          	    url = new URL(base, fileName);
                          	else
                          	{
                          	    log.warn("Base URL is unknown");
                          	    url = new URL(fileName);
                          	}
                          	               			
                          	log.debug("Actually, will try to import file \"{}\"", url.toString());	
                          			
                            // save current lexer's stream
                            LexerStream stream = new LexerStream(input, ls);
                            imports.push(stream);
                                	
                            CSSToken t = new CSSToken(IMPORT, ls);
                            t.setText(media.toString());
                                	
                            // switch on new stream
                            setCharStream(CSSInputStream.urlStream(url));
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1054:2: ( '@media' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1054:4: '@media'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1058:2: ( '@page' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1058:4: '@page'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1063:2: ( '@' IDENT_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1063:4: '@' IDENT_MACR
            {
            match('@'); 
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1067:5: ( '.' IDENT_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1067:7: '.' IDENT_MACR
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1078:2: ( STRING_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1078:4: STRING_MACR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1083:2: ( '#' NAME_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1083:4: '#' NAME_MACR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1088:2: ( NUMBER_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1088:4: NUMBER_MACR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1093:2: ( NUMBER_MACR '%' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1093:4: NUMBER_MACR '%'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1098:2: ( NUMBER_MACR IDENT_MACR )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1098:4: NUMBER_MACR IDENT_MACR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1103:2: ( 'url(' W_MACR ( STRING_MACR | URI_MACR ) W_MACR ')' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1103:4: 'url(' W_MACR ( STRING_MACR | URI_MACR ) W_MACR ')'
            {
            match("url("); 

            mW_MACR(); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1103:18: ( STRING_MACR | URI_MACR )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='\"'||LA11_0=='\'') ) {
                alt11=1;
            }
            else if ( ((LA11_0>='\t' && LA11_0<='\n')||(LA11_0>='\f' && LA11_0<='\r')||(LA11_0>=' ' && LA11_0<='!')||(LA11_0>='#' && LA11_0<='&')||(LA11_0>=')' && LA11_0<='~')||(LA11_0>='\u0080' && LA11_0<='\uD7FF')||(LA11_0>='\uE000' && LA11_0<='\uFFFD')) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1103:19: STRING_MACR
                    {
                    mSTRING_MACR(); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1103:33: URI_MACR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1107:9: ( 'U+' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) )? ( '-' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )? )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1108:2: 'U+' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) )? ( '-' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )?
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1112:7: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>='0' && LA12_0<='9')||LA12_0=='?'||(LA12_0>='A' && LA12_0<='F')||(LA12_0>='a' && LA12_0<='f')) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1112:8: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '?' )
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

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1113:2: ( '-' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0=='-') ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1113:3: '-' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )?
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

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1118:14: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( ((LA13_0>='0' && LA13_0<='9')||(LA13_0>='A' && LA13_0<='F')||(LA13_0>='a' && LA13_0<='f')) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1118:15: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1124:2: ( '<!--' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1124:4: '<!--'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1129:2: ( '-->' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1129:4: '-->'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1133:2: ( ';' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1133:4: ';'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1137:2: ( ':' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1137:4: ':'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1141:5: ( ',' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1141:7: ','
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1145:2: ( '?' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1145:4: '?'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1149:2: ( '%' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1149:4: '%'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1153:5: ( '=' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1153:7: '='
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1157:5: ( '/' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1157:7: '/'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1161:5: ( '>' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1161:7: '>'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1165:5: ( '<' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1165:7: '<'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1169:2: ( '{' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1169:4: '{'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1173:2: ( '}' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1173:4: '}'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1177:2: ( '\\'' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1177:4: '\\''
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1181:2: ( '\"' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1181:4: '\"'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1185:2: ( '(' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1185:4: '('
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1189:2: ( ')' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1189:4: ')'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1193:2: ( '[' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1193:4: '['
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1197:2: ( ']' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1197:4: ']'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1201:5: ( '!' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1201:7: '!'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1205:2: ( '-' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1205:4: '-'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1209:2: ( '+' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1209:4: '+'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1213:2: ( '*' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1213:4: '*'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1218:2: ( ( W_CHAR )+ )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1218:4: ( W_CHAR )+
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1218:4: ( W_CHAR )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>='\t' && LA15_0<='\n')||(LA15_0>='\f' && LA15_0<='\r')||LA15_0==' ') ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1218:4: W_CHAR
            	    {
            	    mW_CHAR(); 

            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1222:2: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1222:4: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1222:9: ( options {greedy=false; } : . )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0=='*') ) {
                    int LA16_1 = input.LA(2);

                    if ( (LA16_1=='/') ) {
                        alt16=2;
                    }
                    else if ( ((LA16_1>='\u0000' && LA16_1<='.')||(LA16_1>='0' && LA16_1<='\uFFFF')) ) {
                        alt16=1;
                    }


                }
                else if ( ((LA16_0>='\u0000' && LA16_0<=')')||(LA16_0>='+' && LA16_0<='\uFFFF')) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1222:37: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop16;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1226:2: ( '//' ( options {greedy=false; } : . )* ( '\\n' | '\\r' ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1226:4: '//' ( options {greedy=false; } : . )* ( '\\n' | '\\r' )
            {
            match("//"); 

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1226:9: ( options {greedy=false; } : . )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0=='\n'||LA17_0=='\r') ) {
                    alt17=2;
                }
                else if ( ((LA17_0>='\u0000' && LA17_0<='\t')||(LA17_0>='\u000B' && LA17_0<='\f')||(LA17_0>='\u000E' && LA17_0<='\uFFFF')) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1226:37: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop17;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1231:3: ( 'expression(' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1231:5: 'expression('
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1236:2: ( IDENT_MACR '(' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1236:4: IDENT_MACR '('
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1240:2: ( '~=' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1240:4: '~='
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1244:2: ( '|=' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1244:4: '|='
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1248:2: ( . )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1248:4: .
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1257:4: ( NAME_START ( NAME_CHAR )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1257:6: NAME_START ( NAME_CHAR )*
            {
            mNAME_START(); 
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1257:17: ( NAME_CHAR )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0=='-'||(LA18_0>='0' && LA18_0<='9')||(LA18_0>='A' && LA18_0<='Z')||LA18_0=='\\'||LA18_0=='_'||(LA18_0>='a' && LA18_0<='z')||(LA18_0>='\u0080' && LA18_0<='\uD7FF')||(LA18_0>='\uE000' && LA18_0<='\uFFFD')) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1257:17: NAME_CHAR
            	    {
            	    mNAME_CHAR(); 

            	    }
            	    break;

            	default :
            	    break loop18;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1262:3: ( ( NAME_CHAR )+ )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1262:5: ( NAME_CHAR )+
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1262:5: ( NAME_CHAR )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0=='-'||(LA19_0>='0' && LA19_0<='9')||(LA19_0>='A' && LA19_0<='Z')||LA19_0=='\\'||LA19_0=='_'||(LA19_0>='a' && LA19_0<='z')||(LA19_0>='\u0080' && LA19_0<='\uD7FF')||(LA19_0>='\uE000' && LA19_0<='\uFFFD')) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1262:5: NAME_CHAR
            	    {
            	    mNAME_CHAR(); 

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


            }

        }
        finally {
        }
    }
    // $ANTLR end "NAME_MACR"

    // $ANTLR start "NAME_START"
    public final void mNAME_START() throws RecognitionException {
        try {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1267:4: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' | NON_ASCII | ESCAPE_CHAR ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1267:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | NON_ASCII | ESCAPE_CHAR )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1267:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | NON_ASCII | ESCAPE_CHAR )
            int alt20=5;
            int LA20_0 = input.LA(1);

            if ( ((LA20_0>='a' && LA20_0<='z')) ) {
                alt20=1;
            }
            else if ( ((LA20_0>='A' && LA20_0<='Z')) ) {
                alt20=2;
            }
            else if ( (LA20_0=='_') ) {
                alt20=3;
            }
            else if ( ((LA20_0>='\u0080' && LA20_0<='\uD7FF')||(LA20_0>='\uE000' && LA20_0<='\uFFFD')) ) {
                alt20=4;
            }
            else if ( (LA20_0=='\\') ) {
                alt20=5;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1267:7: 'a' .. 'z'
                    {
                    matchRange('a','z'); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1267:18: 'A' .. 'Z'
                    {
                    matchRange('A','Z'); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1267:29: '_'
                    {
                    match('_'); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1267:35: NON_ASCII
                    {
                    mNON_ASCII(); 

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1267:47: ESCAPE_CHAR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1272:4: ( ( '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1272:6: ( '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1277:3: ( ( '\\\\' ) ( ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? ) | ( '\\u0020' .. '\\u007E' | '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' ) ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1277:5: ( '\\\\' ) ( ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? ) | ( '\\u0020' .. '\\u007E' | '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' ) )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1277:5: ( '\\\\' )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1277:6: '\\\\'
            {
            match('\\'); 

            }

            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1278:5: ( ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? ) | ( '\\u0020' .. '\\u007E' | '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' ) )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0>='0' && LA22_0<='9')||(LA22_0>='A' && LA22_0<='F')||(LA22_0>='a' && LA22_0<='f')) ) {
                int LA22_1 = input.LA(2);

                if ( ((LA22_1>='0' && LA22_1<='9')||(LA22_1>='A' && LA22_1<='F')||(LA22_1>='a' && LA22_1<='f')) ) {
                    alt22=1;
                }
                else {
                    alt22=2;}
            }
            else if ( ((LA22_0>=' ' && LA22_0<='/')||(LA22_0>=':' && LA22_0<='@')||(LA22_0>='G' && LA22_0<='`')||(LA22_0>='g' && LA22_0<='~')||(LA22_0>='\u0080' && LA22_0<='\uD7FF')||(LA22_0>='\uE000' && LA22_0<='\uFFFD')) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1279:7: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1279:7: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )? )
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1279:8: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )?
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

                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1283:8: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( ((LA21_0>='0' && LA21_0<='9')||(LA21_0>='A' && LA21_0<='F')||(LA21_0>='a' && LA21_0<='f')) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1283:9: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1286:7: ( '\\u0020' .. '\\u007E' | '\\u0080' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1292:4: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | NON_ASCII | ESCAPE_CHAR ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1292:6: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | NON_ASCII | ESCAPE_CHAR )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1292:6: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | NON_ASCII | ESCAPE_CHAR )
            int alt23=7;
            int LA23_0 = input.LA(1);

            if ( ((LA23_0>='a' && LA23_0<='z')) ) {
                alt23=1;
            }
            else if ( ((LA23_0>='A' && LA23_0<='Z')) ) {
                alt23=2;
            }
            else if ( ((LA23_0>='0' && LA23_0<='9')) ) {
                alt23=3;
            }
            else if ( (LA23_0=='-') ) {
                alt23=4;
            }
            else if ( (LA23_0=='_') ) {
                alt23=5;
            }
            else if ( ((LA23_0>='\u0080' && LA23_0<='\uD7FF')||(LA23_0>='\uE000' && LA23_0<='\uFFFD')) ) {
                alt23=6;
            }
            else if ( (LA23_0=='\\') ) {
                alt23=7;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1292:7: 'a' .. 'z'
                    {
                    matchRange('a','z'); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1292:18: 'A' .. 'Z'
                    {
                    matchRange('A','Z'); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1292:29: '0' .. '9'
                    {
                    matchRange('0','9'); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1292:40: '-'
                    {
                    match('-'); 

                    }
                    break;
                case 5 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1292:46: '_'
                    {
                    match('_'); 

                    }
                    break;
                case 6 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1292:52: NON_ASCII
                    {
                    mNON_ASCII(); 

                    }
                    break;
                case 7 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1292:64: ESCAPE_CHAR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1297:4: ( ( '0' .. '9' )+ | ( ( '0' .. '9' )* '.' ( '0' .. '9' )+ ) )
            int alt27=2;
            alt27 = dfa27.predict(input);
            switch (alt27) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1297:6: ( '0' .. '9' )+
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1297:6: ( '0' .. '9' )+
                    int cnt24=0;
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( ((LA24_0>='0' && LA24_0<='9')) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1297:7: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt24 >= 1 ) break loop24;
                                EarlyExitException eee =
                                    new EarlyExitException(24, input);
                                throw eee;
                        }
                        cnt24++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1297:20: ( ( '0' .. '9' )* '.' ( '0' .. '9' )+ )
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1297:20: ( ( '0' .. '9' )* '.' ( '0' .. '9' )+ )
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1297:21: ( '0' .. '9' )* '.' ( '0' .. '9' )+
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1297:21: ( '0' .. '9' )*
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( ((LA25_0>='0' && LA25_0<='9')) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1297:22: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop25;
                        }
                    } while (true);

                    match('.'); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1297:37: ( '0' .. '9' )+
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
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1297:38: '0' .. '9'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1302:2: ( QUOT ( STRING_CHAR | APOS )* QUOT | APOS ( STRING_CHAR | QUOT )* APOS )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0=='\"') ) {
                alt30=1;
            }
            else if ( (LA30_0=='\'') ) {
                alt30=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1302:4: QUOT ( STRING_CHAR | APOS )* QUOT
                    {
                    mQUOT(); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1302:9: ( STRING_CHAR | APOS )*
                    loop28:
                    do {
                        int alt28=3;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0=='\t'||(LA28_0>=' ' && LA28_0<='!')||(LA28_0>='#' && LA28_0<='&')||(LA28_0>='*' && LA28_0<='~')||(LA28_0>='\u0080' && LA28_0<='\uD7FF')||(LA28_0>='\uE000' && LA28_0<='\uFFFD')) ) {
                            alt28=1;
                        }
                        else if ( (LA28_0=='\'') ) {
                            alt28=2;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1302:10: STRING_CHAR
                    	    {
                    	    mSTRING_CHAR(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1302:24: APOS
                    	    {
                    	    mAPOS(); 
                    	    ls.aposOpen=false;

                    	    }
                    	    break;

                    	default :
                    	    break loop28;
                        }
                    } while (true);

                    mQUOT(); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1303:4: APOS ( STRING_CHAR | QUOT )* APOS
                    {
                    mAPOS(); 
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1303:9: ( STRING_CHAR | QUOT )*
                    loop29:
                    do {
                        int alt29=3;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0=='\t'||(LA29_0>=' ' && LA29_0<='!')||(LA29_0>='#' && LA29_0<='&')||(LA29_0>='*' && LA29_0<='~')||(LA29_0>='\u0080' && LA29_0<='\uD7FF')||(LA29_0>='\uE000' && LA29_0<='\uFFFD')) ) {
                            alt29=1;
                        }
                        else if ( (LA29_0=='\"') ) {
                            alt29=2;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1303:10: STRING_CHAR
                    	    {
                    	    mSTRING_CHAR(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1303:24: QUOT
                    	    {
                    	    mQUOT(); 
                    	    ls.quotOpen=false;

                    	    }
                    	    break;

                    	default :
                    	    break loop29;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1308:2: ( ( URI_CHAR | ' ' | ( '\\\\' NL_CHAR ) ) )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1308:5: ( URI_CHAR | ' ' | ( '\\\\' NL_CHAR ) )
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1308:5: ( URI_CHAR | ' ' | ( '\\\\' NL_CHAR ) )
            int alt31=3;
            int LA31_0 = input.LA(1);

            if ( (LA31_0=='\\') ) {
                int LA31_1 = input.LA(2);

                if ( (LA31_1=='\n'||(LA31_1>='\f' && LA31_1<='\r')) ) {
                    alt31=3;
                }
                else {
                    alt31=1;}
            }
            else if ( (LA31_0=='\t'||LA31_0=='!'||(LA31_0>='#' && LA31_0<='&')||(LA31_0>='*' && LA31_0<='[')||(LA31_0>=']' && LA31_0<='~')||(LA31_0>='\u0080' && LA31_0<='\uD7FF')||(LA31_0>='\uE000' && LA31_0<='\uFFFD')) ) {
                alt31=1;
            }
            else if ( (LA31_0==' ') ) {
                alt31=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1308:6: URI_CHAR
                    {
                    mURI_CHAR(); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1308:17: ' '
                    {
                    match(' '); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1308:23: ( '\\\\' NL_CHAR )
                    {
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1308:23: ( '\\\\' NL_CHAR )
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1308:24: '\\\\' NL_CHAR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1313:2: ( ( URI_CHAR )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1313:4: ( URI_CHAR )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1313:4: ( URI_CHAR )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0=='\t'||LA32_0=='!'||(LA32_0>='#' && LA32_0<='&')||(LA32_0>='*' && LA32_0<='~')||(LA32_0>='\u0080' && LA32_0<='\uD7FF')||(LA32_0>='\uE000' && LA32_0<='\uFFFD')) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1313:4: URI_CHAR
            	    {
            	    mURI_CHAR(); 

            	    }
            	    break;

            	default :
            	    break loop32;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1318:2: ( ( '\\u0009' | '\\u0021' | '\\u0023' .. '\\u0026' | '\\u002A' .. '\\u007E' ) | NON_ASCII | ESCAPE_CHAR )
            int alt33=3;
            int LA33_0 = input.LA(1);

            if ( (LA33_0=='\\') ) {
                int LA33_1 = input.LA(2);

                if ( ((LA33_1>=' ' && LA33_1<='~')||(LA33_1>='\u0080' && LA33_1<='\uD7FF')||(LA33_1>='\uE000' && LA33_1<='\uFFFD')) ) {
                    alt33=3;
                }
                else {
                    alt33=1;}
            }
            else if ( ((LA33_0>='\u0080' && LA33_0<='\uD7FF')||(LA33_0>='\uE000' && LA33_0<='\uFFFD')) ) {
                alt33=2;
            }
            else if ( (LA33_0=='\t'||LA33_0=='!'||(LA33_0>='#' && LA33_0<='&')||(LA33_0>='*' && LA33_0<='[')||(LA33_0>=']' && LA33_0<='~')) ) {
                alt33=1;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1318:4: ( '\\u0009' | '\\u0021' | '\\u0023' .. '\\u0026' | '\\u002A' .. '\\u007E' )
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
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1319:6: NON_ASCII
                    {
                    mNON_ASCII(); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1319:18: ESCAPE_CHAR
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1324:4: ( '\\u000A' | '\\u000D' '\\u000A' | '\\u000D' | '\\u000C' )
            int alt34=4;
            switch ( input.LA(1) ) {
            case '\n':
                {
                alt34=1;
                }
                break;
            case '\r':
                {
                int LA34_2 = input.LA(2);

                if ( (LA34_2=='\n') ) {
                    alt34=2;
                }
                else {
                    alt34=3;}
                }
                break;
            case '\f':
                {
                alt34=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1324:6: '\\u000A'
                    {
                    match('\n'); 

                    }
                    break;
                case 2 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1324:17: '\\u000D' '\\u000A'
                    {
                    match('\r'); 
                    match('\n'); 

                    }
                    break;
                case 3 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1324:37: '\\u000D'
                    {
                    match('\r'); 

                    }
                    break;
                case 4 :
                    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1324:48: '\\u000C'
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1329:2: ( ( W_CHAR )* )
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1329:4: ( W_CHAR )*
            {
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1329:4: ( W_CHAR )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( ((LA35_0>='\t' && LA35_0<='\n')||(LA35_0>='\f' && LA35_0<='\r')||LA35_0==' ') ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1329:4: W_CHAR
            	    {
            	    mW_CHAR(); 

            	    }
            	    break;

            	default :
            	    break loop35;
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
            // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1334:4: ( '\\u0009' | '\\u000A' | '\\u000C' | '\\u000D' | '\\u0020' )
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
        // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:8: ( T__88 | IDENT | CHARSET | IMPORT | MEDIA | PAGE | ATKEYWORD | CLASSKEYWORD | STRING | HASH | NUMBER | PERCENTAGE | DIMENSION | URI | UNIRANGE | CDO | CDC | SEMICOLON | COLON | COMMA | QUESTION | PERCENT | EQUALS | SLASH | GREATER | LESS | LCURLY | RCURLY | APOS | QUOT | LPAREN | RPAREN | LBRACE | RBRACE | EXCLAMATION | MINUS | PLUS | ASTERISK | S | COMMENT | SL_COMMENT | EXPRESSION | FUNCTION | INCLUDES | DASHMATCH | INVALID_TOKEN )
        int alt36=46;
        alt36 = dfa36.predict(input);
        switch (alt36) {
            case 1 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:10: T__88
                {
                mT__88(); 

                }
                break;
            case 2 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:16: IDENT
                {
                mIDENT(); 

                }
                break;
            case 3 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:22: CHARSET
                {
                mCHARSET(); 

                }
                break;
            case 4 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:30: IMPORT
                {
                mIMPORT(); 

                }
                break;
            case 5 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:37: MEDIA
                {
                mMEDIA(); 

                }
                break;
            case 6 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:43: PAGE
                {
                mPAGE(); 

                }
                break;
            case 7 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:48: ATKEYWORD
                {
                mATKEYWORD(); 

                }
                break;
            case 8 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:58: CLASSKEYWORD
                {
                mCLASSKEYWORD(); 

                }
                break;
            case 9 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:71: STRING
                {
                mSTRING(); 

                }
                break;
            case 10 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:78: HASH
                {
                mHASH(); 

                }
                break;
            case 11 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:83: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 12 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:90: PERCENTAGE
                {
                mPERCENTAGE(); 

                }
                break;
            case 13 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:101: DIMENSION
                {
                mDIMENSION(); 

                }
                break;
            case 14 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:111: URI
                {
                mURI(); 

                }
                break;
            case 15 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:115: UNIRANGE
                {
                mUNIRANGE(); 

                }
                break;
            case 16 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:124: CDO
                {
                mCDO(); 

                }
                break;
            case 17 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:128: CDC
                {
                mCDC(); 

                }
                break;
            case 18 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:132: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 19 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:142: COLON
                {
                mCOLON(); 

                }
                break;
            case 20 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:148: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 21 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:154: QUESTION
                {
                mQUESTION(); 

                }
                break;
            case 22 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:163: PERCENT
                {
                mPERCENT(); 

                }
                break;
            case 23 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:171: EQUALS
                {
                mEQUALS(); 

                }
                break;
            case 24 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:178: SLASH
                {
                mSLASH(); 

                }
                break;
            case 25 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:184: GREATER
                {
                mGREATER(); 

                }
                break;
            case 26 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:192: LESS
                {
                mLESS(); 

                }
                break;
            case 27 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:197: LCURLY
                {
                mLCURLY(); 

                }
                break;
            case 28 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:204: RCURLY
                {
                mRCURLY(); 

                }
                break;
            case 29 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:211: APOS
                {
                mAPOS(); 

                }
                break;
            case 30 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:216: QUOT
                {
                mQUOT(); 

                }
                break;
            case 31 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:221: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 32 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:228: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 33 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:235: LBRACE
                {
                mLBRACE(); 

                }
                break;
            case 34 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:242: RBRACE
                {
                mRBRACE(); 

                }
                break;
            case 35 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:249: EXCLAMATION
                {
                mEXCLAMATION(); 

                }
                break;
            case 36 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:261: MINUS
                {
                mMINUS(); 

                }
                break;
            case 37 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:267: PLUS
                {
                mPLUS(); 

                }
                break;
            case 38 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:272: ASTERISK
                {
                mASTERISK(); 

                }
                break;
            case 39 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:281: S
                {
                mS(); 

                }
                break;
            case 40 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:283: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 41 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:291: SL_COMMENT
                {
                mSL_COMMENT(); 

                }
                break;
            case 42 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:302: EXPRESSION
                {
                mEXPRESSION(); 

                }
                break;
            case 43 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:313: FUNCTION
                {
                mFUNCTION(); 

                }
                break;
            case 44 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:322: INCLUDES
                {
                mINCLUDES(); 

                }
                break;
            case 45 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:331: DASHMATCH
                {
                mDASHMATCH(); 

                }
                break;
            case 46 :
                // /home/burgetr/workspace/CSSParser/src/cz/vutbr/web/csskit/antlr/CSS.g:1:341: INVALID_TOKEN
                {
                mINVALID_TOKEN(); 

                }
                break;

        }

    }


    protected DFA27 dfa27 = new DFA27(this);
    protected DFA36 dfa36 = new DFA36(this);
    static final String DFA27_eotS =
        "\1\uffff\1\3\2\uffff";
    static final String DFA27_eofS =
        "\4\uffff";
    static final String DFA27_minS =
        "\2\56\2\uffff";
    static final String DFA27_maxS =
        "\2\71\2\uffff";
    static final String DFA27_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA27_specialS =
        "\4\uffff}>";
    static final String[] DFA27_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\2\1\uffff\12\1",
            "",
            ""
    };

    static final short[] DFA27_eot = DFA.unpackEncodedString(DFA27_eotS);
    static final short[] DFA27_eof = DFA.unpackEncodedString(DFA27_eofS);
    static final char[] DFA27_min = DFA.unpackEncodedStringToUnsignedChars(DFA27_minS);
    static final char[] DFA27_max = DFA.unpackEncodedStringToUnsignedChars(DFA27_maxS);
    static final short[] DFA27_accept = DFA.unpackEncodedString(DFA27_acceptS);
    static final short[] DFA27_special = DFA.unpackEncodedString(DFA27_specialS);
    static final short[][] DFA27_transition;

    static {
        int numStates = DFA27_transitionS.length;
        DFA27_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA27_transition[i] = DFA.unpackEncodedString(DFA27_transitionS[i]);
        }
    }

    class DFA27 extends DFA {

        public DFA27(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 27;
            this.eot = DFA27_eot;
            this.eof = DFA27_eof;
            this.min = DFA27_min;
            this.max = DFA27_max;
            this.accept = DFA27_accept;
            this.special = DFA27_special;
            this.transition = DFA27_transition;
        }
        public String getDescription() {
            return "1295:1: fragment NUMBER_MACR : ( ( '0' .. '9' )+ | ( ( '0' .. '9' )* '.' ( '0' .. '9' )+ ) );";
        }
    }
    static final String DFA36_eotS =
        "\1\uffff\5\50\3\46\1\74\1\76\1\46\1\102\2\50\1\107\1\111\6\uffff"+
        "\1\122\13\uffff\1\50\2\46\1\uffff\1\50\1\uffff\6\50\2\uffff\1\50"+
        "\1\uffff\2\50\4\71\1\uffff\1\102\6\uffff\1\102\3\uffff\1\50\32\uffff"+
        "\7\50\4\71\5\50\1\60\3\50\4\71\5\50\1\uffff\3\50\3\71\1\u0090\10"+
        "\50\2\71\1\u009b\1\uffff\10\50\2\71\1\uffff\5\50\2\71\1\uffff\1"+
        "\50\1\u00a9\1\uffff\1\71\1\50\1\uffff\1\71\1\50\2\uffff";
    static final String DFA36_eofS =
        "\u00ae\uffff";
    static final String DFA36_minS =
        "\1\0\5\50\1\40\1\101\1\60\2\11\1\55\1\45\2\50\1\41\1\55\6\uffff"+
        "\1\52\13\uffff\1\50\2\75\1\uffff\1\50\1\uffff\6\50\1\40\1\uffff"+
        "\1\50\1\uffff\2\50\1\150\1\155\1\145\1\141\1\uffff\1\45\5\uffff"+
        "\1\60\1\45\3\uffff\1\50\32\uffff\7\50\1\141\1\160\1\144\1\147\5"+
        "\50\1\11\3\50\1\162\1\157\1\151\1\145\5\50\1\uffff\3\50\1\163\1"+
        "\162\1\141\1\55\10\50\1\145\1\164\1\55\1\uffff\10\50\1\164\1\11"+
        "\1\uffff\5\50\1\11\1\162\1\uffff\2\50\1\uffff\1\154\1\50\1\uffff"+
        "\2\50\2\uffff";
    static final String DFA36_maxS =
        "\1\uffff\16\ufffd\1\41\1\55\6\uffff\1\57\13\uffff\1\ufffd\2\75\1"+
        "\uffff\1\ufffd\1\uffff\7\ufffd\1\uffff\1\ufffd\1\uffff\2\ufffd\1"+
        "\150\1\155\1\145\1\141\1\uffff\1\ufffd\5\uffff\1\71\1\ufffd\3\uffff"+
        "\1\ufffd\32\uffff\7\ufffd\1\141\1\160\1\144\1\147\11\ufffd\1\162"+
        "\1\157\1\151\1\145\5\ufffd\1\uffff\3\ufffd\1\163\1\162\1\141\11"+
        "\ufffd\1\145\1\164\1\ufffd\1\uffff\10\ufffd\1\164\1\165\1\uffff"+
        "\5\ufffd\1\47\1\162\1\uffff\2\ufffd\1\uffff\1\154\1\ufffd\1\uffff"+
        "\1\50\1\ufffd\2\uffff";
    static final String DFA36_acceptS =
        "\21\uffff\1\22\1\23\1\24\1\25\1\26\1\27\1\uffff\1\31\1\33\1\34\1"+
        "\37\1\40\1\41\1\42\1\43\1\45\1\46\1\47\3\uffff\1\56\1\uffff\1\2"+
        "\7\uffff\1\53\1\uffff\1\17\6\uffff\1\7\1\uffff\1\10\1\36\1\11\1"+
        "\35\1\12\2\uffff\1\13\1\15\1\14\1\uffff\1\20\1\32\1\21\1\44\1\22"+
        "\1\23\1\24\1\25\1\26\1\27\1\50\1\51\1\30\1\31\1\33\1\34\1\37\1\40"+
        "\1\41\1\42\1\43\1\45\1\46\1\47\1\54\1\55\35\uffff\1\16\22\uffff"+
        "\1\6\12\uffff\1\5\7\uffff\1\4\2\uffff\1\3\2\uffff\1\1\2\uffff\2"+
        "\52";
    static final String DFA36_specialS =
        "\1\0\u00ad\uffff}>";
    static final String[] DFA36_transitionS = {
            "\11\46\2\42\1\46\2\42\22\46\1\42\1\37\1\11\1\13\1\46\1\25\1"+
            "\46\1\12\1\33\1\34\1\41\1\40\1\23\1\20\1\10\1\27\12\14\1\22"+
            "\1\21\1\17\1\26\1\30\1\24\1\7\24\16\1\3\5\16\1\35\1\6\1\36\1"+
            "\46\1\4\1\46\4\43\1\15\3\43\1\1\13\43\1\2\5\43\1\31\1\45\1\32"+
            "\1\44\1\46\ud780\5\u0800\46\u1ffe\5\2\46",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\14\51\1\47\15\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\21\51\1\61\10\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\2\uffff\1\62\1\uffff\1\54\2\uffff\12\53\7\uffff\32\52"+
            "\1\uffff\1\57\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\20\64\12\63\7\64\6\63\32\64\6\63\30\64\1\uffff\ud780\64\u0800"+
            "\uffff\u1ffe\64",
            "\32\71\1\uffff\1\71\2\uffff\1\71\1\uffff\2\71\1\65\5\71\1\66"+
            "\3\71\1\67\2\71\1\70\12\71\5\uffff\ud780\71\u0800\uffff\u1ffe"+
            "\71",
            "\12\72\7\uffff\32\73\1\uffff\1\73\2\uffff\1\73\1\uffff\32\73"+
            "\5\uffff\ud780\73\u0800\uffff\u1ffe\73",
            "\1\75\26\uffff\10\75\2\uffff\125\75\1\uffff\ud780\75\u0800"+
            "\uffff\u1ffe\75",
            "\1\75\26\uffff\10\75\2\uffff\125\75\1\uffff\ud780\75\u0800"+
            "\uffff\u1ffe\75",
            "\1\77\2\uffff\12\77\7\uffff\32\77\1\uffff\1\77\2\uffff\1\77"+
            "\1\uffff\32\77\5\uffff\ud780\77\u0800\uffff\u1ffe\77",
            "\1\104\10\uffff\1\100\1\uffff\12\101\7\uffff\32\103\1\uffff"+
            "\1\103\2\uffff\1\103\1\uffff\32\103\5\uffff\ud780\103\u0800"+
            "\uffff\u1ffe\103",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\27\51\1\105\2\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\106",
            "\1\110",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\120\4\uffff\1\121",
            "",
            "",
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
            "\1\136",
            "\1\137",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\17\51\1\140\12\51\5\uffff\ud780\56\u0800"+
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
            "\20\142\12\141\7\142\6\141\32\142\6\141\30\142\1\uffff\ud780"+
            "\142\u0800\uffff\u1ffe\142",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\13\51\1\143\16\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\146\7\uffff\6\145\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\144\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\152",
            "",
            "\1\104\12\uffff\12\72\7\uffff\32\103\1\uffff\1\103\2\uffff"+
            "\1\103\1\uffff\32\103\5\uffff\ud780\103\u0800\uffff\u1ffe\103",
            "",
            "",
            "",
            "",
            "",
            "\12\72",
            "\1\104\10\uffff\1\100\1\uffff\12\101\7\uffff\32\103\1\uffff"+
            "\1\103\2\uffff\1\103\1\uffff\32\103\5\uffff\ud780\103\u0800"+
            "\uffff\u1ffe\103",
            "",
            "",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\17\51\1\153\12\51\5\uffff\ud780\56\u0800"+
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
            "\2\uffff\1\55\1\uffff\16\51\1\154\13\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\157\7\uffff\6\156\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\155\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\160\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "\1\60\4\uffff\1\54\2\uffff\12\163\7\uffff\6\162\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\161\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\163\7\uffff\6\162\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\161\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\163\7\uffff\6\162\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\161\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\21\51\1\170\10\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\21\51\1\171\10\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\174\7\uffff\6\173\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\172\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\174\7\uffff\6\173\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\172\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\174\7\uffff\6\173\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\172\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\2\175\1\uffff\2\175\22\uffff\10\175\1\uffff\126\175\1\uffff"+
            "\ud780\175\u0800\uffff\u1ffe\175",
            "\1\60\4\uffff\1\54\2\uffff\12\u0080\7\uffff\6\177\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\176\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0080\7\uffff\6\177\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\176\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0080\7\uffff\6\177\24\52\1\uffff"+
            "\1\57\2\uffff\1\55\1\uffff\6\176\24\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\4\51\1\u0085\25\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\23\51\1\u0086\6\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0089\7\uffff\6\u0088\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u0087\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0089\7\uffff\6\u0088\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u0087\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0089\7\uffff\6\u0088\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u0087\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\u008c\7\uffff\6\u008b\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u008a\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u008c\7\uffff\6\u008b\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u008a\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u008c\7\uffff\6\u008b\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u008a\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\71\2\uffff\12\71\7\uffff\32\71\1\uffff\1\71\2\uffff\1\71"+
            "\1\uffff\32\71\5\uffff\ud780\71\u0800\uffff\u1ffe\71",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\22\51\1\u0091\7\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\1\u0092\31\51\5\uffff\ud780\56\u0800\uffff"+
            "\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0095\7\uffff\6\u0094\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u0093\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0095\7\uffff\6\u0094\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u0093\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0095\7\uffff\6\u0094\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u0093\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0098\7\uffff\6\u0097\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u0096\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0098\7\uffff\6\u0097\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u0096\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u0098\7\uffff\6\u0097\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u0096\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\u0099",
            "\1\u009a",
            "\1\71\2\uffff\12\71\7\uffff\32\71\1\uffff\1\71\2\uffff\1\71"+
            "\1\uffff\32\71\5\uffff\ud780\71\u0800\uffff\u1ffe\71",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\22\51\1\u009c\7\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\15\51\1\u009d\14\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u00a0\7\uffff\6\u009f\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u009e\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u00a0\7\uffff\6\u009f\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u009e\24\51\5\uffff\ud780"+
            "\56\u0800\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\u00a0\7\uffff\6\u009f\24\52\1"+
            "\uffff\1\57\2\uffff\1\55\1\uffff\6\u009e\24\51\5\uffff\ud780"+
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
            "\1\u00a1",
            "\2\u00a3\1\uffff\2\u00a3\22\uffff\1\u00a3\1\uffff\1\u00a3\4"+
            "\uffff\1\u00a3\115\uffff\1\u00a2",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\10\51\1\u00a4\21\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\23\51\1\u00a5\6\51\5\uffff\ud780\56\u0800"+
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
            "\2\u00a6\1\uffff\2\u00a6\22\uffff\1\u00a6\1\uffff\1\u00a6\4"+
            "\uffff\1\u00a6",
            "\1\u00a7",
            "",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\16\51\1\u00a8\13\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff\u1ffe"+
            "\56",
            "",
            "\1\u00aa",
            "\1\60\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1\57"+
            "\2\uffff\1\55\1\uffff\15\51\1\u00ab\14\51\5\uffff\ud780\56\u0800"+
            "\uffff\u1ffe\56",
            "",
            "\1\u00a3",
            "\1\u00ac\4\uffff\1\54\2\uffff\12\53\7\uffff\32\52\1\uffff\1"+
            "\57\2\uffff\1\55\1\uffff\32\51\5\uffff\ud780\56\u0800\uffff"+
            "\u1ffe\56",
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
            return "1:1: Tokens : ( T__88 | IDENT | CHARSET | IMPORT | MEDIA | PAGE | ATKEYWORD | CLASSKEYWORD | STRING | HASH | NUMBER | PERCENTAGE | DIMENSION | URI | UNIRANGE | CDO | CDC | SEMICOLON | COLON | COMMA | QUESTION | PERCENT | EQUALS | SLASH | GREATER | LESS | LCURLY | RCURLY | APOS | QUOT | LPAREN | RPAREN | LBRACE | RBRACE | EXCLAMATION | MINUS | PLUS | ASTERISK | S | COMMENT | SL_COMMENT | EXPRESSION | FUNCTION | INCLUDES | DASHMATCH | INVALID_TOKEN );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA36_0 = input.LA(1);

                        s = -1;
                        if ( (LA36_0=='i') ) {s = 1;}

                        else if ( (LA36_0=='u') ) {s = 2;}

                        else if ( (LA36_0=='U') ) {s = 3;}

                        else if ( (LA36_0=='_') ) {s = 4;}

                        else if ( ((LA36_0>='\u0080' && LA36_0<='\uD7FF')||(LA36_0>='\uE000' && LA36_0<='\uFFFD')) ) {s = 5;}

                        else if ( (LA36_0=='\\') ) {s = 6;}

                        else if ( (LA36_0=='@') ) {s = 7;}

                        else if ( (LA36_0=='.') ) {s = 8;}

                        else if ( (LA36_0=='\"') ) {s = 9;}

                        else if ( (LA36_0=='\'') ) {s = 10;}

                        else if ( (LA36_0=='#') ) {s = 11;}

                        else if ( ((LA36_0>='0' && LA36_0<='9')) ) {s = 12;}

                        else if ( (LA36_0=='e') ) {s = 13;}

                        else if ( ((LA36_0>='A' && LA36_0<='T')||(LA36_0>='V' && LA36_0<='Z')) ) {s = 14;}

                        else if ( (LA36_0=='<') ) {s = 15;}

                        else if ( (LA36_0=='-') ) {s = 16;}

                        else if ( (LA36_0==';') ) {s = 17;}

                        else if ( (LA36_0==':') ) {s = 18;}

                        else if ( (LA36_0==',') ) {s = 19;}

                        else if ( (LA36_0=='?') ) {s = 20;}

                        else if ( (LA36_0=='%') ) {s = 21;}

                        else if ( (LA36_0=='=') ) {s = 22;}

                        else if ( (LA36_0=='/') ) {s = 23;}

                        else if ( (LA36_0=='>') ) {s = 24;}

                        else if ( (LA36_0=='{') ) {s = 25;}

                        else if ( (LA36_0=='}') ) {s = 26;}

                        else if ( (LA36_0=='(') ) {s = 27;}

                        else if ( (LA36_0==')') ) {s = 28;}

                        else if ( (LA36_0=='[') ) {s = 29;}

                        else if ( (LA36_0==']') ) {s = 30;}

                        else if ( (LA36_0=='!') ) {s = 31;}

                        else if ( (LA36_0=='+') ) {s = 32;}

                        else if ( (LA36_0=='*') ) {s = 33;}

                        else if ( ((LA36_0>='\t' && LA36_0<='\n')||(LA36_0>='\f' && LA36_0<='\r')||LA36_0==' ') ) {s = 34;}

                        else if ( ((LA36_0>='a' && LA36_0<='d')||(LA36_0>='f' && LA36_0<='h')||(LA36_0>='j' && LA36_0<='t')||(LA36_0>='v' && LA36_0<='z')) ) {s = 35;}

                        else if ( (LA36_0=='~') ) {s = 36;}

                        else if ( (LA36_0=='|') ) {s = 37;}

                        else if ( ((LA36_0>='\u0000' && LA36_0<='\b')||LA36_0=='\u000B'||(LA36_0>='\u000E' && LA36_0<='\u001F')||LA36_0=='$'||LA36_0=='&'||LA36_0=='^'||LA36_0=='`'||LA36_0=='\u007F'||(LA36_0>='\uD800' && LA36_0<='\uDFFF')||(LA36_0>='\uFFFE' && LA36_0<='\uFFFF')) ) {s = 38;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 36, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}