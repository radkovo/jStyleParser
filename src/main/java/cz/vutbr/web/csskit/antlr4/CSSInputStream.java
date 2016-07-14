/**
 *
 */
package cz.vutbr.web.csskit.antlr4;

import cz.vutbr.web.css.NetworkProcessor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.misc.Interval;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
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

        CSSInputStream stream = new CSSInputStream();
        stream.rawData = source;
        stream.encoding = encoding;
        //stream.source = is;
        stream.input = new ANTLRInputStream(br);

        return stream;
    }

    public static CSSInputStream urlStream(URL source, NetworkProcessor network, String encoding) throws IOException {
        InputStream is = network.fetch(source);
        if (encoding == null) {
            encoding = Charset.defaultCharset().name();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is, encoding));

        CSSInputStream stream = new CSSInputStream();
        stream.base = source;
        stream.network = network;
        stream.url = source;
        stream.encoding = encoding;
        stream.source = is;
        stream.input = new ANTLRInputStream(br);
        return stream;
    }

    // Sole constructor
    // force using factory methods
    private CSSInputStream() {
    }

    @Override
    public int LA(int i) {
        return input.LA(i);
    }

    @Override
    public int LT(int i) {
        return input.LT(i);
    }

    @Override
    public void consume() {
        input.consume();
    }

    @Override
    public String getText(Interval interval) {
        return input.getText(interval);
    }

    @Override
    public int index() {
        return input.index();
    }

    @Override
    public void load(Reader arg0, int arg1, int arg2) throws IOException {
        input.load(arg0, arg1, arg2);
    }

    @Override
    public int mark() {
        return input.mark();
    }

    @Override
    public void release(int marker) {
        input.release(marker);
    }

    @Override
    public void reset() {
        input.reset();
    }

    @Override
    public void seek(int index) {
        input.seek(index);
    }

    @Override
    public int size() {
        return input.size();
    }

    @Override
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
                int oldindex = input.index();
                source.close();
                encoding = enc;
                CSSInputStream newstream = urlStream(url, network, encoding);
                input = newstream.input;
                input.seek(oldindex);
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
