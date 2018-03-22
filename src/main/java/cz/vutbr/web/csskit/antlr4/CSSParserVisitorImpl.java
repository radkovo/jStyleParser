package cz.vutbr.web.csskit.antlr4;

import cz.vutbr.web.css.*;
import cz.vutbr.web.css.Selector.PseudoElement;
import cz.vutbr.web.csskit.RuleArrayList;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class CSSParserVisitorImpl implements CSSParserVisitor<Object>, CSSParserExtractor {
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

    // list of media queries to wrap rules
    private List<MediaQuery> wrapMedia;

    // structures after parsing
    private List<String> importPaths = new ArrayList<>();
    private List<List<MediaQuery>> importMedia = new ArrayList<>();
    private RuleList rules = null;
    private List<MediaQuery> mediaQueryList = null;

    //prevent imports inside the style sheet
    private boolean preventImports = false;

    private void logEnter(String entry) {
        if (log.isTraceEnabled())
            log.trace("Enter: {}{}", generateSpaces(spacesCounter), entry);
    }

    private void logEnter(String entry, RuleContext ctx) {
        if (log.isTraceEnabled())
            log.trace("Enter: {}{}: >{}<", generateSpaces(spacesCounter), entry, ctx.getText());
    }
    
    private void logLeave(String leaving) {
        if (log.isTraceEnabled())
            log.trace("Leave: {}{}", generateSpaces(spacesCounter), leaving);
    }

    private String extractTextUnescaped(String text) {
        return org.unbescape.css.CssEscape.unescapeCss(text);
    }

    private Declaration.Source extractSource(CSSToken ct) {
        return new Declaration.Source(ct.getBase(), ct.getLine(), ct.getCharPositionInLine());
    }

    /**
     * extract base from parse tree node
     */
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
     * remove terminal node emtpy tokens from input list
     *
     * @param inputArrayList original list
     * @return list without terminal node type = S (space)
     */
    private List<ParseTree> filterSpaceTokens(List<ParseTree> inputArrayList) {
        List<ParseTree> ret = new ArrayList<ParseTree>(inputArrayList.size());
        for (ParseTree item : inputArrayList) {
            if (!(item instanceof TerminalNode) || ((TerminalNodeImpl) item).getSymbol().getType() != CSSLexer.S) {
                ret.add(item);
            }
        }
        return ret;
    }

    /**
     * check if rule context contains error node
     *
     * @param ctx rule context
     * @return contains context error node
     */
    private boolean ctxHasErrorNode(ParserRuleContext ctx) {
        for (int i = 0; i < ctx.children.size(); i++) {
            if (ctx.getChild(i) instanceof ErrorNode) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tries to convert generic terms to more specific value types. Currently, colors (TermColor) and
     * rectangles (TermRect) are supported.
     * @param term the term to be converted
     * @return the corresponding more specific term type or {@code null} when nothing was found.
     */
    private Term<?> findSpecificType(Term<?> term)
    {
        TermColor colorTerm = null;
        TermRect rectTerm = null;
        if (term instanceof TermIdent) { //idents - try to convert colors
            colorTerm = tf.createColor((TermIdent) term);
        } else if (term instanceof TermFunction) { // rgba(0,0,0)
            colorTerm = tf.createColor((TermFunction) term);
            if (colorTerm == null)
                rectTerm = tf.createRect((TermFunction) term);
        }
        //replace with more specific value
        if (colorTerm != null) {
            log.debug("term color is OK - creating - " + colorTerm.toString());
            return colorTerm;
        } else if (rectTerm != null) {
            log.debug("term rect is OK - creating - " + rectTerm.toString());
            return rectTerm;
        } else
            return null;
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

    /**
     * get import list
     *
     * @return list of urls to import
     */
    public List<String> getImportPaths() {
        return importPaths;
    }

    /**
     * get media for imports
     *
     * @return list of media for imports
     */
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
     * @return RuleList
     * inlinestyle: S* (declarations | inlineset+ )
     */
    @Override
    public RuleList visitInlinestyle(CSSParser.InlinestyleContext ctx) {
        logEnter("inlinestyle");
        this.rules = new cz.vutbr.web.csskit.RuleArrayList();
        if (ctx.declarations() != null) {
            //declarations
            List<Declaration> decl = visitDeclarations(ctx.declarations());
            cz.vutbr.web.css.RuleBlock<?> rb = preparator.prepareInlineRuleSet(decl, null);
            if (rb != null) {
                //rb is valid,add to rules
                this.rules.add(rb);
            }
        } else {
            //inlineset
            for (CSSParser.InlinesetContext ctxis : ctx.inlineset()) {
                cz.vutbr.web.css.RuleBlock<?> irs = visitInlineset(ctxis);
                if (irs != null) {
                    //irs is valid, add to rules
                    this.rules.add(irs);
                }
            }
        }
        log.debug("\n***\n{}\n***\n", this.rules);
        logLeave("inlinestyle");
        return this.rules;
    }

    /**
     * Stylesheet, main rule
     * stylesheet: ( CDO | CDC  | S | nostatement | statement )*
     * statement* is only processed
     */
    @Override
    public RuleList visitStylesheet(CSSParser.StylesheetContext ctx) {
        logEnter("stylesheet: ", ctx);
        this.rules = new RuleArrayList();
        //statement*
        for (CSSParser.StatementContext stmt : ctx.statement()) {
            RuleBlock<?> s = visitStatement(stmt);
            if (s != null) {
                //add statement to rules
                this.rules.add(s);
            }
        }
        log.debug("\n***\n{}\n***\n", this.rules);
        logLeave("stylesheet");
        return this.rules;
    }

    /**
     * scope and stack for statement
     * - this is for accessing statement scope from inner rules
     * e.g. - used for invalidate statement from selector
     */
    protected static class statement_scope {
        boolean invalid = false;
    }

    /**
     * stack for posibly recursion
     */
    protected Stack<statement_scope> statement_stack = new Stack<>();

    @Override
    /**
     * Statement, main contents unit
     * statement : ruleset | atstatement
     */
    public RuleBlock<?> visitStatement(CSSParser.StatementContext ctx) {
        if (ctxHasErrorNode(ctx)) {
            //context is invalid
            return null;
        }
        logEnter("statement: ", ctx);
        //create new scope and push it to stack
        statement_stack.push(new statement_scope());
        RuleBlock<?> stmt = null;
        if (ctx.ruleset() != null) {
            //ruleset
            stmt = visitRuleset(ctx.ruleset());
        } else if (ctx.atstatement() != null) {
            //atstatement
            stmt = visitAtstatement(ctx.atstatement());
        }
        if (statement_stack.peek().invalid) {
            //stmt == null - is invalid
            log.debug("Statement is invalid");
        }
        statement_stack.pop();
        logLeave("statement");
        //could be null
        return stmt;
    }

    @Override
    /**
     *

     atstatement
     : CHARSET
     | IMPORT S* import_uri S* media? SEMICOLON
     | page
     | VIEWPORT S* LCURLY S* declarations RCURLY
     | FONTFACE S* LCURLY S* declarations RCURLY
     | MEDIA S* media? LCURLY S* (media_rule S*)* RCURLY
     | unknown_atrule
     ;

     */
    public RuleBlock<?> visitAtstatement(CSSParser.AtstatementContext ctx) {
        logEnter("atstatement: ", ctx);
        RuleBlock<?> atstmt = null;
        //noinspection StatementWithEmptyBody
        if (ctx.CHARSET() != null) {
            //charset is served in lexer
        }
        //import
        else if (ctx.IMPORT() != null) {
            List<cz.vutbr.web.css.MediaQuery> im = null;
            if (ctx.media() != null) {
                im = visitMedia(ctx.media());
            }
            ctx.import_uri();
            String iuri = visitImport_uri(ctx.import_uri());
            if (!this.preventImports && iuri != null) {
                log.debug("Adding import: {}", iuri);
                importMedia.add(im);
                importPaths.add(iuri);
            } else {
                log.debug("Ignoring import: {}", iuri);
            }
        }
        //page
        else if (ctx.page() != null) {

            atstmt = visitPage(ctx.page());
        }
        //viewport
        else if (ctx.VIEWPORT() != null) {

            List<cz.vutbr.web.css.Declaration> declarations = visitDeclarations(ctx.declarations());
            atstmt = preparator.prepareRuleViewport(declarations);
            if (atstmt != null)
                this.preventImports = true;
        }
        //fontface
        else if (ctx.FONTFACE() != null) {

            List<cz.vutbr.web.css.Declaration> declarations = visitDeclarations(ctx.declarations());
            atstmt = preparator.prepareRuleFontFace(declarations);
            if (atstmt != null)
                this.preventImports = true;
        }
        //media
        else if (ctx.MEDIA() != null) {

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
            if (atstmt != null)
                this.preventImports = true;
        }
        //unknown
        else {
            log.debug("Skipping invalid at statement");
        }
        logLeave("atstatement");
        return atstmt;
    }

    @Override
    /**
     * import_uri : (STRING | URI)
     */
    public String visitImport_uri(CSSParser.Import_uriContext ctx) {
        if (ctx != null)
            return extractTextUnescaped(ctx.getText());
        else
            return null;
    }

    @Override
    /**
     *
     page
     : PAGE S* IDENT? pseudo? S*
     LCURLY S*
     declarations margin_rule*
     RCURLY
     */
    public RuleBlock<?> visitPage(CSSParser.PageContext ctx) {
        boolean invalid = false;
        String name = null;
        if (ctx.IDENT() != null) {
            name = extractTextUnescaped(ctx.IDENT().getText());
        }
        Selector.PseudoPage pseudo = null;
        if (ctx.pseudo() != null) {
            Selector.SelectorPart p = visitPseudo(ctx.pseudo());
            if (p != null && p instanceof Selector.PseudoPage) {
                pseudo = (Selector.PseudoPage) p;
            } else { // Invalid pseudo
                log.debug("skipping RulePage with invalid pseudo-class: " + pseudo);
                invalid = true;
            }
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

        if (invalid) {
            return null;
        } else {
            RuleBlock<?> rb = preparator.prepareRulePage(declarations, margins, name, pseudo);
            if (rb != null)
                this.preventImports = true;
            return rb;
        }
    }

    @Override
    /**
     * margin_rule : MARGIN_AREA S* LCURLY S* declarations RCURLY S*
     */
    public RuleMargin visitMargin_rule(CSSParser.Margin_ruleContext ctx) {
        logEnter("margin_rule");
        RuleMargin m;
        String area = ctx.MARGIN_AREA().getText();
        List<Declaration> decl = visitDeclarations(ctx.declarations());
        m = preparator.prepareRuleMargin(extractTextUnescaped(area).substring(1), decl);
        logLeave("margin_rule");
        return m;
    }

    @Override
    /**
     *
     inlineset
     : (pseudo S* (COMMA S* pseudo S*)*)?
     LCURLY
     declarations
     RCURLY
     ;
     */
    public cz.vutbr.web.css.RuleBlock<?> visitInlineset(CSSParser.InlinesetContext ctx) {
        logEnter("inlineset");
        List<cz.vutbr.web.css.Selector.SelectorPart> pplist = new ArrayList<>();
        if (ctx.pseudo() != null) {
            for (CSSParser.PseudoContext pctx : ctx.pseudo()) {
                Selector.SelectorPart p = visitPseudo(pctx);
                pplist.add(p);
            }
        }
        List<Declaration> decl = visitDeclarations(ctx.declarations());
        RuleBlock<?> is = preparator.prepareInlineRuleSet(decl, pplist);
        logLeave("inlineset");
        return is;
    }

    @Override
    /**
     media : media_query (COMMA S* media_query)*
     */
    public List<cz.vutbr.web.css.MediaQuery> visitMedia(CSSParser.MediaContext ctx) {
        logEnter("media: ", ctx);
        List<MediaQuery> queries = mediaQueryList = new ArrayList<>();
        for (CSSParser.Media_queryContext mqc : ctx.media_query()) {
            queries.add(visitMedia_query(mqc));
        }
        log.debug("Totally returned {} media queries.", queries.size());
        logLeave("media");
        return queries;
    }

    protected static class mediaquery_scope {
        cz.vutbr.web.css.MediaQuery q;
        MediaQueryState state;
        boolean invalid;
    }

    mediaquery_scope mq;

    @Override
    /**
     * media_query : (media_term S*)+
     */
    public MediaQuery visitMedia_query(CSSParser.Media_queryContext ctx) {
        logEnter("mediaquery: ", ctx);
        mq = new mediaquery_scope();
        mq.q = rf.createMediaQuery();
        mq.q.unlock();
        mq.state = MediaQueryState.START;
        mq.invalid = false;
        logLeave("mediaquery");
        for (CSSParser.Media_termContext mtc : ctx.media_term()) {
            visitMedia_term(mtc);
        }
        if (mq.invalid) {
            log.trace("Skipping invalid rule {}", mq.q);
            mq.q.setType("all"); //change the malformed media queries to "not all"
            mq.q.setNegative(true);
        }
        logLeave("mediaquery");
        return mq.q;
    }

    @Override
    /**
     *
     media_term
     : (IDENT | media_expression)
     | nomediaquery
     */
    public Object visitMedia_term(CSSParser.Media_termContext ctx) {
        //IDENT
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
        }
        //media_expression
        else if (ctx.media_expression() != null) {
            MediaExpression e = visitMedia_expression(ctx.media_expression());
            if (mq.state == MediaQueryState.START
                    || mq.state == MediaQueryState.EXPR
                    || mq.state == MediaQueryState.TYPEOREXPR) {
                if (e != null && e.getFeature() != null) //the expression is valid
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
        }
        //nomediaquery
        else {
            mq.invalid = true;
        }
        return null;
    }

    @Override
    /**
     * media_expression : LPAREN S* IDENT S* (COLON S* terms)? RPAREN
     */
    public MediaExpression visitMedia_expression(CSSParser.Media_expressionContext ctx) {
        logEnter("mediaexpression: ", ctx);
        if (ctxHasErrorNode(ctx)) {
            mq.invalid = true;
            return null;
        }
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

        if (declaration_stack.peek().d != null && !declaration_stack.peek().invalid) { //if the declaration is valid
            expr.setFeature(decl.getProperty());
            expr.replaceAll(decl);
        }
        declaration_stack.pop();

        logLeave("mediaexpression");
        return expr;
    }

    @Override
    /**
     *
     media_rule
     : ruleset
     | atstatement //invalid statement
     ;
     */
    public RuleBlock<?> visitMedia_rule(CSSParser.Media_ruleContext ctx) {
        logEnter("media_rule: ", ctx);
        RuleBlock<?> rules = null;
        if (ctx.ruleset() != null) {
            statement_stack.push(new statement_scope());
            rules = visitRuleset(ctx.ruleset());
            statement_stack.pop();
        } else {
            log.debug("Skiping invalid statement in media");
        }
        logLeave("media_rule");
        //could be null
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
     ruleset
     : combined_selector (COMMA S* combined_selector)*
     LCURLY S*
     declarations
     RCURLY
     | norule
     */
    public RuleBlock<?> visitRuleset(CSSParser.RulesetContext ctx) {
        logEnter("ruleset");
        if (ctxHasErrorNode(ctx) || ctx.norule() != null) {
            log.trace("Leaving ruleset with error {} {}", ctxHasErrorNode(ctx), (ctx.norule() != null)); 
            return null;
        }
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
     * declarations : declaration? (SEMICOLON S* declaration? )*
     */
    public List<Declaration> visitDeclarations(CSSParser.DeclarationsContext ctx) {
        logEnter("declarations");
        List<Declaration> decl = new ArrayList<>();
        if (ctx != null && ctx.declaration() != null) {
            for (CSSParser.DeclarationContext declctx : ctx.declaration()) {
                Declaration d = visitDeclaration(declctx);
                if (d != null) {
                    decl.add(d);
                    log.debug("Inserted declaration #{} ", decl.size() + 1);
                } else {
                    log.debug("Null declaration was omitted");
                }
            }
        }
        logLeave("declarations");
        return decl;
    }

    protected static class declaration_scope {
        cz.vutbr.web.css.Declaration d;
        boolean invalid;
    }

    protected Stack<declaration_scope> declaration_stack = new Stack<>();

    @Override
    /**
     *
     declaration : property COLON S* terms? important?
     | noprop any*
     */
    public Declaration visitDeclaration(CSSParser.DeclarationContext ctx) {
        logEnter("declaration");
        Declaration decl;
        declaration_stack.push(new declaration_scope());
        declaration_stack.peek().d = decl = rf.createDeclaration();
        declaration_stack.peek().invalid = false;

        if (ctx.noprop() == null && !ctxHasErrorNode(ctx)) {
            if (ctx.important() != null) {
                visitImportant(ctx.important());
            }
            visitProperty(ctx.property());
            if (ctx.terms() != null) {
                List<Term<?>> t = visitTerms(ctx.terms());
                decl.replaceAll(t);
            }
        } else {
            log.debug("invalidating declaration");
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

        return decl;
    }

    @Override
    /**
     * important
     : EXCLAMATION S* IMPORTANT S*
     */
    public Object visitImportant(CSSParser.ImportantContext ctx) {
        if (ctxHasErrorNode(ctx)) {
            declaration_stack.peek().invalid = true;
        } else {
            declaration_stack.peek().d.setImportant(true);
            log.debug("IMPORTANT");
        }
        //returns null
        return null;
    }

    @Override
    /**
     * Setting property of declaration
     property
     : MINUS? IDENT S*

     returns null - processed via declaration_sctack
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
        //returns null
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
     *
     * terms : term+
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
        if (ctx.term() != null)
        {
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
        }
        log.debug("Totally added {} terms", tlist.size());
        logLeave("terms");
        terms_stack.pop();
        return tlist;
    }

    /**
     * term
     * : valuepart #termValuePart
     * | LCURLY S* (any | SEMICOLON S*)* RCURLY #termInvalid // invalid term
     * | ATKEYWORD S* #termInvalid // invalid term
     * ;
     */
    //
    @Override
    public Object visitTermValuePart(CSSParser.TermValuePartContext ctx) {
        logEnter("term");
        visitValuepart(ctx.valuepart());
        //returns null
        return null;
    }

    @Override
    public Object visitTermInvalid(CSSParser.TermInvalidContext ctx) {
        logEnter("term");
        declaration_stack.peek().invalid = true;
        //returns null
        return null;
    }


    @Override
    public Object visitFunct(CSSParser.FunctContext ctx) {
        if (ctx.EXPRESSION() != null) {
            log.warn("Omitting expression " + ctx.getText() + ", expressions are not supported");
            return null;
        }
        Term<?> ret = null;
        final String fname = extractTextUnescaped(ctx.FUNCTION().getText()).toLowerCase();
        if (ctx.funct_args() != null)
        {
            List<Term<?>> t = visitFunct_args(ctx.funct_args());
            if (fname.equals("url")) {
                // the function name is url() after escaping - create an URI
                if (t == null || t.size() != 1)
                    ret = null;
                else {
                    cz.vutbr.web.css.Term<?> term = t.get(0);
                    if (term instanceof cz.vutbr.web.css.TermString && term.getOperator() == null)
                        ret = tf.createURI(((cz.vutbr.web.css.TermString) term).getValue(), extractBase(ctx.FUNCTION()));
                    else
                        ret = null;
                }
            } else if (fname.equals("calc")) {
                // create calc() of the given type: <length>, <frequency>, <angle>, <time>, <number>, or <integer>
                if (t == null || t.size() == 0)
                    ret = null;
                else
                    ret = tf.createCalc(t);
                
            } else {
                // create function
                cz.vutbr.web.css.TermFunction function = tf.createFunction();
                function.setFunctionName(fname);
                if (t != null)
                    function.setValue(t);
                ret = function;
            }
        }
        return ret;
    }

    @Override
    public Object visitValuepart(CSSParser.ValuepartContext ctx) {
        logEnter("valuepart: ", ctx);
        if (ctxHasErrorNode(ctx)) {
            log.error("value part with error");
            terms_stack.peek().term = null;
            declaration_stack.peek().invalid = true;
            return null;
        }
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
        } else if (ctx.UNIRANGE() != null) {
            log.debug("VP - unirange");
            terms_stack.peek().term = tf.createUnicodeRange(ctx.UNIRANGE().getText());
        } else if (ctx.URI() != null) {
            log.debug("VP - uri");
            terms_stack.peek().term = tf.createURI(extractTextUnescaped(ctx.URI().getText()), extractBase(ctx.URI()));
        } else if (ctx.UNCLOSED_URI() != null && ((CSSToken) ctx.UNCLOSED_URI().getSymbol()).isValid()) {
            log.debug("VP - unclosed_uri");
            terms_stack.peek().term = tf.createURI(extractTextUnescaped(ctx.UNCLOSED_URI().getText()), extractBase(ctx.UNCLOSED_URI()));
        } else if (ctx.funct() != null) {
            terms_stack.peek().term = null;
            Term<?> fnterm = (Term<?>) visitFunct(ctx.funct());
            if (fnterm != null) {
                if (terms_stack.peek().unary == -1) { 
                    if (fnterm instanceof TermFunction) {
                        //normal function - add the unary minus to the function name
                        ((TermFunction) fnterm).setFunctionName('-' + ((TermFunction) fnterm).getFunctionName());
                        terms_stack.peek().term = fnterm;
                    } else {
                        //url() and calc() - not applicable 
                        declaration_stack.peek().invalid = true;
                    }
                } else {
                    terms_stack.peek().term = fnterm;
                }
            } else {
                declaration_stack.peek().invalid = true;
            }
        } else {
            log.error("unhandled valueparts");
            terms_stack.peek().term = null;
            declaration_stack.peek().invalid = true;
        }
        //try to convert generic terms to more specific value types
        Term<?> term = terms_stack.peek().term;
        if (term != null) {
            term = findSpecificType(term);
            if (term != null)
                terms_stack.peek().term = term;
        }
        //returns null
        return null;
    }

    protected static class funct_args_scope {
        List<cz.vutbr.web.css.Term<?>> list;
        cz.vutbr.web.css.Term<?> term;
    }

    protected Stack<funct_args_scope> funct_args_stack = new Stack<>();

    @Override
    public List<Term<?>> visitFunct_args(CSSParser.Funct_argsContext ctx)
    {
        funct_args_stack.push(new funct_args_scope());
        List<cz.vutbr.web.css.Term<?>> tlist;
        funct_args_stack.peek().term = null;
        logEnter("funct_args");
        funct_args_stack.peek().list = tlist = new ArrayList<>();
        if (ctx.funct_argument() != null)
        {
            for (CSSParser.Funct_argumentContext argCtx : ctx.funct_argument()) {
                visitFunct_argument(argCtx);
                // set operator, store and create next
                if (!declaration_stack.peek().invalid && funct_args_stack.peek().term != null) {
                    funct_args_stack.peek().list.add(funct_args_stack.peek().term);
                    funct_args_stack.peek().term = null;
                }
            }
        }
        log.debug("Totally added {} args", tlist.size());
        logLeave("funct_args");
        funct_args_stack.pop();
        return tlist;
    }

    @Override
    public Object visitFunct_argument(CSSParser.Funct_argumentContext ctx)
    {
        logEnter("funct_argument: ", ctx);
        if (ctxHasErrorNode(ctx)) {
            log.error("argument with error");
            funct_args_stack.peek().term = null;
            declaration_stack.peek().invalid = true;
            return null;
        }
        if (ctx.PLUS() != null) {
            log.debug("VP - plus");
            funct_args_stack.peek().term = tf.createOperator('+');
        } else if (ctx.MINUS() != null) {
            log.debug("VP - minus");
            funct_args_stack.peek().term = tf.createOperator('-');
        } else if (ctx.ASTERISK() != null) {
            log.debug("VP - *");
            funct_args_stack.peek().term = tf.createOperator('*');
        } else if (ctx.SLASH() != null) {
            log.debug("VP - /");
            funct_args_stack.peek().term = tf.createOperator('/');
        } else if (ctx.LPAREN() != null) {
            log.debug("VP - (");
            funct_args_stack.peek().term = tf.createOperator('(');
        } else if (ctx.RPAREN() != null) {
            log.debug("VP - )");
            funct_args_stack.peek().term = tf.createOperator(')');
        } else if (ctx.COMMA() != null) {
            log.debug("VP - comma");
            funct_args_stack.peek().term = tf.createOperator(',');
        } else if (ctx.string() != null) {
            log.debug("VP - string");
            funct_args_stack.peek().term = tf.createString(extractTextUnescaped(ctx.string().getText()));
        } else if (ctx.IDENT() != null) {
            log.debug("VP - ident");
            funct_args_stack.peek().term = tf.createIdent(extractTextUnescaped(ctx.IDENT().getText()));
        } else if (ctx.PERCENTAGE() != null) {
            log.debug("VP - percentage");
            funct_args_stack.peek().term = tf.createPercent(ctx.PERCENTAGE().getText(), 1);
        } else if (ctx.DIMENSION() != null) {
            log.debug("VP - dimension");
            String dim = ctx.DIMENSION().getText();
            funct_args_stack.peek().term = tf.createDimension(dim, 1);
            if (funct_args_stack.peek().term == null) {
                log.info("Unable to create dimension from {}, unary {}", dim, 1);
                declaration_stack.peek().invalid = true;
            }
        } else if (ctx.HASH() != null) {
            log.debug("VP - hash");
            funct_args_stack.peek().term = tf.createColor(ctx.HASH().getText());
            if (funct_args_stack.peek().term == null) {
                declaration_stack.peek().invalid = true;
            }
        } else if (ctx.NUMBER() != null) {
            log.debug("VP - number");
            funct_args_stack.peek().term = tf.createNumeric(ctx.NUMBER().getText(), 1);
        } else if (ctx.funct() != null) {
            funct_args_stack.peek().term = null;
            Term<?> fnterm = (Term<?>) visitFunct(ctx.funct());
            if (fnterm != null) {
                funct_args_stack.peek().term = fnterm;
            } else {
                declaration_stack.peek().invalid = true;
            }
        } else {
            log.error("unhandled funct_args");
            funct_args_stack.peek().term = null;
            declaration_stack.peek().invalid = true;
        }
        //try convert color from current term
        Term<?> term = funct_args_stack.peek().term;
        if (term != null) {
            term = findSpecificType(term);
            if (term != null)
                funct_args_stack.peek().term = term;
        }
        //returns null
        return null;
    }

    protected static class combined_selector_scope {
        boolean invalid;
    }

    protected Stack<combined_selector_scope> combined_selector_stack = new Stack<>();

    @Override
    /**
     * Construction of selector
     * combined_selector : selector ((combinator) selector)*
     */
    public CombinedSelector visitCombined_selector(CSSParser.Combined_selectorContext ctx) {
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
        combined_selector_stack.pop();
        logLeave("combined_selector");
        return combinedSelector;
    }

    @Override
    /**
     combinator
     : GREATER S* //child combinator
     | PLUS S* //adjacent combinator
     | TILDE S* //preceding combinator
     | S //descendant combinator
     */
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

    /**
     * selector
     * : (IDENT | ASTERISK)  selpart* S*
     * | selpart+ S*
     * ;
     */
    public Selector visitSelector(CSSParser.SelectorContext ctx) {
        if (ctxHasErrorNode(ctx)) {
            statement_stack.peek().invalid = true;
            return null;
        }
        selector_stack.push(new selector_scope());
        cz.vutbr.web.css.Selector sel;
        logEnter("selector");
        selector_stack.peek().s = sel = (cz.vutbr.web.css.Selector) rf.createSelector().unlock();
        if (ctx.IDENT() != null || ctx.ASTERISK() != null) {
            cz.vutbr.web.css.Selector.ElementName en = rf.createElement(cz.vutbr.web.css.Selector.ElementName.WILDCARD);
            if (ctx.IDENT() != null) {
                en.setName(extractTextUnescaped(ctx.IDENT().getText()));
            }
            //log.debug("Adding element name: {}.", en.getName());
            selector_stack.peek().s.add(en);
        }
        for (CSSParser.SelpartContext selpartctx : ctx.selpart()) {
            visitSelpart(selpartctx);
        }

        logLeave("selector");
        selector_stack.pop();
        return sel;
    }

    /**
     * selpart
     * : HASH
     * | CLASSKEYWORD
     * | LBRACE S* attribute RBRACE
     * | pseudo
     * | INVALID_SELPART // invalid selpart
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
            Selector.SelectorPart p = visitPseudo(ctx.pseudo());
            if (p != null) {
                if (p instanceof PseudoElement && selector_stack.peek().s.getPseudoElementType() != null) {
                    log.warn("Invalid selector with multiple pseudo-elements");
                    combined_selector_stack.peek().invalid = true;
                }
                else
                    selector_stack.peek().s.add(p);
            } else {
                combined_selector_stack.peek().invalid = true;
            }

        } else {
            combined_selector_stack.peek().invalid = true;
        }
        logLeave("selpart");
        //returns null
        return null;
    }


    @Override
    /**
     * attribute
     : IDENT S*
     ((EQUALS | INCLUDES | DASHMATCH | STARTSWITH | ENDSWITH | CONTAINS) S* (IDENT | string) S*)?
     */
    public Selector.ElementAttribute visitAttribute(CSSParser.AttributeContext ctx) {
        //attributes can be like [attr] or [attr operator value]
        // see http://www.w3.org/TR/CSS2/selector.html#attribute-selectors
        logEnter("attribute: ", ctx);
        //initialize attribute
        String attributeName = extractTextUnescaped(ctx.children.get(0).getText());
        String value = null;
        boolean isStringValue = false;
        Selector.Operator op = Selector.Operator.NO_OPERATOR;
        List<ParseTree> ctx2 = filterSpaceTokens(ctx.children);
        //is attribute like [attr=value]
        if (ctx2.size() == 3) {
            CommonToken opToken = (CommonToken) ((TerminalNodeImpl) ctx2.get(1)).symbol;
            isStringValue = (ctx2.get(2) instanceof CSSParser.StringContext);
            if (isStringValue) {
                value = ctx2.get(2).getText();
            } else {

                value = ctx2.get(2).getText();
            }
            value = extractTextUnescaped(value);
            switch (opToken.getType()) {
                case CSSParser.EQUALS: {
                    op = Selector.Operator.EQUALS;
                    break;
                }
                case CSSParser.INCLUDES: {
                    op = Selector.Operator.INCLUDES;
                    break;
                }
                case CSSParser.DASHMATCH: {
                    op = Selector.Operator.DASHMATCH;
                    break;
                }
                case CSSParser.CONTAINS: {
                    op = Selector.Operator.CONTAINS;
                    break;
                }
                case CSSParser.STARTSWITH: {
                    op = Selector.Operator.STARTSWITH;
                    break;
                }
                case CSSParser.ENDSWITH: {
                    op = Selector.Operator.ENDSWITH;
                    break;
                }
                default: {
                    op = Selector.Operator.NO_OPERATOR;
                }
            }
        }
        Selector.ElementAttribute elemAttr = null;
        if (attributeName != null) {
            elemAttr = rf.createAttribute(value, isStringValue, op, attributeName);
        } else {
            log.debug("Invalid attribute element in selector");
            combined_selector_stack.peek().invalid = true;
        }
        logLeave("attribute");

        return elemAttr;
    }

    @Override
    /**
     * pseudo
     : COLON COLON? (MINUS? IDENT | FUNCTION S*  (IDENT | MINUS? NUMBER | MINUS? INDEX) S* RPAREN)
     */
    public Selector.SelectorPart visitPseudo(CSSParser.PseudoContext ctx) {
        logEnter("pseudo: ", ctx);
        boolean isPseudoElem = ctx.COLON().size() > 1;
        Selector.SelectorPart pseudo = null;
        String name;
        if (ctx.FUNCTION() != null) {
            // function
            name = extractTextUnescaped(ctx.FUNCTION().getText());
            if (ctx.selector() != null) {
                Selector sel = visitSelector(ctx.selector());
                pseudo = (isPseudoElem ? rf.createPseudoElement(name, sel) : rf.createPseudoClass(name, sel));
            } else {
                String value = (ctx.MINUS() == null ? "" : "-");
                if (ctx.IDENT() != null) {
                    value += ctx.IDENT().getText();
                } else if (ctx.NUMBER() != null) {
                    value += ctx.NUMBER().getText();
                } else if (ctx.INDEX() != null) {
                    value += ctx.INDEX().getText();
                } else {
                    throw new UnsupportedOperationException("unknown state");
                }
                pseudo = (isPseudoElem ? rf.createPseudoElement(name, value) : rf.createPseudoClass(name, value));
            }
        } else if (ctx.IDENT() != null) {
            // ident
            name = extractTextUnescaped(ctx.IDENT().getText());
            if (ctx.MINUS() != null) {
                name = ctx.MINUS().getText() + name;
            }
            // Legacy support for :after, :before, :first-line, and :first-letter pseudo-elements
            if (!isPseudoElem && ("after".equalsIgnoreCase(name) || "before".equalsIgnoreCase(name) || "first-line".equalsIgnoreCase(name) || "first-letter".equalsIgnoreCase(name))) {
                isPseudoElem = true;
            }
            if (isPseudoElem) {
                pseudo = rf.createPseudoElement(name);
            } else if (ctx.parent instanceof CSSParser.PageContext) {
                pseudo = rf.createPseudoPage(name);
            } else {
                pseudo = rf.createPseudoClass(name);
            }
        } else {
            // invalid selpart
            name = "";
        }
        
        if ((pseudo == null) ||
                (pseudo instanceof Selector.PseudoPage && ((Selector.PseudoPage) pseudo).getType() == null) ||
                (pseudo instanceof Selector.PseudoClass && ((Selector.PseudoClass) pseudo).getType() == null) ||
                (pseudo instanceof Selector.PseudoElement && ((Selector.PseudoElement) pseudo).getType() == null)) {
            log.error("invalid pseudo declaration: " + name);
            pseudo = null; // invalid
        }
        logLeave("pseudo");
        return pseudo;
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
