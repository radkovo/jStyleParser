package cz.vutbr.web.domassign;

import cz.vutbr.web.css.RuleSet;

/**
 * 
 * @author snowman, modified by kapy
 * @version 1.0 Improved generics usage,
 * 				 added hashCode, changed constructor
 */
public class OrderedRule implements Comparable<OrderedRule> {

	private RuleSet ruleSet;
	private int orderNum;

	public OrderedRule(RuleSet ruleSet, int orderNum) {

		if (ruleSet == null)
			throw new IllegalArgumentException("RuleSet must be set!");

		this.ruleSet = ruleSet;
		this.orderNum = orderNum;
	}

	public int compareTo(OrderedRule rule) {

		if (orderNum < rule.orderNum)
			return -1;
		else if (orderNum > rule.orderNum)
			return 1;
		else
			return 0;

	}

	public RuleSet getRuleSet() {
		return ruleSet;
	}

	public int getOrderNum() {
		return orderNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orderNum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof OrderedRule))
			return false;
		final OrderedRule other = (OrderedRule) obj;
		if (orderNum != other.orderNum)
			return false;
		
		return true;
	}
}
