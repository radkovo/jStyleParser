/**
 * 
 */
package cz.vutbr.web.csskit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

import cz.vutbr.web.css.NetworkProcessor;

/**
 * Default implementation of the NetworkProcessor that is used when no other
 * implementation is provided. This implementation is based on the java built-in 
 * URLConnection mechanism.
 * 
 * @author burgetr
 */
public class DefaultNetworkProcessor implements NetworkProcessor
{

    @Override
    public InputStream fetch(URL url) throws IOException
    {
        URLConnection con = url.openConnection();
        InputStream is;
        if ("gzip".equalsIgnoreCase(con.getContentEncoding()))
            is = new GZIPInputStream(con.getInputStream());
        else
            is = con.getInputStream();
        
        return is;
    }

}
