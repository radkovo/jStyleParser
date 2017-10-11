package cz.vutbr.web.css;

import cz.vutbr.web.csskit.Color;

/**
 * Holds color value directly usable in Java.
 * @author Jan Svercl, VUT Brno, 2008
 * @author Karel Piwko, 2008
 */
public interface TermColor extends Term<Color> {
    
    public enum Keyword {
        none(""), TRANSPARENT("transparent"), CURRENT_COLOR("currentColor");
        
        private String text;
        
        private Keyword(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
    
    /**
     * When the color was created using a special keyword such as 'transparent'
     * or 'currentColor', this method may be used to test this condition.
     * @return The corresponding {@link Keyword} value
     */
    public Keyword getKeyword();
    
    /**
     * Checks whether the given color is entirely transparent.
     * @return {@code true} when the color is entirely transparent
     */
    public boolean isTransparent();
    
}
