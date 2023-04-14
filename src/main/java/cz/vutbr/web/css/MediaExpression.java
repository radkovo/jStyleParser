/**
 * MediaExpression.java
 *
 * Created on 26. 6. 2014, 15:39:25 by burgetr
 */
package cz.vutbr.web.css;

/**
 * Holds the media expression: the tested feature and the values
 * @author burgetr
 */
public interface MediaExpression extends Rule<Term<?>>
{

    /**
     * Obtains the name of the tested feature.
     * @return The feature name
     */
    public String getFeature();
    
    /**
     * Sets the feature name.
     * @param feature The feature name;
     */
    public void setFeature(String feature);
    
    /**
     * Sets the negative flag
     * @param negative
     */
    public void setNegative(boolean negative);

    /**
     * Checks whether the feature is negated
     * @return true when the feature is negated
     */
    public boolean isNegative();

}
