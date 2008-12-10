package cz.vutbr.web.csskit;

import java.awt.Color;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermInteger;
import cz.vutbr.web.css.TermPercent;

/**
 * TermColor
 * TODO: Clipping should be done against devices gamut
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 * @version 1.0 * Rewritten according to interface
 * 				 * Construction moved to parser
 * 			     * Fixed percentage of rgb() function 
 */
public class TermColorImpl extends TermImpl<Color> implements TermColor {
    
	protected static final String COLOR_FUNCTION_NAME = "rgb";
	protected static final int COLOR_PARAMS_COUNT = 3;
	protected static final int MAX_VALUE = 255;
	protected static final int MIN_VALUE = 0;
	protected static final int PERCENT_CONVERSION = 100;
	
    protected TermColorImpl(int r, int g, int b) {
        value = new Color(r, g, b);
    }
    
    @Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	if(operator!=null) sb.append(operator.value());
    	
        String s = Integer.toHexString(value.getRGB() & 0xffffff );
        if ( s.length() < 6 ) { 
            s = "000000".substring(0, 6 - s.length()) + s;
        }
        
        sb.append(OutputUtil.HASH_SIGN).append(s);
    
        return sb.toString();
    }
    
    /**
     * Checks indent value against color card.
     * If its value matches, new TermColor is returned which is 
     * subject of replace of TermIndent afterwards
     * @param ident Identifier possibly holding color's name
     * @return <code>TermColor</code> if color matches, <code>null</code> elsewhere
     */
    public static TermColor getColorByIdent(TermIdent ident) {
    	return ColorCard.getTermColor(ident.getValue());
    }
    
    /**
     * Creates color from string in form #ABC or #AABBCC, or
     * just simply ABC and AABBCC.
     * where A, B, C are hexadecimal digits. 
     * @param hash Hash string
     * @return Created color or <code>null</code> in case of error
     */
    public static TermColor getColorByHash(String hash) {

    	if(hash==null)
    		throw new IllegalArgumentException("Invalid hash value (null) for color construction");
    	
    	// lowercase and remove hash character, if any
    	hash = hash.toLowerCase().replaceAll("^#", "");
    	
    	// color written in #ABC format
        if(hash.matches("^[0-9a-f]{3}$")) {
            String r = hash.substring(0, 1);
            String g = hash.substring(1, 2);
            String b = hash.substring(2, 3);
            return new TermColorImpl(Integer.parseInt(r+r, 16), Integer.parseInt(g+g, 16), Integer.parseInt(b+b, 16));
        }
        // color written in #AABBCC format
        else if(hash.matches("^[0-9a-f]{6}$")) {
            String r = hash.substring(0, 2);
            String g = hash.substring(2, 4);
            String b = hash.substring(4, 6);
            return new TermColorImpl(Integer.parseInt(r, 16), Integer.parseInt(g, 16), Integer.parseInt(b, 16));
        }
        // invalid hash
        return null;
    }
    
    /**
     * Creates color from <code>rgb()</code> function.
     * @param func Function to be tested
     * @return Created color if parsing matched, <code>null</code> otherwise
     */
    public static TermColor getColorByFunction(TermFunction func) {
    	
    	// we matched rgb function
    	if(COLOR_FUNCTION_NAME.equals(func.getFunctionName()) &&
    			func.size() == COLOR_PARAMS_COUNT) {
    		
    		int[] rgb = new int[COLOR_PARAMS_COUNT];
    		int i = 0;
    		for(Term<?> term: func) {
    			// term is number and numeric
    			if(term instanceof TermInteger ) {
    				rgb[i] = ((TermInteger)term).getIntValue();
    			}
    			// term is percent
    			else if(term instanceof TermPercent) {
    				int value = ((TermPercent) term).getValue().intValue();
    				rgb[i] = (value * MAX_VALUE) / PERCENT_CONVERSION;
    			}
    			// not valid term
    			else {
    				return null;
    			}
    			i++;
    		}
    		
    		// limits
    		for(i = 0; i < rgb.length; i++) {
    			if(rgb[i] < MIN_VALUE) rgb[i] = MIN_VALUE;
    			if(rgb[i] > MAX_VALUE) rgb[i] = MAX_VALUE;
    		}
    		return new TermColorImpl(rgb[0], rgb[1], rgb[2]);
    	}
    	// invalid function
    	return null;
    }
	
}
