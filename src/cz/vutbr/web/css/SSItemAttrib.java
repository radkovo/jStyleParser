package cz.vutbr.web.css;

/**
 * SSItemAttrib
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface SSItemAttrib extends SSItem {
    
    public enum EnumOperator { equals, includes, dashmatch }
    public enum EnumValueType { ident, string }

    public String getAttrib();

    public void setAttrib(String attrib);

    public EnumOperator getOperator();
    
    public String getValue();

    public EnumValueType getValueType();
    
    public void setValue(EnumOperator operator, String value, EnumValueType valueType);
    
}
