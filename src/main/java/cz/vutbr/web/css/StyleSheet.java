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
     * The origin of the style sheet (user agent, user, or author). 
     * @author radek
     */
    public enum Origin { AUTHOR, AGENT, USER }
    
    public String getName();
    public void setName(String name);
    
    public CSSComment getComment();
    public void setComment(CSSComment comment);
    
    public CodeLocation getLocation();
    public void setLocation(CodeLocation location);
}
