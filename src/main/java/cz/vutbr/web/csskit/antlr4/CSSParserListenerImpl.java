package cz.vutbr.web.csskit.antlr4;

import cz.vutbr.web.css.*;
import cz.vutbr.web.csskit.RuleArrayList;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;


public class CSSParserListenerImpl implements CSSParserListener {

    // factories for building structures
    protected RuleFactory rf = CSSFactory.getRuleFactory();
    protected TermFactory tf = CSSFactory.getTermFactory();

    // structures after parsing
    private List<String> importPaths;
    private List<List<MediaQuery>> importMedia;
    private RuleList rules;

    // block preparator
    private Preparator preparator;
    private List<MediaQuery> wrapMedia;

    //prevent imports inside the style sheet
    @SuppressWarnings("unused")
    private boolean preventImports = false;

    //logger
    private org.slf4j.Logger log;


    //temp variables for construction
    CombinedSelector tmpCombinedSelector;
    Selector tmpSelector;
    List<CombinedSelector> tmpCombinedSelectorList;
    List<Declaration> tmpDeclarations;
    declaration_scope tmpDeclarationScope;
    atstatement_scope tmpAtStatementOrRuleSetScope;
    RuleList tmpRuleList;
    List<RuleMargin> tmpMargins;
    RuleMargin tmpMarginRule;

    //    TermsScope tmpTermScope;
    protected static class terms_scope {
        List<cz.vutbr.web.css.Term<?>> list;
        cz.vutbr.web.css.Term<?> term;
        cz.vutbr.web.css.Term.Operator op;
        int unary;
        boolean dash;
    }

    protected Stack<terms_scope> terms_stack = new Stack<terms_scope>();
    List<cz.vutbr.web.css.Term<?>> tmpTermList;

    Boolean stmtIsValid = false;
    Selector.Combinator tmpCombinator = null;
    mediaquery_scope tmpMediaQueryScope;
    List<MediaQuery> mediaQueryList = null;
    MediaExpression tmpMediaExpression = null;
    private boolean isInlineStyle = false;


    // list of context childern without spaces modified on enterEveryRule generated from ctx.childern
    List childernWithoutSpaces;

    public CSSParserListenerImpl(Preparator preparator, List<MediaQuery> wrapMedia) {
        this.preparator = preparator;
        this.wrapMedia = wrapMedia;
        this.importPaths = new ArrayList<>();
        this.importMedia = new ArrayList<>();
        this.rules = new RuleArrayList();
        this.log = org.slf4j.LoggerFactory.getLogger(getClass());
//        log.info("listener created");
    }

    public CSSParserListenerImpl() {
        this.log = org.slf4j.LoggerFactory.getLogger(getClass());
    }

    private int spacesCounter = 0;

    private String generateSpaces(int count) {
        String spaces = "";
        for (int i = 0; i < count; i++) {
            spaces += " ";
        }
        return spaces;
    }

    public RuleList getRules() {
        return rules;
    }

    public List<MediaQuery> getMedia() {
        return mediaQueryList;
    }

    public List<String> getImportPaths() {
        return importPaths;
    }

    public List<List<MediaQuery>> getImportMedia() {
        return importMedia;
    }

    protected void logEnter(String msg) {
        log.info("Enter: " + generateSpaces(spacesCounter) + "{}", msg);
    }


    @SuppressWarnings("unused")
    protected void logLeave() {
        //        log.info("Leave: " + generateSpaces(spacesCounter) + "{}", msg);
    }

    @SuppressWarnings("unused")
    protected void logLeave(String msg) {
        log.info("Leave: " + generateSpaces(spacesCounter) + "{}", msg);
//        spacesCounter -= 2;
    }


    //override generated methods

    @Override
    public void enterInlinestyle(CSSParser.InlinestyleContext ctx) {
        logEnter("inlinestyle: " + ctx.getText());
        isInlineStyle = true;
    }

    @Override
    public void exitInlinestyle(CSSParser.InlinestyleContext ctx) {
//        logLeave("inlinestyle"  +ctx.getText());
        log.info("EXITING INLINESTYLE ----------------------------------");
        isInlineStyle = false;
        tmpDeclarations = null;
    }

    @Override
    public void enterStylesheet(CSSParser.StylesheetContext ctx) {
        logEnter("stylesheet: " + ctx.getText());

    }

    @Override
    public void exitStylesheet(CSSParser.StylesheetContext ctx) {
        log.debug("\n***\n{}\n***\n", rules);
        logLeave("stylesheet");
        log.info("EXITING STYLESHEET ----------------------------------");
    }

    @Override
    public void enterStatement(CSSParser.StatementContext ctx) {
        logEnter("statement: " + ctx.getText());
        stmtIsValid = true;
        tmpRuleList = new RuleArrayList();
    }

    @Override
    public void exitStatement(CSSParser.StatementContext ctx) {
        //statement: ruleset | atstatement
        if (ctx.ruleset() != null) {
            if (stmtIsValid) {
                for (RuleBlock<?> rule : tmpRuleList) {
                    if (rule != null) {
                        log.debug("exitStatement |ADDING statement {}", rule);
                        rules.add(rule);
                    } else {
                        log.debug("exitStatement |ommited statement :{}", rule);
                    }

                }
            } else {
                log.debug("exitStatement | statement is not valid, so not adding it");
            }
        } else {
            if (tmpAtStatementOrRuleSetScope.stm == null) {
                //Todo: stmtIsValid a tmpAtStatementOrRuleSetScope.stm == null sjednotit !
                log.error("exitStatement | stmt not valid ");
            } else {
                log.debug("exitStatement | ADDING statement {}", tmpAtStatementOrRuleSetScope.stm);
                rules.add(tmpAtStatementOrRuleSetScope.stm);
            }
        }

    }

    @Override
    public void enterRuleset(CSSParser.RulesetContext ctx) {
        logEnter("ruleset: " + ctx.getText());
        stmtIsValid = true;
        //init scope
        tmpAtStatementOrRuleSetScope = new atstatement_scope();
        tmpCombinedSelectorList = new ArrayList<>();
    }

    @Override
    public void exitRuleset(CSSParser.RulesetContext ctx) {
//        logLeave("ruleset"  +ctx.getText());
        //complete ruleset and add to ruleList
        tmpAtStatementOrRuleSetScope.stm = preparator.prepareRuleSet(tmpCombinedSelectorList, tmpDeclarations, (this.wrapMedia != null && !this.wrapMedia.isEmpty()), this.wrapMedia);
        if (tmpAtStatementOrRuleSetScope.stm != null && !ctxHasErrorNode(ctx)) {
            tmpRuleList.add(tmpAtStatementOrRuleSetScope.stm);
        }
        //cleanup tmpDeclarations
        tmpDeclarations = null;
    }

    @Override
    public void enterDeclarations(CSSParser.DeclarationsContext ctx) {
        logEnter("declarations: " + ctx.getText());
        tmpDeclarations = new ArrayList<>();
        //if is ruleset
        // exit combinator if needed
    }

    @Override
    public void exitDeclarations(CSSParser.DeclarationsContext ctx) {
//        logLeave("declarations"  +ctx.getText());
        if (isInlineStyle) {
            RuleBlock<?> rb = preparator.prepareInlineRuleSet(tmpDeclarations, null);
            if (rb != null) {
                rules.add(rb);
            }
        }
    }

    @Override
    public void enterDeclaration(CSSParser.DeclarationContext ctx) {
        logEnter("declaration: " + ctx.getText());
        tmpDeclarationScope = getDeclarationScopeAndInit();
        if (ctxHasErrorNode(ctx) || ctx.noprop() != null) {
            log.debug("invalidating declaration");
            tmpDeclarationScope.invalid = true;
        }
    }

    @Override
    public void exitDeclaration(CSSParser.DeclarationContext ctx) {
        if (!tmpDeclarationScope.invalid) {
            log.debug("Returning declaration: {}.", tmpDeclarationScope.d);
            tmpDeclarations.add(tmpDeclarationScope.d);
            log.debug("Inserted declaration #{} ", tmpDeclarations.size() + 1);
        } else {
            log.debug("Declaration was invalidated or already invalid");
        }

    }

    @Override
    public void enterImportant(CSSParser.ImportantContext ctx) {
        if (ctxHasErrorNode(ctx)) {
            tmpDeclarationScope.invalid = true;
        } else {
            tmpDeclarationScope.d.setImportant(true);
            log.debug("Setting property to IMPORTANT");
        }
    }

    @Override
    public void exitImportant(CSSParser.ImportantContext ctx) {

    }

    @Override
    public void enterProperty(CSSParser.PropertyContext ctx) {
        //done
        logEnter("property: " + ctx.getText());
        String property = unescapeString(ctx.IDENT().getText());
        if (ctx.MINUS() != null) {
            property = ctx.MINUS().getText() + property;
        }
        tmpDeclarationScope.d.setProperty(property);
        Token token = ctx.IDENT().getSymbol();
        tmpDeclarationScope.d.setSource(extractSource((CSSToken) token));
        log.debug("Setting property: {}", tmpDeclarationScope.d.getProperty());
    }

    @Override
    public void exitProperty(CSSParser.PropertyContext ctx) {
        //done - empty stuff
    }

    protected static class TermsScope {
        List<Term<?>> list;
        Term<?> term;
        Term.Operator op;
        int unary;
        boolean dash;
    }

    @Override
    public void enterTerms(CSSParser.TermsContext ctx) {
        logEnter("terms: " + ctx.getText());
        tmpTermList = new ArrayList<>();
        terms_stack.push(new terms_scope());
        terms_stack.peek().list = new ArrayList<>();
        terms_stack.peek().term = null;
        terms_stack.peek().op = null;
        terms_stack.peek().unary = 1;
        terms_stack.peek().dash = false;
    }

    @Override
    public void exitTerms(CSSParser.TermsContext ctx) {
        log.debug("Totally added {} terms", terms_stack.peek().list.size());
        tmpTermList = terms_stack.peek().list;
        tmpDeclarationScope.d.replaceAll(terms_stack.peek().list);
        terms_stack.pop();
        logLeave("terms");
    }

    @Override
    public void enterTermValuePart(CSSParser.TermValuePartContext ctx) {
        logEnter("termValuePart: " + ctx.getText());
    }

    @Override
    public void exitTermValuePart(CSSParser.TermValuePartContext ctx) {
        logLeave("termValuePart: " + ctx.getText());
    }

    @Override
    public void enterTermCurlyBlock(CSSParser.TermCurlyBlockContext ctx) {

    }

    @Override
    public void exitTermCurlyBlock(CSSParser.TermCurlyBlockContext ctx) {

    }

    @Override
    public void enterTermAtKeyword(CSSParser.TermAtKeywordContext ctx) {

    }

    @Override
    public void exitTermAtKeyword(CSSParser.TermAtKeywordContext ctx) {

    }

/*
    @Override
    public void enterTerm(CSSParser.TermContext ctx) {
        logEnter("term: " + ctx.getText());
//        tmpTerm =
    }

    @Override
    public void exitTerm(CSSParser.TermContext ctx) {

    }
*/

    @Override
    public void enterFunct(CSSParser.FunctContext ctx) {

        logEnter("funct: " + ctx.getText());
    }

    @Override
    public void exitFunct(CSSParser.FunctContext ctx) {
        if (ctx.EXPRESSION() != null) {
            //EXPRESSION
            throw new UnsupportedOperationException("EXPRESSIONS are not allowed yet");
            //todo
        } else {
            String fname = unescapeString(ctx.FUNCTION().getText());
            if (fname.equalsIgnoreCase("url")) {
                if (terms_stack.peek().unary == -1 || tmpTermList == null || tmpTermList.size() != 1) {
                    tmpDeclarationScope.invalid = true;
                } else {
                    Term<?> term = tmpTermList.get(0);
                    if (term instanceof TermString) {
                        log.debug("creating url");
                        terms_stack.peek().term = tf.createURI(unescapeString(((TermString) term).getValue()), extractBase(ctx.FUNCTION()));
                    } else
                        tmpDeclarationScope.invalid = true;
                }
            } else {
                TermFunction function = tf.createFunction();
                log.debug("function name to " + fname);
                function.setFunctionName(fname);
                if (terms_stack.peek().unary == -1) //if started with minus, add the minus to the function name
                    function.setFunctionName('-' + function.getFunctionName());
                if (tmpTermList != null)
                    function.setValue(tmpTermList);
                terms_stack.peek().term = function;

            }
            //function
        }
        logLeave("funct");
    }

    @Override
    public void enterValuepart(CSSParser.ValuepartContext ctx) {
        logEnter("valuepart: >" + ctx.getText() + "<");
        if (ctx.MINUS() != null) {
            terms_stack.peek().unary = -1;
        }
        if (ctx.COMMA() != null) {
            log.debug("VP - comma");
            terms_stack.peek().op = Term.Operator.COMMA;
        } else if (ctx.SLASH() != null) {
            terms_stack.peek().op = Term.Operator.SLASH;
        } else if (ctx.string() != null) {
            //string
            log.debug("VP - string");
            terms_stack.peek().term = tf.createString(unescapeString(ctx.string().getText()));
        } else if (ctx.IDENT() != null) {
            log.debug("VP - ident");
            terms_stack.peek().term = tf.createIdent(unescapeString(ctx.IDENT().getText()), terms_stack.peek().dash);
        } else if (ctx.HASH() != null) {
            log.debug("VP - hash");
            terms_stack.peek().term = tf.createColor(ctx.HASH().getText());
        } else if (ctx.PERCENTAGE() != null) {
            log.debug("VP - percentage");
            terms_stack.peek().term = tf.createPercent(ctx.PERCENTAGE().getText(), terms_stack.peek().unary);
        } else if (ctx.DIMENSION() != null) {
            log.debug("VP - dimension");
            terms_stack.peek().term = tf.createDimension(ctx.DIMENSION().getText(), terms_stack.peek().unary);
        } else if (ctx.NUMBER() != null) {
            log.debug("VP - number");
            terms_stack.peek().term = tf.createNumeric(ctx.NUMBER().getText(), terms_stack.peek().unary);
        } else if (ctx.URI() != null) {
            log.debug("VP - uri");
            terms_stack.peek().term = tf.createURI(unescapeString(ctx.URI().getText()));//todo check
        } else if (ctx.funct() != null) {
            terms_stack.peek().term = null;
            //served in function
            log.debug("function is server later");
        } else {
            log.error("unhandled valueparts");
            terms_stack.peek().term = null;
        }

    }

    @Override
    public void exitValuepart(CSSParser.ValuepartContext ctx) {
        //try convert color from current term
        if (terms_stack.peek().term != null) {
            TermColor termColor = null;
            if (terms_stack.peek().term instanceof TermIdent) { // red
                termColor = tf.createColor((TermIdent) terms_stack.peek().term);
            } else if (terms_stack.peek().term instanceof TermFunction) { // rgba(0,0,0)
                termColor = tf.createColor((TermFunction) terms_stack.peek().term);

            }
            if (termColor != null) {
                log.debug("term color is OK - creating - " + termColor.toString());
                terms_stack.peek().term = termColor;
            }

        }

        //save valuepartr to termslist
        if (/*!declartionstack.invalid && */ terms_stack.peek().term != null) {
            log.debug("adding valuepart " + ctx.getText());
            //set operator and add term to term list
            terms_stack.peek().term.setOperator(terms_stack.peek().op);
            terms_stack.peek().list.add(terms_stack.peek().term);
            tmpTermList.add(terms_stack.peek().term);
            //reinitialization
            terms_stack.peek().op = Term.Operator.SPACE;
            terms_stack.peek().unary = 1;
            terms_stack.peek().dash = false;
        } else {
            log.debug("tmpTermScope.term is null");
        }
        terms_stack.peek().term = null;
        logLeave("valuePart");
    }

    @Override
    public void enterCombined_selector(CSSParser.Combined_selectorContext ctx) {
        String combinedSelector = ctx.getText();
        logEnter("combinedselector : : " + combinedSelector);
        tmpCombinedSelector = (CombinedSelector) rf.createCombinedSelector().unlock();
    }

    @Override
    public void exitCombined_selector(CSSParser.Combined_selectorContext ctx) {
        tmpCombinedSelectorList.add(tmpCombinedSelector);
        tmpCombinator = null;
    }

    @Override
    public void enterCombinatorChild(CSSParser.CombinatorChildContext ctx) {
        tmpCombinator = Selector.Combinator.CHILD;
    }

    @Override
    public void exitCombinatorChild(CSSParser.CombinatorChildContext ctx) {

    }

    @Override
    public void enterCombinatorAdjacent(CSSParser.CombinatorAdjacentContext ctx) {
        tmpCombinator = Selector.Combinator.ADJACENT;
    }

    @Override
    public void exitCombinatorAdjacent(CSSParser.CombinatorAdjacentContext ctx) {

    }

    @Override
    public void enterCombinatorPreceding(CSSParser.CombinatorPrecedingContext ctx) {
        tmpCombinator = Selector.Combinator.PRECEDING;
    }

    @Override
    public void exitCombinatorPreceding(CSSParser.CombinatorPrecedingContext ctx) {

    }

    @Override
    public void enterCombinatorDescendant(CSSParser.CombinatorDescendantContext ctx) {
        tmpCombinator = Selector.Combinator.DESCENDANT;
    }

    @Override
    public void exitCombinatorDescendant(CSSParser.CombinatorDescendantContext ctx) {

    }


    @Override
    public void enterSelectorWithIdOrAsterisk(CSSParser.SelectorWithIdOrAsteriskContext ctx) {
        enterSelector();
        Selector.ElementName en = rf.createElement(unescapeString(ctx.getChild(0).getText()));
        log.debug("Adding selector: {}", en.getName());
        tmpSelector.add(en);
    }

    @Override
    public void exitSelectorWithIdOrAsterisk(CSSParser.SelectorWithIdOrAsteriskContext ctx) {
        exitSelector(ctx);
    }

    @Override
    public void enterSelectorWithoutIdOrAsterisk(CSSParser.SelectorWithoutIdOrAsteriskContext ctx) {
        enterSelector();
    }

    @Override
    public void exitSelectorWithoutIdOrAsterisk(CSSParser.SelectorWithoutIdOrAsteriskContext ctx) {
        exitSelector(ctx);
    }

    // on every enterSelecotr submethod
    private void enterSelector() {
        logEnter("selector");
        tmpSelector = (Selector) rf.createSelector().unlock();
        if (tmpCombinator != null) {
            tmpSelector.setCombinator(tmpCombinator);
        }
    }

    // on every exitSelecotr submethod
    private void exitSelector(CSSParser.SelectorContext ctx) {

        tmpCombinedSelector.add(tmpSelector);
    }

    //////////////
    //  SELPART
    /////////////
    @Override
    public void enterSelpartId(CSSParser.SelpartIdContext ctx) {
        enterSelpart(ctx);
        tmpSelector.add(rf.createID(unescapeString(ctx.getText())));
    }

    @Override
    public void exitSelpartId(CSSParser.SelpartIdContext ctx) {
        //do nothing
    }

    @Override
    public void enterSelpartClass(CSSParser.SelpartClassContext ctx) {
        log.debug("Enter selpart - class {}", ctx.getText());
        enterSelpart(ctx);
        tmpSelector.add(rf.createClass(unescapeString(ctx.getText())));
    }

    @Override
    public void exitSelpartClass(CSSParser.SelpartClassContext ctx) {
        //do nothing
    }

    @Override
    public void enterSelpartAttrib(CSSParser.SelpartAttribContext ctx) {
        enterSelpart(ctx);
        //do nothing
    }

    @Override
    public void exitSelpartAttrib(CSSParser.SelpartAttribContext ctx) {
        //do nothing
    }

    @Override
    public void enterSelpartPseudo(CSSParser.SelpartPseudoContext ctx) {
        enterSelpart(ctx);
    }

    @Override
    public void exitSelpartPseudo(CSSParser.SelpartPseudoContext ctx) {
        //do nothing
    }

    @Override
    public void enterSelpartInvalid(CSSParser.SelpartInvalidContext ctx) {
        enterSelpart(ctx);
    }

    @Override
    public void exitSelpartInvalid(CSSParser.SelpartInvalidContext ctx) {
        //do nothing
    }


    // on enter every selpart submethod
    private void enterSelpart(ParserRuleContext ctx) {
        logEnter("selpart: " + ctx.getText());
    }

    @Override
    public void enterAttribute(CSSParser.AttributeContext ctx) {
        //attributes can be like [attr] or [attr operator value]
        // see http://www.w3.org/TR/CSS2/selector.html#attribute-selectors
        logEnter("attribute: " + ctx.getText());
        //initialize attribute
        String attributeName = unescapeString(ctx.children.get(0).getText());
        String value = null;
        boolean isStringValue = false;
        Selector.Operator op = Selector.Operator.NO_OPERATOR;
        //is attribute like [attr=value]
        if (childernWithoutSpaces.size() == 3) {
            CommonToken opToken = (CommonToken) ((TerminalNodeImpl) childernWithoutSpaces.get(1)).symbol;
            isStringValue = (childernWithoutSpaces.get(2) instanceof CSSParser.StringContext);
            if (isStringValue) {
                value = ((ParserRuleContext) childernWithoutSpaces.get(2)).getText();
            } else {

                value = ((TerminalNode) childernWithoutSpaces.get(2)).getText();
            }
            value = unescapeString(value);
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
        Selector.ElementAttribute elemAttr = rf.createAttribute(value, isStringValue, op, attributeName);
        tmpSelector.add(elemAttr);
    }

    @Override
    public void exitAttribute(CSSParser.AttributeContext ctx) {

    }

    @Override
    //pseudocolon (IDENT | FUNCTION S*  (IDENT | MINUS? NUMBER | MINUS? INDEX) S* RPAREN)
    public void enterPseudo(CSSParser.PseudoContext ctx) {
        if (ctxHasErrorNode(ctx)) {
            stmtIsValid = false;
        }
        logEnter("pseudo: " + ctx.getText());
        //todo: check if is attribute building
        // childcount == 2
        //first item is pseudocolon | : or ::
        Boolean isPseudoElem = ctx.getChild(0).getText().length() != 1;
        Selector.PseudoPage tmpPseudo;
        String first = unescapeString(ctx.getChild(1).getText());
        if (ctx.FUNCTION() == null) {
            // ident
            tmpPseudo = rf.createPseudoPage(first, null);
            if (tmpPseudo == null || tmpPseudo.getDeclaration() == null) {
                log.error("invalid pseudo declaration: " + first);
                stmtIsValid = false;
                tmpPseudo = null;
            } else if (isPseudoElem && !tmpPseudo.getDeclaration().isPseudoElement()) {
                log.error("pseudo class cannot be used as pseudo element");
                tmpPseudo = null; //* pseudoClasses are not allowed here *//*
            }
        } else {
            //function
            if (isPseudoElem) {
                log.error("pseudo element cannot be used as a function");
                tmpPseudo = null;
            } else {
                //function
                String func = first;
                String value = "";
                if (ctx.IDENT() != null) {
                    value = ctx.IDENT().getText();
                } else {
                    if (ctx.MINUS() != null) {
                        value = "-";
                    }
                    if (ctx.NUMBER() != null) {
                        value += ctx.NUMBER().getText();
                    } else if (ctx.INDEX() != null) {
                        value += ctx.INDEX().getText();
                    } else {
                        throw new UnsupportedOperationException("unknown state");
                    }
                }
                tmpPseudo = rf.createPseudoPage(value, func);
            }
        }
        //todo: kdyz bude tmpPseudo null, tak co potom
        //kontrola, zda probehla sematicka kontrola spravne
        if (tmpPseudo != null) {
            log.debug("Setting pseudo: {}", tmpPseudo.toString());
            tmpSelector.add(tmpPseudo);
        } else {
            //tmpDeclarationScope == null
//            tmpDeclarationScope.invalid = true;
        }
    }

    private boolean ctxHasErrorNode(ParserRuleContext ctx) {
        for (int i = 0; i < ctx.children.size(); i++) {
            if (ctx.getChild(i) instanceof ErrorNode) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void exitPseudo(CSSParser.PseudoContext ctx) {
        //check if is in declaration
        if (tmpDeclarationScope != null && tmpDeclarationScope.d != null && tmpDeclarationScope.invalid) {
            stmtIsValid = false;
        }
    }

    @Override
    public void enterPseudocolon(CSSParser.PseudocolonContext ctx) {
        logEnter("pseudocolon: " + ctx.getText());
    }

    @Override
    public void exitPseudocolon(CSSParser.PseudocolonContext ctx) {

    }

    @Override
    public void enterString(CSSParser.StringContext ctx) {
        logEnter("string: " + ctx.getText());
    }

    @Override
    public void exitString(CSSParser.StringContext ctx) {

    }

    @Override
    public void enterAny(CSSParser.AnyContext ctx) {
        logEnter("any: " + ctx.getText());
    }

    @Override
    public void exitAny(CSSParser.AnyContext ctx) {

    }

    @Override
    public void enterNostatement(CSSParser.NostatementContext ctx) {
        logEnter("nostatement: " + ctx.getText());
        stmtIsValid = false;
    }

    @Override
    public void exitNostatement(CSSParser.NostatementContext ctx) {

    }

    @Override
    public void enterNoprop(CSSParser.NopropContext ctx) {
        logEnter("noprop: " + ctx.getText());
    }

    @Override
    public void exitNoprop(CSSParser.NopropContext ctx) {

    }

    @Override
    public void enterNorule(CSSParser.NoruleContext ctx) {
        logEnter("norule: " + ctx.getText());
    }

    @Override
    public void exitNorule(CSSParser.NoruleContext ctx) {

    }


    //<editor-fold desc="nomediaquery - done">
    @Override
    public void enterNomediaquery(CSSParser.NomediaqueryContext ctx) {
        // done in enterMedia_term
    }

    @Override
    public void exitNomediaquery(CSSParser.NomediaqueryContext ctx) {
        // done
    }
    //</editor-fold>

    @Override
    public void enterAtstatement(CSSParser.AtstatementContext ctx) {
        logEnter("atstatement: " + ctx.getText());
        //init scope
        tmpAtStatementOrRuleSetScope = new atstatement_scope();


    }

    @Override
    public void exitAtstatement(CSSParser.AtstatementContext ctx) {
        log.debug("exit atstatement: " + ctx.getText());
        if (ctxHasErrorNode(ctx)) {
            log.debug("atstatement is not valid ");
            return;
        }
        if (ctx.CHARSET() != null) {

        } else if (ctx.IMPORT() != null) {
            log.debug("Adding import: {}", ctx.import_uri().getText());
            importMedia.add(mediaQueryList);
            importPaths.add(ctx.import_uri().getText());

        } else if (ctx.page() != null) {

        } else if (ctx.VIEWPORT() != null) {

        } else if (ctx.FONTFACE() != null) {

        } else if (ctx.MEDIA() != null) {
            log.debug("exitAtstatement MEDIA");
            if (ctx.media() == null) {
                //media is not set, set empty
                mediaQueryList = new ArrayList<>();
            }
            List<RuleSet> mediaRulesList = new ArrayList<>();
            if (ctx.media_rule() != null) {
                for (RuleBlock<?> rule : tmpRuleList) {
                    mediaRulesList.add((RuleSet) rule);
                }

            }
            tmpAtStatementOrRuleSetScope.stm = preparator.prepareRuleMedia(mediaRulesList, mediaQueryList);
            this.preventImports = true;
        } else {
            //unknown atrule
            log.debug("Skipping invalid at statement");
            tmpAtStatementOrRuleSetScope.stm = null;
        }
    }

    @Override
    public void enterImport_uri(CSSParser.Import_uriContext ctx) {
        logEnter("enterImportUri");

    }

    @Override
    public void exitImport_uri(CSSParser.Import_uriContext ctx) {

    }

    @Override
    public void enterPage(CSSParser.PageContext ctx) {
        logEnter("page: " + ctx.getText());
        //initialize margin rules
        tmpMargins = null;
        tmpDeclarations = null;
    }

    @Override
    public void exitPage(CSSParser.PageContext ctx) {
        String name = null, pseudo = null;
        if (ctx.IDENT() != null) {
            name = unescapeString(ctx.IDENT().getText());
        }
        if (ctx.page_pseudo() != null) {
            pseudo = unescapeString(ctx.page_pseudo().getText());
        }

        RuleBlock<?> rb = preparator.prepareRulePage(tmpDeclarations, tmpMargins, name, pseudo);
        if (rb != null) {
            rules.add(rb);
        }
        this.preventImports = true;
    }

    @Override
    public void enterPage_pseudo(CSSParser.Page_pseudoContext ctx) {
        logEnter("page_pseudo: " + ctx.getText());
    }

    @Override
    public void exitPage_pseudo(CSSParser.Page_pseudoContext ctx) {

    }

    @Override
    public void enterMargin_rule(CSSParser.Margin_ruleContext ctx) {
        logEnter("margin_rule: " + ctx.getText());
        if (tmpMargins == null) {
            tmpMargins = new ArrayList<>();
        }
    }

    @Override
    public void exitMargin_rule(CSSParser.Margin_ruleContext ctx) {
        if (tmpMarginRule != null) {
            tmpMargins.add(tmpMarginRule);
            log.debug("Inserted margin rule #{} into @page", tmpMargins.size() + 1);
            tmpMarginRule = null;
        }
    }

    @Override
    public void enterInlineset(CSSParser.InlinesetContext ctx) {
        logEnter("inlineset: " + ctx.getText());
    }

    @Override
    public void exitInlineset(CSSParser.InlinesetContext ctx) {
    }

    private enum MediaQueryState {START, TYPE, AND, EXPR, TYPEOREXPR}

    protected static class mediaquery_scope {
        cz.vutbr.web.css.MediaQuery q;
        MediaQueryState state;
        boolean invalid;
    }

    @Override
    public void enterMedia(CSSParser.MediaContext ctx) {
        logEnter("media: " + ctx.getText());
        mediaQueryList = new ArrayList<>();
    }

    @Override
    public void exitMedia(CSSParser.MediaContext ctx) {
        tmpMediaQueryScope = null;
        log.debug("Totally returned {} media queries.", mediaQueryList.size());
        logLeave("media");
    }

    @Override
    public void enterMedia_query(CSSParser.Media_queryContext ctx) {
        logEnter("media_query: " + ctx.getText());
        tmpMediaQueryScope = new mediaquery_scope();
        tmpMediaQueryScope.q = rf.createMediaQuery();
        tmpMediaQueryScope.q.unlock();
        tmpMediaQueryScope.state = MediaQueryState.START;
        tmpMediaQueryScope.invalid = false;
    }

    @Override
    public void exitMedia_query(CSSParser.Media_queryContext ctx) {
        logLeave("exitMedia_query1");
        if (tmpMediaQueryScope.invalid) {
            ///mediaquery invalid add NOT ALL
            tmpMediaQueryScope.q = rf.createMediaQuery();
            tmpMediaQueryScope.q.unlock();
            tmpMediaQueryScope.q.setType("all");
            tmpMediaQueryScope.q.setNegative(true);
            log.debug("mediaQuery INVALID - addding NOT ALL");
        }
        log.debug("Adding media query {}", tmpMediaQueryScope.q);
        mediaQueryList.add(tmpMediaQueryScope.q);
    }

    @Override
    public void enterMedia_term(CSSParser.Media_termContext ctx) {
        logEnter("media_term: " + ctx.getText());
        stmtIsValid = true;
        if (ctx.IDENT() != null) {
            log.debug("mediaterm ident");
            String m = extractTextUnescaped(ctx.IDENT().getText());
            MediaQueryState state = tmpMediaQueryScope.state;
            if (m.equalsIgnoreCase("ONLY") && state == MediaQueryState.START) {
                tmpMediaQueryScope.state = MediaQueryState.TYPEOREXPR;
            } else if (m.equalsIgnoreCase("NOT") && state == MediaQueryState.START) {
                tmpMediaQueryScope.q.setNegative(true);
                tmpMediaQueryScope.state = MediaQueryState.TYPEOREXPR;
            } else if (m.equalsIgnoreCase("AND") && state == MediaQueryState.AND) {
                tmpMediaQueryScope.state = MediaQueryState.EXPR;
            } else if (state == MediaQueryState.START
                    || state == MediaQueryState.TYPE
                    || state == MediaQueryState.TYPEOREXPR) {
                tmpMediaQueryScope.q.setType(m);
                tmpMediaQueryScope.state = MediaQueryState.AND;
            } else {
                log.debug("Invalid media query: found ident: {} state: {}", m, state);
                tmpMediaQueryScope.invalid = true;
            }
        } else if (ctx.media_expression() != null) {
            // in enterMedia_expression
            // empty here
        } else if (ctx.nomediaquery() != null) {
            //nomediaquery -> mediaquery is invalid
            tmpMediaQueryScope.invalid = true;
        }
    }

    @Override
    public void exitMedia_term(CSSParser.Media_termContext ctx) {
        if (ctx.media_expression() != null) {
            if (tmpMediaQueryScope.state == MediaQueryState.START
                    || tmpMediaQueryScope.state == MediaQueryState.EXPR
                    || tmpMediaQueryScope.state == MediaQueryState.TYPEOREXPR) {
                if (tmpMediaExpression.getFeature() != null) //the expression is valid
                {
                    tmpMediaQueryScope.q.add(tmpMediaExpression);
                    tmpMediaQueryScope.state = MediaQueryState.AND;
                } else {
                    log.trace("Invalidating media query for invalud expression");
                    tmpMediaQueryScope.invalid = true;
                }
            } else {
                log.trace("Invalid media query: found expr, state: {}", tmpMediaQueryScope.state);
                tmpMediaQueryScope.invalid = true;
            }
        }
    }

    @Override
    public void enterMedia_expression(CSSParser.Media_expressionContext ctx) {
        logEnter("media_expression: " + ctx.getText());

        //create temp media expression storage
        tmpMediaExpression = rf.createMediaExpression();
        //create temp declaration storage
        tmpDeclarationScope = getDeclarationScopeAndInit();
        //set property to declaration
        tmpDeclarationScope.d.setProperty(unescapeString(ctx.IDENT().getText()));
        Token token = ctx.IDENT().getSymbol();
        tmpDeclarationScope.d.setSource(extractSource((CSSToken) token));
    }

    @Override
    public void exitMedia_expression(CSSParser.Media_expressionContext ctx) {
        if (ctx.terms() != null) {
            // terms were specified so set terms list
            tmpDeclarationScope.d.replaceAll(tmpTermList);
        }
        if (tmpDeclarationScope.d != null) { //if the declaration is valid
            tmpMediaExpression.setFeature(tmpDeclarationScope.d.getProperty());
            tmpMediaExpression.replaceAll(tmpDeclarationScope.d);
        }
        if (ctxHasErrorNode(ctx)) {
            log.debug("media_expression is invalid");
            tmpMediaQueryScope.invalid = true;
        }
    }

    @Override
    public void enterMedia_rule(CSSParser.Media_ruleContext ctx) {
        logEnter("media_rule: " + ctx.getText());
    }

    @Override
    public void exitMedia_rule(CSSParser.Media_ruleContext ctx) {
//        if (tmpAtStatementRules == null) {
//            tmpAtStateme
//        }
        List<RuleSet> tmpAtStatementRules = null;
    }

    @Override
    public void enterUnknown_atrule(CSSParser.Unknown_atruleContext ctx) {
        logEnter("unknown_atrule: " + ctx.getText());
    }

    @Override
    public void exitUnknown_atrule(CSSParser.Unknown_atruleContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {
//        log.info("visit Term: >" + terminalNode + "<");
        if (terminalNode.getSymbol().getType() == CSSParser.CDO) {
            return;
        }
    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {
//        log.error("visit Term: " + errorNode);
    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {
        spacesCounter += 2;
        if (parserRuleContext.getChildCount() == 0) {
            return;
        }
        childernWithoutSpaces = filterSpaceTokens(parserRuleContext.children);
    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {
        spacesCounter -= 2;
    }

    @SuppressWarnings("unused")
    private String extractTextUnescaped(String text) {
        return unescapeString(text);
    }

    private String unescapeString(String text) {
        return org.unbescape.css.CssEscape.unescapeCss(text);
    }

    private Declaration.Source extractSource(CSSToken ct) {
        return new Declaration.Source(ct.getBase(), ct.getLine(), ct.getCharPositionInLine());
    }

    private URL extractBase(TerminalNode node) {
        CSSToken ct = (CSSToken) node.getSymbol();
        return ct.getBase();
    }

    protected static class declaration_scope {
        cz.vutbr.web.css.Declaration d;
        boolean invalid;
    }

    private declaration_scope getDeclarationScopeAndInit() {
        declaration_scope tmp = new declaration_scope();
        tmp.d = rf.createDeclaration();
        tmp.invalid = false;
        tmp.d.unlock();
        return tmp;
    }

    protected static class atstatement_scope {
        cz.vutbr.web.css.RuleBlock<?> stm;
    }

    /**
     * remove terminal node emtpy tokens from input list
     *
     * @param inputArrayList original list
     * @return list without terminal node type = S (space)
     */
    private List<ParseTree> filterSpaceTokens(List<ParseTree> inputArrayList) {
        return inputArrayList.stream().filter(
                item -> (
                        (
                                item instanceof TerminalNode &&
                                        ((TerminalNodeImpl) item).getSymbol().getType() != CSSLexer.S) ||
                                !(item instanceof TerminalNode)
                )
        ).collect(Collectors.toList());
    }

}