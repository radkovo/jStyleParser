package cz.vutbr.web.csskit.antlr;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Object that is able to obtain an input stream from a CSSSource.
 *
 * @author bertfrees
 */
public interface CSSSourceReader {

	public static class CSSInputStream {

		/**
		 * The character stream
		 */
		public final Reader stream;

		/**
		 * Base URL for resolving relative URLs against if <code>sourceMap</code> is
		 * <code>null</code>, if there is no mapping for a certain token, or when the base URL is
		 * needed in contexts where there is no current token.
		 */
		public final URL base;

		/**
		 * Maps locations within this {@link CSSInputStream} to locations within the original
		 * sources from which the {@link CSSInputStream} was read or compiled.
		 */
		public final SourceMap sourceMap;

		public CSSInputStream(Reader stream, URL base, SourceMap sourceMap) {
			this.stream = stream;
			this.base = base;
			this.sourceMap = sourceMap;
		}

		/**
		 * Read the source again using a different character encoding. Closes {@code stream}.
		 *
		 * @throws IOException if the character encoding can not be changed or if the source can not
		 *                     be read a second time.
		 */
		public Reader reread(Charset encoding) throws IOException {
			throw new IOException();
		}
	}

	/**
	 * Whether this reader supports sources of the given media type.
	 *
	 * @param mediaType The media type.
	 * @param url If the source is of type {@link CSSSource.SourceType.URL}, the URL is provided in
	 *            case <code>mediaType</code> is null and the media type needs to be inferred.
	 */
	public boolean supportsMediaType(String mediaType, URL url);

	/**
	 * Read the source.
	 */
	public CSSInputStream read(CSSSource source) throws IOException;

}
