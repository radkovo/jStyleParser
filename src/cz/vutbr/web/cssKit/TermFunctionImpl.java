package cz.vutbr.web.cssKit;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermNumber;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.css.TermString;
import cz.vutbr.web.css.TermUri;
import cz.vutbr.web.cssKit.parser.SimpleNode;
import java.util.ArrayList;

/**
 * TermFunction
 * @author Jan Svercl, VUT Brno, 2008
 */
public class TermFunctionImpl extends TermImpl implements TermFunction {

    private String functionName;
    private ArrayList<Term> termsList = new ArrayList<Term>();

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        if(functionName == null) {
            throw new NullPointerException();
        }    
        else {
            this.functionName = functionName;
        }
    }

    public ArrayList<Term> getTermsList() {
        return termsList;
    }
    
    public TermFunctionImpl(String functionName) {
        setFunctionName(functionName);
    }
    
    @Override
    public String toString() {
        String out = functionName + "(";
        for(Term t: termsList) {
            out += t.toString();
        }
        out += ")";
        return operator(out);
    }
    
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
                        Term.EnumOperator tmpOperator = null;
                        for(int i = 0; i < exprNode.jjtGetNumChildren(); i++) {
                            SimpleNode cNode = (SimpleNode)exprNode.jjtGetChild(i);

                            if(cNode.getType().equals("operator")) {
                                if(cNode.jjtGetNumChildren() == 0) {
                                    tmpOperator = Term.EnumOperator.space;
                                }
                                else if(((SimpleNode)cNode.jjtGetChild(0)).getType().equals("slash")) {
                                    tmpOperator = Term.EnumOperator.slash;
                                }
                                else if(((SimpleNode)cNode.jjtGetChild(0)).getType().equals("comma")) {
                                    tmpOperator = Term.EnumOperator.comma;
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
}
