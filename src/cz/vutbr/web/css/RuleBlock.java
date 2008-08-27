package cz.vutbr.web.css;

public interface RuleBlock<T> extends Rule<T>, Comparable<RuleBlock<?>>{

	public RuleBlock<T> setFilePosition(int position);
	
	public int getFilePosition();
	
	public int compareTo(RuleBlock<?> o) throws ClassCastException;
	
}
