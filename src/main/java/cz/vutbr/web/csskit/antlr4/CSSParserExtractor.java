package cz.vutbr.web.csskit.antlr4;

import cz.vutbr.web.css.MediaQuery;
import cz.vutbr.web.css.RuleList;

import java.util.List;

/**
 * CSS Parser extractor interface
 * should be implemented by ANTLR listener or visitor extractor
 */
public interface CSSParserExtractor {
    List<String> getImportPaths();

    List<List<MediaQuery>> getImportMedia();

    RuleList getRules();

    List<MediaQuery> getMedia();
}
