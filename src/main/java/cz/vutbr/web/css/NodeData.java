package cz.vutbr.web.css;

import java.util.Collection;

/**
 * Wrap of CSS properties defined for element. Enumeration values follows this
 * syntax:
 * 
 * <ul style="list-style:none"> <li>
 * <b>UPPERCASE</b> terminal symbols, direct values present in stylesheet, such
 * as background-color: transparent;</li> <li>
 * <b>lowercase</b> non-terminal symbols, just information that concrete value
 * is stored somewhere else</li> </ul>
 * 
 * @author kapy
 */
public interface NodeData {

	/**
	 * Returns property of given name and supposed type. Inherited properties
	 * are included.
	 * 
	 * @param <T>
	 *            Type of property returned. Let compiler infer returning type 
	 *            from left part of statement, otherwise return just CSSProperty
	 * @param name
	 *            Name of property
	 * @return Value of property of type T or <code>null</code>
	 */
	public <T extends CSSProperty> T getProperty(String name);

	/**
	 * Returns property of given name and supposed type. Inherited properties
	 * can be avoided
	 * 
	 * @param <T>
	 *            Type of property returned. Let compiler infer returning type 
	 *            from left part of statement, otherwise return just CSSProperty
	 * @param name
	 *            Name of property
	 * @param includeInherited
	 *            Whether to include inherited properties or not
	 * @return Value of property of type T or <code>null</code>
	 */
	public <T extends CSSProperty> T getProperty(String name,
			boolean includeInherited);

    /**
     * Returns value of property of given name as a generic type. Inherited
     * values can be avoided.
     * 
     * @param name
     *            Name of property
     * @param includeInherited
     *            Whether to include inherited properties or not
     * @return Value of property or <code>null</code>
     */
    public Term<?> getValue(String name, boolean includeInherited);
    
	/**
	 * Returns value of property of given name and supposed type. Inherited
	 * values are included.
	 * 
	 * @param <T>
	 *            Type of value returned
	 * @param clazz
	 *            Class of type
	 * @param name
	 *            Name of property
	 * @return Value of property of type T or <code>null</code>
	 */
	public <T extends Term<?>> T getValue(Class<T> clazz, String name);

	/**
	 * Returns value of property of given name and supposed type. Inherited
	 * values can be avoided.
	 * 
	 * @param <T>
	 *            Type of value returned 
	 * @param clazz
	 *            Class of type
	 * @param name
	 *            Name of property
	 * @param includeInherited
	 *            Whether to include inherited properties or not
	 * @return Value of property of type T or <code>null</code>
	 */
	public <T extends Term<?>> T getValue(Class<T> clazz, String name,
			boolean includeInherited);

	/**
	 * Accepts values from parent as its own. <code>null</code> parent is
	 * allowed, than instance is returned unchanged.
	 * 
	 * @param parent
	 *            Source of inheritance
	 * @return Modified instance
	 * @throws ClassCastException When parent implementation class is not the same
	 */
	public NodeData inheritFrom(NodeData parent) throws ClassCastException;

	/**
	 * Replaces all <code>inherit</code> CSS properties either with values of
	 * parent, or, if not present, with default values of user agent.
	 * 
	 * @return Modified property
	 */
	public NodeData concretize();

	/**
	 * Adds data stored in declaration into current instance
	 * 
	 * @param d
	 *            Declaration to be added
	 * @return Modified instance
	 *            	 
	 */            
	public NodeData push(Declaration d);

	/**
	 * Returns the names of all the that are available in the current node.
	 *  
	 * @return the name of the properties.
	 */
	public Collection<String> getPropertyNames();
	
	/**
	 * Obtains the source declaration used for the given property. Inherited properties are included.
	 * @param name The property name.
	 * @return the source declaration
	 */
	public Declaration getSourceDeclaration(String name);
	
    /**
     * Obtains the source declaration used for the given property.
     * @param name The property name.
     * @param includeInherited whether to include the inherited properties.
     * @return the source declaration
     */
    public Declaration getSourceDeclaration(String name, boolean includeInherited);
    
}
