package cz.vutbr.web.css;

import java.util.List;

/**
 * RuleMedia
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface RuleMedia extends Rule {
  
    public List<String> getMediaList();

    public List<RuleSet> getRulesList();
    
    public String toString(int depth);
    
    public void check(String path) throws StylesheetNotValidException;
}
