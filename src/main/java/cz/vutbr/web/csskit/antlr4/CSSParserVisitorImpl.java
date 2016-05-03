package cz.vutbr.web.csskit.antlr4;

import cz.vutbr.web.css.*;
import cz.vutbr.web.csskit.RuleArrayList;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class CSSParserVisitorImpl implements CSSParserVisitor, CSSParserExtractor {
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
    private List<MediaQuery> mediaQueryList = null;

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

    private Declaration.Source extractSource(CSSToken ct) {
        return new Declaration.Source(ct.getBase(), ct.getLine(), ct.getCharPositionInLine());
    }

    private URL extractBase(TerminalNode node) {
        CSSToken ct = (CSSToken) node.getSymbol();
        return ct.getBase();
    }

    /**
     * check if string is valid ID
     *
     * @param id ID to validate and unescapes
     * @return unescaped id or null
     */
    private String extractIdUnescaped(String id) {
        if (!id.isEmpty() && !Character.isDigit(id.charAt(0))) {
            return org.unbescape.css.CssEscape.unescapeCss(id);
        }
        return null;
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
     * get parsed rulelist
     *
     * @return parsed rules
     */
    public RuleList getRules() {
        return rules;
    }

    /**
     * get mediaquery list
     *
     * @return media query list
     */
    public List<MediaQuery> getMedia() {
        return mediaQueryList;
    }

    public List<String> getImportPaths() {
        return importPaths;
    }

    public List<List<MediaQuery>> getImportMedia() {
        return importMedia;
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
    public RuleBlock<?> visitInlinestyle(CSSParser.InlinestyleContext ctx) {
//        logEnter("inlineset");
//        RuleBlock<?> is;
//        List<Selector.PseudoPage> pplist = new ArrayList<>();
//
//        logLeave("inlineset");
//        return is
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


    protected static class statement_scope {
        boolean invalid;
        // this flag allows us to encapsulate rulesets
        // into media when media import is used
        boolean insideAtstatement;
    }

    protected Stack<statement_scope> statement_stack = new Stack<statement_scope>();

    @Override
    /**
     * Statement, main contents unit
     */
    public RuleBlock<?> visitStatement(CSSParser.StatementContext ctx) {
        logEnter("statement: " + ctx.getText());
        statement_stack.push(new statement_scope());
        statement_stack.peek().invalid = false;
        RuleBlock<?> stmt = null;
        if (ctx.ruleset() != null) {
            stmt = visitRuleset(ctx.ruleset());
        } else if (ctx.atstatement() != null) {
            stmt = visitAtstatement(ctx.atstatement());
        }
        if (statement_stack.peek().invalid) {
            log.debug("Statement is invalid");
        }
        statement_stack.pop();
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
            String m = extractTextUnescaped(ctx.IDENT().getText());
            MediaQueryState state = mq.state;
            if (m.equalsIgnoreCase("ONLY") && state == MediaQueryState.START) {
                mq.state = MediaQueryState.TYPEOREXPR;
            } else if (m.equalsIgnoreCase("NOT") && state == MediaQueryState.START) {
                mq.q.setNegative(true);
                mq.state = MediaQueryState.TYPEOREXPR;
            } else if (m.equalsIgnoreCase("AND") && state == MediaQueryState.AND) {
                mq.state = MediaQueryState.EXPR;
            } else if (state == MediaQueryState.START
                    || state == MediaQueryState.TYPE
                    || state == MediaQueryState.TYPEOREXPR) {
                mq.q.setType(m);
                mq.state = MediaQueryState.AND;
            } else {
                log.trace("Invalid media query: found ident: {} state: {}", m, state);
                mq.invalid = true;
            }
        } else if (ctx.media_expression() != null) {
            MediaExpression e = visitMedia_expression(ctx.media_expression());
            if (mq.state == MediaQueryState.START
                    || mq.state == MediaQueryState.EXPR
                    || mq.state == MediaQueryState.TYPEOREXPR) {
                if (e.getFeature() != null) //the expression is valid
                {
                    mq.q.add(e);
                    mq.state = MediaQueryState.AND;
                } else {
                    log.trace("Invalidating media query for invalud expression");
                    mq.invalid = true;
                }
            } else {
                log.trace("Invalid media query: found expr, state: {}", mq.state);
                mq.invalid = true;
            }
        } else {
            mq.invalid = true;
        }
        return null;
    }

    @Override
    public MediaExpression visitMedia_expression(CSSParser.Media_expressionContext ctx) {
        logEnter("mediaexpression: " + ctx.getText());
        MediaExpression expr = rf.createMediaExpression();
        Declaration decl;
        declaration_stack.push(new declaration_scope());
        declaration_stack.peek().d = decl = rf.createDeclaration();
        declaration_stack.peek().invalid = false;

        String property = extractTextUnescaped(ctx.IDENT().getText());
        decl.setProperty(property);
        Token token = ctx.IDENT().getSymbol();
        decl.setSource(extractSource((CSSToken) token));
        if (ctx.terms() != null) {
            List<Term<?>> t = visitTerms(ctx.terms());
            decl.replaceAll(t);
        }

        if (decl != null && !declaration_stack.peek().invalid) { //if the declaration is valid
            expr.setFeature(decl.getProperty());
            expr.replaceAll(decl);
        }
        declaration_stack.pop();

        logLeave("mediaexpression: " + ctx.getText());
        return expr;
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
    /**
     * The most common block in CSS file,
     * set of declarations with selector
     */
    public RuleBlock<?> visitRuleset(CSSParser.RulesetContext ctx) {
        logEnter("ruleset");
        List<CombinedSelector> cslist = new ArrayList<>();
        // body
        for (CSSParser.Combined_selectorContext csctx : ctx.combined_selector()) {
            CombinedSelector cs = visitCombined_selector(csctx);
            if (cs != null && !cs.isEmpty() && !statement_stack.peek().invalid) {
                cslist.add(cs);
                log.debug("Inserted combined selector ({}) into ruleset", cslist.size());
            }
        }
        List<cz.vutbr.web.css.Declaration> decl = visitDeclarations(ctx.declarations());
        RuleBlock<?> stmnt;
        if (statement_stack.peek().invalid) {
            stmnt = null;
            log.debug("Ruleset not valid, so not created");
        } else {
            stmnt = preparator.prepareRuleSet(cslist, decl, (this.wrapMedia != null && !this.wrapMedia.isEmpty()), this.wrapMedia);
            this.preventImports = true;
        }
        logLeave("ruleset");
        return stmnt;
    }

    @Override
    /**
     * Multiple CSS declarations
     */
    public List<Declaration> visitDeclarations(CSSParser.DeclarationsContext ctx) {
        logEnter("declarations");
        List<Declaration> decl = new ArrayList<>();
        for (CSSParser.DeclarationContext declctx : ctx.declaration()) {
            Declaration d = visitDeclaration(declctx);
            if (d != null) {
                decl.add(d);
                log.debug("Inserted declaration #{} ", decl.size() + 1);
            }
        }
        logLeave("declarations");
        return decl;
    }

    protected static class declaration_scope {
        cz.vutbr.web.css.Declaration d;
        boolean invalid;
    }

    protected Stack<declaration_scope> declaration_stack = new Stack<declaration_scope>();

    @Override
    public Declaration visitDeclaration(CSSParser.DeclarationContext ctx) {
        logEnter("declaration");
        Declaration decl;
        declaration_stack.push(new declaration_scope());
        declaration_stack.peek().d = decl = rf.createDeclaration();
        declaration_stack.peek().invalid = false;

        if (ctx.noprop() == null) {
            if (ctx.important() != null) {
                decl.setImportant(true);
                log.debug("IMPORTANT");
            }
            visitProperty(ctx.property());
            List<Term<?>> t = visitTerms(ctx.terms());
            decl.replaceAll(t);
        } else {
            declaration_stack.peek().invalid = true;
        }

        if (declaration_stack.peek().invalid || declaration_stack.isEmpty()) {
            decl = null;
            log.debug("Declaration was invalidated or already invalid");
        } else {
            log.debug("Returning declaration: {}.", decl);
        }
        logLeave("declaration");
        declaration_stack.pop();

        return null;
    }

    @Override
    public Object visitImportant(CSSParser.ImportantContext ctx) {
        //empty
        return null;
    }

    @Override
    /**
     * Setting property of declaration
     */
    public Object visitProperty(CSSParser.PropertyContext ctx) {
        logEnter("property");

        String property = extractTextUnescaped(ctx.IDENT().getText());
        if (ctx.MINUS() != null) {
            property = ctx.MINUS().getText() + property;
        }
        declaration_stack.peek().d.setProperty(property);
        Token token = ctx.IDENT().getSymbol();
        declaration_stack.peek().d.setSource(extractSource((CSSToken) token));

        log.debug("Setting property: {}", declaration_stack.peek().d.getProperty());
        logLeave("property");
        return null;
    }


    protected static class terms_scope {
        List<cz.vutbr.web.css.Term<?>> list;
        cz.vutbr.web.css.Term<?> term;
        cz.vutbr.web.css.Term.Operator op;
        int unary;
        boolean dash;
    }

    protected Stack<terms_scope> terms_stack = new Stack<>();

    @Override
    /**
     * Term of CSSDeclaration
     */
    public List<Term<?>> visitTerms(CSSParser.TermsContext ctx) {
        terms_stack.push(new terms_scope());
        List<cz.vutbr.web.css.Term<?>> tlist;
        logEnter("terms");
        terms_stack.peek().list = tlist = new ArrayList<>();
        terms_stack.peek().term = null;
        terms_stack.peek().op = null;
        terms_stack.peek().unary = 1;
        terms_stack.peek().dash = false;
        for (CSSParser.TermContext trmCtx : ctx.term()) {
            if (trmCtx instanceof CSSParser.TermValuePartContext) {
                visitTermValuePart((CSSParser.TermValuePartContext) trmCtx);
                // set operator, store and create next
                if (!declaration_stack.peek().invalid && terms_stack.peek().term != null) {
                    terms_stack.peek().term.setOperator(terms_stack.peek().op);
                    terms_stack.peek().list.add(terms_stack.peek().term);
                    // reinitialization
                    terms_stack.peek().op = cz.vutbr.web.css.Term.Operator.SPACE;
                    terms_stack.peek().unary = 1;
                    terms_stack.peek().dash = false;
                    terms_stack.peek().term = null;
                }
            } else {
                visitTermInvalid((CSSParser.TermInvalidContext) trmCtx);
            }
        }


        log.debug("Totally added {} terms", tlist.size());
        logLeave("terms");

        terms_stack.pop();
        return null;
    }

    @Override
    public Object visitTermValuePart(CSSParser.TermValuePartContext ctx) {
        logEnter("term");
        visitValuepart(ctx.valuepart());
        return null;
    }

    @Override
    public Object visitTermInvalid(CSSParser.TermInvalidContext ctx) {
        logEnter("term");
        declaration_stack.peek().invalid = true;
        return null;
    }


    @Override
    public Object visitFunct(CSSParser.FunctContext ctx) {
        return null;
    }

    @Override
    public Object visitValuepart(CSSParser.ValuepartContext ctx) {
        logEnter("valuepart: >" + ctx.getText() + "<");
        if (ctx.MINUS() != null) {
            terms_stack.peek().unary = -1;
            terms_stack.peek().dash = true;
        }
        if (ctx.COMMA() != null) {
            log.debug("VP - comma");
            terms_stack.peek().op = Term.Operator.COMMA;
        } else if (ctx.SLASH() != null) {
            terms_stack.peek().op = Term.Operator.SLASH;
        } else if (ctx.string() != null) {
            //string
            log.debug("VP - string");
            terms_stack.peek().term = tf.createString(extractTextUnescaped(ctx.string().getText()));
        } else if (ctx.IDENT() != null) {
            log.debug("VP - ident");
            terms_stack.peek().term = tf.createIdent(extractTextUnescaped(ctx.IDENT().getText()), terms_stack.peek().dash);
        } else if (ctx.HASH() != null) {
            log.debug("VP - hash");
            terms_stack.peek().term = tf.createColor(ctx.HASH().getText());
            if (terms_stack.peek().term == null) {
                declaration_stack.peek().invalid = true;
            }
        } else if (ctx.PERCENTAGE() != null) {
            log.debug("VP - percentage");
            terms_stack.peek().term = tf.createPercent(ctx.PERCENTAGE().getText(), terms_stack.peek().unary);
        } else if (ctx.DIMENSION() != null) {
            log.debug("VP - dimension");
            String dim = ctx.DIMENSION().getText();
            terms_stack.peek().term = tf.createDimension(dim, terms_stack.peek().unary);
            if (terms_stack.peek().term == null) {
                log.info("Unable to create dimension from {}, unary {}", dim, terms_stack.peek().unary);
                declaration_stack.peek().invalid = true;
            }
        } else if (ctx.NUMBER() != null) {
            log.debug("VP - number");
            terms_stack.peek().term = tf.createNumeric(ctx.NUMBER().getText(), terms_stack.peek().unary);
        } else if (ctx.URI() != null) {
            log.debug("VP - uri");
            terms_stack.peek().term = tf.createURI(extractTextUnescaped(ctx.URI().getText()), extractBase(ctx.URI()));
        } else if (ctx.funct() != null) {
            terms_stack.peek().term = null;
            //served in function
            log.debug("function is server later");
        } else {
            log.error("unhandled valueparts");
            terms_stack.peek().term = null;
            declaration_stack.peek().invalid = true;
        }
        //try convert color from current term
        Term<?> term = terms_stack.peek().term;
        if (term != null) {
            TermColor colorTerm = null;
            if (term instanceof TermIdent) { // red
                colorTerm = tf.createColor((TermIdent) term);
            } else if (term instanceof TermFunction) { // rgba(0,0,0)
                colorTerm = tf.createColor((TermFunction) term);
            }
            //replace with color
            if (colorTerm != null) {
                log.debug("term color is OK - creating - " + colorTerm.toString());
                terms_stack.peek().term = colorTerm;
            }

        }
        return null;
    }

    protected static class combined_selector_scope {
        boolean invalid;
    }

    protected Stack<combined_selector_scope> combined_selector_stack = new Stack<>();

    @Override
    /**
     * Construction of selector
     */

    public CombinedSelector visitCombined_selector(CSSParser.Combined_selectorContext ctx) {
        CombinedSelector cs;
        logEnter("combined_selector");
        combined_selector_stack.push(new combined_selector_scope());
        CombinedSelector combinedSelector = (CombinedSelector) rf.createCombinedSelector().unlock();
        Selector.Combinator c;
        Selector s = visitSelector(ctx.selector(0));
        combinedSelector.add(s);
        for (int i = 1; i < ctx.selector().size(); i++) {
            c = visitCombinator(ctx.combinator(i - 1));
            s = visitSelector(ctx.selector(i));
            s.setCombinator(c);
            combinedSelector.add(s);
        }
        // entire ruleset is not valid when selector is not valid
        // there is no need to parse selector's when already marked as invalid
        if (statement_stack.peek().invalid || combined_selector_stack.peek().invalid) {
            combinedSelector = null;
            if (statement_stack.peek().invalid) {
                log.debug("Ommiting combined selector, whole statement discarded");
            } else {
                log.debug("Combined selector is invalid");
            }
            // mark whole ruleset as invalid
            statement_stack.peek().invalid = true;
        } else {
            log.debug("Returing combined selector: {}.", combinedSelector);
        }
        logLeave("combined_selector");
        return combinedSelector;
    }

    @Override
    public Selector.Combinator visitCombinator(CSSParser.CombinatorContext ctx) {
        logEnter("combinator");
        if (ctx.GREATER() != null) {
            return Selector.Combinator.CHILD;
        } else if (ctx.PLUS() != null) {
            return Selector.Combinator.ADJACENT;
        } else if (ctx.TILDE() != null) {
            return Selector.Combinator.PRECEDING;
        } else {
            return Selector.Combinator.DESCENDANT;
        }
    }

    protected static class selector_scope {
        cz.vutbr.web.css.Selector s;
    }

    protected Stack<selector_scope> selector_stack = new Stack<>();

    public Selector visitSelector(CSSParser.SelectorContext ctx) {
        selector_stack.push(new selector_scope());
        cz.vutbr.web.css.Selector sel;
        logEnter("selector");
        selector_stack.peek().s = sel = (cz.vutbr.web.css.Selector) rf.createSelector().unlock();
        if (ctx.IDENT() != null || ctx.ASTERISK() != null) {
            cz.vutbr.web.css.Selector.ElementName en = rf.createElement(cz.vutbr.web.css.Selector.ElementName.WILDCARD);
            if (ctx.IDENT() != null) {
                en.setName(extractTextUnescaped(ctx.IDENT().getText()));
            }
            log.debug("Adding element name: {}.", en.getName());
            selector_stack.peek().s.add(en);
        }
        for (CSSParser.SelpartContext selpartctx : ctx.selpart()) {
            visitSelpart(selpartctx);
        }

        logLeave("selector");
        return sel;
    }

    /**
     * @param ctx the parse tree
     * @return null
     */
    @Override
    public Object visitSelpart(CSSParser.SelpartContext ctx) {
        logEnter("selpart");
        String ident;
        if (ctx.HASH() != null) {
            ident = extractIdUnescaped(ctx.HASH().getText());
            if (ident != null) {
                selector_stack.peek().s.add(rf.createID(ident));
            } else {
                combined_selector_stack.peek().invalid = true;
            }

        } else if (ctx.CLASSKEYWORD() != null) {
            selector_stack.peek().s.add(rf.createClass(extractTextUnescaped(ctx.CLASSKEYWORD().getText())));
        } else if (ctx.attribute() != null) {
            Selector.ElementAttribute ea = visitAttribute(ctx.attribute());
            selector_stack.peek().s.add(ea);

        } else if (ctx.pseudo() != null) {
            Selector.PseudoPage p = visitPseudo(ctx.pseudo());
            if (p != null) {
                selector_stack.peek().s.add(p);
            } else {
                combined_selector_stack.peek().invalid = true;
            }

        } else {
            combined_selector_stack.peek().invalid = true;
        }
        logLeave("selpart");
        return null;
    }


    @Override
    public Selector.ElementAttribute visitAttribute(CSSParser.AttributeContext ctx) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Selector.PseudoPage visitPseudo(CSSParser.PseudoContext ctx) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Object visitPseudocolon(CSSParser.PseudocolonContext ctx) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public String visitString(CSSParser.StringContext ctx) {
        if (ctx.INVALID_STRING() != null) {
            return null;
        }
        return extractTextUnescaped(ctx.getText());
    }

    @Override
    public Object visitAny(CSSParser.AnyContext ctx) {
        // handled elsewhere
        return null;
    }

    @Override
    public Object visitNostatement(CSSParser.NostatementContext ctx) {
        // handled elsewhere
        return null;
    }

    @Override
    public Object visitNoprop(CSSParser.NopropContext ctx) {
        // handled elsewhere
        return null;
    }

    @Override
    public Object visitNorule(CSSParser.NoruleContext ctx) {
        // handled elsewhere
        return null;
    }

    @Override
    public Object visitNomediaquery(CSSParser.NomediaqueryContext ctx) {
        // handled elsewhere
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
        log.error("visitErrorNode");
        return null;
    }
}
