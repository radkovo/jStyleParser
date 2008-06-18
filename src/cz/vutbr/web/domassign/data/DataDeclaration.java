package cz.vutbr.web.domassign.data;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.StylesheetNotValidException;
import cz.vutbr.web.css.Term;
import java.util.ArrayList;

/**
 * Declaration
 * @author Jan Svercl, VUT Brno, 2008
 */
public class DataDeclaration implements Declaration {

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

    public DataDeclaration(String property) {
        if (property == null) {
            throw new NullPointerException();
        } else {
            this.property = property;
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
