package cz.vutbr.web.csskit;

import java.util.Map;

import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.Term;

public interface DeclarationTransformer
{
  boolean parseDeclaration(Declaration d,
      Map<String, CSSProperty> properties, Map<String, Term<?>> values);
}
