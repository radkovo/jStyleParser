package cz.vutbr.web.css;

import java.net.URL;

/**
 * Holds CSS URI value
 * 
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 * 
 */
public interface TermURI extends Term<String> {
    
    public TermURI setBase(URL base);
    public URL getBase();

}
