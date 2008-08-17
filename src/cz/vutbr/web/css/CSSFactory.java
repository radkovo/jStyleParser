package cz.vutbr.web.css;

/**
 * This class is abstract factory for other factories used during CSS parsing.
 * Use it, for example, to retrieve current(default) TermFactory, current(default)
 * SupportedCSS implementation and so on.
 * 
 * Factories need to be registered first.
 * This can be done using Java static block initialization together with 
 * Java classloader.
 * 
 * By default, factory searches automatically for implementations:
 * <code>cz.vutbr.web.csskit.TermFactoryImpl</code>
 * <code>cz.vutbr.web.domassign.SupportedCSS21</code>
 * 
 * Example:
 * <pre>
 * public class TermFactoryImpl implemenent TermFactory {
 * 		static {
 * 			CSSFactory.registerTermFactory(new TermFactoryImpl());
 * 		}
 * 		...
 * }
 * 
 * That, default factory is set when this class is loaded by class loader.
 * <pre>
 * Class.forName("xx.package.TermFactoryImpl")
 * </pre>
 * 
 * </pre>
 * @author kapy
 *
 */
public final class CSSFactory {
	
	private static final String DEFAULT_TERM_FACTORY = "cz.vutbr.web.csskit.TermFactoryImpl";
	private static final String DEFAULT_SUPPORTED_CSS = "cz.vutbr.web.domassign.SupportedCSS21";
	private static final String DEFAULT_RULE_FACTORY = "cz.vutbr.web.csskit.RuleFactoryImpl";
	
	
	/**
	 * Default instance of TermFactory implementation
	 */
	private static TermFactory tf;
	
	/**
	 * Default instance of SupportedCSS implementation
	 */
	private static SupportedCSS css;
	
	/**
	 * Default instance of RuleFactory implementation 
	 */
	private static RuleFactory rf;
	
	/**
	 * Registers new TermFactory instance
	 * @param newFactory New TermFactory
	 */
	public static final void registerTermFactory(TermFactory newFactory) {
		tf = newFactory;
	}

	/**
	 * Returns TermFactory registered in step above
	 * @return TermFactory registered
	 */
	public static final TermFactory getTermFactory() {
		if(tf==null) {
			try {
				Class.forName(DEFAULT_TERM_FACTORY);
				if(tf!=null) return tf;
			}
			catch(Exception e) {}
			
			throw new RuntimeException("No TermFactory implementation registered!");
		}
		return tf;
	}

	public static final void registerSupportedCSS(SupportedCSS newCSS) {
		css = newCSS;
	}
	
	
	public static final SupportedCSS getSupportedCSS() {
		if(css==null) {
			try {
				Class.forName(DEFAULT_SUPPORTED_CSS);
				if(css!=null) return css;
			}
			catch(Exception e) {}
			
			throw new RuntimeException("No SupportedCSS implementation registered!");
		}
		return css;
	}
	
	public static final void registerRuleFactory(RuleFactory newRuleFactory) {
		rf = newRuleFactory;
	}
	
	public static final RuleFactory getRuleFactory() {
		if(rf==null) {
			try {
				Class.forName(DEFAULT_RULE_FACTORY);
				if(rf!=null) return rf;
			}
			catch(Exception e) {}
			
			throw new RuntimeException("No RuleFactory implementation registered!");
		}
		
		return rf;
	}
	
}
