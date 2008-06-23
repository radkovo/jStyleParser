package cz.vutbr.web.csskit;

import java.util.HashMap;
import java.util.Map;

import cz.vutbr.web.css.TermColor;

/**
 * Holds colors by their names.
 * Ignores System Colors are they are deprecated in CSS3
 * TODO Consider possibility of implementing SystemColors
 * @author kapy
 */
public class ColorCard {

	private static final Map<String, TermColor> map
		= new HashMap<String, TermColor>(17, 1.0f);
	
	static	{
		map.put("maroon", new TermColorImpl(0x80, 0, 0));
		map.put("red", new TermColorImpl(0xff, 0, 0));
        map.put("yellow", new TermColorImpl(0xff, 0xff, 0));
        map.put("orange", new TermColorImpl(0xff, 0xa5, 0));
        map.put("olive", new TermColorImpl(0x80, 0x80, 0));
        map.put("purple", new TermColorImpl(0x80, 0, 0x80));
        map.put("fuchsia", new TermColorImpl(0xff, 0, 0xff));
        map.put("white", new TermColorImpl(0xff, 0xff, 0xff));
        map.put("lime", new TermColorImpl(0, 0xff, 0));
        map.put("green", new TermColorImpl(0, 0x80, 0));
        map.put("navy", new TermColorImpl(0, 0, 0x80));
        map.put("blue", new TermColorImpl(0, 0, 0xff));
        map.put("aqua", new TermColorImpl(0, 0xff, 0xff));
        map.put("teal", new TermColorImpl(0, 0x80, 0x80));
        map.put("black", new TermColorImpl(0, 0, 0));
        map.put("silver", new TermColorImpl(0xc0, 0xc0, 0xc0));
        map.put("gray", new TermColorImpl(0x80, 0x80, 0x80));
	};

	/**
	 * Return color by its name
	 * @param name Name of color
	 * @return Color if found, <code>null</code> otherwise
	 */
	public static TermColor getTermColor(String name) {
		return map.get(name.toLowerCase());
	}
	
}
