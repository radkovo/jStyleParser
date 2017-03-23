/**
 * TermRect.java
 *
 * Created on 22. 3. 2017, 16:00:02 by burgetr
 */
package cz.vutbr.web.css;

import java.util.List;

/**
 * A term that represents a rect(a, b, c, d) value used in clip: properties.
 * It always holds a list of exactly four lengths.
 * @author burgetr
 */
public interface TermRect extends Term<List<TermLength>>
{

}
