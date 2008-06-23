package cz.vutbr.web.csskit;

import java.util.Collections;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.SimpleSelector;
import cz.vutbr.web.css.StyleSheetNotValidException;

/**
 * Selector
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko, 2008
 * @version 1.0 * Changed according to new interface
 * 				 * Construction moved to parser
 * 				 * Rewritten toString() method 
 */
public class SelectorImpl implements Selector {
  
	protected List<SimpleSelector> simpleSelectors;
	
	public SelectorImpl() {
		this.simpleSelectors = Collections.emptyList();
	}	
	
	public SimpleSelector getLastSimpleSelector()
			throws OperationNotSupportedException {
	
		if(simpleSelectors.size()==0)
			throw new OperationNotSupportedException("There is no \"last\" simple selector");
		
		return simpleSelectors.get(simpleSelectors.size()-1);
	}
	
	
	/**
	 * @return the simpleSelectors
	 */
	public List<SimpleSelector> getSimpleSelectors() {
		return simpleSelectors;
	}


	/**
	 * @param simpleSelectors the simpleSelectors to set
	 */
	public void setSimpleSelectors(List<SimpleSelector> simpleSelectors) {
		this.simpleSelectors = simpleSelectors;
	}

	public String toString(int depth) {

		StringBuilder sb = new StringBuilder();
		sb = OutputUtil.appendList(sb, simpleSelectors, OutputUtil.EMPTY_DELIM);
		
		return sb.toString();
	}
	
    @Override
    public String toString() {
    	return this.toString(0);
    }       
    
    
    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((simpleSelectors == null) ? 0 : simpleSelectors.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SelectorImpl))
			return false;
		final SelectorImpl other = (SelectorImpl) obj;
		if (simpleSelectors == null) {
			if (other.simpleSelectors != null)
				return false;
		} else if (!simpleSelectors.equals(other.simpleSelectors))
			return false;
		return true;
	}

	public void check(String path) throws StyleSheetNotValidException {
        if(simpleSelectors.isEmpty()) {
            throw new StyleSheetNotValidException("Selector without SimpleSelector", path);
        }
        for(int i = 0; i < simpleSelectors.size(); i++) {
            SimpleSelector simpleSelector = simpleSelectors.get(i);
            if(i == 0 && simpleSelector.getCombinator() != null) {
                simpleSelector.setCombinator(null); //Fix error
            }
            if(i != 0 && simpleSelector.getCombinator() == null) {
                throw new StyleSheetNotValidException("SimpleSelector without operator!", path + " -> simpleSelector("+simpleSelector.toString()+")");
            }
        }
    }
}
