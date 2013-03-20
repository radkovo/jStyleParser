package cz.vutbr.web.csskit;

import java.awt.Color;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermInteger;
import cz.vutbr.web.css.TermNumber;
import cz.vutbr.web.css.TermPercent;

/**
 * TermColor
 * TODO: Clipping should be done against devices gamut
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 *          CSS3 extensions by Radek Burget, 2013
 */
public class TermColorImpl extends TermImpl<Color> implements TermColor {
    
	protected static final String COLOR_RGB_NAME = "rgb";
    protected static final String COLOR_RGBA_NAME = "rgba";
    protected static final String COLOR_HSL_NAME = "hsl";
    protected static final String COLOR_HSLA_NAME = "hsla";
	protected static final int COLOR_PARAMS_COUNT = 3;
	protected static final int MAX_VALUE = 255;
	protected static final int MIN_VALUE = 0;
	protected static final int PERCENT_CONVERSION = 100;
    protected static final int MAX_HUE = 360;
	
    protected TermColorImpl(int r, int g, int b) {
        value = new Color(r, g, b);
    }
    
    protected TermColorImpl(int r, int g, int b, int a) {
        value = new Color(r, g, b, a);
    }
    
    @Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	if(operator!=null) sb.append(operator.value());
    	
    	if (value.getAlpha() == 255) { //use hash notation if aplha is not used
            String s = Integer.toHexString(value.getRGB() & 0xffffff );
            if ( s.length() < 6 ) { 
                s = "000000".substring(0, 6 - s.length()) + s;
            }
            sb.append(OutputUtil.HASH_SIGN).append(s);
    	} else { //use rgba() when aplha is used
    	    sb.append("rgba(");
    	    sb.append(value.getRed());
    	    sb.append(',');
            sb.append(value.getGreen());
            sb.append(',');
            sb.append(value.getBlue());
            sb.append(',');
            sb.append(Math.round(value.getAlpha() / 2.55) / 100.0);
            sb.append(")");
    	}
    
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
    	
    	if ((COLOR_RGB_NAME.equals(func.getFunctionName()) && func.size() == COLOR_PARAMS_COUNT)
    	   || COLOR_RGBA_NAME.equals(func.getFunctionName()) && func.size() == COLOR_PARAMS_COUNT + 1) {
    		
    		int[] rgb = new int[COLOR_PARAMS_COUNT];
    		for(int i = 0; i < COLOR_PARAMS_COUNT; i++) {
    		    Term<?> term = func.get(i);
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
    		}
    		
    		// limits
    		for(int i = 0; i < rgb.length; i++) {
    			if(rgb[i] < MIN_VALUE) rgb[i] = MIN_VALUE;
    			if(rgb[i] > MAX_VALUE) rgb[i] = MAX_VALUE;
    		}
    		
    		//alpha
    		int a = MAX_VALUE;
    		if (func.size() > COLOR_PARAMS_COUNT)
    		{
    		    Term<?> term = func.get(COLOR_PARAMS_COUNT);
    		    if (term instanceof TermNumber || term instanceof TermInteger) {
    		        float alpha = getFloatValue(term);
    		        a = Math.round(alpha * MAX_VALUE);
    		        if (a < MIN_VALUE) a = MIN_VALUE;
    		        if (a > MAX_VALUE) a = MAX_VALUE;
    		    }
    		    else
    		        return null; //unacceptable alpha value
    		}
    		
    		return new TermColorImpl(rgb[0], rgb[1], rgb[2], a);
    	}
    	else if ((COLOR_HSL_NAME.equals(func.getFunctionName()) && func.size() == COLOR_PARAMS_COUNT)
                || COLOR_HSLA_NAME.equals(func.getFunctionName()) && func.size() == COLOR_PARAMS_COUNT + 1) {

    	    float h, s, l;
    	    Term<?> hterm = func.get(0);
            if (hterm instanceof TermNumber || hterm instanceof TermInteger) {
                h = getFloatValue(hterm);
    	        while (h >= MAX_HUE) h -= MAX_HUE;
    	        while (h < 0) h += MAX_HUE;
    	        h = h / MAX_HUE; //normalize to 0..1
    	    }
    	    else
    	        return null;
    	    
            Term<?> sterm = func.get(1);
            if (sterm instanceof TermPercent) {
                int is = ((TermPercent) sterm).getValue().intValue();
                if (is > 100) is = 100;
                else if (is < 0) is = 0;
                s = is / 100.0f;
            }
            else
                return null;
    	    
            Term<?> lterm = func.get(2);
            if (lterm instanceof TermPercent) {
                int il = ((TermPercent) lterm).getValue().intValue();
                if (il > 100) il = 100;
                else if (il < 0) il = 0;
                l = il / 100.0f;
            }
            else
                return null;
            
            int[] rgb = hslToRgb(h, s, l);
            
            // alpha
            int a = MAX_VALUE;
            if (func.size() > COLOR_PARAMS_COUNT)
            {
                Term<?> term = func.get(COLOR_PARAMS_COUNT);
                if (term instanceof TermNumber || term instanceof TermInteger) {
                    float alpha = getFloatValue(term);
                    a = Math.round(alpha * MAX_VALUE);
                    if (a < MIN_VALUE) a = MIN_VALUE;
                    if (a > MAX_VALUE) a = MAX_VALUE;
                }
                else
                    return null; // unacceptable alpha value
            }

            return new TermColorImpl(rgb[0], rgb[1], rgb[2], a);
        }
    	// invalid function
    	else
    	    return null;
    }
	
    private static float getFloatValue(Term<?> term)
    {
        if (term instanceof TermNumber)
            return ((TermNumber) term).getValue().floatValue();
        else if (term instanceof TermInteger)
            return ((TermInteger) term).getValue().floatValue();
        else
            return 0;
    }
    
    /**
     * Converts the HSL color model to RGB
     * @param h hue normalized to 0..1
     * @param s saturation normalized to 0..1
     * @param l level normalized to 0..1
     * @return an array of three values R, G and B in the interval 0..255
     */
    private static int[] hslToRgb(float h, float s, float l) {
        
        int[] ret = new int[3];
        
        float m2 = (l <= 0.5f) ? l * (s + 1) : l + s - l * s; 
        float m1 = l * 2 - m2;
        ret[0] = Math.round(hueToRgb(m1, m2, h + 1.0f/3.0f) * MAX_VALUE);
        ret[1] = Math.round(hueToRgb(m1, m2, h) * MAX_VALUE);
        ret[2] = Math.round(hueToRgb(m1, m2, h - 1.0f/3.0f) * MAX_VALUE);
        return ret;
    }
    
    private static float hueToRgb(float m1, float m2, float h) {
        if (h < 0) h += 1;
        if (h > 1) h -= 1;
        if (h * 6 < 1) return m1 + (m2 - m1) * h * 6;
        if (h * 2 < 1) return m2;
        if (h * 3 < 2) return m1 + (m2 -m1 ) * (2.0f/3.0f - h) * 6;
        return m1;
    }
    
}
