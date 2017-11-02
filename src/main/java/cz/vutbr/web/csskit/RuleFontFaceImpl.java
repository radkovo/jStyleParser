/**
 * RuleFontFaceImpl.java
 *
 * Created on 1.2.2013, 14:28:51 by burgetr
 */
package cz.vutbr.web.csskit;

import java.util.ArrayList;
import java.util.List;

import cz.vutbr.web.css.CSSProperty.FontStyle;
import cz.vutbr.web.css.CSSProperty.FontWeight;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleFontFace;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.Term.Operator;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermString;
import cz.vutbr.web.css.TermURI;

/**
 * Wrap of declarations bound with a font specification
 *  
 * @author burgetr
 */
public class RuleFontFaceImpl extends AbstractRuleBlock<Declaration> implements RuleFontFace 
{
	private static final String PROPERTY_FONT_FAMILY_NAME = "font-family";
	private static final String PROPERTY_SOURCE = "src";
	private static final String PROPERTY_FONT_STYLE = "font-style";
	private static final String PROPERTY_FONT_WEIGHT = "font-weight";
	
    protected RuleFontFaceImpl() 
    {
        super();
    }
    
    @Override
	public String getFontFamily() 
    {
    	return getStringValue(PROPERTY_FONT_FAMILY_NAME);
	}

	@Override
	public List<RuleFontFace.Source> getSources() 
	{
	    Declaration decl = getDeclaration(PROPERTY_SOURCE);
	    if (decl != null)
	    {
    	    List<RuleFontFace.Source> ret = new ArrayList<>(decl.size());
    	    boolean invalid = false;
    	    
    	    for (int i = 0; i < decl.size() && !invalid; i++)
    	    {
    	        Term<?> val = decl.get(i);
    	        if (val instanceof TermURI)
    	        {
    	            final TermURI uri = (TermURI) val;
    	            final String format = (i + 1 < decl.size()) ? checkForFormat(decl.get(i + 1)) : null;
    	            if (format != null)
    	                i++; //skip correct format definition
                    final RuleFontFace.SourceURL src = new RuleFontFace.SourceURL() {
                        @Override
                        public TermURI getURI() {
                            return uri;
                        }
                        @Override
                        public String getFormat() {
                            return format;
                        }
                    };
                    ret.add(src);
    	        }
    	        else if (val instanceof TermFunction)
    	        {
    	            final TermFunction fn = (TermFunction) val;
    	            if (fn.getFunctionName().equalsIgnoreCase("local") && fn.size() == 1 && fn.get(0) instanceof TermString)
    	            {
    	                final String fontname = ((TermString) fn.get(0)).getValue();
    	                final RuleFontFace.SourceLocal src = new RuleFontFace.SourceLocal() {
                            @Override
                            public String getName() {
                                return fontname;
                            }
                        };
                        ret.add(src);
    	            }
    	            else
    	                invalid = true;
    	        }
    	        else
    	            invalid = true;
    	        
    	        if (i + 1 < decl.size() && decl.get(i + 1).getOperator() != Operator.COMMA)
    	            invalid = true; //some additional (invalid) terms found
    	    }
	    
    	    if (!invalid)
    	        return ret;
    	    else
    	        return null;
	    }
	    else
	        return null;
	}

	private String checkForFormat(Term<?> term)
	{
	    if (term instanceof TermFunction && term.getOperator() == Operator.SPACE)
	    {
	        final TermFunction fn = (TermFunction) term;
	        if (fn.getFunctionName().equalsIgnoreCase("format") && fn.size() == 1 && fn.get(0) instanceof TermString)
	        {
	            return ((TermString) fn.get(0)).getValue();
	        }
	        else
	            return null;
	    }
	    else
	        return null;
	}
	
	@Override
	public FontStyle getFontStyle() 
	{
		String strValue = getStringValue(PROPERTY_FONT_STYLE);
		if (strValue == null) {
			return null;
		}
		
		try {
			return FontStyle.valueOf(strValue.toUpperCase());
		} catch (IllegalArgumentException e){
			return null;
		}
	}

	@Override
	public FontWeight getFontWeight() 
	{
		String strValue = getStringValue(PROPERTY_FONT_WEIGHT);
		if (strValue == null) {
			return null;
		}
		
		try {
			return FontWeight.valueOf(strValue.toUpperCase());
		} catch (IllegalArgumentException e){
			return null;
		}
	}
	
    @Override 
    public String toString() 
    {
        return this.toString(0);
    }
    
    public String toString(int depth) 
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append(OutputUtil.FONT_FACE_KEYWORD).append(OutputUtil.SPACE_DELIM);
        
        // append declarations
        sb.append(OutputUtil.RULE_OPENING);
        sb = OutputUtil.appendList(sb, list, OutputUtil.EMPTY_DELIM, depth + 1);
        sb.append(OutputUtil.RULE_CLOSING);
    
        return sb.toString();
    }
    
    private String getStringValue(String propertyName) 
    {
        Declaration decl = getDeclaration(propertyName);
    	if (decl == null) {
    		return null;
    	}
    	
    	Term<?> term= decl.get(0);
    	if (term == null) {
    		return null;
    	}
    	
    	Object value = term.getValue();
    	if (!(value instanceof String)) {
    		return null;
    	}
    	
    	return (String)value;
    }
    
    private Declaration getDeclaration(String property) 
    {
    	for (Declaration decl : list) {
			if (property.equals(decl.getProperty())) {
				return decl;
			}
		}
    	
    	return null;
    }
}
