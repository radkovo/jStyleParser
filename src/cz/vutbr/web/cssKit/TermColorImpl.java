package cz.vutbr.web.cssKit;

import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.cssKit.parser.SimpleNode;
import java.awt.Color;

/**
 * TermColor
 * @author Jan Svercl, VUT Brno, 2008
 */
public class TermColorImpl extends TermImpl implements TermColor {
    
    Color color;
    
    public TermColorImpl(int r, int g, int b) {
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
    
    protected static TermColorImpl getColorByNode(SimpleNode term) {
        if(term != null) {
            if((term.jjtGetNumChildren() == 1)) {
                if(((SimpleNode)term.jjtGetChild(0)).getType().equals("ident")) {
                    String ident = ((SimpleNode)term.jjtGetChild(0)).getImage().toLowerCase();

                    if(ident.equals("maroon")) {
                        return new TermColorImpl(0x80, 0, 0);
                    }
                    else if(ident.equals("red")) {
                        return new TermColorImpl(0xff, 0, 0);
                    }
                    else if(ident.equals("orange")) {
                        return new TermColorImpl(0xff, 0xa5, 0);
                    }
                    else if(ident.equals("yellow")) {
                        return new TermColorImpl(0xff, 0xff, 0);
                    }
                    else if(ident.equals("orange")) {
                        return new TermColorImpl(0xff, 0xffa500, 0);
                    }
                    else if(ident.equals("olive")) {
                        return new TermColorImpl(0x80, 0x80, 0);
                    }
                    else if(ident.equals("purple")) {
                        return new TermColorImpl(0x80, 0, 0x80);
                    }
                    else if(ident.equals("fuchsia")) {
                        return new TermColorImpl(0xff, 0, 0xff);
                    }
                    else if(ident.equals("white")) {
                        return new TermColorImpl(0xff, 0xff, 0xff);
                    }
                    else if(ident.equals("lime")) {
                        return new TermColorImpl(0, 0xff, 0);
                    }
                    else if(ident.equals("green")) {
                        return new TermColorImpl(0, 0x80, 0);
                    }
                    else if(ident.equals("navy")) {
                        return new TermColorImpl(0, 0, 0x80);
                    }
                    else if(ident.equals("blue")) {
                        return new TermColorImpl(0, 0, 0xff);
                    }
                    else if(ident.equals("aqua")) {
                        return new TermColorImpl(0, 0xff, 0xff);
                    }
                    else if(ident.equals("teal")) {
                        return new TermColorImpl(0, 0x80, 0x80);
                    }
                    else if(ident.equals("black")) {
                        return new TermColorImpl(0, 0, 0);
                    }
                    else if(ident.equals("silver")) {
                        return new TermColorImpl(0xc0, 0xc0, 0xc0);
                    }
                    else if(ident.equals("gray")) {
                        return new TermColorImpl(0x80, 0x80, 0x80);
                    }
                }
                else if(((SimpleNode)term.jjtGetChild(0)).getType().equals("hexcolor") && term.jjtGetChild(0).jjtGetNumChildren() == 1 ) {
                    if(((SimpleNode)term.jjtGetChild(0).jjtGetChild(0)).getType().equals("hash")) {
                        String hash = ((SimpleNode)term.jjtGetChild(0).jjtGetChild(0)).getImage().toLowerCase();
                        if(hash.matches("^#[0-9a-f]{3}$")) {
                            String r = hash.substring(1, 2);
                            String g = hash.substring(2, 3);
                            String b = hash.substring(3, 4);
                            return new TermColorImpl(Integer.parseInt(r+r, 16), Integer.parseInt(g+g, 16), Integer.parseInt(b+b, 16));
                        }
                        else if(hash.matches("^#[0-9a-f]{6}$")) {
                            String r = hash.substring(1, 3);
                            String g = hash.substring(3, 5);
                            String b = hash.substring(5, 7);
                            return new TermColorImpl(Integer.parseInt(r, 16), Integer.parseInt(g, 16), Integer.parseInt(b, 16));
                        }
                    }
                }
                else if(((SimpleNode)term.jjtGetChild(0)).getType().equals("function") && term.jjtGetChild(0).jjtGetNumChildren() == 2 ) {
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
}
