package test;

import java.util.Date;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.StyleSheet;

public class ProfilerEntryPoint {

	public static void main(String[] args) throws Exception {

        Date start = new Date();
		StyleSheet sheet = CSSFactory.parse("src/test/resources/profiling/slate.css", "UTF-8");

		System.out.println("Total rules: " + sheet.size());
        Date end = new Date();
        System.out.println("Parsing time: " + (end.getTime() - start.getTime()) + " ms");

	}

}
