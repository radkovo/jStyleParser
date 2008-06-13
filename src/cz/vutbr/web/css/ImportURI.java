package cz.vutbr.web.css;

import java.util.List;

/**
 * ImportUri
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface ImportURI {
  
    public String getUri();

    public void setUri(String uri);

    public List<String> getMedia();
    
    public void check(String path) throws StylesheetNotValidException;
}
