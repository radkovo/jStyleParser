package cz.vutbr.web.css;

import java.util.Set;


/**
 * Encapsulates supported CSS properties, their types and default values.
 * Implementations should provide reasonable User Agent defaults.
 * 
 * @author kapy
 *
 */
public interface SupportedCSS {

	/**
	 * Returns total number of properties defined
	 * @return total number of properties defined
	 */
	int getTotalProperties();
	
	/**
	 * Returns names of defined properties
	 * @return Set with name of defined properties
	 */
	Set<String> getDefinedPropertyNames();
	
	
	/**
	 * Checks whether media type is supported
	 * @param media Name of media, such as <code>screen</code>
	 * @return <code>true</code> if supported, <code>false</code> otherwise
	 */
	boolean isSupportedMedia(String media);
	
	/**
	 * Checks whether property is supported
	 * @param property Name of CSS property
	 * @return <code>true</code> if supported, <code>false</code> otherwise
	 */
	boolean isSupportedCSSProperty(String property);
	
	/**
	 * Return default value of CSSProperty under given name
	 * @param propertyName Name of CSSProperty
	 * @return Default value of CSSProperty or <code>null</code>
	 * when property with this name is not found
	 */
	CSSProperty getDefaultProperty(String propertyName);
	
	/**
	 * Some CSSProperties have even additional values. When this 
	 * happens, this functions could be used to retrieve that value.
	 * 
	 * Example: property <code>background-position</code>, 
	 * has default value of list with two percentages 0% 0%
	 * 
	 * @param propertyName Name of CSSProperty
	 * @return Default value or CSSProperty's value or <code>null</code>
	 * when property with this name is not found
	 */
	Term<?> getDefaultValue(String propertyName);
	
	/**
	 * For testing, this should get name of randomly chosen CSSProperty.
	 * @return Name of CSSProperty
	 */
	String getRandomPropertyName();
	
	/**
	 * Returns ordinal number of propertyName. This number must be positive integer. 
	 * This value should be unique with supported properties
	 * @param propertyName Name of property
	 * @return ordinal number in property was found, <code>-1</code> elsewhere
	 */
	int getOrdinal(String propertyName);

	/**
	 * Returns property name according to ordinal number
	 * @param o Ordinal number previously retrieved by <code>getOrdinal()</code>
	 * @return Name of property or <code>null</code> if not found
	 */
	String getPropertyName(int o);
	
}
