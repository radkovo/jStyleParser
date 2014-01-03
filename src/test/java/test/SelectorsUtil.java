package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.RuleFactory;
import cz.vutbr.web.css.Selector;


/**
 * Creates selectors
 * @author kapy
 *
 */
public class SelectorsUtil {

	public static final RuleFactory rf = CSSFactory.getRuleFactory();
	
	
	/**
	 * Create basic selector of HTML element
	 * @param element the element name
	 * @return the created selector
	 */
	public static CombinedSelector createCS(String element) {
		
		CombinedSelector cs = rf.createCombinedSelector();
		
		List<Selector> list = new ArrayList<Selector>();
		Selector ss = (Selector) rf.createSelector().unlock();
		ss.add(rf.createElement(element));
		list.add(ss);
		cs.replaceAll(list);
		
		return cs;
	}
	
	/**
	 * Create list of combined selectors
	 * @param elements list of element names
	 * @return a list of selectors
	 */
	public static List<CombinedSelector> createSelectors(String...elements) {
		
		List<CombinedSelector> list = new ArrayList<CombinedSelector>();
		for(String e: elements)
			list.add(createCS(e));
	
		return list;
	}
	
	/**
	 * Appends simple selector to current selector in list
	 * @param cslist List of selector
	 * @param element Name of selectors element
	 * @param c Combinator
	 * @param items Items of simple selector
	 * @return modified list
	 */
	public static List<CombinedSelector> appendSimpleSelector(List<CombinedSelector> cslist,
			String element, Selector.Combinator c, Selector.SelectorPart...items) {
		
		if(cslist==null)
			cslist = new ArrayList<CombinedSelector>();
		
		// add new combined selector if empty
		if(cslist.size()==0)
			cslist.add(rf.createCombinedSelector());

		// get last combined selector
		CombinedSelector current = cslist.get(cslist.size()-1);
		
		// list of selectors, create if empty
		if(current.asList()==null || Collections.emptyList().equals(current.asList())) {
			current.unlock();
		}
		
		// create new selector
		Selector s = (Selector) rf.createSelector().unlock();
		if(element!=null) // avoid inserting empty element
			s.add(rf.createElement(element));
		s.setCombinator(c);
		s.addAll(Arrays.asList(items));
		
		// add
		current.add(s);
		
		return cslist;
	}
	
	/* wrappers */
	public static List<CombinedSelector> appendDescendant(List<CombinedSelector> selectors,
			String element, Selector.SelectorPart...items) {
		return appendSimpleSelector(selectors, element, Selector.Combinator.DESCENDANT, items);
	}
	
	public static List<CombinedSelector> appendChild(List<CombinedSelector> selectors,
			String element, Selector.SelectorPart...items) {
		return appendSimpleSelector(selectors, element, Selector.Combinator.CHILD, items);
	}
	
	public static List<CombinedSelector> appendAdjacent(List<CombinedSelector> selectors,
			String element, Selector.SelectorPart...items) {
		return appendSimpleSelector(selectors, element, Selector.Combinator.ADJACENT, items);
	}
	
    public static List<CombinedSelector> appendPreceding(List<CombinedSelector> selectors,
            String element, Selector.SelectorPart...items) {
        return appendSimpleSelector(selectors, element, Selector.Combinator.PRECEDING, items);
    }
    
	/**
	 * Append new empty combined selector to list of selectors
	 * @param cslist List of selectors
	 * @return Modified list
	 */
	public static List<CombinedSelector> appendCS(List<CombinedSelector> cslist) {
		
		if(cslist==null)
			cslist = new ArrayList<CombinedSelector>();
		
		// add new selector if empty
		if(cslist.size()==0) {
			cslist.add(rf.createCombinedSelector());
			return cslist;
		}
		
		cslist.add(rf.createCombinedSelector());
		
		return cslist;
	}
	
}
