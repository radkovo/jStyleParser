package cz.vutbr.web.css;

/**
 * StyleSheetNotValidException
 * @author Jan Svercl, VUT Brno, 2008
 */
@SuppressWarnings("serial")
public final class StyleSheetNotValidException extends Exception {
    
    private String path;
    private String message;

    public StyleSheetNotValidException(String message) {
    	this.message = message;
    }
    
    public StyleSheetNotValidException(String message, String path) {
        this.path = path;
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
