package cz.vutbr.web.cssKit;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.StylesheetNotValidException;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermNumber;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.css.TermString;
import cz.vutbr.web.css.TermUri;
import cz.vutbr.web.cssKit.parser.SimpleNode;
import java.util.ArrayList;

/**
 * Declaration
 * @author Jan Svercl, VUT Brno, 2008
 */
public class DeclarationImpl implements Declaration {

    private String property;
    private ArrayList<Term> termsList = new ArrayList<Term>();
    private boolean important = false;
    private String toString = null;

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
        toString = null;
    }

    public ArrayList<Term> getTermsList() {
        return termsList;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        if (property == null) {
            throw new NullPointerException();
        } else {
            this.property = property;
            toString = null;
        }
    }

    public DeclarationImpl(String property) {
        if (property == null) {
            throw new NullPointerException();
        } else {
            this.property = property;
        }
    }

    protected DeclarationImpl(SimpleNode n) {
        property = ((SimpleNode) n.jjtGetChild(0).jjtGetChild(0)).getImage();

        if (n.jjtGetNumChildren() > 2) { // !IMPORTANT
            if (((SimpleNode) n.jjtGetChild(2)).getType().equals("prio")) {
                important = true;
            }
        }

        Term.EnumOperator tmpOperator = null;
        for (int i = 0; i < n.jjtGetChild(1).jjtGetNumChildren(); i++) {
            SimpleNode cNode = (SimpleNode) n.jjtGetChild(1).jjtGetChild(i);

            if (cNode.getType().equals("operator")) {
                if (cNode.jjtGetNumChildren() == 0) {
                    tmpOperator = Term.EnumOperator.space;
                } else if (((SimpleNode) cNode.jjtGetChild(0)).getType().equals("slash")) {
                    tmpOperator = Term.EnumOperator.slash;
                } else if (((SimpleNode) cNode.jjtGetChild(0)).getType().equals("comma")) {
                    tmpOperator = Term.EnumOperator.comma;
                }

            } else {
                TermColor color = TermColorImpl.getColorByNode(cNode);
                if (color != null) {
                    color.setOperator(tmpOperator);
                    termsList.add(color);
                    continue;
                }
                TermNumber number = TermNumberImpl.getNumberByNode(cNode);
                if (number != null) {
                    number.setOperator(tmpOperator);
                    termsList.add(number);
                    continue;
                }
                TermPercent percent = TermPercentImpl.getPercentByNode(cNode);
                if (percent != null) {
                    percent.setOperator(tmpOperator);
                    termsList.add(percent);
                    continue;
                }
                TermUri uri = TermUriImpl.getUriByNode(cNode);
                if (uri != null) {
                    uri.setOperator(tmpOperator);
                    termsList.add(uri);
                    continue;
                }
                TermString value = TermStringImpl.getStringByNode(cNode);
                if (value != null) {
                    value.setOperator(tmpOperator);
                    termsList.add(value);
                    continue;
                }
                TermIdent ident = TermIdentImpl.getIdentByNode(cNode);
                if (ident != null) {
                    ident.setOperator(tmpOperator);
                    termsList.add(ident);
                    continue;
                }
                TermFunction function = TermFunctionImpl.getFunctionByNode(cNode);
                if (function != null) {
                    function.setOperator(tmpOperator);
                    termsList.add(function);
                    continue;
                }
            }
        }
    }

    public String toString(int depth) {
        if (toString != null) {
            return toString;
        }
        String out = "";
        for (int i = 0; i < (depth); i++) {
            out += "\t";
        }
        out += property + ": ";
        for (Term term : termsList) {
            out += term.toString();
        }
        out += ";\n";
        toString = out;
        return out;
    }

    public void check(String path) throws StylesheetNotValidException {
        if (property.trim().equals("")) {
            throw new StylesheetNotValidException("Empty string as property name", path);
        }
        String pathNew = path + " -> Declaration(" + property + ")";
        if (termsList.isEmpty()) {
            throw new StylesheetNotValidException("Declaration without values", pathNew);
        }
        for (int i = 0; i < termsList.size(); i++) {
            Term term = termsList.get(i);
            if (i == 0 && term.getOperator() != null) {
                term.setOperator(null); //Fix error
            }
            if (i != 0 && term.getOperator() == null) {
                throw new StylesheetNotValidException("Value without operator!", pathNew);
            }
        }
    }
}
