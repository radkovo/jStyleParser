/**
 * 
 */
package cz.vutbr.web.csskit;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFloatValue;
import cz.vutbr.web.css.TermInteger;
import cz.vutbr.web.css.TermNumber;
import cz.vutbr.web.css.TermNumeric;
import cz.vutbr.web.css.TermNumeric.Unit.Type;
import cz.vutbr.web.css.TermOperator;
import cz.vutbr.web.css.TermPercent;


/**
 * This class tracks the calc() arguments (postorder) and their resulting type.
 * @author burgetr
 */
public class CalcArgs extends ArrayList<Term<?>> {
    
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CalcArgs.class);
    
    public static final StringEvaluator stringEvaluator;
    static {
        stringEvaluator = new StringEvaluator();
    }
    
    private Type utype = Type.none; //expected value type
    private boolean isint = true; //all the values are integers?
    private boolean valid = true;
    
    public CalcArgs(List<Term<?>> terms) {
        super(terms.size());
        scanArguments(terms);
    }

    public TermNumeric.Unit.Type getType() {
        return utype;
    }

    public boolean isInt() {
        return isint;
    }
    
    public boolean isValid() {
        return valid;
    }
    
    protected void scanArguments(List<Term<?>> args) {
        //tansform expression to a postfix notation
        Deque<TermOperator> stack = new ArrayDeque<>(5);
        boolean unary = true;
        for (Term<?> t : args) {
            if (t instanceof TermFloatValue) {
                add(t);
                considerType((TermFloatValue) t);
                unary = false;
                if (!valid) break; //type mismatch
            } else if (t instanceof TermOperator) {
                TermOperator op = (TermOperator) t;
                if (unary && op.getValue() == '-') {
                    op = (TermOperator) op.shallowClone();
                    op.setValue('~');
                }
                final int p = getPriority(op);
                if (p != -1) {
                    TermOperator top = stack.peek();
                    if (top == null || top.getValue() == '(' || p > getPriority(top)) {
                        stack.push(op);
                    } else {
                        do {
                            add(top);
                            stack.pop();
                            top = stack.peek();
                        } while (top != null && top.getValue() != '(' && p <= getPriority(top));
                        stack.push(op);
                    }
                    unary = true;
                } else if (op.getValue() == '(') {
                    stack.push(op);
                    unary = true;
                } else if (op.getValue() == ')') {
                    if (!stack.isEmpty()) {
                        TermOperator top = stack.pop();
                        while (top != null && top.getValue() != '(' && !stack.isEmpty()) {
                            add(top);
                            top = stack.pop();
                        }
                    }

                    unary = false;
                } else {
                    valid = false;
                    break;
                }
            } else {
                valid = false;
                break;
            }
        }
        while (!stack.isEmpty())
            add(stack.pop());
    }
    
    private int getPriority(TermOperator op) {
        char c = op.getValue();
        switch (c) {
            case '+':
            case '-':
                return 0;
            case '*':
            case '/':
                return 1;
            case '~':
                return 2; //unary -
            default:
                return -1;
        }
    }

    //isLength, isFrequency, isAngle, isTime, isNumber, isInteger
    
    private void considerType(TermFloatValue term) {
        
        TermNumeric.Unit unit = term.getUnit();
        if (utype == Type.none) { //only a number
            if (unit != null && unit.getType() != Type.none) {
                utype = unit.getType();
            } else if (term instanceof TermPercent) {
                utype = Type.length; //percentages have no unit
            } else if (term instanceof TermNumber) {
                isint = false; //not an integer
            }
        } else { //some type already assigned, check for mismatches
            if (unit != null && unit.getType() != Type.none) {
                if (unit.getType() != utype)
                    valid = false;
            }
        }
    }
    
    //=========================================================================
    
    public <T> T evaluate(Evaluator<T> eval) throws IllegalArgumentException {
        try {
            Deque<T> stack = new ArrayDeque<>();
            for (Term<?> t : this) {
                if (t instanceof TermOperator) {
                    T val;
                    if (((TermOperator) t).getValue() == '~') {
                        T val1 = stack.pop();
                        val = eval.evaluateOperator(val1, (TermOperator) t);
                    } else {
                        T val2 = stack.pop();
                        T val1 = stack.pop();
                        val = eval.evaluateOperator(val1, val2, (TermOperator) t);
                    }
                    stack.push(val);
                } else if (t instanceof TermFloatValue) {
                    T val = eval.evaluateArgument((TermFloatValue) t);
                    stack.push(val);
                }
            }
            return stack.peek();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Couldn't evaluate calc() expression", e);
        }
    }
    
    //=========================================================================
    
    /**
     * A generic evaluator that is able to obtain a resulting value of the given type
     * from the expressions.
     * 
     * @author burgetr
     * @param <T> the type of the resulting value
     */
    public interface Evaluator<T> {
        
        public T evaluateArgument(TermFloatValue val);
        
        public T evaluateOperator(T val1, T val2, TermOperator op);
        
        public T evaluateOperator(T val, TermOperator op);
        
    }
    
    /**
     * A pre-defined evaluator that produces a string representation of the expression.
     * 
     * @author burgetr
     */
    public static class StringEvaluator implements Evaluator<String> {

        @Override
        public String evaluateArgument(TermFloatValue val) {
            return val.toString();
        }

        @Override
        public String evaluateOperator(String val1, String val2, TermOperator op) {
            return "(" + val1 + " " + op.toString() + " " + val2.toString() + ")";
        }
        
        @Override
        public String evaluateOperator(String val, TermOperator op) {
            if (op.getValue() == '~')
                return "-" + val;
            else
                return op.getValue() + val;
        }
        
    }
    
    /**
     * An abstract pre-defined evaluator that produces a double value from the expression.
     * Implementations must provide the {@code resolveValue()} method that evaluates an atomic value.
     *  
     * @author burgetr
     */
    public static abstract class DoubleEvaluator implements Evaluator<Double> {
        
        @Override
        public Double evaluateArgument(TermFloatValue val) {
            if (val instanceof TermNumber || val instanceof TermInteger)
                return Double.valueOf(val.getValue());
            else
                return resolveValue(val);
        }

        @Override
        public Double evaluateOperator(Double val1, Double val2, TermOperator op) {
            switch (op.getValue()) {
                case '+': return val1 + val2;
                case '-': return val1 - val2;
                case '*': return val1 * val2;
                case '/': return val1 / val2;
                default:
                    log.error("Unknown operator {} in expression", op);
                    return 0.0;
            }
        }
        
        @Override
        public Double evaluateOperator(Double val, TermOperator op)
        {
            if (op.getValue() == '~') {
                return -val;
            } else {
                log.error("Unknown unary operator {} in expression", op);
                return val;
            }
        }

        /**
         * Evaluates an atomic value.
         * @param val the input value specification
         * @return the evaluated value
         */
        public abstract double resolveValue(TermFloatValue val);
        
    }

    /**
     * An abstract pre-defined evaluator that produces a float value from the expression.
     * Implementations must provide the {@code resolveValue()} method that evaluates an atomic value.
     *  
     * @author burgetr
     */
    public static abstract class FloatEvaluator implements Evaluator<Float> {
        
        @Override
        public Float evaluateArgument(TermFloatValue val) {
            if (val instanceof TermNumber || val instanceof TermInteger)
                return Float.valueOf(val.getValue());
            else
                return resolveValue(val);
        }

        @Override
        public Float evaluateOperator(Float val1, Float val2, TermOperator op) {
            switch (op.getValue()) {
                case '+': return val1 + val2;
                case '-': return val1 - val2;
                case '*': return val1 * val2;
                case '/': return val1 / val2;
                default:
                    log.error("Unknown operator {} in expression", op);
                    return 0.0f;
            }
        }
        
        @Override
        public Float evaluateOperator(Float val, TermOperator op)
        {
            if (op.getValue() == '~') {
                return -val;
            } else {
                log.error("Unknown unary operator {} in expression", op);
                return val;
            }
        }

        /**
         * Evaluates an atomic value.
         * @param val the input value specification
         * @return the evaluated value
         */
        public abstract float resolveValue(TermFloatValue val);
        
    }
    
}
