/**
 * 
 */
package cz.vutbr.web.csskit;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;

import cz.vutbr.web.css.NetworkProcessor;

import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;

/**
 * Default implementation of the NetworkProcessor that is used when no other
 * implementation is provided. This implementation is based on the java built-in 
 * URLConnection mechanism.
 * 
 * @author burgetr
 */
public class DefaultNetworkProcessor implements NetworkProcessor {

	@Override
	public Reader fetch(URL url, Charset encoding, boolean forceEncoding, boolean assertEncoding) throws IOException {
		URLConnection connection = url.openConnection();
		InputStream is;
		if ("gzip".equalsIgnoreCase(connection.getContentEncoding()))
			is = new GZIPInputStream(connection.getInputStream());
		else
			is = connection.getInputStream();
		InputStreamReader r = detectEncodingAndSkipBOM(is, connection, encoding, forceEncoding);
		if (assertEncoding) {
			if (encoding == null)
				throw new IllegalArgumentException("encoding must not be null");
			if (!encoding.equals(getEncoding(r)))
				throw new IOException("Failed to read URL as " + encoding + ": " + url);
		}
		return r;
	}

	/**
	 * - Skip the BOM (byte order mark)
	 * - Determine the encoding
	 *   - if {@code forceEncoding} is {@code true}, use {@code encoding}
	 *   - otherwise look at the BOM (if any)
	 *   - otherwise if the protocol is HTTP, look at "Content-Type" attribute of the HTTP header
	 */
	protected InputStreamReader detectEncodingAndSkipBOM(InputStream is, URLConnection connection,
	                                                     Charset encoding, boolean forceEncoding) throws IOException {
		BOMInputStream bis = new BOMInputStream(is);
		if (forceEncoding)
			;
		else {
			ByteOrderMark bom = bis.getBOM();
			if (bom != null)
				try {
					encoding = Charset.forName(bom.getCharsetName());
				} catch (Throwable e) {
					throw new IllegalStateException(); // should not happen
				}
			else if (connection != null) {
				String contentType = connection.getContentType();
				if (contentType != null)
					for (String s : contentType.split("\\s;\\s"))
						if (s.startsWith("charset="))
							try {
								encoding = Charset.forName(s.substring("charset=".length()).trim());
								break;
							} catch (Throwable e) {
								throw new IllegalStateException(); // should not happen
							}
			}
		}
		if (encoding == null)
			throw new IllegalArgumentException("encoding must not be null");
		return new InputStreamReader(bis, encoding);
	}

	protected static Charset getEncoding(InputStreamReader r) {
		try {
			return Charset.forName(r.getEncoding());
		} catch (Throwable e) {
			throw new IllegalStateException(); // should not happen
		}
	}
}
