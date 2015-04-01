package mime.plain.measure.assoc;

import mime.plain.measure.itemset.ItemSetMeasure;
import be.uantwerpen.adrem.fim.base.PlainItem;
import be.uantwerpen.adrem.fim.base.PlainItemSet;
import be.uantwerpen.adrem.fim.base.PlainTransactionDB;

/**
 * This class contains basic information for association rule measures
 * 
 * @author Sandy Moens & Emin Aksehirli
 */
public abstract class RuleMeasureBase implements ItemSetMeasure, RuleMeasure {

	private static PlainItemSet empty = new PlainItemSet();

	protected int dbSize;

	public RuleMeasureBase(PlainTransactionDB db) {
		dbSize = db.getNumberOfTransactions();
	}

	/**
	 * Evaluates an itemset as a rule by using an empty left hand side and using
	 * the itemset as right hide side for the rule
	 */
	@Override
	public double evaluate(PlainItemSet itemSet) {
		return evaluate(empty, itemSet);
	}

	@Override
	public double evaluate(PlainItemSet itemSet, PlainItem extension) {
		PlainItemSet i = new PlainItemSet(itemSet);
		i.add(extension);
		return evaluate(i);
	}
}
