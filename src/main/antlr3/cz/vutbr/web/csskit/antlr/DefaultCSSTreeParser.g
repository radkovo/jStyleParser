tree grammar DefaultCSSTreeParser;

options {
    tokenVocab=DefaultCSSLexer;
    ASTLabelType=CommonTree;
}

import CSSTreeParser;

@header {
package cz.vutbr.web.csskit.antlr;

import cz.vutbr.web.css.MediaQuery;
import cz.vutbr.web.css.RuleList;
import cz.vutbr.web.csskit.antlr.Preparator;
}

@members {
    public void init(Preparator preparator, List<MediaQuery> wrapMedia) {
        gCSSTreeParser.init(preparator, wrapMedia);
    }
    
    public RuleList getRules() {
        return gCSSTreeParser.getRules();
    }
    
    public List<List<MediaQuery>> getImportMedia() {
        return gCSSTreeParser.getImportMedia();
    }
    
    public List<String> getImportPaths() {
        return gCSSTreeParser.getImportPaths();
    }
}

dummy returns [Object o]
    : DUMMY
    ;
