package cz.vutbr.web.domassign;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.NodeData.*;

public class DeclarationTransformer {

	
	private static final CSSProperty INHERITABLE_PROPERTY = 
		new CSSProperty() {
			public boolean isInheritable() {return true;}
		};
		
	private static final CSSProperty NOT_INHERITABLE_PROPERTY = 
		new CSSProperty() {
			public boolean isInheritable() {return false;}
		};	
	
	private static final CSSProperty MULTIVALUE_PROPERY = 
		new CSSProperty() {
			public boolean isInheritable() {return false;}
		};
	
	/**
	 * Contains names of supported elements and default values
	 * according to
	 * <a href="http://www.culturedcode.com/css/reference.html">
	 * http://www.culturedcode.com/css/reference.html</a>
	 */	
	private static final Map<String, CSSProperty> supportedCSS;
	
	static {
		supportedCSS = new HashMap<String, CSSProperty>();
		
		// text type		
		supportedCSS.put("color", INHERITABLE_PROPERTY);
		supportedCSS.put("font", MULTIVALUE_PROPERY);
		supportedCSS.put("font-family", INHERITABLE_PROPERTY);
		supportedCSS.put("font-size", FontSize.medium);
		supportedCSS.put("font-style", FontStyle.normal);
		supportedCSS.put("font-variant", FontVariant.normal);
		supportedCSS.put("font-weight", FontWeight.normal);
		supportedCSS.put("text-decoration", TextDecoration.none);
		supportedCSS.put("text-transform", TextTransform.none);
		
		// text spacing
		supportedCSS.put("white-space", WhiteSpace.normal);
		supportedCSS.put("text-align", INHERITABLE_PROPERTY);
		supportedCSS.put("text-indent", TextIndent.length); // zero
		supportedCSS.put("line-height", INHERITABLE_PROPERTY);
		supportedCSS.put("word-spacing", WordSpacing.normal);
		supportedCSS.put("letter-spacing", LetterSpacing.normal);
		supportedCSS.put("vertical-align", VerticalAlign.baseline);
		supportedCSS.put("direction", Direction.ltr);
		supportedCSS.put("unicode-bidi", UnicodeBidi.normal);
		
		// layout box
		supportedCSS.put("margin", MULTIVALUE_PROPERY);
		supportedCSS.put("margin-top", MarginWidth.lenght);
		supportedCSS.put("margin-right", MarginWidth.lenght);
		supportedCSS.put("margin-bottom", MarginWidth.lenght);
		supportedCSS.put("margin-left", MarginWidth.lenght);
		supportedCSS.put("padding", MULTIVALUE_PROPERY);
		supportedCSS.put("padding-top", PaddingWidth.length);
		supportedCSS.put("padding-right", PaddingWidth.length);
		supportedCSS.put("padding-bottom", PaddingWidth.length);
		supportedCSS.put("padding-left", PaddingWidth.length);
		supportedCSS.put("border", MULTIVALUE_PROPERY);
		supportedCSS.put("border-width", BorderWidth.medium);
		supportedCSS.put("border-top-width", BorderWidth.medium);
		supportedCSS.put("border-right-width", BorderWidth.medium);
		supportedCSS.put("border-bottom-width", BorderWidth.medium);
		supportedCSS.put("border-left-width", BorderWidth.medium);
		supportedCSS.put("border-style", BorderStyle.none);
		supportedCSS.put("border-top-style", BorderStyle.none);
		supportedCSS.put("border-right-style", BorderStyle.none);
		supportedCSS.put("border-bottom-style", BorderStyle.none);
		supportedCSS.put("border-left-style", BorderStyle.none);
		// default color is taken form color property
		supportedCSS.put("border-color", NOT_INHERITABLE_PROPERTY);
		supportedCSS.put("border-top-color", NOT_INHERITABLE_PROPERTY);
		supportedCSS.put("border-right-color", NOT_INHERITABLE_PROPERTY);
		supportedCSS.put("border-bottom-color", NOT_INHERITABLE_PROPERTY);
		supportedCSS.put("border-left-color", NOT_INHERITABLE_PROPERTY);
		supportedCSS.put("width", Width.auto);
		supportedCSS.put("min-width", MinWidth.lenght);
		supportedCSS.put("max-width", MaxWidth.none);
		supportedCSS.put("height", Width.auto);
		supportedCSS.put("min-height", MinHeight.lenght);
		supportedCSS.put("max-height", MaxHeight.none);
		supportedCSS.put("overflow", Overflow.visible);
		supportedCSS.put("clip", Clip.auto);
		
		// positioning
		supportedCSS.put("display", Display.inline);
		supportedCSS.put("position", Position.prefix_static);
		supportedCSS.put("top", Top.auto);
		supportedCSS.put("right", Right.auto);
		supportedCSS.put("bottom", Bottom.auto);
		supportedCSS.put("left", Left.auto);
		supportedCSS.put("float", NodeData.Float.none);
		supportedCSS.put("clear", Clear.none);
		supportedCSS.put("z-index", ZIndex.auto);
		supportedCSS.put("visibility", Visibility.visible);
		
		// background
		supportedCSS.put("background", MULTIVALUE_PROPERY);
		supportedCSS.put("background-attachement", BackgroundAttachment.scroll);
		supportedCSS.put("background-color", BackgroundColor.transparent);
		supportedCSS.put("background-image", BackgroundImage.none);
		supportedCSS.put("background-position", MULTIVALUE_PROPERY);
		supportedCSS.put("background-repeat", BackgroundRepeat.repeat);
		
		// elements
	}
		
	
	

	public static boolean parseDeclaration(Declaration d, 
			List<CSSProperty> properties, List<Term> values) {
		
		String propertyName = d.getProperty().toLowerCase();
		
		CSSProperty defaultValue = supportedCSS.get(propertyName);
		
		if(defaultValue==null)
			return false;
		
		
	}
    
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
    */
    
    /**
     * Funkce převede název vlastnosti na název funkce, která bude volána. Název
     * funkce začíná slovem "process" a následuje názvem vlastnosti bez pomlček
     * (pomlčky v názvu vlastnosti jsou nahrazeny velkým následujícím písemenem v
     * názvu funkce)
     * @param property název
     * @return název funkce
     */
	/*
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
    */
    /*
    private Boolean processBackgroundAttachment(Declaration d) {
        //Zpracování vlastnosti background-attachment. Přípustné hodnoty jsou pouze 
        //scroll, fixed a inherit. Výchozí hodnota je scroll
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermColor) {
                TermColor color = (TermColor)d.getTerms().get(0);
                backgroundColorValue = color;
                backgroundColorType = EnumColorTransparent.color;
                return new Boolean(true);
                
            }
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermUri) {
                TermUri uri = (TermUri)d.getTerms().get(0);
                backgroundImageValue = uri;
                backgroundImageType = EnumBackgroundImage.uri;
                return new Boolean(true);
                
            }
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            //Pokud je v deklaraci pouze jeden term, znamená to, že buď je tento term inherit,
            //nebo je třeba druhou hodnotu doplnit jako identifikátor center
            //Popis dle W3C: If only one value is specified, the second value is assumed to be 'center'.
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            t1 = d.getTerms().get(0);
            t2 = new DataTermIdent("center");
        }
        if(d.getTerms().size() == 2) {
            t1 = d.getTerms().get(0);
            t2 = d.getTerms().get(1);
        }
        
        //V tomto bodě jsou definovány termy t1 a t2
        if(d.getTerms().size() <= 2) {
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
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
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
            tmpDeclaration.getTerms().add(t);
            
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
            tmpDeclaration.getTerms().add(probablyFirstPositionTerm);
            if(probablySecondPositionTerm != null) {
                tmpDeclaration.getTerms().add(probablySecondPositionTerm);
            }
            if(!processBackgroundPosition(tmpDeclaration)) {
            	// FIXME not valid CSS behaviour
                rollbackTransaction(trans);
                return false;
            }
        }
        return true;
    }
    
    private Boolean processBorderCollapse(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-color");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-color");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-color");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-color");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(0));
            
            if(processBorderTopColor(tmpDeclarationTop) && processBorderRightColor(tmpDeclarationRight) &&
               processBorderBottomColor(tmpDeclarationBottom) && processBorderLeftColor(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 2) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-color");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-color");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-color");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-color");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processBorderTopColor(tmpDeclarationTop) && processBorderRightColor(tmpDeclarationRight) &&
               processBorderBottomColor(tmpDeclarationBottom) && processBorderLeftColor(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 3) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-color");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-color");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-color");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-color");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processBorderTopColor(tmpDeclarationTop) && processBorderRightColor(tmpDeclarationRight) &&
               processBorderBottomColor(tmpDeclarationBottom) && processBorderLeftColor(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 4) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-color");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-color");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-color");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-color");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(3));
            
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermColor) {
                TermColor color = (TermColor)d.getTerms().get(0);
                borderColorTopValue = color;
                borderColorTopType = EnumColorTransparent.color;
                return new Boolean(true);
                
            }
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermColor) {
                TermColor color = (TermColor)d.getTerms().get(0);
                borderColorRightValue = color;
                borderColorRightType = EnumColorTransparent.color;
                return new Boolean(true);
                
            }
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermColor) {
                TermColor color = (TermColor)d.getTerms().get(0);
                borderColorBottomValue = color;
                borderColorBottomType = EnumColorTransparent.color;
                return new Boolean(true);
                
            }
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermColor) {
                TermColor color = (TermColor)d.getTerms().get(0);
                borderColorLeftValue = color;
                borderColorLeftType = EnumColorTransparent.color;
                return new Boolean(true);
                
            }
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    borderSpacingType = EnumBorderSpacing.inherit;
                    borderSpacingHorValue = null;
                    borderSpacingVerValue = null;
                    return new Boolean(true);
                }
            }
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
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
        if(d.getTerms().size() == 2) {
            if(d.getTerms().get(0) instanceof TermNumber && d.getTerms().get(1) instanceof TermNumber) {
                TermNumber num1 = (TermNumber)d.getTerms().get(0);
                TermNumber num2 = (TermNumber)d.getTerms().get(1);
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-style");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-style");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-style");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-style");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(0));
            
            if(processBorderTopStyle(tmpDeclarationTop) && processBorderRightStyle(tmpDeclarationRight) &&
               processBorderBottomStyle(tmpDeclarationBottom) && processBorderLeftStyle(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 2) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-style");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-style");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-style");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-style");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processBorderTopStyle(tmpDeclarationTop) && processBorderRightStyle(tmpDeclarationRight) &&
               processBorderBottomStyle(tmpDeclarationBottom) && processBorderLeftStyle(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 3) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-style");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-style");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-style");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-style");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processBorderTopStyle(tmpDeclarationTop) && processBorderRightStyle(tmpDeclarationRight) &&
               processBorderBottomStyle(tmpDeclarationBottom) && processBorderLeftStyle(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 4) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-style");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-style");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-style");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-style");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(3));
            
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    borderWidthTopValue = num;
                    borderWidthTopType = EnumBorderWidth.length;
                    return new Boolean(true);
                }
            }
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    borderWidthRightValue = num;
                    borderWidthRightType = EnumBorderWidth.length;
                    return new Boolean(true);
                }
            }
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    borderWidthBottomValue = num;
                    borderWidthBottomType = EnumBorderWidth.length;
                    return new Boolean(true);
                }
            }
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    borderWidthLeftValue = num;
                    borderWidthLeftType = EnumBorderWidth.length;
                    return new Boolean(true);
                }
            }
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-width");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-width");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-width");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-width");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(0));
            
            if(processBorderTopWidth(tmpDeclarationTop) && processBorderRightWidth(tmpDeclarationRight) &&
               processBorderBottomWidth(tmpDeclarationBottom) && processBorderLeftWidth(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 2) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-width");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-width");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-width");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-width");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processBorderTopWidth(tmpDeclarationTop) && processBorderRightWidth(tmpDeclarationRight) &&
               processBorderBottomWidth(tmpDeclarationBottom) && processBorderLeftWidth(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 3) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-width");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-width");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-width");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-width");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processBorderTopWidth(tmpDeclarationTop) && processBorderRightWidth(tmpDeclarationRight) &&
               processBorderBottomWidth(tmpDeclarationBottom) && processBorderLeftWidth(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 4) {
            Declaration tmpDeclarationTop = new DataDeclaration("border-top-width");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("border-right-width");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("border-bottom-width");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("border-left-width");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(3));
            
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
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
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
            tmpDeclaration.getTerms().add(t);
            
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
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
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
            tmpDeclaration.getTerms().add(t);
            
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
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
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
            tmpDeclaration.getTerms().add(t);
            
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
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
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
            tmpDeclaration.getTerms().add(t);
            
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
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
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
            tmpDeclaration.getTerms().add(t);
            
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    fontFamilyType = EnumFontFamily.inherit;
                    fontFamilyValues.clear();
                    return true;
                }
            }
        }
        
        for(Term t : d.getTerms()) {
            if(t instanceof TermIdent) {
                String ident = ((TermIdent)t).getValue();
                fontFamilyType = EnumFontFamily.font;
                if(t.getOperator() == Term.Operator.SPACE && !input.isEmpty()) {
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
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
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
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
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
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
                if(d.getTerms().size() == 1) {
                    //neimplementováno 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("icon")) {
                if(d.getTerms().size() == 1) {
                    //neimplementováno 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("menu")) {
                if(d.getTerms().size() == 1) {
                    //neimplementováno 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("message-box")) {
                if(d.getTerms().size() == 1) {
                    //neimplementováno 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("small-caption")) {
                if(d.getTerms().size() == 1) {
                    //neimplementováno 
                    return true;
                }
                else {
                    rollbackTransaction(trans);
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("status-bar")) {
                if(d.getTerms().size() == 1) {
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
            tmpDeclaration.getTerms().add(t);
            
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
            
            if(!processedLineHeight && t.getOperator() == Term.Operator.SLASH) {
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
            fontFamilyDeclaration.getTerms().add(t);
        }
        
        //Font family musí být zadáno
        if(fontFamilyDeclaration.getTerms().isEmpty()) {
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
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
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    topType = EnumSize.length;
                    topNumberValue = num;
                    topPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                topType = EnumSize.percentage;
                topNumberValue = null;
                topPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processRight(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    rightType = EnumSize.length;
                    rightNumberValue = num;
                    rightPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                rightType = EnumSize.percentage;
                rightNumberValue = null;
                rightPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processBottom(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    bottomType = EnumSize.length;
                    bottomNumberValue = num;
                    bottomPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                bottomType = EnumSize.percentage;
                bottomNumberValue = null;
                bottomPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processLeft(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    leftType = EnumSize.length;
                    leftNumberValue = num;
                    leftPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                leftType = EnumSize.percentage;
                leftNumberValue = null;
                leftPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processWidth(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    widthType = EnumSize.length;
                    widthNumberValue = num;
                    widthPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                widthType = EnumSize.percentage;
                widthNumberValue = null;
                widthPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processHeight(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    heightType = EnumSize.length;
                    heightNumberValue = num;
                    heightPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                heightType = EnumSize.percentage;
                heightNumberValue = null;
                heightPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processCaptionSide(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermFunction) {
                TermFunction f = (TermFunction)d.getTerms().get(0);
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermColor) {
                TermColor color = (TermColor)d.getTerms().get(0);
                colorValue = color;
                colorType = EnumColor.color;
                return true;
                
            }
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    counterIncrementType = EnumCounter.inherit;
                    counterIncrementValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
                if(d.getTerms().size() == 1) {
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
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    counterResetType = EnumCounter.inherit;
                    counterResetValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
                if(d.getTerms().size() == 1) {
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
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    cursorType = EnumCursor.inherit;
                    cursorUri.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("auto")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.auto;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("crosshair")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.crosshair;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("default")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.prefix_default;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("pointer")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.pointer;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("move")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.move;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("e-resize")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.e_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("ne-resize")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.ne_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("nw-resize")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.nw_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("n-resize")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.n_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("se-resize")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.se_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("sw-resize")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.sw_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("s-resize")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.s_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("w-resize")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.w_resize;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("text")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.text;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("wait")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.wait;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("help")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
                    cursorType = EnumCursor.help;
                    processedGeneric = true;
                    continue;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("progress")) {
                if(d.getTerms().size()-1 == index) { //Poslední hodnota
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermUri) {
                TermUri uri = (TermUri)d.getTerms().get(0);
                listStyleImageType = EnumListStyleImage.uri;
                listStyleImageValue = uri;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processListStylePosition(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
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
            tmpDeclaration.getTerms().add(t);
            
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    marginTopType = EnumSize.length;
                    marginTopNumberValue = num;
                    marginTopPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                marginTopType = EnumSize.percentage;
                marginTopNumberValue = null;
                marginTopPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processMarginRight(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    marginRightType = EnumSize.length;
                    marginRightNumberValue = num;
                    marginRightPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                marginRightType = EnumSize.percentage;
                marginRightNumberValue = null;
                marginRightPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processMarginBottom(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    marginBottomType = EnumSize.length;
                    marginBottomNumberValue = num;
                    marginBottomPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                marginBottomType = EnumSize.percentage;
                marginBottomNumberValue = null;
                marginBottomPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processMarginLeft(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    marginLeftType = EnumSize.length;
                    marginLeftNumberValue = num;
                    marginLeftPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
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
        if(d.getTerms().size() == 1) {
            Declaration tmpDeclarationTop = new DataDeclaration("margin-top");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("margin-right");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationBottom = new DataDeclaration("margin-bottom");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("margin-left");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(0));
            
            if(processMarginTop(tmpDeclarationTop) && processMarginRight(tmpDeclarationRight) &&
               processMarginBottom(tmpDeclarationBottom) && processMarginLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 2) {
            Declaration tmpDeclarationTop = new DataDeclaration("margin-top");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("margin-right");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("margin-bottom");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("margin-left");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processMarginTop(tmpDeclarationTop) && processMarginRight(tmpDeclarationRight) &&
               processMarginBottom(tmpDeclarationBottom) && processMarginLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 3) {
            Declaration tmpDeclarationTop = new DataDeclaration("margin-top");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("margin-right");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("margin-bottom");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("margin-left");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processMarginTop(tmpDeclarationTop) && processMarginRight(tmpDeclarationRight) &&
               processMarginBottom(tmpDeclarationBottom) && processMarginLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 4) {
            Declaration tmpDeclarationTop = new DataDeclaration("margin-top");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("margin-right");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("margin-bottom");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("margin-left");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(3));
            
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
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
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
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
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
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
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
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
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    orphansType = EnumOrphans.inherit;
                    orphansIntegerValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermColor) {
                TermColor color = (TermColor)d.getTerms().get(0);
                outlineColorValue = color;
                outlineColorType = EnumColorInvert.color;
                return true;
                
            }
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    outlineWidthValue = num;
                    outlineWidthType = EnumBorderWidth.length;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
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
            tmpDeclaration.getTerms().add(t);
            
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    paddingTopType = EnumPaddingWidth.inherit;
                    paddingTopNumberValue = null;
                    paddingTopPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
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
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    paddingRightType = EnumPaddingWidth.inherit;
                    paddingRightNumberValue = null;
                    paddingRightPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
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
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    paddingBottomType = EnumPaddingWidth.inherit;
                    paddingBottomNumberValue = null;
                    paddingBottomPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
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
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    paddingLeftType = EnumPaddingWidth.inherit;
                    paddingLeftNumberValue = null;
                    paddingLeftPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
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
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
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
        if(d.getTerms().size() == 1) {
            Declaration tmpDeclarationTop = new DataDeclaration("padding-top");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("padding-right");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationBottom = new DataDeclaration("padding-bottom");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("padding-left");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(0));
            
            if(processPaddingTop(tmpDeclarationTop) && processPaddingRight(tmpDeclarationRight) &&
               processPaddingBottom(tmpDeclarationBottom) && processPaddingLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 2) {
            Declaration tmpDeclarationTop = new DataDeclaration("padding-top");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("padding-right");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("padding-bottom");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationLeft = new DataDeclaration("padding-left");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processPaddingTop(tmpDeclarationTop) && processPaddingRight(tmpDeclarationRight) &&
               processPaddingBottom(tmpDeclarationBottom) && processPaddingLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 3) {
            Declaration tmpDeclarationTop = new DataDeclaration("padding-top");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("padding-right");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("padding-bottom");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("padding-left");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(1));
            
            if(processPaddingTop(tmpDeclarationTop) && processPaddingRight(tmpDeclarationRight) &&
               processPaddingBottom(tmpDeclarationBottom) && processPaddingLeft(tmpDeclarationLeft)) {
                return true;
            }
            else {
                rollbackTransaction(trans);
                return false;
            }
        }
        else if(d.getTerms().size() == 4) {
            Declaration tmpDeclarationTop = new DataDeclaration("padding-top");
            tmpDeclarationTop.getTerms().add(d.getTerms().get(0));
            Declaration tmpDeclarationRight = new DataDeclaration("padding-right");
            tmpDeclarationRight.getTerms().add(d.getTerms().get(1));
            Declaration tmpDeclarationBottom = new DataDeclaration("padding-bottom");
            tmpDeclarationBottom.getTerms().add(d.getTerms().get(2));
            Declaration tmpDeclarationLeft = new DataDeclaration("padding-left");
            tmpDeclarationLeft.getTerms().add(d.getTerms().get(3));
            
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    quotesType = EnumQuotes.inherit;
                    quotesValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
                if(d.getTerms().size() == 1) {
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
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
                if(d.getTerms().size() == 1) {
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    textIndentType = EnumTextIndent.inherit;
                    textIndentNumberValue = null;
                    textIndentPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    textIndentType = EnumTextIndent.length;
                    textIndentNumberValue = num;
                    textIndentPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                textIndentType = EnumTextIndent.percentage;
                textIndentNumberValue = null;
                textIndentPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processTextTransform(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
                if(num.isLength()) {
                    verticalAlignType = EnumVerticalAlign.length;
                    verticalAlignNumberValue = num;
                    verticalAlignPercentValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermPercent) {
                TermPercent percentage = (TermPercent)d.getTerms().get(0);
                verticalAlignType = EnumVerticalAlign.percentage;
                verticalAlignNumberValue = null;
                verticalAlignPercentValue = percentage;
                return true;
            }
        }
        return false;
    }
    
    private Boolean processVisibility(Declaration d) {
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    widowsType = EnumWidows.inherit;
                    widowsIntegerValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    wordSpacingType = EnumWordSpacing.inherit;
                    wordSpacingNumberValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
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
        if(d.getTerms().size() == 1) {
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("inherit")) {
                    zIndexType = EnumZIndex.inherit;
                    zIndexIntegerValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermIdent) {
                String ident = ((TermIdent)d.getTerms().get(0)).getValue();
                if(ident.equalsIgnoreCase("auto")) {
                    zIndexType = EnumZIndex.auto;
                    zIndexIntegerValue = null;
                    return true;
                }
            }
            if(d.getTerms().get(0) instanceof TermNumber) {
                TermNumber num = (TermNumber)d.getTerms().get(0);
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
        
        for(Term t : d.getTerms()) {
            //Pokud je první (a jediný) identifikátor inherit, pak se nastaví všecm hodnotám inherit
            //Pokud by se inherit objevilo až například jako třetí term, dojde k ignorování celé deklarace
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("inherit")) {
                if(d.getTerms().size() == 1) {
                    contentType = EnumContent.inherit;
                    contentValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("normal")) {
                if(d.getTerms().size() == 1) {
                    contentType = EnumContent.normal;
                    contentValues.clear();
                    return true;
                }
                else {
                    return false;
                }
            }
            if((t instanceof TermIdent) && ((TermIdent)t).getValue().equalsIgnoreCase("none")) {
                if(d.getTerms().size() == 1) {
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
	
}
