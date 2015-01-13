package cz.vutbr.web.csskit.antlr;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.Token;

import org.slf4j.Logger;

public class CSSExpressionReader {
	
	private final CharStream input;
	private final Logger log;
	
	public CSSExpressionReader(CharStream input, Logger log) {
		this.input = input;
		this.log = log;
	}
	
	/**
	 * Reads all the contents of an expression. Parenthesis are matched but not in strings.
	 */
	public String read() {
		log.debug("readExpressionContents");
		
		StringBuffer ret = new StringBuffer();
		int parenN = 1; /* one already open */
		boolean inApos = false;
		boolean inQuot = false;
		boolean esc = false;
		boolean finished = false;
		int c;
		while (!finished)
		{
			c = input.LA(1);
			if(c=='\'' && !inQuot && !(inApos && esc)) {
				inApos = !inApos;
			}
			else if (c=='"' && !inApos && !(inQuot && esc)) {
				inQuot = !inQuot;
			}
			else if(c=='(' && !inApos && !inQuot) {
				parenN++;
			}
			else if(c==')' && parenN>0  && !inApos && !inQuot) {
				parenN--;
				if (parenN == 0) finished = true;
			}
			// handle end of line in string
			else if (c=='\n') {
				inQuot = false;
				inApos = false;
			}
			else if(c==Token.EOF) {
				log.info("Unexpected EOF during consumeUntilBalanced, EOF not consumed");
				return ret.toString();
			}
			
			esc = (c == '\\') && !esc;
			
			if (!finished) ret.append((char) c);
			
			input.consume();
			
		}
		
		log.debug("Expr: " + ret.toString());
		return ret.toString();
	}
}
