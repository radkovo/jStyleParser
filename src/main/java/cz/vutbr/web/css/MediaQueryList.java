package cz.vutbr.web.css;

import java.util.List;

/**
 * List of media queries. It matches a medium if at least one of the media queries matches it.
 */
public interface MediaQueryList extends Rule<MediaQuery> {

	/**
	 * Returns a new media query list that is the combination of this and the given media query.
	 */
	public MediaQueryList and(MediaQuery query);

	/**
	 * Returns a new media query list that is the combination of this and the given media query list.
	 */
	public MediaQueryList and(List<MediaQuery> queries);

}
