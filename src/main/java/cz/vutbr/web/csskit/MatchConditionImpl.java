/**
 * MatchConditionImpl.java
 *
 * Created on 1.7.2013, 11:06:40 by burgetr
 */
package cz.vutbr.web.csskit;

import org.w3c.dom.Element;

import cz.vutbr.web.css.MatchCondition;
import cz.vutbr.web.css.Selector.PseudoDeclaration;
import cz.vutbr.web.css.Selector.PseudoPage;
import cz.vutbr.web.css.Selector.SelectorPart;

/**
 * A default match condition that matches the LINK (or other) pseudo classes to link elements.
 * 
 * @author burgetr
 */
public class MatchConditionImpl implements MatchCondition
{
    PseudoDeclaration pseudo;
    
    /**
     * Creates the fefault condition that matches the LINK pseudo class to links.
     */
    public MatchConditionImpl()
    {
        pseudo = PseudoDeclaration.LINK;
    }
    
    /**
     * Creates the fefault condition that matches the given pseudo class to links.
     * @param pseudoClass the pseudoClass to be matched to links
     */
    public MatchConditionImpl(PseudoDeclaration pseudoClass)
    {
        this.pseudo = pseudoClass;
    }
    
    /**
     * Sets the pseudo class that is matched to links.
     * @param pseudoClass the pseudoClass to be matched to links
     */
    public void setPseudoClass(PseudoDeclaration pseudoClass)
    {
        this.pseudo = pseudoClass;
    }
    
    public boolean isSatisfied(Element e, SelectorPart selpart)
    {
        if (selpart instanceof PseudoPage)
        {
            PseudoDeclaration declaration = ((PseudoPage) selpart).getDeclaration();
            return declaration.equals(pseudo) && e.getTagName().equalsIgnoreCase("a");
        }
        else
            return false;
    }
    
    @Override
    public Object clone() {
    	return new MatchConditionImpl(pseudo);
    }
}
