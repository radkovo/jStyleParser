package cz.vutbr.web.csskit;

import java.util.Collections;
import java.util.List;

import cz.vutbr.web.css.Rule;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheetNotValidException;

/**
 * RuleMedia
 * @author Jan Svercl, VUT Brno, 2008,
 * 			modified by Karel Piwko, 2008
 * @version 1.0 * Changed constructor to create empty object,
 * 				   node creation moved to parser,
 * 				 * Rewritten toString() method,
 * 				 * Renamed getters/setters according to changed interface		
 */
public class RuleMediaImpl implements RuleMedia {
  
	/** List of medias */
	protected List<String> medias;
	
	/** List of rules */
	protected List<RuleSet> rules;
	
	/**
	 * Creates empty object to be fulfilled by interface methods
	 * @return New empty instance of RuleMedia
	 */
	public RuleMediaImpl() {
		this.medias = Collections.emptyList();
		this.rules = Collections.emptyList();
	}
	
    /*
    public RuleMediaImpl(SimpleNode n) {
    	
        for(int ii = 0; ii < n.jjtGetNumChildren(); ii++) {
            SimpleNode cNode = (SimpleNode)n.jjtGetChild(ii);
            if(cNode.getType().equals("medium")) {
                String mediumName = ((SimpleNode)cNode.jjtGetChild(0)).getImage();
                mediaList.add(mediumName);
            }
            else if(cNode.getType().equals("ruleset")) {
                rulesList.add(new RuleSetImpl(cNode));
            }
        }
        
    }
    */
    
    public List<String> getMedias() {
		return medias;
	}


	public void setMedias(List<String> medias) {
		this.medias = medias;
	}


	public List<RuleSet> getRules() {
		return rules;
	}


	public void setRules(List<RuleSet> rules) {
		this.rules = rules;
	}


	@Override
    public String toString() {
    	return this.toString(0);
    }
    
    public String toString(int depth) {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	// append medias
    	sb = OutputUtil.appendTimes(sb, OutputUtil.DEPTH_DELIM, depth);
    	sb.append(OutputUtil.MEDIA_KEYWORD);    	
    	sb = OutputUtil.appendList(sb, medias, OutputUtil.MEDIA_DELIM);
    	
    	// append rules
    	sb = OutputUtil.appendTimes(sb, OutputUtil.DEPTH_DELIM, depth);
    	sb.append(OutputUtil.RULE_OPENING);
    	sb = OutputUtil.appendList(sb, rules, OutputUtil.RULE_DELIM, depth + 1);
    	sb.append(OutputUtil.RULE_CLOSING);
    	
    	return sb.toString();
    }
    
    public void check(String path) throws StyleSheetNotValidException {
        if(medias.isEmpty()) {
            throw new StyleSheetNotValidException("Media type is missing", path);
        }
        for(String string: medias) {
            if(!string.matches("^(all|aural|braille|embossed|handheld|print|projection|screen|tty|tv)$")) {
                throw new StyleSheetNotValidException("Unknown Media type: " + string, path);
            }
        }
        
        String pathNew = path + " -> Media(";
        boolean first = true;
        for(String mName : medias) {
            if(!first) {
                pathNew += ", ";
            }
            else {
                first = false;
            }
            pathNew += mName;
        }
        pathNew += ")";
        for(Rule rule: rules) {
            rule.check(pathNew);
        }
    }

}
