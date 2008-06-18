package cz.vutbr.web.domassign;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermNumber;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.css.TermString;
import cz.vutbr.web.css.TermUri;
import cz.vutbr.web.domassign.data.DataDeclaration;
import cz.vutbr.web.domassign.data.DataTermIdent;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Třída pro uchovávání informací o daném elementu v HTML stromu
 * @author Jan Svercl, VUT Brno, 2008
 */
public class NodeData implements Cloneable {

    public enum EnumColorTransparent { color, transparent, inherit }
    public enum EnumColorInvert { color, invert, inherit }
    public enum EnumColor { color, inherit }
    public enum EnumSize { length, percentage, auto, inherit }
    public enum EnumMinMaxSize { length, percentage, none, inherit }
    
    public enum EnumBackgroundAttachment { scroll, fixed, inherit }
    public enum EnumBackgroundImage { uri, none, inherit }
    public enum EnumBackgroundPosition { value, inherit }
    public enum EnumBackgroundPositionHor { percentage, length, left, center, right }
    public enum EnumBackgroundPositionVer { percentage, length, top, center, bottom }
    public enum EnumBackgroundRepeat { repeat, repeat_x, repeat_y, no_repeat, inherit }
    
    public enum EnumBorderCollapse { collapse, separate, inherit }
    public enum EnumBorderSpacing { length, inherit }
    public enum EnumBorderStyle { none, hidden, dotted, dashed, solid, prefix_double, groove, ridge, inset, outset, inherit }
    public enum EnumBorderWidth { length, thin, medium, thick, inherit }
    
    public enum EnumFontFamily { font, inherit }
    public enum EnumFontSize { percentage, length, xx_small, x_small, small, medium, large, x_large, xx_large, larger, smaller, inherit }
    public enum EnumFontStyle { normal, italic, oblique, inherit }
    public enum EnumFontVariant { normal, small_caps, inherit }
    public enum EnumFontWeight { normal, bold, bolder, lighter, prefix_100, prefix_200, prefix_300, prefix_400, prefix_500, prefix_600, prefix_700, prefix_800, prefix_900, inherit }
    public enum EnumLineHeight { normal, number, length, percentage, inherit }
    
    public enum EnumCaptionSide { top, bottom, inherit }
    public enum EnumContent { normal, none, list_values, inherit }
    public enum EnumCounter { none, table_values, inherit }
    public enum EnumClear { none, left, right, both, inherit }
    public enum EnumClip { length, auto, inherit }
    public enum EnumCursor { auto, crosshair, prefix_default, pointer, move, e_resize, ne_resize, nw_resize, n_resize, se_resize, sw_resize, s_resize, w_resize, text, wait, progress, help, inherit }
    public enum EnumDirection { ltr, rtl, inherit }
    public enum EnumDisplay { inline, block, list_item, run_in, inline_block, table, inline_table, table_row_group, table_header_group, table_footer_group, table_row, table_column_group, table_column, table_cell, table_caption, none, inherit }
    public enum EnumEmptyCells { show, hide, inherit }
    public enum EnumFloat { none, left, right, inherit }
    
    public enum EnumListStyleImage { uri, none, inherit }
    public enum EnumListStylePosition { inside, outside, inherit }
    public enum EnumListStyleType { disc, circle, square, decimal, decimal_leading_zero, lower_roman, upper_roman, lower_greek, lower_latin, upper_latin, armenian, georgian, lower_alpha, upper_alpha, none, inherit }
    public enum EnumOrphans { integer, inherit }
    public enum EnumOverflow { visible, hidden, scroll, auto, inherit }
    public enum EnumPaddingWidth { length, percentage, inherit }
    public enum EnumPageBreak { auto, always, avoid, left, right, inherit }
    public enum EnumPageBreakInside { auto, avoid, inherit }
    public enum EnumPosition { prefix_static, relative, absolute, fixed, inherit }
    public enum EnumQuotes { none, list_values, inherit }
    public enum EnumTableLayout { auto, fixed, inherit }
    public enum EnumTextAlign { by_direction, left, right, center, justify, inherit }
    public enum EnumTextDecoration { set, inherit }
    public enum EnumTextIndent { length, percentage, inherit }
    public enum EnumTextTransform { capitalize, uppercase, lowercase, none, inherit }
    public enum EnumUnicodeBidi { normal, embed, bidi_override, inherit }
    public enum EnumVerticalAlign { baseline, sub, prefix_super, top, text_top, middle, bottom, text_bottom, length, percentage, inherit }
    public enum EnumVisibility { visible, hidden, collapse, inherit }
    public enum EnumWhiteSpace { normal, pre, nowrap, pre_wrap, pre_line, inherit }
    public enum EnumWidows { integer, inherit }
    public enum EnumWordSpacing { length, normal, inherit }
    public enum EnumZIndex { auto, integer, inherit }
    
    
    //Background
    private EnumBackgroundAttachment  backgroundAttachmentType = null;
    private EnumColorTransparent      backgroundColorType = null;
    private TermColor                 backgroundColorValue = null;
    private EnumBackgroundImage       backgroundImageType = null;
    private TermUri                   backgroundImageValue = null;
    private EnumBackgroundPosition    backgroundPositionType = null;
    private EnumBackgroundPositionHor backgroundPositionHorType = null;
    private EnumBackgroundPositionVer backgroundPositionVerType = null;
    private TermPercent               backgroundPositionHorPercentValue = null;
    private TermNumber                backgroundPositionHorNumberValue = null;
    private TermPercent               backgroundPositionVerPercentValue = null;
    private TermNumber                backgroundPositionVerNumberValue = null;
    private EnumBackgroundRepeat      backgroundRepeatType = null;
    
    //Border
    private EnumBorderCollapse        borderCollapseType = null;
    private EnumColorTransparent      borderColorTopType = null;
    private EnumColorTransparent      borderColorRightType = null;
    private EnumColorTransparent      borderColorBottomType = null;
    private EnumColorTransparent      borderColorLeftType = null;
    private TermColor                 borderColorTopValue = null;
    private TermColor                 borderColorRightValue = null;
    private TermColor                 borderColorBottomValue = null;
    private TermColor                 borderColorLeftValue = null;
    private EnumBorderSpacing         borderSpacingType = null;
    private TermNumber                borderSpacingHorValue = null;
    private TermNumber                borderSpacingVerValue = null;
    private EnumBorderStyle           borderStyleTopType = null;
    private EnumBorderStyle           borderStyleRightType = null;
    private EnumBorderStyle           borderStyleBottomType = null;
    private EnumBorderStyle           borderStyleLeftType = null;
    private EnumBorderWidth           borderWidthTopType = null;
    private EnumBorderWidth           borderWidthRightType = null;
    private EnumBorderWidth           borderWidthBottomType = null;
    private EnumBorderWidth           borderWidthLeftType = null;
    private TermNumber                borderWidthTopValue = null;
    private TermNumber                borderWidthRightValue = null;
    private TermNumber                borderWidthBottomValue = null;
    private TermNumber                borderWidthLeftValue = null;
    
    //Font
    private EnumFontStyle             fontStyleType = null;
    private EnumFontFamily            fontFamilyType = null;
    private ArrayList<String>         fontFamilyValues = new ArrayList<String>();
    private EnumFontSize              fontSizeType = null;
    private TermPercent               fontSizePercentValue = null;
    private TermNumber                fontSizeNumberValue = null;
    private EnumFontVariant           fontVariantType = null;
    private EnumFontWeight            fontWeightType = null;
    private EnumLineHeight            lineHeightType = null;
    private TermPercent               lineHeightPercentValue = null;
    private TermNumber                lineHeightNumberValue = null;
     
    //Pozice
    private EnumSize                  topType = null;
    private TermPercent               topPercentValue = null;
    private TermNumber                topNumberValue = null;
    private EnumSize                  rightType = null;
    private TermPercent               rightPercentValue = null;
    private TermNumber                rightNumberValue = null;
    private EnumSize                  bottomType = null;
    private TermPercent               bottomPercentValue = null;
    private TermNumber                bottomNumberValue = null;
    private EnumSize                  leftType = null;
    private TermPercent               leftPercentValue = null;
    private TermNumber                leftNumberValue = null;
    private EnumSize                  widthType = null;
    private TermPercent               widthPercentValue = null;
    private TermNumber                widthNumberValue = null;
    private EnumSize                  heightType = null;
    private TermPercent               heightPercentValue = null;
    private TermNumber                heightNumberValue = null;
    
    private EnumCaptionSide           captionSideType = null;
    private EnumContent               contentType = null;
    private ArrayList<Term>           contentValues = new ArrayList<Term>();
    private EnumCounter               counterIncrementType = null;
    private HashMap<String, Integer>  counterIncrementValues = new HashMap<String, Integer>();
    private EnumCounter               counterResetType = null;
    private HashMap<String, Integer>  counterResetValues = new HashMap<String, Integer>();
    private EnumClear                 clearType = null;
    private EnumClip                  clipTopType = null;
    private EnumClip                  clipRightType = null;
    private EnumClip                  clipBottomType = null;
    private EnumClip                  clipLeftType = null;
    private TermNumber                clipTopNumberValue = null;
    private TermNumber                clipRightNumberValue = null;
    private TermNumber                clipBottomNumberValue = null;
    private TermNumber                clipLeftNumberValue = null;
    
    private EnumColor                 colorType = null;
    private TermColor                 colorValue = null;
    private EnumCursor                cursorType = null;
    private ArrayList<TermUri>        cursorUri = new ArrayList<TermUri>();
    private EnumDirection             directionType = null;
    private EnumDisplay               displayType = null;
    private EnumEmptyCells            emptyCellsType = null;
    private EnumFloat                 floatType = null;
    
    private EnumListStyleImage        listStyleImageType = null;
    private TermUri                   listStyleImageValue = null;  
    private EnumListStylePosition     listStylePositionType = null;
    private EnumListStyleType         listStyleTypeType = null;
    
    private EnumSize                  marginTopType = null;
    private TermPercent               marginTopPercentValue = null;
    private TermNumber                marginTopNumberValue = null;
    private EnumSize                  marginRightType = null;
    private TermPercent               marginRightPercentValue = null;
    private TermNumber                marginRightNumberValue = null;
    private EnumSize                  marginBottomType = null;
    private TermPercent               marginBottomPercentValue = null;
    private TermNumber                marginBottomNumberValue = null;
    private EnumSize                  marginLeftType = null;
    private TermPercent               marginLeftPercentValue = null;
    private TermNumber                marginLeftNumberValue = null;
    
    private EnumMinMaxSize            maxHeightType = null;
    private TermPercent               maxHeightPercentValue = null;
    private TermNumber                maxHeightNumberValue = null;
    private EnumMinMaxSize            maxWidthType = null;
    private TermPercent               maxWidthPercentValue = null;
    private TermNumber                maxWidthNumberValue = null;
    private EnumMinMaxSize            minHeightType = null;
    private TermPercent               minHeightPercentValue = null;
    private TermNumber                minHeightNumberValue = null;
    private EnumMinMaxSize            minWidthType = null;
    private TermPercent               minWidthPercentValue = null;
    private TermNumber                minWidthNumberValue = null;
    
    private EnumOrphans               orphansType = null;
    private TermNumber                orphansIntegerValue = null;
    private EnumColorInvert           outlineColorType = null;
    private TermColor                 outlineColorValue = null;
    private EnumBorderStyle           outlineStyleType = null;
    private EnumBorderWidth           outlineWidthType = null;
    private TermNumber                outlineWidthValue = null;
    private EnumOverflow              overflowType = null;
    
    private EnumPaddingWidth          paddingTopType = null;
    private TermPercent               paddingTopPercentValue = null;
    private TermNumber                paddingTopNumberValue = null;
    private EnumPaddingWidth          paddingRightType = null;
    private TermPercent               paddingRightPercentValue = null;
    private TermNumber                paddingRightNumberValue = null;
    private EnumPaddingWidth          paddingBottomType = null;
    private TermPercent               paddingBottomPercentValue = null;
    private TermNumber                paddingBottomNumberValue = null;
    private EnumPaddingWidth          paddingLeftType = null;
    private TermPercent               paddingLeftPercentValue = null;
    private TermNumber                paddingLeftNumberValue = null;
    
    private EnumPageBreak             pageBreakAfterType = null;
    private EnumPageBreak             pageBreakBeforeType = null;
    private EnumPageBreakInside       pageBreakInsideType = null;
    private EnumPosition              positionType = null;
    private EnumQuotes                quotesType = null;
    private ArrayList<String>         quotesValues = new ArrayList<String>();
    private EnumTableLayout           tableLayoutType = null;
    private EnumTextAlign             textAlignType = null;
    private EnumTextDecoration        textDecorationType = null;
    private Boolean                   textDecorationUnderline = null;
    private Boolean                   textDecorationOverline = null;
    private Boolean                   textDecorationLineThrough = null;
    private Boolean                   textDecorationBlink = null;
    private EnumTextIndent            textIndentType = null;
    private TermPercent               textIndentPercentValue = null;
    private TermNumber                textIndentNumberValue = null;
    private EnumTextTransform         textTransformType = null;
    private EnumUnicodeBidi           unicodeBidiType = null;
    private EnumVerticalAlign         verticalAlignType = null;
    private TermPercent               verticalAlignPercentValue = null;
    private TermNumber                verticalAlignNumberValue = null;
    private EnumVisibility            visibilityType = null;
    private EnumWhiteSpace            whiteSpaceType = null;
    private EnumWidows                widowsType = null;
    private TermNumber                widowsIntegerValue = null;
    private EnumWordSpacing           wordSpacingType = null;
    private TermNumber                wordSpacingNumberValue = null;
    private EnumZIndex                zIndexType = null;
    private TermNumber                zIndexIntegerValue = null;
    
    //Proměnné pro ukládání správných, nepodporovaných a chybných deklarací
    private String                    unsupported = "";
    private String                    ignored = "";
    private String                    passed = "";
    
    private static HashMap<String, Method> cache = new HashMap();
    
    /**
     * Funkce zpracuje jednu deklaraci a pokud je tato správná, aplikuje její
     * její hodnoty.
     * @param d deklarace
     */
    public void putDeclaration(Declaration d) {
        try {
            Method m = cache.get(d.getProperty());
            if(m == null) {
                synchronized (cache) {
                    m = this.getClass().getDeclaredMethod(methodName(d.getProperty()), new Class [] { Declaration.class });
                    if(m != null) {
                       cache.put(d.getProperty(), m);
                    }
                }
            }
            
            Boolean status = (Boolean)m.invoke(this, new Object [] { d });
            if(status.booleanValue()) {
                if(getPassed().length() != 0) {
                    passed += " ";
                }
                passed += d.toString(0).trim();
            }
            else {
                if(getIgnored().length() != 0) {
                    ignored += " ";
                }
                ignored += d.toString(0).trim();
            }
        }

        catch (NoSuchMethodException e) {
            if(getUnsupported().length() != 0) {
                unsupported += " ";
            }
            unsupported += d.toString(0).trim();
        }        
	catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Funkce převede název vlastnosti na název funkce, která bude volána. Název
     * funkce začíná slovem "process" a následuje názvem vlastnosti bez pomlček
     * (pomlčky v názvu vlastnosti jsou nahrazeny velkým následujícím písemenem v
     * názvu funkce)
     * @param property název
     * @return název funkce
     */
    private String methodName(String property) {
        byte[] b = property.getBytes();
        StringBuffer out = new StringBuffer("process");
        boolean upper = true;
        for(byte chr : b) {
            if((chr > 96 && chr < 123) || (chr > 64 && chr < 91)) {  //if(tmp.matches("[a-zA-Z]"))
                if(upper) {
                    if(chr > 96) { //toUpperCase()
                        chr -= 32;
                    }
                    out.append((char)chr);
                    upper = false;
                } else {
                    if(chr < 91) { //toLowerCase()
                        chr += 32;
                    }
                    out.append((char)chr);
                }
            } else if(chr == 45) { //equals("-")
                upper = true;
            }
            else {
                //Nepodporovaný znak (pokus o IE-like vlastnost např. _margin-top)
                out.append("Underscore");
            }
        }
        return out.toString();
    }
    
    private Boolean processBackgroundAttachment(Declaration d) {
        //Zpracování vlastnosti background-attachment. Přípustné hodnoty jsou pouze 
        //scroll, fixed a inherit. Výchozí hodnota je scroll
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("scroll")) {
                    backgroundAttachmentType = EnumBackgroundAttachment.scroll;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("fixed")) {
                    backgroundAttachmentType = EnumBackgroundAttachment.fixed;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    backgroundAttachmentType = EnumBackgroundAttachment.inherit;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBackgroundColor(Declaration d) {
        //Zpracování vlastnosti background-color. Přípustné hodnoty jsou pouze 
        //<barva>, transparent a inherit. Výchozí hodnota je transparent
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermColor) {
                TermColor color = (TermColor)d.getTermsList().get(0);
                backgroundColorValue = color;
                backgroundColorType = EnumColorTransparent.color;
                return new Boolean(true);
                
            }
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("transparent")) {
                    backgroundColorType = EnumColorTransparent.transparent;
                    backgroundColorValue = null;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    backgroundColorType = EnumColorTransparent.inherit;
                    backgroundColorValue = null;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBackgroundImage(Declaration d) {
        //Zpracování vlastnosti background-image. Přípustné hodnoty jsou pouze 
        //<uri>, none a inherit. Výchozí hodnota je none
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermUri) {
                TermUri uri = (TermUri)d.getTermsList().get(0);
                backgroundImageValue = uri;
                backgroundImageType = EnumBackgroundImage.uri;
                return new Boolean(true);
                
            }
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("none")) {
                    backgroundImageType = EnumBackgroundImage.none;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    backgroundImageType = EnumBackgroundImage.inherit;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBackgroundRepeat(Declaration d) {
        //Zpracování vlastnosti background-repeat. Přípustné hodnoty jsou pouze 
        //repeat, repeat-x, repeat-y a inherit. Výchozí hodnota je repeat
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("repeat")) {
                    backgroundRepeatType = EnumBackgroundRepeat.repeat;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("repeat-x")) {
                    backgroundRepeatType = EnumBackgroundRepeat.repeat_x;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("repeat-y")) {
                    backgroundRepeatType = EnumBackgroundRepeat.repeat_y;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("no-repeat")) {
                    backgroundRepeatType = EnumBackgroundRepeat.no_repeat;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    backgroundRepeatType = EnumBackgroundRepeat.inherit;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBackgroundPosition(Declaration d) {
        //Zpracování vlastnosti background-position.
        Term t1 = null;
        Term t2 = null;
        if(d.getTermsList().size() == 1) {
            //Pokud je v deklaraci pouze jeden term, znamená to, že buď je tento term inherit,
            //nebo je třeba druhou hodnotu doplnit jako identifikátor center
            //Popis dle W3C: If only one value is specified, the second value is assumed to be 'center'.
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    backgroundPositionType = EnumBackgroundPosition.inherit;
                    backgroundPositionHorNumberValue = null;
                    backgroundPositionHorPercentValue = null;
                    backgroundPositionVerNumberValue = null;
                    backgroundPositionVerPercentValue = null;
                    backgroundPositionHorType = null;
                    backgroundPositionVerType = null;
                    return new Boolean(true);
                }
            }
            t1 = d.getTermsList().get(0);
            t2 = new DataTermIdent("center");
        }
        if(d.getTermsList().size() == 2) {
            t1 = d.getTermsList().get(0);
            t2 = d.getTermsList().get(1);
        }
        
        //V tomto bodě jsou definovány termy t1 a t2
        if(d.getTermsList().size() <= 2) {
            //Pořadí termů Vertical - Horizontal je pouze v případě že druhý je left nebo right
            //nebo první je top nebo bottom
            //Pokud ano, přehodím je.
            if(    ((t2 instanceof TermIdent) && (((TermIdent)t2).getValue().equalsIgnoreCase("left")))
                || ((t2 instanceof TermIdent) && (((TermIdent)t2).getValue().equalsIgnoreCase("right")))
                || ((t1 instanceof TermIdent) && (((TermIdent)t1).getValue().equalsIgnoreCase("top")))
                || ((t1 instanceof TermIdent) && (((TermIdent)t1).getValue().equalsIgnoreCase("bottom")))
            ) {
                Term tmp = t1;
                t1 = t2;
                t2 = tmp;
            }
            //Teď je určitě pořadí termů Horizontal - Vertical
            NodeData trans = beginTransaction();
            backgroundPositionType = EnumBackgroundPosition.value;
            
            //Zpracování první hodnoty (horizontální směr)
            if((t1 instanceof TermNumber) && ((TermNumber)t1).isLength()) {
                backgroundPositionHorNumberValue = (TermNumber)t1;
                backgroundPositionHorPercentValue = null;
                backgroundPositionHorType = EnumBackgroundPositionHor.length;
            }
            else if(t1 instanceof TermPercent) {
                backgroundPositionHorNumberValue = null;
                backgroundPositionHorPercentValue = (TermPercent)t1;
                backgroundPositionHorType = EnumBackgroundPositionHor.percentage;
            }
            else if((t1 instanceof TermIdent) && (((TermIdent)t1).getValue().equalsIgnoreCase("left"))) {
                backgroundPositionHorNumberValue = null;
                backgroundPositionHorPercentValue = null;
                backgroundPositionHorType = EnumBackgroundPositionHor.left;
            }
            else if((t1 instanceof TermIdent) && (((TermIdent)t1).getValue().equalsIgnoreCase("right"))) {
                backgroundPositionHorNumberValue = null;
                backgroundPositionHorPercentValue = null;
                backgroundPositionHorType = EnumBackgroundPositionHor.right;
            }
            else if((t1 instanceof TermIdent) && (((TermIdent)t1).getValue().equalsIgnoreCase("center"))) {
                backgroundPositionHorNumberValue = null;
                backgroundPositionHorPercentValue = null;
                backgroundPositionHorType = EnumBackgroundPositionHor.center;
            }
            else {
                rollbackTransaction(trans);
                return new Boolean(false);
            }
            
            //Zpracování druhé hodnoty (Vertikální směr)
            if((t2 instanceof TermNumber) && ((TermNumber)t2).isLength()) {
                backgroundPositionVerNumberValue = (TermNumber)t2;
                backgroundPositionVerPercentValue = null;
                backgroundPositionVerType = EnumBackgroundPositionVer.length;
            }
            else if(t2 instanceof TermPercent) {
                backgroundPositionVerNumberValue = null;
                backgroundPositionVerPercentValue = (TermPercent)t2;
                backgroundPositionVerType = EnumBackgroundPositionVer.percentage;
            }
            else if((t2 instanceof TermIdent) && (((TermIdent)t2).getValue().equalsIgnoreCase("top"))) {
                backgroundPositionVerNumberValue = null;
                backgroundPositionVerPercentValue = null;
                backgroundPositionVerType = EnumBackgroundPositionVer.top;
            }
            else if((t2 instanceof TermIdent) && (((TermIdent)t2).getValue().equalsIgnoreCase("bottom"))) {
                backgroundPositionVerNumberValue = null;
                backgroundPositionVerPercentValue = null;
                backgroundPositionVerType = EnumBackgroundPositionVer.bottom;
            }
            else if((t2 instanceof TermIdent) && (((TermIdent)t2).getValue().equalsIgnoreCase("center"))) {
                backgroundPositionVerNumberValue = null;
                backgroundPositionVerPercentValue = null;
                backgroundPositionVerType = EnumBackgroundPositionVer.center;
            }
            else {
                rollbackTransaction(trans);
                return new Boolean(false);
            }
            
            //Pokud došel běh programu až sem, jsou obě hodnoty správně rozpoznány
            return new Boolean(true);
        }
        return new Boolean(false);
    }
    
    private Boolean processBackground(Declaration d) {
        NodeData trans = beginTransaction();
        //Nastavení na výchozí hodnoty
        backgroundAttachmentType = null;
        backgroundColorType = null;
        backgroundColorValue = null;
        backgroundImageType = null;
        backgroundImageValue = null;
        backgroundPositionType = null;
        backgroundPositionHorType = null;
        backgroundPositionVerType = null;
        backgroundPositionHorPercentValue = null;
        backgroundPositionHorNumberValue = null;
        backgroundPositionVerPercentValue = null;
        backgroundPositionVerNumberValue = null;
        backgroundRepeatType = null;
        
        //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné deklaraci
        //objevila například 2x barva. K určení slouží následující proměnné
        boolean processedColor = false;
        boolean processedAttachment = false;
        boolean processedRepeat = false;
        boolean processedImage = false;
        
        //V seznamu se může vyskytnout 1 nebo 2 termy (pokud dva, tak za sebou), které identifikují
        //vlastnost background-position. Ty se načítají do pomocných proměnných a na konci cyklu se ověří
        Term probablyFirstPositionTerm = null;
        Term probablySecondPositionTerm = null;
        int firstTermIndex = 0;
        
        int index = 0;
        for(Term t : d.getTermsList()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTermsList().size() == 1) {
                    backgroundAttachmentType = EnumBackgroundAttachment.inherit;
                    backgroundColorType = EnumColorTransparent.inherit;
                    backgroundImageType = EnumBackgroundImage.inherit;
                    backgroundPositionType = EnumBackgroundPosition.inherit;
                    backgroundPositionHorType = null;
                    backgroundPositionVerType = null;
                    backgroundRepeatType = EnumBackgroundRepeat.inherit;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten aktuální)
            //a v jednotlivých blocích se pokouším tuto deklaraci zpracovat. 
            Declaration tmpDeclaration = new DataDeclaration("background");
            tmpDeclaration.getTermsList().add(t);
            
            //Vyzkouším, jestli se jedná o barvu
            tmpDeclaration.setProperty("background-color");
            if(processBackgroundColor(tmpDeclaration)) {
                //Jedná se o barvu. Zjistím, jestli barva už nebyla jednou zadána
                if(processedColor) {
                    //Barva už byla jednou zadáno, deklarace je chybná, rollback a konec
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    //Barva ještě nebyla zadána, pokračujeme dalším termem
                    processedColor = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("background-attachment");
            if(processBackgroundAttachment(tmpDeclaration)) {
                if(processedAttachment) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedAttachment = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("background-image");
            if(processBackgroundImage(tmpDeclaration)) {
                if(processedImage) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedImage = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("background-repeat");
            if(processBackgroundRepeat(tmpDeclaration)) {
                if(processedRepeat) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedRepeat = true;
                    continue;
                }
            }
            
            //Term není nic z předchozího, možná se jedná o term z dvojice pro určení background-position
            //uložím si ho postupně do probablyFirstPositionTerm či probablySecondPositionTerm, pokud nenásledují
            //přímo po sobě či by se objevil třetí term, je deklarace ignotována
            if(probablyFirstPositionTerm == null) {
                probablyFirstPositionTerm = t;
                firstTermIndex = index;
            }
            else {
                if(index-1 == firstTermIndex) {
                    probablySecondPositionTerm = t;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            index++;
        }
        
        //Pokud byl nalezen jeden nebo dva neidentifikovatelné termy, zkusím je použít jako
        //backround-position. Pokud se to nepodaří, deklarace je ignorována
        if(probablyFirstPositionTerm != null) {
            Declaration tmpDeclaration = new DataDeclaration("background-position");
            tmpDeclaration.getTermsList().add(probablyFirstPositionTerm);
            if(probablySecondPositionTerm != null) {
                tmpDeclaration.getTermsList().add(probablySecondPositionTerm);
            }
            if(!processBackgroundPosition(tmpDeclaration)) {
                rollbackTransaction(trans);
                return false;
            }
        }
        return true;
    }
    
    private Boolean processBorderCollapse(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("collapse")) {
                    borderCollapseType = EnumBorderCollapse.collapse;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("separate")) {
                    borderCollapseType = EnumBorderCollapse.separate;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    borderCollapseType = EnumBorderCollapse.inherit;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderColor(Declaration d) {
        NodeData trans = beginTransaction();
        if(d.getTermsList().size() == 1) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-color");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-color");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-color");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-color");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(0));
            
            if(processBorderTopColor(tmpDeclarationTop) && processBorderRightColor(tmpDeclarationRight) &&
               processBorderBottomColor(tmpDeclarationBottom) && processBorderLeftColor(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTermsList().size() == 2) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-color");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-color");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-color");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-color");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(1));
            
            if(processBorderTopColor(tmpDeclarationTop) && processBorderRightColor(tmpDeclarationRight) &&
               processBorderBottomColor(tmpDeclarationBottom) && processBorderLeftColor(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTermsList().size() == 3) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-color");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-color");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-color");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-color");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(1));
            
            if(processBorderTopColor(tmpDeclarationTop) && processBorderRightColor(tmpDeclarationRight) &&
               processBorderBottomColor(tmpDeclarationBottom) && processBorderLeftColor(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTermsList().size() == 4) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-color");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-color");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-color");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-color");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(3));
            
            if(processBorderTopColor(tmpDeclarationTop) && processBorderRightColor(tmpDeclarationRight) &&
               processBorderBottomColor(tmpDeclarationBottom) && processBorderLeftColor(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderTopColor(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermColor) {
                TermColor color = (TermColor)d.getTermsList().get(0);
                borderColorTopValue = color;
                borderColorTopType = EnumColorTransparent.color;
                return new Boolean(true);
                
            }
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("transparent")) {
                    borderColorTopType = EnumColorTransparent.transparent;
                    borderColorTopValue = null;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    borderColorTopType = EnumColorTransparent.inherit;
                    borderColorTopValue = null;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderRightColor(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermColor) {
                TermColor color = (TermColor)d.getTermsList().get(0);
                borderColorRightValue = color;
                borderColorRightType = EnumColorTransparent.color;
                return new Boolean(true);
                
            }
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("transparent")) {
                    borderColorRightType = EnumColorTransparent.transparent;
                    borderColorRightValue = null;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    borderColorRightType = EnumColorTransparent.inherit;
                    borderColorRightValue = null;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderBottomColor(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermColor) {
                TermColor color = (TermColor)d.getTermsList().get(0);
                borderColorBottomValue = color;
                borderColorBottomType = EnumColorTransparent.color;
                return new Boolean(true);
                
            }
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("transparent")) {
                    borderColorBottomType = EnumColorTransparent.transparent;
                    borderColorBottomValue = null;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    borderColorBottomType = EnumColorTransparent.inherit;
                    borderColorBottomValue = null;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderLeftColor(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermColor) {
                TermColor color = (TermColor)d.getTermsList().get(0);
                borderColorLeftValue = color;
                borderColorLeftType = EnumColorTransparent.color;
                return new Boolean(true);
                
            }
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("transparent")) {
                    borderColorLeftType = EnumColorTransparent.transparent;
                    borderColorLeftValue = null;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    borderColorLeftType = EnumColorTransparent.inherit;
                    borderColorLeftValue = null;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderSpacing(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    borderSpacingType = EnumBorderSpacing.inherit;
                    borderSpacingHorValue = null;
                    borderSpacingVerValue = null;
                    return new Boolean(true);
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    if(num.getValue().floatValue() < 0) {
                        num.setValue(new Float(0));
                    }
                    borderSpacingType = EnumBorderSpacing.length;
                    borderSpacingHorValue = num;
                    borderSpacingVerValue = num;
                    return new Boolean(true);
                }
            }
        }
        if(d.getTermsList().size() == 2) {
            if(d.getTermsList().get(0) instanceof TermNumber && d.getTermsList().get(1) instanceof TermNumber) {
                TermNumber num1 = (TermNumber)d.getTermsList().get(0);
                TermNumber num2 = (TermNumber)d.getTermsList().get(1);
                if(num1.isLength() && num2.isLength()) {
                    if(num1.getValue().floatValue() < 0) {
                        num1.setValue(new Float(0));
                    }
                    if(num2.getValue().floatValue() < 0) {
                        num2.setValue(new Float(0));
                    }
                    borderSpacingType = EnumBorderSpacing.length;
                    borderSpacingHorValue = num1;
                    borderSpacingVerValue = num2;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderTopStyle(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("none")) {
                    borderStyleTopType = EnumBorderStyle.none;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("hidden")) {
                    borderStyleTopType = EnumBorderStyle.hidden;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("dotted")) {
                    borderStyleTopType = EnumBorderStyle.dotted;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("dashed")) {
                    borderStyleTopType = EnumBorderStyle.dashed;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("solid")) {
                    borderStyleTopType = EnumBorderStyle.solid;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("double")) {
                    borderStyleTopType = EnumBorderStyle.prefix_double;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("groove")) {
                    borderStyleTopType = EnumBorderStyle.groove;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("ridge")) {
                    borderStyleTopType = EnumBorderStyle.ridge;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inset")) {
                    borderStyleTopType = EnumBorderStyle.inset;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("outset")) {
                    borderStyleTopType = EnumBorderStyle.outset;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    borderStyleTopType = EnumBorderStyle.inherit;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderRightStyle(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("none")) {
                    borderStyleRightType = EnumBorderStyle.none;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("hidden")) {
                    borderStyleRightType = EnumBorderStyle.hidden;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("dotted")) {
                    borderStyleRightType = EnumBorderStyle.dotted;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("dashed")) {
                    borderStyleRightType = EnumBorderStyle.dashed;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("solid")) {
                    borderStyleRightType = EnumBorderStyle.solid;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("double")) {
                    borderStyleRightType = EnumBorderStyle.prefix_double;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("groove")) {
                    borderStyleRightType = EnumBorderStyle.groove;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("ridge")) {
                    borderStyleRightType = EnumBorderStyle.ridge;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inset")) {
                    borderStyleRightType = EnumBorderStyle.inset;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("outset")) {
                    borderStyleRightType = EnumBorderStyle.outset;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    borderStyleRightType = EnumBorderStyle.inherit;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderBottomStyle(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("none")) {
                    borderStyleBottomType = EnumBorderStyle.none;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("hidden")) {
                    borderStyleBottomType = EnumBorderStyle.hidden;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("dotted")) {
                    borderStyleBottomType = EnumBorderStyle.dotted;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("dashed")) {
                    borderStyleBottomType = EnumBorderStyle.dashed;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("solid")) {
                    borderStyleBottomType = EnumBorderStyle.solid;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("double")) {
                    borderStyleBottomType = EnumBorderStyle.prefix_double;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("groove")) {
                    borderStyleBottomType = EnumBorderStyle.groove;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("ridge")) {
                    borderStyleBottomType = EnumBorderStyle.ridge;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inset")) {
                    borderStyleBottomType = EnumBorderStyle.inset;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("outset")) {
                    borderStyleBottomType = EnumBorderStyle.outset;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    borderStyleBottomType = EnumBorderStyle.inherit;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderLeftStyle(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("none")) {
                    borderStyleLeftType = EnumBorderStyle.none;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("hidden")) {
                    borderStyleLeftType = EnumBorderStyle.hidden;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("dotted")) {
                    borderStyleLeftType = EnumBorderStyle.dotted;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("dashed")) {
                    borderStyleLeftType = EnumBorderStyle.dashed;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("solid")) {
                    borderStyleLeftType = EnumBorderStyle.solid;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("double")) {
                    borderStyleLeftType = EnumBorderStyle.prefix_double;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("groove")) {
                    borderStyleLeftType = EnumBorderStyle.groove;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("ridge")) {
                    borderStyleLeftType = EnumBorderStyle.ridge;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inset")) {
                    borderStyleLeftType = EnumBorderStyle.inset;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("outset")) {
                    borderStyleLeftType = EnumBorderStyle.outset;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    borderStyleLeftType = EnumBorderStyle.inherit;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderStyle(Declaration d) {
        NodeData trans = beginTransaction();
        if(d.getTermsList().size() == 1) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-style");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-style");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-style");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-style");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(0));
            
            if(processBorderTopStyle(tmpDeclarationTop) && processBorderRightStyle(tmpDeclarationRight) &&
               processBorderBottomStyle(tmpDeclarationBottom) && processBorderLeftStyle(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTermsList().size() == 2) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-style");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-style");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-style");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-style");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(1));
            
            if(processBorderTopStyle(tmpDeclarationTop) && processBorderRightStyle(tmpDeclarationRight) &&
               processBorderBottomStyle(tmpDeclarationBottom) && processBorderLeftStyle(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTermsList().size() == 3) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-style");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-style");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-style");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-style");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(1));
            
            if(processBorderTopStyle(tmpDeclarationTop) && processBorderRightStyle(tmpDeclarationRight) &&
               processBorderBottomStyle(tmpDeclarationBottom) && processBorderLeftStyle(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTermsList().size() == 4) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-style");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-style");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-style");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-style");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(3));
            
            if(processBorderTopStyle(tmpDeclarationTop) && processBorderRightStyle(tmpDeclarationRight) &&
               processBorderBottomStyle(tmpDeclarationBottom) && processBorderLeftStyle(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderTopWidth(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    borderWidthTopValue = num;
                    borderWidthTopType = EnumBorderWidth.length;
                    return new Boolean(true);
                }
            }
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("thin")) {
                    borderWidthTopType = EnumBorderWidth.thin;
                    borderWidthTopValue = null;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("medium")) {
                    borderWidthTopType = EnumBorderWidth.medium;
                    borderWidthTopValue = null;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("thick")) {
                    borderWidthTopType = EnumBorderWidth.thick;
                    borderWidthTopValue = null;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    borderWidthTopType = EnumBorderWidth.inherit;
                    borderWidthTopValue = null;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderRightWidth(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    borderWidthRightValue = num;
                    borderWidthRightType = EnumBorderWidth.length;
                    return new Boolean(true);
                }
            }
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("thin")) {
                    borderWidthRightType = EnumBorderWidth.thin;
                    borderWidthRightValue = null;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("medium")) {
                    borderWidthRightType = EnumBorderWidth.medium;
                    borderWidthRightValue = null;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("thick")) {
                    borderWidthRightType = EnumBorderWidth.thick;
                    borderWidthRightValue = null;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    borderWidthRightType = EnumBorderWidth.inherit;
                    borderWidthRightValue = null;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderBottomWidth(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    borderWidthBottomValue = num;
                    borderWidthBottomType = EnumBorderWidth.length;
                    return new Boolean(true);
                }
            }
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("thin")) {
                    borderWidthBottomType = EnumBorderWidth.thin;
                    borderWidthBottomValue = null;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("medium")) {
                    borderWidthBottomType = EnumBorderWidth.medium;
                    borderWidthBottomValue = null;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("thick")) {
                    borderWidthBottomType = EnumBorderWidth.thick;
                    borderWidthBottomValue = null;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    borderWidthBottomType = EnumBorderWidth.inherit;
                    borderWidthBottomValue = null;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderLeftWidth(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    borderWidthLeftValue = num;
                    borderWidthLeftType = EnumBorderWidth.length;
                    return new Boolean(true);
                }
            }
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("thin")) {
                    borderWidthLeftType = EnumBorderWidth.thin;
                    borderWidthLeftValue = null;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("medium")) {
                    borderWidthLeftType = EnumBorderWidth.medium;
                    borderWidthLeftValue = null;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("thick")) {
                    borderWidthLeftType = EnumBorderWidth.thick;
                    borderWidthLeftValue = null;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    borderWidthLeftType = EnumBorderWidth.inherit;
                    borderWidthLeftValue = null;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderWidth(Declaration d) {
        NodeData trans = beginTransaction();
        if(d.getTermsList().size() == 1) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-width");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-width");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-width");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-width");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(0));
            
            if(processBorderTopWidth(tmpDeclarationTop) && processBorderRightWidth(tmpDeclarationRight) &&
               processBorderBottomWidth(tmpDeclarationBottom) && processBorderLeftWidth(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTermsList().size() == 2) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-width");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-width");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-width");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-width");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(1));
            
            if(processBorderTopWidth(tmpDeclarationTop) && processBorderRightWidth(tmpDeclarationRight) &&
               processBorderBottomWidth(tmpDeclarationBottom) && processBorderLeftWidth(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTermsList().size() == 3) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-width");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-width");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-width");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-width");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(1));
            
            if(processBorderTopWidth(tmpDeclarationTop) && processBorderRightWidth(tmpDeclarationRight) &&
               processBorderBottomWidth(tmpDeclarationBottom) && processBorderLeftWidth(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTermsList().size() == 4) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-width");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-width");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-width");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-width");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(3));
            
            if(processBorderTopWidth(tmpDeclarationTop) && processBorderRightWidth(tmpDeclarationRight) &&
               processBorderBottomWidth(tmpDeclarationBottom) && processBorderLeftWidth(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processBorderTop(Declaration d) {
        NodeData trans = beginTransaction();
        //Nastavení na výchozí hodnoty
        borderColorTopType = null;
        borderColorTopValue = null;
        borderStyleTopType = null;
        borderWidthTopType = null;
        borderWidthTopValue = null;
        
        //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné deklaraci
        //objevila například 2x barva. K určení slouží následující proměnné
        boolean processedColor = false;
        boolean processedStyle = false;
        boolean processedWidth = false;
        
        for(Term t : d.getTermsList()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTermsList().size() == 1) {
                    borderColorTopType = EnumColorTransparent.inherit;
                    borderColorTopValue = null;
                    borderStyleTopType = EnumBorderStyle.inherit;
                    borderWidthTopType = EnumBorderWidth.inherit;
                    borderWidthTopValue = null;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten aktuální)
            //a v jednotlivých blocích se pokouším tuto deklaraci zpracovat. 
            Declaration tmpDeclaration = new DataDeclaration("border-top");
            tmpDeclaration.getTermsList().add(t);
            
            //Vyzkouším, jestli se jedná o barvu
            tmpDeclaration.setProperty("border-top-color");
            if(processBorderTopColor(tmpDeclaration)) {
                //Jedná se o barvu. Zjistím, jestli barva už nebyla jednou zadána
                if(processedColor) {
                    //Barva už byla jednou zadáno, deklarace je chybná, rollback a konec
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    //Barva ještě nebyla zadána, pokračujeme dalším termem
                    processedColor = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-top-style");
            if(processBorderTopStyle(tmpDeclaration)) {
                if(processedStyle) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedStyle = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-top-width");
            if(processBorderTopWidth(tmpDeclaration)) {
                if(processedWidth) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedWidth = true;
                    continue;
                }
            }
            
            //Pokud program dojde sem, znamená to že term není ani color, style nebo width - ignorace celé deklarace
            rollbackTransaction(trans);
            return false;
        }
        return true;
    }
    
    private Boolean processBorderRight(Declaration d) {
        NodeData trans = beginTransaction();
        //Nastavení na výchozí hodnoty
        borderColorRightType = null;
        borderColorRightValue = null;
        borderStyleRightType = null;
        borderWidthRightType = null;
        borderWidthRightValue = null;
        
        //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné deklaraci
        //objevila například 2x barva. K určení slouží následující proměnné
        boolean processedColor = false;
        boolean processedStyle = false;
        boolean processedWidth = false;
        
        for(Term t : d.getTermsList()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTermsList().size() == 1) {
                    borderColorRightType = EnumColorTransparent.inherit;
                    borderColorRightValue = null;
                    borderStyleRightType = EnumBorderStyle.inherit;
                    borderWidthRightType = EnumBorderWidth.inherit;
                    borderWidthRightValue = null;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten aktuální)
            //a v jednotlivých blocích se pokouším tuto deklaraci zpracovat. 
            Declaration tmpDeclaration = new DataDeclaration("border-right");
            tmpDeclaration.getTermsList().add(t);
            
            //Vyzkouším, jestli se jedná o barvu
            tmpDeclaration.setProperty("border-right-color");
            if(processBorderRightColor(tmpDeclaration)) {
                //Jedná se o barvu. Zjistím, jestli barva už nebyla jednou zadána
                if(processedColor) {
                    //Barva už byla jednou zadáno, deklarace je chybná, rollback a konec
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    //Barva ještě nebyla zadána, pokračujeme dalším termem
                    processedColor = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-right-style");
            if(processBorderRightStyle(tmpDeclaration)) {
                if(processedStyle) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedStyle = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-right-width");
            if(processBorderRightWidth(tmpDeclaration)) {
                if(processedWidth) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedWidth = true;
                    continue;
                }
            }
            
            //Pokud program dojde sem, znamená to že term není ani color, style nebo width - ignorace celé deklarace
            rollbackTransaction(trans);
            return false;
        }
        return true;
    }
    
    private Boolean processBorderBottom(Declaration d) {
        NodeData trans = beginTransaction();
        //Nastavení na výchozí hodnoty
        borderColorBottomType = null;
        borderColorBottomValue = null;
        borderStyleBottomType = null;
        borderWidthBottomType = null;
        borderWidthBottomValue = null;
        
        //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné deklaraci
        //objevila například 2x barva. K určení slouží následující proměnné
        boolean processedColor = false;
        boolean processedStyle = false;
        boolean processedWidth = false;
        
        for(Term t : d.getTermsList()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTermsList().size() == 1) {
                    borderColorBottomType = EnumColorTransparent.inherit;
                    borderColorBottomValue = null;
                    borderStyleBottomType = EnumBorderStyle.inherit;
                    borderWidthBottomType = EnumBorderWidth.inherit;
                    borderWidthBottomValue = null;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten aktuální)
            //a v jednotlivých blocích se pokouším tuto deklaraci zpracovat. 
            Declaration tmpDeclaration = new DataDeclaration("border-bottom");
            tmpDeclaration.getTermsList().add(t);
            
            //Vyzkouším, jestli se jedná o barvu
            tmpDeclaration.setProperty("border-bottom-color");
            if(processBorderBottomColor(tmpDeclaration)) {
                //Jedná se o barvu. Zjistím, jestli barva už nebyla jednou zadána
                if(processedColor) {
                    //Barva už byla jednou zadáno, deklarace je chybná, rollback a konec
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    //Barva ještě nebyla zadána, pokračujeme dalším termem
                    processedColor = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-bottom-style");
            if(processBorderBottomStyle(tmpDeclaration)) {
                if(processedStyle) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedStyle = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-bottom-width");
            if(processBorderBottomWidth(tmpDeclaration)) {
                if(processedWidth) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedWidth = true;
                    continue;
                }
            }
            
            //Pokud program dojde sem, znamená to že term není ani color, style nebo width - ignorace celé deklarace
            rollbackTransaction(trans);
            return false;
        }
        return true;
    }
    
    private Boolean processBorderLeft(Declaration d) {
        NodeData trans = beginTransaction();
        //Nastavení na výchozí hodnoty
        borderColorLeftType = null;
        borderColorLeftValue = null;
        borderStyleLeftType = null;
        borderWidthLeftType = null;
        borderWidthLeftValue = null;
        
        //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné deklaraci
        //objevila například 2x barva. K určení slouží následující proměnné
        boolean processedColor = false;
        boolean processedStyle = false;
        boolean processedWidth = false;
        
        for(Term t : d.getTermsList()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTermsList().size() == 1) {
                    borderColorLeftType = EnumColorTransparent.inherit;
                    borderColorLeftValue = null;
                    borderStyleLeftType = EnumBorderStyle.inherit;
                    borderWidthLeftType = EnumBorderWidth.inherit;
                    borderWidthLeftValue = null;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten aktuální)
            //a v jednotlivých blocích se pokouším tuto deklaraci zpracovat. 
            Declaration tmpDeclaration = new DataDeclaration("border-left");
            tmpDeclaration.getTermsList().add(t);
            
            //Vyzkouším, jestli se jedná o barvu
            tmpDeclaration.setProperty("border-left-color");
            if(processBorderLeftColor(tmpDeclaration)) {
                //Jedná se o barvu. Zjistím, jestli barva už nebyla jednou zadána
                if(processedColor) {
                    //Barva už byla jednou zadáno, deklarace je chybná, rollback a konec
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    //Barva ještě nebyla zadána, pokračujeme dalším termem
                    processedColor = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-left-style");
            if(processBorderLeftStyle(tmpDeclaration)) {
                if(processedStyle) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedStyle = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-left-width");
            if(processBorderLeftWidth(tmpDeclaration)) {
                if(processedWidth) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedWidth = true;
                    continue;
                }
            }
            
            //Pokud program dojde sem, znamená to že term není ani color, style nebo width - ignorace celé deklarace
            rollbackTransaction(trans);
            return false;
        }
        return true;
    }
    
    private Boolean processBorder(Declaration d) {
        NodeData trans = beginTransaction();
        //Nastavení na výchozí hodnoty
        borderColorTopType = null;
        borderColorRightType = null;
        borderColorBottomType = null;
        borderColorLeftType = null;
        borderColorTopValue = null;
        borderColorRightValue = null;
        borderColorBottomValue = null;
        borderColorLeftValue = null;
        borderStyleTopType = null;
        borderStyleRightType = null;
        borderStyleBottomType = null;
        borderStyleLeftType = null;
        borderWidthTopType = null;
        borderWidthRightType = null;
        borderWidthBottomType = null;
        borderWidthLeftType = null;
        borderWidthTopValue = null;
        borderWidthRightValue = null;
        borderWidthBottomValue = null;
        borderWidthLeftValue = null; 
        
        //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné deklaraci
        //objevila například 2x barva. K určení slouží následující proměnné
        boolean processedColor = false;
        boolean processedStyle = false;
        boolean processedWidth = false;
        
        for(Term t : d.getTermsList()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTermsList().size() == 1) {
                    borderColorTopType = EnumColorTransparent.inherit;
                    borderColorRightType = EnumColorTransparent.inherit;
                    borderColorBottomType = EnumColorTransparent.inherit;
                    borderColorLeftType = EnumColorTransparent.inherit;
                    borderColorTopValue = null;
                    borderColorRightValue = null;
                    borderColorBottomValue = null;
                    borderColorLeftValue = null;
                    borderStyleTopType = EnumBorderStyle.inherit;
                    borderStyleRightType = EnumBorderStyle.inherit;
                    borderStyleBottomType = EnumBorderStyle.inherit;
                    borderStyleLeftType = EnumBorderStyle.inherit;
                    borderWidthTopType = EnumBorderWidth.inherit;
                    borderWidthRightType = EnumBorderWidth.inherit;
                    borderWidthBottomType = EnumBorderWidth.inherit;
                    borderWidthLeftType = EnumBorderWidth.inherit;
                    borderWidthTopValue = null;
                    borderWidthRightValue = null;
                    borderWidthBottomValue = null;
                    borderWidthLeftValue = null; 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten aktuální)
            //a v jednotlivých blocích se pokouším tuto deklaraci zpracovat. 
            Declaration tmpDeclaration = new DataDeclaration("border");
            tmpDeclaration.getTermsList().add(t);
            
            //Vyzkouším, jestli se jedná o barvu
            tmpDeclaration.setProperty("border-color");
            if(processBorderColor(tmpDeclaration)) {
                //Jedná se o barvu. Zjistím, jestli barva už nebyla jednou zadána
                if(processedColor) {
                    //Barva už byla jednou zadáno, deklarace je chybná, rollback a konec
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    //Barva ještě nebyla zadána, pokračujeme dalším termem
                    processedColor = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-style");
            if(processBorderStyle(tmpDeclaration)) {
                if(processedStyle) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedStyle = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("border-width");
            if(processBorderWidth(tmpDeclaration)) {
                if(processedWidth) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedWidth = true;
                    continue;
                }
            }
            
            //Pokud program dojde sem, znamená to že term není ani color, style nebo width - ignorace celé deklarace
            rollbackTransaction(trans);
            return false;
        }
        return true;
    }
    
    private Boolean processFontFamily(Declaration d) {
        NodeData trans = beginTransaction();
        ArrayList<String>input = new ArrayList<String>();
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    fontFamilyType = EnumFontFamily.inherit;
                    fontFamilyValues.clear();
                    return true;
                }
            }
        }
        
        for(Term t : d.getTermsList()) {
            if(t instanceof TermIdent) {
                String ident = ((TermIdent)t).getValue();
                fontFamilyType = EnumFontFamily.font;
                if(t.getOperator() == Term.EnumOperator.space && !input.isEmpty()) {
                    String tmp = input.get(input.size()-1);
                    tmp = tmp + " " + ident;
                    input.remove(input.size()-1);
                    input.add(tmp);
                }
                else {
                    input.add(ident);
                }
            }
            else if(t instanceof TermString) {
                String ident = ((TermString)t).getValue();
                fontFamilyType = EnumFontFamily.font;
                input.add(ident);
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        fontFamilyValues.clear();
        fontFamilyValues.addAll(input);
        return true;
    }
    
    private Boolean processFontSize(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("xx-small")) {
                    fontSizeType = EnumFontSize.xx_small;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("x-small")) {
                    fontSizeType = EnumFontSize.x_small;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("small")) {
                    fontSizeType = EnumFontSize.small;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("medium")) {
                    fontSizeType = EnumFontSize.medium;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("large")) {
                    fontSizeType = EnumFontSize.large;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("x-large")) {
                    fontSizeType = EnumFontSize.x_large;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("xx-large")) {
                    fontSizeType = EnumFontSize.xx_large;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("larger")) {
                    fontSizeType = EnumFontSize.larger;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("smaller")) {
                    fontSizeType = EnumFontSize.smaller;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    fontSizeType = EnumFontSize.inherit;
                    fontSizeNumberValue = null;
                    fontSizePercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    if(num.getValue().floatValue() < 0) {
                        num.setValue(new Float(0));
                    }
                    fontSizeType = EnumFontSize.length;
                    fontSizeNumberValue = num;
                    fontSizePercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                if(percentage.getValue().floatValue() < 0) {
                    percentage.setValue(new Float(0));
                }
                fontSizeType = EnumFontSize.percentage;
                fontSizeNumberValue = null;
                fontSizePercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processFontStyle(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("normal")) {
                    fontStyleType = EnumFontStyle.normal;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("italic")) {
                    fontStyleType = EnumFontStyle.italic;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("oblique")) {
                    fontStyleType = EnumFontStyle.oblique;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    fontStyleType = EnumFontStyle.inherit;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processFontVariant(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("normal")) {
                    fontVariantType = EnumFontVariant.normal;
                    return true;
                }
                if(ident.equalsIgnoreCase("small-caps")) {
                    fontVariantType = EnumFontVariant.small_caps;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    fontVariantType = EnumFontVariant.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processFontWeight(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("normal")) {
                    fontWeightType = EnumFontWeight.normal;
                    return true;
                }
                if(ident.equalsIgnoreCase("bold")) {
                    fontWeightType = EnumFontWeight.bold;
                    return true;
                }
                if(ident.equalsIgnoreCase("bolder")) {
                    fontWeightType = EnumFontWeight.bolder;
                    return true;
                }
                if(ident.equalsIgnoreCase("lighter")) {
                    fontWeightType = EnumFontWeight.lighter;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    fontWeightType = EnumFontWeight.inherit;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.getUnit() == null) {
                    if(num.getValue() == 100.0) {
                        fontWeightType = EnumFontWeight.prefix_100;
                        return true;
                    }
                    if(num.getValue() == 200.0) {
                        fontWeightType = EnumFontWeight.prefix_200;
                        return true;
                    }
                    if(num.getValue() == 300.0) {
                        fontWeightType = EnumFontWeight.prefix_300;
                        return true;
                    }
                    if(num.getValue() == 400.0) {
                        fontWeightType = EnumFontWeight.prefix_400;
                        return true;
                    }
                    if(num.getValue() == 500.0) {
                        fontWeightType = EnumFontWeight.prefix_500;
                        return true;
                    }
                    if(num.getValue() == 600.0) {
                        fontWeightType = EnumFontWeight.prefix_600;
                        return true;
                    }
                    if(num.getValue() == 700.0) {
                        fontWeightType = EnumFontWeight.prefix_700;
                        return true;
                    }
                    if(num.getValue() == 800.0) {
                        fontWeightType = EnumFontWeight.prefix_800;
                        return true;
                    }
                    if(num.getValue() == 900.0) {
                        fontWeightType = EnumFontWeight.prefix_900;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private Boolean processFont(Declaration d) {
        NodeData trans = beginTransaction();
        //Hodnoty jsou děděné (inherited) - Nastavení na výchozí hodnoty
        fontStyleType = EnumFontStyle.normal;
        fontFamilyType = EnumFontFamily.font;
        fontFamilyValues.clear();
        fontSizeType = EnumFontSize.medium;
        fontSizePercentValue = null;
        fontSizeNumberValue = null;
        fontVariantType = EnumFontVariant.normal;
        fontWeightType = EnumFontWeight.normal;
        lineHeightType = EnumLineHeight.normal;
        lineHeightNumberValue = null;
        lineHeightPercentValue = null;
        
        //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné deklaraci
        //objevila například 2x barva. K určení slouží následující proměnné
        boolean processedStyle = false;
        boolean processedVariant = false;
        boolean processedWeight = false;
        boolean processedFontSize = false;
        boolean processedLineHeight = false;
        int count = 0;
        
        //Sem se budou kládat všechny hodnoty na konci (pravděpodobně se bude jednat o hodnoty font-family)
        Declaration fontFamilyDeclaration = new DataDeclaration("font-family");
        
        for(Term t : d.getTermsList()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTermsList().size() == 1) {
                    fontStyleType = EnumFontStyle.inherit;
                    fontFamilyType = EnumFontFamily.inherit;
                    fontFamilyValues.clear();
                    fontSizeType = EnumFontSize.inherit;
                    fontSizePercentValue = null;
                    fontSizeNumberValue = null;
                    fontVariantType = EnumFontVariant.inherit;
                    fontWeightType = EnumFontWeight.inherit; 
                    lineHeightType = EnumLineHeight.inherit;
                    lineHeightNumberValue = null;
                    lineHeightPercentValue = null;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("caption")) {
                if(d.getTermsList().size() == 1) {
                    //neimplementováno 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("icon")) {
                if(d.getTermsList().size() == 1) {
                    //neimplementováno 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("menu")) {
                if(d.getTermsList().size() == 1) {
                    //neimplementováno 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("message-box")) {
                if(d.getTermsList().size() == 1) {
                    //neimplementováno 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("small-caption")) {
                if(d.getTermsList().size() == 1) {
                    //neimplementováno 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("status-bar")) {
                if(d.getTermsList().size() == 1) {
                    //neimplementováno 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten aktuální)
            //a v jednotlivých blocích se pokouším tuto deklaraci zpracovat. 
            Declaration tmpDeclaration = new DataDeclaration("font");
            tmpDeclaration.getTermsList().add(t);
            
            if(!processedFontSize && count < 3) {
                if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("normal")) {
                    //U normal není jasné o co se jedná. Ale je to výchozí hodnota, lze jí ignorovat
                    count++;
                    continue;
                }

                //Vyzkouším, jestli se jedná o barvu
                tmpDeclaration.setProperty("font-style");
                if(processFontStyle(tmpDeclaration)) {
                    //Jedná se o barvu. Zjistím, jestli barva už nebyla jednou zadána
                    if(processedStyle) {
                        //Barva už byla jednou zadáno, deklarace je chybná, rollback a konec
                        rollbackTransaction(trans);
                        return false;
                    }
                    else {
                        //Barva ještě nebyla zadána, pokračujeme dalším termem
                        processedStyle = true;
                        count++;
                        continue;
                    }
                }
                tmpDeclaration.setProperty("font-variant");
                if(processFontVariant(tmpDeclaration)) {
                    if(processedVariant) {
                        rollbackTransaction(trans);
                        return false;
                    }
                    else {
                        processedVariant = true;
                        count++;
                        continue;
                    }
                }
                tmpDeclaration.setProperty("font-weight");
                if(processFontWeight(tmpDeclaration)) {
                    if(processedWeight) {
                        rollbackTransaction(trans);
                        return false;
                    }
                    else {
                        processedWeight = true;
                        count++;
                        continue;
                    }
                }
            }
            
            //V tomto místě musí být deklarováno bezpodmínečně font-size
            if(!processedFontSize) {
                tmpDeclaration.setProperty("font-size");
                if(processFontSize(tmpDeclaration)) {
                    processedFontSize = true;
                    continue;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            if(!processedLineHeight && t.getOperator() == Term.EnumOperator.slash) {
                tmpDeclaration.setProperty("line-height");
                if(processLineHeight(tmpDeclaration)) {
                    processedLineHeight = true;
                    continue;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vše co neprojde předchozím
            fontFamilyDeclaration.getTermsList().add(t);
        }
        
        //Font family musí být zadáno
        if(fontFamilyDeclaration.getTermsList().isEmpty()) {
            rollbackTransaction(trans);
            return false;
        }
        
        if(processFontFamily(fontFamilyDeclaration)) {
            return true;
        }
        else {
            rollbackTransaction(trans);
            return false;
        }
    }
    
    private Boolean processLineHeight(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("normal")) {
                    lineHeightType = EnumLineHeight.normal;
                    lineHeightNumberValue = null;
                    lineHeightPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    lineHeightType = EnumLineHeight.inherit;
                    lineHeightNumberValue = null;
                    lineHeightPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    if(num.getValue().floatValue() < 0) {
                        num.setValue(new Float(0));
                    }
                    lineHeightType = EnumLineHeight.length;
                    lineHeightNumberValue = num;
                    lineHeightPercentValue = null;
                    return true;
                }
                if(num.isNumber()) {
                    if(num.getValue().floatValue() < 0) {
                        num.setValue(new Float(0));
                    }
                    lineHeightType = EnumLineHeight.number;
                    lineHeightNumberValue = num;
                    lineHeightPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                if(percentage.getValue().floatValue() < 0) {
                    percentage.setValue(new Float(0));
                }
                lineHeightType = EnumLineHeight.percentage;
                lineHeightNumberValue = null;
                lineHeightPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processTop(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    topType = EnumSize.auto;
                    topNumberValue = null;
                    topPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    topType = EnumSize.inherit;
                    topNumberValue = null;
                    topPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    topType = EnumSize.length;
                    topNumberValue = num;
                    topPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                topType = EnumSize.percentage;
                topNumberValue = null;
                topPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processRight(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    rightType = EnumSize.auto;
                    rightNumberValue = null;
                    rightPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    rightType = EnumSize.inherit;
                    rightNumberValue = null;
                    rightPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    rightType = EnumSize.length;
                    rightNumberValue = num;
                    rightPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                rightType = EnumSize.percentage;
                rightNumberValue = null;
                rightPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processBottom(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    bottomType = EnumSize.auto;
                    bottomNumberValue = null;
                    bottomPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    bottomType = EnumSize.inherit;
                    bottomNumberValue = null;
                    bottomPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    bottomType = EnumSize.length;
                    bottomNumberValue = num;
                    bottomPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                bottomType = EnumSize.percentage;
                bottomNumberValue = null;
                bottomPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processLeft(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    leftType = EnumSize.auto;
                    leftNumberValue = null;
                    leftPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    leftType = EnumSize.inherit;
                    leftNumberValue = null;
                    leftPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    leftType = EnumSize.length;
                    leftNumberValue = num;
                    leftPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                leftType = EnumSize.percentage;
                leftNumberValue = null;
                leftPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processWidth(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    widthType = EnumSize.auto;
                    widthNumberValue = null;
                    widthPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    widthType = EnumSize.inherit;
                    widthNumberValue = null;
                    widthPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    widthType = EnumSize.length;
                    widthNumberValue = num;
                    widthPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                widthType = EnumSize.percentage;
                widthNumberValue = null;
                widthPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processHeight(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    heightType = EnumSize.auto;
                    heightNumberValue = null;
                    heightPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    heightType = EnumSize.inherit;
                    heightNumberValue = null;
                    heightPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    heightType = EnumSize.length;
                    heightNumberValue = num;
                    heightPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                heightType = EnumSize.percentage;
                heightNumberValue = null;
                heightPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processCaptionSide(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("top")) {
                    captionSideType = EnumCaptionSide.top;
                    return true;
                }
                if(ident.equalsIgnoreCase("bottom")) {
                    captionSideType = EnumCaptionSide.bottom;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    captionSideType = EnumCaptionSide.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processClear(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("none")) {
                    clearType = EnumClear.none;
                    return true;
                }
                if(ident.equalsIgnoreCase("left")) {
                    clearType = EnumClear.left;
                    return true;
                }
                if(ident.equalsIgnoreCase("right")) {
                    clearType = EnumClear.right;
                    return true;
                }
                if(ident.equalsIgnoreCase("both")) {
                    clearType = EnumClear.both;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    clearType = EnumClear.both;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processClip(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    clipTopType = EnumClip.auto;
                    clipRightType = EnumClip.auto;
                    clipBottomType = EnumClip.auto;
                    clipLeftType = EnumClip.auto;
                    clipTopNumberValue = null;
                    clipRightNumberValue = null;
                    clipBottomNumberValue = null;
                    clipLeftNumberValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    clipTopType = EnumClip.inherit;
                    clipRightType = EnumClip.inherit;
                    clipBottomType = EnumClip.inherit;
                    clipLeftType = EnumClip.inherit;
                    clipTopNumberValue = null;
                    clipRightNumberValue = null;
                    clipBottomNumberValue = null;
                    clipLeftNumberValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermFunction) {
                TermFunction f = (TermFunction)d.getTermsList().get(0);
                if(f.getFunctionName().equalsIgnoreCase("rect") && f.getTermsList().size() == 4) {
                    NodeData trans = beginTransaction();
                    int index = 0;
                    for(Term t : f.getTermsList()) {
                        if(t instanceof TermIdent) {
                            String ident = ((TermIdent)t).getValue();
                            if(ident.equalsIgnoreCase("auto")) {
                                switch (index) {
                                    case 0: 
                                        clipTopType = EnumClip.auto;
                                        clipTopNumberValue = null;
                                        break;
                                    case 1: 
                                        clipRightType = EnumClip.auto;
                                        clipRightNumberValue = null;
                                        break;
                                    case 2: 
                                        clipBottomType = EnumClip.auto;
                                        clipBottomNumberValue = null;
                                        break;
                                    case 3: 
                                        clipLeftType = EnumClip.auto;
                                        clipLeftNumberValue = null;
                                        break;    
                                }
                                index++;
                                continue;
                            }
                        }
                        if(t instanceof TermNumber) {
                            TermNumber num = (TermNumber)t;
                            if(num.isLength()) {
                                switch (index) {
                                    case 0: 
                                        clipTopType = EnumClip.length;
                                        clipTopNumberValue = num;
                                        break;
                                    case 1: 
                                        clipRightType = EnumClip.length;
                                        clipRightNumberValue = num;
                                        break;
                                    case 2: 
                                        clipBottomType = EnumClip.length;
                                        clipBottomNumberValue = num;
                                        break;
                                    case 3: 
                                        clipLeftType = EnumClip.length;
                                        clipLeftNumberValue = num;
                                        break;    
                                }
                                index++;
                                continue;
                            }
                        }
                        
                        rollbackTransaction(trans);
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processColor(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermColor) {
                TermColor color = (TermColor)d.getTermsList().get(0);
                colorValue = color;
                colorType = EnumColor.color;
                return true;
                
            }
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    colorType = EnumColor.inherit;
                    colorValue = null;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processCounterIncrement(Declaration d) {
        HashMap<String, Integer> out = new HashMap<String, Integer>();
        String lastIdent = null;
        
        for(Term t : d.getTermsList()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTermsList().size() == 1) {
                    counterIncrementType = EnumCounter.inherit;
                    counterIncrementValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
                if(d.getTermsList().size() == 1) {
                    counterIncrementType = EnumCounter.none;
                    counterIncrementValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            
            if((t instanceof TermIdent)) {
                out.put(((TermIdent)t).getValue(), new Integer(1));
                lastIdent = ((TermIdent)t).getValue();
                continue;
            }
            if((t instanceof TermNumber)) {
                TermNumber num = (TermNumber)t;
                if(num.isInteger() && lastIdent != null) {
                    out.put(lastIdent, num.getValue().intValue());
                    lastIdent = null;
                    continue;
                }
            }
            return false;
        }
        
        if(!out.isEmpty()) {
            counterIncrementType = EnumCounter.table_values;
            counterIncrementValues.clear();
            counterIncrementValues.putAll(out);
            return true;
        }
        else {
            return false;
        }
    }
    
    private Boolean processCounterReset(Declaration d) {
        HashMap<String, Integer> out = new HashMap<String, Integer>();
        String lastIdent = null;
        
        for(Term t : d.getTermsList()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTermsList().size() == 1) {
                    counterResetType = EnumCounter.inherit;
                    counterResetValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
                if(d.getTermsList().size() == 1) {
                    counterResetType = EnumCounter.none;
                    counterResetValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            
            if((t instanceof TermIdent)) {
                out.put(((TermIdent)t).getValue(), new Integer(1));
                lastIdent = ((TermIdent)t).getValue();
                continue;
            }
            if((t instanceof TermNumber)) {
                TermNumber num = (TermNumber)t;
                if(num.isInteger() && lastIdent != null) {
                    out.put(lastIdent, num.getValue().intValue());
                    lastIdent = null;
                    continue;
                }
            }
            return false;
        }
        
        if(!out.isEmpty()) {
            counterResetType = EnumCounter.table_values;
            counterResetValues.clear();
            counterResetValues.putAll(out);
            return true;
        }
        else {
            return false;
        }
    }
            
    private Boolean processCursor(Declaration d) {
        boolean processedGeneric = false;
        ArrayList<TermUri> out = new ArrayList<TermUri>();
        
        int index = 0;
        for(Term t : d.getTermsList()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTermsList().size() == 1) {
                    cursorType = EnumCursor.inherit;
                    cursorUri.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("auto")) {
                if(d.getTermsList().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.auto;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("crosshair")) {
                if(d.getTermsList().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.crosshair;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("default")) {
                if(d.getTermsList().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.prefix_default;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("pointer")) {
                if(d.getTermsList().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.pointer;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("move")) {
                if(d.getTermsList().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.move;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("e-resize")) {
                if(d.getTermsList().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.e_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("ne-resize")) {
                if(d.getTermsList().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.ne_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("nw-resize")) {
                if(d.getTermsList().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.nw_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("n-resize")) {
                if(d.getTermsList().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.n_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("se-resize")) {
                if(d.getTermsList().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.se_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("sw-resize")) {
                if(d.getTermsList().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.sw_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("s-resize")) {
                if(d.getTermsList().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.s_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("w-resize")) {
                if(d.getTermsList().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.w_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("text")) {
                if(d.getTermsList().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.text;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("wait")) {
                if(d.getTermsList().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.wait;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("help")) {
                if(d.getTermsList().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.help;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("progress")) {
                if(d.getTermsList().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.progress;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if(t instanceof TermUri) {
                index++;
                out.add((TermUri)t);
                continue;
            }
            
            return false;
        }
        
        if(processedGeneric) {
            cursorUri.clear();
            cursorUri.addAll(out);
            return true;
        }
        else {
            return false;
        }
    }
    
    private Boolean processDirection(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("ltr")) {
                    directionType = EnumDirection.ltr;
                    return true;
                }
                if(ident.equalsIgnoreCase("rtl")) {
                    directionType = EnumDirection.rtl;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    directionType = EnumDirection.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processDisplay(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("inline")) {
                    displayType = EnumDisplay.inline;
                    return true;
                }
                if(ident.equalsIgnoreCase("block")) {
                    displayType = EnumDisplay.block;
                    return true;
                }
                if(ident.equalsIgnoreCase("list-item")) {
                    displayType = EnumDisplay.list_item;
                    return true;
                }
                if(ident.equalsIgnoreCase("run-in")) {
                    displayType = EnumDisplay.run_in;
                    return true;
                }
                if(ident.equalsIgnoreCase("inline-block")) {
                    displayType = EnumDisplay.inline_block;
                    return true;
                }
                if(ident.equalsIgnoreCase("table")) {
                    displayType = EnumDisplay.table;
                    return true;
                }
                if(ident.equalsIgnoreCase("inline-table")) {
                    displayType = EnumDisplay.inline_table;
                    return true;
                }
                if(ident.equalsIgnoreCase("table-row-group")) {
                    displayType = EnumDisplay.table_row_group;
                    return true;
                }
                if(ident.equalsIgnoreCase("table-header-group")) {
                    displayType = EnumDisplay.table_header_group;
                    return true;
                }
                if(ident.equalsIgnoreCase("table-footer-group")) {
                    displayType = EnumDisplay.table_footer_group;
                    return true;
                }
                if(ident.equalsIgnoreCase("table-row")) {
                    displayType = EnumDisplay.table_row;
                    return true;
                }
                if(ident.equalsIgnoreCase("table-column-group")) {
                    displayType = EnumDisplay.table_column_group;
                    return true;
                }
                if(ident.equalsIgnoreCase("table-column")) {
                    displayType = EnumDisplay.table_column;
                    return true;
                }
                if(ident.equalsIgnoreCase("table-cell")) {
                    displayType = EnumDisplay.table_cell;
                    return true;
                }
                if(ident.equalsIgnoreCase("table-caption")) {
                    displayType = EnumDisplay.table_caption;
                    return true;
                }
                if(ident.equalsIgnoreCase("none")) {
                    displayType = EnumDisplay.none;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    displayType = EnumDisplay.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processEmptyCells(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("show")) {
                    emptyCellsType = EnumEmptyCells.show;
                    return true;
                }
                if(ident.equalsIgnoreCase("hide")) {
                    emptyCellsType = EnumEmptyCells.hide;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    emptyCellsType = EnumEmptyCells.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processFloat(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("none")) {
                    floatType = EnumFloat.none;
                    return true;
                }
                if(ident.equalsIgnoreCase("left")) {
                    floatType = EnumFloat.left;
                    return true;
                }
                if(ident.equalsIgnoreCase("right")) {
                    floatType = EnumFloat.right;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    floatType = EnumFloat.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processListStyleImage(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("none")) {
                    listStyleImageType = EnumListStyleImage.none;
                    listStyleImageValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    listStyleImageType = EnumListStyleImage.inherit;
                    listStyleImageValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermUri) {
                TermUri uri = (TermUri)d.getTermsList().get(0);
                listStyleImageType = EnumListStyleImage.uri;
                listStyleImageValue = uri;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processListStylePosition(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("inside")) {
                    listStylePositionType = EnumListStylePosition.inside;
                    return true;
                }
                if(ident.equalsIgnoreCase("outside")) {
                    listStylePositionType = EnumListStylePosition.outside;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    listStylePositionType = EnumListStylePosition.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processListStyleType(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("disc")) {
                    listStyleTypeType = EnumListStyleType.disc;
                    return true;
                }
                if(ident.equalsIgnoreCase("circle")) {
                    listStyleTypeType = EnumListStyleType.circle;
                    return true;
                }
                if(ident.equalsIgnoreCase("square")) {
                    listStyleTypeType = EnumListStyleType.square;
                    return true;
                }
                if(ident.equalsIgnoreCase("decimal")) {
                    listStyleTypeType = EnumListStyleType.decimal;
                    return true;
                }
                if(ident.equalsIgnoreCase("decimal-leading-zero")) {
                    listStyleTypeType = EnumListStyleType.decimal_leading_zero;
                    return true;
                }
                if(ident.equalsIgnoreCase("lower-roman")) {
                    listStyleTypeType = EnumListStyleType.lower_roman;
                    return true;
                }
                if(ident.equalsIgnoreCase("upper-roman")) {
                    listStyleTypeType = EnumListStyleType.upper_roman;
                    return true;
                }
                if(ident.equalsIgnoreCase("lower-greek")) {
                    listStyleTypeType = EnumListStyleType.lower_greek;
                    return true;
                }
                if(ident.equalsIgnoreCase("lower-latin")) {
                    listStyleTypeType = EnumListStyleType.lower_latin;
                    return true;
                }
                if(ident.equalsIgnoreCase("upper-latin")) {
                    listStyleTypeType = EnumListStyleType.upper_latin;
                    return true;
                }
                if(ident.equalsIgnoreCase("armenian")) {
                    listStyleTypeType = EnumListStyleType.armenian;
                    return true;
                }
                if(ident.equalsIgnoreCase("georgian")) {
                    listStyleTypeType = EnumListStyleType.georgian;
                    return true;
                }
                if(ident.equalsIgnoreCase("lower-alpha")) {
                    listStyleTypeType = EnumListStyleType.lower_alpha;
                    return true;
                }
                if(ident.equalsIgnoreCase("upper-alpha")) {
                    listStyleTypeType = EnumListStyleType.upper_alpha;
                    return true;
                }
                if(ident.equalsIgnoreCase("none")) {
                    listStyleTypeType = EnumListStyleType.none;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    listStyleTypeType = EnumListStyleType.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processListStyle(Declaration d) {
        NodeData trans = beginTransaction();
        //Nastavení na výchozí hodnoty
        listStyleImageType = EnumListStyleImage.none;
        listStyleImageValue = null;  
        listStylePositionType = EnumListStylePosition.outside;
        listStyleTypeType = EnumListStyleType.disc;
        
        //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné deklaraci
        //objevila například 2x barva. K určení slouží následující proměnné
        boolean processedImage = false;
        boolean processedPosition = false;
        boolean processedType = false;
        
        for(Term t : d.getTermsList()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTermsList().size() == 1) {
                    listStyleImageType = EnumListStyleImage.inherit;
                    listStyleImageValue = null;  
                    listStylePositionType = EnumListStylePosition.inherit;
                    listStyleTypeType = EnumListStyleType.inherit;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten aktuální)
            //a v jednotlivých blocích se pokouším tuto deklaraci zpracovat. 
            Declaration tmpDeclaration = new DataDeclaration("list-style");
            tmpDeclaration.getTermsList().add(t);
            
            //Vyzkouším, jestli se jedná o barvu
            tmpDeclaration.setProperty("list-style-image");
            if(processListStyleImage(tmpDeclaration)) {
                //Jedná se o barvu. Zjistím, jestli barva už nebyla jednou zadána
                if(processedImage) {
                    //Barva už byla jednou zadáno, deklarace je chybná, rollback a konec
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    //Barva ještě nebyla zadána, pokračujeme dalším termem
                    processedImage = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("list-style-position");
            if(processListStylePosition(tmpDeclaration)) {
                if(processedPosition) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedPosition = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("list-style-type");
            if(processListStyleType(tmpDeclaration)) {
                if(processedType) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedType = true;
                    continue;
                }
            }
            
            //Pokud program dojde sem, znamená to že term není ani image, position nebo type - ignorace celé deklarace
            rollbackTransaction(trans);
            return false;
        }
        return true;
    }
    
    private Boolean processMarginTop(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    marginTopType = EnumSize.auto;
                    marginTopNumberValue = null;
                    marginTopPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    marginTopType = EnumSize.inherit;
                    marginTopNumberValue = null;
                    marginTopPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    marginTopType = EnumSize.length;
                    marginTopNumberValue = num;
                    marginTopPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                marginTopType = EnumSize.percentage;
                marginTopNumberValue = null;
                marginTopPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processMarginRight(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    marginRightType = EnumSize.auto;
                    marginRightNumberValue = null;
                    marginRightPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    marginRightType = EnumSize.inherit;
                    marginRightNumberValue = null;
                    marginRightPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    marginRightType = EnumSize.length;
                    marginRightNumberValue = num;
                    marginRightPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                marginRightType = EnumSize.percentage;
                marginRightNumberValue = null;
                marginRightPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processMarginBottom(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    marginBottomType = EnumSize.auto;
                    marginBottomNumberValue = null;
                    marginBottomPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    marginBottomType = EnumSize.inherit;
                    marginBottomNumberValue = null;
                    marginBottomPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    marginBottomType = EnumSize.length;
                    marginBottomNumberValue = num;
                    marginBottomPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                marginBottomType = EnumSize.percentage;
                marginBottomNumberValue = null;
                marginBottomPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processMarginLeft(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    marginLeftType = EnumSize.auto;
                    marginLeftNumberValue = null;
                    marginLeftPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    marginLeftType = EnumSize.inherit;
                    marginLeftNumberValue = null;
                    marginLeftPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    marginLeftType = EnumSize.length;
                    marginLeftNumberValue = num;
                    marginLeftPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                marginLeftType = EnumSize.percentage;
                marginLeftNumberValue = null;
                marginLeftPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processMargin(Declaration d) {
        NodeData trans = beginTransaction();
        if(d.getTermsList().size() == 1) {
            Declaration tmpDeclarationTop = new DataDeclaration("margin-top");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("margin-right");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationBottom = new DataDeclaration("margin-bottom");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("margin-left");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(0));
            
            if(processMarginTop(tmpDeclarationTop) && processMarginRight(tmpDeclarationRight) &&
               processMarginBottom(tmpDeclarationBottom) && processMarginLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTermsList().size() == 2) {
            Declaration tmpDeclarationTop = new DataDeclaration("margin-top");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("margin-right");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("margin-bottom");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("margin-left");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(1));
            
            if(processMarginTop(tmpDeclarationTop) && processMarginRight(tmpDeclarationRight) &&
               processMarginBottom(tmpDeclarationBottom) && processMarginLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTermsList().size() == 3) {
            Declaration tmpDeclarationTop = new DataDeclaration("margin-top");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("margin-right");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("margin-bottom");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("margin-left");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(1));
            
            if(processMarginTop(tmpDeclarationTop) && processMarginRight(tmpDeclarationRight) &&
               processMarginBottom(tmpDeclarationBottom) && processMarginLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTermsList().size() == 4) {
            Declaration tmpDeclarationTop = new DataDeclaration("margin-top");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("margin-right");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("margin-bottom");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("margin-left");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(3));
            
            if(processMarginTop(tmpDeclarationTop) && processMarginRight(tmpDeclarationRight) &&
               processMarginBottom(tmpDeclarationBottom) && processMarginLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processMaxHeight(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("none")) {
                    maxHeightType = EnumMinMaxSize.none;
                    maxHeightNumberValue = null;
                    maxHeightPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    maxHeightType = EnumMinMaxSize.inherit;
                    maxHeightNumberValue = null;
                    maxHeightPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    if(num.getValue().floatValue() < 0) {
                        num.setValue(new Float(0));
                    }
                    maxHeightType = EnumMinMaxSize.length;
                    maxHeightNumberValue = num;
                    maxHeightPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                if(percentage.getValue().floatValue() < 0) {
                    percentage.setValue(new Float(0));
                }
                maxHeightType = EnumMinMaxSize.percentage;
                maxHeightNumberValue = null;
                maxHeightPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processMaxWidth(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("none")) {
                    maxWidthType = EnumMinMaxSize.none;
                    maxWidthNumberValue = null;
                    maxWidthPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    maxWidthType = EnumMinMaxSize.inherit;
                    maxWidthNumberValue = null;
                    maxWidthPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    if(num.getValue().floatValue() < 0) {
                        num.setValue(new Float(0));
                    }
                    maxWidthType = EnumMinMaxSize.length;
                    maxWidthNumberValue = num;
                    maxWidthPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                if(percentage.getValue().floatValue() < 0) {
                    percentage.setValue(new Float(0));
                }
                maxWidthType = EnumMinMaxSize.percentage;
                maxWidthNumberValue = null;
                maxWidthPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processMinHeight(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("none")) {
                    minHeightType = EnumMinMaxSize.none;
                    minHeightNumberValue = null;
                    minHeightPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    minHeightType = EnumMinMaxSize.inherit;
                    minHeightNumberValue = null;
                    minHeightPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    if(num.getValue().floatValue() < 0) {
                        num.setValue(new Float(0));
                    }
                    minHeightType = EnumMinMaxSize.length;
                    minHeightNumberValue = num;
                    minHeightPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                if(percentage.getValue().floatValue() < 0) {
                    percentage.setValue(new Float(0));
                }
                minHeightType = EnumMinMaxSize.percentage;
                minHeightNumberValue = null;
                minHeightPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processMinWidth(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("none")) {
                    minWidthType = EnumMinMaxSize.none;
                    minWidthNumberValue = null;
                    minWidthPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    minWidthType = EnumMinMaxSize.inherit;
                    minWidthNumberValue = null;
                    minWidthPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    if(num.getValue().floatValue() < 0) {
                        num.setValue(new Float(0));
                    }
                    minWidthType = EnumMinMaxSize.length;
                    minWidthNumberValue = num;
                    minWidthPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                if(percentage.getValue().floatValue() < 0) {
                    percentage.setValue(new Float(0));
                }
                minWidthType = EnumMinMaxSize.percentage;
                minWidthNumberValue = null;
                minWidthPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processOrphans(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    orphansType = EnumOrphans.inherit;
                    orphansIntegerValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isInteger()) {
                    orphansType = EnumOrphans.integer;
                    orphansIntegerValue = num;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processOutlineColor(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermColor) {
                TermColor color = (TermColor)d.getTermsList().get(0);
                outlineColorValue = color;
                outlineColorType = EnumColorInvert.color;
                return true;
                
            }
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("invert")) {
                    outlineColorType = EnumColorInvert.invert;
                    outlineColorValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    outlineColorType = EnumColorInvert.inherit;
                    outlineColorValue = null;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processOutlineStyle(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("none")) {
                    outlineStyleType = EnumBorderStyle.none;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("hidden")) {
                    outlineStyleType = EnumBorderStyle.hidden;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("dotted")) {
                    outlineStyleType = EnumBorderStyle.dotted;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("dashed")) {
                    outlineStyleType = EnumBorderStyle.dashed;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("solid")) {
                    outlineStyleType = EnumBorderStyle.solid;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("double")) {
                    outlineStyleType = EnumBorderStyle.prefix_double;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("groove")) {
                    outlineStyleType = EnumBorderStyle.groove;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("ridge")) {
                    outlineStyleType = EnumBorderStyle.ridge;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inset")) {
                    outlineStyleType = EnumBorderStyle.inset;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("outset")) {
                    outlineStyleType = EnumBorderStyle.outset;
                    return new Boolean(true);
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    outlineStyleType = EnumBorderStyle.inherit;
                    return new Boolean(true);
                }
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processOutlineWidth(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    outlineWidthValue = num;
                    outlineWidthType = EnumBorderWidth.length;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("thin")) {
                    outlineWidthType = EnumBorderWidth.thin;
                    outlineWidthValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("medium")) {
                    outlineWidthType = EnumBorderWidth.medium;
                    outlineWidthValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("thick")) {
                    outlineWidthType = EnumBorderWidth.thick;
                    outlineWidthValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    outlineWidthType = EnumBorderWidth.inherit;
                    outlineWidthValue = null;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processOutline(Declaration d) {
        NodeData trans = beginTransaction();
        //Nastavení na výchozí hodnoty
        outlineColorType = null;
        outlineColorValue = null;
        outlineStyleType = null;
        outlineWidthType = null;
        outlineWidthValue = null;
        
        //Každou z částí lze nastavit pouze jednou. Není přípustné aby se v jedné deklaraci
        //objevila například 2x barva. K určení slouží následující proměnné
        boolean processedColor = false;
        boolean processedStyle = false;
        boolean processedWidth = false;
        
        for(Term t : d.getTermsList()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTermsList().size() == 1) {
                    outlineColorType = EnumColorInvert.inherit;
                    outlineColorValue = null;
                    outlineStyleType = EnumBorderStyle.inherit;
                    outlineWidthType = EnumBorderWidth.inherit;
                    outlineWidthValue = null;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            
            //Vytvořím pomocnou deklaraci, která obsahuje jeden jediný term (ten aktuální)
            //a v jednotlivých blocích se pokouším tuto deklaraci zpracovat. 
            Declaration tmpDeclaration = new DataDeclaration("outline");
            tmpDeclaration.getTermsList().add(t);
            
            //Vyzkouším, jestli se jedná o barvu
            tmpDeclaration.setProperty("outline-color");
            if(processOutlineColor(tmpDeclaration)) {
                //Jedná se o barvu. Zjistím, jestli barva už nebyla jednou zadána
                if(processedColor) {
                    //Barva už byla jednou zadáno, deklarace je chybná, rollback a konec
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    //Barva ještě nebyla zadána, pokračujeme dalším termem
                    processedColor = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("outline-style");
            if(processOutlineStyle(tmpDeclaration)) {
                if(processedStyle) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedStyle = true;
                    continue;
                }
            }
            tmpDeclaration.setProperty("outline-width");
            if(processOutlineWidth(tmpDeclaration)) {
                if(processedWidth) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    processedWidth = true;
                    continue;
                }
            }
            
            //Pokud program dojde sem, znamená to že term není ani image, position nebo type - ignorace celé deklarace
            rollbackTransaction(trans);
            return false;
        }
        return true;
    }
    
    private Boolean processOverflow(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("visible")) {
                    overflowType = EnumOverflow.visible;
                    return true;
                }
                if(ident.equalsIgnoreCase("hidden")) {
                    overflowType = EnumOverflow.hidden;
                    return true;
                }
                if(ident.equalsIgnoreCase("scroll")) {
                    overflowType = EnumOverflow.scroll;
                    return true;
                }
                if(ident.equalsIgnoreCase("auto")) {
                    overflowType = EnumOverflow.auto;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    overflowType = EnumOverflow.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processPaddingTop(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    paddingTopType = EnumPaddingWidth.inherit;
                    paddingTopNumberValue = null;
                    paddingTopPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    if(num.getValue().floatValue() < 0) {
                        num.setValue(new Float(0));
                    }
                    paddingTopType = EnumPaddingWidth.length;
                    paddingTopNumberValue = num;
                    paddingTopPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                if(percentage.getValue().floatValue() < 0) {
                    percentage.setValue(new Float(0));
                }
                paddingTopType = EnumPaddingWidth.percentage;
                paddingTopNumberValue = null;
                paddingTopPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processPaddingRight(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    paddingRightType = EnumPaddingWidth.inherit;
                    paddingRightNumberValue = null;
                    paddingRightPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    if(num.getValue().floatValue() < 0) {
                        num.setValue(new Float(0));
                    }
                    paddingRightType = EnumPaddingWidth.length;
                    paddingRightNumberValue = num;
                    paddingRightPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                if(percentage.getValue().floatValue() < 0) {
                    percentage.setValue(new Float(0));
                }
                paddingRightType = EnumPaddingWidth.percentage;
                paddingRightNumberValue = null;
                paddingRightPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processPaddingBottom(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    paddingBottomType = EnumPaddingWidth.inherit;
                    paddingBottomNumberValue = null;
                    paddingBottomPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    if(num.getValue().floatValue() < 0) {
                        num.setValue(new Float(0));
                    }
                    paddingBottomType = EnumPaddingWidth.length;
                    paddingBottomNumberValue = num;
                    paddingBottomPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                if(percentage.getValue().floatValue() < 0) {
                    percentage.setValue(new Float(0));
                }
                paddingBottomType = EnumPaddingWidth.percentage;
                paddingBottomNumberValue = null;
                paddingBottomPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processPaddingLeft(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    paddingLeftType = EnumPaddingWidth.inherit;
                    paddingLeftNumberValue = null;
                    paddingLeftPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    if(num.getValue().floatValue() < 0) {
                        num.setValue(new Float(0));
                    }
                    paddingLeftType = EnumPaddingWidth.length;
                    paddingLeftNumberValue = num;
                    paddingLeftPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                if(percentage.getValue().floatValue() < 0) {
                    percentage.setValue(new Float(0));
                }
                paddingLeftType = EnumPaddingWidth.percentage;
                paddingLeftNumberValue = null;
                paddingLeftPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processPadding(Declaration d) {
        NodeData trans = beginTransaction();
        if(d.getTermsList().size() == 1) {
            Declaration tmpDeclarationTop = new DataDeclaration("padding-top");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("padding-right");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationBottom = new DataDeclaration("padding-bottom");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("padding-left");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(0));
            
            if(processPaddingTop(tmpDeclarationTop) && processPaddingRight(tmpDeclarationRight) &&
               processPaddingBottom(tmpDeclarationBottom) && processPaddingLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTermsList().size() == 2) {
            Declaration tmpDeclarationTop = new DataDeclaration("padding-top");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("padding-right");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("padding-bottom");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("padding-left");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(1));
            
            if(processPaddingTop(tmpDeclarationTop) && processPaddingRight(tmpDeclarationRight) &&
               processPaddingBottom(tmpDeclarationBottom) && processPaddingLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTermsList().size() == 3) {
            Declaration tmpDeclarationTop = new DataDeclaration("padding-top");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("padding-right");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("padding-bottom");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("padding-left");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(1));
            
            if(processPaddingTop(tmpDeclarationTop) && processPaddingRight(tmpDeclarationRight) &&
               processPaddingBottom(tmpDeclarationBottom) && processPaddingLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTermsList().size() == 4) {
            Declaration tmpDeclarationTop = new DataDeclaration("padding-top");
            tmpDeclarationTop.getTermsList().add(d.getTermsList().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("padding-right");
            tmpDeclarationRight.getTermsList().add(d.getTermsList().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("padding-bottom");
            tmpDeclarationBottom.getTermsList().add(d.getTermsList().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("padding-left");
            tmpDeclarationLeft.getTermsList().add(d.getTermsList().get(3));
            
            if(processPaddingTop(tmpDeclarationTop) && processPaddingRight(tmpDeclarationRight) &&
               processPaddingBottom(tmpDeclarationBottom) && processPaddingLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        return new Boolean(false);
    }
    
    private Boolean processPageBreakAfter(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    pageBreakAfterType = EnumPageBreak.auto;
                    return true;
                }
                if(ident.equalsIgnoreCase("always")) {
                    pageBreakAfterType = EnumPageBreak.always;
                    return true;
                }
                if(ident.equalsIgnoreCase("avoid")) {
                    pageBreakAfterType = EnumPageBreak.avoid;
                    return true;
                }
                if(ident.equalsIgnoreCase("left")) {
                    pageBreakAfterType = EnumPageBreak.left;
                    return true;
                }
                if(ident.equalsIgnoreCase("right")) {
                    pageBreakAfterType = EnumPageBreak.right;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    pageBreakAfterType = EnumPageBreak.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processPageBreakBefore(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    pageBreakBeforeType = EnumPageBreak.auto;
                    return true;
                }
                if(ident.equalsIgnoreCase("always")) {
                    pageBreakBeforeType = EnumPageBreak.always;
                    return true;
                }
                if(ident.equalsIgnoreCase("avoid")) {
                    pageBreakBeforeType = EnumPageBreak.avoid;
                    return true;
                }
                if(ident.equalsIgnoreCase("left")) {
                    pageBreakBeforeType = EnumPageBreak.left;
                    return true;
                }
                if(ident.equalsIgnoreCase("right")) {
                    pageBreakBeforeType = EnumPageBreak.right;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    pageBreakBeforeType = EnumPageBreak.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processPageBreakInside(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    pageBreakInsideType = EnumPageBreakInside.auto;
                    return true;
                }
                if(ident.equalsIgnoreCase("avoid")) {
                    pageBreakInsideType = EnumPageBreakInside.avoid;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    pageBreakInsideType = EnumPageBreakInside.inherit;
                    return true;
                }
            }
        }
        return false;
    }
   
    private Boolean processPosition(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("static")) {
                    positionType = EnumPosition.prefix_static;
                    return true;
                }
                if(ident.equalsIgnoreCase("relative")) {
                    positionType = EnumPosition.relative;
                    return true;
                }
                if(ident.equalsIgnoreCase("absolute")) {
                    positionType = EnumPosition.absolute;
                    return true;
                }
                if(ident.equalsIgnoreCase("fixed")) {
                    positionType = EnumPosition.fixed;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    positionType = EnumPosition.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processQuotes(Declaration d) {
        ArrayList<String> out = new ArrayList<String>();
        for(Term t : d.getTermsList()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTermsList().size() == 1) {
                    quotesType = EnumQuotes.inherit;
                    quotesValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
                if(d.getTermsList().size() == 1) {
                    quotesType = EnumQuotes.none;
                    quotesValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermString)) {
                out.add(((TermString)t).getValue());
                continue;
            }
            return false;
        }
        
        if(!out.isEmpty() && out.size() % 2 == 0) {
            quotesType = EnumQuotes.list_values;
            quotesValues.clear();
            quotesValues.addAll(out);
            return true;
        }
        else {
            return false;
        }
    }
    
    private Boolean processTableLayout(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    tableLayoutType = EnumTableLayout.auto;
                    return true;
                }
                if(ident.equalsIgnoreCase("fixed")) {
                    tableLayoutType = EnumTableLayout.fixed;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    tableLayoutType = EnumTableLayout.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processTextAlign(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("left")) {
                    textAlignType = EnumTextAlign.left;
                    return true;
                }
                if(ident.equalsIgnoreCase("right")) {
                    textAlignType = EnumTextAlign.right;
                    return true;
                }
                if(ident.equalsIgnoreCase("center")) {
                    textAlignType = EnumTextAlign.center;
                    return true;
                }
                if(ident.equalsIgnoreCase("justify")) {
                    textAlignType = EnumTextAlign.justify;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    textAlignType = EnumTextAlign.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processTextDecoration(Declaration d) {
        NodeData trans = beginTransaction();
        //Nastavení na výchozí hodnoty
        textDecorationType = null;
        textDecorationUnderline = null;
        textDecorationOverline = null;
        textDecorationLineThrough = null;
        textDecorationBlink = null;
        
        for(Term t : d.getTermsList()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTermsList().size() == 1) {
                    textDecorationType = EnumTextDecoration.inherit;
                    textDecorationUnderline = null;
                    textDecorationOverline = null;
                    textDecorationLineThrough = null;
                    textDecorationBlink = null;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
                if(d.getTermsList().size() == 1) {
                    textDecorationType = EnumTextDecoration.set;
                    textDecorationUnderline = false;
                    textDecorationOverline = false;
                    textDecorationLineThrough = false;
                    textDecorationBlink = false;
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }

            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("underline")) {
                textDecorationType = EnumTextDecoration.set;
                if(textDecorationBlink == null) { textDecorationBlink = false; }
                if(textDecorationLineThrough == null) { textDecorationLineThrough = false; }
                if(textDecorationOverline == null) { textDecorationOverline = false; }
               
                if(textDecorationUnderline != null && textDecorationUnderline) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    textDecorationUnderline = true;
                    continue;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("overline")) {
                textDecorationType = EnumTextDecoration.set;
                if(textDecorationBlink == null) { textDecorationBlink = false; }
                if(textDecorationLineThrough == null) { textDecorationLineThrough = false; }
                if(textDecorationUnderline == null) { textDecorationUnderline = false; }

                if(textDecorationOverline != null && textDecorationOverline) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    textDecorationOverline = true;
                    continue;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("line-through")) {
                textDecorationType = EnumTextDecoration.set;
                if(textDecorationBlink == null) { textDecorationBlink = false; }
                if(textDecorationOverline == null) { textDecorationOverline = false; }
                if(textDecorationUnderline == null) { textDecorationUnderline = false; }

                if(textDecorationLineThrough != null && textDecorationLineThrough) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    textDecorationLineThrough = true;
                    continue;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("blink")) {
                textDecorationType = EnumTextDecoration.set;
                if(textDecorationLineThrough == null) { textDecorationLineThrough = false; }
                if(textDecorationOverline == null) { textDecorationOverline = false; }
                if(textDecorationUnderline == null) { textDecorationUnderline = false; }

                if(textDecorationBlink != null && textDecorationBlink) {
                    rollbackTransaction(trans);
                    return false;
                }
                else {
                    textDecorationBlink = true;
                    continue;
                }
            }
            
            //Pokud program dojde sem, znamená to že term není ani image, position nebo type - ignorace celé deklarace
            rollbackTransaction(trans);
            return false;
        }
        return true;
    }
    
    private Boolean processTextIndent(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    textIndentType = EnumTextIndent.inherit;
                    textIndentNumberValue = null;
                    textIndentPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    textIndentType = EnumTextIndent.length;
                    textIndentNumberValue = num;
                    textIndentPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                textIndentType = EnumTextIndent.percentage;
                textIndentNumberValue = null;
                textIndentPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processTextTransform(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("capitalize")) {
                    textTransformType = EnumTextTransform.capitalize;
                    return true;
                }
                if(ident.equalsIgnoreCase("uppercase")) {
                    textTransformType = EnumTextTransform.uppercase;
                    return true;
                }
                if(ident.equalsIgnoreCase("lowercase")) {
                    textTransformType = EnumTextTransform.lowercase;
                    return true;
                }
                if(ident.equalsIgnoreCase("none")) {
                    textTransformType = EnumTextTransform.none;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    textTransformType = EnumTextTransform.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processUnicodeBidi(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("normal")) {
                    unicodeBidiType = EnumUnicodeBidi.normal;
                    return true;
                }
                if(ident.equalsIgnoreCase("embed")) {
                    unicodeBidiType = EnumUnicodeBidi.embed;
                    return true;
                }
                if(ident.equalsIgnoreCase("bidi-override")) {
                    unicodeBidiType = EnumUnicodeBidi.bidi_override;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    unicodeBidiType = EnumUnicodeBidi.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processVerticalAlign(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    verticalAlignType = EnumVerticalAlign.inherit;
                    verticalAlignNumberValue = null;
                    verticalAlignPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("baseline")) {
                    verticalAlignType = EnumVerticalAlign.baseline;
                    verticalAlignNumberValue = null;
                    verticalAlignPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("sub")) {
                    verticalAlignType = EnumVerticalAlign.sub;
                    verticalAlignNumberValue = null;
                    verticalAlignPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("super")) {
                    verticalAlignType = EnumVerticalAlign.prefix_super;
                    verticalAlignNumberValue = null;
                    verticalAlignPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("top")) {
                    verticalAlignType = EnumVerticalAlign.top;
                    verticalAlignNumberValue = null;
                    verticalAlignPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("text-top")) {
                    verticalAlignType = EnumVerticalAlign.text_top;
                    verticalAlignNumberValue = null;
                    verticalAlignPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("middle")) {
                    verticalAlignType = EnumVerticalAlign.middle;
                    verticalAlignNumberValue = null;
                    verticalAlignPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("bottom")) {
                    verticalAlignType = EnumVerticalAlign.bottom;
                    verticalAlignNumberValue = null;
                    verticalAlignPercentValue = null;
                    return true;
                }
                if(ident.equalsIgnoreCase("text-bottom")) {
                    verticalAlignType = EnumVerticalAlign.text_bottom;
                    verticalAlignNumberValue = null;
                    verticalAlignPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isLength()) {
                    verticalAlignType = EnumVerticalAlign.length;
                    verticalAlignNumberValue = num;
                    verticalAlignPercentValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTermsList().get(0);
                verticalAlignType = EnumVerticalAlign.percentage;
                verticalAlignNumberValue = null;
                verticalAlignPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processVisibility(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("visible")) {
                    visibilityType = EnumVisibility.visible;
                    return true;
                }
                if(ident.equalsIgnoreCase("hidden")) {
                    visibilityType = EnumVisibility.hidden;
                    return true;
                }
                if(ident.equalsIgnoreCase("collapse")) {
                    visibilityType = EnumVisibility.collapse;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    visibilityType = EnumVisibility.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processWhiteSpace(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("normal")) {
                    whiteSpaceType = EnumWhiteSpace.normal;
                    return true;
                }
                if(ident.equalsIgnoreCase("pre")) {
                    whiteSpaceType = EnumWhiteSpace.pre;
                    return true;
                }
                if(ident.equalsIgnoreCase("nowrap")) {
                    whiteSpaceType = EnumWhiteSpace.nowrap;
                    return true;
                }
                if(ident.equalsIgnoreCase("pre-wrap")) {
                    whiteSpaceType = EnumWhiteSpace.pre_wrap;
                    return true;
                }
                if(ident.equalsIgnoreCase("pre-line")) {
                    whiteSpaceType = EnumWhiteSpace.pre_line;
                    return true;
                }
                if(ident.equalsIgnoreCase("inherit")) {
                    whiteSpaceType = EnumWhiteSpace.inherit;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processWidows(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    widowsType = EnumWidows.inherit;
                    widowsIntegerValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isInteger()) {
                    widowsType = EnumWidows.integer;
                    widowsIntegerValue = num;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processWordSpacing(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    wordSpacingType = EnumWordSpacing.inherit;
                    wordSpacingNumberValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isNumber()) {
                    wordSpacingType = EnumWordSpacing.length;
                    wordSpacingNumberValue = num;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processZIndex(Declaration d) {
        if(d.getTermsList().size() == 1) {
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    zIndexType = EnumZIndex.inherit;
                    zIndexIntegerValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTermsList().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    zIndexType = EnumZIndex.auto;
                    zIndexIntegerValue = null;
                    return true;
                }
            }
            if(d.getTermsList().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTermsList().get(0);
                if(num.isInteger()) {
                    zIndexType = EnumZIndex.integer;
                    zIndexIntegerValue = num;
                    return true;
                }
            }
        }
        return false;
    }
    
    private Boolean processContent(Declaration d) {

        ArrayList<Term> out = new ArrayList<Term>();
        
        for(Term t : d.getTermsList()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTermsList().size() == 1) {
                    contentType = EnumContent.inherit;
                    contentValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("normal")) {
                if(d.getTermsList().size() == 1) {
                    contentType = EnumContent.normal;
                    contentValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
                if(d.getTermsList().size() == 1) {
                    contentType = EnumContent.none;
                    contentValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("open-quote")) {
                out.add(t);
                continue;
            }
            
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("close-quote")) {
                out.add(t);
                continue;
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("no-open-quote")) {
                out.add(t);
                continue;
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("no-close-quote")) {
                out.add(t);
                continue;
            }
            if(t instanceof TermString) {
                out.add(t);
                continue;
            }
            if(t instanceof TermUri) {
                out.add(t);
                continue;
            }
            if((t instanceof TermFunction) && ((TermFunction)t).getFunctionName().equalsIgnoreCase("counter")) {
                out.add(t);
                continue;
            }
            if((t instanceof TermFunction) && ((TermFunction)t).getFunctionName().equalsIgnoreCase("counters")) {
                out.add(t);
                continue;
            }
            if((t instanceof TermFunction) && ((TermFunction)t).getFunctionName().equalsIgnoreCase("attr")) {
                out.add(t);
                continue;
            }
            return false;
        }
        
        if(!out.isEmpty()) {
            contentType = EnumContent.list_values;
            contentValues.clear();
            contentValues.addAll(out);
            return true;
        }
        else {
            return false;
        }
    }
    
    
    /**
     * Funkce začne transakci (klokuje obsah tohoto objektu). Na rozdíl od DB 
     * systémů jsou operace automaticky potvrzovány (commit), ale je možné se 
     * kdykoli vrátit do výchozího stavu (rollback)
     * @return kopie dat sloužící pro případný rollback
     */
    protected NodeData beginTransaction() {
        try {
            return (NodeData)this.clone();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Návrat objektu do výchozího stavu před započetím transakce
     * @param data kopie dat objektu
     */
    protected void rollbackTransaction(NodeData data) {
        for(Field f : this.getClass().getDeclaredFields()) {
            try {
                f.set(this, f.get(data));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Funkce pro klonování objektu
     * @return klonovaný objekt
     */
    @Override
    public Object clone() {
        try {
            return super.clone();
        }
        catch ( CloneNotSupportedException e ) {
            return null;
        }
    }
    
    /**
     * Výpis objektu v textové formě.
     * @param depth určuje zanoření v hierarchii, slouží k odsazení jednotlivých řádků
     * @return txtová reprezentace objektu
     */
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
    
    /**
     * Výpis objektu v HTML formátu. Je o něco přehlednější než textová forma, 
     * nicméně potřebuje pro zobrazení využít webový prohlížeč
     * @return HTML reprezentace objektu
     */
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
            for(TermUri t : cursorUri) {
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
    
    public EnumBackgroundAttachment getBackgroundAttachmentType() {
        return backgroundAttachmentType;
    }

    public EnumColorTransparent getBackgroundColorType() {
        return backgroundColorType;
    }

    public TermColor getBackgroundColorValue() {
        return backgroundColorValue;
    }

    public EnumBackgroundImage getBackgroundImageType() {
        return backgroundImageType;
    }

    public TermUri getBackgroundImageValue() {
        return backgroundImageValue;
    }

    public EnumBackgroundPosition getBackgroundPositionType() {
        return backgroundPositionType;
    }

    public EnumBackgroundPositionHor getBackgroundPositionHorType() {
        return backgroundPositionHorType;
    }

    public EnumBackgroundPositionVer getBackgroundPositionVerType() {
        return backgroundPositionVerType;
    }

    public TermPercent getBackgroundPositionHorPercentValue() {
        return backgroundPositionHorPercentValue;
    }

    public TermNumber getBackgroundPositionHorNumberValue() {
        return backgroundPositionHorNumberValue;
    }

    public TermPercent getBackgroundPositionVerPercentValue() {
        return backgroundPositionVerPercentValue;
    }

    public TermNumber getBackgroundPositionVerNumberValue() {
        return backgroundPositionVerNumberValue;
    }

    public EnumBackgroundRepeat getBackgroundRepeatType() {
        return backgroundRepeatType;
    }

    public EnumBorderCollapse getBorderCollapseType() {
        return borderCollapseType;
    }

    public EnumColorTransparent getBorderColorTopType() {
        return borderColorTopType;
    }

    public EnumColorTransparent getBorderColorRightType() {
        return borderColorRightType;
    }

    public EnumColorTransparent getBorderColorBottomType() {
        return borderColorBottomType;
    }

    public EnumColorTransparent getBorderColorLeftType() {
        return borderColorLeftType;
    }

    public TermColor getBorderColorTopValue() {
        return borderColorTopValue;
    }

    public TermColor getBorderColorRightValue() {
        return borderColorRightValue;
    }

    public TermColor getBorderColorBottomValue() {
        return borderColorBottomValue;
    }

    public TermColor getBorderColorLeftValue() {
        return borderColorLeftValue;
    }

    public EnumBorderSpacing getBorderSpacingType() {
        return borderSpacingType;
    }

    public TermNumber getBorderSpacingHorValue() {
        return borderSpacingHorValue;
    }

    public TermNumber getBorderSpacingVerValue() {
        return borderSpacingVerValue;
    }

    public EnumBorderStyle getBorderStyleTopType() {
        return borderStyleTopType;
    }

    public EnumBorderStyle getBorderStyleRightType() {
        return borderStyleRightType;
    }

    public EnumBorderStyle getBorderStyleBottomType() {
        return borderStyleBottomType;
    }

    public EnumBorderStyle getBorderStyleLeftType() {
        return borderStyleLeftType;
    }

    public EnumBorderWidth getBorderWidthTopType() {
        return borderWidthTopType;
    }

    public EnumBorderWidth getBorderWidthRightType() {
        return borderWidthRightType;
    }

    public EnumBorderWidth getBorderWidthBottomType() {
        return borderWidthBottomType;
    }

    public EnumBorderWidth getBorderWidthLeftType() {
        return borderWidthLeftType;
    }

    public TermNumber getBorderWidthTopValue() {
        return borderWidthTopValue;
    }

    public TermNumber getBorderWidthRightValue() {
        return borderWidthRightValue;
    }

    public TermNumber getBorderWidthBottomValue() {
        return borderWidthBottomValue;
    }

    public TermNumber getBorderWidthLeftValue() {
        return borderWidthLeftValue;
    }

    public EnumFontStyle getFontStyleType() {
        return fontStyleType;
    }

    public EnumFontFamily getFontFamilyType() {
        return fontFamilyType;
    }

    public ArrayList<String> getFontFamilyValues() {
        return fontFamilyValues;
    }

    public EnumFontSize getFontSizeType() {
        return fontSizeType;
    }

    public TermPercent getFontSizePercentValue() {
        return fontSizePercentValue;
    }

    public TermNumber getFontSizeNumberValue() {
        return fontSizeNumberValue;
    }

    public EnumFontVariant getFontVariantType() {
        return fontVariantType;
    }

    public EnumFontWeight getFontWeightType() {
        return fontWeightType;
    }

    public EnumLineHeight getLineHeightType() {
        return lineHeightType;
    }

    public TermPercent getLineHeightPercentValue() {
        return lineHeightPercentValue;
    }

    public TermNumber getLineHeightNumberValue() {
        return lineHeightNumberValue;
    }

    public EnumSize getTopType() {
        return topType;
    }

    public TermPercent getTopPercentValue() {
        return topPercentValue;
    }

    public TermNumber getTopNumberValue() {
        return topNumberValue;
    }

    public EnumSize getRightType() {
        return rightType;
    }

    public TermPercent getRightPercentValue() {
        return rightPercentValue;
    }

    public TermNumber getRightNumberValue() {
        return rightNumberValue;
    }

    public EnumSize getBottomType() {
        return bottomType;
    }

    public TermPercent getBottomPercentValue() {
        return bottomPercentValue;
    }

    public TermNumber getBottomNumberValue() {
        return bottomNumberValue;
    }

    public EnumSize getLeftType() {
        return leftType;
    }

    public TermPercent getLeftPercentValue() {
        return leftPercentValue;
    }

    public TermNumber getLeftNumberValue() {
        return leftNumberValue;
    }

    public EnumSize getWidthType() {
        return widthType;
    }

    public TermPercent getWidthPercentValue() {
        return widthPercentValue;
    }

    public TermNumber getWidthNumberValue() {
        return widthNumberValue;
    }

    public EnumSize getHeightType() {
        return heightType;
    }

    public TermPercent getHeightPercentValue() {
        return heightPercentValue;
    }

    public TermNumber getHeightNumberValue() {
        return heightNumberValue;
    }
    
    public EnumCaptionSide getCaptionSideType() {
        return captionSideType;
    }

    public EnumContent getContentType() {
        return contentType;
    }

    public ArrayList<Term> getContentValues() {
        return contentValues;
    }

    public EnumCounter getCounterIncrementType() {
        return counterIncrementType;
    }

    public HashMap<String, Integer> getCounterIncrementValues() {
        return counterIncrementValues;
    }

    public EnumCounter getCounterResetType() {
        return counterResetType;
    }

    public HashMap<String, Integer> getCounterResetValues() {
        return counterResetValues;
    }

    public EnumClear getClearType() {
        return clearType;
    }

    public EnumClip getClipTopType() {
        return clipTopType;
    }

    public EnumClip getClipRightType() {
        return clipRightType;
    }

    public EnumClip getClipBottomType() {
        return clipBottomType;
    }

    public EnumClip getClipLeftType() {
        return clipLeftType;
    }

    public TermNumber getClipTopNumberValue() {
        return clipTopNumberValue;
    }

    public TermNumber getClipRightNumberValue() {
        return clipRightNumberValue;
    }

    public TermNumber getClipBottomNumberValue() {
        return clipBottomNumberValue;
    }

    public TermNumber getClipLeftNumberValue() {
        return clipLeftNumberValue;
    }

    public EnumColor getColorType() {
        return colorType;
    }

    public TermColor getColorValue() {
        return colorValue;
    }

    public EnumCursor getCursorType() {
        return cursorType;
    }

    public ArrayList<TermUri> getCursorUri() {
        return cursorUri;
    }

    public EnumDirection getDirectionType() {
        return directionType;
    }

    public EnumDisplay getDisplayType() {
        return displayType;
    }

    public EnumEmptyCells getEmptyCellsType() {
        return emptyCellsType;
    }

    public EnumFloat getFloatType() {
        return floatType;
    }

    public EnumListStyleImage getListStyleImageType() {
        return listStyleImageType;
    }

    public TermUri getListStyleImageValue() {
        return listStyleImageValue;
    }

    public EnumListStylePosition getListStylePositionType() {
        return listStylePositionType;
    }

    public EnumListStyleType getListStyleTypeType() {
        return listStyleTypeType;
    }

    public EnumSize getMarginTopType() {
        return marginTopType;
    }

    public TermPercent getMarginTopPercentValue() {
        return marginTopPercentValue;
    }

    public TermNumber getMarginTopNumberValue() {
        return marginTopNumberValue;
    }

    public EnumSize getMarginRightType() {
        return marginRightType;
    }

    public TermPercent getMarginRightPercentValue() {
        return marginRightPercentValue;
    }

    public TermNumber getMarginRightNumberValue() {
        return marginRightNumberValue;
    }

    public EnumSize getMarginBottomType() {
        return marginBottomType;
    }

    public TermPercent getMarginBottomPercentValue() {
        return marginBottomPercentValue;
    }

    public TermNumber getMarginBottomNumberValue() {
        return marginBottomNumberValue;
    }

    public EnumSize getMarginLeftType() {
        return marginLeftType;
    }

    public TermPercent getMarginLeftPercentValue() {
        return marginLeftPercentValue;
    }

    public TermNumber getMarginLeftNumberValue() {
        return marginLeftNumberValue;
    }

    public EnumMinMaxSize getMaxHeightType() {
        return maxHeightType;
    }

    public TermPercent getMaxHeightPercentValue() {
        return maxHeightPercentValue;
    }

    public TermNumber getMaxHeightNumberValue() {
        return maxHeightNumberValue;
    }

    public EnumMinMaxSize getMaxWidthType() {
        return maxWidthType;
    }

    public TermPercent getMaxWidthPercentValue() {
        return maxWidthPercentValue;
    }

    public TermNumber getMaxWidthNumberValue() {
        return maxWidthNumberValue;
    }

    public EnumMinMaxSize getMinHeightType() {
        return minHeightType;
    }

    public TermPercent getMinHeightPercentValue() {
        return minHeightPercentValue;
    }

    public TermNumber getMinHeightNumberValue() {
        return minHeightNumberValue;
    }

    public EnumMinMaxSize getMinWidthType() {
        return minWidthType;
    }

    public TermPercent getMinWidthPercentValue() {
        return minWidthPercentValue;
    }

    public TermNumber getMinWidthNumberValue() {
        return minWidthNumberValue;
    }
    
    public EnumOrphans getOrphansType() {
        return orphansType;
    }

    public TermNumber getOrphansIntegerValue() {
        return orphansIntegerValue;
    }

    public EnumColorInvert getOutlineColorType() {
        return outlineColorType;
    }

    public TermColor getOutlineColorValue() {
        return outlineColorValue;
    }

    public EnumBorderStyle getOutlineStyleType() {
        return outlineStyleType;
    }

    public EnumBorderWidth getOutlineWidthType() {
        return outlineWidthType;
    }

    public TermNumber getOutlineWidthValue() {
        return outlineWidthValue;
    }

    public EnumOverflow getOverflowType() {
        return overflowType;
    }

    public EnumPaddingWidth getPaddingTopType() {
        return paddingTopType;
    }

    public TermPercent getPaddingTopPercentValue() {
        return paddingTopPercentValue;
    }

    public TermNumber getPaddingTopNumberValue() {
        return paddingTopNumberValue;
    }

    public EnumPaddingWidth getPaddingRightType() {
        return paddingRightType;
    }

    public TermPercent getPaddingRightPercentValue() {
        return paddingRightPercentValue;
    }

    public TermNumber getPaddingRightNumberValue() {
        return paddingRightNumberValue;
    }

    public EnumPaddingWidth getPaddingBottomType() {
        return paddingBottomType;
    }

    public TermPercent getPaddingBottomPercentValue() {
        return paddingBottomPercentValue;
    }

    public TermNumber getPaddingBottomNumberValue() {
        return paddingBottomNumberValue;
    }

    public EnumPaddingWidth getPaddingLeftType() {
        return paddingLeftType;
    }

    public TermPercent getPaddingLeftPercentValue() {
        return paddingLeftPercentValue;
    }

    public TermNumber getPaddingLeftNumberValue() {
        return paddingLeftNumberValue;
    }

    public EnumPageBreak getPageBreakAfterType() {
        return pageBreakAfterType;
    }

    public EnumPageBreak getPageBreakBeforeType() {
        return pageBreakBeforeType;
    }

    public EnumPageBreakInside getPageBreakInsideType() {
        return pageBreakInsideType;
    }

    public EnumPosition getPositionType() {
        return positionType;
    }

    public EnumQuotes getQuotesType() {
        return quotesType;
    }

    public ArrayList<String> getQuotesValues() {
        return quotesValues;
    }

    public EnumTableLayout getTableLayoutType() {
        return tableLayoutType;
    }

    public EnumTextAlign getTextAlignType() {
        return textAlignType;
    }

    public EnumTextDecoration getTextDecorationType() {
        return textDecorationType;
    }

    public Boolean getTextDecorationUnderline() {
        return textDecorationUnderline;
    }

    public Boolean getTextDecorationOverline() {
        return textDecorationOverline;
    }

    public Boolean getTextDecorationLineThrough() {
        return textDecorationLineThrough;
    }

    public Boolean getTextDecorationBlink() {
        return textDecorationBlink;
    }

    public EnumTextIndent getTextIndentType() {
        return textIndentType;
    }

    public TermPercent getTextIndentPercentValue() {
        return textIndentPercentValue;
    }

    public TermNumber getTextIndentNumberValue() {
        return textIndentNumberValue;
    }

    public EnumTextTransform getTextTransformType() {
        return textTransformType;
    }

    public EnumUnicodeBidi getUnicodeBidiType() {
        return unicodeBidiType;
    }

    public EnumVerticalAlign getVerticalAlignType() {
        return verticalAlignType;
    }

    public TermPercent getVerticalAlignPercentValue() {
        return verticalAlignPercentValue;
    }

    public TermNumber getVerticalAlignNumberValue() {
        return verticalAlignNumberValue;
    }

    public EnumVisibility getVisibilityType() {
        return visibilityType;
    }

    public EnumWhiteSpace getWhiteSpaceType() {
        return whiteSpaceType;
    }

    public EnumWidows getWidowsType() {
        return widowsType;
    }

    public TermNumber getWidowsIntegerValue() {
        return widowsIntegerValue;
    }

    public EnumWordSpacing getWordSpacingType() {
        return wordSpacingType;
    }

    public TermNumber getWordSpacingNumberValue() {
        return wordSpacingNumberValue;
    }

    public EnumZIndex getZIndexType() {
        return zIndexType;
    }

    public TermNumber getZIndexIntegerValue() {
        return zIndexIntegerValue;
    }
    
    public String getUnsupported() {
        return unsupported;
    }

    public String getIgnored() {
        return ignored;
    }

    public String getPassed() {
        return passed;
    }
    
}
