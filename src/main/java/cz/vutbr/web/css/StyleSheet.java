package cz.vutbr.web.css;

/**
 * Acts as collection of Rules. Remembers last priority used 
 * in style sheet to allow incremental parsing. The style sheet
 * origin is tracked as well for later rule priority evaluation.
 * The default style sheet origin is "Author". 
 *
 * @author kapy
 */
public interface StyleSheet extends Rule<RuleBlock<?>>{
  
    /**
     * Sets the stylesheet origin.
     * @param o The origin to be set
     */
    public void setOrigin(Origin o);
    	
    /**
     * Gets the origin of the stylesheet.
     * @return the origin of the stylesheet.
     */
    public Origin getOrigin();
    
    /**
     * Filters out rules in this style sheet that do not match the given medium. Guaranteed to not
     * contain any instances of {@link RuleMedia}.
     */
    public StyleSheet filter(MediaSpec medium);

    /**
     * The origin of the style sheet (user agent, user, or author). 
     * @author radek
     */
    public enum Origin { AUTHOR, AGENT, USER };
}
