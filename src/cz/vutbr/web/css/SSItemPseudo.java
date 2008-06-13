package cz.vutbr.web.css;

/**
 * SSItemPseudo
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface SSItemPseudo extends SSItem {

    public String getValue();

    public void setValue(String value);

    public String getFuncName();

    public void setFuncName(String funcName);
    
}
