package cz.vutbr.web.css;

import cz.vutbr.web.csskit.RuleArrayList;

/**
 * This interface simplifies the traversal of the CSS tree by providing the visitor pattern.
 * 
 * @author Nicasso
 * 
 */
public interface CSSNodeVisitor {
	
	public Object visit(StyleSheet node);
	
	public Object visit(RuleArrayList node);
	public Object visit(RuleFontFace node);
	public Object visit(RuleMargin node);
	public Object visit(RuleMedia node);
	public Object visit(RulePage node);
	public Object visit(RuleSet node);
	public Object visit(RuleViewport node);
	
	public Object visit(Declaration node);
	public Object visit(CombinedSelector node);
	
	public Object visit(Selector node);
	public Object visit(Selector.ElementAttribute node);
	public Object visit(Selector.ElementClass node);
	public Object visit(Selector.ElementDOM node);
	public Object visit(Selector.ElementID node);
	public Object visit(Selector.ElementName node);
	public Object visit(Selector.PseudoPage node);
	
	public Object visit(MediaExpression node);
	public Object visit(MediaQuery node);
	public Object visit(MediaSpec node);
	
	public Object visit(TermAngle node);
	public Object visit(TermColor node);
	public Object visit(TermExpression node);
	public Object visit(TermFloatValue node);
	public Object visit(TermLength node);
	public Object visit(TermFrequency node);
	public Object visit(TermFunction node);
	public Object visit(TermIdent node);
	public Object visit(TermInteger node);
	public Object visit(TermList node);
	public Object visit(TermNumber node);
	public Object visit(TermPercent node);
	public Object visit(TermResolution node);
	public Object visit(TermString node);
	public Object visit(TermTime node);
	public Object visit(TermURI node);

}
