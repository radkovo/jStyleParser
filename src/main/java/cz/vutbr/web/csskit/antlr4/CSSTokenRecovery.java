package cz.vutbr.web.csskit.antlr4;

import java.util.Stack;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.slf4j.Logger;

public class CSSTokenRecovery {

    private final Lexer lexer;
    private final CharStream input;
    //    private final Recognizer state;
    private final CSSLexerState ls;
    private final Logger log;
    private final Stack<Integer> expectedToken;

    private boolean eof;

    // tokens
    public static final int APOS = 1;
    public static final int QUOT = 2;
    public static final int RPAREN = 3;
    public static final int RCURLY = 4;
    public static final int IMPORT = 5;
    public static final int CHARSET = 6;
    public static final int STRING = 7;
    public static final int INVALID_STRING = 8;

    private final CSSToken.TypeMapper typeMapper;

    public CSSTokenRecovery(Lexer lexer,
                            CharStream input,
                            CSSLexerState ls,
                            Logger log) {
        this.lexer = lexer;
        this.input = input;
//        this.state = state;
        this.ls = ls;
        this.log = log;
        this.expectedToken = new Stack<Integer>();
        this.eof = false;
        typeMapper = new CSSToken.TypeMapper(CSSTokenRecovery.class, lexer.getClass(),
                "APOS", "QUOT", "RPAREN", "RCURLY", "IMPORT",
                "CHARSET", "STRING", "INVALID_STRING");
    }

    public boolean isAtEof() {
        return eof;
    }
    
    public void expecting(int token) {
        expectedToken.push(token);
    }

    public void end() {
        expectedToken.pop();
    }

    public boolean recover() {
        // there is no special recovery
        if (expectedToken.isEmpty())
            return false;
        int t;
        try {
            t = typeMapper.inverse().get(expectedToken.pop().intValue());
        } catch (NullPointerException e) {
            return false;
        }
        switch (t) {
            case IMPORT:  // IMPORT share recovery rules with CHARSET
            case CHARSET:
                final IntervalSet charsetFollow = new IntervalSet((int) '}', (int) ';');
                consumeUntilBalanced(charsetFollow);
                break;
            case STRING:
                // eat character which should be newline but not EOF
                if (consumeAnyButEOF()) {
                    // set back to uninitialized state
                    ls.quotOpen = false;
                    ls.aposOpen = false;
                    // create invalid string token
                    lexer.setToken((Token) new CSSToken(typeMapper.get(INVALID_STRING), ls, lexer.getClass()));
                    ((CSSToken) lexer.getToken()).setText("INVALID_STRING");
//                    state.token = (Token) new CSSToken(typeMapper.get(INVALID_STRING), ls, lexer.getClass());
//                    state.token.setText("INVALID_STRING");
                }
                // we can't just let parser generate missing
                // single/double quot token
                // because we have not emitted content of string yet!
                // we will fake string token
                else {
                    char enclosing = (ls.quotOpen) ? '"' : '\'';
                    ls.quotOpen = false;
                    ls.aposOpen = false;
                    lexer.setToken((Token) new CSSToken(typeMapper.get(STRING), ls,
                            lexer._tokenStartCharIndex, input.index() - 1,
                            lexer.getClass()));
                    ((CSSToken) lexer.getToken()).setText(

                            input.toString().substring(lexer._tokenStartCharIndex, input.index() - 1)
                                    + enclosing);
                }
                break;
            default:
                return false;
        }
        return true;
    }

    /**
     * Implements Lexer's next token with extra token passing from
     * recovery function
     */
    public Token nextToken() {

        if(lexer._input == null) {
            throw new IllegalStateException("nextToken requires a non-null input stream.");
        } else {
            int tokenStartMarker = lexer._input.mark();

            try {
                Token ttype1;
                label110:
                while(!lexer._hitEOF) {
                    lexer._token = null;
                    lexer._channel = Token.DEFAULT_CHANNEL;
                    lexer._tokenStartCharIndex = lexer._input.index();
                    lexer._tokenStartCharPositionInLine = ((LexerATNSimulator)lexer.getInterpreter()).getCharPositionInLine();
                    lexer._tokenStartLine = ((LexerATNSimulator)lexer.getInterpreter()).getLine();
                    lexer._text = null;


                    do {
                        lexer._type = 0;

                        int ttype;
                        try {
                            ttype = ((LexerATNSimulator)lexer.getInterpreter()).match(lexer._input, lexer._mode);
                        } catch (LexerNoViableAltException var7) {
                            lexer.notifyListeners(var7);
                            lexer.recover(var7);
                            ttype = -3;
                        }

                        if(lexer._input.LA(1) == -1) {
                            lexer._hitEOF = true;
                        }

                        if(lexer._type == 0) {
                            lexer._type = ttype;
                        }

                        if(lexer._type == -3) {
                            continue label110;
                        }
                    } while(lexer._type == -2);

                    if(lexer._token == null) {
                        lexer.emit();
                    }

                    ttype1 = lexer._token;
                    //log.trace("return token: >" + ttype1.getText()+"< " + ttype1.getType());
                    return ttype1;
                }
                // recover from unexpected EOF
                eof = true;
                if (!ls.isBalanced()) {
                    return generateEOFRecover();
                }
                log.trace("lexer state is balanced - emitEOF");
                lexer.emitEOF();
                ttype1 = lexer._token;
                return ttype1;
            } finally {
                lexer._input.release(tokenStartMarker);
            }
        }
    }

    /**
     * Recovers from unexpected EOF by preparing
     * new token
     */
    public CSSToken generateEOFRecover() {

        CSSToken t = null;

        if (ls.aposOpen) {
            ls.aposOpen = false;
            t = new CSSToken(typeMapper.get(APOS), ls, lexer.getClass());
            t.setText("'");
        } else if (ls.quotOpen) {
            ls.quotOpen = false;
            t = new CSSToken(typeMapper.get(QUOT), ls, lexer.getClass());
            t.setText("\"");
        } else if (ls.parenNest != 0) {
            ls.parenNest--;
            t = new CSSToken(typeMapper.get(RPAREN), ls, lexer.getClass());
            t.setText(")");
        } else if (ls.curlyNest != 0) {
            ls.curlyNest--;
            t = new CSSToken(typeMapper.get(RCURLY), ls, lexer.getClass());
            t.setText("}");
        }

        log.debug("Recovering from EOF by {}", t.getText());
        return t;
    }

    /**
     * Eats characters until on from follow is found and Lexer state
     * is balanced at the moment
     */
    private void consumeUntilBalanced(IntervalSet follow) {

        log.debug("Lexer entered consumeUntilBalanced with {} and follow {}",
                ls, follow);

        int c;
        do {
            c = input.LA(1);
            // change apostrophe state
            if (c == '\'' && ls.quotOpen == false) {
                ls.aposOpen = !ls.aposOpen;
            }
            // change quot state
            else if (c == '"' && ls.aposOpen == false) {
                ls.quotOpen = !ls.quotOpen;
            } else if (c == '(') {
                ls.parenNest++;
            } else if (c == ')' && ls.parenNest > 0) {
                ls.parenNest--;
            } else if (c == '{') {
                ls.curlyNest++;
            } else if (c == '}' && ls.curlyNest > 0) {
                ls.curlyNest--;
            }
            // handle end of line in string
            else if (c == '\n') {
                if (ls.quotOpen) ls.quotOpen = false;
                else if (ls.aposOpen) ls.aposOpen = false;
            } else if (c == Token.EOF) {
                log.info("Unexpected EOF during consumeUntilBalanced, EOF not consumed");
                return;
            }

            input.consume();
            // log result
            if (log.isTraceEnabled())
                log.trace("Lexer consumes '{}'({}) until balanced ({}).",
                        new Object[]{
                                Character.toString((char) c),
                                Integer.toString(c),
                                ls});

        } while (!(ls.isBalanced() && follow.contains(c)));
    }

    /**
     * Consumes arbitrary character but EOF
     *
     * @return <code>false</code> if EOF was matched,
     * <code>true</code> otherwise and that character was consumed
     */
    private boolean consumeAnyButEOF() {

        int c = input.LA(1);

        if (c == CharStream.EOF)
            return false;

        if (log.isTraceEnabled())
            log.trace("Lexer consumes '{}' while consumeButEOF",
                    Character.toString((char) c));

        // consume character
        input.consume();
        return true;
    }
}
