/**
 * 
 */
package cz.vutbr.web.csskit.antlr;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTreeAdaptor;

/**
 * @author kapy
 *
 */
public class CSSAdaptor extends CommonTreeAdaptor {

	public Object invalidFallback(int ttype, String ttext) {
		
		Object root = (Object) nil();
		Object node = (Object) create(ttype, ttext);
		
		addChild(root, node);		
		return root;		
	}
	
	@Override
	public Object errorNode(TokenStream arg0, Token arg1, Token arg2,
			RecognitionException arg3) {
		return super.errorNode(arg0, arg1, arg2, arg3);
	}
	
}
