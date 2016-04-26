package cz.vutbr.web;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.MediaExpression;
import cz.vutbr.web.css.MediaQuery;
import cz.vutbr.web.css.Rule;
import cz.vutbr.web.css.RuleBlock;
import cz.vutbr.web.css.RuleFontFace;
import cz.vutbr.web.css.RuleImport;
import cz.vutbr.web.css.RuleMargin;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.RulePage;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.RuleViewport;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.Selector.ElementAttribute;
import cz.vutbr.web.css.Selector.ElementClass;
import cz.vutbr.web.css.Selector.ElementDOM;
import cz.vutbr.web.css.Selector.ElementID;
import cz.vutbr.web.css.Selector.ElementName;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermAngle;
import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermExpression;
import cz.vutbr.web.css.TermFrequency;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermInteger;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermLengthOrPercent;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermNumber;
import cz.vutbr.web.css.TermNumeric;
import cz.vutbr.web.css.TermPair;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.css.TermResolution;
import cz.vutbr.web.css.TermString;
import cz.vutbr.web.css.TermTime;
import cz.vutbr.web.css.TermURI;

public class Nico {
  
  public Nico() {
    loadStylesheet(null);
  }

  private void loadStylesheet(URL file) {

    StyleSheet style = null;

    if (file == null) {
      try {
        // style = CSSFactory.parse(new
        // URL("http://nicasso.nl/assets/css/style.css"), "UTF-8");
        style = CSSFactory.parse("style.css", "UTF-8");
        if(style.getComment() != null) {
	        System.out.println("Stylesheet comment: "+style.getComment().getText());
	        System.out.println("Stylesheet comment location: "+style.getComment().getLocation().toString());
        }
        
        rulesBlock(style);
      } catch (CSSException | IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {
      try {
        style = CSSFactory.parse(file, "UTF-8");
        rulesBlock(style);
      } catch (CSSException | IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

  }

  private void rulesBlock(Collection<RuleBlock<?>> rules) {
	System.out.println("Collection<RuleBlock<?>>");
    for (RuleBlock<?> ruleBlock : rules) {
    	
    	if (ruleBlock.getComment() != null) {
    		System.out.println("");
    		System.out.println("ruleBlock comment: "+ruleBlock.getComment().getText());
    		System.out.println("ruleBlock comment location: "+ruleBlock.getComment().getLocation().toString());
    	}
    	
    	
      if (ruleBlock instanceof RuleSet) {
    	  System.out.println("");
    	  System.out.println("RULE: "+ruleBlock.getLocation().toString());
        ruleSet(ruleBlock);
      } else if (ruleBlock instanceof RuleMedia) {
    	  System.out.println("");
    	  System.out.println("RULE: "+ruleBlock.getLocation().toString());
        ruleMedia(ruleBlock);
      } else if (ruleBlock instanceof RuleFontFace) {
    	  System.out.println("");
    	  System.out.println("RULE: "+ruleBlock.getLocation().toString());
        ruleFontFace(ruleBlock);
      } else if (ruleBlock instanceof RuleMargin) {
    	  System.out.println("");
    	  System.out.println("RULE: "+ruleBlock.getLocation().toString());
        ruleMargin(ruleBlock);
      } else if (ruleBlock instanceof RulePage) {
    	  System.out.println("");
    	  System.out.println("RULE: "+ruleBlock.getLocation().toString());
        rulePage(ruleBlock);
      } else if (ruleBlock instanceof RuleViewport) {
    	  System.out.println("");
    	  System.out.println("RULE: "+ruleBlock.getLocation().toString());
        ruleViewport(ruleBlock);
      } else if (ruleBlock instanceof RuleImport) {
    	  System.out.println("");
    	  System.out.println("RULE: "+ruleBlock.getLocation().toString());
        ruleImport((RuleImport) ruleBlock);
      } else {
        System.out.println("This is a exotic block");
      }
      // System.out.println("");
    }
  }

  private void ruleSet(RuleBlock<?> ruleBlock) {
    System.out.println("");
    System.out.println("This is a ruleset");
    RuleSet set = (RuleSet) ruleBlock;
    selectors(set.getSelectors());
	
    // System.out.println("Declarations:");
    for (Declaration decl : set) {
		if (decl.getComment() != null) {
			System.out.println("Declaration comment: "+decl.getComment().getText());
			System.out.println("Declaration comment location: "+decl.getComment().getLocation().toString());
		}
		
		System.out.println("Declaration location: "+decl.getLocation().toString());
		
      System.out.println(decl.getSource());
      System.out.println("  Property: " + decl.getProperty());
      System.out.println("  Values: ");
      declarations(decl);
    }
  }

  private void ruleMedia(RuleBlock<?> ruleBlock) {
    System.out.println("");
    System.out.println("This is a ruleMedia");
    RuleMedia media = (RuleMedia) ruleBlock;
    mediaQuery(media.getMediaQueries());
    for (RuleSet set : media) {
      ruleSet(set);
    }
  }
  
  private void mediaQuery(List<MediaQuery> list) {
    for(MediaQuery m : list) {
      System.out.println("MEDIAQUERY: "+m.getType()+" - "+m.toString());
      mediaExpression(m);
    }
  }
  
  private void mediaExpression(MediaQuery m) {
    for(MediaExpression a : m) {
      System.out.println("MEDIAEXPRESSION: "+a.getFeature() + " - " +a.toString());
      mediaTerms(a);
    }
  }
  
  private void mediaTerms(MediaExpression m) {
    for(Term a : m) {
      System.out.println("(MEDIA)TERMS: "+a.toString());
      terms(a);
    }
  }

  private void ruleFontFace(RuleBlock<?> ruleBlock) {
    System.out.println("");
    System.out.println("This is a ruleFontFace");
    RuleFontFace media = (RuleFontFace) ruleBlock;
    // System.out.println("FontFace" + media.f);
    for (Declaration set : media) {
      declarations(set);
    }
  }
  
  private void ruleImport(RuleImport ruleBlock) {
	    System.out.println("");
	    System.out.println("This is a ruleImport");
	    System.out.println(ruleBlock.getURI());
  }

  private void ruleMargin(RuleBlock<?> ruleBlock) {
    System.out.println("");
    System.out.println("This is a ruleMargin");
    RuleMargin media = (RuleMargin) ruleBlock;
    System.out.println("MaginAre: " + media.getMarginArea());
    for (Declaration set : media) {
      declarations(set);
    }
  }

  private void rulePage(RuleBlock<?> ruleBlock) {
    System.out.println("");
    System.out.println("This is a rulePage");
    RulePage media = (RulePage) ruleBlock;
    System.out.println("Name: " + media.getName());
    for (Rule<?> set : media) {
      rules(set);
    }
  }

  private void ruleViewport(RuleBlock<?> ruleBlock) {
    System.out.println("");
    System.out.println("This is a ruleViewport");
    RuleViewport media = (RuleViewport) ruleBlock;
    // System.out.println("MaginAre: " + media.getMarginArea());
    for (Declaration set : media) {
      declarations(set);
    }
  }

  private void realSelectors(CombinedSelector s2) {
    for (Selector s : s2) {
    	System.out.println("SELECTOR LOCATION :"+s.getLocation().toString());
      for (Selector.SelectorPart sp : s) {
        System.out.println(sp.toString());
        
        if(sp instanceof ElementClass) {
          System.out.println("Class");
          System.out.println("Class location:"+((ElementClass) sp).getLocation().toString());
          System.out.println(((ElementClass) sp).getClassName());
        } else if (sp instanceof ElementID) {
          System.out.println("ID");
          System.out.println("Id location:"+((ElementID) sp).getLocation().toString());
          System.out.println(((ElementID) sp).getID());
        } else if (sp instanceof ElementDOM) {
          System.out.println("DOM");
          System.out.println(((ElementDOM) sp).getElement().getTagName());
        } else if (sp instanceof ElementAttribute) {
          System.out.println("ATTRIBUTE");
          System.out.println(((ElementAttribute) sp).getAttribute());
          System.out.println(((ElementAttribute) sp).getOperator());
          System.out.println(((ElementAttribute) sp).getValue());
        } else if (sp instanceof ElementName) {
          System.out.println("NAME");
          System.out.println(((ElementName) sp).getName());
        } else {
          System.out.println("EXOTIC ELEMENT");
        }
        
        System.out.println("COMBINATOR: " + s.getCombinator());
      }
    }
  }

  private void mediaExpression(MediaExpression exp) {
    System.out.println("mediaExpression: " + exp.getFeature());

  }

  private void mediaQuery(MediaQuery q) {
    System.out.println("mediaQuery: " + q.getType());
  }

  private void rules(Rule<?> rules) {
    System.out.println("Rules");
    for (Object rule : rules) {
      if (rule instanceof CombinedSelector) {
        // realSelectors((Collection<Selector.SelectorPart>) rule);
      } else if (rule instanceof Declaration) {
        declarations((Declaration) rule);
      } else if (rule instanceof MediaExpression) {
        mediaExpression((MediaExpression) rule);
      } else if (rule instanceof MediaQuery) {
        mediaQuery((MediaQuery) rule);
      } else if (rule instanceof RuleBlock) {
        rulesBlock((RuleBlock) rule);
      } else if (rule instanceof RuleFontFace) {
        ruleFontFace((RuleFontFace) rule);
      } else if (rule instanceof RuleMargin) {
        ruleMargin((RuleMargin) rule);
      } else if (rule instanceof RulePage) {
        rulePage((RulePage) rule);
      } else if (rule instanceof RuleSet) {
        ruleSet((RuleSet) rule);
      } else if (rule instanceof RuleViewport) {
        ruleViewport((RuleViewport) rule);
      } else if (rule instanceof Selector) {
        selectors((CombinedSelector[]) rule);
      } else if (rule instanceof StyleSheet) {
        rulesBlock((Collection<RuleBlock<?>>) rule);
      }
      // System.out.println("");
    }
  }

  private void declarations(Declaration decl) {
    // System.out.println("Declaration source: "+decl.getSource());
    System.out.println("Important? " + decl.isImportant());
    for (Term<?> term : decl) {
      terms(term);
    }
  }

  private void terms(Term<?> term) {
    if (term instanceof TermAngle) {
      System.out.println("    Angle: " + term.getValue());
    } else if (term instanceof TermColor) {
      System.out.println("    Color: " +term.toString()+" - "+ term.getValue() + " - " + term.getOperator());
      System.out.println(((TermColor) term).getOriginalFormat());
    } else if (term instanceof TermExpression) {
      System.out.println("    Expression: " + term.getValue()); 
    } else if (term instanceof TermFrequency) {
      System.out.println("    Frequency: " + term.getValue());
    } else if (term instanceof TermFunction) {
      System.out.println("    Function: " + term.getValue());
    } else if (term instanceof TermIdent) {
      System.out.println("    Ident: " + term.getValue());
    } else if (term instanceof TermInteger) {
      System.out.println("    Integer: " + term.getValue());
    } else if (term instanceof TermLength) {
      System.out.println("    Length: " + term.getValue());
    } else if (term instanceof TermLengthOrPercent) {
      TermLengthOrPercent test = (TermLengthOrPercent) term;
      System.out.println("    Length or Percent: " + term.getValue() + " - Percent? " + test.isPercentage());
    } else if (term instanceof TermList) {
      System.out.println("    List: " + term.getValue());
    } else if (term instanceof TermNumber) {
      System.out.println("    Number: " + term.getValue());
    } else if (term instanceof TermNumeric) {
      System.out.println("    Numeric: " + term.getValue());
    } else if (term instanceof TermPair) {
      System.out.println("    Pair: " + term.getValue());
    } else if (term instanceof TermPercent) {
      System.out.println("    Percent: " + term.getValue());
    } else if (term instanceof TermResolution) {
      System.out.println("    Resolution: " + term.getValue());
    } else if (term instanceof TermString) {
      System.out.println("    String: " + term.getValue());
    } else if (term instanceof TermTime) {
      System.out.println("    Time: " + term.getValue());
    } else if (term instanceof TermURI) {
      System.out.println("    URI: " + term.getValue());
    }
  }

  private void selectors(CombinedSelector[] combinedSelectors) {
    for (CombinedSelector s : combinedSelectors) {
      System.out.println(s);
      realSelectors(s);
    }
  }
  
  public static void main(String[] arguments) throws Exception {
    System.out.println("NICO");
    new Nico();
  }
  
}
