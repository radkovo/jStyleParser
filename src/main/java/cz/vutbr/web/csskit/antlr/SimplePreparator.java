package cz.vutbr.web.csskit.antlr;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CombinedSelector;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleBlock;
import cz.vutbr.web.css.RuleFactory;
import cz.vutbr.web.css.RuleFontFace;
import cz.vutbr.web.css.RuleMargin;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.RulePage;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.RuleViewport;
import cz.vutbr.web.css.Selector;
import cz.vutbr.web.css.RuleBlock.Priority;
import cz.vutbr.web.css.Selector.PseudoPage;
import cz.vutbr.web.csskit.PriorityStrategy;

public class SimplePreparator implements Preparator {
	protected static final Logger log = LoggerFactory
			.getLogger(SimplePreparator.class);

	private static RuleFactory rf = CSSFactory.getRuleFactory();

	private PriorityStrategy ps;
	private Element elem;
	private boolean inlinePriority;

	public SimplePreparator(PriorityStrategy ps, Element e, boolean inlinePriority) {
		this.ps = ps;
		this.elem = e;
		this.inlinePriority = inlinePriority;
	}

	public RuleBlock<?> prepareRuleSet(List<CombinedSelector> cslist,
			List<Declaration> dlist, boolean wrap, List<String> media) {

		// check emptiness
		if ((cslist == null || cslist.isEmpty())
				&& (dlist == null || dlist.isEmpty())) {
			log.debug("Empty RuleSet was ommited");
			return null;
		}

		// create rule set
		Priority prio = ps.getAndIncrement();
		RuleSet rs = rf.createSet(prio);
		rs.setSelectors(cslist);
		rs.replaceAll(dlist);
		log.info("Created RuleSet as {}th with:\n{}", prio, rs);

		// wrap
		if (wrap) {
			// swap numbers, so RuleMedia is created before RuleSet
			prio = rs.getPriority();
			rs.setPriority(ps.getAndIncrement());
			RuleMedia rm = rf.createMedia(prio);
			log.debug("Wrapping RuleSet {} into RuleMedia: {}", rs, media);

			rm.unlock();
			rm.add(rs);
			rm.setMedia(media);

			// return wrapped block
			return (RuleBlock<?>) rm;
		}

		// return classic rule set
		return (RuleBlock<?>) rs;
	}

	public RuleBlock<?> prepareRuleMedia(Priority mark, List<RuleSet> rules,
			List<String> media) {

		if (rules == null || rules.isEmpty()) {
			log.debug("Empty RuleMedia was ommited");
			return null;
		}

		// create media at position of mark
		RuleMedia rm = rf.createMedia(mark);
		rm.replaceAll(rules);
		if (media != null && !media.isEmpty())
			rm.setMedia(media);

		log.info("Create @media as {}th with:\n{}", mark, rm);

		return (RuleBlock<?>) rm;
	}

	public RuleBlock<?> prepareRulePage(List<Declaration> declarations, List<RuleMargin> marginRules, String name, String pseudo) {

	    if ((declarations == null || declarations.isEmpty()) &&
	         (marginRules == null || marginRules.isEmpty())) {
			log.debug("Empty RulePage was ommited");
			return null;
		}

		Priority prio = ps.getAndIncrement();
		RulePage rp = rf.createPage(prio);
        if (declarations != null)
            for (Declaration d : declarations)
                rp.add(d);
        if (marginRules != null)
            for (RuleMargin m : marginRules)
                rp.add(m);
        rp.setName(name);
		
		rp.setPseudo(pseudo);
		log.info("Create @page as {}th with:\n{}", prio, rp);

		return (RuleBlock<?>) rp;
	}

    public RuleMargin prepareRuleMargin(String area, List<Declaration> decl) {

        if ((decl == null || decl.isEmpty()))
        {
            log.debug("Empty RuleMargin was ommited");
            return null;
        }

        Priority prio = ps.getAndIncrement();
        RuleMargin rm = rf.createMargin(area, prio);
        rm.replaceAll(decl);

        log.info("Create @" + area + " as " + prio + "th with:\n" + rm);

        return rm;
    }
	
    public RuleBlock<?> prepareRuleViewport(List<Declaration> decl) {

        if (decl == null || decl.isEmpty()) {
            log.debug("Empty Viewport was ommited");
            return null;
        }

        Priority prio = ps.getAndIncrement();
        RuleViewport rp = rf.createViewport(prio);
        rp.replaceAll(decl);
        log.info("Create @viewport as {}th with:\n{}", prio, rp);

        return (RuleBlock<?>) rp;
    }

    public RuleBlock<?> prepareRuleFontFace(List<Declaration> decl) {

        if (decl == null || decl.isEmpty()) {
            log.debug("Empty RuleFontFace was ommited");
            return null;
        }

        Priority prio = ps.getAndIncrement();
        RuleFontFace rp = rf.createFontFace(prio);
        rp.replaceAll(decl);
        log.info("Create @font-face as {}th with:\n{}", prio, rp);

        return (RuleBlock<?>) rp;
    }

	public Priority markPriority() {
		return ps.markAndIncrement();
	}

	public RuleBlock<?> prepareInlineRuleSet(List<Declaration> dlist,
			List<PseudoPage> pseudos) {

		if(dlist==null || dlist.isEmpty()) {
			log.debug("Empty RuleSet (inline) was ommited");
			return null;
		}
		
		// create selector with element
		CombinedSelector cs = (CombinedSelector) rf.createCombinedSelector()
				.unlock();
		Selector sel = (Selector) rf.createSelector().unlock();
		sel.add(rf.createElementDOM(elem, inlinePriority));
		if(pseudos!=null) sel.addAll(pseudos);
		cs.add(sel);
		
		Priority prio = ps.getAndIncrement();
		RuleSet rs = rf.createSet(prio);
		rs.replaceAll(dlist);
		rs.setSelectors(Arrays.asList(cs));
		
		log.info("Create @media as {}th with:\n{}", prio, rs);
		
		return (RuleBlock<?>) rs;
	}

}
