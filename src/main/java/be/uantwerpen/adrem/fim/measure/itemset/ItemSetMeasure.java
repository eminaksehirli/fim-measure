package be.uantwerpen.adrem.fim.measure.itemset;

import be.uantwerpen.adrem.fim.measure.Measure;
import be.uantwerpen.adrem.fim.model.Item;
import be.uantwerpen.adrem.fim.model.Itemset;

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
	public double evaluate(Itemset itemSet);

	public double evaluate(Itemset itemSet, Item extension);
}
