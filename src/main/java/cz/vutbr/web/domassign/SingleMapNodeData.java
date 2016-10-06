package cz.vutbr.web.domassign;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.SupportedCSS;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.csskit.DeclarationTransformer;
import cz.vutbr.web.csskit.OutputUtil;

/**
 * Implementation of NodeData by single HashMap. Is more space efficient at the cost of 
 * speed.
 * 
 * @author kapy
 *
 */
public class SingleMapNodeData implements NodeData {

	private static final int COMMON_DECLARATION_SIZE = 7;
	
	protected static DeclarationTransformer transformer = CSSFactory.getDeclarationTransformer();
	protected static SupportedCSS css = CSSFactory.getSupportedCSS();
	
	private Map<String, Quadruple> map;
	
	public SingleMapNodeData() {
		this.map = new HashMap<String, Quadruple>(css.getTotalProperties(), 1.0f);
	}
	
	public <T extends CSSProperty> T getProperty(String name) {
		// until java 7 compiler is not able to infer correct type 
		// this is an ugly workaround
		return this.<T>getProperty(name, true);
	}

	public <T extends CSSProperty> T getProperty(String name,
			boolean includeInherited) {
		
		Quadruple q = map.get(name);
		if(q==null) return null;
		
		CSSProperty tmp;
		
		if(includeInherited) {
			if(q.curProp!=null) tmp = q.curProp;
			else tmp = q.inhProp;
		}
		else {
			tmp = q.curProp;
		}
		
		// this will cast to inferred type
		// if there is no inferred type, cast to CSSProperty is safe
		// otherwise the possibility having wrong left side of assignment
		// is roughly the same as use wrong dynamic class cast 
		@SuppressWarnings("unchecked")
		T retval = (T) tmp;
		return retval;
		
	}

    public Term<?> getValue(String name, boolean includeInherited) {
        
        Quadruple q = map.get(name);
        if(q==null) return null;
        
        if(includeInherited) {
            if(q.curProp!=null)
                return q.curValue;
            else
                return q.inhValue;
        }
        else
            return q.curValue;
    }
    
	public <T extends Term<?>> T getValue(Class<T> clazz, String name) {
		return getValue(clazz, name, true);
	}

    public String getAsString(String name, boolean includeInherited) {
        Quadruple q = map.get(name);
        if(q==null) return null;
        
        CSSProperty prop = q.curProp;
        Term<?> value = q.curValue;
        if (prop == null && includeInherited) {
            prop = q.inhProp;
            value = q.inhValue;
        }
        return (value == null ? prop.toString() : value.toString());
    }

    public <T extends Term<?>> T getValue(Class<T> clazz, String name,
			boolean includeInherited) {
		
		Quadruple q = map.get(name);
		if(q==null) return null;
		
		if(includeInherited) {
			if(q.curProp!=null)
			    return clazz.cast(q.curValue);
			else
			    return clazz.cast(q.inhValue);
		}
		else
		    return clazz.cast(q.curValue);
	}

	public NodeData push(Declaration d) {
		
		Map<String,CSSProperty> properties = 
			new HashMap<String,CSSProperty>(COMMON_DECLARATION_SIZE);
		Map<String,Term<?>> terms = 
			new HashMap<String, Term<?>>(COMMON_DECLARATION_SIZE);
		
		boolean result = transformer.parseDeclaration(d, properties, terms);
		
		// in case of false do not insert anything
		if(!result) return this;
		
		for(Entry<String, CSSProperty> entry : properties.entrySet()) {
		    final String key = entry.getKey();
			Quadruple q = map.get(key);
			if(q==null) q = new Quadruple();
			q.curProp = entry.getValue();
			q.curValue = terms.get(key);
			q.curSource = d;
			// remove operator
			if((q.curValue!=null) && (q.curValue.getOperator() != null)) {
				q.curValue = q.curValue.shallowClone().setOperator(null);
			}
			map.put(key, q);
		}
		return this;

	}
	
	public NodeData concretize() {
		
		for(Map.Entry<String, Quadruple> entry : map.entrySet()) {
		    final String key = entry.getKey();
			final Quadruple q = entry.getValue();
			
			// replace current with inherited or defaults
			if(q.curProp!=null && q.curProp.equalsInherit()) {
				if(q.inhProp==null) q.curProp = css.getDefaultProperty(key);
				else {
				    q.curProp = q.inhProp;
				    q.curSource = q.inhSource;
				}
				
				if(q.inhValue==null) q.curValue = css.getDefaultValue(key);
				else q.curValue = q.inhValue;
				
	            map.put(key, q);
			}
		}
		return this;
	}
	
	public NodeData inheritFrom(NodeData parent) throws ClassCastException{
		
		if(parent==null)
			return this;
		
		if(!(parent instanceof SingleMapNodeData))
			throw new ClassCastException(
					"Cant't inherit from NodeData different from "
							+ this.getClass().getName() + "("+ parent.getClass().getName()+")");
		
		SingleMapNodeData nd = (SingleMapNodeData) parent;
		
		// inherit values
		for(Entry<String, Quadruple> entry : nd.map.entrySet()) {
		    final String key = entry.getKey();
			final Quadruple qp = entry.getValue();
			Quadruple q = map.get(key);
			
			// create new quadruple if this do not contain one
			// for this property
			if(q==null) q = new Quadruple();
			
			boolean forceInherit = (q.curProp != null && q.curProp.equalsInherit());
			boolean changed = false;
			
			//try the inherited value of the parent
			if(qp.inhProp!=null && (qp.inhProp.inherited() || forceInherit)) {
				q.inhProp = qp.inhProp;
				q.inhValue = qp.inhValue;
				q.inhSource = qp.inhSource;
				changed = true;
			}
			
			//try the declared property of the parent
			if(qp.curProp!=null && (qp.curProp.inherited() || forceInherit)) {
				q.inhProp = qp.curProp;
				q.inhValue = qp.curValue;
                q.inhSource = qp.curSource;
                changed = true;
			}
			// insert/replace only if contains inherited/original 
			// value			
			if(changed && !q.isEmpty())
			    map.put(key, q);
		}
		return this;
	}

	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);

		for(String key:keys) {
			// always use own value if exists
			Quadruple q = map.get(key);

			CSSProperty prop = q.curProp;
			if(prop==null) prop = q.inhProp;

			Term<?> value = q.curValue;
			if(value==null) value = q.inhValue;
			
			sb.append(key).append(OutputUtil.PROPERTY_OPENING);
			
			if(value!=null) sb.append(value.toString());
			else sb.append(prop.toString());
				
			sb.append(OutputUtil.PROPERTY_CLOSING);
			
		}
		return sb.toString();
	}

    @Override
    public Collection<String> getPropertyNames()
    {
        final List<String> keys = new ArrayList<String>();
        keys.addAll(map.keySet());
        return keys;
    }
	
    @Override
    public Declaration getSourceDeclaration(String name)
    {
        return getSourceDeclaration(name, true);
    }
    
    @Override
    public Declaration getSourceDeclaration(String name, boolean includeInherited)
    {
        Quadruple q = map.get(name);
        if (q == null)
            return null;
        else
        {
            if(includeInherited) {
                if(q.curSource!=null) return q.curSource;
                return q.inhSource;
            }
            else
                return q.curSource;
        }
    }

	static class Quadruple {
		CSSProperty inhProp = null;
		CSSProperty curProp = null;
		Term<?> inhValue = null;
		Term<?> curValue = null;
		Declaration inhSource = null;
        Declaration curSource = null;
		
		public Quadruple() {			
		}
		
		public boolean isEmpty() {
			return inhProp==null && curProp==null &&
			inhValue==null && curValue==null;
		}
	}

}



