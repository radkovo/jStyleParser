/**
 * 
 */
package cz.vutbr.web.domassign.decode;

import java.util.Map;

import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermURI;
import cz.vutbr.web.css.CSSProperty.ListStyleImage;
import cz.vutbr.web.css.CSSProperty.ListStylePosition;
import cz.vutbr.web.css.CSSProperty.ListStyleType;

/**
 * Variator for list style. Grammar:
 * 
 * <pre>
 * [ 'list-style-type' || 'list-style-position' || 'list-style-image' ]
 * | inherit
 * 
 * @author kapy
 */
public class ListStyleVariator extends Variator {

    public static final int TYPE = 0;
    public static final int POSITION = 1;
    public static final int IMAGE = 2;

    /*
     * protected String[] names = { "list-style-image", "list-style-type",
     * "list-style-position" };
     */
    public ListStyleVariator() {
        super(3);
        names.add("list-style-type");
        types.add(ListStyleType.class);
        names.add("list-style-position");
        types.add(ListStylePosition.class);
        names.add("list-style-image");
        types.add(ListStyleImage.class);
    }

    @Override
    protected boolean variant(int v, IntegerRef iteration,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values) {

        // we won't use multivalue functionallity
        int i = iteration.get();

        switch (v) {
        case TYPE:
            // list style type
            return genericTermIdent(ListStyleType.class, terms.get(i),
                    AVOID_INH, names.get(TYPE), properties);
        case POSITION:
            // list style position
            return genericTermIdent(ListStylePosition.class, terms.get(i),
                    AVOID_INH, names.get(POSITION), properties);
        case IMAGE:
            // list style image
            return genericTermIdent(types.get(IMAGE), terms.get(i),
                    AVOID_INH, names.get(IMAGE), properties)
                    || genericTerm(TermURI.class, terms.get(i), names
                            .get(IMAGE), ListStyleImage.uri, ValueRange.ALLOW_ALL,
                            properties, values);
        default:
            return false;
        }
    }
}
