package cz.vutbr.web.csskit;

import java.util.Collections;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.SimpleSelector;
import cz.vutbr.web.css.StyleSheetNotValidException;
import cz.vutbr.web.css.Selector.Specificity.Level;

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
	
	/**
	 * Computes specificity of selector
	 */
	public Specificity computeSpecificity() {
		
		Specificity spec = new SpecificityImpl();
		
		for(SimpleSelector s: simpleSelectors) {
			
			if(s.getFirstItem()!=null && s.getFirstItem().isElement())
				spec.add(Level.D);
			
			for(SimpleSelector.Item item: s.getItems()) {
				if(item.isElement()) spec.add(Level.D);
				else if(item.isAttribute()) spec.add(Level.C);
				else if(item.isPseudoElement()) spec.add(Level.D);
				else if(item.isPseudoClass()) spec.add(Level.C);
				else if(item.isClass()) spec.add(Level.C);
				else if(item.isID()) spec.add(Level.B);
			}
			
		}
		
		return spec;
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
	
	public static class SpecificityImpl implements Specificity {
			
		protected int[] spec = new int[Level.values().length];
		
		public int compareTo(Specificity o) {
			
			if(get(Level.A) > o.get(Level.A)) return 1;
			else if(get(Level.A) < o.get(Level.A)) return -1;
			
			if(get(Level.B) > o.get(Level.B)) return 1;
			else if(get(Level.B) < o.get(Level.B)) return -1;
			
			if(get(Level.C) > o.get(Level.C)) return 1;
			else if(get(Level.C) < o.get(Level.C)) return -1;
			
			if(get(Level.D) > o.get(Level.D)) return 1;
			else if(get(Level.D) < o.get(Level.D)) return -1;
			
			return 0;
			
		}
		
		public int get(Level level) {
			
			switch(level) {
			case A: return spec[0];
			case B: return spec[1];
			case C: return spec[2];
			case D: return spec[3];
			default: return 0;
			}
		}
		
		public void add(Level level) {
			
			switch(level) {
			case A: spec[0]++;
			case B: spec[1]++;
			case C: spec[2]++;
			case D: spec[3]++;
			}
		}
	}
	
}
