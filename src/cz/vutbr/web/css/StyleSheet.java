package cz.vutbr.web.css;

import cz.vutbr.web.css.RuleBlock.Priority;


/**
 * Acts as collection of Rules. Remembers last priority used 
 * in style sheet to allow incremental parsing.
 *
 * @author kapy
 */
public interface StyleSheet extends Rule<RuleBlock<?>>{
  
    /**
     * Marks last priority used in this style-sheet
     * @param last Priority mark
     */
    public void markLast(Priority last);
    
    /**
     * Gets last mark priority
     * @return Priority mark
     */
    public Priority getLastMark();
}
