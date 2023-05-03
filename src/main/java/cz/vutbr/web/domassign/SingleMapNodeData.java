package cz.vutbr.web.domassign;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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
		this.map = new HashMap<String, Quadruple>(css != null ? css.getTotalProperties() : 16, 1.0f);
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
		return q.getProperty(includeInherited);
	}

    public Term<?> getValue(String name, boolean includeInherited) {
        
        Quadruple q = map.get(name);
        if (q==null) return null;
        else return q.getValue(includeInherited);
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
			if(q==null) q = new Quadruple(css, key);
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
		return concretize(true, false);
	}
	
	public NodeData concretize(boolean concretizeInherit, boolean concretizeInitial) {
		if (concretizeInherit || concretizeInitial) {
			Iterator<Map.Entry<String,Quadruple>> entries = map.entrySet().iterator();
			while (entries.hasNext()) {
				Quadruple q = entries.next().getValue();
				q.concretize(concretizeInherit, concretizeInitial);
				if (q.isEmpty())
					// default unknown
					entries.remove();
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
		for(String key:nd.map.keySet()) {
			Quadruple qp = nd.map.get(key);
			Quadruple q = map.get(key);
			
			// create new quadruple if this do not contain one
			// for this property
			if(q==null) q = new Quadruple(qp.getDefault());
			
			q.inheritFrom(qp);
			
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
            return q.getSourceDeclaration(includeInherited);
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
		clone.map = new HashMap<String,Quadruple>(css != null ? css.getTotalProperties() : 16, 1.0f);
		for (String key : map.keySet())
			clone.map.put(key, (Quadruple)map.get(key).clone());
		return clone;
	}
	
	public static class Quadruple implements Cloneable {
		protected CSSProperty inhProp = null;
		protected CSSProperty curProp = null;
		protected Term<?> inhValue = null;
		protected Term<?> curValue = null;
		protected Declaration inhSource = null;
        protected Declaration curSource = null;
		private Quadruple defaultValue = null;
		private final SupportedCSS css;
		private final String key;
		
		public Quadruple() {
			this.css = null;
			this.key = null;
		}
		
		public Quadruple(SupportedCSS css, String key) {
			if (css == null || key == null)
				throw new IllegalArgumentException();
			this.css = css;
			this.key = key;
		}
		
		/**
		 * @param defaultValue assumed to be immutable
		 */
		public Quadruple(Quadruple defaultValue) {
			if (defaultValue == null)
				throw new IllegalArgumentException();
			this.defaultValue = defaultValue;
			// these variables will not be used
			this.css = null;
			this.key = null;
		}
		
		public <T extends CSSProperty> T getProperty(boolean includeInherited) {
			CSSProperty prop; {
				if (curProp != null)
					prop = curProp;
				else if (includeInherited)
					prop = inhProp;
				else
					prop = null;
			}
			// this will cast to inferred type
			// if there is no inferred type, cast to CSSProperty is safe
			// otherwise the possibility having wrong left side of assignment
			// is roughly the same as use wrong dynamic class cast
			@SuppressWarnings("unchecked")
			T retval = (T)prop;
			return retval;
		}
		
		public Term<?> getValue(boolean includeInherited) {
			if (curValue != null)
				return curValue;
			else if (curProp != null)
				return null;
			else if (includeInherited)
				return inhValue;
			else
				return null;
		}
		
		public Declaration getSourceDeclaration(boolean includeInherited) {
			if (curSource != null)
				return curSource;
			else if (includeInherited)
				return inhSource;
			else
				return null;
		}
		
		public void concretize() {
			concretize(true, false);
		}
		
		public void concretize(boolean concretizeInherit, boolean concretizeInitial) {
			if (curProp != null) {
				if (concretizeInherit && curProp.equalsInherit()) {
					// replace current with inherited or defaults
					if (inhProp != null) {
						curProp = inhProp;
						curValue = inhValue;
						curSource = inhSource;
					} else if (defaultValue != null) {
						curProp = defaultValue.curProp;
						curValue = defaultValue.curValue;
					} else if (css != null) {
						curProp = css.getDefaultProperty(key);
						curValue = css.getDefaultValue(key);
						defaultValue = this;
					} else {
						// default not known
						curProp = null;
						defaultValue = this;
					}
				} else if (concretizeInitial && curProp.equalsInitial()) {
					// replace current with defaults
					if (css != null) {
						curProp = css.getDefaultProperty(key);
						curValue = css.getDefaultValue(key);
						defaultValue = this;
					} else {
						// default not known
						curProp = null;
						defaultValue = this;
					}
				}
			}
		}
		
		public void inheritFrom(Quadruple parent) {
			boolean forceInherit = curProp != null && curProp.equalsInherit();
			// try the inherited value of the parent
			if (parent.inhProp != null && (parent.inhProp.inherited() || forceInherit)) {
				inhProp = parent.inhProp;
				inhValue = parent.inhValue;
				inhSource = parent.inhSource;
			}
			// try the declared property of the parent
			if (parent.curProp != null && (parent.curProp.inherited() || forceInherit)) {
				inhProp = parent.curProp;
				inhValue = parent.curValue;
				inhSource = parent.curSource;
			}
		}
		
		public Quadruple getDefault() {
			if (defaultValue == null) {
				if (css != null) {
					defaultValue = new Quadruple(css, key);
					defaultValue.curProp = css.getDefaultProperty(key);
					defaultValue.curValue = css.getDefaultValue(key);
				} else {
					defaultValue = new Quadruple();
				}
				defaultValue.curSource = null;
				defaultValue.defaultValue = defaultValue;
			}
			return defaultValue;
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



