/**
 * TermBracketedIdentImpl.java
 *
 * Created on 30. 11. 2018, 10:28:47 by burgetr
 */
package cz.vutbr.web.csskit;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cz.vutbr.web.css.TermBracketedIdents;
import cz.vutbr.web.css.TermIdent;

/**
 * 
 * @author burgetr
 */
public class TermBracketedIdentsImpl extends AbstractList<TermIdent> implements TermBracketedIdents {

    protected List<TermIdent> value;
    protected Operator operator;
    
    protected TermBracketedIdentsImpl() {
        this.value = new ArrayList<>();
    }
    
    protected TermBracketedIdentsImpl(int initialSize) {
        this.value = new ArrayList<>(initialSize);
    }
    
    /**
     * @return the value
     */
    public List<TermIdent> getValue() {
        return value;
    }
    
    /**
     * @param value the value to set
     */
    public TermBracketedIdents setValue(List<TermIdent> value) {
        this.value = value;
        return this;
    }
    /**
     * @return the operator
     */
    public Operator getOperator() {
        return operator;
    }
    /**
     * @param operator the operator to set
     */
    public TermBracketedIdents setOperator(Operator operator) {
        this.operator = operator;
        return this;
    }
    
    @Override
    public TermIdent get(int arg0) {
        return value.get(arg0);
    }
    
    @Override
    public void add(int index, TermIdent element) {
        value.add(index, element);
    }
    
    @Override
    public TermIdent remove(int index) {
        return value.remove(index);
    }

    @Override
    public int size() {
        return value.size();
    }
    
    @Override
    public Iterator<TermIdent> iterator() {
        return value.iterator();
    }
    
    @Override
    public boolean add(TermIdent o) {
        return value.add(o);
    }
    
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        // append operator
        if(operator!=null) sb.append(operator.value());
        sb.append('[');
        OutputUtil.appendList(sb, value, OutputUtil.SPACE_DELIM);
        sb.append(']');
        return sb.toString();
    }
    
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((operator == null) ? 0 : operator.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        if (!(obj instanceof TermBracketedIdentsImpl))
            return false;
        TermBracketedIdentsImpl other = (TermBracketedIdentsImpl) obj;
        if (operator == null) {
            if (other.operator != null)
                return false;
        } else if (!operator.equals(other.operator))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    public TermBracketedIdents shallowClone() {
        try {
            return (TermBracketedIdents) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}
