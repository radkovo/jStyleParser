package cz.vutbr.web.csskit.fn;

import cz.vutbr.web.css.*;
import cz.vutbr.web.csskit.TermFunctionImpl;

import java.util.List;

public class VarImpl extends TermFunctionImpl implements TermFunction.Var {

    private String property;
    private Term<?> defaultTerm;
    private boolean emptyValueFallback;

    public VarImpl() {
        setValid(false); //arguments are required
    }

    @Override
    public String getProperty() {
        return property;
    }

    @Override
    public Term<?> getDefaultTerm() {
        return defaultTerm;
    }

    @Override
    public boolean hasEmptyValueFallback() {
        return emptyValueFallback;
    }

    @Override
    public TermList setValue(List<Term<?>> value)
    {
        super.setValue(value);
        List<List<Term<?>>> args = getSeparatedArgs(DEFAULT_ARG_SEP);
        if (args != null && !args.isEmpty()) {
            boolean valid = args.size() < 3;
            List<Term<?>> propertyTerm = args.get(0);
            if (propertyTerm.size() == 1 && propertyTerm.get(0) instanceof TermIdent) {
                TermIdent ident = (TermIdent) propertyTerm.get(0);
                if (ident.getValue().startsWith("--")) {
                    property = ident.getValue();
                } else {
                    valid = false;
                }
            } else {
                valid = false;
            }
            if (args.size() > 1) {
                List<Term<?>> defaultTerms = args.get(1);
                if (defaultTerms.size() == 1) {
                    this.defaultTerm = defaultTerms.get(0);
                } else if (defaultTerms.isEmpty()) {
                    emptyValueFallback = true;
                } else {
                    valid = false;
                }
            }
            setValid(valid);
            if (DEFAULT_ARG_SEP.equals(value.get(value.size() - 1))) {
                // Needed because getSeparatedArgs ignores empty lists of terms
                emptyValueFallback = true;
            }
        }
        return this;
    }
}