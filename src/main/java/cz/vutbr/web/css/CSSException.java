package cz.vutbr.web.css;

/**
 * CSSException
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 */
public final class CSSException extends Exception {

    /**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;

	public CSSException(String message) {
    	super(message);
    }
    
    public CSSException(String message, Throwable cause) {
    	super(message, cause);
    }
}
