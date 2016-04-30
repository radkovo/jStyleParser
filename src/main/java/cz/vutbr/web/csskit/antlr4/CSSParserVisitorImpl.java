package cz.vutbr.web.csskit.antlr4;

import cz.vutbr.web.css.*;
import cz.vutbr.web.csskit.RuleArrayList;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class CSSParserVisitorImpl implements CSSParserVisitor {
    // factories for building structures
    private RuleFactory rf = CSSFactory.getRuleFactory();
    private TermFactory tf = CSSFactory.getTermFactory();

    private enum MediaQueryState {START, TYPE, AND, EXPR, TYPEOREXPR}

    //logger
    private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
    //counter of spaces for pretty debug printing
    private int spacesCounter = 0;

    // block preparator
    private Preparator preparator;
    private List<MediaQuery> wrapMedia;

    // structures after parsing
    private List<String> importPaths = new ArrayList<>();
    private List<List<MediaQuery>> importMedia = new ArrayList<>();
    private RuleList rules = null;

    //prevent imports inside the style sheet
    private boolean preventImports = false;

    private void logEnter(String entry) {
        log.trace("Enter: " + generateSpaces(spacesCounter) + "{}", entry);
    }

    private void logLeave(String leaving) {
        log.trace("Leave: " + generateSpaces(spacesCounter) + "{}", leaving);
    }

    private String extractTextUnescaped(String text) {
        return org.unbescape.css.CssEscape.unescapeCss(text);
    }

    /**
     * generate spaces for pretty debug printing
     *
     * @param count number of generated spaces
     * @return string with spaces
     */
    private String generateSpaces(int count) {
        String spaces = "";
        for (int i = 0; i < count; i++) {
            spaces += " ";
        }
        return spaces;
    }

    /**
     * Constructor
     *
     * @param preparator The preparator to be used for creating the rules.
     * @param wrapMedia  The media queries to be used for wrapping the created rules (e.g. in case
     *                   of parsing and imported style sheet) or null when no wrapping is required.
     */
    public CSSParserVisitorImpl(Preparator preparator, List<MediaQuery> wrapMedia) {
        this.preparator = preparator;
        this.wrapMedia = wrapMedia;
    }

    //used in parseMediaQuery
    public CSSParserVisitorImpl() {

    }
    /******************************************************************
     /******************************************************************
     /******************************************************************
     /******************************************************************
     /******************************************************************
     /******************************************************************
     /******************************************************************
     /******************************************************************
     /******************************************************************/


    /**
     * @param ctx the parse tree
     * @return Object
     */
    @Override
    public Object visitInlinestyle(CSSParser.InlinestyleContext ctx) {

        return null;
    }

    @Override
    /**
     * Stylesheet, main rule
     */
    public Object visitStylesheet(CSSParser.StylesheetContext ctx) {
        logEnter("stylesheet: " + ctx.getText());
        rules = new RuleArrayList();
        for (CSSParser.StatementContext stmt : ctx.statement()) {
            RuleBlock<?> s = visitStatement(stmt);
            if (s != null) {
                rules.add(s);
            }
        }
        log.debug("\n***\n{}\n***\n", rules);
        logLeave("stylesheet");
        return null;
    }

    @Override
    /**
     * Statement, main contents unit
     */
    public RuleBlock<?> visitStatement(CSSParser.StatementContext ctx) {
        logEnter("statement: " + ctx.getText());
        RuleBlock<?> stmt = null;
        if (ctx.ruleset() != null) {
            stmt = visitRuleset(ctx.ruleset());
        } else if (ctx.atstatement() != null) {
            stmt = visitAtstatement(ctx.atstatement());
        } else {
            log.debug("Statement is invalid");
        }
        logLeave("statement: " + ctx.getText());
        return stmt;
    }

    @Override
    public RuleBlock<?> visitAtstatement(CSSParser.AtstatementContext ctx) {
        logEnter("atstatement: " + ctx.getText());
        RuleBlock<?> atstmt = null;
        if (ctx.CHARSET() != null) {
            //charset set in lexer
        } else if (ctx.IMPORT() != null) {
            List<cz.vutbr.web.css.MediaQuery> im = null;
            if (ctx.media() != null) {
                im = visitMedia(ctx.media());
            }
            String iuri = visitImport_uri(ctx.import_uri());
            if (!this.preventImports) {
                log.debug("Adding import: {}", iuri);
                importMedia.add(im);
                importPaths.add(iuri);
            } else {
                log.debug("Ignoring import: {}", iuri);
            }
        } else if (ctx.page() != null) {
            atstmt = visitPage(ctx.page());
        } else if (ctx.VIEWPORT() != null) {
            List<cz.vutbr.web.css.Declaration> declarations = visitDeclarations(ctx.declarations());
            atstmt = preparator.prepareRuleViewport(declarations);
            this.preventImports = true;
        } else if (ctx.FONTFACE() != null) {
            List<cz.vutbr.web.css.Declaration> declarations = visitDeclarations(ctx.declarations());
            atstmt = preparator.prepareRuleFontFace(declarations);
            this.preventImports = true;
        } else if (ctx.MEDIA() != null) {
            List<cz.vutbr.web.css.MediaQuery> mediaList = null;
            List<cz.vutbr.web.css.RuleSet> rules = null;
            if (ctx.media() != null) {
                mediaList = visitMedia(ctx.media());
            }
            if (ctx.media_rule() != null) {
                rules = new ArrayList<>();
                for (CSSParser.Media_ruleContext mr : ctx.media_rule()) {
                    RuleBlock<?> rs = visitMedia_rule(mr);
                    if (rs != null) {
                        rules.add((RuleSet) rs);
                    }
                }
            }
            atstmt = preparator.prepareRuleMedia(rules, mediaList);
            this.preventImports = true;
        } else {
            log.debug("Skipping invalid at statement");
        }
        logLeave("atstatement");
        return atstmt;
    }

    @Override
    public String visitImport_uri(CSSParser.Import_uriContext ctx) {
        return extractTextUnescaped(ctx.getText());
    }

    @Override
    public RuleBlock<?> visitPage(CSSParser.PageContext ctx) {
        String name = null, pseudo = null;
        if (ctx.IDENT() != null) {
            name = extractTextUnescaped(ctx.IDENT().getText());
        }
        if (ctx.page_pseudo() != null) {
            pseudo = extractTextUnescaped(ctx.page_pseudo().getText());
        }
        List<Declaration> declarations = visitDeclarations(ctx.declarations());
        List<cz.vutbr.web.css.RuleMargin> margins = null;
        if (ctx.margin_rule() != null) {
            margins = new ArrayList<>();
            for (CSSParser.Margin_ruleContext mctx : ctx.margin_rule()) {
                RuleMargin m = visitMargin_rule(mctx);
                margins.add(m);
                log.debug("Inserted margin rule #{} into @page", margins.size() + 1);
            }
        }

        RuleBlock<?> rb = preparator.prepareRulePage(declarations, margins, name, pseudo);
        this.preventImports = true;
        return rb;
    }

    @Override
    public Object visitPage_pseudo(CSSParser.Page_pseudoContext ctx) {
        return null;
    }

    @Override
    public RuleMargin visitMargin_rule(CSSParser.Margin_ruleContext ctx) {
        return null;
    }

    @Override
    public Object visitInlineset(CSSParser.InlinesetContext ctx) {
        return null;
    }

    @Override
    public List<cz.vutbr.web.css.MediaQuery> visitMedia(CSSParser.MediaContext ctx) {
        logEnter("media: " + ctx.getText());
        List<MediaQuery> queries = new ArrayList<>();
        for (CSSParser.Media_queryContext mqc : ctx.media_query()) {
            queries.add(visitMedia_query(mqc));
        }
        log.debug("Totally returned {} media queries.", queries.size());
        logLeave("media: " + ctx.getText());
        return null;
    }

    protected static class mediaquery_scope {
        cz.vutbr.web.css.MediaQuery q;
        MediaQueryState state;
        boolean invalid;
    }

    mediaquery_scope mq;

    @Override
    public MediaQuery visitMedia_query(CSSParser.Media_queryContext ctx) {
        logEnter("mediaquery: " + ctx.getText());
        mq = new mediaquery_scope();
        mq.q = rf.createMediaQuery();
        mq.q.unlock();
        mq.state = MediaQueryState.START;
        mq.invalid = false;
        logLeave("mediaquery: " + ctx.getText());
        for (CSSParser.Media_termContext mtc : ctx.media_term()) {
            visitMedia_term(mtc);
        }
        if (mq.invalid) {
            log.trace("Skipping invalid rule {}", mq.q);
            mq.q.setType("all"); //change the malformed media queries to "not all"
            mq.q.setNegative(true);
        }
        logLeave("mediaquery");
        return null;
    }

    @Override
    public Object visitMedia_term(CSSParser.Media_termContext ctx) {
        if (ctx.IDENT() != null) {
            String m = extractTextUnescaped(i);
            MediaQueryState state = mq.state;
            if (m.equalsIgnoreCase("ONLY") && state == MediaQueryState.START)
            {
                mq.state = MediaQueryState.TYPEOREXPR;
            }
            else if (m.equalsIgnoreCase("NOT") && state == MediaQueryState.START)
            {
                mq.q.setNegative(true);
                mq.state = MediaQueryState.TYPEOREXPR;
            }
            else if (m.equalsIgnoreCase("AND") && state == MediaQueryState.AND)
            {
                mq.state = MediaQueryState.EXPR;
            }
            else if (state == MediaQueryState.START
                    || state == MediaQueryState.TYPE
                    || state == MediaQueryState.TYPEOREXPR)
            {
                mq.q.setType(m);
                mq.state = MediaQueryState.AND;
            }
            else
            {
                log.trace("Invalid media query: found ident: {} state: {}", m, state);
                mq.invalid = true;
            }
        }
        else if(ctx.media_expression() != null){
            MediaExpression e = visitMedia_expression(ctx.media_expression());
            if (mq.state == MediaQueryState.START
                    || mq.state == MediaQueryState.EXPR
                    || mq.state == MediaQueryState.TYPEOREXPR)
            {
                if (e.getFeature() != null) //the expression is valid
                {
                    mq.q.add(e);
                    mq.state = MediaQueryState.AND;
                }
                else
                {
                    log.trace("Invalidating media query for invalud expression");
                    mq.invalid = true;
                }
            }
            else
            {
                log.trace("Invalid media query: found expr, state: {}", mq.state);
                mq.invalid = true;
            }
        }
        else{
            mq.invalid = true;
        }
        return null;
    }

    @Override
    public MediaExpression visitMedia_expression(CSSParser.Media_expressionContext ctx) {
        logEnter("mediaexpression: " + ctx.getText());
        MediaExpression expr = rf.createMediaExpression();
        //todo:

        logLeave("mediaexpression: " + ctx.getText());
        return null;
    }

    @Override
    public RuleBlock<?> visitMedia_rule(CSSParser.Media_ruleContext ctx) {
        logEnter("media_rule: " + ctx.getText());
        RuleBlock<?> rules = null;
        if (ctx.ruleset() != null) {
            rules = visitRuleset(ctx.ruleset());
        } else {
            log.debug("Skiping invalid statement in media");
        }
        logLeave("media_rule: " + ctx.getText());
        return rules;
    }

    @Override
    public Object visitUnknown_atrule(CSSParser.Unknown_atruleContext ctx) {
        //done in atstatement else section
        return null;
    }

    @Override
    public RuleBlock<?> visitRuleset(CSSParser.RulesetContext ctx) {
        return null;
    }

    @Override
    public List<cz.vutbr.web.css.Declaration> visitDeclarations(CSSParser.DeclarationsContext ctx) {
        return null;
    }

    @Override
    public Object visitDeclaration(CSSParser.DeclarationContext ctx) {
        return null;
    }

    @Override
    public Object visitImportant(CSSParser.ImportantContext ctx) {
        return null;
    }

    @Override
    public Object visitProperty(CSSParser.PropertyContext ctx) {
        return null;
    }

    @Override
    public Object visitTerms(CSSParser.TermsContext ctx) {
        return null;
    }

    @Override
    public Object visitTermValuePart(CSSParser.TermValuePartContext ctx) {
        return null;
    }

    @Override
    public Object visitTermCurlyBlock(CSSParser.TermCurlyBlockContext ctx) {
        return null;
    }

    @Override
    public Object visitTermAtKeyword(CSSParser.TermAtKeywordContext ctx) {
        return null;
    }

    @Override
    public Object visitFunct(CSSParser.FunctContext ctx) {
        return null;
    }

    @Override
    public Object visitValuepart(CSSParser.ValuepartContext ctx) {
        return null;
    }

    @Override
    public Object visitCombined_selector(CSSParser.Combined_selectorContext ctx) {
        return null;
    }

    @Override
    public Object visitCombinatorChild(CSSParser.CombinatorChildContext ctx) {
        return null;
    }

    @Override
    public Object visitCombinatorAdjacent(CSSParser.CombinatorAdjacentContext ctx) {
        return null;
    }

    @Override
    public Object visitCombinatorPreceding(CSSParser.CombinatorPrecedingContext ctx) {
        return null;
    }

    @Override
    public Object visitCombinatorDescendant(CSSParser.CombinatorDescendantContext ctx) {
        return null;
    }

    @Override
    public Object visitSelectorWithIdOrAsterisk(CSSParser.SelectorWithIdOrAsteriskContext ctx) {
        return null;
    }

    @Override
    public Object visitSelectorWithoutIdOrAsterisk(CSSParser.SelectorWithoutIdOrAsteriskContext ctx) {
        return null;
    }

    @Override
    public Object visitSelpartId(CSSParser.SelpartIdContext ctx) {
        return null;
    }

    @Override
    public Object visitSelpartClass(CSSParser.SelpartClassContext ctx) {
        return null;
    }

    @Override
    public Object visitSelpartAttrib(CSSParser.SelpartAttribContext ctx) {
        return null;
    }

    @Override
    public Object visitSelpartPseudo(CSSParser.SelpartPseudoContext ctx) {
        return null;
    }

    @Override
    public Object visitSelpartInvalid(CSSParser.SelpartInvalidContext ctx) {
        return null;
    }

    @Override
    public Object visitAttribute(CSSParser.AttributeContext ctx) {
        return null;
    }

    @Override
    public Object visitPseudo(CSSParser.PseudoContext ctx) {
        return null;
    }

    @Override
    public Object visitPseudocolon(CSSParser.PseudocolonContext ctx) {
        return null;
    }

    @Override
    public Object visitString(CSSParser.StringContext ctx) {
        return null;
    }

    @Override
    public Object visitAny(CSSParser.AnyContext ctx) {
        return null;
    }

    @Override
    public Object visitNostatement(CSSParser.NostatementContext ctx) {
        return null;
    }

    @Override
    public Object visitNoprop(CSSParser.NopropContext ctx) {
        return null;
    }

    @Override
    public Object visitNorule(CSSParser.NoruleContext ctx) {
        return null;
    }

    @Override
    public Object visitNomediaquery(CSSParser.NomediaqueryContext ctx) {
        return null;
    }

    @Override
    public Object visit(ParseTree parseTree) {
        logEnter("visit");
        log.debug(parseTree.getText());
//        Object o  = visitChildren(parseTree.chi);
        logLeave("visit");
        return null;
    }

    @Override
    public Object visitChildren(RuleNode ruleNode) {
        return null;
    }

    @Override
    public Object visitTerminal(TerminalNode terminalNode) {
        return null;
    }

    @Override
    public Object visitErrorNode(ErrorNode errorNode) {
        return null;
    }
}
