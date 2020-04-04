/**
 * 
 */
package cz.vutbr.web.domassign.decode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.CSSProperty.BorderColor;
import cz.vutbr.web.css.CSSProperty.BorderStyle;
import cz.vutbr.web.css.CSSProperty.BorderWidth;

/**
 * Border variator. Grammar: [ <border-width> || <border-style> ||
 * <border-top-color> ] | inherit
 * 
 * @author kapy
 * 
 */
public class BorderVariator extends Variator {

    public static final int WIDTH = 0;
    public static final int STYLE = 1;
    public static final int COLOR = 2;

    private List<Repeater> repeaters;

    public BorderVariator() {
        super(3);
        types.add(BorderWidth.class);
        types.add(BorderStyle.class);
        types.add(BorderColor.class);
        repeaters = new ArrayList<Repeater>(variants);
        repeaters.add(new BorderWidthRepeater());
        repeaters.add(new BorderStyleRepeater());
        repeaters.add(new BorderColorRepeater());
    }

    @Override
    protected boolean variant(int variant, IntegerRef iteration,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

        // iteration is not modified in this function
        int i = iteration.get();
        Term<?> term = terms.get(i);
        Repeater r;

        switch (variant) {
        case WIDTH:
            r = repeaters.get(WIDTH);
            r.assignTerms(term, term, term, term);
            return r.repeat(properties, values);
        case STYLE:
            r = repeaters.get(STYLE);
            r.assignTerms(term, term, term, term);
            return r.repeat(properties, values);
        case COLOR:
            r = repeaters.get(COLOR);
            r.assignTerms(term, term, term, term);
            return r.repeat(properties, values);
        default:
            return false;
        }
    }

    /**
     * This method is overriden to use repeaters
     */
    @Override
    protected boolean checkInherit(int variant, Term<?> term,
            Map<String, CSSProperty> properties) {

        // check whether term equals inherit
        if (!(term instanceof TermIdent)
                || !CSSProperty.INHERIT_KEYWORD
                        .equalsIgnoreCase(((TermIdent) term).getValue())) {
            return false;
        }

        if (variant == ALL_VARIANTS) {
            for (int i = 0; i < variants; i++) {
                Repeater r = repeaters.get(i);
                r.assignTerms(term, term, term, term);
                r.repeat(properties, null);
            }
            return true;
        }

        Repeater r = repeaters.get(variant);
        r.assignTerms(term, term, term, term);
        r.repeat(properties, null);
        return true;
    }

    @Override
    public void assignDefaults(Map<String, CSSProperty> properties, Map<String, Term<?>> values)
    {
        for (Repeater r : repeaters)
            r.assignDefaults(properties, values);
    }

}
