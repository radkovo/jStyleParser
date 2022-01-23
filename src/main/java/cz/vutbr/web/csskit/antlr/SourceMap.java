package cz.vutbr.web.csskit.antlr;

import cz.vutbr.web.css.SourceLocator;

/**
 * Associates locations within a {@link CSSInputStream} with locations within the sources from which
 * the {@link CSSInputStream} was read or compiled.
 */
public interface SourceMap {

	/**
	 * Get the original location associated with <code>line</code> and <code>column</code>, or
	 * <code>null</code> if there is no such location.
	 */
	public SourceLocator get(int line, int column);

	/**
	 * Get the original location associated with <code>line</code> and <code>column</code>, or with
	 * the first preceding location with an associated original location, or <code>null</code> if
	 * there is no such location.
	 */
	public SourceLocator floor(int line, int column);

	/**
	 * Get the original location associated with <code>line</code> and <code>column</code>, or with
	 * the first following location with an associated original location, or <code>null</code> if
	 * there is no such location.
	 */
	public SourceLocator ceiling(int line, int column);

}
