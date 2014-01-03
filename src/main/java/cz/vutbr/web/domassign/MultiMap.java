/**
 * MultiMap.java
 *
 * Created on 31.1.2010, 21:20:00 by radek
 */
package cz.vutbr.web.domassign;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 * This is a general map that assigns the data D to two keys E (Element) and P (Pseudo element).
 * The element is mandatory, the PseudoElement key value may be null. When P is null, the structure 
 * behaves as a simple map called a main map. Other values of PseudoElement create so-called pseudo
 * maps. The map is optimized to provide the best performance for the main map.
 * 
 * @author burgetr
 */
public abstract class MultiMap<E, P, D>
{
    private HashMap<E, D> mainMap; //main map for no pseudo-elements
    private HashMap<E, HashMap<P, D>> pseudoMaps; //maps for the individual pseudo-elements

    /**
     * Creates an empty map
     */
    public MultiMap()
    {
        mainMap = new HashMap<E, D>();
        pseudoMaps = new HashMap<E, HashMap<P, D>>();
    }
    
    /**
     * Creates an empty map
     */
    public MultiMap(int initialSize)
    {
        mainMap = new HashMap<E, D>(initialSize);
        pseudoMaps = new HashMap<E, HashMap<P, D>>();
    }
    
    /**
     * Creates a new instance of the data value.
     * This is only used by {@link MultiMap#getOrCreate(Object, Object)}.
     */
    protected abstract D createDataInstance();
    
    /**
     * Obtains the size of the main map (where P is null)
     * @return the number of elements in the main map.
     */
    public int size()
    {
        return mainMap.size();
    }
    
    /**
     * Gets the data for the given element and pseudo-element.
     * @param el the element
     * @param pseudo a pseudo-element or null, if no pseudo-element is required
     * @return the stored data
     */
    public D get(E el, P pseudo)
    {
        D ret;
        if (pseudo == null)
            ret = mainMap.get(el);
        else
        {
        	HashMap<P, D> map = pseudoMaps.get(el);
            if (map == null)
                ret = null;
            else
                ret = map.get(pseudo);
        }
        return ret;
    }
    
    /**
     * Gets the data for the given element and no pseudo-element
     * @param el the element
     * @return the stored data
     */
    public D get(E el)
    {
        return mainMap.get(el);
    }
    
    /**
     * Gets the data or creates an empty list if it does not exist yet.
     * @param el the element
     * @param pseudo a pseudo-element or null, if no pseudo-element is required
     * @return the stored data
     */
    public D getOrCreate(E el, P pseudo)
    {
        D ret;
        if (pseudo == null)
        {
            ret = mainMap.get(el);
            if (ret == null)
            {
                ret = createDataInstance();
                mainMap.put(el, ret);
            }
        }
        else
        {
            HashMap<P, D> map = pseudoMaps.get(el);
            if (map == null)
            {
                map = new HashMap<P, D>();
                pseudoMaps.put(el, map);
            }
            ret = map.get(pseudo);
            if (ret == null)
            {
                ret = createDataInstance();
                map.put(pseudo, ret);
            }
        }
        return ret;
    }
    
    
    
    /**
     * Sets the data for the specified element and pseudo-element.
     * @param el the element to which the data belongs
     * @param pseudo a pseudo-element or null of none is required
     * @param data data to be set
     */
    public void put(E el, P pseudo, D data)
    {
        if (pseudo == null)
            mainMap.put(el, data);
        else
        {
            HashMap<P, D> map = pseudoMaps.get(el);
            if (map == null)
            {
                map = new HashMap<P, D>();
                pseudoMaps.put(el, map);
            }
            map.put(pseudo, data);
        }
    }
    
    /**
     * Gets all the keys (elements) of the main map.
     * @return A set of elements contained in the map.
     */
    public Set<E> keySet()
    {
    	return mainMap.keySet();
    }
    
    
    /**
     * Gets all the pseudo elements that are available for the given element.
     * @param el The given element
     * @return A set of all pseudo elements available for the element
     */
    public Set<P> pseudoSet(E el)
    {
        HashMap<P, D> map = pseudoMaps.get(el);
        if (map == null)
            return Collections.emptySet();
        else
            return map.keySet();
    }
    
    /**
     * Checks if the given pseudo element is available for the given element
     * @param el The element
     * @param pseudo The tested pseudo element
     * @return true when there is some value associated with the given pair
     */
    public boolean hasPseudo(E el, P pseudo)
    {
        HashMap<P, D> map = pseudoMaps.get(el);
        if (map == null)
            return false;
        else
            return map.containsKey(pseudo);
    }
    
}
