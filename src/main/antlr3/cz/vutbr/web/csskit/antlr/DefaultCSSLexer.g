lexer grammar DefaultCSSLexer;

import CSSLexer;

@header {package cz.vutbr.web.csskit.antlr;}

@members {
    public void init() {
        gCSSLexer.init();
    }
    
    @Override
    public Token emit() {
        Token t = gCSSLexer.tf.make();
        emit(t);
        return t;
    }
    
    @Override
    public Token nextToken(){
       Token token = gCSSLexer.tr.nextToken();
       if (token.getType() == S)
           gCSSLexer.tokencnt++;
       if(((CommonToken)token).getStartIndex() < 0)
           token = nextToken();
       return token;
    }
}

DUMMY: '@@dummy@@' ;
