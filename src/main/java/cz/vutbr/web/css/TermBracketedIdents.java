/**
 * TermBracketedIdent.java
 *
 * Created on 30. 11. 2018, 10:26:43 by burgetr
 */
package cz.vutbr.web.css;

import java.util.List;

/**
 * Represents a list of identifiers in square brackets (e.g. in grid-template)
 * 
 * @author burgetr
 */
public interface TermBracketedIdents extends Term<List<TermIdent>>, List<TermIdent> {
}
