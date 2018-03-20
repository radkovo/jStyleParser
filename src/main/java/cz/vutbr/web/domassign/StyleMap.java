/**
 * StyleMap.java
 *
 * Created on 22.1.2010, 16:06:07 by burgetr
 */
package cz.vutbr.web.domassign;

import org.w3c.dom.Element;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.Selector.PseudoElementType;

/**
 * This is a map that assigns a style to a particular elements and moreover, it
 * gathers the information about the pseudo elements. 
 * 
 * @author burgetr
 */
public class StyleMap extends MultiMap<Element, PseudoElementType, NodeData>
{

	public StyleMap(int size)
	{
		super(size);
	}

	@Override
	protected NodeData createDataInstance()
	{
		return CSSFactory.createNodeData();
	}
    
}
