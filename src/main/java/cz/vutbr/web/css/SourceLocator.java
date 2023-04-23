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
	 * The URL or {@code null} if not available.
	 */
	public URL getURL();
	/**
	 * The line number (0-based) or {@code -1} if not available.
	 */
	public int getLineNumber();
	/**
	 * The character position (0-based) within the line or {@code -1} if not available.
	 */
	public int getColumnNumber();

	public static String toString(SourceLocator locator) {
		String s = "";
		URL base = locator.getURL();
		if (base != null)
			s += base;
		else
			s += "<internal>";
		int line = locator.getLineNumber();
		if (line >= 0) {
			s += (":" + line);
			int column = locator.getColumnNumber();
			if (column >= 0) {
				s += (":" + column);
			}
		}
		return s;
	}
}
