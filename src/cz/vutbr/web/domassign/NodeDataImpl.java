package cz.vutbr.web.domassign;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.Term;

public class NodeDataImpl implements NodeData {

	private static final int COMMON_DECLARATION_SIZE = 7;
	
	protected Map<String, CSSProperty> properties;	
	protected Map<String, Term<?>> values;
	protected Map<String, List<Term<?>>> listValues;
	
	protected DeclarationTransformer transformer;
	
	public NodeDataImpl() {
		this.properties = new HashMap<String, CSSProperty>();
		this.values = new HashMap<String, Term<?>>();
		this.listValues = new HashMap<String, List<Term<?>>>();
		this.transformer = DeclarationTransformer.getInstance();
	}
	
	public <T extends CSSProperty> T getProperty(Class<T> clazz, String name) {
		return clazz.cast(properties.get(name));
	}
	
	public <T extends Term<?>> T getValue(Class<T> clazz, String name) {
		return clazz.cast(values.get(name));
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Term<?>> List<T> getValues(Class<T> clazz, String name) {
		return (List<T>) listValues.get(name);
	}
	
	public void push(Declaration d, int inheritanceLevel, boolean inherit) {
		
		Map<String,CSSProperty> properties = 
			new HashMap<String,CSSProperty>(COMMON_DECLARATION_SIZE);
		Map<String,Term<?>> terms = 
			new HashMap<String, Term<?>>(COMMON_DECLARATION_SIZE);
		Map<String,List<Term<?>>> listTerms =
			new HashMap<String, List<Term<?>>>(COMMON_DECLARATION_SIZE);
		
		boolean result = transformer
			.parseDeclaration(d, properties, terms, listTerms);
		
		// in case of false do not insert anything
		if(!result) return;
		
		this.properties.putAll(properties);
		this.values.putAll(terms);
		this.listValues.putAll(listTerms);
		
	}
	
	/*
	
    
    */
    
    /**
     * Funkce začne transakci (klokuje obsah tohoto objektu). Na rozdíl od DB 
     * systémů jsou operace automaticky potvrzovány (commit), ale je možné se 
     * kdykoli vrátit do výchozího stavu (rollback)
     * @return kopie dat sloužící pro případný rollback
     */
    /*
    protected NodeData beginTransaction() {
        try {
            return (NodeData)this.clone();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    */
    /**
     * Návrat objektu do výchozího stavu před započetím transakce
     * @param data kopie dat objektu
     */
    /*
    protected void rollbackTransaction(NodeData data) {
        for(Field f : this.getClass().getDeclaredFields()) {
            try {
                f.set(this, f.get(data));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    */
    /**
     * Funkce pro klonování objektu
     * @return klonovaný objekt
     */
    /*
    @Override
    public Object clone() {
        try {
            return super.clone();
        }
        catch ( CloneNotSupportedException e ) {
            return null;
        }
    }
    */
    /**
     * Výpis objektu v textové formě.
     * @param depth určuje zanoření v hierarchii, slouží k odsazení jednotlivých řádků
     * @return txtová reprezentace objektu
     */
    /*
    public String toString(int depth) {
        String s = "";
        for(Field f : this.getClass().getDeclaredFields()) {
            try {
                Object o = f.get(this);
                if(o != null) {
                    if(!f.getName().equals("cache")) {
                        String value = o.toString().replace("prefix_", "").replace("_", "-");
                        if(o instanceof Term) {
                            value = value.replaceAll("^( |/|, )", "");
                        }
                        if(!((o instanceof Map && ((Map)o).isEmpty()) || (o instanceof List && ((List)o).isEmpty()))) {
                            for(int i = 0; i < depth; i++) {
                               s += "    ";
                            }
                            s += f.getName() + ": " + value + "\n";
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return s;
    }
    */
    /**
     * Výpis objektu v HTML formátu. Je o něco přehlednější než textová forma, 
     * nicméně potřebuje pro zobrazení využít webový prohlížeč
     * @return HTML reprezentace objektu
     */
    /*
    public String toHTMLString() {
        String s = "";
        for(Field f : this.getClass().getDeclaredFields()) {
            try {
                Object o = f.get(this);
                if(o != null) {
                    if(!f.getName().equals("cache")) {
                        if(f.getName().equals("unsupported") || f.getName().equals("ignored") || f.getName().equals("passed")) {
                            s += "<b><span style=\"color: #F00;\">" + f.getName() + "</span></b>: " + o.toString() + "<br />\n";
                        }
                        else {
                            String value = o.toString().replace("prefix_", "").replace("_", "-");
                            if(o instanceof Term) {
                                value = value.replaceAll("^( |/|, )", "");
                            }
                            if(!((o instanceof Map && ((Map)o).isEmpty()) || (o instanceof List && ((List)o).isEmpty()))) {
                                s += "<b>" + f.getName() + "</b>: " + value + "<br />\n";
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return s;
    }
    
    public String toInlineStyle() {
        StringBuffer out = new StringBuffer();
        if(backgroundAttachmentType != null) {
            out.append("background-attachment: ");
            out.append(backgroundAttachmentType);
            out.append("; ");
        }
        if(backgroundColorType != null) {
            out.append("background-color: ");
            if(backgroundColorType == EnumColorTransparent.inherit || backgroundColorType == EnumColorTransparent.transparent) {
                out.append(backgroundColorType);
            }
            else {
                out.append(backgroundColorValue.toString().trim());
            }
            out.append("; ");
        }
        if(backgroundImageType != null) {
            out.append("background-image: ");
            if(backgroundImageType == EnumBackgroundImage.inherit || backgroundImageType == EnumBackgroundImage.none) {
                out.append(backgroundImageType);
            }
            else {
                out.append(backgroundImageValue.toString());
            }
            out.append("; ");
        }
        if(backgroundPositionType != null) {
            out.append("background-position: ");
            if(backgroundPositionType == EnumBackgroundPosition.inherit) {
                out.append(backgroundPositionType);
            }
            else {
                if(backgroundPositionHorType == EnumBackgroundPositionHor.center || backgroundPositionHorType == EnumBackgroundPositionHor.left || backgroundPositionHorType == EnumBackgroundPositionHor.right) {
                   out.append(backgroundPositionHorType);
                }
                else if(backgroundPositionHorType == EnumBackgroundPositionHor.length) {
                   out.append(backgroundPositionHorNumberValue.toString().trim());
                }
                else if(backgroundPositionHorType == EnumBackgroundPositionHor.percentage) {
                   out.append(backgroundPositionHorPercentValue.toString().trim());
                }
                out.append(" ");
                if(backgroundPositionVerType == EnumBackgroundPositionVer.center || backgroundPositionVerType == EnumBackgroundPositionVer.top || backgroundPositionVerType == EnumBackgroundPositionVer.bottom) {
                   out.append(backgroundPositionVerType);
                }
                else if(backgroundPositionVerType == EnumBackgroundPositionVer.length) {
                   out.append(backgroundPositionVerNumberValue.toString().trim());
                }
                else if(backgroundPositionVerType == EnumBackgroundPositionVer.percentage) {
                   out.append(backgroundPositionVerPercentValue.toString().trim());
                }
            }
            out.append("; ");
        }
        if(backgroundRepeatType != null) {
            out.append("background-repeat: ");
            out.append(backgroundRepeatType.toString().replace("_", "-"));
            out.append("; ");
        }
        
        if(borderCollapseType != null) {
            out.append("border-collapse: ");
            out.append(borderCollapseType);
            out.append("; ");
        }
        if(borderColorTopType != null) {
            out.append("border-top-color: ");
            if(borderColorTopType == EnumColorTransparent.inherit || borderColorTopType == EnumColorTransparent.transparent) {
                out.append(borderColorTopType);
            }
            else {
                out.append(borderColorTopValue.toString().trim());
            }
            out.append("; ");
        }
        if(borderColorRightType != null) {
            out.append("border-right-color: ");
            if(borderColorRightType == EnumColorTransparent.inherit || borderColorRightType == EnumColorTransparent.transparent) {
                out.append(borderColorRightType);
            }
            else {
                out.append(borderColorRightValue.toString().trim());
            }
            out.append("; ");
        }
        if(borderColorBottomType != null) {
            out.append("border-bottom-color: ");
            if(borderColorBottomType == EnumColorTransparent.inherit || borderColorBottomType == EnumColorTransparent.transparent) {
                out.append(borderColorBottomType);
            }
            else {
                out.append(borderColorBottomValue.toString().trim());
            }
            out.append("; ");
        }
        if(borderColorLeftType != null) {
            out.append("border-left-color: ");
            if(borderColorLeftType == EnumColorTransparent.inherit || borderColorLeftType == EnumColorTransparent.transparent) {
                out.append(borderColorLeftType);
            }
            else {
                out.append(borderColorLeftValue.toString().trim());
            }
            out.append("; ");
        }
        if(borderSpacingType != null) {
            out.append("border-spacing: ");
            if(borderSpacingType == EnumBorderSpacing.inherit) {
                out.append(borderSpacingType);
            }
            else {
                out.append(borderSpacingHorValue.toString().trim());
                out.append(" ");
                out.append(borderSpacingVerValue.toString().trim());
            }
            out.append("; ");
        }
        if(borderStyleTopType != null) {
            out.append("border-top-style: ");
            out.append(borderStyleTopType.toString().replace("prefix_", ""));
            out.append("; ");
        }
        if(borderStyleRightType != null) {
            out.append("border-right-style: ");
            out.append(borderStyleRightType.toString().replace("prefix_", ""));
            out.append("; ");
        }
        if(borderStyleBottomType != null) {
            out.append("border-bottom-style: ");
            out.append(borderStyleBottomType.toString().replace("prefix_", ""));
            out.append("; ");
        }
        if(borderStyleLeftType != null) {
            out.append("border-left-style: ");
            out.append(borderStyleLeftType.toString().replace("prefix_", ""));
            out.append("; ");
        }
        if(borderWidthTopType != null) {
            out.append("border-top-width: ");
            if(borderWidthTopType == EnumBorderWidth.inherit || borderWidthTopType == EnumBorderWidth.medium || borderWidthTopType == EnumBorderWidth.thick || borderWidthTopType == EnumBorderWidth.thin) {
                out.append(borderWidthTopType);
            }
            else {
                out.append(borderWidthTopValue.toString().trim());
            }
            out.append("; ");
        }
        if(borderWidthRightType != null) {
            out.append("border-right-width: ");
            if(borderWidthRightType == EnumBorderWidth.inherit || borderWidthRightType == EnumBorderWidth.medium || borderWidthRightType == EnumBorderWidth.thick || borderWidthRightType == EnumBorderWidth.thin) {
                out.append(borderWidthRightType);
            }
            else {
                out.append(borderWidthRightValue.toString().trim());
            }
            out.append("; ");
        }
        if(borderWidthBottomType != null) {
            out.append("border-bottom-width: ");
            if(borderWidthBottomType == EnumBorderWidth.inherit || borderWidthBottomType == EnumBorderWidth.medium || borderWidthBottomType == EnumBorderWidth.thick || borderWidthBottomType == EnumBorderWidth.thin) {
                out.append(borderWidthBottomType);
            }
            else {
                out.append(borderWidthBottomValue.toString().trim());
            }
            out.append("; ");
        }
        if(borderWidthLeftType != null) {
            out.append("border-left-width: ");
            if(borderWidthLeftType == EnumBorderWidth.inherit || borderWidthLeftType == EnumBorderWidth.medium || borderWidthLeftType == EnumBorderWidth.thick || borderWidthLeftType == EnumBorderWidth.thin) {
                out.append(borderWidthLeftType);
            }
            else {
                out.append(borderWidthLeftValue.toString().trim());
            }
            out.append("; ");
        }
        
        if(fontStyleType != null) {
            out.append("font-style: ");
            out.append(fontStyleType);
            out.append("; ");
        }
        if(fontFamilyType != null) {
            out.append("font-family: ");
            if(fontFamilyType == EnumFontFamily.inherit) {
                out.append(fontFamilyType);
            }
            else {
                boolean first = true;
                for(String s : fontFamilyValues) {
                    if(first) {
                        first = false;
                    }
                    else {
                        out.append(", ");
                    }
                    out.append("'"+s.trim()+"'");
                }
            }
            out.append("; ");
        }
        if(fontSizeType != null) {
            out.append("font-size: ");
            if(fontSizeType == EnumFontSize.length) {
                out.append(fontSizeNumberValue.toString().trim());
            }
            else if(fontSizeType == EnumFontSize.percentage) {
                out.append(fontSizePercentValue.toString().trim());
            }
            else {
                out.append(fontSizeType.toString().replace("_", "-"));
            }
            out.append("; ");
        }
        if(fontVariantType != null) {
            out.append("font-variant: ");
            out.append(fontVariantType);
            out.append("; ");
        }
        if(fontWeightType != null) {
            out.append("font-weight: ");
            out.append(fontWeightType.toString().replace("prefix_", ""));
            out.append("; ");
        }
        if(lineHeightType != null) {
            out.append("line-height: ");
            if(lineHeightType == EnumLineHeight.length) {
                out.append(lineHeightNumberValue.toString().trim());
            }
            else if(lineHeightType == EnumLineHeight.percentage) {
                out.append(lineHeightPercentValue.toString().trim());
            }
            else {
                out.append(lineHeightType);
            }
            out.append("; ");
        }
        
        if(topType != null) {
            out.append("top: ");
            if(topType == EnumSize.length) {
                out.append(topNumberValue.toString().trim());
            }
            else if(topType == EnumSize.percentage) {
                out.append(topPercentValue.toString().trim());
            }
            else {
                out.append(topType);
            }
            out.append("; ");
        }
        if(rightType != null) {
            out.append("right: ");
            if(rightType == EnumSize.length) {
                out.append(rightNumberValue.toString().trim());
            }
            else if(rightType == EnumSize.percentage) {
                out.append(rightPercentValue.toString().trim());
            }
            else {
                out.append(rightType);
            }
            out.append("; ");
        }
        if(bottomType != null) {
            out.append("bottom: ");
            if(bottomType == EnumSize.length) {
                out.append(bottomNumberValue.toString().trim());
            }
            else if(bottomType == EnumSize.percentage) {
                out.append(bottomPercentValue.toString().trim());
            }
            else {
                out.append(bottomType);
            }
            out.append("; ");
        }
        if(leftType != null) {
            out.append("left: ");
            if(leftType == EnumSize.length) {
                out.append(leftNumberValue.toString().trim());
            }
            else if(leftType == EnumSize.percentage) {
                out.append(leftPercentValue.toString().trim());
            }
            else {
                out.append(leftType);
            }
            out.append("; ");
        }
        if(widthType != null) {
            out.append("width: ");
            if(widthType == EnumSize.length) {
                out.append(widthNumberValue.toString().trim());
            }
            else if(widthType == EnumSize.percentage) {
                out.append(widthPercentValue.toString().trim());
            }
            else {
                out.append(widthType);
            }
            out.append("; ");
        }
        if(heightType != null) {
            out.append("height: ");
            if(heightType == EnumSize.length) {
                out.append(heightNumberValue.toString().trim());
            }
            else if(heightType == EnumSize.percentage) {
                out.append(heightPercentValue.toString().trim());
            }
            else {
                out.append(heightType);
            }
            out.append("; ");
        }

        if(captionSideType != null) {
            out.append("caption-side: ");
            out.append(captionSideType);
            out.append("; ");
        }
        if(contentType != null) {
            out.append("content: ");
            if(contentType == EnumContent.inherit || contentType == EnumContent.none || contentType == EnumContent.normal) {
                out.append(quotesType);
            }
            else {
                boolean first = true;
                for(Term t : contentValues) {
                    if(first) {
                        first = false;
                    }
                    else {
                        out.append(" ");
                    }
                    out.append(t.toString().trim());
                }
            }
            out.append("; ");
        }
        if(counterIncrementType != null) {
            out.append("counter-increment: ");
            if(counterIncrementType == EnumCounter.inherit || counterIncrementType == EnumCounter.none) {
                out.append(counterIncrementType);
            }
            else {
                boolean first = true;
                for(String s : counterIncrementValues.keySet()) {
                    if(first) {
                        first = false;
                    }
                    else {
                        out.append(" ");
                    }
                    out.append(s+" "+counterIncrementValues.get(s));
                }
            }
            out.append("; ");
        }
        if(counterResetType != null) {
            out.append("counter-reset: ");
            if(counterResetType == EnumCounter.inherit || counterResetType == EnumCounter.none) {
                out.append(counterResetType);
            }
            else {
                boolean first = true;
                for(String s : counterResetValues.keySet()) {
                    if(first) {
                        first = false;
                    }
                    else {
                        out.append(" ");
                    }
                    out.append(s+" "+counterResetValues.get(s));
                }
            }
            out.append("; ");
        }

        if(clearType != null) {
            out.append("clear: ");
            out.append(clearType);
            out.append("; ");
        }
        if(clipTopType != null) {
            out.append("clip-top: ");
            if(clipTopType == EnumClip.length) {
                out.append(clipTopNumberValue.toString().trim());
            }
            else {
                out.append(clipTopType);
            }
            out.append("; ");
        }
        if(clipRightType != null) {
            out.append("clip-right: ");
            if(clipRightType == EnumClip.length) {
                out.append(clipRightNumberValue.toString().trim());
            }
            else {
                out.append(clipRightType);
            }
            out.append("; ");
        }
        if(clipBottomType != null) {
            out.append("clip-bottom: ");
            if(clipBottomType == EnumClip.length) {
                out.append(clipBottomNumberValue.toString().trim());
            }
            else {
                out.append(clipBottomType);
            }
            out.append("; ");
        }
        if(clipLeftType != null) {
            out.append("clip-left: ");
            if(clipLeftType == EnumClip.length) {
                out.append(clipLeftNumberValue.toString().trim());
            }
            else {
                out.append(clipLeftType);
            }
            out.append("; ");
        }
        if(colorType != null) {
            out.append("color: ");
            if(colorType == EnumColor.inherit) {
                out.append(colorType);
            }
            else {
                out.append(colorValue.toString().trim());
            }
            out.append("; ");
        }
        if(colorType != null) {
            out.append("color: ");
            if(colorType == EnumColor.inherit) {
                out.append(colorType);
            }
            else {
                out.append(colorValue.toString().trim());
            }
            out.append("; ");
        }
        if(cursorType != null) {
            out.append("cursor: ");
            for(TermURI t : cursorUri) {
                out.append(t.toString().trim());
                out.append(", ");
            }
            out.append(cursorType.toString().replace("prefix_", "").replace("_", "-"));
            out.append("; ");        
        }
        if(directionType != null) {
            out.append("direction: ");
            out.append(directionType.toString());
            out.append("; ");
        }
        if(displayType != null) {
            out.append("display: ");
            out.append(displayType.toString().replace("_", "-"));
            out.append("; ");
        }
        if(emptyCellsType != null) {
            out.append("empty-cells: ");
            out.append(emptyCellsType);
            out.append("; ");
        }
        if(floatType != null) {
            out.append("float: ");
            out.append(floatType);
            out.append("; ");
        }
        
        if(listStyleImageType != null) {
            out.append("list-style-image: ");
            if(listStyleImageType == EnumListStyleImage.inherit || listStyleImageType == EnumListStyleImage.none) {
                out.append(listStyleImageType);
            }
            else {
                out.append(listStyleImageValue.toString());
            }
            out.append("; ");
        }
        if(listStylePositionType != null) {
            out.append("list-style-position: ");
            out.append(listStylePositionType);
            out.append("; ");
        }
        if(listStyleTypeType != null) {
            out.append("list-style-type: ");
            out.append(listStyleTypeType);
            out.append("; ");
        }
        if(marginTopType != null) {
            out.append("margin-top: ");
            if(marginTopType == EnumSize.length) {
                out.append(marginTopNumberValue.toString().trim());
            }
            else if(marginTopType == EnumSize.percentage) {
                out.append(marginTopPercentValue.toString().trim());
            }
            else {
                out.append(marginTopType);
            }
            out.append("; ");
        }
        if(marginRightType != null) {
            out.append("margin-right: ");
            if(marginRightType == EnumSize.length) {
                out.append(marginRightNumberValue.toString().trim());
            }
            else if(marginRightType == EnumSize.percentage) {
                out.append(marginRightPercentValue.toString().trim());
            }
            else {
                out.append(marginRightType);
            }
            out.append("; ");
        }
        if(marginBottomType != null) {
            out.append("margin-bottom: ");
            if(marginBottomType == EnumSize.length) {
                out.append(marginBottomNumberValue.toString().trim());
            }
            else if(marginBottomType == EnumSize.percentage) {
                out.append(marginBottomPercentValue.toString().trim());
            }
            else {
                out.append(marginBottomType);
            }
            out.append("; ");
        }
        if(marginLeftType != null) {
            out.append("margin-left: ");
            if(marginLeftType == EnumSize.length) {
                out.append(marginLeftNumberValue.toString().trim());
            }
            else if(marginLeftType == EnumSize.percentage) {
                out.append(marginLeftPercentValue.toString().trim());
            }
            else {
                out.append(marginLeftType);
            }
            out.append("; ");
        }
        
        if(maxHeightType != null) {
            out.append("max-height: ");
            if(maxHeightType == EnumMinMaxSize.length) {
                out.append(maxHeightNumberValue.toString().trim());
            }
            else if(maxHeightType == EnumMinMaxSize.percentage) {
                out.append(maxHeightPercentValue.toString().trim());
            }
            else {
                out.append(maxHeightType);
            }
            out.append("; ");
        }
        if(maxWidthType != null) {
            out.append("max-width: ");
            if(maxWidthType == EnumMinMaxSize.length) {
                out.append(maxWidthNumberValue.toString().trim());
            }
            else if(maxWidthType == EnumMinMaxSize.percentage) {
                out.append(maxWidthPercentValue.toString().trim());
            }
            else {
                out.append(maxWidthType);
            }
            out.append("; ");
        }
        if(minHeightType != null) {
            out.append("min-height: ");
            if(minHeightType == EnumMinMaxSize.length) {
                out.append(minHeightNumberValue.toString().trim());
            }
            else if(minHeightType == EnumMinMaxSize.percentage) {
                out.append(minHeightPercentValue.toString().trim());
            }
            else {
                out.append(minHeightType);
            }
            out.append("; ");
        }
        if(minWidthType != null) {
            out.append("min-width: ");
            if(minWidthType == EnumMinMaxSize.length) {
                out.append(minWidthNumberValue.toString().trim());
            }
            else if(minWidthType == EnumMinMaxSize.percentage) {
                out.append(minWidthPercentValue.toString().trim());
            }
            else {
                out.append(minWidthType);
            }
            out.append("; ");
        }
        if(orphansType != null) {
            out.append("orphans: ");
            if(orphansType == EnumOrphans.integer) {
                out.append(orphansIntegerValue.toString().trim());
            }
            else {
                out.append(orphansType.toString());
            }
            out.append("; ");
        }
        if(outlineColorType != null) {
            out.append("outline-color: ");
            if(outlineColorType == EnumColorInvert.inherit || outlineColorType == EnumColorInvert.invert) {
                out.append(outlineColorType);
            }
            else {
                out.append(outlineColorValue.toString().trim());
            }
            out.append("; ");
        }
        if(outlineStyleType != null) {
            out.append("outline-style: ");
            out.append(outlineStyleType);
            out.append("; ");
        }
        if(outlineWidthType != null) {
            out.append("outline-width: ");
            if(outlineWidthType == EnumBorderWidth.length) {
                out.append(outlineWidthValue.toString().trim());
            }
            else {
                out.append(outlineWidthType.toString());
            }
            out.append("; ");
        }
        if(overflowType != null) {
            out.append("overflow: ");
            out.append(overflowType);
            out.append("; ");
        }
        
        if(paddingTopType != null) {
            out.append("padding-top: ");
            if(paddingTopType == EnumPaddingWidth.length) {
                out.append(paddingTopNumberValue.toString().trim());
            }
            else if(paddingTopType == EnumPaddingWidth.percentage) {
                out.append(paddingTopPercentValue.toString().trim());
            }
            else {
                out.append(paddingTopType);
            }
            out.append("; ");
        }
        if(paddingRightType != null) {
            out.append("padding-right: ");
            if(paddingRightType == EnumPaddingWidth.length) {
                out.append(paddingRightNumberValue.toString().trim());
            }
            else if(paddingRightType == EnumPaddingWidth.percentage) {
                out.append(paddingRightPercentValue.toString().trim());
            }
            else {
                out.append(paddingRightType);
            }
            out.append("; ");
        }
        if(paddingBottomType != null) {
            out.append("padding-bottom: ");
            if(paddingBottomType == EnumPaddingWidth.length) {
                out.append(paddingBottomNumberValue.toString().trim());
            }
            else if(paddingBottomType == EnumPaddingWidth.percentage) {
                out.append(paddingBottomPercentValue.toString().trim());
            }
            else {
                out.append(paddingBottomType);
            }
            out.append("; ");
        }
        if(paddingLeftType != null) {
            out.append("padding-left: ");
            if(paddingLeftType == EnumPaddingWidth.length) {
                out.append(paddingLeftNumberValue.toString().trim());
            }
            else if(paddingLeftType == EnumPaddingWidth.percentage) {
                out.append(paddingLeftPercentValue.toString().trim());
            }
            else {
                out.append(paddingLeftType);
            }
            out.append("; ");
        }
        
        if(pageBreakAfterType != null) {
            out.append("page-break-after: ");
            out.append(pageBreakAfterType);
            out.append("; ");
        }
        if(pageBreakBeforeType != null) {
            out.append("page-break-before: ");
            out.append(pageBreakBeforeType);
            out.append("; ");
        }
        if(pageBreakInsideType != null) {
            out.append("page-break-inside: ");
            out.append(pageBreakInsideType);
            out.append("; ");
        }
        if(positionType != null) {
            out.append("position: ");
            out.append(positionType);
            out.append("; ");
        }
        if(quotesType != null) {
            out.append("quotes: ");
            if(quotesType == EnumQuotes.inherit || quotesType == EnumQuotes.none) {
                out.append(quotesType);
            }
            else {
                boolean first = true;
                for(String s : quotesValues) {
                    if(first) {
                        first = false;
                    }
                    else {
                        out.append(" ");
                    }
                    out.append("'"+s.trim()+"'");
                }
            }
            out.append("; ");
        }
        if(tableLayoutType != null) {
            out.append("table-layout: ");
            out.append(tableLayoutType);
            out.append("; ");
        }
        if(textAlignType != null) {
            out.append("text-align: ");
            out.append(textAlignType);
            out.append("; ");
        }
        if(textDecorationType != null) {
            out.append("text-decoration: ");
            if(textDecorationType == EnumTextDecoration.inherit) {
                out.append(textDecorationType);
            }
            else {
                String prepared = "";
                if(textDecorationBlink) {
                    prepared += " blink";
                }
                if(textDecorationLineThrough) {
                    prepared += " line-through";
                }
                if(textDecorationOverline) {
                    prepared += " overline";
                }
                if(textDecorationUnderline) {
                    prepared += " underline";
                }
                if(prepared.equals("")) {
                    out.append("none");
                }
                else {
                    out.append(prepared.trim());
                }
            }
            out.append("; ");
        }
        if(textIndentType != null) {
            out.append("text-indent: ");
            if(textIndentType == EnumTextIndent.length) {
                out.append(textIndentNumberValue.toString().trim());
            }
            else if(textIndentType == EnumTextIndent.percentage) {
                out.append(textIndentPercentValue.toString().trim());
            }
            else {
                out.append(textIndentType);
            }
            out.append("; ");
        }
        if(textTransformType != null) {
            out.append("text-transform: ");
            out.append(textTransformType);
            out.append("; ");
        }
        if(unicodeBidiType != null) {
            out.append("unicode-bidi: ");
            out.append(unicodeBidiType.toString().replace("_", "-"));
            out.append("; ");
        }
        if(verticalAlignType != null) {
            out.append("vertical-align: ");
            if(verticalAlignType == EnumVerticalAlign.length) {
                out.append(verticalAlignNumberValue.toString().trim());
            }
            else if(verticalAlignType == EnumVerticalAlign.percentage) {
                out.append(verticalAlignPercentValue.toString().trim());
            }
            else {
                out.append(verticalAlignType.toString().replace("prefix_", "").replace("_", "-"));
            }
            out.append("; ");
        }
        if(visibilityType != null) {
            out.append("visibility: ");
            out.append(visibilityType);
            out.append("; ");
        }
        if(whiteSpaceType != null) {
            out.append("white-space: ");
            out.append(whiteSpaceType.toString().replace("_", "-"));
            out.append("; ");
        }
        if(widowsType != null) {
            out.append("widows: ");
            if(widowsType == EnumWidows.integer) {
                out.append(widowsIntegerValue.toString().trim());
            }
            else {
                out.append(widowsType);
            }
            out.append("; ");
        }
        if(wordSpacingType != null) {
            out.append("word-spacing: ");
            if(wordSpacingType == EnumWordSpacing.length) {
                out.append(wordSpacingNumberValue.toString().trim());
            }
            else {
                out.append(wordSpacingType);
            }
            out.append("; ");
        }
        if(zIndexType != null) {
            out.append("z-index: ");
            if(zIndexType == EnumZIndex.integer) {
                out.append(zIndexIntegerValue.toString().trim());
            }
            else {
                out.append(zIndexType.toString());
            }
            out.append("; ");
        }
        
        return out.toString().trim();
    }
    
   */
}
