package cz.vutbr.web.csskit.antlr4;

import org.antlr.runtime.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.RuleTransition;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.slf4j.Logger;


public class CSSErrorStrategy extends DefaultErrorStrategy {
    private Logger logger;

    public CSSErrorStrategy() {
        this.logger = org.slf4j.LoggerFactory.getLogger(getClass());
        logger.trace("CssErrorStrategy instantiated");
    }


    public void sync(Parser recognizer) throws RecognitionException {
        ATNState s = (ATNState) ((ParserATNSimulator) recognizer.getInterpreter()).atn.states.get(recognizer.getState());
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
//                            if (this.singleTokenDeletion(recognizer) != null) {
                            throw new RecognitionException(recognizer, tokens, recognizer.getContext());
//                                return;
//                            }
//                            throw new InputMismatchException(recognizer);
                        case 9:
                        case 11:
                            //added
                            this.reportUnwantedToken(recognizer);
                            throw new RecognitionException(recognizer, tokens, recognizer.getContext());
//                            IntervalSet expecting = recognizer.getExpectedTokens();
//                            IntervalSet whatFollowsLoopIterationOrRule = expecting.or(this.getErrorRecoverySet(recognizer));
//                            this.consumeUntil(recognizer, whatFollowsLoopIterationOrRule);
                        case 6:
                        case 7:
                        case 8:
                        default:
                    }
                }
            }
        }
    }


    public Token recoverInline(Parser recognizer) throws RecognitionException {
        logger.debug("recoverInline");
        int currentSymbolType = recognizer.getInputStream().LA(1);
//        if(currentSymbolType == -1){
//            if (this.singleTokenInsertion(recognizer)) {
//                return this.getMissingSymbol(recognizer);
//            }
//        }
        logger.debug("recoverInline - throwing exception");
        throw new RecognitionException(recognizer, recognizer.getInputStream(), recognizer.getContext());
        /*
        Token matchedSymbol = this.singleTokenDeletion(recognizer);
        if (matchedSymbol != null) {
            recognizer.consume();
            return matchedSymbol;
        } else if (this.singleTokenInsertion(recognizer)) {
            return this.getMissingSymbol(recognizer);
        } else {
            throw new InputMismatchException(recognizer);
        }*/
    }


    protected void consumeUntil(Parser recognizer, IntervalSet set) {
        logger.debug("CONSUME UNTIL {}", set.toString());
        for (int ttype = recognizer.getInputStream().LA(1); ttype != -1 && !set.contains(ttype); ttype = recognizer.getInputStream().LA(1)) {
            Token t = recognizer.consume();
            logger.debug("CONSUME {}", t.getText());
        }

    }

    protected void consumeUntilGreedy(Parser recognizer, IntervalSet set) {
        logger.debug("CONSUME UNTIL {}", set.toString());
        for (int ttype = recognizer.getInputStream().LA(1); ttype != -1 && !set.contains(ttype); ttype = recognizer.getInputStream().LA(1)) {
            Token t = recognizer.consume();
            logger.debug("CONSUME {}", t.getText());
        }
        Token t = recognizer.consume();
        logger.debug("CONSUME {}", t.getText());

    }

    protected void consumeUntilGreedy(Parser recognizer, IntervalSet set, CSSLexerState.RecoveryMode mode) {
        CSSLexerState ls = null;
        CSSToken t;
        do {
            Token next = recognizer.getInputStream().LT(1);
            if (next instanceof CSSToken) {
                t = (CSSToken) recognizer.getInputStream().LT(1);
                if (t.getType() == Token.EOF) {
                    logger.debug("token eof ");
                    break;
                }
            } else
                break; /* not a CSSToken, probably EOF */
            logger.debug("Skipped greedy: {}", t.getText());
            // consume token even if it will match
            recognizer.consume();
        } while (!(t.getLexerState().isBalanced(mode, ls, t) && this.getErrorRecoverySet(recognizer).contains(t.getType())));
    }

    /**
     * consume tokens until is balanced mode and next is in follow
     * @param recognizer
     * @param follow
     * @param mode
     * @param ls
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
                    logger.debug("token eof ");
                    break;
                }
            } else
                break; /* not a CSSToken, probably EOF */
            // consume token if does not match
            finish = (t.getLexerState().isBalanced(mode, ls, t) && follow.contains(t.getType()));
            if (!finish) {
                logger.debug("Skipped: {}", t);
                input.consume();
            }
        } while (!finish);
    }
}
