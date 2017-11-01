/**
 * RuleFontFace.java
 *
 * Created on 1.2.2013, 14:27:04 by burgetr
 */
package cz.vutbr.web.css;

import java.util.List;

import cz.vutbr.web.css.CSSProperty.FontStyle;
import cz.vutbr.web.css.CSSProperty.FontWeight;

/**
 * Contains collection of CSS declarations specified for a given font.
 * 
 * @author burgetr
 */
public interface RuleFontFace extends RuleBlock<Declaration>, PrettyOutput
{
	/**
	 * Gets the font family name
	 * @return Font family name
	 */
    public String getFontFamily();
    
    /**
     * Gets the declared font sources
     * @return The value of the font source URL
     */
    public List<Source> getSources();
    
    /**
     * Gets the font style
     * @return Font style
     */
    public FontStyle getFontStyle();
    
    /**
     * Gets the font weight
     * @return Font weight
     */
    public FontWeight getFontWeight();
    
    //=================================================================================
    
    /**
     * A generic font source.
     * @author burgetr
     */
    public interface Source
    {
    }
    
    /**
     * A local font (src: local()). 
     * @author burgetr
     */
    public interface SourceLocal extends Source
    {
        public String getName();
    }
    
    /**
     * A remote source indentified by its URL (src: url()). 
     * @author burgetr
     */
    public interface SourceURL extends Source
    {
        public TermURI getURI();
    }
    
}
