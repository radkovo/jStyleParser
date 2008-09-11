/**
 * 
 */
package cz.vutbr.web.csskit.antlr;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CharStream;

/**
 * @author kapy
 *
 */
public class CSSInputStream implements CharStream {

	/**
	 * ANTLR input
	 */
	protected CharStream input;	
	
	/**
	 * Raw data of string passed, if any
	 */
	protected String rawData;
	
	/**
	 * File name, if any
	 */
	protected String fileName;
	
	/**
	 * Path to which others are relative.
	 * Used for import, determined from fileName used to initialize
	 * the input stream.
	 * 
	 * Example:
	 * <p>
	 * Local path: <code>data/simple/imp.css</code> is passed.
	 * During initialization absolute path is determined, then
	 * its parent is used as relativeRoot for all imports found 
	 * in that resource.
	 * </p>
	 * 
	 */
	protected String relativeRoot;
	
	/**
	 * Encoding of file or string. If <code>null</code>
	 */
	protected String encoding;
	
	/**
	 * Creates input stream from reading from file
	 * @param fileName Name of file
	 * @param encoding Encoding of file, if <code>null</code> default is used
	 * @throws IOException If file not found
	 */
	public CSSInputStream(String fileName, String encoding) throws IOException {
		this.fileName = fileName;
		this.encoding = encoding;
		
		File file = new File(fileName);
		this.relativeRoot = file.getParent() + File.separator;
		this.input = new ANTLRFileStream(fileName, encoding);
	}
	
	/**
	 * Creates input stream from string
	 * @param css Data to be parsed
	 * @throws IOException If any problem during read occurs
	 */
	public CSSInputStream(String css) throws IOException {
		this.rawData = css;
		this.encoding = Charset.defaultCharset().name();
		this.fileName = null;
		this.relativeRoot = "";
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new ByteArrayInputStream(css.getBytes()), encoding));
		this.input = new ANTLRReaderStream(br);
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
	 * @see org.antlr.runtime.IntStream#getSourceName()
	 */
	public String getSourceName() {
		return fileName;
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

	/**
	 * @return the relativeRoot
	 */
	public String getRelativeRoot() {
		return relativeRoot;
	}

	
	
}
