/**
 * TermRectImpl.java
 *
 * Created on 22. 3. 2017, 16:06:36 by burgetr
 */
package cz.vutbr.web.csskit;

import java.util.ArrayList;
import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermRect;

/**
 * 
 * @author burgetr
 */
public class TermRectImpl extends TermImpl<List<TermLength>> implements TermRect
{

    public TermRectImpl(TermLength a, TermLength b, TermLength c, TermLength d) {
        value = new ArrayList<>(4);
        value.add(a);
        value.add(b);
        value.add(c);
        value.add(d);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder(OutputUtil.RECT_KEYWORD);
        ret.append(OutputUtil.FUNCTION_OPENING);
        for (int i = 0; i < 4; i++)  {
            if (i != 0)
                ret.append(OutputUtil.SPACE_DELIM);
            Term<?> v = value.get(i);
            ret.append((v == null) ?  "auto" : v.toString());
        }
        ret.append(OutputUtil.FUNCTION_CLOSING);
        return ret.toString();
    }
    
}
