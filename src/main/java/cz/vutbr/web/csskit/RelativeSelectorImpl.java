package cz.vutbr.web.csskit;

import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.ElementMatcher;
import cz.vutbr.web.css.MatchCondition;
import cz.vutbr.web.css.Selector;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RelativeSelectorImpl extends SelectorImpl {

    public static class RelationalPseudoClassImpl implements PseudoClass {

        private final String name;
        private final String functionValue;
        private final PseudoClassType type;
        private final List<CombinedSelector> relativeSelector;

        public RelationalPseudoClassImpl(String name, String functionValue, List<CombinedSelector> relativeSelector) {
            this.name = name;
            type = PseudoClassType.forName(name.substring(0, name.length()-1));
            this.functionValue = functionValue;

            if (relativeSelector.size() < 1) {
                throw new RuntimeException(":has() must not be empty");
            }
            this.relativeSelector = relativeSelector;
        }

        protected RelationalPseudoClassImpl(String name, List<CombinedSelector> nestedSelector) {
            this(name, null, nestedSelector);
        }

        @Override
        public boolean matches(Element e, ElementMatcher matcher, MatchCondition cond) {
            for (CombinedSelector s : relativeSelector) {
                if (matchesRelative(s, e, cond, matcher)) {
                    return true;
                }
            }
            return false;
        }

        public static boolean matchesRelative(List<Selector> selector, Element e, MatchCondition cond, ElementMatcher matcher) {
            Iterator<Selector> it = selector.iterator();
            Selector first = it.next();
            Selector.Combinator combinator = first.getCombinator();
            List<Selector> rest = null;
            if (it.hasNext()) {
                rest = new ArrayList<Selector>();
                while (it.hasNext()) {
                    rest.add(it.next());
                }
            }
            switch (combinator) {
                case CHILD:
                case DESCENDANT:
                    NodeList children = e.getChildNodes();
                    for (int i = 0; i < children.getLength(); i++) {
                        Node child = children.item(i);
                        if (child instanceof Element) {
                            if (first.matches((Element)child, matcher, cond)) {
                                if (rest == null || matchesRelative(rest, (Element) child, cond, matcher)) {
                                    return true;
                                }
                            }
                        }
                    }
                    if (combinator == Selector.Combinator.DESCENDANT) {
                        for (int i = 0; i < children.getLength(); i++) {
                            Node child = children.item(i);
                            if (child instanceof Element) {
                                if (matchesRelative(selector, (Element)child, cond, matcher)) {
                                    return true;
                                }
                            }
                        }
                    }
                    break;
                case ADJACENT:
                case PRECEDING:
                    Node next = e.getNextSibling();
                    while (next != null && !(next instanceof Element)) {
                        next = next.getNextSibling();
                    }
                    if (next != null) {
                        if (first.matches((Element)next, matcher, cond)) {
                            if (rest == null || matchesRelative(rest, (Element) next, cond, matcher)) {
                                return true;
                            }
                        }
                        if (combinator == Selector.Combinator.PRECEDING) {
                            if (matchesRelative(selector, (Element)next, cond, matcher)) {
                                return true;
                            }
                        }
                    }
                    break;
            }
            return false;
        }

        public void computeSpecificity(CombinedSelector.Specificity specificity) {
            CombinedSelector mostSpecificSelector = null;
            CombinedSelector.Specificity highestSpecificity = null;
            for (CombinedSelector sel : relativeSelector) {
                CombinedSelector.Specificity spec = new CombinedSelectorImpl.SpecificityImpl();
                for (Selector s : sel) {
                    s.computeSpecificity(spec);
                }
                if (highestSpecificity == null || spec.compareTo(highestSpecificity) > 0) {
                    mostSpecificSelector = sel;
                    highestSpecificity = spec;
                }
            }
            for (Selector s : mostSpecificSelector) {
                s.computeSpecificity(specificity);
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(":has(");
            OutputUtil.appendList(sb, relativeSelector, ", ");
            sb.append(")");
            return sb.toString();
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            return prime + relativeSelector.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            RelationalPseudoClassImpl other = (RelationalPseudoClassImpl) obj;
            if (!relativeSelector.equals(other.relativeSelector)) {
                return false;
            }
            return true;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getFunctionValue() {
            return functionValue;
        }

        @Override
        public PseudoClassType getType() {
            return type;
        }

        @Override
        public List<Selector> getNestedSelectors() {
            return null;
        }

        public List<CombinedSelector> getRelativeSelectors() {
            return relativeSelector;
        }

    }
}
