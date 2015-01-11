/**
 * 
 */
package cz.vutbr.web.css;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * A network processor that is able to obtain an input stream from a URL.
 * 
 * @author burgetr
 */
public interface NetworkProcessor
{

    /**
     * Fetches the resource with the given URL and creates a stream containing
     * the resource contents.
     * 
     * @param url Resource URL.
     * @return input stream that reads resource contents
     * @throws IOException when the stream cannot be obtained for any reason
     */
    public InputStream fetch(URL url) throws IOException;
    
}
