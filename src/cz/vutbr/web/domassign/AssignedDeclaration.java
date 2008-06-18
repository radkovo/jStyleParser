package cz.vutbr.web.domassign;

import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.SSItem;
import cz.vutbr.web.css.SSItemAttrib;
import cz.vutbr.web.css.SSItemClass;
import cz.vutbr.web.css.SSItemID;
import cz.vutbr.web.css.SSItemPseudo;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.SimpleSelector;

public class AssignedDeclaration implements Comparable {

    private Declaration declaration = null;
    
    private int specA = 0; //popis dle W3C: "count 1 if the declaration is from is a 'style' attribute"
    private int specB = 0; //popis dle W3C: "count the number of ID attributes in the selector"
    private int specC = 0; //popis dle W3C: "count the number of other attributes and pseudo-classes in the selector"
    private int specD = 0; //popis dle W3C: "count the number of element names and pseudo-elements in the selector"

    /**
     * Konstruktor, který při vytvoření spočítá specificitu daného selectoru a uloží jí k deklaraci
     * @param d deklarace
     * @param s selektor
     */
    public AssignedDeclaration(Declaration d, Selector s) {
        if(d == null || s == null) {
            throw new UnsupportedOperationException("Null values!!");
        }
        declaration = d;
        
        for(SimpleSelector ss : s.getSimpleSelectorsList()) {
            if(ss.getFirstItem() != null) {
                if(!ss.getFirstItem().equals("*")) {
                    specD++;
                }
            }
            for(SSItem ssi : ss.getItemsList()) {
                if(ssi instanceof SSItemClass) {
                    specC++;
                }
                else if(ssi instanceof SSItemID) {
                    specB++;
                }
                else if(ssi instanceof SSItemAttrib) {
                    specC++;
                }
                else if(ssi instanceof SSItemPseudo) {
                    //Tato část kódu nebude pravděpodobně nikdy vykonána, protože 
                    //ve funkci matchElementBySimpleSelector(..) se pseudo-elementy a
                    //pseudo-třídy ignorují a prohlašuje se vždy že element neodpovídá 
                    //selectoru. Slouží pouze pro demonstraci použití
                    if(((SSItemPseudo)ssi).getValue().matches("first-line|first-letter|before|after")) {
                        specD++; //jedná se o pseudo-elementy
                    }
                    else {
                        specC++; //jedná se o pseudo-třídy
                    }
                }
            }
        }
    }
    
    /**
     * Metoda pro porovnání dvou deklarací - bere se v úvahu specificita selektoru a 
     * pravidlo !IMPORTANT deklarace
     * @param arg0
     * @return
     */
    public int compareTo(Object arg0) {
        if(arg0 instanceof AssignedDeclaration) {
            AssignedDeclaration ad = (AssignedDeclaration)arg0;
            if(declaration.isImportant() && !ad.getDeclaration().isImportant()) {
                return 1;
            }
            else if(!declaration.isImportant() && ad.getDeclaration().isImportant()) {
                return -1;
            } 
            else {
                if(specA > ad.getSpecA()) { return 1; }
                else if(specA < ad.getSpecA()) { return -1; }
                else {
                    if(specB > ad.getSpecB()) { return 1; }
                    else if(specB < ad.getSpecB()) { return -1; }
                    else {
                        if(specC > ad.getSpecC()) { return 1; }
                        else if(specC < ad.getSpecC()) { return -1; }
                        else {
                            if(specD > ad.getSpecD()) { return 1; }
                            else if(specD < ad.getSpecD()) { return -1; }
                            else {
                                return 0;
                            }
                        }
                    }
                }
            }
        }
        else {
            return 0;
        }
    }

    public Declaration getDeclaration() {
        return declaration;
    }

    public int getSpecA() {
        return specA;
    }

    public int getSpecB() {
        return specB;
    }

    public int getSpecC() {
        return specC;
    }

    public int getSpecD() {
        return specD;
    }
}
