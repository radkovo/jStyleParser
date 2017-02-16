package cz.vutbr.web.csskit.antlr4;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

import cz.vutbr.web.csskit.antlr4.CSSToken.TypeMapper;

public class CSSTokenFactory {

    private final Pair<TokenSource, CharStream> input;
    private final Lexer lexer;
    private final CSSLexerState ls;
    private final TypeMapper typeMapper;


    public CSSTokenFactory(Pair<TokenSource, CharStream> input, Lexer lexer, CSSLexerState ls, Class<? extends Lexer> lexerClass) {
        this.input = input;
        this.lexer = lexer;
        this.ls = ls;
        this.typeMapper = CSSToken.createDefaultTypeMapper(lexerClass);
    }

    public CSSToken make() {
        CSSToken t = new CSSToken(input, lexer._type, lexer._channel, lexer._tokenStartCharIndex, input.b.index() - 1, typeMapper);
        t.setLine(lexer._tokenStartLine);
        t.setText(lexer._text);
        t.setCharPositionInLine(lexer._tokenStartCharPositionInLine);
        t.setBase(((CSSInputStream) input.b).getBase());

        // clone lexer state
        t.setLexerState(new CSSLexerState(ls));
        return t;
    }
}
