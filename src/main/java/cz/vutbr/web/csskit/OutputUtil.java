package cz.vutbr.web.csskit;

import java.util.List;

import cz.vutbr.web.css.PrettyOutput;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermOperator;

/**
 * Helper class for generation output for given CSS rules
 * @author kapy
 *
 */
public class OutputUtil {

	public static final String EMPTY_DELIM = "";
	public static final String SPACE_DELIM = " ";
	public static final String DEPTH_DELIM = "\t";
    public static final String QUERY_DELIM = " AND ";
	public static final String RULE_OPENING = " {\n";
	public static final String RULE_CLOSING = "}\n";
	public static final String MEDIA_DELIM = ", ";
	public static final String SELECTOR_DELIM = ", ";
	public static final String IMPORT_KEYWORD = "@import ";
	public static final String URL_OPENING = "url('";
	public static final String URL_CLOSING = "')";
	public static final String LINE_CLOSING = ";\n";
	public static final String NEW_LINE = "\n";
	public static final String MEDIA_KEYWORD = "@media ";
	public static final String RULE_DELIM = "\n";
	public static final String CHARSET_KEYWORD = "@charset ";
	public static final String CHARSET_OPENING = "\"";
	public static final String CHARSET_CLOSING = "\";\n";
	public static final String PROPERTY_OPENING = ": ";
	public static final String PROPERTY_CLOSING = ";\n";
	public static final String IMPORTANT_KEYWORD = "!important";
	public static final String PAGE_KEYWORD = "@page";
	public static final String PSEUDO_OPENING = ":";
	public static final String PAGE_CLOSING = "";
    public static final String VIEWPORT_KEYWORD = "@viewport";
    public static final String FONT_FACE_KEYWORD = "@font-face";
	public static final String FUNCTION_OPENING = "(";
	public static final String FUNCTION_CLOSING = ")";
	public static final String STRING_OPENING = "'";
	public static final String STRING_CLOSING = "'";
	public static final String ATTRIBUTE_OPENING = "[";
	public static final String ATTRIBUTE_CLOSING = "]";
	public static final String PERCENT_SIGN = "%";
	public static final String HASH_SIGN = "#";
	public static final String MARGIN_AREA_OPENING = "@";
    public static final String MEDIA_EXPR_OPENING = "(";
    public static final String MEDIA_EXPR_CLOSING = ")";
    public static final String MEDIA_FEATURE_DELIM = ": ";
    public static final String CALC_KEYWORD = "calc";
    public static final String RECT_KEYWORD = "rect";
	
	
	
	
	/**
	 * Appends string multiple times to buffer
	 * @param sb StringBuilder to be modified
	 * @param append String to be added
	 * @param times Number of times <code>append</code> is added 
	 * @return Modified StringBuilder <code>sb</code> to allow chaining
	 */
	public static StringBuilder appendTimes(StringBuilder sb, String append, int times) {
		
		for(;times>0; times--)
			sb.append(append);
		
		return sb;
	}
	
	/**
	 * Appends all elements of <code>array</code> to buffer, separated by delimiter
	 * @param <T> Type of elements stored in <code>array</code>
	 * @param sb StringBuilder to be modified
	 * @param array Array of elements
	 * @param delimiter Delimiter to separate elements
	 * @return Modified <code>sb</code> to allow chaining
	 */
	public static <T> StringBuilder appendArray(StringBuilder sb, T[] array, String delimiter) {
		
		boolean firstRun = true;
		
		for(T elem: array) {
			if(!firstRun) 
				sb.append(delimiter);
			else 
				firstRun = false;
			
			sb.append(elem.toString());
		}
		
		return sb;
		
	}
	
	/**
	 * Appends all elements of <code>list</code> to buffer, separated by delimiter
	 * @param <T> Type of elements stored in <code>list</code>
	 * @param sb StringBuilder to be modified
	 * @param list List of elements
	 * @param delimiter Delimiter to separate elements
	 * @return Modified <code>sb</code> to allow chaining
	 */
	public static <T> StringBuilder appendList(StringBuilder sb, List<T> list, String delimiter) {
		
		boolean firstRun = true;
		
		for(T elem: list) {
			if(!firstRun) 
				sb.append(delimiter);
			else 
				firstRun = false;
			
			sb.append(elem.toString());
		}
		
		return sb;
		
	}
	
	/**
	 * Appends of elements of <code>list</code> to list, separater by delimiter.
	 * Uses depth parameter to make output nicer for each element
	 * @param <T> List of elements, which implements <code>Rule</code>
	 * @param sb StringBuilder to be modified
	 * @param list List of elements
	 * @param delimiter Delimeter between elements
	 * @param depth Depth of each element
	 * @return Modified <code>sb</code> to allow chaining
	 */
	public static <T extends PrettyOutput> StringBuilder appendList(
			StringBuilder sb, List<T> list, 
			String delimiter, int depth) {
		
		boolean firstRun = true;
		
		for(T elem: list) {
			if(!firstRun)
				sb.append(delimiter);
			else
				firstRun = false;
			
			sb.append(elem.toString(depth));
		}
		
		return sb;
	}
	
	/**
	 * Appends the calc() function arguments to a string builder.
	 * @param sb the string builder to be modified
	 * @param args the calc arguments
	 * @return Modified <code>sb</code> to allow chaining
	 */
	public static StringBuilder appendCalcArgs(StringBuilder sb, CalcArgs args) {
	    final String astr = args.evaluate(CalcArgs.stringEvaluator);
	    if (!astr.startsWith(FUNCTION_OPENING)) {
	        sb.append(FUNCTION_OPENING);
	        sb.append(astr);
	        sb.append(FUNCTION_CLOSING);
	    } else {
	        sb.append(astr);
	    }
	    return sb;
	}
	
    /**
     * Appends the formatted list of function arguments to a string builder. The individual
     * arguments are separated by spaces with the exception of commas.
     * @param sb the string builder to be modified
     * @param list the list of function arguments
     * @return Modified <code>sb</code> to allow chaining
     */
	public static StringBuilder appendFunctionArgs(StringBuilder sb, List<Term<?>> list) {

        boolean firstRun = true;

        for (Term<?> elem : list) {
            boolean sep = (elem instanceof TermOperator &&
                    ((TermOperator) elem).getValue() == ',');
            if (!firstRun && !sep)
                sb.append(SPACE_DELIM);
            firstRun = false;

            sb.append(elem.toString());
        }

        return sb;
    }
}
