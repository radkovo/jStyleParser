package cz.vutbr.web.css;

/**
 * Contains CSS rules associated with a specific area in the page margin. 
 * 
 * @author Bert Frees, 2012
 */
public interface RuleMargin extends RuleBlock<Declaration>, PrettyOutput {

	public enum MarginArea {
		
		TOPLEFTCORNER("top-left-corner"),
		TOPLEFT("top-left"),
		TOPCENTER("top-center"),
		TOPRIGHT("top-right"),
		TOPRIGHTCORNER("top-right-corner"),
		BOTTOMLEFTCORNER("bottom-left-corner"),
		BOTTOMLEFT("bottom-left"),
		BOTTOMCENTER("bottom-center"),
		BOTTOMRIGHT("bottom-right"),
		BOTTOMRIGHTCORNER("bottom-right-corner"),
		LEFTTOP("left-top"),
		LEFTMIDDLE("left-middle"),
		LEFTBOTTOM("left-bottom"),
		RIGHTTOP("right-top"),
		RIGHTMIDDLE("right-middle"),
		RIGHTBOTTOM("right-bottom");
		
		public final String value;
		
		private MarginArea(String value) {
			this.value = value;
		}
	}

	/**
	 * Returns margin area
	 * @return Margin area
	 */
	public MarginArea getMarginArea();	
	
}
