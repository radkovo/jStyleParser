/**
 * ProfilerEntryPointAnalyzer.java
 *
 * Created on 18. 11. 2015, 13:40:18 by burgetr
 */
package test;

import java.util.Date;

import org.w3c.dom.Document;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.domassign.StyleMap;

/**
 * 
 * @author burgetr
 */
public class ProfilerEntryPointAnalyzer
{

    public static void main(String[] args) throws Exception {

        final String src = "/profiling/slate.html";
        //final String src = "/profiling/lidovky2.html";
        
        Date start = new Date();
        DOMSource ds = new DOMSource(AnalyzerTest.class.getResourceAsStream(src));
        Document doc = ds.parse();
        
        Date analyze = new Date();
        StyleMap decl = CSSFactory.assignDOM(doc, null, ProfilerEntryPointAnalyzer.class.getResource(src), "screen", true);
        
        Date end = new Date();
        System.out.println("DOM parsing: " + (analyze.getTime() - start.getTime()) + " ms");
        System.out.println("CSS analysis: " + (end.getTime() - analyze.getTime()) + " ms");
        System.out.println("Obtained " + decl.size() + " node styles");
    }

    
}
