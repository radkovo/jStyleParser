package test;

import java.io.StringReader;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.csskit.antlr.CSSTreeParser;

/**
 * Remotely debugs antlr parser
 * Parser must be compiled with <code>-debug</code> option
 * Default debug port is 49153
 * @author kapy
 *
 */
public class AntlrDebug {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			
			CSSTreeParser parser = CSSTreeParser.createParser(new StringReader(
					GrammarRecovery1.TEST_CHARSET_WITHOUT_SEMICOLON3));

			StyleSheet style = parser.stylesheet();

			System.out.println(style);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}

	}

}
