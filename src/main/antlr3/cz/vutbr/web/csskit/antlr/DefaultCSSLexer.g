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
}

DUMMY: '@@dummy@@' ;
