package cz.vutbr.web.csskit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringReader;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.csskit.parser.CssParser;
import cz.vutbr.web.csskit.parser.ParseException;

/**
 * Engine - main class of CSS library
 * @author Jan Svercl, VUT Brno, 2008
 */
public class Engine {
    
    public static StyleSheet parse(InputStream in) throws ParseException {
    	CssParser parser = new CssParser(in);
    	return new StyleSheetImpl(parser.start());
    }
    
    public static StyleSheet parse(File file) throws ParseException, FileNotFoundException {
        return parse(new FileInputStream(file.getAbsolutePath()));
    }
    
    public static StyleSheet parse(String stylesheet) throws ParseException {
    	CssParser parser = new CssParser(new StringReader(stylesheet));
    	return new StyleSheetImpl(parser.start());
    }
}
