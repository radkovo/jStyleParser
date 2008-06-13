package cz.vutbr.web.css;

/**
 * StyleSheetNotValidException
 * @author Jan Svercl, VUT Brno, 2008
 */
public class StylesheetNotValidException extends Exception {
    
    private String path;
    String message;

    public StylesheetNotValidException(String message, String path) {
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
