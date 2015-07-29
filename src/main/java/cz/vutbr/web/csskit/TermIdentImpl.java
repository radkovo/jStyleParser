package cz.vutbr.web.csskit;

import org.unbescape.css.CssEscape;

import cz.vutbr.web.css.TermIdent;

/**
 * TermIdent
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 */
public class TermIdentImpl extends TermImpl<String> implements TermIdent {    
    
    protected TermIdentImpl() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(operator != null) sb.append(operator.value());
        if(value!=null) sb.append(CssEscape.escapeCssIdentifier(value));

        return sb.toString();
    }
    
}
