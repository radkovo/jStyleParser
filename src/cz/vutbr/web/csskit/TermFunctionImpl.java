package cz.vutbr.web.csskit;

import java.util.Collections;
import java.util.List;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;

/**
 * TermFunction
 * @author Jan Svercl, VUT Brno, 2008
 * 			modified by Karel Piwko
 */
public class TermFunctionImpl extends TermImpl implements TermFunction {

	protected String functionName;
	protected List<Term> terms;
	
    public TermFunctionImpl(String functionName) {
    	this.terms = Collections.emptyList();
        setFunctionName(functionName);
    }
    
    
    /**
	 * @return the functionName
	 */
	public String getFunctionName() {
		return functionName;
	}



	/**
	 * @param functionName the functionName to set
	 */
	public void setFunctionName(String functionName) {
		if(functionName==null)
			throw new IllegalArgumentException("Invalid functionName in function (null)");
		
		functionName = functionName.replaceAll("\\($", "");
		this.functionName = functionName;
	}



	/**
	 * @return the terms
	 */
	public List<Term> getTerms() {
		return terms;
	}



	/**
	 * @param terms the terms to set
	 */
	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}



	@Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		// append operator
		if(operator!=null) sb.append(operator.value());
		
		sb.append(functionName).append(OutputUtil.FUNCTION_OPENING);
		sb = OutputUtil.appendList(sb, terms, OutputUtil.EMPTY_DELIM)
			.append(OutputUtil.FUNCTION_CLOSING);
		
		return sb.toString();
    }
    /*
    protected static TermFunction getFunctionByNode(SimpleNode term) {
        if(term != null) {
            if((term.jjtGetNumChildren() == 1)) {
                if(((SimpleNode)term.jjtGetChild(0)).getType().equals("function") && term.jjtGetChild(0).jjtGetNumChildren() == 2) {
                    String functionName = ((SimpleNode)term.jjtGetChild(0).jjtGetChild(0)).getImage();
                    if(functionName != null) {
                        functionName = functionName.replaceAll("\\($", "");
                        TermFunction termFunction;
                        
                        termFunction = new TermFunctionImpl(functionName);
                        SimpleNode exprNode = ((SimpleNode)term.jjtGetChild(0).jjtGetChild(1));
                        Operator tmpOperator = null;
                        for(int i = 0; i < exprNode.jjtGetNumChildren(); i++) {
                            SimpleNode cNode = (SimpleNode)exprNode.jjtGetChild(i);

                            if(cNode.getType().equals("operator")) {
                                if(cNode.jjtGetNumChildren() == 0) {
                                    tmpOperator = Operator.SPACE;
                                }
                                else if(((SimpleNode)cNode.jjtGetChild(0)).getType().equals("slash")) {
                                    tmpOperator = Operator.SLASH;
                                }
                                else if(((SimpleNode)cNode.jjtGetChild(0)).getType().equals("comma")) {
                                    tmpOperator = Operator.COMMA;
                                }

                            }
                            else {
                                TermColor color = TermColorImpl.getColorByNode(cNode);
                                if(color != null) {
                                    color.setOperator(tmpOperator);
                                    termFunction.getTermsList().add(color);
                                    continue;
                                }
                                TermNumber number = TermNumberImpl.getNumberByNode(cNode);
                                if(number != null) {
                                    number.setOperator(tmpOperator);
                                    termFunction.getTermsList().add(number);
                                    continue;
                                }
                                TermPercent percent = TermPercentImpl.getPercentByNode(cNode);
                                if(percent != null) {
                                    percent.setOperator(tmpOperator);
                                    termFunction.getTermsList().add(percent);
                                    continue;
                                }
                                TermUri uri = TermUriImpl.getUriByNode(cNode);
                                if(uri != null) {
                                    uri.setOperator(tmpOperator);
                                    termFunction.getTermsList().add(uri);
                                    continue;
                                }
                                TermString value = TermStringImpl.getStringByNode(cNode);
                                if(value != null) {
                                    value.setOperator(tmpOperator);
                                    termFunction.getTermsList().add(value);
                                    continue;
                                }
                                TermIdent ident = TermIdentImpl.getIdentByNode(cNode);
                                if(ident != null) {
                                    ident.setOperator(tmpOperator);
                                    termFunction.getTermsList().add(ident);
                                    continue;
                                }
                                TermFunction function = TermFunctionImpl.getFunctionByNode(cNode);
                                if(function != null) {
                                    function.setOperator(tmpOperator);
                                    termFunction.getTermsList().add(function);
                                    continue;
                                }
                            }
                        }
                        return termFunction;
                    }
                }
            }
        }
        return null;
    }
    */
}
