package cz.vutbr.web.css;

import java.net.URL;

/**
 * Location within a source file or input stream.
 *
 * Modelled after {@link javax.xml.transform.SourceLocator} but with a {@link #getURL} method
 * instead of {@link javax.xml.transform.SourceLocator#getSystemId}.
 */
public interface SourceLocator {
	/**
	 * The URL.
	 */
	public URL getURL();
	/**
	 * The line number.
	 */
	public int getLineNumber();
	/**
	 * The character position within the line.
	 */
	public int getColumnNumber();
}
