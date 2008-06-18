package test;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StylesheetNotValidException;
import cz.vutbr.web.csskit.Engine;
import cz.vutbr.web.csskit.StyleSheetImpl;
import cz.vutbr.web.csskit.parser.ParseException;
import cz.vutbr.web.domassign.Controller;
import cz.vutbr.web.domassign.NodeData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.tidy.Tidy;


public class Main {
    
    /**
     * @param args argumenty příkazové řádky
     */
    public static void main(String[] args) {
        String stylesheet = null;
        boolean stylesheetKeyWord = false;
        String html = null;
        boolean htmlKeyWord = false;
        String out = null;
        boolean outKeyWord = false;
        int index = 0;
        for(String s : args) {
            if(s.equalsIgnoreCase("-css")) {
                if(stylesheetKeyWord || htmlKeyWord || outKeyWord || stylesheet != null) {
                    break;
                }
                stylesheetKeyWord = true;
                index++;
                continue;
            }
            if(s.equalsIgnoreCase("-html")) {
                if(stylesheetKeyWord || htmlKeyWord || outKeyWord || html != null) {
                    break;
                }
                htmlKeyWord = true;
                index++;
                continue;
            }
            if(s.equalsIgnoreCase("-out")) {
                if(stylesheetKeyWord || htmlKeyWord || outKeyWord || out != null) {
                    break;
                }
                outKeyWord = true;
                index++;
                continue;
            }
            if(s.equalsIgnoreCase("validate")) {
                if(args.length-1 == index && !stylesheetKeyWord && !htmlKeyWord && !outKeyWord && stylesheet != null && html == null && out == null) {
                    try {
                        StyleSheet ss = Engine.parse(new File(stylesheet));
                        System.out.println("Syntax OK.");
                        return;
                    }
                    catch (FileNotFoundException e) {
                        System.out.println("Input file with CSS stylesheet not found!");
                        return;
                    }
                    catch (ParseException e) {
                        System.out.println("Error occured when parsing CSS file!");
                        System.out.println();
                        System.out.println(e.getMessage());
                        return;
                    }
                }
                else {
                    break;
                }
            }
            if(s.equalsIgnoreCase("assign_text") || s.equalsIgnoreCase("assign_html") || s.equalsIgnoreCase("assign_inline")) {
                if(args.length-1 == index && !stylesheetKeyWord && !htmlKeyWord && !outKeyWord && stylesheet != null && html != null) {
                    OutputStream outStream = System.out;
                    if(out != null) {
                        try {
                            outStream = new FileOutputStream(new File(out));
                        }
                        catch(FileNotFoundException e) {
                            System.out.println("Can't use out file!");
                            return;
                        }
                    }
                    
                    StyleSheet ss = null;
                    Document d = null;
                    HashMap<Element, NodeData> nodeData = null;
                    
                    try {
                        ss = Engine.parse(new File(stylesheet));
                    }
                    catch (FileNotFoundException e) {
                        System.out.println("Input file with CSS stylesheet not found!");
                        return;
                    }
                    catch (ParseException e) {
                        System.out.println("Error occured when parsing CSS file!");
                        System.out.println();
                        System.out.println(e.getMessage());
                        return;
                    }
                    
                    try {
                        Tidy tidy = new Tidy();
                        tidy.setCharEncoding(org.w3c.tidy.Configuration.UTF8);
                        d = tidy.parseDOM(new FileInputStream((new File(html)).getAbsolutePath()), null);
                    }
                    catch (FileNotFoundException e) {
                        System.out.println("Input file with HTML document not found!");
                        return;
                    }
                    
                    //Přiřazení hodnot
                    try {
                        nodeData = Controller.process(d, ss, "screen");
                    }
                    catch (StylesheetNotValidException e) {
                        e.printStackTrace();
                        return;
                    }
                    
                    if(s.equalsIgnoreCase("assign_text")) {
                        Controller.print(d, nodeData, outStream);
                        return;
                    }
                    if(s.equalsIgnoreCase("assign_html")) {
                        Controller.printHTML(d, nodeData, outStream);
                        return;
                    }
                    if(s.equalsIgnoreCase("assign_inline")) {
                        Controller.printInlineStyleHTML(d, nodeData, outStream);
                        return;
                    }
                }
                else {
                    break;
                }
            }

            if(stylesheetKeyWord && !s.equalsIgnoreCase("-css")) {
                stylesheet = s;
                stylesheetKeyWord = false;
                index++;
                continue;
            }
            if(htmlKeyWord && !s.equalsIgnoreCase("-html")) {
                html = s;
                htmlKeyWord = false;
                index++;
                continue;
            }
            if(outKeyWord && !s.equalsIgnoreCase("-out")) {
                out = s;
                outKeyWord = false;
                index++;
                continue;
            }
            break;
        }

        System.out.println("Arguments:");
        System.out.println("-css <stylesheet> validate");
        System.out.println("-css <stylesheet> -html <html_doc> [-out <out_file>] assign_text");
        System.out.println("-css <stylesheet> -html <html_doc> [-out <out_file>] assign_html");
        System.out.println("-css <stylesheet> -html <html_doc> [-out <out_file>] assign_inline");
    }
}
