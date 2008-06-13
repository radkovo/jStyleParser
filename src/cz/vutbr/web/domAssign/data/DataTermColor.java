package cz.vutbr.web.domAssign.data;

import cz.vutbr.web.css.TermColor;
import java.awt.Color;

/**
 * TermColor
 * @author Jan Svercl, VUT Brno, 2008
 */
public class DataTermColor extends DataTerm implements TermColor {
    
    Color color;
    
    public DataTermColor(int r, int g, int b) {
        color = new Color(r, g, b);
    }
    
    public Color getValue() {
        return color;
    }
    
    @Override
    public String toString() {
        String s = Integer.toHexString(color.getRGB() & 0xffffff );
        if ( s.length() < 6 ) { 
            s = "000000".substring(0, 6 - s.length()) + s;
        }
        return operator('#' + s);
    }
}
