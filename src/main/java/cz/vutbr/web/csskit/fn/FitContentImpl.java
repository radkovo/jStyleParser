
package cz.vutbr.web.csskit.fn;

import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermLengthOrPercent;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.csskit.TermFunctionImpl;
import java.util.List;

/**
 *
 * @author Petr Mikulík
 */
public class FitContentImpl extends TermFunctionImpl implements TermFunction.FitContent {

	private TermLengthOrPercent _max;

	@Override
	public TermList setValue(List<Term<?>> value) {
		super.setValue(value);
		List<Term<?>> args = getSeparatedValues(DEFAULT_ARG_SEP, true);
		if (args != null && args.size() == 1) {
			_max = getLengthOrPercentArg(args.get(0));
			if (_max != null) {
				setValid(true);
			}
		}
		return this;
	}

	@Override
	public TermLengthOrPercent getMaximum() {
		return _max;
	}

}
