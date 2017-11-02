package cz.vutbr.web.css;

import java.net.URL;
import java.util.List;

import cz.vutbr.web.css.TermNumeric.Unit;

public interface TermFactory {

	TermAngle createAngle(Float value);
	TermAngle createAngle(String value, Unit unit, int unary);
	
	TermCalc createCalc(List<Term<?>> args);
	
	TermColor createColor(TermIdent ident);
	TermColor createColor(String hash);
	TermColor createColor(int r, int g, int b);
    TermColor createColor(int r, int g, int b, int a);
	TermColor createColor(TermFunction function);
	
	TermFrequency createFrequency(Float value);
	TermFrequency createFrequency(String value, Unit unit, int unary);
	
    TermExpression createExpression(String expr);
	TermFunction createFunction();
	
	TermIdent createIdent(String value);
    TermIdent createIdent(String value, boolean dash);
	
	TermInteger createInteger(Integer value);
	TermInteger createInteger(String value, int unary);
	
	TermLength createLength(Float value);
    TermLength createLength(Float value, Unit unit);
	TermLength createLength(String value, Unit unit, int unary);
	
	TermList createList();
	TermList createList(int initialSize);
	
	TermNumber createNumber(Float value);
	TermNumber createNumber(String value, int unary);
	
	TermNumeric<?> createNumeric(String value, int unary);
	
	TermNumeric<Float> createDimension(String value, int unary);
	
	<K,V> TermPair<K,V> createPair(K key, V value);
	
	TermPercent createPercent(Float value);
	TermPercent createPercent(String value, int unary);
	
	TermRect createRect(TermFunction function);
	/**
	 * Creates a rectangle from four lengths. Use {@code null} for {@code auto} values.
	 */
	TermRect createRect(TermLength a, TermLength b, TermLength c, TermLength d);
	
    TermResolution createResolution(Float value);
    TermResolution createResolution(String value, Unit unit, int unary);
    
	TermString createString(String value);
	
	<V> Term<V> createTerm(V value);
	
	TermTime createTime(Float value);
	TermTime createTime(String value, Unit unit, int unary);
	
    TermUnicodeRange createUnicodeRange(String value);
	
	TermURI createURI(String value);
    TermURI createURI(String value, URL base);
    
    TermOperator createOperator(char value);
}
