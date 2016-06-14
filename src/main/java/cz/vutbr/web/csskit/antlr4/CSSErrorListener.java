//not generated file
package cz.vutbr.web.csskit.antlr4;


import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.slf4j.LoggerFactory;

import java.util.BitSet;

public class CSSErrorListener extends BaseErrorListener {

    //logger
    private org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
        log.error("CSSErrorListener syntaxError | " + s);
    }

    @Override
    public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {
        log.error("CSSErrorListener ambiguity | ");
    }

    @Override
    public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {
        log.error("CSSErrorListener full context | ");
    }

    @Override
    public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {
        log.error("CSSErrorListener context sensitivity | ");
    }
}
