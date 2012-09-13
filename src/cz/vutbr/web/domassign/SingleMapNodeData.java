package cz.vutbr.web.domassign;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.SupportedCSS;
import cz.vutbr.web.css.Term;
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
            if(q.curValue!=null) return q.curValue;
            return q.inhValue;
        }
        
        return q.curValue;
    }
    
	public <T extends Term<?>> T getValue(Class<T> clazz, String name) {
		return getValue(clazz, name, true);
	}

	public <T extends Term<?>> T getValue(Class<T> clazz, String name,
			boolean includeInherited) {
		
		Quadruple q = map.get(name);
		if(q==null) return null;
		
		if(includeInherited) {
			if(q.curValue!=null) return clazz.cast(q.curValue);
			return clazz.cast(q.inhValue);
		}
		
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
		
		for(String key: properties.keySet()) {
			Quadruple q = map.get(key);
			if(q==null) q = new Quadruple();
			q.curProp = properties.get(key);
			q.curValue = terms.get(key);
			// remove operator
			if(q.curValue!=null) q.curValue = q.curValue.setOperator(null);
			map.put(key, q);
		}
		return this;

	}
	
	public NodeData concretize() {
		
		for(String key: map.keySet()) {
			Quadruple q = map.get(key);
			// replace inherited with defaults
			if(q.inhProp!=null && q.inhProp.equalsInherit()) {
				q.inhProp = css.getDefaultProperty(key);
				Term<?> value = css.getDefaultValue(key);
				if(value!=null) q.inhValue = value;
			}
			
			// replace current with inherited or defaults
			if(q.curProp!=null && q.curProp.equalsInherit()) {
				if(q.inhProp==null) q.curProp = css.getDefaultProperty(key);
				else q.curProp = q.inhProp;
				
				if(q.inhValue==null) q.curValue = css.getDefaultValue(key);
				else q.curValue = q.inhValue;
			}
			map.put(key, q);
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
		for(String key:nd.map.keySet()) {
			Quadruple qp = nd.map.get(key);
			Quadruple q = map.get(key);
			
			// create new quadruple if this do not contain one
			// for this property
			if(q==null) q = new Quadruple();
			
			if(qp.inhProp!=null && qp.inhProp.inherited()) {
				q.inhProp = qp.inhProp;
				q.inhValue = qp.inhValue;
			}
			
			if(qp.curProp!=null && qp.curProp.inherited()) {
				q.inhProp = qp.curProp;
				q.inhValue = qp.curValue;
			}
			// insert/replace only if contains inherited/original 
			// value			
			if(!q.isEmpty()) map.put(key, q);
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
	
	static class Quadruple {
		CSSProperty inhProp = null;
		CSSProperty curProp = null;
		Term<?> inhValue = null;
		Term<?> curValue = null;
		
		public Quadruple() {			
		}
		
		public boolean isEmpty() {
			return inhProp==null && curProp==null &&
			inhValue==null && curValue==null;
		}
	}

}



