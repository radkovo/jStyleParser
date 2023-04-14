package cz.vutbr.web.csskit;

import java.util.Collections;
import java.util.List;

import cz.vutbr.web.css.MediaQuery;
import cz.vutbr.web.css.MediaQueryList;
import cz.vutbr.web.css.Rule;

public class MediaQueryListImpl extends AbstractRuleBlock<MediaQuery> implements MediaQueryList {

	/**
	 * Immutable empty media query list.
	 */
	public static final MediaQueryList EMPTY = new MediaQueryListImpl() {
			{
				this.list = Collections.emptyList();
			}
			@Override
			public Rule<MediaQuery> replaceAll(List<MediaQuery> replacement) {
				throw new UnsupportedOperationException("Object is immutable");
			}
			@Override
			public Rule<MediaQuery> unlock() {
				throw new UnsupportedOperationException("Object is immutable");
			}
		};

	public MediaQueryListImpl() {
	}

	public MediaQueryList and(MediaQuery query) {
		if (query == null)
			return this;
		return and(Collections.singletonList(query));
	}

	public MediaQueryList and(List<MediaQuery> queries) {
		if (queries == null || queries.isEmpty())
			return this;
		else if (isEmpty() && queries instanceof MediaQueryList)
			return (MediaQueryList)queries;
		MediaQueryList list = new MediaQueryListImpl();
		list.unlock();
		if (isEmpty()) {
			for (MediaQuery q : queries)
				list.add(q);
			return list;
		}
		for (MediaQuery q : this)
			for (MediaQuery qq : queries) {
				MediaQuery qqq = q.and(qq);
				if (qqq != MediaQueryImpl.NOT_ALL)
					list.add(qqq);
			}
		if (list.isEmpty())
			list.add(MediaQueryImpl.NOT_ALL);
		return list;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb = OutputUtil.appendList(sb, this, OutputUtil.MEDIA_DELIM);
		return sb.toString();
	}
}
