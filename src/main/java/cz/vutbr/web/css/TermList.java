package cz.vutbr.web.css;

import java.util.List;

/**
 * Holds list of terms and allows access to them as to collection
 * @author kapy
 *
 */
public interface TermList extends Term<List<Term<?>>>, List<Term<?>> {

}
