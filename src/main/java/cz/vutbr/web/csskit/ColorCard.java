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
		= new HashMap<String, TermColor>(147, 1.0f);
	
	static	{
        map.put("aliceblue", new TermColorImpl(0xf0, 0xf8, 0xff));
        map.put("antiquewhite", new TermColorImpl(0xfa, 0xeb, 0xd7));
        map.put("aqua", new TermColorImpl(0x00, 0xff, 0xff));
        map.put("aquamarine", new TermColorImpl(0x7f, 0xff, 0xd4));
        map.put("azure", new TermColorImpl(0xf0, 0xff, 0xff));
        map.put("beige", new TermColorImpl(0xf5, 0xf5, 0xdc));
        map.put("bisque", new TermColorImpl(0xff, 0xe4, 0xc4));
        map.put("black", new TermColorImpl(0x00, 0x00, 0x00));
        map.put("blanchedalmond", new TermColorImpl(0xff, 0xeb, 0xcd));
        map.put("blue", new TermColorImpl(0x00, 0x00, 0xff));
        map.put("blueviolet", new TermColorImpl(0x8a, 0x2b, 0xe2));
        map.put("brown", new TermColorImpl(0xa5, 0x2a, 0x2a));
        map.put("burlywood", new TermColorImpl(0xde, 0xb8, 0x87));
        map.put("cadetblue", new TermColorImpl(0x5f, 0x9e, 0xa0));
        map.put("chartreuse", new TermColorImpl(0x7f, 0xff, 0x00));
        map.put("chocolate", new TermColorImpl(0xd2, 0x69, 0x1e));
        map.put("coral", new TermColorImpl(0xff, 0x7f, 0x50));
        map.put("cornflowerblue", new TermColorImpl(0x64, 0x95, 0xed));
        map.put("cornsilk", new TermColorImpl(0xff, 0xf8, 0xdc));
        map.put("crimson", new TermColorImpl(0xdc, 0x14, 0x3c));
        map.put("cyan", new TermColorImpl(0x00, 0xff, 0xff));
        map.put("darkblue", new TermColorImpl(0x00, 0x00, 0x8b));
        map.put("darkcyan", new TermColorImpl(0x00, 0x8b, 0x8b));
        map.put("darkgoldenrod", new TermColorImpl(0xb8, 0x86, 0x0b));
        map.put("darkgray", new TermColorImpl(0xa9, 0xa9, 0xa9));
        map.put("darkgreen", new TermColorImpl(0x00, 0x64, 0x00));
        map.put("darkgrey", new TermColorImpl(0xa9, 0xa9, 0xa9));
        map.put("darkkhaki", new TermColorImpl(0xbd, 0xb7, 0x6b));
        map.put("darkmagenta", new TermColorImpl(0x8b, 0x00, 0x8b));
        map.put("darkolivegreen", new TermColorImpl(0x55, 0x6b, 0x2f));
        map.put("darkorange", new TermColorImpl(0xff, 0x8c, 0x00));
        map.put("darkorchid", new TermColorImpl(0x99, 0x32, 0xcc));
        map.put("darkred", new TermColorImpl(0x8b, 0x00, 0x00));
        map.put("darksalmon", new TermColorImpl(0xe9, 0x96, 0x7a));
        map.put("darkseagreen", new TermColorImpl(0x8f, 0xbc, 0x8f));
        map.put("darkslateblue", new TermColorImpl(0x48, 0x3d, 0x8b));
        map.put("darkslategray", new TermColorImpl(0x2f, 0x4f, 0x4f));
        map.put("darkslategrey", new TermColorImpl(0x2f, 0x4f, 0x4f));
        map.put("darkturquoise", new TermColorImpl(0x00, 0xce, 0xd1));
        map.put("darkviolet", new TermColorImpl(0x94, 0x00, 0xd3));
        map.put("deeppink", new TermColorImpl(0xff, 0x14, 0x93));
        map.put("deepskyblue", new TermColorImpl(0x00, 0xbf, 0xff));
        map.put("dimgray", new TermColorImpl(0x69, 0x69, 0x69));
        map.put("dimgrey", new TermColorImpl(0x69, 0x69, 0x69));
        map.put("dodgerblue", new TermColorImpl(0x1e, 0x90, 0xff));
        map.put("firebrick", new TermColorImpl(0xb2, 0x22, 0x22));
        map.put("floralwhite", new TermColorImpl(0xff, 0xfa, 0xf0));
        map.put("forestgreen", new TermColorImpl(0x22, 0x8b, 0x22));
        map.put("fuchsia", new TermColorImpl(0xff, 0x00, 0xff));
        map.put("gainsboro", new TermColorImpl(0xdc, 0xdc, 0xdc));
        map.put("ghostwhite", new TermColorImpl(0xf8, 0xf8, 0xff));
        map.put("gold", new TermColorImpl(0xff, 0xd7, 0x00));
        map.put("goldenrod", new TermColorImpl(0xda, 0xa5, 0x20));
        map.put("gray", new TermColorImpl(0x80, 0x80, 0x80));
        map.put("green", new TermColorImpl(0x00, 0x80, 0x00));
        map.put("greenyellow", new TermColorImpl(0xad, 0xff, 0x2f));
        map.put("grey", new TermColorImpl(0x80, 0x80, 0x80));
        map.put("honeydew", new TermColorImpl(0xf0, 0xff, 0xf0));
        map.put("hotpink", new TermColorImpl(0xff, 0x69, 0xb4));
        map.put("indianred", new TermColorImpl(0xcd, 0x5c, 0x5c));
        map.put("indigo", new TermColorImpl(0x4b, 0x00, 0x82));
        map.put("ivory", new TermColorImpl(0xff, 0xff, 0xf0));
        map.put("khaki", new TermColorImpl(0xf0, 0xe6, 0x8c));
        map.put("lavender", new TermColorImpl(0xe6, 0xe6, 0xfa));
        map.put("lavenderblush", new TermColorImpl(0xff, 0xf0, 0xf5));
        map.put("lawngreen", new TermColorImpl(0x7c, 0xfc, 0x00));
        map.put("lemonchiffon", new TermColorImpl(0xff, 0xfa, 0xcd));
        map.put("lightblue", new TermColorImpl(0xad, 0xd8, 0xe6));
        map.put("lightcoral", new TermColorImpl(0xf0, 0x80, 0x80));
        map.put("lightcyan", new TermColorImpl(0xe0, 0xff, 0xff));
        map.put("lightgoldenrodyellow", new TermColorImpl(0xfa, 0xfa, 0xd2));
        map.put("lightgray", new TermColorImpl(0xd3, 0xd3, 0xd3));
        map.put("lightgreen", new TermColorImpl(0x90, 0xee, 0x90));
        map.put("lightgrey", new TermColorImpl(0xd3, 0xd3, 0xd3));
        map.put("lightpink", new TermColorImpl(0xff, 0xb6, 0xc1));
        map.put("lightsalmon", new TermColorImpl(0xff, 0xa0, 0x7a));
        map.put("lightseagreen", new TermColorImpl(0x20, 0xb2, 0xaa));
        map.put("lightskyblue", new TermColorImpl(0x87, 0xce, 0xfa));
        map.put("lightslategray", new TermColorImpl(0x77, 0x88, 0x99));
        map.put("lightslategrey", new TermColorImpl(0x77, 0x88, 0x99));
        map.put("lightsteelblue", new TermColorImpl(0xb0, 0xc4, 0xde));
        map.put("lightyellow", new TermColorImpl(0xff, 0xff, 0xe0));
        map.put("lime", new TermColorImpl(0x00, 0xff, 0x00));
        map.put("limegreen", new TermColorImpl(0x32, 0xcd, 0x32));
        map.put("linen", new TermColorImpl(0xfa, 0xf0, 0xe6));
        map.put("magenta", new TermColorImpl(0xff, 0x00, 0xff));
        map.put("maroon", new TermColorImpl(0x80, 0x00, 0x00));
        map.put("mediumaquamarine", new TermColorImpl(0x66, 0xcd, 0xaa));
        map.put("mediumblue", new TermColorImpl(0x00, 0x00, 0xcd));
        map.put("mediumorchid", new TermColorImpl(0xba, 0x55, 0xd3));
        map.put("mediumpurple", new TermColorImpl(0x93, 0x70, 0xdb));
        map.put("mediumseagreen", new TermColorImpl(0x3c, 0xb3, 0x71));
        map.put("mediumslateblue", new TermColorImpl(0x7b, 0x68, 0xee));
        map.put("mediumspringgreen", new TermColorImpl(0x00, 0xfa, 0x9a));
        map.put("mediumturquoise", new TermColorImpl(0x48, 0xd1, 0xcc));
        map.put("mediumvioletred", new TermColorImpl(0xc7, 0x15, 0x85));
        map.put("midnightblue", new TermColorImpl(0x19, 0x19, 0x70));
        map.put("mintcream", new TermColorImpl(0xf5, 0xff, 0xfa));
        map.put("mistyrose", new TermColorImpl(0xff, 0xe4, 0xe1));
        map.put("moccasin", new TermColorImpl(0xff, 0xe4, 0xb5));
        map.put("navajowhite", new TermColorImpl(0xff, 0xde, 0xad));
        map.put("navy", new TermColorImpl(0x00, 0x00, 0x80));
        map.put("oldlace", new TermColorImpl(0xfd, 0xf5, 0xe6));
        map.put("olive", new TermColorImpl(0x80, 0x80, 0x00));
        map.put("olivedrab", new TermColorImpl(0x6b, 0x8e, 0x23));
        map.put("orange", new TermColorImpl(0xff, 0xa5, 0x00));
        map.put("orangered", new TermColorImpl(0xff, 0x45, 0x00));
        map.put("orchid", new TermColorImpl(0xda, 0x70, 0xd6));
        map.put("palegoldenrod", new TermColorImpl(0xee, 0xe8, 0xaa));
        map.put("palegreen", new TermColorImpl(0x98, 0xfb, 0x98));
        map.put("paleturquoise", new TermColorImpl(0xaf, 0xee, 0xee));
        map.put("palevioletred", new TermColorImpl(0xdb, 0x70, 0x93));
        map.put("papayawhip", new TermColorImpl(0xff, 0xef, 0xd5));
        map.put("peachpuff", new TermColorImpl(0xff, 0xda, 0xb9));
        map.put("peru", new TermColorImpl(0xcd, 0x85, 0x3f));
        map.put("pink", new TermColorImpl(0xff, 0xc0, 0xcb));
        map.put("plum", new TermColorImpl(0xdd, 0xa0, 0xdd));
        map.put("powderblue", new TermColorImpl(0xb0, 0xe0, 0xe6));
        map.put("purple", new TermColorImpl(0x80, 0x00, 0x80));
        map.put("red", new TermColorImpl(0xff, 0x00, 0x00));
        map.put("rosybrown", new TermColorImpl(0xbc, 0x8f, 0x8f));
        map.put("royalblue", new TermColorImpl(0x41, 0x69, 0xe1));
        map.put("saddlebrown", new TermColorImpl(0x8b, 0x45, 0x13));
        map.put("salmon", new TermColorImpl(0xfa, 0x80, 0x72));
        map.put("sandybrown", new TermColorImpl(0xf4, 0xa4, 0x60));
        map.put("seagreen", new TermColorImpl(0x2e, 0x8b, 0x57));
        map.put("seashell", new TermColorImpl(0xff, 0xf5, 0xee));
        map.put("sienna", new TermColorImpl(0xa0, 0x52, 0x2d));
        map.put("silver", new TermColorImpl(0xc0, 0xc0, 0xc0));
        map.put("skyblue", new TermColorImpl(0x87, 0xce, 0xeb));
        map.put("slateblue", new TermColorImpl(0x6a, 0x5a, 0xcd));
        map.put("slategray", new TermColorImpl(0x70, 0x80, 0x90));
        map.put("slategrey", new TermColorImpl(0x70, 0x80, 0x90));
        map.put("snow", new TermColorImpl(0xff, 0xfa, 0xfa));
        map.put("springgreen", new TermColorImpl(0x00, 0xff, 0x7f));
        map.put("steelblue", new TermColorImpl(0x46, 0x82, 0xb4));
        map.put("tan", new TermColorImpl(0xd2, 0xb4, 0x8c));
        map.put("teal", new TermColorImpl(0x00, 0x80, 0x80));
        map.put("thistle", new TermColorImpl(0xd8, 0xbf, 0xd8));
        map.put("tomato", new TermColorImpl(0xff, 0x63, 0x47));
        map.put("turquoise", new TermColorImpl(0x40, 0xe0, 0xd0));
        map.put("violet", new TermColorImpl(0xee, 0x82, 0xee));
        map.put("wheat", new TermColorImpl(0xf5, 0xde, 0xb3));
        map.put("white", new TermColorImpl(0xff, 0xff, 0xff));
        map.put("whitesmoke", new TermColorImpl(0xf5, 0xf5, 0xf5));
        map.put("yellow", new TermColorImpl(0xff, 0xff, 0x00));
        map.put("yellowgreen", new TermColorImpl(0x9a, 0xcd, 0x32));
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
