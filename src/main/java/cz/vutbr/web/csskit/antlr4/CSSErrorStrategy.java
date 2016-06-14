package cz.vutbr.web.csskit.antlr4;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.slf4j.Logger;


public class CSSErrorStrategy extends DefaultErrorStrategy {
    private Logger logger;

    public CSSErrorStrategy() {
        this.logger = org.slf4j.LoggerFactory.getLogger(getClass());
        logger.trace("CssErrorStrategy instantiated");
    }


    public void sync(Parser recognizer) throws RecognitionException {
        ATNState s = recognizer.getInterpreter().atn.states.get(recognizer.getState());
        if (!this.inErrorRecoveryMode(recognizer)) {
            TokenStream tokens = recognizer.getInputStream();
            int la = tokens.LA(1);
            if (!recognizer.getATN().nextTokens(s).contains(la) && la != -1) {
                if (!recognizer.isExpectedToken(la)) {
                    switch (s.getStateType()) {
                        case 3:
                        case 4:
                        case 5:
                        case 10:
                            throw new RecognitionException(recognizer, tokens, recognizer.getContext());
                        case 9:
                        case 11:
                            //added
                            this.reportUnwantedToken(recognizer);
                            throw new RecognitionException(recognizer, tokens, recognizer.getContext());
                        case 6:
                        case 7:
                        case 8:
                        default:
                    }
                }
            }
        }
    }

    /**
     * throws RecognitionException to handle in parser's catch block
     */
    public Token recoverInline(Parser recognizer) throws RecognitionException {
        throw new RecognitionException(recognizer, recognizer.getInputStream(), recognizer.getContext());
    }

    /**
     * Consumes token until lexer state is balanced and
     * token from follow is matched. Matched token is also consumed
     */
    protected void consumeUntilGreedy(Parser recognizer, IntervalSet follow) {
        logger.trace("CONSUME UNTIL GREEDY {}", follow.toString());
        for (int ttype = recognizer.getInputStream().LA(1); ttype != -1 && !follow.contains(ttype); ttype = recognizer.getInputStream().LA(1)) {
            Token t = recognizer.consume();
            logger.trace("Skipped greedy: {}", t.getText());
        }
        Token t = recognizer.consume();
        logger.trace("Skipped greedy: {} follow: {}", t.getText(), follow);

    }

    /**
     * Consumes token until lexer state is function-balanced and
     * token from follow is matched. Matched token is also consumed
     */
    protected void consumeUntilGreedy(Parser recognizer, IntervalSet set, CSSLexerState.RecoveryMode mode) {
        CSSToken t;
        do {
            Token next = recognizer.getInputStream().LT(1);
            if (next instanceof CSSToken) {
                t = (CSSToken) recognizer.getInputStream().LT(1);
                if (t.getType() == Token.EOF) {
                    logger.trace("token eof ");
                    break;
                }
            } else
                break; /* not a CSSToken, probably EOF */
            logger.trace("Skipped greedy: {}", t.getText());
            // consume token even if it will match
            recognizer.consume();
        }
        while (!(t.getLexerState().isBalanced(mode, null, t) && set.contains(t.getType())));
    }

    /**
     * Consumes token until lexer state is function-balanced and
     * token from follow is matched.
     */
    public void consumeUntil(Parser recognizer, IntervalSet follow, CSSLexerState.RecoveryMode mode, CSSLexerState ls) {
        CSSToken t;
        boolean finish;
        TokenStream input = recognizer.getInputStream();
        do {
            Token next = input.LT(1);
            if (next instanceof CSSToken) {
                t = (CSSToken) input.LT(1);
                if (t.getType() == Token.EOF) {
                    logger.trace("token eof ");
                    break;
                }
            } else
                break; /* not a CSSToken, probably EOF */
            // consume token if does not match
            finish = (t.getLexerState().isBalanced(mode, ls, t) && follow.contains(t.getType()));
            if (!finish) {
                logger.trace("Skipped: {}", t);
                input.consume();
            }
        } while (!finish);
    }
}
