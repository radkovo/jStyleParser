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
public class SingleMapNodeData implements NodeData, Cloneable {

	private static final int COMMON_DECLARATION_SIZE = 7;
	
	private final DeclarationTransformer transformer;
	private final SupportedCSS css;
	
	protected Map<String, Quadruple> map;
	
	public SingleMapNodeData() {
		this(CSSFactory.getDeclarationTransformer(), CSSFactory.getSupportedCSS());
	}

	public SingleMapNodeData(DeclarationTransformer transformer, SupportedCSS css) {
		this.transformer = transformer;
		this.css = css;
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
            if(q.curProp!=null) return null;
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
		
		for(String key: map.keySet()) {
			Quadruple q = map.get(key);
			
			// replace current with inherited or defaults
			if(q.curProp!=null && q.curProp.equalsInherit()) {
				if(q.inhProp==null) q.curProp = css.getDefaultProperty(key);
				else {
				    q.curProp = q.inhProp;
				    q.curSource = q.inhSource;
				}
				
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
			
			boolean forceInherit = (q.curProp != null && q.curProp.equalsInherit());
			
			//try the inherited value of the parent
			if(qp.inhProp!=null && (qp.inhProp.inherited() || forceInherit)) {
				q.inhProp = qp.inhProp;
				q.inhValue = qp.inhValue;
				q.inhSource = qp.inhSource;
			}
			
			//try the declared property of the parent
			if(qp.curProp!=null && (qp.curProp.inherited() || forceInherit)) {
				q.inhProp = qp.curProp;
				q.inhValue = qp.curValue;
                q.inhSource = qp.curSource;
			}
			// insert/replace only if contains inherited/original 
			// value			
			if(!q.isEmpty())
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
			Term<?> value = q.curValue;
			if(prop==null) {
				prop = q.inhProp;
				if (value==null) value = q.inhValue; }
			
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

	@Override
	public Object clone() {
		SingleMapNodeData clone; {
			try {
				clone = (SingleMapNodeData)super.clone();
			} catch (CloneNotSupportedException e) {
				throw new InternalError("coding error");
			}
		}
		clone.map = new HashMap<String,Quadruple>(css.getTotalProperties(), 1.0f);
		for (String key : map.keySet())
			clone.map.put(key, (Quadruple)map.get(key).clone());
		return clone;
	}
	
	static class Quadruple implements Cloneable {
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
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((curProp == null) ? 0 : curProp.hashCode());
			result = prime * result
					+ ((curValue == null) ? 0 : curValue.hashCode());
			result = prime * result
					+ ((inhProp == null) ? 0 : inhProp.hashCode());
			result = prime * result
					+ ((inhValue == null) ? 0 : inhValue.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Quadruple other = (Quadruple) obj;
			if (curProp == null) {
				if (other.curProp != null)
					return false;
			} else if (!curProp.equals(other.curProp))
				return false;
			if (curValue == null) {
				if (other.curValue != null)
					return false;
			} else if (!curValue.equals(other.curValue))
				return false;
			if (inhProp == null) {
				if (other.inhProp != null)
					return false;
			} else if (!inhProp.equals(other.inhProp))
				return false;
			if (inhValue == null) {
				if (other.inhValue != null)
					return false;
			} else if (!inhValue.equals(other.inhValue))
				return false;
			return true;
		}
		
		@Override
		public Object clone() {
			Quadruple clone; {
				try {
					 clone = (Quadruple)super.clone();
				} catch (CloneNotSupportedException e) {
					throw new InternalError("coding error");
				}
			}
			if (inhValue != null)
				clone.inhValue = (Term<?>)inhValue.clone();
			if (curValue != null)
				clone.curValue = (Term<?>)curValue.clone();
			if (inhSource != null)
				clone.inhSource = (Declaration)inhSource.clone();
			if (curSource != null)
				clone.curSource = (Declaration)curSource.clone();
			return clone;
		}
	}

}



