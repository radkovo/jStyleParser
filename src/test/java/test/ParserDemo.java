/**
 * ParserDemo.java
 *
 * Created on 8. 7. 2014, 13:02:07 by burgetr
 */
package test;

import java.io.IOException;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleBlock;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.Term;

/**
 * A simple demo showing how to parse and process a style sheet with no DOM analysis involved.
 * 
 * @author burgetr
 */
public class ParserDemo
{

    private static String css = 
              "a { color: #abc; }"
            + " b { font-weight: bold; }"
            + " @media screen {"
            + "    em { font-style: italic; }"
            + "}";
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        
        try
        {
            StyleSheet style = CSSFactory.parseString(css, null);
            
            //obtain the number of rules
            System.out.println("#of rules parsed: " + style.size());
            //go through the rules
            for (RuleBlock<?> rule : style)
            {
                if (rule instanceof RuleSet)
                {
                    RuleSet set = (RuleSet) rule;
                    System.out.println("Selectors: " + set.getSelectors());
                    System.out.println("Declarations:");
                    for (Declaration decl : set)
                    {
                        System.out.println("  Property: " + decl.getProperty());
                        System.out.println("  Values: ");
                        for (Term<?> term : decl)
                            System.out.println("    " + term);
                    }
                }
                else if (rule instanceof RuleMedia)
                {
                    RuleMedia media = (RuleMedia) rule;
                    System.out.println("Media: " + media.getMediaQueries());
                    for (RuleSet set : media)
                    {
                        //process similarly to the RuleSet processing above
                        System.out.println("  Rule: " + set);
                    }
                }
                else
                {
                    //other rules such as @page, @viewport etc.
                    System.out.println("Other rule " + rule);
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSSException e) {
            e.printStackTrace();
        }

    }

}
