package test;

import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.csskit.antlr4.CSSParserFactory;
import cz.vutbr.web.csskit.antlr4.CSSParserFactory.SourceType;

public class ProfilerEntryPoint {

	public static void main(String[] args) throws Exception {

		StyleSheet sheet = CSSParserFactory.getInstance().parse("data/abclinuxu/styles.css", null,
				null, SourceType.URL, null);

		System.out.println("Total rules: " + sheet.size());

	}

}
