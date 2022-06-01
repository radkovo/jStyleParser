/**
 * StyleMap.java
 *
 * Created on 22.1.2010, 16:06:07 by burgetr
 */
package cz.vutbr.web.domassign;

import org.w3c.dom.Element;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.Selector.PseudoElement;
import cz.vutbr.web.css.SupportedCSS;

/**
 * This is a map that assigns a style to a particular elements and moreover, it
 * gathers the information about the pseudo elements. 
 * 
 * @author burgetr
 */
public class StyleMap extends MultiMap<Element, PseudoElement, NodeData>
{

	private final DeclarationTransformer transformer;
	private final SupportedCSS css;

	public StyleMap(int size) {
		this(size, CSSFactory.getDeclarationTransformer(), CSSFactory.getSupportedCSS());
	}

	public StyleMap(int size, DeclarationTransformer transformer, SupportedCSS css) {
		super(size);
		this.transformer = transformer;
		this.css = css;
	}

	@Override
	protected NodeData createDataInstance()
	{
		return CSSFactory.createNodeData(transformer, css);
	}
    
}
