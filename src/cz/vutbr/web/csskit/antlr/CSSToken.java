package cz.vutbr.web.csskit.antlr;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;

public class CSSToken extends CommonToken {

	/**
	 * Basic version
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Current nesting level
	 */
	protected short curlyNest;
	
	public CSSToken(CharStream arg0, int arg1, int arg2, int arg3, int arg4) {
		super(arg0, arg1, arg2, arg3, arg4);
	}

	/**
	 * @return the curlyNest
	 */
	public short getCurlyNest() {
		return curlyNest;
	}

	/**
	 * @param curlyNest the curlyNest to set
	 */
	public void setCurlyNest(short curlyNest) {
		this.curlyNest = curlyNest;
	}
	
	

}
