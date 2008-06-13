package cz.vutbr.web.cssKit;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.cssKit.parser.CssParser;
import cz.vutbr.web.cssKit.parser.ParseException;
import cz.vutbr.web.cssKit.parser.SimpleNode;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Engine - main class of CSS library
 * @author Jan Svercl, VUT Brno, 2008
 */
public class Engine {
    
    public static StyleSheet parse(InputStream in) throws ParseException {
        SimpleNode n = CssParser.parse(in);
        SimpleNode ssNode = (SimpleNode)n.jjtGetChild(0);
        StyleSheetImpl s = new StyleSheetImpl(ssNode);
        return s;
    }
    
    public static StyleSheet parse(File file) throws ParseException, FileNotFoundException {
        return parse(new FileInputStream(file.getAbsolutePath()));
    }
    
    public static StyleSheet parse(String stylesheet) throws ParseException {
        byte currentBytes[] = stylesheet.getBytes();
        return parse(new ByteArrayInputStream(currentBytes));
    }
}
