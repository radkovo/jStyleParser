/**
 * 
 */
package cz.vutbr.web.domassign.decode;

import java.util.Map;

import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.css.CSSProperty.BorderRadius;
import cz.vutbr.web.css.Term.Operator;

/**
 * Border radius repeater
 * 
 * @author burgetr
 * 
 */
public class BorderRadiusRepeater extends Repeater {

    public BorderRadiusRepeater() {
        super(4);
        this.type = BorderRadius.class;
        names.add("border-top-left-radius");
        names.add("border-top-right-radius");
        names.add("border-bottom-right-radius");
        names.add("border-bottom-left-radius");
    }

    @Override
    protected boolean operation(int i, Map<String, CSSProperty> properties,
            Map<String, Term<?>> values) {

        Term<?> term = terms.get(i);
        String name = names.get(i);
        
        if (genericTermIdent(type, terms.get(i), AVOID_INH, names.get(i), properties))
        {
            return true;
        }       
        else if (term instanceof TermList)
        {
            properties.put(name, BorderRadius.list_values);
            values.put(name, term);
            return true;
        }
        else
            return false;
    }
    
    /** Decodes the complicated border-radius declaration into four term pairs */
    public boolean repeatOverMultiTermDeclaration(Declaration d,
            Map<String, CSSProperty> properties, Map<String, Term<?>> values)
            throws IllegalArgumentException {

        if (d.size() == 1) //one value - check for inherit
        {
            Term<?> term = d.get(0);
            if(term instanceof TermIdent && CSSProperty.INHERIT_KEYWORD.equalsIgnoreCase(((TermIdent) term).getValue())) {
                CSSProperty property = CSSProperty.Translator.createInherit(type);
                for(int i = 0; i < times; i++) {
                    properties.put(names.get(i), property);
                }
                return true;
            }
        }
        
        //find the slash (if any)
        int slash = -1;
        for (int i = 0; i < d.size(); i++)
        {
            Term<?> term = d.get(i);
            if (term.getOperator() == Operator.SLASH)
            {
                slash = i;
                break;
            }
        }
        if (slash == -1)
        {
            Term<?>[] sterms = createFourTerms(d, 0, d.size());
            for (int i = 0; i < 4; i++)
            {
                TermList list = tf.createList(2);
                list.add(sterms[i]);
                list.add(sterms[i]);
                terms.add(list);
            }
        }
        else
        {
            Term<?>[] sterms1 = createFourTerms(d, 0, slash);
            Term<?>[] sterms2 = createFourTerms(d, slash, d.size());
            for (int i = 0; i < 4; i++)
            {
                TermList list = tf.createList(2);
                list.add(sterms1[i]);
                list.add(sterms2[i]);
                terms.add(list);
            }
        }
        return repeat(properties, values);
    }
    
    private Term<?>[] createFourTerms(Declaration d, int fromIndex, int toIndex)
            throws IllegalArgumentException
    {
        int size = toIndex - fromIndex;
        Term<?>[] ret = new Term<?>[4];
        switch (size) {
        case 1:
            // one term for all value
            ret[0] = ret[1] = ret[2] = ret[3] = d.get(fromIndex); 
            break;
        case 2:
            ret[0] = ret[2] = d.get(fromIndex);
            ret[1] = ret[3] = d.get(fromIndex + 1);
            break;
        case 3:
            ret[0] = d.get(fromIndex);
            ret[1] = ret[3] = d.get(fromIndex+1);
            ret[2] = d.get(fromIndex+2);
            break;
        case 4:
            for (int i = 0; i < 4; i++)
                ret[i] = d.get(fromIndex + i);
            break;
        default:
            throw new IllegalArgumentException(
                    "Invalid length of terms in Repeater.");
        }
        
        //when started by a slash, remove the slash from the terms
        if (fromIndex != 0)
        {
            for (int i = 0; i < 4; i++)
                if (ret[i].getOperator() == Operator.SLASH)
                    ret[i] = stripSlash(ret[i]);
        }
        
        return ret;
    }
    
    private Term<?> stripSlash(Term<?> src)
    {
        if (src.getOperator() == Operator.SLASH)
        {
            if (src instanceof TermLength)
                return tf.createLength((java.lang.Float) src.getValue(), ((TermLength) src).getUnit());
            else if (src instanceof TermPercent)
                return tf.createPercent((java.lang.Float) src.getValue());
            else
                return src;
        }
        else
            return src;
    }
    
}
