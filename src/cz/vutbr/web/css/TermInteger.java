package cz.vutbr.web.css;

/**
 * Holds integer value 
 * @author kapy
 *
 */
public interface TermInteger extends TermLength {
    
    public int getIntValue();
    
    public TermInteger setValue(int value);
    
}
