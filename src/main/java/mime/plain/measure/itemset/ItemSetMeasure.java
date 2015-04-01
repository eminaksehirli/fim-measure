package mime.plain.measure.itemset;

import mime.plain.measure.Measure;
import be.uantwerpen.adrem.fim.base.PlainItem;
import be.uantwerpen.adrem.fim.base.PlainItemSet;

/**
 * This interface provides basic functionality for itemset measures
 * 
 * @author Sandy Moens & Emin Akserhirli
 */
public interface ItemSetMeasure extends Measure {

	/**
	 * Evaluates a given itemset and returns the score
	 * 
	 * @param itemSet
	 *            the itemset to evaluate
	 * @return the score for the itemset
	 */
	public double evaluate(PlainItemSet itemSet);

	public double evaluate(PlainItemSet itemSet, PlainItem extension);
}
