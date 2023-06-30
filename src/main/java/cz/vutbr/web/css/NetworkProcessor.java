/**
 * 
 */
package cz.vutbr.web.css;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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
     * @param encoding Character encoding to use if it can not be determined or if {@code
     *                 forceEncoding} is {@code true}. Must not be {@code null}.
     * @param forceEncoding Whether to use {@code encoding} even if the character encoding can be
     *                      determined.
     * @param assertEncoding Whether to assert that the character encoding of the result equals
     *                       {@code encoding}.
     * @return input stream that reads resource contents
     * @throws IOException if {@code assertEncoding} is {@code true} and the character encoding of
     *                     the result does not equal {@code encoding}, or if the stream cannot be
     *                     obtained for any other reason.
     */
    public Reader fetch(URL url, Charset encoding, boolean forceEncoding, boolean assertEncoding) throws IOException;

    public default Reader fetch(URL url) throws IOException {
        return fetch(url, StandardCharsets.UTF_8, false, false);
    }
}
