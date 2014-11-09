package test;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.StyleSheet;

/**
 * Remotely debugs antlr parser
 * Parser must be compiled with <code>-debug</code> option
 * Default debug port is 49153
 * Expects instance of CSSTreeParser
 * @author kapy
 *
 */
public class AntlrDebug {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {			
			//CSSFactory.parse(GrammarRecovery1.TEST_CHARSET_WITHOUT_SEMICOLON3);
			//StyleSheet ss = CSSFactory.parse(SimpleTest.TEST_URI1);
            //StyleSheet ss = CSSFactory.parse("body { color: red; $font-weight: bold; font-style:italic; } h1 { font-size: 200% }");
            StyleSheet ss = CSSFactory.parseString("body { color: red; padding: expression(ms_setPadding()); font-style:italic; } h1 { font-size: 200% }", null);
			System.out.println(ss);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}

	}

}
