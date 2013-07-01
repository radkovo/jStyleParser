/**
 * MatchConditionOnElements.java
 *
 * Created on 1.7.2013, 11:26:35 by burgetr
 */
package cz.vutbr.web.csskit;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;

import cz.vutbr.web.css.MatchCondition;
import cz.vutbr.web.css.Selector.PseudoDeclaration;
import cz.vutbr.web.css.Selector.PseudoPage;
import cz.vutbr.web.css.Selector.SelectorPart;

/**
 * A match condition for matching the pseudo classes to particular elements.
 * 
 * @author burgetr
 */
public class MatchConditionOnElements implements MatchCondition
{
    private Map<Element, PseudoDeclaration> matches;
    
    public MatchConditionOnElements()
    {
        matches = null;
    }
    
    public MatchConditionOnElements(Element e, PseudoDeclaration pseudo)
    {
        addMatch(e, pseudo);
    }
    
    public void addMatch(Element e, PseudoDeclaration pseudo)
    {
        if (matches == null)
            matches = new HashMap<Element, PseudoDeclaration>();
        matches.put(e, pseudo);
    }
    
    public boolean isSatisfied(Element e, SelectorPart selpart)
    {
        if (matches != null && selpart instanceof PseudoPage)
        {
            PseudoDeclaration pseudo = matches.get(e);
            if (pseudo != null)
                return pseudo.equals(((PseudoPage) selpart).getDeclaration());
            else
                return false;
        }
        else
            return false;
    }
}
