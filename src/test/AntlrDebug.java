package test;

import cz.vutbr.web.css.CSSFactory;

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
			CSSFactory.parse(GrammarRecovery1.TEST_CHARSET_WITHOUT_SEMICOLON3);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}

	}

}
