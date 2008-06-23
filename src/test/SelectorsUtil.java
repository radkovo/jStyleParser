package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.SimpleSelector;
import cz.vutbr.web.csskit.SelectorImpl;
import cz.vutbr.web.csskit.SimpleSelectorImpl;

/**
 * Creates selectors
 * @author kapy
 *
 */
public class SelectorsUtil {

	/**
	 * Create basic selector of HTML element
	 * @param element
	 * @return
	 */
	public static Selector createSelector(String element) {
		
		Selector sel = new SelectorImpl();
		
		List<SimpleSelector> ssels = new ArrayList<SimpleSelector>();
		SimpleSelector ss = new SimpleSelectorImpl();
		ss.setFirstItem( new SimpleSelectorImpl.ItemImpl(element));
		ssels.add(ss);
		sel.setSimpleSelectors(ssels);
		
		return sel;
	}
	
	/**
	 * Create list of basic selectors
	 * @param elements
	 * @return
	 */
	public static List<Selector> createSelectors(String...elements) {
		
		List<Selector> list = new ArrayList<Selector>();
		for(String e: elements)
			list.add(createSelector(e));
	
		return list;
	}
	
	/**
	 * Appends simple selector to current selector in list
	 * @param selectors List of selector
	 * @param element Name of selectors element
	 * @param c Combinator
	 * @param items Items of simple selector
	 * @return modified list
	 */
	public static List<Selector> appendSimpleSelector(List<Selector> selectors,
			String element, SimpleSelector.Combinator c, SimpleSelector.Item...items) {
		
		if(selectors==null)
			selectors = new ArrayList<Selector>();
		
		// add new selector if empty
		if(selectors.size()==0)
			selectors.add(new SelectorImpl());

		// get last selector
		Selector current = selectors.get(selectors.size()-1);
		
		// list of simple selectors, create if empty
		List<SimpleSelector> ssels = current.getSimpleSelectors();
		if(ssels == null || Collections.emptyList().equals(ssels)) {
			ssels = new ArrayList<SimpleSelector>();
		}
		
		// create new simple selector
		SimpleSelector ss = new SimpleSelectorImpl();
		if(element!=null) // avoid inserting empty element
			ss.setFirstItem(new SimpleSelectorImpl.ItemImpl(element));
		ss.setCombinator(c);
		ss.setItems(Arrays.asList(items));
		
		// add
		ssels.add(ss);
		current.setSimpleSelectors(ssels);
		
		return selectors;
	}
	
	/* wrappers */
	public static List<Selector> appendDescendant(List<Selector> selectors,
			String element, SimpleSelector.Item...items) {
		return appendSimpleSelector(selectors, element, SimpleSelector.Combinator.DESCENDANT, items);
	}
	
	public static List<Selector> appendChild(List<Selector> selectors,
			String element, SimpleSelector.Item...items) {
		return appendSimpleSelector(selectors, element, SimpleSelector.Combinator.CHILD, items);
	}
	
	public static List<Selector> appendAdjacent(List<Selector> selectors,
			String element, SimpleSelector.Item...items) {
		return appendSimpleSelector(selectors, element, SimpleSelector.Combinator.ADJACENT, items);
	}
	
	/**
	 * Append new empty selector to list of selectors
	 * @param selectors List of selectors
	 * @return Modified list
	 */
	public static List<Selector> appendSelector(List<Selector> selectors) {
		
		if(selectors==null)
			selectors = new ArrayList<Selector>();
		
		// add new selector if empty
		if(selectors.size()==0) {
			selectors.add(new SelectorImpl());
			return selectors;
		}
		
		selectors.add(new SelectorImpl());
		
		return selectors;
	}
	
}
