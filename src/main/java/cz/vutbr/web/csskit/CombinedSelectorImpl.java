package cz.vutbr.web.csskit;

import java.util.Arrays;

import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.Selector;

/**
 * CSS CombinedSelector with implementation of specificity
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 * 
 */
public class CombinedSelectorImpl extends AbstractRule<Selector> implements CombinedSelector {
  
	protected CombinedSelectorImpl() {
	}
	
	public Selector getLastSelector() throws UnsupportedOperationException {
		if(list.size()==0)
			throw new UnsupportedOperationException("There is no \"last\" simple selector");
		return list.get(list.size()-1);
	}
	
    @Override
    public Selector.PseudoElementType getPseudoElementType() {
        return getLastSelector().getPseudoElementType(); //pseudo-elements may only be appended after the last simple selector of the selector
    }
	
	/**
	 * Computes specificity of selector
	 */
	public Specificity computeSpecificity() {
		
		Specificity spec = new SpecificityImpl();
		
		for(Selector s: list) 
			s.computeSpecificity(spec);
			
		return spec;
		
	}
	

	public String toString(int depth) {

		StringBuilder sb = new StringBuilder();
		sb = OutputUtil.appendList(sb, list, OutputUtil.EMPTY_DELIM);
		
		return sb.toString();
	}
	
    @Override
    public String toString() {
    	return this.toString(0);
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

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(spec);
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
			if (!(obj instanceof SpecificityImpl))
				return false;
			SpecificityImpl other = (SpecificityImpl) obj;
			if (!Arrays.equals(spec, other.spec))
				return false;
			return true;
		}
		
		
	}
	
}
