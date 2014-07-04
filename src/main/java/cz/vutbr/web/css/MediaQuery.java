/**
 * MediaQuery.java
 *
 * Created on 26. 6. 2014, 15:34:42 by burgetr
 */
package cz.vutbr.web.css;

/**
 * Holds the media query: the media type and a list of media expressions
 * 
 * @author burgetr
 */
public interface MediaQuery extends Rule<MediaExpression>
{

    /**
     * Sets the negative flag (i.e. the media query starts with NOT)
     * @param negative
     */
    public void setNegative(boolean negative);

    /**
     * Checks whether the query starts with NOT
     * @return true when the rule starts with NOT
     */
    public boolean isNegative();

    /**
     * Sets the media type.
     * @param type
     */
    public void setType(String type);
    
    /**
     * Obtains the declared media type.
     * @return the declared media type or <code>null</code> when no type is declared
     */
    public String getType();
    
}
