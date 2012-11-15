/**
 * 
 */
package org.fit.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLStreamHandler;


/**
 * URL handler for the data: URI scheme.
 * @author burgetr
 */
public class DataURLHandler extends URLStreamHandler 
{
    protected String mime = "text/plain";
    protected String charset = "US-ASCII";
    protected boolean encoded = false;
    

    public DataURLHandler() 
    {
        super();
    }

    @Override
    protected URLConnection openConnection(URL u) throws IOException 
    {
        if ("data".equals(u.getProtocol()))
        {
            String path = u.getPath();
            if (path == null || path.isEmpty())
                throw new IOException("No data specified");
            
            String data;
            String[] parts = path.split(",", 2);
            if (parts.length == 2)
            {
                data = parts[1];
                String[] hparts = parts[0].split(";", 3);
                for (String part : hparts)
                {
                    if (part.equalsIgnoreCase("base64"))
                        encoded = true;
                    else if (part.startsWith("charset="))
                        charset = part.substring(8);
                    else
                        mime = part;
                }
            }
            else
            {
                data = parts[0];
            }
            
            byte[] bytes;
            if (!encoded)
                bytes = URLDecoder.decode(data, charset).getBytes(charset);
            else
                try {
                    bytes = Base64Coder.decode(URLDecoder.decode(data, charset));
                } catch (Exception e) {
                    throw new IOException("Couldn't decode base64 data", e);
                }
            
            return new DataURLConnection(u, mime, charset, bytes);
        }
        else
            throw new IOException("Only the 'data' protocol is supported by this URL handler");
    }
    
    /**
     * Creates an URL from string while considering the data: scheme.
     * @param base the base URL used for relative URLs
     * @param urlstring the URL string
     * @return resulting URL
     * @throws MalformedURLException
     */
    public static URL createURL(URL base, String urlstring) throws MalformedURLException
    {
        if (urlstring.startsWith("data:"))
            return new URL(null, urlstring, new DataURLHandler());
        else
            return new URL(base, urlstring);
    }
    
}

