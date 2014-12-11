package cz.vutbr.web.csskit;

import java.util.Collections;
import java.util.List;

import cz.vutbr.web.css.MediaQuery;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;

/**
 * Implementation of RuleMedia
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 * 
 */
public class RuleMediaImpl extends AbstractRuleBlock<RuleSet> implements RuleMedia {
  
	/** List of medias */
	protected List<MediaQuery> media;
	
	/**
	 * Creates an empty object to be filled by interface methods
	 * @param priority Priority mark
	 */
	protected RuleMediaImpl() {
		this.media = Collections.emptyList();
	}    
    
	@Override
	public List<MediaQuery> getMediaQueries() {
		return media;
	}

	@Override
	public RuleMedia setMediaQueries(List<MediaQuery> medias) {
		this.media = medias;
		return this;
	}
	
    @Override
    public void setStyleSheet(StyleSheet stylesheet)
    {
        super.setStyleSheet(stylesheet);
        //assign the style sheet recursively to the contained rule sets
        for (RuleSet set : list)
            set.setStyleSheet(stylesheet);
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
    	sb = OutputUtil.appendList(sb, media, OutputUtil.MEDIA_DELIM);
    	
    	// append rules
    	sb = OutputUtil.appendTimes(sb, OutputUtil.DEPTH_DELIM, depth);
    	sb.append(OutputUtil.RULE_OPENING);
    	sb = OutputUtil.appendList(sb, list, OutputUtil.RULE_DELIM, depth + 1);
    	sb.append(OutputUtil.RULE_CLOSING);
    	
    	return sb.toString();
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((media == null) ? 0 : media.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof RuleMediaImpl))
			return false;
		RuleMediaImpl other = (RuleMediaImpl) obj;
		if (media == null) {
			if (other.media != null)
				return false;
		} else if (!media.equals(other.media))
			return false;
		return true;
	}   
    
    

}
