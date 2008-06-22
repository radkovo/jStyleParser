package cz.vutbr.web.csskit;

import java.awt.Color;

import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermIdent;

/**
 * TermColor
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 */
public class TermColorImpl extends TermImpl implements TermColor {
    
    protected Color color;
    
    public TermColorImpl(int r, int g, int b) {
        color = new Color(r, g, b);
    }
    
    public Color getValue() {
        return color;
    }
    
    @Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	if(operator!=null) sb.append(operator.value());
    	
        String s = Integer.toHexString(color.getRGB() & 0xffffff );
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
    
    public static TermColor getColorByHash(String hash) {

    	// color written in #ABC format
        if(hash.matches("^#[0-9a-f]{3}$")) {
            String r = hash.substring(1, 2);
            String g = hash.substring(2, 3);
            String b = hash.substring(3, 4);
            return new TermColorImpl(Integer.parseInt(r+r, 16), Integer.parseInt(g+g, 16), Integer.parseInt(b+b, 16));
        }
        // color written in #AABBCC format
        else if(hash.matches("^#[0-9a-f]{6}$")) {
            String r = hash.substring(1, 3);
            String g = hash.substring(3, 5);
            String b = hash.substring(5, 7);
            return new TermColorImpl(Integer.parseInt(r, 16), Integer.parseInt(g, 16), Integer.parseInt(b, 16));
        }
        
        return null;
    }
    /*
    public static TermColor getColorByFunction(TermFunction func) {
    	                if(((SimpleNode)term.jjtGetChild(0)).getType().equals("function") && term.jjtGetChild(0).jjtGetNumChildren() == 2 ) {
                    if(((SimpleNode)term.jjtGetChild(0).jjtGetChild(0)).getImage().equals("rgb(") && ((SimpleNode)term.jjtGetChild(0).jjtGetChild(1)).getType().equals("expr") && term.jjtGetChild(0).jjtGetChild(1).jjtGetNumChildren() == 5) {
                        SimpleNode expr = (SimpleNode)term.jjtGetChild(0).jjtGetChild(1);
                        Integer[] rgbValues = new Integer[3];
                        for(int i = 0; i < 3; i++) {
                            int unary_operator = 1;
                            int value = 0;
                            SimpleNode fTerm = (SimpleNode)expr.jjtGetChild(i*2);
                            for(int ii = 0; ii < fTerm.jjtGetNumChildren(); ii++) {
                                SimpleNode fTermChild = (SimpleNode)fTerm.jjtGetChild(ii);
                                if(fTermChild.getType().equals("unary_operator") && fTermChild.jjtGetNumChildren() == 1) {
                                    SimpleNode fTermChildOperator = (SimpleNode)fTermChild.jjtGetChild(0);
                                    if(fTermChildOperator.getType().equals("minus")) {
                                        unary_operator = -1;
                                    }
                                }
                                else if(fTermChild.getType().equals("number")) {
                                    value = Integer.parseInt(fTermChild.getImage());
                                    if(value > 255) {
                                        value = 255;
                                    }
                                }
                                else if(fTermChild.getType().equals("percentage")) {
                                    int pr = Integer.parseInt(fTermChild.getImage().replaceAll("%$", ""));
                                    if(pr > 100) {
                                        pr = 100;
                                    }
                                    value = (255 * pr) / 100;
                                }
                            }
                            value = value * unary_operator;
                            if(value < 0) {
                                value = 0;
                            }
                            rgbValues[i] = new Integer(value);
                        }
                        if(rgbValues[0] != null && rgbValues[1] != null && rgbValues[2] != null) {
                            return new TermColorImpl(rgbValues[0].intValue(), rgbValues[1].intValue(), rgbValues[2].intValue());
                        }
                    }
                }
            }
        }
        return null;
    }
    */
}
