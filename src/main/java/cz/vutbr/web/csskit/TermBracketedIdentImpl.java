/**
 * TermBracketedIdentImpl.java
 *
 * Created on 30. 11. 2018, 10:28:47 by burgetr
 */
package cz.vutbr.web.csskit;

import org.unbescape.css.CssEscape;

import cz.vutbr.web.css.TermBracketedIdent;

/**
 * 
 * @author burgetr
 */
public class TermBracketedIdentImpl extends TermImpl<String> implements TermBracketedIdent {

    protected TermBracketedIdentImpl() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(operator != null) sb.append(operator.value());
        if(value!=null) {
            sb.append('[');
            sb.append(CssEscape.escapeCssIdentifier(value));
            sb.append(']');
        }

        return sb.toString();
    }

}
