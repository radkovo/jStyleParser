package cz.vutbr.web.css;

/**
 * Special case of rule, where rule is meant to be comparable
 * with other rules to determine priority of CSS declarations
 * @author kapy
 *
 * @param <T> Internal content of rule
 */
public interface RuleBlock<T> extends Rule<T>, Comparable<RuleBlock<?>>{

	/**
	 * Priority mark object.
	 * @author kapy
	 *
	 */
	public static interface Priority extends Comparable<Priority> {
		@Override
		public boolean equals(Object obj);
		@Override
		public int hashCode();
	}
	
	/**
	 * Sets priority for current object
	 * @param priority New objects priority
	 * @return Modified instance
	 */
	public RuleBlock<T> setPriority(Priority priority);
	
	/**
	 * Returns priority associated with this object
	 * @return Priority associated
	 */
	public Priority getPriority();
	
}
