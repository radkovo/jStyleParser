/**
 * 
 */
package cz.vutbr.web.csskit.antlr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;

import cz.vutbr.web.css.SourceLocator;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CharStream;

/**
 * Wraps ANTLR stream with useful decorations,
 * mainly to allow switching encoding on lexer
 * @author kapy
 *
 */
public class CSSInputStream implements CharStream {

	/**
	 * ANTLR input
	 */
	private CharStream input;
	
	/**
	 * Base URL
	 */
	private URL base = null;
	
	/**
	 * Source map
	 */
	private SourceMap sourceMap = null;
	
	/**
	 * For reading stream again with different encoding.
	 */
	private CSSSourceReader.CSSInputStream source = null;
	
	public static CSSInputStream newInstance(String input, URL base) throws IOException {
		return newInstance(new StringReader(input), base, null);
	}
	
	public static CSSInputStream newInstance(CSSSourceReader.CSSInputStream input) throws IOException {
		CSSInputStream stream = newInstance(input.stream, input.base, input.sourceMap);
		// store input stream so that we can reset the stream when @charset is encountered
		stream.source = input;
		return stream;
	}
	
	public static CSSInputStream newInstance(Reader input, URL base, SourceMap sourceMap) throws IOException {
		CSSInputStream stream = new CSSInputStream();
		stream.input = new ANTLRReaderStream(new BufferedReader(input));
		stream.base = base;
		stream.sourceMap = sourceMap;
		return stream;
	}

	// Sole constructor
	// force using factory methods
	private CSSInputStream() {
	}
	
	/* (non-Javadoc)
	 * @see org.antlr.runtime.CharStream#LT(int)
	 */
	public int LT(int arg0) {
		return input.LT(arg0);
	}

	/* (non-Javadoc)
	 * @see org.antlr.runtime.CharStream#getCharPositionInLine()
	 */
	public int getCharPositionInLine() {
		return input.getCharPositionInLine();
	}

	/* (non-Javadoc)
	 * @see org.antlr.runtime.CharStream#getLine()
	 */
	public int getLine() {
		return input.getLine();
	}

	/* (non-Javadoc)
	 * @see org.antlr.runtime.CharStream#setCharPositionInLine(int)
	 */
	public void setCharPositionInLine(int arg0) {
		input.setCharPositionInLine(arg0);
	}

	/* (non-Javadoc)
	 * @see org.antlr.runtime.CharStream#setLine(int)
	 */
	public void setLine(int arg0) {
		input.setLine(arg0);
	}

	/* (non-Javadoc)
	 * @see org.antlr.runtime.CharStream#substring(int, int)
	 */
	public String substring(int arg0, int arg1) {
		return input.substring(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see org.antlr.runtime.IntStream#LA(int)
	 */
	public int LA(int arg0) {
		return input.LA(arg0);
	}

	/* (non-Javadoc)
	 * @see org.antlr.runtime.IntStream#consume()
	 */
	public void consume() {
		input.consume();
	}
	
	/* (non-Javadoc)
	 * @see org.antlr.runtime.IntStream#index()
	 */
	public int index() {
		return input.index();
	}

	/* (non-Javadoc)
	 * @see org.antlr.runtime.IntStream#mark()
	 */
	public int mark() {
		return input.mark();
	}

	/* (non-Javadoc)
	 * @see org.antlr.runtime.IntStream#release(int)
	 */
	public void release(int arg0) {
		input.release(arg0);
	}

	/* (non-Javadoc)
	 * @see org.antlr.runtime.IntStream#rewind()
	 */
	public void rewind() {
		input.rewind();
	}

	/* (non-Javadoc)
	 * @see org.antlr.runtime.IntStream#rewind(int)
	 */
	public void rewind(int arg0) {
		input.rewind(arg0);
	}

	/* (non-Javadoc)
	 * @see org.antlr.runtime.IntStream#seek(int)
	 */
	public void seek(int arg0) {
		input.seek(arg0);
	}

	/* (non-Javadoc)
	 * @see org.antlr.runtime.IntStream#size()
	 */
	public int size() {
		return input.size();
	}
	
	/* (non-Javadoc)
	 * @see org.antlr.runtime.CharStream#getSourceName()
	 */
	public String getSourceName() {
		return base!=null ? base.toString() : "";
	}
	
	/**
	 * Obtain the source location for resolving relative URLs and for use in messages
	 */
	public SourceLocator getSourceLocator() {
		int line = getLine() - 1;
		int column = getCharPositionInLine();
		SourceLocator loc = null;
		if (sourceMap != null)
			loc = sourceMap.floor(line, column);
		if (loc == null)
			loc = new SourceLocator() {
				public URL getURL() { return base; }
				public int getLineNumber() { return line; }
				public int getColumnNumber() { return column; }
				@Override
				public String toString() {
					return ((base == null) ? "<internal>" : base.toString()) + ":" + line + ":" + column;
				}
				};
		return loc;
	}
	
	/**
	 * Sets a new encoding for the input stream. <b>Warning:</b> this resets the stream
	 * i.e. a new connection is opened and all the data is read again.
	 * @param enc The new encoding name.
	 * @throws IOException
	 */
	public void setEncoding(Charset encoding) throws IOException {
		if (source != null) {
			try {
				input = new ANTLRReaderStream(new BufferedReader(source.reread(encoding)));
			} finally {
				source = null; // subsequent @charset rules are ignored
			}
		}
	}
}
