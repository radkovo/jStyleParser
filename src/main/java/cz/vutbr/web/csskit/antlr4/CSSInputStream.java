/**
 *
 */
package cz.vutbr.web.csskit.antlr4;

import cz.vutbr.web.css.NetworkProcessor;
import org.antlr.v4.runtime.ANTLRInputStream;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Wraps ANTLR stream with useful decorations,
 * mainly to allow switching encoding on lexer
 *
 * @author kapy
 */
public class CSSInputStream extends ANTLRInputStream {

    /**
     * ANTLR input
     */
    private ANTLRInputStream input;

    /**
     * Raw data of string passed, if any
     */
    private String rawData;

    /**
     * Base location of this input stream
     */
    private URL base = null;

    /**
     * Source URL for URL streams, null for string streams
     */
    private URL url;

    /**
     * Network processor used for obtaining data from URLs
     */
    private NetworkProcessor network;

    /**
     * Source input stream for URL streams, null for string streams
     */
    private InputStream source = null;

    /**
     * Encoding of file or string. If <code>null</code>
     */
    private String encoding;


    public static CSSInputStream stringStream(String source) throws IOException {
        InputStream is = new ByteArrayInputStream(source.getBytes());
        String encoding = Charset.defaultCharset().name();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, encoding));

        CSSInputStream stream = new CSSInputStream(br);
        stream.rawData = source;
        stream.encoding = encoding;
        //stream.source = is;
        stream.input = stream;

        return stream;
    }

    public static CSSInputStream urlStream(URL source, NetworkProcessor network, String encoding) throws IOException {
        InputStream is = network.fetch(source);
        if (encoding == null) {
            encoding = Charset.defaultCharset().name();
        }
        BufferedReader br = new BufferedReader(
                new InputStreamReader(is, encoding));

        CSSInputStream stream = new CSSInputStream(br);

        stream.base = source;
        stream.network = network;
        stream.url = source;
        stream.encoding = encoding;
        stream.source = is;
        stream.input = stream;
        return stream;
    }

    // Sole constructor
    // force using factory methods
    private CSSInputStream() {
    }

    private CSSInputStream(Reader r) throws IOException {
        super(r);
    }


    /* (non-Javadoc)
     * @see org.antlr.runtime.CharStream#getSourceName()
     */
    public String getSourceName() {
        return base != null ? base.toString() : "";
    }

    /**
     * Obtains the current base URL used for locating the eventual imported style sheets.
     *
     * @return The base URL.
     */
    public URL getBase() {
        return base;
    }

    /**
     * Sets the base URL used for locating the eventual imported style sheets.
     *
     * @param base The new base URL.
     */
    public void setBase(URL base) {
        this.base = base;
    }

    /**
     * Obtains current character encoding used for processing the style sheets.
     *
     * @return The charset name.
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * Sets a new encoding for the input stream. <b>Warning:</b> this resets the stream
     * i.e. a new connection is opened and all the data is read again.
     *
     * @param enc The new encoding name.
     * @throws IOException
     */
    public void setEncoding(String enc) throws IOException {
        if (source != null) //applicapble to URL streams only
        {
            String current = encoding;
            if (current == null)
                current = Charset.defaultCharset().name();
            if (!current.equalsIgnoreCase(enc)) {
                source.close();
                encoding = enc;
                CSSInputStream newstream = urlStream(url, network, encoding);
                input = newstream.input;
            }
        }
    }

    /**
     * @return the raw data
     */
    public String getRawData() {
        return rawData;
    }

    @Override
    public String toString() {
        return "[CSSInputStream - base: " + getBase() + ", encoding: " + getEncoding();
    }

}
