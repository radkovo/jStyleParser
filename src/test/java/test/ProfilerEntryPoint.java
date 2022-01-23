package test;

import java.net.URL;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.csskit.antlr.CSSParserFactory;
import cz.vutbr.web.csskit.antlr.CSSSource;
import cz.vutbr.web.csskit.antlr.DefaultCSSSourceReader;

public class ProfilerEntryPoint {

	public static void main(String[] args) throws Exception {

		StyleSheet sheet = CSSParserFactory.getInstance().parse(
				new CSSSource("data/abclinuxu/styles.css", (String)null, (URL)null, 0, 0),
				new DefaultCSSSourceReader());

		System.out.println("Total rules: " + sheet.size());

	}

}
