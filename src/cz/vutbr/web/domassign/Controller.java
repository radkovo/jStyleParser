package cz.vutbr.web.domassign;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.StyleSheetNotValidException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.w3c.tidy.Tidy;

public class Controller {
    
    /**
     * Hlavní funkce provádějící ohodnocení stromu dokumentu
     * @param doc dokument
     * @param styleSheet předpis CSS
     * @param media medium pro které se ohodnocení provádí
     * @param thread počet vláken pro zpracování
     * @return mapa obsahující pro každý element stromu objekt třídy NodeData
     * @throws cz.vutbr.web.css.StyleSheetNotValidException
     */
    public static HashMap<Element, NodeData> process(Document doc, StyleSheet styleSheet, String media) throws StyleSheetNotValidException {
        //Analyzer vyhledá všechny deklarace odpovídajícím elementům a seřadí je
        HashMap<Element, ArrayList<AssignedDeclaration>> assignedDeclarations = Analyzer.analyze(doc, styleSheet, media);
       
        HashMap<Element, NodeData> nodeDataMap = new HashMap<Element, NodeData>();
        
        //Ke každému elementu se vytvoří objekt NodeData a naplní se všemi deklaracemi
        for(Element e : assignedDeclarations.keySet()) {
            NodeData data = new NodeData();
            nodeDataMap.put(e, data);
            for(AssignedDeclaration ad : assignedDeclarations.get(e)) {
                data.putDeclaration(ad.getDeclaration());
            }
        }
        return nodeDataMap;
    }
    
    /**
     * Funkce vypíše daný dokument v textové podobě včetně všech přiřazených hodnot
     * @param d vstupní dokument
     * @param data přiřazené hodnoty
     */
    public static void print(Document d, HashMap<Element, NodeData> data, OutputStream os) {
        print(d.getDocumentElement(), data, 0, os);
    }
    
    /**
     * Funkce vypíše daný dokument v textové podobě včetně všech přiřazených hodnot
     * @param d vstupní dokument
     * @param data přiřazené hodnoty
     */
    public static void printHTML(Document d, HashMap<Element, NodeData> data, OutputStream os) {
        printHTML(d.getDocumentElement(), data, 0, os);
    }
    
    /**
     * Funkce vypíše daný dokument v textové podobě včetně všech přiřazených hodnot
     * @param d vstupní dokument
     * @param data přiřazené hodnoty
     */
    public static void printInlineStyleHTML(Document d, HashMap<Element, NodeData> data, OutputStream os) {
        HTMLtest(d.getDocumentElement(), data, 0);
        Tidy tidy = new Tidy();
        tidy.setCharEncoding(org.w3c.tidy.Configuration.UTF8);
        tidy.pprint(d, os);
    }
    
    /**
     * Vypíše na výstup jeden element včetně všech jeho atributů
     * @param e element
     * @param data přiřazené hodnoty
     * @param depth hloubka zanoření
     */
    private static void print(Element e, HashMap<Element, NodeData> data, int depth, OutputStream os) {
        PrintStream ps = new PrintStream(os);
        ps.print(depth(depth) + e.getNodeName());
        boolean printComma = false;
        for(int i = 0; i < e.getAttributes().getLength(); i++) {
            Node n = e.getAttributes().item(i);
            if(n instanceof Attr) {
                if(printComma) { ps.print(", "); } else { ps.print("["); printComma = true; }
                ps.print(((Attr)n).getName() + "='" + ((Attr)n).getValue() + "'");
            }
        }
        if(printComma) {
            ps.print("]");
        }
        ps.println();
        ps.println(depth(depth) + "========================================================");
        
        NodeData thisNodeData = data.get(e);
        ps.println(thisNodeData.toString(depth));
        
        for(int i = 0; i < e.getChildNodes().getLength(); i++) {
            Node n = e.getChildNodes().item(i);
            if(n instanceof Element) {
                print((Element)n, data, depth+1, os);
            }
        }
    }
    
    
    /**
     * Vypíše na výstup jeden element včetně všech jeho atributů v HTML formátu
     * @param e element
     * @param data přiřazené hodnoty
     * @param depth hloubka zanoření
     */
    private static void printHTML(Element e, HashMap<Element, NodeData> data, int depth, OutputStream os) {
        PrintStream ps = new PrintStream(os);
        if(depth == 0) {
            ps.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
            ps.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">");
            ps.println("<head>");
            ps.println("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" />");
            ps.println("<title>Výsledek ohodnocení stromu dokumentu</title>");
            ps.println("<style type=\"text/css\" media=\"all\"> DIV { border: 1px solid; margin-left: 20px; margin-bottom: 10px; } DIV.depth0 { background-color: #FFF; } DIV.depth1 { background-color: #DDD; } DIV.depth2 { background-color: #BBB; }              </style>");
            ps.println("</head>");
            ps.println("<body>");
        }
        
        ps.println("<div class=\"depth" + new Integer(depth % 3).toString() + "\">");
        
        ps.print("<h1>" + e.getNodeName() );
        boolean printComma = false;
        for(int i = 0; i < e.getAttributes().getLength(); i++) {
            Node n = e.getAttributes().item(i);
            if(n instanceof Attr) {
                if(printComma) { ps.print(", "); } else { ps.print("["); printComma = true; }
                ps.print(((Attr)n).getName() + "='" + ((Attr)n).getValue() + "'");
            }
        }
        if(printComma) {
            ps.print("]");
        }
        ps.println("</h1>");
                
        NodeData thisNodeData = data.get(e);
        ps.print(thisNodeData.toHTMLString());
        
        for(int i = 0; i < e.getChildNodes().getLength(); i++) {
            Node n = e.getChildNodes().item(i);
            if(n instanceof Element) {
                printHTML((Element)n, data, depth+1, os);
            }
        }
        
        ps.println("</div>");
        
        if(depth == 0) {
            ps.println("</body>");
            ps.println("</html>");
        }
    }
    
    /**
     * Upraví dokument tak, aby v atributu style byly všechny přiřazené vlastnosti
     * @param e element
     * @param data přiřazené hodnoty
     * @param depth hloubka zanoření
     */
    private static void HTMLtest(Element e, HashMap<Element, NodeData> data, int depth) {
        NodeData thisNodeData = data.get(e);
        
        Boolean style = false;
        for(int i = 0; i < e.getAttributes().getLength(); i++) {
            Node n = e.getAttributes().item(i);
            if(n instanceof Attr) {
                if(((Attr)n).getName().equalsIgnoreCase("style") ) {
                    ((Attr)n).setValue(thisNodeData.toInlineStyle() + ((Attr)n).getValue());
                    style = true;
                }
            }
        }
        if(!style) {
            e.setAttribute("style", thisNodeData.toInlineStyle());
        }
        
        for(int i = 0; i < e.getChildNodes().getLength(); i++) {
            Node n = e.getChildNodes().item(i);
            if(n instanceof Element) {
                HTMLtest((Element)n, data, depth+1);
            }
        }
    }
    
    /**
     * Vrací String s mezerami, kde jejich počet je 4xdepth
     * @param depth hloubka zanoření
     * @return řetězec obsahující 4xdepth mezer
     */
    private static String depth(int depth) {
        String s = "";
        while(depth > 0) {
            s += "    ";
            depth--;
        }
        return s;
    }
    
}
