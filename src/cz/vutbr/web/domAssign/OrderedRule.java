package cz.vutbr.web.domAssign;

import cz.vutbr.web.css.RuleSet;

/**
 *
 * @author snowman
 */
public class OrderedRule implements Comparable {
    
    private RuleSet ruleSet;
    private int orderNum;
    
    public OrderedRule(RuleSet r, int on) {
        if(r == null) {
            throw new NullPointerException();
        }
        else {
            ruleSet = r;
            orderNum = on;
        }
    }

    public int compareTo(Object arg0) {
        if(arg0 instanceof OrderedRule) {
            if(orderNum < ((OrderedRule)arg0).orderNum) {
                return -1;
            }
            else if(orderNum > ((OrderedRule)arg0).orderNum) {
                return 1;
            }
            else {
                return 0;
            }
        }
        else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object arg0) {
        if(arg0 instanceof OrderedRule) {
            return ((OrderedRule)arg0).getOrderNum() == orderNum;
        }
        return false;
    }

    public RuleSet getRuleSet() {
        return ruleSet;
    }

    public int getOrderNum() {
        return orderNum;
    }
}
