package mime.plain.measure.assoc;

import mime.plain.measure.Measure;
import be.uantwerpen.adrem.fim.base.PlainItemSet;

/**
 * This interface provides basic functionality for association rule measures
 * 
 * @author Sandy Moens & Emin Akserhirli
 */
public interface RuleMeasure extends Measure {

	/**
	 * Evaluates a given association rule and returns the score
	 * 
	 * @param lhs
	 *            the left hand side of the rule
	 * @param rhs
	 *            the right hand side of the rule
	 * @return the score for the association rule
	 */
	public double evaluate(PlainItemSet lhs, PlainItemSet rhs);
}
