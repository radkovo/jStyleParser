package cz.vutbr.web.css;

/**
 * StyleSheetNotValidException
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 */
public final class StyleSheetNotValidException extends Exception {

    /**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;

	public StyleSheetNotValidException(String message) {
    	super(message);
    }
    
    public StyleSheetNotValidException(String message, Throwable cause) {
    	super(message, cause);
    }
}
