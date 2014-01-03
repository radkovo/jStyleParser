package cz.vutbr.web.csskit;

import cz.vutbr.web.css.RuleBlock.Priority;

/**
 * Assigns priorities
 * @author kapy
 *
 */
public interface PriorityStrategy {

	/**
	 * Gets current priority and atomically increments
	 * @return Priority instance
	 */
	public Priority getAndIncrement();
	

	/**
	 * Gets current priority and atomically increments.
	 * This is in general an alias to {@code getAndIncrement()}
	 * @return Priority instance
	 */
	public Priority markAndIncrement();
	
}
